package georgmargus.springboot.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

@Service
public class RssReaderService{
	
	@Autowired
	private RssEntryDao rssEntryDao;
	
	@Autowired
	private SyndFeed feed;
	
	@Scheduled(fixedRate = 24 * 3600 * 1000)
	public void getRssEntries() {
		for(SyndEntry entry : feed.getEntries()) {
			String author = entry.getAuthor();
			String title = entry.getTitle();
			String description = entry.getDescription() != null ? entry.getDescription().getValue() : null;
			RssEntry rssEntry = new RssEntry(author, title, description);
			rssEntryDao.insert(rssEntry);
		}
	}
	
}
