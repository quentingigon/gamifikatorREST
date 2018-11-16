# Teaching-HEIGVD-AMT-2018-Project


## WP 1: UI to manage developer accounts and applications

### Overview

There are many SaaS services that target application developers, just like our gamification service. The workflow is always the same:

1. **An application developer may work on several applications** (he may develop an online shop, a collaborative editor, a sports tracker, etc.). An application developer may want to gamify all of these projects.
2. The application developer visits the gamification service website. He registers and **creates a developer account**. He provides the usual information (contact details, e-mail, password, etc.).
3. The application developer then **creates one application for each of his projects**. He can individually configure each application. The service generates an API key and an API secret for every application. These attributes are not used in the WP 1 features. However, when we move to WP 2 and when the code of the gamified applications needs to communicate via the gamification REST APIs, they will use these attributes as credentials.
4. The **administrator** of the gamification service can also access the console. Because he has a special role, he has access to other features: he can see the list of registered accounts, disable them, reset passwords, etc.

### Implementation choices

- We have not seen REST APIs in the course yet, so they are not required for this work package. We can implement everything by applying the MVC pattern using core Java EE technologies (servlets, JSPs).
- Likewise, there is no need for Javascript on the client side.
- We use Enterprise Java Beans (EJBs) to implement business services. One objective is to experiment with EJBs and transaction demarcation.
- This WP will be done following the traditional Java EE development cycle (deployment of a .jar file in the application server of your file). Springboot will be used in WP2. Your deliverable is a ready-to-use docker topology, with containers for your application and your database.

### Specifications: functional and non-functional requirements

| Description                                                  | Functional / non-functional | Points |
| ------------------------------------------------------------ | --------------------------- | ------ |
| As an **application developer**, I can access the web UI and register with the gamification service (create a `developer account`). I provide basic info (`name`, etc.), my `e-mail address` is my user identifier. | FR                          | 0.5 |
| As an **application developer**, I can login and logout from the gamification service web UI. When I login, I have access to my profile information and to my list of applications. When I have logged out and try to access a protected page, I am redirected to the login page. | FR |0.5|
| As an **application developer**, I can manage my applications (CRUD operations). Every application has at least a `name`, a `description`, an `API key` and an `API secret` (the last 2 are unique and generated by the service). | FR                          | 0.5 |
| As an **administrator**, I can see a list of registered application developers, with their details. The user interface is designed to work well even if the application developer has created 1'000 applications (pagination). | FR                          | 0.5 |
| As an **administrator**, I can suspend an account. When the developer tries to login, he is blocked and sees an informative error message. | FR                          | 0.5 |
| As an **administrator**, I can reset a user password. The password is automatically generated and sent by e-mail to the application developer. When the developer logs in, he is obliged to change his password immediately. | FR |0.5|
| As a **user**, I can change my username. | FR |0.5|
| As a **user**, I can change the website theme. | FR |0.5|
| | **Total** | **4.0** |
| As a **user**, I have a good UI/UX experience (nice design, clear navigation, informative error messages, etc.) | NFR-usability |0.5|
| As a **spiritual guide**, I can clone the repo, move to a documented repository and type `docker-compose up` to start the system. I can then access it with my browser. | NFR-maintainability         | 0.5 |
| As a **spiritual guide**, I find a report named `TESTING_FUNCTIONAL.md` which describes how to execute **automated functional tests**. At the minimum, there should be a scenario with the following steps: developer creates an account, developer logs in, developer creates 25 pages, developer browses the list of applications (3 pages of 10, 10 and 5 applications), developer logs out, developer tries to go back to the list of applications and is redirected to login page. The tests should include assertions, so that changing the code (e.g. introducing a bug) breaks the code. The report should describe and document a concrete example (with screenshots). | NFR-testability             | 1.0 |
| As a **spiritual guide**, I find a report named `TESTING_NON_FUNCTIONAL_PAGINATION.md`which describes which **non-functional tests** have been implemented and run. At the minimum, the report answers this question: "What is the impact of using pagination between the business and the resources tier?". The report should introduce the issue, describe the design of an experiment, document the experimental results and interpret the data. | NFR-testability             | 1.0 |
| As a spiritual guide, I find a report named `TESTING_NON_FUNCTIONAL_TRANSACTIONS`, which describes an experiment that has been done to prove that transactions work as expected with **Enterprise Java Beans** (especially in the case of rollbacks). The report describes the related code. | NFR-reliability | 1.0 |
|                                                              | **Total** | **4.0** |



### Grading

Number of points / 8.0 * 5 + 1.

### Schedule

| Week # | Date       | Description                                                  |
| ------ | ---------- | ------------------------------------------------------------ |
| 1      | 21.09.2018 | *Preparation (intro to Java EE)*                             |
| 2      | 28.09.2018 | *Preparation (MVC)*                                          |
| 3      | 05.10.2018 |                                                              |
| 4      | 12.10.2018 |                                                              |
| 5      | 19.10.2018 |                                                              |
| Break  | 26.10.2018 |                                                              |
| 6      | 02.11.2018 | Finish. Deadline to submit the deliverables: **Monday 05.11.2018 08:00 AM**. |
| 7      | 09.11.2018 | **Presentations**: demo (functional + automated tests), presentation of experimental results, summary of current limitations and bugs. **20' per group**. |

### Instructions to deploy
The deployement is done throw the following command:
    docker-compose up --build

After that, you can connect to the website with the url:
    localhost:8080/gamifikator/login

If you want to completly reset the database, you can do that with the command:
    docker-compose down
