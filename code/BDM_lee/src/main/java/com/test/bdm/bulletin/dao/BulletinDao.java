package com.test.bdm.bulletin.dao;

import java.sql.SQLException;

import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.cmn.WorkDiv;

public interface BulletinDao extends WorkDiv<BulletinVO> {
	
	int getBulletinSeq()throws SQLException;
	
	int doDeleteAll(BulletinVO inVO)throws SQLException;
	
	int updateReadCnt(BulletinVO inVO)throws SQLException;
}
