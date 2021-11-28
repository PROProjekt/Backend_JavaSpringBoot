CREATE TABLE user_role(
   id BIGINT NOT NULL PRIMARY KEY,
   role VARCHAR(20)

);
CREATE TABLE "user"(
   id BIGINT NOT NULL,
   username VARCHAR(100) NOT NULL,
   password VARCHAR(100) NOT NULL,
   role_id BIGINT NOT NULL,

   CONSTRAINT fk_user_role_id FOREIGN KEY (role_id) REFERENCES user_role(id),
   PRIMARY KEY(id)
);

