package com.radtke.bartosz.comments.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentDto(@NotBlank String content) {
}
