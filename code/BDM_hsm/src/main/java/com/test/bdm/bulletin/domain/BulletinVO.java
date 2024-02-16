package com.test.bdm.bulletin.domain;

import com.test.bdm.cmn.DTO;
import com.test.bdm.cmn.PcwkLogger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BulletinVO extends DTO {
	
	private int postNo;
	private String title;
	private String contents;
	private String regDt;
	private String modDt;
	private int readCnt;
	private String id;
	private String modId;
	
}