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
	* Servidor de nombres para registrar los microservicios
	* El servicio se registra por un id
	* Registra el id, la ubicacion fisica, ip, puerto y metada propia del servicio
	* Solo nos comunicamos por medio del identificador del servicio
	* Se utiliza el balanceo de carga
16.- ***********************************************************************************************Creacion de servidor de registro eureka
	1.- Desde sts se crea un new Spring Starter Project
		1.1.- Se crea proyecto
				name 			--> microservicios-eureka
				type         	--> Maven
				Packaging    	--> jar
				Java version 	--> 8
				Group 		 	--> com.alfonso.app.eureka
				artifact	 	--> microservicios-eureka
				package      	--> com.alfonso.app.eureka

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
		com.alfonso.commons.controller
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
	9.- Se modifica CommonServiceImpl, Se elimina la anotacion @Service, ya que no es un componente que se vaya a inyectar
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
22.- *****************************************************************Añadiendo librería common para un controller genérico y reutilizar
	1.- En micreservicios-commons
		1.1.- Se crea CommonController.java 
			public class CommonController<E, S extends ICommonService<E>>{

			@Autowired
			protected S entityService;
			
			@GetMapping
			public ResponseEntity<?> listar(){
				return ResponseEntity.ok().body(entityService.buscarTodos());
			}
			
			@GetMapping("/{id}")
			public ResponseEntity<?> verAlumno(@PathVariable Long id){
				Optional<E> opt = entityService.BuscarXId(id);
				if(opt.isEmpty()) {
					//Si no lo encuentra envia un codigo 404 y construye body vacio
					return ResponseEntity.notFound().build();
				}
				return ResponseEntity.ok(opt.get());
			}
			
			@PostMapping
			public ResponseEntity<?> guardar(@RequestBody E entity){
				
				E entityBD = entityService.guardar(entity);
				return ResponseEntity.status(HttpStatus.CREATED).body(entityBD);
			}
			
			
			
			@DeleteMapping("/{id}")
			public ResponseEntity<?> borrarAlumno(@PathVariable Long id){
				entityService.deleteById(id);
				return ResponseEntity.noContent().build();
			}
	2.- Se modifica AlumnoController.java 
		@RestController
		public class AlumnoController extends CommonController<Alumno, IAlumnoService>{

			
			@PutMapping("/{id}")
			public ResponseEntity<?> editar(@RequestBody Alumno alumno , @PathVariable Long id){
				
				Optional<Alumno> opt = entityService.BuscarXId(id);
				if(opt.isEmpty()) {
					return ResponseEntity.notFound().build();
				}
				
				Alumno alumnoDB = opt.get();
				alumnoDB.setNombre(alumno.getNombre());
				alumnoDB.setApellido(alumno.getApellido());
				alumnoDB.setEmail(alumno.getEmail());
				
				return ResponseEntity.status(HttpStatus.CREATED).body(entityService.guardar(alumnoDB));
			}
			
			//endpoint para la busqueda de alumno por apellido o nombre
			@GetMapping("/filtrar/{term}")
			public ResponseEntity<?> filtrarAlumno(@PathVariable String term){
				return ResponseEntity.ok(entityService.findByNombreOrApellido(term));
			}

		}
Seccion 4: Backend: Microservicio cursos***************************************************************************************************
24.- ******************************************************************************************************Creacion de microservicio cursos
	1.- Desde sts se crea un new Spring Starter Project
		1.1.- Se crea proyecto
				name 			 --> microservicios-cursos
				type         -->Maven
				Packaging    -->jar
				Java version --> 8
				Group 		 --> com.alfonso.app.cursos
				artifact	 --> microservicios-cursos
				package      --> com.alfonso.app.cursos

				next-->

				Spring Boot Project 		--> 2.3.4
				Seleccionas dependencias	-->	Spring Boot DeevTool
											--> Spring Data JPA
											--> MySQL Driver
											--> Spring Web
											--> Eureka Discovery Client
	2.- Se agrega en el pom.xml dependencia de proyecto microservicios-commons
		<dependency>
			<groupId>com.alfonso.commons</groupId>
			<artifactId>microservicios-commons</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
	3.- Se modifica application.properties, se le añaden las configuraciones
		#Config eureka como cliente
		spring.application.name=microservicios-cursos
		#se habilita puerto de forma ramdom, asigna puerto automaticamente
		server.port=${PORT:0}
		#Se configura intancia id en eureka de forma random
		eureka.instance.instance-id=${spring.application.name}:${random.value}
		#ruta de eureka
		eureka.client.service-url.defaultZone=http://localhost:8761/eureka

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
	4.- Se modifica MicroserviciosCursosApplication, se le añade anotacion @EnableEurekaClient
		@EnableEurekaClient
		@SpringBootApplication
		public class MicroserviciosCursosApplication {

			public static void main(String[] args) {
				SpringApplication.run(MicroserviciosCursosApplication.class, args);
			}

		}
	5.- Se crean dos paquetes para la entidad
		com.alfonso.app.cursos.models.entity
		com.alfonso.app.cursos.models.repository
	6.- Se crea entidad Curso en el paquete com.alfonso.app.cursos.models.entity
		@Entity
		@Table(name = "cursos")
		public class Curso {
			
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id;
			
			private String nombre;
			
			@Column(name = "create_at")
			@Temporal(TemporalType.TIMESTAMP)
			private Date createAt;
			
			@PrePersist
			private void prePersist() {
				this.createAt = new Date();
			}
		}
	7.- Se crea repositorio ICursoRepository de la clase Curso, en el paquete com.alfonso.app.cursos.models.repository
		@Repository
		public interface ICursoRepository extends CrudRepository<Curso, Long> {

		}
25.- ************************************************************************************Implementacion del componente Service y Controller
	1.- Implementacion de componente services
		1.1.- Se crea paquete com.alfonso.app.cursos.services
		1.2.- Se crea Interface ICursoService, extiende de ICommonService
			public interface ICursoService extends ICommonService<Curso> {

			}
		1.3.- Se crea la clase de servicio CursoServiceImpl.java, extiende de CommonServiceImpl e implementa ICursoService
			@Service
			public class CursoServiceImpl extends CommonServiceImpl<Curso, ICursoRepository> implements ICursoService {

			}
	2.- Implementacion de componente controller
		2.1.- Se crea paquete com.alfonso.app.cursos.controller
		2.2.- Se crea clase CursoController, extiende de CommonController y se le agrega unicamente el metodo put para modificar,
		todos los otros metodos los hereda de CommonController.
			@RestController
			public class CursoController extends CommonController<Curso, ICursoService> {
				
				@PutMapping("/{id}")
				public ResponseEntity<?> editarCurso(@RequestBody Curso curso, @PathVariable Long id){
					Optional<Curso> opt = this.entityService.BuscarXId(id);
					if(!opt.isPresent()) {
						return ResponseEntity.notFound().build();
					}
					Curso cursodb = opt.get();
					cursodb.setNombre(curso.getNombre());
					return ResponseEntity.status(HttpStatus.CREATED).body(this.entityService.guardar(cursodb));
				}
			}
26.- ****************************************************************************Creacion de libreria commons para reutilizar entity Alumno
	1.- Se configura ruta de acceso para microservicios-cursos, en el microservicios-zuul
		1.1.- Se modifica application.properties en microservicios-zuul, se agrega la ruta al microservicios-usuarios
			#Se crea la ruta al microservicio usuario
			zuul.routes.cursos.service-id=microservicios-cursos
			zuul.routes.cursos.path=/api/cursos/**   -->*/
	2.- Se crea proyecto commons-alumnos

		2.1.- Desde sts se crea un new Spring Starter Project
			2.1.1.- Se crea proyecto
					name 			 --> commons-alumnos
					type         -->Maven
					Packaging    -->jar
					Java version --> 8
					Group 		 --> com.alfonso.commons.alumnos
					artifact	 --> commons-alumnos
					package      --> com.alfonso.commons.alumnos

					next-->

					Spring Boot Project 		--> 2.3.4
					Seleccionas dependencias	--> Spring Data JPA
	3.- Se elimina la clase principal
	4.- Se modifica el pom.xml, se elimina el plugins de Maven
		<!-- 	<build> -->
		<!-- 		<plugins> -->
		<!-- 			<plugin> -->
		<!-- 				<groupId>org.springframework.boot</groupId> -->
		<!-- 				<artifactId>spring-boot-maven-plugin</artifactId> -->
		<!-- 			</plugin> -->
		<!-- 		</plugins> -->
		<!-- 	</build> -->
	5.- Se mueve la clase alumno del proyecto microservicios-usuarios a commons-alumnos
		5.1.- Se crea paquete com.alfonso.commons.alumnos.models.entity
		5.2.- Se mueve la clase alumno de proyecto
		5.3.- Se elimina el paquete com.alfonso.app.usuarios.entity del proyecto
		5.4.- Se agrega dependencia de commons-alumnos en microservicios-usuarios, se modifica el pom.xml de microservicios-usuarios
			<dependency>
				<groupId>com.alfonso.commons.alumnos</groupId>
				<artifactId>commons-alumnos</artifactId>
				<version>0.0.1-SNAPSHOT</version>
			</dependency>
		/*5.5.- Se modifica la clase MicroserviciosUsuariosApplication, se configura scan de paquete de entidad
			//Se agrega los paquetes para el escaneo de la entidad
			@EntityScan({"com.alfonso.commons.alumnos.entity"})
			public class MicroserviciosUsuariosApplication {
		5.6.- Se agrega dependencia de commons-alumnos en microservicios-cursos, se modifica el pom.xml de microservicios-cursos
			<dependency>
				<groupId>com.alfonso.commons.alumnos</groupId>
				<artifactId>commons-alumnos</artifactId>
				<version>0.0.1-SNAPSHOT</version>
			</dependency>
		5.7.- Se modifica la clase MicroserviciosCursosApplication, se configura scan de paquete de entidad
			//Se agrega los paquetes para el escaneo de la entidad
			@EntityScan({"com.alfonso.commons.alumnos.models.entity",
							"com.alfonso.app.cursos.models.entity"}) 
			public class MicroserviciosCursosApplication {...}	*/											
27.- ************************************************************************************************Add relacion de cursos con sus alumnos
	1.- Se modifica microservicio cursos, entidad Curso.java, se le agrega lista de alumno con su get y su set
		@OneToMany(fetch = FetchType.LAZY)
		private List<Alumno> alumnos;
		1.2.- se crean metodos para eliminar y agregar alumnos de la lista
			1.2.1.- Se crea constructor para inicializar la lista de alumnoService
				public Curso() {
					this.alumnos = new ArrayList<>();
				} 
			1.2.2.- Se crea metodo para agregar  y remover un alumno a la lista
					public void addAlumno(Alumno alumno) {
						this.alumnos.add(alumno);
					}
					
					public void removeAlumno(Alumno alumno) {
						this.alumnos.remove(alumno);
					}
			1.2.3.- Se modifica libreria common.alumno, en la entidad alumno se sobreescribe el meto equals para que pueda ser 
			comparado en el metodo remove
				@Override
				//Se sobreescribe el metodo equeal para la comparacion de objetos en el metodo remove de Curso
				public boolean equals(Object obj) {
					if(obj == this) {
						return true;
					}
					if(!(obj instanceof Alumno)) {
						return false;
					}
					Alumno a = (Alumno) obj;
					return this.id != null && this.id.equals(a.getId());
				}
28.- **************************************************************************************************Asignar y eliminar alumnos del curso
	1.- Se modifica el microservicios-cursos, Se modifica CursoController.java se agrega metodos para agregar y eliminar alumnos del curso
		//metodo que añade alumnos al curso
		@PutMapping("/{id}/asignar-alumno")
		public ResponseEntity<?> asignarAlumno(@RequestBody List<Alumno> alumnos, @PathVariable Long id){
			Optional<Curso> opt = this.entityService.BuscarXId(id);
			if(!opt.isPresent()) {
				return ResponseEntity.noContent().build();
			}
			Curso cursodb = opt.get();
			alumnos.forEach(alumno -> {
				cursodb.addAlumno(alumno);
			});
			
			return ResponseEntity.status(HttpStatus.CREATED).body(this.entityService.guardar(cursodb));
		}
		
		//metodo que elimina un alumno del curso
		@PutMapping("/{id}/eliminar-alumno")
		public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
			Optional<Curso> opt = this.entityService.BuscarXId(id);
			if(!opt.isPresent()) {
				return ResponseEntity.noContent().build();
			}
			Curso cursodb = opt.get();
			cursodb.removeAlumno(alumno);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(this.entityService.guardar(cursodb));
		} 
