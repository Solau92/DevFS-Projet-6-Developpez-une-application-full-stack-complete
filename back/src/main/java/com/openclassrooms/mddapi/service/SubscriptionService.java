package com.openclassrooms.mddapi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.exception.UserNotFoundException;
import com.openclassrooms.mddapi.mapper.SubscriptionMapper;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;

@Service
public class SubscriptionService implements ISubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    private SubscriptionMapper subscriptionMapper;

    // Version SS
    // private UserService userService;
    // private UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger(SubscriptionService.class);

    public SubscriptionService(SubscriptionRepository subscriptionRepository,
            SubscriptionMapper subscriptionMapper/*
                                                  * ,
                                                  * UserService userService,
                                                  * UserMapper userMapper
                                                  */) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
        // this.userService = userService;
        // this.userMapper = userMapper;
    }

    /**
     * Saves a subscription in database.
     * 
     * @param SubscriptionDto the subscription that must be saved
     * @return SubscriptionDto the subscription saved
     */
    @Override
    public SubscriptionDto create(SubscriptionDto subscriptionDto) {

        log.debug("Trying to save the subscription by user {} to topic {}", subscriptionDto.getUser().getId(),
                subscriptionDto.getTopic().getId());

        Subscription subscriptionSaved = subscriptionRepository.save(subscriptionMapper.toEntity(subscriptionDto));

        log.debug("Subscription by user {} to topic {} saved", subscriptionSaved.getUser().getId(),
                subscriptionSaved.getTopic().getId());

        return subscriptionMapper.toDto(subscriptionSaved);
    }

    // Version SS removed
    @Override
    public List<SubscriptionDto> getAll(String id) throws NumberFormatException, UserNotFoundException {

        // log.debug("Searching subscriptions of user with id {}", id);

        // User user = userMapper.toEntity(userService.findById(Long.valueOf(id)));

        // return subscriptionMapper.toDto(subscriptionRepository.findByUser(user));

        return null;
    }

}
