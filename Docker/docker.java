
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
					    image: kiosco:v5
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
				* Se instala docker -- > sudo yum install docker
				* Se levanta servicio de docker --> sudo service docker start 
				* Se valida version --> docker version 
				*  descarga el archivo ejecutable de Docker Compose para Linux (64 bits) y lo 
					guarda en la ruta "/usr/local/bin/docker-compose"
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
			134. Creando Deployment usuarios***********************************************************

			*******************************************************************************************
		***********************************************************************************************
	***************************************************************************************************
	* Creacion de imagen 
		docker build .  
	* Ver Imagenes 
		docker images 
	* Levantar imagen 
		docker run Id_image 
	* Lista de contenedores que se levantaron 
		docker ps 
	* detener contenedor 
		docker stop Id_container 
	* Lenata la imagen por un puerto especifico 
		docker run -p 8000:8001 Id_image 
	* Crea imagen con una tag 
		docker build -t usuarios . 
	* Lista contenedores  
		docker ps -a 
	* Etiquetar imagenes 
		docker tag, docker build -t   
	* Eliminar imagenes 
		docker rmi, docker images prune
		docker rmi 06199f47c0dc 47090b710122 e987ce964b7c
	* Analizar imagenes 
		docker inspect 
	* Nombrar contenedor 
		docker run -name 
	* Eliminar contenedor 
		docker rm, container prune 
	* Analizar contenedor
		docker container inspect 
	* Correr, detener, reiniciar 
		docker run/stop/start 
	* Correr contenedor en background
		docker run -d -p 8080:8080 kiosco
	* Ver logs 
		docker logs id_container 
	* Help 
		docker --help
	* Atachar contenedor 
		docker attach id_container 
	* Eliminar contenedor cuando se detiene
		docker run -p 8080:8081 -d --rm usuarios
	*Eliminar todos los contenedores detenidos
		docker container prune 
	* Ingreso modo interactivo del terminal 
		docker run -8080:8081 --rm -it usuarios /bin/sh 
	* Copiar un archivo en contenedor 
		docker cp .\login.java id_container:/app/login.java 
	* Copiar del contenedor a la maquina 
		docker cp Id_container:/app/login.java .\login2.java 
	* Inspeccionar imagen o contenedor 
		docker image inspect Id_image 
		docker container inspect Id_container 
	* Etiquetar Imagen 
		docker build -t usuarios:v1 . -f \ms-usuario\dockerFile 
	* Crear una red 
		docker network create spring 
	* levantar contenedor en una red 
		docker run -p 8080:8081 -d --rm --name ms-usuarios --network spring name_container 
	* Creacion de contenedor mysql 
		docker run -d -p 3307:3306 -name mysql8 --network spring -e mysql_root_password=sasa -e mysql_database=ms-usuarios <imagen> 
	* Creacion de contenedor postgre 
		docker run -d -p 5532:5432 -name postgre14 --network spring -e postgre_password=sasa -e postgres=ms-cursos postgres:14-alpine  
	* Ver volumenes de base de datos 
		docker volumen ls 
	* Para ejecutar docker compose 
		docker-compose up -d
	* Para bajar el docker compose y el volumen creado 
		docker-compose down -v
	* Para bajar el docker compose y el volumen creado 
		docker-compose down
	* Subir y forzar a que construya la imagen 
		docker-compose up --build -d
	* Solo que contruya la imagen 
		docker-compose --build
	* Parar docker compose 
		docker-comppose stop
	* Iniciar contenedores 
		docker start 


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