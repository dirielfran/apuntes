***************************************************************************************DATA DEL TRANS
  Select TRABTH as Lote,' ' as CODIGO,' ' AS USUARIO,            
     TRABRN as Agencia,                                           
     TRAGLN as Cuenta_Contable,                                   
     case when TRADCC = 5 then 'Credito'                          
          when TRADCC = 0 then 'Debito'                           
          end as IndDebitoCredito,                                
     TRAAMT as Monto,                                             
     TRABDM as Mes_Contable,                                      
     TRANAR as Descripcion,                                       
     TRACKN as Referencia,                                        
     concat(concat(digits(TRABDM),digits(TRABDD)),digits(TRABDY)) 
     as FecContab                                                 
   From siif0910.trans                                            
   where  TRABDM = 05 and TRABDY = 2018                           
  and TRABTH in(SELECT BTHFB1 FROM SIIF0910/LOTUSERPF where       
                       BTHFB1 <> 0)                               
  fetch first 100 rows only



  SELECT sum(TRAAMT) FROM siif0910.trans WHERE TRAACC= 6567148 and 
  substr(TRAGLN,1,3) = 275 and TRADCC = 5 and TRABDM in (09) and   
  TRABDY = 2018 GROUP BY TRAACC  
*****************************************************************************************************




*********************************************************************************UPDATE DE OTRA TABLA
  update b02trabajo.SIF004HHHH  set CARNGE = (select b.TGENOM  
  from b02trabajo.tbgrupoeco b inner join b02trabajo.SIF004HHHH
  on a.CARIDN = b.CARIDN)
  where a.CARIDN = b.CARIDN 
*****************************************************************************************************




******************************************************************************ubica credito diferente
  SELECT CARSAL,A.* FROM B02TRABAJO.SIF004HHHH A        
   WHERE dec(substring(a.caracc, 24,30),7,0) NOT IN (   
   SELECT  CREDI     from b02trabajo.saldories          
    WHERE CTA1 =   1310510101 )                         
    AND substring(a.CARGLN,1,8) = 13105101              
    AND CAREST=1              
*****************************************************************************************************             




************************************************************************************BUSCAR REPETIDOS
  Select                        
       REFER, Count(*) As Total 
  From                          
     b02trabajo/auextman1       
  Group By                      
      REFER                     
  Having                        
      Count(*) > 1 

      Nota: REFER es el atributo de referencia ej:
        select caracc                            
        from b02trabajo.sif004hHHH               
        group by Caracc                          
        having count(caracc) > 1
****************************************************************************************************



**********************************************************************Se borran registros duplicados
  1*-Se identifican los creditos duplicados
  select caracc                            
  from b02trabajo.sif004hHHH               
  group by Caracc                          
  having count(caracc) > 1

  2*-Se crea tabla con todos los creditos  duplicados 
  create table b02trabajo.sobdupa as (     
  select * from  b02trabajo.sif004hHHH     
  where caracc in (                        
  select caracc                            
  from b02trabajo.sif004hHHH               
  group by Caracc                          
  having count(caracc) > 1 ))with data 

  3*-Se borran los duplicados en la tabla creada en paso 2
  delete b02trabajo.sobdupa                      
  where rrn(sobdupa) in(                         
  SELECT rrn(sobdupa) FROM b02trabajo.sobdupa  
  where mod(rrn(sobdupa),2)=0) 

  4*-Se borran todos los resgistros duplicados de la tabla original
  delete  b02trabajo.sif004hHHH                           
  where caracc in (select caracc from b02trabajo.sobdupa)

  5*-Se insertan las registrso de la tabla temporal en la tabla original
  insert into b02trabajo.sif004hHHH
    select * from b02trabajo.sobdupa
****************************************************************************************************




************************************************Identifica registro duplicados por registro relativo
  SELECT rrn(dupli04d1),caracc FROM b02trabajo.dupli04d1      
  where mod(rrn(dupli04d1),2)=0 
****************************************************************************************************




*********************************************************************************update mes anterior
  UPDATE b02trabajo.sif004hhhh  A                               
  SET A.carufc=(SELECT B.carufc FROM                            
  b02trabajo.sif004hant B WHERE a.CARACC                        
  =b.caracc  )                                                  
   WHERE a.caracc in(                                           
  SELECT caracc                                                 
  FROM b02trabajo.sif004hant)                                   
  AND (A.CARSAL+A.carm30+A.carm60+A.carm90+A.car120)<A.carmon   
  and A.carufc = 19000101                                       
  and A.carest = 1


  ------------------------------------------------------------------
  UPDATE B02TRABAJO/SIF004HHHH SET (CARFVA,CARFPR,CARFVO,CARCPR)= 
  (SELECT CARFVA,CARFPR,CARFVO,CARCPR FROM B02TRABAJO/SIF004HANT 
  WHERE CARACC IN ('000000000000000000000003758749',   
  '000000000000000000000001138293'))
