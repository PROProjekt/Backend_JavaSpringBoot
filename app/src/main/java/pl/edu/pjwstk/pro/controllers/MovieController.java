package pl.edu.pjwstk.pro.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.entities.MovieEntity;
import pl.edu.pjwstk.pro.requests.MovieRequest;
import pl.edu.pjwstk.pro.services.MovieService;

import java.util.List;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/addMovie")
    public void addMovie(@RequestBody MovieRequest movieRequest){
        movieService.saveMovie(movieRequest);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/editMovie/{movieId}")
    public void editMovie(@PathVariable Long movieId, @RequestBody MovieRequest movieRequest){
        movieService.editMovie(movieId,movieRequest);
    }

    @GetMapping("/getMovies")
    public List<MovieEntity> showMovies(){
        return movieService.getAllMovies();
    }
}
