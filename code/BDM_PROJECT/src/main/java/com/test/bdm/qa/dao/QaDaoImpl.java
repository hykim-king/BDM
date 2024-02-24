package com.test.bdm.qa.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.qa.domain.QaVO;

@Repository
public class QaDaoImpl implements PcwkLogger, QaDao {
	final String NAMESPACE = "com.test.bdm.qa";
	final String DOT = ".";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int doUpdate(QaVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(QaVO inVO) throws SQLException {
		LOG.debug("────────────────────────────────────────────");
		LOG.debug("doDelete"                                    );
		LOG.debug("QaVO: " + inVO                               );
		LOG.debug("statement: " + NAMESPACE + DOT + "doDelete"  );
		LOG.debug("────────────────────────────────────────────");	
		return sqlSessionTemplate.delete(NAMESPACE + DOT + "doDelete", inVO);
	}

	@Override
	public QaVO doSelectOne(QaVO inVO) throws SQLException, EmptyResultDataAccessException {
		LOG.debug("─────────────────────────────────────────────");
		LOG.debug("doSelectOne"                                  );
		LOG.debug("QaVO: " + inVO                                );
		LOG.debug("statement: " + NAMESPACE + DOT + "doSelectOne");
		LOG.debug("─────────────────────────────────────────────");	
		return sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doSelectOne", inVO);
	}

	@Override
	public int doSave(QaVO inVO) throws SQLException {
		LOG.debug("────────────────────────────────────────────");
		LOG.debug("doSave"                                      );
		LOG.debug("QaVO: " + inVO                               );
		LOG.debug("statement: " + NAMESPACE + DOT + "doSave"    );
		LOG.debug("────────────────────────────────────────────");	
		return sqlSessionTemplate.insert(NAMESPACE + DOT + "doSave", inVO);
	}

	@Override
	public List<QaVO> doRetrieve(QaVO inVO) throws SQLException {
		LOG.debug("────────────────────────────────────────────");
		LOG.debug("doRetrieve"                                  );
		LOG.debug("QaVO: " + inVO                               );
		LOG.debug("statement: " + NAMESPACE + DOT + "doRetrieve");
		LOG.debug("────────────────────────────────────────────");	
		return sqlSessionTemplate.selectList(NAMESPACE + DOT + "doRetrieve", inVO);
	}

	@Override
	public int getQaSeq() throws SQLException {
		LOG.debug("──────────────────────────────────────────");
		LOG.debug("getQaSeq"                                  );
		LOG.debug("statement: " + NAMESPACE + DOT + "getQaSeq");
		LOG.debug("──────────────────────────────────────────");
		return sqlSessionTemplate.selectOne(NAMESPACE + DOT + "getQaSeq");
	}

	@Override
	public int doDeleteAll(QaVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getDisclosure(QaVO inVO) throws SQLException {
		LOG.debug("───────────────────────────────────────────────");
		LOG.debug("getDisclosure"                                  );
		LOG.debug("statement: " + NAMESPACE + DOT + "getDisclosure");
		LOG.debug("────────────────────────────────────────────────");
		return sqlSessionTemplate.selectOne(NAMESPACE + DOT + "getDisclosure");
	}

}
