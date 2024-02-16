package com.test.bdm.comments.dao;

import java.sql.SQLException;

import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.comments.domain.CommentsVO;

public interface CommentsDao extends WorkDiv<CommentsVO> {

	public int getRegNo() throws SQLException;
	

	
	
}
