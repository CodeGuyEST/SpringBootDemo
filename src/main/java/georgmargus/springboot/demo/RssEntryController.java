package georgmargus.springboot.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RssEntryController {
	
	@Autowired
	private RssEntryDao rssEntryDao;
	
	@GetMapping("/")
	List<RssEntry> all(@RequestParam(value = "count", required = false) Integer count) {
		if(count == null) {
			return rssEntryDao.findAll();
		}
		return rssEntryDao.findAll(count);
	}

}
