
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
CREATE TABLE `db_springboot_backend`.`saldos` (
  `id` BIGINT NOT NULL,
  `user` VARCHAR(45) NULL DEFAULT NULL,
  `create_at` TIMESTAMP NULL DEFAULT NULL,
  `update_at` TIMESTAMP NULL DEFAULT NULL,
  `efectivo` DOUBLE NULL DEFAULT 0,
  `mercadoPago` DOUBLE NULL DEFAULT 0,
  `patrimoniPesos` DOUBLE NULL DEFAULT 0,
  `patrimonioDolar` DOUBLE NULL DEFAULT 0,
  `puntoVenta` DOUBLE NULL DEFAULT 0,
  `pedidosYa` DOUBLE NULL DEFAULT 0,
  `ganancias` DOUBLE NULL DEFAULT 0,
  `perdidas` DOUBLE NULL DEFAULT 0,
  `gastos` DOUBLE NULL DEFAULT 0,
  `diferencias` DOUBLE NULL DEFAULT 0,
  `tasaDolar` DOUBLE NULL DEFAULT 0,
  PRIMARY KEY (`id`));

ALTER TABLE `db_springboot_backend`.`saldos` 
CHANGE COLUMN `mercadoPago` `mercado_pago` DOUBLE NULL DEFAULT '0' ,
CHANGE COLUMN `patrimoniPesos` `patrimoni_pesos` DOUBLE NULL DEFAULT '0' ,
CHANGE COLUMN `patrimonioDolar` `patrimonio_dolar` DOUBLE NULL DEFAULT '0' ,
CHANGE COLUMN `puntoVenta` `punto_venta` DOUBLE NULL DEFAULT '0' ,
CHANGE COLUMN `pedidosYa` `pedidos_ya` DOUBLE NULL DEFAULT '0' ;