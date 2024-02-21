package com.test.bdm.cmn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 문자열 관련 처리
 * @author lik05
 *
 */
public class StringUtil {
	
	public final static long BOTTOM_COUNT = 10;//상수 //PC화면용
	public final static String FILE_PATH = "C:\\upload";//일반파일 업로드 경로
	
	//총글수, 페이지번호, 페이지사이즈, bottomCount, url, 자바스크립트 함수
	/**
	 * 
	 * @param maxNum : 총글수
	 * @param currentPageNo : 페이지번호
	 * @param rowPerPage : 페이지사이즈 -> 10,20,30,50,100
	 * @param bottomCount : 10 or 모바일 5
	 * @param url : 서버호출 URL
	 * @param scriptName : javascript 함수명 (홈서밋?)
	 * @return
	 */
	public static String renderingPager(long maxNum, long currentPageNo, long rowPerPage, long bottomCount, String url, String scriptName) {
		StringBuilder html = new StringBuilder(3000);
		
		//<< < 1 2 3...10  > >>
		//<< : 1page 첫페이지, if문
		//<  : bottomCount(10씩) 페이지이동 -, html특수문자
		//1,2,3.. : 해당페이지 글, for문 돌리고 if문 걸어서
		//>  : bottomCount 만큼 이동 +
		//>> : 마지막페이지
		
		//maxNum = 21
		//currentPageNo = 1
		//rowPerPage = 10
		//bottomCount = 10
		
		long maxPageNo = (maxNum -1)/rowPerPage +1;//3
		long startPageNo = ((currentPageNo -1)/bottomCount) * bottomCount +1;//1,11,21,...
		long endPageNo = ((currentPageNo -1)/bottomCount +1)*bottomCount;//10,20,30,...
		long nowBlockNo = ((currentPageNo -1)/bottomCount) +1;//1,
		long maxBlockNo = ((maxNum -1)/bottomCount) +1;//3
		
		if(currentPageNo > maxPageNo) {
			return "";
		}
		html.append("<ul class=\"pagination\"> \n");
		//<<
		//board_list.jsp
		if(nowBlockNo >1 && nowBlockNo <= maxBlockNo) {
			html.append("<li class=\"page-item\"> \n");
			html.append("<a class=\"page-link\" href=\"javascript:"+scriptName+"('"+url+"', 1);\"> \n");
			html.append("<span>&laquo;</span>");
			html.append("</a> \n");
			html.append("</li> \n");
			
		}
		
		//< : &lt;
		if(startPageNo > bottomCount) {
			html.append("<li class=\"page-item\"> \n");
			html.append("<a class=\"page-link\" href=\"javascript:"+scriptName+"('"+url+"',"+(startPageNo - bottomCount)+"); \"> \n");
			html.append("&lt;");
			html.append("</a> \n");
			html.append("</li> \n");
			
		}
		
		//1,2,3...10
		long inx = 0;
		for(inx = startPageNo; inx <= maxPageNo && inx <= endPageNo; inx++) {
			if(inx == currentPageNo) {//현재 페이지 이면 link 없음
				html.append("<li class=\"page-item\"> \n");
				html.append("<a class=\"page-link disabled\" href=\"#\"> \n");//active
				html.append(inx);
				html.append("</a> \n");
				html.append("</li> \n");
			}else {
				html.append("<li class=\"page-item\"> \n");
				html.append("<a class=\"page-link\" href=\"javascript:"+scriptName+"('"+url+"',"+(inx)+"); \"> \n");
				html.append(inx);
				html.append("</a> \n");
				html.append("</li> \n");
			}
		}
		
		//> : &gt;
		if(maxPageNo > inx) {
			html.append("<li class=\"page-item\"> \n");
			html.append("<a class=\"page-link\" href=\"javascript:"+scriptName+"('"+url+"',"+((nowBlockNo * bottomCount)+1)+"); \"> \n");
			html.append("&gt;");
			html.append("</a> \n");
			html.append("</li> \n");
		}
		
		//>>
		if(maxPageNo > inx) {
			html.append("<li class=\"page-item\"> \n");
			html.append("<a class=\"page-link\" href=\"javascript:"+scriptName+"('"+url+"',"+maxPageNo+"); \"> \n");
			html.append("<span>&raquo;</span>");
			html.append("</a> \n");
			html.append("</li> \n");
		}
		html.append("</ul> \n");
		
		return html.toString();
	}
	
	
	/**
	 * 파일에 확장자 구하기
	 * @param fileName
	 * @return "": 확장자 없음, 그렇지 않으면 return
	 */
	public static String getExt(String fileName) {
		String ext = "";
		String tmpFileName = fileName;
		if(tmpFileName.lastIndexOf(".")>-1) {
			int lastIdx = tmpFileName.lastIndexOf(".");
			ext = tmpFileName.substring(lastIdx+1);
		}
		return ext;
	}
	
	
	/**
	 * 45byte pk return
	 * @return
	 */
	public static String getPK() {
		return getCurrentDate("yyyyMMdd")+"_"+getUUID();
	}
	
	/**
	 * 
	 * @return 36byte 범용 고유 식별자(Universally Unique Identifiers)
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString();
	}
	
	/**
	 * 현재 날짜, 시간 패턴
	 * @param pattern
	 * @return 문자열
	 */
	public static String getCurrentDate(String pattern) {
		if(null == pattern || "".equals(pattern)) {
			pattern = "yyyyMMdd";
		}
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}
	
	/**
	 * 문자열 치환
	 * @param strTarget (원본 문자열)
	 * @param strReplace (치환 문자열)
	 * @return String
	 */
	public static String nvl(final String strTarget, final String strReplace) {
		if(null == strTarget || "".equals(strTarget)){
			return strReplace.trim();
		}
		
		return strTarget.trim();
	}
	
	/**
	 * 문자열이 null인 경우""으로 치환
	 * @param strTarget
	 * @return String
	 */
	public static String nvl(final String strTarget) {
		return nvl(strTarget, "");
	}
			

}
