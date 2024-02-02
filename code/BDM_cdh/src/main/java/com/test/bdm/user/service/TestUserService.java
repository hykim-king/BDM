package com.test.bdm.user.service;

import java.sql.SQLException;

import com.test.bdm.user.domain.UserVO;

public class TestUserService extends UserServiceImpl {

	private String id;

	public TestUserService(String id) {
		super();
		this.id = id;
	}
	
	
}
