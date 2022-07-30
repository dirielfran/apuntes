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
Seccion 6: Backend: Validaciones en crear y editar (POST y PUT )***************************************************************************
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
Seccion 7: Backend: Paginacion*************************************************************************************************************
46. *******************************************************************************************************************Añadiendo paginación
	1.- Se modifica microservicios-usuarios1
		1.1.- Se modifica IAlumnosRepository, se cambia la interfaz a la que hereda PagingAndSortingRepository
			@Repository
			public interface IAlumnosRepository extends PagingAndSortingRepository<Alumno, Long> {
	2.- Se modifica microservicios-examenes
		1.1.- Se modifica IExamenRepository, se cambia la interfaz a la que hereda PagingAndSortingRepository
			@Repository
			public interface IExamenRepository extends PagingAndSortingRepository<Examen, Long> {
	3.- Se modifica microservicios-cursos
		1.1.- Se modifica ICursoRepository, se cambia la interfaz a la que hereda PagingAndSortingRepository
			@Repository
			public interface ICursoRepository extends PagingAndSortingRepository<Curso, Long> {
	4.- Se modifica microservicios-commons, se modifica CommonServiceImpl
		4.1.- Se modifica la firma se extiende PagingAndSortingRepository
			public class CommonServiceImpl<E, R extends PagingAndSortingRepository<E,Long>> implements ICommonService<E> {
		4.2.- Se crea metodo pageable buscarTodos() retorna un Page	

			@Override
			@Transactional(readOnly = true)
			public Page<E> buscarTodos(Pageable pageable) {
				return this.buscarTodos(pageable);
			}
	5.- Se modifica microservicios-commons, se modifica ICommonService, se crea firma de metodo pageable buscarTodos()
			public Page<E> buscarTodos(Pageable pageable);
	6.- Se modifica microservicios-commons, se modifica CommonController, se crea metodo pageable litar()
			@GetMapping("/paginable")
			public ResponseEntity<?> listar(Pageable pageable){
				return ResponseEntity.ok().body(entityService.buscarTodos(pageable));
			}
47. *********************************************************************************************************Probando paginación en Postman
	1.- Se prueba en postman paginacion del microservicio de usuarios
		http://localhost:8092/api/alumnos/paginable?page=1&size=3

		Respuesta:
			{
			    "content": [
			        {
			            "id": 4,
			            "nombre": "Antonio",
			            "apellido": "Piñango",
			            "email": "aa@gmail",
			            "createAt": "2022-02-07T22:56:35.378+00:00"
			        },
			        {
			            "id": 5,
			            "nombre": "Yalaury",
			            "apellido": "toboa",
			            "email": "Yalaury@gmail",
			            "createAt": "2022-02-07T22:57:11.834+00:00"
			        },
			        {
			            "id": 6,
			            "nombre": "Camila",
			            "apellido": "toboa",
			            "email": "Camila@gmail",
			            "createAt": "2022-02-07T22:57:27.363+00:00"
			        }
			    ],
			    "pageable": {
			        "sort": {
			            "empty": true,
			            "sorted": false,
			            "unsorted": true
			        },
			        "offset": 3,
			        "pageNumber": 1,
			        "pageSize": 3,
			        "paged": true,
			        "unpaged": false
			    },
			    "last": false,
			    "totalElements": 9,
			    "totalPages": 3,
			    "size": 3,
			    "number": 1,
			    "sort": {
			        "empty": true,
			        "sorted": false,
			        "unsorted": true
			    },
			    "first": false,
			    "numberOfElements": 3,
			    "empty": false
			}
Seccion 8: Backend: Upload de foto en microservicio alumnos ********************************************************************************
48. *********************************************************************************Añadiendo atributo foto del tipo Blob en entity Alumno
***************************************************************************************************************************************@Lob
*********************************************************************************************************************************@JsonIgnore
	1.- Se modifica libreria commons-alumnos
		1.1.- Se modifica Alumno.java
			1.1.1.- Se crea atributo de tipo byte[] para la foto con su get y set 
				//Para manejo de datos muy grandes 
				@Lob
				@JsonIgnore
				private byte[] foto; 
	2.- Se crea metodo para obtener el hash de la foto 
			public Integer getFotoHshCode() {
				return ( this.foto != null ) ? this.foto.hashCode() : null;
			}
49. *******************************************************************Añadeindo métodos handlers con soporte multipartfile para post y put
	1.- Se modifica microservicios-asuarios1, se modifica AlumnoController.java 
		1.1.- Se crea metodo guardarConFoto() 
				@PostMapping("crear-con-foto")
				//Se agrega MultipartFile
				public ResponseEntity<?> guardarConFoto(@Valid Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
					if( !archivo.isEmpty() ) {
						alumno.setFoto(archivo.getBytes());
					}
					return super.guardar(alumno, result);
				}
		1.2.- Se crea metodo editarConFoto() 
				@PutMapping("/editar-con-foto/{id}")
				// No se utiliza en @RequestBody ya que no maneja json y se agrega MultipartFile
				public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result , @PathVariable Long id,
						@RequestParam MultipartFile archivo) throws IOException{
					
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
					// Se agrega la foto 
					if( !archivo.isEmpty() ) {
						alumnoDB.setFoto(archivo.getBytes());
					}
					
					return ResponseEntity.status(HttpStatus.CREATED).body(entityService.guardar(alumnoDB));
				}
50. *************************************************************************************************************Probando upload en Postman
	1.- Se prueba POST --> http://localhost:8092/api/alumnos/crear-con-foto
		form-data 
			archivo --> se selecciona la imagen 
			nombre 	--> prosegur
			apellido --> areiza	
			email    --> proegur@gmail.com  

		respuesta: 
		{
		    "id": 10,
		    "nombre": "prosegur",
		    "apellido": "areiza",
		    "email": "proegur@gmail.com",
		    "createAt": "2022-02-08T00:01:10.141+00:00",
		    "fotoHshCode": 911538362
		}
	2.- Se prueba PUT --> http://localhost:8092/api/alumnos/editar-con-foto/1
		form-data 
			archivo --> se selecciona la imagen 
			nombre 	--> elvis
			apellido --> areiza	
			email    --> dirielafran@gmail.com 
		Respuesta 
			{
			    "id": 1,
			    "nombre": "elvis",
			    "apellido": "areiza",
			    "email": "dirielfran@gmail.com",
			    "createAt": "2022-02-04T01:56:09.968+00:00",
			    "fotoHshCode": 220310321
			}
51. *****************************************************************Añadiendo método handler del controlador para ver la imagen del alumno
***********************************************************************************************************************************Resource
	1.- Se modifica microservicios-usuarios, se modifica AlumnoController.java, se crea metodo para mostrar la foto 
			@GetMapping("/uploads/img/{id}")
			public  ResponseEntity<?> verFoto(@PathVariable Long id){
				Optional<Alumno> opt = entityService.BuscarXId(id);
				if(opt.isEmpty() || opt.get().getFoto() == null) {
					return ResponseEntity.notFound().build();
				}
				
				Resource imagen = new ByteArrayResource(opt.get().getFoto());
				return ResponseEntity.ok()
						.contentType(MediaType.IMAGE_JPEG)
						.body(imagen);
			}	
	2.- Se prueba en postman 
		GET --> http://localhost:8092/api/alumnos/uploads/img/1
		Nota: se debe motrar la imagen
Seccion 9: Backend: Microservicio-respuesta************************************************************************************************
52. *******************************************************************************************************Creando microservicio respuestas
	1.- 1.- Desde sts se crea un new Spring Starter Project
			1.1.- Se crea proyecto
					name 			 --> microservicios-respuesta
					type         -->Maven
					Packaging    -->jar
					Java version --> 8
					Group 		 --> com.alfonso.app.respuesta
					artifact	 --> microservicios-repuesta
					package      --> com.alfonso.app.respuesta

					next-->

					Spring Boot Project 		--> 2.3.4
					Seleccionas dependencias	-->	Spring Boot DeevTool
												--> Spring Data JPA
												--> MySQL Driver
												--> Spring Web
												--> Eureka Discovery Client
	2.- Se modifica microservicios-respuesta 
		2.1.- Se modifica application.properties 
			#Config eureka como cliente
			spring.application.name=microservicios-respuesta
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
		2.2.- Se modifica la clase MicroserviciosRespuestaApplication
			@EnableEurekaClient
			@SpringBootApplication
			public class MicroserviciosRespuestaApplication {
		2.3.- Se agrega paquete 
			package com.alfonso.app.respuesta.models.entity;
			package com.alfonso.app.respuesta.models.repository;
		2.4.- Se crea clase repositorio 
			public interface RespuestaRepository extends CrudRepository<Respuesta, Long>{
		2.5.- Se crea entidad 
			@Entity
			@Table(name="respuesta")
			public class Respuesta {
				
				@Id
				@GeneratedValue(strategy = GenerationType.IDENTITY)
				private Long id; 
				
				private String texto;

				public Long getId() {
					return id;
				}

				public void setId(Long id) {
					this.id = id;
				}

				public String getTexto() {
					return texto;
				}

				public void setTexto(String texto) {
					this.texto = texto;
				}

			}

	3.- Se modifica microservicios-zuul, se agrega ruta a microservicios -respuesta 
		#Se crea la ruta al microservicio respuesta
		zuul.routes.examenes.service-id=microservicios-respuesta
		//zuul.routes.examenes.path=/api/respuetas/**
53. ***************************************************************************************************************Configurando entity scan
	1.- Se modifica microservicios-respuesta 
		1.1.- Se modifica pom.xml, se agregan dependencias de commons-alumnos y commons-examenes
			<dependency>
				<groupId>com.alfonso.commons.examenes</groupId>
				<artifactId>commons-examenes</artifactId>
				<version>0.0.1-SNAPSHOT</version>
			</dependency>
			<dependency>
				<groupId>com.alfonso.commons.alumnos</groupId>
				<artifactId>commons-alumnos</artifactId>
				<version>0.0.1-SNAPSHOT</version>
			</dependency>
		1.2.- Se modifica clase principal MicroserviciosRespuestaApplication, se agrega EntityScan 
			@EnableEurekaClient
			@SpringBootApplication
			@EntityScan({"com.alfonso.app.respuesta.models.entity",
							"com.alfonso.commons.alumnos.models.entity",
							"com.alfonso.commons.examenes.models.entity"})
			public class MicroserviciosRespuestaApplication {
54. ****************************************************************Añadiendo entity Respuesta y sus relaciones con el alumno y la pregunta
	1.- Se modifica microservicios-respuesta, se modifica entidad Respuesta se agregan atributos alumno, pregunta
		@ManyToOne(fetch = FetchType.LAZY)
		private Alumno alumno;
		
		@OneToOne(fetch = FetchType.LAZY)
		private Pregunta pregunta; 

		public Alumno getAlumno() {
			return alumno;
		}

		public void setAlumno(Alumno alumno) {
			this.alumno = alumno;
		}

		public Pregunta getPregunta() {
			return pregunta;
		}

		public void setPregunta(Pregunta pregunta) {
			this.pregunta = pregunta;
		}
55. *********************************************************************************************Añadiendo componentes service y controller
	1.- Se modifica microservicios-respuesta 
		1.1.- Se crea clase de servicio  
			1.1.1.- Se crea paquete de servicios  
				package com.alfonso.app.respuesta.services;
			1.1.2.- Se crea interface del servicio 
				public interface IRespuestaService {
					public Iterable<Respuesta> saveAll( Iterable<Respuesta> respuestas);
				}
			1.1.3.- Se crea implementacion del servicio 
				@Service
				public class RespuestaServiceImpl implements IRespuestaService {
					
					@Autowired
					private RespuestaRepository respuestaRepository;

					@Override
					@Transactional
					public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
						return respuestaRepository.saveAll(respuestas);
					}
				}	
		1.2.- Se crea controlador de respuestas 
			1.2.1.- Se crea paquete para el controlador  
				package com.alfonso.app.respuesta.controller;
			1.2.2.- Se crea controlador 
				@RestController
				public class RespuestaController {
					
					@Autowired
					IRespuestaService iRespuestaService;
					
					@PostMapping
					public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas){
						Iterable<Respuesta> respuestasDB = this.iRespuestaService.saveAll(respuestas);
						return ResponseEntity.status(HttpStatus.CREATED).body(respuestasDB);
					}
				} 
56. **************************************************************************************************************Escribiendo consulta JPQL 
	1.- Se modifica microservicios-respuesta, se modifica repositorio, se realiza consulta JPQL 
		@Repository
		public interface RespuestaRepository extends CrudRepository<Respuesta, Long>{
			
			@Query("select r from Respuesta r join fetch r.alumno a join fetch r.pregunta p join fetch p.examen e where a.id = ?1 and e.id = ?2")
			public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long idAlumno, Long idExamen);

		}
57. *************************************************************Añadiendo métodos en service y controlador para las respuestas del alumno
	1.- Se modifica microservicios-respuesta 
		1.1.- Se modifica iRespuestaService, se crea firma del metodo 
			public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long idAlumno, Long idExamen);
		1.2.- Se modifica RespuestaServiceImpl.java, se implementa metodo  
			@Override
			@Transactional(readOnly = true)
			public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long idAlumno, Long idExamen) {
				return respuestaRepository.findRespuestaByAlumnoByExamen(idAlumno, idExamen);
			}	
		1.3.- Se crea metodo en el controlador que recupera las respuestas por alumno y por examen  
			@GetMapping("/alumno/{idAlumno}/examen/{idExamen}")
			public ResponseEntity<?> getRespuestasXAlumXExam(@PathVariable Long idAlumno, @PathVariable Long idExamen){
				Iterable<Respuesta> respuestas = iRespuestaService.findRespuestaByAlumnoByExamen(idAlumno, idExamen);
				return ResponseEntity.ok(respuestas);
			}
58. ***************************************************************Consulta JPQL para obtener los ids de exámenes respondidos por el alumno
*********************************************************************************************************************************@Transient
	1.- Se modifica la libreria commons-examenes, se modifica Examen.java, se le agrega atributo respondido 
		1.1.- Se crea el atributo y se anota con la anotacion @Trasient para indicar que no persistira en la tabla de BD
			@Transient
			private boolean respondido;
		1.2.- Se crea su get y set 
			public boolean isRespondido() {
				return respondido;
			}

			public void setRespondido(boolean respondido) {
				this.respondido = respondido;
			}
	2.- Se modifica microservicios-respuesta, se moddifica RespuestaRepository, se crea consulta para obtener ids de examenes con respuesta 
	por alumno  
		@Query("select e.id from Respuesta r join r.alumno a join r.pregunta p join p.examen e where a.id=? group by e.id")
		public Iterable<Long> findExamenesIdsConRespuestaByAlumno(Long alumnoId);
59. ************************************************************************Añadiendo métodos en service y controlador para ids de exámenes
	1.- Se modifica microservicios-respuesta 
		1.1.- Se modifica IRespuestaService.java, se agrega la firma para metodo de consulta findExamenesIdsConRespuestaByAlumno() 
			public Iterable<Long> findExamenesIdsConRespuestaByAlumno(Long alumnoId);
		1.2.- Se modifica RespuestaServiceImpl, se implementa metodo findExamenesIdsConRespuestaByAlumno()	
			@Override
			@Transactional(readOnly = true)
			public Iterable<Long> findExamenesIdsConRespuestaByAlumno(Long alumnoId) {
				return respuestaRepository.findExamenesIdsConRespuestaByAlumno(alumnoId);
			}
		1.3.- Se modifica RespuestaController.java, se crea metodo para la consulta getExamenesIdsRespondidos()
			@GetMapping("/alumno/{idAlumno}/examenes-respondidos")
			public ResponseEntity<?> getExamenesIdsRespondidos(@PathVariable Long idAlumno){
				Iterable<Long> examenesIds = iRespuestaService.findExamenesIdsConRespuestaByAlumno(idAlumno);
				return ResponseEntity.ok(examenesIds);
			}
60. ***************************************************************************************************Probando en postman responder examen
	0.- Se levantan lo microservicios:
		eureka
		cursos  
		examenes  
		respuesta  
		usuarios 
		zuul 
	1.- Se abre postman, se prueba micreoservicio-respuesta  
		1.1.- Se insertan respuestas 
			Metodo: POST 
			path: http://localhost:8092/api/respuestas
			body --> raw --> JSON: 
				[
				    {
				        "texto": "Cristobal Colon",
				        "alumno": {
				            "id": 2,
				            "nombre": "Diego",
				            "apellido": "Areiza",
				            "email": "diego2@gmail",
				            "createAt": "2022-02-04T01:56:24.644+00:00",
				            "fotoHshCode": null
				        },
				        "pregunta": {
				            "id": 1,
				            "texto": "Quien descubrio Centro America?"
				        }
				    },
				    {
				        "texto": "15",
				        "alumno": {
				            "id": 2,
				            "nombre": "Diego",
				            "apellido": "Areiza",
				            "email": "diego2@gmail",
				            "createAt": "2022-02-04T01:56:24.644+00:00",
				            "fotoHshCode": null
				        },
				        "pregunta": {
				            "id": 3,
				            "texto": "Cuantos paises tiene America?"
				        }
				    },
				    {
				        "texto": "Caracas",
				        "alumno": {
				            "id": 2,
				            "nombre": "Diego",
				            "apellido": "Areiza",
				            "email": "diego2@gmail",
				            "createAt": "2022-02-04T01:56:24.644+00:00",
				            "fotoHshCode": null
				        },
				        "pregunta": {
				            "id": 4,
				            "texto": "Capital de Venezuela?"
				        }
				    }
				]
		1.2.- Se prueba metodo getRespuestasXAlumXExam() 
			metodo: GET 
			Path: http://localhost:8092/api/respuestas/alumno/2/examen/1
			Respuesta:
				[
				    {
				        "id": 1,
				        "texto": "Cristobal Colon",
				        "alumno": {
				            "id": 2,
				            "nombre": "Diego",
				            "apellido": "Areiza",
				            "email": "diego2@gmail",
				            "createAt": "2022-02-04T01:56:24.644+00:00",
				            "fotoHshCode": null
				        },
				        "pregunta": {
				            "id": 1,
				            "texto": "Quien descubrio Centro America?",
				            "examen": {
				                "id": 1,
				                "nombre": "Examen de Historia",
				                "createAt": "2022-02-05T12:25:27.016+00:00",
				                "asignatura": null,
				                "respondido": false
				            }
				        }
				    },
				    {
				        "id": 2,
				        "texto": "15",
				        "alumno": {
				            "id": 2,
				            "nombre": "Diego",
				            "apellido": "Areiza",
				            "email": "diego2@gmail",
				            "createAt": "2022-02-04T01:56:24.644+00:00",
				            "fotoHshCode": null
				        },
				        "pregunta": {
				            "id": 3,
				            "texto": "Cuantos paises tiene America?",
				            "examen": {
				                "id": 1,
				                "nombre": "Examen de Historia",
				                "createAt": "2022-02-05T12:25:27.016+00:00",
				                "asignatura": null,
				                "respondido": false
				            }
				        }
				    },
				    {
				        "id": 3,
				        "texto": "Caracas",
				        "alumno": {
				            "id": 2,
				            "nombre": "Diego",
				            "apellido": "Areiza",
				            "email": "diego2@gmail",
				            "createAt": "2022-02-04T01:56:24.644+00:00",
				            "fotoHshCode": null
				        },
				        "pregunta": {
				            "id": 4,
				            "texto": "Capital de Venezuela?",
				            "examen": {
				                "id": 1,
				                "nombre": "Examen de Historia",
				                "createAt": "2022-02-05T12:25:27.016+00:00",
				                "asignatura": null,
				                "respondido": false
				            }
				        }
				    }
				]
		1.3.- Se prueba metodo getExamenesIdsRespondidos() 
			Metodo: GET 
			Path: http://localhost:8092/api/respuestas/alumno/2/examenes-respondidos
			Respuesta:
				[1]
61. **********************************************************************Implementando cliente HTTP Feign en cursos para obteners examenes
*******************************************************************************************************************************@FeignClient
	1.- Se modfica micreoservicio-cursos 
		1.1.- Se modifica pom.xml, Se agrega dependencia de openfeign
				<dependency>
					<groupId>org.springframework.cloud</groupId>
					<artifactId>spring-cloud-starter-openfeign</artifactId>
				</dependency>
		1.2.- Se modifica clase principal para habilitar FeignClient  
			// Se habilita FeingClient
			@EnableFeignClients
			//Se configura la aplicación Spring Boot actúe como un cliente de Eureka.
			@EnableEurekaClient
			@SpringBootApplication
			//Se agrega los paquetes para el escaneo de la entidad
			@EntityScan({"com.alfonso.commons.alumnos.models.entity",
							"com.alfonso.commons.examenes.models.entity",
							"com.alfonso.app.cursos.models.entity"})
			public class MicroserviciosCursosApplication {

				public static void main(String[] args) {
					SpringApplication.run(MicroserviciosCursosApplication.class, args);
				}

			}
		1.3.- Se crea interface RespuestaFeingClient.java 
			1.3.1.- Se crea paquete package --> com.alfonso.app.cursos.clients
			1.3.2.- Se crea la interface 
				@FeignClient(name = "microservicios-respuesta")
				public interface RespuestaFeignClient {
					
					@GetMapping("/alumno/{idAlumno}/examenes-respondidos")
					public Iterable<Long> getExamenesIdsRespondidos(@PathVariable Long idAlumno);

				}
		1.4.- Se modifica ICursoService, se agrega la firma del metodo 
			public Iterable<Long> getExamenesIdsRespondidos(Long idAlumno);
		1.5.- Se modifica CursoServiceImpl.java, se implementa el metodo   
			@Override
			public Iterable<Long> getExamenesIdsRespondidos(Long idAlumno) {
				return respuestaFeignClient.getExamenesIdsRespondidos(idAlumno);
			}
62. ***********************************************************************Comunicacion con servicio respuesta para obtener ids de examenes
	1.- Se modifica CursoController, metodo buscarCursoXAlumnoId(), se agrega modificacion a cada examen en el atributo respondido 
		//Metodo que devuelve curso por id del alumno
		@GetMapping("/alumno/{id}")
		public ResponseEntity<?> buscarCursoXAlumnoId(@PathVariable Long id){
			Curso curso = entityService.findCursoByAlumnoId(id);
			// Valida si existe curso
			if( null != curso) {
				//Se recuperan ids de examenes respondidos por alumno
				List<Long> examenesIds = (List<Long>) entityService.getExamenesIdsRespondidos(id);
				
				//Se valida si el examen del curso fue respondido 
				List<Examen> examenes = curso.getExamenes().stream().map( examen -> {
					if( examenesIds.contains(examen.getId())) {
						examen.setRespondido(true);
					}
					return examen;
				}).collect(Collectors.toList());
				curso.setExamenes(examenes);
			}
			return ResponseEntity.ok(curso);
		}
	2.- Se prueba en postman 
		Metodo: GET
		Path: http://localhost:8092/api/cursos/alumno/3
		Respuesta: Debe traer el curso del alumno con el atributo respondido en examen
			{
			    "id": 7,
			    "nombre": "4° Secundaria",
			    "createAt": "2022-02-04T19:28:19.358+00:00",
			    "alumnos": [
			        {
			            "id": 2,
			            "nombre": "Diego",
			            "apellido": "Areiza",
			            "email": "diego2@gmail",
			            "createAt": "2022-02-04T01:56:24.644+00:00",
			            "fotoHshCode": null
			        }
			    ],
			    "examenes": [
			        {
			            "id": 1,
			            "nombre": "Examen de Historia",
			            "preguntas": [
			                {
			                    "id": 1,
			                    "texto": "Quien descubrio Centro America?"
			                },
			                {
			                    "id": 3,
			                    "texto": "Cuantos paises tiene America?"
			                },
			                {
			                    "id": 4,
			                    "texto": "Capital de Venezuela?"
			                }
			            ],
			            "createAt": "2022-02-05T12:25:27.016+00:00",
			            "asignatura": null,
			            "respondido": true
			        }
			    ]
			}
Seccion 10: Backend: Spring Cloud Gateway**************************************************************************************************
63. ***********************************************************************************Creando y configurando servidor Spring Cloud Gateway
	1.- 1.- Desde sts se crea un new Spring Starter Project
			1.1.- Se crea proyecto
					name 			--> microservicios-gateway
					type         	-->Maven
					Packaging    	-->jar
					Java version 	--> 8
					Group 		 --> com.alfonso.app.gateway
					artifact	 	--> microservicios-gateway
					package      	--> com.alfonso.app.gateway

					next-->

					Spring Boot Project 		--> 2.6.3
					Seleccionas dependencias	-->	Spring Boot DeevTool
												--> Gateway
												--> Eureka Discovery Client
	2.- Se modifica la clase principal, se habilita eurekaClient 
		@EnableEurekaClient
		@SpringBootApplication
		public class MicroserviciosGatewayApplication {

			public static void main(String[] args) {
				SpringApplication.run(MicroserviciosGatewayApplication.class, args);
			}

		}
	3.- Se modifica el application.properties, se agrega las configuraciones del gateway 
		spring.application.name=microservicios-gateway
		server.port=8090

		#Se registra donde se encuentra eureka 
		eureka.client.service-url.defaultZone=http://localhost:8761/eureka


		#Se crea la ruta al microservicio usuario
		spring.cloud.gateway.routes[0].id=microservicios-usuarios
		#Balanceo de cargas con loadBalancer
		spring.cloud.gateway.routes[0].uri=lb://microservicios-usuarios
		#Se configura la ruta 
		/*spring.cloud.gateway.routes[0].predicates=Path=/api/alumnos/**
		#que parametros se eliminan  en la request internamente, se borran los dos primeros prefijos del path
		spring.cloud.gateway.routes[0].filters=StripPrefix=2

		#Se crea la ruta al microservicio cursos
		spring.cloud.gateway.routes[1].id=microservicios-cursos
		spring.cloud.gateway.routes[1].uri=lb://microservicios-cursos
		spring.cloud.gateway.routes[1].predicates=Path=/api/cursos/**
		spring.cloud.gateway.routes[1].filters=StripPrefix=2

		#Se crea la ruta al microservicio examenes
		spring.cloud.gateway.routes[2].id=microservicios-examenes
		spring.cloud.gateway.routes[2].uri=lb://microservicios-examenes
		spring.cloud.gateway.routes[2].predicates=Path=/api/examenes/**
		spring.cloud.gateway.routes[2].filters=StripPrefix=2

		#Se crea la ruta al microservicio respuesta
		spring.cloud.gateway.routes[3].id=microservicios-respuesta
		spring.cloud.gateway.routes[3].uri=lb://microservicios-respuesta
		spring.cloud.gateway.routes[3].predicates=Path=/api/respuestas/**
		spring.cloud.gateway.routes[3].filters=StripPrefix=2


		#Se deshabilita ribbon  para el balanceo de carga
		spring.cloud.loadbalancer.enabled=false*/

	4.- Configuracion en archivo application.yml, se elimina el mapeo a las rutas en el properties y se configuran en tml 
		4.1.- Se crea archivo application.yml 
		4.2.- Se crean las configuraciones  
			spring:
			  cloud:
			    gateway:
			      routes:
			      - id: microservicio-usuarios
			        uri: lb://microservicios-usuarios
			        predicates:
			          - Path=/api/usuarios/**					(**/)
			        filters:
			          - StripPrefix=2 
