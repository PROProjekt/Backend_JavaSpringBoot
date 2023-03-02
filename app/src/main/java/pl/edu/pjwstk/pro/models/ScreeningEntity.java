package pl.edu.pjwstk.pro.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "screening", schema = "public")
public class ScreeningEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String day;
    private String time;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "movie_screening",
            joinColumns = { @JoinColumn(name = "screening_id") },
            inverseJoinColumns = { @JoinColumn(name = "movie_id") }
    )
    Set<MovieEntity> movies = new HashSet<>();

    public void setMovies(Set<MovieEntity> movies) {
        this.movies = movies;
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

    public Set<MovieEntity> getMovies() {
        return movies;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
