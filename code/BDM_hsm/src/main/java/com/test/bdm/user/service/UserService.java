package com.test.bdm.user.service;

import java.sql.SQLException;

import com.test.bdm.user.domain.UserVO;

public interface UserService {
	
	/**
	 * 회원 정보 저장
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	public int doSave(UserVO inVO) throws SQLException;

}
