package com.test.bdm.user.service;

import java.sql.SQLException;
import java.util.List;

import com.test.bdm.file.domain.FileVO;
import com.test.bdm.user.domain.UserVO;

public interface UserService {
	

	/**
	 * 회원다건 입력
	 * @param list
	 * @return 입력된 회원수
	 * @throws SQLException
	 */
	public int upExcelUploadUser(List<UserVO> list)throws SQLException;
	
	/**
	 * 회원 엑셀 다운로드 
	 * @param inVO
	 * @return FileVO
	 * @throws SQLException
	 */
	public FileVO downloadUser(UserVO inVO) throws SQLException;	
	
	
	public int doCheckPassword(UserVO inVO) throws SQLException;

	public int doCheckEmail(UserVO inVO) throws SQLException;

	public int doCheckId(UserVO inVO) throws SQLException;
	
	public int doSave(UserVO inVO) throws SQLException;

}
