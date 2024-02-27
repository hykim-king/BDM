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

import java.security.MessageDigest;
import java.security.SecureRandom;

@Controller
@RequestMapping("user")
public class UserController implements PcwkLogger {

	@Autowired
	UserService userService;

	private static final int SALT_SIZE = 16;
	
	@GetMapping(value = "/moveToDelete.do")
	public String moveToDelete() throws SQLException {
		return "user/user_delete";
	}
	
	@GetMapping(value = "/changePassword.do")
	public String changePassword() throws SQLException {
		return "account/account_changePassword";
	}

	@GetMapping(value = "/moveToMod.do")
	public String moveToMod() throws SQLException {
		return "user/user_mod";
	}

	@GetMapping(value = "/moveToReg.do")
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
	
	@GetMapping(value = "/doDelete.do", produces = "application/json;charset=UTF-8") // RequestMethod.GET)
	@ResponseBody // HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
	public MessageVO doDelete(UserVO inVO) throws SQLException {

		int count = userService.doCheckPassword(inVO);
		int flag = 0;
		String message = "";

		if (count == 1) {
			flag = userService.doDelete(inVO);
		} else {
			message = "잘못된 비밀번호 입니다.";
			MessageVO messageVO = new MessageVO(String.valueOf(flag), message);
			return messageVO;
		}

		if (1 == flag) {// 삭제 성공
			message = "그 동안 서비스를 이용해 주셔서 감사합니다.";
		} else {
			message = "탈퇴 실패!";
		}

		MessageVO messageVO = new MessageVO(String.valueOf(flag), message);

		LOG.debug("│ messageVO                           │" + messageVO);
		return messageVO;
	}	

	// =========================================================================

	// ============================= 계정 찾기 =====================================
	@GetMapping(value = "/doFindId.do", produces = "application/json;charset=UTF-8")
	@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
	public String doFindId(UserVO inVO) throws SQLException {
		String jsonString = "";

		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doFindId()     │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");

		UserVO outVO = userService.doFindId(inVO);
		String message = "";
		if (outVO == null) {
			message = "아이디를 찾을 수 없습니다. 정확한 정보를 입력해주세요.";
			MessageVO messageVO = new MessageVO(0 + "", message);
			jsonString = new Gson().toJson(messageVO);
			LOG.debug("jsonString:" + jsonString);
			return jsonString;
		} else {
			String id = outVO.getId();
			message = "찾으시는 아이디는 " + id + " 입니다.";
			MessageVO messageVO = new MessageVO(1 + "", message);
			jsonString = new Gson().toJson(messageVO);
			LOG.debug("jsonString:" + jsonString);
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
		if (outVO == null) {
			message = "비밀번호를 찾을 수 없습니다. 정확한 정보를 입력해주세요.";
			MessageVO messageVO = new MessageVO(0 + "", message);
			jsonString = new Gson().toJson(messageVO);
			LOG.debug("jsonString:" + jsonString);
			return jsonString;
		} else {
			String password = outVO.getPw();
			message = "찾으시는 비밀번호는 " + password + " 입니다.";
			MessageVO messageVO = new MessageVO(1 + "", message);
			jsonString = new Gson().toJson(messageVO);
			LOG.debug("jsonString:" + jsonString);
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
		if (0 == count) {
			message = "사용 가능한 아이디 입니다.";
		} else {
			message = "중복된 아이디 입니다.";
		}
		MessageVO messageVO = new MessageVO(count + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString:" + jsonString);
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
		if (0 == count) {
			message = "사용 가능한 이메일 입니다.";
		} else {
			message = "중복된 이메일 입니다.";
		}
		MessageVO messageVO = new MessageVO(count + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString:" + jsonString);
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
	public String doSave(UserVO inVO) throws Exception {
		String jsonString = "";
		String SALT = getSALT();

		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doSave()     │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");

		byte[] password = String_to_Byte(inVO.getPw());

		inVO.setPw(Hashing(password, SALT));
		inVO.setSalt(SALT);
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

	// 비밀번호 해싱
	public String Hashing(byte[] password, String Salt) throws Exception {

		MessageDigest md = MessageDigest.getInstance("SHA-256"); // SHA-256 해시함수를 사용

		// key-stretching
		for (int i = 0; i < 10000; i++) {
			String temp = Byte_to_String(password) + Salt; // 패스워드와 Salt 를 합쳐 새로운 문자열 생성
			md.update(temp.getBytes()); // temp 의 문자열을 해싱하여 md 에 저장해둔다
			password = md.digest(); // md 객체의 다이제스트를 얻어 password 를 갱신한다
		}

		return Byte_to_String(password);
	}

	// SALT 값 생성
	private String getSALT() throws Exception {
		SecureRandom rnd = new SecureRandom();
		byte[] temp = new byte[SALT_SIZE];
		rnd.nextBytes(temp);

		return Byte_to_String(temp);

	}

	// 바이트 값을 16진수로 변경해준다
	private String Byte_to_String(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for (byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}

	public byte[] String_to_Byte(String hexString) {
		int len = hexString.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
					+ Character.digit(hexString.charAt(i + 1), 16));
		}
		return data;
	}
}