package com.jhmemo.memo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jhmemo.memo.common.HashingEncoder;
import com.jhmemo.memo.common.MD5HashingEncoder;
import com.jhmemo.memo.user.domain.User;
import com.jhmemo.memo.user.repository.UserRepository;

@Service
public class UserService {
	// IoC : 제어의 역전
	// DI(Dependency injection) : 의존성 주입
	private UserRepository userRepository;
	
	private HashingEncoder encoder;
	
	// 생성자가 autowired를 위한 것 하나만 존재하는 경우 autowired 생략가능
	@Autowired	
	public UserService(UserRepository userRepository, @Qualifier("sha256Hashing") HashingEncoder encoder)  // 생성자를 통해서 생성
	{
		this.userRepository = userRepository;
		this.encoder = encoder;
	}
	
	public int addUser(
			String loginId
			, String password
			, String name
			, String email)
	{
		// 암호화
//		String encryptPassword = MD5HashingEncoder.encode(password);
//		HashingEncoder encoder = new MD5HashingEncoder();
		String encryptPassword = encoder.encode(password);
		
		int count = userRepository.insertUser(loginId, encryptPassword, name, email);
		
		return count;
	}
	
	public boolean isDuplicateId(String loginId)
	{
		int count = userRepository.selectCountByLoginId(loginId);
		
		if(count == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public User getUser(String loginId, String password)
	{
		String encryptPassword = MD5HashingEncoder.encode(password);
		return userRepository.selectUser(loginId, encryptPassword);
	}
}
