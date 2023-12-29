import java.time.LocalDateTime;
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
		LocalDateTime regDate = null;
		while (true) {
			System.out.print("명령어 > ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			} else if (cmd.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();

				System.out.printf("%d번 글이 생성 되었습니다.\n", id);

				content = new Article(id, title, body, regDate.now());
				lastArticleId++;

				articles.add(content);
			} else if (cmd.equals("article list")) {

				System.out.println("번호  |  제목  ");

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d  |  %s  \n", article.getId(), article.getTitle());
				}

			} else if (cmd.startsWith("article detail")) {
				int id = -1;
				try {
					id = Integer.parseInt(cmd.substring(14).trim());
				} catch (NumberFormatException e) {
					System.out.println("번호는 정수를 입력해주세요.");
					continue;
				}
				if (id > articles.size() || id == 0) {
					System.out.printf("%d번 게시글은 없습니다. 주인님.\n", id);
				}
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.getId() == id) {
						System.out.printf("== article detail %d ==\n", id);
						System.out.println("번호 : " + article.getId());
						System.out.println("날짜 : " + article.getRegDate());
						System.out.println("제목 : " + article.getTitle());
						System.out.println("내용 : " + article.getBody());
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