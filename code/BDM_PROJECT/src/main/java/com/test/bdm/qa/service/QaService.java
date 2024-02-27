package com.test.bdm.qa.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.test.bdm.qa.dao.QaDao;
import com.test.bdm.qa.domain.QaVO;

public interface QaService {
	
	public int getQaSeq() throws SQLException;
	
	public int doDelete(QaVO inVO) throws SQLException;
	
	public QaVO doSelectOne(QaVO inVO) throws SQLException, EmptyResultDataAccessException;
	
	public int doSave(QaVO inVO) throws SQLException;
	
	public List<QaVO> doRetrieve(QaVO inVO) throws SQLException;
	
	int getDisclosure(QaVO inVO) throws SQLException;
	
}
