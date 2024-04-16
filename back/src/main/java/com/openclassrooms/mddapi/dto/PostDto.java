package com.openclassrooms.mddapi.dto;

import java.time.LocalDate;
import java.util.List;

public class PostDto {

	private Long id;
	private TopicDto topic;
	private UserDto auteur;
	private String title; 
	private String content;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private List<CommentDto> comments;

	public PostDto() {
	}

	public PostDto(Long id, TopicDto topic, UserDto auteur, String title, String content, LocalDate createdAt, LocalDate updatedAt, List<CommentDto> comments) {
		this.id = id;
		this.topic = topic;
		this.auteur = auteur;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.comments = comments;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TopicDto getTopic() {
		return this.topic;
	}

	public void setTopic(TopicDto topic) {
		this.topic = topic;
	}

	public UserDto getAuteur() {
		return this.auteur;
	}

	public void setAuteur(UserDto auteur) {
		this.auteur = auteur;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<CommentDto> getComments() {
		return this.comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
    
}
