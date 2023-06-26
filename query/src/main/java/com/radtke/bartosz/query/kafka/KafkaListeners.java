package com.radtke.bartosz.query.kafka;

import com.radtke.bartosz.query.model.dto.CommentWithPostIdDto;
import com.radtke.bartosz.query.model.dto.PostDto;
import com.radtke.bartosz.query.service.QueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class KafkaListeners {
    private final QueryService queryService;

    @KafkaListener(topics = "${kafka.topic.post}", groupId = "${kafka.consumer.group-id}", containerFactory = "postKafkaListenerContainerFactory")
    void postListener(PostDto postDto) {
        log.info("Received Message in group post-topic: " + postDto.toString());
        queryService.savePostWithoutComments(postDto);
    }

    @KafkaListener(topics = "${kafka.topic.comment}", groupId = "${kafka.consumer.group-id}", containerFactory = "commentKafkaListenerContainerFactory")
    void commentListener(CommentWithPostIdDto commentDto) {
        log.info("Received Message in group comment-topic: " + commentDto.toString());
        queryService.saveCommentsToGivenPost(commentDto);
    }
}
