package pl.edu.pjwstk.pro.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.entities.MovieEntity;
import pl.edu.pjwstk.pro.requests.MovieRequest;

import javax.persistence.EntityManager;
import pl.edu.pjwstk.pro.exceptions.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class MovieService {
    private final EntityManager em;

    public MovieService(EntityManager em) {
        this.em = em;
    }

    public void saveMovie(MovieRequest movieRequest){
        var movieEntity = new MovieEntity();
        movieEntity.setTitle(movieRequest.getTitle());
        movieEntity.setDate(movieRequest.getDate());
        movieEntity.setDescription(movieRequest.getDescription());
        movieEntity.setScreening(movieRequest.getScreeningEntity());
        movieEntity.setPhotos(movieRequest.getPhotoEntityList());

        em.persist(movieEntity);
    }

    public void editMovie(Long id, MovieRequest movieRequest){
        if(movieExist(id)){
            var movieToEdit = em.createQuery("select ue from MovieEntity ue where ue.id = :id", MovieEntity.class)
                    .setParameter("id",id).getSingleResult();
            movieToEdit.setTitle(movieRequest.getTitle());
            movieToEdit.setDate(movieRequest.getDate());
            movieToEdit.setDescription(movieRequest.getDescription());
            movieToEdit.setScreening(movieRequest.getScreeningEntity());
            movieToEdit.setPhotos(movieRequest.getPhotoEntityList());

            em.merge(movieToEdit);
        }else{
            throw new EntityNotFoundException();
        }
    }
    public boolean movieExist(Long id){
        var isExist =  em.createQuery("select ue from MovieEntity ue where ue.id = :id", MovieEntity.class)
                .setParameter("id",id).getResultList();
        if(isExist.isEmpty()){
            return false;
        }else {
            return true;
        }
    }
    public boolean isAnyMovieExist(){
        return em.createQuery("select count(ue) from MovieEntity ue", Long.class)
                .getSingleResult()
                > 0;
    }
    public List<MovieEntity> getAllMovies(){
        if (!isAnyMovieExist()){
            throw new EntityNotFoundException();
        }else{
            var movieList = em.createQuery("select ue from MovieEntity ue", MovieEntity.class)
                    .getResultList();
            return movieList;
        }
    }
    public MovieEntity getSingleMovie(Long id){
        if(movieExist(id)){
            return em.createQuery("select ue from MovieEntity ue where ue.id = :id", MovieEntity.class)
                    .setParameter("id",id).getSingleResult();
        }else{
            throw new EntityNotFoundException();
        }
    }
}
