package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.exception.SubscriptionAlreadyExistsException;
import com.openclassrooms.mddapi.exception.SubscriptionNotFoundException;
import com.openclassrooms.mddapi.exception.TopicNotFoundException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;

public interface SubscriptionService {
    
    /**
     * Saves a subscription given the user  email and the topic id.
     * 
     * @param topicId
     * @param email
     * @return SubscriptionDto
     * @throws UserNotFoundException
     * @throws SubscriptionAlreadyExistsException
     * @throws TopicNotFoundException 
     */
    public SubscriptionDto save(Long topicId, String email) throws UserNotFoundException, SubscriptionAlreadyExistsException, TopicNotFoundException;

    /**
     * Deletes a subscription given the user email and the topic id.
     * 
     * @param topicId
     * @param email
     * @throws UserNotFoundException
     * @throws SubscriptionNotFoundException
     */
    public void delete(Long topicId, String email) throws UserNotFoundException, SubscriptionNotFoundException;

}
