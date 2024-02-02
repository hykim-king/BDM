package com.test.bdm.news.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.news.dao.NewsDao;
import com.test.bdm.news.domain.NewsVO;

@Service
public class NewsServiceImpl implements NewsService,PcwkLogger {

	@Autowired
	NewsDao dao;
	
	public NewsServiceImpl() {}
	
	@Override
	public int getNewsSeq() throws SQLException {
		return dao.getNewsSeq();
	}

	@Override
	public int doUpdate(NewsVO inVO) throws SQLException {
		return dao.doUpdate(inVO);
	}

	@Override
	public int doDelete(NewsVO inVO) throws SQLException {
		return dao.doDelete(inVO);
	}

	@Override
	public NewsVO doSelectOne(NewsVO inVO) throws SQLException, EmptyResultDataAccessException {
		//1. 단건조회
				//2. 조회count증가
				NewsVO outVO = dao.doSelectOne(inVO);
				
				if(null != outVO) {
					int updateReadCnt = dao.updateReadCnt(inVO);
					LOG.debug("┌───────────────────────────────────┐");
					LOG.debug("│ updateReadCnt                     │"+updateReadCnt);
					LOG.debug("└───────────────────────────────────┘");				
				}

				return outVO;
	}

	@Override
	public int doSave(NewsVO inVO) throws SQLException {
		return dao.doSave(inVO);
	}

	@Override
	public List<NewsVO> doRetrieve(NewsVO inVO) throws SQLException {
		return dao.doRetrieve(inVO);
	}

}
