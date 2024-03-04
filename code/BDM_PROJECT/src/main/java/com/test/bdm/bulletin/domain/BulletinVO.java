package com.test.bdm.bulletin.domain;

import java.util.List;

import com.test.bdm.cmn.DTO;
import com.test.bdm.heart.domain.HeartVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  //setter
@Setter  //getter
@NoArgsConstructor //default 생성자
@AllArgsConstructor//모든인자 생성자
public class BulletinVO extends DTO{

	private int postNo;
	private String title;
	private String contents;
	private String regDt;
	private String modDt;
	private int readCnt;
	private String id;
	private String modId;
	private int heartCount; // 하트(좋아요) 개수를 저장할 필드
	private int commentsCount;
	
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
	
	public int getCommentsCount() {
		return commentsCount;
	}

    // 기타 필드와 메소드들...

    // 하트(좋아요) 개수를 설정하는 메소드
    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }

    // 하트(좋아요) 개수를 반환하는 메소드
    public int getHeartCount() {
        return heartCount;
    }
	
	@Override
	public String toString() {
		return "BulletinVO [postNo=" + postNo + ", title=" + title + ", contents=" + contents + ", regDt=" + regDt
				+ ", modDt=" + modDt + ", readCnt=" + readCnt + ", id=" + id + ", modId=" + modId + ", toString()="
				+ super.toString() + "]";
	}

	
	
}
