package com.test.bdm.user.controller;

import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserController {
	
	public UserController() {}

    @RequestMapping(value = "/moveToReg.do", method = RequestMethod.GET)
    public String moveToReg() throws SQLException {
        String view = "/user/user_reg";

        return view;
    }

}