package com.test.bdm.notice.domain;

import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter  //setter
@Setter  //getter
@NoArgsConstructor //default 생성자
@AllArgsConstructor//모든인자 생성자
@ToString
public class NoticeVO extends DTO {
	
	private int    postNo;
    private String title;
    private String contents;
    private String regDt;
    private int    readCnt;
    private String id;
    
}
