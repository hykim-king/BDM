package com.test.bdm.notice.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.notice.dao.NoticeDao;
import com.test.bdm.notice.domain.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService, PcwkLogger {
	
	@Autowired
	NoticeDao dao;
	
	public NoticeServiceImpl() {}

	@Override
	public int getNoticeSeq() throws SQLException {
		// TODO Auto-generated method stub
		return dao.getNoticeSeq();
	}

	@Override
	public int doUpdate(NoticeVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return dao.doUpdate(inVO);
	}

	@Override
	public int doDelete(NoticeVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return dao.doDelete(inVO);
	}

	@Override
	public NoticeVO doSelectOne(NoticeVO inVO) throws SQLException, EmptyResultDataAccessException {
		
		NoticeVO outVO = dao.doSelectOne(inVO);
		if(null != outVO) {
			int updateReadCnt = dao.updateReadCnt(inVO);
			LOG.debug("┌───────────────────────────────────┐");
			LOG.debug("│ updateReadCnt                     │"+updateReadCnt);
			LOG.debug("└───────────────────────────────────┘");
		}
		return outVO;
	}

	@Override
	public int doSave(NoticeVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return dao.doSave(inVO);
	}

	@Override
	public List<NoticeVO> doRetrieve(NoticeVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return dao.doRetrieve(inVO);
	}
	
	

}
