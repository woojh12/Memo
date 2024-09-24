package com.jhmemo.memo.post.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder=true)	// toBuilder : 객체를 수정해서 가져오는 것
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="post")
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId")
	private int userId;
	private String title;
	private String contents;
	
	@Column(name="imagePath")
	private String imagePath;
	
	@CreationTimestamp
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
}
