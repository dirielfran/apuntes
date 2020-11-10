************************************************************************************************************************
************************************************************************************************************************
******************************************************1012620  	Reportes Syngenta - modalidad de nuevos contratos "Nieto"

	SELECT (SELECT TO_CHAR(fechaalta, 'dd/mm/yyyy')  
		FROM contrato c 
		WHERE numerocontrato =(SELECT con1.NUMEROCONTRATO 
								FROM contrato con1 
								WHERE con1.id  = hijo.CONTRATO_ID)) ,
		(SELECT nrocuenta 
			FROM cuenta_cliente 
			WHERE id = (select con1.COMPRADOR_ID 
					from contrato con1 
					where con1.id  = madre.CONTRATO_ID)),  
		(select con1.NUMEROCONTRATO 
			from contrato con1 
			where con1.id  = madre.CONTRATO_ID), 
		(SELECT razonSocial 
			FROM Cliente c 
			INNER JOIN CUENTA_CLIENTE cc 
			ON c.Id = cc.CLIENTE_ID 
			where cc.id = (select con1.COMPRADOR_ID 
							from contrato con1 
							where con1.id  = madre.CONTRATO_ID)), 
		(SELECT con1.NUMEROCONTRATO 
			FROM contrato con1 
			WHERE con1.id  = hijo.CONTRATO_ID) , 
		(SELECT cuit 
			FROM cliente C 
			INNER JOIN CUENTA_CLIENTE CC 
			ON C.ID = CC.CLIENTE_ID 
			INNER JOIN contrato CON 
			ON CC.ID = CON.VENDEDOR_ID 
			WHERE CON.ID = hijo.CONTRATO_ID) , 
		(SELECT RAZONSOCIAL 
			FROM CLIENTE C 
			INNER JOIN CUENTA_CLIENTE CC 
			ON C.ID = CC.CLIENTE_ID 
			INNER JOIN CONTRATO CON 
			ON CC.ID = CON.VENDEDOR_ID 
			WHERE CON.ID = hijo.CONTRATO_ID) , 
		(SELECT cobroDeMercaderia 
			FROM contrato 
			WHERE id = hijo.CONTRATO_ID),
		(select Descripcion 
			from Producto 
			where id = con.Producto_ID) , 
		(SELECT preciomercaderia 
			FROM contrato 
			WHERE id = hijo.contrato_id) ,
		(SELECT TO_CHAR(fechaentregadesde, 'dd/mm/yyyy') 
			FROM contrato 
			WHERE id = hijo.contrato_id) , 
		(SELECT TO_CHAR(fechaentregahasta, 'dd/mm/yyyy') 
			FROM contrato 
			WHERE id = hijo.contrato_id) , 
		(SELECT TO_CHAR(min(Fechafijaciondesde), 'dd/mm/yyyy') 
			FROM GRUPO_FIJACIONES 
			where CONTRATO_ID = hijo.contrato_id), 
		(SELECT TO_CHAR(max(Fechafijacionhasta), 'dd/mm/yyyy') 
			FROM GRUPO_FIJACIONES 
			where CONTRATO_ID = hijo.contrato_id), 
		(SELECT cantidadPactada 
			FROM  contrato 
			WHERE id = hijo.CONTRATO_ID) , 
		(SELECT ROUND(COALESCE(SUM(ep.cantidad),0),2) 
			FROM entrega_partida ep 
			INNER JOIN workflow_state ws 
			ON ws.id = ep.state_id 
			INNER JOIN workflow_definition w 
			ON w.id = ws.workflowDefinition_id 
			WHERE ep.fechaBaja is NULL 
			AND w.estadoActual != 'N' 
			AND ep.contrato_id = hijo.CONTRATO_ID), 
		(SELECT ROUND(COALESCE(SUM( fp.cantidadFacturada ),0),2) 
			FROM factura_producto fp  
			left join tipo_comprobante tc 
			on tc.id=fp.tipoComprobante_id 
			WHERE fp.contrato_id =  hijo.CONTRATO_ID 
			and upper(fp.facturador) not like '%FINAL%' 
			and tc.abreviatura not in ('FA','ND','NC','LF','BF') 
			and (tc.abreviatura <> 'FC' or fp.descontarCantidadFacturada = 1) 
			and fp.fechaBaja is NULL and fp.anulador_id is null and fp.anulada_id is null), 	
		(SELECT con2.NUMEROCONTRATO 
			FROM contrato con2 
			WHERE con2.id  = nieto.CONTRATO_ID) , 
		(SELECT TO_CHAR(fechaalta, 'dd/mm/yyyy')  
			FROM contrato c 
			WHERE NUMEROCONTRATO = (SELECT con1.NUMEROCONTRATO 
									FROM contrato con1 
									WHERE con1.id  = nieto.CONTRATO_ID)), 
		(SELECT RAZONSOCIAL 
			FROM CLIENTE C 
			INNER JOIN CUENTA_CLIENTE CC 
			ON C.ID = CC.CLIENTE_ID 
			INNER JOIN CONTRATO CON 
			ON CC.ID = CON.VENDEDOR_ID 
			WHERE CON.ID = nieto.CONTRATO_ID) ,
		(SELECT cuit 
			FROM cliente C 
			INNER JOIN CUENTA_CLIENTE CC 
			ON C.ID = CC.CLIENTE_ID 
			INNER JOIN contrato CON 
			ON CC.ID = CON.VENDEDOR_ID 
			WHERE CON.ID = nieto.CONTRATO_ID), 
		(SELECT Descripcion 
			from Producto
			WHERE id = (SELECT con1.Producto_ID 
						FROM contrato con1 
						WHERE con1.id  = nieto.CONTRATO_ID)) ,
		(SELECT preciomercaderia 
			FROM contrato 
			WHERE id = nieto.contrato_id),
		(SELECT TO_CHAR(fechaentregahasta, 'dd/mm/yyyy') 
			FROM contrato 
			WHERE id = nieto.CONTRATO_ID) , 
		(SELECT TO_CHAR(max(Fechafijacionhasta), 'dd/mm/yyyy') 
			FROM GRUPO_FIJACIONES 
			where CONTRATO_ID = nieto.contrato_id), 
		(SELECT cantidadPactada 
			FROM  contrato 
			WHERE id = nieto.CONTRATO_ID),  
		(SELECT ROUND(COALESCE(SUM(ep.cantidad),0),2)  
			FROM entrega_partida ep 
			INNER JOIN workflow_state ws 
			ON ws.id = ep.state_id 
			INNER JOIN workflow_definition w 
			ON w.id = ws.workflowDefinition_id 
			WHERE ep.fechaBaja is NULL AND w.estadoActual != 'N' 
			AND ep.contrato_id = nieto.CONTRATO_ID), 
		(SELECT ROUND(COALESCE(SUM( fp.cantidadFacturada ),0),2) 
			FROM factura_producto fp  
			left join tipo_comprobante tc 
			on tc.id=fp.tipoComprobante_id 
			WHERE fp.contrato_id =  nieto.CONTRATO_ID 
			and upper(fp.facturador) not like '%FINAL%' 
			and tc.abreviatura not in ('FA','ND','NC','LF','BF') 
			and (tc.abreviatura <> 'FC' or fp.descontarCantidadFacturada = 1) 
			and fp.fechaBaja is NULL and fp.anulador_id is null and fp.anulada_id is null) 
	FROM CANJE_CONTRATO_COMP_CEREAL hijo  
	LEFT join CONTRATO con 
	ON con.CANJE_ID = hijo.CANJE_ID 
	LEFT JOIN CANJE_CONTRATO_VTA_CEREAL madre 
	ON  hijo.CANJE_ID = madre.CANJE_ID 
	LEFT JOIN CANJE_CONTRATO_NIETO nieto 
	ON con.CANJE_ID = nieto.CANJE_ID 
	WHERE con.FECHAOPERACION >= to_date('"+ DateUtil.getDate(fechaDesde) + "','dd/MM/yyyy') 
	AND con.FECHAOPERACION <= to_date('"+ DateUtil.getDate(fechaHasta) + "','dd/MM/yyyy') 
	AND con.ESCANJE = 1 
	AND con.CANJE_ID is not null 
	AND (con.ESNIETO is null or  con.ESNIETO = 0 ) 
	AND (con.COMPRADOR_ID in (170029069, 170029809))
	AND ((SELECT con1.comprador_id FROM contrato con1 WHERE con1.id  = hijo.CONTRATO_ID) IN (170029069, 170029809));