****************************************************************************************************




*********************************************************************Comparacion de AT07 con el AT04
  sif004en18/fe18/ab18
  inforgofi sif0071801/02/03/04

  AT07 != AT04 --> 8298 -->B02TRABAJO.AT07NINAT04
  select count(distinct(sifacc)) from inforgofic.sif0071804 
  where lpad(sifacc,'30','0') not in (select caracc from    
  b02trabajo.sif004ab18)

  ----------------------------------------------------------------------------------------------
  select count(*) from b02trabajo.sif004hhhh       
  where caracc in ( 
  select lpad(SIFACC,30,0 ) from siif0910.sif007pf)

  AT07 != AT04 --> 8298 -->B02TRABAJO.AT07NINAT04
  select count(distinct(sifacc)) from inforgofic.sif0071804 
  where lpad(sifacc,'30','0') not in (select caracc from    
  b02trabajo.sif004ab18)
****************************************************************************************************





********************************************************************UPDATE DE CAMPO SEGUN OTRO CAMPO

  BEGIN                                  
     DECLARE SQLCODE INTEGER DEFAULT 0;  
     DECLARE CREDITO NUMERIC(30);                 
     DECLARE MONTO NUMERIC(19,2);       
     DECLARE C1 CURSOR FOR
        SELECT CREDI, XCARPVI          
        FROM B02TRABAJO.PRUEBASALA          
        WHERE CREDI IN (378995, 390813,
                         399276)                 
     OPEN C1;                            
  fetch_loop:                            
     LOOP                                
        FETCH c1 INTO CREDITO, MONTO;        
        IF SQLCODE = 0 THEN 
           UPDATE B02TRABAJO.PRUEBASALb
           SET XCARPVI = MONTO     
              WHERE CREDI = CREDITO;     
        ELSE                          
          LEAVE fetch_loop;           
        END IF;                       
     END LOOP fetch_loop;             
     CLOSE C1;                        
  END 
****************************************************************************************************





****************************************************************************Añadir campo a una tabla
  alter table cliente
    add observacion varchar(255)
****************************************************************************************************





***********************************************************************************Modificar columna
  ALTER TABLE TBL_IMAGES
  MODIFY ID INT NULL;
****************************************************************************************************





**************************************************************************reporte con CUBE y rollup
  SELECT LOTE, CTA_CONT,substring(FECCONT,3,2) as Mes,sum(MONTO), COUNT(*) AS Total,    
     CASE                                                  
        WHEN GROUPING(LOTE)=0 AND  andGROUPING(CTA_CONT)=1     
        Then 'Total Lote'                                  
        WHEN GROUPING(LOTE)=1 AND GROUPING(CTA_CONT)=0     
        Then 'Total Cuenta'                                
        WHEN GROUPING(LOTE)=1 AND GROUPING(CTA_CONT)=1     
        Then 'Total General'                               
     END AS TipoFila                                       
  FROM B02TRABAJO.AUEXTman3                                
  GROUP BY CUBE(LOTE,CTA_CONT,substring(FECCONT,3,2))  
  -------------------------------------------------------------------------------------
  SELECT LOTE, CTA_CONT, COUNT(*) as Cantidad
  FROM B02TRABAJO.AUEXTMAN1          
  GROUP BY LOTE,CTA_CONT             
  with rollup     
  -------------------------------------------------------------------------------------
  SELECT LOTE, CTA_CONT, COUNT(*)
  FROM B02TRABAJO.AUEXTMAN1      
  GROUP BY LOTE,CTA_CONT         
  with cube    

  -------------------------------------------------------------------------------Oracle
  SELECT fechaalta, COUNT(*) as Cantidad
  FROM cuenta_cliente   
  where fechaalta >= '01/01/2019'
  and fechaalta <= '01/02/2019'
  GROUP BY  rollup(fechaalta)   
***************************************************************************************************






