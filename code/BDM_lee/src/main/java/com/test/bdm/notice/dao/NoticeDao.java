package com.test.bdm.notice.dao;

import java.sql.SQLException;

import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.notice.domain.NoticeVO;

public interface NoticeDao extends WorkDiv<NoticeVO> {
	
	int getNoticeSeq()throws SQLException;
	
	int doDeleteAll(NoticeVO inVO)throws SQLException;
	
	int updateReadCnt(NoticeVO inVO)throws SQLException;
	

}
