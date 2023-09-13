-- создаем таблицу клиентов
CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

-- заполняем таблицу клиентов
insert into customers (first_name, last_name, age, country) 
	values
	('Alex', 'Bond', 30, 'USA'),
	('Inna', 'Guff', 27, 'UK'),
	('Nick', 'Aver', 50, 'USA');

-- запрос который возвращает список клиентов, возраст которых является минимальным
SELECT * FROM customers 
WHERE age = (SELECT MIN(age) FROM customers);

-- создаем таблицу заказов
CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

-- заполняем таблицу заказов
insert into orders (amount, customer_id) 
	values
	(10, 1),
	(15, 1),
	(20, 2),
	(5, 2);

-- запрос, который вернет список пользователей, которые еще не выполнили ни одного заказа
select * from customers
where customers.id not in (select customer_id from orders);