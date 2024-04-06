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
     */
    SubscriptionDto create(SubscriptionDto subscriptionDto);

    List<SubscriptionDto> getAll(String id) throws NumberFormatException, UserNotFoundException;
}
