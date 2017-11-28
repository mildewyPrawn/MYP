CREATE TABLE tienda(id INTEGER PRIMARY KEY ASC, telefono INTEGER, sucursal TEXT, nombre TEXT);
CREATE TABLE comprador(id INTEGER PRIMARY KEY ASC, apellido TEXT, nombre TEXT, direccion TEXT, correo TEXT, telefono INTEGER);
CREATE TABLE vendedor(id INTEGER PRIMARY KEY ASC, apellido TEXT, nombre TEXT, dirBodega TEXT, correo TEXT, telefono INTEGER);
CREATE TABLE tienda_comprador(id INTEGER PRIMARY KEY ASC, id_comprador INTEGER, id_tienda INTEGER);
CREATE TABLE tienda_vendedor(id INTEGER PRIMARY KEY ASC, id_vendedor INTEGER, id_tienda INTEGER);
