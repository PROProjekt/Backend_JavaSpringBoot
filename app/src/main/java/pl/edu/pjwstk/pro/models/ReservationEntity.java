package pl.edu.pjwstk.pro.entities;

import javax.persistence.*;

@Entity
@Table(name = "reservation", schema = "public")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "screening_id")
    private ScreeningEntity screeningEntity;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movieEntity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public Long getId() {
        return id;
    }

    public ScreeningEntity getScreeningEntity() {
        return screeningEntity;
    }

    public MovieEntity getMovieEntity() {
        return movieEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setScreeningEntity(ScreeningEntity screeningEntity) {
        this.screeningEntity = screeningEntity;
    }

    public void setMovieEntity(MovieEntity movieEntity) {
        this.movieEntity = movieEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
