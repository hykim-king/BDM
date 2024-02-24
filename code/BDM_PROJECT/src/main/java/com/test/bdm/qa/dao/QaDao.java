package com.test.bdm.qa.dao;

import java.sql.SQLException;
import java.util.List;

import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.qa.domain.QaVO;

public interface QaDao extends WorkDiv<QaVO> {
	
	int getQaSeq() throws SQLException;
	
	int doDeleteAll(QaVO inVO) throws SQLException;

	List<QaVO> doRetrieve(QaVO inVO) throws SQLException;
<<<<<<< HEAD
	
	int getDisclosure(QaVO inVO) throws SQLException;
=======
>>>>>>> e7dbeee0230a4f8c7dc29d9d7833fb5b3dd3ea15
}
