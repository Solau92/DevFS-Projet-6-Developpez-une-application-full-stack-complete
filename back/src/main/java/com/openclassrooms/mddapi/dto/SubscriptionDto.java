package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;

public class SubscriptionDto {
    
    //TODO : voir si je dois mettre des Dto ou des Entities ?
    private Long id;

    private User auteur;

    private Topic topic;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuteur() {
        return this.auteur;
    }

    public void setAuteur(User auteur) {
        this.auteur = auteur;
    }

    public Topic getTopic() {
        return this.topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

}
