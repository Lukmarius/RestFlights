# RestFlights
## Showcase
Features:
* /2api/ - REST API generated automatically by RepositoryRestResource (without implementation of any controller)
* /api/ - REST API implemented by PassengerRestController and RoutesRestController
* ActivePassengerServiceImpl provides safe deleting resources after DELETE requests (only changing column 'Active' in DB)
* links added in /api/routes/ which direct to concrete Airport resource
* logging requests and statuses of responses into txt file

## Technologies:
* Spring Boot
* JPA, Hibernate
* Maven
* PL SQL
* log4j logging

Comming soon:
* front-end (React or Angular)

## How to prepare DB:
* optionally create new databes in outer IDE / console (without tables of course)
* in all_tables_init.sql specify your paths, directing to .csv files in resources folder
* in application.properties configure your database connection
* run all_tables_init.sql
