package pl.edu.pjwstk.pro.entities;

import javax.persistence.*;

@Entity
@Table(name = "movie_photo",schema = "public")
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String title;

    @Column(name="position")
    private int position;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPosition() {
        return position;
    }

    public MovieEntity getMovie() {
        return movie;
    }
}
