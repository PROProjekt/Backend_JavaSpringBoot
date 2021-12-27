package pl.edu.pjwstk.pro.requests;

import pl.edu.pjwstk.pro.entities.PhotoEntity;
import pl.edu.pjwstk.pro.entities.ScreeningEntity;

import java.util.List;

public class MovieRequest {
    private String title;
    private String date;
    private String description;
    private ScreeningEntity screeningEntity;
    private List<PhotoEntity> photoEntityList;

    public MovieRequest(String title, String date, String description, ScreeningEntity screeningEntity, List<PhotoEntity> photoEntityList) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.screeningEntity = screeningEntity;
        this.photoEntityList = photoEntityList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setScreeningEntity(ScreeningEntity screeningEntity) {
        this.screeningEntity = screeningEntity;
    }

    public void setPhotoEntityList(List<PhotoEntity> photoEntityList) {
        this.photoEntityList = photoEntityList;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public ScreeningEntity getScreeningEntity() {
        return screeningEntity;
    }

    public List<PhotoEntity> getPhotoEntityList() {
        return photoEntityList;
    }
}
