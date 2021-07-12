package com.arthur.telegram.nonCommand;

import com.arthur.telegram.Bot;
import com.arthur.telegram.exceptions.IllegalSettingsException;
import lombok.extern.log4j.Log4j;

@Log4j
public class NonCommand {

	public String nonCommandExecute(Long chatId, String userName, String text) {
		log.debug(String.format("Пользователь %s. Начата обработка сообщения \"%s\", не являющегося командой",
				userName, text));

		Settings settings;
		String answer;
		try {
			log.debug(String.format("Пользователь %s. Пробуем создать объект настроек из сообщения \"%s\"",
					userName, text));
			settings = createSettings(text);
			saveUserSettings(chatId, settings);
			log.debug(String.format("Пользователь %s. Объект настроек из сообщения \"%s\" создан и сохранён",
					userName, text));
			answer = "Настройки обновлены. Вы всегда можете их посмотреть с помощью /settings";
		} catch (IllegalSettingsException e) {
			log.debug(String.format("Пользователь %s. Не удалось создать объект настроек из сообщения \"%s\". " +
					"%s", userName, text, e.getMessage()));
			answer = e.getMessage() +
					"\n\n❗ Настройки не были изменены. Вы всегда можете их посмотреть с помощью /settings";
		} catch (Exception e) {
			log.debug(String.format("Пользователь %s. Не удалось создать объект настроек из сообщения \"%s\". " +
					"%s. %s", userName, text, e.getClass().getSimpleName(), e.getMessage()));
			answer = "Простите, я не понимаю Вас. Похоже, что Вы ввели сообщение, не соответствующее формату, или " +
					"использовали слишком большие числа\n\n" +
					"Возможно, Вам поможет /help";
		}

		log.debug(String.format("Пользователь %s. Завершена обработка сообщения \"%s\", не являющегося командой",
				userName, text));
		return answer;
	}

	/**
	 * Создание настроек из полученного пользователем сообщения
	 *
	 * @param text текст сообщения
	 * @throws IllegalArgumentException пробрасывается, если сообщение пользователя не соответствует формату
	 */
	private Settings createSettings(String text) throws IllegalArgumentException {
		//отсекаем файлы, стикеры, гифки и прочий мусор
		if (text == null) {
			throw new IllegalArgumentException("Сообщение не является текстом");
		}

		Settings settings = new Settings();
		settings.setResultsLimit(Integer.valueOf(text));
		return settings;
	}

	/**
	 * Валидация настроек
	 */
	private void validateSettings() {
		if (false) {
			throw new IllegalSettingsException("");
		}
	}

	/**
	 * Добавление настроек пользователя в мапу, чтобы потом их использовать для этого пользователя при генерации файла
	 * Если настройки совпадают с дефолтными, они не сохраняются, чтобы впустую не раздувать мапу
	 *
	 * @param chatId   id чата
	 * @param settings настройки
	 */
	private void saveUserSettings(Long chatId, Settings settings) {
		if (!settings.equals(Bot.getDefaultSettings())) {
			Bot.getUserSettings().put(chatId, settings);
		} else {
			Bot.getUserSettings().remove(chatId);
		}
	}

}
