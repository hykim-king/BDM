package com.test.bdm.comments.dao;

import java.sql.SQLException;
import java.util.List;

import com.test.bdm.cmn.DTO;
import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.comments.domain.CommentsVO;

public interface CommentsDao extends WorkDiv<CommentsVO> {

	public int getRegNo() throws SQLException;
	
	List<CommentsVO> doRetrieve(DTO inVO) throws SQLException;
}
