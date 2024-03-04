package com.test.bdm.comments.dao;

import java.sql.SQLException;
import java.util.List;

import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.comments.domain.CommentsVO;
import com.test.bdm.comments.domain.QaCommentsVO;

public interface CommentsDao extends WorkDiv<CommentsVO> {

	public int getRegNo() throws SQLException;
	
	List<CommentsVO> doRetrieve(CommentsVO inVO) throws SQLException;

	int doUpdate(CommentsVO inVO) throws SQLException;
	
	int commentsCount(int postNo) throws SQLException;

}
