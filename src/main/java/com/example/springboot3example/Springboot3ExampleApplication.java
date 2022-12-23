package com.example.springboot3example;

import com.example.springboot3example.repository.PostRepository;
import com.example.springboot3example.service.JsonPlaceholderService;
import com.example.springboot3example.service.PostService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class Springboot3ExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(Springboot3ExampleApplication.class, args);
  }

/*  @Bean
  public CommandLineRunner commandLineRunner(PostService postService, PostRepository postRepository) {
    return args -> {
      var posts = postService.loadPosts();
      postRepository.saveAll(posts);
    };
  }*/

  @Bean
  public CommandLineRunner commandLineRunner(PostRepository postRepository, ObservationRegistry observationRegistry) {
    return args -> {
      var client = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
      var factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
      var jsonPlaceholderService = factory.createClient(JsonPlaceholderService.class);
      //var posts = jsonPlaceholderService.loadPosts();
      var posts = Observation
          .createNotStarted("json-placeholder.load-posts", observationRegistry)
          .lowCardinalityKeyValue("some-value", "88")
          .observe(jsonPlaceholderService::loadPosts);
      //postRepository.saveAll(posts);
      Observation
          .createNotStarted("post-repository.save-all", observationRegistry)
          .observe(() -> postRepository.saveAll(posts));
    };
  }

}
