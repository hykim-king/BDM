package com.test.bdm.cmn;

import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;

/**
 * 모든 DAO 인터페이스는 WorkDiv 를 상속받는다.
 * @author user
 *
 * @param <T>
 */
public interface WorkDiv<T> {

	/**
	 * 저장
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doSave(T inVO) throws SQLException;
	
	
	/**
	 * 단 건 조회
	 * @param inVO
	 * @return T
	 * @throws SQLException
	 * @throws EmptyResultDataAccessException
	 */
	T doSelectOne(T inVO) throws SQLException, EmptyResultDataAccessException;
}
