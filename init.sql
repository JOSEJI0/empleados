-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS empleado;
USE empleado;

-- Insertar el perfil de ADMIN si no existe (asumiendo ID 1 para ADMIN)
INSERT IGNORE INTO perfil (id, perfil) VALUES (1, 'ADMIN');
INSERT IGNORE INTO perfil (id, perfil) VALUES (2, 'USUARIO');

-- Insertar el usuario administrador (admin / 1234)
-- Nota: Como configuramos NoOpPasswordEncoder, la contraseña va tal cual
INSERT IGNORE INTO usuario (id, username, password, email, estatus, fechaRegistro) 
VALUES (1, 'admin', '1234', 'admin@tecnologico.edu.mx', 1, NOW());

-- Relacionar el usuario con el perfil de ADMIN
INSERT IGNORE INTO usuarioPerfil (idUsuario, idPerfil) VALUES (1, 1);