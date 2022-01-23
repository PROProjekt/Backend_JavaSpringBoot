package pl.edu.pjwstk.pro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.email.EmailService;
import pl.edu.pjwstk.pro.requests.MovieRequest;
import pl.edu.pjwstk.pro.responses.Movie;
import pl.edu.pjwstk.pro.responses.MovieCollection;
import pl.edu.pjwstk.pro.services.MovieService;

import javax.mail.MessagingException;


@RestController
public class MovieController {
    @Autowired
    MovieService service;
    @Autowired
    EmailService emailService;

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
    @PostMapping("/sendThankYouEmail/{movieId}")
    public void sendThankYouMail(@PathVariable long movieId) {
        var movie = service.getMovie(movieId);
        try{
            emailService.thanksForMakingOrder(movie.getTitle());
        }catch (MessagingException e){
            e.printStackTrace();
        }

    }
}
