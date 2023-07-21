package com.photoservice.photoservice.controller;

import com.photoservice.photoservice.dto.UserDto;
import com.photoservice.photoservice.service.PhotoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;

@RestController
@RequestMapping("/photo")
@CrossOrigin("*")
public class PhotoController {


    @Autowired
    private RestTemplate restTemplate;

//    private final PhotoServiceImpl photoServiceImpl;
//
//    public PhotoController(PhotoServiceImpl photoServiceImpl) {
//        this.photoServiceImpl = photoServiceImpl;
//    }

    @Autowired
    private PhotoServiceImpl photoServiceImpl;

    @Operation(summary = "add new photo object to db", description = "send a photo object with required properties and store the same to database")
    @PostMapping("/addnewphoto")
    public ResponseEntity<?> addNewPhoto(@RequestParam String userId, @RequestParam MultipartFile imageFile) {
        
        return photoServiceImpl.addNewPhoto(userId, imageFile);
    }

    @GetMapping("/getphotosbyuserid")
    public LinkedList<String> getAllPhotosByUserId(@RequestParam String userId) {

        return photoServiceImpl.getAllPhotos(userId);
    }

    @GetMapping("/getfeedimages")
    public LinkedList<String> getFeedImages() {
        return photoServiceImpl.getFeedImages();
    }

    @GetMapping("/getuserbyphotourl")
    public UserDto getUserByPhotoUrl(@RequestParam String photoUrl) {

        String userId = photoServiceImpl.getUserDetailsByPhotoUrl(photoUrl.trim());


        return restTemplate.getForObject("http://user-service/userphoto/getuserbyuserid?userId=" + userId, UserDto.class);

    }

}