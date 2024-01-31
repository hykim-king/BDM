package com.test.bdm.bulletin.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.test.bdm.bulletin.domain.BulletinVO;

public interface BulletinService {
	
	public int getBulletinSeq()throws SQLException;
	
	public int doUpdate(BulletinVO inVO)throws SQLException;
	
	public int doDelete(BulletinVO inVO)throws SQLException;
	
	public BulletinVO doSelectOne(BulletinVO inVO) throws SQLException,EmptyResultDataAccessException;
	
	public int doSave(BulletinVO inVO) throws SQLException;
	
	public List<BulletinVO> doRetrieve(BulletinVO inVO)throws SQLException;

}
