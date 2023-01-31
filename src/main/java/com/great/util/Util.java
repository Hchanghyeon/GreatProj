package com.great.util;

import java.security.MessageDigest;
import org.apache.commons.mail.HtmlEmail;
import java.security.SecureRandom;

public class Util {
	
	public static String getSALT() throws Exception {
		SecureRandom rnd = new SecureRandom();
		byte[] temp = new byte[16];
		rnd.nextBytes(temp);
		
		return Byte_to_String(temp);
	}
	
	public static String Byte_to_String(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for(byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}
	
	public static String Hashing(byte[] password, String Salt) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256"); 
 
		for(int i = 0; i < 10000; i++) {
			String temp = Byte_to_String(password) + Salt; 
			md.update(temp.getBytes()); 
			password = md.digest(); 
		}
		
		return Byte_to_String(password);
	}
	
	public static void sendMail(String email, String subject, String msg) throws Exception {
		String charSet = "utf-8";
		String hostSMTP = "";
		String hostSMTPid = "";
		String hostSMTPpwd = ""; 
		
		String fromEmail = "";
		String fromName = "";
		
		try {
			HtmlEmail mail = new HtmlEmail();
			mail.setDebug(true);
			mail.setCharset(charSet);
			mail.setSSLOnConnect(true);
			mail.setHostName(hostSMTP);
			mail.setSmtpPort(587);
			
			mail.setAuthentication(hostSMTPid, hostSMTPpwd);
			mail.setStartTLSEnabled(true);
			mail.addTo(email);
			mail.setFrom(fromEmail, fromName, charSet);
			mail.setSubject(subject);
			mail.setHtmlMsg(msg);
			mail.send();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String newPwd() {
		String pw = "";
		for (int i = 0; i < 12; i++) {
			pw += (char) ((Math.random() * 26) + 97);
		}
		return pw;
	}
	
	public static String getPWD() throws Exception {
		SecureRandom rnd = new SecureRandom();
		byte[] temp = new byte[10];
		rnd.nextBytes(temp);
		
		return Byte_to_String(temp);
	}
}
