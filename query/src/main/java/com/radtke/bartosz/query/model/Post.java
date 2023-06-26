package com.radtke.bartosz.query.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "query")
@Getter
@Setter
public class Post {
    private String id;
    private String title;
    private List<Comment> comments = List.of();
}
