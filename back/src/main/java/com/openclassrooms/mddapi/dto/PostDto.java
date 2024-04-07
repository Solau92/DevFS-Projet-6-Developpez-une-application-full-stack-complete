package com.openclassrooms.mddapi.dto;

import java.time.LocalDate;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;

public class PostDto {

	private Long id;

	private Topic topic;

	private User auteur;

	private String title; 

	private String content;

	private LocalDate createdAt;

	private LocalDate updatedAt;


	public PostDto() {
	}

	public PostDto(Long id, Topic topic, User auteur, String title, String content, LocalDate createdAt, LocalDate updatedAt) {
		this.id = id;
		this.topic = topic;
		this.auteur = auteur;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getAuteur() {
		return this.auteur;
	}

	public void setAuteur(User auteur) {
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

    
}
