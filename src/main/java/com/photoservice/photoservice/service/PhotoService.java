package com.photoservice.photoservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;

public interface PhotoService {

    ResponseEntity<?> addNewPhoto(String userId, MultipartFile imageFile);

    LinkedList<String> getAllPhotos(String userId);

    LinkedList<String> getFeedImages();

    String getUserDetailsByPhotoUrl(String photoUrl);
}
