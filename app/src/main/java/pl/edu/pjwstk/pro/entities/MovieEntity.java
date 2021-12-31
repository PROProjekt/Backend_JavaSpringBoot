package pl.edu.pjwstk.pro.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie", schema = "public")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String year;
    private String description;
    private String type;
    private String poster;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "movie_screening",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "screening_id") }
    )
    Set<ScreeningEntity> screenings = new HashSet<>();

    public void setScreenings(Set<ScreeningEntity> screenings) {
        this.screenings = screenings;
    }

    public Set<ScreeningEntity> getScreenings() {
        return screenings;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPoster() {
        return poster;
    }

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
}
