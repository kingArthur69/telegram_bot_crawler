package com.arthur.telegram.commands.service;

import lombok.extern.log4j.Log4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Log4j
public class HelpCommand extends ServiceCommand {

	public HelpCommand(String identifier, String description) {
		super(identifier, description);
	}

	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
		String userName = user.getUserName();

		log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName,
				this.getCommandIdentifier()));
		sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
				"Я бот,\n\n" +
						"❗*Список команд*\n" +
						"/crawl - crawl site\n" +
						"/all - show all available sites\n" +
						"/settings - просмотреть текущие настройки\n" +
						"/help - помощь\n\n" +
						"Желаю удачи\uD83D\uDE42");
		log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName,
				this.getCommandIdentifier()));
	}
}
