package com.test.bdm.notice.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.bdm.cmn.DTO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.notice.domain.NoticeVO;

@Repository
public class NoticeDaoImpl implements NoticeDao, PcwkLogger {
	final String NAMESPACE = "com.test.bdm.notice";
	final String DOT       = ".";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int doDelete(NoticeVO inVO) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doDelete                          │");
		LOG.debug("│ NoticeVO                          │"+inVO);
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"doDelete");
		LOG.debug("└───────────────────────────────────┘");	
		return sqlSessionTemplate.delete(NAMESPACE+DOT+"doDelete", inVO);
	}

	@Override
	public int doSave(NoticeVO inVO) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doSave                            │");
		LOG.debug("│ NoticeVO                          │"+inVO);
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"doSave");
		//com.pcwk.ehr.board.doSave
		LOG.debug("└───────────────────────────────────┘");	
		return sqlSessionTemplate.insert(NAMESPACE+DOT+"doSave", inVO);
	}

	@Override
	public int doUpdate(NoticeVO inVO) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doDelete                          │");
		LOG.debug("│ NoticeVO                          │"+inVO);
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"doUpdate");
		LOG.debug("└───────────────────────────────────┘");		
		return sqlSessionTemplate.update(NAMESPACE+DOT+"doUpdate", inVO);
	}

	@Override
	public NoticeVO doSelectOne(NoticeVO inVO) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doSelectOne                       │");
		LOG.debug("│ NoticeVO                          │"+inVO);
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"doSelectOne");
		LOG.debug("└───────────────────────────────────┘");
		return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"doSelectOne", inVO);
	}

	@Override
	public List<NoticeVO> doRetrieve(DTO inVO) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doRetrieve                        │");
		LOG.debug("│ NoticeVO                          │"+inVO);
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"doRetrieve");
		LOG.debug("└───────────────────────────────────┘");			
		return sqlSessionTemplate.selectList(NAMESPACE+DOT+"doRetrieve", inVO);
	}

	@Override
	public int getNoticeSeq() throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ getNoticeSeq                      │");
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"getNoticeSeq");
		LOG.debug("└───────────────────────────────────┘");	
		return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"getNoticeSeq");
	}

	@Override
	public int doDeleteAll(NoticeVO inVO) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doDeleteAll                       │");
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"doDeleteAll");
		LOG.debug("└───────────────────────────────────┘");	
		return sqlSessionTemplate.delete(NAMESPACE+DOT+"doDeleteAll", inVO);
	}

	@Override
	public int updateReadCnt(NoticeVO inVO) throws SQLException {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ updateReadCnt                     │");
		LOG.debug("│ statement                         │"+NAMESPACE+DOT+"updateReadCnt");
		LOG.debug("└───────────────────────────────────┘");	
		return sqlSessionTemplate.update(NAMESPACE+DOT+"updateReadCnt", inVO);
	}
	
	

}
