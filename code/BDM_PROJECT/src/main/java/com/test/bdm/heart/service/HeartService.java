package com.test.bdm.heart.service;

import java.sql.SQLException;
import java.util.List;

import com.test.bdm.heart.domain.HeartVO;

public interface HeartService {

	public int getPostNo() throws SQLException;

	int save(HeartVO inVO) throws SQLException;

	int delete(HeartVO inVO) throws SQLException;

	List<HeartVO> selectHeart(HeartVO inVO) throws SQLException;

	int getHeartStatus(HeartVO heartVO) throws SQLException;

	int getCount(HeartVO heartVO) throws SQLException;
	
	int getTotalCount(HeartVO heartVO) throws SQLException;

	int getHeartCountForBulletin(int postNo) throws SQLException;

}
