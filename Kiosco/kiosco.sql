
-- alter de tabla productos 08032022
ALTER TABLE `db_springboot_backend`.`productos` 
ADD COLUMN `tipo_producto_id` BIGINT NULL AFTER `proveedor_id`,
ADD INDEX `FKTipoProducto_idx` (`tipo_producto_id` ASC) VISIBLE;
;
ALTER TABLE `db_springboot_backend`.`productos` 
ADD CONSTRAINT `FKTipoProducto`
  FOREIGN KEY (`tipo_producto_id`)
  REFERENCES `db_springboot_backend`.`tipo_producto` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


--Creacion de tabla tipo_producto
CREATE TABLE `tipo_producto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user` varchar(50) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `codigo` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=473 DEFAULT CHARSET=utf8mb3;

ALTER TABLE `db_springboot_backend`.`tipo_producto` 
ADD COLUMN `deleted` TINYINT NULL DEFAULT '0' AFTER `codigo`;


--Ajuste de iva en inventario
ALTER TABLE `db_springboot_backend`.`inventarios` 
ADD COLUMN `is_impuesto` TINYINT(1) NULL DEFAULT 0 AFTER `metodopago`,
ADD COLUMN `impuestos` DOUBLE NULL AFTER `is_impuesto`,
ADD COLUMN `is_iva` TINYINT(1) NULL DEFAULT 0 AFTER `impuestos`;



-- Se agrega tabla de logs
CREATE TABLE `db_springboot_backend`.`logs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(45) NOT NULL,
  `create_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_at` TIMESTAMP NULL,
  `entity` VARCHAR(45) NULL,
  `metodo` VARCHAR(45) NULL
  `description_old` TEXT NULL,
  `description_new` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);


ALTER TABLE `db_springboot_backend`.`logs` 
ADD COLUMN `metodo` VARCHAR(45) NULL AFTER `entity`;

ALTER TABLE `db_springboot_backend`.`logs` 
DROP COLUMN `action`,
DROP COLUMN `name`,
CHANGE COLUMN `description_old` `description_old` TEXT NULL DEFAULT NULL ,
CHANGE COLUMN `description_new` `description_new` TEXT NULL DEFAULT NULL ;

ALTER TABLE `db_springboot_backend`.`logs` 
CHANGE COLUMN `id` `id` INT NOT NULL AUTO_INCREMENT ;


-- responsable en facturas al costo
ALTER TABLE `db_springboot_backend`.`facturas` 
ADD COLUMN `responsable_id` BIGINT NULL DEFAULT NULL AFTER `costo`;


-- Se agrega registros de saldos 
CREATE TABLE `saldos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  `efectivo` double DEFAULT '0',
  `mercado_pago` double DEFAULT '0',
  `patrimonio_pesos` double DEFAULT '0',
  `patrimonio_dolar` double DEFAULT '0',
  `punto_venta` double DEFAULT '0',
  `pedidos_ya` double DEFAULT '0',
  `ganancias` double DEFAULT '0',
  `perdidas` double DEFAULT '0',
  `gastos` double DEFAULT '0',
  `diferencias` double DEFAULT '0',
  `tasa_dolar` double DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
SELECT * FROM db_springboot_backend.saldos;




# Se agrea saldo dollar 
ALTER TABLE `db_springboot_backend`.`cajachica` 
ADD COLUMN `saldodollar` DOUBLE NULL DEFAULT 0 AFTER `saldoefectivo`,
ADD COLUMN `isdollar` BIT(1) NULL DEFAULT b'0' AFTER `transferencia`;

ALTER TABLE `db_springboot_backend`.`cajachica` 
ADD COLUMN `tasa` DOUBLE NULL DEFAULT 0 AFTER `isdollar`;

ALTER TABLE `db_springboot_backend`.`saldos` 
ADD COLUMN `dollar` DOUBLE NULL DEFAULT 0 AFTER `tasa_dolar`;

ALTER TABLE `db_springboot_backend`.`saldos` 
ADD COLUMN `is_transferencia` BIT(1) NULL DEFAULT b'0' AFTER `dollar`;


# Se add column existencia en perdidas
ALTER TABLE `db_springboot_backend`.`perdidas` 
ADD COLUMN `existencia` DOUBLE NULL DEFAULT 0 AFTER `cantidad`;


# Se crea tabla de promociones 
 CREATE TABLE `promociones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  `deleted` TINYINT DEFAULT '0',
  `is_complete` TINYINT DEFAULT '0',
  `is_several` TINYINT DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

ALTER TABLE `db_springboot_backend`.`productos` 
ADD COLUMN `promocion_id` BIGINT NULL DEFAULT NULL AFTER `tipo_producto_id`


ALTER TABLE `db_springboot_backend`.`productos` 
ADD COLUMN `is_promocion_total` TINYINT NULL DEFAULT 0 AFTER `promocin_id`;

ALTER TABLE `db_springboot_backend`.`facturas_items` 
ADD COLUMN `is_promocion` TINYINT(1) NULL DEFAULT 0 AFTER `consignacion`;


# Requerimiento de cuadre de caja
ALTER TABLE `db_springboot_backend`.`cajas` 
ADD COLUMN `is_cuadre` BIT(1) NULL DEFAULT b'0' AFTER `ganancia`;

ALTER TABLE `db_springboot_backend`.`cajas` 
CHANGE COLUMN `is_cuadre` `is_cuadre` BIGINT(1) NULL DEFAULT 0 ;



# Facturas al credito
ALTER TABLE `db_springboot_backend`.`facturas` 
ADD COLUMN `is_credito` TINYINT NOT NULL DEFAULT b'0' AFTER `responsable_id`,
ADD COLUMN `usu_cred_id` BIGINT NULL DEFAULT NULL AFTER `is_credito`,
ADD COLUMN `fecha_aprob` TIMESTAMP NULL DEFAULT NULL AFTER `usu_cred_id`,
ADD COLUMN `is_aprobada` TINYINT NULL DEFAULT b'0' AFTER `fecha_aprob`,
ADD COLUMN `fecha_pago` TIMESTAMP NULL DEFAULT NULL AFTER `is_aprobada`,
ADD COLUMN `is_pago` TINYINT NOT NULL DEFAULT b'0' AFTER `fecha_pago`,
ADD COLUMN `usu_res_pago_id` BIGINT NULL DEFAULT NULL AFTER `is_pago`,
ADD COLUMN `usu_aprob` BIGINT NULL DEFAULT NULL AFTER `usu_res_pago_id`;


ALTER TABLE `db_springboot_backend`.`facturas` 
CHANGE COLUMN `usu_aprob` `usu_aprob_id` BIGINT NULL DEFAULT NULL ;


# Modulo de promociones

ALTER TABLE `db_springboot_backend`.`productos` 
CHANGE COLUMN `promocion_id` `promotion_id` BIGINT(20) NULL DEFAULT NULL ;


DROP TABLE `db_springboot_backend`.`promociones`; 

# Se crea tabla de promociones 
 CREATE TABLE `promotions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  `deleted` TINYINT DEFAULT '0',
  `name` VARCHAR(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double DEFAULT '0',
  `percent` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

 ALTER TABLE `db_springboot_backend`.`promotions` 
ADD UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE;
;

ADD COLUMN `code` VARCHAR(5) NOT NULL AFTER `percent`, RENAME TO  `db_springboot_backend`.`promotions` ;

