---------- BASE DE DATOS PARA PROYECTO ----------

--- TABLAS ---

CREATE TABLE tab_sucursal (
    id_sucursal NUMBER NOT NULL,
    nombre varchar2(80) NOT NULL,
    provincia varchar2(30) NOT NULL,
    canton varchar2(30) NOT NULL,
    direccion varchar2(100) NOT NULL,
    telefono varchar2(30) NOT NULL,
    sitio_web varchar2(100) NOT NULL,
    PRIMARY KEY (id_sucursal)
    );

CREATE TABLE tab_categoria(
	id_categoria NUMBER NOT NULL,
	nombre varchar2(50) NOT NULL,
    PRIMARY KEY (id_categoria)
    );
    
 CREATE TABLE tab_cliente(
	id_cliente NUMBER NOT NULL,
	nombre varchar2(20) NOT NULL,
	apellido1 varchar2(20) NOT NULL,
	apellido2 varchar2(20) NOT NULL,
	email varchar2(100) NULL,
	telefono varchar2(10) NULL,
	fecha_registro date DEFAULT SYSDATE NOT NULL,
    PRIMARY KEY (id_cliente) 
);

CREATE TABLE tab_producto(
	id_producto NUMBER NOT NULL,
    id_categoria NUMBER NOT NULL,
	nombre varchar2(80) NOT NULL,
    descripcion varchar2(100) NOT NULL,
    precio_unitario NUMBER(10, 2) NOT NULL,
    PRIMARY KEY (id_producto),
    CONSTRAINT FK_categoria_tab_producto FOREIGN KEY (id_categoria) REFERENCES tab_categoria(id_categoria)
);

CREATE TABLE tab_venta (
    id_venta NUMBER NOT NULL,
    id_cliente NUMBER NOT NULL,
    total_pagado NUMBER(10, 2),
    fecha DATE,
    PRIMARY KEY (id_venta),
    CONSTRAINT fk_cliente_tab_venta FOREIGN KEY (id_cliente) REFERENCES tab_cliente(id_cliente)
);

CREATE TABLE tab_detalle_venta (
  id_detalle NUMBER NOT NULL,
  id_venta NUMBER NOT NULL,
  id_producto NUMBER NOT NULL,
  cantidad NUMBER NOT NULL,
  precio_unitario NUMBER(10, 2),
  PRIMARY KEY (id_detalle),
  CONSTRAINT fk_venta_tab_detalle FOREIGN KEY (id_venta) REFERENCES tab_venta(id_venta),
  CONSTRAINT fk_producto_tab_detalle FOREIGN KEY (id_producto) REFERENCES tab_producto(id_producto)
);

CREATE TABLE tab_cargo(   
  id_cargo NUMBER NOT NULL,
  nombre_cargo varchar2(80) NOT NULL,
  departamento varchar2(80) NOT NULL,
  salario NUMBER(10, 2),
  PRIMARY KEY (id_cargo)
);

CREATE TABLE tab_empleado(
  id_empleado NUMBER NOT NULL,
  id_sucursal NUMBER NOT NULL,
  id_cargo NUMBER NOT NULL,
  fecha_contratacion DATE NOT NULL,
  nombre varchar2(80) NOT NULL,
  apellido varchar2(80) NOT NULL,
  telefono varchar2(80) NOT NULL,
  email varchar2(80) NOT NULL,
  PRIMARY KEY (id_empleado),
  CONSTRAINT fk_sucursal_tab_empleado FOREIGN KEY (id_sucursal) REFERENCES tab_sucursal(id_sucursal),
  CONSTRAINT fk_cargo_tab_empleado FOREIGN KEY (id_cargo) REFERENCES tab_cargo(id_cargo)
);

CREATE TABLE tab_proveedor( 
  id_proveedor NUMBER NOT NULL,
  nombre varchar2(80) NOT NULL,
  apellido varchar2(80) NOT NULL,
  telefono varchar2(80) NOT NULL, 
  email varchar2(80) NOT NULL,
  PRIMARY KEY (id_proveedor)
);

CREATE TABLE tab_inventario(
	id_inventario NUMBER NOT NULL,
	id_sucursal NUMBER NOT NULL,
	id_producto NUMBER NOT NULL,
	id_proveedor NUMBER NOT NULL,
	cant_disponible NUMBER NOT NULL,
    PRIMARY KEY (id_inventario),
    CONSTRAINT FK_sucursal_tab_inventario FOREIGN KEY (id_sucursal) REFERENCES tab_sucursal(id_sucursal),
    CONSTRAINT FK_producto_tab_inventario FOREIGN KEY (id_producto) REFERENCES tab_producto(id_producto),
    CONSTRAINT FK_proveedor_tab_inventario FOREIGN KEY (id_proveedor) REFERENCES tab_proveedor(id_proveedor)
);

CREATE TABLE tab_comentario(
	id_comentario NUMBER NOT NULL,
    id_cliente NUMBER NOT NULL,
    id_producto NUMBER NOT NULL,
	calificacion NUMBER NOT NULL CHECK (calificacion >= 1 AND calificacion <= 5),
    comentario varchar2(300) NOT NULL,
    fecha date NOT NULL,
    PRIMARY KEY (id_comentario),
    CONSTRAINT FK_cliente_tab_comentario FOREIGN KEY (id_cliente) REFERENCES tab_cliente(id_cliente),
    CONSTRAINT FK_producto_tab_comentario FOREIGN KEY (id_producto) REFERENCES tab_producto(id_producto)
);

--- INSERTS ---

