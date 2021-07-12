package com.arthur.telegram.commands.service;

import com.arthur.crawlers.CrawlResult;
import com.arthur.crawlers.CrawlerService;
import com.arthur.telegram.Bot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class CrawlCommand extends ServiceCommand {
	private CrawlerService crawlerService;

	public CrawlCommand(String identifier, String description) {
		super(identifier, description);
		crawlerService = new CrawlerService();
	}

	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

		CrawlResult result;
		if (strings.length == 1) {
			result = crawlerService.crawl(strings[0]);
		} else {
			result = crawlerService.crawl(strings);
		}

		result.getFoundObjects().stream()
				.limit(Bot.getUserSettings(chat.getId()).getResultsLimit())
				.forEach(article ->
						sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), user.getUserName(), article.toString())
				);

		sendAnswerWithKeyboard(absSender, chat.getId(), this.getCommandIdentifier(), user.getUserName(),
				crawlerService.showOptionsNames(result), crawlerService.showOptionsKeyboard(result, this.getCommandIdentifier(), strings));
	}
}
