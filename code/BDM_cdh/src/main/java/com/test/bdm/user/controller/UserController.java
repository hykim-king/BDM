package com.test.bdm.user.controller;

import java.sql.SQLException;

import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Session;
import javax.mail.Authenticator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
 import com.test.bdm.cmn.DTO;
 import com.test.bdm.user.domain.UserVO;
 import com.test.bdm.user.confirm.SHA256;
 import com.test.bdm.user.confirm.Gmail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.StringUtil;
import com.test.bdm.code.service.CodeService;
import com.test.bdm.file.domain.FileVO;
import com.test.bdm.user.domain.UserVO;
import com.test.bdm.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController implements PcwkLogger {
	

	
	@Autowired
	UserService  userService;
	
	@Autowired
	CodeService codeService;
	
	final String FILE_PATH = StringUtil.FILE_PATH;
	String yyyyMMPath   = "";//년월을 포함하는 경로
	String saveFilePath = "";//저장경로
	
	public UserController() {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ UserController                            │");
		LOG.debug("└───────────────────────────────────────────┘");
				
		//FILE_PATH 폴더 생성
		File normalFileRoot = new File(FILE_PATH);
		if(normalFileRoot.isDirectory()==false) {
			boolean isMakeDir =  normalFileRoot.mkdirs();
			LOG.debug("FILE_PATH isMakeDir:"+isMakeDir);
		}
		
		//년도 : YYYY
		//월    : MM
		String yyyyStr = StringUtil.getCurrentDate("yyyy");
		String mmStr = StringUtil.getCurrentDate("MM");
		LOG.debug("yyyyStr:"+yyyyStr);
		LOG.debug("yyyyStr:"+mmStr);		
		
		yyyyMMPath = File.separator + yyyyStr + File.separator+mmStr;
		LOG.debug("yyyyMMPath:"+yyyyMMPath);		
		
		saveFilePath = FILE_PATH+yyyyMMPath;
		
		LOG.debug("saveFilePath:"+saveFilePath);	
	}
	
	@GetMapping(value="/moveToReg.do")
	public String moveToReg() throws SQLException {
		return "user/user_reg";
	}
	
	// ============================= 회원 가입 =====================================
	// id 검사
		@RequestMapping(value = "/doCheckId.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
		public String doCheckId(UserVO inVO) throws SQLException {
			String jsonString = "";
			
			LOG.debug("┌───────────────────┐");
			LOG.debug("┃  doCheckId()      │ inVO: " + inVO);
			LOG.debug("└───────────────────┘");
			
			int count = userService.doCheckId(inVO);
			LOG.debug("count: " + count);
			
			String message = "";
			if(0==count) {
				message = "사용 가능한 아이디 입니다.";
			}else {
				message = "중복된 아이디 입니다.";
			}
			MessageVO messageVO=new MessageVO(count+"", message);
			jsonString = new Gson().toJson(messageVO);		
			LOG.debug("jsonString:"+jsonString);		
			return jsonString;
		}
		@PostMapping(value = "/upExcelUploadUser.do",
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public MessageVO upExcelUploadUser(MultipartFile uploadFile) throws SQLException {
			
			MessageVO  messageVO = new MessageVO();
			LOG.debug("┌───────────────────────────────────────────┐");
			LOG.debug("│ upExcelUploadUser()                       │");
			LOG.debug("└───────────────────────────────────────────┘");
			//upload파일 upload		
			
			String UUID = StringUtil.getPK();
			//savePath, //저장파일명
			FileVO  fileVO=new FileVO();
			   
			
			//파일확장자
			String orgFileName = uploadFile.getOriginalFilename();
			String ext = StringUtil.getExt(orgFileName);
			fileVO.setSaveFileName(UUID+"."+ext);
			fileVO.setSavePath(saveFilePath);
			LOG.debug("│ fileVO                       │"+fileVO);
			
			File saveFile= null;
			try {
				uploadFile.transferTo(new File(fileVO.getSavePath(),fileVO.getSaveFileName()));
				messageVO.setMsgId("1");
				messageVO.setMsgContents(orgFileName+"업로드 되었습니다.");
				
				saveFile = new File(fileVO.getSavePath(),fileVO.getSaveFileName());
			}catch(Exception e) {
				LOG.debug("│ Exception                       │"+e.getMessage());
			}
			
			//upload파일 read
			List<UserVO> list = readExcel(saveFile);
			LOG.debug("│ list.size()                 │"+list.size());
			
			int flag = this.userService.upExcelUploadUser(list);
			messageVO.setMsgContents(flag+"건이 등록되었습니다.");
			
			return messageVO;
		}
		private List<UserVO> readExcel(File file){
			
			List<UserVO>  list=new ArrayList<UserVO>();
			
			FileInputStream  fis= null;
			try {
				fis=new FileInputStream(file);
				
				Workbook workbook=new XSSFWorkbook(fis);
				Sheet  sheet = workbook.getSheet("Sheet1");
				Iterator<Row> rowIterator = sheet.iterator();
				
				//skip the header row
				if( rowIterator.hasNext() ) {
					rowIterator.next();
				}
				
				while(rowIterator.hasNext()) {
					//row데이터를 UserVO에 메핑
					Row row = rowIterator.next();
					UserVO userVO=new UserVO();
					
					Cell cell01 = row.getCell(1);
					LOG.debug(cell01.getCellType());
					
					//row내에 각가의 cell
					userVO.setId(row.getCell(1).getStringCellValue());
					userVO.setName(row.getCell(2).getStringCellValue());
					userVO.setPw(row.getCell(3).getStringCellValue());
					userVO.setEmail(row.getCell(7).getStringCellValue());
					
					list.add(userVO);
				}
				
				for(UserVO vo  :list) {
					LOG.debug(vo);
				}
				
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}		
			
			return list;
		}
		// email 검사
		@RequestMapping(value = "/doCheckEmail.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
		public String doCheckEmail(UserVO inVO) throws SQLException {
			String jsonString = "";
			
			LOG.debug("┌───────────────────┐");
			LOG.debug("┃  doCheckEmail()   │ inVO: " + inVO);
			LOG.debug("└───────────────────┘");
			
			int count = userService.doCheckEmail(inVO);
			LOG.debug("count: " + count);
			
			String message = "";
			if(0==count) {
				message = "사용 가능한 이메일 입니다.";
			}else {
				message = "중복된 이메일 입니다.";
			}
			MessageVO messageVO=new MessageVO(count+"", message);
			jsonString = new Gson().toJson(messageVO);		
			LOG.debug("jsonString:"+jsonString);		
			return jsonString;
		}
		
		// password 검사
		@RequestMapping(value = "/doCheckPassword.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
		public int doCheckPassword(UserVO inVO) throws SQLException {
			
			LOG.debug("┌───────────────────┐");
			LOG.debug("┃  doCheckPassword()     │ inVO: " + inVO);
			LOG.debug("└───────────────────┘");
			
			int flag = userService.doCheckPassword(inVO);

			return flag;
		}
		
		@PostMapping(value = "/doSave.do", produces = "application/json;charset=UTF-8")
		@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
		public String doSave(UserVO inVO) throws SQLException {
			String jsonString = "";

			LOG.debug("┌───────────────────┐");
			LOG.debug("┃  doSave()     │ inVO: " + inVO);
			LOG.debug("└───────────────────┘");

			int flag = userService.doSave(inVO);
			String message = "";

			if (flag == 1)
				message = "회원가입 되었습니다";
			else
				message = "회원가입 실패";

			MessageVO messageVO = new MessageVO(flag + "", message);
			jsonString = new Gson().toJson(messageVO);
			LOG.debug("jsonString: " + jsonString);

			return jsonString;
		}
}