package com.test.bdm.user;

import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVO extends DTO {

	private String userId;
	private int no;
	private String password;
	private String email;
	private String name;
	private int birth;
	private int gender;
	private int height;
	private int weight;
	private String regDt;
	private int master;

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", no=" + no + ", password=" + password + ", email=" + email + ", name="
				+ name + ", birth=" + birth + ", gender=" + gender + ", height=" + height + ", weight=" + weight
				+ ", regDt=" + regDt + ", master=" + master + ", toString()=" + super.toString() + "]";
	}

//	public UserVO() {
//	}
//
//	public UserVO(String userId, int no, String password, String email, String name, int birth, int gender, int height,
//			int weight, String regDt, int master) {
//		super();
//		this.userId = userId;
//		this.no = no;
//		this.password = password;
//		this.email = email;
//		this.name = name;
//		this.birth = birth;
//		this.gender = gender;
//		this.height = height;
//		this.weight = weight;
//		this.regDt = regDt;
//		this.master = master;
//	}
//
//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getBirth() {
//		return birth;
//	}
//
//	public void setBirth(int birth) {
//		this.birth = birth;
//	}
//
//	public int getGender() {
//		return gender;
//	}
//
//	public void setGender(int gender) {
//		this.gender = gender;
//	}
//
//	public int getHeight() {
//		return height;
//	}
//
//	public void setHeight(int height) {
//		this.height = height;
//	}
//
//	public int getWeight() {
//		return weight;
//	}
//
//	public void setWeight(int weight) {
//		this.weight = weight;
//	}
//
//	public String getRegDt() {
//		return regDt;
//	}
//
//	public void setRegDt(String regDt) {
//		this.regDt = regDt;
//	}
//
//	public int getMaster() {
//		return master;
//	}
//
//	public void setMaster(int master) {
//		this.master = master;
//	}

}
