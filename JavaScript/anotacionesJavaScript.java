tutorial --> http://expertojava.ua.es/experto/restringido/2014-15/js/js.html#_trabajando_con_el_dom

******************************************************************************************************
**********************************************************************Invocacion de archivo.js externo
******************************************************************************************************
	<head>
		<scripttype="text/javascript" src="funciones.js"></script>
	</head>	
******************************************************************************************************



******************************************************************************************************
*****************************************************************************************Consultar Api
******************************************************************************************************
		<%@taglib uri="/struts-tags" prefix="s" %>
		<html>
			<head>
				<title><s:text name="form.titulo" /></title>
				<style>
		        #posts { display: flex; flex-wrap: wrap }
		        .card { padding: 0.5rem; box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.3); transition: all 0.3s ease; width: 250px; margin: 1rem;}
		        .card:hover{transform: scale(1.1)}
		    </style>
			</head>
			<body>
				<section id="posts"></section>
		    	<script type="text/javascript">
		    	//Tomo el obj por el id
		    	let section_posts = document.querySelector("#posts")
		    	let principal = document.querySelector("#main")
		    	//Coloco url en variable
		    	let url = "https://jsonplaceholder.typicode.com"
		    	let select = document.createElement("select")
		    	//Creo un objeto XMLHttpRequest
		    	let xhr = new XMLHttpRequest
		    	//Are la solicitud
		    	xhr.open("GET",`https://jsonplaceholder.typicode.com/users`)
		    	//Evento load
		    	xhr.addEventListener("load",()=>{
		    		 //Valida es status
		    	    if(xhr.status == 200){
		    	    	//Almacena 
				        let usuarios = JSON.parse(xhr.response)
				        console.log(usuarios)
				        
				         usuarios.forEach(usuario=>{
				            let opt = document.createElement("option")
				            opt.innerText = usuario.name
				            opt.value = usuario.id
				            select.appendChild(opt)
				        })
				       principal.appendChild(select)
				       
				       select.addEventListener("change",e=>{
				            //Se blanquea el section
				            section_posts.innerHTML = "" 
			                //console.log(select.value)
			                //se agrega en variable el id
			                let userId = e.target.value
			                console.log(userId)
			                console.log(${userId})
			                //Se realiza una segunda consulta a la API
				            let xhr_posts = new XMLHttpRequest
				            xhr_posts.open("get",`https://jsonplaceholder.typicode.com/posts?userId=${userId}`+userId)
				            xhr_posts.addEventListener("load",()=>{
				            	if(xhr_posts.status == 200){
				                    //Se crea arreglo con el response
				                    let posts = JSON.parse(xhr_posts.response)
				                    console.log(posts)
				                    //Se crea segmento
				                    let f = document.createDocumentFragment()
				                    //Se recorre el arreglo
				                    posts.forEach(post=>{
				                        let div = document.createElement("div")
				                        let h2 = document.createElement("h2")
				                        let p = document.createElement("p")
				
				                        div.className = "card"
				                        h2.innerText = post.title
				                        p.innerText = post.body
				
				                        div.appendChild(h2)
				                        div.appendChild(p)
				                        f.appendChild(div)
				                    })
				                    section_posts.appendChild(f)
				            	}
				            })
				            xhr_posts.send()
				       })
				       document.body.insertBefore(select,section_posts)
		    	    }
		    	})
		    	xhr.send() 
		    	</script> 
			</body>
		</html>
******************************************************************************************************

******************************************************************************************************
***********************************************************************Invocacion de js interno en pag
******************************************************************************************************
	<script type="text/javascript">
		alert("Hola Mundo");
		document.write("<h1>HolaMundo desde JavaScript</h1>");
	</script>
******************************************************************************************************




******************************************************************************************************
*******************************************************Ejecucion de funciones en la carga de la pagina
******************************************************************************************************
	
	<body onload="saluda();escribeMensaje();">
	---------------------------------------------------------------------------------------------
	window.onload = cargarImagenes;
******************************************************************************************************




******************************************************************************************************
******************************************************************************Alert, Cuadro de Dialogo
******************************************************************************************************
	function saluda(){
		alert("Hola Mundo");
	}
******************************************************************************************************




******************************************************************************************************
**********************************************************************Escritura sobre un elemento html
******************************************************************************************************
	function escribeMensaje(){
		document.getElementById("mensajeHtml").innerHTML="Saludo desde archivo de JavaScript";
	}
	---------------------------------------------------------------------------------------------
	<h1 id="mensajeHtml"></h1>
