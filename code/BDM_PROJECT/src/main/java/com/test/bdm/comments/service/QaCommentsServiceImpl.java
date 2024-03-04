package com.test.bdm.comments.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.comments.dao.QaCommentsDao;
import com.test.bdm.comments.domain.QaCommentsVO;

@Service
public class QaCommentsServiceImpl implements QaCommentsService, PcwkLogger {

	@Autowired
	QaCommentsDao dao;
	
	public QaCommentsServiceImpl() {}
	
	@Override
	public int getRegNo() throws SQLException {
		return dao.getRegNo();
	}

	@Override
	public int doUpdate(QaCommentsVO inVO) throws SQLException {
		return dao.doUpdate(inVO);
	}

	@Override
	public int doDelete(QaCommentsVO inVO) throws SQLException {
		return dao.doDelete(inVO);
	}

	@Override
	public QaCommentsVO doSelectOne(QaCommentsVO inVO) throws SQLException, EmptyResultDataAccessException {
		return dao.doSelectOne(inVO);
	}

	@Override
	public int doSave(QaCommentsVO inVO) throws SQLException {
		return dao.doSave(inVO);
	}

	@Override
	public List<QaCommentsVO> doRetrieve(QaCommentsVO inVO) throws SQLException {
		return dao.doRetrieve(inVO);
	}


}
