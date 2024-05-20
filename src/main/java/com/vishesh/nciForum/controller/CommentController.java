package com.vishesh.nciForum.controller;

import com.vishesh.nciForum.entity.CommentRequest;
import com.vishesh.nciForum.model.Comment;
import com.vishesh.nciForum.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for Comment
 */

@RestController
@RequestMapping("/api/v1/comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{discussionId}")
    public ResponseEntity<Comment> saveComment(@PathVariable UUID discussionId, @RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok(commentService.save(discussionId, commentRequest));
    }

    @GetMapping("/{discussionId}")
    public ResponseEntity<List<Comment>> getCommentsByDiscussion(@PathVariable UUID discussionId){
        return ResponseEntity.ok(commentService.findByDiscussion(discussionId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable UUID commentId){
        commentService.delete(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
