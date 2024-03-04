package com.test.bdm.comments.dao;

import java.sql.SQLException;
import java.util.List;

import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.comments.domain.CommentsVO;
import com.test.bdm.comments.domain.QaCommentsVO;

public interface QaCommentsDao extends WorkDiv<QaCommentsVO> {

	public int getRegNo() throws SQLException;
	
	List<QaCommentsVO> doRetrieve(QaCommentsVO inVO) throws SQLException;
	
	int doUpdate(QaCommentsVO inVO) throws SQLException;
}
