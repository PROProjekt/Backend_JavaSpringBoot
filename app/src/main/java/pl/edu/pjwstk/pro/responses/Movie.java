package pl.edu.pjwstk.pro.responses;

import java.util.List;

public class Movie {
    private Long id;
    private String title;
    private String year;
    private String description;
    private String type;
    private String poster;
    private List<Screening> screeningList;

    public Movie(Long id, String title, String year, String description, String type, String poster, List<Screening> screeningList) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.description = description;
        this.type = type;
        this.poster = poster;
        this.screeningList = screeningList;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getPoster() {
        return poster;
    }

    public List<Screening> getScreeningList() {
        return screeningList;
    }
}
