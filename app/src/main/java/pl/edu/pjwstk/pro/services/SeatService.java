package pl.edu.pjwstk.pro.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.entities.SeatEntity;
import pl.edu.pjwstk.pro.requests.SeatRequest;

import javax.persistence.EntityManager;

@Service
@Transactional
public class SeatService {
    private final EntityManager em;

    public SeatService(EntityManager em) {
        this.em = em;
    }
    public void saveSeat(SeatRequest request){
        var seat = new SeatEntity();
        seat.setSeat_number(request.getSeat_number());
        seat.setAvailable(request.isAvailable());
        em.persist(seat);
    }
    public boolean seatExist(Long id) {
        var isExist =em.createQuery("select ue from SeatEntity ue where ue.id = :id", SeatEntity.class)
                .setParameter("id", id).getResultList();
        return !isExist.isEmpty();
    }
    public SeatEntity findSeat(Long id){
        return em.createQuery("select ue from SeatEntity ue where ue.id = :id", SeatEntity.class)
                .setParameter("id", id).getSingleResult();
    }
    public void setSeatAvailable(Long id){
        var seat = findSeat(id);
        seat.setAvailable(true);
        em.merge(seat);
    }
    public void setSeatTaken(Long id){
        var seat = findSeat(id);
        seat.setAvailable(false);
        em.merge(seat);
    }
}
