package com.test.bdm.news.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.StringUtil;
import com.test.bdm.code.domain.CodeVO;
import com.test.bdm.code.service.CodeService;
import com.test.bdm.news.domain.NewsVO;
import com.test.bdm.news.service.NewsService;

@Controller
@RequestMapping("news")
public class NewsController implements PcwkLogger {

	@Autowired
	NewsService service;
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	MessageSource messageSource;//ResourceBundleMessageSource가 주입됨
	
	public NewsController() {}
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping(value="/moveToRegNews.do")
	public String moveToReg(Model model, NewsVO inVO) throws SQLException {
		String viewName = "";
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ moveToReg                         │");
		LOG.debug("│ inVO                              │"+inVO);
		LOG.debug("└───────────────────────────────────┘");		
		
		
		
		viewName = "news/news";///WEB-INF/views/ viewName .jsp
		return viewName;
	}
	
	@GetMapping(value = "/doRetrieve.do")
	public ModelAndView doRetrieve(NewsVO inVO, ModelAndView modelAndView) throws SQLException{
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doRetrieve                        │");
		LOG.debug("│ NewsVO                            │"+inVO);
		LOG.debug("└───────────────────────────────────┘");
		//Default처리
		//페이지 사이즈:10
		if(null != inVO && inVO.getPageSize() == 0) {
			inVO.setPageSize(10L);
		}

		//페이지 번호:1
		if(null != inVO && inVO.getPageNo() == 0) {
			inVO.setPageNo(1L);
		}
		
		//검색구분:""
		if(null != inVO && null == inVO.getSearchDiv()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv()));
		}
		//검색어:""
		if(null != inVO && null == inVO.getSearchWord()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchWord()));
		}
		LOG.debug("│ newsVO Default처리                          │"+inVO);
		//코드목록 조회 : 'PAGE_SIZE','News_SEARCH'
		Map<String, Object> codes =new HashMap<String, Object>();
		String[] codeStr = {"PAGE_SIZE","SEARCH"};
		
		codes.put("code", codeStr);
		List<CodeVO> codeList = this.codeService.doRetrieve(codes);
		
		List<CodeVO> NewsSearchList=new ArrayList<CodeVO>();
		List<CodeVO> pageSizeList=new ArrayList<CodeVO>();
		
		
		for(CodeVO vo :codeList) {
			if(vo.getCategory().equals("SEARCH")) {
				NewsSearchList.add(vo);
			}
			
			if(vo.getCategory().equals("PAGE_SIZE")) {
				pageSizeList.add(vo);
			}	
			//LOG.debug(vo);
		}
		//목록조회
		List<NewsVO>  list = service.doRetrieve(inVO);
		
		
		long totalCnt = 0;
		//총글수 
		for(NewsVO vo  :list) {
			if(totalCnt == 0) {
				totalCnt = vo.getTotalCnt();
				break;
			}
		}
		modelAndView.addObject("totalCnt", totalCnt);
		
		//뷰
		modelAndView.setViewName("news/News_list");//  /WEB-INF/views/board/News_list.jsp
		//Model
		modelAndView.addObject("list", list);
		//검색데이터
		modelAndView.addObject("paramVO", inVO);  
		
		//검색조건
		modelAndView.addObject("newsSearch", NewsSearchList);
		
		//페이지 사이즈
		modelAndView.addObject("pageSize",pageSizeList);
		
		//페이징
		long bottomCount = StringUtil.BOTTOM_COUNT;//바닥글
		String html = StringUtil.renderingPager(totalCnt, inVO.getPageNo(), inVO.getPageSize(), bottomCount,
				"/bdm/news/doRetrieve.do", "pageDoRerive");
		modelAndView.addObject("pageHtml", html);
		
		
		
		
			
		return modelAndView;   
	}
	
	
	//@RequestMapping(value = "/doSave.do",method = RequestMethod.POST)
		@PostMapping(value = "/doSave.do", produces = "application/json;charset=UTF-8")
		@ResponseBody
		public MessageVO doSave(NewsVO inVO) throws SQLException{
			LOG.debug("┌───────────────────────────────────┐");
			LOG.debug("│ doSave                            │");
			LOG.debug("│ NewsVO                            │"+inVO);
			LOG.debug("└───────────────────────────────────┘");				
//			//seq조회
//			int seq = service.getBoardSeq();
//			inVO.setSeq(seq);
//			LOG.debug("│ BoardVO seq                       │"+inVO);
			int flag = service.doSave(inVO);
			
			String message = "";
			if(1 == flag) {
				message = "등록 되었습니다.";
			}else {
				message = "등록 실패.";
			}
			
			MessageVO  messageVO=new MessageVO(String.valueOf(flag), message);
			LOG.debug("│ messageVO                           │"+messageVO);
			return messageVO;
		}
		
		
		@GetMapping(value ="/doDelete.do",produces = "application/json;charset=UTF-8" )//@RequestMapping(value = "/doDelete.do",method = RequestMethod.GET)
		@ResponseBody// HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
		public MessageVO doDelete(NewsVO inVO) throws SQLException{
			LOG.debug("┌───────────────────────────────────┐");
			LOG.debug("│ doDelete                          │");
			LOG.debug("│ NewsVO                            │"+inVO);
			LOG.debug("└───────────────────────────────────┘");		
			if(0 == inVO.getPostNo() ) {
				LOG.debug("============================");
				LOG.debug("==nullPointerException===");
				LOG.debug("============================");
				MessageVO messageVO=new MessageVO(String.valueOf("3"), "순번을 선택 하세요.");
				return messageVO;
			} 
			
			
			int flag = service.doDelete(inVO);
			
			String   message = "";
			if(1==flag) {//삭제 성공
				message = inVO.getPostNo()+"삭제 되었습니다.";	
			}else {
				message = inVO.getPostNo()+"삭제 실패!";
			}
			
			MessageVO messageVO=new MessageVO(String.valueOf(flag), message);
			
			LOG.debug("│ messageVO                           │"+messageVO);
			return messageVO;
		}
		
	
}
