
*********************************************************************Docker*************************************************************
	Referencia --> https://www.udemy.com/course/guia-completa-de-docker-kubernetes-con-spring-boot/learn/lecture/31916642#questions

	Container 		Caja aislada para ejecucion de aplicaciones
					Empaquetado del codigo
					Hacer que se ejecuten de la misma manera, sin importar donde ni quien lo ejecute
					encapsula una fina capa de SO, el ambiente y la aplicacion a ejecutar 
					Optimiza consumo, poco espacio en disco
					simple de compartir, distribuir y construir

	imagen 			una imagen es un Archivo Estático 
					contiene todos los Componentes Necesarios para 
					ejecutar una aplicación, incluyendo el sistema operativo, las bibliotecas, las dependencias y 
					el código fuente de la aplicación. En otras palabras, una imagen de Docker es una Plantilla 
					que se utiliza para Crear y Ejecutar Instancias de Contenedores.

	************************************************************************************Install Windows
		Referencia --> https://docs.docker.com/desktop/install/windows-install/

		* Enable Hyper-V using PowerShell
			Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V -All

			Enable-WindowsOptionalFeature -Online -FeatureName containers -All

		* Windows activar o desactivar caracteristicas de windows
			Se activa Hyper v 

		* Se chequea que este habilitado la virtualizacion
			Administrador de tareas --> Rendimiento

		* Se descarga Docker Desktop  
			Se ejecuta para instalar
	***************************************************************************************************


	*******************************************************************************Dockerizar KioscoApp
		Seccion 6: Docker Introduction*****************************************************************
			* Se agrega file Dockerfile en raiz de la app  
				FROM openjdk:11.0.16

				WORKDIR /app

				COPY ./target/KioscoAppSpring.jar .

				EXPOSE 8080

				ENTRYPOINT ["java", "-jar", "KioscoAppSpring.jar"]


			* Se cambia datasource para que desde  ele contenedor se pueda comunicar con la BD , 
				se modifica localhost por host.docker.interna
				spring.datasource.url=jdbc:mysql://host.docker.internal:3306/db_springboot_backend?useSSL=false&serverTimezone=America/Argentina/Buenos_Aires&allowPublicKeyRetrieval=true

			* se genera el jar nuevamente 
				.\mvnw clean package -DskipTests

			* Se construye imagen desde la raiz del proyecto se ejecuta  
					docker build .

			* Se verifica la imagen creada  
				docker images  

			* Se ejecuta el contenedor 
				docker run -p 8080:8080 9969d6ebd235


			* se valida que el contenedor este arriba  
				docker ps 

			* Se detiene el contenedor 
				docker stop name_container  
		***********************************************************************************************
		Seccion 6: Docker Optimizacion DockerFile******************************************************
			* Se modifica file Dockerfile en raiz de la app  
				FROM openjdk:11.0.16

				WORKDIR /app/kioscoApp

				COPY ./kioscoApp .

				RUM ./mvnw clean package -DskipTests

				EXPOSE 8080

				ENTRYPOINT ["java", "-jar", ".target/KioscoAppSpring.jar"]


			Explanation*******************************************************************************

				El archivo Dockerfile que has mostrado describe los pasos para construir una imagen 
				de Docker. Aquí está lo que cada instrucción hace:

				FROM openjdk:11.0.16: Esta instrucción indica que se utilizará una imagen base de 
				Docker llamada openjdk con la versión 11.0.16. Esta imagen base proporciona una 
				instalación del entorno de ejecución de Java en la versión 11.

				WORKDIR /app/kioscoApp: Esta instrucción establece el directorio de trabajo dentro 
				del contenedor en /app/kioscoApp. Es el directorio donde se realizarán las operaciones 
				posteriores.

				COPY ./kioscoAppSpring .: Esta instrucción copia los archivos y directorios del contexto 
				de construcción (el directorio donde se encuentra el Dockerfile) al directorio de trabajo 
				del contenedor (/app/kioscoApp). En este caso, se copia el contenido del directorio 
				kioscoAppSpring (ubicado en el mismo directorio que el Dockerfile) al directorio de trabajo.

				RUN sed -i -e 's/\r$//' ./mvnw: Esta instrucción ejecuta el comando sed en el archivo 
				mvnw. El comando sed se utiliza para manipular texto en archivos, y en este caso se utiliza 
				para eliminar los caracteres de retorno de carro (\r) del archivo mvnw. Este paso puede 
				ser necesario si el archivo mvnw ha sido creado en un sistema operativo diferente que 
				utiliza caracteres de retorno de carro diferentes.

				RUN ./mvnw clean package -DskipTests: Esta instrucción ejecuta el comando 
				./mvnw clean package -DskipTests dentro del directorio de trabajo del contenedor. 
				El archivo mvnw es un script de Maven Wrapper, que se utiliza para construir y empaquetar 
				la aplicación Java. El comando clean package indica a Maven que limpie cualquier compilación 
				anterior y empacar la aplicación en un archivo JAR. El parámetro -DskipTests se utiliza 
				para omitir la ejecución de pruebas durante el proceso de compilación y empaquetado.

				EXPOSE 8080: Esta instrucción especifica que el contenedor expondrá el puerto 8080. 
				Indica que el contenedor está configurado para escuchar conexiones entrantes en el 
				puerto 8080.

				ENTRYPOINT ["java", "-jar", "./target/KioscoAppSpring.jar"]: Esta instrucción establece 
				el punto de entrada (entrypoint) para el contenedor. Indica que se ejecutará el comando 
				java -jar ./target/KioscoAppSpring.jar cuando el contenedor se inicie. 
				Esto inicia la aplicación Java contenida en el archivo JAR KioscoAppSpring.jar ubicado 
				en el directorio ./target.
			*******************************************************************************************

			* Se ejecuta  --> docker build -t kiosco . -f .\kioscoAppSpring\Dockerfile

			Explanation********************************************************************************
				docker build: Comando para construir una imagen de Docker.
				-t kiosco: Opción para asignar un nombre y una etiqueta a la imagen resultante. 
				En este caso, el nombre será "kiosco".
				.: Ruta al contexto de construcción. Especifica la ubicación donde se encuentran el 
				Dockerfile y los archivos necesarios para la construcción de la imagen. En este caso, 
				se asume que estás ejecutando el comando desde el mismo directorio que contiene el 
				Dockerfile.
				-f .\kioscoAppSpring\Dockerfile: Opción para especificar el nombre y la ubicación 
				del Dockerfile a utilizar para la construcción de la imagen. En este caso, el Dockerfile 
				se encuentra en el directorio "kioscoAppSpring" y su nombre es "Dockerfile".
				
				En resumen, el comando docker build -t kiosco . -f .\kioscoAppSpring\Dockerfile se 
				utiliza para construir una imagen de Docker utilizando el Dockerfile ubicado en el 
				directorio "kioscoAppSpring" con el nombre "Dockerfile". El nombre asignado a la 
				imagen resultante será "kiosco".	
			*******************************************************************************************
			57.- Optimizacion del dockerfile- añade nuevas capas***************************************
				FROM openjdk:11.0.16

				WORKDIR /app/kioscoApp

				COPY ./KioscoAppSpring/pom.xml /app
				COPY ./KioscoAppSpring/.mvn ./.mvn
				COPY ./KioscoAppSpring/mvnw .
				COPY ./KioscoAppSpring/pom.xml .

				RUN sed -i 's/\r$//' mvnw
				RUN ./mvnw dependency:go-offline

				COPY ./KioscoAppSpring/src ./src

				RUN ./mvnw clean package -DskipTests

				EXPOSE 8001

				ENTRYPOINT ["java", "-jar", "./target/KioscoAppSpring.jar"]

				Explanation****************************************************************************
					Con este Dockerfile, se construirá una imagen de Docker que se basa en la imagen 
					de Maven con Java 11. Luego, se copiará el archivo pom.xml al directorio de trabajo 
					del contenedor y se ejecutará el comando mvn dependency:go-offline -B para 
					descargar las dependencias. Después, se copiarán todos los archivos del proyecto 
					al contenedor y se ejecutará el comando mvn clean package -DskipTests para compilar 
					y empaquetar la aplicación. El contenedor expondrá el puerto 8080 y se iniciará la 
					aplicación con el comando java -jar target/KioscoApp.jar cuando el contenedor se ejecute.
				***************************************************************************************
			*******************************************************************************************
			58.- Optimizacion del dockerfile- bajo en peso*********************************************
				FROM openjdk:11.0.16 as builder

				WORKDIR /app/kioscoApp

				COPY ./KioscoAppSpring/pom.xml /app
				COPY ./KioscoAppSpring/.mvn ./.mvn
				COPY ./KioscoAppSpring/mvnw .
				COPY ./KioscoAppSpring/pom.xml .

				RUN sed -i 's/\r$//' mvnw
				RUN ./mvnw dependency:go-offline

				COPY ./KioscoAppSpring/src ./src

				RUN ./mvnw clean package -DskipTests

				FROM openjdk:11.0.16

				WORKDIR /app

				COPY --from=builder /app/kioscoApp/target/KioscoAppSpring.jar .

				EXPOSE 8080

				ENTRYPOINT ["java", "-jar", "KioscoAppSpring.jar"]

				Explanation****************************************************************************
					Este archivo crea dos etapas en la construcción de la imagen de Docker:

					La primera etapa, llamada "builder", utiliza la imagen de OpenJDK 11.0.16 como base. 
					Copia los archivos necesarios para resolver las dependencias y compilar el proyecto 
					Maven, y luego ejecuta los comandos necesarios para compilar el proyecto y generar 
					el archivo JAR.

					La segunda etapa utiliza la misma imagen de OpenJDK 11.0.16 como base, pero solo 
					copia el archivo JAR generado en la etapa anterior. Luego, expone el puerto 8080 y 
					configura el comando de entrada para ejecutar el archivo JAR.

					Este Dockerfile te permite construir una imagen de Docker que contiene la aplicación 
					KioscoAppSpring compilada y lista para ser ejecutada.

					Recuerda ejecutar el comando docker build con la ruta adecuada al Dockerfile para 
					construir la imagen, y luego puedes ejecutar el contenedor utilizando el comando docker run.
				***************************************************************************************
			*******************************************************************************************
			63.- Eliminar contenedores detenidos automaticamente***************************************
				docker run -p 8080:8080 -d --rm kiosco

				Explanation****************************************************************************
					-p 8080:8080 especifica que se debe redirigir el tráfico del puerto 8080 del host 
					al puerto 8080 del contenedor. Esto permite acceder a la aplicación que se ejecuta 
					dentro del contenedor a través del puerto 8080 en el host.
					-d indica que el contenedor se debe ejecutar en segundo plano (modo detached).
					--rm especifica que el contenedor debe eliminarse automáticamente después de detenerse. 
					Esto asegura que el contenedor se elimine una vez que se detenga su ejecución.
					kiosco es el nombre de la imagen del contenedor que se utilizará para ejecutar el 
					contenedor.

					En resumen, el comando docker run -p 8080:8080 -d --rm kiosco ejecutará un contenedor 
					a partir de la imagen llamada "kiosco" en segundo plano, redirigiendo el tráfico del 
					puerto 8080 del host al puerto 8080 del contenedor. Además, el contenedor se eliminará 
					automáticamente una vez que se detenga.
				***************************************************************************************
			*******************************************************************************************
			64. Ingresando en modo interactivo en contenedores*****************************************
				* Se modifica dockerfile, se cambia ENTRYPOINT por CMD como linea de comandos
					#ENTRYPOINT ["java", "-jar", "KioscoAppSpring.jar"]
					CMD ["java", "-jar", "KioscoAppSpring.jar"]
				* Se construye imagen nuevamente
					docker build -t kiosco . -f .\kioscoAppSpring\Dockerfile

				* Se ejecuta el contenedor de forma interactiva, para ingresar a la maquina de forma interactiva
					docker run -p 8080:8080 --rm -it kiosco /bin/sh

				* se visualiza version de java  
					 java -version

				* Se ejecuta la aplicacion desde el cmd 
					java -jar KioscoAppSpring.jar
				Explanation****************************************************************************
					-it habilita la interacción en modo interactivo y conecta un terminal al contenedor. 
					Esto permite interactuar directamente con el shell dentro del contenedor.
					kiosco es el nombre de la imagen que se utilizará para crear el contenedor.
					/bin/sh es el comando que se ejecutará dentro del contenedor. En este caso, se 
					utilizará el shell (sh) para acceder al entorno del contenedor.
				***************************************************************************************
			*******************************************************************************************
			66. Copiando archivos logs de spring desde el contenedor***********************************
				* Se modifica dockerfile para cree directorio de logs --> RUN mkdir ./logs
					FROM openjdk:11.0.16 as builder

					WORKDIR /app/kioscoApp

					COPY ./KioscoAppSpring/pom.xml /app
					COPY ./KioscoAppSpring/.mvn ./.mvn
					COPY ./KioscoAppSpring/mvnw .
					COPY ./KioscoAppSpring/pom.xml .

					RUN sed -i 's/\r$//' mvnw
					RUN ./mvnw dependency:go-offline

					COPY ./KioscoAppSpring/src ./src

					RUN ./mvnw clean package -DskipTests

					FROM openjdk:11.0.16

					WORKDIR /app
					RUN mkdir ./logs
					COPY --from=builder /app/kioscoApp/target/KioscoAppSpring.jar .

					EXPOSE 8080

					#ENTRYPOINT ["java", "-jar", "KioscoAppSpring.jar"]
					CMD ["java", "-jar", "KioscoAppSpring.jar"]
				* Se modifica properties para que loguee en el archivo creado   
					logging.file.path=/app/logs
				* Se crea la imagen nuevamente 
					docker build -t kiosco . -f .\kioscoAppSpring\Dockerfile
				* Se levanta el contenedor 
					docker run -p 8080:8080 --rm -d kiosco
				* Se copia el archivo de logs a maquina local 
					docker cp d39bcd987728:/app/logs .\logs
			*******************************************************************************************
		***********************************************************************************************
		Seccion 9: Docker networks: Comunicacion entre contenedores************************************
			70. Configurando la red o network**********************************************************
				* Docker network --help
				* Creacion de una red de nombre spring 	
					docker network create spring
				* Listar las redes   --> docker network ls  
			71. Comunicación entre contenedores********************************************************
				* Se ejecuta contenedor  con la red spring 
					docker run -p 8080:8080 -d --rm --name KioscoAppSpring --network spring kiosco
				* Se prueba microservicio 
			*******************************************************************************************
			72. Dockerizando MySQL*********************************************************************
				* Se ingresa a docker hub para buscar imagen mysql
				* Se descarga la  imagen  
					docker pull mysql:8
				* Se valida que se descargo imagen mysql 
					docker images 
				* Se ejecuta contenedor mysql 
					docker run -d -p 3307:3306 --name mysql8 --network spring -e MYSQL_ROOT_PASSWORD=Danger120- -e MYSQL_DATABASE=db_springboot_backend mysql:8

					Explicacion************************************************************************
						docker run: Comando para crear y ejecutar un nuevo contenedor a partir de una imagen.
						-d: Ejecuta el contenedor en segundo plano (en modo "detach").
						-p 3307:3306: Mapea el puerto 3307 del host (el sistema operativo donde se ejecuta Docker) 
						al puerto 3306 del contenedor. Esto permite acceder al servidor MySQL dentro del 
						contenedor a través del puerto 3307 del host.
						--name mysql8: Asigna un nombre al contenedor, en este caso, "mysql8".
						--network spring: Conecta el contenedor a la red llamada "spring". Esto es útil si 
						tienes otros contenedores en la misma red y deseas que se comuniquen entre sí.
						-e MYSQL_ROOT_PASSWORD=Danger120-: Establece la variable de entorno MYSQL_ROOT_PASSWORD 
						con el valor "Danger120-", que se utilizará como la contraseña de root para MySQL.
						-e MYSQL_DATABASE=db_springboot_backend: Establece la variable de entorno MYSQL_DATABASE 
						con el valor "db_springboot_backend", que será el nombre de la base de datos que se creará en MySQL.
						mysql:8: Especifica el nombre de la imagen de Docker que se utilizará para crear el 
						contenedor, en este caso, "mysql:8", que es la imagen oficial de MySQL versión 8.
					**********************************************************************************
				* Se valida el contenedor 
					docker ps
				* Se crea conexion en workbench
					name: MySqlDocker
					port: 3307 
					user: root
					pass: Danger120- 
			*******************************************************************************************	
			74. Comunicación contenedores con BBDD Dockerizadas****************************************
				* Se modifica datasource para que haga referencia al contenedor mysql
					spring.datasource.url=jdbc:mysql://mysql8:3306/db_springboot_backend?useSSL=false&serverTimezone=America/Argentina/Buenos_Aires&allowPublicKeyRetrieval=true
				* Se contruye la imagen nuevamente desde el workspace 
					docker build -t kiosco:v2 . -f .\kioscoAppSpring\Dockerfile
				* Se levanta el contenedor 
					docker run -p 8080:8080 -d --rm --name KioscoAppSpring --network spring kiosco:v2
				* Se corren los script de base de datos 
				* Se prueba el aplicativo 
			*******************************************************************************************
			77. Docker Volumes: la solución al problema de persistencia de datos***********************
				Los volúmenes en Docker son un mecanismo que permite persistir y compartir datos entre 
				contenedores y el host del sistema operativo donde se ejecuta Docker. Un volumen es un 
				directorio o un archivo que existe fuera del sistema de archivos del contenedor, lo que 
				permite que los datos persistan incluso si el contenedor se detiene o se elimina.

				Los volúmenes son importantes porque, por defecto, los sistemas de archivos de los 
				contenedores son efímeros, lo que significa que cualquier cambio que se realice dentro 
				del contenedor se perderá una vez que el contenedor se detenga o elimine. Sin embargo, 
				al utilizar volúmenes, puedes tener datos persistentes que sobreviven a la vida del 
				contenedor.

				* Se eliminan todos los contenedores
					docker stop mysql8
					docker stop KioscoAppSpring
					docker rm mysql8
				* Se corre contenedor mysql con volumen
					docker run -d -p 3307:3306 --name mysql8 --network spring -e MYSQL_ROOT_PASSWORD=Danger120- -e MYSQL_DATABASE=db_springboot_backend -v data-mysql:/var/lib/mysql --restart=always mysql:8
					
					Explicacion************************************************************************
						-v data-mysql:/var/lib/mysql: Aquí, se está creando un volumen llamado "data-mysql" y 
						se está montando en el directorio "/var/lib/mysql" dentro del contenedor. Esto 
						permite que los datos de MySQL se almacenen de manera persistente fuera del contenedor, 
						en el host.

						--restart=always: Esta opción indica que el contenedor se reiniciará automáticamente 
						siempre que Docker o el sistema operativo se reinicie.
					***********************************************************************************
				* Se listan los volumenes   
					docker volume ls
			*******************************************************************************************
			78. Conectarse desde contenedor cliente de línea de comandos a MySQL/Postgres**************
				* Conectarse con un utilitario 
					docker run -it --rm --network spring mysql:8 bash --> enter  
					mysql -hmysql8 -uroot -p
			*******************************************************************************************
		***********************************************************************************************
		Seccion 10: Docker: Arguments and enviroments Variables****************************************
			Docker soporta dos tipos de variables que podemos configurar:

			Arguments: (En tiempo de construccion)  los "arguments" (argumentos) se refieren a variables 
			que se pueden pasar a un contenedor cuando se ejecuta. Estos argumentos permiten personalizar 
			la ejecución del contenedor y proporcionar valores dinámicos o configuraciones específicas en 
			el momento de la ejecución.

			Enviroment: (En tiempo de ejecucion)Las variables de entorno (environment variables) son valores 
			dinámicos que pueden afectar el comportamiento de un proceso o aplicación en un sistema operativo. 
			Estas variables son almacenadas por el sistema y pueden ser accedidas por diferentes programas en 
			el sistema, lo que las hace muy útiles para configurar el entorno de ejecución de 
			aplicaciones o para proporcionar información relevante.

			80. Trabajando con variables de ambiente***************************************************
				* Se modifica application.properties,se modifica el puerto con la variable PORT
					server.port = ${PORT:8080}
				* Se modifica el dockerfile, se agrega variable PORT 
					ENV PORT 8080
					EXPOSE $PORT
				* Se crea imagen   
					docker build -t kiosco:v4 . -f .\kioscoAppSpring\Dockerfile
				* Se ejecuta contenedor  
					docker run -p 8080:8080 -d --rm --name KioscoAppSpring --network spring kiosco:v3
				* Se para contenedor  
					docker stop KioscoAppSpring 
				* Se pasa variable env en tiempo de construccion   
					docker run -p 8080:8090 --env POR=8090 -d --rm --name KioscoAppSpring --network spring kiosco:v3
				* Se utiliza archivo de enviroment  
					* Se crea en raiz del proyecto file .env, se le agrega variable PORT 
						PORT=8091
					* Se ejecuta contenedor con referencia al file enviroment 
						docker run -p 8080:8091 --env-file .\KioscoAppSpring\.env -d --rm --name KioscoAppSpring --network spring kiosco:v3
			*******************************************************************************************
			80. Trabajando con argumentos en el Dockerfile*********************************************
				* Se modifica Dockerfile, se crean argumentos 
					FROM openjdk:11.0.16 as builder
					ARG APP_NAME=KioscoAppSpring
					WORKDIR /app/$APP_NAME

					COPY ./$APP_NAME/pom.xml /app
					COPY ./$APP_NAME/.mvn ./.mvn
					COPY ./$APP_NAME/mvnw .
					COPY ./$APP_NAME/pom.xml .

					RUN sed -i 's/\r$//' mvnw
					RUN ./mvnw dependency:go-offline

					COPY ./$APP_NAME/src ./src

					RUN ./mvnw clean package -DskipTests

					FROM openjdk:11.0.16

					WORKDIR /app
					RUN mkdir ./logs
					ARG TARGET_FOLDER=/app/KioscoAppSpring/target
					COPY --from=builder $TARGET_FOLDER/KioscoAppSpring.jar .

					ENV PORT 8080
					EXPOSE $PORT

					#ENTRYPOINT ["java", "-jar", "KioscoAppSpring.jar"]
					CMD ["java", "-jar", "KioscoAppSpring.jar"]
				* Se crea imagen 
					docker build -t kiosco:v1 . -f .\kioscoAppSpring\Dockerfile
				* Se ejecuta contenedor 
					docker run -p 8080:8080 --env-file .\KioscoAppSpring\.env -d --rm --name KioscoAppSpring --network spring kiosco:v1
			*******************************************************************************************
			83. Variables de ambiente (env) para los parametros de MySQL*******************************
				* Se modifica los datos de conexion de la base de datos
					spring.datasource.url=jdbc:mysql://${DB_HOST:mysql8:3306}/${DB_DATABASE:db_springboot_backend}?useSSL=false&serverTimezone=America/Argentina/Buenos_Aires&allowPublicKeyRetrieval=true
					spring.datasource.username=${DB_USER:alfonso}
					spring.datasource.password=${DB_PASSWORD:danger120-}
				* Se agregan al file .env 
					PORT=8080
					DB_HOST=mysql8:3306
					DB_DATABASE=db_springboot_backend
					DB_USER=alfonso
					DB_PASSWORD=danger120-
				* Se contruye imagen 
					docker build -t kiosco:v2 . -f .\kioscoAppSpring\Dockerfile 
				* Se ejecuta contenedor 
					docker run -p 8080:8080 --env-file .\KioscoAppSpring\.env -d --rm --name KioscoAppSpring --network spring kiosco:v2
				* Se revisan variables del entorno en el container  
					docker container inspect KioscoAppSpring 

			*******************************************************************************************
			85. Variables de ambiente (env) para los hostnames de los contenedores*********************
				* Se modifica application.properties, se agrega variable DOLAR_SI   
					dolarSiClient.url=${DOLAR_SI:https://www.dolarsi.com/api/api.php?type=valoresprincipales}
				* Se agrega en file .env   
					DOLAR_SI=https://www.dolarsi.com/api/api.php?type=valoresprincipales
				* Se contruye imagen 
					docker build -t kiosco:v5 . -f .\kioscoAppSpring\Dockerfile 
				* Se ejecuta container 
					docker run -p 8080:8080 --env-file .\KioscoAppSpring\.env -d --rm --name KioscoAppSpring --network spring kiosco:v5
				* Se inspecciona  
					docker container inspect KioscoAppSpring 
			*******************************************************************************************
		***********************************************************************************************
		Seccion 11: Docker Compose: Orquestador *******************************************************
			89. Creando el archivo docker compose******************************************************
				* Se eliminan los contenedores 
					Se paran los contenedores --> docker stop name_containes 
					Se eliminan --> docker container prune 
				* Se crea en la raiz del proyecto el file docker-compose.yaml
					version: "3.9"
					services:
					  mysql8:
					    container_name: mysql8
					    image: mysql:8
					    ports:
					      - "3307:3306"
					    environment:
					      MYSQL_ROOT_PASSWORD: Danger120-
					      MYSQL_DATABASE: db_springboot_backend
					    volumes:
					      - data-mysql:/var/lib/mysql
					    restart: always
					    networks:
					      - spring

					  KioscoAppSpring:
					    container_name: KioscoAppSpring
					    image: kiosco
					    ports:
					      - "8080:8080"
					    env_file: ./KioscoAppSpring/.env
					    networks:
					      - spring
					    depends_on:
					      - mysql8
					    restart: always

					volumes:
					  data-mysql:
					    name: data-mysql
					networks:
					  spring:

					Explicacion************************************************************************
						El archivo docker-compose.yml que proporcionaste describe la configuración de 
						dos servicios en Docker, mysql8 y KioscoAppSpring, junto con una definición de 
						volumen y una red personalizada. Vamos a analizar las secciones y opciones clave 
						de este archivo:

						Versión de Compose: El archivo está escrito en la versión 3.9 del formato de Docker 
						Compose. 

						Servicio mysql8:

							container_name: Establece el nombre del contenedor como "mysql8".
							image: Especifica la imagen de Docker a utilizar, en este caso, "mysql:8", que 
							es la versión 8 de MySQL.
							ports: Mapea el puerto 3307 del host al puerto 3306 del contenedor, lo que 
							permitirá acceder a la base de datos de MySQL desde el host a través del puerto 3307.
							environment: Define las variables de entorno que se utilizarán al iniciar el 
							contenedor de MySQL. En este caso, se establece la contraseña del usuario root 
							(MYSQL_ROOT_PASSWORD) y el nombre de la base de datos (MYSQL_DATABASE).
							volumes: Asocia el volumen llamado "data-mysql" con el directorio /var/lib/mysql 
							del contenedor de MySQL, lo que permitirá persistir los datos de la base de datos 
							fuera del contenedor.
							restart: Indica que el contenedor se reiniciará automáticamente en caso de 
							detenerse inesperadamente.
							networks: Asocia el servicio al network personalizado llamado "spring".
						
						Servicio KioscoAppSpring:

							container_name: Establece el nombre del contenedor como "KioscoAppSpring".
							image: Especifica la imagen de Docker a utilizar, en este caso, "kiosco:v5". 
							La versión "v5" de la imagen "kiosco".
							ports: Mapea el puerto 8080 del host al puerto 8080 del contenedor, 
							permitiendo acceder a la aplicación Spring desde el host a través del puerto 8080.
							env_file: Especifica el archivo .env ubicado en la carpeta ./KioscoAppSpring 
							para cargar las variables de entorno del contenedor.
							networks: Asocia el servicio al network personalizado llamado "spring".
							depends_on: Indica que este servicio depende del servicio "mysql8", lo que 
							significa que el servicio de MySQL se iniciará antes de que se inicie este 
							servicio.
							restart: Indica que el contenedor se reiniciará automáticamente en caso de 
							detenerse inesperadamente.
						
						Volumen data-mysql: Define un volumen llamado "data-mysql", que se utiliza para 
						persistir los datos de la base de datos MySQL fuera del contenedor.

						Network spring: Define una red personalizada llamada "spring", que se utilizará 
						para conectar los dos servicios, permitiendo que se comuniquen entre sí.

						En general, este archivo docker-compose.yml configura dos servicios que se 
						ejecutarán en contenedores separados, uno para MySQL y otro para una aplicación 
						Spring llamada "KioscoAppSpring". También se definen volúmenes y redes 
						personalizadas para facilitar la persistencia de datos y la comunicación entre 
						los contenedores.
					***********************************************************************************
				* Se ejecuta el docker compose 
					docker-compose up -d

				* Para bajar el docker compose 
			*******************************************************************************************
			93. Build imagen en docker compose*********************************************************
				* Se incluye la construccion de imagenes en el docker-compose 
					version: "3.9"
					services:
					  mysql8:
					    container_name: mysql8
					    image: mysql:8
					    ports:
					      - "3307:3306"
					    environment:
					      MYSQL_ROOT_PASSWORD: Danger120-
					      MYSQL_DATABASE: db_springboot_backend
					    volumes:
					      - data-mysql:/var/lib/mysql
					    restart: always
					    networks:
					      - spring

					  kiosco_app_spring:
					    container_name: KioscoAppSpring
					    build:
					      context: ./
					      dockerfile: ./KioscoAppSpring/Dockerfile
					    ports:
					      - "8080:8080"
					    env_file: ./KioscoAppSpring/.env
					    networks:
					      - spring
					    depends_on:
					      - mysql8
					    restart: always

					volumes:
					  data-mysql:
					    name: data-mysql
					networks:
					  spring:
				* Se ejecuta docker compose para que construya la imagen siempre 
					docker-compose up --build -d
			*******************************************************************************************
		***********************************************************************************************
		Seccion 12: Docker Hub: Repository*************************************************************
			Reporsitorio en dockerHub 
				docker push dirielan3/kiosco_app_spring:tagname
			* Se crea imagen para subir al repositorio 
				docker build -t dirielan3/kiosco_app_spring . .\kioscoAppSpring\Dockerfile
			* Otra opcion es crear un clon con la ya existente 
				docker tag workspace-kiosco_app_spring dirielan3/kiosco_app_spring 
			* Se inicia session en docker hub   
				docker login 
					username --> dirielan3  
					password --> Danger120-
			* Se realiza push   
				docker push dirielan3/kiosco_app_spring
			* Se baja la imagen del repositorio 
				 docker pull dirielan3/kiosco_app_spring

			* Modificando docker-compose para obtener imagenes desde repositorio Docker Hub
				version: "3.9"
				services:
				  mysql8:
				    container_name: mysql8
				    image: mysql:8
				    ports:
				      - "3307:3306"
				    environment:
				      MYSQL_ROOT_PASSWORD: Danger120-
				      MYSQL_DATABASE: db_springboot_backend
				    volumes:
				      - data-mysql:/var/lib/mysql
				    restart: always
				    networks:
				      - spring

				  kiosco_app_spring:
				    container_name: KioscoAppSpring
				    image: dirielan3/kiosco_app_spring
				#    build:
				#      context: ./
				#      dockerfile: ./KioscoAppSpring/Dockerfile
				    ports:
				      - "8080:8080"
				    env_file: ./KioscoAppSpring/.env
				    networks:
				      - spring
				    depends_on:
				      - mysql8
				    restart: always

				volumes:
				  data-mysql:
				    name: data-mysql
				networks:
				  spring:
		***********************************************************************************************
		Seccion 13: AWS Despliegue en produccion*******************************************************
			User: dirielan.ar@gmail.com 
			pass: danger120-
			101. Comenzando con Amazon EC2*************************************************************
				* Se escoge la zona ohio 
				* Se lanza una instancia 
				* Se le da nombre a la instancia KioscoAppSpring 
				* Se escoge la imagen del SO Amazon Linux  
					Amazon Linux 2023 AMI 2023.1.20230719.0 x86_64 HVM kernel-6.1
			*******************************************************************************************
			102. Lanzando nuestra instancia EC2********************************************************
				* Se escoge el tipo de instancia t2.micro
				* Se crea par de claves 
					* Se coloca la clave y se genera, se descarga certificado  --> dirielfran
				* Configuraciones de red 
					reglas de grupos, se crea una regla para el puerto 8080 y otra para el 8081
				* Lanzar instancia 
			*******************************************************************************************
			103. Conectar a instancia de amazon linux EC2**********************************************
				* Se instala ubuntu para windows
				* Ingresamos 
					user dirielfran
					pass danger120-
				* Ubicamos en la carpeta donde tenemos .pem  y --> cp dirielfran.pem /root
				* Agregamos permisos de su 
					sudo su 
				* Se ingresa en root --> cd /root
				* Damos permisos al certificado --> chmod 400 dirielfran.pem
					El comando "chmod 400 dirielfran.pem" se utiliza en sistemas Unix 
					y Linux para cambiar los permisos de un archivo. La opción "400" establece 
					permisos específicos para el archivo "dirielfran.pem".

					En detalle, estos permisos se definen de la siguiente manera:

					"4": Permiso de lectura para el propietario del archivo (dueño).
					"0": Sin permisos de escritura o modificación para el propietario.
					"0": Sin permisos de lectura para el grupo del archivo.
					"0": Sin permisos de escritura o modificación para el grupo del archivo.
					"0": Sin permisos de lectura para otros usuarios (resto del mundo).
					"0": Sin permisos de escritura o modificación para otros usuarios.
					El resultado de ejecutar este comando sería establecer el archivo "dirielfran.pem" con 
					permisos de solo lectura para el propietario del archivo, mientras que ni el grupo 
					ni otros usuarios tendrían ningún tipo de acceso.
				* Se conecta a la instancia ec2  
					ssh -i "dirielfran.pem" ec2-user@ec2-18-191-212-237.us-east-2.compute.amazonaws.com
				* Se ejecuta pwd para saber donde estamos --> carpeta usuario   
				* Se actualizan los paquetes --> sudo yum update -y / sudo apt update
				* Se instala docker -- > sudo yum install docker / sudo apt install docker
				* Se levanta servicio de docker --> sudo service docker start 
				* Se valida version --> docker version 
				*  descarga el archivo ejecutable de Docker Compose para Linux (64 bits) y lo 
					guarda en la ruta "/usr/local/bin/docker-compose" o si estas en ubuntu en --> root
					sudo curl -SL https://github.com/docker/compose/releases/download/v2.20.2/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose
				* Se le dan permisos 
					sudo chmod +x /usr/local/bin/docker-compose
				* El comando crea un enlace simbólico (symlink) desde /usr/local/bin/docker-compose 
				hacia /usr/bin/docker-compose. Este enlace simbólico permite que el ejecutable de 
				Docker Compose sea accesible desde la ubicación /usr/bin/, lo que facilita su uso y 
				ejecución sin tener que escribir la ruta completa cada vez. 
					sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
				* Se valida que se instalo 
					docker-compose version
			*******************************************************************************************
			104. Corriendo contenedores con docker-compose en maquina remota AWS EC2*******************
				* Se incluyen las variables de entorno en el docker-compose 
					version: "3.9"
					services:
					  mysql8:
					    container_name: mysql8
					    image: mysql:8
					    ports:
					      - "3307:3306"
					    environment:
					      MYSQL_ROOT_PASSWORD: Danger120-
					      MYSQL_DATABASE: db_springboot_backend
					    volumes:
					      - data-mysql:/var/lib/mysql
					    restart: always
					    networks:
					      - spring

					  kiosco_app_spring:
					    container_name: KioscoAppSpring
					    image: dirielan3/kiosco_app_spring
					#    build:
					#      context: ./
					#      dockerfile: ./KioscoAppSpring/Dockerfile
					    ports:
					      - "8080:8080"
					#    env_file: ./KioscoAppSpring/.env
					    environment:
					      PORT: 8080
					      DB_HOST: mysql8:3306
					      DB_DATABASE: db_springboot_backend
					      DB_USER: alfonso
					      DB_PASSWORD: danger120-
					      DOLAR_SI: https://www.dolarsi.com/api/api.php?type=valoresprincipales
					    networks:
					      - spring
					    depends_on:
					      - mysql8
					    restart: always

					volumes:
					  data-mysql:
					    name: data-mysql
					networks:
					  spring:
				* Se copia docker-compose a la carpeta root de linux 
					* Desde ubunto nos colocamos donde se encuentra el docker-compose y se ejecuta  
						sudo cp docker-compose.yaml /root
				* Desde el local copiamos el archivo en ec2 
					scp -i "dirielfran.pem" docker-compose.yaml ec2-user@ec2-18-118-227-139.us-east-2.compute.amazonaws.com:/home/ec2-user
				* Nos conectamos ec2
					ssh -i "dirielfran.pem" ec2-user@ec2-18-118-227-139.us-east-2.compute.amazonaws.com
				* Con el comando free vemos espacios en disco --> free 
				* Se ejecuta --> sudo docker login 
					dirielan3
					Danger120-
				* Se ejecuta  -- > sudo docker-compose up 
				* Se ejecuta script conectandonos ssh desde workbench (1ra manera)
					* Desde workbech se configura la conexion
						Conection name: ec2DockerKioscoApp
						SSH hostname: ec2-user@ec2-18-191-225-1.us-east-2.compute.amazonaws.com:22
						SSH username: ec2-user
						SSH key File: C:\Users\Elvis Areiza\Desktop\Elvis\DockeKubernetes\dirielfran.pem
						MySQL Hostname: 127.0.0.1 
						Mysql Server Port: 3307 
						username: root  
						password: Danger120-
				* Se lleva script de base de datos al ec2 (2da forma)
					* No ubicamos como usuario en ubuntu y accedemos a la ubicacion del script sql   
						dirielfran@eareiza:/mnt/c/Users/Elvis Areiza/Desktop/Elvis/kiosco/backup$
					* Se copia script en root 
						sudo su
						sudo cp prod230723.sql /root
						cd /root 
						ls 
					* Desde local copiamos el archivo en ec2 
						scp -i "dirielfran.pem" prod230723.sql ec2-user@ec2-18-118-227-139.us-east-2.compute.amazonaws.com:/home/ec2-user
				* Nos conectamos ec2
					ssh -i "dirielfran.pem" ec2-user@ec2-18-118-227-139.us-east-2.compute.amazonaws.com	
				* Nos conectamos de manera interactiva con el contenedor mysql8
					* como nos conectamos de manera interactiva con el contenedor  
						sudo docker exec -it mysql8 mysql -uroot -pDanger120-
					* Se corre el script de base datos 
						[root@ip-172-31-10-188 ec2-user]# docker exec -i mysql8 mysql -uroot --password=Danger120- db_springboot_backend < prod230723.sql
			*******************************************************************************************
			109. Creando Cluster ECS y una definición de tarea o task definition***********************
				* Se crae cluster  --> https://us-east-2.console.aws.amazon.com/ecs/v2/create-cluster?region=us-east-2
					* Se le asigna nombre al cluster ECSCursoAWS 
					* Se aplica opcion Create VPC, se deja la configuracion por defecto --> create   
					* Se accede al menu izq -> task definition
						* Se selecciona lanzamiento con fargate 
							* Se le agrega nombre a la tarea 
								/*rol para ejecutar tareas*/
								* Se crea rol por consola --> se busca ecs --> AWSServiceRoleForECS --> crear 
								* Se selecciona caso de uso --> buscar --> Elastic Container Service Task --> siguient
								* Se agrega permisos --> busca --> AmazonECSTaskExecutionRolePolicy --> siguiente 
								* Se le da nombre --> ECSkioscoAppRol --> crear rol   
							* Se refrezca y se asigna rol creado 
							* Sistema operativo --> linux 
							* Se agrega memoria --> 1G 
							* Se agrega CPU 0.5 vCPU  
			*******************************************************************************************
		***********************************************************************************************
		Seccion 14: Kubernetes*************************************************************************
			122. Introducción a Kubernetes*************************************************************
				Kubernetes (comúnmente abreviado como K8s) es una plataforma de código abierto para la 
				orquestación y gestión de contenedores, desarrollada originalmente por Google y ahora 
				mantenida por la Cloud Native Computing Foundation (CNCF). Kubernetes facilita la 
				implementación, escalado y operación de aplicaciones en contenedores, proporcionando una 
				infraestructura más eficiente y flexible para la administración de aplicaciones distribuidas.

				Características principales de Kubernetes:

					*Orquestación de Contenedores: Kubernetes permite automatizar la implementación, 
					el escalado y la gestión de aplicaciones en contenedores. Los contenedores son unidades 
					portátiles y livianas que encapsulan el código y todas sus dependencias, lo que facilita 
					la gestión y el despliegue de aplicaciones.

					* Alta disponibilidad y tolerancia a fallos: Kubernetes asegura que las aplicaciones se 
					mantengan en funcionamiento incluso en caso de fallos en los nodos o en los contenedores. 
					Puede detectar y reemplazar automáticamente los contenedores que fallan y redistribuir 
					la carga entre los nodos. Revicion de salud (health check) del contenedor. Load Balancer

					* Escalabilidad: Kubernetes permite escalar de forma horizontal las aplicaciones para 
					satisfacer la demanda de recursos en función de la carga. Puede aumentar o disminuir el 
					número de réplicas de un contenedor automáticamente según las reglas de escalado definidas.

					* Gestión del tráfico y balanceo de carga: Kubernetes puede dirigir el tráfico de red 
					hacia las aplicaciones desplegadas y distribuir la carga entre diferentes réplicas de 
					contenedores para mantener un alto rendimiento y disponibilidad.

					* Despliegue de versiones y actualizaciones: Kubernetes facilita la implementación y 
					actualización continua de aplicaciones mediante la definición de estrategias de despliegue 
					y versionado de contenedores.

					* Configuración declarativa: Kubernetes utiliza archivos de configuración YAML o JSON 
					para definir el estado deseado de las aplicaciones y recursos. Esto permite una gestión 
					declarativa y controlada de la infraestructura.

					* Portabilidad y agnosticismo del proveedor: Kubernetes es compatible con múltiples 
					proveedores de servicios en la nube y también se puede implementar en entornos locales 
					y en centros de datos propios.

				Workwer Node 
					En Kubernetes, un "Worker Node" (Nodo de Trabajo) es una de las partes fundamentales 
					de un clúster. Es un nodo en el que se ejecutan los contenedores que componen las 
					aplicaciones y cargas de trabajo que se despliegan en el clúster.

					Un clúster de Kubernetes consta de dos tipos principales de nodos:

						Master Node (Nodo Maestro): Es el nodo principal encargado de controlar y gestionar 
						todo el clúster. Se encarga de la planificación de tareas, monitorización del estado 
						de los nodos, asignación de recursos, entre otras funciones de gestión. Generalmente, 
						hay uno o más nodos maestros en un clúster de Kubernetes.

						Worker Node (Nodo de Trabajo): Son los nodos donde se ejecutan los contenedores que 
						forman las aplicaciones que se desean desplegar en el clúster. Los worker nodes son 
						los que realmente ejecutan las cargas de trabajo y proporcionan la capacidad de cómputo 
						y almacenamiento para las aplicaciones.

						En resumen, los nodos de trabajo (Worker Nodes) son los componentes del clúster de 
						Kubernetes que realizan el trabajo real de ejecutar contenedores y alojar aplicaciones. 
						Los nodos de trabajo están conectados al nodo maestro y son monitoreados por este para 
						asegurarse de que la aplicación se ejecute de manera confiable y en alta disponibilidad.

						Cada nodo de trabajo debe tener instalado y configurado el software de Kubernetes, 
						incluido el "kubelet" (agente que interactúa con el nodo maestro) y el "kube-proxy" 
						(encargado de manejar el tráfico de red entre los servicios en el clúster).

						Cuando se despliega una aplicación en Kubernetes, los contenedores de esa aplicación 
						se programan en los nodos de trabajo disponibles en función de los recursos disponibles 
						(CPU, memoria, etc.) y las restricciones de afinidad o tolerancia a fallos definidas. 
						Así, Kubernetes aprovecha la capacidad de cómputo distribuida de los nodos de trabajo 
						para ejecutar y gestionar las aplicaciones de manera eficiente.

					Pod es la unidad más pequeña y básica de despliegue y escalado. Representa un grupo de uno o 
					más contenedores que comparten un entorno de ejecución y recursos comunes, como direcciones IP, 
					almacenamiento y espacio de nombres. Los Pods son la unidad principal en la que se despliegan 
					las aplicaciones y cargas de trabajo en un clúster de Kubernetes.

					Un Pod puede contener uno o varios contenedores, pero generalmente se usa para agrupar contenedores 
					relacionados que necesitan compartir recursos y estar estrechamente acoplados. Por ejemplo, si 
					tienes una aplicación web y su base de datos, podrías empaquetar ambos en un solo Pod para que 
					puedan comunicarse entre sí a través de la red interna del clúster.

				Kubelet es un componente fundamental en un clúster de Kubernetes. Es el agente que se ejecuta 
				en cada nodo de trabajo (worker node) y se encarga de gestionar los contenedores y los Pods en ese 
				nodo. El kubelet es responsable de asegurarse de que los Pods estén en el estado deseado y que 
				los contenedores dentro de los Pods se estén ejecutando correctamente.

				Kube-proxy es otro componente importante en un clúster de Kubernetes. Es un servicio de red que 
				se ejecuta en cada nodo de trabajo (worker node) y es responsable de la gestión del tráfico 
				de red dentro del clúster.

				Master Node Es el nodo principal encargado de controlar y gestionar 
				todo el clúster. Se encarga de la planificación de tareas, monitorización del estado 
				de los nodos, asignación de recursos, entre otras funciones de gestión. Generalmente, 
				hay uno o más nodos maestros en un clúster de Kubernetes.

					Control Plane  es como el "cerebro" de Kubernetes, toma decisiones sobre cómo deben 
					funcionar y comportarse los recursos dentro del clúster.

						Api Server Es el punto de entrada principal para todas las operaciones y la 
						comunicación dentro del clúster. El API Server expone una API RESTful que permite 
						a los usuarios y otros componentes interactuar y administrar los recursos del 
						clúster de Kubernetes. El Api Server se comunica con los Kublet

						Schedules en Kubernetes es una parte esencial del plano de control (control plane). 
						Su función principal es determinar en qué nodo del clúster se deben ejecutar los 
						pods de las aplicaciones recién creados o aquellos que necesitan ser reprogramados 
						debido a cambios en el clúster.

						El scheduler toma decisiones basándose en varias políticas y requisitos definidos 
						por el usuario, así como en el estado actual del clúster.

						El componente "kube-controller-manager" (gestor del controlador Kubernetes) es 
						otro de los elementos que forman parte del plano de control (control plane) de Kubernetes. 
						Este gestor es responsable de ejecutar varios controladores que se encargan de mantener 
						el estado deseado del clúster y tomar acciones en función de los cambios detectados en 
						los recursos.

				Cluster  Un "clúster" en el contexto de Kubernetes se refiere a un grupo o conjunto de nodos 
				(máquinas) que trabajan juntos para proporcionar una plataforma de orquestación y administración 
				de contenedores. Estos nodos están organizados y coordinados por el plano de control (control plane) 
				de Kubernetes para ejecutar aplicaciones en contenedores de manera eficiente y escalable.

				En un clúster de Kubernetes, los nodos pueden estar distribuidos en diferentes servidores físicos, 
				máquinas virtuales o incluso en la nube. Cada nodo tiene instalado Kubernetes y proporciona la 
				infraestructura para ejecutar los contenedores que componen las aplicaciones.

				ETCD es un almacén de datos distribuido y altamente disponible que se utiliza en Kubernetes 
				para mantener la configuración y el estado del clúster. Es una parte crítica del plano de 
				control (control plane) de Kubernetes, ya que proporciona la persistencia y coherencia 
				necesarias para el funcionamiento del sistema.
			*******************************************************************************************
			126. Instalación Windows*******************************************************************
				* Se instala Kubectl
					Ref --> https://kubernetes.io/docs/tasks/tools/install-kubectl-windows/
					* Se instala chocolate --> https://chocolatey.org/install
						*desde powershell se ejecuta 
							Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
							
							* Set-ExecutionPolicy Bypass -Scope Process -Force: Esta parte del comando 
							cambia la política de ejecución de PowerShell para permitir la ejecución de 
							scripts en el ámbito del proceso actual. Bypass significa que se omitirán las 
							restricciones de ejecución.

							* [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072: 
							Esta línea establece los protocolos de seguridad de la red que se utilizarán para las solicitudes web. 
							Agrega el protocolo TLS 1.2 (3072) a los protocolos existentes.

							* iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1')): 
							Esta parte del comando descarga y ejecuta el script de instalación de Chocolatey desde la 
							URL proporcionada. El comando iex ejecuta el contenido descargado como un script.
						* Se prueba install --> choco -?  --> Chocolatey v1.2.0
					* Se instala kubernetes-cli por medio de chocolate 
						choco install kubernetes-cli
						kubectl version --client  --> Kustomize Version: v5.0.1
				* Se instala MiniKube  --> https://minikube.sigs.k8s.io/docs/start/
					* Se descarga .exe 
					* Se ejecuta
					* Se navega a home  
						* Desde cmd --> cd %USERPROFILE%
							* Se crea directorio --> mkdir .kube
							* Se accede al folder creado 
							* desde power shell --> New-Item -ItemType File -Force $env:USERPROFILE\.kube\config
							* Se valida que se haya creado --> Get-ChildItem $env:USERPROFILE\.kube
					* Se inicia 
						* Desde power shell -->  minikube start --driver=hyperv --memory 2200
					* Para validar 
						* Abrrir administrador Hyper-v, debe aparecer la maquina virtual 
						* Se abre el archivo config creado, ya debe tener las configuraciones para que 
						el cluster de minikube
						* Se valida version --> minikube version   
				* Sentencias de ejecucion del minikube 
					minikube status 
					minikube stop 
					minikube start 
					minikube delete 
			*******************************************************************************************
			131. Creando deployment mysql**************************************************************
				* Se accede intellij como administrador   
				* Se valida como esta el minikube 
					terminal --> minikube status
				* Si hay algun servicio detenido, se para y se ejecuta nuevamente 
					* minikube stop 
					* minikube start --driver=hyperv
				* Desde la terminal de intellj se crea deployment para mysql  
					kubectl create deployment mysql8 --image=mysql:8 --port=3306
				* Se valida recurso creado   
					kubectl get deployments
				* Se valida pod creado 
					kubectl get pods 
				* Se ve el detalle del pod creado  
					kubectl describe pods mysql8-59977b5746-fwnd4(nombre del pod)  
				* Se ven los logs del pod   
					kubectl logs mysql8-59977b5746-fwnd4

				Nota: Se creo el deployment de forma iterativa, pero de esta forma no podemos tener 
				variables de ambiente, por lo que nos genera error al necesitar la clave y usuario de la 
				base de datos, en la siguiente seccion se realizara de forma declarativa, para solucionar 
			*******************************************************************************************
			132. Deployment mysql con las variables de ambientes***************************************
				* Se elimina el deployment  
					kubectl delete deployment mysql8
				* Se crea un manifiesto de un despliegue de MySQL en Kubernetes
					kubectl create deployment mysql8 --image=mysql:8 --port=3306 --dry-run=client -o yaml > deployment-mysql.yaml

					kubectl create deployment mysql8: 	Crea un despliegue llamado "mysql8".
					--image=mysql:8: 					Especifica la imagen de Docker a utilizar para el despliegue. En este caso, se utiliza la imagen de MySQL versión 8.
					--port=3306: 						Define el puerto en el que el contenedor expone su servicio. En este caso, el puerto 3306 utilizado por MySQL.
					--dry-run=client: 					Simula la operación de creación sin modificar el clúster.
					-o yaml: 							Especifica que la salida se formateará en YAML.
					> deployment-mysql.yaml: 			Redirige la salida del comando a un archivo llamado "deployment-mysql.yaml".

					Nota: Esto crea un archivo .yaml en la raiz del proyecto, estamos obligados a crear este archivo 
					para poder pasar variables de ambiente 

					apiVersion: apps/v1
					kind: Deployment
					metadata:
					  name: mysql8
					spec:
					  replicas: 1
					  selector:
					    matchLabels:
					      app: mysql8
					  template:
					    metadata:
					      labels:
					        app: mysql8
					    spec:
					      containers:
					      - image: mysql:8
					        name: mysql8
					        ports:
					        - containerPort: 3306
					        env:
					          - name: MYSQL_ROOT_PASSWORD
					            value: Danger120-
					          - name: MYSQL_DATABASE
					            value: db_springboot_backend

				* Se realiza apply del deployment  
					kubectl apply -f .\deployment-mysql.yaml
				* Se valida el pods 	
					kubectl get pods 
					kubectl describe pods mysql8-5676c64bf6-sql5d 
					kubectl logs  mysql8-5676c64bf6-sql5d 
			*******************************************************************************************
			133. Creando el servicio de mysql para la comunicación interna con hostname****************
				* Se expone deployment como servicio 
					Se exponer un deployment llamado "mysql8" como un servicio con un puerto específico 
					y un tipo de servicio "ClusterIP". Este comando es útil para acceder a los pods del 
					deployment a través de una dirección IP interna dentro del clúster de Kubernetes.

					--> kubectl expose deployment mysql8 --port=3306 --type=ClusterIP


					kubectl: Es la herramienta de línea de comandos de Kubernetes que se utiliza para 
					interactuar con clústeres de Kubernetes.

					expose: Un subcomando de kubectl que se utiliza para exponer recursos como servicios.

					deployment mysql8: Esto se refiere al nombre del deployment que deseas exponer. 
					En este caso, se asume que existe un deployment llamado "mysql8".

					--port=3306: Esto especifica el puerto en el que el servicio escuchará dentro del 
					clúster. En este caso, el servicio escuchará en el puerto 3306, que es un puerto 
					comúnmente utilizado para MySQL.

					--type=ClusterIP: Esto establece el tipo de servicio como "ClusterIP", lo que significa 
					que el servicio solo será accesible internamente dentro del clúster y tendrá una 
					dirección IP virtual (Cluster IP) que se asignará al servicio.

					Nota: ten en cuenta que este servicio no estará expuesto externamente a la red pública 
					debido a su tipo "ClusterIP". Si deseas que el servicio sea accesible desde fuera del 
					clúster, debes cambiar el tipo de servicio a "LoadBalancer" o "NodePort".

					LoadBalancer: Cuando expones un servicio con el tipo "LoadBalancer", Kubernetes 
					interactúa con el proveedor de la nube (si estás utilizando un clúster en la nube) 
					para crear un equilibrador de carga externo que dirige el tráfico hacia los pods 
					detrás del servicio. Esto permite que los usuarios externos accedan al servicio a 
					través de una dirección IP pública. Sin embargo, ten en cuenta que no todas las 
					soluciones de Kubernetes admiten de manera nativa el tipo "LoadBalancer". En algunas 
					plataformas, como minikube, puedes tener una emulación básica de "LoadBalancer", 
					pero en una implementación completa en la nube, esta opción puede crear un equilibrador 
					de carga real.

					NodePort: Cuando utilizas el tipo de servicio "NodePort", Kubernetes asigna 
					automáticamente un puerto en cada nodo del clúster y reenvía el tráfico desde 
					ese puerto a los pods detrás del servicio. Esto permite que los usuarios externos 
					accedan a los servicios utilizando la dirección IP de cualquier nodo junto con el 
					puerto asignado. Aunque los servicios NodePort son accesibles desde fuera del clúster, 
					a menudo se utilizan junto con un equilibrador de carga externo para distribuir el 
					tráfico de manera más uniforme.

					En resumen, al exponer un servicio en Kubernetes, puedes elegir entre diferentes 
					tipos de servicio según tus necesidades y entorno. "ClusterIP" es para acceder 
					internamente en el clúster, "LoadBalancer" es para exponer servicios a través de 
					un equilibrador de carga externo en la nube, y "NodePort" es para exponer servicios 
					en puertos asignados en los nodos del clúster. La elección del tipo de servicio 
					depende de la forma en que desees acceder a tus aplicaciones y de la infraestructura 
					que estés utilizando.
			*******************************************************************************************
			134. Creando Deployment kioscoApp**********************************************************
				* Se crea deployment para kioscoApp con la imagen de dockerHub y por el puerto 8080
					kubectl create deployment kiosco-app-spring --image=dirielan3/kiosco_app_spring --port=8080 --dry-run=client -o yaml > deployment-kioscoapp.yaml
				* Se crea secret con las credenciales de dockerhub, ya que es privado el repositorio 
					kubectl create secret docker-registry kioscoapp --docker-server=https://hub.docker.com/ --docker-username=dirielan3 --docker-password=Danger120- --docker-email=dirielfran@gmail.com

					* Se valida secret creado 
						kubectl get secrets kioscoapp
				* Se modifica el manifiesto deployment-kioscoapp.yaml, se agrega Secret creado
					apiVersion: apps/v1
					kind: Deployment
					metadata:
					  name: kiosco-app-spring
					spec:
					  replicas: 1
					  selector:
					    matchLabels:
					      app: kiosco-app-spring
					  template:
					    metadata:
					      labels:
					        app: kiosco-app-spring
					    spec:
					      containers:
					      - image: dirielan3/kiosco_app_spring
					        name: kiosco-app-spring-66nvn
					        ports:
					        - containerPort: 8080
					      imagePullSecrets:
					        - name: kioscoapp

				* Se ejecuta el deployment 	
					kubectl apply -f .\deployment-kioscoapp.yaml
			*******************************************************************************************
			135. Creando el servicio de usuarios para la comunicación, tráfico y LoadBalancer**********
				* Se utiliza para crear un servicio que expone los puertos de un deployment para que 
				sean accesibles desde fuera del clúster
					kubectl expose deployment kiosco-app-spring --port=8080 --type=LoadBalancer

				expose deployment kiosco-app-spring: Crea un servicio basado en el deployment llamado 
					kiosco-app-spring.
				--port=8080: Especifica el puerto que se expondrá en el servicio.
				--type=LoadBalancer: Indica que el servicio debe ser de tipo LoadBalancer, lo que 
					permite que el clúster o la plataforma en la que se está ejecutando Kubernetes asigne 
					una dirección IP externa y distribuya el tráfico entre los pods del deployment.

				Una vez ejecutado el comando, Kubernetes creará un servicio que redirige el tráfico 
				del puerto 8080 al deployment kiosco-app-spring. Si estás utilizando una plataforma 
				que admite balanceadores de carga externos, como servicios en la nube, también se 
				creará un balanceador de carga externo que distribuirá el tráfico a tus pods.

				* Se valida servicio creado 
					 kubectl get services
						NAME                TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
						kiosco-app-spring   LoadBalancer   10.100.159.142   <pending>     8080:30337/TCP   3m58s
						kubernetes          ClusterIP      10.96.0.1        <none>        443/TCP          47h
						mysql8              ClusterIP      10.100.181.222   <none>        3306/TCP         47h
				* Se obtienen la direccion url externa del minikube 
					minikube service kiosco-app-spring --url
						|-----------|-------------------|-------------|-----------------------------|
						| NAMESPACE |       NAME        | TARGET PORT |             URL             |
						|-----------|-------------------|-------------|-----------------------------|
						| default   | kiosco-app-spring |        8080 | http://172.22.121.128:30337 |
						|-----------|-------------------|-------------|-----------------------------|
			*******************************************************************************************
			136. Actualizando imagen de un deployment**************************************************
				* Se le realiza modificacion al aplicativo, se modifica controlller UserController
					/*@Autowired private ApplicationContext context;: Esto inyecta el contexto de la 
					aplicación en el controlador mediante la anotación @Autowired. El ApplicationContext 
					es una parte fundamental de Spring y contiene información sobre los beans y las 
					configuraciones de la aplicación.*/
					@Autowired
					private ApplicationContext context;

					@GetMapping("/crash")
					public void crash(){
						/*((ConfigurableApplicationContext)context).close();: Esta línea de código 
							cierra el contexto de la aplicación. Primero, se realiza un casting del 
							contexto a ConfigurableApplicationContext para poder usar el método close() 
							que permite cerrar el contexto.*/
						((ConfigurableApplicationContext)context).close();
					}
				* Se construye imagen 
					docker build -t kiosco . -f .\KioscoAppSpring\Dockerfile
				* Se taguea la imagen  
					docker tag kiosco dirielan3/kiosco_app_free:v1 
				* Se pushea la imagen 
					docker push dirielan3/kiosco_app_free:v1
				* Se update la imagen del deploy 
					kubectl set image deployment kiosco-app-spring kiosco-app-free-mg787=dirielan3/kiosco_app_free:v1

					Explication: Actualiza la imagen del contenedor kiosco-app-free-mg787 en el Deployment 
					kiosco-app-spring a la imagen dirielan3/kiosco_app_free:v1.

				* Para probar el reinicio que realiza kubernetes de una aplicacion cuando se cae 
					* Por postman le pegamos al endpoint creado --> {{url}}/api/crash
					* Se valida en los ppod en ejecuacion el reinicio del app 
						kubectl get pods

							NAME                                 READY   STATUS    RESTARTS      AGE
							kiosco-app-spring-6f79cd949d-jcbxw   1/1     Running   2 (17s ago)   7m6s
							mysql8-5676c64bf6-4q87m              1/1     Running   0             163m
			*******************************************************************************************
			137. Replicas, escalando instancias de pods en Deployment**********************************
				* Se crean dos replicas del deplyment 
					kubectl scale deployment kiosco-app-spring --replicas=2
				* prueba desde postman  
					se baja la aplicacion con -->  {{url}}/api/crash
				* Se invoka un servicio  
					{{url}}/api/inventarios/242  

					Nota:  al tener dos replicas debe seguir funcionando la app, y levantar nuevamente 
					la replica del servicio caido
			*******************************************************************************************
			138. Pasando del método Imperativo al Declarativo con archivos de configuraciones**********
				* Información del servicio en formato YAML, te muestra manifiesto del servicio 
					kubectl get service mysql8 -o yaml

					apiVersion: v1
					kind: Service
					metadata:
					  name: mysql8
					spec:
					  ports:
					  - nodePort: 31701
					    port: 3306
					    protocol: TCP
					    targetPort: 3306
					  selector:
					    app: mysql8
					  type: NodePort
					  
				* Se guarda manifiesto  del servicio mysql8 en un archivo llamado svc-mysql8.yaml 
					kubectl get service mysql8 -o yaml > svc-mysql8
				* Se guarda manifiesto del servicio kioscoapp en un archivo   
					kubectl get service kiosco-app-spring -o yaml > svc-kiosco-app.yaml
			*******************************************************************************************
			139. Trabajando con la forma Declarativa yaml**********************************************
				* Se elimina el deployment de kioscoApp utilizando el manifiesto creado
					kubectl delete -f .\deployment-kioscoapp.yaml
				* Se comprueba que se elimino 
					kubectl get deployments

						NAME     READY   UP-TO-DATE   AVAILABLE   AGE
						mysql8   1/1     1            1           41h
				* Se crea deployment nuevamente, con tres replicas  
					kubectl apply -f .\deployment-kioscoapp.yaml

					NAME                                 READY   STATUS    RESTARTS      AGE
					kiosco-app-spring-57bffd77bd-cvjfw   1/1     Running   0             7m54s
					kiosco-app-spring-57bffd77bd-w4x94   1/1     Running   0             8m48s
					kiosco-app-spring-57bffd77bd-wxr48   1/1     Running   0             8m48s
					mysql8-5676c64bf6-4q87m              1/1     Running   1 (25h ago)   41h

				* Se utiliza la herramienta --> minikube dashboard

				* se le agragan variables de entorno al manifiesto -> deployment-kioscoapp.yaml 
					apiVersion: apps/v1
					kind: Deployment
					metadata:
					  name: kiosco-app-spring
					spec:
					  replicas: 3
					  selector:
					    matchLabels:
					      app: kiosco-app-spring
					  template:
					    metadata:
					      labels:
					        app: kiosco-app-spring
					    spec:
					      containers:
					      - image: dirielan3/kiosco_app_free:v1
					        name: kiosco-app-free-mg787
					        ports:
					          - containerPort: 8080
					        env:
					          - name: PORT
					            value: "8080"
					          - name: DB_HOST
					            value: "mysql8:3306"
					          - name: DB_DATABASE
					            value: db_springboot_backend
					          - name: DB_USER
					            value: alfonso
					          - name: DB_PASSWORD
					            value: "danger120-"
			*******************************************************************************************
			146. Configurando volume hostPath para MySQL***********************************************
				* Se crea volume de tipo hostPath en el manifiesto , para que este en el workerNode y 
				fuera del pod

					apiVersion: apps/v1
					kind: Deployment
					metadata:
					  name: mysql8
					spec:
					  replicas: 1
					  selector:
					    matchLabels:
					      app: mysql8
					  template:
					    metadata:
					      labels:
					        app: mysql8
					    spec:
					      containers:
					        - image: mysql:8
					          name: mysql8
					          ports:
					            - containerPort: 3306
					          env:
					            - name: MYSQL_ROOT_PASSWORD
					              value: Danger120-
					            - name: MYSQL_DATABASE
					              value: db_springboot_backend
					          volumeMounts:
					            - name: data-mysql
					              mountPath: /var/lib/mysql
					      volumes:
					        - name: data-mysql
					          hostPath:
					            path: /var/lib/mysql
					            type: DirectoryOrCreate  
				* Se prueba que los datos queden persistidos al eliminar el pod de mysql 
					* Se elimina pod de mysql  
						kubectl apply -f .\deployment-mysql.yaml
					* Se crea pod nuevamente 
						kubectl apply -f .\deployment-mysql.yaml
					* Se prueba por base de datos si los datos aun estan persistidos  
			*******************************************************************************************
			148. Configurando PersistentVolume para MySQL***********************************************
				Un PersistentVolume (PV) en Kubernetes es un recurso que representa una porción de 
				almacenamiento físico en un clúster de Kubernetes. Los PersistentVolumes se utilizan 
				para proporcionar almacenamiento persistente para los pods en el clúster. Permiten 
				separar el almacenamiento de los pods, lo que facilita la gestión y la persistencia 
				de los datos incluso cuando los pods se eliminan o recrean.	

				En resumen, los PersistentVolumes en Kubernetes brindan una manera conveniente y 
				controlada de proporcionar almacenamiento persistente a los pods. Permiten que los 
				datos persistan incluso cuando los pods se recrean y brindan flexibilidad en cuanto 
				a tipos de almacenamiento y modos de acceso.

				* En la raiz del proyecto se crea un manifiesto para el PV --> mysql-pv.yaml 
					apiVersion: v1
					kind: PersistentVolume
					metadata:
					  name: mysql-pv
					spec:
					  capacity:
					    storage: 2Gi
					  volumeMode: Filesystem
					  storageClassName: standard
					  accessModes:
					    - ReadWriteOnce
					  hostPath:
					    path: /var/lib/mysql
					    type: DirectoryOrCreate

					* apiVersion: La versión de la API Kubernetes con la que se creó este recurso.
					* kind: El tipo de recurso, en este caso, es un PersistentVolume.
					* metadata: Información sobre el PersistentVolume, como el nombre.
					* spec: Las especificaciones del PersistentVolume, que incluyen la 
					capacidad de almacenamiento (capacity), el modo de volumen (volumeMode), 
					la clase de almacenamiento (storageClassName), el modo de acceso (accessMode), 
					y la configuración del almacenamiento subyacente (hostPath).

					En este caso, el PersistentVolume se configura de la siguiente manera:

					* capacity: Indica la capacidad del volumen, en este caso, 2 gigabytes.
					* volumeMode: Define el modo del volumen, en este caso, se utiliza "Filesystem" 
					para un sistema de archivos.
					* storageClassName: Define la clase de almacenamiento a la que pertenece el 
					volumen, en este caso, "standard".
					* accessMode: Define el modo de acceso al volumen, en este caso, "ReadWriteOnce", 
					lo que significa que solo un pod a la vez puede acceder al volumen en modo de 
					lectura y escritura.
					* hostPath: Define la ubicación del almacenamiento en el nodo del clúster, en 
					este caso, se usa /var/lib/mysql como ruta y se crea un directorio si no 
					existe (DirectoryOrCreate).
			*******************************************************************************************
			149. Configurando PersistentVolumeClaim para MySQL*****************************************
				Un PersistentVolumeClaim (PVC) en Kubernetes es un recurso que solicita un almacenamiento 
				persistente con ciertas características. Los PVCs son utilizados por los pods para 
				obtener acceso a almacenamiento persistente sin tener que preocuparse por los detalles 
				subyacentes del almacenamiento físico.

				* Se crea persistenVolumeClaim --> msql-pvc.yaml 
					apiVersion: v1
					kind: PersistenVolumeClaim
					metadata:
					  name: mysql-pvc
					spec:
					  volumeName: mql-pv
					  storageClassName: standard
					  accessMode:
					    - ReadWriteOnce
					  resources:
					    requests:
					      storage: 2Gi

					* apiVersion: La versión de la API Kubernetes con la que se creó este recurso.
					* kind: El tipo de recurso, en este caso, es un PersistentVolumeClaim.
					* metadata: Información sobre el PersistentVolumeClaim, como el nombre.
					* spec: Las especificaciones del PersistentVolumeClaim, que incluyen el nombre 
					del PersistentVolume (volumeName), la clase de almacenamiento (storageClassName), 
					el modo de acceso (accessMode), la capacidad de almacenamiento solicitada (resources).
					
					En este caso, el PersistentVolumeClaim se configura de la siguiente manera:

					* volumeName: Especifica el nombre del PersistentVolume con el que deseas asociar 
					este reclamo. En este caso, el reclamo se asocia con el PersistentVolume llamado 
					mql-pv.
					*storageClassName: Define la clase de almacenamiento a la que pertenece el reclamo, 
					en este caso, "standard".
					* accessMode: Define el modo de acceso al volumen, en este caso, "ReadWriteOnce", 
					lo que significa que solo un pod a la vez puede acceder al volumen en modo de 
					lectura y escritura.
					* resources: Define los recursos solicitados para el reclamo, en este caso, 2 gigabytes 
					de almacenamiento.

				* Se configura el pvc en el deployment de mysql 

					apiVersion: apps/v1
					kind: Deployment
					metadata:
					  name: mysql8
					spec:
					  replicas: 1
					  selector:
					    matchLabels:
					      app: mysql8
					  template:
					    metadata:
					      labels:
					        app: mysql8
					    spec:
					      containers:
					        - image: mysql:8
					          name: mysql8
					          ports:
					            - containerPort: 3306
					          env:
					            - name: MYSQL_ROOT_PASSWORD
					              value: Danger120-
					            - name: MYSQL_DATABASE
					              value: db_springboot_backend
					          volumeMounts:
					            - name: data-mysql
					              mountPath: /var/lib/mysql
					      volumes:
					        - name: data-mysql
					          persistentVolumeClaim:
					            claimName: mysql-pvc
				* Se crea el PersistenVolume 
					kubectl apply -f .\mysql-pv.yaml

					* Se valida que se creo   
						kubectl get pv 	

						NAME       CAPACITY   ACCESS MODES   RECLAIM POLICY   STATUS   CLAIM               STORAGECLASS   REASON   AGE
						mysql-pv   2Gi        RWO            Retain           Bound    default/mysql-pvc   standard                9m2s

				* Se crea el PersistenVolumeClaim 
					kubectl apply -f .\mysql-pvc.yaml

					* Se valida que se creo   
						kubectl get pvc

							NAME        STATUS   VOLUME     CAPACITY   ACCESS MODES   STORAGECLASS   AGE
							mysql-pvc   Bound    mysql-pv   2Gi        RWO            standard       5s

				* Se aplica el deployment de mysql 
					kubectl apply -f .\deployment-mysql.yaml

				* Se valida por postman  
					* Se valida url de servicio 
						 minikube service kiosco-app-spring --url 
						 	http://172.19.82.171:30751
					* Se prueban los servicios  

					Nota: no se deberian perder los datos de la base
			*******************************************************************************************
		***********************************************************************************************
		Seccion 15: Kubernetes: configMap y secret - Configuracion Centralizada************************
			151. Agregando diccionario ConfigMap*******************************************************
				* Se crea archivo configMap en la raiz del proyecto con los variables de entorno  
					apiVersion: v1
					kind: ConfigMap
					metadata:
					  name: kiosco-app-spring
					data:
					  port: "8080"
					  db_host: "mysql8:3306"
					  db_database: db_springboot_backend
					  db_user: alfonso
					  db_password: "danger120-"
					---
					apiVersion: v1
					kind: ConfigMap
					metadata:
					  name: mysql-config
					data:
					  mysql_root_password: Danger120-
					  mysql_database: db_springboot_backend
				* Se modifican las variables de entorno del deployment de kioscoApp para que apunten 
				a las variables de configMap  
					apiVersion: apps/v1
					kind: Deployment
					metadata:
					  name: kiosco-app-spring
					spec:
					  replicas: 2
					  selector:
					    matchLabels:
					      app: kiosco-app-spring
					  template:
					    metadata:
					      labels:
					        app: kiosco-app-spring
					    spec:
					      containers:
					      - image: dirielan3/kiosco_app_free:v1
					        name: kiosco-app-free-mg787
					        ports:
					          - containerPort: 8080
					        env:
					          - name: PORT
					            valueFrom:
					              configMapKeyRef:
					                name: kiosco-app-spring
					                key: port
					          - name: DB_HOST
					            valueFrom:
					              configMapKeyRef:
					                name: kiosco-app-spring
					                key: db_host
					          - name: DB_DATABASE
					            valueFrom:
					              configMapKeyRef:
					                name: kiosco-app-spring
					                key: db_database
					          - name: DB_USER
					            valueFrom:
					              configMapKeyRef:
					                name: kiosco-app-spring
					                key: db_user
					          - name: DB_PASSWORD
					            valueFrom:
					              configMapKeyRef:
					                name: kiosco-app-spring
					                key: db_password
				* Se modifican las variables de entorno del deployment de mysql para que apunten 
				a las variables de configMap  
					apiVersion: apps/v1
					kind: Deployment
					metadata:
					  name: mysql8
					spec:
					  replicas: 1
					  selector:
					    matchLabels:
					      app: mysql8
					  template:
					    metadata:
					      labels:
					        app: mysql8
					    spec:
					      containers:
					        - image: mysql:8
					          name: mysql8
					          ports:
					            - containerPort: 3306
					          env:
					            - name: MYSQL_ROOT_PASSWORD
					              valueFrom:
					                configMapKeyRef:
					                  name: mysql-config
					                  key: mysql_root_password
					            - name: MYSQL_DATABASE
					              valueFrom:
					                configMapKeyRef:
					                  name: mysql-config
					                  key: mysql_database
					          volumeMounts:
					            - name: data-mysql
					              mountPath: /var/lib/mysql
					      volumes:
					        - name: data-mysql
					          persistentVolumeClaim:
					            claimName: mysql-pvc
				* Se aplica el configMap 
					--> kubectl apply -f .\configMap.yaml
				* Se aplica deplyment de kioscoApp 
					--> kubectl apply -f .\deployment-kioscoapp.yam 
				* Se aplica deplyment de kioscoApp 
					--> kubectl apply -f .\deployment-mysql.yaml 
				* Se valida el configmap 
					--> kubectl get configmap  
				* Se valida que los pods suban correctamente   
					--> kubectl get pods 
				* Se validan los servicios por postman  
			*******************************************************************************************
			153. Agregando Secret**********************************************************************
				* Se crea file secret en la raiz del proyecto, incluyendo la data codificada en base 64
					Ref para codificar a base 64 --> https://www.base64encode.org/
					--> secret.yaml 
							apiVersion: v1
							kind: Secret
							metadata:
							  name: kiosco-app-spring
							type: Opaque
							data:
							  db_user: YWxmb25zbw==
							  db_password: ZGFuZ2VyMTIwLQ==
							---
							apiVersion: v1
							kind: Secret
							metadata:
							  name: mysql-config
							type: Opaque
							data:
							  mysql_root_password: RGFuZ2VyMTIwLQ==
				* Se condiguran los secrets en el deployment de kioscoApp  
					apiVersion: apps/v1
					kind: Deployment
					metadata:
					  name: kiosco-app-spring
					spec:
					  replicas: 2
					  selector:
					    matchLabels:
					      app: kiosco-app-spring
					  template:
					    metadata:
					      labels:
					        app: kiosco-app-spring
					    spec:
					      containers:
					      - image: dirielan3/kiosco_app_free:v1
					        name: kiosco-app-free-mg787
					        ports:
					          - containerPort: 8080
					        env:
					          - name: PORT
					            valueFrom:
					              configMapKeyRef:
					                name: kiosco-app-spring
					                key: port
					          - name: DB_HOST
					            valueFrom:
					              configMapKeyRef:
					                name: kiosco-app-spring
					                key: db_host
					          - name: DB_DATABASE
					            valueFrom:
					              configMapKeyRef:
					                name: kiosco-app-spring
					                key: db_database
					          - name: DB_USER
					            valueFrom:
					              secretKeyRef:
					                name: kiosco-app-spring
					                key: db_user
					          - name: DB_PASSWORD
					            valueFrom:
					              secretKeyRef:
					                name: kiosco-app-spring
					                key: db_password
				* Se condiguran los secrets en el deployment de mysql
					apiVersion: apps/v1
					kind: Deployment
					metadata:
					  name: mysql8
					spec:
					  replicas: 1
					  selector:
					    matchLabels:
					      app: mysql8
					  template:
					    metadata:
					      labels:
					        app: mysql8
					    spec:
					      containers:
					        - image: mysql:8
					          name: mysql8
					          ports:
					            - containerPort: 3306
					          env:
					            - name: MYSQL_ROOT_PASSWORD
					              valueFrom:
					                secretKeyRef:
					                  name: mysql-config
					                  key: mysql_root_password
					            - name: MYSQL_DATABASE
					              valueFrom:
					                configMapKeyRef:
					                  name: mysql-config
					                  key: mysql_database
					          volumeMounts:
					            - name: data-mysql
					              mountPath: /var/lib/mysql
					      volumes:
					        - name: data-mysql
					          persistentVolumeClaim:
					            claimName: mysql-pvc
				* Se validan los secrets 
					--> kubectl get secrets
				* Se validan los servicios en postman
			*******************************************************************************************
		***********************************************************************************************
		Seccion 17: kubernetes: Spring Cloud Kubernetes************************************************
			156. Configurando nuestros msvcs con Spring Cloud Kubernetes*******************************
				Nota: Para esta seccion se utiliza el recurso del curso facilitado al final de la seccion 16  
				* Se descarga el curso --> C:\Users\Elvis Areiza\Desktop\Elvis\DockeKubernetes\microservicios\curso-kubernetes
				* Se modifica el pom del microservicio usuarios y cursos, se agregan dependencias de spring cloud kubernetes  
					<dependency>
						<groupId>org.springframework.cloud</groupId>
						<artifactId>spring-cloud-starter-kubernetes-client</artifactId>
					</dependency>
					<dependency>
						<groupId>org.springframework.cloud</groupId>
						<artifactId>spring-cloud-starter-kubernetes-client-config</artifactId>
					</dependency>
					<dependency>
						<groupId>org.springframework.cloud</groupId>
						<artifactId>spring-cloud-starter-kubernetes-client-loadbalancer</artifactId>
					</dependency>
				* Se habilita DiscoveryClient en el proyecto de cursos y usuarios 
					--> package org.aguzman.springcloud.msvc.usuarios;  
					@EnableDiscoveryClient
					@EnableFeignClients
					@SpringBootApplication
					public class MsvcUsuariosApplication {

						public static void main(String[] args) {
							SpringApplication.run(MsvcUsuariosApplication.class, args);
						}

					}

					--> package org.aguzman.springcloud.msvc.cursos;
					@EnableDiscoveryClient
					@EnableFeignClients
					@SpringBootApplication
					public class MsvcCursosApplication {

						public static void main(String[] args) {
							SpringApplication.run(MsvcCursosApplication.class, args);
						}

					}

					Nota: La anotación @EnableDiscoveryClient es una anotación de Spring Cloud que se 
					utiliza para habilitar la integración con un servicio de descubrimiento, como Eureka, 
					Consul o Kubernetes Discovery, en una aplicación Spring Boot.

					Cuando agregas la anotación @EnableDiscoveryClient a una clase de configuración en 
					tu proyecto Spring Boot, estás indicando que deseas habilitar las capacidades de 
					descubrimiento de servicios proporcionadas por Spring Cloud. Esto permite que tu
					aplicación registre y descubra automáticamente otros servicios en el mismo entorno.

					En el contexto de Kubernetes, si estás utilizando Spring Cloud Kubernetes para 
					el descubrimiento de servicios en un clúster Kubernetes, la anotación @EnableDiscoveryClient 
					será utilizada para habilitar la integración con el descubrimiento de servicios en Kubernetes.
				* Se modifica el client de microservicio cursos, se elimina la url ya que se comunica mediante el nombre
					--> package org.aguzman.springcloud.msvc.usuarios.clients;
					@FeignClient(name="msvc-cursos")
					public interface CursoClienteRest {

					    @DeleteMapping("/eliminar-curso-usuario/{id}")
					    void eliminarCursoUsuarioPorId(@PathVariable Long id);
					} 
				* Se modifica el client de microservicio usuarios, se elimina la url ya que se comunica mediante el nombre
					--> package org.aguzman.springcloud.msvc.cursos.clients;
					@FeignClient(name="msvc-usuarios")
					public interface UsuarioClientRest {

					    @GetMapping("/{id}")
					    Usuario detalle(@PathVariable Long id);

					    @PostMapping("/")
					    Usuario crear(@RequestBody Usuario usuario);

					    @GetMapping("/usuarios-por-curso")
					    List<Usuario> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);
					}
				* Se modifican las properties en cada microservicio cursos y usuarios  
					* Se comenta url de microservicio usuario en el microservicio cursos  
						#msvc.usuarios.url=${USUARIOS_URL:msvc-usuarios:8001}
					* Se comenta url de microservicio cursos en el microservicio usuarios
						#msvc.cursos.url=${CURSOS_URL:msvc-cursos:8002}
					* Se agrega habilitacion de secrets en ambos microservicios
						spring.cloud.kubernates.secrets.enable-api=true

						Nota: Esta propiedad generalmente se utiliza en la configuración de una aplicación 
						Spring Boot para habilitar el acceso a los secretos almacenados en Kubernetes.

						Cuando se habilita esta propiedad, Spring Cloud Kubernetes intentará recuperar los 
						secretos de Kubernetes y los hará disponibles como propiedades en tu aplicación 
						Spring Boot. Esto permite que puedas acceder a los valores de los secretos desde 
						tu aplicación sin tener que preocuparte por gestionarlos manualmente.
					* Se activa discovery en todos los namespace  
						spring.cloud.kubernates.discovery.all-namespaces=true

						Nota: Cuando estableces spring.cloud.kubernetes.discovery.all-namespaces=true, 
						estás permitiendo que Spring Cloud Kubernetes detecte y registre automáticamente 
						los servicios en todos los namespaces del clúster. Esto puede ser útil en escenarios 
						donde tienes múltiples namespaces y deseas que tus servicios sean descubiertos en 
						todos ellos.
				* Se crea imagen de cada uno de los microservicio  
					--> docker build -t cursos . -f .\msvc-cursos\Dockerfile
					--> docker build -t usuarios . -f .\msvc-usuarios\Dockerfile
				* Se taguean para subir a docker hub 
					--> docker tag usuarios dirielan3/usuarios
					--> docker tag cursos dirielan3/cursos
				* Se realiza el push al reposito 
					--> docker push dirielan3/cursos
					--> docker push dirielan3/usuarios
				* Se realiza apply de configmap 
					--> kubectl apply -f .\configMap.yaml
				* Se realiza apply de secret  
					--> kubectl apply -f secret.yaml
				* Se realiza apply de de PersistentVolume  
					--> kubectl apply -f .\postgres-pv.yaml 
					--> kubectl apply -f .\mysql-pv.yaml
				* Se realiza el apply de PersistentVolumeClaim
					--> kubectl apply -f .\postgres-pvc.yaml 
					--> kubectl apply -f .\mysql-pvc.yaml
				* Se realiza el apply de el deployment de las base de datos 
					--> kubectl apply -f .\deployment-mysql.yaml
					--> kubectl apply -f .\deployment-postgres.yaml
				* Se realiza el apply de los servicios para las bds 
					--> kubectl apply -f .\svc-mysql.yaml
					--> kubectl apply -f .\svc-postgres.yaml
				* Se realiza el apply del deployment del las apps 
					--> kubectl apply -f .\deployment-cursos.yaml
					--> kubectl apply -f .\deployment-usuarios.yaml
				* Se realiza el apply de los servicios del deployment del las apps 
					--> kubectl apply -f .\svc-cursos.yaml
					--> kubectl apply -f .\svc-usuarios.yaml
			*******************************************************************************************
			157. Aplicando cambios en K8s y probando en Postman****************************************
				* Se crear un enlace de roles de clúster (ClusterRoleBinding) en Kubernetes. 

				--> kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default

					Este enlace de roles concede el rol de cluster-admin al servicio de cuenta 
					predeterminado (default:default), lo que otorga permisos de administrador en todo 
					el clúster. Es importante tener en cuenta la seguridad y las implicaciones de conceder 
					un rol tan elevado.

					La instrucción se desglosa de la siguiente manera:

						kubectl create clusterrolebinding: Comando para crear un enlace de roles de clúster.
						admin: Nombre del enlace que estás creando (puedes usar otro nombre si lo deseas).
						--clusterrole=cluster-admin: Especifica el nombre del rol de clúster que estás 
							asignando, en este caso, cluster-admin. Este rol tiene permisos elevados 
							en todo el clúster.
						--serviceaccount=default:default: Especifica la cuenta de servicio a la que se 
							asignará el rol. En este caso, se está utilizando la cuenta de servicio 
							predeterminada en el namespace predeterminado (default:default).
					
					Ten en cuenta que otorgar el rol cluster-admin a una cuenta de servicio es un 
					poderoso privilegio que debe usarse con precaución. Asegúrate de comprender 
					completamente las implicaciones de seguridad antes de aplicar esta configuración 
					en un entorno de producción

				* Se revisan los logs para validar que suban bien los servicios 
					--> kubectl logs msvc-usuarios-5d677bb954-6gpvd
					--> kubectl logs msvc-cursos-fc878c94c-dhstp
			*******************************************************************************************
			158. Ejemplo visualizando LoadBalancer con metadata de los pods****************************
				* Se modifica deployment-usuarios.yaml, se agregan variables para capturar nombre del pod e ip 
			        env:
			          - name: MY_POD_NAME
			            valueFrom:
			              fieldRef:
			                fieldPath: metadata.name
			          - name: MY_POD_IP
			            valueFrom:
			              fieldRef:
			                fieldPath: status.podIP
			    * Se modifica UsuarioController, para enviar las variables creadas en el response  
			    	* Se agrega inyeccion de dependencia para obtener los enviroments  
			    		@Autowired
    					private Environment env;
    				* Se agrega modificacion en el metodo del controller 
    					@GetMapping
					    public ResponseEntity<Map<String, Object>> listar() {
					        Map<String, Object> body = new HashMap<>();
					        body.put("users", service.listar());
					        body.put("podInfo", env.getProperty("MY_POD_NAME") + ": " + env.getProperty("MY_POD_IP"));
					//        return Collections.singletonMap("users", service.listar());
					        return ResponseEntity.ok(body);
					    }
			*******************************************************************************************
			159. Configuraciones de Spring Boot en ConfigMap*******************************************
				* Se configura el configmap del micreservicio usuarios  
					apiVersion: v1
					kind: ConfigMap
					metadata:
					  name: msvc-usuarios
					data:
					  port: "8001"
					  db_host: "mysql8:3306"
					  database: msvc_usuarios
					#  username: root
					#  password: sasa
					  curso_url: "msvc-cursos:8002"
					  /*cambios */
					  application.yaml: |- 
					    config:
					      texto: Configurando ambiente por defecto
				* Se modifica el UsuarioContrller  
					@GetMapping
				    public ResponseEntity<Map<String, Object>> listar() {
				        Map<String, Object> body = new HashMap<>();
				        body.put("users", service.listar());
				        body.put("podInfo", env.getProperty("MY_POD_NAME") + ": " + env.getProperty("MY_POD_IP"));
				        body.put("podInfoProperties", env.getProperty("config.texto"));
				//        return Collections.singletonMap("users", service.listar());
				        return ResponseEntity.ok(body);
				    }
			*******************************************************************************************
			160. Configuraciones de entornos dev y prod************************************************ ***
				* Se modifica el congmap, se configuran variables de ambiente de microservicio usuarios 
					apiVersion: v1
					kind: ConfigMap
					metadata:
					  name: msvc-usuarios
					data:
					  port: "8001"
					  db_host: "mysql8:3306"
					  database: msvc_usuarios
					#  username: root
					#  password: sasa
					  curso_url: "msvc-cursos:8002"
					  application.yaml: |- 
					    config:
					      texto: Configurando ambiente por defecto
					    ---
					    spring:
					      config:
					        active: 
					          on-profile: dev
					    config:
					      texto: Configuracion ambiente de desarrollo
					    ---
					    spring:
					      config:
					        active: 
					          on-profile: prod
					    config:
					      texto: Configuracion ambiente de produccion
				* Se modifica el application.properties, se agrega propertie para indicar el ambiente 
					--> spring.profiles.active=dev
				* Se construye la imagen ya que modificamos el proyecto
					--> docker build -t usuarios . -f .\msvc-usuarios\Dockerfile
					--> docker tag usuarios dirielan3/usuarios:latest
					--> docker push dirielan3/usuarios:latest 
				* Se realiza apply del configmap que cambiamos 
					--> kubectl apply -f .\configmap.yaml  
				* Se realiza apply del deployment  
					--> kubectl delete -f .\deployment-usuarios.yaml
					--> kubectl apply -f .\deployment-usuarios.yaml	
				* Se prueba por postman el servicio de usuarios, el response deberia reflejar que esta en desaarrollo 
					{
					    "podInfo": "msvc-usuarios-7c44487f98-rdc6c: 10.244.0.17",
					    "podInfoProperties": "Configuracion ambiente de desarrollo",
					    "users": [
					        {
					            "id": 1,
					            "nombre": "elvis",
					            "email": "elvis@gmail.com",
					            "password": "123456"
					        },
					        {
					            "id": 2,
					            "nombre": "diego",
					            "email": "diego@gmail.com",
					            "password": "123456"
					        }
					    ]
					}
				* Se hacen las modificaciones para que tome el ambiente del configmap  
					* Se modifica el application.properties  
						--> spring.profiles.active=${ENVIRONMENT:dev}
					* Se modifica el deployment-usuarios.yaml, se agrega variable del envirnment 
						--> - name: ENVIRONMENT
					            valueFrom:
					              configMapKeyRef:
					                name: msvc-usuarios
					                key: environment
					* Se modifica de confifmap, se agrega variable   
						-->   environment: prod 
					* Se construye la imagen ya que modificamos el proyecto
						--> docker build -t usuarios . -f .\msvc-usuarios\Dockerfile
						--> docker tag usuarios dirielan3/usuarios:latest
						--> docker push dirielan3/usuarios:latest 
					* Se realiza apply del configmap que cambiamos 
						--> kubectl apply -f .\configmap.yaml  
					* Se realiza apply del deployment  
						--> kubectl delete -f .\deployment-usuarios.yaml
						--> kubectl apply -f .\deployment-usuarios.yaml	
					* Se prueba en postam, se debe poder cambiar la variable en el configmap, delete y 
					apply del deplyment y ver resflejado en el response 
			*******************************************************************************************
			161. Configurando Liveness y Readiness en microservicios con Spring Boot Actuator**********
				Liveness Probe verifica si tu aplicación o contenedor está en un estado saludable y 
				responde correctamente a las solicitudes. Si el Liveness Probe detecta que el contenedor 
				no responde correctamente o está en un estado de falla, Kubernetes puede tomar medidas, 
				como reiniciar el contenedor o el pod, para intentar restaurar el servicio a un estado 
				funcional.

				Existen tres tipos principales de sondas de estado en Kubernetes:

					Liveness Probe: Se utiliza para determinar si el contenedor está funcionando 
					correctamente. Si la sonda de estado falla, Kubernetes reinicia automáticamente 
					el contenedor.

					Readiness Probe: Determina si el contenedor está listo para recibir tráfico de 
					red. Si la sonda de estado de preparación falla, el pod se excluye del equilibrador 
					de carga y no se le enviarán nuevas solicitudes hasta que se recupere.

					Startup Probe: Introducida en versiones más recientes de Kubernetes, evalúa si el 
					contenedor se ha inicializado correctamente. Es útil para aplicaciones que pueden 
					necesitar tiempo adicional para iniciar y ponerse en funcionamiento.

				Las sondas de estado se definen en la configuración del pod de Kubernetes y pueden 
				verificar una variedad de aspectos, como puertos abiertos, rutas de URL, conexiones a 
				bases de datos u otros criterios específicos de tu aplicación. Configurar sondas 
				de estado adecuadas es importante para garantizar que tu aplicación sea confiable y 
				responda de manera efectiva a situaciones de fallo.


				* Se agrega dependencia de actuator en microservicio usuarios
					<dependency>
						<groupId>org.springframework.boot</groupId>
						<artifactId>spring-boot-starter-actuator</artifactId>
					</dependency>

					Nota: Spring Boot Actuator es un módulo de Spring Boot que proporciona diversas 
					características para ayudar a gestionar y supervisar aplicaciones Spring Boot de 
					manera más efectiva. Actuator agrega endpoints (puntos finales) a tu aplicación 
					que te permiten obtener información sobre el estado y el comportamiento de la 
					aplicación en tiempo de ejecución.

					Algunas de las características más comunes de Spring Boot Actuator incluyen:

						Health Check: Proporciona un punto final /actuator/health que muestra el 
						estado de salud de la aplicación. Puede ser útil para verificar si la aplicación 
						está funcionando correctamente o si hay algún problema.

						Info: Ofrece información personalizada sobre la aplicación, como el nombre, 
						la descripción y la versión. Este punto final se encuentra en /actuator/info.

						Metrics: Recopila y expone métricas sobre la aplicación, como la utilización de 
						la CPU, el uso de memoria y otras métricas personalizadas. Los puntos finales 
						de métricas se encuentran en /actuator/metrics.

						Environment: Muestra información sobre las propiedades de configuración de la 
						aplicación y su entorno. Puedes acceder a este punto final en /actuator/env.

						Loggers: Permite administrar y ajustar la configuración de registro de la aplicación 
						en tiempo de ejecución. El punto final para los registros se encuentra en /actuator/loggers.

						Shutdown: Proporciona un punto final para apagar la aplicación de manera segura. Esto 
						puede ser útil para detener la aplicación desde un entorno de administración.

						Auditing: Permite realizar un seguimiento de las acciones realizadas en la aplicación, 
						como crear, actualizar y eliminar registros.

						Custom Endpoints: Actuator permite agregar puntos finales personalizados para exponer 
						información o acciones específicas de la aplicación.

					Puedes habilitar las características de Spring Boot Actuator agregando la dependencia adecuada 
					a tu proyecto y configurando las propiedades en el archivo application.properties o application.yml.

					Spring Boot Actuator es una herramienta poderosa para supervisar y administrar aplicaciones en 
					producción, ya que te brinda información esencial sobre el estado y el rendimiento de tu aplicación 
					sin requerir una implementación adicional.

				* Se habilitan caracteristicas de actuator en el application.properties de ms usuarios
					management.endpoints.web.exposure.include=*
					management.endpoint.health.show-details=always
					management.endpoint.health.probes.enable=true
					management.health.livenesstate.enable=true
					management.health.readinessstate.enable=true

					* management.endpoints.web.exposure.include=*: Esta configuración expone todos los puntos 
					finales disponibles en Actuator a través de una solicitud web. Esto permite acceder a todos 
					los puntos finales, como /actuator/health, /actuator/metrics, etc.

					* management.endpoint.health.show-details=always: Esto establece que al acceder al punto 
					final /actuator/health, se deben mostrar detalles completos en la respuesta, incluyendo 
					información sobre los componentes de salud individuales.

					* management.endpoint.health.probes.enable=true: Habilita los puntos finales de sondas de estado 
					(probes) de salud. Las sondas de estado son endpoints adicionales que pueden verificar aspectos 
					específicos del estado de la aplicación, como la conexión a una base de datos.

					* management.health.livenessstate.enable=true: Habilita el punto final /actuator/health/liveness, 
					que verifica el estado de "vivacidad" de la aplicación. Es decir, si la aplicación está en un 
					estado funcional y puede responder a las solicitudes.

					* management.health.readinessstate.enable=true: Habilita el punto final /actuator/health/readiness, 
					que verifica si la aplicación está lista para recibir tráfico. Si la aplicación aún no ha terminado 
					de inicializarse, el estado de "preparación" podría ser "DOWN".

					Estas configuraciones te permitirán acceder y gestionar los puntos finales de supervisión y 
					administración proporcionados por Spring Boot Actuator en tu aplicación Spring Boot. Recuerda que 
					estas configuraciones pueden tener impacto en la seguridad y la exposición de información en tu 
					aplicación, por lo que es importante configurarlas adecuadamente según tus necesidades y requisitos.
				* Se modifica deployment para configurar las sondas de estado para el contenedor
						apiVersion: apps/v1
						kind: Deployment
						metadata:
						  name: msvc-usuarios
						spec:
						  replicas: 2
						  selector:
						    matchLabels:
						      app: msvc-usuarios
						  template:
						    metadata:
						      labels:
						        app: msvc-usuarios
						    spec:
						      containers:
						      - image: dirielan3/usuarios:latest
						        name: usuarios
						        ports:
						        - containerPort: 8001
						        env:
						          - name: MY_POD_NAME
						            valueFrom:
						              fieldRef:
						                fieldPath: metadata.name
						          - name: MY_POD_IP
						            valueFrom:
						              fieldRef:
						                fieldPath: status.podIP
						          - name: PORT
						            valueFrom:
						              configMapKeyRef:
						                name: msvc-usuarios
						                key: port
						          - name: DB_HOST
						            valueFrom:
						              configMapKeyRef:
						                name: msvc-usuarios
						                key: db_host
						          - name: DB_DATABASE
						            valueFrom:
						              configMapKeyRef:
						                name: msvc-usuarios
						                key: database
						          - name: DB_USERNAME
						            valueFrom:
						              secretKeyRef:
						                name: msvc-usuarios
						                key: username
						          - name: DB_PASSWORD
						            valueFrom:
						              secretKeyRef:
						                name: msvc-usuarios
						                key: password
						          - name: CURSOS_URL
						            valueFrom:
						              configMapKeyRef:
						                name: msvc-usuarios
						                key: curso_url
						          - name: ENVIRONMENT
						            valueFrom:
						              configMapKeyRef:
						                name: msvc-usuarios
						                key: environment
						        /*modificacion */
						        readinessProbe:
						          httpGet:
						            path: /actuator/health/readiness
						            port: 8001
						            scheme: HTTP
						          initialDelaySeconds: 5
						          periodSeconds: 20
						          timeoutSeconds: 10
						        livenessProbe:
						          httpGet:
						            path: /actuator/health/liveness
						            port: 8001
						            scheme: HTTP
						          initialDelaySeconds: 5
						          periodSeconds: 30
						          timeoutSeconds: 10

					* Rexplanation 
						readinessProbe: Define la sonda de estado de preparación. Esta sonda verifica si 
						el contenedor está listo para recibir tráfico.

							httpGet: Indica que se realizará una solicitud HTTP GET para verificar el estado. 
							Se especifica la ruta (path) a la que se realizará la solicitud, el puerto (port) 
							en el que se espera la aplicación y el esquema (scheme) utilizado (en este caso, HTTP).

							initialDelaySeconds: Especifica el tiempo en segundos después de que el contenedor se 
							haya iniciado antes de que se ejecute la primera sonda de estado.

							periodSeconds: Define el intervalo en segundos entre ejecuciones sucesivas de la sonda de estado.

							timeoutSeconds: Define el tiempo en segundos que espera la sonda de estado para recibir una respuesta antes de considerarla fallida.

						livenessProbe: Define la sonda de estado de vivacidad. Esta sonda verifica si el contenedor 
						está funcionando correctamente.

							httpGet: Similar a la configuración de readinessProbe, esta configuración especifica la 
							ruta, el puerto y el esquema para la sonda de vivacidad.

							initialDelaySeconds: Tiempo en segundos después de que el contenedor se haya iniciado 
							antes de que se ejecute la primera sonda de vivacidad.

							periodSeconds: Intervalo en segundos entre ejecuciones sucesivas de la sonda de vivacidad.

							timeoutSeconds: Tiempo en segundos que espera la sonda de vivacidad para recibir una respuesta antes de considerarla fallida.

						Estas sondas de estado son útiles para Kubernetes, ya que permiten que Kubernetes monitoree y 
						gestione automáticamente los contenedores en función de su estado de preparación y vivacidad. 
						Si las sondas de estado fallan, Kubernetes puede tomar medidas como reiniciar el contenedor 
						para intentar recuperar el servicio a un estado funcional.






			*******************************************************************************************
		***********************************************************************************************
		Seccion 18: kubernetes: Spring Cloud Gateway***************************************************
			166. Creando y configurando servicio Spring Cloud Gateway**********************************
				* Se ingresa al inizialicer y se crea proyecto de gateway
					--> Maven  
					--> Java 17  
					--> Packing jar 
					Project Metadata
						Group
						org.aguzman.springcloud.msvc.gateway
						Artifact
						msvc-gateway
						Name
						msvc-gateway
						Description
						Demo project for Spring Boot
						Package name
						org.aguzman.springcloud.msvc.gateway.msvc-gateway
				* Se descarga y se copia en la raiz del proyecto junto a los microservicios
				* Se modifica pom.xml del parent, se agrega modulo de gateway
				    <modules>
				        <module>msvc-usuarios</module>
				        <module>msvc-cursos</module>
				        <module>msvc-gateway</module>
				    </modules>
				* Se modifica pom.xml del proyecto de gateway   
					* Se agrega parent  
						<parent>
							<groupId>org.aguzman.springcloud.msvc</groupId>
							<artifactId>curso-kubernetes</artifactId>
							<version>1.0-SNAPSHOT</version>
						</parent>  
					* Se modifica version de spring cloud  ala misma de los demas microservicios   
						<properties>
							<java.version>17</java.version>
							<spring-cloud.version>2021.0.1</spring-cloud.version>
						</properties>
					* Se agregan dependencias   
						<dependency>
							<groupId>org.springframework.cloud</groupId>
							<artifactId>spring-cloud-starter-kubernetes-client</artifactId>
						</dependency>
						<dependency>
							<groupId>org.springframework.cloud</groupId>
							<artifactId>spring-cloud-starter-kubernetes-client-loadbalancer</artifactId>
						</dependency>
				* Se modifica el application.properties  
					spring.application.name=msvc-gateway
					server.port=8090
				* Se modifica MsvcGatewayApplication, se habilita el DiscoveryClient 
					@EnableDiscoveryClient	
			*******************************************************************************************
			167. Configurando rutas de msvcs en Gateway y Dockerizando*********************************
				
			*******************************************************************************************
		***********************************************************************************************
	***************************************************************************************************

	Docker sentences***********************************************************************************
		* Creacion de imagen 
			--> docker build .  
		* Ver Imagenes 
			--> docker images 
		* Levantar imagen 
			--> docker run Id_image 
		* Lista de contenedores que se levantaron 
			--> docker ps 
		* detener contenedor 
			--> docker stop Id_container 
		* Lenata la imagen por un puerto especifico 
			--> docker run -p 8000:8001 Id_image 
		* Crea imagen con una tag 
			--> docker build -t usuarios . 
		* Lista contenedores  
			--> docker ps -a 
		* Etiquetar imagenes 
			--> docker tag, docker build -t   
		* Eliminar imagenes 
			--> docker rmi, docker images prune
			--> docker rmi 06199f47c0dc 47090b710122 e987ce964b7c
		* Analizar imagenes 
			--> docker inspect 
		* Nombrar contenedor 
			--> docker run -name 
		* Eliminar contenedor 
			--> docker rm, container prune 
		* Analizar contenedor
			--> docker container inspect 
		* Correr, detener, reiniciar 
			--> docker run/stop/start 
		* Correr contenedor en background
			--> docker run -d -p 8080:8080 kiosco
		* Ver logs 
			--> docker logs id_container 
		* Help 
			--> docker --help
		* Atachar contenedor 
			--> docker attach id_container 
		* Eliminar contenedor cuando se detiene
			--> docker run -p 8080:8081 -d --rm usuarios
		*Eliminar todos los contenedores detenidos
			--> docker container prune 
		* Ingreso modo interactivo del terminal 
			--> docker run -8080:8081 --rm -it usuarios /bin/sh 
		* Copiar un archivo en contenedor 
			--> docker cp .\login.java id_container:/app/login.java 
		* Copiar del contenedor a la maquina 
			--> docker cp Id_container:/app/login.java .\login2.java 
		* Inspeccionar imagen o contenedor 
			--> docker image inspect Id_image 
			--> docker container inspect Id_container 
		* Etiquetar Imagen 
			--> docker build -t usuarios:v1 . -f \ms-usuario\dockerFile 
		* Crear una red 
			--> docker network create spring 
		* levantar contenedor en una red 
			--> docker run -p 8080:8081 -d --rm --name ms-usuarios --network spring name_container 
		* Creacion de contenedor mysql 
			--> docker run -d -p 3307:3306 -name mysql8 --network spring -e mysql_root_password=sasa -e mysql_database=ms-usuarios <imagen> 
		* Creacion de contenedor postgre 
			--> docker run -d -p 5532:5432 -name postgre14 --network spring -e postgre_password=sasa -e postgres=ms-cursos postgres:14-alpine  
		* Ver volumenes de base de datos 
			--> docker volumen ls 
		* Para ejecutar docker compose 
			--> docker-compose up -d
		* Para bajar el docker compose y el volumen creado 
			--> docker-compose down -v
		* Para bajar el docker compose y el volumen creado 
			--> docker-compose down
		* Subir y forzar a que construya la imagen 
			--> docker-compose up --build -d
		* Solo que contruya la imagen 
			--> docker-compose --build
		* Parar docker compose 
			--> docker-comppose stop
		* Iniciar contenedores 
			--> docker start 
	***************************************************************************************************
	Kubernetes Sentences*******************************************************************************
		* Versión del cliente de kubectl que tienes instalada en tu máquina
			--> kubectl version --client
		* Se valida Version del minikube
			--> minikube version
		* Verificar el estado actual de tu clúster de Minikube
			--> minikube status 
		* Detener el clúster de Minikube que está en ejecución en la maquina
			--> minikube stop 
		* Iniciar un clúster de Minikube en la maquina
			--> minikube start 
		* Detiene y Eliminará completamente el clúster de Minikube y todos sus recursos
			--> minikube delete 
		* Inicia un clúster de Minikube utilizando el hipervisor Hyper-V como driver. 
			Hyper-V es una tecnología de virtualización desarrollada por Microsoft. 
			Al usar el driver Hyper-V, Minikube creará y gestionará las máquinas virtuales del clúster dentro de Hyper-V.
				--> minikube start --driver=hyperv
		* Se crea deployment con imagen de mysql8 y puerto 3306
			--> kubectl create deployment mysql8 --image=mysql:8 --port=3306
		* Se obtiene Informacion sobre los deployments que están en ejecución en tu clúster de Kubernetes
			--> kubectl get deployments
		* Se obtiene informacion sobre los pods que estan en ejecucion
			--> kubectl get pods 
		* Se describe el pod creado 
			--> kubectl describe pods mysql8-59977b5746-fwnd4(nombre del pod)
		* Se Obtiene los registros (logs) de un pod específico en Kubernetes
			--> kubectl logs mysql8-59977b5746-fwnd4  
		* Se crea un manifiesto de un despliegue de MySQL en Kubernetes
			--> kubectl create deployment mysql8 --image=mysql:8 --port=3306 --dry-run=client -o yaml > deployment-mysql.yaml
		* Se realiza apply del deployment  
			--> kubectl apply -f .\deployment-mysql.yaml
		* Se expone deployment como servicio
			--> kubectl expose deployment mysql8 --port=3306 --type=ClusterIP
		* Se obtiene url para acceder externamente   
			--> minikube service mysql8 --url
		* Se crea secret con las credenciales de dockerhub, ya que es privado el repositorio 
			--> kubectl create secret docker-registry kioscoapp --docker-server=https://index.docker.io 
			--docker-username=dirielan3 --docker-password=Danger120- --docker-email=dirielfran@gmail.com
		* Se valida secret creado 
			--> kubectl get secrets kioscoapp
		* Se elimina un deployment 
			--> kubectl delete deployment kiosco-app-spring
		* Se elimina el deployment de kioscoApp utilizando el manifiesto creado
			--> kubectl delete -f .\deployment-kioscoapp.yaml
		* Se despliega dashboard de kubernetes 
			--> minikube dashboard
		* Se valida el configmap 
			--> kubectl get configmap
		* Se validan los secrets 
			--> kubectl get secrets 
		* Se validan los namespace 
			--> kubectl get namespaces
	***************************************************************************************************



	* Argumentos 
		Se utiliza como variables solo en el dockerfile, pero no disponibles 
		en las instrucciones entrypoint ni CMD, tampoco en nuestro codigo o aplicacion

		* Son para tiempo de construccion de imagen

		* Se crea argumento en dockerfile 
			ARG MS_NAME = ms-usuario
		* Se utliza el  argumento   
			WORKDIR /app/$MS_NAME

	* Enviroment (Variables de ambiente)
		* Se utiliza detro del dockerfile y esta disponiible en el codigo  
		* Se conf. dentro del dockerfile con la instruccion env y via docker run con la bandera -e o --env 
		* Estan disponibles para la ejecucion  

		* Variables de ambiente 
			ENV PORT 8000
			EXPOSE $PORT 
		* Variables de ambiente al levantar container
			docker run - p 8001:8090 --env PORT=8090 -d --rm --name ms-usuarios --network spring usuarios
		* Variables de entorno desde un archivo
			* Se crea archivo .env en la raiz del proyecto ms-usuario
			* Se levanta el container con la variable env-file 
				docker run - p 8001:8090 --env-file .\ms-usuarios\.env -d --rm --name ms-usuarios --network spring usuarios


****************************************************************************************************************************************

Puesta en produccion de Lima************************************************************************************************************
	* Se pone a correr Docker   
	* Por FilleZilla se ingresa al servidor 
		linode2 --> root    --> Danger16225262
	    ip      -->139.144.56.222
	    * Se ingresa a la carpeta --> var/www/html/kiosco  
	    	* Se modifican los archivos del frontcd 

	  Despliegue del back 
	  * Se crea imagen de docker
	    docker build -t kiosco . -f .\KioscoAppSpring\Dockerfile
	  * Se crea el tag de la imagen
	    docker tag kiosco dirielan3/kiosco_app_spring
	  * Se realiza el push de la imagen
	    docker push dirielan3/kiosco_app_spring
	  * Se ejecuta docker-compose en maquina remota
	  	sudo docker-compose pull
	    sudo docker-compose up --build -d

    * Ver logs --> docker logs -f  KioscoAppSpring


   * Instalacion************************************************************************************************************************
		* Se actualizan los paquetes --> sudo apt update
		* Se instala docker -- > sudo apt install docker
		* Se levanta servicio de docker --> sudo service docker start 
		* Se valida version --> docker version 
		*  descarga el archivo ejecutable de Docker Compose para Linux (64 bits) y lo 
			guarda en la ruta "/usr/local/bin/docker-compose" o si estas en ubuntu en --> root
			sudo curl -SL https://github.com/docker/compose/releases/download/v2.20.2/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose
		* Se le dan permisos 
			sudo chmod +x /usr/local/bin/docker-compose
		* El comando crea un enlace simbólico (symlink) desde /usr/local/bin/docker-compose 
		hacia /usr/bin/docker-compose. Este enlace simbólico permite que el ejecutable de 
		Docker Compose sea accesible desde la ubicación /usr/bin/, lo que facilita su uso y 
		ejecución sin tener que escribir la ruta completa cada vez. 
			sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
		* Se valida que se instalo 
			docker-compose version
		* Desde ubunto nos colocamos donde se encuentra el docker-compose y se ejecuta  
			sudo cp docker-compose.yaml /root
		* Se ejecuta --> sudo docker login 
			dirielan3
			Danger120-

		* Se ejecuta  -- > sudo docker-compose up
		* Se crea conexion a la base de datos desde workbench 
		* Se le dan permisos a usuario alfonso  
			 CREATE USER 'alfonso'@'%' IDENTIFIED BY 'danger120-';
			GRANT ALL PRIVILEGES ON db_springboot_backend.* TO 'alfonso'@'%';
			FLUSH PRIVILEGES;

			SELECT User, Host FROM mysql.user;
			SHOW GRANTS FOR 'alfonso'@'%';
			use db_springboot_backend;
   *************************************************************************************************************************************
****************************************************************************************************************************************
