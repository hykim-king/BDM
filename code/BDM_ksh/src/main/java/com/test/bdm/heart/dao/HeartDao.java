package com.test.bdm.heart.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.bdm.heart.domain.HeartVO;

public interface HeartDao {
	
	public int getPostNo() throws SQLException;
	public int getId()throws SQLException;

	int save(HeartVO inVO)throws SQLException;
	int delete(HeartVO inVO)throws SQLException;
	List<HeartVO> selectHeart(HeartVO inVO) throws SQLException;
    int getHeartStatus(HeartVO heartVO) throws SQLException;
    int getCount(HeartVO heartVO) throws SQLException;
	int getTotalCount(HeartVO heartVO) throws SQLException;
	
	
	

}