29.- ******************************************************************************************Prueba en Postman asignar y eliminar alumnos
	1.- Asignar alumno
		PUT -> localhost:8090/api/cursos/7/asignar-alumno
			[
			    {
			        "id": 1,
			        "nombre": "Elvis",
			        "apellido": "Areiza",
			        "email": "dirielfran@gmail.com",
			        "createAt": "2020-11-19T13:34:53.055+00:00"
			    }
			]
	2.- Eliminar alumno
		PUT --> localhost:8090/api/cursos/7/eliminar-alumno
			{
		        "id": 1,
		        "nombre": "Elvis",
		        "apellido": "Areiza",
		        "email": "dirielfran@gmail.com",
		        "createAt": "2020-11-19T13:34:53.055+00:00"
		    }
30.- *****************************************************************************************************************Uso del operador LIKE
	1.- Se modifica microservicios-usuarios, el repositorio IAlumnosRepository.java, se agrega query para la busqueda de alumnos por nombre 
	o apellido
		@Query("SELECT a FROM Alumno a WHERE a.nombre LIKE %?1% OR a.apellido LIKE %?1%")
		public List<Alumno> findByNombreOrApellido(String term);
	2.- Se modifica IAlumnoService, se le agrega metodo para la busqueda de alumnos por nombre o apellido
		public List<Alumno> findByNombreOrApellido(String term);
	3.- Se modifica la clase de servicio AlumnoServiceImpl
		@Override
		//metodo para la busqueda de alumnos por nombre o apellido
		@Transactional(readOnly = true)
		public List<Alumno> findByNombreOrApellido(String term) {
			return entityRepo.findByNombreOrApellido(term);
		}
	4.- Se modifica el controlador AlumnoController.java, se crea endpoint para la busqueda de alumno por apellido o nombre
		//endpoint para la busqueda de alumno por apellido o nombre
		@GetMapping("/filtrar/{term}")
		public ResponseEntity<?> filtrarAlumno(@PathVariable String term){
			return ResponseEntity.ok(entityService.findByNombreOrApellido(term));
		}
