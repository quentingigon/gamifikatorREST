USE gamifikator;

INSERT INTO USER(email, username, password, isAdmin, isSuspended, isPasswordValid)
VALUES ('jeanfrancois@gmail.com', 'jean neige', 'test', false, false, true),
	   ('marie@gmail.com', 'mariadb', 'test', false, false, true),
	   ('macmoudi@gmail.com', 'macmoudi', 'test', false, false, true),
	   ('amtIsBest@gmail.com', 'AMT', 'test', false, false, true);

INSERT INTO APPLICATION(name, owner_email, description, isDeployed)
VALUES ('Super app', 'jeanfrancois@gmail.com', 'generic description', false),
     ('Great app', 'jeanfrancois@gmail.com', 'generic description2', false),
     ('Awesome app', 'macmoudi@gmail.com', 'generic description3', false),
     ('Incredible app', 'jeanfrancois@gmail.com', 'generic description4', false);