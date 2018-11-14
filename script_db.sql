CREATE OR REPLACE DATABASE gamifikator;
USE gamifikator;

CREATE TABLE user
(
  email VARCHAR(50) NOT NULL,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  isAdmin BOOL NOT NULL,
  isSuspended BOOL NOT NULL,
  PRIMARY KEY (email)
);

CREATE TABLE application
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  owner VARCHAR(50) NOT NULL,
  description VARCHAR(300),
	api_token VARCHAR(36) UNIQUE,
  api_key VARCHAR(36) UNIQUE,
  PRIMARY KEY (id),
	FOREIGN KEY (owner) REFERENCES user(email)
);
