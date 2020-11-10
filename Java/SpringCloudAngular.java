Seccion 2: Microservicio Usuario (Backend)*************************************************************************************************
6.- ******************************************************************************************************Creacion de microservicio Usuario
	1.- Desde sts se crea un new Spring Starter Project
		1.1.- Se crea proyecto
				name 			 --> microservicios-usuarios
				type         -->Maven
				Packaging    -->jar
				Java version --> 8
				Group 		 --> com.alfonso.app.usuarios
				artifact	 --> microservicios-usuarios
				package      --> com.alfonso.app.usuarios

				next-->

				Spring Boot Project 		--> 2.3.4
				Seleccionas dependencias	-->	Spring Boot DeevTool
											--> Spring Data JPA
											--> MySQL Driver
											--> Spring Web
8.- ********************************************************************************************************Creacion de Clase Entity Alumno
	1.- Se crea paquete package com.alfonso.app.usuarios.models.entity;
	@Entity
	@Table(name = "alumnos")
	public class Alumno {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String nombre;
		private String apellido;
		private String email;
		
		@Column(name = "create_at")
		@Temporal(TemporalType.TIMESTAMP)
		private Date createAt;
		
		@PrePersist
		public void prePersist() {
			this.createAt = new Date();
		}

		{.. get y set ...}
	}
9.- ****************************************************************************************************************Creacion de Repositorio
	1.- Se crea paquete package com.alfonso.app.usuarios.models.repository;
	@Repository
	public interface IAlumnosRepository extends CrudRepository<Alumno, Long> {

	}
10.- ********************************************************************************************Creacion de componente Service para Alumno
	
	1.- Se crea interface IAlumnosService en el paquete package com.alfonso.app.usuarios.models.service;
		public interface IAlumnoService {
	
			public Iterable<Alumno> buscarTodos();
			
			public Optional<Alumno> BuscarXId(Long idAlumno);
			
			public Alumno guardar(Alumno alumno);
			
			public void deleteById(Long idAlumno);
		}
	2.- Se crea la implementacion AlumnoService.Java en el paquete package com.alfonso.app.usuarios.models.service;
		@Service
		public class AlumnoServiceImpl implements IAlumnoService {
			
			@Autowired
			private IAlumnosRepository alumnoRepo;

			@Override
			@Transactional(readOnly = true)
			public Iterable<Alumno> buscarTodos() {
				return alumnoRepo.findAll();
			}

			@Override
			@Transactional(readOnly = true)
			public Optional<Alumno> BuscarXId(Long idAlumno) {
				return alumnoRepo.findById(idAlumno);
			}

			@Override
			@Transactional
			public Alumno guardar(Alumno alumno) {
				return alumnoRepo.save(alumno);
			}

			@Override
			@Transactional
			public void deleteById(Long idAlumno) {
				alumnoRepo.deleteById(idAlumno);
			}

		}
11.- ***************************************************************************************************************Creacion de Controlador
	1.- Se crea paquete package com.alfonso.app.usuarios.controller;
	2.- Se crea controlador AlumnoController

		@RestController
		public class AlumnoController {

			@Autowired
			private IAlumnoService alumnoService;
			
			@GetMapping
			public ResponseEntity<?> listar(){
				return ResponseEntity.ok().body(alumnoService.buscarTodos());
			}
			
			@GetMapping("/{id}")
			public ResponseEntity<?> verAlumno(@PathVariable Long id){
				Optional<Alumno> opt = alumnoService.BuscarXId(id);
				if(opt.isEmpty()) {
					//Si no lo encuentra envia un codigo 404 y construye body vacio
					return ResponseEntity.notFound().build();
				}
				return ResponseEntity.ok(opt.get());
			}
			
			@PostMapping
			public ResponseEntity<?> guardar(@RequestBody Alumno alumno){
				Alumno alumnoBD = alumnoService.guardar(alumno);
				return ResponseEntity.status(HttpStatus.CREATED).body(alumnoBD);
			}
			
			@PutMapping("/{id}")
			public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id){
				Optional<Alumno> opt = alumnoService.BuscarXId(id);
				if(opt.isEmpty()) {
					return ResponseEntity.notFound().build();
				}
				
				Alumno alumnoDB = opt.get();
				alumnoDB.setNombre(alumno.getNombre());
				alumnoDB.setApellido(alumno.getApellido());
				alumnoDB.setEmail(alumno.getEmail());
				
				return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.guardar(alumnoDB));
			}
			
			@DeleteMapping("/{id}")
			public ResponseEntity<?> borrarAlumno(@PathVariable Long id){
				alumnoService.deleteById(id);
				return ResponseEntity.noContent().build();
			}
		}
