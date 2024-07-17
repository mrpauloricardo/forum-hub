package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.dto.CreatePostDto;
import br.com.alura.forumhub.dto.FeedDto;
import br.com.alura.forumhub.dto.FeedItemDto;
import br.com.alura.forumhub.entity.Post;
import br.com.alura.forumhub.entity.Role;
import br.com.alura.forumhub.repository.PostRepository;
import br.com.alura.forumhub.repository.UserRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository,
                           UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/feed")
    public ResponseEntity<FeedDto> feed(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        var posts = postRepository.findAll(
                        PageRequest.of(page, pageSize, Sort.Direction.DESC, "creationTimestamp"))
                .map(post ->
                        new FeedItemDto(
                                post.getPostId(),
                                post.getTitle(),
                                post.getContent(),
                                post.getUser().getUsername())
                );

        return ResponseEntity.ok(new FeedDto(
                posts.getContent(), page, pageSize, posts.getTotalPages(), posts.getTotalElements()));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<FeedItemDto> getPostById(@PathVariable("id") Long postId) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var feedItemDto = new FeedItemDto(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getUsername()
        );

        return ResponseEntity.ok(feedItemDto);
    }

    @PostMapping("/posts")
    public ResponseEntity<Void> createPost(@RequestBody CreatePostDto dto,
                                            JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()));

        var post = new Post();
        post.setUser(user.get());
        post.setTitle(dto.title());
        post.setContent(dto.content());

        postRepository.save(post);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable("id") Long postId,
                                           @RequestBody CreatePostDto dto,
                                           JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()));
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!post.getUser().getUserId().equals(UUID.fromString(token.getName()))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        post.setTitle(dto.title());
        post.setContent(dto.content());

        postRepository.save(post);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long postId,
                                            JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()));
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var isAdmin = user.get().getRoles()
                .stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));

        if (isAdmin || post.getUser().getUserId().equals(UUID.fromString(token.getName()))) {
            postRepository.deleteById(postId);

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


        return ResponseEntity.ok().build();
    }
}