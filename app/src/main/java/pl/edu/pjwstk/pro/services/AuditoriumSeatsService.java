package pl.edu.pjwstk.pro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.exceptions.EntityNotFoundException;

import javax.persistence.EntityManager;

@Service
@Transactional
public class AuditoriumSeatsService {
    @Autowired
    SeatService seatService;
    @Autowired
    AuditoriumService auditoriumService;

    private final EntityManager em;

    public AuditoriumSeatsService(EntityManager em) {
        this.em = em;
    }

    public void connect(Long auditoriumId,Long seatId){
        if(seatService.seatExist(seatId) && auditoriumService.auditoriumExist(auditoriumId)){
            var seat = seatService.findSeat(seatId);
            var auditorium = auditoriumService.findAuditorium(auditoriumId);
            auditorium.getSeats().add(seat);
            em.merge(auditorium);
        }else{
            throw new EntityNotFoundException();
        }
    }

}