************************************************************************funciona PROCEDURE CON LOOP
  CREATE PROCEDURE b02trabajo.PROCsicol() 
  LANGUAGE SQL                           
  BEGIN                                  
     DECLARE SQLCODE INTEGER DEFAULT 0;  
     DECLARE Id decimal(19);
     DECLARE cuenta integer;                 
     DECLARE C1 CURSOR FOR               
        SELECT distinct(SICIDN)
        FROM SIOFFLIB.sicol  
        WHERE SICTIP = 4 ;                
     OPEN C1;                            
  fetch_loop:                            
     LOOP                                
        FETCH c1 INTO Id;        
        IF SQLCODE = 0 THEN
      SET cuenta = (select count(*) 
                         from SIOFFLIB.sicol
                         where sicidn = id
                         and  SICTIP = 4);             
           UPDATE  SIOFFLIB.sicol
      set SICPRI = ((SELECT SIGPRI 
                         FROM SIOFFLIB.sigarb 
                         WHERE SIGTIP = 4 
                         and SIGIDN = id)/cuenta)
           WHERE sicidn = id
           and sictip = 4;     
        ELSE                          
          LEAVE fetch_loop;           
        END IF;                       
     END LOOP fetch_loop;             
     CLOSE C1;                        
  END   
***************************************************************************************************





**************************Actividad modificacion del saldo de garantias en cuenta contable dolarisada 
  CREATE PROCEDURE b02trabajo.PROCsigar(IN TasaD DECIMAL(19,2),
                                IN TasaE DECIMAL(19,2))              
  LANGUAGE SQL                                      
  BEGIN  
    update SIOFFLIB.sigar set sigpri = sigpri*TasaE     
    where sigidn in(                            
      SELECT distinct(a.sigidn)                   
      FROM SIOFFLIB.sigar as A                    
      inner join SIOFFLIB.cumst as B              
      on SIGNAC||lpad(SIGIDN,9,'0') = cusidn      
      inner join SIOFFLIB.colmst as C             
      on cuscun = colcun                          
      WHERE substring(digits(colgln),1,6)= 813071 
      and SIGTIP=4                                
      and C.colusd='EUR')                         
    and SIGTIP=4;
    update SIOFFLIB.sigar set sigpri = sigpri*TasaD     
    where sigidn in(                            
      SELECT distinct(a.sigidn)                   
      FROM SIOFFLIB.sigar as A                    
      inner join SIOFFLIB.cumst as B              
      on SIGNAC||lpad(SIGIDN,9,'0') = cusidn      
      inner join SIOFFLIB.colmst as C             
      on cuscun = colcun                          
      WHERE substring(digits(colgln),1,6)= 813071 
      and SIGTIP=4                                
      and C.colusd='USD')                         
    and SIGTIP=4;                                     
  END  
*****************************************************************************************************





****************************************************************************CREATE PROCEDURE  EJEMPLO
  ***pgm
  EXEC SQL CREATE PROCEDURE pettycash( 
   IN Amount DECIMAL(10,2) )
  BEGIN
   UPDATE account
   SET balance = balance - Amount
   WHERE name = 'bank';

   UPDATE account
   SET balance = balance + Amount
   WHERE name = 'pettycash expense';
  END;
  EXEC SQL CALL pettycash( 10.72 );

  ***en sql
  CREATE PROCEDURE qgplbanes.clientes(        
   IN id int )         
  BEGIN                              
   UPDATE qgplbanes.clients                   
   SET NAME_CLI = 'Elvis'    
   WHERE ID_CLI = id;              
                                     
   UPDATE qgplbanes.clients                   
   SET NAME_CLI = 'Ricardo'    
   WHERE ID_CLI <> id ; 
  END        

  ***pgm
  EXEC SQL CALL qgplbanes.clientes( 1 );  

  ***en sql
  CALL qgplbanes.clientes( 1 )  


  CLIENTES    *PGM      QGPLBANES   CLE         SQL PROCEDURE CLIENTES 
*****************************************************************************************************


 

***************************************************************************EJEMPLO PROCEDURE CON CASE
  CREATE PROCEDURE Empeval
    (IN I_Empnbr CHARACTER(5),
      IN I_Empevl DECIMAL(2) )
  LANGUAGE SQL
    CASE
    WHEN I_Empevl > 90 THEN UPDATE
      Employee SET Salary = Salary * 1.3
    WHERE EMPNR = I_Empnbr ;
    WHEN I_Empevl > 80 THEN UPDATE
      Employee SET Salary = Salary * 1.2
    WHERE EMPNR = I_Empnbr ;
    ELSE UPDATE Employee
      SET Salary = Salary * 1.05
    WHERE EMPNR = I_Empnbr ;
    END CASE ;
  ;
*****************************************************************************************************






********************************************************SENTENCIA IF-THEN/IF-THEN-ELSE/IF-THEN-ELSEIF
  CASE
  WHEN Evaluation > 90 THEN SET Factor = 1.3;
  WHEN Evaluation > 80 THEN SET Factor = 1.2;
  WHEN Evaluation > 70 THEN SET Factor = 1.1;
  ELSE SET Factor = 1.05;
  END CASE ;

  CREATE PROCEDURE qgplbanes.clientes2()                          
  LANGUAGE SQL                                                    
  BEGIN                                                           
    declare MayorId integer;                                        
    SET MayorId = (SELECT MAX(ID_CLI) FROM qgplbanes.clients);      
    IF MayorId > 1 THEN                                             
      UPDATE QGPLBANES.CLIENTS SET NAME_CLI = 'ELVIS' WHERE ID_CLI = 
      MayorId;                                                        
    else                                                            
    UPDATE QGPLBANES.CLIENTS SET NAME_CLI = 'Ricardo';             
    END IF;                                                         
  END                                                             
