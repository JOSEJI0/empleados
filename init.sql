-- Insertar Habilidades de prueba si no existen
INSERT IGNORE INTO Habilidad (id, nombre, descripcion) VALUES (1, 'Java Spring Boot', 'Desarrollo de aplicaciones backend corporativas');
INSERT IGNORE INTO Habilidad (id, nombre, descripcion) VALUES (2, 'SQL Avanzado', 'Optimización de consultas y diseño de bases de datos relacionales');
INSERT IGNORE INTO Habilidad (id, nombre, descripcion) VALUES (3, 'Thymeleaf & Front', 'Diseño de vistas dinámicas utilizando Bootstrap');

-- Insertar un Proyecto de prueba inicial (Activo y sin fecha de fin)
INSERT IGNORE INTO Proyecto (id, nombre, descripcion, fechaInicio, fechaFin, activo, statusProyecto) 
VALUES (1, 'Sistema de Gestion Institucional', 'Desarrollo del software core para administracion escolar', NOW(), NULL, 1, 'en_proceso');

-- Insertar Actividades ligadas al proyecto de prueba
INSERT IGNORE INTO Actividad (idActividad, nombre, descripcion, fechaInicio, fechaFin, proyecto_id) 
VALUES (1, 'Diseño del Modelo de Datos', 'Creacion de diagramas entidad relacion y scripts SQL', NOW(), NULL, 1);
INSERT IGNORE INTO Actividad (idActividad, nombre, descripcion, fechaInicio, fechaFin, proyecto_id) 
VALUES (2, 'Implementacion de Spring Security', 'Configuracion de filtros de sesion y roles de usuario', NOW(), NULL, 1);

-- Vincular un Empleado (ID 2 de tu lista en memoria o BD) con una actividad específica
INSERT IGNORE INTO ActividadEmpleado (id, empleado_id, actividad_id, fechaAsignacion) 
VALUES (1, 2, 1, NOW());