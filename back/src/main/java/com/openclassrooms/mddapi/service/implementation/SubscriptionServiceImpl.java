package com.openclassrooms.mddapi.service.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.exception.SubscriptionAlreadyExistsException;
import com.openclassrooms.mddapi.exception.SubscriptionNotFoundException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;
import com.openclassrooms.mddapi.mapper.SubscriptionMapper;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.service.SubscriptionService;
import com.openclassrooms.mddapi.service.TopicService;
import com.openclassrooms.mddapi.service.UserService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    private UserService userService;
    private TopicService topicService;

    private SubscriptionMapper subscriptionMapper;

    private static final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
            UserServiceImpl userService,
            TopicService topicService,
            SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
        this.userService = userService;
        this.topicService = topicService;
    }

    /**
     * Saves in database a subscription given the user email and the topic id.
     * 
     * @param topicId
     * @param email
     * @return SubscriptionDto
     * @throws UserNotFoundException
     * @throws SubscriptionAlreadyExistsException
     */
    @Override
    public SubscriptionDto save(Long topicId, String email)
            throws UserNotFoundException, SubscriptionAlreadyExistsException {

        log.debug("Trying to save the subscription by user with email {} to topic with id {}", email, topicId);

        // Chercher le user
        UserDto userDto = userService.findByEmail(email);

        /// Chercher si la souscription existe déjà

        Optional<Subscription> optionalSubscription = subscriptionRepository.findByUserIdAndTopicId(userDto.getId(),
                topicId);

        if (optionalSubscription.isPresent()) {
            log.error("Subscription by user with email {} to topic with id {} already exists", email, topicId);
            throw new SubscriptionAlreadyExistsException(
                    "Subscription by user with email " + email + " to topic with id " + topicId + " already exists");
        }

        // Chercher le topic
        TopicDto topicDto = topicService.findById(topicId);

        // Créer la subscription à saver
        SubscriptionDto subscriptionDtoToSave = new SubscriptionDto();
        subscriptionDtoToSave.setUser(userDto);
        subscriptionDtoToSave.setTopic(topicDto);

        Subscription subscriptionSaved = subscriptionRepository
                .save(subscriptionMapper.toEntity(subscriptionDtoToSave));

        log.debug("Subscription by user {} to topic {} saved", subscriptionSaved.getUser().getId(),
                subscriptionSaved.getTopic().getId());

        return subscriptionMapper.toDto(subscriptionSaved);
    }

    /**
     * Deletes in database a subscription given the user email and the topic id.
     * 
     * @param topicId
     * @param email
     * @throws UserNotFoundException
     * @throws SubscriptionNotFoundException
     */
    @Override
    public void delete(Long topicId, String email) throws UserNotFoundException, SubscriptionNotFoundException {

        log.debug("Trying to delete the subscription by user with email {} to topic with id {}", email, topicId);

        // Chercher le user Id
        Long userId = userService.findByEmail(email).getId();

        Optional<Subscription> optionalSubscription = subscriptionRepository.findByUserIdAndTopicId(userId, topicId);

        if (!optionalSubscription.isPresent()) {
            log.error("Subscription by user with email {} to topic with id {} doesn't exist", email, topicId);
            throw new SubscriptionNotFoundException(
                    "Subscription by user with email " + email + " to topic with id " + topicId + " doesn't exist");
        }

        Subscription subscriptionToDelete = optionalSubscription.get();

        subscriptionRepository.delete(subscriptionToDelete);

        log.debug("Subscription by user {with email {} to topic with id {} deleted", email, topicId);
    }

}
