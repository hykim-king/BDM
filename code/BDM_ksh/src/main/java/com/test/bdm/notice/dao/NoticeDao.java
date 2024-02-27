package com.test.bdm.notice.dao;

import java.sql.SQLException;
import java.util.List;

import com.test.bdm.cmn.DTO;
import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.notice.domain.NoticeVO;

public interface NoticeDao extends WorkDiv<NoticeVO> {
	
	int getNoticeSeq()throws SQLException;
	
	int doDeleteAll(NoticeVO inVO)throws SQLException;
	
	int updateReadCnt(NoticeVO inVO)throws SQLException;
	
	List<NoticeVO> doRetrieve(DTO inVO)throws SQLException;
}
