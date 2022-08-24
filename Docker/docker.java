
*********************************************************************Docker*************************************************************
	Referencia --> https://www.udemy.com/course/guia-completa-de-docker-kubernetes-con-spring-boot/learn/lecture/31916642#questions

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
	* Lista contenedores detenidos 
		docker ps -a 
	* Etiquetar imagenes 
		docker tag, docker build -t   
	* Eliminar imagenes 
		docker rmi, docker images prune 
	* Analizar imagenes 
		docker inspect 
	* Nombrar contenedor 
		docker run -name 
	* Eliminar contenedor 
		docker rm, cntainer prune 
	* Analizar contenedor
		docker container inspect 
	* Correr, detener, reiniciar 
		docker run/stop/start 
	* Ver logs 
		docker logs id_container 
	* Help 
		docker --help
	* Atachar contenedor 
		docker attach id_container 
	* Eliminar contenedor cuando se detiene
		docker run -p 8080:8081 -d --rm usuarios 
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