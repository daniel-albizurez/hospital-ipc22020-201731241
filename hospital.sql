CREATE DATABASE IF NOT EXISTS hospital;

USE hospital;

CREATE TABLE IF NOT EXISTS usuario(
    email VARCHAR(50),
    nombre VARCHAR(50),
    dpi VARCHAR(13) UNIQUE,
    telefono VARCHAR(8),
    tipo INT,
    password VARCHAR(1000),
    PRIMARY KEY (email)
);

CREATE TABLE IF NOT EXISTS medico(
    codigo VARCHAR(15),
    email VARCHAR(50),
    numero_colegiado VARCHAR(13) UNIQUE,
    hora_inicio VARCHAR(5),
    hora_fin VARCHAR(5),
    inicio_labores DATE,
    PRIMARY KEY (codigo),
    CONSTRAINT fk_medico_usuario FOREIGN KEY (email) REFERENCES usuario(email)
);

CREATE TABLE IF NOT EXISTS especialidad(
    titulo VARCHAR(50),
    costo DOUBLE,
    PRIMARY KEY (titulo)
);

CREATE TABLE IF NOT EXISTS especializacion(
    medico VARCHAR(15),
    titulo VARCHAR(50),
    CONSTRAINT pk_especializacion PRIMARY KEY (medico, titulo),
    CONSTRAINT fk_especializacion_medico FOREIGN KEY (medico) REFERENCES medico(codigo),
    CONSTRAINT fk_especializacion_especialidad FOREIGN KEY (titulo) REFERENCES especialidad(titulo)
);

CREATE TABLE IF NOT EXISTS paciente(
    codigo VARCHAR(15),
    email VARCHAR(50),
    nacimiento DATE,
    grupo_sanguineo CHAR(2),
    sexo VARCHAR(10),
    peso DOUBLE,
    PRIMARY KEY (codigo),
    CONSTRAINT fk_paciente_usuario FOREIGN KEY (email) REFERENCES usuario(email)
);

CREATE TABLE IF NOT EXISTS examen(
    codigo VARCHAR(15),
    nombre VARCHAR(50),
    descripcion VARCHAR(10000),
    costo DOUBLE,
    tipo_informe VARCHAR(5),
    requiere_orden BOOL,
    PRIMARY KEY (codigo)
);

CREATE TABLE IF NOT EXISTS cita(
    codigo VARCHAR(15),
    fecha DATE,
    hora VARCHAR(5),
    paciente VARCHAR(15),
    tipo INT,
    costo DOUBLE,
    PRIMARY KEY (codigo),
    CONSTRAINT fk_cita_paciente FOREIGN KEY (paciente) REFERENCES paciente(codigo),
    CONSTRAINT uc_cita UNIQUE (fecha, hora, paciente)
);

CREATE TABLE IF NOT EXISTS consulta(
    codigo VARCHAR(15),
    medico VARCHAR(15),
    especialidad VARCHAR(50),
    descripcion VARCHAR(1000),
    PRIMARY KEY (codigo),
    CONSTRAINT fk_consulta_medico FOREIGN KEY (medico) REFERENCES medico(codigo),
    CONSTRAINT fk_consulta_cita FOREIGN KEY (codigo) REFERENCES cita(codigo),
    CONSTRAINT uc_consulta UNIQUE (codigo, medico)
);

CREATE TABLE IF NOT EXISTS laboratorista(
    codigo VARCHAR(15),
    examen VARCHAR(15),
    email VARCHAR(100),
    registro VARCHAR(13) UNIQUE,
    inicio_labores DATE,
    PRIMARY KEY (codigo),
    CONSTRAINT fk_laboratorista_examen FOREIGN KEY (examen) REFERENCES examen(codigo),
    CONSTRAINT fk_laboratorista_usuario FOREIGN KEY (email) REFERENCES usuario(email)
);

CREATE TABLE IF NOT EXISTS horario(
    laboratorista VARCHAR(15),
    dia VARCHAR(10),
    CONSTRAINT pk_horario PRIMARY KEY (laboratorista, dia),
    CONSTRAINT fk_horario_laboratorista FOREIGN KEY (laboratorista) REFERENCES laboratorista(codigo)
);

CREATE TABLE IF NOT EXISTS resultado(
    codigo VARCHAR(15),
    laboratorista VARCHAR(15),
    examen VARCHAR(15),
    informe VARCHAR(100),
    orden VARCHAR(100),
    PRIMARY KEY (codigo),
    CONSTRAINT fk_resultado_cita FOREIGN KEY (codigo) REFERENCES cita(codigo),
    CONSTRAINT fk_resultado_laboratorista FOREIGN KEY (laboratorista) REFERENCES laboratorista(codigo),
    CONSTRAINT fk_resultado_examen FOREIGN KEY (examen) REFERENCES examen(codigo)
);

