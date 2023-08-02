package com.photoservice.photoservice.repository;

import com.photoservice.photoservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
