package pl.edu.pjwstk.pro.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.entities.ScreeningEntity;
import pl.edu.pjwstk.pro.exceptions.EntityNotFoundException;
import pl.edu.pjwstk.pro.requests.ScreeningRequest;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class ScreeningService {
    private final EntityManager em;

    public ScreeningService(EntityManager em) {
        this.em = em;
    }

    public void saveScreening(ScreeningRequest screeningRequest){
        var screening = new ScreeningEntity();
        screening.setDay(screeningRequest.getDay());
        screening.setTime(screeningRequest.getTime());
        screening.setTicket(screeningRequest.getTicket());

        em.persist(screening);
    }
    public void editScreening(Long id,ScreeningRequest screeningRequest){
        if(screeningExist(id)){
            var screeningToEdit = em.createQuery("select ue from ScreeningEntity ue where ue.id = :id", ScreeningEntity.class)
                    .setParameter("id",id).getSingleResult();
            screeningToEdit.setDay(screeningRequest.getDay());
            screeningToEdit.setTime(screeningRequest.getTime());
            screeningToEdit.setTicket(screeningRequest.getTicket());

            em.merge(screeningToEdit);
        }else{
            throw new EntityNotFoundException();
        }
    }
    public List<ScreeningEntity> getAllScreening(){
        return em.createQuery("select ue from ScreeningEntity ue", ScreeningEntity.class)
                .getResultList();
    }
    public ScreeningEntity getSingleScreening(Long id){
        if(screeningExist(id)){
            return em.createQuery("select ue from ScreeningEntity ue where ue.id = :id", ScreeningEntity.class)
                    .setParameter("id", id).getSingleResult();
        }else{
            throw new EntityNotFoundException();
        }

    }
    public boolean screeningExist(Long id) {
        var isExist = em.createQuery("select ue from ScreeningEntity ue where ue.id = :id", ScreeningEntity.class)
                .setParameter("id", id).getResultList();
        if(isExist.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