****************************************************************************************************





*****************************************************************************SENTENCIA ITERATIVA FOR
  FOR Each_Record AS Cursor1 CURSOR FOR
  SELECT Cusnbr, Cuscrd FROM Customer
  DO
  UPDATE Customer
  SET Cuscrd = Cuscrd * 1.1
  WHERE CURRENT OF Cursor1;
  END FOR ;



  CREATE PROCEDURE qgplbanes.clientes2()                   
  LANGUAGE SQL                                             
  BEGIN                                                    
     FOR Each_Record AS Cursor1 CURSOR FOR                 
          SELECT * FROM QGPLBANES.CLIENTS WHERE ID_CLI = 1 
     DO                                                    
        UPDATE  QGPLBANES.CLIENTS                          
        SET NAME_CLI = 'ANTONIO'                           
        WHERE CURRENT OF Cursor1;                          
     END FOR;                                              
  END  
****************************************************************************************************
  




*******************************************************************************************RESULTSET
  CREATE PROCEDURE clientes4()
     RESULT SETS 1
     LANGUAGE SQL
  BEGIN
     DECLARE C1 CURSOR FOR SELECT id
        FROM qgplbanes.clients ;
     OPEN C1 ;
     SET RESULT SETS CURSOR C1 ;
  END ;       
****************************************************************************************************





********************************************************************************************TRIGGERS
  drop trigger QGPLBANES.TRIGGERS1;

  create trigger QGPLBANES.TRIGGERS1
     after INSERT ON QGPLBANES.CLIENTS
     FOR EACH ROW MODE DB2SQL
  BEGIN
     INSERT INTO QGPLBANES.CLIENTS1 (SELECT * FROM 
        QGPLBANES.CLIENTS);
  END;             
            
***************************************************************************************************





*************************************************************SINTAXIS DE SENTESNCIAS DE DECLARACION
  BEGIN
  DECLARE Sqlcode INTEGER;
  DECLARE Total_Sales DECIMAL(11,2);
  DECLARE Number_Customer DECIMAL(5);
  DECLARE Err_Msg CHARACTER(10);
  DECLARE Product_Photo BLOB (12k);
  DECLARE Timestamp_Order TIMESTAMP;
  DECLARE Date_Order DATE;
  ......
  END
***************************************************************************************************





