package com.radtke.bartosz.comments.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@ToString
public class Comment {
    private String id;
    private String content;
    @NotBlank
    private String postId;

}
