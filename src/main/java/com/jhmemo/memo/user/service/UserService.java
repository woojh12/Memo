package com.jhmemo.memo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhmemo.memo.common.MD5HashingEncoder;
import com.jhmemo.memo.user.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	
	// 생성자가 autowried를 위한 것 하나만 존재하는 경우 autowired 생략가능
	@Autowired	
	public UserService(UserRepository userRepository)  // 생성자를 통해서 생성
	{
		this.userRepository = userRepository;
	}
	
	public int addUser(
			String loginId
			, String password
			, String name
			, String email)
	{
		// 암호화
		String encryptPassword = MD5HashingEncoder.encode(password);
		int count = userRepository.insertUser(loginId, encryptPassword, name, email);
		
		return count;
	}
}
