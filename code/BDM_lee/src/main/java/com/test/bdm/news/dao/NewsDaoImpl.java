package com.test.bdm.news.dao;

import java.sql.SQLException;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.news.domain.NewsVO;
@Repository
public class NewsDaoImpl implements NewsDao,PcwkLogger {
	final String NAMESPACE = "com.test.bdm.news";
	final String DOT       = ".";
	 
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int doUpdate(NewsVO inVO) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doDelete                          │");
		LOG.debug("│ NewsVO                            │"+inVO);
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"doUpdate");
		LOG.debug("└───────────────────────────────────┘");		
		return sqlSessionTemplate.update(NAMESPACE+DOT+"doUpdate", inVO);
	}

	@Override
	public int doDelete(NewsVO inVO) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doDelete                          │");
		LOG.debug("│ NewsVO                            │"+inVO);
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"doDelete");
		LOG.debug("└───────────────────────────────────┘");		
		
		return sqlSessionTemplate.delete(NAMESPACE+DOT+"doDelete", inVO);
	}

	@Override
	public NewsVO doSelectOne(NewsVO inVO) throws SQLException, EmptyResultDataAccessException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doSelectOne                       │");
		LOG.debug("│ NewsVO                            │"+inVO);
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"doSelectOne");
		LOG.debug("└───────────────────────────────────┘");	
		
		return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"doSelectOne", inVO);
	}

	@Override
	public int doSave(NewsVO inVO) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doSave                            │");
		LOG.debug("│ NewsVO                            │"+inVO);
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"doSave");
		LOG.debug("└───────────────────────────────────┘");	
		return sqlSessionTemplate.insert(NAMESPACE+DOT+"doSave", inVO);
	}

	@Override
	public List<NewsVO> doRetrieve(NewsVO inVO) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doRetrieve                        │");
		LOG.debug("│ NewsVO                            │"+inVO);
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"doRetrieve");
		LOG.debug("└───────────────────────────────────┘");			
		return sqlSessionTemplate.selectList(NAMESPACE+DOT+"doRetrieve", inVO);
	}

	@Override
	public int getNewsSeq() throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ getNewsSeq                        │");
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"getNewsSeq");
		LOG.debug("└───────────────────────────────────┘");	
		return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"getNewsSeq");
	}

	
	@Override
	public int updateReadCnt(NewsVO inVO) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ updateReadCnt                     │");
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"updateReadCnt");
		LOG.debug("└───────────────────────────────────┘");	
		return sqlSessionTemplate.update(NAMESPACE+DOT+"updateReadCnt", inVO);
	}

	@Override
	public int doDeleteAll(NewsVO inVO) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doDeleteAll                       │");
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"doDeleteAll");
		LOG.debug("└───────────────────────────────────┘");	
		return sqlSessionTemplate.delete(NAMESPACE+DOT+"doDeleteAll", inVO);
	
	}

}
