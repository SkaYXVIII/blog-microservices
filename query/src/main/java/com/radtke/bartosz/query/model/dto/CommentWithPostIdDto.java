package com.radtke.bartosz.query.model.dto;

import com.radtke.bartosz.query.model.Comment;

public record CommentWithPostIdDto (String id, String content, String postId) {
    public Comment toComment() {
        return new Comment(id, content);
    }
}
