create table marca
(
	id serial not null,
	nome varchar(256)
);

CREATE TABLE celular(
    id SERIAL PRIMARY KEY,
    imei varchar(11),
    marca varchar(100),
    modelo varchar(100),
    cor varchar(100),
    ano varchar(4),
);