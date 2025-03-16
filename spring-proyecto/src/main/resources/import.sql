INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES('Juan','Perez','Empresa A','Gerente','Calle Ficticia 123',12345,'Madrid',600111222,'1985-04-25');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES('Maria','Lopez','Empresa B','Desarrolladora','Avenida Principal 456',23456,'Barcelona',600222333,'1990-02-15');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES('Carlos','Gomez','Empresa C','Analista','Calle Luna 789',34567,'Sevilla',600333444,'1983-11-30');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES('Ana','Martinez','Empresa D','Diseñadora','Calle Sol 101',45678,'Valencia',600444555,'1992-08-10');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES('Luis','Rodriguez','Empresa E','Administrador','Calle Norte 202',56789,'Bilbao',600555666,'1988-01-17');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES('Elena','Hernandez','Empresa F','Consultora','Calle Mar 303',67890,'Zaragoza',600666777,'1995-12-05');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES('Pedro','Garcia','Empresa G','CEO','Calle Oeste 404',78901,'Murcia',600777888,'1980-07-21');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES('Laura','Fernandez','Empresa H','Contadora','Calle Este 505',89012,'Palma',600888999,'1993-09-11');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES('Javier','Sanchez','Empresa I','Marketing','Calle Centro 606',90123,'Alicante',600999000,'1986-03-14');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES('Isabel','Jimenez','Empresa J','Jefa de ventas','Calle Sur 707',12312,'Vigo',601000111,'1991-10-02');

INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES('Laptop','Laptop Dell con 16GB de RAM',1200,50,'Electrónica','Proveedor A','2025-01-01');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES('Teclado','Teclado mecánico para gaming',100,200,'Accesorios','Proveedor B','2025-01-05');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES('Ratón','Ratón inalámbrico de alta precisión',30,150,'Accesorios','Proveedor C','2025-01-10');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES('Monitor','Monitor 27 pulgadas, 144Hz',350,75,'Electrónica','Proveedor D','2025-02-12');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES('Cargador','Cargador rápido para móvil',25,300,'Accesorios','Proveedor E','2025-02-15');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES('Auriculares','Auriculares Bluetooth con cancelación de ruido',150,50,'Electrónica','Proveedor F','2025-03-01');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES('Mochila','Mochila para laptop de 15 pulgadas',50,100,'Accesorios','Proveedor G','2025-03-10');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES('Cable HDMI','Cable HDMI 2.0 de 2 metros',15,500,'Accesorios','Proveedor H','2025-03-12');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES('Smartphone','Smartphone 5G con cámara de 48MP',500,100,'Electrónica','Proveedor I','2025-03-15');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES('Impresora','Impresora láser multifuncional',250,25,'Electrónica','Proveedor J','2025-03-18');

INSERT INTO compra (cliente_id, articulo_id, fecha_compra, cantidad, total, iva, total_iva) VALUES(1,1,'2025-03-20',2,2400,480,2880);
INSERT INTO compra (cliente_id, articulo_id, fecha_compra, cantidad, total, iva, total_iva) VALUES(2,3,'2025-03-21',5,150,30,180);
INSERT INTO compra (cliente_id, articulo_id, fecha_compra, cantidad, total, iva, total_iva) VALUES(3,2,'2025-03-22',3,300,60,360);
INSERT INTO compra (cliente_id, articulo_id, fecha_compra, cantidad, total, iva, total_iva) VALUES(4,4,'2025-03-23',1,350,70,420);
INSERT INTO compra (cliente_id, articulo_id, fecha_compra, cantidad, total, iva, total_iva) VALUES(5,5,'2025-03-24',4,100,20,120);
INSERT INTO compra (cliente_id, articulo_id, fecha_compra, cantidad, total, iva, total_iva) VALUES(6,6,'2025-03-25',2,300,60,360);
INSERT INTO compra (cliente_id, articulo_id, fecha_compra, cantidad, total, iva, total_iva) VALUES(7,7,'2025-03-26',1,50,10,60);
INSERT INTO compra (cliente_id, articulo_id, fecha_compra, cantidad, total, iva, total_iva) VALUES(8,8,'2025-03-27',3,45,9,54);
INSERT INTO compra (cliente_id, articulo_id, fecha_compra, cantidad, total, iva, total_iva) VALUES(9,9,'2025-03-28',2,1000,200,1200);
INSERT INTO compra (cliente_id, articulo_id, fecha_compra, cantidad, total, iva, total_iva) VALUES(10,10,'2025-03-29',1,250,50,300);