******************************************************************************************************




******************************************************************************************************
**************************************************************************Funciones Basicas JavaScript
******************************************************************************************************
	********************************************************************************index.html
	<body>
		<h1>Ejercicio JavaScript2</h1>

		<a id="linkSearch" href="http://www.google.com.mx">Buscar en google</a>
	</body>
	******************************************************************************funciones.js
	******************************************************LLamado de funcion al cargar pagina
		window.onload=iniciaDatos;
	*******************************************************Evento onclick de un elemento HTML
		/**
		* Funcion que se manda llamar
		* al cargar la pagina HTML, al hacer click en un elemento */
		function iniciaDatos(){
			document.getElementById("link").onclick=validaSalir;
			document.getElementById("linkSearch").onclick=busqueda;
		}
	*************************************************************************Elemento confirm
		/**
		* Funcion que valida si el usuario quiere salir del sitio o no
		*/
		function validaSalir(){
			if(confirm("Desea salir del sitio?")){
				alert("Nos vamos a google");
				return true;
			//regresamos verdadero para salir
			}else{
				alert("Nos quedamos en el sitio");
				return false;
				//regresamos falso para quedarnos 
			}
		}
	**************************************************************************Elemento prompt
		/**
		* Funcion que pide una cadena a buscar en google
		*/
		function busqueda(){
			//Con la funcion prompt capturamos informacion del usuario 
			var respuesta =prompt("Escribe la cadena a buscar:","");
			//si hubo una respuesta concatenamos la cadena a buscar
			//al link de google
			if(respuesta){
				alert("Tu respuesta fue:"+respuesta);
				//el operador this nos sirve para referenciar 
				//al elemento que provoco el evento, en este caso
				//el elemento con identificador "linkSearch"
				//y concatenamos la respuesta como un parametro
				//de una peticionget
				var nuevoLink=this+"search?q="+respuesta;
				alert("Nuevo link:"+nuevoLink);
				//redireccionamos el link
				window.location=nuevoLink;
				//regresamos false, sino nos lleva al link originalmente
				//registrado en el elemento "linkSearch"
				returnfalse;
			}else{
				alert("No proporcionaste ninguna cadena a buscar");
				returnfalse;
			}
		}
******************************************************************************************************




******************************************************************************************************
****************************Funciones Carga De ElementoHTML y Cambio de Caracteristicas de un Elemento 
******************************************************************************************************
	Descripcion: Se cargan todas las imagenes con la funcion cargarImagenes() y se filtra por la que 
	tenga como padre un elemento <a> 
		function cargarImagenes() {
			for (var i = 0; i < document.images.length; i++) {
				if (document.images[i].parentNode.tagName == "A") {
					configuraRollover(document.images[i]);
				}
			}
		}
	----------------------------------------Funcion Cambio de caracteristicas de un elemento html
		function configuraRollover(imagen) {
			imagen.imagenOff = new Image();
			imagen.imagenOff.src = "images/boton_off.jpg";
			imagen.onmouseout = cambiaOff;
			imagen.imagenOn = new Image();
			imagen.imagenOn.src = "images/boton_on.jpg";
			imagen.onmouseover = cambiaOn;
		}
	---------------------------------------------------------------------------------------------
	/**
	* Estas funciones se ejecutan segun el evento que se dispare
	* pero no es al iniciar la pagina, sino dependen del
	* boton que se presione, son conocidas como handlers
	*/
	//Se asocio al evento onmouseout
	function cambiaOff() {
		this.src = this.imagenOff.src;//tomamos los valores ya asociados
	}
	//Se asocio al evento onmouseover
	function cambiaOn() {
		this.src = this.imagenOn.src;//tomamos los valores ya asociados
	}
******************************************************************************************************




******************************************************************************************************
******************************Funciones Carga De ElementoHTML y Llamado de Funcion al Cambiar Elemento 
********************************************************Añadir a variable el Valor de un Elemento HTML
******************************************Redireccionar a una Pagina Segun el valor de u elemento HTML
******************************************************************************************************
	HTML--------------------------------------------------------------------------------------
		<select id="selectFaq">
			<option selected>Selecciona un FAQ</option>
			<option value="javaFaq.html">Java FAQ</option>
			<option value="netFaq.html">.Net FAQ</option>
			<option value="phpFaq.html">PHP FAQ</option>
		</select>
	JavaScript-------------------------------------------------------------------------------
		window.onload = configuraSelect;
		function configuraSelect() {
			//Este es el elemento seleccionado por default, se le añade indice al elemento
			document.getElementById("selectFaq").selectedIndex = 0;
			//LLamado de funcion al cabio del estado del elemento html
			document.getElementById("selectFaq").onchange = cambiaPagina;
		}

		function cambiaPagina() {
			var elementoSelect = document.getElementById("selectFaq");
			//Se añade a variable valor de un elemento
			var nuevaPagina = elementoSelect.options[elementoSelect.selectedIndex].value;
			//Se Redirecciona a una Pagina Segun el valor de un elemento HTML
			if (nuevaPagina != "") {
				window.location = nuevaPagina;
			}
		}