12.- **************************************************************************************************Configuracion y creacion de BD Mysql
	1.- Se modifica application.properties
		#Configuracion del datasource
		#spring.datasource.url=jdbc:mysql://localhost/db_springboot_backend
		spring.datasource.url=jdbc:mysql://localhost:3306/db_microservicios?useSSL=false&serverTimezone=America/Argentina/Buenos_Aires&allowPublicKeyRetrieval=true
		spring.datasource.username=alfonso
		spring.datasource.password=danger120-
		spring.datasource.driver-class-name=com.mysql.jdbc.Driver
		#Se configura el dialecto
		#InnoDBDialec soporte a integridad referencial
		spring.jpa.database-platform=org.hibernate.dialect.MySQL57Dialect
		#Configuracion para que las tables se creen de forma automatica
		#a partir de las clases entity
		spring.jpa.generate-ddl=true
		#Configuracion para mostrar las consultas nativas SQL
		logging.level.org.hibernate.SQL=debug
	2.- Se crea la tabla de base de datasource
		create database db_microservicios;
Seccion 3: Backend Eureka Server registrando Microservicios********************************************************************************
	*Servidor de nombres para registrar los microservicios
	*El servicio se registra por un id
	* Registra el id, la ubicacion fisica, ip, puerto y metada propia del servicio
	* Solo nos comunicamos por medio del identificador del servicio
	*Se utiliza el balanceo de carga
16.- ***********************************************************************************************Creacion de servidor de registro eureka
	1.- Desde sts se crea un new Spring Starter Project
		1.1.- Se crea proyecto
				name 			 --> microservicios-eureka
				type         -->Maven
				Packaging    -->jar
				Java version --> 8
				Group 		 --> com.alfonso.app.eureka
				artifact	 --> microservicios-eureka
				package      --> com.alfonso.app.eureka

				next-->

				Spring Boot Project 		--> 2.3.4
				Seleccionas dependencias	-->	Spring Cloud Discovery --> Eureka Server
	2.- Se modifica MicroserviciosEurekaApplication.Java
		2.1.- Se habilita eureka
			//Se habilita eureka
			@EnableEurekaServer
			@SpringBootApplication
			public class MicroserviciosEurekaApplication {

				public static void main(String[] args) {
					SpringApplication.run(MicroserviciosEurekaApplication.class, args);
				}

			}
	3.- Se modifica application.properties, se configuran propiedades de eureka
		spring.application.name=microservicios-eureka-server
		server.port=8761

		#se deshabilita registro como  cliente
		eureka.client.register-with-eureka=false
		eureka.client.fetch-registry=false
