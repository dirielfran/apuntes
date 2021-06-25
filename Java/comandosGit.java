Definiciones**********************************************************************
	***Git tiene tres estados principales en los que se pueden encontrar tus archivos: 
		confirmado (committed), 
		modificado (modified), y 
		preparado (staged). 
		*Confirmado: significa que los datos están almacenados de manera segura en 
		tu base de datos local. 
		*Modificado: significa que has modificado el archivo pero todavía no lo has 
		confirmado a tu base de datos. 
		*Preparado: significa que has marcado un archivo modificado en su versión 
		actual para que vaya en tu próxima confirmación.

	***Esto nos lleva a las tres secciones principales de un proyecto de Git: 
		El directorio de Git (Git directory), 
		el directorio de trabajo (working directory), y 
		el área de preparación (staging area).

		*El directorio de Git es donde se almacenan los metadatos y la base 
		de datos de objetos para tu proyecto. Es la parte más importante de Git, 
		y es lo que se copia cuando clonas un repositorio desde otra computadora.

		*El directorio de trabajo es una copia de una versión del proyecto. Estos 
		archivos se sacan de la base de datos comprimida en el directorio de Git, 
		y se colocan en disco para que los puedas usar o modificar.

		*El área de preparación es un archivo, generalmente contenido en tu directorio 
		de Git, que almacena información acerca de lo que va a ir en tu próxima 
		confirmación. A veces se le denomina índice (“index”)

	***El flujo de trabajo básico en Git es algo así:

		*Modificas una serie de archivos en tu directorio de trabajo.

		*Preparas los archivos, añadiéndolos a tu área de preparación.

		*Confirmas los cambios, lo que toma los archivos tal y como están en el área 
		de preparación y almacena esa copia instantánea de manera permanente en tu 
		directorio de Git.

	**Guardando cambios en el Repositorio
		cada archivo de tu repositorio puede tener dos estados: 
			*rastreados y 
			*sin rastrear. 
			**Los archivos rastreados (tracked files en inglés) son todos aquellos 
			archivos que estaban en la última instantánea del proyecto; pueden ser 
			archivos sin modificar, modificados o preparados. 
			**Los archivos sin rastrear son todos los demás - cualquier otro archivo 
			en tu directorio de trabajo que no estaba en tu última instantánea y que 
			no está en el área de preparación (staging area). 

		Cuando clonas por primera vez un repositorio, todos tus archivos estarán 
		rastreados y sin modificar pues acabas de sacarlos y aun no han sido editados.