-- Sucursal
INSERT INTO tab_sucursal VALUES (1, 'DataTech San Pedro', 'San José', 'Montes de Oca', 'Av52 Calle4', '22025633', 'datatechsanpedro.com');
INSERT INTO tab_sucursal VALUES (2, 'DataTech Alajuela', 'Alajuela', 'Alajuela', 'Av23 Calle1', '22789900', 'datatechalajuela.com');
INSERT INTO tab_sucursal VALUES (3, 'DataTech Heredia', 'Heredia', 'Belén', 'Av47A Calle13', '22771100', 'datatechheredia.com');
INSERT INTO tab_sucursal VALUES (4, 'DataTech Cartago', 'Cartago', 'Paraíso', 'Av78 Calle23', '22001033', 'datatechcartago.com');
INSERT INTO tab_sucursal VALUES (5, 'DataTech Curridabat', 'San José', 'Central', 'Av14 Calle3', '22471039', 'datatechcurridabat.com');

-- Categoría
INSERT INTO tab_categoria VALUES (1, 'Laptops');
INSERT INTO tab_categoria VALUES (2, 'Monitores');
INSERT INTO tab_categoria VALUES (3, 'Tablets');
INSERT INTO tab_categoria VALUES (4, 'Componentes');
INSERT INTO tab_categoria VALUES (5, 'Accesorios');

-- Cliente
INSERT INTO tab_cliente VALUES (1, 'Carlos', 'Perez', 'Rodriguez', 'carlosperez@gmail.com', '88591263', SYSDATE);
INSERT INTO tab_cliente VALUES (2, 'Ana', 'García', 'López', 'anagarcia@gmail.com', '82345678', SYSDATE);
INSERT INTO tab_cliente VALUES (3, 'Juan', 'Martínez', 'Sánchez', 'juanmartinez@gmail.com', '87654321', SYSDATE);
INSERT INTO tab_cliente VALUES (4, 'María', 'Hernández', 'Fernández', 'mariahernandez@gmail.com', '73579246', SYSDATE);
INSERT INTO tab_cliente VALUES (5, 'Pedro', 'López', 'Gómez', 'pedrolopez@gmail.com', '88765432', SYSDATE);
INSERT INTO tab_cliente VALUES (6, 'Laura', 'Díaz', 'Pérez', 'lauradiaz@gmail.com', '84681357', SYSDATE);
INSERT INTO tab_cliente VALUES (7, 'Luis', 'González', 'Martínez', 'luisgonzalez@gmail.com', '66925814', SYSDATE);
INSERT INTO tab_cliente VALUES (8, 'Sofía', 'Rodríguez', 'García', 'sofiarodriguez@gmail.com', '88246137', SYSDATE);
INSERT INTO tab_cliente VALUES (9, 'Diego', 'Sánchez', 'Jiménez', 'diegosanchez@gmail.com', '74185296', SYSDATE);
INSERT INTO tab_cliente VALUES (10, 'Carmen', 'Pérez', 'Ruiz', 'carmenperez@gmail.com', '85935746', SYSDATE);
INSERT INTO tab_cliente VALUES (11, 'Pablo', 'Muñoz', 'Alvarez', 'pablomunoz@gmail.com', '82348765', SYSDATE);
INSERT INTO tab_cliente VALUES (12, 'Elena', 'Fernández', 'López', 'elenafdez@gmail.com', '88765431', SYSDATE);
INSERT INTO tab_cliente VALUES (13, 'Javier', 'Martínez', 'González', 'javiermartinez@gmail.com', '74185296', SYSDATE);
INSERT INTO tab_cliente VALUES (14, 'Marina', 'Sánchez', 'Díaz', 'marinasanchez@gmail.com', '62345678', SYSDATE);
INSERT INTO tab_cliente VALUES (15, 'Roberto', 'García', 'Hernández', 'robertogarcia@gmail.com', '86925814', SYSDATE);
INSERT INTO tab_cliente VALUES (16, 'Sara', 'Pérez', 'Muñoz', 'saraperez@gmail.com', '88246137', SYSDATE);
INSERT INTO tab_cliente VALUES (17, 'Alejandro', 'López', 'Rodríguez', 'alejandrolpz@gmail.com', '74681357', SYSDATE);
INSERT INTO tab_cliente VALUES (18, 'Lucía', 'Ruiz', 'Sánchez', 'luciaruiz@gmail.com', '83579246', SYSDATE);
INSERT INTO tab_cliente VALUES (19, 'Miguel', 'Jiménez', 'Gómez', 'migueljimenez@gmail.com', '75935746', SYSDATE);
INSERT INTO tab_cliente VALUES (20, 'Paula', 'Gómez', 'Pérez', 'paulagomez@gmail.com', '82348765', SYSDATE);

-- Cargo
INSERT INTO tab_cargo VALUES (10, 'Gerente', 'Gerencia', 610000);
INSERT INTO tab_cargo VALUES (20, 'Dependiente', 'Ventas', 400000);
INSERT INTO tab_cargo VALUES (30, 'Contador', 'Contaduria', 600000);
INSERT INTO tab_cargo VALUES (40, 'Reclutador', 'Recursos Humanos', 585000);
INSERT INTO tab_cargo VALUES (50, 'Guarda', 'Seguridad', 390000);