******************************************************************************************************



******************************************************************************************************
***************************************************************************************Libreria Jquery 
*******************************************************************Añade un elemento de forma Dinamica
******************************************************************Se incluye libreria Jquery desde CDN
******************************************************************************************************
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<!-- Se incluye la librerias de Jquery desde cdn-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<title>Insert title here</title>
		<script >
			//Se añade funcion de Jquery para que añada elementos de forma dinamica
			$(document).ready(function (){
				$("#btnAgregar").click(function(){
					var num = $("input[type=text]").length;
					var numeroSiguiente = num + 1;
					var elementoNuevo = $("#valor" + num).clone().attr('id',
					'valor'+numeroSiguiente).attr("name", "valor"+numeroSiguiente);
					var etiquetaNueva = $("label[for=valor"+num+"]").clone().attr("for",
					"valor"+numeroSiguiente).text("Valor " + numeroSiguiente + ": ");
					$("#valor" + num).after(elementoNuevo);
					elementoNuevo.before(etiquetaNueva);
					etiquetaNueva.before("<br />");
				});
			});
		</script>
	</head>
	<body>
		<s:form action="multiparametros" id="formulario" theme="simple">
			<s:label for="valor1" value="Valor 1: " />
			<s:textfield id="valor1" name="valor1" /><br />
			<s:submit value="Enviar" />
		</s:form>
		<button id="btnAgregar">Agregar Elemento</button>
	</body>
******************************************************************************************************



******************************************************************************************************
*******************************************************************Funcion Ajax para request asyncrono 
*****************************************************************Redireccion a pagina segun validacion
************************************************************************Pase de parametros a un Action
******************************************************************************************************
	function onFacturarAut(){
		var grilla = SweetDevRia.$("procesoMFCaucionpGrid");
		var comprobantes = grilla.getCheckedRows();
		
		if(!confirm("Está seguro de los datos ingresados para facturar?"))
	 		return;
		
		if (comprobantes.length > 0) {
			new Ajax.Request('<c:url value="/simularComprobante.html"/>',
					  { parameters: {					  
							comprobantesSeleccionados: comprobantes.toString(),
							facturacion: 'true'
						  },
					    onComplete: function(transport, json) {
					    	var msg = getValidValue(json.msg);
							if(msg != null)	alert(msg);
					    	// Limpiamos la seleccion de la grilla
					    	grilla.checkedRows = [];
					    	// refrescamos la grilla para que recargue
					    	SweetDevRia_asyncRefreshGrid('comprobantesMFCaucionGrid');
					    	displayElement("facturarAutButton", json.habilitarFacturacion);
					    	if(json.lote != null){
					    		var url = '<c:url value="/listarComprobantesLote.html?nroLote='+ json.lote + '"/>';
								location.href = url;
					    	}				    				    	
					    }
					  });
		} else {
			alert("No hay comprobantes seleccionados");
		}
	}
******************************************************************************************************



******************************************************************************************************
***************************************************************++*Funcion para dar formato a decimales
	function formatoImporteJS(pImporte){
		if(!pImporte || isNaN(pImporte.toString().toFloat()))
			pImporte='0';
		return pImporte.toString().toFloat().toFixed(2).toString();
	}
******************************************************************************************************


******************************************************************************************************
********************************************************************Limpiar cajas de texto al recargar
	<script type="text/javascript">	
		window.onload=iniciaDatos;
		function iniciaDatos(){
			document.getElementById("contrato1").value="";
		}

	</script>
******************************************************************************************************



