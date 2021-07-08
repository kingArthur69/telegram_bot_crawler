package com.arthur.telegram.commands.operations;

import com.arthur.crawlers.CrawlerService;
import lombok.extern.log4j.Log4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Log4j
abstract class OperationCommand extends BotCommand {
	//	private ArithmeticService service;
	private CrawlerService service;

	OperationCommand(String identifier, String description) {
		super(identifier, description);
		this.service = new CrawlerService();
	}

	/**
	 * Отправка ответа пользователю
	 */
	void sendAnswer(AbsSender absSender, Long chatId, String text, String description,
					String commandName, String userName) {
//		try {
////			absSender.execute(text);
////			absSender.execute(createDocument(chatId, operations, description));
//		} catch (IOException | RuntimeException e) {
//			log.error(String.format("Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName, userName));
//			sendError(absSender, chatId, commandName, userName);
//			e.printStackTrace();
//		} catch (TelegramApiException e) {
//			log.error(String.format("Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName, userName));
//			e.printStackTrace();
//		}
	}

//	/**
//	 * Создание документа для отправки пользователю
//	 *
//	 * @param chatId     id чата
//	 * @param operations список типов операций (сложение и/или вычитание)
//	 * @param fileName   имя, которое нужно присвоить файлу
//	 */
//	private SendDocument createDocument(Long chatId, List<OperationEnum> operations, String fileName) throws IOException {
//		FileInputStream stream = service.getFile(operations, Bot.getUserSettings(chatId));
//		SendDocument document = new SendDocument();
//		document.setChatId(chatId.toString());
//		document.setDocument(new InputFile(stream, String.format("%s.docx", fileName)));
//		return document;
//	}

	/**
	 * Отправка пользователю сообщения об ошибке
	 */
	private void sendError(AbsSender absSender, Long chatId, String commandName, String userName) {
		try {
			absSender.execute(new SendMessage(chatId.toString(), "Похоже, я сломался. Попробуйте позже"));
		} catch (TelegramApiException e) {
			log.error(String.format("Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName, userName));
			e.printStackTrace();
		}
	}
}