31.- *******************************************************************************Usando un join para obtener el curso asignado al alumno
	1.- Se moddifica microservicios-cursos
		1.1.- Se modifica IAlumnosRepository, se agrega busqueda de curso por id de alumno
			@Query("SELECT c FROM Curso c JOIN FETCH Alumno a WHERE a.id = ?1 ")
			public Curso findCursoByAlumnoId(Long id);
		1.2.- Se modifica IAlumnoService, se agrega firma del metodo
			//metodo para la busqueda de curso por el id del alumno
			public Curso findCursoByAlumnoId(Long id);
		1.3.- Se modifica la clase de servicio
			@Override
			@Transactional(readOnly = true)
			public Curso findCursoByAlumnoId(Long id) {
				return entityRepo.findCursoByAlumnoId(id);
			}
		1.4.- Se modifica CursoController, se crea endpoint
			//Metodo que devuelve curso por id del alumno
			@GetMapping("/alumno/{id}")
			public ResponseEntity<?> buscarCursoXAlumnoId(@PathVariable Long id){
				Curso curso = entityService.findCursoByAlumnoId(id);
				return ResponseEntity.ok(curso);
			}
	2.- Se prueba en postman el servicio
		GET--> localhost:8090/api/cursos/alumno/1
Seccion 5: Backend: Microservicios examenes************************************************************************************************
32.- ***************************************************************************************************Creacion de microservicios-examenes
	1.- 1.- Desde sts se crea un new Spring Starter Project
		1.1.- Se crea proyecto
				name 			 --> microservicios-cursos
				type         -->Maven
				Packaging    -->jar
				Java version --> 8
				Group 		 --> com.alfonso.app.cursos
				artifact	 --> microservicios-cursos
				package      --> com.alfonso.app.cursos

				next-->

				Spring Boot Project 		--> 2.3.4
				Seleccionas dependencias	-->	Spring Boot DeevTool
											--> Spring Data JPA
											--> MySQL Driver
											--> Spring Web
											--> Eureka Discovery Client
	2.- Se crean los paquetes com.alfonso.app.examenes.models.entity y com.alfonso.app.examenes.models.repository
	3.- Se modifica MicroserviciosExamenesApplication.java, se le agrega la condiguracion de @EnableEurekaClient
		@EnableEurekaClient
		@SpringBootApplication
		public class MicroserviciosExamenesApplication {...}
	4.- Se crea entidad Examen con los get y set de sus atributos
		@Entity
		@Table(name = "examenes")
		public class Examen implements Serializable{


			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id;
			
			private String nombre;
			
			@Column(name = "create_at")
			@Temporal(TemporalType.TIMESTAMP)
			private Date createAt;
			
			@PrePersist
			public void prePersist() {
				this.createAt = new Date();
			}
		}
	5.- Se crea la entidad Pregunta.java con los get y set de sus atributos
		@Entity
		@Table(name = "preguntas")
		public class Pregunta implements Serializable{

			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id;
			
			private String texto;
		}
	6.- Se crea repositorio IExamenRepository
		6.1.- Se crea paquete com.alfonso.examenes.app.models.repository
		6.2.- Se crea el repositorio 
			public interface IExamenRepository extends CrudRepository<Examen, Long> {

			}
	7.- Se modifica el application.properties, se le añaden las configurariones de eureka, datasource
		#Config eureka como cliente
		spring.application.name=microservicios-examenes
		#se habilita puerto de forma ramdom, asigna puerto automaticamente
		server.port=${PORT:0}
		#Se configura intancia id en eureka de forma random
		eureka.instance.instance-id=${spring.application.name}:${random.value}
		#ruta de eureka
		eureka.client.service-url.defaultZone=http://localhost:8761/eureka

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
33.- ************************************************************************************Relacion bidireccional en examen con sus preguntas
	1.- Se modifica Entidad Examen.java, se le agrega atributo preguntas, se agrega constructor que inicializa con un arraylist,
	su get yset
		//El orphanRemoval atributo instruirá al proveedor de JPA para que active una remove transición 
		//de estado de entidad cuando el hijo su entidad matriz ya no haga referencia a una entidad.
		@JsonIgnoreProperties(value = {"examen"}, allowSetters = true)
		@OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
		private List<Pregunta> preguntas;
		
		public Examen() {
			this.preguntas = new ArrayList<>();
		}	
		1.1.- Se agrega metodo para agregar y eliminar preguntas de la lista.
			public void addPregunta(Pregunta pregunta ) {
				this.preguntas.add(pregunta);
				//relacion inversa
				pregunta.setExamen(this);
			}
			
			public void deletePregunta(Pregunta pregunta) {
				this.preguntas.remove(pregunta);
				//Se estable relacion inversa al setearle null se elimana por la 
				//propiedad config. orphanRemoval = true
				pregunta.setExamen(null);
			}
		1.2.- Se modifica el metoo setPreguntas, para que utilice el metodo addPregunta, el cual setea el examen a cada pregunta
			//Se utiliza el metodo addPregunta para añadir la lsita de preguntas y seteo de examen
			public void setPreguntas(List<Pregunta> preguntas) {
				this.preguntas.clear();
				preguntas.forEach(this::addPregunta);
			}
	2.- Se modifica Pregunta.java, se agrega atributo examen, con su get y set
		@JsonIgnoreProperties(value = {"preguntas"})
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "examen_id")
		private Examen examen;
		2.1.- Se sobreescribe el metodo equals
			@Override
			public boolean equals(Object obj) {
				if(obj == this) {
					return true;
				}
				if(!(obj instanceof Pregunta)) {
					return false;
				}
				Pregunta p = (Pregunta) obj;
				
				return this.id != null && this.id.equals(p.id);
			}
