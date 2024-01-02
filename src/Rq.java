import java.util.List;

public class Rq {
	private Article foundArticle;
	private int id;
	private int foundIndex = -1;
	
	public Rq(String cmd, List<Article> articles) {
		String[] cmdDiv = cmd.split(" ");
		
		try {
			id = Integer.parseInt(cmdDiv[2]);
		} catch (Exception e) {
			System.out.println("메뉴얼대로 움직여라 인간.");
			return;
		}
		
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.getId() == id) {
				foundArticle = article;
				foundIndex = i;
			}
		}
		
		if (foundIndex == -1) {
			System.out.printf("%d번 게시글은 없습니다. 주인님.\n", id);
			return;
		}
	}

	public int getFoundIndex() {
		return foundIndex;
	}

	public int getId() {
		return id;
	}

	public Article getFoundArticle() {
		return foundArticle;
	}

}
