USE empleado;

-- Datos obligatorios de perfiles y administrador base
INSERT IGNORE INTO Perfil (id, perfil) VALUES (2, 'ADMIN');
INSERT IGNORE INTO Perfil (id, perfil) VALUES (1, 'USUARIO');

-- Registro de habilidades exigido por el PDF
INSERT IGNORE INTO Habilidad (id, nombre, descripcion) VALUES (1, 'Java Spring Boot 3', 'Desarrollo backend y arquitectura empresarial');
INSERT IGNORE INTO Habilidad (id, nombre, descripcion) VALUES (2, 'Thymeleaf Framework', 'Creación de interfaces dinámicas y responsivas con Bootstrap');
INSERT IGNORE INTO Habilidad (id, nombre, descripcion) VALUES (3, 'Optimización SQL', 'Diseño de consultas relacionales y gestión de índices');

-- Registro de un proyecto activo inicial (Estatus activo y sin fecha de fin)
INSERT IGNORE INTO Proyecto (id, nombre, descripcion, fechaInicio, fechaFin, activo, statusProyecto) 
VALUES (1, 'Ecosistema de Administracion Escolar', 'Desarrollo del software core institucional', NOW(), NULL, 1, 'en_proceso');

-- Registro de actividades vinculadas al proyecto de prueba
INSERT IGNORE INTO Actividad (idActividad, nombre, descripcion, fechaInicio, fechaFin, proyecto_id) 
VALUES (1, 'Diseño de Base de Datos', 'Estructuración de tablas mapeadas por JPA', NOW(), NULL, 1);
INSERT IGNORE INTO Actividad (idActividad, nombre, descripcion, fechaInicio, fechaFin, proyecto_id) 
VALUES (2, 'Pruebas de Seguridad con Spring Security', 'Filtros de endpoints y contraseñas seguras', NOW(), NULL, 1);

-- Vinculación de un empleado de pruebas (asumiendo ID 2 o superior) con una actividad
INSERT IGNORE INTO ActividadEmpleado (id, empleado_id, actividad_id, fechaAsignacion) 
VALUES (1, 2, 1, NOW());

CREATE TABLE actividad (
    idActividad INT AUTO_INCREMENT PRIMARY KEY, -- Declarada explícitamente como key
    nombre VARCHAR(255) NULL,
    descripcion VARCHAR(255) NULL,
    fechaInicio DATE NULL,
    fechaFin DATE NULL,
    activo TINYINT(1) DEFAULT 1,
    id_proyecto INT,
    CONSTRAINT fk_actividad_proyecto FOREIGN KEY (id_proyecto) 
        REFERENCES proyecto(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;