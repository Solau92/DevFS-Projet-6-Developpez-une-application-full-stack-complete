package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.exception.PostNotFoundException;
import com.openclassrooms.mddapi.exception.SubscriptionAlreadyExistsException;
import com.openclassrooms.mddapi.exception.SubscriptionNotFoundException;
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

    private IUserService userService;
    private ITopicService topicService;

    // Version SS
    // private UserService userService;
    // private UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger(SubscriptionService.class);

    public SubscriptionService(SubscriptionRepository subscriptionRepository,
            SubscriptionMapper subscriptionMapper,
            UserService userService, ITopicService topicService /*,
            UserMapper userMapper*/) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
        this.userService = userService;
        this.topicService = topicService;
        // this.userMapper = userMapper;
    }

    /**
     * Saves a subscription in database.
     * 
     * @param SubscriptionDto the subscription that must be saved
     * @return SubscriptionDto the subscription saved
     * @throws UserNotFoundException 
     * @throws SubscriptionNotFoundException 
     */
    // @Override
    // public SubscriptionDto create(SubscriptionDto subscriptionDto) {

    //     log.debug("Trying to save the subscription by user {} to topic {}", subscriptionDto.getUser().getId(),
    //             subscriptionDto.getTopic().getId());

    //     Subscription subscriptionSaved = subscriptionRepository.save(subscriptionMapper.toEntity(subscriptionDto));

    //     log.debug("Subscription by user {} to topic {} saved", subscriptionSaved.getUser().getId(),
    //             subscriptionSaved.getTopic().getId());

    //     return subscriptionMapper.toDto(subscriptionSaved);
    // }

    @Override
    public SubscriptionDto create(Long topicId, String email) throws UserNotFoundException, SubscriptionAlreadyExistsException {

        log.debug("Trying to save the subscription by user with email {} to topic with id {}", email, topicId);

        // Chercher le user 
        UserDto userDto = userService.findByEmail(email);

        /// Chercher si la souscription existe déjà 

        Optional<Subscription> optionalSubscription = subscriptionRepository.findByUserIdAndTopicId(userDto.getId(), topicId);
            
        if(optionalSubscription.isPresent()) {
            log.error("Subscription by user with email {} to topic with id {} already exists", email, topicId);
            throw new SubscriptionAlreadyExistsException("Subscription by user with email " +  email + " to topic with id " + topicId + " already exists");
        }

        // Chercher le topic 
        TopicDto topicDto = topicService.getTopicById(topicId);

        // Créer la subscription à saver 

        SubscriptionDto subscriptionDtoToSave = new SubscriptionDto();
        subscriptionDtoToSave.setUser(userDto);
        subscriptionDtoToSave.setTopic(topicDto);

        Subscription subscriptionSaved = subscriptionRepository.save(subscriptionMapper.toEntity(subscriptionDtoToSave));

        log.debug("Subscription by user {} to topic {} saved", subscriptionSaved.getUser().getId(),
                subscriptionSaved.getTopic().getId());

        return subscriptionMapper.toDto(subscriptionSaved);
    }


    @Override
    public void delete(Long topicId, String email) throws UserNotFoundException, SubscriptionNotFoundException {

        log.debug("Trying to delete the subscription by user with email {} to topic with id {}", email, topicId);

        // Chercher le user Id
        Long userId = userService.findByEmail(email).getId();

        Optional<Subscription> optionalSubscription = subscriptionRepository.findByUserIdAndTopicId(userId, topicId);

        if (!optionalSubscription.isPresent()) {
            log.error("Subscription by user with email {} to topic with id {} doesn't exist", email, topicId);
            throw new SubscriptionNotFoundException("Subscription by user with email " + email + " to topic with id " + topicId + " doesn't exist");
        }

        Subscription subscriptionToDelete = optionalSubscription.get();

        subscriptionRepository.delete(subscriptionToDelete);

        log.debug("Subscription by user {with email {} to topic with id {} deleted", email, topicId);

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
