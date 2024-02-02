package com.test.bdm.notice.domain;

import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  //setter
@Setter  //getter
@NoArgsConstructor //default 생성자
@AllArgsConstructor//모든인자 생성자
public class NoticeVO extends DTO {
	
	private int    postNo;
    private String title;
    private String contents;
    private String regDt;
    private int    readCnt;
    private String id;
    
	@Override
	public String toString() {
		return "NoticeVO [postNo=" + postNo + ", title=" + title + ", contents=" + contents + ", regDt=" + regDt
				+ ", readCnt=" + readCnt + ", id=" + id + ", toString()=" + super.toString() + "]";
	}
}