**************************************************************Ejercicio de PROCEDIMIENTO ALMACENADO
  MEJORES N PROVEEDORES CON MAYOR VENTA
  PEORES N PROVEEDORES CON MENOR VENTA
  SI N = 0 DEBERIA GENERAR UNA CLASIFICACION DESENDENTE O ASCENDENTE SEGUN EL CASO 
  EL PROGRAMA RETORNA UN PARAMETRO DE SALIDA CON LAS FILAS DISPONIBLES POR CADA CONJUNTO DE RESULADOS

  CREATE PROCEDURE DBTEAMXX/Get_Supplier_solution
    (IN proc_year DECIMAL(4,0),
    IN proc_month DECIMAL(2,0),
    INOUT proc_rank INTEGER)
  RESULT SETS 2
  LANGUAGE SQL

  BEGIN
    DECLARE highsales DECIMAL(11,2);
    DECLARE lowsales DECIMAL(11,2);
    DECLARE rank1 INTEGER;
    DECLARE rank2 INTEGER;
    DECLARE SQLStmt CHAR(512);

    --SE DECLARAN LOS CURSORES COMO DYNAMIC SCROLL, PARA QUE TODOS LOS FETCH ESTEN DISPONIBLES
    (fetch options (FIRST, LAST, PRIOR, NEXT, RELATIVE, ABSOLUTE) )
    DECLARE c1 DYNAMIC SCROLL CURSOR FOR s1;
    DECLARE c2 DYNAMIC SCROLL CURSOR FOR s2;

    --SE OBTIENE EL RANKING DE LOS MEJORES VENDEDORES
    SET SQLStmt='SELECT totalsales FROM totalsale
                       WHERE year=? AND month=?
                       ORDER BY totalsales DESC';
    PREPARE s1 FROM SQLStmt;

          -- SE ABRE CURSOR PASANDOLE LAS VARIABLES
    OPEN c1 USING proc_year, proc_month;

    IF proc_rank = 0 THEN
      --devuelve la ultima fila del cursor
      FETCH LAST FROM c1 INTO highsales;
    ELSE
      --devuelve la n fila mas alla de la fila actual
      FETCH RELATIVE proc_rank FROM c1 INTO highsales;
    END IF;

    IF highsales IS NULL THEN
      -- Just in case that we ask for more rank positions then there are Suppliers
      FETCH LAST FROM c1 INTO highsales;
    END IF;
    
    CLOSE c1;
    
    --Obtengo el numero de registros del mes y año con la mayor venta
    SET SQLStmt='SELECT count(*) FROM totalsale
        WHERE year=? AND month=? AND totalsales>=?';
    PREPARE s1 FROM SQLStmt;
    OPEN c1 USING proc_year, proc_month, highsales;
    FETCH c1 INTO rank1;
    CLOSE c1;

    --obtengo los registros con mayor venta
    SET SQLStmt='SELECT supplier_name,totalsales FROM totalsale
        WHERE year=? AND month=? AND totalsales>=?
        ORDER BY totalsales DESC';
    PREPARE s1 FROM SQLStmt;
    OPEN c1 USING proc_year, proc_month, highsales;

    --hacemos el mismo proceso para obtener los peores proveedores
    SET SQLStmt='SELECT totalsales FROM totalsale
        WHERE year=? AND month=?
        ORDER by totalsales ASC';
        PREPARE s2 FROM SQLStmt;

    PREPARE s2 FROM SQLStmt;
    OPEN c2 USING proc_year, proc_month;
    IF proc_rank = 0 THEN
      FETCH LAST FROM c2 INTO lowsales;
    ELSE
      FETCH RELATIVE proc_rank FROM c2 INTO lowsales;
    END IF;

    IF lowsales IS NULL THEN
      FETCH LAST FROM c2 INTO lowsales;
    END IF;

    CLOSE c2;

    SET SQLStmt='SELECT count(*) FROM totalsale
        WHERE year=? AND month=? AND totalsales<=?';
    
    PREPARE s2 FROM SQLStmt;
    
    OPEN c2 USING proc_year,proc_month, lowsales;

    FETCH c2 INTO rank2;
    CLOSE c2;

    SET SQLStmt='SELECT supplier_name,totalsales FROM totalsale
        WHERE year=? AND month=? AND totalsales<=?
        ORDER BY totalsales ASC';
    PREPARE s2 FROM SQLStmt;
    OPEN c2 USING proc_year,proc_month, lowsales;

    IF rank1< rank2 THEN
      SET proc_rank=rank2;
    ELSE
      SET proc_rank=rank1;
    END IF;

    sET RESULT SETS CURSOR c1, CURSOR c2 ;
  END
***************************************************************************************************





****************************************************************************************RESULT SETS  

  HAY DOS FORMAS DE RETORNAR RESULT STES
    SET RESULT SETS
    DECLARE C1 CURSOR WITH RETURN


   
  ***SET RESULT SETS

  CREATE PROCEDURE GetCusName()
     RESULT SETS 1                            -->SE NECEESITA DEFINIR EL NUMERO DE CONJUNTOS
     LANGUAGE SQL
  BEGIN
     DECLARE C1 CURSOR FOR SELECT Cusnam
         FROM Customer ORDER BY Cusnam;
     OPEN C1 ;
     SET RESULT SETS CURSOR C1 ;              -->CODIFICAR
  END ;          




  *** DECLARE C1 CURSOR WITH RETURN

  CREATE PROCEDURE Set_Return
     RESULT SETS 1
     LANGUAGE SQL
  BEGIN
     DECLARE cursor CURSOR WITH RETURN FOR    -->DECLARACION      
     SELECT Employee_Name
        FROM Employee_Table ;
     OPEN cursor ;
  END ; 
***************************************************************************************************





*****************************************************************TRABAJANDO CON MULTIPLES CONJUNTOS
  CREATE PROCEDURE Getrank
     (IN Proc_Year DECIMAL(4,0) ,
      IN Proc_Month DECIMAL(2,0) ,
      INOUT Proc_Rank INTEGER )
     RESULT SETS 2                            -->DEFINIR EL NUMERO DE CONJUNTOS
     LANGUAGE SQL
  BEGIN 
     DECLARE c1 DYNAMIC SCROLL CURSOR FOR s1; 
     DECLARE c2 CURSOR FOR s2;
     SET SQLStmt1='SELECT Supplier_Name,Totalsales
                      FROM Totalsale
                      WHERE Year=? AND Month=? AND Totalsales>=?
                      ORDER BY Totalsales DESC' ;
     SET SQLStmt2='SELECT Supplier_Name, Totalsales
                      FROM Totalsale
                      WHERE Year=? AND Month=? AND Totalsales>=?
                      ORDER BY Totalsales ASC'; 

     /* Se PREPARAN LAS SENTENCIAS */
     PREPARE s1 FROM SQLStmt1 ;
     PREPARE s2 FROM SQLStmt2 ;
     /* Open the cursors procedure variables */
     OPEN c1 USING Proc_Year, Proc_Month, Highsales ;
     OPEN c2 USING Proc_Year,Proc_Month, Lowsales ;
     /* Set the Result Sets */
     SET RESULT SETS CURSOR c1 , CURSOR c2 ; 
  end;  
