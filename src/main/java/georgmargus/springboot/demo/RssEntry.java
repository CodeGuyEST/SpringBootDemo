package georgmargus.springboot.demo;

public class RssEntry {
	
	private long id;
	private String author;
	private String title;
	private String description;
	
	public RssEntry(long id, String author, String title, String description) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.description = description;
	}
	
	public RssEntry(String author, String title, String description) {
		this.author = author;
		this.title = title;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
