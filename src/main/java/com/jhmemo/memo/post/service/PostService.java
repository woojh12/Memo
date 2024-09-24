package com.jhmemo.memo.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jhmemo.memo.common.FileManager;
import com.jhmemo.memo.post.domain.Post;
import com.jhmemo.memo.post.repository.PostRepository;

@Service
public class PostService {
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository)
	{
		this.postRepository = postRepository;
	}
	
	public Post addPost(int userId, String title, String contents, MultipartFile file)
	{
		String urlPath = FileManager.saveFile(userId, file);
		
		Post post = Post.builder()
					.userId(userId)
					.title(title)
					.contents(contents)
					.imagePath(urlPath)
					.build();
		
		Post result = postRepository.save(post);
		
		return result;
	}
	
	public List<Post> getPostList(int userId)
	{ 
		return postRepository.findByUserIdOrderByIdDesc(userId);
	}
	
	public Post getPost(int id)
	{
		Optional<Post> optionalPost = postRepository.findById(id);
		
		Post post = optionalPost.orElse(null);
		
		return post;
	}
	
	public Post updatePost(int id, String title, String contents)
	{
		Optional<Post> optionalPost = postRepository.findById(id);
		
		Post post = optionalPost.orElse(null);
		
		if(post != null)
		{
			Post updatePost = post.toBuilder()
				.title(title)
				.contents(contents)
				.build();
			return postRepository.save(updatePost);
		}
		else
		{
			return null;
		}
	}
	
	public boolean deletePost(int id)
	{
		Optional<Post> optionalPost = postRepository.findById(id);
		
		Post post = optionalPost.orElse(null);
		
		if(post != null)
		{
			// 폴더 안의 삭제 이미지 경로 추출
			FileManager.removeFile(post.getImagePath());
			
			postRepository.delete(post);
			return true;
		}
		else
		{
			return false;
		}
	}
}
