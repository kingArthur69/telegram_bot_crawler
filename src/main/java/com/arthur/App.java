package com.arthur;

import com.arthur.telegram.Bot;
import lombok.extern.log4j.Log4j;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

@Log4j
public class App {

	private static final Map<String, String> getenv = System.getenv();

	public static void main(String[] args) {
		try {

			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new Bot(getenv.get("BOT_NAME"), getenv.get("BOT_TOKEN")));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}


	}
}
