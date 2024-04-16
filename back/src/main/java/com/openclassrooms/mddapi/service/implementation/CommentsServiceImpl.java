package com.openclassrooms.mddapi.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {

    private CommentRepository commentRepository;

    private CommentMapper commentMapper;
    private UserMapper userMapper;
    private PostMapper postMapper;

    private static final Logger log = LoggerFactory.getLogger(CommentsServiceImpl.class);

    public CommentsServiceImpl(CommentRepository commentRepository,
            CommentMapper commentMapper, 
            UserMapper userMapper,
            PostMapper postMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
        this.postMapper = postMapper;
    }

    @Override
    public List<CommentDto> getAll(Post post) {

        log.debug("Searching all comments for post with id {}", post.getId());

        List<Comment> comments = commentRepository.findByPost(post);

        return commentMapper.toDto(comments);
    }

    @Override
    public CommentDto create(String content, UserDto auteur, PostDto post) {

        Comment commentToSave = new Comment();
        commentToSave.setAuteur(userMapper.toEntity(auteur));
        commentToSave.setContent(content);
        commentToSave.setPost(postMapper.toEntity(post));
        commentToSave.setCreatedAt(LocalDate.now());
        commentToSave.setUpdatedAt(LocalDate.now());

        return commentMapper.toDto(commentRepository.save(commentToSave));
    }

}
