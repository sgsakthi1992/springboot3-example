package com.example.springboot3example.service;

import com.example.springboot3example.model.Post;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class PostService {

  private final RestTemplate restTemplate;

  public List<Post> loadPosts() {
    var response = restTemplate.exchange(
        "https://jsonplaceholder.typicode.com/posts",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Post>>() {
        });
    return response.getBody();
  }
}
