package com.jhmemo.memo.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request
			, HttpServletResponse response
			, Object handle) throws IOException
	{
		HttpSession session = request.getSession();
		
		// 값이 있는지 없는지 확인하기 위해 Integer타입 사용
		Integer userId = (Integer)session.getAttribute("userId");
		
		// PostMapping을 제외한 url 얻어오기
		String uri = request.getRequestURI();
		
		// 로그인이 안된 상태에서 메모리스트, 메모 입력화면, ... 게시글과 관련된 페이지로 접근을 막고
		// 로그인 페이지로 이동시킨다.
		
		if(userId == null)
		{
			// post로 시작하는 path로 요청
			if(uri.startsWith("/post"))
			{
				// redirect
				response.sendRedirect("/user/login-view");
				
				return false;
			}		
		}
		else // 로그인 된 상태
		{
			// 사용자와 관련된 페이지로 접근을 막고
			// 리스트 페이지로 이동
			if(uri.startsWith("/user"))
			{
				response.sendRedirect("/post/list-view");
				return false;
			}
		}
		return true;
	}
}
