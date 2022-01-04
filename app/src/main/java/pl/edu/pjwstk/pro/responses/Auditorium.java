package pl.edu.pjwstk.pro.responses;

import java.util.List;

public class Auditorium {
    private Long screening_id;
    private List<Seats> seatsList;

    public Auditorium(Long screening_id, List<Seats> seatsList) {
        this.screening_id = screening_id;
        this.seatsList = seatsList;
    }

    public Long getScreening_id() {
        return screening_id;
    }

    public List<Seats> getSeatsList() {
        return seatsList;
    }
}
