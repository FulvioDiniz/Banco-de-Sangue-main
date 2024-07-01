
INSERT INTO doacao (data, hora, volume, codigo_doador, situacao) VALUES
('2024-06-01', '09:00:00', 450.0, 1, 'ATIVO'),
('2024-06-02', '10:30:00', 450.0, 2, 'ATIVO'),
('2024-06-03', '11:00:00', 450.0, 3, 'INATIVO'),
('2024-06-04', '12:00:00', 450.0, 4, 'ATIVO'),
('2024-06-05', '13:30:00', 450.0, 5, 'ATIVO');

INSERT INTO doador (nome, cpf, contato, tipo_sanguineo, rh, tipo_rh_corretos, situacao) VALUES
('João Silva', '12345678901', 'joao.silva@example.com', 'O+', 'Positivo', TRUE, 'ATIVO'),
('Maria Oliveira', '23456789012', 'maria.oliveira@example.com', 'A-', 'Negativo', TRUE, 'ATIVO'),
('Carlos Souza', '34567890123', 'carlos.souza@example.com', 'B+', 'Positivo', FALSE, 'INATIVO'),
('Ana Santos', '45678901234', 'ana.santos@example.com', 'AB+', 'Positivo', TRUE, 'ATIVO'),
('Luiz Costa', '56789012345', 'luiz.costa@example.com', 'O-', 'Negativo', TRUE, 'ATIVO');
