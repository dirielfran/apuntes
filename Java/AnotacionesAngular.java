Comandos angular-cli
ng new [nombre aplicativo] 			--> Creacion de aplicativo angular
ng generate class footer.component  --> crea clase
ng serve -o                         --> corre en el servidor la aplicacion
ng generate component directivas    --> crea un componente 
ng g c components/heroeTarjeta --skipTests=true  --> Creacion de componente sin el archivo de prueba
ng g c clientes/form --flat 		--> Crea componente con el -- flat se le indica que no cree folder
ng generate service cliente         --> Se crea clase de servicio
ng g m miModulo						--> Genera un modulo propio
ng g m template --routing 			--> Genera modulo con su routing
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
[routerLinkActiveOptions]			--> Se le indica que este activo siempre y cuando el path sea exactamente igual
										[routerLinkActiveOptions]="{exact:true}"
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


************************************************************Angular Fit***************************************************************
**************************************************************************************************************************************
**************************************************************************************************************************************
Seccion 4: Introduccion a Angular*****************************************************************************************************
*******************************************************************************************************************************Modulos
	Referencia --> https://desarrolloweb.com/articulos/trabajar-modulos-angular.html
	Un módulo es uno de los elementos principales con los que podemos organizar el código de las aplicaciones en Angular. No deben 
	ser desconocidos hasta este momento del Manual de Angular, puesto que nuestra aplicación básica ya disponía de uno.

	Sin embargo, en lugar de colocar el código de todos los componentes, directivas o pipes en el mismo módulo principal, lo 
	adecuado es desarrollar diferentes módulos y agrupar distintos elementos en unos u otros. El orden se realizará de una manera 
	lógica, atendiendo a nuestras propias preferencias, el modelo de negocio o las preferencias del equipo de desarrollo.
	
	Creacion de Modulo********************************************************************************************************
		ng generate module nombre

		Si abrimos el código del módulo generado "nombre.module.ts", encontraremos cómo se define un módulo en Angular. La 
		parte más importante es, como ya viene siendo habitual en Angular, un decorador. El decorador de los módulos se 
		llama @NgModule.

			@NgModule({
			  imports: [
			    CommonModule
			  ],
			  declarations: []
			})

		Como ves en el decorador, tienes de momento un par de arrays definidos:

			imports: con los imports que este módulo necesita
			declarations: con los componentes, u otros artefactos que este module construye.

	Generar un componente dentro de un modulo**********************************************************************************
		Hasta ahora todos los componentes que habíamos creado habían sido generados dentro del módulo principal, pero si
		queremos podemos especificar otro módulo donde crearlos, mediante el comando:

			ng generate component nombre/miComponente

		El comando del CLI también modificará el código del módulo, agregando automáticamente el import del 
		componente y su referencia en el array "declarations". Ahora el código del módulo "nombre-modulo.module.ts" tendrá una 
		forma como esta:

			import { NgModule } from '@angular/core';
			import { CommonModule } from '@angular/common';
			import { MicomponenteComponent } from './micomponente/micomponente.component';

			@NgModule({
			  imports: [
			    CommonModule
			  ],
			  declarations: [
			    MiComponenteComponent
			  ]
			})
			export class NombreModuloModule { }
	Exportar del módulo hacia afuera*******************************************************************************************
		Más adelante, si queremos que este módulo exponga cosas hacia afuera, que se puedan llegar a utilizar desde otros 
		módulos, tendremos que agregar una nueva información al decorador del módulo: el array de exports.

		Vamos a suponer que el componente "MiComponenteComponent" queremos que se pueda usar desde otros módulos. Entonces 
		debemos señalar el nombre de la clase del componente en el array de "exports". Con ello el decorador del module 
		quedaría de esta manera.

			@NgModule({
			  imports: [
			    CommonModule
			  ],
			  declarations: [
			    MiComponenteComponent
			  ],
			  exports: [
			    MiComponenteComponent
			  ]
			})
	
	Declarar el import en el decorador del module principal***************************************************************
		La importación de nuestro módulo se realiza en la declaración "imports" del módulo principal.

		Este es el código del decorador del módulo principal.

			@NgModule({
			  declarations: [
			    AppComponent
			  ],
			  imports: [
			    BrowserModule,
			    NombreModule
			  ],
			  providers: [],
			  bootstrap: [AppComponent]
			})
**************************************************************************************************************************************
54.- ***********************************************************************************************************Creacion de modulo dbz
	1.- Se crea modulo dbz
		ng g m dbz

		import { NgModule } from '@angular/core';
		import { CommonModule } from '@angular/common';
		import { MainPageComponent } from './main-page/main-page.component';



		@NgModule({
		  declarations: [MainPageComponent],
		  imports: [
		    CommonModule
		  ]
		})
		export class DbzModule { }
	2.- Se crea componente mainPage
		ng g c dbz/mainPage
	3.- Se modifica dbz.module.ts, se crea el componente como exports

		import { NgModule } from '@angular/core';
		import { CommonModule } from '@angular/common';
		import { MainPageComponent } from './main-page/main-page.component';



		@NgModule({
		  declarations: [MainPageComponent],
		  imports: [
		    CommonModule
		  ],
		  exports:[
		    MainPageComponent
		  ]
		})
		export class DbzModule { }
	4.- Se modifica app.module.ts, Se agrega el modulo dbz al modulo principal
		@NgModule({
		  declarations: [
		    AppComponent,
		  ],
		  imports: [
		    BrowserModule,
		    HeroesModule,
		    ContadorModule,
		    DbzModule
		  ],
		  providers: [],
		  bootstrap: [AppComponent]
		})
		export class AppModule { }
	5.- Se modifica app.component.html, se le agrega el componente creado
		<app-main-page></app-main-page>
**************************************************************************************************************************************
55.- ***************************************************************************************************************Deseño de pantalla
	1.- Se modifica main-page.component.html
		<h1>Dragon Ball Z</h1>
		<hr>
		<div class="row">
		    <div class="col">
		        <h3>Personajes</h3>
		        <ul>
		            <li>Krillin - 700</li>
		            <li>Goku - 15,000</li>
		            <li>Veguetta - 8,500</li>
		        </ul>
		    </div>
		    <div class="col">
		        <h3>Agregar</h3>
		        <hr>
		        <form action="">
		            <input type="text" placeholder="Nombre">
		            <input type="number" placeholder="poder">
		            <button>Agregar</button>
		        </form>
		        
		    </div>
		</div>
	2.- Se modifica style.css, se le agregan clases
		.row {
		    display: flex;
		}

		.col {
		    flex-grow: 1;
		    margin-right: 10px;
		}

		input {
		    display: block;
		    margin: 5px;
		}
	3.- Se corre la aplicacion
		ng serve -o
**************************************************************************************************************************************
56.- ***********************************************************************************************************************FormModule
	1.- Se modifica dbz.module.ts, se le agrega FormModule a los imports
	2.- Se modifica main-page.component.ts, se modifica  metodo agregar() 
		  agregar( ){
			   console.log('Heyyy!!!');
			  }
	3.- Se modifica main-page.component.html, se utiliza directiva (ngSubmit)
		<form (ngSubmit)="agregar()">
**************************************************************************************************************************************
57.- **************************************************************************************************************************ngModel
	1.- Se modifica main-page.component.ts, se crea interface Personaje y se crea atributo de tipo de la interface
		1.1.- Se crea interface
			interface Personaje {
			  nombre: string;
			  poder: number;
			}
		1.2.- Se crea atributo de tipo personaje
			  nuevo : Personaje = {
			    nombre: 'Trunck',
			    poder: 14000
			  }
		1.3.- Se modifica metodo agregar(), se imprime por consola el objeto nuevo 
			  agregar( ){
				   console.log(this.nuevo);
				  }
	2.- Se modifica main-page.component.html, se le agrega directiva [value] para el binding alos input
            <input type="text" placeholder="Nombre" name="nombre" [(ngModel)]="nuevo.nombre">
            <input type="number" placeholder="poder" name="poder" [(ngModel)]="nuevo.poder">
        2.1.- Se modifica titulo, se le agrega impresion de atributo nuevo.nombre
        	<h3>Agregar <small>{{nuevo.nombre}}</small></h3>
**************************************************************************************************************************************
58.- ******************************************************************************************************************Mostrar listado
	1.- Se modifica main-page.component.ts, se agrega arreglo de personaje
		personajes: Personaje[] = [];
		Se modifica el metodo agregar()
		2.1.- se valida que nombre tenga contenido
			if(this.nuevo.nombre.trim().length === 0){ return };
		2.2.- Se pushea el personaje en el arreglo
			this.personajes.push(this.nuevo);
		2.3.- Se blanquea el atributo nuevo
			    this.nuevo = {
		      nombre: '',
		      poder : 0
		    }
	2.- Se modifica main-page.component.html
		2.- Se renderiza el arreglo con la directiva *ngFor
			<li *ngFor="let pers of personajes">
                {{pers.nombre}} - {{pers.poder | number}}
            </li>
**************************************************************************************************************************************
59.- ******************************************************************************************************Creacion de componente hijo
	1.- Se crea componente personajes dentro del modulo dbz
		ng g c dbz/personajes --skipTests
	2.- Se modifica main-page.components.html
		2.1.- Se comenta la rederizacion de la lista 
			        <!-- <h3>Personajes</h3>
			        <ul>
			            <li *ngFor="let pers of personajes">
			                {{pers.nombre}} - {{pers.poder | number}}
			            </li>
			        </ul> -->
		2.2.- Se agrega el componente creado de personajes
			<div class="col">
		        <app-personajes></app-personajes>
		        <!-- <h3>Personajes</h3>
		        <ul>
		            <li *ngFor="let pers of personajes">
		                {{pers.nombre}} - {{pers.poder | number}}
		            </li>
		        </ul> -->
		    </div>
	3.- Se modifica personajes.component.html, se copia renderizacion de la lista
		<h3>Personajes</h3>
		<ul>
		    <li *ngFor="let pers of personajes">
		        {{pers.nombre}} - {{pers.poder | number}}
		    </li>
		</ul>
	4.- Se modifica personajes.component.ts, se crea atributo personajes
		personajes: any[] = [];
**************************************************************************************************************************************
60.- ***************************************************************************************************************************@Input
	1.- Se crea folder dentro del modulo dbz --> Interfaces
	2.- Se crea archivo interface --> personaje.interface.ts y se copia la interface de main-page
			export interface Personaje {
				nombre: string;
			  poder: number;
			}
	3.- Se modifica personajes.compoent.ts, se le agrega tipaje al atributo personajes y el imput
			@Input() personajes: Personaje[] = [];
	4.- Se modifica main-page.component.ts, se corta la interface y se importa del archivo creado de interface
	5.- Se modifica main-page.componet.html
			<app-personajes [personajes]="personajes"></app-personajes>
**************************************************************************************************************************************
61.- *****************************************************************************************************************@Input y Modulos
	1.- Se crea compoente --> ng g c dbz/agregar --skipTests
	2.- Se modifica main-page.component.html
		2.1.- se corta objetos de agregar
		2.2.- Se agrega el componente agregar
			<app-agregar [personajes]="personajes"></app-agregar>
	3.- Se modifica agregar.component.html, se pega los elements cortados de agregar
		<h3>Agregar <small>{{nuevo.nombre}}</small></h3>
		<hr>
		<form (ngSubmit)="agregar()">
		    <input type="text" placeholder="Nombre" name="nombre" [(ngModel)]="nuevo.nombre">
		    <input type="number" placeholder="poder" name="poder" [(ngModel)]="nuevo.poder">
		    <button type="submit">Agregar</button>
		</form>
	4.- Se modifca agregar.component.ts
		1.- Se agrega metodo agregar() y objeto nuevo del componente main-page
			nuevo : Personaje = {
			    nombre : '',
			    poder : 0
			  }

			  agregar( ){
			    if(this.nuevo.nombre.trim().length === 0){ return };
			    this.personajes.push(this.nuevo);
			    console.log(this.nuevo);
			    this.nuevo = {
			      nombre: '',
			      poder : 0
			    }
			    console.log(this.personajes);
			  }
		2.- Se le agrega input para el pase de obj del padre al hijo
			@Input() personajes: Personaje[] = [];
**************************************************************************************************************************************
62.- **********************************************************************************************************@OutPuts y EventEmitter
	1. Se modifica agregar.componet.ts
		1.- Se importa Output de la libreria @angular/core
		2.- Se agrega atributo @output que emite el personaje
			@Output() emitirPresonaje: EventEmitter<Personaje> = new EventEmitter();
		3.- Se modifica el metodo agregar() para pasarle el personaje por emitter
			this.emitirPresonaje.emit(this.nuevo);
	2.- Se modifca main-page.component.html para escuchar e evento OutPuts
		<app-agregar [personajes]="personajes" (emitirPresonaje)="agregarPersonaje($event)"></app-agregar>
	3.- Se modifca main-page.component.ts, se agrega metodo agregarPersonaje()
		agregarPersonaje( argumento: Personaje){
		    this.personajes.push(argumento);
		  }
**************************************************************************************************************************************
64.- *****************************************************************************************************************Servicios Manual
	1.- Se crea folder dentro del modulo dbz --> services
	2.- Se crea archivo --> dbz.service.ts
		import { Injectable } from "@angular/core";


		@Injectable()
		export class DbzService{
		  constructor(){
		    console.log('Servicio inicializado');
		  }
		}
	3.- Se modifica main-page.component.ts, se le agrega inyeccion de dependencia del servicio
		constructor( public dbzService: DbzService) { }
**************************************************************************************************************************************
65.- ***********************************************************************************Centralizar acceso a personajes en el servicio
	1.- Se modifica la clase de servicio dbz.service.ts
		1.1.- Se le agrega arreglo
			private  _personajes: Personaje[] = [{
			      nombre: 'Goku',
			      poder: 8000
			    },
			    {
			      nombre: 'Vegueta',
			      poder: 7000
			    }
			  ];
		1.2.- Se le agrega metodo getter personajes
			get personajes(): Personaje[]{
			    //Operador spread:
			    return [...this._personajes];
			  }
			Nota: Al anteponer los tres puntos que representan al spread operator transformamos la variable personajes 
			 en una lista de argumentos. Es como si le quitáramos los corchetes ( “[]” ) al array.
		1.3.- Se crea metodo agregarPersonaje()
			  agregarPersonaje(personaje: Personaje): void{
			    this._personajes.push(personaje);
			  }
	2.- Se modifica main-page.component.html
		<h1>Dragon Ball Z</h1>
		<hr>
		<div class="row">
		    <div class="col">
		        <app-personajes></app-personajes>
		        <!-- <h3>Personajes</h3>
		        <ul>
		            <li *ngFor="let pers of personajes">
		                {{pers.nombre}} - {{pers.poder | number}}
		            </li>
		        </ul> -->
		    </div>
		    <div class="col">
		        <app-agregar></app-agregar>
		    </div>
		</div>
	3.- Se modifica main-page.component.ts
		@Component({
		  selector: 'app-main-page',
		  templateUrl: './main-page.component.html',
		  styleUrls: ['./main-page.component.css']
		})
		export class MainPageComponent implements OnInit {


		  constructor( ) {
		  }

		  ngOnInit(): void {
		  }
		}
	4.- Se modifica agregar.component.ts, se comentan los input y OutPuts
		export class AgregarComponent implements OnInit {

		  //@Input() personajes: Personaje[] = [];

		  //@Output() emitirPresonaje: EventEmitter<Personaje> = new EventEmitter();

		  constructor( private dbzService: DbzService) { }

		  ngOnInit(): void {
		  }

		  nuevo : Personaje = {
		    nombre : '',
		    poder : 0
		  }

		  agregar( ){
		    if(this.nuevo.nombre.trim().length === 0){ return };
		    //this.emitirPresonaje.emit(this.nuevo);
		    this.dbzService.agregarPersonaje(this.nuevo);
		    console.log(this.nuevo);
		    this.nuevo = {
		      nombre: '',
		      poder : 0
		    }
		  }
		}
	5.- Se modifica Personajes.component.ts
		export class PersonajesComponent implements OnInit {

		  //@Input() personajes: Personaje[] = [];
		  get personajes(): Personaje[]{
		    return this.dbzService.personajes;
		  }

		  constructor(private dbzService: DbzService) { }

		  ngOnInit(): void {
		  }

		}
**************************************************************************************************************************************
Seccion 7: GifsApp aplicacion para buscar imagenes************************************************************************************
75.- **************************************************************************************************************Inicio del proyecto
	1.- Se crea proyecto --> ng new gifsApp
	2.- Se modifica el index y se le agrega el link a bootstrap 5 .CSS
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	3.- Se corre el proyecto --> ng serve -o
**************************************************************************************************************************************
76.- *******************************************************************************************************************Diseño inicial
	1.- Se modifica app.component.html, se le añade diseño
		<div class="d-flex">
		    <!-- Sidebar -->
		    <div class="bg-dark border-right p-3" id="sidebar">
			    <h3 class="text-light">GifsApp</h3>
			    <hr class="text-white">
			    <div class="list-group list-reset">
			        <a href="#" class="list-group-item list-group-item-action">
			      Dashboard
			    </a>
			    </div>
			</div>
		    <!-- end sidebar -->
		    <div class="container">
		        <div class="row p-3">
		            <div class="col">
		                <h5>Buscar:</h5>
		                <input type="text" class="form-control" placeholder="Buscar gifs...">
		            </div>
		        </div>
		        <hr>

		        <div class="row">
		            <div class="col">
		                Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat ut id officia vitae voluptas repudiandae, 
		                pariatur distinctio cum error quas sapiente maxime consequatur minima amet asperiores, quam perferendis 
		                delectus ipsum!
		            </div>
		        </div>
		    </div>
		</div>
**************************************************************************************************************************************
77.- ********************************************************************************************************************Module shared
	1.- Se genera un modulo --> ng g m shared
	2.- Se importa el modulo en el app.module.ts
	3.- Se genera componente sidebar --> ng g c shared/sidebar --skipTests
	4.- Se configura el componente para ser exportado en el shared.module.ts
		@NgModule({
		  declarations: [SidebarComponent],
		  imports: [
		    CommonModule
		  ],
		  exports:[
		    SidebarComponent
		  ]
		})
		export class SharedModule { }
	5.- Se corta la parte de sidebar de app.component.html y se pega en sidebar.component.html
**************************************************************************************************************************************
78.- *****************************************************************************************************GifsModule y sus componentes
	1.- Se genera modulo gifs enn raiza --> ng g m gifs
	2.- Se importa el modulo en al app.module.ts
	3.- Se crean componentes
		3.1.- Se crea componente buscador --> ng g c gifs/buscador
		3.2.- Se crea componente resultado --> ng g c gifs/resultado
	4.- Se modifica buscador.component.html, se copia la parde buscador de gifs-page.component.htm
	5.- Se modifica resultado.component.html, se copia la parde resultado de gifs-page.component.htm
**************************************************************************************************************************************
79.- ********************************************************************************@ViewChild Obtener referencias a objetos del html
	1.- Se obtiene referencia de elemento html
  		@ViewChild('txtbuscar') txtBuscar!:ElementRef<HTMLInputElement>;
  	2.- Se modifica metodo buscar(), se obtiene valor del elemento referenciado y se blanquea
  		buscar(){
	    const valor = this.txtBuscar.nativeElement.value;
	    console.log(valor);
	    this.txtBuscar.nativeElement.value = '';
	  }
	3.- Se elmimina del keyup.enter el argumento en la llamada al metodo buscar()
		<input type="text" class="form-control" placeholder="Buscar gifs..." (keyup.enter)="buscar( )" #txtbuscar>
**************************************************************************************************************************************
80.- ****************************************************************************************************Clase de servicio gifsService
	1.- Se crea servicio --> ng g s gifs/services/gifs
	2.- Se modifica la clase de servicio
		2.1.- Se crea atributo de tipo string[]
			private _historial: string[] = [];
		2.2.- Se crea getter
			 get historial(): string[]{
			    return [...this.historial];
			  }
		2.3.- Se crea metodo buscargifs()
			buscarGifs( query: string){
			    this._historial.unshift( query );
			    console.log(this._historial)
			  }
	3.- Se modifica buscador.component.ts, se modifica metodo buscar(), se le agrega el llamado a la clase de servicio para guardar
	el nuevo valor
		this.gifsService.buscarGifs(valor);
	4.- Se modifica sidebar.component.ts
		4.1.- Se importa la clase de servicio
			import { GifsService } from '../../gifs/services/gifs.service';
		4.2.- Se realiza la inyeccion de dependencia
			 constructor( private gifsService: GifsService ) { }
		4.3.- Se crea getter que obtienen el historial de la clase de servicio
			  get historico(): string[]{
			    return this.gifsService.historial;
			  }
	5.- Se modifica sidebar.component.html, se renderiza la lista historico con la directiva *ngFor
		<a href="#" class="list-group-item list-group-item-action" *ngFor="let cadena of historico">
          {{cadena}}
        </a>
**************************************************************************************************************************************
81.- **************************************************************************************************Controlar historial de busqueda
************************************************************************************************************************Pipe titlecase
	1.- Se modifica buscador.component.ts, se modifica metodo buscar()
		//Se valida que no incluyan cadenas vacia
    	if(valor.trim().length === 0){ return;}
    2.- Se modifica gifs.service.ts, se modifica el metodo buscarGifs(), se valida un nuemro especidico de tarjetas
    	buscarGifs( query: string){
		    this._historial.unshift( query );
		    this._historial = this._historial.slice(0,9);
		    console.log(this._historial)
		  }
		2.1.- Se valida que no existan repetidos
			buscarGifs( query: string){
			    query = query.toLowerCase();
			    //Se valida que no tenga repetidos la lista
			    if( !this.historial.includes(query)){
			      this._historial.unshift( query );
			      this._historial = this._historial.slice(0,9);
			    }

			    console.log(this._historial)
			  }
	3.- Se modifica sidebar.component.html, se utliza pipe titlecase
		<a href="#" class="list-group-item list-group-item-action" *ngFor="let cadena of historico">
          {{cadena | titlecase}}
        </a>
**************************************************************************************************************************************
82.- *************************************************************************************************Giphy Api Key - Giphy Developers
	https://developers.giphy.com/  --> api que nos provee los gifs
	Api Key: 2b47rkZmZZg8uRXBmOmiQHUPiJT19zqk
	1.- Se crea una palicacion en Giphy, se copia la key para la aplicacion
	2.- Se prueba servicio en postman, se copia url de la documentacion de giphy y se prueba
		GET --> http://api.giphy.com/v1/gifs/trending?api_key=2b47rkZmZZg8uRXBmOmiQHUPiJT19zqk
**************************************************************************************************************************************
83.- ********************************************************************************************************************Peticion Http
	1.- Se prueba con un fetch de JavaScript y luego se comentan

	    //Sentencia fetch de javascript
		fetch('http://api.giphy.com/v1/gifs/trending?api_key=2b47rkZmZZg8uRXBmOmiQHUPiJT19zqk&limit=20&q=grdragon ball')
		      .then(  resp => {
		          resp.json().then( data => {
		          console.log(data)
		        })
		      })

	2.- Se prueba con fetch wait  y luego se comentan
		2.1.- Se le coloca async al metodo
			  /* async */ buscarGifs( query: string){
		2.2.- Se realiza el fetch
			const resp = await fetch('http://api.giphy.com/v1/gifs/trending?api_key=2b47rkZmZZg8uRXBmOmiQHUPiJT19zqk&limit=20&q=grdragon ball')
		      const data = await resp.json();
		      console.log(data) 

	3.- Se realiza la peticion utilizando HttpClient
		1.- Se importa en el app.module.ts
			imports: [
			    BrowserModule,
			    HttpClientModule,
			    SharedModule,
			    GifsModule
			  ],
		2.- Se modifica gifs.service.ts
			2.1.- Se realiza inyeccion de dependencia del httpClient
				constructor( private http: HttpClient) { }
			2.2.- Se modifica metodo buscargifs()
			    this.http.get('http://api.giphy.com/v1/gifs/trending?api_key=2b47rkZmZZg8uRXBmOmiQHUPiJT19zqk')
			        .subscribe( (resp: any) => {
			          console.log(resp.data);
			        })
**************************************************************************************************************************************
84.- *************************************************************************************************************Rederizar resultados
	1.- Se modifica gifs.service.ts, se modifca peticion http para que se encruste la variable query
		this.http.get(`http://api.giphy.com/v1/gifs/trending?api_key=2b47rkZmZZg8uRXBmOmiQHUPiJT19zqk&limit=20&q=${query}`)
	        .subscribe( (resp: any) => {
	          console.log(resp.data);
	          this.resultados = resp.data;
	        })
	2.- Se modifca resultados.component.ts
		2.1.- Se realiza inyeccion de dependecia del servicio
			constructor( private gifsService: GifsService) { }
		2.2.- Se crea getter para buscar resultados de la clase de servicio
			get resultados(){
		    return this.gifsService.resultados;
		  }
	3.- Se modifica resultados.component.html
		<div class="row">
		    <div class="col-md-4 col-sm-6" *ngFor="let gif of resultados">
		        <div class="card">
		            <img [src]="gif.images.downsized_medium.url" [alt]="gif.title" class="top">
		        </div>
		        <div class="card-body">
		            <p class="card-text">{{gif.title}}</p>
		        </div>
		    </div>
		</div>
*************************************************************************************************************************************
85.- ******************************************************************************************Colocando tipado a las peticiones http
	Referencia  --> https://quicktype.io/ --> nos convierte un json en un tipado o viceversa
	1.- Se copia el json de respuesta del postman y se convierte en un tipaje typescript en la pagina de referencia
	2.- Se crea interface en la carpeta gifs/interfaces/gif.interfaces.ts y se copia el contenido del paso 1
	3.- Se modifica gifs.service.ts, se le agrega tipado a los resultados
		3.1.- Se le agrega tipado al atributo resultados
			public resultados: Gif[] = [];
		3.2.- Se le agrega tipado a la peticion http.get
			this.http.get<SerchGIFResponse>(`http://api.giphy.com/v1/gifs/trending?api_key=2b47rkZmZZg8uRXBmOmiQHUPiJT19zqk&limit=20&q=${query}`)
		        .subscribe( (resp ) => {
		          console.log(resp.data);
		          this.resultados = resp.data;
		        })
*************************************************************************************************************************************
86.- ********************************************************************************************************************LocalStorage
	1.- Se modifica gifs.service.ts, se modifica metodo buscargifs() para que almacene en el LocalStorage

		  /* async */ buscarGifs( query: string){
		    query = query.toLowerCase();
		    //Se valida que no tenga repetidos la lista
		    if( !this.historial.includes(query)){
		      this._historial.unshift( query );
		      this._historial = this._historial.slice(0,9);
		      //Se guarda en el localstorage
		      localStorage.setItem("historial", JSON.stringify(this._historial));
		    }
		1.1.- Se modifica el constructor para que obtenga el historial de localstorage
			  constructor( private http: HttpClient) {
			  	//Obtengo del localstorage
			    this._historial = JSON.parse(localStorage.getItem('historial')!) || [];
			   }
*************************************************************************************************************************************
87.- ***************************************************************************************************************Carga de imagenes
	1.- Se modifica gifs.service.ts, se modifica metodo buscargifs() para que almacene en el LocalStorage
	    this.http.get<SerchGIFResponse>(`http://api.giphy.com/v1/gifs/trending?api_key=2b47rkZmZZg8uRXBmOmiQHUPiJT19zqk&limit=20&q=${query}`)
	        .subscribe( (resp ) => {
	          console.log(resp.data);
	          this.resultados = resp.data;
	          //Se almacenan las respuestas en el localstorage
	          localStorage.setItem("resultados", JSON.stringify(this.resultados));
	        })
	1.1.- Se modifica el constructor para que obtenga el historial de localstorage
		  constructor( private http: HttpClient) {
		    //Obtener informacion del localstorage
		    this._historial = JSON.parse(localStorage.getItem('historial')!) || [];
		    this.resultados = JSON.parse(localStorage.getItem('resultados')!) || [];
		   }
*************************************************************************************************************************************
88.- ***********************************************************************************************Obtener Imagenes desde el sidebar
	1.- Se modifica sidebar.component.ts, se crea metodo buscar()
		  buscar( query:string): void {
		    this.gifsService.buscarGifs( query );
		  }
	2.- Se modifica sidevar.component.html, se agrega la llamada al metodo buscar desde un evento click
		<div class="bg-dark border-right p-3" id="sidebar">
		    <h3 class="text-light">GifsApp</h3>
		    <hr class="text-white">
		    <div class="list-group list-reset" *ngFor="let cadena of historico">
		        <a href="#" class="list-group-item list-group-item-action" (click)="buscar(cadena)">
		          {{cadena | titlecase}}
		        </a>
		    </div>
		</div>
*************************************************************************************************************************************
89.- **********************************************************************************************************************HttpParams
	1.- Se modifica clase de servicio gifs.service.ts
		1.1.- Se crea constante para el endpoiint
			private urlEndPoint: string = 'http://api.giphy.com/v1/gifs'
		1.2.- Se modifica metodo buscargifs(), se crea constante para los parametros de HttpParams
			   const params = new HttpParams()
                  .set('api_key', this.apiKey)
                  .set('limit', '10')
                  .set('q', query);
        1.3.- Se modifica metodo buscargifs(), se modifica el metodo http get()
        	 this.http.get<SerchGIFResponse>(`${this.urlEndPoint}/search`, {params:params})
		        .subscribe( (resp ) => {
		          console.log(resp.data);
		          this.resultados = resp.data;
		          //Se almacenan las respuestas en el localstorage
		          localStorage.setItem("resultados", JSON.stringify(this.resultados));
		        })
*************************************************************************************************************************************
90.- ***************************************************************************************************************Animate.style CSS

	Refrencia --> https://animate.style/
*************************************************************************************************************************************
Seccion 8: SPA-paisesApp*************************************************************************************************************
95.- *************************************************************************************************************Inicio del proyecto
	1.- Se crea carpeta del proyecto paisesApp
	2.- Se ejecuta dentro del forder --> ng new paisesApp
	3.- Referencia de api para paises  --> https://restcountries.eu/
	4.- Se modifica el index.html, se copia el link de bootstrap CSS
		<!-- CSS only -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" 
		integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	5.- Se crean directorios dentro de app
		shared  --> menu 
				--> sidebar
				--> footer
		
		paises  --> components
				--> interfaces
				--> pages
				--> services
*************************************************************************************************************************************
97.- *******************************************************************************************Creando componentes y modulos basicos
	1.- Se crean modulos de shared y paises
		1.1.- 	--> ng g m shared
				--> ng g m paises
	2.- Se crean los componentes
		--> ng g c shared/sidebar --skipTests -is
		--> ng g c paises/pages/porCapital --skip-tests -is
		--> ng g c paises/pages/porPais --skip-tests -is
		--> ng g c paises/pages/porRegion --skip-tests -is
		--> ng g c paises/pages/verPais --skip-tests
	3.- Se modifica el shared.module.ts, se configura para exportacion el SidebarComponent
		@NgModule({
		  declarations: [SidebarComponent],
		  exports: [
		    SidebarComponent
		  ],
		  imports: [
		    CommonModule
		  ]
		})
		export class SharedModule { }
	4.- Se modifica paises.module.ts, se exportan los componentes a Exportar
		@NgModule({
		  declarations: [
		    VerPaisComponent, 
		    PorRegionComponent, 
		    PorPaisComponent, 
		    PorCapitalComponent
		  ],
		  exports: [
		    VerPaisComponent, 
		    PorRegionComponent, 
		    PorPaisComponent, 
		    PorCapitalComponent
		  ],
		  imports: [
		    CommonModule
		  ]
		})
		export class PaisesModule { }
	5.- Se modifica el app.module.ts, se configuran en los imports del decorator los modulos creados
		@NgModule({
		  declarations: [
		    AppComponent
		  ],
		  imports: [
		    BrowserModule,
		    SharedModule,
		    PaisesModule
		  ],
		  providers: [],
		  bootstrap: [AppComponent]
		})
		export class AppModule { }

	6.- Se modifica el app.component.html y se le agregan los componentes creados para probar
		<app-por-capital></app-por-capital>
		<app-por-pais></app-por-pais>
		<app-por-region></app-por-region>
		<app-sidebar></app-sidebar>
		<app-ver-pais></app-ver-pais>
*************************************************************************************************************************************
98.- *************************************************************************************************************Estructura del html
	1.- Se modifica app.component.html
		<div class="row container mt-4">
		    <div class="col-3">
		        <h2>Busqueda</h2>
		        <hr>
		        <ul class="list-group">
		            <li class="list-group-item">Buscar Pais</li>
		            <li class="list-group-item">Por Region</li>
		            <li class="list-group-item">Por Capital</li>
		        </ul>
		    </div>
		    <div class="col">
		        <h2>Por Region</h2>
		        <hr>
		        <app-por-region></app-por-region>
		    </div>
		</div>
*************************************************************************************************************************************
99.- ********************************************************************************************************************RouterModule
	1.- Se crea en el app un archivo app-routing.module.ts
	2.- Se modifica el archivo creado
		import { NgModule} from '@angular/core';
		import { RouterModule, Routes } from '@angular/router';
		import { PorPaisComponent } from './paises/pages/por-pais/por-pais.component';
		import { PorRegionComponent } from './paises/pages/por-region/por-region.component';
		import { PorCapitalComponent } from './paises/pages/por-capital/por-capital.component';
		import { VerPaisComponent } from './paises/pages/ver-pais/ver-pais.component';

		const routes: Routes = [
		  {path:'', component: PorPaisComponent, pathMatch:'full'},
		  {path:'region', component:PorRegionComponent},
		  {path:'capital', component: PorCapitalComponent},
		  {path:'pais/:id', component: VerPaisComponent},
		  {path:'**', redirectTo:''}
		];

		@NgModule({
		  imports:[ RouterModule.forRoot(routes)],
		  exports: [ RouterModule ]
		})
		export class AppRoutingModule{

		}
	3.- Se modifica app.module.ts, se configura el modulo en los imports
		  imports: [
		    BrowserModule,
		    SharedModule,
		    PaisesModule,
		    AppRoutingModule
		  ],
	4.- Se modifica app.component.html, se agrega componente router-outlet
		<div class="row container mt-4">
		    <div class="col-3">
		        <h2>Busqueda</h2>
		        <hr>
		        <ul class="list-group">
		            <li class="list-group-item">Buscar Pais</li>
		            <li class="list-group-item">Por Region</li>
		            <li class="list-group-item">Por Capital</li>
		        </ul>
		    </div>
		    <div class="col">
		        <router-outlet></router-outlet>
		    </div>
		</div>
*************************************************************************************************************************************
100.- *********************************************************************************************************************RouterLink
	1.- Se modifica app.component.html, se le agregan atributos routerLink a cada elemento para que lo enlace a los componentes
		<div class="row container mt-4">
		    <div class="col-3">
		        <h2>Busqueda</h2>
		        <hr>
		        <ul class="list-group">
		            <li class="list-group-item" routerLink="" routerLinkActive="active" [routerLinkActiveOptions]="{exact:true}">Buscar Pais</li>
		            <li class="list-group-item" routerLink="region" routerLinkActive="active">Por Region</li>
		            <li class="list-group-item" routerLink="capital" routerLinkActive="active">Por Capital</li>
		        </ul>
		    </div>
		    <div class="col">
		        <router-outlet></router-outlet>
		    </div>
		</div>
*************************************************************************************************************************************
101.- *********************************************************************************Separa componente sidebar del app.component.ts
	1.- Se modifica app.component.html
		1.1.- Se corta lo referente al sidebar y se coloca en sidebar.component.html
			<h2>Busqueda</h2>
			<hr>
			<ul class="list-group">
			    <li class="list-group-item" routerLink="" routerLinkActive="active" [routerLinkActiveOptions]="{exact:true}">Buscar Pais</li>
			    <li class="list-group-item" routerLink="region" routerLinkActive="active">Por Region</li>
			    <li class="list-group-item" routerLink="capital" routerLinkActive="active">Por Capital</li>
			</ul>
		1.2.- Se agrega componente app-sidebar 
	2.- Se modifica en shared.module.ts, se importa el modulo RouterModule
		imports: [
		    CommonModule,
		    RouterModule
		  ]
	3.- Se modifica sidebar.component.ts, se modifica el decorator para agregar estilo css NullPointerException
		@Component({
		  selector: 'app-sidebar',
		  templateUrl: './sidebar.component.html',
		  styles: [`
		      li{
		        cursor:pointer;
		      }
		    `
		  ]
		})
*************************************************************************************************************************************
102.- ************************************************************************************************Componente para buscar por pais
	1.- Se modifica paises.module.ts, se importa el FormsModule
		  imports: [
		    CommonModule,
		    FormsModule
		  ]
	2.- Se modifica por-pais.component.html
		<h2>Por Paiss</h2>
		<hr>

		<div class="row">
		    <div class="col">
		        <form action="" autocomplete="off" (ngSubmit)="buscar()">
		            <input type="text" class="form-control" name="termino" placeholder="Buscar Pais..." [(ngModel)]=termino>
		        </form>
		    </div>
		</div>
		<hr>

		<div class="alert alert-danger">
		    No se encontro el termino.
		</div>

		<div class="row">
		    <div class="col">
		        <table class="table table-hover">
		            <thead>
		                <tr>
		                    <th>#</th>
		                    <th>Bandera</th>
		                    <th>Nombre</th>
		                    <th>Poblacion</th>
		                    <th></th>
		                </tr>
		            </thead>
		            <tbody>

		            </tbody>
		        </table>
		    </div>
		</div>
	3.- Se modifica por-pais.component.ts, se agrega atributo termino y metodo buscar()
		termino: string = "";

		  buscar(){
		    console.log(this.termino);
		  }
*************************************************************************************************************************************
103.- ****************************************************************************************************Servicio para buscar paises
	1.- Se modifica app.module.ts, se importa el modulo HttpClientModule
		  imports: [
		    BrowserModule,
		    AppRoutingModule,
		    HttpClientModule,
		    SharedModule,
		    PaisesModule
		  ],
	2.- Se modifica paises.service.ts
		2.1.- Se realiza inyeccion de dependencia del HttpClient
			constructor( private http: HttpClient ) { }
		2.2.- Se crea metodo que buscaPaises segun un termino
			  buscarPais( termino: string):Observable<any>{
			    return this.http.get(`${this.urlEndPoint}/name/${termino}`)
			  }
		2.3.- Se crea atributo con el urlEndPoint
			private urlEndPoint ='https://restcountries.eu/rest/v2';
	3.- Se modifica por-pais.component.ts se agrega el servicio que busca el pais
		3.1.- e realiza inyeccion de dependencia del servicio
			constructor( private paisesService: PaisService ) { }
		3.2.- Se modifica metodo buscar()
			  buscar(){
			    this.paisesService.buscarPais( this.termino )
			      .subscribe(
			          resp => {
			            console.log(resp);
			          }
			      );
			  }
*************************************************************************************************************************************
104.- **************************************************************************************************************Manejo de Errores
	1.- Se modifica por-pais.component.ts
		1.1.- Se crea atriburo necesario como bandera si hay error
			  hayError: boolean = false;
		1.2.- Se modifica el metodo buscar() para manejar el error
			buscar(){
			    this.hayError = false;
			    this.paisesService.buscarPais( this.termino )
			      .subscribe(
			          resp => {
			            console.log(resp);
			          }, error => {
			            this.hayError = true;
			            console.log('Error');
			            console.log(error);
			          }
			      );
			  }
	2.- Se modifica por-pais.component.html, se añade manejo del mensaje y tabla con la directiva *ngIf
		2.1.- Se maneja la table segun error
			<table class="table table-hover" *ngIf="!hayError">
		2.2.- Se maneja mensaje segun error
			<div class="alert alert-danger" *ngIf="hayError">
			    No se encontro el termino {{termino}}
			</div> *
*************************************************************************************************************************************
105.- *******************************************************************************************************Tipado de las peticiones
	1.- Se crea interface country
		1.1.- Se pureba en postam el servicio --> GET --> https://restcountries.eu/rest/v2/name/Venezuela y se copia body
		1.2.- Se utiliza --> https://app.quicktype.io/ para combertir el jacon en tipado typescript
		1.3.- Se crea interface y se copia el tipado del paso anterior
			export interface Country {
			  name:           string;
			  topLevelDomain: string[];
			  alpha2Code:     string;
			  alpha3Code:     string;
			  callingCodes:   string[];
			  capital:        string;
			  altSpellings:   string[];
			  region:         string;
			  subregion:      string;
			  population:     number;
			  latlng:         number[];
			  demonym:        string;
			  area:           number;
			  gini:           number;
			  timezones:      string[];
			  borders:        string[];
			  nativeName:     string;
			  numericCode:    string;
			  currencies:     Currency[];
			  languages:      Language[];
			  translations:   Translations;
			  flag:           string;
			  regionalBlocs:  RegionalBloc[];
			  cioc:           string;
			}

			export interface Currency {
			  code:   string;
			  name:   string;
			  symbol: string;
			}

			export interface Language {
			  iso639_1:   string;
			  iso639_2:   string;
			  name:       string;
			  nativeName: string;
			}

			export interface RegionalBloc {
			  acronym:       string;
			  name:          string;
			  otherAcronyms: string[];
			  otherNames:    string[];
			}

			export interface Translations {
			  de: string;
			  es: string;
			  fr: string;
			  ja: string;
			  it: string;
			  br: string;
			  pt: string;
			  nl: string;
			  hr: string;
			  fa: string;
			}
	2.- Se modifica la clase de servicio, se modifica metodo buscarPais(), se le coloca tipado al observable
		  buscarPais( termino: string):Observable<Country[]>{
		    return this.http.get<Country[]>(`${this.urlEndPoint}/name/${termino}`);
		  }
	Nota ya se puede manejar en el componente donde se subscribe al observable por tipado
*************************************************************************************************************************************
106.- ****************************************************************************************LLenar tabla con los datos del servicio
	1.- Se modifica por-pais.component.ts
		paises: Country[] = [];

		buscar(){
	    this.hayError = false;
	    this.paisesService.buscarPais( this.termino )
	      .subscribe(
	          paises => {
	            console.log(paises);
	            this.paises = paises;
	          }, error => {
	            this.paises = [];
	            this.hayError = true;
	          }
	      );	
	2.- Se modifica por-paises.component.html, se recorre la lista 
		        <tr *ngFor="let pais of paises">
                    <td>{{pais.numericCode}}</td>
                    <td><img [src]="pais.flag" alt="" class="small-flag"></td>
                    <td>{{pais.name}}</td>
                    <td>{{pais.population | number}}</td>
                    <td>
                        <a [routerLink]="['./pais', pais.alpha2Code]">Ver...</a>
                    </td>
                </tr>
    3.- Se modifca paises.module.ts, se importa RouterModule para poder utilizar el routerLink
    	imports: [
		    CommonModule,
		    FormsModule,
		    RouterModule
		  ]
*************************************************************************************************************************************
107.- *******************************************************************************************************Componente Input y Tabla
	1.- Se genera componente para la table
		ng g c paises/components/paisTabla --skipTests
	2.- Se modifica por-pais.component.html, se corta la table y se agrega el componente creado
		<app-pais-tabla [paises]="paises"></app-pais-tabla>
	3.- Se modifica pais-tabla.component.html, se agrega la tabla cortada en el paso anterior
		<table class="table table-hover" *ngIf="paises.length > 0">
		    <thead>
		        <tr>
		            <th>#</th>
		            <th>Bandera</th>
		            <th>Nombre</th>
		            <th>Poblacion</th>
		            <th>prueba</th>
		        </tr>
		    </thead>
		    <tbody>
		        <tr *ngFor="let pais of paises">
		            <td>{{pais.numericCode}}</td>
		            <td><img [src]="pais.flag" alt="" class="small-flag"></td>
		            <td>{{pais.name}}</td>
		            <td>{{pais.population | number}}</td>
		            <td>
		                <a [routerLink]="['./pais', pais.alpha2Code]">Ver...</a>
		            </td>
		        </tr>
		    </tbody>
		</table>
	4.- Se modifica pais-tabla.component.ts, se crea atributo @Input para pasar atributo paises
		@Input() paises: Country[] = []
	5.- Se modifica pais-table.component.css
		.small-flag {
		    width: 50px;
		}
	6.- Se crea componente --> ng g c paises/components/paisInput --skipTests
	7.- Se modifica por-pais.component.html, se corta el form del input y se agrega componente
		<div class="row">
		    <div class="col">
		        <app-pais-input></app-pais-input>
		    </div>
		</div>
	8.- Se modifica pais-input.component.html, se pega el form del input
		<form action="" autocomplete="off" (ngSubmit)="buscar()">
		    <input type="text" class="form-control" name="termino" placeholder="Buscar Pais..." [(ngModel)]=termino>
		</form>
	9.- Se modifca pais-input.component.ts, se agrega atributo termino y se agrega metodo buscar()
		termino: string = '';

		buscar(){

		}
*************************************************************************************************************************************
108.- ******************************************************************************Funcionalidades del componente PaisInputComponent
	1.- Se modifca pais-input.component.ts, se agrega emision del evento
		1.1.- Se crea atributo con el decorador Output
		 	@Output() onEnter: EventEmitter<string> = new EventEmitter()
		1.2.- Se modifica el metodo buscar()
			  buscar(){
			    this.onEnter.emit( this.termino );
			  }
	2.- Se modofica por-pais.component.html, se agrega la escucha al evento y se le pasa al metodo buscar el evento
		<div class="row">
		    <div class="col">
		        <app-pais-input (onEnter)="buscar( $event )"></app-pais-input>
		    </div>
		</div>
	3.- Se modifca modifica el metodo buscar() para que reciba el termino del evento
		buscar( termino:string ){
		    this.hayError = false;
		    this.termino = termino;
		    this.paisesService.buscarPais( this.termino )
		      .subscribe(
		          paises => {
		            console.log(paises);
		            this.paises = paises;
		          }, error => {
		            this.paises = [];
		            this.hayError = true;
		          }
		      );
		  }
*************************************************************************************************************************************
109.- *****************************************************************************************************DebounceTime - en el input
	1.- Se modifica pais-input.component.ts 
		1.1.- Se agrega atributo @OutPuts
			@Output() onDebounce : EventEmitter<string> = new EventEmitter();
		1.2.- Se crea atributo de tipo subject
			debounce: Subject<string> = new Subject();
		1.3.- Se modifica el metod ngOnInit()
			  ngOnInit(): void {
			    this.debounce
			    .pipe(debounceTime(300))
			    .subscribe( valor => {
			      this.onDebounce.emit( valor )
			    })
			  }
		1.4.- Se crea metodo teclaPresionada()
			  teclaPresionada(){
			    this.debounce.next( this.termino );
			  }
	2.- Se modifica pais-input.component.html, se añade atributo de tipo input
		<form action="" autocomplete="off" (ngSubmit)="buscar()">
		    <input type="text"
		    class="form-control"
		    name="termino"
		    placeholder="Buscar Pais..."
		    [(ngModel)]=termino
		    (input)="teclaPresionada()">
		</form>
*************************************************************************************************************************************
110.- **********************************************************************************************Creacion d componente por-capital
	1.- Se modifica por-capital.component.html, se crea renderizacion 
		<h2>Por Capital</h2>
		<hr>

		<div class="row">
		    <div class="col">
		        <app-pais-input (onEnter)="buscar( $event )" (onDebounce)="sugerencias( $event )" placeHolder="Buscar Capital..."> </app-pais-input>
		    </div>
		</div>
		<hr>

		<div class="alert alert-danger" *ngIf="hayError">
		    No se encontro el termino {{termino}}
		</div>

		<div class="row">
		    <div class="col">
		        <app-pais-tabla [paises]="paises"></app-pais-tabla>
		    </div>
		</div>
	2.- Se modifica por-capital.component.ts
		1.- Se crean atributos
			  termino: string = "";
			  hayError: boolean = false;
			  paises: Country[] = [];
		2.- Se realiza injeccion de dependencia del servicios
			constructor( private paisesService: PaisService ) { }
		3.- Se crean metodos para invocar el servicio
			  buscar( termino:string ){
			    this.hayError = false;
			    this.termino = termino;
			    this.paisesService.buscarCapital( this.termino )
			      .subscribe(
			          paises => {
			            console.log(paises);
			            this.paises = paises;
			          }, error => {
			            this.paises = [];
			            this.hayError = true;
			          }
			      );
			  }

		    sugerencias( termino: string ){
			    this.hayError = false;
			  }
	3.- Se modifica pais.service.ts, se crea metodo 
		  buscarCapital( termino: string ):Observable<Country[]>{
		    return this.http.get<Country[]>(`${this.urlEndPoint}/capital/${termino}`, {params: this.httpParams});
		  }
	4.- Se modifica pais-imput.component.ts, se agrega @input place placeHolder
			@Input() placeHolder: string = '';
	5.- Se modifica por-pais.component.html, se le agrega placeholder al imput
		<app-pais-input (onEnter)="buscar( $event )" (onDebounce)="sugerencias( $event )" placeHolder="Buscar Pais..."> </app-pais-input>
*************************************************************************************************************************************
111.- ************************************************************************************************Ver pais de forma independiente
******************************************************************************************************************operators switchMap
***********************************************************************************************************************ActivatedRoute
************************************************************************************************************************Operators Tap
	1.- Se modifica ver-pais.component.ts
		1.1.- Se realiza injeccion de dependencia del ActivatedRoute y la clase de servicio para capturar cambios en la url
			    constructor( private activatedRoute: ActivatedRoute,
                 private paisService: PaisService) { }
			1.1.1.- Se crea atributo pais
				pais!: Country;
		1.2.- Se modifica metodo ngoninit(), recupera el parametro con el activatedRoute y llama al servicio para recuperar 
		pais por codigo
			  ngOnInit(){
			    this.activatedRoute.params.pipe(
			      switchMap( ({id}) => {
			        console.log(id);
			        return this.paisService.buscarPaisXCod( id )
			      }),
			      tap( resp => console.log(resp))
			    ).subscribe( pais => this.pais = pais);
			  }
	2.- Se modifica pais.service.ts, se creaa metodo para recuperar paises por codigo
		  buscarPaisXCod( id: string ): Observable<Country>{
		    console.log(id);
		    return this.http.get<Country>(`${this.urlEndPoint}/alpha/${id}`);
		  }
*************************************************************************************************************************************
113.- ***************************************************************************************************Renderizar pantalla ver-pais
***************************************************************************************************************************ngTemplate
	1.- Se modifica ver-pais.component.html
		<div *ngIf="!pais; else divPais">
		    <div class="alert-alert-info">
		        Espere Por favor...
		    </div>
		</div>

		<ng-template #divPais>
		    <div class="row">
		        <div class="col-12">
		            <h1>Pais: <small>{{pais.name}}</small></h1>
		        </div>
		    </div>
		    <div class="row">
		        <div class="col-4">
		            <h3>Bandera</h3>
		            <img [src]="pais.flag" alt="" class="img-thumbnail">
		        </div>
		        <div class="col">
		            <h3>Informacion</h3>
		            <ul class="list-group">
		                <li class="list-group-item">
		                    <strong>Poblacion: </strong>{{pais.population}}
		                </li>
		                <li class="list-group-item">
		                    <strong>Codigo numerico: </strong>{{pais.numericCode}}
		                </li>
		                <li class="list-group-item">
		                    <strong>Codigo Alpha3: </strong>{{pais.alpha3Code}}
		                </li>
		            </ul>
		        </div>
		    </div>
		    <h3>Traducciones</h3>
		    <div class="row">
		        <div class="col">
		            <span class="badge bg-primary mr-5">{{pais.translations.de}}</span>
		            <span class="badge bg-primary mr-5">{{pais.translations.es}}</span>
		            <span class="badge bg-primary mr-5">{{pais.translations.fr}}</span>
		            <span class="badge bg-primary mr-5">{{pais.translations.ja}}</span>
		            <span class="badge bg-primary mr-5">{{pais.translations.it}}</span>
		            <span class="badge bg-primary mr-5">{{pais.translations.br}}</span>
		            <span class="badge bg-primary mr-5">{{pais.translations.br}}</span>
		            <span class="badge bg-primary mr-5">{{pais.translations.pt}}</span>
		            <span class="badge bg-primary mr-5">{{pais.translations.nl}}</span>
		            <span class="badge bg-primary mr-5">{{pais.translations.hr}}</span>
		            <span class="badge bg-primary mr-5">{{pais.translations.fa}}</span>
		        </div>
		    </div>
		    <pre class="mt-5">
		      {{pais | json}}
		    </pre>
		</ng-template>
*************************************************************************************************************************************
114.- ************************************************************************************************************************ngClass
********************************************************************************************************************************class
	1.- Se modifica por-region.cmponent.ts
		1.1.- se crean atributos regiones
			regiones: string[] = ['africa', 'americas', 'asia', 'aurope', 'oceania'];
			regionSeleccionada: string = '';
			paises: Country[]=[];
		1.2.- Se crea metodo activarRegion()
			activarRegion( region: string){
			    this.regionSeleccionada =  region;

			    this.paisService.buscarPaisXRegion(region).subscribe(
			      paises => { this.paises = paises }
			    );
			  }
		1.3.- Se realiza la injeccion de dependencia del servicio
			constructor( private paisService: PaisService ) { }
		1.4.- Se crea metodo para cambiar clase con [ngClass] css segun validacion
			  activarCSS( region: string ): string{
			    return ( region === this.regionSeleccionada ) ? 'btn-primary' : 'btn-outline-primary';
			  }
	2.- Se modifica por-region.component.html
		<h3>Buscar por Region: <small>{{regionSeleccionada | titlecase}}</small></h3>
		<hr>
		<h5>Seleccionar Region:</h5>
		<div class="row">
		    <div class="col">
		        <!-- Se maneja enl class -->
		        <!-- [class.btn-primary]="region === regionSeleccionada" -->
		        <!-- manejo de la directiva ngClass -->
		        <!-- [ngClass]="{'btn-primary': region === regionSeleccionada,'btn-outline-primary': region !== regionSeleccionada}" -->
		        <!-- Se maneja con directiva ngClass -->
		        <!-- Se maneja con directiva ngClass -->
		        <button class="btn btn-outline-primary" 
		        *ngFor="let region of regiones" 
		        (click)="activarRegion(region)" 
		        [ngClass]="activarCSS(region)">
		          {{region}}
		    </button>
		    </div>
		</div>

		<div class="row">
		    <div class="col">
		        <app-pais-tabla [paises]="paises"></app-pais-tabla>
		    </div>
		</div>
	3.- Se modifica clase de servicio pais.service.ts, se crea metodo para obtener los paises por region
		  buscarPaisXRegion( region: string ): Observable<Country[]>{
		    return this.http.get<Country[]>(`${this.urlEndPoint}/region/${region}`, {params: this.httpParams});
		  } 
*************************************************************************************************************************************
122.- ************************************************************************************Optimizar peticion Http enviando parametros
*************************************************************************************************************************HttpParams()
	1.- Se modifica pais.service.ts
		1.1.- Se crea getters para los parametros
			  //Se crea getter para los parametros
			  get httpParams(){
			    //HttpParams, permite configurar los parametros
			    return new HttpParams().set('fields','name;capital;alpha2Code;flag;population')
			  }
		1.2.- Se modifica metodos para pasarle los paramtros que se necesitan
			  buscarPais( termino: string):Observable<Country[]>{
			    return this.http.get<Country[]>(`${this.urlEndPoint}/name/${termino}`, {params: this.httpParams});
			  }

			    buscarCapital( termino: string ):Observable<Country[]>{
			    return this.http.get<Country[]>(`${this.urlEndPoint}/capital/${termino}`, {params: this.httpParams});
			  }

			    buscarPaisXRegion( region: string ): Observable<Country[]>{
			    return this.http.get<Country[]>(`${this.urlEndPoint}/region/${region}`, {params: this.httpParams});
			  }
*************************************************************************************************************************************
124.- ********************************************************************************************Mostrar sugerencias en autocomplete
	1.- Se modifica por-pais.component.html, se agrega lista para las sugerencias despues del input

		<ul class="list-group" *ngIf="mostrarSugerencias">
		    <li *ngFor="let pais of paisesSugeridos" class="list-group-item list-group-item-action">
		        <a class="nav-link" [routerLink]="['/pais', pais.alpha2Code]">{{pais.name}}</a>
		    </li>
		    <li class="list-group-item list-group-item-action" (click)="buscar( termino )">
		        <a class="nav-link">Buscar {{termino}}</a>
		    </li>
		</ul>
	2.- Se modifica por-pais.component.ts, se le agrega en el decorador clase css al elemento li
		@Component({
		  selector: 'app-por-pais',
		  templateUrl: './por-pais.component.html',
		  styles: [' li { cursor: pointer }']
		})
		2.1.- Se crean atributos
			paisesSugeridos: Country[] = [];
			mostrarSugerencias: false;
		2.2.- Se modifica metodo sugerencias(), para que llame al servicio
			  sugerencias( termino: string ){
			    this.hayError = false;
			    this.termino = termino;
			    this.mostrarSugerencias = true;

			    this.paisesService.buscarPais( termino ).subscribe(
			      paises => this.paisesSugeridos = paises.slice(0,3),
			      (err) => this.paisesSugeridos = []
			    );
			  }
*************************************************************************************************************************************
Seccion 10: Pipes en Angular*********************************************************************************************************
	Esta sección es de mis favoritas del curso, veremos temas sobre Pipes y transformación visual de la data, pero también decidí 
	mezclarlo con otro tema muy solicitado que es PrimeNG (Más adelante hay también una sección con muchos componentes de 
	Angular Material), pero esta sección pretende enseñarles muchas cosas interesantes, aparte de los Pipes de Angular

	Este es un breve listado de los temas fundamentales:

	Todos los Pipes de Angular a la fecha

		Uppercase

		Lowercase

		TitleCase

		Date

		Decimal

		Currency

		Percent

		i18nSelect

		i18Plural

		KeyValue

		Json

		Async

	PrimeNG

	Módulo especializado para módulos de PrimeNG

		PrimeNG es un paquete de componentes estilizados que contienen funcionalidades basadas en Angular para crear 
		aplicaciones elegantes, hermosas y funcionales rápidamente.

		Recuerden que PrimeNG no es el único, hay muchos pero la mayoría funcionan de formas similares, importando 
		módulos, por eso mi objetivo de entrenarlos bastante bien con el manejo de módulos en todo momento para que 
		nos familiaricemos con esta métodología.
	Referencias:
		Angular pipes -->  https://angular.io/api?query=pipe
		PrimeNg  --> https://www.primefaces.org/primeng/
	1.- Inicio de proyecto pipesApp
		ng new pipesApp
131.- ************************************************************************************************************Instalacion PrimeNg+
	Referencia --> https://primefaces.org/primeng/showcase/#/calendar
	1.- Instalacion primeNg y primeIcons
		npm install primeng primeicons
		Nota: revizar que se hayan creado en el archivo package.json en las dependencias
	2.- Se modifica el archivo angular.json, para agregarle estilos de primeNg
		    "styles": [
              "src/styles.css",
              "node_modules/primeicons/primeicons.css", 
              "node_modules/primeng/resources/themes/vela-blue/theme.css",              
              "node_modules/primeng/resources/primeng.min.css"
            ],
    3.- Se baja la aplicacion y se vuelve a subir, no deben generarse errores
*************************************************************************************************************************************
132.- ****************************************************************************************************PrimeButton y estilo global+
	1.- Se modifica app.module.ts, se importa el  ButtonModule
		//PrimeNg
		import {ButtonModule} from 'primeng/button';

		1.1.- Se agrega el modulo en los imports del decorador
			imports: [
			    BrowserModule,
			    AppRoutingModule,
			    ButtonModule
			  ],
	2.- Se modifica style.css, para crear estilos de manera global
		1.- se accede al theme.css del estilo vela-blue node_modules --> primeng --> resources --> themes --> vela-blue --> theme.css
		2.- se copian los estilos y se modifica style.css 
			html, body{
			    margin: 10px;
			    background-color: var(--surface-b);
			    font-family: var(--font-family);
			}

			.text-loyaut{
			    color: var(--text-color);
			}
*************************************************************************************************************************************
133.- *****************************************************************************************************Cards y botones con iconos+
	1.- Se  modifica app.component.ts, se decea poner icono a la derecha del boton y de color lila (tarea)
		<button pButton type="button" label="Click" icon="pi pi-bell" iconPos="right" class="p-button-help"></button> 
	2.- Crear una tarjeta (tarea) apoyandose en la documentacion de primeng
		1.- Se accede a la documentacion panel --> card
		2.- Se modifica app.mdule.ts
			import {CardModule} from 'primeng/card';

			  imports: [
			    BrowserModule,
			    AppRoutingModule,
			    ButtonModule,
			    CardModule
			  ],
		3.- Se modifica app.component.html, se agrega la tarjeta
			<p-card [header]="nombre | titlecase" [style]="{'width': '25rem', 'margin-bottom': '2em'}" subheader="Esto es un subheader">
			  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore sed consequuntur error repudiandae numquam deserunt
			      quisquam repellat libero asperiores earum nam nobis, culpa ratione quam perferendis esse, cupiditate neque quas!</p>
			</p-card>
		4.- Se modifica app.component.ts, se crea variable nombre
			nombre = 'elvis areiza';
*************************************************************************************************************************************
134.- ******************************************************************************************************************PrimeNgModule++
	1.- Se crea modulo con ng-cli
		ng g m primeNg
	2.- Se modifica prime-ng.module.ts, se corta los imports de app.module.ts y se pegan 
		//PrimeNg
		import {ButtonModule} from 'primeng/button';
		import {CardModule} from 'primeng/card';
		2.1.- Se crea los exports para los modulos
			@NgModule({
			  exports: [
			    ButtonModule,
			    CardModule
			  ]
			})
			export class PrimeNgModule { }
	3.- Se modifica app.module.ts, se importa el modulo personalizados
		//Modulo perzonalizado
		import { PrimeNgModule } from './prime-ng/prime-ng.module';

		  imports: [
		    BrowserModule,
		    AppRoutingModule,
		    PrimeNgModule
		  ],
*************************************************************************************************************************************
135.- **************************************************************************************************************PrimeNg - MenuBar
	1.- Se crea estructura de folders
		app
			shared
			ventas
				interfaces
				pages
				pipes
	2.- Se crean modulos shared y ventas
		ng g m shared
		ng g m ventas
	3.- Se crea componente shared/menu

		ng g c shared/menu
	4.- Se modifica share.module.ts, se exporta el componente menu para que pueda ser utilizado fuera de shared y se importa PrimeNgModule
		@NgModule({
		  declarations: [MenuComponent],
		  exports:[ MenuComponent],
		  imports: [
		    CommonModule,
		    PrimeNgModule
		  ]
		})
		export class SharedModule { }

	4.-  Se modifica prime-ng.module.ts, se exporta MenuBarModule
		import {MenubarModule} from 'primeng/menubar';

		@NgModule({
		  exports: [
		    ButtonModule,
		    CardModule,
		    MenubarModule
		  ]
		})
		export class PrimeNgModule { }
	5.- Se modifica app.module.ts, se importa el SharedModule y se elimina PrimeNgModule ya que es importado en el SharedModule
		import { SharedModule } from 'primeng/api';

		@NgModule({
		  declarations: [
		    AppComponent
		  ],
		  imports: [
		    BrowserModule,
		    AppRoutingModule,
		    PrimeNgModule,
		    SharedModule
		  ],
		  providers: [],
		  bootstrap: [AppComponent]
		})
		export class AppModule { }
	6.- Se modifica menu.component.ts
		6.1.- Se crea atributo items de tipo MenuItem que es una interface de PrimeNg
			import {MenuItem} from 'primeng/api';

			 items: MenuItem[] = [];
		6.2.- Se modifica ngoninit()
			ngOnInit() {
			    this.items = [
			      {
			          label: 'File',
			          items: [{
			                  label: 'New', 
			                  icon: 'pi pi-fw pi-plus',
			                  items: [
			                      {label: 'Project'},
			                      {label: 'Other'},
			                  ]
			              },
			              {label: 'Open'},
			              {label: 'Quit'}
			          ]
			      },
			      {
			          label: 'Edit',
			          icon: 'pi pi-fw pi-pencil',
			          items: [
			              {label: 'Delete', icon: 'pi pi-fw pi-trash'},
			              {label: 'Refresh', icon: 'pi pi-fw pi-refresh'}
			          ]
			      }
			    ];
			  }
*************************************************************************************************************************************
136.- *********************************************************************************************************Rutas de la aplicacion++
	//-- flat es para que no cree una carpeta
	1.- Se crea modulo para las rutas
		ng g m appRouter --flat
	2.- Se crean componentes
		ng g c ventas/pages/numeros --skipTests -is
		ng g c ventas/pages/noComunes --skipTests -is
		ng g c ventas/pages/basicos --skipTests -is
		ng g c ventas/pages/ordenar --skipTests -is
	3.- Se modifica app-router.module.ts, se crea la constante para las rutas y se importa el RouterModule.forRoot
		const ROUTES: Routes =[
		  {
		    path: '',
		    component: BasicosComponent,
		    pathMatch: 'full'
		  },
		  {
		    path: 'numeros',
		    component: NumerosComponent
		  },
		  {
		    path: 'no-comunes',
		    component: NoComunesComponent
		  },
		  {
		    path: 'ordenar',
		    component: OrdenarComponent
		  },
		  //comodin
		  {
		    path: '**',
		    redirectTo: ''
		  }
		]
		
		@NgModule({
			  declarations: [],
			  imports: [
			  	RouterModule,
			    RouterModule.forRoot( ROUTES )
			  ],
			  exports:[ RouterModule ]
			})
			export class AppRouterModule { }
	4.- Se modifica ventas.module.ts, se exportan los modulos para que puedan ser utilizados por otro modulo
		@NgModule({
			  declarations: [NumerosComponent, NoComunesComponent, BasicosComponent, OrdenarComponent],
			  exports: [NumerosComponent, NoComunesComponent, BasicosComponent, OrdenarComponent],
			  imports: [
			    CommonModule
			  ]
			})
			export class VentasModule { }
	5.- Se modifica app.module.ts, se importa el mdulo de rutas
		 imports: [
		    BrowserModule,
		    AppRoutingModule,
		    AppRouterModule,
		    SharedModule
		  ],
			
	6.- Se modifica pp.component.html, se incluye el router-outlet
		<router-outlet></router-outlet>
*************************************************************************************************************************************
137.- ****************************************************************************************Cambiar las rutas utilizando el menuBar++
	1.- Se modifica menu.component.ts, se modific metodo ngoninit() para crear rutas Personalizadas con el RouterLink
		 ngOnInit() {
		    this.items = [
		      {
		        label: 'Pipes de Angular',
		        icon: 'pi pi-desktop',
		        items: [
		          {
		            label: 'Textos y fechas',
		            icon: 'pi pi-align-left',
		            routerLink: '/'
		          },
		          {
		            label: 'Numeros',
		            icon: 'pi pi-dollar',
		            routerLink: 'numeros'
		          },
		          {
		            label: 'No Comunes',
		            icon: 'pi pi-globe',
		            routerLink: 'no-comunes'
		          }
		        ]
		      },
		      {
		        label: 'Pipes Personalizados',
		        icon: 'pi pi-cog'
		      }
		    ];
		  }
	2.- Se modifica menu.component.html, se agrega icono al menuBar
		<p-menubar [model]="items">
		    <img src="./favicon.ico" >
		</p-menubar>
*************************************************************************************************************************************
138.- **********************************************************************************************************************PrimeFlex+
	PrimeFlex es una biblioteca de utilidades CSS que presenta varios ayudantes, como un sistema de cuadrícula, caja flexible, 
	espaciado, elevación y más. Aunque no es necesario, se recomienda encarecidamente agregar PrimeFlex, ya que es probable que 
	necesite dichas utilidades al desarrollar aplicaciones con PrimeNG.

	1.- Se instala Prime flex
		npm install primeflex --save
		1.1.- Se valida en packege.json si se instalo de manera correcta en las dependencias
	2.- Se modifica angular.jsonSe agrega primeflex.css a la aplicación para incluir todas las utilidades. Si prefiere elegir las utilidades, pase al 
	siguiente paso.

		"styles": [
              "src/styles.css",
              "./node_modules/primeflex/primeflex.css",
              "node_modules/primeicons/primeicons.css", 
              "node_modules/primeng/resources/themes/vela-blue/theme.css",              
              "node_modules/primeng/resources/primeng.min.css"
            ],
    3.- Se baja y se sube el aplicativo
   	4.- Se modifica basicos.component.html, se renderiza la vista
   		<div class="text-loyaut">
		    <h1>Pipes Basicos</h1>
		    <p>Pipes incluidos en el CommonModule</p>
		</div>
		<div class="p-grid">
		    <div class="p-col-4">
		        <p-card header="UpperCase"></p-card>
		    </div>
		    <div class="p-col-4"></div>
		    <div class="p-col-4"></div>
		</div>
	5.- Se modifica ventas.module.ts, se importa PrimeNgModule
		  imports: [
		    CommonModule,
		    PrimeNgModule
		  ]
	6.- Se modifica el app.module.ts, se importa VentasModule
		  imports: [
		    BrowserModule,
		    AppRouterModule,
		    SharedModule,
		    VentasModule
		  ],
*************************************************************************************************************************************
139.- ***********************************************************************************************UpperCase, LowerCase y TitleCase
	1.- Se modifica basicos.component.ts, se crea atributos de la clase
		  lowerCase:  string = 'elvis';
		  upperCase: string = 'ELVIS';
		  nombreCompleto: string = 'elvIs arEiZa';
	2.- Se modifica basicos.component.html, se crea <div class="text-loyaut">
	    <h1>Pipes Basicos</h1>
	    <p>Pipes incluidos en el CommonModule</p>
		</div>
		<div class="p-grid">
		    <div class="p-col-4">
		        <p-card header="LowerCase" [subheader]="upperCase">
		            {{lowerCase | lowercase}}
		        </p-card>
		    </div>
		    <div class="p-col-4">
		        <p-card header="UpperCase" [subheader]="lowerCase">
		            {{upperCase | uppercase}}
		        </p-card>
		    </div>
		    <div class="p-col-4">
		        <p-card header="TitleCase" [subheader]="nombreCompleto">
		            {{nombreCompleto | titlecase}}
		        </p-card>
		    </div>
		</div>
*************************************************************************************************************************************
140.- **********************************************************************************************************************Date pipe
	Referencia --> https://angular.io/api/common/DatePipe
	1.- Se modifica basicos.component.ts
		1.1.- Se crea atributo
			fecha: Date = new Date();
	2.- Se modicia basicos.component.html
		<div class="text-loyaut">
		    <h1>Pipes Basicos</h1>
		    <p>Pipes incluidos en el CommonModule</p>
		</div>
		<div class="p-grid">
		    <div class="p-col-4">
		        <p-card header="LowerCase" [subheader]="upperCase">
		            {{lowerCase | lowercase}}
		        </p-card>
		    </div>
		    <div class="p-col-4">
		        <p-card header="UpperCase" [subheader]="lowerCase">
		            {{upperCase | uppercase}}
		        </p-card>
		    </div>
		    <div class="p-col-4">
		        <p-card header="TitleCase" [subheader]="nombreCompleto">
		            {{nombreCompleto | titlecase}}
		        </p-card>
		    </div>
		</div>

		<div class="p-grid">
		    <div class="p-col-6">
		        <p-card>
		            <ul>
		                <li>fecha</li>
		                <li>fecha |  date (format	string   Optional. Default is 'mediumDate'.)</li>
		                <li>fecha | date: 'short' ('M/d/yy, h:mm a')</li>
		                <li>fecha | date: 'long' ('MMMM d, y, h:mm:ss a z')</li>
		                <li>fecha | date: 'long' ('dd, MM, YYYY')</li>
		                <li>fecha | date: 'long' ('MMMM dd, YYYY')</li>
		            </ul>
		        </p-card>
		    </div>
		    <div class="p-col-6">
		        <p-card>
		            <ul>
		                <li>{{fecha}}</li>
		                <li>{{fecha | date}}</li>
		                <li>{{fecha | date: 'short'}}</li>
		                <li>{{fecha | date: 'long'}}</li>
		                <li>{{fecha | date: 'dd, MM, YYYY'}}</li>
		                <li>{{fecha | date: 'MMMM dd, YYYY'}}</li>
		            </ul>            
		        </p-card>
		    </div>
		</div>
*************************************************************************************************************************************
141.- **************************************************************************************************Cambiar el idioma por defecto
	1.- Se modifica app.module.ts
		//Cambiar el locale de la App
		import localeEs from '@angular/common/locales/es-AR';
		import { registerLocaleData } from '@angular/common';
		registerLocaleData( localeEs );
		1.1.- Se modifica el providers
			  providers: [
			    { provide: LOCALE_ID, useValue: 'es-AR' }
			  ],
	2.- Probar la aplicacion, debe estar en el lenguaje correcto
	3.- Se modifica basico.component.html
		<div class="p-grid">
		    <div class="p-col-6">
		        <p-card>
		            <ul>
		                <li>fecha</li>
		                <li>fecha |  date (format	string   Optional. Default is 'mediumDate'.)</li>
		                <li>fecha | date: 'short' ('M/d/yy, h:mm a')</li>
		                <li>fecha | date: 'long' ('MMMM d, y, h:mm:ss a z')</li>
		                <li>fecha | date: 'long' ('dd, MM, YYYY')</li>
		                <li>fecha | date: 'long' ('MMMM dd, YYYY')</li>
		            </ul>
		            
		        </p-card>
		    </div>
		    <div class="p-col-6">
		        <p-card>
		            <ul>
		                <li>{{fecha}}</li>
		                <li>{{fecha | date}}</li>
		                <li>{{fecha | date: 'short'}}</li>
		                <li>{{fecha | date: 'long'}}</li>
		                <li>{{fecha | date: 'dd, MM, YYYY'}}</li>
		                <li>{{fecha | date: 'MMMM dd, YYYY'}}</li>
		            </ul>  
		                   
		        </p-card>
		    </div>
		</div>
*************************************************************************************************************************************
142.- *******************************************************************************************************TimeZone y otros idiomas
	1.- Se modifica app.module.ts, se le añade idioma Frances
		//Cambiar el locale de la App
		import localeEs from '@angular/common/locales/es-AR';
		import localeFr from '@angular/common/locales/fr';
		import { registerLocaleData } from '@angular/common';
		registerLocaleData( localeEs );
		registerLocaleData( localeFr );
	2.- Se modifica basico.component.html, se agregan idioma y zona horaria
		<div class="p-grid">
		    <div class="p-col-6">
		        <p-card>
		            <ul>
		                <li>fecha</li>
		                <li>fecha |  date (format	string   Optional. Default is 'mediumDate'.)</li>
		                <li>fecha | date: 'short' ('M/d/yy, h:mm a')</li>
		                <li>fecha | date: 'long' ('MMMM d, y, h:mm:ss a z')</li>
		                <li>fecha | date: 'long' ('dd, MM, YYYY')</li>
		                <li>fecha | date: 'long' ('MMMM dd, YYYY')</li>
		            </ul>
		            <hr>
		            <ul>
		                <li>fecha | date: 'long': 'GMT-6'</li>
		                <li>fecha | date: 'long': 'GMT-5'</li>
		                <li>fecha | date: 'long': 'GMT-5':'en'</li>
		                <li>fecha | date: 'long': 'GMT-5':'fr'</li>
		            </ul>
		        </p-card>
		    </div>
		    <div class="p-col-6">
		        <p-card>
		            <ul>
		                <li>{{fecha}}</li>
		                <li>{{fecha | date}}</li>
		                <li>{{fecha | date: 'short'}}</li>
		                <li>{{fecha | date: 'long'}}</li>
		                <li>{{fecha | date: 'dd, MM, YYYY'}}</li>
		                <li>{{fecha | date: 'MMMM dd, YYYY'}}</li>
		            </ul>  
		            <hr>
		            <ul>
		                
		                <li>{{fecha | date: 'long': 'GMT-6'}}</li>
		                <li>{{fecha | date: 'long': 'GMT-5'}}</li>
		                <li>{{fecha | date: 'long': 'GMT-5':'en'}}</li>
		                <li>{{fecha | date: 'long': 'GMT-5':'fr'}}</li>
		            </ul>          
		        </p-card>
		    </div>
		</div>
*************************************************************************************************************************************
143.- *************************************************************************************************************DecimalPipe number+
	Referencia --> https://angular.io/api?query=pipe
	1.- Se modifica numero.component.ts, se crean atributos
		 ventasNetas: number = 34856.5576;
  		porcentaje:number = 0.48
  	2.- Se modifica numeros.component.html, se renderiza la vista
  		<div class="text-loyauot">
		    <h1>Decimal Pipe y Porcentaje</h1>
		    <p>Pipes incluidos en Angular</p>
		</div>

		<div class="p-grid">
		    <div class="p-col-4">
		        <p-card header="Ventas Netas" subheader="Presente año">
		            <i class="pi pi-dollar">{{ ventasNetas | number: '1.5-5' }}</i>            
		        </p-card>
		    </div>
		</div>
*************************************************************************************************************************************
144.- *****************************************************************************************************CurrencyPipe y PercentPipe+
	1.- Se modifica numeros.component.html, se agrega elementos html para el porcentaje
		    <div class="p-col-4">
		        <p-card header="Porcentaje" subheader="Presente año">
		            {{ porcentaje | percent:'2.2-2' }}      
		        </p-card>
		    </div>
*************************************************************************************************************************************
145.- **************************************************************************************************************PimeNg - FieldSet+++
	Referencia: --> https://primefaces.org/primeng/showcase/#/fieldset
	1.- Se modifica ventas.module.ts, se importa y exporta el modulo de FieldSetModule
		import {FieldsetModule} from 'primeng/fieldset';

		@NgModule({
		  declarations: [NumerosComponent, NoComunesComponent, BasicosComponent, OrdenarComponent],
		  exports: [NumerosComponent, NoComunesComponent, BasicosComponent, OrdenarComponent, FieldsetModule],
		  imports: [
		    CommonModule,
		    FieldsetModule,
		    PrimeNgModule
		  ]
		})
		export class VentasModule { }
	2.- Se modifica app.module.ts, Se importa el modulo de animacion de angular de manera global en la aplicacion

		import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

		  imports: [
		    BrowserModule,
		    AppRouterModule,
		    BrowserAnimationsModule,
		    SharedModule,
		    VentasModule
		  ],
	3.- Se modifica app.component.ts, se configura la propiedad riple de primeng para efecto bubble
		3.1.- Se importa PrimeNgConfig 
			import { PrimeNGConfig } from 'primeng/api';
		3.2.- Se realiza la injeccion de dependencia
			constructor(private primengConfig: PrimeNGConfig) {}
		3.3.- Se  modifica el metodo ngoninit()
			  ngOnInit() {
			    this.primengConfig.ripple = true;
			  }
	5.- Se modifica no-counes.component.html, se renderiza la vista
		<div class="text-loyauot">
		    <h1>Pipes No tan Comunes</h1>
		    <p>Pipes incluidos en Angular</p>
		</div>

		<div class="p-grid">
		    <div class="p-col p-md-6">
		        <p-fieldset legend="i18nSelectPipe">
		            <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ab unde iste quas 
		            et! Eos, minima magnam odit accusantium facilis praesentium voluptas cum omnis 
		            nisi quis aliquid quos quasi libero eligendi.</p>    
		        </p-fieldset>
		    </div>
		    <div class="p-col p-md-6">
		        <p-fieldset [toggleable]="true" legend="i18nPluralPipe">
		            <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ab unde iste quas 
		            et! Eos, minima magnam odit accusantium facilis praesentium voluptas cum omnis 
*************************************************************************************************************************************
146.- *****************************************************************************************************************i18nSelectPipe
	Referencia --> https://angular.io/api/common/I18nSelectPipe
	1.- Se modifica no-comunes.component.ts, se crean atributos
		  nombre: string = 'Elvis';
		  genero: string = 'masculino';

		  invitacionMapa = {
		    'masculino' : 'invitarlo',
		    'femenino' : 'invitarla'
		  }
	2.- Se modifica no-comunes.html, se utiliza pipe
		<div class="p-grid">
		    <div class="p-col p-md-6">
		        <p-fieldset legend="i18nSelectPipe">
		            Saludos {{ nombre }}, es un placer {{ genero | i18nSelect: invitacionMapa }} a esta fiesta
		            </p-fieldset>
		    </div>
		    <div class="p-col p-md-6">
		        <p-fieldset [toggleable]="true" legend="i18nPluralPipe" >
		            </p-fieldset>
		    </div>
		</div>
*************************************************************************************************************************************
147.- *****************************************************************************************************************i18nPluralPipe+
 	Referencia  --> https://angular.io/api/common/I18nPluralPipe
 	1.- Se modifica no-comunes.component.html
 		<div class="p-col p-md-6">
	        <p-fieldset [toggleable]="true" legend="i18nPluralPipe" >
	            Actualmente {{ pluralMap.length | i18nPlural:clienteMapa}}
	            </p-fieldset>
	    </div>
	2.- Se modifica no-comunes.component.ts, se crean atributos y mapa para el pipe
		  //i18nPluralPipe
		  pluralMap: string[]=['elvis'];
		  clienteMapa = {
		    '=0' : 'no hay clientes esperando',
		    '=1' : 'hay un cliente esperando',
		    '=2' : 'hay dos clientes esperando',
		    'other': 'hay # clientes esperando' 
		  }	
*************************************************************************************************************************************
148.- ****************************************************************************************************i18nPipes y primeNg (tarea)
	1.- Se debe crear un boton que le cambie el genero y nombre, se debe crear otro boton que elimine un registro del arreglo 
	clienteMapa
		1.1.- Se modifica no-comunes.component.html
			<div class="p-grid">
	    		<div class="p-col p-md-6">
	        		<p-fieldset legend="i18nSelectPipe">
	           		 <p>Saludos {{ nombre }}, es un placer {{ genero | i18nSelect: invitacionMapa }} a esta fiesta</p>
	            		<button pButton icon type="button" label="Cambio Genero" class="p-button-danger" (click)="cambioGenero()"></button>
	        		</p-fieldset>
	    		</div>
		    	<div class="p-col p-md-6">
		        	<p-fieldset [toggleable]="true" legend="i18nPluralPipe" >
		            	<p>Actualmente {{ pluralMap.length | i18nPlural:clienteMapa}}</p>
		            	<button pButton icon="pi pi-check" iconPos="right" type="button" label="Reducir" class="p-button-text" (click)="reducir()" badge="8"></button>
		        	</p-fieldset>
		    	</div>
			</div>
		1.2.- Se modifica no-comunes.component.ts
			  cambioGenero(): void {
			    this.nombre = this.nombre === 'Elvis' ? 'Yala' : 'Elvis';
			    this.genero = this.genero === 'masculino' ? 'femenino': 'masculino';
			  }

			  reducir(): void {
			    this.pluralMap= this.pluralMap.slice(0,this.pluralMap.length-1)
			  }
*************************************************************************************************************************************
149.- **********************************************************************************************************************SlicePipe
	Referencia --> https://angular.io/api/common/SlicePipe
	1.- Se modifica no-comunes.component.html
		<div class="p-grid">
		    <div class="p-col p-md-6">
		        <p-fieldset [toggleable]="true" legend="SlicePipe">
		           <b>Original</b>
		           <pre>{{pluralMap}}</pre>
		           <b>Slice 0:2</b>
		           <pre>{{ pluralMap | slice:0:2 }}</pre>
		           <b>Slice 1:3</b>
		           <pre>{{ pluralMap | slice:1:3 }}</pre>
		           <b>Slice 1:3(string Elvis)</b>
		           <pre>{{ 'Elvis' | slice:1:3 }}</pre>
		        </p-fieldset>
		    </div>
		</div>
*************************************************************************************************************************************
150.- *******************************************************************************************************************KeyValuePipe+
	Referencia  --> https://angular.io/api/common/KeyValuePipe
	Transforma Objeto o Mapa en una matriz de pares clave-valor.

	1.- Se modifica no-comunes.component.html
		<div class="p-col p-md-6">
	        <p-fieldset [toggleable]="true" legend="KeyValuePipe">
	           <ul>
	               <li *ngFor="let item of persona | keyvalue">
	                   {{item.key}}: {{item.value}}
	               </li>
	           </ul>
	        </p-fieldset>
	    </div>
*************************************************************************************************************************************
151.- **********************************************************************************************************************JasonPipe+
	Referencia --> https://angular.io/api/common/JsonPipe
		Convierte un valor en su representación en formato JSON. Útil para depurar.

	1.- Se modifica no-comunes.component.html
		    <div class="p-col p-md-6">
		        <p-fieldset [toggleable]="true" legend="Json Pipe">
		           <pre>
		               {{ heroes | json}}
		           </pre>
		        </p-fieldset>
		    </div>
*************************************************************************************************************************************
152.- **********************************************************************************************************************AsyncPipe+
	1.- Se modifica no-comunes.component.ts, se crea observable
		import { interval } from 'rxjs';

		//Aysnc Pipe
  		miObservable = interval(1000);
  		  valorPromesa = new Promise(( resolve, reject) => {
		      setTimeout(()=> {
		          resolve('Tenemos data de promesa')
		      },3500);
		  });
  	2.- Se modifica no-comunes.component.html
  		<div class="p-col p-md-6">
	        <p-fieldset [toggleable]="true" legend="Json Pipe">
	            <pre>{{ miObservable | async}}</pre>
	            <pre *ngIf="!(valorPromesa | async)">Valor de la promesa</pre>
	            <pre>{{ valorPromesa | async}}</pre>
	        </p-fieldset>
	    </div>
*************************************************************************************************************************************
Seccion 11: Pipes Personalizados*****************************************************************************************************
157.- *********************************************************************************************Pipe Perzonalizado - MayusculaPipe+
	1.- Se modifica menu.component.ts, se agrega routerlink a opcion de menu
		{
	        label: 'Pipes Personalizados',
	        icon: 'pi pi-cog',
	        routerLink: 'ordenar'
	      }
	2.- Dentro del folder pipes, se crea clase MayusculaPipe, se le agrega decorador Pipe, se implementa PipeTransform y se 
	sobreescribe metodo transform
		import { Pipe, PipeTransform } from "@angular/core";

		@Pipe({
		    name:'mayuscula'
		})
		export class MayusculasPipe implements PipeTransform{
		    
		    transform(nosotros:string): string {
		        return 'Hola Mundo';
		    }
		}
	3.- Se modifica ordenar.component.html
		<div class="text-loyaut">
		    <h1>Pipes Personalizados</h1>
		    <p>Pipes Creados de forma {{ 'nosotros' | mayuscula }}</p>
		</div>
	4.- Se modifica ventas.module.ts, se añade la declaracion del pipe creado
		@NgModule({
		  declarations: [NumerosComponent, NoComunesComponent, BasicosComponent, OrdenarComponent, MayusculasPipe],
		  exports: [NumerosComponent, NoComunesComponent, BasicosComponent, OrdenarComponent, FieldsetModule, ButtonModule],
		  imports: [
		    CommonModule,
		    FieldsetModule,
		    PrimeNgModule,
		    ButtonModule
		  ]
		})
		export class VentasModule { 
		}
*************************************************************************************************************************************
158.- ***********************************************************************************Valor y argumento de los pipe perzonalizados+
	1.- Se modifica mayusculas.pipe.ts, se modifica metodo transfomr()
		transform( valor: string, enMayuscula: boolean = true): string {
	        if(enMayuscula){
	            return valor.toUpperCase();
	        }
	        return valor.toLocaleLowerCase();        
	    }
	2.- Se modifica ordenar.component.html, se renderiza pipe y boton segun balidacion de Bandera
		<div class="text-loyaut">
		    <h1>Pipes Personalizados</h1>
		    <p>Pipes Creados de forma {{ 'personalizada' | mayuscula: bandera }}</p>
		    <p-button label="Minuscula" (onClick)="mayuscula()" *ngIf="bandera else elseBlock"></p-button>
		    <ng-template #elseBlock>
		        <p-button label="Mayuscula" (onClick)="mayuscula()"></p-button>
		    </ng-template>
		</div>
	3.- Se modifica ordenar.component.ts, se crea atributo bandera y se crea metodo mayuscula()
		export class OrdenarComponent implements OnInit {
		  bandera: boolean= false;

		  constructor() { }

		  ngOnInit(): void {
		  }

		  mayuscula(){
		    this.bandera = !this.bandera;
		  }

		}
*********************************************************************************************************************************ngIf
*************************************************************************************************************************************
159.- ******************************************************************************************************PrimeTable y PrimeToolbar+
	Referencia   --> https://primefaces.org/primeng/showcase/#/setup

	1.- Se realiza la importacion del modulo del toolbar y el table, se modifica app.module.ts

		import {ToolbarModule} from 'primeng/toolbar';
		import {TableModule} from 'primeng/table';

		@NgModule({
		  exports: [
		    ButtonModule,
		    CardModule,
		    ToolbarModule,
		    TableModule,
		    MenubarModule
		  ]
		})
		export class PrimeNgModule { }
	2.- Se instala CDK  referencia  --> https://primefaces.org/primeng/showcase/#/table
		Nota: da soporte a las tablas en PrimeNg
		CDK
		VirtualScrolling depends on @angular/cdk s ScrollingModule so begin with installing CDK if not 
		already installed.

			npm install @angular/cdk --save
	3.- Se modifica ordenar.component.html
		3.1.- Se crea toolbar
			<p-toolbar >
			    <div class="p-toolbar-group-left">
			    </div>
			    
			    <div class="p-toolbar-group-right">
			        <button label="Minuscula" (onClick)="mayuscula()" *ngIf="bandera else elseBlock" class="p-mr-3 p-button-success"></button>
			        <ng-template #elseBlock>
			            <button label="Mayuscula" (onClick)="mayuscula()" class="p-mr-3 p-button-success"></button>
			        </ng-template>
			        <button pButton icon="pi pi-sort" label="Por Nombre" class="p-mr-1"></button>
			        <button pButton label="Valores" icon="pi pi-sort" class="p-mr-3 p-button-success"></button>
			        <button pButton label="Por color" icon="pi pi-sort" class="p-mr-3 p-button-danger"></button>
			    </div>
			</p-toolbar>
		3.2.- Se crea tabla
			<hr>

			<div class="p-grid">
			    <div class="p-col">
			        <p-table [value]="[]">
			            <ng-template pTemplate="header">
			                <tr>
			                    <th>Code</th>
			                    <th>Name</th>
			                    <th>Category</th>
			                    <th>Quantity</th>
			                </tr>
			            </ng-template>
			            <ng-template pTemplate="body" let-product>
			                <tr>
			                    <td>{{product.code}}</td>
			                    <td>{{product.name}}</td>
			                    <td>{{product.category}}</td>
			                    <td>{{product.quantity}}</td>
			                </tr>
			            </ng-template>
			        </p-table>
			    </div>
			</div>
*************************************************************************************************************************************
160.- ***************************************************************************************************Llenar table PrimeNgcon data
	1.- Se crea en folder ventas/interfaces  --> venta.interface.ts
		export enum Color{
		    rojo, negro, violeta, azul, blanco
		}

		export interface Heroe{
		    nombre: string,
		    vuela: boolean,
		    color: Color
		}
	2.- Se modifica ordenar.component.ts, se crea lista heroes 
		heroes: Heroe[] = [
		    {
		      nombre: 'Superman',
		      vuela: true,
		      color: Color.azul
		    },
		    {
		      nombre: 'Aquaman',
		      vuela: false,
		      color: Color.violeta
		    },
		    {
		      nombre: 'Batman',
		      vuela: false,
		      color: Color.negro
		    }
		  ]
	3.- Se modifica ordenar.component.html, se modifica la tabla para renderizar heroeSeleccionado
		<div class="p-grid">
		    <div class="p-col">
		        <p-table [value]="heroes">
		            <ng-template pTemplate="header">
		                <tr>
		                    <th>Nombre</th>
		                    <th>Vuela</th>
		                    <th>Color</th>
		                </tr>
		            </ng-template>
		            <ng-template pTemplate="body" let-heroe>
		                <tr>
		                    <td>{{heroe.nombre}}</td>
		                    <td>{{heroe.vuela}}</td>
		                    <td>{{heroe.color}}</td>
		                </tr>
		            </ng-template>
		        </p-table>
		    </div>
		</div>
*************************************************************************************************************************************
161.- *********************************************************************************************Tarea pipe Personalizado vuelaPipe+
	1.- Se crea pipe personalisado, vuela.pipe.ts en el foldes pipes
		import { PipeTransform, Pipe } from '@angular/core';

		@Pipe({
		    name: 'vuela'
		})
		export class VuelaPipe implements PipeTransform{

		    transform( vuela : boolean ): string{
		        return vuela ? 'Si' : 'No';
		    }
		}
	2.- Se modifica el modulo ventas.module.ts, se declara el pipe
		import { VuelaPipe } from './pipes/vuela.pipe';

		@NgModule({
		  declarations: [
		    NumerosComponent, 
		    NoComunesComponent, 
		    BasicosComponent, 
		    OrdenarComponent, 
		    MayusculasPipe,
		    VuelaPipe
		  ],
	3.- Se  modifica ordenar.component.ts, se agrega pipe al atributo de la tabla vuela
		    <ng-template pTemplate="body" let-heroe>
                <tr>
                    <td>{{heroe.nombre}}</td>
                    <td>{{heroe.vuela | vuela}}</td>
                    <td>{{heroe.color}}</td>
                </tr>
            </ng-template>
*************************************************************************************************************************************
162.- ******************************************************************************************************************Oredenar Pipe+
	Ordenamiento en javascript con el metodo sort() --> 
	https://developer.mozilla.org/es/docs/Web/JavaScript/Reference/Global_Objects/Array/sort 
	El método sort() ordena los elementos de un arreglo (array) localmente y devuelve el arreglo ordenado.

	1.- Se crea pipe con angular cli  --> ng g pipe ventas/pipes/ordenar --skipTest
	2.- Se modifica ordenar.pipe.ts
		import { Pipe, PipeTransform } from '@angular/core';
		import { Heroe } from '../interfaces/ventas.interface';

		@Pipe({
		  name: 'ordenar'
		})
		export class OrdenarPipe implements PipeTransform {

		  transform(heroes: Heroe[]): Heroe[] {
		    heroes =  heroes.sort( (a,b) => (a.nombre>b.nombre) ? 1 : -1);

		    return heroes;
		  }

		}
	3.- Se modifica ordenar.component.html, se utiliza pipe ordenar en la lista
		<p-table [value]="heroes | ordenar">
*************************************************************************************************************************************
163.- **************************************************************************************************Parametrizar pipe personalido
************************************************************************************************************************Sort de tabla
	1.- Se modifica ordenar.component.ts
		1.1.- Se crea atributo ordenarPor
			ordenarPor: string= '';	
		1.2.- Se crea metodo parametro()
			 parametro( variable: string): void{
			    console.log(variable);
			    this.ordenarPor = variable;
			    console.log('atributo: ',this.ordenarPor);
			  }	
	2.- Se modifica ordenar.component.html
		2.1.- Se añade evento click a los botones
			<div class="p-toolbar-group-right">
		        <button pButton label="Minuscula" (click)="mayuscula()" *ngIf="bandera else elseBlock" class="p-mr-3 p-button-success"></button>
		        <ng-template #elseBlock>
		            <button pButton label="Mayuscula" (click)="mayuscula()" class="p-mr-3 p-button-success"></button>
		        </ng-template>
		        <button pButton icon="pi pi-sort" label="Por Nombre" class="p-mr-1" (click)="parametro('nombre')"></button>
		        <button pButton label="Vuela" icon="pi pi-sort" class="p-mr-3 p-button-success" (click)="parametro('vuela')"></button>
		        <button pButton label="Por color" icon="pi pi-sort" class="p-mr-3 p-button-danger" (click)="parametro('color')"></button>
		    </div>
		2.2.- Se le añade aparamero al pipe
			<p-table [value]="heroes | ordenar: ordenarPor">
	3.- Se modifica ordenar.pipe.ts, se modifica el metodo transform()
		transform(heroes: Heroe[], tipo: string = ''): Heroe[] {
		    switch (tipo){

		      case 'nombre':
		        return heroes.sort( (a,b) => (a.nombre > b.nombre) ? 1 : -1);

		      case 'vuela':
		        return heroes.sort( (a,b) => (a.vuela>b.vuela) ? -1 : 1);

		      case 'color':
		        return heroes.sort( (a,b) => (a.color>b.color) ? 1 : -1);

		      default:
		        return heroes;
		    }
		    /* heroes =  heroes.sort( (a,b) => (a.nombre>b.nombre) ? 1 : -1);
		    return heroes; */
		  }
*************************************************************************************************************************************
164.- *******************************************************************************************************PrimeNg - Sortable table++
	Referencia  --> https://primefaces.org/primeng/showcase/#/table/sort
	1.- Se modifica ordenar.component.html, se agrega tabla con el sort en los headers
		<div class="p-grid">
		    <div class="p-col">
		        <p-table [value]="heroes">
		            <ng-template pTemplate="header">
		                <tr>
		                    <th pSortableColumn="nombre">Nombre <p-sortIcon field="nombre"></p-sortIcon></th>
		                    <th pSortableColumn="vuela">Vuela <p-sortIcon field="vuela"></p-sortIcon></th>
		                    <th pSortableColumn="color">Color <p-sortIcon field="color"></p-sortIcon></th>
		                </tr>
		            </ng-template>
		            <ng-template pTemplate="body" let-heroe>
		                <tr>
		                    <td>{{heroe.nombre}}</td>
		                    <td>{{heroe.vuela | vuela}}</td>
		                    <td>{{heroe.color}}</td>
		                </tr>
		            </ng-template>
		        </p-table>
		    </div>
		</div>
*************************************************************************************************************************************
Seccion 12: HeroesApp - Rutas Hijas y LazyLoad***************************************************************************************
169.- ***************************************************************************************************Inicio de Proyecto HeroesApp+
	1.- Se cra el nuevo proyecto --> ng new heroesApp	
	2.- Se agrega AngularMaterial  --> ng add @angular/material
*************************************************************************************************************************************
170.- ************************************************************************************************Modulos y componentes iniciales++
	1.- Se crea los modulos a utilizar
		ng g m auth
		ng g m heroes 
		ng g m material
	2.- Dentro de heroes y auth se crean un folder  --> pages
	3.- Se crea componente
		ng g c auth/pages/login --skipTests -is
		ng g c auth/pages/registro --skipTests -is
		ng g c heroes/pages/agregar --skipTests -is
		ng g c heroes/pages/buscar --skipTests -is
		ng g c heroes/pages/heroe --skipTests -is
		ng g c heroes/pages/home --skipTests -is
		ng g c heroes/pages/listado --skipTests -is
	4.- Dentro de app se crea folder d forma manual shared y se crea componente
		ng g c shared/errorPage --skipTests -is
*************************************************************************************************************************************
171.- *********************************************************************************************************Rutas principales Root**
***************************************************************************************************************RouterModule.forRoot()
	1.- Se crea modulo de rutas
		ng g m appRouting --flat
	2.- Se modifica app-routing.module.ts, se configuran las rutas
		2.1.- Se crea una constante con las rutas
			const ROUTES: Routes = [
			  {
			    path: '404',
			    component: ErrorPageComponent
			  },
			  {
			    path: '**',
			    redirectTo: '404'
			  }
			]
		2.2.- Se importan las rutas con el forRoot de RouterModule y se exporta el RouterModule

			@NgModule({
			  declarations: [],
			  imports: [
			    CommonModule,
			    RouterModule.forRoot( ROUTES )
			  ],
			  exports:[
			    RouterModule
			  ]
			})
			export class AppRoutingModule { }
	3.- Se modifica app.module.ts, se importa AppRoutingModule
		  imports: [
		    BrowserModule,
		    BrowserAnimationsModule,
		    AppRoutingModule
		  ],
	4.- Se modifica app.component.html, se agrega componente router-outlet
		<router-outlet></router-outlet>
*************************************************************************************************************************************
172.- *********************************************************************************************Rutas hijas, LazyLoad - AuthRoutes
***************************************************************************************************************************forChild()+
	1.- Se crea modulo de rutas dentro del folder auth
		ng g m auth/authRouting --flat
	2.- Se modifica auth-routing.module.ts, se crean rutas hijas
		2.1.- Se cran rutas
			const ROUTES: Routes = [
			  {
			    path: '',
			    children: [
			      {
			        path: 'login',
			        component: LoginComponent
			      },
			      {
			        path: 'registro',
			        component: RegistroComponent
			      },
			      {
			        path: '**',
			        redirectTo: 'login'
			      }
			    ]
			  }
			]
		2.2.- Se importan las rutas hijas con el metodo forChild() de RouterModule y se exporta el RouterModule
			@NgModule({
			  declarations: [],
			  imports: [
			    CommonModule,
			    RouterModule.forChild( ROUTES )
			  ],
			  exports:[
			    RouterModule
			  ]
			})
			export class AuthRoutingModule { }
	3.- Se modifica auth.module.ts, se importa AuthRoutingModule
		@NgModule({
		  declarations: [LoginComponent, RegistroComponent],
		  imports: [
		    CommonModule,
		    AuthRoutingModule
		  ]
		})
		export class AuthModule { }
	4.- Se modifica app-routing.module.ts, se cargan las rutas hijas
		const ROUTES: Routes = [
		  {
		    path: 'auth',
		    loadChildren: () => import('./auth/auth.module').then( m => m.AuthModule )
		  },
		  {
		    path: '404',
		    component: ErrorPageComponent
		  },
		  {
		    path: '**',
		    redirectTo: '404'
		  }
		]
	5.- Se prueban con el path localhost:4200/auth/login ..registro ..clientes(debe redirigir a login)
*************************************************************************************************************************************
173.- ******************************************************************************************************Rutas Hijas Heroes(Tarea)+
**************************************************************************************************************************loadChildre
	1.- Se crea modulo para las rutas de heroes  --> ng g m heroes/heroesRouting --flat
	2.- Crear las rutas en el archivo heroes-routing.module.ts
		const ROUTES: Routes = [
		  {
		    path:'',
		    children:[
		      {
		        path: 'listado',
		        component: ListadoComponent
		      },
		      {
		        path:'agregar',
		        component: AgregarComponent 
		      },
		      {
		        path:'editar/:id',
		        component: AgregarComponent 
		      },
		      {
		        path: 'buscar',
		        component: BuscarComponent
		      },
		      {
		        path: ':id',
		        component: HeroeComponent
		      },
		      {
		        path: '**',
		        redirectTo: 'listado'
		      }
		    ]
		  }
		];
	3.- Se importan las rutas hijas por medio del metodo forChild() de RouterModule, se exporta RouterModule
		@NgModule({
		  declarations: [],
		  imports: [
		    CommonModule,
		    RouterModule.forChild( ROUTES )
		  ],
		  exports:[
		    RouterModule
		  ]
		})
		export class AuthRoutingModule { }
	4.- Se modifica heroes.module.ts, se importa HeroesRoutingModule
		@NgModule({
		  declarations: [AgregarComponent, BuscarComponent, HeroeComponent, HomeComponent, ListadoComponent],
		  imports: [
		    CommonModule,
		    HeroesRoutingModule
		  ]
		})
		export class HeroesModule { }
	5.- Se modifica app-routing.module.ts, se crea el lazyLoad del modulo heroeSeleccionado
		  {
		    path: 'heroes',
		    loadChildren: () => import('./heroes/heroes.module').then( m => m.HeroesModule)
		  },

	6.- Se modifica error-page.component.html, se crea lnk a las rutas creadas
		<ul>
		    <li>
		        <a routerLink="/auth/login">Login</a>
		    </li>
		    <li>
		        <a routerLink="/auth/registro">Registro</a>
		    </li>
		    <li>
		        <a routerLink="/heroes/listado">Listado</a>
		    </li>
		    <li>
		        <a routerLink="/heroes/agregar">Agregar</a>
		    </li>
		    <li>
		        <a routerLink="/heroes/buscar">Buscar</a>
		    </li>
		</ul>
*************************************************************************************************************************************
175.- **************************************************************************************Mostrar Rutas hijas - Segundo RouterOulet+
	1.- Se modifica heroes-routing.module.ts, se agrega componente que cargara a las rutas hijas
		const ROUTES: Routes = [
		  {
		    path:'',
		    component: HomeComponent,
		    children:[
		      {
		        path: 'listado',
		        component: ListadoComponent
		      },
		      {
		        path:'agregar',
		        component: AgregarComponent 
		      },
		      {
		        path:'editar/:id',
		        component: AgregarComponent 
		      },
		      {
		        path: 'buscar',
		        component: BuscarComponent
		      },
		      {
		        path: ':id',
		        component: HeroeComponent
		      },
		      {
		        path: '**',
		        redirectTo: 'listado'
		      }
		    ]
		  }
		];
	2.- Se modifica home.component.html, se le agrega router-outlet
		<p>home works!</p>
		<hr>
		<router-outlet></router-outlet>
*************************************************************************************************************************************
Seccion 13: HeroesApp - Angular Material & Angular Flex-Layout***********************************************************************
	¿Qué veremos en esta sección?
	Este es un breve listado de los temas fundamentales:

	Angular Material

	Interfaces y tipado

	Pipes personalizados

	Variables de entorno

	Autocomplete de AngularMaterial

	Peticiones HTTP

	JSON-Server

	Angular Flex y Flexbox
180.- ******************************************************************************Continuacion del proyecto heroesApp e instalacion+
**********************************************************************************************************Instalacion de Angular Flex
	Referencias
		Angular Material --> https://material.angular.io/
		Angular Flex  --> https://www.npmjs.com/package/@angular/flex-layout

	1.- Se instala angular Flex
		npm i @angular/flex-layout
	2.- Se instala angular cdk
		npm i -s @angular/flex-layout @angular/cdk 
	3.- Se modifica heroes.module.ts, se importa el FlexLayoutModule
		import { FlexLayoutModule } from '@angular/flex-layout';

		@NgModule({
		  declarations: [AgregarComponent, BuscarComponent, HeroeComponent, HomeComponent, ListadoComponent],
		  imports: [
		    CommonModule,
		    HeroesRoutingModule,
		    FlexLayoutModule
		  ]
		})
		export class HeroesModule { }
*************************************************************************************************************************************
181.- *********************************************************************************************Material Sidenav, toolbar e iconos
	1.- Se modifica material.module.ts, se exporta MatSidenavModule
	Referencias de iconos de angular material  --> https://fonts.google.com/icons?selected=Material+Icons:bookmark
	1.- Se modifica app.module.t, se importa MaterialModule
		@NgModule({
		  declarations: [
		    AppComponent,
		    ErrorPageComponent
		  ],
		  imports: [
		    BrowserModule,
		    BrowserAnimationsModule,
		    MaterialModule,
		    AppRoutingModule
		  ],
		  providers: [],
		  bootstrap: [AppComponent]
		})
		export class AppModule { }
	2.- Se modifica materialModule, se exportan componentes de angular Material 
		@NgModule({
		  exports:[
		    MatToolbarModule,
		    MatSidenavModule,
		    MatButtonModule,
		    MatIconModule
		  ],
		  imports:[
		  ]
		})
		export class MaterialModule { }
	3.- Se modifica home.component.html, se renderiza Sidenav, se comenta el router-outlet
		<!-- <router-outlet></router-outlet> -->

		<mat-sidenav-container fullscreen>
		    <!-- Se coloca referencia local sidenav -->
		    <mat-sidenav #sidenav>
		        <h1>SideNav</h1>
		    </mat-sidenav>
		    <mat-toolbar>
		        <!-- se crea evento click que desencadena el evento toggle() del elemento sidenav -->
		        <button mat-icon-button (click)="sidenav.toggle()">
		            <mat-icon>menu</mat-icon>
		        </button>
		    </mat-toolbar>
		</mat-sidenav-container>
*************************************************************************************************************************************
182.- ***************************************************************************************************************Material NavList
	1.- Se modifca home.component.htlml, se crean itmens de la lista de sidenav, se agrega router-outlet, se agrega mat-toolbar al
	sidenav, se crea boton de logout
		<mat-sidenav-container fullscreen>
		    <!-- Se coloca referencia local sidenav -->
		    <mat-sidenav #sidenav mode="push">
		        <mat-toolbar color="primary">
		            <span>Menu</span>
		            <button mat-icon-button (click)="sidenav.toggle()">
		                <mat-icon>menu</mat-icon>
		            </button>
		        </mat-toolbar>
		        <!-- Se crea lista de items -->
		        <mat-nav-list (click)="sidenav.toggle()">
		            <a routerLink="./listado" mat-list-item>
		                <mat-icon mat-list-icon>label</mat-icon>
		                <span>Listado de Heroes</span>
		            </a>
		            <a routerLink="./agregar" mat-list-item>
		                <mat-icon mat-list-icon>add</mat-icon>
		                <span>Agregar Heroes</span>
		            </a>
		            <a routerLink="./buscar" mat-list-item>
		                <mat-icon mat-list-icon>search</mat-icon>
		                <span>Buscar Heroe</span>
		            </a>
		        </mat-nav-list>            
		    </mat-sidenav>
		    <mat-toolbar>
		        <!-- se crea evento click que desencadena el evento toggle() del elemento sidenav -->
		        <button mat-icon-button (click)="sidenav.toggle()">
		            <mat-icon>menu</mat-icon>
		        </button>
		        <div class="spacer"></div>
		        <button mat-icon-button style="margin-right: 50px">
		            <mat-icon>logout</mat-icon>
		            Logout
		        </button>
		    </mat-toolbar>
		    <div class="container">
		        <router-outlet></router-outlet>
		    </div>    
		</mat-sidenav-container>
	2.- Se modifica style.css, se crea clase spacer que se utiliza para el boton logout
		/* toma todo el espacio y lo empuja al margen */
		.spacer{
		    flex: 1 1 auto;
		}
	3.- Se modifica home.component.ts, se agrega clase css container
		@Component({
		  selector: 'app-home',
		  templateUrl: './home.component.html',
		  styles: [`
		    .container{
		      margin: 10px
		    }
		  `]
		})
		export class HomeComponent implements OnInit {

		  constructor() { }

		  ngOnInit(): void {
		  }

		}
	4.- Se modifica material.module.ts, se exporta MatListModule
		@NgModule({
		  exports:[
		    MatToolbarModule,
		    MatSidenavModule,
		    MatButtonModule,
		    MatListModule,
		    MatIconModule
		  ],
		  imports:[
		  ]
		})
		export class MaterialModule { }
*************************************************************************************************************************************
183.- *****************************************************************************************************Heroes Backend json-server
	Referencia json-server --> https://www.npmjs.com/package/json-server
	1.- Se instala json-server
		npm install -g json-server
	2.- Se se descargan db.json y se coloca en la carpeta del proyecto
		json-server --watch db.json
	3.- Se descargan las imagenes y se colocan en los assets del proyecto

*************************************************************************************************************************************
*************************************************************************************************************************************

















Seccion 16: Formularios -Template y LazyLoad*****************************************************************************************
226.- **************************************************************************************************Inicio de seccion Formularios
	1.- Creacion de Proyecto --> ng new Formularios
	2.- Se modifica index.html, se coloca link de bootstrap
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	3.- Se modifica el app.component.html
		3.1.- Se borra el contenido de angular
		3.2.- Se añade un h1 --> <h1>Formularios</h1>
*************************************************************************************************************************************
227.- *************************************************************************************************Creacion de modulos necesarios++
	1.- Se crea un modulo shared --> ng g m shared
	2.- Se crea componente sidemenu en shared --> ng g c shared/sidemenu --skipTests -is
	3.- Se modifica app.module.ts, se importa el SharedModule
		@NgModule({
				  declarations: [
				    AppComponent
				  ],
				  imports: [
				    BrowserModule,
				    AppRoutingModule,
				    SharedModule
				  ],
				  providers: [],
				  bootstrap: [AppComponent]
				})
		export class AppModule { }
	4.- Se modifica shared.menu.ts,  se exporta el componente sidemenuComponent
		@NgModule({
		  declarations: [SidemenuComponent],
		  imports: [
		    CommonModule
		  ],
		  exports:[
		    SidemenuComponent
		  ]
		})
		export class SharedModule { }
	5.- Se modifica app.component.html, se agrega componente app-sidemenu
		<app-sidemenu></app-sidemenu>
	6.- Se crea modulo con su routing --> ng g m template --routing
										--> ng g m reactive --routing
*************************************************************************************************************************************
228.- *******************************************************************************************Componentes y LazyLoad de Formulario++
	1.- Se crean componentes
		ng g c reactive/basicos --skipTests -is
		ng g c reactive/dinamicos --skipTests -is
		ng g c reactive/switches --skipTests -is
		ng g c template/basicos --skipTests -is
		ng g c template/dinamicos --skipTests -is
		ng g c template/switches --skipTests -is
	2.- Se modifica reactive-routing.module.ts, se crean las rutas
		const routes: Routes = [
			  {
			    path:'',
			    children:[
			      {
			        path:'basicos', component: BasicosComponent
			      },
			      {
			        path:'dinamicos', component: DinamicosComponent
			      },
			      {
			        path:'switches', component: SwitchesComponent
			      },
			      {
			        path: '**', redirectTo: 'basicos'
			      }
			    ]
			  }
			];
	3.- Se modifica template-routing.module.ts, se crean las rutas
		import { BasicosComponent } from './basicos/basicos.component';
		import { DinamicosComponent } from './dinamicos/dinamicos.component';
		import { SwitchesComponent } from './switches/switches.component';

		const routes: Routes = [
		  {
		    path:'',
		    children:[
		      {
		        path:'basicos', component: BasicosComponent
		      },
		      {
		        path:'dinamicos', component: DinamicosComponent
		      },
		      {
		        path:'switches', component: SwitchesComponent
		      },
		      {
		        path: '**', redirectTo: 'basicos'
		      }
		    ]
		  }
		];
	4.- Se modifica app-routing.module.ts, se crean las rutas a los modulos template y reactive
		const routes: Routes = [
		  {
		    path:'template',
		    loadChildren: () => import('./template/template.module').then(m => m.TemplateModule)
		  },
		  {
		    path: 'reactive',
		    loadChildren: () => import('./reactive/reactive.module').then(m =>m.ReactiveModule)
		  },
		  {
		    path: '**',
		    redirectTo:'template'
		  }
		];
*************************************************************************************************************************************
229.- ***********************************************************************************************************************SideMenu
	1.- Se modifica shared.module.ts, se importa el RouterModule
		@NgModule({
		  declarations: [SidemenuComponent],
		  imports: [
		    CommonModule,
		    RouterModule
		  ],
		  exports:[
		    SidemenuComponent
		  ]
		})
		export class SharedModule { }
	2.- Se modifica sidemenu.component.html
		<h2>Template</h2>
		<hr>
		<ul class="list-group">
		    <li class="list-group-item" *ngFor="let item of templateMenu" [routerLink]="[item.ruta]" routerLinkActive="active">
		        {{item.texto}}
		    </li>
		</ul>
		<h2>Reactive</h2>
		<hr>
		<ul class="list-group">
		    <li class="list-group-item" *ngFor="let item of reactiveMenu" [routerLink]="[item.ruta]" routerLinkActive="active">
		        {{item.texto}}
		    </li>
		</ul>
	3.- Se modifica sidemenu.component.ts
		3.1.- Se crea una interface para las rutas del menu
			interface MenuItem{
			  texto: string,
			  ruta: string
			}
		3.2.- Se crean arreglos con las rutas para el menu
			@Component({
			  selector: 'app-sidemenu',
			  templateUrl: './sidemenu.component.html',
			  styles: [
			  ]
			})
			export class SidemenuComponent  {

			  templateMenu: MenuItem[] =[
			    {
			      texto: 'Basicos',
			      ruta: './template/basicos'
			    },{
			      texto: 'Dinamicas',
			      ruta: './template/dinamicos'
			    },{
			      texto: 'Switches',
			      ruta: './template/switches'
			    },
			  ]

			  reactiveMenu: MenuItem[] =[
			    {
			      texto: 'Basicos',
			      ruta: './reactive/basicos'
			    },{
			      texto: 'Dinamicas',
			      ruta: './reactive/dinamicos'
			    },{
			      texto: 'Switches',
			      ruta: './reactive/switches'
			    },
			  ]

			}
*************************************************************************************************************************************
230.- ****************************************************************************************************Diseño de formulario Basico
	1.- Se modifica template/basicos.component.html, se renderiza el formulario
		<h2>Template Basicos</h2>
		<hr>

		<div class="row">
		    <div class="col">
		        <form action="">
		            <!-- Campo de nombre -->
		            <div class="mb-3 col">
		                <label class="col-sm-3 col-form-label">Producto</label>
		                <input type="text" class="form-control" placeholder="Nombre del producto">
		                <span class="form-text text-danger"> Debe tener al menos 4 letras</span>
		            </div>
		            <!-- Campo de precio -->
		            <div class="mb-3 col">
		                <label class="col-sm-3 col-form-label">Precio</label>
		                <input type="number" class="form-control" placeholder="Precio del producto">
		            </div>
		            <!-- Campo de existencia -->
		            <div class="mb-3 col">
		                <label class="col-sm-3 col-form-label">Existencia</label>
		                <input type="number" class="form-control" placeholder="Existencia del producto">
		            </div>
		            <div class="row">
		                <div class="col">
		                    <button type="submit" class="btn btn-primary float-end">Guardar</button>
		                </div>
		            </div>
		        </form>
		    </div>
		</div>
*************************************************************************************************************************************
231.- *********************************************************************************************************Template - FormsModule
******************************************************************************************************************************ngModel
*****************************************************************************************************************************ngSubmit
*******************************************************************************************************************************ngForm
	1.- Se modifica template.module.ts, se importa el FormsModule
		@NgModule({
		  declarations: [BasicosComponent, DinamicosComponent, SwitchesComponent],
		  imports: [
		    CommonModule,
		    FormsModule,
		    TemplateRoutingModule
		  ]
		})
		export class TemplateModule { }
	2.- Se modifica template/basicos.component.html
		2.1.- Se le añade referencia al formulario y se agrega directiva ngSubmit
			<form (ngSubmit)="guardar(myForm)" #myForm="ngForm">
		2.2.- Se le agrega la directiva NgModel y el atributo name a cada input
			<!-- Campo de nombre -->
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Producto</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="Nombre del producto" name="nombre" ngModel required>
                    <span class="form-text text-danger"> Debe tener al menos 4 letras</span>
                </div>
            </div>
            <h1>prueba {{nombre}}</h1>
            <!-- Campo de precio -->
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Precio</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="Precio del producto" name="precio" ngModel>
                </div>
            </div>
            <!-- Campo de existencia -->
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Existencia</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="Existencia del producto" name="existencia" ngModel>
                </div>
            </div>
           2.3.- Se imprimen valores del formulario

				<div class="row">
				    <div class="col">
				        <span>Valid</span>
				        <pre>{{myForm.valid}}</pre>
				        <span>Value</span>
				        <pre>{{myForm.value | json}}</pre>
				    </div>
				</div>

	3.- Se modifica template/basicos.component.ts
		3.1.- Se crea metodo guardar
			  guardar( myForm: NgForm){
			    console.log(myForm.value);
			  }
************************************************************************************************************************************
232.- *******************************************************************************************Template: mostrar mensajes de error
******************************************************************************************************************elementos del Form
*************************************************************************************touched y pristine para validar elementos input
**************************************************************************************************************************[disabled]
	1.- Se modifica template/basicos.component.html
		1.1.- Se imprimenlos valores de touched y pristine
			<!-- Valida si el formulario fue tocado por el usuario -->
	        <span>Pristine</span>
	        <pre>{{myForm.pristine }}</pre>
	        <span>Touched</span>
	        <pre>{{myForm.touched | json}}</pre>
	    1.2.- Se valida el mensaje de error en nombre
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Producto</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="Nombre del producto" name="nombre" minlength="4" ngModel required>
                    <span class="form-text text-danger" *ngIf="myForm.controls.nombre?.invalid && myForm.controls.nombre?.touched">
                        Debe tener al menos 4 letras
                   </span>
                </div>
            </div>
        1.3.- Se inhabilita boton en caso de tener errores el formulario
        	<div class="row">
                <div class="col">
                    <button type="submit" class="btn btn-primary float-end" [disabled] ="myForm.invalid">Guardar</button>
                </div>
            </div>
************************************************************************************************************************************
233.- ********************************************************************************************************************@viewChild++
	1.- Se modifica template/basicos.component.ts
		1.1.- Se crea viewChild
			@ViewChild('myForm') myForm!: NgForm;

		1.2.- Se modifica metodo guardar()
			  guardar( ){
			    console.log(this.myForm);
			  }
		1.3.- Se crea metodo para hacer la validacion con el viewChield
			  validarNombre(): boolean{
			    return this.myForm?.controls.nombre?.invalid 
			            && this.myForm?.controls.nombre?.touched;
			  }
	2.- Se modifica template/basicos.component.html
		1.- Se modifica la llamada a guardar
			<form (ngSubmit)="guardar()" #myForm="ngForm">
		2.- Se modifica la validacion del metodo de error
			<span class="form-text text-danger" *ngIf="validarNombre()"> Debe tener al menos 4 letras</span>
************************************************************************************************************************************
234.- **************************************************************************************************Template: validar numero > 0
	1.- Se modifica template/basicos.html, se realiza balidacion de campo tipo number
		<span class="form-text text-danger" *ngIf="validarPrecio()"> Debe ser mayor a 0</span>
	2.- Se modifica template/basicos.ts, se crea metodo validarPrecio()
		  validarPrecio():boolean{
		    return this.myForm?.controls.precio?.value <= 0 && this.myForm?.controls.precio?.touched ? true :  false;
		  }
************************************************************************************************************************************
235.- ********************************************************************************Directivas perzonalizadas -CustomMin- Optional
**************************************************************************************************************************@Directive+	
***********************************************************************************************************************NG_VALIDATORS+
******************************************************************************************************************************@Input
*************************************************************************************************************************FormControl+
***************************************************************************************************************************Validator+
	1.- Se crea dentro de folder template, un folder --> directivas --> custom-min.directive.ts
	1.- Se crea folder --> Directivas --> custom-min.directive.ts
	2.- Se modifica custom-min.directive.ts
		@Directive({
		    //Para que active la rirectiva el elemento de la vista debe tener el selector
		    selector: '[customMin][ngModel]',
		    providers: [{
		        provide: NG_VALIDATORS,
		        useExisting: CustomMinDirective,
		        multi: true
		    }]
		})
		//Se implementa el validator
		export class CustomMinDirective implements Validator{
		    //Se captura el atributo 
		    @Input() minimo!: number;
		    

		    constructor(){
		        console.log('Directive', this.minimo);
		    }

		    validate(control: FormControl){
		        const inputValue = control.value;
		        return (inputValue < this.minimo) ?
		                {'custonMin': true}:
		                null;
		    }

		}
	3.- Se modifica template/basicos.component.html, se le agrega directiva new, valor capturado por la directiva --> minimo y la
	validacion required
		    <!-- Campo de existencia -->
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Existencia</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" customMin [minimo]="0" required placeholder="Existencia del producto" name="existencia" ngModel>
                </div>
            </div>
        1.- Se agrega muestra del json de errores del elemento existencia
	        <span>CustomDirective</span>
			<pre>{{myForm.controls.existencia?.errors| json}}</pre>
************************************************************************************************************************************
236.- *************************************************************************************************Template: Limpiar Formularios+++
***************************************************************************************************************************resetForm
***************************************************************************************************************************[ngModel]
	1.- Se modifica template/basicos.component.ts
		1.1.- Se modifica metodo guardar()
			  guardar( ){
			    //Se realiza reset del formulario
			    this.myForm.resetForm();
			  }
		1.2.- Se crea atributo de tipo obj
			  initForm = {
			    producto: 'RTX 4080',
			    precio: 10,
			    existencia: 5
			  }
	2.- se modifica template/basicos.component.html, se le agrega datos de inicio al ngModel
		<!-- Campo de nombre -->
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Producto</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="Nombre del producto" name="nombre" minlength="4" [ngModel]="initForm.producto" required>
                    <span class="form-text text-danger" *ngIf="validarNombre()"> Debe tener al menos 4 letras</span>
                </div>
            </div>
            <!-- Campo de precio -->
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Precio</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="Precio del producto" name="precio" [ngModel]="initForm.precio">
                    <span class="form-text text-danger" *ngIf="validarPrecio()"> Debe ser mayor a 0</span>   
                </div>
            </div>
            <!-- Campo de existencia -->
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Existencia</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" customMin [minimo]="0" required placeholder="Existencia del producto" name="existencia" [ngModel]="initForm.existencia">
                </div>
            </div>
************************************************************************************************************************************
237.- **********************************************************************************************Formularios Dinamicos y arreglos
	1.- Se modifica template/dinamicos.component.html
		<h2>Template <small>dinamicos</small></h2>
		<hr>
		<form (ngSubmit)="guardar()" #FormularioDinamico="ngForm">
		    <!-- Nombre -->
		    <div class="mb-3 row">
		        <label for="" class="col-sm-3 col-form-label">Nombre:</label>
		        <div class="col-sm-9">
		            <input type="text" class="form-control" placeholder="Nombre de la persona" name="nombre" required ngModel>
		            <span class="form-text text-danger" *ngIf="FormularioDinamico.controls.nombre?.errors && FormularioDinamico.controls.nombre?.touched">
		                Campo nombre es requerido.
		            </span>
		        </div> 
		    </div>
	/*
		    <!-- Agregar 
		    <div class="mb-3 row">
		        <label class="col-sm-3 col-form-label">Agregar:</label>
		        <div class="col-sm-9">
		            <div class="input-group">
		                <input type="text" class="form-control" placeholder="Agregar Favorito" name="favorito" >
		                <button class="btn btn-outline-primary" type="button">Agregar</button>
		            </div>
		        </div> 
		    </div>
	
		    <!-- Lista -->
		    <div class="mb-3 row">
		        <label for="" class="col-sm-3 col-form-label">Favoritos:</label>
		        <div class="col-sm-9">
		            <input type="text" class="form-control">
		        </div> 
		    </div>


		    <div class="row">
		        <div class="col-sm-12">
		            <button type="button" (click)="guardar()" class="btn btn-primary float-end" [disabled]="FormularioDinamico.invalid">Guardar</button>
		        </div>
		    </div>
		</form>*/
************************************************************************************************************************************
238.- ******************************************************************************************Agragar elementos de forma diinamica+
	1.- Se modifica template/favoritos.component.ts, se agregan interfaces y se crea tributo de tipo persona
		1.1.- Se crean interfaces persona y Favoritos
			interface Persona{
			  nombre: string;
			  favoritos: Favorito[];
			}

			interface Favorito{
			  id: number;
			  nombre: string;
			}
		1.2.- Se crea atributo persona
			  persona:Persona = {
			    nombre:'Elvis',
			    favoritos: [
			      { id:1, nombre:'Sperman'},
			      { id:2, nombre:'Batman'}
			    ]
			  }
	2.- Se modifica template/favoritos.component.html, se modifica elemento de la lista, se le agrega binding, arreglo de la 
	lista por for

	    <!-- Lista -->
	    <div class="mb-3 row">
	        <label for="" class="col-sm-3 col-form-label">Favoritos:</label>
	        <div class="col-sm-9">
	            <input type="text" class="form-control mb-1" *ngFor="let heroe of persona.favoritos; let i = index" [(ngModel)]="heroe.nombre" name="favorito_{{i}}" required>
	        </div> 
	    </div>
	    2.1.- Se craega elelemnto para ver contenido del objeto persona
	    	<span>Favoritos</span>
			<pre>{{persona | json}}</pre>
************************************************************************************************************************************
239.- ************************************************************************************Eliminar elemento creado de forma dinamica+
	1.- Se modifica template/dinamicos.component.html, se modifica elemento de lista, se agrega boton de eliminar
		<!-- Lista -->
	    <div class="mb-3 row">
	        <label for="" class="col-sm-3 col-form-label">Favoritos:</label>
	        <div class="col-sm-9">
	            <div class="input-group mb-1" *ngFor="let heroe of persona.favoritos; let i = index">
	                <input type="text" class="form-control"  [(ngModel)]="heroe.nombre" name="favorito_{{i}}" required>
	                <button class="btn btn-outline-primary" type="button" (click)="eliminar(i)">Eliminar</button>
	            </div>            
	        </div> 
	    </div>
	2.- Se modifica template/dinamicos.component.ts, se crea metodo eliminar()
		  eliminar(index:number){
		    this.persona.favoritos.splice(index,1);
		  }
************************************************************************************************************************************
240.- ******************************************************************************************************Agregar heroes favoritos+
	1.- Se modifica template/dinamicos.component.ts, se crea metodo agregarPersonaje()

		1.1.- Se agrega atributo 
			nuevoPersonaje: string = '';
		1.2.- Se crea metodo para agregar personajes
		  agregarPersonaje(){
			    const favoritos: number[]=[];
			    this.persona.favoritos.forEach( item => {
			      favoritos.push(item.id);
			    })
			    var max = Math.max.apply(null,favoritos);
			    const personajeNew:  Favorito ={
			      id: max + 1,
			      nombre: this.nuevoPersonaje
			    };
			    this.persona.favoritos.push({...personajeNew});
			    this.nuevoPersonaje='';
			  }

	2.- Se modifica template./dinamicos.component.html, se le agrega ngModel y evento click al boton
		    <!-- Agregar -->
		    <div class="mb-3 row">
		        <label for="" class="col-sm-3 col-form-label">Agregar:</label>
		        <div class="col-sm-9">
		            <div class="input-group">
		                <input type="text" class="form-control" placeholder="Agregar Favorito" name="favorito" [(ngModel)]="nuevoPersonaje" (keyup.enter)="agregarPersonaje()">
		                <button class="btn btn-outline-primary" type="button" (click)="agregarPersonaje()">Agregar</button>
		            </div>
		        </div> 
		    </div>
************************************************************************************************************************************
241.- *********************************************************************************************Template: Radio, check y Switches++
	
	1.- Se modifica template/switches.component.html, se rnderizan elementos
		<h2>Template<small> Switches</small></h2>
		<hr>


		<form #switchForm="ngForm">
		    <!-- Radio -->
		    <div class="row mb-3">
		        <label class="col-sm-3 col-form-label">Genero</label>
		        <div class="col-sm-9">
		            <div class="form-check">
		                <input class="form-check-input" type="radio" value="" name="radioGenero" id="radioMasculino">
		                <label class="form-check-label" for="radioMasculino">
		                  Masculino
		                </label>
		              </div>
		              <div class="form-check">
		                <input class="form-check-input" type="radio" value="" name="radioGenero" id="radioFemenino" checked>
		                <label class="form-check-label" for="radioFemenino">
		                  Femenino
		                </label>
		              </div>
		        </div>
		    </div>

		    <!-- Switch -->
		    <div class="row mb-3">
		        <label class="col-sm-3 col-form-label">Notificaciones</label>
		        <div class="col-sm-9">
		            <div class="form-check form-switch">
		                <input class="form-check-input" type="checkbox" id="flexSwitchCheckChecked">
		                <label class="form-check-label" for="flexSwitchCheckChecked">Recibir Notificaciones</label>
		            </div>
		        </div>
		    </div>

		    <!-- Switch -->
		    <div class="row mb-3">
		        <label class="col-sm-3 col-form-label"></label>
		        <div class="col-sm-9">
		            <div class="form-check">
		                <input class="form-check-input" type="checkbox" id="flexCheckDefault">
		                <label class="form-check-label" for="flexCheckDefault">
		                  Terminos y condiciones de Uso
		                </label>
		              </div>
		        </div>
		    </div>

		    <div class="row">
		        <div class="col">
		            <button class="btn btn-primary float-end">Guardar</button>
		        </div>
		    </div>
		</form>
************************************************************************************************************************************
242.- ***********************************************************************************Template: Validando Radio, check y Switches++
	1.- Se modifica templates/switches.component.ts
		  persona = {
		    genero : '', 
		    notificaciones: 'false'
		  }

		  terminos: boolean = false;
	2.- Se modifica templates/switches.component.html
		2.1.- Se modifica elemento radio, se le coloca ngModel y name
			<!-- Radio -->
		    <div class="row mb-3">
		        <label class="col-sm-3 col-form-label">Genero</label>
		        <div class="col-sm-9">
		            <div class="form-check">
		                <input class="form-check-input" type="radio" value="" name="radioGenero" id="radioMasculino" value="M" [(ngModel)]="persona.genero" required>
		                <label class="form-check-label" for="radioMasculino">
		                  Masculino
		                </label>
		              </div>
		              <div class="form-check">
		                <input class="form-check-input" type="radio" value="" name="radioGenero" id="radioFemenino" value="F" [(ngModel)]="persona.genero" required>
		                <label class="form-check-label" for="radioFemenino">
		                  Femenino
		                </label>
		              </div>
		        </div>
		    </div>
		2.2.- Se modifica el switch 
			    <!-- Switch -->
		    <div class="row mb-3">
		        <label class="col-sm-3 col-form-label">Notificaciones</label>
		        <div class="col-sm-9">
		            <div class="form-check form-switch mt-2">
		                <input class="form-check-input" type="checkbox" id="flexSwitchCheckChecked" [(ngModel)]="persona.notificaciones" name="notificaciones">
		                <label class="form-check-label" for="flexSwitchCheckChecked">Recibir Notificaciones</label>
		            </div>
		        </div>
		    </div>
		2.3.- Se modifica check, se coloca name  y ngModelS
			    <!-- checkBox -->
		    <div class="row mb-3">
		        <label class="col-sm-3 col-form-label"></label>
		        <div class="col-sm-9">
		            <div class="form-check">
		                <input class="form-check-input" type="checkbox" id="flexCheckDefault" name="terminos" [(ngModel)]="terminos" required>
		                <label class="form-check-label" for="flexCheckDefault">
		                  Terminos y condiciones de Uso
		                </label>
		              </div>
		        </div>
		    </div>
		2.4.- Se inhabilita el bototn
			   <div class="row">
		        <div class="col">
		            <button class="btn btn-primary float-end" [disabled]="switchForm.invalid">Guardar</button>
		        </div>
		    </div>
************************************************************************************************************************************
Seccion 17: Formularios Reactivos***************************************************************************************************
246.- *****************************************************************************************Continuacion del proyecto formularios
	1.- Se modifica reactive/basicos.component.html
		<h2>Reactive Basicos</h2>
		<hr>

		<div class="row">
		    <div class="col">
		        <form >
		            <!-- Campo de nombre -->
		            <div class="mb-3 row">
		                <label class="col-sm-3 col-form-label">Producto</label>
		                <div class="col-sm-9">
		                    <input type="text" class="form-control" placeholder="Nombre del producto" >
		                    <span class="form-text text-danger" > Debe tener al menos 4 letras</span>
		                </div>
		            </div>
		            <!-- Campo de precio -->
		            <div class="mb-3 row">
		                <label class="col-sm-3 col-form-label">Precio</label>
		                <div class="col-sm-9">
		                    <input type="text" class="form-control" placeholder="Precio del producto" >
		                    <span class="form-text text-danger" > Debe ser mayor a 0</span>   
		                </div>
		            </div>
		            <!-- Campo de existencia -->
		            <div class="mb-3 row">
		                <label class="col-sm-3 col-form-label">Existencia</label>
		                <div class="col-sm-9">
		                    <input type="text" class="form-control"  placeholder="Existencia del producto" >
		                </div>
		            </div>
		            <div class="row">
		                <div class="col">
		                    <button type="submit" class="btn btn-primary float-end" >Guardar</button>
		                </div>
		            </div>
		        </form>
		    </div>
		</div>
		<!-- <div class="row">
		    <div class="col">
		        <span>Valid</span>
		        <pre>{{myForm.valid}}</pre>
		        <span>Value</span>
		        <pre>{{myForm.value | json}}</pre> -->
		        <!-- Valida si el formulario fue tocado por el usuario -->
		<!--         <span>Pristine</span>
		        <pre>{{myForm.pristine }}</pre>
		        <span>Touched</span>
		        <pre>{{myForm.touched | json}}</pre>
		        <span>CustomDirective</span>
		        <pre>{{myForm.controls.existencia?.errors| json}}</pre>
		    </div>
		</div> -->
	2.- Se modifica reactive/dinamicos.component.html
		/*<h2>Reactive <small>dinamicos</small></h2>
		<hr>
		<form >
		    <!-- Nombre -->
		    <div class="mb-3 row">
		        <label for="" class="col-sm-3 col-form-label">Nombre:</label>
		        <div class="col-sm-9">
		            <input type="text" class="form-control" placeholder="Nombre de la persona" >
		            <span class="form-text text-danger" >
		                Campo nombre es requerido.
		            </span>
		        </div> 
		    </div>

		    <!-- Agregar -->
		    <div class="mb-3 row">
		        <label for="" class="col-sm-3 col-form-label">Agregar:</label>
		        <div class="col-sm-9">
		            <div class="input-group">
		                <input type="text" class="form-control" placeholder="Agregar Favorito" >
		                <button class="btn btn-outline-primary" type="button" >Agregar</button>
		            </div>
		        </div> 
		    </div>

		    <!-- Lista -->
		    <div class="mb-3 row">
		        <label for="" class="col-sm-3 col-form-label">Favoritos:</label>
		        <div class="col-sm-9">
		            <div class="input-group mb-1" >
		                <input type="text" class="form-control"  >
		                <button class="btn btn-outline-primary" type="button" >Eliminar</button>
		            </div>            
		        </div> 
		    </div>


		    <div class="row">
		        <div class="col-sm-12">
		            <button type="button"  class="btn btn-primary float-end" >Guardar</button>
		        </div>
		    </div>
		</form>

		<!-- <span>Favoritos</span>
		<pre>{{persona | json}}</pre> -->*/
	3.- Se modifica reactive/switches.component.html
		/*<h2>Reactive<small> Switches</small></h2>
		<hr>
		<form>
		    <!-- Radio -->
		    <div class="row mb-3">
		        <label class="col-sm-3 col-form-label">Genero</label>
		        <div class="col-sm-9">
		            <div class="form-check">
		                <input class="form-check-input" type="radio" value="" name="radioGenero" id="radioMasculino" >
		                <label class="form-check-label" for="radioMasculino">
		                  Masculino
		                </label>
		              </div>
		              <div class="form-check">
		                <input class="form-check-input" type="radio" value="" name="radioGenero" id="radioFemenino" value="F" >
		                <label class="form-check-label" for="radioFemenino">
		                  Femenino
		                </label>
		              </div>
		        </div>
		    </div>

		    <!-- Switch -->
		    <div class="row mb-3">
		        <label class="col-sm-3 col-form-label">Notificaciones</label>
		        <div class="col-sm-9">
		            <div class="form-check form-switch mt-2">
		                <input class="form-check-input" type="checkbox" id="flexSwitchCheckChecked" >
		                <label class="form-check-label" for="flexSwitchCheckChecked">Recibir Notificaciones</label>
		            </div>
		        </div>
		    </div>

		    <!-- checkBox -->
		    <div class="row mb-3">
		        <label class="col-sm-3 col-form-label"></label>
		        <div class="col-sm-9">
		            <div class="form-check">
		                <input class="form-check-input" type="checkbox" id="flexCheckDefault" >
		                <label class="form-check-label" for="flexCheckDefault">
		                  Terminos y condiciones de Uso
		                </label>
		              </div>
		        </div>
		    </div>

		    <div class="row">
		        <div class="col">
		            <button class="btn btn-primary float-end" >Guardar</button>
		        </div>
		    </div>
		</form>

		<!-- <h5>Valores del formulario</h5>
		<pre>{{switchForm.value | json}}</pre>

		<h5>Validacion del formulario</h5>
		<pre>{{switchForm.valid | json}}</pre>

		<h5>Validacion de termino</h5>
		<pre>{{ switchForm.controls?.terminos?.errors | json}}</pre> -->*/
************************************************************************************************************************************
247.- *******************************************************************************************Importacion del ReactiveFormsModule+++
*****************************************************************************************************************ReactiveFormsModule
***************************************************************************************************************************FormGroup	
*************************************************************************************************************************FormControl
**********************************************************************************************************************formContrlName+++
	1.- Se modifica reactive.module.ts, se importa ReactiveFormsModule
		@NgModule({
		  declarations: [BasicosComponent, DinamicosComponent, SwitchesComponent],
		  imports: [
		    CommonModule,
		    ReactiveFormsModule,
		    ReactiveRoutingModule
		  ]
		})
		export class ReactiveModule { }
	2.- Se modifica reactive/basicos.component.ts, se crea atributo de tipo FormGroup
		  myForm: FormGroup = new FormGroup({
		    nombre : new FormControl('RTX 4080')
		  })
	3.- Se modifica reactive/basicos.component.html, se identifica formulario y elementos
		3.1.- Se modifica el form
			<form [formGroup]="myForm">
		3.2.- Se agrega atributo formContrlName
			 <!-- Campo de nombre -->
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Producto</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="Nombre del producto" formControlName="nombre">
                    <span class="form-text text-danger" > Debe tener al menos 4 letras</span>
                </div>
            </div>
************************************************************************************************************************************
248.- *******************************************************************************************************************FormBuilder+++
	1.- Se modifica reactive/basicos.component.ts
		1.1.- Se agregan elementos al FormGroup
			  myForm: FormGroup = new FormGroup({
			    nombre      : new FormControl('RTX 4080'),
			    precio      : new FormControl(0),
			    existencia  : new FormControl(5),
			  })
		1.2.- Se trabaja con FormBuilder
			1.2.1.- Se realiza inyeccion de dependencia
				  constructor( private formBuilder: FormBuilder ) { }
			1.2.2.- Se modifica el atributo myForm para que trabaje con el FormBuilder
				/* myForm: FormGroup = new FormGroup({
				    nombre      : new FormControl('RTX 4080'),
				    precio      : new FormControl(0),
				    existencia  : new FormControl(5),
				  }) */

				  myForm: FormGroup = this.formBuilder.group({
				    nombre: ['RTX 4080'],
				    precio: [0],
				    existencia: [6]
				  })
	2.- Se modifica reactive/basicos.component.html
		2.1.- Se agrega formControlName a los elementos
			<!-- Campo de precio -->
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Precio</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="Precio del producto" formControlName="precio">
                    <span class="form-text text-danger" > Debe ser mayor a 0</span>   
                </div>
            </div>
            <!-- Campo de existencia -->
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Existencia</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"  placeholder="Existencia del producto" formControlName="existencia">
                </div>
            </div>
************************************************************************************************************************************
249.- ********************************************************************************************Validaciones Basicas FormValidator+++
	1.- Se modifica reactive/basicos.component.ts, se agregan los Validators a los elementos del FormGroup
		  myForm: FormGroup = this.formBuilder.group({
		    nombre: ['RTX 4080', [Validators.required, Validators.minLength(4)]],
		    precio: [0, [Validators.required,Validators.min(0)]],
		    existencia: [6, [Validators.required,Validators.min(0)]]
		  })
	2.- Se modifica reactive/basicos.component.html, se deshabilita el boton si el formulario es invalido
		    <div class="row">
                <div class="col">
                    <button type="submit" class="btn btn-primary float-end" [disabled]="myForm.invalid">Guardar</button>
                </div>
            </div>
************************************************************************************************************************************
250.- ******************************************************************************************************Mostrar mensaje de error+++
	1.- Se modifica reactive/basicos.component.ts, se crea metodo de validacion
		  validacion( campo:string ){
			    return this.myForm.controls[campo].errors &&
			            this.myForm.controls[campo].touched;
			  }
	2.- Se modifica reactive/basicos.component.html, se agrega ngIf a los mensajes de error
		            <!-- Campo de nombre -->
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Producto</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="Nombre del producto" formControlName="nombre">
                    <span class="form-text text-danger" *ngIf="validacion('nombre')"> Debe tener al menos 4 letras</span>
                </div>
            </div>
            <!-- Campo de precio -->
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Precio</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="Precio del producto" formControlName="precio">
                    <span class="form-text text-danger" *ngIf="validacion('precio')"> Debe ser mayor a 0</span>   
                </div>
            </div>
            <!-- Campo de existencia -->
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Existencia</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"  placeholder="Existencia del producto" formControlName="existencia">
                    <span class="form-text text-danger" *ngIf="validacion('existencia')"> Debe ser mayor a 0</span>  
                </div>
            </div>
************************************************************************************************************************************
251.- *********************************************************************************************************submit del formulario +
	1.- Se modifica reactive/basicos.component.html, se le agrega al formuario el submit
		<form (ngSubmit)="guardar()" [formGroup]="myForm">
		1.1.- Se modifica el boton para que no se deshabilite
			<button type="submit" class="btn btn-primary float-end" >Guardar</button>
	2.- Se modifica reactive/basicos.component.ts
		2.1.- Se crea metodo guardar()
			  guardar(){
			    if (this.myForm.invalid){
			      //se marcan todos los input del formulario
			      this.myForm.markAllAsTouched();
			      return;
			    }
			    console.log(this.myForm.value);
			  }
		2.2.- Se eliminan los valores por defecto del formulario, para el ejercicio propuesto, manejar los valores por el ngOnInit
			  myForm: FormGroup = this.formBuilder.group({
			    nombre: [, [Validators.required, Validators.minLength(4)]],
			    precio: [, [Validators.required,Validators.min(0)]],
			    existencia: [, [Validators.required,Validators.min(0)]]
			  })
		2.3.- Se  modifica el metodo ngoninit()
			 ngOnInit(): void {
			    // Se inicializan los datos del formulario, deben estar todas las propiedades
			    this.myForm.setValue({
			      nombre: 'RXJ 2500',
			      precio: 1600,
			      existencia: 5
			    })
			  }
************************************************************************************************************************************
252.- **************************************************************************************************formulario reactivo dinamico +
	1.- Se modifica reactive/dinamicos.component.html
		1.1.- Se modifica el form, se le agrega FormGroup y submit
			<form [formGroup]="myForm" (ngSubmit)="guardar()">
		1.2.- Se agrga validacion al input
			<div class="col-sm-9">
	            <input type="text" class="form-control" placeholder="Nombre de la persona" formControlName="nombre">
	            <span class="form-text text-danger" *ngIf="validacion('nombre')">
	                Campo nombre es requerido.
	            </span>
	        </div> 
	     1.3.- Se modifica el boton y se coloca de tipo submit
	     	<button type="submit"  class="btn btn-primary float-end" >Guardar</button>
	2.- Se modifica reactive/dinamicos.component.ts
		2.1.- Se agrega atributo de tipo FormGroup
			  myForm: FormGroup = this.fb.group({
			    nombre: ['',[Validators.required, Validators.minLength(4)]]
			  })
		2.2.- Se crea metdo guardar()
			  guardar(){
			    if(this.myForm.invalid){
			      this.myForm.markAllAsTouched();
			      return;
			    }
			    console.log(this.myForm.value);
			  }
		2.3.- Se crea metodo de validacion
			  validacion( campo:string){
			    return this.myForm.controls[campo].errors &&
			            this.myForm.controls[campo].touched;
			  }
		2.4.- Se crea inyeccion de dependencia del FormBuilder
			constructor( private fb:FormBuilder ) { }
************************************************************************************************************************************
253.- *********************************************************************************************************************FormArray +
	1.- Se modifica el reactive/dinamicos.component.ts
		1.1.- Se crea atributo utilizando FormGroup, FormBuilder y FormArray
			  myForm: FormGroup = this.fb.group({
			    nombre: ['',[Validators.required, Validators.minLength(4)]],
			    favoritos: this.fb.array([
			      ['Metal Gear', Validators.required],
			      ['Death Strandig', Validators.required]
			    ])
			  })
		1.2.- Se crea metoddo de tipo get para convrtir el elemento favoritos en un FormArray
			  get favoritosArr(){
			    return this.myForm.get('favoritos') as FormArray;
			  }
		1.3.- Se reliza la inyeccion de dependencia de FormBuilder
			  constructor( private fb:FormBuilder ) { }
		1.4.- Se crea metodo guardar, valida si es invalido el form y marca los input como manipulados
			  guardar(){
			    if(this.myForm.invalid){
			      this.myForm.markAllAsTouched();
			      return;
			    }
			    console.log(this.myForm.value);
			  }
		1.5.- Se crea metodo para la validacion del formulario
			validacion( campo:string){
			    return this.myForm.controls[campo].errors &&
			            this.myForm.controls[campo].touched;
			  }
	2.- Se modifica reactive/dinamicos.component.html
		2.1.- Se agrega submit y formGroup al formulario
			<form [formGroup]="myForm" (ngSubm it)="guardar()">
		2.2.- Se utiliza el metodo de validacion para renderizar el mensaje de error
			    <div class="mb-3 row">
			        <label for="" class="col-sm-3 col-form-label">Nombre:</label>
			        <div class="col-sm-9">
			            <input type="text" class="form-control" placeholder="Nombre de la persona" formControlName="nombre">
			            <span class="form-text text-danger" *ngIf="validacion('nombre')">
			                Campo nombre es requerido.
			            </span>
			        </div> 
			    </div>
		2.3.- Se le agrega indice para renderizar de forma dinamica en el formControlName
			<!-- Lista -->
		    <div class="mb-3 row">
		        <label for="" class="col-sm-3 col-form-label">Favoritos:</label>
		        <div class="col-sm-9" formArrayName="favoritos">
		            <div class="input-group mb-1" *ngFor="let favorito of favoritosArr.controls, let i = index">
		                <input type="text" class="form-control"  [formControlName]="i">
		                <button class="btn btn-outline-primary" type="button" >Eliminar</button>
		            </div>            
		        </div> 
		    </div>
************************************************************************************************************************************
254.- ************************************************************************************************Agregar controles al FormArray
	1.- Se modifica reactive/dinamicos.component.ts
		1.1.- Se agrega un atributo de tipo FormControl
			nuevoFavorito: FormControl = this.fb.control('',[Validators.required])
		1.2.- Se crea metodo agregarFavorito(), valida si el control es valido y agrega un nevo formControl al formGroup
			  agregarFavorito(){
			    if(this.nuevoFavorito.invalid){ return; };
			    this.favoritosArr.push( new FormControl( this.nuevoFavorito.value, [Validators.required] ));
			    //Realizando el push  con en formbuilder
			    //this.favoritosArr.push( this.fb.control( this.nuevoFavorito.value, [Validators.required] ));
			  }
	1.- Se modifica reactive/dinamicos.component.html, se modifica input para el llamado al metodo creado y se mapea con el 
	formControl al nuevo atributo creado
		    <!-- Agregar -->
		    <div class="mb-3 row">
		        <label for="" class="col-sm-3 col-form-label">Agregar:</label>
		        <div class="col-sm-9">
		            <div class="input-group">
		                <input type="text" class="form-control" [formControl]="nuevoFavorito" (keyup.enter)="agregarFavorito()" placeholder="Agregar Favorito" >
		                <button class="btn btn-outline-primary" type="button" (click)="agregarFavorito()">Agregar</button>
		            </div>
		        </div> 
		    </div>
************************************************************************************************************************************
255.- *********************************************************************************************Eliminar elemento de un FormArray
	1.- Se modifica reactive/dinamicos.component.ts, se crea metodo de eliminarFavorito
		  eliminar(i: number){
		    this.favoritosArr.removeAt(i);
		  }
	1.- Se modifica reactive/dinamicos.component.html, se agrega llamado al metodo eliminar pasandole el indice del arreglos
		    <!-- Lista -->
		    <div class="mb-3 row">
		        <label for="" class="col-sm-3 col-form-label">Favoritos:</label>
		        <div class="col-sm-9" formArrayName="favoritos">
		            <div class="input-group mb-1" *ngFor="let favorito of favoritosArr.controls, let i = index">
		                <input type="text" class="form-control"  [formControlName]="i">
		                <button class="btn btn-outline-primary" type="button" (click)="eliminar(i)">Eliminar</button>
		            </div>            
		        </div> 
		    </div>
************************************************************************************************************************************
256.- **********************************************************************************************************************Switches
	1.- Se modifica reactive/switches.component.ts.
		1.1.- Se crea inyeccion de dependencia de formBuilder
			constructor( private fb: FormBuilder) { }
		1.2.- Se cra atributo de la clase de tipo formGroup
			  myForm: FormGroup = this.fb.group({
			    genero: ['M', [Validators.required]],
			    notificacion: [true, [Validators.required]],
			    condicion: [false, [Validators.requiredTrue]]
			  })
		1.3.- Se crea atributo de tipo persona
			  persona={
			    genero: 'F',
			    notificacion: true  
			  }
		1.4.- Se inicializan valores del myForm en el ngoninit
			  ngOnInit(): void {
			    this.myForm.reset({
			      ...this.persona, 
			      condicion: true
			    });
			  }

	1.- Se modifica reactive/switches.component.html, se agregan formControlName a cada input, 
		<h2>Reactive<small> Switches</small></h2>
		<hr>
		<form [formGroup]="myForm">
		    <!-- Radio -->
		    <div class="row mb-3">
		        <label class="col-sm-3 col-form-label">Genero</label>
		        <div class="col-sm-9">
		            <div class="form-check">
		                <input class="form-check-input" type="radio" value="M" formControlName="genero" id="radioMasculino" >
		                <label class="form-check-label" for="radioMasculino">
		                  Masculino
		                </label>
		              </div>
		              <div class="form-check">
		                <input class="form-check-input" type="radio"  formControlName="genero" id="radioFemenino" value="F" >
		                <label class="form-check-label" for="radioFemenino">
		                  Femenino
		                </label>
		              </div>
		        </div>
		    </div>

		    <!-- Switch -->
		    <div class="row mb-3">
		        <label class="col-sm-3 col-form-label">Notificaciones</label>
		        <div class="col-sm-9">
		            <div class="form-check form-switch mt-2">
		                <input class="form-check-input" type="checkbox" id="flexSwitchCheckChecked" formControlName="notificacion">
		                <label class="form-check-label" for="flexSwitchCheckChecked">Recibir Notificaciones</label>
		            </div>
		        </div>
		    </div>

		    <!-- checkBox -->
		    <div class="row mb-3">
		        <label class="col-sm-3 col-form-label"></label>
		        <div class="col-sm-9">
		            <div class="form-check">
		                <input class="form-check-input" type="checkbox" id="flexCheckDefault" formControlName="condicion">
		                <label class="form-check-label" for="flexCheckDefault">
		                  Terminos y condiciones de Uso
		                </label>
		              </div>
		        </div>
		    </div>

		    <div class="row">
		        <div class="col">
		            <button class="btn btn-primary float-end" >Guardar</button>
		        </div>
		    </div>
		</form>

		<h5>Valores del formulario</h5>
		<pre>{{myForm.value | json}}</pre>
		<hr>
		<h5>Valores de persona</h5>
		<pre>{{persona | json}}</pre>
		<hr>
		<h5>Validacion del formulario</h5>
		<pre>{{myForm.valid | json}}</pre>

		<h5>Validacion de termino</h5>
		<!-- <pre>{{ myForm.controls?.terminos?.errors | json}}</pre> -->
************************************************************************************************************************************
257.- *********************************************************************************************Actualizar el valor de la persona
	1.- Se modifica reactive/switches.component.ts, se modifica el metodo ngOnInit()
		 ngOnInit(): void {
		    this.myForm.reset({
		      ...this.persona, 
		      condicion: true
		    });

		    //evalua los cambios del formulario, borra condicion y se le asigna a persona
		    this.myForm.valueChanges.subscribe( form => {
		      delete form.condicion;
		      this.persona = form;
		      console.log(this.myForm.value);
		    })

		    //evaluando un cambio y Obteniendo un unico elemento del formulario
		    this.myForm.get('condicion')?.valueChanges.subscribe( newValue => {
		      console.log(newValue);
		    })

		    //evaluando un cambio y 9haciendo la desestructuracion
		    this.myForm.valueChanges.subscribe( ({ condicion, ...restoArgumentos})=> {
		      this.persona = restoArgumentos;
		      console.log(this.myForm.value);
		    })
		  }
************************************************************************************************************************************
258.- *****************************************************************************Documentacion de formularios reactivos en Angular
	Referencias
		https://angular.io/guide/reactive-forms
************************************************************************************************************************************
Seccion 18: Formularios: Validaciones manuales y asincronas*************************************************************************
262.- *****************************************************************************************************Continuacion del proyecto+
	1.- Se modifica sidemenu.component.html, se renderiza la opcion de Validaciones
		<h2>Validaciones</h2>
		<hr>
		<ul class="list-group">
		    <li class="list-group-item" *ngFor="let item of authMenu" [routerLink]="item.ruta" routerLinkActive="active">
		        {{item.texto}}
		    </li>
		</ul>
	2.- Se modifica sidemenu.component.ts, se crea arreglo de rutas
		 authMenu: MenuItem[] = [
		    {
		      texto: 'Registro',
		      ruta: './auth/registro'
		    },
		    {
		      texto: 'Login',
		      ruta: './auth/login'
		    }
		  ]
	3.- Se cre modulo auth con su archivo routing
		ng g m auth --routing
	4.- Se crean los componentes registro y login
		ng g c auth/pages/login --skipTests -is
		ng g c auth/pages/registro --skipTests -is
	5.- Se modifica auth-routing.module.ts, se crean las rutas hijas
		const routes: Routes = [
		  {
		    path: '',
		    children:[
		      {
		        path: 'registro', component: RegistroComponent
		      },
		      {
		        path: 'login', component: LoginComponent
		      }
		    ]
		  }
		];
	6.- Se modifica app-routing.module.ts, se realiza lazyLoad del modulo auth
		const routes: Routes = [
		  {
		    path:'template',
		    loadChildren: () => import('./template/template.module').then(m => m.TemplateModule)
		  },
		  {
		    path: 'reactive',
		    loadChildren: () => import('./reactive/reactive.module').then(m =>m.ReactiveModule)
		  },
		  {
		    path: 'auth',
		    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
		  },
		  {
		    path: '**',
		    redirectTo:'template'
		  }
		];
************************************************************************************************************************************
264.- ************************************************************************************************Diseño de pantalla de registro+
	1.- Se modifica registro.component.html, se crea el formulario
		<h2>Vlidaciones Reactivas</h2>
		<hr>
		<div class="row">
		    <div class="col">
		        <form>
		            <!-- Nombre -->
		            <div class="row mb-3">
		                <label for="" class="col-sm-3 col-form-label">Nombre</label>
		                <div class="col-sm-9">
		                    <input type="text" class="form-control" placeholder="Nombre de usuario">
		                    <span class="form-text text-danger">
		                        Debe ser en formato de nombre y apellido.
		                    </span>
		                </div>  
		                              
		            </div>

		            <!-- Email -->
		            <div class="row mb-3">
		                <label for="" class="col-sm-3 col-form-label">Email</label>
		                <div class="col-sm-9">
		                    <input type="email" class="form-control" placeholder="Email de usuario">
		                </div>             
		            </div>

		            <!-- Username -->
		            <div class="row mb-3">
		                <label for="" class="col-sm-3 col-form-label">Username</label>
		                <div class="col-sm-9">
		                    <input type="text" class="form-control" placeholder="Username de usuario">
		                </div>             
		            </div>

		            <!-- Password -->
		            <div class="row mb-3">
		                <label for="" class="col-sm-3 col-form-label">Password</label>
		                <div class="col-sm-9">
		                    <input type="password" class="form-control" placeholder="Password de usuario">
		                </div>             
		            </div>

		            <!-- Confirmar Password -->
		            <div class="row mb-3">
		                <label for="" class="col-sm-3 col-form-label">Confirmar</label>
		                <div class="col-sm-9">
		                    <input type="password" class="form-control" placeholder="Confirmacion de contraseña">
		                </div>             
		            </div>

		            <div class="row">
		                <div class="col">
		                    <button class="btn btn-primary float-end">
		                        Crear
		                    </button>
		                </div>
		            </div>

		        </form>
		    </div>
		</div>
************************************************************************************************************************************
265.- ******************************************************************************************Validar contra una expresion regular
	1.- Se modifica auth.module.ts, se importa ReactiveFormsModule
		@NgModule({
		  declarations: [LoginComponent],
		  imports: [
		    CommonModule,
		    AuthRoutingModule,
		    ReactiveFormsModule
		  ]
		})
		export class AuthModule { }
	2.- Se modifica registro.component.ts
		2.1.- Se realiza la inyeccion de dependencia del FormBuilder
			  constructor( private fb: FormBuilder ) { }
		2.2.- Se crea el formGroup
		  myForm : FormGroup = this.fb.group({
		    nombre: ['',[Validators.required , Validators.pattern( this.nombreApellidoPattern )]]
		  })
		2.3.- Se crea string de patron para la validacion
			nombreApellidoPattern: string = '([a-zA-Z]+) ([a-zA-Z]+)';
		2.4.- Se crea metodo para que sirva como bandera si un campo es valido o no
			  campoValido( campo: string){
			    return this.myForm.get(campo)?.invalid &&
			      this.myForm.get(campo)?.touched
			  }
	3.- Se modifica registro.component.html
		3.1.- Se le agrega al form el FormGroup
			<form [formGroup]="myForm">	
		3.2.- Se le agrega formControlName al input
			<input type="text" class="form-control" placeholder="Nombre de usuario" formControlName="nombre">
		3.3.- Se crean elementos para visualizar las Validaciones
			<h2>Form Valid: {{ myForm.valid }}</h2>
			<hr>
			<h2>{{ myForm.controls.nombre.errors| json}}</h2>
		3.4.- Se crea validacion para mostrar el mensaje, en el campo nombre
			<span class="form-text text-danger" *ngIf="campoValido('nombre')">
                Debe ser en formato de nombre y apellido.
            </span>
************************************************************************************************************************************
266.- **************************************************************************************************************Evaluar un email
	1.- Se modifica registro.component.ts
		1.1.- Se agrega objeto email al formGroup
			myForm : FormGroup = this.fb.group({
			    nombre: ['',[Validators.required , Validators.pattern( this.nombreApellidoPattern )]],
			    email: ['',[Validators.required , Validators.pattern( this.emailPattern )]],
			  })
		1.2.- Se modifica el metodo ngOnInit paraponer por defecto los atributos del formGroup
			ngOnInit(): void {
			    this.myForm.reset({
			      nombre: 'Elvis Areiza',
			      email: 'Dirielfran@gmail.com'
			    })
			  } 
		1.3.- Se agrega patern para el correcto
			emailPattern: string = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
	2.- Se modifica registro.component.html
		2.1.- Se le agrega formControlName al elemento correspondiente, se le agrega mensaje de validacion
            <!-- Email -->
            <div class="row mb-3">
                <label for="" class="col-sm-3 col-form-label">Email</label>
                <div class="col-sm-9">
                    <input type="email" class="form-control" placeholder="Email de usuario" formControlName="email">
                    <span class="form-text text-danger" *ngIf="campoValido('email')">
                        El correo debe tener un formato correcto.
                    </span>
                </div>             
            </div>
        2.3.- Se agrega visualizacion de validacion de email
        	<hr>
			<h2>{{ myForm.controls.email.errors| json}}</h2>
************************************************************************************************************************************
267.- ***************************************************************************************************Validaciones Personalizadas
	1.- Se modifica registro.component.ts
		1.1.- Se agrega objeto username al formGroup
			  myForm : FormGroup = this.fb.group({
			    nombre: ['',[Validators.required , Validators.pattern( this.nombreApellidoPattern )]],
			    email: ['',[Validators.required , Validators.pattern( this.emailPattern )]],
			    username: ['',[Validators.required , this.noPuedeSerStrider ]],
			  })
		1.2.- Se agrega metodo de validacion perzonalizado
			noPuedeSerStrider(control: FormControl){
			    const valor = control.value?.trim().toLowerCase();
			    if( valor === 'strider'){
			      return {
			        noStrider: true
			      }
			    }
			    return null;
			  }
	2.- Se modifica registro.component.html
		2.- Se modifica registro.component.html
			2.1.- Se le agrega formControlName al elemento correspondiente, se le agrega mensaje de validacion
	            <!-- Username -->
	            <div class="row mb-3">
	                <label for="" class="col-sm-3 col-form-label">Username</label>
	                <div class="col-sm-9">
	                    <input type="text" class="form-control" placeholder="Username de usuario" formControlName="username"> 
	                    <span class="form-text text-danger" *ngIf="campoValido('username')">
	                        El username es requerio y no puede ser strider.
	                    </span>
	                </div>             
	            </div>
	        2.3.- Se agrega visualizacion de validacion de username
	        	<hr>
				<h2>{{ myForm.controls.username.errors| json}}</h2>
************************************************************************************************************************************
268.- *******************************************************************************Separar la logic de validaciones del componente
	1.- Se mdifica registro.component.ts
		1.1.- Se cortan los patrones para llevarlos a un archivo externo, nombreApellidoPattern y emailPattern
		1.2.- Se cortan metodo noPuedeSerStrider() para llevarlos a un archivo externo
		1.3.- Se modifica myForm para que llame las validaciones del archivo externo
			  myForm : FormGroup = this.fb.group({
			    nombre: ['',[Validators.required , Validators.pattern( nombreApellidoPattern )]],
			    email: ['',[Validators.required , Validators.pattern( emailPattern )]],
			    username: ['',[Validators.required, noPuedeSerStrider ]],
			  })
	2.- Se crea folder dentro de shared --> validaciones --> validaciones.ts
		2.1.- Se agregan patrones
			export const nombreApellidoPattern: string = '([a-zA-Z]+) ([a-zA-Z]+)';
			export const emailPattern: string = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
		2.2.- Se agrega funcion noPuedeSerStrider
			export const noPuedeSerStrider = (control: FormControl) => {
			    const valor = control.value?.trim().toLowerCase();
			    if( valor === 'strider'){
			      return {
			        noStrider: true
			      }
			    }
			    return null;
			}
	3.- Se trabaja con una clase de servicio, Se crea servicio validator
		ng g s shared/validator --skipTests
		3.1.- Se modifica validator.service.tsc
			import { Injectable } from '@angular/core';
			import { FormControl, ValidationErrors } from '@angular/forms';

			@Injectable({
			  providedIn: 'root'
			})
			export class ValidatorService {

			  nombreApellidoPattern: string = '([a-zA-Z]+) ([a-zA-Z]+)';
			  emailPattern: string = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

			  constructor() { }

			  noPuedeSerStrider(control: FormControl): ValidationErrors | null {
			    const valor = control.value?.trim().toLowerCase();
			    if( valor === 'strider'){
			      return {
			        noStrider: true
			      }
			    }
			    return null;
			  }
			}
		3.2.- Se modifica registro.component.ts, se cambia para trabajar con el servicio
			3.2.1.- Se realiza injeccion de dependencia del servicio
				  constructor( private fb: FormBuilder,
              					private vS: ValidatorService ) { }
			3.2.2.- Se modifica myForm, para que busque las validaciones en el servicio
				  myForm : FormGroup = this.fb.group({
				    nombre: ['',[Validators.required , Validators.pattern( this.vS.nombreApellidoPattern )]],
				    email: ['',[Validators.required , Validators.pattern( this.vS.emailPattern )]],
				    username: ['',[Validators.required, this.vS.noPuedeSerStrider ]],
				  })+
************************************************************************************************************************************
269.- ***************************************************************************************************Validar contraseñas iguales
	1.- Se modifica validator.service.ts, se crea metodo para la validacion de campos iguales
		  camposIguales( campo1: string, campo2: string ){
		    return ( formGroup: AbstractControl): ValidationErrors | null => {
		      const pass1 = formGroup.get(campo1)?.value;
		      const pass2 = formGroup.get(campo2)?.value;

		      if (pass1 !== pass2){
		        formGroup.get(campo2)?.setErrors({ noIguales: true});
		        return { noIguales: true };
		      }

		      formGroup.get(campo2)?.setErrors(null);
		      return null;
		    }
		  }
	2.- Se modifica registro.component.ts, se añade validators al formulario
		myForm : FormGroup = this.fb.group({
		      nombre: ['',[Validators.required , Validators.pattern( this.vS.nombreApellidoPattern )]],
		      email: ['',[Validators.required , Validators.pattern( this.vS.emailPattern )]],
		      username: ['',[Validators.required, this.vS.noPuedeSerStrider ]],
		      password: ['',[Validators.required, Validators.minLength(6) ]],
		      password2: ['',[Validators.required ]],
		    },
		    {
		      validators: [ this.vS.camposIguales('password','password2')]
		    }
		  )
	3.- Se modifica registro.component.html
		2.- Se modifica registro.component.html
			2.1.- Se le agrega formControlName al elemento correspondiente, se le agrega mensaje de validacion
	             <!-- Password -->
	            <div class="row mb-3">
	                <label for="" class="col-sm-3 col-form-label">Password</label>
	                <div class="col-sm-9">
	                    <input type="password" class="form-control" placeholder="Password de usuario" formControlName="password">
	                    <span class="form-text text-danger" *ngIf="campoValido('password')">
	                        El campo debe contener minimo 6 caracteres.
	                    </span>  
	                </div>         
	            </div>

	            <!-- Confirmar Password -->
	            <div class="row mb-3">
	                <label for="" class="col-sm-3 col-form-label">Confirmar</label>
	                <div class="col-sm-9">
	                    <input type="password" class="form-control" placeholder="Confirmacion de contraseña" formControlName="password2">
	                    <span class="form-text text-danger" *ngIf="campoValido('password2')">
	                        El campo debe ser igual al password.
	                    </span> 
	                </div>           
	            </div>
	        2.3.- Se agrega visualizacion de validacion de formulario
	        	<h5>Formulario:</h5>
				<pre>{{ myForm.errors| json}}</pre>
				<hr>+
************************************************************************************************************************************
270.- **************************************************************************************Preparacion para validaciones asincronas
	1.- Se descarga el archivo db.json, se coloca en la carpeta del proyecto y desde alli se ejecuta el json-server
		json-server --watch db.json
		1.2.- Se valida --> http://localhost:3000/usuarios
************************************************************************************************************************************
271.- *******************************************************************************************************Validaciones asincronas
	1.- Se modifica app.module.ts, se importa HttpClientModule
		import { NgModule } from '@angular/core';
		import { BrowserModule } from '@angular/platform-browser';
		import { HttpClientModule } from '@angular/common/http'
		import { AppRoutingModule } from './app-routing.module';
		import { AppComponent } from './app.component';
		import { SharedModule } from './shared/shared.module';

		@NgModule({
		  declarations: [
		    AppComponent
		  ],
		  imports: [
		    BrowserModule,
		    AppRoutingModule,
		    HttpClientModule,
		    SharedModule
		  ],
		  providers: [],
		  bootstrap: [AppComponent]
		})
		export class AppModule { }
	2.- Se crea servicio emailValidator
		ng g s shared/validadores/emailValidator --skipTests
		2.1.- Se crea la injeccion de dependencia de HttpClient
			constructor( private http: HttpClient) { }
		2.2.- Se implementa en la clase de servicio --> AsyncValidator
			export class EmailValidatorService implements AsyncValidator{...}
		2.3.- Se sobreescribe el metodo validate()
			validate(control: AbstractControl):  Observable<ValidationErrors | null> {
				//Se obtiene el valor de control
			    const email = control.value;
			    console.log(email);
			    return this.http.get<any[]>(`http://localhost:3000/usuarios?q=${email}`).pipe(
			    	//Se convierte la respuest en un formato de validacion
			      map( response => {
			        return ( response.length === 0 ) ? null : { emailTomado: true }
			      })
			    )
			  }
	3.- Se modifica registro.component.ts
		3.1.- Se crea la injeccion de dependencia EmailValidatorService
			  constructor( private fb: FormBuilder,
              private vS: ValidatorService,
              private emailValidator: EmailValidatorService) { }
        3.2.- Se modifica myForm, el atributo email se le añade validacion asincronas
        	myForm : FormGroup = this.fb.group({
			      nombre: ['',[Validators.required , Validators.pattern( this.vS.nombreApellidoPattern )]],
			      email: ['',[Validators.required , Validators.pattern( this.vS.emailPattern )], [ this.emailValidator ]],
			      username: ['',[Validators.required, this.vS.noPuedeSerStrider ]],
			      password: ['',[Validators.required, Validators.minLength(6) ]],
			      password2: ['',[Validators.required ]],
			    },
			    {
			      validators: [ this.vS.camposIguales('password','password2')]
			    }
			  )
************************************************************************************************************************************
272.- *********************************************************************************************************Estado del formulario
	1.- Se modifica email-validator.service.ts, se modifica metodo validate() con el operador delay de crea un delay para probar
		validate(control: AbstractControl):  Observable<ValidationErrors | null> {
		    const email = control.value;
		    console.log(email);
		    return this.http.get<any[]>(`http://localhost:3000/usuarios?q=${email}`).pipe(
		      delay(3000),
		      map( response => {
		        return ( response.length === 0 ) ? null : { emailTomado: true }
		      })
		    )
		  }
	2.- Se modifica registro.component.ts, se modfica metod ngOnInit() se le colocan por default valores a todos los input
		ngOnInit(): void {
		    this.myForm.reset({
		      nombre: 'Elvis Areiza',
		      email: 'test@test.com',
		      username: 'eareiza',
		      password: 123456,
		      password2: 123456
		    })
		  }
	3.- Se modifica registro.component.html, se deshabilita boton hasta que tenga estatus valido
		3.1.- Se modifica elemento boton para deshabilitarlo segun validacion
			<div class="row">
                <div class="col">
                    <button class="btn btn-primary float-end" [disabled]="myForm.status !== 'VALID'">
                        Crear
                    </button>
                </div>
            </div>
        3.2.- Se visualizan el estatus y el pending del formulario
        	<h5>Form Estatus: {{ myForm.status }}</h5>
			<h5>Form pendiente: {{ myForm.pending }}</h5>+
************************************************************************************************************************************
274.- ********************************************************************************************************Errores perzanalizados
	1.- Se modifica registro.component.ts, se crean metodo de validacion
		  //Validacion si el campo es requerido
		  emailRequerido( ){
		    return this.myForm.get('email')?.errors?.required &&
		      this.myForm.get('email')?.touched
		  }

		  //Validacion si el campo tiene el formato correcto
		  emailFormato( ){
		    return this.myForm.get('email')?.errors?.pattern &&
		      this.myForm.get('email')?.touched
		  }

		  //Validacion si el campo es requerido
		  emailTomado( ){
		    return this.myForm.get('email')?.errors?.emailTomado &&
		      this.myForm.get('email')?.touched
		  }
	2.- Se modifica registro.component.html, se validan los diferentes mensajes de error de email
		<!-- Email -->
            <div class="row mb-3">
                <label for="" class="col-sm-3 col-form-label">Email</label>
                <div class="col-sm-9">
                    <input type="email" class="form-control" placeholder="Email de usuario" formControlName="email">
                    <span class="form-text text-danger" *ngIf="emailRequerido()">
                        El correo es requerido.
                    </span>
                    <span class="form-text text-danger" *ngIf="emailFormato()">
                        El correo no posee el formato correcto.
                    </span>
                    <span class="form-text text-danger" *ngIf="emailTomado()">
                        El correo ya existe.
                    </span>
                </div>             
            </div>+
************************************************************************************************************************************
275.- ************************************************************************************************Mensaje de error personalizado
	1.- Se modifica registro.component.ts
		1.1.- Se borran metodos creados en el paso anterior para la validacion de email
		1.2.- Se crea getter para mostrar el mensaje de error en la vista
			  get emailErrorMsg(): string {
			    const errors = this.myForm.get('email')?.errors;
			    if( errors?.required ){
			      return 'Correo es requerido.';
			    }else if( errors?.pattern ){
			      return 'El correo no tiene el formato correcto.';
			    }else if ( errors?.emailTomado){
			      return 'El correo ya existe';
			    }
			    return '';
			  }
	2.- Se modifica registro.component.html, se modifica el mensaje a mostrar de email
		<!-- Email -->
            <div class="row mb-3">
                <label for="" class="col-sm-3 col-form-label">Email</label>
                <div class="col-sm-9">
                    <input type="email" class="form-control" placeholder="Email de usuario" formControlName="email">
                    <span class="form-text text-danger" *ngIf="campoValido('email')">
                        {{ emailErrorMsg }}
                    </span>
                </div>             
            </div>+
************************************************************************************************************************************
************************************************************************************************************************************
Seccion 19: Formularios Reactivos . Multiples Select Anidados***********************************************************************
279.- ***********************************************************************************************Inicio del proyecto -selectores
	1.- Se crea proyecto selectores
	ng new selectores
************************************************************************************************************************************
280.- *****************************************************************************************************Estructura de directorios
	1.- Se crean directorios
		paises 	--> interfaces
				--> pages
				--> servicios
	2.- Se crea modulo paises 
		ng g m paises --routing
	3.- Se crea componente
		ng g c paises/pages/selectorPages --skipTests -is
	4.- Se crea servicio
		ng g s paises/servicios/paises 
	5.- Se modifica paises-routing.module.ts, se crean las rutas hijas
		const routes: Routes = [{
			  path:'',
			  children: [
			    { path: 'selector', component: SelectorPagesComponent },
			    { path: '**', redirectTo: 'selector'}
			  ]
			}];
	5.- Se modifica app-routing.module.ts, se crean las rutas principales y el lazyLoad
		const routes: Routes = [
		  {
		    path: 'paises',
		    loadChildren: () => import('./paises/paises.module').then(m => m.PaisesModule)
		  }
		];
	6.- Se modifica app.component.html, se renderiza elelmento router-outlet
		<router-outlet></router-outlet>
************************************************************************************************************************************
281.- ******************************************************************************************Formularios reactivos- Primer select
	1.- se modifica app.module.ts, se importa el ReactiveFormsModule
		@NgModule({
		  declarations: [
		    AppComponent,
		    SelectorPagesComponent
		  ],
		  imports: [
		    BrowserModule,
		    ReactiveFormsModule,
		    AppRoutingModule
		  ],
		  providers: [],
		  bootstrap: [AppComponent]
		})
		export class AppModule { }
	2.- Se modifica selector-pages.component.ts
		2.1.- Se crea inyeccion de dependencia de FormBuilder
			constructor( private fb:FormBuilder ) { }
		2.2.- Se crea FormGroup
			  myForm: FormGroup = this.fb.group({
			    region: ['', Validators.required ]
			  })
		2.3.- Se crea metodo guardar() para el submit
			  guardar(): void{
			    console.log(this.myForm.value);
			  }
	3.- Se modifica selector-page.component.ts
		<h1 class="mt-5">Selectores anidados</h1>
		<hr>
		<form [formGroup]="myForm" (ngSubmit)="guardar()">
		    <div class="row mb-3">
		        <div class="col">
		            <label class="form-label">Continente:</label>
		            <select class="form-control" formControlName="region">
		                <option value=""> -- Seleccione Continente --</option>
		                <option value="1">America</option>
		            </select>
		        </div>
		    </div>
		    <div class="row">
		        <div class="col">
		            <button class="btn btn-primary float-end" type="submit">
		                Guardar
		            </button>
		        </div>
		    </div>
		</form>

		<h3>Formulario:</h3>
		<pre>Valid:{{myForm.valid}}</pre>
		<pre>{{myForm.value | json}}</pre>
************************************************************************************************************************************
282.- *************************************************************************************************************Selectro regiones
	1.- Se modifica paises.service.ts, se crea atributo privado con su get
		  private _regiones: string[]=['Africa', 'America','Asia', 'Europa','Oceania'];

		  get regiones(): string[]{
		    //Se desestructura para crear una copia con ...
		    return [ ...this._regiones ];
		  }

	2.- Se modifica selector-page.component.ts
		2.1.- Se crea injeccion de dependencia del servicio
			constructor( private fb:FormBuilder,
                private paisesServices: PaisesService ) { }
		2.2.- Se crea atributo regiones 
			//Se lllenan los select
  			regiones: string[]=[];
  		2.3.- Se modifica metodo ngOnInit() para que busque la data
  			  ngOnInit(): void {
			    this.regiones = this.paisesServices.regiones;
			  }
	3.- Se modifica selector-page.component.html, se modifica select de regiones para recorra el array regionSeleccionada
		<select class="form-control" formControlName="region">
            <option value=""> -- Seleccione Continente --</option>
            <option [value]="region | lowercase" *ngFor="let region of regiones">{{region}}</option>
        </select>
************************************************************************************************************************************
283.- ******************************************************************************************************Segundo selector anidado
************************************************************************************************************************valueChanges
	1.- Se odifica pp.module.ts, se importa HttpClientModule
		@NgModule({
		  declarations: [
		    AppComponent,
		    SelectorPagesComponent
		  ],
		  imports: [
		    BrowserModule,
		    ReactiveFormsModule,
		    HttpClientModule,
		    AppRoutingModule
		  ],
		  providers: [],
		  bootstrap: [AppComponent]
		})
		export class AppModule { }	
	2.- Se modifica paises.service.ts
		2.1.- Se crea tributo urlEndPoint
			private urlEndPoint: string = "https://restcountries.eu/rest/v2/";
		2.2.- Se crea meto que consume servicio para obtener paisService
			  getPaisesXRegion( region: string): Observable<PaisSmall[]>{
			    return this.http.get<PaisSmall[]>(`${this.urlEndPoint}region/${region}?fields=alpha3Code;name`);
			  }
	3.- Se modifica selector-page.component.ts
		3.1.- Se crea atributo para paises 
			 paises: PaisSmall[] = [];
		3.2.- Se modifica metodo ngoninit(), para que se subscriba al servicio que obtiene paisService con el evento valueChanges
			ngOnInit(): void {
			    this.regiones = this.paisesServices.regiones;
			    this.myForm.get('region')?.valueChanges.subscribe( region =>{
			      console.log(region);
			      this.paisesServices.getPaisesXRegion(region).subscribe(resp =>{
			        this.paises = resp as PaisSmall[];
			      })
			    })
			  }
		3.3.- Se modifica myForm, se agrega elemento select
			  myForm: FormGroup = this.fb.group({
			    region: ['', Validators.required ],
			    paises: ['', Validators.required ]
			  })
	4.- Se modifica selector-page.component.html, se crea elemento select para paises 
		<!-- Pais -->
	    <div class="row mb-3">
	        <div class="col">
	            <label class="form-label">Paises:</label>
	            <select class="form-control" formControlName="paises">
	                <option value=""> -- Seleccione Pais --</option>
	                <option [value]="pais.alpha3Code" *ngFor="let pais of paises">{{pais.name}}</option>
	            </select>
	        </div>
	    </div>+
************************************************************************************************************************************
284.- *********************************************************************************Limpiar pais cuando el primer selector cambia
************************************************************************************************************************Operador Tap
******************************************************************************************************************Operador switchMap
	1.- Se modifica selector-page.component.ts, se modifica metodo ngoninit para utilizar operadors
		ngOnInit(): void {
		    this.regiones = this.paisesServices.regiones;

		    this.myForm.get('region')?.valueChanges.pipe( 
		      //Se utiliza el operador tap para retear elemento de myForm
		      tap( ( _ ) => this.myForm.get('paises')?.reset('') ),
		      // Se utiliza operador switchMap para llamar al servicio
		      switchMap( region => this.paisesServices.getPaisesXRegion(region) )
		    ).subscribe( paises => {
		      this.paises = paises;
		    })
		  }+
************************************************************************************************************************************
285.- *********************************************************************************************Tercer selector anidado Fronteras
************************************************************************************************************************valueChanges
*************************************************************************************************operador of() para crear observable
	1.- Se modifica interface paises.interface.ts
		1.1.- Se convierte json en obj typescript
			1.1.- Se consulta la api por postman
				https://restcountries.eu/rest/v2/alpha/col
			1.2.- Se utiliza quicktype pagina para convertir de json a typescript
				https://app.quicktype.io/
			1.3.- Se copia typescript en paises.interfaces.ts
				// To parse this data:
				//
				//   import { Convert, Pais } from "./file";
				//
				//   const pais = Convert.toPais(json);
				//
				// These functions will throw an error if the JSON doesn't
				// match the expected interface, even if the JSON is valid.

				export interface Pais {
				    name:           string;
				    topLevelDomain: string[];
				    alpha2Code:     string;
				    alpha3Code:     string;
				    callingCodes:   string[];
				    capital:        string;
				    altSpellings:   string[];
				    region:         string;
				    subregion:      string;
				    population:     number;
				    latlng:         number[];
				    demonym:        string;
				    area:           number;
				    gini:           number;
				    timezones:      string[];
				    borders:        string[];
				    nativeName:     string;
				    numericCode:    string;
				    currencies:     Currency[];
				    languages:      Language[];
				    translations:   Translations;
				    flag:           string;
				    regionalBlocs:  RegionalBloc[];
				    cioc:           string;
				}

				export interface Currency {
				    code:   string;
				    name:   string;
				    symbol: string;
				}

				export interface Language {
				    iso639_1:   string;
				    iso639_2:   string;
				    name:       string;
				    nativeName: string;
				}

				export interface RegionalBloc {
				    acronym:       string;
				    name:          string;
				    otherAcronyms: string[];
				    otherNames:    string[];
				}

				export interface Translations {
				    de: string;
				    es: string;
				    fr: string;
				    ja: string;
				    it: string;
				    br: string;
				    pt: string;
				    nl: string;
				    hr: string;
				    fa: string;
				}

				// Converts JSON strings to/from your types
				// and asserts the results of JSON.parse at runtime
				export class Convert {
				    public static toPais(json: string): Pais {
				        return cast(JSON.parse(json), r("Pais"));
				    }

				    public static paisToJson(value: Pais): string {
				        return JSON.stringify(uncast(value, r("Pais")), null, 2);
				    }
				}

				function invalidValue(typ: any, val: any, key: any = ''): never {
				    if (key) {
				        throw Error(`Invalid value for key "${key}". Expected type ${JSON.stringify(typ)} but got ${JSON.stringify(val)}`);
				    }
				    throw Error(`Invalid value ${JSON.stringify(val)} for type ${JSON.stringify(typ)}`, );
				}

				function jsonToJSProps(typ: any): any {
				    if (typ.jsonToJS === undefined) {
				        const map: any = {};
				        typ.props.forEach((p: any) => map[p.json] = { key: p.js, typ: p.typ });
				        typ.jsonToJS = map;
				    }
				    return typ.jsonToJS;
				}

				function jsToJSONProps(typ: any): any {
				    if (typ.jsToJSON === undefined) {
				        const map: any = {};
				        typ.props.forEach((p: any) => map[p.js] = { key: p.json, typ: p.typ });
				        typ.jsToJSON = map;
				    }
				    return typ.jsToJSON;
				}

				function transform(val: any, typ: any, getProps: any, key: any = ''): any {
				    function transformPrimitive(typ: string, val: any): any {
				        if (typeof typ === typeof val) return val;
				        return invalidValue(typ, val, key);
				    }

				    function transformUnion(typs: any[], val: any): any {
				        // val must validate against one typ in typs
				        const l = typs.length;
				        for (let i = 0; i < l; i++) {
				            const typ = typs[i];
				            try {
				                return transform(val, typ, getProps);
				            } catch (_) {}
				        }
				        return invalidValue(typs, val);
				    }

				    function transformEnum(cases: string[], val: any): any {
				        if (cases.indexOf(val) !== -1) return val;
				        return invalidValue(cases, val);
				    }

				    function transformArray(typ: any, val: any): any {
				        // val must be an array with no invalid elements
				        if (!Array.isArray(val)) return invalidValue("array", val);
				        return val.map(el => transform(el, typ, getProps));
				    }

				    function transformDate(val: any): any {
				        if (val === null) {
				            return null;
				        }
				        const d = new Date(val);
				        if (isNaN(d.valueOf())) {
				            return invalidValue("Date", val);
				        }
				        return d;
				    }

				    function transformObject(props: { [k: string]: any }, additional: any, val: any): any {
				        if (val === null || typeof val !== "object" || Array.isArray(val)) {
				            return invalidValue("object", val);
				        }
				        const result: any = {};
				        Object.getOwnPropertyNames(props).forEach(key => {
				            const prop = props[key];
				            const v = Object.prototype.hasOwnProperty.call(val, key) ? val[key] : undefined;
				            result[prop.key] = transform(v, prop.typ, getProps, prop.key);
				        });
				        Object.getOwnPropertyNames(val).forEach(key => {
				            if (!Object.prototype.hasOwnProperty.call(props, key)) {
				                result[key] = transform(val[key], additional, getProps, key);
				            }
				        });
				        return result;
				    }

				    if (typ === "any") return val;
				    if (typ === null) {
				        if (val === null) return val;
				        return invalidValue(typ, val);
				    }
				    if (typ === false) return invalidValue(typ, val);
				    while (typeof typ === "object" && typ.ref !== undefined) {
				        typ = typeMap[typ.ref];
				    }
				    if (Array.isArray(typ)) return transformEnum(typ, val);
				    if (typeof typ === "object") {
				        return typ.hasOwnProperty("unionMembers") ? transformUnion(typ.unionMembers, val)
				            : typ.hasOwnProperty("arrayItems")    ? transformArray(typ.arrayItems, val)
				            : typ.hasOwnProperty("props")         ? transformObject(getProps(typ), typ.additional, val)
				            : invalidValue(typ, val);
				    }
				    // Numbers can be parsed by Date but shouldn't be.
				    if (typ === Date && typeof val !== "number") return transformDate(val);
				    return transformPrimitive(typ, val);
				}

				function cast<T>(val: any, typ: any): T {
				    return transform(val, typ, jsonToJSProps);
				}

				function uncast<T>(val: T, typ: any): any {
				    return transform(val, typ, jsToJSONProps);
				}

				function a(typ: any) {
				    return { arrayItems: typ };
				}

				function u(...typs: any[]) {
				    return { unionMembers: typs };
				}

				function o(props: any[], additional: any) {
				    return { props, additional };
				}

				function m(additional: any) {
				    return { props: [], additional };
				}

				function r(name: string) {
				    return { ref: name };
				}

				const typeMap: any = {
				    "Pais": o([
				        { json: "name", js: "name", typ: "" },
				        { json: "topLevelDomain", js: "topLevelDomain", typ: a("") },
				        { json: "alpha2Code", js: "alpha2Code", typ: "" },
				        { json: "alpha3Code", js: "alpha3Code", typ: "" },
				        { json: "callingCodes", js: "callingCodes", typ: a("") },
				        { json: "capital", js: "capital", typ: "" },
				        { json: "altSpellings", js: "altSpellings", typ: a("") },
				        { json: "region", js: "region", typ: "" },
				        { json: "subregion", js: "subregion", typ: "" },
				        { json: "population", js: "population", typ: 0 },
				        { json: "latlng", js: "latlng", typ: a(0) },
				        { json: "demonym", js: "demonym", typ: "" },
				        { json: "area", js: "area", typ: 0 },
				        { json: "gini", js: "gini", typ: 3.14 },
				        { json: "timezones", js: "timezones", typ: a("") },
				        { json: "borders", js: "borders", typ: a("") },
				        { json: "nativeName", js: "nativeName", typ: "" },
				        { json: "numericCode", js: "numericCode", typ: "" },
				        { json: "currencies", js: "currencies", typ: a(r("Currency")) },
				        { json: "languages", js: "languages", typ: a(r("Language")) },
				        { json: "translations", js: "translations", typ: r("Translations") },
				        { json: "flag", js: "flag", typ: "" },
				        { json: "regionalBlocs", js: "regionalBlocs", typ: a(r("RegionalBloc")) },
				        { json: "cioc", js: "cioc", typ: "" },
				    ], false),
				    "Currency": o([
				        { json: "code", js: "code", typ: "" },
				        { json: "name", js: "name", typ: "" },
				        { json: "symbol", js: "symbol", typ: "" },
				    ], false),
				    "Language": o([
				        { json: "iso639_1", js: "iso639_1", typ: "" },
				        { json: "iso639_2", js: "iso639_2", typ: "" },
				        { json: "name", js: "name", typ: "" },
				        { json: "nativeName", js: "nativeName", typ: "" },
				    ], false),
				    "RegionalBloc": o([
				        { json: "acronym", js: "acronym", typ: "" },
				        { json: "name", js: "name", typ: "" },
				        { json: "otherAcronyms", js: "otherAcronyms", typ: a("") },
				        { json: "otherNames", js: "otherNames", typ: a("") },
				    ], false),
				    "Translations": o([
				        { json: "de", js: "de", typ: "" },
				        { json: "es", js: "es", typ: "" },
				        { json: "fr", js: "fr", typ: "" },
				        { json: "ja", js: "ja", typ: "" },
				        { json: "it", js: "it", typ: "" },
				        { json: "br", js: "br", typ: "" },
				        { json: "pt", js: "pt", typ: "" },
				        { json: "nl", js: "nl", typ: "" },
				        { json: "hr", js: "hr", typ: "" },
				        { json: "fa", js: "fa", typ: "" },
				    ], false),
				};
	2.- Se modifica paises.service.ts, se crea metodo que consume api para data pais por codigo
		  getPaisPorCodigo(cod: string): Observable<Pais | null>{
		    //Se valida si no viene codigo pais
		    if(!cod){ return of(null) }
		    return this.http.get<Pais>(`${this.urlEndPoint}alpha/${cod}`);
		  }
	3.- Se modifica selector-page.component.html, se crea elementos para Fronteras
	    <!-- Frontera -->
	    <div class="row mb-3">
	        <div class="col">
	            <label class="form-label">Frontera:</label>
	            <select class="form-control" formControlName="frontera">
	                <option value=""> -- Seleccione Frontera --</option>
	                <option [value]="pais.alpha3Code" *ngFor="let pais of paises">{{pais.name}}</option>
	            </select>
	        </div>
	    </div>
	4.- Se modifica selector-page.component.ts
		4.1.- Se modifica myForm, se crea elemento frontera
			  myForm: FormGroup = this.fb.group({
			    region    : ['', Validators.required ],
			    paises    : ['', Validators.required ],
			    frontera  : ['', Validators.required]
			  })
		4.2.- Se modifica ngoninit, se agrega valueChanges al elemento paises y se imprime por consola
			ngOnInit(): void {
			    this.regiones = this.paisesServices.regiones;

			    this.myForm.get('region')?.valueChanges.pipe( 
			      //Se utiliza el operador tap para retear elemento de myForm
			      tap( ( _ ) => this.myForm.get('paises')?.reset('') ),
			      // Se utiliza operador switchMap para llamar al servicio
			      switchMap( region => this.paisesServices.getPaisesXRegion(region) )
			    ).subscribe( paises => {
			      this.paises = paises;
			    })

			    this.myForm.get('paises')?.valueChanges.subscribe( codigo => {console.log(codigo);})
			  }
************************************************************************************************************************************
286.- ***********************************************************************************************LLenar tercer selector frontera
	1.- Se modifica selector-page.component.ts
		1.1.- Se crea atributo para Fronteras
			fronteras   : string[]= [];
		1.2.- Se modifica metodo ngoninit(), para que se llame al servicio de buscar fronteras al cambiar selector pais
			  ngOnInit(): void {
			    this.regiones = this.paisesServices.regiones;

			    //LLena selector pais
			    this.myForm.get('region')?.valueChanges.pipe( 
			      //Se utiliza el operador tap para retear elemento de myForm
			      tap( ( _ ) => this.myForm.get('paises')?.reset('') ),
			      // Se utiliza operador switchMap para llamar al servicio
			      switchMap( region => this.paisesServices.getPaisesXRegion(region) )
			    ).subscribe( paises => {
			      this.paises = paises;
			    })

			    //LLena selector frontera
			    this.myForm.get('paises')?.valueChanges.pipe(
			      tap((_) => this.myForm.get('frontera')?.reset('')),
			      switchMap( codigo => this.paisesServices.getPaisPorCodigo(codigo) )
			    ).subscribe( pais => { this.fronteras = pais?.borders || []; })
			  }
	2.- Se modifica selector-page.component.html, se renderiza lista de fronteras por medio del option
		<!-- Frontera -->
	    <div class="row mb-3">
	        <div class="col">
	            <label class="form-label">Frontera:</label>
	            <select class="form-control" formControlName="frontera">
	                <option value=""> -- Seleccione Frontera --</option>
	                <option [value]="frontera" *ngFor="let frontera of fronteras">{{frontera}}</option>
	            </select>
	        </div>
	    </div>
************************************************************************************************************************************
287.- *********************************************************************************************Mejorra la experiencia de usuario
	1.- Se modifica selector-page.component.html, se coloca validacion *ngIf a componente paises y frontera para que se muestren 
	casa de que esten llenos unicamente
		<!-- Pais -->
	    <div class="row mb-3" *ngIf="paises.length > 0">
	        <div class="col">
	            <label class="form-label">Paises:</label>
	            <select class="form-control" formControlName="paises">
	                <option value=""> -- Seleccione Pais --</option>
	                <option [value]="pais.alpha3Code" *ngFor="let pais of paises">{{pais.name}}</option>
	            </select>
	        </div>
	    </div>
	    <!-- Frontera -->
	    <div class="row mb-3" *ngIf="fronteras.length > 0">
	        <div class="col">
	            <label class="form-label">Frontera:</label>
	            <select class="form-control" formControlName="frontera">
	                <option value=""> -- Seleccione Frontera --</option>
	                <option [value]="frontera" *ngFor="let frontera of fronteras">{{frontera}}</option>
	            </select>
	        </div>
	    </div>
************************************************************************************************************************************
288.- ****************************************************************************Cambiar codigo de las fronteras por nombre de pais
***********************************************************************************************************************combineLatest
	1.- Se modifica paises.service.ts
		1.1.- Se crea servicio para buscar nobre y codigo de pais
			  //Se busca nombre y codigo de pais segun el codigo del pais
			  getPaisPorCodigoSmall(cod: string): Observable<PaisSmall>{
			    return this.http.get<Pais>(`${this.urlEndPoint}alpha/${cod}?fields=alpha3Code;name`);
			  }
		1.2.- Se crea metodo que realiza varias peticiones segun arreglo de observables
			//metodo que genera varias peticiones segun arreglo borders
			  getPaisesPorCodigo( borders: string[] ): Observable<PaisSmall[]>{   
			    if(!borders){ return of([]) };    
			    const peticiones: Observable<PaisSmall>[] = [];    
			    borders.forEach( codigo => {
			      const peticion = this.getPaisPorCodigoSmall( codigo );
			      peticiones.push( peticion );
			    });
			    return combineLatest( peticiones );
			  }
	2.- Se modifica selector-page.component.ts
		2.1.- Se cambia tipo a atributo fronteras para que sea de tipo PaisSmall[]
  			fronteras   : PaisSmall[]= [];
  		2.2.- Se modifica metodo ngOnInit(), se le agrega switchMap que realiza  las peticiones de las fronteras, retorna arreglo
  		de PaisSmall[]
  			 ngOnInit(): void {
			    this.regiones = this.paisesServices.regiones;

			    //LLena selector pais
			    this.myForm.get('region')?.valueChanges.pipe( 
			      //Se utiliza el operador tap para retear elemento de myForm
			      tap( ( _ ) => {
			        this.myForm.get('paises')?.reset('') ;
			      }),
			      // Se utiliza operador switchMap para llamar al servicio
			      switchMap( region => this.paisesServices.getPaisesXRegion(region) )
			    ).subscribe( paises => {
			      this.paises = paises;
			    })

			    //LLena selector frontera
			    this.myForm.get('paises')?.valueChanges.pipe(
			      tap((_) => {
			        this.myForm.get('frontera')?.reset('');
			      }),
			      //Retorna pais segun codigo
			      switchMap( codigo => this.paisesServices.getPaisPorCodigo(codigo) ),
			      //Retorna PaisSmall segun fronteras de pais
			      switchMap( pais => this.paisesServices.getPaisesPorCodigo( pais?.borders! ) )
			    ).subscribe( paises => { 
			      this.fronteras = paises;
			    })
			  }
************************************************************************************************************************************
************************************************************************************************************************************
Seccion 20: LifeCicle Hooks*********************************************************************************************************
292.- *************************************************************************************************Inicio del proyecto lifeCicle
	Referencia --> https://angular.io/guide/lifecycle-hooks
	1.- Se crea proyecto lifeCicle
		ng new lifeCicle
	2.- Se crean componentes 
		ng g c pages/pagina1 --skipTests -is
		ng g c components/muestraNombre --skipTests -is
************************************************************************************************************************************
293.- *********************************************************************************Implementar todos los hooks del ciclo de vida
	1.- Se modifica app.component.html, se crea acceso al componente app-pagina1
		<h1>Hooks del ciclo de vida</h1>
		<hr>
		<app-pagina1></app-pagina1>
	2.- Se modifica pagina.component.ts
		2.1.- Se implementan hooks en la clase
			export class Pagina1Component implements OnInit, OnChanges, DoCheck, AfterContentInit, 
              AfterContentChecked, AfterViewInit, AfterViewChecked, OnDestroy{...}
        2.2.- Se implementan los metodos
        	2.2.1.- Sobre el nombre de la clase se ejecuta ctrl+. y se implementan todos los metodos, se modifica contenido de cada 
        	metodo
        		ngOnChanges(changes: SimpleChanges): void {
				    console.log('OnChanges')
				  }

				  ngDoCheck(): void {
				   console.log('DoCheck');
				  }

				  ngAfterContentInit(): void {
				   console.log('AfterContentInit');
				  }

				  ngAfterContentChecked(): void {
				   console.log('AfterContentChecked');
				  }

				  ngAfterViewInit(): void {
				   console.log('AfterViewInit');
				  }

				  ngAfterViewChecked(): void {
				   console.log('AfterViewChecked');
				  }

				  ngOnDestroy(): void {
				   console.log('OnDestroy');
				  }
************************************************************************************************************************************
294.- ******************************************************************************************Explicacion sobre los ciclos de vida
	1.- Se modifica app.module.ts, se importa FormsModule
		@NgModule({
		  declarations: [
		    AppComponent,
		    Pagina1Component,
		    MuestraNombreComponent
		  ],
		  imports: [
		    BrowserModule,
		    FormsModule
		  ],
		  providers: [],
		  bootstrap: [AppComponent]
		})
		export class AppModule { }
	2.- Se modifica pagina1.component.ts
		1.1.- Se crea atributo nombre, 
			nombre: string = 'Elvis';
		1.2.- Se crea metodo guardar(), se crea metodo para que veamos los metodos que se activan en la deteccion de cambios.
			  guardar(){}
	3.- Se modifica pagina1.component.html
		<p>pagina1 works!</p>
		<input type="text" placeholder="Hello" [(ngModel)]="nombre">

		<button (click)="guardar()">Guadar</button>
	4.- Se modifica app.components.ts, se crea atributo
		mostrar: boolean = true;
	5.- Se modifica app.component.html, se crea validacion al component app-pagina1 para ver los metodos que se aplican al crea
	y destruir el componente
		<h1>Hooks del ciclo de vida</h1>
		<hr>
		<button (click)="mostrar = !mostrar">{{ !mostrar ? 'mostrar' : 'ocultar'}}</button>
		<app-pagina1 *ngIf="mostrar"></app-pagina1>
************************************************************************************************************************************
295.- *******************************************************************************************************************ngOnChanges
	Se llama antes ngOnInit() y siempre que cambien una o más propiedades de entrada enlazadas a datos.
	Tenga en cuenta que si su componente no tiene @Input() o lo usa sin proporcionar ninguna entrada, el marco no llamará 
	ngOnChanges().
	1.- Se modifica muestra-nombre.component.ts
		1.1.- Se crea atributo @Input, para ver el cambio de este en el metodo ngOnChanges()
			@Input() nombre!: string;
		1.2.- Se implementa en la clase el OnChanges
			export class MuestraNombreComponent implements OnInit, OnChanges {...}
		1.3.- Se sobreescribe el metodo ngOnChanges()
			ngOnChanges(changes: SimpleChanges): void {
			    console.log(changes);
			  }
	2.- Se modifica pagina1.component.html, se crea  componente app-muestra-nombre con un atributo nombre
		<app-muestra-nombre [nombre]="nombre"></app-muestra-nombre>
************************************************************************************************************************************
296.- *******************************************************************************************************************ngOnDestroy
***************************************************************************************************************funcion interval rxjs
	ngOnDestroy Se llama inmediatamente antes de que Angular destruya la directiva o el componente.
	1.- Se modifica pagina1.components.ts
		1.1.- Se crea atributo de Subscribe de rxjs
			timeSubcription!: Subscription;
		1.2.- Se modifica ngOnInit(), se asigna el observable de interval al atributo creado
			ngOnInit(): void {
			    console.log('ngOnInit')
			    //funcion que se eecuta segun intervalo de tiempo y se envuelve en un obj subscripcion de rxjs
			    this.timeSubcription = interval(1000).subscribe( tiempo =>{
			      this.segundos = tiempo;
			      console.log(tiempo);
			    })
			}
		1.3.- Se modifica el metodo ngOnDestroy(), se para la subcripcion del observable por medio del atributo timeSubcription
			  ngOnDestroy(): void {
			   console.log('OnDestroy');
			   //Se para la subscripcion
			   this.timeSubcription.unsubscribe();
			  }
************************************************************************************************************************************
************************************************************************************************************************************















Seccion 22: Grafica es Angular******************************************************************************************************+++
326.- *************************************************************************************************Incio de proyecto graficasApp
	1.- Se crea proyecto --> ng new graficasApp
	2.- Referencias
		https://valor-software.com/ng2-charts/
	3.- Se modifica index.html, se le agrega css de bootstrap y js
		<head>
		  <meta charset="utf-8">
		  <title>GraficasApp</title>
		  <base href="/">
		  <meta name="viewport" content="width=device-width, initial-scale=1">
		  <link rel="icon" type="image/x-icon" href="favicon.ico">
		  <!-- CSS only -->
		  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
		</head>
		<body>
		  <app-root></app-root>
		  <!-- JavaScript Bundle with Popper -->
		  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
		</body>
************************************************************************************************************************************
327.- *******************************************************************************************************Estructura del proyecto
	1.- Se crea forlder dentro de app --> graficas
	2.- Se crea modulo --> ng g m graficas --routing
	3.- Se crean folder en folder graficas
		servicios
		paginas
		componentes
	4.- Se generan componentes
		ng g c graficas/pages/barras --skipTests
		ng g c graficas/pages/barrasDoble --skipTests
		ng g c graficas/pages/dona --skip-tests
		ng g c graficas/pages/donaHttp --skip-tests
		ng g c graficas/componentes/graficaBarra --skip-tests
		ng g c shared/menu --skip-tests
	5.- Se crea servicio
		ng g s graficas/servicios/graficas --skip-tests
************************************************************************************************************************************
328.- ****************************************************************************************************************Rutas LazyLoad
	1.- Se modifica app.componnet.html
		<div class="row mt-5">
		  <div class="col-md-3 col-sm-12">
		      <app-menu></app-menu>
		  </div>

		  <div class="col-md-9 col-sm-12">
		      <router-outlet></router-outlet>
		  </div>
		</div>
	2.- Se modifca graficas-routing.module.ts, se configuran las rutas hijas
		const routes: Routes = [
		  {
		    path:'',
		    children: [
		      {path: 'barra', component: BarrasComponent},
		      {path: 'barradoble', component: BarrasDobleComponent},
		      {path: 'dona', component: DonaComponent},
		      {path: 'donahttp', component: DonaHttpComponent},
		      {path: '**', redirectTo: 'barra'}
		    ]
		  }  
		];
	3.- Se modifica app-routing.module.ts, se configura el lazyLoad
		const routes: Routes = [
		  {
		    path: 'graficas',
		    loadChildren: () => import('./graficas/graficas.module').then( m => m.GraficasModule )
		  },
		  {
		    path: '**', redirectTo: 'graficas'
		  }
		];
	4.- se prueban las rutas de la aplicacion
************************************************************************************************************************************
329.- *********************************************************************************************************Menu de la aplicacion
	1.- Se modifica menu.component.ts
		1.1.- Se crea interface 
			interface MenuItem{
			  ruta: string,
			  texto: string
			}
		1.2.- Se crea arreglo de tipo de la interface creada
			menu: MenuItem[] = [
			    { ruta: '/graficas/barra', texto: 'Barras'},
			    { ruta: '/graficas/barradoble', texto: 'Barras Doble'},
			    { ruta: '/graficas/dona', texto: 'Donas'},
			    { ruta: '/graficas/donahttp', texto: 'Donas Http'},
			  ] 
	2.- Se modifica menu.component.html, se le da estructura
		<ul class="list-group">
		    <li *ngFor="let item of menu" class="list-group-item" [routerLink]="[item.ruta]" routerLinkActive="active">
		        {{item.texto}}
		    </li>
		</ul>
	3.- Se modifica menu.component.css, se crea atributo para lo elemento html li
		<ul class="list-group">
		    <li *ngFor="let item of menu" class="list-group-item" [routerLink]="[item.ruta]" routerLinkActive="active">
		        {{item.texto}}
		    </li>
		</ul>
	4.- Se modifica app.component.html
		<div class="row mt-5">
		  <div class="col-md-3 col-sm-12">
		      <app-menu></app-menu>
		  </div>

		  <div class="col-md-9 col-sm-12">
		      <router-outlet></router-outlet>
		  </div>
		</div>
	5.- Se prueban las rutas en el html renderizado
************************************************************************************************************************************
330.- **************************************************************************************************************Grafica de barra
	1.- instalacion de ng2-charts 
		npm install --save ng2-charts
		npm install --save chart.js
	2.- Se modifica graficas.module.ts, se importa 
		ChartsModule
	3.- Se sube la aplicacion --> ng serve
		Nota se genera el siguiente warning
			Warning: C:\Users\eareizac\Desktop\Personal\Workspace\Angular\AngularFit\Seccion 21 Graficas Angular\graficasApp\node_modules\ng2-charts\__ivy_ngcc__\fesm2015\ng2-charts.js depends on 'chart.js'. CommonJS or AMD dependencies can cause optimization bailouts.
			For more info see: https://angular.io/guide/build#configuring-commonjs-dependencies
		
		Para corregirlo se modifica el angular.json, se incluye allowedCommonJsDependencies 

		"architect": {
        "build": {
          "builder": "@angular-devkit/build-angular:browser",
          "options": {
            "allowedCommonJsDependencies": ["chart.js"],
    4.- Se modifica barras.component.html
    	4.1.- Se copia grafico de barra
    		<div class="row">
			    <div class="col">
			        <div style="display: block">
			            <canvas baseChart
			              [datasets]="barChartData"
			              [labels]="barChartLabels"
			              [options]="barChartOptions"
			              [legend]="barChartLegend"
			              [chartType]="barChartType">
			            </canvas>
			          </div>
			          <button color="primary" (click)="randomize()" class="btn btn-primary float-end">Update</button>
			    </div>
			</div>
	5.- Se modifica barras.component.ts
		5.1.- se copian atributos del grafico de barra en la clase
			public barChartOptions: ChartOptions = {
		    responsive: true,
		    // We use these empty structures as placeholders for dynamic theming.
		    scales: { xAxes: [{}], yAxes: [{}] },
		    plugins: {
		      datalabels: {
		        anchor: 'end',
		        align: 'end',
		      }
		    }
		  };
		  public barChartLabels: Label[] = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
		  public barChartType: ChartType = 'bar';
		  public barChartLegend = true;
		  public barChartPlugins = [pluginDataLabels];

		  public barChartData: ChartDataSets[] = [
		    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
		    { data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B' }
		  ];
		   public randomize(): void {
		    // Only Change 3 values
		    this.barChartData[0].data = [
		      Math.round(Math.random() * 100),
		      59,
		      80,
		      (Math.random() * 100),
		      56,
		      (Math.random() * 100),
		      40 ];
		  }
************************************************************************************************************************************
331.- *************************************************************************************************Personalizacion de la grafica
	1.- Se modifica barras.component.ts
		1.1.- Se modifica ChartDataSets, se le agregan colores
			Referencia --> https://color.adobe.com/es/create/color-wheel

			public barChartData: ChartDataSets[] = [
			    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' , backgroundColor: '#1AD40F', hoverBackgroundColor: 'blue'},
			    { data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B' , backgroundColor: '#2183FF', hoverBackgroundColor: 'red'},
			    { data: [8, 68, 70, 39, 56, 7, 10], label: 'Series c' , backgroundColor: '#EBFA1C', hoverBackgroundColor: 'green'}
			  ];
		1.2.- Se modifica chartType
			public barChartType: ChartType = 'horizontalBar';
		1.3.- Se modifica metodo  randomize()
			  public randomize(): void {
			    // Only Change 3 values
			    this.barChartData[0].data = [
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100)
			    ],
			    this.barChartData[1].data = [
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100)
			    ],
			    this.barChartData[2].data = [
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100),
			      Math.round(Math.random() * 100)
			    ]

			  }
	2.- Se modifica barras.component.html, se le agrega clase al boton y se agrega titulo
		<h1>Grafica de Barras</h1>
		<hr>
		<div class="row">
		    <div class="col">
		        <div style="display: block">
		            <canvas baseChart
		              [datasets]="barChartData"
		              [labels]="barChartLabels"
		              [options]="barChartOptions"
		              [legend]="barChartLegend"
		              [chartType]="barChartType">
		            </canvas>
		          </div>
		          <button color="primary" (click)="randomize()" class="btn btn-primary float-end">Update</button>
		    </div>
		</div>
************************************************************************************************************************************
332.- ********************************************************************************Componente perzonalizado para mostrar graficos
	1.- Se modifica grafica-barra.component.ts
		public barChartOptions: ChartOptions = {
		    responsive: true,
		    // We use these empty structures as placeholders for dynamic theming.
		    scales: { xAxes: [{}], yAxes: [{}] },
		    plugins: {




		      datalabels: {
		        anchor: 'end',
		        align: 'end',
		      }
		    }
		  };
		  public barChartLabels: Label[] = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
		  public barChartType: ChartType = 'horizontalBar';
		  public barChartLegend = true;

		  public barChartData: ChartDataSets[] = [
		    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' , backgroundColor: '#1AD40F', hoverBackgroundColor: 'blue'},
		    { data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B' , backgroundColor: '#2183FF', hoverBackgroundColor: 'red'},
		    { data: [8, 68, 70, 39, 56, 7, 10], label: 'Series c' , backgroundColor: '#EBFA1C', hoverBackgroundColor: 'green'}
		  ];

		  public randomize(): void {
		    // Only Change 3 values
		    this.barChartData[0].data = [
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100)
		    ],
		    this.barChartData[1].data = [
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100)
		    ],
		    this.barChartData[2].data = [
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100),
		      Math.round(Math.random() * 100)
		    ]

		  }
	2.- Se modifica grafica-barra.component.html
		<div class="row">
		    <div class="col">
		        <div style="display: block">
		            <canvas baseChart
		              [datasets]="barChartData"
		              [labels]="barChartLabels"
		              [options]="barChartOptions"
		              [legend]="barChartLegend"
		              [chartType]="barChartType">
		            </canvas>
		          </div>
		      </div>
		</div>
	3.- Se modifica barra-doble.component.html
		<h1>Graficas Dobles</h1>
		<hr>
		<div class="row">
		    <div class="col-md-6">
		        <app-grafica-barra></app-grafica-barra>
		    </div>
		    <div class="col-md-6">
		        <app-grafica-barra></app-grafica-barra>
		    </div>
		</div>
************************************************************************************************************************************
333.- **************************************************************************Añadir flexibilidad a los componentes personalizados
	1.- Se modifica barra-doble.component.ts se le agrega data
		  proveedoresData: ChartDataSets[] = [
		    { data: [ 100,200,300,400,500 ], label: 'Vendedor A' },
		    { data: [ 50,250,30, 450,200 ], label: 'Vendedor B' },
		  ];
		  
		  proveedoresLabels: string[] = ['2021', '2022','2023','2024','2025'];
		  
		  productoData: ChartDataSets[] = [
		    { data: [ 200, 300,400,300, 100 ], label: 'Carros', backgroundColor: 'blue' },
		  ];
	2.- Se modifica  barra-doble.component.html, se agrega propiedades de @input
		<h1>Graficas Dobles</h1>
		<hr>
		<div class="row">
		    <div class="col-md-6">
		        <app-grafica-barra [horizontal]="true"
		        [barChartLabels]="proveedoresLabels"
		        [barChartData]="productoData"
		        ></app-grafica-barra>
		    </div>
		    <div class="col-md-6">
		        <app-grafica-barra
		        [barChartLabels]="proveedoresLabels"
		        [barChartData]="proveedoresData">
		    </app-grafica-barra>
		    </div>
		</div>
	3.- Se modifica grafica-barra.component.ts
		3.1.- Se le agrega @input()
			@Input() horizontal: boolean = false;
			//public barChartLabels: Label[] = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
  			@Input() barChartLabels: Label[] = [];
  			/* public barChartData: ChartDataSets[] = [
			    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' , backgroundColor: '#1AD40F', hoverBackgroundColor: 'blue'},
			    { data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B' , backgroundColor: '#2183FF', hoverBackgroundColor: 'red'},
			    { data: [8, 68, 70, 39, 56, 7, 10], label: 'Series c' , backgroundColor: '#EBFA1C', hoverBackgroundColor: 'green'}
			  ]; */
  			@Input() barChartData: ChartDataSets[] = [];
		3.2.- Se odifica metodo ngOnInit()
			  ngOnInit(): void {
			    if(this.horizontal){
			      this.barChartType = 'horizontalBar'
			    }
			  }
************************************************************************************************************************************
334.- ******************************************************************************************************************Grafica Dona
	1.- Se modifica donas.component.html
		<h1>Porciones de ventas</h1>
		<hr>
		<div class="row">
		    <div class="col">
		        <div style="display: block">
		            <canvas baseChart
		                [data]="doughnutChartData"
		                [labels]="doughnutChartLabels"
		                [chartType]="doughnutChartType"
		                [colors]="colors">
		            </canvas>
		        </div>
		    </div>
		</div>
	2.- Se modifica donas.component.ts
		export class DonaComponent implements OnInit {

			  // Doughnut
			  public doughnutChartLabels: Label[] = ['Download Sales', 'In-Store Sales', 'Mail-Order Sales'];
			  public doughnutChartData: MultiDataSet = [
			    [350, 450, 100],
			    [50, 150, 120],
			    [250, 130, 70],
			  ];
			  public doughnutChartType: ChartType = 'doughnut';

			  public colors: Color[] = [
			    {
			      backgroundColor:[
			        '#26FDD2',
			        '#22E386',
			        '#33FA62',
			        '#2EE322',
			        '#7EFD26'
			      ]
			    },
			    {
			      backgroundColor:[
			        '#26FDD2',
			        '#22E386',
			        '#33FA62',
			        '#2EE322',
			        '#7EFD26'
			      ]
			    },
			    {
			      backgroundColor:[
			        '#26FDD2',
			        '#22E386',
			        '#33FA62',
			        '#2EE322',
			        '#7EFD26'
			      ]
			    }
			  ]


			  constructor() { }

			  ngOnInit(): void {
			  }

			}
************************************************************************************************************************************
335.- ***************************************************************************************************************Peticiones Http
	1.- Se crea archivo db.json con el siguiente contenido
		{
		    "grafica": {
		        "facebook": 2700,
		        "youtube": 2000,
		        "whatsapp": 1400,
		        "facebook-messenger": 1350,
		        "instagram": 1123
		    }
		}
	2.- Se instala jso-server referencia --> https://www.npmjs.com/package/json-server
		2.1.- Se instala desde la consola
			npm install -g json-server
		2.2.- Nos ubicamos desde la consola en el path donde creamos el archivo db.json
			json-server --watch db.json
		2.3.- Se prueba el servicio  con la url que indica luego de levantar el json-server
			http://localhost:3000/grafica
	3.- Se modifica app.module.ts, se importa HttpClientModule
		  imports: [
		    BrowserModule,
		    AppRoutingModule,
		    HttpClientModule
		  ],
	4.- Se modifica graficas.service.ts, se crea metodo para obtener la data del json
		4.1.- Se realiza injeccion de dependencia de HttpClient
			constructor( private http: HttpClient) { }
		4.2.- Se crea el servicio
			  getData(){
			    return this.http.get('http://localhost:3000/grafica');
			  }
	5.- Se modifica dona-http.component.ts, se modifica el metodo ngOnInit() para que obtenga la data
		5.1.- Se realiza injeccion de dependencia del servicio
			constructor( private graficasService: GraficasService ) { }
		5.2.- Se modifica ngoninit()
			  ngOnInit(): void {
			    this.graficasService.getData().subscribe(
			      data => {
			        console.log(data);
			      }
			    )
			  };
************************************************************************************************************************************
336.- *************************************************************************************************Mostrar la data en la grafica
	1.- Se modifca dona-http.component.ts
		1.1.- Se modifica metodo ngoninit()
			ngOnInit(): void {
		    this.graficasService.getData().subscribe(
		      data => {
		        console.log(data);
		        const labels = Object.keys(data);
		        const values = Object.values(data);
		        this.doughnutChartLabels = labels;
		        this.doughnutChartData.push(values);
		      }
		    )
		  };
		1.2.- Se modifica los labels y la data a mostrar y se traen desde el servicio
			  public doughnutChartLabels: Label[] = [
			    //'Download Sales', 'In-Store Sales', 'Mail-Order Sales'
			  ];
			  public doughnutChartData: MultiDataSet = [
			    /* [350, 450, 100],
			    [50, 150, 120],
			    [250, 130, 70], */
			  ];
	2.- Se modifca dona-http.component.html
		<h1>Redes sociales mas usadas</h1>
		<hr>
		<div class="alert alert-info" *ngIf="doughnutChartData.length === 0; else divGraficas">
		    Cargando Data...
		</div>
		<div class="row" #divGrafica>
		    <div class="col">
		        <ng-template #divGraficas>
		            <div style="display: block">
		                <canvas baseChart
		                    [data]="doughnutChartData"
		                    [labels]="doughnutChartLabels"
		                    [chartType]="doughnutChartType"
		                    [colors]="colors">
		                </canvas>
		            </div>
		            <span class="float-end text-primary">
		                Informacion en billones de usuarios
		            </span>
		        </ng-template>               
		    </div>
		</div>
************************************************************************************************************************************
337.- *****************************************************************************************************Informacion mediante RXJS
	1.- Se modifica graficas.service.ts, se modifica el metodo getData(), se transforma la data del subscribe por medio del pipe
		 getData(){
		    return this.http.get('http://localhost:3000/grafica').pipe(
		      delay(1500),
		      map( 
		        data => {
		          const labels = Object.keys(data);
		          const valores = Object.values(data);
		          return {labels, valores};
		        }
		      )
		    );
		  }
	2.- Se modifica dona-http.component.ts, se modifica metodo ngoninit(), se desestructura la data que llega del servicio
		  ngOnInit(): void {
		    this.graficasService.getData().subscribe(
		      ({labels , valores }) => { 
		        //console.log(data);
		        /* const labels = Object.keys(data);
		        const values = Object.values(data);
		        this.doughnutChartLabels = labels;
		        this.doughnutChartData.push(values); */

		        this.doughnutChartLabels = labels;
		        this.doughnutChartData.push( valores );
		      }
		    )
		  };
************************************************************************************************************************************



Complementarios*********************************************************************************************************************
	pipe: nos sirve para cambiar data
	------------------------------------------------------------------------------------------------------------------------
	Directivas**************************************************************************************************************
		Las Directivas extienden la funcionalidad del HTML usando para ello una nueva sintaxis. Con ella podemos usar lógica 
		que será ejecutada en el DOM (Document Object Model).
		Cada Directiva que usamos tiene un nombre, y determina donde puede ser usada, sea en un elemento, atributo, 
		componente o clase.
		
		Se dividen en tres tipos diferentes:
			Directivas de Atributo
				Alteran la apariencia o comportamiento de un elemento del DOM y son usados como atributos de los elementos.
					Entre la directivas de atributo, encontramos:
						ngModel: Implementa binding
						ngClass: permite añadir/eliminar varias clases
						ngStyle: permite asignar estilos inline
			Directivas de estructurales
				Alteran la estructura del DOM, agregando, eliminando y manipulando los elementos host a los que están unidos.
				En las directivas estructurales podemos encontrar las siguientes:
					*ngIf: Nos permite incluir condicionales de lógica en nuestro código
					*ngFor: Permite ejecutar bucles, los bucles son los que conocemos en lógica de programación como: 
						for, while, foreach, etc.
					ngSwitch: esta directiva es similar al *ngIf, y es como el switch en lógica de programación. 
						En esta directiva se pueden crear los diferentes casos que deseamos evaluar y cuando se cumple 
						la condición esperada, oculta/muestra el HTML.
					ngPlural: es una directiva que permite agregar o remover elementos del DOM, basado en un valor númerico.
					ngTemplate: esta directiva como su nombre lo indica es un template en Angular. El contenido de esta 
						etiqueta puede reutilizarse en otros templates.
					ngComponentOutlet: nos permite crear componentes dinámicos.
			Componentes
				Las Directivas de Componente son directivas con un Template. Los componentes tienen decoradores
				“@Component”, el componente es un decorador @Directive que es extendido con características propias 
				de los templates.

		¿Cómo generar una directiva?
			Desde el Angular CLI podemos generar una directiva usando el siguiente comando:
				ng generate directive <name> [options]
			En su forma abreviada sería:
				ng g d [name]
	************************************************************************************************************************
	ElementRef y Render2************************************************************************************************************
		Es simplemente una clase que envuelve elementos DOM nativos en el navegador y le permite trabajar con DOM 
		proporcionando el nativeElementobjeto que expone todos los métodos y propiedades de los elementos nativos.

		An ElementRefestá respaldado por un elemento específico de renderizado. En el navegador, esto suele ser un 
		elemento DOM.

		Nota:_______________________________________________________________________________________________________
		Utilice esta API como último recurso cuando se necesite acceso directo a DOM. En su lugar, use 
		plantillas y enlaces de datos proporcionados por Angular. Alternativamente, puede echar un vistazo 
		a Renderer2 qué proporciona una API que se puede utilizar de forma segura incluso cuando no se admite 
		el acceso directo a elementos nativos.

		Confiar en el acceso DOM directo crea un acoplamiento estrecho entre su aplicación y las capas de 
		renderización, lo que hará que sea imposible separar las dos e implementar su aplicación en un trabajador web.

		Para sortear este obstáculo tienes la clase Renderer2 de Angular. Renderer2 proporciona una API para acceder 
		de forma segura a elementos nativos, incluso cuando no están soportados por la plataforma (web workers, 
		server-side rendering, etc).

		Ejemplo------------------------------------------------------------------------------------------------------
			import { Component, Renderer2 } from '@angular/core';

			@Component({
			  selector: 'app-root',
			  template: '<button #myButton></button>'
			})
			export class AppComponent{
			  @ViewChild("myButton") myButton: ElementRef;

			  constructor(private renderer: Renderer2) { 
			  }

			  addMyClass(){
			    //this.myButton.nativeElement.classList.add("my-class"); //BAD PRACTICE
			    this.renderer.addClass(this.myButton.nativeElement, "my-class");
			  }

			  removeMyClass(){
			    //this.myButton.nativeElement.classList.remove("my-class"); //BAD PRACTICE
			    this.renderer.removeClass(this.myButton.nativeElement, "my-class");
			  }

			 disable(){
			    //this.myButton.nativeElement.setAttribute("disabled", "true"); //BAD PRACTICE
			    this.renderer.setAttribute(this.myButton.nativeElement, "disabled", "true");
			  }

			  enable(){
			   //this.myButton.nativeElement.removeAttribute("disabled"); //BAD PRACTICE
			   this.renderer.removeAttribute(this.myButton.nativeElement, "disabled");
			  }  

			    clickButton(){
			    //this.myButton.nativeElement.click(); //BAD PRACTICE
			    this.renderer.selectRootElement(this.myButton.nativeElement).click();
			  }

			  addText(){
			    let text = this.renderer.createText("my button");
			    this.renderer.appendChild(this.myButton.nativeElement, text);
			  } 

			}
	************************************************************************************************************************
	HostListener************************************************************************************************************
		Decorador que declara un evento DOM para escuchar y proporciona un método de controlador para ejecutar cuando 
		ocurre ese evento.
			eventName?	
			The DOM event to listen for.

			args?	
			A set of arguments to pass to the handler method when the event occurs.
			Ejemplo-------------------------------------------------------------------------------------------------
				  @HostListener('mouseenter') mouseEntro(){
				    this.elem.nativeElement.style.backgroundColor = "yellow";
				  }
				  @HostListener('mouseleave') mouseSale(){
				    this.elem.nativeElement.style.backgroundColor = null;
				  }
	************************************************************************************************************************
	Angular CLI Referencia**************************************************************************************************

		https://cli.angular.io/
	************************************************************************************************************************
	RouterLink**************************************************************************************************************
		Cuando se aplica a un elemento en una plantilla, convierte ese elemento en un enlace que inicia la navegación 
		a una ruta. La navegación abre uno o más componentes enrutados en una o más ubicaciones 
		de la página.<router-outlet>
	************************************************************************************************************************
	switchMap***************************************************************************************************************
		Proyecta cada valor de fuente en un Observable que se fusiona en el Observable de salida, emitiendo valores solo 
		del Observable proyectado más recientemente.

		el switchMapoperador creará un observable derivado (llamado observable interno) a partir de una fuente observable 
		y emitirá esos valores.

		Cuando la fuente emite un nuevo valor, creará un nuevo observable interno y switcha esos valores en su lugar. De lo que 
		se cancela la suscripción son los observables internos que se crean sobre la marcha, y no la fuente observable.
	************************************************************************************************************************
	Pluggins para descargar en VC*******************************************************************************************
		*Angular snippets
			Nos ayudará completando nuestro código Angular, tanto con las directivas como con los componentes.
		*Angular Language Service 
			This extension provides a rich editing experience for Angular templates, both inline and external templates
		*angular2-inline
			Para los colores inline de html y css.
		*Angular Essential
			Paquete de programas 
		*Auto Complete tag
		*Angular files 
			Podremos crear componentes, filtros, modules… con el botón derecho del ratón.
		*Code spell checker
			Como un diccionario aplicado a nuetro código
		*Document this
	************************************************************************************************************************
	css bootstrap***********************************************************************************************************
		btn btn-primary  		--> boton de color azul
		float-end				--> lo alinea a la izquierda
		text-primary			--> texto en azul
	************************************************************************************************************************
	css*********************************************************************************************************************
		/* toma todo el espacio y lo empuja al margen */
		.spacer{
		    flex: 1 1 auto;
		}
	************************************************************************************************************************
	Temas de AngularMaterial************************************************************************************************
		deeppurple-amber.css
		indigo-pink.css
		pink-bluegrey.css
		purple-green.css
	************************************************************************************************************************
	combineLatest --> rxjs**************************************************************************************************
		Combina múltiples Observables para crear un Observable cuyos valores se calculan a partir de los últimos valores 
		de cada uno de sus Observables de entrada.
		
		Ejemplo:_______________________________________________________________________________________________________
			//metodo que genera varias peticiones segun arreglo borders
			  getPaisesPorCodigo( borders: string[] ): Observable<PaisSmall[]>{   
			    if(!borders){ return of([]) };    
			    const peticiones: Observable<PaisSmall>[] = [];    
			    borders.forEach( codigo => {
			      const peticion = this.getPaisPorCodigoSmall( codigo );
			      peticiones.push( peticion );
			    });
			    return combineLatest( peticiones );
			  }
		_______________________________________________________________________________________________________________

		Devoluciones:
			Observable<R> | Observable<ObservedValueOf<O>[]>: Un Observable de valores proyectados a partir de los 
			valores más recientes de cada entrada Observable, o una matriz de los valores más recientes de cada 
			entrada Observable.

		combineLatest combina los valores de todos los Observables pasados ​​en la matriz de observables. Esto se 
		hace suscribiéndose a cada Observable en orden y, siempre que cualquier Observable emite, recolectando 
		una matriz de los valores más recientes de cada Observable. Entonces, si pasa nObservables a este operador, 
		el Observable devuelto siempre emitirá una matriz de nvalores, en un orden correspondiente al orden de los 
		Observables pasados ​​(el valor del primer Observable estará en el índice 0 de la matriz y así sucesivamente).
	************************************************************************************************************************
	cicleLife Hooks Angular************************************************************************************************
		Una instancia de componente tiene un ciclo de vida que comienza cuando Angular crea una instancia de la clase de 
		componente y representa la vista del componente junto con sus vistas secundarias. El ciclo de vida continúa con 
		la detección de cambios, ya que Angular verifica cuándo cambian las propiedades vinculadas a los datos y 
		actualiza tanto la vista como la instancia del componente según sea necesario. El ciclo de vida termina cuando 
		Angular destruye la instancia del componente y elimina su plantilla renderizada del DOM.

		ngOnChanges()***********************************************************************************************
			Proposito:
			Responde cuando Angular establece o restablece las propiedades de entrada vinculadas a datos. El método 
			recibe un SimpleChangesobjeto de valores de propiedad actuales y anteriores.

			Tenga en cuenta que esto sucede con mucha frecuencia, por lo que cualquier operación que realice aquí 
			tendrá un impacto significativo en el rendimiento. Consulte los detalles en Uso de ganchos de detección 
			de cambios en este documento.
			
			Timing:
			Se llama antes ngOnInit()  y siempre que cambien una o más propiedades de entrada enlazadas a datos.

			Tenga en cuenta que si su componente no tiene @imput o lo usa sin proporcionar ninguna @imput, 
			el marco no llamará ngOnChanges().
		ngOnInit()**************************************************************************************************
			Propsito:
			Inicialice la directiva o el componente después de que Angular muestre primero las propiedades 
			enlazadas a datos y establezca las propiedades de entrada de la directiva o del componente. 
			Consulte los detalles en Inicialización de un componente o directiva en este documento.

			Timing:
			Llamado una vez, después de la primera ngOnChanges()
		ngDoCheck()**************************************************************************************************
			Proposito:
			Detecte y actúe sobre los cambios que Angular no puede o no detectará por sí solo.
			Timing:
			Se llama inmediatamente después ngOnChanges() de cada ejecución de detección de cambios e inmediatamente 
			después ngOnInit()de la primera ejecución.
		ngAfterContentInit()****************************************************************************************
			Proposito:
			Responda después de que Angular proyecte contenido externo en la vista del componente o en la vista en 
			la que se encuentra una directiva.

			Timing:
			Llamado una vez después del primero ngDoCheck().
		ngAfterContentChecked()*************************************************************************************
			Proposito: 
			Responda después de que Angular verifique el contenido proyectado en la directiva o el componente.

			Timing:
			Llamado después ngAfterContentInit()y todos los posteriores ngDoCheck().
		ngAfterViewInit()*******************************************************************************************
			Proposito:
			Responda después de que Angular inicialice las vistas del componente y las vistas secundarias, o 
			la vista que contiene la directiva.

			Timing:
			Llamado una vez después del primero ngAfterContentChecked().
		ngAfterViewChecked()****************************************************************************************
			Proposito:
			Responda después de que Angular verifique las vistas del componente y las vistas secundarias, o la 
			vista que contiene la directiva.

			Timing:
			Llamado después del ngAfterViewInit()y todos los siguientes ngAfterContentChecked().
		ngOnDestroy()**********************************************************************************************
			Proposito:
			Limpieza justo antes de que Angular destruya la directiva o el componente. Cancele la suscripción a 
			Observables y desconecte los controladores de eventos para evitar pérdidas de memoria.

			Timing:
			Se llama inmediatamente antes de que Angular destruya la directiva o el componente.
	************************************************************************************************************************
************************************************************************************************************************************




Modificaciones de Kiosco pendientes************************************************************************************************
	* Cambiar formularios a formularios reactivos
	* Modifica projecto para que este estructurado en modulos y se carguen por lazyLoad
	* Incluir Angular Flex y NgPrime
***********************************************************************************************************************************
















******************************************************Primer curso de Angular**********************************************************
Seccion 1 Intro************************************************************************************************************************
Complementarios************************************************************************************************************************
	Versionado de herramientas
		node -v 
		npm -v 
		tsc --version 
		ng -v --> angular cli 
		npm install -g @angular/cli
		npm install -g ionic

	**Creacion de archivo de config de typescript
		tsc --init 
	** Modo de observador typescript
		tsc -w 
		tsc --watch
	** Excluir archivos a traducir por typescript
		1.- Se modifica tsconfig.json
			{
				"exclude":["nombreFolder"],
			}
***************************************************************************************************************************************
Seccion 2 typescript*******************************************************************************************************************
15.- ***************************************************************************************************************Templates Literales
	function saludar( nombre:string ) {
	    console.table( 'Hola' + nombre ); // Hola Logan
	}

	const nombre : string = "Elvis";
	const apellido : string = "Areiza";
	const edad: number = 38;
	const wolverine = {
	    nombre: `${nombre} ${apellido} tiene una edad de ${edad}`  //templates literales
	};


	saludar( wolverine.nombre );

	Nota: entre las llaves puedes ejecutar codigo typescript, funciones y procesar codigo
16.- **********************************************************************funciones: parametros opcionales, obligatorios y por defecto
	function activar( obligatorio: string,
						opcional ?: string,
						paramDefault: string = "default"){
		
	}
18.- **********************************************************************************************Desestructuracion de objs y arreglos
	const persona ={
	    nombre: "Diego",
	    apellido : "areiza",
	    edad : 15
	}

	const {nombre, apellido, edad} = persona;

	const wolverine = {
    	nombre: `${nombre} ${apellido} tiene una edad de ${edades(edad)}`
	};

	1.- destructuracion de argumentos
		//destructuracion de argumentos
		const extraer = ({nombre,apellido, edad}:any) => {
		    console.log(`${nombre} ${apellido} tiene una edad de ${edad}`)
		}

		extraer( persona );

	2.-  //destruturacion de arreglos
		const arreglo:string[] =['uno', 'dos', 'tres'];
		const [primero, segundo, tercero] = arreglo;

		console.log(`primero:${primero}, segundo:${segundo}, tercero:${tercero}`);

	3.- destructuracion de argumento arreglo

		const arreglo:string[] =['uno', 'dos', 'tres'];

		//destructuracion de arreglo
		const extraer = ([primero, segundo, tercero]:string[]){
		    console.log(`primero:${primero}, segundo:${segundo}, tercero:${tercero}`);
		}

		extraer( arreglo );
19.- **************************************************************************************************************************Promesas
	console.log('Inicio');

	//resolve se ejecuta si es satisfactorio, reject en caso de falla
	const prom1=new Promise((resolve,reject)=>{
	    setTimeout(()=>{
	        reject('se termino el time out');
	    },1000);
	});

	prom1.then(mensaje=>console.log(mensaje)).catch(err => console.warn(err));

	console.log('Fin');
20.- ************************************************************************************************Promesas y su tipado en Typescript
	console.log('Inicio');

	//devuelve una promesa de tipo number
	const retirarDinero=(montoRetiro:number): Promise<number>=>{
	    let dineroActual = 1000;

	    return new Promise((resolve, reject)=>{
	        if (montoRetiro > dineroActual){
	            reject('No hay dinero suficiente');
	        }else{
	            dineroActual -= montoRetiro;
	            resolve(dineroActual);
	        }
	    })
	}

	retirarDinero(1500).then(montoActual => console.log(montoActual)).catch(console.warn);
	console.log('Fin');
21.- ************************************************************************************************************************Interfaces
		
	Nota: las interface es la manera de establecer reglas para la creacion de objetos
	interface Xmen{
	    nombre:string;
	    edad: number;
	    poder ?: string; //Se marca como opcional
	}

	const enviarMision=(xmen: Xmen)=>{
	    console.log(`enviando a ${xmen.nombre} a la mision`);
	}

	const regresarCuartel=(xmen: Xmen)=>{
	    console.log(` ${xmen.nombre} a regresado al cuartel`);
	}

	const wolverine: Xmen={
	    nombre:"Logan",
	    edad: 38
	}

	enviarMision(wolverine);
	regresarCuartel(wolverine);
27.- *************************************************************************************************Tipado del retorno de una funcion
	//Tipado de retorno de funcion
	const sumar= (a:number, b:number):number => a + b;

	const nombre = ():string => 'Hello'

	const obtenerSalario = (): Promise<string> =>{
	    return new Promise((resolve, reject) =>{
	        resolve('Elvis');
	    })
	}

	obtenerSalario().then(a => console.log(a));
Seccion 3: Aplicacion Angular**********************************************************************************************************
36.- ***************************************************************************************************Primera Interaccion con Angular
	1.- Servicio Plunker para probar codigo en angular
	2.- Stackblitz para probar codigo en angular
38.- *********************************************************************************************************Creacion de entorno local
	1.- ng new nombreapp
	2.- ng g c components/header
		2.1.- Se modifica el header.component.tsc
			import { Component } from '@angular/core';

			@Component({
			  selector: 'app-header',
			  templateUrl: './header.component.html'
			})
			export class HeaderComponent{

			}
		2.2.- Se modifica el header.component.html
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
			    <a class="navbar-brand" href="#">Navbar</a>
			    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			    <span class="navbar-toggler-icon"></span>
			  </button>

			    <div class="collapse navbar-collapse" id="navbarSupportedContent">
			        <ul class="navbar-nav mr-auto">
			            <li class="nav-item active">
			                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
			            </li>
			            <li class="nav-item">
			                <a class="nav-link" href="#">Link</a>
			            </li>
			            <li class="nav-item dropdown">
			                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Dropdown
			        </a>
			                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
			                    <a class="dropdown-item" href="#">Action</a>
			                    <a class="dropdown-item" href="#">Another action</a>
			                    <div class="dropdown-divider"></div>
			                    <a class="dropdown-item" href="#">Something else here</a>
			                </div>
			            </li>
			            <li class="nav-item">
			                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
			            </li>
			        </ul>
			        <form class="form-inline my-2 my-lg-0">
			            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
			            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			        </form>
			    </div>
			</nav>
		2.3.- Se modifica el app.component.html
	3.- Se modifica index.html agregagan los cdn de boottrap 4
		/*<!doctype html>
		<html lang="en">

		<head>
		    <meta charset="utf-8">
		    <title>MyApp</title>
		    <base href="/">
		    <meta name="viewport" content="width=device-width, initial-scale=1">
		    <link rel="icon" type="image/x-icon" href="favicon.ico">
		    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
		</head>

		<body>
		    <app-root></app-root>
		    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
		</body>

		</html>*/
42.- *******************************************************************************************Creacion del component footer.component
	1.- Se crea compponente ng g c /components/footer
	2.- Se modifica footer.component.html
		<footer class="footer bg-dark">
		    <div class="container">
		        <p>&copy; Elvis Areiza 2020</p>
		    </div>
		</footer>
	3.- Se modifica app.component.html, se le agrega el selector de footer
		<app-header></app-header>
		<app-body class="container"></app-body>
		<ul>
		    <li>Nombre: {{nombre}}</li>
		    <li>Apellido: {{apellido}}</li>
		</ul>
		<app-footer></app-footer>
	4.- Se aplican cambios de css
		4.1.- se modifica style.css, se crea clase general de la Aplicacion
			footer {
			    color: white;
			    position: fixed;
			    bottom: 0px;
			    width: 100%;
			}
	5.- Se modifica footer.component.html, se le agrega clase bootstrap para centrar 
		<footer class="footer bg-dark text-center">
		    <div class="container">
		        <p>&copy; {{anio}}Elvis Areiza </p>
		    </div>
		</footer>
	6.- Se modifica footer.component.ts, se agrega atributo anio a la clase y se inicializa en el constructor
		export class FooterComponent implements OnInit {

		  anio:number;
		  constructor() {
		    this.anio = new Date().getFullYear();
		   }

		  ngOnInit(): void {
		  }

		}
43.- *********************************************************************************************Creacion del component body.component
	1.- Se crea compponente ng g c /components/body
	2.- Se modifica body.component.html
		<div class="row">
		    
	3.- Se modifica app.component.html, se le agrega el selector de body
		<app-header></app-header>
		<div class="container">
		    <app-body class="container m-5"></app-body>
		</div>
		<app-footer></app-footer>
44.- *******************************************************************************************Directivas estructurales *ngIf y *ngFor
	1.- Se crea atributo boolean y string[] en el componente body.component.ts
		bandera:boolean = true;
		personajes: string[] = ['spiderman', 'superman', 'batman', 'Hulk'];
	2.- Se modifica body.component.html, se utiliza la directiva *ngIf
		<div *ngIf="bandera" class="card text-white bg-success mb-3" style="max-width:100%;">
            <div class="card-body">
                <h5 class="card-title">Autor: {{frase.autor}}</h5>
                <p class="card-text">{{frase.mensaje}}</p>
            </div>
        </div>
       	2.1.- Se crea evento onclick en el boton
       		<div (click)="bandera = !bandera" class="btn btn-outline-success btn-block">
	            Mostrar/Ocultar
	        </div>
	3.-	Se modifica body.component.html, se utiliza la directiva *ngIf
	    <ul class="list-group">
            <li *ngFor="let personaje of personajes; let i = index" class="list-group-item">
                {{i+1}}.- {{personaje}}
            </li>
        </ul>
Seccion 4: Aplicacion SPA****************************************************************************************************************
50.- ***********************************************************************************************Creacion de estructura del proyecto
	1.- Se crea aplicacion con el comendo --> ng new spa
	2.- Se ingresa en la carpeta del proyecto y se ejecuta --> ng serve -o
	3.- Se crean dentro de app los folder components dentro de este sahred
	4.- Se crea componente navbar --> ng g c components/shared/navbar
51.- ******************************************************************************************Instalacion de bootstrap con angular cli
	1.- Primera forma mediante los cdn (Se requiere internet)
		1.1.- Se modifica index.html, se le agregan los cdn
			<!doctype html>
			<html lang="en">

			<head>
			    <meta charset="utf-8">
			    <title>Spa</title>
			    <base href="/">
			    <meta name="viewport" content="width=device-width, initial-scale=1">
			    <link rel="icon" type="image/x-icon" href="favicon.ico">
			    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
			</head>

			<body>
			    <app-root></app-root>
			    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
			    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
			    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
			    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

			</body>

			</html>

	2.- Segunda forma descargando las librerias de bootstrap
		2.1.- Se descargan las librerias
		2.2.- Se copia la libreria en src/assets
		2.3.- Se modifica index.html, se modifica los src para que los busque en los assets
	3.- Tercera forma, utilizando npm
		3.1.- Se ejecuta --> npm install bootstrap --save
		3.2.- Se ejecuta --> npm install jquery --save
		3.3.- Se ejecuta --> npm install popper.js --save 
		3.4.- Se modifica el archivo angular.json
			"styles": [
                "src/styles.css",
                "node_modules/bootstrap/dist/css/bootstrap.min.css"
            ],
            "scripts": [
                "node_modules/jquery/dist/jquery.slim.min.js",
                "node_modules/popper.js/dist/umd/popper.min.js",
                "node_modules/bootstrap/dist/js/bootstrap.min.js"
            ]
52.- **************************************************************************************Configuracion del navbar y otros componentes
	1.- Se crea carpeta img en assets
	2.- Se copian las imagenes del projecto en la carpeta img
	3.- Se modifica el archivo navbar.component.html
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		    <a class="navbar-brand" href="#">
		        <img src="assets/img/A-64.png" width="30" height="30" alt="" loading="lazy">
		    </a>
		    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>

		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		        <ul class="navbar-nav mr-auto">
		            <li class="nav-item active">
		                <a class="nav-link" href="#">Home </a>
		            </li>
		            <li class="nav-item">
		                <a class="nav-link" href="#">Link</a>
		            </li>
		            <li class="nav-item dropdown">
		                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          Dropdown
		        </a>
		                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		                    <a class="dropdown-item" href="#">Action</a>
		                    <a class="dropdown-item" href="#">Another action</a>
		                    <div class="dropdown-divider"></div>
		                    <a class="dropdown-item" href="#">Something else here</a>
		                </div>
		            </li>

		        </ul>
		        <form class="form-inline my-2 my-lg-0">
		            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
		            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		        </form>
		    </div>
		</nav>
	4.- Se crea componente --> ng g c components/Home
		/*4.1.- Se modifica home.component.html
			<div class="jumbotron jumbotron-fluid">
			    <div class="container">
			        <h1 class="display-4">Commics App</h1>
			        <p class="lead">Esta es una aplicacion del Curso Angular</p>
			    </div>
			</div>*/
		4.2.- Se modifica app.component.html, se agrega el selector de home.component.ts
			<app-home></app-home>
	5.- Se crea componente Acerca --> ng g c components/acerca
	5.- Se crea componente heroes --> ng g c components/heroes
53.- ******************************************************************************************************************Rutas en Angular
	1.- Dentro de app, se crea archivo --> app.routes.ts
	2.- Se modifica el archivo app.routes.ts
		import { Routes, RouterModule } from '@angular/router';
		import { HomeComponent } from './components/home/home.component';


		const APP_ROUTES: Routes = [
		  { path: 'home', component: HomeComponent },
		  { path: '**', pathMatch: 'full', redirectTo: 'home' }
		];

		export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES);
	3.- Se incorporan las rutas en el app.module.ts
		3.1.- Se importan las rutas
			//Rutas
			import { APP_ROUTING } from './app.routes';
		3.2.- Se añaden a los imports
			  imports: [
			    BrowserModule,
			    APP_ROUTING
			  ]
54.- *****************************************************************************************************RouterLink y RouterLinkActive
	1.- Se modifica app.routes.ts, se agregan las rutas a los complnetes heroes y acerca
		1.1.- Se importan los componentes 
			import { HeroesComponent } from './components/heroes/heroes.component';
			import { AcercaComponent } from './components/acerca/acerca.component';
		1.2.- Se agregan las rutas
			const APP_ROUTES: Routes = [
			  { path: 'home', component: HomeComponent },
			  { path: 'acerca', component: AcercaComponent },
			  { path: 'heroes', component: HeroesComponent },
			  { path: '**', pathMatch: 'full', redirectTo: 'home' }
			];
	2.- Se agregan los routerlink al navbar
		 <li class="nav-item active">
            <a class="nav-link" [routerLink]="['home']">Home </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" [routerLink]="['acerca']">Acerca</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" [routerLink]="['heroes']">Heroes</a>
        </li>
    3.- Se modifica style.css, se crea clase con margen superior
    	.main-container{
    		margin-top: 20px
    	}
    4.- Se modifica app.component.html, se agega clase css creada
    	<div class="container main_container">
		    <router-outlet></router-outlet>
		</div>
	5.- Se crean animaciones, se modifica el archivo style.css para crear animacion
		.animated {
		  -webkit-animation-duration: 1s;
		  animation-duration: 1s;
		  -webkit-animation-fill-mode: both;
		  animation-fill-mode: both;
		}

		.fast {
		  -webkit-animation-duration: 0.4s;
		  animation-duration: 0.4s;
		  -webkit-animation-fill-mode: both;
		  animation-fill-mode: both;
		}

		@keyframes fadeIn {
		  from {
		    opacity: 0;
		  }

		  to {
		    opacity: 1;
		  }
		}

		.fadeIn {
		  animation-name: fadeIn;
		}
	6.- Se le agregan las clase de css a los componentes
		6.1.- componente de acercacomponent.html
			<h1 class="animated fadeIn fast">App sobre Comics</h1>
			<hr>
			<p class="animated fadeIn">
			    Lorem ipsum dolor sit amet consectetur adipisicing elit. Doloribus, totam rerum quidem, accusantium voluptatum doloremque delectus expedita magnam perferendis ab quos magni sed! Recusandae, incidunt cumque. Ullam earum ipsa error.
			</p>
		6.2.- Se modifica home.component.html
			<div class="jumbotron jumbotron-fluid animated fadeIn fast">
			    <div class="container">
			        <h1 class="display-4">Commics App</h1>
			        <p class="lead">Esta es una aplicacion del Curso Angular</p>
			    </div>
			</div>
55.- **********************************************************************************************************Componente heroes diseño
	1.- Se modifica heroes.components.html, se le agregan cars de bootstrap con imagenes
		<h1>Heroes <small>Marvel</small></h1>
		<hr>

		<div class="card-columns">
		    <div class="card">
		        <img src="assets/img/+58.png" class="card-img-top" alt="...">
		        <div class="card-body">
		            <h5 class="card-title">Card title</h5>
		            <p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
		            <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
		        </div>
		    </div>
		    <button class="btn btn-outline-primary btn-block">
	          Ver mas ...
	        </button>

		</div>

	2.- Se modifica style.css, para crear un margen al en la parte inferior
		body {
		    padding-bottom: 50px;
		}
57.- *******************************************************************************************************Creacion de servicio Heroes
	1.- Se crea folder services
	2.- Se crea heroes.sevice.ts
		2.1.- Se ejecuta en el archivo --> ng-service
			import { Injectable } from '@angular/core';

			@Injectable({
			  providedIn: 'root'
			})
			export class NameService {
			}
		2.2.- Se cambia el nombre del service por HeroesService
	3.- Se configura el servicio en el archivo app.module.ts 
		3.1.- Se importa el servicio
			ng-import
			/* Services */
			import { HeroesService } from './services/heroes.servce';
		3.2.- Se configura el servicio en los providers
			  providers: [
			    HeroesService
			  ],
	4.- Se modifica clase de servicio, se agrega arrglo de data,, se crea metodo get para el acceso al arreglo, se crea interface heroes
		import { Injectable } from '@angular/core';

		@Injectable({
		  providedIn: 'root'
		})
		export class HeroesService {

		  private heroes: Heroe[] = [
		    {
		      nombre: "Aquaman",
		      bio: "El poder más reconocido de Aquaman es la capacidad telepática para comunicarse con la vida marina, la cual puede convocar a grandes distancias.",
		      img: "assets/img/aquaman.png",
		      aparicion: "1941-11-01",
		      casa:"DC"
		    },
		    {
		      nombre: "Batman",
		      bio: "Los rasgos principales de Batman se resumen en «destreza física, habilidades deductivas y obsesión». La mayor parte de las características básicas de los cómics han variado por las diferentes interpretaciones que le han dado al personaje.",
		      img: "assets/img/batman.png",
		      aparicion: "1939-05-01",
		      casa:"DC"
		    },
		    {
		      nombre: "Daredevil",
		      bio: "Al haber perdido la vista, los cuatro sentidos restantes de Daredevil fueron aumentados por la radiación a niveles superhumanos, en el accidente que tuvo cuando era niño. A pesar de su ceguera, puede \"ver\" a través de un \"sexto sentido\" que le sirve como un radar similar al de los murciélagos.",
		      img: "assets/img/daredevil.png",
		      aparicion: "1964-01-01",
		      casa: "Marvel"
		    },
		    {
		      nombre: "Hulk",
		      bio: "Su principal poder es su capacidad de aumentar su fuerza hasta niveles prácticamente ilimitados a la vez que aumenta su furia. Dependiendo de qué personalidad de Hulk esté al mando en ese momento (el Hulk Banner es el más débil, pero lo compensa con su inteligencia).",
		      img: "assets/img/hulk.png",
		      aparicion: "1962-05-01",
		      casa:"Marvel"
		    },
		    {
		      nombre: "Linterna Verde",
		      bio: "Poseedor del anillo de poder que posee la capacidad de crear manifestaciones de luz sólida mediante la utilización del pensamiento. Es alimentado por la Llama Verde (revisada por escritores posteriores como un poder místico llamado Starheart), una llama mágica contenida en dentro de un orbe (el orbe era en realidad un meteorito verde de metal que cayó a la Tierra, el cual encontró un fabricante de lámparas llamado Chang)",
		      img: "assets/img/linterna-verde.png",
		      aparicion: "1940-06-01",
		      casa: "DC"
		    },
		    {
		      nombre: "Spider-Man",
		      bio: "Tras ser mordido por una araña radiactiva, obtuvo los siguientes poderes sobrehumanos, una gran fuerza, agilidad, poder trepar por paredes. La fuerza de Spider-Man le permite levantar 10 toneladas o más. Gracias a esta gran fuerza Spider-Man puede realizar saltos íncreibles. Un \"sentido arácnido\", que le permite saber si un peligro se cierne sobre él, antes de que suceda. En ocasiones este puede llevar a Spider-Man al origen del peligro.",
		      img: "assets/img/spiderman.png",
		      aparicion: "1962-08-01",
		      casa: "Marvel"
		    },
		    {
		      nombre: "Wolverine",
		      bio: "En el universo ficticio de Marvel, Wolverine posee poderes regenerativos que pueden curar cualquier herida, por mortal que ésta sea, además ese mismo poder hace que sea inmune a cualquier enfermedad existente en la Tierra y algunas extraterrestres . Posee también una fuerza sobrehumana, que si bien no se compara con la de otros superhéroes como Hulk, sí sobrepasa la de cualquier humano.",
		      img: "assets/img/wolverine.png",
		      aparicion: "1974-11-01",
		      casa: "Marvel"
		    }
		  ];

		  constructor(){
		    console.log('Servicio listo para ejecutar.');
		  }

		  getHeroes(): Heroe[]{
		    return this.heroes;
		  }

		}

		export interface Heroe{
		  nombre: string;
		  bio: string;
		  img: string;
		  aparicion: string;
		  casa: string;
		}


	5.- Se modifica heroes.component.ts, se le agrega importacion de la clase de servicio, se le pasa como parametro al constructor y
		se recupera la lista en el metodo ngOnInit
		import { Component, OnInit } from '@angular/core';
		import { HeroesService, Heroe } from '../../services/heroes.servce';


		@Component({
		  selector: 'app-heroes',
		  templateUrl: './heroes.component.html',
		  styleUrls: ['./heroes.component.css']
		})
		export class HeroesComponent implements OnInit {

		  heroes: Heroe[] = [];

		  constructor( private _heroesService: HeroesService) { }

		  ngOnInit(): void {
		    this.heroes = this._heroesService.getHeroes();
		    console.log(this.heroes);
		  }

		}
58.- **************************************************************************************************************Rutas con parametros
	1.- Se crea un componente para mostrar el detalle del heroe
		ng g c components/heroe -is 
	2.- Se incorpora la ruta al app.routes.ts
		2.1.- Se importa el componente 
			import { HeroeComponent } from './components/heroe/heroe.component';
		2.2.- Se incorpora el path
			  { path: 'heroe/:id', component: HeroeComponent },
	3.- Primera forma de navegar hacia el compornente creado, Se modifica heroes.component.html, se crea indice al for, 
	se crea hipervinculo con el enlace al componente creado
		<div class="card-columns">
		    <div class="card" *ngFor="let heroe of heroes; let i = index">
		        <img [src]="heroe.img" class="card-img-top" alt="">
		        <div class="card-body">
		            <h5 class="card-title">{{heroe.nombre}}</h5>
		            <p class="card-text">{{heroe.bio}}</p>
		            <p class="card-text"><small class="text-muted">Aparicion: {{heroe.aparicion}}</small></p>
		        </div>
		        //<button class="btn btn-outline-primary btn-block">
		        //  Ver mas ...
		        //</button>
		        <a [routerLink]="['/heroe',i]" class="btn btn-outline-info">Ver mas</a>
		    </div>

		</div>
	4.- Segunda forma de navegar hacia el componente, se modifica heroes.component.ts
		4.1.- Se grega boton con evento onclick
			<button (click)="mostrarHeroe(i)" class="btn btn-outline-primary btn-block">
	          Ver mas ...
	        </button>
	    4.2.- Se modifica heroes.component.ts
	    	4.2.1.- Se importa libreria Router 
	    		import { Router } from '@angular/router';
	    	4.2.2.- Se realiza inyeccion de dependencia en el constructor
	    		  constructor( private router: Router,
                private _heroesService: HeroesService) { }
	    	4.2.3.- Se crea metodo mostrarHeroe()
	    	 	 mostrarHeroe(idx: number){
				    this.router.navigate(['/heroe',idx]);
				  }
59.- *****************************************************************************************Recibir parametros por URL ActivatedRoute
	1.- Se modifica heroe.component.ts, se captura parametros con ActivatedRoute
		1.1.- Se importa la libreria
			import { ActivatedRoute } from '@angular/router';
		1.2.- Se realiza inyeccion de dependencia del ActivatedRoute y se capturan los parametros en el constructor
			constructor(private activatedRoute: ActivatedRoute) {
			    this.activatedRoute.params.subscribe( param => {
			      console.log(param);
			    });
			}
	2.- Se modifica heroe.service.ts, se crea getHeroe() metodo que retorna un heroe
		  getHeroe(idx: number): Heroe {
		    return this.heroes[idx];
		  }
	3.- Se modifica heroe.component.ts, para que recupere el heroe de la clase de servicio
		3.1.- Se importa la clase de servicio
			import { HeroesService, Heroe } from '../../services/heroes.servce';
		3.2.- Se realiza la inyeccion de dependencia en el constructor y se recupera el heroe de la clase de servicio.
			constructor(private activatedRoute: ActivatedRoute,
              private heroeService: HeroesService) {
			    this.activatedRoute.params.subscribe( param => {
			      this.heroe = this.heroeService.getHeroe(param['id']);
			    });
			   }
	4.- Se modifica heroe.component.html, se crea plantilla para mostrar heroe
		<h1>{{heroe.nombre}} <small>{{heroe.aparicion}}</small></h1>
		<hr>
		<div class="row">
		    <div class="col-md-4">
		        <img [src]="heroe.img" alt="{{heroe.nombre}}" class="img-fluid">
		        <br><br>
		        <a [routerLink]="['/heroes']" class="btn btn-outline-primary btn-block">Regresar</a>
		    </div>
		    <div class="col-md-8">
		        <h3>{{heroe.nombre}}</h3>
		        <hr>
		        <p>{{heroe.bio}}</p>
		        <div class="animated fadeIn">
		            <img class="img-logo" *ngIf="heroe.casa == 'DC'" src="/assets/img/dc.jpg" alt="">
		            <img class="img-logo" *ngIf="heroe.casa == 'Marvel'" src="/assets/img/marvel.jpg" alt="">
		        </div>
		    </div>
		</div>
60.- *******************************************************************************************Pipes: transformacion visual de la data
	Tipos:
		*currency
		*Date
		*uppercase 
		*json
		*limitto
		*lowercase
		*async
		*decimal
		*percent
	1.- Se modifica heroe.component.html
		<h1>{{heroe.nombre | uppercase}} <small>({{heroe.aparicion | date: 'y' }})</small></h1>

	1.- Se modifica nvarbar.component.html
		1.1.- Se captura el valor de una caja de texto, se le pone alias a el tag de html con #buscarTexto, se le agrega 
		funcion (keyup.enter), se le crea evento click al boton
			        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" (keyup.enter)="buscarHeroe(buscarTexto.value)" #buscarTexto>
            <button class="btn btn-outline-primary my-2 my-sm-0" type="button" (click)="buscarHeroe(buscarTexto.value)">Search</button>
        </form>
    2.- Se modifica heroe.service.ts, se crea funcion para recuperar el heroe indicado en el serch 
    	  buscarHeroes(termino:string): Heroe[]{
		    const heroesArr: Heroe[] = [];
		    termino = termino.toLowerCase();
		    for (let heroe of this.heroes){
		      const nombre = heroe.nombre.toLowerCase();
		      if (nombre.indexOf(termino) >= 0){
		        heroesArr.push(heroe);
		      }
		    }
		    return heroesArr;
		  }
	3.- Se realizan las modificaciones para que al buscar un heroe en el nvarbar, nos lleve a la pagina de heroes y muestre 
	el que estamos buscando
		3.1.- Se modifica nvarbar.component.ts, se importa la libreria Router
			import { Router } from '@angular/router';
		3.2.- Se realiza la inyeccion de dependencia
			constructor(private router: Router) { }
		3.3.- Se crea metodo para navegar hasta el componente heroes 
			  buscarHeroe(texto: string){
			    this.router.navigate(['/heroes', texto]);
			  }
		3.4.- Se crea una nueva ruta en app.routes.ts
			{ path: 'heroes/:term', component: HeroesComponent },
		3.5.- Se modifica heroes.component.ts, se le añade al ngoninit, procesos para buscar un heroe y renderizarlo

			  ngOnInit(): void {
			    this.activatedRoute.params.subscribe(param => {
			      const term = param['term'];
			      // Se valida si la ruta tiene el parametro term
			      if (!(term === undefined)){
			        // se puebla el arreglo con el heroe del search
			        this.heroes = this._heroesService.buscarHeroes(param['term']);
			      }
			    });
			    // valida si el arreglo de heroes contiene algun obj
			    if (!(this.heroes.length > 0)){
			      // Puebla el arreglo con la lista de heroes
			      this.heroes = this._heroesService.getHeroes();
			    }
			  }
67.- *****************************************************************************************Mostrando mensaje cuando no hay resultado
	1.- Se modifica heroes.component.html, se agrega div para el mensaje
		<div class="row" *ngIf="heroes.length == 0">
		    <div class="col-md-12">
		        <div class="alert alert-info" role="alert">
		            No hay resultados en la busqueda.
		        </div>
		    </div>
		</div>
68.- ******************************************************************************@Input recibir inf. de un componente padre a un hijo
	1.- Se crea un componente ng g c components/heroeTarjeta --skipTest=false
	2.- Se modifica heroes.component.html, se corta el div referente a la heroeTarjeta
		<h1>Heroes <small>Marvel</small></h1>
		<hr>
		<div class="row" *ngIf="heroes.length == 0">
		    <div class="col-md-12">
		        <div class="alert alert-info" role="alert">
		            No hay resultados en la busqueda.
		        </div>
		    </div>
		</div>
		<div class="card-columns">
		  <!-- *ngFor="let heroe of heroes; let i = index" -->
		    

		</div>
	3.- Se modifica heroe-tarjeta.component.html, se copia el div correspondiente a la heroe-tarjeta
		<h1>Heroes <small>Marvel</small></h1>
		<hr>
		<div class="row" *ngIf="heroes.length == 0">
		    <div class="col-md-12">
		        <div class="alert alert-info" role="alert">
		            No hay resultados en la busqueda.
		        </div>
		    </div>
		</div>
		<div class="card-columns">
		  <!-- *ngFor="let heroe of heroes; let i = index" -->
		    

		</div>
	4.- Se modifica heroe-tarjeta.component.ts, se crea atributo --> heroe:any = []
	5.- Se modifica heroes.component.html, se añade el selector, se le añade el ciclo for y el aparametro que importa
		<app-heroe-tarjeta [heroe]="heroe" *ngFor="let heroe of heroes; let i = index"></app-heroe-tarjeta>
		5.1.- Se modifica heroe-tarjeta.component.ts, se agrega el @Input 
			5.1.1.- Se importa la libreria Input 
				import { Component, OnInit, Input} from '@angular/core';
			5.1.2.- Se agrega el @Input a el atributo que vendra del padre
				  @Input() heroe: any = [];
	6.- Modificaciones para que siga funcionando el mostrarHeroe()
		6.1.- Se modifica heroe-tarjeta.component.html, se modifica la llamada al metodo mostrarHeroe(), ya no lleva argumento.
		6.2.- Se modifica heroe-tarjeta.component.html, se copia el metodo mostrarHeroe() de heroes.component.ts
			mostrarHeroe(){
			    this.router.navigate(['/heroe', this.idx]);
			  }
		6.3.- Se importa y se inyecta el Router
			import { Router } from '@angular/router';
			constructor( private router: Router) { }
		6.4.- Se crea atributo que recibe el indice del heroe
			@Input() idx: number;
		6.5.- Se modifica heroes.component.html, se exporta el indice al hijo [idx]="i"
			<app-heroe-tarjeta [heroe]="heroe" [idx]="i" *ngFor="let heroe of heroes; let i = index"></app-heroe-tarjeta>
69.- **********************************************************************************@Output emitir un evento del hijo hacia el padre
	1.- Se modifica heroe-tarjeta.component.ts, emite el evento
		1.1.- Se importa  Output, EventEmitter
			import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
		1.2.- Se crea el atributo que recibe el padre @Output de tipo emitter
			@Output() heroeSeleccionado: EventEmitter<number>;
		1.3.- Se inicializa en el constructor el evento
			constructor( private router: Router) {
			    this.heroeSeleccionado = new EventEmitter();
			  }
		1.4.- Se emite el evento pasandole el indice
			 mostrarHeroe(){
			    // this.router.navigate(['/heroe', this.idx]);
			    this.heroeSeleccionado.emit(this.idx);
			  }
		1.5.- Se modifica el padre heroes.component.html para que escuche el evento --> (heroeSeleccionado)="mostrarHeroe( $event )"
			<app-heroe-tarjeta (heroeSeleccionado)="mostrarHeroe( $event )" [heroe]="heroe" [idx]="i" *ngFor="let heroe of heroes; let i = index"></app-heroe-tarjeta>
70.- **********************************************************Se soluciona problema en el serch, siempre busca en el array el indice 0
	1.- Se modifica la clase de servicio, para que cree un atributo al obj llamado idx
		  getHeroes(): Heroe[]{
		    let i:number = 0;
		    this.heroes.forEach(heroe => {
		      heroe.idx = i;
		      i += 1;
		    });
		    return this.heroes;
		  }

		  buscarHeroes(termino: string): Heroe[]{
		    const heroesArr: Heroe[] = [];
		    termino = termino.toLowerCase();
		    for (let i = 0; i <  this.heroes.length ; i++){
		      let heroe = this.heroes[i];
		      const nombre = heroe.nombre.toLowerCase();
		      if (nombre.indexOf(termino) >= 0){
		        heroe.idx = i;
		        heroesArr.push(heroe);

		      }
		    }
		    console.log('servicio')
		    console.log(heroesArr);
		    return heroesArr;
		  }

		}
		1.1.- Se modifica la interface con el nuevo atributo de forma opcional
			export interface Heroe{
			  nombre: string;
			  bio: string;
			  img: string;
			  aparicion: string;
			  casa: string;
			  idx ?: number;
			}
	2.- Se modifica heroe-tarjeta.component.ts, para que emita el indice para buscar el elemento
		 mostrarHeroe(){
		    //this.router.navigate(['/heroe', this.heroe.idx]);
		    this.heroeSeleccionado.emit(this.heroe.idx);
		  }
Seccion 5: Pipes***********************************************************************************************************************
	Sirven para cambiar la data de forma visual
		Pipes uppercase y lowercase
		Pipe Slice
		Pice Decimal
		Pipe Percent
		Pipe Currency
		Pipe Json
		Pipe Async
		Pipe Date
		Pipes personalizados
			Capitalizar palabras y nombres
			Creación de un pipe, que permite cargar recursos externos de forma segura.
75.- ****************************************************************************************Introduccion a Pipes lowercase y uppercase

	1.- Se cre proyecto --> ng new Pipes
	2.- Se modifca app.component.ts, se agrega atributo name
		nombre:string='Capitan America';
	3.- Se modifica app.component.html
		<h1>Pipes <small>Angular</small></h1>
		<hr>
		<div class="row">
		    <div class="col">
		        <table class="table">
		            <thead class="thead-dark">
		                <th>Variable</th>
		                <th>Pipe</th>
		                <th>Retorno</th>
		            </thead>
		            <tbody>
		                <tr>
		                    <td>{{nombre}}</td>
		                    <td>UpperCase</td>
		                    <td>{{ nombre | uppercase }}</td>
		                </tr>
		                <tr>
		                    <td>{{nombre}}</td>
		                    <td>UpperCase</td>
		                    <td>{{ nombre | lowercase }}</td>
		                </tr>
		            </tbody>

		        </table>
		    </div>
		</div>
76.- ************************************************************************************************************************Pipe Slace
	Corta el texto o arreglo segun necesidad
	1.- Se modifica app.component.ts, se crea arreglo
		arreglo =[0,1,2,3,4,5,6,7,8,9];
	2.- Se modifica app.component.html, se le agregan ejemplos a la tabla
		<tr>
            <td>{{nombre}}</td>
            <td>Slice:2:8</td>
            <td>{{ nombre | slice:2:8}}</td>
        </tr>
        <tr>
            <td>{{arreglo}}</td>
            <td>Slice:2:8</td>
            <td>{{ arreglo | slice:2:8}}</td>
        </tr>
    3.- Se recorre arreglo con slice

        <h4>Slice</h4>
        <ul>
            <li *ngFor="let item of arreglo | slice:5:20"> {{item}} </li>
        </ul>
77.- **********************************************************************************************************************Pipe Decimal
	1.- Se crea propiedad
		PI = Math.PI;
	2.- Se crea el ejemplos
		 <tr>
            <td>{{PI}}</td>
            <td>number:'.2-4'</td>
            <td>{{ PI | number:'.2-4'}}</td>
        </tr>
78.- *********************************************************************************************************Pipe Percent(¨porcentaje)
        1.- Se agrega propiedad
        	porcentaje: number = 0.234;
        2.- ejemplos
        	 <tr>
                <td>{{ porcentaje }}</td>
                <td>percent:'.2-2'</td>
                <td>{{ porcentaje | percent:'.2-2'}}</td>
            </tr>
79.- *********************************************************************************************************************Pipe Currency
	1.- Se crea propiedad
		salario: number = 354258
	2.- ejemplos
		 <tr>
            <td>{{ salario }}</td>
            <td>currency</td>
            <td>{{ salario | currency}}</td>
        </tr>
        <tr>
            <td>{{ salario }}</td>
            <td>currency:'EUR'</td>
            <td>{{ salario | currency:'EUR'}}</td>
        </tr>
80.- *************************************************************************************************************************Pipe Json
	1.- Se crea propiedad
		  persona = {
		    nombre: 'Elvis',
		    apellido: 'Areiza',
		    edad: 38,
		    caracteristica: {
		      piel: 'blanca',
		      ojos: 'negros'
		    }
		  };
	2.- Se crea ejemplos
		 <h3>Pipe Json</h3>
        <hr>
        <div class="row mb-5">
            <div class="col">
                <pre>
              {{persona | json}}
            </pre>
            </div>
        </div>
81.- ************************************************************************************************************************Pipe Async
	1.- Se crea una promesa
		  valorPromesa = new Promise<string>((resolve) => {
		    setTimeout(() => {
		      resolve('Llego la data');
		    }, 5000);
		  });
	2.- Se crea el ejemplos
		<tr>
	        <td>{{ valorPromesa }}</td>
	        <td>Async</td>
	        <td>{{ valorPromesa | async }}</td>
	    </tr>
82.- *************************************************************************************************************************Pipe Date
	1.- Se crea propiedad de tipo date
		fecha : Date = new Date();
	2.- ejemplos
		<tr>
            <td>{{ fecha }}</td>
            <td>date</td>
            <td>{{ fecha | date }}</td>
        </tr>
        <tr>
            <td>{{ fecha }}</td>
            <td>date:'medium'</td>
            <td>{{ fecha | date: 'medium' }}</td>
        </tr>
        <tr>
            <td>{{ fecha }}</td>
            <td>date:'short'</td>
            <td>{{ fecha | date: 'short' }}</td>
        </tr>
        <tr>
            <td>{{ fecha }}</td>
            <td>date:'MMMM-dd'</td>
            <td>{{ fecha | date: 'MMMM-dd' }}</td>
        </tr>
    3.- Para cambiar la configuracion del locale, se configura el app.module.ts
    	3.0.- Se instala el locales --> ng add @angular/localize
    	3.1.- Se importan las librerias
    		import { registerLocaleData } from '@angular/common';
			import locales from '@angular/common/locales/es';
		3.2.- Se agrega funcion 
			registerLocaleData(locales);
		3.3.- Se añade al providers

			providers: [{
			provide: LOCALE_ID,
			useValue: 'es'
			}],
83.- ***********************************************************************************************************Registrar otros idiomas
	1.- Se modifica app.components.ts, se agrega atributo
		idioma: string = 'fr';
	2.- Se modifica app.module.ts
		2.1.- Se agrega libreria del idioma
			import localfr from '@angular/common/locales/fr';	
		2.2.- Se agrega funcion para el registro del locale
			registerLocaleData(localfr);
	3.- ejemplos
		<tr>
            <td>{{ fecha }}</td>
            <td>fecha | date: 'MMMM-dd':'':{{idioma}}<br>
                <button (click)="idioma = 'es'" class="btn btn-outline-success mr-1">Españos</button>
                <button (click)="idioma = 'en'" class="btn btn-outline-primary mr-1">Ingles</button>
                <button (click)="idioma = 'fr'" class="btn btn-outline-secondary mr-1">Frances</button>
            </td>
            <td>{{ fecha | date: 'MMMM-dd':'':idioma }}</td>
        </tr>
84.- **************************************************************************************************************Pipes Perzonalizados
	1.- Creao un componente pipes --> ng g p pipes/capitalizado
	2.- Se modifica app.components.ts, se agrega atributo --> nombre2 = 'elvis areiza' 
	3.- Se modifica capitalizado.pipes.ts
		export class CapitalizadoPipe implements PipeTransform {

		  transform(value: string, todas: boolean = true): string {
		  	//Se transforma el valor en minuscula
		    value = value.toLocaleLowerCase();
		    //Se combierte a un arreglo de palabras
		    let nombres = value.split(' ');
		    //Se valida si es todas o la primera palabra
		    if (todas){
		      //Se recorre el arreglo y se coloca la primera mayuscula en cada item
		      nombres = nombres.map(nombre => {
		        return nombre[0].toUpperCase() + nombre.substr(1);

		      });
		    }else{
		      //Se coloca la primera letra de la primera palabra en mayuscula
		      nombres[0] = nombres[0][0].toUpperCase() + nombres[0].substr(1);
		    }
		    return nombres.join(' ');
		  }

		}
	4.- Se modifica app.components.html
		<tr>
            <td>{{ nombre2 }}</td>
            <td>Capitalizado</td>
            <td>{{ nombre2 | capitalizado:false }}</td>
        </tr>
85.- *****************************************************************************************************Pipe Perzonalizado: DomSeguro
	1.- Se copia iframe de youtube
		<iframe width="560" height="315" src="https://www.youtube.com/embed/Dr6iQxNu0wo" frameborder="0" 
		allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
	2.- Se modifica app.compoents.ts, se agrega propiedad
		 url:string = 'https://www.youtube.com/embed/Dr6iQxNu0wo';
	3.- Se modifica app.components.html, se crea el ejemplos
		<h3 class="mt-4">Video de fuente externa</h3>
        <hr>
        <div class="row">
            <div class="col">
                <iframe width="560" height="315" [src]="url | domseguro" frameborder="0" 
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
            </iframe>
            </div>
        </div>
    4.- Se crea componente pipe --> ng g p pipes/domseguro 
    	import { Pipe, PipeTransform } from '@angular/core';
		import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

		@Pipe({
		  name: 'domseguro'
		})
		export class DomseguroPipe implements PipeTransform {

		  constructor(private domSanitizer: DomSanitizer){
		  }
		  transform(value: string, ...args: unknown[]): SafeResourceUrl {
		    return this.domSanitizer.bypassSecurityTrustResourceUrl(value);
		  }

		}
Seccion 6: Aplicacion #6 spotiApp******************************************************************************************************
	Pueden ver lo servicios en esta dirección

	https://beta.developer.spotify.com/console/		
93.- ****************************************************************************************************************Inicio de proyecto
	1.- Se crea proyecto --> ng new spotyApp
	2.- Se crea componente home 	-->  ng g c components/home --skipTests=true -is
	3.- Se crea componente search 	-->  ng g c components/search --skipTests=true -is
	4.- Se crea componente artista 	-->  ng g c components/artista --skipTests=true -is
	5.- Se crea componente navbar 	-->   ng g c components/shared/navbar  --skipTests=true -is
	6.- Se coloca la carpeta de img en assets
	7.- se modifica el archivo style.css 
	8.- Se configura bootstrap 
		8.1.- utilizando npm
			8.1.1.- Se ejecuta --> npm install bootstrap --save
			8.1.2.- Se ejecuta --> npm install jquery --save
			8.1.3.- Se ejecuta --> npm install popper.js --save 
			8.1.4.- Se modifica el archivo angular.json
				"styles": [
	                "src/styles.css",
	                "node_modules/bootstrap/dist/css/bootstrap.min.css"
	            ],
	            "scripts": [
	                "node_modules/jquery/dist/jquery.slim.min.js",
	                "node_modules/popper.js/dist/umd/popper.min.js",
	                "node_modules/bootstrap/dist/js/bootstrap.min.js"
	            ]
	9.- Creacion de aplicacion en spotify
		9.1.- Se ingresa en https://beta.developer.spotify.com/consol, se registra la aplicacion
			Client ID a1c0e2867e984ef1879a2609f0857ef6
94.- *****************************************************************************************************************Creacion de rutas
	1.- Se crea el archivo app.routes.ts en raiz de la app
	2.- Se modifica el archivo app.routes.ts, se crean las rutas 
		import { Routes } from '@angular/router';
		import { HomeComponent } from './components/home/home.component';
		import { SearchComponent } from './components/search/search.component';


		export const ROUTES: Routes = [
		  { path: 'home', component: HomeComponent},
		  { path: 'search', component: SearchComponent},
		  { path: '', pathMatch: 'full' , redirectTo: 'home'},
		  { path: '**', pathMatch: 'full' , redirectTo: 'home'}
		];
	3.- Se configuran las rutas en el archivo app.module.ts
		3.1.- Se importa la libreria RouterModule
			import { RouterModule } from '@angular/router';
		3.2.- Se importa las rutas
			// import de rutas
			import { ROUTES } from './app.routes';	
		3.3.- Se configuran las rutas en los imports
			  imports: [
			    BrowserModule,
			    RouterModule.forRoot( ROUTES ,{useHash: true})
			  ],

		Nota: Añadimos el literal {useHash: true} esto añadirá un # a la ruta, que es un viejo truco de los navegadores para evitar que el
		navegador recargue la pagina.
	4.- Se modifica app.component.html
		<app-navbar></app-navbar>

		<div class="container m-5">
		    <router-outlet></router-outlet>
		</div>
	5.- Se modifica nvarbar.component.html, copia de bootstratp un nvarbar
		<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		    <img src="/assets/img/banner-ico.png" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">

		    <a class="navbar-brand" href="#">SpotyApp</a>
		    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>

		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		        <ul class="navbar-nav mr-auto">
		            <li class="nav-item" routerLinkActive="active">
		                <a class="nav-link" routerLink="home">Home <span class="sr-only">(current)</span></a>
		            </li>
		            <li class="nav-item" routerLinkActive="active">
		                <a class="nav-link" routerLink="search">Search</a>
		            </li>

		        </ul>
		    </div>
		</nav>
	1.- Ingresar en --> http://restcountries.eu/
	2.- Se modifica app.module.ts
		2.1.- se importa la libreria HttpClientModule
			import { HttpClientModule } from '@angular/common/http';
		2.2.- Se configura en los imports 
			 imports: [
			    BrowserModule,
			    HttpClientModule,
			    RouterModule.forRoot( ROUTES , { useHash: true})
			  ],
	3. Se modifica home.component.ts, se recupera data de --> https://restcountries.eu/rest/v2/lang/es
		3.1.- Se importa HttpClient --> import { HttpClient } from '@angular/common/http';
		3.2.- Se crea propiedad --> paises: any[];
		3.2.- Se realiza la injeccion de dependencia yde HttpClient y por medio de este se recupera la data
			  constructor( private httpClient: HttpClient) {
			    this.httpClient.get<any>('https://restcountries.eu/rest/v2/lang/es').subscribe( resp => {
			    	this.paises = resp;
			      console.log(resp);
			    });
			  }
		3.3.- Se modifica home.component.html, se crea una lista con los datos recuperados
95.- *********************************************************************************************************Token para uso de spotify
	1.- Se abre postman
	2.- Se prueba servicio de token 
		POST --> https://accounts.spotify.com/api/token
		body --> x-www-form-urlencoded 
			grant_type 			client_credentials
			client_id 			a1c0e2867e984ef1879a2609f0857ef6
			client_secret 		f9976e0363994c398aca11467a99bb4f

		respuesta ejemplo:
			{
			    "access_token": "BQAsJgnSivZYSwng98mPD81VHtLqegFpHjT-DY389Kj5Nv0t1-B_-pvptLWzfMgcRY3M1hvE_Re1UHef8mk",
			    "token_type": "Bearer",
			    "expires_in": 3600,
			    "scope": ""
			}
	3.- Se prueba metodo get --> https://api.spotify.com/v1/browse/new-releases
		3.1.- Se modifica headers, se le añade token
			KEY 									VALUE 
			Authorization							Bearer BQAsJgnSivZYSwng98mPD81VHtLqegFpHjT-DY389Kj5Nv0t1-B_-pvptLWzfMgcRY3M1hvE_Re1UHef8mk
97.- *********************************************************************************************************Se crea clase de Servicio
	1.- Se crea servicio --> ng g s services/spotify
	2.- Se agrega en el constructor console.log('servicio spotify');
	3.- Se realiza la injeccion de dependencia en home.component.ts;
		 constructor( private httpClient: HttpClient,
	                private spotifyService: SpotifyService) {
	4.- Se modifica la clase de servicio spotify.service.ts, Se crea metodo para obtener nuevos new-releases

		  getNewRelease(){
		    //headers de la aplicacion
		    const headers = new HttpHeaders({
		      'Authorization' : 'Bearer BQDBZzqzDucjBZZAq5yTyDVsoCZvq0nPJHYLD8iS59BPgBQgkLxCPowi-Mw7a4DD9xB2rkBzFZM2uafUEMWVyVlwtB0ET0sHu5Y-n60VO_q2qV5xH_R-ccCfF-hhgeCNf5FcUSt1jQkIqsWYhKFuce-y9ngbRRE'
		    });

		    this.http.get('https://api.spotify.com/v1/browse/new-releases', {headers}).subscribe( data => {
		      console.log(data);
		    });
		  }

		 4.1.- import { HttpClient, HttpHeaders } from '@angular/common/http';
	5.- se modifica home.component.ts, se llama al metodo del servicio en el constructor
		  constructor( private httpClient: HttpClient,
                private spotifyService: SpotifyService) {
		    this.spotifyService.getNewRelease();
		  }
98.- **************************************************************************************************************Consumiendo servicio
	1.- Se modifica clase de servicio spotify.service.ts, se le agrega return y se elimina el subscribe
		getNewRelease(){
		    // headers de la aplicacion
		    const headers = new HttpHeaders({
		      'Authorization' : 'Bearer BQDBZzqzDucjBZZAq5yTyDVsoCZvq0nPJHYLD8iS59BPgBQgkLxCPowi-Mw7a4DD9xB2rkBzFZM2uafUEMWVyVlwtB0ET0sHu5Y-n60VO_q2qV5xH_R-ccCfF-hhgeCNf5FcUSt1jQkIqsWYhKFuce-y9ngbRRE'
		    });

		    return this.http.get('https://api.spotify.com/v1/browse/new-releases', {headers});
		  }
	2.- Se modifica home.components.ts, se modifica el constructor para que se subscribe al metodo de la clase de servicio y se crea 
	propiedad para almacenar las nuevas canciones
		nuevasCanciones: any[] = [];

		  constructor( private httpClient: HttpClient,
	            private spotifyService: SpotifyService) {
		    this.spotifyService.getNewRelease().subscribe( (data: any) => {
		      console.log(data.albums.items);
		      this.nuevasCanciones = data.albums.items;
		    });
		  }
	3.- Se modifica home.components.html, se muestra la informacion de cada cancion
		<div class="card-columns">
		    <div class="card" *ngFor="let albums of nuevasCanciones">
		        <img [src]="albums.images[0].url" class="card-img-top" alt="...">
		        <div class="card-body">
		            <h5 class="card-title">{{albums.name}}</h5>
		            <p class="card-text"><span class="badge badge-pill badge-primary" *ngFor="let artista of albums.artists">{{artista.name}}</span></p>
		        </div>
		    </div>
		</div>
99.- *************************************************************************************************Componente de busqueda de artista
	1.- Se modifica spotify.service.ts, se crea metodo que recupera artistas
		  getArtista(term:string){
		    // headers de la aplicacion
		    const headers = new HttpHeaders({
		      'Authorization' : 'Bearer BQDfmK9peh3ieE0R-HRWqNrfzE04Ft5aTM_oOu10fk_hK2EZnS935qbE-PONGfdQYiO4r60P5un9Vj5NsZU'
		    });

		    return this.http.get(`https://api.spotify.com/v1/search?q=${term}&type=artist&limit=15`, {headers});
		  }
	2.- Se modifica search.component.ts, se crea metodo para que recupere artistas desde la clase de servicio, se crea 
	propiedad artistas
		1.- Se crea propiedad
			artistas:any[]=[];
		2.- Se crea metodo
			obtenerArtista(term:string){
		    this.spotifyService.getArtista(term).subscribe( (data:any) => {
		      console.log(data.artists.items);
		      this.artistas = data.artists.items;
		    });
		    console.log(term);
		  }
		3.- Se modifica search.component.html, se renderiza la lista de artistas y se crea input para buscar el artista
			<div class="row">
			    <div class="col">
			        <input type="text" #termino class="form-control0" (keyup)="obtenerArtista(termino.value)" placeholder="Buscar Artista">
			    </div>

			    <div class="card-columns mt-5">
			        <div class="card" *ngFor="let artista of artistas">
			            <img [src]="artista.images[0].url" class="card-img-top" alt="...">
			            <div class="card-body">
			                <h5 class="card-title">{{artista.name}}</h5>
			            </div>
			        </div>
			    </div>
			</div>
100.- **********************************************************************************************Oprearador map() de los Observables
	1.- Se modifica spotify.service.ts, se modifica metodo getNewRelease(), para que filtre con el operador map, lo que realmente se 
	necesita
		1.1.- Se importa la libreria map 
			import { map } from 'rxjs/operators';
		1.2.- Se modifica metodo getNewRelease() y getArtista(), se le agrega pipe y map
			  getNewRelease(){
			    // headers de la aplicacion
			    const headers = new HttpHeaders({
			      'Authorization' : 'Bearer BQD7MlZIYCJ6R8bGKUTkG5mt_0Lq3C6n45bfyZaCfvMJ1UDHK6D_sSKvY91FckJeiRKXnXp7H15UGYtaRhY'
			    });

			    return this.http.get('https://api.spotify.com/v1/browse/new-releases', {headers}).pipe(
			      map( data => {
			        return data['albums'].items;
			      })
			    );
			  }

			   getArtista(term:string){
			    // headers de la aplicacion
			    const headers = new HttpHeaders({
			      'Authorization' : 'Bearer BQD7MlZIYCJ6R8bGKUTkG5mt_0Lq3C6n45bfyZaCfvMJ1UDHK6D_sSKvY91FckJeiRKXnXp7H15UGYtaRhY'
			    });

			    return this.http.get(`https://api.spotify.com/v1/search?q=${term}&type=artist&limit=15`, {headers}).pipe(
			      map(data => {
			        return data['artists'].items;
			      })
			    );
			  }
	2.- Se modifica search.components.ts, se modifica metodo obtenerArtista(), para que solo reciba data
		obtenerArtista(term:string){
		    this.spotifyService.getArtista(term).subscribe( (data:any) => {
		      this.artistas = data;
		    });
		  }
	3.- Se modifica home.components.ts, se modifica el constructor
		constructor( private httpClient: HttpClient,
		                private spotifyService: SpotifyService) {
		    this.spotifyService.getNewRelease().subscribe( (data: any) => {
		      this.nuevasCanciones = data;
		    });
		}
101.- *******************************************************************************************************Centralizar las peticiones
	1.- Se modifica spotify.service.ts getQuery(), se crea metodo para centralizar las peticiones.
		getQuery(query:string){
		    //Se crea constante para la url
		    const url = `https://api.spotify.com/v1/${query}`;
		    // headers de la aplicacion
		    const headers = new HttpHeaders({
		      'Authorization' : 'Bearer BQD7MlZIYCJ6R8bGKUTkG5mt_0Lq3C6n45bfyZaCfvMJ1UDHK6D_sSKvY91FckJeiRKXnXp7H15UGYtaRhY'
		    });
		    return this.http.get(url, {headers});
		  }
		1.2.- Se modifica metodo getNewRelease(), se elimina la constante headers que se centraliza en el metodo getQuery(), se
		llama al metodo getQuery() para hacer la peticion
			 getNewRelease(){
			    // Se llama al metodo getQuery que centraliza las peticiones
			    return this.getQuery('browse/new-releases').pipe(
			      map( data => data['albums'].items)
			    );
			  }
		1.3.- Se modifica metodo getArtista(), se elimina la constante headers que se centraliza en el metodo getQuery(), se
		llama al metodo getQuery() para hacer la peticion
			  getArtista(term:string){
			    return this.getQuery(`search?q=${term}&type=artist&limit=15`).pipe(
			      map(data =>  data['artists'].items)
			    );
			  }
102.- ****************************************************************************************************************Creacion de pipes
	1.- Se crea pipe por consola
		ng g p pipes/noimage
	2.- Se modifica el archivo noimage.pipe.ts
		transform(images: any[]): string {
	    // Se valida si no tiene images y retorno noimage.png
	    if (!images){
	      return 'assets/img/noimage.png';
	    }
	    // valido si tiene imagen el arreglo
	    if (images.length > 0){
	      return images[0].url;
	    }else{
	      return 'assets/img/noimage.png';
	    }
	  }
	3.- Se añade noimage.png de los recursos del curso en assets/img 
	4.- Se modifica search.componets.html y home.components.html, se utiliza pipe noimage
		<img [src]="artista.images | noimage" class="card-img-top" alt="...">
103.- **************************************************************************************************************Componente tarjetas
	1.- Se crea componentes de tarjetas -->  ng g c components/tarjetas --skipTests
	2.- Se copia el contenido de home.components.html en tarjetas.components.html y se realizan alguna modificaciones, para que recorra
	la propiedad items
		<div class="card-columns mt-5">
		    <div class="card putero" *ngFor="let item of items">
		        <img [src]="item.images | noimage" class="card-img-top" alt="...">
		        <div class="card-body">
		            <h5 class="card-title">{{item.name}}</h5>
		            <p class="card-text">
		                <span class="badge badge-pill badge-primary" *ngFor="let artista of item.artists">{{artista.name}}</span>
		            </p>
		        </div>
		    </div>
		</div>
	3.- Se modifica tarjetas.components.ts, se agrega propiedad items
		3.1.- Se importa la libreria Input
			import { Component, OnInit, Input } from '@angular/core';
		3.2.- Se crea propiedad que utiliza la vista que utiliza el componente para pasar la data
			@Input() items: any[];
	4.- Se implementa el componente en home.components.html y search.components.html
		4.1.- Se modifica home.components.html
			<app-tarjetas [items]="nuevasCanciones"></app-tarjetas>
		4.2.- Se modifica search.components.html, se agrega componente tarjetas
			<app-tarjetas [items]="artistas"></app-tarjetas>
104.- ***************************************************************************************************Creacion de loading.components
	1.- Se crea componente --> ng g c components/shared/loading --skipTests -is
	2.- Se instala fontaweson
		 npm install --save @fortawesome/fontawesome-free
		2.1.- Se modifica angular.json, se le agregan scripts y styles
			"styles": [
                "src/styles.css",
                "node_modules/bootstrap/dist/css/bootstrap.min.css",
                "node_modules/@fortawesome/fontawesome-free/css/all.css"
            ],
            "scripts": [
                "node_modules/jquery/dist/jquery.slim.min.js",
                "node_modules/popper.js/dist/umd/popper.min.js",
                "node_modules/bootstrap/dist/js/bootstrap.min.js",
                "node_modules/@fortawesome/fontawesome-free/js/all.js"
            ]
    3.- Se modifica loading.components.html, se renderiza el icono
    	<div class="row text-center animated fadeIn">
		    <div class="col">
		        <i class="fa fa-sync fa-spin fa-5x"></i>
		    </div>
		</div>
	4.- Se modifica home.components.ts, se agrega propiedad loading, para que renderoce el loadin unicamente cuando sea true y este
	cargada la data
		5.1.- Se crea propiedad loading --> loading:boolean;
		5.2.- Se modifica el constructor para que manipule la propiedad loading
			constructor( private httpClient: HttpClient,
                private spotifyService: SpotifyService) {
			    this.loading = true;
			    this.spotifyService.getNewRelease().subscribe( (data: any) => {
			      this.nuevasCanciones = data;
			      setTimeout(() => {}, 3000);
			      this.loading = false;
			    });
			  }
	5.- Se modifica home.compnents.html, se le agrega compoente loading
		<app-loading *ngIf="loading"></app-loading>
		<app-tarjetas [items]="nuevasCanciones" *ngIf="loading"></app-tarjetas>
	6.- Se modifica search.components.ts, se crea propiedad loading y se manipula en el metodo obtenerArtista()
		6.1.- Se crea propiedad -->   loading: boolean;
		6.2.- Se manipula propiedad en el metodo obtenerArtista()
			obtenerArtista(term:string){
			    this.loading = true;
			    this.spotifyService.getArtista(term).subscribe( (data:any) => {
			      this.artistas = data;
			      this.loading = false;
			    });
			  }
	7.- Se modifica search.components.html, se renderiza el loading
		<div class="row">
		    <div class="col">
		        <input type="text" #termino class="form-control" (keyup)="obtenerArtista(termino.value)" placeholder="Buscar Artista">
		        <app-loading *ngIf="loading" class="m-5"></app-loading>
		        <app-tarjetas [items]="artistas" *ngIf="loading"></app-tarjetas>
		    </div>
		</div>
105.- **********************************************************************Pagina de artista, nueva ruta, parametro por url y servicio
	1.- Se modifica app.routes.ts, se crea una ruta al componente artists
		1.1.- Se importa el componente
			import { ArtistaComponent } from './components/artista/artista.component';
		1.2.- Se crea la rura 
			{ path: 'artist/:id', component: ArtistaComponent}, 
	2.- se modifica tarjetas.compoents.ts, se crea metodo que recupera el id del artista y redirecciona para mostrar el detalle
		verDetalle( item: any){
		    console.log(item);
		    let idArtista:string;
		    if( item.type === 'artist'){
		      idArtista = item.id;
		    }else{
		      idArtista = item.artists[0].id;
		    }
		    console.log(idArtista);
		  }
		  2.1.- Se importa objeto Router 
		  	import { Router } from '@angular/router';
		  2.2.- Se realiza injeccion de dependencia de Router 
		  	constructor( private router: Router) { }
		  2.3.- Se modifica el metodo verDetalle(), se le agrega redireccion al modulo artista
		  	this.router.navigate(['/artist', idArtista]);
	3.- Se modifica tarjetas.components.html, se agrega evento click
		<div class="card puntero" (click)="verDetalle( item )" *ngFor="let item of items">
	4.- Se modifica artista.compoenents.ts, se añaden cambios para recuperar parametros por url
		4.1.- Se importa obj ActivatedRoute
			import { ActivatedRoute } from '@angular/router';
		4.2.- Se modifica el constructor para recuperar el parametro
			 constructor( private activatedRoute: ActivatedRoute ) {
			      this.activatedRoute.params.subscribe( params => {
			        console.log(params);
			      });
			   }
106.- *****************************************************************************************************Renderizar y obtener artista
	1.- Se modifica artista.components.ts
		1.1.- Se agrega propiedad --> artista:any;
		1.2.- Se crea metodo getArtista(), para que recupere el artista de la clase de servicio
			getArtista(id:string){
			    this.spotifyService.getArtista(id).subscribe( artista =>{
			      this.artista = artista;
			    });
			  }
		1.3.- Se modifica el constructo llama al metodo getArtista() y le pasa el id recuperado
			  constructor( private activatedRoute: ActivatedRoute,
                private spotifyService: SpotifyService ) {
			      this.activatedRoute.params.subscribe( params => {
			        this.getArtista(params['id']);
			      });
			   }
		1.4.- Se crea propiedad para el loading compoenent --> loading: boolean;
			1.4.1.- Se modifica el metodo getArtista(), para que maneje el loading
				getArtista(id:string){
				    this.loading = true;
				    this.spotifyService.getArtista(id).subscribe( artista => {
				      this.artista = artista;
				      this.loading = false;
				    });
				  }
	2.- Se modifica artista.component.html, se renderiza artista
		<app-loading *ngIf="loading"></app-loading>
		<div class="row" *ngIf="!loading">
		    <div class="col-2">
		        <img [src]="artista.images | noimage" alt="" class="img-circle" style="height: 150px; width: 150px;">
		    </div>
		    <div class="col">
		        <h3>{{ artista.name }}</h3>

		        <p>
		            <a [href]="artista.external_urls.spotify" target="_blank">
		            Ir a la pagina del Artista
		          </a>
		        </p>
		    </div>
		    <div class="col-4 text-right">
		        <button routerLink="/serch" class="btn btn-outline-primary">
		        Regresar
		      </button>
		    </div>
		</div>
107.- **************************************************************************************************************Servicio Top-Tracks
	1.- se modifica la clase de sercicio spotify.service.ts, se agrega metodo getTopTrack()
		  getTopTrack(id:string){
		    return this.getQuery(`artists/${id}/top-tracks?market=us`).pipe(
		      map( data => data['tracks'])
		    );
		  }
	2.- Se modifica artista.components.ts, se crea metodo que obtiene el toptrack de la clase de servicio y se llama en el constructor
		  getTopTrack( id: string){
		    this.spotifyService.getTopTrack( id ).subscribe( topTrack => {
		      console.log(topTrack);
		      this.topTracks = topTrack;
		    });
		  }
		2.1.- Se modifica el constructor, se llemam al metodo creado
		 	  constructor( private activatedRoute: ActivatedRoute,
                private spotifyService: SpotifyService ) {
			      this.activatedRoute.params.subscribe( params => {
			        this.getArtista(params['id']);
			        this.getTopTrack(params['id']);
			      });
			   }
		2.2.- Se crea propiedad  -->  topTracks: any[] = [];
		2.3.- Se modifica artistas.components.ts 
			<div class="row m-5">
			    <div class="col">
			        <table class="table">
			            <thead>
			                <tr>
			                    <th>Foto</th>
			                    <th>Album</th>
			                    <th>Cancion</th>
			                    <th>Vista Previa</th>
			                </tr>
			            </thead>
			            <tbody>
			                <tr *ngFor="let item of topTracks">
			                    <td><img [src]="item.album.images | noimage" alt="" class="img-thumb" style="height: 150px; width: 150px;"></td>
			                    <td>{{item.album.name}}</td>
			                    <td>{{item.name}}</td>
			                    <td>
			                        <audio *ngIf="item.preview_url" [src]="item.preview_url" controls></audio><span *ngIf="!item.preview_url" class="badge badge-primary">Vista previa no Disponible</span>
			                    </td>
			                </tr>
			            </tbody>
			        </table>
			    </div>
			</div>
108.- ****************************************************************************************************************witget de spotify
	1.- Se ingresa a los widgets de spotify
		https://developer.spotify.com/documentation/widgets/generate/embed/
	2.- Se copia iframe y se le agrega a artistas.components.html
		<iframe [src]="item.uri | domseguro" width="300" height="80" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
    3.- Se descargan los recursos de la seccion y pipes, se copia domseguro.pipe.ts y se agrega en la carpeta de pipes del proyecto
    	 transform( value: string ): any {
		    const url = 'https://open.spotify.com/embed?uri=';
		    return this.domSanitizer.bypassSecurityTrustResourceUrl( url + value );
		  }
    4.- Se modifica app.module.ts, se configura el pipe domseguro.pipe.ts
    	4.1.- Se importa 
    		import { DomseguroPipe } from './pipes/domseguro.pipe';
    	4.2.- Se agrega al @ngModule
    		@NgModule({
			  declarations: [
			    AppComponent,
			    HomeComponent,
			    SearchComponent,
			    ArtistaComponent,
			    NavbarComponent,
			    NoimagePipe,
			    DomseguroPipe,
			    TarjetasComponent,
			    LoadingComponent
			  ],               
109.- ***********************************************************************************************Manejo de errores de un observable
	1.- Se modifica home.components.ts, se agrega manejo de eroores en el observable
		1.1.- Se crea propiedad boolean para saber si hay o no un error y una propiedad para almacenar el mensaje
			error: boolean;
  			mensaje: string;
  		1.2.- Se modifica el constructor para el manejo del error
  			 constructor( private httpClient: HttpClient,
                private spotifyService: SpotifyService) {
			    this.loading = true;
			    this.error = false;

			    this.spotifyService.getNewRelease().subscribe( (data: any) => {
			      this.nuevasCanciones = data;
			      console.log(this.nuevasCanciones);
			      this.loading = false;
			    }, (errorService) => {
			      this.loading = false;
			      this.error = true;
			      this.mensaje = errorService.error.error.message;
			    });

			  }
	2.- Se genera componente para despliegue de mensajes de error
		ng g c components/shared/msgerror --skipTests=true -is
		2.1.- Se modifica msgerror.components.ts, se crea propiedad para almacenar el error
			2.1.1.- Se importa Input
				import { Component, OnInit, Input} from '@angular/core';
			2.1.2.- Se crea propiedad
				@Input() mensaje: string;
		2.2.- Se modifica msgerror.components.html
			<div class="alert alert-danger animated fadeIn">
			    <h3>Error:</h3>
			    <p>{{mensaje}}</p>
			</div>
	3.- Se modifica home.components.html, se le agrega el selector y se le mpasa el mensaje
		<app-msgerror [mensaje]="mensaje" *ngIf="error"></app-msgerror>
	4.- Se modifica search.compoenets
		4.1.- Se modifica search.components.ts
			4.1.1.- Se crean propiedades
			  error: boolean;
			  mensaje: string;
			4.1.2.- Se modifica el metodo obtenerArtista(), para que administre el mensaje de error del observable
				  obtenerArtista(term: string){
				    this.loading = true;
				    this.error = false;
				    this.spotifyService.getArtistas(term).subscribe( (data:any) => {
				      this.artistas = data;
				      this.loading = false;
				    },(errorServer) => {
				      this.loading = false;
				      this.error = true;
				      this.mensaje = errorServer.error.error.message;
				    });
				  }
		4.2.- Se modifica search.components.html, se le agrega componente y se le pasa el mensaje
			<app-msgerror [mensaje]="mensaje" *ngIf="error"></app-msgerror>
Seccion 8: Componentes, Directivas de atributos y ciclo de vida************************************************************************
139.- *************************************************************************************************Creacion de proyecto Miscelaneos
	1.- Se descarga de los recursos font-awesome-4.7.0
	2.- Se descargan las librerias bootstrap, jquery, tether
	3.- Se crean carpetas en assets b4,jquery,tether, se copia la carpeta font-awesome-4.7.0
	4.- Se incluye en b4 la carpeta css y js
	5.- Se incluye en la carpeta jquery --> jquery.slim.js
	6.- Se incluye en la carpeta tether --> tether.min.js
	7.- Se modifica angular.json 
		"styles": [
              "src/styles.css",
              "src/assets/b4/css/bootstrap.min.css",
              "src/assets/font-awesome-4.7.0/css/font-awesome.min.css"
            ],
            "scripts": [
              "src/assets/jquery/jquery.slim.js",
              "src/assets/tether/tether.min.js",
              "src/assets/b4/js/bootstrap.min.js"
            ]
140.- *************************************************************************************ngStyle y su uso con directivas de atributos
	1.- Se modifica style.css, crea clase para el componente 
		.main-container {
		    margin-top: 40px;
		}
	2.- Se modifica app.components.html
		<div class="container main-container">
		    <h1>Demos <span>Angular</span></h1>
		    <hr>
		    <app-ng-style></app-ng-style>
		</div>

	3.- Se crea componente, que incluya el template y el style --> ng g c components/ngStyle -it -is
	4.- Se modifica ng-style.components.ts
		@Component({
			  selector: 'app-ng-style',
			  template: `
			    <p  [style.fontSize.px]="size">
			      Esta es una etiqueta
			    </p>
			     <button class="btn btn-outline-primary" (click)="size = size + 5">
			       <i class="fa fa-plus"></i>
			     </button>
			     <button class="btn btn-outline-primary" (click)="size = size - 5">
			       <i class="fa fa-minus"></i>
			     </button>
			  `,
			  styles: [
			  ]
			})
		4.1.- Se agrega propiedad --> size: number = 30;
141.- *********************************************************************************************Aplicando style a un solo componente
	1.- Se crea un componente --> ng g c components/css -it -is
	2.- Se modifica css.components.ts, se agrega styles
		@Component({
			  selector: 'app-css',
			  template: `
			    <p>
			      css works!
			    </p>
			  `,
			  styles: [`
			    p{
			      color: red;
			      font-size: 20px
			    }
			    `]
			})
	3.- Se modifica app.compoents.html, se le agrega selector y se le agrega parrafo
		<div class="container main-container">
		    <h1>Demos <span>Angular</span></h1>
		    <hr>
		    <!-- <app-ng-style></app-ng-style> -->
		    <app-css></app-css>
		    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quos corrupti, ea impedit quia nesciunt sapiente, reiciendis iusto perspiciatis quidem, est incidunt modi quibusdam architecto quod aperiam esse velit commodi. Facilis.</p>
		</div>
	4.- Se le añade style al app.components.css
		p {
		    color: blue !important
		}
	5.- Se modifica style.css
		p {
		    color: green !important
		}
142.- ****************************************************************************ngClass, se añaden clases de estilos a elementos html
	1.- Se crea componente --> ng g c components/clases -is
	2.- Se modifica clases.components.ts, se crean propiedades para generar el estilos
		  alerta = 'alert-danger';
		  // utilizando obj
		  propiedades = {
		    danger: true
		  }
	3.- se modifica clases.components.html
		<div [ngClass]="alerta" class="alert" role="alert">
		    A simple primary alert—check it out!
		</div>
		<button class="btn btn-outline-success" (click)="alerta = 'alert-success'">Verde</button>
		<button class="btn btn-outline-primary" (click)="alerta = 'alert-primary'">Azul</button>
		<br><br>

		<p [ngClass]="{'text-danger': propiedades.danger, 'text-info': !propiedades.danger}">
		    Lorem ipsum dolor sit amet consectetur adipisicing elit. Amet ipsum beatae odit quos! Aut nesciunt culpa consectetur non obcaecati, vel eius quasi ducimus accusamus, laudantium reiciendis cupiditate commodi ex at!
		</p>

		<button class="btn" [ngClass]="{'btn-danger': propiedades.danger, 'btn-info': !propiedades.danger}" (click)="propiedades.danger = !propiedades.danger">Color</button>
143.- ****************************************************************************Usando procesos asincronos con indicadores de usuario
	1.- Se modifica clases.components.ts, se agrega propiedad y metodo
		loading = false;
		ejecutaProceso(): void{
		   this.loading = true;
		   setTimeout( () => { this.loading = false; }, 3000);
		  }
	2.- Se modifica clases.components.html
		<br><br>

		<h3>Proceso asincrono</h3>
		<button class="btn btn-primary" (click)="ejecutaProceso()" [disabled]="loading">
		  <i [ngClass]="{'fa-refresh fa-spin': loading, 'fa-hdd-o':!loading}"  class="fa"></i>
		  <span *ngIf="!loading">Guardar</span>
		  <span *ngIf="loading">Esperar</span>
		</button>
144.- ********************************************************************************************************Directivas Personalizadas
	1.- Se crea directiva --> ng g d directivas/resaltado
	2.- Se modifica app.components.html
		 <hr> -->
	    <!-- <h2 appResaltado>Resaltado</h2> -->
	    <h2 [appResaltado]="'blue'">Resaltado</h2>
	3.- Se modifica resaltado.directive.ts
		import { Directive, ElementRef, HostListener, Input } from '@angular/core';

		@Directive({
		  selector: '[appResaltado]'
		})
		export class ResaltadoDirective {

		  @Input("appResaltado") resaltado:string;

		  constructor( private elem: ElementRef) {
		    //Se cambia la propiedad del elemento
		    //elem.nativeElement.style.backgroundColor = "yellow";

		  }

		  //Se escucha el evento y se acciona el cambio de la propiedad
		  @HostListener('mouseenter') mouseEntro(){
		    //this.elem.nativeElement.style.backgroundColor = "yellow";
		    this.resaltar('blue');
		  }
		  @HostListener('mouseleave') mouseSale(){
		    this.elem.nativeElement.style.backgroundColor = null;
		    this.resaltar('green');
		  }

		  //Metodo que cambia la propiedad del elemento 
		  private resaltar( color:string = 'yellow'){
		    this.elem.nativeElement.style.backgroundColor = color;
		  }
		}
145.- ******************************************************************************ngSwitch - multiples opciones con una sola decision
	1.- Se crea un componente --> ng g c components/ngSwitch -is
	2.- Se modifica app.components.html
		   <app-ng-switch></app-ng-switch> 
	3.- Se modifica ng-switch.components.ts, se crea propiedades
		  alerta:string = 'danger';
 		 lista: string[] = ['danger','success','warning','secundary','primary']
 	4.- Se modifica ng-switch.components.ts
 		<select name="miselect" [(ngModel)]="alerta" class="form-select">
		    <option value="0" selected>Seleccione una opcion</option>
		    <option *ngFor="let item of lista">{{item}}</option>
		</select>
		<hr>
		<div [ngSwitch]="alerta">
		    <div *ngSwitchCase="'primary'" class="alert alert-primary" role="alert">
		        A simple primary alert—check it out!
		    </div>
		    <div *ngSwitchCase="'secundary'" class="alert alert-secondary" role="alert">
		        A simple secondary alert—check it out!
		    </div>
		    <div *ngSwitchCase="'success'" class="alert alert-success" role="alert">
		        A simple success alert—check it out!
		    </div>
		    <div *ngSwitchCase="'danger'" class="alert alert-danger" role="alert">
		        A simple danger alert—check it out!
		    </div>
		    <div *ngSwitchCase="'warning'" class="alert alert-warning" role="alert">
		        A simple warning alert—check it out!
		    </div>
		</div>
146.- **************************************************************************************************************Rutas y Rutas hijas 
	1.- Se genera componente --> ng g c components/home -it -is
	1.- se  crea componente --> ng g c components/home -it -is
	2.- Se modifica el home.components.ts, se añade al template los modulos realizados hasta Authorization
		@Component({
		  selector: 'app-home',
		  template: `
		    <app-ng-style></app-ng-style>
		    <app-css></app-css>
		    <app-clases></app-clases>
		    <h2 appResaltado>Resaltado</h2>
		    <h2 [appResaltado]="'blue'">Resaltado</h2>
		    <app-ng-switch></app-ng-switch>
		  `,
		  styles: [
		  ]
		})
	3.- Se crea archivo app.routes.ts, para añadir las rutas del proyecto
		import { RouterModule, Routes } from '@angular/router';
		import { HomeComponent } from './components/home/home.component';


		const APP_ROUTES: Routes = [
		  { path: 'home', component: HomeComponent },
		  { path: '**', pathMatch: 'full', redirectTo: 'home' },
		];


		export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES);
	4.- Se importa app.routes.ts en app.module.ts
		import { APP_ROUTING } from './app.routes';
		4.1.- Se agrega a los imports
			imports: [
			    BrowserModule,
			    FormsModule,
			    APP_ROUTING
			  ],


	5.- Se modifica app.components.html, se renderizan las rutas principales, se agrega compoente <router-outlet>
		<router-outlet></router-outlet>

	6.- Se crea componentes usuario y usuarioNuevo
		ng g c components/usuario
		ng g c components/usuario/usuarioNuevo -is -it --flat
		ng g c components/usuario/usuarioEditar -is -it --flat
		ng g c components/usuario/usuarioDetalle -is -it --flat
		ng g c components/navbar -is
	7.- Se modifica navbar.component.html, se le agrega navbar de bootstrap
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		    <a class="navbar-brand" href="#">Navbar</a>
		    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>

		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		        <ul class="navbar-nav mr-auto">
		            <li class="nav-item active">
		                <a class="nav-link" href="#">Home</a>
		            </li>
		            <li class="nav-item">
		                <a class="nav-link" href="#">Usuarios</a>
		            </li>
		        </ul>
		    </div>
		</nav>
	8.- Se modifica app.components.html, se le agrega navbar component
		<app-navbar></app-navbar>
	9.- Se modifica app.routes.ts, se agrega la ruta a usuario
		9.1.- Se importa componente usuarios
			import { UsuarioComponent } from './components/usuario/usuario.component';
		9.2.- Se agrega la ruta a usuario
			const APP_ROUTES: Routes = [
			  { path: 'home', component: HomeComponent },
			  { path: 'usuario', component: UsuarioComponent },
			  { path: '**', pathMatch: 'full', redirectTo: 'home' },
			];
	10.- Se modifica navbar.components.html, se le agregan routerLinkActive y routerlink
		<ul class="navbar-nav mr-auto">
            <li class="nav-item " routerLinkActive="active">
                <a class="nav-link" [routerLink]="['home']">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" [routerLink]="['usuario']">Usuarios</a>
            </li>
        </ul>






Seccion 22:Sección 22:Gráficas Dinámicasen Angular
335.- Instalaciones Necesarias********************************************************************************************************
	1.- Install ng2-charts using npm
		npm install --save ng2-charts
	2.- Install Chart.js library
		npm install --save chart.js
	3.- Se modifica app.module.ts, se importa componente y se añade componente a los imports
		// Graficos
		import { ChartsModule } from 'ng2-charts';
336.- Graficos Lineales**************************************************************************************************************
	1.- Se crea componente 
		ng g c components/graficos/linea 



		npm install chartjs-plugin-annotation --save
		npm install chartjs-plugin-datalabels --save
	https://www.youtube.com/watch?v=WB_QK6TqTho
	https://amoelcodigo.com/graficas-angular-ng2charts/
	***https://www.positronx.io/angular-chart-js-tutorial-with-ng2-charts-examples/


https://www.argentinasx.com/contactos/lucila
https://www.argentinasx.com/escorts/giovanna
https://www.argentinasx.com/rubro59/zoehot
http://www.argentinasensual.com/rubro59/ema.html