64. **********************************************************************************Probando Balanceo de carga Spring Cloud Load Balancer
	1.- Se modifica microservicios-cursos 
		1.1.- Se agrega dependencia --> spring-cloud-starter-loadbalancer en el pom.xml, desde el 
			proyecto --> rigth --> Spring --> addStarters --> Spring Cloud Routing --> Cloud loadBalancer
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-loadbalancer</artifactId>
			</dependency> 
		1.2.- Se modifica application.properties, se agrega variable de entorno para prueba
			#Se configura variable de entorno para pruebas 
			config.balanceador.test=${BALANCEADOR_TEST: por defecto}
		1.3.- Se modifica CursoController.java, se agrega endpoint    
				//Para la variable de entorno 
				@Value("${config.balanceador.test}")
				private String balanceadorTest;
				
				@GetMapping("/balanceador-test")
				public ResponseEntity<?> balancedorTest() {
					Map<String, Object> response = new HashMap<String, Object>();
					response.put("balanceador", balanceadorTest);
					response.put("cursos", this.entityService.buscarTodos());
					return ResponseEntity.ok(response);
				}
65. **************************************************************************************************Probando balanceo de carga en postman
35. ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++Implementando filtros globales pre y post
	1.- Se crea paquete de configuracion --> com.eareiza.app.gateway.config
	2.- Se cea clase Filters 

		//Se mapea como bean
		@Component
		//Se implementa GlobalFilter
		public class Filters implements GlobalFilter{

			//Se instancia Logger			
			private final Logger logger = LoggerFactory.getLogger(Filters.class);

			@Override
			// GatewayFilterChain Contrato para permitir WebFilterque uno delegue al siguiente en la cadena.
			public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
				logger.info("Ejecutando  filtro pre");
				
				//Se obtiene el contrato y se retorna un Mono
				return chain.filter(exchange).then(Mono.fromRunnable(() -> {
					logger.info("Ejecutando filtro post");
					//se obtiene el response y se le agrega cookie
					exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "rojo").build());
					//se obtiene los headers y se setea tipo
					exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
				}));
			}

		}
36. ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++Modificando el request en el filtro pre
	1.- Se modifica la clase Filters para modificar el request
		//Se implementa Oredered para darle valor de precedencia al filtro
		public class Filters implements GlobalFilter, Ordered{
			
			private final Logger logger = LoggerFactory.getLogger(Filters.class);

			@Override
			public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
				logger.info("Ejecutando  filtro pre");
				
				//Se recupera y se muta el request, añadiendo un token a los headers
				exchange.getRequest().mutate().headers(h -> h.add("token", "123456"));
				
				return chain.filter(exchange).then(Mono.fromRunnable(() -> {
					logger.info("Ejecutando filtro post");
					
					//Se utiliza Optional para validar que venga el token 
					//Se obtiene el token del request y se añade al response
					Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(valor -> {
						exchange.getResponse().getHeaders().add("token", valor);
					});
					
					exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "rojo").build());
					exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
				}));
			}

			@Override
			//Se le da valor de precedencia al filtro
			public int getOrder() {
				return -1;
			}

		}
	2.- Se prueba en postman, se valida que la respuesta tenga en los headers el token 
		--> GET --> http://localhost:8090/api/usuarios/3
37. +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++Implementando Gateway Filter Factory
	1.- Se crea clase de filtro perzonalizado 
	@Component
	//Se hereda de AbstractGatewayFilterFactory
	public class EjemploGatewayFilterFactory extends AbstractGatewayFilterFactory<EjemploGatewayFilterFactory.Configuration>{

		private final Logger logger = LoggerFactory.getLogger(EjemploGatewayFilterFactory.class);	

		//Se crea contructor y se le pasa la clase Configuration
		public EjemploGatewayFilterFactory() {
			super(Configuration.class);
		}

		@Override
		//Se implementa metodo apply
		public GatewayFilter apply(Configuration config) {

			// exchange --> ServerWebExchange 
			// chain --> GatewayFilterChain 
			return (exchange, chain) -> {
				logger.info("Ejecutando pre filter"+ config.mensaje);
				return chain.filter(exchange).then(Mono.fromRunnable(() ->{
					//Se utiliza Optional para validar que venga la cookie
					//Se obtienen los headers y se añade cookie
					Optional.ofNullable(config.cookieValor).ifPresent(cookie -> {
						exchange.getResponse().addCookie(ResponseCookie.from(config.cookieNombre, cookie).build());
					});
					
					logger.info("Ejecutando post filter"+ config.mensaje);
				}));
			};
		}
		
		//Se crea clase Configuration 
		public static class Configuration {
			
			private String mensaje;
			private String cookieValor;
			private String cookieNombre;
			public String getMensaje() {
				return mensaje;
			}
			public void setMensaje(String mensaje) {
				this.mensaje = mensaje;
			}
			public String getCookieValor() {
				return cookieValor;
			}
			public void setCookieValor(String cookieValor) {
				this.cookieValor = cookieValor;
			}
			public String getCookieNombre() {
				return cookieNombre;
			}
			public void setCookieNombre(String cookieNombre) {
				this.cookieNombre = cookieNombre;
			}

		}

	}
