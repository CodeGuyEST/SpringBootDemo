package georgmargus.springboot.demo;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
	
	@Value("${rss.url}")
	private String rssFeedUrl;
	
	@Bean
	public SyndFeed syndFeed() throws FeedException, IOException {
		return new SyndFeedInput().build(new XmlReader(new URL(rssFeedUrl)));
	}

}
