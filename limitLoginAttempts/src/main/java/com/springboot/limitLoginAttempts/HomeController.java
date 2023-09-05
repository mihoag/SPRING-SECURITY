package com.springboot.limitLoginAttempts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
 
	/*@RequestMapping(value = "/login1", method = RequestMethod.GET)
	public String login1(Model model, @RequestParam(name = "msg", defaultValue = "Please login") String msg)
	{
		//System.out.println("OK");
		System.out.println(msg);
		model.addAttribute("msg", msg);
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, @RequestParam(name = "msg", defaultValue = "Please login") String msg)
	{
		System.out.println("OK");
		System.out.println(msg);
		model.addAttribute("msg", msg);
		return "login";
	}*/
}
