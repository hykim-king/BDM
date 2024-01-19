package com.test.bdm.controller;

import java.sql.SQLException;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("user")
public class UserController {
	

	
	//µî·Ï
		@RequestMapping(value="/doSave.do",method = RequestMethod.GET	
				)
		public String doSave() throws SQLException{
			String view = "user/user_reg";

			
			return view;
		}
		
		
	
}