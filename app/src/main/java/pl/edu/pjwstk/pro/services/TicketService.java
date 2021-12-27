package pl.edu.pjwstk.pro.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.entities.TicketEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class TicketService {
    private final EntityManager em;

    public TicketService(EntityManager em) {
        this.em = em;
    }

    public void setPrice(double price){
        var ticket = new TicketEntity();
        ticket.setPrice(price);
        em.persist(ticket);
    }
    public void editPrice(Long id,double price){
        if(ticketExist(id)){
            var ticket = new TicketEntity();
            ticket.setPrice(price);
            em.merge(ticket);
        }else{
            throw new EntityNotFoundException();
        }
    }
    public boolean ticketExist(Long id) {
        var isExist = em.createQuery("select ue from TicketEntity ue where ue.id = :id", TicketEntity.class)
                .setParameter("id", id).getResultList();
        if(isExist.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
