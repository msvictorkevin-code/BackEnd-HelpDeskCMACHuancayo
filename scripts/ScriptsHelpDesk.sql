drop TABLE bd_helpdesk

create DATAbase bd_helpdesk 

CREATE SEQUENCE seq_colaborador START WITH 1 INCREMENT BY 1 MAXVALUE 99999 MINVALUE 1;



CREATE TABLE colaborador (
	id_Colaborador INTEGER NOT NULL DEFAULT nextval( 'seq_colaborador' :: REGCLASS ),
	nombres VARCHAR ( 80 ) NOT NULL,
	apellidos VARCHAR ( 80 ) NOT NULL,
	cod_empleado VARCHAR ( 40 ) NOT NULL,
	area VARCHAR ( 50 ) NOT NULL,
	is_activo BOOLEAN NOT NULL,
	PRIMARY KEY ( id_Colaborador ) 
);

INSERT INTO colaborador ( nombres, apellidos, cod_empleado, area,is_activo ) VALUES 	( 'Victor', 'Matos', '10055989', 'Sistemas',TRUE );
INSERT INTO colaborador ( nombres, apellidos, cod_empleado, area,is_activo ) VALUES 	( 'Juan', 'Perez', '10066213', 'Finanzas',TRUE );
 
CREATE SEQUENCE seq_usuario START WITH 1 INCREMENT BY 1 MAXVALUE 99999 MINVALUE 1;

CREATE TABLE usuario (
	id_usuario INTEGER NOT NULL DEFAULT nextval( 'seq_usuario' :: REGCLASS ),
	id_Colaborador INTEGER NOT NULL,
	username VARCHAR ( 80 ) NOT NULL,
	PASSWORD VARCHAR ( 80 ) NOT NULL,
	is_admin BOOLEAN NOT NULL,
	nro_intentos INTEGER NOT NULL,
	PRIMARY KEY ( id_usuario ),
	CONSTRAINT fk_id_Colaborador_usuario FOREIGN KEY ( id_Colaborador ) REFERENCES colaborador ( id_Colaborador ) 
);

INSERT INTO usuario ( id_Colaborador, username, PASSWORD, is_admin, nro_intentos ) VALUES 	( 1, 'usuadmin', 'Peru20239', TRUE, 0 );
INSERT INTO usuario ( id_Colaborador, username, PASSWORD, is_admin, nro_intentos ) VALUES 	( 2, 'usucolab', 'Peru20239', FALSE, 0 );



SELECT	MD5( 'Peru2023.' );
SELECT	MD5( 'Peru2025.' );

CREATE SEQUENCE seq_tipo_requisicion START WITH 1 INCREMENT BY 1 MAXVALUE 99999 MINVALUE 1;



CREATE TABLE tipo_requisicion (
	id_tipo INTEGER NOT NULL DEFAULT nextval( 'seq_tipo_requisicion' :: REGCLASS ),
	nombre VARCHAR ( 80 ) NOT NULL,
	descripcion VARCHAR ( 80 ) NOT NULL,
	PRIMARY KEY ( id_tipo ) 
);

INSERT INTO tipo_requisicion ( nombre, descripcion) VALUES 	( 'Hardware y equipos','Tipo requisicion para Hardware y equipos' );
INSERT INTO tipo_requisicion ( nombre, descripcion) VALUES 	( 'Software y aplicaciones','Tipo requisicion para Software y aplicaciones' );
INSERT INTO tipo_requisicion ( nombre, descripcion) VALUES 	( 'Credenciales y accesos','Tipo requisicion para Credenciales y accesos' );

select * from tipo_requisicion

CREATE SEQUENCE seq_categoria_requisicion START WITH 1 INCREMENT BY 1 MAXVALUE 99999 MINVALUE 1;

CREATE TABLE categoria_requisicion (
	id_categoria INTEGER NOT NULL DEFAULT nextval( 'seq_categoria_requisicion' :: REGCLASS ),
	id_tipo INTEGER NOT NULL,
	nombre VARCHAR ( 80 ) NOT NULL,
	descripcion VARCHAR ( 80 ) NOT NULL,
	PRIMARY KEY ( id_categoria ),
	CONSTRAINT fk_id_tipo FOREIGN KEY ( id_tipo ) REFERENCES tipo_requisicion ( id_tipo ) 
);


INSERT INTO categoria_requisicion ( id_tipo,nombre, descripcion) VALUES 	( 1,'Incidente de equipo computo','Categoria requisicion Incidente de equipo computo' );
INSERT INTO categoria_requisicion ( id_tipo,nombre, descripcion) VALUES 	( 2,'Instalacion de aplicaciones','Categoria requisicion Instalacion de aplicaciones' );
INSERT INTO categoria_requisicion ( id_tipo,nombre, descripcion) VALUES 	( 2,'Actualizacion de sistema operativo','Categoria requisicion Actualizacion de sistema operativo' );
INSERT INTO categoria_requisicion ( id_tipo,nombre, descripcion) VALUES 	( 3,'Actualizacion de cuenta windows','Categoria requisicion Actualizacion de cuenta windows' );
INSERT INTO categoria_requisicion ( id_tipo,nombre, descripcion) VALUES 	( 3,'Actualizacion de credenciales SAP','Categoria requisicion Actualizacion de credenciales SAP' );

