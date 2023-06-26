package com.radtke.bartosz.comments.model.repository;

import com.radtke.bartosz.comments.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}
