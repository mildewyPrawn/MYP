CREATE TABLE estudiante(id INTEGER PRIMARY KEY ASC, num_cuenta INTEGER, apellido_paterno TEXT, apellido_materno TEXT, nombres TEXT, sexo TEXT, fech_nacimiento INTEGER);
CREATE TABLE profesor(id INTEGER PRIMARY KEY ASC, num_trabajador INTEGER, apellido_paterno TEXT, apellido_materno TEXT, nombres TEXT);
CREATE TABLE carrera(id INTEGER PRIMARY KEY ASC, nombre TEXT);
CREATE TABLE materia(id INTEGER PRIMARY KEY ASC, creditos INTEGER);
CREATE TABLE salon(id INTEGER PRIMARY KEY ASC, num_salon INTEGER);
CREATE TABLE estudiante_carrera(id INTEGER PRIMARY KEY ASC, id_estudiante INTEGER, id_carrera INTEGER);
CREATE TABLE carrera_materia(id INTEGER PRIMARY KEY ASC, id_carrera INTEGER, id_materia INTEGER);
CREATE TABLE salon_materia(id INTEGER PRIMARY KEY ASC, id_salon INTEGER, id_materia INTEGER);
CREATE TABLE carrera_profesor(id INTEGER PRIMARY KEY ASC, id_carrera INTEGER, id_profesor INTEGER);
