package com.test.bdm.user.controller;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.StringUtil;
import com.test.bdm.cmn.UserDTO;
import com.test.bdm.code.domain.CodeVO;
import com.test.bdm.code.service.CodeService;
import com.test.bdm.user.domain.UserVO;
import com.test.bdm.user.service.MailSendService;
import com.test.bdm.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController implements PcwkLogger {

	@Autowired
	UserService userService;
	
	@Autowired
	CodeService codeService;

	@Autowired
	private MailSendService mailService;
	
	private static final int SALT_SIZE = 16;
	
	@GetMapping(value = "/moveToBlockList.do")
	public ModelAndView moveToBlockList(ModelAndView model, UserDTO inVO) throws SQLException {
		if (null != inVO && inVO.getPageSize() == 0) {
			inVO.setPageSize(10L);
		}

		// 페이지 번호:1
		if (null != inVO && inVO.getPageNo() == 0) {
			inVO.setPageNo(1L);
		}

		// 검색구분:""
		if (null != inVO && null == inVO.getSearchDiv()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv()));
		}
		// 검색어:""
		if (null != inVO && null == inVO.getSearchWord()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchWord()));
		}
		LOG.debug("Bulletin Default처리: " + inVO);
		
		Map<String, Object> codes = new HashMap<String, Object>();
		String[] codeStr = { "PAGE_SIZE", "USER_SEARCH" };

		codes.put("code", codeStr);
		List<CodeVO> codeList = codeService.doRetrieve(codes);

		List<CodeVO> userSearchList = new ArrayList<CodeVO>();
		List<CodeVO> pageSizeList = new ArrayList<CodeVO>();
		
		for (CodeVO vo : codeList) {
			if (vo.getCategory().equals("USER_SEARCH")) {
				userSearchList.add(vo);
			}

			if (vo.getCategory().equals("PAGE_SIZE")) {
				pageSizeList.add(vo);
			}
		}
		
		List<UserVO> list = userService.doSelectBlockUsers(inVO);
		
		long totalCnt = 0;
		
		for(UserVO vo: list) {
			if(totalCnt == 0) {
				totalCnt = vo.getTotalCnt();
				break;
			}
		}
		model.addObject("totalCnt", totalCnt);
		model.addObject("list", list);
		model.setViewName("user/user_block_list");
		model.addObject("paramVO", inVO);
		model.addObject("userSearch", userSearchList);
		model.addObject("pageSize", pageSizeList);
		
		long bottomCount = StringUtil.BOTTOM_COUNT;// 바닥글
		String html = StringUtil.renderingPager(totalCnt, inVO.getPageNo(), inVO.getPageSize(), bottomCount,
				"/bdm/beforeMain/moveToUserMonitor.do", "pageDoRerive");
		model.addObject("pageHtml", html);

		String title = "제재 회원 목록";
		model.addObject("title", title);
		
		return model;
	}
	
	@PostMapping(value = "/doBlock.do", produces = "application/json;charset=UTF-8")
	@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
	public String doBlock(UserVO inVO) throws Exception {
		String jsonString = "";

		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doBlock()     │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");
		
		int flag = userService.doBlock(inVO);
		String message = "";

		if (flag == 1)
			message = "제재 처리 되었습니다";
		else
			message = "제재 처리 실패";

		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString: " + jsonString);

		return jsonString;
	}
	
	@GetMapping(value = "/moveToBlock.do")
	public ModelAndView moveToBlock(ModelAndView model, UserVO inVO) throws SQLException {
		String id = inVO.getId();
		UserVO outVO = doSelectOne(id);
		model.addObject("outVO", outVO);
		model.setViewName("user/user_block");
		
		return model;
	}
	
	@GetMapping(value = "/doSelectOne.do")
	public UserVO doSelectOne(String id) throws SQLException {
		return userService.doSelectOne(id);
	}
	
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
	
	// 이메일 인증
			@RequestMapping(value = "/mailCheck.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
			@ResponseBody // HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
			public String mailCheck(HttpServletRequest request) {
				String email = request.getParameter("email");

				LOG.debug("┌───────────────────────────────────────────┐");
				LOG.debug("│ mailCheck()                               │email:" + email);
				LOG.debug("└───────────────────────────────────────────┘");
				return mailService.joinEmail(email);

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