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








**********************************************************************************
Modificaciones duplicadas
**********************************************************************************
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147871');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147873');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147875');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147876');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147878');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147879');
UPDATE `db_springboot_backend`.`cajachica` SET `saldoefectivo` = '1444093.1409885762' WHERE (`id` = '147880');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147881');
UPDATE `db_springboot_backend`.`cajachica` SET `saldoefectivo` = '1444853.1409885762' WHERE (`id` = '147882');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147883');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147884');
UPDATE `db_springboot_backend`.`cajachica` SET `saldoefectivo` = '1449933.1409885762' WHERE (`id` = '147885');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147886');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147887');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147889');
UPDATE `db_springboot_backend`.`cajachica` SET `saldoefectivo` = '1445553.1409885762' WHERE (`id` = '147888');
UPDATE `db_springboot_backend`.`cajachica` SET `saldoefectivo` = '1446503.1409885762' WHERE (`id` = '147890');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147891');
UPDATE `db_springboot_backend`.`cajachica` SET `saldoefectivo` = '1446503.1409885762' WHERE (`id` = '147892');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147893');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147894');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '161121.59626710007', `saldoefectivo` = '1447393.1409885762' WHERE (`id` = '147895');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147896');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '161121.59626710007', `saldoefectivo` = '1449853.1409885762' WHERE (`id` = '147897');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147898');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147899');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '161121.59626710007', `saldoefectivo` = '1452163.1409885762' WHERE (`id` = '147900');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147901');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147902');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147903');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147904');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147905');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147906');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147907');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147908');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147909');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147910');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147911');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147912');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147913');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147914');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147915');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147916');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147917');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147918');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147919');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147920');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147921');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147922');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147923');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147924');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147925');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147926');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147927');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147928');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147929');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147930');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147931');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147932');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147933');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147934');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147935');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147936');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147937');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147938');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147939');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147941');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147942');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147943');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147944');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147945');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147946');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147947');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147948');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147949');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147950');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147951');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147952');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147953');


DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147940');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '161361.59626710007', `saldopv` = '216625.63999999998', `saldoefectivo` = '1452783.1409885762' WHERE (`id` = '147954');

update db_springboot_backend.cajachica set saldomp = saldomp-4200, saldopv = saldopv-630, saldoefectivo= saldoefectivo - 78180 where id >= 147955 and id <= 147998;



DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '147999');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1496843.5409885761' WHERE (`id` = '148000');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148001');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148002');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1497433.3409885762' WHERE (`id` = '148003');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148004');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1498053.3409885762' WHERE (`id` = '148005');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148006');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148007');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1501233.3409885762' WHERE (`id` = '148008');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148009');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1501833.3409885762' WHERE (`id` = '148010');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148011');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148012');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1503673.3409885762' WHERE (`id` = '148013');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148014');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148015');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1505123.3409885762' WHERE (`id` = '148016');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148017');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148018');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1505683.3409885762' WHERE (`id` = '148019');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148020');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1506433.3409885762' WHERE (`id` = '148021');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148022');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1506813.3409885762' WHERE (`id` = '148023');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148024');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148025');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1507463.3409885762' WHERE (`id` = '148026');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148027');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1508563.3409885762' WHERE (`id` = '148028');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148029');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148030');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1508933.3409885762' WHERE (`id` = '148031');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148032');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1509713.1409885762' WHERE (`id` = '148033');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148034');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1509913.1409885762' WHERE (`id` = '148035');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148036');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148037');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1513503.9409885763' WHERE (`id` = '148038');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148039');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1514103.9409885763' WHERE (`id` = '148040');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148041');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1514403.9409885763' WHERE (`id` = '148042');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148043');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148044');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1516343.9409885763' WHERE (`id` = '148045');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148046');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1516383.9409885763' WHERE (`id` = '148047');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148048');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1516603.9409885763' WHERE (`id` = '148049');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148050');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1517503.9409885763' WHERE (`id` = '148051');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148052');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148053');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1517903.9409885763' WHERE (`id` = '148054');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148055');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1519383.9409885763' WHERE (`id` = '148056');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148057');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1521923.9409885763' WHERE (`id` = '148058');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148059');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148060');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1522653.9409885763' WHERE (`id` = '148061');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148062');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1523193.9409885763' WHERE (`id` = '148063');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148064');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1523983.9409885763' WHERE (`id` = '148065');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148066');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1525183.9409885763' WHERE (`id` = '148067');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148068');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148069');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1526803.9409885763' WHERE (`id` = '148070');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148071');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1527243.9409885763' WHERE (`id` = '148072');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148073');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148074');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1528143.9409885763' WHERE (`id` = '148075');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148076');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1528563.9409885763' WHERE (`id` = '148077');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148078');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1529823.9409885763' WHERE (`id` = '148079');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148080');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1530273.9409885763' WHERE (`id` = '148081');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148082');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148083');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1532273.9409885763' WHERE (`id` = '148084');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148085');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1532633.9409885763' WHERE (`id` = '148086');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148087');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1536083.9409885763' WHERE (`id` = '148088');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148089');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148090');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1536513.9409885763' WHERE (`id` = '148091');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148092');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1536603.9409885763' WHERE (`id` = '148093');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148094');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1538003.9409885763' WHERE (`id` = '148095');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148096');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1538433.9409885763' WHERE (`id` = '148097');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148098');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1541283.7409885763' WHERE (`id` = '148099');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148100');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '217975.63999999998', `saldoefectivo` = '1541973.7409885763' WHERE (`id` = '148101');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148102');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148103');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '168351.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1541973.7409885763' WHERE (`id` = '148104');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148105');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170241.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1541973.7409885763' WHERE (`id` = '148106');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148107');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170241.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1542818.7409885763' WHERE (`id` = '148108');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148109');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170241.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1544367.1409885762' WHERE (`id` = '148110');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148111');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148112');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170241.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1544817.7409885763' WHERE (`id` = '148113');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148114');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170241.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1545537.7409885763' WHERE (`id` = '148115');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148116');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170241.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1547757.7409885763' WHERE (`id` = '148117');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148118');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170641.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1547757.7409885763' WHERE (`id` = '148119');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148120');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170641.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1548757.7409885763' WHERE (`id` = '148121');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148122');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148123');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170641.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1549440.9409885763' WHERE (`id` = '148124');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148125');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170641.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1550370.9409885763' WHERE (`id` = '148126');




DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148127');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170641.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1550870.9409885763' WHERE (`id` = '148128');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148129');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170641.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1551160.9409885763' WHERE (`id` = '148130');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148131');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148132');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170641.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1551660.9409885763' WHERE (`id` = '148133');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148134');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170641.59626710007', `saldopv` = '220255.63999999998', `saldoefectivo` = '1552970.9409885763' WHERE (`id` = '148135');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '170641.59626710007', `saldopv` = '222055.63999999998', `saldoefectivo` = '1552970.9409885763' WHERE (`id` = '148136');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148137');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148138');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '176291.59626710007', `saldopv` = '222055.63999999998', `saldoefectivo` = '1552970.9409885763' WHERE (`id` = '148139');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148140');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '176291.59626710007', `saldopv` = '222055.63999999998', `saldoefectivo` = '1553770.9409885763' WHERE (`id` = '148141');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148142');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '176291.59626710007', `saldopv` = '222055.63999999998', `saldoefectivo` = '1554230.9409885763' WHERE (`id` = '148143');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148144');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '176291.59626710007', `saldopv` = '224458.63999999998', `saldoefectivo` = '1554230.9409885763' WHERE (`id` = '148145');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148146');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '176291.59626710007', `saldopv` = '224458.63999999998', `saldoefectivo` = '1555090.9409885763' WHERE (`id` = '148147');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148148');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '176291.59626710007', `saldopv` = '224458.63999999998', `saldoefectivo` = '1556090.9409885763' WHERE (`id` = '148149');





DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148150');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148151');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148152');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148153');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148154');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148155');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148156');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148157');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148158');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148159');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148160');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148161');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148162');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148163');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148164');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148165');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148166');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148167');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148168');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148169');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148170');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148171');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148172');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148173');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148174');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148175');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '176731.59626710007', `saldopv` = '225425.63999999998', `saldoefectivo` = '1557431.940988576' WHERE (`id` = '148176');


