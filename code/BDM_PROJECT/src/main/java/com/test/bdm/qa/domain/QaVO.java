package com.test.bdm.qa.domain;

import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QaVO extends DTO {
	
	private int postNo;
	private String title;
    private String contents;
    private String regDt;
    private String id;
    
	@Override
	public String toString() {
		return "QaVO [postNo=" + postNo + ", title=" + title + ", contents=" + contents + ", regDt=" + regDt + ", id="
				+ id + ", toString()=" + super.toString() + "]";
	}
}
