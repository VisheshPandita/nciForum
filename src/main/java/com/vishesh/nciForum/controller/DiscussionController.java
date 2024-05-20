package com.vishesh.nciForum.controller;

import com.vishesh.nciForum.entity.DiscussionRequest;
import com.vishesh.nciForum.model.Discussion;
import com.vishesh.nciForum.service.DiscussionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for Discussion
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/discussions")
@RequiredArgsConstructor
public class DiscussionController {

    private final DiscussionService discussionService;

    @GetMapping
    public ResponseEntity<List<Discussion>> findAllDiscussions(@RequestParam(name = "page", defaultValue = "0") int page,
                                                              @RequestParam(name = "size", defaultValue = "10") int size){
        return new ResponseEntity<>(discussionService.findAll(page, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Discussion> saveDiscussion(@RequestBody DiscussionRequest discussionRequest){
        return new ResponseEntity<>(discussionService.save(discussionRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discussion> findDiscussionById(@PathVariable UUID id) {
        return new ResponseEntity<>(discussionService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscussion(@PathVariable UUID id){
        discussionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
