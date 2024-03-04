package com.test.bdm.beforeMain.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.test.bdm.cmn.UserDTO;
import com.test.bdm.code.domain.CodeVO;
import com.test.bdm.code.service.CodeService;
import com.test.bdm.file.domain.FileVO;
import com.test.bdm.file.service.AttachFileService;
import com.test.bdm.news.domain.NewsVO;
import com.test.bdm.news.service.NewsService;
import com.test.bdm.notice.domain.NoticeVO;
import com.test.bdm.notice.service.NoticeService;
import com.test.bdm.qa.domain.QaVO;
import com.test.bdm.qa.service.QaService;
import com.test.bdm.user.domain.UserVO;
import com.test.bdm.user.service.UserService;

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
	QaService qaService;
	
	@Autowired
	AttachFileService attachFileService;
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	UserService userService;

	@GetMapping(value = "/checkSession.do")
	public String checkSession(UserVO user, HttpSession httpSession) throws SQLException {
			
		String jsonString = "";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ checkSession                              │user:" + user);
		LOG.debug("└───────────────────────────────────────────┘");

		MessageVO message = new MessageVO();
		
		UserVO outVO = beforeMainService.doSelectNaverEmail(user);

		if (null != outVO) {
			httpSession.setAttribute("user", outVO);
		}
		else {
			return "user/naver_user_reg";
		}
		
		jsonString = new Gson().toJson(message);
		LOG.debug("jsonString:" + jsonString);

		return "main/afterLoginMain";
		
	}

	@GetMapping(value = "/moveToMenuBTN.do")
	public String moveToMenuBTN() {
		return "main/menuBTN";
	}

	@GetMapping(value = "/moveToUserMonitor.do")
	public ModelAndView moveToUserMonitor(ModelAndView modelAndView, UserDTO inVO) throws SQLException {
		if (null != inVO && inVO.getPageSize() == 0) {
			inVO.setPageSize(10L);
		}

		// 페이지 번호:1
		if (null != inVO && inVO.getPageNo() == 0) {
			inVO.setPageNo(1L);
		}

		// 검색구분:""
		if (null != inVO && null == inVO.getSearchDiv()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv()));
		}
		// 검색어:""
		if (null != inVO && null == inVO.getSearchWord()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchWord()));
		}
		LOG.debug("Bulletin Default처리: " + inVO);
		
		Map<String, Object> codes = new HashMap<String, Object>();
		String[] codeStr = { "PAGE_SIZE", "USER_SEARCH" };

		codes.put("code", codeStr);
		List<CodeVO> codeList = codeService.doRetrieve(codes);

		List<CodeVO> userSearchList = new ArrayList<CodeVO>();
		List<CodeVO> pageSizeList = new ArrayList<CodeVO>();
		
		for (CodeVO vo : codeList) {
			if (vo.getCategory().equals("USER_SEARCH")) {
				userSearchList.add(vo);
			}

			if (vo.getCategory().equals("PAGE_SIZE")) {
				pageSizeList.add(vo);
			}
		}
		 List<UserVO> list = userService.doRetrieve(inVO);
		
		long totalCnt = 0;
		
		for(UserVO vo: list) {
			if(totalCnt == 0) {
				totalCnt = vo.getTotalCnt();
				break;
			}
		}
		modelAndView.addObject("totalCnt", totalCnt);
		
		modelAndView.setViewName("user/user_monitor");
		modelAndView.addObject("list", list);
		modelAndView.addObject("paramVO", inVO);
		modelAndView.addObject("userSearch", userSearchList);
		modelAndView.addObject("pageSize", pageSizeList);
		
		long bottomCount = StringUtil.BOTTOM_COUNT;// 바닥글
		String html = StringUtil.renderingPager(totalCnt, inVO.getPageNo(), inVO.getPageSize(), bottomCount,
				"/bdm/beforeMain/moveToUserMonitor.do", "pageDoRerive");
		modelAndView.addObject("pageHtml", html);

		String title = "회원 관리";
		modelAndView.addObject("title", title);
		
		
		return modelAndView;
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
		if (httpSession.getAttribute("user") != null) {
			return "main/afterLoginMain";
		} else {
			return "main/beforeLoginMain";
		}

	}

	public static Date convertStringToDate(String dateString, String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public HashMap<String, String> startFinish() throws ParseException {
		HashMap<String, String> map = new HashMap<>();

		LocalDate now = LocalDate.now();

		// 포맷 정의
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
		// 포맷 적용
		String formatedNow = now.format(formatter);

		Date today = convertStringToDate(formatedNow, "yy/MM/dd");

		String pattern = "yy/MM/dd HH:mm:ss";
		// 출력용으로 사용할 데이트 포맷
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		// 포맷 적용
		String formatedToday = simpleDateFormat.format(today);

		System.out.println("formatedToday: " + formatedToday);

		Date date1 = new SimpleDateFormat(pattern).parse(formatedToday);

		Calendar calendar = Calendar.getInstance();
		// calendar 구조체에 오늘 날짜 지정
		calendar.setTime(date1);

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 출력 형식 지정, 한 주 시작 날짜(일요일)
		// 시간을 '00:00:00'으로 설정
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		String startDate = simpleDateFormat.format(calendar.getTime());
		map.put("startDate", startDate);

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		// 출력 형식 지정, 한 주 끝 날짜(토요일)
		// 시간을 '23:59:59'으로 설정
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		String finishDate = simpleDateFormat.format(calendar.getTime());
		map.put("finishDate", finishDate);

		return map;
	}

	@GetMapping(value = "/popSearchWord.do")
	public ModelAndView popSearchWord(DTO inVO, ModelAndView modelAndView, HttpSession httpSession)
			throws SQLException {
		if (inVO != null && inVO.getPageSize() == 0) {
			inVO.setPageSize(10L);
		}
		if (inVO != null && inVO.getPageNo() == 0) {
			inVO.setPageNo(1L);
		}

		if (inVO != null && inVO.getSearchWord() == null) {
			inVO.setSearchWord(StringUtil.nvl(inVO.getSearchWord()));
		}

		inVO.setSearchDiv("10");
		LOG.debug("inVO:" + inVO);
		List<DTO> wordList = beforeMainService.popSearchWord();
		LOG.debug("wordList:" + wordList);
		modelAndView.addObject("wordList", wordList);

		List<NewsVO> newsList = newsService.doRetrieve(inVO);
		for (NewsVO news : newsList) {
			List<FileVO> fileList = attachFileService.getFileUuid(news.getUuid());
			news.setFileList(fileList);
		}
		
		List<BulletinVO> bulletinList = bulletinService.doRetrieve(inVO);
		
		List<NoticeVO> noticeList = noticeService.doRetrieve(inVO);
		
		List<QaVO> qaList = qaService.doRetrieve(inVO);

		HashMap<String, String> map = new HashMap<>();
		try {
			map = startFinish();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<DTO> weeklyWordList = beforeMainService.popWeeklySearchWord(map);

		modelAndView.addObject("newsList", newsList);
		modelAndView.addObject("bulletinList", bulletinList);
		modelAndView.addObject("noticeList", noticeList);
		modelAndView.addObject("qaList", qaList);
		modelAndView.addObject("weeklyWordList", weeklyWordList);

		if (httpSession.getAttribute("user") != null) {
			modelAndView.setViewName("main/afterLoginMain");
		} else {
			modelAndView.setViewName("main/beforeLoginMain");
		}

		return modelAndView;
	}

	@GetMapping(value = "/doLogout.do")
	public ModelAndView doLogout(DTO inVO, ModelAndView modelAndView, HttpSession httpSession) throws SQLException {

		if (inVO != null && inVO.getPageSize() == 0) {
			inVO.setPageSize(10L);
		}
		if (inVO != null && inVO.getPageNo() == 0) {
			inVO.setPageNo(1L);
		}

		if (inVO != null && inVO.getSearchWord() == null) {
			inVO.setSearchWord(StringUtil.nvl(inVO.getSearchWord()));
		}

		inVO.setSearchDiv("10");
		LOG.debug("inVO:" + inVO);
		List<DTO> wordList = beforeMainService.popSearchWord();
		List<NewsVO> newsList = newsService.doRetrieve(inVO);
		List<BulletinVO> bulletinList = bulletinService.doRetrieve(inVO);
		List<NoticeVO> noticeList = noticeService.doRetrieve(inVO);
		List<QaVO> qaList = qaService.doRetrieve(inVO);
		
		HashMap<String, String> map = new HashMap<>();
		try {
			map = startFinish();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<DTO> weeklyWordList = beforeMainService.popWeeklySearchWord(map);
		
		modelAndView.addObject("wordList", wordList);
		modelAndView.addObject("newsList", newsList);
		modelAndView.addObject("bulletinList", bulletinList);
		modelAndView.addObject("noticeList", noticeList);
		modelAndView.addObject("qaList", qaList);
		modelAndView.addObject("weeklyWordList", weeklyWordList);
		
		if (httpSession.getAttribute("user") != null) {
			httpSession.removeAttribute("user");
			httpSession.invalidate();
		}
		
		if (httpSession.getAttribute("user") != null) {
			modelAndView.setViewName("main/afterLoginMain");
		} else {
			modelAndView.setViewName("main/beforeLoginMain");
		}

		return modelAndView;
	}

	@GetMapping(value = "/doGumsaek.do")
	public ModelAndView doGumsaek(DTO inVO, ModelAndView modelAndView) throws SQLException {
		if (inVO != null && inVO.getPageSize() == 0) {
			inVO.setPageSize(10L);
		}
		if (inVO != null && inVO.getPageNo() == 0) {
			inVO.setPageNo(1L);
		}

		if (inVO != null && inVO.getSearchWord() == null) {
			inVO.setSearchWord(StringUtil.nvl(inVO.getSearchWord()));
		}
		inVO.setSearchDiv("10");

		List<BulletinVO> bulletinList = bulletinService.doRetrieve(inVO);
		List<NoticeVO> noticeList = noticeService.doRetrieve(inVO);
		List<NewsVO> newsList = newsService.doRetrieve(inVO);

		long totalBulletin = 0;
		// 총 자유글
		for (BulletinVO vo : bulletinList) {
			if (totalBulletin == 0) {
				totalBulletin = vo.getTotalCnt();
				break;
			}
		}
		// 총 공지글
		long totalNotice = 0;
		for (NoticeVO vo : noticeList) {
			if (totalNotice == 0) {
				totalNotice = vo.getTotalCnt();
				break;
			}
		}
		// 총 뉴스글
		long totalNews = 0;
		for (NewsVO vo : newsList) {
			if (totalNews == 0) {
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

		if (httpSession.getAttribute("user") != null) {
			gender = sessionData.getGender();
			birth = sessionData.getBirth();
		}

		int flag = 0;
		if(!words.equals("")) {
			flag = beforeMainService.doSaveSearch(gender, birth, words);
		}

		String jsonString = "";
		String message = "";
		if (flag == 1) {
			LOG.debug("검색어 저장 성공");
			message = "검색어 저장 성공";
		} else {
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
