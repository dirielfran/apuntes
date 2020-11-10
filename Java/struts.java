
Anotaciones******************************************************************************************************************************************************************
	@Results--------------------------------------------------------------------------
		Cada vez que un Action termina su ejecución, se muestra un resultado al usuario. Estos resultados pueden ser de muchos tipos y tener muchos significados. El tipo 
		más común de resultado es mostrar al usuario una nueva página web cierta información

		Cuando el método "execute" de un Action termina de ejecutarse este siempre regresa un objeto de tipo String. El valor de ese String se usa para seleccionar un 
		elemento de tipo "<result>", si trabajamos con archivos de configuración en XML, o "@Result", si trabajamos con anotaciones. Dentro del elemento se indica qué 
		tipo de Result se quiere regresar al usuario (enviar un recurso, redirigirlo a otro Action, un archivo de texto plano, etc.), el recurso que se quiere regresar, 
		algunos parámetros adicionales que requiera el Result y, lo más importante, el nombre que tendrá este Result, en donde este nombre es precisamente lo que se 
		indica con la cadena que es regresada de la ejecución del método "execute".

		Cada result tiene un nombre que debe ser único para un Action y es en base a este nombre que Struts 2 sabrá qué es lo que debe mostrarle al usuario. La interface 
		"Action", que es implementada por la clase "ActionSupport", que hemos estado utilizando como base para nuestros Actions, proporciona un conjunto de constantes que 
		contienen los nombres de los results más comunes para que podamos usarlos dentro de nuestros Actions. El identificador de la constante es el mismo que el valor 
		que contiene (usado las respectivas convenciones) para que sea fácil identificar cuándo debemos usar cada uno:

			String SUCCESS = "success";
			String NONE    = "none";
			String ERROR   = "error";
			String INPUT   = "input";
			String LOGIN   = "login";

		El valor de cada una de las constantes anteriores representa una situación que puede ocurrir durante la ejecución de nuestro Action:

			"success" - Indica que todo ha salido correctamente (validaciones, y el proceso de lógica en general) durante la ejecución del método, por lo que el 
				usuario puede ver la salida esperada.
			"error" - Indica que alguna cosa dentro del método ha salido mal. Puede ser que algún cálculo no pueda realizarse, o que algún servicio externo que 
			estemos utilizando no esté disponible en ese momento o haya lanzado alguna excepción, o cualquier otra cosa que se les ocurra que pueda salir mal.
			"input" - Indica que algún campo proporcionado en un formulario es incorrecto. Puede ser que el valor no sea del tipo esperado, o no cumpla con el 
			formato o rango requerido, o cosas más sofisticadas como que un valor esté repetido (digamos que alguien está tratando de usar un nombre de usuario 
			que ya está siendo usado por otra persona).
			"login" - Indica que el recurso al que el usuario está intentado acceder solo está disponible para usuarios registrados del sitio y por lo tanto debe 
			loguearse primero. Para poder usar este result de forma correcta debemos crear un result global en la configuración del sitio. Veremos cómo hacer esto 
			al final del tutorial.
			"none" - Este es un result de un tipo especial ya que le indica a Struts 2 que no debe enviar al usuario a ningún lugar (o en términos formales que el 
			procesamiento del resultado sea cancelado). Esto es usado cuando el Action maneja el proceso de regresar el resultado al usuario usando, por ejemplo, 
			de forma directa los objetos "HttpServletResponse" o "ServletOutputStream".

		Struts 2 maneja por default 11 tipos de results:

			"dispatcher" - Este es el result más usado y el default. Envía como resultado una nueva vista, usalmente una jsp.
			"redirect" - Le indica al navegador que debe redirigirse a una nueva página, y por lo tanto este creará una nueva petición para ese recurso.
			"redirectAction" - Redirige la petición a otro Action de nuestra aplicación. 
			"chain" - Al terminarse la ejecución del Action se invoca otro Action.
			"stream" - Permite enviar un archivo binario de vuelta al usuario.
			"plaintext" - Envía el contenido del recurso que indiquemos como un texto plano. Típicamente se usa cuando necesitamos mostrar una JSP o HTML sin procesar
			"httpheader" - Permite establecer el valor de la cabecera HTTP de código de estatus que se regresará al cliente. Así por ejemplo podemos usarlo para enviar 
			un error al cliente (estatus 500), un recurso no encontrado (404), un recurso al que no tiene acceso (401), o por el que requiere pagar (402).
			"xslt" - Se usa cuando el resultado generará un XML será procesado por una hoja de transformación de estilos para generar la vista que se mostrará al cliente.
			"freemarker" - Para integración con FreeMarker
			"velocity" - Para integración con Velocity
			"tiles" - Para integración con Tiles

	@Ejemplo------------------------------------------------------------------------
		@Results({
		    @Result(name="success", location="/WEB-INF/pages/bienvenido1.jsp"),
		    @Result(name="input", location="Bienvenido", type="redirectAction")
		})
		public class ValidarUsuarioAction extends ActionSupport {}

		-----------------------------------------------------------------------
		<result name="success" type="dispatcher">
	    	<param name="location">/dispatcher/exito.jsp</param>
		</result>

		<action name="redirect" class="com.javatutoriales.results.actions.RedirectResultAction">   
	    	<result type="redirect">/dispatcher/index.jsp</result>
		</action>

		<action name="redirect-externo" class="com.javatutoriales.results.actions.RedirectExternoResultAction">
    		<result type="redirect">http://www.javatutoriales.com/2011/10/struts-2-parte-3-trabajo-con.html</result>
		</action>

		<action name="redirect-action" class="com.javatutoriales.results.actions.RedirectActionAction">
		    <result type="redirectAction">dispatcher</result>
		</action>
		-----------------------------------------------------------------------
		<action name="chain">
		    <result type="chain">chain2</result>
		</action>

		<action name="chain2">
		    <result type="chain">chain3</result>
		</action>
	@Action--------------------------------------------------------------------------
****************************************************************************************************************************************************************************


********************************************************************************************
********************************************************************************************
*****************************************************************************Ejemplo 1 Action

	***Se realizaron modificaciones al struts.xml
	<action name="Saludar" class="com.zeni.app.webapp.action.LoginAction" method="execute">
	    <result name="exito">/WEB-INF/pages/saludos.jsp</result>
	</action>


	Se crea clase action-----------------------------------------------------------------------
	public class LoginAction {
		private String saludosAtr;
		
		public String execute(){
			setSaludosAtr("Hola desde Struts2");
			return"exito";
		}

		public String getSaludosAtr() {
			return saludosAtr;
		}

		public void setSaludosAtr(String saludosAtr) {
			this.saludosAtr=saludosAtr;
		}
	}

	Se modifico el menu-config.xml--------------------------------------------------------------

	<Item name="Saludar" title="login" page="/Saludar.html" 
	roles="ROLE_USER,ROLE_CLIENTE_VIEW,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION,ROLE_BOLETOS,ROLE_SUBPRODUCTOS,ROLE_IMPUESTOS" width="250"/> 

	Se creo jsp-------------------------------------------------------------------------------
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="s" uri="/struts-tags" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Insert title here</title>
		</head>
	<body>
		<h1><s:property value="saludosAtr"></s:property></h1>
	</body>
	</html>
*******************************************************************************************





********************************************************************************************
********************************************************************************************
*****************************************************************************Ejemplo 2 Action
	

	Se modifico el menu-config.xml--------------------------------------------------------------

	<Item name="Saludar" title="login" page="/Saludar.html" 
	roles="ROLE_USER,ROLE_CLIENTE_VIEW,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION,ROLE_BOLETOS,ROLE_SUBPRODUCTOS,ROLE_IMPUESTOS" width="250"/> 

	---------------------------------------------------Se realizaron modificaciones al struts.xml
	<action name="Saludar" class="com.zeni.app.webapp.action.LoginAction" method="execute">
	    <result name="success">/WEB-INF/pages/saludos.jsp</result>
	</action>

	Se creo la clase Action----------------------------------------------------------------------
	public class LoginAction extends ActionSupport{
		
		private String nombre;
		
		@Override
		public String execute() {
			return SUCCESS;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre=nombre;
		}
	}

	Se creo jsp-------------------------------------------------------------------------------
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="s" uri="/struts-tags" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<body>
		<%--FormularioStruts 2--%>
		<h1>Personas con Struts2</h1>
		<s:form>
			<s:textfield name="nombre" />
			<s:submit value="Enviar" />
		</s:form>
		<%--Desplegamos el valor del atributo nombre de la clase Action--%> 
		<div>Nombre proporcionado:<s:property value="nombre" /></div>
	</body>
	</html>
*********************************************************************************************






********************************************************************************************
********************************************************************************************
*************************************************************************Mensajes en struts2
	#Se modifica el archivo ApplicationResources.properties-------------------------------------
	persona.titulo: Personas con Struts 2
	persona.valor: Nombre Proporcionado
	persona.boton: Enviar Formulario

	Se crean metodos de acceso a las propiedades en el action trabajando con el bean------------
	public String getTitulo(){
		    return getText("persona.titulo");
		}
		    
		public String getValor(){
		    return getText("persona.valor");
		}
		    
		public String getBoton(){
		    return getText("persona.boton");
		}


	Se modifica el jsp con los beans creados en el action--------------------------------------

		<head>
			<title><s:property value="titulo" /></title>
		</head>
		<body>
			<%--Formulario Struts 2--%>
			<h1><s:property value="titulo" /></h1>
			<s:form>
				<s:textfield name="nombre" />
				<s:submit key="persona.boton" name="submit" />
			</s:form>
			<%--Desplegamos el valor del atributo nombre de la clase Action--%> 
			<div>
				<s:propertyvalue="valor" />:<s:property value="nombre" />
			</div>
		</body>
********************************************************************************************





********************************************************************************************
********************************************************************************************
**********************************************************************************Resultados
	Se modifica el archivo struts.xml-----------------------------------------------------------
	<action name="Saludar">
		<result>/WEB-INF/pages/saludos.jsp</result>
	</action>
	        
	 <action name="Login" class="com.zeni.app.webapp.action.LoginAction" method="execute">
	     <result name="success">/WEB-INF/pages/Bienvenido.jsp</result>
	     <result name="input" type="redirectAction">Saludar</result>
	 </action>


	Se crea la clase action---------------------------------------------------------------------

	import com.opensymphony.xwork2.ActionSupport;

	public class LoginAction extends ActionSupport{
		private String nombre;
		private String clave;
		
		@Override
		public String execute() {
			//Si es usuario valido mostramos la pagina de bievenido.jsp
			if ("admin".equals(this.nombre)) {
				return SUCCESS;
			} else {
				//Si es usuario NO valido, regresamos al login
				return INPUT;
			}
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre=nombre;
		}
		
		public String getClave(){
			return clave;
		}
		
		public void setClave(String clave){
			this.clave = clave;
		}
			
	}

	Se modifica el archivo ApplicationResources.properties--------------------------------------

	form.usuario: Usuario
	form.password: Password
	form.boton: Enviar
	form.titulo: Login
	bienvenido.titulo: Bienvenido
	bienvenido.mensaje: Usuario correcto


	Se crea jsb saludos.jsp formulario----------------------------------------------------------------------
	<head>
		<title><s:textname="form.titulo" /></title>
	</head>
	<body>
		<%-- El url es del form es: /validarUsuario --%>
		<h1><s:text name="form.titulo" /></h1>
		<s:form action="Login">
			<s:textfield key="form.usuario" name="nombre" />
			<s:password key="form.password" name="clave" />
			<s:submit key="form.boton" name="submit" />
		</s:form>
	</body>

	Se crea jsp Bienvenido.jsp para la redireccion-------------------------------------------------------------
	<head>
		<title><s:text name="bienvenido.titulo" /></title>
	</head>
	<body>
		<h1><s:text name="bienvenido.titulo" /></h1>
		<h2><s:text name="bienvenido.mensaje" />: <s:property value="nombre"/></h2>
	</body>
********************************************************************************************





