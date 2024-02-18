package com.test.bdm.user.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.StringUtil;
import com.test.bdm.file.domain.FileVO;
import com.test.bdm.user.dao.UserDao;
import com.test.bdm.user.domain.UserVO;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService, PcwkLogger {

	
	public static final int MIN_LOGIN_COUNT_FOR_SILVER = 50;
	public static final int MIN_RECOMEND_COUNT_FOR_GOLD = 30;

	

	
	@Autowired
	private UserDao userDao;
	
	public UserServiceImpl() {}
	
	@Override
	public int doCheckPassword(UserVO inVO) throws SQLException {
		return userDao.doCheckPassword(inVO);
	}

	@Override
	public int doCheckEmail(UserVO inVO) throws SQLException {
		return userDao.doCheckEmail(inVO);
	}

	@Override
	public int doCheckId(UserVO inVO) throws SQLException {
		return userDao.doCheckId(inVO);
	}

	@Override
	public int doSave(UserVO inVO) throws SQLException {
		return userDao.doSave(inVO);
	}

	private String makeExcel(List<UserVO> list) {
		String retFileName = "";

		// 번호|사용자ID|이름|이메일|등급|로그인|추천|수정일
		String headerStr = "번호,사용자ID,이름,이메일,등급,로그인,추천,수정일";
		String headerArray[] = headerStr.split(",");
		// WorkBook생성
		Workbook workbook = new XSSFWorkbook();

		// header style
		CellStyle headerStyle = workbook.createCellStyle();
		// 선
		headerStyle.setBorderTop(BorderStyle.THICK);
		headerStyle.setBorderBottom(BorderStyle.DOUBLE);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		
		Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFont(font);
        
		//headerStyle.setFillBackgroundColor(IndexedColors.BLACK.index);
		//headerStyle.setFillPattern(FillPatternType.BIG_SPOTS);
		//headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		
	    
		// Sheet생성
		Sheet sheet = workbook.createSheet("user");

		int rowNo = 7;// row번호
		int cellNo = 1;// cell번호

		// column width
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 6000);
		sheet.setColumnWidth(3, 6000);
		sheet.setColumnWidth(4, 9000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 6000);

		// header생성
		Row headerRow = sheet.createRow(rowNo++);
		for (String str : headerArray) {
			Cell cell = headerRow.createCell(cellNo++);// .setCellValue(str);
			cell.setCellStyle(headerStyle);// style
			cell.setCellValue(str);

		}

		// 리플렉션으로 처리 :?
		for (UserVO vo : list) {
			Row row = sheet.createRow(rowNo++);
			cellNo = 1;

			Cell dataCell = row.createCell(cellNo++);
			dataCell.setCellStyle(StringUtil.alignCell(workbook, "CENTER"));
			dataCell.setCellValue(vo.getNo());

			dataCell = row.createCell(cellNo++);
			dataCell.setCellStyle(StringUtil.alignCell(workbook, "LEFT"));
			dataCell.setCellValue(vo.getId());

			dataCell = row.createCell(cellNo++);
			dataCell.setCellStyle(StringUtil.alignCell(workbook, "LEFT"));
			dataCell.setCellValue(vo.getName());

			dataCell = row.createCell(cellNo++);
			dataCell.setCellStyle(StringUtil.alignCell(workbook, "LEFT"));
			dataCell.setCellValue(vo.getEmail());

			

			dataCell = row.createCell(cellNo++);
			dataCell.setCellStyle(StringUtil.alignCell(workbook, "CENTER"));
			dataCell.setCellValue(vo.getRegDt());

		}

		String fileName = StringUtil.getPK() + ".xlsx";
		File fileCreate = new File("c:\\upload\\" + fileName);
		try {
			FileOutputStream fileOut = new FileOutputStream(fileCreate);

			workbook.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		LOG.debug("│ 파일생성 완료                                 │" + fileCreate.getAbsolutePath());
		retFileName = fileCreate.getAbsolutePath();
		LOG.debug("│ retFileName             │" + retFileName);
		
		return retFileName;
	}


	@Override
	public FileVO downloadUser(UserVO inVO) throws SQLException {
		FileVO fileVO = new FileVO();
		// 1. 데이터 조회
		// 2. 데이터 excel에 기록, 파일 생성
		// 3. 파일정보를 return

		List<UserVO> list = userDao.doRetrieve(inVO);
		String fileFullPathName = makeExcel(list);
		
		fileVO.setSaveFileName(fileFullPathName);
		return fileVO;
	}
	
	@Override
	public int upExcelUploadUser(List<UserVO> list) throws SQLException {
		int flag = 0;
		
		for(UserVO vo  :list) {
			flag += userDao.doSave(vo);
		}
		
		LOG.debug("│ flag             │" + flag);
		return flag;
	}

}
