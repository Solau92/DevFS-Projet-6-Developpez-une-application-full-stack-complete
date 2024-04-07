package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.PostDto;

public interface IPostService {

    public List<PostDto> getAll();

}
