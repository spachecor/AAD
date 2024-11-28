drop database if exists estudiantes;
create database estudiantes;
use estudiantes;
create table estudiante(
	id int,
    nombre varchar(250),
    apellido varchar(250),
    email varchar(250),
    constraint estudiante_id_pk primary key(id)
);