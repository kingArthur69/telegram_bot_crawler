package com.arthur.telegram.commands.service;

import com.arthur.crawlers.CrawlResult;
import com.arthur.crawlers.CrawlerService;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class CrawlCommand extends ServiceCommand {
	private CrawlerService service;

	public CrawlCommand(String identifier, String description) {
		super(identifier, description);
		service = new CrawlerService();
	}

	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

		CrawlResult result;
		if (strings.length == 1) {
			result = service.crawl(strings[0]);
		} else {
			result = service.crawl(strings);
		}

		sendAnswer(absSender, chat.getId(), user.getUserName(), this.getCommandIdentifier(),
				"Options: \n" + service.showOptions(result)
						+ "\n<------->\nResults: \n" + service.showFoundObjects(result));
	}
}
