package com.openclassrooms.mddapi.dto;

import java.time.LocalDate;
import java.util.List;

public class UserDto {

    private Long id;

    private String username;

    private String email;

    private String password;

    private LocalDate createdAt;
    
    private LocalDate updatedAt;

    // Version SS
    private List<SubscriptionDto> subscriptions;
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<SubscriptionDto> getSubscriptions() {
        return this.subscriptions;
    }

    public void setSubscriptions(List<SubscriptionDto> subscriptions) {
        this.subscriptions = subscriptions;
    }

}
