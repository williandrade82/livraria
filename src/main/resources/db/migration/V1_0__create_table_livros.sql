CREATE TABLE TB_LIVRO(
    id integer NOT NULL AUTO_INCREMENT,
    isbn varchar(50) DEFAULT NULL,
    titulo varchar(500) NOT NULL,
    autor varchar(300) DEFAULT NULL,
    preco decimal(6,2) DEFAULT NULL,
    PRIMARY KEY(id)
);