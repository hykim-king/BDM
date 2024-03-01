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
    
    // 게시물의 하트 개수를 가져오는 메소드
    @Override
    public int getHeartCountForBulletin(int postNo) throws SQLException {
        try {
            // HeartDao를 사용하여 해당 게시물의 하트 개수를 가져옵니다.
            return dao.getHeartCountForBulletin(postNo);
        } catch (SQLException e) {
            // 예외 처리
            throw new SQLException("게시물의 하트 개수를 가져오는 중에 오류가 발생했습니다.", e);
        }
    }
}
