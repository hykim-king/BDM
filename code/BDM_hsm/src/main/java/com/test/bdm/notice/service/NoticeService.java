package com.test.bdm.notice.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.notice.domain.NoticeVO;

@Transactional
public interface NoticeService {
	
	public int getNoticeSeq()throws SQLException;
	
	public int doUpdate(NoticeVO inVO)throws SQLException;
	
	public int doDelete(NoticeVO inVO)throws SQLException;
	
	public NoticeVO doSelectOne(NoticeVO inVO) throws SQLException,EmptyResultDataAccessException;
	
	public int doSave(NoticeVO inVO) throws SQLException;
	
	public List<NoticeVO> doRetrieve(NoticeVO inVO)throws SQLException;

}