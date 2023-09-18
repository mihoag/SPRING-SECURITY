package com.springboot.sendOTP.user;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")

public class users {

	private static final long OTP_VALID_DURATION = 5*60*1000;  // 5 minutes
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "one_time_password", length = 64)
	private String otp;
	
	@Column(name = "otp_requested_time")
	private Date otp_requested_time;

	public users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public users(int id, String email, String password, String otp, Date otp_requested_time) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.otp = otp;
		this.otp_requested_time = otp_requested_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		//return password;
		if(isOTPrequired())
		{
			return otp;
		}
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getOtp_requested_time() {
		return otp_requested_time;
	}

	public void setOtp_requested_time(Date otp_requested_time) {
		this.otp_requested_time = otp_requested_time;
	}

	
	public  boolean isOTPrequired() {
		if(otp == null)
		{
			return false;
		}
		
		long currentTime = System.currentTimeMillis();
		if(otp_requested_time.getTime() + OTP_VALID_DURATION <  currentTime)
		{
			return false;
		}
	
		return true;
	}
}