-- Empleado
INSERT INTO tab_empleado VALUES (1, 1, 10, '03-03-2020', 'Antonio', 'Estrada', '88594063', 'antonioestrada@gmail.com');
INSERT INTO tab_empleado VALUES (2, 1, 20, '05-03-2021', 'Pamela', 'Gutierrez', '77594063', 'pamegutierrez@gmail.com'); 
INSERT INTO tab_empleado VALUES (3, 1, 20, '01-08-2022', 'Johel', 'Mena', '88594022', 'johelmena@gmail.com'); 
INSERT INTO tab_empleado VALUES (4, 2, 10, '19-03-2019', 'Luis', 'Lopez', '66594063', 'luislopez@gmail.com');
INSERT INTO tab_empleado VALUES (5, 2, 20, '28-03-2021', 'Carlos', 'Mora', '77537063', 'carlosmora@gmail.com'); 
INSERT INTO tab_empleado VALUES (6, 2, 30, '11-05-2022', 'Alex', 'Mena', '8850000', 'axelmena@gmail.com'); 
INSERT INTO tab_empleado VALUES (7, 3, 10, '20-04-2016', 'Josue', 'Sequeira', '66577063', 'josuesequeira@gmail.com');
INSERT INTO tab_empleado VALUES (8, 3, 20, '10-02-2018', 'Nicolas', 'Mendez', '77552033', 'nicolasmendez@gmail.com'); 
INSERT INTO tab_empleado VALUES (9, 3, 50, '13-06-2022', 'Axel', 'Gonzalez', '88500022', 'axelgonzalez@gmail.com'); 
INSERT INTO tab_empleado VALUES (10, 4, 10, '03-04-2016', 'Gabriela', 'Lopez', '66551234', 'gabrielalopez@gmail.com');
INSERT INTO tab_empleado VALUES (11, 4, 20, '10-02-2018', 'Andres', 'Martinez', '77559988', 'andresmartinez@gmail.com');
INSERT INTO tab_empleado VALUES (12, 5, 10, '13-10-2022', 'Camila', 'Perez', '88557799', 'camilaperez@gmail.com');
INSERT INTO tab_empleado VALUES (13, 5, 20, '20-04-2016', 'Mateo', 'Gutierrez', '66554411', 'mateogutierrez@gmail.com');
INSERT INTO tab_empleado VALUES (14, 5, 40, '10-02-2019', 'Valentina', 'Sanchez', '77553322', 'valentinasanchez@gmail.com');
INSERT INTO tab_empleado VALUES (15, 5, 50, '21-06-2023', 'Daniel', 'Fernandez', '88556633', 'danielfernandez@gmail.com');

-- Producto
INSERT INTO tab_producto VALUES (1, 1, 'Laptop HP Pavilion', 'Potente laptop para uso diario', 539000);
INSERT INTO tab_producto VALUES (2, 1, 'Laptop Dell Inspiron', 'Laptop confiable para trabajo y entretenimiento', 449000);
INSERT INTO tab_producto VALUES (3, 1, 'Laptop Lenovo ThinkPad', 'Portátil resistente y seguro', 650000);
INSERT INTO tab_producto VALUES (4, 1, 'Laptop ASUS VivoBook', 'Laptop delgada y ligera con pantalla Full HD', 407000);
INSERT INTO tab_producto VALUES (5, 1, 'Laptop Apple MacBook Air', 'La potencia de Apple en un diseño ultraportátil', 719000);
INSERT INTO tab_producto VALUES (6, 2, 'Monitor LG UltraWide', 'Pantalla panorámica para una experiencia inmersiva', 239000);
INSERT INTO tab_producto VALUES (7, 2, 'Monitor Samsung 4K', 'Calidad de imagen excepcional con resolución 4K UHD', 359900);
INSERT INTO tab_producto VALUES (8, 2, 'Monitor ASUS Gaming', 'Monitor diseñado para la experiencia de juego', 290000);
INSERT INTO tab_producto VALUES (9, 2, 'Monitor HP LED', 'Monitor LED de alta definición para tareas diarias', 119000);
INSERT INTO tab_producto VALUES (10, 2, 'Monitor Dell UltraSharp', 'Precisión de color profesional para trabajos creativos', 477000);
INSERT INTO tab_producto VALUES (11, 3, 'Tablet Samsung Galaxy Tab', 'Tablet Android con pantalla brillante y delgada', 170500);
INSERT INTO tab_producto VALUES (12, 3, 'Tablet Apple iPad', 'La tableta iOS más popular con potencia y versatilidad', 200000);
INSERT INTO tab_producto VALUES (13, 3, 'Tablet Amazon Fire', 'Tablet asequible con acceso a contenido de Amazon', 59000);
INSERT INTO tab_producto VALUES (14, 3, 'Tablet Lenovo Tab', 'Tablet Android diseñada para entretenimiento móvil', 120000);
INSERT INTO tab_producto VALUES (15, 3, 'Tablet Huawei MediaPad', 'Tablet con pantalla Full HD y sonido envolvente', 209000);
INSERT INTO tab_producto VALUES (16, 4, 'Tarjeta gráfica NVIDIA GeForce', 'Gráficos potentes para juegos y aplicaciones profesionales', 140600);
INSERT INTO tab_producto VALUES (17, 4, 'Procesador AMD Ryzen', 'Rendimiento de vanguardia para PC de escritorio', 230000);
INSERT INTO tab_producto VALUES (18, 4, 'Memoria RAM Corsair', 'Módulos de memoria confiables para mejorar el rendimiento del sistema', 40000);
INSERT INTO tab_producto VALUES (19, 4, 'Disco duro SSD Samsung', 'Almacenamiento rápido y confiable para tu PC', 77000);
INSERT INTO tab_producto VALUES (20, 4, 'Placa base ASUS ROG', 'Placa base diseñada para entusiastas de los juegos', 100000);
INSERT INTO tab_producto VALUES (21, 5, 'Teclado mecánico Razer', 'Teclado para juegos con interruptores mecánicos', 25000);
INSERT INTO tab_producto VALUES (22, 5, 'Ratón inalámbrico Logitech', 'Ratón ergonómico para uso diario', 15000);
INSERT INTO tab_producto VALUES (23, 5, 'Auriculares Sony Noise Cancelling', 'Auriculares inalámbricos con cancelación de ruido', 17500);
INSERT INTO tab_producto VALUES (24, 5, 'Cargador inalámbrico Anker', 'Cargador rápido compatible con teléfonos inteligentes', 11000);
INSERT INTO tab_producto VALUES (25, 5, 'Funda para portátil Case Logic', 'Funda acolchada para proteger tu portátil', 13900);

