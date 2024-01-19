package com.test.bdm.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserController {
	
	public UserController() {}
	
	@GetMapping(value="/moveToReg.do")
	public String moveToReg() throws SQLException {
		return "user/user_reg";
	}
}