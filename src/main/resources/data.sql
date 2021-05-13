
-- DELETE FROM permissions_roles;
-- DELETE FROM permissions;
DELETE FROM users_roles;
DELETE FROM roles;

DELETE FROM contacts;
DELETE FROM addresses;
DELETE FROM users;

-- INSERT INTO permissions(id, permission, note) VALUES (1, 'LOGIN', 'User Login');
-- INSERT INTO permissions(id, permission, note) VALUES (2, 'VIEW_PROFILE', 'View user profile');
-- INSERT INTO permissions(id, permission, note) VALUES (3, 'ADMIN_USER_DATA', 'Manage user data');

-- INSERT INTO permissions(id, permission, note, enabled) VALUES (4, 'ADMIN_STATISTICS', 'View statistical graphs', false);

INSERT INTO roles(id, role) VALUES (1, 'APPLICANT');
INSERT INTO roles(id, role) VALUES (2, 'HEADHUNTER');

-- INSERT INTO permissions_roles(permission_id, role_id) VALUES (1, 1);
-- INSERT INTO permissions_roles(permission_id, role_id) VALUES (2, 1);

-- INSERT INTO permissions_roles(permission_id, role_id) VALUES (1, 2);
-- INSERT INTO permissions_roles(permission_id, role_id) VALUES (2, 2);
-- INSERT INTO permissions_roles(permission_id, role_id) VALUES (3, 2);


INSERT INTO users(id, username, name, surname, gender, createdAt) VALUES (1, 'andrea', 'Andrea', 'Test', 0, '2021-01-01 12:00:00');
INSERT INTO users(id, username, name, surname, gender, createdAt) VALUES (2, 'mario', 'Mario', 'Rossi', 0, '2021-01-01 12:00:00');
INSERT INTO users(id, username, name, surname, gender, createdAt) VALUES (3, 'stefania', 'Stefania', 'Verdi', 1, '2021-01-01 12:00:00');
INSERT INTO users(id, username, name, surname, gender, createdAt) VALUES (4, 'veronica', 'Veronica', 'Gialli', 1, '2021-01-01 12:00:00');
INSERT INTO users(id, username, name, surname, gender, createdAt) VALUES (5, 'mark', 'Mark', 'Green', 0, '2021-01-01 12:00:00');
INSERT INTO users(id, username, name, surname, gender, createdAt) VALUES (6, 'paul', 'Paul', 'Ludwing', 0, '2021-01-01 12:00:00');
INSERT INTO users(id, username, name, surname, gender, createdAt) VALUES (7, 'jennifer', 'Jennifer', 'Red', 0, '2021-01-01 12:00:00');
INSERT INTO users(id, username, name, surname, gender, createdAt) VALUES (8, 'karina', 'Karina', 'Yellow', 1, '2021-01-01 12:00:00');

-- UPDATE users SET ENABLED = false WHERE id = 6;

UPDATE users SET birth_date = '1977-08-14' WHERE id = 1;
-- UPDATE users SET secured = true WHERE id = 1;

INSERT INTO users_roles(user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles(user_id, role_id) VALUES (1, 2);

INSERT INTO users_roles(user_id, role_id) VALUES (2, 1);
INSERT INTO users_roles(user_id, role_id) VALUES (3, 1);
INSERT INTO users_roles(user_id, role_id) VALUES (4, 1);
INSERT INTO users_roles(user_id, role_id) VALUES (5, 1);
INSERT INTO users_roles(user_id, role_id) VALUES (6, 1);
INSERT INTO users_roles(user_id, role_id) VALUES (7, 1);
INSERT INTO users_roles(user_id, role_id) VALUES (8, 1);

INSERT INTO contacts(user_id, email, phoneNumber, linkedin) VALUES (1, 'andrea.test@gmail.com', NULL, NULL);
INSERT INTO contacts(user_id, email, phoneNumber, linkedin) VALUES (2, 'mario.rossi@gmail.com', NULL, 'test contact note on mario rossi');
INSERT INTO contacts(user_id, email, phoneNumber, linkedin) VALUES (3, 'stefania.verdi@gmail.com', NULL, NULL);
INSERT INTO contacts(user_id, email, phoneNumber, linkedin) VALUES (4, 'veronica.gialli@gmail.com', NULL, NULL);
INSERT INTO contacts(user_id, email, phoneNumber, linkedin) VALUES (5, 'mark.green@gmail.com', NULL, NULL);
INSERT INTO contacts(user_id, email, phoneNumber, linkedin) VALUES (6, 'paul.ludwing@gmail.com', NULL, NULL);
INSERT INTO contacts(user_id, email, phoneNumber, linkedin) VALUES (7, 'jennifer.red@gmail.com', NULL, NULL);
INSERT INTO contacts(user_id, email, phoneNumber, linkedin) VALUES (8, 'karina.yellow@gmail.com', NULL, NULL);

insert into addresses(user_id, address, address2, city, country, zip_code) values (2, 'Via Filzi 2', 'Borgo Teresiano', 'Florence', 'Italy', '50100');
insert into addresses(user_id, address, address2, city, country, zip_code) values (7, 'Piazza Grande 12', 'Gran canal', 'Venice', 'Italy', '30100');
insert into addresses(user_id, address, address2, city, country, zip_code) values (8, 'Via Roma 2', 'Borgo Teresiano', 'Trieste', 'Italy', '34100');
