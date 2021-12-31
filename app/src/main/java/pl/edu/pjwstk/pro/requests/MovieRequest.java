package pl.edu.pjwstk.pro.requests;

public class MovieRequest {
    private String title;
    private String year;
    private String description;
    private String type;
    private String poster;

    public MovieRequest(String title, String year, String description, String type, String poster) {
        this.title = title;
        this.year = year;
        this.description = description;
        this.type = type;
        this.poster = poster;
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
}
