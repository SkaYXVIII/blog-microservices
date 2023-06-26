package com.radtke.bartosz.posts.service;

import com.radtke.bartosz.posts.model.Post;
import com.radtke.bartosz.posts.model.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostService {
    private final PostRepository postRepository;
    private final KafkaTemplate<String, Post> kafkaTemplate;
    @Value("${kafka.topic}")
    private String topic;
    @Value("${kafka.producer.key}")
    private String key;

    public Post savePost(String title) {
        Post post = new Post()
                .setTitle(title);
        Post savedPost = postRepository.save(post);
        log.info("wysyłam z kafki nowy post" + post);
        kafkaTemplate.send(topic,key, savedPost);
        return savedPost;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
