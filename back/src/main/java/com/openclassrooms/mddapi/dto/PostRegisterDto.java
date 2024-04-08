package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.model.User;

public class PostRegisterDto {
    
    private String topic;

    private String title;

    private String content;

    private UserDto auteur;


    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public UserDto getAuteur() {
        return this.auteur;
    }

    public void setAuteur(UserDto auteur) {
        this.auteur = auteur;
    }


    @Override
    public String toString() {
        return "{" +
            " topic='" + getTopic() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", auteur='" + getAuteur() + "'" +
            "}";
    }


}
