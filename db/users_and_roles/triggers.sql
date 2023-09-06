-- Создание таблицы
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

-- Заполнение таблицы
insert into products (name, producer, count, price) 
	values
	('chair', 'China', 10, 10),
	('table', 'Iraq', 5, 25),
	('sofa', 'Italy', 3, 75);

/* Стейтмент триггер, срабатывающий после вставки данных для любого
товара и просто насчитывает налог на товар
*/
create trigger tax_trigger_state
    after insert on products
    for each statement
    execute procedure tax();

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id = new.id;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- Роу триггер срабатывающий до вставки данных и насчитывающий налог на товар
create or replace function taxes()
    returns trigger as
$$
    BEGIN
        NEW.price := NEW.price * 1.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger taxes_trigger
    before insert
    on products
    for each row
    execute procedure discount();

-- Создание таблицы для истории цен товаров
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

/*Создание триггера на row уровне, который сразу после вставки продукта в 
таблицу products, будет заносить имя, цену и текущую дату
в таблицу history_of_price. 
*/
create or replace function history_of_prices_fnc()
	returns trigger as
	$$
	begin
		insert into "history_of_price" ("name", "price", "date")
		values(NEW."name", NEW."price", current_date);
		return new;
	end;
	$$
	language 'plpgsql';
	
create trigger history_of_prices_trigger
	after insert
	on "products"
	for each row
	execute procedure history_of_prices_fnc();