******************************************************************************************************
****************************************************************Funcion para hacer visible un elemento
	***Cambia caracteristicas de estilo de un elemento
	function hideUnHideFecha(obj){
		elemento = document.getElementById('div_fechaMuestraAgrupada');
		if(obj.checked) 
			elemento.style.visibility = 'visible';
		else {
			elemento.style.visibility = 'hidden';
		}	
	}
	----------------------------------------------------------------------------------------------
	function validar(){	 
		if(!fileValid){
			alert("Debe seleccionar un archivo para importar !!");
			return fileValid;
		}else{
			var tipoAlerta = document.getElementById("validacion");	
		    tipoAlerta.value = 'true';
		    displayElement("actualizar", true);	
		    displayElement("simularButton", false);	
		    var obj = document.getElementById("actualizar");    
		    if (obj){
		       obj.click();   
		    }
		}	
	}
******************************************************************************************************



******************************************************************************************************
**********************************************************Funcion para cambiar el valor de un elemento
	function iniciaDatos(){
		var tipoAlerta = document.getElementById("validacion");
		tipoAlerta.value = 'false';
		alert(tipoAlerta.value);
	}
******************************************************************************************************




******************************************************************************************************
***********************************************************Funcion que formatea el valor de un decimal
	Se modifico el metodo javascript onChangeField()
	function onChangeField(field){
		var d = field.value.toFloat();
		field.value = d.format(3);
		calculaDependientes();
	}
******************************************************************************************************




******************************************************************************************************
*******************************************************Funcion javascript que valida por medio de jstl
	function confirmarBorrado(){
		<c:choose>
		    <c:when test='${tieneFactura && tieneReconsideracion}'>
		   		alert("No se puede eliminar Certificado Original. Posee Reconsideraci\u00F3n \n"+ "El certificado no se puede eliminar porque fue facturado. Facturas: ${numerosFactura}");
		   		return false;
		    </c:when>
			<c:when test='${tieneFactura}'>
				alert("El certificado no se puede eliminar porque fue facturado. Facturas: ${numerosFactura}");
				return false;
		    </c:when>
		    <c:when test='${tieneReconsideracion}'>
				alert("No se puede eliminar Certificado Original. Posee Reconsideraci\u00F3n");
				return false;
		    </c:when>
		    <c:otherwise>
		    	return confirmDelete('AnalisisMuestra');
		    </c:otherwise>
		</c:choose>
	}
******************************************************************************************************





******************************************************************************************************
****************************************************Funcion javascript que utiliza expression Languaje
	function confirmarSave(){
		if('${anticipoFinanciero.id}')
			return confirm('<fmt:message key="anticipoFinanciero.confirmarModificacion"/>');
		else
			return confirm('<fmt:message key="anticipoFinanciero.confirmarAlta"/>');
	}
	function confirmarAnulacion(){
			return confirm('<fmt:message key="anticipoFinanciero.confirmarAnulacion"/>');
	}
******************************************************************************************************



******************************************************************************************************
*****************************************************Funcion javascript valida por jstl y crea funcion
***************************************Se utiliza confirm para tipos de respuesta diferentes a boolean
	<c:if test="${empty contrato.id}">
		<script type="text/javascript">
			function confirmMail() {
					$N("contrato.enviarConfirmacionMailAvisos").value = confirm("¿Desea enviar Emails de Nuevo Depósito?") ? "SI" : "NO";
			}
		</script>		
	</c:if>
	<c:if test="${not empty contrato.id}">
		<script type="text/javascript">
			function confirmMail() {
			}
		</script>		
	</c:if>
******************************************************************************************************




******************************************************************************************************
*********************************************************************Imput valida por medio de confirm
	<input type="button" style="margin-right: 5px" class="veryLargeButton" 
        onclick="if(confirm('¿Enviar informe?'))location.href='<c:url value="informeContratoDepositoAbierto.html"/>';"
        value="Enviar Informe"/>
******************************************************************************************************




******************************************************************************************************
Curso JavaScript**************************************************************************************
******************************************************************************************Links Utiles
	//link para descarga de las clases
	https://tinyurl.com/uk42eyf

	//link para descarga de libro javascript
	tinyurl.com/y4s45u3

	//Descarga de cursos gratis
	cursosmegaup.bid
*****************************************************************************************************



*****************************************************************************************Cosumir API REST
	//url de la API a la que se va a consultar
	let url = "https://jsonplaceholder.typicode.com"


	let xhr = new XMLHttpRequest
	xhr.open("GET",`${url}/users`)
	xhr.addEventListener("load",()=>{
	    if(xhr.status == 200){
	        let respuesta = JSON.parse(xhr.response)
	        console.table(respuesta)
	    }
	})
	xhr.send()
*********************************************************************************************************




