# Teaching-HEIGVD-AMT-2018-Project


## WP 1: UI to manage developer accounts and applications

### Overview

There are many SaaS services that target application developers, just like our gamification service. The workflow is always the same:

1. **An application developer may work on several applications** (he may develop an online shop, a collaborative editor, a sports tracker, etc.). An application developer may want to gamify all of these projects.
2. The application developer visits the gamification service website. He registers and **creates a developer account**. He provides the usual information (contact details, e-mail, password, etc.).
3. The application developer then **creates one application for each of his projects**. He can individually configure each application. The service generates an API key and an API secret for every application. These attributes are not used in the WP 1 features. However, when we move to WP 2 and when the code of the gamified applications needs to communicate via the gamification REST APIs, they will use these attributes as credentials.
4. The **administrator** of the gamification service can also access the console. Because he has a special role, he has access to other features: he can see the list of registered accounts, disable them, reset passwords, etc.


### Instructions to deploy
The deployement is done via the following command:

    docker-compose up --build

After that, you can connect to the website with the url:

    localhost:8080/gamifikator/login
    
If you want to make SQL operations on DB, connect to it via:

    docker exec -it <id of db_docker> bash

If you want to completly reset the database, you can do that with the command:

    docker-compose down
    
## What does not work
We can't connect to the JavaMail Resource in payara, even after setting all the right parameters. The session is always null.