-- Proveedor
INSERT INTO tab_proveedor VALUES (1, 'Juan', 'González', '22364455', 'juangonzalez@gmail.com');
INSERT INTO tab_proveedor VALUES (2, 'María', 'Martínez', '88330211', 'mariamartinez@example.com');
INSERT INTO tab_proveedor VALUES (3, 'Carlos', 'Hernández', '66773899', 'carlosperez@example.com');
INSERT INTO tab_proveedor VALUES (4, 'Laura', 'Díaz', '77885766', 'lauragomez@example.com');
INSERT INTO tab_proveedor VALUES (5, 'Alejandro', 'Ruiz', '88223344', 'alejandromartinez@example.com');
INSERT INTO tab_proveedor VALUES (6, 'Ana', 'Sánchez', '66445066', 'anajimenez@example.com');
INSERT INTO tab_proveedor VALUES (7, 'Sofía', 'Fernández', '87637788', 'sofiagarcia@example.com');
INSERT INTO tab_proveedor VALUES (8, 'Pedro', 'Gómez', '71881900', 'pedroruiz@example.com');
INSERT INTO tab_proveedor VALUES (9, 'Lucía', 'Gutiérrez', '64113293', 'luciatorres@example.com');
INSERT INTO tab_proveedor VALUES (10, 'Daniel', 'Martín', '89001122', 'danielsanchez@example.com');