17.- ***********************************************************************************Conexion de servicio usuario como cliente de Eureka
	1.- Se añade dependencia de client eureka server
		1.1.- click der proyecto microservicios-usuarios --> spring --> add started --> spring cloud discovery --> Eureka discovery Client
	2.- Se habilita la aplicacion como cliente de eureka
		2.1.- Se modifica microserviciosUsuariosApplication 
			//Se habilita como cliente de eureka
			@EnableEurekaClient
			@SpringBootApplication
			public class MicroserviciosUsuariosApplication {

				public static void main(String[] args) {
					SpringApplication.run(MicroserviciosUsuariosApplication.class, args);
				}

			}
	3.- Se modifica application.properties, se agregan configuraciones de cliente de eureka
		#Config eureka como cliente
		spring.application.name=microservicios-usuarios
		#se habilita puerto de forma ramdom, asigna puerto automaticamente
		server.port=${PORT:0}
		#Se configura intancia id en eureka de forma random
		eureka.instance.instance-id=${spring.application.name}:${random.value}
		#ruta de eureka
		eureka.client.service-url.defaultZone=http://localhost:8761/eureka
18.- **********************************************************************************************Creacion y configuracion de Zuul Gateway
	Gateway es la puerta de enlace a nuestros microservicios, es la puerta de entrada o de enlace al ecosistema de microservicios
	Zuul es un proxy que enruta nuestros microservicios
	1.- Desde sts se crea un new Spring Starter Project
		1.1.- Se crea proyecto
				name 			 --> microservicios-zuul
				type         -->Maven
				Packaging    -->jar
				Java version --> 8
				Group 		 --> com.alfonso.app.zuul
				artifact	 --> microservicios-zuul
				package      --> com.alfonso.app.zuul

				next-->

				Spring Boot Project 		--> 2.3.4
				Seleccionas dependencias	-->	Spring Boot DeevTool
											--> Spring Cloud Routing --> Zuul
											--> Spring Cloud Discovery --> Eureka Discovery Client
											--> Spring Web
	2.- Se modifica MicroserviciosZuulApplication.java, se habilita EurekaClient y ZuulProxy
		@EnableZuulProxy
		@EnableEurekaClient
		@SpringBootApplication
		public class MicroserviciosZuulApplication {

			public static void main(String[] args) {
				SpringApplication.run(MicroserviciosZuulApplication.class, args);
			}

		}
	3.- Se modifica application.properties, se configura enrutamiento a los microservicios-usuarios
		spring.application.name=microservicios-zuul
		server.port=8090

		#Se registra donde se encuentra eureka 
		eureka.client.service-url.defaultZone=http://localhost:8761/eureka
		#Se crea la ruta al microservicio usuario
		zuul.routes.usuarios.service-id=microservicios-usuarios
		zuul.routes.usuarios.path=/api/alumnos/**
		**/
	4.- Se prueba con postman
		4.1.- Se levantan los servicios en cada proyecto --> click der --> run as --> Spring Boot App  (en orden)
			*eureka
			*usuarios
			*zuul
		4.2.- Se prueban los servicios
			GET 	--> http://localhost:8090/api/alumnos/6  
			POST 	--> http://localhost:8090/api/alumnos
				{
			        "nombre": "ricardo",
			        "apellido": "areiza",
			        "email": "ricardo@gmail.com"
			    }
			PUT 	--> http://localhost:8090/api/alumnos/2
								{
			        "nombre": "ricardo",
			        "apellido": "areiza",
			        "email": "ricardo@Hotmail.com"
			    }

		
