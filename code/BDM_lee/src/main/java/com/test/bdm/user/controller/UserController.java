package com.test.bdm.user.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.bdm.cmn.PcwkLogger;

@Controller
@RequestMapping("user")
public class UserController implements PcwkLogger {
	
	public UserController() {}

    @RequestMapping(value = "/doRegister.do", method = RequestMethod.GET)
    public String doRegister() throws SQLException {
    	String view = "user/user_reg";
    	LOG.debug("┌────────────────┐");
		LOG.debug("│naverApiView    │");
		LOG.debug("└────────────────┘");
    	
    	
        return view;
    }
    
    

    // 다른 회원가입 관련 메소드들도 추가 가능
}
