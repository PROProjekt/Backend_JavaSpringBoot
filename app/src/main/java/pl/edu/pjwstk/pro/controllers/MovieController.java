package pl.edu.pjwstk.pro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.User;
import pl.edu.pjwstk.pro.entities.ScreeningEntity;
import pl.edu.pjwstk.pro.requests.MovieRequest;
import pl.edu.pjwstk.pro.responses.Movie;
import pl.edu.pjwstk.pro.responses.MovieCollection;
import pl.edu.pjwstk.pro.responses.Screening;
import pl.edu.pjwstk.pro.responses.UserResponse;
import pl.edu.pjwstk.pro.services.MovieService;

import java.util.function.Function;
import java.util.stream.Collectors;


@RestController
public class MovieController {
    @Autowired
    MovieService service;

    @PostMapping("/addMovie")
    public void addMovie(@RequestBody MovieRequest movieRequest){
        service.saveMovie(movieRequest);
    }

    @PutMapping("/editMovie/{movieId}")
    public void updateMovie(@PathVariable Long movieId, @RequestBody MovieRequest movieRequest){
        service.editMovie(movieId,movieRequest);
    }
    @GetMapping("/getMovies")
    public ResponseEntity<MovieCollection> getMovies(){
        return ResponseEntity.ok(service.findMovies());
    }

    @GetMapping("/getMovie/{movieId}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long movieId){
        return ResponseEntity.ok(service.getMovie(movieId));
    }
}
