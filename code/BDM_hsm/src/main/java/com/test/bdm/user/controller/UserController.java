package com.test.bdm.user.controller;

import java.sql.SQLException;

import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Session;
import javax.mail.Authenticator;
 import java.util.Properties;
 import com.test.bdm.cmn.DTO;
 import com.test.bdm.user.domain.UserVO;
 import com.test.bdm.user.confirm.SHA256;
 import com.test.bdm.user.confirm.Gmail;
 import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
//	public UserController() {}
	
	@GetMapping(value="/moveToReg.do")
	public String moveToReg() throws SQLException {
		return "user/user_reg";
	}
	
	// ============================= 회원 가입 =====================================
	// id 검사
		@RequestMapping(value = "/doCheckId.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
		public String doCheckId(UserVO inVO) throws SQLException {
			String jsonString = "";
			
			LOG.debug("┌───────────────────┐");
			LOG.debug("┃  doCheckId()      │ inVO: " + inVO);
			LOG.debug("└───────────────────┘");
			
			int count = userService.doCheckId(inVO);
			LOG.debug("count: " + count);
			
			String message = "";
			if(0==count) {
				message = "사용 가능한 아이디 입니다.";
			}else {
				message = "중복된 아이디 입니다.";
			}
			MessageVO messageVO=new MessageVO(count+"", message);
			jsonString = new Gson().toJson(messageVO);		
			LOG.debug("jsonString:"+jsonString);		
			return jsonString;
		}
		
		// email 검사
		@RequestMapping(value = "/doCheckEmail.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
		public String doCheckEmail(UserVO inVO) throws SQLException {
			String jsonString = "";
			
			LOG.debug("┌───────────────────┐");
			LOG.debug("┃  doCheckEmail()   │ inVO: " + inVO);
			LOG.debug("└───────────────────┘");
			
			int count = userService.doCheckEmail(inVO);
			LOG.debug("count: " + count);
			
			String message = "";
			if(0==count) {
				message = "사용 가능한 이메일 입니다.";
			}else {
				message = "중복된 이메일 입니다.";
			}
			MessageVO messageVO=new MessageVO(count+"", message);
			jsonString = new Gson().toJson(messageVO);		
			LOG.debug("jsonString:"+jsonString);		
			return jsonString;
		}
		
		// password 검사
		@RequestMapping(value = "/doCheckPassword.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
		public int doCheckPassword(UserVO inVO) throws SQLException {
			
			LOG.debug("┌───────────────────┐");
			LOG.debug("┃  doCheckPassword()     │ inVO: " + inVO);
			LOG.debug("└───────────────────┘");
			
			int flag = userService.doCheckPassword(inVO);

			return flag;
		}
		
		@PostMapping(value = "/doSave.do", produces = "application/json;charset=UTF-8")
		@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
		public String doSave(UserVO inVO) throws SQLException {
			String jsonString = "";

			LOG.debug("┌───────────────────┐");
			LOG.debug("┃  doSave()     │ inVO: " + inVO);
			LOG.debug("└───────────────────┘");

			int flag = userService.doSave(inVO);
			String message = "";

			if (flag == 1)
				message = "회원가입 되었습니다";
			else
				message = "회원가입 실패";

			MessageVO messageVO = new MessageVO(flag + "", message);
			jsonString = new Gson().toJson(messageVO);
			LOG.debug("jsonString: " + jsonString);

			return jsonString;
		}
}