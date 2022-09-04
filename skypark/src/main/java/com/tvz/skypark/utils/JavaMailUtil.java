package com.tvz.skypark.utils;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JavaMailUtil {
	
    @Value("${spring.mail.username}")
    private String MY_ACCOUNT_EMAIL;

    @Value("${spring.mail.password}")
    private String MY_PASSWORD;
	
	public void sendMail(String recipient, String firstName) throws MessagingException {

		System.out.println("Preparing to send email");
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.ssl.trust", "*");	
		
		String myAccountEmail = MY_ACCOUNT_EMAIL;
		String mypassword = MY_PASSWORD;
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(myAccountEmail, mypassword);
			}
			
		});
		Message message = prepareMessage(session,myAccountEmail,recipient, firstName);

		Transport.send(message);
		
		System.out.println("Message sent succesfully");
	}
	
	private Message prepareMessage(Session session, String myAccountEmail,String recipient, String firstName ){
		String uuid = UUID.randomUUID().toString();
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("You won free parking !");
			message.setText("Hey there " + firstName 
					+ ",\n\nYour code for free parking is : " + uuid + "\n"
			        + "Keep this code in case there is some sort of inconvenience regarding your free reservation.\n"
			        + "Your loyalty points will reset, so you can start collecting them again to win another prize!"
			        +"\n\nBest regards, \n\nPark Application");
			
			return message;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
