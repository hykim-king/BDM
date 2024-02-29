package com.test.bdm.beforeMain.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.test.bdm.file.domain.FileVO;
import com.test.bdm.file.service.AttachFileService;
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
	
	
	@Autowired
	AttachFileService attachFileService;

	@GetMapping(value = "/checkSession.do")
	public String checkSession(HttpSession httpSession) throws SQLException {
		if(httpSession.getAttribute("sessionId") != null) {
			return "main/afterLoginMain";
		}
		else {
			return "user/user_reg";
		}
	}
	
	@GetMapping(value="/moveToMenuBTN.do")
	public String moveToMenuBTN() {
        return "cmn/menuBTN";  
    }
	@GetMapping(value = "/moveToUserMonitor.do")
	public String moveToUserMonitor() throws SQLException {
		return "user/user_monitor";
	}
	
	@GetMapping(value = "/moveToFindPassword.do")
	public String moveToFindPassword() throws SQLException {
		return "account/account_findPassword";
	}
	
	@GetMapping(value = "/moveToFindId.do")
	public String moveToFindId() throws SQLException {
		return "account/account_findId";
	}
	
	@GetMapping(value = "/moveToLogin.do")
	public String moveToLogin() throws SQLException {
		return "account/account_login";
	}
	
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
		else {
			return "main/beforeLoginMain";
		}
		
	}
	
	@GetMapping(value = "/popSearchWord.do")
	public ModelAndView popSearchWord(DTO inVO, ModelAndView modelAndView, HttpSession httpSession) throws SQLException {
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
		LOG.debug("inVO:"+inVO);
		List<DTO> wordList = beforeMainService.popSearchWord();
		LOG.debug("wordList:"+wordList);
		modelAndView.addObject("wordList", wordList);
		
	
	
		List<NewsVO> newsList =  newsService.doRetrieve(inVO);
		for (NewsVO news : newsList) {
		    List<FileVO> fileList = attachFileService.getFileUuid(news.getUuid());
		    news.setFileList(fileList);
		}
		
		modelAndView.addObject("newsList", newsList);
		
		if(httpSession.getAttribute("user") != null) {
			modelAndView.setViewName("main/afterLoginMain");
		}
		else {
			modelAndView.setViewName("main/beforeLoginMain");
		}		
		
		return modelAndView;
	}	
	
	@GetMapping(value="/doLogout.do")
	public ModelAndView doLogout(DTO inVO, ModelAndView modelAndView, HttpSession httpSession) throws SQLException {
		
		if(httpSession.getAttribute("user") != null) {
			httpSession.removeAttribute("user");
			httpSession.invalidate();
		}
		
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
		LOG.debug("inVO:"+inVO);
		List<DTO> wordList = beforeMainService.popSearchWord();
		List<NewsVO> newsList =  newsService.doRetrieve(inVO);
		LOG.debug("wordList:"+wordList);
		modelAndView.addObject("wordList", wordList);
		modelAndView.addObject("newsList", newsList);
		if(httpSession.getAttribute("user") != null) {
			modelAndView.setViewName("main/afterLoginMain");
		}
		else {
			modelAndView.setViewName("main/beforeLoginMain");
		}		
		
		return modelAndView;
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
	
	@PostMapping(value = "/doSaveSearch.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSaveSearch(HttpSession httpSession, String words) throws SQLException {
		int gender = 0;
		int birth = 000000;
		UserVO sessionData = (UserVO) httpSession.getAttribute("user");
		
		if(httpSession.getAttribute("user") != null) {
			gender = sessionData.getGender();
			birth = sessionData.getBirth();
		}
		
		int flag = beforeMainService.doSaveSearch(gender, birth, words);
		
		String jsonString = "";
		String message = "";
		if(flag == 1) {
			LOG.debug("검색어 저장 성공");
			message = "검색어 저장 성공";
		}
		else {
			LOG.debug("검색어 저장 실패");
			message = "검색어 저장 실패";
		}
		
		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString: " + jsonString);
		
		return jsonString;
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
}
