
-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS clientesdb;
USE clientesdb;

-- Crear la tabla clientes
CREATE TABLE IF NOT EXISTS clientes (
    codigo VARCHAR(30) PRIMARY KEY,
    nombres VARCHAR(50),
    apellidos VARCHAR(50),
    direccion VARCHAR(100),
    telefono VARCHAR(20),
    fecha_nacimiento DATE,
    puesto ENUM('Analista', 'Programador')
);

-- Insertar algunos datos de ejemplo
INSERT INTO clientes (codigo, nombres, apellidos, direccion, telefono, fecha_nacimiento, puesto)
VALUES 
('001', 'Juan', 'Pérez', 'Calle Falsa 123', '555-1234', '1990-01-01', 'Analista'),
('002', 'Ana', 'García', 'Avenida Siempre Viva 456', '555-5678', '1985-05-12', 'Programador');
