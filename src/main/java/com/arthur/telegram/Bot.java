package com.arthur.telegram;

import com.arthur.telegram.commands.service.*;
import com.arthur.telegram.nonCommand.NonCommand;
import com.arthur.telegram.nonCommand.Settings;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

@Log4j
public final class Bot extends TelegramLongPollingCommandBot {

	@Getter
	private static final Settings defaultSettings = new Settings();
	/**
	 * Настройки файла для разных пользователей. Ключ - уникальный id чата
	 */
	@Getter
	private static Map<Long, Settings> userSettings;
	private final String BOT_NAME;
	private final String BOT_TOKEN;
	private final NonCommand nonCommand;

	public Bot(String botName, String botToken) {
		super();
		log.debug("Конструктор суперкласса отработал");
		this.BOT_NAME = botName;
		this.BOT_TOKEN = botToken;
		log.debug("Имя и токен присвоены");

		this.nonCommand = new NonCommand();
		log.debug("Класс обработки сообщения, не являющегося командой, создан");

		register(new StartCommand("start", "Старт"));
		log.debug("Команда start создана");

		register(new CrawlCommand("crawl", "Crawl"));
		log.debug("Команда plus создана");
//
//		register(new MinusCommand("minus", "Вычитание"));
//		log.debug("Команда minus создана");
//
//		register(new PlusMinusCommand("plusminus", "Сложение и вычитание"));
//		log.debug("Команда plusminus создана");
//
//		register(new MultiplicationCommand("multiply", "Умножение"));
//		log.debug("Команда multiply создана");
//
//		register(new DivisionCommand("divide", "Деление"));
//		log.debug("Команда divide создана");
//
//		register(new MultiplicationDivisionCommand("multdivide", "Умножение и деление"));
//		log.debug("Команда multdivide создана");
//
		register(new AllCommand("all", "All commands"));
		log.debug("Команда all создана");
//
		register(new HelpCommand("help", "Помощь"));
		log.debug("Команда help создана");

		register(new SettingsCommand("settings", "Мои настройки"));
		log.debug("Команда settings создана");

		userSettings = new HashMap<>();
		log.info("Бот создан!");
	}

	/**
	 * Получение настроек по id чата. Если ранее для этого чата в ходе сеанса работы бота настройки не были установлены,
	 * используются настройки по умолчанию
	 */
	public static Settings getUserSettings(Long chatId) {
		Map<Long, Settings> userSettings = Bot.getUserSettings();
		Settings settings = userSettings.get(chatId);
		if (settings == null) {
			return defaultSettings;
		}
		return settings;
	}

	@Override
	public String getBotToken() {
		return BOT_TOKEN;
	}

	@Override
	public String getBotUsername() {
		return BOT_NAME;
	}

	/**
	 * Ответ на запрос, не являющийся командой
	 */
	@Override
	public void processNonCommandUpdate(Update update) {
		Message msg = update.getMessage();
		Long chatId = msg.getChatId();
		String userName = msg.getFrom().getUserName();

		String answer = nonCommand.nonCommandExecute(chatId, userName, msg.getText());
		setAnswer(chatId, userName, answer);
	}

	/**
	 * Отправка ответа
	 *
	 * @param chatId   id чата
	 * @param userName имя пользователя
	 * @param text     текст ответа
	 */
	private void setAnswer(Long chatId, String userName, String text) {
		SendMessage answer = new SendMessage();
		answer.setText(text);
		answer.setChatId(chatId.toString());
		try {
			execute(answer);
		} catch (TelegramApiException e) {
			log.error(String.format("Ошибка %s. Сообщение, не являющееся командой. Пользователь: %s", e.getMessage(),
					userName));
		}
	}
}