***********************************************************Descarga de raimbowbrakets para Visual Code 
	
	raimbowbrakets, pone de diferentes colores los breakers para una mejor visualizacion
******************************************************************************************************


********************************************************************************Loupe - Philip Roberts
	Loupe. Intro. Loupe is a little visualisation to help you understand how JavaScript 
	call stack/event loop/callback queue interact with each other. 
******************************************************************************************************


*******************************************************************************createDocumentFragment()	
	/**Un fragmento actua como una etiqueta pero no lo es
	DocumentFragment son Nodos del DOM que nunca forman parte del DOM tree. 
	El caso de uso mas comun es crear un document fragment, agregar elementos 
	al document fragment y luego agregar el document fragment al DOM tree. En 
	el DOM tree, el document fragment es remplazado por todos sus hijos.

	Dado que el document fragment es generado en memoria y no como parte del DOM tree, 
	agregar elementos al mismo no causan reflow (computo de la posicion y geometria 
	de los elementos) en la pagina. Como consecuencia, usar document fragments 
	usualmente resultan en mejor performance.*/

	let f = document.createDocumentFragment()
******************************************************************************************************


*****************************************************************************************removeChild()
********************************************************************************************parentNode
	//con el parentNode localizas el padre y con removeChild
	let p
	p.parentNode.removeChild(p)
******************************************************************************************************


*****************************************************************Interpolacion de cadenas de variables
	//Interpolacion de adenas de variables
	let a = 1
	let b = 2

	let c = " resultado : "+ a + ", " + b
	let d = ' resultado : ${a} , ${b} '
******************************************************************************************************


***********************************************************************************************Foreach
	items.forEach(e=>{
        //6) Crear un nuevo select con tantos option como elementos haya en el array 
        let opt = document.createElement("option")
        opt.value = e
        opt.innerText = e
        nuevo_select.appendChild(opt)
    })
   	--------------------------------------------------------------------------------------------
   	let arr = [1,2,3,4]
   	//Array.forEach(callback Function) :void
	arr.forEach((elemento,i)=>{
	    console.log(elemento,i)
	})
******************************************************************************************************



******************************************************************************Funcion Map para arreglo
	let arr = [1,2,3,4]
	//Array.map(callback Function) => Array
	//Le suma un numero a cada item del array
	arr.map(e=>e + 1)
	arr.map((e)=>{ return e + 1 })
******************************************************************************************************



*****************************************************************************Funcion filter para array
	//Filtra segun una condicion
	//Array.filter(callback Function) => Array
	arr.filter(e=>e%2==0)
	arr.filter(e=>e%2==0 ? true : false)
	arr.filter((e)=>{ 
	    if(e%2==0){
	        return true
	    }else{
	        return false
	    }
	 })
*******************************************************************************************************



**************************************************************************************Funcion reduce()
	//La funcion reduce tiene dos parametros, el primero es el acumulador y el segundo es el que sumara al acumulado
	//Array.reduce(callback Function [, start]) => Any
	let arr_reduce = [1,2,3,4]

	arr_reduce.reduce((i,s)=>i+s,50)

	arr_reduce.reduce((inicial,siguiente)=>inicial + siguiente)

	arr_reduce.reduce((inicial,siguiente)=>{
	    return inicial + siguiente
	})
******************************************************************************************************




*********************************************************************************************Expresion
	//Expresion
	let a = item > 3 ? 2 : 4
******************************************************************************************************


**************************************************************************************addEventListener
	//keydown - keyup - keypress - change - focus - blur

	categorias.addEventListener("change",e=>{
	    console.log(e.target.value)
	})
*****************************************************************************************************




************************************************************************************Datos del profesor
	//horacio.estevez@gmail.com
	//argent.os
