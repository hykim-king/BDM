package com.test.bdm.user.domain;

import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  //setter
@Setter  //getter
@NoArgsConstructor //default 생성자
@AllArgsConstructor//모든인자 생성자
public class UserVO extends DTO{
	
	//테이블 _언더스코어
	//VO 카멜케이스
	//C+S+x/y:대/소문자
	private String id ;//아이디
	private long no;
	private String pw;//비번
	private String email  ;//email
	
	private String name    ;//이름
    private int birth     ;//생년월일
    private int gender ;//성별
    private int height ;//키
    private int weight ;//체중
    private String regDt  ;//등록일 
    
    
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", no=" + no + ", pw=" + pw + ", email=" + email + ", name=" + name + ", birth="
				+ birth + ", gender=" + gender + ", height=" + height + ", weight=" + weight + ", regDt=" + regDt
				+ ", toString()=" + super.toString() + "]";
	}
    
    
	

}
