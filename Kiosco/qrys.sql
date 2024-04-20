/*Ganancias de turno nocturno*/
SELECT SUM(((fi.precio-ii.preciocompra)*fi.cantidad)+fi.comision) AS ganancia, DAYOFMONTH(f.create_at) 
FROM facturas_items fi 
INNER JOIN facturas f ON f.id = fi.factura_id 
INNER JOIN itemsfactura_itemsinventario piv ON fi.id = piv.itemfactura_id 
INNER JOIN inventarios_items ii ON ii.id = piv.iteminventario_id 
WHERE f.create_at BETWEEN '2022-05-01' AND '2022-05-31' 
and ((HOUR(f.create_at) between 0 and 6) or (HOUR(f.create_at) between 23 and 24)) 
GROUP BY DAY(f.create_at);

/*facturas*/

select * from facturas f where is_credito = 1 and mercadopago > 0 order by create_at desc;

select f.descripcion, f.is_credito,f.is_aprobada, f.fecha_aprob, f.mercadopago, f.total,
(select usu.nombre from usuarios usu where usu.id = f.usu_cred_id) as responsable 
from facturas f where is_credito = 1 and mercadopago > 0 order by create_at desc;

select * from facturas where is_credito = 1 and fecha_aprob is not null order by create_at desc;

select f.descripcion, f.is_credito,f.is_aprobada, f.fecha_aprob,
(select usu.nombre from usuarios usu where usu.id = f.usu_cred_id) as responsable 
from facturas f where is_credito = 1 and fecha_aprob is not null order by create_at desc;

select cc.id as id_caja, cc.user as id_user_caja, cc.create_at as fecha_caja, cc.monto, cc.factura_id, f.descripcion, f.is_credito,f.is_aprobada, f.fecha_aprob,
(select usu.nombre from usuarios usu where usu.id = f.usu_cred_id) as responsable
from cajachica cc inner join facturas f on cc.factura_id = f.id
where f.is_credito = 1 and f.fecha_aprob is not null order by cc.id desc;

select * from cajachica order by id desc;



/**Qrys de facturas a credito*/
select * from  facturas where is_credito and fecha_aprob is null and usu_cred_id = 3;
select f.id as invoice, 
	f.create_at as date, 
    u.username as user, 
    f.total as total,
    p.id as idProduct,
    p.nombre as product, 
    p.preciocosto as priceCost, 
    fi.cantidad as count,
    fi.total as PriceItemInvoice,
    fi.precio as priceInvoice, 
    p.precio as ActualPrice
from facturas f 
	inner join usuarios u on f.usu_cred_id = u.id
	inner join facturas_items fi on f.id = fi.factura_id 
    inner join productos p on fi.producto_id = p.id
where f.is_credito 
	and fecha_aprob is null 
    and usu_cred_id = 3
    and p.id not in (1011,1790,184,560,225,832,375,356,523,793,1788,289,1855,817,1424,1862,1500,909,1806,164,156);
    
select sum(fi.total)    
from facturas f 
	inner join usuarios u on f.usu_cred_id = u.id
	inner join facturas_items fi on f.id = fi.factura_id 
    inner join productos p on fi.producto_id = p.id
where f.is_credito 
	and fecha_aprob is null 
    and usu_cred_id = 3
    and p.id not in (1011,1790,184,560,225,832,375,356,523,793,1788,289,1855,817,1424,1862,1500,909,1806,164,156);
    
select sum(fi.total)    
from facturas f 
	inner join usuarios u on f.usu_cred_id = u.id
	inner join facturas_items fi on f.id = fi.factura_id 
    inner join productos p on fi.producto_id = p.id
where f.is_credito 
	and fecha_aprob is null 
    and usu_cred_id = 3
    and p.id in (1011,1790,184,560,225,832,375,356,523,793,1788,289,1855,817,1424,1862,1500,909,1806,164,156);


/**qry de facturas al costo*/
select * from  
    (select sum(f.total), u.username  
    from facturas f  
        inner join usuarios u on u.id =  f.responsable_id  
    WHERE MONTH(f.create_at) = 5  
        AND YEAR(f.create_at) = 2023  
        and costo = 1  
    group by f.responsable_id) f1  
union all  
    select sum(ft.total), null  
    from facturas ft 
    WHERE MONTH(ft.create_at) = 5  
        AND YEAR(ft.create_at) = 2023  
        and ft.costo = 1   
        and ft.responsable_id is not null;


