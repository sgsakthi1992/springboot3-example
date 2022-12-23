package com.example.springboot3example.service;

import com.example.springboot3example.model.Post;
import java.util.List;
import org.springframework.web.service.annotation.GetExchange;

public interface JsonPlaceholderService {
  @GetExchange("/posts")
  List<Post> loadPosts();
}
