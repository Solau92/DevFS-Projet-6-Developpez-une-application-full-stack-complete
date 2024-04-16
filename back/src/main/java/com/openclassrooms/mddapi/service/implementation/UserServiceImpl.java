package com.openclassrooms.mddapi.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.LoginDto;
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
import com.openclassrooms.mddapi.service.UserService;
import com.openclassrooms.mddapi.service.JWTService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private SubscriptionRepository subscriptionRepository;

    private JWTService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserRegisterMapper userRegisterMapper;
    private UserMapper userMapper;
    private SubscriptionMapper subscriptionMapper;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository,
            SubscriptionRepository subscriptionRepository,
            JWTService jwtService,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserRegisterMapper userRegisterMapper,
            UserMapper userMapper,
            SubscriptionMapper subscriptionMapper) {
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
        this.userMapper = userMapper;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserRegisterDto save(UserRegisterDto userRegisterDto) throws UserAlreadyExistsException {

        log.debug("Trying to save user with email : {}", userRegisterDto.getEmail());

        // Searching if there is already a user with the given email
        Optional<User> optionalUser = userRepository.findByEmail(userRegisterDto.getEmail());

        if (optionalUser.isPresent()) {
            log.error("User with email {} already exists", userRegisterDto.getEmail());
            throw new UserAlreadyExistsException("User with email " + userRegisterDto.getEmail() + " already exists ");
        }

        // User doesn't already exist
        User userToSave = userRegisterMapper.toEntity(userRegisterDto);
        userToSave.setCreatedAt(LocalDate.now());
        userToSave.setUpdatedAt(LocalDate.now());
        userToSave.setPassword(this.bCryptPasswordEncoder.encode(userRegisterDto.getPassword()));

        return userRegisterMapper.toDto(userRepository.save(userToSave));
    }

    @Override
    public String validateCredentials(LoginDto loginRegisterDto)
            throws BadCredentialsCustomException {

        log.debug("Trying to validate credential of user with email {}", loginRegisterDto.getEmail());

        Optional<User> optionalUser = userRepository.findByEmail(loginRegisterDto.getEmail());

        if (!optionalUser.isPresent()
                || !this.bCryptPasswordEncoder.matches(loginRegisterDto.getPassword(),
                        optionalUser.get().getPassword())) {
            log.error("Invalid email or password");
            throw new BadCredentialsCustomException("Invalid email or password");
        }

        log.debug("User with email {} successfully identified", loginRegisterDto.getEmail());

        String token = jwtService.generateToken(loginRegisterDto);

        return token;
    }

    @Override
    public UserDto findById(Long id) throws UserNotFoundException {

        log.debug("Searching user with id {}", id);

        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            log.error("User with id {} not found", id);
            throw new UserNotFoundException("User with" + id + "not found");
        }

        log.debug("User with id {} successfully identified", id);

        UserDto userFound = userMapper.toDto(optionalUser.get());

        List<SubscriptionDto> subscriptions = this.getAllSubscriptions(optionalUser.get());

        userFound.setSubscriptions(subscriptions);

        return userFound;
    }

    public List<SubscriptionDto> getAllSubscriptions(User user) throws NumberFormatException {

        log.debug("Searching subscriptions of user with id {}", user.getId());

        List<Subscription> subscriptions = subscriptionRepository.findByUser(user);

        return subscriptionMapper.toDto(subscriptions);
    }

    @Override
    public UserDto update(UserDto userDto) throws UserNotFoundException {

        log.debug("Searching user with id {}", userDto.getId());

        Optional<User> optionalUser = userRepository.findById(userDto.getId());

        if (!optionalUser.isPresent()) {
            log.error("User with id {} not found", userDto.getId());
            throw new UserNotFoundException("User with" + userDto.getId() + "not found");
        }

        log.debug("User with id {} successfully identified", userDto.getId());

        User userToSave = userMapper.toEntity(userDto);
        userToSave.setCreatedAt(optionalUser.get().getCreatedAt());
        userToSave.setUpdatedAt(LocalDate.now());

        // Vérifier si le password a été modifié
        if (userDto.getPassword().equals(optionalUser.get().getPassword())) {
            userToSave.setPassword(optionalUser.get().getPassword());
        } else {
            userToSave.setPassword(this.bCryptPasswordEncoder.encode(userDto.getPassword()));
        }

        UserDto userSaved = userMapper.toDto(userRepository.save(userToSave));

        return userSaved;
    }

    @Override
    public UserDto findByEmail(String email) throws UserNotFoundException {

        log.debug("Searching user with email {}", email);

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (!optionalUser.isPresent()) {
            log.error("User with email {} not found", email);
            throw new UserNotFoundException("User with email " + email + " not found");
        }

        User user = optionalUser.get();

        log.debug("User with email {} successfully identified", email);

        UserDto userFound = userMapper.toDto(user);

        List<SubscriptionDto> subscriptions = this.getAllSubscriptions(user);

        userFound.setSubscriptions(subscriptions);

        return userFound;
    }

}
