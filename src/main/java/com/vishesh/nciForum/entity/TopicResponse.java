package com.vishesh.nciForum.entity;

import com.vishesh.nciForum.model.Discussion;
import com.vishesh.nciForum.model.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicResponse {
    Topic topic;
    List<Discussion> discussions;
}
