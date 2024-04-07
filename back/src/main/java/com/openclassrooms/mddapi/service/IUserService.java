package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.LoginRegisterDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserRegisterDto;
import com.openclassrooms.mddapi.exception.BadCredentialsCustomException;
import com.openclassrooms.mddapi.exception.UserAlreadyExistsException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;


public interface IUserService {

        UserRegisterDto save(UserRegisterDto userRegisterDto) throws UserAlreadyExistsException;

        String validateCredentials(LoginRegisterDto loginRegisterDto) throws BadCredentialsCustomException;

        UserDto findById(Long id) throws UserNotFoundException;

        UserDto update(UserDto userDto) throws UserNotFoundException;
        
        UserDto findByEmail(String email) throws UserNotFoundException;
    
}
