package com.example.Movie.application.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String movie_name;
    private String watch_hours;
    private String movie_actor_name;

    private byte[] movie_photo;
    private String fileType;
    private String released_date;
    private String movie_description;

    @ManyToOne
    private Category category;
}
