use personas;

insert into persona (nombre, edad) 
values ("Domingo", 34), 
("Sara", 24), 
("Antonio", 26);

insert into direccion (calle, ciudad, codigo_postal, persona_id) 
values ("Álamo", "Sevilla", "41015", 1), 
("Cuero", "Córdoba", "14001", 3), 
("Morera", "Madrid", "28012", 2), 
("Cuidada", "Cádiz", "11005", 2), 
("Garbilla", "Alicante", "03011", 2);

insert into control 
values ("insercion-datos", true);