-- Inventario
INSERT INTO tab_inventario VALUES (1, 1, 1, 1, 10);
INSERT INTO tab_inventario VALUES (2, 1, 2, 2, 15);
INSERT INTO tab_inventario VALUES (3, 1, 3, 3, 20);
INSERT INTO tab_inventario VALUES (4, 1, 4, 4, 8);
INSERT INTO tab_inventario VALUES (5, 1, 5, 5, 12);
INSERT INTO tab_inventario VALUES (6, 1, 6, 6, 6);
INSERT INTO tab_inventario VALUES (7, 1, 7, 7, 10);
INSERT INTO tab_inventario VALUES (8, 1, 8, 8, 15);
INSERT INTO tab_inventario VALUES (9, 1, 9, 9, 20);
INSERT INTO tab_inventario VALUES (10, 1, 10, 10, 8);
INSERT INTO tab_inventario VALUES (11, 1, 11, 1, 12);
INSERT INTO tab_inventario VALUES (12, 1, 12, 2, 6);
INSERT INTO tab_inventario VALUES (13, 1, 13, 3, 10);
INSERT INTO tab_inventario VALUES (14, 1, 14, 4, 15);
INSERT INTO tab_inventario VALUES (15, 1, 15, 5, 20);
INSERT INTO tab_inventario VALUES (16, 1, 16, 6, 8);
INSERT INTO tab_inventario VALUES (17, 1, 17, 7, 12);
INSERT INTO tab_inventario VALUES (18, 1, 18, 8, 6);
INSERT INTO tab_inventario VALUES (19, 1, 19, 9, 10);
INSERT INTO tab_inventario VALUES (20, 1, 20, 10, 15);
INSERT INTO tab_inventario VALUES (21, 1, 21, 1, 20);
INSERT INTO tab_inventario VALUES (22, 1, 22, 2, 8);
INSERT INTO tab_inventario VALUES (23, 1, 23, 3, 12);
INSERT INTO tab_inventario VALUES (24, 1, 24, 4, 6);
INSERT INTO tab_inventario VALUES (25, 1, 25, 5, 10);
INSERT INTO tab_inventario VALUES (26, 3, 1, 1, 2);
INSERT INTO tab_inventario VALUES (27, 3, 2, 2, 15);
INSERT INTO tab_inventario VALUES (28, 3, 3, 3, 20);
INSERT INTO tab_inventario VALUES (29, 3, 4, 4, 8);
INSERT INTO tab_inventario VALUES (30, 3, 5, 5, 12);
INSERT INTO tab_inventario VALUES (31, 3, 6, 6, 6);
INSERT INTO tab_inventario VALUES (32, 3, 7, 7, 8);
INSERT INTO tab_inventario VALUES (33, 3, 8, 8, 15);
INSERT INTO tab_inventario VALUES (34, 3, 9, 9, 32);
INSERT INTO tab_inventario VALUES (35, 3, 10, 10, 8);
INSERT INTO tab_inventario VALUES (36, 3, 11, 1, 12);
INSERT INTO tab_inventario VALUES (37, 3, 12, 2, 6);
INSERT INTO tab_inventario VALUES (38, 3, 13, 3, 10);
INSERT INTO tab_inventario VALUES (39, 3, 14, 4, 13);
INSERT INTO tab_inventario VALUES (40, 3, 15, 5, 1);
INSERT INTO tab_inventario VALUES (41, 3, 16, 6, 8);
INSERT INTO tab_inventario VALUES (42, 3, 17, 7, 12);
INSERT INTO tab_inventario VALUES (43, 3, 18, 8, 6);
INSERT INTO tab_inventario VALUES (44, 3, 19, 9, 10);
INSERT INTO tab_inventario VALUES (45, 3, 20, 10, 15);
INSERT INTO tab_inventario VALUES (46, 2, 1, 1, 10);
INSERT INTO tab_inventario VALUES (47, 2, 2, 2, 15);
INSERT INTO tab_inventario VALUES (48, 2, 3, 3, 20);
INSERT INTO tab_inventario VALUES (49, 2, 4, 4, 8);
INSERT INTO tab_inventario VALUES (50, 2, 5, 5, 1);
INSERT INTO tab_inventario VALUES (51, 2, 6, 6, 6);
INSERT INTO tab_inventario VALUES (52, 2, 7, 7, 10);
INSERT INTO tab_inventario VALUES (53, 2, 8, 8, 15);
INSERT INTO tab_inventario VALUES (54, 2, 9, 9, 20);
INSERT INTO tab_inventario VALUES (55, 2, 10, 10, 8);
INSERT INTO tab_inventario VALUES (56, 2, 11, 1, 12);
INSERT INTO tab_inventario VALUES (57, 2, 12, 2, 6);
INSERT INTO tab_inventario VALUES (58, 2, 13, 3, 10);
INSERT INTO tab_inventario VALUES (59, 2, 14, 4, 15);
INSERT INTO tab_inventario VALUES (60, 2, 15, 5, 1);
INSERT INTO tab_inventario VALUES (61, 2, 16, 6, 8);
INSERT INTO tab_inventario VALUES (62, 2, 17, 7, 15);
INSERT INTO tab_inventario VALUES (63, 2, 18, 8, 6);
INSERT INTO tab_inventario VALUES (64, 2, 19, 9, 10);
INSERT INTO tab_inventario VALUES (65, 2, 20, 10, 11);
INSERT INTO tab_inventario VALUES (66, 2, 21, 1, 19);
INSERT INTO tab_inventario VALUES (67, 2, 22, 2, 8);
INSERT INTO tab_inventario VALUES (68, 2, 23, 3, 12);
INSERT INTO tab_inventario VALUES (69, 2, 24, 4, 6);
INSERT INTO tab_inventario VALUES (70, 2, 25, 5, 10);
INSERT INTO tab_inventario VALUES (71, 4, 1, 1, 30);
INSERT INTO tab_inventario VALUES (72, 4, 2, 2, 15);
INSERT INTO tab_inventario VALUES (73, 4, 3, 3, 20);
INSERT INTO tab_inventario VALUES (74, 4, 4, 4, 8);
INSERT INTO tab_inventario VALUES (75, 4, 5, 5, 12);
INSERT INTO tab_inventario VALUES (76, 4, 9, 6, 6);
INSERT INTO tab_inventario VALUES (77, 4, 10, 7, 10);
INSERT INTO tab_inventario VALUES (78, 4, 11, 8, 15);
INSERT INTO tab_inventario VALUES (79, 4, 16, 9, 8);
INSERT INTO tab_inventario VALUES (80, 4, 17, 10, 12);
INSERT INTO tab_inventario VALUES (81, 4, 18, 1, 6);
INSERT INTO tab_inventario VALUES (82, 4, 21, 2, 34);
INSERT INTO tab_inventario VALUES (83, 4, 22, 3, 8);
INSERT INTO tab_inventario VALUES (84, 4, 23, 4, 6);
INSERT INTO tab_inventario VALUES (85, 5, 6, 1, 10);
INSERT INTO tab_inventario VALUES (86, 5, 7, 2, 15);
INSERT INTO tab_inventario VALUES (87, 5, 8, 3, 37);
INSERT INTO tab_inventario VALUES (88, 5, 12, 4, 8);
INSERT INTO tab_inventario VALUES (89, 5, 13, 5, 12);
INSERT INTO tab_inventario VALUES (90, 5, 9, 6, 6);
INSERT INTO tab_inventario VALUES (91, 5, 10, 7, 10);
INSERT INTO tab_inventario VALUES (92, 5, 11, 8, 9);
INSERT INTO tab_inventario VALUES (93, 5, 19, 9, 8);
INSERT INTO tab_inventario VALUES (94, 5, 20, 10, 3);
INSERT INTO tab_inventario VALUES (95, 5, 24, 1, 6);
INSERT INTO tab_inventario VALUES (96, 5, 25, 2, 2);
INSERT INTO tab_inventario VALUES (97, 5, 1, 3, 8);

