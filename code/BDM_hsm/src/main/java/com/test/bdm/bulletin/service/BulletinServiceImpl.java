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
public class BulletinServiceImpl implements PcwkLogger, BulletinService {
	
	@Autowired
	BulletinDao dao;
	
	public BulletinServiceImpl() {}

	@Override
	public int doSave(BulletinVO inVO) throws SQLException {
		return dao.doSave(inVO);
	}

	@Override
	public int doDeleteAll(BulletinVO inVO) throws SQLException {
		return dao.doDeleteAll(inVO);
	}

	@Override
	public BulletinVO doSelectOne(BulletinVO inVO) throws SQLException, EmptyResultDataAccessException {
		BulletinVO outVO = dao.doSelectOne(inVO);
		
		if(null != outVO) {
			int updateReadCnt = dao.updateReadCnt(inVO);
			LOG.debug("──────────────────────────────────────────");
			LOG.debug(" updateReadCnt: " + updateReadCnt          );
			LOG.debug("──────────────────────────────────────────");
		}
		
		return outVO;
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
	public List<BulletinVO> doRetrieve(BulletinVO inVO) throws SQLException {
		return dao.doRetrieve(inVO);
	}

	@Override
	public BulletinVO bulletinView(BulletinVO inVO) throws SQLException, EmptyResultDataAccessException {
		BulletinVO outVO = dao.bulletinView(inVO);
		
		if(null != outVO) {
			int updateReadCnt = dao.updateReadCnt(inVO);
			LOG.debug("──────────────────────────────────────────");
			LOG.debug(" updateReadCnt: " + updateReadCnt          );
			LOG.debug("──────────────────────────────────────────");
		}
		
		return outVO;
	}
}
