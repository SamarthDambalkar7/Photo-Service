package com.photoservice.photoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Photos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    @Id

    private String photoId;

    private String userId;

    private String photoUrl;

}
