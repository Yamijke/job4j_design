insert into roles(name) values ('admin');
insert into roles(name) values ('user');

insert into categories(name) values ('dubug');
insert into categories(name) values ('error');

insert into states(name) values ('active');
insert into states(name) values ('resolved');

insert into users(name, role_id) VALUES ('Alex', 1);
insert into users(name, role_id) VALUES ('Inna', 2);

insert into rules(name) values ('god');
insert into rules(name) values ('mortal');

insert into roles_rules(role_id, rule_id) values (1, 1);
insert into roles_rules(role_id, rule_id) values (2, 2);

insert into items(name, user_id, category_id, state_id) VALUES ('Printer', 2, 2, 1);
insert into items(name, user_id, category_id, state_id) VALUES ('DataBase', 1, 1, 2);

insert into comments(name, item_id) values ('Urgent', 1);
insert into comments(name, item_id) values ('Non-urgent', 2);

insert into attachs(name, item_id) values ('Jpg', 1);
insert into attachs(name, item_id) values ('Pdf', 2);