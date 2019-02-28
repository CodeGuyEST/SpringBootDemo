package georgmargus.springboot.demo;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "scheduling.enabled=false")
@AutoConfigureMockMvc
public class RssEntryControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private RssEntryDao rssEntryDao;

	@Test
	@DirtiesContext
	public void testAll() throws Exception {
		String resultJson = "[{\"id\":1,\"author\":\"author_1\",\"title\":\"title_1\","
				          + "\"description\":\"description_1\"},"
				          + "{\"id\":2,\"author\":\"author_2\",\"title\":\"title_2\","
				          + "\"description\":\"description_2\"}]";
		rssEntryDao.insert(new RssEntry(1, "author_1", "title_1", "description_1"));
		rssEntryDao.insert(new RssEntry(2, "author_2", "title_2", "description_2"));
		MvcResult result = mockMvc.perform(get("/"))
		    .andExpect(status().isOk())
		    .andExpect(content().contentType("application/json;charset=UTF-8"))
		    .andReturn();
		assertEquals(result.getResponse().getContentAsString(), resultJson);
	}
	
	@Test
	@DirtiesContext
	public void testAllCount1() throws Exception {
		String resultJson = "[{\"id\":1,\"author\":\"author_1\",\"title\":\"title_1\","
		                  + "\"description\":\"description_1\"}]";
		rssEntryDao.insert(new RssEntry(1, "author_1", "title_1", "description_1"));
		rssEntryDao.insert(new RssEntry(2, "author_2", "title_2", "description_2"));
		rssEntryDao.insert(new RssEntry(3, "author_3", "title_3", "description_3"));
		MvcResult result = mockMvc.perform(get("/?count=1"))
		    .andExpect(status().isOk())
		    .andExpect(content().contentType("application/json;charset=UTF-8"))
		    .andReturn();
		assertEquals(result.getResponse().getContentAsString(), resultJson);
	}

}
