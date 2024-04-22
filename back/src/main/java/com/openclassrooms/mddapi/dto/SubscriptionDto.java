package com.openclassrooms.mddapi.dto;

public class SubscriptionDto {
    
    private Long id;
    private UserDto user;
    private TopicDto topic;

    public SubscriptionDto() {
    }

    public SubscriptionDto(Long id, UserDto user, TopicDto topic) {
        this.id = id;
        this.user = user;
        this.topic = topic;
    }

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
