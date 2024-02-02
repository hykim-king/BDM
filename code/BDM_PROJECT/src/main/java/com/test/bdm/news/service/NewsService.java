package com.test.bdm.news.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.test.bdm.news.domain.NewsVO;

public interface NewsService {
   
	public int getNewsSeq()throws SQLException;
	public int doUpdate(NewsVO inVO) throws SQLException;
	
	public int doDelete(NewsVO inVO) throws SQLException;
	/**
	 * 단건조회, 조회 Count증가
	 * @param inVO
	 * @return BoardVO
	 * @throws SQLException
	 * @throws EmptyResultDataAccessException
	 */
	public NewsVO doSelectOne(NewsVO inVO) throws SQLException, EmptyResultDataAccessException;
	
	public int doSave(NewsVO inVO) throws SQLException;
	
	public List<NewsVO> doRetrieve(NewsVO inVO) throws SQLException;
}
