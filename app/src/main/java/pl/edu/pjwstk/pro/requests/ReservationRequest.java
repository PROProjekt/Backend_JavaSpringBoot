package pl.edu.pjwstk.pro.requests;

public class ReservationRequest {
    private Long screening_id;
    private Long movie_id;
    public Long getMovie_id() {
        return movie_id;
    }

    public Long getScreening_id() {
        return screening_id;
    }
}
