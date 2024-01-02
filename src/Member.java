
public class Member {
	private int id;
	private String regDate;
	private String loginId;
	private String loginPw;
	private String name;

	public Member(int id, String regDate, String logind, String loginPw, String name) {
		this.id = id;
		this.regDate = regDate;
		this.loginId = logind;
		this.loginPw = loginPw;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String logind) {
		this.loginId = logind;
	}

	public String getLoginPw() {
		return loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
