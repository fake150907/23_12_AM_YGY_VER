
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static List<Article> articles = new ArrayList<>();
	private static List<Member> members = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 == ");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 3;
		int lastMemberId = 0;
		while (true) {
			System.out.print("명령어 > ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력하세요. 주인님.");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}
			if (cmd.equals("article write")) {
				System.out.println("==게시글 작성==");
				int id = lastArticleId + 1;
				String regDate = Util.getNowDate_TimeStr();
				String updateDate = regDate;
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, updateDate, title, body);
				articles.add(article);

				System.out.printf("%d번 글이 생성 되었습니다. 주인님.\n", id);
				lastArticleId++;
			} else if (cmd.equals("member join")) {
				System.out.println("==회원 가입==");
				int id = lastMemberId + 1;
				String regDate = Util.getNowDate_TimeStr();
				System.out.print("loginId : ");
				String loginId = sc.nextLine().trim();

				List<Member> foundloginId = members;

				while (loginId.length() == 0 || loginId.contains(" ")) {
					System.out.println("아이디를 제대로 입력해주세요. 주인님.");
					System.out.print("loginId : ");
					loginId = sc.nextLine().trim();
				}

				if (loginId.length() > 0) {
					foundloginId = new ArrayList<>();

					for (Member foundmember : members) {
						while (foundmember.getLoginId().equals(loginId) || loginId.contains(" ")) {
							System.out.println("중복된 아이디는 사용할 수 없습니다. 다시 입력해주세요 주인님.");
							System.out.print("loginId : ");
							loginId = sc.nextLine().trim();
						}
					}
					if (foundloginId.size() == 0) {
						System.out.println("사용 가능한 아이디 입니다.");
					}
				}

				System.out.print("loginPw : ");
				String loginPw = sc.nextLine();

				while (loginPw.length() == 0 || loginPw.contains(" ")) {
					System.out.println("비밀번호를 제대로 입력해주세요. 주인님.");
					System.out.print("loginId : ");
					loginPw = sc.nextLine().trim();
				}

				System.out.print("loginPw확인 : ");
				String loginPwCheck = sc.nextLine();

				while (!loginPw.equals(loginPwCheck)) {
					System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요. 주인님");
					System.out.print("loginPw확인 : ");
					loginPwCheck = sc.nextLine();
				}

				System.out.print("이름 : ");
				String name = sc.nextLine();

				while (name.length() == 0 || name.contains(" ")) {
					System.out.println("이름을 제대로 입력해주세요. 주인님.");
					System.out.print("이름 : ");
					name = sc.nextLine().trim();
				}

				Member member = new Member(id, regDate, loginId, loginPw, name);
				members.add(member);

				System.out.printf("회원가입이 완료되었습니다. 주인님.\n", id);
				lastMemberId++;

			} else if (cmd.startsWith("article list")) {
				System.out.println("==게시글 목록==");
				if (articles.size() == 0) {
					System.out.println("아무것도 없어");
					continue;
				}

				String searchKeyword = cmd.substring("article list".length()).trim();

				List<Article> forPrintArticles = articles;

				if (searchKeyword.length() > 0) {
					System.out.println("검색어 : " + searchKeyword);
					forPrintArticles = new ArrayList<>();

					for (Article article : articles) {
						if (article.getTitle().contains(searchKeyword)) {
							forPrintArticles.add(article);
						}
					}
					if (forPrintArticles.size() == 0) {
						System.out.println("  번호  /  제목    /   작성일     /   조회");
						System.out.println("검색 결과 없음");
						continue;
					}
				}

				System.out.println("  번호  /  제목    /   작성일     /   조회");
				for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
					Article article = forPrintArticles.get(i);
					if (Util.getNowDate_TimeStr().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
						System.out.printf("  %4d  /   %s    /     %s   /   %d\n", article.getId(), article.getTitle(),
								article.getRegDate().split(" ")[1], article.getHit());
					} else {
						System.out.printf("  %4d  /   %s    /     %s   /   %d\n", article.getId(), article.getTitle(),
								article.getRegDate().split(" ")[0], article.getHit());
					}

				}

			} else if (cmd.startsWith("article detail")) {

				String[] cmdDiv = cmd.split(" ");

				int id = 0;

				try {
					id = Integer.parseInt(cmdDiv[2]);
				} catch (Exception e) {
					System.out.println("번호는 정수로 입력해주세요. 주인님");
					continue;
				}

				Article foundArticle = getArticleById(id);

				System.out.println("번호 : " + foundArticle.getId());
				System.out.println("작성 날짜 : " + foundArticle.getRegDate());
				System.out.println("수정 날짜 : " + foundArticle.getUpdateDate());
				System.out.println("제목 : " + foundArticle.getTitle());
				System.out.println("내용 : " + foundArticle.getBody());
				System.out.println("조회 : " + foundArticle.getHit());

				foundArticle.setHit(foundArticle.getHit() + 1);

			} else if (cmd.startsWith("article delete")) {

				String[] cmdDiv = cmd.split(" ");

				int id = 0;

				try {
					id = Integer.parseInt(cmdDiv[2]);
				} catch (Exception e) {
					System.out.println("번호는 정수로 입력해주세요.주인님.");
					continue;
				}

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시글은 없습니다. 주인님.\n", id);
					continue;
				}
				articles.remove(foundArticle);
				System.out.println(id + "번 글이 삭제되었습니다. 주인님.");

			} else if (cmd.startsWith("article modify")) {

				String[] cmdDiv = cmd.split(" ");

				int id = 0;

				try {
					id = Integer.parseInt(cmdDiv[2]);
				} catch (Exception e) {
					System.out.println("번호는 정수로 입력해주세요.주인님.");
					continue;
				}

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시글은 없습니다. 주인님.\n", id);
					continue;
				}

				System.out.println("기존 제목 : " + foundArticle.getTitle());
				System.out.println("기존 내용 : " + foundArticle.getBody());
				System.out.print("새 제목 : ");
				String newTitle = sc.nextLine();
				System.out.print("새 내용 : ");
				String newBody = sc.nextLine();

				foundArticle.setUpdateDate(Util.getNowDate_TimeStr());
				foundArticle.setTitle(newTitle);
				foundArticle.setBody(newBody);
				System.out.println(id + "번 글이 수정되었습니다. 주인님.");
			} else {
				System.out.println("사용할 수 없는 명령어입니다. 주인님.");
			}
		}

		System.out.println("== 프로그램 끝 == ");

		sc.close();

	}

	private static Article getArticleById(int id) {
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	private static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성하겠습니다.");

		articles.add(new Article(1, "2024-01-01 12:12:12", Util.getNowDate_TimeStr(), "고양이", "귀여워", 10));
		articles.add(new Article(2, "2023-12-12 12:12:12", Util.getNowDate_TimeStr(), "응가", "뿌직뿌직", 20));
		articles.add(new Article(3, Util.getNowDate_TimeStr(), Util.getNowDate_TimeStr(), "수달", "초초귀여워", 30));
	}
}