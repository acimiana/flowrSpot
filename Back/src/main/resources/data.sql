INSERT INTO user (id, first_name, last_name, password, username, user_Role)
VALUES (1, 'Alex', 'Adams', 'aaaaa', 'aaaaa', 'USER');
INSERT INTO user (id, first_name, last_name, password, username, user_Role)
VALUES (2, 'Ben', 'Beal', 'bbbbb', 'bbbbb', 'ADMIN');
INSERT INTO user (id, first_name, last_name, password, username, user_Role)
VALUES (3, 'Carl', 'Christie', 'ccccc', 'ccccc', 'USER');

INSERT INTO flower (id, name, latin_name, sightings_no, profile_picture) 
VALUES (1, 'Sunflower', 'Helianthus annuus', 1, 'aaa.jpg');
INSERT INTO flower (id, name, latin_name, sightings_no, profile_picture) 
VALUES (2, 'Rose', 'Rosa', 2, 'bbb.jpg');
INSERT INTO flower (id, name, latin_name, sightings_no, profile_picture) 
VALUES (3, 'Tulip', 'Tulipa', 3, 'ccc.jpg');

INSERT INTO sighting (id, flower_id, name, description, latitude, longitude, user_id)
VALUES (1, 2, 'first', 'aaaaaaaaaaaaaaa', 11, 11, 2);
INSERT INTO sighting (id, flower_id, name, description, latitude, longitude, user_id)
VALUES (2, 3, 'second', 'bbbbbbbbbbbbbb', 22, 22, 3);
INSERT INTO sighting (id, flower_id, name, description, latitude, longitude, user_id)
VALUES (3, 1, 'third', 'ccccccccccccccc', 11, 11, 1);
