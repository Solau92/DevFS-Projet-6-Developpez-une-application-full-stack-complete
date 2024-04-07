package com.openclassrooms.mddapi.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.LoginRegisterDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserRegisterDto;
import com.openclassrooms.mddapi.dto.response.LoginResponse;
import com.openclassrooms.mddapi.exception.BadCredentialsCustomException;
import com.openclassrooms.mddapi.exception.UserAlreadyExistsException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;
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
    // ... pas forcément : si renvoie vers Login et pas directement logué 
    @PostMapping("/register")
    public ResponseEntity<UserRegisterDto> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) throws UserAlreadyExistsException {

        log.info("/auth/register : Trying to register user with email {}", userRegisterDto.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRegisterDto));

    }

    // A modifier avec Spring Security 
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRegisterDto loginRegisterDto) throws BadCredentialsCustomException {

        log.info("/auth/login : Trying to login user with email {}", loginRegisterDto.getEmail());
        
        String token = userService.validateCredentials(loginRegisterDto);

        LoginResponse response = new LoginResponse(token);

        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // log.info("token : " + token + " - authentication in login : " + authentication);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    // // Version ***
    // // A modifier avec Spring Security : 
    @GetMapping("me")
    public ResponseEntity<UserDto> me(Authentication authentication) throws UserNotFoundException {

        log.info("authentication : " + authentication);

        String email = authentication.getName();

        // Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
        // log.info("authentication : " + authentication2);
        // String email = authentication2.getName();

        log.info("api/auth/me : getting information from user with email {}", email);

        UserDto userDto = userService.findByEmail(email);

        // TODO !!! : récupérer utilisateur authenticated 
        // UserDto userDto = userService.findById(Long.valueOf(2));

        return ResponseEntity.status(HttpStatus.OK).body(userDto);

    }

     
}
