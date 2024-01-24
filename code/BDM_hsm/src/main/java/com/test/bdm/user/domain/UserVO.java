package com.test.bdm.user.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UserVO {
	
	private String id;
	private int no;
	private String pw;
	private String email;
	private String name; 
	private String birth;
	private int gender;
	private int height;
	private int weight;
	private String regDt;
	private int userFilter;	
}