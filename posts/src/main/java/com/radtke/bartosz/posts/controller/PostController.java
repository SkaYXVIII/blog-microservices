package com.radtke.bartosz.posts.controller;

import com.radtke.bartosz.posts.model.Post;
import com.radtke.bartosz.posts.model.dto.PostDto;
import com.radtke.bartosz.posts.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody @Valid PostDto postDto) {
        return ResponseEntity.ok(postService.savePost(postDto.title()));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
}
