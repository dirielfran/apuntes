Comandos angular-cli
ng new [nombre aplicativo] 			--> Creacion de aplicativo angular
ng generate class footer.component  --> crea clase
ng serve -o                         --> corre en el servidor la aplicacion
ng generate component directivas    --> crea un componente 
ng g c components/heroeTarjeta --skipTests=true  --> Creacion de componente sin el archivo de prueba
ng g c clientes/form --flat 		--> Crea componente con el -- flat se le indica que no cree folder
ng generate service cliente         --> Se crea clase de servicio
ng g m miModulo						--> Genera un modulo propio
ng g g usuarios/guard/auth  		--> genera guard en ruta especificada
ng g p pipes/noimage				--> Creacion de pipe
ng build --prod 					--> Creacion de mi carpeta de distribucion con la aplicacion mimificada 
guard 								--> Los Guard son interceptores que sirven para controlar el acceso a las rutas
.pipe								--> Los pipes son una herramienta de Angular que nos permite transformar visualmente la información,
										por ejemplo, cambiar un texto a mayúsculas o minúsculas, o darle formato de fecha y hora.
Observable 							--> Los observables brindan soporte para pasar mensajes entre partes de su aplicación. 
										Se usan con frecuencia en Angular y son la	técnica recomendada para el manejo de eventos, 
										la programación asincrónica y el manejo de múltiples valores. 
operador map 						--> Este operador nos permite recibir un valor de entrada y devolver un valor diferente al que recibimos.
flatMap (mergeMap) operator 		--> flatMap, también llamado mergeMap, nos va a permitir operar con otros observables. Básicamente 
										lo que va a hacer es transformar un Observable en otros Observables y unificar la salida de 
										todos los Observables bajo un solo stream.
operador switchMap() operator		--> El funcionamiento de switchMap es muy similar al de flatMap pero con una diferencia muy
										importante. Por cada nuevo ciclo del stream principal, el stream interior se detiene.
operador tap() 						--> Realice un efecto secundario para cada emisión en la fuente Observable, pero devuelva un Observable 
										que sea idéntico a la fuente.
operador fill() 					--> El fill() método llena los elementos especificados en una matriz con un valor estático.
										Puede especificar la posición de dónde comenzar y finalizar el llenado. Si no se especifica, 
										se completarán todos los elementos.
										Nota: este método sobrescribe la matriz original.
paramMap(Interface)					--> Un mapa que proporciona acceso a los parámetros obligatorios y opcionales específicos 
										de una ruta. El mapa admite recuperar un solo valor con get() o varios valores con getAll().
[(ngModel)]							--> Directiva que va a poblar desde el form a un atributo de la clase y a los atributos de este 
										(Binding) ESTA MAPEADO AL ATRIBUTO AL FORMULARIO.
	<input type="text" class="form-control" [(ngModel)]="cliente.apellido" name="apellido">
(ngSubmit)							--> <!--(ngSubmit) invoca un metodo de la clase-->
    <form (ngSubmit) ="create()">
[routerLink]						-->	<!--[routerLink] Nos permite crear rutas internas del proyecto-->
		            						<button class="btn btn-rounded btn-primary" type="buttom" [routerLink]="['/clientes/form']">Crear Cliente</button>
[ngClass]							--> Adds and removes CSS classes on an HTML element.
										<li class="page-item" *ngFor="let pagina of paginas" [ngClass]="pagina-1 == paginador.number?'disabled':''">
[ngValue]							--> [ngValue] permite enlazar un objeto complejo. De esta manera al seleccionar el nombre de la ciudad 
										desplegaremos el objeto entero. 
<ng-template>						-->	<!--Se agrega directiva ngIf, si no existe cliente coloca boton crear en caso contrario se utiliza template-->
                    						<button class="btn btn-primary" role="button" (click)='create()' *ngIf="!cliente.id else elseBlock">Crear</button>

						                    <ng-template #elseBlock>
						                        <button class="btn btn-primary" role="button" (click)='update()'>Editar</button>
						                    </ng-template>
*ngFor                              --> directiva estructural para recorrer una lista
	<li class="list-group-item" *ngFor="let curso of listaCursos">
*ngIf								--> directiva estructural condicional if
	<ul class="list-group" *ngIf="habilitar==true">
@Input								--> Los inputs nos permiten pasar valores desde el componente «padre» que utiliza dentro del el 
										otro componente y así intercambiar datos entre estos dos componentes.
EventEmitter 						--> Úselo en componentes con la directiva para emitir eventos personalizados de forma sincrónica 
										o asincrónica, y registre controladores para esos eventos suscribiéndose a una instancia.
funcion btoa(JavaScript)  			-->Convertir a base64 en JavaScript
										De esto se encarga la función btoa. Quiere decir “Binary to ascii” y bueno, convierte datos 
										binarios o cualquier tipo de dato a una representación imprimible.
funcion atob(JavaScript) 			-->Decodificar base64 en JavaScript
										Ahora entra la función atob, que quiere decir “ASCII to binary”. Recibe una cadena que está 
										codificada con base64 y devuelve los datos binarios.
Almacenar en el sessioStorage 		-->sessionStorage.setItem('usuarios', JSON.stringify(this._usuario));
Convertir un objeto en json 		-->JSON.stringify(this._usuario)
cambio de puerto 					--> ng serve --port 4401    
**************************************************************************************************************************Instalacion
	node --> https://nodejs.org/es/
		instalar en ubunto
			sudo apt-get update
			sudo apt install nodejs
			sudo apt install npm
			nodejs -v
			npm -v
			sudo npm install -g typescript
			sudo npm install -g @angular/cli
5.- ***************************************************************************************************Creando new Aplication Angular
	1.- desde d: , se crean carpetas del proyecto
		1.1.- Se habre consola de comandos cmdir
			mkdir springAngular
			cd springAngular
			mkdir Angular
			cd angular 
 			ng new clientes-app
 			ng serve -o  --> corre la aplicacion en el navegador
6.- **********************************************************************************************************Estructura del proyecto
	
	Referecias --> https://www.udemy.com/course/angular-spring/learn/lecture/11991932#overview
7.- ***************************************************************************************************Introduccion a los componentes
		
	Ref--> https://www.udemy.com/course/angular-spring/learn/lecture/21510576#questions/11585058/
10.- ***********************************************************************************************Integracion Bootstrap con Angular
	1.- Copiamos cdn de la pagina de Bootstrap y lo pegamos en index.html
		1.0.- copiamos en el 2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo
			<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
		1.1.- COPIAMOS AL FINAL DEL BODy
			<!-- JS, Popper.js, and jQuery -->
			<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
			<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
			<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
11.- ******************************************************************************************Creacion de componente HeaderComponent
	1.- Se crea controlador dentro dentro de folder app --> header
		header.component.ts 
	2.- Se crea la clase 
		//Se importa el Component para poderlo utilizar como decorador
		import { Component } from '@angular/core';
		//Se crea decorator
		@Component({
			//Se agrega el selector
		    selector: 'app-header',
		    //Se agrega la plantilla html
		    templateUrl: './header.component.html'
		})
		//Se exporta laa clase que sera importada en el module
		export class HeaderComponent{
		    title:string ='App Spring - Angular';
		}
	3.- Se modifica el archivo app.module.ts
		import { BrowserModule } from '@angular/platform-browser';
		import { NgModule } from '@angular/core';

		import { AppComponent } from './app.component';
		//Se importa la clase creada
		import { HeaderComponent} from './header/header.component';

		@NgModule({
		  declarations: [
		    AppComponent,
		    //Se agrega al modulo
		    HeaderComponent
		  ],
		  imports: [
		    BrowserModule
		  ],
		  providers: [],
		  bootstrap: [AppComponent]
		})
		export class AppModule { }
	1.- Se crea vista del componente, dentro de folder app --> header
		header.component.html
		1.1.- Se copia nvar de bootstrap y se pega en el archivo
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
			  <!--Se llama a variable creada en la clase-->  
			  <a class="navbar-brand" href="#">{{title}}</a>
			    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
			      <span class="navbar-toggler-icon"></span>
			    </button>
			    <div class="collapse navbar-collapse" id="navbarNav">
			      <ul class="navbar-nav">
			        <li class="nav-item active">
			          <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
			        </li>
			        <li class="nav-item">
			          <a class="nav-link" href="#">Features</a>
			        </li>
			        <li class="nav-item">
			          <a class="nav-link" href="#">Pricing</a>
			        </li>
			        <li class="nav-item">
			          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
			        </li>
			      </ul>
			    </div>
  </nav>
13.- *************************************************************************************Creando un nuevo componente FooterComponent
	1.- Se crea componete desde consola 
		1.1.- Se abre cmdir
		1.2.- Nos ubicamos en el proyecto d://angular/springangular/angular/clientes-app/src/app
			mkdir footer 
			cd footer
		1.3.- Se crea una clase
			ng generate class footer.component

			//Se importa el decorator @Component
			import { Component } from '@angular/core';
			// Se crea su decorador 
			@Component({
			    selector: 'app-footer',
			    templateUrl: './footer.component.html';
			})
			// Se importa el decorador 
			export class FooterComponent {
			    //se crea variable de tipo any (generico)
			    public author: any = {nombre: "Elvis", apellido:"Areiza"};
			}
		1.4.- Se crea footer.component.html
			<footer>
			    <div>
			        <p>
			            &copy; {{ author.nombre +' '+ author.apellido}}
			        </p>
			    </div>
			</footer>
		1.5.- Se modifica app.component.html, se le agrega
			<app-footer></app-footer>
		1.6.- Se modifica app.module.ts
			//Se importa la clase FooterComponent
			import { FooterComponent } from './footer/footer.component';
			 //Se agrega al modula la clase
    		FooterComponent
    	1.7.- Se crea hoja de estilo
    		footer.component.css
    			.footer{
				    position: absolute;
				    /*Para dejarlo al fondo*/
				    bottom: 0px;
				    /*altura*/
				    height: 60px;
				    width: 100%;
				}
    		1.7.1.- Se registra footer.component.css en el footer.component.ts
    			// Se crea su decorador 
				@Component({
				    selector: 'app-footer',
				    templateUrl: './footer.component.html',
				    styleUrls: ['./footer.component.css']
				})
14.- ****************************************************************************************************Directiva estructural *ngFor
	1.- Desde la consola se ejecuta 
		ng generate component directivas
		Nota: genera los archivos directivas.component.ts, directivas.component.html, directivas.component.css, directivas.component.spec.ts
		los configura en el modulo
	2.- Se modifica la clase
		export class DirectivasComponent implements OnInit {
		  //Se añade lista de cursos que seran desplegados en la vista
		  listaCursos: string[] = ['Java','JavaScript', 'PHP', 'C#','Angular'];
		  constructor() { }

		  ngOnInit(): void {
		  }

		}
	3.- Se modifica la vista, utilizando la rirectiva *ngFor para recorrer la lista
		<div class="card">
		    <div class="card-header">
		        Listado de cursos
		    </div>
		    <ul class="list-group">
		        <li class="list-group-item" *ngFor="let curso of listaCursos">{{ curso }}</li>*********************************
		    </ul>
		</div>
	4.- Se modifica app.component.html, agragando el selector app.directivas
		<app-header></app-header>
		<div class="container">
		    <h1>{{title}}</h1>
		    <app-directivas></app-directivas>
		</div>
		<app-footer></app-footer>
15.- *****************************************************************************************************Directiva estructural *ngIf
	1.- Se modifica directivas.component.html, se le añade boton de ocultar la lista 
		<!--boton de ocultar la lista-->
		<button class="btn btn-primary" (click)="visibilidad()">{{habilitar == true?'Ocultar':'Mostrar'}}</button>
		<div class="card">
		    <div class="card-header">
		        Listado de cursos
		    </div>
		    <!--Se utiliza directicva *ngIf-->
		    <ul class="list-group" *ngIf="habilitar==true">
		        <!--Se utiliza directiva *ngFor-->
		        <li class="list-group-item" *ngFor="let curso of listaCursos">{{ curso }}</li>
		    </ul>
		</div>
	2.- Se modifica directivas.component.ts, se agrega variable a la clase
		import { Component, OnInit } from '@angular/core';

		@Component({
		  selector: 'app-directivas',
		  templateUrl: './directivas.component.html',
		  styleUrls: ['./directivas.component.css']
		})
		export class DirectivasComponent implements OnInit {
		  //Se añade lista de cursos que seran desplegados en la vista
		  listaCursos: string[] = ['Java','JavaScript', 'PHP', 'C#','Angular'];
		  //Variable que utiliza el btn para mostrar u ocultar la lista
		  habilitar: boolean = true;
		  constructor() { }

		  ngOnInit(): void {
		  }
		  //Metodo que setea la variable habilitar
		  visibilidad(){
		    this.habilitar = (this.habilitar == true)?false:true;
		  }

		}
Seccion 3: Componente Cliente********************************************************************************************************
16.- ***************************************************************************************Creacion de componente clientes.component
	1.- Desde la consola, nos ubicamos en el proyecto,  se crea componente clientes
		ng g c clientes
	2.- Se crea clase clientes, ubicados en el folder clientes
		cd src/app/clientes
		ng g class cliente
		2.1.- Se crean atributos de la clase
			export class Cliente {
			    id: number;
			    nombre: string;
			    apellido: string;
			    fecha: string;
			    email: string;
			}
17.- ***********************************************************************************************Listando los objs de tipo cliente
	15.1.- Se crea lista de clientes en el controlador clientes.component.ts
		15.1.1.- Se importa la clase cliente
		15.1.2.- Se cre la lista en la clase
			import { Component, OnInit } from '@angular/core';
			//Se importa la clase cliente
			import { Cliente } from './cliente';

			@Component({
			  selector: 'app-clientes',
			  templateUrl: './clientes.component.html',
			  styleUrls: ['./clientes.component.css']
			})
			export class ClientesComponent implements OnInit {
			  //Se crea la lista de objs Cliente
			  clientes: Cliente[] = [
			    {id:1, nombre:'Elvis', apellido: 'Areiza', fecha: '1982-10-14', email:'dirielfran@gmail.com'},
			    {id:2, nombre:'Diego', apellido: 'Areiza', fecha: '2005-08-12', email:'diego@gmail.com'},
			    {id:3, nombre:'Antonio', apellido: 'Areiza', fecha: '2001-08-14', email:'antonio@gmail.com'},
			    {id:4, nombre:'Ricardo', apellido: 'Areiza', fecha: '2001-12-11', email:'ricardo@gmail.com'},
			    {id:5, nombre:'Cammila', apellido: 'Tobosa', fecha: '1982-10-14', email:'camila@gmail.com'},
			    {id:6, nombre:'Yalauri', apellido: 'Tobosa', fecha: '1986-12-17', email:'dirielfran@gmail.com'}
			  ]

			  constructor() { }



			  ngOnInit(): void {
			  }

			}
	15.2.- Se modifica la vista para mostrar la lista de clientes clientes.component.html
		<div class="card" style="">
		    <div class="card-header">
		      Lista de clientes
		    </div>
		    <table class="table table-bordered table-striped">
		        <thead>
		            <tr>
		                <th>Id</th>
		                <th>Nombre</th>
		                <th>Apellido</th>
		                <th>fecha</th>
		                <th>Email</th>
		            </tr>
		        </thead>
		        <tbody>
		            <!--Se añade condicional *ngFor-->
		            <tr *ngFor="let cliente of clientes">
		                <td>{{cliente.id}}</td>
		                <td>{{cliente.nombre}}</td>
		                <td>{{cliente.apellido}}</td>
		                <td>{{cliente.fecha}}</td>
		                <td>{{cliente.email}}</td>
		            </tr>
		        </tbody>
		    </table>
		  </div>
18.- ************************************************************************************************Creando archivo clientes.json.ts
	1.- En el folder clientes, se crea archivo clientes.json.ts
		1.1.- Se corta el array de clientes y se copia en el archivo Creando
		1.2.- En el archivo creado se importa Cliente
		1.3.- Se crea y se exporta constante que almacena la lista
			import { Cliente } from './cliente';

			export const CLIENTES: Cliente[] = [
			    {id:1, nombre:'Elvis', apellido: 'Areiza', fecha: '1982-10-14', email:'dirielfran@gmail.com'},
			    {id:2, nombre:'Diego', apellido: 'Areiza', fecha: '2005-08-12', email:'diego@gmail.com'},
			    {id:3, nombre:'Antonio', apellido: 'Areiza', fecha: '2001-08-14', email:'antonio@gmail.com'},
			    {id:4, nombre:'Ricardo', apellido: 'Areiza', fecha: '2001-12-11', email:'ricardo@gmail.com'},
			    {id:5, nombre:'Cammila', apellido: 'Tobosa', fecha: '1982-10-14', email:'camila@gmail.com'},
			    {id:6, nombre:'Yalauri', apellido: 'Tobosa', fecha: '1986-12-17', email:'dirielfran@gmail.com'}
			  ]
	2.- Se modifica clientes.component.ts, para importar la constante que contiene la lista
		//Se importa la lista de clientes de un archivo .json
		import { CLIENTES } from './clientes.json';
	3.- Se modifica la clase almacenando la lista importa en la variable clientes
		//Se crea cuando se inicia el componente
		  ngOnInit(): void {
		    //Se almacena en la lista, la lista importada del archivo json
		    this.clientes = CLIENTES;
		  }
19.- *********************************************************************Creacion de clase de Servicio y la Inyeccion de Dependencia
	1.- Desde la consola parado en clientes, se crea la clase de Servicio
		ng generate service cliente

		//Se importa el decorator
		import { Injectable } from '@angular/core';
		//Se importa la lista de clientes de un archivo .json
		import { CLIENTES } from './clientes.json';
		//Se importa la clase cliente
		import { Cliente } from './cliente';

		//El decorador indica la funcion de la clase
		@Injectable({
		  providedIn: 'root'
		})
		export class ClienteService {

		  constructor() { }

		  getClientes(): Cliente[] { 
		    return CLIENTES; 
		  } 
		}
	2.- Se modifica clientes.componet.ts
		//Se importa la clase de servicio
		import { ClienteService } from './cliente.service';

		  //Se crea variable en el const. y se le inyecta la clase de servicio
  		constructor(private clienteService: ClienteService) { }

  		 //Se crea cuando se inicia el componente
		  ngOnInit(): void {
		    //Se almacena en la lista, por medio de la clase de servicio
		    this.clientes = this.clienteService.getClientes();
		  }
	3.- Se modifica el app.module.ts
		//Se importa la clase de servicio
		import { ClienteService } from './clientes/cliente.service';

		//Se agrega al modulo la clase de servicio
  		providers: [ClienteService],
20.- **************************************************************************************************Introduccion a los observables
	
	Ref --> https://www.udemy.com/course/angular-spring/learn/lecture/21510620#questions/11585058/
21.- *******************************************************************************Implementacion Observable en la clase de Servicio
	Los observables brindan soporte para pasar mensajes entre partes de su aplicación. Se usan con frecuencia en Angular y son la 
	técnica recomendada para el manejo de eventos, la programación asincrónica y el manejo de múltiples valores. 
	1.- Se modifica la clase de servicio cliente.service.ts

		//Api Observable y of, soporte para el pase de mensajes entre partes de una aplicacion
		import { Observable, of } from 'rxjs';

		 //Patron de diseño, notifica el cambio de estado de clliente
		  //observado
		  getClientes(): Observable<Cliente[]> { 
		    //Se convierte listado de clientes en stream (flujo de datos)
		    return of(CLIENTES); 
		  } 
	2.- Se modifica clientes.component.ts, el metodo ngOnInit()
		//se suscribe el observable al observador
	    this.clienteService.getClientes().subscribe(
	      //funcion anonima
	      //observador, actualiza el listado de clientes 
	      clientes => this.clientes = clientes
	    );
22.- ************************************************************************************************Implementando rutas y navegacion
	Las rutas dividen la aplicacion en diferentes secciones o areas, que en realidad son spa en una sola pagina se renderiza paginas 
	diferentes, se anidan  las paginas
	1.- Se modifica el archivo app.module.ts
		1.1.- Se importan las librerias
			//se importa el routerModule y routes
			import { RouterModule, Routes } from '@angular/router';
		1.2.- Se crea en una constante las rutas de la aplicacion
			//Se crea constante que contiene arreglo con las rutas
			//Se definen todas las rutas de la aplicacion
			const routes: Routes =[
			  //pagina principal
			  {path: '', redirectTo: './clientes', pathMatch:'full'},
			  {path: 'directivas', component: DirectivasComponent},
			  {path: 'clientes', component: ClientesComponent}
			]
		1.3.- Se registra en imports las rutas creadas
			//Se registran las rutas creadas
	    	RouterModule.forRoot(routes)
	2.- Se modifica app.component.html
	   	<app-header></app-header>
		<div class="container">
		    <!-- <h1>{{title}}</h1>
		    <app-clientes></app-clientes> -->
		    <!-- Directiva especial de angular para indicar la renderizacion de cada ruta -->
		    <router-outlet></router-outlet>
		</div>
		<app-footer></app-footer>
	3.- Se modifica archivo header.component.html
		
		<!-- Se agrega link a directivas -->
        <li class="nav-item" routerLinkActive="active">
          <a class="nav-link" routerLink="./directivas">Directivas</a>
        </li>
        <!-- Se agrega link a clientes -->
        <li class="nav-item" routerLinkActive="active">
          <a class="nav-link" routerLink="./clientes">Clientes</a>
        </li>
24.- **************************************************************************************Configuracion de bootstrap de manera local
	1.- Se descargan los  archivos de bootstrap, jjquery,  pooper, bootstrap.css, bootstrap.js
		https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js
		https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js
		https://code.jquery.com/jquery-3.5.1.slim.min.js
		https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css
		1.1.- Se despliegan en el navegador
		1.2.- Guardar como

	2.- Se crean folder para js y css, dentro del folder assets (Carpeta donde estaran los archivos publicos) del proyecto
		assets/js
		assest/css
		2.1.- Se copian los archivos descargados en sus respectivos folders
	3.- Se reemplazan los links en el archivo index.html
		<!doctype html>
		<html lang="en">
			<head>
			  <meta charset="utf-8">
			  <title>ClientesApp</title>
			  <base href="/">
			  <meta name="viewport" content="width=device-width, initial-scale=1">
			  <link rel="icon" type="image/x-icon" href="favicon.ico">
			  <!-- CSS only -->
			  <link rel="stylesheet" href="assets/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
			</head>
			<body>
			  <app-root></app-root>
			  <!-- JS, Popper.js, and jQuery -->
			  <script src="assets/js/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
			  <script src="assets/js/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
			  <script src="assets/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
			</body>
		</html>
	4.- Se corre la aplicacion desde la consola
		ng serve -o
	5.- Se incluyen los archivos creados en el folder assets en el archivo angular.json
		    "assets": [
              "src/favicon.ico",
              "src/assets"
            ],
            "styles": [
              "src/styles.css",
              "src/assets/css/bootstrap.min.css"
            ],
            "scripts": [
              "src/assets/js/jquery-3.5.1.slim.min.js",
              "src/assets/js/popper.min.js",
              "src/assets/js/popper.min.js",
              "src/assets/js/bootstrap.min.js"
            ]
    6.- Instalando bootstrap utilizando comando npm
    	6.1.- Desde la consola del proyecto se ejecuta 
    		--> npm install bootstrap@4.5.0 jquery popper.js --save
    	6.2.- Se modifica el archivo angular.json, con las nuevas rutas
    	    "assets": [
              "src/favicon.ico",
              "src/assets"
            ],
            "styles": [
              "src/styles.css",
              "node_modules/bootstrap/dist/css/bootstrap.min.css"
            ],
            "scripts": [
              "node_modules/jquery/dist/jquery.slim.min.js",
              "node_modules/popper.js/dist/umd/popper.min.js",
              "node_modules/bootstrap/dist/js/bootstrap.min.js"
            ]
Seccion 4: Backend con Spring API REST***********************************************************************************************
29.- ********************************************************************************************Configuracion del Datasource a MySql
	https://www.udemy.com/course/angular-spring/learn/lecture/11992308#questions

	1.- Creacion de la app Empleos con springInitializr
		https://start.spring.io/
		1.1.- tipo de proyecto Maven como manejador de dependencias
		1.2.- lenguaje de prog. Java
		1.3.- Version se spring boot
		1.4.- Project Metadata --> com.eareiza
		1.5.- artefacto --> springAngular
		1.6.- nombre del proyecto  --> springAngular
		1.7.- sE CONFIGURA LA VERSION DE Java
		1.8.- Se añaden las dependencias
			web --> trae enbebido un contenedor con apache Tomcat
			JPA --> 
			dev-tools  --> herramientas de desarrolla para la aplicacion
			mySql  --> Driver de conexion a la BD 

		1.9.- Se guarda el archivo comprimido en el workspace
		1.10.- Se descomprime el archivo
		1.11.- desde el ide se importa el proyecto con maven, proyectos existentes 
		1.12.- se escoge el proyecto en el workspace
	2.- Se configura el data-source en el archivo aplication.properties
		#Configuracion del datasource
		#spring.datasource.url=jdbc:mysql://localhost/db_springboot_backend
		spring.datasource.url=jdbc:mysql://localhost:3306/db_springboot_backend?useSSL=false&serverTimezone=America/Argentina/Buenos_Aires&allowPublicKeyRetrieval=true
		spring.datasource.username=alfonso
		spring.datasource.password=danger120-
		spring.datasource.driver-class-name=com.mysql.jdbc.Driver
		#Se configura el dialecto
		#InnoDBDialec soporte a integridad referencial
		spring.jpa.database-platform=org.hibernate.dialect.MySQL57Dialect
		#Configuracion para que las tables se creen de forma automatica
		#a partir de las clases entity
		spring.jpa.hibernate.ddl-auto=create-drop
		#Configuracion para mostrar las consultas nativas SQL
		logging.level.org.hibernate.SQL=debug
	3.- Se añade dependencia 
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.49</version>
		</dependency>
32.- ************************************************************************************Añadiendo la clase entity Cliente al Backend
	32.1.- Se crea paquete de entidad
		com.eareiza.springAngular.model.entity
	32.2.- Se crea la clase Cliente


		@Entity
		@Table(name="clientes")
		public class Cliente implements Serializable{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private Long id;
			
			@Column()
			private String nombre;
			private String apellido;
			private String email;
			
			@Column(name="create_at")
			//Para transformar a la fecha date de sql
			@Temporal(TemporalType.DATE)
			private Date fecha;

			get y set...
		}
33.- *************************************************************************Clase Repository y de Servicio de la logica de negocios
	33.1.- Creacion de clase de repositorio (DAO)
		33.1.1.- Se crea paquete de repositorio
			com.eareiza.springAngular.model.repository
		33.1.2.- Se crea interface de repositorio que extienda de JpaRepository
			public interface IClientesRepository extends JpaRepository<Cliente, Integer> {

			}
	33.2.- Creacion de la clase de servicio
		33.2.1.- Se crea paquete de repositorio
			com.eareiza.springAngular.model.service
		33.2.2.- Se crea interface de la clase servicio
			public interface IClienteService {
				public List<Cliente> findAll();
			}
		33.2.3.- Se crea clase de serviicio

			@Service
			public class ClienteServiceImp implements IClienteService {

				@Autowired
				private IClientesRepository clienteRepo;
				
				@Override
				//anotamos la transaccion como de solo letura
				@Transactional(readOnly = true)
				public List<Cliente> findAll() {
					return clienteRepo.findAll();
				}

			}
34.- *************************************************************************************Crear controlador RestController y EndPoint
	1.- Se crea paquete de controladores
		com.eareiza.springAngular.comproller
	2.- Se crea la clase controlador ClienteController
		@RestController
		@RequestMapping("/api")
		public class ClienteRestController {
			@Autowired 
			private IClienteService clienteServ;
			
			@GetMapping("/clientes")
			public List<Cliente> index(){
				return clienteServ.findAll();
			}
		}

	3.- Se crea un archivo para importar la data a sql
		3.1.- Se crea en recursos--> new File --> import.sql (Debe tener el nombre import para que lo detecte el framework y lo exporte a MySql)
			/* Populate tabla clientes */
			INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Andrés', 'Guzmán', 'profesor@bolsadeideas.com', '2018-01-01');
			INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Mr. John', 'Doe', 'john.doe@gmail.com', '2018-01-02');
			INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2018-01-03');
			INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2018-01-04');
			INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2018-02-01');
			INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2018-02-10');
			INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2018-02-18');
			INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2018-02-28');
			INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Dr. James', 'Gosling', 'james.gosling@gmail.com', '2018-03-03');
			INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Magma', 'Lee', 'magma.lee@gmail.com', '2018-03-04');
			INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Tornado', 'Roe', 'tornado.roe@gmail.com', '2018-03-05');
			INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Jade', 'Doe', 'jane.doe@gmail.com', '2018-03-06');
	4.- Probar la aplicacion desde el navegador
		http://localhost:8080/api/clientes
	5.- Probar en Porstman
37.- *********************************************************************************Uso de Cors para compartir recursos en API REST
	Cors: Intercambio de recursos de origen crusado, desde un dominio a otro dominio, se tiene un cliente que accede a recursos en 
	otro dominio, interactua entre el navevagador web y el servidor

	1.- Se configura cors en el backend, modificando el controlador
		@RestController
		@RequestMapping("/api")
		//Se configuran los dominios permitidos, soporta una lista d dominios
		//Se pueden especificar los metodos permitidos, las cabeceras
		@CrossOrigin(origins= {"http://localhost:4200"})
		public class ClienteRestController {...}
38.- **********************************************************************************Implementacion Servicio Angular con HttpClient
	Referencia https://www.udemy.com/course/angular-spring/learn/lecture/11992338#questions/11133383

	1.- En la aplicacion clientes-app, se modifica el archivo app.module.ts, se agrega el modulo HttpClient, que es el mecanismo que tiene
	angular para la comunicacion con el servidor remoto, atraves de peticiones http con dif verbos get, post, put o delete (CRUD)
		1.1.- /*agrega el modulo HttpClient, que es el mecanismo que tiene
				angular para la comunicacion con el servidor remoto, atraves de peticiones http con dif verbos get, post, put o delete (CRUD)*/
			import { HttpClientModule } from '@angular/common/http';
		1.2.- Se agrega al import 
			 imports: [
			    BrowserModule,
			    //Se registra el HttpClientModule
			    HttpClientModule,
			    //Se registran las rutas creadas
			    RouterModule.forRoot(routes)
			  ], 
	2.- Se modifica cliente.sercice.ts, para que retorne una lista de forma remota remota
		2.1.- Se import HttpClient
			//Se importa la api que me permite llegar al endpoint
			import { HttpClient } from '@angular/common/http';
		2.2.- Se instancia en el constructor de la clase el obj HttpClient
			constructor(private http: HttpClient) { }
		2.3.- //Se alamacena en variable el endPoint
  			private urlEndPoint: string ='http://localhost:8080/api/clientes';
  		2.4.- Se modifica el metodo getClientes()
  			//Se recupera la lista con el metodo get del endpoint, se castea get<Cliente[]>() debido a que devuelve un any la promesa
    		return this.http.get<Cliente[]>(this.urlEndPoint);

    	2.4- (Otra forma de retornar la lista) con un map
    		//Se importa operador
			import { map } from 'rxjs/operators';

			//pipe permite agregar mas operadores, se toma la respuesta que viene en formato json (any) y 
			//se combierte  a un arreglo de clientes
		    return this.http.get(this.urlEndPoint).pipe(map( 
		      //Funcion anonima
		      response => response as Cliente[]
		    ));
Seccion 5: CRUD con Spring API REST**************************************************************************************************
40.- *********************************************************************************Metodos CRUD en la clase ClienteService Backend
	1.- Se crean los metodos abstractos en la interface IClienteService
		public List<Cliente> findAll();
	
		public Cliente save(Cliente cliente);
		
		public Cliente findById(Integer id);
		
		public void delete(Integer id);
	2.- Se implementan los metodos en la clase de servicio ClienteServiceImp.Java

		@Override
		//anotamos la transaccion como de solo letura
		@Transactional(readOnly = true)
		public List<Cliente> findAll() {
			return clienteRepo.findAll();
		}

		@Override
		@Transactional
		//Metodo que guarda un cliente
		public Cliente save(Cliente cliente) {
			return clienteRepo.save(cliente);
		}

		@Override
		@Transactional(readOnly = true)
		//Metodo que busca un cliente por id
		public Cliente findById(Integer id) {
			return clienteRepo.findById(id).orElse(null);
		}

		@Override
		@Transactional
		//Metodo que borra un cliente por id
		public void delete(Integer id) {
			clienteRepo.deleteById(id);
		}
41.- ****************************************************************************************Metodos para mostrar por id y para crear
	1.- Se crea metodo que retorna un cliente por el id
		@GetMapping("/cliente/{id}")
		public Cliente showCliente(@PathVariable Integer id) {
			return clienteServ.findById(id);
		}
	2.- Se crea metodo que guarda un cliente
		@PostMapping("/clientes")
		//Se utiliza @RequestBody para que los datos los busque 
		//dentro del cuerpo del mensaje, ya que los datos vienen por json de la aplicacion Angular 
		public Cliente saveCliente(@RequestBody Cliente cliente) {
			return clienteServ.save(cliente);
		}
	3.- Se modifica la clase Cliente para crearle un evento prePersist, para que antes de guardar la entidad se le asigne una fecha
		@PrePersist
		//Antes de crear la entidad le asigna una fecha 
		public void prePersist() {
			fecha = new Date();
		}
	4.- Se agrega a los metodos el manejo de codigos de respuesta 
		4.1.- Se modifica metodo showCliente() con la anotacion
			@GetMapping("/cliente/{id}")
			//Manejo de codigo de respuesta
			@ResponseStatus(HttpStatus.OK)
			public Cliente showCliente(@PathVariable Integer id) {
				return clienteServ.findById(id);
			}
		4.2.- Se modifica el metodo saveCliente() con la anotacion 
			@PostMapping("/clientes")
			//Manejo de codigo de respuesta
			@ResponseStatus(HttpStatus.CREATED)
			//Se utiliza @RequestBody para que los datos los busque 
			//dentro del cuerpo del mensaje, ya que los datos vienen por json de la aplicacion Angular 
			public Cliente saveCliente(@RequestBody Cliente cliente) {
				return clienteServ.save(cliente);
			}
42.- *********************************************************************************************************Metodos delete y update
	1.- Se crea el metodo update en el controlador ClienteRestController
		//Update 
		@PutMapping("/cliente/{id}")
		//CREATED CODIGO 201
		@ResponseStatus(HttpStatus.CREATED)
		public Cliente updateCliente(@RequestBody Cliente cliente,@PathVariable("id") Integer idCliente) {
			Cliente clienteActual = clienteServ.findById(idCliente);
			
			clienteActual.setApellido(cliente.getNombre());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());
			
			return clienteServ.save(clienteActual);
		}
	2.- Se crea el metodo delete en el controlador ClienteRestController 
		@DeleteMapping("/cliente/{id}")
		//NO_CONTENT CODIGO 204
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void delete(@PathVariable Integer id) {
			clienteServ.delete(id);
		}
45.- **************************************************************************************************Creando formulario con Angular
	1.- Creando componente dentro de cliente sin crear un folder(--flat)
		ng g c clientes/form --flat
	2.- Se modifica app.module.ts
		2.1.- Se importa la libreria de angular forms
			import { FormsModule } from '@angular/forms';
		2.2.- Se agrega en el import
			imports: [
			    BrowserModule,
			    //Se registra el HttpClientModule
			    HttpClientModule,
			    FormsModule,
			    //Se registran las rutas creadas
			    RouterModule.forRoot(routes)
			 ],
	3.- Se crea el atributo cliente en el form.component.ts
		3.1.- Se importa la clase Cliente
			//Se importa Cliente
			import { Cliente } from './cliente';
		3.2.- Se crea en la clase el atributo de la clase
			  //Se crea atributo de la clase
			  public cliente: Cliente = new Cliente();
			 
		3.3.- Se crea metodo invocado en el  formulario, en la clase
			//Se crea metodo invocado por el formulario
			  public create(): void{
			    console.log("clicked");
			    console.log(this.cliente);
			  }
		3.4.- Se crea en la clase atributo de titulo que se renderiza en el form
			//Se crea atributo de titulo que se renderiza en el formulario
  			public titulo: string ='Crear Cliente';
	4.- Se crea la vista, modiificando el archivo form.component.html, el formulario de creacion de cliente
			<div class="card bg-dark text-white">
		    <div class="card-header">{{ titulo }}</div>
		    <div class="card-body">
		        <!--(ngSubmit) invoca un metodo de la clase-->
		        <form (ngSubmit) ="create()">
		            <div class="form-group row">
		                <label for="nombre" class="col-form-label col-sm-2">Nombre:</label>
		                <div class="col-sm-6">
		                    <!--[(ngModel)] mapea con un atributo de la clase(Binding)-->
		                    <input type="text" class="form-control" [(ngModel)]="cliente.nombre" name="nombre">
		                </div>
		            </div>

		            <div class="form-group row">
		                <label form="apellido" class="col-form-label col-sm-2">Apellido:</label>
		                <div class="col-sm-6">
		                    <!--[(ngModel)] mapea con un atributo de la clase(Binding)-->
		                    <input type="text" class="form-control" [(ngModel)]="cliente.apellido" name="apellido">
		                </div>
		            </div>

		            <div class="form-group row">
		                <label for="email" class="col-form-label col-sm-2">Email:</label>
		                <div class="col-sm-6">
		                    <!--[(ngModel)] mapea con un atributo de la clase(Binding)-->
		                    <input type="text" class="form-control" [(ngModel)]="cliente.email" name="email">
		                </div>
		            </div>
		            <div class="form-group row">
		                <div class="col-sm-6">
		                    <button class="btn btn-primary" role="button">Crear</button>
		                </div>
		            </div>
		        </form>
		    </div>
		</div>
