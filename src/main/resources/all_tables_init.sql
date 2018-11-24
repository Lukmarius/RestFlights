
DROP TABLE IF EXISTS planes CASCADE;
DROP TABLE IF EXISTS seats CASCADE;
DROP TABLE IF EXISTS routes CASCADE;
DROP TABLE IF EXISTS flights CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS passengers CASCADE;
DROP TABLE IF EXISTS crew CASCADE;
DROP TABLE IF EXISTS airports CASCADE;
DROP TABLE IF EXISTS tickets CASCADE;
DROP TYPE IF EXISTS role;

DROP unique index if exists crew_employee_id_uindex;
DROP unique index if exists crew_employee_id_uindex;
DROP unique index if exists planes_plane_id_uindex;
DROP unique index if exists passengers_passenger_id_uindex;
DROP unique index if exists seats_seat_id_uindex;
DROP unique index if exists routes_relationid_uindex;
DROP unique index if exists flights_flight_id_uindex;
DROP unique index if exists tickets_ticket_id_uindex;

CREATE TYPE role AS ENUM ('user', 'manager');

create table if not exists users
(
  user_id bigserial not null
    constraint customers_pkey
      primary key,
  first_name varchar,
  last_name varchar not null,
  password varchar(255) not null,
  email varchar(255) not null,
  phone_number varchar,
  country varchar,
  city varchar,
  adress varchar,
  role role,
  active boolean DEFAULT true
)
;
create unique index if not exists customers_customer_id_uindex
  on users (user_id)
;

create table if not exists crew
(
  employee_id bigserial not null
    constraint crew_pkey
      primary key,
  first_name varchar,
  last_name varchar not null,
  function varchar,
  active boolean DEFAULT true
)
;
create unique index if not exists crew_employee_id_uindex
  on crew (employee_id)
;

create table if not exists planes
(
  plane_id bigserial not null
    constraint planes_pkey
      primary key,
  seats_amount integer,
  active boolean DEFAULT true
)
;
create unique index if not exists planes_plane_id_uindex
  on planes (plane_id)
;

create table if not exists passengers
(
  passenger_id bigserial not null
    constraint passengers_pkey
      primary key,
  firstname varchar,
  lastname varchar not null,
  active boolean DEFAULT true
)
;
create unique index if not exists passengers_passenger_id_uindex
  on passengers (passenger_id)
;


create table if not exists airports
(
  airport_id varchar not null
    constraint airports_pkey
      primary key,
  city varchar,
  country varchar,
  latitude double precision not null,
  longitude double precision not null,
  active boolean DEFAULT true
)
;
create unique index if not exists airports_airportid_uindex
  on airports (airport_id)
;

create table if not exists seats
(
  seat_id bigserial not null
    constraint seats_pkey
      primary key,
  seat_number varchar,
  plane_id bigint
    constraint planes_planeid_fk
      references planes (plane_id) on delete cascade,
  standard varchar,
  active boolean DEFAULT true
)
;
create unique index if not exists seats_seat_id_uindex
  on seats (seat_id)
;

create table if not exists routes
(
  relation_id bigserial not null
    constraint routes_pkey
      primary key,
  from_airport varchar not null
    constraint routes_airports_from__fk
      references airports (airport_id) on delete cascade on update cascade,
  destination_airport varchar not null
    constraint routes_dest___fk
      references airports (airport_id) on delete cascade on update cascade,
  distance integer,
  active boolean DEFAULT true
)
;
create unique index if not exists routes_relationid_uindex
  on routes (relation_id)
;

create table if not exists flights
(
  flight_id bigserial not null
    constraint flights_pkey
      primary key,
  relation_id bigint not null
    constraint flights_routes__fk
      references routes (relation_id) on delete cascade on update cascade,
  plane_id bigint not null,
  price money,
  captain_id bigint
    constraint crew_employeeid_fk
      references crew (employee_id) on delete set null on update cascade,
  startdate timestamp not null,
  enddate timestamp,
  active boolean DEFAULT true
)
;
create unique index if not exists flights_flight_id_uindex
  on flights (flight_id)
;


create table if not exists tickets
(
  ticket_id bigserial not null
    constraint tickets_pkey
      primary key,
  flight_id bigint not null
    constraint tickets_flights__fk
      references flights (flight_id) on delete cascade on update cascade ,
  seat_id bigint not null
    constraint tickets_seats__fk
      references seats (seat_id) on delete cascade on update cascade ,
  user_id bigint not null
    constraint users_userid_fk
      references users (user_id) on delete cascade on update cascade,
  passenger_id bigint not null
    constraint tickets_passengers__fk
      references passengers (passenger_id) on delete cascade on update cascade,
  price money,
  active boolean DEFAULT true
)
;
create unique index if not exists tickets_ticket_id_uindex
  on tickets (ticket_id)
;
-- --------------------------------- TRIGGERS ------------------------------------------------

-- creating seats after created plane:

CREATE OR REPLACE FUNCTION create_seats() RETURNS TRIGGER AS $$
DECLARE
  seats integer;
BEGIN
  seats := new.seats_amount;
  FOR i in 1..seats LOOP
    INSERT INTO seats(seat_number, plane_id, standard)
    VALUES (i, new.plane_id, 'normal');
  END LOOP ;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS seats_trigger ON planes;

CREATE TRIGGER seats_trigger AFTER INSERT ON planes
  FOR EACH ROW EXECUTE PROCEDURE create_seats();

-- fill enddate of arrival in flights, calculated by distance from constant_relation:

CREATE OR REPLACE FUNCTION flights_time() RETURNS TRIGGER AS $$
DECLARE
  speed integer := 900;
  dist integer;
  flight_duration interval;
BEGIN
  SELECT distance INTO dist FROM routes
  WHERE routes.relation_id = new.relation_id;

  flight_duration := make_interval(hours := (dist / speed));
  new.enddate := new.startdate + flight_duration;
  RAISE NOTICE 'DISTANCE: (%)', dist;
  RAISE NOTICE 'DURANCE: (%)', flight_duration;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS flights_trigger ON flights;

CREATE TRIGGER flights_trigger BEFORE INSERT ON flights
  FOR EACH ROW EXECUTE PROCEDURE flights_time();



-- --------------------------------- FILL FROM CSV -------------------------------------------

COPY airports(airport_id, city, country, latitude, longitude)
  FROM '/home/mariusz/IdeaProjects/restflights/src/main/resources/csv_files/airports.csv' with csv delimiter ',';
--
COPY users(first_name, last_name, email, password, phone_number, country, city, adress, role)
  FROM '/home/mariusz/IdeaProjects/restflights/src/main/resources/csv_files/users.csv' with csv delimiter ',';
--
COPY crew(first_name, last_name, function)
  FROM '/home/mariusz/IdeaProjects/restflights/src/main/resources//csv_files/crew.csv' with csv delimiter ',';
--
COPY passengers(firstname, lastname)
  FROM '/home/mariusz/IdeaProjects/restflights/src/main/resources//csv_files/passengers.csv' with csv delimiter ',';
--
COPY planes(seats_amount)
  FROM '/home/mariusz/IdeaProjects/restflights/src/main/resources//csv_files/planes.csv' with csv delimiter ',';
--
COPY routes(from_airport, destination_airport, distance)
  FROM '/home/mariusz/IdeaProjects/restflights/src/main/resources//csv_files/routes.csv' with csv delimiter ',';


