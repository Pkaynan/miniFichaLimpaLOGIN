DROP DATABASE IF EXISTS mini;
CREATE DATABASE mini;
USE mini;

CREATE TABLE partido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sigla VARCHAR(10) NOT NULL
);

CREATE TABLE politico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    numero_eleitoral INT NOT NULL,
    nome_eleitoral VARCHAR(100),
    partido_id INT NOT NULL,
    FOREIGN KEY (partido_id) REFERENCES partido(id)
);

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    politico_id INT, 
    FOREIGN KEY (politico_id) REFERENCES politico(id)
);

-- Inserindo partidos
INSERT INTO partido (nome, sigla) VALUES 
('Partido da Justiça Social', 'PJS'),
('Partido Verde Sustentável', 'PVS'),
('Partido Liberal Nacional', 'PLN');

-- Inserindo políticos
INSERT INTO politico (nome, numero_eleitoral, nome_eleitoral, partido_id) VALUES
('Maria Silva', 1010, 'Maria da Saúde', 1),
('João Costa', 2020, 'João do Povo', 2),
('Ana Beatriz', 3030, 'Ana da Educação', 3);

-- Inserindo produtos com associação a políticos
INSERT INTO produto (nome, preco, politico_id) VALUES
('Camiseta Ficha Limpa', 39.90, 1),
('Caneca do João do Povo', 25.00, 2),
('Botton Ana da Educação', 9.50, 3),
('Adesivo Maria da Saúde', 4.99, 1);