************************************************************************************************************************




************************************************************************************************************************
************************************************************************************************************************
***********************************************************************************Ticket#1013756 — RV: contratos nietos
	select *
	FROM CANJE_CONTRATO_COMP_CEREAL hijo  
	LEFT join CONTRATO con 
	ON con.CANJE_ID = hijo.CANJE_ID 
	LEFT JOIN CANJE_CONTRATO_VTA_CEREAL madre 
	ON  hijo.CANJE_ID = madre.CANJE_ID 
	LEFT JOIN CANJE_CONTRATO_NIETO nieto 
	ON con.CANJE_ID = nieto.CANJE_ID 
	WHERE con.FECHAOPERACION >= to_date('"+ DateUtil.getDate(fechaDesde) + "','dd/MM/yyyy') 
	AND con.FECHAOPERACION <= to_date('"+ DateUtil.getDate(fechaHasta) + "','dd/MM/yyyy') 
	AND con.ESCANJE = 1 
	AND con.CANJE_ID is not null 
	AND (con.ESNIETO is null or  con.ESNIETO = 0 ) 
	AND (con.COMPRADOR_ID in (170029069, 170029809))
	AND ((SELECT con1.comprador_id FROM contrato con1 WHERE con1.id  = hijo.CONTRATO_ID) IN (170029069, 170029809));

	SELECT (SELECT c.id 
	FROM Cliente c 
	INNER JOIN CUENTA_CLIENTE cc 
	ON c.Id = cc.CLIENTE_ID
	WHERE CC.ID = con.COMPRADOR_ID) as contrato,
	(SELECT c.id 
	FROM Cliente c 
	INNER JOIN CUENTA_CLIENTE cc 
	ON c.Id = cc.CLIENTE_ID
	WHERE CC.ID = (select con2.COMPRADOR_ID from contrato con2 where con2.id = hijo.contrato_id)) as hijo, 
	  (SELECT c.id 
	FROM Cliente c 
	INNER JOIN CUENTA_CLIENTE cc 
	ON c.Id = cc.CLIENTE_ID
	WHERE CC.ID = (select con3.COMPRADOR_ID from contrato con3 where con3.id = madre.contrato_id)) as madre	
	FROM CANJE_CONTRATO_COMP_CEREAL hijo  
	LEFT join CONTRATO con 
	ON con.CANJE_ID = hijo.CANJE_ID 
	LEFT JOIN CANJE_CONTRATO_VTA_CEREAL madre 
	ON  hijo.CANJE_ID = madre.CANJE_ID
	WHERE con.NUMEROCONTRATO = '19/08248/7'

	SELECT c.id 
	FROM Cliente c 
	INNER JOIN CUENTA_CLIENTE cc 
	ON c.Id = cc.CLIENTE_ID
	WHERE CC.ID = 48179

	select * from CANJE_CONTRATO_COMP_CEREAL

	select * from cliente where id = 19566196   razonsocial like 'Syngenta%'

	select * from contrato where numerocontrato = '19/09704/6'

	select * from cuenta_cliente where id in (50501,48179,50272, 19566196)

	select * from cuenta_cliente where denominacioncuenta like 'Syngenta%'

	(SELECT razonSocial 
				FROM Cliente c 
				INNER JOIN CUENTA_CLIENTE cc 
				ON c.Id = cc.CLIENTE_ID 
				where cc.id = (select con1.COMPRADOR_ID 
								from contrato con1 
								where con1.id  = madre.CONTRATO_ID))
