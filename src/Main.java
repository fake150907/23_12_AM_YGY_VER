import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 == ");

		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;
		Article content;
		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.print("명령어 > ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			} else if (cmd.equals("article write")) {
				int id = lastArticleId + 1;
				String regDate = Util.getNowDate_TimeStr();
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();

				System.out.printf("%d번 글이 생성 되었습니다.\n", id);

				content = new Article(id, regDate, title, body);
				lastArticleId++;

				articles.add(content);
			} else if (cmd.equals("article list")) {

				System.out.println("번호  |  제목  ");

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d  |  %s  \n", article.getId(), article.getTitle());
				}

			} else if (cmd.startsWith("article detail")) {
				Rq rq = new Rq(cmd, articles);
				Article foundArticle = rq.getFoundArticle();

				if (foundArticle != null) {
					System.out.printf("== article detail %d ==\n", foundArticle.getId());
					System.out.println("번호 : " + foundArticle.getId());
					System.out.println("날짜 : " + foundArticle.getRegDate());
					System.out.println("제목 : " + foundArticle.getTitle());
					System.out.println("내용 : " + foundArticle.getBody());
				}

			} else if (cmd.startsWith("article delete")) {
				Rq rq = new Rq(cmd, articles);
				Article foundArticle = rq.getFoundArticle();

				if (foundArticle != null) {
					for (int i = 0; i < articles.size(); i++) {
						if (foundArticle.getId() == articles.get(i).getId()) {
							articles.remove(i);
							System.out.printf("%d번 게시글이 삭제되었습니다. 주인님.\n", rq.getId());
							break;
						}
					}
				}

			} else {
				System.out.println("명령을 똑바로 내려주세요 주인님.");
			}
		}
		System.out.println("== 프로그램 끝 == ");

		sc.close();

	}
}