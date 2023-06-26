package com.radtke.bartosz.query.controller;

import com.radtke.bartosz.query.model.Post;
import com.radtke.bartosz.query.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/query")
@RequiredArgsConstructor
public class QueryController {
    private final QueryService queryService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> allPosts = queryService.getAllPosts();
        return ResponseEntity.ok(allPosts);
    }

}
