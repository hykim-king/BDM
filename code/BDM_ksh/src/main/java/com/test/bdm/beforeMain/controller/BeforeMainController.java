package com.test.bdm.beforeMain.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.test.bdm.beforeMain.service.BeforeMainService;
import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.bulletin.service.BulletinService;
import com.test.bdm.cmn.DTO;
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.StringUtil;
import com.test.bdm.news.domain.NewsVO;
import com.test.bdm.news.service.NewsService;
import com.test.bdm.notice.domain.NoticeVO;
import com.test.bdm.notice.service.NoticeService;
import com.test.bdm.user.domain.UserVO;

@Controller
@RequestMapping("beforeMain")
public class BeforeMainController implements PcwkLogger {

	@Autowired
	BeforeMainService beforeMainService;
	
	@Autowired
	BulletinService bulletinService;
	
	@Autowired
	NoticeService noticeService;
	
	@Autowired
	NewsService newsService;
	
	@GetMapping(value = "/moveToBeforeMain.do")
	public String moveToBeforeMain() throws SQLException {
		return "main/beforeLoginMain";
	}

	@GetMapping(value = "/moveToAfterMain.do")
	public String moveToAfterMain() throws SQLException {
		return "main/afterLoginMain";
	}

	@GetMapping(value = "/moveToMyPage.do")
	public String moveToMyPage() throws SQLException {
		return "user/mypage";
	}

	@GetMapping(value = "/moveToNews.do")
	public String moveToNews() throws SQLException {
		return "news/news_list"; 
	}

	@GetMapping(value = "/moveToBulletin.do")
	public String moveToBulletin() throws SQLException {
		return "board/bulletin";
	}
	
	@GetMapping(value = "/moveToNotice.do")
	public String moveToNotice() throws SQLException {
		return "board/notice";
	}

	@GetMapping(value = "/moveToMain.do")
	public String moveToMain(HttpSession httpSession) throws SQLException {
		if(httpSession.getAttribute("user") != null) {
			return "main/afterLoginMain";
		}
		else return "main/beforeLoginMain";
	}
	
	@RequestMapping(value="/doLogout.do", method = RequestMethod.GET)
	public String doLogout(HttpSession httpSession) {
		String view = "main/beforeLoginMain";
		
		if(httpSession.getAttribute("user") != null) {
			httpSession.removeAttribute("user");
			httpSession.invalidate();
		}
		
	     return view;
	}

	@RequestMapping(value = "/doLogin.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
	public String doLogin(UserVO user, HttpSession httpSession) throws SQLException {
		String jsonString = "";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doLogin                                   │user:" + user);
		LOG.debug("└───────────────────────────────────────────┘");

		MessageVO message = new MessageVO();

		// 입력 validation
		// id null check
		if (null == user.getId() || "".equals(user.getId())) {
			message.setMsgId("1");
			message.setMsgContents("아이디를 입력 하세요.");

			jsonString = new Gson().toJson(message);
			LOG.debug("jsonString:" + jsonString);
			return jsonString;
		}

		// pass null check
		if (null == user.getPw() || "".equals(user.getPw())) {
			message.setMsgId("2");
			message.setMsgContents("비밀번호를 입력 하세요.");

			jsonString = new Gson().toJson(message);
			LOG.debug("jsonString:" + jsonString);
			return jsonString;
		}

		int check = beforeMainService.loginCheck(user);
		if (10 == check) { // id확인
			message.setMsgId("10");
			message.setMsgContents("아이디를 확인 하세요.");

		} else if (20 == check) { // 비번확인
			message.setMsgId("20");
			message.setMsgContents("비밀번호를 확인 하세요.");

		} else if (30 == check) {
			UserVO outVO = beforeMainService.doSelectOne(user);
			message.setMsgId("30");
			message.setMsgContents(outVO.getName() + "님 반갑습니다.");

			if (null != outVO) {
				httpSession.setAttribute("user", outVO);
			}
		} else {
			message.setMsgContents("오류가 발생 했습니다. 다시 시도해주세요.");
		}
		jsonString = new Gson().toJson(message);
		LOG.debug("jsonString:" + jsonString);

		return jsonString;
	}
	
	@GetMapping(value = "/doGumsaek.do")
	public ModelAndView doGumsaek(DTO inVO, ModelAndView modelAndView) throws SQLException {
		if(inVO != null && inVO.getPageSize() == 0) {
			inVO.setPageSize(10L);
		}
		if(inVO != null && inVO.getPageNo() == 0) {
			inVO.setPageNo(1L);
		}
		
		if(inVO != null && inVO.getSearchWord() == null) {
			inVO.setSearchWord(StringUtil.nvl(inVO.getSearchWord()));
		}
		inVO.setSearchDiv("10");
		
		List<BulletinVO> bulletinList = bulletinService.doRetrieve(inVO);
		List<NoticeVO> noticeList = noticeService.doRetrieve(inVO);
		List<NewsVO> newsList = newsService.doRetrieve(inVO);
		
		long totalBulletin = 0;
		// 총 자유글
		for(BulletinVO vo  :bulletinList) {
			if(totalBulletin == 0) {
				totalBulletin = vo.getTotalCnt();
				break;
			}
		}
		// 총 공지글
		long totalNotice = 0;
		for(NoticeVO vo  :noticeList) {
			if(totalNotice == 0) {
				totalNotice = vo.getTotalCnt();
				break;
			}
		}
		// 총 뉴스글
		long totalNews = 0;
		for(NewsVO vo  :newsList) {
			if(totalNews == 0) {
				totalNews = vo.getTotalCnt();
				break;
			}
		}				
		modelAndView.addObject("totalBulletin", totalBulletin);
		modelAndView.addObject("totalNotice", totalNotice);
		modelAndView.addObject("totalNews", totalNews);
		modelAndView.setViewName("gumsaek/gumsaek_list");
		modelAndView.addObject("bulletinList", bulletinList);
		modelAndView.addObject("noticeList", noticeList);
		modelAndView.addObject("newsList", newsList);
		modelAndView.addObject("paramVO", inVO);
		
		return modelAndView;
	}	
}
