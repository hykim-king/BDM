package com.bdm.ehr.anno.domain;

import com.bdm.ehr.cmn.DTO;

public class AnnoVO extends DTO {

	private String userId;//아이디
	private String password;//비번
	
	public AnnoVO() {}

	public AnnoVO(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AnnoVO [userId=" + userId + ", password=" + password + "]";
	}
	
	
	
}