***************************************************************************************************




********************************************************************************PROCEDURE CON LOOP
  CREATE PROCEDURE qgplbanes.clientes3() 
  LANGUAGE SQL                           
  BEGIN                                  
     DECLARE SQLCODE INTEGER DEFAULT 0;  
     DECLARE Id integer;                 
     DECLARE nombre character(20);       
     DECLARE C1 CURSOR FOR               
        SELECT ID_CLI, NAME_CLI          
        FROM QGPLBANES.CLIENTS           
        WHERE ID_CLI= 1;                 
     OPEN C1;                            
  fetch_loop:                            
     LOOP                                
        FETCH c1 INTO Id, nombre;        
        IF SQLCODE = 0 THEN              
           SET nombre = 'ARMANDO';              
           UPDATE qgplbanes.clients
           SET NAME_CLI  = NOMBRE     
              WHERE CURRENT OF c1;     
        ELSE                          
          LEAVE fetch_loop;           
        END IF;                       
     END LOOP fetch_loop;             
                                      
     CLOSE C1;                        
  END      
**************************************************************************************************




********************************************************************************propiedad OPTIMIZE
  select  deabnk, deagln,deaacc                            
   From siif0910.Deals                                     
          Where DeaSts <> 'C' and                          
          (DEAPRI>0) And Substr(Char(DeaGLN),1,3)          
          In('131', '132', '133', '134', '819')  and       
          RRN(deals) BETWEEN 1 And 320314  or              
                                                           
              (DeaPri = 0 And DeaLpm =07 And DeaLpy =2018) 
           and (DEAPRI=0) And Substr(Char(DeaGLN),1,3)     
               In('131', '132', '133', '134', '819') and   
               RRN(deals) BETWEEN 1 And 320314             
           FOR READ ONLY OPTIMIZE FOR 1000000 ROWS 
**************************************************************************************************        





***********************************************************************************Multiples insert
  begin 
  insert into b02trabajo/manuales values (31002 ,'Manual','Identificado en el Primer semestre como manual');
  insert into b02trabajo/manuales values (12665 ,'Manual','Identificado en el Primer semestre como manual');
  insert into b02trabajo/manuales values (39912 ,'Manual','Identificado en el Primer semestre como manual');
  insert into b02trabajo/manuales values (40110 ,'Manual','Identificado en el Primer semestre como manual');
  insert into b02trabajo/manuales values (5074 ,'Manual','Identificado en el Primer semestre como manual');
  insert into b02trabajo/manuales values (39859 ,'Manual','Identificado en el Primer semestre como manual');
  insert into b02trabajo/manuales values (39914 ,'Manual','Identificado en el Primer semestre como manual');
  insert into b02trabajo/manuales values (40136 ,'Manual','Identificado en el Primer semestre como manual');
  insert into b02trabajo/manuales values (36019 ,'Manual','Identificado en el Primer semestre como manual');

  insert into b02trabajo/automatico values (90400,'Manual','Identificado en el Primer semestre como manual');
  insert into b02trabajo/automatico values (38124,'Manual','Identificado en el Primer semestre como manual');
  insert into b02trabajo/automatico values (34034,'Manual','Identificado en el Primer semestre como manual');
  insert into b02trabajo/automatico values (9729 ,'Manual','Identificado en el Primer semestre como manual');
  End
***************************************************************************************************



*************************************************************************************************Zeni
***********************************************************************Sentencia Insert con TIMESTAMP
  insert into contrato_factura_nietos values (202616523,  CURRENT_TIMESTAMP,'esubovsky', null,null,null
    ,null,1268,  5592,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 202616164, null )
*****************************************************************************************************




*************************************************************************************************Zeni
******************************************************************Select Cantidad Limitadad Registros
  SELECT nombre FROM LIBROS where rownum < 11.
*****************************************************************************************************



