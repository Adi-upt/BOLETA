-- Script de la tabla Cargo para la BD dbtiendaupt (MySQL/MariaDB - XAMPP)
CREATE DATABASE IF NOT EXISTS dbtiendaupt;
USE dbtiendaupt;

CREATE TABLE IF NOT EXISTS tbcargo (
    idcargo     INT          NOT NULL AUTO_INCREMENT,
    nombre      VARCHAR(20)  NOT NULL,
    descripcion VARCHAR(100) NULL,
    PRIMARY KEY (idcargo)
);

-- Si la tabla ya existía SIN auto_increment, ejecutar:
-- ALTER TABLE tbcargo MODIFY idcargo INT NOT NULL AUTO_INCREMENT;

INSERT INTO tbcargo (nombre, descripcion) VALUES
('Administrador', 'Gestiona la tienda y el personal'),
('Vendedor',      'Atiende a los clientes y registra ventas'),
('Cajero',        'Realiza cobros y cierre de caja'),
('Almacenero',    'Controla el ingreso y salida de productos');
