package com.huneth.hams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    public JavaMailSender javaMailSender;

    @Async
    public void sendMail(String email) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setFrom("lee@huneth.co.kr"); // NAVER, DAUM, NATE일 경우 넣어줘야 함
        simpleMessage.setTo(email);
        simpleMessage.setSubject("휴네스 이메일 인증 테스트");
        simpleMessage.setText("인증번호: 123456");
        javaMailSender.send(simpleMessage);
    }
}
