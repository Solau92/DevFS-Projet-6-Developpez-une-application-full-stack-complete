package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserLoginDto;

public interface JWTService {

    /**
     * Generates token if the given user is properly identified.
     * 
     * @param userLoginDto
     * @return String corresponding to the token
     */
    public String generateToken(UserLoginDto userLoginDto);

}
