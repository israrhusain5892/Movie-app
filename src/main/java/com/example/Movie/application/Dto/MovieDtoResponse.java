package com.example.Movie.application.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieDtoResponse {

    private int id;
    private String movie_name;
    private String watch_hours;
    private String movie_actor_name;
    private String released_date;
    private String movie_description;
    private String url;
    private CategoryDto categoryDto;

}
