/* ************************************************************ BEGIN PRODUCT TABLE ********************************************************** */

insert into producto(nombre, tipo, porcion_por_persona, precio, stock, marca) values('Porcion de Queso', 'Lacteo', 1, 0.30, 4000, 'Laive');
insert into producto(nombre, tipo, porcion_por_persona, precio, stock, marca) values('Lata de Leche', 'Lacteo', 0.3, 4, 10000, 'Gloria');
insert into producto(nombre, tipo, porcion_por_persona, precio, stock, marca) values('Lata de Leche', 'Lacteo', 0.3, 4, 10000, 'Gloria');

/* ************************************************************ ENDS PRODUCT TABLE ********************************************************** */


/* ************************************************************ BEGIN LOCATION TABLE ********************************************************** */

insert into lugar(departamento, provincia, distrito, direccion) values('La Libertad', 'Trujillo', 'Trujillo', 'Las quintanas, 345 los condenados');
insert into lugar(departamento, provincia, distrito, direccion) values('La Libertad', 'Lacteo', 'Lacteo', 'Laive');
insert into lugar(departamento, provincia, distrito, direccion) values('La Libertad', 'Lacteo', 'Lacteo', 'Laive');
insert into lugar(departamento, provincia, distrito, direccion) values('La Libertad', 'Lacteo', 'Lacteo', 'Laive');
insert into lugar(departamento, provincia, distrito, direccion) values('La Libertad', 'Lacteo', 'Lacteo', 'Laive');
insert into lugar(departamento, provincia, distrito, direccion) values('La Libertad', 'Lacteo', 'Lacteo', 'Laive');
insert into lugar(departamento, provincia, distrito, direccion) values('La Libertad', 'Lacteo', 'Lacteo', 'Laive');
insert into lugar(departamento, provincia, distrito, direccion) values('La Libertad', 'Lacteo', 'Lacteo', 'Laive');
insert into lugar(departamento, provincia, distrito, direccion) values('La Libertad', 'Lacteo', 'Lacteo', 'Laive');
insert into lugar(departamento, provincia, distrito, direccion) values('La Libertad', 'Lacteo', 'Lacteo', 'Laive');
insert into lugar(departamento, provincia, distrito, direccion) values('La Libertad', 'Lacteo', 'Lacteo', 'Laive');
insert into lugar(departamento, provincia, distrito, direccion) values('Lima', 'Lacteo', 'Lacteo', 'Laive');
insert into lugar(departamento, provincia, distrito, direccion) values('Lima', 'Lacteo', 'Lacteo', 'Laive');
insert into lugar(departamento, provincia, distrito, direccion) values('Lima', 'Lacteo', 'Lacteo', 'Laive');
insert into lugar(departamento, provincia, distrito, direccion) values('Lima', 'Lacteo', 'Lacteo', 'Laive');

/* ************************************************************ ENDS LOCATION TABLE ********************************************************** */


/* ************************************************************ BEGIN INSTITUTION TABLE ********************************************************** */

insert into institucion(numero, nombre, nro_de_alumnos, nivel, lugar_id) values('234432', 'I.E. Primavera', 200, 'Primaria', 1);
insert into institucion(numero, nombre, nro_de_alumnos, nivel, lugar_id) values('562534', 'I.E. Santa Rosa', 340, 'Primaria-Secundaria', 2);
insert into institucion(numero, nombre, nro_de_alumnos, nivel, lugar_id) values('475443', 'I.E. Puerto Rico', 600, 'Primaria-Inicial', 3);

/* ************************************************************ ENDS INSTITUTION TABLE ********************************************************** */


/* ************************************************************ BEGIN HELP TABLE ********************************************************** */

insert into Ayuda(fecha_de_llegada, fecha_de_envio, fecha_de_registro, porciones_totales, precio_total, institucion_id) values('2020-10-02', '2020-10-01', '2020-09-26', 200, 2000, 1);
insert into Ayuda(fecha_de_llegada, fecha_de_envio, fecha_de_registro, porciones_totales, precio_total, institucion_id) values('2020-10-02', '2020-10-01', '2020-09-26', 200, 2000, 1);
insert into Ayuda(fecha_de_llegada, fecha_de_envio, fecha_de_registro, porciones_totales, precio_total, institucion_id) values('2020-10-02', '2020-10-01', '2020-09-26', 200, 2000, 1);
insert into Ayuda(fecha_de_llegada, fecha_de_envio, fecha_de_registro, porciones_totales, precio_total, institucion_id) values('2020-10-02', '2020-10-01', '2020-09-26', 200, 2000, 1);
insert into Ayuda(fecha_de_llegada, fecha_de_envio, fecha_de_registro, porciones_totales, precio_total, institucion_id) values('2020-10-02', '2020-10-01', '2020-09-26', 200, 2000, 1);

/* ************************************************************ ENDS HELP TABLE ********************************************************** */


/* ************************************************************ BEGIN LINE OF HELP TABLE ********************************************************** */

insert into linea_de_ayuda(porciones, producto_id, ayuda_id) values(200, 1, 1);
insert into linea_de_ayuda(porciones, producto_id, ayuda_id) values(300, 2, 1);
insert into linea_de_ayuda(porciones, producto_id, ayuda_id) values(200, 1, 1);
insert into linea_de_ayuda(porciones, producto_id, ayuda_id) values(300, 2, 1);
insert into linea_de_ayuda(porciones, producto_id, ayuda_id) values(600, 3, 2);
insert into linea_de_ayuda(porciones, producto_id, ayuda_id) values(140, 4, 2);

/* ************************************************************ ENDS HELP LINE TABLE ********************************************************** */