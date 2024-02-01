package com.test.bdm.bulletin.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.test.bdm.bulletin.dao.BulletinDao;
import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.cmn.PcwkLogger;

@Service
public class BulletinServiceImpl implements BulletinService,PcwkLogger {

	@Autowired
	BulletinDao dao;
	
	public BulletinServiceImpl() {}
	
	@Override
	public int getBulletinSeq() throws SQLException {
		return dao.getBulletinSeq();
	}

	@Override
	public int doUpdate(BulletinVO inVO) throws SQLException {
		return dao.doUpdate(inVO);
	}

	@Override
	public int doDelete(BulletinVO inVO) throws SQLException {
		return dao.doDelete(inVO);
	}

	@Override
	public BulletinVO doSelectOne(BulletinVO inVO) throws SQLException, EmptyResultDataAccessException {
		BulletinVO outVO = dao.doSelectOne(inVO);
		
		if(null != outVO) {
			int updateReadCnt = dao.updateReadCnt(inVO);
		}
	
		return outVO;
	}

	@Override
	public int doSave(BulletinVO inVO) throws SQLException {
		return dao.doSave(inVO);
	}

	@Override
	public List<BulletinVO> doRetrieve(BulletinVO inVO) throws SQLException {
		return dao.doRetrieve(inVO);
	}

}
