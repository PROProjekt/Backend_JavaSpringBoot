CREATE TABLE screening(
     id BIGINT NOT NULL,
     day VARCHAR(100) NOT NULL,
     time VARCHAR(100) NOT NULL,

     PRIMARY KEY(id)
);
CREATE TABLE ticket(
     id BIGINT NOT NULL,
     price DECIMAL(10,2) NOT NULL,
     screening_id BIGINT NOT NULL,
     CONSTRAINT fk_screening_id FOREIGN KEY (screening_id) REFERENCES screening(id),
     PRIMARY KEY(id)
);
CREATE TABLE user_ticket(
    ticket_id BIGINT,
    user_id BIGINT,
    CONSTRAINT fk_ticket_id FOREIGN KEY (ticket_id) REFERENCES ticket(id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "user"(id)
);
CREATE TABLE auditorium(
     id BIGINT NOT NULL,
     screening_id BIGINT NOT NULL,
     CONSTRAINT fk_screening_id FOREIGN KEY (screening_id) REFERENCES screening(id),
     PRIMARY KEY(id)
);
CREATE TABLE seat (
    id BIGINT NOT NULL,
    seat_number VARCHAR(20) NOT NULL,
    available BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE auditorium_seat(
    auditorium_id BIGINT NOT NULL,
    seat_id BIGINT NOT NULL,
    CONSTRAINT fk_auditorium_id FOREIGN KEY (auditorium_id) REFERENCES auditorium(id),
    CONSTRAINT fk_seat_id FOREIGN KEY (seat_id) REFERENCES seat(id)
);

CREATE TABLE movie(
      id BIGINT NOT NULL,
      title VARCHAR(100) NOT NULL,
      year VARCHAR(100) NOT NULL,
      description VARCHAR(500),
      type VARCHAR(500) NOT NULL,
      poster VARCHAR(500) NOT NULL,
--       imbdbID?
      PRIMARY KEY(id)
);
CREATE TABLE movie_screening(
    screening_id BIGINT,
    movie_id BIGINT,

    CONSTRAINT fk_screening_id FOREIGN KEY (screening_id) REFERENCES screening(id),
    CONSTRAINT fk_movie_id FOREIGN KEY (movie_id) REFERENCES movie(id)
);

