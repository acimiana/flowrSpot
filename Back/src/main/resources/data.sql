INSERT INTO user (id, first_name, last_name)
VALUES (1, 'Alex', 'Adams');
INSERT INTO user (id, first_name, last_name)
VALUES (2, 'Ben', 'Beal');
INSERT INTO user (id, first_name, last_name)
VALUES (3, 'Carl', 'Christie');

INSERT INTO flower (id, name, latin_name, sightings_no, profile_picture, favorite) 
VALUES (1, 'Sunflower', 'Helianthus annuus', 1, 'aaa.jpg', true);
INSERT INTO flower (id, name, latin_name, sightings_no, profile_picture, favorite) 
VALUES (2, 'Rose', 'Rosa', 2, 'bbb.jpg', false);
INSERT INTO flower (id, name, latin_name, sightings_no, profile_picture, favorite) 
VALUES (3, 'Tulip', 'Tulipa', 3, 'ccc.jpg', true);

INSERT INTO sighting (id, flower_id, name, description, latitude, longitude)
VALUES (1, 2, 'first', 'aaaaaaaaaaaaaaa', 11, 11);
INSERT INTO sighting (id, flower_id, name, description, latitude, longitude)
VALUES (2, 3, 'second', 'bbbbbbbbbbbbbb', 22, 22);
INSERT INTO sighting (id, flower_id, name, description, latitude, longitude)
VALUES (3, 1, 'third', 'ccccccccccccccc', 11, 11);
