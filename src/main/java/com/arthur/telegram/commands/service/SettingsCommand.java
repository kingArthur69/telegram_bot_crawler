package com.arthur.telegram.commands.service;

import com.arthur.telegram.Bot;
import com.arthur.telegram.nonCommand.Settings;
import lombok.extern.log4j.Log4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Log4j
public class SettingsCommand extends ServiceCommand {

	public SettingsCommand(String identifier, String description) {
		super(identifier, description);
	}

	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
		String userName = user.getUserName();

		log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName,
				this.getCommandIdentifier()));

		Long chatId = chat.getId();
		Settings settings = Bot.getUserSettings(chatId);
		sendAnswer(absSender, chatId, this.getCommandIdentifier(), userName, "*Текущие настройки*:\n" + settings.toString());

		log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName,
				this.getCommandIdentifier()));
	}
}
