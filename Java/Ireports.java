****https://www.javatutoriales.com/2009/04/creacion-de-reportes-con-jasperrepots-y_17.html
***************************************************************************************Librerias a descargar
************************************************************************************************************
************************************************************************************************************
	commons-beanutils-1.8.0
	commons-collections-2.1.1
	commons-digester-1.7
	commons-javaflow-20060411
	commons-logging-1.0.4
	groovy-all-1.7.5
	iText-2.1.7
	jasperreports-3.7.6
	jasperreports-fonts-3.7.6
	jasperreports-javaflow-3.7.6
	poi-3.6
	png-encoder-1.5
	***Para graficos
	jcommon
	jfreechart

************************************************************************************************************



**************************************************************************************************Expresiones
************************************************************************************************************
************************************************************************************************************
	Existen 3 tipos de expresiones:

	Campos (fields) representados con "$F{nombre_campo}".
	Variables representadas por "$V{nombre_variable}".
	Parámetros representados por "$P{nombre_parámetro}".

	Los campos ("$F{}") le dicen al reporte dónde colocará los valores obtenidos a través del datasource. 
	Por ejemplo, nuestro objeto "Partcipante" tiene un atributo llamado "username". Usando una expresión 
	de campo indicamos en cuál parte o sección del reporte debe aparecer el valor de ese atributo usando "$F{username}". 
	Esto quedará más claro un poco más adelante.

	Los parámetros ("$P{}") son valores que usualmente se pasan al reporte directamente desde el programa que crea 
	el JasperPrint del reporte (en nuestra aplicación Java). Aunque también existen algunos parámetros internos que 
	podemos leer pero no modificar. Para hacer uso de estos parámetros simplemente indicamos el nombre del parámetro 
	en el lugar que queremos colocarlo. Pueden encontrar los nombres y significados de los parámetros internos en 
	la documentación de JasperReports.

	Las variables ("$V{}") son objetos usados para almacenar valores como los resultados de cálculos. Al igual que 
	con los parámetros, JasperReports tiene algunas variables internas que podemos leer. Pueden encontrar los nombres 
	y significados de las variables la documentación de JasperReports.

	Para terminar solo recuerden: los parámetros son expresiones que nos permiten pasar valores, desde nuestra 
	aplicación Java, a los reportes generador por JasperReports. Y las variables almacenan valores que son el 
	resultado de cálculos de distinta naturaleza sobre los valores que recibimos en nuestro reporte.
************************************************************************************************************





**************************************************************************************************Ver Paleta
************************************************************************************************************
************************************************************************************************************

	ver la ventana "Palette" presionen las teclas "Ctrl + Shift + 8"
************************************************************************************************************



*****************************************************************************************Creacion de Reporte
************************************************************************************************************
************************************************************************************************************
	public static void main(String[] args) throws Exception{
	        List listaPariticipantes = new ArrayList(); 

	        for (int i = 1; i <= 10; i++) 
	        { 
	            Participante p = new Participante(i, "Particpante " + i, "Usuario " + i, "Pass " + i, "Comentarios para " + i); 
	            listaPariticipantes.add(p); 
	        }  

	        //Creacion de report
	        JasperReport reporte = (JasperReport) JRLoader.loadObject("report1.jasper");  
	        //Imprimir en reporte
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(listaPariticipantes));  
	        
	        //Exportar el reporte
	        JRExporter exporter = new JRPdfExporter();  
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
	        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporte2PDF.pdf")); 
	        exporter.exportReport(); 
	    }
************************************************************************************************************



****************************************************Pasar valores parametro por medio de una aplicacion java
************************************************************************************************************
************************************************************************************************************
	Map<String, String> parametros = new HashMap<String, String>();
	parametros.put("autor", "Juan");
	parametros.put("titulo", "Reporte Participantes");

	JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(listaPariticipantes));          
************************************************************************************************************




***************************************************************************************************Variables
************************************************************************************************************
************************************************************************************************************
	***las variables son objetos usados para almacenar valores como los resultados de cálculos.

	***agregaremos la variable que realizará la suma de los puntos de todos los Participantes. Para esto 
	agregamos una nueva variable en el nodo "Variables" de la ventana "Report Inspector" haciendo clic 
	derecho sobre este nodo y seleccionando la opción "Add Variable" del menú contextual que se abre.         