-- Comentario
INSERT INTO tab_comentario VALUES (1, 1, 1, 4, 'Excelente laptop HP Pavilion, muy rápido y confiable.', '10-05-2023');
INSERT INTO tab_comentario VALUES (2, 2, 2, 5, 'Me encanta esta laptop Dell Inspiron, es perfecta para trabajar y para jugar.', '15-08-2023');
INSERT INTO tab_comentario VALUES (3, 3, 6, 3, 'El monitor LG UltraWide es bueno, pero la calidad de imagen podría ser mejor.', '20-07-2023');
INSERT INTO tab_comentario VALUES (4, 4, 11, 5, 'La tablet Samsung Galaxy Tab es increíble, la mejor que he tenido.', '25-09-2023');
INSERT INTO tab_comentario VALUES (5, 5, 4, 2, 'No estoy satisfecho con la laptop ASUS VivoBook, esperaba más calidad.', '30-06-2023');
INSERT INTO tab_comentario VALUES (6, 6, 5, 4, 'El MacBook Air es muy ligero y potente, ideal para llevar a todas partes.', '12-04-2023');
INSERT INTO tab_comentario VALUES (7, 7, 6, 5, 'Este monitor LG UltraWide es genial para multitarea, lo recomiendo.', '05-10-2023');
INSERT INTO tab_comentario VALUES (8, 8, 22, 3, 'Excelente!', '18-11-2023');
INSERT INTO tab_comentario VALUES (9, 9, 16, 5, 'Muy buena calidad.', '01-12-2023');
INSERT INTO tab_comentario VALUES (10, 10, 9, 3, 'El monitor HP LED es bueno para el precio, pero esperaba más resolución.', '10-11-2023');
INSERT INTO tab_comentario VALUES (11, 11, 10, 5, 'El Dell UltraSharp tiene una precisión de color impresionante.', '20-09-2023');
INSERT INTO tab_comentario VALUES (12, 12, 3, 3, 'La tablet Apple iPad tiene una pantalla brillante, pero se ralentiza a veces.', '14-08-2023');
INSERT INTO tab_comentario VALUES (13, 13, 12, 5, 'El iPad es simplemente perfecto, sin quejas.', '26-07-2023');
INSERT INTO tab_comentario VALUES (14, 14, 13, 3, 'Buen tablet, pero la duración de la batería podría ser mejor.', '30-10-2023');
INSERT INTO tab_comentario VALUES (15, 15, 25, 5, 'La funda para portátil Case Logic es excelente para proteger mi portátil.', '08-09-2023');

-- Venta
INSERT INTO tab_venta VALUES (1, 1, 539000, '05-05-2023');
INSERT INTO tab_venta VALUES (2, 2, 449000, '05-08-2023');
INSERT INTO tab_venta VALUES (3, 3, 239000, '10-07-2023');
INSERT INTO tab_venta VALUES (4, 4, 170500, '15-09-2023');
INSERT INTO tab_venta VALUES (5, 5, 407000, '20-06-2023');
INSERT INTO tab_venta VALUES (6, 6, 719000, '02-04-2023');
INSERT INTO tab_venta VALUES (7, 7, 239000, '25-09-2023');
INSERT INTO tab_venta VALUES (8, 8, 290000, '08-11-2023');
INSERT INTO tab_venta VALUES (9, 9, 119000, '20-11-2023');
INSERT INTO tab_venta VALUES (10, 10, 477000, '25-10-2023');
INSERT INTO tab_venta VALUES (11, 11, 200000, '10-09-2023');
INSERT INTO tab_venta VALUES (12, 12, 200000, '01-08-2023');
INSERT INTO tab_venta VALUES (13, 13, 200000, '21-07-2023');
INSERT INTO tab_venta VALUES (14, 14, 59000, '20-10-2023');
INSERT INTO tab_venta VALUES (15, 15, 13900, '03-09-2023');

-- Detalle Venta
INSERT INTO tab_detalle_venta VALUES (1, 1, 1, 1, 539000);
INSERT INTO tab_detalle_venta VALUES (2, 2, 2, 1, 449000);
INSERT INTO tab_detalle_venta VALUES (3, 3, 6, 1, 239000);
INSERT INTO tab_detalle_venta VALUES (4, 4, 11, 1, 170500);
INSERT INTO tab_detalle_venta VALUES (5, 5, 4, 1, 407000);
INSERT INTO tab_detalle_venta VALUES (6, 6, 5, 1, 719000);
INSERT INTO tab_detalle_venta VALUES (7, 7, 6, 1, 239000);
INSERT INTO tab_detalle_venta VALUES (8, 8, 22, 1, 290000);
INSERT INTO tab_detalle_venta VALUES (9, 9, 16, 1, 119000);
INSERT INTO tab_detalle_venta VALUES (10, 10, 9, 1, 477000);
INSERT INTO tab_detalle_venta VALUES (11, 11, 10, 1, 200000);
INSERT INTO tab_detalle_venta VALUES (12, 12, 3, 1, 200000);
INSERT INTO tab_detalle_venta VALUES (13, 13, 12, 1, 200000);
INSERT INTO tab_detalle_venta VALUES (14, 14, 13, 1, 59000);
INSERT INTO tab_detalle_venta VALUES (15, 15, 25, 1, 13900);

--- STORED PROCEDURES ---

-- INSERTAR

CREATE OR REPLACE PROCEDURE sp_insertar_categoria(
    p_nombre IN VARCHAR2
)
IS
BEGIN
    INSERT INTO tab_categoria (id_categoria, nombre)
    VALUES ((SELECT NVL(MAX(id_categoria), 0) + 1 FROM tab_categoria), p_nombre);
    COMMIT;
END sp_insertar_categoria;

CREATE OR REPLACE PROCEDURE sp_insertar_cliente(
    v_nombre IN VARCHAR2,
    v_apellido1 IN VARCHAR2,
    v_apellido2 IN VARCHAR2,
    v_email IN VARCHAR2,
    v_telefono IN VARCHAR2,
    v_fecha_registro IN DATE
)
IS
BEGIN
    INSERT INTO tab_cliente (id_cliente, nombre, apellido1, apellido2, email, telefono, fecha_registro)
    VALUES ((SELECT NVL(MAX(id_cliente), 0) + 1 FROM tab_cliente), v_nombre, v_apellido1, v_apellido2, v_email, v_telefono, v_fecha_registro);
    COMMIT;
END sp_insertar_cliente;

