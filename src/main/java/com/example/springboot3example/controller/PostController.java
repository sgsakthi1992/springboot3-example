package com.example.springboot3example.controller;

import com.example.springboot3example.exception.PostNotFoundException;
import com.example.springboot3example.model.Post;
import com.example.springboot3example.repository.PostRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

  private final PostRepository postRepository;

  @GetMapping
  public List<Post> findAll() {
    return postRepository.findAll();
  }

  @GetMapping("/{id}")
  public Post findById(@PathVariable Integer id) {
    return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
  }
}
