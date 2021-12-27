package pl.edu.pjwstk.pro.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.entities.AuditoriumEntity;
import pl.edu.pjwstk.pro.requests.AuditoriumRequest;

import javax.persistence.EntityManager;
import pl.edu.pjwstk.pro.exceptions.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class AuditoriumService {
    private final EntityManager em;

    public AuditoriumService(EntityManager em) {
        this.em = em;
    }

    public void saveAuditorium(AuditoriumRequest auditoriumRequest){
        var auditorium = new AuditoriumEntity();
        auditorium.setScreening(auditoriumRequest.getScreening());
        auditorium.setSeat_number(auditoriumRequest.getSeat_number());

        em.persist(auditorium);
    }
    public void editAuditorium(Long id, AuditoriumRequest auditoriumRequest){
        if(auditoriumExist(id)){
            var auditoriumToEdit = em.createQuery("select ue from AuditoriumEntity ue where ue.id = :id", AuditoriumEntity.class)
                    .setParameter("id",id).getSingleResult();
            auditoriumToEdit.setSeat_number(auditoriumRequest.getSeat_number());
            auditoriumToEdit.setScreening(auditoriumRequest.getScreening());

            em.merge(auditoriumToEdit);
        }else{
            throw new EntityNotFoundException();
        }
    }
    public List<String> getSeats(){
        var seats =  em.createQuery("select ue.seat_number from AuditoriumEntity ue", String.class)
                .getResultList();
        return seats;
    }
    public boolean auditoriumExist(Long id){
        var isExist =  em.createQuery("select ue from AuditoriumEntity ue where ue.id = :id", AuditoriumEntity.class)
                .setParameter("id",id).getResultList();
        if(isExist.isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}