************************************************************************************************************



*******************************************************************************************************Break
************************************************************************************************************
************************************************************************************************************
	Se agrega un break de la paleta y se con ¿figura en Print when expression del panel de propiedades del 
	elemento break

		($V{PAGE_COUNT}.intValue()==2) || $F{AFijar} || ($F{comentarios}.toString().length() > 50)
************************************************************************************************************




************************************************************Envio de reporte por un servlet hacia el cliente
************************************************************************************************************
************************************************************************************************************
	***Lo primero que haremos dentro de nuestro Servlet es indicar el tipo de contenido que regresaremos 
	(que por default es "text/html"). Como se trata de un archivo PDF el tipo de retorno es "application/pdf". 
	Indicamos esto con el método "setContentType" del objeto "HttpServletResponse" que recibimos como segundo 
	argumento, de la siguiente forma:

		response.setContentType("application/pdf");

	Este método se encarga de establecer las cabeceras adecuadas en la respuesta regresada al cliente para 
	que este sepa cómo tratar los datos que está recibiendo. En este caso esperamos que abra el reporte con 
	su aplicación habitual para leer archivos PDF (Acrobat Reader o algún otro). Es importante establecer 
	este valor antes de obtener el “ServletOutputStream”, de lo contrario podríamos obtener algún error 
	inesperado al enviar la respuesta.

	***Ahora que el Servlet ya conoce el tipo de respuesta que regresará podemos proceder a obtener el 
	“ServletOutputStream” correspondiente para enviar la respuesta al cliente:

		ServletOutputStream out = response.getOutputStream();

	***Ahora generamos el reporte como lo hemos estado haciendo hasta ahora, haciendo unos pequeños 
	cambios: como ahora el archivo de la plantilla del reporte no se encuentra en la raíz del proyecto, 
	sino dentro del directorio web, debemos indicar la ruta completa en la que se encuentra este archivo. 
	Por lo que es necesario cambiar la línea y obtener el contexto de la aplicacion 

	JasperReport reporte = (JasperReport) JRLoader.loadObject(getServletContext().getRealPath("WEB-INF/report1.jasper")); 

	*** Se envia directamente al cliente, debemos cambiar la línea: 

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);  

	En realidad la línea anterior es la que hace el truco. El flujo del reporte es dirigido al flujo que 
	es regresado al cliente, logrando así que este vea el reporte directamente en su navegador.

	***Para poder lograr esto es necesario agregar una cabera llamada "Content-Disposition" a la respuesta.
	 Esta cabecera tiene como valor "attachment; filename=" y el nombre que queremos darle a nuestro reporte. 
	 Para establecer esta cabecera usamos el método "setHeader" del objeto "HttpServletResponse", de la siguiente forma:

	 response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");


	 ***//Para evitar que nuestro navegador guarde el archivo en cache podemos establecer
        response.setHeader("Cache-Control","no-cache"); 
        response.setHeader("Pragma","no-cache");  
        response.setDateHeader ("Expires", 0);


    Ejemplo del servlet-----------------------------------------------------------------------------------------

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Para que el usuario tenga a disposicion la opcion si guardar el reporte o no
        response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
        //Para evitar que nuestro navegador guarde el archivo en cache podemos establecer
        response.setHeader("Cache-Control","no-cache"); 
        response.setHeader("Pragma","no-cache");  
        response.setDateHeader ("Expires", 0);
        
        response.setContentType("application/pdf");
        
        ServletOutputStream out = response.getOutputStream();
        
        List listaPariticipantes = new ArrayList();

        for (int i = 1; i <= 10; i++){
            Participante p = new Participante(i, "Particpante " + i, "Usuario " + i, "Pass " + i, "Comentarios para " + i);
            p.setPuntos(i);
            listaPariticipantes.add(p);
        }

        try{
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getServletContext().getRealPath("WEB-INF/report1.jasper"));

            Map parametros = new HashMap();
            parametros.put("autor", "Juan");
            parametros.put("titulo", "Reporte");

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(listaPariticipantes));

            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
