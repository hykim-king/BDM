package com.test.bdm.news.dao;

import java.sql.SQLException;
import java.util.List;

import com.test.bdm.cmn.DTO;
import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.news.domain.NewsVO;

public interface NewsDao extends WorkDiv<NewsVO> {

	
	/**
	 * board_seq 
	 * @return int
	 * @throws SQLException
	 */
	int getNewsSeq()throws SQLException;
	/**
	 * 글제목으로 삭제: test only
	 * @param inVO
	 * @return int
	 * @throws SQLException
	 */
	int doDeleteAll(NewsVO inVO)throws SQLException;
	
	/**
	 * 글제목으로 삭제: test only
	 * @param inVO
	 * @return int
	 * @throws SQLException
	 */
	
	int updateReadCnt(NewsVO inVO)throws SQLException;  
	
	List<NewsVO> doRetrieve(DTO inVO) throws SQLException;
	
}
