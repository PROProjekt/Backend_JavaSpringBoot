package pl.edu.pjwstk.pro.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie",schema = "public")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="date")
    private String date;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private ScreeningEntity screening;

    @OneToMany(mappedBy = "movie", cascade = {CascadeType.ALL})
    private List<PhotoEntity> photos;

    public MovieEntity() {
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setScreening(ScreeningEntity screening) {
        this.screening = screening;
    }

    public Long getId() {
        return id;
    }

    public void setPhotos(List<PhotoEntity> photos) {
        this.photos = photos;
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

    public ScreeningEntity getScreening() {
        return screening;
    }
}
