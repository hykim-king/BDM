package com.test.bdm.file.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.test.bdm.cmn.DTO;
import com.test.bdm.file.domain.FileVO;

@Repository
public class AttachFileDaoImpl implements AttachFileDao {
	final Logger LOG = LogManager.getLogger(getClass());
	final String NAMESPACE = "com.test.bdm.file";
	final String DOT       = ".";

		//Sql Session Template: My Batis의 Sql Session Template을 주입받아, 데이터베이스와의 세션을 관리합니다.
		//이 객체를 통해 실제 데이터베이스 작업을 수행합니다.
		@Autowired
		SqlSessionTemplate sqlSessionTemplate;
		
		public AttachFileDaoImpl() {}
		
		/*CRUD 메서드
		각 메서드는 특정 데이터베이스 작업을 수행하며, 입력 값으로 FileVO 객체를 받습니다.
		FileVO는 파일 정보를 담고 있는 객체입니다.
		do Update(FileVO inVO) 현재는 구현되지 않았습니다(반환 값이 0).
		*/
		@Override
		public int doUpdate(FileVO inVO) throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		/*
		do Delete(FileVO inVO)	파일 정보를 삭제합니다.
		LOG.debug를 사용하여 입력 값 inVO를 로깅합니다.
		sql Session Template.delete 메서드를 호출하여
		SQL 매핑 파일에서 
		NAME SPACE+DOT+"do Delete"에 해당하는 SQL을 실행합니다.
		*/
		@Override
		public int doDelete(FileVO inVO) throws SQLException {
			LOG.debug("1.param \n" + inVO.toString());
			return sqlSessionTemplate.delete(NAMESPACE+DOT+"doDelete",inVO);
		}
		/*
		SQL Exception: SQL 처리 중 발생할 수 있는 예외입니다.
		각 메서드가 SQL Exception을 throw 할 수 있으므로, 이를 호출하는 서비스 레이어에서 적절히 처리해야 합니다.
		Empty Result Data Access Exception: do Select One 메서드에서 발생할 수 있는 예외로, 결과가 없을 때 발생합니다.
		호출하는 측에서 이를 처리해야 합니다.
		*/
		
		/*
		do Select One(FileVO inVO)
		하나의 파일 정보를 조회합니다. LOG.debug를 사용하여 입력 값 inVO를 로깅합니다.
		sql Session Template.select One 메서드를 호출하여 
		SQL 매핑 파일에서 NAME SPACE+DOT+"do Select One"에 해당하는 SQL을 실행합니다.
		 */
		@Override
		public FileVO doSelectOne(FileVO inVO) throws SQLException, EmptyResultDataAccessException {
			LOG.debug("1.param \n" + inVO.toString());
			
			return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"doSelectOne", inVO);
		}
	   
		/*
		do Save(FileVO inVO)
		새로운 파일 정보를 저장합니다.
		LOG.debug를 사용하여 입력 값 inVO를 로깅합니다.
		try-catch 블록 내에서 sql Session Template.insert 메서드를 호출하여 
		SQL 매핑 파일에서 NAME SPACE+DOT+"do Save"에 해당하는 SQL을 실행합니다.
		예외 발생 시, 예외 정보를 로깅하고 예외를 다시 throw합니다.
		*/

		@Override
		public int doSave(FileVO inVO) throws SQLException {
			LOG.debug("1.param \n" + inVO.toString());
			int flag = 0;
			try {
				flag = sqlSessionTemplate.insert(NAMESPACE+DOT+"doSave", inVO);
			}catch(Exception e) {
				LOG.debug("====================");
				LOG.debug("=doSave SQLException="+e.getMessage());
				LOG.debug("====================");				
				throw e;
			}
			return flag;
		}

		/* do Retrieve(FileVO inVO)
		조건에 맞는 파일 정보 목록을 조회합니다.
		LOG.debug를 사용하여 입력 값 inVO를 로깅합니다.
		sql Session Template.select List 메서드를 호출하여 
		SQL 매핑 파일에서 NAME SPACE+DOT+"do Retrieve"에 해당하는 SQL을 실행합니다.
		 */
		@Override
		public List<FileVO> doRetrieve(DTO inVO) throws SQLException {
			LOG.debug("1.param \n" + inVO.toString());
			
			return sqlSessionTemplate.selectList(NAMESPACE+DOT+"doRetrieve", inVO);
		}

		@Override
		public int getFileSeq() throws SQLException {
			return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"getFileSeq");
		}

		@Override
		public List<FileVO> getFileUuid(String uuid) {		
			return sqlSessionTemplate.selectList(NAMESPACE+DOT+"getFileUuid", uuid);
		}

		@Override
		public int upFileDelete(FileVO inVO) throws SQLException {
			return sqlSessionTemplate.update(NAMESPACE+DOT+"upFileDelete", inVO);
		}

		@Override
		public int getLastSeqByUuid(String uuid) {
			return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"getLastSeqByUuid", uuid);
		}
}
