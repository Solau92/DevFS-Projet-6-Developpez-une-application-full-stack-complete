package com.openclassrooms.mddapi.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.exception.UserNotFoundException;
import com.openclassrooms.mddapi.service.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private IUserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") String id) throws NumberFormatException, UserNotFoundException {
        
        UserDto userDto = this.userService.findById(Long.valueOf(id));
        
        return ResponseEntity.status(HttpStatus.OK).body(userDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") String id, 
    @Valid @ModelAttribute("UserDto") UserDto userDto) throws UserNotFoundException {

        userDto.setId(Long.valueOf(id));
        UserDto updatedUserDto = userService.update(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUserDto);

    }
    
}
