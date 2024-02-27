package com.test.bdm.comments.domain;


import com.google.gson.annotations.SerializedName;
import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  //setter
@Setter  //getter
@NoArgsConstructor //default 생성자
@AllArgsConstructor//모든인자 생성자 
public class CommentsVO extends DTO {
	

<<<<<<< HEAD
	// 소문자 변환: ctrl+shift+y
	// 대문자 변환: ctrl+shift+x
	private int regNo;// 댓글순번
	private String contents;//댓글내용
	private String regDt;//댓글등록일
	private int postNo;// 게시순번
	private String id;// 댓글 등록자
	private String modId;//댓글 수정자
=======
	

	
	
	//소문자 변환: ctrl+shift+y
	//대문자 변환: ctrl+shift+x
	private int   regNo   ;//순번
	private String contents;
	private String regDt   ;
	private int   postNo  ;//게시순번
	private String id      ;//등록자
	private String modId;
	
>>>>>>> 899eb84f5bd42cee9ee4caf060e0ae577f140b54
	
	@Override
	public String toString() {
		return "CommentsVO [regNo=" + regNo + ", contents=" + contents + ", regDt=" + regDt + ", postNo=" + postNo
				+ ", id=" + id + ", modId=" + modId + ", toString()=" + super.toString() + "]";
	}
<<<<<<< HEAD
=======
	
	

	
	
	
>>>>>>> 899eb84f5bd42cee9ee4caf060e0ae577f140b54
}
