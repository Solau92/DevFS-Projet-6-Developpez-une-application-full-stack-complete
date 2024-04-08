package com.openclassrooms.mddapi.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.repository.CommentRepository;

@Service
public class CommentsService implements ICommentsService {

    private CommentRepository commentRepository;

    private static final Logger log = LoggerFactory.getLogger(CommentsService.class);

    private UserMapper userMapper;
    private PostMapper postMapper;

    public CommentsService(CommentRepository commentRepository,
            UserMapper userMapper,
            PostMapper postMapper) {
        this.commentRepository = commentRepository;
        this.userMapper = userMapper;
        this.postMapper = postMapper;
    }

    @Override
    public List<CommentDto> getAll(Post post) {

        List<Comment> comments = commentRepository.findByPost(post);

        // TODO : régler problème mapper

        List<CommentDto> commentsDto = new ArrayList<>();

        for (Comment c : comments) {

            CommentDto commentDto = new CommentDto();
            commentDto.setId(c.getId());
            commentDto.setAuteur(userMapper.toDto(c.getAuteur()));
            commentDto.setPost(postMapper.toDto(c.getPost()));
            commentDto.setContent(c.getContent());
            commentDto.setCreatedAt(c.getCreatedAt());
            commentDto.setUpdatedAt(c.getCreatedAt());

            commentsDto.add(commentDto);
        }

        return commentsDto;

    }

}
