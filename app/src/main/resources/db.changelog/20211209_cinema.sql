DROP TABLE test1;
CREATE TABLE ticket(
     id BIGINT NOT NULL,
     price DECIMAL(10,2) NOT NULL,
     PRIMARY KEY(id)
);
ALTER TABLE "user" ADD COLUMN ticket_id BIGINT;
ALTER TABLE "user" ADD CONSTRAINT fk_ticket_id FOREIGN KEY (ticket_id) REFERENCES ticket(id);
CREATE TABLE screening(
      id BIGINT NOT NULL,
      day DATE NOT NULL,
      time VARCHAR(100) NOT NULL,
      ticket_id BIGINT NOT NULL,
      CONSTRAINT fk_ticket_id FOREIGN KEY (ticket_id) REFERENCES ticket(id),
      PRIMARY KEY(id)
);
CREATE TABLE auditorium(
     id BIGINT NOT NULL,
     seat_number VARCHAR(20) NOT NULL,
     screening_id BIGINT NOT NULL,
     CONSTRAINT fk_screening_id FOREIGN KEY (screening_id) REFERENCES screening(id),
     PRIMARY KEY(id)
);
CREATE TABLE movie(
      id BIGINT NOT NULL,
      title VARCHAR(100) NOT NULL,
      date VARCHAR(100) NOT NULL,
      description VARCHAR(500) NOT NULL,
      screening_id BIGINT NOT NULL,
      CONSTRAINT fk_screening_id FOREIGN KEY (screening_id) REFERENCES screening(id),
      PRIMARY KEY(id)
);
CREATE TABLE movie_photo(
      id BIGINT NOT NULL,
      name TEXT NOT NULL,
      position INT NOT NULL,
      movie_id BIGINT NOT NULL,

      PRIMARY KEY (id),
      CONSTRAINT fk_movie_id FOREIGN KEY (movie_id) REFERENCES  movie(id)
);