create database cine;
use cine;
drop table pelicula;
create table pelicula(
	director varchar(128),
    titulo varchar(128) primary key not null unique,
    fecha date not null);
    
insert into pelicula values ('Alpachino', 'Torino', '1997-04-19');
insert into pelicula values ('Alpachino', 'Toronto', '1997-04-19');
insert into pelicula values ('Ni idea', 'Gran Torino', '1997-04-19');
insert into pelicula values ('No se', 'Toy Story', '2004-04-18');
select * from pelicula;
create table admins(
	usuario varchar(128) primary key not null unique,
    contrasena varchar(128) not null);
    
insert into admins values ("admin", "admin");