package com.test.bdm.qa.dao;

import java.sql.SQLException;

import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.qa.domain.QaVO;

public interface QaDao extends WorkDiv<QaVO> {
	
	int getQaSeq() throws SQLException;
	
	int doDeleteAll(QaVO inVO) throws SQLException;

}
