package com.test.bdm.comments.controller;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.comments.domain.CommentsVO;
import com.test.bdm.comments.service.CommentsService;

@Controller
@RequestMapping("comments")
public class CommentsController implements PcwkLogger {
	
	@Autowired
	CommentsService service;
	
	@Autowired
	MessageSource messageSource;
	
	public CommentsController() {}
	
	
	@GetMapping(value = "/doRetrieve.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommentsVO> doRetrieve(CommentsVO inVO) throws SQLException {
		List<CommentsVO> list = new ArrayList<CommentsVO>();
		LOG.debug("┌────────────┐");
		LOG.debug("│ doRetrieve │");
		LOG.debug("│ ReplyVO    │"+inVO);
		LOG.debug("└────────────┘");
		
		//#{boardSeq}
		//필수 처리
		if(0 == inVO.getPostNo()) {
			LOG.debug("┌──────────┐");
			LOG.debug("│ BoardSeq │"+inVO.getPostNo());
			LOG.debug("└──────────┘");
			
			throw new NullPointerException("보드 순번을 입력 하시오");//예외 발생
		}
		list = service.doRetrieve(inVO);
		
		
		
		return list;
	}
	
	
	@PostMapping(value = "/doUpdate.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO doUpdate(CommentsVO inVO) throws SQLException {
		MessageVO messageVO = null;
		LOG.debug("┌──────────┐");
		LOG.debug("│ doUpdate │");
		LOG.debug("│ ReplyVO  │"+inVO);
		LOG.debug("└──────────┘");
		
		//필수 처리
		if(0 == inVO.getRegNo()) {
			LOG.debug("┌──────────┐");
			LOG.debug("│ ReplySeq │"+inVO.getRegNo());
			LOG.debug("└──────────┘");
			
			return new MessageVO(String.valueOf("3"), "순번을 선택 하시오");
		}
		
		int flag = service.doUpdate(inVO);
		
//		Locale locale = LocaleContextHolder.getLocale();
		
		String message = "";
		if(1 == flag) {
			//{0}이 되었습니다
			message = "수정 성공";
	    } else {
	        message = "수정 실패";
	    }
		
		//객체 생성?
		messageVO = new MessageVO(flag+"", message);
		LOG.debug("│ messageVO │"+messageVO);
		
		return messageVO;
	}
	
	
	@GetMapping(value = "doDelete.do", produces = "application/json;charset=UTF-8" )//@RequestMapping(value = "/doDelete.do",method = RequestMethod.GET)
	@ResponseBody// HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
	public MessageVO doDelete(CommentsVO inVO) throws SQLException {
		MessageVO messageVO = null;
		LOG.debug("┌──────────┐");
		LOG.debug("│ doDelete │");
		LOG.debug("│ ReplyVO  │"+inVO);
		LOG.debug("└──────────┘");
		
		//필수 처리
		if(0 == inVO.getRegNo()) {
			LOG.debug("┌────────────────────┐");
			LOG.debug("│ nullPointException │");
			LOG.debug("└────────────────────┘");
			
			return new MessageVO(String.valueOf("3"), "순번을 선택 하시오");
		}
		
		int flag = service.doDelete(inVO);
		
//		Locale locale = LocaleContextHolder.getLocale();
		
		String message = "";
		if(1 == flag) {
			message = "삭제 성공.";
		}else {
			message = "삭제 실패.";
		}
		
		//객체 생성?
		messageVO = new MessageVO(flag+"", message);
		LOG.debug("│ messageVO │"+messageVO);
		
		return messageVO;
	}
	
	
	@PostMapping(value = "/doSave.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO doSave(CommentsVO inVO)throws SQLException {
//		MessageVO messageVO = null;
		LOG.debug("┌────────────┐");
		LOG.debug("│ doSave     │");
		LOG.debug("│ CommentsVO │"+inVO);
		LOG.debug("└────────────┘");
		
		//조회 replySeq
		int regNo = service.getRegNo();
		inVO.setRegNo(regNo);
		LOG.debug("│ CommentsVO │"+inVO);
		
		int flag = service.doSave(inVO);
		
		String message = "";
		if(1 == flag) {
			message = "등록 성공.";
		}else {
			message = "등록 실패.";
		}
		
		MessageVO  messageVO = new MessageVO(String.valueOf(flag), message);
		LOG.debug("│ messageVO │"+messageVO);
		return messageVO;
	}

}
