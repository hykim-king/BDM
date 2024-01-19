package com.test.bdm.user.domain;

import com.test.bdm.cmn.DTO;

public class UserVO extends DTO{
	
	//테이블 _언더스코어
	//VO 카멜케이스
	//C+S+x/y:대/소문자
	private String userId ;//아이디
	private String password;//비번
	private String email  ;//email
	private String name    ;//이름
    private int birth     ;//생년월일
    private int gender ;//성별
    private String height ;//키
    private String weight ;//체중
    
    //
	public UserVO() {}
	
	//
	public UserVO(String userId, String password, String email, String name, int birth, int gender, String height,
			String weight) {
		super();
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
	}

	//
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBirth() {
		return birth;
	}

	public void setBirth(int birth) {
		this.birth = birth;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	//
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", password=" + password + ", email=" + email + ", name=" + name
				+ ", birth=" + birth + ", gender=" + gender + ", height=" + height + ", weight=" + weight
				+ ", toString()=" + super.toString() + "]";
	}

}
