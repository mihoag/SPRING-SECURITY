package com.springboot.springbootsecurityRoleBased;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseRecaptcha {
    private boolean success;
    private float score;
    private String action;
    private String challenge_ts;
    private String hostname;
    
    @JsonProperty("error-codes")
    private String[] errorcode;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getChallenge_ts() {
		return challenge_ts;
	}

	public void setChallenge_ts(String challenge_ts) {
		this.challenge_ts = challenge_ts;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String[] getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String[] errorcode) {
		this.errorcode = errorcode;
	}

	@Override
	public String toString() {
		return "ResponseRecaptcha [success=" + success + ", score=" + score + ", action=" + action + ", challenge_ts="
				+ challenge_ts + ", hostname=" + hostname + ", errorcode=" + Arrays.toString(errorcode) + "]";
	}
	
	
}
