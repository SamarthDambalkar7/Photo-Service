package com.photoservice.photoservice.service;

import java.util.LinkedList;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    String addNewPhoto(String userId, MultipartFile imageFile);

    LinkedList<String> getAllPhotos(String userId);

    LinkedList<String> getFeedImages();

    String getUserDetailsByPhotoUrl(String photoUrl);
}