************************************************************************************************************




*****************************************************************************************Graficas en Reporte
************************************************************************************************************
************************************************************************************************************
	*****Aplicacion Java
	***Se importan al proyecto los jar correspondientes jasperReports mas los siguientes:
		jcommon
		jfreechart

	*** Se crea entidad jugador con tres atributos id, nombre y consola

	*** Se crea main en ReporteGrafica.java donde se generan los datos del reporte
		public static void main(String[] args) {
        	List<Jugador> listaJugadores = new ArrayList<Jugador>();
	        for (int i = 1; i <= 49; i++) {       
	            listaJugadores.add(new Jugador(i, "Jugador " + i , "Wii"));   
	        }    

	        for(int i = 50; i <= 79; i++){       
	            listaJugadores.add(new Jugador(i, "Jugador " + i , "XBox"));   
	        }    

	        for(int i = 80; i <= 100; i++){       
	            listaJugadores.add(new Jugador(i, "Jugador " + i , "PS3"));   
	        }
	        JasperReport reporte = (JasperReport)JRLoader.loadObject("reporteGrafica.jasper");    
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(listaJugadores));     

	        JRExporter exporter = new JRPdfExporter();    
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);    
	        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File("reporte grafica.pdf"));     

	        exporter.exportReport(); 
    	}

    ****Aplicacion Ireports
    ***Se coloca el titulo y los encabezados en la franja que corresponde con texto estatico

    *** Se crean los fields id, nombre, consola y arrastran a la franja de detail

    ***Ahora vienen los pasos interesantes. Primero debemos crear un grupo. Un grupo nos permite tratar 
    de forma especial un conjunto de datos que se encuentran asociados (es como un group by en SQL). 
    Además, al crear un grupo se genera también una variable que mantiene el conteo de los elementos de 
    cada grupo (como si hicieramos un count sobre los elementos de los grupos en SQL).
    En este caso el grupo que nos interesa es el de las consolas.

    Lo único que debemos hacer es click derecho sobre el nodo "report name" (la raíz de los elementos del 
    proyecto) en el panel "Report Inspector". Con esto se abrirá un menú contextual. De este seleccionamos 
    el elemento "Add Report Group":

    En esta colocamos el nombre del grupo, en este caso será CONSOLAS, y en el 
    campo "Group by the following report object:" seleccionamos el field "consola" que creamos anteriormente. 
    Con esto conseguiremos que el nuevo grupo que se creará se haga con respecto al valor del atributo 
    "cosola" de nuestros objeto "Jugador".

    Presionamos el botón "Siguiente >" y se nos mostrarán dos radio buttons preguntando si quieremos 
    agregar la cabecera (header) y el pie de pagina (footer) del grupo. Nosotros deseleccionamos estas 
    opciones (se encuentran seleccionadas por default) ya que no nos interesa agregar ninguno de estos 
    elementos. Para terminar presionamos el botón "Finalizar" y ya tendremos agregado nuestro grupo

    No es muy obvio el que este grupo existe, de hecho los unicos indicadores que tenemos son que se han 
    agregado en la zona de las bandas del "Report Inspector" dos nuevas bandas: "CONSOLAS Group Header" 
    y "CONSOLAS Group Footer", además se ha agregado una variable llamada "CONSOLAS_COUNT", que es la 
    variable que nos interesa y por la que hemos creado el grupo, que mantendrá el número de jugadores 
    que tienen cada una de las consolas:

    Ahora agregaremos nuestra gráfica. Para eso arrastraremos desde la Paleta de elementos del reporte 
    un "Chart" y lo colocamos en la banda "Summary" (ajusten el tamaño de la banda para que la gráfica 
    se vea bien).

    Al arrastrar la gráfica se abrirá una nueva ventana preguntándonos qué tipo de gráfico queremos 
    mostrar. En este caso seleccionaremos el gráfico de pie o de pastel (la primer o segunda opción):

    Presionamos el botón "OK" y la gráfica se agregará a nuestro reporte, ajustenla para que abarque 
    toda el área de la banda.

    Lo último que haremos es configurar la gráfica para que obtenga los datos de los campos y variables que 
    nos interesa mostrar. Para esto, hacemos click derecho sobre la gráfica, con lo que se abrirá un 
    menú contextual. En este menú seleccionamos la opción "Chart Data".

    	Con lo que se abrirá una nueva ventana, y en esta ventana seleccionamos la pestaña "Details". 
    	En esta pestaña configuraremos tres valores:
			Key expression
			Value expression
			Label expression

		Nota: Dependiendo del tipo de gráfica que estemos haciendo puede que debamos agregar más valores, 
		pero para los gráficos de pie solo son necesarios estos.

		La "Key expression" dice cuál será la base que se usará para cada uno de las piezas de la gráfica. 
		En nuestro caso queremos que cada trozo muestre las consolas. Por lo que colocamos como valor 
		$F{consola}.

		La "Value expression" dice cuál será el tamaño de cada una de las piezas de la gráfica. Nosotros 
		queremos que cada pieza sea equivalente al número de consolas que se han comprado. Por lo que 
		colocamos como valor $V{CONSOLAS_COUNT}.

		Label expression es la etiqueta que se mostrará para cada valor. Nosotros queremos mostrar el 
		nombre de la consola junto con el número de unidades que se han vendido de cada una. Por lo que 
		colocamos como valor $F{consola} + " - " + $V{CONSOLAS_COUNT}. Lo cual colocará como valor el nombre 
		de la consola, concatenándole el signo " - " y concatenándole el número de consolas vendidas 
		(el número de jugadores que han comprado cada una de las consolas).

	Presionamos el botón "Close" y hemos terminado con nuestro reporte. Cambiamos a la vista "Preview"
	para compilar el reporte. No se nos mostrará nada del reporte final. De hecho los regresará a la vista 
	del "Designer" y mostrará una excepción en la ventana de salida indicando algo así: 
	"Key is null in pie dataset". Esto es normal ya que la gráfica no puede generarse ya que no existen 
	datos. Lo importante es que se haya generado el archivo "reporteGrafica.jasper".

	Ahora ejecutamos nuestra aplicación, con lo que debe generarse nuestro reporte en PDF 
