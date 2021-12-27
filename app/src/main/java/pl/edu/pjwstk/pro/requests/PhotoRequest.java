package pl.edu.pjwstk.pro.requests;

import pl.edu.pjwstk.pro.entities.MovieEntity;

public class PhotoRequest {
    private String title;
    private int position;
    private MovieEntity movieEntity;

    public String getTitle() {
        return title;
    }

    public int getPosition() {
        return position;
    }

    public MovieEntity getMovieEntity() {
        return movieEntity;
    }
}
