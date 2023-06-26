package com.radtke.bartosz.comments.service;

import com.radtke.bartosz.comments.model.Comment;
import com.radtke.bartosz.comments.model.dto.CommentDto;
import com.radtke.bartosz.comments.model.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CommentService {
    private final CommentRepository commentRepository;
    private final KafkaTemplate<String, Comment> kafkaTemplate;
    @Value("${kafka.topic}")
    private String topic;
    @Value("${kafka.producer.key}")
    private String key;

    public Comment createComment(CommentDto commentDto, String postId) {
        Comment comment = new Comment()
                .setContent(commentDto.content())
                .setPostId(postId);
        Comment savedComment = commentRepository.save(comment);
        log.info("wysyłam z kafki nowy komentarz" + comment);
        kafkaTemplate.send(topic, key, savedComment);
        return savedComment;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
