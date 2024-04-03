package com.openclassrooms.mddapi.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.LoginRegisterDto;
import com.openclassrooms.mddapi.dto.UserRegisterDto;
import com.openclassrooms.mddapi.exception.BadCredentialsCustomException;
import com.openclassrooms.mddapi.exception.UserAlreadyExistsException;
import com.openclassrooms.mddapi.service.IUserService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private IUserService userService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(IUserService userService) {
        this.userService = userService;
    }

    // A transformer ensuite pour que renvoie Register / Login Response : pour token 
    @PostMapping("/register")
    public ResponseEntity<UserRegisterDto> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) throws UserAlreadyExistsException {

        log.info("/auth/register : Trying to register user with email {}", userRegisterDto.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRegisterDto));

    }

    // A modifier avec Spring Security 
    @PostMapping("/login")
    public ResponseEntity<LoginRegisterDto> login(@Valid @RequestBody LoginRegisterDto loginRegisterDto) throws BadCredentialsCustomException {

        log.info("/auth/login : Trying to login user with email {}", loginRegisterDto.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(userService.validateCredentials(loginRegisterDto));

    }
     
}
