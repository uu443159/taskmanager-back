-- noinspection SqlNoDataSourceInspectionForFile

INSERT INTO user_role
VALUES (1,'ROLE_ADMIN'),
       (2,'ROLE_USER');

INSERT INTO user (first_name, last_name, password, user_name, role_id)
VALUES ('Jack', 'Sparrow', '$2a$10$ehO/wJL6lfFsLGtldTRLvOdJ04XVMe2SvsraapBmsT6IGMhRURZnq', 'jacksparrow', 2),
       ('Hermione', 'Granger', '$2a$10$ehO/wJL6lfFsLGtldTRLvOdJ04XVMe2SvsraapBmsT6IGMhRURZnq', 'hermione', 2);

INSERT INTO task (title, description, user_name)
VALUES ('Black Pearl', 'To get back my ship!', 'jacksparrow'),
       ('Hat', 'Where is my hat? Need to find it', 'jacksparrow'),
       ('Rum', 'I need money to buy a bottle of rum', 'jacksparrow'),
       ('Harry', "I should save Harry's butt...", 'hermione'),
       ('Study', "Study, girl, study, you're not clever enough!", 'hermione'),
       ('Pants', 'I should buy those lovely pants', 'hermione'),
       ('Horcrux', 'Still 1 left. We should finish Nagini', 'hermione');