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
	UserService  userService;
	
	public UserController() {}
	
	
	//http://localhost:8080/ehr/user/idDuplicateCheck.do?userId='p8-03'
	@RequestMapping(value="/idDuplicateCheck.do",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody// HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
	public String idDuplicateCheck(UserVO inVO) throws SQLException {
		String jsonString = "";  
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ idDuplicateCheck()                        │inVO:"+inVO);
		LOG.debug("└───────────────────────────────────────────┘");		
					
		int flag = userService.idDuplicateCheck(inVO);
		String message = "";
		if(0==flag) {
			message = inVO.getId()+"사용 가능";
		}else {
			message = inVO.getId()+"사용 불가";
		}
		MessageVO messageVO=new MessageVO(flag+"", message);
		jsonString = new Gson().toJson(messageVO);		
		LOG.debug("jsonString:"+jsonString);		
		return jsonString;
	}
	
	//등록
	@RequestMapping(value="/doSave.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody// HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
	public String doSave(UserVO inVO) throws SQLException{
		String jsonString = "";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doSave()                                  │inVO:"+inVO);
		LOG.debug("└───────────────────────────────────────────┘");		
		
		
		int flag = userService.doSave(inVO);
		String message = "";
		
		if(1==flag) {
			message = inVO.getId()+"등록 성공";
		}else {
			message = inVO.getId()+"등록 실패";
		}
		
		MessageVO messageVO=new MessageVO(flag+"", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString:"+jsonString);		
				
		return jsonString;
	}

	@RequestMapping(value= "/moveToReg.do", method = RequestMethod.GET)
	public String moveToReg()throws SQLException {
		String view = "user/user_reg";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ moveToReg                                 │");
		LOG.debug("└───────────────────────────────────────────┘");	
		
		return view;
	}
	
	@RequestMapping(value = "/doRetrieve.do", method = RequestMethod.GET)
	public String doRetrieve() {
	    String view = "user/user_reg";  // 해당 JSP 파일 경로
	    LOG.debug("┌───────────────────────────────────────────┐");
	    LOG.debug("│ doRetrieve                                │");
	    LOG.debug("└───────────────────────────────────────────┘");
	    return view;
	}

    
    

    // 다른 회원가입 관련 메소드들도 추가 가능
}
