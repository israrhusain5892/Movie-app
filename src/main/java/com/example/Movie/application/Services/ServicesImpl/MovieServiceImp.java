package com.example.Movie.application.Services.ServicesImpl;


import com.example.Movie.application.Dto.CategoryDto;
import com.example.Movie.application.Dto.MovieDto;
import com.example.Movie.application.Dto.MovieDtoResponse;
import com.example.Movie.application.Exceptions.ResourceNotFoundException;
import com.example.Movie.application.Models.Category;
import com.example.Movie.application.Models.Movie;
import com.example.Movie.application.Repository.CategoryRepo;
import com.example.Movie.application.Repository.MovieRepo;
import com.example.Movie.application.Services.ServicesInterface.Movieservice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImp implements Movieservice {


    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ModelMapper modelMapper;

     @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public MovieDto publishMovie(MovieDto movieDto, Integer categoryId) throws IOException {

        String downloadUrl="";
        Category cat=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceAccessException("Resource not found"));
        CategoryDto categoryDto=modelMapper.map(cat,CategoryDto.class);

          Movie movie=modelMapper.map(movieDto,Movie.class);
//          movie.setMovie_photo(file.getBytes());
//          String name=file.getOriginalFilename();
//          movie.setMovie_name(name);
//          movie.setFileType(file.getContentType());
          movie.setCategory(cat);
          Movie savedMovie=movieRepo.save(movie) ;


          MovieDto savedMovieDto= modelMapper.map(savedMovie,MovieDto.class);

          savedMovieDto.setCategoryDto(categoryDto);
          return savedMovieDto;
    }

    @Override
    public MovieDto updateMovie(MovieDto movieDto, Integer movieId,Integer categoryId) {
         Movie movie=movieRepo.findById(movieId).
                    orElseThrow(()->new ResourceNotFoundException("movie","movieId",movieId));
         Category cat=categoryRepo.findById(categoryId).get();
          movie.setMovie_name(movieDto.getMovie_name());
          movie.setMovie_actor_name(movieDto.getMovie_actor_name());
          movie.setReleased_date(movieDto.getReleased_date());
          movie.setWatch_hours(movieDto.getWatch_hours());
          movie.setCategory(cat);
          movie.setMovie_description(movieDto.getMovie_description());
          Movie savedMovie=movieRepo.save(movie);
          MovieDto movieDto1=modelMapper.map(savedMovie,MovieDto.class);
           movieDto1.setCategoryDto(modelMapper.map(cat,CategoryDto.class));
          return movieDto1;

    }

    @Override
    public String deleteMovie(Integer movieId) {
          movieRepo.deleteById(movieId);
          return "Movie with "+movieId+" deleted successfully !!" ;
    }

    @Override
    public MovieDto getMovieById(Integer movieId) {
            Movie movie=movieRepo.findById(movieId).orElseThrow(()->
                    new ResourceNotFoundException("movie","movieId",movieId)) ;
            Category category=categoryRepo.findById(movie.getCategory().getId()).get();
            MovieDto movieDto=modelMapper.map(movie,MovieDto.class);
            movieDto.setCategoryDto(modelMapper.map(category,CategoryDto.class));
            return movieDto;
    }


    public List<MovieDto> getAllMovieByCategoryName(Integer categoryId) {
         Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceAccessException("resource not found"));
        List<Movie> movieList=movieRepo.findByCategory(category);
        List<MovieDto> movieDtos=new ArrayList<>();
         for(Movie movie:movieList){
               MovieDto movieDto=modelMapper.map(movie,MovieDto.class);
               movieDto.setCategoryDto(modelMapper.map(category,CategoryDto.class));
               movieDtos.add(movieDto);
         }
         return movieDtos;
    }

    @Override
    public List<MovieDto> getAllMovie() {
        List<MovieDto> movieDtos=new ArrayList<>();

        List<Movie> movieList=movieRepo.findAll();
        for(Movie newmovie:movieList){
             Category category=categoryRepo.findById(newmovie.getCategory().getId()).get();
              MovieDto movieDto=modelMapper.map(newmovie,MovieDto.class);
              movieDto.setCategoryDto(modelMapper.map(category,CategoryDto.class));
              movieDtos.add(movieDto);
        }
        return movieDtos ;
    }
}