CREATE OR REPLACE PROCEDURE sp_insertar_sucursal(
    v_nombre IN VARCHAR2,
    v_provincia IN VARCHAR2,
    v_canton IN VARCHAR2,
    v_direccion IN VARCHAR2,
    v_telefono IN VARCHAR2,
    v_sitio_web IN VARCHAR2
)
IS
BEGIN
    INSERT INTO tab_sucursal (id_sucursal, nombre, provincia, canton, direccion, telefono, sitio_web)
    VALUES ((SELECT NVL(MAX(id_sucursal), 0) + 1 FROM tab_sucursal), v_nombre, v_provincia, v_canton, v_direccion, v_telefono, v_sitio_web);
    COMMIT;
END sp_insertar_sucursal;

CREATE OR REPLACE PROCEDURE sp_insertar_empleado(
    v_nombre IN VARCHAR2,
    v_apellido1 IN VARCHAR2,
    v_apellido2 IN VARCHAR2,
    v_email IN VARCHAR2,
    v_telefono IN VARCHAR2,
    v_fecha_contratacion IN DATE,
    v_id_sucursal IN NUMBER,
    v_id_cargo IN NUMBER
)
IS
BEGIN
    INSERT INTO tab_empleado (id_empleado, id_sucursal, id_cargo, fecha_contratacion, nombre, apellido, telefono, email)
    VALUES ((SELECT NVL(MAX(id_empleado), 0) + 1 FROM tab_empleado), v_id_sucursal, v_id_cargo, v_fecha_contratacion, v_nombre, v_apellido1 || ' ' || v_apellido2, v_telefono, v_email);
    COMMIT;
END sp_insertar_empleado;

CREATE OR REPLACE PROCEDURE sp_insertar_proveedor(
    v_nombre IN VARCHAR2,
    v_apellido IN VARCHAR2,
    v_email IN VARCHAR2,
    v_telefono IN VARCHAR2
)
IS
BEGIN
    INSERT INTO tab_proveedor (id_proveedor, nombre, apellido, telefono, email)
    VALUES ((SELECT NVL(MAX(id_proveedor), 0) + 1 FROM tab_proveedor), v_nombre, v_apellido, v_telefono, v_email);
    COMMIT;
END sp_insertar_proveedor;

CREATE OR REPLACE PROCEDURE sp_insertar_inventario(
    v_id_sucursal IN NUMBER,
    v_id_producto IN NUMBER,
    v_id_proveedor IN NUMBER,
    v_cant_disponible IN NUMBER
)
IS
BEGIN
    INSERT INTO tab_inventario (id_inventario, id_sucursal, id_producto, id_proveedor, cant_disponible)
    VALUES ((SELECT NVL(MAX(id_inventario), 0) + 1 FROM tab_inventario), v_id_sucursal, v_id_producto, v_id_proveedor, v_cant_disponible);
    COMMIT;
END sp_insertar_inventario;

CREATE OR REPLACE PROCEDURE sp_insertar_detalle_venta(
    v_id_venta IN NUMBER,
    v_id_producto IN NUMBER,
    v_cantidad IN NUMBER,
    v_precio_unitario IN NUMBER
)
IS
BEGIN
    INSERT INTO tab_detalle_venta (id_detalle, id_venta, id_producto, cantidad, precio_unitario)
    VALUES ((SELECT NVL(MAX(id_detalle), 0) + 1 FROM tab_detalle_venta), v_id_venta, v_id_producto, v_cantidad, v_precio_unitario);
    COMMIT;
END sp_insertar_detalle_venta;

CREATE OR REPLACE PROCEDURE sp_insertar_comentario(
    v_id_cliente IN NUMBER,
    v_id_producto IN NUMBER,
    v_calificacion IN NUMBER,
    v_comentario IN VARCHAR2,
    v_fecha IN DATE
)
IS
BEGIN
    INSERT INTO tab_comentario (id_comentario, id_cliente, id_producto, calificacion, comentario, fecha)
    VALUES ((SELECT NVL(MAX(id_comentario), 0) + 1 FROM tab_comentario), v_id_cliente, v_id_producto, v_calificacion, v_comentario, v_fecha);
    COMMIT;
END sp_insertar_comentario;

---------------------- LEER -----------------------

CREATE OR REPLACE PROCEDURE sp_obtener_datos(
    v_cursor OUT SYS_REFCURSOR,
    v_tabla VARCHAR2
)
IS
    v_select VARCHAR2(1000);
BEGIN
    v_select := 'SELECT * FROM ' || v_tabla;
    OPEN v_cursor FOR v_select;
    
END sp_obtener_datos;

---------------------- ACTUALIZAR -----------------------
CREATE OR REPLACE PROCEDURE sp_actualizar_categoria(
    p_id_categoria IN NUMBER,
    p_nombre IN VARCHAR2
)
IS
BEGIN
    UPDATE tab_categoria
    SET nombre = p_nombre
    WHERE id_categoria = p_id_categoria;
    COMMIT;
END sp_actualizar_categoria;

CREATE OR REPLACE PROCEDURE sp_actualizar_cliente(
    v_id_cliente IN NUMBER,
    v_nombre IN VARCHAR2,
    v_apellido1 IN VARCHAR2,
    v_apellido2 IN VARCHAR2,
    v_email IN VARCHAR2,
    v_telefono IN VARCHAR2,
    v_fecha_registro IN DATE
)
IS
BEGIN
    UPDATE tab_cliente
    SET nombre = v_nombre,
        apellido1 = v_apellido1,
        apellido2 = v_apellido2,
        email = v_email,
        telefono = v_telefono,
        fecha_registro = v_fecha_registro
    WHERE id_cliente = v_id_cliente;
    COMMIT;
