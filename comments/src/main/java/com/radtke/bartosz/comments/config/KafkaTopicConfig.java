package com.radtke.bartosz.comments.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Value("${kafka.topic}")
    private String postTopic;
    @Bean
    public NewTopic postTopic() {
        return TopicBuilder.name(postTopic)
                .build();
    }
}
