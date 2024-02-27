package com.test.bdm.file.dao;

import java.sql.SQLException;
import java.util.List;

import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.file.domain.FileVO;

public interface AttachFileDao extends WorkDiv<FileVO> {

public int getFileSeq() throws SQLException;
	
	public List<FileVO> getFileUuid(String uuid);
	
	public int upFileDelete(FileVO inVO) throws SQLException;

	public int getLastSeqByUuid(String uuid);
	
	List<FileVO> doRetrieve(FileVO inVO) throws SQLException;
}
