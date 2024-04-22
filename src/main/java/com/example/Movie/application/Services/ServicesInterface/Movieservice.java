package com.example.Movie.application.Services.ServicesInterface;

import com.example.Movie.application.Dto.MovieDto;
import com.example.Movie.application.Dto.MovieDtoResponse;
import com.example.Movie.application.Models.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface Movieservice {


     MovieDtoResponse publishMovie(MovieDto movieDto, Integer categoryId, MultipartFile file) throws IOException;

     MovieDto updateMovie(MovieDto movieDto,Integer movieId,Integer categoryId);
     String deleteMovie(Integer movieId);
     MovieDto getMovieById(Integer movieId);
     List<MovieDto> getAllMovieByCategoryName(Integer categoryId);

     List<MovieDto> getAllMovie();
}
