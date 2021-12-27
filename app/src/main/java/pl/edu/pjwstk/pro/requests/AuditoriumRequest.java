package pl.edu.pjwstk.pro.requests;

import pl.edu.pjwstk.pro.entities.ScreeningEntity;

public class AuditoriumRequest {
    private String seat_number;
    private ScreeningEntity screening;

    public AuditoriumRequest(String seat_number, ScreeningEntity screening) {
        this.seat_number = seat_number;
        this.screening = screening;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public void setScreening(ScreeningEntity screening) {
        this.screening = screening;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public ScreeningEntity getScreening() {
        return screening;
    }
}
