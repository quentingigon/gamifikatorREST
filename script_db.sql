USE gamifikator;

create table users(
	  email varchar(50),
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    PRIMARY KEY (email)
);

create table applications(
    name varchar(50) NOT NULL,
    owner varchar(50) NOT NULL,
    description varchar(300),
	  api_token varchar(36) NOT NULL UNIQUE,
    api_key varchar(36) NOT NULL UNIQUE,
    foreign key (owner) references users(email)
    PRIMARY KEY (name)
);