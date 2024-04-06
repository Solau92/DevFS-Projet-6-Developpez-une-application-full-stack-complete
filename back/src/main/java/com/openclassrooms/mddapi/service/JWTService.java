package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.LoginRegisterDto;

public interface JWTService {

    /**
     * Generates token if the given user is properly identified.
     * 
     * @param userLoginDto
     * @return String corresponding to the token
     */
    String generateToken(LoginRegisterDto userLoginDto);

}
