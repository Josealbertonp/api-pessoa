CREATE TABLE IF NOT EXISTS pessoa (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50),
    cpf VARCHAR(11),
    dataNascimento DATE,
    email VARCHAR(50),
    PRIMARY KEY (id)
    );

INSERT INTO pessoa (nome, cpf, dataNascimento, email)
VALUES
    ('Joao Silva', '64571181000', '1985-06-15', 'joao.silva@email.com'),
    ('Maria Oliveira', '11571738029', '1990-04-22', 'maria.oliveira@email.com'),
    ('Carlos Souza', '78226929091', '1982-11-30', 'carlos.souza@email.com'),
    ('Ana Pereira', '39390273080', '1995-02-10', 'ana.pereira@email.com'),
    ('Pedro Costa', '27075511041', '1988-08-08', 'pedro.costa@email.com');
