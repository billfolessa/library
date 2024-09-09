package com.micro.cors.dao;

import lombok.Data;

@Data
public class MailDTO {
	public final static String MAIL_FROM = "library@gmail.com";
	private int customerId;
	private String emailSubject;
	private String emailContent;
	
	

}
