************************************************************************
*********************************************pendientes por documentarme
guia de desarrollo de servicios ✔
************************************************************************

Seguridad***************************************************************
	Proovedor de authenticacion JAAS
************************************************************************


*******************************************************Curso Inicial Tia
	Jose Miguel Suarez  --> Everis Portugal 

	Carlos Miguel Niza  --> Everis Portugal



	Consultas
	customer Tower y tia change log
	mapeo entre el usuario y perfil se realiza fuera de tia

	p --> son llamados por los b y son pocesos mas internos de tia
	b --> negocios 
	t --> validacines 
	uf --> user function
	site preference reglas y variables de configuracion de tia

************************************************************************

reunion integracion*****************************************************
	Se necesita un api de alta de clientes con los datos de polaris
		se debe definir la equivalencia de los datos de polaris con tia
			polaris consume ai para que se cree o actualice en tia
			pendiente la equivalencia de datos de tia con polaris
			 	actualmente solarisapi --> apimanager --> merlinapi

		existe un estado de inhabilitado, que posibilita la emision o no de la poliza

	la consulta para saber si existe el cliente 
		punto n comun tipo y numero de documento
		ver en polaris, saber si se tienen polizas en tia, presupuestos 

	Merlin provee todos los datos de geolocalizacion

	Productores tia no esta apto para crear o modificar productores, 
	habria que dar de alta estos en tia cada vez que se necesite
************************************************************************











Trainig Tia ADF*********************************************************
	weblogic server

	[11:13] Nuno Lobato Faria
	IP_VM:7202/tiaweb/faces/Login

	[11:14] Nuno Lobato Faria
	Consola -> IP_VM:7201/console/

	[11:14] Nuno Lobato Faria
	IP_VM:7201/em/

	U:weblogic P:weblogic123



clase 2 minuto 34
|		2:10:00 data
		2:40:00


clase 3
	minuto 13, 25
	minuto 43 nuevo punto
	minuto 1:36 aplication model
			1:42
		15:19 maquina de nuno
		16:21 despues de la pausa

clase 4
	1:10 tabla
	1:40 receso
	1:50 reinicio
	15:46 pagina 

************************************************************************

	bbva
	contreras 
	Danger12



	Cuando se instalan los servicios REST, la documentación del servicio también se instala automáticamente y 
	está disponible en Swagger mediante esta ruta:

	<Myserver>:<port number>/<business area>/api/swagger-ui.html

	authenticacion y authorizacion

		Autenticacion y autorizacion
			Como se describe en las secciones Instalación y operaciones , los servicios REST de Tia 
			están diseñados para funcionar con Auth0 o la configuración de JWT autofirmado y usar 
			la autorización de nivel de permiso. 

			Nota:Para llamar a los puntos finales de la API REST de Tia desde la interfaz de usuario de Swagger 
			u otro cliente de API, debe tener un Auth0 configurado para una aplicación de máquina a máquina 
			o un generador de tokens para JWT autofirmados. Si desea llamar a los puntos finales de la API 
			REST de Tia como usuario de una aplicación de página única, debe hacerlo mediante programación.

		Para Auth0, puede obtenerlo realizando una operación POST en https: //YOUR_DOMAIN/oauth/token 
		con carga útil que contiene los siguientes datos:

			*client_id: ID de cliente de su aplicación. Puede encontrar este valor en la pestaña 
			Configuración de la aplicación (configuración del cliente).
			*client_secret: el secreto de cliente de su aplicación. Puede encontrar este valor en 
			la pestaña Configuración de la aplicación (configuración del cliente).
			*audiencia: el valor del identificador en la pestaña Configuración para la API que 
			creó como parte de los pasos anteriores (configuración de la API).
			grant_type: debe ser "client_credentials".





******************************************************************************party synchronized 15032021
	sincronizacion inicial
	sincronizacion diaria o semanal
	poner limite en la creacion de entidades en tia
	Se determino que realizara una session con el cliente para que el tome parte de la desicion
************************************************************************************************





documentacion


************************************************************************************Terminology
REST Service				Una URL HTTP (por ejemplo, ) y todos los métodos HTTP (por 
								ejemplo, GET, POST, PUT, DELETE) que puede utilizar con la URL. /customers
REST 						Una URL HTTP con métodos HTTP específicos. Por ejemplo, GET 
								/customers
REST API					Un servicio REST que está destinado a ser llamado por software 
								externo que no es de Tia.
URL, URI, or Endpoint		Todos se refieren a una URL HTTP. Ya sea una URL absoluta, 
								como o una URL relativa, como . https://api.tiatechnology.com/
								customer/api/v1/customers  /v1/customers
Payload						En este contexto, es el contenido de la solicitud HTTP o 
								el cuerpo de respuesta, que para la mayoría de nuestros 
								Servicios REST será un documento JSON.
Property					En este contexto, es un par nombre-valor. Por ejemplo, 
								lastName = Skywalker. A veces, la propiedad también se 
								denomina campo o atributo.
**********************************************************************************************