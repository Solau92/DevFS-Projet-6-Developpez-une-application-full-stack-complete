package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.LoginDto;
import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserRegisterDto;
import com.openclassrooms.mddapi.exception.BadCredentialsCustomException;
import com.openclassrooms.mddapi.exception.UserAlreadyExistsException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;
import com.openclassrooms.mddapi.model.User;

public interface UserService {

        /**
         * Saves the given user. 
         * 
         * @param userRegisterDto
         * @return UserRegisterDto the user saved
         * @throws UserAlreadyExistsException
         */
        public UserRegisterDto save(UserRegisterDto userRegisterDto) throws UserAlreadyExistsException;

        /**
         * Checks if the user credentials are corresponding to a registered user. 
         * 
         * @param loginRegisterDto
         * @return a token if the user is a registered user
         * @throws BadCredentialsCustomException if the credentials are invalid
         */
        public String validateCredentials(LoginDto loginRegisterDto) throws BadCredentialsCustomException;

        /**
         * Searches a user given his id. 
         * 
         * @param id 
         * @return UserDto, the user found
         * @throws UserNotFoundException
         */
        public UserDto findById(Long id) throws UserNotFoundException;

        /**
         * Searches all subscriptions of a given user.
         * @param user
         * @return List<SubscriptionDto>
         * @throws NumberFormatException
         */
        List<SubscriptionDto> getAllSubscriptions(User user) throws NumberFormatException;
        
        /**
         * Updates a user.
         * 
         * @param userDto
         * @return UserDto, the user updated
         * @throws UserNotFoundException
         */
        public UserDto update(UserDto userDto) throws UserNotFoundException;
        
        /**
         * Searches a user given an email. 
         * 
         * @param email
         * @return UserDto, the user found
         * @throws UserNotFoundException
         */
        public UserDto findByEmail(String email) throws UserNotFoundException;
    
}