************************************************************************************************************************



************************************************************************************************************************
************************************************************************************************************************
*************************************************Ticket#1013558 — RV: consulta analitica sin cto canje asociado en excel 
	Usuario 	--> Juan Ignacio Dimitroff
	Ext			--> 5061
	Area		--> Mercaderías
	Descripcion:
	El listar los cttos de la consulta analítica los contratos tienen la relación de canje cargada o su respectivo 
	“arbolito”	El problema, es que al bajar la lista en Excel, no aparecen relacionados los contratos de canje… lo que 
	nos obliga a completar los listados de forma manual


	SQL:********************************************************************************************************
		select * from contrato where NUMEROCONTRATO = '18/11382/5'  --contrato.id 187286325

		select * from canje where id = 187286325

		select * from contrato where NUMEROCONTRATO = '19/08461/9'  --contrato.id 198424694

		select * from canje where id = 198424694

		SELECT CONTRATOCANJEASOCIADO_ID FROM contrato


		SELECT C.CONTRATOCANJEASOCIADO_ID,C.ESCANJE, C.NUMEROCONTRATO, C.* FROM CONTRATO WHERE C.NUMEROCONTRATO LIKE (

		'18/11554/0',
		'18/11552/6',
		'18/11382/5',
		'18/13233/6',
		'18/13734/8',
		'18/14391/5',
		'19/01023/6',
		'19/00868/7',
		'18/13676/4',
		'19/06983/7',
		'19/07348/8',
		'19/08461/9',
		'18/12722/2')


		Caso de prueba #1:   --> contrato1 = compra
		null	201225664	1	19/12816/2	201223942	201225664	201223939	null

		Caso de prueba #2:  -->(contrato1 = venta and compra is not null)
		null	201316542	1	19/12935/0	201312449	201316542	201312446	null

		update CANJE_CONTRATO_COMP_CEREAL set contrato_id = 201312446 where CANJE_ID = 201312449;
		update CANJE_CONTRATO_VTA_CEREAL set contrato_id = 201316542 where CANJE_ID = 201312449;

		Caso de prueba #3:  -->(contrato1 = venta and nieto is not null)
		null	201173990	1	19/12700/7	201173431	201173990	201173427	null

		update CANJE_CONTRATO_VTA_CEREAL set contrato_id = 201173990 where CANJE_ID = 201173431;
		insert into CANJE_CONTRATO_NIETO values (201173431,201173427)
		delete CANJE_CONTRATO_COMP_CEREAL where CANJE_ID = 201173431;


		Caso de prueba #4:  --> (contrato1 = nieto and venta is not null)
		null	201173427	1	19/12694/2	null 		null 		null 	null			
		null	201223939	1	19/12812/4	null 		null 		null 	null	

		null 	201173427	1	19/12694/2	19532145	14992108	null 	null	

		    select c.canje_id,c.* from contrato c where numerocontrato = '19/12694/2'
    		update contrato set canje_id = 19532145 where numerocontrato = '19/12694/2'
    
    		select * from canje
    		insert into CANJE_CONTRATO_NIETO values (19532145,201173427)
    		insert into CANJE_CONTRATO_VTA_CEREAL values (19532145,14992108)
    
	Procedimiento Almacenado:***********************************************************************************

		DECLARE 
		     contratoCanjeAsociado CONTRATO.CONTRATOCANJEASOCIADO_ID%type;
		     contrato1 CONTRATO.ID%type;
		     esCanje CONTRATO.ESCANJE%type; 
		     numeroContrato CONTRATO.NUMEROCONTRATO%type; 
		     idCanje CONTRATO.CANJE_ID%type;
		     compra CANJE_CONTRATO_COMP_CEREAL.CONTRATO_ID%TYPE;
		     venta CANJE_CONTRATO_VTA_CEREAL.CONTRATO_ID%type;
		     nieto CANJE_CONTRATO_NIETO.CONTRATO_ID%TYPE;     
		     CURSOR cursor1 is 
		        SELECT C.CONTRATOCANJEASOCIADO_ID , C.ID , C.ESCANJE, C.NUMEROCONTRATO, C.CANJE_ID, H.CONTRATO_ID , V.CONTRATO_ID, N.CONTRATO_ID 
		        FROM CONTRATO C
		        LEFT JOIN
		        CANJE_CONTRATO_COMP_CEREAL H
		        ON C.CANJE_ID = H.CANJE_ID
		        LEFT JOIN
		        CANJE_CONTRATO_VTA_CEREAL V
		        ON C.CANJE_ID = V.CANJE_ID
		        LEFT JOIN
		        CANJE_CONTRATO_NIETO N
		        ON C.CANJE_ID = N.CANJE_ID
		        WHERE C.CONTRATOCANJEASOCIADO_ID  is NULL 
		        AND C.CANJE_ID IS NOT NULL
		        AND C.ESCANJE = 1
		        AND (C.ID = H.CONTRATO_ID OR C.ID = V.CONTRATO_ID OR C.ID = N.CONTRATO_ID)
		        --AND C.ID NOT IN (select id from contratoPre)
		        AND C.FECHAALTA > '01/01/2017';
		        --AND C.NUMEROCONTRATO IN ('19/12930/0','19/12935/0','19/12694/2','19/12700/7','19/12812/4','19/12816/2');
		  BEGIN 
		     OPEN cursor1; 
		     LOOP 
		     FETCH cursor1 into contratoCanjeAsociado, contrato1, esCanje, numeroContrato, idCanje, compra, venta, nieto; 
		        EXIT WHEN cursor1%notfound; 
		        dbms_output.put_line('CanjeAsociado: ' || contratoCanjeAsociado || ' Contrato : ' || contrato1 || ' Canje: ' || esCanje || ' NumeroContrato: ' || numeroContrato || ' idCanje: ' || idCanje);
		        IF contrato1 = compra 
		          THEN
		            INSERT INTO CONTRATOPRE VALUES(contratoCanjeAsociado,contrato1, 'MODIFICACION VENTA: ' || venta);
		            UPDATE CONTRATO 
		            SET CONTRATOCANJEASOCIADO_ID = venta
		            WHERE  CONTRATO.id = contrato1;
		             dbms_output.put_line('MODIFICACION VENTA');
		        ELSIF (contrato1 = venta and compra is not null)  
		          THEN
		            INSERT INTO CONTRATOPRE VALUES(contratoCanjeAsociado,contrato1, 'MODIFICACION COMPRA: ' || compra); 
		            UPDATE CONTRATO 
		              SET CONTRATOCANJEASOCIADO_ID = compra
		              WHERE  CONTRATO.id = contrato1; 
		              dbms_output.put_line('MODIFICACION COMPRA');
		        ELSIF (contrato1 = venta and nieto is not null)
		          THEN 
		            INSERT INTO CONTRATOPRE VALUES(contratoCanjeAsociado,contrato1, 'MODIFICACION NIETO: ' || nieto);
		            UPDATE CONTRATO 
		              SET CONTRATOCANJEASOCIADO_ID = nieto
		              WHERE  CONTRATO.id = contrato1;
		            dbms_output.put_line('modificacion nieto');
		        ELSIF (contrato1 = nieto and venta is not null)
		          THEN 
		            INSERT INTO CONTRATOPRE VALUES(contratoCanjeAsociado,contrato1, 'MODIFICACION VENTA CON NIETO: ' || venta);
		            UPDATE CONTRATO 
		              SET CONTRATOCANJEASOCIADO_ID = venta
		              WHERE  CONTRATO.id = contrato1;
		            dbms_output.put_line('modificacion venta con nieto');
		        END IF;
		     END LOOP; 
		     CLOSE cursor1; 
		  END;  
		/
************************************************************************************************************************