******************************************************************************************************
***********************************************************************************************Clase 2
Creacion, seleccion, remove, modificacion de objetos del dom*******************************************
******************************************************************************************************
********************************************************************************Ejercicio 1 Clase 2
	Ejercicio.html---------------------------------------------------------------------------
		<!DOCTYPE html>
		<html lang="en">
		<head>
		    <meta charset="UTF-8">
		    <title>Document</title>
		</head>
		<body>
		    <select id="categorias">
		        <option value="0">Seleccione una opcion</option>
		        <option value="frutas">Frutas</option>
		        <option value="verduras">Verduras</option>
		    </select>
		    <script src="ejercicio1_resuelto.js"></script>    
		</body>
		</html>
	ejercicio1_resuelto.js--------------------------------------------------------------------
		//1) Agarrar el select de categorias
		let select_categorias = document.querySelector("#categorias")

		let categorias = {
		    frutas : ["sandia","granada","melon"],
		    verduras : ["zapallo","papa","acelga"]
		}
	Ejercici_resuelto.js----------------------------------------------------------------------
		//1) Agarrar el select de categorias
		let select_categorias = document.querySelector("#categorias")
		//Se crean arreglos de objetos
		let categorias = {
		    //Se crea arreglo
		    frutas : ["sandia","granada","melon"],
		    verduras : ["zapallo","papa","acelga"]
		}

		//2) Asignarle un evento change
		select_categorias.addEventListener("change",e=>{
		    //3) Sacarle el value
		    let categoria = e.target.value
		    //4) Con el valor extraido del punto 3, acceder a la propiedad del objeto "categorias" correspondiente 
		    let items = categorias[categoria] 
		    //5) Iterar sobre el array que se obtuvo en el punto 4
		    let nuevo_select = document.createElement("select")
		    //Se le añade id al select creado
		    nuevo_select.id = "items"

		    //6) Crear un nuevo select con tantos option como elementos haya en el array
		    items.forEach(e=>{
		        //Se crean las opciones del select 
		        let opt = document.createElement("option")
		        //Se le añade valor al objeto  creado opcion
		        opt.value = e
		        //Se le añade valor a la propiedad innerText del objeto creado
		        opt.innerText = e
		        //Se le agrega el option al objeto select creado en el  paso 5
		        nuevo_select.appendChild(opt)
		    })
		    //Se agrega el select al body
		    document.body.appendChild(nuevo_select)
		    //7) Asignarle un evento change al select creado en el punto 6
		    nuevo_select.addEventListener("change", e=>{
		        //8) Extraer el value de la fruta o verdura que haya seleccionado el usuario 
		        let seleccionado = e.target.value
		        console.log(seleccionado)
		        //9) Crear dos botones que digan "confirmar" y "limpiar"
		        let b1 = document.createElement("button")
		        let b2 = document.createElement("button")
		        //Se crea un elemento div en el documento
		        //let div = document.createElement("div")
		        //Se crea fragmento en el documento
		        let f = document.createDocumentFragment()
		        //Se añade valor a la propiedad innerText de los botones creados en el paso 9
		        b1.innerText = "confirmar"
		        b2.innerText = "limpiar"
		        //Se añade botones al elemento div
		        /* div.appendChild(b1)
		        div.appendChild(b2) */
		        //Se le añaden botones al fragmento
		        f.appendChild(b1)
		        f.appendChild(b2)
		        //Se añade objeto fragmento al body
		        document.body.appendChild(f)
		        console.log(f)

		        //10) Si se hace click en confirmar, se tiene que mostrar en pantalla la categoria y fruta/verdura seleccionados
		        let p 
		        b1.addEventListener("click",()=>{
		            //Se crea elemento donde se mostrara el texto
		            p = document.createElement("p")
		            //Se añade texto a la propiedad innerText del elemento p creado, pasando las variables por interpolacion de variables
		            p.innerText = `Categoria : ${categoria} - Item : ${seleccionado}`
		            //Se agrega al documento elemento p
		            document.body.appendChild(p)
		        })

		        //11) Si se hace click en limpiar, se tiene que borrar el mensaje del punto 10 y borrar el select del punto 6
		        b2.addEventListener("click",()=>{
		            //Se elimina objeto p y objeto nuevo_select
		            document.body.removeChild(p)
		            document.body.removeChild(nuevo_select)
		            //forma de saber el padre y borrar elemento a partir de alli
		            //p.parentNode.removeChild(p)
		        })
		    })    
		})  
******************************************************************************************************


