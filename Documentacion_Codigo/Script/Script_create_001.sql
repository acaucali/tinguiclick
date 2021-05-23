/*
*
	Tabla de reporte
*
*/

CREATE TABLE reporte (
  reporte_id serial PRIMARY KEY,
  titulo varchar(100) NULL,
  detalle text NULL, 
);

/*
*
	Tablas de parametrización
*
*/

CREATE TABLE estado_pedido (
  estado_pedido_id serial PRIMARY KEY,
  nombre varchar(150) NULL,
  descripcion varchar(300) NULL, 
);

CREATE TABLE tipo_pedido (
  tipo_pedido_id serial PRIMARY KEY,
  nombre varchar(150) NULL,
  descripcion varchar(300) NULL, 
);

CREATE TABLE tipo_aliado (
  tipo_aliado_id serial PRIMARY KEY,
  nombre varchar(150) NULL,
  descripcion varchar(300) NULL, 
);

CREATE TABLE tipo_pago_electronico (
  tipo_pago_id serial PRIMARY KEY,
  nombre varchar(150) NULL,
  descripcion varchar(300) NULL, 
);

CREATE TABLE tipo_identificacion (
  tipo_identifiacion_id serial PRIMARY KEY,
  nombre varchar(150) NULL,
  descripcion varchar(300) NULL, 
);


/*
*
	Tarifas y facturas
*
*/

CREATE TABLE tarifas (
  tarifa_id serial PRIMARY KEY,
  tipo numeric (1) NULL,
  descripcion varchar(300) NULL,
  valor float NULL, 
);

CREATE TABLE facturas (
  factura_id serial PRIMARY KEY,
  tipo numeric (1) NULL,
  descripcion varchar(300) NULL,
  valor float NULL, 
  estado numeric (1) NULL,
  fecha_registro timestamp without time zone,
  fecha_finalizado timestamp without time zone,
  detalle_id numeric(10, 0) NOT NULL,
  domiciliario_id numeric(10, 0) NOT NULL,
  
);

CREATE TABLE detalle_factura (
  detalle_factura_id serial PRIMARY KEY,
  aliado_id numeric(10, 0) NOT NULL,
  pago_id numeric(10, 0) NOT NULL,
  aliado_nit varchar(50)  NULL,
  cantidad integer NULL,
  destino varchar(500) NULL, 
  
);

/*
*
	Aliados
*
*/

CREATE TABLE aliados (
  aliado_id serial PRIMARY KEY,
  razon_social varchar(300)  NULL,
  nombre_comercial varchar(300)  NULL,
  nit varchar(50)  NULL,
  nombre varchar(400)  NULL,
  telefono integer NULL,
  pago_electronico numeric(10, 0) NOT NULL,	
  no_cuenta_bancaria varchar(50) NULL,
  direccion_factura varchar(200) NULL,
  email_factura varchar(200) NULL,
  categoria_principal varchar(500) NULL,
  categoria_secundaria varchar(500) NULL,
  categoria_terciaria varchar(500) NULL,
  tipo_aliado numeric(10, 0) NOT NULL,
  
);

/*
*
	Domiciliarios
*
*/

CREATE TABLE domiciliarios (
  domiciliario_id serial PRIMARY KEY,
  nombres varchar(300)  NULL,
  apellidos varchar(300)  NULL,
  no_identificacion numeric(20) NULL,
  eps varchar(150)  NULL,
  telefono numeric(20)  NULL,
  direccion_hogar varchar(200)  NULL,
  grupo_sanguineo varchar(100)  NULL,
  pasaporte varchar(100)  NULL,
  nacionalidad varchar(200)  NULL,
  tipo_identificacion numeric(10, 0) NOT NULL,
  arriendo varchar()  NULL,
  duracion_arriendo numeric(10) NULL,
  horario_disponibilidad varchar()  NULL,		
);

/*
*
	Pedidos
*
*/

CREATE TABLE pedidos (
  pedido_id serial PRIMARY KEY,
  nombre_cliente varchar(300)  NULL,
  apellido_cliente varchar(300)  NULL,
  direccion_cliente varchar(200)  NULL,
  numero_celular varchar(50)  NULL,
  telefono numeric(20) NULL,
  municipio varchar(300)  NULL,
  dato_adicional_direccion varchar(300)  NULL,
  ciudad varchar(300)  NULL,
  metodo_pago numeric(10, 0) NOT NULL,	
  detalle varchar NULL,
  observacion varchar NULL,
  valor varchar(100) NULL,
  alerta numeric(1) NULL,
  estado_id numeric(10, 0) NOT NULL,	
  tipo_id numeric(10, 0) NOT NULL,	
  tarifa_id numeric(10, 0) NOT NULL,	
  domiciliario_id numeric(10, 0) NOT NULL,	  
  aliado_id numeric(10, 0) NOT NULL,		
);

/*
ALTER TABLE ONLY accion
    ADD CONSTRAINT ak_accion UNIQUE (problema_id, padre_id, nombre);

CONSTRAINT fk_organizacion_reporte FOREIGN KEY (organizacion_id)
      REFERENCES organizacion (organizacion_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
*/




























