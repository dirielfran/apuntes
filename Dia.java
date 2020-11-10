	Instalacion de maven y jdk   					--> Exitosa
	Instalacion de plugins       					--> Exitosa
	Checkout del proyecto y manejo de dependencias			--> Revisar error
	Instalación de Eclipse						--> Revisar en JSP Index Manager
	Ejecucion y depuracion de la aplicacion				--> Zeni Debug
	Configuracion de base de Datos					--> Listo



*****************************************************************************************Extencion
	Mi ext: 					5160
	Maribel:					5165
	GONZALEZ HERNAN (D)			5130		RRHH
	Antonella Spezia 			5136  		Mercaderia
	oscar                                   skype: oskarher
**************************************************************************************************




********************************************************************Compilacion de aplicativo Zeni
	Compilar el proyecto
	Abrir una consola DOS y posicionarse en el directorio C:\workspace\Zeni\core (SOLO SOBRE EL CORE)
	y ejecutar el siguiente comando:
	mvn install -Dmaven.test.skip=true
	***Cuendo no levante la aplicacion pararse en l carpeta zeni y por comando
	mvn clan --> borra los target
	Al finalizar debe decir BUILD SUCCESSFUL
**************************************************************************************************
4247531

20629480421

**************************************************************************************Comandos dos
	*****Para abrir archivos desde el dos
		@ECHO OFF
		start %windir%\explorer.exe \\10.2.0.243\Documentacion\DOC. CLIENTES
**************************************************************************************************





***************************************************************************************Directorio
	\\10.2.0.242\Software\SIC
	http://sweetdev-ria.sourceforge.net/3.5.2.1/ -->Libreria
	http://otrs/otrs/index.pl? 		--> OTRS	
	WIKI: http://10.2.11.202/zeni/index.php/Wiki

	AMBIENTES:
	LOCAL: http://10.2.1.60:9090/zeni/login.jsp
	TESTING: http://10.2.1.60:9090/zeni/login.jsp
	PRODUCCIÓN: http://10.2.1.60:8080/zeni/mainMenu.html

	NOVEDADES:
	http://10.2.11.202/novedades/index.php/Novedades (Ver correos adjuntos ‘Portal de Novedades del 
	SIC’)
	Por otro tipo de documentación:
	svn://10.2.0.201/zeni/trunk/documentos técnicos

	Levantar aplicacion en nuestro navegador
	http://localhost:8080/zeni

	Internos:
	http://10.2.90.246:100/ABM_Internos.aspx

	Contraseña de inicio VNC: zeni
	Contraseña VNC : t0mc4t

	Sentencia para password en appZeni:
	update app_user set PASSWORD = 'b103d5a5c94bdf1c93350498eec02699d1688652' WHERE username = 'eareiza'

	Base de Dato:
	-->Produccion: 
	IP:10.2.111.107 
	Port: 1521
	Servicio: zscdb
	usuario: zeniapp
	Contraseña: zeniapp

	-->Desarrollo
	IP:10.2.51.139 
	Port: 1521
	Servicio: APPTEST
	usuario: apptest
	Contraseña: apptest

	pin correo : 1182

	***Alicacion simax
	http://10.2.0.20/SimaxTecnicos/

	0005-81221996

**************************************************************************************************





**************************************************************************Documentacion Importante
	-->C:\workspace\Zeni\documentos tecnicos\
	SicZeni
	Agenda de Notificaciones
	CRUD
	Diseño de Interfaces con el Usuario
	Estandar de Interfases Graficas
	Esquema de Seguridad 
	Reportes JasperReports
	Validadores
	Tablas Referencia SICZeni
	Arquitectura del framework
	crudGenerar Release
	guia subversion(eclipse)
	guia subversion(linea de comando)
	Infraestructura de la implementacion
	Instalacion de ambiente Windows
	nueva Implementacion de consultas
**************************************************************************************************




Fuente********************************************************************************************
	La configuracion de menues se especifica en las dos componentes que se mensionan a continuación:

	•	Comportamiento: /zeni-webapp/src/main/webapp/common/menu.jsp
	Describe el aspecto y el comportamiento de la componente de administración del menu.

	•	Configuración: /zeni-webapp/src/main/webapp/WEB-INF/menu-config.xml
	Definición de todas las opciones disponibles de la “barra” y de las subopciones de su “cortina”, 
	junto con la especificación de rol correspondiente.

	***roles y decidir la visibilidad de las opciones
	El atributo “permissions” nombra la componente java que tiene la responsabilidad de analizar el 
	esquema de roles y decidir la visibilidad de las opciones:
	/zeni-webapp/src/main/java/com/zeni/app/webapp/util/RolesAndGrantsAdapter.java
**************************************************************************************************