***********************************************************************************************Clase 3
Ajax**************************************************************************************************
******************************************************************************************************
	//Bloqueante == Sincronico
	//No Bloqueantes == Asincronicos
	***Referencias
	https://www.youtube.com/watch?v=8aGhZQkoFbQ
	https://www.youtube.com/watch?v=cCOL7MC4Pl0

	***Nota Importantes : alert() - prompt() - confirm()  --> Contituyen malas practicas al bloquear el javascript

	***Propiedades
		La propiedad readyState de un XHR nos dice en que estado esta el pedido 

			0 - Objeto Inicializado / Pedido abortado
			1 - Objeto configurado
			2 - Headers enviados y recibidos
			3 - Descargando informacion
			4 - Pedido Finalizado 

	***Http Codigos de estado
		***Referencia
			https://es.wikipedia.org/wiki/Anexo:C%C3%B3digos_de_estado_HTTP
		
		***Estructura
			Request

			GET  /index.html     http/1.1   
			Host : miservidor.com
			Accept : html , xml , xhtml
			User-Agent : el navegador

			Response

			http/1.1    200     OK
			Content-Type : (MIME TYPE) text/html
			Content-Length : 11
			Server : Win32  Apache4.2 / PHP7.6

	***readyState == 0
		Objeto Inicializado / Pedido abortado
		//AJAX == XHR = XMLHttpRequest
		let xhr = new XMLHttpRequest

		xhr.addEventListener("abort",()=>{
		    console.log("Se abortó la solicitud")
		    //Se reconfigura y se vuelve a mandar
		    //xhr.open()
		})

	***Evento que lee el cambio de estado //readystatechange : Es un evento que se dispara CADA VEZ que la propiedad readyState cambia
		xhr.addEventListener("readystatechange",()=>{
			//Muestra el estado 
		    console.log(`Nuevo Estado : ${xhr.readyState}`)

		    //readyState == 2
		    if(xhr.readyState == 2){
		    	//Se almacena todos los encabezados en un array
		        let headers = xhr.getAllResponseHeaders()
		        //Se separan los encabezados segun salto de linea con la funcion split
		        headers = headers.split("\n")

		        //Se carga en variable contenido del encabezado
		        let tipo = xhr.getResponseHeader("content-type")
		        let size = xhr.getResponseHeader("content-length")

		        //Se valida segun tipo de archivo image/jpg
		        if(tipo != "image/jpg"){
		            //xhr.abort()
		        }                

		    }

		    /* Validacion de estado para una determinada accion
		    if(xhr.readyState == 4 && xhr.status == 200){
		        console.log(xhr.response)
		    }
			//Se puede programar que hacer en cada codigo de estado
		    switch(xhr.status){
		        case 200 : 
		        case 404 : 
		        case 500 : 
		    } 
		    */

		})

		//readyState == 4
		xhr.addEventListener("load",()=>{
		    if(xhr.status == 200){
		        let p = document.createElement("p")
		        p.innerText = xhr.response
		        document.body.appendChild(p)
		    }
		})

		//inicia
		xhr.send()
				

	***Ejemplo
		//XMLHttpRequest(XHR) / Fetch
		//AJAX = Asnyc Js and XML

		let xhr = new XMLHttpRequest

		xhr.open("get","estilos.css")

		//load = Se ejecuta cuando el readyState == 4
		xhr.addEventListener("load",()=>{
		    console.log(xhr.response)
		})

		xhr.send()
	
	********************************************************************************Ejercicio 1 Clase 3
		Ejercicio.html---------------------------------------------------------------------------
			<!DOCTYPE html>
			<html lang="en">
			<head>
			    <meta charset="UTF-8">
			    <title>Document</title>
			</head>
			<body>
			    <header>
			        <h1>AJAX 1</h1>
			    </header>    
			    <nav>
			        <button id="archivo1">archivo 1</button>
			        <button id="archivo2">archivo 2</button>
			    </nav>
			    <main>

			    </main>
			    <script src="ejercicio_1.js"></script>
			</body>
			</html>
		ejercicio1.js---------------------------------------------------------------------------

		//1) Agarrar los botones
		//let b_1 = document.querySelector("#archivo1")
		//let b_2 = document.querySelector("#archivo2")
		let main = document.querySelector("main")
		//let nav = document.querySelector("nav")
		let botones = document.querySelectorAll("button")

		//Recorrer el objeto tomado
		/*
		for(let i=0; i<botones.length; i++){
		    let boton = botones[i]
		    boton.addEventListener(...)
		}
		*/

		//2) Asignarles un evento de click
		//b_1.addEventListener("click",()=>{
		/*
		botones[0].addEventListener("click",()=>{
		    main.innerText = "Cargando..."
		    let xhr = new XMLHttpRequest
		    xhr.open("get","archivo1.txt")
		    xhr.addEventListener("load",()=>{
		        if(xhr.status == 200){
		            main.innerText = xhr.response
		        }
		    })
		    xhr.send()
		})
		*/
		//b_2.addEventListener("click",()=>{
		/*
		botones[1].addEventListener("click",()=>{
		    main.innerText = "Cargando..."
		    let xhr = new XMLHttpRequest
		    xhr.open("get","archivo2.txt")
		    xhr.addEventListener("load",()=>{
		        if(xhr.status == 200){
		            main.innerText = xhr.response
		        }
		    })
		    xhr.send()
		})
		*/

		//Forma optima de hacer el punto 2
		//Utilizando metodo foreach para cada boton
		botones.forEach((boton,i)=>{
			//Se añade metodo de escucha del evento cickk a cada boton
		    boton.addEventListener("click", e =>{
		    	//3) Mostrar un mensaje en pantalla que diga "cargando..."
		        main.innerText = "Cargando..."
		        //5) Configurarlo para que pida el archivo txt que le corresponda
		        ajax(`${e.target.id}.txt`,"get",render)
		        //Se 
		        ajax(`${e.target.id}.txt`,"get",data=>{
		            console.log(data)
		        })
		    })
		})

		//Funcion ajax
		function ajax(url,metodo,callback){
			//Se crea variable de tipo XMLHttpRequest
		    let xhr = new XMLHttpRequest
		    //4) Iniciar un objeto XHR
		    xhr.open(metodo,url)
		    //Se añade metodo de escucha para que accione cuando este en estado 4
		    xhr.addEventListener("load",()=>{
		    	//Se valida para que invoque el metodo unicamente si el mensaje de respuesta es 200
		        if(xhr.status == 200){
		        	//Se llama metodo y se le pasa la data del response
		            callback(xhr.response)
		        }
		    })
		    //Metodo de envio
		    xhr.send()
		}

		//funcion render, funcion que modifica la propiedad innerText con la data 
		function render(data){
			//6) Mostrar la respuesta(el contenido del archivo) en el <main>
		    main.innerText = data
		}
	******************************************************************************************************
