package com.test.bdm.heart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeartVO {
	
	private int postNo;
	private String id;
	
	@Override
	public String toString() {
		return "HeartVO [postNo=" + postNo + ", id=" + id + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
}
