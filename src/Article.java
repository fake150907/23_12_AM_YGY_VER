import java.time.LocalDateTime;

class Article {
	private int id;
	private String title;
	private String body;
	private LocalDateTime regDate;


	public Article(int id, String title, String body, LocalDateTime regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}