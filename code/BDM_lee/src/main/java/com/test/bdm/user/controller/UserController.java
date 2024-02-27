package com.test.bdm.user.controller;

import java.sql.SQLException;
 import com.test.bdm.user.domain.UserVO;

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
import com.test.bdm.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController implements PcwkLogger {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "/changePassword.do")
	public String changePassword() throws SQLException {
		return "account/account_changePassword";
	}
	
	@GetMapping(value="/moveToMod.do")
	public String moveToMod() throws SQLException {
		return "user/user_mod";
	}
	
	@GetMapping(value="/moveToReg.do")
	public String moveToReg() throws SQLException {
		return "user/user_reg";
	}
	
	// ==========================개인정보 수정, 회원 탈퇴================================

	@PostMapping(value = "/doUpdate.do", produces = "application/json;charset=UTF-8")
	@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
	public String doUpdate(UserVO inVO) throws SQLException {
		String jsonString = "";

		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doUpdate()     │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");

		int flag = userService.doUpdate(inVO);
		String message = "";

		if (flag == 1)
			message = "정상적으로 반영 되었습니다";
		else
			message = "업데이트 실패";

		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString: " + jsonString);

		return jsonString;
	}
	
	@PostMapping(value = "/changePassword.do", produces = "application/json;charset=UTF-8")
	@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
	public String changePassword(UserVO inVO) throws SQLException {
		String jsonString = "";

		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  changePassword()     │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");

		int flag = userService.changePassword(inVO);
		String message = "";

		if (flag == 1)
			message = "정상적으로 변경 되었습니다";
		else
			message = "변경 실패";

		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString: " + jsonString);

		return jsonString;
	}
	
	// =========================================================================
	
	//============================= 계정 찾기 =====================================
		@GetMapping(value = "/doFindId.do", produces = "application/json;charset=UTF-8")
		@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
		public String doFindId(UserVO inVO) throws SQLException {
			String jsonString = "";
			
			LOG.debug("┌───────────────────┐");
			LOG.debug("┃  doFindId()     │ inVO: " + inVO);
			LOG.debug("└───────────────────┘");
			
			UserVO outVO = userService.doFindId(inVO);
			String message = "";
			if(outVO == null) {
				message = "아이디를 찾을 수 없습니다. 정확한 정보를 입력해주세요.";
				MessageVO messageVO=new MessageVO(0+"", message);
				jsonString = new Gson().toJson(messageVO);
				LOG.debug("jsonString:"+jsonString);		
				return jsonString;
			}else {
				String id = outVO.getId();
				message = "찾으시는 아이디는 " + id + " 입니다.";
				MessageVO messageVO=new MessageVO(1+"", message);
				jsonString = new Gson().toJson(messageVO);
				LOG.debug("jsonString:"+jsonString);		
				return jsonString;
			}
		}
		
		@GetMapping(value = "/doFindPassword.do", produces = "application/json;charset=UTF-8")
		@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
		public String doFindPassword(UserVO inVO) throws SQLException {
			String jsonString = "";
			
			LOG.debug("┌───────────────────┐");
			LOG.debug("┃  doFindPassword()     │ inVO: " + inVO);
			LOG.debug("└───────────────────┘");
			
			UserVO outVO = userService.doFindPassword(inVO);
			String message = "";
			if(outVO == null) {
				message = "비밀번호를 찾을 수 없습니다. 정확한 정보를 입력해주세요.";
				MessageVO messageVO=new MessageVO(0+"", message);
				jsonString = new Gson().toJson(messageVO);
				LOG.debug("jsonString:"+jsonString);		
				return jsonString;
			}else {
				String password = outVO.getPw();
				message = "찾으시는 비밀번호는 " + password + " 입니다.";
				MessageVO messageVO=new MessageVO(1+"", message);
				jsonString = new Gson().toJson(messageVO);
				LOG.debug("jsonString:"+jsonString);		
				return jsonString;
			}
		}	
		
		// ========================================================================
	
	// ============================= 회원 가입 =====================================
	// id 검사
		@RequestMapping(value = "/doCheckId.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
		public String doCheckId(UserVO inVO) throws SQLException {
			String jsonString = "";
			
			LOG.debug("┌───────────────────┐");
			LOG.debug("┃  doCheckId()     │ inVO: " + inVO);
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
			LOG.debug("┃  doCheckEmail()     │ inVO: " + inVO);
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