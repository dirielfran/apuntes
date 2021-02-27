*************************************************************************************************************Elementos de un JSP
***Expresiones:
Sintaxis: <%= expresion %>
//Se evalua y se inserta en la salida del servlet, es equivalente al out.printl(expresion)
***Scriptles:
Sintaxis: <% codigoJava %>
//El codigo java se inserta en el metodo service() del servlet generado, puede ser cualquier codigo java
***Declaraciones:
Sintaxis: <%! codigoJava %>
//Se utiliza para agregar codigo a la clase del servlet generado, se pueden declara variables o metodos que pertenecen a la clase
***Sintaxis XML:
//Cada elemento JSP tiene su equivalente en sintaxis XML, se utiliza para tener una mayor compatibilidad


********************************************************************************************************************************



***********************************************************************************************************Directivas en un JSP
Atributo import
//Permite indicar que clases se importaran
Sintaxis: <%@page import="paquete.clase1, paquete.clase2" %>

Atributo contentType
//Se indi el tipo de respuesta a enviar al cliente
Sintaxis: <%@page contentType="MIME-Type" %>

Atributo session:
//Se puede especificar si se accede o no a los atributos de la session
Sintaxis:  <%@page session="true" %>

Atributo isELIgnored:
Deshabilita el manejo de expression Languaje
Sintaxis: <%@page isELIgnored="false" %>

Atributo buffer:
//Se especifica el tamaño en kilobytes que puede contener el buffer y al llegar se hace un flush
Sintaxis: <%@page buffer="tamañoenKb" %>

Atributo errorPage:
//Especifica el jsp que va a manejar una excepcion
Sintaxis: <%@page errorPage="url relativa al JSP de error" %>

Atributo isErrorPage:
//Se convierte en un jsp que puede recibir excepciones
Sintaxis: <%@page isErrorPage="true" %>

Atributo isThreadSafe:
//Especifica que el jsp es apto para para ser accedido por multihilos
<%@page isThreadSafe="true" %>

Atributo extends:
//Nos permite heredar de otra clase
Sintaxis: <%@page extends="paquete.NombreClase" %>

Ejemplo----------------------------------------------------------------
<%@page import="utilerias.Conversiones, java.util.Date"%>
<%@page contentType="application/vnd.ms-excel"%>
<%
	String nombreArchivo="reporte.xls";
	response.setHeader("Content-Disposition", "inline; filename="+nombreArchivo);
	%>
	<html>
		<head>
			<title>Reporte de Excel</title>
		</head>
		<body>
			<h1>Reporte de Excel</h1><br>
			<table border="1">
				<tr>
					<th>Curso</th>
					<th>Descripción</th>
					<th>Fecha Inicio</th>
				</tr>
				<tr>
					<td>1. Fudamentosde Java</td>
					<td>Aprenderemos la sintaxis básica de Java</td>
					<td><%= Conversiones.format( new Date() ) %></td>
				</tr>
				<tr>
					<td>2. Programación con Java</td>
					<td>Pondremos en práctica conceptos dela Programación Orientada a Objetos</td>
					<td><%= Conversiones.format( new Date() ) %></td>
				</tr>
			</table>
		</body>
	</html>

JSP de error-----------------------------------------------------------------------------------------
<%@page isErrorPage="true"%>
<%@ page import="java.io.*"%>
<html>
	<head>
		<metahttp-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>Manejo Errores</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/recursos/funciones.js"></script>
	</head>
	<body>
		<h1>Manejo de Errores</h1><br><br>
		Ocurri&oacute;un error:<%= exception.getMessage()%><br><br>
		<a href="<%=request.getContextPath()%>/index.html">Regresar al Inicio</a><br><br>
		<a href="#" onclick="cambio('mensaje1')">Ver detalles</a>
		<div id="mensaje1" style="position:relative;visibility: hidden">
			<PRE><% exception.printStackTrace(new PrintWriter(out)); %></PRE>
		</div>
	</body>
