package com.KoreaIT.java.AM;

import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.Controller;
import com.KoreaIT.java.AM.controller.MemberController;

public class App {

	public App() {
	}

	public void run() {
		System.out.println("== 프로그램 시작 == ");

		Scanner sc = new Scanner(System.in);

		Controller controller = null;

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestArticleData();
		memberController.makeTestMemberData();

		while (true) {
			System.out.print("명령어 > ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력하세요. 주인님.");
				continue;
			}
			if (cmd.equals("exit")) {
				System.out.println("== 프로그램을 종료합니다. == ");
				break;
			}
			String[] cmdBits = cmd.split(" ");

			String controllerName = cmdBits[0];

			if (cmdBits.length == 1) {
				System.out.println("명령을 똑바로 내려주세요. 주인님.");
				continue;
			}

			String actionMethodName = cmdBits[1];

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("사용할 수 없는 명령어 입니다. 주인님.");
				continue;
			}

			controller.doAction(actionMethodName, cmd);
		}

		sc.close();

	}
}
