INSERT INTO screening (id,day,time) VALUES (1,'10-11-2021','10:30');
INSERT INTO screening (id, day, time) VALUES (2,'11-11-2021','11:30');
INSERT INTO screening (id, day, time) VALUES (3,'12-11-2021','12:30');
-- movie
INSERT INTO movie (id,title,year,description,type,poster) VALUES (1,'Spider-Man: No Way Home','2021','American fantasy action film based on the comic book series about the superhero of the same nickname by the publisher Marvel Comics. Jon Watts was responsible for directing, based on a screenplay by Chris McKenna and Erik Sommers. The film is produced by Kevin Feige and Amy Pascal.','fantasy','https://assets-a1.kompasiana.com/items/album/2021/12/15/no-way-home-poster-61b9b0ea06310e7d772e3783.jpg?t=o&v=410');
INSERT INTO movie (id,title,year,description,type,poster) VALUES (2,'Venom 2','2021','American superhero film featuring the Marvel Comics character of the same name, produced by Columbia Pictures in association with Marvel and Tencent Pictures. Distributed by Sony Pictures Releasing, it is the first film in Sony s Spider-Man Universe.','fantasy','https://fwcdn.pl/fpo/65/19/836519/7973189.3.jpg');
INSERT INTO movie (id,title,year,description,type,poster) VALUES (3,'Diuna','2021',' American science fiction film directed by Denis Villeneuve and based on Frank Herbert''s novel of the same name, adapting the first half of the novel.','Sci-Fi','https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQchwjRToXF4QCUlL68lrISf_kc8yJyCOTTAGmFYr4qJav_sUom');
-- movie_screening
INSERT INTO movie_screening (screening_id,movie_id) VALUES (1,1);
INSERT INTO movie_screening (screening_id,movie_id) VALUES (2,2);
INSERT INTO movie_screening (screening_id,movie_id) VALUES (3,3);