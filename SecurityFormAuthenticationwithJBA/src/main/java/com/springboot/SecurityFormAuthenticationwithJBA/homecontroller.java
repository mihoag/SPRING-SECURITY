package com.springboot.SecurityFormAuthenticationwithJBA;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class homecontroller {

	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("name","Hoang");
		System.out.println("hello");
		return "index";
    }
}
