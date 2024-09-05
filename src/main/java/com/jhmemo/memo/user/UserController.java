package com.jhmemo.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@GetMapping("/join-view")
	public String inputJoin()
	{
		return "user/join";
	}
	
	@GetMapping("/login-view")
	public String inputLogin()
	{
		return "user/login";
	}
}