select * from categoria_requisicion

CREATE SEQUENCE seq_ticket START WITH 1 INCREMENT BY 1 MAXVALUE 99999 MINVALUE 1;

CREATE TABLE ticket (
	id_ticket INTEGER NOT NULL DEFAULT nextval( 'seq_ticket' :: REGCLASS ),
	descripcion VARCHAR ( 80 ) NOT NULL,
	estado VARCHAR ( 80 ) NOT NULL,
	fecha_generada DATE NULL,
	fecha_cierre DATE NULL,
	prioridad INTEGER NOT NULL,
	id_tipo INTEGER NOT NULL,
	id_categoria INTEGER NOT NULL,
	id_usuario INTEGER NOT NULL,
	PRIMARY KEY ( id_ticket ),
	CONSTRAINT fk_id_tipo_ticket FOREIGN KEY ( id_tipo ) REFERENCES tipo_requisicion ( id_tipo ),
	CONSTRAINT fk_id_categoria_ticket FOREIGN KEY ( id_categoria ) REFERENCES categoria_requisicion ( id_categoria ),
	CONSTRAINT fk_id_usuario_ticket FOREIGN KEY ( id_usuario ) REFERENCES usuario ( id_usuario ) 
);

INSERT INTO ticket (descripcion,estado,fecha_generada,fecha_cierre,prioridad,id_tipo,id_categoria,id_usuario) 
VALUES ('Descripcion test1','Iniciado',now(),now(),1,2,2,1);
INSERT INTO ticket (descripcion,estado,fecha_generada,fecha_cierre,prioridad,id_tipo,id_categoria,id_usuario) 
VALUES ('Descripcion test2','Iniciado',now(),now(),1,2,2,1);
INSERT INTO ticket (descripcion,estado,fecha_generada,fecha_cierre,prioridad,id_tipo,id_categoria,id_usuario) 
VALUES ('Descripcion test3','Iniciado',now(),now(),1,2,2,1);

select * from ticket

CREATE SEQUENCE seq_detalle START WITH 1 INCREMENT BY 1 MAXVALUE 99999 MINVALUE 1;

CREATE TABLE detalle_ticket (
	id_detalle INTEGER NOT NULL DEFAULT nextval( 'seq_detalle' :: REGCLASS ),
	descripcion VARCHAR ( 80 ) NOT NULL,
	fecha_creacion DATE NULL,
	estado VARCHAR ( 80 ) NOT NULL,
	id_ticket INTEGER NOT NULL,
	PRIMARY KEY ( id_detalle ),
CONSTRAINT fk_id_ticket_detalle FOREIGN KEY ( id_ticket ) REFERENCES ticket ( id_ticket ) 
);

/*
DROP SEQUENCE IF EXISTS seq_detalle;
DROP TABLE IF EXISTS detalle_ticket CASCADE;

DROP TABLE IF EXISTS ticket CASCADE;
DROP SEQUENCE IF EXISTS  seq_ticket;

DROP TABLE IF EXISTS usuario CASCADE;
DROP SEQUENCE IF EXISTS  seq_usuario;

 DROP TABLE IF EXISTS colaborador CASCADE;
DROP SEQUENCE IF EXISTS  seq_colaborador;

DROP TABLE IF EXISTS categoria_requisicion CASCADE;
DROP SEQUENCE IF EXISTS seq_categoria_requisicion;

DROP TABLE IF EXISTS tipo_requisicion CASCADE;
DROP SEQUENCE IF EXISTS  seq_tipo_requisicion;
*/


 select * from colaborador


SELECT count(	*)  FROM 	colaborador WHERE id_Colaborador = 1;
SELECT	u.passord  FROM	usuario u;


UPDATE usuario SET nro_intentos = 0 ;
 
select c.cod_empleado as codEmpleado,
      c.id_Colaborador as idColaborador,
      c.area,
c.nombres as nombre,
c.apellidos as apellido,
u.id_usuario as idUsuario,
u.username as usuario,
u.is_admin as administador
 from colaborador c INNER JOIN usuario u on c.id_Colaborador = u.id_Colaborador
 
 
SELECT c.cod_empleado,c.id_Colaborador,c.area,c.nombres,c.apellidos,u.id_usuario,u.username,u.is_admin,c.estado
FROM colaborador c INNER JOIN usuario u ON c.id_Colaborador = u.id_Colaborador
where c.id_Colaborador = 1 and u.id_usuario = 2
LIMIT 1


select * from usuario
det

 SELECT
        c.cod_empleado,
        c.id_Colaborador,
        c.area,
        c.nombres,
        c.apellidos,
        u.id_usuario,
        u.username,
        u.is_admin,
        c.estado 
    FROM
        colaborador c 
    INNER JOIN
        usuario u 
            ON c.id_Colaborador = u.id_Colaborador 
    WHERE
        (c.id_Colaborador = 4)
        and  u.id_usuario = 3 LIMIT 1

  