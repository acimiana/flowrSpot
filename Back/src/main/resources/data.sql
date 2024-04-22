INSERT INTO user (id, first_name, last_name, username, password)
VALUES (1, 'Alex', 'Adams', 'aaaaa', 'aaaaa');
INSERT INTO user (id, first_name, last_name, username, password)
VALUES (2, 'Ben', 'Beal', 'bbbbb', 'bbbbb');
INSERT INTO user (id, first_name, last_name, username, password)
VALUES (3, 'Carl', 'Christie', 'ccccc', 'ccccc');

INSERT INTO flower (id, name, latin_name, sightings_no, profile_picture, favorite) 
VALUES (1, 'Sunflower', 'Helianthus annuus', 1, 'aaa.jpg', true);
INSERT INTO flower (id, name, latin_name, sightings_no, profile_picture, favorite) 
VALUES (2, 'Rose', 'Rosa', 2, 'bbb.jpg', false);
INSERT INTO flower (id, name, latin_name, sightings_no, profile_picture, favorite) 
VALUES (3, 'Tulip', 'Tulipa', 3, 'ccc.jpg', true);

INSERT INTO sighting (id, flower_id, name, description, latitude, longitude, user_id)
VALUES (1, 2, 'first', 'aaaaaaaaaaaaaaa', 11, 11, 2);
INSERT INTO sighting (id, flower_id, name, description, latitude, longitude, user_id)
VALUES (2, 3, 'second', 'bbbbbbbbbbbbbb', 22, 22, 3);
INSERT INTO sighting (id, flower_id, name, description, latitude, longitude, user_id)
VALUES (3, 1, 'third', 'ccccccccccccccc', 11, 11, 1);
