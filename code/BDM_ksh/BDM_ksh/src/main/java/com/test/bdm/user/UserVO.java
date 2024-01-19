package com.test.bdm.user;

public class UserVO {
	
	private String userId;
	private String name;
	private String password;
	private String email;
	private int birth;
	private int gender;
	private int height;
	private int weight;
	
	public UserVO() {}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", birth=" + birth + ", gender=" + gender + ", height=" + height + ", weight=" + weight
				+ ", toString()=" + super.toString() + "]";
	}


	}
	
	
	
	


