
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




  CREATE TABLE `promotions_products` (
    `promotion_id` bigint NOT NULL,
    `product_id` bigint NOT NULL,
    UNIQUE KEY `UKitems` (`promotion_id`,`product_id`),
    KEY `FKiteminventario` (`product_id`),
    CONSTRAINT `FKpromotio` FOREIGN KEY (`promotion_id`) REFERENCES `Promotions` (`id`),
    CONSTRAINT `FKproducts` FOREIGN KEY (`product_id`) REFERENCES `productos` (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

  ALTER TABLE `db_springboot_backend`.`promotions_products` 
  ADD COLUMN `price` DOUBLE NOT NULL DEFAULT 0 AFTER `product_id`;


  ALTER TABLE `db_springboot_backend`.`promotions_products` 
  ADD COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT FIRST,
  ADD COLUMN `user` VARCHAR(45) NULL DEFAULT NULL AFTER `id`,
  ADD COLUMN `create_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `user`,
  ADD COLUMN `update_at` TIMESTAMP NULL DEFAULT NULL AFTER `create_at`,
  ADD PRIMARY KEY (`id`);
;

  ALTER TABLE `db_springboot_backend`.`promotions_products` 
  DROP COLUMN `price`;


  ALTER TABLE `db_springboot_backend`.`productos` 
  ADD COLUMN `price_promotion` DOUBLE NULL DEFAULT '0' AFTER `is_promocion_total`;


  ALTER TABLE `db_springboot_backend`.`promotions_products` 
  DROP COLUMN `update_at`,
  DROP COLUMN `create_at`,
  DROP COLUMN `user`,
  DROP COLUMN `id`,
  DROP PRIMARY KEY;
  ;

  ALTER TABLE `db_springboot_backend`.`promotions_products` 
  ADD COLUMN `price` DOUBLE NOT NULL AFTER `product_id`;

  ALTER TABLE `db_springboot_backend`.`promotions_products` 
ADD INDEX `FKPromotion_idx` (`promotion_id` ASC) VISIBLE;
;
ALTER TABLE `db_springboot_backend`.`promotions_products` 
ADD CONSTRAINT `FKPromotion`
  FOREIGN KEY (`promotion_id`)
  REFERENCES `db_springboot_backend`.`promotions` (`id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

  ALTER TABLE `db_springboot_backend`.`promotions_products` 
CHANGE COLUMN `price` `price` DOUBLE NULL ;

ALTER TABLE `db_springboot_backend`.`productos` 
DROP COLUMN `price_promotion`;


ALTER TABLE `db_springboot_backend`.`promotions_products` 
ADD COLUMN `amount` DOUBLE NULL DEFAULT NULL AFTER `price`;

## Modulo de proveedor
ALTER TABLE `db_springboot_backend`.`inventarios` 
ADD COLUMN `proveedor_id` BIGINT NULL DEFAULT NULL AFTER `is_iva`;

ALTER TABLE `db_springboot_backend`.`gastos` 
ADD COLUMN `proveedor_id` BIGINT NULL DEFAULT NULL AFTER `metodopago`;


## Creacion de Nuevo ROL
INSERT INTO `db_springboot_backend`.`roles` (`id`, `nombre`) VALUES ('3', 'ROLE_COORDINATOR');


## Refacto de Comision entity, se le agrega EntityCommon
ALTER TABLE `db_springboot_backend`.`comisiones` 
ADD COLUMN `user` VARCHAR(45) NULL AFTER `id`,
ADD COLUMN `create_at` TIMESTAMP NULL AFTER `user`,
ADD COLUMN `update_at` TIMESTAMP NULL AFTER `create_at`;

# ProductGroup
CREATE TABLE `product_group` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  `deleted` TINYINT DEFAULT '0',
  `name` VARCHAR(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

ALTER TABLE `db_springboot_backend`.`productos` 
ADD COLUMN `group_id` BIGINT NULL AFTER `is_promocion_total`;


ALTER TABLE `db_springboot_backend`.`productos` ALTER INDEX `FKGroup_idx` VISIBLE;
ALTER TABLE `db_springboot_backend`.`productos` 
DROP FOREIGN KEY `FKgroup`;
ALTER TABLE `db_springboot_backend`.`productos` ADD CONSTRAINT `FKGroup`
  FOREIGN KEY (`group_id`)
  REFERENCES `db_springboot_backend`.`product_group` (`id`)
  ON DELETE SET NULL
  ON UPDATE NO ACTION;


# Se agrega tabla para cuentas requerimiento Declaracion de Diferencia en cuentas
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  `deleted` TINYINT DEFAULT '0',
  `name` VARCHAR(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `difference_box` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  `deleted` TINYINT DEFAULT '0',
  `amount` double DEFAULT '0',
  `box_id` BIGINT DEFAULT NULL,
  `account_id` BIGINT DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

ALTER TABLE `db_springboot_backend`.`clientes` 
ADD COLUMN `user` VARCHAR(45) NULL AFTER `id`,
ADD COLUMN `update_at` TIMESTAMP NULL AFTER `create_at`,
CHANGE COLUMN `create_at` `create_at` TIMESTAMP NULL AFTER `user`;

ALTER TABLE `db_springboot_backend`.`clientes` 
CHANGE COLUMN `update_at` `update_at` TIMESTAMP NULL DEFAULT NULL AFTER `create_at`;


ALTER TABLE `db_springboot_backend`.`retiroscaja` 
ADD COLUMN `user` VARCHAR(45) NULL AFTER `id`,
ADD COLUMN `create_at` TIMESTAMP NULL DEFAULT NULL AFTER `user`,
ADD COLUMN `update_at` TIMESTAMP NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`cajas` 
DROP FOREIGN KEY `FK_clientes`;
ALTER TABLE `db_springboot_backend`.`cajas` 
CHANGE COLUMN `cliente_id` `cliente_id` BIGINT NULL DEFAULT NULL ,
DROP INDEX `FK_cliente` ;
;

ALTER TABLE `db_springboot_backend`.`facturas` 
DROP FOREIGN KEY `FK1qiuk10rfkovhlfpsk7oic0v8`;
ALTER TABLE `db_springboot_backend`.`facturas` 
DROP INDEX `FK1qiuk10rfkovhlfpsk7oic0v8` ;
;

ALTER TABLE `db_springboot_backend`.`retiroscaja` 
DROP FOREIGN KEY `FKclientesid`;
ALTER TABLE `db_springboot_backend`.`retiroscaja` 
DROP INDEX `FKclienteid` ;
;


ALTER TABLE `db_springboot_backend`.`clientes` 
CHANGE COLUMN `id` `id` BIGINT NOT NULL AUTO_INCREMENT ;

ALTER TABLE `db_springboot_backend`.`retiroscaja` 
CHANGE COLUMN `cliente_id` `cliente_id` BIGINT NULL DEFAULT NULL ;

ALTER TABLE `db_springboot_backend`.`retiroscaja` 
ADD CONSTRAINT `FK_retiro_cliente`
  FOREIGN KEY (`cliente_id`)
  REFERENCES `db_springboot_backend`.`clientes` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  ALTER TABLE `db_springboot_backend`.`facturas` 
CHANGE COLUMN `cliente_id` `cliente_id` BIGINT NULL DEFAULT NULL ;

  ALTER TABLE `db_springboot_backend`.`facturas` 
ADD INDEX `FK_factura_cliente_idx` (`cliente_id` ASC) VISIBLE;
;
ALTER TABLE `db_springboot_backend`.`facturas` 
ADD CONSTRAINT `FK_factura_cliente`
  FOREIGN KEY (`cliente_id`)
  REFERENCES `db_springboot_backend`.`clientes` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


  ALTER TABLE `db_springboot_backend`.`cajas` 
CHANGE COLUMN `cliente_id` `cliente_id` BIGINT NULL DEFAULT NULL ;

  ALTER TABLE `db_springboot_backend`.`cajas` 
ADD CONSTRAINT `FK_caja_cliente`
  FOREIGN KEY (`cliente_id`)
  REFERENCES `db_springboot_backend`.`clientes` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


INSERT INTO `db_springboot_backend`.`account` (`id`, `user`, `create_at`, `deleted`, `name`, `description`) VALUES ('1', 'eareiza', null, '0', 'MERCADOPAGO', 'Mecado Pago');
INSERT INTO `db_springboot_backend`.`account` (`id`, `user`, `create_at`, `deleted`, `name`, `description`) VALUES ('2', 'eareiza', null, '0', 'PEDIDOYA', 'Pedido ya');
INSERT INTO `db_springboot_backend`.`account` (`id`, `user`, `create_at`, `deleted`, `name`, `description`) VALUES ('3', 'eareiza', null, '0', 'PUNTOVENTA', 'Punto de Venta');
INSERT INTO `db_springboot_backend`.`account` (`id`, `user`, `create_at`, `deleted`, `name`, `description`) VALUES ('4', 'eareiza', null, '0', 'PEDIDOYAEFECTIVO', 'Pedido ya efectivo');
INSERT INTO `db_springboot_backend`.`account` (`id`, `user`, `create_at`, `deleted`, `name`, `description`) VALUES ('5', 'eareiza', null, '0', 'EFECTIVO', 'Efectivo');
INSERT INTO `db_springboot_backend`.`account` (`id`, `user`, `create_at`, `deleted`, `name`, `description`) VALUES ('6', 'eareiza', null, '0', 'DOLAR', 'DOLAR');

UPDATE `db_springboot_backend`.`account` SET `deleted` = '1' WHERE (`id` = '4');
UPDATE `db_springboot_backend`.`account` SET `deleted` = '1' WHERE (`id` = '2');

ALTER TABLE `db_springboot_backend`.`clientes` 
ADD COLUMN `deleted` TINYINT NULL DEFAULT '0' AFTER `region_id`;

UPDATE `db_springboot_backend`.`clientes` SET `deleted` = '1' WHERE (`id` = '17');
UPDATE `db_springboot_backend`.`clientes` SET `deleted` = '1' WHERE (`id` = '20');
UPDATE `db_springboot_backend`.`clientes` SET `deleted` = '1' WHERE (`id` = '24');

ALTER TABLE `db_springboot_backend`.`retiroscaja` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`account` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`difference_box` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`gastos` 
ADD COLUMN `is_withdrawal` TINYINT NULL AFTER `proveedor_id`;


# Required abaot soft Delete in Invoice entity (esta listo en prod)
  ALTER TABLE `db_springboot_backend`.`facturas` 
  ADD COLUMN `deleted` TINYINT NULL DEFAULT '0' AFTER `usu_aprob_id`;

ALTER TABLE `db_springboot_backend`.`facturas` 
CHANGE COLUMN `deleted` `deleted` TINYINT NULL DEFAULT '0' AFTER `update_at`;


# delete object Cliente (Esta isto en prod)
SET FOREIGN_KEY_CHECKS = 0;
update usuarios set id = 26 where id = 13;
update usuarios set id = 13 where id = 4;
update usuarios set id = 15 where id = 5;
update usuarios set id = 16 where id = 3;
update usuarios set id = 17 where id = 6;
update usuarios set id = 20 where id = 8;
update usuarios set id = 21 where id = 7;
update usuarios set id = 22 where id = 9;
update usuarios set id = 24 where id = 12;
update usuarios set id = 25 where id = 14;
update usuarios set id = 27 where id = 15;

DELETE FROM `db_springboot_backend`.`usuarios_roles` WHERE (`usuario_id` = '1') and (`role_id` = '1');
DELETE FROM `db_springboot_backend`.`usuarios_roles` WHERE (`usuario_id` = '2') and (`role_id` = '1');
DELETE FROM `db_springboot_backend`.`usuarios_roles` WHERE (`usuario_id` = '3') and (`role_id` = '1');
DELETE FROM `db_springboot_backend`.`usuarios_roles` WHERE (`usuario_id` = '4') and (`role_id` = '1');
DELETE FROM `db_springboot_backend`.`usuarios_roles` WHERE (`usuario_id` = '5') and (`role_id` = '1');
DELETE FROM `db_springboot_backend`.`usuarios_roles` WHERE (`usuario_id` = '6') and (`role_id` = '1');
UPDATE `db_springboot_backend`.`usuarios_roles` SET `usuario_id` = '16' WHERE (`usuario_id` = '9') and (`role_id` = '1');
UPDATE `db_springboot_backend`.`usuarios_roles` SET `usuario_id` = '17' WHERE (`usuario_id` = '8') and (`role_id` = '1');
UPDATE `db_springboot_backend`.`usuarios_roles` SET `usuario_id` = '20' WHERE (`usuario_id` = '7') and (`role_id` = '1');
INSERT INTO `db_springboot_backend`.`usuarios_roles` (`usuario_id`, `role_id`) VALUES ('21', '1');
INSERT INTO `db_springboot_backend`.`usuarios_roles` (`usuario_id`, `role_id`) VALUES ('22', '1');
INSERT INTO `db_springboot_backend`.`usuarios_roles` (`usuario_id`, `role_id`) VALUES ('24', '1');
INSERT INTO `db_springboot_backend`.`usuarios_roles` (`usuario_id`, `role_id`) VALUES ('25', '1');
INSERT INTO `db_springboot_backend`.`usuarios_roles` (`usuario_id`, `role_id`) VALUES ('26', '1');
DELETE FROM `db_springboot_backend`.`usuarios_roles` WHERE (`usuario_id` = '14') and (`role_id` = '1');
INSERT INTO `db_springboot_backend`.`usuarios_roles` (`usuario_id`, `role_id`) VALUES ('27', '1');
UPDATE `db_springboot_backend`.`usuarios_roles` SET `usuario_id` = '13' WHERE (`usuario_id` = '2') and (`role_id` = '2');
UPDATE `db_springboot_backend`.`usuarios_roles` SET `usuario_id` = '15' WHERE (`usuario_id` = '3') and (`role_id` = '2');
UPDATE `db_springboot_backend`.`usuarios_roles` SET `usuario_id` = '16' WHERE (`usuario_id` = '4') and (`role_id` = '2');
DELETE FROM `db_springboot_backend`.`usuarios_roles` WHERE (`usuario_id` = '7') and (`role_id` = '2');
UPDATE `db_springboot_backend`.`usuarios_roles` SET `usuario_id` = '21' WHERE (`usuario_id` = '5') and (`role_id` = '2');
UPDATE `db_springboot_backend`.`usuarios_roles` SET `usuario_id` = '13' WHERE (`usuario_id` = '3') and (`role_id` = '3');
UPDATE `db_springboot_backend`.`usuarios_roles` SET `usuario_id` = '15' WHERE (`usuario_id` = '4') and (`role_id` = '3');
UPDATE `db_springboot_backend`.`usuarios_roles` SET `usuario_id` = '16' WHERE (`usuario_id` = '5') and (`role_id` = '3');
UPDATE `db_springboot_backend`.`usuarios_roles` SET `usuario_id` = '21' WHERE (`usuario_id` = '6') and (`role_id` = '3');
UPDATE `db_springboot_backend`.`usuarios_roles` SET `usuario_id` = '26' WHERE (`usuario_id` = '7') and (`role_id` = '3');
UPDATE `db_springboot_backend`.`usuarios_roles` SET `usuario_id` = '22' WHERE (`usuario_id` = '9') and (`role_id` = '3');
SET FOREIGN_KEY_CHECKS = 1;



select distinct(responsable_id) from facturas;

update facturas set responsable_id = 21 where responsable_id = 7; 
update facturas set responsable_id = 13 where responsable_id = 4;
update facturas set responsable_id = 17 where responsable_id = 6;
update facturas set responsable_id = 22 where responsable_id = 9;
update facturas set responsable_id = 20 where responsable_id = 8;
update facturas set responsable_id = 15 where responsable_id = 5;
update facturas set responsable_id = 16 where responsable_id = 3;
update facturas set responsable_id = 24 where responsable_id = 12;


select distinct(usu_cred_id) from facturas;
update facturas set usu_cred_id = 21 where usu_cred_id = 7; 
update facturas set usu_cred_id = 13 where usu_cred_id = 4;
update facturas set usu_cred_id = 17 where usu_cred_id = 6;
update facturas set usu_cred_id = 22 where usu_cred_id = 9;
update facturas set usu_cred_id = 20 where usu_cred_id = 8;
update facturas set usu_cred_id = 15 where usu_cred_id = 5;
update facturas set usu_cred_id = 16 where usu_cred_id = 3;
update facturas set usu_cred_id = 24 where usu_cred_id = 12;
update facturas set usu_cred_id = 25 where usu_cred_id = 14;


select distinct(usu_aprob_id) from facturas;
update facturas set usu_aprob_id = 16 where usu_aprob_id = 3;
update facturas set usu_aprob_id = 15 where usu_aprob_id = 5;
update facturas set usu_aprob_id = 13 where usu_aprob_id = 4;
update facturas set usu_aprob_id = 21 where usu_aprob_id = 7;


ALTER TABLE `db_springboot_backend`.`usuarios` 
ADD COLUMN `user` VARCHAR(45) NULL AFTER `id`,
ADD COLUMN `create_at` TIMESTAMP NULL AFTER `user`,
ADD COLUMN `update_at` TIMESTAMP NULL AFTER `create_at`;



#Modificacion User_update (Listo en prod)
ALTER TABLE `db_springboot_backend`.`cajas` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`cajachica` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`comisiones` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`facturas` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`facturas_items` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`gastos` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`inventarios` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`inventarios_items` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`logs` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`perdidas` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`product_group` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`productos` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`promotions` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`promotions_products` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `amount`;

ALTER TABLE `db_springboot_backend`.`proveedores` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`saldos` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`tipo_producto` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;


ALTER TABLE `db_springboot_backend`.`difference_box` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;


ALTER TABLE `db_springboot_backend`.`account` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;


# Requerimiento Retiro de socios  
ALTER TABLE `db_springboot_backend`.`retiroscaja` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`usuarios` 
ADD COLUMN `user_update` VARCHAR(45) NULL AFTER `create_at`;

ALTER TABLE `db_springboot_backend`.`gastos` 
ADD COLUMN `is_withdrawal` TINYINT NULL AFTER `proveedor_id`;

ALTER TABLE `db_springboot_backend`.`gastos` 
DROP COLUMN `Usuario`;


#Se solventa incidencia con pago de producto a consignacion 
ALTER TABLE `db_springboot_backend`.`gastos` 
DROP COLUMN `Usuario`;



# transferencias
CREATE TABLE `transfer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `user_update` varchar(45) DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  `deleted` TINYINT DEFAULT '0',
  `amount` double DEFAULT '0',
  `nameTo` varchar(45) DEFAULT NULL,
  `nameFrom` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

ALTER TABLE `db_springboot_backend`.`transfer` 
ADD COLUMN `is_dollar` TINYINT NULL DEFAULT '0' AFTER `description`;

ALTER TABLE `db_springboot_backend`.`transfer` 
ADD COLUMN `dollar_rate` DOUBLE NULL DEFAULT '0' AFTER `is_dollar`;

ALTER TABLE `db_springboot_backend`.`transfer` 
CHANGE COLUMN `nameTo` `name_to` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `nameFrom` `name_from` VARCHAR(45) NULL DEFAULT NULL ;


linode2 --> root    --> Danger16225262
            ip      -->139.144.56.222

            

#Dockerizacion
 CREATE USER 'alfonso'@'%' IDENTIFIED BY 'danger120-';
GRANT ALL PRIVILEGES ON db_springboot_backend.* TO 'alfonso'@'%';
FLUSH PRIVILEGES;

SELECT User, Host FROM mysql.user;
SHOW GRANTS FOR 'alfonso'@'%';
use db_springboot_backend;
select * from productos where codigo = 7790310984253;



# Subir aplicacion angular a servidor nginx
  Refrencia despliegue de app angular en nginx 
    --> https://codingpotions.com/clouding-io-despliegue-angular/
  * Subir contenido de app angular a  --> /var/www/html/kiosco

  Despliegue del back 
  * Se crea imagen de docker
    docker build -t kiosco . -f .\KioscoAppSpring\Dockerfile
  * Se crea el tag de la imagen
    docker tag kiosco dirielan3/kiosco_app_spring
  * Se realiza el push de la imagen
    docker push dirielan3/kiosco_app_spring
  * Se ejecuta docker-compose en maquina remota
     sudo docker-compose up --build -d

http://139.144.56.222/KioscoAppSpring/oauth/token
http://66.228.61.76/KioscoAppSpring/oauth/token
# Requerimeinto mas de una caja abierta 
ALTER TABLE `db_springboot_backend`.`cajas` 
DROP FOREIGN KEY `FK_caja_cliente`;
ALTER TABLE `db_springboot_backend`.`cajas` 
DROP INDEX `FK_caja_cliente` ,
ADD INDEX `FK_caja_cliente_idx` (`cliente_id` ASC);
;
ALTER TABLE `db_springboot_backend`.`cajas` 
ADD CONSTRAINT `FK_caja_cliente`
  FOREIGN KEY (`cliente_id`)
  REFERENCES `db_springboot_backend`.`usuarios` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;



ALTER TABLE `db_springboot_backend`.`facturas` 
DROP FOREIGN KEY `FK_factura_cliente`;
ALTER TABLE `db_springboot_backend`.`facturas` 
;
ALTER TABLE `db_springboot_backend`.`facturas` RENAME INDEX `FK_factura_cliente` TO `FK_factura_cliente_idx`;
ALTER TABLE `db_springboot_backend`.`facturas` ALTER INDEX `FK_factura_cliente_idx` VISIBLE;
ALTER TABLE `db_springboot_backend`.`facturas` 
ADD CONSTRAINT `FK_factura_cliente`
  FOREIGN KEY (`cliente_id`)
  REFERENCES `db_springboot_backend`.`usuarios` (`id`);


ALTER TABLE `db_springboot_backend`.`retiroscaja` 
DROP FOREIGN KEY `FK_retiro_cliente`;
ALTER TABLE `db_springboot_backend`.`retiroscaja` 
;
ALTER TABLE `db_springboot_backend`.`retiroscaja` RENAME INDEX `FK_retiro_cliente` TO `FK_retiro_cliente_idx`;
ALTER TABLE `db_springboot_backend`.`retiroscaja` ALTER INDEX `FK_retiro_cliente_idx` VISIBLE;
ALTER TABLE `db_springboot_backend`.`retiroscaja` 
ADD CONSTRAINT `FK_retiro_cliente`
  FOREIGN KEY (`cliente_id`)
  REFERENCES `db_springboot_backend`.`usuarios` (`id`);


# Sesarrollo del beneficio
ALTER TABLE `db_springboot_backend`.`usuarios` 
ADD COLUMN `benefit` DOUBLE NULL DEFAULT 0 AFTER `username`;

CREATE TABLE `benefit` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `user_update` varchar(45) DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  `deleted` TINYINT DEFAULT '0',
  `amount` double DEFAULT '0',
  `user_id` varchar(45) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

ALTER TABLE `db_springboot_backend`.`benefit` 
CHANGE COLUMN `user_id` `user_id` BIGINT NULL DEFAULT NULL ;

ALTER TABLE `db_springboot_backend`.`benefit` 
ADD CONSTRAINT `FK_benefit_user`
  FOREIGN KEY (`user_id`)
  REFERENCES `db_springboot_backend`.`usuarios` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


# clean de base de datos
delete from cajachica where create_at is null;
delete from cajachica where create_at < '2023-01-01';

delete from itemsfactura_itemsinventario where itemfactura_id in (
select id from facturas_items where create_at is null);
select count(*) from itemsfactura_itemsinventario where itemfactura_id in(
select id from facturas_items where create_at < '2023-01-01'); #140545
delete from itemsfactura_itemsinventario where itemfactura_id in(
select id from facturas_items where create_at < '2023-01-01'); #140545

select count(*) from facturas_items where create_at is null; #15599
delete from facturas_items where create_at is null; #15599
select count(*) from facturas_items where create_at < '2023-01-01';  #139402
select * from facturas_items where create_at < '2023-01-01';
delete from facturas_items where create_at < '2023-01-01';

select count(*) from facturas where create_at is null; # 2
delete from facturas where create_at is null; # 2

delete from cajachica where factura_id in(
select id from facturas where create_at < '2023-01-01'); #72

select count(*) from facturas where create_at < '2023-01-01';  #89946
select * from facturas where create_at < '2023-01-01';
delete from facturas where create_at < '2023-01-01';

select count(*) from cajas where create_at is null; #271
delete from cajas where create_at is null;
select count(*) from cajas where create_at < '2023-01-01'; #1270
delete from cajas where create_at < '2023-01-01'; #1270

select count(*) from gastos where create_at is null; # 579
delete from gastos where create_at is null; # 2
select count(*) from gastos where create_at < '2023-01-01';  #3202
delete from gastos where create_at < '2023-01-01';


select count(*) from itemsperdida_itemsinventario where iteminventario_id in (
select id from inventarios_items where create_at is null); #186
delete from itemsperdida_itemsinventario where iteminventario_id in (
select id from inventarios_items where create_at is null); #186


select count(*) from itemsperdida_itemsinventario where iteminventario_id in (
select id from inventarios_items where create_at < '2023-01-01'); #1576
delete from itemsperdida_itemsinventario where iteminventario_id in (
select id from inventarios_items where create_at < '2023-01-01'); #1576


select count(*) from perdidas where create_at < '2023-01-01'; #1456
select * from perdidas where create_at < '2023-01-01';
delete from perdidas where create_at < '2023-01-01';

select * from inventarios_items where create_at is null;
select count(*) from inventarios_items where create_at is null; # 1439
delete from inventarios_items where create_at is null; 


select count(*) from itemsfactura_itemsinventario where itemfactura_id in (
select id from facturas_items where iteminventario_id in (
select id from inventarios_items where create_at < '2023-01-01')); #838

delete from itemsfactura_itemsinventario where itemfactura_id in (
select id from facturas_items where iteminventario_id in (
select id from inventarios_items where create_at < '2023-01-01')); #838

select count(*) from facturas_items where iteminventario_id in (
select id from inventarios_items where create_at < '2023-01-01'); #773

delete from facturas_items where iteminventario_id in (
select id from inventarios_items where create_at < '2023-01-01'); #773


select count(*) from itemsperdida_itemsinventario where itemperdida_id in ( 
select id from perdidas where iteminventario_id in (
select id from inventarios_items where create_at < '2023-01-01')); #8

delete from itemsperdida_itemsinventario where itemperdida_id in ( 
select id from perdidas where iteminventario_id in (
select id from inventarios_items where create_at < '2023-01-01')); #8


select count(*) from perdidas where iteminventario_id in (
select id from inventarios_items where create_at < '2023-01-01'); #104

delete from perdidas where iteminventario_id in (
select id from inventarios_items where create_at < '2023-01-01'); #104


select count(*) from itemsfactura_itemsinventario where iteminventario_id in (
select id from inventarios_items where create_at < '2023-01-01');#8746

delete from itemsfactura_itemsinventario where iteminventario_id in (
select id from inventarios_items where create_at < '2023-01-01');#8746



select count(*) from inventarios_items where create_at < '2023-01-01'; #11633 
select * from inventarios_items where create_at < '2023-01-01';
delete from inventarios_items where create_at < '2023-01-01';


# requirement create spent coordinator
ALTER TABLE `db_springboot_backend`.`gastos` 
ADD COLUMN `is_approved` TINYINT(1) NULL DEFAULT 1 AFTER `is_withdrawal`;

# requerment extra hours  
CREATE TABLE `extra_hour` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `user_update` varchar(45) DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  `deleted` TINYINT DEFAULT '0',
  `justify` varchar(100) DEFAULT NULL,
  `amount` bigint DEFAULT '0',
  `date_from` timestamp NULL DEFAULT NULL,
  `date_to` timestamp NULL DEFAULT NULL,
  `time_start` timestamp NULL DEFAULT NULL,
  `time_end` timestamp NULL DEFAULT NULL,
  `is_approved` TINYINT DEFAULT '0',
  `user_responsible_id` bigint NOT NULL,
  `user_owner_id` bigint NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

ALTER TABLE `db_springboot_backend`.`extra_hour` 
ADD INDEX `FK_extrahour_responsible_idx` (`user_responsible_id` ASC) VISIBLE;
;
ALTER TABLE `db_springboot_backend`.`extra_hour` 
ADD CONSTRAINT `FK_extrahour_responsible`
  FOREIGN KEY (`user_responsible_id`)
  REFERENCES `db_springboot_backend`.`usuarios` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `db_springboot_backend`.`extra_hour` 
ADD INDEX `FK_extrahour_owner_idx` (`user_owner_id` ASC) VISIBLE;
;
ALTER TABLE `db_springboot_backend`.`extra_hour` 
ADD CONSTRAINT `FK_extrahour_owner`
  FOREIGN KEY (`user_owner_id`)
  REFERENCES `db_springboot_backend`.`usuarios` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `db_springboot_backend`.`extra_hour` 
DROP COLUMN `time_end`,
DROP COLUMN `time_start`;

ALTER TABLE `db_springboot_backend`.`extra_hour` 
CHANGE COLUMN `is_approved` `state` VARCHAR(45) NOT NULL ;


# se agrega saldo de mercancia en la generacion de saldos
ALTER TABLE `db_springboot_backend`.`saldos` 
ADD COLUMN `mercancia` DOUBLE NULL DEFAULT 0 AFTER `is_transferencia`;

ALTER TABLE `db_springboot_backend`.`saldos` 
CHANGE COLUMN `mercancia` `mercancia` DOUBLE NULL DEFAULT '0' AFTER `patrimonio_pesos`;

# Requerimeinto de modulo salary
ALTER TABLE `db_springboot_backend`.`usuarios` 
ADD COLUMN `salary` DOUBLE NOT NULL DEFAULT 0 AFTER `benefit`;

# modulo de aumento de sueldo
CREATE TABLE `increase_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `user_update` varchar(45) DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  `deleted` TINYINT DEFAULT '0',
  `amount` DOUBLE DEFAULT '0',
  `type` varchar(45) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

ALTER TABLE `db_springboot_backend`.`increase_user` 
ADD COLUMN `user_id` BIGINT NOT NULL AFTER `type`;

ALTER TABLE `db_springboot_backend`.`increase_user` 
ADD COLUMN `salary_old` DOUBLE NOT NULL AFTER `user_id`,
ADD COLUMN `salary_new` DOUBLE NOT NULL AFTER `salary_old`;


CREATE TABLE `pay` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `user_update` varchar(45) DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  `deleted` TINYINT DEFAULT '0',
  `user_id` bigint NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `salary_month` DOUBLE DEFAULT '0',
  `salary_pay` DOUBLE DEFAULT '0',
  `discount` DOUBLE DEFAULT '0',
  `extra_hours` DOUBLE DEFAULT '0',
  `total_pay` DOUBLE DEFAULT '0',
  `start` timestamp NULL DEFAULT NULL,
  `end` timestamp NULL DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


ALTER TABLE `db_springboot_backend`.`pay` 
ADD INDEX `FK_pay_user_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `db_springboot_backend`.`pay` 
ADD CONSTRAINT `FK_pay_user`
  FOREIGN KEY (`user_id`)
  REFERENCES `db_springboot_backend`.`usuarios` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `db_springboot_backend`.`increase_user` 
ADD INDEX `FK_increase_user_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `db_springboot_backend`.`increase_user` 
ADD CONSTRAINT `FK_increase_user`
  FOREIGN KEY (`user_id`)
  REFERENCES `db_springboot_backend`.`usuarios` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `db_springboot_backend`.`cajachica` 
ADD COLUMN `pay_id` BIGINT NULL AFTER `tasa`;

ALTER TABLE `db_springboot_backend`.`cajachica` 
ADD INDEX `FKCaja_pay_idx` (`pay_id` ASC) VISIBLE;
;
ALTER TABLE `db_springboot_backend`.`cajachica` 
ADD CONSTRAINT `FKCaja_pay`
  FOREIGN KEY (`pay_id`)
  REFERENCES `db_springboot_backend`.`pay` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  ALTER TABLE `db_springboot_backend`.`pay` 
CHANGE COLUMN `amount_holidays` `amount_holidays` INT NULL DEFAULT '0' AFTER `extra_hours`,
CHANGE COLUMN `salary_holidays` `salary_holidays` DOUBLE NULL DEFAULT '0' AFTER `amount_holidays`;



  ALTER TABLE `db_springboot_backend`.`gastos` 
ADD COLUMN `spent_id` BIGINT NULL DEFAULT NULL AFTER `is_approved`,
ADD INDEX `FK_spent_pay_idx` (`spent_id` ASC) VISIBLE;
;
ALTER TABLE `db_springboot_backend`.`gastos` 
ADD CONSTRAINT `FK_spent_pay`
  FOREIGN KEY (`spent_id`)
  REFERENCES `db_springboot_backend`.`pay` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


# Se agregan dias feriados al pago
ALTER TABLE `db_springboot_backend`.`pay` 
ADD COLUMN `amount_holidays` INT NULL DEFAULT 0 AFTER `end`;


ALTER TABLE `db_springboot_backend`.`pay` 
ADD COLUMN `salary_holidays` DOUBLE NULL DEFAULT 0 AFTER `amount_holidays`;