34.- ***************************************************************************************************Add componente service y controller
	1.- Se modifica microservicios-examenes, se modifica POM.xml, se le agrega la dependencia a la libreria microservicios-commons	
		<dependency>
			<groupId>com.alfonso.commons</groupId>
			<artifactId>microservicios-commons</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
	2.- Se crea el componente de servicio
		2.1.- Se crea paquete com.alfonso.app.examen.service
		2.2.- Se crea interface IExamenService
			public interface IExamenService extends ICommonService<Examen>{

			}
		2.3.- Se crea la clase de servicio ExamenServiceImpl.java
			@Service
			public class ExamenServiceImpl extends CommonServiceImpl<Examen, IExamenRepository> implements IExamenService{

			}
	3.- Se crea clase ExamenController
			@RestController
			public class ExamenController extends CommonController<Examen, IExamenService> {

			}
35.- **********************************************************************************Se añade metodo handler para la edicion de preguntas
	1.- Se modifica microservicios-examenes, ExamenController.java, se agrega el metodo PutMapping		
			@PutMapping("/{id}")
			public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id){
				Optional<Examen> opt = entityService.BuscarXId(id);
				if(!opt.isPresent()) {
					return ResponseEntity.noContent().build();
				}
				Examen examenbd = opt.get();
				examenbd.setNombre(examen.getNombre());
				
				//Se eliminan de la bd las preguntas eliminadas
				List<Pregunta> eliminadas = new ArrayList<>();
				
				//Se validan que preguntas del json no estan en bd
		//		examenbd.getPreguntas().forEach(p ->{
		//			if(!examen.getPreguntas().contains(p)) {
		//				eliminadas.add(p);
		//			}
		//		});
				
				//Se eliminan de bd las preguntas eliminadas
		//		eliminadas.forEach(p ->{
		//			examenbd.deletePregunta(p);
		//		});
				
				examenbd.getPreguntas()
				.stream()
				.filter(p -> !examenbd.getPreguntas().contains(p))
				.forEach(examenbd::deletePregunta);
				
				examenbd.setPreguntas(examen.getPreguntas());
			
				return ResponseEntity.status(HttpStatus.CREATED).body(entityService.guardar(examenbd));
			}
	2.- Se modifica microservicios-zuul,se modifica application.properties, se le agrega configuracion de microservicios-examenes
		#Se crea la ruta al microservicio examenes
		zuul.routes.examenes.service-id=microservicios-examenes
		zuul.routes.examenes.path=/api/examenes/**   --> */