select * from  
 (select sum(f.total), u.username  
 from facturas f  
 inner join usuarios u on u.id =  f.responsable_id  
 WHERE MONTH(f.create_at) = ?1  
 AND YEAR(f.create_at) = ?2  
 and costo = 1  
 group by f.responsable_id) f1  
 union all  
 select sum(ft.total), null  
 from facturas ft 
 WHERE MONTH(ft.create_at) = ?1  
 AND YEAR(ft.create_at) = ?2  
 and ft.costo = 1   
 and ft.responsable_id is not null 


/*Identificar  facturas duplicadas */
SELECT *
FROM cajachica
WHERE factura_id IN (
    SELECT factura_id
    FROM cajachica
    GROUP BY factura_id
    HAVING COUNT(*) > 1
);

select f.id,
        pedidosya, 
        pedidosyaefectivo, 
        puntoventa, 
        mercadopago, 
        (total - mercadopago - puntoventa)  as efectivo,
        total, 
        is_credito, 
        (SELECT COUNT(*) AS cantidad_repeticiones
            FROM cajachica
            GROUP BY factura_id
            HAVING COUNT(*) > 1
            and factura_id = f.id) as cant
from facturas f
where f.id in (SELECT factura_id
                FROM cajachica
                GROUP BY factura_id
                HAVING COUNT(*) > 1)
order by id desc;

SELECT  id, REPLACE(ROUND(puntoventa, 2),'.',',') as pv, REPLACE(ROUND(puntoventa * (cantidad -1), 2),'.',',') as pvcant, 
        REPLACE(ROUND(mercadopago, 2),'.',',') as mp, REPLACE(ROUND(mercadopago * (cantidad -1), 2),'.',',') as mpcant,
        REPLACE(ROUND((total - mercadopago - puntoventa), 2),'.',',') as efect, REPLACE(ROUND((total - mercadopago - puntoventa) * (cantidad -1), 2),'.',',') as efectcant,
        cantidad - 1 as cantidad
FROM (
    select f.id as id,
        puntoventa, 
        mercadopago, 
        (total - mercadopago - puntoventa)  as efectivo,
        total,
        (SELECT COUNT(*) 
            FROM cajachica
            GROUP BY factura_id
            HAVING COUNT(*) > 1
            and factura_id = f.id) AS cantidad
    from facturas f
    where f.id in (SELECT factura_id
                    FROM cajachica
                    GROUP BY factura_id
                    HAVING COUNT(*) > 1)
    order by id desc
) subquery
GROUP BY id;

# modificaciones al dar error en el cierre de una caja por factura duplicada 
UPDATE `db_springboot_backend`.`facturas` SET `cliente_id` = NULL WHERE (`id` = '194935');
UPDATE `db_springboot_backend`.`facturas` SET `caja_id` = '2852' WHERE (`id` = '194935');

# Duplicadas para excel

SELECT id as id,
        user as Usuario, 
        create_at as fecha,
        REPLACE(ROUND(saldomp, 2),'.',',') as MP, 
        REPLACE(ROUND(saldopy, 2),'.',',') as PY,
        REPLACE(ROUND(saldoefectivopy, 2),'.',',') as PYE, 
        REPLACE(ROUND(saldopv, 2),'.',',') as PV, 
        REPLACE(ROUND(saldoefectivo, 2),'.',',') as Efectivo,
        REPLACE(ROUND(saldodollar, 2),'.',',') as Dollar,
        factura_id,
        gasto_id,
        caja_id,
        transferencia,
        isdollar,
        tasa
FROM cajachica
WHERE factura_id IN (
    SELECT factura_id
    FROM cajachica
    GROUP BY factura_id
    HAVING COUNT(*) > 1
);



# Aumento de productos
update productos set precio = ROUND(precio*1.15, -1) where deleted <> 1;
update productos set precio = CEIL(precio * 1.15 / 100) * 100 where deleted <> 1;



# cantidad facturada en una caja por cuenta
select * from facturas order by id desc;
select sum(total), sum(mercadopago), sum(puntoventa), sum(total) - sum(mercadopago) - sum(puntoventa) 
from facturas 
where cliente_id = 26
and deleted is false;

select sum(total), sum(mercadopago), sum(puntoventa), sum(total) - sum(mercadopago) - sum(puntoventa) 
from facturas 
where id in ( select factura_id from cajachica where id <= 169598 and id >169461)



# se busca producto por parte del nombre
select * from productos where nombre  like ('%Red Point Box%');



# Se busca en logs
SELECT * FROM db_springboot_backend.logs where description_old like ('%lays 94 gr%') order by id desc;

