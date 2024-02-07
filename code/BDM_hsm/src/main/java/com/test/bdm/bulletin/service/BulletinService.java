package com.test.bdm.bulletin.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.test.bdm.bulletin.domain.BulletinVO;

public interface BulletinService {
	
	int doSave(BulletinVO inVO) throws SQLException;
	
	int doDeleteAll(BulletinVO inVO) throws SQLException;
	
	/**
	 * 단 건 조회, 조회 Count 증가
	 * @param inVO
	 * @return BulletinVO
	 * @throws SQLException
	 * @throws EmptyResultDataAccessException
	 */
	BulletinVO doSelectOne(BulletinVO inVO) throws SQLException, EmptyResultDataAccessException;
	
	int doUpdate(BulletinVO inVO) throws SQLException;
	
	int doDelete(BulletinVO inVO) throws SQLException;
	
	List<BulletinVO> doRetrieve(BulletinVO inVO) throws SQLException;
	
	BulletinVO bulletinView(BulletinVO inVO) throws SQLException, EmptyResultDataAccessException;

}
