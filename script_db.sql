CREATE OR REPLACE DATABASE gamifikator;
USE gamifikator;

CREATE TABLE users(
  email varchar(50),
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
);

ALTER TABLE `users` ADD PRIMARY KEY (`email`);

INSERT INTO `users` ( email, username, password ) VALUES ( 'test', 'test', 'test' );

CREATE TABLE applications(
  name varchar(50) NOT NULL,
  owner varchar(50) NOT NULL,
  description varchar(300),
	api_token varchar(36) NOT NULL UNIQUE,
  api_key varchar(36) NOT NULL UNIQUE
);

ALTER TABLE `applications` ADD PRIMARY KEY (`name`);
ALTER TABLE `applications` ADD FOREIGN KEY (`owner`) REFERENCES users(email);