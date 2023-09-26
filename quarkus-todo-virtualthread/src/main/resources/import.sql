INSERT INTO todo(id, title, completed, ordering, url) VALUES (1, 'Introduction to Imperative vs. Reactive', false, 1, null);
INSERT INTO todo(id, title, completed, ordering, url) VALUES (2, 'What is Project Loom?', false, 1, null);
INSERT INTO todo(id, title, completed, ordering, url) VALUES (3, 'Quarkus integrates Loom', false, 2, 'https://quarkus.io/guides/virtual-threads');
INSERT INTO todo(id, title, completed, ordering, url) VALUES (4, 'Find more demos on Daniel Oh TV', false, 3, 'https://bit.ly/danielohtv');
ALTER SEQUENCE todo_seq RESTART WITH 5;