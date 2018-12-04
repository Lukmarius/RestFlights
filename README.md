# RestFlights

## Concept:
Spring Boot project including Rest Api which shows resources from database (postgres). 3 tables (from all 10 in DB): Airports, Routes (related on Airports) and Passengers have been already implemented at the moment.

## Features:
* /api/ - API implemented by PassengerRestController and RoutesRestController (+ build in handlers from Spring Rest Repository)
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

## How to prepare DB before running server:
* optionally create new database in outer IDE / console (without tables of course)
* in all_tables_init.sql specify your paths, directing to .csv files in resources folder
* in application.properties configure your database connection
* run all_tables_init.sql
