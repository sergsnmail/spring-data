create table users (id bigserial, username varchar(30) not null, password varchar(80) not null, email varchar(50) unique, score integer, primary key (id));
create table roles (id serial, name varchar(50) not null, primary key (id));
create table users_roles (user_id bigint not null, role_id int not null, primary key (user_id, role_id), foreign key (user_id) references users (id),foreign key (role_id) references roles (id));
create table products (id bigserial primary key, title varchar(255), cost float );

insert into products (title, cost) values
('Яблоки',10.50),
('Бананы',3.85),
('Апельсины',4.20),
('Абрикосы',74.20),
('Арбузы',44.00),
('Виноград',63.45),
('Дыня',32.26),
('Мандарины',8.15),
('Авокадо',17.42),
('Груши',12.11),
('Киви',6.38),
('Персики',9.00),
('Сливы',10.00),
('Клубника',13.05),
('Крыжовник',16.05),
('Малина',45.15),
('Грейпфрут',25.15),
('Картошка',18.96),
('Лук', 5.15),
('Укроп', 2.56),
('Грейпфрут', 25.15),
('Капуста', 5.75);

insert into roles (name) values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email, score) values
('user1', '$2y$12$Nw4PZnOVH0bDMX7zTyLJJeg.7E8ZOG94W4B346lRXl5yPzDQpQddS', 'user1@gmail.com', 5),
('user2', '$2y$12$Nw4PZnOVH0bDMX7zTyLJJeg.7E8ZOG94W4B346lRXl5yPzDQpQddS', 'user2@gmail.com', 8),
('user3', '$2y$12$Nw4PZnOVH0bDMX7zTyLJJeg.7E8ZOG94W4B346lRXl5yPzDQpQddS', 'user3@gmail.com', 4),
('user4', '$2y$12$Nw4PZnOVH0bDMX7zTyLJJeg.7E8ZOG94W4B346lRXl5yPzDQpQddS', 'user4@gmail.com', 3);

insert into users_roles (user_id, role_id) values
(1, 1),
(1, 2),
(2, 1),
(3, 1),
(4, 1);