**********************************************************************************
**************************************************Configurando Git por primera vez
	Git trae una herramienta llamada git config, que te permite obtener y 
	establecer variables de configuración que controlan el aspecto y 
	funcionamiento de Git. 

	Estas variables pueden almacenarse en tres sitios distintos:

		*Archivo /etc/gitconfig: Contiene valores para todos los usuarios del 
		sistema y todos sus repositorios. Si pasas la opción --system a git 
		config, lee y escribe específicamente en este archivo.

		*Archivo ~/.gitconfig o ~/.config/git/config: Este archivo es específico 
		de tu usuario. Puedes hacer que Git lea y escriba específicamente en este 
		archivo pasando la opción --global.

		*Archivo config en el directorio de Git (es decir, .git/config) del repositorio 
		que estés utilizando actualmente: Este archivo es específico del repositorio actual.

	Cada nivel sobrescribe los valores del nivel anterior, por lo que los valores de 
	.git/config tienen preferencia sobre los de /etc/gitconfig.

	En sistemas Windows, Git busca el archivo .gitconfig en el directorio $HOME 
	(para mucha gente será (C:\Users\$USER). También busca el archivo /etc/gitconfig, 
	aunque esta ruta es relativa a la raíz MSys, que es donde decidiste instalar Git 
	en tu sistema Windows cuando ejecutaste el instalador.
**********************************************************************************

**********************************************************************Tu Identidad --> $ git config --global user.name
	$ git config --global user.name "John Doe"
	$ git config --global user.email johndoe@example.com

	De nuevo, sólo necesitas hacer esto una vez si especificas la opción --global, 
	ya que Git siempre usará esta información para todo lo que hagas en ese sistema. 
	Si quieres sobrescribir esta información con otro nombre o dirección de correo 
	para proyectos específicos, puedes ejecutar el comando sin la opción --global 
	cuando estés en ese proyecto. 
**********************************************************************************

******************************************************Comprobando tu Configuración --> git config
	git config --list

	También puedes comprobar el valor que Git utilizará para una clave específica 
	ejecutando git config <key>:
		Ej:
		$ git config user.name
		John Doe
**********************************************************************************

*********************************************************************obtener ayuda --> $ git help
	Si alguna vez necesitas ayuda usando Git, existen tres formas de ver la página 
	del manual (manpage) para cualquier comando de Git:

		$ git help <verb>
		$ git <verb> --help
		$ man git-<verb>

	Por ejemplo, puedes ver la página del manual para el comando config ejecutando

		$ git help config

	Estos comandos son muy útiles porque puedes acceder a ellos desde cualquier 
	sitio, incluso sin conexión. Si las páginas del manual y este libro no son 
	suficientes y necesitas que te ayude una persona, puedes probar en los canales 
	#git o #github del servidor de IRC Freenode (irc.freenode.net). Estos canales 
	están llenos de cientos de personas que conocen muy bien Git y suelen estar 
	dispuestos a ayudar.
**********************************************************************************

***************************Inicializando un repositorio en un directorio existente --> $ git init
	
	$ git init
**********************************************************************************

*************************************************Clonando un repositorio existente --> $ git clone
	Si deseas obtener una copia de un repositorio Git existente — por ejemplo, un 
	proyecto en el que te gustaría contribuir — el comando que necesitas es git 
	clone. Si estás familizarizado con otros sistemas de control de versiones 
	como Subversion, verás que el comando es "clone" en vez de "checkout".

	Puedes clonar un repositorio con git clone [url]. Por ejemplo, si quieres 
	clonar la librería de Git llamada libgit2 puedes hacer algo así:

		$ git clone https://github.com/libgit2/libgit2

	***Si quieres clonar el repositorio a un directorio con otro nombre que no 
	sea libgit2, puedes especificarlo con la siguiente opción de línea de comandos:	
**********************************************************************************

***********************************************Revisando el Estado de tus Archivos --> git status
	La herramienta principal para determinar qué archivos están en qué estado es 
	el comando:
		git status
**********************************************************************************

**********************************************************Rastrear Archivos Nuevos -->$ git add README
	
	$ git add README
**********************************************************************************

******************************************************************Estado Abreviado -->$ git status -s
	Si bien es cierto que la salida de git status es bastante explícita, también 
	es verdad que es muy extensa. Git ofrece una opción para obtener un estado 
	abreviado, de manera que puedas ver tus cambios de una forma más compacta. 
	Si ejecutas git status -s o git status --short, obtendrás una salida mucho 
	más simplificada.

		$ git status -s
		 M README
		MM Rakefile
		A  lib/git.rb
		M  lib/simplegit.rb
		?? LICENSE.txt
	
	Los archivos nuevos que no están rastreados tienen un ?? a su lado, los 
	archivos que están preparados tienen una A y los modificados una M. El estado 
	aparece en dos columnas - la columna de la izquierda indica el estado preparado 
	y la columna de la derecha indica el estado sin preparar. Por ejemplo, en esa 
	salida, el archivo README está modificado en el directorio de trabajo pero no 
	está preparado, mientras que lib/simplegit.rb está modificado y preparado. 
	El archivo Rakefile fue modificado, preparado y modificado otra vez por lo que 
	existen cambios preparados y sin preparar.
**********************************************************************************

******************************************************************Ignorar Archivos --> cat .gitignore
	A veces, tendrás algún tipo de archivo que no quieres que Git añada 
	automáticamente o más aun, que ni siquiera quieras que aparezca como 
	no rastreado. Este suele ser el caso de archivos generados automáticamente 
	como trazas o archivos creados por tu sistema de compilación. En estos casos, 
	puedes crear un archivo llamado .gitignore que liste patrones a considerar. 
	Este es un ejemplo de un archivo .gitignore:

		$ cat .gitignore
		*.[oa]
		*~

	Aquí puedes ver otro ejemplo de un archivo .gitignore:

		# ignora los archivos terminados en .a
		*.a

		# pero no lib.a, aun cuando había ignorado los archivos terminados en .a en la línea anterior
		!lib.a

		# ignora unicamente el archivo TODO de la raiz, no subdir/TODO
		/TODO

		# ignora todos los archivos del directorio build/
		build/

		# ignora doc/notes.txt, pero no este: doc/server/arch.txt
		doc/*.txt

		# ignora todos los archivos .txt del directorio doc/
		doc/**/*.txt
**********************************************************************************

****************************************************Ver los Cambios sin Preparados --> git diff
	Para ver qué has cambiado pero aun no has preparado, escribe git diff sin más 
	parámetros:
		git diff
	Este comando compara lo que tienes en tu directorio de trabajo con lo que está 
	en el área de preparación. El resultado te indica los cambios que has hecho 
	pero que aun no has preparado.

	Es importante resaltar que al llamar a git diff sin parámetros no verás los 
	cambios desde tu última confirmación - solo verás los cambios que aun no están 
	preparados. Esto puede ser confuso porque si preparas todos tus cambios, git 
	diff no te devolverá ninguna salida
**********************************************************************************

************************************************************Ver cambios preparados --> git diff --staged --cached
	Si quieres ver lo que has preparado y será incluido en la próxima confirmación, 
	puedes usar git diff --staged. Este comando compara tus cambios preparados con 
	la última instantánea confirmada.
**********************************************************************************

*************************************************************Confirmar tus Cambios --> git commit -m
	Ahora que tu área de preparación está como quieres, puedes confirmar tus 
	cambios. Recuerda que cualquier cosa que no esté preparada - cualquier archivo 
	que hayas creado o modificado y que no hayas agregado con git add desde su 
	edición - no será confirmado. Se mantendrán como archivos modificados en tu 
	disco. En este caso, digamos que la última vez que ejecutaste git status 
	verificaste que todo estaba preparado y que estás listo para confirmar tus 
	cambios. La forma más sencilla de confirmar es escribiendo git commit:

		$ git commit

	Recuerda que la confirmación guarda una instantánea de tu área de preparación. 
	Todo lo que no hayas preparado sigue allí modificado; puedes hacer una nueva 
	confirmación para añadirlo a tu historial. Cada vez que realizas un commit, 
	guardas una instantánea de tu proyecto la cual puedes usar para comparar o 
	volver a ella luego.
**********************************************************************************

*****************************************************Saltar el Área de Preparación --> git commit -a -m
	Git te ofrece un atajo sencillo. Añadiendo la opción -a al comando git commit 
	harás que Git prepare automáticamente todos los archivos rastreados antes de 
	confirmarlos, ahorrándote el paso de git add:
		git commit -a -m
**********************************************************************************

*****************************************************************Eliminar Archivos --> git rm
	Para eliminar archivos de Git, debes eliminarlos de tus archivos rastreados 
	(o mejor dicho, eliminarlos del área de preparación) y luego confirmar. Para 
	ello existe el comando git rm, que además elimina el archivo de tu directorio 
	de trabajo de manera que no aparezca la próxima vez como un archivo no rastreado.

	Si simplemente eliminas el archivo de tu directorio de trabajo, aparecerá en la 
	sección “Changes not staged for commit” (esto es, sin preparar) en la salida de 
	git status:

		$ git rm log/\*.log
	Este comando elimina todos los archivo que tengan la extensión .log dentro del 
	directorio log/. O también puedes hacer algo como:

		$ git rm \*~
	Este comando elimina todos los archivos que acaben con ~.
**********************************************************************************

************************************************Eliminarlo del área de preparación --> $ git rm --cached <nombreArchivo>
	mantener el archivo en tu directorio de trabajo pero eliminarlo del área de preparación. 
	En otras palabras, quisieras mantener el archivo en tu disco duro pero sin que Git 
	lo siga rastreando. Esto puede ser particularmente útil si olvidaste añadir algo en 
	tu archivo .gitignore y lo preparaste accidentalmente, algo como un gran archivo de 
	trazas a un montón de archivos compilados .a. 
	Para hacerlo, utiliza la opción --cached:
		$ git rm --cached README
**********************************************************************************

*************************************************Cambiar el Nombre de los Archivos --> $ git mv file_from file_to
	 Si quieres renombrar un archivo en Git, puedes ejecutar algo como

		$ git mv file_from file_to
**********************************************************************************

************************************************Ver el Historial de Confirmaciones --> git log
	Para ver qué modificaciones se han llevado a cabo. La herramienta más básica 
	y potente para hacer esto es el comando git log.
		git log

	Por defecto, si no pasas ningún parámetro, git log lista las confirmaciones 
	hechas sobre ese repositorio en orden cronológico inverso. Es decir, las 
	confirmaciones más recientes se muestran al principio. Como puedes ver, este 
	comando lista cada confirmación con su suma de comprobación SHA-1, el nombre 
	y dirección de correo del autor, la fecha y el mensaje de confirmación.


	Table 2. Opciones típicas de git log
	Opción				Descripción
	-p           	    Muestra el parche introducido en cada confirmación.
	--stat 				Muestra estadísticas sobre los archivos modificados en 
						cada confirmación.
	--shortstat 		Muestra solamente la línea de resumen de la opción --stat.
	--name-only 		Muestra la lista de archivos afectados.
	--name-status 		Muestra la lista de archivos afectados, indicando además 
						si fueron añadidos, modificados o eliminados.
	--abbrev-commit 	Muestra solamente los primeros caracteres de la suma SHA-1, 
						en vez de los 40 caracteres de que se compone.
	--relative-date 	Muestra la fecha en formato relativo (por ejemplo, “2 weeks 
						ago” (“hace 2 semanas”)) en lugar del formato completo.
	--graph             Muestra un gráfico ASCII con la historia de ramificaciones 
						y uniones.
	--pretty 			Muestra las confirmaciones usando un formato alternativo. 
						Posibles opciones son oneline, short, full, fuller y 
						format (mediante el cual puedes especificar tu propio formato).
*********************************************************************************

**************************************************historia n confirmaciones y dif --> git log -p -2
	Una de las opciones más útiles es -p, que muestra las diferencias introducidas 
	en cada confirmación. También puedes usar la opción -2, que hace que se muestren 
	únicamente las dos últimas entradas del historial:
		git log -p -2
*********************************************************************************

************************************************Estadisticas de cada confirmacion --> git log --stat
	ver algunas estadísticas de cada confirmación, puedes usar la opción --stat:
		git log --stat
	 la opción --stat imprime tras cada confirmación una lista de archivos modificados, 
	 indicando cuántos han sido modificados y cuántas líneas han sido añadidas y 
	 eliminadas para cada uno de ellos, y un resumen de toda esta información.
*********************************************************************************

************************************************Estadisticas de cada confirmacion --> git log --pretty=online/short/fuller
	Otra opción realmente útil es --pretty, que modifica el formato de la salida. 
	Tienes unos cuantos estilos disponibles. La opción oneline imprime cada confirmación 
	en una única línea, lo que puede resultar útil si estás analizando gran cantidad de 
	confirmaciones. Otras opciones son short, full y fuller, que muestran la salida en 
	un formato parecido, pero añadiendo menos o más información
*********************************************************************************

************************************************Estadisticas de cada confirmacion --> git log --pretty=format: "%h - %s"
	La opción más interesante es format, que te permite especificar tu propio formato. 
		$ git log --pretty=format:"%h - %an, %ar : %s"

	Opciones útiles de git log --pretty=format lista algunas de las opciones más útiles aceptadas por format.

		Table 1. Opciones útiles de git log --pretty=format
		Opción	Descripción de la salida
		%H  	-->	Hash de la confirmación
		%h      --> Hash de la confirmación abreviado
		%T 		--> Hash del árbol
		%t 		-->	Hash del árbol abreviado
		%P 		-->	Hashes de las confirmaciones padre
		%p 		--> Hashes de las confirmaciones padre abreviados
		%an 	-->	Nombre del autor
		%ae 	-->	Dirección de correo del autor
		%ad 	-->	Fecha de autoría (el formato respeta la opción -–date)
		%ar 	--> Fecha de autoría, relativa
		%cn 	--> Nombre del confirmador
		%ce 	--> Dirección de correo del confirmador
		%cd 	--> Fecha de confirmación
		%cr     --> Fecha de confirmación, relativa
		%s 		-->	Asunto
*********************************************************************************

*********************************************************Ramificaciones y Uniones --> git log --pretty=oneline --graph --> git log --pretty=format: "%h - %s" --graph
    Las opciones oneline y format son especialmente útiles combinadas con otra 
	opción llamada --graph. Ésta añade un pequeño gráfico ASCII mostrando tu 
	historial de ramificaciones y uniones:
		git log --pretty=format: "%h - %s" --graph
*********************************************************************************

**************************************************Limitar la Salida del Historial --> git log <opcion> 
	Table 3. Opciones para limitar la salida de git log
	Opción				Descripción
	-(n) 				Muestra solamente las últimas n confirmaciones
							Ej: git log -2
	--since, --after 	Muestra aquellas confirmaciones hechas después de la fecha 
						especificada.
						    Ej: git log --pretty=format:"%h %cn: %s" --since="2020-06-12" --before="2020-06-13"
	--until, --before 	Muestra aquellas confirmaciones hechas antes de la fecha 
						especificada.
							Ej: git log --pretty=format:"%h %cn: %s" --since="2020-06-12" --before="2020-06-13"
	--author			Muestra sólo aquellas confirmaciones cuyo autor coincide 
						con la cadena especificada.
							Ej: git log --pretty=format:"%h %cn: %s" --author=AlfonsoRA
	--committer 		Muestra sólo aquellas confirmaciones cuyo confirmador 
						coincide con la cadena especificada.
							Ej: git log --pretty=format:"%h %cn: %s" --committer=AlfonsoRA
	-S 					Muestra sólo aquellas confirmaciones que añaden o eliminen 
						código que corresponda con la cadena especificada.
							Ej: git log -Selvis	
*********************************************************************************

*******************************************************************Deshacer Cosas --> git commit -amend
	Uno de las acciones más comunes a deshacer es cuando confirmas un cambio 
	antes de tiempo y olvidas agregar algún archivo, o te equivocas en el mensaje 
	de confirmación. Si quieres rehacer la confirmación, puedes reconfirmar con la 
	opción --amend:
		git commit --amend
	Este comando utiliza tu área de preparación para la confirmación. Si no has 
	hecho cambios desde tu última confirmación (por ejemplo, ejecutas este comando 
	justo después de tu confirmación anterior), entonces la instantánea lucirá 
	exactamente igual y lo único que cambiarás será el mensaje de confirmación.

	** si confirmas y luego te das cuenta que olvidaste preparar los cambios de 
	un archivo que querías incluir en esta confirmación, puedes hacer lo siguiente:

		$ git commit -m 'initial commit'
		$ git add forgotten_file
		$ git commit --amend

	Al final terminarás con una sola confirmación - la segunda confirmación 
	reemplaza el resultado de la primera.
*********************************************************************************


****************************************************Deshacer un Archivo Preparado --> git restore --staged prueba1.txt
	Las siguientes dos secciones demuestran cómo lidiar con los cambios de tu área 
	de preparación y tú directorio de trabajo.
		
		git restore --staged prueba1.txt   /    git reset head pruebota.txt
*********************************************************************************

***************************************************Deshacer un Archivo Modificado -->  git checkout -- pruebota.txt
	puedes restaurarlo fácilmente - volver al estado en el que estaba en la última 
	confirmación (o cuando estaba recién clonado, o como sea que haya llegado a tu 
	directorio de trabajo)
		 git checkout -- pruebota.txt
*********************************************************************************

Remotos
*************************************************************Trabajar con Remotos
	Para poder colaborar en cualquier proyecto Git, necesitas saber cómo gestionar 
	repositorios remotos. Los repositorios remotos son versiones de tu proyecto 
	que están hospedadas en Internet o en cualquier otra red. Puedes tener varios 
	de ellos, y en cada uno tendrás generalmente permisos de solo lectura o de 
	lectura y escritura. Colaborar con otras personas implica gestionar estos 
	repositorios remotos enviando y trayendo datos de ellos cada vez que necesites 
	compartir tu trabajo. Gestionar repositorios remotos incluye saber cómo añadir 
	un repositorio remoto, eliminar los remotos que ya no son válidos, gestionar 
	varias ramas remotas, definir si deben rastrearse o no y más. En esta sección, 
	trataremos algunas de estas habilidades de gestión de remotos.
*********************************************************************************

******************************************************************Ver Tus Remotos --> git remote
	Para ver los remotos que tienes configurados, debes ejecutar el comando git 
	remote. Mostrará los nombres de cada uno de los remotos que tienes especificados. 
	Si has clonado tu repositorio, deberías ver al menos origin (origen, en inglés) - 
	este es el nombre que por defecto Git le da al servidor del que has clonado:
		git remote
	También puedes pasar la opción -v, la cual muestra las URLs que Git ha asociado 
	al nombre y que serán usadas al leer y escribir en ese remoto:

		$ git remote -v
		origin	https://github.com/schacon/ticgit (fetch)
		origin	https://github.com/schacon/ticgit (push)
*********************************************************************************

******************************************************Añadir Repositorios Remotos --> $ git remote add pb https://github.com/paulboone/ticgit
	En secciones anteriores hemos mencionado y dado alguna demostración de cómo 
	añadir repositorios remotos. Ahora veremos explícitamente cómo hacerlo. Para 
	añadir un remoto nuevo y asociarlo a un nombre que puedas referenciar fácilmente, 
	ejecuta git remote add [nombre] [url]:
		$ git remote add pb https://github.com/paulboone/ticgit
*********************************************************************************

*********************************************************Traer y Combinar Remotos --> $ git fetch [remote-name]
	El comando irá al proyecto remoto y se traerá todos los datos que aun no tienes 
	de dicho remoto. Luego de hacer esto, tendrás referencias a todas las ramas del 
	remoto, las cuales puedes combinar e inspeccionar cuando quieras.

	Si clonas un repositorio, el comando de clonar automáticamente añade ese repositorio 
	remoto con el nombre “origin”. Por lo tanto, git fetch origin se trae todo el 
	trabajo nuevo que ha sido enviado a ese servidor desde que lo clonaste (o desde 
	la última vez que trajiste datos). Es importante destacar que el comando git 
	fetch solo trae datos a tu repositorio local - ni lo combina automáticamente 
	con tu trabajo ni modifica el trabajo que llevas hecho. La combinación con tu 
	trabajo debes hacerla manualmente cuando estés listo.

	Si has configurado una rama para que rastree una rama remota (más información 
	en la siguiente sección y en Ramificaciones en Git), puedes usar el comando 
	git pull para traer y combinar automáticamente la rama remota con tu rama 
	actual. Es posible que este sea un flujo de trabajo mucho más cómodo y fácil 
	para ti; y por defecto, el comando git clone le indica automáticamente a tu 
	rama maestra local que rastree la rama maestra remota (o como se llame la rama 
	por defecto) del servidor del que has clonado. Generalmente, al ejecutar git 
	pull traerás datos del servidor del que clonaste originalmente y se intentará 
	combinar automáticamente la información con el código en el que estás trabajando.
*********************************************************************************

*************************************************************Enviar a Tus Remotos --> git push [nombre-remoto] [nombre-rama]
	Cuando tienes un proyecto que quieres compartir, debes enviarlo a un servidor. 
	El comando para hacerlo es simple: git push [nombre-remoto] [nombre-rama]. Si 
	quieres enviar tu rama master a tu servidor origin (recuerda, clonar un repositorio 
	establece esos nombres automáticamente), entonces puedes ejecutar el siguiente 
	comando y se enviarán todos los commits que hayas hecho al servidor:

		$ git push origin master
	Este comando solo funciona si clonaste de un servidor sobre el que tienes 
	permisos de escritura y si nadie más ha enviado datos por el medio. Si alguien 
	más clona el mismo repositorio que tú y envía información antes que tú, tu 
	envío será rechazado. Tendrás que traerte su trabajo y combinarlo con el tuyo 
	antes de que puedas enviar datos al servidor.
*********************************************************************************

***********************************************************Inspeccionar un Remoto --> git remote show [nombre-remoto]
	Si quieres ver más información acerca de un remoto en particular, puedes 
	ejecutar el comando git remote show [nombre-remoto]. 
*********************************************************************************

****************************************************************Renombrar Remotos --> $ git remote rename pb paul
	Al hacer esto también cambias el nombre de las ramas remotas. Por lo tanto, 
	lo que antes estaba referenciado como pb/master ahora lo está como paul/master.
*********************************************************************************

*****************************************************************Eliminar remotos --> git remote rm [nombre del remoto]
	Si por alguna razón quieres eliminar un remoto - has cambiado de servidor o 
	no quieres seguir utilizando un mirror o quizás un colaborador ha dejado de 
	trabajar en el proyecto - puedes usar git remote rm:
*********************************************************************************

******************************************************Setear otra url para remoto --> git remote set-url origin [url repo]

	git remote set-url origin https://github.com/USERNAME/REPOSITORY.git
*********************************************************************************






Trabajando con branch************************************************************
*********************************************************************Crear Branch --> git branch nuevafuncionalidad
	 git branch Producto

	 Existe un atajo para crear una rama que aún no existe usando directamente 
	 el comando checkout que consiste en pasar el parámetro -b en la llamada
	 --> git checkout -b facturas
*********************************************************************************

******************************************************************Cambiar de rama --> git checkout nuevafuncionalidad
*********************************************************************************

********************************************************************Mostrar Ramas --> git branch
	Una vez creada nuestra nueva rama de desarrollo, podemos comprobar en que 
	rama nos encontramos utilizando el comando git branch
*********************************************************************************

************************************************Diferencias entre una rama y otra --> git diff --stat master nuevafuncionalidad
	podemos ver las diferencias entre nuestras dos ramas usando el comando diff.
		--> git diff --stat master factura
*********************************************************************************

*****************************************************************Merge de branch --> git merge nuevafuncionalidad (parado desde master)
	Para realizar la mezcla de la rama nuevafuncionalidad dentro de master vamos 
	a situarnos en la rama mater y a mezclar ambas:
		$ git checkout master
		$ git merge nuevafuncionalidad
********************************************************************************

*******************************************************************Borrar branch --> git branch -d nuevafuncionalidad
	Una vez hemos mezclado nuestras ramas, y en caso de que no haya conflictos, 
	podemos eliminar la rama nuevafuncionalidad por que no vamos a necesitarla más.
		$ git branch -d nuevafuncionalidad
********************************************************************************

**************************************Configuracion para que solicite contraseña
	[credential]
    interactive = always
********************************************************************************

git push -u origin master
git add: agrega un ar
//configurar usuario
git config --global user.name elvis
git config --global user.email "elvis@gmail.com"
//para ver las configuraciones
git config --get-regexp user
git config --list  //configuracion de git
nano README.md  //crea un archivo con nano
en nano
ctrl + o  // guarda
ctrl + x // salir

//llevar seguimiento en git
git status // compara  si es igual al que esta en el index
git add README.md //agrega seguimiento al archivo


//commit --> se crea un apuntador
git commit  //comitea
			//se agrega comentario
			//scape , shift + :wr  -->escribe
			// shift + q  --> salir

git log  // te permite ver el log de commit

git add index/   //agrega todo lo que hay en el directorio

git commit -m "Se agrego la carpeta index" //realiza commit y pone un mensaje

--          // si se va a escribir un texto
-           // espera un caracter
regexp      //busca una palabra
q           //para salir




CLASE 2
$ git log -1      // un solo comit
git log --oneline // un solo commit
git log --since="2020-05-01"  // muestra los commit desde una fecha
git log --after="2020-04-01" --before="2020-06-01" entre fechas
git log --after="2020-04-01" --before="2020-06-01" --oneline

decorate // para que muestre en otro color las ramas
graph // en fotma de rama
git log --oneline --decorate --all --graph 

//cuando crea una rama crea un nuevo apuntador
head es un apuntador dinamico
git branch entrendiendo-ramas //crea rama
git branch  //muestra las ramas
git checkout entrendiendo-ramas //cambia de rama
rm -d prueba.txt
rm -rf prueba.txt

git checkout master //swit a master

git add . //se agregan los cambios
git commit -m "se agrego rama" //se realiza el commit
//para ver todas las ramas
git log --oneline --decorate --all --graph 


comando de ramas
git branch <nombre>           crea una rama
git switch <nombre>           cambia de rama
git checkout <nombre>         cambia de rama
git checkout -b <nombre>      crea la rama y la cambia hacia ella

comandos de branch
git branch                    lista las ramas locales
git branch -r                 lista las ramas remotas
git branch -a                 lista todas las ramas remotas / locales
           -d <nombre>       elimina una rama (si fue fussionada
           -D <nombre>                         sin importar si no fue fusionada

git commmit --amend  // para cambiar desde del ultimo commit

git commmit --amend  - m  "descripcion"

git add repo/  //agrego carpeta

hacerle seguimiento a la rama
 $ git push --set-upstream https://github.com/dirielfran/curso-git entrendiendo-ramas

//clonar un repositorio
git clone http://github.com/c215714n/git-desarrollo github


//para ver las rutas
git remote -v

origin  http://github.com/c215714n/git-desarrollo (fetch)
origin  http://github.com/c215714n/git-desarrollo (push)

git clone <url>        clon de un repositorio remoto, utiliza la rama master como principal
                        tambien se agrega por defecto el remoto origin con la direccion de url clonada


git clone <url> <nombre>  crea un clon en una carpeta con el nombre que elegimos

git clone --branch <nombrerama> <url>:
        crea un clon de la rama especificada siempre y cuando exista en el repositorio remoto

git fetch <url>:
        obtiene del servidor los archivos correspondientes a la rama actual
        actualiza

git pull <url> <rama>:
        obt los archivos del servidor y los combina con la rama actual fusionando los cambios
        actualiza repositorio LOCAL (fetch + merge + commit)
        en caso de trabajar con varias personas, si se han realizado cambios en el repo remoto antes de mandar los cambios al servidor debemos realizar el pull de los mismos

git push <url>:
        manda del local al servidor, en caso de trabajar de manera colaborativa para evitar conflictos en el historial git nos pide que hagamos un pull de los cambios efectuados en el servido>
        ACTUALIZA EL REPO REMOTO (commit)
        los cambios se podran enviar a ramas que se esten haciendo seguimiento, en caso contrario debemos agregarlas al servidor

git push --set-upstream <url> <rama>
        define una nueva rama a la que se le hara seguimiento y se agregara a las ramas administradas por nuestro repositorio remoto
        esta operacion debe realizarce la primera vez que realizamos un push sobre una rama agregada recientemente
        ACTUALIZA REPOSITORIO REOTO (BRANCH)

git push --unset-upstream <url> <rama> :
        quita el seguimiento de la rama por parte del repositorio
        ACTUALIZA REPOSITORIO LOCAL /REMOTO (BRANCH-)

***comparar la rama actual y otra
git diff entrendiendo-ramas

***realiza merge de la rama actual con otra
	git merge entrendiendo-ramas

//cancela la integracion de ramas, cuando surge un conflicto
git merge --abort

//te permite ir a un checkout del histoorico
git checkout <hash>

//puedes ir al head de nuevo
git switch -

//ir a dos commit atras 
git switch HEAD~2

//ir al ancestro
git checkout HEAD~1^

//ir a master
git checkout master

//quitar del index o sacar del commit 
git reset branches.txt

//reestablecer un archivo 
git checkout HEAD -- branches.txt

//reorganizar todos los directorios
git rebase branch-merge

