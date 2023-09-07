-- создаем таблицу
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

-- заполняем таблицу
insert into products (name, producer, count, price) 
	values
	('chair', 'prod1', 3, 50),
	('table', 'prod2', 10, 100),
	('chair', 'prod2', 7, 50);

-- создаем процедуру которая удаляет из таблицы строки, если они больше заданного значения
create or replace procedure delete_data(u_count integer)
language 'plpgsql'
as $$
	declare
	exist_count integer;
    BEGIN
	select count(*) into exist_count from products where count >= u_count;
	
    if exist_count > 0 THEN
            delete from products where count >= u_count;
        end if;
    END;
$$;

-- вызываем процедуру со значением "7"
call delete_data(7);

/* создаем функцию, которая удаляет из таблицы строки, если они больше заданного 
значения и возвращает количество удаленных строк
*/
create or replace function f_delete_data(u_count integer)
returns integer
language 'plpgsql'
as $$
	declare
	exist_count integer;
    BEGIN
	select count(*) into exist_count from products where count >= u_count;
	
    if exist_count > 0 THEN
            delete from products where count >= u_count;
        end if;
		return exist_count;
    END;
$$;

-- вызываем функцию
select f_delete_data(2);