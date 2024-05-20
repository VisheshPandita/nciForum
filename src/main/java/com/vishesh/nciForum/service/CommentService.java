package com.vishesh.nciForum.service;

import com.vishesh.nciForum.entity.CommentRequest;
import com.vishesh.nciForum.exception.CustomException;
import com.vishesh.nciForum.model.Comment;
import com.vishesh.nciForum.model.Discussion;
import com.vishesh.nciForum.model.User;
import com.vishesh.nciForum.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final DiscussionService discussionService;
    private final UserService userService;

    @Transactional
    public Comment save(UUID discussionId, CommentRequest commentRequest) {
        Discussion discussion = discussionService.findById(discussionId);
        User user = userService.findByUsername(commentRequest.getAuthorUsername());
        var comment = Comment.builder()
                .discussion(discussion)
                .content(commentRequest.getContent())
                .author(user)
                .build();
        return commentRepository.save(comment);
    }

    @Transactional
    public List<Comment> findByDiscussion(UUID discussionId) {
        List<Comment> comments = commentRepository.findCommentsByDiscussionIdOrderByCreatedAtDesc(discussionId);
        if(comments.isEmpty()){
            throw new CustomException("No comments found for discussion with id: " + discussionId, "NO_COMMENTS_FOUND", 404);
        }
        return comments;
    }

    @Transactional
    public void delete(UUID commentId) {
        commentRepository.deleteById(commentId);
    }
}
