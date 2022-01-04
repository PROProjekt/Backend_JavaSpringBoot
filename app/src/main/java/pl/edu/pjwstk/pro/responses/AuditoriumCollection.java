package pl.edu.pjwstk.pro.responses;

import java.util.List;

public class AuditoriumCollection {
    private List<Auditorium> auditoriumList;

    public AuditoriumCollection(List<Auditorium> auditoriumList) {
        this.auditoriumList = auditoriumList;
    }

    public List<Auditorium> getAuditoriumList() {
        return auditoriumList;
    }
}
