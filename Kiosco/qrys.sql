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