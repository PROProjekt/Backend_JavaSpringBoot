package pl.edu.pjwstk.pro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.pro.User;
import pl.edu.pjwstk.pro.UserService;
import pl.edu.pjwstk.pro.email.EmailService;
import pl.edu.pjwstk.pro.requests.ReservationRequest;
import pl.edu.pjwstk.pro.services.MovieService;
import pl.edu.pjwstk.pro.services.ReservationService;

import javax.mail.MessagingException;

@RestController
public class ReservationController {
    @Autowired
    ReservationService service;
    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;
    @Autowired
    EmailService emailService;

    @PostMapping("/setReservation")
    public void sendThankYouMail(@RequestBody ReservationRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userService.findByEmail(((User) principal).getEmail());
        service.saveReservation(user,request);
        var movie = movieService.getMovie(request.getMovie_id());
        var screen = movie.getScreeningList().stream().filter(screening -> screening.getId().equals(request.getScreening_id())).findAny()
                .orElse(null);
        try{
            emailService.thanksForMakingOrder(user.getEmail(),movie.getTitle(),screen.getDay(), screen.getTime());
        }catch (MessagingException e){
            e.printStackTrace();
        }

    }

}
