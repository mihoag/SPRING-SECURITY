package com.springboot.sendOTP.user;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;

@Service
public class UserService {

	@Autowired
	private JavaMailSender javamail;
	
	@Autowired
	private userRepository res;
	
	
	
	 public void generateOneTimePassword(users u) throws UnsupportedEncodingException, MessagingException {
	     String otp = RandomString.make(8);
	     BCryptPasswordEncoder passwordencoder = new BCryptPasswordEncoder();
	     String encodedOtp = passwordencoder.encode(otp);
	     
	     u.setOtp(encodedOtp);
	     u.setOtp_requested_time(new Date());
	     res.save(u);	    
	     sendOTPEmail(u, otp);
	    }
	     
	    public void sendOTPEmail(users u, String OTP) throws MessagingException, UnsupportedEncodingException {
	    	
	    	MimeMessage message = javamail.createMimeMessage();
	    	MimeMessageHelper helper = new MimeMessageHelper(message);
	    	
	    	 helper.setFrom("leminhhoang123456le@gmail.com", "Shopme Support");
	    	 helper.setTo(u.getEmail());
	    	     
	    	    String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
	    	     
	    	    String content = "<p>Hello " + u.getEmail() + "</p>"
	    	            + "<p>For security reason, you're required to use the following "
	    	            + "One Time Password to login:</p>"
	    	            + "<p><b>" + OTP + "</b></p>"
	    	            + "<br>"
	    	            + "<p>Note: this OTP is set to expire in 5 minutes.</p>";
	    	     
	    	    helper.setSubject(subject);
	    	     
	    	    helper.setText(content, true);
	    	     
	    	   javamail.send(message);    
	    	
	    }
	 
	    public void clearOTP(users u) {
	    	u.setOtp(null);
	    	u.setOtp_requested_time(null);
	    	res.save(u);
	    }  
	    public users selectByEmail(String email)
	    {
	    	return res.selectByEmail(email);
	    }
}
