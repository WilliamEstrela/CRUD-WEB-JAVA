create table carro
(
	id serial not null,
	placa varchar(20),
	marca varchar(20),
	modelo varchar(20),
	ano integer
);

CREATE TABLE pessoa(
    id SERIAL PRIMARY KEY,
    cpf varchar(11),
    nome varchar(60),
    nascimento varchar(10),
    telefone varchar(15),
    email varchar(100),
    cidade varchar(20),
    estado varchar(20),
    cep varchar(10)
);