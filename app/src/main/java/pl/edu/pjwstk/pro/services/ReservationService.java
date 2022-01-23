package pl.edu.pjwstk.pro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.User;
import pl.edu.pjwstk.pro.UserService;
import pl.edu.pjwstk.pro.entities.ReservationEntity;
import pl.edu.pjwstk.pro.entities.UserEntity;
import pl.edu.pjwstk.pro.requests.ReservationRequest;
import pl.edu.pjwstk.pro.responses.UserResponse;

import javax.persistence.EntityManager;

@Service
@Transactional
public class ReservationService {
    @Autowired
    MovieService movieService;
    @Autowired
    ScreeningService screeningService;
    @Autowired
    UserService userService;

    private final EntityManager em;

    public ReservationService(EntityManager em) {
        this.em = em;
    }

    public void saveReservation(UserEntity user, ReservationRequest request){
        var reservation = new ReservationEntity();
        reservation.setScreeningEntity(screeningService.findScreening(request.getScreening_id()));
        reservation.setMovieEntity(movieService.findMovie(request.getMovie_id()));
        reservation.setUserEntity(user);
//        reservation.setUserEntity(userService.findById(user_id));

        em.persist(reservation);
    }
}
