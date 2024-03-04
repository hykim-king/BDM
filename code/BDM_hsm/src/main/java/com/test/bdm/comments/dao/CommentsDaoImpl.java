package com.test.bdm.comments.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.comments.domain.CommentsVO;
import com.test.bdm.comments.domain.QaCommentsVO;



@Repository
public class CommentsDaoImpl implements CommentsDao,PcwkLogger {
	final String NAMESPACE = "com.test.bdm.comments";
	final String DOT       = ".";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public CommentsDaoImpl() {}
	
	
	@Override
	public int doUpdate(CommentsVO inVO) throws SQLException {
		LOG.debug("──────────────────────────────────────────");
		LOG.debug("doUpdate"                                  );
		LOG.debug("comments: " + inVO                         );
		LOG.debug("statement: " + NAMESPACE + DOT + "doUpdate");
		LOG.debug("──────────────────────────────────────────");
		return sqlSessionTemplate.update(NAMESPACE + DOT + "doUpdate", inVO);
	}
	
	@Override
	public List<CommentsVO> doRetrieve(CommentsVO inVO) throws SQLException {
		LOG.debug("────────────────────────────────────────────");
		LOG.debug("doRetrieve"                                  );
		LOG.debug("comments: " + inVO                           );
		LOG.debug("statement: " + NAMESPACE + DOT + "doRetrieve");
		LOG.debug("────────────────────────────────────────────");			
		return sqlSessionTemplate.selectList(NAMESPACE + DOT + "doRetrieve", inVO);
	}
	
	@Override
	public int doDelete(CommentsVO inVO) throws SQLException {
		LOG.debug("──────────────────────────────────────────");
		LOG.debug("doDelete"                                  );
		LOG.debug("comments: " + inVO                         );
		LOG.debug("statement: " + NAMESPACE + DOT + "doDelete");
		LOG.debug("──────────────────────────────────────────");		
		return sqlSessionTemplate.delete(NAMESPACE + DOT + "doDelete", inVO);
	}

	@Override
	public CommentsVO doSelectOne(CommentsVO inVO) throws SQLException, EmptyResultDataAccessException {
		LOG.debug("─────────────────────────────────────────────");
		LOG.debug("doSelectOne"                                  );
		LOG.debug("comments: " + inVO                            );
		LOG.debug("statement: " + NAMESPACE + DOT + "doSelectOne");
		LOG.debug("─────────────────────────────────────────────");	
		return sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doSelectOne", inVO);
	}

	@Override
	public int doSave(CommentsVO inVO) throws SQLException {
		LOG.debug("────────────────────────────────────────");
		LOG.debug("doSave"                                  );
		LOG.debug("comments: " + inVO                       );
		LOG.debug("statement: " + NAMESPACE + DOT + "doSave");
		LOG.debug("────────────────────────────────────────");		
		return sqlSessionTemplate.insert(NAMESPACE + DOT + "doSave", inVO);
	}


	@Override
	public int getRegNo() throws SQLException {
		LOG.debug("──────────────────────────────────────────");
		LOG.debug("getRegNo"                                  );
		LOG.debug("statement: " + NAMESPACE + DOT + "getRegNo");
		LOG.debug("──────────────────────────────────────────");	
		return sqlSessionTemplate.selectOne(NAMESPACE + DOT + "getRegNo");

	}


	@Override
	public int commentsCount(int postNo) throws SQLException {
		return sqlSessionTemplate.selectOne(NAMESPACE + DOT + "commentsCount",postNo);

	}

}
