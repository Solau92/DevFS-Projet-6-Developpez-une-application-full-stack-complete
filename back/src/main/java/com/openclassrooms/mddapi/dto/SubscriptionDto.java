package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;

public class SubscriptionDto {
    
    //TODO : voir si je dois mettre des Dto ou des Entities ?
    private Long id;

    private UserDto user;

    private TopicDto topic;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return this.user;
    }

    public void setUser(UserDto auteur) {
        this.user = auteur;
    }

    public TopicDto getTopic() {
        return this.topic;
    }

    public void setTopic(TopicDto topic) {
        this.topic = topic;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", topic='" + getTopic() + "'" +
            "}";
    }

}
