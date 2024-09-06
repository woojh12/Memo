package com.jhmemo.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhmemo.memo.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	private UserService userService;
	
	public UserRestController(UserService userService)
	{
		this.userService = userService;
	}
	
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("loginId") String loginId
			, @RequestParam("passowrd") String passwrod
			, @RequestParam("name") String name
			, @RequestParam("email") String email)
	{
		int count = userService.addUser(loginId, passwrod, name, email);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1)
		{
			resultMap.put("result", "success");
		}
		else
		{
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
}
