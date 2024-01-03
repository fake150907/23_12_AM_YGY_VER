package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.MemberController;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class App {
	private List<Article> articles;
	private List<Member> members;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		System.out.println("== 프로그램 시작 == ");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, articles);

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
			if (cmd.equals("member join")) {
				memberController.doJoin();

			} else if (cmd.equals("article write")) {
				articleController.write();

			} else if (cmd.startsWith("article list")) {
				articleController.list(cmd);

			} else if (cmd.startsWith("article detail")) {
				articleController.detail(cmd);

			} else if (cmd.startsWith("article delete")) {
				articleController.delete(cmd);

			} else if (cmd.startsWith("article modify")) {
				articleController.modify(cmd);

			} else {
				System.out.println("사용할 수 없는 명령어입니다. 주인님.");
			}
		}

		System.out.println("== 프로그램 끝 == ");

		sc.close();

	}

	private void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성하겠습니다.");

		articles.add(new Article(1, "2024-01-01 12:12:12", Util.getNowDate_TimeStr(), "고양이", "귀여워", 10));
		articles.add(new Article(2, "2023-12-12 12:12:12", Util.getNowDate_TimeStr(), "응가", "뿌직뿌직", 20));
		articles.add(new Article(3, Util.getNowDate_TimeStr(), Util.getNowDate_TimeStr(), "수달", "초초귀여워", 30));
	}
}
