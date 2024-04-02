package com.openclassrooms.mddapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.mapper.SubscriptionMapper;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;

public class SubscriptionService implements ISubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    private SubscriptionMapper subscriptionMapper;

    private static final Logger log = LoggerFactory.getLogger(SubscriptionService.class);

    public SubscriptionService(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    /**
     * Saves a subscription in database.
     * 
     * @param SubscriptionDto the subscription that must be saved
     * @return SubscriptionDto the subscription saved
     */
    @Override
    public SubscriptionDto create(SubscriptionDto subscriptionDto) {

        log.debug("Trying to save the subscription by user {} to topic {}", subscriptionDto.getAuteur().getId(),
                subscriptionDto.getTopic().getId());

        Subscription subscriptionSaved = subscriptionRepository.save(subscriptionMapper.toEntity(subscriptionDto));

        log.debug("Subscription by user {} to topic {} saved", subscriptionSaved.getAuteur().getId(),
                subscriptionSaved.getTopic().getId());

        return subscriptionMapper.toDto(subscriptionSaved);
    }

}