***************************************************************************Select filtrando por fecha
  select wd.estadoactual,p.estadoCuit,c.*
  from cliente c
  inner join workflow_state ws
  on c.state_id = ws.id
  inner join workflow_definition wd
  on ws.workflowdefinition_id = wd.id
  inner join padron_rg2300 p
  on c.cuit = p.cuit
  where estadoactual = 'H'
  and trunc(fechaAlta) = '15/08/2019' 
*****************************************************************************************************


*************************************************************************************************Zeni
*****************************************************************************Procedimiento Almacenado
  CREATE or replace PROCEDURE PADRONES (fechaAltaPadron IN padron_rg2300.FECHAALTA%type, fechaCopy IN padron_rg2300.FECHAALTA%type) is  
    cuenta PADRON_RG2300.ID%type;
    fechaAlta padron_rg2300.FECHAALTA%type;
    userAlta padron_rg2300.USERALTA%type;
    fechaModif padron_rg2300.FECHAMODIF%type;
    usuarioModif padron_rg2300.USERMODIF%type;
    fechaBaja padron_rg2300.FECHABAJA%type;
    userBaja padron_rg2300.USERBAJA%type;
    fechaGeneracion padron_rg2300.FECHAGENERACION%type;
    observacion padron_rg2300.OBSERVACIONES%type;
    situacion padron_rg2300.SITUACION%type;
    cuit padron_rg2300.CUIT%type;
    cbu padron_rg2300.CBU%type;
    categoria padron_rg2300.CATEGORIA%type;
    fechaActCBU padron_rg2300.FECHAACTUALIZACIONCBU%type;
    fechaPublicacion padron_rg2300.FECHAPUBINCLUSION%type;
    fechaPubSusp padron_rg2300.FECHAPUBSUSPENSION%type;
    fechaLevSusp padron_rg2300.FECHALEVANTASUSPENSION%type;
    fechaNotExc padron_rg2300.FECHANOTIFEXCLUSION%type;
    fechaActRegis padron_rg2300.FECHAACTUALIZACIONREGISTRO%type;
    estado padron_rg2300.STATE_ID%type;
    estadoCuit padron_rg2300.ESTADOCUIT%type;
    fechaVigEst padron_rg2300.FECHAVIGENCIAESTADO%type;
    fechaNotEst padron_rg2300.FECHANOTIFICACIONDFEESTADO%type;
    fechaNotCat padron_rg2300.FECHANOTIFICACIONDFECATEGORIA%type;
    codCategoria padron_rg2300.CODIGOCATEGORIA%type;
    CURSOR cursor1 is
    select p.FECHAALTA
          ,p.USERALTA
          ,p.FECHAMODIF
          ,p.USERMODIF
          ,p.FECHABAJA
          ,p.USERBAJA
          ,p.FECHAGENERACION
          ,p.OBSERVACIONES
          ,p.SITUACION
          ,p.CUIT
          ,p.CBU
          ,p.CATEGORIA
          ,p.FECHAACTUALIZACIONCBU
          ,p.FECHAPUBINCLUSION
          ,p.FECHAPUBSUSPENSION
          ,p.FECHALEVANTASUSPENSION
          ,p.FECHANOTIFEXCLUSION
          ,p.FECHAACTUALIZACIONREGISTRO
          ,p.STATE_ID
          ,p.ESTADOCUIT
          ,p.FECHAVIGENCIAESTADO
          ,p.FECHANOTIFICACIONDFEESTADO
          ,p.FECHANOTIFICACIONDFECATEGORIA
          ,p.CODIGOCATEGORIA
      from cliente c
      inner join workflow_state ws
      on c.state_id = ws.id
      inner join workflow_definition wd
      on ws.workflowdefinition_id = wd.id
      inner join padron_rg2300 p
      on c.cuit = p.cuit
      where wd.estadoactual = 'H'
      and trunc(p.fechaAlta) = trunc(fechaCopy); 
  BEGIN 
    OPEN cursor1; 
    SELECT MAX("A1"."ID") "MAX(ID)" into cuenta FROM "APPTEST"."PADRON_RG2300" "A1";
    LOOP
      cuenta := cuenta + 1;
      FETCH cursor1 into  fechaAlta , userAlta , fechaModif , 
                usuarioModif , fechaBaja, userBaja , 
                fechaGeneracion , observacion , situacion, 
                cuit , cbu , categoria , 
                fechaActCBU, fechaPublicacion, fechaPubSusp, 
                fechaLevSusp, fechaNotExc, fechaActRegis, 
                estado, estadoCuit, fechaVigEst, 
                fechaNotEst, fechaNotCat, codCategoria;
      EXIT WHEN cursor1%notfound;
      insert into padron_rg2300 values ( cuenta, fechaAltaPadron , userAlta , fechaModif , 
                usuarioModif , fechaBaja, userBaja , 
                fechaGeneracion , observacion , situacion, 
                cuit , cbu , categoria , 
                fechaActCBU, fechaPublicacion, fechaPubSusp, 
                fechaLevSusp, fechaNotExc, fechaActRegis, 
                estado, estadoCuit, fechaVigEst, 
                fechaNotEst, fechaNotCat, codCategoria);  
    END LOOP;
    CLOSE cursor1;          
  END; 


  EXECUTE PADRONES('17/08/2019 01:00:00,000000000 AM', '15/08/2019 01:00:00,000000000 AM');
