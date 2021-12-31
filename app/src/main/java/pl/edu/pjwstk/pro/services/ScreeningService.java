package pl.edu.pjwstk.pro.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.entities.ScreeningEntity;
import pl.edu.pjwstk.pro.exceptions.EntityNotFoundException;

import javax.persistence.EntityManager;


@Service
@Transactional
public class ScreeningService {
    private final EntityManager em;

    public ScreeningService(EntityManager em) {
        this.em = em;
    }

    public void saveScreening(String day, String time){
        var screening = new ScreeningEntity();
        screening.setDay(day);
        screening.setTime(time);

        em.persist(screening);
    }
    public void editScreening(Long id, String day, String time){
        if(!screeningExist(id)){
            throw new EntityNotFoundException();
        }else{
            var screening = findScreening(id);
            screening.setTime(time);
            screening.setDay(day);

            em.merge(screening);
        }
    }
    public boolean screeningExist(Long id) {
        var isExist =em.createQuery("select ue from ScreeningEntity ue where ue.id = :id", ScreeningEntity.class)
                .setParameter("id", id).getResultList();
        return !isExist.isEmpty();
    }
    public ScreeningEntity findScreening(Long id){
        return em.createQuery("select ue from ScreeningEntity ue where ue.id = :id", ScreeningEntity.class)
                .setParameter("id", id).getSingleResult();
    }
}