update db_springboot_backend.cajachica set saldomp = saldomp-14600, saldopv = saldopv-5227, saldoefectivo= saldoefectivo - 122429 where id = 148177;
update db_springboot_backend.cajachica set saldomp = saldomp-14600, saldopv = saldopv-5227, saldoefectivo= saldoefectivo - 122429 where id = 148178;
update db_springboot_backend.cajachica set saldomp = saldomp-14600, saldopv = saldopv-5227, saldoefectivo= saldoefectivo - 122429 where id >= 148179 and id <= 148204;




    DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148205');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '177461.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1484104.457601576' WHERE (`id` = '148206');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148207');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '177461.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1484984.457601576' WHERE (`id` = '148208');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148209');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '177461.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1485384.457601576' WHERE (`id` = '148210');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148211');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '177461.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1485834.457601576' WHERE (`id` = '148212');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148213');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '177761.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1485834.457601576' WHERE (`id` = '148214');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148215');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148216');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '177761.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1486234.2576015762' WHERE (`id` = '148217');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148218');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '177761.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1488034.2576015762' WHERE (`id` = '148219');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148220');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '177761.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1489694.2576015762' WHERE (`id` = '148221');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148222');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148223');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '177761.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1490534.2576015762' WHERE (`id` = '148224');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148225');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148226');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '177761.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1492324.2576015762' WHERE (`id` = '148227');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148228');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '177761.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1492704.2576015762' WHERE (`id` = '148229');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148230');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '177761.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1493364.2576015762' WHERE (`id` = '148231');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148232');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178191.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1493364.2576015762' WHERE (`id` = '148233');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148234');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178191.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1497199.3576015763' WHERE (`id` = '148235');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148236');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178191.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1497499.2576015762' WHERE (`id` = '148237');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148238');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178191.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1497809.2576015762' WHERE (`id` = '148239');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148240');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148241');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178191.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1498309.2576015762' WHERE (`id` = '148242');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148243');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178191.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1498529.2576015762' WHERE (`id` = '148244');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148245');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178191.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1499119.2576015762' WHERE (`id` = '148246');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148247');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178191.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1499499.2576015762' WHERE (`id` = '148248');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148249');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178191.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1500519.2576015762' WHERE (`id` = '148250');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148251');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178191.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1501249.2576015762' WHERE (`id` = '148252');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148253');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148254');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178191.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1505999.2576015762' WHERE (`id` = '148255');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148256');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178791.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1505979.2576015762' WHERE (`id` = '148257');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148258');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148259');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178791.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1508549.2576015762' WHERE (`id` = '148260');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148261');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '178791.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1509049.2576015762' WHERE (`id` = '148262');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148263');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1509049.2576015762' WHERE (`id` = '148264');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148265');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1509529.2576015762' WHERE (`id` = '148266');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148267');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1510079.2576015762' WHERE (`id` = '148268');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148269');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148270');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1510579.2576015762' WHERE (`id` = '148271');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148272');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1510749.2576015762' WHERE (`id` = '148273');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148274');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1511409.2576015762' WHERE (`id` = '148275');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148276');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1512099.3576015763' WHERE (`id` = '148277');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148278');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1512699.3576015763' WHERE (`id` = '148279');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148280');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1513809.3576015763' WHERE (`id` = '148281');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148282');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1515175.7576015762' WHERE (`id` = '148283');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148284');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1515625.3576015763' WHERE (`id` = '148285');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148286');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1515805.3576015763' WHERE (`id` = '148287');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148288');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148289');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1516205.3576015763' WHERE (`id` = '148290');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148291');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1516435.3576015763' WHERE (`id` = '148292');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1516735.3576015763' WHERE (`id` = '148293');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148294');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1517085.3576015763' WHERE (`id` = '148295');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148296');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1517555.3576015763' WHERE (`id` = '148297');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148298');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148299');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1517975.3576015763' WHERE (`id` = '148300');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148301');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1518435.3576015763' WHERE (`id` = '148302');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148303');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1518835.3576015763' WHERE (`id` = '148304');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148305');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1520205.3576015763' WHERE (`id` = '148306');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148307');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1520405.3576015763' WHERE (`id` = '148308');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148309');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1520805.3576015763' WHERE (`id` = '148310');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148311');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1521315.3576015763' WHERE (`id` = '148312');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148313');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1521715.3576015763' WHERE (`id` = '148314');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148315');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1522615.3576015763' WHERE (`id` = '148316');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148317');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148318');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148320');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1522815.3576015763' WHERE (`id` = '148319');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1523455.3576015763' WHERE (`id` = '148321');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148322');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1524375.3576015763' WHERE (`id` = '148323');
DELETE FROM `db_springboot_backend`.`cajachica` WHERE (`id` = '148324');
UPDATE `db_springboot_backend`.`cajachica` SET `saldomp` = '179281.59626710007', `saldopv` = '157575.63999999998', `saldoefectivo` = '1524775.3576015763' WHERE (`id` = '148325');



delete from cajachica where id >= 148326 and id <=148368;
UPDATE `db_springboot_backend`.`cajachica` SET `caja_id` = '2200' WHERE (`id` = '148369');

update db_springboot_backend.cajachica set saldomp = saldomp-16918, saldopv = saldopv-11497, saldoefectivo= saldoefectivo - 170538 where id >= 148369 and id <= 148375;