CREATE TABLE IF NOT EXISTS administrador(
    codigo VARCHAR(15),
    email VARCHAR(50),
    PRIMARY KEY (codigo),
    CONSTRAINT fk_administrador_usuario FOREIGN KEY (email) REFERENCES usuario(email)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE OR REPLACE VIEW top_10_medico_informes AS
SELECT COUNT(c.descripcion) AS informes, m.codigo, u.nombre FROM
consulta c 
INNER JOIN medico m ON c.medico=m.codigo
INNER JOIN usuario u ON u.email = m.email
GROUP BY m.codigo
ORDER BY informes DESC
LIMIT 10
;

CREATE OR REPLACE VIEW ingresos_medico AS
SELECT SUM(c.costo) AS ingresos, m.codigo, u.nombre FROM
cita c 
INNER JOIN consulta con ON c.codigo = con.codigo
INNER JOIN medico m ON m.codigo = con.medico
INNER JOIN usuario u ON u.email = m.email
GROUP BY m.codigo
;

CREATE OR REPLACE VIEW menos_citas AS
SELECT COUNT(c.codigo) AS citas, m.codigo, u.nombre FROM
consulta c INNER JOIN medico m ON c.medico=m.codigo
INNER JOIN usuario u ON u.email = m.email
GROUP BY m.codigo
ORDER BY citas ASC
LIMIT 5
;

CREATE OR REPLACE VIEW examenes_mas_solicitados AS
SELECT COUNT(r.examen) AS cantidad, e.codigo, e.nombre AS tipo FROM
resultado r INNER JOIN examen e ON r.examen=e.codigo
GROUP BY e.codigo
ORDER BY cantidad DESC
;

CREATE VIEW medicos_examenes AS
-- Significa que voy a tener que agregar la orden como una entidad

CREATE OR REPLACE VIEW ingresos_paciente AS
SELECT SUM(c.costo) AS ingresos, p.codigo, u.nombre FROM
cita c
INNER JOIN paciente p ON p.codigo = c.paciente
INNER JOIN usuario u ON u.email = p.email
GROUP BY p.codigo;

-- Reportes Medicos

CREATE OR REPLACE VIEW historial_medico AS
SELECT
c.codigo,
c.paciente,
up.nombre,
IF(c.tipo=1, 'Consulta', 'Examen de laboratorio') AS Tipo,
IF(c.tipo=1, m.codigo, l.codigo) As Encargado,
u.nombre AS nombre_encargado,
IF(c.tipo=1, con.especialidad, e.nombre) As Detalle,
IF(c.tipo=1, con.descripcion, r.informe) As Informacion,
c.fecha,
c.costo FROM
cita c 
LEFT JOIN consulta con ON c.codigo = con.codigo
LEFT JOIN resultado r ON c.codigo = r.codigo
LEFT JOIN examen e ON r.examen = e.CODIGO
LEFT JOIN medico m ON con.medico = m.codigo
LEFT JOIN laboratorista l ON r.laboratorista = l.codigo
LEFT JOIN paciente p ON c.paciente = p.codigo
LEFT JOIN usuario u ON m.email = u.email OR l.email = u.email
LEFT JOIN usuario up ON p.email = up.email
ORDER BY c.fecha DESC
;

CREATE OR REPLACE VIEW citas_agendadas AS
SELECT c.codigo, c.paciente, u.nombre, c.fecha, c.hora, con.especialidad , con.medico FROM
cita c
INNER JOIN paciente p ON c.paciente = p.codigo
INNER JOIN usuario u ON p.email = u.email
INNER JOIN consulta con ON c.codigo = con.codigo
;

CREATE OR REPLACE VIEW informes_paciente AS
SELECT COUNT(con.descripcion) cantidad, c.paciente, u.nombre FROM
cita c
INNER JOIN consulta con ON c.codigo = con.codigo
INNER JOIN paciente p ON p.codigo = c.paciente
INNER JOIN usuario u ON p.email = u.email
ORDER BY cantidad
;

-- Reportes Laboratorista

CREATE OR REPLACE  VIEW examenes_laboratorista AS
SELECT r.examen, e.nombre, c.fecha, l.codigo, r.informe FROM
cita c
INNER JOIN resultado r ON c.codigo = r.codigo
INNER JOIN examen e ON e.codigo = r.examen
INNER JOIN laboratorista l ON r.laboratorista = l.codigo OR r.examen = l.examen 
;

CREATE OR REPLACE  VIEW examenes_por_dia AS
SELECT COUNT(r.examen) cantidad, c.fecha, l.codigo FROM
cita c
INNER JOIN resultado r ON c.codigo = r.codigo
INNER JOIN laboratorista l ON r.laboratorista = l.codigo OR r.examen = l.examen 
GROUP BY c.fecha, l.codigo
;

-- Reportes Paciente

CREATE OR REPLACE  VIEW ultimos_examenes AS
SELECT e.nombre, c.fecha, c.paciente FROM
cita c
INNER JOIN resultado r ON r.codigo = c.codigo 
INNER JOIN examen e ON e.codigo = r.examen
ORDER BY c.fecha DESC
;

CREATE OR REPLACE  VIEW ultimas_consultas AS
SELECT con.especialidad, con.medico, u.nombre, c.fecha, c.paciente FROM
cita c
INNER JOIN consulta con ON con.codigo = c.codigo 
INNER JOIN medico m ON m.codigo = con.medico
INNER JOIN usuario u ON u.email = m.email
ORDER BY c.fecha DESC
;

-- Extras

CREATE OR REPLACE VIEW titulos_medico AS
SELECT m.codigo, u.nombre, e.titulo, m.hora_inicio AS inicio, m.hora_fin as fin FROM
medico m
INNER JOIN especializacion e ON m.codigo = e.medico
INNER JOIN usuario u ON m.email = u.email
;