******************************************************************************************************





Curso JavaScript --> *********************************************************************************
	tutorial --> http://expertojava.ua.es/experto/restringido/2014-15/js/js.html#_trabajando_con_el_dom
******************************************************************************************************
*****************************************************************************************Nodos del Dom	
	Los otros enlaces que ofrece un nodo son:

	firstChild: primer hijo de un nodo, o null si no tiene hijos.

	lastChild: último hijo de un nodo, o null si no tiene hijos.

	nextSibling: siguiente hermano de un nodo, o null si no tiene un hermano a continuación (es el último).

	previousSibling: anterior hermano de un nodo, o null si no tiene un hermano previo (es el primero).

	Por ejemplo:

	Enlaces DOM - http://jsbin.com/bopije/1/edit?js
	var encabezado = document.body.firstChild;
	var scriptJS = document.body.lastChild;
	var parrafo1 = encabezado.nextSibling;
	var capa = scriptJS.previousSibling;
******************************************************************************************************
************************************************************************3.2.5. Seleccionando Elementos
	Mediante el DOM, podemos usar dos métodos para seleccionar un determinado elemento.

	Si queremos seleccionar un conjunto de elementos, por ejemplo, todos los párrafos del documento, 
	necesitamos utilizar el método document.getElementsByTagName(nombreDeTag). En cambio, si queremos 
	acceder a un elemento por su id (que debería ser único), usaremos el método 
	document.getElementById(nombreDeId).

	(function() {
	  var pElements = document.getElementsByTagName("p"); // NodeList
	  console.log(pElements.length);  // 3
	  console.log(pElements[0]);  // Primer párrafo

	  var divpElement = document.getElementById("tres");
	  console.log(divpElement); // "<p id="tres">Tercer párrafo dentro de un div</p>"
	}());

	En vez de usarlos sobre el objeto document, podemos utilizarlos sobre un elemento en concreto 
	para refinar la búsqueda Destacar que si estamos interesados en un único elemento, lo haremos 
	mediante su id, mientras que si queremos más de un elemento, lo haremos mediante tu tag, ya 
	que obtendremos un NodeList, el cual es similar a un array, y es una representación viva de 
	los elementos, de modo que si se produce algún cambio en el DOM se verá trasladado al navegador.

	querySelector
	El método de getElementsByTagName es antiguo y no se suele utilizar. En el año 2013 se definió el 
	Selector API, que define los métodos querySelectorAll y querySelector, los cuales permiten obtener elementos mediantes consultas CSS, las cuales ofrecen mayor flexibilidad:

	var pElements = document.querySelectorAll("p");
	var divpElement = document.querySelector("div p");
	var tresElement = document.querySelector("#tres");
	Esta manera de acceder a los elementos es la misma que usa jQuery, por lo que la estudiaremos en sesión correspondiente.

	Conviene citar que getElementById es casi 5 veces más rápido que querySelector
******************************************************************************************************
