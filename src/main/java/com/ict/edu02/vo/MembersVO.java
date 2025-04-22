package com.ict.edu02.vo;

public class MembersVO {
	private String userIdx, userId, userPw, userName, userAge, userReg;

	public MembersVO() {}
	
	public MembersVO(String userId, String userPw, String userName, String userAge) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userAge = userAge;
	}

	public String getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(String userIdx) {
		this.userIdx = userIdx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserReg() {
		return userReg;
	}

	public void setUserReg(String userReg) {
		this.userReg = userReg;
	}
	
}
