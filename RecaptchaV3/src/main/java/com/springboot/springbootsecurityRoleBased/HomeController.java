package com.springboot.springbootsecurityRoleBased;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
  @Autowired
  private IproductService i;
  
  @RequestMapping(value = "/", method = RequestMethod.GET )
  public String home(Model model)
  {
	  ArrayList<product> arr=  (ArrayList<product>) i.FindAll();
	  model.addAttribute("listProduct", arr);
	  return "index";
  }
  
  @RequestMapping(value = "/new" )
  public String newProduct(Model model)
  {
	  model.addAttribute("product", new product());
	  return "formNew";
  }
  
  @RequestMapping(value = "/save",method = RequestMethod.POST)
  public String listForm(@ModelAttribute("product") product p)
  {
	  i.save(p);
	  return "redirect:/";
  }
  
  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public String editProduct(@PathVariable Long id, Model model)
  {
	  product p = i.FindOne(id);
	  model.addAttribute("product",p);
	  return "edit_form";
  }
  
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public String deleteProduct(@PathVariable Long id)
  {
	  i.delete(id);
	  return "redirect:/";
  }
  
  @RequestMapping(value = "/successLogout" , method = RequestMethod.GET)
  public String logout()
  {
	  return "successLogout";
  }
  
  @PostMapping("/successlogin")
  public String successLogin()
  {
	  System.out.println("Login successfully");
	  return "successlogin";
  }
  
}
