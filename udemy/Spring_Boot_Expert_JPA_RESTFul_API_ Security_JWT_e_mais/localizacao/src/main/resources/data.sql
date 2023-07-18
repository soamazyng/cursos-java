create table tb_cidade (
    id_cidade bigint not null primary key,
    nome varchar(50) not null,
    qtde_habitantes bigint
);

insert into tb_cidade
    (id_cidade, nome, qtde_habitantes)
values
    (1, 'São Paulo', 90),
    (2, 'Rio de Janeiro', 40),
    (3, 'Fornateleza', 80),
    (4, 'Salvador', 70),
    (5, 'Belo Horizonte', 60),
    (6, 'Porto Alegre', 77),
    (7, 'Porto Velho', 21),
    (8, 'Palmas', 76),
    (9, 'Natal', 24);