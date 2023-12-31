package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class MemberController extends Controller {
	private List<Member> members;
	private Scanner sc;
	private String cmd;
	private String actionMethodName;

	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.sc = sc;
	}

	public MemberController() {

	}

	private int lastMemberId = 3;

	public void doAction(String actionMethodName, String cmd) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			if (isLogined()) {
				System.out.println("이미 로그인 상태입니다. 주인님.");
				return;
			}
			doLogin();
			break;
		case "logout":
			if (!isLogined()) {
				System.out.println("이미 로그아웃 상태입니다. 주인님");
				return;
			}
			doLogout();
			break;
		default:
			System.out.println("잘못된 명령입니다. 주인님");
			break;
		}
	}

	private void doLogout() {

		loginedMember = null;

		System.out.println("로그아웃되었습니다. 주인님.");
	}

	private void doLogin() {
		System.out.println("== 로그인 ==");
		System.out.print("login id : ");
		String loginId = sc.nextLine().trim();
		System.out.print("login pw : ");
		String loginPw = sc.nextLine().trim();
		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("일치하는 회원이 없습니다. 다시 입력해주세요. 주인님.");
			return;
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			return;
		}

		this.loginedMember = member;

		System.out.printf("로그인 성공! %s님 반갑습니다.\n", loginedMember.getName());

	}

	private void doJoin() {
		System.out.println("==회원 가입==");
		int id = lastMemberId + 1;
		String regDate = Util.getNowDate_TimeStr();
		String loginId = null;
		while (true) {
			System.out.print("로그인 아이디 : ");
			loginId = sc.nextLine();
			if (isJoinableLoginId(loginId) == false) {
				System.out.println("이미 사용중입니다 주인님 다시 입력해주세요.");
				continue;
			}
			break;
		}
		String loginPw = null;
		while (true) {
			System.out.print("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.print("로그인 비밀번호 확인 : ");
			String loginPwConfirm = sc.nextLine();
			if (!loginPw.equals(loginPwConfirm)) {
				System.out.println("비밀번호가 일치하지 않아. 다시 입력해주세요.");
				continue;
			}
			break;
		}
		System.out.print("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%d번 회원이 가입 되었습니다. %s님 환영합니다.\n", id, name);
		lastMemberId++;
	}

	private Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.getLoginId().equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	private boolean isJoinableLoginId(String loginId) {
		for (Member member : members) {
			if (member.getLoginId().equals(loginId)) {
				return false;
			}
		}

		return true;
	}

	public List<Member> getTestMemberData() {
		return members;
	}

	public void makeTestMemberData() {
		System.out.println("테스트를 위한 회원 데이터를 생성하겠습니다.");
		members.add(new Member(1, Util.getNowDate_TimeStr(), "qwe", "qwe", "고양이"));
		members.add(new Member(2, Util.getNowDate_TimeStr(), "asd", "asd", "수달"));
		members.add(new Member(3, Util.getNowDate_TimeStr(), "zxc", "zxc", "강아지"));
	}
}