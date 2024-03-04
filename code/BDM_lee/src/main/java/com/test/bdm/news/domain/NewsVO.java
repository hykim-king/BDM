package com.test.bdm.news.domain;

import java.sql.Date;
import java.util.List;

import com.test.bdm.cmn.DTO;
import com.test.bdm.file.domain.FileVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter  //setter
@Setter  //getter
@NoArgsConstructor //default 생성자
@AllArgsConstructor//모든인자 생성자
public class NewsVO extends DTO {
	
	private int postNo   ;
	private String title    ;
	private String contents ;
	private String regDt    ;
	private int readCnt  ;
	private String id       ;
	private String uuid  ;
	private List<FileVO> fileList;
	
	 
	public void setFileList(List<FileVO> fileList) {
	        this.fileList = fileList;
	   } 
	 
	@Override
	public String toString() {
		return "NewsVO [postNo=" + postNo + ", title=" + title + ", contents=" + contents + ", regDt=" + regDt
				+ ", readCnt=" + readCnt + ", id=" + id + ", uuid=" + uuid + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
	
}
