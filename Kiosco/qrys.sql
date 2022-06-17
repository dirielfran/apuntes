/*Ganancias de turno nocturno*/
SELECT SUM(((fi.precio-ii.preciocompra)*fi.cantidad)+fi.comision) AS ganancia, DAYOFMONTH(f.create_at) 
FROM facturas_items fi 
INNER JOIN facturas f ON f.id = fi.factura_id 
INNER JOIN itemsfactura_itemsinventario piv ON fi.id = piv.itemfactura_id 
INNER JOIN inventarios_items ii ON ii.id = piv.iteminventario_id 
WHERE f.create_at BETWEEN '2022-05-01' AND '2022-05-31' 
and ((HOUR(f.create_at) between 0 and 6) or (HOUR(f.create_at) between 23 and 24)) 
GROUP BY DAY(f.create_at);