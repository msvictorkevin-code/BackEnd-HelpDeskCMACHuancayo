drop SEQUENCE seq_colaborador
drop  TABLE colaborador

drop SEQUENCE seq_usuario;
drop  TABLE usuario;

 

  create sequence seq_colaborador
  start with 1
  increment by 1
  maxvalue 99999
  minvalue 1;
 
CREATE TABLE colaborador(
 id_Colaborador integer NOT NULL DEFAULT nextval('seq_colaborador'::regclass),
 nombres varchar(80) not null,
 apellidos varchar(80) not null,
 cod_empleado varchar(40) not null,
 area varchar(50) not null,
 PRIMARY KEY(id_Colaborador)	
);

insert into colaborador (nombres,apellidos,cod_empleado,area) values ('Victor','Matos','10055989','Sistemas');
insert into colaborador (nombres,apellidos,cod_empleado,area) values ('Juan','Perez','10066213','Finanzas');

select * from colaborador



  create sequence seq_usuario
  start with 1
  increment by 1
  maxvalue 99999
  minvalue 1;

create table usuario(
 id_usario integer NOT NULL DEFAULT nextval('seq_usuario'::regclass),
 id_Colaborador integer not null,
 username varchar(80) not null,
 password varchar(80) not null,
 is_admin BOOLEAN NOT NULL,
	nro_intentos integer not null,
	PRIMARY KEY(id_usario),
	 CONSTRAINT fk_id_Colaborador
      FOREIGN KEY(id_Colaborador)
      REFERENCES colaborador(id_Colaborador)
);

insert into usuario (id_Colaborador,username,password,is_admin,nro_intentos) values (1,'usuadmin','Peru2023.',true,0);
insert into usuario (id_Colaborador,username,password,is_admin,nro_intentos) values (2,'usucolab','Peru2025.',false,0);
 
select * from usuario

update usuario set nro_intentos = 0
