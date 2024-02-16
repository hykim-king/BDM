package com.test.bdm.user.service;

import java.sql.SQLException;

import com.test.bdm.user.domain.UserVO;

public interface UserService {
	
	/**
	 * 아이디 중복 체크
	 * @param inVO
	 * @return
	 * @throws SQLException
	 */
	public int idDuplicateCheck(UserVO inVO) throws SQLException;
	
	
	/**
	 * 회원정보 저장
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	public int doSave(UserVO inVO) throws SQLException;
	
	
	
}
