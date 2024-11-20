create database if not exists personas;

use personas;

create table persona(
	id int auto_increment,
	nombre varchar(100),
	edad int,
	constraint persona_id_pk primary key(id)
);

create table direccion(
	id int auto_increment,
	calle varchar(100),
	ciudad varchar(50),
	codigo_postal varchar(10),
	persona_id int,
	constraint direccion_id_pk primary key(id),
	constraint direccion_persona_id_fk foreign key(persona_id) references persona(id) on delete cascade on update cascade
);

create table control(
	nombre varchar(100),
	hecho boolean,
	constraint control_nombre_pk primary key(nombre)
);

insert into control 
values ("creacion-bbdd", true);