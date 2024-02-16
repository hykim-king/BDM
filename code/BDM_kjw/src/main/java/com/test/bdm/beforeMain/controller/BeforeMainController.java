package com.test.bdm.beforeMain.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.test.bdm.beforeMain.service.BeforeMainService;
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.user.domain.UserVO;

@Controller
@RequestMapping("beforeMain")
public class BeforeMainController implements PcwkLogger {
	
	@Autowired
	BeforeMainService beforeMainService;
	
	@GetMapping(value = "/moveToAfterMain.do")
	public String moveToAfterMain() throws SQLException {
		return "main/afterLoginMain";
	}

	@GetMapping(value = "/moveToMain.do")
	public String moveToMain() throws SQLException {
		return "main/beforeLoginMain";
	}

	@RequestMapping(value = "/doLogin.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
	public String doLogin(UserVO user, HttpSession httpSession) throws SQLException {
		String jsonString = "";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doLogin                                   │user:" + user);
		LOG.debug("└───────────────────────────────────────────┘");

		MessageVO message = new MessageVO();

		// 입력 validation
		// id null check
		if (null == user.getId() || "".equals(user.getId())) {
			message.setMsgId("1");
			message.setMsgContents("아이디를 입력 하세요.");

			jsonString = new Gson().toJson(message);
			LOG.debug("jsonString:" + jsonString);
			return jsonString;
		}

		// pass null check
		if (null == user.getPw() || "".equals(user.getPw())) {
			message.setMsgId("2");
			message.setMsgContents("비밀번호를 입력 하세요.");

			jsonString = new Gson().toJson(message);
			LOG.debug("jsonString:" + jsonString);
			return jsonString;
		}

		int check = beforeMainService.loginCheck(user);
		if (10 == check) { // id확인
			message.setMsgId("10");
			message.setMsgContents("아이디를 확인 하세요.");

		} else if (20 == check) { // 비번확인
			message.setMsgId("20");
			message.setMsgContents("비밀번호를 확인 하세요.");

		} else if (30 == check) {
			UserVO outVO = beforeMainService.doSelectOne(user);
			message.setMsgId("30");
			message.setMsgContents(outVO.getName() + "님 반갑습니다.");

			if (null != outVO) {
				httpSession.setAttribute("user", outVO);
			}
		} else {
			message.setMsgContents("오류가 발생 했습니다. 다시 시도해주세요.");
		}
		jsonString = new Gson().toJson(message);
		LOG.debug("jsonString:" + jsonString);

		return jsonString;
	}
}
