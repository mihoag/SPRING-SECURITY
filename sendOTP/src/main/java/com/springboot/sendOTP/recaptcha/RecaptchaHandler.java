package com.springboot.sendOTP.recaptcha;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


public class RecaptchaHandler {

	private String secretkey = "6LdUhC0oAAAAANKi4M-XOmWzGHs-uK58Lke5dBDI";
	private String recaptchaURL = "https://www.google.com/recaptcha/api/siteverify";
    
	public float verify(String recaptchaFormResponse)
	{
		//System.out.println("Recaptcha has called");
		//System.out.println("Recaptcha response");
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("secret",secretkey);
		map.add("response", recaptchaFormResponse);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,header);
		
		////
		RestTemplate restTemplate = new RestTemplate();
		ResponseRecaptcha res = restTemplate.postForObject(recaptchaURL, request, ResponseRecaptcha.class); 
		//ResponseEntity<String> response =  restTemplate.postForEntity(recaptchaURL, request, String.class);
		//System.out.println("Recaptcha response : " + response );
		System.out.println(res.toString());
        return (float) 0.4;
			//return res.getScore();
	}
}
