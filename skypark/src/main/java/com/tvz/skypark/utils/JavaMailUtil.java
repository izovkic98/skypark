package com.tvz.skypark.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tvz.skypark.model.User;
import com.tvz.skypark.repository.DiscountRepository;

@Component
public class JavaMailUtil {
	
	@Autowired
	DiscountRepository discountRepository;
	
    @Value("${spring.mail.username}")
    private String MY_ACCOUNT_EMAIL;

    @Value("${spring.mail.password}")
    private String MY_PASSWORD;
	
	public void sendMail(User user) throws MessagingException {
		
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Collections.shuffle(list);
		Integer code = 1000*list.get(0) + 100*list.get(1) + 10*list.get(2) + list.get(3);

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
		Message message = prepareMessage(session,myAccountEmail, user.getEmail(), user.getFirstName(), code);

		Transport.send(message);
		
		user.getDiscount().setCode(String.valueOf(code));
		
	}
	
	private Message prepareMessage(Session session, String myAccountEmail, String recipient, String firstName, Integer code ){
		
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("You won free parking !");
			message.setText("Hey there " + firstName 
					+ ",\n\nYour code for free parking is : " + String.valueOf(code) + "\n"
			        + "Keep this code in case there is some sort of inconvenience regarding your free reservation.\n"
			        + "Your loyalty points will reset, so you can start collecting them again to win another prize!"
			        +"\n\nBest regards, \n\nPark Application");
			
			return message;
		} catch (AddressException e) {
			e.printStackTrace(System.out);
		} catch (MessagingException e) {
			e.printStackTrace(System.out);
		}
		
		return null;
	}

}