*****************************************************************************************************




  CREATE or replace PROCEDURE CartaPorte(fechaInicio IN contrato.fechaoperacion%type, fechaFin IN contrato.fechaoperacion%type) is
   contradoId contrato.ID%type;
   CURSOR cursor1 is
    select id
    from contrato c
    where (c.comprador_id = 47804 
    and c.escanje = 1) 
    and (trunc(c.fechaoperacion) >= trunc(fechaInicio) 
    and  trunc(c.fechaoperacion) >= trunc(fechaFin));
  BEGIN
      OPEN cursor1; 
      LOOP
        FETCH cursor1 into contradoId;
        DBMS_OUTPUT.put ('Texto de prueba');
        EXIT WHEN cursor1%notfound;
      END LOOP;
      CLOSE cursor1;
  END; 

   EXECUTE PADRONES('17/01/2019 01:00:00,000000000 AM', '15/08/2019 01:00:00,000000000 AM');



******************************************************************Ticket#1014664 — Incidencias para mercado de capitales 
  select C.razonsocial, 
          C.cuit, 
          (SELECT mp.nombre FROM mat_producto mp  WHERE mp.id = matO.matproducto_id) , 
          matO.horasaliquidar as Plazo,
          matO.id, 
          matO.cuentacliente_id, 
          (select nombre from moneda where id = (select moneda_id from mat_Operacion where id = matO.id)), 
          case when matO.condicion = 'C' then 'Compra' 
              when matO.condicion = 'V' then 'Venta' 
          end, 
          matO.volumen, 
          matO.preciooprima 
  from mat_operacion matO 
  inner join cuenta_cliente CC 
  on matO.cuentacliente_id = CC.id 
  inner join cliente C 
  on C.Id = CC.CLIENTE_ID 
  WHERE matO.fecha >= to_date('DateUtil.getDate(fechaDesde)','dd/MM/yyyy') 
  AND matO.fecha <= to_date('DateUtil.getDate(fechaHasta)','dd/MM/yyyy') 
  AND matO.condicion NOT IN ('R','S')  
  and matO.tipo = 'L'+stringCuit;
*****************************************************************************************************


*************************************************************************************************Zeni
*******************************************************Simax 53 Requerimiento Validacion de Percepcion
  CREATE or replace PROCEDURE IIBB(cuit IN cliente.cuit%type, sucursal IN sucursal.nombre%type) is
    idProvincia partido.provincia_id%type;
    CURSOR cursor1 is
    select p.provincia_id 
    from sucursal s
    inner join ciudad c
    on s.ciudad_id = c.id
    inner join partido p
    on c.partido_id = p.id
    where s.nombre := sucursal;
  BEGIN
      OPEN cursor1;
      FETCH cursor1 into contradoId; 
      DBMS_OUTPUT.put_line(idProvincia);
  END; 

   EXECUTE IIBB('30-62197317-3', 'Casa Central');
   ----------------------------------------------------------------------------------------------
   CREATE or replace PROCEDURE IIBB(cuitCliente IN cliente.cuit%type) is
    idCliente cliente.id%type;
    CURSOR cursor1 is
      select c.id from cliente c where c.cuit = cuitCliente;    
      BEGIN    
          OPEN cursor1;     
          FETCH cursor1 into idCliente ;
          update cliente_provincia_iibb set esagentePERCEPCION = 1 where cliente_id = idCliente and provincia_id = 233;
          dbms_output.put_line(idCliente ||'-'|| cuitCliente);
      END; 


    EXECUTE IIBB('30-62197317-3');
******************************************************************************************************


 
**********************************************************Seleccionar TOP N registro ordenado(Oracle)
   select * from (
  select c.promedio 
                from
                    moneda_cotizacion c 
                where c.moneda_id = 1718 
                    and c.cotizada_id = 1717 
                    and c.banco_id = 19558201                    
                    and c.fechaBaja is null 
  order by fecha desc)
   WHERE rownum <=1
  ****************************************************************************************************


Mysql************************************************************************************************
  backup de base de datos*************************************************************************
     mysqldump --user=root --password=root acme > copia_seguridad.sql
*****************************************************************************************************