package com.openclassrooms.mddapi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.repository.CommentRepository;

@Service
public class CommentsService implements ICommentsService {

    private CommentRepository commentRepository;

    private static final Logger log = LoggerFactory.getLogger(CommentsService.class);

    private CommentMapper commentMapper;

    public CommentsService(CommentRepository commentRepository,
            CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDto> getAll(Post post) {

        log.debug("Searching all comments for post with id {}", post.getId());

        List<Comment> comments = commentRepository.findByPost(post);

        return commentMapper.toDto(comments);

    }

}
