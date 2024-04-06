package com.openclassrooms.mddapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.LoginRegisterDto;
import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserRegisterDto;
import com.openclassrooms.mddapi.exception.BadCredentialsCustomException;
import com.openclassrooms.mddapi.exception.UserAlreadyExistsException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;
import com.openclassrooms.mddapi.mapper.SubscriptionMapper;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.mapper.UserRegisterMapper;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    private UserRegisterMapper userRegisterMapper;

    private UserMapper userMapper;

    // Version SS
    // private ISubscriptionService subscriptionService;

    private SubscriptionRepository subscriptionRepository;

    private SubscriptionMapper subscriptionMapper;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, UserRegisterMapper userRegisterMapper, UserMapper userMapper
    // , ISubscriptionService subscriptionService
            , SubscriptionRepository subscriptionRepository, 
            SubscriptionMapper subscriptionMapper
            ) {
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
        this.userMapper = userMapper;
        // this.subscriptionService = subscriptionService;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    // TODO : compléter pour vérifier que user pas déjà renseigné en bdd ??
    // et sécurity
    @Override
    public UserRegisterDto save(UserRegisterDto userRegisterDto) throws UserAlreadyExistsException {

        log.info("Trying to save user with email : {}", userRegisterDto.getEmail());

        // Searching if there is already a user with the given email
        Optional<User> optionalUser = userRepository.findByEmail(userRegisterDto.getEmail());

        if (optionalUser.isPresent()) {
            log.error("User with email {} already exists", userRegisterDto.getEmail());
            throw new UserAlreadyExistsException("User with email " + userRegisterDto.getEmail() + " already exists ");
        }

        // TODO with Spring Secu :
        // encrypt password
        // generation of token

        // User doesn't already exist
        User userToSave = userRegisterMapper.toEntity(userRegisterDto);
        userToSave.setCreatedAt(LocalDate.now());
        userToSave.setUpdatedAt(LocalDate.now());

        return userRegisterMapper.toDto(userRepository.save(userToSave));
    }

    @Override
    public LoginRegisterDto validateCredentials(LoginRegisterDto loginRegisterDto)
            throws BadCredentialsCustomException {

        log.info("Trying to validate credential of user with email {}", loginRegisterDto.getEmail());

        Optional<User> optionalUser = userRepository.findByEmail(loginRegisterDto.getEmail());

        if (!optionalUser.isPresent() || !(loginRegisterDto.getPassword().equals(optionalUser.get().getPassword()))) {
            log.error("Invalid email or password");
            throw new BadCredentialsCustomException("Invalid email or password");
        }

        log.info("User with email {} successfully identified", loginRegisterDto.getEmail());

        return loginRegisterDto;
    }

    // @Override
    // public UserDto findById(Long id) throws UserNotFoundException {

    // log.info("Searching user with id {}", id);

    // Optional<User> optionalUser = userRepository.findById(id);

    // if(!optionalUser.isPresent()) {
    // log.error("User with id {} not found", id);
    // throw new UserNotFoundException("User with" + id + "not found");
    // }

    // log.info("User with id {} successfully identified", id);

    // return userMapper.toDto(optionalUser.get());
    // }

    // Version SS
    @Override
    public UserDto findById(Long id) throws UserNotFoundException {

        log.info("Searching user with id {}", id);

        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            log.error("User with id {} not found", id);
            throw new UserNotFoundException("User with" + id + "not found");
        }

        log.info("User with id {} successfully identified", id);

        UserDto userFound = userMapper.toDto(optionalUser.get());

        List<SubscriptionDto> subscriptions = this.getAll(optionalUser.get());

        userFound.setSubscriptions(subscriptions);

        return userFound;
    }

    // Ajouté Version SS
    public List<SubscriptionDto> getAll(User user) throws NumberFormatException, UserNotFoundException {

        log.debug("Searching subscriptions of user with id {}", user.getId());

        // User user = userMapper.toEntity(this.findById(Long.valueOf(id)));

        log.info("List of subscriptions : ");
        List<Subscription> subscriptions = subscriptionRepository.findByUser(user);

        return subscriptionMapper.toDto(subscriptions);

    }

    @Override
    public UserDto update(UserDto userDto) throws UserNotFoundException {

        log.info("Searching user with id {}", userDto.getId());

        Optional<User> optionalUser = userRepository.findById(userDto.getId());

        if (!optionalUser.isPresent()) {
            log.error("User with id {} not found", userDto.getId());
            throw new UserNotFoundException("User with" + userDto.getId() + "not found");
        }

        log.info("User with id {} successfully identified", userDto.getId());

        User userToSave = userMapper.toEntity(userDto);
        userToSave.setCreatedAt(optionalUser.get().getCreatedAt());
        userToSave.setUpdatedAt(LocalDate.now());

        UserDto userSaved = userMapper.toDto(userRepository.save(userToSave));

        return userSaved;
    }

    // @Override
    // public UserRegisterDto findByEmail(String email) throws UserNotFoundException
    // {

    // log.info("Searching user with email {}", email);

    // Optional<User> optionalUser = userRepository.findByEmail(email);

    // if (!optionalUser.isPresent()) {
    // log.error("User with email {} not found", email);
    // throw new UserNotFoundException("User with email " + email + " not found");
    // }

    // User user = optionalUser.get();

    // log.info("User with email {} found", email);

    // return userMapper.toDto(user);
    // }

}
