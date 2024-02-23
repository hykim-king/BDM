package com.test.bdm.news.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.StringUtil;
import com.test.bdm.code.domain.CodeVO;
import com.test.bdm.code.service.CodeService;
import com.test.bdm.file.domain.FileVO;
import com.test.bdm.file.service.AttachFileService;
import com.test.bdm.news.domain.NewsVO;
import com.test.bdm.news.service.NewsService;
import com.test.bdm.user.domain.UserVO;
@Controller
@RequestMapping("news")
public class NewsController implements PcwkLogger {
   @Autowired
   NewsService service;
   
   @Autowired
   CodeService codeService;
   
   @Autowired
   AttachFileService  attachFileService;
   
   @Autowired
   MessageSource messageSource;//ResourceBundleMessageSource가 주입됨
   
   
   final String FILE_PATH = StringUtil.FILE_PATH;
   final String IMG_PATH  = "C:\\JSPM_0907\\03_WEB\\0305_SPRING\\WORKSPACE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\BDM_cdh\\resources\\upload";
   String yyyyMMPath = "";//년월을 포함하는 경로
   String saveFilePath = "";
   
   
   public NewsController() {}
   
   
   
   private static String UPLOAD_DIR = "/path/to/your/upload/directory/";
   // 이미지 업로드 처리 메소드
   @PostMapping("/uploadImage")
   public String uploadImage(@RequestParam("file") MultipartFile file) {
       try {
           String fileName = StringUtils.cleanPath(file.getOriginalFilename());
           // 파일을 저장할 경로 지정
           String uploadPath = UPLOAD_DIR;
           // 파일 저장
           FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(uploadPath + fileName));
       } catch (IOException e) {
           e.printStackTrace();
       }
       // 이미지가 저장된 경로 반환
       return UPLOAD_DIR + file.getOriginalFilename();
   }
   
   
   
   
   @GetMapping(value="/moveToReg.do")
   public String moveToReg() {
      String viewName = "";
      LOG.debug("┌───────────────────────────────────┐");
      LOG.debug("│ moveToReg                         │");
      LOG.debug("└───────────────────────────────────┘");      
      viewName = "news/news_reg";///WEB-INF/views/ viewName .jsp
      return viewName;
   }
   
   @GetMapping(value = "/doRetrieve.do")
   public ModelAndView doRetrieve(NewsVO inVO, ModelAndView modelAndView) throws SQLException{
      LOG.debug("┌───────────────────────────────────┐");
      LOG.debug("│ doRetrieve                        │");
      LOG.debug("│ BoardVO                           │"+inVO);
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
      LOG.debug("│ NewsVO Default처리                          │"+inVO);
      //코드목록 조회 : 'PAGE_SIZE','BOARD_SEARCH'
      Map<String, Object> codes =new HashMap<String, Object>();
      String[] codeStr = {"PAGE_SIZE","SEARCH"};
      
      codes.put("code", codeStr);
      List<CodeVO> codeList = this.codeService.doRetrieve(codes);
      
      List<CodeVO> NewsSearchList=new ArrayList<CodeVO>();
      List<CodeVO> pageSizeList=new ArrayList<CodeVO>();
      
      
      for(CodeVO vo :codeList) {
         if(vo.getCategory().toString().equals("SEARCH")) {
            NewsSearchList.add(vo);
         }
         
         if(vo.getCategory().toString().equals("PAGE_SIZE")) {
            pageSizeList.add(vo);
         }   
         //LOG.debug(vo);
      }
      //목록조회
      List<NewsVO>  list = service.doRetrieve(inVO);
      
      
      for (NewsVO vo : list) {
    	    List<FileVO> fileList = attachFileService.getFileUuid(vo.getUuid());
    	    vo.setFileList(fileList); // 해당 게시물의 파일 리스트를 설정합니다.
    	}
      
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
      modelAndView.setViewName("news/news_list");//  /WEB-INF/views/board/board_list.jsp
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
      //@PostMapping(value = "/doSave.do", produces = "application/json;charset=UTF-8")
      @ResponseBody
      //public MessageVO doSave(NewsVO inVO) throws SQLException{
         @RequestMapping(value="/doSave.do", method=RequestMethod.POST)   
      
         public MessageVO doSave(@RequestPart(value = "key") Map<String, Object> param, @RequestPart(value = "file",required = false) List<MultipartFile> fileList) throws Exception {
         
            /*
             * LOG.debug("┌───────────────────────────────────┐");
             * LOG.debug("│ doSave                            │");
             * LOG.debug("│ NewsVO                            │"+inVO);
             * LOG.debug("└───────────────────────────────────┘");
             */
         
//         //seq조회
//         int seq = service.getBoardSeq();
//         inVO.setSeq(seq);
//         LOG.debug("│ BoardVO seq                       │"+inVO);
         
         
         //List<FileVO>  list=new ArrayList<FileVO>();
         
         //attachFileService.upFileSave(list);
         
         
         /*
          * int flag = service.doSave(inVO);
          * 
          * String message = ""; if(1 == flag) { message = "등록 되었습니다."; }else { message =
          * "등록 실패."; }
          * 
          * MessageVO messageVO=new MessageVO(String.valueOf(flag), message);
          * LOG.debug("│ messageVO                           │"+messageVO); return
          * messageVO; }
          */

      List<FileVO>  list=new ArrayList<FileVO>();
      
      //UUID
      String UUID = StringUtil.getPK();
            
      //SEQ
      int    seq  = 1;
      
      
      for(MultipartFile multipartFile   :fileList) {
         FileVO fileVO=new FileVO();
         
         LOG.debug("│ multipartFile                          │"+multipartFile);
         
         //UUID설정
         fileVO.setUuid(UUID);
         
         //SEQ
         fileVO.setSeq(seq++);
         
         //원본파일명
         fileVO.setOrgFileName(multipartFile.getOriginalFilename());
         
         //확장자
         String ext = StringUtil.getExt(fileVO.getOrgFileName());
         fileVO.setExtension(ext);         
         
         //저장파일명 :getPK()+확장자
         //getPK(): yyyyMMdd+UUID
         fileVO.setSaveFileName(StringUtil.getPK()+"."+ext);
         
         //파일크기:byte
         fileVO.setFileSize(multipartFile.getSize());
         //저장경로 : 
         String contentType = multipartFile.getContentType();
         String savePath    = "";
         
         if(contentType.startsWith("image")==true) {//image파일
            savePath = IMG_PATH;
         }else {
            savePath = FILE_PATH;
         }
         
         fileVO.setSavePath(savePath);
         
         //------------------------------------------------------------------
         //TO DO: Session에서 사용자 ID가져올것
         //------------------------------------------------------------------
         fileVO.setRegId("Admin");
         
         try {
            //fileUpload
            multipartFile.transferTo(new File(fileVO.getSavePath(),fileVO.getSaveFileName()));
         }catch(Exception e) {
            LOG.debug("│ Exception   │"+e.getMessage());
         }
         LOG.debug("│ fileVO   │"+fileVO);
         list.add(fileVO);
      }//--for end
      
      int countFile = 0;
      try {
         countFile = attachFileService.upFileSave(list);
         LOG.debug("│ countFile   │"+countFile);
      } catch (SQLException e) {
         LOG.debug("====================");
         LOG.debug("=upFileSave SQLException=" + e.getMessage());
         LOG.debug("====================");            
      }
      
      NewsVO news = new NewsVO();
      news.setContents(param.get("contents").toString());
      news.setId(param.get("id").toString());
      news.setTitle(param.get("title").toString());
      news.setUuid(UUID);
      
       int flag = service.doSave(news); // 게시글 저장 
      
      //List<Map<String,Object>> list = attachFileService.fileUpload(param, fileList);
      
      String message = "";
      if(1 == flag) {
         message = "등록 되었습니다.";
      }else {
         message = "등록 실패.";
      }
          
      MessageVO messageVO=new MessageVO(String.valueOf(flag), message);
      return messageVO;
      
      }
      
      @GetMapping(value = "/doSelectOne.do")
      public String doSelectOne( NewsVO inVO, Model model, HttpSession httpSession)
            throws SQLException, EmptyResultDataAccessException {
         String view = "news/news_mng";
         LOG.debug("┌───────────────────────────────────┐");
         LOG.debug("│ doSelectOne                       │");
         LOG.debug("│ BulletinVO                           │" + inVO);
         LOG.debug("└───────────────────────────────────┘");
         if (0 == inVO.getPostNo()) {
            LOG.debug("============================");
            LOG.debug("==nullPointerException===");
            LOG.debug("============================");
            throw new NullPointerException("순번을 입력 하세요");
         }
         if (null == inVO.getId()) {
            inVO.setId(StringUtil.nvl(inVO.getId(), "Guest"));
         }
         // session이 있는 경우
         if (null != httpSession.getAttribute("user")) {
            UserVO user = (UserVO) httpSession.getAttribute("user");
            inVO.setId(user.getId());
         }
         
         NewsVO outVO = service.doSelectOne(inVO);
         model.addAttribute("vo", outVO);
        // 파일 정보 조회
         List<FileVO> fileList = attachFileService.getFileUuid(outVO.getUuid());
 		 model.addAttribute("fileList", fileList);
         
         
         String title = "뉴스 게시글";
         model.addAttribute("title", title);   
         return view;
      }
      
      @GetMapping(value ="/doDelete.do",produces = "application/json;charset=UTF-8" )//@RequestMapping(value = "/doDelete.do",method = RequestMethod.GET)
      @ResponseBody// HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
      public MessageVO doDelete(NewsVO inVO) throws SQLException{
         LOG.debug("┌───────────────────────────────────┐");
         LOG.debug("│ doDelete                          │");
         LOG.debug("│ BoardVO                           │"+inVO);
         LOG.debug("└───────────────────────────────────┘");      
         if(0 == inVO.getPostNo() ) {
            LOG.debug("============================");
            LOG.debug("==nullPointerException===");
            LOG.debug("============================");
            MessageVO messageVO=new MessageVO(String.valueOf("1"), "순번을 선택 하세요.");
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