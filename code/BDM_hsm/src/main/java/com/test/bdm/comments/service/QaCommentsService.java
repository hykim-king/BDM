package com.test.bdm.comments.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.test.bdm.comments.domain.QaCommentsVO;


public interface QaCommentsService {
	
	
	public int getRegNo() throws SQLException;
	
	
	int doUpdate(QaCommentsVO inVO) throws SQLException;
	
	
	int doDelete(QaCommentsVO inVO) throws SQLException;
	
	QaCommentsVO doSelectOne(QaCommentsVO inVO) throws SQLException, EmptyResultDataAccessException;
	
	
	int doSave(QaCommentsVO inVO) throws SQLException;
	
	
	List<QaCommentsVO> doRetrieve(QaCommentsVO inVO) throws SQLException;
		
}
