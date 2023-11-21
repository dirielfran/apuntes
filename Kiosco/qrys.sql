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