21.- *****************************************************************Crear libreria common para servicio generico y Reutilizacio de codigo
	1.- Desde sts se crea un new Spring Starter Project
		1.1.- Se crea proyecto
				name 			 --> microservicios-commons
				type         -->Maven
				Packaging    -->jar
				Java version --> 8
				Group 		 --> com.alfonso.commons
				artifact	 --> microservicios-commons
				package      --> com.alfonso.commons

				next-->

				Spring Boot Project 		--> 2.3.4
				Seleccionas dependencias	-->	Spring Web
											--> Spring Data JPA
	2.- Se modifica el pom.xml, se elimina el build, para utilizar este proyecto como libreria y no arranque el proyecto con la 
	clase principal
		<!-- Se comenta el build para que no arranque con la clase principal -->
		<!-- 	<build> -->
		<!-- 		<plugins> -->
		<!-- 			<plugin> -->
		<!-- 				<groupId>org.springframework.boot</groupId> -->
		<!-- 				<artifactId>spring-boot-maven-plugin</artifactId> -->
		<!-- 			</plugin> -->
		<!-- 		</plugins> -->
		<!-- 	</build> -->

	3.- Se elimina la clase principal MicroserviciosCommonsApplication.java
	4.- Se crean paquetes de service y controller
		com.alfonso.commons.services
		com.alfonso.commons.services
	5.- Se copian la interface y la clase de servicio del microservicio de usuarios a commons
	6.- Se modifica la interface para convertirla en una generica, para que no dependa de una entidad especifica, se utiliza el API 
	generics de java
		public interface IAlumnoService<E> {
	
			public Iterable<E> buscarTodos();
			
			public Optional<E> BuscarXId(Long idAlumno);
			
			public E guardar(E entity);
			
			public void deleteById(Long idAlumno);
		}
	7.- Se modifica la implementacion para que sea generica, 
		@Service
		//Se modifica para que la implementacion sea generica, R es cualquier tipo que extienda de CrudRepository
		public class AlumnoServiceImpl<E, R extends CrudRepository<E,Long>> implements IAlumnoService<E> {
			
			@Autowired
			//Se modifica metodo de acceso para que puedan ser utilizadas por las clases hijas
			protected R alumnoRepo;

			@Override
			@Transactional(readOnly = true)
			public Iterable<E> buscarTodos() {
				return alumnoRepo.findAll();
			}

			@Override
			@Transactional(readOnly = true)
			public Optional<E> BuscarXId(Long idAlumno) {
				return alumnoRepo.findById(idAlumno);
			}

			@Override
			@Transactional
			public E guardar(E entity) {
				return alumnoRepo.save(entity);
			}

			@Override
			@Transactional
			public void deleteById(Long idAlumno) {
				alumnoRepo.deleteById(idAlumno);
			}

		}
	8.- Se renombran tanto la interface como la clase de servicio
		refactor --> se cambia el nombre  --> CommonServiceImpl
											  ICommonService
	9.- Se modifica CommonServiceImpl, Se elimina la anotacion @Service, ya que no es un componente que se vaya ainyectar
	10.- Se añade dependencia de common en microservicio usuarios
		10.1.- Se copia del pom del proyecto commons, artifact, version y groupId y se añade como dependencia en microservicio usuarios
			<dependency>
				<groupId>com.alfonso.commons</groupId>
				<artifactId>microservicios-commons</artifactId>
				<version>0.0.1-SNAPSHOT</version>
			</dependency>
	11.- Se modifica IAlumnoService, se extiende de ICommonService
		public interface IAlumnoService extends ICommonService<Alumno>{
			
		//	public Iterable<Alumno> buscarTodos();
		//	
		//	public Optional<Alumno> BuscarXId(Long idAlumno);
		//	
		//	public Alumno guardar(Alumno alumno);
		//	
		//	public void deleteById(Long idAlumno);
		}
	12.- Se modifica AlumnoServiceImpl.java, se extiende de CommonServiceImpl
		@Service
		public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, IAlumnosRepository> implements IAlumnoService {
			
		//	@Autowired
		//	private IAlumnosRepository alumnoRepo;
		//
		//	@Override
		//	@Transactional(readOnly = true)
		//	public Iterable<Alumno> buscarTodos() {
		//		return alumnoRepo.findAll();
		//	}
		//
		//	@Override
		//	@Transactional(readOnly = true)
		//	public Optional<Alumno> BuscarXId(Long idAlumno) {
		//		return alumnoRepo.findById(idAlumno);
		//	}
		//
		//	@Override
		//	@Transactional
		//	public Alumno guardar(Alumno alumno) {
		//		return alumnoRepo.save(alumno);
		//	}
		//
		//	@Override
		//	@Transactional
		//	public void deleteById(Long idAlumno) {
		//		alumnoRepo.deleteById(idAlumno);
		//	}

		}