SELECT user, create_at, description_old, description_new
FROM db_springboot_backend.logs 
where entity = 'Producto'
and metodo = 'updateProducto'
and user = 'fmendez'
order by id desc;



# Se busca en inventario con join en productos
select p.nombre, ii.* 
from inventarios_items ii 
inner join productos p on ii.producto_id = p.id 
where inventario_id = 3666;



# Se modifican existencia de productos a la existencia de los inventarios


update productos p set p.existencia = (select sum(ii.existencia) 
                                            from inventarios_items ii 
                                            where ii.producto_id = p.id and ii.estado = 'Activo') 
where p.existencia <> (select sum(ii.existencia) 
                        from inventarios_items ii 
                        where ii.producto_id = p.id 
                            and ii.estado = 'Activo');


select p.nombre, p.existencia, (select sum(ii.existencia) 
                                from inventarios_items ii 
                                where ii.producto_id = p.id 
                                    and ii.estado = 'Activo') 
from productos p 
where p.existencia <> (select sum(ii.existencia) 
                        from inventarios_items ii 
                        where ii.producto_id = p.id 
                            and ii.estado = 'Activo');



# selecciona nombre precio inventario y producto en consignacio
select p.nombre as producto, 
sum(fi.cantidad) as cantiad,
(select preciocompra 
from inventarios_items 
where producto_id = p.id order by id desc limit 1) as precio, 
(select inventario_id 
from inventarios_items 
where producto_id = p.id limit 1) as inventario,
p.id as idProducto 
from facturas_items fi 
inner join productos p 
on fi.producto_id = p.id 
where fi.consignacion = 1 
group by fi.producto_id;


# saldos se formatea
select id, user, create_at, REPLACE(saldomp, '.', ',') as saldomp, REPLACE(saldopy, '.', ',') as saldopy, REPLACE(saldoefectivopy, '.', ',') as efectivopy, REPLACE(saldopv, '.', ',') as saldopv, 
REPLACE(saldoefectivo, '.', ',') as saldoefectivo, REPLACE(saldodollar, '.', ',') as saldodollar, REPLACE(monto, '.', ',') as monto, factura_id, gasto_id, caja_id, transferencia 
from cajachica 
where id > 169315;


# Validar facturas
select f.id, f.user, f.create_at, f.deleted, f.descripcion, f.total, f.costo, f.is_credito, f.is_aprobada, fi.id, fi.cantidad, fi.precio, fi.total, fi.factura_id, p.nombre
from facturas f
inner join facturas_items fi on f.id = fi.factura_id
inner join productos p on p.id = fi.producto_id
where f.id > 163250 and f.id < 163324 order by f.id desc;

select f.id, f.user, f.create_at, f.deleted, f.descripcion, f.total, f.mercadopago, f.costo, f.is_credito, f.is_aprobada, fi.id, fi.cantidad, fi.precio, fi.total, fi.factura_id, p.nombre
from facturas f
inner join facturas_items fi on f.id = fi.factura_id
inner join productos p on p.id = fi.producto_id
where  f.create_at > '2024-01-23 17:00:00'
and f.create_at < '2024-01-24 07:50:00'
order by f.id desc;

# buscar facturas_items con el nombre de los productos
select * from facturas_items fi inner join productos p on p.id = fi.producto_id where fi.factura_id in (161156, 161167, 161169);




# existencia de productos diferentes a existencia en inventarios items

update productos p set p.existencia = (select sum(existencia) from inventarios_items ii where ii.producto_id = p.id and ii.estado = 'Activo')
where p.existencia <> (select sum(existencia) 
                        from inventarios_items ii 
                        where ii.producto_id = p.id 
                        and ii.estado = 'Activo');
                        
                        select p.id,p.codigo, p.nombre, p.existencia, (select sum(existencia) from inventarios_items ii where ii.producto_id = p.id and ii.estado = 'Activo') ep
from productos p 
where deleted <> 1
and p.existencia <> (select sum(existencia) 
                        from inventarios_items ii 
                        where ii.producto_id = p.id 
                        and ii.estado = 'Activo');

# Consulta de facturas por fecha y hora 
select sum(total) from facturas
where create_at > '2023-11-12 07:00:00'
and create_at < '2023-11-12 15:00:00';

select * from facturas
where create_at > '2023-11-12 07:00:00'
and create_at < '2023-11-12 15:00:00';


# Diferencias por mes 
SELECT sum(c.diferencia), MONTH(c.create_at) 
FROM cajas c 
WHERE YEAR(c.create_at) = '2023' 
GROUP BY MONTH(c.create_at); 
#ORDER BY MONTH(c.create_at) ASC;



