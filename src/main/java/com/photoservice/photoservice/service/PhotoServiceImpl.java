package com.photoservice.photoservice.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.photoservice.photoservice.model.Comment;
import com.photoservice.photoservice.model.Photo;
import com.photoservice.photoservice.repository.CommentRepository;
import com.photoservice.photoservice.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Autowired
    private Cloudinary cloudinary;

    @Override
    public ResponseEntity<?> addNewPhoto(String userId, MultipartFile imageFile) {
        try {
            Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());

            String photoId = UUID.randomUUID().toString();
            Photo photo = new Photo(photoId, userId, uploadResult.get("secure_url").toString(), new ArrayList<>(), new HashSet<>());
            photoRepository.save(photo);
            return ResponseEntity.ok(uploadResult.get("secure_url"));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public LinkedList<String> getAllPhotos(String userId) {
        return photoRepository.getPhotoUrlsByUserId(userId);
    }


    @Override
    public LinkedList<String> getFeedImages() {

        return photoRepository.getAllPhotoUrls();
    }

    @Override
    public String getUserDetailsByPhotoUrl(String photoUrl) {

        String userId = photoRepository.getUserIdByPhotoUrl(photoUrl);

        return userId;
    }

    @Override
    public void addNewComment(String photoUrl, Comment comment) {

        Photo photo = photoRepository.findByPhotoUrl(photoUrl);
        comment.setPhoto(photo);
        comment.setLikes(0);
        photo.getComments().add(comment);
        commentRepository.save(comment);
        photoRepository.save(photo);

    }

    @Override
    public void likePhoto(String photoUrl, String liker) {

        Photo photo = photoRepository.findByPhotoUrl(photoUrl);
        photo.getLikers().add(liker);
        photoRepository.save(photo);
    }

    @Override
    public void unLikePhoto(String photoUrl, String unLiker) {
        Photo photo = photoRepository.findByPhotoUrl(photoUrl);
        photo.getLikers().remove(unLiker);
        photoRepository.save(photo);
    }

}
