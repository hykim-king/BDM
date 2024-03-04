package com.test.bdm.user.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.test.bdm.cmn.PcwkLogger;

@Component
public class MailSendService implements PcwkLogger {
	@Autowired
	private JavaMailSenderImpl mailSender;
	private int authNumber; 
	
	
		public void makeRandomNumber() {
			// 난수의 범위 111111 ~ 999999 (6자리 난수)
			Random r = new Random();
			int checkNum = r.nextInt(888888) + 111111;
			System.out.println("인증번호 : " + checkNum);
			authNumber = checkNum;
		}
		
		
		//이메일 보낼 양식! 
		public String joinEmail(String email) {
			makeRandomNumber();
			String setFrom = "dohyeon7952@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력 
			String toMail = email;
			String title = "회원 가입 인증 이메일 입니다."; // 이메일 제목 
			String content = 
					"BDM 홈페이지 인증번호 입니다." + 	//html 형식으로 작성 ! 
	                "<br><br>" + 
				    "6자리 " + authNumber + "입력 해주세여.";
				   //이메일 내용 삽입
			System.out.println("내용 : " + content);
			
			mailSend(setFrom, toMail, title, content);
			return Integer.toString(authNumber);
		}
		
		//이메일 보낼 양식! 
		public String findPassword(String email) {
			makeRandomNumber();
			String setFrom = "dohyeon7952@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력 
			String toMail = email;
			String title = "비밀번호 찾기 인증 이메일 입니다."; // 이메일 제목 
			String content = 
					"BDM 홈페이지 인증번호 입니다." + 	//html 형식으로 작성 ! 
	                "<br><br>" + 
				    "6자리" + authNumber + "입니다.";
				     //이메일 내용 삽입
			System.out.println("내용 : " + content);
			
			mailSend(setFrom, toMail, title, content);
			return Integer.toString(authNumber);
		}
		
		//이메일 전송 메소드
		public void mailSend(String setFrom, String toMail, String title, String content) { 
			MimeMessage message = mailSender.createMimeMessage();
			// true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				// true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
				helper.setText(content,true);
				mailSender.send(message);
			} catch (Exception e) {
				LOG.debug("┌──────────────────────────────┐");
				LOG.debug("│MessagingException:           │" + e.getMessage());
				LOG.debug("└──────────────────────────────┘");
				e.printStackTrace();
			}

			if (mailSender instanceof JavaMailSenderImpl) {
				LOG.debug("┌─────────────────────────────────────────────────────────┐");
				LOG.debug("│ mail전송:OK                                              │" );
				LOG.debug("└─────────────────────────────────────────────────────────┘");
			}
		
		}
}