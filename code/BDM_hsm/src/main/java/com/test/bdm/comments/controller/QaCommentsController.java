package com.test.bdm.comments.controller;

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
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.comments.domain.QaCommentsVO;
import com.test.bdm.comments.service.QaCommentsService;

@Controller
@RequestMapping("qaComments")
public class QaCommentsController implements PcwkLogger {

	@Autowired
	QaCommentsService service;

	@Autowired
	MessageSource messageSource;

	public QaCommentsController() {}

	@GetMapping(value = "/doRetrieve.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<QaCommentsVO> doRetrieve(QaCommentsVO inVO) throws SQLException {
		List<QaCommentsVO> list = new ArrayList<QaCommentsVO>();
		LOG.debug("─────────────────────────────────────");
		LOG.debug("doRetrieve"                           );
		LOG.debug("QaCommentsVO: " + inVO                );
		LOG.debug("─────────────────────────────────────");

		if (0 == inVO.getPostNo()) {
			LOG.debug("─────────────────────────────────────");
			LOG.debug("PostNo: " + inVO.getPostNo()          );
			LOG.debug("─────────────────────────────────────");

			throw new NullPointerException("게시판 순번을 입력 하세요.");
		}

		list = service.doRetrieve(inVO);

		return list;
	}
	

	@PostMapping(value = "/doUpdate.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO doUpdate(QaCommentsVO inVO) throws SQLException {
		// MessageVO messageVO = null;
		LOG.debug("─────────────────────────────────────");
		LOG.debug("doUpdate"                             );
		LOG.debug("QaCommentsVO: " + inVO                );
		LOG.debug("─────────────────────────────────────");

		if (0 == inVO.getRegNo()) {
			LOG.debug("─────────────────────────────────────");
			LOG.debug("getRegNo: " + inVO.getRegNo()         );
			LOG.debug("─────────────────────────────────────");

			return new MessageVO(String.valueOf("3"), "순번을 선택 하세요.");
		}

		int flag = service.doUpdate(inVO);
		// Locale locale = LocaleContextHolder.getLocale();

		String message = "";
		if (1 == flag) {
			message = "수정 성공";

		} else {
			message = "수정 실패!";
		}

		MessageVO messageVO = new MessageVO(flag + "", message);
		LOG.debug("messageVO: " + messageVO);

		return messageVO;
	}

	@GetMapping(value = "doDelete.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO doDelete(QaCommentsVO inVO) throws SQLException {
		MessageVO messageVO = null;

		if (0 == inVO.getRegNo()) {

			return new MessageVO(String.valueOf("3"), "순번을 선택 하세요.");
		}
		
		LOG.debug("─────────────────────────────────────");
		LOG.debug("doDelete"                             );
		LOG.debug("QaCommentsVO: " + inVO                );
		LOG.debug("─────────────────────────────────────");


		int flag = service.doDelete(inVO);
		LOG.debug("flag: " + flag);


		String message = "";
		if (1 == flag) {
			message = "삭제 성공";
		} else {
			message = "삭제 실패!";
		}

		messageVO = new MessageVO(flag + "", message);
		LOG.debug("messageVO: " + messageVO);

		return messageVO;
	}

	@PostMapping(value = "/doSave.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO doSave(QaCommentsVO inVO, HttpSession httpSession) throws SQLException {
		LOG.debug("─────────────────────────────────────");
		LOG.debug("doSave"                               );
		LOG.debug("QaCommentsVO: " + inVO                );
		LOG.debug("─────────────────────────────────────");

		int regNo = service.getRegNo();
		inVO.setRegNo(regNo);

		int flag = service.doSave(inVO);


		String message = "";
		if (1 == flag) {
			message = "등록 되었습니다.";
		} else {
			message = "등록 실패!";
		}

		MessageVO messageVO = new MessageVO(flag + "", message);
		LOG.debug("messageVO: " + messageVO);

		return messageVO;
	}

}
