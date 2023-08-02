package com.photoservice.photoservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentId;

    @Column(name = "comment_date")
    private String date;

    @Column(name = "comment_time")
    private String time;

    @Column(name = "comment_msg")
    private String commentMsg;

    @Column(name = "commenter")
    private String commenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @Column(name = "likes")
    private long likes;

}