36.- *******************************************************************************************************************Probando en postman
	1.- Probando metodo GET
		http://localhost:8090/api/examenes
	2.- Probando metodo POST
		http://localhost:8090/api/examenes

			{
			    "nombre": "Examen de Historia",
			    "preguntas": [
			        {"texto":"Quien descubrio America?"},
			        {"texto":"Que paises independizo Bolivar"},
			        {"texto":"En que año se independizo Venezuela?"}
			    ]
			}
	3.- Probando PUT --> http://localhost:8090/api/examenes/1

		{

	        "nombre": "Examen de Historia",

	        "preguntas": [
	            {
	                "id": 1,
	                "texto": "Quien descubrio America?"
	            },
	            {
	                "id": 2,
	                "texto": "Que paises independizo Bolivar"
	            },
	            {
	                "texto": "Quien fue Simon Bolivar"
	            }
	        ]
	    }
37.- ***********************************************************************************************Creacion libreria commons para examenes
	1.- Se crea proyecto commons-alumnos

		1.1.- Desde sts se crea un new Spring Starter Project
			1.1.1.- Se crea proyecto
					name 			 --> commons-examenes
					type         -->Maven
					Packaging    -->jar
					Java version --> 8
					Group 		 --> com.alfonso.commons.examenes
					artifact	 --> commons-exameness
					package      --> com.alfonso.commons.examenes

					next-->

					Spring Boot Project 		--> 2.3.4
					Seleccionas dependencias	--> Spring Data JPA
	2.- Se modifica POM.xml, se le comenta el plugins de maven
		<!-- 	<build> -->
		<!-- 		<plugins> -->
		<!-- 			<plugin> -->
		<!-- 				<groupId>org.springframework.boot</groupId> -->
		<!-- 				<artifactId>spring-boot-maven-plugin</artifactId> -->
		<!-- 			</plugin> -->
		<!-- 		</plugins> -->
		<!-- 	</build> -->
	3.- Se elimina la clase principal 
	4.- Se crea paquete --> com.alfonso.commons.examenes.models.entity
	5.- Se copian las entidades de microservicios-examenes a commons-examenes
		Examen.java 
		Pregunta.java
	6.- Se modifica pom.xml, se agrega dependencia para jackson anotaciones
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-json</artifactId>
		</dependency>
	7.- Se modifica microservicios-examenes y microservicios-cursos, se le agrega dependencia de cammons-examenes
		<dependency>
			<groupId>com.alfonso.commons.examenes</groupId>
			<artifactId>commons-examenes</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
