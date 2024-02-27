package com.test.bdm.heart.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bdm.heart.dao.HeartDao;
import com.test.bdm.heart.domain.HeartVO;

@Service
public class HeartServiceImpl implements HeartService {
	
	@Autowired
	HeartDao dao;
	
	public HeartServiceImpl() {}

	@Override
	public int save(HeartVO inVO) throws SQLException {
			return dao.save(inVO);
	}

	@Override
	public int delete(HeartVO inVO) throws SQLException {
		return dao.delete(inVO);
	}

	@Override
	public List<HeartVO> selectHeart(HeartVO inVO) throws SQLException {
		return dao.selectHeart(inVO);
	}

	@Override
	public int getPostNo() throws SQLException {
		return dao.getPostNo();
	}
	
	@Override
    public int getHeartStatus(HeartVO heartVO) throws SQLException {
        return dao.getHeartStatus(heartVO);
    }

	@Override
	public int getCount(HeartVO heartVO) throws SQLException {
		return dao.getCount(heartVO);
	}

	@Override
	public int getTotalCount(HeartVO heartVO) throws SQLException {
		return dao.getTotalCount(heartVO);
	}

}