38. +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++Configurando y probando nuestro Gateway Filter Factory personalizado
	1.- Se modifica application.yml, se agrega filter configurado en la clase 
		spring:
		  cloud:
		    gateway:
		      routes:
		      - id: microservicio-usuarios
		        uri: lb://microservicios-usuarios
		        predicates:
		          - Path=/api/usuarios/**   (**/)
		        filters:
		          - StripPrefix=2 
		          - name: Ejemplo
		            args:
		              mensaje: Mensaje Personalizado
		              cookieNombre: usuario
		              cookieValor: ElvisAreiza
    2.- Se modifica Filters, se le cambia orden de precedencia
    		@Override
			//Se le da valor de precedencia al filtro
			public int getOrder() {
				return 1;
			}
    3.- Se prueba en postman, se deben validar las cookies agregadas
    	* Se levanta eureka --> 
    	GET --> http://localhost:8090/api/usuarios/3
39. ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++Filtros Gateway Factory que vienen de fábrica
	Referencia --> https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gatewayfilter-factories
	1.- Se modifica application.yml, se le agregan filters de fabrica 
		spring:
		  cloud:
		    gateway:
		      routes:
		      - id: microservicio-usuarios
		        uri: lb://microservicios-usuarios
		        predicates:
		          - Path=/api/usuarios/**				(**/)
		        filters:
		          - StripPrefix=2 
		          - name: Ejemplo
		            args:
		              mensaje: Mensaje Personalizado
		              cookieNombre: usuario
		              cookieValor: ElvisAreiza
		          - AddRequestHeader=token-request, 123456
		          - AddResponseHeader=token-response, 123456
		          // Se modifica el Content-Type  
		          - SetResponseHeader=Content-Type, text/plain
		          - AddRequestParameter=nombre, andres
	2.- Se modifica CommonsControllers, para imprimir filtros agregados al request en el metodo listar()  
			@GetMapping
			public ResponseEntity<?> listar(@RequestParam String nombre, @RequestHeader String token){
				System.out.println(nombre);
				System.out.println(token);
				return ResponseEntity.ok().body(service.findAll());
			}
	3.- Se prueba por postman, se debe validar los headers que esten los filters agregados 
		http://localhost:8090/api/usuarios
