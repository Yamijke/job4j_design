-- creating tables
create table car_bodies (
	id serial primary key,
	name text
);

create table car_engines (
	id serial primary key,
	name text
);

create table car_transmissions (
	id serial primary key,
	name text
);

create table cars (
	id serial primary key,
	name text,
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

-- inserting data into tables
insert into car_bodies(name) values ('sedan'), ('hatchback'), ('pick-up truck');
insert into car_engines(name) values ('2.0 turbo'), ('1.6'), ('4.8');
insert into car_transmissions(name) values ('5 mech'), ('5 auto'), ('7 auto');
insert into cars(name, body_id, engine_id, transmission_id)
	values 
	('skoda', 1, 1, 1),
	('skoda', 2, 2, null),
	('bmw', 1, 1, 2),
	('bmw', null, 2, 1),
	('bmw', 2, null, 1);
	
-- sql request: All cars
select c.name as car_name, cb.name as body_name, ce.name as engine_name, ct.name as transmission_name
from cars as c
left join car_bodies cb on c.body_id = cb.id 
left join car_engines ce on c.engine_id = ce.id 
left join car_transmissions ct on c.transmission_id = ct.id ;

--sql reauest where body not exists
select cb.*
from car_bodies cb
left join cars c on cb.id = c.body_id
where c.id is null;

--sql reauest where engine not exists
select ce.*
from car_engines ce
left join cars c on ce.id = c.engine_id 
where c.engine_id is null;

--sql reauest where transmission not exists
select ct.*
from car_transmissions ct
left join cars c on ct.id = c.transmission_id
where c.transmission_id is null;