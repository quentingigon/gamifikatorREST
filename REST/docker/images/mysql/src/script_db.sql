DROP DATABASE IF EXISTS gamifikator;
CREATE DATABASE IF NOT EXISTS gamifikator;
USE gamifikator;



CREATE TABLE user_entity
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  api_token VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE property_entity
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  rule_name VARCHAR(50) NOT NULL,
  value INT NOT NULL,
  operator VARCHAR(10) NOT NULL,
  api_token VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user_badges_entity
(
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  badge_id INT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE badge_entity
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  api_token VARCHAR(100) NOT NULL,
  level INT,
  icon VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE rule_entity
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  api_token VARCHAR(100) NOT NULL,
  badge_id INT,
  property_id INT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE application_entity
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  owner VARCHAR(50) NOT NULL,
  description VARCHAR(400),
	api_secret VARCHAR(100) UNIQUE,
  api_token VARCHAR(100) UNIQUE,
  is_deployed BOOL NOT NULL,
  PRIMARY KEY (id)
);


INSERT INTO application_entity
(name, owner, description, api_secret, api_token, is_deployed)
VALUES ("App1", "moi", "description", "secret", "token", false);


INSERT INTO user_entity
(name, api_token)
VALUES ("TestUser", "token");