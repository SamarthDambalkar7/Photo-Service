package com.photoservice.photoservice.repository;

import com.photoservice.photoservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    //    @Query(value = "SELECT c FROM Comment c JOIN c.photo p WHERE p.photoUrl = :photoUrl", nativeQuery = false)
//    List<Comment> getCommentsByPhotoUrl(String photoUrl);
    List<Comment> findByPhoto_PhotoUrl(String photoUrl);
}
