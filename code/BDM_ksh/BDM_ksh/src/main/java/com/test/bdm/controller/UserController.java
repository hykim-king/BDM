package com.test.bdm.controller;

import java.sql.SQLException;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.bdm.cmn.PcwkLogger;

@Controller
@RequestMapping("user")
@ResponseBody
public class UserController implements PcwkLogger {

	// 등록
	@RequestMapping(value = "/moveToReg.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ModelAndView moveToReg() throws SQLException {
        ModelAndView modelAndView = new ModelAndView("user/user_reg");
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ moveToReg()                                  │") ;
		LOG.debug("└───────────────────────────────────────────┘");
		
		

        return modelAndView;
	}

	@RequestMapping(value="/doSave.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"
			)
	public ModelAndView doSave() throws SQLException{

        ModelAndView modelAndView = new ModelAndView("user/user_reg");

		
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doSave()                                  │:");
		LOG.debug("└───────────────────────────────────────────┘");		


		return modelAndView;


}
	
	@RequestMapping(value="/idDuplicateCheck.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8"
			)
	public ModelAndView idDuplicateCheck( ) throws SQLException {
		
        ModelAndView modelAndView = new ModelAndView("user/user_reg");

		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ idDuplicateCheck()                        │:");
		LOG.debug("└───────────────────────────────────────────┘");		
					
			
		return modelAndView;
	}
}
