package com.vishesh.nciForum.service;

import com.vishesh.nciForum.entity.DiscussionRequest;
import com.vishesh.nciForum.exception.CustomException;
import com.vishesh.nciForum.model.Discussion;
import com.vishesh.nciForum.model.Topic;
import com.vishesh.nciForum.model.User;
import com.vishesh.nciForum.repository.CommentRepository;
import com.vishesh.nciForum.repository.DiscussionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiscussionService {

    private final DiscussionRepository discussionRepository;
    private final UserService userService;
    private final TopicService topicService;
    private final CommentRepository commentRepository;

    @Transactional
    public List<Discussion> findAll(int page, int size) {
        return discussionRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, size)).getContent();
    }

    @Transactional
    public Discussion save(DiscussionRequest discussionRequest) {
        User author = userService.findByUsername(discussionRequest.getAuthorUsername());
        Topic topic = topicService.findByName(discussionRequest.getTopicName());
        Discussion newDiscussion = Discussion.builder()
                .id(UUID.randomUUID())
                .title(discussionRequest.getTitle())
                .content(discussionRequest.getContent())
                .author(author)
                .topic(topic)
                .comments(new ArrayList<>())
                .build();

        return discussionRepository.save(newDiscussion);
    }

    @Transactional
    public Discussion findById(UUID discussionId) {
        return discussionRepository.findById(discussionId).orElseThrow(() -> new CustomException("Discussion not found", "DISCUSSION_NOT_FOUND", 404));
    }

    @Transactional
    public List<Discussion> findByTopic(Topic topic, int page, int size) {
        return discussionRepository.findDiscussionsByTopicOrderByCreatedAtDesc(topic, PageRequest.of(page, size)).getContent();
    }

    @Transactional
    public void delete(UUID id) {
        Discussion existingDiscussion = discussionRepository.findById(id).orElseThrow(() -> new CustomException("Discussion not found", "DISCUSSION_NOT_FOUND", 404));
        commentRepository.deleteByDiscussion(existingDiscussion);
        discussionRepository.delete(existingDiscussion);
    }
}
