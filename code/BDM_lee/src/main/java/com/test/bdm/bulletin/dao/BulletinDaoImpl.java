package com.test.bdm.bulletin.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.cmn.PcwkLogger;
@Repository
public class BulletinDaoImpl implements BulletinDao, PcwkLogger {
	final String NAMESPACE = "com.test.bdm.bulletin";
	final String DOT       = ".";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int getBulletinSeq() throws SQLException {
		return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"getBulletinSeq");

	}

	@Override
	public int doDeleteAll(BulletinVO inVO) throws SQLException {
		return sqlSessionTemplate.delete(NAMESPACE+DOT+"doDeleteAll", inVO);

	}

	@Override
	public int updateReadCnt(BulletinVO inVO) throws SQLException {
		return sqlSessionTemplate.update(NAMESPACE+DOT+"updateReadCnt", inVO);

	}

	@Override
	public int doUpdate(BulletinVO inVO) throws SQLException {
		return sqlSessionTemplate.update(NAMESPACE+DOT+"doUpdate", inVO);

	}

	@Override
	public int doDelete(BulletinVO inVO) throws SQLException {
		return sqlSessionTemplate.delete(NAMESPACE+DOT+"doDelete", inVO);

	}

	@Override
	public BulletinVO doSelectOne(BulletinVO inVO) throws SQLException, EmptyResultDataAccessException {
		return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"doSelectOne", inVO);

	}

	@Override
	public int doSave(BulletinVO inVO) throws SQLException {
		return sqlSessionTemplate.insert(NAMESPACE+DOT+"doSave", inVO);

	}

	@Override
	public List<BulletinVO> doRetrieve(BulletinVO inVO) throws SQLException {
		return sqlSessionTemplate.selectList(NAMESPACE+DOT+"doRetrieve", inVO);

	}

}
