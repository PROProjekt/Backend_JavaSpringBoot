package pl.edu.pjwstk.pro.responses;

public class Seats {
    private Long id;
    private String seat_number;
    private boolean available;

    public Long getId() {
        return id;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public boolean isAvailable() {
        return available;
    }

    public Seats(Long id, String seat_number, boolean available) {
        this.id = id;
        this.seat_number = seat_number;
        this.available = available;
    }
}
