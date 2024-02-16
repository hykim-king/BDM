package com.test.bdm.code.domain;

import com.test.bdm.cmn.DTO;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeVO extends DTO {
	private String category;
	private int divs;
	private String divName;
	private int seq;
	private String useYn;
	
	
	@Override
	public String toString() {
		return "CodeVO [category=" + category + ", divs=" + divs + ", divName=" + divName + ", seq=" + seq + ", useYn="
				+ useYn + ", toString()=" + super.toString() + "]";
	}
	
}