package com.bdm.ehr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	//http://localhost:8080/ehr/hello/hello.do
	@RequestMapping(value="/hello/hello.do",method = RequestMethod.GET)
	public String hello(Model model) {
		String message = "Hello world!";
		
		System.out.println("===================");
		System.out.println("=message="+message);
		System.out.println("===================");
		//화면으로 데이터 전달
		model.addAttribute("message", message);
		//http://localhost:8080/ehr/hello/hello.do
		
		//servlet-context.xml
		//hello.jsp호출  -> /WEB-INF/views/hello.jsp
		return "hello";
	}
}


//1. 브라우저: http://localhost:8080/ehr/hello/hello.do
//2. HelloController.java에 hello() ->@RequestMapping(value="/hello/hello.do"
//3. model.addAttribute(키,value);
//4. 화면호출: return "hello"; -->hello.jsp

