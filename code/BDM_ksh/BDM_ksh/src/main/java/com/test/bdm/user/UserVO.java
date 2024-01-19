package com.test.bdm.user;

import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserVO extends DTO{
	
	private String userId;
	private int No;
	private String password;
	private String email;
	private String name;
	private int birth;
	private int gender;
	private int height;
	private int weight;
	private String regDt;
	private int master;

	
	
	public UserVO() {}



	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", No=" + No + ", password=" + password + ", email=" + email + ", name="
				+ name + ", birth=" + birth + ", gender=" + gender + ", height=" + height + ", weight=" + weight
				+ ", regDt=" + regDt + ", master=" + master + ", toString()=" + super.toString() + "]";
	}




	
	
	}
	
	
	
	


