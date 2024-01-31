package com.test.bdm.cart.dao;

import javax.servlet.http.HttpServletRequest;

public interface CartDao {
	
	String findUser(HttpServletRequest request);
}
