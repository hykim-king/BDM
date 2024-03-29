package com.test.bdm.file.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.test.bdm.file.domain.FileVO;

public interface AttachFileService {

	/**
	 * 파일 삭제
	 * @param inVO
	 * @return
	 * @throws SQLException
	 */
	public int upFileDelete(List<FileVO> list) throws SQLException;
	
	/**
	 * 파일 단건 조회 
	 * @param inVO
	 * @return FileVO
	 * @throws SQLException
	 * @throws EmptyResultDataAccessException
	 */
	public FileVO doSelectOne(FileVO inVO) throws SQLException, EmptyResultDataAccessException;
	
	/**
	 * 파일 단건 저장
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	public int upFileSave(List<FileVO> list) throws SQLException;
	
	/**
	 * UUID가 동일한 파일 조회 
	 * @param inVO
	 * @return List<FileVO>
	 * @throws SQLException
	 */
	public List<FileVO> doRetrieve(FileVO inVO) throws SQLException;

	public List<FileVO> getFileUuid(String uuid);
	
	/**
     * 특정 UUID에 해당하는 파일들 중 가장 큰 SEQ 조회
     * @param uuid 파일 UUID
     * @return 가장 큰 SEQ 값
     * @throws SQLException
     */
    int getLastSeqByUuid(String uuid) throws SQLException;
	
	
}
