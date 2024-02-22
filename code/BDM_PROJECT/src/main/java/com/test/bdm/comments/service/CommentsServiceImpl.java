package com.test.bdm.comments.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.test.bdm.cmn.DTO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.comments.dao.CommentsDao;
import com.test.bdm.comments.domain.CommentsVO;

@Service("CommentsServiceImpl")
public class CommentsServiceImpl implements CommentsService, PcwkLogger {

	@Autowired
	CommentsDao dao;
	
	public CommentsServiceImpl() {}
	
	@Override
	public int getRegNo() throws SQLException {
		return dao.getRegNo();
	}

	@Override
	public int doUpdate(CommentsVO inVO) throws SQLException {
		return dao.doUpdate(inVO);
	}

	@Override
	public int doDelete(CommentsVO inVO) throws SQLException {
		return dao.doDelete(inVO);
	}

	@Override
	public CommentsVO doSelectOne(CommentsVO inVO) throws SQLException, EmptyResultDataAccessException {
		return dao.doSelectOne(inVO);
	}

	@Override
	public int doSave(CommentsVO inVO) throws SQLException {
		return dao.doSave(inVO);
	}

	
	 @Override 
	 public List<CommentsVO> doRetrieve(CommentsVO inVO) throws SQLException { 
		 return dao.doRetrieve(inVO); 
	 }
}