40. +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++Request Predicates Factory que vienen de fábrica
	Referecia --> https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gateway-request-predicates-factories
	1.- Se modifica application.yml 
		spring:
		  cloud:
		    gateway:
		      routes:
		      - id: microservicio-usuarios
		        uri: lb://microservicios-usuarios
		        predicates:
		          - Path=/api/usuarios/**      (**/)
				  - Header=Content-Type,application/json
		          - Header= token, \d+  -->(Expresion regular \d+ --> que sea digito)
		          - Method= GET, POST
		          - Query=color, verde
		          - Cookie=color, azul
		        filters:
		          - StripPrefix=2 
		          - name: Ejemplo
		            args:
		              mensaje: Mensaje Personalizado
		              cookieNombre: usuario
		              cookieValor: ElvisAreiza
		          - AddRequestHeader=token-request, 123456
		          - AddResponseHeader=token-response, 123456
		          - AddRequestParameter=nombre, andres
	2.- Se prueba en postman
		2.1.- Debe fallar 
		2.2.- Se agregan filtros    
			header 	--> token 			--> 123456
					--> Content-Type 	--> application/json
			param  	--> color 			--> verde  
			cookie 	--> color 			--> azul
Seccion 5: Backend: Resilencia4J: Resilencia y toleracia a fallos++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++           
43. ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++Configurando microservicio con Resilience4J








