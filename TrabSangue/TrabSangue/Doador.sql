CREATE TABLE doador (
    codigo SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    contato VARCHAR(255),
    tipo_sanguineo VARCHAR(20),
    rh VARCHAR(20),
    tipo_rh_corretos BOOLEAN DEFAULT FALSE,
    situacao VARCHAR(20) DEFAULT 'ATIVO'
);
