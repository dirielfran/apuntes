**********************************************************************************************************
	Existen los siguientes selectores en CSS, por un lado tenemos los selectores que se
	conocen como ID y por otro lado los que se conocen como Class.

	Los selectores de tipo ID nos van a permitir aplicar un estilo definido para un elemento
	de manera única en la página web actual, por lo que se deben aplicar solo a un elemento
	en nuestra página web. Para definir un selector ID se utiliza el signo de numero # esto
	para definir el estilo, pero para poder utilizarlo dentro de nuestro elemento HMTL se
	debe de utilizar el atributo ID del elemento HTML. Más adelante veremos un ejemplo de
	este tipo de estilos.

	Por otro lado tenemos el selector CLASS. Este tipo de selectores se utilizan para
	especificar el estilo de un grupo de elementos. Para definir una selector css de tipo
	CLASS se utiliza el operador punto (.) y para utilizar estos selectores utilizamos el atributo
	class en el elemento HTML donde queramos aplicar este estilo. De igual manera veremos
	un ejemplo de cómo aplicar estos estilos CSS.*
**********************************************************************************************************




**********************************************************************************************************
*******************************************************************************EstilosVariosConPrioridad 1

	<body style="background:#ffffcc; color:#551100; font-family:Comic Sans MS;">
		<h1>Ejemplo CSS 1</h1>
		<p style="font-family:ArialBlack;">Este es el contenido del 
			<span style="font-size:xx-large;">parrafo</span>1
		</p>
		<br>
		<p style="color:#ff0000;">Este es el contenido del parrafo2</p>
		<br> 
		<p style="color:#f8d077">Este es el contenido del parrafo3</p>
	</body>
**********************************************************************************************************




**********************************************************************************************************
**********************************************************************************EstilosVariasPrioridades
	estilos.css-----------------------------------------------------------------------------------------
	/*Se definen las propiedades de estilos.Prioridad 3: Los estilos definidos en un archivo aplican 
	despues que los estilos definidos en el documento HTML y tambien despues de los elementos definidos 
	en un elemento HTML*/
	body{
		background:#c0c0c0;
		color:#000080;
		font-family:"Century Gothic";
	}
	----------------------------------------------------------------------------------------------------
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="estilos.css">
		<!--Prioridad 2: este es el segundo el prioridad -->
		<style>
			<!--body{
					font-family:"Bodoni MT Black"; 
					color:#ff0000; 
				}-->
		</style>
		<title>CSS Ejemplo 2</title>
	</head>
	<!--el tag de body hereda las propiedades del archivo estilos.css -->
	<body>
		<h1>CSS Ejemplo 2</h1>
		<!--Prioridad 1:estilo mas interno-->
		<p style="font-family:AgencyFB">Este es un parrafode ejemplo</p>
	</body>
**********************************************************************************************************



**********************************************************************************************************
***********************************************************************************EstilosPrElementoYClase
	estilos.css----------------------------------------------------------------------------------------
		/*Agregamos ESTILOS POR elemento html*/
		body{
			background:#256F5B;
			font-family:"Arial Narrow";
			color:white;
		}
		h1{
			font-size:xx-large;
			font-style:italic;
		}
		p{
			font-weight:bolder;
			background:#468B78;
		}
		/*Agregamos una nueva clase aplicable a cualquier elemento html*/
		.resaltado{
			background:#9E354A;
			font-weight:bold;
		}
		.nuevaClaseCss{
			font-family:"Century Gothic";
			font-weight:bold;
			background:#4F0010;
		}
	---------------------------------------------------------------------------------------------------

	<html>
		<head>
			<meta charset="UTF-8">
			<link rel="stylesheet" type="text/css" href="estilos.css">
			<title>CSS Ejemplo 3</title>
		</head>
		<body>
			<h1>CSS Ejemplo 3</h1>
			<p>P&aacute;rrafode Ejemplo 1</p>
			<p>P&aacute;rrafode Ejemplo 2</p>
			<div class="resaltado">Agregamos una nueva divisi&oacute;n</div>
			<br>
			<div class="nuevaClaseCss">Agregamos una segunda divisi&oacute;n</div>
		</body>
	</html>
**********************************************************************************************************