46.- ***********************************************************************************Configurando rutas y navegacion de Formulario
	1.- Se modifica el archivo app.module.ts, para mapear la ruta al formulario
		const routes: Routes =[
		  //pagina principal
		  {path: '', redirectTo: '/clientes', pathMatch: 'full'},
		  {path: 'directivas', component: DirectivasComponent},
		  {path: 'clientes', component: ClientesComponent},
		  //Se mapea la ruta al formulario
		  {path: 'clientes/form', component: FormComponent}
		]
	2.- Se crea boton en clientes.component.html con directiva [routerLink], para que dirija a la ruta configurada en ngModule
		<div class="my-2 text-left">
            <!--[routerLink] Nos permite crear rutas nternas del proyecto-->
            <button class="btn btn-rounded btn-primary" type="buttom" [routerLink]="['/clientes/form']">Crear Cliente</button>
        </div>
47.- ***********************************************************Implementacion metodo crear en cliente.service.ts y form.component.ts
	1.- Se modifica cliente.service.ts, se crea metodo que va al endpoint de guardar cliente
		1.1.- Se importo libreria HttpHeaders
			//Se importa la api que me permite llegar al endpoint
			import { HttpClient, HttpHeaders } from '@angular/common/http';
		1.2.- Se modifica la clase con un nuevo atributo httpHeaders
			  //Se crea headers para el endpoint
 			 private httpHeaders = new HttpHeaders ({'Content-type':'application/json'});
 		1.3.- Se crea metodo crearCliente()
 			createCliente(cliente: Cliente): Observable<Cliente>{
			    return this.http.post<Cliente>(this.urlEndPoint, cliente, {headers: this.httpHeaders});
			  }
	2.- Se modifica form.component.ts, se modifica metodo crear()
		2.1.- Se intancian en el constructor los atributos  
		  //Se instancia atributos en el constructor
  			constructor(private clienteService: ClienteService,  private router: Router) { }

  		2.2.- Se importan las librerias
  			import { ClienteService } from './cliente.service';
			import { Router } from '@angular/router';
		2.3.- Se modifica metodo crear para que llame al metodo de la clase de servicio
			public create(): void{
			    //Se llama el metodo de la clase de servicio y se suscribe al observable
			    this.clienteService.createCliente(this.cliente).subscribe(
			      response => this.router.navigate(['/clientes'])
			    )
			  }
49.- ************************************************************************************************************Instalar SweetAlert2
	1.- Se instala desde la consola parado en la raiz clientes-app, (--save) lo incluye en el package.json(pom.xml)
		npm install sweetalert2 --save
	2.- Se implementa en el form.component.ts
		2.1.- Se importa la libreria
			//Se importa la libreria de sweetAlert ya instalada
			import swal from 'sweetalert2'
	3.- Se modifica metodo create, para crear la allowPublicKeyRetrieval
		//Se crea metodo invocado por el formulario
		  public create(): void{
		    //Se llama el metodo de la clase de servicio y se suscribe al observable
		    this.clienteService.createCliente(this.cliente).subscribe(
		      cliente => {
		        this.router.navigate(['/clientes'])
		        //Metodo de sweetAlert
		        swal.fire('Nuevo Cliente', `Cliente ${cliente.nombre} creado con exito!`, 'success')
		        
		      }
		    )
		  }
50.- *********************************************************************************************Actualizar los datos del formulario
	1.- Se modifica cliente.service.ts, se crea metodo que obtiene cliente por id
		//Se crea metodo que recupera un Cliente
	  getCliente(id): Observable<Cliente>{
	    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`);
	  } 
	2.- Se modifica archivo form.component.ts para que llame al metodo de la clase de servicio
		2.1.- Se importan las librerias ActiveRoute
			import { Router, ActivatedRoute} from '@angular/router';
		2.2.- Se instancia atributo en el constructor
			constructor(private clienteService: ClienteService,  private router: Router,private actRoute: ActivatedRoute) { }
		2.3.- Se crea metodo en la clase, que llama al metodo de la clase de servicio
			//Se crea metodo que llama al metodo de la clase de servicio para obtener clienet por id
			  cargarCliente(): void{
			    //Obtine los parametros del formulario
			    this.actRoute.params.subscribe(
			      params => {
			        //Obtiene el valor del id
			        let id = params['id'];
			        if(id){
			          //Se llama al metodo de la clase de servicio
			          this.clienteService.getCliente(id).subscribe(
			            cliente => this.cliente = cliente
			          )
			        }
			      }
			    )
			  }
		2.4.- Se modifica el archivo app.module.ts, se mapea la url  la que renderiza el cliente
			  //Url dond se renderiza metordo que busca cliente por id
  			{path: 'clientes/form/:id', component: FormComponent}
  		2.5.- Se modifica clientes.component.html, se le agrega boton
  			<td>
                <button class="btn btn-primary" type="button" name="button" [routerLink]="['./clientes/form', cliente.id]">Editar</button>
            </td>









54.- **************************************************************************Update en el cliente.service.ts y en form.component.ts
	1.- Se modifica cliente.servises.ts, se crea metodo update() para modificacion de cliente
		//Metodo de modificacion del cliente
		  update(cliente: Cliente): Observable<Cliente>{
		    return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.id}`, cliente, {headers: this.httpHeaders});
		  } 
	2.- Se modifica form.component.ts, se crea metodo update(), el cual llama al metodo update del servicio
		//Metodo que llama al metodo update de cliente.service
		  update():void{
		    //Se llama a la clase de servici y se suscribe al metodo update
		    this.clienteService.update(this.cliente).subscribe(
		      cliente => {
		        //Se navega hasta la url clientes
		        this.router.navigate(['/clientes'])
		        //Se realiza interpolacion `` para poder mostrar el nombre del cliente modificado
		        swal.fire('Cliente Actualizado', `Cliente ${cliente.nombre} actualizado con exito!`, 'success')
		      }
		    )
		  }
	3.- Se realizan modificaciones a form.component.html, se cambia submit, por metodo (click) y se utiliza <ng-template>
		3.1.-  Se elmina (ngSubmit)
			<!--(ngSubmit) invoca un metodo de la clase-->
	        <!-- <form (ngSubmit) ="create()"> -->
	        <!-- Se elimina ngSubmit para agregar metodo (click al boton) -->
	        <form >
	     3.2.- Se agrega metodo (click), se agrega directiva ngIf para validar si existe el cliente, en caso contrario se ejecuta el teplate elseBlock 
	     	<!-- Se agrega metodo (click), se agrega directiva ngIf para validar si existe el cliente, en caso contrario se 
	     	ejecuta el teplate elseBlock -->
            <div class="form-group row">
                <div class="col-sm-6">
                	<!--Se agrega directiva ngIf, si no existe cliente coloca boton crear en caso contrario se utiliza template-->
                    <button class="btn btn-primary" role="button" (click)='create()' *ngIf="!cliente.id else elseBlock">Crear</button>

                    <ng-template #elseBlock>
                        <button class="btn btn-primary" role="button" (click)='update()'>Editar</button>
                    </ng-template>
                </div>
            </div>
55.- *******************************************************************************************Delete en la clase cliente.service.ts
	1.- Se modifica clase de servicio cliente.service.ts, se crea metodo delete para el cliente
		//Metodo delete de cliente
	  delete(id:numbre): Observable<Cliente>{
	    return this.http.delete<Cliente>(`${this.urlEndPoint}/{id}`, {headers: this.httpHeaders} );
	  }
	2.- Se modifica cliente.component.ts, se crea metodo delete() y se le pasa elemento sweetalert2
		 delete(cliente: Cliente): void{
		    const swalWithBootstrapButtons = Swal.mixin({
		      customClass: {
		        confirmButton: 'btn btn-success',
		        cancelButton: 'btn btn-danger'
		      },
		      buttonsStyling: false
		    })
		    
		    swalWithBootstrapButtons.fire({
		      title: 'Esta usted seguro?',
		      text: `Desea eliminar al cliente ${cliente.nombre} ${cliente.apellido}?`,
		      icon: 'warning',
		      showCancelButton: true,
		      confirmButtonText: 'Si, eliminar!',
		      cancelButtonText: 'No, cancelar!',
		      reverseButtons: true
		    }).then((result) => {
		      if (result.value) {
		        this.clienteService.delete(cliente.id).subscribe(
		          response => {
		            this.clientes = this.clientes.filter(cli => cli !== cliente)
		            swalWithBootstrapButtons.fire(
		              'Cliente Eliminado!',
		              `Cliente ${cliente.nombre} eliminado con exito.`,
		              'success'
		            )
		          }
		        )        
		      } else if (
		        /* Read more about handling dismissals below */
		        result.dismiss === Swal.DismissReason.cancel
		      ) {
		        swalWithBootstrapButtons.fire(
		          'Cancelado',
		          'La opcion de eliminar ha sido cancelada.',
		          'error'
		        )
		      }
		    })
		  }
	3.- Se modifica cliente.component.ts, se agrega boton para eliminar
		3.1.- Se agrega columna header
			 <th>Eliminar</th>
		3.2.- Se agrega boton para Eliminar
			 <td>
                <button class="btn btn-danger btn-sm" type="button" name="eliminar" (click)="delete(cliente)">Eliminar</button>
            </td>
56.- Se le dan caracteristicas css, para que el footer este estatico en el pie de pagina(overflow)*************************************
57.- *******************************************************************************Validar que el listado de cientes venga con datos
	1.- Se modifica cliente.component.html, para mostrar mensaje en caso de que no existan datos de clienteService
		<!-- Se crea div informativo en caso de no haber clientes en la lista -->
        <div class="alert alert-info" *ngIf="clientes?.length == 0">
            No hay registros en la Base de Datos.
        </div>
		<!-- Se agrega directiva ngIf para validar si la lista de clientes esta vacia -->
        <table class="table table-bordered table-striped"  *ngIf="clientes?.length>0">
    2.- A modo de prueba se modifica el archivo de recursos import.sql(backend


    	), se quitan todos los import y se corre la aplicacion
    3.- Se añaden los insert al archivo import.sql
Seccion 6: Manejo de errores en String(Backend)**************************************************************************************

59.- ******************************************************************************************Manejo de errores metodo showCliente()
	1.- Se modifica ClienteRestController.java, Se agrega objeto ResponseEntity para el manejo de errores
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
60.- ******************************************************************************************Manejo de errores metodo saveCliente()
	1.- Se modifica ClienteRestController.java, se modifica el metodo saveCliente() para manejar los errores
			@PostMapping("/clientes")
			//Manejo de codigo de respuesta
		//	@ResponseStatus(HttpStatus.CREATED)
			//Se utiliza @RequestBody para que los datos los busque 
			//dentro del cuerpo del mensaje, ya que los datos vienen por json de la aplicacion Angular 
			//Se agrega obj ResponseEntity para el manejo de respuesta
			public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
				Cliente clienteNew = null;
				//Se agrega map para el envio de mensaje y obj en el response
				Map<String, Object> response = new HashMap<>();
				//Manejo de errores utilizando el obj de spring DataAccessException
				try {
					//Se crea el cliente por medio de la clase de servicio
					clienteNew = clienteServ.save(cliente);
				} catch (DataAccessException e) {
					//Se crean mensajes de error y se añaden all map
					response.put("mensaje", "Error al insertar el cliente en la Base de Datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					//Se crea respuesta enviando los mensajes del error y el estatus
					return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				}
				//Se agrega mensaje success al mapa
				response.put("mensaje", "El cliente se ha registrado");
				//Se agrega obj cliente creado al mapa
				response.put("cliente", clienteNew);		
				//Se retorna respuesta añadiendo mapa y estatus
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			}
	2.- Se modifica Cliente.java, se le añaden constraint a los atributos nombre y email
		//Se añade constrain para que el nombre no pueda ser null
		@Column(nullable = false)

		//Se añade constrain para que el nombre no pueda ser null y que sea unico
		@Column(nullable = false, unique = true)
		private String email;
