package com.example.springboot3example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Post {

  @Id
  private Integer id;
  private Integer userId;
  private String title;
  private String body;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Post post = (Post) o;
    return id != null && Objects.equals(id, post.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
