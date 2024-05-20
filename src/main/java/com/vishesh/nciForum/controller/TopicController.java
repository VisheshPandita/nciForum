package com.vishesh.nciForum.controller;

import com.vishesh.nciForum.entity.TopicRequest;
import com.vishesh.nciForum.entity.TopicResponse;
import com.vishesh.nciForum.model.Discussion;
import com.vishesh.nciForum.model.Topic;
import com.vishesh.nciForum.service.DiscussionService;
import com.vishesh.nciForum.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for Topic
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;
    private final DiscussionService discussionService;

    @GetMapping
    public ResponseEntity<List<Topic>> findAllTopics(@RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "10") int size){
        return new ResponseEntity<>(topicService.findAll(page, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Topic> saveTopic(@RequestBody TopicRequest topic){
        return ResponseEntity.ok(topicService.save(topic));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Topic> updateTopic(@PathVariable String name, @RequestBody TopicRequest topic){
        return ResponseEntity.ok(topicService.update(name, topic));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteTopic(@PathVariable String name){
        topicService.delete(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<TopicResponse> findTopicByName(@PathVariable String name,
                                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "10") int size){
        Topic topic = topicService.findByName(name);
        List<Discussion> discussions = discussionService.findByTopic(topic, page, size);
        return new ResponseEntity<>(TopicResponse.builder().topic(topic).discussions(discussions).build(), HttpStatus.OK);
    }
}
