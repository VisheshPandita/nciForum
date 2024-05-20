package com.vishesh.nciForum.service;

import com.vishesh.nciForum.entity.TopicRequest;
import com.vishesh.nciForum.exception.CustomException;
import com.vishesh.nciForum.model.Topic;
import com.vishesh.nciForum.model.User;
import com.vishesh.nciForum.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicService {

    static String TOPIC_NOT_FOUND = "Topic not found";
    static String TOPIC_NOT_FOUND_CODE = "TOPIC_NOT_FOUND";

    public final TopicRepository topicRepository;
    public final UserService userService;

    public List<Topic> findAll(int page, int size) {
        return topicRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, size)).getContent();
    }

    public Topic save(TopicRequest topic) {
        User user = userService.findByUsername(topic.getCreatorUsername());
        List<User> moderator = new ArrayList<>();
        moderator.add(user);
        Topic newTopic = Topic.builder()
                .id(UUID.randomUUID())
                .name(topic.getName())
                .description(topic.getDescription())
                .moderators(moderator)
                .build();
        return topicRepository.save(newTopic);
    }

    public Topic findByName(String name) {
        return topicRepository.findTopicByName(name).orElseThrow(() -> new CustomException(TOPIC_NOT_FOUND, TOPIC_NOT_FOUND_CODE, 404));
    }

    public Topic update(String name, TopicRequest topic) {
        Topic existingTopic = topicRepository.findTopicByName(name).orElseThrow(() -> new CustomException(TOPIC_NOT_FOUND, TOPIC_NOT_FOUND_CODE, 404));
        existingTopic.setName(topic.getName());
        existingTopic.setDescription(topic.getDescription());
        return topicRepository.save(existingTopic);
    }

    public void delete(String name) {
        Topic existingTopic = topicRepository.findTopicByName(name).orElseThrow(() -> new CustomException(TOPIC_NOT_FOUND, TOPIC_NOT_FOUND_CODE, 404));
        topicRepository.delete(existingTopic);
    }
}
