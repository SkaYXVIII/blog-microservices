package com.radtke.bartosz.comments.controller;

import com.radtke.bartosz.comments.model.Comment;
import com.radtke.bartosz.comments.model.dto.CommentDto;
import com.radtke.bartosz.comments.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<Comment> createComment(@PathVariable String postId, @RequestBody @Valid CommentDto commentDto) {
        Comment comment = commentService.createComment(commentDto, postId);
        return ResponseEntity.ok(comment);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }
}
