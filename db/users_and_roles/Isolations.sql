-- Создаем таблицу
create table cars(
	id serial primary key,
	name text,
	speed integer,
	price integer
);

-- Вносим в таблицу данные
insert into cars (name, speed, price) values ('bmw', 240, 100000);
insert into cars (name, speed, price) values ('skoda', 200, 60000);

-- Запускаем 2 параллельные транзакции 
begin transaction isolation level serializable;

-- В транзакции 1 проиводим обновление данных
update cars set price = 70000 where name ='skoda';

-- В транзакции 2 проиводим обновление данных
update cars set price = 50000 where name ='skoda';

--получаем ошибку "не удалось сериализовать доступ из-за параллельного изменения"