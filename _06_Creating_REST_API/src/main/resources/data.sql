INSERT INTO user_details(id, name, birth_date) VALUES (10001, 'Fernando', current_date());
INSERT INTO user_details(id, name, birth_date) VALUES (10002, 'Rafael', '1990-01-01');
INSERT INTO user_details(id, name, birth_date) VALUES (10003, 'Gabriel', '1994-09-23');
INSERT INTO user_details(id, name, birth_date) VALUES (10004, 'Lucas', '1996-12-25');

INSERT INTO post_details(id, description, user_id) VALUES (20001, 'I am learning Spring Boot', 10001);
INSERT INTO post_details(id, description, user_id) VALUES (20002, 'RESTful Web Services with Spring Boot', 10001);
INSERT INTO post_details(id, description, user_id) VALUES (20003, 'Quarkus - Supersonic Subatomic Java', 10002);
INSERT INTO post_details(id, description, user_id) VALUES (20004, 'Learning Angular', 10003);
INSERT INTO post_details(id, description, user_id) VALUES (20005, 'Getting started with Kotlin', 10003);
INSERT INTO post_details(id, description, user_id) VALUES (20006, 'The Complete Node.js Developer Course', 10003);
INSERT INTO post_details(id, description, user_id) VALUES (20007, 'The Complete React Developer Course', 10003);
INSERT INTO post_details(id, description, user_id) VALUES (20008, 'Learning Docker', 10004);
INSERT INTO post_details(id, description, user_id) VALUES (20009, 'Learning Kubernetes', 10004);
INSERT INTO post_details(id, description, user_id) VALUES (20010, 'Learning AWS', 10004);
