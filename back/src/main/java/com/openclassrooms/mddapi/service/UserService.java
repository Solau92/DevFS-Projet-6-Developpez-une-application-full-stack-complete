package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.LoginDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserRegisterDto;
import com.openclassrooms.mddapi.exception.BadCredentialsCustomException;
import com.openclassrooms.mddapi.exception.UserAlreadyExistsException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;

public interface UserService {

        public UserRegisterDto save(UserRegisterDto userRegisterDto) throws UserAlreadyExistsException;

        public String validateCredentials(LoginDto loginRegisterDto) throws BadCredentialsCustomException;

        public UserDto findById(Long id) throws UserNotFoundException;

        public UserDto update(UserDto userDto) throws UserNotFoundException;
        
        public UserDto findByEmail(String email) throws UserNotFoundException;
    
}
