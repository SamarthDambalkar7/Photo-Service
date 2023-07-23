package com.photoservice.photoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private String userName;
    
    private ArrayList<String> followers;

    private ArrayList<String> following;

    private int photosCount;
}
