package com.example.springboot3example.repository;

import com.example.springboot3example.model.Post;
import org.springframework.data.repository.ListCrudRepository;

public interface PostRepository extends ListCrudRepository<Post, Integer> {

}
