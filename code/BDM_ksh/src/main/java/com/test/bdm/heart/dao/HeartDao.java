package com.test.bdm.heart.dao;

import java.sql.SQLException;

import com.test.bdm.heart.domain.HeartVO;

public interface HeartDao {
	
	int delete(HeartVO inVO)throws SQLException;
	
	int save(HeartVO inVO)throws SQLException; 
	

}
