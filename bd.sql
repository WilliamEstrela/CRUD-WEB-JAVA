CREATE TABLE proprietario(
  id SERIAL PRIMARY KEY,
  cpf VARCHAR(11),
  nome varchar(256),
  telefone varchar(20)
);

CREATE TABLE celular(
    id SERIAL PRIMARY KEY,
    imei varchar(11),
    marca varchar(100),
    modelo varchar(100),
    cor varchar(100),
    ano varchar(4),
    fk_proprietario INTEGER REFERENCES proprietario(id)
);