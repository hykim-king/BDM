package com.test.bdm.heart.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.bdm.cmn.MessageVO;
import com.test.bdm.comments.domain.CommentsVO;
import com.test.bdm.heart.domain.HeartVO;
import com.test.bdm.heart.service.HeartService;
import com.test.bdm.user.domain.UserVO;

@Controller
@RequestMapping("heart")
public class HeartController {

	@Autowired
	HeartService service;

	@Autowired
	MessageSource messageSource;

	@GetMapping(value = "/selectHeart.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO selectHeart(HeartVO inVO, HttpSession httpSession) throws SQLException {
	    MessageVO messageVO = null;

	    // 사용자 세션에서 사용자 정보를 가져올 수 있도록 적절히 수정
	    UserVO userVO = (UserVO) httpSession.getAttribute("user");

	    // 사용자 정보가 없는 경우 예외 처리
	    if (userVO == null) {
	        return new MessageVO("-1", "사용자 정보를 찾을 수 없습니다.");
	    }

	    // 해당 글에 대한 사용자의 좋아요 상태를 가져오는 로직
	    HeartVO heartVO = new HeartVO();
	    heartVO.setId(userVO.getId()); // 사용자 ID 설정
	    heartVO.setPostNo(inVO.getPostNo()); // 글 ID 설정

	    // 서비스를 통해 좋아요 상태를 가져오는 메소드 호출
	    int heartStatus = service.getHeartStatus(heartVO);

	    // 가져온 좋아요 상태를 메시지 객체에 담아 클라이언트에게 전달
	    messageVO = new MessageVO("1", String.valueOf(heartStatus));
	    return messageVO;
	}

	@GetMapping(value = "/delete.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO delete(HeartVO inVO) throws SQLException {
		MessageVO messageVO = null;

		int flag = service.delete(inVO);

		String message = "";
		if (1 == flag) {
			message = "삭제 성공";
		} else {
			message = "삭제 실패!";
		}

		messageVO = new MessageVO(flag + "", message);

		return messageVO;
	}

	@PostMapping(value = "/save.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO doSave(HeartVO inVO, HttpSession httpSession) throws SQLException {

		
		int flag = service.save(inVO);

		String message = "";
		if (1 == flag) {

			message = "좋아요";

		} else {
			message = "좋아요 실패";
		}

		MessageVO messageVO = new MessageVO(flag + "", message);

		return messageVO;
	}
	
	@GetMapping(value = "/count.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO getCount(HeartVO heartVO, HttpSession httpSession) throws SQLException {
	    MessageVO messageVO = null;
	    
	    UserVO userVO = (UserVO) httpSession.getAttribute("user");

	    // 사용자 정보가 없는 경우 예외 처리
	    if (userVO == null) {
	        return new MessageVO("-1", "사용자 정보를 찾을 수 없습니다.");
	    }
	    
	    heartVO.setId(userVO.getId());
	    
	    int count = service.getCount(heartVO);

	    // HeartService를 사용하여 좋아요 갯수를 가져오는 메소드 호출
	    //int count = service.getCount(heartVO);

	    // 가져온 좋아요 갯수를 메시지 객체에 담아 클라이언트에게 전달
	    messageVO = new MessageVO("1", String.valueOf(count));

	    return messageVO;
	}
	
	@GetMapping(value = "/totalCount.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO getTotalCount(HeartVO heartVO, HttpSession httpSession) throws SQLException {
	    MessageVO messageVO = null;

	    UserVO userVO = (UserVO) httpSession.getAttribute("user");

	    // 사용자 정보가 없는 경우 예외 처리
	    if (userVO == null) {
	        return new MessageVO("-1", "사용자 정보를 찾을 수 없습니다.");
	    }

	    // HeartService를 사용하여 총 좋아요 갯수를 가져오는 메소드 호출
	    int totalCount = service.getTotalCount(heartVO);

	    // 가져온 총 좋아요 갯수를 메시지 객체에 담아 클라이언트에게 전달
	    messageVO = new MessageVO("1", String.valueOf(totalCount));

	    return messageVO;
	}
	


}
