# Gamifikator REST API

####Endpoints

We offer 4 endpoints:

- /rules -> CRUD operations
- /badges -> CRUD operations (no DELETE*)
- /event -> POST a new event to the server
- /users -> GET specific user with his badge's list
	(you can also POST a user for testing purposes)
	
*We chose not to implement a DELETE route for the badges because of our implementation choice. We would have had to go through each row of our DB table that store our user-badges relations to delete the concerned relation and it made no sense to offer the choice.
For the rules on the other hand we had no problem to implement it because there was no other entities in the database to modify or very few.
	
####Models

User

	{
		"name": "username",
		"apitoken": "token",
		"badges": [
				badges, ...
			]
	}
	
Badge

	{
		"timestamp": "12:00:00",
		"userId": 1,
		"apiToken": "token",
		"ruleName": "ruleName",
		"value": 5,
		"property": {
				"name": "propertyName",
				"ruleName": "ruleName"
		}
}

Rule

	{
		"apiToken":"token",
		"name":"ruleName",
		"badgeId": "2",
		"property": {
				"name": "propertyName",
				"ruleName":"ruleName",
				"value": 1,
				"operator": "=="
			}
	}

Property

	{
		"property": {
			"name": "propertyName",
			"ruleName":"ruleName",
			"value": 42,
			"operator": "=="
		}
	}
	
Event

	{
		"timestamp": "12:00:00",
		"userId": 1,
		"apiToken": "token",
		"ruleName": "ruleName",
		"value": 5,
		"property": {
			"name": "propertyName",
			"ruleName": "ruleName"
		}
	}

####How to use the API

Before doing anything, you must have the apiToken of an application registred in the database. For testing purposes (and as we do not offer a way to create a new app..) there is a demo application in the database with the apiToken: "token". You must pass it with almost every request. You can also create test users on the /users path

If you want to create a new rule for the app, you must first POST a new badge and use the ID returned in the response in the new rule. When creating a rule, you must provide in the body of the request a Property param with the logic of your rule. For example: **value = 42** and **operator = "=="**. You can only use Longs as value and the authorized values for operator field are:

					<, >, <=, >=, ==, !=

When a rule has been created, you can send an event triggering it by using the apiToken and the rule name. The value in the event is compared to the condition value of the rule using its operator and in case of success, a badge is attributed to the user. You can then see all your badges with a GET on /users. Enjoy!

####Known problems
During development, there was a change made to the architecture of the program in the case of the rules losing the ability to have more than one properties. Because of that, there are some incoherence in the code where some parts handles a list of properties but some other don't. It causes no bugs, but it ain't pretty son.

There is also some cleaning and refactoring to do in the models sent back by the server as some information repetition somtimes occurs, e.g. two fields *"apiToken"* in some responses.