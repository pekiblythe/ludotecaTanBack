INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Super Mario Wonder', '8', 3, 5);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Blasphemous', '16', 2, 4);

INSERT INTO clients(name) VALUES ('Paula');
INSERT INTO clients(name) VALUES ('Paco');
INSERT INTO clients(name) VALUES ('Enrique');
INSERT INTO clients(name) VALUES ('Pilar');

INSERT INTO prestamo(game_id, clients_id, datein, dateout) values (1, 2, '2018-01-01', '2023-08-03');
INSERT INTO prestamo(game_id, clients_id, datein, dateout) values (3, 1, '2018-01-01', '2023-08-03');
INSERT INTO prestamo(game_id, clients_id, datein, dateout) values (6, 4, '2018-01-01', '2023-08-03');
INSERT INTO prestamo(game_id, clients_id, datein, dateout) values (2, 3, '2018-01-01', '2023-08-03');
INSERT INTO prestamo(game_id, clients_id, datein, dateout) values (1, 1, '2018-02-03', '2023-08-03');
INSERT INTO prestamo(game_id, clients_id, datein, dateout) values (4, 1, '2018-01-01', '2023-08-03');
INSERT INTO prestamo(game_id, clients_id, datein, dateout) values (5, 2, '2018-01-01', '2023-08-03');
INSERT INTO prestamo(game_id, clients_id, datein, dateout) values (7, 1, '2023-07-07', '2023-08-23');
INSERT INTO prestamo(game_id, clients_id, datein, dateout) values (8, 4, '2023-07-01', '2023-07-15');
