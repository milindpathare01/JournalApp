package com.ex_mongo_2.demo_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailSendServices {

	
	@Autowired
	private JavaMailSender javamail;
	
	public void sendEmail(String to , String subjcet,String body) {
		
		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(to);
			mail.setSubject(subjcet);
			mail.setText(body);
			javamail.send(mail);
		} catch (Exception e) {
			
		}

	}
}
