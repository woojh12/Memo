package com.jhmemo.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jhmemo.memo.post.domain.Post;
import com.jhmemo.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {
	private PostService postService;
	
	public PostRestController(PostService postService)
	{
		this.postService = postService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createMemo(
			@RequestParam("title") String title
			, @RequestParam("contents") String contents
			, @RequestParam(value="imageFile", required=false) MultipartFile file	// 파일 업로드 필수 아니게 설정
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		Post post = postService.addPost(userId, title, contents, file);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(post != null)
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
