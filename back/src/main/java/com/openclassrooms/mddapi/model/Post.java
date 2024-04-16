package com.openclassrooms.mddapi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "topic_id")
	private Topic topic;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User auteur;

	@Column
	@NotBlank
	private String title;

	@Column(columnDefinition="TEXT")
	@NotBlank
	private String content;

	@Column(name = "created_at")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate createdAt;

	@Column(name = "updated_at")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Topic getTopic() {
		return topic;
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
