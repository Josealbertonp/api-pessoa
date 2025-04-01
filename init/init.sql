CREATE TABLE IF NOT EXISTS pessoa (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50),
    cpf VARCHAR(11),
    dataNascimento DATE,
    email VARCHAR(30),
    PRIMARY KEY (id)
    );
