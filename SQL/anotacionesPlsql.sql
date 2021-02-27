
****************************************************************************************Referencia
	https://www.tutorialspoint.com/plsql
**************************************************************************************************


**********************************************************************************************Loop
	DECLARE 
	   i number(1); 
	   j number(1); 
	BEGIN 
	   << outer_loop >> 
	   FOR i IN 1..3 LOOP 
	      << inner_loop >> 
	      FOR j IN 1..3 LOOP 
	         dbms_output.put_line('i is: '|| i || ' and j is: ' || j); 
	      END loop inner_loop; 
	   END loop outer_loop; 
	END; 
	/
***************************************************************************************************




********************************************************************************Variables de cadena

	DECLARE 
   	name varchar2(20); 
   	company varchar2(30); 
   	introduction clob; 
   	choice char(1); 
	BEGIN 
	   name := 'John Smith'; 
	   company := 'Infotech'; 
	   introduction := ' Hello! I''m John Smith from Infotech.'; 
	   choice := 'y'; 
	   IF choice = 'y' THEN 
	      dbms_output.put_line(name); 
	      dbms_output.put_line(company); 
	      dbms_output.put_line(introduction); 
	   END IF; 
	END; 
	/

	*********************************************************************************
		DECLARE 
		   greetings varchar2(11) := 'hello world'; 
		BEGIN 
		   dbms_output.put_line(UPPER(greetings)); 
		    
		   dbms_output.put_line(LOWER(greetings)); 
		    /*Devuelve la primera letra mayuscula*/
		   dbms_output.put_line(INITCAP(greetings)); 
		    
		   /* retrieve the first character in the string */ 
		   dbms_output.put_line ( SUBSTR (greetings, 1, 1)); 
		    
		   /* retrieve the last character in the string */ 
		   dbms_output.put_line ( SUBSTR (greetings, -1, 1)); 
		    
		   /* retrieve five characters,  
		      starting from the seventh position. */ 
		   dbms_output.put_line ( SUBSTR (greetings, 7, 5)); 
		    
		   /* retrieve the remainder of the string, 
		      starting from the second position. */ 
		   dbms_output.put_line ( SUBSTR (greetings, 2)); 
		     
		   /* find the location of the first "e" */ 
		   dbms_output.put_line ( INSTR (greetings, 'e')); 
		END; 
		/ 
		/////////////////////////////////////////////////////////////////////
		HELLO WORLD
		hello world
		Hello World
		h
		d
		world
		ello world
		2
***************************************************************************************************




