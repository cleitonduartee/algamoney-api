CREATE TABLE pessoa(
    codigo serial PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(10),
    complemento VARCHAR(20),
    bairro VARCHAR(30),
    cep VARCHAR(10),
    cidade VARCHAR(20),
    estado VARCHAR(2)

);

INSERT INTO pessoa (nome, ativo) values ('Cleiton Duarte', true);
INSERT INTO pessoa (nome, ativo,logradouro,numero,complemento,bairro,cep,cidade,estado)
    values ('José da Esquina', true,'Rua vira na proxima', '001','Casa qualquer','Poço sem fim','790999999','Lago Azul','JJ' );
INSERT INTO pessoa (nome, ativo,logradouro,numero,complemento,bairro,cep,cidade,estado)
values ('Maria do Bairro', true,'Rua uma acima', '002','APT','È aqui mesmo','790999999','Mar molhado','TT' );
INSERT INTO pessoa (nome, ativo) values ('Pedro Henrique', true);
INSERT INTO pessoa (nome, ativo) values ('Stephany Moreira', true);