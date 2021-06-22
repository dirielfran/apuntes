GESTION DE RAMAS CON GITFLOW:
Metodología de trabajo con Git

3 pautas: 
*Tiempo
*Ramas
*Roles
---------------------------------------------------------------------------------------------
RAMAS:

Ramas fijas, con un tiempo de vida indefinido, NO SE TRABAJA DIRECTAMENTE SOBRE ESTAS RAMAS!:
-Master 
-Develop

Ramas auxiliares:
-Feature: va a tener los desarrollos evolutivos (1 rama por requerimiento)
-Release: contienen versiones de código candidato a ser estable
-Bug: Para solucionar bugs q van a ir en una próxima release candidate
-Hotfix: Para solucionar los errores de producción, no se planifican y se solucionan lo más rápido posible

----------------------------------------------------------------------------------------------
ROLES:
Realease Manager:
-Apertura, revisión y aceptación de código mediante las merge request
-Resolución de conflictos tras la revisión de las merge request
-Subidas de versión y despliegues en los distintos entornos
-Encagados de mantener la zona remota "limpia"

Desarrollador:
-Generación y actualización de código en local y posterior subida a remoto
-Primeros responsables de la apertura de las merge request
-Encargados de mantener la zona local "limpia" (borrar las ramas en local)
------------------------------------------------------------------------------------------------
HERRAMIENTAS:
Git como SCM:
-Gitlab
-Gestion de ramas basada en GitFlow

Jenkins:
-Pruebas unitarias
-Automatizar build
-Integración QA

sonarQube:
-Analizar la calidad centralizado + local para adquirir buenos hábitos de programación
------------------------------------------------------------------------------------------------



Pautas***************************************************
	Tiempos**********************************************
		*Cuando Comitera
			* al cambiar de rama
			* completar un submodulo
			* al terminar jornada laboral

		* Cuando pushear
			* a diario
			* al completar un modulo

		* Cuando hacer pull
			* siempre antes de hacer push
			* a diario desde su rama padre remota 





git checkout develop
git checkout -b feature_eareiza
git push 
 	cambios
git add .
git commit -m 
git pull
git push 
git pull origin develop
	merge request


ejercicio #2





































