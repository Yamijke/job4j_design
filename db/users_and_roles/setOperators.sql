-- создаем таблицу фильмов
CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    name text,
    director text
);

-- создаем таблицу книг
CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title text,
    author text
);

-- заполняем таблицу фильмов
INSERT INTO movie (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

-- заполняем таблицу книг
INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');

-- запрос "выведите названия всех фильмов, которые сняты по книге"
select name from movie
intersect
select title from book;

-- запрос "выведите все названия книг, у которых нет экранизации"
select title from book
except
select name from movie;

/* запрос "выведите все уникальные названия произведений из таблиц movie и book
(т.е фильмы, которые сняты не по книге, и книги без экранизации)"
*/
select name
from
	(select name as name from movie
	union all
	select title as name from book) as result
group by name
having count(*) = 1;