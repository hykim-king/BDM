package com.test.bdm.notice.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doSave(NoticeVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(NoticeVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NoticeVO doSelectOne(NoticeVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeVO> doRetrieve(NoticeVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
