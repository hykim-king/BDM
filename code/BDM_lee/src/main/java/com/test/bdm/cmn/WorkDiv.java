package com.test.bdm.cmn;

import java.sql.SQLException;
import java.util.List;

/**
 * 모든 dao 인터페이스는 WorkDiv를 상속받으시오. 룰
 * @author lik05
 *
 * @param <T>
 */
public interface WorkDiv<T> {
	
	//표준화
	
	/**
	 * 삭제
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doDelete(T inVO) throws SQLException;
	
	/**
	 * 저장
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doSave(T inVO) throws SQLException;
	
	/**
	 * 수정
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doUpdate(T inVO) throws SQLException;
	
	/**
	 * 단건조회
	 * @param inVO
	 * @return T
	 * @throws SQLException
	 * @throws EmptyResultDataAccessException
	 */
	T doSelectOne(T inVO) throws SQLException;
	
	/**
	 * 목록 조회
	 * @param inVO
	 * @return List<T>
	 * @throws SQLException
	 */
	List<T> doRetrieve(T inVO) throws SQLException;

}
