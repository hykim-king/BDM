package com.test.bdm.notice.domain;

import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  //setter
@Setter  //getter
@NoArgsConstructor //default 생성자
@AllArgsConstructor//모든인자 생성자
public class NoticeVO extends DTO {
	
	private String post_no;
    private String title;
    private String contents;
    private String reg_dt;
    private String read_cnt;
    private String id;
    
	@Override
	public String toString() {
		return "NoticeVO [post_no=" + post_no + ", title=" + title + ", contents=" + contents + ", reg_dt=" + reg_dt
				+ ", read_cnt=" + read_cnt + ", id=" + id + ", toString()=" + super.toString() + "]";
	}
}
