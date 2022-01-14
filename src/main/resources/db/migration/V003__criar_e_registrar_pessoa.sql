CREATE TABLE lancamento(
      codigo serial PRIMARY KEY,
      descricao VARCHAR(100) NOT NULL,
      data_vencimento DATE NOT NULL,
      data_pagamento DATE,
      valor DECIMAL (10,2) NOT NULL,
      observacao VARCHAR(100),
      tipo VARCHAR (20) NOT NULL,
      codigo_pessoa INTEGER NOT NULL,
      codigo_categoria INTEGER NOT NULL,
      FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo),
      FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo)
);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_pessoa, codigo_categoria) values ('Salário Mensal', '2021-11-20',null,6500.00,'Distribuição de Lucros', 'RECEITA',1,1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_pessoa, codigo_categoria) values ('Diversão', '2021-12-10','2021-12-10',1000.50,'Saida Com família', 'DESPESA',2,2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_pessoa, codigo_categoria) values ('Diárias', '2021-11-20','2021-11-20',2500.00,'Receitas com Diarias', 'RECEITA',3,3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_pessoa, codigo_categoria) values ('Lanches', '2021-12-05','2021-12-05',100.00,'Lanche em família', 'DESPESA',1,1);