</html>
*******************************************************************************************************************************



*************************************************************************************************Nota: ***Variables Predefinidas
Un scriptets puede usar las siguientes variables predefinidas: 
	session 
	request 
	response
	out 
	in
	application(es equivalente al objeto ServletContext) 
Estas se utilizan igual que en los servlets pero no se declaran.
*******************************************************************************************************************************




***********************************************************************************Impresionde cadenas con diferentes tecnologia
<!--Impresionde cadenas con diferentes tecnologias-->
<ul>
	<li>Hola Mundo con HTML
	<li><%out.print("Hola Mundo con Scriptlets");%>
	<li>${"Hola Mundo con ExpressionLanguage(EL)"}
	<li><c:out value="Hola Mundo con JSTL" />
</ul>

----------------------------------------------------------------------------------



----------------------------------------------------------------------------------
<!--Algunos valores del lado del servidor --> utilizando expresiones imprime directamente al cliente--> <%=  expresion %>
<ul>
	<li>Hola: <%= new java.util.Date()%>
	<li>Nombre del Contexto de la aplicaci&oacute;n:
	<%= request.getContextPath() %>
	<li>Valor del par&aacute;metro x:
	<%= request.getParameter("x") %>
</ul>

Ejemplo--------------------------------------------------------------------------

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String fondo = request.getParameter("colorFondo");
	//Si no se envioun color, ponemos un color por default
	if(fondo == null|| fondo.trim().equals("")) {
		fondo = "white";
	}
	%>
	<html>
		<head>
			<metahttp-equiv="Content-Type" content="text/html;charset=UTF-8">
			<title>JSP Cambio de Color de Fondo</title>
		</head>
		<body bgcolor="<%=fondo%>">
			<h1>Fondo de color aplicado: <%= fondo%></h1>
			<br><a href="index.html">Regresar al Index</a>
		</body>
	</html>

--------------------------------------------------------------------------------declaracion de metos y variables
<%--Agregamos una declaracion--%>
<%!
	//Declaramos una variable con su metodoget
	private String usuario = "Alberto";
	public String getUsuario() {
		returnthis.usuario;
	}
	//Declaramos un contador de visitas
	private int contadorVisitas= 1;
	%>
	<!DOCTYPE html>
	<html>
		<head>
			<metahttp-equiv="Content-Type" content="text/html;charset=UTF-8">
			<title>Uso de Declaraciones</title>
		</head>
		<body>
			<h1>Uso de Declaraciones</h1>
			Usuario por medio del atributo: <%= this.usuario%><br>
			Usuario por medio del metodo: <%= this.getUsuario()%><br>
			Contador de Visitas desde que se reinicio el servidor:<%= this.contadorVisitas++%>
		</body>
	</html>

--------------------------------------------------------------------Ejemplo de un documento .jspx en formato XML
<?xmlversion="1.0" encoding="UTF-8"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
	<!--uso de declaraciones -->
	<jsp:declaration>
		private int contadorVisitas=1;
	</jsp:declaration>
	<html>
		<body bgcolor="yellow">
			<h1>Ejemplo de un Documento JSPx</h1>
			<!--Ejemplo de salida de texto -->
			<jsp:text>Saludos desde un documento JSP</jsp:text><br/>
			<!--Ejemplo de una expression-->Expresión Matemática: 
			<jsp:expression>2* 3</jsp:expression>
			<br/>
			<!--Ejemplo de un scriptlet-->
			<jsp:scriptlet>
				String nombreAplicacion=request.getContextPath();
			</jsp:scriptlet>
			Nombre Aplicación:
			<jsp:expression>nombreAplicacion</jsp:expression><br/>
			Contador de Visitas:
			<jsp:expression> this.contadorVisitas++ </jsp:expression>
		</body>
	</html>
</jsp:root>
********************************************************************************************************************************


