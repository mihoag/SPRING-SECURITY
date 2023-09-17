package com.springboot.springbootsecurityRoleBased;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class otpController {
      @RequestMapping(value = "/otp_login" , method = RequestMethod.POST)
      public String otpForm()
      {
    	  return "otpform";
      }
}
