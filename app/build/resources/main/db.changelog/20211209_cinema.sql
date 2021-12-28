CREATE TABLE screening(
     id BIGINT NOT NULL,
     day DATE NOT NULL,
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
    id BIGINT NOT NULL,
    ticket_id BIGINT,
    user_id BIGINT,
    CONSTRAINT fk_ticket_id FOREIGN KEY (ticket_id) REFERENCES ticket(id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "user"(id),
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
      year VARCHAR(100) NOT NULL,
      description VARCHAR(500),
      type VARCHAR(500),
--       imbdbID?
      screening_id BIGINT,
      CONSTRAINT fk_screening_id FOREIGN KEY (screening_id) REFERENCES screening(id),
      PRIMARY KEY(id)
);