61.- ****************************************************************************************Manejo de errores metodo updateCliente()
		***Update de Cliente 
		@PutMapping("/clientes/{id}")
		//CREATED CODIGO 201
		

		//	@ResponseStatus(HttpStatus.CREATED)
		//Se agrega obj ResponseEntity para el manejo de respuesta
		public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente,@PathVariable("id") Integer idCliente) {
			//Se busca cliente a modificar
			Cliente clienteActual = clienteServ.findById(idCliente);
			Cliente clienteUpd = null;
			//Se crea map que se añadira al response
			Map<String, Object> response= new HashMap<>();
			//Se valida si es null el cliente
			if(clienteActual == null) {
				response.put("mensaje", "El cliente ID: ".concat(idCliente.toString().concat(" no existe en la Base de Datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			//Se manejan los errores con DataAccessException
			try {
				//Se modifica cliente
				clienteActual.setApellido(cliente.getNombre());
				clienteActual.setNombre(cliente.getNombre());
				clienteActual.setEmail(cliente.getEmail());
				clienteActual.setFecha(cliente.getFecha());
				//Se guarda el cliente
				clienteUpd = clienteServ.save(clienteActual);			
			} catch (DataAccessException e) {
				//Se crean mensajes de error y se añaden all map
				response.put("mensaje", "Error al modificar el cliente con ID ".concat(idCliente.toString()).concat("en la Base de Datos"));
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				//Se crea respuesta enviando los mensajes del error y el estatus
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}		
			//Se agrega mensaje success al mapa
			response.put("mensaje", "El cliente se ha registrado");
			//Se agrega obj cliente creado al mapa
			response.put("cliente", clienteUpd);		
			//Se retorna respuesta añadiendo mapa y estatus
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}
62.- ***********************************************************************************************Manejo de errores metodo delete()
		***Update de Cliente 
			@DeleteMapping("/clientes/{id}")
			//NO_CONTENT CODIGO 204
			//	@ResponseStatus(HttpStatus.NO_CONTENT)
			//Se agrega obj ResponseEntity para el manejo de respuesta
			public ResponseEntity<?> delete(@PathVariable Integer id) {
				Map<String, Object> response = new HashMap<>();
				try {
					clienteServ.delete(id);
				} catch (DataAccessException e) {
					//Se crean mensajes de error y se añaden all map
					response.put("mensaje", "Error al eliminar el cliente con ID ".concat(id.toString()).concat(" en la Base de Datos"));
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					//Se crea respuesta enviando los mensajes del error y el estatus
					return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				response.put("mensaje", "El Cliente ha sido eliminado con exito");
				
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		}
Seccion 7: Manejo de errrores Angular (FrontEnd)*************************************************************************************
63.- ***************************************************************************************Manejo de error en obtener cliente por id
	1.- Se modifica cliente.service.ts
		1.1.- Se importa la libreria catchError
			//Se importa de operadorla clase map y catchError
			import { map, catchError } from 'rxjs/operators';
		1.2.- Se importe  la libreria de sweetAlert
			//Se importa la libreria de sweetAlert ya instalada
			import Swal from 'sweetalert2';
		1.3.- //Se importa obj throwError
			import { Observable, of, throwError } from 'rxjs';
		1.4.- Se importa la libreria Router
			//Se importa objeto Route para redirijir a un enlace
			import { Router } from '@angular/router';
		1.5.- Se realiza la inyeccion de dependencia del obj Router en el constructor
			//Se instancia en el constructor de la clase el obj HttpClient y el obj Router 
  			constructor(private http: HttpClient, private router: Router) { }
		1.4.- Se modifica metodo getCliente() de la clase 
			//Se crea metodo que recupera un Cliente
			  getCliente(id): Observable<Cliente>{
			    //Se añade metodo pipe para para transformar la data 
			    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
			      //Se añade obj que me permite detectar si hay errores
			      catchError(e => {
			        //Se redirige al capturar el error
			        this.router.navigate(['/clientes']);
			        console.error(e.error.mensaje);
			        //Se muestra mensaje de error
			        Swal.fire('Error al obtener Cliente', e.error.mensaje,'error');
			        return throwError(e);
			      })
			    );
			  }
64.- **************************************************************************Manejo de error en los metoodos crear, update y delete
	1.- Se modifica archivo cliente.service.ts
		1.1.- Se modifica metodo createCliente(), para el manejo de errores
			 //Se crea metodo que crea el cliente
			  createCliente(cliente: Cliente): Observable<Cliente>{
			    //Se agrega pipe
			    return this.http.post<Cliente>(this.urlEndPoint, cliente, {headers: this.httpHeaders}).pipe(
			      //Se agrega el operador catchError, intercepta en caso de existir error
			      catchError(e => {
			        //Se muestra por consola el error
			        console.error(e.error.mensaje);
			        //Se muestra mensaje al usuario
			        Swal.fire('Error al crear el cliente', e.error.mensaje, 'error');
			        //Se retorna obj observable
			        return throwError(e);
			      })
			    );
			  }
		1.2.- Se modifica metodo update(), para el manejo de errores
			//Metodo de modificacion del cliente
			  update(cliente: Cliente): Observable<Cliente>{
			    return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.id}`, cliente, {headers: this.httpHeaders}).pipe(
			      catchError( e => {
			        console.error(e.error.mensaje);
			        Swal.fire('Error al modificar cliente', e.error.mensaje, 'error');
			        return throwError(e);
			      })
			    );
			  }
		1.3.- Se modifica metodo delete(), para el manejo de errores
		 	//Metodo delete de cliente
			  delete(id:number): Observable<Cliente>{
			    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders}).pipe(
			      catchError( e => {
			        console.error(e.error.mensaje);
			        Swal.fire('Error al eliminar el Ciente.', e.error.mensaje, 'error');
			        return throwError(e);
			      })      
			    );
			  }
65.- **********************************************************************Se arregla texto de mensaje de exito en crear y actualizar
	1.- Se modifica el emtodo crear para que maneje un json en vez de un Cliente
		1.1.- Se modifica cliente.service.ts, metodo createCliente()
			//Se crea metodo que crea el cliente
		  /* createCliente(cliente: Cliente): Observable<Cliente>{ */
		    /* Se modifica Observable para que trate un generico */
		    createCliente(cliente: Cliente): Observable<any>{
		    //Se agrega pipe
		    /* return this.http.post<Cliente>(this.urlEndPoint, cliente, {headers: this.httpHeaders}).pipe( */
		      //Se modifica el cast del metodo post
		      return this.http.post<any>(this.urlEndPoint, cliente, {headers: this.httpHeaders}).pipe(
		      //Se agrega el operador catchError, intercepta en caso de existir error
		      catchError(e => {
		        //Se muestra por consola el error
		        console.error(e.error.mensaje);
		        //Se muestra mensaje al usuario
		        Swal.fire('Error al crear el cliente', e.error.mensaje, 'error');
		        //Se retorna obj observable
		        return throwError(e);
		      })
		    );
		  }

      	1.2.- Se modififica form.component.ts, se modifica el metodo create para que maneje un json
      		 //Se crea metodo invocado por el formulario
			  public create(): void{
			    //Se llama el metodo de la clase de servicio y se suscribe al observable
			    this.clienteService.createCliente(this.cliente).subscribe(
			      /* cliente => { */
			        //Se cambia a json que es lo que recibe
			        json => {
			        this.router.navigate(['/clientes']);
			        //Metodo de sweetAlert
			        /* Swal.fire('Nuevo Cliente', `Cliente ${cliente.nombre} creado con exito!`, 'success'); */
			        Swal.fire('Nuevo Cliente', `Cliente ${json.cliente.nombre} creado con exito!`, 'success');
			      }
			    )
			  }
	2.- Modo dos de tratar el response, esta vez no como un json si no como un cliente
		 //Metodo de modificacion del cliente
		  update(cliente: Cliente): Observable<Cliente>{
		    /* return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.id}`, cliente, {headers: this.httpHeaders}).pipe( */
		      //Se modifica el cast del metodo put para que no convierta de forma automatica
		      return this.http.put(`${this.urlEndPoint}/${cliente.id}`, cliente, {headers: this.httpHeaders}).pipe(
		        //Se agrega operador map que combierte a Cliente
		        map((response: any) => response.cliente as Cliente),
		        catchError( e => {
		        console.error(e.error.mensaje);
		        Swal.fire('Error al modificar cliente', e.error.mensaje, 'error');
		        return throwError(e);
		      })
		    );
		  }
Seccion 8: ***********************************************************************Validacion de form por el lado de Angular (FronEnd)
	1.- Se modifica el archivo form.component.html, para validar el formulario
		1.1.- Validacion de input de nombre
			 <!-- Se agrega validaciones al input required, minlength, se asigna a una variable local nombre el objeto ngModel -->
	            <input type="text" class="form-control" [(ngModel)]="cliente.nombre" name="nombre" required minlength="4" #nombre="ngModel">
	            <!-- Se valida con ngIf si es invalido el contenido del input y si tipeas o dejas el foco -->
	            <div class="alert alert-danger" *ngIf="nombre.invalid && (nombre.dirty || nombre.touched)">
	                <!-- Se agrega mensaje validando si en los errores esta el reuired -->
	               <div *ngIf="nombre.errors.required">
	                    Nombre es requerido.
	               </div> 
	               <!-- Se agrega mensaje validando la longitud en los errores -->
	               <div *ngIf="nombre.errors.minlength">
	                   Nombre debe tener al menos 4 caracteres.
	               </div>
	            </div> 
	    1.2.- Validacion de input apellido
	    	<!--[(ngModel)] mapea con un atributo de la clase(Binding)-->
	        <input type="text" class="form-control" [(ngModel)]="cliente.apellido" name="apellido" requiered minlength="4" #apellido="ngModel">
	        <div class="alert alert-danger" *ngIf="apellido.invalid && (apellido.dirty || apellido.touched)">
	            <div *ngIf="apellido.errors.required">
	                Apellido es requerido.
	            </div>
	            <div *ngIf="apellido.errors.minlength">
	                Apellido debe tener al menos 4 caracteres.
	            </div>
	        </div>			
	    1.3.- Validacion de email
	    	<!--[(ngModel)] mapea con un atributo de la clase(Binding)-->
	        <input type="text" class="form-control" [(ngModel)]="cliente.email" name="email" required email #email="ngModel">
	        <div class="alert alert-danger" *ngIf="email.invalid && (email.dirty || email.touched)">
	            <div *ngIf="email.errors.required">
	                El email es requerido.
	            </div>
	            <div *ngIf="email.errors.email">
	                Email debe tener formato correcto.
	            </div>
	        </div>
	    1.4.- Se inhabilitan botones segun validacion del formulario
	    	1.4.1.- <!-- Se agrega varible global con con el objeto ngForm para que pueda ser validado -->
        		<form #validadorForm="ngForm">
        	1.4.2.-  <!-- Se agrega atributi disabled para que este inhabilitado si el formulario no esta de forma correcta -->
                    <button class="btn btn-primary" role="button" (click)='create()' *ngIf="!cliente.id else elseBlock" [disabled]="!validadorForm.form.valid">Crear</button>

                    <ng-template #elseBlock>
                        <button class="btn btn-primary" role="button" (click)='update()' [disabled]="!validadorForm.form.valid">Editar</button>
                    </ng-template>
Seccion 9: *******************************************************************************************Validacion form Spring(Backend)
67.- ********************************************************************************Anotaciones JavaBeans validacion en clase Entity
	1.- Se modifica el pom.xml, se agrega libreria javax.validation
		<!-- https://mvnrepository.com/artifact/javax.validation/validation-api -->
		<dependency>
		    <groupId>javax.validation</groupId>
		    <artifactId>validation-api</artifactId>
		    <version>2.0.1.Final</version>
		</dependency>
	2.- Se modifica Cliente.java
		2.1.- Se garegan validaciones al campo nombre
			//Se agrega validacion para que no este vacio
			@NotEmpty
			//Se agrega validacion de longitud
			@Size(min = 4, max = 12)
			//Se añade constrain para que el nombre no pueda ser null
			@Column(nullable = false)
			private String nombre;
		2.2.- Se agregan validaciones al campo email
			//Se agrega validacion para que no ecepte vacios
			@NotEmpty
			//Se agrega validacion para el email formato
			@Email
			//Se añade constrain para que el nombre no pueda ser null y que sea unico
			@Column(nullable = false, unique = true)
			private String email;
68.- *******************************************************Validacion metodos create()  y updateCliente() por intercecion con @Valid
	1.- Se Modifica ClienteRestController.java
		1.1.- Se valida el metodo saveCliente()
			1.1.1.- Se añade anotacion @Valid y se añade obj BindingResult a la firma
				//Se agrega obj ResponseEntity para el manejo de respuesta
				public ResponseEntity<?> saveCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {...}
			1.1.2.- Se realiza el manejo de errores por medio del obj BindingResult
						//Validacion de errores del binding result
						if(result.hasErrors()) {
							//Java 7
				//			List<String> errors = new ArrayList<>();
				//			//Se recorren todos los errores
				//			for (FieldError error :result.getFieldErrors()) {
				//				errors.add("El campo "+error.getField()+" "+error.getDefaultMessage());
				//			}
							
							//Java  8
							List<String> errores = result.getFieldErrors()
									//Se convierten a stream
									.stream()
									//Cada error se convierte en un tipo String
									.map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
									//Se convierte en una lista 
									.collect(Collectors.toList());
							response.put("errores", errores);
							return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
						}
		1.2.- Se valida el metodo updateCliente()
			1.2.1.- Se añade anotacion @Valid y se añade obj BindingResult a la firma
				//Se agrega obj ResponseEntity para el manejo de respuesta
				public ResponseEntity<?> updateCliente(@Valid @RequestBody Cliente cliente,
										BindingResult result, @PathVariable("id") Integer idCliente) {

			1.2.2.- Se realiza el manejo de errores por medio del obj BindingResult
				if(result.hasErrors()) {
					List<String> errores = result.getFieldErrors()
											.stream()
											.map(err -> "El campo "+err.getField()+" "+err.getDefaultMessage())
											.collect(Collectors.toList());
					response.put("errores", errores);
					return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
				}
	2.- Se agrega la dependencia en el pom.xml
		<dependency>
    		<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
	3.- Probar validaciones PostMapping
70.- **************************************************************************************Manejo de errores de validacion en Angular
	1.- Se modifica cliente.service.ts
		1.1.- Se maneja los errores en el metodo createCliente() y 
			createCliente(cliente: Cliente): Observable<any>{
		    //Se agrega pipe
		    /* return this.http.post<Cliente>(this.urlEndPoint, cliente, {headers: this.httpHeaders}).pipe( */
		      //Se modifica el cast del metodo post
		      return this.http.post<any>(this.urlEndPoint, cliente, {headers: this.httpHeaders}).pipe(
		      //Se agrega el operador catchError, intercepta en caso de existir error
		      catchError(e => {
		        //Manejo de validacion que viene en el response del backend
		        if(e.status == 400){
		          return throwError(e);
		        }
		        //Se muestra por consola el error
		        console.error(e.error.mensaje);
		        //Se muestra mensaje al usuario
		        Swal.fire('Error al crear el cliente', e.error.mensaje, 'error');
		        //Se retorna obj observable
		        return throwError(e);
		      })
		    );
		  }
	    1.2.- Se manejan los errores en el metodo update()
	    	//Metodo de modificacion del cliente
			  update(cliente: Cliente): Observable<Cliente>{
			    /* return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.id}`, cliente, {headers: this.httpHeaders}).pipe( */
			      //Se modifica el cast del metodo put para que no convierta de forma automatica
			      return this.http.put(`${this.urlEndPoint}/${cliente.id}`, cliente, {headers: this.httpHeaders}).pipe(
			        //Se agrega operador map que combierte a Cliente
			        map((response: any) => response.cliente as Cliente),
			        catchError( e => {
			          //Manejo de validacion que viene en el response del backend
			        if(e.status == 400){
			          return throwError(e);
			        }
			        console.error(e.error.mensaje);
			        Swal.fire('Error al modificar cliente', e.error.mensaje, 'error');
			        return throwError(e);
			      })
			    );
			  }
	2.- Se modifica form.component.ts
		2.1.-Se maneja el error que viene desde el backend
			2.1.1.- Se crea lista para albergar los errores que vienen del backend
				private errores: string[];
			2.1.2.- Se crean las moodificaciones en el metodo create()
				  public create(): void{
			    //Se llama el metodo de la clase de servicio y se suscribe al observable
			    this.clienteService.createCliente(this.cliente).subscribe(
			      /* cliente => { */
			        //Se cambia a json que es lo que recibe
			        json => {
			        this.router.navigate(['/clientes']);
			        //Metodo de sweetAlert
			        /* Swal.fire('Nuevo Cliente', `Cliente ${cliente.nombre} creado con exito!`, 'success'); */
			        Swal.fire('Nuevo Cliente', `Cliente ${json.cliente.nombre} creado con exito!`, 'success');
			      }, 
			      //Recorrido de errores del backend
			      err => {
			        this.errores =  err.error.errors as string[];
			        console.error('Codigo del error desde el backend '+err.status);
			        console.error(err.error.errors);
			      }
			    )
			  }
			2.1.3.- Se crean las modificaciones para el metodo update()
				 //Metodo que llama al metodo update de cliente.service
				  update():void{
				    //Se llama a la clase de servici y se suscribe al metodo update
				    this.clienteService.update(this.cliente).subscribe(

				      cliente => { 
				        //Se navega hasta la url clientes
				        this.router.navigate(['/clientes'])
				        //Se realiza interpolacion `` para poder mostrar el nombre del cliente modificado
				        Swal.fire('Cliente Actualizado', `Cliente ${cliente.nombre} actualizado con exito!`, 'success') 
				      }, 
				      //Recorrido de errores del backend
				      err => {
				        this.errores =  err.error.errors as string[];
				        console.error('Codigo del error desde el backend '+err.status);
				        console.error(err.error.errors);
				      }
				    )
				  }
71.- ****************************************************************************Se agregan los mensajes de error a la plantilla form
	1.- Se modifica form.component.html, se le agrega lista de errores
		<ul class="alert alert-danger" *ngIf="errores?.length>0">
		  <li *ngFor="let err of errores">
		    {{err}}
		  </li>
		</ul>
	2.- Se eliminan las validaciones del form para probar
Seccion 10: Transformacion de datos del observable usando map************************************************************************
74.- ************************************************************************************Operador map formato uppercase en Observable
	1.- Se modifica archivo cliente.service.ts, se modifica cada elemento de la lista de clienetes retornada para que el nombre del 
	cliente se le formato mayuscula, utilizanzo el operador map 

       getClientes(): Observable<Cliente[]> {
		    //Se convierte listado de clientes en stream (flujo de datos)
		    //return of(CLIENTES);
		    //Se recupera la lista con el metodo get del endpoint, se castea get<Cliente[]>() debido a que devuelve un any la promesa
		    //return this.http.get<Cliente[]>(this.urlEndPoint);
		    //pipe permite agregar mas operadores, se toma la respuesta que viene en formato json (any) y se combierte  a un arreglo de clientes
		    return this.http.get(this.urlEndPoint).pipe(
		      map(
		        //Funcion anonima
		        response => {
		          //se instancia variable a retornar y se almacena la respuesta convertida en una rray de Cliente
		          let clientes = response as Cliente[];
		          //El array de cliente por medio de map a c/u se le aplica metodo toUpperCase y se retorna
		          return clientes.map(cliente => {
		            cliente.nombre = cliente.nombre.toUpperCase();
		            return cliente;
		          });
		        }
		      )
		    );
		  }
75.- **************************************************************************Operador map para dar formato a fecha en un Observable
	1.- Se modifica cliente.service.ts
		1.1.- Se importa libreria propia de Angular para formateo de fechas
			//Libreria para el formato de fechas
			import { formatDate } from '@Angular/common';
		1.2.- Se modifica metodo getCliente(), Se agrega formato a ala fecha del cliente
			//Se agrega formato a fecha
            cliente.fecha = formatDate(cliente.fecha,'dd-MM-yyyy', 'en-US');
            1.2.1.- Otra forma de formatear una fecha es con la libreria DatePipe
            	1.2.1.1.- Se import la libreria 
            		//Librerias para el formato de fechas
					import { formatDate,DatePipe } from '@Angular/common';
            	1.2.1.2.- semodifica metodo getCliente(), Se instancia obje DatePipe y luego se utiliza el metodo transformar, 
            	para formatear la fecha
            		let datePipe = new DatePipe('en-US');
            		cliente.fecha = datePipe.transform(cliente.fecha,'dd/MM/yyyy');
76.- *****************************************************************Registrando Locale y los diferentes Pattern de formato de fecha
	1.- Se modifica cliente.service.ts
		1.1.- Se importa el objeto RegisterLocalData de la libreria common de angular
			//Librerias para el formato de fechas, se importa el registerLocalData para localidad config
			import { formatDate,DatePipe, registerLocaleData } from '@Angular/common';
		1.2.- Se importa el locale
			//Import locale
			import localeES from '@angular/common/locales/es-AR'; 
		1.3.- Se modifica metodo getCliente(), para registrar el locale y configurarselo al formato de fecha
			 registerLocaleData(localeES, 'es')
            //Se agrega formato a fecha
            /* cliente.fecha = formatDate(cliente.fecha,'fullDate', 'es'); */
            cliente.fecha = formatDate(cliente.fecha,'EEEE dd MMMM,yyyy', 'es');
        1.4.- Se modifica el app.module.ts, Se pasa la visibilidad del registerLocaleData de manera global para toda la aplicacion
        	//se importa el registerLocalData para localidad config
			import {  registerLocaleData } from '@Angular/common';
			//Import locale
			import localeES from '@angular/common/locales/es-AR'; 
			//Se registra la localidad
			registerLocaleData(localeES, 'es')
77.- ******************************************************************Uso de Pipe para formater fecha y uppercase en plantillas html
	1.- Se modifica cliente.service.ts
		1.1.- Para pruebas se modifica el metodo getCliente(), se comenta el formato de fecha
			 //cliente.fecha = formatDate(cliente.fecha,'EEEE dd MMMM,yyyy', 'es');
	2.- Se modifica cliente.component.html
		2.1.- Se agregan los pipe (|) en la plantilla para formatear apellido y fecha
    		<td>{{cliente.fecha | date:'EEEE dd, MMMM yyyy'}}</td>
            <td>{{cliente.apellido | uppercase}}</td>
    3.- Se agrega configuracion del locale_id, que es para toda la aplicacion incluyendo las plantillas html
    	3.1.- Se modifica app.module.ts
    		3.1.1.- Se agrega al provider la l¿configuracion
    			   // Se agrega confg de LOCALE_ID
				  providers: [ClienteService, {provide: LOCALE_ID, useValue: 'en-US' }],
			3.1.2.- Se importa la constante LOCALE_ID de angular/core
				//Se importa la constante LOCALE_ID
				import { NgModule, LOCALE_ID } from '@angular/core';
			3.1.3.- Se modifica configuracion del LOCALE_ID para que use el registerLocaleData configurado ('es')
				providers: [ClienteService, {provide: LOCALE_ID, useValue: 'es' }],















78.- *******************************************************************************************Uso del operador map en el Observable
	1.- Se modifica cliente.service.ts
		1.1.- Se importa el operador 
				//Se importa de operadorla clase map y catchError, tap
			import { map, catchError, tap} from 'rxjs/operators';

		1.2.- Se modifica el metodo getCliente(), para trabajar con operador map
			 //operador tap
		      tap(

		        response => {
		          //Se comierte el objeto en un cliente para poder utilizar el foreach
		          let cliente = response as Cliente[];
		          cliente.forEach(
		            cliente => {console.log(cliente.nombre)}
		          );
		        }
		      ),
Seccion 11: Paginacion desde el backend**********************************************************************************************
79.- *****************************************************************************Implementacion paginacion en API Rest y  Repository
	1.- Se modifica IClienteRepository, se extiende de JpaRepository
		public interface IClientesRepository extends JpaRepository<Cliente, Integer> {
		}
	2.- Se modifica IClienteService, se agrega metodo que recupera la lista de paginacion
		public Page<Cliente> findAll(Pageable pagina);
	3.- se modifica la clase de servicio ClienteServiceImm, Se agrega metodo en la clase de servicio
		@Override
		@Transactional(readOnly = true)
		public Page<Cliente> findAll(Pageable pagina) {
			return clienteRepo.findAll(pagina);
		}
	4.- Se modifica ClienteRestController, se agrega metodo ue retorna un objeto Page de paginacion
			@GetMapping("/clientes/page/{page}")
		public Page<Cliente> index(@PathVariable("page") Integer pagina){
			Pageable page = PageRequest.of(pagina, 4);
			return clienteServ.findAll(page);
		}
	5.- Se prueba con postman el metodo get creado
	.
		localhost:8080/api/clientes/page/1
81.- ***************************************************************************Modificacion del Observable de Cliente en el frontEnd
	1.- Se modifica cliente.service.ts
		1.1.- Se modifica metodo getClientes()
			1.1.1.- //Se modifica para que acepte el observable un generico
    			getClientes(): Observable<any> {
    		1.1.2.-//Se indica que la respuesta puede ser un generico, dentro del tap
          		(response: any) => {
          	1.1.3.- //Se castea el content de la respuesta a un arrego de clientes
		          (response.content as Cliente[]).forEach(
		            cliente => {console.log(cliente.nombre)}
		          );
			1.1.4.- //Se indica que la respuesta puede ser un generico, dentro del map	
          		(response: any) => {
          	1.1.5.- //Se caste el content de la respuesta a un arrego de clientes
          		(response.content as Cliente[]).map(cliente => {
    		1.1.6.- Se le añade argumento al metodo para recuperar la pagina
    			   //metodo que recupera un objeto pageable del backend
    			getClientes(page: number): Observable<any> {
    		1.1.7.- Se  odifica el endPoint
    			return this.http.get(this.urlEndPoint+'/page/'+page).pipe(
    2.- Se modifica cliente.component.ts
    	2.1.- Se modifica el metodo ngOnInit()
    		2.1.1.- Se le agrega atributo para la apaginacion
    			let page:number = 0;	
    		2.1.2.- Se agrega el argumento de pagina al llamado del metodo getClientes()
    			this.clienteService.getClientes(page).pipe(
    		2.1.3.- Se modifica para que maneje un response en ves de un cliente, y se caste el response.content a un Cliente[] a
    			ser recorrido
    			tap(response =>{
		          console.log('cliente.component: tap 3');
		          (response.content as Cliente[]).forEach(element => {
		            console.log(element.nombre);
		          });
		        }
			2.1.4.- Se modifica el suscribe para que maneje el response, realice el cast y modifique el atributo clienetes
				.subscribe(
				      //funcion anonima
				      //observador, actualiza el listado de clientes
				      response => this.clientes = (response.content as Cliente[])
				    );			      		
82.- *******************************************************Agregando la nueva ruta page del paginador y el Observable ActivatedRoute
	1.- Se modifica app.module.ts, se le agrega la ruta de apaginacion
		{path: 'clientes/page/:page', component: ClientesComponent},
	2.- Se modifica cliente.component.ts
		2.1.- Se importa la libreri ActivadedRoute
			import { ActivatedRoute } from '@angular/router';
		2.2.- Se inyecta en el constructor
			constructor(private clienteService: ClienteService, private activatedRoute: ActivatedRoute) { }
		2.1.- se modifica el metodo ngOnInit(), para que recupere el page dinamicamente
			2.1.1.- Se añade observador paramMap al atributo activatedRoute
				 ngOnInit() {
				    //Se le añade observador parammMap
				    this.activatedRoute.paramMap.subscribe( params => {
				        //Se agrega atributo que recupera la pagina dinamicamente
				        let page:number = +params.get('page');
				        if(!page){
				          page =0;
				        }
				        //se suscribe el observable al observador
				        //Observador
				        //ae añade pipe, para añadir operadores
				        this.clienteService.getClientes(page).pipe(
				          tap(response =>{
				              console.log('cliente.component: tap 3');
				              (response.content as Cliente[]).forEach(element => console.log(element.nombre))
				          })
				        ).subscribe(
				          //funcion anonima
				          //observador, actualiza el listado de clientes
				          response => this.clientes = (response.content as Cliente[])
				        );
				      }
				 	   );
				  }
83.- ****************************************************************************************************Creando componente paginador
	1.-Se crea componente 
		ng g c paninator
	2.- Se borrar el componente de testing y el de css
	3.- Se modifica el paginator.component.ts
		3.1.- Se elimina styleUrls
	4.- Se modifica clientes.component.html, se agrega el tag del componente creado luego de la tabla
		<!-- Paginacion -->
        <paginator-nav></paginator-nav>
84.- ***********************************************************Inyectar paginador usando decorador @Input en el componente paginador
**************************************************************Inyeccion de dependencia entre un componente padre y un componente hijo
	1.- Se modifica clientes.component.ts
		1.1.- Se crea en la clase atributo para la paginacion
			  //Atributo para la paginacion
  			paginador: any;
		1.2.- Se modifica metodo ngOnInit()
			1.2.1.- Dentro del metodo subscribe se le da valor al atributo paginador
				this.paginador = response;
	2.- Se modifica clientes.component.html
		2.1.- Se inyecta el paginador al html
			<!-- Paginacion -->
           <paginator-nav [paginador] = "paginador"></paginator-nav>
	3.- Se modifica paginator.component.ts 
		3.1.- Se importa obj input de la libreria angular/core
			import { Component, OnInit, Input } from '@angular/core';
		3.2.- Se agrega atributo de tipo input
			 @Input() paginador: any;
	4.- Se modifica paginator.component.html
		4.1.- Se muestra el listado de clientes

	5.- Se modifica paginator.component.ts, Creacion de arreglo de paginas que se mostraran en la vista
		5.1.- Se crea atributo para el paginador
			paginas: number[];
		5.2.- Se modifica el metodo ngOnInit(), se crea una lista iterable de paginas
			this.paginas = new Array(this.paginador.totalPages).fill(0).map((valor, indice) => indice + 1)
	6.- Se modifica clientes.component.html, se le añade validacion para que renderice la paginacion cuando exista el atributo paginador	
		<paginator-nav *ngIf="paginador" [paginador] = "paginador"></paginator-nav>
	7.- Se modifica paginator.component.html, se le añade validacion(*ngIf) para que muestre en caso de que existan paginas, se recorre 
		y se renderiza el array de paginas 
		<ul *ngIf="paginas?.length > 0" class="pagination">
		  <li *ngFor="let pagina of paginas">{{pagina}}</li>
		</ul>
85.- *****************************************************************************************Implementacion de link para las paginas
	1.- Se modifica paginator.component.html. 
		1.1.- Se le añade link al recorrido de las paginas
			<a [routerLink]="['/clientes/page', pagina-1]">{{pagina}}</a>
		1.2.- Se le añade estilos de bootstrap a los botones de paginacion
			1.2.1.- Se le añade la clase page-item a el tag <li>
				<li class="page-item" *ngFor="let pagina of paginas">
			1.2.2.- Se le añade la clase page-link a el tag <a>
				<a class="page-link" [routerLink]="['/clientes/page', pagina-1]">{{pagina}}</a>
			1.2.3.- Se añade filtro para que mustre el item actual de forma diferente
				<span class="page-link" *ngIf="pagina-1 == paginador.number">{{pagina}}</span>
    			<a *ngIf="pagina-1 != paginador.number" class="page-link" [routerLink]="['/clientes/page', pagina-1]">{{pagina}}</a>
86.- ***************************************************************************************Implementacion de boton Siguiente y atras
	1.- Se modifica paginator.component.html
		1.1.- Se añade validacion con la directica [ngClass], para que dasabilite el objto en caso de ser el seleccionado
			<li class="page-item" *ngFor="let pagina of paginas" [ngClass]="pagina-1 == paginador.number?'disabled':''">
		1.2.- Se añade boton de Siguiente
			<li class="page-item" *ngIf="paginador.number < 0">
			    <a [routerLink]="['/clientes/page',paginador.number+1]" class="page-link">&raquo;</a>
			</li>
		1.3.- Se añade boton de Atras
			<li class="page-item" *ngIf="paginador.number > paginador.totalPages-1">      "
			    <a [routerLink]="['/clientes/page',paginador.number-1]" class="page-link">&raquo;</a>
			</li>
87.- ****************************************************************************Implementacion de link para ulltima y primera pagina
	1.- Se modifica paginator.component.html
		1.1.- Se crea link para que vaya a la primera pagina
		  <li [ngClass]="paginador.number == 0 ? 'disabled page-item':'page-item'">
		    <a [routerLink]="['/clientes/page',0]" class="page-link">Primera</a>
		  </li>
		1.2.- Se crea link para que vaya a la ultima pagina
			 <li [ngClass]="paginador.number == paginador.totalPages-1 ? 'disabled page-item':'page-item'">
			    <a [routerLink]="['/clientes/page',paginador.totalPages-1]" class="page-link">Última</a>
			  </li>
		1.3.- Se realiza de otra forma utilizando el atributo del obj pageable firt y localhost
			1.3.1.- Se modifica la condicion del ngClass
			  <li [ngClass]="paginador.First ? 'disabled page-item':'page-item'">
			    <a [routerLink]="['/clientes/page',0]" class="page-link">Primera</a>
			  </li>
			1.2.- Se modifica la condicion del ngClass
				 <li [ngClass]="paginador.last ? 'disabled page-item':'page-item'">
				    <a [routerLink]="['/clientes/page',paginador.totalPages-1]" class="page-link">Última</a>
				  </li>
		1.4.- Se modifica el link de la pagina actual para que este activatedRoute
			<li class="page-item" *ngFor="let pagina of paginas" [ngClass]="pagina-1 == paginador.number?'active':''">
88.- ******************************************************************************************Paginador con muchas paginas: overflow
	1.- Se modifica paginator.component.html
		1.1.- Se modifica el ul para que se muestre en scroll
			<ul *ngIf="paginas?.length > 0" class="pagination" style="overflow:scroll">
		1.2.- Para pruebas se realizan modificaciones en el backend, se insertan muchos registros
			1.2.1.- Se modifica entidad Cliente.java para que acepte correos repetidos
					@Column(nullable = false, unique = false)
					private String email;
			1.2.2.- Se modifica insert.sql, se le añaden muchos registros
89.- *****************************************************************************Implementacion de rangos en el componente paginator
	1.- Se modifica paginator.component.ts, para que tenga un techo y un RuntimePermission
		1.2.- Se cren dos atributos desde y hasta 
			desde: number;
  			hasta : number;
  		1.3.- Se modifica el metodo ngOnInit(), para calcular el desde y el hasta, con una formula matematica
  			this.desde = Math.min(Math.max(1,this.paginador.number - 4), this.paginador.totalPages -5);
    		this.hasta = Math.max(Math.min(this.paginador.totalPages, this.paginador.number + 4), 6);

    	1.4.- Se añade proceso que devolvera la cantidad de paginas tomando en cuenta el desde y el hasta
		    if(this.paginador.totalPages > 5){
		      this.paginas = new Array(this.hasta - this.desde +1).fill(0).map((valor, indice) => indice + this.desde);
		    }else{
		      this.paginas = new Array(this.paginador.totalPages).fill(0).map((valor, indice) => indice + 1);
		    }
		Nota: por el ciclo de vida como el proceso lo estamos realizando en el metodo ngOnInit y este se ejecuta una sola
			vez, no esta cambiando el numero de pagina
		1.5.- Se corrige el cambio de numero de paginacion, utilizando el onChange
			1.5.1.- Se importa OnChange de la libreria angular/core
				import { Component, OnInit, Input, OnChanges } from '@angular/core';
			1.5.2.- Se implementa la interface OnChanges
				export class PaginatorComponent implements OnInit, OnChanges {...}
			1.5.3.- Se crea metodo ngOnChanges(), se corta el proceso de ngOnInit y se pega en el metodo ngOnChanges
				ngOnChanges(): void{
				    this.desde = Math.min(Math.max(1,this.paginador.number - 4), this.paginador.totalPages -5);
				    this.hasta = Math.max(Math.min(this.paginador.totalPages, this.paginador.number + 4), 6);

				    if(this.paginador.totalPages > 5){
					      this.paginas = new Array(this.hasta - this.desde +1).fill(0).map((valor, indice) => indice + this.desde);
					    }else{
					      this.paginas = new Array(this.paginador.totalPages).fill(0).map((valor, indice) => indice + 1);
					    }
					}
				}
		1.6.- Se modifica paginator.component.html, el ul para que se muestre en scroll se elimina
			<ul *ngIf="paginas?.length > 0" class="pagination" >
90.- ***********************************************************************************************Otra implementacion de paginacion
	1.- Se modifica paginator.component.ts
		1.1.- Se importa obj SimpleChanges de la libreria angular/core
			import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
		1.2.- Se crea metodo initPaginator(), se le coloca el proceso de calculo de los link de paginas
			private initPaginator(): void {
			    this.desde = Math.min(Math.max(1,this.paginador.number - 4), this.paginador.totalPages -5);
			    this.hasta = Math.max(Math.min(this.paginador.totalPages, this.paginador.number + 4), 6);

			    if(this.paginador.totalPages > 5){
			      this.paginas = new Array(this.hasta - this.desde +1).fill(0).map((valor, indice) => indice + this.desde);
			    }else{
			      this.paginas = new Array(this.paginador.totalPages).fill(0).map((valor, indice) => indice + 1);
			    }
			  }
		1.3.- Se modifica metodo ngOnInit(), para llamar al metodo initPaginator()
			  ngOnInit(): void {
			    this.initPaginator();
			  }
		1.4.- Se modifica metodo ngOnChanges(), para que se llame al metodo initPaginator() en caso de que haya cambios.
			  ngOnChanges( changes: SimpleChanges): void{
			    let paginadorActualizado = changes['paginador'];
			    if(paginadorActualizado.previousValue){
			      this.initPaginator();
			    }
			  }
Seccion 12 Añadir fecha al formulario************************************************************************************************
92.- ********************************************************************************Añadir campo para fecha en el backend y frontend
	1.- Se modifica Cliente.java
		1.1.- Se le agrega la anotacion @NotNull con un mensaje a ell campo fecha
			@NotNull(message = "El campo fecha no puede estar vacio.")
			@Column(name="create_at")
			//Para transformar a la fecha date de sql
			@Temporal(TemporalType.DATE)
			private Date fecha;
		1.2.- Se comenta el metodo prePersist, ya que se va a manejar ahora con un datepicker
			//@PrePersist
				//Antes de crear la entidad le asigna una fecha 
			//	public void prePersist() {
			//		fecha = new Date();
			//	}
	2.- Se modifica form.component.html, se crea nuevo campo en el formulario de tipo date
		    <div class="form-group row">
              <label for="fecha" class="col-form-label col-sm-2">fecha:</label>
              <div class="col-sm-6">
                  <input type="date" class="form-control" [(ngModel)]="cliente.fecha" name="fecha" >
              </div>
            </div>
    3.- Se coonfigura locale en el backen, se modifica application.properties
    	3.1.- Se configura el timeZone que maneja el api jackson para trabajar con json
    		#Configuracion del locale
			spring.jackson.time-zone = America/Argentina/Buenos_Aires
			spring.jackson.locale=es-AR
93.- ****************************************************************************************Añadiendo datePicker de Angular Material
	1.- Se añade la libreria de angular Material por consola desde la raiz del proyecto
		ng add @angular/material
Seccion 13: Subida de imagen*********************************************************************************************************
94.- ************************************************************************************************Upload en el Api Rest Controller
*******************************************************************************************Se Configura el tamaño maximo de la imagen
	1.- Se modifica entidad Cliente.java
		1.1.- Se le añade atributo foto con su get y set
			private String foto;
			public String getFoto() {
				return foto;
			}
			public void setFoto(String foto) {
				this.foto = foto;
			}
	2.- Se modifica ClienteRestController.java, se crea metodo para capturar la imagen del cliente
			//Metodo que obtiene el file del cliente
			@PostMapping("/clientes/upload")
			private ResponseEntity<?> uploadArchivo(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Integer idCliente){
				Map<String, Object> response = new HashMap<>();
				Cliente cliente = clienteServ.findById(idCliente);
				//Se valida si el archivo esta vacio
				if(!archivo.isEmpty()) {
					//Se recupera el nombre del archivo
					String nombreArchivo = UUID.randomUUID().toString() + "_" +archivo.getOriginalFilename().replace(" ", " ");
					//Se recupera ruta completa 
					Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
					//Se copia el archivo en la ruta
					try {			
						Files.copy(archivo.getInputStream(), rutaArchivo);
					} catch (IOException e) {
						response.put("mensaje", "Error al subir la imagen del cliente: "+nombreArchivo);
						response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
						return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					cliente.setFoto(nombreArchivo);
					clienteServ.save(cliente);
					response.put("cliente", cliente);
					response.put("mensaje", "Has subido correctamente la imagen: "+nombreArchivo);
				}
				
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			}
	3.- Se modifica el archivo application.properties, se configura maximo de tamaño del archivo a subir
		#Configuracion del maximo de tamaño de los archivos a subir
		spring.servlet.multipart.max-file-size= 10MB
		spring.servlet.multipart.max-request-size=10MB
	4.- Se modifica ClienteRestController.java
		4.1.- Se modifica el metodo uploadArchivo(), para que borre la foto anterior
			//Se borra foto anterior para que no quede almacenada
			String fotoAnterior = cliente.getFoto();
			if(fotoAnterior != null && fotoAnterior.length() > 0) {
				Path rutaAnterior = Paths.get("uploads").resolve(fotoAnterior).toAbsolutePath();
				File archivoAnterior = rutaAnterior.toFile();
				if(archivoAnterior.exists() && archivoAnterior.canRead()) {
					archivoAnterior.delete();
				}
			}
		4.2.- Se modifica el metodo delete(), para que borrela foto antes de eliminar el cliente
			Cliente cliente = clienteServ.findById(id);
			//Se borra foto para que no quede almacenada
			String fotoAnterior = cliente.getFoto();
			if(fotoAnterior != null && fotoAnterior.length() > 0) {
				Path rutaAnterior = Paths.get("uploads").resolve(fotoAnterior).toAbsolutePath();
				File archivoAnterior = rutaAnterior.toFile();
				if(archivoAnterior.exists() && archivoAnterior.canRead()) {
					archivoAnterior.delete();
				}
			}
97.- ********************************************************************Se añade metodo para descargar foto en el navegador(backend)
 	1.- Se modifica clase ClienteRestController.java, se crea metodo que descarga imagen en el navegador
 			@GetMapping("/uploads/img/{nombreFoto:.+}")
			public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
				
				Path ruta = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
				Resource recurso = null;
				
				try {
					recurso = new UrlResource(ruta.toUri());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Se valida si existe y si se puede leer el recurso
				if(recurso.exists() && !recurso.isReadable()) {
					throw new RuntimeException("No se pudo cargar la imagen: "+nombreFoto);
				}
				//Se pasa el attachmen para que se pueda descargar el archivo
				HttpHeaders cabecera = new HttpHeaders();
				cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+"\"");
				
				return new ResponseEntity<Resource>(recurso,cabecera, HttpStatus.OK);
			}
	2.- Se prueba la descarga de imagen
		2.1.- Con postman se llama al metodo uploads y se sube una imagen en el body, se copia el nombre que genero de la imagen
			localhost:8080/api/clientes/upload
		2.2.- Desde el explorador se copia la ruta completa  mas el nuombre del archivo
			Ej: http://localhost:8080/api/uploads/62a1cbf2-faf6-4067-9637-fbaf4fe3e26c_CapturaArg.PNG
98.- ********************************************************************************************************Añadir Logger en backend
	1.- Se modifica ClienteRestController.java
		1.1.- Se añade atributo para el log
			private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
		1.2.- Se modifica metodo uploads() y verFoto() para añadir al log la ruta en la que se esta modificando
			log.info(ruta.toString());
Subida de foto en el FronEnd*********************************************************************************************************
99.- **********************************************************************Añadiendo componente detalle para el upload en el FrontEnd
	1.- se genera nuevo componente dentro del folder cliente
		ng g c clientes/detalle
		1.1.- Se elimina el archivo spec.ts porque no se utilizara
	2.- Se modifica detalle.component.ts 
		2.1.- Se cambia el selector a selector: 'detalle-cliente'
		2.2.- Se importan las clases necesarias
			import {Cliente} from '../cliente';
			import {ClienteService} from '../cliente.service';
			import {ActivatedRoute} from '@angular/router';
		2.3.- Se crea atributo cliente y titulo en la clase
			cliente: Cliente;
			titulo: string ='Detalle del Cliente';
		2.4.- Se realiza inyeccion de dependencias
			constructor(private clienteService: ClienteService,
              private activatedRoute: ActivatedRoute) { }
		2.5.- Se modifica el metodo ngOnInit()
			ngOnInit(): void {
			    this.activatedRoute.paramMap.subscribe(params =>{
			      let id: number = +params.get('id');
			      if(id){
			        this.clienteService.getCliente(id).subscribe(cliente =>{
			          this.cliente = cliente;
			        });
			      }
			    });
			  }
	3.- Se modifica detalle.component.html
		<div class="card bg-dark text-white">
		  <div class="card-header">{{titulo}}</div>
		  <div class="card-body">
		    <div class="input-group">
		      <div class="custom-file">
		        <input (change)="seleccionarFoto($event)" type="file" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04">
		        <label class="custom-file-label" for="inputGroupFile04">Seleccionar Foto</label>
		      </div>
		      <div class="input-group-append">
		        <button (click)="subirFoto()" class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">Subir Foto</button>
		      </div>
		    </div>
		  </div>
		</div>
		  

	4.- Se modifica app.module.ts, se le agrega la ruta
		  //ruta para el upload de la imagen
		  {path: 'clientes/ver/:id', component: DetalleComponent}
	5.- Se modifica clientes.component.html
		<td><button type="button" [routerLink]="['/clientes/ver', cliente.id]" class="btn btn-success btn-sm">{{cliente.id}}</button></td>
100.- *************************************************************************Implementacion de la subida del archivo en el frontend
	1.- Se modifica cliente.ts, se le añade atributo foto
		foto: string;
	2.- Se modifica clase de sevicio clientes.service.ts, se cre metodo para subir la foto
		  subirFoto(archivo: File, id): Observable<Cliente>{
		    //Se crea objeto tipo FormData, es equivalente al multipart-FormData
		    let formData = new FormData();
		    formData.append("archivo", archivo);
		    formData.append("id", id);
		    return this.http.post(`${this.urlEndPoint}/upload`,formData).pipe(
		      map((response: any) => response.cliente as Cliente),
		      catchError(e => {
		        console.error(e.error.mensaje);
		        Swal.fire('Error al subir la imagen.', e.error.mensaje, 'error');
		        return throwError(e);
		      })
		    );
		  }
	3.- Se modifica detalle.component.html
		3.1.- se le agrega evento change al input, ejecuta el metodo seleccionarFoto() cuando se carga el archivo
			<input (change)="seleccionarFoto($event)" type="file" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04">
    	3.2.- Se agrega evento click al boton
    		<button (click)="subirFoto()" class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">Subir Foto</button>
    4.- Se modifica detella.component.ts
    	4.1.- Se crea metodo seleccionarFoto()
    		 seleccionarFoto(event){
			    this.fotoSeleccionada = event.target.files[0];
			    console.log(this.fotoSeleccionada);
			  }
		4.2.- Se crea metodo subirFoto()
			//metodo que llama al metodo subirFoto de la clase de servicio
			  subirFoto(){
			    this.clienteService.subirFoto(this.fotoSeleccionada, this.cliente.id)
			    .subscribe(
			      cliente => {
			        this.cliente = cliente;
			        swal.fire('La foto se subido', `La foto se ha subido con exito: ${this.cliente.foto}`, 'success');
			      }
			    )
			  }
101.- *****************************************************************************Añadir la foto y el detalle del cliente en angular
	1.- Se modifica detalle.component.html
		<div class="card bg-dark text-white">
		  <div class="card-header">{{titulo}}</div>
		  <div class="card-body">
		    <div class="container">
		      <div class="row">
		        <div class="col-sm">
		          <ul *ngIf="cliente" class="list-group text-dark mb-3">
		            <li class="list-group-item active">{{cliente.nombre}}</li>
		            <li class="list-group-item">{{cliente.apellido}}</li>
		            <li class="list-group-item">{{cliente.email}}</li>
		            <li class="list-group-item">{{cliente.fecha | date:"fullDate"}}</li>
		          </ul>
		          <div class="input-group">
		            <div class="custom-file">
		              <input (change)="seleccionarFoto($event)" type="file" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04">
		              <label class="custom-file-label" for="inputGroupFile04">Seleccionar Foto</label>
		            </div>
		            <div class="input-group-append">
		              <button (click)="subirFoto()" class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">Subir Foto</button>
		            </div>
		          </div>
		        </div>
		        <div class="col-sm">
		        	<img *ngIf="cliente?.foto" src="http://localhost:8080/api/uploads/img/{{cliente.foto}}" alt="{{cliente.foto}}" class="img-thumbnail rounded">
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
102.- ********************************************************************************************Validacion de imagen antes de subir
	1.- Se modifica detalle.component.ts, se modifica el metodo subirFoto(), para vaidar que se seleccione un archivo para subirFoto
		  subirFoto(){
	    //Se valida si selecciono un archivo
	    if(!this.fotoSeleccionada){
	      swal.fire('Error Upload', 'Debe seleccionar una foto','error')
	    }else{
	      this.clienteService.subirFoto(this.fotoSeleccionada, this.cliente.id)
	      .subscribe(
	        cliente => {
	          this.cliente = cliente;
	          swal.fire('La foto se subido', `La foto se ha subido con exito: ${this.cliente.foto}`, 'success');
	        }
	      )
	    }
	  }	
		2.1.- Se modifica metodo seleccionarFoto(), para que valide que el archivo sea de tipo imagen
			 seleccionarFoto(event){
			    this.fotoSeleccionada = event.target.files[0];
			    console.log(this.fotoSeleccionada);
			    //Validacion para que el archivo sea de tipo imagen
			    if(this.fotoSeleccionada.type.indexOf('image') < 0 ){
			      swal.fire('Error al seleccionar el archivo', 'El archivo debe ser de tipo imagen', 'error');
			      //Se setea a null para que no pueda ser enviado
			      this.fotoSeleccionada = null;
			    }
			  }	
103.- ****************************************************************************************************Añadiendo Barra de progreso
	1.- Se modifica el archivo cliente.service.ts
		1.1.- Se importan librerias de HttpRequest y HttpEvent
			import { HttpClient, HttpHeaders, HttpRequest, HttpEvent  } from '@angular/common/http';
		1.2.- Se modifica metodo subirFoto()
			  subirFoto(archivo: File, id): Observable<HttpEvent<{}>>{
			    //Se crea objeto tipo FormData, es equivalente al multipart-FormData
			    let formData = new FormData();
			    formData.append("archivo", archivo);
			    formData.append("id", id);

			    const req = new HttpRequest('POST', `${this.urlEndPoint}/upload`,formData, {
			      reportProgress: true
			    });

			    return this.http.request(req);
			  }
	2.- Se modifica detalle.component.ts
		2.1.- Se importa libreria HttpEventType
			import {HttpEventType} from '@angular/common/http';
		2.2.- Se agrega atributo para el progreso en la clase
			progreso: number = 0;
		2.3.- Se modifica metodo subirFoto(), para añadir el evento del progreso;	
			  subirFoto(){
			    //Se valida si selecciono un archivo
			    if(!this.fotoSeleccionada){
			      swal.fire('Error Upload', 'Debe seleccionar una foto','error')
			    }else{
			      this.clienteService.subirFoto(this.fotoSeleccionada, this.cliente.id)
			      .subscribe(
			        event => {
			          if(event.type === HttpEventType.UploadProgress){
			            this.progreso = Math.round((event.loaded/event.total)*100);
			          }else if(event.type === HttpEventType.Response){
			            let response: any = event.body;
			            this.cliente = response.cliente as Cliente;
			            swal.fire('La foto se ha subido', response.mensaje, 'success');
			          }
			        }
			      )
			    }
			  }
		2.4.- Se modifica metodo seleccionarFoto(), para inizializar progreso
			 seleccionarFoto(event){
		    this.fotoSeleccionada = event.target.files[0];
		    this.progreso = 0;
		    console.log(this.fotoSeleccionada);
		    //Validacion para que el archivo sea de tipo imagen
		    if(this.fotoSeleccionada.type.indexOf('image') < 0 ){
		      swal.fire('Error al seleccionar el archivo', 'El archivo debe ser de tipo imagen', 'error');
		      //Se setea a null para que no pueda ser enviado
		      this.fotoSeleccionada = null;
		    }
		  }
	3.- Se modifica detalle.component.html, se le añade barra de progreso
		<div *ngIf="progreso > 0" class="progress">
            <div class="progress-bar progress-bar-striped" role="progressbar" [ngStyle]="{width:progreso+'%'" attr.aria-valuenow="{{progreso}}" aria-valuemin="0" aria-valuemax="100"></div>
          </div>
104.- *****************************************************************************Implementando modal como componente anidado (hijo)
	1.- Se modifica app.module.ts, se comenta la ruta para el upload
		 //{path: 'clientes/ver/:id', component: DetalleComponent}
	2.- Se modifica clientes.component.html, se añade selector de detalle.component.ts y se realiza inyeccion de cliente
		<detalle-cliente [cliente]="clienteSeleccionado"></detalle-cliente>
		2.1.- Se elmina routerLink del cliente id y se agrega evento click que ejecuta metodo abrirModal()
			<td><button type="button" (click)="abrirModal(cliente)" class="btn btn-success btn-sm">{{cliente.id}}</button></td>
	3.- Se modifica detalle.component.ts, se añade Imput al atributo cliente
		3.1.- Se importa la libreria Imput de angular/core
			import { Component, OnInit, Input } from '@angular/core';
		3.2.- Se añade anotacion al atributo cliente
			  @Input() cliente: Cliente;
		3.3..- Se comenta el proceso dentro del ngOnInit(), no se buscara el cliente por la ruta
			ngOnInit(): void {
		    /* this.activatedRoute.paramMap.subscribe(params =>{
		      let id: number = +params.get('id');
		      if(id){
		        this.clienteService.getCliente(id).subscribe(cliente =>{
		          this.cliente = cliente;
		        });
		      }
		    }); */
		  }
	4.- Se modifica clientes.component.ts
		4.1.- Se añade atributo para el uploads			
		  //Atributo para el upload
		  clienteSeleccionado: Cliente;
		4.2.- Se crea metodo abrirModal
			 abrirModal(cliente: Cliente){
			    this.clienteSeleccionado = cliente;
			  }
105.- **********************************************************************************************Convertiendo a Modal de Bootstrap
	1.- Se genera una clase de servicio Modal
		mg g s clientes/detalle/modal
		1.1.- Se crea atributo booleano
			modal: boolean= false;
		1.2.- Se crea metodos para cambiar la bandera de abrirModal() y cerrarModal()
			  abrirModal(){
			    this.modal = true;
			  }

			  cerrarModal(){
			    this.modal = false;
			  }
	2.- Se modifica cliente.component.ts
		2.1.- Se crea metodo abrirModal()
			abrirModal(cliente: Cliente){
			    this.clienteSeleccionado = cliente;
			    this.modalService.abrirModal();
			}
		2.2.- Se importa la clase de servicio modal.service.ts
			import { ModalService } from './detalle/modal.service'
		2.3.- Se realiza la inyeccion de dependencia de la clase de servicio
			 constructor(private clienteService: ClienteService, 
              private activatedRoute: ActivatedRoute,
              public modalService: ModalService) { }
	3.- Se modifica detalle.component.ts
		3.1.- Se importa clase de servicio, se retira la importacion del ActivatedRoute ya que no se utiliza 
			import {ModalService} from './modal.service';
		3.2.- Se realiza inyeccion de dependencia de la clase de servicio, se elimina la inyeccion de dependencia de ActivatedRoute
			constructor(private clienteService: ClienteService,
              private modalService: ModalService) { }
		3.3.- Se crea metodo cerrarModal()
			  cerrarModal(){
			    this.modalService.cerrarModal();
			    this.fotoSeleccionada = null;
			    this.progreso = 0;
			  }
	4.- Se modifica detalle.component.html, se cambia a obj model
		<div *ngIf="modalService.modal" class="modal" tabindex="-1" style="display: block;">
		  <div class="modal-dialog modal-dialog-centered modal-lg">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h3 class="modal-title">{{titulo}}</h3>
		        <button (click)="cerrarModal()" type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <div class="container ">
		          <div class="row">
		            <div class="col-sm">
		              <ul *ngIf="cliente" class="list-group text-dark mb-3">
		                <li class="list-group-item active">{{cliente.nombre}}</li>
		                <li class="list-group-item">{{cliente.apellido}}</li>
		                <li class="list-group-item">{{cliente.email}}</li>
		                <li class="list-group-item">{{cliente.fecha | date:"fullDate"}}</li>
		              </ul>
		              <div class="input-group mb-3">
		                <div class="custom-file">
		                  <input (change)="seleccionarFoto($event)" type="file" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" value="Buscar">
		                  <label class="custom-file-label" for="inputGroupFile04">Seleccionar Foto</label>
		                </div>
		                <div class="input-group-append">
		                  <button (click)="subirFoto()" [disabled]="!fotoSeleccionada" class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">Subir Foto</button>
		                </div>
		              </div>
		              <div *ngIf="progreso > 0" class="progress" style="height: 40px;">
		                <div class="progress-bar progress-bar-striped" role="progressbar" [ngStyle]="{width:progreso+'%'}" attr.aria-valuenow="{{progreso}}" aria-valuemin="0" aria-valuemax="100">
		                  {{progreso}}%
		                </div>
		              </div>
		            </div>
		            <div class="col-sm">
		              <img *ngIf="cliente?.foto" src="http://localhost:8080/api/uploads/img/{{cliente.foto}}" alt="{{cliente.foto}}" class="img-thumbnail rounded">
		            </div>
		          </div>
		        </div>
		      </div>
		      <div class="modal-footer">
		        /*<button type="button" (click)="cerrarModal()" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
		      </div>
		    </div>
		  </div>
		</div>*/
106.- ***************************************************************************Añadiendo estilos y animacion de difuminado al Modal
	1.- Se modifica detalle.component.css, se crea clase para el difuminado
		.abrir-modal{
		  /*Transparecia de color negra*/
		  background-color: rgba(0, 0, 0, 0.7);
		  /* Se posociona el backgroun de forma fija */
		  position: fixed;
		  /* Comienza en la esq superior izq */
		  top: 0px;
		  left: 0px;
		  /* Con ancho y alto, toda la pantalla*/
		  width: 100%;
		  height: 100%;
		  /* Se posiciona al frente de los demas elementos */
		  z-index: 1000;
		}


		.animacion{
		  /* duracion */
		  animation-duration: 4s;
		  /* modo de la animacion */
		  animation-fill-mode: both;
		  /* Compatibilidad con firefox */
		  -webkit-animation-duration: 4s;
		  -webkit-animation-fill-mode: both;
		}

		.fadeIn{
		  animation-name: fadeIn;
		  -webkit-animation-name: fadeIn;
		}

		/* Fotograma clave para la animacion */
		@keyframes fadeIn{
		  0% {opacity: 0;}to{opacity: 2;}
		}
		@-webkit-keyframes fadeIn{
		  0% {opacity: 0;}to{opacity: 2;}
		}

	2.- Se modifica detalle.component.html, se crea un div que envuelve todos los componentes y  se le aplica la clase css creada,
		se desplaza el ngIf al div padre
		<div class="abrir-modal animacion fadeIn" *ngIf="modalService.modal">...</div>
107.- ******************************************************************************************************Añadir foto en el listado
	1.- Se modifica clientes.component.html, se reemplaza el id por la imagen
		<td><img *ngIf="cliente?.foto" (click)="abrirModal(cliente)"
          src="http://localhost:8080/api/uploads/img/{{cliente.foto}}" alt="{{cliente.foto}}"
          class="img-thumbnail rounded" style="width: 64px; cursor: pointer;">
          <img *ngIf="!cliente?.foto" (click)="abrirModal(cliente)"
          src="http://localhost:8080/images/noUser.png" alt="Sin Imagen"
          class="img-thumbnail rounded" style="width: 64px; cursor: pointer;">
        </td>
    2.- Se descarga icono default para cuando no halla foto
    	https://www.iconfinder.com/search/?q=no%20user&price=free&size=128
    3.- Se crea carpeta image en recursos del backend
    	3.1.- Se copia imagen descargada en la carpeta creada
    4.- Se modifica ClienteRestController.java, el metodo verFoto, para que añada la foto por defecto en caso de no encontrar la imagen
    	//Se valida si existe y si se puede leer el recurso
		if(recurso.exists() && !recurso.isReadable()) {
			ruta = Paths.get("src/main/resources/static/images").resolve("noUser.png").toAbsolutePath();
			try {
				recurso = new UrlResource(ruta.toUri());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.error("No se pudo cargar la imagen: "+nombreFoto);
		}
108.- **********************************************************************Actualizacion de foto en lista implementando eventEmitter
	1.- Se modifica modal.service.ts
		1.1.- Se importa la libreria eventEmitter
			import { Injectable, EventEmitter } from '@angular/core';
		1.2.- Se crea atributo privado con su acceso get
			private _notificarUpload= new EventEmitter<any>();

			get notificarUpload(): EventEmitter<any>{
			    return this._notificarUpload;
			  }

	2.- Se modifica detalle.component.ts
		2.1.- Se modifica metodo subirFoto(), para avisar que hubo un cambio en el  cliente
			//Se notifica el cambio del cliente
            this.modalService.notificarUpload.emit(this.cliente);
    3.- Se modifica clientes.component.ts, se modifica el metodo ngOnInit(), se agrega la suscripcion del evento
    	 this.modalService.notificarUpload.subscribe(cliente =>{
	        this.clientes = this.clientes.map(clienteOriginal =>{
	          if(cliente.id == clienteOriginal.id){
	            clienteOriginal.foto = cliente.foto;
	          }
	          return clienteOriginal;
	        })
	      })
109.- ******************************************************Creacion clase de servicio UploadFileService en el backend para optimizar
	1.- Se cre una interface IUploadFileService
		1.1.- Se crean los metodos abstractos
			public interface IUploadFileService {
				public Resource cargar(String nombreFoto) throws MalformedURLException;
				public String copiar(MultipartFile archivo) throws IOException;
				public boolean eliminar(String nombreFoto);
				public Path getPath(String nombreFoto);
			}
	2.- Se crea clase de servicio UploadServiceImpl
		2.1.- Se crea atributo para el log4j
			private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
		2.2.- Se crea contante para el directorio de las fotoSeleccionada
			private final static String DIRECTORIO_UPLOAD = "uploads";
		2.3.- Se implementa el metodo cargar()
			@Override
			public Resource cargar(String nombreFoto) throws MalformedURLException {
				Path ruta = getPath(nombreFoto);
				log.info(ruta.toString());
				Resource recurso = new UrlResource(ruta.toUri());
				
				//Se valida si existe y si se puede leer el recurso
				if(recurso.exists() && !recurso.isReadable()) {
					ruta = Paths.get("src/main/resources/static/images").
							resolve("noUser.png").toAbsolutePath();
					recurso = new UrlResource(ruta.toUri());

					log.error("No se pudo cargar la imagen: "+nombreFoto);
				}
				return recurso;
			}
		2.4.- Se implementa el metodo copiar()
			@Override
			public String copiar(MultipartFile archivo) throws IOException {
				//Se recupera el nombre del archivo
				String nombreArchivo = UUID.randomUUID().toString() + "_" +archivo.getOriginalFilename().replace(" ", " ");
				//Se recupera ruta completa 
				Path rutaArchivo = getPath(nombreArchivo);
				log.info(rutaArchivo.toString());
				//Se copia el archivo en la ruta		
				Files.copy(archivo.getInputStream(), rutaArchivo);
				
				return nombreArchivo;
			}
		2.5.- Se implementa el metodo eliminar()
			@Override
			public boolean eliminar(String nombreFoto) {
				if(nombreFoto != null && nombreFoto.length() > 0) {
					Path rutaAnterior = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
					File archivoAnterior = rutaAnterior.toFile();
					if(archivoAnterior.exists() && archivoAnterior.canRead()) {
						archivoAnterior.delete();
						return true;
					}
				}
				return false;
			}
		2.6.- Se implemenat el metodo getPath()
			@Override
			public Path getPath(String nombreFoto) {
				// TODO Auto-generated method stub
				return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
			}
	3.- Se modifica ClienteRestController.java, se implementa la clase de servicio
		3.1.- Se crea inyeccion de dependencia de la interface IUploadFileService
			@Autowired
			private IUploadFileService uploadService;
		3.2.- Se modifica el metodo uploadArchivo()


			//Metodo que obtiene el file del cliente
			@PostMapping("/clientes/upload")
			private ResponseEntity<?> uploadArchivo(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Integer idCliente){
				Map<String, Object> response = new HashMap<>();
				Cliente cliente = clienteServ.findById(idCliente);
				//Se valida si el archivo esta vacio
				if(!archivo.isEmpty()) {
					String nombreArchivo=null;
					try {			
						nombreArchivo = uploadService.copiar(archivo);
					} catch (IOException e) {
						response.put("mensaje", "Error al subir la imagen del cliente");
						response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
						return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					//Se borra foto anterior para que no quede almacenada
					String fotoAnterior = cliente.getFoto();
					
					uploadService.eliminar(fotoAnterior);
					cliente.setFoto(nombreArchivo);
					clienteServ.save(cliente);
					response.put("cliente", cliente);
					response.put("mensaje", "Has subido correctamente la imagen: "+nombreArchivo);
				}
				
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			}
		3.3.- Se modifica metodo verFoto()
			@GetMapping("/uploads/img/{nombreFoto:.+}")
			public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
				
				Resource recurso = null;
				
				try {
					recurso = uploadService.cargar(nombreFoto);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				
				//Se pasa el attachmen para que se pueda descargar el archivo
				HttpHeaders cabecera = new HttpHeaders();
				cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+"\"");
				
				return new ResponseEntity<Resource>(recurso,cabecera, HttpStatus.OK);
			}
		3.4.- Se modifica metodo delete()
				public ResponseEntity<?> delete(@PathVariable Integer id) {
					Map<String, Object> response = new HashMap<>();
					try {
						Cliente cliente = clienteServ.findById(id);
						//Se borra foto para que no quede almacenada
						String fotoAnterior = cliente.getFoto();
						uploadService.eliminar(fotoAnterior);
						clienteServ.delete(id);
					} catch (DataAccessException e) {
						//Se crean mensajes de error y se añaden all map
						response.put("mensaje", "Error al eliminar el cliente con ID ".concat(id.toString()).concat(" en la Base de Datos"));
						response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
						//Se crea respuesta enviando los mensajes del error y el estatus
						return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
					}
					
					response.put("mensaje", "El Cliente ha sido eliminado con exito");
					
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}
Seccion 14: Agregando campo select en el formulario y relacion de tablas*************************************************************
111.- ********************************************************************************************Creacion de entidad Region(Backend)
	1.- Se crea clase de entidad Region, se implementa de Serializable, se le agregan las anotaciones y los atributos con su acceso
		@Entity
		@Table(name = "regiones")
		public class Region implements Serializable{

			private static final long serialVersionUID = 1L;
			
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
	2.- Se modifica Cliente.java, se le añade la relacion con la entidad Region
		2.1.- Se crea atributo de tipo Region con sus anotaciones
			@NotNull(message = "La region no puede ser vacia.")
			@ManyToOne
			@JoinColumn(name = "region_id")
			@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
			private Region region;

		2.2.- Se crean los metodos de acceso del atributo
			public Region getRegion() {
				return region;
			}
			public void setRegion(Region region) {
				this.region = region;
			}	
	3.- Se modifica Repositorio IClientesRepository, se añade consulta a regiones
		@Query("from Region")
		public List<Region> findAllRegiones();
112.- ***********************************************************************Añadir las regiones a la clase de servicio y controlador
	1.- Se modifica la interface IClienteService, se le agrega el metodo abstracto;
		public List<Region> findAllRegiones();
	2.- Se modifica ClienteServiceImp, para implementar el metodo findAllRegiones()
		@Override
		@Transactional(readOnly = true)
		public List<Region> findAllRegiones() {
			return clienteRepo.findAllRegiones();
		}
	3.- Se modifica ClienteRestController, se agrega metodo para recuperar las regiones
		@GetMapping("/clientes/regiones")
		public List<Region> listaRegiones(){
			return clienteServ.findAllRegiones();
		}
		3.1.- Se modifica metodo updateCliente(), para setear la region
			clienteActual.setRegion(cliente.getRegion());
113.- **********************************************************************************Se prueba en postaman los cambios del backend
	1.- Se modifica el insert.sql, Se insertan registros en la tabla regiones
		INSERT INTO regiones (id, nombre) VALUES (1, 'Sudamérica');
		INSERT INTO regiones (id, nombre) VALUES (2, 'Centroamérica');
		INSERT INTO regiones (id, nombre) VALUES (3, 'Norteamérica');
		INSERT INTO regiones (id, nombre) VALUES (4, 'Europa');
		INSERT INTO regiones (id, nombre) VALUES (5, 'Asia');
		INSERT INTO regiones (id, nombre) VALUES (6, 'Africa');
		INSERT INTO regiones (id, nombre) VALUES (7, 'Oceanía');
		INSERT INTO regiones (id, nombre) VALUES (8, 'Antártida');
		1.1.- Se modifican los insert a la tabla clienteServ
			/* Populate tabla clientes */
			INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(1, 'Andrés', 'Guzmán', 'profesor@bolsadeideas.com', '2018-01-01');
			INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(2, 'Mr. John', 'Doe', 'john.doe@gmail.com', '2018-01-02');
			INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(4, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2018-01-03');
			INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(4, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2018-01-04');
			INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(4, 'Erich', 'Gamma', 'erich.gamma@gmail.com', '2018-02-01');
			INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(3, 'Richard', 'Helm', 'richard.helm@gmail.com', '2018-02-10');
			INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(3, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2018-02-18');
			INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(3, 'John', 'Vlissides', 'john.vlissides@gmail.com', '2018-02-28');
			INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(3, 'Dr. James', 'Gosling', 'james.gosling@gmail.com', '2018-03-03');
			INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(5, 'Magma', 'Lee', 'magma.lee@gmail.com', '2018-03-04');
			INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(6, 'Tornado', 'Roe', 'tornado.roe@gmail.com', '2018-03-05');
			INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(7, 'Jade', 'Doe', 'jane.doe@gmail.com', '2018-03-06');
	2.- Se prueba en postman el metodo get que recupera la lista de clientes y el metodo get que recupera las regiones
		localhost:8080/api/clientes
		localhost:8080/api/clientes/regiones
114.- ************************************************************************(FrontEnd)Se crea la clase entidad Region en TypeScript
	1.- Se crea dentro de clientes, la clase Region.ts
		export class Region{
		  id: number;
		  nombre: string;
		}
	2.- Se modifica la clase Cliente.ts, se le agrega el atributo correspondiente a Region
		2.1.- Se importa la clase Region
			import {Region} from './region';
		2.2.- Se agrega atributo region
			region: Region;
	3.- Se modifica detalle.component.html, se le agrega el campo a la lista correspondiente a region
		<li class="list-group-item">{{cliente.region.nombre}}</li>
    4.- Se modifica la calse de servicio cliente.service.ts, se crea metodo para que recupere las regiones
    	getRegiones(): Observable<Region[]>{
		    return this.http.get<Region[]>(this.urlEndPoint+'/regiones');
		  }  
		4.1.- Se importa la clase Region
			import {Region} from './region';  
	5.- Se modifica form.component.ts, se crea metodo que recupera las regiones
		5.1.- Se crea metodo cargarRegiones(), para cargar las regiones
			cargarRegiones(): void{
			    this.clienteService.getRegiones().subscribe(regiones => this.regiones = regiones)
			  } 
		5.2.- Se modifica el metodo ngOnInit(), se llama al metodo cargarRegiones()
			  ngOnInit(): void {
			    //Se llama al metodo cargar cliente
			    this.cargarCliente();
			    this.cargarRegiones();
			  }  
		5.3.- Se crea atributo en la clase regiones
			regiones: Region[];
		5.4.- Se importa la clase Region
			import {Region} from './region';
115.- ********************************************************************************************Se añade campo select al formulario
	1.- Se modifica form.componetn.html, se le agrega campo select correspondiente a regiones
	    <div class="form-group row">
          <label for="region" class="col-form-label col-sm-2">Region:</label>
          <div class="col-sm-6">
              <select class="form-control" [(ngModel)]="cliente.region" name="region" style="width:500px">
                <option *ngFor="let region of regiones" [ngValue]="region">{{region.nombre}}</option>
              </select>
            </div>
        </div>
116.- *******************************************************************************Marcar como seleccionada una region en el select
	1.- Se modifica form.component.html, se le añade atributo directiva [compareWith]
		<select [compareWith]="comparaRegion" class="form-control" [(ngModel)]="cliente.region" name="region" style="width:500px">
	        <option *ngFor="let region of regiones" [ngValue]="region">{{region.nombre}}</option>
	      </select>
	2.- Se modifica form.component.ts, se crea metodo comparaRegion()
		 compararRegion(o1: Region, o2: Region): boolean {
		    if (o1 === undefined && o2 === undefined) {
		      return true;
		    }
		    return o1 === null || o2 === null || o1 === undefined || o2 === undefined ? false : o1.id === o2.id;
		  }
117.- **************************************************************************************Se añade una opcion por defecto al select
	1.- Se modifica form.component.html, se le añade una opcion por defecto al select 
		<option [ngValue]="undefined">--- Seleccionar una region ---</option>
Seccion 15: Authenticacion OAuth2 con JWT: Backend Spring****************************************************************************
	Referencias:
		https://www.baeldung.com/spring-security-oauth
121.- *******************************************************************************************Codificacion de Java Web Token (JWT)
	1.- Prueba: Se acede a la pagina de jwt.io, Se coloca en consola en una variable el token de ejemplo de la pagina
		1.1.- let token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODk3IiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ._kgs7yZHpOT55IuVQrLHM4hmC8lZlnbIn54ujUqQDa4"
		1.2.- Se utiliza el metodo split para cortar el token y acceder al arry posicion 1
			let payload = token.split(.)[1];
		1.3.- Se utiliza el metodo atob() de window para codificarlo
			window.atob(payload);
		1.4.- Se convierte en json
			JSON.parse(window.atob(payload));
121.- ************************************************************************************************************Añadir dependencias
	Se reviza en spring.io, spring security OUTH, se valida --> https://spring.io/projects/spring-security-oauth
	Se busca en mvnrepository 
			--> spring-security-oauth2  y 
			--> spring-security-jwt  
			--> si es mayor a java 8  tambien buscar 
			--> jaxb api y jaxb Runtime
	1.- Se instalan dependencias en el pom.xml
		<!-- https://mvnrepository.com/artifact/org.springframework.security.oauth/spring-security-oauth2 -->
		<dependency>
		    <groupId>org.springframework.security.oauth</groupId>
		    <artifactId>spring-security-oauth2</artifactId>
		    <version>2.5.0.RELEASE</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.springframework.security/spring-security-jwt -->
		<dependency>
		    <groupId>org.springframework.security</groupId>
		    <artifactId>spring-security-jwt</artifactId>
		    <version>1.1.1.RELEASE</version>
		</dependency>
122.- ***********************************************************************************************Creando entidades Usuarios y Rol
	1.- Se crea entidad role
		@Entity
		@Table(name="roles")
		public class Role implements Serializable{


			private static final long serialVersionUID = -2728258378812673040L;

			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id;
			
			@Column(unique = true,length = 20)
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
	2.- Se crea entidad Usuarios
		@Entity
		@Table(name="usuarios")
		public class Usuario implements Serializable{

			private static final long serialVersionUID = -7097901776416347581L;
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id;
			@Column(unique = true, length = 20)
			private String userName;
			@Column(length = 60)
			private String password;
			
			private Boolean enable;
			
			//Se le añade el cascade para que se elimine en cascda cuando sea eliminada la entidad
			@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
			@JoinTable(name = "usuarios_roles", 
			joinColumns = @JoinColumn(name="usuario_id"),
			inverseJoinColumns = @JoinColumn(name="role_id"), 
			//Se configura para que exista una key usuario rol igual
			uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id"})}	)
			private List<Role> roles;



			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				this.id = id;
			}

			public String getUserName() {
				return userName;
			}

			public void setUserName(String userName) {
				this.userName = userName;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}

			public Boolean getEnable() {
				return enable;
			}

			public void setEnable(Boolean enable) {
				this.enable = enable;
			}

			public List<Role> getRoles() {
				return roles;
			}

			public void setRoles(List<Role> roles) {
				this.roles = roles;
			}	
		}
123.- ********************************************************************************Creacion de repositorio JPA IUasuarioRepository
	1.- Creacion de Interface de repositorio IUasuarioRepository	
		public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

			public Usuario findByUsername(String username);
			
			@Query("SELECT u FROM Usuario u WHERE u.username = ?1")
			public Usuario findByUsername2(String username);
		}
124.- ********************************************************************************Creacion de la clase de servicio UsuarioService
	1.- El el paquete de clases de servcio se crea la clase UsuarioService.java
		1.1.- Se implementa interface  UserDetailsService
		1.2.- Se le agrega la anotacion @Service
		1.3.- Se implementa el metodo loadUserByUsername()
		1.4.- Se anota como @Transactional de springframework, se le agrega el atributo de solo lectura
			@Service
			public class UsuariosService implements UserDetailsService{
				
				@Autowired
				private IUsuarioRepository usuarioRepo;
				
				private Logger logger = LoggerFactory.getLogger(UsuariosService.class);
				
				@Override
				@Transactional(readOnly = true)
				public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
					Usuario usuario = usuarioRepo.findByUsername(username);
					
					if(usuario == null) {
						logger.error("Error en el login: no existe el usuario "+username+" en el sistema!");
						throw new UsernameNotFoundException("Error en el login: no existe el usuario \"+username+\" en el sistema!");
					}
					
					List<GrantedAuthority> authorities = usuario.getRoles()
							.stream()
							.map(role -> new SimpleGrantedAuthority(role.getNombre()))
							//Es como el operador tap de angular
							.peek(authority -> logger.info("Role: "+authority.getAuthority()))
							.collect(Collectors.toList());
					
					return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnable(), true, true, true, authorities);
				}

			}
125.- *******************************************************************************************Añadir la clase SpringSecurityConfig
	1.- Se crea paquete 
		package com.eareiza.springAngular.auth;
	2.- Se crea clase SpringSecurityConfig
		2.1.- Se anota con @Configuration, para indicar que es de configuracion y se extiende de la interface de seguridad WebSecurityConfigurerAdapter
			@Configuration
			public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{...}
		2.2.-  Se crea atributo UsuarioService
			@Autowired
			private UserDetailsService usurioService;
		2.3.- 	//Se registra en el authentication manager
			@Override
			@Autowired
			protected void configure(AuthenticationManagerBuilder auth) throws Exception {
				auth.userDetailsService(this.usurioService).passwordEncoder(passwordEncoder());
			}
		2.4.- Se crea metod de encriptacion 
			//Registra en el contenedor de spring lo retornado, queda disponible para inyeccion de dependencia de cualquier 
			//otro componente
			@Bean
			public BCryptPasswordEncoder passwordEncoder() {
				return new BCryptPasswordEncoder();
			}


			@Configuration
			public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
				
				@Autowired
				private UserDetailsService usurioService;

				//Se registra en el authentication manager
				@Override
				@Autowired
				protected void configure(AuthenticationManagerBuilder auth) throws Exception {
					auth.userDetailsService(this.usurioService).passwordEncoder(passwordEncoder());
				}
				
				@Bean
				public BCryptPasswordEncoder passwordEncoder() {
					return new BCryptPasswordEncoder();
				}
			}
126.- ********************************************************************************************************Actualizacion de la 121
	Se recomienda modificar el pom con las siguientes versiones
		<dependency>
			<groupId>org.springframework.security.oauth</groupId>
			<artifactId>spring-security-oauth2</artifactId>
			<version>2.3.4.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-jwt</artifactId>
			<version>1.0.9.RELEASE</version>
		</dependency>
127.- *****************************************************************************************Configuracion del authorization Server
	1.- En el paquete auth, se crea clase AuthorizationServerConfig, extiende de AuthorizationServerConfigurerAdapter
	2.- Se le añaden las notaciones @Configuration y @EnableAuthorizationServer
		@Configuration
		//habilitar un servidor de autorización (es decir, an AuthorizationEndpointy a TokenEndpoint) en el contexto de la aplicación actual,
		@EnableAuthorizationServer
		public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	3.- Se modifica AuthorizationServerConfig, se crean atrbutos, passwordEncoder y authenticationManager
		@Autowired
		private BCryptPasswordEncoder passworEncoder;
		
		@Autowired
		@Qualifier("authenticationManager")
		private AuthenticationManager authenticationManager;
		3.1.- Se implementan metodos de configuracion de la interface AuthorizationServerConfigurerAdapter
			@Override
			public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
				super.configure(security);
			}

			@Override
			public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
				super.configure(clients);
			}

			//Configuracion del endpoint del authorizationServer, se encarga de validar el token
			@Override
			public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
				super.configure(endpoints);
			}
		3.2 Se modifica la configuracion para el endPoint y se crean los metodos que necesita el tokenStore y el accessTokenConverter
			//Configuracion del endpoint del authorizationServer
			@Override
			public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
				//Se registra el authenticationManager
				endpoints.authenticationManager(authenticationManager)
				.tokenStore(tokenStore())
				//almacena los datos de authenticacion del token
				.accessTokenConverter(accessTokenConverter());
				
			}

			@Bean
			public  JwtTokenStore tokenStore() {
				return new JwtTokenStore(accessTokenConverter());
			}

			@Bean
			public JwtAccessTokenConverter accessTokenConverter() {
				JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
				return jwtAccessTokenConverter;
			}

			private AuthenticationManager authenticationManager;
	4.- Se modifica la clase SpringSecurityConfig.java, Se registra el AuthorizationManager como bean
		4.1.- Se abre en eclipse el modulo de override/implements methods
			alt+shift+s
		4.2.- Se selecciona el metodo authenticationManager() y se registra como @bean
			@Bean("authenticationManager")
			@Override
			protected AuthenticationManager authenticationManager() throws Exception {
				return super.authenticationManager();
			}
128.- **********************************************************************Configuracion del cliente aplicacion y acceso al endpoint
	1.- Se modifica AuthorizationServerConfig, se configura el metodo para ClientDetailsServiceConfigurer
			@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			//Se almacena en memoria el cliente con su clave
			clients.inMemory().withClient("angularapp")
			.secret(passwordEncoder.encode("12345"))
			//Se configura alcance del cliente para leectura y escritura
			.scopes("read","write")
			//Se configura el tipo de consesion del token, como se obtiene el toke --> password
			.authorizedGrantTypes("password", "refresh_token")
			//Configurar en cuanto tiempo caduca el token
			.accessTokenValiditySeconds(3600)
			//Tiempo de expiracion del refreshToken
			.refreshTokenValiditySeconds(3600);
		}
	2.- Se configura el AuthorizationServerSecurityConfigurer, se configuran los permisos para las rutas de accessTokenConverter
		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			//Genera el token
			security.tokenKeyAccess("permitAll()")
			//verifica el token y su firma
			.checkTokenAccess("isAuthenticated()");
		}
129.- *****************************************************************************************Configuracion del servidor de recursos
	Nota: se encarga del acceso a los recursos por parte del cliente, siempre que el token sea validado
	1.- Se crea la clase de configuracion ResourceServerConfig, para el manejo de recurso o peticiones por outh2
		@Configuration
		//permite que nuestra aplicación se comporte como un  servidor de recursos 
		@EnableResourceServer
		public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

			@Override
			public void configure(HttpSecurity http) throws Exception {
				//Se permite a todos el accesso por el metodo get a "/api/clientes"
				http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/clientes").permitAll()
				//Se valida que el cliente este autenticado
				.anyRequest().authenticated();
			}
			
		}
	2.- Se modifica SpringSecurityConfig, para el manejo de peticiones

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
			//Se valida que el cliente este autenticado
			.anyRequest().authenticated()
			.and()
			//Se deshabilita cors
			.csrf().disable()
			//Se configura para que manejo de sesiones sea sin estado
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
130.- ********************************************************************************************Prueba de autenticacion con POSTMAN
		1.- Se prueba metodo get que apunte a clientes, el mismo es de acceso publicos
			http://localhost:8080/api/clientes
		2.- Se prueba cuanquier otro metodo o url, debe solicitar token
		 	Post  --> http://localhost:8080/api/clientes
		2.- Obteniendo token JWT, con el metodo post
			2.1.- -->http://localhost:8080/oauth/token
			2.2.- en body marcamos tipo de contenido
				x-www-form-urlencoded
			2.3.- se agrega el username y password y el tipo de consesion
				Key 			value
				username 		andres
				password 		12345
				grant_type 		password
			2.4.- en authozation la pestaña, ponemos en type --> Basic Auth
			2.5.- se coloca username y password del aplicativo cliente
				username: angularapp 
				password: 12345
			2.6 se ejecuta y se valida que la respuesta sea 200, se copia el token del accesstoken en el body
				ej: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTk3NTc1ODUsInVzZXJfbmFtZSI6ImFuZHJlcyIsImF1dGhvcml0aWVzIjpbIlJP
					TEVfVVNFUiJdLCJqdGkiOiJjNWUyNDVkMy0yOWE2LTRkYmMtYWNkYy1kYzYzODUyNTBjN2QiLCJjbGllbnRfaWQiOiJhbmd1bGFyYXBwIiwic2Nvc
					GUiOlsicmVhZCIsIndyaXRlIl19.JPqvmTTwS4Nmho5EAfGfnRd4mVFRqAEdR4S1pdHfBN4
			2.6.- Se prueba recuperar un cliente con el metodo get --> http://localhost:8080/api/clientes/1
			2.7.- Authorization  --> type Bearer Tken, se coloca el token --> en el input token
			2.8.- se envia, se valida recuperar el cliente y el estatus 200
			2.9.- se prueba borrando un cliente con el metodo DELETE
				http://localhost:8080/api/clientes/2
131.- ***********************************************************************Asignacion de clave secreta MAC para firmar el token JWT
	1.- Se modifica AuthorizationServerConfig.java, se modifica el metodo accessTokenConverter(), para asignar clave mac en el token
			@Bean
			public JwtAccessTokenConverter accessTokenConverter() {
				JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
				jwtAccessTokenConverter.setSigningKey(JwtConfig.LLAVE_SECRETA);
				return jwtAccessTokenConverter;
			}
	2.- Se crea clase de configuracion que tendra la clave secreta del token
		package com.eareiza.springAngular.auth;

		public class JwtConfig {
			public static final String LLAVE_SECRETA = "alguna.clave.secreta.123456789"; 
		}
132.- *******************************************************************Creando y asignando certificado RSA para firmar el token JWT
	1.- Se descarga openssl en el SO desde --> https://wiki.openssl.org/index.php/Binaries
		1.1.- Se genera la clave
			openssl genrsa -out jwt.pem
		1.2.- Se muestra en pantalla la clave privada
			openssl rsa -in jwt.pem
		1.3.- Se muestra en pantalla la clave publica
			openssl rsa -in jwt.pem -pubout

			eareiza@SMSL162:~$ openssl genrsa -out jwt.pem
			Generating RSA private key, 2048 bit long modulus (2 primes)
			...............................................................+++++
			........+++++
			e is 65537 (0x010001)
			eareiza@SMSL162:~$ openssl rsa -in jwt.pem
			writing RSA key
			-----BEGIN RSA PRIVATE KEY-----
			MIIEowIBAAKCAQEAsgrRmKYWY0N4VBpxlGSnB0lD9wYGUUNo0nvj6YZx+sE7F7bT
			eWQAOfOSHxnNm2MPPjZhCOGxqs15eE/IJlVvyqvrLIv12qgMmO1Xo6wPzw5d6T51
			e55BniM3FX1+KirBngYFdkRtZOpqT7/1mCeuuY/QFXA04JJ8U7QUvcM+6TRMaWfU
			zgh0/yDeyVs2xxv1Chg02OHNJSNL9uX89DTECiWZIS+mXPhWhrvo6nEjsaI3P7c0
			ycf//7J4mVVceu1vxJD2Mqn3E4j5WC1SSAx1XJTMo2AhbyZLjAlon5iX7EBOKlXQ
			gdJaLMU0iuW/blwVCukYeC+PMgzLhzdEum2wDQIDAQABAoIBAF6rogepSP4/Qhx2
			cy6U1rvpGVqguXs8fOBvQCFMfA758JILhSdnJTerbGddMRcO9Wv/PlmaqP5Jg+2V
			frfjdi4ufD6TrYc3FHu8uH0vYLiYyxbbP10cKYgM8Z1bvVpuocWFLiJVAFZIz37Y
			c58UerPw5JSBtZw0SCe5+c1tpGYbpev3hJ9ESaQHwhBT76Mjx/pD0huMY6QkABID
			qKg7v9RAYsJNLaQY2OwH8ihZg+iDH3oj3QI6/fBXY9LBwG30n2EC3qL9FPBiBU6H
			+psU5tuXIe1eRzOQQVaRi6kPHhj22zQ4+KXk0ka79rFd2pT9A1c1lTLSONeE1YPZ
			N8TXg4ECgYEA2lsAO1rLWGCnz7Bd8A10PmSxh6J4zlP8I/U7sO4AoEp/iJUK8WDg
			aJV1O2oEsCfgZKdown1UAcX3YbMrMX36nSZ5UEzXHO9if63B5Ox8+AnQ/lLvJOcw
			/m+Ojg+BPjyEMd4uKKgtYh1W6v2mcQDC5BvJuM3oLTugL41yhcgO578CgYEA0Lyd
			x9QGghyU/Q4Dn6Z4nb3/Yu647Yjth/3gZ3DlkHjjfG2zhG6HYNwc2N8TCBexTvip
			9PUQFWo8O/eFAI8DLiOZphb8hS3a2xn77tz5ftkCWtubJj5xCKdFtbH7FqE9ubfo
			Uzr8PY75PMJ1hYh1hADOLjytjlIfLD/y2ncTuzMCgYEAmMXEssKCtVHi91fhUZxI
			MYNIBqrxNQLjzdC9s7vBqmSVOF13K4zpVaMEQ86YextbT3ElxbXkOd1liPDPjrAi
			0SLjDADHHNKMKuLKXz3q7awIkng6I6qxx8MLjW9hsMasgyt9RirsndIDRySy+Ctd
			MDYU+/zsrgEiwUKI1vszGocCgYAPyPO1lNcZJ8gsis7DV/dvFlslxB+/9sbfrdc2
			e1cg4cNyOWmF7kXWkU6tJ9Fsnz4NVsijeZTJycrcTg+Ex0vaRV7tLHfUKm+OehyP
			J67GZOrErC7DaZ1qphujbd4rC+57COlMwf0pOpKmgZ4ZhwQ8A0OUQ4psZNFuqzdW
			RgL7+QKBgGUtlOO3NDreainMPuh12JfpTUj74pMh8jGlZNL7bVRHodSK9hm9tI/H
			7BgB2MO1KKnRM9yq+6iL6IjHWoy1ws97XByl+jmRvfqdyvfxIU6hjnqQux+nsuEL
			gbbPfNIZWpuo2IKFn5ftXNsw8dtlcxjhiWFsMQKQ5XQ0J1Yj5XrD
			-----END RSA PRIVATE KEY-----
			eareiza@SMSL162:~$ openssl rsa -in jwt.pem -pubout
			writing RSA key
			-----BEGIN PUBLIC KEY-----
			MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsgrRmKYWY0N4VBpxlGSn
			B0lD9wYGUUNo0nvj6YZx+sE7F7bTeWQAOfOSHxnNm2MPPjZhCOGxqs15eE/IJlVv
			yqvrLIv12qgMmO1Xo6wPzw5d6T51e55BniM3FX1+KirBngYFdkRtZOpqT7/1mCeu
			uY/QFXA04JJ8U7QUvcM+6TRMaWfUzgh0/yDeyVs2xxv1Chg02OHNJSNL9uX89DTE
			CiWZIS+mXPhWhrvo6nEjsaI3P7c0ycf//7J4mVVceu1vxJD2Mqn3E4j5WC1SSAx1
			XJTMo2AhbyZLjAlon5iX7EBOKlXQgdJaLMU0iuW/blwVCukYeC+PMgzLhzdEum2w
			DQIDAQAB
			-----END PUBLIC KEY-----

		1.4 Se modifica JwtConfig.java, se crean dos constantes para las llaves publicas y privadas
				public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" + 
						"MIIEowIBAAKCAQEAsgrRmKYWY0N4VBpxlGSnB0lD9wYGUUNo0nvj6YZx+sE7F7bT\n" + 
						"eWQAOfOSHxnNm2MPPjZhCOGxqs15eE/IJlVvyqvrLIv12qgMmO1Xo6wPzw5d6T51\n" + 
						"e55BniM3FX1+KirBngYFdkRtZOpqT7/1mCeuuY/QFXA04JJ8U7QUvcM+6TRMaWfU\n" + 
						"zgh0/yDeyVs2xxv1Chg02OHNJSNL9uX89DTECiWZIS+mXPhWhrvo6nEjsaI3P7c0\n" + 
						"ycf//7J4mVVceu1vxJD2Mqn3E4j5WC1SSAx1XJTMo2AhbyZLjAlon5iX7EBOKlXQ\n" + 
						"gdJaLMU0iuW/blwVCukYeC+PMgzLhzdEum2wDQIDAQABAoIBAF6rogepSP4/Qhx2\n" + 
						"cy6U1rvpGVqguXs8fOBvQCFMfA758JILhSdnJTerbGddMRcO9Wv/PlmaqP5Jg+2V\n" + 
						"frfjdi4ufD6TrYc3FHu8uH0vYLiYyxbbP10cKYgM8Z1bvVpuocWFLiJVAFZIz37Y\n" + 
						"c58UerPw5JSBtZw0SCe5+c1tpGYbpev3hJ9ESaQHwhBT76Mjx/pD0huMY6QkABID\n" + 
						"qKg7v9RAYsJNLaQY2OwH8ihZg+iDH3oj3QI6/fBXY9LBwG30n2EC3qL9FPBiBU6H\n" + 
						"+psU5tuXIe1eRzOQQVaRi6kPHhj22zQ4+KXk0ka79rFd2pT9A1c1lTLSONeE1YPZ\n" + 
						"N8TXg4ECgYEA2lsAO1rLWGCnz7Bd8A10PmSxh6J4zlP8I/U7sO4AoEp/iJUK8WDg\n" + 
						"aJV1O2oEsCfgZKdown1UAcX3YbMrMX36nSZ5UEzXHO9if63B5Ox8+AnQ/lLvJOcw\n" + 
						"/m+Ojg+BPjyEMd4uKKgtYh1W6v2mcQDC5BvJuM3oLTugL41yhcgO578CgYEA0Lyd\n" + 
						"x9QGghyU/Q4Dn6Z4nb3/Yu647Yjth/3gZ3DlkHjjfG2zhG6HYNwc2N8TCBexTvip\n" + 
						"9PUQFWo8O/eFAI8DLiOZphb8hS3a2xn77tz5ftkCWtubJj5xCKdFtbH7FqE9ubfo\n" + 
						"Uzr8PY75PMJ1hYh1hADOLjytjlIfLD/y2ncTuzMCgYEAmMXEssKCtVHi91fhUZxI\n" + 
						"MYNIBqrxNQLjzdC9s7vBqmSVOF13K4zpVaMEQ86YextbT3ElxbXkOd1liPDPjrAi\n" + 
						"0SLjDADHHNKMKuLKXz3q7awIkng6I6qxx8MLjW9hsMasgyt9RirsndIDRySy+Ctd\n" + 
						"MDYU+/zsrgEiwUKI1vszGocCgYAPyPO1lNcZJ8gsis7DV/dvFlslxB+/9sbfrdc2\n" + 
						"e1cg4cNyOWmF7kXWkU6tJ9Fsnz4NVsijeZTJycrcTg+Ex0vaRV7tLHfUKm+OehyP\n" + 
						"J67GZOrErC7DaZ1qphujbd4rC+57COlMwf0pOpKmgZ4ZhwQ8A0OUQ4psZNFuqzdW\n" + 
						"RgL7+QKBgGUtlOO3NDreainMPuh12JfpTUj74pMh8jGlZNL7bVRHodSK9hm9tI/H\n" + 
						"7BgB2MO1KKnRM9yq+6iL6IjHWoy1ws97XByl+jmRvfqdyvfxIU6hjnqQux+nsuEL\n" + 
						"gbbPfNIZWpuo2IKFn5ftXNsw8dtlcxjhiWFsMQKQ5XQ0J1Yj5XrD\n" + 
						"-----END RSA PRIVATE KEY-----";
				public static final String RSA_PUBLICA ="-----BEGIN PUBLIC KEY-----\n" + 
						"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsgrRmKYWY0N4VBpxlGSn\n" + 
						"B0lD9wYGUUNo0nvj6YZx+sE7F7bTeWQAOfOSHxnNm2MPPjZhCOGxqs15eE/IJlVv\n" + 
						"yqvrLIv12qgMmO1Xo6wPzw5d6T51e55BniM3FX1+KirBngYFdkRtZOpqT7/1mCeu\n" + 
						"uY/QFXA04JJ8U7QUvcM+6TRMaWfUzgh0/yDeyVs2xxv1Chg02OHNJSNL9uX89DTE\n" + 
						"CiWZIS+mXPhWhrvo6nEjsaI3P7c0ycf//7J4mVVceu1vxJD2Mqn3E4j5WC1SSAx1\n" + 
						"XJTMo2AhbyZLjAlon5iX7EBOKlXQgdJaLMU0iuW/blwVCukYeC+PMgzLhzdEum2w\n" + 
						"DQIDAQAB\n" + 
						"-----END PUBLIC KEY-----";
		1.5.- Se modifica el AuthorizationServerConfig.java, el metodo accessTokenConverter(), se les configura la llave publica y privada
			//Se firma el token con la privada
			jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
			//Se verifica utentisidad del token
			jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
		1.6.- Para probar por medio de postman, se crea nuevamente el token
			POST --> http://localhost:8080/oauth/token
		1.7.- Se copia el token generado y se evalua en la pagina jwt.io
			ej: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTk4MjY4MzQsInVzZXJfbmFtZSI6ImFuZHJlcyIsImF1dGhvcml0aWV
				zIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiIyZDRjYTZjZC0zY2U1LTQyMmUtYmQzNS1lNGFiNWVkOTE2MmQiLCJjbGllbnRfaWQiOiJhbmd
				1bGFyYXBwIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.ZA58V8EcHBj203tFcMGCJ8cUOXxhfEvt0j52OrZ5vPW35v5a53KhG5adc3
				XmM8BPWmU0Zb_4c9FhUZwyxdCjjv5mnILDznRNoG5V-x0DCXn70-6Wnx79Stw5n89ftkefwjvxwtpmGSLKV8brd_Goyd0o-aseZFGQEk
				jG7RREhIToUeuuht7oZ1KxPTHcl9ym5DYfiYxWx4ZMcNRgW6p64xn1UNQ2tj19NfeiKQnAvMgFh0ncEO5NSeqjnRPKQ6WIO5R9Vj7bIM
				sZHOfvL9whkxzSGE76m90q1uuyGKy_xmFDlC5d-YUeQlPOi26MWzMqUQjKGisBN2SGUY7NaDpCMg
		1.8.- en postman, Se copia el toquen en la cabecera , authorization --> Bearer Token , en el input se reemplaza el token
			se prueba con el metodo get --> http://localhost:8080/api/clientes/1
133.- ***********************************************************************************************Añadir mas datos en el token JWT
	1.- Se crea una clase en auth , InfoAdicionalToken.java
	2.- Se implementa la interfaz TokenEnhancer y se añaden sus metodo TokenEnhancer
	3.- Se decora con la anotacion @Component
		@Component
		public class InfoAdicionalToken implements TokenEnhancer{

			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

				Map<String, Object> info = new HashMap<>();
				info.put("info:adicional", "Implementacion de Outh2 con "+authentication.getName());
				
				//Se asigna la informacion al accestoken
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
				
				return accessToken;
			}

		}
	4.- Se crea en el paquete service,  una interface de usuario IUsuariosService
		public interface IUsuariosService {
			public Usuario findByUsername(String username);
		}

	5.- Se modifica UsuarioService.java, se implementa tambien de IUsuariosService
		public class UsuariosService implements IUsuariosService,UserDetailsService{...}

		5.1.- Se realiza override del metodo de la interface creada 
				@Override
				@Transactional
			public Usuario findByUsername(String username) {
				return usuarioRepo.findByUsername(username);
			}
	6.- Se modifica la clase InfoAdicionalToken para añadir los datos del usuario y se recupera el usuario
		@Component
		public class InfoAdicionalToken implements TokenEnhancer{
			
			@Autowired
			private IUsuariosService usuarioService;
			

			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

				Usuario usuario = usuarioService.findByUsername(authentication.getName());
				
				Map<String, Object> info = new HashMap<>();
				info.put("info_adicional", "Implementacion de Outh2 con "+authentication.getName());
				
				info.put("nombre_usuario", usuario.getId()+": "+usuario.getUsername());
				
				//Se asigna la informacion al accestoken
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
				
				return accessToken;
			}
		}
	7.- Registrar el componente en el servidor de AuthorizationServerConfig
		7.0.- Se crea inyeccion de dependencia del componente creado
				@Autowired
				private InfoAdicionalToken infoAdicionalToken;
		7.1.- Se modifica el metodo de configuracion del endpoint para registrar la informacion adicional, se enlaza la informacion 
			  por defecto con la nueva informacion

			  	//Configuracion del endpoint del authorizationServer
			@Override
			public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
				TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
				tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken,accessTokenConverter()));
				
				//Se registra el authenticationManager
				endpoints.authenticationManager(authenticationManager)
				.tokenStore(tokenStore())
				//almacena los datos de authenticacion del token
				.accessTokenConverter(accessTokenConverter())
				.tokenEnhancer(tokenEnhancerChain);;
				
			}
	8.- Se prueba en postman y Jwt.io
		1.- Se genera el token con el metodo post
			http://localhost:8080/oauth/token
		2.- Se copia y se prueba en la pagina jwt.io, dede indicar la informacion adicional
134.- ************************************************************************Complementando con mas informacion del usuario al token
	1.- Se crean nuevos atributos a la entidad Usuario.java, con su get y set
			private String nombre;
	
			private String apellido;
			
			@Column(unique=true)
			private String email;
	2.- Se modifica el archivo insert.sql, se modifican los inserte usuarioService
		INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('andres','$2a$10$to/ZApT23BEQKuuaca3LYOlhrRhBNA.sUr3.p36Ydi9AyHh2L7J4a',1,'Andres','Espinoza','aespinoza@gmail.com');
		INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$Sx3KHjauh9sQsZy7YN3WtuLxUzAYGiLtLoVPnSbuBiAv197FvjZby',1,'Jhon','Doe','admin@gmail.com');

	3.- Se modifica la clase InfoAdicionalToken.java, se le agrega la informacion adicional del usuario		
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("email", usuario.getEmail());
	4.- Se prueba en postman, generando el token y validandolo en la pagina Jwt.io
135.- ************************************************************************************************Agregando seguridad a las rutas
	1.- Se modifica la clase ResourceServerConfig.java, se agregan permisos por rol a las rutas
		@Configuration
		//permite que nuestra aplicación se comporte como un  servidor de recursos 
		@EnableResourceServer
		public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

			@Override
			public void configure(HttpSecurity http) throws Exception {
				//Se permite a todos el accesso por el metodo get a "/api/clientes"
				http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/clientes", "/clientes/page/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER","ADMIN")
				.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/clientes/upload").hasAnyRole("USER","ADMIN")
				.antMatchers("/api/clientes/**").hasRole("ADMIN")		
				//Se valida que el cliente este autenticado
				.anyRequest().authenticated();
			}
			
		}
136.- **********************************************************************************Agregar seguridad a las rutas con anotaciones
	1.- Se modifica el archivo SpringSecurityConfig.java
		1.1.- Se habilita la seguridad con la anotacion @EnableGlobalMethodSecurity
			@EnableGlobalMethodSecurity(securedEnabled = true)
			@Configuration
			public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	2.- Se modifica ClienteRestController.java,  Despues que se habilita la anotacion @Secured, se le dan autorizaciones a los roles
			//Se le añade seguridad a los endpoint por url
			@Secured({"ROLE_ADMIN", "ROLE_USER"})
			@GetMapping("/clientes/{id}")
			//Manejo de codigo de respuesta
			//@ResponseStatus(HttpStatus.OK)
			//public Cliente showCliente(@PathVariable Integer id) {
			//Se agrega objeto ResponseEntity para el manejo de obj y mensajes de error
			//para que maneje cualquiertipo de objeto ResponseEntity<?>
			public ResponseEntity<?> showCliente(@PathVariable Integer id) {...}

			@Secured({"ROLE_ADMIN", "ROLE_USER"})
			@PostMapping("/clientes/upload")
			private ResponseEntity<?> uploadArchivo(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Integer idCliente){


			//Se le añade seguridad a los endpoint por url
			@Secured("ROLE_ADMIN")
			@PostMapping("/clientes")
			//Manejo de codigo de respuesta
		//	@ResponseStatus(HttpStatus.CREATED)
			//Se utiliza @RequestBody para que los datos los busque 
			//dentro del cuerpo del mensaje, ya que los datos vienen por json de la aplicacion Angular 
			//Se agrega obj ResponseEntity para el manejo de respuesta
			public ResponseEntity<?> saveCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {


			//Se le añade seguridad a los endpoint por url
			@Secured("ROLE_ADMIN")
			@GetMapping("/clientes/regiones")
			public List<Region> listaRegiones(){
				return clienteServ.findAllRegiones();
			}


			//Se le añade seguridad a los endpoint por url
			@Secured("ROLE_ADMIN")
			@DeleteMapping("/clientes/{id}")
			//NO_CONTENT CODIGO 204
		//	@ResponseStatus(HttpStatus.NO_CONTENT)
			//Se agrega obj ResponseEntity para el manejo de respuesta
			public ResponseEntity<?> delete(@PathVariable Integer id) {


			//Se le añade seguridad a los endpoint por url
			@Secured("ROLE_ADMIN")
			@PutMapping("/clientes/{id}")
			//CREATED CODIGO 201
		//	@ResponseStatus(HttpStatus.CREATED)
			//Se agrega obj ResponseEntity para el manejo de respuesta
			public ResponseEntity<?> updateCliente(@Valid @RequestBody Cliente cliente,BindingResult result, @PathVariable("id") Integer idCliente) {
		
	3.- Se modifica el archivo ResourceServerConfig.java, se comentan las autorizaciones a los roles
		@Override
		public void configure(HttpSecurity http) throws Exception {
			//Se permite a todos el accesso por el metodo get a "/api/clientes"
			http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/clientes", "/api/clientes/page/**", "/api/uploads/img/**").permitAll()
			/*.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER","ADMIN")
			.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER","ADMIN")
			.antMatchers("/api/clientes/**").hasRole("ADMIN")*/		
			//Se valida que el cliente este autenticado
			.anyRequest().authenticated();
		}

	4.- Se prueban los metodos en postman
		1.- Se valida los get que tienen acceso all, sin token
			http://localhost:8080/api/clientes

			http://localhost:8080/api/clientes/Page/1

			http://localhost:8080/api/clientes/Page/1

		2.- Se validan los metodos que tienen rol configurado sin token, los mismos no deben tener acceso

		3.- Se genera token con rol usurio, luego con usuario admin, se realizan las pruebas a los distintos metodos segun rol configurado


			http://localhost:8080/oauth/token
	5.- e modifica el archivo ResourceServerConfig.java, Se le da autorizacion all a los recursos de images

		@Override
		public void configure(HttpSecurity http) throws Exception {
			//Se permite a todos el accesso por el metodo get a "/api/clientes"
			http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/clientes", "/api/clientes/page/**", "/api/uploads/img/**", "/images/**").permitAll()
			/*.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER","ADMIN")
			.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER","ADMIN")
			.antMatchers("/api/clientes/**").hasRole("ADMIN")*/		
			//Se valida que el cliente este autenticado
			.anyRequest().authenticated();
		}
137.- *******************************************************************************************Configurando Cors en Spring Security
	1.- Se modifica ResourceServerConfig.java, se crea metodo de configuracion de cors
		@Bean
		public CorsConfigurationSource corsConfigurationSource() {
			//Se crea instancia de CorsConfiguration
			CorsConfiguration conf = new CorsConfiguration();
			//Permitir dominio
			conf.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
			//Se configuran todos los verbos o metodos que seran permitidos en el backend
			conf.setAllowedMethods(Arrays.asList("GET","POST", "DELETE", "PUT", "OPTIONS"));
			//Se permitesn las credenciales
			conf.setAllowCredentials(true);
			//Se configuran las cabeceras que se permiten
			conf.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
			
			//Se registra la configuracion para todas las rutas
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", conf);
			return source;
		}
		1.2.- Se Configura o registra un filter
			@Bean 
			public FilterRegistrationBean<CorsFilter> corsFilter(){
				FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
				bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
				return bean;
			}
Seccion 16: Authenticacion Outh2 con JWT: FrontEnd Angular***************************************************************************
140.- **************************************************************************************Creacion de componente login y formulario
	1.- Se crea componente login dentro de usurioService, el --flat=true es para que no cree un nuevo directorio
		ng g c usuarios/login --flat=true
		1.1.- Se elimina el .spect y el componente .css
		1.2.- Se modifica login.componente.ts.
			1.2.1.- se elimina del decorador la referencia al componente .css
			1.2.2.- Se crea atributo titulo de tipo string en la clase
				titulo:string = 'Por favor hacer Sing-In';
	2.- Se modifica app.module.ts, se registra la ruta al login
		{path: 'login', component: LoginComponent}
	3.- Se modifica login.component.html, se crea el formulari html
		<div class="card border-primary text-center">
		  <div class="card-header">{{titulo}}</div>
		  <div class="card-body">
		    <form method="post">
		      <div  class="form-group col-sm-6">
		        <input type="text" class="form-control" name="username" id="username" placeholder="Nombre de Usuario" autofocus required>
		      </div>
		      <div  class="form-group col-sm-6">
		        <input type="text" class="form-control" name="password" id="password" placeholder="Password" required>
		      </div>
		      <div  class="form-group col-sm-6">
		        <input type="submit" class="btn btn-lg btn-primary btn-block" value="login">
		      </div>
		    </form>
		  </div>
		</div>
	4.- Se modifica header.component.html
		4.1.- Se eliminan los link que no se estan utilizando
		4.2.- Se agrega link del lado derecho a login
			/*<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			  <!--Se llama a variable creada en la clase por interpolacion-->
			  <a class="navbar-brand" href="#">{{title}}</a>
			  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav"
			    aria-expanded="false" aria-label="Toggle navigation">
			    <span class="navbar-toggler-icon"></span>
			  </button>
			  <div class="collapse navbar-collapse" id="navbarSupportedContent">
			    <ul class="navbar-nav mr-auto">
			      <li class="nav-item">
			        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
			      </li>
			      <!-- Se agrega link a directivas -->
			      <li class="nav-item" routerLinkActive="active">
			        <a class="nav-link" routerLink="./directivas">Directivas</a>
			      </li>
			      <!-- Se agrega link a clientes -->
			      <li class="nav-item" routerLinkActive="active">
			        <a class="nav-link" [routerLink]="['/clientes']">Clientes</a>
			      </li>


			    </ul>
			    <ul class="navbar-nav ml-auto">
			      <li><a [routerLink]="['/login']" class="btn btn-outline-primary" >Sing-In</a></li>
			    </ul>
			  </div>
			</nav>*/
141.- ***************************************************************************Manejo de error no autorizado y redireccion al login
	1.- Se modifica clientes.service.ts
		1.1.- Se crea metodo que valida los errores 401 o 403
			  //Metodo que valida si hay error 402 o 403 y redirecciona al formulario
			  private isNoAutorizado(e){
			    if(e.status == 401 || e.status == 403){
			      //Se redirecciona
			      this.router.navigate(['./login'])
			      return false;
			    }
			    return false;
			  }
		1.2.- Se modifica el metodo getRegiones(), se le incluye por medio de un pipe el manejo del error en caso de presentarse
			  getRegiones(): Observable<Region[]>{
			    return this.http.get<Region[]>(this.urlEndPoint+'/regiones').pipe(
			      catchError(e => {
			        this.isNoAutorizado(e);
			        return throwError(e);
			      })
			    );
			  }
		1.3.- Se modifica el metodo createCliente(), getCliente(), update(), delete(),  este metodo ya contiene manejo de errores, 
		por lo que  este metodo se incluye la validacion
			//validacion de errores de autorizacion
	        if(this.isNoAutorizado(e)){
	          return throwError(e);
	        }
	    1.4.- Se modifica el metodo subirFoto() con un pipe para el manejo de error
	    	return this.http.request(req).pipe(
		      catchError(e => {
		        //validacion de errores de autorizacion
		        this.isNoAutorizado(e);
		        return throwError(e);
		      })
		    );
142.- ****************************************************************Creacion de metodo login en el componente y nueva clase Usuario
	1.- Se crea la clase usuario
		ng g class usuarios/usuario
		1.1.- Se crean atributos en la clase Usuario
			export class Usuario {
			  id:number;
			  username: string;
			  password: string;
			  nombre: string;
			  apellido: string;
			  email: string;
			  roles:string[] = [];
			}
	2.- Se modifica login.component.ts
		2.1.- Se importa la clase Usuario
			import {Usuario} from './usuario';
		2.2.- Se crea atributo de tipo Usuario
			usuario: Usuario;
		2.3.- Se inicializa en el constructor el atributo creado
			constructor() {
			    //Se inicializa el objeto usuario
			    this.usuario = new Usuario();
			  }
		2.4.- Se crea metodo login()
			 login():void{
		    console.log(this.usuario);
		    if(this.usuario.username == null || this.usuario.password == null){
		      swal.fire('Error Login', 'Username o password vacio', 'error');
		      return;
		    }
	3.- Se modifica login.component.html, se crea el binding de los imput y se configura el onclis del submit
		 <input [(ngModel)]="usuario.username" class="form-control" name="username" id="username" placeholder="Nombre de Usuario" autofocus required>

		 <input [(ngModel)]="usuario.password" type="text" class="form-control" name="password" id="password" placeholder="Password" required>

		 <input (click)="login()" type="submit" class="btn btn-lg btn-primary btn-block" value="login">
143.- ***************************************************************Creacion de la clase de servicio AuthService y su metodo login()
	1.- Se crea clase de servicio
		ng g s usuarios/auth
	2.- Se crea metodo login()
		  login(usuario:Usuario):Observable<any>{
		    const urlEndPoint ='http://localhost:8080/oauth/token';
		    //credenciales de la aplicacion encriptada en base64 con la funcion btoa
		    const credenciales = btoa('angularapp'+':'+'12345');
		    //headers de la peticion
		    const httpHeaders = new HttpHeaders({'Content-Type':'application/x-www-form-urlencoded',
		    'Authorization': 'Basic '+ credenciales});
		    //Parametros
		    let params = new URLSearchParams();
		    params.set('grant_type','password');
		    params.set('username', usuario.username);
		    params.set('password', usuario.password);
		    return this.http.post<any>(urlEndPoint, params.toString(), {headers: httpHeaders});
		  }
	3.- Se importan las librerias necesarias
		import {Usuario} from './usuario';
		import {Observable} from 'rxjs';
		import {HttpClient,HttpHeaders} from '@angular/common/http';
	4.- Se realiza inyeccion de dependencia de  HttpClient
			constructor(private http:HttpClient) { }
144.- ************************************************************************Implementacion de authenticacion en el componente login
	1.- Se modifica login.component.ts
		1.1.- Se importa la clase de servicio auth y el obj Router
			import {AuthService} from './auth.service';
			import {Router} from '@angular/router';
		1.2.- Se realiza inyeccion de dependencia de los obj importados
			constructor(private authService: AuthService, private router: Router) {
		1.3.- Se modifica etodo login(), para suscribir el metodo login() de la clase de servicio
			login():void{
			    console.log(this.usuario);
			    if(this.usuario.username == null || this.usuario.password == null){
			      swal.fire('Error Login', 'Username o password vacio', 'error');
			      return;
			    }
			    this.authService.login(this.usuario).subscribe(response=>{
			      let payload = JSON.parse(atob(response.access_token.split(".")[1]));
			      console.log(payload);
			      console.log(response);
			      this.router.navigate(['/clientes']);
			      swal.fire('Login', `Hola ${payload.user_name}, has iniciado sesion con exito!`,'success' );
			    });
			  }
145.- *****************************************************************************Guardar datos de usuario y token en sessionStorage
	1.- Se modifica login.component.ts
		1.1.- Se modifica metodo login(), se agregan metodos para guardar usuario y token
			  login():void{
			    console.log(this.usuario);
			    if(this.usuario.username == null || this.usuario.password == null){
			      swal.fire('Error Login', 'Username o password vacio', 'error');
			      return;
			    }
			    this.authService.login(this.usuario).subscribe(response=>{
			      this.authService.guardarUsuario(response.access_token);
			      this.authService.guardarToken(response.access_token);
			      this.router.navigate(['/clientes']);
			      let usuario: Usuario = this.authService.usuario;
			      swal.fire('Login', `Hola ${usuario.username}, has iniciado sesion con exito!`,'success' );
			    });
			  }
     2.- se modifica auth.service.ts
     	2.1.- Se cre metodo guardarUsuario(), guarda usuario en el sessionStorage
     		  guardarUsuario(accessToken:string):void{
			    let payload = this.obtenerDatosToken(accessToken);
			    this._usuario = new Usuario();
			    this._usuario.nombre = payload.nombre;
			    this._usuario.apellido = payload.apellido;
			    this._usuario.email = payload.email;
			    this._usuario.username = payload.user_name;
			    this._usuario.roles = payload.authorities;
			    sessionStorage.setItem('usuarios', JSON.stringify(this._usuario));
			  }

		2.2.- Se crea metodo guardarToken(), guarda el token en el sessionStorage

			  guardarToken(accessToken:string):void{
			    this._token = accessToken;
			    sessionStorage.setItem('token', accessToken);
			  }
		2.3.- Se crea metodo que obtiene los datos del token en un json
			   //metodo que obtiene los datos del token en un json
			  obtenerDatosToken(accessToken:string):any{
			    if(accessToken != null){
			      return JSON.parse(atob(accessToken.split(".")[1]));
			    }
			    return null;
			  }
		2.4.- Se cgreagan atributos para el token y para el usuario, con su get y set

			private _usuario:Usuario;
  			private _token:string;

			public get usuario():Usuario{
			    //Se valida que no se null el usuario
			    if(this._usuario != null){
			      return this._usuario;
			      //Si es null lo buscamos en el sessionstorage
			    }else if(this._usuario == null && sessionStorage.getItem('usuario')!=null){
			      //se recupera del sessionStorage y se convierte en json, luego se castea a usuario
			      this._usuario = JSON.parse(sessionStorage.getItem('usuario')) as Usuario;
			    }
			    return new Usuario();
			  }


			  public get token():string{
			    //Se valida que no se null el token
			    if(this._token != null){
			      return this._token;
			      //Si es null lo buscamos en el sessionstorage
			    }else if(this._token == null && sessionStorage.getItem('token')!=null){
			      //se recupera del sessionStorage el token
			      this._token = sessionStorage.getItem('token');
			    }
			    return null;
			  }
146.- *************************************************************************************Manejo de error de credenciales incorretas
	1.- Se modifica el login.component.ts, el metodo login() para validar error de credenciales incorrectas
		  login():void{
		    console.log(this.usuario);
		    if(this.usuario.username == null || this.usuario.password == null){
		      swal.fire('Error Login', 'Username o password vacio', 'error');
		      return;
		    }
		    this.authService.login(this.usuario).subscribe(response=>{
		      this.authService.guardarUsuario(response.access_token);
		      this.authService.guardarToken(response.access_token);
		      this.router.navigate(['/clientes']);
		      let usuario: Usuario = this.authService.usuario;
		      swal.fire('Login', `Hola ${usuario.username}, has iniciado sesion con exito!`,'success' );
		    //Se valida el error 400
		    }, err => {
		      if (err.status == 400){
		        swal.fire('Error Login', 'Username o password Incorrectos!', 'error');
		      }
		    });
		  }
147.- ***************************************************************************Chequear en el login si el usuario ya inicio session
	1.- Se modifica la clase de servicio auth.service.ts, se crea metodo para validar si usuario ya tiene sesion
		isAuthenticated(): boolean{
		    let payload = this.obtenerDatosToken(this.token);
		    if (payload != null && payload.user_name && payload.user_name.length>0){
		      return true;
		    }
		    return false;
		  }
	2.- Se modifica login.component.ts, se modifica metodo OnInit para que valide si esta autenticado y redirija a clienteServ
		  ngOnInit(): void {
		    if(this.authService.isAuthenticated()){
		      swal.fire('Login', `El usuario ${this.authService.usuario.username} ya se encuentra autenticado`, 'info');
		      this.router.navigate(['/clientes']);
		    }
		  }
148.- **************************************************************************************Añadir link logout para cerrar la session
	1.- Se modifica header.component.ts, se agrega construntor y se realiza inyeccion de dependencia de la clase de servicio AuthService
		1.1.- Se importa la clase de servicio
			import { AuthService } from '../usuarios/auth.service';
			import { Router } from '@angular/router';
			import swal from 'sweetalert2';
		1.2.- Se agrega constructor con la inyeccion de dependencia
			constructor(public authService:AuthService, private router: Router){
		1.3.- Se crea metodo de logout()
			    logout():void{
			    	swal.fire('Logout', `El usuario ${this.authService.usuario.username}, ha cerrado sesion con exito`, 'success');
			      this.authService.logout();

			      this.router.navigate(['/login']);
			    }
	2.- Se modifica header.component.html, se le agrega validacion si esta autenticado no mostrara boton de Sing-In
		<ul class="navbar-nav ml-auto">
	      <li *ngIf="!authService.isAuthenticated()"><a [routerLink]="['/login']" class="btn btn-outline-primary" >Sing-In</a></li>
	      <li *ngIf="authService.isAuthenticated()" class="dropdown">
	        <a href="#" class="btn btn-outline-primary dropdown-toggle"  id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          {{authService.usuario.username}}
	        </a>
	        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	          <button (click)="logout()" class="dropdown-item" type="submit">Sing Out</button>
	        </div>
	      </li>
	    </ul>
	3.- Se modifica auth.service.ts, se crea metodo de logout
		  logout():void{
		    this._usuario = null;
		    this._token = null;
		    //Se borra todo del sessionStorage
		    sessionStorage.clear();
		    //Se borra porl elemento del sessionStorage
		    sessionStorage.removeItem('token');
		    sessionStorage.removeItem('usuario');
		  }
149.- **************************************************************************Envio de token al backend para acceder a los recursos
	1.- Se modifica cliente.service.ts, se agrega en las cabeceras el atributo authorization con el token
		1.1.- Se crea un metodo que agregue a la cabecera el atributo authorization
			 //metodo que agrega el atributo authorization a la cabecera
		  private agregarAuthorizationHeaders(){
		    //Se recupera token de la clase de servicio
		    let token = this.authService.token;
		    //Se valida token
		    if(token != null){
		      return this.httpHeaders.append('Authorization', 'Bearer '+token);
		    }
		    return this.httpHeaders;
		  }
		1.2.- Se importa la clase de servicio AuthService
			import { AuthService } from '../usuarios/auth.service'
		1.3,. Se realiza la inyeccion de dependencia de la clase de servicio
			 //Se instancia en el constructor de la clase el obj HttpClient y el obj Router
  			constructor(private http: HttpClient, private router: Router,
            private authService:AuthService) { }
  		1.4.- Se modifica metodo getRegiones(), se agrega a la peticion los headers
  			  getRegiones(): Observable<Region[]>{
			    return this.http.get<Region[]>(this.urlEndPoint+'/regiones', {headers: this.agregarAuthorizationHeaders()}).pipe(
			      catchError(e => {
			        //validacion de errores de autorizacion
			        this.isNoAutorizado(e);
			        return throwError(e);
			      })
			    );
			  }
		1.5.- Se modifica metodo createCliente(), se agregan nueva cabeceras en la peticion http
			   createCliente(cliente: Cliente): Observable<any>{
			    //Se agrega pipe
			    /* return this.http.post<Cliente>(this.urlEndPoint, cliente, {headers: this.httpHeaders}).pipe( */
			      //Se modifica el cast del metodo post
			      return this.http.post<any>(this.urlEndPoint, cliente, {headers: this.agregarAuthorizationHeaders()}).pipe(
			      //Se agrega el operador catchError, intercepta en caso de existir error
			      catchError(e => {
			        //validacion de errores de autorizacion
			        if(this.isNoAutorizado(e)){
			          return throwError(e);
			        }

			        //Manejo de validacion que viene en el response del backend
			        if(e.status == 400){
			          return throwError(e);
			        }

			        //Se muestra por consola el error
			        console.error(e.error.mensaje);
			        //Se muestra mensaje al usuario
			        Swal.fire('Error al crear el cliente', e.error.mensaje, 'error');
			        //Se retorna obj observable
			        return throwError(e);
			      })
			    );
			  }
		1.6.- Se modifica metodo getCliente(), se agrega a la pecion las cabeceras con el metodo agregarAuthorizationHeaders()
			 getCliente(id): Observable<Cliente>{
			    //Se añade metodo pipe para para transformar la data
			    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`, {headers: this.agregarAuthorizationHeaders()}).pipe(
			      //Se añade obj que me permite detectar si hay errores
			      catchError(e => {
			        //validacion de errores de autorizacion
			        if(this.isNoAutorizado(e)){
			          return throwError(e);
			        }
			        //Se redirige al capturar el error
			        this.router.navigate(['/clientes']);
			        console.error(e.error.mensaje);

			        //Se muestra mensaje de error
			        Swal.fire('Error al obtener Cliente', e.error.mensaje,'error');
			        return throwError(e);
			      })
			    );
			  }
		1.7.- Se modifica metodo update(), se agregan cabeceras con el metodo agregarAuthorizationHeaders() a la peticion http
			  update(cliente: Cliente): Observable<Cliente>{
			    /* return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.id}`, cliente, {headers: this.httpHeaders}).pipe( */
			      //Se modifica el cast del metodo put para que no convierta de forma automatica
			      return this.http.put(`${this.urlEndPoint}/${cliente.id}`, cliente, {headers: this.agregarAuthorizationHeaders()}).pipe(
			        //Se agrega operador map que combierte a Cliente
			        map((response: any) => response.cliente as Cliente),
			        catchError( e => {
			        //validacion de errores de autorizacion
			        if(this.isNoAutorizado(e)){
			          return throwError(e);
			        }
			        //Manejo de validacion que viene en el response del backend
			        if(e.status == 400){
			          return throwError(e);
			        }

			        console.error(e.error.mensaje);
			        Swal.fire('Error al modificar cliente', e.error.mensaje, 'error');
			        return throwError(e);
			      })
			    );
			  }
		1.8.- Se modifica metodo delete(), se le agregan las cabeceras a la peticion http con el metodo agregarAuthorizationHeaders()
			  //Metodo delete de cliente
			  delete(id:number): Observable<Cliente>{
			    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`, {headers: this.agregarAuthorizationHeaders()}).pipe(
			      catchError( e => {
			        //validacion de errores de autorizacion
			        if(this.isNoAutorizado(e)){
			          return throwError(e);
			        }
			        console.error(e.error.mensaje);
			        Swal.fire('Error al eliminar el Ciente.', e.error.mensaje, 'error');
			        return throwError(e);
			      })
			    );
			  }
		1.9.- Se modifica el metodo subirFoto(), para incluir la cabecera en la peticion http
			 //Metodo que llama al endpoint del backend
				  subirFoto(archivo: File, id): Observable<HttpEvent<{}>>{
				    //Se crea objeto tipo FormData, es equivalente al multipart-FormData
				    let formData = new FormData();
				    formData.append("archivo", archivo);
				    formData.append("id", id);

				    //Se crea headres
				    let httpHeaders = new HttpHeaders();
				    //recuepro el token
				    let token = this.authService.token;
				    //Valido el token
				    if(token != null){
				      httpHeaders = httpHeaders.append('Authorization', 'Bearer '+token);
				    }

				    const req = new HttpRequest('POST', `${this.urlEndPoint}/upload`,formData, {
				      reportProgress: true,
				      //Se agrega los headers
				      headers: httpHeaders
				    });

				    return this.http.request(req).pipe(
				      catchError(e => {
				        //validacion de errores de autorizacion
				        this.isNoAutorizado(e);
				        return throwError(e);
				      })
				    );
				  }
150.- ******************************************************************************Manejo de error 403 (Acceso denegado o prohibido)
	1.- Se modifica cliente.service.ts, el metodo isNoAutorizado(), para que valide de forma separada el error 403
		  //Metodo que valida si hay error 402 o 403 y redirecciona al formulario
		  private isNoAutorizado(e):boolean{
		    if(e.status == 401){
		      //Se redirecciona
		      this.router.navigate(['./login'])
		      return false;
		    }

		    if(e.status == 403){
		      Swal.fire('Acceso Denegado', `El usuario ${this.authService.usuario.username}`+' no tiene acceso al recurso!', 'warning');
		      //Se redirecciona
		      this.router.navigate(['./clientes']);
		      return false;
		    }
		    return false;
		  }
151.- ************************************************************************Ocultando boton en la plantilla segun el rol de usuario
	1.- Se modifica auth.service.ts, se crea metodo para la validacion de rol
		 //Metodo que permite validar si el usuario posee un rol determinado
		  hasRole(role:string):boolean{
		    if(this.usuario.roles.includes(role)){
		      return true;
		    }
		    return false;
		  }
	2.- Se modifica cliente.component.ts
		2.1.- Se importa la clase de servicio AuthService
			import { AuthService } from '../usuarios/auth.service';
		2.2.- Se realiza la inyeccion de dependencia de la clase de servicio
			//Se crea variable en el const. y se le inyecta la clase de servicio
			  constructor(private clienteService: ClienteService,
			              private activatedRoute: ActivatedRoute,
			              private modalService: ModalService,
			              public authService: AuthService) { }
	3.- Se modifica cliente.component.html, se le agregan condicionales ngIf segun el rol de usuario
		3.1.- Se modifica boton crear
			<button *ngIf="authService.hasRole('ROLE_ADMIN')" class="btn btn-rounded btn-primary" type="buttom" [routerLink]="['/clientes/form']">Crear Cliente</button>
       	3.2.- Se modifican los headers de la tabla
       		<tr>
                <th *ngIf="authService.hasRole('ROLE_USER')">Img</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>fecha</th>
                <th>Email</th>
                <th *ngIf="authService.hasRole('ROLE_ADMIN')">Editar</th>
                <th *ngIf="authService.hasRole('ROLE_ADMIN')">Eliminar</th>
            </tr>
        3.3.- Se modifican los registros de la tabla
        	 <!--Se añade condicional *ngFor-->
            <tr *ngFor="let cliente of clientes">
                <td *ngIf="authService.hasRole('ROLE_USER')"><img *ngIf="cliente?.foto" (click)="abrirModal(cliente)"
                  src="http://localhost:8080/api/uploads/img/{{cliente.foto}}" alt="{{cliente.foto}}"
                  class="img-thumbnail rounded" style="width: 64px; cursor: pointer;">
                  <img *ngIf="!cliente?.foto" (click)="abrirModal(cliente)"
                  src="http://localhost:8080/images/noUser.png" alt="Sin Imagen"
                  class="img-thumbnail rounded" style="width: 64px; cursor: pointer;">
                </td>
                <td>{{cliente.nombre}}</td>
                <td>{{cliente.apellido}}</td>
                <td>{{cliente.fecha | date:'EEEE dd, MMMM yyyy'}}</td>
                <td>{{cliente.email}}</td>
                <td *ngIf="authService.hasRole('ROLE_ADMIN')">
                    <button class="btn btn-primary btn-sm" type="button" name="editar" [routerLink]="['/clientes/form', cliente.id]">Editar</button>
                </td>
                <td *ngIf="authService.hasRole('ROLE_ADMIN')">
                    <button class="btn btn-danger btn-sm" type="button" name="eliminar" (click)="delete(cliente)">Eliminar</button>
                </td>
            </tr>
    4.- Se modifica detalle.component.ts
    	4.1.- Se importa la clase de servicio AuthService
    		import {AuthService} from '../../usuarios/auth.service';
    	4.2.- Se realiza inyeccion de dependencia de la clase de servicio
    		constructor(private clienteService: ClienteService,
		                  public modalService: ModalService,
		                  public authService: AuthService) { }

    5.- Se modifica detalle.component.html, se role para mostrar el imput para subir la foto
    	<div class="input-group mb-3" *ngIf="authService.hasRole('ROLE_ADMIN')">
          <div class="custom-file">
            <input (change)="seleccionarFoto($event)" type="file" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" value="Buscar">
            <label class="custom-file-label" for="inputGroupFile04">Seleccionar Foto</label>
          </div>
          <div class="input-group-append">
            <button (click)="subirFoto()" [disabled]="!fotoSeleccionada" class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">Subir Foto</button>
          </div>
        </div>		
152.- *********************************************************************Cerrar Sesion en angular cuando expire token en el backend
	1.- Se modifica cliente.service.ts, se modifica el metodo isNoAutorizado() para que valide si esta authenticado aun en el backend
	en caso de no estarlo ejecuta el logout
		 private isNoAutorizado(e):boolean{
		    if(e.status == 401){
		      //Se valida si el token no ha expirado
		      if(this.authService.isAuthenticated()){
		        this.authService.logout();
		      }
		      //Se redirecciona
		      this.router.navigate(['./login'])
		      return false;
		    }

		    if(e.status == 403){
		      Swal.fire('Acceso Denegado', `El usuario ${this.authService.usuario.username}`+' no tiene acceso al recurso!', 'warning');
		      //Se redirecciona
		      this.router.navigate(['./clientes']);
		      return false;
		    }
		    return false;
		  }
153.- *************************************************************************************Añadiendo seguridad en las rutas con Guard
	Los Guard son interceptores que sirven para controlar el acceso a las rutas
	1.- Se genera guard por consola
		ng g g usuarios/guards/auth
		--> canActivated
	2.- Se modifica auth.guard.ts
		2.1.- Se importa la clase de servicio y la clase Router
			import { AuthService } from '../auth.service';
		2.2.- Se crea constructor y se realiza inyeccion de dependencia 
			  constructor(private authService: AuthService,
              private router: Router){ }
		2.3.- Se modifica el metodo canActivate(), para que valide la authenticacion y en caso de no estar autenticado redirija archivo
			login
			canActivate(
			    next: ActivatedRouteSnapshot,
			    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
				    if(this.authService.isAuthenticated()){
				      return true;
				    }
			      this.router.navigate(['/login']);
			      return false;
			  }
	3.- Se modifica app.module.ts, se aplica el interceptor a las rutas
		3.1.- Se importa la libreria auth.guard.ts
			import { AuthGuard } from './usuarios/guards/auth.guard';
		3.2.- Se modifican las rutas con el metodo canActivate() del interceptor
			const routes: Routes =[
			  //pagina principal
			  {path: '', redirectTo: '/clientes', pathMatch: 'full'},
			  {path: 'directivas', component: DirectivasComponent},
			  {path: 'clientes', component: ClientesComponent},
			  //Ruta con paginacion
			  {path: 'clientes/page/:page', component: ClientesComponent},
			  //Se mapea la ruta al formulario
			  {path: 'clientes/form', component: FormComponent, canActivate: [AuthGuard]},
			  //Url dond se renderiza metordo que busca cliente por id
			  {path: 'clientes/form/:id', component: FormComponent, canActivate: [AuthGuard]},
			  //ruta para el upload de la imagen
			  //{path: 'clientes/ver/:id', component: DetalleComponent}
			  {path: 'login', component: LoginComponent}
			];
154.- ***************************************************************************************************Crear Guard router RoleGuard
	1.- Se crea Guard por consola
		ng g g usuarios/guards/role
	2.- Se modifica role.guard.ts
		2.1.- Se importan librerias de la clase de servicio AuthService, Router y sweetalert2
			import { AuthService } from '../auth.service';
			import swal from 'sweetalert2';
		2.2.- Se crea constructor y se realiza inyeccion de dependencia de la clase de servicio  AuthService y Router
			  constructor(private authService: AuthService,
    						private router: Router){ }
		2.3.- Se modifica el metodo canActivate()
			canActivate(
			    next: ActivatedRouteSnapshot,
			    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
			      //Se valida si el usuario no esta autenticado
			      if(!this.authService.isAuthenticated()){
			        this.router.navigate(['/login']);
			        return false;
			      }		
			      //Se obtiene el role de la ruta
			      let role = next.data['role'] as string;
			      //Se valida el role
			      if(this.authService.hasRole(role)){
			        return true;
			      }
			      swal.fire('Acceso denegado.',`El usuario ${this.authService.usuario.username} no tiene acceso a este recurso`,'warning');
			      this.router.navigate(['/clientes']);
			      return false;
			  }
	3.- Se modifica app.module.ts, se incluye el interceptor en las rutas y la data 
		3.1.- Se importa la libreria del Guard para el role
			import { RoleGuard } from './usuarios/guards/role.guard';
		3.2.- Se modifican las rutas
			const routes: Routes =[
			  //pagina principal
			  {path: '', redirectTo: '/clientes', pathMatch: 'full'},
			  {path: 'directivas', component: DirectivasComponent},
			  {path: 'clientes', component: ClientesComponent},
			  //Ruta con paginacion
			  {path: 'clientes/page/:page', component: ClientesComponent},
			  //Se mapea la ruta al formulario
			  {path: 'clientes/form', component: FormComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ROLE_ADMIN'}},
			  //Url dond se renderiza metordo que busca cliente por id
			  {path: 'clientes/form/:id', component: FormComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ROLE_ADMIN'}},
			  //ruta para el upload de la imagen
			  //{path: 'clientes/ver/:id', component: DetalleComponent}
			  {path: 'login', component: LoginComponent}
			];
155.- *********************************************************************Validacion del tiempo de expiracion del token en AuthGuard
	1.- Se modifica auth.guard.ts
		1.1.- Se crea metodo que retorna un boolean isTokenExpirado()
			    isTokenExpirado():boolean{
				    //Se obtiene el token 
				    let token = this.authService.token;
				    //Se obtiene el payload
				    let payload = this.authService.obtenerDatosToken(token);
				    //Se obtiene el tiempo actual
				    let tiempoActual = new Date().getTime() / 1000;
				    //Se valida que el tiempo de expiracion del token no sea menos al actual
				    if(payload.exp < tiempoActual){
				      return true;
				    }
				    return false;
				  }
		1.2.- Se modifica metodo canActivate(), para validar el tiempo de expiracion del token
			canActivate(
			    next: ActivatedRouteSnapshot,
			    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
			      if(this.authService.isAuthenticated()){
			        //Se valida expiracion del token
			        if(this.isTokenExpirado()){
			          this.authService.logout();
			          this.router.navigate(['/login']);
			          return false;
			        }
			        return true;
			      }
			      this.router.navigate(['/login']);
			      return false;
			  }
156.- **********************************************************HttpInterceptor para añadir el token a la cabecera Http Authorization
	1.- Se crea una clase para el interceptor, por consola
		ng g class usuarios/interceptors
		1.1.- Se importan las librerias a utilizar
			import { Injectable } from '@angular/core';
			import{
			  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
			} from '@angular/common/http';
			import { Observable } from 'rxjs';
			import { AuthService } from '../auth.service';
		1.2.- Se crea la clase que implementa HttpInterceptor
			//Se genera un Interceptor a forma de servicio ( @Injectable() )
			@Injectable()
			export class TokenInterceptor implements HttpInterceptor {
			  //Se realiza inyeccion de dependencia de la clase de servicio AuthService
			  constructor(private authService: AuthService){}
			  /* invocamos el método intercept que obtendrá la petición y 
			  la alterará, retornando un Observable */
			  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
			    //Obtenemos el token
			    let token = this.authService.token;
			    //Se valida si existe el token 
			    if(token != null){
			      //Se clona el request
			      const authReq = req.clone({
			        //Se asigna un header de tipo Authorization con el valor de Bearer + la token 
			        headers: req.headers.set('Authorization', 'Bearer '+token)
			      });
			      //Se retorna la nueva peticion ya con el header de Authorization
			      return next.handle(authReq);
			    }
			    return next.handle(req);
			  }
			}
	2.- Se modifica app.module.ts, se integrar el interceptor en nuestro módulo
		2.1.- Se realiza el import de la clase interceptor creada
			import { TokenInterceptor } from './usuarios/interceptors/token.interceptor';
		2.2.- Se registra en providers
			 //Se agrega al modulo la clase de servicio
			   // Se agrega confg de LOCALE_ID
			  providers: [ClienteService,
			              {provide: LOCALE_ID, useValue: 'es' },
			              MatDatepickerModule,
			              MatMomentDateModule, 
			              { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }  ],
			  bootstrap: [AppComponent]

		2.3.- Se importa la libreria HTTP_INTERCEPTORS
			import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

	3.- Se modifica cliente.service.ts
		3.1.- Se comenta el metodo agregarAuthorizationHeaders
			 //metodo que agrega el atributo authorization a la cabecera
			  //Se comenta ya que el toquen se esta pasando por HttpInterceptor
			  /* private agregarAuthorizationHeaders(){
			    //Se recupera token de la clase de servicio
			    let token = this.authService.token;
			    //Se valida token
			    if(token != null){
			      return this.httpHeaders.append('Authorization', 'Bearer '+token);
			    }
			    return this.httpHeaders;
			  } */
		3.2.- Se comenta el headers en el metodo getRegiones()
			 getRegiones(): Observable<Region[]>{
			    return this.http.get<Region[]>(this.urlEndPoint+'/regiones'/* , {headers: this.agregarAuthorizationHeaders()} */).pipe(
			      catchError(e => {
			        //validacion de errores de autorizacion
			        this.isNoAutorizado(e);
			        return throwError(e);
			      })
			    );
			  }
		3.3.- Se comenta el headers en el metodo createCliente()
			    createCliente(cliente: Cliente): Observable<any>{
			    //Se agrega pipe
			    /* return this.http.post<Cliente>(this.urlEndPoint, cliente, {headers: this.httpHeaders}).pipe( */
			      //Se modifica el cast del metodo post
			      return this.http.post<any>(this.urlEndPoint, cliente, /* {headers: this.agregarAuthorizationHeaders()} */).pipe(
			      //Se agrega el operador catchError, intercepta en caso de existir error
			      catchError(e => {
			        //validacion de errores de autorizacion
			        if(this.isNoAutorizado(e)){
			          return throwError(e);
			        }

			        //Manejo de validacion que viene en el response del backend
			        if(e.status == 400){
			          return throwError(e);
			        }

			        //Se muestra por consola el error
			        console.error(e.error.mensaje);
			        //Se muestra mensaje al usuario
			        Swal.fire('Error al crear el cliente', e.error.mensaje, 'error');
			        //Se retorna obj observable
			        return throwError(e);
			      })
			    );
			  }
		3.4.- Se comenta el headers en el metodo getCliente()
			  //Se crea metodo que recupera un Cliente
			  getCliente(id): Observable<Cliente>{
			    //Se añade metodo pipe para para transformar la data
			    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`/* , {headers: this.agregarAuthorizationHeaders()} */).pipe(
			      //Se añade obj que me permite detectar si hay errores
			      catchError(e => {
			        //validacion de errores de autorizacion
			        if(this.isNoAutorizado(e)){
			          return throwError(e);
			        }
			        //Se redirige al capturar el error
			        /*  this.router.navigate(['/clientes']);
			        console.error(e.error.mensaje);

			        //Se muestra mensaje de error
			         Swal.fire('Error al obtener Cliente', e.error.mensaje,'error'); */
			        return throwError(e);
			      })
			    );
			  }

		3.5.- Se comenta el headers en el metodo update()
			  //Metodo de modificacion del cliente
				  update(cliente: Cliente): Observable<Cliente>{
				    /* return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.id}`, cliente, {headers: this.httpHeaders}).pipe( */
				      //Se modifica el cast del metodo put para que no convierta de forma automatica
				      return this.http.put(`${this.urlEndPoint}/${cliente.id}`, cliente/* , {headers: this.agregarAuthorizationHeaders()} */).pipe(
				        //Se agrega operador map que combierte a Cliente
				        map((response: any) => response.cliente as Cliente),
				        catchError( e => {
				        //validacion de errores de autorizacion
				        if(this.isNoAutorizado(e)){
				          return throwError(e);
				        }
				        //Manejo de validacion que viene en el response del backend
				        if(e.status == 400){
				          return throwError(e);
				        }

				        console.error(e.error.mensaje);
				        Swal.fire('Error al modificar cliente', e.error.mensaje, 'error');
				        return throwError(e);
				      })
				    );
				  }
		3.6.- Se comenta el headers en el metodo delete()
			  delete(id:number): Observable<Cliente>{
				    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`/* , {headers: this.agregarAuthorizationHeaders()} */).pipe(
				      catchError( e => {
				        //validacion de errores de autorizacion
				        if(this.isNoAutorizado(e)){
				          return throwError(e);
				        }
				/*         console.error(e.error.mensaje);
				        Swal.fire('Error al eliminar el Ciente.', e.error.mensaje, 'error'); */
				        return throwError(e);
				      })
				    );
				  }
		3.7.- Se comenta el headers en el metodo  subirFoto()
			 //Metodo que llama al endpoint del backend
			  subirFoto(archivo: File, id): Observable<HttpEvent<{}>>{
			    //Se crea objeto tipo FormData, es equivalente al multipart-FormData
			    let formData = new FormData();
			    formData.append("archivo", archivo);
			    formData.append("id", id);

			    /*
			    //Se comenta ya que el toquen se incluye en la cabecera por HttpInterceptor
			    //Se crea headres
			    let httpHeaders = new HttpHeaders();
			    //recuepro el token
			    let token = this.authService.token;
			    //Valido el token
			    if(token != null){
			      httpHeaders = httpHeaders.append('Authorization', 'Bearer '+token);
			    } */

			    const req = new HttpRequest('POST', `${this.urlEndPoint}/upload`,formData, {
			      reportProgress: true/* ,
			      //Se agrega los headers
			      headers: httpHeaders */
			    });

			    return this.http.request(req).pipe(
			      catchError(e => {
			        //validacion de errores de autorizacion
			        this.isNoAutorizado(e);
			        return throwError(e);
			      })
			    );
			  }
		3.8.- Se comenta atributo httpHeaders
			//private httpHeaders = new HttpHeaders ({'Content-type':'application/json'});
		3.9.- Se modifica token.interceptor.ts, el metodo intercept(), 
		 Como prueba se coloca un console.log() en el interceptor y se valida cada metodo por consola
			console.log("token => " + 'Bearer '+token);
157.- ***********************************************************************************HttpInterceptor para codigos  HTTP 401 y 403
	1.-  Se copia el interceptor token.interceptor.ts y se pega en la misma carpeta interceptors
		1.1.- Se le modifica el nombre a auth.interceptor.ts
		1.2.- Se elimina todo lo referente al token en el metodo intercept()
		1.3.- Se importan las librerias throwError, sweetalert2 y catchError
			import { Observable, throwError } from 'rxjs';
			import { AuthService } from '../auth.service';
			//Se importa la libreria de sweetAlert ya instalada
			import Swal from 'sweetalert2';
			//Se importa de operadorla clase map y catchError, tap
			import { catchError } from 'rxjs/operators';
			//Se importa objeto Route para redirijir a un enlace
			import { Router } from '@angular/router';
		1.4.- Se realiza la inyeccion de dependencia del router
			 constructor(private authService: AuthService,
              private router:Router){}
		1.5.- Se modifica el metodo intercept()
			   intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
				    //Cuendo resivimos validamos los codigos de respuesta
				    return next.handle(req).pipe(
				      catchError( e => {
				        if(e.status == 401){
				          //Se valida si el token no ha expirado
				          if(this.authService.isAuthenticated()){
				            this.authService.logout();
				          }
				          //Se redirecciona
				          this.router.navigate(['./login']);
				        }

				        if(e.status == 403){
				          Swal.fire('Acceso Denegado', `El usuario ${this.authService.usuario.username}`+' no tiene acceso al recurso!', 'warning');
				          //Se redirecciona
				          this.router.navigate(['./clientes']);
				        }
				        return throwError(e);
				      })
				    );
				  }
		1.6.- Se comenta el metodo isNoAutorizado()
		1.7.- Se comenta en cada metodo el manejo de error 
			1.7.1.-  Se comenta en el metodo getRegiones() 
				getRegiones(): Observable<Region[]>{
				    return this.http.get<Region[]>(this.urlEndPoint+'/regiones'/* , {headers: this.agregarAuthorizationHeaders()} */
				     )
				     //Se comenta, la validacion de los erroores se realiza en auth.interceptors.ts
				     /*.pipe(
				      catchError(e => {
				        //validacion de errores de autorizacion
				        this.isNoAutorizado(e);
				        return throwError(e);
				      })
				    ) */;
				  }
			1.7.2.-  Se comenta en el metodo createCliente() 

			    createCliente(cliente: Cliente): Observable<any>{
			    //Se agrega pipe
			    /* return this.http.post<Cliente>(this.urlEndPoint, cliente, {headers: this.httpHeaders}).pipe( */
			      //Se modifica el cast del metodo post
			      return this.http.post<any>(this.urlEndPoint, cliente, /* {headers: this.agregarAuthorizationHeaders()} */).pipe(
			      //Se agrega el operador catchError, intercepta en caso de existir error
			      catchError(e => {
			        //validacion de errores de autorizacion
			        /* if(this.isNoAutorizado(e)){
			          return throwError(e);
			        } */

			        //Manejo de validacion que viene en el response del backend
			        if(e.status == 400){
			          return throwError(e);
			        }

			        //Se muestra por consola el error
			        if(e.error.mensaje){
			          console.error(e.error.mensaje);
			        }
			        //Se muestra mensaje al usuario
			        /* Swal.fire('Error al crear el cliente', e.error.mensaje, 'error'); */
			        //Se retorna obj observable
			        return throwError(e);
			      })
			    );
			  }

			1.7.3.-  Se comenta en el metodo getCliente()
				  getCliente(id): Observable<Cliente>{
				    //Se añade metodo pipe para para transformar la data
				    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`/* , {headers: this.agregarAuthorizationHeaders()} */).pipe(
				      //Se añade obj que me permite detectar si hay errores
				      catchError(e => {
				        if(e.status != 401 && e.error.mensaje){
				          //Se redirige al capturar el error
				          this.router.navigate(['/clientes']);
				          console.error(e.error.mensaje);
				        }
				        //validacion de errores de autorizacion
				        /* if(this.isNoAutorizado(e)){
				          return throwError(e);
				        } */
				        //Se redirige al capturar el error
				        this.router.navigate(['/clientes']);
				        console.error(e.error.mensaje);

				        //Se muestra mensaje de error
				         /*Swal.fire('Error al obtener Cliente', e.error.mensaje,'error'); */
				        return throwError(e);
				      })
				    );
				  }

			1.7.4.-  Se comenta en el metodo update()
				  update(cliente: Cliente): Observable<Cliente>{
				    /* return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.id}`, cliente, {headers: this.httpHeaders}).pipe( */
				      //Se modifica el cast del metodo put para que no convierta de forma automatica
				      return this.http.put(`${this.urlEndPoint}/${cliente.id}`, cliente/* , {headers: this.agregarAuthorizationHeaders()} */).pipe(
				        //Se agrega operador map que combierte a Cliente
				        map((response: any) => response.cliente as Cliente),
				        catchError( e => {
				        //validacion de errores de autorizacion
				/*         if(this.isNoAutorizado(e)){
				          return throwError(e);
				        } */
				        //Manejo de validacion que viene en el response del backend
				        if(e.status == 400){
				          return throwError(e);
				        }

				        if(e.error.mensaje){
				          console.error(e.error.mensaje);
				        }
				        /* Swal.fire('Error al modificar cliente', e.error.mensaje, 'error');
				 */        return throwError(e);
				      })
				    );
				  }
			1.7.5.-  Se comenta en el metodo delete()
				 delete(id:number): Observable<Cliente>{
				    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`/* , {headers: this.agregarAuthorizationHeaders()} */).pipe(
				      catchError( e => {
				        //validacion de errores de autorizacion
				        /* if(this.isNoAutorizado(e)){
				          return throwError(e);
				        } */
				        if(e.error.mensaje){
				          console.error(e.error.mensaje);
				        }
				        //Swal.fire('Error al eliminar el Ciente.', e.error.mensaje, 'error'); 
				        return throwError(e);
				      })
				    );
				  }

			1.7.6.- Se comenta en el metodo subirFoto()
				  subirFoto(archivo: File, id): Observable<HttpEvent<{}>>{
			    //Se crea objeto tipo FormData, es equivalente al multipart-FormData
			    let formData = new FormData();
			    formData.append("archivo", archivo);
			    formData.append("id", id);

			    /*
			    //Se comenta ya que el toquen se incluye en la cabecera por HttpInterceptor
			    //Se crea headres
			    let httpHeaders = new HttpHeaders();
			    //recuepro el token
			    let token = this.authService.token;
			    //Valido el token
			    if(token != null){
			      httpHeaders = httpHeaders.append('Authorization', 'Bearer '+token);
			    } */

			    const req = new HttpRequest('POST', `${this.urlEndPoint}/upload`,formData, {
			      reportProgress: true/* ,
			      //Se agrega los headers
			      headers: httpHeaders */
			    });

			    return this.http.request(req)/* .pipe(
			      catchError(e => {
			        //validacion de errores de autorizacion
			        this.isNoAutorizado(e);
			        return throwError(e);
			      })
			    ) */;
			  }
	2.- Se modifica app.module.ts, se registra el interceptor AuthInterceptor
		2.1.- Se importa la clase AuthInterceptor

		2.2.- Se registra interceptor
			 providers: [ClienteService,
              {provide: LOCALE_ID, useValue: 'es' },
              MatDatepickerModule,
              MatMomentDateModule,
              { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
              { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }  
Seccion 17: Sistema de Facturacion***************************************************************************************************
161.- **********************************************Asociaciones: @ManyToOne y @OneToMany, relacion bidireccional Factura <-> Cliente
	1.- Se crea la entidad Factura()
		@Entity
		@Table(name="facturas")
		public class Factura implements Serializable{
			
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id;
			private String descripcion;
			private String observacion;
			
			@Column(name = "create_at")
			@Temporal(TemporalType.DATE)
			private Date createAt;
			
			//Muchas facturas a un cliente
			@ManyToOne(fetch = FetchType.LAZY)
			private Cliente cliente;
	2.- Se crea el metodo prePersistFecha(), se anota @PrePersist para que nates de la persistencia de datos se le coloque la fecha 
		actual al campo createAt
		@PrePersist
		public void prePersistFecha() {
			this.createAt = new Date();
		}
	
	3.- Se modifica Cliente(), se crea relacion bidireccional con la entidad Factura()
		3.1.- Se crea entidad facturas, con su get y set 
			private List<Factura> facturas;
		3.2.- Se crea constructor de la clase y se inicializa la lista 
			public Cliente() {
				this.facturas = new ArrayList<>();
			}	
		3.3.- Se crea relacion con la tabla Factura
			//Se crea relacion con la clase Factura
			@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
			private List<Factura> facturas;
162.- *******************************************************Asociaciones: @OneToMany, relacion unidireccional Factura -> ItemFactura
	1.- Se crea entidad ItemFactura()
		@Entity
		@Table(name="itemfacturas")
		public class ItemFactura implements Serializable{

			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id;
			
			private Integer cantidad;
	2.- Se modifica Factura.java, se le añade atributo de objeto ItemFactura, con su get y set 
		@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		@JoinColumn(name = "factura_id")
		private List<ItemFactura> items;
		2.1.- Se crea constructor y se inicializa items 
			public Factura() {
				this.items = new ArrayList<>();
			}
163.- ******************************************************Asociaciones: @ManyToOne, relacion unidireccional ItemFactura -> Producto
	1.- Se crea entidad Producto
		@Entity
		@Table(name="productos")
		public class Producto implements Serializable{

			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id;
			private String nombre;
			private Double precio;
			
			@Column(name="create_at")
			@Temporal(TemporalType.DATE)
			private Date createAt;
			
			@PrePersist
			public void prePersistFecha() {
				this.createAt = new Date();
			}
	2.- Se modifica ItemFactura.java, se le agrega atributo de producto, con su get y set y se crea metodo que calcula el importe  
		//Se mapea la relacion con tabla Producto
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "producto_id")
		private Producto producto;
		2.1.- Se cera metodo para el calculo del importe getImporte()
			public Double getImporte() {
				return cantidad.doubleValue() * producto.getPrecio();
			}
	3.- Se modifica Factura.java, se crea metodo que calcula el total de la factura getTotal()
		public Double getTotal() {
			Double total = 0.0;
			for (ItemFactura itemFactura : items) {
				total += itemFactura.getImporte();
			}
			return total;
		}
164.- **********************************************************************Se agregan inserts a las tablas facturas y facturas_items
	/* Populate tabla productos */
	INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
	INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
	INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
	INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
	INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
	INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
	INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

	/* Creamos algunas facturas */
	INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());

	INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
	INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
	INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
	INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

	INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
	INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);
165.- ***********************************************************************************Listando las facturas del cliente en postman
	1.- Configuracion de springSecurity para dar permisos a los endpoints permit.all
		1.1.- Se modifica ResourceServerConfig.java, se modifica el metodo configure() para dar permisos a los endpoints
			public void configure(HttpSecurity http) throws Exception {
				//Se permite a todos el accesso por el metodo get a "/api/clientes"
				http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/clientes", "/api/clientes/page/**", "/api/uploads/img/**", "/images/**").permitAll()
				.antMatchers("/api/clientes/{id}").permitAll()
				.antMatchers("/api/facturas/**").permitAll()
				/*.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER","ADMIN")
				.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER","ADMIN")
				.antMatchers("/api/clientes/**").hasRole("ADMIN")*/		
				//Se valida que el cliente este autenticado
				.anyRequest().authenticated();
			}
	2.- Para pruebas se modifica ClienteRestController.java, el metodo showCliente(), se comenta la anotacion @Secured 
		//@Secured({"ROLE_ADMIN"})
	3.- Se soluciona problema de serializacion del json, cuando hay relacion bidireccional
		3.1.- Se modifica Cliente.java
			//Se crea relacion con la clase Factura
			@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
			@JsonIgnoreProperties({"clientes"})
			private List<Factura> facturas;	
		3.2.- Se modifica Factura.java
			@JsonIgnoreProperties({"facturas"})
			@ManyToOne(fetch = FetchType.LAZY)
			private Cliente cliente;
	4.- Se eliminan objetos hibernateLazyInitializer y handler del las propiedades de json
		4.1.- Se modifica Factura.java
			@JsonIgnoreProperties({"facturas", "hibernateLazyInitializer", "handler"})
			@ManyToOne(fetch = FetchType.LAZY)
			private Cliente cliente;

			@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
			@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
			@JoinColumn(name = "factura_id")
			private List<ItemFactura> items;
		4.2.- Se modifica Cliente.java
			//Se crea relacion con la clase Factura
			@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
			@JsonIgnoreProperties({"clientes", "hibernateLazyInitializer", "handler"})
			private List<Factura> facturas;	
		4.3.- Se modifica ItemFactura.java
				//Se mapea la relacion con tabla Producto
			@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "producto_id")
			private Producto producto;
	5.- Se prueba en postman con el metodo getCliente
		http://localhost:8080/api/clientes/1
166.- ***********************************************(FrontEnd Angular)Añadiendo las clses TypeScript Factura, ItemFactura y Producto
	1.- Se crea clases por consola 
		ng g class facturas/models/factura

		ng g class facturas/models/itemFactura

		ng g class facturas/models/producto
	2.- Se modifica cliente.ts, se relaciona factura con cliente
		2.1.- Se importa factura.ts
			import { Factura } from '../facturas/models/factura';
		2.2.- Se crea el atributo factura en la clase
			facturas: Array<Factura> =[];
	3.- Se modifica factura.ts, se relaciona con cliente y con ItemFactura
		3.1.- Se crean los atributos 	
			export class Factura {

			  id: number;
			  descripcion: string;
			  observacion: string;
			  items: Array<ItemFactura>=[];
			  cliente: Cliente;
			  total: number;
			  createAt: string;
			  
			}
		3.2.- Se importan las librerias
			import { Cliente } from '../../clientes/cliente';
			import { ItemFactura } from './item-factura';
	4.- Se modifica ItemFactura.ts, se le añaden los atributos
		import { Producto } from './producto';

		export class ItemFactura {
		  producto: Producto;
		  cantidad: number = 1;
		  importe: number;
		}
	5.- Se modifica Producto.ts, se crean los atributos
		export class Producto {
		  id: number;
		  nombre: string;
		  precio: number;
		}
167.- ******************************************************************Listando las facturas en el componente de detalle del cliente
	1.- Para prueba se realiza modificaciones en cliente.component.ts, para que no valide el rol y me muestre el detalle
		1.1.- Se elimina ngIf del header de Img
			<tr>
	            <th >Img</th>
	            <th>Nombre</th>
	            <th>Apellido</th>
	            <th>fecha</th>
	            <th>Email</th>
	            <th *ngIf="authService.hasRole('ROLE_ADMIN')">Editar</th>
	            <th *ngIf="authService.hasRole('ROLE_ADMIN')">Eliminar</th>
	        </tr>
	    1.2.- Se elimina el ngIf del registro de imagen en la tabla
	    	<td >
	    		<img *ngIf="cliente?.foto" (click)="abrirModal(cliente)"
                          src="http://localhost:8080/api/uploads/img/{{cliente.foto}}" alt="{{cliente.foto}}"
                          class="img-thumbnail rounded" style="width: 64px; cursor: pointer;">
                          <img *ngIf="!cliente?.foto" (click)="abrirModal(cliente)"
                          src="http://localhost:8080/images/noUser.png" alt="Sin Imagen"
                          class="img-thumbnail rounded" style="width: 64px; cursor: pointer;">
            </td>
    2.- Se modifica detalle.component.ts, para que detalle contenga las facturas del cliente 
    	2.1.- Se añade clase ccs al modal para que tenga scroll, modal-dialog-scrollable 
    		<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
    	2.2.- Se añade tabla para mostrar las facturas del cliente 
    		<div class="row">
              <div class="alert alert-info my-4" *ngIf="cliente.facturas.length == 0">
                No hay facturas asignadas para el cliente {{cliente.nombre}} {{cliente.apellido}}
              </div>
              <table class="table table-bordered table-striped my-4"  *ngIf="cliente.facturas.length > 0">
                <thead>
                  <tr>
                    <th>IdFactura</th>
                    <th>Descripcion</th>
                    <th>Fecha</th>
                    <th>total</th>
                  </tr>
                </thead>
                <tbody>
                  <tr *ngFor="let factura of cliente.facturas">
                    <td>{{factura.id}}</td>
                    <td>{{factura.descripcion}}</td>
                    <td>{{factura.createAt}}</td>
                    <td>{{factura.total}}</td>
                  </tr>
                </tbody>
              </table>
            </div>
168.- ************************************************************************Agregar Repositorio para Factura y se imp en el service
	1.- Se crea repositorio FacturaRepository en el paquete repository
		public interface IFacturaRepository extends CrudRepository<Factura, Long> {}
	2.- Se crea clase de servicio para Factura FacturaServiceImpl{}
		2.1.- Se crea interface para la clase de servicio IFacturaService
			public interface IFacturaService {
	
				Factura findById(Long idFactura);
				
				Factura saveFactura(Factura factura);
				
				void deleteFactura(Long idFactura);

			}
		2.2.- Se crea clase de servicio FacturaServiceImpl.java, se impelmenta la interfase IFacturaService
			@Service
			public class FacturaServiceImpl implements IFacturaService {

				@Autowired
				private IFacturaRepository facturasRepo;
				
				@Override
				@Transactional(readOnly = true)
				public Factura findById(Long idFactura) {
					return facturasRepo.findById(idFactura).orElse(null);
				}

				@Override
				@Transactional
				public Factura saveFactura(Factura factura) {
					return facturasRepo.save(factura);
				}

				@Override
				@Transactional
				public void deleteFactura(Long idFactura) {
					facturasRepo.deleteById(idFactura);
				}

			}
169.- *******************************************************Creacion de controlador FacturaRestController con la accion handler show
	1.- Se crea FacturaRestController.java
		@CrossOrigin(origins = "http://localhost:4200")
		@RestController
		@RequestMapping("/api")
		public class FacturaRestController {

			@Autowired
			private IFacturaService factServ;
			
			@GetMapping("/facturas/{id}")
			@ResponseStatus(HttpStatus.OK)
			public Factura show(@PathVariable("id") Long idFactura) {
				return factServ.findById(idFactura);
			}
		}
	2.- Se prueba por postman el controlador
		get-- > http://localhost:8080/api/facturas/1
170.- ************************************************************(FrontEnd) Creacion de componente detalleFactura y su clase service
	1.- Se crea componente para detalleFactura por consola
		ng g c facturas/detalleFactura --flat
		1.1.- Se elimina el .spect y .css
		1.2.- Se modifica detalle-Factura.compoenent.ts, se elimina del decorators la referencia al .css
		1.3.- Se modifica app.module.ts, se configura el componente
			1.3.1.- Se valida que este importado el compoenete
			1.3.2.- Se valida que se halla creado en el declarations el componente
			1.3.3.- Se crea la ruta de acceso al componente
	2.- Se modifica detalle.component.html, se agrega columna y boton que redirija a detalle
		<thead>
          <tr>
            <th>IdFactura</th>
            <th>Descripcion</th>
            <th>Fecha</th>
            <th>total</th>
            <th>detalle</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let factura of cliente.facturas">
            <td>{{factura.id}}</td>
            <td>{{factura.descripcion}}</td>
            <td>{{factura.createAt}}</td>
            <td>{{factura.total}}</td>
            <td><button class="btn btn-primary btn-sm" type="button" [routerLink]="['/facturas', factura.id]">Ver</button></td>
          </tr>
        </tbody>
    3.- Se crea clase de servicio para la factura por consola
    	ng g s facturas/services/facturas
    	3.1.- Se elimna el .spect
    	3.2.- Se modifica facturas.service.ts
    		3.2.1.- Se crea atributo que contenga el endPoint
    			private urlEndPoint: string = 'http://localhost:8080/api/facturas';
    		3.2.2.- Se importa el HttpClient para poder acceder al backend, el Observable y la clase Factura
    			import { HttpClient } from '@angular/common/http';
				import { Observable } from 'rxjs';
				import { Factura } from '../models/factura';
			3.2.3.- Se inyecta el HttpClient en el constructor
				constructor(private httpCliente: HttpClient) { }
			3.2.4.- Se crea metodo para obtener el detalle de la factura
				  getFactura(id: number):Observable<Factura>{
				    return this.httpCliente.get<Factura>(`${this.urlEndPoint}/${id}`);
				  }
171.- ************************************************************************************************Se implementa el detalleFactura
	1.- Se modifica detalle-Factura.component.ts
		1.1.- Se importan las librerias necesarias, FacturaService, ActivatedRoute, Factura
			import { ActivatedRoute } from '@angular/router';
			import { Factura } from './models/factura';
			import { FacturasService } from './services/facturas.service';
		1.2.- Se inyecta en el constructor el obj ActivatedRoute y FacturasService
			constructor(private activatedRoute: ActivatedRoute,
              private facturasService: FacturasService) { }
        1.3.- Se crea atributo Factura
        	factura: Factura;
  			titulo: string = 'Factura';			
  		1.4.- Se modifica el metodo ngOnInit, para obtener la factura
  			  ngOnInit(): void {
			    this.activatedRoute.paramMap.subscribe(params => {
			      let id = +params.get('id');
			      this.facturasService.getFactura(id).subscribe(factura => this.factura = factura);
			    })
			  }
	2.- Se modifica detalle-factura.component.html
		<div class="card bg-light" *ngIf="factura">
		  <div class="card-header">{{titulo}}: {{factura.descripcion}}</div>
		  <div class="card-body">
		    <h4 class="card-title">
		      <a [routerLink]="['/clientes']" class="btn btn-light btn-xs">&laquo; volver</a>
		    </h4>

		    <ul class="list-group my-2">
		      <li class="list-group-item list-group-item-primary">Datos del Cliente</li>
		      <li class="list-group-item ">{{factura.cliente.nombre}}</li>
		      <li class="list-group-item ">{{factura.cliente.apellido}}</li>
		    </ul>

		    <ul class="list-group my-2">
		      <li class="list-group-item list-group-item-primary">Datos de la factura</li>
		      <li class="list-group-item ">Id: {{factura.id}}</li>
		      <li class="list-group-item ">Descripcion: {{factura.descripcion}}</li>
		      <li class="list-group-item ">Fecha: {{factura.createAt}}</li>
		    </ul>

		  </div>
		</div>
172.- ********************************************************************Añadiendo lineas de factura y Observacion del detllafactura
	1.- Se modifica detalle-factura.component.ts
		1.1.- Se añade table para el detalle de la factura despues de los datos de la factura
			<table class="table table-striped table-hover table-bordered my-3">
		      <thead>
		        <tr>
		          <th>Producto</th>
		          <th>Precio</th>
		          <th>Cantidad</th>
		          <th>Total</th>
		        </tr>
		      </thead>
		      <tbody>
		        <tr *ngFor="let item of factura.items">
		          <th>{{item.producto.nombre}}</th>
		          <th>{{item.producto.precio}}</th>
		          <th>{{item.cantidad}}</th>
		          <th>{{item.importe}}</th>
		        </tr>
		      </tbody>
		    </table>
		1.2.- Se añade span para el total de la factura y se alinea a la derecha
			<h1 class="float-right">
		      <span>Total: </span><span class="badge badge-secondary">{{factura.total}}</span>
		    </h1>
		1.3.- Se añade card para la observacion de la factura
			<div class="card card-info mb-4" style="max-width: 350px">
		      <div class="card card-header">
		        Observaciones
		      </div>
		      <div class="card card-body">
		        <p *ngIf="!factura.observacion">No tiene Observacion</p>
		        <p *ngIf="factura.observacion">{{factura.observacion}}</p>
		      </div>
		    </div>
173.- ***********************************************************(Backend Spring)Creacion de metodo para eliminar Factura y sus items
	1.- Se modifica FacturaRestController, se crea metodo deleteFactura()
		@DeleteMapping("/facturas/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void delete(@PathVariable("id") Long idFactura) {
			factServ.deleteFactura(idFactura);
		}
174.- ***************************************************************************(FrontEnd Angular)Eliminando la factura y sus lineas
	1.- Se modifica la clase de servicio factura.service.ts, se crea metodo para eliminar factura
		deleteFactura(id: number): Observable<void>{
		    return this.httpCliente.delete<void>(`${this.urlEndPoint}/${id}`);
		  }
	2.- Se modifica detalle.component.html, se agrega boton de eliminar para factura
		2.1.- Se modifica head de la table
			 <thead>
                  <tr>
                    <th>IdFactura</th>
                    <th>Descripcion</th>
                    <th>Fecha</th>
                    <th>total</th>
                    <th>detalle</th>
                    <th>eliminar</th>
                  </tr>
                </thead>
        2.2.- Se agrega el boton al body de la tabla
                <tbody>
                  <tr *ngFor="let factura of cliente.facturas">
                    <td>{{factura.id}}</td>
                    <td>{{factura.descripcion}}</td>
                    <td>{{factura.createAt}}</td>
                    <td>{{factura.total}}</td>
                    <td><button class="btn btn-primary btn-sm" type="button" [routerLink]="['/facturas', factura.id]">Ver</button></td>
                    <td><button class="btn btn-danger btn-sm" type="button" (click)="deleteFactura(factura)">eliminar</button></td>
                  </tr>
                </tbody>
    3.- Se modifica detalle.component.ts, se implementa el metodo deleteFactura()
    	3.1.- Se importa FacturasService y Factura 
    		import { FacturasService } from '../../facturas/services/facturas.service';
			import { Factura } from '../../facturas/models/factura';
		3.2.- Se realiza inyeccion de dependencia de FacturasService
			constructor(private clienteService: ClienteService,
                  private facturaService: FacturasService,
                  public modalService: ModalService,
                  public authService: AuthService) { }
175.- ******************************************************************************Creacion de componente para formulario de factura
	1.- Se crea componente para factura por consola
		ng g c facturas
	2.- Se modifica app.module.ts, se crea la ruta del componente
		const routes: Routes =[
		  //pagina principal
		  {path: '', redirectTo: '/clientes', pathMatch: 'full'},
		  {path: 'directivas', component: DirectivasComponent},
		  {path: 'clientes', component: ClientesComponent},
		  //Ruta con paginacion
		  {path: 'clientes/page/:page', component: ClientesComponent},
		  //Se mapea la ruta al formulario
		  {path: 'clientes/form', component: FormComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ROLE_ADMIN'}},
		  //Url dond se renderiza metordo que busca cliente por id
		  {path: 'clientes/form/:id', component: FormComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ROLE_ADMIN'}},
		  //ruta para el upload de la imagen
		  //{path: 'clientes/ver/:id', component: DetalleComponent}
		  {path: 'login', component: LoginComponent},
		  {path: 'facturas/:id', component: DetalleFacturaComponent},
		  {path: 'facturas/form/:clienteId', component: FacturasComponent}
		];
	3.- Se modifica facturas.component.ts
		3.1.- Se importan las clases ClienteService, Factura, ActivatedRoute
			import { Factura } './models/factura';
			import { ClienteService } from '../clientes/cliente.service';
			import { ActivatedRoute } from '@angular/router';
		3.2.- Se realiza inyeccion de dependencia del ActivatedRoute y ClienteService
			  constructor(private cienteService: ClienteService,
              private ActivatedRoute: ActivatedRoute) { }
        3.3.- Se crean los atributos 
        	  titulo: string;
  			factura: Factura = new Factura();
  		3.4.- Se modifica el metodo ngOnInit()
  			ngOnInit(): void {
			    //Se asigna cliente al atributo factura
			    this.ActivatedRoute.paramMap.subscribe(params =>{
			      let clienteId = +params.get('clienteId');
			      this.clienteService.getCliente(clienteId).subscribe(cliente=>this.factura.cliente=cliente);
			    })
			  }
		3.5.- Se modifica cliente.component.html, se agrega boton para crear factura
			3.5.- Se modifica los heads de la tabla
				 <thead>
                    <tr>
                        <th >Img</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>fecha</th>
                        <th>Email</th>
                        <th>crear factura</th>
                        <th *ngIf="authService.hasRole('ROLE_ADMIN')">Editar</th>
                        <th *ngIf="authService.hasRole('ROLE_ADMIN')">Eliminar</th>
                    </tr>
                </thead>
            3.6.- Se modifica el body de la tabla
            	 <tbody>
                    <!--Se añade condicional *ngFor-->
                    <tr *ngFor="let cliente of clientes">
                        <td ><img *ngIf="cliente?.foto" (click)="abrirModal(cliente)"
                          src="http://localhost:8080/api/uploads/img/{{cliente.foto}}" alt="{{cliente.foto}}"
                          class="img-thumbnail rounded" style="width: 64px; cursor: pointer;">
                          <img *ngIf="!cliente?.foto" (click)="abrirModal(cliente)"
                          src="http://localhost:8080/images/noUser.png" alt="Sin Imagen"
                          class="img-thumbnail rounded" style="width: 64px; cursor: pointer;">
                        </td>
                        <td>{{cliente.nombre}}</td>
                        <td>{{cliente.apellido}}</td>
                        <td>{{cliente.fecha | date:'EEEE dd, MMMM yyyy'}}</td>
                        <td>{{cliente.email}}</td>
                        <td><button class="btn btn-success btn-sm" type="button" [routerLink]="['/facturas/form', cliente.id]">Crear Factura</button></td>
                        <td *ngIf="authService.hasRole('ROLE_ADMIN')">
                            <button class="btn btn-primary btn-sm" type="button" name="editar" [routerLink]="['/clientes/form', cliente.id]">Editar</button>
                        </td>
                        <td *ngIf="authService.hasRole('ROLE_ADMIN')">
                            <button class="btn btn-danger btn-sm" type="button" name="eliminar" (click)="delete(cliente)">Eliminar</button>
                        </td>
                    </tr>
                </tbody>
        4.- Se modifica detalle.compoent.html, se agrega boton para la creacion de la factura
        	<div class="row">
              <td><button class="btn btn-success btn-sm" type="button" [routerLink]="['/facturas/form', cliente.id]">Crear Factura</button></td>
            </div>
176.- *******************************************************************************************Agregar campos al formulario factura
	1.- Se modifica factura.component.ts
		/*<div class="card bg-light">
		  <div class="card-header"></div>
		  <div class="card-body">
		    <div class="card-title">
		      <form >
		        <div class="form-group row" *ngIf="factura.cliente">
		          <label for="cliente" class="col-sm-2 col-form-label">Cliente</label>
		          <div class="col-sm-6">
		            <input type="text" class="form-control" name="cliente" value="{{factura.cliente.nombre}} {{factura.cliente.apellido}}" disabled>
		          </div>
		        </div>

		        <div class="form-group row">
		          <label for="descripcion" class="col-sm-2 col-form-label">Descripcion</label>
		          <div class="col-sm-6">
		            <input type="text" class="form-control" name="descripcion" [(ngModel)]="factura.descripcion">
		          </div>
		        </div>

		        <div class="form-group row">
		          <label for="observacion" class="col-sm-2 col-form-label">Observacion</label>
		          <div class="col-sm-6">
		            <textarea type="text" class="form-control" name="observacion" [(ngModel)]="factura.observacion"></textarea>
		          </div>
		        </div>

		        <div class="form-group row">
		          <div class="col-sm-6">
		            <input type="submit" value="Crear Factura"  class="btn btn-secondary">
		          </div>
		        </div>
		      </form>
		    </div>
		  </div>
		</div>*/
177.- *******************************************************************************************AutoComplete usando Angular Material
	1.- Se accede a la documentacion de angular material para compoente AutoComplete
		https://material.angular.io/components/autocomplete/overview 
		1.1.- Se busca custon filter --> ver codigo
		1.2.- Se copia el html sin el tag form, porque ya lo tiene la plantilla facturas.component.html
		1.3.- Se modifica facturas.component.html
			1.3.1.- Se agrega antes de observacion div form-group y div col-sm-6, dentro se copia el hatml del AutoComplete
				<div class="form-group row">
		          <div class="col sm-6">
		            <mat-form-field class="example-full-width">
		              <input type="text" placeholder="Agregue Producto" aria-label="Number" matInput [formControl]="autoCompleteControl" [matAutocomplete]="auto">
		              <mat-autocomplete #auto="matAutocomplete">
		                <mat-option *ngFor="let option of productosFiltrados | async" [value]="option">
		                  {{option}}
		                </mat-option>
		              </mat-autocomplete>
		            </mat-form-field>
		          </div>
		        </div>
		1.4.- Se modifica facturas.component.ts
			1.4.1.- Se copia el ts del autocomplete en angular material despues del metodo ngOnInit, solo lo que esta dentro de la clase 
			1.4.2.- Se pegan las libreria para importar
				//Se importan las librerias para el autocomplete de anfular material
				import {FormControl} from '@angular/forms';
				import {Observable} from 'rxjs';
				import {map, startWith} from 'rxjs/operators';
			1.4.3.- Se pegan los atributos
			  //Atributos del autocomplete de angular materia
			  autoCompleteControl = new FormControl();
			  //productos a ser recuperados
			  productos: string[] = ['One', 'Two', 'Three'];
			  //Areglo de productos filtrados
			  productosFiltrados: Observable<string[]>;

			1.4.4.- Se modifica el metodo ngOnInit()
				  ngOnInit(): void {
				    //Se asigna cliente al atributo factura
				    this.ActivatedRoute.paramMap.subscribe(params =>{
				      let clienteId = +params.get('clienteId');
				      this.clienteService.getCliente(clienteId).subscribe(cliente=>this.factura.cliente=cliente);
				    })
				    //recupero los productos filtrados
				    this.productosFiltrados = this.autoCompleteControl.valueChanges
				      .pipe(
				        //retorna todos antes de que cambie el imput
				        startWith(''),
				        map(value => this._filter(value))
				      );
				  }
			1.4.5.- Se pega el metodo filter
				    //Metodo filter para el autocomplete de angular material
				  private _filter(value: string): string[] {
				    const filterValue = value.toLowerCase();
				    //filtra el valor pasado por parametro en el arreglo de productos
				    return this.productos.filter(option => option.toLowerCase().includes(filterValue));
				  }
		1.5.- Se modifica app.module.ts 
			1.5.1.- Se busca al principio de la documentacion la api, se copia el import del module y se pega en app.module.ts
				import {MatAutocompleteModule} from '@angular/material/autocomplete';
			1.5.2.- Se busca la api de input y se importa en el app.module.ts
				import {MatInputModule} from '@angular/material/input';
			1.5.3.- Se busca la api de form y se importa en el app.module.ts
				import {MatFormFieldModule} from '@angular/material/form-field';
			1.5.4.- Se importa la libreria ReactiveFormsModule
				import { FormsModule, ReactiveFormsModule } from '@angular/forms';
			1.5.5.- Se registran los modulos importados
				  imports: [
				    BrowserModule,
				    //Se registra el HttpClientModule
				    HttpClientModule,
				    FormsModule,
				    //Se registran las rutas creadas
				    RouterModule.forRoot(routes),
				    BrowserAnimationsModule,
				    MatDatepickerModule,
				    MatMomentDateModule,
				    //autocomplete angular material
				    ReactiveFormsModule, MatAutocompleteModule, MatInputModule, MatFormFieldModule
				  ],
178.- ************************************************************************************Consultas JPA para el autocomplete(Backend)
	1.- Creacion de Repositorio IProductoRepository
		@Repository
		public interface IProductoRepository extends JpaRepository<Producto, Long> {
			
			//QueriMthod con consulta personalizada
			@Query("SELECT p FROM Producto p WHERE p.nombre like %?1%")
			public List<Producto> findByNombre(String nombre);
			
			//Con QueryMethod, busca donde nombre contenga el parametro y se ignore si es may. o min.
			public List<Producto> findByNombreContainingIgnoreCase(String nombre);

			
			//Con QueryMethod, busca donde nombre comience con el parametro y se ignore si es may. o min.
			public List<Producto> findByNombreStartingWithIgnoreCase(String nombre);
		}
	2.- Se crea interface IproductoService
		public interface IProductoService {
			List<Producto> findProductoByNombre(String nombre);
		}
	3.- Se crea Clase de Servicio ProductoServiceImpl
		@Service
		public class ProductoServiceImpl implements IProductoService {
			
			@Autowired
			private IProductoRepository productoRepo;

			@Override
			@Transactional(readOnly = true)
			public List<Producto> findProductoByNombre(String nombre) {
				return productoRepo.findByNombreContainingIgnoreCase(nombre);
			}
		}
	4.- Se crea controlador ProductoController
		@RestController
		@RequestMapping("/api")
		@CrossOrigin(origins = {"http://localhost:4200"})
		public class ProductoController {
			
			@Autowired
			private IProductoService productoServ;
			
			@GetMapping("/facturas/filtrarProducto/{term}")
			@ResponseStatus(HttpStatus.OK)
			public List<Producto> filtrarProducto(@PathVariable("term") String termino){
				return productoServ.findProductoByNombre(termino);
			}

		}
	5.- Se prueba con postman
		Metodo get--> http://localhost:8080/api/facturas/filtrarProducto/son
179.- *********************************************************************Busqueda de Productos Filtrados desde el backend(FrontEnd)
	1.- Se modifica facturas.service.ts, se crea metodo que busca la lista de productos filtrados
		1.1.- Se importa Producto
			import { Producto } from '../models/producto';
		1.2.- Se crea metodo
		  //Metodo que filtra los productos en el backend por un parametro(term)
		  getFiltroProducto(term: string): Observable<Producto[]>{
		    return this.httpCliente.get<Producto[]>(`${this.urlEndPoint}/filtrarProducto/${term}`);
		  }
	2.- Se modifica factura.component.ts, se utiliza el metodo de la clase de servicio para el fltro de producto
		2.1.- Se importa la clase de servicio FacturasService y Producto
			import { FacturasService } from './services/facturas.service';
			import { Producto } from './models/producto';
		2.2.- Se inyecta en el constructor la clase de servicio facturasService
			constructor(private clienteService: ClienteService,
              private facturasService: FacturasService, 
              private ActivatedRoute: ActivatedRoute) { }
		2.3.- Se modifica el metodo  _filter(), para que busque el arreglo de productos en el backend
			//Metodo filter para el autocomplete de angular material
			  private _filter(value: string): Observable<Producto[]> {
			    const filterValue = value.toLowerCase();
			    //filtra el valor pasado por parametro en el arreglo de productos
			    return this.facturasService.getFiltroProducto(filterValue);
			  }
		2.4.- Se modifica el atributo productosFiltrados, para que sea de tipo Onservable<Producto[]>
			 productosFiltrados: Observable<Producto[]>;
		2.5.- Se importa clase flatMap de @angular/operators
				import {map, flatMap} from 'rxjs/operators';
		2.6.- Se modifica metodo ngOnInit(),para que trabaje con el observable con flatMap
			ngOnInit(): void {
		    //Se asigna cliente al atributo factura
		    this.ActivatedRoute.paramMap.subscribe(params =>{
		      let clienteId = +params.get('clienteId');
		      this.clienteService.getCliente(clienteId).subscribe(cliente=>this.factura.cliente=cliente);
		    })
		    //recupero los productos filtrados
		    this.productosFiltrados = this.autoCompleteControl.valueChanges
		      .pipe(
		        //retorna todos antes de que cambie el imput
		        //startWith(''),
		        //Evalua si el item del observable es string en caso contrario accede al atributo nambre del obj
		        map(value => typeof value === 'string' ? value : value.nombre),
		        //Se evalua si hay valor lo retorna en caso contrario
		        flatMap(value => value ? this._filter(value): [])
		      );
		  }
	3.- Se modifica facturas.component.html, se modifica autocomplete para que busque por atributo del objeto producto.nombre
		 <div class="form-group row">
          <div class="col sm-6">
            <mat-form-field class="example-full-width">
              <input type="text" placeholder="Agregue Producto" aria-label="Number" matInput [formControl]="autoCompleteControl" [matAutocomplete]="auto" >
              <mat-autocomplete #auto="matAutocomplete">
                <mat-option *ngFor="let producto of productosFiltrados | async" [value]="producto">
                  {{producto.nombre}}
                </mat-option>
              </mat-autocomplete>
            </mat-form-field>
          </div>
        </div>
        3.1.- Se modifica el tag mat-autocomplete para que llame al metodo mostrar nombre pasandole el objeto productosFiltrados
        	<mat-autocomplete #auto="matAutocomplete" [displayWith]="mostrarNombre">
    	3.2.- Se modifica facturas.component.ts, se crea metodo mostrarNombre(), recibe un obj Producto y devuelve el objeto 
    	del mismo
    		//Metodo que resuelve el nombre del producto en la vista
		  public mostrarNombre(producto?:Producto):string | undefined{
		    return producto ? producto.nombre : undefined;
		  }
180.- ***********************************************************************************************Creando las lineas de la factura
	1.- Se modifica facturas.component.html, se le agrega evento al autoCompleteControl
		1.1.- Se le agrega evento al tag mat-autocomplete, evento (optionSelected)
			<mat-autocomplete #auto="matAutocomplete" [displayWith]="mostrarNombre" (optionSelected)="seleccionarProducto($event)">
		1.2.- Se agrega tabla que almacena cada linea de la factura
			<table class="table table-striped table-hover table-sm">
	          <thead>
	            <tr>
	              <th>Producto</th>
	              <th>Precio</th>
	              <th>Cantidad</th>
	              <th>Total</th>
	            </tr>
	          </thead>
	          <tbody>
	            <tr *ngFor="let item of factura.items">
	              <th>{{item.producto.nombre}}</th>
	              <th>{{item.producto.precio}}</th>
	              <th>{{item.cantidad}}</th>
	              <th>{{item.getTotal()}}</th>
	            </tr>
	          </tbody>
	        </table>
	    1.3.- Se modifica clase item-factura.ts, se crea metodo para calcular el total
	    	 public getTotal():number{
			    return this.cantidad*this.producto.precio;
			  }
	2.- Se modifica detalles.component.ts
		2.1.- Se importa MatAutocompleteSelectedEvent de @angular/material, se importa ItemFactura
			import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
			import { ItemFactura } from './models/item-factura';
		2.2.- Se crea metodo seleccionarProducto()
			seleccionarProducto(event: MatAutocompleteSelectedEvent): void{
			    //Se optiene el objeto del evento y se castea a producto
			    let producto = event.option.value as Producto;
			    console.log(producto);
			    
			    //se crea nuevo itemFactura
			    let newItem = new ItemFactura();
			    //Se le asigna el producto al nuevo Item
			    newItem.producto = producto;
			    //Se agrega la linea a la factura
			    this.factura.items.push(newItem);

			    //Se blanquea el autocomplete
			    this.autoCompleteControl.setValue('');
			    //Se deseclecciona
			    event.option.focus();
			    event.option.deselect();
			}
181.- *************************************************************************Actualizar en la vista cantidad de items de la factura
	1.- facturas.component.html, se modifica la cantidad 
		 <tbody>
            <tr *ngFor="let item of factura.items">
              <th>{{item.producto.nombre}}</th>
              <th>{{item.producto.precio}}</th>
              <!-- el metodo actCantidad se le pasa como parametro el evento que contiene la cantidad -->
              <th><input type="number" value="{{item.cantidad}}" class="form-control col-sm-4" (change)="actCantidad(item.producto.id,$event)"></th>
              <th>{{item.getTotal()}}</th>
            </tr>
          </tbody>
    2.- Se modifica facturas.component.ts, se crea metodo actCantiidad()
    	   actCantidad(idProducto:number,event:any): void{
		    //Se obtiene la cantidad del objeto event y se castea a number 
		    let cantidad = event.target.value as number; 

		    //se modifica la cantidad de cada item con el map
		    this.factura.items = this.factura.items.map((item:ItemFactura) =>{
		      //Se valida el id del producto para poder modificar la cantidad
		      if(idProducto === item.producto.id){
		        item.cantidad = cantidad;
		      }
		      return item;
		    })
		  }
182.- ********************************************************************Incrementar la cantidad si el producto existe en el detalle
	1.-Se modifica facturas.component.ts
		1.1.- Se crea metodo existeItem, valida si existe o no recorriendo la lista de items de la factura
			  existeItem(idProducto:number):boolean{
			    let existe = false;
			    this.factura.items.forEach((item:ItemFactura) => {
			      if(idProducto === item.producto.id){
			        existe = true;
			      }
			    });
			    return existe;
			  }
		1.2.- Se crea metodo incrementaCant()
			  incrementaCant(idProducto:number): void {
			    this.factura.items = this.factura.items.map((item:ItemFactura) =>{
			      if(idProducto === item.producto.id){
			        ++item.cantidad; 
			      }
			      return item;
			    });
			  }
		1.3.- Se modifica metodo seleccionarProducto()
			seleccionarProducto(event: MatAutocompleteSelectedEvent): void{
			    //Se optiene el objeto del evento y se castea a producto
			    let producto = event.option.value as Producto;
			    console.log(producto);

			    //Valida si existe e incrementa, en caso contrario, crea un item a la factura 
			    if(this.existeItem(producto.id)){
			      this.incrementaCant(producto.id);
			    }else{
			      //se crea nuevo itemFactura
			      let newItem = new ItemFactura();
			      //Se le asigna el producto al nuevo Item
			      newItem.producto = producto;
			      //Se agrega la linea a la factura
			      this.factura.items.push(newItem);
			    }   

			    //Se blanquea el autocomplete
			    this.autoCompleteControl.setValue('');
			    //Se deseclecciona
			    event.option.focus();
			    event.option.deselect();
			  }
183.- ******************************************************************************************************Eliminar linea de factura
	1.- Se modifica facturas.component.html
		1.1.- Se modifica heders de la tabla, se agrega campo Eliminar
			<thead>
            <tr>
              <th>Producto</th>
              <th>Precio</th>
              <th>Cantidad</th>
              <th>Total</th>
              <th>Eliminar</th>
            </tr>
          </thead>
        1.2.- Se modifica tbody de la tabla, se agrega boton para eliminar
        	<td><button class="btn btn-success btn-sm" type="button" (click)="deleteItem(item.producto.id)"></button></td>
    2.- Se modifica facturas.component
    	2.1.-se crea metodo para eliminar el iten de la factura
	    	//Metodo que elimina item
			  deleteItem(idProducto: number):void{
			    this.factura.items = this.factura.items.filter((item:ItemFactura)=> idProducto !== item.producto.id);
			  }  
		2.2.- Se modifica metodo actCantidad()
			actCantidad(idProducto:number,event:any): void{
			    //Se obtiene la cantidad del objeto event y se castea a number
			    let cantidad = event.target.value as number;

			    //Valida la cantidad, si es 0 elimina el item
			    if(cantidad == 0){
			      return this.deleteItem(idProducto);
			    }
			    //se modifica la cantidad de cada item con el map
			    this.factura.items = this.factura.items.map((item:ItemFactura) =>{
			      //Se valida el id del producto para poder modificar la cantidad
			      if(idProducto === item.producto.id){
			        item.cantidad = cantidad;
			      }
			      return item;
			    });
			  }
184.- ************************************************************************************************Calculo del total de la factura
	1.- Se modifica factura.ts, se crea metodo que calcula el total de la factura
		getTotalFactura(): number{
		    this.total = 0;
		    this.items.forEach((item:ItemFactura)=>{
		      this.total += item.getTotal();
		    });
		    return this.total;
		  }
	2.- Se modifica facturas.component.html
		<h5 class="float-right"><span class="badge badge-secondary">{{factura.getTotalFactura()}}</span></h5>
		2.1.- Se valida si hay items en la factura
			 <!-- Se valida si hay items en la factura -->
	        <div class="alert alert-info my-4" *ngIf="factura.items.length == 0">
	            La factura no posee items. Debe agregar al menos uno.
	        </div>
	    2.2.- Se modifica la tabla de items y el total de la factura, se valida si hay items en la factura
	    	2.2.1.- Se modifica table
	    		<table class="table table-striped table-hover table-sm" *ngIf="factura.items.length > 0">
	    	2.2.2.- Se modifica total
	    		<h2 class="float-right" *ngIf="factura.items.length" > 0>Total Factura:<span class="badge badge-secondary">{{factura.getTotalFactura()}}</span></h2>
185.- ************************************************************************************Implementar crearFactura(Backend)(Frontend)
	1.- Se modifica FacturaRestController.java, se crea metodo crearFactura()
		@PostMapping("/facturas")
		@ResponseStatus(HttpStatus.CREATED)
		public Factura crearFactura(@RequestBody Factura factura) {
			return factServ.saveFactura(factura);
		}	
	2.- Se modifica facturas.service.ts, se crea metodo crearFactura()
		  crearFactura(factura:Factura): Observable<Factura>{
		    return this.httpCliente.post<Factura>(this.urlEndPoint,factura);
		  }
	3.- Se modifica facturas.component.html, se le agrega evento al boton crear Factura 
		<div class="form-group row">
          <div class="col-sm-6">
            <input type="submit" value="Crear Factura"  (click)="crearFactura()" class="btn btn-secondary">
          </div>
        </div>
    4.- Se modifica facturas.component.ts, se crea metodo para la creacionde la factura
    	4.1.- Se importan las clases router y la libreria sweetalert2
    		import { ActivatedRoute, Router } from '@angular/router';
    		import Swal from 'sweetalert2';
    	4.2.- se inyecta en el constructor el obj Router
    		constructor(private clienteService: ClienteService,
              private facturasService: FacturasService,
              private ActivatedRoute: ActivatedRoute,
              private router:Router
            ) { }
        4.3.- se crea metodo
        	  //Creacion de factura
			  crearFactura(): void{
			    this.facturasService.crearFactura(this.factura).subscribe(factura => {
			      Swal.fire(this.titulo,`La Factura ${factura.descripcion} fue creada con exito.`, 'success');
			      this.router.navigate(['/clientes']);
			    })
			  }
186.- ********************************************************************************************Validacion de Formulario de Factura
	Validacion de formulario*********************************************************************************************************
	1.- Se modifica facturas.component.html
		1.1.- Se agrega validacion a campo descripcion Se exporta el ngModel, para tener una instancia del form-control que nos 
		permita saber si el campo es invalido para poder mostrar el error, debe poseer el mismo nomnre del campo 
		#descripcion="ngModel", en caso de ser invalido se puede mostrar el mensaje
			1.1.1.- Se agrega instancia del form-control
				<input type="text" class="form-control" name="descripcion" [(ngModel)]="factura.descripcion" required #descripcion="ngModel">
			1.1.2.- Se maneja el error de validacion
				<!-- el atributo touched valida si se hace click fuera del elemento -->
	            <div class="alert alert-danger" *ngIf="descripcion.invalid && descripcion.touched">
	              La descripcion es requerida
	            </div>
	    1.2.- Se agrega una instancia del form-group que es el que maneja el form-control de cada campo, por medio del cual podemos 
	    saber si es valido o no el formulario
	    	<form #facturaForm="ngForm">
	    	1.2.1.- Se deshabilita el boton por medio de la validacion de la instancia creada facturaForm y si los items son igual a 0
	    		 <input type="submit" value="Crear factura"  (click)="crearFactura()" class="btn btn-secondary"  
	    		 [disabled]="facturaForm.form.invalid || factura.items.length == 0">
	Validacion de formulario por submit**********************************************************************************************
		1.- Se quita la validacion de la directiva [disabled]
		2.- Se pasa facturaForm como parametro del metodo crearFactura()
			<input type="submit" value="Crear factura"  (click)="crearFactura(facturaForm)" class="btn btn-secondary">
		3.- Se modifica facturas.component.ts, se modifica el metodo crearFactura()
			 crearFactura(facturaForm): void{
		    console.log(this.factura);
		    //Se valida si la factura es igual a 0
		    if(this.factura.items.length == 0){
		      this.autoCompleteControl.setErrors({'invalid':true});
		    }
		    if(facturaForm.form.valid && this.factura.items.length > 0){
		      this.facturasService.crearFactura(this.factura).subscribe(factura => {
		        Swal.fire(this.titulo,`La Factura ${factura.descripcion} fue creada con exito.`, 'success');
		        this.router.navigate(['/clientes']);
		      });
		    }
		  }	
		4.- Se modifica el manejo de error en el campo descripcion
			<div class="alert alert-danger" *ngIf="descripcion.invalid && descripcion.touched || descripcion.invalid && facturaForm.submitted">
              La descripcion es requerida
            </div> 
        5.- Se le agrega manejo de error al autoComplete 
        	<div class="alert alert-danger" *ngIf="autoCompleteControl.invalid && facturaForm.submitted">
              La factura debe tener lineas.
            </div>
187.- ********************************************************************************************Reglas de Spring Security y Angular
	1.- Se modifica ResourceServerConfig, se eliminan los permitAll
		.antMatchers("/api/clientes/{id}").permitAll()
		.antMatchers("/api/facturas/**").permitAll()
	2.- Se modifica ClienteRestController
		2.1.- Se descomenta la @Secured del metodo showCliente()
			@Secured({"ROLE_ADMIN", "ROLE_USER"})
	3.- Se modifica FacturaRestController
		3.1.- Se modifica metodo show, se le agregan authorizacion por roles
			@Secured({"ROLE_ADMIN", "ROLE_USER"})
			@GetMapping("/facturas/{id}")
			@ResponseStatus(HttpStatus.OK)
			public Factura show(@PathVariable("id") Long idFactura) {
				return factServ.findById(idFactura);
			}
		3.2.- Se modifica metodo delete(), se le agregan authorizacion por roles
			@Secured({"ROLE_ADMIN"})
			@DeleteMapping("/facturas/{id}")
			@ResponseStatus(HttpStatus.NO_CONTENT)
			public void delete(@PathVariable("id") Long idFactura) {
				factServ.deleteFactura(idFactura);
			}
		3.3.- Se modifica metodo  crearFactura(), se le agrega authorizacion por rol
			@Secured({"ROLE_ADMIN"})
			@PostMapping("/facturas")
			@ResponseStatus(HttpStatus.CREATED)
			public Factura crearFactura(@RequestBody Factura factura) {
				return factServ.saveFactura(factura);
			}
	4.- Se modifica ProductoController
		4.1.- Se modifica metodo filtrarProducto(), se le agrega autorizacion por rol
			@Secured({"ROLE_ADMIN"})
			@GetMapping("/facturas/filtrarProducto/{term}")
			@ResponseStatus(HttpStatus.OK)
			public List<Producto> filtrarProducto(@PathVariable("term") String termino){
				return productoServ.findProductoByNombre(termino);
			}
	5.- (Angular) Se modifiica app.module.ts, se agrega seguridad a las rutas 'facturas/:id' y 'facturas/form/:clienteId'
		const routes: Routes =[
		  //pagina principal
		  {path: '', redirectTo: '/clientes', pathMatch: 'full'},
		  {path: 'directivas', component: DirectivasComponent},
		  {path: 'clientes', component: ClientesComponent},
		  //Ruta con paginacion
		  {path: 'clientes/page/:page', component: ClientesComponent},
		  //Se mapea la ruta al formulario
		  {path: 'clientes/form', component: FormComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ROLE_ADMIN'}},
		  //Url dond se renderiza metordo que busca cliente por id
		  {path: 'clientes/form/:id', component: FormComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ROLE_ADMIN'}},
		  //ruta para el upload de la imagen
		  //{path: 'clientes/ver/:id', component: DetalleComponent}
		  {path: 'login', component: LoginComponent},
		  {path: 'facturas/:id', component: DetalleFacturaComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ROLE_USER'}},
		  {path: 'facturas/form/:clienteId', component: FacturasComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ROLE_ADMIN'}}
		];
	6.- Se modifica clientes.component.html, se le añade authorizacion al detalle y crear factura
		6.1.- Se modifica head de la tabla, se le da autorizacion en la columna img y crear factura
			 <thead>
                <tr>
                    <th *ngIf="authService.hasRole('ROLE_USER')">Img</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>fecha</th>
                    <th>Email</th>
                    <th *ngIf="authService.hasRole('ROLE_ADMIN')">crear factura</th>
                    <th *ngIf="authService.hasRole('ROLE_ADMIN')">Editar</th>
                    <th *ngIf="authService.hasRole('ROLE_ADMIN')">Eliminar</th>
                </tr>
            </thead>
        6.2.- Se modifica el body de la tabla
        	<tbody>
                <!--Se añade condicional *ngFor-->
                <tr *ngFor="let cliente of clientes">
                    <td *ngIf="authService.hasRole('ROLE_USER')><img *ngIf="cliente?.foto" (click)="abrirModal(cliente)"
                      src="http://localhost:8080/api/uploads/img/{{cliente.foto}}" alt="{{cliente.foto}}"
                      class="img-thumbnail rounded" style="width: 64px; cursor: pointer;">
                      <img *ngIf="!cliente?.foto" (click)="abrirModal(cliente)"
                      src="http://localhost:8080/images/noUser.png" alt="Sin Imagen"
                      class="img-thumbnail rounded" style="width: 64px; cursor: pointer;">
                    </td>
                    <td>{{cliente.nombre}}</td>
                    <td>{{cliente.apellido}}</td>
                    <td>{{cliente.fecha | date:'EEEE dd, MMMM yyyy'}}</td>
                    <td>{{cliente.email}}</td>
                    <td *ngIf="authService.hasRole('ROLE_ADMIN')"><button class="btn btn-success btn-sm" type="button" [routerLink]="['/facturas/form', cliente.id]">Crear Factura</button></td>
                    <td *ngIf="authService.hasRole('ROLE_ADMIN')">
                        <button class="btn btn-primary btn-sm" type="button" name="editar" [routerLink]="['/clientes/form', cliente.id]">Editar</button>
                    </td>
                    <td *ngIf="authService.hasRole('ROLE_ADMIN')">
                        <button class="btn btn-danger btn-sm" type="button" name="eliminar" (click)="delete(cliente)">Eliminar</button>
                    </td>
                </tr>
            </tbody>
    7.- Se modifica detalle.component.html, se le agrega authorizacion a la tabla y al boton crear
    	7.1.- Se modifica el head de la tabla
	    	<thead>
	          <tr>
	            <th>IdFactura</th>
	            <th>Descripcion</th>
	            <th>Fecha</th>
	            <th>total</th>
	            <th>detalle</th>
	            <th *ngIf="authService.hasRole('ROLE_ADMIN')">eliminar</th>
	          </tr>
	        </thead>
	    7.2.- Se modifica el body
	    	<tbody>
              <tr *ngFor="let factura of cliente.facturas">
                <td>{{factura.id}}</td>
                <td>{{factura.descripcion}}</td>
                <td>{{factura.createAt}}</td>
                <td>{{factura.total}}</td>
                <td><button class="btn btn-primary btn-sm" type="button" [routerLink]="['/facturas', factura.id]">Ver</button></td>
                <td *ngIf="authService.hasRole('ROLE_ADMIN')"><button class="btn btn-danger btn-sm" type="button" (click)="deleteFactura(factura)">eliminar</button></td>
              </tr>
            </tbody>
        7.3.- Se agrega authorizacion al boton crear 
        	<div class="row" *ngIf="authService.hasRole('ROLE_ADMIN')">
                <button class="btn btn-success btn-sm" type="button" [routerLink]="['/facturas/form', cliente.id]">Crear Factura</button>
            </div>
188.- ************************************************************************Solucionando problema de recursion al editar un cliente
	1.- Modificacion en el backend Spring
		1.1- Se modifica Cliente.java, se modifica el JsonIgnoreProperties, se agrega atributo allowSetters
			//Se crea relacion con la clase Factura
			@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
			@JsonIgnoreProperties(value = {"clientes", "hibernateLazyInitializer", "handler"}, allowSetters = true)
			private List<Factura> facturas;	
			
		1.2.- Se modifica Factura.java, se modifica el JsonIgnoreProperties, se agrega atributo allowSetters
			@JsonIgnoreProperties(value = {"facturas", "hibernateLazyInitializer", "handler"}, allowSetters = true)
			@ManyToOne(fetch = FetchType.LAZY)
			private Cliente cliente;
	2.- Modificacion en FronEnd Angular
		2.1.- Se modifica form.component.ts, el metodo actualizar, se le setea a cliente.facturas = null, 
		lo que se quiere modificar es unicamente el cliente
			update():void{
    
			    this.cliente.facturas = null;
			    //Se llama a la clase de servici y se suscribe al metodo update
			    this.clienteService.update(this.cliente).subscribe(

			      cliente => {
			        //Se navega hasta la url clientes
			        this.router.navigate(['/clientes'])
			        //Se realiza interpolacion `` para poder mostrar el nombre del cliente modificado
			        Swal.fire('Cliente Actualizado', `Cliente ${cliente.nombre} actualizado con exito!`, 'success')
			      },
			      //Recorrido de errores del backend
			      err => {
			        this.errores =  err.error.errores as string[];
			        console.error('Codigo del error desde el backend '+err.status);
			        console.error(err.error.errores);
			      }
			    )
			  }
Seccion 18: Deployment: despliegue en servidores de produccion***********************************************************************
190.- ************************************************************************************Preparacion de proyectos para el despliegue
	1.- Se modifica ResourceServerConfig.java, se configura el cors, se permite el dominio
		1.1.- Se modifica corsConfigurationSource()
			conf.setAllowedOrigins(Arrays.asList("http://localhost:4200", "*"));
	2.- Se modifica FacturaRestController, se configura cors para todo dominio
		@CrossOrigin(origins = {"http://localhost:4200","*"})
	3.- Se modifica ProductoController.java, se configura cors para todo dominio
		@CrossOrigin(origins = {"http://localhost:4200","*"})
	4.- Se modifica ClienteRestController.java, se configura cors para todo dominio
		@CrossOrigin(origins= {"http://localhost:4200","*"})
	5.- (FrontEnd) Se modifica clientes.compoenet.ts
		5.1.- Se modifica el constructor, se cambia el modo de acceso del modalService, ya que en produccion se accede desde fuera
			 //Se crea variable en el const. y se le inyecta la clase de servicio
			  constructor(private clienteService: ClienteService,
			              private activatedRoute: ActivatedRoute,
			              public modalService: ModalService,
			              public authService: AuthService) { }	
	6.- Se modifica facturas.component.ts, se modifica el metodo crearFactura(), para que redirija al detalle de la factura
		crearFactura(facturaForm): void{
	    console.log(this.factura);
	    //Se valida si la factura es igual a 0
	    if(this.factura.items.length == 0){
	      this.autoCompleteControl.setErrors({'invalid':true});
	    }
	    if(facturaForm.form.valid && this.factura.items.length > 0){
	      this.facturasService.crearFactura(this.factura).subscribe(factura => {
	        Swal.fire(this.titulo,`La Factura ${factura.descripcion} fue creada con exito.`, 'success');
	        //this.router.navigate(['/clientes']);
	        this.router.navigate(['/facturas', factura.id]);
	      });
	    }
Seccion 20: Sistema de Chat en tiempo real con web socket****************************************************************************
204.- ************************************************************************************************Configurando servidorWeb Socket
	1.- Se modifica el pom.xml, se añaden las dependencias de mongodb y webSocket
		<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-mongodb -->
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-data-mongodb</artifactId>
		    <version>2.3.4.RELEASE</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-websocket -->
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-websocket</artifactId>
		    <version>2.3.4.RELEASE</version>
		</dependency>
	2.- Se crea clase de configuracion para el webSocket
		2.1.- Se crea paquete --> package com.eareiza.springAngular.webSocket;
		2.2.- Se crea clase WebSocketConfig en el paquete creado
			//Se añade anotacion de configuracion 
			@Configuration
			//Habilitacion del webSocketMessage
			@EnableWebSocketMessageBroker
			public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{...}
		2.3.- Se sobreescriben metodos registerStompEndpoints para el registro del endPoint y se configuran prefijos 
			para el envio de la mensajeria

				//Se registra EndPoint
				@Override
				public void registerStompEndpoints(StompEndpointRegistry registry) {
					//Se configura punto de acceso desde el cliente (Angular)
					registry.addEndpoint("/chat-webSocket")
					//Se configura el cors para cliente (Angular)
					.setAllowedOrigins("http://localhost:4200")
					// Configuracion para que nos permita el protocolo http 
					.withSockJS();
				}
				//Se configuran prefijos en el envio de la mensajeria
				@Override
				public void configureMessageBroker(MessageBrokerRegistry registry) {
					//Se configura prefijo para los eventos
					registry.enableSimpleBroker("/chat/");
					//se configura prefijo de destino
					registry.setApplicationDestinationPrefixes("/app");
				}
205.- ******************************************************************************************************* Creacion de controlador
	1.-Se crea clase controlador ChatController.java
		@Controller
		public class ChatController {			
			//Metodo donde se indica el destino de los mensajes del chat
			@MessageMapping("/mensaje")
			//Se configura el nombre del evento al que se le envia el mensaje
			@SendTo("/chat/mensaje")
			public Mensaje recibeMensaje(Mensaje mensaje) {
				mensaje.setFecha(new Date().getTime());
				mensaje.setTexto("Recibido por el broker: "+mensaje.getTexto());		
				return mensaje; 
			}			
		}

	2.- Se crea entidad de mongodb
		2.1.- Se crea paquete para las entidades de mongoDB -->  package com.eareiza.springAngular.model.entity.mongoDB;
		2.2.- Se crea entidad Mensaje
			public class Mensaje implements Serializable{

			private String texto;
			private Long fecha;
			
			public String getTexto() {
				return texto;
			}
			public void setTexto(String texto) {
				this.texto = texto;
			}
			public Long getFecha() {
				return fecha;
			}
			public void setFecha(Long fecha) {
				this.fecha = fecha;
			}

			private static final long serialVersionUID = 1L;
			
		}
206.- *****************************************************************************************************Modificaciones en FrontEnd
	1.- Se crea componente chat por consola
		ng g c chat
		1.1.- Se elimina el archivo .spect
	2.- Se modifica el app.module.ts
		al crear el componente se importo en el app.module.ts
		2.1.- Se configura la ruta de acceso al chat
			  //rutas para el chat
  			{path: 'chat', component: ChatComponent }
  		2.2.- registrar el modulo para formulario
  			import { FormsModule, ReactiveFormsModule } from '@angular/forms';
  			2.2.1.- se añade a los imports
  				imports: [
			    BrowserModule,
			    //Se registra el HttpClientModule
			    HttpClientModule,
			    FormsModule,
			    //Se registran las rutas creadas
			    RouterModule.forRoot(routes),
			    BrowserAnimationsModule,
			    MatDatepickerModule,
			    MatMomentDateModule,
			    //autocomplete angular material
			    ReactiveFormsModule, MatAutocompleteModule, MatInputModule, MatFormFieldModule
			  ],
207.- ***************************************************************Instalacion librerias sockjs y stompjs, para el cliente WebScket
	1.- Accedemos a npm , para copiar el comando de instalacion
		https://www.npmjs.com/package/@stomp/stompjs
		1.1.- Se instala por consola, se le añade --save para que lo agregue al package.json
			npm i @stomp/stompjs --save
		1.2.- Se accede a https://www.npmjs.com/package/sockjs-client y se copia el comando de instalacion
			npm i sockjs-client --save
		1.3.- Se instalan los tipos para sockjs
			Nota: los tipos de datos que maneja typescript para la librería de javascript sockjs, typescript como lenguaje es 
			fuertemente tipado y necesita los tipos para trabajar de forma más robusta y con menos posibilidad de errores.
			
			npm i @types/sockjs-client --save-dev
	2.- Se modifica chat.component.ts
		2.1.- Se importan las librerias
			import { Client } from '@stomp/stompjs';
			import * as SockJS from 'sockjs-client';
		2.2.- Se crea atributo de tipo Cliente, para conectarnos, suscribirnos a los eventos, enviar o publicar 
		mensajes al Broker
			private cliente: Cliente;
		2.3.- Se modifica el metodo ngOnInit()
			ngOnInit(): void {
				//Se inicializa el atributo client
			    this.client = new Client();
			    //Se hace la conexion Broker registrado como endpoint en el backend
			    this.client.webSocketFactory = () => {
			      return new SockJS("http://localhost:8080/chat-webSocket");
			    }
			    //tareas cuando nos conectamos, donde frame contiene toda la informacion de la conexion
			    this.client.onConnect = (frame) => {
			      console.log('Conectado: '+this.client.connected+' : '+frame);
			    }
			    //Se inicializa la conecion
			    this.client.activate();
			  }
	3.- Se modifica polyfills.ts al final, para configurar variable global y que sea compatible con todos los navegadores
		(window as any).global = window;
	4.- Se modifica en el backend la clase ResourceServerConfig.java, el metodo configure, se le agrega .antMatchers("/chat-webSocket/**").permitAll()
			@Override
			public void configure(HttpSecurity http) throws Exception {
				//Se permite a todos el accesso por el metodo get a "/api/clientes"
				http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/clientes", "/api/clientes/page/**", "/api/uploads/img/**", "/images/**").permitAll()
				.antMatchers("/chat-webSocket/**").permitAll()
		//		.antMatchers("/api/clientes/{id}").permitAll()
		//		.antMatchers("/api/facturas/**").permitAll()
				/*.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER","ADMIN")
				.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER","ADMIN")
				.antMatchers("/api/clientes/**").hasRole("ADMIN")*/		
				//Se valida que el cliente este autenticado
				.anyRequest().authenticated();
			}
208.- **********************************************************************************************************Mauqetacion Html Chat
	1.- Se modifica chat.component.html
		<div class="card mx-4 my-4">
		  <div class="card-header">
		    <ul class="nav nav-pills card-header-pills">
		      <li class="nav-item mr-2">
		        <button class="btn btn-success" type="button">Conectar</button>
		      </li>
		      <li class="nav-item">
		        <button class="btn btn-warning" type="button">Cerrar Chat</button>
		      </li>
		    </ul>
		  </div>

		  <div class="card-body" style="overflow-y: scroll; height: 360px;"></div>

		  <div class="card-footer">
		    <form  class="form-inline">
		      <div class="form-group mx-3">
		        <input type="text" class="form-control" placeholder="Esciba un mensaje" name="texto">
		      </div>
		      <button class="btn btn-primary" type="submit">Enviar</button>
		    </form>
		  </div>
		</div>
209.- **************************************************************************************Implementar evento conectar y desconectar
	1.- Se modifica chat.component.html
		1.1.- Se agrega evento conectar al boton 
			<button class="btn btn-success" type="button" (click)="conectar()" *ngIf="!conectado">Conectar</button>
		1.2.- Se agrega evento desconectar al boton correspondiente
			<button class="btn btn-warning" type="button" (click)="desconectar()" *ngIf="conectado">Cerrar Chat</button>
		1.3.- Se realiza modificacion al body y footer para que solo se muestre cuando esta conectado
			<div class="card-body" style="overflow-y: scroll; height: 360px;" *ngIf="conectado"></div>

			<div class="card-footer"*ngIf="conectado">
	2.- Se modifica chat.component.ts, se crean los metodos conectar y desconectar
		1.- Se modifica metodo ngOnInit(), se corta la inicializacion del chat y se copia en el metodo conectar
			 ngOnInit(): void {

			    this.client = new Client();
			    //Se hace la conexion Broker registrado como endpoint en el backend
			    this.client.webSocketFactory = () => {
			      return new SockJS("http://localhost:8080/chat-webSocket");
			    }
			    //tareas cuando nos conectamos, donde frame contiene toda la informacion de la conexion
			    this.client.onConnect = (frame) => {
			      console.log('Conectado: '+this.client.connected+' : '+frame);
			      this.conectado = true;
			    }
			    //tareas cuando nos desconectamos
			    this.client.onDisconnect = (frame) => {
			      console.log('Desconectado: '+!this.client.connected+' : '+frame);
			      this.conectado = false;
			    }

			  }
		2.- Se crea aributo publico boolean Conectado
			conectado: boolean = false;
		3.- Se crea metodo conectar()
			 conectar(): void{
			    //Se inicializa la conecion
			    this.client.activate();
			  }
		4.- Se crea metodo desconectar()
			desconectar(): void{
			    //Se desactiva la conecion
			    this.client.deactivate();
			  }
210.- ********************************************************************************Implementar el evento enviar y recibir mensajes
	1.- Se crea clase mensaje por consola 
		ng g class chat/models/mensaje
		1.1.- Se borra el archivo .spect
		1.2.- Se crean atributos de la clase
			export class Mensaje {
			  texto: string ="";
			  fecha: Date;
			}
	2.- Se modifica chat.component.ts
		2.1.- Se crea e instancia atributo de tipo Mensaje.ts
			mensaje : Mensaje = new Mensaje();
		2.2.- Se importa la clase Mensaje
			import { Mensaje } from './models/mensaje';
		2.3.- Se crea array de mensajes vacio
			mensajes : Mensaje[] = []
		2.4.- Se modifica metodo ngOnInit(), se suscribe al evento de envio demensajes
			ngOnInit(): void {

			    this.client = new Client();
			    //Se hace la conexion Broker registrado como endpoint en el backend
			    this.client.webSocketFactory = () => {
			      return new SockJS("http://localhost:8080/chat-webSocket");
			    }
			    //tareas cuando nos conectamos, donde frame contiene toda la informacion de la conexion
			    this.client.onConnect = (frame) => {
			      console.log('Conectado: '+this.client.connected+' : '+frame);
			      this.conectado = true;

			      //Nos susbcribimos al evento de envio de mensaje
			      this.client.subscribe('/chat/mensaje', (e)=>{
			        //Se captura y se castea el mensaje del endpoint backend
			        let mensaje: Mensaje = JSON.parse(e.body) as Mensaje;
			        mensaje.fecha = new Date(mensaje.fecha);
			        //Se agrega mensaje al array de mensajes
			        this.mensajes.push(mensaje);
			        console.log(mensaje);
			      })
			    }
			    //tareas cuando nos desconectamos
			    this.client.onDisconnect = (frame) => {
			      console.log('Desconectado: '+!this.client.connected+' : '+frame);
			      this.conectado = false;
			    }

			  }
		2.5.- Se crea metodo enviarMensaje()
			enviarMensaje(): void {
			    this.client.publish({destination: '/app/mensaje', body: JSON.stringify(this.mensaje)});
			    this.mensaje.texto ="";
			  }
	3.- Se modifica chat.component.html
		3.1.- Se modifica body para que muestre los mensajes
			<div class="card-body" style="overflow-y: scroll; height: 360px;" *ngIf="conectado">
			    <ul class="list-group">
			           <li class="list-group-item light" *ngFor="let mensaje of mensajes">
				        {{mensaje.fecha | date:'shortTime'}} : {{mensaje.texto}}
				      </li>
			    </ul>
			  </div>
		3.1.- Se asocia el input mensaje al atributo mensaje.texto
			<input type="text" class="form-control" [(ngModel)]="mensaje.texto" placeholder="Esciba un mensaje" name="texto">
      	3.3.- Se modifica el boton de enviar, se le añade evento click que invoca el metodo enviarMensaje()
      		<button class="btn btn-primary" type="submit" (click)="enviarMensaje()">Enviar</button>
211.- *****************************************************************************************Notificar cuando un usuario se conecta
	1.- Se modifica mensaje.ts, se agregan atributos para la notificacion de usurio
		username: string;
		tipo: string;
	2.- Se modifica chat.component.html, se coloca input antes del button conectar
		<li class="nav-item mr-2" *ngIf="!conectado">
		    <input type="text" class="form-control" [(ngModel)]="mensaje.username" placeholder="Tu username..." name="username">
      </li>
	    2.1.- Se modifica boton conectar´para que este hablitado unicamente si ingreso usuario
	    	<button class="btn btn-success" type="button" (click)="conectar()" *ngIf="!conectado" [disabled]="!mensaje.username">Conectar</button>
	3.- Se modifica chat.componentt.ts
		3.1.- Se modifica el metodo ngOnInit(), enviando mensaje al broker el el subscribe del onConnect
			  ngOnInit(): void {

			    this.client = new Client();
			    //Se hace la conexion Broker registrado como endpoint en el backend
			    this.client.webSocketFactory = () => {
			      return new SockJS("http://localhost:8080/chat-webSocket");
			    }
			    //tareas cuando nos conectamos, donde frame contiene toda la informacion de la conexion
			    this.client.onConnect = (frame) => {
			      console.log('Conectado: '+this.client.connected+' : '+frame);
			      this.conectado = true;

			      //Nos susbcribimos al evento de envio de mensaje
			      this.client.subscribe('/chat/mensaje', (e)=>{
			        //Se captura y se castea el mensaje del endpoint backend
			        let mensaje: Mensaje = JSON.parse(e.body) as Mensaje;
			        mensaje.fecha = new Date(mensaje.fecha);
			        //Se agrega mensaje al array de mensajes
			        this.mensajes.push(mensaje);
			        console.log(mensaje);
			      });
			      //Se envia mensaje al Broker
			      this.mensaje.tipo = 'MENSAJE_USUARIO';
			      this.client.publish({destination: '/app/mensaje', body: JSON.stringify(this.mensaje)});
			    }
		3.2.- Se modifica metodo enviarMensaje, se añade tipo de mensaje 
			enviarMensaje(): void {
			    this.mensaje.tipo = 'MENSAJE';
			    this.client.publish({destination: '/app/mensaje', body: JSON.stringify(this.mensaje)});
			    this.mensaje.texto ="";
			  }
	4.- (Backend Spring) se modifica clase Mensaje, se agregan atributos username y tipo
			private String username;
			private String tipo;
			
			public String getUsername() {
				return username;
			}
			public void setUsername(String username) {
				this.username = username;
			}
			public String getTipo() {
				return tipo;


			}
			public void setTipo(String tipo) {
				this.tipo = tipo;
			}
	5.- Se modifica ChatController.java, se valida si es nuevo usuario y se cambia el texto del mensaje
		//Metodo donde se indica el destino de los mensajes del chat
		@MessageMapping("/mensaje")
		//Se configura el nombre del evento al que se le envia el mensaje
		@SendTo("/chat/mensaje")
		public Mensaje recibeMensaje(Mensaje mensaje) {
			mensaje.setFecha(new Date().getTime());
			if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
				mensaje.setTexto("nuevo usuario");
			}	
			return mensaje; 
		}
	6.- Se modifica cha.component.html, se modifica body para que valide tipo de mensaje
		<div class="card-body" style="overflow-y: scroll; height: 360px;" *ngIf="conectado">
		    <ul class="list-group list-group-flush">
		      <li class="list-group-item light" *ngFor="let mensaje of mensajes">
		        <span *ngIf="mensaje.tipo=='NUEVO_USUARIO'">{{mensaje.fecha | date:'shortTime'}} @ {{mensaje.texto}} : {{mensaje.username}}</span>
		        <span *ngIf="mensaje.tipo=='MENSAJE'">{{mensaje.fecha | date:'shortTime'}} {{mensaje.username}} dice: <br>{{mensaje.texto}}</span>
		      </li>
		    </ul>
		  </div>
212.- *******************************************************************************************************Dar color a cada usuario
	1.- (Backend) Se modifica clase Mensaje, se le añade atributo color
		private String color;
	
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
	2.- Se modifica la clase ChatController.java, se le añade color al mensaje
		@Controller
		public class ChatController {
			
			private String[] colores = {"red" ,"blue","green", "brown", "orange", "purple"};
			
			//Metodo donde se indica el destino de los mensajes del chat
			@MessageMapping("/mensaje")
			//Se configura el nombre del evento al que se le envia el mensaje
			@SendTo("/chat/mensaje")
			public Mensaje recibeMensaje(Mensaje mensaje) {
				mensaje.setFecha(new Date().getTime());
				if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
					mensaje.setColor(colores[new Random().nextInt(colores.length)]);
					mensaje.setTexto("nuevo usuario");
				}	
				return mensaje; 
			}
		}
	3.- (FrontEnd) Se modifica Mensaje.ts, se le añade atributo color
		color:string;
	4.- Se modifica chat.component.ts, se modifica metodo ngOnInit(), se realiza validacion y se add color al mensaje
		ngOnInit(): void {

		    this.client = new Client();
		    //Se hace la conexion Broker registrado como endpoint en el backend
		    this.client.webSocketFactory = () => {
		      return new SockJS("http://localhost:8080/chat-webSocket");
		    }
		    //tareas cuando nos conectamos, donde frame contiene toda la informacion de la conexion
		    this.client.onConnect = (frame) => {
		      console.log('Conectado: '+this.client.connected+' : '+frame);
		      this.conectado = true;

		      //Nos susbcribimos al evento de envio de mensaje
		      this.client.subscribe('/chat/mensaje', (e)=>{
		        //Se captura y se castea el mensaje del endpoint backend (Broker)
		        let mensaje: Mensaje = JSON.parse(e.body) as Mensaje;
		        mensaje.fecha = new Date(mensaje.fecha);
		        //Se valida si existe color, tipo de mensaje del broker y si el usuario del broker es igual al usuario
		        if(!this.mensaje.color && mensaje.tipo == 'NUEVO_USUARIO' && this.mensaje.username == mensaje.username){
		          //Se añade al color del mensaje el que obtenemos del broker
		          this.mensaje.color = mensaje.color;
		        }

		        //Se agrega mensaje al array de mensajes
		        this.mensajes.push(mensaje);
		        console.log(mensaje);
		      });
		      //Se envia mensaje al Broker
		      this.mensaje.tipo = 'NUEVO_USUARIO';
		      this.client.publish({destination: '/app/mensaje', body: JSON.stringify(this.mensaje)});
		    }
		    //tareas cuando nos desconectamos
		    this.client.onDisconnect = (frame) => {
		      console.log('Desconectado: '+!this.client.connected+' : '+frame);
		      this.conectado = false;
		    }

		  }
	5.- Se modifica chat.component.html, se le agrega color a userName
		<div class="card-body" style="overflow-y: scroll; height: 360px;" *ngIf="conectado">
		    <ul class="list-group list-group-flush">
		      <li class="list-group-item light" *ngFor="let mensaje of mensajes">
		        <span *ngIf="mensaje.tipo=='NUEVO_USUARIO'">
		          {{mensaje.fecha | date:'shortTime'}} @ {{mensaje.texto}} : 
		          <span [ngStyle]="{'color': mensaje.color}">
		            {{mensaje.username}}
		          </span>
		        </span>
		        <span *ngIf="mensaje.tipo=='MENSAJE'">
		          {{mensaje.fecha | date:'shortTime'}} 
		          <span [ngStyle]="{'color': mensaje.color}">
		            {{mensaje.username}}
		          </span> 
		          dice: <br>{{mensaje.texto}}
		        </span>
		      </li>
		    </ul>
		  </div>
213.- ***********************************************************************************Notificacion de que usuario esta escribiendo
	1.- Se modifica ChatController.java, se crea metodo que notifica cuando un usuario esta escribiendo
		//Metodo que informa si un usuario esta escribiendo
		@MessageMapping("/escribiendo")
		@SendTo("/chat/escribiendo")
		public String escribiendo(String username) {
			return username.concat(" esta escribiendo");
		}	  
	2.- Se modifica chat.component.html, se modifica el input del footer, se crea evento para notificar que un usuario esta 
	escribiendo
		<input type="text" class="form-control" [(ngModel)]="mensaje.texto" placeholder="Esciba un mensaje" name="texto" (keyup)="escribiendoEvento()">
	3.- Se modifica chat.component.ts, se crea metodo escribiendoEvento()
		//Metodo que  publica si un usuario esta escribiendo
		  escribiendoEvento(): void {
		    this.client.publish({destination: '/app/escribiendo', body: this.mensaje.username});
		  }
		3.1.- Se crea  atributo escribiendo
			escribiendo: string;
		3.2.- Se modifica metodo ngOnInit(), se le añade subscribe que escuche si alguien esta escribiendo
			//Escucha si un usuario esta escribiendo
	      this.client.subscribe('chat/escribiendo',e=>{
	        this.escribiendo = e.body;
	        setTimeout(() => this.escribiendo="", 3000);
	      });
	    3.3.- Se modifica footer para mostrar quien esta escribiendoEvento
	    	 <div class="card-footer"*ngIf="conectado">
			    <form  class="form-inline">
			      <div class="form-group mx-3">
			        <!-- el evento keyup notifica cuando se esta escribiendo en el input -->
			        <input type="text" class="form-control" [(ngModel)]="mensaje.texto" placeholder="Esciba un mensaje" name="texto" (keyup)="escribiendoEvento()">
			      </div>
			      <button class="btn btn-primary" type="submit" (click)="enviarMensaje()">Enviar</button>
			      <div>{{escribiendo}}</div>
			    </form>
			    
			  </div>
	    3.4.- Se modifica bodi, se añade atributo para que el scroll este a la altura del ultimo mensaje
	    	<div #scrollChat [scrollTop]="scrollChat.scrollHight" class="card-body" style="overflow-y: scroll; height: 360px;" *ngIf="conectado">
214.- ************************************************************************************************************Instalacion MongoDB
	1.- se descarga mongoDB
		https://www.mongodb.com/try/download/community
	2.- Se configura variable de entorno 
		panel de control --> sistemas --> configuracion avanzada del sistema --> variables del entorno --> variables del sistema -->
		editas Path --> nuevo --> C:\Program Files\MongoDB\Server\4.4\bin
	3.- Se valida que este Instalando
		cmd --> mongo -version
215.- *********************************************************************************************************************BD mongoDB
	1.- Se crea el folder c:/data/db 
	2.- Se ejecuta en una terminal mongod 
	3.- Se ejecuta en otra terminal mongo
	4.- por consola insertamos varios documentos
		db.inventory.insertMany([
		   { item: "journal", qty: 25, size: { h: 14, w: 21, uom: "cm" }, status: "A" },
		   { item: "notebook", qty: 50, size: { h: 8.5, w: 11, uom: "in" }, status: "A" },
		   { item: "paper", qty: 100, size: { h: 8.5, w: 11, uom: "in" }, status: "D" },
		   { item: "planner", qty: 75, size: { h: 22.85, w: 30, uom: "cm" }, status: "D" },
		   { item: "postcard", qty: 45, size: { h: 10, w: 15.25, uom: "cm" }, status: "A" }
		]);
	5.- listamos lo insertado
		db.inventory.find( {} )
	6.- Mostrar un registro especifica
		db.inventory.find( { status: "D" } )
	7.- busqueda por varias condicionales
		db.inventory.find( { status: "A", qty: { $lt: 30 } } )
	Referencia --> https://docs.mongodb.com/manual/tutorial/query-documents/
216.- ************************************************************************************Instalacion de herramienta GUI para MongoDB









Comandos de Mongodb******************************************************************************************************************
	db            	--> muestra la bd actual
	use 			--> para usar una bd en especifico 
*************************************************************************************************************************************
Cambio de stylo en Angular Material**************************************************************************************************
	@import '@angular/material/prebuilt-themes/deeppurple-amber.css';
*************************************************************************************************************************************
Configuracion para que el log se escriba en un archivo*******************************************************************************
	logging.file = logfile.log //en la carpeta actual
	o
	logging.file = relativepath/to/logfile.log //ruta relativa con nombre del archivo
	o
	logging.file = /fullpath/to/logfile.log //ruta completa con nombre de archivo

	Referencia --> https://docs.spring.io/spring-boot/docs/1.4.0.RELEASE/reference/htmlsingle/#boot-features-logging-file-output
*************************************************************************************************************************************
Error que no encuentra el modulo @angular-devkit/build-angular***********************************************************************
	1.- Por consola instalar el modulo
	npm install --save-dev @angular-devkit/build-angular
*************************************************************************************************************************************
Configuracion del contextPath String Boot********************************************************************************************
	 	el application.properties lo puedes configurar con:

		server.servlet.contextPath=/servicios
*************************************************************************************************************************************
Chorcuts Visualcode******************************************************************************************************************
	Ctrl+P. 							Este te permite navegar a cualquier archivo del proyecto.
	Ctrl+,. 							Permite editar la configuración de Visual Studio Code.
	Ctrl+Mayúsculas+Tab. 				Te permite cambiar entre los diferentes archivos abiertos.
	Ctrl+Mayúsculas+P. 					Abre la paleta de comandos
	Ctrl+Mayúsculas+O. 					Para ir a una propiedad, función o método del archivo abierto.
	Ctrl+G. 							Para ir a una determinada línea del archivo.
	Ctrl+AvancePágina. 					Te mueve al editor de la derecha.
	Ctrl+RetrocesoPágina. 				Vas al editor de la izquierda.
	Ctrl+1. 							Te mueve al editor que esté mas a la izquierda.
	Ctrl+2. 							Vas al editor central.
	Ctrl+3. 							Te mueve al editor que esté mas a la derecha.
	Ctrl+W. 							Cierra el editor activo.
	Ctrl+K W. 							En este caso, cierra todos los editores del grupo.
	Ctrl+K Ctrl+W. 						Cierra todos los editores.
	Ctrl+K Z. 							Activa el modo zen. El modo zen te permite centrarte en el código en el que estás trabajando. 
	Para ello, oculta toda la interfaz gráfica excepto el propio editor. Es decir, oculta la barra de actividad, 
	la barra de estado, la barra lateral y el panel. Además activa el modo de pantalla completa. Para desactivar 
	el modo zen, tienes que escapar dos veces consecutivas.
	alt 									--> se selecciona con el cursor varias lineas
	ctrl+k  								--> selecciona la linea para luego comentar por ejemplo con ctrl+z
	alt+ flecha 							--> para mover la linea
	shift+alt+ flecha  						--> copia la linea o seccion seleccionada para abajo o arriba
	ctrl+Inicio 							--> va alinicio del archivo
	ctrl+Fin 								--> va al final del archivo
	./mvnw spring-boot:run 					--> correr proyecto springboot
	alt+click 								--> Se dirige al metodo o atributo que le hgamos click
	ctrl+shift+L 							--> selecciona todas las coincidencias
	cr+shift+k 								--> borra todas las lineas
	ctrl+z y ctrl+shift+z 					--> hacer y deshacer cambios
	ctrl+b  								--> enfoca en el codigos
	ctrl + k z 								--> modo zen, para escapar doble esc
	ctrl+}         							--> comenta linea
	ctrl+shift+p -->wrap with abrevation	--> engrapa la seccion de codigo
	ctrl+k ctrl+s 							--> metodos abreviados de teclado
		--> se configuro wrap con ctrl+alt+w 
	ctrl+tab 								--> menu con todas las pestañas abiertas
	ctrl+w 									--> cierra la pagina actual
	ctrl+k ctrl+w 							--> cierra todas las ventanas abiertas
	ctrl+shift+t 							--> reabre la ultima ventana
	ctrl + alt flecha						--> selecciona hacia abajo o hacia arriba
	***Multicursor y edicion rapida	 
*************************************************************************************************************************************
Base de Datos creacion de tablas*****************************************************************************************************
	drop database db_springboot_backend;
	create database db_springboot_backend;
	use db_springboot_backend;

	CREATE TABLE `productos` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `create_at` date DEFAULT NULL,
	  `nombre` varchar(255) DEFAULT NULL,
	  `precio` double DEFAULT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


	CREATE TABLE `regiones` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `nombre` varchar(255) DEFAULT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


	CREATE TABLE `clientes` (
	  `id` int(11) NOT NULL AUTO_INCREMENT,
	  `apellido` varchar(255) DEFAULT NULL,
	  `email` varchar(255) NOT NULL,
	  `create_at` date NOT NULL,
	  `foto` varchar(255) DEFAULT NULL,
	  `nombre` varchar(12) NOT NULL,
	  `region_id` bigint(20) NOT NULL,
	  PRIMARY KEY (`id`),
	  UNIQUE KEY `UK_1c96wv36rk2hwui7qhjks3mvg` (`email`),
	  KEY `FKf1kwxd4whuje3nv9yxddld4c3` (`region_id`),
	  CONSTRAINT `FKf1kwxd4whuje3nv9yxddld4c3` FOREIGN KEY (`region_id`) REFERENCES `regiones` (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

	CREATE TABLE `facturas` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `create_at` date DEFAULT NULL,
	  `descripcion` varchar(255) DEFAULT NULL,
	  `observacion` varchar(255) DEFAULT NULL,
	  `cliente_id` int(11) DEFAULT NULL,
	  PRIMARY KEY (`id`),
	  KEY `FK1qiuk10rfkovhlfpsk7oic0v8` (`cliente_id`),
	  CONSTRAINT `FK1qiuk10rfkovhlfpsk7oic0v8` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


	CREATE TABLE `facturas_items` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `cantidad` int(11) DEFAULT NULL,
	  `producto_id` bigint(20) DEFAULT NULL,
	  `factura_id` bigint(20) DEFAULT NULL,
	  PRIMARY KEY (`id`),
	  KEY `FKdumnm9x14hjfp9fufn7q8389r` (`producto_id`),
	  KEY `FKnv8ijya20df661b0p6drqcx7u` (`factura_id`),
	  CONSTRAINT `FKdumnm9x14hjfp9fufn7q8389r` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`),
	  CONSTRAINT `FKnv8ijya20df661b0p6drqcx7u` FOREIGN KEY (`factura_id`) REFERENCES `facturas` (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;



	CREATE TABLE `roles` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `nombre` varchar(20) DEFAULT NULL,
	  PRIMARY KEY (`id`),
	  UNIQUE KEY `UK_ldv0v52e0udsh2h1rs0r0gw1n` (`nombre`)
	) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


	CREATE TABLE `usuarios` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `apellido` varchar(255) DEFAULT NULL,
	  `email` varchar(255) DEFAULT NULL,
	  `enabled` bit(1) DEFAULT NULL,
	  `nombre` varchar(255) DEFAULT NULL,
	  `password` varchar(60) DEFAULT NULL,
	  `username` varchar(20) DEFAULT NULL,
	  PRIMARY KEY (`id`),
	  UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`),
	  UNIQUE KEY `UK_m2dvbwfge291euvmk6vkkocao` (`username`)
	) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


	CREATE TABLE `usuarios_roles` (
	  `usuario_id` bigint(20) NOT NULL,
	  `role_id` bigint(20) NOT NULL,
	  UNIQUE KEY `UKqjaspm7473pnu9y4jxhrds8r2` (`usuario_id`,`role_id`),
	  KEY `FKihom0uklpkfpffipxpoyf7b74` (`role_id`),
	  CONSTRAINT `FKihom0uklpkfpffipxpoyf7b74` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
	  CONSTRAINT `FKqcxu02bqipxpr7cjyj9dmhwec` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;

	INSERT INTO regiones (id, nombre) VALUES (1, 'Sudamérica');
	INSERT INTO regiones (id, nombre) VALUES (2, 'Centroamérica');
	INSERT INTO regiones (id, nombre) VALUES (3, 'Norteamérica');
	INSERT INTO regiones (id, nombre) VALUES (4, 'Europa');
	INSERT INTO regiones (id, nombre) VALUES (5, 'Asia');
	INSERT INTO regiones (id, nombre) VALUES (6, 'Africa');
	INSERT INTO regiones (id, nombre) VALUES (7, 'Oceanía');
	INSERT INTO regiones (id, nombre) VALUES (8, 'Antártida');

	INSERT INTO clientes (id,region_id, nombre, apellido, email, create_at) VALUES(1,1, 'Andrés', 'Guzmán', 'profesor@bolsadeideas.com', '2018-01-01');
	INSERT INTO clientes (id,region_id, nombre, apellido, email, create_at) VALUES(2,2, 'Mr. John', 'Doe', 'john.doe@gmail.com', '2018-01-02');
	INSERT INTO clientes (id,region_id, nombre, apellido, email, create_at) VALUES(3,4, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2018-01-03');
	INSERT INTO clientes (id,region_id, nombre, apellido, email, create_at) VALUES(4,4, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2018-01-04');
	INSERT INTO clientes (id,region_id, nombre, apellido, email, create_at) VALUES(5,4, 'Erich', 'Gamma', 'erich.gamma@gmail.com', '2018-02-01');
	INSERT INTO clientes (id,region_id, nombre, apellido, email, create_at) VALUES(6,3, 'Richard', 'Helm', 'richard.helm@gmail.com', '2018-02-10');
	INSERT INTO clientes (id,region_id, nombre, apellido, email, create_at) VALUES(7,3, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2018-02-18');
	INSERT INTO clientes (id,region_id, nombre, apellido, email, create_at) VALUES(8,3, 'John', 'Vlissides', 'john.vlissides@gmail.com', '2018-02-28');
	INSERT INTO clientes (id,region_id, nombre, apellido, email, create_at) VALUES(9,3, 'Dr. James', 'Gosling', 'james.gosling@gmail.com', '2018-03-03');
	INSERT INTO clientes (id,region_id, nombre, apellido, email, create_at) VALUES(10,5, 'Magma', 'Lee', 'magma.lee@gmail.com', '2018-03-04');
	INSERT INTO clientes (id,region_id, nombre, apellido, email, create_at) VALUES(11,6, 'Tornado', 'Roe', 'tornado.roe@gmail.com', '2018-03-05');
	INSERT INTO clientes (id,region_id, nombre, apellido, email, create_at) VALUES(12,7, 'Jade', 'Doe', 'jane.doe@gmail.com', '2018-03-06');


	/* Creamos algunos usuarios con sus roles*/
	INSERT INTO `usuarios` (id, username, password, enabled, nombre, apellido, email) VALUES (1,'andres','$2a$10$to/ZApT23BEQKuuaca3LYOlhrRhBNA.sUr3.p36Ydi9AyHh2L7J4a',1,'Andres','Espinoza','aespinoza@gmail.com');
	INSERT INTO `usuarios` (id, username, password, enabled, nombre, apellido, email) VALUES (2,'admin','$2a$10$Sx3KHjauh9sQsZy7YN3WtuLxUzAYGiLtLoVPnSbuBiAv197FvjZby',1,'Jhon','Doe','admin@gmail.com');


	INSERT INTO `roles` (id, nombre) VALUES (1,'ROLE_USER');
	INSERT INTO `roles` (id, nombre) VALUES (2,'ROLE_ADMIN');

	INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
	INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
	INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);

	/* Populate tabla productos */
	INSERT INTO productos (id,nombre, precio, create_at) VALUES(1,'Panasonic Pantalla LCD', 259990, NOW());
	INSERT INTO productos (id,nombre, precio, create_at) VALUES(2,'Sony Camara digital DSC-W320B', 123490, NOW());
	INSERT INTO productos (id,nombre, precio, create_at) VALUES(3,'Apple iPod shuffle', 1499990, NOW());
	INSERT INTO productos (id,nombre, precio, create_at) VALUES(4,'Sony Notebook Z110', 37990, NOW());
	INSERT INTO productos (id,nombre, precio, create_at) VALUES(5,'Hewlett Packard Multifuncional F2280', 69990, NOW());
	INSERT INTO productos (id,nombre, precio, create_at) VALUES(6,'Bianchi Bicicleta Aro 26', 69990, NOW());
	INSERT INTO productos (id,nombre, precio, create_at) VALUES(7,'Mica Comoda 5 Cajones', 299990, NOW());

	/* Creamos algunas facturas */
	INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());

	INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 3, 1);
	INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 3, 4);
	INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 3, 5);
	INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 3, 7);
*************************************************************************************************************************************
Implementacion de aplicacion SpringBoot en servidor tomcat***************************************************************************
	1.- Se crea war del aplicativo
		1.1.- Se modifica el pom.xml
			 Agregue lo siguiente justo después del <description>nodo en su pom.xml.
			<packaging>war</packaging>
		1.2.- Elimine el servidor Tomcat incorporado agregando lo siguiente a su lista de dependencias:

			<dependency>
			   <groupId>org.springframework.boot</groupId>
			   <artifactId>spring-boot-starter-tomcat</artifactId>
			   <scope>provided</scope>
			</dependency>
	2.- Se modifica la clase SpringAngularApplication, se extiende de SpringBootServletInitializer 
		@SpringBootApplication
		public class SpringAngularApplication /*implements CommandLineRunner*/ extends SpringBootServletInitializer{

	Referencia:
	implementar springboot
	https://developer.okta.com/blog/2019/04/16/spring-boot-tomcat
*************************************************************************************************************************************
Implementar angular en Tomcat********************************************************************************************************
	https://www.youtube.com/watch?v=Fda_EJYra-c
	1.- Se transpilado de la aplicacion, estocrea una carpeta dist
		ng build --prod 
	2.- Se busca dentro de la carpeta dist-->clientes-app--> index.html
		<base href="clientesApp">
	3.- Se crea carpeta clienteApp en el servidor tomcat opt/apache-tomcat-8.5.54/webapps y se copian todos los archivos del proyecto
	en esta carpeta
		http://66.228.61.76/clientesApp/clientesApp/
************************************************************************************************************************************
Convertir a json o string con la libreria JSON**************************************************************************************
	*Convierte de objeto a string
		JSON.stringify(this.mensaje)
	*Convierte a json 
		JSON.parse(e.body)
************************************************************************************************************************************







   1. *crear cuenta*







Aplicacion de geolacalizaciom
***********************************
	*Premisas
		*Dado una ip obtenga informacion asociada del pais al que pertenece
			* nombre y codigo ISO  
			* El idioma Oficial
			* Hora actual del pais, si el pais cubre mas de una zona horaria mostrar todas
			* Distancia estimada entre BAs y el pais en Kms
			* Moneda local y cotizacion en dolares(si esta disponible)
		
		* Basado en informacion anterior 
			* Distancia mas lejana a buenos aires desde la cual se haya consultado el servicio
			* Distancia mas cercana ....
			* Dsitancia Promedio de todas las ejecuciones del servicio
			* Tomar en consideracion la fluctuaciones agresivas del servicio entre 100 y un millon por segundo --> averiguar de upsert

	* apis a consultar
		● Geolocalización de IPs: https://ip2country.info/
		● Información de paises: http://restcountries.eu/
		● Información sobre monedas: http://fixer.io/
			key --> b49556d595487883252ecfd8fbcabb78
			raiz --> http://data.fixer.io/api/


Referencias:
https://jarroba.com/gson-json-java-ejemplos/
https://opensofty.com/es/2019/11/25/que-es-upsert-y-como-hacerlo-en-mysql/
http://expertojava.ua.es/experto/restringido/2014-15/rest/rest.html
https://donnierock.com/2015/03/16/calculando-la-distancia-entre-doos-coordenadas-en-java/
https://sacavix.com/2020/04/14/dockerizando-una-app-spring-boot/
https://medium.com/@hansleolml/docker-spring-boot-mysql-2eb59bc9a622


Documentar  --> Jautodoc  --> Referencia  --> https://evelb.es/autodocumentar-codigo-con-jautodoc/
CTRL + ALT + J


DateUtil.getZonedTime(countryCodeInfo.getTimeZones().get(x)) + " ("+countryCodeInfo.getTimeZones().get(x)+") "

CREATE TABLE `estadisticasip` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` date DEFAULT NULL,
  `distancia` double DEFAULT NULL,
  `fecha_modificacion` date DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `nombre_destino` varchar(255) DEFAULT NULL,
  `nombre_origen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

https://github.com/mauricionrgarcia/examen-mercadolibre-mutante/tree/master/src/main/java/com/mercadolibre/exam/mutant

https://github.com/kelvyns/mutants-service#arquitectura


mvn install para la creacion del jar
docker build -t dockerbasico
docker run -it -p 8080:8080 dockerbasico


puntos pendientes
prueba unitarias
endpoints



prueba 2
docker --version
docker info
docker pull hello-world
docker run hello-world
docker run -d -p 6033:3306 --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=fraudemeli" mysql
docker image ls
docker container ls
docker exec -it docker-mysql bash
se ingresa en mysql y se valida la creacion de bd -- showdatabase;
Se crea Dockerfile
	FROM openjdk:8-jdk-slim
	COPY "./target/Geolocalizacion-0.0.1-SNAPSHOT.jar" "appmeli.jar"
	EXPOSE 8080
	ENTRYPOINT ["java","-jar","appmeli.jar"]

docker build -f Dockerfile -t spring-meli .
docker run -t --link docker-mysql:mysql -p 8080:8080 spring-meli



when(this.userRepository.findByUsername(TestUtil.VALID_USER_USERNAME)).thenReturn(userActive);







<div class="container">
    <!-- Tabla de productos -->
    <div class="col-lg-12 col-md-12" >
        <div class="card">
            <div class="card-header card-header-info" >
                <h4 class="card-title">Facturas</h4>
                <p class="card-category">Compras al costo</p>
            </div>
            <div class="card-body table-responsive">
                <p-table #dt [value]="facturas"   dataKey="id" styleClass="p-datatable-customers" [rowHover]="true"
                [rows]="10" [showCurrentPageReport]="true" [rowsPerPageOptions]="[10,25,50]" [loading]="loading"
                [paginator]="true" currentPageReportTemplate="Showing {first} to {last} of {totalRecords} entries"
                [filterDelay]="0" *ngIf="facturas?.length > 0" [columns]="scrollableCols" [frozenColumns]="frozenCols" 
                [scrollable]="true" scrollHeight="200px" frozenWidth="300px">
                    <ng-template pTemplate="caption">
                        <div class="table-header">
                            Lista de Facturas                         
                        </div>                        
                    </ng-template>
                    <ng-template pTemplate="colgroup" let-columns>
                        <colgroup>
                            <col *ngFor="let col of columns" style="width:200px">
                        </colgroup>
                    </ng-template>
                    <ng-template pTemplate="header" let-columns>
                        <tr>
                            <th *ngFor="let col of columns">
                                {{col.header}}
                            </th>
                        </tr>
                    </ng-template>
                    <ng-template pTemplate="body" let-factura let-columns="columns">
                        <tr>
                            <td *ngFor="let col of columns">
                                <div [ngSwitch]="col.field">
                                    <div *ngSwitchCase="'createAt'">{{ factura[col.field] | date: 'short' }}</div>
                                    <div *ngSwitchCase="'mercadopago'">{{ factura[col.field] | number }}</div>
                                    <div *ngSwitchCase="'pedidosya'">{{ factura[col.field] | number }}</div>
                                    <div *ngSwitchCase="'puntoventa'">{{ factura[col.field] | number }}</div>
                                    <div *ngSwitchCase="'montocomision'">{{ factura[col.field] | number }}</div>
                                    <div *ngSwitchCase="'pedidosyaefectivo'">{{ factura[col.field] | number }}</div>
                                    <div *ngSwitchDefault>{{ factura[col.field] }}</div>
                                </div> 
                            </td>
                        </tr>
                    </ng-template>
                    <ng-template pTemplate="emptymessage">
                        <tr>
                            <td colspan="8">No customers found.</td>
                        </tr>
                    </ng-template>
                </p-table>
            </div>
        </div>
    </div>
</div>