# Cafe 
use db_springboot_backend;

select * from facturas_items where producto_id in (
SELECT id FROM db_springboot_backend.productos where is_inventory is false)
and create_at > '2024-01-04 11:00:00'
and create_at < '2024-01-08 12:00:00';

select '04-01 / 08-01', sum(total) from facturas_items where producto_id in (
SELECT id FROM db_springboot_backend.productos where is_inventory is false)
and create_at > '2024-01-04 11:00:00'
and create_at < '2024-01-08 12:00:00'
union all 
select '08-01 / 12-01',sum(total) from facturas_items where producto_id in (
SELECT id FROM db_springboot_backend.productos where is_inventory is false)
and create_at > '2024-01-08 12:00:00'
and create_at < '2024-01-12 09:00:00'
union all 
select '12-01 / 16-01',sum(total) from facturas_items where producto_id in (
SELECT id FROM db_springboot_backend.productos where is_inventory is false)
and create_at > '2024-01-12 09:00:00'
and create_at < '2024-01-16 10:00:00';

select fi.producto_id, p.nombre, sum(fi.cantidad), sum(fi.total) 
from facturas_items fi inner join productos p on p.id = fi.producto_id
where producto_id in (
SELECT id FROM db_springboot_backend.productos pro where pro.is_inventory is false)
and fi.create_at > '2024-01-12 09:00:00'
and fi.create_at < '2024-01-16 10:00:00'
group by fi.producto_id;


# consultas de ventas del cafe por fecha
select p.id, p.nombre , sum(cantidad)  
from facturas_items fi 
inner join productos p  on p.id =fi.producto_id
where tipo_producto_id in (495) 
and fi.create_at > '2024-02-10 23:00:00'
and fi.create_at < '2024-02-12 07:00:00'
group by p.id, p.nombre order by id desc;

# ganancias por dia para el cafe 
select DATE(f.create_at) as fecha, sum(fi.total) as total , sum((p.preciocosto*cantidad)) as preciocosto, (sum(fi.total) -sum((p.preciocosto*cantidad))) as ganancia
from facturas f
inner join facturas_items fi on f.id = fi.factura_id
inner join productos p on p.id = fi.producto_id
where p.tipo_producto_id = 495
    AND MONTH(f.create_at) = 1
    AND YEAR(f.create_at) = 2024
GROUP BY 
    DATE(f.create_at)
order by DATE(f.create_at);

# Promedio de venta y ganancias por mes
select 'Enero',AVG(total) as ventas, AVG(ganancia) as ganancia from (select  sum(fi.total) as total, (sum(fi.total) -sum((p.preciocosto*cantidad))) as ganancia
from facturas f
inner join facturas_items fi on f.id = fi.factura_id
inner join productos p on p.id = fi.producto_id
where p.tipo_producto_id = 495
    AND MONTH(f.create_at) = 1
    AND YEAR(f.create_at) = 2024
GROUP BY 
    DATE(f.create_at)
order by DATE(f.create_at)) as subconsulta1
UNION ALL
select 'Febrero',AVG(total) as ventas, AVG(ganancia) as ganancia from (select  sum(fi.total) as total, (sum(fi.total) -sum((p.preciocosto*cantidad))) as ganancia
from facturas f
inner join facturas_items fi on f.id = fi.factura_id
inner join productos p on p.id = fi.producto_id
where p.tipo_producto_id = 495
    AND MONTH(f.create_at) = 2
    AND YEAR(f.create_at) = 2024
GROUP BY 
    DATE(f.create_at)
order by DATE(f.create_at)) as subconsulta2;

# Venta por dia
select sum(fi.total)
from facturas f
inner join facturas_items fi on f.id = fi.factura_id
inner join productos p on p.id = fi.producto_id
where producto_id in (SELECT p.id FROM db_springboot_backend.productos p
                        where p.tipo_producto_id = 495
                        order by p.id desc)
and DATE(f.create_at) = '2024-01-29';

# Detalle de facturado del cafe por dia
select f.id, f.user, f.create_at, f.deleted, f.descripcion, f.total, f.mercadopago, f.costo, f.is_credito, f.is_aprobada, fi.id as fi, fi.cantidad, fi.precio, fi.total, fi.factura_id, p.nombre, p.id, p.preciocosto
from facturas f
inner join facturas_items fi on f.id = fi.factura_id
inner join productos p on p.id = fi.producto_id
where producto_id in (SELECT p.id FROM db_springboot_backend.productos p
                        where p.tipo_producto_id = 495
                        order by p.id desc)
