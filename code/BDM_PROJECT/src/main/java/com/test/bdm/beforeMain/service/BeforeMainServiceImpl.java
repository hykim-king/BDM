package com.test.bdm.beforeMain.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.test.bdm.beforeMain.dao.BeforeMainDao;
import com.test.bdm.cmn.DTO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.user.domain.UserVO;

@Service("beforeUserServiceImpl")
public class BeforeMainServiceImpl implements BeforeMainService, PcwkLogger {

	@Autowired
	BeforeMainDao beforeMainDao;
	
	public BeforeMainServiceImpl() {}

	@Override
	public int loginCheck(UserVO inVO) throws SQLException {
		//10:ID 없음
		//20:비번이상
		//30:로그인
		int checkStatus = 0;
		
		//idCheck
		int status = beforeMainDao.idCheck(inVO);
		
		if(status==0) {
			checkStatus = 10;
			LOG.debug("10 idCheck checkStatus:"+checkStatus);
			return checkStatus;
		}
		
		//idCheck:비번 check;
		try {
			status = beforeMainDao.idPassCheck(inVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(status==0) {
			checkStatus = 20;
			LOG.debug("20 idPassCheck checkStatus:"+checkStatus);
			return checkStatus;
		}
		
		checkStatus = 30;//id/비번 정상 로그인 
		LOG.debug("30 idPassCheck pass checkStatus:"+checkStatus);
		return checkStatus;
	}

	@Override
	public UserVO doSelectOne(UserVO inVO) throws SQLException, EmptyResultDataAccessException {
		return beforeMainDao.doSelectOne(inVO);
	}

	@Override
	public int doSaveSearch(int gender, int birth, String words) throws SQLException {
		return beforeMainDao.doSaveSearch(gender, birth, words);
	}

	@Override
	public List<DTO> popSearchWord() throws SQLException {
		return beforeMainDao.popSearchWord();
	}

	@Override
	public List<DTO> popWeeklySearchWord(HashMap<String, String> map) throws SQLException {
		return beforeMainDao.popWeeklySearchWord(map);
	}
}
