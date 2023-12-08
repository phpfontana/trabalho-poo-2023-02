-- Deleta o banco de dados aula, se ele existir
SELECT 'DROP DATABASE poo_2023_02' WHERE EXISTS (SELECT FROM pg_database WHERE datname = 'poo_2023_02')\gexec

SELECT 'CREATE DATABASE poo_2023_02' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'poo_2023_02')\gexec

-- Conectar ao banco de dados criado
\c poo_2023_02;

-- Instalar a extensão uuid-ossp se ainda não estiver instalada
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Tabela Pessoa
CREATE TABLE pessoa (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(255) NOT NULL,
    cpf_cnpj VARCHAR(14) UNIQUE NOT NULL,
    tipo VARCHAR(1) NOT NULL
);

-- Tabela PessoaFisica
CREATE TABLE pessoa_fisica (
    id UUID PRIMARY KEY REFERENCES pessoa(id),
    rg VARCHAR(20),
);

-- Tabela PessoaJuridica
CREATE TABLE pessoa_juridica (
    id UUID PRIMARY KEY REFERENCES pessoa(id),
    razao_social VARCHAR(255) NOT NULL,
    nome_fantasia VARCHAR(255),
    inscricao_estadual VARCHAR(20)
);

-- Tabela Advogado
CREATE TABLE advogado (
    id UUID PRIMARY KEY REFERENCES pessoa(id),
    numero_oab VARCHAR(20) UNIQUE NOT NULL,
    estado_oab VARCHAR(2) NOT NULL -- UF do registro da OAB
);

-- Índices para otimização de buscas
CREATE INDEX idx_pessoa_cpf_cnpj ON pessoa(cpf_cnpj);
CREATE INDEX idx_advogado_numero_oab ON advogado(numero_oab);
