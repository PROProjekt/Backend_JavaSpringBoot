package pl.edu.pjwstk.pro.responses;

public class Screening {
    private Long id;
    private String day;
    private String time;

    public Screening(Long id, String day, String time) {
        this.id = id;
        this.day = day;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }
}
