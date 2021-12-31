package pl.edu.pjwstk.pro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.entities.MovieEntity;
import pl.edu.pjwstk.pro.exceptions.EntityNotFoundException;

import javax.persistence.EntityManager;

@Service
@Transactional
public class MovieScreeningService {
    @Autowired
    MovieService movieService;
    @Autowired
    ScreeningService screeningService;

    private final EntityManager em;

    public MovieScreeningService(EntityManager em) {
        this.em = em;
    }

    public void conncect(Long movieId, Long screeningId){
        if(ifExist(movieId,screeningId)){
            var movie = movieService.findMovie(movieId);
            var screening = screeningService.findScreening(screeningId);
            movie.getScreenings().add(screening);
            em.merge(movie);
        }else{
            throw new EntityNotFoundException();
        }
    }
    public boolean ifExist(Long movieId,Long screeningId){
        return movieService.movieExist(movieId) && screeningService.screeningExist(screeningId);
    }
}
