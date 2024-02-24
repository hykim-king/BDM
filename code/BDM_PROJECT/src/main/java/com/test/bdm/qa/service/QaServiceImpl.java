package com.test.bdm.qa.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.qa.dao.QaDao;
import com.test.bdm.qa.domain.QaVO;

@Service("QaServiceImpl")
public class QaServiceImpl implements QaService, PcwkLogger {
	
	@Autowired
	QaDao dao;
	
	public QaServiceImpl() {}
	
	@Override
	public int getQaSeq() throws SQLException {
		return dao.getQaSeq();
	}

	@Override
	public int doDelete(QaVO inVO) throws SQLException {
		return dao.doDelete(inVO);
	}

	@Override
	public QaVO doSelectOne(QaVO inVO) throws SQLException, EmptyResultDataAccessException {
		QaVO outVO = dao.doSelectOne(inVO);
		return outVO;
	}

	@Override
	public int doSave(QaVO inVO) throws SQLException {
		return dao.doSave(inVO);
	}

	@Override
	public List<QaVO> doRetrieve(QaVO inVO) throws SQLException {
		return dao.doRetrieve(inVO);
	}

}
