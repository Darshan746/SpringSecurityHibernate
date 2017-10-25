package com.darshan.secure;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String getPage()
	{
		return "home";
	}
	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("User name is"+ user.getUsername()+" "+user.getPassword());
	  ModelAndView model = new ModelAndView();
	  
	  model.addObject("title", "Spring Security Login Form - Database Authentication");
	  model.addObject("message", "This page is for ROLE_ADMIN only!");
	  model.setViewName("admin");
	  return model;

	}
	
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView adminPage1() {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("User name is"+ user.getUsername()+" "+user.getPassword());
	  ModelAndView model = new ModelAndView();
	  
	  model.addObject("title", "Spring Security Login Form - Database Authentication");
	  model.addObject("message", "This page is for ROLE_ADMIN only!");
	  model.setViewName("403");
	  return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
System.out.println(error+" error msg");
		
	  ModelAndView model = new ModelAndView();
	  if (error != null) {
		model.addObject("error", "Invalid username and password!");
	  }

	  if (logout != null) {
		model.addObject("msg", "You've been logged out successfully.");
	  }
	  model.setViewName("login");

	  return model;

	}
}
