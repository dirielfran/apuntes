*****************************************************************************************************
Funciona, valida, modifica e inserta segun corresponda, de una tabla externa utiliza for each_record
*****************************************************************************************************
  *****Valida si hay creditos y modifica la oficina y la cuenta contable de cada creditos
        segun tabla externa, en caso de que no exista inserta el registro
  CREATE PROCEDURE qgplbanes.clientes4()                
  LANGUAGE SQL
  BEGIN
     DECLARE Credito CHARACTER(30);
     DECLARE ind INT;  
     FOR Each_Record AS Cursor1 CURSOR FOR
        select caracc from sif004cccp
     DO 
        SET Credito = caracc;
        SET ind=(SELECT count(*) from sif004hhhp WHERE CARACC = Credito);	
        IF (ind > 0) THEN 
          UPDATE b02trabajo.sif004hhhp
          SET (CAROFI,CARGLN) = (SELECT CAROFI, CARGLN 
                                  FROM sif004CCCP 
                                  where Caracc = Credito)
          where caracc = Credito;
        ELSE 
          INSERT INTO sif004hhhp
             SELECT * FROM sif004cccp b WHERE b.caracc = Credito;
        END IF;
     END FOR;                                                       
  END  

  000000000000000000000001138293

  DROP PROCEDURE  QGPLBANES.CLIENTES4
  delete from b02trabajo.sif004hhhp 
******************************************************************************************************




******************************************************************************************************
Funciona, valida, modifica e inserta segun corresponda, de una tabla externa utiliza for each_record
******************************************************************************************************
  CREATE PROCEDURE qgplbanes.clientes4()                
  LANGUAGE SQL
  BEGIN
     DECLARE Credito CHARACTER(30);
     DECLARE Cred zoned(20);
     DECLARE ind INT;  
     FOR Each_Record AS Cursor1 CURSOR FOR
        select NUCREDITO from b02trabajo.CAMN23AT04
     DO 
        SET Credito = CAST((lpad(NUCREDITO,'30','0')) AS CHAR(30));
        SET Cred = NUCREDITO; 
        SET ind=(SELECT count(*) 
                 from b02trabajo.sif004hhhp 
                 WHERE CARACC = CRedito);	
        IF (ind > 0) THEN 
          UPDATE b02trabajo.sif004hhhp
          SET (MTINEFECOB) = (SELECT MTINEFECOB 
                                  FROM b02trabajo.CAMN23AT04 
                                  where NUCREDITO = Credito)
          where caracc = Credito;
        END IF;
     END FOR;                                                         
  END  

     616990
  1,138,293
  2,060,711
  2,895,457
  3,252,864
  3,559,612
  3,637,885
  3,758,749
  3,998,451
  4,062,991
  4,139,503
  4,152,926
  4,770,420
  7,106,636

  CREATE PROCEDURE qgplbanes.clientes4()                         
  LANGUAGE SQL                                                   
  BEGIN                                                          
     DECLARE Credito numeric(20);                                
     DECLARE ind INT;                                            
     FOR Each_Record AS Cursor1 CURSOR FOR                       
        select NUCREDITO from b02trabajo.CAMN23AT04              
     DO                                                          
        SET Credito = NUCREDITO;                                 
        SET ind=(SELECT count(*)                                 
                 from b02trabajo.sif004hhhp                      
                 WHERE CARACC = CAST((lpad(Credito,'30','0')) AS 
  CHAR(30)));                                                    
        IF (ind > 0) THEN                                        
          UPDATE b02trabajo.sif004hhhp                           
          SET (MTINEFECOB) = (SELECT MTINEFECOB                  
                                  FROM b02trabajo.CAMN23AT04     
                                  where NUCREDITO = Credito)        
          where caracc = CAST((lpad(Credito,'30','0')) AS CHAR(30));
        END IF;                                                     
     END FOR;                                                       
  END                                                                                                                        
******************************************************************************************************





******************************************************************************************************
Funciona, valida, modifica e inserta segun corresponda, de una tabla externa utiliza un loop
******************************************************************************************************
  CREATE PROCEDURE qgplbanes.clientes3() 
  LANGUAGE SQL                           
  BEGIN                                  
     DECLARE SQLCODE INTEGER DEFAULT 0;  
     DECLARE Credito numeric(20);                 
     DECLARE ind INT;       
     DECLARE C1 CURSOR FOR               
        select NUCREDITO from b02trabajo.CAMN23AT04;                 
     OPEN C1;                            
  fetch_loop:                            
     LOOP                                
        FETCH c1 INTO Credito;        
        IF SQLCODE = 0 THEN              
           SET ind=(SELECT count(*)                                 
                 from b02trabajo.sif004hhhp                      
                 WHERE CARACC = CAST((lpad(Credito,'30','0')) AS 
                 CHAR(30)));
           IF (ind > 0) THEN                                        
             UPDATE b02trabajo.sif004hhhp                           
             SET (MTINEFECOB) = (SELECT MTINEFECOB                  
                                  FROM b02trabajo.CAMN23AT04     
                                  where NUCREDITO = Credito)        
             where caracc = CAST((lpad(Credito,'30','0')) AS CHAR(30));
           END IF;         
        ELSE                          
          LEAVE fetch_loop;           
        END IF;                       
     END LOOP fetch_loop;                
     CLOSE C1;                        
  END 
