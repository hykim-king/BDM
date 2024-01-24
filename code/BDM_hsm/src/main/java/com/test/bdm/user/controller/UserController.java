package com.test.bdm.user.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.user.domain.UserVO;
import com.test.bdm.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController implements PcwkLogger {
	
	@Autowired
	UserService userService;
	
	public UserController() {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ UserController                            │");
		LOG.debug("└───────────────────────────────────────────┘");
	}
	
	//회원가입 페이지 이동
	@RequestMapping(value = "/moveToReg.do", method = RequestMethod.GET)
	public String moveToReg() throws SQLException {
		String view = "user/user_reg";
		
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ moveToReg                                 │");
		LOG.debug("└───────────────────────────────────────────┘");	
		
		return view;
	}
	
	//등록
	@RequestMapping(value = "/doSave.do", method = RequestMethod.POST)
	@ResponseBody
	public String doSave(UserVO inVO) throws SQLException {
		String jsonString = "";
		
		LOG.debug("─────────────────────────────────────────────");
		LOG.debug("doSave() │ inVO: " + inVO                     );
		LOG.debug("─────────────────────────────────────────────");
		
		try {
		int flag = userService.doSave(inVO);
		
		String message = "";
		
		if(flag == 1) {
			message = "등록 성공";
		} else {
			message = "등록 실패";
		}
		
		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString: " + jsonString);
		} catch (Exception e) {
	        // 예외 발생 시 로깅
	        LOG.error("Exception in doSave: ", e);
	        // 예외 메시지를 클라이언트에 반환
	        MessageVO messageVO = new MessageVO("-1", "서버 오류");
	        jsonString = new Gson().toJson(messageVO);
	    }
		
		return jsonString;
	}

}