********************************************************************************************
********************************************************************************************
********************************************************************************@Anotaciones
	Referencias --> https://www.tutorialspoint.com/struts_2/struts_annotations_types.htm
	@Action----------------------------------------------------------------------------
		La clase debe terminar con el sufijo Action
		Se utiliza la anotacion @Action para definir la accion, se coloca justa delante del 
		metodo que queremos ejecutar ante tal accion Ej: @Action(value="nombreaccion")
	@Results---------------------------------------------------------------------------
		Se colocan delante de la clase Action con las vistas a renderizar, la clase action 
		debe extender de la clase ActionSupport
		@Results({
	    	@Result(name="success", location="/WEB-INF/pages/reportesConsultaPactadosList.jsp"),
	    	@Result(name="input", location="/WEB-INF/pages/reportesConsultaPactadosList.jsp")
		})
		Utilizando redireccionamiento--------------------------------------------------
		@Results({
		    @Result(name="success", location="/WEB-INF/pages/Bienvenido.jsp"),
		    @Result(name="input", location="saludar", type="redirectAction")
		})
	@RequiredFieldValidator------------------------------------------------------------
		Define que un campo es requerido, se coloca antes de un setter
		Se requiere que se coloque el formulario en la vista con el atributo Validator en true
		<s:form action="validarUsuario" validate="true">
		</s:form>
		Ej:
		@RequiredFieldValidator(message = "El nombre es requerido")
		public String getNombre(){
			return name<
		}
	@RequiredStringValidator-----------------------------------------------------------
		Se requiere que se coloque el formulario en la vista con el atributo Validator en true
		<s:form action="validarUsuario" validate="true">
		</s:form>

		@RequiredStringValidator(type= ValidatorType.FIELD, message = "The name is required.")
		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
	@IntRangeFieldValidator------------------------------------------------------------
		Valida un rango para un campo especifico asociado al atributo
		@IntRangeFieldValidator(message = "Age must be in between 28 and 65",min = "29", max = "65",shortCircuit=true)
		public int getEdad() {
			return edad;
		}


	Se crea acceso desde el menu-config------------------------------------------------
	<Item name="Bienvenido" title="Bienvenido" page="/Bienvenido.html" 
	roles="ROLE_USER,ROLE_CLIENTE_VIEW,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION,ROLE_BOLETOS,ROLE_SUBPRODUCTOS,ROLE_IMPUESTOS" width="250"/> 

	Se configura action en struts.xml--------------------------------------------------
	<action name="Bienvenido">
		<result>/WEB-INF/pages/Bienvenido.jsp</result>
	</action>

	Se crea jsp formulario Bienvenido.jsp----------------------------------------------
		<body>
			<%--Elurles del formes: /validarUsuario--%>
			<h1><s:text name="form.titulo" /></h1>
			<s:form action="validarUsuario">
				<s:textfield key="form.usuario" name="usuario" />
				<s:password key="form.password" name="password" />
				<s:submit key="form.boton" name="submit" />
			</s:form>
		</body>

	Se crea clase action con anotaciones-----------------------------------------------
	import com.opensymphony.xwork2.ActionSupport;
	import org.apache.struts2.convention.annotation.*;

	@Results({
	    @Result(name="success", location="/WEB-INF/pages/bienvenido1.jsp"),
	    @Result(name="input", location="Bienvenido", type="redirectAction")
	})
	public class ValidarUsuarioAction extends ActionSupport {

	    private String usuario;
	    private String password;
	    

	    @Action("validarUsuario")
	    @Override
	    public String execute() {
	        //Si es usuario valido mostramos la pagina de bienvenido.jsp 
	        if ("admin".equals(this.usuario)) {
	            return SUCCESS;
	        } else {
	            //Si es usuario NO valido, regresamos al login
	            return INPUT;
	        }
	    }

	    public String getUsuario() {
	        return usuario;
	    }

	    public void setUsuario(String usuario) {
	        this.usuario = usuario;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	}

	Se crea bienvenido1.jsp---------------------------------------------------------

		<body>
			<h1><s:text name="bienvenido.titulo" /></h1>
			<h2><s:text name="bienvenido.mensaje" />: <s:property value="usuario"/></h2>
			<div>
				<a href="<s:url action="Bienvenido"/>">
					<s:text name="bienvenido.regresar" />
				</a>
			</div>
		</body>
********************************************************************************************






********************************************************************************************
********************************************************************************************
********************************************************************************Validaciones

	***La primer forma de enviar mensajes de error a nuestro formulario es indicando el campo del 
	formulario y agregando un mensaje de error, utilizando el método:
	addFieldError(String nombreCampoHtml, String mensajeError).

	***La segunda forma es enviar un error genérico, no asociado a ningún campo sino a la página 
	en general. Para ello utilizamos el método:
	addActionError(String mensajeError).

	***En el método validate( ) básicamente podemos hacer las validaciones que consideremos 
	necesarias, e indicar al usuario a través de los métodos mencionados que hay algún problema 
	con algún campo o con el formulario en general y que debe ser corregido según lo indiquemos.

	***Para el manejo de mensajes cuando NO es un error, sino solo un mensaje informativo usamos 
	el método:	
	addActionMessage(StringmensajeGenerar)


	Modificacion en menu-config.xml-------------------------------------------------------------
	<Item name="Bienvenido" title="Bienvenido" page="/Bienvenido.html" 
	roles="ROLE_USER,ROLE_CLIENTE_VIEW,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION,ROLE_BOLETOS,ROLE_SUBPRODUCTOS,ROLE_IMPUESTOS" width="250"/> 
	
	Modificacion en struts.xml------------------------------------------------------------------
	 <action name="Bienvenido">
		<result>/WEB-INF/pages/Bienvenido.jsp</result>
	</action>
	
	Se crea Bienvenido.jsp----------------------------------------------------------------------
	**Se añade <s:head/> para atributos de ccs
	**Se añade <s:actionerror/> para que muestre los mensajes de error


	<%@page contentType="text/html" pageEncoding="UTF-8"%>
	<%@taglib prefix="s" uri="/struts-tags" %>
	<!DOCTYPEhtml>
	<html>
		<head>
			<title><s:text name="form.titulo" /></title>
			<s:head/>
		</head>
		<body>
			<%--Elurles del formes: /validarUsuario--%>
			<h1><s:text name="form.titulo" /></h1>
			<s:actionerror/>
			<s:form action="validarUsuario">
				<s:textfield key="form.usuario" name="usuario" />
				<s:password key="form.password" name="password" />
				<s:submit key="form.boton" name="submit" />
			</s:form>
		</body>
	</html>

	Se crea ValidarUsuarioAction.java-----------------------------------------------------------
	**Se realiza extends de ActionSupport
	**Se sobreescribe metodo validate()
	**Se agregan los mensajes
	**Se agregan las anotaciones de @Results y @Result


	import com.opensymphony.xwork2.ActionSupport;
	import org.apache.struts2.convention.annotation.*;

	@Results({
	    @Result(name="success", location="/WEB-INF/pages/bienvenido1.jsp"),
	    @Result(name="input", location ="/WEB-INF/pages/Bienvenido.jsp")
	})
	public class ValidarUsuarioAction extends ActionSupport {

	    private String usuario;
	    private String password;
	    
	    
	    @Override
	    public void validate() {
	    	if(this.usuario==null||"".equals(this.usuario.trim())) {
	    		addFieldError("usuario", getText("val.usuario"));
	    	} else if(!usuarioValido()) {
	    		addActionError(getText("usuario.invalido"));
	    	}
	    	
	    	if(this.password==null||"".equals(this.password.trim())) {
	    		addFieldError("password", getText("val.password"));
	    	} else if(this.password.length() <3) {
	    		addFieldError("password", getText("val.pass.min.length"));
	    	}
	    }

	    @Action("validarUsuario")
	    @Override
	    public String execute() {
	    	//Si es usuario valido mostramos la pagina de bienvenido.jsp
	    	if(usuarioValido()) {
	    		addActionMessage(getText("usuario.valido"));
	    		return SUCCESS;
	    	} else{
	    		//Si es usuario NO valido, regresamos al login
	    		return INPUT;
	    	}
	    	
	    }
	    
	    private boolean usuarioValido() {
	    	//Valor de usuario valido = "admin" en codigoduro 
	    	return "admin".equals(this.usuario);
	    }
	        

	    public String getUsuario() {
	        return usuario;
	    }

	    public void setUsuario(String usuario) {
	        this.usuario = usuario;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	}

	Se crea bienvenido1.jsp---------------------------------------------------------------------
	**Se añade <s:actionmessage/> para que muestre los mensajes de error

	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	 <%@ taglibprefix="s" uri="/struts-tags" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title><s:text name="bienvenido.titulo" /></title>
			<s:head/>
		</head>
		<body>
			<h1><s:text name="bienvenido.titulo" /></h1>
			<s:actionmessage/>
			<h2><s:text name="bienvenido.mensaje" />: <s:property value="usuario"/></h2>
			<div>
				<a href="<s:url action="Bienvenido"/>">
					<s:text name="bienvenido.regresar" />
				</a>
			</div>
		</body>
	</html>
********************************************************************************************




********************************************************************************************
********************************************************************************************
*********************************************************************************Value Stock
	Cuando Struts 2 ejecuta un Action como consecuencia de una petición, este action es 
	colocado en la cima del ValueStack, es por esto que podemos acceder a sus atributos 
	haciendo una llamada directa al nombre del mismo.

	Cuando realizamos una petición para el Action, el DispatcherFilter ejecutará el
	método "execute" del Action, después colocará este Action en la cima del ValueStack 
	con lo cual lo tendremos disponible.

	Cuando hacemos la petición para el atributo un atributo ej "raza", usando la etiqueta 
	"s:property", Struts busca en el ValueStack, de arriba a abajo, un objeto que tenga un 
	método "getRaza()". Como el primer objeto que encuentra con el método "getRaza()" es 
	"StackAction" (de hecho es el primer objeto en el que busca) ejecuta este método mostrando 
	el valor correspondiente.

	Además del ValueStack, Struts 2 coloca otros objetos en el ActionContext, incluyendo 
	Maps que representan los contextos de "application", "session", y "request". Una 
	representación de esto puede verse en la siguiente :

		"application" representa el ApplicationContext, o sea, el contexto completo de la 
			aplicación.
		"session" representa el HTTPSession, o sea, la sesión del usuario.
		"request" representa el ServletRequest, o sea, la petición que se está sirviendo.
		"parameters" representa los parámetros que son enviados en la petición, ya sea 
			por GET o por POST.
		"attr" representa los atributos de los objetos implícitos. Cuando preguntamos 
			por un atributo, usando "attr", este busca el atributo en los siguientes scopes: 
			page, request, session, application. Si lo encuentra, regresa el valor
			del atributo y no continúa con la búsqueda, sino regresa un valor nulo.

	Para hacer referencia a los objetos del contexto que NO son la raíz debemos preceder 
	el nombre del objeto con el símbolo de gato (#).

	Como el objeto "session" no es la raíz del mapa de contexto, es necesario hacer referencia 
	a él de la siguiente forma:
		#session
	Y podemos obtener cualquiera de sus atributos mediante el nombre del atributo. Por lo que 
	la etiqueta queda de la siguiente forma:
		<s:property value="#session.datoSesion" />
		También podemos hacerlo de esta otra forma:
		<s:property value="#session['datoSesion']" />

	-----------------------------------------------------------------------------------
	Ejemplo:
	-----------------------------------------------------------------------------------
	struts.xml
		<Item name="Saludos" title="Saludos" page="/stack.html" roles="ROLE_USER,ROLE_CLIENTE_VIEW,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION,ROLE_BOLETOS,ROLE_SUBPRODUCTOS,ROLE_IMPUESTOS" width="250"/>
	-----------------------------------------------------------------------------------
	Animal.java
		public class Animal {
			private String nombre;
			private String raza;

			public String getNombre() {
				return nombre;
			}

			public void setNombre(String nombre) {
				this.nombre = nombre;
			}

			public String getRaza() {
				return raza;
			}

			public void setRaza(String raza) {
				this.raza = raza;
			}
		}
	----------------------------------------------------------------------------------
	Persona.java
		public class Persona {
			private String nombre;
			private String salario;

			public String getNombre() {
				return nombre;
			}

			public void setNombre(String nombre) {
				this.nombre = nombre;
			}

			public String getSalario() {
				return salario;
			}

			public void setSalario(String salario) {
				this.salario = salario;
			}
		}
	----------------------------------------------------------------------------------
    StackAction.java
    	@Results({
    		@Result(name="success", location="/WEB-INF/pages/Bienvenido.jsp")
		})
		public class StackAction extends BaseAction implements Preparable{
			
			@Action("stack")
			@Override
			public String execute() throws Exception {
				ValueStack stack = ActionContext.getContext().getValueStack();
				ActionContext.getContext().getSession().put("datoSesion", "dato en la sesion");	
				Animal animal = new Animal();
				animal.setNombre("nombre del animal");
				animal.setRaza("perro labrador");
				Persona persona = new Persona();
				persona.setNombre("nombre de la persona");
				persona.setSalario("realmente poco");
				stack.push(persona);
				stack.push(animal);
				return SUCCESS;
			}
		}
	----------------------------------------------------------------------------------
	Bienvenido.jsp
		<body>
			<ul>
				<li><strong>Raza: </strong> <s:property value="raza" /></li>
				<li><strong>Salario: </strong> <s:property value="salario" /></li>
				<li><strong>Nombre: </strong> <s:property value="nombre" /></li>
			</ul>

			<ul>
				<li><strong>Animal: </strong>
				<s:property value="[0].nombre" /></li>
				<li><strong>Persona: </strong>
				<s:property value="[1].nombre" /></li>
				<li><strong>Correo del Usuario por medio del valueStack: </strong>
				<s:property value="[2].currentUser.email" /></li>
				<li><strong>Acceder a otros objetos en el actionContext: </strong>
				<s:property value="#session.datoSesion" />
			</ul>
		</body>
********************************************************************************************




********************************************************************************************
********************************************************************************************
*************************************************************************Expression Languaje
	Ejemplo_ExpressionLanguaje____________________________________________________
    saludos.jsp
    <%@taglib prefix="s" uri="/struts-tags"%>
    <html>
        <head>
            <title><s:text name="form.titulo" /></title>
        </head>
        <body>
            <s:form action="Login">
                <s:textfield name="nombre" label="Nombre" />
                <s:textfield name="username" label="Username" />
                <s:password name="password" label="Password" />
                <s:textfield name="edad" label="Edad" />
                <s:textfield name="fechaNacimiento" label="Fecha de Nacimiento" />
                <s:submit value="Enviar" />
            </s:form>
        </body>
    </html>
    ------------------------------------------------------------------------------
    @Results({@Result(name="success", location="/WEB-INF/pages/Bienvenido.jsp")})
    public class LoginAction extends ActionSupport{
        private String nombre;
        private String username;
        private String password;
        private int edad;
        private Date fechaNacimiento;
        private Persona usuario;
        private List<String> estado = new ArrayList<String>();
        
        @Action("Login")
        @Override
        public String execute() throws Exception{
            usuario = new Persona();
            usuario.setNombre(nombre);
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setEdad(edad);
            usuario.setFechaNacimiento(fechaNacimiento);
            estado.add("Activo");
            estado.add("Inactivo");
            return SUCCESS;
        }
        
        
        
        public List<String> getEstado() {
            return estado;
        }
        public Persona getUsuario() {
            return usuario;
        }
        public int getEdad(){
            return edad;
        }
        public void setEdad(int edad){
            this.edad = edad;
        }
        public void setFechaNacimiento(Date fechaNacimiento){
            this.fechaNacimiento = fechaNacimiento;
        }
        public void setNombre(String nombre){
            this.nombre = nombre;
        }
        public void setPassword(String password){
            this.password = password;
        }
        public void setUsername(String username){
            this.username = username;
        }
    }


    ------------------------------------------------------------------------------
    Bienvenido.jsp
    <%@taglib prefix="s" uri="/struts-tags"%>
    <html>
        <head>
            <title><s:text name="form.titulo" /></title>
        </head>
        <body>
            Nombre: <strong><s:property value="usuario.nombre" /></strong><br />
            Username: <strong><s:property value="usuario.username" /></strong><br />
            Password: <strong><s:property value="usuario.password" /></strong><br />
            Edad: <strong><s:property value="usuario.edad" /></strong><br />
            Fecha de Nacimiento: <strong><s:property value="usuario.fechaNacimiento" /></strong>
            <h1>Expression Languaje</h1>
            Nombre El:${usuario.nombre}<br />
            Nombre del atributo del action: ${edad}<br />
            Password EL: ${usuario['password']}<br />
            Elementos de una lista: ${estado[0]}
            
            <h1>EL y Variables Implicitas</h1>
            <ul>
                <li>Nombre Aplicaci&oacute;n: ${pageContext.request.contextPath}</li>
                <li>Navegador del Cliente: ${ header["User-Agent"] }</li>
                <li>Id Session: ${cookie.JSESSIONID.value}</li>
                <li>Web Server: ${pageContext.servletContext.serverInfo}</li>
                
        </body>
    </html>
********************************************************************************************





********************************************************************************************
********************************************************************************************
******************************Obteniendo Valores de Constantes, metodos y Variables con OGNL
	Una  clase no tiene que tener nada de especial: ninguna anotación, no usa ninguna 
	librería, ni nada por el estilo.
	Si creamos una página llamada "constantes.jsp", en el directorio raíz de las páginas web.
	Para crear un objeto nuevo con OGNL podemos usar el operador "new" y el fully qualified 
	class name. Por ejemplo:
		para crear un nuevo objeto de la clase "Constantes" haríamos lo siguiente:
		<s:property value="new com.zeni.app.model.Constantes().atributo" />


	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		public class Constantes {
			private String atributo = "Atributo de instancia";
			public final static String valor= "variable estatica";
			private static enum Datos {PRIMERO, SEGUNDO, TERCERO; public String getDato(){ 
				return	"dato";
				} 
			}
			public String getAtributo() {
				return atributo;
			}
			
			public String metodoDeInstancia()	{
				return "metodo de instancia";
			}
			public String metodoDeInstancia(String mensaje)	{
				return mensaje;
			}
			public static String metodoEstatico()	{
				return "metodo estatico";
			}
			public static String metodoEstatico(String mensaje)	{
				return mensaje;
			}
			
		}
		***********************************************************************
		<body>
			<h3>Acceso al atributo de una clase</h3>
			<s:property value="new com.zeni.app.model.Constantes().atributo" />
			<h3>Invocar el metodo de una clase</h3>
			<s:property value="new com.zeni.app.model.Constantes().metodoDeInstancia()" />
			<h3>invocar los miembros estáticas</h3>
			<s:property value="new com.zeni.app.model.Constantes().metodoEstatico()" />
			<s:property value="new com.zeni.app.model.Constantes().valor" />
			<h3>Llamar sin crear una nueva instancia</h3>
			<s:property value="@com.zeni.app.model.Constantes@valor" />***
			<h3>Llamar metodo estatico sin crear una nueva instancia</h3>
			<s:property value="@com.zeni.app.model.Constantes@metodoEstatico()" />
			<h3>Métodos que reciben un parámetro </h3>
			<s:property value="@com.zeni.app.model.Constantes@metodoEstatico('Hola Metodo Estaticos')"/>
			<h3>Inclusive podemos pasar como parámetro uno de los atributos obtenidos anteriormente:</h3
			<li><s:property value="new com.zeni.app.model.Constantes().metodoDeInstancia(@com.zeni.app.model.Constantes@valor)" /></li> 
		</body>

	*****Nota: El valor de la constante "valor" no se muestra, porque cuando llamamos a los 
	atributos de esta forma:
		<s:property value="new com.javatutoriales.struts2.ognl.Constantes().valor" />
	OGNL busca un método llamado "getValor()" en la clase "Constantes", y este método 
	no existe.

	****************************************llamadas sin una instancia de la clase
	******************************************************************************
	También es posible hacer estas llamadas sin una instancia de la clase. En este caso 
	debemos usar una notación especial de OGNL.

	En esta notación, debemos indicar el nombre completo de la clase que contiene al 
	miembro estático, precedida por una arroba ("@"). También se debe indicar el miembro 
	que se quiere llamar precedido por una arroba.
		Por ejemplo, para usar la constante "valor", la etiqueta debe estar colocada de 
		esta forma:
		<s:property value="@com.javatutoriales.struts2.ognl.Constantes@valor" />
		<s:property value="@com.zeni.app.model.Constantes@valor" />



	*********************************************************************************
	*************************Obtener los valores de las Constantes de una Enumeración
	Como cada uno de los valores de una enumeración es una constante (o sea una variable 
	publica, estática, y final) obtener sus valores es igual a obtener cualquier otro 
	valor de una variable estática. O sea que tenemos que usar la sintaxis de la doble arroba.
	Ahora, el secreto aquí viene dado en el sentido de que las enumeraciones son en 
	realidad un tipo especial de clase Java. Como aquí la enumeración está dentro de 
	otra clase, la primera se convierte en una clase interna de la segunda. El fully
	qualified class name de una clase interna es el nombre de la primer clase, 
	seguida de un signo de dólar ($) seguido del nombre de la clase interna. Por lo 
	tanto, el fully qualified class name de la enumeración "Datos" es:
		"com.javatutoriales.struts2.ognl.Constantes$Datos". 

	Teniendo el nombre de esta enumeración, podemos obtener	los valores de sus constantes 
	de la siguiente forma:
		<h3>Accediendo alos atributos de una enumeracion:</h3> 
		<s:property value="@com.zeni.app.model.Constantes$Datos@PRIMERO" />
		<s:property value="@com.zeni.app.model.Constantes$Datos@SEGUNDO" />

	E igual que en los casos anteriores, teniendo estos valores podemos invocar cualquier 
	método que exista dentro de la	enumeración, como en este caso el método "getDato()" 
	que invocamos de esta forma:
		<s:property value="@com.javatutoriales.struts2.ognl.Constantes$Datos@TERCERO.dato" />
	Que también podría ser de esta forma:
		<s:property value="@com.javatutoriales.struts2.ognl.Constantes$Datos@TERCERO.getDato()"	/>


	*********************************************************************************
	**************************************Obtener los valores del metodo de una clase
		<td align="left" valign="top"><s:select key="dependencia"
			list="%{@com.zeni.app.util.PropertyValues@getDependenciasAddendaAfip()}"
			required="false" emptyOption="true" cssClass="text medium" label="Dependencia"/>
		</td> 


		canjeForm.jsp
		<ria:simplegridcolumn id="cantidadEmtregada" label="canje.entregada" width="70" style="text-align:right;">
			<c:if test="${item['class'].simpleName=='Contrato'}">
				<s:property value="%{@com.zeni.app.util.DoubleUtil@format(#request.item.totalEntregasPartidas, #request.item.unidadDeMedida.formato)}"/> 
			</c:if>
			</ria:simplegridcolumn>
			<ria:simplegridcolumn id="cantidadPactada" label="canje.cantidadPactada" width="70" style="text-align:right;">
			<c:if test="${item['class'].simpleName=='Contrato'}">
			     <s:property value="%{@com.zeni.app.util.DoubleUtil@format(#request.item.cantidadPactada, #request.item.unidadDeMedida.formato)}"/>
			</c:if>
		</ria:simplegridcolumn>

		cartaCamaraFiltro.jsp
		<td colspan="1" align="left" valign="top">
********************************************************************************************



***************************************************************************************OGNL
	****Uso de # (signo de libra)

	OGNL se utiliza para referirse a objetos en el ActionContext de la siguiente manera:

		***objectName: objeto en ValueStack (objeto predeterminado / raíz en el contexto OGNL), 
		como una propiedad Action
		***#objectName: objeto en ActionContext pero fuera de ValueStack, específicamente ...
			**#objectName: Objeto ActionContext que se ha creado utilizando las etiquetas de 
			datos Struts2 con el alcance de acción predeterminado (por ejemplo 
			<s:set name="foo" value="'Testing'" />, referenciado por <s:property value="#foo" />)
			#parameters.objectName: parámetro de solicitud
			#request.objectName: atributo de ámbito de solicitud
			#session.objectName: atributo de ámbito de sesión
			#application.objectName: atributo de ámbito de aplicación
			#attr.objectName: atributo en la página, solicitud, sesión o ámbito de aplicación (buscado en ese orden)
		
		***Las referencias de mapa de alcance anteriores (parámetros, solicitud, sesión y aplicación) 
		se pueden realizar de dos maneras:

			#scopeName.objectName o
			#scopeName['objectName']
	
	****Uso de% (signo de porcentaje)

		***%{ OGNL expression } se utiliza para forzar la evaluación OGNL de un atributo que 
		normalmente se interpretaría como un literal de cadena.

			Ejemplo: <s:property value="myProperty" default="%{myDynamicDefaultValue}" />

	****Uso de @ (en el signo)

		El símbolo @ se usa para hacer referencias a propiedades y métodos estáticos. Tenga en 
		cuenta que es posible que necesite habilitar esto en sus propiedades 
			Struts2:struts.ognl.allowStaticMethodAccess=true

		Ejemplos:

			@my.package.ClassName@MY_STATIC_PROPERTY
			@my.package.ClassName@myStaticMethod
	

	****Uso de $ (signo de dólar)

		Struts2 OGNL no hace un uso especial del signo de dólar. Sin embargo, se puede usar para 
		evaluar expresiones JSTL normales. Por ejemplo:

			Struts2: <h1><s:property value="#pageTitle" /></h1>
			(es equivalente a ...)
			JSTL:<h1>${pageTitle}</h1>
*******************************************************************************************


********************************************************************************************
********************************************************************************************
************************************************Obtenido Valores de Tipos Indexados con OGNL
	Definir un arreglo con los enteros del 1 al 5 lo hacemos de la siguiente forma:
	new int[] {1, 2, 3, 4, 5}
	<h3>Valores Indexados:</h3>
	<s:property value="new int[]{1, 2, 3, 4, 5}" />
	<h3>Valore Indexados en un select:</h3>
	<s:select name="valores" list="{'Uno', 'Dos', 'Tres', 'Cuatro', 'Cinco'}" />
	<h3>Indexar un map:</h3>
	<s:select name="valores" list="#{'uno':'1', 'dos':'2', 'tres':'3', 'cuatro':'4', 'cinco':'5'}"/>
	<h3>Indexar un map utilizando la clase:</h3>
	<s:select name="valores" list="#@java.util.TreeMap@{'uno':'1', 'dos':'2', 'tres':'3', 'cuatro':'4', 'cinco':'5'}"/>
	<h3>Indexar utilizando la clase linkedHashMap:</h3>
	<s:property value="#@java.util.LinkedHashMap@{'uno':'1', 'dos':'2','tres':'3','cuatro':'4','cinco':'5'}" />
	<h3>Indexar utilizando la clase TreeMap:</h3>
	<s:property value="#@java.util.TreeMap@{'uno':'1', 'dos':'2', 'tres':'3', 'cuatro':'4','cinco':'5'}" />
	<h3>Indexar utilizando la clase HashMaap:</h3>
	<s:property value="#@java.util.HashMap@{'uno':'1', 'dos':'2', 'tres':'3', 'cuatro':'4','cinco':'5'}" />
********************************************************************************************





********************************************************************************************
********************************************************************************************
**********************************************************************Tag de struts Iterator
	Struts 2 proporciona una etiqueta especial que nos permite iterar a través de todos los 
	valores de un arreglo o colección, la etiqueta "iterator".	La etiqueta "iterator" 
	recibirá el arreglo o colección a través del cual ciclaremos para obtener sus valores. 
	Indicamos este arreglo o colección usando el atributo "value". En este caso indicaremos 
	que el valor a través del cual queremos	iterar es arreglo llamado "correos":
		<s:iterator value="correos">
		</s:iterator>
	Ya podemos ciclar a través de todos los elementos del arreglo, ahora necesitamos una 
	manera de obtener el elemento actual dentro del ciclo. Para esto usamos el atributo "var", 
	en este indicaremos el nombre de la variable que mantendrá	el valor del elemento actual. 
	No debemos de preocuparnos por crear esta variable, ya que Struts 2 la creara
	automáticamente y la pondrá a nuestra disposición. Esta variable será del tipo de elementos 
	que sea mantenido por	nuestro arreglo o colección:
		<s:iterator value="correos" var="correo">
		</s:iterator>
	Algunas veces querremos poder obtener alguna información relativa a la iteración, como 
	el índice del elemento actual, el	número total de elementos que tiene el arreglo o 
	colección, si el elemento actual es par o impar, etc. Para obtener esta	información 
	podemos indicarle a la etiqueta "iterator" que la coloque, como una instancia de la 
	clase "IteratorStatus"	y que la ponga a nuestra disposición en una variable. Para esto, 
	indicamos el nombre de la variable en el atributo	"status":
		<s:iterator value="correos" var="correo" status="estatus">
		</s:iterator>
	El estatus es una variable que no será colocada dentro en la raíz del stack de Struts 2 ,
	por lo que es necesario hacer referencia a el usando el operador "#". Ahora que podemos 
	iterar a través de cada uno de los elementos de nuestro arreglo, solo hace falta mostrar 
	el valor de	cada uno de estos elementos, adicionalmente también mostraremos su índice. 
	Colocaremos estos valores en una lista	para que se vea más presentable:
	<ul>
		<s:iterator value="correos" var="correo" status="estatus">
			<li><s:property value="#estatus.index" /> - <s:property value="correo" /></li>
		</s:iterator>
	</ul>
********************************************************************************************





********************************************************************************************
********************************************************************************************
************************************************************************Carga del valueStack

	ValueStack stack = ActionContext.getContext().getValueStack();
********************************************************************************************




********************************************************************************************
********************************************************************************************
****************************************************************************************ONGL

	ONGL significa Object Graphic navigation Languaje, y es una herramienta que surgió
	en Struts 2 con el objetivo de poder acceder a nuestros objetos Java desde la vista
	(JSP’s).

	Con esto queremos decir que los atributos de nuestras clases Action o cualquier otra
	clase que se agregue al stack de variables de Struts, podremos accederlo
	directamente desde nuestros JSP’s de varias maneras, ya sea desde etiquetas de
	Struts, utilizando Expression Language, Scriplets, o cualquier otra variante de nuestra
	vista para poder acceder a las propiedades de nuestras clases Java.

	OGNL usa un contexto estándar de nombres para evaluar las expresiones, esto quiere decir 
	que dependiendo de qué tan "profundo" esté nuestro objeto en el grafo, podremos hacer 
	referencia a él de distintas formas. El objeto de más alto nivel en OGNL es un Map, al 
	cual llamamos "mapa de contexto" (context map) o simplemente "contexto".
	------------------------------------------------------------------------------------------
	OGNL maneja siempre un objeto raíz dentro del contexto. Este objeto raíz es el objeto 
	default al que se hacen las llamadas, a menos que se indique lo contrario. Cuando usamos 
	una expresión, las propiedades del objeto raíz pueden ser referenciadas sin ninguna marca 
	especial, esto quiere decir que si nuestro objeto tiene una propiedad llamada "nombre", 
	hacemos referencia a él simplemente con la expresión "nombre". Las referencias a otros 
	objetos son marcadas con un signo de número (#).
	Ej:
		#foo.blah   //regresa foo.getBlah()
		#bar.blah   //regresa  bar.getBlah()
		blah        //regresa foo.getBlah(), porque foo es la raíz
	------------------------------------------------------------------------------------------
	En el OGNL estándar solo se tiene una raíz, sin embargo en el OGNL de Struts 2 se tiene 
	un "ValueStack", el cual permite simular la existencia de varias raíces. Todos los objetos 
	que pongamos en el "ValueStack" se comportarán como la raíz del mapa de contexto.

	------------------------------------------------------------------------------------------
	el OGNL de Struts 2 tiene un "PropertyAccessor" especial que buscará automáticamente en 
	todos los objetos del stack (de arriba a abajo) hasta que encuentre un objeto con la 
	propiedad que estamos buscando.

	------------------------------------------------------------------------------------------
	Struts 2 agrega soporte para índices en el ValueStack. Todo lo que debemos hacer es:


	[0].nombre  //llama a animal.getNombre()
	[1].nombre  //llama a persona.getNombre()
	------------------------------------------------------------------------------------------
	Cuando realizamos una petición para el Action, el DispatcherFilter ejecutará el método 
	"execute" del Action, después colocará este Action en la cima del ValueStack con lo cual 
	lo tendremos disponible.
	Además del ValueStack, Struts 2 coloca otros objetos en el ActionContext, incluyendo Maps 
	que representan los contextos de "application", "session", y "request". Una representación 
	de esto puede verse en la siguiente imagen:

	Recuerden que para hacer referencia a los objetos del contexto que NO son la raíz debemos
	preceder el nombre del objeto con el símbolo de gato (#).

	Hablaré brevemente de estos objetos:

	"application" representa el ApplicationContext, o sea, el contexto completo de la aplicación.
	"session" representa el HTTPSession, o sea, la sesión del usuario.
	"request" representa el ServletRequest, o sea, la petición que se está sirviendo.
	"parameters" representa los parámetros que son enviados en la petición, ya sea por GET o por POST.
	"attr" representa los atributos de los objetos implícitos. Cuando preguntamos por un atributo, 
	usando "attr", este busca el atributo en los siguientes scopes: page, request, session, 
	application. Si lo encuentra, regresa el valor del atributo y no continúa con la búsqueda, 
	sino regresa un valor nulo.

	Ej:

	@Namespace(value = "/")
	@Action(value = "datos", results ={@Result(location = "/datos.jsp")})
	public class DatosAction extends ActionSupport{

	}

	@Override
	public String execute() throws Exception
	{
	   ActionContext.getContext().getSession().put("datoSesion", "dato en la sesion");

	   return SUCCESS;
	}

	Como el objeto "session" no es la raíz del mapa de contexto, es necesario hacer referencia
	a él de la siguiente forma:

	<s:property value="#session.datoSesion" />

	--------------------------------------------------------------------------------------------


	Se crea clase bean Domicilio1.java-----------------------------------------------------------
	public class Domicilio1 {

	    private String calle;
	    private int numeroCalle;
	    private String pais;

	    public String getCalle() {
	        return calle;
	    }

	    public void setCalle(String calle) {
	        this.calle = calle;
	    }

	    public int getNumeroCalle() {
	        return numeroCalle;
	    }

	    public void setNumeroCalle(int numeroCalle) {
	        this.numeroCalle = numeroCalle;
	    }

	    public String getPais() {
	        return pais;
	    }

	    public void setPais(String pais) {
	        this.pais = pais;
	    }

	    @Override
	    public String toString() {
	        return "Domicilio{" + "calle=" + calle + ", numeroCalle=" + numeroCalle + ", pais=" + pais + '}';
	    }
	}

	Se crea clase bean Persona.java-------------------------------------------------------------
	public class Persona {

	    private String nombre;
	    Domicilio1 domicilio;

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

		

	    public Domicilio1 getDomicilio() {
	        return domicilio;
	    }

	    public void setDomicilio(Domicilio1 domicilio) {
	        this.domicilio = domicilio;
	    }

		@Override
		public String toString() {
			return "Persona [nombre=" + nombre + ", domicilio=" + domicilio + "]";
		}

	    
	}

	Se crea action ValidarUsuarioAction.java----------------------------------------------------
	import com.opensymphony.xwork2.ActionSupport;
	import com.zeni.app.model.Persona;
	import org.apache.struts2.convention.annotation.*;

	@Results({
	    @Result(name="input", location="/WEB-INF/pages/bienvenido1.jsp"),
	    @Result(name="success", location ="/WEB-INF/pages/Bienvenido.jsp")
	})
	public class ValidarUsuarioAction extends ActionSupport {
		
		//Logger log = LogManager.getLogger(PersonasAction.class);

	    private Persona persona;

	    @Action("validarUsuario")
	    @Override
	    public String execute() {
	        if (persona != null) {
	            System.out.println("\n");
	            System.out.println("persona:" + persona.getNombre());
	            System.out.println("Calle:" + persona.getDomicilio().getCalle());
	            System.out.println("No. Calle:" + persona.getDomicilio().getNumeroCalle());
	            System.out.println("Pais:" + persona.getDomicilio().getPais());
	        }
	        else{
	            System.out.println("Persona con valor nulo");
	        }
	        return SUCCESS;
	    }

	    public Persona getPersona() {
	        return persona;
	    }

	    public void setPersona(Persona persona) {
	        this.persona = persona;
	    }    
	    
	}

	Se crea jsp de formulario Bienvenida.jsp----------------------------------------------------

	<%@page contentType="text/html" pageEncoding="UTF-8"%>
	<%@taglib  prefix="s" uri="/struts-tags" %>
	<!DOCTYPE html>
	<html>
	    <head>
	        <title>Formulario Personas</title>
	        <%--Agregamos los estilos basicos de Struts 2 --%>
	        <s:head />
	    </head>
	    <body>
	        <h1>Formulario de Personas (OGNL)</h1>
	        <s:form action="validarUsuario">
	            <s:textfield label="Nombre" name="persona.nombre" />
	            <s:textfield label="Calle (Domicilio)" name="persona.domicilio.calle" />
	            <s:textfield label="No. Calle (Domicilio)" name="persona.domicilio.numeroCalle" />
	             <s:textfield label="Pais" name="persona.domicilio.pais" />
	             <s:submit value="Enviar"/>
	        </s:form>
	        
	        <h2>Valores proporcionados</h2>
	        Nombres: <s:property value="persona.nombre"/></br>
	        Calle <s:property value="persona.domicilio.calle"/></br>
	        No. Calle: <s:property value="persona.domicilio.numeroCalle"/></br>
	        Pais: <s:property value="persona.domicilio.pais"/></br>
	        
	    </body>
	</html>
********************************************************************************************




********************************************************************************************
********************************************************************************************
****************************************************************************Tags y Etiquetas
	-----------------------------------------------------------------------<s:property/>
		Se usa desde los JSP para acceder a las propiedades del action
			<s:property value="propiedad"/>

	----------------------------------------------------------------------------<s:url/>
		Generar una url relativa a un recurso de nuestra aplicación.
			<link rel="shortcut icon" href='<s:url value="/favicon.ico"/>' />
			<link type="text/css" rel="stylesheet"   href="<s:url value='/css/styles.css'/>" />
		Generar una url absoluta. Esto no suele ser necesario puesto que la etiqueta simplemente devuelve la url indicada.
			<a href="<s:url value='http://danielme.com'  />">danielme.com</a>
		Generar la url correspondiente a un action.
			<a href="<s:url action="home.action"  />">home</a>
		Generar la url correspondiente a un action con parámetros.
			<s:url action="mainAction">
			  <s:param name="param1">valor1</s:param>
			  <s:param name="param2"><s:property value="parametroDos"/></s:param>
			</s:url>

	------------------------------------------------------<s:url/> con Parametros
		Paso de parametros por url
		<s:url Action="hello" var="HelloLink">
			<s:param name ="userName">PepeSan</s:param>
		</s:url>
		<p><a href="<s:property" value="#HelloLink"/>">Hello Pepesan</a></p> //comilla no valida"

		Ej2:--------------------------------------------------------------------
		<s:url action='datosPestanaTransAction' var="urlt">
			<s:param name="idPaciente" value="%{paciente.idPaciente}">
			</s:param>
		</s:url>
		<s:a href="%{urlt}">
	  		<s:label key="persoas.pestanaTransplante"></s:label>
		</s:a> 
	-------------------------------------------<s:form> Generacion de formulario
		<s:form Action="hello">
			<s:textfield name="userName" label="Tu nombre" />
			<s:submit value="enviar" />
		</s:form>

	----------------------------------------------------------------------------
		<s:select label="Pets"
	        name="petIds"
	        list="petDao.pets"
	        listKey="id"
	        listValue="name"
	        multiple="true"
	        size="3"
	        required="true"
	        value="%{petDao.pets.{id}}"
 		/>

		/*<s:select label="Months" 
		        name="months"
		        headerKey="-1" headerValue="Select Month"
		        list="#{'01':'Jan', '02':'Feb', [...]}"
		        value="selectedMonth"
		        required="true"
		 />*/
********************************************************************************************





********************************************************************************************
********************************************************************************************
******************************************************************************Tags de struts
	Struts 2 agrega distintos tipos de tags dependiendo de la tarea que queremos realizar.
	Por ejemplo existen tags para:

	**Control: Permiten indicar qué y cómo deben mostrarse los componentes de la interfaz de
	usuario. Estos tags se utilizan principalmente como control de flujo y un poco de manejo
	de datos. Algunos ejemplos de este tipo de tags son: <s:if >, <s:elseif >,
	<s:iterator >, entre otros.
	
	**Datos: Son para manejar algunos valores de información, así como creación y
	manipulación de datos. Por ejemplo <s:a >, <s:url >, <s:text >,
	<s:property>, <s:param>, etc.
	
	**Formularios: Los componentes que pueden formar parte de un formulario. Por ejemplo:
	<s:form>, <s:checkbox>, <s:select>, <s:textfield>, <s:submit >, etc.
	
	**(UI no formularios): Información extra que se muestra pero que no pertenece a un
	formulario (mensajes de error o de resultados de las acciones). Por ejemplo,
	<s:actionmessage >, <s:actionmessage>, <s:fielderror>, etc.

	------------------------------------------------------------------------------------
	La directiva taglib le dice al contenedor Servlet que esta página utilizará las etiquetas 
	Struts 2 y que estas etiquetas estarán precedidas por s
		<%@taglib  prefix="s" uri="/struts-tags" %>
		-------------------------------------------
		La etiqueta s: property muestra el valor de la clase de acción propiedad 
		nombre > que es devuelto por el método getName () de la clase HelloWorldAction.
			El nombre enviado es: <s:property value="nombre" /> 

	Modificacion en menu-config.xml-------------------------------------------------------------
	<Item name="Bienvenido" title="Bienvenido" page="/Bienvenido.html" 
	roles="ROLE_USER,ROLE_CLIENTE_VIEW,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION,ROLE_BOLETOS,ROLE_SUBPRODUCTOS,ROLE_IMPUESTOS" width="250"/> 
	

	Modificacion en struts.xml------------------------------------------------------------------
 	<action name="Bienvenido">
		<result>/WEB-INF/pages/Bienvenido.jsp</result>
	</action>
	
	Se crea Bienvenido.jsp----------------------------------------------------------------------
	<%@page contentType="text/html" pageEncoding="UTF-8"%>
	<%@taglib  prefix="s" uri="/struts-tags" %>
	<!DOCTYPE html>
	<html>
	    <head>
	        <title>Formulario Personas</title>
	        <%--Agregamos los estilos basicos de Struts 2 --%>
	        <s:head />
	    </head>
	    <body>
	        <h1>Tags con Struts 2</h1>
	        <s:form action="validarUsuario">
	            <s:textfield label="Nombre" name="nombre" />
	            <s:submit value="Enviar"/>
	        </s:form>

	        <s:if test="nombre!=null">
	            <s:if test = "nombre == 'Karla'">
	                Seleccionaste a 'Karla'. 
	            </s:if>
	            <s:elseif test = "nombre == 'Juan'">
	                Seleccionaste a 'Juan'
	            </s:elseif>
	            <s:else>
	                El nombre enviado es: <s:property value="nombre" />     
	            </s:else>
	        </s:if>        
	    </body>
	</html>


	Se crea ValidarUsuarioAction.java-----------------------------------------------------------
	import com.opensymphony.xwork2.ActionSupport;
	import com.zeni.app.model.Persona;
	import org.apache.struts2.convention.annotation.*;

	@Results({
	    @Result(name="input", location="/WEB-INF/pages/bienvenido1.jsp"),
	    @Result(name="success", location ="/WEB-INF/pages/Bienvenido.jsp")
	})
	public class ValidarUsuarioAction extends ActionSupport {
		
		//Logger log = LogManager.getLogger(PersonasAction.class);

		private String nombre;
		
		@Action("validarUsuario")
		@Override
		public String execute() {	        
		    return SUCCESS;
		}

		public String getNombre() {
		    return nombre;
		}

		public void setNombre(String nombre) {
		    this.nombre = nombre;
		}
	    
	}


	Ejemplo____________________________________________________________________________


	/*<s:if test="%{operaciones != null}">
		<appfuse:verysimplegrid id="garantiasMFCaucionpGrid"
					collection="${operaciones}"  var="item"
					rowCountVar="rowCount" stateful="false" rowPerPage="${rowPerPage}"
					heightRow="25" height="250" width="850" resizable="true" 
					showPropertyButton="false" selectionMode="multiple" rowSelectable="true"
					addRowButton="false">
			
					<ria:row id="row${rowCount}">
					<ria:simplegridcolumn id="fecha" label="Fecha Oper." width="100" object="${item.matOperacion.fecha}" style="text-align:center;" formatter="formatFecha"/>
					<ria:simplegridcolumn id="producto" label="Producto." width="120" object="${item.matOperacion.matProducto.nombre}" style="text-align:center;" />				
							<ria:simplegridcolumn id="volumneOp" label="Vol Oper" width="80" object="${item.matOperacion.volumen}" style="text-align:right;" />
					<ria:simplegridcolumn id="cuenta" label="Cuenta Cliente" width="300px" sortable="false"
			    	object="${(item.matOperacion.cuentaCliente!=null && item.liquida_a!=null && (item.matOperacion.mercado.id==-6 || item.matOperacion.mercado.id==-9))?
			    			  item.liquida_a.matOperacion.cuentaCliente.cuentaCliDescr:
			    	          item.matOperacion.cuentaCliente!=null?item.matOperacion.comitente.cuentaClienteDescr:item.matOperacion.comitente.clienteDescr}"/>		    
			    	<ria:simplegridcolumn id="comitente" label="Comitente" width="70px" style="text-align:left;" exportedString="@S"
			    		object="${item.matOperacion.rechazoComitente!=null? item.matOperacion.rechazoComitente : item.matOperacion.comitente.numero}" />
			    	<ria:simplegridcolumn label='DIVISION' object="${item.matOperacion.cuentaCliente.id}" width="60px" id="Cta. Comprador" style="text-align:center;"/>
					<ria:gridinnerexport id="innerexport" filename="Comprobantes por periodo"/>
			  	</ria:row>
		</appfuse:verysimplegrid>
	</s:if>
	<s:else>
		<li class="buttonBar bottom">
		    <c:if test="${empty mfCaucion.id}">
			<input type="button" class="button" id="simularButton" value="Simular" onclick="javascript:onSimular();" style="width:160px;"/>
			<input type="button" class="button" id="facturarButton" value="Facturar" onclick="javascript:onFacturar();" style="width:160px; display: none;"/>
		    </c:if>
		    <c:if test="${!empty mfCaucion.id && empty mfCaucion.facturaVencimiento}">
		    <span><b>VENCIMIENTO </b></span>
			<input type="button" class="button" id="simularButton" value="Simular" onclick="javascript:onSimularVencimiento();" style="width:160px;"/>
			<input type="button" class="button" id="facturarButton" value="Facturar" onclick="javascript:onFacturarVencimiento();" style="width:160px; display: none;"/>
		    </c:if>
		</li>
	</s:else>*/
********************************************************************************************




********************************************************************************************
********************************************************************************************
**********************************************************************Tags de struts <s:file
	Referencia -->http://puntocomnoesunlenguaje.blogspot.com/2013/05/clase-file-java.html
	Permite la exploracion de archivos para cargar en el explorador
	Ej_Action___________________________________________________________________________
		public class LoginAction extends ActionSupport{
			private File fileUpload = new File("N:\\DOC. CLIENTES\\");
			private String fileUploadContentType;
			private String fileUploadFileName;

			public String getFileUploadContentType() {
				return fileUploadContentType;
			}

			public void setFileUploadContentType(String fileUploadContentType) {
				this.fileUploadContentType = fileUploadContentType;
			}

			public String getFileUploadFileName() {
				return fileUploadFileName;
			}

			public void setFileUploadFileName(String fileUploadFileName) {
				this.fileUploadFileName = fileUploadFileName;
			}

			public File getFileUpload() {
				return fileUpload;
			}

			public void setFileUpload(File fileUpload) {
				this.fileUpload = fileUpload;
			}

			public String execute() throws Exception{		
				return SUCCESS;		
			}
			
			public String display() {
				return NONE;
			}
		}
	Struts_xml_________________________________________________________________________
		<action name="resultAction" class="com.zeni.app.webapp.action.LoginAction">
           	<result name="success">/WEB-INF/pages/Bienvenido.jsp</result>
	    	<result name="input">/WEB-INF/pages/saludos.jsp</result>
		</action>
	Saludos_jsp________________________________________________________________________
		<body>
			<h1>Struts 2  file upload example</h1>

			<s:form action="resultAction" namespace="/" method="POST" enctype="multipart/form-data">

				<s:file name="fileUpload" label="Select a File to upload" size="40" />

				<s:submit value="submit" name="submit" />
				
			</s:form>

		</body>
	Bienvenido_jsp_____________________________________________________________________
		<body>
			<h1>Struts 2 file upload example</h1>

			<h2>
			   File Name : <s:property value="fileUploadFileName"/> 
			</h2> 

			<h2>
			   Content Type : <s:property value="fileUploadContentType"/> 
			</h2> 

			<h2>
			   File : <s:property value="fileUpload"/> 
			</h2>
		</body>

	Ejemplo____________________________________________________________________________

		<fieldset id="nameFile">
	    	<legend><fmt:message key="documentoRespaldatorio.ubicacionArchivo"/></legend>
	    		<s:file name="fileUpload" label="%{getText('parseaTxtForm.file')}" 
	    		cssClass="text file" cssStyle="width:320px;" required="false" 
	    		onchange="ObtieneRuta(this);"  />
    	</fieldset>

		private File fileUpload;
	
	
		public File getFileUpload() {
			return fileUpload;
		}

		public void setFileUpload(File fileUpload) {
			this.fileUpload = fileUpload;
		}

		fileUpload= new File("N:\\DOC. CLIENTES\\"+cuitString);
********************************************************************************************




*********************************************************************mensajes a msg de vist
*******************************************************************************************
*******************************************************************************************
	saveMessage("No existen Slip nuevos para generar");
	////
*******************************************************************************************





**************************************************************************mensajes de error
*******************************************************************************************
*******************************************************************************************
	addActionError("La fecha de inicio y de fin son necesarias para la busqueda");
	////
*******************************************************************************************





*******************************************************Borrar conjunto de mensajes de error
***********************************************************************setFieldErrors(null)
*******************************************************************************************
	//Referencia --> https://codeday.me/es/qa/20190510/671956.html
	Valido para la primera vez que se carga la pagina
	@Override
	public void validate(){ 
      if (getNumeroContrato()==null){
         setFieldErrors(null); 
      }
	}
*******************************************************************************************



Formularios en Struts 

***********************************************************************Formularios Tutorial
************************************************************Recepción de parámetros simples
*******************************************************************************************
	***Pasos basicos para obtener parametrod de un formulario:
	Colocar un formulario en nuestra JSP usando la etiqueta <s:form>
	Colocar los campos del formulario usando las etiquetas correspondientes de Struts
	Crear un Action y colocar setters para cada uno de los elementos que recibiremos a través del formulario
	Procesar los datos del formulario en el método "execute"
	**Persona.java-----------------------------------------------------------------
		public class Persona implements Serializable{
			/**
			 * 
			 */
			private static final long serialVersionUID = 5390763990454171995L;
			private String nombre;
			private String username;
			private String password;
			private int edad;
			private Date fechaNacimiento;	
			
			public Persona() {
				super();
			}
			
			public Persona(String nombre, String username, String password, int edad,
					Date fechaNacimiento) {
				super();
				this.nombre = nombre;
				this.username = username;
				this.password = password;
				this.edad = edad;
				this.fechaNacimiento = fechaNacimiento;
			}
			public String getNombre() {
				return nombre;
			}
			public void setNombre(String nombre) {
				this.nombre = nombre;
			}
			public String getUsername() {
				return username;
			}
			public void setUsername(String username) {
				this.username = username;
			}
			public String getPassword() {
				return password;
			}
			public void setPassword(String password) {
				this.password = password;
			}
			public int getEdad() {
				return edad;
			}
			public void setEdad(int edad) {
				this.edad = edad;
			}
			public Date getFechaNacimiento() {
				return fechaNacimiento;
			}
			public void setFechaNacimiento(Date fechaNacimiento) {
				this.fechaNacimiento = fechaNacimiento;
			}
			
		}



	**Saludos.jsp------------------------------------------------------------------
		<%@taglib uri="/struts-tags" prefix="s" %>
		<html>
			<head>
				<title><s:text name="form.titulo" /></title>
			</head>
			<body>
				<s:form action="datosUsuario">
					<s:textfield name="nombre" label="Nombre" />
					<s:textfield name="username" label="Username" />
					<s:password name="password" label="Password" />
					<s:textfield name="edad" label="Edad" />
					<s:textfield name="fechaNacimiento" label="Fecha de Nacimiento" />
					<s:submit value="Enviar" />
				</s:form>
			</body>
		</html>

	**LoginAction.java--------------------------------------------------------------
	*Se crea un  Action que se encargará de procesar los datos del mismo. Recuerden que 
	para recibir los datos del formulario debemos colocar los atributos en los que se 
	almacenarán estos datos, y setterspara que los interceptores correspondientes puedan 
	inyectar los valores dentro del Action:

	*crear un Usuario y mostrar sus datos en otra página. Para hacer eso debemos colocar 
	un atributo que almacene una referencia al Usuario, y un getter para poder obtener esta 
	referencia.

	*sobre-escribimos el método "execute" para crear un nuevo objeto de tipo Usuario y 
	establecer sus datos usando los valores que recibimos del formulario.

		@Results({@Result(name="success", location="/WEB-INF/pages/Bienvenido.jsp")})
		public class LoginAction extends ActionSupport{
			
			//Atributos para ser capturados del form
			private String nombre;
			private String username;
			private String password;
			private int edad;
			private Date fechaNacimiento;
			//Se cre atributo para poder almacenar referencia al atributo usuario
			private Persona usuario;
			
			//Se crea metodo get para que devuelva la referencia
			public Persona getUsuario() {
				return usuario;
			}
			
			public void setEdad(int edad)	{
				this.edad = edad;
			}

			public void setUsuario(Persona usuario) {
				this.usuario = usuario;
			}
			public void setFechaNacimiento(Date fechaNacimiento)	{
				this.fechaNacimiento = fechaNacimiento;
			}
			public void setNombre(String nombre)	{
				this.nombre = nombre;
			}
			public void setPassword(String password)	{
				this.password = password;
			}
			public void setUsername(String username)	{
				this.username = username;
			}
			
			//Se sobre-escribimos el método "execute" para crear un nuevo objeto de tipo Usuario y establecer sus datos
			//usando los valores que recibimos del formulario:
			@Action("datosUsuario")//se indica el metodo con la anotacion para que responda como un action
			@Override
			public String execute() throws Exception	{
				usuario = new Persona();
				usuario.setNombre(nombre);
				usuario.setUsername(username);
				usuario.setPassword(password);
				usuario.setEdad(edad);
				usuario.setFechaNacimiento(fechaNacimiento);
				return SUCCESS;
			}
		}

		**Bienvenido.java---------------------------------------------------------------
		<body>
			Nombre: <strong><s:property value="usuario.nombre" /></strong><br />
			Username: <strong><s:property value="usuario.username" /></strong><br />
			Password: <strong><s:property value="usuario.password" /></strong><br />
			Edad: <strong><s:property value="usuario.edad" /></strong><br />
			Fecha de Nacimiento: <strong><s:property value="usuario.fechaNacimiento" /></strong>
		</body>
*******************************************************************************************





*******************************************************************************************
*******************************************************************************************
*******************************************************************************Model Driven
	Model Driven es una forma que Struts 2 proporciona para poder establecer todos los parámetros que se reciben de un
	formulario directamente dentro de un objeto. Este objeto es conocido como el modelo.
	Usando este modelo nos evitamos estar llamando nosotros mismos a los setters de este objeto modelo.
	Esto también permite que si realizamos validaciones de datos del formulario (lo cual veremos cómo hacer un poco más
	adelante) estas se realizarán sobre este objeto modelo.
	Un interceptor especial, llamado model driven interceptor, se encarga de manejar todo esto de forma automática ^_^.
	Extenderemos nuestro ejemplo para usar model driven.
	Creamos una nueva clase Java, en el paquete "actions", llamada "UsuarioActionMD". Esta clase extenderá de
	"ActionSupport":
		public class UsuarioActionMD extends ActionSupport	{
		}
	**Para indicarle a Struts 2 que este Action será Model Driven, la clase "UsuarioActionMD" debe implementar la
	interface "ModelDriven", indicando de qué tipo de objeto será usado como modelo:
			public class UsuarioActionMD extends ActionSupport implements ModelDriven<Usuario>	{
			}
	**Ahora pondremos un objeto Usuario dentro de nuestra clase, con una variable de instancia, que será el objeto que
	usaremos como modelo:
		public class UsuarioActionMD extends ActionSupport implements ModelDriven<Usuario>	{
		private Usuario usuario = new Usuario();
			}
	**Es importante tener creado este objeto antes de que nuestro Action reciba alguna petición, por lo que podemos
	inicializarlo en la misma declaración o en el constructor del Action, en caso de tener alguno.
	La interface "ModelDriven" tiene tan solo un método: "getModel". Esté método no recibe ningún parámetro, y regresa
	el objeto que estamos usando como modelo:
		public Usuario getModel()	{
			return usuario;
		}
	**Lo único que queda es realizar algún proceso en el método "execute", que podría ser almacenar al Usuario en alguna
	base de datos, enviarlo por algún stream, etc. Como en este caso no haremos nada con el Usuario más que regresarlo
	para poder mostrar sus datos en una página, nuestro método "execute" solo regresará un valor de "SUCCESS".

	**Pero ¿qué ocurre si queremos recibir un parámetro que no sea un atributo del "usuario"? En ese caso, solo tenemos que
	agregar un nuevo atributo, con su correspondiente setter para establecer su valor (y su getter si es que pensamos
	recuperarlo posteriormente) dentro de nuestro Action.

	Ejemplo____________________________________________________________________________
		***UsuarioActionMD.java
		@Results({@Result(name="success", location="/WEB-INF/pages/Bienvenido.jsp")})
		public class UsuarioActionMD extends ActionSupport implements ModelDriven<Persona>{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private Persona usuario = new Persona();
			private int numero;
	
			public int getNumero() {
				return numero;
			}

			public void setNumero(int numero) {
				this.numero = numero;
			}
			
			public Persona getModel() {
				// TODO Auto-generated method stub
				return usuario;
			}
			
			@Action("datosUsuario")
			@Override
			public String execute() throws Exception	{
				return SUCCESS;
			}
		}
		----------------------------------------------------------------------------
		***saludos.jsp
			<body>
				<s:form action="datosUsuario">
					<s:textfield name="nombre" label="Nombre" />
					<s:textfield name="username" label="Username" />
					<s:password name="password" label="Password" />
					<s:textfield name="edad" label="Edad" />
					<s:textfield name="fechaNacimiento" label="Fecha de Nacimiento" />
					<s:textfield name="numero" label="Numero Confirmacion" />
					<s:submit value="Enviar" />
				</s:form>
			</body>
		----------------------------------------------------------------------------
		***Bienvenido.jsp
			<body>
				Nombre: <strong><s:property value="nombre" /></strong><br />
				Username: <strong><s:property value="username" /></strong><br />
				Password: <strong><s:property value="password" /></strong><br />
				Edad: <strong><s:property value="edad" /></strong><br />
				Fecha de Nacimiento: <strong><s:property value="fechaNacimiento" /></strong>
				Numero de Confirmacion: <strong><s:property value="numero" /></strong>
				<h1>Expression Languaje</h1>
				
				Nombre del atributo del action: ${edad}<br />
						
				<h1>EL y Variables Implicitas</h1>
				<ul>
					<li>Nombre Aplicaci&oacute;n: ${pageContext.request.contextPath}</li>
					<li>Navegador del Cliente: ${ header["User-Agent"] }</li>
					<li>Id Session: ${cookie.JSESSIONID.value}</li>
					<li>Web Server: ${pageContext.servletContext.serverInfo}</li>
					
			</body>
*******************************************************************************************




*******************************************************************************************
*******************************************************************************************
**********************************************************Recepción de múltiples parámetros
	Para poder recibir estos múltiples parámetros ^_^. Uno de los interceptores de Struts 2 
	se encargará de recibir los parámetros del formulario que tienen el mismo nombre; este 
	interceptor colocará los parámetros en un arreglo o una colección, dependiendo de qué es 
	lo que estemos esperando, y lo colocará en	nuestro Action usando el setter apropiado.
	Según la explicación anterior, debemos colocar el setter del atributo llamado "correos" 
	para poder establecer el arreglo de correos, y su getter para poder obtenerlo 
	posteriormente.

	Ejemplo____________________________________________________________________________
	***datos.jsp
		<s:form action="envioCorreo">
			<s:textfield name="nombre" label="Nombre" />
			<s:textfield name="correos" label="Correo" />
			<s:textfield name="correos" label="Correo" />
			<s:textfield name="correos" label="Correo" />
			<s:textfield name="correos" label="Correo" />
			<s:textfield name="correos" label="Correo" />
			<s:submit value="Enviar" />
		</s:form>

	***MultiplesDatosAction.java-------------------------------------------------
		@Results({@Result(name="success", location="/WEB-INF/pages/datosEnviados.jsp")})
		public class MultiplesDatosAction extends ActionSupport{
			private String[] correos;
			private String nombre;
			
			public String getNombre()	{
				return nombre;
			}
			public void setNombre(String nombre)	{
				this.nombre = nombre;
			}	
			public String[] getCorreos()	{
				return correos;
			}
			public void setCorreos(String[] correos)	{
				this.correos = correos;
			}
			
			@Action("envioCorreo")
			@Override
			public String execute() throws Exception	{
				return SUCCESS;
			}
		}

		***Si preferimos trabajar con una colección, como un Set o un List, lo
		único que tenemos que hacer es cambiar el tipo del atributo "correos" (junto con sus
		correspondientes getters y setters). Usemos un "Set", el cual es una colección que no permite elementos duplicados.
		Modifiquemos el atributo "correos" para que quede de la siguiente forma:
			private Set<String> correos;
			public Set<String> getCorreos(){
				return correos;
			}
			public void setCorreos(Set<String> correos)	{
				this.correos = correos;
			}

	***datosEnviados.jsp---------------------------------------------------------
		**La etiqueta "iterator" recibirá el arreglo o colección a través del cual ciclaremos para obtener sus valores. Indicamos
		este arreglo o colección usando el atributo "value". En este caso indicaremos que el valor a través del cual queremos
		iterar es arreglo llamado "correos":
			<s:iterator value="correos">
			</s:iterator>
		**Ya podemos ciclar a través de todos los elementos del arreglo, ahora necesitamos una manera de obtener el elemento
		actual dentro del ciclo. Para esto usamos el atributo "var", en este indicaremos el nombre de la variable que mantendrá
		el valor del elemento actual. No debemos de preocuparnos por crear esta variable, ya que Struts 2 la creara
		automáticamente y la pondrá a nuestra disposición. Esta variable será del tipo de elementos que sea mantenido por
		nuestro arreglo o colección:
			<s:iterator value="correos" var="correo">
			</s:iterator>
		**Algunas veces querremos poder obtener alguna información relativa a la iteración, como el índice del elemento actual, el
		número total de elementos que tiene el arreglo o colección, si el elemento actual es par o impar, etc. Para obtener esta
		información podemos indicarle a la etiqueta "iterator" que la coloque, como una instancia de la clase "IteratorStatus"
		y que la ponga a nuestra disposición en una variable. Para esto, indicamos el nombre de la variable en el atributo
		"status":

		**El estatus es una variable que no será colocada dentro en la raíz del stack de Struts 2 (el cual explicamos en el segundo
		tutorial de la serie), por lo que es necesario hacer referencia a el usando el operador "#".
		<body>
			<s:property value="nombre" />
			<ul>
				<s:iterator value="correos" var="correo" status="estatus">
					<li><s:property value="#estatus.index" /> - <s:property value="correo" /></li>
				</s:iterator>
			</ul>
		</body>
*******************************************************************************************




*******************************************************************************************
*******************************************************************************************
****************************************************4. Recepción de Parámetros Desconocidos
	***Esperando recibir, dentro de nuestro Action, un conjunto de parámetros de los cuales no
	conocemos ni su cantidad ni sus nombres, como por ejemplo cuando estamos haciendo un 
	filtrado de datos y no sabes qué filtros recibiremos y cuáles serán los valores de estos 
	filtros, o cuando la generación de componentes se realiza de forma dinámica.
	Para estos casos Struts 2 proporciona una manera de indicar que deberá entregarnos todos 
	los parámetros en un objeto tipo "java.util.Map". Las llaves de este mapa representarán 
	los nombres de los parámetros, y sus valores representarán un arreglo de Strings con los 
	valores correspondientes de cada parámetro. 
	Para lograr que Struts 2 nos proporcione estos parámetros, nuestro Action debe implementar 
	la interface "ParameterAware". Esta interface se ve de la siguiente forma:
		public interface ParameterAware		{
			void setParameters(Map<String, String[]> parameters);
		}
	Esta interface solo tiene un método llamado "setParameters", que tiene como argumento el mapa de
	parámetros recibidos en la petición.

	Ejemplo____________________________________________________________________________
	***ParameterAware.java---------------------------------------------------------
		public interface ParameterAware {
			void setParameters(Map<String, String[]> parameters);
		}

	***Datos.java------------------------------------------------------------------
	**Primero, indicaremos que haremos uso de las etiquetas de Struts 2 usando el taglib correspondiente:
		<%@taglib prefix="s" uri="/struts-tags" %>

		<!-- Se incluye la librerias de Jquery desde cdn-->
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
						var elementoNuevo = $("#valor" + num).clone().attr('id','valor'+numeroSiguiente).attr("name", "valor"+numeroSiguiente);
						var etiquetaNueva = $("label[for=valor"+num+"]").clone().attr("for","valor"+numeroSiguiente).text("Valor " + numeroSiguiente + ": ");
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
	***LoginAction.java----------------------------------------------------------------
		@Results({@Result(name="success", location="/WEB-INF/pages/Bienvenido.jsp")})
		public class LoginAction extends ActionSupport implements ParameterAware{
			
			//Variable que nos permita almacenar la referencia al mapa que los interceptores inyectarán con la
			//lista de parámetros recibidos:
			private Map<String, String[]> parametros;
			
			//Implementacion sel método "setParameters" de la interface "ParameterAware":
			public void setParameters(Map<String, String[]> parametros)		{
				this.parametros = parametros;
			}
			
			//	Getter para el mapa de parámetros, para poder recuperarlo desde
			//	la JSP correspondiente:
			public Map<String, String[]> getParametros()	{
				return parametros;
			}
			
			@Action("multiparametros")
			@Override
			public String execute() throws Exception	{
				return SUCCESS;
			}
		}
*******************************************************************************************




*******************************************************************************************
*******************************************************************************************
************************************************Marca de campo obligatorio en tag de spring
	Struts 2 es que nos permiten indicar si cuáles
	campos son obligatorios, con lo que se le colocará una pequeña marca en la etiqueta para que el usuario sepa cuáles
	campos son los que está obligado a llenar. Esta marca es solo una ayuda visual, no realizará ningún proceso de validación
	del campo.
	Para agregar dicha marca e indicar que el campo será obligatorio, debemos colocar el valor de "true" en el atributo
	"required" de las etiquetas del formulario. En este caso todos los campos, con excepción del teléfono, serán requeridos:
	<s:form action="validacionDatos">
		<s:textfield name="nombre" label="Nombe" required="true" />
		<s:textfield name="username" label="Username" required="true" />
		<s:password name="password" label="Password" required="true" />
		<s:textfield name="email" label="Email" required="true" />
		<s:textfield name="edad" label="Edad" required="true" />
		<s:textfield name="telefono" label="Telefono" />
		<s:submit value="Enviar" />
	</s:form>
*******************************************************************************************




*******************************************************************************************
*******************************************************************************************
**********************************************Mostrar los errores en una lista en la pagina
	Debido al tema ("theme") que se usa por default para construir el formulario, los errores serán mostrados directamente
	sobre los campos que no pasen la validación. Sin embargo, si quisiéramos mostrar, de manera adicional, una lista con
	todos los errores del formulario, debemos agregar en nuestra JSP la etiqueta "<s:fielderror />". Solo la colocamos
	sobre el formulario, de la siguiente forma:
		
		<s:fielderror />
*******************************************************************************************




*******************************************************************************************
*******************************************************************************************
********************************************************************************Validadores
	Struts 2 proporciona varias maneras de realizar validaciones de forma automática para los 
	datos de nuestros formularios.
	Las validaciones son realizadas por dos interceptores: "validation" y "workfllow". El 
	primero realiza las validaciones y crea una lista de errores específicos para cada uno 
	de los campos que no pase la validación. Posteriormente el interceptor "workflow" verifica 
	si hay errores de validación; si encuentra algún error regresa un resultado "input", por lo 
	que es necesario que proporcionemos un result con este nombre.
	
	Struts 2 tiene básicamente tres formas de realizar validaciones:
		Validaciones mediante un archivo XML
		Validaciones mediante anotaciones
		Validaciones manuales

	La clase "nombre-ClaseAction" tendrá setters y getters para cada uno de los valores que 
	recibiremos del formulario. En	este caso los getters son importantes por dos razones, 
	la primera es que queremos que el formulario sea repoblado con los valores que el usuario 
	ya habia introducido (los cuales solo pueden obtenerse a partir de los getters); la segunda 
	es	que, por alguna razón que no alcanzo a entender, las validaciones se realizan sobre 
	los getters de las propiedades. 

	Si existe un error de validación el interceptor correspondiente regresará un resultado con
	valor "input". Este resultado debe enviarnos a una página en la que se mostrará la lista 
	de errores de datos de entrada. Normalmente esta página es el mismo formulario de entrada 
	para que el usuario pueda corregir los datos que introdujo de forma incorrecta.

	Si observamos el código fuente de la pagina generada veremos que cada uno de los errores marcados
	está colocado en un elemento que tiene como clase CSS "errorMessage", por lo que podemos personalizar la forma en
	la que se muestran los errores de validación de la aplicación a través de una hoja de estilos.

	5.1 Validaciones usando archivos XML-----------------------------------------------

		Para realizar validaciones usando archivos en XML debemos crear un archivo por cada una 
		de las clases que será validada. El nombre de este archivo debe seguir una convención 
		muy sencilla: 
			<NombreDeNuestroAction>-validation.xml
		O sea, que debe tener el mismo nombre del Action + la cadena "-validation" y la extensión 
		".xml". El archivo debe estar colocado en el mismo directorio que la clase Action que 
		validará. Dentro de este archivo se indicará cuáles campos serán validados y qué validaciones 
		se le aplicarán. Cada una de estas validaciones tiene un parámetro obligatorio, que es el 
		nombre del campo que validará. Adicionalmente pueden o no tener más parámetros.
		
		Struts 2 ya viene con algunas validaciones predeterminadas que podemos usar, aunque también 
		tenemos la posibilidad 	de crear nuestras propias validaciones en caso de ser necesario. 
		
		Las validaciones incuidas con Struts 2 se muestran en la siguiente tabla:
		
		Validador 				Descripción
		required 				Verifica que el campo especificado no sea nulo.
		requiredstring    		Verifica que un campo de tipo String no sea nulo y que tenga un longitud mayor a 0.
		stringlength 			Verifica que una cadena tenga una cierta longitud.
		int, long, y short		Verifica que el int, long, o short especificado estén dentro de un rango determinado.
		double 					Verifica que el valor double especificado esté dentro de cierto rango.
		date					Verifica que la fecha proporcionada esté dentro del rango proporcionado. Por
								default se usa el formatoDate.SHORT para indicar las fechas.
		email 					Verifica que el campo cumpla con el formato de una dirección de email válida
		url 					Verifica que el campo cumpla con el formato de una URL válida.
		visitor					Permite enviar la validación a las propiedades del objeto del Action usando los
								propios de archivos de validación del objeto. Esto permite usar ModelDriven y
								administrar las validaciones de los objetos de modelo en un solo lugar.
		conversion 				Verifica si ocurre un error de conversión en el campo.
		expression 				Realiza una validación basada en una expresión regular en OGNL.
		fieldexpression 		Valida un campo usando una expresión OGNL.
		regex 					Valida un campo usando una expresión regular.

			Sintaxis-----------------------------------------------------------
		***Lo siguiente es indicar cada campo que será validado usando el elemento "<field>", 
		e indicamos el nombre del campo	que queremos validar en su atributo "name":
			<field name="nombre">
			</field>
		***Posteriormente indicamos cada una de las validaciones que se aplicarán a ese campo, 
		usando el elemento "<fieldvalidator>"	y su atributo "type":
		<field name="nombre">
			<field-validator type="requiredstring">
			</field-validator>
		</field>

		***Para agregar un parámetro a la validación usamos el elemento "<param>" y colocamos 
		el nombre del parámetro en su atributo "name". En este caso usaremos el parámetro "trim" 
		del validador "requiredstring" para indicar que deseamos que se eliminen los espacios en 
		blanco del inicio y fin de la cadena (en caso de existir) antes de que la validación sea
		aplicada:
			<field-validator type="requiredstring">
				<param name="trim">true</param>
			</field-validator>
		*** agregar un mensaje que será mostrado en caso de que la validación no sea exitosa. Para esto
		usamos el elemento "<message>":
			<field-validator type="requiredstring">
				<param name="trim">true</param>
				<message>El nombre del usuario es un campo obligatorio.</message>
			</field-validator>

		*** Si necesitáramos agregar más validaciones a este campo, solo bastaría con agregar un 
		segundo elemento "<fieldvalidator>". Agregamos un validador de tipo "stringlength" para 
		asegurarnos que el nombre que introduzca el usuario tenga una longitud mínima de 4 caracteres, 
		y una longitud máxima de 12. 
			<field-validator type="stringlength">
				<param name="trim">true</param>
				<param name="minLength">4</param>
				<param name="maxLength">12</param>
				<message>El nombre del usuario debe tener entre 4 y 12 caracteres</message>
			</field-validator>

			Ejemplo____________________________________________________________________________
			***LoginAction-validation.xml
				<?xml version="1.0" encoding="UTF-8"?>
				<!DOCTYPE validators PUBLIC "-//OpenSymphony Group//XWork Validator 1.0.2//EN"
				"http://www.opensymphony.com/xwork/xwork-validator-1.0.2.dtd">
				<validators>
					<!-- Se valida campo de nombre -->
					<field name="nombre">
						<!-- Se valida que contenga datos -->
						<field-validator type="requiredstring">
							<!-- Se añade parametro para que se eliminen los espacios en blanco del principio y final de la cadena -->
							<param name="trim">true</param>
							<!-- Se añade mensaje en caso de que no pase validacion -->
							<message>El nombre del usuario es un campo obligatorio.</message>
						</field-validator>
						<!-- Se valida longitud del contenido del campo nombre -->
						<field-validator type="stringlength">
							<param name="trim">true</param>
							<!-- Se añade parametros para minimo y maximo de longitud -->
							<param name="minLength">4</param>
							<param name="maxLength">12</param>
							<message>El nombre del usuario debe tener entre 4 y 12 caracteres</message>
						</field-validator>
					</field>
					<!-- Se valida campo username -->
					<field name="username">
						<field-validator type="requiredstring">
							<param name="trim">true</param>
							<message>El username es un campo obligatorio.</message>
						</field-validator>
						<field-validator type="stringlength">
							<param name="trim">true</param>
							<param name="minLength">6</param>
							<message>El username debe tener al menos 6 caracteres</message>
						</field-validator>
					</field>
					<!-- Se valida campo password -->
					<field name="password">
						<field-validator type="requiredstring">
							<param name="trim">true</param>
							<message>La contraseña es un campo obligatorio.</message>
						</field-validator>
						<field-validator type="stringlength">
							<param name="trim">true</param>
							<param name="minLength">6</param>
							<param name="maxLength">8</param>
							<message>La contraseña debe tener entre 6 y 8 caracteres</message>
						</field-validator>
						<!-- Se valida contenido de la cadena -->
						<field-validator type="regex">
							<!-- parametro que evalua que contenga una minuscula, una mayuscula y un numero -->
							<param name="expression">^\w*(?=\w*\d)(?=\w*[a-z])(?=\w*[A-Z])\w*$</param>
							<message>La contraseña debe ser alfanumérica, debe tener al menos una letra mayúscula,
							una letra minúscula, y al menos un número.</message>
						</field-validator>
					</field>
					<!-- Se valida campo email -->
					<field name="email">
						<field-validator type="requiredstring">
							<param name="trim">true</param>
							<message>El correo electrónico es un campo obligatorio.</message>
						</field-validator>
						<!-- Se valida que contenga el formato correcto para email -->
						<field-validator type="email">
							<message>El correo electrónico está en un formato inválido.</message>
						</field-validator>
					</field>
					<!-- Se valida campo edad -->
					<field name="edad">
						<field-validator type="required">
							<message>La edad es un campo obligatorio.</message>
						</field-validator>
						<!-- Se valida que el campo contenga solo numeros -->
						<field-validator type="conversion">
							<message>La edad debe contener solo números enteros.</message>
						</field-validator>
						<!-- Se valida que los valores esten entre un determinado rango -->
						<field-validator type="int">
							<param name="min">10</param>
							<param name="max">200</param>
							<message>La edad proporcionada no está dentro del rango permitido.</message>
						</field-validator>
					</field>
				</validators>
			***LoginAction.java
				@Results({@Result(name="success", location="/WEB-INF/pages/Bienvenido.jsp"),
					@Result(name="input", location="/WEB-INF/pages/saludos.jsp")})
				public class LoginAction extends ActionSupport {
					
					private String nombre;
					private String username;
					private String password;
					private String email;
					private int edad;
					private String telefono;
					
					public void setEdad(int edad){
						this.edad = edad;
					}
					public void setEmail(String email){
						this.email = email;
					}
					public void setNombre(String nombre){
						this.nombre = nombre;
					}
					public void setPassword(String password){
						this.password = password;
					}
					public void setTelefono(String telefono){
						this.telefono = telefono;
					}
					public void setUsername(String username){
						this.username = username;
					}
					public int getEdad()	{
						return edad;
					}
					public String getEmail()	{
						return email;
					}
					public String getNombre()	{
						return nombre;
					}
					public String getPassword()	{
						return password;
					}
					public String getTelefono()	{
						return telefono;
					}
					public String getUsername()	{
						return username;
					}
					
					@Action("validacionDatos")
					@Override
					public String execute() throws Exception{
						System.out.println("nombre: " + nombre);
						System.out.println("username: " + username);
						System.out.println("password: " + password);
						System.out.println("email: " + email);
						System.out.println("edad: " + edad);
						System.out.println("telefono: " + telefono);
						return SUCCESS;
					}
				}
			***saludos.jsp
				<s:fielderror />
			
				<s:form action="validacionDatos">
					<s:textfield name="nombre" label="Nombe" required = "true" />
					<s:textfield name="username" label="Username" required = "true" />
					<s:password name="password" label="Password" required = "true"/>
					<s:textfield name="email" label="Email" required = "true"/>
					<s:textfield name="edad" label="Edad" required = "true"/>
					<s:textfield name="telefono" label="Telefono" />
					<s:submit value="Enviar" />
				</s:form>
			***Bienvenido.jsp
				<ul>
					<li>Nombre Aplicaci&oacute;n: ${pageContext.request.contextPath}</li>
					<li>Navegador del Cliente: ${ header["User-Agent"] }</li>
					<li>Id Session: ${cookie.JSESSIONID.value}</li>
					<li>Web Server: ${pageContext.servletContext.serverInfo}</li>
				</ul>

			Otros Ejemplos__________________________________________________________
			    ---------------------------------------------------------Double
			    <field name="analisisMuestra.honorarios">
			        <field-validator type="doubleRF">
			        	<param name="minInclusive">0</param>
			            <message key="errors.double.e.min.max"/>
			        </field-validator>
			    </field>

			    <field name="facturaProducto.cotizacionPesificacion">
			        <field-validator type="required">
			        	<message key="errors.required" />
			        </field-validator>
			        <field-validator type="double">
			            <param name="minExclusive">0</param>
			            <message key="facturaProducto.error.cotizacionPesificacionMayorACero"/>
			        </field-validator>
			    </field>
			    --------------------------------------------------------------
			    <field name="analisisMuestra.nroCertificadoAnalisis">
			        <field-validator type="requiredstring">
			            <message key="errors.required"/>
			        </field-validator>
				    <field-validator type="fieldexpression">
						<param name="expression">
					    	(!"0".equals(analisisMuestra.nroCertificadoAnalisis.trim()))
					    </param> 
					    <message key="analisisMuestra.error.certificadoIgualACero"/>
					</field-validator>
			    </field>
			    -------------------------------------------ValidaCampoFecha
			        <field name="cAI.fechaVencimiento">
			        <field-validator type="required">
			            <message key="errors.required"/>
			        </field-validator>
			        <field-validator type="fieldexpression">
						<param name="expression">
							!(cAI.fechaVencimiento.before(currentDate))
						</param>
				        <message>${getText("errors.fechaPosteriorAHoy", {getText("cAI.fechaVencimiento")})}</message>
					</field-validator>
			        <field-validator type="fieldexpression">
						<param name="expression">
							!(cAI.fechaVencimiento.before(cAI.fechaInicioVigencia)) and (cAI.fechaVencimiento.compareTo(cAI.fechaInicioVigencia) > 0)
						</param>
				        <message>${getText("errors.fechaPosterior", {getText("cAI.fechaVencimiento"),getText("cAI.fechaInicioVigencia")})}</message>
					</field-validator>
			    </field>

			    --------------------------------------------------------------
			    <field name="cartaCamara.fechaEmisionCarta">
			        <field-validator type="required">
			            <message key="errors.required"/>
			        </field-validator>
			        <field-validator type="date">
						<param name="min">1970-01-01T00:00:00</param>
						<message key="errors.date"/>
			        </field-validator>
			    </field>
			    -------------------------------------------ValidaExpression
			    <field name="cartaRectificatoria.contrato">
					<field-validator type="fieldexpression">
						<param name="expression">cartaRectificatoria.id>0 or (cartaRectificatoria.contrato.state.workflowDefinition.estadoActual!='10' and cartaRectificatoria.contrato.state.workflowDefinition.estadoActual!='60')</param>
						<message key="errors.contratoStateDenied" />
					</field-validator>
				</field>

	5.2 Validaciones usando anotaciones------------------------------------------------
		Las anotaciones que podemos usar para realizar las validaciones se encuentran en el paquete
		"com.opensymphony.xwork2.validator.annotations", y tenemos las siguientes validaciones disponibles:

			@ConversionErrorFieldValidator
			@DateRangeFieldValidator
			@DoubleRangeFieldValidator
			@EmailValidator
			@ExpressionValidator
			@FieldExpressionValidator
			@IntRangeFieldValidator
			@RegexFieldValidator
			@RequiredFieldValidator
			@RequiredStringValidator
			@StringLengthFieldValidator
			@UrlValidator
			@VisitorFieldValidator

		Existe una anotación por cada una de las validaciones que se tienen cuando trabajamos con los
		archivos XML; la explicación para cada una de las validaciones también es la misma.

		Esta anotación puede ser colocada tanto en el setter como en el getter de la propiedad que queremos validar. También
		podemos usar varias validaciones para una misma propiedad.

		Todas las anotaciones de validaciones proporcionan una serie de atributos que nos permiten configurar el
		comportamiento de la validación. Casi en todos los casos lo único que tendremos que configurar es el mensaje que se
		mostrará en caso de que la validación no sea correcta. Para eso usamos el atributo "message":
			-----------------------------------------Validacion de campo requerido
				@RequiredStringValidator(message = "El nombre de usuario es un campo obligatorio.")
				public void setNombre(String nombre){
					this.nombre = nombre;
				}

			------------------------------------------------Validacion de longitud
				@StringLengthFieldValidator(message = "Default message", key = "i18n.key", shortCircuit = true, trim = true, minLength = "5",  maxLength = "12")
				public void setUsername(String username){
					this.username = username;
				}
			--------------------------------------------Validacion de solo numeros
				@ConversionErrorFieldValidator(message="La edad debe contener solo números enteros.")
				public void setEdad(int edad){
					this.edad = edad;
				}
			----------------------------------Validacion para un Rango de numeros
				@IntRangeFieldValidator(min="0", max="50", message="La edad proporcionada no está dentro del rango permitido.")
				public int getEdad()	{
					return edad;
				}
			------------------------------------------------Validacion para Email
				@EmailValidator(message="El correo electrónico está en un formato inválido.")
				public void setEmail(String email){
					this.email = email;
				}
			----------------------------------------------Validacion de expresion
				@RegexFieldValidator(expression="^\\w*(?=\\w*\\d)(?=\\w*[a-z])(?=\\w*[A-Z])\\w*$",
						message="La contraseña debe ser alfanumérica, debe tener al menos una letra mayúscula," +
								"una letra minúscula, y al menos un número.")
				public void setPassword(String password){
					this.password = password;
				}
				
				@RegexFieldValidator(message="Numero Invalido", expression="\\d{3}-\\d{3}-\\d{4}") 
				public void setTelefono(String telefono){
					this.telefono = telefono;
				}
			----------------------------------------------Validacion de fecha
				@DateRangeFieldValidator(message = "Fecha invalida", key = "i18n.key", shortCircuit = true, min = "01/01/2019", max = "30/12/2019")
				public void setFechaHasta(Date fechaHasta) {
					this.fechaHasta = fechaHasta;
				}
			----------------------------------------------Validacion de Decimales
				@DoubleRangeFieldValidator(message = "Valor no esta en el rango establecido.", key = "i18n.key", shortCircuit = true, minInclusive = "0.123", maxInclusive = "99.987")
				public void setSueldo(double sueldo) {
					this.sueldo = sueldo;
				}

			----------------------------------------------Validacion de expresiones
				@Action("validacionDatos")
				@ExpressionValidator(
					    expression = "fechaDesde lt fechaHasta",
					    message = "La fecha desde no puede ser mayor a la fecha hasta"
					)
				@Override
				public String execute() throws Exception{
					return SUCCESS;
				}
			----------------------------------------------Validacion expresiones sobre un campo
				@FieldExpressionValidator(
					    expression = "numero3 eq (numero1 + numero2)",
					    message = "El numero 3 debe ser igual a la suma entre numero1 y numero2!"
					)
				public void setNumero3(int numero3) {
					this.numero3 = numero3;
				}
				------------------------------------------------------------------------
				@Action("validacionDatos")
				@FieldExpressionValidator(
					    fieldName = "numero3",         
					    expression = "numero3 eq (numero1 + numero2)",
					    message = "El numero 3 debe ser igual a la suma entre numero1 y numero2!"
				)
				@Override
				public String execute() throws Exception{
					return SUCCESS;
				}

	5.3 Validaciones Manuales----------------------------------------------------------
		Las validaciones manuales son aquellas que son tan particulares a nuestro proceso de negocio.
		Este tipo de validación, aunque las tenemos que realizar nosotros mismos, sigue siendo administrado por el framework.
		Para ello nos proporciona una interface llamada "Validateable", que proporciona únicamente un método:
			public void validate();
		Afortunadamente para nosotros, la clase "ActionSupport" implementa esta interface, por lo que lo unico que tenemos
		que hacer es sobre-escribirlo en nuestros Actions y, ayudados de algunos métodos auxiliares de la clase
		"ActionSupport", enviar los errores correspondientes en caso de que estos existan.
		Para poder indicar si existe algún error en las validaciones nos ayudamos del método "addFieldError", en caso de que
		exista un error de la validación de un campo, y de "addActionError", en caso de existir un error que no esté relacionado
		con un campo.
		"addFieldError" recibe dos parámetros, el primero es el nombre del campo que generó el error y el segundo es la
		descripción del error. "addActionError" recibe un solo parámetro que es la descripción del error.
		Si existe algún error en un campo este se mostrará en el campo correspondiente del formulario
		y en la etiqueta "<s:fielderror />". Si existe un error de otro tipo este se mostrará en la etiqueta "<s:actionerror/>".

		Ejemplo____________________________________________________________________________
			@Override
			public void validate(){
				if ("programadorJava".equals(userName)){
					addFieldError("userName", "El usuario se encuentra seleccionado.");
				}
				if ("programadorjavablog@gmail.com".equals(email)){
					addFieldError("email", "El correo electrónico proporcionado ya ha sido registrado " +
							"anteriormente.");
				}
			}
*******************************************************************************************



*******************************************************************************************
*******************************************************************************************
***********************************************************************6. Carga de archivos
	Struts 2 proporciona el soporte para la carga de archivos conforme a la especificación de HTML, esto nos permite subir
	uno o varios archivos desde el cliente al servidor.
	Cuando un archivo es cargado, este será almacenado en un directorio temporal por el interceptor correspondiente
	(fileUpload). El archivo deberá entonces ser procesado o movido a otra ubicación, por nuestro Action, ya que al
	terminar la petición el interceptor se encargará de eliminar este archivo temporal.

	6.1 Formulario-----------------------------------------------------------------------
	Se indica en la JSP que haremos uso de la biblioteca de etiquetas de Struts 2:
		<%@taglib prefix="s" uri="/struts-tags" %>
	Los datos del formulario deben codificarse de una forma especial antes de que estos sean enviados al servidor.
	Afortunadamente es el navegador el que se encarga de hacer esta codificación, lo único que nosotros debemos hacer es
	indicar, usando el atributo "enctype" del formulario, cuál codificación será; cuando cargamos archivos estos deben ir
	codificados en "multipart/form-data".
	Además los datos deben ser enviados por POST (en los formularios HTML el método por default para enviar datos
	es GET (por la URL), pero en Struts 2 por default se envían por POST (en el cuerpo del mensaje de la petición), así
	que no debemos agregarle ninguna cosa extra en este caso). El formulario, hasta ahora, se ve de la siguiente forma:
		<s:form enctype="multipart/form-data">
		</s:form>
	Los archivos son cargados con un tipo de campo especial el cual es generado usado la etiqueta "<s:file />". Esta
	etiqueta funciona de la misma forma que las demás que hemos venido usando hasta el momento:
		<s:form action="cargaArchivo" enctype="multipart/form-data">
			<s:file name="archivo" label="Archivo" />
		</s:form>

	Se agrega otro campo solo para que vemos que podemos subir archivos junto con datos "planos" del formulario:
		<s:form action="cargaArchivo" enctype="multipart/form-data">
			<s:file name="archivo" label="Archivo" />
			<s:textfield name="autor" label="Autor" />
		</s:form>
	Terminaremos el formulario agregando un botón de envío de formulario y una etiqueta que nos mostrará cualquier error
	que pudiera ocurrir en el proceso de carga:
		<s:actionerror />
		<s:form action="cargaArchivo" enctype="multipart/form-data">
			<s:file name="archivo" label="Archivo" />
			<s:textfield name="autor" label="Autor" />
		<s:submit value="Enviar" />
		</s:form>
	6.2 Action-----------------------------------------------------------------------
	Se crea Action que se encargará de procesar los datos de este formulario.
	Creamos una nueva clase en el paquete "actions" llamada "CargaArchivo". Esta clase extenderá de "ActionSupport":
		public class CargaArchivo extends ActionSupport
		{
		}
	Agregaremos una propiedad de tipo String para almacenar el nombre del autor:
	public class CargaArchivo extends ActionSupport
		{
			private String autor;
		}
	Ahora viene la parte importante, para poder obtener una referencia al archivo, debemos colocar, en
	nuestro Action, una propiedad de tipo "File":
		public class CargaArchivo extends ActionSupport		{
			private String autor;
			private File archivo;
		}
	Con esto, cada vez que se cargue un archivo usando el formulario, la variable de tipo "File" que tenemos, hará referencia
	a este archivo. Lo que nos queda es agregar los respectivos setters para las dos propiedades anteriores:
		public class CargaArchivo extends ActionSupport		{
			private String autor;
			private File archivo;
			public void setArchivo(File archivo){
				this.archivo = archivo;
			}
			public void setAutor(String autor)		{
				this.autor = autor;
			}
		}
	Ahora sobrecargaremos el método "execute" de nuestro Action para mover el archivo a una nueva ubicación en nuestro
	sistema de archivos:
		@Override
		public String execute() throws Exception		{
			File nuevoArchivo = new File("/", archivo.getName());
			archivo.renameTo(nuevoArchivo);
			return SUCCESS;
		}
	Podemos ver que colocaremos el nuevo archivo en el directorio raíz del sistema operativo y que el nombre del archivo
	será el mismo que el del archivo que estamos subiendo.
	Al terminar el proceso mostraremos un poco de información con respecto al archivo. La información del archivo se verá
	en la página que enviemos como resultado "SUCCESS", por lo que deberemos proporcionar
	los getters correspondientes para esta información; en este caso mostraremos el nombre del archivo y la ruta en la que
	queda almacenado:
		public String getNombre()		{
			return archivo.getName();
		}
		public String getRuta()	{
			return archivo.getAbsolutePath();
		}
	Como ven estamos obteniendo la información directamente del objeto File que representa al archivo que estamos
	cargando.
		
*******************************************************************************************


********************************************************Pasar parametros por get con struts
	<!--FacturaContratoListAction-END -->
	<result name="F1116B" type="redirectAction">
	    <param name="actionName">generarF1116BPDFDesdeBD</param>
	    <param name="parse">true</param>
		<param name="idFact">${idComprobante}</param>
		<param name="imp">true</param>
	</result>
*******************************************************************************************