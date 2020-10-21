insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Goumet', 120.50 , 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery', 30.60, 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Indian Restaurantes', 30.60, 2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('India delivery', 0, 2, utc_timestamp, utc_timestamp);

insert into forma_pagamento (descricao) values ('dinheiro');
insert into forma_pagamento (descricao) values ('cartão');

insert into estado (id, nome) values (1, 'São Paulo');
insert into estado (id, nome) values (2, 'Minas Gerais');

insert into cidade (id, nome, estado_id ) values (1, 'Socorro', 1);
insert into cidade (id, nome, estado_id ) values (2, 'Bueno Brandão', 2);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1), (3, 2)

insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (1, 'Queijo', 'Queijo ralado', 15.10, 1, 1)
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (2, 'refrigerante', 'Coca-cola', 5, 1, 1)
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (3, 'vinho', 'tinto suave', 60, 1, 1)
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (4, 'Queijo', 'Queijo ralado', 15.10, 1, 2)
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (5, 'cerveja', 'kaiser', 10, 1, 2)
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (6, 'chocolate', 'prestigio', 3, 1, 2)
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (7, 'suco', 'suco de laranja', 5, 1, 2)