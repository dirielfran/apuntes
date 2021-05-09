Referencias*****************************************************************************************************************************
	StroreProcedure llamado desde spring data JPA**********************************************************************************
		https://www.adictosaltrabajo.com/2018/04/25/invocar-a-procedimientos-usando-spring-boot-data-jpa/
	*******************************************************************************************************************************
	Spring Data JPA Documentacion**************************************************************************************************
		https://docs.spring.io/spring-data/jpa/docs/current/reference/html
	*******************************************************************************************************************************
*******************************************************Definiciones*********************************************************************
***********************************************************************************************************************************Model
	Un modelo es un objeto genérico de Spring utilizado para almacenar los datos en proceso durante una 
	petición web. 
	En la práctica es un objeto Map que almacena los valores asociados a una cadena de texto con su nombre.

	Model
	La forma más sencilla de acceder al modelo dentro de un handler es declarar un argumento de tipo 
	Model, ModelMap o Map.

		@RequestMapping(value="/modelo")
		public void handler(Model model) {
		...
		    model.addAttribute("pi", 3.14159);
		...
		    model.addAttribute(calculadora);
		...
****************************************************************************************************************************************

*****************************************************************************************************************************Spring Data
	Spring Data es uno de los frameworks que se encuentra dentro de la plataforma de Spring.  Su objetivo es simplificar al desarrollador 
	la persistencia de datos contra distintos repositorios de información .

	Spring Data es parte del  Spring Framework . El objetivo de la abstracción del repositorio de Spring Data es reducir significativamente 
	la cantidad de código repetitivo requerido para implementar capas de acceso a datos para varios almacenes de persistencia.

	Spring Data JPA no es un proveedor de JPA. Es una biblioteca / marco que agrega una capa adicional de abstracción en la parte superior 
	de nuestro proveedor de JPA (como Hibernate).

	JPA---------------------------------------------------------------------------------------------------------------------------
		La API de persistencia de Java proporciona una especificación para persistir, leer y administrar datos de su objeto Java a 
		tablas relacionales en la base de datos.

		El objetivo que persigue el diseño de esta API es no perder las ventajas de la orientación a objetos al interactuar con una 
		base de datos (siguiendo el patrón de mapeo objeto-relacional), permitir usar objetos regulares (conocidos como POJO).
	Entity------------------------------------------------------------------------------------------------------------------------
		Una entidad de persistencia (entity) es una clase de Java ligera, cuyo estado es persistido de manera asociada a una tabla 
		en una base de datos relacional. Las instancias de estas entidades corresponden a un registro (conjunto de datos representados 
		en una fila) en la tabla. Normalmente las entidades están relacionadas a otras entidades, y estas relaciones son expresadas 
		a través de meta datos objeto/relacional. Los meta datos del objeto/relacional pueden ser especificados directamente en el 
		fichero de la clase, usando las anotaciones de Java (annotations), o en un documento descriptivo XML, el cual es distribuido 
		junto con la aplicación.
	Hibernate---------------------------------------------------------------------------------------------------------------------
		Hibernate es una solución de mapeo relacional de objetos para entornos Java. El mapeo relacional de objetos u ORM es la 
		técnica de programación para mapear objetos de modelo de dominio de aplicación a las tablas de bases de datos relacionales. 
		Hibernate es una herramienta ORM basada en Java que proporciona un marco para asignar objetos de dominio de aplicación a 
		las tablas de bases de datos relacionales y viceversa.

		Hibernate proporciona una implementación de referencia de la API de persistencia de Java que la convierte en una excelente 
		opción como herramienta ORM con los beneficios del acoplamiento flexible.

		Tenga en cuenta que JPA es una especificación e Hibernate es un proveedor o implementación de JPA.
	
	Diferencias JPA,Hibernate,Spring Data JPA------------------------------------------------------------------------------------
		Hibernate es una implementación JPA, mientras que Spring Data JPA es una abstracción de acceso a datos JPA. Spring Data 
		ofrece una solución para  GenericDao implementaciones personalizadas. También puede generar consultas JPA en su nombre a 
		través de convenciones de nombres de métodos.

		Con Spring Data, puede usar Hibernate, Eclipse Link o cualquier otro proveedor de JPA. Un beneficio muy interesante es 
		que puede controlar los límites de las transacciones de forma declarativa utilizando la anotación @Transactional .

		Spring Data JPA no es un proveedor de implementación o JPA, es solo una abstracción utilizada para reducir 
		significativamente la cantidad de código repetitivo requerido para implementar capas de acceso a datos para varios 
		almacenes de persistencia. 

		Hibernate proporciona una implementación de referencia de la API de Java Persistence que la convierte en una excelente 
		opción como herramienta ORM con los beneficios del acoplamiento flexible.
****************************************************************************************************************************************


************************************************************************************************************************atributos Flash

	Los atributos flash proporcionan una forma de almacenar atributos para poder ser usados en otra petición 
	diferente.
	*Comúnmente son usados cuando hacemos una redirección utilizando el patrón Post/Redirect/Get.
		Ejemplo: return "redirect:/peliculas/index";
	*Los atributos flash son almacenados temporalmente antes de hacer el redirect (tipícamente en la sesión) 
	para tenerlos disponibles después del redirect. Despúes del Redirect son eliminados de la sesión automáticamente.

		Ejemplo_____________________________________________________________________________________________

		@PostMapping("/save")
		public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
				@RequestParam("imagen") MultipartFile multiPart, HttpServletRequest request) {
			...

			...

			attributes.addFlashAttribute("mensaje", "El registro fue guardado");

			// return "peliculas/formPelicula";
			return "redirect:/peliculas/index";
		}
***************************************************************************************************************************************


**************************************************************************************************************************Data Binding
	Data Binding es el mecanismo mediante el cual Spring MVC extrae dinámicamente los datos de entrada del usuario 
	y los asigna a objetos de Modelo de nuestra aplicación

	Un ejemplo de Data Bindingen Spring MVC sería almacenar los datos de un formulario HTML a un objeto 
	de Modelo (AUTOMÁTICAMENTE).

	Cuando utilizamos Data Bindingen Spring MVC, la conversión de los tipos de datos se hace automáticamente.

	Spring MVC convierte los parámetros de la petición HTTP de tipo String, al tipo de dato según nuestra clase 
	de modelo (int, Date, double, etc).

	La validación de los datos de entrada también se hace automáticamente.
	Se puede crear una validación de datos personalizada.

	-----------------------------------------------------------------------------------------------------------------------
	BindingResult es una interface propia de spring para almacenar todos los errores durante el dataBinding
	Referencia --> https://programandoointentandolo.com/2019/03/spring-boot-validacion-spring-mvc-y-thymeleaf.html
		0.- Se debe añadir la dependencia 
			<dependency>
		        <groupId>org.springframework.boot</groupId>
		        <artifactId>spring-boot-starter-validation</artifactId>
		    </dependency>
		1.- se crean los validadores en el bean
			Ej:  
				@Size(min = 3, max = 8, message="El código del producto tiene que tener entre 3 y 8 caracteres")
			    @Pattern(regexp = "[A-Z0-9]+", message="El código del producto solo puede tener letras mayúsculas o números")
			    private String codigo;
		2.- AÑADIR VALIDACIÓN A LOS CONTROLADORES
			Una vez que ya tenemos anotada nuestra clase para que se valide tenemos que preparar los métodos de 
			nuestro controller sobre los que queremos que se haga una validación.

			Las 3 cosas que tenemos que hacer son añadir la anotación @Valid al objeto que queremos validar, 
			en nuestro caso el producto, añadir un parámetro de tipo BindingResult al método y si queremos 
			que en caso de que haya algún error nos mantengamos en el formulario y podamos mostrar los errores 
			haremos la comprobación de si nuestro BindingResult tiene algún error.

				ej:
				@PostMapping("/save")
				public String guardarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr, Model modelo) {
					if(result.hasErrors()) {
						return "usuarios/formRegistro";
					}
			    	usuario.setEstatus(1);
					usuario.setFechaRegistro(new Date());
					Perfil perfil = new Perfil();
					perfil.setId(3);		
					try {
						usuario.agregar(perfil);
						servicesUsuario.guardar(usuario);	
					}catch(Exception e) {
						result.addError(new ObjectError("excepcion",e.getMessage() ));
						return "usuarios/formRegistro";
					}		
					attr.addFlashAttribute("msg", "El registro fue guardado correctamente.");
					return "redirect:/usuarios/index";
				}
		3.- MOSTRAR LOS ERRORES EN LA VISTA CON THYMELEAF
			El último paso que nos queda es mostrarle al usuario cual es el motivo por el que 
			sigue en el formulario y no se ha podido completar la operación que estaba realizando.

			Para comprobar en Thymeleaf si hay algun error podemos utilizar ${#fields.hasErrors('*')} 
			si queremos comprobar si hay cualquier error y si queremos comprobar si hay un error en un 
			campo concreto sustituimos el * por el por nombre del campo.

			Dependiendo de la forma en la que queramos mostrar los errores también varía la forma en la 
			que tenemos que hacerlo. Si optamos por mostrar todos los errores juntos como una lista lo 
			que haremos será iterar sobre la lista completa de errores del formulario he ir mostrándolos 
			todos como una lista por ejemplo.
				<div th:object="${producto}" th:remove="tag">
				    <ul th:if="${#fields.hasErrors('*')}" class="alert alert-danger" role="alert">
				        <li th:each="error : ${#fields.errors('*')}" th:text="${error}"></li>
				    </ul>
				</div>		

			Y si optamos por mostrar los errores para cada uno de los campos por separado podemos 
			hacerlo con th:errors="*{nombrePropiedad}" que devuelve todos los errores asociados a 
			cada propiedad.

				<form th:action="@{guardar}" th:object="${producto}" method="post">
				    <input type="hidden" th:field="*{id}" />

				    <div class="form-group row">
				        <label class="col-sm-2 col-form-label">Código</label>
				        <div class="col-sm-6">
				            <input type="text" th:field="*{codigo}">
				            <small class="form-text text-danger" th:if="${#fields.hasErrors('codigo')}" th:errors="*{codigo}"></small>
				        </div>
				    </div>
		Ejemplo_____________________________________________________________________________________________
			public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
				@RequestParam("imagen") MultipartFile multiPart, HttpServletRequest request) {

				...

				if (result.hasErrors()) {
					System.out.println("Existieron errores");
					return "peliculas/formPelicula";
				}

				return "redirect:/peliculas/index";
			}
		--------------------------------------------------------------------------------------------
			****Manejo del error en el jsp
			<spring:hasBindErrors name="pelicula">
				<div class='alert alert-danger' role='alert'>
					Por favor corrija los siguientes errores:
					<ul>
						<c:forEach var="error" items="${errors.allErrors}">
							<li><spring:message message="${error}" /></li>
						</c:forEach>
					</ul>
				</div>
			</spring:hasBindErrors>
**************************************************************************************************************************************


**************************************************************************************************************************@Anotaciones
	Spring Stereotypes
	En estos momentos existen únicamente 4 Spring Stereotypes :

	@Component: Es el estereotipo general y permite anotar un bean para que Spring lo considere uno de sus 
	objetos.

	@Repository: Es el estereotipo que se encarga de dar de alta un bean para que implemente el patrón 
	repositorio que es el encargado de almacenar datos en una base de datos o repositorio de información que 
	se necesite. 
	Al marcar el bean con esta anotación Spring aporta servicios transversales como conversión de tipos de 
	excepciones.

	Spring Stereotypes Repository

	@Service : Este estereotipo se encarga de gestionar las operaciones de negocio más importantes a nivel 
	de la aplicación y aglutina llamadas a varios repositorios de forma simultánea. Su tarea fundamental 
	es la de agregador.

	Spring Stereotypes Service

	@Controller : El último de los estereotipos que es el que realiza las tareas de controlador y gestión 
	de la comunicación entre el usuario el aplicativo. Para ello se apoya habitualmente en algún motor 
	de plantillas o librería de etiquetas que facilitan la creación de páginas.

	La etiqueta @Controller la emplea Spring para escanear las clases y así detectar cuales de estas clases 
	forman los Controllers de nuestra aplicación.

	Con la anotación @Controller marcará una clase Java como una clase que contiene varios controladores HTTP, 
	es decir, puntos de acceso HTTP a su aplicación.

	@Repository, @Service y @Controller son especializaciones de @Component para casos concretos (persistencia, 
	servicios y presentación). Esto significa que puede usarse siempre @Component pero lo adecuado es usar 
	estos estereotipos ya que algunas herramientas o futuras versiones de Spring pueden añadir semántica 
	adicional (por ejemplo Spring Boot usa estas anotaciones y genera aspectos).


	-------------------------------------------------------------------------------------------------------------
		@Autowired
		La funcionalidad tan importante como básica de esta anotación es resolver mediante inyección las dependencias 
		de un bean de Spring. Por eso es fundamental conocer bien las características de @Autowired.

		Lo primero, @Autowired permite resolver la inyección de dependencias de los siguiente modos.

		En el constructor de la clase.
		En un atributo.
		En un método setter.
		En un método JavaConfig.

		La mas clara, es que al realizar @Autowired en el constructor, la inyección se realiza en el momento en que el 
		objeto es creado, lo que nos permite marcar con un final real nuestro recurso inyectado 

			@Controller
			public class MyController {
			 
			    private final MyBean myBean;
			 
			    @Autowired
			    public MyController(MyBean myBean) {
			       
			        this.myBean = myBean;
			    }
			}

		Si utilizamos @Autowired en un metodo setter, se creara el metodo y una vez creado, Spring inyectara el bean 
		mediante de dicho metodo.

		@Controller
		public class MyController {
		 
		    private MyBean myBean;
		 
		    @Autowired
		    public void setMyBean(MyBean myBean) {
		 
		        this.myBean = myBean;
		    }
		}

		Y por ultimo tenemos el caso de @Autowired sobre el atributo, Spring crea la instancia del objeto y una vez 
		creada le inyecta la independencia.

		@Controller
		public class MyController {
		  
		    @Autowired
		    private MyBean myBean;
		}



	------------------------------------------------------------------------------------------------------------
	@Service
	@GetMapping
	@PostMapping
	------------------------------------------------------------------------------------------------------------
	@RequestParam
	la etiqueta @RequestParam, con la que nos podemos ahorrar unas lineas de programación, si antes hemos 
	utilizado el objeto HttpServletRequest para recoger manualmente los parámetros de la llamada, podríamos 
	utilizar la etiqueta @RequestParam para mecanizar este paso.

	Use la anotación @RequestParam para vincular los parámetros de solicitud a un parámetro de método en su 
	controlador.
	-----------------------------------------------------------------------------------------------------------
	@RequestMapping
	La etiqueta @RequestMapping la emplea Spring para conocer a que Controller o método de un Controller tiene 
	que direccionar cada llamada del cliente

	Referencis --> https://codigoalonso.blogspot.com/2015/12/ejemplos-de-requestmapping-en-spring-mvc.html
	-----------------------------------------------------------------------------------------------------------
	@PathVariable

	-----------------------------------------------------------------------------------------------------------
	La anotación @InitBinder permite crear métodos para configurar el Data Binding directamente en el controlador.

	Sabe a qué parámetros de solicitud aplicar la conversión según su tipo de datos.

	Al hacer esto:

	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	Estás registrando el editor para el Date tipo.

	Los métodos marcados con la anotación @InitBinder no regresan ningún valor.
	Normalmente son declarados como void.
	Comúnmente reciben un párametro de tipo WebDataBinder.
	El siguiente ejemplo muestra como utilizar @InitBinder para configurar CustomDateEditor para todas las 
	propiedades de tipo 

	java.util.Date

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@InitBinder
	Esta anotación sirve para declarar un conversor de tipos dentro de un controlador. Se aplica a un método que 
	admite un argumento de tipo WebDataBinder, además de casi cualquier combinación de los mismos tipos 
	soportados por los métodos marcados con @RequestMapping.

	La clase WebDataBinder permite configurar conversores a medida de una aplicación. Sirva de ejemplo el 
	conversor de fechas que aparece en la documentación oficial de referencia:


	@Controller
	public class MyFormController {

	    @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class,
	            new CustomDateEditor(dateFormat, false));
	...

	-------------------------------------------------------------------------------------------@ModelAttribute
	Utilizamos esta anotacion para vincular un parametro de un metodo a una clase modelo y poderla utilizar en
	un jsp.
	Ej:
	10.16.3.- Se modifica el metodo mostrarContactos() para que identifique el atributo modelo con otro nombre
			@GetMapping("/contactos")
			public String mostrarContactos(@ModelAttribute("instanciaContacto") Contacto contacto) {
				return "formContactos";
			}
		10.16.4.- Se modifica la vista formContactos.jsp, para que el formulario se configure con los tags de spring, se añade metodo 
			post y el modelAttribute para que identifique el modelo
			<form:form class="form-horizontal" method="post" modelAttribute="instanciaContacto">
	------------------------------------------------------------------------------@ModelAttribute para metodo
		Se utiliza en un metodo de un controlador, regresa un valor al atributo que seleccionemos y estara 
		disponible o se puede utilizar para cuando se ejecute cualquier metodo del controlador

		Ejemplo:

		//Metodo creado para que agregue la lista de generos al modelo, para cualquier metodo 
		//Del controlador
		@ModelAttribute
		public List<String> getGeneros(){
			return servicePeliculas.buscarGeneros();
		}
	*******************************************************************************************************JPA
		-----------------------------------------------------------------------------------------@entity
		Crea una clase como una entidad de la base de datos
		-------------------------------------------------------------------------@Table(name="noticias")
		Configura el nombre de la tabla de la base de datos que se va a persistir
		---------------------------------------------------------------------------------------------@Id
		Configurar la llave primaria, se declara un atributo private int id, y se le agrega la anotacion @Id,
		el atributo debe ser unico en la entidad
		---------------------------------------------------------------------------------@GeneratedValue
		Se agrega la anotacion @GeneratedValue, para que el atributo se genere de forma automatica autoincrementable,
		se pasa como parametro la forma como se va a generar la llave primaria, por ser mysql es Identity, para oracle 
		es sequence
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
		-----------------------------------------------------------------------------------------@column
		JPA identifique que columna mapea contra cada atributo de la clase y es aquí donde entra @Column.
		Primero que nada algo importante: si el nombre de nuestro atributo en nuestra entidad, mapea 
		con un campo de una tabla y tienen el mismo nombre, en este caso se puede omitir la anotación 
		@Column.
		Se usa la anotación @Column para mapear el atributo fecha de nuestra entidad, a la columna 
		fechaRegistro de nuestra tabla Noticias. Tambien podríamos especificar la longitud para el 
		atributo titulo. Entonces nuestra clase de entidad quedaría de la siguiente forma:
			@Column(name="titulo",length=250,nullable=false)
		    private String titulo;
		    
		    @Column(name="fechaRegistro")
		    private Date fecha;

		Algunas propiedades que podemos definirle a @Column son las siguientes:

			name: Permite establecer el nombre del campo de la tabla de la base de datos que 
				mapeará el atributo donde se aplique la anotación @Column. A pesar de que ningún 
				atributo de @Column es obligatorio, este atributo siempre recomiendo establecerlo.
			length: Permite definir la longitud de la columna en caracteres, solo aplica para Strings.
			nullable: Crea una restricción en la tabla (Not Null) para impedir que se inserten 
			valores nulos.
		----------------------------------------------------------------------------------------@Transient
		Especifica que la propiedad o el campo no es persistente. Se utiliza para anotar una propiedad o 
		campo de una clase de entidad, superclase asignada o clase incorporable.
		----------------------------------------------------------------------------------------@OneToOne
		Las relaciones One to One (@OneToOne) se caracterizan porque solo puede existir una y solo una 
		relación con la Entidad de destino, de esta forma, la entidad marcada como @OnoToOne deberá 
		tener una referencia a la Entidad destino y por ningún motivo podrá ser una colección. De la misma 
		forma, la Entidad destino no podrá pertenecer a otra Instancia de la Entidad origen.

		Solo para ponernos en contexto, las relaciones @OneToOne se utilizan cuando existe una profunda 
		relación entre la Entidad origen y destino,de tal forma que la entidad destino le pertenece a la 
		Entidad origen y solo a ella, por ende, la existencia de la entidad destino depende de la Entidad 
		origen.
		--------------------------------------------------------------------------------------@JoinColumn
		La anotación @JoinColumn  se puede llegar a confundir con @Column , sin embargo, tiene una 
		funcionalidad diferente, @JoinColumn  se utiliza para marcar una propiedad la cual requiere de un 
		JOIN para poder accederlas, mientas que @Column  se utiliza para representar columnas simples que 
		no están relacionadas con otra Entidad. A pesar de que se utiliza para cosas distintas, la realidad 
		es que se parecen muchísimo, pues tiene casi las mismas propiedades, por que podríamos decir que 
		@JoinColumn  es el equivalente de @Column  cuando utilizamos relaciones con Entidades.
		--------------------------------------------------------------------------------------@Temporal
		Referencia --> https://www.oscarblancarteblog.com/2016/11/23/mapeo-fechas-temporal/
		Mediante la anotación @Temporal es posible mapear las fechas con la base de datos de una forma 
		simple. Una de las principales complicaciones cuando trabajamos con fecha y hora es determinar 
		el formato empleado por el manejador de base de datos.

		Mediante el uso de @Temporal es posible determinar si nuestro atributo almacena Hora, Fecha 
		u Hora y fecha, y es posible utilizar la clase Date o Calendar para estos fines. Yo siempre 
		recomiendo utilizar Calendar, pues tiene muchas más operaciones para manipular fecha y hora.

		Se pueden establecer tres posibles valores para la anotación:

			DATE: Acotara el campo solo a la Fecha, descartando la hora.
			@Temporal(TemporalType.DATE)
			TIME: Acotara el campo solo a la Hora, descartando a la fecha.
			@Temporal(TemporalType.TIME)
			TIMESTAMP: Toma la fecha y hora.
			@Temporal(TemporalType.TIMESTAMP)
		-------------------------------------------------------------------------------------------------
			Callback Methods
		 	Continuación se muestran todas las anotaciones que pueden utilizarse para que los métodos de la entidad sean llamados 
		 	de forma automática al producirse un evento en su ciclo de vida. Los métodos anotados de esta manera se llaman Callback 
		 	Methods o métodos de retrollamada.

			Podemos asignar varias anotaciones para un mismo método, y designar varios métodos de retrollamada en una misma entidad. 
			Pero no podremos asignar dos métodos diferentes para el mismo evento.

			@PrePersist	Antes de una operación persist.
			@PreRemove	Antes de una remove.
			@PostPersist	Después del INSERT de la base de datos y por tanto del persist.
			@PostRemove	Después del remove.
			@PreUpdate	Antes del UPDATE.
			@PostUpdate	Después UPDATE .
			@PostLoad	Después de que se carga una entidad en el contexto de persistencia.
			@PrePersist

			Ejemplo_________________________________________________________________________________________________
				//Antes de crear la entidad le asigna una fecha 
				@Prepersist
				public void prePersist() {
					fecha = new Date();
				}
		--------------------------------------------------------------------------------------------------
		@Value
		Esta anotación se puede usar para inyectar valores en campos en beans administrados por Spring y se 
		puede aplicar a nivel de campo o parámetro de constructor / método.
		--------------------------------------------------------------------------------------------------
		@Entity
			Crea una clase como una entidad de la base de datos
		--------------------------------------------------------------------------------------------------
		@Table(name="noticias")
			Configura el nombre de la tabla de la base de datos que se va a persistir
		--------------------------------------------------------------------------------------------------
		@Id..
			Configurar la llave primaria, se declara un atributo private int id, y se le agrega la anotacion @Id,
			el atributo debe ser unico en la entidad
		--------------------------------------------------------------------------------------------------
		@GeneratedValue
			Se agrega la anotacion @GeneratedValue, para que el atributo se genere de forma automatica autoincrementable,
			se pasa como parametro la forma como se va a generar la llave primaria, por ser mysql es Identity, para oracle 
			es sequence
				@Id
				@GeneratedValue(strategy=GenerationType.IDENTITY)
				private int id;
		-----------------------------------------------------------------------------@JsonIgnoreProperties
			@JsonIgnoreProperties ignora las propiedades lógicas especificadas en la serialización y 
			deserialización JSON. Está anotado a nivel de clase.:
				@JsonIgnoreProperties(value = { "intValue" })
				public class MyDto {
				 
				    private String stringValue;
				    private int intValue;
				    private boolean booleanValue;

			@JsonIgnoreProperties a nivel de atributo
				@JsonIgnoreProperties({"facturas"})
				@ManyToOne(fetch = FetchType.LAZY)
				private Cliente cliente;

			@JsonIgnoreProperties allowGetters
				Cuando pasamos trueal allowGetterselemento, se permitirán los captadores para las propiedades 
				lógicas especificadas. Significa que las propiedades lógicas especificadas en @JsonIgnoreProperties
				participarán en la serialización JSON pero no en la deserialización.
					@JsonIgnoreProperties ( value = { "bookName" , "bookCategory" }, allowGetters = true ) 
					public class Libro{}

			@JsonIgnoreProperties allowSetters
				Cuando pasamos true al allowSetterselemento, se permitirán los establecedores para las propiedades 
				lógicas especificadas. Significa que las propiedades lógicas especificadas en @JsonIgnoreProperties
				participarán en la deserialización JSON pero no en la serialización.
					@JsonIgnoreProperties ( value = { "bookName" , "bookCategory" }, allowSetters = true )
					public class Libro{}
		-------------------------------------------------------------------------------------------------
		@JsonManagedReference -> Administra la parte de avance de la referencia y los campos marcados por 
		esta anotación son los que se serializan
		@JsonBackReference -> Administra la parte inversa de la referencia y los campos / colecciones 
		marcados con esta anotación no se serializan.
		Caso de uso: tiene relaciones uno-muchos o muchos-muchos en sus entidades / tablas y no usar lo 
		anterior conduciría a errores como

		Infinite Recursion and hence stackoverflow - > Could not write content: Infinite recursion 
		(StackOverflowError)
		Los errores anteriores ocurren porque Jackson (o algún otro similar) intenta serializar ambos 
		extremos de la relación y termina en una recursividad.

		@JsonIgnore realiza funciones similares, pero las anotaciones mencionadas anteriormente son 
		preferibles.

	**********************************************************************************************************



	*******************************************************************************************Etiqueta iframe
	etiqueta de html5 que Nos permite insertar un documento html en otro html, es un fracmento de una pagina 
	que incrustamos en la pagina 
**************************************************************************************************************************************


******************************************************************************************************************ContextLoaderListner
	ContextLoaderListenercrea el contexto de la aplicación raíz y se compartirá con los contextos secundarios creados por todos los 
	DispatcherServletcontextos. Solo puede tener una entrada de esto en web.xml.

	El contexto de ContextLoaderListenercontiene beans que son visibles globalmente, como servicios, repositorios, beans de 
	infraestructura, etc. Una vez creado el contexto de la aplicación raíz, se almacena ServletContextcomo un atributo

	El ContextLoaderListner es uno de los componentes esenciales del marco Spring MVC, probablemente el más importante después de la 
	DispatcherServlet sí. Se utiliza para crear el contexto raíz y responsable de cargar beans, que son compartidos por múltiples 
	DispatcherServlet como beans relacionados con la capa de servicio y la capa de acceso a datos. En general, cuando desarrolla 
	una aplicación web basada en Spring MVC y también usa Spring en la capa de servicios, debe proporcionar dos contextos de aplicación. 
	El primero se configura con ContextLoaderListener y el otro con DispatcherServlet . El DispatcherServletes responsable de cargar 
	beans específicos de componentes web como controladores , resoluciones de vista y asignaciones de controladores , mientras que, como 
	dije antes, ContextLoaderListener es responsable de cargar beans de nivel intermedio y de nivel de datos que forman el back-end de 
	las aplicaciones Spring.

	El ContextLoaderListener es como cualquier otro oyente Servlet, y tiene que ser declarado en el descriptor de despliegue para 
	escuchar a los eventos. Escucha el inicio y apagado del servidor mediante la implementación de ServletContextListener y, en 
	consecuencia, crea y destruye beans administrados por Spring.

	Read more: https://www.java67.com/2019/05/contextloaderlistener-in-spring-mvc-10.html#ixzz6Mwce422V
**************************************************************************************************************************************


*******************************************************************************************************************DispatcherServletes
	DispatcherServletes esencialmente un Servlet (se extiende HttpServlet) cuyo propósito principal es manejar las solicitudes web 
	entrantes que coinciden con el patrón de URL configurado. Se necesita un URI entrante y encuentra la combinación correcta de 
	controlador y vista. Entonces es el controlador frontal.

	Cuando define una DispatcherServletconfiguración en primavera, proporciona un archivo XML con entradas de clases de controlador, 
	asignaciones de vistas, etc., utilizando el contextConfigLocationatributo.
**************************************************************************************************************************************




*******************************************************Inicio Proyecto CineApp*******************************************************
	1.- Creacion de Proyecto Maven
		1.1.-Se marca Proyecto Simple
		1.2.-Next y se le coloca Grupo Id -->net.itinajero, artifact id (nobre de la aplicacion) -->CineApp 
		1.3.-Finich

	2.- Se combierte en un proyecto web simple facelets
		2.1.- Propiedades del proyecto
		2.2.- Project Facets
		2.3.- Dinamic web Module
		2.4.- Se cambia la version del Modulo web dinamico a 3.1
		2.5.- Se cambia el nombre del directorio webapp(puede ser otro nombre), esta contiene jsp
		2.6.- Se marca para que se genere el web.xml
		2.7.- Se aplica

	3.- Se cambia la version del JRE
		3.1.- Se va a propiedades de la carpeta JRE
		3.2.- Se elige Workspace default JRE

	4.- Se selecciona el servidor web que se utilizara para el proyecto
		4.1.- Propiedades del proyecto
		4.2.- Java Build Path
		4.3.- Librerias
		4.4.- add Library
		4.5.- Server Runtime
		4.6.- Se escoje Pivotal server

	5.- Se crea pagina JSP para hacer la primera prueba
*****************************************************************************************Agregar Librerias de Spring utilizando Maven
	6.- Se agrega Spring MVC a una aplicacion web basado en Maven
		6.1.-Se abre el archivo pom.xml (Es el archivo principal de maven)
		6.2.- Se declaran cada una de las dependencias del proyecto (va entre los tags 	del proyecto)
			6.2.1con maven se descargan los jar de la dependencia especificada a nuestro proyecto, 
			primero busca en el repositorio local y si no lo encuentra los	busca en la red
	 		<dependencies>
	  			<dependency>
	  				<groupId>org.springframework</groupId>
	  				<artifactId>spring-webmvc</artifactId>
	  				<version>5.1.0.RELEASE</version>
	  			</dependency>
	  		</dependencies>
		6.3.- Se configura para que los jar se agregados al deployment de la aplicacion
			6.3.1.- Click derecho al proyecto
			6.3.2.- Propiedades
			6.3.3.-	Deploy assembly+
			6.3.4.- add
			6.3.5.- Java Build Path Entries
			6.3.6.- Seleccionamos las dependencias de maven
			6.3.7.- Aplicamos
***************************************************************************************************Configuracion del Dispacher Servlet
	7.-Configuracion del Dispacher Servlet
		7.1.- Se abre el archivo web.xml
		7.2.- Se borran las configuraciones por defecto(menos web-app)
		7.3.- Declaracion del dispacher servlet
		<servlet>
			<servlet-name>springmvc</servlet-name>
			<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
			<load-on-startup>1</load-on-startup>
		</servlet>
		7.3.- Configuracion del mapeo del servlet
		<servlet-mapping>
			<servlet-name>springmvc</servlet-name>
			<url-pattern>*.html</url-pattern>
		</servlet-mapping>
*******************************************************************************************************Configuracion del viewResolver
	8.- Definir el ViewResolver(configura donde buscara las vistas)
		8.1.- Archivo Web-INF
		8.2.- new
		8.3.- Spring Bean Configuratin File
		8.4.- Se le da nombre springmvc-servlet.xml
		8.5.- Next
		8.6.- Se agregan los namespace
			8.6.1.- beans
			8.6.2.- Context
			8.6.3.- MVC
		8.7.- Se agregan las configuraciones dentro del tags beans 
			<!-- Le decimos a Spring MVC que detecte automaticamente todos los Beans de tipo @Controller-->
			<context:component-scanbase-package="net.itinajero.app.controller" />
			<!-- Necesario para poder utilizar MVC con Anotaciones -->
			<!-- <mvc:annotation-driven />	 -->
			<mvc:annotation-driven >
				<mvc:argument-resolvers>
					<bean class="org.springframework.data.web.PageableHandlerMethodArgumentResolver">
						<property name="maxPageSize" value="5"></property>
					</bean>
				</mvc:argument-resolvers>
			</mvc:annotation-driven>
			<!-- Le indicamos a Spring MVC a donde debera ir a buscar las vistas (JSPs) -->
			<bean id="viewResolver"
				class="org.springframework.web.servlet.view.InternalResourceViewResolver">
				<property name="prefix">
					<value>/WEB-INF/views/</value>
				</property>
				<property name="suffix">
					<value>.jsp</value>
				</property>
			</bean>
******************************************************************************************************************Creacion Controller
	9.- Crear Controller
		9.1.- Se crea el paquete donde se creara el controlador configurado en el paso anterion 
				en base-package ="net.itinajero.app.controller" en la carpeta src/main/java
		9.2.- Dentro del paquete creado se crea clase -->ej: HomeControler
		9.3.- Se agrega la anotacion @Controller
		9.4.- Se importan las librerias para anotacion agregada
		9.5.- Se crea metodo
		9.6.- Se agrega al metodo la anotacion @RequestMapping
			9.6.1.- Se le agrega parametro al que va a responder y el metodo
			Ej: (value = "/home", method=RequestMethod.GET)
			9.6.2.- Se importan las libreria para el RequestMapping
		9.7.- El metodo devuelve un string con la vista que se va reederizar.

			Ej: 
			public class homeController {
				@RequestMapping(value="/home", method=RequestMethod.GET)
				public  String goHome() {
					return "home";
				}
			}

	10.- Se crea Directorio y vistas en webapp/Web-INF
		10.1.- Se crea directorio en web-Inf -->ej: views
		10.2.- Se crea en las carpeta de vistas el archivo jsp -->home

	11.- Se enciene el servidor y se ejecuta el proyecto
*************************************************************************************************************************************


Proyecto 2

*******************************************************************************************************************Seccion 4 clase 15
*********************************************************************************************************Creacion de Proyecto cineapp

	1.- Se inicia con proyecto con las configuraciones anteriores ya instaladas
		1.1.- se coloca la carpeta en el workspace
		1.2.- File --> Import --> Existing project in  workspace
		1.3.- Se coloca ruta
	2.- Se modifica el archivo web.xml para que acepte peticiones de cualquier tipo de archivo
		2.1.- Se cambia el tag url-pattern --> /
		<servlet-mapping>
			<servlet-name>springmvc</servlet-name>
			<url-pattern>/</url-pattern>
		</servlet-mapping>
*************************************************************************************************************************************


*******************************************************************************************************************Seccion 4 clase 16
******************************************************************************************************Agrega objetos al modelo(model)

	3.- Se agrega nuevo metodo al HomeController para mostrar los detalles de una pelicula en una 
		pagina jsp
		3.1.- Se agrega nuevo metodo que retorna string, en la clase HomeController, con parametro 
			de tipo Model, la clase Model es propia de Spring MVC, permite agregar objetos al modelo.
				--> mostrarDetalle(Model model)
		3.2.- Se agrega la anotacion @RequestMapping
			@RequestMapping(value="/detail")
		3.3.- Se agregan las propiedades del metodo
			String tituloPelicula = "Rapidos y Furiosos";
			int duracion = 136;
			double precioEntrada =  50;
			
		3.4.- Se añaden los atributos al modelo
			ej: Model.addAttribute(String nombre del atributo, Objeto atributo);
			model.addAttribute("peliculas", tituloPelicula);
			model.addAttribute("duracion", duracion);
			model.addAttribute("precio", precioEntrada);
		
		@RequestMapping(value="/detail")
		public String mostrarDetalle(Model modelo) {
			String tituloPelicula = "Rapido y Furioso";
			int duracion = 136;
			double precioEntrada = 50;
			
			modelo.addAttribute("titulo", tituloPelicula);
			modelo.addAttribute("duracion", duracion);
			modelo.addAttribute("precio", precioEntrada);
			
			return "detalle";		
		}

	4.- Se crea Pagina JSP para mostrar los detalles del metodo creado en el paso anterior
		3.1.- WEB-INF-->views
		3.2.- Se crea archivo JSP	 
		3.3.- Se enlazan a el atributo añadido al model del metodo mostrarDetalle
		Ej: 
		<h1>Titulo de la Pelicula: ${ titulo} </h1>
		<h1>Duracion de la pelicula: ${ duracion} </h1>
		<h1>Precio de la Pelicula ${ precio} </h1>
*************************************************************************************************************************************


*******************************************************************************************************************Seccion 4 clase 17
*******************************************************************************************************************Configuracion JSTL

	5.- Configuracion de JSTL en el pom.xml
		5.1 Se abre el pom.xml
		5.2 Se agrega dependencia de JSTL
			<dependency>
				<groupId>jstl</groupId>
				<artifactId>jstl</artifactId>
				<version>1.2</version>
			</dependency>
*************************************************************************************************************************************


*******************************************************************************************************************Seccion 4 clase 18
*******************************************************************************************************JSTL -Desplegar una Lista List

	6.- Se agrega un tipo de dato list a el modelo y desplegarlo en jsp por medio jstl
		6.2.- Se crea nuevo metodo con parametro Model que responda a la pagna principal de	la aplicacion
		@RequestMapping(value="/", method=RequestMethod.GET)
		public String mostrarPrincipal(Model model) {		
			List<String> peliculas = new LinkedList<>();
			peliculas.add("Papido y Furioso");
			peliculas.add("El aro dos");
			peliculas.add("Aliens");
			model.addAttribute("peliculas", peliculas);
			return "home";
		}
	-------------------------------------------------------------------------------------------------------------------------
	7.- Se hacen modificaciones en nuestra pagina principal home.jsp, haciendo uso de jstl
		7.1.- Se declara para poder utilizar JSTL en la JSP
			<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		7.2.- Se declara un foreach con jstl que lleva dos parametros items="nombre de la lista"
			y var= "la variable" con la que se va a identificar la variable
		<ol>
			<c:forEach items="${ peliculas }" var="pelicula">
				<li> ${ pelicula } </li>		
			</c:forEach>
		</ol>
**************************************************************************************************************************************


*******************************************************************************************************************Seccion 4 clase 19
*************************************************************************************************************clase de modelo Pelicula

	8.- Se crea clase Java Beans de peliculas
		8.1.- Se crea nuevo paquete Modelo en src/main/java
			net.itinajero.app.modelo
		8.2.- Se crea clase Java Beans dentro del paquete modelo -->Pelicula
		8.3.- Se crean las propiedades en la clase
			private int id;
			private String titulo;
			private int duracion;
			private String clasificacion;
			private String genero;
			private String imagen = "cinema.png";  //Imagen default
			private Date fechaEstreno;
			private String estatus  = "Activa";
		8.4.- Se generan los getters y Setters
		8.5.- Se genera metodo toString
			8.5.1.- click derecho --> source --> generate toString()
	9.- Se modifica metodo mostrarPrincipal para que la lista contenga objetos Pelicula
		9.1.- Se crea objeto de tipo lista, se llena con el metodo getLista.
			List<Pelicula> peliculas = getLista();
*************************************************************************************************************************************


*******************************************************************************************************************Seccion 4 clase 20
*********************************************************************************************************Desplegar lista de tipo List
	10.3.1- Se modifica el metodo mostrar principal
		10.3.1.1.- Se modifica Objeto List para que trabaje con objetos de tipo Pelicula 
			List<Pelicula> peliculas = getLista();
		10.3.1.2.- Se crea metodo para generar una lista de objetos de modelo Peliculas
			private List<Pelicula> getLista(){}
			10.3.1.2.1.- Se crea un objeto de SimpleDateFormat 
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			10.1.1.2.2.- Se cre objeto tipo list que maneja objetos Pelicula, de manera global
				List<Pelicula> lista = null;
		10.3.- Se crean los objetos Pelicula y se le añaden atributos;
			10.3.1.- Se crea un Try catch
			10.3.2.- Se le añade a lista un objeto tipo LinkedList
				lista = new LinkedList<>();
			10.3.3.- Se crean los objetos pelicula
				Pelicula pelicula1 = new Pelicula();
					pelicula1.setId(1);
					pelicula1.setTitulo("Aliens");
					pelicula1.setDuracion(136);
					pelicula1.setClasificacion("C");
					pelicula1.setGenero("ficcion");
					pelicula1.setFechaEstreno(sdf.parse("20-02-2019"));
		10.4.- Se añaden los objetos Pelicula a la lista;
			lista.add(pelicula1);
			lista.add(pelicula2);
			lista.add(pelicula3);
		10.5.- Se retorna un objeto lista
			return lista;
**************************************************************************************************************************************


*******************************************************************************************************************Seccion 4 clase 21
********************************************************************************************************************Aplicar Bootstrap

	12.- Aplicando diseño a una tabla HTML con Bootstrap (21 Udemy)
		12.1.- Se modifica el archivo home.jsp

		12.2.- Agregar css via CDN
			12.2.1.- Se habre la pagina de bootstrap
				https://getbootstrap.com/
			12.2.2.- Se Copia el el bootstrap CDN
			<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
			12.2.3.- Se copia el CDN en el encabezado del archivo home.jsp

		12.3.- Agregar panel Bootstrap -->https://getbootstrap.com/docs/3.4/components/
			<div class="panel panel-default">
	 			<div class="panel-heading">Panel heading without title</div>
	  			<div class="panel-body">
	    			Panel content
	  			</div>
			</div>

		12.4.- Se agregan las tres columnas que nos faltaban a la tabla al encabezado
			<th>Imagen</td>
			<th>Fecha de Estreno</td>
			<th>Estatus</td>
		12.5.- Se agregan las tres columnas que nos faltaban a la tabla al body
			<td>${ pelicula.imagen }</td>
			<td>${ pelicula.fechaEstreno }</td>
			<td>${ pelicula.estatus }</td>	
		12.6.- Se  agrega clase a la tabla -->Documentacion https://getbootstrap.com/docs/3.4/css/	
			y se elimina el atributo borde de la tabla
			class="table table-striped"

			<table class="table table-striped">
 				<tr>
 					<th>Imagen</td>
					<th>Fecha de Estreno</td>
					<th>Estatus</td>
 				</tr>
 				<c:forEach items="${peliculas}" var="pelicula">
	 				<tr>
	 					<td>${ pelicula.imagen }</td>
						<td>${ pelicula.fechaEstreno }</td>
						<td>${ pelicula.estatus }</td>
	 				</tr>
	 			</c:forEach>
 			</table>
		12.7.- Se configura para que el proyecto abra en el navegador
			12.7.1.- Windows --> Web Browser --> Navegador que elijas		
*************************************************************************************************************************************


*******************************************************************************************************************Seccion 4 clase 22
**************************************************************************************************Configuracion de recursos estaticos
	13.- Configuración de recursos estáticos (JS, CSS, Images) (Seccion 4.22 Udemy)
		13.1.- Se crea un folder en webApp--> Resources
		13.2.- en la carpeta de resources --> css,js,image
			13.2.1.- Se copian las imagenes en el archivo image
		13.3.- Agregar la siguiente configuración al archivo XML del DispatcherServlet, 			
			luego del bean del viewResolver
			<mvc:resources mapping="/resources/**" location="/resources/" />
		13.4.- Incluir el tag library de Spring en el archivo home.JSP en la parte alta 			
			del archivo despues de la configuracion del JSTL
			<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
		13.5.- Agregar una variable al modelo con la URL relativa a resource, dentro del 			
			head del archivo home.jsp Se puede incluir en cualquier parte del la pagina .jsp
			<spring:url value="/resources" var="urlPublic" />
		13.6.- Utilizar la variable antes creada para acceder a los archivos estáticos, 			
			demtro del  tag <img> 
		<td><img src="${urlPublic}/images/${pelicula.imagen}" width="80" height="100"></td>
*************************************************************************************************************************************


*******************************************************************************************************************Seccion 4 clase 24
*****************************************************************************************JSTL<fmt:formatDate> Aplicar formato a fecha
	14.-Aplicar formato a objeto Date por medio de JSTL (Seccion 4.24 Udemy)  
		14.1.- Se importa JSTL/fmt a la pagina .jsp
			<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
		14.2.- Se añade tag JSTL --><fmt:formatDate
		14.3.- En el atributo value se incluye por medio expresing languaje el atributo
			de nuestra clase pelicula
		14.4.- por medio del atributo pattern se le da formato a la fecha
			<td><fmt:formatDate value="${pelicula.fechaEstreno}" pattern="dd-MM-yyyy"/></td>
*************************************************************************************************************************************


*******************************************************************************************************************Seccion 4 clase 25
****************************************************************************************JSTL<c:choose> Aplicar condionales a la vista
	15.- Se utiliza el tag JSTL de condicional para el elemento status de la tabla (Seccion 4.25 Udemy)
	 	15.1.- Se utliza el tag JSTL choose
		15.2.- Dentro del choose, se utiliza el tag JSTL when
		15.3.- En el  tag when se utiliza el atributo test, donde con expresionlanguaje se coloca la condicion
		15.4.- se coloca las acciones al ser verdadero dentro del tag when
		15.5.- En caso de que no se cumpla la condicion se utiliza el tag JSTL otherwise
		15.6.- Se colocan las acciones al no cumplirse la condicion
			<td>
				<c:choose>
					<c:when test="${ pelicula.estatus=='Activa'}">
						<span class="label label-success">ACTIVA</span>
					</c:when>
					<c:otherwise>
						<span class="label label-danger">INACTIVA</span>
					</c:otherwise>
				</c:choose>
			</td>
*************************************************************************************************************************************


*******************************************************************************************************************Seccion 5 clase 27
****************************************************************************************Integracion bootstrap y recursos del proyecto
	16.- Agregar recursos staticos de HTML al proyecto (Seccion 4.27 Udemy)
		16.1.- Se copia la carpeta de bootstrap en webApp -->resources
		16.2.- Se copia el archivo de imagenes en webApp --> resources	 
		16.3.- Se copia carpeta tinymce que es un framework de javascript para editar en nuesta
			plantilla un editor html webApp  --> resources
		16.4.- Se añade el tag link para que busque la carpeta de bootstrap
		<link rel="stylesheet"	href="${urlPublic}/bootstrap/css/bootstrap.min.css">
*************************************************************************************************************************************


*******************************************************************************************************************Seccion 5 clase 28
*************************************************************************************Integracion plantilla HTML a la pagina de inicio
	17.- Integrar plantilla html de la pagina de inicio al home.jsp (Seccion 4.28 Udemy)
		17.1.- Se renombre el archivo de home.jsp -->homerespaldo.jsp
		17.2.- Se crea archivo home.jsp(new)
		17.3.- Se copia todo del archivo index.html de templates
		17.4.- Se borra todo el contenido de home.jsp
		17.5.- Se pega el contenido copiado en home.jsp
		17.6.- se agregan los tags de spring 
			<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
		17.7.- Se agrega tag spring:url para que mapee la urlPublic que hace referencia a la 
			carpeta de nuestro folder resources
			<spring:url value="/resources" var="urlPublic"></spring:url>
		17.8.- Se añade la variable urlPublic a nuestro directorio de archivos estaticos
			<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
			<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
			al final
				<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
		17.9.- Se añade la variable urlPublic a la ruta de nuestras imagenes
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="${urlPublic}/images/slide1.jpg" alt="Slide" title="Some text">
				</div>
				<div class="item">
					<img src="${urlPublic}/images/slide2.jpg" alt="Slide" title="Some text">
				</div>
				<div class="item">
					<img src="${urlPublic}/images/slide3.jpg" alt="Slide" title="Some text">
				</div>
				<div class="item">
					<img src="${urlPublic}/images/slide4.jpg" alt="Slide" title="Some text">
				</div>
			</div>	
*************************************************************************************************************************************


******************************************************************************************************************Seccion 4 clase 29
********************************************************************************************************Generar codigo html dinamico
	18.- Generar lista de peliculas de forma dinamica
		18.1.- Se ubica el sector de la pagina jsp donde se listan las peliculas
		18.2.- Se deja el codigo de una sola pelicula y se borra el resto de peliculas
		18.3.- Se configura en la pagina home.jsp los tag de JSTL 
			<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		18.4.- Se empaqueta el codigo de la pelicula en un foreach de JSTL agregandole por medio 	
			de expression laguaje la lista de peliculas en el atributo items y se agrega el nombre 	de la variable
			<c:forEach items="${ peliculas }" var="pelicula">
		18.5.- Se añaden los atributos a la pelicula por medio de espression laguaje
			<h4>${ pelicula.titulo }</h4>

			
			<c:forEach items="${ peliculas }" var="pelicula">
				<div class="col-xs-12 col-sm-6 col-md-3">
					<img class="img-rounded" src="${urlPublic}/images/${pelicula.imagen}" 						
					alt="Generic placeholder image" width="150" height="200">
					<h4>${ pelicula.titulo }</h4>
					<h4>
						<span class="label label-default">${ pelicula.clasificacion }</span> 			
						<span class="label label-default">${ pelicula.duracion } min</span> 			
						<span class="label label-default">${ pelicula.genero }</span>
					</h4>
					<p>
					<a class="btn btn-sm btn-primary" href="#" role="button">Consulta Horarios &raquo;</a>
					</p>
				</div>
			</c:forEach>
************************************************************************************************************************************


******************************************************************************************************************Seccion 4 clase 31
***************************************************************************<jsp:include> Separar el codigo html del menu y el footer
	19.- Separar el codigo html del menu y el footer
		19.1.- Se corta menu del header de la pagina home.jsp
		19.2.- Se crea folder includes en el folder views
			19.2.1.- Se crea un archivo JSP nuevo
			19.2.3.- Se copia lo cortado en el paso anterior
		19.3.- Se incluye el tag en la pagina home.jsp
			<jsp:include page="includes/menu.jsp"></jsp:include>
		19.4- Se corta footer  de la pagina home.jsp	
		19.5- Se crea un archivo JSP nuevo		 
		19.6.- Se pega lo cortado en el paso anterior
		19.7.- Se incluye el tag en la pagina home.jsp
			<jsp:include page="includes/piePagina.jsp"></jsp:include>
************************************************************************************************************************************


******************************************************************************************************************Seccion 6 clase 33
*************************************************************************************************************Anotacion @PathVariable
	20.- Se manda id por una url dinamica, se modifica metodo mostrarDetalle()
		20.1.- Se agrega diagonal y parametro dinamico en el @RequestMapping
			 @RequestMapping(value="/detail/{id}")
		20.2.- Se limita para que solo responda a peticiones de tipo get 
			@RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
		20.3.- Se agrega un nuevo parametro al metodo para que reciba la url dinamica mapeandolo @PathVariable
			public String mostrarDetalle(Model model,@PathVariable("id") int idPelicula ) {
		20.4.- Se modifica la vista home.jsp la url se le pasa por medio EL el id de la pelicula
			<a class="btn btn-sm btn-primary" href="detail/${pelicula.id }" role="button">Consulta	Horarios &raquo;</a>
			Ejemplo_____________________________________________________________________________________________
				@RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
				public String mostrarDetalle(Model model,@PathVariable("id") int idPelicula ) {
					String tituloPelicula = "Rapido y Furioso";
					int duracion = 136;
					double precioEntrada = 50;
					
					model.addAttribute("titulo", tituloPelicula);
					model.addAttribute("duracion", duracion);
					model.addAttribute("precio", precioEntrada);
					
					return "detalle";		
				}
************************************************************************************************************************************


*****************************************************************************************************************Seccion 6 clase 34
******************************************************************************************Anotacion @PathVariable con dos variables
	21.- configurar url consultar horarios
		21.1.- Se crea una variable de instancia SimpleDateFormat en la clase HomeController
			private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 	21.2.- Se agrega en el metodo mostrarPrincipal otro atributo al modelo la fecha del dia
			model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		21.3.- En la vista home.jsp, en la consulta de horario se le añade un parametro dinamico 
			el cual	captura la fecha de el metodo mostrarprincipal 
			<a class="btn btn-sm btn-primary" href="detail/${pelicula.id}/${fechaBusqueda}"	
				role="button">Consulta	Horarios &raquo;</a>
		21.4.- Recibir la fecha por la url, se agrega un parametro dinamico a la url en el 	metodo mostrarDetalle
			@RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
		21.5.- Se agrega un parametro al metodo mostrarDetalle correspondiente a la fecha
			public String mostrarDetalle(Model model,@PathVariable("id") int idPelicula, 
				@PathVariable("fecha") String fecha ) {

			@RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
			public String mostrarDetalle(Model model,@PathVariable("id") int idPelicula, 	
											@Path Variable("fecha") String fecha ) {
				System.out.println("idPelicula: "+ idPelicula);
				System.out.println("Fecha: "+ fecha);
				//Se agregan las propiedades del metodo
				String tituloPelicula = "Rapidos y Furiosos";
				int duracion = 136;
				double precioEntrada =  50;
				
				//Se añaden atributos al modelo
				model.addAttribute("peliculas", tituloPelicula);
				model.addAttribute("duracion", duracion);
				model.addAttribute("precio", precioEntrada);
				return "detalle";
			}
***********************************************************************************************************************************


*****************************************************************************************************************Seccion 6 clase 35
************************************************************************************************************Anotacion @RequestParam
	22.- configurar boton consulta horario para que utilice @RequestParam
		22.1.- se modifica la vista para que los parametros sean mandados por url
			<a class="btn btn-sm btn-primary" href="detail?idMovie=${pelicula.id}&fecha=${fechaBusqueda}" 
				role="button">Consulta Horarios &raquo;</a>
		22.2.- Se modifica el RequestMapping para eliminar los parametros dinamicos en el metodo mostrar
			detalle
			@RequestMapping(value="/detail", method=RequestMethod.GET)
		22.3.- Se reemplaza el PathVariable por el @RequestParam
			public String mostrarDetalle(Model model,@RequestParam("idMovie") int idPelicula, @RequestParam	("fecha") String fecha ) {	

			//@RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
			@RequestMapping(value="/detail", method=RequestMethod.GET)
			//public String mostrarDetalle(Model model,@PathVariable("id") int idPelicula, @PathVariable("fecha") 	String fecha ) {
			public String mostrarDetalle(Model model,@RequestParam("idMovie") int idPelicula, @RequestParam	("fecha") String fecha ) {	
				System.out.println("idPelicula: "+ idPelicula);
				System.out.println("Fecha: "+ fecha);
				//Se agregan las propiedades del metodo
				String tituloPelicula = "Rapidos y Furiosos";
				int duracion = 136;
				double precioEntrada =  50;
				
				//Se añaden atributos al modelo
				model.addAttribute("peliculas", tituloPelicula);
				model.addAttribute("duracion", duracion);
				model.addAttribute("precio", precioEntrada);
				return "detalle";
			}
***********************************************************************************************************************************


*****************************************************************************************************************Seccion 6 clase 37
***********************************************************************GENERAR LISTA DE FECHA DINAMICAS PARA FORMULARIO DE BUSQUEDA
	23.1.- SE CREA PAQUETE --> net.itinajero.app.util
		23.2.- Se crea clase de utilitarios con metodo para generear n fechas
		public class Utileria {
			
			/**
			*Metodo que regresa una lista de String con las n fechas siguientes
			*/
			public static List<String> getNextDays(int count){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				//fecha desde hoy
				Date start = new Date();
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH,count); //Proximos N dias desde hoy
				Date endDate = cal.getTime();
				
				GregorianCalendar gcal = new GregorianCalendar();
				gcal.setTime(start);
				List<String> nextDays = new ArrayList<String>();
				while(!gcal.getTime().after(endDate)) {
					Date d = gcal.getTime();
					gcal.add(Calendar.DATE, 1);
					nextDays.add(sdf.format(d));
				}
				return nextDays;
			}
			
		}
		23.3.- En el metodo mostrarPrincipal del homeController se agrega una lista que contiene las fechas.
			List<String> listaFechas = Utileria.getNextDays(4);
		23.4.- Se agrega la lista al modelo
			model.addAttribute("fechas", listaFechas);
		23.5.- Se modifica el home.jsp colocando un for each para que recorra las fechas
			<c:forEach items="${fechas }" var="fecha">
				<option value="${fecha}">${fecha }</option>
			</c:forEach>	
***********************************************************************************************************************************


*****************************************************************************************************************Seccion 6 clase 38
*****************************************************************************************ANOTACION @RequestParam-Peticion HTTP POST
	24.- En el homeController se crea nuevo metodo --Buscar()
		@RequestMapping(value="/search", method=RequestMethod.POST)
		public String buscar(@RequestParam("fecha") String fecha) {
			System.out.println("Buscando las fechas: " + fecha );
			 return "home";
		}
		24.1.- En home.jsp Se crea una nueva url utilizando el tag <spring:url
			<spring:url value="/" var="urlRoot"></spring:url>
		24.2.- Se modifica form de la cartelera, se le pone url de busqueda
			<form class="form-inline" action="${urlRoot}search" method="post">
		24.3.- Se agrega lista para fechas y peliculas, se agregan al modelo y se agrega fecha de busqueda

			@RequestMapping(value="/search", method=RequestMethod.POST)
			public String buscar(@RequestParam("fecha")String fecha, Model model) {
				List<String> listaFechas = Utileria.getNextDays(5);		
				List<Pelicula> peliculas = getLista();
				model.addAttribute("fechas", listaFechas);
				model.addAttribute("fechaBusqueda", fecha);
				model.addAttribute("peliculas", peliculas); 
				return "home";
			}
***********************************************************************************************************************************


*****************************************************************************************************************Seccion 6 clase 40
******************************************************************************************************************Ejercicio de JSTL
	25.- Se debe modificar el select de la fecha para que ponga la fecha seleccionada como por defecto
		<select id="fecha" name="fecha"	class="form-control">
			<c:forEach items="${fechas }" var="fecha">
				<c:choose>
					<c:when test="${fechaBusqueda eq fecha}">
						<option value="${fecha}" selected="selected">${fecha }</option>
					</c:when>
					<c:otherwise>
						<option value="${fecha}">${fecha }</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
***********************************************************************************************************************************


*****************************************************************************************************************Seccion 6 clase 43
**************************************************************************************************************Anotacion @GetMapping
	26.- Se crea una clase controler para noticias
		26.1.- Se agrega anotacion @controller
		26.2.- Se agrega anotacion @RequestMapping
			@Controller
			@RequestMapping("/noticias")
			public class NoticiasController {
			}
		26.3.- Se crea metodo crear noticia -->crear()
			@GetMapping(value="/create")
			public String crear() {
				return "noticias/formNoticia";
			}
		26.4.- Se crea en views un folder --> noticias
		26.5.- Se crea un jsp -->formNoticia.jsp
***********************************************************************************************************************************
		

*****************************************************************************************************************Seccion 6 clase 45 
************************************************************************************************Integrar plantilla HTML formNoticia
	27.- Copiar plantilla en formNoticia
		27.1.- Se añade el tag de spring al jsp	en linea 1
			<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
		27.2.- Se añade tag <spring:url> en el head
			<spring:url value="/resources" var="urlPublic"></spring:url>
		27.3.- Se añade url configurada en todos los archivos estaticos por medio de espression Languaje
		 Ejemplo:
			<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">   
		 	<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
		27.4.- Se reemplaza la cabecera de la pagina y se incluye header.jsp con el tag --><jsp:include>
		  	<jsp:include page="../includes/header.jsp"></jsp:include>
		27.4.- Se reemplaza footer de la pagina y se incluye footer.jsp con el tag --><jsp:include>
			<jsp:include page="../includes/footer.jsp"></jsp:include>
		27.5.- Se agrega codigo javaScript para utilizar plugin tinymcs para crear editor de texto			 
				<script src="${urlPublic}/tinymce/tinymce.min.js"></script>
		    	<script>
		      		tinymce.init({
		          		selector: '#detalle',
		          		plugins: "textcolor, table lists code",
		          		toolbar: " undo redo | bold italic | alignleft aligncenter alignright alignjustify \n\
		          		   	 | bullist numlist outdent indent | forecolor backcolor table code"
		      		});
		    	</script>
***********************************************************************************************************************************


****************************************************************************************************************Seccion 6 clase 46
****************************************************************************************************Anotacion @PostMapping ejemplo
	28.- Se crea en el NoticiasController un nuevo metodo para que reciba los parametros del 
		formulario de noticias
		@PostMapping(value="/save")
		public String guardar() {
			return "noticias/formNoticia";
		}  
		28.1.- Se utiliza tag spring:url en formNoticias
			<spring:url value="/noticias/save" var="urlForm"></spring:url>
		28.2.- Se ubica y modifica con expression Languaje el form en la pagina formNoticia.jsp
			<form action="${urlForm}" method="Post">
		28.3.- Se configura en el formNoticias.jsp con los parametros de llegada del formulario
			@PostMapping(value="/save")
			public String guardar(@RequestParam("titulo") String titulo,
						@RequestParam("estatus") String estatus,
						@RequestParam("detalle") String detalle){
				return "noticias/formNoticia";
			} 
**********************************************************************************************************************************


****************************************************************************************************************Seccion 6 clase 47
*************************************************************************************************************Clase Modelo Noticias
	29.- Se crea Clase modelo para representas las noticias -->model.Noticia
		29.1.- Se crean los atributos.
			private int id;
			private String titulo;
			private Date fecha;
			private String detalle;
			private String estatus;
		29.2.- se crea constructor 
			public Noticia() {
				this.fecha = new Date();
				this.estatus = "Activa";
			}
		29.3.- Se crean getters y seters
		29.4.- Se genera metodo toString
			@Override
		public String toString() {
			return "Noticia [id=" + id + ", titulo=" + titulo + ", fecha=" + fecha + ", detalle=" + 
			detalle + ", estatus=" + estatus + "]";
		}
		29.5.- Se crea en el NoticiasController el objeto noticias en el metodo guardar y se le setean 
			los	atributos
			Noticia noticia = new Noticia();		
			noticia.setTitulo(titulo);
			noticia.setEstatus(estatus);
			noticia.setDetalle(detalle);
		29.6.- Se prueba imprimiendo el objeto con el metodo toString
			System.out.println(noticia.toString());
**********************************************************************************************************************************


****************************************************************************************************************Seccion 7 clase 49
*****************************************************************************************Configuracion del Root ApplicationContext
	El Root ApplicationContext tiene la configuracion de todos beans de componentes propios de la capa
	de negocios y acceso a datos, en una aplicacion con springMVC solo puede haber un root 
	ApplicationContext

		30.1.- Se configura el root AplicationContext en el archivo web.xml luego de la configuracion 
			del	dispacher servlet
			30.1.1.- Crtl+space --> Se busca el contextLoaderListener que trae toda lo configuracion

			<!-- needed for ContextLoaderListener -->
			<context-param>
				<param-name>contextConfigLocation</param-name>
				<param-value>/WEB-INF/spring/root-context.xml</param-value>
			</context-param>

			<!-- Bootstraps the root web application context before servlet initialization -->
			<listener>
				<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
			</listener>
			
			30.1.2.- Se configura el parametro valor
				<param-value>/WEB-INF/spring/root-context.xml</param-value>
			30.1.3.- Se crea el archivo root-context.xml
				30.1.3.1.- Se crea un nuevo directorio en WEB_INF --> spring
				30.1.3.2.- Se crea el archivo de configuracion de bean
					Spring->new->Spring bean Configuration File --> root-context.xml
					next--> Se seleccionan los namesapace
					--> beans
					--> Context
**********************************************************************************************************************************


************************************************************************************Configuracion de nueva ubicacion del dispacher
	31.- Se coloca el dispacher en una nueva ubicacion y se le da un nombre mas descriptivo
		31.1.- Se arrastra el archivo a la carpeta spring
		31.2.- Le cambiamos el nombre al dispacher --> dispatcher-servlet.xml
		31.3.- Se configura en el web.xml el parametro para nueva localizacion del dispacher
			web.xml-> se añade tag init-Param --> param-name --param-value
			<servlet-name>springmvc</servlet-name>
			<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
			<init-param>
				<param-name>contextConfigLocation</param-name>
				<param-value>/WEB-INF/spring/dispatcher-servlet.xml</param-value>
			</init-param>
			<load-on-startup>1</load-on-startup>
**********************************************************************************************************************************


****************************************************************************************************************Seccion 7 clase 50
********************************************************************************************Clase de Servicios PeliculaServiceImpl
	32.- Se crea clase de servicio
		32.1.- Se crea un nuevo paquete para los servicios, las clases que van a manejar la logica 
		de negocios --> net.itinajero.app.service
		32.2.- Se crea Interface -->IPeliculasService
			32.3.- Se crea en la interface la firma del metodo que retorna una lista de peliculas
				List<Peliculas> buscarTodas(); 
		32.3.- Se crea clase de implementacion --> PeliculasServiceImp
			32.3.1.- Se mplementa la interface y Se agregan la declaracion de todos los metodos de la
				interface implementada
			32.3.2.- Se agrega la implementacion al metodo, se copia el contenido del metodo 
				getLista() del HomeController y se pega en el constructor para que genere la lista al crearse la clase, 
				se deben borrar los return,	ya que un contructor no puede retornar nada
			32.3.3.- Se corta el objeto List<Pelicula> lista = null; del contructor y se copia como 
				atributo de la clase, se pone como privada, para que este disponible para todos los metodos		
			32.3.4.- En el metodo buscarTodas() se retorna el objeto lista que ya se crea en el constructor
**********************************************************************************************************************************


****************************************************************************************************************Seccion 7 clase 52
****************************************************************************Implementacion del @Autowired Inyeccion de dependecias 
	33.- Se configura el autoescaneo de	beans
		33.1.- Activar el autoescanéo para detectar e instanciar beans en el Root ApplicationContext.
			<context:component-scan base-package="net.itinajero.app.service" />
		33.2.- Se agrega la anotacion @Service a la clase de Servicio
			@Service
			public class PeliculasServiceImp implements IPeliculasService{
	34.- Se configura el controlador
		34.1.-Se agrega una variable de tipo privada, de tipo de la interface que implenta la clase de
			servicio
			@Autowired
			private IPeliculasService servicePeliculas;
	35.- Se modifican los metodos buscar y mostrar principal de homeController.java, donde se utiliza metodos para obtener 
		las peliculas, se inyecta la dependencia con el metodo buscarTodas().
			List<Pelicula> peliculas = servicePeliculas.buscarTodas(); 
	36.- Se elimina el metodo getListas() del HomeController. 
**********************************************************************************************************************************


****************************************************************************************************************Seccion 7 clase 53
**************************************************************Agregar metodo para busqueda enlazada de los detalles de la pelicula
	37.- Se declara un nuevo metodo en la interface IPeliculasService, este regresa un obj de tipo 
		pelicula y busca por id, recibe un entero de id pelicula para busqueda. 
		public interface IPeliculasService {
			List<Pelicula> buscarTodas(); 
			Pelicula buscarPrId(int idPelicula);
		}
	38.- Se implementa en la clase de servicio el metodo declarado en la interface
		@Override
		public Pelicula buscarPorId(int idPelicula) {
			// TODO Auto-generated method stub
			return null;
		}
	39.- Se modifica en la clase el metodo buscarPorId, el cual va a retornar de la lista el obj Pelicula segun id
		@Override
		public Pelicula buscarPorId(int idPelicula) {
			for(Pelicula p: lista) {
				if(p.getId()== idPelicula) {
					return p;
				}
			}
			return null;
		}
	40.- Modificacion del metodo mostrarDetalle del HomeControler
		40.1.- Se agrega al modelo el objeto que retorna el metodo buscarProId
			@RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
			public String mostrarDetalle(Model model,@PathVariable("id") int idPelicula, @PathVariable("fecha") String fecha ) {
				System.out.println("idPelicula: "+ idPelicula);
				System.out.println("Fecha: "+ fecha);
				model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
				return "detalle";
			}
	41.- Modificamos el archivo detalle.jsp
		<body>
			<h1>${ pelicula}</h1>
		</body>
**********************************************************************************************************************************

			
****************************************************************************************************************Seccion 7 clase 54
**************************************************************************************************Integracion de plantilla detalle
	42.- Se copia el contenido de la plantilla detalle en detalle.jsp
	43.- Se modifica jsp y se añade tag de spring y jstl
		<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	44.- Se añade tag <spring:url>
		<spring:url value="/resources" var="urlPublic"></spring:url>
	45.- Se añade la url configurarada para recursos estaticos para directorio
		<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
		<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script> 
	46.- Se incluye menu y pie de pagina con tag <jsp:include> 
		<jsp:include page="../includes/menu.jsp"></jsp:include>
		<jsp:include page="../includes/piePagina.jsp"></jsp:include>
	47.- Se realizan las modificaciones correspondientes para de forma dinamica traer los datos de la 
		pelicula por medio de expression languaje
**********************************************************************************************************************************
	

****************************************************************************************************************Seccion 7 clase 55
************************************************************************************Inyeccion de Dependencias Servicio de Noticias
	48.- Se crea la interface de servicio en el paquete	package net.itinajero.app.service;

		import net.itinajero.app.modelo.Noticia;

		public interface INoticiasService {
			void guardar(Noticia noticia);
		}
	49.-Se crea clase de noticias que implemente la interface
		@Service
		public class NoticiasServiceImp implements INoticiasService{

			@Override
			public void guardar(Noticia noticia) {
				System.out.println(noticia);
			}
		}
	50.- Se crea variable en el controlador del tipo de la interface creada y se realiza la inyeccion 
		de dependencia en el controladorNoticias
		@Autowired
		private INoticiasService serviceNoticias;

	51.- Se modifica metodo guardar() de NoticiasController.java para que llame al 
		metodo guardar implementado en la clase de servicio
			serviceNoticias.guardar(noticia);
**********************************************************************************************************************************
		

****************************************************************************************************************Seccion 8 clase 57
***********************************************************************************************Data Binding - Clase modelo Noticia
	52.- Se modifica en el controllerNoticias, el metodo guardar()
	53.- Se eliminan los parametros del metodo y se le añade un parametro de tipo Noticia		
		@PostMapping(value="/save")
		public String guardar(Noticia noticia){	
	54.- Se elimina la intancia creada al objeto noticia y el seteo de los atributos, ya que esto 
		lo hara de forma automatica
		@PostMapping(value="/save")
		public String guardar(Noticia noticia){
			
			
			//pendiente guardar el objeto noticias en la base de datos
			
			serviceNoticias.guardar(noticia);
			
			System.out.println(noticia.toString());
			return "noticias/formNoticia";

		} 
**********************************************************************************************************************************


***************************************************************************************************************Seccion 8 clase 58
**********************************************************************Ejemplo Data Binding -Integracion HTML para crear Peliculas
	55.- Creamos un nuevo controlador para Peliculas -->PeliculasController
	56.- Se le añade anotacion @Controller
	57.- Se añade anotacion @RequestMapping("/peliculas")
	58.- Se crea un metodo tipo get --> crear()
		@GetMapping("/create")
		public String crear() {			
			return "peliculas/formPelicula";
		}
	59.- Se crea directorio en views --> peliculas
	60.- Se crea formPelicula.jsp dentro del folder peliculas
	61.- Se copia de la plantilla formPelicula.html en el jsp creado 
	62.- Se modifica jsp y se añade tag de spring y jstl
		<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	63.- Se añade tag <spring:url>
		<spring:url value="/resources" var="urlPublic"></spring:url>
	64.- Se añade la url configurarada para recursos estaticos para directorio
		<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
	65.- Se incluye menu y pie de pagina con tag <jsp:include> 
		<jsp:include page="../includes/menu.jsp"></jsp:include>
		<jsp:include page="../includes/piePagina.jsp"></jsp:include>
	66.- Se verifica que los imput de la plantilla tengan el mismo nombre de los atributos de la 
		clase para el Data Binding
	67.- Se crea un nuevo metodo para guardar los datos de la pelicula utilizando data Binding
		@PostMapping("/save")
		public String guardar(Pelicula pelicula) {
			return "/peliculas/formPeliculas";
		}
	68.- Se cre tag de <spring: url>
		<spring:url value="/peliculas/save" var="urlForm"></spring:url>
	69.- Se modifica form para que redirija al guardar
		<form action="${urlForm}" method="post">
	70.- Se agrega al metodo el objeto BindingResult para control de errores
		@PostMapping("/save")
		public String guardar(Pelicula pelicula, BindingResult resultado) {
			System.out.println("Pelicula: "+pelicula);
			return "/peliculas/formPeliculas";
		}
	71.- Desplegar errores en el proceso de dataBinding, agregando un foreach al metod
	 	guardar,que recorra todos los errores de BindingResult
			@PostMapping(value="/save")
			public String guardar(Pelicula pelicula, BindingResult resultado) {
						
				for (ObjectError error : resultado.getAllErrors()) {
					System.out.println("Error: "+error.getDefaultMessage());
				}
				
				System.out.println("Pelicula: "+pelicula);
				return "/peliculas/formPeliculas";
			}
	72.- Desplegar errores en la vista
		72.1.-Agregar el taglib de spring.
			<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
		72.2.- Utilizar el tag <spring:hasBindErrors>para desplegar los posibles errores 
			que ocurrieron al realizarse el Data Binding.
		
			<!--Se le pasa el nombre del objeto de modelo-->
			<spring:hasBindErrors name="pelicula">
				<!--Clase propia de bootstrap para alertar y colocar mensaje en color rojo-->
				<div class='alert alert-danger' role='alert'>
					Por favor corrija los siguientes errores:
					<ul>
						<c:forEach var="error" items="${errors.allErrors}">
							<li><spring:message message="${error}" /></li>
						</c:forEach>
					</ul>
				</div>
			</spring:hasBindErrors>
*********************************************************************************************************************************


***************************************************************************************************************Seccion 8 clase 63
*********************************************************************************Anotación @InitBinder –Perzonalizar Data Binding
	73.- Se modifica el controlador PeliculasController, agregando el metodo initBinder con la 
		anotacion @InitBinder, le pasamos como parametro un WebDataBinder y se registra con el 
		metodo registerCustomEditor para todo tipo Date.class
		un objeto CustomDateEditor con el formato indicado con un objeto SimpleDateFormat
		//declarar un conversor de tipos fcha para que sea manejado por el controlador antes de almacenarlo en el bean
		//Aplica para todos los tipos de variable, tipo fecha
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		}
*********************************************************************************************************************************


**************************************************************************************************************Seccion 8 clase 64
******************************************************************************Implementar metodo insertar en PeliculasServiceImp
	74.-Se crea metodo de insertar() en la interface IPeliculasService
		void Insertar(Pelicula pelicula);
	75.- Se implementa el metodo Insertar() en PeliculasServiceImp
		@Override
		public void insertar(Pelicula pelicula) {
			lista.add(pelicula);		
		}
	76.- Se manda a llamar el metodo insertar desde en el controlador PeliculasController.java
		76.1.- Se declara un atributo de la Inteface IPeliculasService
			private IPeliculasService sevicePeliculas;
		76.2.- Se agrega la anotacion @Autowired, para inyectar una instancia de nuestra clase de servicio
			@Autowired
			private IPeliculasService servicePeliculas;
		76.3.- Por medio de el atributo de la la interface, se llama al metodo insertar
			servicePeliculas.insertar(pelicula);
********************************************************************************************************************************


**************************************************************************************************************Seccion 8 clase 65
**********************************************************Crear metodo en PeliculasController para mostrar la lista de peliculas
	77.- Se crea un nuevo metodo en PeliculasController --> mostrarIndex
		public String mostrarIndex(Model model) {
			return "peliculas/listPeliculas";
		}
		77.1.- Se le añaden las anotaciones del metodo get y se indica a que va a responder
			@GetMapping("/index")
			public String mostrarIndex(Model model) {
				return "peliculas/listPeliculas";
			}
	78.- Se crea un listado de Peliculas con el metodo buscarTodas() del atributo instanciado de IPeliculasService
		List<Pelicula> lista= servicePeliculas.buscarTodas();
	79.- Se añade la lista creada al objeto model
		model.addAttribute("peliculas",lista);
	80.- Se crea el archivo de la vista jsp en la carpetas de views/peliculas -->listPeliculas.jsp
	81.- Para probar se añade en el cuerpo del jsp creado y por medio de espression laguaje el objeto pasado en 
		el model -->PeliculaServiceImpl
		${peliculas}
********************************************************************************************************************************


***************************************Se inhabilita el usuario de session por url y se config para que se almacene en la cookie
	<session-config>
		<tracking-mode>COOKIE</tracking-mode>
	</session-config>
********************************************************************************************************************************


**************************************************************************************************************Seccion 8 clase 66
****************************************************************Ejercicio para renderizar la lista en la plantilla listPeliculas
	82.- Se renderiza la plantilla listPeliculas.html se agregan los tags de spring 
		<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
	83.- Se configura en la pagina jsp los tag de JSTL 
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	84.- Se importa JSTL/fmt a la pagina .jsp
		<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	85.- Se incluye el tag en la pagina jsp
		<jsp:include page="includes/menu.jsp"></jsp:include>
	86.- Se incluye el tag en la pagina jsp
		<jsp:include page="includes/piePagina.jsp"></jsp:include>
	87.- Agregar una variable con la URL relativa a resource, dentro del 			
		head del archivo jsp Se puede incluir en cualquier parte del la pagina .jsp
			<spring:url value="/resources" var="urlPublic" />
	88.- Se modifican los recursos estaticos con la variable urlPublic por medio de espression languaje
	89.- se utiliza tag spring:url en /peliculas/create
		<spring:url value="/peliculas/create" var="urlForm"></spring:url>
	90.- Se modifica el boton por medio de espression languaje, para ser dirijido peliculas/create
		<a href="${urlForm }" class="btn btn-success" role="button" title="Nueva Pelicula" >Nueva</a><br><br>
	91.- Se utilizo foreach con jstl para imprimir las peliculas en la tabla y un choose para seleccionar el estatus
		<c:forEach items="${ peliculas }" var="pelicula">
	        <tr>
	            <td>${pelicula.titulo }</td>
	            <td>${pelicula.genero }</td>
	            <td>${pelicula.clasificacion }</td>
	            <td>${pelicula.duracion }</td>
	            <td><fmt:formatDate value="${pelicula.fechaEstreno}" pattern="dd-MM-yyyy"/></td>
	            <td><c:choose>
	             		<c:when test="${pelicula.estatus == 'Activa' }">
	               			<span class="label label-success">ACTIVA</span>
	               		</c:when>	
	               		<c:otherwise>
	               			<span class="label label-danger">INACTIVA</span>
	               		</c:otherwise>
	            </c:choose></td>
	            <td>
	                <a href="#" class="btn btn-success btn-sm" role="button" title="Edit" ><span class="glyphicon glyphicon-pencil"></span></a>
	                <a href="#" class="btn btn-danger btn-sm" role="button" title="Eliminar" ><span class="glyphicon glyphicon-trash"></span></a>
	            </td>
	        </tr>
	    </c:forEach>
********************************************************************************************************************************


**************************************************************************************************************Seccion 8 clase 67
*******************************************************************************Agregar menu para acceder al listado de peliculas
	92.- Se agrega al views/includes/menu.jsp la opcion del listado de peliculas
		<li><a href="#">Peliculas</a></li>
	93.- Agregamos los tag de spring al archivo menu.jsp
		<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
	94.- Se configura url realizarse	
		<spring:url value="/" var="urlRoot"></spring:url>
	95.- Se modifica el atributo href de la opcion del menu peliculas
		<li><a href="${urlRoot }peliculas/index">Peliculas</a></li>
	96.- Se configura la opcion de menu My CineSite
		<a class="navbar-brand" href="${urlRoot }">My CineSite</a>"
********************************************************************************************************************************
	


**************************************************************************************************************Seccion 8 clase 68
*****************************************************************************************Redirect y atributo Flash en Spring MVC
	97.- Se modifica el metodo guardar del archivo PeliculasController.java para que retorne el redireccionamiento 
		return "redirect:/peliculas/index";

	98.- Mostrar al usuario mensaje de insercion
		98.1.- Se añade un objeto RedirectAttributes a los parametros del metodo guardar
			public String guardar(Pelicula pelicula, BindingResult resultado, RedirectAttributes attributes) {
		98.2.- Se añade un atributo por medio del metodo addFlashAttribute del objeto RedirectAttributes creado
			attributes.addFlashAttribute("msg", "El registro ya fue insertado");
		98.3.- Se modifica la vista listPeliculas.jsp para que muestre el mensaje luego del titulo, con un condicional if de jstl
			<c:if test="${msg != null }">
		      	<div class='alert alert-success' role="alert">${ msg}</div>
		    </c:if>

	----------------------------------------------------------------------------------------------
		@PostMapping(value="/save")
		public String guardar(Pelicula pelicula, BindingResult resultado, RedirectAttributes attributes) {
			
			if(resultado.hasErrors()) {
				System.out.println("Existen Errores.");
				return "peliculas/formPeliculas";
			}
			
			//for (ObjectError error : resultado.getAllErrors()) {
				//System.out.println("Error: "+error.getDefaultMessage());
			//}
			
			System.out.println("Pelicula: "+pelicula);
			System.out.println("Elementos en la lista antes de la insercion: "+servicePeliculas.buscarTodas().size());
			servicePeliculas.insertar(pelicula);
			System.out.println("Elementos en la lista despues de la insercion: "+servicePeliculas.buscarTodas().size());
			attributes.addFlashAttribute("msg", "El registro ya fue insertado");
			//return "/peliculas/formPeliculas";
			return "redirect:/peliculas/index";
		}
********************************************************************************************************************************



**************************************************************************************************************Seccion 9 clase 70
********************************************************************************************************************Upload Files
	9.1.- Configuracion del dispacher Servlet	
		9.1.1.- Configuracion del dispacher Servlet en el web.xml, añadimos la configuracion del multipart dentro del tag servlet y le 
		colocamos en el directorio temporal
			<servlet>
				<servlet-name>springmvc</servlet-name>
				<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
				<init-param>
					<param-name>contextConfigLocation</param-name>
					<param-value>/WEB-INF/spring/dispatcher-servlet.xml</param-value>
				</init-param>
				<load-on-startup>1</load-on-startup>
				
				<multipart-config>
					<location>D:\tmp</location>
				</multipart-config>
			</servlet>
		9.1.2.- Declarar un Bean para soporte de MultiPart (upload file), en el dispatcher-servlet.xml
			<bean id="multipartResolver" class="org.springframework.web.multipart.support.StandardServletMultipartResolver" ></bean>
	9.2.- Modificamos el metodo guardar de PeliculasController.java
		9.2.1.- Se añaden dos parametros al metodo guardar, un objeto MultiPart y un HttpServletRequest
			@PostMapping(value="/save")
			public String guardar(Pelicula pelicula, BindingResult resultado, RedirectAttributes attributes,
				@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {
		9.2.2.- Se valida en el metodo que el archivo no venga vacio
			if(!multiPart.isEmpty()) {
				String nombreImagen = guardarImagen(multiPart, request);
			}
		9.2.3.- Se crea metodo para guardar la imagen
			private String guardarImagen(MultipartFile multiPart, HttpServletRequest request) {
				// Obtenemos el nombre original del archivo
				String nombreOriginal = multiPart.getOriginalFilename();
				// Obtenemos la ruta ABSOLUTA del directorio images
				// apache-tomcat/webapps/cineapp/resources/images/
				String rutaFinal = request.getServletContext().getRealPath("/resources/images/");
				try {
					// Formamos el nombre del archivo para guardarlo en el disco duro
					File imageFile = new File(rutaFinal + nombreOriginal);
					// Aqui se guarda fisicamente el archivo en el disco duro
					multiPart.transferTo(imageFile);
					return nombreOriginal;
				} catch (IOException e) {
					System.out.println("Error " + e.getMessage());
				return null;
				}
			}
	9.3.- Se modifica la vista
		9.3.1.- Se agrega el atributo enctype al formulario
			<form action="${urlForm}" method="post" enctype="multipart/form-data">
	9.4.- Optimizar el metodo guardar
		9.4.1.- Quitando el metodo guardar de PeliculasController y se agrega en la clase utileria
		9.4.2.- Se modifica el metodo para que sea publico y estatico
		9.4.3.- Se modifica el metodo guardar() de PeliculasController, al colocar el metodo como 
			estatico no es necesario instanciar  la clase Utileria.java
				String nombreImagen = Utileria.guardarImagen(multiPart, request);

			if(!multiPart.isEmpty()) {
				//System.out.println("Entre en la validacion vacia");
				String nombreImagen = Utileria.guardarImagen(multiPart, request);
				pelicula.setImagen(nombreImagen);
				//System.out.println("Nombre de la pelicula: "+pelicula.getImagen());
			}
	9.5.- Modificacion para que no se pueda subir un archivo con espacios, si no,los mismos se reemplacen por un guion bajo
		9.5.1.- Se modifica el metodo guardarImagen() de la clase Utilerias
			nombreOriginal = nombreOriginal.replace(" ", "_");
	9.6.- Modificacion para metodo guardarImagen() de la clase de utilerias, para que no se dupliquen las imagenes.
		9.6.1.- Se crea un metodo para generar una cadena de n caracteres aleatorios
			//Metodo para generar una cadena de longitud N de caracteres aleatorios
			public static String randomAlphaNumeric(int count) {
				String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
				StringBuilder builder = new StringBuilder();
				while(count-- != 0) {
					int character = (int)(Math.random()* CARACTERES.length());
					builder.append(CARACTERES.charAt(character));
				}
				return builder.toString();
			}
		9.6.2.- Se crea cadena para que añada 8 caracteres al inicio del nombre del archivoImagen
			String nombreFinal = randomAlphaNumeric(8)+nombreOriginal;
		9.6.3.- Modificamos el nombre del archivo que se guardara en el disco duro reemplazando nombreOriginal por nombreFinal
			// Formamos el nombre del archivo para guardarlo en el disco duro
			File imageFile = new File(rutaFinal+nombreFinal);
		9.6.4.- Se modifica el nombre del archivo que retorna el metodo reemplazando nombreOriginal por nombreFinal
			return nombreFinal;

		public static String guardarImagen(MultipartFile multiPart, HttpServletRequest request) {
				// Obtenemos el nombre original del archivo
				String nombreOriginal = multiPart.getOriginalFilename();
				nombreOriginal = nombreOriginal.replace(" ", "_");
				String nombreFinal = randomAlphaNumeric(8)+nombreOriginal;
				//System.out.println("Nombre Original: "+nombreOriginal);
				// Obtenemos la ruta ABSOLUTA del directorio images
				// apache-tomcat/webapps/cineapp/resources/images/
				String rutaFinal = request.getServletContext().getRealPath("/resources/images/");
				//String rutaFinal = "D:\\Udemy\\Spring\\cineapp\\cineapp\\webapp\\resources\\images\\spiderman.png";
				System.out.println("Ruta Original: "+rutaFinal);
				try {
					// Formamos el nombre del archivo para guardarlo en el disco duro
					File imageFile = new File(rutaFinal+nombreFinal);
					//muestra la ruta del archivo por consola
					System.out.println(imageFile.getAbsolutePath());
					// Aqui se guarda fisicamente el archivo en el disco duro
					multiPart.transferTo(imageFile);
					return nombreFinal;
				} catch (IOException e) {
					System.out.println("Error " + e.getMessage());
				return null;
				}
			}
********************************************************************************************************************************



**************************************************************************************************************Seccion 9 Clase 75
	9.7.- Se crea opcion para guardar las imagenes del banner
		9.7.1.- Se crea bean banner con su get, set y metodo toStringen nuestro paquete modelo --> Banner.java
			public class Banner {
				private int id;
				private String titulo;
				private Date fecha; // Fecha de Publicacion del Banner
				private String archivo; // atributo para guardar el nombre de la imagen
				private String estatus;

				/**
				 * Constructor de la clase
				 */
				public Banner(){		
					this.fecha = new Date(); // Por default, la fecha del sistema
					this.estatus="Activo"; // Por default el banner esta activo
				}
		9.7.2.- Se crea Interface -->IBannerService
		9.7.2.1.- Se crean metodos 
			public interface IBannerService {
				void insertar(Banner banner);
				List<Banner> buscarTodos();
			}
	9.7.3.- Se crea clase implementacion de la interface IBannerService
		9.7.3.1.- Se implementa la interface IBannerService
			
			public class BannerServiceImp implements IBannerService{
			...
			}

		9.7.3.2.-  Se crea una lista de tipo Banner			
			List<Banner> lista = null;
	  	9.7.3.3.- Se modifica el contructor de la clase BannerServiceImp
			9.7.3.3.1.- Se Crea una nueva lista enlazada en el constructor de la clase
				lista = new LinkedList<>();
			9.7.3.3.2.- Se crean y setean objetos de tipo Banner
				Ej:
				Banner banner1 = new Banner();
				banner1.setId(1);
				banner1.setTitulo("Alient Covenant");
				banner1.setArchivo("slide6.jpg");
			9.7.3.3.3.- Se añaden los objetos de tipo Banner a la lista --> lista 
		9.7.3.4.-Se modifica el metodo sobreescrito de la interface insertar() para que inserte la 
			lista un objeto de tipo BannerServiceImp
			@Override
			public void insertar(Banner banner) {
				lista.add(banner);		
			}
		9.7.3.5.- Se modifica el metodo buscarTodos() para que retorne la lista de banner
			@Override
			public List<Banner> buscarTodos() {
				return lista;
			}


	9.7.4.- Se crea la clase controlador BannersController.java
		@Controller
		public class BannersController {
		9.7.4.1.- Se Inyecta instancia de la clase de servicio 
			@Autowired
			private IBannerService serviceBanner;
		9.7.4.2.- Se crea metodo que añade las lista de Banners a el Modelo por medio de metodo GET
			@GetMapping("/index")
			public String mostrarIndex(Model modelo) {
				9.7.4.2.1.- Se crea lista de Banner y se llena con el metodo buscarTodos de la interface IBannerService
					List<Banner> lista = serviceBanner.buscarTodos();
				9.7.4.2.2.- Se añade la lista al modelo
					modelo.addAttribute("Banners", lista);
				9.7.4.2.3.- Se retorna una cadena con la redireccionamiento
					return "banners/listBanners";

					@GetMapping("/index")
					public String mostrarIndex(Model modelo) {
		
						// Ejercicio: Implementar metodo
						List<Banner> lista = serviceBanner.buscarTodos();
						modelo.addAttribute("Banners", lista);
						
						return "banners/listBanners";
					}
		9.7.4.3.- Se crear vista listBanners.jsp 
			9.7.4.3.1.- Se copia la plantilla de listBanners.html en listBanners.jsp
			9.7.4.3.2.- Se añaden los tags: spring, core y fmt
				<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
				<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
				<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
			9.7.4.3.3.- Se añaden los tags de spring para las urlPublic
				<spring:url value="/resources" var="urlPublic"></spring:url>
    			<spring:url value="/banners/create" var="urlForm"></spring:url>
    		9.7.4.3.4.- Se modifican los recursos estaticos con la url de spring configuradas
    		 	ej:	
    		 	<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    		9.7.4.3.5.- Se incluyen los archivos menu.jsp y piePagina.jsp
    		    <jsp:include page="../includes/menu.jsp"></jsp:include>
      			<jsp:include page="../includes/piePagina.jsp"></jsp:include>
      		9.7.4.3.6.- Se crea bucle para que muestre la lista de Banners dentro de la tabla del formulario

	      		<c:forEach items="${Banners}" var="banner">
	            	<tr>
	                	<td>${banner.id}</td>
	                	<td>${banner.titulo}</td>
	                 	<td><fmt:formatDate value="${banner.fecha}" pattern="dd-MM-yyyy"/></td>    
	                	<td>${banner.archivo}</td>                         
	                	<td>
	                		<c:choose>
								<c:when test="${banner.estatus == 'Activa' }">
									<span class="label label-success">${banner.estatus}</span>
								</c:when>
								<c:otherwise>
									<span class="label label-danger">${banner.estatus}</span>
								</c:otherwise>
							</c:choose>
	                	</td>
	                	<td>
	                    	<a href="#" class="btn btn-success btn-sm" role="button" title="Edit" ><span class="glyphicon glyphicon-pencil"></span></a>
	                    	<a href="#" class="btn btn-danger btn-sm" role="button" title="Eliminar" ><span class="glyphicon glyphicon-trash"></span></a>
	                	</td>
	            	</tr>
	            </c:forEach>







        9.7.4.4.- Se crea metodo en el BannersController para crear los Banners
        	/**
	 		* Metodo para mostrar el formulario para crear un nuevo Banner
	 		* @return
	 		*/
			@GetMapping("/create")
			public String crear() {		
				return "banners/formBanner";		
			}
			
		9.7.4.5.-Crear vista formBanner.jsp. Utilizar el archivo formBanner.html de la plantilla
            9.7.4.5.1.- Se copia la plantilla de listBanners.html en listBanners.jsp
			9.7.4.5.2.- Se añaden los tags: spring, core y fmt
				<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
				<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
				<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
			9.7.4.5.3.- Se añaden los tags de spring para las urlPublic
				<spring:url value="/resources" var="urlPublic"></spring:url>
    			<spring:url value="/banners/save" var="urlForm"></spring:url>
    		9.7.4.5.4.- Se modifican los recursos estaticos con la url de spring configuradas
    		 ej:	<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    		9.7.4.5.5.- Se incluyen los archivos menu.jsp y piePagina.jsp
    		    <jsp:include page="../includes/menu.jsp"></jsp:include>
      			<jsp:include page="../includes/piePagina.jsp"></jsp:include>
      		9.7.4.5.6.- Se crea tag de spring para mostrar los errores del biding del modelo
      			<!--Se le pasa el nombre del objeto de modelo-->
				<spring:hasBindErrors name="pelicula">
					<!--Clase propia de bootstrap para alertar y colocar mensaje en color rojo-->
					<div class='alert alert-danger' role='alert'>
						Por favor corrija los siguientes errores:
						<ul>
							<c:forEach var="error" items="${errors.allErrors}">
								<li><spring:message message="${error}" /></li>
							</c:forEach>
						</ul>
					</div>
				</spring:hasBindErrors>		
			9.7.4.5.7.- Se modifica la firma del formulario con el atributo enctype para la carga del archivo 
				<form action="${urlForm }" method="post" enctype="multipart/form-data">
      	9.7.4.6.- Se crea el metodo guardar() en BannersController.java, el cual recibe un Banner, captura de parametros del formulario
      		el archivo de imagen
      		@PostMapping(value="/save")
			public String guardar(Banner banner, BindingResult resultado, RedirectAttributes attributes,
				@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {
			9.7.4.6.1.- Se valida si hay errores
				if(resultado.hasErrors()) {
					//System.out.println("Existen Errores.");
					return "banner/formBanner";
				}
			9.7.4.6.2.- Se valida si esta cargado el archivo de imagen
				if(!multiPart.isEmpty()) {
					//System.out.println("Entre en la validacion vacia");
					String nombreImagen = Utileria.guardarImagen(multiPart, request);
					banner.setArchivo(nombreImagen);
					//System.out.println("Nombre de la pelicula: "+pelicula.getImagen());
				}
			9.7.4.6.3.- Se inserta el objeto banner
				serviceBanner.insertar(banner);
			9.7.4.6.4.- Se añade el mensaje de exito, a los atributos para mostrarlo en la vista
				attributes.addFlashAttribute("msg", "El archivo fue insertado");
			9.7.4.6.5.- Se retorna a la principal de banner
				return "redirect:/banners/index";
	9.7.5.- Se realizan modificaciones al archivo controlador principal HomeController.java
		9.7.5.1.- Se le agrega inyeccion de dependencias de la interface IBannerService
			@Autowired
			private IBannerService serviceBanner;
		9.7.5.2.- Se realizan modificaciones al metodo buscar()
			9.7.5.2.1.- Se crea lista con objetos Banner y se llena con el metodo buscarTodos() de IBannerService
				List<Banner> banners = serviceBanner.buscarTodos();
			9.7.5.2.2.- Se añade la lsita al modelo
				model.addAttribute("banners", banners); 
		9.7.5.3.- Se realizan modificaciones al metodo mostrarPrincipal()
			9.7.5.3.1.- Se crea lista con objetos Banner y se llena con el metodo buscarTodos() de IBannerService
				List<Banner> banners = serviceBanner.buscarTodos();
			9.7.5.3.2.- Se añade la lista al modelo
				model.addAttribute("banners", banners); 
********************************************************************************************************************************


**********************************************************************************************************Seccion 10 Capitulo 77
****************************************************************************************************************Form Tag Library
	10.1.- Se añaden los tag de spring en el formPeliculas.jsp y se agrega el atributo model al formulario
		10.1.1.- Se declara el tag de spring
			<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
		10.1.2.- Se reemplazan los form de html por los tags/form de spring --><form:form/>
			Ej:	<form:form action="${urlForm}" method="post" enctype="multipart/form-data" modelAttribute="pelicula"></form:form>
		10.1.3.- Se reemplazan los input de html por los tags/form de spring --><form:input/>
			Ej: <form:input type="text" class="form-control" path="titulo" id="titulo" required="required" />
		10.1.4.- Se reemplazan los select de html por los tags/form de spring --><form:select/>
			Ej: <form:select id="clasificacion" path="clasificacion" class="form-control">
		10.1.5.- Se reemplazan los option de html por los tags/form de spring --><form:option/>
			Ej: <form:option value="A">Clasificacion A</form:option>
		10.1.7.- Se cambia el nombre del atributo name por path en cada uno de los elementos del formulario cambiados, que es el 
			utilizado por los tag spring
		10.1.7.- Se agrega al formulario el atributo modelAttribute y se le pasa nuestro objeto modelo -->PeliculasController
			<form:form action="${urlForm}" method="post" enctype="multipart/form-data" modelAttribute="pelicula">
	10.2.- Se modifica PeliculasController.java
		10.2.1.- Se modifica el metodo crear() para pasarle como parametro el objeto Pelicula y como 
		ModelAttribute y que este vinculado al formulario
			@GetMapping("/create")
			public String crear(@ModelAttribute Pelicula pelicula) {		
		10.2.2.- Se modifica el metodo guardar() para que el objeto pelicula sea de tipo ModelAttribute 
		y que este vinculado al formulario
			@PostMapping(value="/save")
			public String guardar(@ModelAttribute Pelicula pelicula, BindingResult resultado, RedirectAttributes attributes,
				@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request)
---------------------------------------------------------------------------------Capitulo 80 Data Binding con objetos compuestos
	10.3.- Se crea clase de detalle de pelicula para poner en practica databinding con objetos compuestos
		10.3.1.- Se crea bean --> Detalle.java
			10.3.1.1.- Se crean los atributos
				private int id;
				private String director;
				private String actores;
				private String sinopsis;
				private String trailer;
			10.3.1.2.- Se crean get y set de los atributos
			10.3.1.3.- Se crea constructor vacio
			10.3.1.4.- Se sobreescribe metodo toString()
		10.3.2.- Se modifica la clase Pelicula
			10.3.2.1.- Se agrega atributo de tipo Detalle 
				private Detalle detalle;
			10.3.2.2.- Se crea su get y set
			10.3.2.3.- Se borra el metodo toString() y se crea nuevamente
	10.4.- Se modifica la vista formPelicula.jsp
		10.4.1.- Se descomenta de la plantilla  los input que albergan los atributos de la clase detalle
		10.4.2.- Se le agragan los tags de spring a los input, se cambia el atributo name por path y se coloca el objeto compuesto
			<form:input type="text" class="form-control" path="detalle.director" id="director" required="required" />
			<form:input type="text" class="form-control" path="detalle.actores" id="actores" required="required" />
			<form:input type="text" class="form-control" path="detalle.trailer" id="trailer" placeholder="URL completa del video de YOUTUBE" required="required" />
			<form:textarea class="form-control" rows="5" path="detalle.sinopsis" id="sinopsis"></form:textarea>
	10.5.- Se modifica la vista detalle.jsp, por medio de Expression Languaje se vincule al valor de actores y director
		Actores : ${pelicula.detalle.actores }	<br>
		Director: ${pelicula.detalle.director }  <br>
------------------------------------------------------------------------------Capitulo 81 Configurar video de youtube de trailer
	10.6.- Se incluye por medio de la etiqueta de html5 iframe y expression Languaje un triler de youtube
		<iframe width="100%" height="315"	src="${pelicula.detalle.trailer }"> </iframe>

		<div class="col-sm-7">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Trailer</h3>
				</div>
				<div class="panel-body">
					<iframe width="100%" height="315"	src="${pelicula.detalle.trailer }"> </iframe>
				</div>
			</div>
		</div>
	10.7.- Se configura la sinopsis
		<div class="panel-body"> <p>${pelicula.detalle.sinopsis }</p>
		
		Ej:<div class="col-sm-5">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">SINOPSIS</h3>
				</div>
				<div class="panel-body"> <p>${pelicula.detalle.sinopsis }</p>
				</div>
			</div>
		</div>
---------------------------------------------------------------------------------Capitulo 82 Generar de forma dinamica un select
	10.8.- Se realiza de forma dinamica la busqueda de los generos en el select de formPelicula.jsp
		10.8.1.- Se crea metodo que retorne una lista de String en la Interface IPeliculasService.java -->buscarGeneros()
			List<String> buscarGeneros();
		10.8.2.- Se implementa el metodo creado en PeliculasServiceImp sobreescribiendo el metodo buscarGeneros()
			@Override
			public List<String> buscarGeneros() {
				List<String> generos = new LinkedList<>();
				generos.add("Accion");
				generos.add("Aventura");
				generos.add("Clasicas");
				generos.add("Comedias Romanticas");
				generos.add("Dramas");
				generos.add("Terror");
				generos.add("Infantil");
				generos.add("Accion y Aventura");
				generos.add("Romanticas");
				generos.add("Ciencia Ficción");
			
				return generos;
			}
	10.9.- Se modifica metodo crear del PeliculasController.java, para llamar al metodo creado y agregarlo al modelo
		@GetMapping("/create")
		public String crear(@ModelAttribute Pelicula pelicula, Model modelo) {
			modelo.addAttribute("generos", servicePeliculas.buscarGeneros());
			return "peliculas/formPeliculas";
		}
--------------------------------------------------------------------------------------------------------Form Tag Library -Select
	10.10.- Modificamos la vista formPeliculas.jsp para presentar la lista de forma dinamica
		<form:select id="genero" path="genero" class="form-control" items="${generos}"></form:select>
-------------------------------------------------------------------------------------------------------Form Tag Library - Hidden
	10.11.- Incluir en formPelicula.jsp un hidden el id de la pelicula
		<form:hidden path="id"/>
	10.12.- Se crea un hidden para la imagen de la Pelicula
		<form:hidden path="imagen"/>
	10.13.- Se crea imagen por defecto para el formulario formPeliculas.jsp deltro del form
		<div class="row">
			<div class="col-sm-3">
				<div class="form-group">
					<img class="img-rounded" alt="" src="${urlPublic}/images/${pelicula.imagen}" title="Imagen actual de la pelicula">
				</div>
			</div>
		</div>
---------------------------------------------------------------------Capitulo 86 Integracion de plantilla para formContactos.jsp
	10.14.- Integracion de plantilla de formulario de contactos -->formContactos.jsp
		10.14.1.- Se crea formContactos.jsp
		10.14.2.- Se copia plantilla html en jsp creado
		10.14.3.- Se añaden los tags: spring, core y los tags de spring
			<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
			<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	10.14.4.- Se añaden los tag de url de spring
		<spring:url value="/resources" var="urlPublic"></spring:url>
		<spring:url value="/" var="urlForm"></spring:url>
	10.14.5.- Se añaden las url configuradas en los tag a los directorios
		<link href="${urlPublic }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${urlPublic }/bootstrap/css/theme.css" rel="stylesheet">
		<script src="${urlPublic }/bootstrap/js/bootstrap.min.js"></script> //Al final
	10.14.6.- Se incluye el tag para incluir el menu en la pagina formContactos.jsp
		<jsp:include page="includes/menu.jsp"></jsp:include>
	10.14.7.- Se incluye el tag para incluir el menu en la pagina formContactos.jsp
		<jsp:include page="includes/piePagina.jsp"></jsp:include>
	10.14.8.- Se agregan los tags de spring a los inputs del formulario y el atributo name se cambia por path
		ej:<form:input type="email" class="form-control" path="email" id="email" placeholder="Email" required="required"/>
	10.15.- Se crea clase controlador para contactos --> ContactosController.java
		10.15.1.- Se crea la clase controlador  -->ContactosController
		10.15.2.- Se le agrega la anotacion @Controller
			@Controller
			public class ContactosController {
		10.15.3.- Se crea metodo que devuelve un String --> mostrarFormulario()
			@GetMapping("/contactos")
			public String mostrarContactos() {
				return "formContactos";
			}
	10.16.- Vincular el formContactos con la clase de modelo Contactos
		10.16.1.- Se crea una clase modelo Contactos.java con sus get y set, el constructor y el metodo toString
			private int id;
			private String nombre;
			private String email;
			private String rating;
			private String[] generos;
			private String[] notificaciones;
			private String comentarios;
		


		10.16.2.- Se agrega al metodo mostrarContactos() un parametro una variable de tipo Contacto y se le agrega la anotacion @ModelAttribute
			para que sea instanciada la clase al llamarse el metodo
			@GetMapping("/contactos")
			public String mostrarContactos(@ModelAttribute Contacto contacto) {
				return "formContactos";
			}
		10.16.3.- Se modifica el metodo mostrarContactos() para que identifique el atributo modelo con otro nombre
			@GetMapping("/contactos")
			public String mostrarContactos(@ModelAttribute("instanciaContacto") Contacto contacto) {
				return "formContactos";
			}
		10.16.4.- Se modifica la vista formContactos.jsp, para que el formulario se configure con los tags de spring, se añade metodo 
			post y el modelAttribute para que identifique el modelo
			<form:form class="form-horizontal" method="post" modelAttribute="instanciaContacto">
		10.16.5.- Se programa el select de generos del formContactos.jsp para que utilice el metodo generos de la clase de utilerias
			10.16.5.1.- Se agrega al metodo mostrarContactos() un parametro de tipo modelAttribute
				@GetMapping("/contactos")
				public String mostrarContactos(@ModelAttribute("instanciaContacto") Contacto contacto, Model modelo) {
					return "formContactos";
				}
			10.16.5.2.- Se instancia una variable de tipo interface IPeliculasService.java, con la anotacion @Autowired para la inyeccion
			 de dependencias.
				@Autowired
				IPeliculasService servicePeliculas;
			10.16.5.3.- Se agrega la lista que genera el metodo buscarGeneros() al modelo
				 modelo.addAttribute("generos",servicePeliculas.buscarGeneros());
			10.16.5.4.- Se modifica el select de generos 
				<form:select id="genero" path="generos" multiple="multiple" class="form-control" items="${generos}"></form:select>
		10.16.6.- Se configura el metodo guardar() del ContactosController y el Action del formulario
			@PostMapping("/contacto")
			public String guardar() {
				return "formContactos";
			}
			<form:form class="form-horizontal" method="post" action="${urlRoot }contacto" modelAttribute="instanciaContacto">
		10.16.7.- Se programa el databinding en el metodo guardar para tener los datos que captura el usuario en el formulario,
		los campos deben tener el mismo nombre de la clase modelo
			@PostMapping("/contacto")
			public String guardar(@ModelAttribute("instanciaContacto") Contacto contacto, Model modelo) {
				modelo.addAttribute("generos",servicePeliculas.buscarGeneros());
				System.out.println(contacto)		
				return "formContactos";
			}
-------------------------------------------------------------------------------------------------------------Form Tag CheckBoxes
		10.16.8.- Configuracion de los checkbox de la vista formContactos.jsp
			10.16.8.1.- Se cambia el atributo, los get y set, toString() notificaciones de la clase Contacto.java
				private List<String> notificaciones;
			10.16.8.2.- Se crea metodo en ContactosController.java para que genere los tipos de notificaciones
				public List<String> tipoNotificaciones(){
					List<String> notificaciones = new LinkedList<>();
					notificaciones.add("Estrenos");
					notificaciones.add("Promociones");
					notificaciones.add("Noticias");
					notificaciones.add("Preventas");
					return notificaciones;
				}
			10.16.8.3.- Se agregan al modelo en el metodo mostrarContactos()
				@GetMapping("/contacto")
				public String mostrarContactos(@ModelAttribute("instanciaContacto") Contacto contacto, Model modelo) {
					modelo.addAttribute("generos",servicePeliculas.buscarGeneros()); 
					modelo.addAttribute("tipos", tipoNotificaciones());
					return "formContactos";
				}
			10.16.8.4.- Se añade tag form de spring 
				<form:checkboxes items="${tipos}" path="notificaciones"/>
			10.16.8.5.- Se modifica el metodo guardar para que redireciione a el metodo mostrarContactos(), y no tener que añadir al modelo 
				el atributo model
				@PostMapping("/contacto")
				public String guardar(@ModelAttribute("instanciaContacto") Contacto contacto) {
		+			//modelo.addAttribute("generos",servicePeliculas.buscarGeneros());
					return "redirect:/contacto";
				}
Spring Data JPA*****************************************************************************************************************
******************************************************************************************************Seccion 11 - Comfiguracion
	Comfiguracion de Spring Data JPA 
	1.- Descarga de Dependencias
		***https://spring.io/projects/spring-data-jpa -->version 2.2.1 08/11/19
		***http://hibernate.org/orm/releases/         -->version 5.4.0 08/11/19
		***http://hibernate.org/validator/            -->version 6.0.17.final 08/11/19
		***https://dev.mysql.com/downloads/connector/j/  --> version 8.0.18
		1.a.- Se configuran las dependencias en el POM.xml
			<dependency>
				<groupId>org.springframework.data</groupId>
				<artifactId>spring-data-jpa</artifactId>
				<version>2.2.1.RELEASE</version>
			</dependency>
			<dependency>
				<groupId>org.hibernate</groupId>
				<artifactId>hibernate-core</artifactId>
				<version>5.4.0.Final</version>
			</dependency>
			<dependency>
				<groupId>org.hibernate</groupId>
				<artifactId>hibernate-validator</artifactId>
				<version>6.0.17.Final</version>
			</dependency>
			<dependency>
				<groupId>mysql</groupId>
				<artifactId>mysql-connector-java</artifactId>
				<version>8.0.18</version>
			</dependency>
	2.- Configuracion de los beans en el root-context.xml:
		2.a.-Configuracion del paquete del repositorio
			<jpa:repositories base-package="net.itinajero.app.repository" />

			Nota: Spring escanea ese packete para buscar interfaces que estan 
			marcadas con la anotacion Repository y que estiendan de la interface 
			repository, crea una nueva instancia y la coloca en el contenedor de 
			beans para que sea utilizada por la aplicacion.
		2.b.- dataSource
			<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
				<property name="driverClassName" value="com.mysql.jdbc.Driver" />
				<property name="url" value="jdbc:mysql://localhost:3306/cineapp?useSSL=false" />
				<property name="username" value="root" />
				<property name="password" value="admin" />
			</bean>

			Nota: dataSource es una interface con los metodos necesarios para la conexion a la BDs
		2.c.- Configuracion de la interface jpaVendorAdapter
			Nota: Permite inicializar la configuracion por default con implementaciones 
			de jpa, como hibernete, en este caso se implementara la clase 
			HibernateJpaVendorAdapter.
				<bean id="jpaVendorAdapter" class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
					<property name="generateDdl" value="false" />
					<property name="showSql" value="true"></property>
					<property name="databasePlatform" value="org.hibernate.dialect.MySQL5Dialect" />
				</bean>
		2.d.- entity Manager factory
			<bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
				<property name="packagesToScan" value="net.itinajero.app.model" />
				<property name="dataSource" ref="dataSource" />
				<property name="jpaVendorAdapter" ref="jpaVendorAdapter" />
			</bean>
			Nota: es un componente que permite la gestion nuestras clases modelos en la BDs.

		2.b.- transaction manager
			<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
				<property name="entityManagerFactory" ref="entityManagerFactory" />
			</bean>
			Nota: Permite los metodos de base de datos transaccionales 







	3.- Se crea el paquete de repositorio
		net.itinajero.app.repository


		Ejemplo del root-context.xml
			/*<?xml version="1.0" encoding="UTF-8"?>
			<beans xmlns="http://www.springframework.org/schema/beans"
				   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				   xmlns:context="http://www.springframework.org/schema/context"
				   xmlns:jpa="http://www.springframework.org/schema/data/jpa"
				   xmlns:tx="http://www.springframework.org/schema/tx"
				   xsi:schemaLocation="http://www.springframework.org/schema/beans 
				   		http://www.springframework.org/schema/beans/spring-beans.xsd
						http://www.springframework.org/schema/context 
						http://www.springframework.org/schema/context/spring-context-4.3.xsd
						http://www.springframework.org/schema/data/jpa 
						http://www.springframework.org/schema/data/jpa/spring-jpa-1.8.xsd
						http://www.springframework.org/schema/tx 
						http://www.springframework.org/schema/tx/spring-tx-4.3.xsd">

				<context:component-scan base-package="net.itinajero.app.service" />
				
				<jpa:repositories base-package="net.itinajero.app.repository" />
				
				<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
					<property name="driverClassName" value="com.mysql.jdbc.Driver" />
					<property name="url" value="jdbc:mysql://localhost:3306/cineapp?serverTimezone=UTC" />
					<property name="username" value="root" />
					<property name="password" value="admin" />
				</bean>
				<bean id="jpaVendorAdapter" class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
					<property name="generateDdl" value="false" />
					<property name="showSql" value="true"></property>
					<property name="databasePlatform" value="org.hibernate.dialect.MySQL5Dialect" />
				</bean>
				<bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
					<property name="packagesToScan" value="net.itinajero.app.model" />
					<property name="dataSource" ref="dataSource" />
					<property name="jpaVendorAdapter" ref="jpaVendorAdapter" />
				</bean>
				<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
					<property name="entityManagerFactory" ref="entityManagerFactory" />
				</bean>

			</beans>*/
	Creacion de la Base de datos-----------------------------------------------------------------------------------------------------
		1.- Se abre CMD
		2.- Se ingresa en la carpeta donde se tiene el xampp c:/xampp/mysql/bin
		3.- Se coloca el comando mysql -u root -p
		4.- Se coloca el password --> admin
			4.1.- Se revisan las bds creadas --> show databases;
			4.2.- Se sale de la consola de my sql
		5.- Se importa archivo para la creacion de bds del proyecto cineapp.showSql
			5.1.- Nos colocamos en consola en donde se encuentra el archivo
			sentencia--> mysql -u root -p cineapp < cineapp.sql
		6.- Entramos nuevamente a la consola de mysql con las credenciales
		7.- Se usa la base de datos creada
			use cineapp;
		8.- Mostramos las tablas creadas 
			show tables;
	Prabando la configuracion  de Spring Data JPA-----------------------------------------------------------------------------------
Spring Data JPA*****************************************************************************************************************
*****************************************************************Seccion 12 - Anotaciones JPA @Entity @Table @id @GeneratedValue
	Configuracion de clase Noticia como una entidad, para persistir en la BDs
		1.- Se agrega la anotacion @entity antes de la firma de la clase para crear la entidad, 
		asegurarse que el import sea del aquete javax.persistence
			import javax.persistence.Entity;

			@Entity
			public class Noticia {
		2.- Se agrega la anotacion @Table para configurar el nombre de la tabla donde queremos almacenar la entidad, 
		se valida el import de javax.persistence.
			@Entity
			@Table(name="noticias")
			public class Noticia {

		3.- Configurar la llave primaria, se declara un atributo private int id, y se le agrega la anotacion @Id,
		el atributo debe ser unico en la entidad
			@Id
			private int id;

		4.- Se agrega la anotacion @GeneratedValue, para que el atributo se genere de forma automatica autoincrementable,
		se pasa como parametro la forma como se va a generar la llave primaria, por ser mysql es Identity, para oracle 
		es sequence
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
		5.- Creacion de repositorio para administrar las entidades de tipo noticia(CRUD), esta interface se encargara de 
			las operaciones basicas de la entidad(CRUD)
			5.1.- Se crea Interfaz que extienda de la interfaz CrudRepository, new --> Interface -->Nombre de la interface 
			-->Extended Innterfaces (Añade que extienda de la interfaz CrudRepository,Se le añaden dos parametros
				el nombre de la clase entidad, tipo de dato para la llave primaria)
				public interface NoticiasRepository extends CrudRepository<Noticia, Integer> {

				}
			5.2.- Agregar anotacion @Repository, para que se registre como un nuevo bean en nuestro ApplicationContext
				@Repository
				public interface NoticiasRepository extends CrudRepository<Noticia, Integer> {

				}
			5.3.-(Para pruebas de la operacion Create del CRUD) Se crea clase AppCreate() con metodo main, aplicacion para persistir (Crear),
			en la tabla Noticias un objeto de tipo noticia.	
				5.3.0.- Se cambia root-context al directorio src/main/resources
				5.3.1.- Se crea aplication context dentro del metodo main()
					// TODO Auto-generated method stub
					ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
				5.3.2.- Se crea una instancia de la interfaz repository, con dos parametros, el bean del repositorio
					y el tipo
					NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
				5.3.3.- Se utiliza el metodo save de la interfaz configurada, para persistir la entidad
					//Se crea instancia de objeto noticia
					Noticia noticia = new Noticia();
					noticia.setTitulo("Proximo estreno: superman");
					noticia.setDetalle("El mes de septiembre es el gran estreno de Superman");	
					repo.save(noticia);
				5.3.4.- Se cierra el context de la aplicacion
					context.close();
			5.4.-(Para pruebas de la operacion Read del CRUD) Se crea clase AppRead() con metodo main, aplicacion 
			para leer una entidad,	en la tabla Noticias un objeto de tipo noticia.	
				
				5.4.1.- Se crea aplication context dentro del metodo main()
					ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
				5.4.2.- Se crea una instancia de la interfaz repository, con dos parametros, el bean del repositorio
					y el tipo
					NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
				5.4.3.-Se busca objeto por medio de la llave primaria
				Este metodo nos devuelve un objeto de tipo opcional, que se incluye a partir de java 8, 
				es un objeto contenedor que puede contener null o no, por lo que se evita el nullPointerException 
				Optional<Noticia> noticia = repo.findById(1);
				//Devuelve un objeto Opcional que envuelve al objeto Noticia
				System.out.println(noticia);
				//Devuelve un objeto Noticia
				System.out.println(noticia.get());
			5.5.-(Para pruebas de la operacion Update del CRUD) Se crea clase AppUpdate() con metodo main, aplicacion 
			para modificar tabla Noticias un objeto de tipo noticia.	
				
				5.5.1.- Se crea aplication context dentro del metodo main()
					ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
				5.5.2.- Se crea una instancia de la interfaz repository, con dos parametros, el bean del repositorio
					y el tipo
					NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
				5.5.3.-Se busca objeto por medio de la llave primaria
					Este metodo nos devuelve un objeto de tipo opcional, que se incluye a partir de java 8, 
					es un objeto contenedor que puede contener null o no, por lo que se evita el nullPointerException 
					Optional<Noticia> noticia = repo.findById(1);
					//Devuelve un objeto Opcional que envuelve al objeto Noticia
					System.out.println(noticia);
					//Devuelve un objeto Noticia
					System.out.println(noticia.get());
				5.5.4.- Modificamos los atributos de la clase y guardamos
					//Se busca objeto por medio de la llave primaria
					//Este metodo nos devuelve un objeto de tipo opcional, que se incluye a partir de java 8, es un objeto contenedor
					//que puede contener null o no, por lo que se evita el nullPointerException 
					Optional<Noticia> optional = repo.findById(1);
					
					//Se valida si se encontro la entidad
					if (optional.isPresent()) {
						Noticia noticia = optional.get();
						System.out.println(noticia);
						noticia.setEstatus("Inactiva");
						
						//Se modifica la entidad y se guarda
						//Se utiliza el mismo metodo save que se utiliza para crear, spring toma como referencia el valor id
						//como se recupero el objeto con el metodo find, este tiene un id diferente a 0, por lo que detecta 
						//que es una modificacion y realiza un update, en caso contrario realiza un insert
						repo.save(noticia);
					}
				5.5.5.- Se cierra el context de la aplicacion
					context.close();
			5.6.- Metodo para verificar si una entidad existe en la base de datos(metodo existById)
				System.out.println(repo.existsById(2));
			5.7.- Metodo para borrar registros deleteById, validando que exista el registro que se quiere borrar
				if(repo.existsById(3))
				repo.deleteById(3);	
			5.8.- Metodo count, Contar el numero de registros de una tabla(metodo count del repository)
				long numero = repo.count();
				System.out.println("Se encontraron "+numero+" registros.");


			5.9.- Metodo findAll(), El metodo FindAll regresa un objeto de la interfaz iterable, la cual envuelve los objetos de 
				tipo Noticia, todos los objetos iterable pueden ser recorridos por un foreach*/
				Iterable<Noticia> it = repo.findAll();
				for (Noticia noticia : it) {
					System.out.println(noticia);			
				}


			5.10.- Metodo deleteAll(), borra todo los registros.				
				//Metodo que borra todos los registros
				repo.deleteAll();
			5.11.- Metodo findAllById, recupera varios registros por el id
				//recupera varios registros,devuelve un objeto iterable
				List<Integer> lista = (List) new LinkedList<Integer>();
				
				lista.add(7);
				lista.add(9);
				
				Iterable<Noticia> it = repo.findAllById(lista);
				for (Noticia not : it) {
					System.out.println(not);
				}
Spring Data JPA Interfaz JpaRepository - Seccion 13*****************************************************************************
********************************************************************************************************Metodos de JpaRepository
	La interfaz principal de Spring Data Repository es Repository, de esta extienden:
		***CrudRepository, proporsiona los metodos para operaciones CRUD, lleva dos parametros 
			La clase dominio o entidad
			El tipo de id de la clase de dominio
		***PagindAndSortingRepository, agrega metodos para recuperar entidades utilizando paginaciony ordenamiento, 
		extienden de la interfaz CrudRepository.
		***JpaRepository, extiende de la interfaz PagindAndSortingRepository y agrega funcionalidad especifica para 
		la tecnologia JPA.
	1.- Metodo findAll de la interfaz JpaRepository
		//Obetener todas las entidades
		//Al extender de la interfaz JpaRepository, el metodo findAll, devuelve un objeto list
		List<Noticia> lista = repo.findAll();
		for (Noticia noticia : lista) {
			System.out.println(noticia);
		}	
	2.- Metodo deleteAllInBatch()
		Este metodo de diferencia deleteAll, en que se ejecuta con una sola sentencia delete a diferencia deleteAll
		repo.deleteAllInBatch();

	3.- Metodo findAll con ordenamiento, este metodo recibe un objeto de tipo sort y devuelve un objeto lista
		List<Noticia> lista = repo.findAll(Sort.by("Titulo").descending());
		
		for (Noticia noticia : lista) {
			System.out.println(noticia);
		}

	4.- Metodo findAll con ordenamiento por mas de un campo, este metodo recibe un objeto de tipo sort 
	y devuelve un objeto lista
		List<Noticia> lista = repo.findAll(Sort.by("fecha").descending().and(Sort.by("titulo").ascending()));
		
		for (Noticia noticia : lista) {
			System.out.println(noticia);
		}
	5.- Metodo findAll(Page), devuelve un objeto Page y se le debe añadir un objeto plegable comom PageRequest.of(Pag, Cant), este emtodo genera listas 
	con dos parametros el numero de la pagina y la cantidad de registros por pagina
		Page<Noticia> pagina = repo.findAll(PageRequest.of(1, 5));
		
		for (Noticia noticia : pagina) {
			System.out.println(noticia);
		}
	6.- Metodo findAll(Page) con paginacion y ordenamiento, devuelve un objeto Page y se le debe añadir un objeto 
	plegable comom PageRequest.of(Pag, Cant, Sort), este emtodo genera listas con tres parametros el numero de la 
	pagina, la cantidad de registros por pagina y el tipo de ordenamiento
		Page<Noticia> pagina = repo.findAll(PageRequest.of(1, 5, Sort.by("Titulo").descending()));
		
		for (Noticia noticia : pagina) {
			System.out.println(noticia);
		}
Spring Data JPA Query Methods - Seccion 14**************************************************************************************
*******************************************************************************************************************Query Methods
 	1.- Consulta por filtro de campo, Se crea firma del metodo en la interfaz NoticiasRepository.java, que devuelva un objeto lista 
 	de la entidad, luego de la palabra reservada findBy se coloca el nombre del campo, y se le pasa como paramatro el tipo de dato 
 	y el nombre
 		//select * from noticia where estado = ?
 		List<Noticia> findByEstado(String estado);

 		//select * from noticia
 		List<Noticia> findBy();

 		//select * from noticia where fecha = ?
 		List<Noticia> findByFecha(Date fecha);

 		//where estatus = ? and fecha = ? and id = ?
		List<Noticia> findByEstatusAndFechaAndId(String estatus, Date fecha, int id);
		
		//where estatus= ? or fecha = ?
		List<Noticia> findByEstatusOrFecha(String estatus, Date fecha);

		//where fecha between ? and ?
		List<Noticia> findByFechaBetween(Date fecha1, Date fecha2);

	2.- Se llama al metodo creado pasandole el valor de busqueda.(metodo findBy+campo(varible))
		List<Noticia> lista = repo.findByEstatus("Activa");
		
		for (Noticia noticia : lista) {
			System.out.println(noticia);
		}	
	3.-Metodo findBy()
		List<Noticia> lista = repo.findByEstatus("Activa");
		
		for (Noticia noticia : lista) {
			System.out.println(noticia);
		}	

	4.-Metodo findByFecha(Date fecha)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Noticia> lista = repo.findByfecha(sdf.parse("2017-09-01"));
		
		for (Noticia noticia : lista) {
			System.out.println(noticia);
		}			

		Nota: la consulta no dio resultado, se tuvo que modificar el DataSource del root-context.com, modificanndo en
		la url de conexion la zona horari, a la de Argentina
			/*<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
				<property name="driverClassName" value="com.mysql.jdbc.Driver" />
				<property name="url" value="jdbc:mysql://localhost:3306/cineapp?serverTimezone=America/Argentina/Buenos_Aires" />
				<property name="username" value="root" />
				<property name="password" value="admin" />
			</bean>*/

	5.- Metodos para filtras por dos + atributos (and/or/between)
		5.1.- Se declara el metodo en la interfaz, Luego de la palabra reservada findBy se coloca el primer atributo de 
		busqueda seguida de la palabra reservada And seguida del segundo parametro.
			//where estado = ? and fecha = ?
			List<Noticia> findByEstatusAndFechaAndId(String estatus, Date fecha, int id);

		5.2.- Se llama al metodo creado(And)
			//List<Noticia> lista = repo.findByEstatus("Inactiva");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			List<Noticia> lista = null;
			try {
				lista = repo.findByEstatusAndFechaAndId("Activa", sdf.parse("2017-09-01"),2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (Noticia noticia : lista) {
				System.out.println(noticia);
			}

		5.3.- Se llama al metodo creado(Or)
			//List<Noticia> lista = repo.findByEstatus("Inactiva");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			List<Noticia> lista = null;
			try {
				lista = repo.findByEstatusOrFecha("Inactiva", sdf.parse("2017-09-01"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (Noticia noticia : lista) {
				System.out.println(noticia);
			}

		5.4.- Se llama al metodo creado(between)
			//List<Noticia> lista = repo.findByEstatus("Inactiva");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			List<Noticia> lista = null;
			try {
				lista = repo.findByFechaBetween(sdf.parse("2017-09-01"), sdf.parse("2017-09-06"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (Noticia noticia : lista) {
				System.out.println(noticia);
			}
Spring Data JPA Relaciones - Seccion 15*****************************************************************************************
********************************************************************************************************************************

	1.- Se configura clase Pelicula con @anotaciones JPA
		1.1.- Se agrega la anotacion @Entity a la clase
			@Entity
			public class Pelicula {
		1.2.- Se agrega l anotacion @Table, pasandole como parametro el nombre de la tabla de BDs
			@Entity
			@Table(name="Peliculas")
			public class Pelicula {
		1.3.- Se agrega @Anotacion @Id, para condigurar el atributo que sera la llave primaria, tambien se le
		agrega la @Anotacion @GeneratedValue, para configurar la manera como se va a generar
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)//Auto_increment MYSQL
			private int id;
	2.- Se crea repositorio para la clase Pelicula
		2.1.- Se crea Interface PeliculasRepository.java, se extiende de la inerface JpaRepository y se le añaden los parametros
		de entidad como Pelicula y el tipo de dato de la clave primaria Integer
			public interface PeliculasRepository extends JpaRepository<Pelicula, Integer> {

			}
		2.2.- Se agrega la anotacion @Repository
	3.- Se crea nuevo paquete de pr uebas
		3.1.- Se crea clase AppFindAll, con su metodo principal
			public static void main(String[] args) {
				
			}
		3.2.- Se le crea el atributo de contexto aplicationContext, pasandole el root-context.xml como parametro
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		
			context.close();
		3.3.- Se instancia la interfaz PeliculasRepository, pasandole el metodo getBean() del context y pasandole 
		dos atributos, el nombre del repositorio como cadena y la clase
			//Se crea la intancia a nuestro repositorio
			PeliculasRepository repo = context.getBean("PeliculasRepository", PeliculasRepository.class);
		3.4.- Se crea una lista de peliculas con el metodo findAll
			//Se crea lista de peliculas
			List<Pelicula> lista = repo.findAll();
			
			for (Pelicula pelicula : lista) {
				System.out.println(pelicula);
			}
		3.5.- Se utiliza la Anotacion @Trasient, para que el atributo detalle de la clase Pelicula, no se ha tomado
		en cuenta para la persistencia de datos
			@Transient//Se ignora el atributo durante la persistencia
			private Detalle detalle;
	4.- Se crea repositorio para la entidad Detalle
		4.1.- Se configura clase Detalle con @anotaciones JPA
			4.1.1.- Se agrega la anotacion @Entity a la clase
				@Entity
				public class Detalle {
			4.1.2.- Se agrega la anotacion @Table, pasandole como parametro el nombre de la tabla de BDs
				@Entity
				@Table(name="Detalles")
				public class Detalle {
			4.1.3.- Se agrega @Anotacion @Id, para condigurar el atributo que sera la llave primaria, tambien se le
			agrega la @Anotacion @GeneratedValue, para configurar la manera como se va a generar
				@Id
				@GeneratedValue(strategy=GenerationType.IDENTITY)
				private int id;
		4.2.- Se crea repositorio para la clase Detalle
			4.2.1.- Se crea Interface DetalleRepository.java, se extiende de la inerface JpaRepository y se le añaden los parametros
			de entidad como Detalle y el tipo de dato de la clave primaria Integer
				public interface DetalleRepository extends JpaRepository<Detalle, Integer> {

				}
			4.2.2.- Se agrega la Anotacion @Repository
		4.3.- Se crea nuevo paquete de pruebas pruebasRelaciones
			4.3.1.- Se crea clase AppRepoDetalle, con su metodo principal
			public static void main(String[] args) {
				
			}
			4.3.2.- Se le crea el atributo de contexto aplicationContext, pasandole el root-context.xml como parametro
				ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		
				context.close();
			4.3.3.- Se instancia la interfaz PeliculasRepository, pasandole el metodo getBean() del context y pasandole 
			dos atributos, el nombre del repositorio como cadena y la clase
				//Se crea la intancia a nuestro repositorio
				PeliculasRepository repo = context.getBean("detalleRepository", DetalleRepository.class);
			4.3.4.- Se crea una lista de peliculas con el metodo findAll
				//Se crea lista de peliculas
				List<Detalle> lista = repo.findAll();
			
				for (Detalle detalle: lista) {
					System.out.println(detalle);
				}



		5.- Se realizan las configuraciones para la relacion one-To-One de las entidades Pelicula y Detalle
			5.1.- Se comenta la anotacion @Trasient del atributo detalle en la clase PeliculasRepository
				//@Transient//Se ignora el atributo durante la persistencia
				private Detalle detalle;
			5.2.- Se le agrega la @Anotacion de relacion one-To-One y la @JoinColumn, con el parametro del 
			nombre de la columna en la bd
				//@Transient//Se ignora el atributo durante la persistencia
				@OneToOne
				@JoinColumn(name="idDetalle")
				private Detalle detalle;


		6.- Se realizan configuraciones de relacion uno a muchos, entre la tabla peliculas y la tabla horarios
			6.1.- Se configura clase Horario con @anotaciones JPA
				6.1.1.- Se agrega la anotacion @Entity a la clase
					@Entity
					public class Horario {
				6.1.2.- Se agrega la anotacion @Table, pasandole como parametro el nombre de la tabla de BDs
					@Entity
					@Table(name="Horarios")
					public class Horario {
				6.1.3.- Se agrega @Anotacion @Id, para condigurar el atributo que sera la llave primaria, tambien se le
				agrega la @Anotacion @GeneratedValue, para configurar la manera como se va a generar
					@Id
					@GeneratedValue(strategy=GenerationType.IDENTITY)
					private int id;
					private Date fecha;
					private String hora;
					private String sala;
					private double precio;

			6.2.- Se crea repositorio para la clase Horario

				6.2.1.- Se crea Interface HorariosRepository.java, se extiende de la inerface JpaRepository y se le 
				añaden los parametros de entidad como Horario y el tipo de dato de la clave primaria Integer
					public interface HorariosRepository extends JpaRepository<Horario, Integer> {

					}
				6.2.2.- Se agrega la @Anotacion @Repository
			6.3.- Se crea nuevo paquete de pruebas pruebasRelaciones
				6.3.1.- Se crea clase AppRepoHorarios, con su metodo principal
					public static void main(String[] args) {
				
					}
				6.3.2.- Se le crea el atributo de contexto aplicationContext, pasandole el root-context.xml como parametro
					ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		
					context.close();
				6.3.3.- Se instancia la interfaz HorariosRepository, pasandole el metodo getBean() del context y pasandole 
				dos atributos, el nombre del repositorio como cadena y la clase
					//Se crea la intancia a nuestro repositorio
					HorariosRepository repo = context.getBean("horariosRepository", HorariosRepository.class);
				6.3.4.- Se crea una lista de Horarios con el metodo findAll
					//Se crea lista de peliculas
					List<Horario> lista = repo.findAll();
			
					for (Horario horario: lista) {
						System.out.println(horario);
					}
			6.4. Se configura la relacion uno a muchos con el atributo pelicula de la clase Horario
				6.4.1.- Se comenta la anotacion @Transient, para el atributo pelicula de la clase Horario
				6.4.2.- Se agrega la anotacion @ManyToOne y la anotacion @JoinColumn
					//@Transient
					@ManyToOne
					@JoinColumn(name ="idPelicula")
					private Pelicula pelicula;
		7.- Se configura relacion muchos a uno entre la clase Peliculas y Horario
			7.1.- Se crea en la clase Pelicula atributo de tipo List<Horario> horarios, con su get y set	
				private List<Horario> horarios;
	
	
				public List<Horario> getHorarios() {
					return horarios;
				}

				public void setHorarios(List<Horario> horarios) {
					this.horarios = horarios;
				}

			7.2.- Se agrega anotacion @oneToMany, con los parametros del campo mapeado y la constante eager que 
			permite que cada vez que se consulte peliculas, se realizara una consulta a la tabla horarios que 
			pertenecen a la pelicula que se esta consultando.
				@OneToMany(mappedBy="pelicula", fetch =FetchType.EAGER)
				private List<Horario> horarios;
Spring Data JPA integracion con Spring MVC**************************************************************************************
***************************************************************Seccion 16 -*****************************************************
	a.-Configuracion del root-context en ClassPath de la aplicacion web, esta se encuentra en el directorio web-Inf, por lo que 
	lo colocamos alli, en esta carpeta por organizacion lo tenemos en el folder spring
	1.- Se crea interfaz PeliculasService, que implemnta IPeliculasService.java
		public class PeliculasServiceJPA implements IPeliculasService{
	2.- Se sobreescriben los metodoss de la interfaz implementada
	3.- Se agrega la anotacion @Service
		@Service
		public class PeliculasServiceJPA implements IPeliculasService{
	4.- Se declara en la clase de servicio PeliculasServiceJPA, de manera global un atributo del repositorio 
	PeliculasRepository.java.
		private PeliculasRepository peliculasRepo
	5.- Se le agrega la anotacion @Autowired, con esta se crea una instancia del aplicationcontext al
	arrancar la aplicacion
		@Autowired
		private PeliculasRepository peliculasRepo;

	6.- Se comenta la anotacion @Service de la clase PeliculaServiceImpl, Ya que no se pueden instancear dos implementaciones 
	de la interfaz IPeliculasService

	7.- Se implemnta el metodo insertar en la clase PeliculasServiceJPA.java, utilizando la instancia de PeliculasRepository
		@Override
		public void insertar(Pelicula pelicula) {
			peliculasRepo.save(pelicula);		
		}
	
	8.- Se crea interface de servicio para los objetos de tipo Detalle
		public interface IDetallesService {

		}
	9.- Se crea metodo insertar que recibe un obj de tipo Detalle 
		public interface IDetallesService {
			void insertar (Detalle detalle);
		}
	10.- Se crea clase de implementacion DetalleServiceJPA, imllementa la interface IDetallesService y se sobreescribe
	 su unico metodo
	 	public class DetalleServiceJPA implements IDetallesService{
	
			@Override
			public void insertar(Detalle detalle) {
				// TODO Auto-generated method stub
				
			}

		}
	11.- Se crea repositorio y se agrega la funcion save en el metodo y se agregaa la anotacion @Service
		@Service
		public class DetalleServiceJPA implements IDetallesService{	
			//Se crea atributo de repositorio
			@Autowired
			private DetalleRepository repo ;
			@Override
			public void insertar(Detalle detalle) {
				// TODO Auto-generated method stub
				repo.save(detalle);
			}
		}
	12.- Se agrega en el controlador PeliculasController una instancia a la interface IDetallesService y 
	se le agrega la anotacion @Autowired para la inyeccion de dependencias
		@Autowired//Para que se realice la inyeccion de dependencia
		private IDetallesService serviceDetalle;

	13.- Se ejecuta en el metodo insertar de la interface, en el metodo guardar de PeliculasController, 
	se le pasa el atributo detalle del obj pelicula, el cual esta cargado por el dataBinding
		serviceDetalle.insertar(pelicula.getDetalle()); 

	14.- Implementacion de metodo buscarTodas() de la implementacion del servicio PeliculasServiceJPA.java 
		@Override
		public List<Pelicula> buscarTodas() {
			List<Pelicula> lista = peliculasRepo.findAll();
			return lista;
		}
	15.- Implementacion de metodo buscarPorId() de la implementacion del servicio PeliculasServiceJPA.java 
		@Override
		public Pelicula buscarPorId(int idPelicula) {
			//Se utiliza el metodo findByAll, el cual devuelve un optional
			Optional<Pelicula> pelicula = peliculasRepo.findById(idPelicula);
			//Se retorna la pelicula en caso de que el optional tenga un valor presente
			if(pelicula.isPresent()) {
				return pelicula.get();
			}
			return null;
		}
	16.- Configuracion de boton editar en el catalogo de Peliculas
		16.1.- Se crea url con el tag de spring:url
			<spring:url value="/peliculas/edit" var="urlEdit" />
		16.2.- Se configura el atributo href del boton con la url creada y el id de la pelicula
			<a href="${urlEdit}/${pelicula.id}" class="btn btn-success btn-sm" role="button" title="Edit"><span class="glyphicon glyphicon-pencil"></span></a>
		16.3.- Crear metodo para que responda a la edicion en PeliculasController.java
			@GetMapping(value="/edit/{id}")
			public String editar(@PathVariable("id")int idPelicula, Model modelo) {
				Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
				//Se añade al model la lsta de generos
				modelo.addAttribute("generos", servicePeliculas.buscarGeneros());
				//Se añade añ model que necesita el formulario para renderizar los datos 
				modelo.addAttribute("pelicula",pelicula);
				
				return "peliculas/formPelicula";
			}
		16.4.- Se crea un hidden en el formulario de Peliculas formPelicula para que guarde el id del detalle de la pelicula
			<form:hidden path="id"/>
			
		**************************************************************************************@ModelAttribute
		16.5.- Se agrega metodo de manera global en PeliculasController, con anotacion @ModelAttribute, para que retorne la lista  de generos 
		para cualquier metodo del controlador.
			//Metodo creado para que agregue la lista de generos al modelo, para cualquier metodo 
			//Del controlador
			@ModelAttribute
			public List<String> getGeneros(){
				return servicePeliculas.buscarGeneros();
			}

	17.- Configuracion de boton eliminar en listPeliculas.jsp
		17.1.- Se declara metodo void eliminar() en la interface IPeliculasService.java, recibe de 
		parametro el id a eliminar
			void Eliminar(int idEliminar);
		17.2.- Se añade el metodo en las clases de servicio que implementan la interface, 
		PeliculaServiceImpl y PeliculasServiceJPA
		17.3.-  Se implementa el metodo eliminar en la clase de servicio PeliculasServiceJPA
			@Override
			public void Eliminar(int idEliminar) {
				peliculasRepo.deleteById(idEliminar);
			}
		17.4.- Se modifica la vista listPeliculas.jsp
			17.4.1.- Se cre tag de spring <spring-url> con la nueva ruta metodo get
				<spring:url value="/peliculas/delete" var="urlDelete"/>
			17.4.2.- Se modifica la propiedad href del boton eleminar
				<a href="${urlDelete}/${pelicula.id}" class="btn btn-danger btn-sm" role="button" title="Eliminar"><span class="glyphicon glyphicon-trash"></span></a>			
		17.5.- Se crea metodo en el controller 
			@GetMapping(value="/delete/{id}")
			public String eliminar(@PathVariable("id")int idEliminar) {
				servicePeliculas.Eliminar(idEliminar);
				
				return "redirect:/peliculas/index";
			}
		17.5.- mensaje informativo al cliente
			@GetMapping(value="/delete/{id}")
			public String eliminar(@PathVariable("id")int idEliminar, RedirectAttributes atributo) {
				servicePeliculas.Eliminar(idEliminar);
				//se añade un objeto de tipo RedirectAttributes para enviar mensaje en un redirect
				atributo.addFlashAttribute("mensaje", "La pelicula fue eliminada.");
				return "redirect:/peliculas/index";
			}
		17.6.- Borrar detalle de la Pelicula
			17.6.1.- Se crea firma en la interface IDetallesService
				void eliminar (int idDetalle);
			17.6.2.- Se implementa el metodo en la clase de servicio
				@Override
				public void eliminar(int idDetalle) {
					repo.deleteById(idDetalle);		
				}
			17.6.3.- En el metodo eliminar del controlador, se recupera el objeto de tipo pelicula por el id
				//Declaramos un objeto de tipo pelicula
				Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
			17.6.4.- Se ejecuta el metodo eliminar de la interface IDetallesService
				//Se eliminan los detalles de la pelicula
				serviceDetalles.eliminar(pelicula.getDetalle().getId());
		17.7.- Se crea un dialogo de confirmacion utilizando javaScript
			<a href="${urlDelete}/${pelicula.id}" onClick='return confirm("¿Desea Eliminar?")' class="btn btn-danger btn-sm" role="button" title="Eliminar"><span class="glyphicon glyphicon-trash"></span></a>
	18.- Paginacion de la lista de Peliculas
		18.1.- Se crea la firma en la interface IPeliculasService
			Page<Pelicula> buscarTodas(Pageable page);
		18.2.- Se agrega el metodo buscarTodas() a la clase de servicio, PeliculasServiceJPA, y se crea metodo en el controlador
			18.2.1.- Se crea metodo buscarTodas()
				@Override
				public Page<Pelicula> buscarTodas(Pageable page) {
					return peliculasRepo.findAll(page);
				}
			18.2.2.- Se crea metodo en el controlador
				@GetMapping(value ="indexPagina")
				public String indicePaginado(Model modelo, Pageable pagina) {
					Page<Pelicula> lista = servicePeliculas.buscarTodas(pagina);
					modelo.addAttribute("peliculas", lista);
					return "peliculas/listPeliculas";
				} 
		18.3.- Se agrega link de paginacion luego de la tabla en listPeliculas
			<nav aria-label="">
				<ul class="pager">
					<li><a href="${urlPeliculas}/indexPagina?page=${peliculas.number - 1 }">Anterior</a></li>
					<li><a href="${urlPeliculas}/indexPagina?page=${peliculas.number + 1 }">Siguiente</a></li>
				</ul>
			</nav>
		18.4.- Recorrido del objeto tipo Page con JSTL, accediendo al metodo content del objeto Page<Pelicula>
			JSP (View) –Recorrido del objeto tipo Page con JSTL.
			Debido a que ahora el atributo peliculas, es de tipo Page<Pelicula> para acceder a la colección de 
			objetos de tipo película, en el atributo items del <c:forEach>debemos mandar llamar el método "content" 
			de nuestro objeto de tipo Page<Pelicula>
			<c:forEach var="pelicula" items="${peliculas.content}">
		18.5.- Configuramos la integración de paginación por default (dispatcher-servlet.xml)
			<mvc:annotation-driven >
				<mvc:argument-resolvers>
					<bean class="org.springframework.data.web.PageableHandlerMethodArgumentResolver">
						<property name="maxPageSize" value="5"></property>
					</bean>
				</mvc:argument-resolvers>
			</mvc:annotation-driven>
		18.6.- Se actualiza el link del menú (includes/menu.jsp)para acceder al listado de películas. 
		Ahora en el link deberías pasar el parámetro page con valor cero (?page=0)para que se muestre la 
		primera página por default. Ejemplo:
			<li><a href="${urlRoot}peliculas/indexPagina?page=0">Peliculas</a></li>
		18.7.- Se modifica los metodos que redireccionan a listPeliculas.jsp, para que sean atendidos por el metodo
		indicePaginado(), el cual utiliza el objeto de paginacion que necesita listPeliculas.jsp
			18.7.1.- Se cambia el redirect del metodo eliminar(), en PeliculasController
				return "redirect:/peliculas/indexPagina";
			18.7.2.- Se cambia el redirect del metodo guardar(), en PeliculasController
				return "redirect:/peliculas/indexPagina";
	19.- Desplegar Horarios por pelicula y fecha
		19.1.- Se crea una interface IHorariosService.java
			19.1.1 Se crea la firma del metodo buscarPorIdPelicula()
				public interface IHorariosService {
					List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha);
				}
		19.2.- Se crea la clase de servicio HorariosServiceJPA.java
			19.2.1.- Se crea un atributo de nuestro repositorio HorariosRepository, se agrega la anotacion @Autowired
			para la injeccion de independencia
				@Autowired
				private HorariosRepository horariosRepo;
			19.2.2.- Se agrega a la clase de servicio la anotacion @Service
				@Service
				public class HorariosServiceJPA implements IHorariosService {
			19.2.3.- Se agrega al repositorio HorariosRepository.java un metodo para busqueda por idPelicula y por fecha, 
			findByPelicula_IdAndFechaOrderByHora
				public List<Horario> findByPelicula_IdAndFechaOrderByHora(int idPelicula, Date fecha);
			19.2.4.- Se implementa el metodo buscarPorIdPelicula(), el cual retorna una lista de Horario, que 
			recupera lista de el metodo findByPelicula_IdAndFechaOrderByHora()
				public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha) {
					return horariosRepo.findByPelicula_IdAndFechaOrderByHora(idPelicula, fecha);
				}
		19.3.- Se modifica el metodo mostrarDetalle() de la clase HomeController.java
			19.3.1.- Se la clase de control HomeController.java se crea la inyeccion al la clase de servicio 
				@Autowired
				private IHorariosService serviceHorarios;
			19.3.2.- Se crea una lista de obj Horario en el metodo mostrarDetalle()	
				List<Horario> horarios = serviceHorarios.buscarPorIdPelicula(idPelicula, fecha);
			19.3.3.- Se modifica los parametros recibe el metodo, para que la fecha la reciba en un tipo Date
				public String mostrarDetalle(Model model,@PathVariable("id") int idPelicula, @PathVariable("fecha") Date fecha) {
			19.3.4.- Se presonalizan  el databinding para los tipos de dato fecha, en el HomeController.java
				@InitBinder
				public void initBinder(WebDataBinder binder) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
				}
			19.3.5.- Se agrega al modelo la lista de horarios recuperada
				model.addAttribute("horarios", horarios);
			19.3.6.- Agregamos al modelo la fecha seleccionada por el cliente
				model.addAttribute("fechaBusqueda", dateFormat.format(fecha));
		19.4.- Se modifica la vista detalle.jsp
			19.4.1.- Se modifica la fecha de busqueda en la vista con atributo fechaBusqueda
				<h3 class="panel-title"><span class="label label-success">${fechaBusqueda}</span></h3>
			19.4.2.- Se renderiza la lista de horarios utilizando un forEach
				<tbody>  
					<c:forEach items="${horarios}" var="horario">
						<tr>                 
							<td>${horario.hora}</td>
							<td>${horario.sala}</td>  
							<td>$${horario.precio}</td>  
						</tr>  
					</c:forEach> 					       
				</tbody> 
	20.- Se configuran las paginas de error 400,404 y 500
		20.1.- Se modifica el archivo web.xml
			<!-- Configuracion de la pagina de error default -->
			<error-page>
		   		<error-code>404</error-code>
		   		<location>/WEB-INF/views/error/404.jsp</location>
			</error-page>
			<error-page>
		   		<error-code>400</error-code>
		   		<location>/WEB-INF/views/error/400.jsp</location>
			</error-page>
			<error-page>
		   		<error-code>500</error-code>
		   		<location>/WEB-INF/views/error/500.jsp</location>
			</error-page>	
		20.2.- Se crean en el folder views, un folder error, en este se crean las vistas 400.jsp, 404.jsp, 500.jsp
	21.- Configuracion de boton editar en el catalogo de banners
		21.1.- Se crea interface BannersRepository.java que extienda de JpaRepository.java
			public interface BannersRepository extends JpaRepository<Banner, Integer> {

			}
		21.2.- Se crea clase de servicio BannerServiceJPA.java la cual implementa IBannerService
			21.2.1.- Se agrega anotacion @Service y se comenta en BannerServiceImp.java
				@Service
				public class BannerServiceJPA implements IBannersService{
			21.2.2.- Se crea inyeccion de dependencia al atributo de nuestro repositorio
				@Autowired
				private BannersRepository bannersRepo;
			21.2.3.- Se modifican los metodos sobreescritos de la interface, para que implementen el repositorio JPA 
				@Override
				public void insertar(Banner banner) {
					bannersRepo.save(banner);
				}

				@Override
				public List<Banner> buscarTodos() {
					return bannersRepo.findAll();
				}
		21.3.- Se implementan metodos para buscar por id
			21.3.1.- Se crea repositorio BannersRepository.java
				public interface BannersRepository extends JpaRepository<Banner, Integer> {

				}
			21.3.2.- Se inyecta dependencia del repositorio en la clase de servicio BannerServiceJPA.java
				@Autowired
				private BannersRepository bannersRepo;
			21.3.3.- Se crea firma del metodo buscarPorId() en la interface IBannersService.java
				Banner buscarPorId(int idBanner);
			21.3.4.- Se crea la implementacion en la clase de servicio
				public Banner buscarPorId(int idBanner) {
					Optional<Banner> banner = bannersRepo.findById(idBanner);
					if(banner.isPresent()) {
						return banner.get();
					}
					return null;
				}
		21.4.- Se crea metodo en BannersController.java, para que responda a la edicion en el formulario formBanner.jsp
			@GetMapping(value="/edit/{id}")
			public String editarBanners(@PathVariable("id") int idBanner, Model modelo) {
				Banner banner = serviceBanners.buscarPorId(idBanner);
				modelo.addAttribute("banner", banner);
				return "banners/formBanner";
			}
		21.4.- Se realizan modificaciones a la vista
			21.4.1.- Se crea url con el tag de spring:url, en la vista
				<spring:url value="/banners/edit" var="urlEdit" />
			21.4.2.- Se configura el atributo href del boton con la url creada y el id de la pelicula
				<a href="${urlEdit}${banner.id}" class="btn btn-success btn-sm" role="button" title="Edit" ><span class="glyphicon glyphicon-pencil"></span></a>
			21.4.3.- Se agrega campo hidden dentro del formulario id, el metodo save valida el id si este esta vacio
			crea un nuevo banner, si hay id, por medio de este lo modifica.
				<form:form action="${urlForm}" method="post" enctype="multipart/form-data" modelAttribute="banner">
         			<form:hidden path="id"/>
    22.- Configuracion de boton eliminar en el catalogo de banners
    	22.1.- Se declara metodo void eliminar() en la interface IBannersService.java, recibe de 
		parametro el id a eliminar
			void Eliminar(int idEliminar);
		22.2.- Se añade el metodo en las clases de servicio que implementan la interface, 
			PeliculaServiceImpl y PeliculasServiceJPA
		22.3.-  Se implementa el metodo eliminar en la clase de servicio BannersServiceJPA
			@Override
			public void eliminar(int idEliminar) {
				bannersRepo.deleteById(idEliminar);
			}
		22.4.- Se modifica la vista listBanners.jsp
			22.4.1.- Se cre tag de spring <spring-url> con la nueva ruta metodo get
				<spring:url value="/Banners/delete" var="urlDelete" />
			22.4.2.- Se modifica la propiedad href del boton eleminar
				<a href="${urlDelete}/${banner.id}" class="btn btn-danger btn-sm" role="button" title="Eliminar"><span class="glyphicon glyphicon-trash"></span></a>			
		22.5.- Se crea metodo en el controller 
			@GetMapping(value="/delete/{id}")
			public String eliminar(@PathVariable("id")int idEliminar) {
				serviceBanners.Eliminar(idEliminar);				
				return "redirect:/peliculas/index";
			}
		22.6.- Se agrega mensaje informativo al cliente
			@GetMapping(value="/delete/{id}")
			public String eliminar(@PathVariable("id")int idEliminar, RedirectAttributes atributo) {
				serviceBanners.Eliminar(idEliminar);
				//se añade un objeto de tipo RedirectAttributes para enviar mensaje en un redirect
				atributo.addFlashAttribute("mensaje", "El banner fue eliminado.");
				return "redirect:/peliculas/index";
			}
		22.7.- Se crea un dialogo de confirmacion utilizando javaScript
			<a href="${urlDelete}/${pelicula.id}" onClick='return confirm("¿Desea Eliminar?")' class="btn btn-danger btn-sm" role="button" title="Eliminar"><span class="glyphicon glyphicon-trash"></span></a>

	23.- Paginacion de la lista de Banners
		23.1.- Se crea la firma en la interface IBannersService
			Page<Banner> buscarTodas(Pageable page);
		23.2.- Se agrega el metodo buscarTodas() a la clase de servicio, BannersServiceJPA, y se crea metodo en el controlador
			23.2.1.- Se crea metodo buscarTodas()
				@Override
				public Page<Banner> buscarTodas(Pageable pagina) {
					return bannersRepo.findAll(pagina);
				}
			23.2.2.- Se crea metodo en el controlador
				@GetMapping(value ="indexPagina")
				public String indicePaginado(Model modelo, Pageable pagina) {
					Page<Banner> lista = serviceBanners.buscarTodas(pagina);
					modelo.addAttribute("Banners", lista);
					return "banners/listBanners";
				} 	
		23.3.- Se agrega link de paginacion luego de la tabla en listBanners.jsp
			<nav aria-label="">
				<ul class="pager">
					<li><a href="${urlBanners}/indexPagina?page=${banner.number - 1 }">Anterior</a></li>
					<li><a href="${urlBanners}/indexPagina?page=${banner.number + 1 }">Siguiente</a></li>
				</ul>
			</nav>
		23.4.- Recorrido del objeto tipo Page con JSTL, accediendo al metodo content del objeto Page<Pelicula>
			JSP (View) –Recorrido del objeto tipo Page con JSTL.
			Debido a que ahora el atributo peliculas, es de tipo Page<Pelicula>para acceder a la colección de 
			objetos de tipo película, en el atributo items del <c:forEach>debemos mandar llamar el método "content" 
			de nuestro objeto de tipo Page<Pelicula>
			<c:forEach var="banner" items="${Banners.content}">
		23.5.- Configuramos la integración de paginación por default (dispatcher-servlet.xml)(Se realiza una unica vez)
			<mvc:annotation-driven >
				<mvc:argument-resolvers>
					<bean class="org.springframework.data.web.PageableHandlerMethodArgumentResolver">
						<property name="maxPageSize" value="5"></property>
					</bean>
				</mvc:argument-resolvers>
			</mvc:annotation-driven>
		23.6.- Se actualiza el link del menú (includes/menu.jsp)para acceder al listado de películas. 
				Ahora en el link deberías pasar el parámetro page con valor cero (?page=0)para que se muestre la 
				primera página por default. Ejemplo:
					<li><a href="${urlRoot}banners/indexPagina?page=0">Banners</a></li>
				18.7.- Se modifica los metodos que redireccionan a listBanners.jsp, para que sean atendidos por el metodo
				indicePaginado(), el cual utiliza el objeto de paginacion que necesita listPeliculas.jsp
	24.- Creacion de catalogo para Noticias
		24.1.- Se copia la plantilla de listNoticias.jsp
		24.2.- Se modifica menu.jsp, con vinculo hacia listNoticias.jsp
			<li><a href="${urlRoot}noticias/index">Noticias</a></li> 
		24.3.- Se crea metodo para obtener la lista de noticias
			24.3.1 Se crea firma del metodo buscarTodas()
				List<Noticia> buscarTodas();
			24.3.2.- Se crea clase de servicio NotociasServiceJPA.java
				24.3.2.1.- Se implementa la interface INoticiasService.java
					@Service
					public class NoticiasServiceJPA implements INoticiasService{
				24.3.2.2.- Se crea intancia de un objeto repositorio por inyeccion de dependecias
					@Autowired
					private NoticiasRepository noticiasRepo;
				24.3.2.3.- Se implementan los metodos declarados en la interface
					@Override
					public void guardar(Noticia noticia) {
						noticiasRepo.save(noticia);		
					}

					@Override
					public List<Noticia> buscarTodas() {
						return noticiasRepo.findAll();
					}
			24.3.3.- Se crea metodos en el controlador para que recupere la lista y muestre la vista
				@GetMapping(value="/index")
				public String mostrarIndex(Model modelo) {
					List<Noticia> listaNoticias = serviceNoticias.buscarTodas();	
					modelo.addAttribute("noticias", listaNoticias);
					return "noticias/listNoticias";
				}
		24.4.- Se configura la paginacion de la lista
			24.4.1.- Se crea la firma en la interface IBannersService
				Page<Noticia> buscarTodas(Pageable pagina);
			24.4.2.- Se implementa en la clase de servicio NoticiasServiceJPA.java el metodo creado en la interface
				@Override
				public Page<Noticia> buscarTodas(Pageable pagina) {
					return noticiasRepo.findAll(pagina);
				}
			24.4.2.- Se crea metodo en el controlador
				@GetMapping(value="/indicePaginado")
				public String indicePaginado(Model modelo, Pageable pagina) {
					Page<Noticia> listaNoticias = serviceNoticias.buscarTodas(pagina);
					modelo.addAttribute("noticias", listaNoticias);
					return "noticias/listNoticias";
				}
			24.4.3.-  Se agrega link de paginacion luego de la tabla en listNoticias.jsp
				<nav aria-label="">
					<ul class="pager">
						<li><a href="${urlNoticias}/indexPaginado?page=${noticias.number - 1 }">Anterior</a></li>
						<li><a href="${urlNoticias}/indexPaginado?page=${noticias.number + 1 }">Siguiente</a></li>
					</ul>
				</nav>
			24.4.4.- Recorrido del objeto tipo Page con JSTL, accediendo al metodo content del objeto Page<Pelicula>
			JSP (View) –Recorrido del objeto tipo Page con JSTL.
			Debido a que ahora el atributo peliculas, es de tipo Page<Pelicula>para acceder a la colección de 
			objetos de tipo película, en el atributo items del <c:forEach>debemos mandar llamar el método "content" 
			de nuestro objeto de tipo Page<Pelicula>
				<c:forEach var="noticia" items="${noticias.content}">
			24.4.5.- Se actualiza el link del menú (includes/menu.jsp)para acceder al listado de películas. 
			Ahora en el link deberías pasar el parámetro page con valor cero (?page=0)para que se muestre la 
			primera página por default. Ejemplo:
				<li><a href="${urlRoot}noticias/indexPagina?page=0">Noticia</a></li>
			24.4.6.- Se modifica los metodos que redireccionan a listBanners.jsp, para que sean atendidos por el metodo
			indicePaginado(), el cual utiliza el objeto de paginacion que necesita listPeliculas.jsp
		24.5.- Configuracion de boton editar en el catalogo de Noticias
			24.5.1.- Creacion de metodo para buscar por id
				24.5.1.1.- Se crea la firma en la interface INoticiasService.java
					Noticia buscarPorId(int idNoticia);
				24.5.1.2.- Se implementa el metodo en la clase de servicio
					@Override
					public Noticia buscarPorId(int idNoticia) {
						Optional<Noticia> noti = noticiasRepo.findById(idNoticia);
						
						if(noti.isPresent()) {
							return noti.get();
						}
						
						return null;
					}
			24.5.2.- Creacion de metodo para la edicion de una pelicula en el NoticiasController.java
				@GetMapping(value="/edit/{id}")
				public String editNoticia(@PathVariable("id") int idNoticia, Model modelo) {
					Noticia noticia = serviceNoticias.buscarPorId(idNoticia);
					modelo.addAttribute("noticia", noticia);
					return "noticias/formNoticia";
				}
			24.5.3.- Configuracion de la vista
				24.5.3.1.- Se crea url con el tag de spring:url, en la vista
					<spring:url value="/noticias/edit" var="urlEdit" />
				24.4.2.- Se configura el atributo href del boton con la url creada y el id de la pelicula
					<a href="${urlEdit}${noticia.id}" class="btn btn-success btn-sm" role="button" title="Edit" ><span class="glyphicon glyphicon-pencil"></span></a>
				21.4.3.- Se agrega campo hidden dentro del formulario id, el metodo save valida el id si este esta vacio
				crea una nueva pelicula, si hay id, por medio de este lo modifica.
					<form:form action="${urlForm}" method="post"  modelAttribute="noticia">
	         			<form:hidden path="id"/>
	         	21.4.4.- Se agraga la configuracion de tags de spring, y se realizan las modificaciones de los imput y 
	         	el path por el name de cada uno    		
					<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
		24.6.- Configuracion de boton delete en el catalogo de Noticias
			24.6.1.- Creacion de metodo deleteNoticia() 
				24.6.1.1.- Se crea la firma del metodo en la interface
					void deleteNoticia(int idNoticia);
				24.6.1.2.- Se implementa el metodo en la clase de servicio
					@Override
					public void deleteNoticia(int idNoticia) {
						noticiasRepo.deleteById(idNoticia);
					}
			24.6.2.- Creacion en el controlado, de metodo para borrar la pelicula 
				@GetMapping(value="/delete/{id}")	
				public String eliminarNoticia(@PathVariable("id") int idNoticia, RedirectAttributes atributo) {
					serviceNoticias.deleteNoticia(idNoticia);
					atributo.addFlashAttribute("msg", "La Noticia fue eliminada.");
					return "redirect:/noticias/indicePaginado";
				}
	25.- Creacion de catalogo para Horario
		25.1.- Se copia la plantilla de listHorarios.jsp
		25.2.- Se modifica menu.jsp, con vinculo hacia listHorarios.jsp
			<li><a href="${urlRoot}horarios/index">Horarios</a></li> 
		25.3.- Se crea metodo para obtener la lista de horarios
			25.3.1 Se crea firma del metodo buscarTodas() y guardar(), en IHorariosService.java
				List<Horario> bucarTodos();
				void guardar(Horario horario);
			25.3.2.- Se implementan los metodos declarados en la interface
				@Override
				public void guardar(Horario horario) {
					horariosRepo.save(horario);		
				}

				@Override
				public List<Horario> buscarTodas() {
					return horariosRepo.findAll();
				}
			25.3.3.- Se crea metodos en el controlador para que recuperar la lista y muestre la vista
				@GetMapping(value="/indice")
				public String index(Model modelo) {
					List<Horario> listaHorario = serviceHorarios.bucarTodos();
					modelo.addAttribute("horarios", listaHorario);
					return "horarios/listHorarios";
				}

		25.4.- Se configura la paginacion de la lista
			25.4.1.- Se crea la firma en la interface IBannersService
				Page<Noticia> buscarTodas(Pageable pagina);
			25.4.2.- Se implementa en la clase de servicio NoticiasServiceJPA.java el metodo creado en la interface
				@Override
				public Page<Noticia> buscarTodas(Pageable pagina) {
					return noticiasRepo.findAll(pagina);
				}
			25.4.2.- Se crea metodo en el controlador
				@GetMapping(value="/indicePaginado")
				public String indicePaginado(Model modelo, Pageable pagina) {
					Page<Noticia> listaNoticias = serviceNoticias.buscarTodas(pagina);
					modelo.addAttribute("noticias", listaNoticias);
					return "noticias/listNoticias";
				}
			25.4.3.-  Se agrega link de paginacion luego de la tabla en listNoticias.jsp
				<nav aria-label="">
					<ul class="pager">
						<li><a href="${urlNoticias}/indexPaginado?page=${noticias.number - 1 }">Anterior</a></li>
						<li><a href="${urlNoticias}/indexPaginado?page=${noticias.number + 1 }">Siguiente</a></li>
					</ul>
				</nav>
			25.4.4.- Recorrido del objeto tipo Page con JSTL, accediendo al metodo content del objeto Page<Pelicula>
			JSP (View) –Recorrido del objeto tipo Page con JSTL.
			Debido a que ahora el atributo peliculas, es de tipo Page<Pelicula>para acceder a la colección de 
			objetos de tipo película, en el atributo items del <c:forEach>debemos mandar llamar el método "content" 
			de nuestro objeto de tipo Page<Pelicula>
				<c:forEach var="noticia" items="${noticias.content}">
			25.4.5.- Se actualiza el link del menú (includes/menu.jsp)para acceder al listado de películas. 
			Ahora en el link deberías pasar el parámetro page con valor cero (?page=0)para que se muestre la 
			primera página por default. Ejemplo:
				<li><a href="${urlRoot}banners/indexPagina?page=0">Banners</a></li>
			25.4.6.- Se modifica los metodos que redireccionan a listBanners.jsp, para que sean atendidos por el metodo
			indicePaginado(), el cual utiliza el objeto de paginacion que necesita listPeliculas.jsp
		25.5.- Configuracion de boton editar en el catalogo de Horario
			25.5.1.- Creacion de metodo para buscar por id
				25.5.1.1.- Se crea la firma en la interface IHorariosService.java
					Noticia buscarPorId(int idHorario);
				25.5.1.2.- Se implementa el metodo en la clase de servicio
					@Override
					public Horario buscarPorId(int idHorario) {
						Optional<Horario> horario = horariosRepo.findById(idHorario);
						
						if(horario.isPresent()) {
							return horario.get();
						}
						
						return null;
					}
			25.5.2.- Creacion de metodo para la edicion de un Horario en el HorariosController.java
				@GetMapping(value="/edit/{id}")
				public String editHorari(@PathVariable("id") int idHorario, Model modelo) {
					Horario horario = serviceHorarios.buscarPorId(idHorario);
					modelo.addAttribute("horario", horario);
					return "horarios/formHorario";
				}
			25.5.3.- Configuracion de la vista
				25.5.3.1.- Se crea url con el tag de spring:url, en la vista
					<spring:url value="/horarios/edit" var="urlEdit" />
				25.4.2.- Se configura el atributo href del boton con la url creada y el id de la pelicula
					<a href="${urlEdit}${horario.id}" class="btn btn-success btn-sm" role="button" title="Edit" ><span class="glyphicon glyphicon-pencil"></span></a>
				25.4.3.- Se agrega campo hidden dentro del formulario id, el metodo save valida el id si este esta vacio
				crea una nueva pelicula, si hay id, por medio de este lo modifica.
					<form:form action="${urlForm}" method="post"  modelAttribute="horario">
	         			<form:hidden path="id"/>
	         	25.4.4.- Se agraga la configuracion de tags de spring, y se realizan las modificaciones de los imput y 
	         	el path por el name de cada uno    		
					<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
		24.6.- Configuracion de boton delete en el catalogo de Horario
			24.6.1.- Creacion de metodo deleteHorario() 
				24.6.1.1.- Se crea la firma del metodo en la interface
					void deleteHorario(int idHorario);
				24.6.1.2.- Se implementa el metodo en la clase de servicio
					@Override
					public void deleteHorario(int idHorario) {
						horariosRepo.deleteById(idHorario);						
					}
			24.6.2.- Creacion en el controlado, de metodo para borrar la pelicula 
				@GetMapping(value="/delete/{id}")	
				public String eliminarHorario(@PathVariable("id") int idHorario, RedirectAttributes atributo) {
					serviceHorarios.deleteHorario(idHorario);
					atributo.addFlashAttribute("msg", "El horario fue eliminado.");
					return "redirect:/horarios/indicePaginado";
				}
*****************************************************************************************************************Spring Security
********************************************************************************************************************************
********************************************************************************************************************************
	1.- Se crean las dependencias en el POM.xml
		<!-- SPRING DATA SECURITY -->
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-web</artifactId>
			<version>5.3.0.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-config</artifactId>
			<version>5.3.0.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-taglibs</artifactId>
			<version>5.3.0.RELEASE</version>
		</dependency>		
	2.- Configuracion de Spring Security filter en el archivo web.xml
		<!--Spring Security Filter -->
		<filter>
			<filter-name>springSecurityFilterChain</filter-name>
			<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
		</filter>
		<filter-mapping>
			<filter-name>springSecurityFilterChain</filter-name>
		<url-pattern>/*</url-pattern>
		</filter-mapping>/**/
	3.- Configuracion del path del archivo de SpringSecurity en el context del archivo web.xlm
		este archivo contiene configuraciones de seguridad de ususarios, roles, recursos protegidos, ruta de formulario login
		<context-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/root-context.xml,/WEB-INF/spring/security.xml
			</param-value>
		</context-param>
	4.- Se copia el archivo security.xml de configuracion en el path configurado
		Nota:
		Con esta configuración:
			Será requerida autenticación para todas las URLs.
			Spring generará un formulario HTML de login de forma automática.
			Se crearán 2 usuarios en memoria con los roles especificados.
			Se agregará CSRF attack prevention (Cross-site request forgery).	
		<?xml version="1.0" encoding="UTF-8"?>

		<b:beans xmlns="http://www.springframework.org/schema/security"
		   xmlns:b="http://www.springframework.org/schema/beans" 
		   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		   xsi:schemaLocation="http://www.springframework.org/schema/beans 
		     http://www.springframework.org/schema/beans/spring-beans.xsd
		     http://www.springframework.org/schema/security 
		     http://www.springframework.org/schema/security/spring-security.xsd">

		   <http />

		   <authentication-manager>

		      <authentication-provider>
				<user-service>
				  <user name="luis" password="{noop}luis123" authorities="EDITOR" />		  
				  <user name="marisol" password="{noop}mari123" authorities="GERENTE" />  
				</user-service>
		      </authentication-provider>

		   </authentication-manager>

		</b:beans>


			
	5.- Se declaran todos los recursos que estaran compartidos en el archivo security.xml
		Los tags <intercept-url> son declarados dentro del tag <http>y son utilizados para definir 
		conjuntos de URLs que estarán protegidas en la aplicación. Los atributos más usados son:
			pattern: sirve para indicar un patrón de URLs. Ejemplo:
				/peliculas/* /*Todas las URLs que comiencen con /peliculas/( /peliculas/index, /peliculas/create, 
				/peliculas/save, etc. )*/
			access: especificar atributos de acceso: Ejemplo:
				access=“hasAnyAuthority('EDITOR')”: Permitir el acceso ÚNICAMENTEa usuarios con el ROL EDITOR.
		

		<http auto-config="true">
			<!--Declaramos todos los recursos que estaran protegidos -->
			<intercept-url pattern="/peliculas/*"
				access="hasAnyAuthority('EDITOR')" />
			<intercept-url pattern="/horarios/*"
				access="hasAnyAuthority('EDITOR')" />
			<intercept-url pattern="/noticias/*"
				access="hasAnyAuthority('EDITOR')" />
			<intercept-url pattern="/banners/*"
				access="hasAnyAuthority('GERENTE')" />
		</http>
		5.1.- Se configura el rol GERENTE para que pueda acceder a todos los recursos
			<http auto-config="true">
				<!--Declaramos todos los recursos que estaran protegidos -->
				<intercept-url pattern="/peliculas/*"
					access="hasAnyAuthority('EDITOR', 'GERENTE')" />
				<intercept-url pattern="/horarios/*"
					access="hasAnyAuthority('EDITOR', 'GERENTE')" />
				<intercept-url pattern="/noticias/*"
					access="hasAnyAuthority('EDITOR', 'GERENTE')" />
				<intercept-url pattern="/banners/*"
					access="hasAnyAuthority('GERENTE')" />
			</http>
	6.- Se crea funcionalidad de logout (Cerrar una session)
		6.1.- Se crea controlador
			@Controller
			public class LoginControler {}
		6.2.- Se crea metodo para finalizar Session
			@GetMapping(value="/logout")
			public String logout(HttpServletRequest request){
				SecurityContextLogoutHandler logoutHandler =	new SecurityContextLogoutHandler();
				logoutHandler.logout(request, null, null);
				return "redirect:/login";
			}
**********************************************************************************************************************Seccion 18
*************************************************************************************************Creacion de formulario de login
	1.-Se agrega plantilla de formulario de login
	2.- Se modifican los recursos estaticos
		2.1.-Se agregan los tag de librerias
			<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
			<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
			<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		2.2.-Se agregan los tag de spring url
			<spring:url value="/resources" var="urlPublic"></spring:url>
			<spring:url value="/" var="urlRoot" />
		2.3.- Se agregan los tag a los recursos estaticos
		2.4.- Se incluye el menu
			<!-- Fixed navbar -->
			<jsp:include page="includes/menu.jsp" />
		2.5.- Se modifica el formulario, y se le agrega el mensaje de error

			La configuración de color AZULes obligatoria como está (NO ES OPCIONAL).
			•action='/login':URL del atributo action del formulario por default.
			•method='POST':Por seguridad, nunca poner GET.
			•name='username':El nombre del input tiene que ser usernameporque así lo espera Spring Security.
			•name='password':El nombre del input tiene que ser passwordporque así lo espera Spring Security.
			•type='password':Para que no sea visible el password al ingresarse.
			•type='hidden':Input de tipo hidden para incluir el CSRF Token para prevenir los ataques de tipo Cross Site Request Forgery (el valor del atributo name y value es generado por Spring Security). El CSRF Token es necesario en peticiones tipo POST, DELETE, PUT y PATCH.
			•type="submit":El botón tiene que ser tipo SUBMIT. No dejarlo por ejemplo type="button".

			<form class="form-signin" action="${urlRoot}login" method="post"> 
				<c:if test="${param.error != null}">
					<img src="${urlPublic}/images/error.png" width="48" height="48" class="center">
					<h4 class="form-signin-heading" style="color:red">Acceso denegado</h4>
				</c:if>       
				<h3 class="form-signin-heading">CineSite | Administracion</h3>        
				<label for="username" class="sr-only">Usuario</label>
				<input type="text" id="username" name="username" class="form-control" placeholder="Usuario" required autofocus>
				<label for="password" class="sr-only">Contraseña</label>
				<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
				<input type="hidden"name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
			</form>

			Nota:Por default cuando es proporcionado un usuario/contraseña incorrectos Spring
				Security realiza un REDIRECCIONAMIENTO a la URL del formulario de login y le
				pasa un parámetro llamado error. Ejemplo:
				http://localhost:8080/cineapp/formLogin?error
				 Este parámetro puede ser usado en la vista del formulario de login para mostrar una
				notificación al usuario.

	3.- Se Configura formulario de login personalizado.
		3.1.- Se agrega en el archivo security.xml, dentro del tag http, la ruta mapeada del formulario personalizado
			<!-- Custom login form -->
			<form-login login-page="/formLogin" />
		3.2.- Se agrega metodo en el controlador HomeController.java
			@RequestMapping(value="/formLogin", method=RequestMethod.GET)
			public String mostrarLogin() {
				return "formLogin";
			}
**************************************************************************Rederizar menu dinamico dependiendo del rol de usuario
	
	1.- Se agrega tag de springSecurity al archivo menu.jsp***************************************************************
		<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

		Spring Security tiene sus propios Taglibs que pueden ser usados en los archivos JSP para proporcionar 
		soporte básico para aplicar seguridad en la vista.
			Para usar cualquiera de los Tags,debes incluir la siguiente declaración en los archivos JSP:
				<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

	2.- Se agrega tag authorize en el archivo menu.jsp.

		El tag authorize
		Este tag es utilizado para determinar si parte de la vista será renderizada o no. 
		Un ejemplo clásico es renderizar un menú de opciones, dependiendo del rol del usuario.

		2.1.- Menu configurado para usuarios anonimos**********************************************************************
		
			<sec:authorize access="isAnonymous()">
	      		<div class="navbar-header">
		          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		            <span class="sr-only">Toggle navigation</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		          </button>
		          <a class="navbar-brand" href="${urlRoot}">My CineSite</a>
		        </div>
		        <div id="navbar" class="navbar-collapse collapse">
		          <ul class="nav navbar-nav">  
		            <li><a href="${urlRoot}acerca">Acerca</a></li>  
		            <li><a href="${urlRoot}formLogin">Login</a></li>         
		          </ul>
		        </div><!--/.nav-collapse -->
	      	</sec:authorize>

	    2.2.-  Menu configurado para usuarios EDITOR*************************************************************************
	    	<sec:authorize access="hasAnyAuthority('EDITOR')">
	        	 <div class="navbar-header">
		          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		            <span class="sr-only">Toggle navigation</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		          </button>
		          <a class="navbar-brand" href="${urlRoot}">My CineSite | Administracion</a>
		        </div>
		        <div id="navbar" class="navbar-collapse collapse">
		          <ul class="nav navbar-nav">  
		            <!-- <li><a href="${urlRoot}peliculas/index">Peliculas</a></li>-->
		            <li><a href="${urlRoot}peliculas/indexPagina?page=0">Peliculas</a></li>
		            <!-- <li><a href="${urlRoot}banners/index">Banners</a></li> -->
		            <li><a href="${urlRoot}noticias/indicePaginado?page=0">Noticias</a></li>
		            <li><a href="${urlRoot}horarios/indicePaginado?page=0">Horarios</a></li>  
		            <li><a href="${urlRoot}admin/logout">Salir</a></li>            
		          </ul>
		        </div><!--/.nav-collapse -->
	        </sec:authorize>

	    2.3.- Menu configurado para usuarios GERENTE ************************************************************************
		   /* <sec:authorize access="hasAnyAuthority('GERENTE')">
	        	<div class="navbar-header">
		          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		            <span class="sr-only">Toggle navigation</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		          </button>
		          <a class="navbar-brand" href="${urlRoot}">My CineSite | Administracion</a>
		        </div>
		        <div id="navbar" class="navbar-collapse collapse">
		          <ul class="nav navbar-nav">  
		            <!-- <li><a href="${urlRoot}peliculas/index">Peliculas</a></li>-->
		            <li><a href="${urlRoot}peliculas/indexPagina?page=0">Peliculas</a></li>
		            <!-- <li><a href="${urlRoot}banners/index">Banners</a></li> -->
		            <li><a href="${urlRoot}banners/indexPagina?page=0">Banners</a></li>
		            <li><a href="${urlRoot}noticias/indicePaginado?page=0">Noticias</a></li>
		            <li><a href="${urlRoot}horarios/indicePaginado?page=0">Horarios</a></li>
		            <li><a href="${urlRoot}admin/logout">Salir</a></li>            
		          </ul>
		        </div><!--/.nav-collapse -->
	        </sec:authorize> */       
	3.- Configurar una url destino despues de hacer login
		3.1.- Se añade url por defecto al tag <form-login/> en el archivo security.xml***************************************
			<form-login login-page="/formLogin"	default-target-url="/admin/index"/>
		3.2.- Se crea metodo que atienda a la url configurada en el controlador LoginControler.java
			@GetMapping(value="/index")
			public String mostrarPrincipalAdmin() {
				return "admin";
			}
		3.3.- Se crea archvo admin.jsp y se copia plantilla menuAdmin.html
			3.3.1.- Se agrega la libreria para los tag de spring
				<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
			3.3.2.- Se agrega tag urlPublic
				<spring:url value="/resources" var="urlPublic"></spring:url>
				<spring:url value="/" var="urlRoot"></spring:url>
			3.3.3.- Se agrega la url configurada a los recursos estaticos
			3.3.4.- Se incluyen el menu y el footer
				    <!-- Fixed navbar -->
    			<jsp:include page="includes/menu.jsp"></jsp:include>

    			 <!-- FOOTER -->
				<jsp:include page="includes/footer.jsp"></jsp:include>
			3.3.5.- Se muestra nombre del usuario configurado en el archivo security.xml**************************************
				3.3.5.1- Se configura el tag de springSecurity en admin.jsp
					<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
				3.3.5.2- Se utiliza el tag authentication para recuperar los datos del usuario, crea una instacia 
				con los detalles del usuario**********************************************************************************
					<div class="jumbotron">        
				        <h3>Administración del Sistema</h3>
				        <p>Bienvenido(a) <sec:authentication property="principal.username"/> </p>
				    </div>
	4.- Recuperar el nombre del usuario que inicio sesion en un controlador
		4.1.- Se modifica el metodo mostrarprincipal(), se le incorpora un parametro de tipo authentication*******************
			@GetMapping(value="/index")
			public String mostrarPrincipalAdmin(Authentication authentication ) {
				System.out.println("Username: "+authentication.getName());
				return "menuAdmin";
			}
	5.- Recuperar en el metodo mostrarPrincipal(), los roles que tiene asociado el usuario, utilizando el metodo del 
	obj Authentication, getAuthorities(), el cual devuelve una coleccion
		for (GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("Rol: "+rol.getAuthority());
		}
**********************************************************************************************************************Seccion 19
*********************************************************************************Recuperar Usuarios y roles de una base de datos
	Hasta ahora en nuestra aplicación hemos tenido almacenados los usuarios y roles en memoria.
		Ventajas
			Configuración rápida (solo se declaran en el archivo XML).
			Adecuada para aplicaciones con pocos usuarios.
		Desventajas
			Si se declaran más usuarios, es necesario reinciar la aplicación.
			No es posible que el usuario pueda cambiar su contraseña.

	Spring Security permite recuperar los usuarios y contraseñas en una base de datos relacional como MySQL (Avanzado).
		Ventajas
			Se pueden agregar N usuarios en tiempo de ejecución (no es necesario reiniciar Apache Tomcat).
			Configuración adecuada para aplicaciones con muchos usuarios.
			Se puede crear un formulario (CRUD) para agregar usuarios.
			Solo se requieren dos tablas (usuarios y roles).
				•Se puede usar la base de datos por defecto.
			Podemos crear nuestra propia estructura de la base de datos.
				•Ideal para usar nuestras tablas de usuarios de acuerdo al contexto de nuestro proyecto.
				•El desarrollador es responsable de escribir las sentencias SQL para estas tablas.


	1.- Creacion de tablas en la base de datos (Tablas por defecto de springSecurity)
		-- Crear tabla de usuarios
		CREATE TABLE `users` (
			`username` varchar(50) NOT NULL,
			`password` varchar(50) NOT NULL,
			`enabled` tinyint(1) NOT NULL,
			PRIMARY KEY (`username`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;


		-- Crear tabla de roles
		CREATE TABLE `authorities` (
			`username` varchar(50) NOT NULL,
			`authority` varchar(50) NOT NULL,
			UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
			CONSTRAINT `authorities_ibfk_1`
			FOREIGN KEY (`username`)
			REFERENCES `users` (`username`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;

	2.- Configuracion en security.xml de SpringSecurity para leer las tablas de la base de datos********************************
		Se declara un tag <authentication-manager> y dentro configuramos el data source

			<!--Autenticacion desde una base de datos -->
			<authentication-manager >
				<authentication-provider>
					<jdbc-user-service data-source-ref="dataSource" />
				</authentication-provider>
			</authentication-manager>

			Nota: Spring Bean de conexión a MySQL. En nuestra aplicación esta declarado en el 
			archivo root-context.xml

	3.- Se insertan los datos de los usuarios en la BDs
		-- Insertamos nuestros usuarios
		INSERT INTO `users` VALUES ('luis','{noop}luis123',1);
		INSERT INTO `users` VALUES ('marisol','{noop}mari123',1);

		-- Insertamos (asignamos roles) a nuestros usuarios.
		INSERT INTO `authorities` VALUES ('luis','EDITOR');
		INSERT INTO `authorities` VALUES ('marisol','GERENTE');

	4.- Configuracion de tablas personalizadas en springSecurity*****************************************************************
		4.1.- Se crean las tablas personalizadas

				CREATE TABLE `Usuarios` (
					`cuenta` varchar(100) NOT NULL,
					`pwd` varchar(100) NOT NULL,
					`activo` tinyint(1) NOT NULL,
					`email` varchar(100) NOT NULL,
					`telefono` varchar(50) NOT NULL,
					PRIMARY KEY (`cuenta`)
				) ENGINE=InnoDB DEFAULT CHARSET=utf8;

				-- Crear tabla de Perfiles
				CREATE TABLE `Perfiles` (
					`cuenta` varchar(100) NOT NULL,
					`perfil` varchar(70) NOT NULL,
					UNIQUE KEY `authorities_idx_2` (`cuenta`,`perfil`),
					CONSTRAINT `authorities_ibfk_2`
					FOREIGN KEY (`cuenta`)
					REFERENCES `Usuarios` (`cuenta`)
				) ENGINE=InnoDB DEFAULT CHARSET=utf8;
		4.2.- Se insertan registros en las tablas
			-- Insertamos nuestros usuarios
			INSERT INTO `Usuarios` VALUES ('luis','{noop}luis123',1,'luis@test.com','9856523');
			INSERT INTO `Usuarios` VALUES ('marisol','{noop}mari123',1,'marisol@example.com','9856482');

			-- Insertamos (asignamos roles) a nuestros usuarios.
			INSERT INTO `Perfiles` VALUES ('luis','EDITOR');
			INSERT INTO `Perfiles` VALUES ('marisol','GERENTE');
		4.3.- Configuracion del DataSource en SpringSecurity, en el archivo security.xml****************************************
			<!--Autenticacion desde una base de datos personalizada -->
			<authentication-manager>
				<authentication-provider>
					<jdbc-user-service data-source-ref="dataSource"
						users-by-username-query="select cuenta,pwd,activo from Usuarios where cuenta = ?"
						authorities-by-username-query="select cuenta,perfil from Perfiles where cuenta = ?" />
				</authentication-provider>
			</authentication-manager>

			Nota: En las consultas solo se debe respetar el orden de los tipos de datos. 
			Los nombres de los campos pueden ser cualquiera.
	5.- Encriptar contraseñas con springSecurity********************************************************************************
		Spring Security recomienda usar el algoritmo bcrypt para encriptar passwords.
			https://docs.spring.io/spring-security/site/docs/current/reference/html/ns-config.html#ns-password-encoder
			Ventajas:
				Ejecuta un hashing one-way (solo se encripta, pero no se puede desencriptar).
				Fácil de configurar en nuestra aplicación web.
				Configuración (security.xml)
		5.1.- Se agrega la configuracion en el archivo security.xml************************************************************
			<!-- Se declara bean de bcrypt para la encriptacion de password -->
			<b:bean id="passwordEncoder" class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder" />

			<!--Autenticacion desde una base de datos personalizada -->
			<authentication-manager>
				<authentication-provider>
					<password-encoder ref="passwordEncoder"/>
					<jdbc-user-service data-source-ref="dataSource"
						users-by-username-query="select cuenta,pwd,activo from Usuarios where cuenta = ?"
						authorities-by-username-query="select cuenta,perfil from Perfiles where cuenta = ?" />
				</authentication-provider>
			</authentication-manager>
	6.- Encriptacion de contraseñas en controlador****************************************************************************
		6.1.- Se crea un controlador para usuariosController
			@Controller
			@RequestMapping("/usuarios")
			public class usuariosController {
				
			}
		6.2.- Se crea inyeccion de dependencias para un atributo de tipo BCryptPasswordEncoder
			@Autowired
			private BCryptPasswordEncoder encoder;
		6.3.- Se crea metodo para la encriptacion
			@GetMapping(value="/demo-Bcryp")
			public String pruebaEncripta() {
				String password ="mari123";
				String encriptado = encoder.encode(password);
				System.out.println("Password encriptado: "+encriptado);
				return "usuarios/demo";
			}
		6.4.- Se realizan modificaciones a las tablas 
			-- Agregamos la columna id como Primary Key y AUTO_INCREMENT.
			-- Creamos un UNIQUE INDEX en la columna cuenta

			ALTER TABLE `Usuarios` 
			ADD COLUMN `id` INT NOT NULL AUTO_INCREMENT FIRST,
			DROP PRIMARY KEY,
			ADD PRIMARY KEY (`id`),
			ADD UNIQUE INDEX `cuenta_UNIQUE` (`cuenta` ASC);


			-- Agregamos la columna id como Primary Key y AUTO_INCREMENT.
			-- Creamos un UNIQUE INDEX COMPOUND en las columnas cuenta y perfil

			ALTER TABLE `Perfiles` 
			ADD COLUMN `id` INT NOT NULL AUTO_INCREMENT FIRST,
			ADD PRIMARY KEY (`id`),
			ADD UNIQUE INDEX `cuenta_perfil_UNIQUE` (`cuenta`,`perfil`);******************Indice Unico Compuesto SQL
	7.- Creacion Clases Modelo, repositorios y clases de servicio
		7.1.- Creacion de Clases Modelo
			7.1.1.- Creacion de Clase Usuario y Clase Perfil
			7.1.2.- Se añaden las anotaciones @Entity y @Table
				@Entity
				@Table(name="usuarios")
				public class Usuario {

				}
			7.1.3.- Se agregan los atributos que mapean a la base de datos
				private int id ;
				private String cuenta;
				private String pwd;
				private int activo;
				private String email;
				private String telefono;
			7.1.4.- Se agrega @Anotacion @Id, para condigurar el atributo que sera la llave primaria, tambien se le
			agrega la @Anotacion @GeneratedValue, para configurar la manera como se va a generar
				@Id
				@GeneratedValue(strategy=GenerationType.IDENTITY)
				private int id ;
			7.1.5.- se agregan sus get, set y el metodo toString
			7.1.6.- Creacion del Perfil

				@Entity
				@Table(name="perfiles")
				public class Perfil {

					@Id
					@GeneratedValue(strategy=GenerationType.IDENTITY)
					private int id;
					private String cuenta;
					private String perfil;
			7.1.7.- se agregan sus get, set y el metodo toString
		7.2.- Creacion de repositorios
			7.2.1.- Se crea Interface repositorio  UsuarioRepository.java
				public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

				}
			7.2.2.- Se crea Interface repositorio PerfilesRepository.java
				public interface PerfilesRepository extends JpaRepository<Perfil, Integer> {

				}
		7.3.- Creacion de las clases de servicio
			7.3.1.- Creacion de Interface
				7.3.1.1.- Creacion de interface IUsuariosService
					public interface IUsuariosService {
						void guardar(Usuario usuario);
					}
				7.3.1.2.- Creacion de Interface IPerfilesService
					public interface IPerfilesService {
						void guardar(Perfil perfil);
					}
			7.3.2.- Creacion de las Clase de implementacion
				7.3.2.1.- Creacion de la clase UsuriosServiceJPA.java
					@Service
					public class UsuariosServiceJPA implements IUsuariosService{

						//Inyeccion de dependencia del repository
						@Autowired
						UsuarioRepository usuriosRepo;
						
						@Override
						public void guardar(Usuario usuario) {
							// TODO Auto-generated method stub
							usuriosRepo.save(usuario);
						}

					}
				7.3.2.1.- Creacion de la clase PerfilesServiceJPA.java
					@Service
					public class PerfilesServiceJPA implements IPerfilesService{

						@Autowired
						PerfilesRepository perfilesRepo;
						
						@Override
						public void guardar(Perfil perfil) {
							// TODO Auto-generated method stub
							perfilesRepo.save(perfil);
						}

					}
	8.- Creacion de formulario para crear usuarios
		8.1.- Creacion de metodo en el controlador que renderiza el formulario					
			@GetMapping(value="/create")
			public String crearUsuario(@ModelAttribute Usuario usuario) {
				return "usuarios/formUsuario";
			}
		8.2.- Se copia la plantilla formUsuario, se le agregan las librerias de spring y los tag de springSecurity
		8.3.- Se crea metodo para guardar el usuario
			@PostMapping(value="/save")
			public String guardar(@ModelAttribute Usuario usuario) {
				System.out.println(usuario);
				return "redirect:usuarios/index";
			}
		8.4.- Se crea metodo para que renderice la vista listUsuarios.jsp
			@GetMapping(value="/index")
			public String index() {
				return "usuarios/listUsuarios";
			}
		8.5.- Se crea vista listUsuarios.jsp
		8.6.- Se modifica el metodo guardar para que reciba como parametro el campo select perfil, por medio de la anotacion
			@RequestParam
			@PostMapping(value="/save")
			public String guardar(@ModelAttribute Usuario usuario, @RequestParam("perfil") String perfil) {
				System.out.println(usuario);
				System.out.println(perfil);
				return "redirect:/usuarios/index";
			}	
		8.7.- Se crea en el controlador una instancia para las clases de servicio de usuarios y perfiles
			@Autowired
			private IUsuariosService servicesUsuario;
			@Autowired
			private IPerfilesService servisesPerfil;
		8.8.- Se modifica metodo guardar para hacerlo en la BD
			@PostMapping(value="/save")
			public String guardar(@ModelAttribute Usuario usuario, @RequestParam("perfil") String perfil) {
				String pass = usuario.getPwd();
				String passEncryp = encoder.encode(pass);		
				usuario.setPwd(passEncryp);
				usuario.setActivo(1);
				servicesUsuario.guardar(usuario);
				Perfil perfilTmp = new Perfil();
				perfilTmp.setCuenta(usuario.getCuenta());
				perfilTmp.setPerfil(perfil);
				servisesPerfil.guardar(perfilTmp);
				return "redirect:/usuarios/index";
			/*}*/
********************************************************************************************************************************
********************************************************************************************************************************
********************************************************************************************************************************
********************************************************************************************************************************
FIN SPRNG MVC, DATA, SECURITY***************************************************************************************************




















































Anotaciones****************************************************************************************************************************
	@SpringBootApplication
		Utilizamos la anotación @SpringBootApplication en nuestra aplicación o clase principal para habilitar una gran cantidad de 
		características, por ejemplo
			Configuración de Spring basada en Java, escaneo de componentes y, en particular, para habilitar la función de 
			configuración automática de Spring Boots.

		Si ha estado utilizando Spring Boot durante mucho tiempo, entonces sabe que antes debemos anotar nuestra clase de aplicación
		o clase principal con muchas anotaciones para comenzar
		@Configuration para habilitar la configuración basada en Java,
		@ComponentScan para habilitar el escaneo de componentes,
		y @EnableAutoConfiguration para habilitar la función de configuración automática de Spring Boots,
		pero ahora puede hacer todo eso simplemente anotando su clase de Aplicación con @SpringBootApplication.

		Si agregó la anotación @SpringBootApplication a la clase, no necesita agregar la anotación @EnableAutoConfiguration, 
		@ComponentScan y @SpringBootConfiguration . La anotación @SpringBootApplication incluye todas las demás anotaciones.

		Read more: https://javarevisited.blogspot.com/2018/05/the-springbootapplication-annotation-example-java-spring-boot.html#ixzz6C0WGFT39
	--------------------------------------------------------------------------------------------------------------------------------
	@ComponentScan
		Spring Boot escanea automáticamente todos los componentes incluidos en el proyecto usando la anotación 
	--------------------------------------------------------------------------------------------------------------------------------
	@RestController 
		Es una anotación de conveniencia que no hace más que agregar las anotaciones @Controller y @ResponseBody en una sola declaración.
		Una diferencia clave entre un MVC tradicional @Controller y el servicio web RESTful @RestController es la forma en que se crea 
		el cuerpo de respuesta HTTP. En lugar de confiar en una tecnología de visualización para realizar la representación del lado 
		del servidor de los datos a HTML, el controlador de descanso simplemente completa y devuelve el objeto de dominio en sí.

		Los datos del objeto se escriben directamente en la respuesta HTTP como JSON o XML y el cliente los analiza para procesarlos 
		más adelante, ya sea para modificar la vista existente o para cualquier otro propósito.

		Nota: Por defecto cada metodo que tengamos en el controlador, la respuesta es formateada a JSON
	-------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.OK)
		Si queremos especificar el estado de respuesta de un método de controlador , podemos marcar ese método con @ResponseStatus. 
		Tiene dos argumentos intercambiables para el estado de respuesta deseado: código y valor. 
		Ej:
			@GetMapping("/facturas/{id}")
			@ResponseStatus(HttpStatus.OK)
			public Factura show(@PathVariable("id") Long idFactura) {
				return factServ.findById(idFactura);
			}
	-------------------------------------------------------------------------------------------------------------------------------
	@Autowired 
		permite resolver la inyección de dependencias de los siguiente modos.

		En el constructor de la clase.
		En un atributo.
		En un método setter.
		En un método JavaConfig.
	------------------------------------------------------------------------------------------------------------------------------
	@Primary 
	  	Esta anotación nos permite indicar el bean default a inyectar en caso de que no se especifique ningún qualifier
	------------------------------------------------------------------------------------------------------------------------------
	@Qualifier 
		Nos permite especificar el nombre del bean que se va a inyectar en el atributo.
	-----------------------------------------------------------------------------------------------------------------------------
	@ModelAttribute
		Cuando se añade a un metodo del controlador, podemos agregar al modelo atributos que esten disponibles para todos los
		metodos del controlador.
	----------------------------------------------------------------------------------------------------------------------------
	@Configuration 
		Anotación encargada de definir que la clase es una clase de configuración para el framework
	----------------------------------------------------------------------------------------------------------------------------
	@EnableAuthorizationServer
	//habilitar un servidor de autorización (es decir, an AuthorizationEndpointy a TokenEndpoint) en el contexto de la aplicación actual,
	----------------------------------------------------------------------------------------------------------------------------
	@EnableResourceServer 
		permite que nuestra aplicación se comporte como un  servidor de recursos  al configurar un OAuth2AuthenticationProcessingFilter  
		y otros componentes igualmente importantes .

		Consulte la  clase ResourceServerSecurityConfigurer para tener una mejor idea de lo que se está configurando detrás de escena.
	----------------------------------------------------------------------------------------------------------------------------
	@Valid
	Se utiliza para realizar validaciones a nivel de bean, actua como interceptor y valida cada atributo antes de entrar en el 
	metododo
	----------------------------------------------------------------------------------------------------------------------------
	@Bean 
	Permite que en tiempo de ejecucion se cree el bean y se deje en el contexto.
	
	@Bean es una anotación a nivel de método y un análogo directo del <bean/> elemento XML . Los soportes de anotación más de 
	los atributos que ofrece <bean/>, tales como: init-method, destroy-method, autowiring, lazy-init, dependency-check, depends-on 
	y scope

	Declarando un Bean
	Para declarar un bean, simplemente anote un método con la @Bean anotación. Cuando JavaConfig encuentre dicho método, 
	ejecutará ese método y registrará el valor de retorno como un bean dentro de a BeanFactory. De forma predeterminada, el 
	nombre del bean será el mismo que el nombre del método (consulte el nombre del bean para obtener detalles sobre cómo 
	personalizar este comportamiento).

	Nota: Registra en el contenedor de spring lo retornado, queda disponible para inyeccion de dependencia de cualquier otro componente
	---------------------------------------------------------------------------------------------------------------------------
	@RequestBody (RestFullWebServices)
		la anotación @RequestBody asigna el cuerpo HttpRequest a un objeto de transferencia o dominio, lo que permite la 
		deserialización automática del cuerpo HttpRequest entrante en un objeto Java.
	---------------------------------------------------------------------------------------------------------------------------
	@ResponseBody
	La anotación @ResponseBody le dice a un controlador que el objeto devuelto se serializa automáticamente en JSON y se 
	devuelve al objeto HttpResponse .
	---------------------------------------------------------------------------------------------------------------------------
	
		------------------------------------------------------------------------------------------------------------------------
		@ResponseStatus
			La anotación @ResponseStatus nos permite marcar un método o excepción con el código de estado 
			http (code) y la razón (reason) que serán devueltos.

			Ejemplo_____________________________________________________________________________________________
				@PostMapping("/clientes")
				//Manejo de codigo de respuesta
				@ResponseStatus(HttpStatus.CREATED)
				//Se utiliza @RequestBody para que los datos los busque 
				//dentro del cuerpo del mensaje, ya que los datos vienen por json de la aplicacion Angular 
				public Cliente saveCliente(@RequestBody Cliente cliente) {
					return clienteServ.save(cliente);
				}
		------------------------------------------------------------------------------------------------------------------------
		@Transactional
			Esta característica da soporte a la transacionalidad, sino tuvieras soporte de Spring, deberías inicializar tu la 
			transacción con begin() y cerrarla con commit() o rollback() utilizando UserTransaction

				Opciones de Propagación

				REQUIRED: Da soporte a la transacción actual o crea una nueva sino existe.

				SUPPORTS: Da soporte a la transacción actual, sino existe, ejecuta el método sin transacción.

				MANDATORY: Da soporte a la transacción actual, sino existe, lanza una excepción.

				REQUIRES_NEW: Crea una nueva transacción, si existe una, la suspende.

				NOT_SUPPORTED: Ejecuta el método sin transacción, sí existe, la suspende.

				NEVER: Ejecuta el método sin transacción, sí existe, lanza una excepción.

				NESTED: Ejecuta dentro una transacción si existe una.

			Transacción de solo lectura readOnly

			Imaginaros que tenemos una entidad con un parámetro cómo “FetchType.Lazy” y un servicio en el cual, necesitamos 
			acceder a esos datos, sólo para usarlos eventualmente. Tenemos dos opciones:

				*Configurar los JPA graphs, para que, cuando lancemos un método concreto de nuestro repositorio, vuelva 
				este Lazy > Eager. baeldung 
				*Anotar el método como @Transactional(readOnly = true). Esto nos permitirá acceder a los métodos Lazy, 
				sin problemas, utilizando el initizalize de hibernate
				*Anotarlos con @Transactional.
		----------------------------------------------------------------------------------------------------------------------
		@EnableGlobalMethodSecurity(securedEnabled = true)
			Es una configuracion de seguridad que nos permite habilitar la configuracion de la anotacion @Secured, para la 
			configuracion de authorizacion de roles 
			ej:

				@EnableGlobalMethodSecurity(securedEnabled = true)
				@Configuration
				public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
		-----------------------------------------------------------------------------------------------------------------------
		@Secured
			La anotación @Secured se utiliza a nivel de método. Por ejemplo, puede agregar la anotación @Secured encima del 
			método @RequestMapping que maneja la solicitud HTTP DELETE para permitir que solo aquellos usuarios que tienen un 
			rol ADMIN invoquen este método.

			Nota:  La anotación @Secured incluye un nombre de autoridad. Si esta anotación se utiliza con un nombre de función, 
			no olvide utilizar el prefijo " ROLE_"  . Ej:

			@Secured({"ROLE_ADMIN", "ROLE_USER"})
			@PostMapping("/clientes/upload")
			private ResponseEntity<?> uploadArchivo(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Integer idCliente){...}
Despliegue en un servidor Tomcat*******************************************************************************************************
	1.- Necesitamos extender la clase SpringBootServletInitializer para admitir la implementación de archivos WAR. El código del 
	archivo de clase de la aplicación Spring Boot se proporciona a continuación:

		package com.tutorialspoint.demo;

		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.boot.builder.SpringApplicationBuilder;
		import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

		@SpringBootApplication
		public class DemoApplication  extends SpringBootServletInitializer {
		   @Override
		   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		      return application.sources(DemoApplication.class);
		   }
		   public static void main(String[] args) {
		      SpringApplication.run(DemoApplication.class, args);
		   }
		}
	2.- Configuración de la clase principal
		En Spring Boot, debemos mencionar la clase principal que debería comenzar en el archivo de compilación. Para este propósito, 
		puede utilizar los siguientes fragmentos de código:

		Para Maven, agregue la clase de inicio en las propiedades pom.xml como se muestra a continuación:

			<start-class>com.tutorialspoint.demo.DemoApplication</start-class>
	3.- Actualizar el paquete JAR en WAR
		Tenemos que actualizar el paquete JAR en WAR usando los siguientes fragmentos de código:

		Para Maven, agregue el paquete como WAR en pom.xml como se muestra a continuación:

			<packaging>war</packaging>
	4.- Ahora, vamos a escribir un punto final de descanso simple para devolver la cadena "Hola mundo desde Tomcat". 
	Para escribir un punto final de descanso, debemos agregar la dependencia del iniciador web Spring Boot en nuestro 
	archivo de compilación.

		Para Maven, agregue la dependencia de inicio Spring Boot en pom.xml usando el código que se muestra a continuación:

		<dependency>
		   <groupId>org.springframework.boot</groupId>
		   <artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	5.- Ahora, escriba un punto final de descanso simple en el archivo de clase de la aplicación Spring Boot usando el 
	código como se muestra a continuación:

			package com.tutorialspoint.demo;

			import org.springframework.boot.SpringApplication;
			import org.springframework.boot.autoconfigure.SpringBootApplication;
			import org.springframework.boot.builder.SpringApplicationBuilder;
			import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
			import org.springframework.web.bind.annotation.RequestMapping;
			import org.springframework.web.bind.annotation.RestController;

			@SpringBootApplication
			@RestController
			public class DemoApplication  extends SpringBootServletInitializer {
			   @Override
			   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
			      return application.sources(DemoApplication.class);
			   }
			   public static void main(String[] args) {
			      SpringApplication.run(DemoApplication.class, args);
			   }
			   
			   @RequestMapping(value = "/")
			   public String hello() {
			      return "Hello World from Tomcat";
			   }
			}
	6.- Empaquetado de su aplicación
		Ahora, cree un archivo WAR para implementarlo en el servidor Tomcat utilizando los comandos Maven y Gradle para empaquetar 
		su aplicación como se indica a continuación:

		Para Maven, use el comando mvn package para empaquetar su aplicación. Luego, se creará el archivo WAR y puede encontrarlo 
		en el directorio de destino 
***************************************************************************************************************************************
SPRING BOTS****************************************************************************************************************************
	Spring Boot es un sub-proyecto de Spring Framework que busca facilitarnos la creación de proyectos con 
	Spring Framework, eliminando la necesidad de crear largos archivos de configuración XML.
		Estas configuraciones tediosas y propensas a errores ya no son necesarias debido a que Spring Boot provee 
		configuraciones por defecto para la mayoría de las tecnologías usadas (Spring MVC, Spring Data JPA & Hibernate, 
		Spring Security, Spring REST, etc).
		Spring Boot nos ayuda a administrar todas las dependencias (archivos JAR y versiones compatibles).
		Spring Boot provee un modelo de programación parecido a las aplicaciones java tradicionales que se 
		inician en el método main.


	1.- Se crea proyecto basado en sprigboots con sring initializr
		https://start.spring.io/
		1.1.- tipo de proyecto Maven como manejador de dependencias
		1.2.- lenguaje de prog. Java
		1.3.- Version se spring boot
		1.4.- Project Metadata --> com.eareiza
		1.5.- artefacto
		1.6.- nombre del proyecto  --> HOLAmUNDO
		1.7.- sE CONFIGURA LA VERSION DE Java
		1.8.- Se añaden las dependencias
			web
		1.9.- Se guarda el archivo comprmido en el workspace
		1.10.- Se descomprime el archivo
		1.11.- desde el ide se importa el proyecto con maven, proyectos existentes 
		1.12.- se escoge el proyecto
		1.13.- Se configura la version del java en el proyecto
			1.13.1.- Click der en la carpeta JRE del proyesto --> Properties --> workspace default JRE
		Nota: tuve que cambiar el puerto por defecto en el archivo Application.Properties**********************************************
			server.port = 8090
	2.- Creacion de controlador
		2.1.- Se crea un nuevo paquete en el folder de la aplicacion
			com.eareiza.HolaMundo.controller
		2.2.- Se crea dentro del paquete creado una clase -->HomeController
			2.2.1.- Se le agrega la anotacion @RestController
				@RestController
				public class HomeController {

				}
			2.2.2.- Se crea metodo
				@RestController
				public class HomeController {
					@GetMapping("/")
					public String iniciar() {
						return "Hola Mundo";
					}
				}
Seccion 22 SpringBoot y thymeleaf******************************************************************************************************
	1.- Creacion de la app Empleos con springInitializr********************************************************************************
		https://start.spring.io/
		1.1.- tipo de proyecto Maven como manejador de dependencias
		1.2.- lenguaje de prog. Java
		1.3.- Version se spring boot
		1.4.- Project Metadata --> com.eareiza
		1.5.- artefacto
		1.6.- nombre del proyecto  --> empleo
		1.7.- sE CONFIGURA LA VERSION DE Java
		1.8.- Se añaden las dependencias
			web --> trae enbebido un contenedor con apache Tomcat
			thymeleaf --> motor de plantillas para vistas
			dev-tools  --> herramientas de desarrolla para la aplicacion
		1.9.- Se guarda el archivo comprmido en el workspace
		1.10.- Se descomprime el archivo
		1.11.- desde el ide se importa el proyecto con maven, proyectos existentes 
		1.12.- se escoge el proyecto
		1.13.- Se configura la version del java en el proyecto
			1.13.1.- Click der en la carpeta JRE del proyesto --> Properties --> workspace default JRE
		

		Nota: tuve que cambiar el puerto por defecto en el archivo Application.Properties
			server.port = 8090


	2.- Creacion de controlador
	2.1.- Se crea un nuevo paquete en el folder de la aplicacion
		com.eareiza.HolaMundo.controller
	2.2.- Se crea dentro del paquete creado una clase -->HomeController
		2.2.1.- Se le agrega la anotacion @RestController
			@Controller
			public class HomeController {
		2.2.2.- Se crea metodo
			@GetMapping("/")
			public String motrarHome() {
				return "home";
			}
	3.- Creacion de plantilla para renderizar --> home
		3.1.- Se crea un archivo html en el folder src/main/resources --> templates
			new --> web --> file html
Thymeleaf******************************************************************************************************************************
	***Referencia a un tutorial de thymeleaf --> https://www.thymeleaf.org/documentation.html
	Thymeleaf es un motor de plantillas para aplicaciones web desarrolladas con Java.
	Es algo similar a los JSPs, con algunas diferencias.
		Página Oficial: www.thymeleaf.org
	Comúnmente utilizado con Spring Boot para generar vistas con código HTML para aplicaciones web.
	En un proyecto Spring boot, ya viene configurado Thymeleaf con valores por defecto al momento de agregar la dependencia.
	Configuración
		 Agregar la siguiente dependencia al archivo pom.xml:
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-thymeleaf</artifactId>
			</dependency>
			Referencia --> https://openwebinars.net/blog/thymeleaf-vs-jsp/

	****Expresiones+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		Cinco tipos:

			${...}: Expresiones variables. Estas son expresiones OGNL (o Spring EL si tiene Spring integrado)

			*{...}: Expresiones de selección. Igual que el anterior, excepto que se ejecutará solo en un objeto previamente seleccionado

			#{...}: Expresiones de mensaje (i18n). Se usa para recuperar mensajes específicos de la localidad de fuentes externas

			@{...}: Expresiones de enlace (URL). Utilizado para construir URL
			~{...}: Fragmentos de expresiones. Representar fragmentos de marcado y moverlos alrededor de las plantillas.	
***************************************************************************************************************************************
	4.- Agregar informacion al modelo para desplegarlo en la vista
		4.1.- Se agrega como parametro una variable de tipo model, en el metodo mostrarHome()
			@GetMapping("/")
			public String mostrarHome(Model modelo) {
				modelo.addAttribute("mensaje", "Bienvenidos a empleos App");
				modelo.addAttribute("fecha", new Date() );
				return "home";
			}
		4.2.- Se renderiza en la vista
			4.2.1.- Se configurar namespace thymeleaf en home.html
				<html xmlns:th="http://www.thymeleaf.org">*****************************************************************************
			4.2.2.- Se configura etiqueta h1 para que muestre el atributo del modelo
				<body>
					33***************************************************************************************
				</body>
	5.- Configuracion para que las plantillas html ya tengan namespace de thymeleaf****************************************************
		Nota: nos permite incluir el namespace de forma automatica en la plantlla html
		windows --> Preferences --> web --> HTML Files --> Editor --> templates
		5.1.- Seleccionamos new HTML File(5) --> edit
		5.2.- Se agrega el namespace
			<html xmlns:th="http://www.thymeleaf.org">
	6.- Agregar tipo de datos simples al modelo y desplegarlos en la vista
		6.1.- Se modifica el metodo mostrarHome(), para añadir al modelo los datos
			@GetMapping("/")
			public String motrarHome(Model modelo) {
		//		modelo.addAttribute("mensaje", "Bienvenidos a empleos App");
		//		modelo.addAttribute("fecha", new Date() );
				String nombre = "Auxiliar de contabilidad";
				Date fechaPub = new Date();
				double salario = 9000.0;
				boolean vigente = true;
				modelo.addAttribute("nombre", nombre); 
				modelo.addAttribute("fecha", fechaPub);
				modelo.addAttribute("salario", salario);
				modelo.addAttribute("vigente", vigente);
				return "home";
			}
		6.2.- Se modifica la vista home.html para que renderice los datos
			<h1>Bienvenido</h1>
			<h2>Oferta de trabajo</h2>
			<h3 th:text="'Titulo: '+${nombre}"></h3>
			<h3 th:text="'Fecha: '+${fecha}"></h3>
			<h3 th:text="'Salario: '+${salario}"></h3>
			<h3 th:text="'Vigente: '+${vigente}"></h3>
	7.- Iteraciones en thymeleaf, expresion th:each************************************************************************************
		En Thymeleaf las iteraciones se pueden realizar con la expresión th:each
		Similar a un for en Java.
		Esta expresión puede iterar sobre diferentes tipos de datos como:
			 List
			Map
			 Iterable
			Map
		7.1.- Se crea metodo mostrarListado() en el HomeController.java
			@GetMapping("/listado")
			public String mostrarListado(Model modelo) {
				List<String> lista = new LinkedList<String>();
				lista.add("Ingeniero en Informatica");
				lista.add("Auxiliar de Contabilidad");
				lista.add("Arquitecto");
				lista.add("Vendedor");
				modelo.addAttribute("empleos", lista);
				return "listado";
			}
		7.2.- Se crea la vista listado.html, con la expresion th:each*****************************************************************
			<h1>Vacantes disponibles</h1>
			<ol>
				<li th:each="tmpEmp: ${empleos}" th:text="${tmpEmp}"></li>
			</ol>	
	8.- Crear clase modelo Vacantes
		8.1.- Se crea nuevo paquete com.eareiza.model
		8.2.- Se crea clase modelo, su get, set y tiString -->Vacante
			public class Vacante {
			private int id;
			private String nombre;
			private String descripcion;
			private Date fecha;
			private Integer salario;
		8.3.- Se crea metodo mostrarDetalle(), en el controlador --> HomeController.java
			@GetMapping("/detalle")
			public String mostrarDetalle(Model modelo) {
				Vacante vacante = new Vacante();
				vacante.setNombre("Ingeniero de Comunicaciones");
				vacante.setDescripcion("Se solicita ingeniero para dar soporte a internet");
				vacante.setFecha(new Date());
				vacante.setSalario(9700);
				modelo.addAttribute("vacante", vacante);
				return "detalle";
			}
		8.4.- Se renderiza en la vista -->detalle.html
			<h1>Detalle de la oferta de trabajo</h1>
			<h3 th:text="'Nombre: '+${vacante.nombre}"></h3>
			<h3 th:text="'Fecha de Publicacion: '+${vacante.fecha}"></h3>
			<h3 th:text="'Salario Ofrecido: '+${vacante.salario}"></h3>
			<h3 th:text="'Descripcion: '+${vacante.descripcion}"></h3>
	9.- Renderizar una lista de obj vacante
		9.1.- Se crea metodo que genera una lista de vacantes getVacantes()
			private List<Vacante> getVacantes(){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				List<Vacante> lista = new LinkedList<>();
				try {
					Vacante vacante1 = new Vacante();
					vacante1.setId(1);
					vacante1.setNombre("Ingeniero Civil");
					vacante1.setDescripcion("Se solicita Ing. Civil para diseñar un puente peatonal");
					vacante1.setFecha(sdf.parse("08-02-2019"));
					vacante1.setSalario(9600);
					

					Vacante vacante2 = new Vacante();
					vacante2.setId(2);
					vacante2.setNombre("Contador Publico");
					vacante2.setDescripcion("Se solicita Contador Publico");
					vacante2.setFecha(sdf.parse("18-02-2019"));
					vacante2.setSalario(9800);
					

					Vacante vacante3 = new Vacante();
					vacante3.setId(3);
					vacante3.setNombre("Ingeniero Electrico");
					vacante3.setDescripcion("Se solicita Ing. Electrico");
					vacante3.setFecha(sdf.parse("28-02-2019"));
					vacante3.setSalario(9900);
					
					lista.add(vacante1);
					lista.add(vacante2);
					lista.add(vacante3);
				}catch (ParseException e) {
					System.out.println(e.getMessage());
				}
				return lista;
			}
		9.2.- Se crea un metodo mostrarTabla
			@GetMapping("/tabla")
			public String mostrarTabla(Model modelo) {
				List<Vacante> listaVac = getVacantes();
				modelo.addAttribute("vacantes", listaVac);
				return "tabla";
			}
		9.3.- se copia cdn de bootstrap y se pega en la vista
			<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
			<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
		9.4.- Se crea tabla en la vista 
			<h1 class="text-primary">Lista de Vacantes</h1>
			<table class="table table-hover table-bordered table-striped">
				  <tr>
				    <th>Id</th>
				    <th>Nombre</th>
				    <th>Fecha de Publicacion</th>
				    <th>Salario</th>
				    <th>Descripcion</th>
				  </tr>
				  
				  <tr th:each="vacante : ${vacantes}">
				    <td th:text="${vacante.id}">Jill</td>
				    <td th:text="${vacante.nombre}">vaca</td>
				    <td th:text="${vacante.fecha}">50</td>
				    <td th:text="${vacante.salario}">Smith</td>
				    <td th:text="${vacante.descripcion}">50</td>
				  </tr>
				 
			</table>
****************************************************************************************************************Condicionales thymeleaf
	1.- Se agrega atributo a la clase Vacante, con su get y set
		private Integer destacada;
	2.- Se le setea el atributo destacada a cada uno de los obj de tipo Vacante
	3.- Se renderizan los cambios en la vista --> tabla.jsp
		3.1.- Se agrega header  a la tabla
			<th>Destacada</th>
		3.2.- Se agrega fila para renderizar el cambios
			<td th:text="${vacante.destacada}">50</td>

			
	4.- Se agrega un condicional para que no muestre solo 1 y 0 , sea mas entendible para el usuario
		Operador Elvis (?:)
		El operador Elvis permite renderizar texto DENTRO de un elemento HTML,
		dependiendo de una expresión Booleana. Es muy similar al operador ternario en otros
		lenguajes de programación.
		
			Ejemplo:
			<td th:text="${usuario.estatus == 1} ? 'ACTIVO' : 'BLOQUEADO'" />

		<!-- <td th:text="${vacante.destacada}">50</td>-->
		<td th:text="${vacante.destacada == 1 ? 'Si' : 'No'}"></td>**************Operador Elvis***************************************
	5.- Condicional if , aplicando componente badge de bootstrap
		if – unless
			La expresión if - unless permite renderizar un elemento HTML, dependiendo de una
			expresión Booleana. Es muy similar a un if-else en otros lenguajes de programación.
			Ejemplo
			<td>
				<span th:if="${alumno.genero == 'F'}"> Femenino </span>
				<span th:unless="${alumno.genero == 'M'}"> Masculino </span>
			</td>
		<!-- <td th:text="${vacante.destacada}">50</td>-->
		<!--<td th:text="${vacante.destacada == 1 ? 'Si' : 'No'}"></td> -->
		<td>
			<span th:if="${vacante.destacada == 1}" class="badge badge-success">Si</span>
			<span th:unless="${vacante.destacada == 1}" class="badge badge-danger">No</span>
		</td>
*******************************************************************************************Referenciar archivos estaticos con thymeleaf
	***Insertar imagenes estaticas en la vista
	Las URLs relativas al ContextPath son las que son relativas al directorio raíz (ROOT) de una aplicacion
	web, una vez que están publicadas en el servidor.
		Las URLs relativas al ContextPath deben iniciar con "/" cuando vayamos a formar una URL para
		referenciar un recurso (imagenes, CSS, JS, PDF, etc) en nuestra aplicacion.
		En un proyecto web cuando se utiliza Thymeleaf como motor de plantillas, los recursos estáticos
		deben guadarse en el directorio src/main/resources/static
	Ejemplos:
		• Para incluir el archivo CSS myStyles.css en una vista se utilizaría la siguiente expresión:
		<link th:href="@{/css/myStyles.css}" rel="stylesheet">
		• Para incluir el archivo Javascript funciones.js en una vista se utilizaría la siguiente expresión:
		<script th:src="@{/js/funciones.js}"></script>
		• Para incluir la imagen foto.png en una vista, se utilizaría la siguiente expresión:
		<img th:src="@{/images/foto.png}" width="136" height="136" >
		Para incluir archivos Javascript y CSS vía CDN (Content Delivery Network) se utiliza la sintáxis
		estándar (sin expresiones Thymeleaf).
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	1.- Se crean directorios para los archivos estaticos --> src/main/resources/static  --> images, css, js
	2.- Se guarda la imagen en src/main/resources/static/images
	3.- Se renderiza la imagen en la vista --> tabla.html
		<img th:src="@{/images/logo1.png}" width="136" height="136" >
	***Incluir imagenes dinamicas en la vista
	1.- Se crea un atributo en la clase Vacante con su get y set
		private String imagen = "no-image.png";
		public String getImagen() {
			return imagen;
		}
		public void setImagen(String imagen) {
			this.imagen = imagen;
		}
	2.- Asignarle nombre de imagen a cada una de las vacantes en el HomeController.java
		vacante2.setImagen("empresa2.png");
		vacante1.setImagen("empresa1.png");
	3.- Se renderiza en la vista la imagen  -->tabla.html
		<td>
	  		<img th:src="@{/images/{img} (img=${vacante.imagen})}" width="136" height="136" >*****************************************
	  	</td>
Seccion 23 - Spring Boot y Thymeleaf - Contrladores************************************************************************************
*****************************************************************************************Anotacion @RequestMapping a nivel de un metodo
	La anotación @RequestMapping cuando se incluye antes de la declaración de un método en un controlador sirve para especificar la URL 
	y el Método HTTP (POST, GET, DELETE, PUT, etc) al que estará mapeado el método.
	El funcionamiento es similar al de las anotaciones @GetMapping, @PostMapping,etc.
	En versiones anteriores de Spring 4.3se utilizaba esta anotación para mapear los métodos de los controladores a las URLs.
	Apartir de la versión Spring 4.3se agregaron las siguientes variaciones de la anotación @RequestMapping.
	Anotaciones (Spring4.3+)
	Mapeo con la anotación@RequestMapping
	Usocomún
	@GetMapping("/lista")  -->	@RequestMapping(value="/lista", method=RequestMethod.GET)  -->	Desarrollo de aplicaciones web y 
	RestFul WebServices.

	@PostMapping("/guardar")  --> 	@RequestMapping(value="/guardar", method=RequestMethod.POST)  -->	Desarrollo de aplicaciones web 
	y RestFul WebServices.

	@DeleteMapping("/borrar") --> RequestMapping(value="/borrar", method=RequestMethod.DELETE) --> 	Desarrollo de RestFul WebServices.

	@PutMapping("/actualizar")  -->  @RequestMapping(value="/actualizar", method=RequestMethod.PUT) --> Desarrollo de RestFul WebServices.



	1.- Se crea controlador CategoriasController.java
		1.1.- Se agrega anotacion @Controller
			@Controller
			public class CategoriasController {
		1.2.- Se crean los metodos en el Contrladores
			// @GetMapping("/index")
			@RequestMapping(value="/index", method=RequestMethod.GET)*****************************************************************
			public String mostrarIndex(Model model) {
				return "categorias/listCategorias";
			}
			// @GetMapping("/create")
			@RequestMapping(value="/create", method=RequestMethod.GET)
			public String crear() {
				return "categorias/formCategorias	";
			}
			// @PostMapping("/save")
			@RequestMapping(value="/save", method=RequestMethod.POST)
			public String guardar() {
				return "categorias/listCategorias";
			}	
		1.3.- Se crea folder en  templates --> categorias
		1.4.- Se crea la vista en categorias --> listCategorias.html y formCategorias.html
*********************************************************************************************Anotacion @RequestMapping a nivel de clase
	1.- Se agrega @RequestMapping al controlador
		@Controller
		@RequestMapping(value="/Categorias")******************************************************************************************
		public class CategoriasController {
************************************************************************************************Anotacion @PathVariable -URLs Dinamicas
	1.- Se crea un controlador --> VacantesController.java
		@Controller
		@RequestMapping(value="/vacantes")
		public class VacantesController {
			
		}
	2.- Se crea metodo -->verDetalle() con la anotacion @PathVariable
		@GetMapping(value="/view/{id}")
		public String verDetalle(@PathVariable("id")int idVacante, Model modelo) {***************************************************
			System.out.println("Id Vacande: "+idVacante);
			modelo.addAttribute("idVacante", idVacante);
			return"/vacantes/detalle";
		}
	3.- Se crea directorio en templates -->vacantes
	4.- Se crea vista en directorio creado vacantes -->detalle.html
		4.1.- Se renderiza atributo del modelo
		 	<h1 th:text="'El id es:'+${idVacante}"></h1>
	5.- Se crea boton en la vista tabla.html, para motrar detalle
		5.1.- En la vista se añade una columna al header
			<th>Detalle</th>
		5.2.- Se añade detalle al cuerpo de la tabla con url dinamica*****************************************************************
			<td>
				<a th:href="@{/vacantes/view/{id} (id=${vacante.id})}" class="btn btn-success">Detalle</a>
			</td>
****************************************************************************************************************Anotacion @RequestParam
	1.- Se crea metodo en el controlador VacantesController.java, que recibe parametro con la anotacion @RequestParam
		@GetMapping("/delete")
		public String eliminar(@RequestParam("id") int idVacante, Model modelo) {
			System.out.println("Vacante eliminada con el id "+idVacante);
			modelo.addAttribute("idVacante", idVacante);
			return "vacante";
		}
	
	2.- Se cre vista para renderizar -->vacante.html
		<h1>Vacante id [[${idVacante}]] Borrada</h1>
	3.- Ejercicio de simulacion de eliminar vacante
		3.1.- Se crea clumna en la vista tabla.html
			<th>Borrar</th>

			<td>
				<a th:href="@{/vacantes/delete(id=${vacante.id})}" class="btn btn-danger">Borrar</a>**********************************
			</td>
	4.- @RequestParam por metodo Post
		4.1.- Se crea formulario en vista formCategorias.html
			<h1>Formulario de categorias</h1>
			<form th:action="@{/categorias/save}" method="post">
			  Primer Nombre:<br>
			  <input type="text" name="nombre" value="">
			  <br>
			  Descripcion:<br>
			  <input type="text" name="descripcion" value="">
			  <br><br>
			  <input type="submit" value="Guardar">
			</form>
		4.2.- Se modifica metodo guardar de CategoriasController.java
			// @PostMapping("/save")
			@RequestMapping(value="/save", method=RequestMethod.POST)
			public String guardar(@RequestParam("nombre") String nombre,@RequestParam("descripcion") String desc ) {
				System.out.println("Categoria: "+nombre);
				System.out.println("Descripcion: "+desc);
				return "categorias/listCategorias";
			}				
Seccion 24 Spring Boot y thymeleaf - Injeccion de Dependencias*************************************************************************
*******************************************************************************************************************Anotacion @Autowired
	1.- Se crea una clase de servicio
		1.1.- Se crea un paquete -->service
		1.2.- Se crea en el paquete una interface  --> IVacanteService
			1.2.1.- Se crea un metodo buscarTodas
				public interface IVacanteService {
					List<Vacante> buscarTodas();
				}					
		1.3.- Se crea clase de implementacion VacanteServiceImp.java
			1.3.1.- Se implementa la interface IVacanteService
			1.3.2.- Se sobreescribe metodo de la interface buscarTodas()
				@Override
				public List<Vacante> buscarTodas() {
					// TODO Auto-generated method stub
					return null;
				}
			1.3.3.- Se crea constructor
				1.3.3.1 Se agrega en el constructor la creacion de lista de vacantes que renderizan en la tabla
					public VacanteServiceImp() {
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						lista = new LinkedList<>();
						try {
							Vacante vacante1 = new Vacante();
							vacante1.setId(1);
							vacante1.setNombre("Ingeniero Civil");
							vacante1.setDescripcion("Se solicita Ing. Civil para diseñar un puente peatonal");
							vacante1.setFecha(sdf.parse("08-02-2019"));
							vacante1.setSalario(9600);
							vacante1.setDestacada(0);
							vacante1.setImagen("empresa1.png");
							

							Vacante vacante2 = new Vacante();
							vacante2.setId(2);
							vacante2.setNombre("Contador Publico");
							vacante2.setDescripcion("Se solicita Contador Publico");
							vacante2.setFecha(sdf.parse("18-02-2019"));
							vacante2.setSalario(9800);
							vacante2.setDestacada(1);
							vacante2.setImagen("empresa2.png");

							Vacante vacante3 = new Vacante();
							vacante3.setId(3);
							vacante3.setNombre("Ingeniero Electrico");
							vacante3.setDescripcion("Se solicita Ing. Electrico");
							vacante3.setFecha(sdf.parse("28-02-2019"));
							vacante3.setSalario(9900);
							vacante3.setDestacada(0);
							
							lista.add(vacante1);
							lista.add(vacante2);
							lista.add(vacante3);
						}catch (ParseException e) {
							System.out.println(e.getMessage());
						}
					}
			1.3.4.- Se crea atributo privado de manera global en la Clase
				private List<Vacante> lista = null;
			1.3.5.- Se modifica metodo buscarTodas(), para que retorne la lista creada en el constructor
				@Override
				public List<Vacante> buscarTodas() {		
					return lista;
				}
			1.3.6.- Se declara la clase VacanteServiceImp.java como servicio con la notacion @Service
				Procedimiento para inyectar (@Autowired) una clase de servicio en un controlador:
					1.Anotar la clase de servicio (la implementación) con la anotación @Service de Spring Framework.
					package net.itinajero.service;
					@Service
					public class VacantesServiceImpl implements IVacantesService{
						// Métodos de lógica de negocio
						@Override
						public List<Vacante> buscarTodas() {
							return lista;
						}
					}
				Por defecto, las clases de servicio tienen alcance Singleton (una sola instancia de la clase para toda la aplicación).
			1.3.7.- Se crea la injeccion de dependencias en el controlador, para la instancia de la clase de servicio
				@Autowired
				private IVacanteService serviceVacantes;
			1.3.8.- Se modifica el metodo mostrarTabla(), para que genere la lista de la clase de servicio
				@GetMapping("/tabla")
				public String mostrarTabla(Model modelo) {
					List<Vacante> listaVac = serviceVacantes.buscarTodas();
					modelo.addAttribute("vacantes", listaVac);
					return "tabla";
				}
		1.4.- Se crea metodo en la clase de servicio para que busque por id
			1.4.1.- Se crea firma en la interface
				Vacante buscarPorId(int id);
			1.4.2.- Se sobreescribe el metodo en la clase de servicio
				@Override
				public Vacante buscarPorId(Integer id) {
					
					return null;
				}
			1.4.3.- Se recorre la lista en el metodo para buscar la vacante
				public Vacante buscarPorId(Integer id) {
					for (Vacante vacante : lista) {
						if(vacante.getId() == id) {
							return vacante;
						}
					}
					return null;
				}
			1.4.4.- Se crea injeccion de dependencia en VacantesController.java, para crear instancia de la clase de servicio
				@Autowired
				private IVacanteService serviceVacantes;
			1.4.5.- Se modifica metodo verDetalle()
				@GetMapping(value="/view/{id}")
				public String verDetalle(@PathVariable("id")int idVacante, Model modelo) {
					Vacante vacante= serviceVacantes.buscarPorId(idVacante);
					System.out.println("Vacande: "+vacante);
					modelo.addAttribute("vacante", vacante);
					//Buscar vacantes de la BDs
					return"/vacantes/detalle";
				}














Seccion 25 Spring Boot y thymeleaf -Fragments  -Loyauts********************************************************************************
*************************************************************************************************************Integracion de Pagina HTML
	1.- Se copian carpetas Bootstrap - images -tinymce en el proyecto, recursos-static
	2.- Se copia plantilla home.html
	3.- Se referencian los archivos css y js con thymeleaf
		<!-- Bootstrap core CSS -->
	    <link th:href="@{/bootstrap/css/bootstrap.min.css}" rel="stylesheet">*********************************************************
	    <!-- Custom styles for this template -->
	    <link th:href="@{/bootstrap/css/jumbotron.css}" rel="stylesheet">
	    <link th:href="@{/bootstrap/css/sticky-footer-navbar.css}" rel="stylesheet">

		<script th:src="@{/bootstrap/js/bootstrap.min.js}"></script>
	4.- Se configuran las imagenes de la plantilla
		Ej:
			th:src="@{images/logo3.png}"
	5.- Integracion de forma dinamica de las vacantes en home.html
		5.1.- Se modifica el metodo de HomeController.java --> mostrarHome()
			@GetMapping("/")
			public String motrarHome(Model modelo) {
				List<Vacante> vacantes = serviceVacantes.buscarTodas();
				modelo.addAttribute("vacantes", vacantes);
				return "home";
			}
		5.2.- Se modifica home.html para que presente las vacantes de forma dinamica
			<div class="row" th:each="vacante : ${vacantes}">***************************************************************************
	          <div class="col-md-3">            
	            <img class="rounded mx-auto d-block" th:src="@{/images/{img} (img=${vacante.imagen})}" alt="Generic placeholder image" width="220" height="220">            
	          </div>									
	          <div class="col-md-9">
	            <h2 th:text="${vacante.nombre}"></h2>
	            <h6 class="card-title"><strong>Categoría: </strong> <span>Arquitectura</span></h6>
	            <h6 class="card-title"><strong>Publicado: </strong> <span th:text="${vacante.fecha}">2019-01-01 </span></h6>                
	            <p th:text="${vacante.descripcion}"></p>
	            <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>            
	          </div>
	        </div> 
	    5.3.- Se integra link de detalle al boton 
	    	<p><a class="btn btn-secondary" th:href="@{/vacantes/view/{id} (id=${vacante.id})}" role="button">Detalle &raquo;</a></p>

	6.- Integracion de la plantilla detalles.html 
		6.1.- Se copia la plantilla detalle.html
		6.2.- Se referencian los archivos estaticos css, js, bootstrap y se configuran las imagenes
			<!-- Bootstrap core CSS -->
		    <link th:href="@{/bootstrap/css/bootstrap.min.css}" rel="stylesheet">
		    <!-- Custom styles for this template -->
		    <link th:href="@{/bootstrap/css/jumbotron.css}" rel="stylesheet">
		    <link th:href="@{/bootstrap/css/sticky-footer-navbar.css}" rel="stylesheet">
		6.3.- Se renderizan los atributos de vacante
			<div class="row">
	          <div class="col-md-3">            
	            <img class="rounded mx-auto d-block" th:src="@{/images/{img} (img=${vacante.imagen})}" alt="Generic placeholder image" width="220" height="220">            
	          </div>
	          <div class="col-md-9">
	            <div class="card">
	              <h4 class="card-header">Aplicar para <strong th:text="${vacante.nombre}">Ingeniero Civil</strong></h4>              
	              <div class="card-body">
	                <h5 class="card-title"><strong>Categoría: </strong> <span></span></h5>
	                <h6 class="card-title"><strong>Publicado: </strong> <span th:text="${vacante.fecha}">2019-01-01 </span></h6>                
	                <h6 class="card-title"><strong>Salario Ofrecido: </strong ><span th:text="${vacante.salario}">$9000</span></h6>
	                <p class="card-text" th:text="${vacante.descripcion}"></p>
	                
	                <div class="card bg-light mb-3" >
	                  <div class="card-body">
	                    <h5 class="card-title">Detalles de la oferta de trabajo</h5>
	                    <p class="card-text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
	                  </div>
	                </div>   
	                <a class="btn btn-primary" href="#" role="button" title="Quiero aplicar para esta oferta de trabajo."><i class="fas fa-file" aria-hidden="true"></i> Aplicar</a>                            
	              </div>
	            </div>  
	          </div>
	        </div>
	7.- Se integra diseño de formulario para crear una categorias
		7.1.- Se copia plantilla formCategoria.html
		7.2.- Se referencian los archivos estaticos
		7.3.- Se configura el action del formulario
			<form th:action="@{/categorias/save}" method="post"> 
	8.- Creacion de fragmentos - Page Layouts*****************************************************************************************
		Normalmente las aplicaciones web comparten componentes (fragmentos de código HTML) 
		que se repiten en cada vista. Algunos ejemplos son:
			Cabecera (header).
			Menús.
			Pie de página (footer).
			y posiblemente muchos más.
		En estos casos se recomienda separar este código repetitivo en archivos 
		externos y solo mandarlos llamar en las vistas cada que sean requeridos.
		Ventajas:
			Nos evitamos repetir el mismo código en cada vista.
			Cuando se requiera cambiar por ejemplo el pie de página o agregar un 
			nuevo menú, lo haremos en un solo archivo, pero los cambios se verán 
			reflejados en todas las vistas que esten incluyendo estos archivos externos.
		Para este tipo de diseño de plantillas, Thymeleaf incluye las siguientes expresiones:
			th:fragment
				Permite definir un fragmento de código HTML en un archivo externo 
				(código HTML que es común en nuestras vistas).
			th:insert 
				Permite INSERTAR EL CÓDIGO HTMLde un fragmento definido previamente, 
				en el TAG en donde este declarada esta expresión.


		8.1.- Separar codigo del menu
			8.1.1.- Se crea directorio en templates para los fragmentos  --> Fragments
			8.1.2.- Se crea archivo de menu.html
			8.1.3.- Se pega el codigo del menu
			8.1.4.- Se asigna nombre al fragmento
				<nav th:fragment="menuPrincipal" class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
			8.1.5.- Se incluye el fragmento en las vistas
				<header th:insert="fragments/menu :: menuPrincipal">     
   				</header>
   		8.2.- Separar codigo del footer
			8.1.1.- Se crea directorio en templates para los fragmentos  --> Fragments
			8.1.2.- Se crea archivo de footer.html
			8.1.3.- Se pega el codigo del footer
			8.1.4.- Se asigna nombre al fragmento
				<div class="container" th:fragment="piePagina">
				   <p>
				     &copy; 2019 EmpleosApp, Inc. | WebApp Desarrollada con Spring Boot 2.1.2 | Autor: Iv&aacute;n E. Tinajero D&iacute;az | &middot; <a href="#">Privacy</a>
				     &middot; <a href="#">Terms</a>
				   </p>
				 </div>
			8.1.5.- Se incluye el fragmento en las vistas
				<footer class="footer" th:insert="fragments/footer :: piePagina">
     
    			</footer>
    9.- Creacion de formulario para vacante
    	9.1.- Se crea metodo en VacantesController.java para que renderice la vista
    		@GetMapping("/create")
			public String crearVacante() {
				return "vacantes/formVacante";
			}
		9.2.- Se crea vista en el directorio templates/vacantes --> formVacante.html
		9.3.- Se realiza la configuracion de los archivos estaticos
		9.4.- Se incluyen los fragmentos menu.html y footer.html
		9.5.- Se configura el action del formulario
			<form th:action="@{/vacantes/save}" method="get"> 
		9.6.- Se configura metodo guardar(), para que reciba los parametros del formulario
			@PostMapping("/save")
			public String guardar(@RequestParam("nombre")String nombre, @RequestParam("descripcion")String descripcion,
					@RequestParam("estatus")String estatus, @RequestParam("fecha")String fecha, @RequestParam("destacado")int destacado,
					@RequestParam("salario")double salario, @RequestParam("detalles")String detalles) {
				System.out.println("nombre: "+nombre);
				System.out.println("descripcion: "+descripcion);
				System.out.println("estatus: "+estatus);	
				System.out.println("fecha: "+fecha);
				System.out.println("destacado: "+destacado);
				System.out.println("salario: "+salario);
				System.out.println("detalles: "+detalles);
				return "vacantes/listVacantes";
			}
Seccion 26 Spring Boot y thymeleaf - Data Binding**************************************************************************************
****************************************************************************************************Data Binding - Clase modelo Vacante
	1.- Se compara los imput del formulario formVacante.html con los atributos de la clase modelo Vacante.java
	2.- Se modifica la clase Vacante.java para que tenga todas los atributos necesarios para el formulario
		private String estatus;
		private String detalles;		
		
		public String getEstatus() {
			return estatus;
		}
		public void setEstatus(String estatus) {
			this.estatus = estatus;
		}
		public String getDetalles() {
			return detalles;
		}
		public void setDetalles(String detalles) {
			this.detalles = detalles;
		}
	3.- Se modifica metodo guardar para que reciba los parametros por dataBinding
		@PostMapping("/save")
		public String guardar(Vacante vacante) {
			System.out.println("Vacante: "+vacante);
			return "vacantes/listVacantes";
		}
	4.- ************************************************Anotación @InitBinder –Perzonalizar Data Binding
	    Se modifica el controlador VacantesController, agregando el metodo initBinder con la 
		anotacion @InitBinder, le pasamos como parametro un WebDataBinder y se registra con el 
		metodo registerCustomEditor para todo tipo Date.class
		un objeto CustomDateEditor con el formato indicado con un objeto SimpleDateFormat
		//declarar un conversor de tipos fcha para que sea manejado por el controlador antes de almacenarlo en el bean
		//Aplica para todos los tipos de variable, tipo fecha
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		}
	5.- Crear metodo en la clase de servicio para guardar la vacante
		5.1.- Se crea firma en la interface IVacanteService.java
			void guardar(Vacante vacante);
		5.2.- Se implementa el metodo VacanteServiceImp.java
			@Override
			public void guardar(Vacante vacante) {
				lista.add(vacante);
			}
		5.3.- Se modifica el metodo guardar() en VacantesController.java, para que guarde la vacante en la lista
			@PostMapping("/save")
			public String guardar(Vacante vacante) {
				serviceVacantes.guardar(vacante);
				System.out.println("Vacante: "+vacante);
				return "vacantes/listVacantes";
			}
	6.- Creacion de lista para las vacantes.
		6.1.- Se crea metodo en VacantesController.java mostrarIndex()
			@GetMapping("/index")
			public String mostrarIndex(Model modelo) {
		6.2.-  Obtener todas las vacantes (recuperarlas con la clase de servicio)
			List<Vacante> vacantes = serviceVacantes.buscarTodas();
		6.3.- Agregar al modelo el listado de vacantes
			modelo.addAttribute("vacantes", vacantes);
		6.4.- Renderizar las vacantes en la vista(integrar el archivo template empleos/listVacantes.html)
			6.4.1.- Se copia plantilla html --> listVacantes.html
			6.4.2.- Se configuran los archivos estaticos
				<!-- Bootstrap core CSS -->
				<link th:href="@{/bootstrap/css/bootstrap.min.css}" rel="stylesheet">
				<!-- Custom styles for this template -->
				<link th:href="@{/bootstrap/css/jumbotron.css}" rel="stylesheet">
				<link th:href="@{/bootstrap/css/sticky-footer-navbar.css}" rel="stylesheet">

				<script th:src="@{/bootstrap/js/bootstrap.min.js}"></script> 
			6.4.3.- Se insertan el menu
				<header th:insert="fragments/menu :: menuPrincipal"></header> 
			6.4.4.- Se inserta el footer
				<footer class="footer" th:insert="fragments/footer :: piePagina"></footer>
			6.4.5.- Se configura propiedad href al boton para crear una nueva vacante
				<a class="btn btn-primary" th:href="@{/vacantes/create}" title="Crear nueva Oferta de Trabajo" role="button"><i class="fas fa-file" aria-hidden="true"></i> Nueva</a>            
            6.4.6.- Se renderiza la lista 
            	<tr th:each="vacante : ${vacantes}">
                  <td th:text="${vacante.categoria}"></td>
                  <td th:text="${vacante.nombre}"></td>                  
                  <td th:text="${vacante.fecha}"></td>
                  <td th:text="${vacante.estatus}"></td>
                  <td th:text="${vacante.destacada == 1 ? 'Si' : 'No'}"></td>
                  <td>
                    <a href="#" class="btn btn-success btn-sm" role="button" title="Editar el registro."><i class="fas fa-pencil-alt" aria-hidden="true"></i></a>
                    <a href="#" onclick="return confirm('¿Estas seguro?')" class="btn btn-success btn-sm" role="button" title="Eliminar el registro."><i class="fas fa-trash" aria-hidden="true"></i></a>
                  </td>
                </tr>   
            6.4.7.- Agregar al menu una opcion llamada "Vacantes" configurando la url
            	<li class="nav-item"><a class="nav-link active" th:href="@{vacantes/index}">Vacantes</a>

    7.- BindingResult para verificar errores 
    	7.1.- Se modifica el metodo guardar del controlador VacantesController.java, se le agrega un parametro de tipo BindingResult
    		@PostMapping("/save")
			public String guardar(Vacante vacante, BindingResult result) {***********************************************************
				serviceVacantes.guardar(vacante);
				System.out.println("Vacante: "+vacante);
				return "vacantes/listVacantes";
			}
		7.2.- Se validan los errores en el metodo guardar()
			if(result.hasErrors()){
				for (ObjectError error : result.getAllErrors()) {********************************************************************
					System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
				}
				return "vacantes/formVacante";
			}
		7.3.- Desplegar errores en la vista
			7.3.1.- Se modifica el metodo crear, agregando un parametro de tipo Vacante.java
				@GetMapping("/create")
				public String crearVacante(Vacante vacante) {
					return "vacantes/formVacante";
				}
			7.3.2.- Vincular nuestro HTML FORM con el objeto de modelo (th:object), en la vista formVacante.html
				<form th:action="@{/vacantes/save}" method="post" th:object="${vacante}">  ****************************************
			7.3.3.- Se agrega div dentro del form para el manejo de errores, utilizando la 
			expresión ${#fields.hasErrors('*')} y ${#fields.errors('*')}
				<div th:if="${#fields.hasErrors('*')}" class='alert alert-danger' role='alert'>
					Por favor corrija los siguientes errores:
					<ul>
						<li th:each="error : ${#fields.errors('*')}" th:text="${error}" />
					</ul>
				</div>
		7.4.- Redireccionar al metodo mostrarIndex() de VacantesController.java, modificando el metodo guardar()
			@PostMapping("/save")
			public String guardar(Vacante vacante, BindingResult result) {
				if(result.hasErrors()){
					for (ObjectError error : result.getAllErrors()) {
						System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
					}
					return "vacantes/formVacante";
				}
				serviceVacantes.guardar(vacante);
				System.out.println("Vacante: "+vacante);
				return "redirect:/vacantes/index";************************************************************************************
			}
		7.5.- Enviar un mensaje de confirmacion al usuario utilizando RedirectAttributes
			***Los atributos flash son almacenados temporalmente antes de hacer el redirect (tipícamente en la
			sesión) para tenerlos disponibles después del redirect. Despúes del Redirect son eliminados de la
			sesión automáticamente.
			7.5.1.- Se modifica metodo guardar(), Añadiendo un parametro RedirectAttributes y añadiendo con el metodo 
			addFlashAttribute un mensaje
				@PostMapping("/save")
				public String guardar(Vacante vacante, BindingResult result, RedirectAttributes redirect) {
					if(result.hasErrors()){
						for (ObjectError error : result.getAllErrors()) {
							System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
						}
						return "vacantes/formVacante";
					}
					serviceVacantes.guardar(vacante);
					redirect.addFlashAttribute("msg", "Registro guardado.");
					System.out.println("Vacante: "+vacante);
					return "redirect:/vacantes/index";
				}
	8.- Utilizar tinymce para el textarea
		8.1.- Se configura tinymce
			<script th:src="@{/tinymce/tinymce.min.js}"></script>
		8.2.- Se crea metodo
			tinymce.init({
	          selector: '#detalles',
	          plugins: "textcolor, table lists code",
	          toolbar: " undo redo | bold italic | alignleft aligncenter alignright alignjustify \n\
	                    | bullist numlist outdent indent | forecolor backcolor table code"
	      });
**************************************************************************************************Ejercicio de Injeccion de Dependencia
	1.- Se crea Clase Modelo Categoria.java
		public class Categoria {

		private Integer id;
		private String nombre;
		private String descripcion;
	2.- Se crea Interface ICategoriaService.java
		public interface ICategoriasService {
			void guardar(Categoria categoria);
			List<Categoria> buscarTodas();
			Categoria buscarPorId(Integer idCategoria);	
		}
	3.- Se crea clase de servicio CategoriasServicesImp.java
		@Service
		public class CategoriasServiceImp implements ICategoriasService{
		3.1.- Se crea constructor donde se crea lista enlazada LinkedList de categorias
			public CategoriasServiceImp() {
			super();
			listaCateg = new LinkedList<Categoria>();
			Categoria categoria1 = new Categoria();
			Categoria categoria2 = new Categoria();
			Categoria categoria3 = new Categoria();
		3.2.- Se crea atributo List, para almacenar la lista de categorias 
			private List<Categoria> listaCateg= null;
		3.3.- Se sobreesscribe metodo guardar()
			@Override
			public void guardar(Categoria categoria) {
				listaCateg.add(categoria);		
			}
		3.4.- Se sobreesscribe el metodo buscarTodas()
			@Override
			public List<Categoria> buscarTodas() {
				return listaCateg;
			}
		3.5.- Se sobreescribe metodo buscarPorId()
			@Override
			public Categoria buscarPorId(Integer idCategoria) {
				for (Categoria categoria : listaCateg) {
					if(categoria.getId() == idCategoria) {
						return categoria;
					}
				}
				return null;
			}
	4.- Inyectar la clase de servicio en CategoriasController.
		@Autowired
		private ICategoriasService categoriasServices;
	5.- Se modifica el metodo mostrarIndex(), para que generar la lista con la clase de servicio y añadirla al modelo
		// @GetMapping("/index")
		@RequestMapping(value="/index", method=RequestMethod.GET)
		public String mostrarIndex(Model model) {
			List<Categoria> lista = categoriasServices.buscarTodas();
			model.addAttribute("categorias", lista);
			return "categorias/listCategorias";
		}
	6.- Se renderiza la vista listCategorias.html
		6.1.- Se copia la plantilla listCategorias.html
		6.2.- Se configuran los archivos estaticos
			<!-- Bootstrap core CSS -->
		    <link th:href="@{/bootstrap/css/bootstrap.min.css}" rel="stylesheet">
		    <!-- Custom styles for this template -->
		    <link th:href="@{/bootstrap/css/jumbotron.css}" rel="stylesheet">
		    <link th:href="@{/bootstrap/css/sticky-footer-navbar.css}" rel="stylesheet">

		    <script th:src="@{/bootstrap/js/bootstrap.min.js}"></script>  

		6.3.- Se incluyen el menu y el pie de pagina
			<header th:insert="fragments/menu :: menuPrincipal"></header>

    		<footer class="footer" th:insert="fragments/footer :: piePagina"></footer>
    	6.4.- Se configura para que renderice la lista de categoria de forma dinamica
    		<tr th:each="categoria : ${categorias}">
              <th scope="row" th:text="${categoria.id}"></th>
              <td th:text="${categoria.nombre}"></td>
              <td th:text="${categoria.descripcion}"></td>
              <td>
                <a href="#" class="btn btn-success btn-sm" role="button" title="Editar"><i class="fas fa-pencil-alt" aria-hidden="true"></i></a>
                <a href="#" onclick="return confirm('¿Estas seguro?')" class="btn btn-success btn-sm" role="button" title="Eliminar"><i class="fas fa-trash" aria-hidden="true"></i></a>
              </td>
            </tr>
        6.5.- Se modifica metodo guardar(), para que guarde la categoria por medio de la clase servicio en la lista,
        	Se crea objeto RedirectAttributes para pasar mensaje al usuario, se una instancia de BindingResult para manejar 
        	los errores
        	// @PostMapping("/save")
			@RequestMapping(value="/save", method=RequestMethod.POST)
			public String guardar(Categoria categoria,BindingResult result ,RedirectAttributes attr) {
				if(result.hasErrors()) {
					for(ObjectError error : result.getAllErrors()) {
						System.out.println("Ocurrio un error: "+error.getDefaultMessage());;
					}
					return "categorias/formCategorias";
				}
				categoriasServices.guardar(categoria);
				attr.addFlashAttribute("msg", "Registro guardado.");
					return "redirect:/categorias/index";
				}

		6.6.- Se modifica metodo crear(), se le pasa como parametro un objeto Categoria.java, para vincularlo con el formulario
			// @GetMapping("/create")
			@RequestMapping(value="/create", method=RequestMethod.GET)
			public String crear(Categoria categoria) {
				return "categorias/formCategorias";
			}
		6.7.- Se agrega al formulario el objeto categoria vinculado y se le añade div para el manejo de errores
			<form th:action="@{/categorias/save}" method="post" th:object="${categoria}">   
            <div th:if="${#fields.hasErrors('*')}" class='alert alert-danger' role='alert'>
				Por favor corrija los siguientes errores:
				<ul>
					<li th:each="err : ${#fields.errors('*')}" th:text="${err}" />
				</ul>
			</div>       
********************************************************************Vincular Imputs del formulario con los Atributos de la clase modelo
	1.- Se vincula el formulario con el objeto de la clase modelo  -->  th:object="${vacante}"*****************************************
		<form th:action="@{/vacantes/save}" method="post" th:object="${vacante}"> 
	2.- Se vinculan los imputs con los atributos --> th:field="*{nombre}"  -->thymeleaf************************************************
		Ejemplo: 
		<input type="text" class="form-control" id="nombre" th:field="*{nombre}" name="nombre" placeholder="Titulo de la oferta de trabajo" required="required">
	3.- Se realiza la vinculacion para el formulario formCategoria
****************************************************************************************************Generar un select de forma dinamica
	1.- Se crea select de forma dinamica en listVacantes.htm 
		1.1.- Se cre injeccion de dependencia de la interface ICategoriasService, en VacantesController.java
			@Autowired
			private ICategoriasService serviceCategorias;
		1.2.- Se modifica metodo crear()
			1.2.1.- Se le agrega parametro ModelAttribute y se le añade como atributo la lista de categorias
				@GetMapping("/create")
				public String crearVacante(Vacante vacante, Model modelo) {
					modelo.addAttribute("categorias", serviceCategorias.buscarTodas());
					return "vacantes/formVacante";
				}
		1.3.- Se modifica el select para que renderice los options de forma dinamica
  		    <select class="form-control" name="categoria" id="categoria" th:field="*{categoria.id}">
              <option th:each="categoria : ${categorias}" th:value="${categoria.id}" th:text="${categoria.nombre}"></option>
            </select>
***************************************************************************************DataBinding entre un select y una clase definida
	1.- En la clase Vacante.java se declara un obj de tipo Categoria.java, con su get y set
		private Categoria categoria;
	
		public Categoria getCategoria() {
			return categoria;
		}
		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}
	2.- Se modifica el metodo toString()
		@Override
		public String toString() {
			return "Vacante [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
					+ ", salario=" + salario + ", destacada=" + destacada + ", imagen=" + imagen + ", estatus=" + estatus
					+ ", detalles=" + detalles + ", categoria=" + categoria + "]";
		}
	3.- Se configura el imput del select para que realice el databinding con el atributo categoria --> th:field="*{categoria.id}"
		<select class="form-control" name="categoria" id="categoria" th:field="*{categoria.id}">
Seccion 27 SpringBoot y Thymeleaf - Upload Files***************************************************************************************
	1. Se agregan propiedades al archivo application.properties	
		# CONFIGURACION MULTIPART (SUBIDA DE ARCHIVOS)
		# ¿Habilitamos subida de archivos?
		spring.servlet.multipart.enabled=true
		# Directorio intermedio para subir archivos (Linux/MAC)
		# spring.servlet.multipart.location=/tmp
		# Directorio temporal para subir archivos (Windows)
		spring.servlet.multipart.location=c:/tmp
		# Maximo tamaño de archivos que se pueden subir
		spring.servlet.multipart.max-file-size=2MB
	2.- Se crea directorio configurado
		c:/tmp
	3.- Se crea la clase utileria.java
		3.1.- Se crea paquete util
		3.2.- Se crea dentro del paquete creado la clase Utileria.java
			public class Utileria {
				public static String guardarArchivo(MultipartFile multiPart, String ruta) {********************************************
					// Obtenemos el nombre original del archivo.
					String nombreOriginal = multiPart.getOriginalFilename();
					try {
						// Formamos el nombre del archivo para guardarlo en el disco duro.
						File imageFile = new File(ruta+ nombreOriginal);
						System.out.println("Archivo: " + imageFile.getAbsolutePath());
						//Guardamos fisicamente el archivo en HD.
						multiPart.transferTo(imageFile);
						return nombreOriginal;
					} catch (IOException e) {
						System.out.println("Error " + e.getMessage());
						return null;
					}
				}
			}
	4.- Se configura la vista formVacante.html
		4.1.- Se le agrega el enctype al formulario**********************************************************************************
			<form th:action="@{/vacantes/save}" method="post" th:object="${vacante}" enctype="multipart/form-data">******************
		4.2.- Se configura el imput 
			<input type="file" class="form-control-file" name="archivoImagen" id="archivoImagen">
	5.- Se modifica el metodo guardar()
		5.1.- Se agrega parametro de tipo MultipartFile, por medio de un RequestParam
			public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attr, @RequestParam("archivoImagen") MultipartFile multipart) {
		5.2.- Se crean las validaciones para recibir el archivo
			if (!multiPart.isEmpty()) {
				//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
				String ruta = "c:/empleos/img-vacantes/"; // Windows
				String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
				if (nombreImagen != null){ // La imagen si se subio
					// Procesamos la variable nombreImagen
					vacante.setImagen(nombreImagen);
				}
			}
	6.- Quitar espacios del nombre del archivo, Se modifica el metodo guardarArchivo de la clase Utileria.java, 
	para quitar los espacios
			nombreOriginal = nombreOriginal.replace(" ", "-");
	7.- Agregar caracteres aleatorios al nombre del archivo para que no se repita
		7.1.- Se agrega en la clase utileria metodo randomAlphaNumeric()
			//Metodo para generar una cadena de longitud N de caracteres aleatorios
			public static String randomAlphaNumeric(int count) {
				String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXY0123456789";
				StringBuilder builder = new StringBuilder();
				while(count-- != 0) {
					int character = (int)(Math.random()* CARACTERES.length());
					builder.append(CARACTERES.charAt(character));
				}
				return builder.toString();
			}
		7.2.- Se crea atributo nombreFinal en el metodo guardarArchivo(), para que almacene la concatenacion 
		del metodo aleatorio con el nombre original
			public static String guardarArchivo(MultipartFile multiPart, String ruta) {
				// Obtenemos el nombre original del archivo.
				String nombreOriginal = multiPart.getOriginalFilename();
				nombreOriginal = nombreOriginal.replace(" ", "-");
				String nombreFinal = randomAlphaNumeric(8)+nombreOriginal;
				try {
					// Formamos el nombre del archivo para guardarlo en el disco duro.
					File imageFile = new File(ruta+ nombreFinal);
					System.out.println("Archivo: " + imageFile.getAbsolutePath());
					//Guardamos fisicamente el archivo en HD.
					multiPart.transferTo(imageFile);
					return nombreFinal;
				} catch (IOException e) {
					System.out.println("Error " + e.getMessage());
					return null;
				}
			}
*******************************************************Declarar propiedades en el archivo application.properties e inyectar los valores
	1.- Se crea la propiedad en el archivo application.properties
		empleaosApp.ruta.imagenes=c:/empleos/img-vacantes/
	2.- Se inyectan los valores en el controlador VacantesController.java
		2.1.- Se crea atributo en el controlador y se le agrega la anotacion @value****************************************************
			@Value("${empleaosApp.ruta.imagenes}")
			private String ruta;

	3.- Se elimina linea del metodo guardar donde se le pasa la ruta
Seccion 28 SpringBoot&SpringDataJPA****************************************************************************************************
	1.- Creacion de la base de datos empleosBD
		use empleosdb;
		-- Script para crear la base de datos empleosdb (MySQL)

		DROP TABLE IF EXISTS `Categorias`;
		CREATE TABLE `Categorias` (
		  `id` int(11) NOT NULL AUTO_INCREMENT,
		  `nombre` varchar(100) NOT NULL,
		  `descripcion` text,
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;

		DROP TABLE IF EXISTS `Perfiles`;
		CREATE TABLE `Perfiles` (
		  `id` int(11) NOT NULL AUTO_INCREMENT,
		  `perfil` varchar(100) NOT NULL,
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;

		DROP TABLE IF EXISTS `Usuarios`;
		CREATE TABLE `Usuarios` (
		  `id` int(11) NOT NULL AUTO_INCREMENT,
		  `nombre` varchar(45) NOT NULL,
		  `email` varchar(100) NOT NULL,
		  `username` varchar(45) NOT NULL,
		  `password` varchar(100) NOT NULL,
		  `estatus` int(11) NOT NULL DEFAULT '1',
		  `fechaRegistro` date DEFAULT NULL,
		  PRIMARY KEY (`id`),
		  UNIQUE KEY `username_UNIQUE` (`username`),
		  UNIQUE KEY `email_UNIQUE` (`email`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;

		DROP TABLE IF EXISTS `Vacantes`;
		CREATE TABLE `Vacantes` (
		  `id` int(11) NOT NULL AUTO_INCREMENT,
		  `nombre` varchar(200) NOT NULL,
		  `descripcion` text NOT NULL,
		  `fecha` date NOT NULL,
		  `salario` double NOT NULL,
		  `estatus` enum('Creada','Aprobada','Eliminada') NOT NULL,
		  `destacado` int(11) NOT NULL,
		  `imagen` varchar(250) NOT NULL,
		  `detalles` text,
		  `idCategoria` int(11) NOT NULL,
		  PRIMARY KEY (`id`),
		  KEY `fk_vacantes_categorias1_idx` (`idCategoria`),
		  CONSTRAINT `fk_vacantes_categorias1` FOREIGN KEY (`idCategoria`) REFERENCES `Categorias` (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;

		DROP TABLE IF EXISTS `Solicitudes`;
		CREATE TABLE `Solicitudes` (
		  `id` int(11) NOT NULL AUTO_INCREMENT,
		  `fecha` date NOT NULL,
		  `archivo` varchar(250) NOT NULL,
		  `comentarios` text,
		  `idVacante` int(11) NOT NULL,
		  `idUsuario` int(11) NOT NULL,
		  PRIMARY KEY (`id`),
		  UNIQUE KEY `Vacante_Usuario_UNIQUE` (`idVacante`,`idUsuario`),
		  KEY `fk_Solicitudes_Vacantes1_idx` (`idVacante`),
		  KEY `fk_Solicitudes_Usuarios1_idx` (`idUsuario`),
		  CONSTRAINT `fk_Solicitudes_Usuarios1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuarios` (`id`),
		  CONSTRAINT `fk_Solicitudes_Vacantes1` FOREIGN KEY (`idVacante`) REFERENCES `Vacantes` (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;

		DROP TABLE IF EXISTS `UsuarioPerfil`;
		CREATE TABLE `UsuarioPerfil` (
		  `idUsuario` int(11) NOT NULL,
		  `idPerfil` int(11) NOT NULL,
		  UNIQUE KEY `Usuario_Perfil_UNIQUE` (`idUsuario`,`idPerfil`),
		  KEY `fk_Usuarios1_idx` (`idUsuario`),
		  KEY `fk_Perfiles1_idx` (`idPerfil`),
		  CONSTRAINT `fk_Usuarios1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuarios` (`id`),
		  CONSTRAINT `fk_Perfiles1` FOREIGN KEY (`idPerfil`) REFERENCES `Perfiles` (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;




	2.- Se crea proyecto con Spring initializr
		https://start.spring.io/
		Project        	--> Maven Project
		Lenguaje       	--> Java
		Spring Boot 	--> 2.1.12
		Project Metadata
						--> Group 		--> com.eareiza
						--> Artifact  	--> JPA-Demo
						--> Options 	--> name 			--> JPA-Demo
										--> DEscripcion 	--> Ejemplos de spring Data JPA
										--> Package name 	--> com.eareiza
										--> Packaging 		--> jar
										--> Java 			--> 8
		Dependencias 	--> Spring Data JPA 
						--> MySql Driver
		--> Generar 
	3.- Se guarda el paquete creado en el workspace
	4.- Se descomprime
	5.- Se importa el proyecto en Spring sts 
		5.1.- archivo --> imports --> maven --> existing Maven Projects 
		5.2.- Se busca donde esta el pom del proyecto --> finalizar
	6.- Se copia el paquete com.eareiza.model desde el proyecto empleos
	7.- Configuracion del datasource
		7.1.- se configura el datasorce en el archivo application.properties
			# DATASOURCE (MYSQL 5.7)
			spring.datasource.driver-class-name=com.mysql.jdbc.Driver
			spring.datasource.url=jdbc:mysql://localhost:3306/empleosdb?useSSL=false
			spring.datasource.username=root
			spring.datasource.password=admin
			#JPA
			spring.jpa.generate-ddl=false
			spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
			spring.jpa.show-sql=true
			# Table names physically
			spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
		7.2.- Se añade dependencia 
			<dependency>
				<groupId>mysql</groupId>
				<artifactId>mysql-connector-java</artifactId>
				<version>5.1.47</version>
			</dependency>			
***************************************************************************Interfaz CommandLineRunner - Spring Boot Console Application
	1.- Configuracion con interfaz CommandLineRunner para que funcione como una aplicacion de Console
		1.1.- Se implementa la interfaz
			public class JpaDemoApplication implements CommandLineRunner{
		1.2.- Se añaden los metodos de la interfaz
		Nota: todo el codigo que se programe en este metodo sera ejecutado al inicio de la aplicacion
			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				
			}
********************************************************************************Anotaciones JPA - @Entity, @Table, @Id, @GeneratedValue
	1.- Mapear la clase categoria con la tabla categoria de la base de datos
		1.1.- Se agrega la anotacion @Entity a la clase modelo Categoria.java
		1.2.- Se agrega la anotacion @Table, mapeandole la tabla de la base de datos, debe coincidir el nombre con la 
		tabla de la base de datos
			@Entity
			@Table(name="Categorias")
			public class Categoria {
		1.3.- Mapeo de la llave primaria
		1.4.- Se añade anotacion @GeneratedValue, para indicar que sera autoincrementable
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)  --> En el caso de MySql
			private Integer id;
		1.5.- Creacion de atributos con su get y set
			private String nombre;
			private String descripcion;
************************************************************************************************************Creacion de Repositorio JPA
	1- Se crea paquete 			-->com.eareiza.repository
	2.- Se crea Interface 		--> CategoriasRepository
		2.1.- se extiende de la interfaz CrudRepository
			2.1.1.- Se le pasa como parametro el modelo que va a mapear el repositorio
				Categoria
			2.1.2.- Se le pasa el tipo de dato de la llave Primaria
				public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
			2.1.3.- Se le agrega la anotacion @Repository
				@Repository
				public interface categoriaRepository extends CrudRepository<Categoria, Integer> {
***************************************************************************Notacion @Autowired - Inyectando instancia de un repositorio
	1.- Se modifica JpaDemoApplication.java, Se crea atributo privado de tipo de la interfaz CategoriasRepository
	2.- Se le agrega Anotacion @Autowired
		@Autowired
		private CategoriasRepository repo;
**************************************************************************************Operaciones CRUD - Create - Persistencia de datos
	1.- Se crea el metodo guardar() de la clase --> JpaDemoApplication
		1.1.- Se instancia un objeto de tipo Categoria.java
		1.2.- Se le setan los atributos al obj creado
			private void guardar() {
				//Operaciones CRUD - Create - Persistencia de datos
				Categoria categoria = new Categoria();
				categoria.setDescripcion("Trabajos relacionados con contabilidad y finanzas");
				categoria.setNombre("Finanazas");
				repo.save(categoria);
				System.out.println(categoria);
			}
*******************************************************************************************Operaciones Cru - Read - Recuperacion por Id
	1.- Se crea metodo buscarPorId() en la clase JpaDemoApplication
		//Operaciones Cru - Read - Recuperacion por Id
		private void buscarPorId(int idCategoria) {
			Optional<Categoria> optional = repo.findById(idCategoria);
			if(optional.isPresent()) {
				System.out.println(optional.get());
			}else {
				System.out.println("Categoria no encontrada");
			}
		}
*****************************************************************************************Operaciones CRUD - Update - Actualizar entidad
	1.- Se crea metodo modificar()
		public void modificar(int idCategoria) {
		
		}
	2.- Se recupera la entidad que se va a modificar		
	3.- Se modifican los atributos
	4.- Se utiliza el metodo save para modificar la entidad
		public void modificar(int idCategoria) {
			Optional<Categoria> optional = repo.findById(idCategoria);
			if(optional.isPresent()) {
				System.out.println(optional.get());
				Categoria catTmp = optional.get();
				catTmp.setNombre("Ingneria de Software");
				catTmp.setDescripcion("Desarrollo de sistemas");
				repo.save(catTmp);
			}else {
				System.out.println("Categoria no encontrada");
		}
********************************************************************************Operaciones CRUD - Delete - Eliminar una entidad por id
	1.- Se crea metodo eliminar()
	2.- Se ejecuta metodo deleteById()
		private void eliminar(int idCategoria) {
			repo.deleteById(idCategoria);
		}
******************************************************************************Metodo count - Obtener cantidad de entidades de una tabla
	1.- Se crea metodo conteo 
		private void conteo() {
			int cantidad = (int) repo.count();
			System.out.println("Cantidad: "+cantidad);
		}
***************************************************************************************Metodo deleteAll -- Eliminar todos los registros
	1.- Se cre metodo eliminarTodos()
		private void eliminarTodos() {
			repo.deleteAll();
		}
*********************************************************************************Metodo findAllById - Recuperar varias entidades por id
	1.- Se cre metodo encontrarPorIds()
		1.1.- Se crea lista LinkedList<Categoria> para los ids de categorias
		1.2.- Se añaden los ids
		1.3.- Se llamam al metodo findAllById y se almacena en una variable iterable
		1.4.- Se recorre el obj iterable
			private void encontrarPorIds() {
				List<Integer> ids = new LinkedList<Integer>();
				ids.add(1);
				ids.add(8);
				ids.add(12);
				Iterable<Categoria> categorias = repo.findAllById(ids);
				for (Categoria categoria : categorias) {
					System.out.println(categoria);
				}
			}
***********************************************************************Metodo findAll - Recupera todas las entidades en un obj Iterable
	1.- Crear metodo buscarTodos()
		1.1.- Se recuperan todas las entidades con el metodo findAll() y se almacena en un obj iterable
		1.2.- Se recorre el objeto creado
			private void buscarTodos() {
				Iterable<Categoria> categorias = repo.findAll();
				for (Categoria categoria : categorias) {
					System.out.println(categoria);
				}
			}
*************************************************************************metodo existById() - Verificar si existe una entidad con un id
	1.- Se crea un metodo existeId()
		private boolean existeId(int id) {
			return repo.existsById(id);
		}
************************************************************************************Metodo saveAll - Guardar una coleccion de entidades
	1.- Se crea un metodo para crear una lista de categorias
		private List<Categoria> getlistaCategorias(){
			List<Categoria> lista = new LinkedList<Categoria>();
			Categoria Categoria1 = new Categoria();
			Categoria1.setNombre("Programador de BlackChain");
			Categoria1.setDescripcion("Trabajo relacionado con Bitcoins y Criptomonedas");
			
			Categoria categoria2 = new Categoria();
			categoria2.setNombre("Soldador");
			categoria2.setDescripcion("Trabajo relacionado con soldaduras");
			
			Categoria categoria3 = new Categoria();
			categoria3.setNombre("Ingeniero Industrial");
			categoria3.setDescripcion("Trabajos relacionados con ingenieria Industrial");
			
			lista.add(Categoria1);
			lista.add(categoria2);
			lista.add(categoria3);
			
			return lista;
		}
	2.- Se crea un metodo guardarTodas()
		public void guardarTodas(List entidades) {
			repo.saveAll(entidades);
		}
	3.- Se llama al metodo pasandole una lista de categorias
		guardarTodas(getlistaCategorias());
Seccion 29 SpringBoot&SpringDataJPA-Interfaz JpaRepository*****************************************************************************
	Interfaces que extienden la interfaz Repository
		La interfaz principal de Spring Data Repositories es la interfaz Repository, sin embargo en la
		práctica se utilizan principalmente cualquiera de las siguientes interfaces:

			 CrudRepository: Esta interfaz extiende la interfaz Repository y proporciona los métodos para
			las operaciones CRUD que actúan sobre la clase de modelo pasada como parámetro.
				 No tenemos que escribir nada de código SQL para crear, leer, actualizar y borrar registros.
				 La interfaz CrudRepository de Spring lleva dos parámetros:
					 Una clase de dominio o modelo (ej. Categoria, Vacante, etc).
					 El tipo del Id de la clase de dominio (el. Integer, Long)

			 PagingAndSortingRepository: Esta interfaz extiende la interfaz CrudRepository y agrega
			métodos adicionales para recuperar entidades usando paginación y ordenamiento.

			 JpaRepository: Esta Interfaz extiende la interfaz PagingAndSortingRepository y agrega
			funcionalidad específica para la tecnología JPA.
		
		Nota: En este curso, nos centraremos en la interfaz JpaRepository.	

	1.- Modificar la interface CategoriasRepository para que extienda de JpaRepository
		public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
*********************************************************************************************************Metodo findAll - JpaRepository
	Nota: Ete metodo devuelve un objeto tipo List
	1.- Se crea metodo buscarTodasJPA, el cual nos devuelve un obj tipo List
		private void buscarTodasJPA() {
			List<Categoria> lista = repo.findAll();
			for (Categoria categoria : lista) {
				System.out.println(categoria.getId()+ " " + categoria.getNombre());
			}
		}
************************************************************************************************Metodo deleteAllInBatch - JpaRepository
	Nota: Elimina todos los registros con una sola sentencia
	1.- Se crea metodo borrarTodasEnBloqueJPA()
		private void borrarTodasEnBloqueJPA() {
			repo.deleteAllInBatch();
		}
*********************************************************************************************Metodo findAll con ordenamiento (order by)
	Nota: busca todas las entidades ordenadas por una propiedad, por default es de forma ascendente
	1.- Crear metodo que busca todas las categorias con un ordenamiento
		private void buscarTodasOrdenadasJPA() {
			List<Categoria> lista = repo.findAll(Sort.by("nombre"));
			for (Categoria categoria : lista) {
				System.out.println(categoria.getId()+ " " + categoria.getNombre());
			}
		}
	2.- Buscar de forma descendente
		private void buscarTodasOrdenadasJPA() {
			List<Categoria> lista = repo.findAll(Sort.by("nombre").descending());
			for (Categoria categoria : lista) {
				System.out.println(categoria.getId()+ " " + categoria.getNombre());
			}
		}
**********************************************************************************************************Metodo findAll con Paginacion
	1.- Se crea metodo buscarTodasPaginacion()+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		private void buscarTodasPaginacionJPA() {
			Page<Categoria> page = repo.findAll(PageRequest.of(1, 5));
			for (Categoria categ: page.getContent()) {
				System.out.println(categ.getId() +" "+ categ.getNombre());
			}
		}
	2.- Utilizar metodo page.getTotalElement() y page.getTotalPages()
		private void buscarTodasPaginacionJPA() {
			Page<Categoria> page = repo.findAll(PageRequest.of(1, 5));
			System.out.println("Cantidad de Registros: "+page.getTotalElements());
			System.out.println("Cantidad de paginas: "+page.getTotalPages());
			for (Categoria categ: page.getContent()) {
				System.out.println(categ.getId() +" "+ categ.getNombre());
			}
		}
********************************************************************************************Metdo findAll con Paginacion y ordenamiento
	1.- Se crea metodo que realiza la paginacion de forma ordenada, por default es asc
		private void buscarTodasPaginacionOrdenadosJPA() {
		Page<Categoria> page = repo.findAll(PageRequest.of(0, 5, Sort.by("nombre").descending()));
		System.out.println("Cantidad de Registros: "+page.getTotalElements());
		System.out.println("Cantidad de paginas: "+page.getTotalPages());
		for (Categoria categ: page.getContent()) {
			System.out.println(categ.getId() +" "+ categ.getNombre());
		}
	}
Seccion 30 SpringBoot&&SpringDataJPA - Relaciones**************************************************************************************
*******************************************************************************************Creacion de Repositorio para entidad Vacante
	1.- Configurar clase de modelo
		1.1.- Se agrega anotacion @Entity y @Table
			
			@Entity
			@Table(name="vacantes")
			public class Vacante {
		1.2.- Se configura la clave primaria y la generacion de forma automatica con las anotaciones @Id y @GeneratedValue	
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
	2.- Se crea el repositorio VacantesRepository y se le extkiende de la interface JpaRepository pasandole como parametros 
	el objeto Vacante y el tipo Integer
		public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

		}	
************************************************************************************************************Anotacion @Transient de JPA
	1.- Se inyecta una dependencia del repositorio
		@Autowired
		private VacantesRepository repoVacantes;
	2.- Se crea metodo buscarVacantes()
		private void buscarVacantes() {
			List<Vacante> lista = repoVacantes.findAll();
			for (Vacante vacante : lista) {
				System.out.println(vacante.getId()+" "+vacante.getNombre());
			}
		}
	3.- Configurar para que ignore el atributo Categoria en la clase modelo Vacante, con la anotacion @Transient++++++++++++++++++++++++
		@Transient
		private Categoria categoria;
***********************************************************************************************Anotacion @OneToOne - Relacion Uno a Uno
	1.- Configuracion de realcion uno a uno en la clase modelo Vacante 
			//@Transient
			@OneToOne
			@JoinColumn(name="idCategoria")
			private Categoria categoria;
********************************************************************************************************************Guardar una Vacante
	1.- Crear metodo guardarVacante()
	2.- Crea un objeto Vacante y setearle las variables
	3.- Se utiliza el repositorio el metodo save para guardar la entidad
		public void guardarVacanteJPA() {
			Vacante vacante = new Vacante();
			vacante.setNombre("Profesor de Matematicas");
			vacante.setDescripcion("Escuela primaria solicita profesor para curso de matematicas.");
			vacante.setFecha(new Date());
			vacante.setSalario(8500.0);
			vacante.setEstatus("Aprobada");
			vacante.setDestacada(0);
			vacante.setImagen("escuela.png");
			vacante.setDetalles("<h1>Los requisitos para profesor de Matematicas</h1>");
			Categoria categoria = new Categoria();
			categoria.setId(15);
			vacante.setCategoria(categoria);
			repoVacantes.save(vacante);
		}
**********************************************************Repositorio para entidades de tipo Usuario y Perfil- Relacion muchos a muchos
	1.- Se crea entidad Perfil()
		@Entity
		@Table(name="Perfiles")
		public class Perfil {
			
			@Id
			@GeneratedValue(strategy= GenerationType.IDENTITY) //auto_increment MySql
			private Integer id;
			private String perfil;
	2.- Se crea entidad Usuario(), con get, set y contructor
		@Entity
		@Table(name="Usuarios")
		public class Usuario {
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY) //Auto_increment MySql
			private Integer id;
			private String username;
			private String nombre;
			private String email;
			private String password;
			private Integer estatus;
			private Date fechaRegistro;


	3.- Se crea repositorio PerfilRepository
		public interface PerfilesRepository extends JpaRepository<Perfil, Integer> {
	4.- Se crea repositorio UsuariosRepository
		public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
	5.- se inyectan las dependencias a los repositorios en la clase principal
		@Autowired
		private PerfilesRepository repoPerfil;
		
		@Autowired
		private UsuariosRepository repoUsuarios;


	6.- Se crea metodo crearPerfilesAplicacion()
		private void crearPerfilesAplicacion() {
			repoPerfil.saveAll(getPerfilesAplicacion());****************************************************************************
		}
	7.- Se crea metodo getPerfilesAplicacion()
		private List<Perfil> getPerfilesAplicacion(){
			List<Perfil> lista = new LinkedList<Perfil>();
			Perfil perfil1 = new Perfil();
			perfil1.setPerfil("SUPERVISOR");
			
			Perfil perfil2 = new Perfil();
			perfil2.setPerfil("ADMINISTRADOR");
			
			Perfil perfil3 = new Perfil();
			perfil3.setPerfil("USUARIO");
			
			lista.add(perfil1);
			lista.add(perfil2);
			lista.add(perfil3);
			return lista;
		}
***************************************************************************************Anotacion @ManyToMany - Relacion muchos a muchos
	1.- se crea atributo de tipo List<Pefil> con su get y set, en la entidad Usuario
		private List<Perfil> perfiles;

		public List<Perfil> getPerfiles() {
			return perfiles;
		}
		public void setPerfiles(List<Perfil> perfiles) {
			this.perfiles = perfiles;
		}
	2.- Se configura la relacion muchos a muchos al atributo, FetchType.EAGER para que al consultar la clase usuario tambien 
	vengan los datos de pefiles
		@ManyToMany(fetch=FetchType.EAGER)
	3.- Se agrega un @JoinTable, para configurar la tabla intermedia
		@JoinTable(name="UsuarioPerfil",
				joinColumns = @JoinColumn(name="idUsuario"), 
				inverseJoinColumns = @JoinColumn(name="idPerfil")
			)
	4.- se crea metodo para agregar pefil a la lista y que se guarde
		public void agregar(Perfil tempPerfil) {
			if(perfiles == null) {
				perfiles = new LinkedList<Perfil>();
			}
			perfiles.add(tempPerfil);
		}
	5.- Crear metodo crearUsuarioDosPerfil().java
		private void crearUsuarioDosPerfil() {
			Usuario user = new Usuario();
			user.setNombre("Elvis");
			user.setEmail("dirielfran@gmail.com");
			user.setFechaRegistro(new Date());
			user.setUsername("eareiza");
			user.setPassword("123456");
			user.setEstatus(1);
			
			Perfil perfil1 = new Perfil();
			perfil1.setId(2);
			

			Perfil perfil2 = new Perfil();
			perfil2.setId(3);
			
			user.agregar(perfil1);
			user.agregar(perfil2);
			
			repoUsuarios.save(user);
		}
**************************************************************************************Buscar usuario y desplegar sus perfiles asociados
	1.- Crear metodo buscarPerfilesUsuarios()
		private void buscarPerfilesUsuarios() {
		
			Optional<Usuario> optional = repoUsuarios.findById(4);
			if (optional.isPresent()) {
				Usuario usu = optional.get();
				System.out.println("Usuario: "+ usu.getNombre());
				System.out.println("perfiles asignados");
				for (Perfil perfil : usu.getPerfiles()) {
					System.out.println(perfil.getPerfil());
				}
			}else {
				System.out.println("usuario no encontrado");
			}
		}
Seccion 31 SpringBoot&SpringDataJPA - QueryMethod**************************************************************************************
	¿Qué son los query methods?
	Son métodos que permiten encontrar información (SELECT) en la base de datos y son DECLARADOS 
	en la interfaz del repositorio.
	Nota: Estos métodos solo se DECLARANen la interfaz, Spring Data JPA realiza la implementación 
	del método, dependiendo del NOMBRE DEL METODO y losPARAMETROS que recibe.
		Ejemplo: Método para recuperar las entidades de tipo Vacante, filtradas por el campo estatus:
			List<Vacante> findByEstatus(String estatus);		
***********************************************************************************************************************Keyword findBy()
	1.- Definir Metodo en el repositorio vacantesRepository
		List <Vacante> findByEstatus(String estatus);

	2.- Crear Metodo buscarVacantesPorEstatus ()
		private void buscarVacantesPorEstatus() {
		List<Vacante> vacante= repoVacantes.findByEstatus("Aprobada");
		System.out.println("El tamaño de lalista es: "+ vacante.size());
		for (Vacante vacante2 : vacante) {
			System.out.println(vacante2.getNombre());			
		}
****************************************************************************************************************************keyWord And
	1.- Se crea metodo buscarBacantesPorDestacadoEstatus()
		private void buscarBacantesPorDestacadoEstatus() {
			List<Vacante> lista = repoVacantes.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
			System.out.println(lista.size());
			for (Vacante vacante : lista) {
				System.out.println(vacante.getNombre());
			}
		}
	2.- Se crea la firma del metodo en el repositorio
		List<Vacante> findByDestacadoAndEstadoOrderByIdDesc(int destacado, String estado);
************************************************************************************************************************keyWord between
	1.- Se crea metodo buscarVacantesSalario()
		private void buscarVacantesSalario() {
			List<Vacante> lista = repoVacantes.findBySalarioBetweenOrderBySalarioDesc(7000, 14000);
			System.out.println(lista.size());
			for (Vacante vacante : lista) {
				System.out.println(vacante.getNombre() +" "+ vacante.getSalario());
			}
		}
	2.- Se crea la firma del metodo en el repositorio
		List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double s1, double s2);
*****************************************************************************************************************************keyWord in
	1.- Se crea metodo buscarVacantesVariosEstatus()
		private void buscarVacantesVariosEstatus() {
			String[] arreglo = new String[] {"Eliminada", "Creada"};
			List<Vacante> lista = repoVacantes.findByEstatusIn(arreglo);
			System.out.println(lista.size());
			for (Vacante vacante : lista) {
				System.out.println(vacante.getNombre() +" "+ vacante.getEstatus());
			}
		}
	2.- Se crea la firma del metodo en el repositorio
		List<Vacante> findByEstatusIn(String[] estatus);
Seccion 32 SpringBoot&SpringDataJPA - Integracion**************************************************************************************
**********************************************************************************************Configurar repositorio de Spring data JPA
	1.- Agregar las dependencias necesarias 
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.47</version>
		</dependency>
	2- Se configura el datasource del properties
		# DATASOURCE (MYSQL 5.7)
		spring.datasource.driver-class-name=com.mysql.jdbc.Driver
		spring.datasource.url=jdbc:mysql://localhost:3306/empleosdb?useSSL=false
		spring.datasource.username=root
		spring.datasource.password=admin
		#JPA
		spring.jpa.generate-ddl=false
		spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
		spring.jpa.show-sql=true
		# Table names physically
		spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
	3.- Actualizar las clases modelo, con las clases que modificamos en el proyecto jpa-demo.java
		Categoria, Perfil, Usuario, Vacante
	4.- Copiar el paquete de los repositorios
		com.eareiza.repository
***************************************************************************Crear implementacion de ICategoriaService con el repositorio
	1.- Se crea paquete com.eareiza.service.db 
	2.- Se crea la clase de servicio CategoriasServiceJPA.java, se le agrega la anotacion @Service y se implementan los metodos 
	declaradoe en la interface
		@Service
		public class CategoriasServiceJPA implements ICategoriasService {

	3.- Se añade la inyeccion de dependencias del repositorio
		@Autowired
		private CategoriasRepository categoriasRepo;

	4.- Se implementa el metodo guardar()
		@Override
		public void guardar(Categoria categoria) {
			categoriasRepo.save(categoria);
		}
	5.- Se implementa el metodo buscarTodas()
		@Override
		public List<Categoria> buscarTodas() {
			return categoriasRepo.findAll();
		}
	6.- Se implementa el metodo buscarPorId()
		@Override
		public Categoria buscarPorId(Integer idCategoria) {
			// TODO Auto-generated method stub
			Optional<Categoria> opcional = categoriasRepo.findById(idCategoria);
			if(opcional.isPresent()) {
				return opcional.get();
			}
			return null;
		}
*********************************************************************************************************************Anotacion @Primary
	@Primary, esta anotación nos permite indicar el bean default a inyectar en caso de que no se especifique ningún qualifier
	1.- Se añade la anotacion @Primary a la clase de servicio CategoriasServiceJPA, se le dice a spring que utilice la clase mapeada 
	en la inyeccion deindependencia
		@Service
		@Primary
		public class CategoriasServiceJPA implements ICategoriasService {...}
*******************************************************************************************************************Anotacion @Qualifier
	La anotación @Qualifier nos permite especificar el nombre del bean que se va a inyectar en el atributo.
	1.- Se agrega en el controlador CategoriasController.java la anotacion @Qualifier en la inyeccion de dependencia de la Clase de 
	servicio CategoriasServiceJPA, pasando como parametro el nombre de la implementacion que se inyectara con la primera 
	letra en minuscula
		@Autowired
		@Qualifier("categoriasServiceJPA")
		private ICategoriasService categoriasServices;
	2.- Se agrega en el controlador VacantesController.java la anotacion @Qualifier en la inyeccion de dependencia de la Clase de 
	servicio CategoriasServiceJPA, pasando como parametro el nombre de la implementacion que se inyectara con la primera 
	letra en minuscula
		@Autowired
		@Qualifier("categoriasServiceJPA")
		private ICategoriasService serviceCategorias;
*********************************************************************Crear Implementacion de la interface IVacanteService.java para BDs
	1.- Se crea clase de servicio VacantesServiceJPA.java y se implementa la interface IVacanteService
	2.- Se añade la anotacion @Service
		@Service
		@Primary
		public class VacantesServiceJPA implements IVacanteService {...}
	3.- Se añade inyeccion de dependencia al repositorio, en VacantesServiceJPA
		@Autowired
		private VacantesRepository vacantesRepo;
	4.- Se implementan los metodos
		@Override
		public List<Vacante> buscarTodas() {
			return vacantesRepo.findAll();
		}

		@Override
		public Vacante buscarPorId(Integer id) {
			Optional<Vacante> opcional = vacantesRepo.findById(id);
			if(opcional.isPresent()) {
				return opcional.get();
			}
			return null;
		}

		@Override
		public void guardar(Vacante vacante) {
			vacantesRepo.save(vacante);
		}
	5.- Se agrega la anotacion @Primary, en la clase de servicio para que sea la clase de servicio pr default
		@Primary
		public class VacantesServiceJPA implements IVacanteService {...}
	6.- Se modifica la vista listVacantes.html, para que muestre el nombre de la categoria
		 <td th:text="${vacante.categoria.nombre}"></td>
********************************************************************************************************Configuracion de serverTimezone
	1.- Se motifica el archivo Application.properties
		spring.datasource.url=jdbc:mysql://localhost:3306/empleosdb?useSSL=false&serverTimezone=America/Argentina/Buenos_Aires&allowPublicKeyRetrieval=true
***********************************************************************************************************Thymeleaf Expresion th:utext
	Si queremos que Thymeleaf respete nuestras etiquetas XHTML y no las escape, tendremos que usar un atributo diferente:
		th:utext(para "texto sin escape")
	1.- Se modifica la etiqueta parrafo de la vista detalle.html
		<p class="card-text" th:utext="${vacante.detalles}"></p>	
****************************************************************************(@ModelAttribute)Implementacion del metodo buscarDestacadas
	1.- Se crea la firma del metodo en la interface IVacantesService.java que regrese una lista de objs Vacante
		List<Vacante> buscarDestacadas();
	2.- Se implementa el metodo buscarDestacadas() en VacantesServiceJPA, utilizando metodo de consulta del repositorio
		@Override
		public List<Vacante> buscarDestacadas() {
			return vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
		}
	3.- Se modifica metodo mostrarHome() del HomeController.java, para que busque todas las bacantes destacadas
		@GetMapping("/")
		public String mostrarHome(Model modelo) {
			List<Vacante> vacantes = serviceVacantes.buscarDestacadas();
			modelo.addAttribute("vacantes", vacantes);
			return "home";
		}
	4.- Se implementa un metodo que agregue la lista de vacantes al modelo para todos los metodos del controlador HomeController.java
	con la anotacion @ModelAttribute
		@ModelAttribute
		public void setGenericos(Model modelo) {
			modelo.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
		}
*************************************************************************************Configurar recursos estaticos de rutas adicionales
	1.- Se crea un nuevo paquete para las configuraciones
		com.eareiza.config
	2.- Se crea clase de configuracion --> WebConfig.java en el paquete creado
	3.- Se le agrega a la clase la anotacion @Configuration ***************************************************************************
	4.- Se implementa la interfaz WebMVCConfigurer
	5.- se sobreescribe el metodo addResourceHandlers() de la interfaz WebMVCConfigurer
		@Value("${empleosapp.ruta.imagenes}")
		private String rutaImagenes;
		
		
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			//registry.addResourceHandler("/logos/**").addResourceLocations("file:/empleos/img-vacantes/");// Linux
			//registry.addResourceHandler("/logos/**").addResourceLocations("file:c:/empleos/img-vacantes/"); // Windows
			registry.addResourceHandler("/logos/**").addResourceLocations("file:"+rutaImagenes); // Windows
		
		}
	6.- Se actualiza las vistas para que busque en la ruta configurada
		6.1.- Se modifica Home.html
			<img class="rounded mx-auto d-block" th:src="@{/logos/{img} (img=${vacante.imagen})}" alt="Generic placeholder image" width="220" height="220">            
        6.2.- Se modifica Detalle.html
        	<img class="rounded mx-auto d-block" th:src="@{/logos/{img} (img=${vacante.imagen})}" alt="Generic placeholder image" width="220" height="220">            
    7.- Se configura la ruta configurada en el application.properties como atributo en la clase WebConfig.java
    	7.1.- Se agrega el atributo
    		private String ruta;
    	7.2.- Se le agrega la anotacion @Value como para metro se le pasa el key configurado en el Application.properties
    		@Value("empleaosApp.ruta.imagenes")
			private String ruta; 
		7.3.- Se concatena la rulta a la localizacion de los recursos
			registry.addResourceHandler("/logos/**").addResourceLocations("file:"+ruta); // Windows
********************************************************************************************Configurar boton HTML para eliminar Vacante
	1.- Se declara un nuevo metodo en la interface IVacanteService --> void eliminar(int idVacante)
	2.- Se agregan las implementaciones en VacantesServiceJPA.java
		@Override
		public void eliminar(int idVacante) {
			vacantesRepo.deleteById(idVacante);		
		}	
	3.- Se modifica el metodo eliminar() de VacantesController.java
		3.1.- Se modifica el @GetMapping para que recupere el id de forma dinamica
			@GetMapping("/delete/{id}")
		3.2.- Se le añade la anotacion @PathVariable para que recupere el id
			public String eliminar(@PathVariable("id") int idVacante, Model modelo) {
		3.3.- Se llama al metodo eliminar de mi clase de servicio ya instanciada
			serviceVacantes.eliminar(idVacante);
		3.4.- Se modifica el metodo para que redireccione a vacantes/index
			return "redirect:/vacantes/index";
		3.5.- Se agrega mensaje en la vista de registro eliminado
			3.5.1.- Se agrega a los parametros del metodo eliminar un parametro RedirectAttributes 
				public String eliminar(@PathVariable("id") int idVacante, Model modelo, RedirectAttributes attr) {
			3.5.2.- Se agrega un atributo flash
				attr.addFlashAttribute("msg", "Registro eliminado.");

		@GetMapping("/delete/{id}")
		public String eliminar(@PathVariable("id") int idVacante, Model modelo, RedirectAttributes attr) {
			serviceVacantes.eliminar(idVacante);
			System.out.println("Vacante eliminada con el id "+idVacante);
			attr.addFlashAttribute("msg", "Registro eliminado.");
			modelo.addAttribute("id", idVacante);
			return "redirect:/vacantes/index";
		}
**********************************************************************************************Configurar boton HTML para editar Vacante
	1.- Se crea un metodo editVacante() en el controlador VacantesController.java
		@GetMapping("/edit/{id}")
		public String editarVacante(@PathVariable("id") int idVacante, Model modelo) {
			Vacante vacante = serviceVacantes.buscarPorId(idVacante);
			modelo.addAttribute("vacante", vacante);
			return "/vacantes/formVacante";
		}
	2.- Se modifica la vista listVacantes.html, para que el boton editar se direccione al metodo creado
		<a th:href="@{/vacantes/edit/{id} (id=${vacante.id})}" class="btn btn-success btn-sm" role="button" title="Editar el registro."><i class="fas fa-pencil-alt" aria-hidden="true"></i></a>
                    
    3.- Agregan datos al modelo que son comunes en el controlador con la anotacion @ModelAttribute a nivel de metodo
    	@ModelAttribute
		public void setGenericos(Model modelo) {
			modelo.addAttribute("categorias", serviceCategorias.buscarTodas());
		}
	4.- Se modifica la vista formVacante.html para que muestre la imagen del logo
		<img class="rounded mx-auto d-block" th:src="@{/logos/{img} (img=${vacante.imagen})}" alt="Generic placeholder image" width="200" height="200">            
    5.- Se crea un campo de tipo hidden en la vista formVacantes.html, para que envie el id de la vacante 
    	<input type="hidden"  th:field="*{id}">         
******************************************************************************************Configurar boton HTML para eliminar Categoria
	1.- Se declara un nuevo metodo en la interface ICategoriasService --> void eliminar(int idCategoria);
	2.- Se agregan las implementaciones en CategoriasServiceJPA.java
		@Override
		public void eliminar(int idCategoria) {
			categoriasRepo.deleteById(idCategoria);		
		}
	3.- Se crea el metodo eliminarCategoria() de CategoriasController.java
		3.1.- Se añade metodo @RequestMapping com el method Get y con un parametro dinamico
			@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
		3.2.- Se le añade la anotacion @PathVariable para que recupere el id
			public String eliminarCategoria(@PathVariable("id") int idCategoria){....}
		3.3.- Se llama al metodo eliminar de mi clase de servicio ya instanciada
			categoriasServices.eliminar(idCategoria);
		3.4.- Se modifica el metodo para que redireccione a categorias/index
			return "redirect:/categorias/index";
		3.5.- Se agrega mensaje en la vista de registro eliminado
			3.5.1.- Se agrega a los parametros del metodo eliminar un parametro RedirectAttributes 
				public String eliminarCategoria(@PathVariable("id") int idCategoria, RedirectAttributes attr ) { 
			3.5.2.- Se agrega un atributo flash
				attr.addFlashAttribute("msg", "Categoria eliminada.");
		3.6.- Se le añade un try{}catch{}, para el amnejo de excepcion por clave foranes
			try {
				categoriasServices.eliminar(idCategoria);
				attr.addFlashAttribute("msg", "Categoria eliminada.");
				System.out.println("Se elimino la categoria "+idCategoria);
			} catch (Exception e) {
				attr.addFlashAttribute("msg", "Nos puede eliminar la categoria, tiene vacantes asociadas.");
			}  
		3.7.- Se modifica la vista listCategorias
			<a th:href="@{/categorias/delete/{id} (id=${categoria.id})}" onclick="return confirm('¿Estas seguro?')" class="btn btn-success btn-sm" role="button" title="Eliminar"><i class="fas fa-trash" aria-hidden="true"></i></a>
		@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
		public String eliminarCategoria(@PathVariable("id") int idCategoria, RedirectAttributes attr ) {        
			try{
				categoriasServices.eliminar(idCategoria);
				attr.addFlashAttribute("msg", "Categoria eliminada.");
				System.out.println("Se elimino la categoria "+idCategoria);
			}catch (Exception e) {
				attr.addFlashAttribute("msg", "Nos puede eliminar la categoria, tiene vacantes asociadas.");
			}  
			return "redirect:/categorias/index";
		}
********************************************************************************************Configurar boton HTML para editar Categoria
	1.- Se crea un metodo editCategoria() en el controlador CategoriasController.java
		@GetMapping("/update/{id}")
		public String editCategoria(@PathVariable("id") int idCategoria, Model modelo) {
			Categoria categoria = categoriasServices.buscarPorId(idCategoria);
			modelo.addAttribute("categoria", categoria);
			return "categorias/formCategorias";
		}
	2.- Se modifica la vista listCategorias.html, para que el boton editar se direccione al metodo creado
		<a th:href="@{/categorias/update/{id} (id=${categoria.id})}" class="btn btn-success btn-sm" role="button" onclick="return confirm('¿Estas seguro de modificar?')" title="Editar"><i class="fas fa-pencil-alt" aria-hidden="true"></i></a>
                               
	3.- Se crea un campo de tipo hidden en la vista formVacantes.html, para que envie el id de la vacante 
    	<input type="hidden"  th:field="*{id}"> 
**************************************************************************************Ejercicio de operaciones CRUD con Spring Data JPA
	1. Usar la plantilla del archivo formRegistro.html
		1.1.- Se configurar namespace thymeleaf en home.html
			<html xmlns:th="http://www.thymeleaf.org">
		1.2.- Se referencian los archivos estaticos css, js, bootstrap y se configuran las imagenes
			<!-- Bootstrap core CSS -->
		    <link th:href="@{/bootstrap/css/bootstrap.min.css}" rel="stylesheet">
		    <!-- Custom styles for this template -->
		    <link th:href="@{/bootstrap/css/jumbotron.css}" rel="stylesheet">
		    <link th:href="@{/bootstrap/css/sticky-footer-navbar.css}" rel="stylesheet">

		    <script th:src="@{/bootstrap/js/bootstrap.min.js}"></script> 
		1.3.- Se incluye el fragmento en las vistas
		    <header th:insert="fragments/menu :: menuPrincipal"> </header>
		    <footer class="footer" th:insert="fragments/footer :: PiePagina"></footer>
		1.4.- Se configura el action del formulario
		    <form th:action="@{/save}" method="post" th:object="${usuario}">
		1.5.- Se configuran los input
	2.- Se crea metodo en UsuarioController.java para que renderice la vista formRegistro.html
		@GetMapping("/create")
	    public String crearUsuario(Usuario usuario, Model modelo) {
	    	return "usuarios/formRegistro";
	    }
	3.- Se crea metodo en el conotrolador UsuarioController.java para guardar el usuario
		@PostMapping("/save")
		public String guardarUsuario(Usuario usuario) {
			System.out.println("Usuario: "+usuario);
			return "usuarios/listUsuarios";
		}
	4.- Se crea firma en la interface IUsuarioService.java
		void guardar(Usuario usuario);
	5.- Se crea la clase de servicio UsuariosServiceJPA
		public class UsuariosServiceJPA implements IUsuariosService {

			@Override
			public void guardar(Usuario usuario) {
				// TODO Auto-generated method stub

			}

			@Override
			public void eliminar(Integer idUsuario) {
				// TODO Auto-generated method stub

			}

			@Override
			public List<Usuario> buscarTodos() {
				// TODO Auto-generated method stub
				return null;
			}

		}
	6.- Se le agrega la anotacion @Service a la clase de servicio
		@Service
		public class UsuariosServiceJPA implements IUsuariosService {..}
	7.- Se crea inyeccion de dependencia del repositorio UsuariosRepository
		@Autowired
		private UsuariosRepository usuariosRepo;
	8.- Se implementa el metodo guardar() y buscarTodos()
		8.1.- Se crea metodo guardar()
			@Override
			public void guardar(Usuario usuario) {
				usuariosRepo.save(usuario);
			}
		8.2.- Se crea metodo buscarTodos()
			@Override
			public List<Usuario> buscarTodos() {
				return usuariosRepo.findAll();
			}	
	9.- Se crea inyeccion de dependencias de la clase de servicio IUsuariosService, en el HomeController
		@Autowired
		private IUsuariosService servicesUsuario;


	10.- Se modifica metodo guardarUsuario() en HomeController.java
		@PostMapping("/save")
		public String guardarUsuario(Usuario usuario, BindingResult result, RedirectAttributes attr) {
			usuario.setEstatus(1);
			usuario.setFechaRegistro(new Date());
			Perfil perfil = new Perfil();
			perfil.setId(3);
			usuario.agregar(perfil);
			servicesUsuario.guardar(usuario);		
			attr.addFlashAttribute("msg", "El registro fue guardado correctamente.");
			return "redirect:/usuarios/index";
		}

	11.- Se crea inyeccion de dependencia de IUsuariosService en UsuariosController.java
		@Autowired
    	private IUsuariosService servicesUsuarios;
	12.- Se crea metodo mostrarIndex(), en UsuariosController para que renderice la lista de usuarios
	    @GetMapping("/index")
		public String mostrarIndex(Model modelo) {
	    	List<Usuario> usuarios = servicesUsuarios.buscarTodos();
	    	modelo.addAttribute("usuarios", usuarios);
	    	return "usuarios/listUsuarios";
		}
	13.- Se realizan las configuraciones de la vista listUsuarios.html
		13.1.- Se configura Thymeleaf
			/*<html xmlns:th="http://www.thymeleaf.org">*/
		13.2.- Se configuran los archivos los archivos estaticos
			 <!-- Bootstrap core CSS -->
		    <link th:href="@{/bootstrap/css/bootstrap.min.css}" rel="stylesheet">
		    <!-- Custom styles for this template -->
		    <link th:href="@{/bootstrap/css/jumbotron.css}" rel="stylesheet">
		    <link th:href="@{/bootstrap/css/sticky-footer-navbar.css}" rel="stylesheet">

		   	<script th:src="@{/bootstrap/js/bootstrap.min.js}"></script> 
		13.3.- Se insertan los fragmentos
			<header th:insert="fragments/menu :: menuPrincipal"></header>
			<footer class="footer" th:insert="fragments/footer :: piePagina"></footer>
		13.4.- Se renderiza la lista de usurios de forma dinamica
			13.4.1.- Se agrega un foreach de thymeleaf para que despliegue de forma dinamica las filtras
				<tr th:each="usuario : ${usuarios}">
			13.4.2.- Se modifican las columnas para que rendericen de forma dinamica los datos
				 <td th:text="${usuario.nombre}"></td>
	              <td th:text="${usuario.username}"></td>                  
	              <td th:text="${usuario.email}"></td>
	              <td th:text="${usuario.estatus}"></td>
	              <td>
	    13.5.- Se crea boton que lleva al formulario formRegistro.html, en la vista
	    	 <a class="btn btn-primary" th:href="@{/create}" title="Crear nuevo usuario" role="button"><i class="fas fa-file" aria-hidden="true"></i> Nuevo</a>                      
    	13.6.- Se configura vista para que muestre mensaje desde el formulario
    		<div th:if="${msg != null}" class='alert alert-success' th:text="${msg}" role='alert'></div>

    14.- Se le añaden errores y validaciones al formulario de registro formRegistro.html
    	14.1.- Se modifica Usuario.java, se crean los validadores en el bean
			@Size(min = 3, max = 8, message="El código del producto tiene que tener entre 3 y 8 caracteres")
			private String password;

			@NotEmpty(message="El nombre del producto es obligatorio")
			private String nombre;
	
		14.2.- Se modifica el clontrolador HomeController.java, AÑADIR VALIDACIÓN A LOS CONTROLADORES
			Se agrega anotacion @Valid, para validar el bean  --> @Valid Usuario usuario
			Se agrega validacion de errores del BindingResult
			Se agrega try catch que en caso de exxcepcion añade un objeto de error al BindingResult
			ej:
			@PostMapping("/save")
			public String guardarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr, Model modelo) {
				if(result.hasErrors()) {
					return "usuarios/formRegistro";
				}
		    	usuario.setEstatus(1);
				usuario.setFechaRegistro(new Date());
				Perfil perfil = new Perfil();
				perfil.setId(3);		
				try {
					usuario.agregar(perfil);
					servicesUsuario.guardar(usuario);	
				}catch(Exception e) {
					result.addError(new ObjectError("excepcion",e.getMessage() ));
					return "usuarios/formRegistro";
				}		
				attr.addFlashAttribute("msg", "El registro fue guardado correctamente.");
				return "redirect:/usuarios/index";
			}


























		3.- Se modifica la vista formRegistro.html,MOSTRAR LOS ERRORES EN LA VISTA CON THYMELEAF	
			<div th:object="${usuario}" th:remove="tag">
	 		    <ul th:if="${#fields.hasErrors('*')}" class="alert alert-danger" role="alert">
			        <li th:each="error : ${#fields.errors('*')}" th:text="${error}"></li>
			    </ul>
			</div>			
	15.- Configuracion del boton de eliminar de la vista listUsuarios.html
		15.1.- Se modifica la vista listUsuarios.html
			<a th:href="@{/usuarios/delete/usuario/{id} (id = ${usurio.id})}" onclick="return confirm('¿Estas seguro de elminar el registro?')" class="btn btn-success btn-sm" role="button" title="Eliminar el registro."><i class="fas fa-trash" aria-hidden="true"></i></a>
        15.2.- Se modifica la Interface IUsuariosService.java, se le agrega la firma al metodo eliminar
        	void eliminar(Integer idUsuario);
        15.3.- Se implemeta el metodo eliminar en la clase de servicio UsuariosServiceJPA.java
        	@Override
			public void eliminar(Integer idUsuario) {
				usuariosRepo.deleteById(idUsuario);
			}
		15.4.- Se modifica UsuariosController.java, se modififica el metodo eliminar()
			@GetMapping("/delete/{id}")
			public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attr) {		    	
		    	servicesUsuarios.eliminar(idUsuario);
		    	attr.addFlashAttribute("msg","El registro fue eliminado con exito.");
				return "redirect:/usuarios/index";
			}
	16.- Configuracion del boton editar de la vista listUsuarios.html
		16.1.- Se modifica la vista listUsuarios.html
			<a th:href="@{/uausarios/edit/{id} (id=${usuario.id})}" class="btn btn-success btn-sm" role="button" title="Editar el registro."><i class="fas fa-pencil-alt" aria-hidden="true"></i></a>
		16.2.- Se modifica la Interface IUsuariosService.java, se le agrega la firma de metodo buscarPorId()
			Usuario buscarProId(Integer idUsuario);
		16.3.- Se implementa el metodo en la clase de servicio UsuariosServiceJPA.java
			@Override
			public Usuario buscarProId(Integer idUsuario) {
				Optional<Usuario> op = usuariosRepo.findById(idUsuario);
				if (op.isPresent()) {
					return op.get();
				}
				return null;
			}
		16.4.- Se modifica el controlador UsuariosController.java
			@GetMapping("/edit/{id}")
		    public String editUsuario(@PathVariable("id") int idUsuario, Model modelo) {
		    	Usuario usuario = servicesUsuarios.buscarProId(idUsuario); 	
		    	modelo.addAttribute("usuario", usuario);
		    	return "usuarios/formRegistro";
		    }
	17.- Configuracion del boton de editar
		17.1.- Se configura la vista listUsuarios.html con el boton editar
			<a th:href="@{/usuarios/edit/{id} (id=${usuario.id})}" class="btn btn-success btn-sm" role="button" title="Editar el registro."><i class="fas fa-pencil-alt" aria-hidden="true"></i></a>
		17.2.- Se modifica el formRegistro.html, se agrega un campo hidden para que el metodo save reconozca que debe updatear
			<input th:type="hidden"   th:field="*{id}">   
	18.- Configuracion de boton bloquear
		18.1.- Se modifica la vista listUsuarios.html
			<a th:href="@{/usuarios/look/{id} (id=${usuario.id})}" onclick="return confirm('¿Quiere bloquear el acceso al usuario?')" class="btn btn-success btn-sm" role="button" title="Bloquear el acceso al sistema para este usuario."><i class="fas fa-lock" aria-hidden="true"></i></a>	
        18.2.- Se modifica UsuariosController.java, se crea metodo que bloquea el usuario.
        	@GetMapping("/look/{id}")
		    public String lookUsuario(@PathVariable("id")int idUsuario, RedirectAttributes attr) {
		    	Usuario usuario = servicesUsuarios.buscarProId(idUsuario);
		    	usuario.setEstatus(0);
		    	servicesUsuarios.guardar(usuario);
		    	attr.addFlashAttribute("msg","El usuario fue bloqueado.");
		    	return "redirect:/usuarios/index";
		    }
	19.- Configuracion de boton desbloquear
		18.1.- Se modifica la vista listUsuarios.html
			<a th:href="@{/usuarios/unlook/{id} (id=${usuario.id})}" onclick="return confirm('¿Desea desbloquear el usuario?')" class="btn btn-success btn-sm" role="button" title="Permitir el acceso al sistema a este usuario."><i class="fas fa-unlock" aria-hidden="true"></i></a>
        18.2.- Se modifica UsuariosController.java, se crea metodo que desbloquea el usuario.
        	@GetMapping("/unlook/{id}")
		    public String lookUsuario(@PathVariable("id")int idUsuario, RedirectAttributes attr) {
		    	Usuario usuario = servicesUsuarios.buscarProId(idUsuario);
		    	usuario.setEstatus(1);
		    	servicesUsuarios.guardar(usuario);
		    	attr.addFlashAttribute("msg","El usuario fue desbloqueado.");
		    	return "redirect:/usuarios/index";
		    }
*********************************************************************************************Implementacion Formulario Busqueda Vacante
	1.- Se modifica el home controller para que se realice inyeccion de dependencias de CategoriasServiceJPA;
		@Autowired
		private ICategoriasService serviceCategorias;
	2.- Se modifica metodo setgenericos(), agregando al modelo la lista de categorias para que este en el contexto.
		@ModelAttribute
		public void setGenericos(Model modelo) {
			modelo.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
			modelo.addAttribute("categorias", serviceCategorias.buscarTodas());
		}

	3.- Se modifica la vista home.html, se nodifica el select para que genere las opciones de forma dinamica, con los datos de la 
	lista de categorias
		<select class="custom-select custom-select mb-3">
              <option th:value="${null}" selected>Seleccione una categoría</option>
              <option th:each="categoria : ${categorias}" 
              			th:value="${categoria.id}"
              			th:text="${categoria.nombre}"></option>             
        </select>&nbsp;
    4.- Configuracion del DataBinding
    	4.1.- Se agrega al modelo un objeto de tipo Vacante, por medio del metodo setGenericos(), se crea un objeto tipo Vacante y 
    	se agrega al modelo
    		 @ModelAttribute
			public void setGenericos(Model modelo) {
		    	Vacante vacanteSerch  = new Vacante();
				modelo.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
				modelo.addAttribute("categorias", serviceCategorias.buscarTodas());
				modelo.addAttribute("vacanteSearch", vacanteSerch);
			}
		4.2.- Se modifica la vista home.html, se modifica el formulario para mapearle el objeto Vacante agregado al modelo, por medio del 
		atributo object de Thymeleaf
			/*<form class="form-inline" th:object="${vacanteSearch}">*/
		4.3.- Se configura input th:field="*{descripcion}"
			<input type="text" class="form-control mb-3" th:field="*{descripcion}" id="exampleFormControlInput1" placeholder="Escriba una búsqueda">&nbsp;
        4.4.- Se configura el select  th:field="*{categoria.id}"
        	<select class="custom-select custom-select mb-3" th:field="*{categoria.id}">
        4.5.- Configuracion del action del formulario
        	4.5.1.- Se configura el action --> th:action="@{/serch}"********************************************************************
        	4.5.2.- Se configura el metodo de envio  -->method="get"	
        		<form class="form-inline" th:object="${vacanteSearch}" th:action="@{/search}" method="get">
   	5.- Se crea metodo en el HomeController.java que responda a la url serch
   		5.1.- Se crea metodo buscar(), agregando el metodo de mapeo
   			@GetMapping("/search")
		    public String buscar() {
		    	return "";
		    }
		5.2.- Se le agrega a los parametros un objeto de tipo Vacante y la anotacion @ModelAttribute() mapeada con el objeto de dataBinding serch
			public String buscar(@ModelAttribute("vacanteSearch")Vacante vacante) {
	6.- Se crea un metodo para que se resetee el atributo imagen que viene por defecto en el objeto Vacante 
		6.1.- Se modifica el bean Vacante.java, Se crea metodo en el bean, resetee el valor 
			public void resetImagen() {
				this.imagen = null;
			}
		6.2.- Se modifica el metodo setGenericos(), para que ponca el atributo imagen del objeto vacante en null, vacanteSerch.resetImagen();
			 @ModelAttribute
			public void setGenericos(Model modelo) {
		    	Vacante vacanteSerch  = new Vacante();
		    	vacanteSerch.resetImagen();
				modelo.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
				modelo.addAttribute("categorias", serviceCategorias.buscarTodas());
				modelo.addAttribute("vacanteSearch", vacanteSerch);
			}
	7.- Se modifica la interface IVacanteService.java (Metodo QueryByExemple)*********************************************************
		QueryByExemple me permite realizar una consulta donde el where de la consulta se formaran de manera dinamica en base a los 
		atributos not null de la clase de modelo que le pasemos como parametro
			List<Vacante> buscarByExample(Example<Vacante> example); //Se importa obj Exanple de spring
	8.- Se modifica VacantesServiceJPA.java, Se implementa el metodo buscarByExample()
		@Override
		public List<Vacante> buscarByExample(Example<Vacante> example) {
			return vacantesRepo.findAll(example);
		}	
	9.- Se modifica HomeController.java, semodifica el metodo buscar()
		@GetMapping("/search")
	    public String buscar(@ModelAttribute("vacanteSearch")Vacante vacante, Model modelo) {
	    	System.out.println("La vacante: "+vacante);
	    	//Se crea obj de tipo Example
	    	Example<Vacante> example = Example.of(vacante);
	    	//Se crea lista utilizando el metodo buscarByExample y pasandole el obj de tipo Vacante
	    	List<Vacante> listVacante = serviceVacantes.buscarByExample(example);
	    	//Se agrega al modelo la lista
	    	modelo.addAttribute("vacantes", listVacante);
	    	return "home";
		}
	10.- Se cre metodo que setee el valos de la caja de texto en null en caso de este vacio, para que no afecte la consulta
		 /*
	     * InitBinder para String, si lo detecta vacio en el data Binding los setea a NULL
	     * */
	    @InitBinder
	    public void initBinder(WebDataBinder binder) {
	    	binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	    }
	11.- Se modifica el HomeController.java, se modifica el metodo buscar para que busque la vacante con el operador 
	like y no con el =, con el objeto ExampleMatcher
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		11.1.- Se modifica el obj example para que contenga el martcher
			Example<Vacante> example = Example.of(vacante, matcher); 
***********************************************************************Implementar paginacion para vacantes con Spring MVC y Spring JPA
	1.- Se modifica la interfaz IVacanteService, se crea la firma al metodo buscarTodas()
		Page<Vacante> buscarTodas(Pageable pagina);
	2.- Se modifica VacantesServiceJPA.java, Se implementa el metodo buscarTodas() en la clase de servicios
		@Override
		public Page<Vacante> buscarTodas(Pageable pagina) {
			return vacantesRepo.findAll(pagina);
		}	
	3.- Se modifica el controlador VacantesController.java
		@GetMapping("/indexPaginado")
		public String mostrarIndexPaginado(Model modelo, Pageable pagina) {
			//Se crea objeto de tipo page que almacena la lista de vacantes
			Page<Vacante> listaVacante = serviceVacantes.buscarTodas(pagina);
			//Se garega la lista al modelo
			modelo.addAttribute("vacantes", listaVacante);
			return "/vacantes/listVacantes";
		}	
	4.- Se modifica el Application.Properties con la Propiedad para especificar el tamaño de la paginacion
		spring.data.web.pageable.default-page-size=5
	5.- Se modifica menu.html para que nos dirija al metodo de paginacion
		<li class="nav-item"><a class="nav-link active" th:href="@{/vacantes/indexPaginado}">Vacantes</a>
*********************************************************************Implementar paginacion para categorias con Spring MVC y Spring JPA
	1.- Se modifica la interfaz ICategoriasService, se crea la firma al metodo buscarTodas()
		Page<Categoria> buscarTodas(Pageable pagina);
	2.- Se modifica CategoriasServiceJPA.java, Se implementa el metodo buscarTodas() en la clase de servicios
		@Override
		public Page<Categoria> buscarTodas(Pageable pagina) {
			return categoriasRepo.findAll(pagina);
		}	
	3.- Se modifica el controlador VacantesController.java
		@GetMapping("/indexPaginado")
		public String mostrarIndexPaginado(Model modelo, Pageable pagina) {
			//Se crea objeto de tipo page que almacena la lista de vacantes
			Page<Categoria> listacategorias = serviceVacantes.buscarTodas(pagina);
			//Se garega la lista al modelo
			modelo.addAttribute("categorias", listacategorias);
			return "/categorias/listCategorias";
		}	
	4.- Se modifica menu.html para que nos dirija al metodo de paginacion
		<li class="nav-item"><a class="nav-link active" th:href="@{/categorias/indexPaginado}">Vacantes</a>
*************************************************************************************************Aplicar formato a fechas con Thymeleaf
	1.- Se modifica la vista home.html, para dale formato a la fecha de vacante publicadas
		<span th:text="${#ates.format(vacante.fecha,'dd-MM-yyyy')}">

	Ejemplos de otros formatos de fecha
		<h1>Formatos de Fechas</h1>
		<p th:text="${#dates.format(vacante.fecha, 'dd-MM-yyyy HH:mm')}"></p>
		<p th:text="${#dates.format(vacante.fecha, 'yyyy-MM-dd HH:mm')}"></p>
Spring Boot y Spring Security**********************************************************************************************************
	Spring Security------------------------------------------------------------------------------------------------------	
		Es un framework de seguridad (módulo de Spring) que permite aplicar seguridad a tus aplicaciones desarrolladas con Spring.
		En este curso integraremos Spring Securitycon Spring Bootpara aplicar Seguridad en Aplicaciones Web.
		Spring Security aplica 2 tipos de seguridad:
			Autenticación: ¿Es un usuario válido para acceder a la aplicación?
			Autorización: ¿El usuario tiene permisos (ROL) para acceder al recurso solicitado?
		La seguridad es aplicada a nivel de Petición Web (HTTP Request)y a nivel de Invocación de Métodos.
		Spring Security esta basado en Spring Framework. Internamente utiliza:
			Inyección de Dependencias (DI)
			Programación orientada a aspectos (AOP).
		En aplicaciones web Spring Security utiliza Servlet Filters para aplicar seguridad a las peticiones web y 
		restringir el acceso a nivel de URL.
	Spring Security - Servlet Filter-------------------------------------------------------------------------------------
		Spring Security utiliza varios Servlet Filters para filtrar las peticiones web.
		Los Servlet Filters son componentes (Interceptors) ejecutados antes (pre-process) y despúes (post-process) 
		de la petición web.
******************************************************************************************Se agregan dependencias en el archivo POM.xml
	Por defecto, con solo agregar la dependencia de Spring Security se aplicará la siguiente configuración:
	Será requerida autenticación para todas las URLs.
	Spring generará un formulario HTML de login de forma automática (COMPLETAMENTE FUNCIONAL).
	Se agregará la configuración necesaria para prevenir ataques de tipo CSRF (Cross-site request forgery).
	Se creará 1 usuario (username: user) en memoria con acceso todas las URLs. La contraseña es generada automáticamente y 
	es desplegada en el log de la aplicación (console).
	
	1.- Se modifica POM.xml, se agrega dependencia para trabajar con spring Security
		<!--Requerido para trabajar Spring Security -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
	2.- Se modifica POM.xml, se agrega dependencia para trabajar con spring Security tags en Thymeleaf
		<!--Requerido para trabajar Thymeleaf Spring Security Tags -->
		<dependency>
			<groupId>org.thymeleaf.extras</groupId>
			<artifactId>thymeleaf-extras-springsecurity5</artifactId>
		</dependency>
*******************************************************************************************************Spring Security - Cerrar Seccion
	La configuración de Spring Security por defecto también incluye la funcionalidad para cerrar la sesión en una aplicación web.
		Para incluir esta funcionalidad, en una vista, solo hay que agregar un link HTML a la URL “/logout”.
		Utilizando thymeleaf el link quedaría así:
		Al hacer clic en el botón aparecérá un cuadro de diálogo para confirmar
***********************************************************************************Personalizar Usuario y contraseña de Spring Security
	1.- Se modifica el archivo Application.properties, para configurar usuario y contraseña estaticas
	spring.security.user.name=eareiza
	spring.security.user.password=masterkey
***************************************************************************************Recuperar Usuario y Roles desde la Base de Datos
	1.- Se  crean las tablas de Spring Security en la BD
		-- Crear tabla de usuarios
		CREATE TABLE `users` (
			`username` varchar(50) NOT NULL,
			`password` varchar(50) NOT NULL,
			`enabled` tinyint(1) NOT NULL,
			PRIMARY KEY (`username`)
		) ENGINE=InnoDB;

		-- Crear tabla de roles
		CREATE TABLE `authorities` (
			`username` varchar(50) NOT NULL,
			`authority` varchar(50) NOT NULL,
			UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
			CONSTRAINT `authorities_ibfk_1`
			FOREIGN KEY (`username`)
			REFERENCES `users` (`username`)
		) ENGINE=InnoDB;
	2.- Se insertan roles y usuario en la BD
		-- Insertamos nuestros usuarios
		INSERT INTO `users` VALUES ('luis','{noop}luis123',1);
		INSERT INTO `users` VALUES ('marisol','{noop}mari123',1);

		-- Insertamos (asignamos roles) a nuestros usuarios.
		INSERT INTO `authorities` VALUES ('luis','SUPERVISOR');
		INSERT INTO `authorities` VALUES ('marisol','ADMINISTRADOR');
******************************************************************************************Configuracion de Spring Security (DataSource)
	1.- Se modifica application.properties, Se comenta la configuracion estatica del usuario y la contraseña en Spring Security
		#spring.security.user.name=eareiza
		#spring.security.user.password=masterkey
	2.- Se crea la clase DatabaseWebSecurity
		2.1.- Se crea paquete com.eareiza.security para una mejor organizacion.
		2.2.- Se crea clase DatabaseWebSecurity
			//Se agrega anotacion para que lo detecte como un archivo de configuracion
			@Configuration
			//Se le añade anotacion para que tenga soporte de seguridad
			@EnableWebSecurity
			public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
				//En tiempo de ejecucion se realiza inyeccion de la instancia del datasource configurado en el archivo application.properties
				@Autowired
				private DataSource dataSource;
				// Se crea metodo para authenticacion en la base de datos configurada en el DataSource
				@Override
				protected void configure(AuthenticationManagerBuilder auth) throws Exception {
					auth.jdbcAuthentication().dataSource(dataSource);
				}
			}
**************************************************************************************Recuperar usuario y rol de nuestras tablas de BDs
	1- Se modicica la clase DatabaseWebSecurity
		public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
			//En tiempo de ejecucion se realiza inyeccion de la instancia del datasource configurado en el archivo application.properties
			@Autowired
			private DataSource dataSource;
			// Se crea metodo para authenticacion en la base de datos configurada en el DataSource
			@Override
			protected void configure(AuthenticationManagerBuilder auth) throws Exception {
				auth.jdbcAuthentication().dataSource(dataSource)
				//Se realiza consulta a la tabla usuarios(propia del desarrollo) devolviendo username,password y estatus
				.usersByUsernameQuery("select username,password, estatus from Usuarios where username = ?")
				//Se realiza consulta a la tabla usuarios, usuarioperfil y perfil(propia del desarrollo) devolviendo username y perfil
				.authoritiesByUsernameQuery("select u.username, p.perfil from UsuarioPerfil up " +
						"inner join Usuarios u on u.id = up.idUsuario " +
						"inner join Perfiles p on p.id = up.idPerfil " +
						"where u.username = ?");

			}
		}
	2.- Se realizan los insert 
		-- Insertamos 2 usuarios
		INSERT INTO `Usuarios` VALUES (2,'Luis Esparza Gomez','luis@itinajero.net','luis','{noop}luis123',1,'2019-06-10');
		INSERT INTO `Usuarios` VALUES (3,'Marisol Salinas Rodarte','marisol@itinajero.net','marisol','{noop}mari123',1,'2019-06-10');

		-- Insertamos los roles para los usuarios
		INSERT INTO `UsuarioPerfil` VALUES (2, 1); -- PERFIL SUPERVISOR
		INSERT INTO `UsuarioPerfil` VALUES (3, 2); -- PERFIL ADMINISTRADOR
********************************************************************************************************Personalizar accesos a las urls
	1.- Se modifica clase DatabaseWebSecurity.java, Se crea metodo sobrecargado configure para el accesos a las url
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//Los recursos estaticos no reuieren autenticacion
		.antMatchers("/bootstrap/**",
				"/images/**",
				"/tinymce/**",
				"/logos/**").permitAll()
		
		//Las vistas publicas no requieren autenticacion
		.antMatchers("/",
				"/signup",
				"/search",
				"/create",
				"/vacantes/view/**").permitAll()
		
		//Todas las demas urls de la aplacacion requieren autenticacion
		.anyRequest().authenticated()
		
		//el formulario  de Login no requiere autenticacion
		.and().formLogin().permitAll();
	}
SpringBoot&SpringSecurity - Configuracion de acceso a los recursos por roles***********************************************************
****************************************************************************************************Personalizar accesos a URLs por Rol
	1.- Se modifica DatabaseWebSecurity.java, Se configura los accesos a las url dependiendo del rol
		//Se configura acceso a las urls segun role
		.antMatchers("/vacantes/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
		.antMatchers("/categorias/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
		.antMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")
****************************************************************Expresion sec:authirize de thymeleaf - Renderizar el menu dinamicamente
	En nuestras vistas podemos renderizar contenido HTML, dependiendo del ROL que tenga asignado un usuario. Para esto se usa la 
	expresión:
		sec:authorize="hasAnyAuthority('SUPERVISOR','ADMINISTRADOR')"******************************************************************


	Nota: La expresión se aplica a un elemento HTML.

	Un ejemplo práctico en nuestra aplicación sería para renderizar las opciones del menú dependiendo del ROL del usuario:
		<li class="nav-item" sec:authorize="hasAnyAuthority('SUPERVISOR','ADMINISTRADOR')">
			<a class="nav-link" th:href="@{/vacantes/indexPaginate}">Vacantes</a>
		</li>

	1.- Se modifica menu.html, se les da autorizacion por rol a las urls--> sec:authorize="hasAnyAuthority('SUPERVISOR','ADMINISTRADOR')"
		<li class="nav-item" sec:authorize="hasAnyAuthority('SUPERVISOR','ADMINISTRADOR')"><a class="nav-link active" th:href="@{/vacantes/indexPaginado}">Vacantes</a></li>
		<li class="nav-item" sec:authorize="hasAnyAuthority('SUPERVISOR','ADMINISTRADOR')"><a class="nav-link active" th:href="@{/categorias/indexPaginado}">Categorias</a></li>
		<li class="nav-item" sec:authorize="hasAnyAuthority('ADMINISTRADOR')"><a class="nav-link active" th:href="@{/usuarios/index}">Usuarios</a></li>
*********************************************************************Recuperar el nombre de usuario que inicio sesion en el controlador
	1.- Se crea metodo que recupera el usuario logueado, con un obj Authentication de spring*******************************************
		@GetMapping(value="/index")
	    //Se le añade un parametro de tipo Authentication, que tiene metodos para recuperar inf. del usuario que inicio sesion
	    public String mostrarIndex(Authentication auth) {
	    	//Se llama al metodo getName del obj Authentication para recuperar el nombre
	    	String username = auth.getName();******************************************************************************************
	    	System.out.println("Se ha logueado el usuario: "+username);
	    	//Redireccionamiento al directorio raiz de la aplicacion
	    	return "redirect:/";
	    }
	2.- Se modifica menu.html, se mapea el boton ingresar a la url /index
		<a class="btn btn-primary" th:href="@{/index}">Ingresar</a>&nbsp; 
**************************************************************************************Recuperar los roles del usuario en el controlador
	1.- Se modifica HomeController.java, el metodo mostrarIndex()
		//Se recupera los perfiles del usuario con el metodo getAuthorities, que devuelve un permisos de autorizacion
    	for (GrantedAuthority item : auth.getAuthorities()) {
			System.out.println("rol: "+item.getAuthority());
		}
************************************************************************************Http-Session Agregar datos a la session del usuario
	1.- Se modifica UsuariosRepository.java, se crea la firma al metodo que busca usuarios por el atributo username
		Usuario findByUsername(String user);
	2.- Se modifica la interfaz IUsuariosService.java, se agrega la firma al metodo buscarPorUsername()
		//Metodo que busca usuario por el username
		Usuario buscarPorUsername(String user);
	3.- Se modifica la clase de servicio UsuariosServiceJPA
		//Metodo que retorna usuario por atributo Username
		@Override
		public Usuario buscarPorUsername(String user) {
			return usuariosRepo.findByUsername(user);
		}
	4.- Se modifica el HomeController.java, se modifica el metodo mostrarIndex(), se le agrega a los parametros un objeto Http-Session,
	se recupera el usuario y se agrega a los atributos de la sesion
 		//Se valida si hay un atributo usario
    	if(sesion.getAttribute("usuario") == null) {
    		//Se recupera el usuario de la Base de Datos
    		Usuario user = servicesUsuario.buscarPorUsername(username);
    		//Se setea el pass a null para que no venga encriptada
    		user.setPassword(null);
    		//Se imprime el usuario
        	System.out.println(user.toString());
        	//Se agraga el usuario a la session
        	sesion.setAttribute("usuario", user);
    	}
****************************************************************************************Renderizar atributos de la sesion con thymeleaf
	1.- Se modifica la vista menu.html, se recupera y renderiza nombre del usuario por medio de session
		Renderizar atributos almacenados en la sesión.
			th:text="${session.nombreAtributo}"
			

			<span th:if="${session.usuario != null}" class="text-light" th:text="'Bienvenido '+${session.usuario.nombre} "></span>&nbsp;
**************************************************************************Recuperar en la vista el nombre del usuario que inicio sesion
	1.- Se modifica home.html, Se renderiza en la vista el nombre de usuario que inicia session por medio del obj Authentication
		Renderizar en la vista el nombre del usuario que inicio sesión.
		sec:authentication="name"	

		<span class="text-light" >Bienvenido, &nbsp;</span><span class="text-light" sec:authentication="name"></span>&nbsp; 
**************************************************************************************Expresion thymeleaf sec:authorize="isAnonimous()"
	Renderizar un elemento HTML para usuarios anónimos (usuarios que no han iniciado sesión).
		sec:authorize="isAnonymous()"
	1.- Se modifica menu.html, para que muestre los botones ingresar y registrarse unicamente si es anonimos
		<div sec:authorize="isAnonymous()">
			<a class="btn btn-primary" th:href="@{/index}">Ingresar</a>&nbsp; 
			<a class="btn btn-primary" th:href="@{/create}">Registrarse</a>&nbsp; 
		</div>
***********************************************************************************Expresion thymeleaf sec:authorize="isAthenticated()"
	Renderizar un elemento HTML para usuarios autenticados (usuarios que ya han iniciado sesión).
		sec:authorize="isAuthenticated()"
	1.- Se modifica la vista menu.html, utilizando --> sec:authorize="isAuthenticated()", para mostrar objetos unicamente si el usuario
	esta autenticado
	<div sec:authorize="isAuthenticated()">
		<span class="text-light">Bienvenido</span>&nbsp;<span class="text-light" sec:authentication="name"></span>&nbsp;
		<a class="btn btn-primary" th:href="@{/logout}">Salir</a>
	</div>
*********************************************************************************************Encriptar password con el algoritmo BCrypt
	Spring Security recomienda usar el algoritmo bcryptpara encriptar passwords.
		https://docs.spring.io/spring-security/site/docs/current/reference/html/ns-config.html#ns-password-encoder
		Ventajas:
			Ejecuta un hashing one-way (solo se encripta, pero no se puede desencriptar).
			Fácil de configurar en nuestra aplicación web.
		Configuración: declarar un Spring Bean de tipo PasswordEncoder
			Como implementación podemos usar la clase BCryptPasswordEncoder

	1.- Se modifica DatabaseWebSecurity.java, se crea metodo passwordEncoder() y le agrega la anotacion @bean
		//Se agrega anotacion para en tiempo de ejecucion se cree un bean del metodo en el contexto
		@Bean++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//Se crea metodo de encriptado
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	2.- Se modifica el homeController.java, se modifica el metodo guardarUsuario() para encriptar la contraseña.
		2.1.- Se realiza inyeccion de dependencia del bean creado 
			@Autowired
			private PasswordEncoder passwordEncoder;
		2.2.- Se modifica el metodo guardarUsuario(), se encripta contraseña
			String passPlano = usuario.getPassword();
	    	String passEncriptado = passwordEncoder.encode(passPlano);
	    	usuario.setPassword(passEncriptado);
*******************************************************************************************************Utileria para encriptar Password
	1.- Se modifica HomeController.java y Se crea metodo de encriptacion 
		@GetMapping("/encriptacion/{keyPlano}")
	    //Anotacion para que redirija directamente al body de la pagina
	    @ResponseBody
	    //Metodo de utilidad para encriptar
	    public String enciptacion(@PathVariable("keyPlano")String texto) {
	    	return texto+" Encriptado en BCrypt "+passwordEncoder.encode(texto);
	    }
	2.- Se modifica clase DatabaseWebSecurity.java, en el metodo configure() se coloca la url /encriptacion/ como de acceso publico
		.antMatchers("/",
				"/signup",
				"/search",
				"/create",
				"/save",
				"/encriptacion/**",
				"/vacantes/view/**").permitAll()
********************************************************************************************************Creacion de Login personalizado
	1.- Se modifica la clase DatabaseWebSecurity.java, se indica cual sera la pagina que renderiza el formulario de login
		//El formulario  de Login no requiere autenticacion, se indica por medio de loginPage la pagina que renderiza el Login
		.and().formLogin().loginPage("/login").permitAll();
	2.- Se modifica HomeController.java, se crea metodo que atienda login
		@GetMapping("/login")
	    public String mostrarLogin() {
	    	return"formLogin";
	    }
	3.- Se copia plantilla formLogin.html
		3.1.- Se configura como thymeleaf
			<html xmlns:th="http://www.thymeleaf.org">
		3.2.- Se configuran los recursos estaticos
			<!-- Bootstrap core CSS -->
		    <link th:href="@{/bootstrap/css/bootstrap.min.css}" rel="stylesheet">
		    <!-- Custom styles for this template -->
		    <link th:href="@{/bootstrap/css/jumbotron.css}" rel="stylesheet">
		    <link th:href="@{/bootstrap/css/sticky-footer-navbar.css}" rel="stylesheet">

		    <script th:src="@{bootstrap/js/bootstrap.min.js}"></script>  
*****************************************************************************************Metodo personalizado para el cierre de session 
***************************************************************************************************************************************
***************************************************************************************************************************************
***************************************************************************************************************************************
***************************************************************************************************************************************
FIN SPRNG BOOT, DATA, SECURITY*********************************************************************************************************



























*********************************************Spring Boot && RestFul Web Services*******************************************************
************************************************************************************************************************ResponseEntity
	En ciertas circunstancias se necesita enviar respuestas HTTP desde nuestra aplicación backend hacia la aplicación cliente. Spring 
	posee muchas formas para manejar esto, pero en esta ocasión se usará ResponseEntity para manejar el cuerpo, cabeceras y estatus de 
	estas respuestas.

	Clase ResponseEntity<T>
		Extiende o hereda de la clase HttpEntity y que agrega un HttpStatus código de estado. Normalmente usada en los servicios 
		REST dentro de los métodos de controladores.
		ResponseEntity maneja toda la respuesta HTTP incluyendo el cuerpo, cabecera y códigos de estado permitiéndonos total 
		libertad de configurar la respuesta que queremos que se envié desde nuestros endpoints.

		Ejemplo_____________________________________________________________________________________________

			@GetMapping("/clientes/{id}")
			//Manejo de codigo de respuesta
			//@ResponseStatus(HttpStatus.OK)
			//public Cliente showCliente(@PathVariable Integer id) {
			//Se agrega objeto ResponseEntity para el manejo de obj y mensajes de error
			//para que maneje cualquiertipo de objeto ResponseEntity<?>
			public ResponseEntity<?> showCliente(@PathVariable Integer id) {
				Cliente cliente = null;
				//Se crea Map para el envio de mensaje de error en el ResponseEntity
				Map<String, Object> response = new HashMap<>();
				//Se maneja el error de base datos con el obj de spring DataAccessExceptions
				try {
					//Se recupera el cliente por el id
					cliente = clienteServ.findById(id);
				} catch (DataAccessException e) {
					response.put("mensaje", "Error a realizar la consulta en la base de Datos.");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				//Se valida si el cliente es null y se maneja el error
				if(cliente == null) {
					response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la Base de Datos")));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				}
				
				//En caso de existir se retorna el obj y el estatus del mensaje
				return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		//		return clienteServ.findById(id);
			}
**************************************************************************************************************************************
***********************************************************************************************************Crear Proyecto Base MusicAPI
	1.- Se va al sitio oficial de Spring initializr
		Project: Maven
		Languaje: Java
		Version: 2.2.6
		Project Metadata
		Group: com.eareiza
		Artifact: music-api
		Name: music-api
		Description: Aplicacion RestFul Web Sercices Music- Api
		Package name: com.eareiza.api
		Packaging: Jar
		Version Java: 8
		Dependencias: Web, JPA, MySql
	2.- Se genera el projecto
	3.- El archivo .zip generado se coloca en el workspace
	4.- Se descomprime
	5.- Importamos el projecto en el sts
		File --> Import --> Existing Maven Project--> Nos colocamos a la altura del POM.xml --> finalizar
***********************************************************************************************************Creacion de la Base de Datos
	1.- Se crea la base de datos musicDB
		CreateDataBase musicDB:
	2.- Se crea la tabla 
************************************************************************************************Se crea la entiidad y se mapea a la BDS
	1.- Se crea clase Album con sus atributos, get y set
		public class Album {
			private Integer id;
			private String titulo;
			private Date lanzado;
			private String genero;
			private Double precio;
			...
		}
	2.- A nivel de la clase se agrega la anotacion @Entity
	3.- Se agrega la anotacion @Table(name="albums")
	4.- Seconfigura el atributo que sera la clave Primary 
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;
	5.- Creacion de la interfaz repositorio de la entidad Album -->AlbumRepository.java
		public interface AlbumRepository extends CrudRepository<Album, Integer> {

		}
*******************************************************************************************************Creacion de la clase de Servicio
	1.- Se crea Interfaz IAlbumsService.java
		1.1.- Se crea paquete com.eareiza.api.services
		1.2.- Se crea la interfaz IAlbumsService.java, se le agrega firma del metodo buscarTodos()
			public interface IAlbumsServices {
				List<Album> buscarTodos();
			}
	2.- Se crea Interfaz Repositorio de la clase Album, AlbumRepository.java extiende de JpaRepository
		public interface AlbumRepository extends JpaRepository<Album, Integer> {

		}
	3.- Se crea la clase de servicio AlbumServicesJpa.java, implementa IAlbumsService.java.
		3.1.- Se crea Inyeccion de dependencia al repositorio
			@Autowired
			private AlbumRepository albumRepository;
		3.2.- Se implementa el metodo buscarTodos()
				@Override
			public List<Album> buscarTodos() {
				return albumRepository.findAll();
			}
************************************************************************************************Configurar el DataSource de conexion BD
	1.- Se modifica el archivo application.properties
		# DATASOURCE (MYSQL 5.7)
		spring.datasource.driver-class-name=com.mysql.jdbc.Driver
		spring.datasource.url=jdbc:mysql://localhost:3306/empleosdb?useSSL=false&serverTimezone=America/Argentina/Buenos_Aires&allowPublicKeyRetrieval=true
		spring.datasource.username=root
		spring.datasource.password=admin
		#JPA
		spring.jpa.generate-ddl=false
		spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
		spring.jpa.show-sql=true
		# Table names physically
		spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
	2.- Se comenta el conector de bd por defect por la version de mysql que se tiene instalado

		<!-- 		<dependency> -->
		<!-- 			<groupId>mysql</groupId> -->
		<!-- 			<artifactId>mysql-connector-java</artifactId> -->
		<!-- 			<scope>runtime</scope> -->
		<!-- 		</dependency> -->
				<dependency>
					<groupId>mysql</groupId>
					<artifactId>mysql-connector-java</artifactId>
					<version>5.1.47</version>
				</dependency>
**************************************************************************************Anotacion @RestController Creacion de Controlador
	1.- Se crea paquete com.eareiza.api.controller
	2.- Se crea clase AlbumsController
	3.- Se agrega la anotacion @RestController
	4.- Se agrega la anotacion @RequestMapping, configurando el endpoint
		@RestController
		@RequestMapping("/api")
		public class AlbumsController {

		}			
	5.- Se crea Injection dependencys de la clase de servicio
		@Autowired
		private IAlbumsServices servicesAlbum;
	6.- Se crea metodo que retorne una lista de obj Album
		@GetMapping("/albums")
		public List<Album> buscarTodos(){
			return servicesAlbum.buscarTodos();
		}
*********************************************************************************************************************Se instala Postman
	1.- Se descarga el postman de la pagina oficial --> https://www.postman.com/
	2.- Se instala el programa
*****************************************************************************************************Peticion HTTP POST - Guardar Album
	1.- Se modifica la interface IAlbumsServices.java, se crea la firma al metodo guardar()
		void guardar(Album album);
	2.- Se modifica AlbumServicesJpa.java, se implementa el metodo guardar()
		@Override
		public void guardar(Album album) {
			albumRepository.save(album);			
		}
	3.- Se modifica AlbumsController, se agrega funion guardar() por metodo post para guardar album
		//Por convencion para guardar se utiliza el metodo POST
		@PostMapping("/albums")
		// La anotacion @RequestBody busca en el cuerpo del mensaje parametros y realiza el dataBinding al bean Album
		public Album guardar(@RequestBody Album album) {******************************************************************************
			servicesAlbum.guardar(album);
			return album;	
		}
	4.- se prueba el metodo en la aplicacion Postman
		1.- Se coloca el tipo de metodo POST
		2.- Se coloca URL --> localhost:8080/api/albums
		3.- Pestaña body --> raw --> tipo JSON, se envia en el body los atributos con los que se realizara el DataBinding
			{
				"titulo":"vivir",
				"lanzado":"1997-01-21",
				"precio":"195",
				"genero":"Pop"
			}
****************************************************************************************************Peticion HTTP PUT - Modificar Album
	1.- Se modifica AlbumsController, se agrega funion modificar() por metodo put para guardar album
		//Por convencion para modificar se utiliza el metodo PUT
		// La anotacion @RequestBody busca en el cuerpo del mensaje parametros y realiza el dataBinding al bean Album
		@PutMapping("/albums")
		public Album modificar(@RequestBody Album album) {
			servicesAlbum.guardar(album);
			return album;
		}
	2.- se prueba el metodo en la aplicacion Postman
		1.- Se coloca el tipo de metodo PUT
		2.- Se coloca URL --> localhost:8080/api/albums
		3.- Pestaña body --> raw --> tipo JSON, se envia en el body los atributos con los que se realizara el DataBinding,
		se coloca el id para que el metodo save de jpa pueda reconocer que es una modificacion
			{
			    "id": 3,
			    "titulo": "vivir",
			    "lanzado": "1997-01-20T03:00:00.000+0000",
			    "genero": "Pop en Español",
			    "precio": 215.0
			}
**************************************************************************************************Peticion HTTP DELETE - Eliminar Album
	1.- Se modifica Interface IAlbumsServices, se crea firma al metodo eliminar()
		void eliminar(int idAlbum);
	2.- Se modifica clase de servicio AlbumServicesJpa.java, se implemneta el metodo eliminar()
		@Override
		public void eliminar(int idAlbum) {
			albumRepository.deleteById(idAlbum);
		}
	3.- Se modifica AlbumsController.java, se crea metodo  eliminarAlbum()
		//Por convencion para elminar se utiliza el metodo Delete
		@DeleteMapping("/albums/{id}")
		public String eliminarAlbun(@PathVariable("id") int idAlbum) {
			servicesAlbum.eliminar(idAlbum);
			return "Registro eliminado";
		}
	4.- Se accesa dese la aplicacion POSTMAN, por el metodo DELETE y con la url 
		localhost:8080/api/albums/3
Creacion del war de la aplicacion******************************************************************************************************
	1.-Extendiendo la clase principal
		ampliamos nuestra clase principal a SpringBootServletInitializer . Esto le dice a Spring que su clase principal será el punto 
		de entrada para inicializar su proyecto en el servidor.

		@SpringBootApplication
		public class EmpleoApplication extends SpringBootServletInitializer{

			public static void main(String[] args) {
				SpringApplication.run(EmpleoApplication.class, args);
			}

		}
	2.- Método de configuración de anulación
		sobrecargamos el método de configuración de SpringBootServletInitializer. Le decimos a Spring que construya las fuentes de 
		nuestra clase Main.

		@SpringBootApplication
		public class EmpleoApplication extends SpringBootServletInitializer{

			public static void main(String[] args) {
				SpringApplication.run(EmpleoApplication.class, args);
			}
			
			@Override
		    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		        return builder.sources(EmpleoApplication.class);
		    }
		}
	3.- Configurar empaquetado a WAR
		Indicamos a maven que empaquete el proyecto en WAR. En su pom.xml, cambie el valor del atributo para el empaquetado de jar 
		a war

		<packaging>war</packaging>
***************************************************************************************************************************************
***************************************************************************************************************************************
***************************************************************************************************************************************
***************************************************************************************************************************************
FIN RestFullWebServices, DATA**********************************************************************************************************




























************************************************Deployment Server Linux Ubuntu********************************************************
**********************************************************************************************Proveedor de Hosting de VPS - linode.com
	
	Referencia --> https://www.linode.com
***********************************************************************************************************************Creacion de VPS
	Referencia --> udemy.com/course/spring-framework-desarrollo-web-spring-mvc/learn/lecture/8800842#overview
	usuario -->eareiza
	contraseña --> Danger120-
	1.- Se add linode
	2.- Se selecciona el plantilla
	3.- Se configura ubicacion de datacenter
	4.- Se agrega
	5.- Se instala el SO
		5.1.- Deploy Imagen
		5.2.- Se selecciona SO Ubuntu 16.04LTS
		5.3.- Se proporciona la contraseña del root
		5.4.- Se presiona boot para iniciar el servidor
		5.5.- Se verifica el estatus que este en running
******************************************************************************************************Acceder por medio de una consola
	1.- Desde el panel de control de linode se accede a Launch Console
	2.- Se ingresa usuario y contraseña
***********************************************************************************************Acceso Remoto al servidor desde Windows
 	Referencia --> https://www.chiark.greenend.org.uk/~sgtatham/putty/
 	1.- Download --> stable 
 	2.- Se descarga el putty.exe y se coloca en el escritorio
 	3.- Se ingresa ip del servidor ip 66.228.61.76
 	4.- User: root
 	5.- Contraseña: Danger120-
**********************************************************************************************Actualizacion y ConfiguracionZonaHoraria
	1.- Actualizacion repositorio de Software
		sudo apt-get update
	2.- Actualizar paquetes a las ultimas versiones
		sudo apt-get upgrade
	3.- Configurar la zona horaria del servidor
		dpkg-reconfigure tzdata
**************************************************************************************************Instalacion de Java 8 en el servidor
	1.- Se ingresa en la pagina oficial de Oracle y se descarga la version 8 Server JRE
	2.- Subir el archivo al servidor
		2.1.- Se instala en el equipo un cliente FTP -->FileZilla
		2.2.- Se abre el aplicativo y se colocan los datos de acceso
			host: 66.228.61.76
			user: root
			contraseña: Danger120-
			Port: 22
		2.3.- Se ingresa en el servidor, en raiz--> opt, y se carga el archivo de instalacion server JRE, ubicandolo en el local 
		y con el click derecho upload
			server-jre-8u241-linux-x64
		2.4.- Ingreso con putty por consola al servidor con los datos de acceso
		2.5.- Acceso a la carpeta
			2.5.1.- cd /opt/
			2.5.2.- ls -l
		2.6.- Se descomprime el archivo
			tar xvf server-jre-8u241-linux-x64.tar.gz
		2.7.- Se declara variable de entorno en el Sistema Operativo
			2.7.1.- Se imprime el nombre del directorio desde la carpeta jdk con el comando 
				pwd
			2.7.2.- Se copia la ruta
				/opt/jdk1.8.0_241
		2.8.- Se configura el archivo etc/profile
			nano /etc/profile
			2.8.1.- Se declaran dos variables de entorno al final del archivo
				export JAVA_HOME=/opt/jdk1.8.0_241
				export PATH=$PATH:$JAVA_HOME/bin
				ctrl+o --> Guardar el archivo actual
				ctrl+x --> Salir de nano
		2.9.- Se aplican las variables de entorno
			source /etc/profile
		2.10.- Para validar, con cd .. nos vamos hasta /root
			ejecutamos java -version
**********************************************************************************************************Instalacion de Apache Tomcat
	1.- Se descarga de la pagina oficial de tomcat la version 8
		https://tomcat.apache.org/download-80.cgi#8.5.54
		1.- se descarga el archivo tar.gz 
	2.- Con el cliente FTP FileZilla se procede a descargarlo en el servidor en la carpeta /opt/
	3.- Se ingresa por la terminal, a la carpeta cd /opt/
	4.- Se descomprime el archivo
		tar xvf apache-tomcat-8.5.54.tar.gz
	5.- Se ingresa al directorio creado 
		cd apache-tomcat-8.5.54
	6.- Se imprime y se copia la ruta ABSOLUTA
		pwd
	7.- Se declara variable de entorno
		7.1.- Se ingresa con nano en /etc/profile
		7.2.- Se configura variable de entorno al final del archivo
			export CATALINA_HOME=/opt/apache-tomcat-8.5.54
			ctrl+o --> Se guarda el archivo
			ctrl+x --> Se sale de nano
		7.3.- Se aplican los cambios 
			 source /etc/profile
		7.4.- Comprovar si la variable creada existe
			echo $CATALINA_HOME  --> Se debe visualizar el apache tomcat configurado
	8.- Se inicia el servidor
		8.1.- Se ingresa en el directorio de bin de tomcat
			cd bin
		8.2.- Se ejecuta comando de comenzar
			./startup.sh
	9.- Se verifica que se inicio de manera correcta en el log de tomcat
		9.1.- Nos devolvemos a la carpeta de tomcat con --> cd ..
		9.2.- Se ingresa a la carpeta de logs  --> cd logs/
		9.3.- Se mustran las ultimas lineas del log con el comando
			tail -f catalina.out
	10.- Se ingresa al servidor con la ip y el puerto por defecto, desde un explorador
		66.228.61.76:8080 

		Referencia Comandos en linux--> https://devs4j.com/2017/10/10/comandos-de-linux-para-busqueda-de-logs-que-todos-los-desarrolladores-deberian-conocer/
**************************************************************************Configuracion Acceso remoto al administrador de aplicaciones
	***Configuración del usuario:
		1. Abrir el archivo conf/tomcat-users.xml esta en el directorio de apache
		2. Al final del archivo agregar un rol llamado "manager-gui" y asignarselo a un
		usuario. Por ejemplo:
		<role rolename="manager-gui"/>
		<user username="eareiza" password="Danger120" roles="manager-gui"/>
		3. Guardar el archivo --> ctrl+x y reiniciar Apache Tomcat.
	1. Abrir el archivo webapps/manager/META-INF/context.xml y se config con nano
	***Para permitir el acceso al otras direcciones IP
	2. La configuración del tag Valve esta de la siguiente forma:
	<Valve className="org.apache.catalina.valves.RemoteAddrValve"
	allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1" />
	3. Cambiar el elemento Valve por el siguiente:
	<Valve className="org.apache.catalina.valves.RemoteAddrValve"
	allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1|.*" />
	4. Guardar el archivo y reiniciar Apache Tomcat.
		4.1.- Desde la carpeta bin del apache-tomcat-8
			4.1.1.- Se reinicia
			 	./shutdown.sh
			 	./startup.sh 
*************************************************************************************************************Aplicaciones del servidor
	1.- Desde tomcat se accede a la carpeta --> cd webapps
***********************************************************************************************************************Publicacion WAR
	1.- Se genera archivo war de la aplicacion, se guarda en directorio distintas
	2.- Se va al administrador de aplicaciones
	3.- Seccion war to deploy
	4.- Cargamos el archivo WAR
	5.- Se ingresa con putty al servidor
	6.- se ingresa al directorio de aplicaciones 
		cd /opt/apache-tomcat-8.54/webapp
	7.- Se ven los directorios con ls -l
********************************************************************************************Instalar el Servidor de BD MYSQL en el VPS
	1.- Actualizamos el software del SO, nos paramos en la base.
		apt-get update
	2.- Busco en los respositorios si hay nuevas versiones para descargarlas
		apt-get upgrade
	3.- Instalacion del paquete MSQL-server
		apt-get install mysql-server
		3.1.- Nos pide contraseña default:
			 base de datos
			Danger120-
		3.2.- Se ingresa con el usuario root 
			mysql -u root -p
		3.3.- Se ingresa la clave del servidor MySql
		3.4.- Se sube con FileZilla, al servidor el driver para conexion a la BD
			/opt/apache-tomcat-8.5.54/lib
		3.5.- Se Sube con FileZilla, al servidor el script a la BD
		 /root/
	4.- Se ejecuta el script de BD
		4.1.- Se ingresa a MySql
			mysql -u root -p
			contraseña: Danger120-
		4.2.- Se crea BD cursodb
			create database cursodb;
			exit 
		4.3.- se ejecuta el comando desde raiz, para ejecutar script en la BDs
			mysql -u root -p cursodb < cursodb.sql
		4.4.- Se verifica si se ejecutado
			4.4.1.- Se ingresa a mysql 
			4.4.2.- use cursodb;
					show tables;
					select * from Usuario;
					insert into Usuario Values(0, 'Luis Fernandez', 'luis', MD5('luis123'),'1');
					exit;
	5.- Se reinicia el servidor
		5.1.- Nos vamos a la carpeta de apache --> bin
			./shutdown.sh
			./startup.sh
**********************************************************************************Publicacion de Aplicacion a partir de un archivo WAR
	1.- Se coloca el driver mysql-conector en la carpeta lib de apache
		cd /opt/apache-tomcat-8.5.54/lib  --> mysql-connector-java-5.1.47.jar
	2.- Se sube el script de BDs a la carpeta root con FileZilla
		cd root/  --> cineapp.sql
	3.- Se ingresa al servidor de base de datos
		mysql -u root -p
	4.- Se crea base de datos de CineApp
		create database cineapp;
	5.- Se va al raiz y se ejecuta
		mysql -u root -p cineapp < cineapp.sql
*******************************************************************************Cambiar la aplicacion de bienvenida(Root) Apache tomcat
	1.-Ingreso al servidor 
	2.- Ingreso a la carpeta webapps
		cd /opt/apache-tomcat-8.5.54/webapps
	3.- Desde el 66.228.61.76/manager, ingresamos y le damos a undeploy(replegar) el directorio / (Root)
*****************************************************************************configurar Apache Tomcat para que escuche en el puerto 80
	Este es el puerto por defecto del protocolo HTTP. Por lo tanto, no es necesario ingresarlo en la barra de direcciones
	de un navegador de internet.
	Nota: Debemos asegurarnos de que este puerto no este siendo utilizado por algun otro servicio como Apache HTTP.

	1.- Nos conectamos al servidor
	2.- Se busca el archivo de configuracion de tomcat, el archivo server.xml
		cd /opt/apache-tomcat-8.5.54/conf/
		ls -l
	3.- Ingresamos con nano al archivo server.xml
		nano server.xml
	4.- En se cambia puerto 8080 por 80
		<Connector port="80" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
    5.- se reinicia el tomcat y luego a bin, nos paramos en la carpeta de tomcat
			./shutdown.sh
			./startup.sh
****************************************************************************************Configuracion de firewall UFW de Ubuntu Server
	Ubuntu Server proporciona un Firewall llamado UFW (Uncomplicated FireWall) para administrar la seguridad
	del servidor.
	Por cuestiones de seguridad, debemos de cerrar todos los puertos TCP en nuestro Servidor Linux.
	Unicamente debemos dejar abiertos dos puertos:
		– Puerto 22: Para poder conectarnos remotamente utilizando SSH.
		– Puerto 80: El puerto en el que esta escuchando nuestro servidor Apache Tomcat.
	Para realizar esta configuración, debemos ejecutar los siguientes comandos:
	Comando 				Descripción
	# ufw status verbose 	Comando para ver el estatus actual del Firewall (por default esta inactivo)
	# ufw enable 			Permite activar el Firewall. Por default, todos los puertos se cerrarán.
							Puede ser que aparezca una alerta que dirá "command may disrupt existing ssh connections". 
							Por lo tanto, si estamos conectados de forma remota vía SSH (puerto 22), 
							el servidor cerrará la conexión y nos sacará del sistema.
	# ufw allow 22 			Abrir el puerto 22 para permitir conexiones vía SSH de forma remota.
	# ufw allow 80 			Abrir el puerto 80 de Apache Tomcat.

	NOTA IMPORTANTE: Para activar el Firewall es recomendable hacerlo desde una de las terminales que nos ofrece
	el proveedor de hosting, debido a que si lo hacemos de forma remota con una conexión vía SSH, al activar el
	Firewall se cerrarán todos los puertos, incluso el puerto 22 (SSH), por lo tanto se cerrará nuestra conexión remota
	y no podremos volver a entrar de forma remota, hasta que el puerto 22 haya sido abierto en el Firewall.

	1.- Se ingresa a linode
	2.- Se ingresa a lounch console con glyphicon-trash
	3.- Se instala ufw
		sudo apt-get install ufw
	4.- Se abren el puerto 22 y el puerto 80
		ufw allow 22
		ufw allow 80
	5.- Se ve el estatus de los puertos 
		ufw status verbose
***********************************************************************************************Configuracion en apache de virtual host
	referencia  --> https://www.udemy.com/course/spring-framework-desarrollo-web-spring-mvc/learn/lecture/8801136#questions
	Es un configuracion en apache para tener varios sitios web en un servidor

	1.- Se ingresa en el servidor, en la carpeta apache, Se edita el archivo server.xml
		 cd /opt/apache-tomcat-8.5.54/conf/
		 nano server.xml
	2.- final del archivo server-xml despues de </Host>

		<Host name="zapateriapos.com" appBase="puntoventa_webapps" unpackWARs="true" autoDeploy="true" > </Host>
		<Host name="cursojavaweb.com" appBase="curso_webapps" unpackWARs="true" autoDeploy="true" > </Host>
	3.- Se crean los directorios dentro del tomcat
		mkdir puntoventa_webapps
		mkdir curso_webapps
	4.- Se copia webapps/manager --> puntoventawebapps
		cp –r webapps/manager puntoventa_webapps
		cp –r webapps/manager puntoventa_webapps
	5.- se reinicia el tomcat y luego a bin, nos paramos en la carpeta de tomcat
			./shutdown.sh
			./startup.sh
****************************************************************************Configuracion de nombres de dominio en el archivo etc/hots
	
	referencia  --> https://www.udemy.com/course/spring-framework-desarrollo-web-spring-mvc/learn/lecture/8801136#questions




Publicacion de aplicacion CineApp*****************************************************************************************************
********************************************************************************************************************Generar el arc war
	1.- Se modifica web.xml, se le cambia la configuracion para las descargas de archivos upload, para formato servidor linux
		<!-- Para Habilitar Upload Files -->
		<multipart-config>
			<!-- <location>d:\tmp</location>-->
			<location>/tmp</location>
		</multipart-config>



*************************************************************************************************Recuperar usuario de SpringSecurity
	@PrePersist
	private void defaultValores() {
		this.fechaCarga = new Date();
	    Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    this.usuario = userDetail.getUsername();
	}
************************************************************************************************************************************

**************************************************************************************************************Mandar email a usuario
	referencia --> https://www.google.com/settings/security/lesssecureapps 
	Clase de servicio--------------------------------------------------------------------------------------------------------
		@Service
		public class EnvioMail {


		    //Importante hacer la inyección de dependencia de JavaMailSender:
		    @Autowired
		    private JavaMailSender mailSender;

		    //Pasamos por parametro: destinatario, asunto y el mensaje
		    public void sendEmail(String to, String subject, String content) {

		        SimpleMailMessage email = new SimpleMailMessage();

		        email.setTo(to);
		        email.setSubject(subject);
		        email.setText(content);
		       
		        mailSender.send(email);
		    }
		}
	Aplication.properties----------------------------------------------------------------------------------------------------
		#envio de mail
		spring.mail.host=smtp.gmail.com
		spring.mail.port=587
		spring.mail.protocol=smtp
		spring.mail.username=dirielfran@gmail.com
		spring.mail.password=Nala120-
		spring.mail.properties.mail.smtp.auth=true
		spring.mail.properties.mail.smtp.starttls.enable=true
		spring.mail.properties.mail.smtp.starttls.required=true
		spring.mail.properties.mail.smtp.quitwait=false
	pom.xml------------------------------------------------------------------------------------------------------------------
		<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-mail -->
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-mail</artifactId>
		    <version>2.3.3.RELEASE</version>
		</dependency>
	Se ejecuta--------------------------------------------------------------------------------------------------------------
		    @Autowired
    		private JavaMailSender mailSender;

		SimpleMailMessage email = new SimpleMailMessage();
        
        //recorremos la lista y enviamos a cada cliente el mismo correo
        email.setTo("ytobosa@gmail.com");
        email.setSubject("Prueba");
        email.setText("Te quiero un mundo aunque tu no me quieras como antes");
        
        mailSender.send(email);
************************************************************************************************************************************



**************************************************************************************************Dar formato a decimal en thymeleaf
	<h5 th:text="'Distancia: '+${#numbers.formatDecimal(distancia, 0, 'POINT' , 2, 'COMMA')}+' Kms.'">
	</h5>
************************************************************************************************************************************




************************************************************************************************Consumo de un servicio REST con GSON
	Referencia --> https://jarroba.com/gson-json-java-ejemplos/

		@GetMapping("/info/{code}") 
	public ResponseEntity<?> pruebaGeoInfo(@PathVariable String code ){
		
		Map<String, Object> response = new HashMap<>();
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("https://restcountries.eu/rest/v2/alpha/".concat(code));
		String  call= restTemplate.getForObject("https://restcountries.eu/rest/v2/alpha/".concat(code),String.class);

		///////////////////////////////////////////////////////////nombre
		System.out.println("**********************************Nombre");
		// Obtain Array
	    JsonObject gsonObj = JsonParser.parseString(call).getAsJsonObject();
	    InformacionPais pais = null;
	    System.out.println(gsonObj);
	    String name = gsonObj.get("name").getAsString();
	    System.out.println(name);
	    
	    ////////////////////////////////////////////////////////lenguajes
	    System.out.println("**********************************Lenguaje");
        // List of primitive elements
	    List<String> cadenas = new ArrayList<String>();
        JsonArray lenguajes = gsonObj.get("languages").getAsJsonArray();
        for (JsonElement lenguaje : lenguajes) {
        	JsonObject gsonObj1 = lenguaje.getAsJsonObject();
        	String iso639_1 = gsonObj1.get("iso639_1").getAsString();
        	String iso639_12 = gsonObj1.get("iso639_2").getAsString();
        	String name1 = gsonObj1.get("name").getAsString();
        	String nativeName = gsonObj1.get("nativeName").getAsString(); 
        	cadenas.add(iso639_1);
        	cadenas.add(iso639_12);
        	cadenas.add(name1);
        	cadenas.add(nativeName);
        	cadenas.forEach(System.out::println);
        }
        
        //////////////////////////////////////////////////////////latitud
        System.out.println("**********************************Latitud");
        JsonArray latitudes = gsonObj.get("latlng").getAsJsonArray();
        List<String> lat = new ArrayList<String>();
        for (JsonElement item : latitudes) {
        	lat.add(item.getAsString());
        }
        lat.forEach(System.out::println);

        //////////////////////////////////////////////////////////Zona Horaria
        System.out.println("**********************************Zone Horaria");
        JsonArray JZonasHor = gsonObj.get("timezones").getAsJsonArray();
        List<String> zonasHor = new ArrayList<String>();
        for (JsonElement item : JZonasHor) {
        	zonasHor.add(item.getAsString());
        }
        zonasHor.forEach(System.out::println);
        
        
        //////////////////////////////////////////////////////////currency
	    System.out.println("**********************************Currency");
        JsonArray jcurrencies = gsonObj.get("currencies").getAsJsonArray();
        List<String> currencies = new ArrayList<String>();
        for (JsonElement lenguaje : jcurrencies) {
        	JsonObject gsonObj2 = lenguaje.getAsJsonObject();
        	String codigo = gsonObj2.get("code").getAsString();
        	String moneda = gsonObj2.get("name").getAsString();
        	String simbolo = gsonObj2.get("symbol").getAsString(); 
        	currencies.add(codigo);
        	currencies.add(moneda);
        	currencies.add(simbolo);
        	currencies.forEach(System.out::println);
        }
        
	    return new ResponseEntity<InformacionPais>(pais, HttpStatus.OK);
	}
************************************************************************************************************************************
//Crear usuario con privilegios en mysql
CREATE USER 'alfonso'@'localhost' IDENTIFIED BY 'danger120-';
GRANT ALL PRIVILEGES ON * . * TO 'alfonso'@'localhost';
FLUSH PRIVILEGES;

//revisar los log de catalina
less /opt/tomcat/logs/catalina.out
less /opt/apache-tomcat-8.5.54/logs/catalina.out

$ tail -f /opt/apache-tomcat-8.5.54/logs/catalina.out






*****************************************************************************************************************************************
	Proyectos de tipo Academico:
	CineApp: 
	Desarrollo completo de aplicación en Java con Spring MVC, Spring Data, Spring Security, Maven, Hibernate como ORM, conexion a base de 
	datos Mysql,  en el FrontEnd se implementa jsp, bootstrap, jstl, Form Tags de Spring, Configuraciones del DispatcherServlet, 
	viewResolver, rootAplication-context, webConfig por medio de archivos .xml, Administracion de dependencias con Maven, DataBinding, 
	RedirectAttributes, Manejo de errores con BindingResult, Descarga de archivos con MultipartFile, JPARepository, Query Methods, 
	Manejo de Usuarios por roles atraves de SpringSecurity, Anotaciones: @PathVariable, @RequestParam, @GetMapping, @PostMapping, 
	@Autowired, @InitBinder, @Component, @Repository, @Service, @Controller, @RequestMapping, @ModelAttribute, @entity, @Table, 
	@column, @Transient, @OneToOne, @JoinColumn

	EmpleosApp: 


	Desarrollo completo de aplicación en Java con Spring Boot, Spring Data, Spring Security, Hibernate como ORM, conexion a base de 
	datos Mysql,  en el FrontEnd se implementa thymeleaf, bootstrap, jstl,  Administracion de dependencias con Maven, DataBinding, 
	RedirectAttributes, Manejo de errores con BindingResult, Descarga de archivos con MultipartFile, JPARepository, Query Methods, 
	Manejo de Autenticacion de usurios por medio del AuthenticationManagerBuilder, manejo de roles de usurio con http.authorizeRequests,  
	configuracion de archivo aplication.properties Anotaciones: @PathVariable, @RequestParam, @GetMapping, @PostMapping, @Autowired, 
	@InitBinder, @Component, @Repository, @Service, @Controller, @RequestMapping, @ModelAttribute, @entity, @Table, @column, @Transient, 
	@OneToOne, @JoinColumn

	ClientesFacturacionApp: (En curso actual Angular)
	Desarrollo completo de aplicación Java orientada a microservicios con Angular y Spring Boot, Spring Data, Hibernate como ORM, 
	conexion a base de datos Mysql,  en el FrontEnd se implementa thymeleaf, bootstrap, jstl,  
	Administracion de dependencias con Maven, DataBinding, RedirectAttributes, Manejo de errores con BindingResult, Descarga 
	de archivos con MultipartFile, JPARepository, Query Methods, Manejo de Autenticacion de usurios por medio del 
	AuthenticationManagerBuilder, manejo de roles de usurio con http.authorizeRequests,  
	configuracion de archivo aplication.properties, Manejo de respuesta con objetos ResponseEntity,  HttpClient,  
	Anotaciones: @RestController, @CrossOrigin, @PathVariable, @RequestParam, @GetMapping, @PostMapping, @Autowired, 
	@InitBinder, @Component, @Repository, @Service, @Controller, @RequestMapping, @ModelAttribute, @entity, @Table, 
	@column, @Transient, @OneToOne, @JoinColumn

	WEbAPI
	Desarrollo completo de aplicación Java orientada a Spring Boot && RestFul Web Services


	La responsabilidad única de los componentes, la segregación de interfaces, la documentación detallada del código, 
	Programación orientada a que se dependa de las abstracciones y no a las implementaciones, en cuanto a patrones de 
	diseño utilizados, patron de diseño MVC para dividir la aplicación por capas bien diferenciadas, DAO para el acceso 
	a los datos desde repositorios, DTO para la transferencia de objetos en caso de necesitar atributos de varios objetos 
	se crea un objeto DTO que es el que se transfiere, patron Factory lo use en un sistema de facturacion creando un clase 
	que crea objetos de facturacion segun el tipo de factura,  patron proxy se crea una clase que añadia funcionalidad a la 
	clase de legajos del cliente, para así no modificar la actual, a nivel academico he implementado otros mas como Singlenton, 
	Builder, focade o decorator.
Platillas gratis**********************************************************************************************************************
https://colorlib.com/wp/free-bootstrap-ecommerce-website-templates/






Tuto Spring Boot************************************************************************************************************************
	Referencia --> https://www.tutorialspoint.com/spring_boot/spring_boot_application_properties.htm
	Spring Boot - Propiedades de la aplicación************************************************************************************
		1.- Application.properties
			server.port = 9090
			spring.application.name = demoservice	
		2.- TutoSpringBootApplication.java
	******************************************************************************************************************************
	Spring Boot - Logging*********************************************************************************************************
		Spring Boot utiliza el registro de Apache Commons para todos los registros internos. Las configuraciones predeterminadas 
		de Spring Boot brindan soporte para el uso de Java Util Logging, Log4j2 y Logback. Con estos, podemos configurar el 
		registro de la consola así como el registro de archivos.

		Si está utilizando Spring Boot Starters, Logback proporcionará un buen soporte para el registro. Además, Logback también 
		proporciona un buen soporte para Common Logging, Util Logging, Log4J y SLF4J.

		Los mensajes de registro predeterminados se imprimirán en la ventana de la consola. De forma predeterminada, los
		mensajes de registro “INFO”, “ERROR” y “WARN” se imprimirán en el archivo de registro.

		Niveles de registro
		Spring Boot admite todos los niveles de registrador, como "TRACE", "DEBUG", "INFO", "WARN", "ERROR", "FATAL", "OFF". 
		Puede definir el registrador raíz en el archivo application.properties como se muestra a continuación:

			logging.level.root = WARN

		Ejemplo---------------------------------------------------------------------------------------------------------------
		private final Logger logger = LoggerFactory.getLogger(this.getClass());
	    public void performTask(){
	        logger.debug("This is a debug message.");
	        logger.info("This is an info message.");
	        logger.warn("This is a warn message.");
	        logger.error("This is an error message.");

	    }
	    	*** Application.properties
	    		logging.file.name=D:/prueba.txt
	******************************************************************************************************************************
	Servicios web RESTful*********************************************************************************************************
		Nota : para crear un servicio web RESTful, necesitamos agregar la dependencia web Spring Boot Starter en el archivo de 
		configuración de la compilación.
		Ejemplo_______________________________________________________________________________________________________________
			***POM.xml	
			<dependency>
			   <groupId>org.springframework.boot</groupId>
			   <artifactId>spring-boot-starter-web</artifactId>    
			</dependency>
			-----------------------------------------------------------------------------------------------------------------
			@RestController
			public class ProductServiceController {
			   private static Map<String, Product> productRepo = new HashMap<>();
			   static {
			      Product honey = new Product();
			      honey.setId("1");
			      honey.setName("Honey");
			      productRepo.put(honey.getId(), honey);
			      
			      Product almond = new Product();
			      almond.setId("2");
			      almond.setName("Almond");
			      productRepo.put(almond.getId(), almond);
			   }
			   
			   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
			   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
			      productRepo.remove(id);
			      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
			   }
			   
			   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
			   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
			      productRepo.remove(id);
			      product.setId(id);
			      productRepo.put(id, product);
			      return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
			   }
			   
			   @RequestMapping(value = "/products", method = RequestMethod.POST)
			   public ResponseEntity<Object> createProduct(@RequestBody Product product) {
			      productRepo.put(product.getId(), product);
			      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
			   }
			   
			   @RequestMapping(value = "/products")
			   public ResponseEntity<Object> getProduct() {
			      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
			   }
			}
			-------------------------------------------------------------------------------------------------------------
				public class Product {
				   private String id;
				   private String name;

				   public String getId() {
				      return id;
				   }
				   public void setId(String id) {
				      this.id = id;
				   }
				   public String getName() {
				      return name;
				   }
				   public void setName(String name) {
				      this.name = name;
				   }
				}

			-------------------------------------------------------------------------------------------------------------
			@SpringBootApplication
				public class DemoApplication {
				   public static void main(String[] args) {
				      SpringApplication.run(DemoApplication.class, args);
				   }
				}
	****************************************************************************************************************************** 
	Spring Boot - Manejo de excepciones*******************************************************************************************
		Asesoramiento del controlador*****************************************************************************************
		@ControllerAdvice-----------------------------------------------------------------------------------------------------
			Es una anotación, para manejar las excepciones globalmente
		----------------------------------------------------------------------------------------------------------------------
		Controlador de excepciones********************************************************************************************
		@ExceptionHandler-----------------------------------------------------------------------------------------------------
		 es una anotación que se usa para manejar las excepciones específicas y enviar las respuestas personalizadas al cliente.
		----------------------------------------------------------------------------------------------------------------------
			1.- Puede usar el siguiente código para crear la clase @ControllerAdvice para manejar las excepciones globalmente:

				package com.tutorialspoint.demo.exception;

				import org.springframework.web.bind.annotation.ControllerAdvice;

				@ControllerAdvice
				public class ProductExceptionController {
				   @ExceptionHandler(value = ProductNotfoundException.class)
				   public ResponseEntity<Object> exception(ProductNotfoundException exception) {
				      return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
				   }
				}
			2.- Defina una clase que amplíe la clase RuntimeException.

				package com.tutorialspoint.demo.exception;

				public class ProductNotfoundException extends RuntimeException {
				   private static final long serialVersionUID = 1L;
				}

			3.- Puede definir el método @ExceptionHandler para manejar las excepciones como se muestra. Este método se 
			debe utilizar para escribir el archivo de clase de avisos del controlador.

				@ExceptionHandler(value = ProductNotfoundException.class)
				public ResponseEntity<Object> exception(ProductNotfoundException exception) {
				}
			4.- Ahora, use el código que se proporciona a continuación para lanzar la excepción de la API.

				@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
				public ResponseEntity<Object> updateProduct() { 
				   throw new ProductNotfoundException();
				}

			5.- El archivo del controlador de API de servicio de producto se proporciona a continuación para 
			actualizar el producto. Si no se encuentra el producto, lanza la clase ProductNotFoundException .
				@RestController
				public class ProductServiceController {
				   private static Map<String, Product> productRepo = new HashMap<>();
				   static {
				      Product honey = new Product();
				      honey.setId("1");
				      honey.setName("Honey");
				      productRepo.put(honey.getId(), honey);
				      
				      Product almond = new Product();
				      almond.setId("2");
				      almond.setName("Almond");
				      productRepo.put(almond.getId(), almond);
				   }
				   
				   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
				   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
				      if(!productRepo.containsKey(id))throw new ProductNotfoundException();
				      productRepo.remove(id);
				      product.setId(id);
				      productRepo.put(id, product);
				      return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
				   }
				}
			6.- El código para el archivo de clase de la aplicación Spring Boot principal se proporciona a continuación

				package com.tutorialspoint.demo;

				import org.springframework.boot.SpringApplication;
				import org.springframework.boot.autoconfigure.SpringBootApplication;

				@SpringBootApplication
				public class DemoApplication {
				   public static void main(String[] args) {
				      SpringApplication.run(DemoApplication.class, args);
				   }
				}
			7.- El código de la clase POJO para el producto se proporciona a continuación:

				package com.tutorialspoint.demo.model;
				public class Product {
				   private String id;
				   private String name;

				   public String getId() {
				      return id;
				   }
				   public void setId(String id) {
				      this.id = id;
				   }
				   public String getName() {
				      return name;
				   }
				   public void setName(String name) {
				      this.name = name;
				   }
				}
	******************************************************************************************************************************
	Spring Boot - Interceptor*****************************************************************************************************
		Puede usar el Interceptor en Spring Boot para realizar operaciones en las siguientes situaciones:

			Antes de enviar la solicitud al controlador

			Antes de enviar la respuesta al cliente

		Por ejemplo, puede usar un interceptor para agregar el encabezado de la solicitud antes de enviar la solicitud al 
		controlador y agregar el encabezado de respuesta antes de enviar la respuesta al cliente.

		Para trabajar con interceptor, necesita crear la clase @Component que lo admita y debe implementar la interfaz 
		HandlerInterceptor .

		Los siguientes son los tres métodos que debe conocer mientras trabaja con interceptores:

			Método preHandle () : se utiliza para realizar operaciones antes de enviar la solicitud al controlador. 
			Este método debe devolver verdadero para devolver la respuesta al cliente.

			Método postHandle () : se utiliza para realizar operaciones antes de enviar la respuesta al cliente.

			Método afterCompletion () : se utiliza para realizar operaciones después de completar la solicitud y la respuesta.

		Tendrá que registrar este Interceptor con InterceptorRegistry utilizando WebMvcConfigurerAdapter como se muestra a continuación:

			@Component
			public class ProductServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
			   @Autowired
			   ProductServiceInterceptor productServiceInterceptor;

			   @Override
			   public void addInterceptors(InterceptorRegistry registry) {
			      registry.addInterceptor(productServiceInterceptor);
			   }
			}
		Ejemplo______________________________________________________________________________________________________________
			***ProductServiceInterceptor
				@Component
				public class ProductServiceInterceptor implements HandlerInterceptor {

					@Override
					public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
							throws Exception {
						System.out.println("Pre Handle method is Calling");
						return true;
					}

					@Override
					public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
							ModelAndView modelAndView) throws Exception {
						 System.out.println("Post Handle method is Calling");
					}

					@Override
					public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
							throws Exception {
						System.out.println("Request and Response is completed");
					}
					
					
				}
			----------------------------------------------------------------------------------------------------------------
			****ProductServiceInterceptorAppConfig.java
				@Component
				public class ProductServiceInterceptorAppConfig implements WebMvcConfigurer{

					@Autowired
					ProductServiceInterceptor productServiceInterceptor;

					@Override
					public void addInterceptors(InterceptorRegistry registry) {
						registry.addInterceptor(productServiceInterceptor);
					}	
				}
			----------------------------------------------------------------------------------------------------------------
			*** Se modifica ProductServiceController, se agrega metodo get
				   @RequestMapping(value = "/products")
				   public ResponseEntity<Object> getProduct() {
				      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
				   }
	******************************************************************************************************************************
	Spring Boot - Servlet Filter**************************************************************************************************
		Un filtro es un objeto que se utiliza para interceptar las solicitudes y respuestas HTTP de su aplicación. Al usar el 
		filtro, podemos realizar dos operaciones en dos instancias:

			Antes de enviar la solicitud al controlador
			Antes de enviar una respuesta al cliente.

		El siguiente código muestra el código de muestra para una clase de implementación de filtro de servlet con la 
		anotación @Component.

			@Component
			public class SimpleFilter implements Filter {
			   @Override
			   public void destroy() {}

			   @Override
			   public void doFilter
			      (ServletRequest request, ServletResponse response, FilterChain filterchain) 
			      throws IOException, ServletException {}

			   @Override
			   public void init(FilterConfig filterconfig) throws ServletException {}
			}
			------------------------------------------------------------------------------------------------------------
			@SpringBootApplication
			@RestController
			public class DemoApplication {
			   public static void main(String[] args) {
			      SpringApplication.run(DemoApplication.class, args);
			   }
			   @RequestMapping(value = "/")
			   public String hello() {
			      return "Hello World";
			   }
			}
	******************************************************************************************************************************
	Spring Boot - Tomcat Port Number**********************************************************************************************
		Spring Boot le permite ejecutar la misma aplicación más de una vez en un número de puerto diferente. En este capítulo, 
		aprenderá sobre esto en detalle. Tenga en cuenta que el número de puerto predeterminado 8080.

		***Puerto personalizado
		En el archivo application.properties , podemos establecer un número de puerto personalizado para la propiedad server.port

			server.port = 9090

		***Puerto aleatorio
		En el archivo application.properties , podemos establecer un número de puerto aleatorio para la propiedad server.port

			server.port = 0
		
		Nota : si el número de puerto del servidor es 0 al iniciar la aplicación Spring Boot, Tomcat usa el número de puerto aleatorio.
	******************************************************************************************************************************	
	Spring Boot - Rest Template***************************************************************************************************
		Referencia --> http://www.profesor-p.com/2019/08/03/trabajando-con-la-clase-resttemplate/
		Rest Template se utiliza para crear aplicaciones que consumen RESTful Web Services. Puede utilizar el método exchange() 
		para consumir los servicios web para todos los métodos HTTP. 

		RestTemplate esta en el core de Spring por lo cual no es necesario instalar ninguna dependencia. Lo puedes encontrar en 
		el paquete org.springframework.web.client.RestTemplate

		Para hacer una petición a un recurso web con RestTemplate simplemente se escribiría este código:

			ResponseEntity<Customer> responseEntity= new RestTemplate().getForEntity(URL, Customer.class);

		Donde URLseria la dirección donde queremos realizar la petición (por ejemplo) y Customer es el objeto que esperamos que 
		nos vaya a devolver esa petición. La petición seria del tipo GET ya que hemos utilizado la función getForEntity.

		En esa llamada se recibe un objeto ResponseEntity donde estará embebido el objeto del tipo indicado, pero si solo nos 
		interesa recoger el cuerpo del mensaje podríamos utilizar la función getForObject, sin embargo el recoger la clase 
		ResponseEntity nos ofrece una información que a menudo es necesaria.

		De esta clase podremos utilizar, entre otras, las siguientes funciones:

			getStatusCode()
				Esta función nos permitirá saber el estado HTTP retornado por la petición. Devuelve un tipo HttpStatus

				Así para saber si el servidor devolvió un OK podremos poner

				  if (responseEntity.getStatusCode()==HttpStatus.OK) { // Todo fue bien
				  ....
				  }

			getHeaders()
				Devuelve un objeto HttpHeaders en el cual tendremos las cabeceras devueltas por el servidor.

			getBody()
				Devuelve una instancia de la clase devuelto por el servidor. En nuestro ejemplo anterior devolvería un objeto del 
				tipo Customer
		----------------------------------------------------------------------------------------------------------------------
		1.1 Capturar la excepción del tipo HttpClientErrorException
			Este sería el método fácil. Para ello simplemente deberemos meter entre un try/catch la llamada a la función 
			de RestTemplate.

			try {
			    responseEntity = new RestTemplate().getForEntity(localUrl, String.class);
			} catch (HttpClientErrorException k1) {            
			    return "Http code is not 2XX. The server responded: " + k1.getStatusCode() + 
			        " Cause: "+ k1.getResponseBodyAsString();
			} catch (RestClientException k) {
			    return "The server didn't respond: " + k.getMessage();
			}
		---------------------------------------------------------------------------------------------------------------------
		 Ejemplo______________________________________________________________________________________________________________
		 	public static void main(String[] args) {
				SpringApplication.run(TutoSpringBootApplication.class, args);
				System.out.println("hello");
				Product producto = new Product();
				ResponseEntity<Product> responceEntity = new RestTemplate().getForEntity("http://localhost:9090/products/1", Product.class);
				System.out.println("*********************************************************************");
				System.out.println("Probando getStatusCode().");
				System.out.println(responceEntity.getStatusCode());
				System.out.println("Probando getBody().");
				producto = responceEntity.getBody();
				System.out.println(producto.toString());
				if(responceEntity.getStatusCode() == HttpStatus.OK) {
					System.out.println("Todo fue bien");
				}
				System.out.println("Probando getHeaders().");
				System.out.println(responceEntity.getHeaders());
				System.out.println("Prueba de getProducto");
				
				System.out.println("*********************************************************************");
				System.out.println(getProducto());
			}
			
			
			public static String getProducto()	{
				try {
				    ResponseEntity responseEntity = new RestTemplate().getForEntity("http:/localhost:8080/DOWN", String.class);
				} catch (HttpClientErrorException k1) {            
				    return "Http code is not 2XX. The server responded: " + k1.getStatusCode() + 
				        " Cause: "+ k1.getResponseBodyAsString();
				} catch (RestClientException k) {
				    return "The server didn't respond: " + k.getMessage();
				}
				return "";
			}

		GET------------------------------------------------------------------------------------------------------------------
			

			Consumir la API GET mediante el método RestTemplate - exchange ()

			Tendrá que seguir los puntos dados para consumir la API -

				*Conectó automáticamente el objeto de plantilla de descanso.
				*Utilice HttpHeaders para configurar los encabezados de solicitud.
				*Utilice HttpEntity para envolver el objeto de solicitud.
				*Proporcione la URL, HttpMethod y el tipo de retorno para el método Exchange ().


			@RestController
			public class ConsumeWebService {
				@Autowired
				RestTemplate restTemplate;
				
				 @RequestMapping(value = "/template/products")
				 public String getProductList() {
				      HttpHeaders headers = new HttpHeaders();
				      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				      HttpEntity <String> entity = new HttpEntity<String>(headers);
				      
				      return restTemplate.exchange("http://localhost:9090/products", HttpMethod.GET, entity, String.class).getBody();
				   }
			}
			______________________________________________________________________________________________________________
			@SpringBootApplication
			@RestController
			public class TutoSpringBootApplication implements CommandLineRunner{


				public static void main(String[] args) {
					SpringApplication.run(TutoSpringBootApplication.class, args);
			
				}
				
				@Bean
			   public RestTemplate getRestTemplate() {
			      return new RestTemplate();
			   }
				
	
			}
		POST-----------------------------------------------------------------------------------------------------------------
			Consumir la API POST mediante el método RestTemplate - exchange ()

			Suponga que esta URL http://localhost:8080/products 

			devuelve la respuesta que se muestra a continuación, vamos a consumir esta respuesta de API mediante el uso de 
			la plantilla Rest.

			El código que se proporciona a continuación es el cuerpo de la solicitud:

				{
				   "id":"3",
				   "name":"Ginger"
				}

			El código que se proporciona a continuación es el cuerpo de la respuesta:

				Product is created successfully
			
			Deberá seguir los puntos que se indican a continuación para consumir la API:

				*Conectó automáticamente el objeto de plantilla de descanso.
				*Utilice HttpHeaders para establecer los encabezados de solicitud.
				*Utilice HttpEntity para envolver el objeto de solicitud. Aquí, envolvemos el objeto Producto para enviarlo 
				al cuerpo de la solicitud.
				*Proporcione la URL, HttpMethod y el tipo de retorno para el método exchange ().

			@RestController
			public class ConsumeWebService {
			   @Autowired
			   RestTemplate restTemplate;

			   @RequestMapping(value = "/template/products", method = RequestMethod.POST)
			   public String createProducts(@RequestBody Product product) {
			      HttpHeaders headers = new HttpHeaders();
			      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			      HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
			      
			      return restTemplate.exchange(
			         "http://localhost:8080/products", HttpMethod.POST, entity, String.class).getBody();
			   }
			}

			Ejemplo_____________________________________________________________________________________________________
				@RequestMapping(value="/template/products/post")
				 public String createProduct(){
					 ResponseEntity<Product> responseEntity = new RestTemplate().getForEntity("http://localhost:9090/products/1", Product.class);
					 Product producto = responseEntity.getBody();
					 
					 HttpHeaders headers = new HttpHeaders();
					 headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
					 HttpEntity <Product> entity = new HttpEntity<Product>(producto,headers); 
					 
					 return restTemplate.exchange("http://localhost:9090/products", HttpMethod.POST, entity, String.class).getBody();
				 }
		PUT------------------------------------------------------------------------------------------------------------------
			Consumir la API PUT mediante el método RestTemplate - exchange ()

			Supongamos que esta URL http: //localhost:8080/products/3 
			devuelve la siguiente respuesta y vamos a consumir esta respuesta de API utilizando Rest Template.

			El código que se proporciona a continuación es el cuerpo de la solicitud:

				{
				   "name":"Indian Ginger"
				}

			El código que se proporciona a continuación es el cuerpo de la respuesta:

				Product is updated successfully
			
			Deberá seguir los puntos que se indican a continuación para consumir la API:

				*Conectó automáticamente el objeto de plantilla de descanso.
				*Utilice HttpHeaders para configurar los encabezados de solicitud.
				*Utilice HttpEntity para envolver el objeto de solicitud. Aquí, envolvemos el objeto Producto para enviarlo al 
				cuerpo de la solicitud.
				*Proporcione la URL, HttpMethod y el tipo de retorno para el método exchange ().

				@RestController
				public class ConsumeWebService {
				   @Autowired
				   RestTemplate restTemplate;

				   @RequestMapping(value = "/template/products/{id}", method = RequestMethod.PUT)
				   public String updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
				      HttpHeaders headers = new HttpHeaders();
				      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				      HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
				      
				      return restTemplate.exchange(
				         "http://localhost:8080/products/"+id, HttpMethod.PUT, entity, String.class).getBody();
				   }
				}

				Ejemplo____________________________________________________________________________________________________

					 @RequestMapping(value="/template/products/{id}")
					 public String updateProduct(@PathVariable String id) {
						 ResponseEntity<Product> responseEntity = new RestTemplate().getForEntity("http://localhost:9090/products/1", Product.class);
						 Product producto = responseEntity.getBody();
						 producto.setName("Elvis");
						 
						 HttpHeaders headers = new HttpHeaders();
						 headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
						 HttpEntity<Product> entity = new HttpEntity<Product>(producto,headers);
						 
						 return restTemplate.exchange("http://localhost:9090/products/"+id, HttpMethod.PUT, entity, String.class).getBody();
					 }
	******************************************************************************************************************************
	Spring Boot - Service Components**********************************************************************************************
		Los componentes de servicio son el archivo de clase que contiene la anotación @Service. Estos archivos de clase se 
		utilizan para escribir lógica empresarial en una capa diferente, separada del archivo de clase @RestController. La 
		lógica para crear un archivo de clase de componente de servicio se muestra aquí:

			public interface ProductService {
			}

		La clase que implementa la interfaz con la anotación @Service es la que se muestra:

			@Service
			public class ProductServiceImpl implements ProductService {
			}
		Ejemplo_______________________________________________________________________________________________________________
				public interface IProductService {
					
					public abstract void createProduct(Product producto);
					public abstract void updateProduct(Long id, Product producto);
					public abstract void deleteProduct(Long id);
					public abstract Collection<Object> getProducts();
					
				}
			--------------------------------------------------------------------------------------------------------------
				@Service
				public class ProductServiceImpl implements IProductService {
		
				private static Map<String,Object> mapa = new HashMap<>();
				
				static {
					Product producto1 = new Product();
					producto1.setId("1");
					producto1.setName("Elvis");
					mapa.put(producto1.getId(), producto1.getName());
					

					Product producto2 = new Product();
					producto2.setId("2");
					producto2.setName("Diego");
					mapa.put(producto2.getId(), producto2.getName());
				}
				
				
					@Override
					public void createProduct(Product producto) {
						mapa.put(producto.getId(), producto.getName());
					}

					@Override
					public void updateProduct(Long id, Product producto) {
						mapa.remove(id);
						producto.setId(id.toString());
						mapa.put(producto.getId(), producto.getName());
					}

					@Override
					public void deleteProduct(Long id) {
						mapa.remove(id.toString());
					}

					@Override
					public Collection<Object> getProducts() {
						return mapa.values();
					}

				}
			--------------------------------------------------------------------------------------------------------------
				@RestController
				@RequestMapping("/api")
				public class ProductServiciosController {
	******************************************************************************************************************************
	Spring Boot - Thymeleaf*******************************************************************************************************
		Thymeleaf es una biblioteca basada en Java que se utiliza para crear una aplicación web. Proporciona un buen soporte 
		para servir un XHTML / HTML5 en aplicaciones web. 

		Plantillas de Thymeleaf
		Thymeleaf convierte sus archivos en archivos XML bien formados. Contiene 6 tipos de plantillas como se indica a continuación:

				XML
				XML válido
				XHTML
				XHTML válido
				HTML5
				HTML5 heredado

		Todas las plantillas, excepto Legacy HTML5, se refieren a archivos XML válidos bien formados. El HTML5 heredado nos 
		permite representar las etiquetas HTML5 en una página web, incluidas las etiquetas no cerradas.

		*Aplicación web
			Puede utilizar las plantillas de Thymeleaf para crear una aplicación web en Spring Boot. Deberá seguir los pasos a 
			continuación para crear una aplicación web en Spring Boot utilizando Thymeleaf.

			Use el siguiente código para crear un archivo de clase @Controller para redirigir el URI de solicitud a un 
			archivo HTML:

				package com.tutorialspoint.demo.controller;

				import org.springframework.stereotype.Controller;
				import org.springframework.web.bind.annotation.RequestMapping;

				@Controller
				public class WebController {
				   @RequestMapping(value = "/index")
				   public String index() {
				      return "index";
				   }
				}

			En el ejemplo anterior, el URI de la solicitud es / index y el control se redirige al archivo index.html. Tenga 
			en cuenta que el archivo index.html debe colocarse en el directorio de plantillas y todos los archivos JS y CSS 
			deben colocarse en el directorio estático en classpath. En el ejemplo que se muestra, usamos un archivo CSS 
			para cambiar el color del texto.

			Puede usar el siguiente código y crear un archivo CSS en una carpeta separada css y nombrar el archivo como styles.css -

				h4 {
				   color: red;
				}

			El código para el archivo index.html se proporciona a continuación:

				<!DOCTYPE html>
				<html>
				   <head>
				      <meta charset = "ISO-8859-1" />
				      <link href = "css/styles.css" rel = "stylesheet"/>
				      <title>Spring Boot Application</title>
				   </head>
				   <body>
				      <h4>Welcome to Thymeleaf Spring Boot web application</h4>
				   </body>
				</html>

			Ahora, necesitamos agregar la dependencia Spring Boot Starter Thymeleaf en nuestro archivo de configuración de compilación.

			Los usuarios de Maven pueden agregar la siguiente dependencia en el archivo pom.xml:

				<dependency>
				   <groupId>org.springframework.boot</groupId>
				   <artifactId>spring-boot-starter-thymeleaf</artifactId>
				</dependency>

			El código para el archivo de clase de la aplicación Spring Boot principal se proporciona a continuación:

				package com.tutorialspoint.demo;

				import org.springframework.boot.SpringApplication;
				import org.springframework.boot.autoconfigure.SpringBootApplication;

				@SpringBootApplication
				public class DemoApplication {
				   public static void main(String[] args) {
				      SpringApplication.run(DemoApplication.class, args);
				   }
				}
	******************************************************************************************************************************	
	Consuming RESTful Web Services************************************************************************************************
		Este capítulo discutirá en detalle sobre el consumo de servicios web RESTful mediante el uso de jQuery AJAX.

		Cree una aplicación web Spring Boot simple y escriba archivos de clase de controlador que se utilizan para redireccionar 
		al archivo HTML para consumir los servicios web RESTful.

		Necesitamos agregar el iniciador de Spring Boot Thymeleaf y la dependencia Web en nuestro archivo de configuración de compilación.

		Para los usuarios de Maven, agregue las siguientes dependencias en su archivo pom.xml.

			<dependency>
			   <groupId>org.springframework.boot</groupId>
			   <artifactId>spring-boot-starter-thymeleaf</artifactId>
			</dependency>

			<dependency>
			   <groupId>org.springframework.boot</groupId>
			   <artifactId>spring-boot-starter-web</artifactId>
			</dependency>

		El código para el archivo de clase @Controller se proporciona a continuación:

			@Controller
			public class ViewController {
			}

		Puede definir los métodos de solicitud URI para redireccionar al archivo HTML como se muestra a continuación:

			@RequestMapping(“/view-products”)
			public String viewProducts() {
			   return “view-products”;
			}
			@RequestMapping(“/add-products”)
			public String addProducts() {
			   return “add-products”;
			}
		Esta API http: //localhost:9090/products 
		debe devolver el siguiente JSON en respuesta como se muestra a continuación:

			[
			   {
			      "id": "1",
			      "name": "Honey"
			   },
			   {
			      "id": "2",
			      "name": "Almond"
			   }
			]

		Ahora, cree un archivo view-products.html en el directorio de plantillas en la ruta de clases.

		En el archivo HTML, agregamos la biblioteca jQuery y escribimos el código para consumir el servicio 
		web RESTful al cargar la página.
			<!DOCTYPE html>
			<html>
			   <head>
			      <meta charset = "ISO-8859-1"/>
			      <title>View Products</title>
			      <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
			      
			      <script>
			         $(document).ready(function(){
			            $.getJSON("http://localhost:9090/products", function(result){
			               $.each(result, function(key,value) {
			                  $("#productsJson").append(value.id+" "+value.name+" ");
			               }); 
			            });
			         });
			      </script>
			   </head>
			   
			   <body>
			      <div id = "productsJson"> </div>
			   </body>
			</html>

		El método POST y esta URL http: // localhost: 9090 / products deben contener el cuerpo de la solicitud y la respuesta a continuación.

		El código para el cuerpo de la solicitud se proporciona a continuación:

			{
			   "id":"3",
			   "name":"Ginger"
			}

		El código para el cuerpo de respuesta se proporciona a continuación:

		Product is created successfully
		Ahora, cree el archivo add-products.html en el directorio de plantillas en la ruta de clases.

		En el archivo HTML, agregamos la biblioteca jQuery y escribimos el código que envía el formulario al servicio web RESTful al hacer clic en el botón.

			<!DOCTYPE html>
			<html>
			   <head>
			      <meta charset = "ISO-8859-1" />
			      <title>Add Products</title>
			      <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
			      
			      <script>
			         $(document).ready(function() {
			            $("button").click(function() {
			               var productmodel = {
			                  id : "3",
			                  name : "Ginger"
			               };
			               var requestJSON = JSON.stringify(productmodel);
			               $.ajax({
			                  type : "POST",
			                  url : "http://localhost:9090/products",
			                  headers : {
			                     "Content-Type" : "application/json"
			                  },
			                  data : requestJSON,
			                  success : function(data) {
			                     alert(data);
			                  },
			                  error : function(data) {
			                  }
			               });
			            });
			         });
			      </script>
			   </head>
			   
			   <body>
			      <button>Click here to submit the form</button>
			   </body>
			</html>
	******************************************************************************************************************************
	Spring Boot - Soporte CORS****************************************************************************************************
		Cross-Origin Resource Sharing (CORS) es un concepto de seguridad que permite restringir los recursos implementados en los 
		navegadores web. Evita que el código JavaScript produzca o consuma las solicitudes contra un origen diferente.

		Por ejemplo, su aplicación web se ejecuta en el puerto 8080 y, al usar JavaScript, está intentando consumir servicios web 
		RESTful desde el puerto 9090. En tales situaciones, enfrentará el problema de seguridad de Intercambio de recursos entre 
		orígenes en sus navegadores web.

		Se necesitan dos requisitos para manejar este problema:

			Los servicios web RESTful deben admitir el intercambio de recursos entre orígenes.

			La aplicación de servicio web RESTful debe permitir el acceso a las API desde el puerto 8080.

		En este capítulo, aprenderemos en detalle sobre cómo habilitar solicitudes de origen cruzado para una aplicación de 
		servicio web RESTful.

		***Habilitar CORS en el método del controlador
			Necesitamos establecer los orígenes del servicio web RESTful usando la anotación @CrossOrigin para el método del 
			controlador. Esta anotación @CrossOrigin admite una API REST específica, y no para toda la aplicación.

				@RequestMapping(value = "/products")
				@CrossOrigin(origins = "http://localhost:8080")

				public ResponseEntity<Object> getProduct() {
				   return null;
				}

		***Configuración CORS global
			Necesitamos definir la configuración de @Bean mostrada para establecer el soporte de configuración CORS 
			globalmente para su aplicación Spring Boot.

				@Bean
				public WebMvcConfigurer corsConfigurer() {
				   return new WebMvcConfigurerAdapter() {
				      @Override
				      public void addCorsMappings(CorsRegistry registry) {
				         registry.addMapping("/products").allowedOrigins("http://localhost:9000");
				      }    
				   };
				}
	******************************************************************************************************************************
	Spring Boot - Internationalization********************************************************************************************
		La internacionalización es un proceso que hace que su aplicación se adapte a diferentes idiomas y regiones sin cambios 
		de ingeniería en el código fuente. En sus palabras, la internacionalización es una preparación para la localización.

		En este capítulo, aprenderemos en detalle cómo implementar la internacionalización en Spring Boot.

		***Dependencias
			Necesitamos la dependencia Spring Boot Starter Web y Spring Boot Starter Thymeleaf para desarrollar una aplicación 
			web en Spring Boot.

			Maven
				<dependency>
				   <groupId>org.springframework.boot</groupId>
				   <artifactId>spring-boot-starter-web</artifactId>
				</dependency>

				<dependency>
				   <groupId>org.springframework.boot</groupId>
				   <artifactId>spring-boot-starter-thymeleaf</artifactId>
				</dependency>

		***LocaleResolver
			Necesitamos determinar la configuración regional predeterminada de su aplicación. Necesitamos agregar el bean 
			LocaleResolver en nuestra aplicación Spring Boot.

				@Bean
				public LocaleResolver localeResolver() {
				   SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
				   sessionLocaleResolver.setDefaultLocale(Locale.US);
				   return sessionLocaleResolver;
				}

		***LocaleChangeInterceptor
			LocaleChangeInterceptor se utiliza para cambiar la nueva configuración regional en función del valor del parámetro 
			de idioma agregado a una solicitud.

				@Bean
				public LocaleChangeInterceptor localeChangeInterceptor() {
				   LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
				   localeChangeInterceptor.setParamName("language");
				   return localeChangeInterceptor;
				}

			Para tener este efecto, debemos agregar LocaleChangeInterceptor en el interceptor de registro de la aplicación. 
			La clase de configuración debe extender la clase WebMvcConfigurerAdapter y anular el método addInterceptors ().

				@Override
				public void addInterceptors(InterceptorRegistry registry) {
				   registry.addInterceptor(localeChangeInterceptor());
				}

		***Fuentes de mensajes
			La aplicación Spring Boot de forma predeterminada toma las fuentes de mensajes de la carpeta src/main/resources 
			debajo de la ruta de clase. El nombre del archivo de mensajes de la configuración regional predeterminada debe 
			ser messages.properties y los archivos de cada configuración regional deben denominarse messages_XX.properties. 
			El "XX" representa el código local.

			Todas las propiedades del mensaje deben usarse como valores de pares de claves. Si no se encuentran propiedades 
			en el entorno local, la aplicación utiliza la propiedad predeterminada del archivo messages.properties.

			Los messages.properties predeterminados serán los siguientes:

				welcome.text=Hi Welcome to Everyone

			El idioma francés messages_fr.properties será como se muestra:

				welcome.text=Salut Bienvenue à tous

			Nota : el archivo de origen de los mensajes debe guardarse como formato de archivo "UTF-8".
		Ejemplo_______________________________________________________________________________________________________________
			*Internacionalizacion.java
				@Configuration
				public class Internacionalizacion implements WebMvcConfigurer {

					//Configuración regional predeterminada de su aplicación
					@Bean
					public LocaleResolver localeResolver() {
						SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
						sessionLocaleResolver.setDefaultLocale(Locale.US);
						return sessionLocaleResolver;
					}

					/*
					 * LocaleChangeInterceptor se utiliza para cambiar la nueva configuración regional en función del valor del parámetro 
					 * de idioma agregado a una solicitud.
					 */
					@Bean
					public LocaleChangeInterceptor localeChangeInterceptor() {
						LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
						localeChangeInterceptor.setParamName("language");
						return localeChangeInterceptor;
					}

					/*
					 * Se agrega LocaleChangeInterceptor en el interceptor de registro de la aplicación.
					 */
					@Override
					public void addInterceptors(InterceptorRegistry registry) {
						registry.addInterceptor(localeChangeInterceptor());
					}

				}
			----------------------------------------------------------------------------------------------------------------
			*LocalleController
				@Controller
				public class LocaleController {
					@RequestMapping("/locale")
					public String locale() {
						return "locale";
					}
				}
			---------------------------------------------------------------------------------------------------------------
			*locale.html
				<!DOCTYPE html>
				<html>
				   <head>
				      <meta charset = "ISO-8859-1"/>
				      <title>Internationalization</title>
				   </head>
				   <body>
				      <h1 th:text = "#{welcome.text}"></h1>
				   </body>
				</html>
			--------------------------------------------------------------------------------------------------------------
			*messages_es.properties
				welcome.text = Bienvenidos a test
			*messages_us.properties
				welcome.text = welcome a test
	******************************************************************************************************************************
	Spring Boot - Scheduling******************************************************************************************************
		La programación es un proceso de ejecución de tareas para un período de tiempo específico. Spring Boot proporciona un 
		buen soporte para escribir un programador en las aplicaciones Spring.

		Expresión Java Cron
		Las expresiones Java Cron se utilizan para configurar las instancias de CronTrigger, una subclase de org.quartz.Trigger. 
		Para obtener más información sobre la expresión cron de Java, puede consultar este enlace:

			https://docs.oracle.com/cd/E12058_01/doc/doc.1014/e12030/cron_expressions.htm

		La anotación @EnableScheduling se usa para habilitar el programador para su aplicación. Esta anotación debe agregarse al 
		archivo de clase de la aplicación Spring Boot principal.

			@SpringBootApplication
			@EnableScheduling

			public class DemoApplication {
			   public static void main(String[] args) {
			      SpringApplication.run(DemoApplication.class, args);
			   }
			}

		La anotación @Scheduled se utiliza para activar el programador durante un período de tiempo específico.

			@Scheduled(cron = "0 * 9 * * ?")
			public void cronJobSch() throws Exception {
			}

		***Tipo de interés fijo
			El programador de tasa fija se utiliza para ejecutar las tareas en el momento específico. No espera a que se complete la tarea anterior. Los valores deben estar en milisegundos. El código de muestra se muestra aquí:

			@Scheduled(fixedRate = 1000)
			public void fixedRateSch() { 
			}

		***Retraso fijo
		El programador de retardo fijo se utiliza para ejecutar las tareas en un momento específico. Debe esperar a que se complete la tarea anterior. Los valores deben estar en milisegundos. Aquí se muestra un código de muestra:

			@Scheduled(fixedDelay = 1000, initialDelay = 1000)
			public void fixedDelaySch() {
			}

		Nota: Este ejemplo utiliza fixedRate, que especifica el intervalo entre invocaciones de métodos, medido desde 
		la hora de inicio de cada invocación. Hay otras opciones , como fixedDelay, que especifica el intervalo entre 
		invocaciones medido desde la finalización de la tarea. También puede utilizar @Scheduled(cron=". . .")expresiones 
		para una programación de tareas más sofisticada.
		Ejemplo_______________________________________________________________________________________________________________
			@Component
			public class Scheduler {

				@Scheduled(cron = "0 22 13 ? * *")
				public void cronJobSch() {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
					Date now = new Date();
					String strDate = sdf.format(now);
					System.out.println("Java cron job expression:: " + strDate);
				}

				@Scheduled(fixedRate = 5000)
				public void fixedRateSch() {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

					Date now = new Date();
					String strDate = sdf.format(now);
					System.out.println("Fixed Rate scheduler:: " + strDate);
				}

				@Scheduled(fixedDelay = 1000, initialDelay = 3000)
				public void fixedDelaySch() {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
					Date now = new Date();
					String strDate = sdf.format(now);
					System.out.println("Fixed Delay scheduler:: " + strDate);
				}
			}
		----------------------------------------------------------------------------------------------------------------------
			@SpringBootApplication
			@EnableScheduling
			public class TutoSpringBootApplication {

				 

				public static void main(String[] args) {
					SpringApplication.run(TutoSpringBootApplication.class, args);
				}
				
				@Bean
			   public RestTemplate getRestTemplate() {
			      return new RestTemplate();
			   }
			}
	******************************************************************************************************************************
	Spring Boot - Eureka Server***************************************************************************************************
		Eureka Server es una aplicación que contiene la información sobre todas las aplicaciones de servicio al cliente. Cada 
		servicio Micro se registrará en el servidor Eureka y el servidor Eureka conoce todas las aplicaciones cliente que se 
		ejecutan en cada puerto y dirección IP. Eureka Server también se conoce como Discovery Server.

		***Construyendo un servidor Eureka
			Eureka Server viene con el paquete de Spring Cloud. Para ello, necesitamos desarrollar el servidor Eureka y ejecutarlo
			en el puerto predeterminado 8761.

			necesitamos agregar la anotación @EnableEurekaServer. La anotación @EnableEurekaServer se utiliza para hacer que 
			su aplicación Spring Boot actúe como un servidor Eureka.

			El código para el archivo de clase de la aplicación Spring Boot principal es el que se muestra a continuación:

				package com.tutorialspoint.eurekaserver;

				import org.springframework.boot.SpringApplication;
				import org.springframework.boot.autoconfigure.SpringBootApplication;
				import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

				@SpringBootApplication
				@EnableEurekaServer
				public class EurekaserverApplication {
				   public static void main(String[] args) {
				      SpringApplication.run(EurekaserverApplication.class, args);
				   }
				}

			Asegúrese de que la dependencia del servidor Spring Cloud Eureka esté agregada en su archivo de configuración de compilación.

			El código para la dependencia del usuario de Maven se muestra a continuación:

				<dependency>
				<groupId>org.springframework.cloud</groupId>
				   <artifactId>spring-cloud-starter-eureka-server</artifactId>
				</dependency>

			De forma predeterminada, Eureka Server se registra en el descubrimiento. Debe agregar la configuración dada a 
			continuación en su archivo application.properties

				eureka.client.registerWithEureka = false
				eureka.client.fetchRegistry = false
				server.port = 8761

			Ahora, puede crear un archivo JAR ejecutable y ejecutar la aplicación Spring Boot utilizando los comandos de 
			Maven o Gradle que se muestran a continuación:

			Para Maven, use el comando como se muestra a continuación:

				mvn clean install
			
			Después de "BUILD SUCCESS", puede encontrar el archivo JAR en el directorio de destino.

			Después de "BUILD SUCCESSFUL", puede encontrar el archivo JAR en el directorio build / libs.

			Ahora, ejecute el archivo JAR usando el siguiente comando:

			 java –jar <JARFILE> 

			Ahora, presione la URL http://localhost:8761/ 
			en su navegador web y podrá encontrar el servidor Eureka ejecutándose en el puerto 8761
	******************************************************************************************************************************
	Service Registration with Eureka**********************************************************************************************
		En este capítulo, aprenderá en detalle sobre cómo registrar la aplicación de servicio Spring Boot Micro en el servidor 
		Eureka. Antes de registrar la aplicación, asegúrese de que Eureka Server se esté ejecutando en el puerto 8761 o primero 
		compile el Eureka Server y ejecútelo. 

		Primero, debe agregar las siguientes dependencias en nuestro archivo de configuración de compilación para registrar 
		el microservicio con el servidor Eureka.

		Los usuarios de Maven pueden agregar las siguientes dependencias en el archivo pom.xml :

			<dependency>
			   <groupId>org.springframework.cloud</groupId>
			   <artifactId>spring-cloud-starter-eureka</artifactId>
			</dependency>

		Ahora, necesitamos agregar la anotación @EnableEurekaClient en el archivo de clase de la aplicación Spring Boot 
		principal. La anotación @EnableEurekaClient hace que su aplicación Spring Boot actúe como un cliente de Eureka.

		La aplicación principal de Spring Boot es la siguiente:

			package com.tutorialspoint.eurekaclient;

			import org.springframework.boot.SpringApplication;
			import org.springframework.boot.autoconfigure.SpringBootApplication;
			import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

			@SpringBootApplication
			@EnableEurekaClient
			public class EurekaclientApplication {
			   public static void main(String[] args) {
			      SpringApplication.run(EurekaclientApplication.class, args);
			   }
			}

		Para registrar la aplicación Spring Boot en Eureka Server, debemos agregar la siguiente configuración en nuestro 
		archivo application.properties

			eureka.client.serviceUrl.defaultZone  = http://localhost:8761/eureka
			eureka.client.instance.preferIpAddress = true
			spring.application.name = eurekaclient

		Ahora, agregue Rest Endpoint para devolver String en la aplicación principal Spring Boot y la dependencia web 
		Spring Boot Starter en el archivo de configuración de compilación. Observe el código que se proporciona a continuación:

			package com.tutorialspoint.eurekaclient;

			import org.springframework.boot.SpringApplication;
			import org.springframework.boot.autoconfigure.SpringBootApplication;
			import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
			import org.springframework.web.bind.annotation.RequestMapping;
			import org.springframework.web.bind.annotation.RestController;

			@SpringBootApplication
			@EnableEurekaClient
			@RestController
			public class EurekaclientApplication {
			   public static void main(String[] args) {
			      SpringApplication.run(EurekaclientApplication.class, args);
			   }
			   @RequestMapping(value = "/")
			   public String home() {
			      return "Eureka Client application";
			   }
			}

		Ejemplo_______________________________________________________________________________________________________________
		*pom.xml
			<dependencies>
				<dependency>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-web</artifactId>
				</dependency>
				<dependency>
					<groupId>org.springframework.cloud</groupId>
					<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
				</dependency>

				<dependency>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-test</artifactId>
					<scope>test</scope>
				</dependency>
			</dependencies>
		*EurekaClienteApplication
			@EnableEurekaClient
			@SpringBootApplication
			@RestController
			public class EurekaclientApplication {

				public static void main(String[] args) {
					SpringApplication.run(EurekaclientApplication.class, args);
				}

				@RequestMapping(value = "/")
				public String home() {
					return "Eureka Client application";
				}
			}
		*Application.properties
			#Config eureka como cliente
			spring.application.name=eurecaclient
			#se habilita puerto de forma ramdom, asigna puerto automaticamente
			server.port=${PORT:0}
			#Se configura intancia id en eureka de forma random
			eureka.instance.instance-id=${spring.application.name}:${random.value}
			#ruta de eureka
			eureka.client.service-url.defaultZone=http://localhost:8761/eureka
	******************************************************************************************************************************
	Spring Boot - Servidor proxy Zuul y enrutamiento******************************************************************************
		Zuul Server es una aplicación de puerta de enlace que maneja todas las solicitudes y realiza el enrutamiento dinámico
		de las aplicaciones de microservicio. El servidor Zuul también se conoce como servidor perimetral.

		***Creación de la aplicación de servidor Zuul
			El servidor Zuul está incluido con la dependencia de Spring Cloud.
			Agregue la anotación @EnableZuulProxy en su aplicación principal Spring Boot. La anotación @EnableZuulProxy se 
			utiliza para hacer que su aplicación Spring Boot actúe como un servidor proxy Zuul.

				package com.tutorialspoint.zuulserver;

				import org.springframework.boot.SpringApplication;
				import org.springframework.boot.autoconfigure.SpringBootApplication;
				import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

				@SpringBootApplication
				@EnableZuulProxy
				public class ZuulserverApplication {
				   public static void main(String[] args) {
				      SpringApplication.run(ZuulserverApplication.class, args);
				   }
				}

			Tendrá que agregar la dependencia Spring Cloud Starter Zuul en nuestro archivo de configuración de compilación.

			Los usuarios de Maven deberán agregar la siguiente dependencia en su archivo pom.xml :

				<dependency>
				   <groupId>org.springframework.cloud</groupId>
				   <artifactId>spring-cloud-starter-zuul</artifactId>
				</dependency>

			Para el enrutamiento de Zuul, agregue las siguientes propiedades en su archivo application.properties

				spring.application.name = zuulserver
				zuul.routes.products.path = /api/demo/**   **/
				zuul.routes.products.url = http://localhost:8080/
				server.port = 8111

			Esto significa que las llamadas http a / api / demo / se reenvían al servicio de productos. Por ejemplo, 
			/api/demo/products se reenvía a /products 

			Nota : la aplicación http: //localhost:8080/ 
			ya debería estar ejecutándose antes de enrutar a través de Zuul Proxy.


			Ejemplo_________________________________________________________________________________________________________
				***pom.xml
					<dependency>
						<groupId>org.springframework.boot</groupId>
						<artifactId>spring-boot-starter-web</artifactId>
					</dependency>
					<dependency>
						<groupId>org.springframework.cloud</groupId>
						<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
					</dependency>
					<dependency>
						<groupId>org.springframework.cloud</groupId>
						<artifactId>spring-cloud-starter-netflix-zuul</artifactId>
					</dependency>
				***MicroserviciosZuulApplication.java
					//Se habilita zuulProxy para el enrutamiento de microservicios
					@EnableZuulProxy
					//Se habilita eurekaClient
					@EnableEurekaClient
					@SpringBootApplication
					public class MicroserviciosZuulApplication {

						public static void main(String[] args) {
							SpringApplication.run(MicroserviciosZuulApplication.class, args);
						}

					}
				***Application.properties
					spring.application.name=microservicios-zuul
					server.port=8090

					#Se registra donde se encuentra eureka 
					eureka.client.service-url.defaultZone=http://localhost:8761/eureka

					#Se crea la ruta al microservicio usuario
					zuul.routes.usuarios.service-id=microservicios-usuarios
					zuul.routes.usuarios.path=/api/alumnos/**        **/

					#Se crea la ruta al microservicio cursos
					zuul.routes.cursos.service-id=microservicios-cursos
					zuul.routes.cursos.path=/api/cursos/**       **/

					#Se crea la ruta al microservicio examenes
					zuul.routes.examenes.service-id=microservicios-examenes
					zuul.routes.examenes.path=/api/examenes/**

	******************************************************************************************************************************
	
****************************************************************************************************************************************

