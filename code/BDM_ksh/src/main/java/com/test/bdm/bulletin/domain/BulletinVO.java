package com.test.bdm.bulletin.domain;

import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  //setter
@Setter  //getter
@NoArgsConstructor //default 생성자
@AllArgsConstructor//모든인자 생성자
public class BulletinVO extends DTO{

	private int postNo;
	private String title;
	private String contents;
	private String regDt;
	private String modDt;
	private int readCnt;
	private String id;
	private String modId;
	
	
	@Override
	public String toString() {
		return "BulletinVO [postNo=" + postNo + ", title=" + title + ", contents=" + contents + ", regDt=" + regDt
				+ ", modDt=" + modDt + ", readCnt=" + readCnt + ", id=" + id + ", modId=" + modId + ", toString()="
				+ super.toString() + "]";
	}

	
	
}
