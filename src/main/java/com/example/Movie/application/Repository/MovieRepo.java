package com.example.Movie.application.Repository;


import com.example.Movie.application.Dto.MovieDto;
import com.example.Movie.application.Models.Category;
import com.example.Movie.application.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Integer> {

    List<Movie> findByCategory(Category category);
}
