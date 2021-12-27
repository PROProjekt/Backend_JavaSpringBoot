package pl.edu.pjwstk.pro.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.entities.PhotoEntity;
import pl.edu.pjwstk.pro.requests.PhotoRequest;

import javax.persistence.EntityManager;
import pl.edu.pjwstk.pro.exceptions.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class PhotoService {
    private final EntityManager em;

    public PhotoService(EntityManager em) {
        this.em = em;
    }

    public void savePhoto(PhotoRequest photoRequest) {
        var photo = new PhotoEntity();
        photo.setTitle(photoRequest.getTitle());
        photo.setPosition(photoRequest.getPosition());
        photo.setMovie(photoRequest.getMovieEntity());

        em.persist(photo);
    }

//    public void editPhoto(Long id, PhotoRequest photoRequest) {
//        if (photoExist(id)) {
//            var photoToEdit = em.createQuery("select ue from PhotoEntity ue where ue.id = :id", PhotoEntity.class)
//                    .setParameter("id", id).getSingleResult();
//            photoToEdit.setTitle(photoRequest.getTitle());
//            photoToEdit.setPosition(photoRequest.getPosition());
//            photoToEdit.setMovie(photoRequest.getMovieEntity());
//
//            em.merge(photoToEdit);
//        } else {
//            throw new EntityNotFoundException();
//        }
//    }
    public void deletePhoto(Long id, PhotoRequest photoRequest){
        if (photoExist(id)) {
            var photoToDelete = em.createQuery("select ue from PhotoEntity ue where ue.id = :id", PhotoEntity.class)
                    .setParameter("id", id).getSingleResult();
            em.remove(photoToDelete);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public PhotoEntity getSinglePhoto(Long id) {
        if(photoExist(id)){
            return em.createQuery("select ue from PhotoEntity ue where ue.id = :id", PhotoEntity.class)
                    .setParameter("id", id).getSingleResult();
        }else{
            throw new EntityNotFoundException();
        }

    }

    public List<PhotoEntity> getAllPhotos() {
        var photosList = em.createQuery("select ue from PhotoEntity ue", PhotoEntity.class)
                .getResultList();
        return photosList;
    }

    public boolean photoExist(Long id) {
        var isExist = em.createQuery("select ue from PhotoEntity ue where ue.id = :id", PhotoEntity.class)
                .setParameter("id", id).getResultList();
        if(isExist.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}