38.- *********************************************************************************************************Relacion entre Curso y Examen
	1.- Se modifica microservicios-cursos, se modifica entidad Curso, se le agrega atributo examenes con su get y set
		@ManyToMany(fetch = FetchType.LAZY)
		private List<Examen> examenes;
		1.0.- Se agrega en el consructor inicializacion de la lista de examenes
				public Curso() {
					this.alumnos = new ArrayList<>();
					this.examenes = new ArrayList<>();
				}
		1.1.- Se agrega metodo addExamen y removeExamen
			public void addExamen(Examen examen) {
				this.examenes.add(examen);
			}
			
			public void removeExamen(Examen examen) {
				this.examenes.remove(examen);
			}
		1.2.- Se modifica commons-examenes, se modifica entidad Examen.java, se sobreescribe metodo equals para poder utilizar el 
		metodo removeExamen 
			@Override
			public boolean equals(Object obj) {
				if(this == obj) {
					return true;
				}
				if(!(obj instanceof Examen)) {
					return false;
				}
				
				Examen examen = (Examen) obj;
				
				return this.id != null && this.id.equals(examen.getId());
			}
	2.- Se modifica CursoController.java, se agregan metodos para eliminar y agregar examen al curso
			//metodo que añade examen al curso
			@PutMapping("/{id}/asignar-examenes")
			public ResponseEntity<?> asignarExamen(@RequestBody List<Examen> examenes, @PathVariable Long id){
				Optional<Curso> opt = this.entityService.BuscarXId(id);
				if(!opt.isPresent()) {
					return ResponseEntity.noContent().build();
				}
				Curso cursodb = opt.get();
				examenes.forEach(examen -> {
					cursodb.addExamen(examen);
				});
				
				return ResponseEntity.status(HttpStatus.CREATED).body(this.entityService.guardar(cursodb));
			}
			
			//metodo que elimina un examen del curso
			@PutMapping("/{id}/eliminar-examen")
			public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
				Optional<Curso> opt = this.entityService.BuscarXId(id);
				if(!opt.isPresent()) {
					return ResponseEntity.noContent().build();
				}
				Curso cursodb = opt.get();
				cursodb.removeExamen(examen);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(this.entityService.guardar(cursodb));
			}
39.- *************************************************************************************************************************Operador Like
	1.- Se modifica microservicios-examenes, se modifica la clase principal MicroserviciosExamenesApplication.java, se le agrega el 
	scan de la entidad
		@EntityScan({"com.alfonso.commons.examenes.models.entity"})
		1.1.- Se modifica IExamenRepository, Se crea query para la busqueda de examen por nombre
			@Query("SELECT e FROM Examen e WHERE e.nombre like %?1%")
			public List<Examen> findByNombre(String nombre);
		1.2.- Se modifica la interface IExamenService 
			public List<Examen> findByNombre(String nombre);
		1.3.- Se modifica la clase de servicio ExamenServiceImpl.java
				@Override
				@Transactional(readOnly = true)
				public List<Examen> findByNombre(String nombre) {
					return entityRepo.findByNombre(nombre);
				}
		1.4.- Se modifica ExamenController.java, se crea metodo para filtrar por nombre
				@GetMapping("/filtrar/{term}")
				public ResponseEntity<?> filtrarExamen(@PathVariable String term){
					return ResponseEntity.ok(entityService.findByNombre(term));
				}
	2.- Se modifica microservicios-cursos, se modifica la clase principal MicroserviciosExamenesApplication.java, se le agrega el 
	scan de la entidad
		@EntityScan({"com.alfonso.commons.alumnos.models.entity",
				"com.alfonso.commons.examenes.models.entity",
				"com.alfonso.app.cursos.models.entity"})
