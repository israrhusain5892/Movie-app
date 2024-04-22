package com.example.Movie.application.Dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MovieDto {

    private int id;
    private String movie_name;
    private String watch_hours;
    private String movie_actor_name;
    private String released_date;
    private String movie_description;
    private CategoryDto categoryDto;

    public MovieDto(String movie_name, String watch_hours, String movie_actor_name,String released_date, String movie_description) {
        this.movie_name = movie_name;
        this.watch_hours = watch_hours;
        this.movie_actor_name = movie_actor_name;
        this.released_date = released_date;
        this.movie_description = movie_description;

    }
}
