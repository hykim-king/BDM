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
	final String DOT = ".";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int doSave(BulletinVO inVO) throws SQLException {
		//com.test.bdm.bulletin.doSave
		LOG.debug("────────────────────────────────────────");
		LOG.debug(" doSave                                 ");
		LOG.debug(" BulletinVO: " + inVO                    );
		LOG.debug(" statement" + NAMESPACE + DOT + "doSave" );
		LOG.debug("────────────────────────────────────────");
		
		return sqlSessionTemplate.insert(NAMESPACE + DOT + "doSave", inVO);
	}

	@Override
	public int getBulletinPostNo() throws SQLException {
		LOG.debug("─────────────────────────────────────────────────────");
		LOG.debug(" getBulletinPostNo"                                   );
		LOG.debug(" statement: " + NAMESPACE + DOT + "getBulletinPostNo" );
		LOG.debug("─────────────────────────────────────────────────────");
		
		return sqlSessionTemplate.selectOne(NAMESPACE + DOT + "getBulletinPostNo");
	}

	@Override
	public int doDeleteAll(BulletinVO inVO) throws SQLException {
		LOG.debug("─────────────────────────────────────────────────────");
		LOG.debug(" doDeleteAll"                                         );
		LOG.debug(" statement: " + NAMESPACE + DOT + "doDeleteAll", inVO );
		LOG.debug("─────────────────────────────────────────────────────");
		
		return sqlSessionTemplate.delete(NAMESPACE + DOT + "doDeleteAll");
	}

	@Override
	public BulletinVO doSelectOne(BulletinVO inVO) throws SQLException, EmptyResultDataAccessException {
		LOG.debug("─────────────────────────────────────────────");
		LOG.debug(" doSelectOne                                 ");
		LOG.debug(" BulletinVO: " + inVO                         );
		LOG.debug(" statement" + NAMESPACE + DOT + "doSelectOne" );
		LOG.debug("─────────────────────────────────────────────");
		
		return sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doSelectOne", inVO);
	}

	@Override
	public int doUpdate(BulletinVO inVO) throws SQLException {
		LOG.debug("──────────────────────────────────────────");
		LOG.debug(" doUpdate                                 ");
		LOG.debug(" BulletinVO: " + inVO                      );
		LOG.debug(" statement" + NAMESPACE + DOT + "doUpdate" );
		LOG.debug("──────────────────────────────────────────");

		return sqlSessionTemplate.update(NAMESPACE + DOT + "doUpdate", inVO);
	}

	@Override
	public int doDelete(BulletinVO inVO) throws SQLException {
		LOG.debug("──────────────────────────────────────────");
		LOG.debug(" doDelete                                 ");
		LOG.debug(" BulletinVO: " + inVO                      );
		LOG.debug(" statement" + NAMESPACE + DOT + "doDelete" );
		LOG.debug("──────────────────────────────────────────");

		return sqlSessionTemplate.delete(NAMESPACE + DOT + "doDelete", inVO);
	}

	@Override
	public List<BulletinVO> doRetrieve(BulletinVO inVO) throws SQLException {
		LOG.debug("────────────────────────────────────────────");
		LOG.debug(" doRetrieve                                 ");
		LOG.debug(" BulletinVO: " + inVO                        );
		LOG.debug(" statement" + NAMESPACE + DOT + "doRetrieve" );
		LOG.debug("────────────────────────────────────────────");

		return sqlSessionTemplate.selectList(NAMESPACE + DOT + "doRetrieve", inVO);
	}

	@Override
	public int updateReadCnt(BulletinVO inVO) throws SQLException {
		LOG.debug("───────────────────────────────────────────────────────");
		LOG.debug(" updateReadCnt"                                         );
		LOG.debug(" statement: " + NAMESPACE + DOT + "updateReadCnt", inVO );
		LOG.debug("───────────────────────────────────────────────────────");
		
		return sqlSessionTemplate.update(NAMESPACE + DOT + "updateReadCnt", inVO);
	}

}
