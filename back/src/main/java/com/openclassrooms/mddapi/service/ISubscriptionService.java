package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.exception.UserNotFoundException;

public interface ISubscriptionService {
    
    /**
     * Saves a subscription.
     * 
     * @param SubscriptionDto the subscription that must be saved
     * @return SubscriptionDto the subscription saved
     * @throws UserNotFoundException 
     */
    // SubscriptionDto create(SubscriptionDto subscriptionDto);

    public SubscriptionDto create(Long topicId, String email) throws UserNotFoundException;

    public void delete(Long topicId, String email) throws UserNotFoundException;

    List<SubscriptionDto> getAll(String id) throws NumberFormatException, UserNotFoundException;
}
