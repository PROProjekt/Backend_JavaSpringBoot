DROP TABLE user_ticket;
DROP TABLE ticket;

CREATE TABLE reservation(
      id BIGINT NOT NULL,
      screening_id BIGINT,
      movie_id BIGINT,
      user_id BIGINT,

      CONSTRAINT fk_screening_id FOREIGN KEY (screening_id) REFERENCES screening(id),
      CONSTRAINT fk_movie_id FOREIGN KEY (movie_id) REFERENCES movie(id),
      CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "user"(id),
      PRIMARY KEY(id)
);