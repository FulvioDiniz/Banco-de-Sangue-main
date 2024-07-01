CREATE TABLE doacao (
    codigo SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    volume DOUBLE PRECISION NOT NULL,
    codigo_doador INTEGER NOT NULL REFERENCES doador(codigo),
    situacao VARCHAR(10) NOT NULL CHECK (situacao IN ('ATIVO', 'INATIVO'))
);
