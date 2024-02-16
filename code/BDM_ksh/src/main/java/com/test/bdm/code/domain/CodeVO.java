package com.test.bdm.code.domain;

import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*@Getter  //setter
@Setter  //getter
@NoArgsConstructor //default 생성자
@AllArgsConstructor//모든인자 생성자
*/
public class CodeVO extends DTO {
	private String category;
	private int divs;
	private String divName;
	private int seq;
	private String useYn;
	
	public CodeVO() {}
	   
	
	public CodeVO(String category, int divs, String divName, int seq, String useYn) {
		super();
		this.category = category;
		this.divs = divs;
		this.divName = divName;
		this.seq = seq;
		this.useYn = useYn;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getDivs() {
		return divs;
	}


	public void setDivs(int divs) {
		this.divs = divs;
	}


	public String getDivName() {
		return divName;
	}


	public void setDivName(String divName) {
		this.divName = divName;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getUseYn() {
		return useYn;
	}


	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}


	@Override
	public String toString() {
		return "CodeVO [category=" + category + ", divs=" + divs + ", divName=" + divName + ", seq=" + seq + ", useYn="
				+ useYn + ", toString()=" + super.toString() + "]";
	}

	
	
	
	

	
	
}
