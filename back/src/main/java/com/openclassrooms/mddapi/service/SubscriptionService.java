package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.exception.SubscriptionAlreadyExistsException;
import com.openclassrooms.mddapi.exception.SubscriptionNotFoundException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;

public interface SubscriptionService {
    
    public SubscriptionDto save(Long topicId, String email) throws UserNotFoundException, SubscriptionAlreadyExistsException;

    public void delete(Long topicId, String email) throws UserNotFoundException, SubscriptionNotFoundException;

}
