package com.photoservice.photoservice.repository;

import com.photoservice.photoservice.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, String> {

    @Query("select p.photoUrl from Photo p where p.userId = :userId")
    LinkedList<String> getPhotoUrlsByUserId(String userId);

    @Query("select p.photoUrl from Photo p")
    LinkedList<String> getAllPhotoUrls();

    @Query("Select p.userId from Photo p WHERE p.photoUrl = :photoUrl")
    String getUserIdByPhotoUrl(String photoUrl);

}
