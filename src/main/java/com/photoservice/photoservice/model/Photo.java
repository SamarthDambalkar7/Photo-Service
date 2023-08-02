package com.photoservice.photoservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Photos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    @Id
    @Column(name = "photoId")
    private String photoId;

    @Column(name = "userId")
    private String userId;

    @Column(name = "photoUrl")
    private String photoUrl;

    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    @Column(name = "comments")
    private List<Comment> comments = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "photo_likers", joinColumns = @JoinColumn(name = "photo_id"))
    @Column(name = "Likers")
    private Set<String> likers = new HashSet<>();
}
