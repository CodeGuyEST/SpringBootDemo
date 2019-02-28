package georgmargus.springboot.demo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "scheduling.enabled=false")
@AutoConfigureMockMvc
public class RssEntryDaoTests {
	
	@Autowired
	private RssEntryDao rssEntryDao;

	@Test
	@DirtiesContext
	public void testInsert() {
		RssEntry rssEntry = new RssEntry(1, "author", "title", "description");
		assertEquals(rssEntryDao.insert(rssEntry), 1);
		
		List<RssEntry> rssEntries = rssEntryDao.findAll();

		assertEquals(rssEntries.size(), 1);
		assertEquals(rssEntries.get(0).getId(), 1);
		assertEquals(rssEntries.get(0).getAuthor(), "author");
		assertEquals(rssEntries.get(0).getTitle(), "title");
		assertEquals(rssEntries.get(0).getDescription(), "description");
		
		rssEntries = rssEntryDao.findAll(10);
		assertEquals(rssEntries.size(), 1);
		assertEquals(rssEntries.get(0).getId(), 1);
		assertEquals(rssEntries.get(0).getAuthor(), "author");
		assertEquals(rssEntries.get(0).getTitle(), "title");
		assertEquals(rssEntries.get(0).getDescription(), "description");
	}

}
