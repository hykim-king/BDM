package com.test.bdm.comments.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.test.bdm.comments.domain.CommentsVO;


public interface CommentsService {
	
	
	public int getRegNo() throws SQLException;
	
	int doUpdate(CommentsVO inVO) throws SQLException;
	
	int doDelete(CommentsVO inVO) throws SQLException;
	
	CommentsVO doSelectOne(CommentsVO inVO) throws SQLException, EmptyResultDataAccessException;
	
	int doSave(CommentsVO inVO) throws SQLException;
	
	List<CommentsVO> doRetrieve(CommentsVO inVO) throws SQLException;
		
}