******************************************************************************************************





******************************************************************************************************
Funciona, valida, modifica e inserta segun corresponda, de una tabla externa utiliza un loop y codigos 
de sql
******************************************************************************************************

  Exec Sql Drop Procedure b02trabajo.copy4hB();        
  Exec Sql                                             
     CREATE PROCEDURE b02trabajo.copy4hB()             
     LANGUAGE SQL                                      
     BEGIN                                             
        DECLARE SQLCODE INTEGER DEFAULT 0;             
        DECLARE Credito CHARACTER(30);                 
        DECLARE ind INT;                               
        DECLARE C1 CURSOR FOR                          
          Select NCREDITO from B02TRABAJO.PRUEBA17;  
        OPEN C1;                                       
      fetch_loop:                                      
        LOOP                                           
          FETCH c1 INTO Credito;                       
          IF SQLCODE = 0 THEN                          
            SET ind=(SELECT count(*) 
                     from b02trabajo.sif004hhhp                       
                     WHERE CARACC = Credito;                          
                IF (ind > 0) THEN                                
                   UPDATE b02trabajo.sif004hhhp                  
                   SET MTINEFECOB = (SELECT INTERES FROM          
                     B02TRABAJO.PRUEBA17 WHERE NCREDITO= Credito) 
                   WHERE CARACC = Credito;                       
                END IF;                                          
              ELSE                                               
                 LEAVE fetch_loop;                               
              END IF;                                            
           END LOOP fetch_loop;                                  
           CLOSE C1;                                             
        END;                                                     
      Exec Sql call b02trabajo.copy4hB();  
******************************************************************************************************
             




******************************************************************************************************
  Exec Sql Drop Procedure b02trabajo.copyPunt();        
  Exec Sql                                             
     CREATE PROCEDURE b02trabajo.copyPunt()             
     LANGUAGE SQL                                      
     BEGIN                                             
        DECLARE SQLCODE INTEGER DEFAULT 0;             
        DECLARE Credito CHARACTER(30);                 
        DECLARE ind INT;                               
                                               
      fetch_loop:                                      
        LOOP                                           
                                    
            SET ind=(SELECT count(*) 
                     from b02trabajo.sif004hhhp                       
                     WHERE CARACC = Credito;                          
                IF (ind > 0) THEN                                
                   UPDATE b02trabajo.sif004hhhp                  
                   SET MTINEFECOB = (SELECT INTERES FROM          
                     B02TRABAJO.PRUEBA17 WHERE NCREDITO= Credito) 
                   WHERE CARACC = Credito;                       
                END IF;                                          
                                                        
           END LOOP fetch_loop;                                  
                                                        
        END;                                                     
      Exec Sql call b02trabajo.copy4hB();  
******************************************************************************************************




**********************************************************************************************funciona
  Begsr CopiaPrc;                                                  
     Exec Sql Drop Procedure b02trabajo.copy4C();                  
     Exec Sql                                                      
        CREATE PROCEDURE b02trabajo.copy4C()                       
        LANGUAGE SQL                                               
        BEGIN                                                      
           DECLARE SQLCODE INTEGER DEFAULT 0;                      
           DECLARE Credito numeric(20);                            
           DECLARE ind INT;                                        
           DECLARE C1 CURSOR FOR                                   
             select NUCREDITO from b02trabajo.CAMN23AT04;          
           OPEN C1;                                                
         fetch_loop:                                               
           LOOP                                                    
             FETCH c1 INTO Credito;                                
             IF SQLCODE = 0 THEN                                   
               SET ind=(SELECT count(*) 
                              from b02trabajo.sif004CCCC               
                 WHERE CARACC =                           
                 CAST((lpad(Credito,'30','0')) AS         
                 CHAR(30)));                              
               IF (ind > 0) THEN                                       
                 UPDATE b02trabajo.sif004CCCC                         
                 SET (MTRENDCORE,MTRENDCOAF,MTRENDCOLI,MTINEFECOB,    
                      POCOMIFLAT,MTCOMIFLAT,CDPEPAESCA,FECAMESCRE,    
                      FEREGVELCA,FEEXULCUPA,IDCTACOPRE,IDCTACOPRR,    
                      IDCTACOINC,MTINTCTAOR,TPINDUSTRI,TPBENEFMAN,    
                      TPBENEFTUR,NBBENEFESP,FEEMCEBEES,TPVIVIENDA,    
                      FEFIPEGRPI,MTCAPTRANS,FECAESCATR)               
                 = (SELECT MTRENDCORE,MTRENDCOAF,MTRENDCOLI,MTINEFECOB
                    ,POCOMIFLAT,MTCOMIFLAT,CDPEPAESCA,FECAMESCRE, 
                    FEREGVELCA,FEEXULCUPA,IDCTACOPRE,IDCTACOPRR, 
                    IDCTACOINC,MTINTCTAOR,TPINDUSTRI,TPBENEFMAN,
                          TPBENEFTUR,NBBENEFESP,FEEMCEBEES,TPVIVIENDA,  
                          FEFIPEGRPI,MTCAPTRANS,FECAESCATR              
                    FROM b02trabajo.CAMN23AT04                          
                    where NUCREDITO = Credito)                          
                  where caracc = CAST((lpad(Credito,'30','0')) AS       
                  CHAR(30));                                            
               END IF;                                                  
             ELSE                                                       
                LEAVE fetch_loop;                                       
             END IF;                                                    
          END LOOP fetch_loop;                                          
          CLOSE C1;                                                     
       END;                                                             
     Exec Sql call b02trabajo.copy4C();                                 
  Endsr;                                                                                            
******************************************************************************************************



**********************************************************************************************funciona
**************************************************************************************************Zeni
*********************************************************************************NrFacturasNoGeneradas
  create or replace FUNCTION         NROS_FACTURAS_NO_GENERADAS 
  RETURN VARCHAR2 
  IS
      nrosFacturas VARCHAR2(4000) default '<br/>';
      
      CURSOR C_TIPOS_COMP IS 
         SELECT id, abreviatura FROM tipo_comprobante WHERE abreviatura in ('FA','ND','NC');

      CURSOR C_NROS_FACT (TC_ID NUMBER, SUC NUMBER) IS 
        select min_a - 1 + level as nroFactura
        from ( select min(NROFACTURANUMERO) min_a, max(NROFACTURANUMERO) max_a
               from factura_producto
               where tipocomprobante_id = TC_ID
               and nrofacturasucursal = SUC
               and letra = 'A'
               and fechaalta >= to_date(to_char(sysdate - 1, 'dd/mm/yyyy'), 'dd/mm/yyyy')
               and fechaalta <= to_date(to_char(sysdate - 1, 'dd/mm/yyyy')||' 23:59:59', 'dd/mm/yyyy hh24:mi:ss')
        )
        connect by level <= max_a - min_a + 1
        minus
        select NROFACTURANUMERO
        from factura_producto
        where tipocomprobante_id = TC_ID
        and nrofacturasucursal = SUC
        and letra = 'A'
        and fechaalta >= to_date(to_char(sysdate - 1, 'dd/mm/yyyy'), 'dd/mm/yyyy')
        and fechaalta <= to_date(to_char(sysdate - 1, 'dd/mm/yyyy')||' 23:59:59', 'dd/mm/yyyy hh24:mi:ss');

  BEGIN
      FOR C_TIPO_COMP_CURSOR IN C_TIPOS_COMP LOOP
        FOR C_NROS_FACT_CURSOR IN C_NROS_FACT(C_TIPO_COMP_CURSOR.id,1) LOOP
          IF C_NROS_FACT_CURSOR.nroFactura IS NOT NULL THEN
            nrosFacturas := nrosFacturas || C_TIPO_COMP_CURSOR.abreviatura || ' 0001 ' || C_NROS_FACT_CURSOR.nroFactura || '<br/>';
          END IF;
        END LOOP;
        FOR C_NROS_FACT_CURSOR IN C_NROS_FACT(C_TIPO_COMP_CURSOR.id,2) LOOP
          IF C_NROS_FACT_CURSOR.nroFactura IS NOT NULL THEN
            nrosFacturas := nrosFacturas || C_TIPO_COMP_CURSOR.abreviatura || ' 0002 ' || C_NROS_FACT_CURSOR.nroFactura || '<br/>';
          END IF;
        END LOOP;
        FOR C_NROS_FACT_CURSOR IN C_NROS_FACT(C_TIPO_COMP_CURSOR.id,101) LOOP
          IF C_NROS_FACT_CURSOR.nroFactura IS NOT NULL THEN
            nrosFacturas := nrosFacturas || C_TIPO_COMP_CURSOR.abreviatura || ' 0101 ' || C_NROS_FACT_CURSOR.nroFactura || '<br/>';
          END IF;
        END LOOP;
        FOR C_NROS_FACT_CURSOR IN C_NROS_FACT(C_TIPO_COMP_CURSOR.id,102) LOOP
          IF C_NROS_FACT_CURSOR.nroFactura IS NOT NULL THEN
            nrosFacturas := nrosFacturas || C_TIPO_COMP_CURSOR.abreviatura || ' 0102 ' || C_NROS_FACT_CURSOR.nroFactura || '<br/>';
          END IF;
        END LOOP;
      END LOOP;
      RETURN nrosFacturas;
  EXCEPTION
     WHEN VALUE_ERROR THEN  -- handles 'division by zero' error
       RETURN nrosFacturas  || ' Y MAS!';
  END NROS_FACTURAS_NO_GENERADAS;
******************************************************************************************************
