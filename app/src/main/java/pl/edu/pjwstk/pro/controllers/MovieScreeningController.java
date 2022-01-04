package pl.edu.pjwstk.pro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.pro.requests.ScreeningMovieRequest;
import pl.edu.pjwstk.pro.services.MovieScreeningService;


@RestController
public class MovieScreeningController {
    @Autowired
    MovieScreeningService service;

    @PostMapping("/conncetScreeningAndMovie")
    public void connect(@RequestBody ScreeningMovieRequest request){
        service.conncect(request.getMovieId(), request.getScreeningId());
    }
}
