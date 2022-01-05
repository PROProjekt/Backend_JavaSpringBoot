package pl.edu.pjwstk.pro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.entities.AuditoriumEntity;
import pl.edu.pjwstk.pro.requests.AuditoriumRequest;
import pl.edu.pjwstk.pro.responses.*;

import javax.persistence.EntityManager;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuditoriumService {
    @Autowired
    ScreeningService screeningService;
    private final EntityManager em;

    public AuditoriumService(EntityManager em) {
        this.em = em;
    }

    public void addAuditorium(AuditoriumRequest request){
        var auditorium = new AuditoriumEntity();
        auditorium.setScreeningEntity(screeningService.findScreening(request.getScreening_id()));
        em.persist(auditorium);
    }

    public boolean auditoriumExist(Long id) {
        var isExist =em.createQuery("select ue from AuditoriumEntity ue where ue.id = :id", AuditoriumEntity.class)
                .setParameter("id", id).getResultList();
        return !isExist.isEmpty();
    }
    public AuditoriumEntity findAuditorium(Long id){
        return em.createQuery("select ue from AuditoriumEntity ue where ue.id = :id", AuditoriumEntity.class)
                .setParameter("id", id).getSingleResult();
    }

    public AuditoriumCollection findAuditoriums() {
        var list = em.createQuery("select(ue) from AuditoriumEntity ue", AuditoriumEntity.class)
                .getResultList();
        var responseList = list.stream().map(aE -> new Auditorium(aE.getScreeningEntity().getId(),aE.getSeats()
                .stream().map(sE -> new Seats(sE.getId(), sE.getSeat_number(), sE.isAvailable())).collect(Collectors.toList()))).collect(Collectors.toList());

        return new AuditoriumCollection(responseList);
    }
}
