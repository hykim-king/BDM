package com.test.bdm.comments.domain;

import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  //setter
@Setter  //getter
@NoArgsConstructor //default 생성자
@AllArgsConstructor//모든인자 생성자 
public class QaCommentsVO extends DTO {
	
	//소문자 변환: ctrl+shift+y
	//대문자 변환: ctrl+shift+x
	private int   regNo   ;//순번
	private String contents;
	private String regDt   ;
	private int   postNo  ;//게시순번
	private String id      ;//등록자
	private String modId;
	
	@Override
	public String toString() {
		return "QaCommentsVO [regNo=" + regNo + ", contents=" + contents + ", regDt=" + regDt + ", postNo=" + postNo
				+ ", id=" + id + ", modId=" + modId + ", toString()=" + super.toString() + "]";
	}
	
}
