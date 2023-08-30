create table cars(
	id serial primary key,
	name varchar(255),
	category text,
	country text
);

insert into cars(name, category, country) 
values
	('skoda', 'passenger car', 'Czech Republic'),
	('jeeep', 'passenger car', 'USA'),
	('kamaz', 'commercial vehicle', 'Russia');
	
select * from cars;

update cars
set name = 'jeep'
where name = 'jeeep';

select * from cars;

delete from cars;

select * from cars;