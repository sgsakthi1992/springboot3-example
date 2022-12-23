package com.example.springboot3example.exception;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

  @ExceptionHandler(PostNotFoundException.class)
  public ProblemDetail handlePostNotFoundException(PostNotFoundException e) throws URISyntaxException {
    var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    problemDetail.setProperty("postId", e.getId());
    problemDetail.setType(new URI("http://localhost:8080/problems/post-not-found"));
    return problemDetail;
  }
}
