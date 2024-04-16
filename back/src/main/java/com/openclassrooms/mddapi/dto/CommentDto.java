package com.openclassrooms.mddapi.dto;

import java.time.LocalDate;

public class CommentDto {
    
  	private Long id;

	private UserDto auteur;

	private PostDto post;

	private String content;
	
	private LocalDate createdAt;

	private LocalDate updatedAt;


    public CommentDto() {
    }

    public CommentDto(Long id, UserDto auteur, PostDto post, String content, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.auteur = auteur;
        this.post = post;
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

    public UserDto getAuteur() {
        return this.auteur;
    }

    public void setAuteur(UserDto auteur) {
        this.auteur = auteur;
    }

    public PostDto getPost() {
        return this.post;
    }

    public void setPost(PostDto post) {
        this.post = post;
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
 
}
