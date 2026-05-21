-- =======================================================
-- DATOS DE INICIALIZACIÓN (ESTADO COMO INTEGER 1/0)
-- =======================================================

-- 1. Insertar Departamentos
INSERT INTO departamento (id, nombre, foto_dep, activo, estado) VALUES (1, 'Recursos Humanos', 'no-imagen.jpg', 1, 1);
INSERT INTO departamento (id, nombre, foto_dep, activo, estado) VALUES (2, 'Desarrollo de Software', 'no-imagen.jpg', 1, 1);
INSERT INTO departamento (id, nombre, foto_dep, activo, estado) VALUES (3, 'Soporte Tecnico', 'no-imagen.jpg', 1, 0);

-- 2. Insertar Empleados
INSERT INTO empleado (id, nombre, apellidos, fecha_ingreso, salario, id_dep, activo, foto, estado) VALUES (1, 'Luis', 'Perez', '2022-02-20', 30000.00, 2, 1, 'no-imagen.jpg', 1);
INSERT INTO empleado (id, nombre, apellidos, fecha_ingreso, salario, id_dep, activo, foto, estado) VALUES (2, 'Pedro', 'Gonzalez', '2021-05-09', 12000.00, 1, 1, 'no-imagen.jpg', 0);

-- 3. Insertar Contratos
INSERT INTO contrato (id, numero_seguro_social, tipo_contrato, id_emp, activo, estado) VALUES (1, '00000000000', 'Indeterminado', 1, 1, 1);
INSERT INTO contrato (id, numero_seguro_social, tipo_contrato, id_emp, activo, estado) VALUES (2, '12345678901', 'Prueba', 2, 1, 0);

-- 4. Insertar Proyectos (Se mantiene el Enum)
INSERT INTO proyecto (id, nombre, activo, status_proyecto) VALUES (1, 'Inventario', 1, 'EN_PROCESO');
INSERT INTO proyecto (id, nombre, activo, status_proyecto) VALUES (2, 'Desarrollo Página Wrb', 1, 'INICIO');
INSERT INTO proyecto (id, nombre, activo, status_proyecto) VALUES (3, 'Desarrollo App Movil', 1, 'FINALIZADO');