-- создаем таблицу
create table cars (
	id serial primary key,
	name text,
	speed integer,
	price integer
);

-- заполняем таблицу
insert into cars (name, speed, price) 
	values 
	('bmw', 240, 100000),
	('skoda', 210, 60000);
	
-- начинаем транзакцию
begin transaction;

-- вносим новые данные в таблицу
insert into cars (name, speed, price) values ('Reno', 160, 30000);

-- проверяем, что данные внесены
select * from cars;

-- добавляем точку сохранения
savepoint reno_added;

-- изменяем данные в таблице
update cars set price = 70000 where name = 'skoda';

-- проверяем, что изменения внесены успешно
select * from cars;

-- возвращаем данные на момент сохранения
rollback to reno_added;

-- проверяем, что изменения откатились
select * from cars;

-- коммитим транзакцию
commit;