END sp_actualizar_cliente;

CREATE OR REPLACE PROCEDURE sp_actualizar_sucursal(
    v_id_sucursal IN NUMBER,
    v_nombre IN VARCHAR2,
    v_provincia IN VARCHAR2,
    v_canton IN VARCHAR2,
    v_direccion IN VARCHAR2,
    v_telefono IN VARCHAR2,
    v_sitio_web IN VARCHAR2
)
IS
BEGIN
    UPDATE tab_sucursal
    SET nombre = v_nombre,
        provincia = v_provincia,
        canton = v_canton,
        direccion = v_direccion,
        telefono = v_telefono,
        sitio_web = v_sitio_web
    WHERE id_sucursal = v_id_sucursal;
    COMMIT;
END sp_actualizar_sucursal;

CREATE OR REPLACE PROCEDURE sp_actualizar_empleado(
    v_id_empleado IN NUMBER,
    v_nombre IN VARCHAR2,
    v_apellido IN VARCHAR2,
    v_email IN VARCHAR2,
    v_telefono IN VARCHAR2,
    v_fecha_contratacion IN DATE,
    v_id_sucursal IN NUMBER,
    v_id_cargo IN NUMBER
)
IS
BEGIN
    UPDATE tab_empleado
    SET nombre = v_nombre,
        apellido = v_apellido,
        email = v_email,
        telefono = v_telefono,
        fecha_contratacion = v_fecha_contratacion,
        id_sucursal = v_id_sucursal,
        id_cargo = v_id_cargo
    WHERE id_empleado = v_id_empleado;
    COMMIT;
END sp_actualizar_empleado;

CREATE OR REPLACE PROCEDURE sp_actualizar_proveedor(
    v_id_proveedor IN NUMBER,
    v_nombre IN VARCHAR2,
    v_apellido IN VARCHAR2,
    v_email IN VARCHAR2,
    v_telefono IN VARCHAR2
)
IS
BEGIN
    UPDATE tab_proveedor
    SET nombre = v_nombre,
        apellido = v_apellido,
        email = v_email,
        telefono = v_telefono
    WHERE id_proveedor = v_id_proveedor;
    COMMIT;
END sp_actualizar_proveedor;

CREATE OR REPLACE PROCEDURE sp_actualizar_inventario(
    v_id_inventario IN NUMBER,
    v_id_sucursal IN NUMBER,
    v_id_producto IN NUMBER,
    v_id_proveedor IN NUMBER,
    v_cant_disponible IN NUMBER
)
IS
BEGIN
    UPDATE tab_inventario
    SET id_sucursal = v_id_sucursal,
        id_producto = v_id_producto,
        id_proveedor = v_id_proveedor,
        cant_disponible = v_cant_disponible
    WHERE id_inventario = v_id_inventario;
    COMMIT;
END sp_actualizar_inventario;

CREATE OR REPLACE PROCEDURE sp_actualizar_detalle_venta(
    v_id_detalle IN NUMBER,
    v_id_venta IN NUMBER,
    v_id_producto IN NUMBER,
    v_cantidad IN NUMBER,
    v_precio_unitario IN NUMBER
)
IS
BEGIN
    UPDATE tab_detalle_venta
    SET id_venta = v_id_venta,
        id_producto = v_id_producto,
        cantidad = v_cantidad,
        precio_unitario = v_precio_unitario
    WHERE id_detalle = v_id_detalle;
    COMMIT;
END sp_actualizar_detalle_venta;

CREATE OR REPLACE PROCEDURE sp_actualizar_comentario(
    v_id_comentario IN NUMBER,
    v_id_cliente IN NUMBER,
    v_id_producto IN NUMBER,
    v_calificacion IN NUMBER,
    v_comentario IN VARCHAR2,
    v_fecha IN DATE
)
IS
BEGIN
    UPDATE tab_comentario
    SET id_cliente = v_id_cliente,
        id_producto = v_id_producto,
        calificacion = v_calificacion,
        comentario = v_comentario,
        fecha = v_fecha
    WHERE id_comentario = v_id_comentario;
    COMMIT;
END sp_actualizar_comentario;

---------------------- ELIMINAR -----------------------

CREATE OR REPLACE PROCEDURE eliminar_registro (
    v_id IN NUMBER,
    v_tabla IN VARCHAR2
)
IS
    v_columna_id VARCHAR2(100);
BEGIN

    CASE v_tabla
        WHEN 'tab_empleado' THEN
            v_columna_id := 'id_empleado';
        WHEN 'tab_proveedor' THEN
            v_columna_id := 'id_proveedor';
        WHEN 'tab_sucursal' THEN
            v_columna_id := 'id_sucursal';
        WHEN 'tab_categoria' THEN
            v_columna_id := 'id_categoria';
        WHEN 'tab_inventario' THEN
            v_columna_id := 'id_inventario';
        WHEN 'tab_detalle_venta' THEN
            v_columna_id := 'id_detalle';
        WHEN 'tab_cliente' THEN
            v_columna_id := 'id_cliente';
        WHEN 'tab_comentario' THEN
            v_columna_id := 'id_comentario';
        ELSE
            DBMS_OUTPUT.PUT_LINE('Tabla no válida');
            RETURN;
    END CASE;
    
    EXECUTE IMMEDIATE 'DELETE FROM ' || v_tabla || ' WHERE ' || v_columna_id || ' = :1' USING v_id;
    DBMS_OUTPUT.PUT_LINE('Registro eliminado correctamente de ' || v_tabla);
END;