40.- **************************************************************************************************Se añaden asignaturas a los examenes
	1.- Se modifica commons-examenes, se agrega pojo Asignatura.java
		@Entity
		@Table(name="asignaturas")
		public class Asignatura {
			
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id;
			
			private String nombre;

			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				this.id = id;
			}

			public String getNombre() {
				return nombre;
			}

			public void setNombre(String nombre) {
				this.nombre = nombre;
			}	

		}
	2.- Se modifica Examen.java, se agrega atributo asignatura con su get y su set
			@ManyToOne(fetch = FetchType.LAZY)
			private Asignatura asignatura;
	3.- Se modifica Asignatura.java, se le crean relaciones para subclases de asignatura, se agregan set y get
			//Trae la clase padre
		@JsonIgnoreProperties(value= {"hijos"})
		@ManyToOne(fetch = FetchType.LAZY)
		private Asignatura padre;
		
		//Trae todas las subclases
		@JsonIgnoreProperties(value= {"padre"}, allowSetters = true)
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "padre", cascade = CascadeType.ALL)
		private List<Asignatura> hijos;
		3.1.- Se agrega al contructor la cracion de ArrayList de hijos
				public Asignatura() {
					this.hijos= new ArrayList<>();
				}
41.- ******************************************************************************Add Componente Service y Controller para las asignaturas
	1.- En micreservicios-examenes, se crea repositorio IAsignaturaRepository 
		public interface IAsignaturaRepository extends CrudRepository<Asignatura, Long> {

		}
	2.- Se modifica IExamenService, se agrega firma de metodo para recuperar las asignaturas  
			public Iterable<Asignatura> findAllAsignatura();
	3.- Se modifica ExamenServiceImpl, se agrega implementacion del metodo findAllAsignatura() 
		3.1.- Se realia ID del repositorio IAsignaturaRepository 
			//repository de Asignatura
			@Autowired
			private IAsignaturaRepository asignaturaRepo;
		3.2.- Se crea metodo findAllAsignatura() 
			@Override
			public Iterable<Asignatura> findAllAsignatura() {
				return asignaturaRepo.findAll();
			}
	4.- Se modifica ExamenController, se agrega metodo para recuperar asignaturas 
		@GetMapping("asignaturas")
		public ResponseEntity<?> getAsignaturas(){
			return ResponseEntity.ok(entityService.findAllAsignatura());
		}
42.- *************************************************************************************** Añadiendo datos de prueba para las asignaturas
	1.- Se levantan los microservicios eureka y examenes 
	2.- Se realizan insert en la tabla 
		INSERT INTO `asignaturas` (`id`, `nombre`, `padre_id`) VALUES
			(1, 'Matemáticas', NULL),
			(2, 'Lenguaje', NULL),
			(3, 'Inglés', NULL),
			(4, 'Ciencias Naturales', NULL),
			(5, 'Ciencias Sociales y Historia', NULL),
			(6, 'Música', NULL),
			(7, 'Artes', NULL),
			(8, 'Algebra', 1),
			(9, 'Aritmética', 1),
			(10, 'Trigonometría', 1),
			(11, 'Lectura y comprensión', 2),
			(12, 'Verbos', 2),
			(13, 'Gramática', 2),
			(14, 'Inglés', 3),
			(15, 'Gramática', 3),
			(16, 'Verbos', 3),
			(17, 'Ciencias Naturales', 4),
			(18, 'Biología', 4),
			(19, 'Física', 4),
			(20, 'Quimica', 4),
			(21, 'Historia', 5),
			(22, 'Ciencias Sociales', 5),
			(23, 'Filosofía', 5),
			(24, 'Música', 6),
			(25, 'Artes', 7);
	3.- Se levanta microservicio zuul 
	4.- Se prueba en postman --> http://localhost:8092/api/examenes/asignaturas
