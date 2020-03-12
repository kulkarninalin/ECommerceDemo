package com.bookstore.controller;

import java.util.Locale;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.domain.security.PasswordToken;
import com.bookstore.service.UserSecurityService;
import com.bookstore.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSecurityService userSecurityService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/myProfile")
	public String myProfile(Model model) {
		System.out.println("login");
		model.addAttribute("classActiveLogin",true);
		return "myProfile";
	}
	
	@RequestMapping("/login1")
	public String login(Model model) {
		System.out.println("login");
		model.addAttribute("classActiveLogin",true);
		return "myAccount";
	}
	
	@RequestMapping("/forgetPassword")
	public String forgetPass(Model model) { 
		
		model.addAttribute("classActiveFPassword",true);
		return "myAccount";
	}
	
	@RequestMapping("/newUser")
	public String newUser(Model model, 
			Locale locale,
			@RequestParam("token") String token) {
		PasswordToken passtoken=userService.getPasswordToken(token);
		
		if(passtoken == null) {
			String message = "Invalid Token";
			model.addAttribute("message", message);
			return "redirect:/badRequest"; 
		}
		
		
		User user = (User) passtoken.getUser();
		String userName=user.getName();
		
		UserDetails userDetails=userSecurityService.loadUserByUsername(userName);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		System.out.println("newuser");
		model.addAttribute("classActiveEdit",true);
		return "myProfile";
	}
}
