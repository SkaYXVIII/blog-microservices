package com.radtke.bartosz.posts.model.dto;

import jakarta.validation.constraints.NotBlank;

public record PostDto(@NotBlank(message = "Title cannot be blank") String title) {
}
