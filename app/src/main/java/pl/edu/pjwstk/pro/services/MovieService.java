package pl.edu.pjwstk.pro.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.responses.Movie;
import pl.edu.pjwstk.pro.responses.Screening;
import pl.edu.pjwstk.pro.entities.MovieEntity;
import pl.edu.pjwstk.pro.requests.MovieRequest;
import pl.edu.pjwstk.pro.responses.MovieCollection;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieService {
    private final EntityManager em;

    public MovieService(EntityManager em) {
        this.em = em;
    }
    public void saveMovie(MovieRequest movieRequest){
        var movie = new MovieEntity();
        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setPoster(movieRequest.getPoster());
        movie.setYear(movieRequest.getYear());
        movie.setType(movieRequest.getType());

        em.persist(movie);
    }

    public void editMovie(Long id, MovieRequest movieRequest){
        if(!movieExist(id)){
            throw new EntityNotFoundException();
        }else{
            var movie = findMovie(id);
            movie.setTitle(movieRequest.getTitle());
            movie.setDescription(movieRequest.getDescription());
            movie.setPoster(movieRequest.getPoster());
            movie.setYear(movieRequest.getYear());
            movie.setType(movieRequest.getType());

            em.merge(movie);
        }
    }
    public MovieEntity findMovie(Long id){
        return em.createQuery("select ue from MovieEntity ue where ue.id = :id", MovieEntity.class)
                .setParameter("id", id).getSingleResult();
    }
    public Movie getMovie(Long id){
        var mE = findMovie(id);
        return new Movie(mE.getId(), mE.getTitle(), mE.getYear(), mE.getDescription(), mE.getType(), mE.getPoster()
                ,mE.getScreenings().stream().map(sE -> new Screening(sE.getId(),sE.getDay(), sE.getTime())).collect(Collectors.toList()));
    }

    public boolean movieExist(Long id) {
        var isExist =em.createQuery("select ue from MovieEntity ue where ue.id = :id", MovieEntity.class)
                .setParameter("id", id).getResultList();
        return !isExist.isEmpty();
    }

    public MovieCollection findMovies() {
        var list = em.createQuery("select(ue) from MovieEntity ue", MovieEntity.class)
                .getResultList();
        var resposnseList = list.stream().map(me -> new Movie(me.getId(), me.getTitle(), me.getYear(),me.getDescription(),me.getType(),me.getPoster()
                ,me.getScreenings().stream().map(se -> new Screening(se.getId(),se.getDay(), se.getTime()))
                .collect(Collectors.toList()))).collect(Collectors.toList());
        return new MovieCollection(resposnseList);
    }
}
