package com.test.bdm.cmn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private long totalCnt;
	private long pageSize;// 페이지 사이즈:10,20,30,50,100
	private long pageNo; // 페이지 번호:1,2,3...

	private String searchDiv;// 검색구분
	private String searchWord;// 검색어

	@Override
	public String toString() {
		return "UserDTO [totalCnt=" + totalCnt + ", pageSize=" + pageSize + ", pageNo=" + pageNo + ", searchDiv="
				+ searchDiv + ", searchWord=" + searchWord + ", toString()=" + super.toString() + "]";
	}
}
