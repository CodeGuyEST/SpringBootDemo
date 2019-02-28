package georgmargus.springboot.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RssEntryDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insert(RssEntry rssEntry) {
		int rows_inserted = 0;
		try {
		    String query = "INSERT INTO rss_entries(author, title, description) VALUES (?, ?, ?)";
		    rows_inserted = jdbcTemplate.update(query, rssEntry.getAuthor(), rssEntry.getTitle(), rssEntry.getDescription());
		}
		catch(DuplicateKeyException e) {
			System.out.println("Unique constraint violated");
		}
		return rows_inserted;
	}
	
	public List<RssEntry> findAll() {
		String query = "SELECT * FROM rss_entries ORDER BY id";
		return jdbcTemplate.query(query, new Object[] {}, new RowMapper<RssEntry>(){
			public RssEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
				long id = rs.getInt("id");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String description = rs.getString("description");
	            RssEntry rss = new RssEntry(id, author, title, description);
	            return rss;
	        }
	    });
	}
	
	public List<RssEntry> findAll(int count) {
		String query = "SELECT * FROM rss_entries ORDER BY id LIMIT " + Integer.toString(count);
		return jdbcTemplate.query(query, new Object[] {}, new RowMapper<RssEntry>(){
			public RssEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
				long id = rs.getInt("id");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String description = rs.getString("description");
	            RssEntry rss = new RssEntry(id, author, title, description);
	            return rss;
	        }
	    });
	}
	
}
