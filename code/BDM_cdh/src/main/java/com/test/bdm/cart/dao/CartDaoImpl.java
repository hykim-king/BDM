package com.test.bdm.cart.dao;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.bdm.user.domain.UserVO;

@Repository
public class CartDaoImpl implements CartDao {
	
	final String NAMESPACE = "com.test.bdm.cart";
	final String DOT = ".";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public CartDaoImpl() {}
	
	UserVO doFindUser(UserVO inVO) throws SQLException {
		return sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doSelectOne", inVO);
	}
	
	@Override
	public String findUser(HttpServletRequest request) {
		String id = "";
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {
			id = session.getId();
		}
		else id = "세션 없음";
		
		return id;
	}

}