******************************************************Curso de Kubernetes******************************************************************
	Referencia --> https://www.udemy.com/course/guia-completa-de-docker-kubernetes-con-spring-boot/learn/lecture/31678416#overview
	6.- Creacion de primer Microservicio***************************************************************************************************
		* Se crea proyecto maven general de kubernetes
		* Se crea ms usuarios 
			Componentes -->	data JPA
							web 
							openfeign  
							devtools
							mysql 
		* Se modifica el proyecto padre MS-SpringKubernetes, el pom.xml 
			* Se agrega parent del ms-usuarios y se agrega modulo 
			    <parent>
			        <groupId>org.springframework.boot</groupId>
			        <artifactId>spring-boot-starter-parent</artifactId>
			        <version>2.7.2</version>
			        <relativePath/> <!-- lookup parent from repository -->
			    </parent>

		   	    <modules>
			        <module>ms-usuarios</module>
			    </modules>
		* Se modifica el proyecto MS-usuarios, el pom.xml 
			* Se agrega parent del ms-SpringKubernetes 
				<parent>
					<groupId>com.areiza</groupId>
					<artifactId>MS-SpringCloudkubernetes</artifactId>
					<version>1.0-SNAPSHOT</version>
				</parent>
	8.- Configuracion de contexto**********************************************************************************************************
		* Se modifica ms-usuarios, se modifica properties, se le da nombre al servicio y puerto 
			spring.application.name=ms-usuarios
			server.port=8081 
		* Se crea package --> com.eareiza.ms.usuarios.entitys
		* Se crea la clase usuarios 
	9- Implementacion de Componente repository*********************************************************************************************
		Spring Data JPA Referencia --> https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories 
		* Se crea pauqete --> com.eareiza.ms.usuarios.repositories
		* Se crea repositorio UsuarioRepository
	11. Implementando el componente Service************************************************************************************************
		* Se crean paquetes   
			package com.eareiza.ms.usuarios.services;
			package com.eareiza.ms.usuarios.interfaces;
		* Se crea interface e implementacion 
			UsuarioService 
			UsuarioServiceImpl
	12. Implementando el controlador RestController y metodos handler**********************************************************************
		* Se crea packege --> package com.eareiza.ms.usuarios.controllers;
		* Se crea clase controlador UsuarioController 
	

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
	ServerWebExchange******************************************************************************************************************
	 	Contrato para una interacción de solicitud-respuesta HTTP. Proporciona acceso a la solicitud y respuesta HTTP y también expone 
	 	propiedades y características adicionales relacionadas con el procesamiento del lado del servidor, como los atributos de 
	 	solicitud.
	***********************************************************************************************************************************
	GatewayFilterChain*****************************************************************************************************************
		Contrato para permitir WebFilterque uno delegue al siguiente en la cadena.
	***********************************************************************************************************************************
*******************************************************************************************************************************************



++++++++++++++++++Curso Microservicios con Spring Boot y Spring Cloud Netflix Eureka
******************Curso Microservicios con Spring Cloud y Angular full stack


<kite-box [css]="{ background: '$white', padding: '$md', width: '100vw', '@md': {borderRadius: '8px', boxShadow: '0px 2px 4px rgba(225, 225, 235, 0.5)', width: '416px'}}">