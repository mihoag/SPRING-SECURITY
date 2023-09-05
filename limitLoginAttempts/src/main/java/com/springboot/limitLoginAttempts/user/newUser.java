package com.springboot.limitLoginAttempts.user;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "newUser")
public class newUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Column(name = "email")
	private String email;
     
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;
	
	
	@Column(name = "password")
	private String password ;
	
	@Column(name = "photos")
	private String photos;
	
	@Column(name = "failed_attempt")
    private int failedAttempt;
	
	@Column(name = "account_non_locked")
    private boolean accountNonLocked;
	
	@Column(name = "lock_time")
	private Date lockTime;

	public newUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public newUser(Integer id, String email, boolean enabled, String first_name, String last_name, String password,
			String photos, int failedAttempt, boolean accountNonLocked, Date lockTime) {
		super();
		this.id = id;
		this.email = email;
		this.enabled = enabled;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.photos = photos;
		this.failedAttempt = failedAttempt;
		this.accountNonLocked = accountNonLocked;
		this.lockTime = lockTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public int getFailedAttempt() {
		return failedAttempt;
	}

	public void setFailedAttempt(int failedAttempt) {
		this.failedAttempt = failedAttempt;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
}