************************************************************************************************************





****************************************************************************************Parte 7: Subreportes
************************************************************************************************************
************************************************************************************************************
	***Se crea la data para el reporte
		CREATE TABLE sga.`alumnos` (            
	                `ID` bigint(21) NOT NULL,               
	                `NOMBRE` varchar(100) NOT NULL,         
	                `CLAVE` varchar(100) NOT NULL,       
	                PRIMARY KEY  (`ID`)                     
	              ) ENGINE=InnoDB DEFAULT CHARSET=latin1
	              
		CREATE TABLE sga.`materias` (            
		                `ID` BIGINT(21) NOT NULL,               
		                `NOMBRE` VARCHAR(100) NOT NULL,         
		                PRIMARY KEY  (`ID`)                     
		              ) ENGINE=INNODB DEFAULT CHARSET=latin1
		              
		              
		CREATE TABLE sga.`alumnos_materias` (
		    `ALUMNO_ID` BIGINT(21) NOT NULL, 
		    `MATERIA_ID` BIGINT(21) NOT NULL, 
		     PRIMARY KEY (`ALUMNO_ID`, `MATERIA_ID`),
		    FOREIGN KEY (`ALUMNO_ID`) REFERENCES sga.`alumnos` (`ID`),
		    FOREIGN KEY (`MATERIA_ID`) REFERENCES sga.`materias` (`ID`) ) ENGINE=INNODB DEFAULT CHARSET=latin1alumnos_materias_ibfk_1
		    
		    
		    
		INSERT INTO sga.alumnos VALUES (1, 'Alumno 1', '00001');
		INSERT INTO sga.alumnos VALUES (2, 'Alumno 2', '00002');
		INSERT INTO sga.alumnos VALUES (3, 'Alumno 3', '00003');
		INSERT INTO sga.alumnos VALUES (4, 'Alumno 4', '00004');
		INSERT INTO sga.alumnos VALUES (5, 'Alumno 5', '00005');
		INSERT INTO sga.alumnos VALUES (6, 'Alumno 6', '00006');
		INSERT INTO sga.alumnos VALUES (7, 'Alumno 7', '00007');
		INSERT INTO sga.alumnos VALUES (8, 'Alumno 8', '00008');
		INSERT INTO sga.alumnos VALUES (9, 'Alumno 9', '00009');
		INSERT INTO sga.alumnos VALUES (10, 'Alumno 10', '000010');


		INSERT INTO sga.materias VALUES (1, 'Matematicas');
		INSERT INTO sga.materias VALUES (2, 'Fisica');
		INSERT INTO sga.materias VALUES (3, 'Quimica');
		INSERT INTO sga.materias VALUES (4, 'Biologia');
		INSERT INTO sga.materias VALUES (5, 'Historia');
		INSERT INTO sga.materias VALUES (6, 'Geografia');


		INSERT INTO sga.alumnos_materias VALUES (1, 1);
		INSERT INTO sga.alumnos_materias VALUES (1, 3);
		INSERT INTO sga.alumnos_materias VALUES (1, 5);

		INSERT INTO sga.alumnos_materias VALUES (2, 1);
		INSERT INTO sga.alumnos_materias VALUES (2, 2);
		INSERT INTO sga.alumnos_materias VALUES (2, 3);

		INSERT INTO sga.alumnos_materias VALUES (3, 2);
		INSERT INTO sga.alumnos_materias VALUES (3, 4);
		INSERT INTO sga.alumnos_materias VALUES (3, 6);

		INSERT INTO sga.alumnos_materias VALUES (4, 4);
		INSERT INTO sga.alumnos_materias VALUES (4, 5);
		INSERT INTO sga.alumnos_materias VALUES (4, 6);

		INSERT INTO sga.alumnos_materias VALUES (5, 1);
		INSERT INTO sga.alumnos_materias VALUES (5, 4);
		INSERT INTO sga.alumnos_materias VALUES (5, 5);

		INSERT INTO sga.alumnos_materias VALUES (6, 2);
		INSERT INTO sga.alumnos_materias VALUES (6, 5);
		INSERT INTO sga.alumnos_materias VALUES (6, 6);

		INSERT INTO sga.alumnos_materias VALUES (7, 1);
		INSERT INTO sga.alumnos_materias VALUES (7, 3);
		INSERT INTO sga.alumnos_materias VALUES (7, 5);

		INSERT INTO sga.alumnos_materias VALUES (8, 1);
		INSERT INTO sga.alumnos_materias VALUES (8, 2);
		INSERT INTO sga.alumnos_materias VALUES (8, 3);

		INSERT INTO sga.alumnos_materias VALUES (9, 2);
		INSERT INTO sga.alumnos_materias VALUES (9, 4);
		INSERT INTO sga.alumnos_materias VALUES (9, 6);

		INSERT INTO sga.alumnos_materias VALUES (10, 4);
		INSERT INTO sga.alumnos_materias VALUES (10, 5);
		INSERT INTO sga.alumnos_materias VALUES (10, 6);
	Como en esta ocasión tenemos una base de datos, usaremos el "Report Wizard", por lo que vamos al menú 
	"Archivo -> New.. -> Report Wizard". El paso 2 (en el que empieza el wizard) nos pide dar un nombre 
	al archivo del reporte y una ubicación.	Denle el nombre que gusten (en mi caso "reporteMaestro.jrxml") 
	y guardenlo en la carpeta raíz del proyecto de NetBeans que acabamos de crear. Presionamos el botón 
	"Siguiente >" para ir la paso 3. Aquí debenos seleccionar el Data Source que se usará para generar el 
	reporte. Si ya tienen creado el datasource para la base de datos "pruebaReportes" pueden seleccionarla 
	aqui. Sino no se preocupen, este es el momento para crearlo.

	Para crear el datasource que usaremos hacelos click en el botón "New":

	Esto abrirá una ventana en la que tendremos que seleccionar el tipo de Datasource que queremos usar.
	En nuestro caso será un "Database JDBC connection" (la primer opción).

	Presionamos el botón "Next >". En la siguiente pantalla debemos darle un nombre y algunos parámetros 
	al datasource, como la dirección del servidor y el nombre de la base de datos.
************************************************************************************************************

