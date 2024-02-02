package com.test.bdm.bulletin.domain;

import com.test.bdm.cmn.DTO;
import com.test.bdm.cmn.PcwkLogger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BulletinVO extends DTO {
	
	private int postNo;
	private String title;
	private String contents;
	private String regDt;
	private int readCnt;
	private String id;
	private String modId;
	
	@Override
	public String toString() {
		return "BulletinVO [postNo=" + postNo + ", title=" + title + ", contents=" + contents + ", regDt=" + regDt
				+ ", readCnt=" + readCnt + ", id=" + id + ", modId=" + modId + ", toString()=" + super.toString() + "]";
	}

}