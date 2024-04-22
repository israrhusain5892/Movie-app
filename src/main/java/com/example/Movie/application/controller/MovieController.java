package com.example.Movie.application.controller;

import com.example.Movie.application.Dto.MovieDto;
import com.example.Movie.application.Dto.MovieDtoResponse;
import com.example.Movie.application.Models.Movie;
import com.example.Movie.application.Services.ServicesImpl.MovieServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/movie")
public class MovieController {


    @Autowired
    private MovieServiceImp movieServiceImp;

    @PostMapping("/{catId}")
    public MovieDtoResponse saveMovie(@RequestBody MovieDto movieDto, @PathVariable Integer catId, @RequestParam("file") MultipartFile file) throws IOException {
          String downloadUrl="";
          MovieDtoResponse res= movieServiceImp.publishMovie(movieDto,catId,file);
        downloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(res.getId()+"")
                .toUriString();
            res.setUrl(downloadUrl);
            return res;
    }

     @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable int id){
          MovieDto movie=movieServiceImp.getMovieById(id);
          return ResponseEntity.ok().
                  contentType(MediaType.parseMediaType(movie.getFileType())).
                  header(HttpHeaders.CONTENT_DISPOSITION,"movie; fileName = \"" +movie.getMovie_name()+"\"").
                  body(new ByteArrayResource(movie.getMovie_photo()));
     }

    @GetMapping("/{categoryId}")
    public List<MovieDto> getAllMovieByCategoryName(@PathVariable Integer categoryId){
           return movieServiceImp.getAllMovieByCategoryName(categoryId);
    }
    @PutMapping("/{movieId}/{categoryId}")
    public MovieDto updateMovie(@RequestBody MovieDto movieDto,@PathVariable Integer movieId,@PathVariable Integer categoryId ){
         return movieServiceImp.updateMovie(movieDto,movieId,categoryId);
    }

    @GetMapping("find/{movieId}")
    public MovieDto getMovieById(@PathVariable Integer movieId){
         return movieServiceImp.getMovieById(movieId);
    }
    @DeleteMapping("/{movieId}")
    public String deleteMovie(@PathVariable Integer movieId){
         return movieServiceImp.deleteMovie(movieId);
    }
    @GetMapping("/")
    public List<MovieDto> getAllMovie(){
         return movieServiceImp.getAllMovie();
    }

}