and DATE(f.create_at) = '2024-01-30';

/*Sin  facturas a credito */
select fi.producto_id, p.nombre, sum(fi.cantidad), sum(fi.total) 
from facturas_items fi 
inner join facturas f on f.id = fi.factura_id
inner join productos p on p.id = fi.producto_id
where producto_id in (
SELECT id FROM db_springboot_backend.productos pro where pro.is_inventory is false)
and fi.create_at > '2024-01-12 09:00:00'
and fi.create_at < '2024-01-16 10:00:00'
and f.costo is false
group by fi.producto_id;


# consulta de ventas comparacion entre meses y dias
SELECT 
    DAY(create_at) as dia,
    SUM(CASE WHEN MONTH(create_at) = 1 AND YEAR(create_at) = 2024 THEN venta ELSE 0 END) as ventas_enero_2024,
    SUM(CASE WHEN MONTH(create_at) = 12 AND YEAR(create_at) = 2023 THEN venta ELSE 0 END) as ventas_diciembre_2023
FROM 
    db_springboot_backend.cajas
WHERE 
    (MONTH(create_at) = 1 AND YEAR(create_at) = 2024 AND DAY(create_at) BETWEEN 15 AND 24) OR
    (MONTH(create_at) = 12 AND YEAR(create_at) = 2023 AND DAY(create_at) BETWEEN 15 AND 24)
GROUP BY 
    DAY(create_at);


# ver productos sin inventarios
SELECT *
FROM db_springboot_backend.productos p
WHERE NOT EXISTS (
    SELECT 1
    FROM inventarios_items ii
    WHERE ii.producto_id = p.id
    AND ii.create_at > DATE_SUB(NOW(), INTERVAL 6 MONTH)
)and deleted = 0
and existencia < 1;



# totales de ventas y de gastos desglosados por mes
SELECT COALESCE(ventas.mes, gastos.mes) AS mes,
       ventas.total AS total_ventas,
       gastos.total AS total_gastos,
       COALESCE(ventas.total, 0) - COALESCE(gastos.total, 0) AS diferencia
FROM (
    SELECT MONTH(f.create_at) AS mes, SUM(f.total) AS total
    FROM facturas f
    WHERE YEAR(f.create_at) = 2024
    GROUP BY mes
) AS ventas
LEFT JOIN (
    SELECT MONTH(g.create_at) AS mes, SUM(monto_pesos) AS total
    FROM gastos g
    WHERE YEAR(g.create_at) = 2024
    GROUP BY mes
) AS gastos ON ventas.mes = gastos.mes
UNION
SELECT COALESCE(ventas.mes, gastos.mes) AS mes,
       ventas.total AS total_ventas,
       gastos.total AS total_gastos,
       COALESCE(ventas.total, 0) - COALESCE(gastos.total, 0) AS diferencia
FROM (
    SELECT MONTH(f.create_at) AS mes, SUM(f.total) AS total
    FROM facturas f
    WHERE YEAR(f.create_at) = 2024
    GROUP BY mes
) AS ventas
RIGHT JOIN (
    SELECT MONTH(g.create_at) AS mes, SUM(monto_pesos) AS total
    FROM gastos g
    WHERE YEAR(g.create_at) = 2024
    GROUP BY mes
) AS gastos ON ventas.mes = gastos.mes;


# query de ventas y gastos solo del cafe
select 'ventas',MONTH(fi.create_at) as mes, sum(fi.total)
from facturas_items fi
inner join productos p on fi.producto_id = p.id
where p.tipo_producto_id = 495
and YEAR(fi.create_at) = 2024
group by MONTH(fi.create_at);

SELECT COALESCE(ventas.mes, gastos.mes) AS mes,
       ventas.total_ventas,
       gastos.total_gastos,
       COALESCE(ventas.total_ventas, 0) - COALESCE(gastos.total_gastos, 0) AS diferencia
FROM (
    SELECT MONTH(g.create_at) AS mes, SUM(monto_pesos) AS total_gastos
    FROM gastos g
    WHERE YEAR(g.create_at) = 2024
    AND descripcion LIKE '%cafe%'
    GROUP BY mes
) AS gastos
LEFT JOIN (
    SELECT MONTH(fi.create_at) AS mes, SUM(fi.total) AS total_ventas
    FROM facturas_items fi
    INNER JOIN productos p ON fi.producto_id = p.id
    WHERE p.tipo_producto_id = 495
    AND YEAR(fi.create_at) = 2024
    GROUP BY mes
) AS ventas ON gastos.mes = ventas.mes;



