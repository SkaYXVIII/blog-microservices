package com.radtke.bartosz.query.service;

import com.radtke.bartosz.query.model.Comment;
import com.radtke.bartosz.query.model.Post;
import com.radtke.bartosz.query.model.dto.CommentWithPostIdDto;
import com.radtke.bartosz.query.model.dto.PostDto;
import com.radtke.bartosz.query.model.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class QueryService {
    private final PostRepository queryRepository;

    public List<Post> getAllPosts() {
        return queryRepository.findAll();
    }

    public Post savePostWithoutComments(PostDto postDto) {
        Post postWithoutComments = new Post()
                .setId(postDto.id())
                .setTitle(postDto.title());
        return queryRepository.save(postWithoutComments);
    }

    public Post saveCommentsToGivenPost(CommentWithPostIdDto commentWithPostIdDto){
        Post post = queryRepository
                .findById(commentWithPostIdDto.postId())
                .orElseThrow();
        Comment comment = commentWithPostIdDto.toComment();
        List<Comment> updatedComments = Stream.concat(post.getComments().stream(), Stream.of(comment))
                .toList();
        post.setComments(updatedComments);
        return queryRepository.save(post);
    }
}
