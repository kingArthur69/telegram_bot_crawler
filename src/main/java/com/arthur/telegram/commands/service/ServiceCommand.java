package com.arthur.telegram.commands.service;

import lombok.extern.log4j.Log4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Суперкласс для сервисных команд
 */
@Log4j
abstract class ServiceCommand extends BotCommand {

	ServiceCommand(String identifier, String description) {
		super(identifier, description);
	}

	/**
	 * Отправка ответа пользователю
	 */
	void sendAnswer(AbsSender absSender, Long chatId, String commandName, String userName, String text) {
		SendMessage message = new SendMessage();
		message.enableMarkdown(true);
		message.setChatId(chatId.toString());
		message.setText(text);
		try {
			absSender.execute(message);
		} catch (TelegramApiException e) {
			log.error(String.format("Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName, userName));
			log.error(e.getMessage(), e);
		}
	}

	void sendAnswerWithKeyboard(AbsSender absSender, Long chatId, String commandName, String userName, String text, List<String> options) {
		SendMessage message = new SendMessage();
		message.enableMarkdown(true);
		message.setChatId(chatId.toString());
		message.setText(text);

		ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
		markup.setOneTimeKeyboard(true);
		markup.setResizeKeyboard(true);

		List<KeyboardRow> rows = options.stream().map(option -> {
			KeyboardRow row = new KeyboardRow();
			row.add(option);
			return row;
		}).collect(Collectors.toList());

		markup.setKeyboard(rows);

		message.setReplyMarkup(markup);

		try {
			absSender.execute(message);
		} catch (TelegramApiException e) {
			log.error(String.format("Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName, userName));
			log.error(e.getMessage(), e);
		}
	}






}