package com.test.bdm.user.domain;



import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserVO extends DTO {
	//ctrl+shift+y : 소문자로
	//ctrl+shift+x : 대문자로      
	private String userId;// 아이디
	private int number; //유저번호
	private String password; //비밀번호
	private String email;
	private String name;
	private int birth;
	private int gender;
	private int height;
	private int weight;
	private String regDt;
	private int filter;
    
    
	public UserVO() {}


	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", number=" + number + ", password=" + password + ", email=" + email
				+ ", name=" + name + ", birth=" + birth + ", gender=" + gender + ", height=" + height + ", weight="
				+ weight + ", regDt=" + regDt + ", filter=" + filter + ", toString()=" + super.toString() + "]";
	}


	

	
	

	


	


	
}
