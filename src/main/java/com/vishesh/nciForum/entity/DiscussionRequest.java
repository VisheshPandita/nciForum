package com.vishesh.nciForum.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscussionRequest {

    private String title;
    private String content;
    private String authorUsername;
    private String topicName;
}
