
    IF EXISTS (SELECT FROM pg_database WHERE datname = 'poo_2023_02') THEN
        EXECUTE 'DROP DATABASE poo_2023_02';




    IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'poo_2023_02') THEN
        EXECUTE 'CREATE DATABASE poo_2023_02';
    


\c poo_2023_02;


CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE pessoa (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf_cnpj VARCHAR(14) UNIQUE NOT NULL CHECK (cpf_cnpj ~* '^\d{11}|\d{14}$'), -- Regex para validar CPF ou CNPJ
    tipo CHAR(1) NOT NULL CHECK (tipo IN ('F', 'J')) -- 'F' para física, 'J' para jurídica
);


CREATE TABLE pessoa_fisica (
    id UUID PRIMARY KEY REFERENCES pessoa(id),
    data_nascimento DATE,
    rg VARCHAR(20),
    sexo CHAR(1) CHECK (sexo IN ('M', 'F')) -- 'M' masculino, 'F' feminino
);


CREATE TABLE pessoa_juridica (
    id UUID PRIMARY KEY REFERENCES pessoa(id),
    razao_social VARCHAR(255) NOT NULL,
    nome_fantasia VARCHAR(255),
    inscricao_estadual VARCHAR(20)
);


CREATE TABLE advogado (
    id UUID PRIMARY KEY REFERENCES pessoa(id),
    numero_oab VARCHAR(20) UNIQUE NOT NULL,
    estado_oab CHAR(2) NOT NULL -- UF do registro da OAB
);


CREATE INDEX idx_pessoa_cpf_cnpj ON pessoa(cpf_cnpj);
CREATE INDEX idx_advogado_numero_oab ON advogado(numero_oab);
