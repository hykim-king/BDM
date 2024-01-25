package com.test.bdm.user.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.user.UserService;
import com.test.bdm.user.UserVO;

@Controller
@RequestMapping("user")

public class UserController implements PcwkLogger {

	@Autowired
	UserService userService;


	// 등록
	@GetMapping(value = "/moveToReg.do", produces = "application/json;charset=UTF-8")
	public ModelAndView moveToReg() throws SQLException {
		ModelAndView modelAndView = new ModelAndView("user/user_reg");
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ moveToReg()                                  │");
		LOG.debug("└───────────────────────────────────────────┘");

		return modelAndView;
	}

	@RequestMapping(value = "/doSave.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSave(UserVO inVO) throws SQLException {

		String jsonString = "";

		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doSave()                                  │");
		LOG.debug("└───────────────────────────────────────────┘");

		int flag = userService.doSave(inVO);

		String message = "";

		if (1 == flag) {
			message = inVO.getUserId() + "가 등록 되었습니다.";
		} else {
			message = inVO.getUserId() + "등록 실패.";
		}

		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString:" + jsonString);

		return jsonString;

	}

	// http://localhost:8080/ehr/user/idDuplicateCheck.do?userId='p8-03'
	@RequestMapping(value = "/idDuplicateCheck.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody // HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
	public String idDuplicateCheck(UserVO inVO) throws SQLException {
		String jsonString = "";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ idDuplicateCheck()                        │inVO:" + inVO);
		LOG.debug("└───────────────────────────────────────────┘");

		int flag = userService.idDuplicateCheck(inVO);
		String message = "";
		if (0 == flag) {
			message = inVO.getUserId() + "사용 가능한 아이디 입니다.";
		} else {
			message = inVO.getUserId() + "사용 불가한 아이디 입니다.";
		}
		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString:" + jsonString);
		return jsonString;

	}
}