*********************************************************************Cambio de servidor de la base
	se reemplaza en el POM.xml
	<profile>
        <id>oracleproduccionnuevo</id>
        <properties>
                            <app.config>PRODUNUEVO</app.config>
                                            <company.name>NUEVO SERVIDOR DE PRODUCCION.</company.name>
            <dbunit.dataTypeFactoryName>org.dbunit.ext.oracle.OracleDataTypeFactory</dbunit.dataTypeFactoryName>
            <dbunit.schema>ZENIAPP</dbunit.schema> <!-- Make sure to capitalize the schema name -->
            <hibernate.dialect>org.hibernate.dialect.Oracle9Dialect</hibernate.dialect>
            <jdbc.groupId>oracle</jdbc.groupId>
            <jdbc.artifactId>ojdbc6</jdbc.artifactId>
            <jdbc.version>11.2.0.1.0</jdbc.version>
            <jdbc.driverClassName>oracle.jdbc.OracleDriver</jdbc.driverClassName>
            <jdbc.url><![CDATA[jdbc:oracle:thin:@(DESCRIPTION =(CONNECT_TIMEOUT=10)(RETRY_COUNT=3)(ADDRESS = (PROTOCOL = TCP)(HOST = 10.2.70.30)(PORT = 1521))(LOAD_BALANCE = YES)(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = ZSCDB)(FAILOVER_MODE = (TYPE = SELECT)(METHOD = BASIC)(RETRIES = 180)(DELAY = 5))))]]></jdbc.url>
            <jdbc.username>zeniapp</jdbc.username>
            <jdbc.password>zeniapp</jdbc.password>
        </properties>
    </profile>

**************************************************************************************************


Atajos Eclipse************************************************************************************
	CTRL + MAY + R	Acceso a la búsqueda de recursos
	CTRL + H	Carga la ventana de búsqueda de todo el sistema
	CTRL + G	Gerarquia
	CTRL + MAY + T	Buscador de tipos en el workspace
	CTRL + E	Acceso a los ficheros que ya están abiertos
	CTRL + O	Acceso a los atributos y métodos de esa clase
	CTRL + O 	(2 veces)	Igual que el anterior pero añadiendo los atributos y métodos de 
			las clases padre
	CTRL + L	Acceso a la línea indicada
	CTRL + K	Rastrea la variable seleccionada
	CTRL + F	Buscar / Reemplazar una palabra
	CTRL + D	Eliminar la fila
	CTRL + SUPR	Eliminar la siguiente palabra
	CTRL + RETRO	Eliminar la anterior palabra
	CTRL + MAY + SUPR	Eliminar hasta el final
	CTRL + Q	Volver a la anterior pestaña de edición
	CTRL + 3	Cargador de vistas
	CTRL + MAY + F	Formatea el texto según lo configurado
	CTRL + MAY + S	Guarda todos los documentos abiertos
	CTRL + MAY + O	Organizador de imports (añadiéndolos si faltan)
	CTRL + 7	Comenta el texto seleccionado
**************************************************************************************************




******************************************************************************************Personal
	DIRECTV
	ELSA KATIUSKA MORENO SOTO
	Número de Cliente 96477038
	DNI 95826765

	Alias de cuenta
	 Editar
	 
	Email
	dirielfran@gmail.com
	 Editar
	 
	Teléfonos de contacto
	011-1550265700 / 011-1550044136
	 Editar
	 
	Domicilio de instalación
	3 SANTIAGO DEL ESTERO 16 CAP FED CAPITAL FEDERAL 1075
	 ?
	 
	Domicilio de facturación
	SANTIAGO DEL ESTERO 724 3 CABA CAPITAL FEDERAL 16 1075
	 Editar
**************************************************************************************************




 Generacion de archivo sin ctg - sistema stop*****************************************************
 	Se debe ijecutar con java 7 instalado en la maquina
 	desde el eclipse apuntando a Produccion
 	menu de sic --> Comercial- Cupos en stop
 *************************************************************************************************

import com.zeni.app.util.JexlNS.JexlModelNS;

 	JexlModelNS model = new JexlModelNS();


 	<td align="left" valign="top"><s:select key="dependencia" id="rubro1" name="rubro1" 
		list="new com.zeni.app.model.ConceptoRubroCaliada().metodoDeInstancia()"  
		required="false" emptyOption="true" label="Rubro" cssClass="text medium"  />  
</td>



<tr> 
 												<td colspan="2"><s:checkbox id="cbYPF" key="" 
 														cssClass="checkbox" theme="simple" /> <s:label 
 														for="cbYPF" 
 														value="YPF"  
 														cssClass="choice desc" theme="simple" /> 
 												</td> 
 												<td valign="middle" ><input type='button'
 														style="width: 50px; margin-right: 5px; margin-left: auto; margin-right: auto; height: 25px; display: none; " 
 														class="button" id="btnAgregar" onclick="javascript:onYPF();" value="<fmt:message key="button.ca.add"/>"	 /> 
 												</td> 
 											</tr>
 											<tr id="sectorYPF" style="visibility: hidden"> 
 													<td align="left" valign="top"><s:select key="dependencia" id="rubro1" name="rubro1" 
 															list="lista"  
 															required="false" emptyOption="true" label="Rubro" cssClass="text medium"  />  
 													</td> 
 													<td><s:textfield id="porcRub1" label="% Rebaja" 
 															key="" required="false" 
 															cssClass="currency size50"  
 															onchange="javascript:formatXDecimales(this,2);calcularKgBonif()" /> 
 													</td> 
 													<td><s:textfield id="medRub1"  label="Medicion" 
 															key="" required="false"
 															cssClass="currency size50" /> 
 													</td> 
 													<td><s:textfield id="kgRub1" label="Kg. Rebaja." 
 															key="" required="false" 
															cssClass="currency size50" readonly="false"/> 
													</td>
 											</tr>