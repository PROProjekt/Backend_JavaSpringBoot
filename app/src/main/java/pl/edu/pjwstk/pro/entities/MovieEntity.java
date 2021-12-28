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

    @Column(name="year")
    private String year;

    @Column(name="description")
    private String description;

    @Column(name="type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private ScreeningEntity screening;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setScreening(ScreeningEntity screening) {
        this.screening = screening;
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

    public ScreeningEntity getScreening() {
        return screening;
    }
}
