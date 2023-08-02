package com.photoservice.photoservice.controller;

import com.photoservice.photoservice.dto.UserDTO;
import com.photoservice.photoservice.model.Comment;
import com.photoservice.photoservice.service.PhotoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photo")
@CrossOrigin("*")
public class PhotoController {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.service.primary.endpoint}")
    private String userService;

    @Autowired
    private PhotoServiceImpl photoServiceImpl;

    @Operation(summary = "add new photo object to db", description = "send a photo object with required properties and store the same to database")
    @PostMapping("/addnewphoto")
    public ResponseEntity<?> addNewPhoto(@RequestParam String userId, @RequestParam MultipartFile imageFile) {
        restTemplate.put(userService + "/photocount?userId=" + userId, null, ResponseEntity.class);

        return ResponseEntity.ok(photoServiceImpl.addNewPhoto(userId, imageFile));
    }

    @GetMapping("/getphotosbyuserid")
    public ResponseEntity<?> getAllPhotosByUserId(@RequestParam String userId) {

        return ResponseEntity.ok(photoServiceImpl.getAllPhotos(userId));
    }

    @GetMapping("/getfeedimages")
    public ResponseEntity<?> getFeedImages() {
        return ResponseEntity.ok(photoServiceImpl.getFeedImages());
    }

    @GetMapping("/getuserbyphotourl")
    public ResponseEntity<?> getUserByPhotoUrl(@RequestParam String photoUrl) {

        String userId = photoServiceImpl.getUserDetailsByPhotoUrl(photoUrl.trim());

        Boolean isCurrentUser = false;

        UserDTO response = restTemplate.getForObject(userService + "/?userId=" + userId + "&isCurrentUser=" + isCurrentUser, UserDTO.class);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/comment/add")
    public ResponseEntity<?> addNewComment(@RequestParam String photoUrl, @RequestBody Comment comment) {

        photoServiceImpl.addNewComment(photoUrl, comment);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/like")
    public ResponseEntity<?> likePhoto(@RequestParam String photoUrl, @RequestParam String liker) {
        photoServiceImpl.likePhoto(photoUrl, liker);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/unlike")
    public ResponseEntity<?> unLikePhoto(@RequestParam String photoUrl, @RequestParam String unLiker) {
        photoServiceImpl.unLikePhoto(photoUrl, unLiker);
        return ResponseEntity.ok().build();
    }
}
