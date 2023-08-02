package com.photoservice.photoservice.service;

import com.photoservice.photoservice.model.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;

public interface PhotoService {

    ResponseEntity<?> addNewPhoto(String userId, MultipartFile imageFile);

    LinkedList<String> getAllPhotos(String userId);

    LinkedList<String> getFeedImages();

    String getUserDetailsByPhotoUrl(String photoUrl);

    void addNewComment(String photoUrl, Comment comment);

    void likePhoto(String photoUrl, String liker);

    void unLikePhoto(String photoUrl, String unLiker);
}