*******************************************************************************************Arreglos
	DECLARE 
   type namesarray IS VARRAY(5) OF VARCHAR2(10); 
   type grades IS VARRAY(5) OF INTEGER; 
   names namesarray; 
   marks grades; 
   total integer; 
	BEGIN 
	   names := namesarray('Kavita', 'Pritam', 'Ayan', 'Rishav', 'Aziz'); 
	   marks:= grades(98, 97, 78, 87, 92); 
	   total := names.count; 
	   dbms_output.put_line('Total '|| total || ' Students'); 
	   FOR i in 1 .. total LOOP 
	      dbms_output.put_line('Student: ' || names(i) || ' 
	      Marks: ' || marks(i)); 
	   END LOOP; 
	END; 
	/

	************************************************************************************
		DECLARE 
	   CURSOR c_customers is 
	    SELECT  name FROM customers; 
	   type c_list is varray (6) of customers.name%type; 
	   name_list c_list := c_list(); 
	   counter integer :=0; 
		BEGIN 
		   FOR n IN c_customers LOOP 
		      counter := counter + 1; 
		      name_list.extend; 
		      name_list(counter)  := n.name; 
		      dbms_output.put_line('Customer('||counter ||'):'||name_list(counter)); 
		   END LOOP; 
		END; 
		/
***************************************************************************************************





***************************************************************************Procedimeinto Almacenado
	CREATE [OR REPLACE] PROCEDURE procedure_name 
	[(parameter_name [IN | OUT | IN OUT] type [, ...])] 
	{IS | AS} 
	BEGIN 
	  < procedure_body > 
	END procedure_name; 
***************************************************************************************************





********************************************************************************************Funcion 
	CREATE [OR REPLACE] FUNCTION function_name 
	[(parameter_name [IN | OUT | IN OUT] type [, ...])] 
	RETURN return_datatype 
	{IS | AS} 
	BEGIN 
	   < function_body > 
	END [function_name];
***************************************************************************************************





**************************************************************************Eliminar un Procedimiento

	DROP PROCEDURE procedure-name; 
***************************************************************************************************





*******************************************************************************************Cursores
	******Tipos de cursores:
		Cursores implícitos
		Cursores explícitos


	*****Cursores implícitos
	Oracle crea automáticamente los cursores implícitos cuando se ejecuta una instrucción SQL, 
	cuando no hay un cursor explícito para la instrucción. Los programadores no pueden controlar 
	los cursores implícitos y la información que contienen.

	Cada vez que se emite una declaración DML (INSERT, UPDATE y DELETE), se asocia un cursor 
	implícito con esta declaración.

	In PL/SQL, puedes hacer referencia al mas reciente cursor implicito por medio de los atributos 
	%FOUND, %ISOPEN, %NOTFOUND, and %ROWCOUNT. Se accederá a cualquier atributo de cursor SQL 
	como sql% attribute_name

	%FOUND
	Devuelve VERDADERO si una instrucción INSERT, UPDATE o DELETE afectó una o más filas o una 
	instrucción SELECT INTO devolvió una o más filas. De lo contrario, devuelve FALSO.

	%NOTFOUND
	El opuesto lógico de% FOUND

	%ISOPEN
	Devuelve true si el cursor esta abierto

	%ROWCOUNT
	Devuelve la cantidad de filas afectadas.

		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		DECLARE  
		   total_rows number(2); 
		BEGIN 
		   UPDATE customers 
		   SET salary = salary + 500; 
		   IF sql%notfound THEN 
		      dbms_output.put_line('no customers selected'); 
		   ELSIF sql%found THEN 
		      total_rows := sql%rowcount;
		      dbms_output.put_line( total_rows || ' customers selected '); 
		   END IF;  
		END; 
		/    

	*******************************************************************************************
	*****Cursores explicitos
	Los cursores explícitos son cursores definidos por el programador para obtener más control 
	sobre el área de contexto . Se debe definir un cursor explícito en la sección de declaración 
	del Bloque PL / SQL. Se crea en una instrucción SELECT que devuelve más de una fila.

	La sintaxis para crear un cursor explícito es - 
		CURSOR cursor_name IS select_statement; 
	
	Trabajar con un cursor explícito incluye los siguientes pasos:

		***Declarando el cursor para inicializar la memoria.
			CURSOR c_customers IS 
   				SELECT id, name, address FROM customers; 
		***Apertura del cursor para asignar la memoria.
			OPEN c_customers; 
		***Recuperando el cursor para recuperar los datos.
			FETCH c_customers INTO c_id, c_name, c_addr; 
		***Cerrar el cursor para liberar la memoria asignada.
			CLOSE c_customers;
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			DECLARE 
			   c_id customers.id%type; 
			   c_name customerS.Name%type; 
			   c_addr customers.address%type; 
			   CURSOR c_customers is 
			      SELECT id, name, address FROM customers; 
			BEGIN 
			   OPEN c_customers; 
			   LOOP 
			   FETCH c_customers into c_id, c_name, c_addr; 
			      EXIT WHEN c_customers%notfound; 
			      dbms_output.put_line(c_id || ' ' || c_name || ' ' || c_addr); 
			   END LOOP; 
			   CLOSE c_customers; 
			END; 
			/
***************************************************************************************************




******************************************************************************************Registros
	*************************************************************Registros basados ​​en tablas
	El atributo %ROWTYPE permite a un programador crear registros basados ​​en tablas y 
	en cursores.

		+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			DECLARE 
			   customer_rec customers%rowtype;  --Se declara el registro basado en la tabla de BD
			BEGIN 
			   SELECT * into customer_rec 
			   FROM customers 
			   WHERE id = 5;  --Se crean los registros
			   dbms_output.put_line('Customer ID: ' || customer_rec.id); 
			   dbms_output.put_line('Customer Name: ' || customer_rec.name); 
			   dbms_output.put_line('Customer Address: ' || customer_rec.address); 
			   dbms_output.put_line('Customer Salary: ' || customer_rec.salary); 
			END; 
			/
	*************************************************************Registros basados ​​en Cursor
	**********************************************Cuando la consulta genera varios registros
		+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			DECLARE 
			   CURSOR customer_cur is 
			      SELECT id, name, address  
			      FROM customers; 
			   customer_rec customer_cur%rowtype; 
			BEGIN 
			   OPEN customer_cur; 
			   LOOP 
			      FETCH customer_cur into customer_rec; 
			      EXIT WHEN customer_cur%notfound; 
			      DBMS_OUTPUT.put_line(customer_rec.id || ' ' || customer_rec.name); 
			   END LOOP; 
			END; 
			/
	*******************************************************Registros definidos por el usuario
	PL / SQL proporciona un tipo de registro definido por el usuario que le permite definir 
	las diferentes estructuras de registro. Estos registros constan de diferentes campos.

		DECLARE 
		TYPE books IS RECORD 
		(title  varchar(50), 
		   author  varchar(50), 
		   subject varchar(100), 
		   book_id   number); 
		book1 books; 
		book2 books; 
		+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		DECLARE 
		   type books is record 
		      (title varchar(50), 
		      author varchar(50), 
		      subject varchar(100), 
		      book_id number); 
		   book1 books; 
		   book2 books; 
		BEGIN 
		   -- Book 1 specification 
		   book1.title  := 'C Programming'; 
		   book1.author := 'Nuha Ali ';  
		   book1.subject := 'C Programming Tutorial'; 
		   book1.book_id := 6495407;  
		   -- Book 2 specification 
		   book2.title := 'Telecom Billing'; 
		   book2.author := 'Zara Ali'; 
		   book2.subject := 'Telecom Billing Tutorial'; 
		   book2.book_id := 6495700;  
		  
		  -- Print book 1 record 
		   dbms_output.put_line('Book 1 title : '|| book1.title); 
		   dbms_output.put_line('Book 1 author : '|| book1.author); 
		   dbms_output.put_line('Book 1 subject : '|| book1.subject); 
		   dbms_output.put_line('Book 1 book_id : ' || book1.book_id); 
		   
		   -- Print book 2 record 
		   dbms_output.put_line('Book 2 title : '|| book2.title); 
		   dbms_output.put_line('Book 2 author : '|| book2.author); 
		   dbms_output.put_line('Book 2 subject : '|| book2.subject); 
		   dbms_output.put_line('Book 2 book_id : '|| book2.book_id); 
		END; 
		/

	***********************************************Registros como parámetros de subprograma
	Puede pasar un registro como un parámetro de subprograma al igual que pasa cualquier 
	otra variable. También puede acceder a los campos de registro de la misma manera que 
	accedió en el ejemplo anterior:

		
		+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		DECLARE 
		   type books is record 
		      (title  varchar(50), 
		      author  varchar(50), 
		      subject varchar(100), 
		      book_id   number); 
		   book1 books; 
		   book2 books;  
		PROCEDURE printbook (book books) IS 
		BEGIN 
		   dbms_output.put_line ('Book  title :  ' || book.title); 
		   dbms_output.put_line('Book  author : ' || book.author); 
		   dbms_output.put_line( 'Book  subject : ' || book.subject); 
		   dbms_output.put_line( 'Book book_id : ' || book.book_id); 
		END; 
		   
		BEGIN 
		   -- Book 1 specification 
		   book1.title  := 'C Programming'; 
		   book1.author := 'Nuha Ali ';  
		   book1.subject := 'C Programming Tutorial'; 
		   book1.book_id := 6495407;
		   
		   -- Book 2 specification 
		   book2.title := 'Telecom Billing'; 
		   book2.author := 'Zara Ali'; 
		   book2.subject := 'Telecom Billing Tutorial'; 
		   book2.book_id := 6495700;  
		   
		   -- Use procedure to print book info 
		   printbook(book1); 
		   printbook(book2); 
		END; 
		/  
***************************************************************************************************




****************************************************************************************Excepciones
	Tipos de excepciones:
		Excepciones definidas por el sistema
		Excepciones definidas por el usuario

	DECLARE 
	   <declarations section> 
	BEGIN 
	   <executable command(s)> 
	EXCEPTION 
	   <exception handling goes here > 
	   WHEN exception1 THEN  
	      exception1-handling-statements  
	   WHEN exception2  THEN  
	      exception2-handling-statements  
	   WHEN exception3 THEN  
	      exception3-handling-statements 
	   ........ 
	   WHEN others THEN 
	      exception3-handling-statements 
	END;
	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		DECLARE 
		   c_id customers.id%type := 8; 
		   c_name customerS.Name%type; 
		   c_addr customers.address%type; 
		BEGIN 
		   SELECT  name, address INTO  c_name, c_addr 
		   FROM customers 
		   WHERE id = c_id;  
		   DBMS_OUTPUT.PUT_LINE ('Name: '||  c_name); 
		   DBMS_OUTPUT.PUT_LINE ('Address: ' || c_addr); 

		EXCEPTION 
		   WHEN no_data_found THEN 
		      dbms_output.put_line('no hay clientes!'); 
		   WHEN others THEN 
		      dbms_output.put_line('Error!'); 
		END; 
		/


	****************************************************Excepciones definidas por el usuario
	*****Sintaxis:
		DECLARE my-exception EXCEPTION; 

	    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    DECLARE 
		   c_id customers.id%type := &cc_id; 
		   c_name customerS.Name%type; 
		   c_addr customers.address%type;  
		   -- user defined exception 
		   ex_invalid_id  EXCEPTION; 
		BEGIN 
		   IF c_id <= 0 THEN 
		      RAISE ex_invalid_id; 
		   ELSE 
		      SELECT  name, address INTO  c_name, c_addr 
		      FROM customers 
		      WHERE id = c_id;
		      DBMS_OUTPUT.PUT_LINE ('Name: '||  c_name);    --Para solicitar que el usuario de entrada
		      DBMS_OUTPUT.PUT_LINE ('Address: ' || c_addr); 
		   END IF; 

		EXCEPTION 
		   WHEN ex_invalid_id THEN 
		      dbms_output.put_line('ID must be greater than zero!'); 
		   WHEN no_data_found THEN 
		      dbms_output.put_line('No such customer!'); 
		   WHEN others THEN 
		      dbms_output.put_line('Error!');  
		END; 
		/
***************************************************************************************************





****************************************************************************************Triggers
	En este capítulo, discutiremos los Desencadenadores en PL / SQL. Los disparadores son programas 
	almacenados, que se ejecutan o disparan automáticamente cuando ocurren algunos eventos. De hecho, 
	los disparadores están escritos para ser ejecutados en respuesta a cualquiera de los siguientes 
	eventos:

	Una declaración de manipulación de base de datos (DML) (DELETE, INSERT o UPDATE)

	Una declaración de definición de base de datos (DDL) (CREATE, ALTER o DROP).

	Una operación de base de datos (SERVERERROR, LOGON, LOGOFF, STARTUP o SHUTDOWN).

	*****Sintaxis
		CREATE [OR REPLACE ] TRIGGER trigger_name {
			BEFORE | AFTER | INSTEAD OF }  
			{INSERT [OR] | UPDATE [OR] | DELETE}  
			[OF col_name]  
		ON table_name  
			[REFERENCING OLD AS o NEW AS n]  
			[FOR EACH ROW]  
		WHEN (condition)   
			DECLARE 
			   Declaration-statements 
		BEGIN  
			   Executable-statements 
			EXCEPTION 
			   Exception-handling-statements 
		END; 

		+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		CREATE OR REPLACE TRIGGER display_salary_changes 
		BEFORE DELETE OR INSERT OR UPDATE ON customers 
		FOR EACH ROW 
		WHEN (NEW.ID > 0) 
		DECLARE 
		   sal_diff number; 
		BEGIN 
		   sal_diff := :NEW.salary  - :OLD.salary; 
		   dbms_output.put_line('Old salary: ' || :OLD.salary); 
		   dbms_output.put_line('New salary: ' || :NEW.salary); 
		   dbms_output.put_line('Salary difference: ' || sal_diff); 
		END; 
		/ 
***************************************************************************************************





*******************************************************************************************Package
	Los paquetes son objetos de esquema que agrupan tipos, variables y subprogramas de PL / SQL 
	relacionados de forma lógica.

	Un paquete tendrá dos partes obligatorias:

		Especificación del paquete
		Cuerpo o definición del paquete

	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	*****La especificación del paquete
		CREATE OR REPLACE PACKAGE c_package AS 
		   -- Adds a customer 
		   PROCEDURE addCustomer(c_id   customers.id%type, 
		   c_name  customerS.No.ame%type, 
		   c_age  customers.age%type, 
		   c_addr customers.address%type,  
		   c_sal  customers.salary%type); 
		   
		   -- Removes a customer 
		   PROCEDURE delCustomer(c_id  customers.id%TYPE); 
		   --Lists all customers 
		   PROCEDURE listCustomer; 
		  
		END c_package; 
		/

		********************************************************************************
	*****Creación del cuerpo del paquete
		CREATE OR REPLACE PACKAGE BODY c_package AS 
			-- Adds a customer
		   PROCEDURE addCustomer(c_id  customers.id%type, 
		      c_name customerS.No.ame%type, 
		      c_age  customers.age%type, 
		      c_addr  customers.address%type,  
		      c_sal   customers.salary%type) 
		   IS 
		   BEGIN 
		      INSERT INTO customers (id,name,age,address,salary) 
		         VALUES(c_id, c_name, c_age, c_addr, c_sal); 
		   END addCustomer; 
		   
		   -- Removes a customer 
		   PROCEDURE delCustomer(c_id   customers.id%type) IS 
		   BEGIN 
		      DELETE FROM customers 
		      WHERE id = c_id; 
		   END delCustomer;  
		   
		   --Lists all customers
		   PROCEDURE listCustomer IS 
			   CURSOR c_customers is 
			      SELECT  name FROM customers; 
			   TYPE c_list is TABLE OF customers.Name%type; 
			   name_list c_list := c_list(); 
			   counter integer :=0; 
			   BEGIN 
			      FOR n IN c_customers LOOP 
			      counter := counter +1; 
			      name_list.extend; 
			      name_list(counter) := n.name; 
			      dbms_output.put_line('Customer(' ||counter|| ')'||name_list(counter)); 
			    END LOOP; 
		   END listCustomer;
		   
		END c_package; 
		/
		********************************************************************************
	******Ejecucion
		DECLARE 
		   code customers.id%type:= 8; 
		BEGIN 
		   c_package.addcustomer(7, 'Rajnish', 25, 'Chennai', 3500); 
		   c_package.addcustomer(8, 'Subham', 32, 'Delhi', 7500); 
		   c_package.listcustomer; 
		   c_package.delcustomer(code); 
		   c_package.listcustomer; 
		END; 
		/  
***************************************************************************************************




****************************************************************************************Colecciones
	PL / SQL proporciona tres tipos de colección -

		Tablas de indexación o matriz asociativa
		Mesa anidada
		Matriz de tamaño variable o Varray


	*****Índice por tabla
	Una tabla de índice (también llamada matriz asociativa ) es un conjunto de pares clave-valor. 
	Cada clave es única y se utiliza para localizar el valor correspondiente. La clave puede ser 
	un entero o una cadena.
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			Ejemplo
			El siguiente ejemplo muestra cómo crear una tabla para almacenar valores enteros 
			junto con los nombres y luego imprime la misma lista de nombres.

			DECLARE 
			   TYPE salary IS TABLE OF NUMBER INDEX BY VARCHAR2(20); 
			   salary_list salary; 
			   name   VARCHAR2(20); 
			BEGIN 
			   -- adding elements to the table 
			   salary_list('Rajnish') := 62000; 
			   salary_list('Minakshi') := 75000; 
			   salary_list('Martin') := 100000; 
			   salary_list('James') := 78000;  
			   
			   -- printing the table 
			   name := salary_list.FIRST; 
			   WHILE name IS NOT null LOOP 
			      dbms_output.put_line ('Salary of ' || name || ' is ' || TO_CHAR(salary_list(name))); 
			      name := salary_list.NEXT(name); 
			   END LOOP; 
			END; 
			/
			++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			DECLARE 
			   CURSOR c_customers is 
			      select name from customers; 

			   TYPE c_list IS TABLE of customers.Name%type INDEX BY binary_integer; 
			   name_list c_list; 
			   counter integer :=0; 
			BEGIN 
			   FOR n IN c_customers LOOP 
			      counter := counter +1; 
			      name_list(counter) := n.name; 
			      dbms_output.put_line('Customer('||counter||'):'||name_lis t(counter)); 
			   END LOOP; 
			END; 
			/ 

	*****************************************************************************************
	*****Tablas anidadas
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		DECLARE 
		   TYPE names_table IS TABLE OF VARCHAR2(10); 
		   TYPE grades IS TABLE OF INTEGER;  
		   names names_table; 
		   marks grades; 
		   total integer; 
		BEGIN 
		   names := names_table('Kavita', 'Pritam', 'Ayan', 'Rishav', 'Aziz'); 
		   marks:= grades(98, 97, 78, 87, 92); 
		   total := names.count; 
		   dbms_output.put_line('Total '|| total || ' Students'); 
		   FOR i IN 1 .. total LOOP 
		      dbms_output.put_line('Student:'||names(i)||', Marks:' || marks(i)); 
		   end loop; 
		END; 
		/  
		+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		DECLARE 
		   CURSOR c_customers is  
		      SELECT  name FROM customers;  
		   TYPE c_list IS TABLE of customerS.Name%type; 
		   name_list c_list := c_list(); 
		   counter integer :=0; 
		BEGIN 
		   FOR n IN c_customers LOOP 
		      counter := counter +1; 
		      name_list.extend; 
		      name_list(counter)  := n.name; 
		      dbms_output.put_line('Customer('||counter||'):'||name_list(counter)); 
		   END LOOP; 
		END; 
		/ 

***************************************************************************************************