Seccion 5: Backend: Validaciones en crear y editar (POST y PUT )***************************************************************************
44. *****************************************************************************************Añadiendo reglas de validaciones en los entity
	1.- En commons-examenes
		1.1.- Se modifica pom.xml, se agrega dependencia validation 
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-validation</artifactId>
			</dependency>
		1.2.- Se modifica Examen.java, se agregan validaciones a nombre, asignatura  
			@NotEmpty
			private String nombre;

			@ManyToOne(fetch = FetchType.LAZY)
			@NotNull
			private Asignatura asignatura;
	2.- En commons-alumnos
		2.1.- Se modifica pom.xml, se agrega dependencia validation 
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		2.2.- Se modifica Examen.java, se agregan validaciones a nombre, apellido, email 

			@NotEmpty
			private String nombre;
			
			@NotEmpty
			private String apellido;
			
			@NotEmpty
			@Email
			private String email;
	3.- En micreservicios-commons  
		3.1.- Se modifica pom.xml, se agrega dependencia validation 
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-validation</artifactId>
			</dependency>	
		3.2.- Se modifica CommonController.java, se crea metodo para validar campos de forma generica y se modifica metodo guardar para 
		validar entidad 
			3.2.1.- Se crea metodo validar()
				protected ResponseEntity<?> validar( BindingResult result){
					Map<String, Object> errores = new HashMap<>();
					result.getFieldErrors().forEach( err -> {
						errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
					});
					return ResponseEntity.badRequest().body(errores);
				}
			3.2.3.- Se modifica metodo guardar() , se agrega anotacion @Valid, el BindingResult y se realiza validacion de errores
				@PostMapping
				public ResponseEntity<?> guardar(@Valid @RequestBody E entity, BindingResult result){
					if(result.hasErrors()) return this.validar(result);
					E entityBD = entityService.guardar(entity);
					return ResponseEntity.status(HttpStatus.CREATED).body(entityBD);
				}
	3.- En micreservicios-cursos 
		3.1.- Se modifica entidad Curso, e agregan validaciones a nombre 
			@NotEmpty
			private String nombre;
		3.2.- Se modifica CursoController, se modifica metodo editarCurso(), se agrega validacion de entidad 
			@PutMapping("/{id}")
			public ResponseEntity<?> editarCurso(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
				
				// Validacion de campos
				if(result.hasErrors()) return this.validar(result);
				
				Optional<Curso> opt = this.entityService.BuscarXId(id);
				if(!opt.isPresent()) {
					return ResponseEntity.notFound().build();
				}
				Curso cursodb = opt.get();
				cursodb.setNombre(curso.getNombre());
				return ResponseEntity.status(HttpStatus.CREATED).body(this.entityService.guardar(cursodb));
			}
	4.- En micreservicios-usuarios1, se modifica AlumnoController, se añade validacion de entidad 
		@PutMapping("/{id}")
		public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result , @PathVariable Long id){
			
			// Validacion de campos
			if(result.hasErrors()) return this.validar(result);
			
			Optional<Alumno> opt = entityService.BuscarXId(id);
			if(opt.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			Alumno alumnoDB = opt.get();
			alumnoDB.setNombre(alumno.getNombre());
			alumnoDB.setApellido(alumno.getApellido());
			alumnoDB.setEmail(alumno.getEmail());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(entityService.guardar(alumnoDB));
		}
	4.- En micreservicios-examenes, se modifica ExamenController, se añade validacion de entidad 
		@PutMapping("/{id}")
		public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result , @PathVariable Long id){
			
			// Validacion de campos
			if(result.hasErrors()) return this.validar(result);
			
			Optional<Examen> opt = entityService.BuscarXId(id);
			if(!opt.isPresent()) {
				return ResponseEntity.noContent().build();
			}
			Examen examenbd = opt.get();
			examenbd.setNombre(examen.getNombre());
			
			//Se eliminan de la bd las preguntas eliminadas
			List<Pregunta> eliminadas = new ArrayList<>();
			
			examenbd.getPreguntas()
			.stream()
			.filter(p -> !examenbd.getPreguntas().contains(p))
			.forEach(examenbd::deletePregunta);
			
			examenbd.setPreguntas(examen.getPreguntas());
		
			return ResponseEntity.status(HttpStatus.CREATED).body(entityService.guardar(examenbd));
		}
45. *******************************************************************************************************Probando validaciones en Postman
	1.- Se prueba micreoservicio alumno  
		POST --> http://localhost:8092/api/alumnos
		body: {}

		Nota: Se debe validar los campos
			{
			    "apellido": "El campo apellido no debe estar vacío",
			    "nombre": "El campo nombre no debe estar vacío",
			    "email": "El campo email no debe estar vacío"
			}
	2.-  Se prueba micreoservicio cursos 
		body: {}

			Nota: Se debe validar los campos
			{
			    "asignatura": "El campo asignatura no debe ser nulo",
			    "nombre": "El campo nombre no debe estar vacío"
			}

		body: {
				    "nombre": ""
				}
			Nota: Se debe validar los campos
			{
			    "asignatura": "El campo asignatura no debe ser nulo",
			    "nombre": "El campo nombre el tamaño debe estar entre 4 y 20"
			}






Apuntes************************************************************************************************************************************
	Eureka Server**********************************************************************************************************************
		Eureka Server es una aplicación que contiene la información sobre todas las aplicaciones de servicio al cliente. Cada servicio 
		Micro se registrará en el servidor Eureka y el servidor Eureka conoce todas las aplicaciones cliente que se ejecutan en cada 
		puerto y dirección IP. Eureka Server también se conoce como Discovery Server.

		Anotación @EnableEurekaServer----------------------------------------------------------------------------------------
			se utiliza para hacer que su aplicación Spring Boot actúe como un servidor Eureka
		@EnableEurekaClient--------------------------------------------------------------------------------------------------
			hace que su aplicación Spring Boot actúe como un cliente de Eureka.
	***********************************************************************************************************************************
	Zuul Server************************************************************************************************************************
		es una aplicación de puerta de enlace que maneja todas las solicitudes y realiza el enrutamiento dinámico de las aplicaciones 
		de microservicio. El servidor Zuul también se conoce como servidor perimetral.

		Por ejemplo, / api / user se asigna al servicio del usuario y / api / products se asigna al servicio del producto y Zuul Server 
		enruta dinámicamente las solicitudes a la aplicación backend correspondiente.
		@EnableZuulProxy-----------------------------------------------------------------------------------------------------------
			se utiliza para hacer que su aplicación Spring Boot actúe como un servidor proxy Zuul.
	***********************************************************************************************************************************
*******************************************************************************************************************************************