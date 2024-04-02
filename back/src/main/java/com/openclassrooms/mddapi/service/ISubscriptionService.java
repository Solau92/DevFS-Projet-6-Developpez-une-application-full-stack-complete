package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.SubscriptionDto;

public interface ISubscriptionService {
    
    /**
     * Saves a subscription.
     * 
     * @param SubscriptionDto the subscription that must be saved
     * @return SubscriptionDto the subscription saved
     */
    SubscriptionDto create(SubscriptionDto subscriptionDto);
}
