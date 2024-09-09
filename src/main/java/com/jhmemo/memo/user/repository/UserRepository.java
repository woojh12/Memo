package com.jhmemo.memo.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jhmemo.memo.user.domain.User;
@Mapper
public interface UserRepository {
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("email") String email);
	
	public int selectCountByLoginId(@Param("loginId") String loginId);
	
	public User selectUser(@Param("loginId") String loginId, @Param("password") String password);
}
