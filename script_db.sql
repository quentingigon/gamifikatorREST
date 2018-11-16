CREATE OR REPLACE DATABASE gamifikator;
USE gamifikator;

CREATE TABLE USER
(
  email VARCHAR(50) NOT NULL,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  isAdmin BOOL NOT NULL,
  isSuspended BOOL NOT NULL,
  isPasswordValid BOOL NOT NULL,
  PRIMARY KEY (email)
);

CREATE TABLE APPLICATION
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  creator VARCHAR(50) NOT NULL,
  owner_email VARCHAR(50) NOT NULL,
  description VARCHAR(400),
	apisecret VARCHAR(100) UNIQUE,
  apikey VARCHAR(100) UNIQUE,
  isDeployed BOOL NOT NULL,
  PRIMARY KEY (id),
	FOREIGN KEY (owner_email) REFERENCES USER(email)
);
