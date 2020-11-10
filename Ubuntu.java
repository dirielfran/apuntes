Ubuntu
*********************************************Ver version intalada de ubuntu
	*Ver version
		lbs_release -a
	*Mostrar el terminal
		ctrl+alt+t
	*Propiedades del sistema
		sudo lshw
	*ver el detalle del host
		htop
	*Comando sudo
		Sirve para ejecutar como administrador
		sudo su  --> suoerusuario
		sudo apt-get install/remove/autoclean/autoremove/update/upgrate

	*ver path en el panel
		ctr+l --> path

*********************************************************Instalacion Java 8
	Referencia --> https://comoinstalar.me/como-instalar-java-en-ubuntu-20-04-lts/

	sudo apt install -y openjdk-8-jre
	sudo apt install -y openjdk-8-jdk

	Establecer la versión por defecto de Java en Ubuntu 20.04
	sudo update-alternatives --config java

	La variable de entorno JAVA_HOME
	 sudo nano /etc/environment


	Añadiremos al final del archivo la variable JAVA_HOME y su valor,
	 JAVA_HOME="/usr/lib/jvm/default-java/jre"

	 Interpretando el archivo con el comando source:
	~$ source /etc/environment

	Podemos comprobar que la variable JAVA_HOME está disponible en el entorno actual:
	~$ echo $JAVA_HOME
	/usr/lib/jvm/default-java/jre

*********************************************************Instalacion de sql
	
	referencia --> https://www.youtube.com/watch?v=hx-nGKFVixA

******************************************************Configuracion de host
	 sudo gedit /etc/hosts
	 127.0.0.1 dotcmsl.intranet.osde

	 
	./startup.sh


********************************************************************entorno
	
	*desde el archivo docker-compose.yml levantar docker en /home/eareiza/dev/env/db --> sudo docker-compose up

	*instalacion docker
		id contenedor
		1a592e4cbfb2

		sudo docker exec -it 1a592e4cbfb2 mkdir /var/opt/mssql/backup
		sudo docker cp DBDOTCMS_5_1_6.bak 1a592e4cbfb2:/var/opt/mssql/backup

		sudo docker exec -it 1a592e4cbfb2 /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Passw0rd' -Q 'RESTORE DATABASE DBDOTCMS_5_1_6 FROM DISK = "/var/opt/mssql/backup/DBDOTCMS_5_1_6.bak" WITH MOVE "DBDOTCMS" TO "/var/opt/mssql/data/DBDOTCMS_5_1_6.mdf", MOVE "DBDOTCMS2_log" TO "/var/opt/mssql/data/DBDOTCMS_5_1_6_2_log.ldf", MOVE "DBDOTCMS_LOG" TO "/var/opt/mssql/data/DBDOTCMS_5_1_6_log.ldf"'

	*levantar proxy
		sudo simpleproxy -L 80 -R localhost:8080 -v

	*ruta del rsync
		/home/eareiza/dev/project/workspaceJava/OSDE/DotCmsCore
		sudo sh rsync.sh

	*deploy despues de un cambio
		1.-Para sincronizar el código fuente de los plugins contra lo que está 
			instalado en el environment de dotCMS, se deben seguir los siguientes pasos:
				Descargar y actualizar el siguiente archivos con el path del código 
				fuente de los plugins y el path de destino de los plugins (el que 
				está en el environment de dotCMS).
				Dar permisos de ejecución al script con el siguiente comando:
					> sudo chmod +x rsync.sh
				Ejecutar script con ./rsync.sh
		2.- Cuando se realiza un cambio en los plugins, se debe ejecutar 
			dotcms_5.2.8/bin/undeploy-plugins.sh y luego dotcms_5.2.8/bin/deploy-plugins.sh 
		
		3.- Verificar que en la carpeta lib de cada plugin exista el jar del que depende:
			osde.biblos.security → osde.biblos.commons.jar
			osde.biblos.workflow → osde.biblos.commons.jar y osde.biblos.security.jar
			osde.biblos.rest → osde.biblos.commons.jar, osde.biblos.security.jar y osde.biblos.workflow.jar
		4.- En caso de que no existan alguna dependencia (jar de otro plugin), 
			buscarla en la carpeta /build/jar de cada plugin y copiar el jar 
			según las dependencias mencionadas anteriormente. El jar de cada 
			plugin se genera luego de ejecutar deploy-plugins.sh, por lo cual 
			siempre es necesario primero la ejecución del mismo. 
		5.- realizar nuevamente un deploy en la carpeta bin

***************************************************************************

***********************************************************Versiones de Dot
	version 5 de dotcmsl
	http://dotcmslinux17.intranet.osde:8080/dotAdmin
	version 4 de dotcmsl
	http://biblos2test401.intranet.osde/dotAdmin

	***accesos
	https://test.intranet.osde/IV3/login.asp
	http://dotcmsl.intranet.osde:8080/dotAdmin/#/c/maintenance

	select * from workflow_comment wc order by creation_date desc;


	select * from workflow_history wh order by creation_date desc;

**************************************************************Usuario admin
	*Admin de dotcms
		admin@dotcms.com
		admin
	*vpn
		eareiza 
		Cordoba_2020
	*usuario de intranet: 
		SS32756870
 		intranet10
 	*Usuario Trello
		Usuario : @elvisareiza
		Cntraseña: Adventure262-
	*Correo 
		Usuario: eareiza@sms-sudamerica.com
		Clave: areiza_2020
	*Jira
		usuario: ss95969785
		pass: nZOYGm


**********************************Principales atajos de teclado para Ubuntu
	1) Ctrl+A = Seleccionar todo (En Documentos, Firefox, Nautilus, etc)

	2) Ctrl+C = Copiar (En Documentos, Firefox, Nautilus, etc)

	3) Ctrl+V = Pegar (En Documentos, Firefox, Nautilus)

	4) Ctrl+N = Nuevo (Crea un documento nuevo)

	5) Ctrl+O = Abrir (Abrir un documento)

	6) Ctrl+S = Guardar (Guardar el documento actual)

	7) Ctrl+P = Imprimir (Imprime el documento actual)

	8 ) Ctrl+E = Enviar a… (Envía el documento actual por email)

	9) Ctrl+W = Cerrar (Cierra el documento actual)

	10) Ctrl+Q = Cerrar ventana (Cierra la aplicación actual)

	10) Alt+Tab = Cambiar entre los programas abiertos.

	11 ) Alt+ F1 = Abrir menú de aplicaciones.

	12 ) Ctrl+Alt+tab = Navegar entre los programas abiertos.

	13 ) ImprPant = Capturar pantalla

	14) Ctrl+C = (usado en el terminal) Terminar proceso actual

	15) Ctrl + F10 = Menú contextual (botón derecho).

	16) Ctrl + Flecha derecha o izquierda = cambiar de escritorio

	17) Shift + Ctrl + Flecha derecha o izquierda = cambiar de escritorio moviendo la ventana actual.

	Este grupo de ocho atajos de teclado se pueden considera útiles en el escritorio.

	18) Ctrl+H = Mostrar/Ocultar archivos ocultos.

	19) Ctrl+D = Fin de sesión.

	20) F2 = Renombrar.

	21) Alt +F4 = Cerrar ventana.

	22) Ctrl + Alt + L = Bloquear pantalla.

	23) Alt + F2 = Abre menú de ejecución.

	24) Alt + F5 = Restaurar ventana maximizada.

	25) Ctrl+T= Abrir nueva pestaña.

	26) Click en la rueda del ratón = Pegar texto seleccionado.
***************************************************************************




