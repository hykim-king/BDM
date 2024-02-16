package com.test.bdm.login.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.google.gson.Gson;
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.login.service.LoginService;
import com.test.bdm.user.domain.UserVO;

@Controller
@RequestMapping("login")
public class LoginController implements PcwkLogger {
	
	@Autowired
	LoginService loginService;
	
//	@Autowired
//	SessionLocaleResolver localeResolver;
	
	public LoginController() {}
	
	//
//	public String localeChange(@RequestParam(value="lang",defaultValue = "kor") String lang, Model model, HttpServletRequest request) {
//		
//		return lang;
//	}
	
	//
	@RequestMapping(value="/loginView.do")
	public String loginView() {
		String view = "login/login";
		LOG.debug("┌───────────┐");
		LOG.debug("│ loginView │");
		LOG.debug("└───────────┘");
		
		return view;
	}
	
	//
	@RequestMapping(value="/doLogin.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody// HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
	public String doLogin(UserVO user, HttpSession httpSession)throws SQLException{
		String jsonString = "";
		LOG.debug("┌───────────┐");
		LOG.debug("│ doLogin   │");
		LOG.debug("└───────────┘");
		
		MessageVO  message =new MessageVO();
		
		//입력 validation
		//id null check
		if(null == user.getId() || "".equals(user.getId())) {
			message.setMsgId("1");
			message.setMsgContents("아이디를 입력하시오");
			
			jsonString = new Gson().toJson(message);
			LOG.debug("│ jsonString   │"+jsonString);
			return jsonString;
		}
		
		//pass null check
		if(null == user.getPw() || "".equals(user.getPw())) {
			message.setMsgId("2");
			message.setMsgContents("비밀번호를 입력하시오");
			
			jsonString = new Gson().toJson(message);
			LOG.debug("│ jsonString   │"+jsonString);
			return jsonString;
		}
		
		int check = loginService.loginCheck(user);
		if(10 == check) {
			message.setMsgId("10");
			message.setMsgContents("아이디를 확인하시오");
		}else if(20 == check) {
			message.setMsgId("20");
			message.setMsgContents("비밀번호를 확인하시오");
		}else if(30 == check) {
			UserVO outVO = loginService.doSelectOne(user);
			message.setMsgId("30");
			message.setMsgContents(outVO.getName()+"님 반갑습니다");
			
			if(null != outVO) {
				httpSession.setAttribute("user", outVO);
			}
		}else {
			message.setMsgId("99");
			message.setMsgContents("오류가 발생했습니다");
		}
		jsonString = new Gson().toJson(message);
		LOG.debug("│ jsonString   │"+jsonString);
		
		return jsonString;
	}

}
