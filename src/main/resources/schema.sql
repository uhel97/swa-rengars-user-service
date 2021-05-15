DROP TABLE IF EXISTS contacts;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS users;

CREATE TABLE `users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` varchar(100) NOT NULL UNIQUE,
  `password` varchar(255) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  gender TINYINT DEFAULT NULL,
  role TINYINT DEFAULT NULL,
  birth_date DATE DEFAULT NULL,
  created_at timestamp NOT NULL DEFAULT current_timestamp,
  updated_at timestamp DEFAULT current_timestamp
);

CREATE TABLE `contacts` (
  user_id BIGINT(20) NOT NULL PRIMARY KEY,
  `email` varchar(255) NOT NULL UNIQUE,
  `phoneNumber` varchar(20) DEFAULT NULL,
  linkedin varchar(255) DEFAULT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE `addresses` (
  user_id BIGINT(20) NOT NULL PRIMARY KEY,
  `address` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `zip_code` varchar(20) DEFAULT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);