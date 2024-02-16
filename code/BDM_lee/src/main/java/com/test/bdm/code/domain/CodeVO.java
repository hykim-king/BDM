package com.test.bdm.code.domain;

import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  //setter
@Setter  //getter
@NoArgsConstructor //default 생성자
@AllArgsConstructor//모든인자 생성자
public class CodeVO extends DTO {
	
	private String category;
	private int div;
	private String divName;
	private int seq;
	private String useYn;
	
	@Override
	public String toString() {
		return "CodeVO [category=" + category + ", div=" + div + ", divName=" + divName + ", seq=" + seq + ", useYn="
				+ useYn + ", toString()=" + super.toString() + "]";
	}
	

	
	
}
