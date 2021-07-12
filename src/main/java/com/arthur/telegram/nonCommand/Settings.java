package com.arthur.telegram.nonCommand;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Пользовательские настройки
 */
@Data
@EqualsAndHashCode
public class Settings {

	private int resultsLimit;

	public Settings() {
		this.resultsLimit = 5;
	}

	@Override
	public String toString() {
		return "ResultsLimit: " + resultsLimit;
	}

	//	public Settings() {
//		setting1 = SettingsAssistant.getSet1();
//	}
}
