package com.openclassrooms.mddapi.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.LoginDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserRegisterDto;
import com.openclassrooms.mddapi.dto.response.LoginResponse;
import com.openclassrooms.mddapi.exception.BadCredentialsCustomException;
import com.openclassrooms.mddapi.exception.UserAlreadyExistsException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;
import com.openclassrooms.mddapi.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterDto> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) throws UserAlreadyExistsException {

        log.info("(post) /auth/register : Trying to register user with email {}", userRegisterDto.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRegisterDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDto loginRegisterDto) throws BadCredentialsCustomException {

        log.info("(post) /auth/login : Trying to login user with email {}", loginRegisterDto.getEmail());
        
        String token = userService.validateCredentials(loginRegisterDto);

        LoginResponse response = new LoginResponse(token);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("me")
    public ResponseEntity<UserDto> me(Authentication authentication) throws UserNotFoundException {

        String email = authentication.getName();

        log.info("(get) api/auth/me : getting information from user with email {}", email);

        UserDto userDto = userService.findByEmail(email);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
     
}
