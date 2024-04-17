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

    /**
     * Registers the given user. 
     * 
     * @param userRegisterDto
     * @return ResponseEntity<UserRegisterDto> with status created, containing the registered user
     * @throws UserAlreadyExistsException
     */
    @PostMapping("/register")
    public ResponseEntity<UserRegisterDto> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) throws UserAlreadyExistsException {

        log.info("(post) /auth/register : Trying to register user with email {}", userRegisterDto.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRegisterDto));
    }

    /**
     * Login method.
     * 
     * @param loginDto
     * @return ResponseEntity<LoginResponse> with status ok, containing the token
     * @throws BadCredentialsCustomException
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDto loginDto) throws BadCredentialsCustomException {

        log.info("(post) /auth/login : Trying to login user with email {}", loginDto.getEmail());
        
        String token = userService.validateCredentials(loginDto);

        LoginResponse response = new LoginResponse(token);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Gets the logged user. 
     * 
     * @param authentication
     * @return ResponseEntity<UserDto> with status ok, containg the logged user
     * @throws UserNotFoundException
     */
    @GetMapping("me")
    public ResponseEntity<UserDto> me(Authentication authentication) throws UserNotFoundException {

        String email = authentication.getName();

        log.info("(get) api/auth/me : getting information from user with email {}", email);

        UserDto userDto = userService.findByEmail(email);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
     
}
