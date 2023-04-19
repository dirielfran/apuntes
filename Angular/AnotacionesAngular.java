Comandos angular-cli
ng new [nombre aplicativo] 												--> Creacion de aplicativo angular
ng generate class footer.component  							--> crea clase
ng serve -o                         							--> corre en el servidor la aplicacion
ng generate component directivas    							--> crea un componente 
ng g c components/heroeTarjeta --skipTests=true  	--> Creacion de componente sin el archivo de prueba
ng g c clientes/form --flat 											--> Crea componente con el -- flat se le indica que no cree folder
ng generate service cliente         							--> Se crea clase de servicio
ng g s services/user --dry-run  									--> hace una simulacion de creacion de archivo 
ng g m miModulo																		--> Genera un modulo propio
ng g m <nombre del modulo> --routing 												--> Genera modulo con su routing
ng g g usuarios/guard/auth  											--> genera guard en ruta especificada
ng g p pipes/noimage															--> Creacion de pipe
ng build --prod 																	--> Creacion de mi carpeta de distribucion con la aplicacion mimificada 


guard 																						--> Los Guard son interceptores que sirven para controlar el acceso a las rutas
.pipe																							--> Los pipes son una herramienta de Angular que nos permite transformar visualmente la información,
																											por ejemplo, cambiar un texto a mayúsculas o minúsculas, o darle formato de fecha y hora.
Observable 																				--> Los observables brindan soporte para pasar mensajes entre partes de su aplicación. 
																											Se usan con frecuencia en Angular y son la	técnica recomendada para el manejo de eventos, 
																											la programación asincrónica y el manejo de múltiples valores. 
operador map 																			--> Este operador nos permite recibir un valor de entrada y devolver un valor diferente al que recibimos.
flatMap (mergeMap) operator 											--> flatMap, también llamado mergeMap, nos va a permitir operar con otros observables. Básicamente 
																											lo que va a hacer es transformar un Observable en otros Observables y unificar la salida de 
																											todos los Observables bajo un solo stream.
operador switchMap() operator											--> El funcionamiento de switchMap es muy similar al de flatMap pero con una diferencia muy
																											importante. Por cada nuevo ciclo del stream principal, el stream interior se detiene.
operador tap() 																		--> Realice un efecto secundario para cada emisión en la fuente Observable, pero devuelva un Observable 
																											que sea idéntico a la fuente.
operador fill() 																	--> El fill() método llena los elementos especificados en una matriz con un valor estático.
																											Puede especificar la posición de dónde comenzar y finalizar el llenado. Si no se especifica, 
																											se completarán todos los elementos.
																											Nota: este método sobrescribe la matriz original.
paramMap(Interface)																--> Un mapa que proporciona acceso a los parámetros obligatorios y opcionales específicos 
																											de una ruta. El mapa admite recuperar un solo valor con get() o varios valores con getAll().
[(ngModel)]																				--> Directiva que va a poblar desde el form a un atributo de la clase y a los atributos de este 
																											(Binding) ESTA MAPEADO AL ATRIBUTO AL FORMULARIO.
																											<input type="text" class="form-control" [(ngModel)]="cliente.apellido" name="apellido">
(ngModelChange) 																	--> ngModelChange is the @output property of ngModel directive. and it’s specific to Angular framework.
																											Where as (change) event is classic HTML DOM event, independent of Angular framework triggered when a 
																											change happened in input element.
(ngSubmit)																				--> <!--(ngSubmit) invoca un metodo de la clase-->
    																									<form (ngSubmit) ="create()">
[routerLink]																			-->	<!--[routerLink] Nos permite crear rutas internas del proyecto-->
		            																			<button class="btn btn-rounded btn-primary" type="buttom" [routerLink]="['/clientes/form']">Crear Cliente</button>
[routerLinkActiveOptions]													--> Se le indica que este activo siempre y cuando el path sea exactamente igual
																											[routerLinkActiveOptions]="{exact:true}"
[ngClass]																					--> Adds and removes CSS classes on an HTML element.
																											<li class="page-item" *ngFor="let pagina of paginas" [ngClass]="pagina-1 == paginador.number?'disabled':''">
[ngValue]																					--> [ngValue] permite enlazar un objeto complejo. De esta manera al seleccionar el nombre de la ciudad 
																											desplegaremos el objeto entero. 
<ng-template>																			-->	<!--Se agrega directiva ngIf, si no existe cliente coloca boton crear en caso contrario se utiliza template-->
											                    						<button class="btn btn-primary" role="button" (click)='create()' *ngIf="!cliente.id else elseBlock">Crear</button>

																	                    <ng-template #elseBlock>
																	                        <button class="btn btn-primary" role="button" (click)='update()'>Editar</button>
																	                    </ng-template>
*ngFor                              							--> directiva estructural para recorrer una lista
																											<li class="list-group-item" *ngFor="let curso of listaCursos">
*ngIf																							--> directiva estructural condicional if
																											<ul class="list-group" *ngIf="habilitar==true">
@Input																						--> Los inputs nos permiten pasar valores desde el componente «padre» que utiliza dentro del el 
																											otro componente y así intercambiar datos entre estos dos componentes.
EventEmitter 																			--> Úselo en componentes con la directiva para emitir eventos personalizados de forma sincrónica 
																											o asincrónica, y registre controladores para esos eventos suscribiéndose a una instancia.
funcion btoa(JavaScript)  												-->Convertir a base64 en JavaScript
																											De esto se encarga la función btoa. Quiere decir “Binary to ascii” y bueno, convierte datos 
																											binarios o cualquier tipo de dato a una representación imprimible.
funcion atob(JavaScript) 													--> Decodificar base64 en JavaScript
																											Ahora entra la función atob, que quiere decir “ASCII to binary”. Recibe una cadena que está 
																											codificada con base64 y devuelve los datos binarios.
Almacenar en el sessioStorage 										--> sessionStorage.setItem('usuarios', JSON.stringify(this._usuario));
Convertir un objeto en json 											--> JSON.stringify(this._usuario)
cambio de puerto 																	--> ng serve --port 4401 

**	Diferentes formas de path en el RouterModule:
  //{ path: 'path/:routeParam', component: MyComponent },
  //{ path: 'staticPath', component: ... },
  //{ path: '**', component: ... },
  //{ path: 'oldPath', redirectTo: '/staticPath' },
  //{ path: ..., component: ..., data: { message: 'Custom' }
** Atajos de angular
	*ngclick 							--> Se agrega evento click en html 

**********************************************************Angular Fit*****************************************************************
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
			1.- Se ejecut l instalacion
				npm install animate.css --save
			2.- Se modifica angular.json, se le agrega a style
				"node_modules/animate.css/animate.min.css"
			3.- Se modifica style.css
				@import '~animate.css/animate.min';
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
		***********************************************************************************************ActivatedRoute para recibir parametros 
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
			Utilizar otros idiomas  --> https://medium.com/angular-chile/aplicaciones-multilenguaje-en-angular-7-con-ngx-translate-db8d1e7b380c
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
				export class HeroesModule { }+
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
		184. ********************************************************************************Heroes Service - Traer información de los héroes
			1.- Se crea servicio dentro de heroes 
				ng g s heroes/services/heroes --skipTests
			2.- Se modifica appmodule.ts, se importa HttpClientModule
				import { NgModule } from '@angular/core';
				import { BrowserModule } from '@angular/platform-browser';


				import { AppRoutingModule } from './app-routing.module';

				import { AppComponent } from './app.component';
				import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
				import { ErrorPageComponent } from './shared/error-page/error-page.component';
				import { HttpClientModule }from '@angular/common/http'
				import { MaterialModule } from './material/material.module';


				@NgModule({
				  declarations: [
				    AppComponent,
				    ErrorPageComponent
				  ],
				  imports: [
				    BrowserModule,
				    HttpClientModule,
				    BrowserAnimationsModule,
				    AppRoutingModule
				  ],
				  providers: [],
				  bootstrap: [AppComponent]
				})
				export class AppModule { }
			3.- Se modifica heroes.service.ts.
				3.1.- Se realiza injeccion de dependencia de HttpClient 
					constructor( private http: HttpClient ) { }
				3.2.- Se crea metodo que devuelve un observable con los datos
					  getHeroes(){
					    return this.http.get("http://localhost:3000/heroes");
					  }
			4.- Se modifica listado.component.ts
				4.1.- Se realiza injeccion de dependencia del servicio heroeService
					constructor( private heroesServices: HeroesService) { }
				4.2.- Se modifica el metodo ngOnInit(), para subscribirse al metodo del servicio getHeroes() y traer la data
					  ngOnInit(): void {
					    this.heroesServices.getHeroes().subscribe( resp => console.log(resp));
					  }
		*************************************************************************************************************************************
		185. ******************************************************************************************************************Interfaz Héroe
			1.- Se crea interface Heroe 
				1.1.- Se consulta la respuesta de la data del servidor por postman -->  http://localhost:3000/heroes, se copia la data y 
				se pega en la herramienta --> https://app.quicktype.io/ para que genere una interface
				1.2.- Se crea dentro de heroes, folder interfaces y dentro la interface heroe.interface.ts 
				1.3.- Se modifica heroe.interface.ts, se pega el resultado de la herramenta quicktype
					export interface Heroe {
					    id?:              string;
					    superhero:        string;
					    publisher:        Publisher;
					    alter_ego:        string;
					    first_appearance: string;
					    characters:       string;
					    alt_img:          string;
					}

					export enum Publisher {
					    DCComics = "DC Comics",
					    MarvelComics = "Marvel Comics",
					}
			2.- Se modifica heroes.service.ts, se modifica metodo getHeroes() para colocar el tipado 
					  getHeroes(){
					    return this.http.get<Heroe[]>("http://localhost:3000/heroes");
					  }
			3.- Se modifica listado.component.ts
				3.1.- Se crea atributo de tipo Heroe[] 
					heroes: Heroe[] = [];
				3.2.- Se modifica metodo ngoninit(), se le añade la respuest del subscribe al atributo heroes 
					  ngOnInit(): void {
						    this.heroesServices.getHeroes().subscribe( resp => this.heroes = resp );
						  }
			4.- Se modifica listado.component.html, se genera lista de heroes 
				<ul>
				    <li *ngFor="let heroe of heroes">{{heroe.superhero}}</li>
				</ul>
		*************************************************************************************************************************************
		186. *****************************************************************************************************Material Card - Flex Layout
		************************************************************************************************************************MatCardModule
			1.- Se modifica material.module.ts, se importa MatCardModule
				import { NgModule } from '@angular/core';
				import {MatToolbarModule} from '@angular/material/toolbar';
				import {MatSidenavModule} from '@angular/material/sidenav';
				import {MatIconModule} from '@angular/material/icon';
				import {MatButtonModule} from '@angular/material/button';
				import {MatListModule} from '@angular/material/list';
				  import {MatCardModule} from '@angular/material/card';

				@NgModule({
				  exports:[
				    MatToolbarModule,
				    MatSidenavModule,
				    MatButtonModule,
				    MatListModule,
				    MatIconModule,
				    MatCardModule
				  ],
				  imports:[
				  ]
				})
				export class MaterialModule { }
			2.- Se modifica listado.component.html
				<h1>Listado Heroes</h1>
				<mat-divider></mat-divider>
				<br>
				<div fxLayout="row wrap">
				    <div fxFlex="20">
				        <mat-card *ngFor="let heroe of heroes">
				            <mat-card-header>
				                <mat-card-title>{{heroe.superhero}}</mat-card-title>
				                <mat-card-subtitle>{{heroe.alter_ego}}</mat-card-subtitle>
				            </mat-card-header>
				            <img matCardImage src="assets/heroes/{{heroe.id}}.jpg">
				            <mat-card-content>
				                <h3>{{heroe.publisher}}</h3>
				                <p>
				                    <strong>Primera Aparicion: </strong>{{heroe.first_appearance}}
				                    <br>
				                    <strong>Personajes: </strong>{{heroe.characters}}
				                </p>
				            </mat-card-content>
				            <mat-card-actions align="start">
				                <button mat-button color="warn">Leer mas...</button>
				                <button mat-button color="info">Editar</button>
				            </mat-card-actions>
				            <mat-card-footer>
				                Footer
				            </mat-card-footer>
				        </mat-card>    
				    </div>
				</div>+
		*************************************************************************************************************************************
		187. *******************************************************************************************Flex Layout - Diferentes resoluciones
			1.- Se modifica listado.component.html, se agregan diferentes resoluciones para diferentes tamaños de pantalla con los fxLayout
				<h1>Listado Heroes</h1>
					<mat-divider></mat-divider>
					<br>
					<div fxLayout="row wrap" 
					    fxLayoutAlign="center" 
					    fxLayoutGap="20px"
					    fxLayout.xs="column">
					    <div *ngFor="let heroe of heroes"
					        fxFlex="20"
					        fxFlex.lg = "15"
					        fxFlex.sm = "30">
					        <mat-card >
					            <mat-card-header>
					                <mat-card-title>{{heroe.superhero}}</mat-card-title>
					                <mat-card-subtitle>{{heroe.alter_ego}}</mat-card-subtitle>
					            </mat-card-header>
					            <img matCardImage src="assets/heroes/{{heroe.id}}.jpg">
					            <mat-card-content>
					                <h3>{{heroe.publisher}}</h3>
					                <p>
					                    <strong>Primera Aparicion: </strong>{{heroe.first_appearance}}
					                    <br>
					                    <strong>Personajes: </strong>{{heroe.characters}}
					                </p>
					            </mat-card-content>
					            <mat-card-actions align="start">
					                <button mat-button color="warn">Leer mas...</button>
					                <button mat-button color="info">Editar</button>
					            </mat-card-actions>
					        </mat-card>    
					    </div>
					    
					</div>
			2.-  Se modifica listado.component.ts, se agrega margen css en el decorator
				@Component({
					  selector: 'app-listado',
					  templateUrl: './listado.component.html',
					  styles: [`
					    mat-card{
					      margin-top: 20px
					    }
					  `  ]
					})
		*************************************************************************************************************************************
		188. ***************************************************************************************************Tarea - HeroeTarjetaComponent
			1.- Se genera componente dentro de heroe
				ng g c heroes/components/HeroeTarjeta --skipTests
			2.- Se modifica listado.component.html, se corta el mat-card, y se agrega componente app-heroe-tarjeta
				<h1>Listado Heroes</h1>
				<mat-divider></mat-divider>
				<br>
				<div fxLayout="row wrap" 
				    fxLayoutAlign="center" 
				    fxLayoutGap="20px"
				    fxLayout.xs="column">
				    <div *ngFor="let heroe of heroes"
				        fxFlex="20"
				        fxFlex.lg = "15"
				        fxFlex.sm = "30">
				        <app-heroe-tarjeta [heroe]="heroe"></app-heroe-tarjeta> 
				    </div>    
				</div>
			3.- Se modifica heroe-tarjeta.component.html y se peca el mat-card, se le agrega como atributo input el heroe 
				<mat-card >
				    <mat-card-header>
				        <mat-card-title>{{heroe.superhero}}</mat-card-title>
				        <mat-card-subtitle>{{heroe.alter_ego}}</mat-card-subtitle>
				    </mat-card-header>
				    <img matCardImage src="assets/heroes/{{heroe.id}}.jpg">
				    <mat-card-content>
				        <h3>{{heroe.publisher}}</h3>
				        <p>
				            <strong>Primera Aparicion: </strong>{{heroe.first_appearance}}
				            <br>
				            <strong>Personajes: </strong>{{heroe.characters}}
				        </p>
				    </mat-card-content>
				    <mat-card-actions align="start">
				        <button mat-button color="warn">Leer mas...</button>
				        <button mat-button color="info">Editar</button>
				    </mat-card-actions>
				</mat-card>   
			4.- Se modifica heroes-tarjeta.component.ts, se agrega atributo @intut 
				@Input() heroe!: Heroe;	
		*************************************************************************************************************************************
		189. **************************************************************************************************************Tarea - PipeImagen
			1.- Se crea pipe perzonalizado para imagen  --> ng g p heroes/pipes/imagenPip
			2.- Se modifica imagen-pipe.pipe.ts, se modifica metodo transform()
				  transform(heroe: Heroe): unknown {
				    return `assets/heroes/${heroe.id}.jpg`;
				  }
			3.- Se modifica heroe-tarjeta.component.html, se utiliza pipe para renderizar la imagen 
				<img matCardImage [src]="heroe | imagenPipe">
		*************************************************************************************************************************************
		190. ***********************************************************************************************Tarea - Ruta Héroe y Editar Héroe
		************************************************************************************activatedRoute para obtener parametro del request
			1.- Se modifica heroe-tarjeta.component.html, se agregan las rutas a los botones
				<button mat-button color="warn" [routerLink] = "['/heroes',heroe.id]">Leer mas...</button>
		        <button mat-button color="info" [routerLink] = "['/heroes/editar', heroe.id]">Editar</button>
		    2.- Se modifica heroe.component.ts, se modifica ngOnInit, se recupera parametro con activatedRoute
		    	  constructor( private activatedRoute: ActivatedRoute) { }

				  ngOnInit(): void {
				    this.activatedRoute.params.subscribe( ({id}) => console.log( id ))
				  }	    
		*************************************************************************************************************************************
		191. ***************************************************************************************************************Pantalla de Héroe
		***********************************************************************************************************switchMa de rxjs/operators
			1.-  Se modifica heroes.service.ts, se crea metodo que devuelve observable con la data de heroe 
				  getHeroe(id:string): Observable<Heroe>{
				    return this.http.get<Heroe>(`http://localhost:3000/heroes/${id}`);
				  }
			2.- Se modifica el heroe.compoent.ts, se modifica ngoninit(), se trabaja con el switchMap de rxjs/operators para convertir el 
				observeble en el observable que recuperamos del servicio 
				2.1.- Se crea atributo de tipo Heroe
					heroe!: Heroe;

				2.2.- Se modifica ngOnInit(), se llama al metodo de servicio getHeroe()
				  ngOnInit(): void {
				    this.activatedRoute.params.pipe(
				      switchMap( ({id}) => {
				        return this.heroeService.getHeroe(id)
				      })
				    ).subscribe( hero => {
				      console.log(hero);
				      this.heroe = hero;
				    })
				  }
			3.- Se modifica heroe.component.html, se renderiza spinner y template con validacion si existe o no Heroe
				//<mat-grid-list cols="2" *ngIf="!heroe; else divHeroe">
				    <mat-grid-tile>
				        <mat-spinner></mat-spinner>
				    </mat-grid-tile>
				</mat-grid-list>

				<ng-template #divHeroe>
				    {{ heroe | json }}
				</ng-template>
		*************************************************************************************************************************************
		192. **************************************************************************************************Diseño de la pantalla de Héroe
			1.- Se modifica heroe.component.html, se renderiza informacion del heroe 
				<ng-template #divHeroe>
				    <div fxLayout="row"
				            fxLayout.xs="column"
				            fxLayoutGap="30px">
				        <div fxFlex="50">
				            <h1>{{heroe.superhero}}<small>{{heroe.alter_ego}}</small></h1>
				            <mat-divider></mat-divider>
				            <br>
				            <img [src]="heroe | imagenPipe" >
				        </div>
				        <div fxFlex="50">
				            <h1>{{heroe.publisher}}</h1>
				            <mat-divider></mat-divider>
				            <br>
				            <mat-list>
				                <mat-list-item>{{ heroe.first_appearance }}</mat-list-item>
				                <mat-list-item>{{ heroe.characters }}</mat-list-item>
				                <mat-list-item>{{ heroe.publisher }}</mat-list-item>
				                <mat-list-item>{{ heroe.alter_ego }}</mat-list-item>
				            </mat-list>
				            <button mat-button (click)="regresar()" color="warm">Regresar</button>
				        </div>
				    </div>
				</ng-template>
			2.- Se modifica heroe.component.ts, se agrega style al decorador, para que la imagen tenga un 100%  y se modifican las puntas
				@Component({
				  selector: 'app-heroe',
				  templateUrl: './heroe.component.html',
				  styles: [`
				    img{
				      width: 100%;
				      border-radius: 5px;
				    }
				  `
				  ]
				})
			3.- Se modifica heroe-tarjeta.component.css, se agrega margen a las cards 
				mat-card{

				    margin-top: 20px
				}
		*************************************************************************************************************************************
		193. ************************************************************************************************************Variables de entorno
			1.- Se modifica environments.ts (test), se le agrega urlBase
				export const environment = {
				  production: false,
				  baseUrl: 'http://localhost:3000'
				};
			2.- Se modifica environments.prod.ts(produccion), se agrega urlBase de produccion
				export const environment = {
				  production: true,
				  baseUrl: 'http://prueba.com/api'
				};
			3.- Se modifica heroes.service.ts, se agrega el manejo de url por variable de entorno
				3.1.- Se crea variable que toma url del environment 
					private baseUrl: string = environment.baseUrl;
				3.2.- Se modifica metodos para que tomen parte de la url del environments
					  getHeroes(){
					    return this.http.get<Heroe[]>(`${this.baseUrl}/heroes`);
					  }

					  getHeroe(id:string): Observable<Heroe>{
					    return this.http.get<Heroe>(`${this.baseUrl}/heroes/${id}`);
					  }+++++
		*************************************************************************************************************************************
		194. ***********************************************************************************************************Material Autocomplete
			1.-  Se modifica material.module.ts, se importan librerias de angular material necesarias para el autocomplete 
				y se incluyen en los exports
				import {MatAutocompleteModule} from '@angular/material/autocomplete';
				import { MatFormFieldModule } from '@angular/material/form-field';
				import { MatInputModule } from '@angular/material/input';

				@NgModule({
				  exports:[
				    MatToolbarModule,
				    MatSidenavModule,
				    MatButtonModule,
				    MatListModule,
				    MatIconModule,
				    MatCardModule,
				    MatProgressSpinnerModule,
				    MatGridListModule,
				    MatAutocompleteModule,
				    MatFormFieldModule,
				    MatInputModule
				  ],
			2.- Se modifica heroes.module.ts, se importa FormsModule de @angular/forms 
				import { MaterialModule } from '../material/material.module';

				@NgModule({
				  declarations: [AgregarComponent, BuscarComponent, HeroeComponent, HomeComponent, ListadoComponent, HeroeTarjetaComponent, ImagenPipePipe],
				  imports: [
				    CommonModule,
				    FlexLayoutModule,
				    MaterialModule,
				    HeroesRoutingModule,
				    FormsModule
				  ]
				})
				export class HeroesModule { }
			3.- Se modifica buscar.component.html, se enderiza el autocomplete de ejemplo de la documentacion de AngularMaterial
				<div fxLayout="column">
				    <div>
				        <h1>Buscar Heroes</h1>
				        <mat-divider></mat-divider>
				    </div>
				    <div>
				        <h3>Buscador</h3>
				        <mat-form-field >
				            <mat-label>Number</mat-label>
				            <input type="text"
				                    placeholder="Pick one"
				                    aria-label="Number"
				                    matInput
				                    [(ngModel)]="termino"
				                    [matAutocomplete]="auto" 
				                    (input)="buscando()">
				            <mat-autocomplete autoActiveFirstOption 
				                    #auto="matAutocomplete"
				                    (optionSelected)="opcionSeleccionada( $event )">
				            <mat-option *ngFor="let heroe of heroes" [value]="heroe">
				                {{heroe.superhero}}
				            </mat-option>
				            </mat-autocomplete>
				        </mat-form-field>
				    </div>
				    <pre>
				        {{heroeSeleccionado | json}}
				    </pre>
				</div>

			4.- Se modifica buscar.componets.ts
				4.1.- Se agregan atributo para el autocomplete
					termino: string = '';
		  			heroes: Heroe[] = [];
		  			heroeSeleccionado!: Heroe;
		  		4.2.- Se crea metodo buscando() y opcionSeleccionada(), se encarga de traer la data al uutocomplete
		  			  buscando(){
					    this.heroeService.getSugerencias( this.termino ).subscribe( heroes => this.heroes = heroes);
					  }

					  opcionSeleccionada( event: MatAutocompleteSelectedEvent ){
					    const hero: Heroe = event.option.value;
					    this.termino =  hero.superhero;
					    this.heroeService.getHeroe( hero.id! ).subscribe( heroe => this.heroeSeleccionado = heroe);
					    console.log( hero );
					  }
			5.- Se modifica heroes.service.ts, se crea metodo getSugerencias()
				  getSugerencias( termino:string){
				    return this.http.get<Heroe[]>(`${this.baseUrl}/heroes?q=${termino}&_limit=5`);
				  }
		*************************************************************************************************************************************
		196. ************************************************************************************Tarea - Autocomplete cuando no encontró nada
			1.- Se modifica buscar.component.html
				1.1.- Se agrega mat-option, con mensaje en caso de que no se encuentren elementos
					<mat-option value="" *ngIf=" heroes.length === 0 && termino.trim().length > 0">
		                No se encontro nada con el termino {{ termino }}
		            </mat-option>
		        1.2.- Se agrega componente app-heroe-tarjeta, en caso de que exista heroeSeleccionado
		        	<div *ngIf="heroeSeleccionado">
				        <app-heroe-tarjeta [heroe]="heroeSeleccionado"></app-heroe-tarjeta>
				    </div>
			2.- Se modifia buscar.component.ts, metodo opcionSeleccionada(), se valida si existe valor en el evento opcionSeleccionada()
				opcionSeleccionada( event: MatAutocompleteSelectedEvent ){
				    if(!event.option.value){
				      console.log('No hay valor');
				      this.heroeSeleccionado = undefined;
				      return;
				    }
				    const hero: Heroe = event.option.value;
				    this.termino =  hero.superhero;
				    this.heroeService.getHeroe( hero.id! ).subscribe( heroe => this.heroeSeleccionado = heroe);
				    console.log( hero );
				  } ++
		*************************************************************************************************************************************
	************************************************************* Fin Seccion ***********************************************************
	**********************************Sección 14: HeroesApp - CRUD (Continuación con Angular Material)***********************************
		203. ***************************************************************************************Diseño de la pantalla para agregar héroes
			1.- Se modifica material.module.ts, se agrega import de modulo
				import { MatSelectModule } from '@angular/material/select';

				exports:[
				    MatToolbarModule,
				    MatSidenavModule,
				    MatButtonModule,
				    MatListModule,
				    MatIconModule,
				    MatCardModule,
				    MatProgressSpinnerModule,
				    MatGridListModule,
				    MatAutocompleteModule,
				    MatFormFieldModule,
				    MatInputModule,
				    MatSelectModule
				  ],
			2.- Se modifica agregar.component.ts, se agregan atributos publishers para el select y uno de tipo heros 
				publishers = [
				    {
				      id: 'DC Comics',
				      desc: 'Dc - Comics'
				    },   
				    {
				      id: 'Marvel Comics',
				      desc: 'Marvel - Comics'
				    }
				  ]

				  heroe: Heroe = {
				    superhero : '',
				    alter_ego : '',
				    characters : '',
				    first_appearance: '',
				    publisher: Publisher.DCComics,
				    alt_img: ''
				  }
			3.- Se modifica agregar.components.htm , se renderiza el formulario
				<h1>Nuevo heroe <small>ASD</small></h1>
				<div fxLayout="row" fxLayoutGap="40px">
				    <div fxFlex="50" fxLayout="column">
				        <div fxLayout="row" fxLayoutGap="20px">
				            <mat-form-field fxFlex="100"> 
				                <mat-label>Super Heroe</mat-label>
				                    <input matInput 
				                           type="text"
				                           required
				                           placeholder="">
				            </mat-form-field>
				            <mat-form-field fxFlex="100"> 
				                <mat-label>Alter Ego</mat-label>
				                    <input matInput 
				                           type="text"
				                           required
				                           placeholder="">
				            </mat-form-field>
				        </div>
				        <mat-form-field > 
				            <mat-label>Primera Aparicion</mat-label>
				                <input matInput 
				                       type="text"
				                       required
				                       placeholder="">
				        </mat-form-field>

				        <mat-form-field > 
				            <mat-label>Personajes</mat-label>
				                <input matInput 
				                       type="text"
				                       required
				                       placeholder="">
				        </mat-form-field>

				        <mat-form-field>
				            <mat-label>Creador</mat-label>
				            <mat-select  placeholder="">
				                <mat-option  *ngFor="let publisher of publishers" [value]="publisher.id">
				                    {{publisher.desc}}
				                </mat-option>
				            </mat-select>
				        </mat-form-field>

				        <mat-form-field > 
				            <mat-label>Photo Url</mat-label>
				                <input matInput 
				                       type="url"
				                       required
				                       placeholder="">
				        </mat-form-field>
				        <br>
				        <button mat-raised-button color="primary">
				            <mat-icon class="mat-18">edit</mat-icon>
				            Guardar
				        </button>

				    </div>
				    <div fxFlex="50">
				        <img [src]=" heroe | imagenPipe " alt="">
				    </div>
				</div>
		*************************************************************************************************************************************
		204. *******************************************************************************************************Insertar en base de datos
			1.- Se modifica agregar.component.html, se le agregan fxLayout.xs="column", para el responsive en pantallas muy pequeñas, 
			se le añade los ngModel para el databinding con el objeto heroe, se le agrega evento click al boton
				<h1>Nuevo heroe <small>ASD</small></h1>
				<div fxLayout="row" 
				    fxLayoutGap="40px"
				    fxLayout.xs="column">

				    <div fxFlex="50" 
				        fxLayout="column">

				        <div fxLayout="row" 
				            fxLayoutGap="20px" 
				            fxLayout.xs="column">

				            <mat-form-field fxFlex="100" fxLayout.xs="column"> 
				                <mat-label>Super Heroe</mat-label>
				                    <input matInput 
				                           type="text"
				                           required
				                           placeholder=""
				                           [(ngModel)]="heroe.superhero">
				            </mat-form-field>

				            <mat-form-field fxFlex="100"> 
				                <mat-label>Alter Ego</mat-label>
				                    <input matInput 
				                           type="text"
				                           required
				                           placeholder=""
				                           [(ngModel)]="heroe.alter_ego">
				            </mat-form-field>
				        </div>
				        <mat-form-field > 
				            <mat-label>Primera Aparicion</mat-label>
				                <input matInput 
				                       type="text"
				                       required
				                       placeholder=""
				                       [(ngModel)]="heroe.first_appearance">
				        </mat-form-field>

				        <mat-form-field > 
				            <mat-label>Personajes</mat-label>
				                <input matInput 
				                       type="text"
				                       required
				                       placeholder=""
				                       [(ngModel)]="heroe.characters">
				        </mat-form-field>

				        <mat-form-field>
				            <mat-label>Creador</mat-label>
				            <mat-select  placeholder="" [(ngModel)]="heroe.publisher">
				                <mat-option  *ngFor="let publisher of publishers" [value]="publisher.id">
				                    {{publisher.desc}}
				                </mat-option>
				            </mat-select>
				        </mat-form-field>

				        <mat-form-field > 
				            <mat-label>Photo Url</mat-label>
				                <input matInput 
				                       type="url"
				                       required
				                       placeholder=""
				                       [(ngModel)]="heroe.alt_img">
				        </mat-form-field>
				        <br>
				        <button mat-raised-button 
				            color="primary"
				            (click)="guardar()">
				            <mat-icon class="mat-18">edit</mat-icon>
				            Guardar
				        </button>

				    </div>
				    <div fxFlex="50">
				        <img [src]=" heroe | imagenPipe " alt="">
				    </div>
				</div>
			2.- Se modifica heroes.service.ts, se agrega metodo guardar 
				  postHeroe( heroe: Heroe): Observable<Heroe>{
				    return this.http.post<Heroe>(`${this.baseUrl}/heroes`, heroe);
				  }
			3.- Se modifica agregar.component.ts, se crea metodo guardar()
				  guardar(){
				    if(this.heroe.superhero.trim().length === 0){
				      return;
				    }
				    this.heroeService.postHeroe(this.heroe).subscribe( resp => console.log(resp));

				  }
		*************************************************************************************************************************************
		205. *******************************************************************************************************************Editar héroes
			1.- Se modifica heroes.service.ts, se crea metodo para modificar heroe 
				  putHeroe(heroe: Heroe): Observable<Heroe>{
				    return this.http.put<Heroe>(`${this.baseUrl}/heroes/${heroe.id}`, heroe);
				  }
			2.- Se modifica agregar.componet.ts 
				2.1.- Se realiza injeccion de dependencia de Router y activatedRoute
					  constructor( private heroeService: HeroesService,
		                private activatedRoute: ActivatedRoute,
		                private router: Router) { }
				2.2.- Se modifica el metodo ngOnInit() para que recupere el id en caso de existir
					  ngOnInit(): void {
					    this.activatedRoute.params.pipe(
					      switchMap( ({id}) => this.heroeService.getHeroe(id))
					    ).subscribe( heroe => this.heroe = heroe );
					  }
				2.3.- Se modifica metodo guardar() para que actualice o guarde en caso de que exista id 
					  guardar(){
					    if(this.heroe.superhero.trim().length === 0){
					      return;
					    }
					    if(this.heroe.id){
					      this.heroeService.putHeroe(this.heroe).subscribe( heroe => this.router.navigate(['/editar',heroe]));
					    }else{
					      this.heroeService.postHeroe(this.heroe).subscribe( resp => console.log(resp));
					    }

					  }
		*************************************************************************************************************************************
		206. ***********************************************************************************************Excepciones en nuestro ImagenPipe
		***************************************************************************************************this.router.url.includes('editar')
			1.- Se modifica imagen-pipe.pipe.ts, se agregan validaciones de imagen 
				transform(heroe: Heroe): unknown {
				    if(!heroe.id && !heroe.alt_img){
				      return 'assets/heroes/no-image.jpg';
				    }else if(heroe.alt_img){
				      return heroe.alt_img;
				    }else{
				      return `assets/heroes/${heroe.id}.jpg`;
				    }
				  }
			2.- Se modifica agregar.component.ts, se modifica metodo ngoninit() para que valide la url si es de editar no busca la imagen 
				ngOnInit(): void {
				    if(!this.router.url.includes('editar')){
				      return;
				    }
				    this.activatedRoute.params.pipe(
				      switchMap( ({id}) => this.heroeService.getHeroe(id))
				    ).subscribe( heroe => this.heroe = heroe );
				  }
			3.- Se modifica assets, se agrega imagen no-image.jpg en heroes
		*************************************************************************************************************************************
		207. **************************************************************************************************************Eliminar registros
			1.- Se modifica heroes.service.ts, se agrega metodo para eliminar heroes
				  deleteHeroe(id: string): Observable<any>{
				    return this.http.delete<any>(`${this.baseUrl}/heroes/${id}`);
				  }	
			2.- Se modifica agregar.component.ts
				2.1.- Se crea metodo para eliminar que llame al servicio
					  eliminar(){
					    this.heroeService.deleteHeroe(this.heroe.id!).subscribe( resp => this.router.navigate(['/heroes']) );
					  }
				2.2.- Se agrega margen al botton, en el decorator
					@Component({
					  selector: 'app-agregar',
					  templateUrl: './agregar.component.html',
					  styles: [`
					    img {
					      width: 100%;
					      border-radius: 5px
					    }
					    button{
					      margin-left: 10px
					    }
					  `]
					})
			3.- Se modifica agregar.component.html, se agrega boton de eliminar 
				<div>
		            <button mat-raised-button 
		                color="primary"
		                (click)="guardar()">
		                <mat-icon class="mat-18">edit</mat-icon>
		                {{(!heroe.id)?'Guardar':'Editar'}}
		            </button>
		            <button mat-raised-button
		                    *ngIf="heroe.id"
		                    color="warn"
		                    (click)="eliminar()">Eliminar</button>   
		        </div>  	 	
		*************************************************************************************************************************************
		209. ***************************************************************************************************************Material Snackbar
		**************************************************************************************************************************MatSnackBar
			1.- Se modifica material.module.ts, se agrega export de  MatSnackBarModule, para mostrar mensaje informativo

				import {MatSnackBarModule} from '@angular/material/snack-bar';	

				@NgModule({
				  exports:[
				    MatToolbarModule,
				    MatSidenavModule,
				    MatButtonModule,
				    MatListModule,
				    MatIconModule,
				    MatCardModule,
				    MatProgressSpinnerModule,
				    MatGridListModule,
				    MatAutocompleteModule,
				    MatFormFieldModule,
				    MatInputModule,
				    MatSelectModule,
				    MatSnackBarModule
				  ],
				  imports:[
				  ]
				})
				export class MaterialModule { }
			2.- Se modifica agregar.component.ts
				2.1.- Se crea metodo para despliegue de snackBar 
					2.1.1.- Se crea injeccion de dpendencia de MatSnackBar 
						 constructor( private heroeService: HeroesService,
			                private activatedRoute: ActivatedRoute,
			                private router: Router,
			                private _snackBar: MatSnackBar) { }
					2.1.2.- Se crea metodo mostrarSnackBar 
						  mostrarSnackBar(mensaje: string){
						    this._snackBar.open(mensaje, 'Ok!', {
						      duration: 2500
						    });
						  }
				2.2.- Se modifica metodo guardar(), para mostrar mensaje de exito 
					  guardar(){
					    if(this.heroe.superhero.trim().length === 0){
					      return;
					    }
					    if(this.heroe.id){
					      this.mostrarSnackBar('Heroe Modificado con exito');
					      this.heroeService.putHeroe(this.heroe).subscribe( heroe => this.router.navigate(['/heroes']));
					    }else{
					      this.mostrarSnackBar('Heroe se guardo con exito');
					      this.heroeService.postHeroe(this.heroe).subscribe( resp => this.router.navigate(['/heroes']));
					    }

					  }
		*************************************************************************************************************************************
		210. *****************************************************************************************************************Material Dialog
		**********************************************************************************************************************MatDialogModule
			1.- Se modifica material.module.ts, se exporta MatDialogModule
				import {MatDialogModule} from '@angular/material/dialog';
				@NgModule({
				  exports:[
				    MatToolbarModule,
				    MatSidenavModule,
				    MatButtonModule,
				    MatListModule,
				    MatIconModule,
				    MatCardModule,
				    MatProgressSpinnerModule,
				    MatGridListModule,
				    MatAutocompleteModule,
				    MatFormFieldModule,
				    MatInputModule,
				    MatSelectModule,
				    MatSnackBarModule,
				    MatDialogModule
				  ],
				  imports:[
				  ]
				})
				export class MaterialModule { }
			2.- Se modifica agregar.component.ts, se modifica metodo eliminar para agregarle dialog 
				2.1.- Se raliza la injeccion de dependencia de MatDialog 
					constructor( private heroeService: HeroesService,
		                private activatedRoute: ActivatedRoute,
		                private router: Router,
		                private _snackBar: MatSnackBar,
		                private _Dialog: MatDialog) { }
				2.2.- S modifica etodo eliminar() 
					eliminar(){
					    this._Dialog.open(ConfirmDialogComponent)
					    this.heroeService.deleteHeroe(this.heroe.id!).subscribe( resp => this.router.navigate(['/heroes']) );
					  }
			3.- Se crea modulo confirmDialog 	
				ng g c heroes/components/confirmDialog --skipTests

			4.- Se modifica confirm-dialog.component.ts 
				4.1.- Se realiza injeccion de dependencia de  MatDialogRef pasandole como referencia el componente ConfirmDialogComponent
					 constructor( private dialogRef: MatDialogRef<ConfirmDialogComponent> ) { }
				4.2.- Se   crean metodo para crear y para elminar
				  eliminar(){
				    this.dialogRef.close();
				  }

				  cancelar(){
				    this.dialogRef.close();
				  }
			5.- Se modifica confirm-dialog.component.html, se renderiza el dialog
				<h1>¿Estas Seguro?</h1>
				<hr>
				<p>Estas a punto de elimiar un heroe.
				    <br>
				    ¿Desea Continuar?
				</p>

				<div>
				    <button mat-button (click)="eliminar()" color="warn">Eliminar</button>
				    <button mat-button (click)="cancelar()">Cancelar</button>
				</div>
		*************************************************************************************************************************************
		211. *********************************************************************************************Información desde y hacia el dialog
		**************************************************************************************************************@Inject(MAT_DIALOG_DATA
		****************************************************************************************************************************MatDialog
		*************************************************************************************************MatDialogRef<ConfirmDialogComponent>
			1.- Se modifica agregar.component.ts
				1.1.- Se realiza injeccion de dependencia de MatDialog
					  constructor( private heroeService: HeroesService,
		                private activatedRoute: ActivatedRoute,
		                private router: Router,
		                private _snackBar: MatSnackBar,
		                private _dialog: MatDialog) { }
				1.2.- Se odifica metodo eliminar()
					  eliminar(){
					  	//se utiliza metodo open() de obj MatDialog, se le pasa el componente que rendirazara, y un objeto con el ancho
					  	//y la data, ... son para que cree otra referencia de heroe, se obtiene un obj MatDialogRef
					    const dialog = this._dialog.open(ConfirmDialogComponent, {
					      width: '250px',
					      data: {...this.heroe}
					    })

					    //se subscribe al metodo afterClosed del objeto MatDialog y si trae como respuesta true(confirmacion) se llama al 
					    //metodo del servicio que borra un heroe 
					    dialog.afterClosed().subscribe(
					      (result ) => {
					        if (result) {
					          this.heroeService.deleteHeroe(this.heroe.id!).subscribe( resp => this.router.navigate(['/heroes']) );
					        }
					      }
					    )
					  }
			2.- Se modifica confirm-dialog.component.ts, se recibe l data por medio del @Inject(MAT_DIALOG-DATA) en el constructor
				  constructor( private dialogRef: MatDialogRef<ConfirmDialogComponent>,
		               			 @Inject(MAT_DIALOG_DATA) public data: Heroe  ) { }

				2.1.- Se modifica metodo eliminar(), se le pasa por argumento true al metodo close del dialogRef, este es el que recibe el 
				 metodo eliminar en  agregar.component  
				 	  eliminar(){
					    this.dialogRef.close(true);
					  }

			3.- Se modifica confirm-dialog.component.html, se agrega referencia al heroe que se va a eliminar  
				<p>Estas a punto de elimiar al heroe {{ data.superhero}}.
				    <br>
				    ¿Desea Continuar?
				</p>
		*************************************************************************************************************************************
	************************************************************* Fin Seccion ***********************************************************
	***************************************************Sección 15: Protección de Rutas***************************************************
		217. ********************************************************************************************************Pantalla de Login Básico
			1.- Se modifica login.component.ts, se crea metodo que navegara al listado de heroes 
				1.1.- Se injecta la dependencia a Router 
					constructor( private route: Router) { } 
				1.2.- Se rcea metodo
					 login(){
					    this.route.navigate(['/heroes'])
					  }
			2.- Se modifica login.component.html, se renderiza boton  
				<mat-grid-list cols="1">
				    <mat-grid-tile>
				        <button mat-raised-button color="primary" (click)="login()">Ingresar</button>
				    </mat-grid-tile>
				</mat-grid-list>
			3.- Se modifica home.component.ts, se crea metodo que nos lleva a modulo login 
				  constructor( private route: Router ) { }

				  logout(){
				    this.route.navigate(['/auth'])
				  }
			4.- Se modifica login.component.html, se agrega evento click al boton de logout 
				<button mat-icon-button style="margin-right: 50px" (click)="logout()">
		*************************************************************************************************************************************
		218. **************************************************************AuthService - Servicio para mantener el estado de la autenticación
			1.- Se crea interface Auth 
				1.1.- Se crea interface auth --> interfaces --> auth.interface.ts  
					export interface Auth{
					    id          : string;
					    email       : string;
					    usuario     : string;
					}
			2.- Se genera clase de servicio --> ng g s auth/services/auth --skip-tests, se le crea metodo getLogin()
				@Injectable({
				  providedIn: 'root'
				})
				export class AuthService {

				  private urlBase = environment.baseUrl;

				  constructor( private http: HttpClient) { }

				  getLogin(): Observable<Auth>{
				    return this.http.get<Auth>(this.urlBase+'/usuarios/1');
				  }
				}
			3.- Se modifica login.component.ts, se modifica metodo login para que llame al servicio y valide si existe el heroe  
				constructor( private route: Router, 
		                private authService: AuthService) { }

					  ngOnInit(): void {
					  }

					  login(){
					    this.authService.getLogin().subscribe( usu => {
					      if(usu.id){
					        this.route.navigate(['/heroes'])
					      }
					    });
					  }
		*************************************************************************************************************************************
		219. ***************************************************************************************Mostrar la información del usuario activo
			1.- Se modifica auth.service.ts, se agrega getter de auth con su atributo 
				private _auth: Auth | undefined;

		  
				  get auth(): Auth{
				    return {...this._auth!};
				  }
				  //Se  modifica para obteh¿ner el usuario y guardarlo en variable _auth
				  getLogin(): Observable<Auth>{
				    return this.http.get<Auth>(this.urlBase+'/usuarios/1').pipe(
				      tap( auth => this._auth = auth )
				    );
				  }
			2.- Se modifica home.component.ts, se obtiene suth de servicio  

				  constructor( private route: Router,
		            private authService: AuthService ) { }

				  get auth(){
				    return this.authService.auth;
				  }
			3.- Se modifica home.component.html, Se renderiza el usuario
				<span style="margin-right: 10px;">{{auth.usuario}}</span>
		*************************************************************************************************************************************
		220. ********************************************************************************************************Angular Guards - CanLoad
			1.- Se genera guars --> ng g guard auth/guards/auth --skip-tests
				? Which interfaces would you like to implement? 
					 (*) CanActivate
					 ( ) CanActivateChild
					 ( ) CanDeactivate
					>(*) CanLoad
			2.- Se modifica auth.guard.ts, se modifica el metodo calLoad() para que valide el usuario si existe antes de cargar el modulo en
				Se realiza injeccion de dependencia del servicio
				constructor( private authService: AuthService){}

				 canLoad(
				    route: Route,
				    segments: UrlSegment[]): Observable<boolean> | Promise<boolean>  | boolean {
				      if(this.authService.auth.id){
				        return true;
				      }
				      console.log('canLoad', false); 
				     /*  console.log(route);
				      console.log(segments);     */  
				    return false;
				  }
			3.- Se modifica app-routing.module.ts, se utiliza canLoad para la carga del modulo de heroes 
				  {
				    path: 'heroes',
				    loadChildren: () => import('./heroes/heroes.module').then( m => m.HeroesModule),
				    canLoad: [ AuthGuard]
				  },++
		*************************************************************************************************************************************
		221. *********************************************************************************************************************CanActivate
			1.- Se modifica auth.guard.ts, se modifica metodo para que valide la ruta activa  
				canActivate(
				    route: ActivatedRouteSnapshot,
				    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
				      if(this.authService.auth.id){
				        console.log(state);
				        return true;
				      }
				      console.log('canActive', false);  
				    return false;
				  }
			2.- Se modifica app-routing.module.ts, se agrega canActive a la carga del modulo de heroes 
				  {
				    path: 'heroes',
				    loadChildren: () => import('./heroes/heroes.module').then( m => m.HeroesModule),
				    canLoad: [ AuthGuard],
				    canActivate: [ AuthGuard ]
				  },++
		*************************************************************************************************************************************
		222. **************************************************************************************************Mantener la sesión del usuario
			1.- Se modifica auth.service.ts, se crea metodo de verificacion de authenticacion 
				 verificaAuthenticacion(): Observable<boolean> {
				    if( !localStorage.getItem('token')){
				      return of(false);
				    }

				    return this.http.get<Auth>(this.urlBase+'/usuarios/1').pipe(
				      map( auth => {
				        this._auth = auth;
				        return true;
				      })
				    );
				  }
			2.- Se modifica auth.guard.ts, se modifican metodos canActive y canLoad, se utiliza metodo de verificacion del servicio 
				  canActivate(
					    route: ActivatedRouteSnapshot,
					    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
					      /* if(this.authService.auth.id){
					        console.log(state);
					        return true;
					      }
					      console.log('canActive', false);  
					    return false; */
					    return this.authService.verificaAuthenticacion().pipe(
					      tap( estado => {
					        if ( !estado ){
					          this.router.navigate(['./auth/login']);
					        }
					      })
					    );
					  }
					  canLoad(
					    route: Route,
					    segments: UrlSegment[]): Observable<boolean> | Promise<boolean>  | boolean {
					     /* if(this.authService.auth.id){
					        return true;
					      }
					      console.log('canLoad', false); 
					      console.log(route);
					      console.log(segments);     */  
					      return this.authService.verificaAuthenticacion().pipe(
					        tap( estado => {
					          if ( !estado ){
					            this.router.navigate(['./auth/login']);
					          }
					        })
					      );
					  }
		*************************************************************************************************************************************
	************************************************************* Fin Seccion ***********************************************************
	********************************************Seccion 16: Formularios -Template y LazyLoad*********************************************
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
	***************************************************Seccion 17: Formularios Reactivos************************************************
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
				<div class="row">
				    //<div class="col">
				        <span>Valid</span>
				        <pre>{{myForm.valid}}</pre>
				        <span>Value</span>
				        <pre>{{myForm.value | json}}</pre>
				        <!-- Valida si el formulario fue tocado por el usuario -->
						<span>Pristine</span>
				        <pre>{{myForm.pristine }}</pre>
				        <span>Touched</span>
				        <pre>{{myForm.touched | json}}</pre>
				        <span>CustomDirective</span>
				        <pre>{{myForm.controls.existencia?.errors| json}}</pre>
				    </div>
				</div>
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
				            //<button class="btn btn-primary float-end" >Guardar</button>
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
	************************************************************ Fin Seccion ***********************************************************
	*************************************Seccion 18: Formularios: Validaciones manuales y asincronas************************************
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
	************************************************************ Fin Seccion ***********************************************************
	**********************************Seccion 19: Formularios Reactivos . Multiples Select Anidados*************************************
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
	************************************************************ Fin Seccion ***********************************************************
	*****************************************************Seccion 20: LifeCicle Hooks****************************************************
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
	************************************************************ Fin Seccion ***********************************************************
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
	************************************************************************************************************************************
	************************************************************ Fin Seccion ***********************************************************
	*****************************************Sección 23: Directivas personalizadas de Angulars******************************************
		341. *********************************************************************************************Inicio de proyecto - DirectivasApp
			Refrencias --> 
				Angular Material Directivas --> https://angular.io/guide/attribute-directives 
				Bootstrap 					--> https://getbootstrap.com/docs/5.0/getting-started/introduction/

			1.- Creacion de proyecto --> ng new directivasApp   
			2.- Se corre el proyecto --> ng serve -o 
			3.- Se modifica index.html, se borra contenido y se peg cdn de bootstrap 
				 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
		************************************************************************************************************************************ 
		342. ****************************************************************************************************Estructura de la aplicación
			1.- Se crean folders 
				productos
				shared 
			2.- Se crean modulos  
				ng g m productos --routing
				ng g m shared
			3.- Se crea componentes
				ng g c productos/pages/agregar --skip-tests -is
			4.- Se modifica app-routing.module.ts,se modifica constante routes se cargan los modulos por lazyLoad
				const routes: Routes = [
				  {
				    path: 'productos',
				    loadChildren: () => import('./productos/productos.module').then( m => m.ProductosModule)
				  },
				  {
				    path: '**',
				    redirectTo: 'productos'
				  }
				]
			5.- Se modifica productos-routing.module.ts, se modifica constante routes, se cargan las rutas hijas 
				const routes: Routes = [{
				  path: '',
				  children:[
				    {
				      path: 'agregar', 
				      component: AgregarComponent
				    },
				    {
				      path: '**',
				      redirectTo: 'agregar'
				    }
				  ]
				}];
			6.- Se modifica app.component.html, se agrega componente router-outlet 
				<div class="container">
				    <router-outlet></router-outlet>
				</div>
			7.- Se modifica agregar.component.html, se renderiza formulario  
				<h1 class="mt-5">Agregar Productos</h1>
				<hr>
				<form>
				    <div class="row g-3 align-items-center mb-3">
				        <div class="col-auto">
				            <label for="" class="col-form-label">
				                Nombre: 
				            </label>
				        </div>
				        <div class="col-auto">
				            <input type="text" class="form-control">
				        </div>
				        <div class="col-auto">
				            <span class="form-text">
				                Este campo es requerido.
				            </span>
				        </div>
				    </div>
				    <div class="row mt-3">
				        <div class="col">
				            <button class="btn btn-primary">  
				                Guardar 
				            </button>
				        </div>
				    </div>
				</form>

				<h3 class="mt-5">
				    Formulario
				</h3>+
		************************************************************************************************************************************
		343. ************************************************************************************************Formulario reactivo tradicional
			1.- Se modifica productos.module.ts, se importa ReactiveFormsModule 
				import { NgModule } from '@angular/core';
				import { CommonModule } from '@angular/common';
				import { ReactiveFormsModule } from '@angular/forms';

				import { ProductosRoutingModule } from './productos-routing.module';
				import { AgregarComponent } from './pages/agregar/agregar.component';


				@NgModule({
				  declarations: [
				    AgregarComponent
				  ],
				  imports: [
				    CommonModule,
				    ProductosRoutingModule,
				    ReactiveFormsModule
				  ]
				})
				export class ProductosModule { }
			2.- Se modifica agregar.component.ts 
				2.1.- Se crea atrbuto de tipo FormGroup 
					  myForm: FormGroup = this.formBuilder.group({
					    nombre: ['', Validators.required]
					  }); 
				2.2.- Se realiza la Injeccion de dependencia de FormBuilder 
					constructor( private formBuilder: FormBuilder) { }
				2.3.- Se crea metodo tieneError() 
					  tieneError( campo: string ): boolean{
					    return this.myForm.get(campo)?.valid || false;
					  }
			3.- Se modifica agregar.component.html 
				3.1.- Se le agrega validacion al mensaje de error del campo nombre 
					<div class="col-auto">
			            <span class="form-text" *ngIf="!tieneError('nombre')">
			                Este campo es requerido.
			            </span>
			        </div>
			    3.2.- Se agregan valores del estado y el formulario 
			    	<h3 class="mt-5">Formulario</h3>
					<pre>Value: {{myForm.value | json }}</pre>
					<pre>Estado: {{myForm.valid | json }}</pre>
		************************************************************************************************************************************
		344. *********************************************************************************************Directiva personalizada - ErrorMsg
			1.- Se crea folder en shares --> directives  
			2.- Se crea directiva 
				ng g d shared/directives/errorMsg --skip-Tests
			3.- Se modifica shared.module.ts, se exporta la directiva 
				@NgModule({
				  declarations: [
				    ErrorMsgDirective
				  ],
				  exports:[
				    ErrorMsgDirective
				  ],
				  imports: [
				    CommonModule
				  ]
				})
				export class SharedModule { }
			4.- Se modifica productos.module.ts, se importa sharedModule para poder utilizar la directiva 
				@NgModule({
				  declarations: [
				    AgregarComponent
				  ],
				  imports: [
				    CommonModule,
				    ProductosRoutingModule,
				    ReactiveFormsModule,
				    SharedModule
				  ]
				})
				export class ProductosModule { }
			5.- Se modifica la directiva, se implementa OnInit y se le agregan impresion por consola 
				@Directive({
				  selector: '[appErrorMsg]'
				})
				export class ErrorMsgDirective implements OnInit{

				  constructor() {
				    console.log('Constructor Directiva.');
				  }
				  
				  
				  ngOnInit(): void {
				    console.log('OnInit() Directiva.');
				  }

				}
		************************************************************************************************************************************
		345. ************************************************************************************Directive Input - Cambiar el color del host
			1.- Se modifica error.msg.directive.ts 
				1.1.- Se le agregan atributos  
					htmlElement: ElementRef<HTMLElement>;
		  			@Input() color: string = 'red';
		  		1.2.- Se modifica constructor, se hace ID de un EementRef<HTMLElement>, se  inicializa htmlElement 
		  			  constructor( private eleRef: ElementRef<HTMLElement>) {
					    console.log('Constructor Directiva.');
					    this.htmlElement = eleRef;
					  }
				1.3.- Se crea metodo que setea valor al elemento html  
					  setColor(): void{
					    this.htmlElement.nativeElement.style.color = this.color;
					  }
				1.4.- Se modifica OnInit(), se setea el color  
					ngOnInit(): void {
						    this.setColor();
						  } 
			2.- Se modifica agregar.component.html, se le agrega directiva al elemento span y a la muestra del elemento formulario  
			    <div class="col-auto">
		            <span class="form-text" *ngIf="!tieneError('nombre')" 
		            appErrorMsg
		            color="blue">
		                Este campo es requerido.
		            </span>
		        </div>

			    <h3 class="mt-5" appErrorMsg color="blue">Formulario</h3>
		************************************************************************************************************************************
		346. **********************************************************************************************Cambiar el mensaje de la etiqueta
			1.- Se modifica error.msg.directive.ts 
					1.1.- Se le agregan atributos  
						@Input() mensaje: string = 'Mensaje por Default'
					1.2.- Se crea metodo para setear mensaje al elemento html 
						  setMensaje(): void{
						    this.htmlElement.nativeElement.innerText = this.mensaje;
						  }
					1.3.- Se modifica metodo para agregar clase al elemento html   
						  setColor(): void{
						    this.htmlElement.nativeElement.style.color = this.color;
						    this.htmlElement.nativeElement.classList.add('form-text');

						  }
					1.4.- Se modifica OnInit(), se setea el mensaje  
						  ngOnInit(): void {
						    this.setColor();
						    this.setMensaje();
						  } 
				2.- Se modifica agregar.component.html, se le agrega mensaje al elemento  
				           <div class="col-auto">
					            <span *ngIf="!tieneError('nombre')" 
					            appErrorMsg
					            mensaje= "Debe indicar un mensaje"
					            color="red">
					                Este campo es requerido.
					            </span>
					        </div>
		************************************************************************************************************************************
		348. ******************************************************************************************************************Input setters
			1.- Se modifica error-msg.directive.ts
				@Directive({
				  selector: '[appErrorMsg]'
				})
				export class ErrorMsgDirective implements OnInit{

					//Se crean atributos
				  private _color: string ='red';
				  private _mensaje: string ='Mensaje por defecto';

				  htmlElement: ElementRef<HTMLElement>;

				  //Se crean los setter de las propiedades
				  @Input() set color(valor: string ){
				    this._color = valor;
				    this.setColor();
				  }
				  @Input() set mensaje( valor: string){
				    this._mensaje = valor;
				    this.setMensaje();
				  }

				  @Input() set valido( valor: boolean ){
				    if (valor){
				      this.htmlElement.nativeElement.classList.add('hidden');
				    }else{
				      this.htmlElement.nativeElement.classList.remove('hidden');    
				    }
				  }

				  //En el constructor se inicializa obj de tipo ElementRef
				  constructor( private eleRef: ElementRef<HTMLElement>) {
				    this.htmlElement = eleRef;
				  }
				  
				  //Se aplican metodos al crearse obj 
				  ngOnInit(): void {
				    this.setColor();
				    this.setMensaje();
				    this.setEstilo();
				  }

				  //Cambia clase del elemento que tiene la directiva
				  setEstilo(): void{
				    this.htmlElement.nativeElement.classList.add('form-text');
				  }
				  
				  //Cambia color del elemento que tiene la directiva
				  setColor(): void{
				    this.htmlElement.nativeElement.style.color = this._color;
				  }

				  //Cambia mensaje en el elemento que tiene la directiva
				  setMensaje(): void{
				    this.htmlElement.nativeElement.innerText = this._mensaje;
				  }

				}
			2.- @Component({
				  selector: 'app-agregar',
				  templateUrl: './agregar.component.html',
				  styles: [
				  ]
				})
				export class AgregarComponent implements OnInit {

					//Propiedades
				  texto1: string = 'Elvis Areiza';
				  color: string = 'green';

				  //Formulario
				  myForm: FormGroup = this.formBuilder.group({
				    nombre: ['', Validators.required]
				  }); 

				  constructor( private formBuilder: FormBuilder) { }

				  ngOnInit(): void {
				  }

				  //Validacion 
				  tieneError( campo: string ): boolean{
				    return this.myForm.get(campo)?.valid || false;
				  }

				  cambiarNombre(){
				    this.texto1 = Math.random().toString();
				  }

				  //Generacion de color de forma aleatoria
				  cambiarColor(){
				    this.color = "#xxxxxx".replace(/x/g, y=>(Math.random()*16|0).toString(16));
				  }

				}
			3.- Se modifica el elemento con su directiva y se crea elemento de lerta
				<h1 class="mt-5">Agregar Productos</h1>
				<hr>
				<form [formGroup]="myForm">
				    <div class="row g-3 align-items-center mb-3">
				        <div class="col-auto">
				            <label for="" class="col-form-label">
				                Nombre: 
				            </label>
				        </div>
				        <div class="col-auto">
				            <input type="text" 
				            class="form-control" 
				            formControlName="nombre">
				        </div>
				        <div class="col-auto">
				            <span appErrorMsg
				                [valido]="tieneError('nombre')"             
				                [mensaje]= "texto1"
				                [color]= "color">
				                Este campo es requerido.
				            </span>
				        </div>
				    </div>
				    <div class="row mt-3">
				        <div class="col">
				            <button class="btn btn-primary" (click)="cambiarNombre()">  
				                Nombre 
				            </button>
				        </div>
				    </div>
				    <div class="row mt-3">
				        <div class="col">
				            <button class="btn btn-primary" (click)="cambiarColor()">  
				                Color 
				            </button>
				        </div>
				    </div>
				</form>

				<h3 class="mt-5" appErrorMsg color="blue">Formulario</h3>
				<pre>Value: {{myForm.value | json }}</pre>
				<pre>Estado: {{myForm.valid | json }}</pre>

				<div class="row mt-5" *ngIf="tieneError('nombre')">
				    <div class="col">
				        <div class="alert alert-primary">Todo Ok!</div>
				    </div>
				</div>
		************************************************************************************************************************************
		351. ****************************************************************************************Directivas estructurales personalizadas
		************************************************************************************************************TemplateRef<HTMLElement>
		********************************************************************************************************************ViewContainerRef
			1.- Se crea directiva 
				ng g d shared/directives/customIf --skip-tests
			2.- Se modifica shares.module.ts, se exporta la directiva 
				@NgModule({
				  declarations: [
				    ErrorMsgDirective,
				    CustomIfDirective
				  ],
				  exports:[
				    ErrorMsgDirective,
				    CustomIfDirective
				  ],
				  imports: [
				    CommonModule
				  ]
				})
				export class SharedModule { }
			3.- Se modifica custom-if.directive.ts 
				@Directive({
				  selector: '[appCustomIf]'
				})
				export class CustomIfDirective {

				  @Input() set appCustomIf( condicion: boolean ){
				    if(condicion){
				      this.viewContainer.createEmbeddedView( this.templateRef );
				    }else{
				      this.viewContainer.clear();
				    }
				  }

				  constructor( private templateRef: TemplateRef<HTMLElement>,
				                private viewContainer: ViewContainerRef) { }

				}
			4.- Se modifica agregar.component.html, se modifica el elemento html para que utilice la directiva 
				<div class="row mt-5" *appCustomIf="myForm.valid">
				    <div class="col">
				        <div class="alert alert-primary">Todo Ok!</div>
				    </div>
				</div>
		************************************************************************************************************************************
	************************************************************************************************************************************
	******************************************************* Fin Seccion ****************************************************************
	************************************************Sección 24: Auth Backend - MEAN*****************************************************
		356. *************************************************************************************************Inicio de proyecto - Auth MEAN
			1.- Se crea proyecto --> npm init 
			2.- Se crea en raiz archivo --> index.js 
			3.- Se visualiza el package.json creado 
			4.- Se ejecuta con --> node index.js
		************************************************************************************************************************************
		357. ******************************************************************************************************************Npm - Nodemon
			1.- Se instala nodemon, para que realice los cambios en caliente 
				npm install -g nodemon
				1.1.- Se corre con nodemon
					nodemon index.js
			2.- Se modifica package.json,se modifica scripts para que tome variables de inicio del aplicativo 
				{
				  "name": "auth",
				  "version": "1.0.0",
				  "description": "",
				  "main": "index.js",
				  "scripts": {
				    "test": "echo \"Error: no test specified\" && exit 1",
				    "dev": "nodemon index.js",
				    "start": "node index.js"
				  },
				  "author": "",
				  "license": "ISC",
				  "dependencies": {
				    "jshint": "^2.13.0"
				  }
				}
			3.- Se corre el aplicativo 
				test -->  npm run dev 
				prod --> npm start 
		************************************************************************************************************************************
		358. ***************************************************************************************Instalaciones necesarias para el backend
			1.- Se realizan instalaciones 
				npm i cors dotenv express express-validator jsonwebtoken mongoose
				encriptacion de contraseñas 				--> bcriptjs 
				manejos de cors 							--> cors 
				configuracion de variables de entorno 		--> dotenv 
				servidor con servicio rest 					--> express 
				validaciones 								--> express-validator 
				para crear JWT 								--> jsonwebtoken 
				conexion y trabajo con BDs Mongo 			--> mongoose

				Nota:  Todas las instalaciones las podemos observar en el package.json como dependencias  
					{
					  "name": "auth",
					  "version": "1.0.0",
					  "description": "",
					  "main": "index.js",
					  "scripts": {
					    "test": "echo \"Error: no test specified\" && exit 1",
					    "dev": "nodemon index.js",
					    "start": "node index.js"
					  },
					  "author": "",
					  "license": "ISC",
					  "dependencies": {
					    "bcryptjs": "^2.4.3",
					    "cors": "^2.8.5",
					    "dotenv": "^10.0.0",
					    "express": "^4.17.1",
					    "express-validator": "^6.11.1",
					    "jshint": "^2.13.0",
					    "jsonwebtoken": "^8.5.1",
					    "mongoose": "^5.12.13"
					  }
					}
		************************************************************************************************************************************
		359. *************************************************************************************************Configurar servidor de Express
			1.- Se modifica index.js, se crea servidor de apicaciones
				const express = require('express');

				// Crear el servidor de aplicaciones express
				const app = express();

				//peticion Get
				app.get('/', (req, res) =>{
				    res.json({
				        ok: true,
				        msg: 'Todod ok',
				        id: 12345
				    });
				}) ;

				app.listen( 4000, () => {
				    console.log('Servidor corriendo en el puerto ${4000}')
				})
		************************************************************************************************************************************
		360. ******************************************************************************************Crear las rutas de nuestra aplicación
			1.- Se crea folder en rais --> ruta --> auth.js 
			2.- Se modifica auth.js, se crea constructor
				//Se desestructura para obtener Router
				const { Router }= require('express');


				const router = Router();

				//controlador

				//Crear usuario
				router.post('/new', (req, res) => {
				    return res.json({
				        ok: true,
				        msg: 'Crear usuario /new'
				    })
				})

				//Crear Login
				router.post('/', (req, res) => {
				    return res.json({
				        ok: true,
				        msg: 'Crear Login /'
				    })
				})

				//validar JWT
				router.get('/renew', (req, res) => {
				    return res.json({
				        ok: true,
				        msg: 'renew /renew'
				    })
				})

				//Se Exporta 
				module.exports = router;
			3.- Se modifica index.js 
				1.- Se elimina peticion get y añade ruta a peticion  
					//Rutas
					app.use('/api/auth', require('./routes/auth'));
		************************************************************************************************************************************
		361. **********************************************************************************************Separar el controlador de la ruta
			1.- Se crea folder --> controllers --> auth.controller.js 
			2.- Se modifica auth.controller.js, se cortan los callbacks de los response en el auth.j y se crean como costantes, luego se 
				exportan


				const crearUsuario = (req, res) => {
				    return res.json({
				        ok: true,
				        msg: 'Crear usuario /new'
				    });
				}

				const crearLogin = (req, res) => {
				    return res.json({
				        ok: true,
				        msg: 'Crear Login /'
				    })
				}

				const validarToken = (req, res) => {
				    return res.json({
				        ok: true,
				        msg: 'renew /renew'
				    })
				}

				module.exports = {
				    crearUsuario,
				    crearLogin,
				    validarToken
				}
			3.- Se modifica auth.js en folder routes, se cortan los callbacks y se reemplazan por el llamado de constantes importadas de auth.controller.js, 
				se prueban las rutas por postman

				//controlador

				//Crear usuario
				router.post('/new', crearUsuario)

				//Crear Login
				router.post('/', crearLogin)

				//validar JWT
				router.get('/renew', validarToken)

			 	rutas a probar
			 	get 	--> http://localhost:4000/api/auth/renew
			 	post 	--> http://localhost:4000/api/auth/
			 	post 	--> http://localhost:4000/api/auth/new
		************************************************************************************************************************************
		362. ***************************************************************************************Configurar CORS y body de las peticiones
			1.- Se modifica index.js, se crean midlewere para cors y lectura y parseo de request 

				//CORS
				app.use( cors() );

				//Lectura y parseo del body
				app.use( express.json() )
			2.- Se modifica auth.controller.js, se modifican metodos para capturar el body del request y mostrarlo por consola
				const crearUsuario = (req, res) => {
				    const { name, email, pass } = req.body;
				    console.log( name, email, pass);
				    return res.json({
				        ok: true,
				        msg: 'Crear usuario /new'
				    });
				}

				const crearLogin = (req, res) => {
				    const { name, email } = req.body;
				    console.log( name, email);
				    return res.json({
				        ok: true,
				        msg: 'Crear Login /'
				    })
				}
		************************************************************************************************************************************
		363. ***************************************************************************************************Variables de entorno de Node
			1.- Se crea archivo de eviroment y se le agrega variable para el puerto 
				PORT=4000
			2.- Se modifica index.js, se importan las configuraciones de dotenv, se imprimen por consola y se modifica variabe de puerto 
			listen
				require('dotenv').config();

				console.log( process.env );


				app.listen( process.env.PORT , () => {
				    console.log(`Servidor corriendo en el puerto ${process.env.PORT}`);
				});
		************************************************************************************************************************************
		364. *******************************************************************************************Servir una página HTTP desde Express
			1.- Se crea directorio publico 
				--> public --> index.html 
			2.- Se modifica index.html, se renderiza vista  
				<!DOCTYPE html>
				<html lang="en">
				<head>
				    <meta charset="UTF-8">
				    <meta http-equiv="X-UA-Compatible" content="IE=edge">
				    <meta name="viewport" content="width=device-width, initial-scale=1.0">
				    <title>Acceso denegado</title>
				</head>
				<body>
				    <h1>Acceso denegado</h1>
				</body>
				</html>
			3.- Se modifica index.js, se agrega directorio publico para el acceso por explorador 
				//Directorio publico
				app.use( express.static('public'));
		************************************************************************************************************************************
		365. ****************************************************************************************************Validar campos obligatorios
			1.- Se modifica el auth.js, se modifica la creacion de login, se le agregan vladaciones a los campos  
				//Crear Login
				router.post('/',[
				    check('email', 'El email es obligatorio.').isEmail(),
				    check('password', 'El password debe contener minimo 5 caracteres.').isLength( {min: 5} ),
				], crearLogin);
			2.- Se modifica auth.controller.js, se modifica crearLogin, se agrega manejo de errores si viene en el reqest 
				const crearLogin = (req, res = response) => {

				    const errors = validationResult( req );
				    if( !errors.isEmpty() ){
				        return res.status(400).json({
				            ok: false,
				            errors: errors.mapped()
				        });
				    }

				    const { email, password } = req.body;
				    console.log( password, email);
				    return res.json({
				        ok: true,
				        msg: 'Crear Login /'
				    })
				}
		************************************************************************************************************************************
		366. **********************************************************************************************************Tarea: Validar campos
			1.- Se modifica el auth.js, se modifica la creacion de login, se le agregan vladaciones a los campos  
				//Crear usuario
				router.post('/new', [
				    check('name', 'El campo name es obligatorio y no puede estar vacio').not().isEmpty(),
				    check('pass', 'El campo pass es obligatorio y debe ser robusta').isStrongPassword(),
				    check('email', 'El campo email es obligatorio y debe tener el formato de email').isEmail(),
				 ] ,crearUsuario)
			2.- Se modifica auth.controller.js, se modifica crearLogin, se agrega manejo de errores si viene en el request 
				const crearUsuario = (req, res = response ) => {
				    const errors = validationResult( req );
				    if( !errors.isEmpty() ){
				        return res.status(400).json({
				            ok: false,
				            errors: errors.mapped()
				        })
				    }
				    const { name, email, pass } = req.body;
				    console.log( name, email, pass);
				    return res.json({
				        ok: true,
				        msg: 'Crear usuario /new'
				    });
				}
		************************************************************************************************************************************
		367. ********************************************************************************************* Custom Middleware - ValidarCampos
			1.- Se crea folder para validaciones y archivo validar-campos.js 
				middlewares --> validar-campos.js 
			2.- Se modifica archivo creado, se corta el manejo de errores en el auth.controller.js y se pega en la constante validarCampos 
				const { response } = require('express');
				const { validationResult } = require("express-validator");

				const validarCampos = ( req, res = response, next) =>{
				    const errors = validationResult( req );
				    if( !errors.isEmpty() ){
				        return res.status(400).json({
				            ok: false,
				            errors: errors.mapped()
				        })
				    }

				    next();
				}

				module.exports = {
				    validarCampos
				}
			3.- Se modifica auth.js, se agrega a los metodo validarCampos 
				//Crear usuario
				router.post('/new', [
				    check('name', 'El campo name es obligatorio y no puede estar vacio').not().isEmpty(),
				    check('pass', 'El campo pass es obligatorio y debe ser robusta').isStrongPassword(),
				    check('email', 'El campo email es obligatorio y debe tener el formato de email').isEmail(),
				    validarCampos
				 ] ,crearUsuario)

				//Crear Login
				router.post('/',[
				    check('email', 'El email es obligatorio.').isEmail(),
				    check('password', 'El password debe contener minimo 5 caracteres.').isLength( {min: 5} ),
				    validarCampos
				], crearLogin);
			4.- Se modifica auth.controller.js, se elimina manejo de errores de los metodos  
				const crearUsuario = (req, res = response ) => {
		    
				    const { name, email, pass } = req.body;
				    console.log( name, email, pass);
				    return res.json({
				        ok: true,
				        msg: 'Crear usuario /new'
				    });
				}

				const crearLogin = (req, res = response) => {


				    const { email, password } = req.body;
				    console.log( password, email);
				    return res.json({
				        ok: true,
				        msg: 'Crear Login /'
				    })
				}
		************************************************************************************************************************************
		368. *********************************************************************************************Configurar base de datos - MongoDB
			https://cloud.mongodb.com --> referencia
			https://www.udemy.com/course/angular-fernando-herrera/learn/lecture/24468434#overview
			1.- Se crea Bd
				user: eareizac
				pass: UnCBCqbB4ClLqMtf
			2.- Se descarga el aplicativo MongoDB Compass 
			3.- Se modifica archivo .env, se agrega la cadena de conexion 
				BD_CNN=mongodb+srv://eareiza:UnCBCqbB4ClLqMtf@clustertutoangular.ljd5e.mongodb.net/myAngular
		************************************************************************************************************************************
		369. ****************************************************************************************Conectar MongoDB Atlas - Compass y Node
			1.- Conectar desde compass con el string de conexion
				1.1.- Se añade ip de conexion en networw access desde el aplicativo de mongoDB en el explorador 
					0.0.0.0/0
			2.- Se crea archivo de configuracion de la DB. 
				raiz --> db --> config.js 
			3.- Se modifica config.js 
				//Se importa mongoose
				const mongoose = require("mongoose");

				//Se cre la conexion 
				const dbConection = async() =>{
				    try {
				        await mongoose.connect(process.env.BD_CNN, {
				            useNewUrlParser: true,
				            userUniFiedTopology: true,
				            useCreateIndex: true
				        });
				        console.log('BD online')
				    } catch ( error ) {
				        console.log(error);
				        throw new Error('Error a la hora de inicializar DB.')
				    }
				}

				// Se exporta 
				module.exports = {
				    dbConection
				}
			4.- Se modifica index.js, se ejecuta conexion 
				//Base de Datos
				dbConection();
		************************************************************************************************************************************
		370. **************************************************************************************************Crear modelo de base de datos
			1.- Se crea archivo modelo, Usuario.js 
				raiz --> models --> Usuario.js 
				//Se importa 
				const { Schema, model } = require("mongoose");

				//Se crea el schema 
				const UsuarioSchema = Schema({
				    name: {
				        type: String,
				        required: true
				    },
				    email:{
				        type: String,
				        required: true,
				        unique: true
				    },
				    pass:{
				        type: String,
				        required: true
				    }
				});

				//Se crea
				module.exports = model('Usuario', UsuarioSchema);
		************************************************************************************************************************************
		371. *************************************************************************************************Crear usuario en base de datos
			1.- Se modifica auth.controller.js, se modifica metodo crearUsuario() 
				const crearUsuario = async(req, res = response ) => {
				    const { name, email, pass } = req.body;
				    console.log( name, email, pass);
				    try {
				        //Verificar el email
				        const usuario = await Usuario.findOne({ email: email });
				        if( usuario ){
				            return res.status(400).json({
				                ok: false, 
				                msg: 'Usuario con ese email ya existe'
				            })
				        }
				        //Crear usuario con el modelo
				        const userDB = new Usuario( req.body );
				        //Crear usuario en la BD 
				        await userDB.save(); 
				        // Generar respuesta exitosa
				        return res.status(201).json({
				            ok: true,
				            uid: userDB.id,
				            name,
				            msg:'Usuario se ha creado con exito.'
				        });
				    } catch (error) {
				        console.log(error);
				        return res.status(500).json({
				            ok: false,
				            msg: 'Por favor comuniquese con el Adiministrador del sistema '
				        });
				    }
				}
			2.- Se prueba en post man 
				Post --> http://localhost:4000/api/auth/new
				{
				    "name": "Antonio piñando",
				    "email": "aa@gmail.com",
				    "pass": "Danger120-" 
				}
		************************************************************************************************************************************
		372. **********************************************************************************************************Hash de la contraseña
			1.- Se modifica auth.controller.js, se modifica la contraseña con hash de bcrypt.js 
				const bcrypt = require('bcryptjs');
				1.1.- Se modifica metodo crearUsuario();
				// Encriptacion de la contraseña
		        const salt = bcrypt.genSaltSync(10);
		        userDB.pass = bcrypt.hashSync(pass , salt);
		************************************************************************************************************************************
		373. ***********************************************************************************************************Generar JsonWebToken
			1.- Se modifica archivo de enviroment --> .env , se agrega clave secreta  
				PORT=4000
				BD_CNN=mongodb+srv://eareiza:UnCBCqbB4ClLqMtf@clustertutoangular.ljd5e.mongodb.net/myAngular
				SECRET_JWT_SEED=3ST0D3B3S3RS3CR370
			2.- Se crea archivo para la creacion de token raiz --> helpers --> jwt.js  
				 const jwt = require('jsonwebtoken');

				const generarJWT = (uid, name) => {
				    const payload = {uid, name};
				    //Promesa
				    return new Promise( (resolve, reject) => {
				        //Creacion de token
				        jwt.sign( payload, process.env.SECRET_JWT_SEED, {
				            expiresIn: '24h'
				        }, (err,token) => {
				            if( err ){
				                //Todo Mal
				                console.log(err);
				                reject(err);
				            }else{
				                //Todo bien 
				                resolve(token);
				            }
				        })
				    })
				}

				module.exports = {
				    generarJWT
				}
			3.- Se modifica auth.controller.js, se modifica metodo crearUsuario(), se genera token y se envia en el body  de la respuesta. 
				const crearUsuario = async(req, res = response ) => {

				    const { name, email, pass } = req.body;
				    console.log( name, email, pass);
				    
				    try {

				        //Verificar el email
				        const usuario = await Usuario.findOne({ email: email });

				        if( usuario ){
				            return res.status(400).json({
				                ok: false, 
				                msg: 'Usuario con ese email ya existe'
				            })
				        }

				        //Crear usuario con el modelo
				        const userDB = new Usuario( req.body );

				        // Encriptacion de la contraseña
				        const salt = bcrypt.genSaltSync(10);
				        userDB.pass = bcrypt.hashSync(pass , salt);

				        // Generacion de token
				        const token = await generarJWT( userDB.id, userDB.name );

				        //Crear usuario en la BD 
				        await userDB.save();
				        
				        // Generar respuesta exitosa
				        return res.status(201).json({
				            ok: true,
				            uid: userDB.id,
				            name,
				            msg:'Usuario se ha creado con exito.',
				            jwt: token 
				        });
				        

				    } catch (error) {
				        console.log(error);
				        return res.status(500).json({
				            ok: false,
				            msg: 'Por favor comuniquese con el Adiministrador del sistema '
				        });
				    }    
				}
		************************************************************************************************************************************
		374. ***************************************************************************************************************Login de usuario
			1.- Se modifica auth.controller.js, se modifica metodo crearLogin(), se valida contraseña y password 
				const crearLogin = async(req, res = response) => {
				    const { email, password } = req.body;
				    
				    try {
				        const dbUser = await Usuario.findOne({ email });

				        if( !dbUser ){
				            return res.status(400).json({
				                ok: false,
				                msg: 'El correo no existe.'
				            })
				        }

				        //Valido password 
				        console.log(dbUser.pass)
				        const validPassword = bcrypt.compareSync( password, dbUser.pass);

				        if( !validPassword ){
				            return res.status(400).json({
				                ok: false,
				                msg: 'La contraseña es invalida.'
				            })
				        }

				        //genero token 
				        const token = await generarJWT( dbUser.id, dbUser.name );
				        
				        //Respuesta
				        return res.json({
				            ok: true,
				            uid: dbUser.id,
				            name: dbUser.name,
				            msg:'Usuario se ha logueado.',
				            jwt: token
				        })

				    } catch (error) {
				        console.error(error);
				        return res.status(500).json({
				            ok: false,
				            msg: 'Hable con el administrador.'
				        });
				    }
				    
				}
		************************************************************************************************************************************
		375. *******************************************************************************************************Renovar y validar el JWT
			1.- Se crea archivo middlewares --> validar-jwt.js
				const { response } = require('express');
				const jwt = require('jsonwebtoken');


				const validaJWT = ( req, res= response, next )=>{
				    const token = req.header('x-token');
				    if(!token){
				        return res.status(401).json({
				            ok: false,
				            msg: 'Error en el token'
				        })
				    }

				    try {
				        
				        const { uid, name } = jwt.verify( token, process.env.SECRET_JWT_SEED);
				        //Se añade a la request id y  name 
				        req.uid = uid;
				        req.name = name;        
				    } catch (error) {
				        return res.status(401).json({
				            ok: false,
				            msg: 'Token no valido'
				        })
				    }
				    //Todo OK
				    next();
				}

				module.exports = {
				    validaJWT
				}
			2.- Se modifica auth.js, Se agrega middleware a la peticion http
				//validar JWT
				router.get('/renew', validaJWT, validarToken)
			3.- Se modifica auth.controller.js, se modifica metodo validarToken()   
				const validarToken = (req, res = response) => {
					//Se recupera id y name del request 
				    const {uid, name} = req;
				    return res.json({
				        ok: true,
				        msg: 'renew /renew',
				        uid, 
				        name
				    })
				}
		************************************************************************************************************************************
		376. **********************************************************************************************Solución a la tarea - Generar JWT
			1.- Se modifica auth.controller.js, se modifica metodo validarToken(), se genera token  

				const validarToken = async(req, res = response) => {
				    const {uid, name} = req;

				    //genero token 
				    const token = await generarJWT( uid, name );


				    return res.json({
				        ok: true,
				        msg: 'renew /renew',
				        uid, 
				        name,
				        token 
				    })
				}
		************************************************************************************************************************************
		************************************************************************************************************************************
	************************************************************************************************************************************
	******************************************************* Fin Seccion ****************************************************************
	*************************************************Sección 25: AuthApp - MEAN*********************************************************
		381. *************************************************************************************************Inicio de proyecto - Auth MEAN
			1.- Se crea el proyecto --> ng new AuthApp
			2.- Semodifica app.component.html,seborratodoy se coloca
				<h1>Hello word</h1>
			3.-Secorreelproyecto -->ng serve -o
		************************************************************************************************************************************
		382. **********************************************************************************************Estructura del proyecto - AuthApp
			1.- Se crea folders 
				auth 	--> pages 
						--> services 
						--> interfaces
				protected 
				guards
			2.-Se crean modulos  
				ng g m protected --routing
				ng g m auth --routing
			3.-Se crean componentes 
				ng g c auth/pages/login --skipTests -is
				ng g c auth/pages/register --skipTests -is
				ng g c auth/pages/main --skipTests -is
				ng g c protected/dashboard --skipTests -is
			4.- Se copian los archivos font y images en assets --> carpeta de  recursos del tuto
			5.- Se copia contenido de login.css en style.css del proyecto --> recursos del tuto   
		************************************************************************************************************************************
		383. ***************************************************************************************************************Rutas y LazyLoad
			1.- Se modifica app.component.html,se le agrega router-outlet  
				 <router-outlet></router-outlet>
			2.- Se modifica app-routing.module.ts, se agrega carga perezosa a los modulos  
				const routes: Routes = [
				  {
				    path: 'auth',
				    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
				  },
				  {
				    path: 'dashboard',
				    loadChildren: () => import('./protected/protected.module').then(m => m.ProtectedModule)
				  },
				  {
				    path: '**',
				    redirectTo: 'auth'
				  }
				]; 
			3.- Se modifica main.component.html, se le agrega router-outlet  
				<router-outlet></router-outlet> 
			4.- Se modifica auth-routing.module.ts, se agregan las rutas
				const routes: Routes = [
				  {
				    path: '',
				    component: MainComponent,
				    children: [
				      { path: 'login', component: LoginComponent },
				      { path: 'register', component: RegisterComponent },
				      { path: '**', redirectTo: 'login' }
				    ]
				  }
				];
			5.- Se modifica protected-routing.module.ts, se agregan rutas hijas 
				const routes: Routes = [
				  {
				    path: '',
				    children: [
				      { path: '', component: DashboardComponent},
				      { path: '**', redirectTo: ''}
				    ]
				  }
				];
		************************************************************************************************************************************
		384. **************************************************************************************Diseño de la pantalla de Registro y Login
			1.- Se modifica main.component.html
				<div class="limiter">
				    <div class="container-login100" style="background-image: url('../../../../assets/images/bg-01.jpg');">
				        <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
				           <router-outlet></router-outlet>
				        </div>
				    </div>
				</div>
			2.- Se modifica auth.module.ts, se importa ReactiveFormsModule  
				@NgModule({
				  declarations: [
				    LoginComponent,
				    RegisterComponent,
				    MainComponent
				  ],
				  imports: [
				    CommonModule,
				    AuthRoutingModule, 
				    ReactiveFormsModule
				  ]
				})
				export class AuthModule { }
			3.- Se modifica login.component.ts, se crea el formGroup 
				myForm: FormGroup = this.formBuilder.group({
				    email: ['', [Validators.required, Validators.email]],
				    password: ['', [Validators.required, Validators.minLength(6)]]
				  })

				  constructor( private formBuilder: FormBuilder) { }

				    login(){
				    console.log(this.myForm.value);
				    console.log(this.myForm.valid);
				  }

			4.- Se modifica login.component.html se renderiza formulario reactivo  
				<form class="login100-form" autocomplete="off"
				    [formGroup]="myForm" 
				    (ngSubmit)="login()">
				                
				    <span class="login100-form-title p-b-49">
				        Login
				    </span>

				    <div class="wrap-input100 m-b-23">
				        <span class="label-input100">Email</span>
				        <input class="input100"
				               type="email" 
				               formControlName="email" 
				               placeholder="Ingrese su email">
				        <span class="focus-input100"></span>
				    </div>

				    <div class="wrap-input100">
				        <span class="label-input100">Password</span>
				        <input class="input100"
				               type="password"
				               formControlName="password" 
				               placeholder="Ingrese su contraseña">
				        <span class="focus-input100"></span>
				    </div>
				    
				    <div class="text-right p-t-8 p-b-31"></div>
				    
				    <div class="container-login100-form-btn">
				        <div class="wrap-login100-form-btn">
				            <div class="login100-form-bgbtn"></div>
				            <button class="login100-form-btn"  type="submit">
				                Login
				            </button>
				        </div>
				    </div>

				    <div class="flex-col-c p-t-60">
				        <span class="txt1 p-b-17">
				            ¿No tienes cuenta?
				        </span>

				        <a routerLink="/auth/register" class="txt2">
				            Crear una aquí
				        </a>
				    </div>
				</form>
			5.- Se modifica register.component.ts 
				import { Component, OnInit } from '@angular/core';
				import { FormBuilder, FormGroup, Validators } from '@angular/forms';

				@Component({
				  selector: 'app-register',
				  templateUrl: './register.component.html',
				  styles: [
				  ]
				})
				export class RegisterComponent implements OnInit {

				  myForm: FormGroup = this.formBuilder.group({
				    name: ['', [Validators.required, Validators.minLength(6)]],
				    email: ['', [Validators.required, Validators.email]],
				    pass: ['', [Validators.required, Validators.minLength(6)]],
				    
				  })

				  constructor( private formBuilder: FormBuilder) { }

				  ngOnInit(): void {
				  }

				  register(){
				    console.log(this.myForm.value);
				    console.log(this.myForm.valid);
				  }

				}
			6.- Se modifica register.component.ts 
				import { Component, OnInit } from '@angular/core';
				import { FormBuilder, FormGroup, Validators } from '@angular/forms';

				@Component({
				  selector: 'app-register',
				  templateUrl: './register.component.html',
				  styles: [
				  ]
				})
				export class RegisterComponent implements OnInit {

				  myForm: FormGroup = this.formBuilder.group({
				    name: ['', [Validators.required, Validators.minLength(6)]],
				    email: ['', [Validators.required, Validators.email]],
				    pass: ['', [Validators.required, Validators.minLength(6)]],
				    
				  })

				  constructor( private formBuilder: FormBuilder) { }

				  ngOnInit(): void {
				  }

				  register(){
				    console.log(this.myForm.value);
				    console.log(this.myForm.valid);
				  }

				}
		************************************************************************************************************************************
		385. *************************************************************************************************************Pantalla protegida
			1.- Se modifica dashboard.component.ts, se agrega navegacion en caso de logout 
				1.1.- Se crea metodo logout() 
					  logout(){
					    this.router.navigateByUrl('/auth');
					  }
				1.2.- Se modifica decorator de css para que todo el contenido del compnente tenga un margen   
					@Component({
					  selector: 'app-dashboard',
					  templateUrl: './dashboard.component.html',
					  styles: [
					    `
					    * {
					      margin: 15px;
					    }
					    `
					  ]
					})
					export class DashboardComponent implements OnInit {...}   

			2.- Se modifica dashboard.component.html, se renderiza vista   
				<h1>Dashborad</h1>
				<hr>

				<p>
				    Esta pagin solo fnciona si estas autenticado
				</p>

				<h4>User Info</h4>
				<pre>...</pre>

				<button (click)="logout()">
				    Logout
				</button>   
			3.- Se modifica register.component.ts, se agrega navegacion a dashboard   
				constructor( private formBuilder: FormBuilder, 
		        private router: Router) { }

				  register(){
				    console.log(this.myForm.valid);
				    this.router.navigateByUrl('/dashboard');
				  }

			4.- Se modifica login.component.ts, se agrega navegacion a componente dashboard   

				  constructor( private formBuilder: FormBuilder,
				                private router: Router) { }

				  ngOnInit(): void {
				  }

				  login(){
				    console.log(this.myForm.valid);
				    this.router.navigateByUrl('/dashboard');
				  }
		************************************************************************************************************************************
		386. *************************************************************************************************Login de usuario desde Angular
			1.- Se modifica app.module.ts, se importa HttpClientModule   
				@NgModule({
				  declarations: [
				    AppComponent
				  ],
				  imports: [
				    BrowserModule,
				    AppRoutingModule,
				    HttpClientModule
				  ],
				  providers: [],
				  bootstrap: [AppComponent]
				})
				export class AppModule { }

			2.- Se modifica environment.ts, se agrega variable de entorno 
				export const environment = {
				  production: false,
				  baseUrl: 'http://localhost:4000/api'
				};
			3.- Se crea clase de servicio --> 	ng g s auth/services/auth --skip-tests
			4.- Se modifica auth.service.ts, se crea servicio de login 
				@Injectable({
				  providedIn: 'root'
				})
				export class AuthService {

				  private baseUrl: String = environment.baseUrl

				  constructor( private http: HttpClient) { }

				  login( email: string, password: string ): Observable<AuthResponse>{
				    const url =`${this.baseUrl}/auth`
				    const body = { email, password };
				    return this.http.post<AuthResponse>(url, body);
				  }
				}
			5.- Se crea interface auth/interfaces/interface.ts 
				export interface AuthResponse {
				    "ok": boolean,
				    "uid"?: string,
				    "name"?: string,
				    "msg"?: string,
				    "jwt"?: string
				}
			6.- Se modifica login.component.ts, se modifica metodo de login para ejecutar el servicio 
				  constructor( private formBuilder: FormBuilder,
		                private router: Router,
		                private authServices: AuthService) { }

				  ngOnInit(): void {
				  }

				  login(){
				    console.log(this.myForm.valid);
				    const { email, password } = this.myForm.value;
				    this.authServices.login( email, password ).subscribe(
				      res => console.log(res)
				    );

				    //this.router.navigateByUrl('/dashboard');
				  }
		************************************************************************************************************************************
		387. *******************************************************************************************Almacenar la información del usuario
			1.- Se modifica interface.ts, se crea y exporta interface Usuario
				export interface Usuario{
				    uid: String, 
				    name: String 
				}
			2.- Se modifica auth.service.ts, se atributo usuario y se llena cn la respuesta del servicio login 
				2.1.- Se crea atributo auaurio con su get 
					 private _usuario!: Usuario;
		  
					  get usuario(){
					    return {...this._usuario};
					  }
				2.2.- Se modifica metodo login para que valide respuesta y si es afirmativa cargue la informacion de usuario 
					login( email: string, password: string ): Observable<boolean>{
				    const url =`${this.baseUrl}/auth`
				    const body = { email, password };
				    return this.http.post<AuthResponse>(url, body)
				      .pipe(
				        tap( resp => {
				            if(resp.ok){
				              this._usuario = {
				                name: resp.name!,
				                uid: resp.uid!
				              }
				            }
				          }),
				        map( resp => resp.ok ),
				        catchError( err => of(false))
				      );
				  }
			3.- Se modifica metodo login(), si respuesta del servicio es ok redirecciona al dashboard  
				login(){
				    const { email, password } = this.myForm.value;
				    this.authServices.login( email, password ).subscribe(
				      ok => {
				        if(ok){
				          this.router.navigateByUrl('/dashboard')
				        }else{
				          //TODO mostrar mensaje de error 
				        }
				      }
				    );

				  }
			4.- Se modifica dashboard.component.ts, se carga el usuario del servicio 
				  get usuario(){
				    return this.authService.usuario;
				  }

				  constructor( private router: Router, 
				                private authService: AuthService) { }

			5.- Se modifica dashboard.component.html, se renderiza info del usuario 
				<pre>{{ usuario | json }}</pre>
		************************************************************************************************************************************
		388. *****************************************************************************************************Mensajes de error visuales
			referencia --> switAlert2 --> https://sweetalert2.github.io/
			1.- Se instala SwuitAler 
				npm install sweetalert2
			2.- Se modifica login.component.ts 
				2.1.- Importa switAlert2 
					import swal from 'sweetalert2';
				2.2.- Se modifica el metodo login en caso de error 
					  login(){
				    console.log(this.myForm.valid);
				    const { email, password } = this.myForm.value;
				    this.authServices.login( email, password ).subscribe(
				      ok => {
				        if(ok === true){
				          this.router.navigateByUrl('/dashboard');
				        }else{
				          swal.fire('Error', ok,  'error')
				        }
				      }
				    );

				  }
		************************************************************************************************************************************
		389. **********************************************************************Mantener el usuario activo tras recargar el navegador web
			1.- Se modifica auth.service.ts
				1.1.- Se modifica metodo login(), se alamacena el token en el LocalStorage
					login( email: string, password: string ){
					    const url =`${this.baseUrl}/auth`
					    const body = { email, password };
					    return this.http.post<AuthResponse>(url, body)
					      .pipe(
					        tap( resp => {
					            if(resp.ok){
					              //Se almacena token en el localstorage
					              localStorage.setItem('token', resp.jwt!)
					              this._usuario = {
					                name: resp.name!,
					                uid: resp.uid!
					              }
					            }
					          }),
					        map( resp => resp.ok ),
					        catchError( err => of(err.error.msg))
					      );
					  }

				1.2.- Se crea metodo validarToken() 
					  validarToken(){
					    const url = `${ this.baseUrl }/auth/renew`;
					    const headers = new HttpHeaders().set('x-token', localStorage.getItem('token') || ' ');
					    return this.http.get(url, { headers });
					  }
		************************************************************************************************************************************
		390. ***********************************************************************************************************ValidarToken - Guard
			1.- Se crea guard 
				ng g guard guards/validarToken --skip-tests
					canActive
					canLoad 
			2.- Se modifica validar-token.guard.ts 
				@Injectable({
					  providedIn: 'root'
					})
					export class ValidarTokenGuard implements CanActivate, CanLoad {

					  constructor( private authService: AuthService,
					                private router: Router ){

					  }
					  canActivate(): Observable<boolean> | boolean {
					    console.log('CanActivate');
					    return this.authService.validarToken().pipe(
					            tap( valid => {
					              if (!valid){
					                this.router.navigateByUrl('/auth');
					              }
					            })
					          );
					  }
					  canLoad(): Observable<boolean> | boolean {
					    console.log('CanLoad');
					    return this.authService.validarToken().pipe(
					              tap( valid => {
					                if (!valid){
					                  this.router.navigateByUrl('/auth');
					                }
					              })
					            );
					  }
					}
			3.- Se modifica app-routing.module.ts, se agregan canLoad y canActivate 
				  {
				    path: 'dashboard',
				    loadChildren: () => import('./protected/protected.module').then(m => m.ProtectedModule),
				    canActivate: [ ValidarTokenGuard ],
				    canLoad: [ ValidarTokenGuard ]
				  },
			4.- Se modifica auth.service.ts, se modifica metodo validarToken() 
				validarToken(): Observable<boolean>{
				    const url = `${ this.baseUrl }/auth/renew`;
				    const headers = new HttpHeaders().set('x-token', localStorage.getItem('token') || ' ');
				    return this.http.get<AuthResponse>(url, { headers }).pipe(
				      map( resp => {
				        console.log(resp);
				        localStorage.setItem('token', resp.jwt!)
				        this._usuario = {
				          name: resp.name!,
				          uid: resp.uid!
				        }
				        return resp.ok;
				      }),
				      catchError( err => of(false))
				    );
				  }
		************************************************************************************************************************************
		391. *************************************************************************************************************************Logout
			1.- Se modifica auth.service.ts, se crea metodo logout() 
				  logout(){
				    localStorage.clear();
				  }
			2.- Se modifica dashboard.component.ts, se modifica metodo logout() 
				  logout(){
				    this.router.navigateByUrl('/auth');
				    this.authService.logout();
				  }
		************************************************************************************************************************************
		392. ****************************************************************************************************Tarea: Registro de usuarios	
		************************************************************************************************************************************
	******************************************************* Fin Seccion ****************************************************************
	***********************************Sección 26: Desplegar backend y frontend a produccion********************************************
		401. ****************************************************************************************Desplegar aplicación de Angular en Node
			1.- Se compila la aplicacion del front --> ng build --prod
			2.- Se copian todos los archivos creados de la carpeta dist/AuthApp
			3.- Nos ubicamos en la carpeta publica del backend y se borran los archivos index y style, se copian los archivos generados
			4.- Se prueban en el navegador --> localhost:4000/ , se debe rederizar el login 
			5.- En el front se modifica app-routing.module.ts, se le agrega hash al route 
				@NgModule({
				  imports: [RouterModule.forRoot(routes, {
				    useHash: true
				  })],
				  exports: [RouterModule]
				})
				export class AppRoutingModule { }

				Nota: Añadimos el literal {useHash: true} esto añadirá un # a la ruta, que es un viejo truco de los navegadores para evitar 
				que el navegador recargue la pagina.
			6.- Se compila la aplicacion del front --> ng build --prod
			7.- Se copian todos los archivos creados de la carpeta dist/AuthApp
			8.- Nos ubicamos en la carpeta public del backend y se borran los archivos, se copian los archivos generados
			9.- Se prueban en el navegador --> localhost:4000/ , se debe rederizar con el path --> http://localhost:4000/#/dashboard
		************************************************************************************************************************************
		402. Desplegar aplicación de Node a Heroku******************************************************************************************	
		************************************************************************************************************************************
********************************************************* Fin Seccion ****************************************************************



****************************************************Angular Fit Avanzado**************************************************************
	**************************************************************************************************************************************
	**************************************************************************************************************************************
	*********************************Sección 2: Dominando la creación automática del Angular CLI******************************************
	7. *****************************************************************************************************Uso de la ayuda del AngularCLI
		*ng help --> ayuda
		1.- Se crea la aplicacion --> ng new clitest
		Referencia --> https://angular.io/cli
	**************************************************************************************************************************************
	8. ********************************************************************************************Control de la generación de componentes
		* ng g c --help --> para solicitar ayuda
		1.- Se genera componente home
			ng g c pages/home -is  --> -is para no crear archivo css 
		2.- Se crea componente about 
			ng g c pages/about/about --flat --skip-test
		3.- Se crea componente contact 
			ng g c pages/contact/contact --flat -is --skip-tests
		4.- Se modifica app.component.ts 
			<app-home></app-home>
			<app-contact></app-contact>
	**************************************************************************************************************************************
	9. **********************************************************************************************************Paths, servicios y guards
		* ng g s --help --> solicitar ayuda 
		1.- Se genera servicio AuthService
			ng g s services/auth --skip-test 
		2.- Se modifica auth.service.ts, se modifica constructor con console.log 
			  constructor() {
			    console.log('Hola desde el servicio auth')
			   }
		3.- Se modifica home.component.ts, se realiza la injeccion de dependencia del servicio
			  constructor( private authService: AuthService) { }
		Nota: se debe probar e inspeccionar en el navegador el mensaje del servicio 
		4.- Se genera servicio user 
			ng g s services/user --dry-run  --> hace una simulacion de creacion de archivo 
			ng g s services/user --skip-tests
		5.- Generacion de guard 
			ng g --help  --> para solicitar ayuda 
			ng g guard guard/auth --skip-tests
	**************************************************************************************************************************************
	**************************************************************************************************************************************
	********************************************************Fin Seccion*******************************************************************
	****************************************Sección 3: Estructuración de nuestro proyecto*************************************************
		15. *****************************************************************************************************Inicio de proyecto - AdminPro
			1.- Se descarga material  
			2.- Se accede a link de tempates --> https://www.wrappixel.com/templates/category/dashboard-templates/
			3.- Se crea proyecto -->  ng new adminpro
		**************************************************************************************************************************************
		17. ***********************************************************************Primeros componentes e inicio de la estructura del proyecto
			1.- Se genera componente login --> ng g c auth/login --skip-tests
			2.- Se genera componente register --> ng g c auth/register --skip-tests
			3.- Se genera componente nopagesfound --> ng g c pages/nopagesfound --skip-tests
			4.- Se genera componente dashboard --> ng g c pages/dashboard --skip-tests
			5.- Se genera componente breadcrumbs --> ng g c shared/breadcrumbs --skip-tests
			6.- Se genera componente sidebar --> ng g c shared/sidebar --skip-tests
			7.- Se genera componente header --> ng g c shared/header --skip-tests
		**************************************************************************************************************************************
		18. *****************************************************************************************Agregar las librerías externas necesarias
			1.- Se copian las siguientes carpetas del material de la seccion a la carpeta assets del proyecto 
				css, images, js, plugins 
			2.- Se realiza el import de la librerias
				2.1.- Se abre el archivo pages-blanck del material de la seccion  
				2.2.- Se modifica el index.html del proyecto con las importaciones copiadas del archivo pages-blank.html 
					<!doctype html>
						<html lang="en">
						<head>
						  <meta charset="utf-8">
						  <title>Adminpro</title>
						  <base href="/">
						  <meta name="viewport" content="width=device-width, initial-scale=1">
						  <!-- Favicon icon -->
						  <link rel="icon" type="image/png" sizes="16x16" href="./assets/images/favicon.png">
						  <!-- Bootstrap Core CSS -->
						  <link href="./assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
						  <!-- Custom CSS -->
						  <link href="./assets/css/style.css" rel="stylesheet">
						  <!-- You can change the theme colors from here -->
						  <link href="./assets/css/colors/default-dark.css" id="theme" rel="stylesheet">
						</head>
						<body>
						  <app-root></app-root>


						    <!-- ============================================================== -->
						    <!-- All Jquery -->
						    <!-- ============================================================== -->
						    <script src="./assets/plugins/jquery/jquery.min.js"></script>
						    <!-- Bootstrap tether Core JavaScript -->
						    <script src="./assets/plugins/bootstrap/js/popper.min.js"></script>
						    <script src="./assets/plugins/bootstrap/js/bootstrap.min.js"></script>
						    <!-- slimscrollbar scrollbar JavaScript -->
						    <script src="./assets/js/perfect-scrollbar.jquery.min.js"></script>
						    <!--Wave Effects -->
						    <script src="./assets/js/waves.js"></script>
						    <!--Menu sidebar -->
						    <script src="./assets/js/sidebarmenu.js"></script>
						    <!--stickey kit -->
						    <script src="./assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
						    <script src="./assets/plugins/sparkline/jquery.sparkline.min.js"></script>
						    <!--Custom JavaScript -->
						    <script src="./assets/js/custom.min.js"></script>
						    <!-- ============================================================== -->
						    <!-- Style switcher -->
						    <!-- ============================================================== -->
						    <script src="./assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
						</body>
					</html>				   
		**************************************************************************************************************************************
		19. ******************************************************************************Header, SiderBar, Breadcrumbs y contenedor principal
			1.- Se modifica el index.html, se le agrega clase al body y preloader del archivo pages-banck 
				<!doctype html>
				<html lang="en">
				<head>
				  <meta charset="utf-8">
				  <title>Adminpro</title>
				  <base href="/">
				  <meta name="viewport" content="width=device-width, initial-scale=1">
				  <!-- Favicon icon -->
				  <link rel="icon" type="image/png" sizes="16x16" href="./assets/images/favicon.png">
				  <!-- Bootstrap Core CSS -->
				  <link href="./assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
				  <!-- Custom CSS -->
				  <link href="./assets/css/style.css" rel="stylesheet">
				  <!-- You can change the theme colors from here -->
				  <link href="./assets/css/colors/default-dark.css" id="theme" rel="stylesheet">
				</head>
				<body class="fix-header card-no-border fix-sidebar">
				  <!-- ============================================================== -->
				  <!-- Preloader - style you can find in spinners.css -->
				  <!-- ============================================================== -->
				  <div class="preloader">
				      <div class="loader">
				          <div class="loader__figure"></div>
				          <p class="loader__label">Admin Pro</p>
				      </div>
				  </div>


				  <app-root></app-root>


				      <!-- ============================================================== -->
				    <!-- All Jquery -->
				    <!-- ============================================================== -->
				    <script src="./assets/plugins/jquery/jquery.min.js"></script>
				    <!-- Bootstrap tether Core JavaScript -->
				    <script src="./assets/plugins/bootstrap/js/popper.min.js"></script>
				    <script src="./assets/plugins/bootstrap/js/bootstrap.min.js"></script>
				    <!-- slimscrollbar scrollbar JavaScript -->
				    <script src="./assets/js/perfect-scrollbar.jquery.min.js"></script>
				    <!--Wave Effects -->
				    <script src="./assets/js/waves.js"></script>
				    <!--Menu sidebar -->
				    <script src="./assets/js/sidebarmenu.js"></script>
				    <!--stickey kit -->
				    <script src="./assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
				    <script src="./assets/plugins/sparkline/jquery.sparkline.min.js"></script>
				    <!--Custom JavaScript -->
				    <script src="./assets/js/custom.min.js"></script>
				    <!-- ============================================================== -->
				    <!-- Style switcher -->
				    <!-- ============================================================== -->
				    <script src="./assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
				</body>
				</html>
			2.- Se copia header del archivo pages-bank.html, se copia en header.component.html y se corrigen la ruta de los assets 
				  <!-- ============================================================== -->
				  <!-- Topbar header - style you can find in pages.scss -->
				  <!-- ============================================================== -->
				  <header class="topbar">
				    <nav class="navbar top-navbar navbar-expand-md navbar-light">
				        <!-- ============================================================== -->
				        <!-- Logo -->
				        <!-- ============================================================== -->
				        <div class="navbar-header">
				            <a class="navbar-brand" href="index.html">
				                <!-- Logo icon --><b>
				                    <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
				                    <!-- Dark Logo icon -->
				                    <img src="./assets/images/logo-icon.png" alt="homepage" class="dark-logo" />
				                    <!-- Light Logo icon -->
				                    /*<img src="./assets/images/logo-light-icon.png" alt="homepage" class="light-logo" />
				                </b>
				                <!--End Logo icon -->
				                <!-- Logo text --><span>
				                  <!-- dark Logo text -->
				                  <img src="./assets/images/logo-text.png" alt="homepage" class="dark-logo" />
				                  <!-- Light Logo text -->
				                  <img src="./assets/images/logo-light-text.png" class="light-logo" alt="homepage" /></span> </a>
				        </div>
				        <!-- ============================================================== -->
				        <!-- End Logo -->
				        <!-- ============================================================== -->
				        <div class="navbar-collapse">
				            <!-- ============================================================== -->
				            <!-- toggle and nav items -->
				            <!-- ============================================================== -->
				            <ul class="navbar-nav mr-auto">
				                <!-- This is  -->
				                <li class="nav-item"> <a class="nav-link nav-toggler hidden-md-up waves-effect waves-dark" href="javascript:void(0)"><i class="ti-menu"></i></a> </li>
				                <li class="nav-item"> <a class="nav-link sidebartoggler hidden-sm-down waves-effect waves-dark" href="javascript:void(0)"><i class="ti-menu"></i></a> </li>
				                <li class="nav-item hidden-sm-down"></li>
				            </ul>
				            <!-- ============================================================== -->
				            <!-- User profile and search -->
				            <!-- ============================================================== -->
				            <ul class="navbar-nav my-lg-0">
				                <!-- ============================================================== -->
				                <!-- Search -->
				                <!-- ============================================================== -->
				                <li class="nav-item hidden-xs-down search-box"> <a class="nav-link hidden-sm-down waves-effect waves-dark" href="javascript:void(0)"><i class="ti-search"></i></a>
				                    <form class="app-search">
				                        <input type="text" class="form-control" placeholder="Search & enter"> <a class="srh-btn"><i class="ti-close"></i></a> </form>
				                </li>
				                <!-- ============================================================== -->
				                <!-- Comment -->
				                <!-- ============================================================== -->
				                <li class="nav-item dropdown">
				                    <a class="nav-link dropdown-toggle waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="mdi mdi-message"></i>
				                        <div class="notify"> <span class="heartbit"></span> <span class="point"></span> </div>
				                    </a>
				                    <div class="dropdown-menu dropdown-menu-right mailbox animated bounceInDown">
				                        <ul>
				                            <li>
				                                <div class="drop-title">Notifications</div>
				                            </li>
				                            <li>
				                                <div class="message-center">
				                                    <!-- Message -->
				                                    <a href="#">
				                                        <div class="btn btn-danger btn-circle"><i class="fa fa-link"></i></div>
				                                        <div class="mail-contnet">
				                                            <h5>Luanch Admin</h5> <span class="mail-desc">Just see the my new admin!</span> <span class="time">9:30 AM</span> </div>
				                                    </a>
				                                    <!-- Message -->
				                                    <a href="#">
				                                        <div class="btn btn-success btn-circle"><i class="ti-calendar"></i></div>
				                                        <div class="mail-contnet">
				                                            <h5>Event today</h5> <span class="mail-desc">Just a reminder that you have event</span> <span class="time">9:10 AM</span> </div>
				                                    </a>
				                                    <!-- Message -->
				                                    <a href="#">
				                                        <div class="btn btn-info btn-circle"><i class="ti-settings"></i></div>
				                                        <div class="mail-contnet">
				                                            <h5>Settings</h5> <span class="mail-desc">You can customize this template as you want</span> <span class="time">9:08 AM</span> </div>
				                                    </a>
				                                    <!-- Message -->
				                                    <a href="#">
				                                        <div class="btn btn-primary btn-circle"><i class="ti-user"></i></div>
				                                        <div class="mail-contnet">
				                                            <h5>Pavan kumar</h5> <span class="mail-desc">Just see the my admin!</span> <span class="time">9:02 AM</span> </div>
				                                    </a>
				                                </div>
				                            </li>
				                            <li>
				                                <a class="nav-link text-center" href="javascript:void(0);"> <strong>Check all notifications</strong> <i class="fa fa-angle-right"></i> </a>
				                            </li>
				                        </ul>
				                    </div>
				                </li>
				                <!-- ============================================================== -->
				                <!-- End Comment -->
				                <!-- ============================================================== -->
				                <!-- ============================================================== -->
				                <!-- Messages -->
				                <!-- ============================================================== -->
				                <li class="nav-item dropdown">
				                    <a class="nav-link dropdown-toggle waves-effect waves-dark" href="" id="2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="mdi mdi-email"></i>
				                        <div class="notify"> <span class="heartbit"></span> <span class="point"></span> </div>
				                    </a>
				                    <div class="dropdown-menu mailbox dropdown-menu-right animated bounceInDown" aria-labelledby="2">
				                        <ul>
				                            <li>
				                                <div class="drop-title">You have 4 new messages</div>
				                            </li>
				                            <li>
				                                <div class="message-center">
				                                    <!-- Message -->
				                                    <a href="#">
				                                        <div class="user-img"> <img src="./assets/images/users/1.jpg" alt="user" class="img-circle"> <span class="profile-status online pull-right"></span> </div>
				                                        <div class="mail-contnet">
				                                            <h5>Pavan kumar</h5> <span class="mail-desc">Just see the my admin!</span> <span class="time">9:30 AM</span> </div>
				                                    </a>
				                                    <!-- Message -->
				                                    <a href="#">
				                                        <div class="user-img"> <img src="./assets/images/users/2.jpg" alt="user" class="img-circle"> <span class="profile-status busy pull-right"></span> </div>
				                                        <div class="mail-contnet">
				                                            <h5>Sonu Nigam</h5> <span class="mail-desc">I've sung a song! See you at</span> <span class="time">9:10 AM</span> </div>
				                                    </a>
				                                    <!-- Message -->
				                                    <a href="#">
				                                        <div class="user-img"> <img src="./assets/images/users/3.jpg" alt="user" class="img-circle"> <span class="profile-status away pull-right"></span> </div>
				                                        <div class="mail-contnet">
				                                            <h5>Arijit Sinh</h5> <span class="mail-desc">I am a singer!</span> <span class="time">9:08 AM</span> </div>
				                                    </a>
				                                    <!-- Message -->
				                                    <a href="#">
				                                        <div class="user-img"> <img src="./assets/images/users/4.jpg" alt="user" class="img-circle"> <span class="profile-status offline pull-right"></span> </div>
				                                        <div class="mail-contnet">
				                                            <h5>Pavan kumar</h5> <span class="mail-desc">Just see the my admin!</span> <span class="time">9:02 AM</span> </div>
				                                    </a>
				                                </div>
				                            </li>
				                            <li>
				                                <a class="nav-link text-center" href="javascript:void(0);"> <strong>See all e-Mails</strong> <i class="fa fa-angle-right"></i> </a>
				                            </li>
				                        </ul>
				                    </div>
				                </li>
				                <!-- ============================================================== -->
				                <!-- End Messages -->
				                <!-- ============================================================== -->
				                <!-- ============================================================== -->
				                <!-- mega menu -->
				                <!-- ============================================================== -->
				                <li class="nav-item dropdown mega-dropdown"> <a class="nav-link dropdown-toggle waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="mdi mdi-view-grid"></i></a>
				                    <div class="dropdown-menu animated bounceInDown">
				                        <ul class="mega-dropdown-menu row">
				                            <li class="col-lg-3 col-xlg-2 m-b-30">
				                                <h4 class="m-b-20">CAROUSEL</h4>
				                                <!-- CAROUSEL -->
				                                <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
				                                    <div class="carousel-inner" role="listbox">
				                                        <div class="carousel-item active">
				                                            <div class="container"> <img class="d-block img-fluid" src="./assets/images/big/img1.jpg" alt="First slide"></div>
				                                        </div>
				                                        <div class="carousel-item">
				                                            <div class="container"><img class="d-block img-fluid" src="./assets/images/big/img2.jpg" alt="Second slide"></div>
				                                        </div>
				                                        <div class="carousel-item">
				                                            <div class="container"><img class="d-block img-fluid" src="./assets/images/big/img3.jpg" alt="Third slide"></div>
				                                        </div>
				                                    </div>
				                                    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev"> <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="sr-only">Previous</span> </a>
				                                    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next"> <span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="sr-only">Next</span> </a>
				                                </div>
				                                <!-- End CAROUSEL -->
				                            </li>
				                            <li class="col-lg-3 m-b-30">
				                                <h4 class="m-b-20">ACCORDION</h4>
				                                <!-- Accordian -->
				                                <div id="accordion" class="nav-accordion" role="tablist" aria-multiselectable="true">
				                                    <div class="card">
				                                        <div class="card-header" role="tab" id="headingOne">
				                                            <h5 class="mb-0">
				                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
				                                          Collapsible Group Item #1
				                                        </a>
				                                            </h5>
				                                        </div>
				                                        <div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne">
				                                            <div class="card-body"> Anim pariatur cliche reprehenderit, enim eiusmod high. </div>
				                                        </div>
				                                    </div>
				                                    <div class="card">
				                                        <div class="card-header" role="tab" id="headingTwo">
				                                            <h5 class="mb-0">
				                                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
				                                          Collapsible Group Item #2
				                                        </a>
				                                            </h5>
				                                        </div>
				                                        <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
				                                            <div class="card-body"> Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. </div>
				                                        </div>
				                                    </div>
				                                    <div class="card">
				                                        <div class="card-header" role="tab" id="headingThree">
				                                            <h5 class="mb-0">
				                                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
				                                          Collapsible Group Item #3
				                                        </a>
				                                            </h5>
				                                        </div>
				                                        <div id="collapseThree" class="collapse" role="tabpanel" aria-labelledby="headingThree">
				                                            <div class="card-body"> Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. </div>
				                                        </div>
				                                    </div>
				                                </div>
				                            </li>
				                            <li class="col-lg-3  m-b-30">
				                                <h4 class="m-b-20">CONTACT US</h4>
				                                <!-- Contact -->
				                                <form>
				                                    <div class="form-group">
				                                        <input type="text" class="form-control" id="exampleInputname1" placeholder="Enter Name"> </div>
				                                    <div class="form-group">
				                                        <input type="email" class="form-control" placeholder="Enter email"> </div>
				                                    <div class="form-group">
				                                        <textarea class="form-control" id="exampleTextarea" rows="3" placeholder="Message"></textarea>
				                                    </div>
				                                    <button type="submit" class="btn btn-info">Submit</button>
				                                </form>
				                            </li>
				                            <li class="col-lg-3 col-xlg-4 m-b-30">
				                                <h4 class="m-b-20">List style</h4>
				                                <!-- List style -->
				                                <ul class="list-style-none">
				                                    <li><a href="javascript:void(0)"><i class="fa fa-check text-success"></i> You can give link</a></li>
				                                    <li><a href="javascript:void(0)"><i class="fa fa-check text-success"></i> Give link</a></li>
				                                    <li><a href="javascript:void(0)"><i class="fa fa-check text-success"></i> Another Give link</a></li>
				                                    <li><a href="javascript:void(0)"><i class="fa fa-check text-success"></i> Forth link</a></li>
				                                    <li><a href="javascript:void(0)"><i class="fa fa-check text-success"></i> Another fifth link</a></li>
				                                </ul>
				                            </li>
				                        </ul>
				                    </div>
				                </li>
				                <!-- ============================================================== -->
				                <!-- End mega menu -->
				                <!-- ============================================================== -->
				                <!-- ============================================================== -->
				                <!-- Language -->
				                <!-- ============================================================== -->
				                <li class="nav-item dropdown">
				                    <a class="nav-link dropdown-toggle waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="flag-icon flag-icon-us"></i></a>
				                    <div class="dropdown-menu dropdown-menu-right animated bounceInDown"> <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-in"></i> India</a> <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-fr"></i> French</a> <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-cn"></i> China</a>                                <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-de"></i> Dutch</a> </div>
				                </li>
				                <!-- ============================================================== -->
				                <!-- Profile -->
				                <!-- ============================================================== -->
				                <li class="nav-item dropdown">
				                    <a class="nav-link dropdown-toggle waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="./assets/images/users/1.jpg" alt="user" class="profile-pic" /></a>
				                    <div class="dropdown-menu dropdown-menu-right animated flipInY">
				                        <ul class="dropdown-user">
				                            <li>
				                                <div class="dw-user-box">
				                                    <div class="u-img"><img src="./assets/images/users/1.jpg" alt="user"></div>
				                                    <div class="u-text">
				                                        <h4>Steave Jobs</h4>
				                                        <p class="text-muted">varun@gmail.com</p><a href="pages-profile.html" class="btn btn-rounded btn-danger btn-sm">View Profile</a></div>
				                                </div>
				                            </li>
				                            <li role="separator" class="divider"></li>
				                            <li><a href="#"><i class="ti-user"></i> My Profile</a></li>
				                            <li><a href="#"><i class="ti-wallet"></i> My Balance</a></li>
				                            <li><a href="#"><i class="ti-email"></i> Inbox</a></li>
				                            <li role="separator" class="divider"></li>
				                            <li><a href="#"><i class="ti-settings"></i> Account Setting</a></li>
				                            <li role="separator" class="divider"></li>
				                            <li><a href="#"><i class="fa fa-power-off"></i> Logout</a></li>
				                        </ul>
				                    </div>
				                </li>
				            </ul>
				        </div>
				    </nav>
				</header>
				<!-- ============================================================== -->
				<!-- End Topbar header -->
				<!-- ============================================================== -->*/
			3.- Se copia sidebar del archivo pages-bank.html, se copia en sidebar.component.html y se corrigen la ruta de los assets
				<!-- ============================================================== -->
				  <!-- Left Sidebar - style you can find in sidebar.scss  -->
				  <!-- ============================================================== -->
				  /*<aside class="left-sidebar">
				    <!-- Sidebar scroll-->
				    <div class="scroll-sidebar">
				        <!-- Sidebar navigation-->
				        <nav class="sidebar-nav">
				            <ul id="sidebarnav">
				                <li class="user-profile">
				                    <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><img src="./assets/images/users/profile.png" alt="user" /><span class="hide-menu">Steave Jobs </span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="javascript:void()">My Profile </a></li>
				                        <li><a href="javascript:void()">My Balance</a></li>
				                        <li><a href="javascript:void()">Inbox</a></li>
				                        <li><a href="javascript:void()">Account Setting</a></li>
				                        <li><a href="javascript:void()">Logout</a></li>
				                    </ul>
				                </li>
				                <li class="nav-devider"></li>
				                <li class="nav-small-cap">PERSONAL</li>
				                <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-gauge"></i><span class="hide-menu">Dashboard <span class="label label-rouded label-themecolor pull-right">4</span></span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="index.html">Minimal </a></li>
				                        <li><a href="index2.html">Analytical</a></li>
				                        <li><a href="index3.html">Demographical</a></li>
				                        <li><a href="index4.html">Modern</a></li>
				                    </ul>
				                </li>
				                <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-bullseye"></i><span class="hide-menu">Apps</span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="app-calendar.html">Calendar</a></li>
				                        <li><a href="app-chat.html">Chat app</a></li>
				                        <li><a href="app-ticket.html">Support Ticket</a></li>
				                        <li><a href="app-contact.html">Contact / Employee</a></li>
				                        <li><a href="app-contact2.html">Contact Grid</a></li>
				                        <li><a href="app-contact-detail.html">Contact Detail</a></li>
				                    </ul>
				                </li>
				                <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-email"></i><span class="hide-menu">Inbox</span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="app-email.html">Mailbox</a></li>
				                        <li><a href="app-email-detail.html">Mailbox Detail</a></li>
				                        <li><a href="app-compose.html">Compose Mail</a></li>
				                    </ul>
				                </li>
				                <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-chart-bubble"></i><span class="hide-menu">Ui Elements <span class="label label-rouded label-danger pull-right">25</span></span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="ui-cards.html">Cards</a></li>
				                        <li><a href="ui-user-card.html">User Cards</a></li>
				                        <li><a href="ui-buttons.html">Buttons</a></li>
				                        <li><a href="ui-modals.html">Modals</a></li>
				                        <li><a href="ui-tab.html">Tab</a></li>
				                        <li><a href="ui-tooltip-popover.html">Tooltip &amp; Popover</a></li>
				                        <li><a href="ui-tooltip-stylish.html">Tooltip stylish</a></li>
				                        <li><a href="ui-sweetalert.html">Sweet Alert</a></li>
				                        <li><a href="ui-notification.html">Notification</a></li>
				                        <li><a href="ui-progressbar.html">Progressbar</a></li>
				                        <li><a href="ui-nestable.html">Nestable</a></li>
				                        <li><a href="ui-range-slider.html">Range slider</a></li>
				                        <li><a href="ui-timeline.html">Timeline</a></li>
				                        <li><a href="ui-typography.html">Typography</a></li>
				                        <li><a href="ui-horizontal-timeline.html">Horizontal Timeline</a></li>
				                        <li><a href="ui-session-timeout.html">Session Timeout</a></li>
				                        <li><a href="ui-session-ideal-timeout.html">Session Ideal Timeout</a></li>
				                        <li><a href="ui-bootstrap.html">Bootstrap Ui</a></li>
				                        <li><a href="ui-breadcrumb.html">Breadcrumb</a></li>
				                        <li><a href="ui-bootstrap-switch.html">Bootstrap Switch</a></li>
				                        <li><a href="ui-list-media.html">List Media</a></li>
				                        <li><a href="ui-ribbons.html">Ribbons</a></li>
				                        <li><a href="ui-grid.html">Grid</a></li>
				                        <li><a href="ui-carousel.html">Carousel</a></li>
				                        <li><a href="ui-date-paginator.html">Date-paginator</a></li>
				                        <li><a href="ui-dragable-portlet.html">Dragable Portlet</a></li>
				                    </ul>
				                </li>
				                <li class="nav-small-cap">FORMS, TABLE &amp; WIDGETS</li>
				                <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-file"></i><span class="hide-menu">Forms</span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="form-basic.html">Basic Forms</a></li>
				                        <li><a href="form-layout.html">Form Layouts</a></li>
				                        <li><a href="form-addons.html">Form Addons</a></li>
				                        <li><a href="form-material.html">Form Material</a></li>
				                        <li><a href="form-float-input.html">Floating Lable</a></li>
				                        <li><a href="form-pickers.html">Form Pickers</a></li>
				                        <li><a href="form-upload.html">File Upload</a></li>
				                        <li><a href="form-mask.html">Form Mask</a></li>
				                        <li><a href="form-validation.html">Form Validation</a></li>
				                        <li><a href="form-dropzone.html">File Dropzone</a></li>
				                        <li><a href="form-icheck.html">Icheck control</a></li>
				                        <li><a href="form-img-cropper.html">Image Cropper</a></li>
				                        <li><a href="form-bootstrapwysihtml5.html">HTML5 Editor</a></li>
				                        <li><a href="form-typehead.html">Form Typehead</a></li>
				                        <li><a href="form-wizard.html">Form Wizard</a></li>
				                        <li><a href="form-xeditable.html">Xeditable Editor</a></li>
				                        <li><a href="form-summernote.html">Summernote Editor</a></li>
				                        <li><a href="form-tinymce.html">Tinymce Editor</a></li>
				                    </ul>
				                </li>
				                <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-table"></i><span class="hide-menu">Tables</span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="table-basic.html">Basic Tables</a></li>
				                        <li><a href="table-layout.html">Table Layouts</a></li>
				                        <li><a href="table-data-table.html">Data Tables</a></li>
				                        <li><a href="table-footable.html">Footable</a></li>
				                        <li><a href="table-jsgrid.html">Js Grid Table</a></li>
				                        <li><a href="table-responsive.html">Responsive Table</a></li>
				                        <li><a href="table-bootstrap.html">Bootstrap Tables</a></li>
				                        <li><a href="table-editable-table.html">Editable Table</a></li>
				                    </ul>
				                </li>
				                <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-widgets"></i><span class="hide-menu">Widgets</span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="widget-data.html">Data Widgets</a></li>
				                        <li><a href="widget-apps.html">Apps Widgets</a></li>
				                        <li><a href="widget-charts.html">Charts Widgets</a></li>

				                    </ul>
				                </li>
				                <li class="nav-small-cap">EXTRA COMPONENTS</li>
				                <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-book-multiple"></i><span class="hide-menu">Page Layout</span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="layout-single-column.html">1 Column</a></li>
				                        <li><a href="layout-fix-header.html">Fix header</a></li>
				                        <li><a href="layout-fix-sidebar.html">Fix sidebar</a></li>
				                        <li><a href="layout-fix-header-sidebar.html">Fixe header &amp; Sidebar</a></li>
				                        <li><a href="layout-boxed.html">Boxed Layout</a></li>
				                        <li><a href="layout-logo-center.html">Logo in Center</a></li>
				                    </ul>
				                </li>
				                <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-book-open-variant"></i><span class="hide-menu">Sample Pages <span class="label label-rouded label-success pull-right">25</span></span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="starter-kit.html">Starter Kit</a></li>
				                        <li><a href="pages-blank.html">Blank page</a></li>
				                        <li><a href="#" class="has-arrow">Authentication <span class="label label-rounded label-success pull-right">6</span></a>
				                            <ul aria-expanded="false" class="collapse">
				                                <li><a href="pages-login.html">Login 1</a></li>
				                                <li><a href="pages-login-2.html">Login 2</a></li>
				                                <li><a href="pages-register.html">Register</a></li>
				                                <li><a href="pages-register2.html">Register 2</a></li>
				                                <li><a href="pages-lockscreen.html">Lockscreen</a></li>
				                                <li><a href="pages-recover-password.html">Recover password</a></li>
				                            </ul>
				                        </li>
				                        <li><a href="pages-profile.html">Profile page</a></li>
				                        <li><a href="pages-animation.html">Animation</a></li>
				                        <li><a href="pages-fix-innersidebar.html">Sticky Left sidebar</a></li>
				                        <li><a href="pages-fix-inner-right-sidebar.html">Sticky Right sidebar</a></li>
				                        <li><a href="pages-invoice.html">Invoice</a></li>
				                        <li><a href="pages-treeview.html">Treeview</a></li>
				                        <li><a href="pages-utility-classes.html">Helper Classes</a></li>
				                        <li><a href="pages-search-result.html">Search result</a></li>
				                        <li><a href="pages-scroll.html">Scrollbar</a></li>
				                        <li><a href="pages-pricing.html">Pricing</a></li>
				                        <li><a href="pages-lightbox-popup.html">Lighbox popup</a></li>
				                        <li><a href="pages-gallery.html">Gallery</a></li>
				                        <li><a href="pages-faq.html">Faqs</a></li>
				                        <li><a href="#" class="has-arrow">Error Pages</a>
				                            <ul aria-expanded="false" class="collapse">
				                                <li><a href="pages-error-400.html">400</a></li>
				                                <li><a href="pages-error-403.html">403</a></li>
				                                <li><a href="pages-error-404.html">404</a></li>
				                                <li><a href="pages-error-500.html">500</a></li>
				                                <li><a href="pages-error-503.html">503</a></li>
				                            </ul>
				                        </li>
				                    </ul>
				                </li>
				                <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-file-chart"></i><span class="hide-menu">Charts</span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="chart-morris.html">Morris Chart</a></li>
				                        <li><a href="chart-chartist.html">Chartis Chart</a></li>
				                        <li><a href="chart-echart.html">Echarts</a></li>
				                        <li><a href="chart-flot.html">Flot Chart</a></li>
				                        <li><a href="chart-knob.html">Knob Chart</a></li>
				                        <li><a href="chart-chart-js.html">Chartjs</a></li>
				                        <li><a href="chart-sparkline.html">Sparkline Chart</a></li>
				                        <li><a href="chart-extra-chart.html">Extra chart</a></li>
				                        <li><a href="chart-peity.html">Peity Charts</a></li>
				                    </ul>
				                </li>
				                <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-brush"></i><span class="hide-menu">Icons</span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="icon-material.html">Material Icons</a></li>
				                        <li><a href="icon-fontawesome.html">Fontawesome Icons</a></li>
				                        <li><a href="icon-themify.html">Themify Icons</a></li>
				                        <li><a href="icon-linea.html">Linea Icons</a></li>
				                        <li><a href="icon-weather.html">Weather Icons</a></li>
				                        <li><a href="icon-simple-lineicon.html">Simple Lineicons</a></li>
				                        <li><a href="icon-flag.html">Flag Icons</a></li>
				                    </ul>
				                </li>
				                <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-map-marker"></i><span class="hide-menu">Maps</span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="map-google.html">Google Maps</a></li>
				                        <li><a href="map-vector.html">Vector Maps</a></li>
				                    </ul>
				                </li>
				                <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-arrange-send-backward"></i><span class="hide-menu">Multi level dd</span></a>
				                    <ul aria-expanded="false" class="collapse">
				                        <li><a href="#">item 1.1</a></li>
				                        <li><a href="#">item 1.2</a></li>
				                        <li> <a class="has-arrow" href="#" aria-expanded="false">Menu 1.3</a>
				                            <ul aria-expanded="false" class="collapse">
				                                <li><a href="#">item 1.3.1</a></li>
				                                <li><a href="#">item 1.3.2</a></li>
				                                <li><a href="#">item 1.3.3</a></li>
				                                <li><a href="#">item 1.3.4</a></li>
				                            </ul>
				                        </li>
				                        <li><a href="#">item 1.4</a></li>
				                    </ul>
				                </li>
				            </ul>
				        </nav>
				        <!-- End Sidebar navigation -->
				    </div>
				    <!-- End Sidebar scroll-->
				</aside>*/


				<!-- ============================================================== -->
				<!-- End Left Sidebar - style you can find in sidebar.scss  -->
				<!-- ============================================================== -->
			4.- Se genera componente footer --> ng g c shared/footer --skip-tests
				<!-- ============================================================== -->
				<!-- footer -->
				<!-- ============================================================== -->
				<footer class="footer">
				    © 2021 Admin Pro by wrappixel.com
				</footer>
				<!-- ============================================================== -->
				<!-- End footer -->
				<!-- ============================================================== -->
			5.- Se copia breadcrumb del archivo pages-bank.html, se copia en breadcrumbs.component.html y se corrigen la ruta de los assets
				<!-- ============================================================== -->
				<!-- Bread crumb and right sidebar toggle -->
				<!-- ============================================================== -->
				<div class="row page-titles">
				  <div class="col-md-5 align-self-center">
				      <h3 class="text-themecolor">Blank Page</h3>
				  </div>

				  <div class="col-md-7 align-self-center">
				      <ol class="breadcrumb">
				          <li class="breadcrumb-item">
				              <a href="javascript:void(0)">Home</a>
				          </li>
				          <li class="breadcrumb-item">pages</li>
				          <li class="breadcrumb-item active">Blank Page</li>
				      </ol>
				  </div>
				  <div>
				      <button class="right-side-toggle waves-effect waves-light btn-inverse btn btn-circle btn-sm pull-right m-l-10"><i class="ti-settings text-white"></i></button>
				  </div>
				</div>
				<!-- ============================================================== -->
				<!-- End Bread crumb and right sidebar toggle -->
				<!-- ============================================================== --> 
			6.- Se modifica app.component.html, se incluyen los componentes 
				<div id="main-wrapper">
				  <app-header></app-header>
				  <app-sidebar></app-sidebar>
				  <div class="page-wrapper">
				    <div class="container-fluid">
				      <!-- Bread crumb and right sidebar toggle -->
				      <app-breadcrumbs></app-breadcrumbs>
				      <!-- Sistema de rutas  -->

				      <!-- ============================================================== -->
				      <!-- Start Page Content -->
				      <!-- ============================================================== -->
				      <div class="row">
				        <div class="col-12">
				            <div class="card">
				                <div class="card-body">
				                    This is some text within a card block.
				                </div>
				            </div>
				        </div>
				    </div>
				    <!-- ============================================================== -->
				    <!-- End PAge Content -->
				    <!-- ============================================================== -->

				    <!-- Footer -->
				    <app-footer></app-footer>
				    </div>
				  </div>
				</div> 
		**************************************************************************************************************************************
		20. ***********************************************************************************************Implementando las rutas principales
			1.- Se genera modulo de routing --> ng g m appRouting --flat
			2.- Se crean componentes de paginas progress y grafica1 
				ng g c pages/grafica1 -is --skip-tests
				ng g c pages/progress -is --skip-tests
			3.- Se modifica app-routing.module.ts, se crean las rutas y se importan  
				3.1.- Se exporta RouterModule 
					3.1.- Se importa la libreria 
						import { RouterModule, Routes } from '@angular/router'
					3.2.- Se exporta el RouterModule   
						exports: [ RouterModule ]
				3.2.- Se crean las rutas en una constante y se importan
					3.2.1.- Se crean las rutas      
						const routes: Routes = [
						  { path: 'dashboard', component: DashboardComponent },
						  { path: 'login', component: LoginComponent },
						  { path: 'register', component: RegisterComponent },
						  { path: 'progress', component: ProgressComponent },
						  { path: 'grafica1', component: Grafica1Component },
						  { path: '', redirectTo: 'dashboard', pathMatch:'full' },
						  { path: '**', component: NopagesfoundComponent },
						]
					3.2.2.- Se importan las rutas creadas   
						@NgModule({
						  declarations: [],
						  imports: [ RouterModule.forRoot(routes) ],
						  exports: [ RouterModule ]
						})
						export class AppRoutingModule { }
			4.- Se modifica app.module.ts, se importa el modulo de rutas AppRoutingModule 
				@NgModule({
				  declarations: [
				    AppComponent,
				    LoginComponent,
				    RegisterComponent,
				    NopagesfoundComponent,
				    DashboardComponent,
				    BreadcrumbsComponent,
				    SidebarComponent,
				    HeaderComponent,
				    FooterComponent,
				    ProgressComponent,
				    Grafica1Component
				  ],
				  imports: [
				    BrowserModule,
				    AppRoutingModule
				  ],
				  bootstrap: [AppComponent]
				})
				export class AppModule { }
			5.- Se modifica app.component.html, se agrega componente router-oulet 
				  <!-- ============================================================== -->
			      <!-- Start Page Content -->
			      <!-- ============================================================== -->
			      <div class="row">
			        <div class="col-12">
			            <div class="card">
			                <div class="card-body">
			                    <router-outlet></router-outlet>
			                </div>
			            </div>
			        </div>
			      </div>
			      <!-- ============================================================== -->
			      <!-- End PAge Content -->
			      <!-- ============================================================== -->
		**************************************************************************************************************************************
		21. *****************************************************************************************************Implementar rutas secundarias
			1.- Se modifica app.component.html, se corta todo el contenido y se agrega router-outlet   
				<router-outlet></router-outlet>
			2.- Se crea componente pages  
				ng g c pages/pages --flat --skip-tests -is
			3.- Se modifica pages.component.html, se pega todo el contenido cortado de app.component.html 
				<div id="main-wrapper">
				  <app-header></app-header>
				  <app-sidebar></app-sidebar>
				  <div class="page-wrapper">
				    <div class="container-fluid">
				      <!-- Bread crumb and right sidebar toggle -->
				      <app-breadcrumbs></app-breadcrumbs>
				      <!-- Sistema de rutas  -->

				      <!-- ============================================================== -->
				      <!-- Start Page Content -->
				      <!-- ============================================================== -->
				      <div class="row">
				        <div class="col-12">
				            <div class="card">
				                <div class="card-body">
				                    <router-outlet></router-outlet>
				                </div>
				            </div>
				        </div>
				      </div>
				      <!-- ============================================================== -->
				      <!-- End PAge Content -->
				      <!-- ============================================================== -->

				    <!-- Footer -->
				    <app-footer></app-footer>
				    </div>
				  </div>
				</div>
			4.- Se modifica app-routing.module.ts, se crean rutas hijas 
				const routes: Routes = [
				  {
				    path      : '',
				    component : PagesComponent,
				    children  : [
				      { path: 'dashboard', component: DashboardComponent },
				      { path: 'progress', component: ProgressComponent },
				      { path: 'grafica1', component: Grafica1Component },
				      { path: '', redirectTo: 'dashboard', pathMatch:'full' }
				    ]
				  },
				  { path: 'login', component: LoginComponent },
				  { path: 'register', component: RegisterComponent },
				  { path: '**', component: NopagesfoundComponent },
				]
			5.- Se levanta la aplicacion con --> ng serve -o, y se prueban las diferentes rutas en el explorador  
				http://localhost:4200/dashboard15  	--> pagina de notfound 
				http://localhost:4200/dashboard    	--> pagina de dashboard con sidebar 
				http://localhost:4200/login  		--> pagina de login sin sidebar 
		**************************************************************************************************************************************
		22. ***************************************************************************************************Separando el login del template
			1.- Se modifica el archivo login.component.html 
				1.1.- Se abre el archivo pages-login-2.html de el material de la seccion, se ubica la seccion de Main Wrapper y se pega en 
				login.component.html 
					/*<!-- ============================================================== -->
					  <!-- Main wrapper - style you can find in pages.scss -->
					  <!-- ============================================================== -->
					  <section id="wrapper" class="login-register login-sidebar" style="background-image:url(../assets/images/background/login-register.jpg);">
					    <div class="login-box card">
					        <div class="card-body">
					            <form class="form-horizontal form-material" id="loginform" action="index.html">
					                <a href="javascript:void(0)" class="text-center db"><img src="../assets/images/logo-icon.png" alt="Home" /><br/><img src="../assets/images/logo-text.png" alt="Home" /></a>
					                <div class="form-group m-t-40">
					                    <div class="col-xs-12">
					                        <input class="form-control" type="text" required="" placeholder="Username">
					                    </div>
					                </div>
					                <div class="form-group">
					                    <div class="col-xs-12">
					                        <input class="form-control" type="password" required="" placeholder="Password">
					                    </div>
					                </div>
					                <div class="form-group row">
					                    <div class="col-md-12">
					                        <div class="checkbox checkbox-primary pull-left p-t-0">
					                            <input id="checkbox-signup" type="checkbox" class="filled-in chk-col-light-blue">
					                            <label for="checkbox-signup"> Remember me </label>
					                        </div>
					                        <a href="javascript:void(0)" id="to-recover" class="text-dark pull-right"><i class="fa fa-lock m-r-5"></i> Forgot pwd?</a> </div>
					                </div>
					                <div class="form-group text-center m-t-20">
					                    <div class="col-xs-12">
					                        <button class="btn btn-info btn-lg btn-block text-uppercase btn-rounded" type="submit">Log In</button>
					                    </div>
					                </div>
					                <div class="row">
					                    <div class="col-xs-12 col-sm-12 col-md-12 m-t-10 text-center">
					                        <div class="social"><a href="javascript:void(0)" class="btn  btn-facebook" data-toggle="tooltip" title="Login with Facebook"> <i aria-hidden="true" class="fa fa-facebook"></i> </a> <a href="javascript:void(0)" class="btn btn-googleplus" data-toggle="tooltip" title="Login with Google"> <i aria-hidden="true" class="fa fa-google-plus"></i> </a> </div>
					                    </div>
					                </div>
					                <div class="form-group m-b-0">
					                    <div class="col-sm-12 text-center">
					                        Don't have an account? <a href="pages-register2.html" class="text-primary m-l-5"><b>Sign Up</b></a>
					                    </div>
					                </div>
					            </form>
					            <form class="form-horizontal" id="recoverform" action="index.html">
					                <div class="form-group ">
					                    <div class="col-xs-12">
					                        <h3>Recover Password</h3>
					                        <p class="text-muted">Enter your Email and instructions will be sent to you! </p>
					                    </div>
					                </div>
					                <div class="form-group ">
					                    <div class="col-xs-12">
					                        <input class="form-control" type="text" required="" placeholder="Email">
					                    </div>
					                </div>
					                <div class="form-group text-center m-t-20">
					                    <div class="col-xs-12">
					                        <button class="btn btn-primary btn-lg btn-block text-uppercase waves-effect waves-light" type="submit">Reset</button>
					                    </div>
					                </div>
					            </form>
					        </div>
					    </div>
					</section>
					<!-- ============================================================== -->
					<!-- End Wrapper -->
					<!-- ============================================================== -->*/
			2.- Se modifica el archivo login.component.css, se copia el contenido del archivo login-register-lock.css del material de 
			la seccion y se pega  
				/*
				Template Name: Admin pro Admin
				Author: Wrappixel
				Email: niravjoshi87@gmail.com
				File: scss
				*/
				/*
				Template Name: Admin Pro Admin
				Author: Wrappixel
				Email: niravjoshi87@gmail.com
				File: scss
				*/
				/*Theme Colors*/
				/*bootstrap Color*/
				/*Light colors*/
				/*Normal Color*/
				/*Extra Variable*/
				/*******************
				Login register and recover password Page
				******************/
				.login-register {
				  background-size: cover;
				  background-repeat: no-repeat;
				  background-position: center center;
				  height: 100%;
				  width: 100%;
				  padding: 10% 0;
				  position: fixed; }

				.login-box {
				  width: 400px;
				  margin: 0 auto; }
				  .login-box .footer {
				    width: 100%;
				    left: 0px;
				    right: 0px; }
				  .login-box .social {
				    display: block;
				    margin-bottom: 30px; }

				#recoverform {
				  display: none; }

				.login-sidebar {
				  padding: 0px;
				  margin-top: 0px; }
				  .login-sidebar .login-box {
				    right: 0px;
				    position: absolute;
				    height: 100%; } +
		**************************************************************************************************************************************
		23. *********************************************************************************************Tarea práctica #2 - Register template
			Basado en la clase anterior se debe modificar el componente de registro, basado en el archivo pages-register.html que se facilito 
			como material en el curso   + +
		**************************************************************************************************************************************
		26. ************************************************************************************************************************Página 404
			Basado en la clase anterior se debe modificar el componente de NopagesfoundComponent , basado en el archivo pages-error-404.html 
			que se facilito como material en el curso    
		**************************************************************************************************************************************
		27. *********************************************************************************************Respaldo de nuestro trabajo en GitHub
			1.- Se inicializa git --> git init 
			2.- Se preparan los cambios --> git add . 
			3.- Se commitean los cambios --> git commit -m "Finaliza seccion 3 Diseño"
			Nota: utile si deseas deshacer los cambios hasta el ultimo commit realizado --> git checkout -- .  
			4.- Crear un proyecto en git hub 
			5.- git remote add origin https://github.com/dirielfran/admPro.git
			6.- git push -u origin main

			Cuando se realaliza un release a produccion 
				git tag -a v1.0.0 -m "Diseño"
				git tag 
				git push --tag 
		**************************************************************************************************************************************
	********************************************************Fin Seccion*******************************************************************
	*****************************************************Sección 4: Módulos***************************************************************
		32. *****************************************************************************************************Creando nuestro primer módulo
			1.- Se crea modulo 	--> ng g m pages/pages --flat
								--> ng g m shared/shared --flat
			2.- Se modifica app.module.ts, se cortan los componentes para pasar a los modulos 
				@NgModule({
				  declarations: [
				    AppComponent,
				    LoginComponent,
				    RegisterComponent,
				    NopagesfoundComponent,
				  ],
				  imports: [
				    BrowserModule,
				    AppRoutingModule,
				    PagesModule
				  ],
				  bootstrap: [AppComponent]
				})
				export class AppModule { }
			3.- Se modifica pages.module.ts, se copial los componentes, se importan y se exportan: dashboard, progress, grafica1, Pages, 
				se importan los modulos sharedModule y RouterModule
				@NgModule({
				  declarations: [
				    DashboardComponent,
				    ProgressComponent,
				    Grafica1Component,
				    PagesComponent
				  ],
				  exports: [
				    DashboardComponent,
				    ProgressComponent,
				    Grafica1Component,
				    PagesComponent
				  ],
				  imports: [
				    CommonModule,
				    RouterModule,
				    SharedModule
				  ]
				})
				export class PagesModule { }
			4.- Se modifica shared.module.ts, se copial los componentes, se importan y se exportan: Breadcrumbs,Sidebar,Header,Footer	
				@NgModule({
				  declarations: [
				    BreadcrumbsComponent,
				    SidebarComponent,
				    HeaderComponent,
				    FooterComponent
				  ],
				  exports:[
				    BreadcrumbsComponent,
				    SidebarComponent,
				    HeaderComponent,
				    FooterComponent
				  ],
				  imports: [
				    CommonModule
				  ]
				})
				export class SharedModule { }
		**************************************************************************************************************************************
		33. ***************************************************************************Tarea práctica #3 - Creación de un módulo personalizado
			1.- Se crea modulo para auth --> ng g m auth/auth --flat 
			2.- Se modifica app.module.ts, se cortan los componentes login,register, se importa AuthModule 
				@NgModule({
				  declarations: [
				    AppComponent,
				    NopagesfoundComponent,
				  ],
				  imports: [
				    BrowserModule,
				    AppRoutingModule,
				    PagesModule,
				    AuthModule
				  ],
				  bootstrap: [AppComponent]
				})
				export class AppModule { }
			3.- Se copian, se importan y se exportan los componentes login y register 
				@NgModule({
				  declarations: [
				    LoginComponent,
				    RegisterComponent,
				  ],
				  exports:[
				    LoginComponent,
				    RegisterComponent,
				  ],
				  imports: [
				    CommonModule
				  ]
				})
				export class AuthModule { }
		**************************************************************************************************************************************
		35. *********************************************************************************************************Rutas hijas - ForChild( )
			1.- Se crea un archivo para las rutas de forma manual --> pages-routing.module.ts  
				1.1.- Se utiliza el shortcust ng-router para crear el modulo 
				1.2.- Se modifica 
					const routes: Routes = [
					  {
					    path      : '',
					    component : PagesComponent,
					    children  : [
					      { path: 'dashboard', component: DashboardComponent },
					      { path: 'progress', component: ProgressComponent },
					      { path: 'grafica1', component: Grafica1Component },
					      { path: '', redirectTo: 'dashboard', pathMatch:'full' }
					    ]
					  },

					  //{ path: 'path/:routeParam', component: MyComponent },
					  //{ path: 'staticPath', component: ... },
					  //{ path: '**', component: ... },
					  //{ path: 'oldPath', redirectTo: '/staticPath' },
					  //{ path: ..., component: ..., data: { message: 'Custom' }
					];

					@NgModule({
					  imports: [RouterModule.forChild(routes)],
					  exports: [RouterModule]
					})
					export class PagesRoutingModule {}
			2.- Se modifica app-routing.module.ts, se eliminan las rutas PagesComponent y se importa PagesRoutingModule 
				const routes: Routes = [
				  { path: 'login', component: LoginComponent },
				  { path: 'register', component: RegisterComponent },
				  { path: '**', component: NopagesfoundComponent },
				]

				@NgModule({
				  declarations: [],
				  imports: [ RouterModule.forRoot(routes), PagesRoutingModule ],
				  exports: [ RouterModule ]
				})
				export class AppRoutingModule { }
		**************************************************************************************************************************************
		36. *****************************************************************************************************************AuthRoutingModule
			Tarea: Se debe crear el AuthRoutingModule e importarlo en el app.routing.module.ts 
			1.- Se crea modulo de routing --> auth.routing.ts
				1.1.- Se ejecuta ng-router
				1.2.- Se modifica  
					const routes: Routes = [
					  { path: 'login', component: LoginComponent },
					  { path: 'register', component: RegisterComponent },
					  //{ path: 'path/:routeParam', component: MyComponent },
					  //{ path: 'staticPath', component: ... },
					  //{ path: '**', component: ... },
					  //{ path: 'oldPath', redirectTo: '/staticPath' },
					  //{ path: ..., component: ..., data: { message: 'Custom' }
					];

					@NgModule({
					  imports: [RouterModule.forChild(routes)],
					  exports: [RouterModule]
					})
					export class AuthRoutingModule {} 
			2.- Se modifica app-routing.morule.ts, se eliminan las rutas a login y register y se importa el nuevo modulo de rutas  
				const routes: Routes = [
				  { path: '**', component: NopagesfoundComponent },
				]

				@NgModule({
				  declarations: [],
				  imports: [
				    RouterModule.forRoot(routes),
				    PagesRoutingModule,
				    AuthRoutingModule
				  ],
				  exports: [ RouterModule ]
				})
				export class AppRoutingModule { }
		**************************************************************************************************************************************
		37. ***************************************************************************Colocando nuestras rutas a partir de un path específico
			1.- Se modifica app-routing.module.ts, se agrega path a la constantes de rutas 
				const routes: Routes = [
				  { path: '', redirectTo:'dashboard', pathMatch: 'full'},
				  { path: '**', component: NopagesfoundComponent },
				]
			2.- Se modifica pages-routing.module.ts, se agrega path raiz 
				const routes: Routes = [
				  {
				    path      : 'dashboard',
				    component : PagesComponent,
				    children  : [
				      { path: '', component: DashboardComponent },
				      { path: 'progress', component: ProgressComponent },
				      { path: 'grafica1', component: Grafica1Component },
				      { path: '', redirectTo: 'dashboard', pathMatch:'full' }
				    ]
				  },
		**************************************************************************************************************************************
		38. ************************************************************************Guardar los cambios en GitHub y crear un TAG de producción
			1.- Se preparan los cambios --> git add . 
			2.- Se guardan los cambios en el local --> git commit -m "Fin seccion 4"
			3.- Se suben los cambios al remoto --> git push 
			4.- Se observan los tags --> git tag 
			5.- Se crea un nuevo release --> git tag -a v1.1.0 -m "Rutas listas" 
			6.- Se sube el release --> git push --tags
		**************************************************************************************************************************************
	******************************************************* Fin Seccion ******************************************************************
	********************************************** Sección 6: @inputs y @Outputs *********************************************************
		46. ************************************************************************************************Nuestro componente del ProgressBar
			1.- Se modifica pages.component.html, se corta el card de contenido  
				<div id="main-wrapper">
				  <app-header></app-header>
				  <app-sidebar></app-sidebar>
				  <div class="page-wrapper">
				    <div class="container-fluid">
				      <!-- Bread crumb and right sidebar toggle -->
				      <app-breadcrumbs></app-breadcrumbs>
				      <!-- Sistema de rutas  -->
				      <router-outlet></router-outlet>
				      <!-- Footer -->
				      <app-footer></app-footer>
				    </div>
				  </div>
				</div>
			2.- Se modifica progress.component.ts, se renderiza vista 
				<!-- ============================================================== -->
				<!-- Start Page Content -->
				<!-- ============================================================== -->
				<div class="row">
				  <div class="col-12">
				      <div class="card">
				          <div class="card-body">
				              <h4 class="card-title">Striped Progress bar </h4>
				              <div class="progress m-t-20">
				                  <div class="progress-bar bg-primary progress-bar-striped active"
				                  style="width: 85%; height:10px;" role="progressbar">
				                      <span class="sr-only">85% Complete (success)</span>
				                  </div>
				              </div>

				          </div>
				      </div>
				  </div>
				</div>
				<div class="row">
				  <div class="col-6">
				    <div class="card">
				      <div class="card-body">
				        <div class="input-group">
				          <span class="input-group-btn">
				            <button class="btn btn-primary" type="button"><i class="fa fa-minus"></i></button>
				          </span>
				          <input type="text" class="form-control" placeholder="Product name">
				          <span class="input-group-btn">
				            <button class="btn btn-primary" type="button"><i class="fa fa-plus"></i></button>
				          </span>
				      </div>
				      </div>
				    </div>
				  </div>
				  <div class="col-6">
				    <div class="card">
				      <div class="card-body">
				          2
				      </div>
				    </div>
				  </div>
				</div>
				<!-- ============================================================== -->
				<!-- End PAge Content -->
				<!-- ============================================================== -->
			3.- Se crea de forma manual el archivo progress.component.css 
			4.- Se modifica progress.component.ts, se le agrega al decorador la url del css creado 
				@Component({
				  selector: 'app-progress',
				  templateUrl: './progress.component.html',
				  styleUrls: [ './progress.component.css']
				})
				export class ProgressComponent implements OnInit {
			5.- Se copia estilo del archivo \main\css\pages\progressbar-page.css y se copia en el elemento css creado 
				/*
				Template Name: Admin pro Admin
				Author: Wrappixel
				Email: niravjoshi87@gmail.com
				File: scss
				*/
				/*
				Template Name: Admin Pro Admin
				Author: Wrappixel
				Email: niravjoshi87@gmail.com
				File: scss
				*/
				/*Theme Colors*/
				/*bootstrap Color*/
				/*Light colors*/
				/*Normal Color*/
				/*Extra Variable*/
				/*******************
				Progress bar
				******************/
				.progress.active .progress-bar,
				.progress-bar.active {
				  -webkit-animation: progress-bar-stripes 2s linear infinite;
				  -o-animation: progress-bar-stripes 2s linear infinite;
				  animation: progress-bar-stripes 2s linear infinite; }

				.progress-vertical {
				  min-height: 250px;
				  height: 250px;
				  position: relative;
				  display: inline-block;
				  margin-bottom: 0;
				  margin-right: 20px; }

				.progress-vertical-bottom {
				  min-height: 250px;
				  height: 250px;
				  position: relative;
				  display: inline-block;
				  margin-bottom: 0;
				  margin-right: 20px;
				  -webkit-transform: rotate(180deg);
				  -ms-transform: rotate(180deg);
				  transform: rotate(180deg); }

				.progress-animated {
				  -webkit-animation-duration: 5s;
				  -webkit-animation-name: myanimation;
				  -webkit-transition: 5s all;
				  animation-duration: 5s;
				  animation-name: myanimation;
				  -o-transition: 5s all;
				  transition: 5s all; }

				@-webkit-keyframes myanimation {
				  from {
				    width: 0; } }

				@keyframes myanimation {
				  from {
				    width: 0; } }+
		**************************************************************************************************************************************
		47. ***************************************************************************************************Uso de atributos personalizados
			1.- Se modifica progress.component.html
				1.1.- Se crea variable para manipular porcentaje del progress 
			  		progress: number = 40;
			  	1.2.- Se crea get para obtener porcentge del progress 
				  get getPorcentaje(){
				    return `${this.progress}%`;
				  }
				1.3.- Se crea metodo para aumento o disminucion del progress 
				  cambiarPorcentaje( valor: number ){
				    if( this.progress >= 100 && valor >= 0 ){
				      return this.progress = 100;
				    }
				    if( this.progress <= 0 && valor < 0 ){
				      return this.progress = 0;
				    }
				    return this.progress = this.progress + valor;
				  }
			2.- Se modifica progress.component.html 
				2.1.- Se modifica el elemento del progress, para pasar estilo por directiva y obtener el porcentaje del componente 
				    <div class="progress m-t-20">
		              <div class="progress-bar bg-primary progress-bar-striped active"
		              style="height:10px;" [style.width]="getPorcentaje" role="progressbar">
		                  <span class="sr-only">85% Complete (success)</span>
		              </div>
		          </div>
		        2.2.- Se modifican los botones para que llamen al metodo cambiarPorcentaje() 
		        	<span class="input-group-btn">
		            <button class="btn btn-primary" type="button" (click)="cambiarPorcentaje(-5)"><i class="fa fa-minus"></i></button>
		          </span>
		          <span class="input-group-btn">
		            <button class="btn btn-primary" type="button" (click)="cambiarPorcentaje(5)"><i class="fa fa-plus"></i></button>
		          </span>
		        2.3.- Se modifica el imput para el databinding con progress 
		          <input type="text" class="form-control" placeholder="Porcentaje" [(ngModel)]="progress">
		        2.4.- Se importa el modulo FormsModule 
		        	import { FormsModule } from '@angular/forms';


		    	  imports: [
				    CommonModule,
				    RouterModule,
				    SharedModule,
				    FormsModule
				  ]
		**************************************************************************************************************************************
		48. ****************************************************************************************************Crear componente incrementador
			1.- Se crea folder en la raiz /components
			2.- Se crea un modulo components --> components/components --flat 
			3.- Se crea componente --> ng g c components/incrementador --skip-tests
			4.- Se modifica components.module.ts, se exporta IncrementComponents y se importa FormsModule
				@NgModule({
				  declarations: [
				    IncrementadorComponent
				  ],
				  exports:[
				    IncrementadorComponent
				  ],
				  imports: [
				    CommonModule,
				    FormsModule
				  ]
				})
				export class ComponentsModule { }
			5.- Se modifica app.module.ts, se importa ComponentModule 
				@NgModule({
				  declarations: [
				    AppComponent,
				    NopagesfoundComponent,
				  ],
				  imports: [
				    BrowserModule,
				    AppRoutingModule,
				    PagesModule,
				    AuthModule,
				    ComponentsModule
				  ],
				  bootstrap: [AppComponent]
				})
				export class AppModule { }
			6.- Se modifica progress.component.html, se corta el elemento input de incremento y se coloca el elemeto app-incrementador
				<div class="row">
				  <div class="col-6">
				    <div class="card">
				      <div class="card-body">
				        <app-incrementador></app-incrementador>
				      </div>
				    </div>
				  </div>
				  <div class="col-6">
				    <div class="card">
				      <div class="card-body">
				        <app-incrementador></app-incrementador>
				      </div>
				    </div>
				  </div>
				</div>
			7.- Se modifica incrementador.component.html, se copia el elemento input cortado de progress.component.html
				<div class="input-group">
				  <span class="input-group-btn">
				    <button class="btn btn-primary" type="button" (click)="cambiarPorcentaje(-5)"><i class="fa fa-minus"></i></button>
				  </span>
				  <input type="text" class="form-control" placeholder="Porcentaje" [(ngModel)]="progress">
				  <span class="input-group-btn">
				    <button class="btn btn-primary" type="button" (click)="cambiarPorcentaje(5)"><i class="fa fa-plus"></i></button>
				  </span>
				</div>
			8.- Se modifica progress.component.ts, se corta todo el contenido de la clase de
				@Component({
				 

			9.- Se modifica incrementador.component.ts, se copia todo lo cortado en ProgressComponent
				@Component({
				  selector: 'app-incrementador',
				  templateUrl: './incrementador.component.html',
				  styleUrls: ['./incrementador.component.css']
				})
				export class IncrementadorComponent implements OnInit {

				  progress: number = 40;

				  constructor() { }

				  ngOnInit(): void {
				  }

				  get getPorcentaje(){
				    return `${this.progress}%`;
				  }

				  cambiarPorcentaje( valor: number ){
				    if( this.progress >= 100 && valor >= 0 ){
				      return this.progress = 100;
				    }
				    if( this.progress <= 0 && valor < 0 ){
				      return this.progress = 0;
				    }
				    return this.progress = this.progress + valor;
				  }


				}
			10.- Se modifica pages.module.ts, se importa ComponenstModule  
				@NgModule({
				  declarations: [
				    DashboardComponent,
				    ProgressComponent,
				    Grafica1Component,
				    PagesComponent
				  ],
				  exports: [
				    DashboardComponent,
				    ProgressComponent,
				    Grafica1Component,
				    PagesComponent
				  ],
				  imports: [
				    CommonModule,
				    RouterModule,
				    SharedModule,
				    FormsModule,
				    ComponentsModule
				  ]
				})
				export class PagesModule { }
		**************************************************************************************************************************************
		49. *************************************************************************************************@Input - Componente incrementador
			1.- Se modifica incrementador.component.ts, se agrega @input() a la variables progress 
				@Input() progress: number = 40; 
			2.- Se modifica progress.component.html, se agrega propiedad del elemento IncrementadorComponent  
				<div class="row">
				  <div class="col-6">
				    <div class="card">
				      <div class="card-body">
				        <app-incrementador [progress]="15"></app-incrementador>
				      </div>
				    </div>
				  </div>
				  <div class="col-6">
				    <div class="card">
				      <div class="card-body">
				        <app-incrementador [progress]="80"></app-incrementador>
				      </div>
				    </div>
				  </div>
				</div>
		**************************************************************************************************************************************
		50. ************************************************************************************************@Output - Componente incrementador
			1.- Se modifica componente hijo incrementador.component.ts, se agrega @OutPut() para poder emitir valor al componente padre  
				1.1.- Se crea atributo de tipo EventEmitter 
					@Output() valorSalida: EventEmitter<number> = new EventEmitter();
				1.2.- Se modifica el metodo cambiarPorcentaje(), para que emita por medio del evento el valor del imput 
					cambiarPorcentaje( valor: number ){
				    if( this.progress >= 100 && valor >= 0 ){
				      this.valorSalida.emit(100);
				      return this.progress = 100;
				    }
				    if( this.progress <= 0 && valor < 0 ){
				      this.valorSalida.emit(0);
				      return this.progress = 0;
				    }
				    this.valorSalida.emit(this.progress + valor);
				    return this.progress = this.progress + valor;
				  }
			2.- Se modifica progress.component.ts 
				2.1.- Se crean atributos progreso1 y progreso2 
				  progreso1: number = 15;
					progreso2: number = 40;
				2.2.- Se crean getter para recuperar valor de progreso en cada componente 
				  get getProgress1(){
				    return `${this.progreso1}%`
				  }

				  get getProgress2(){
				    return `${this.progreso2}%`
				  }
			3.- Se modifica progress.component.html 
				3.1.- Se modifica componente agregando atributo de estilo [style.width]
						<div class="progress m-t-20">
					      <div class="progress-bar bg-primary progress-bar-striped active"
					      style="height:10px;" [style.width]="getProgress1"  role="progressbar">
					          <span class="sr-only">85% Complete (success)</span>
					      </div>
					  </div>
					  <div class="progress m-t-20">
					      <div class="progress-bar bg-info progress-bar-striped active"
					      style="height:10px;" [style.width]="getProgress2" role="progressbar">
					          <span class="sr-only">85% Complete (success)</span>
					      </div>
					  </div>
				3.2.- Se modifica componente de incrementador, se le agrega evento emiter (valorSalida) y el @Input() [progress] 
					<app-incrementador (valorSalida)="progreso1 = $event" [progress]="progreso1"></app-incrementador>

					<app-incrementador (valorSalida)="progreso2 = $event" [progress]="progreso2"></app-incrementador>+
		**************************************************************************************************************************************
		51. *****************************************************************************************Color de los botones de forma condicional
			1.- Se modifica incrementador.component.html, se le agrega directiva --> [ngClass]="valorClase" y se le elimina la clase 
				como atributo
				<div class="input-group">
				  <span class="input-group-btn">
				    <button [ngClass]="valorClase" type="button" (click)="cambiarPorcentaje(-5)"><i class="fa fa-minus"></i></button>
				  </span>
				  <input type="text" class="form-control" placeholder="Porcentaje" [(ngModel)]="progress">
				  <span class="input-group-btn">
				    <button [ngClass]="valorClase" type="button" (click)="cambiarPorcentaje(5)"><i class="fa fa-plus"></i></button>
				  </span>
				</div>
			2.- Se modifica incrementador.component.ts
				2.1.- Se crea atributo @Input 
					  @Input() valorClase: string = 'btn-info';
				2.2.- Se modifica metodo ngOnInit() 
					  ngOnInit(): void {
					    this.valorClase = `btn ${ this.valorClase }`
					  }
			3.- S modifica progress.component.html, se modifica el componente incrementador para pasarle la clase que utilizaran los botones 
				 <app-incrementador (valorSalida)="progreso1 = $event" [progress]="progreso1" [valorClase]="'btn-primary'"></app-incrementador>

				 <app-incrementador (valorSalida)="progreso2 = $event" [progress]="progreso2" [valorClase]="'btn-danger'"></app-incrementador>+
		**************************************************************************************************************************************
		52. *********************************************************************************Pulir detalles de nuestro incrementador component
		***********************************************************************************************************************(ngModelChange)
		*****************************************************************************************************************************[ngClass]
			1.- Se modifica incrementador.component.html, se le agrega directiva [ngClass] y evento (ngModelChange) al input  
				<div class="input-group">
				  <span class="input-group-btn">
				    <button [ngClass]="valorClase" type="button" (click)="cambiarPorcentaje(-5)"><i class="fa fa-minus"></i></button>
				  </span>
				  <input (ngModelChange)="onChange( $event )"
				          type="number"
				          class="form-control"
				          placeholder="Porcentaje"
				          [(ngModel)]="progress"
				          [ngClass]="{'is-invalid': progress > 100 ||  progress < 0 }">
				  <span class="input-group-btn">
				    <button [ngClass]="valorClase" type="button" (click)="cambiarPorcentaje(5)"><i class="fa fa-plus"></i></button>
				  </span>
				</div>
			2.- Se modifica incrementador.component.ts, se crea metodo onChange() que validra que el numero no sea mayor a 100 o menosr a 0 
				  onChange( newValor:  number){
				    if(newValor > 100){
				      this.progress = 100;
				    }else if(newValor < 0 ){
				      this.progress = 0;
				    }else{
				      this.progress = newValor;
				    }
				    this.valorSalida.emit(this.progress);
				  }
		**************************************************************************************************************************************
		54. ***************************************************************************************************************Gráficas en Angular
			Referencia --> https://valor-software.com/ng2-charts/#/GeneralInfo
			1.- Se realiza instalacion de ng2-chart --> npm install --save ng2-charts
			2.- Instalacion de chart.js --> npm install --save chart.js
			Nota: Al instalar las versiones del punto 1 y 2 se me genera error, por lo que debi desintalar y volver a instalar con una version 
						determinada 

							npm uninstall ng2-charts
							npm uninstall chart.js

						Luego simplemente instala las siguientes versiones:

							npm install --save ng2-charts@2.3.0
							npm install --save chart.js@2.9.3
			3.- Se modifica pages.module.ts, se importa ChartsModule 
				  imports: [
					    CommonModule,
					    RouterModule,
					    SharedModule,
					    FormsModule,
					    ComponentsModule,
					    ChartsModule
					  ]
					})
					export class PagesModule { }
			4.- Se modifica grafica1.component.html, se renderiza grafica de torta, segun pagina de referencia ng2chart 
				<div class="row">
				  <div class="col-6">
				    <div class="card">
				      <div class="card-body">
				        <div style="display: block">
				          <canvas baseChart
				            [data]="doughnutChartData"
				            [labels]="doughnutChartLabels"
				            [colors]="colors"
				            [chartType]="doughnutChartType">
				          </canvas>
				        </div>
				      </div>
				    </div>
				  </div>
				</div>
			5.- Se modifica grafica1.component.ts, se agrega datos para los graficos, segun pagina de referencia ng2chart  
				5.1.- Se importan las librerias 
					import { ChartType } from 'chart.js';
					import { MultiDataSet, Label, Color } from 'ng2-charts';}
				5.2.- Se crean los atributos para los graficos
				  // Doughnut
				  public doughnutChartLabels: Label[] = ['Download Sales', 'In-Store Sales', 'Mail-Order Sales'];
				  public doughnutChartData: MultiDataSet = [
				    [350, 450, 100],
				  ];
				  public doughnutChartType: ChartType = 'doughnut';

				  public colors: Color[] = [
				    { backgroundColor: [ '#00ff40', '#00ffff' , '#8080ff']}
				  ]

				5.3.- Se crean metodo para los eventos hover y click 

				  // events
				  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
				    console.log(event, active);
				  }

				  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
				    console.log(event, active);
				  }+
		**************************************************************************************************************************************
		55. *********************************************************************************************************Tarea de Inputs y Outputs
			1.- Se crea componente de grafica dona para reutilizar 
				ng g c components/dona --skip-tests
			2.- Se modifica grafica1.component.html, se corta div de la grafica de dona y inserta componente app-dona creado 
				<div class="row">
				  <div class="col-6">
				    <app-dona></app-dona>
				  </div>
				  <div class="col-6">
				    <app-dona></app-dona>
				  </div>
				</div>
				<div class="row">
				  <div class="col-6">
				    <app-dona></app-dona>
				  </div>
				  <div class="col-6">
				    <app-dona></app-dona>
				  </div>
				</div>
			3.- Se modifica dona.component.html y se pega div de la grafica de dona
				<div class="card">
				  <div class="card-body">
				    <div style="display: block">
				      <canvas baseChart
				        [data]="doughnutChartData"
				        [labels]="doughnutChartLabels"
				        [colors]="colors"
				        [chartType]="doughnutChartType">
				      </canvas>
				    </div>
				  </div>
				</div>
			4.- Se modifica grafica1.component.ts, se corta contenido de la grafica y se deja solo el constructor y el OnInit 
				@Component({
				  selector: 'app-grafica1',
				  templateUrl: './grafica1.component.html',
				  styles: [
				  ]
				})
				export class Grafica1Component implements OnInit {

				  constructor() { }

				  ngOnInit(): void {
				  }

				}
			5.- Se modifica dona.component.ts, se pega el contenido de datos y metodos de la dona  
				@Component({
				  selector: 'app-dona',
				  templateUrl: './dona.component.html',
				  styleUrls: ['./dona.component.css']
				})
				export class DonaComponent implements OnInit {


				  // Doughnut
				  public doughnutChartLabels: Label[] = ['Download Sales', 'In-Store Sales', 'Mail-Order Sales'];
				  public doughnutChartData: MultiDataSet = [
				    [350, 450, 100],
				  ];
				  public doughnutChartType: ChartType = 'doughnut';

				  public colors: Color[] = [
				    { backgroundColor: [ '#00ff40', '#00ffff' , '#8080ff']}
				  ]

				  constructor() { }

				  ngOnInit(): void {
				  }

				  // events
				  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
				    console.log(event, active);
				  }

				  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
				    console.log(event, active);
				  }

				}
			6.- Se modifica pages.module.ts, se elimana la importacion de ChartsModule
				@NgModule({
				  declarations: [
				    DashboardComponent,
				    ProgressComponent,
				    Grafica1Component,
				    PagesComponent
				  ],
				  exports: [
				    DashboardComponent,
				    ProgressComponent,
				    Grafica1Component,
				    PagesComponent
				  ],
				  imports: [
				    CommonModule,
				    RouterModule,
				    SharedModule,
				    FormsModule,
				    ComponentsModule,

				  ]
				})
				export class PagesModule { }
			7.- Se modifica ComponentsModule, se agrega la importacion de ChartsModule y se importa y exporta D

				@NgModule({
				  declarations: [
				    IncrementadorComponent,
				    DonaComponent
				  ],
				  exports:[
				    IncrementadorComponent,
				    DonaComponent
				  ],
				  imports: [
				    CommonModule,
				    FormsModule,
				    ChartsModule
				  ]
				})
				export class ComponentsModule { }
			8.- Se modifica dona.component.html, se le agrega titulo a la grafica 
				<div class="card">
				  <div class="card-body">
				    <!-- Titulo -->
				    <h3>{{titulo}}</h3>
				    <div style="display: block">
				      <canvas baseChart
				        [data]="doughnutChartData"
				        [labels]="doughnutChartLabels"
				        [colors]="colors"
				        [chartType]="doughnutChartType">
				      </canvas>
				    </div>
				  </div>
				</div>
			9.- Se modifica dona.component.ts, se agregan @Input() a los atributos del grafico  
				  @Input() titulo: string ='';
				  // Doughnut
				  // Doughnut
				  @Input('labels') doughnutChartLabels: Label[] = [];
				  @Input('data') doughnutChartData: MultiDataSet = [ ];
			10.- Se modifica grafica1.component.ts, se crean los datos para los graficos 
				  public labels1: Label[] = ['Download Sales1', 'In-Store Sales1', 'Mail-Order Sales1'];
				  public labels2: Label[] = ['Download Sales2', 'In-Store Sales2', 'Mail-Order Sales2'];
				  public labels3: Label[] = ['Download Sales3', 'In-Store Sales3', 'Mail-Order Sales3'];
				  public labels4: Label[] = ['Download Sales4', 'In-Store Sales4', 'Mail-Order Sales4'];

				  public data1 = [[350, 450, 100],];
				  public data2 = [[150, 250, 400],];
				  public data3 = [[450, 250, 500],];
				  public data4 = [[450, 350, 300],];
			11.- Se modifica grafica1.component.html, se agregan atributos a cada grafico  
					<div class="row">
					  <div class="col-6">
					    <app-dona titulo="Sales"
					              [labels]="labels1"
					              [data]="data1"></app-dona>
					  </div>
					  <div class="col-6">
					    <app-dona titulo="Billing"
					              [labels]="labels2"
					              [data]="data2"></app-dona>
					  </div>
					</div>
					<div class="row">
					  <div class="col-6">
					    <app-dona titulo="Expenses"
					              [labels]="labels3"
					              [data]="data3"></app-dona>
					  </div>
					  <div class="col-6">
					    <app-dona titulo="Profits"
					              [labels]="labels4"
					              [data]="data4"></app-dona>
					  </div>
					</div>+
		**************************************************************************************************************************************
		57. *******************************************************************************Guardar nuestros cambios en GitHub - Input y Output
			1.- Se preparan los cambios --> git add .
		 	2.- Se guardan los cambios en el local --> git commit -m FinSeccion6
		 	3.- Se suben los cambios al remoto --> git push
		 	4.- Se visualizan los tags --> git tag
		 	5.- Se agrega tag --> git tag -a v1.2.0 -m"Fin Seccion 6"
		 	6.- Se sube el tag al remoto --> git push --tags
		**************************************************************************************************************************************
	******************************************************* Fin Seccion ******************************************************************
	******************************** Sección 7: Servicios básicos, temas,rutas básicas y persistencia ************************************
		62. **************************************************************************************Diseño inicial de la página account-settings
			1.- Se crea componente -->  ng g c pages/accountSetting --skip-tests 
			2.- Se modifica pages.module.ts, se exporta el componente creado 
				@NgModule({
				  declarations: [


				    DashboardComponent,
				    ProgressComponent,
				    Grafica1Component,
				    PagesComponent,
				    AccountSettingComponent
				  ],
				  exports: [
				    DashboardComponent,
				    ProgressComponent,
				    Grafica1Component,
				    PagesComponent,
				    AccountSettingComponent
				  ],
				  imports: [
				    CommonModule,
				    RouterModule,
				    SharedModule,
				    FormsModule,
				    ComponentsModule,

				  ]
				})
				export class PagesModule { }
			3.- Se modifica pages-routing.module.ts, se agrega nueva ruta para el nuevo componente 
			4.- Se modifica account-setting.component.html, se agrega elementos html para escoger el color 
				<div class="row">
				  <div class="col-5">
				      <div class="card">
				          <div class="card-body">
				            <h3>Seleccione el tema</h3>
				            <div class="r-panel-body">
				              <ul id="themecolors" class="m-t-20">
				                  <li><b>Con el sidebar claro</b></li>
				                  <li><a data-theme="default" class="selector default-theme">1</a></li>
				                  <li><a data-theme="green" class="selector green-theme">2</a></li>
				                  <li><a data-theme="red" class="selector red-theme">3</a></li>
				                  <li><a data-theme="blue" class="selector blue-theme">4</a></li>
				                  <li><a data-theme="purple" class="selector purple-theme">5</a></li>
				                  <li><a data-theme="megna" class="selector megna-theme">6</a></li>

				                  <li class="d-block m-t-30"><b>Con el sidebar oscuro</b></li>
				                  <li><a data-theme="default-dark" class="selector default-dark-theme">7</a></li>
				                  <li><a data-theme="green-dark" class="selector green-dark-theme">8</a></li>
				                  <li><a data-theme="red-dark" class="selector red-dark-theme">9</a></li>
				                  <li><a data-theme="blue-dark" class="selector blue-dark-theme working">10</a></li>
				                  <li><a data-theme="purple-dark" class="selector purple-dark-theme">11</a></li>
				                  <li><a data-theme="megna-dark" class="selector megna-dark-theme">12</a></li>
				              </ul>
				            </div>
				          </div>
				      </div>
				  </div>
				</div> 
			5.- Se modifica Style.css, se agrega stilo para cursor en clase determinada 
				#themecolors .selector{
				  cursor: pointer;
				}
		**************************************************************************************************************************************
		63. ****************************************************************************************Cambiar el CSS principal de forma dinámica
			1.- Se modifica index.html, se modifica link de thema 
				<!-- You can change the theme colors from here -->
		    <link  id="theme" rel="stylesheet">
		  2.- Se modifica account-setting.component.html, se añade evento changeTheme() para cambio de thema en la aplicacion  
		  	<div class="row">
				  <div class="col-5">
				      <div class="card">
				          <div class="card-body">
				            <h3>Seleccione el tema</h3>
				            <div class="r-panel-body">
				              <ul id="themecolors" class="m-t-20">
				                  <li><b>Con el sidebar claro</b></li>
				                  <li><a (click)="changeTheme('default')" data-theme="default" class="selector default-theme">1</a></li>
				                  <li><a (click)="changeTheme('green')" data-theme="green" class="selector green-theme">2</a></li>
				                  <li><a (click)="changeTheme('red')" data-theme="red" class="selector red-theme">3</a></li>
				                  <li><a (click)="changeTheme('blue')" data-theme="blue" class="selector blue-theme">4</a></li>
				                  <li><a (click)="changeTheme('purple')" data-theme="purple" class="selector purple-theme">5</a></li>
				                  <li><a (click)="changeTheme('megna')" data-theme="megna" class="selector megna-theme">6</a></li>

				                  <li class="d-block m-t-30"><b>Con el sidebar oscuro</b></li>
				                  <li><a (click)="changeTheme('default-dark')" data-theme="default-dark" class="selector default-dark-theme">7</a></li>
				                  <li><a (click)="changeTheme('green-dark')" data-theme="green-dark" class="selector green-dark-theme">8</a></li>
				                  <li><a (click)="changeTheme('red-dark')" data-theme="red-dark" class="selector red-dark-theme">9</a></li>
				                  /*<li><a (click)="changeTheme('blue-dark')" data-theme="blue-dark" class="selector blue-dark-theme working">10</a></li>
				                  <li><a (click)="changeTheme('purple-dark')" data-theme="purple-dark" class="selector purple-dark-theme">11</a></li>
				                  <li><a (click)="changeTheme('megna-dark')" data-theme="megna-dark" class="selector megna-dark-theme">12</a></li>
				              </ul>
				            </div>
				          </div>
				      </div>
				  </div>
				</div>*/  
			3.- Se modifica account-setting.component.html, se agrega dentro de la clase, variable para la referencia a objeto del documento 
					con vainilla 
				public urlLink = document.querySelector('#theme');
				3.1.- Se crea metodo de cambio de tema   
					changeTheme( theme: string ){
				    const url = `./assets/css/colors/${ theme }.css`;
				    this.urlLink?.setAttribute('href', url);
				    //Se setea en el localstorage 
				    localStorage.setItem('theme', url);
				  }
			4.- Se modifica pages.component.ts, se recupera del local storage el tema 
				4.1.- Se agrega dentro de la clase, variable para la referencia a objeto del documento con vainilla 
					public urlLink = document.querySelector('#theme');
				4.2.- Se modifica metodo ngOnInit(), para recuperar tema del local storage y setearselo al elemento html 
				  ngOnInit(): void {
				    const tema: string = localStorage.getItem('theme') || './assets/css/colors/default-dark.css';
				    this.urlLink?.setAttribute('href', tema);
				  }
		**************************************************************************************************************************************
		64. ******************************************************************************************Agregando clases de CSS sin usar ngClass
			1.- Se modifica account-setting.component.ts. 
				1.1.- Se crea tributo de tipo NodeListOf<Element> de manera global para ir aldom en cada llamada
				  public links!: NodeListOf<Element>;
				1.2.- Se modifica metodo ngOnInit(), se capturan  elementos del dom con la clase selector y se llama al metodo 
							checkCurrencyTheme()
				  ngOnInit(): void {
				    this.links =document.querySelectorAll(".selector");
				    this.checkCurrencyTheme();
				  }
				1.3.- Se modifica el metodo changeTheme(), se llama al metodo checkCurrencyTheme()
				  changeTheme( theme: string ){
				    const url = `./assets/css/colors/${ theme }.css`;
				    this.urlLink?.setAttribute('href', url);
				    localStorage.setItem('theme', url);
				    this.checkCurrencyTheme();
				  }
				1.4.- Se crea metodo para checkCurrencyTheme()
						//Se recorren los objetos de DOM con clase selector
					  checkCurrencyTheme(){
					    this.links.forEach( elem => {
					    	//Se borra clase de todos los elementos 
					      elem.classList.remove('working');
					      //se obtiene el atributo del elemento
					      const btnTheme = elem.getAttribute('data-theme');
					      //Se arma url del elemento 
					      const btnThemeUrl = `./assets/css/colors/${ btnTheme }.css`;
					      // Se obtiene url del atributo href del tema actual
					      const currentTheme = this.urlLink?.getAttribute('href');
					      // Se compra cada elemento con el actual, si es igual le agrega la clase working
					      if ( btnThemeUrl === currentTheme ){
					        elem.classList.add('working');
					      }
					    })
					  }			 
		**************************************************************************************************************************************
		65. *****************************************************************************************************************Servicio Settings
			1.- Se crea un servicio --> ng g s services/setting --skip-tests
			2.- Se modifica pages.component.ts, se cortan los servicios 
				@Component({
				  selector: 'app-pages',
				  templateUrl: './pages.component.html',
				  styles: [
				  ]
				})
				export class PagesComponent implements OnInit {
				  constructor() { }

				  ngOnInit(): void {}
				} 
			3.- Se modifica setting.service.ts
				3.1.- Se crea atributo para recuperar elemento del DOM 
					private urlLink = document.querySelector('#theme');
				3.2.- Se modifica constructor(), se recupera tema del local storage y se setea en url de elemento html 
					  constructor() {
					    const tema: string = localStorage.getItem('theme') || './assets/css/colors/default-dark.css';
					    this.urlLink?.setAttribute('href', tema);
					  }
				3.3.- Se copia metodo changeTheme() de account-setting.component.ts
					  changeTheme( theme: string ){
					    const url = `./assets/css/colors/${ theme }.css`;
					    this.urlLink?.setAttribute('href', url);
					    localStorage.setItem('theme', url);
					    this.checkCurrencyTheme();
					  }
				3.4.- Se copia metodo checkCurrencyTheme() de account-setting.component.ts
					  checkCurrencyTheme(){

					    const links= document.querySelectorAll(".selector");
					    links.forEach( elem => {
					      elem.classList.remove('working');
					      const btnTheme = elem.getAttribute('data-theme');
					      const btnThemeUrl = `./assets/css/colors/${ btnTheme }.css`;
					      const currentTheme = this.urlLink?.getAttribute('href');

					      if ( btnThemeUrl === currentTheme ){
					        elem.classList.add('working');
					      }
					    })
					  } 
			4.- Se modifica account-setting.component.ts, se deja lo mas limpio posible y se delega en el servicio las tareas 
				@Component({
				  selector: 'app-account-setting',
				  templateUrl: './account-setting.component.html',
				  styleUrls: ['./account-setting.component.css']
				})
				export class AccountSettingComponent implements OnInit {


				  public links!: NodeListOf<Element>;

				  constructor( private settingService: SettingService ) { }

				  ngOnInit(): void {
				    this.settingService.checkCurrencyTheme();
				  }

				  changeTheme( theme: string ){
				    this.settingService.changeTheme( theme );
				  }
				}
		**************************************************************************************************************************************
		66. ***************************************************************************************RouterLink - Mover a una ruta en particular
			1.- Se modifica sidebar.component.html, se agrega routerlink al componente de account-setting  
		    <li class="user-profile">
		        <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><img src="./assets/images/users/profile.png" alt="user" /><span class="hide-menu">Steave Jobs </span></a>
		        <ul aria-expanded="false" class="collapse">
		            <li><a href="javascript:void()">My Profile </a></li>
		            <li><a href="javascript:void()">My Balance</a></li>
		            <li><a href="javascript:void()">Inbox</a></li>
		            <li><a routerLink="setting">Account Setting</a></li>
		            <li><a href="javascript:void()">Logout</a></li>
		        </ul>
		    </li>
		  2.- Se modifica header.component.html, se agrega routerlink al componente de account-setting 
		    <ul class="dropdown-user">
		        <li>
		            <div class="dw-user-box">
		                <div class="u-img"><img src="./assets/images/users/1.jpg" alt="user"></div>
		                <div class="u-text">
		                    <h4>Steave Jobs</h4>
		                    <p class="text-muted">varun@gmail.com</p><a href="pages-profile.html" class="btn btn-rounded btn-danger btn-sm">View Profile</a></div>
		            </div>
		        </li>
		        <li role="separator" class="divider"></li>
		        <li><a href="#"><i class="ti-user"></i> My Profile</a></li>
		        <li><a href="#"><i class="ti-wallet"></i> My Balance</a></li>
		        <li><a href="#"><i class="ti-email"></i> Inbox</a></li>
		        <li role="separator" class="divider"></li>
		        <li><a routerLink="setting"><i class="ti-settings"></i> Account Setting1</a></li>
		        <li role="separator" class="divider"></li>
		        <li><a href="#"><i class="fa fa-power-off"></i> Logout</a></li>
		    </ul> 
		  3.- Se modifica shared.module.ts, se agrega import del RouterModule   
		  	@NgModule({
				  declarations: [
				    BreadcrumbsComponent,
				    SidebarComponent,
				    HeaderComponent,
				    FooterComponent
				  ],
				  exports:[
				    BreadcrumbsComponent,
				    SidebarComponent,
				    HeaderComponent,
				    FooterComponent
				  ],
				  imports: [
				    CommonModule,
				    RouterModule
				  ]
				})
				export class SharedModule { } 
		**************************************************************************************************************************************
		67. *********************************************************************************Servicio para controlar el Sidebar - Menú lateral
			1.- Se crea servicio para el sidebar --> ng g s services/sidebar --skip-tests
			2.- Se modifica sidebar.service.ts, se crearrglo de emnu con sus items  
				menu: any[] = [
			    {
			      titulo  : 'Dashboard!',
			      icono   : 'mdi mdi-gauge',
			      submenu : [
			        { titulo: 'Main', url:'/' },
			        { titulo: 'ProgressBar', url: 'progress' },
			        { titulo: 'Graficas', url: 'grafica1' }
			      ]
			    }
			  ]; 
			3.- Se modifica sidebar.componen.ts 
				3.1.- Se crea arreglo para el menu 
					itemsSidebar: any[];   
				3.2.- Se modifica constructor(), se recupera el menu del servicio
					  constructor( private sidebarService: SidebarService ) {
					    this.itemsSidebar = sidebarService.menu;
					  }
			4.- Se modifica sidebar.component.html, se renderiza menu  
				  	<!-- ============================================================== -->
					  <!-- Left Sidebar - style you can find in sidebar.scss  -->
					  <!-- ============================================================== -->
					  <aside class="left-sidebar">
					    <!-- Sidebar scroll-->
					    <div class="scroll-sidebar">
					        <!-- Sidebar navigation-->
					        <nav class="sidebar-nav">
					            <ul id="sidebarnav">
					                <li class="user-profile">
					                    <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><img src="./assets/images/users/profile.png" alt="user" /><span class="hide-menu">Steave Jobs </span></a>
					                    <ul aria-expanded="false" class="collapse">
					                        <li><a href="javascript:void()">My Profile </a></li>
					                        <li><a href="javascript:void()">My Balance</a></li>
					                        <li><a href="javascript:void()">Inbox</a></li>
					                        <li><a routerLink="setting">Account Setting</a></li>
					                        <li><a href="javascript:void()">Logout</a></li>
					                    </ul>
					                </li>
					                <li class="nav-devider"></li>
					                <li class="nav-small-cap">PERSONAL</li>
					                <li *ngFor="let item of itemsSidebar">
					                    <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false">
					                      <i class="mdi mdi-chart-bubble"></i>
					                      <span class="hide-menu">
					                        {{ item.titulo }}
					                        <span class="label label-rouded label-danger pull-right">
					                          {{ item.submenu.length }}
					                        </span>
					                      </span>
					                    </a>
					                    <ul aria-expanded="false" class="collapse">
					                        <li *ngFor="let subMenu of item.submenu">
					                          <a [routerLink]="subMenu.url">{{ subMenu.titulo }} </a>
					                        </li>
					                    </ul>
					                </li>
					            </ul>
					        </nav>
					        <!-- End Sidebar navigation -->
					    </div>
					    <!-- End Sidebar scroll-->
					</aside>
					<!-- ============================================================== -->
					<!-- End Left Sidebar - style you can find in sidebar.scss  -->
					<!-- ============================================================== -->
		**************************************************************************************************************************************
		68. ***************************************************************Uso de Scripts de archivos importados en el index.html en TypeScrip
				1.- Se modifica header.component.ts, se agrega routerlink que dirige a login 
				        <!-- ============================================================== -->
		            <!-- Profile -->
		            <!-- ============================================================== -->
		            <li class="nav-item dropdown">
		                <a class="nav-link dropdown-toggle waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="./assets/images/users/1.jpg" alt="user" class="profile-pic" /></a>
		                <div class="dropdown-menu dropdown-menu-right animated flipInY">
		                    <ul class="dropdown-user">
		                        <li>
		                            <div class="dw-user-box">
		                                <div class="u-img"><img src="./assets/images/users/1.jpg" alt="user"></div>
		                                <div class="u-text">
		                                    <h4>Steave Jobs</h4>
		                                    <p class="text-muted">varun@gmail.com</p><a href="pages-profile.html" class="btn btn-rounded btn-danger btn-sm">View Profile</a></div>
		                            </div>
		                        </li>
		                        <li role="separator" class="divider"></li>
		                        <li><a href="#"><i class="ti-user"></i> My Profile</a></li>
		                        <li><a href="#"><i class="ti-wallet"></i> My Balance</a></li>
		                        <li><a href="#"><i class="ti-email"></i> Inbox</a></li>
		                        <li role="separator" class="divider"></li>
		                        <li><a routerLink="setting"><i class="ti-settings"></i> Account Setting1</a></li>
		                        <li role="separator" class="divider"></li>
		                        <li><a routerLink="/login"><i class="fa fa-power-off"></i> Logout</a></li>
		                    </ul>
		                </div>
		            </li>
		    2.- Se modifica sidebar.component.html, se modifica routerlink para que dirija a login 
		      <li class="user-profile">
		        <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><img src="./assets/images/users/profile.png" alt="user" /><span class="hide-menu">Steave Jobs </span></a>
		        <ul aria-expanded="false" class="collapse">
		            <li><a href="javascript:void()">My Profile </a></li>
		            <li><a href="javascript:void()">My Balance</a></li>
		            <li><a href="javascript:void()">Inbox</a></li>
		            <li><a routerLink="setting">Account Setting</a></li>
		            <li><a routerLink="/login">Logout</a></li>
		        </ul>
		    	</li>
		    3.- Se modifica login.component.html, se modifica formulario para que ejecute funcion login() con el submit del boton   
		        <form class="form-horizontal form-material"
		        id="loginform"
		        (submit)="login()">
		    4.- Se modifica login.component.ts, se crea metodo para que redirija a dashboard 
		    		  constructor( private router: Router) { }

		  		    login(){
						    this.router.navigateByUrl('/');
						  }
		    5.- Se modifica auth.module.ts, se importa RouterModule
							@NgModule({
							  declarations: [
							    LoginComponent,
							    RegisterComponent,
							  ],
							  exports:[
							    LoginComponent,
							    RegisterComponent,
							  ],
							  imports: [
							    CommonModule,
							    RouterModule,
							    FormsModule
							  ]
							})
							export class AuthModule { }
				6.- Se modifica index.html, se modifica custom JavaScript 
					<!--Custom JavaScript -->
		      <script src="./assets/js/custom.js"></script>
		    7.- Se modifica custom.js  
		    	7.1.- Se envuelve la funcion en otra funcion customInitFunction()
		    		const customInitFunction = () => {
						  $(function() {
						    "use strict";
						    $(function() {
						        $(".preloader").fadeOut();
						    });
						    jQuery(document).on('click', '.mega-dropdown', function(e) {
						        e.stopPropagation()
						    });
						    // ==============================================================
						    // This is for the top header part and sidebar part
						    // ==============================================================
						    var set = function() {
						        var width = (window.innerWidth > 0) ? window.innerWidth : this.screen.width;
						        var topOffset = 0;
						        if (width < 1170) {
						            $("body").addClass("mini-sidebar");
						            $('.navbar-brand span').hide();
						            $(".sidebartoggler i").addClass("ti-menu");
						        } else {
						            $("body").removeClass("mini-sidebar");
						            $('.navbar-brand span').show();
						        }

						        var height = ((window.innerHeight > 0) ? window.innerHeight : this.screen.height) - 1;
						        height = height - topOffset;
						        if (height < 1) height = 1;
						        if (height > topOffset) {
						            $(".page-wrapper").css("min-height", (height) + "px");
						        }

						    };
						    $(window).ready(set);
						    $(window).on("resize", set);

						    // ==============================================================
						    // Theme options
						    // ==============================================================
						    $(".sidebartoggler").on('click', function() {
						        if ($("body").hasClass("mini-sidebar")) {
						            $("body").trigger("resize");
						            $("body").removeClass("mini-sidebar");
						            $('.navbar-brand span').show();

						        } else {
						            $("body").trigger("resize");
						            $("body").addClass("mini-sidebar");
						            $('.navbar-brand span').hide();

						        }
						    });

						    // this is for close icon when navigation open in mobile view
						    $(".nav-toggler").click(function() {
						        $("body").toggleClass("show-sidebar");
						        $(".nav-toggler i").toggleClass("ti-menu");
						        $(".nav-toggler i").addClass("ti-close");
						    });

						    $(".search-box a, .search-box .app-search .srh-btn").on('click', function() {
						        $(".app-search").toggle(200);
						    });
						    // ==============================================================
						    // Right sidebar options
						    // ==============================================================
						    $(".right-side-toggle").click(function() {
						        $(".right-sidebar").slideDown(50);
						        $(".right-sidebar").toggleClass("shw-rside");
						    });
						    // ==============================================================
						    // This is for the floating labels
						    // ==============================================================
						    $('.floating-labels .form-control').on('focus blur', function(e) {
						        $(this).parents('.form-group').toggleClass('focused', (e.type === 'focus' || this.value.length > 0));
						    }).trigger('blur');

						    // ==============================================================
						    // Auto select left navbar
						    // ==============================================================
						    $(function() {
						        var url = window.location;
						        var element = $('ul#sidebarnav a').filter(function() {
						            return this.href == url;
						        }).addClass('active').parent().addClass('active');
						        while (true) {
						            if (element.is('li')) {
						                element = element.parent().addClass('in').parent().addClass('active');
						            } else {
						                break;
						            }
						        }

						    });
						    // ==============================================================
						    //tooltip
						    // ==============================================================
						    $(function() {
						        $('[data-toggle="tooltip"]').tooltip()
						    })
						    // ==============================================================
						    //Popover
						    // ==============================================================
						    $(function() {
						        $('[data-toggle="popover"]').popover()
						    })
						    // ==============================================================
						    // Sidebarmenu
						    // ==============================================================
						    $(function() {
						        $('#sidebarnav').AdminMenu();
						    });

						    // ==============================================================
						    // Perfact scrollbar
						    // ==============================================================
						    $('.scroll-sidebar, .right-side-panel, .message-center, .right-sidebar').perfectScrollbar();

						    // ==============================================================
						    // Resize all elements
						    // ==============================================================
						    $("body").trigger("resize");
						    // ==============================================================
						    // To do list
						    // ==============================================================
						    $(".list-task li label").click(function() {
						        $(this).toggleClass("task-done");
						    });



						    // ==============================================================
						    // Collapsable cards
						    // ==============================================================
						    $('a[data-action="collapse"]').on('click', function(e) {
						        e.preventDefault();
						        $(this).closest('.card').find('[data-action="collapse"] i').toggleClass('ti-minus ti-plus');
						        $(this).closest('.card').children('.card-body').collapse('toggle');

						    });
						    // Toggle fullscreen
						    $('a[data-action="expand"]').on('click', function(e) {
						        e.preventDefault();
						        $(this).closest('.card').find('[data-action="expand"] i').toggleClass('mdi-arrow-expand mdi-arrow-compress');
						        $(this).closest('.card').toggleClass('card-fullscreen');
						    });

						    // Close Card
						    $('a[data-action="close"]').on('click', function() {
						        $(this).closest('.card').removeClass().slideUp('fast');
						    });

						  });
						}
					7.2.- Se llama a la funcion luego 
						customInitFunction();
				8.- Se modifica pages.component.ts, se llama a la funcion javascript de archivo externo desde typescript 
					8.1.- Se declara la funcion 
						declare function customInitFunction(): void;
					8.2.- Se modifica metodo ngOnInit(),se llama a la funcion 
						ngOnInit(): void {
					    customInitFunction();
					  }
		**************************************************************************************************************************************
		69. ************************************************************************************Guardar nuestros cambios en GitHub - Sección 7
			1.- Se preparan los cambios --> git add .
		 	2.- Se guardan los cambios en el local --> git commit -m FinSeccion7
		 	3.- Se suben los cambios al remoto --> git push
		 	4.- Se visualizan los tags --> git tag
		 	5.- Se agrega tag --> git tag -a v1.3.0 -m"Fin Seccion 7"
		 	6.- Se sube el tag al remoto --> git push --tags
		**************************************************************************************************************************************
	******************************************************* Fin Seccion ******************************************************************
	******************************************* Sección 8: Observables y Promesas*********************************************************
		74. ******************************************************************************************************Reforzamiento sobre Promesas
			1.- Se crea componente promesa 
				1.1.- Se ejecuta --> ng g c pages/promesas --skip-tests (tarea)
				1.2.- Se modifica sidebar.service.ts, se crea la entrada del menu para el componente creado (tarea)
				1.3.- Se modifica pages-routing.module.ts, se crea ruta para el nuevo componente (tarea)
					const routes: Routes = [
					  {
					    path      : 'dashboard',
					    component : PagesComponent,
					    children  : [
					      { path: '', component: DashboardComponent },
					      { path: 'progress', component: ProgressComponent },
					      { path: 'grafica1', component: Grafica1Component },
					      { path: 'setting', component: AccountSettingComponent },
					      { path: 'promesas', component: PromesaComponent },
					      { path: '', redirectTo: 'dashboard', pathMatch:'full' }
					    ]
					  },
			2.- Se modifica promesa.component.ts, se crea promesa en el metodo ngOnInit() 
				  ngOnInit(): void {
					    const promesa = new Promise( (resolve, reject) => {
					      if(true){
					        resolve('Hola Mundo');
					      }else{
					        reject('Algo salio mal');
					      }
					    });

					    promesa.then( (mensaje) => {
					      console.log(mensaje);
					    }).catch((error) => {
					      console.log( 'Error de la promesa', error );
					    })

					    console.log('Fin de la promesa')
					  }
					}
		**************************************************************************************************************************************
		75. ***************************************************************************************************Funciones que retornan promesas
			1.- Se accede a la pagina para consumir servicio --> https://reqres.in/
			2.- Se modifica promesa.component.ts, se crea metodo que devuelve promesa de usuario 
				  getUsuario(){
				    return new Promise( (resolve) => {
				      fetch( 'https://reqres.in/api/users' )
				        .then( resp => resp.json() )
				        .then( body => resolve( body.data ) );
				    } )
				  }
				2.1.- Se modifca metodo ngOnInit(), se llama a metodo getUsuario() y se imprime   
				  ngOnInit(): void {

				    this.getUsuario().then( resp => console.log( resp ) );

				    const promesa = new Promise( (resolve, reject) => {
				      if(true){
				        resolve('Hola Mundo');
				      }else{
				        reject('Algo salio mal');
				      }
				    });

				    promesa.then( (mensaje) => {
				      console.log(mensaje);
				    }).catch((error) => {
				      console.log( 'Error de la promesa', error );
				    })

				    console.log('Fin de la promesa')
				  }
		**************************************************************************************************************************************
		76. **********************************************************************************************Componente Rxjs y arreglo en el menú +
		**********************************************************************************************************************routerLinkActive
		*************************************************************************************************************[routerLinkActiveOptions]
			1.- Se crea componente --> ng g c pages/rxjs --skip-tests
			2.- Se modifica custom.js, se elemina auto select del js para poder hacerlo con directiva de angular 
				    // ==============================================================
				    // Auto select left navbar
				    // ==============================================================
				    $(function() {
				        var url = window.location;
				        var element = $('ul#sidebarnav a').filter(function() {
				            return this.href == url;
				        }).addClass('active').parent().addClass('active');
				        while (true) {
				            if (element.is('li')) {
				                element = element.parent().addClass('in').parent().addClass('active');
				            } else {
				                break;
				            }
				        }

				    });
			3.- Se modifica sidebar.component.html, se agrega routerLinkActive 
		      <li *ngFor="let subMenu of item.submenu">
		        <a [routerLink]="subMenu.url"
		            routerLinkActive="active"
		            //Indica que debe ser exactamente igual la opcion para que este activo 
		            [routerLinkActiveOptions]="{exact: true}">{{ subMenu.titulo }} </a>
		      </li>
		**************************************************************************************************************************************
		77. ***************************************************************************************************Crear un observable manualmente
		******************************************************************************************************************************Interval 
		*****************************************************************************************************************************Obsevable
		*****************************************************************************************************************************Subscribe
			1.- Se modifica rxjs.component.ts, se crea observable en el constructor
				  constructor() {
				    const obs$ = new Observable( observer => {
				      let i = -1;
				      const interval =   setInterval( () => {
				        i++;
				        observer.next(i);
				        if( i === 4 ){
				          //Se limpia y se culmina el interval
				          clearInterval( interval );
				          observer.complete();
				        }
				      }, 1000)
				    });

				    obs$.subscribe(
				      valor => console.log('Subs: ', valor),
				      error => console.warn('Error', error),
				      () => console.info('Observable termino.')
				    )

				  }
		**************************************************************************************************************************************
		78. *****************************************************************************************************Método Retry de un observable
			Nota: Retry se utiliza para cuando el observable falla pero aun asi se quiere volver a intentar una determinada cantidad de veces 
			1.- Se modifica rxjs.component.ts, se utiliza operador rxjs retry() para hacer reintentos del observable una cantidad de veces 
			determinada
				1.1.- Se modifica el metodo constructor()
					1.1.- Se saca la variable del interva 
						let i = -1;
					1.2.- Se genera el error del observables
						if( i === 2 ){
		          i= 0;
		          observer.error('i llego al valor 2');
		        }
		      1.3.- Se modifica el subscribe, se le añade pipe con el operador retry() y la cantidad de reintentos
		      	obs$.pipe(
				      retry(2)
				    ).subscribe(
				      valor => console.log('Subs: ', valor),
				      error => console.warn('Error', error),
				      () => console.info('Observable termino.')
				    )
				    --------------------------------------------------------------------------------------------
				  constructor() {
				    let i = -1;

				    const obs$ = new Observable( observer => {

				      const interval =   setInterval( () => {
				        i++;
				        observer.next(i);
				        if( i === 4 ){
				          clearInterval( interval );
				          observer.complete();
				        }

				        if( i === 2 ){
				          i= 0;
				          observer.error('i llego al valor 2');
				        }
				      }, 1000)
				    });

				    obs$.pipe(
				      retry(2)
				    ).subscribe(
				      valor => console.log('Subs: ', valor),
				      error => console.warn('Error', error),
				      () => console.info('Observable termino.')
				    )

				  }
		**************************************************************************************************************************************
		79. ************************************************************************************************Funciones que retornen observables
			1.- Se modifica rxjs.component.ts, se crea funcion que retorna el observable 
				1.1.- Se modifica el metodo constructor(), se corta el observable  
					constructor() {

				    this.retornaObservable().pipe(
				      retry(2)
				    ).subscribe(
				      valor => console.log('Subs: ', valor),
				      error => console.warn('Error', error),
				      () => console.info('Observable termino.')
				    )

				  }
				1.2.- Se crea metodo, se pega y retorna el observable
					  retornaObservable(): Observable<number>{
					    let i = -1;

					    return new Observable<number>( observer => {

					      const interval =   setInterval( () => {
					        i++;
					        observer.next(i);
					        if( i === 4 ){
					          clearInterval( interval );
					          observer.complete();
					        }

					        if( i === 2 ){
					          i= 0;
					          observer.error('i llego al valor 2');
					        }
					      }, 1000)
					    });
					  }
		**************************************************************************************************************************************
		80. ***************************************************************************************************Operador map de los observables
		*********************************************************************************************************************operador rxjs map 
		********************************************************************************************************************operador rxjs take  
		***************************************************************************************************************funcion rxjs interval()
			1.- Se modifica rxjs.componen.ts, se utilizan operadores rxjs take() y map()  
				1.1.- Se crea metodo retornaInterval 
					  retornaIntervalo(): Observable<string>{
					    return interval(1000).pipe(
					                          take(4),
					                          map( valor => 'Hola '+valor)
					    );
					  }
				1.2.- Se modifica metodo contructor(), se subscribe al ,metodo retornaInterval() 
					    this.retornaIntervalo().subscribe( valor => {
					      console.log(valor)
					    })
		**************************************************************************************************************************************
		81. *******************************************************************************************************************Operador filter
			1.- Se modifica metodo retornaInterval() de rxjs.component.ts, se utiliza oprador rxjs filter() 
				  retornaIntervalo(): Observable<number>{
				    return interval(1000).pipe(
				      map( valor => valor + 1),
				      filter( valor => (valor%2) == 0 ),
				      take(10),
				      );
				  } 
		**************************************************************************************************************************************
		83. *************************************************************************************************************Llamar el unsubscribe
			1.- Se modifica rxjs.component.ts, se utiliza metodo unsubcribe para dstruir el flujo del observable
				1.1.- Se crea variable para el observable 
					  public obsSubs: Subscription;
				1.2.- Se modifica el constructor, se almacena en la variable el observable 
					  constructor() {

					    /*this.retornaObservable().pipe(
					      retry(2)
					    ).subscribe(
					      valor => console.log('Subs: ', valor),
					      error => console.warn('Error', error),
					      () => console.info('Observable termino.')
					    )*/

					    this.obsSubs = this.retornaIntervalo().subscribe( valor => {console.log(valor)})

					  }
				1.3.- Se realiza el unsupscribe en el metodo OnDestroy
					1.3.1.- En la clase se implementa la clase OnDestroy 
						export class RxjsComponent implements OnInit, OnDestroy {...}
					1.3.2.- Se implementa el metodo OnDestroy() y se realiza el unsupscribe 
						  ngOnDestroy(): void {
						    this.obsSubs.unsubscribe();
						  }
		**************************************************************************************************************************************
		84. ******************************************************************************Breadcrumbs usando observables y parámetros de rutas
			1.- Se modifica pages-routing.module.ts, se modifican las rutas para que pasen por data el titulo de cada componente 
					const routes: Routes = [
					  {
					    path      : 'dashboard',
					    component : PagesComponent,
					    children  : [
					      { path: '', component: DashboardComponent, data: { titulo: 'Dashboard'}},
					      { path: 'progress', component: ProgressComponent , data: { titulo: 'Progress'}},
					      { path: 'grafica1', component: Grafica1Component , data: { titulo: 'Grafica #1'}},
					      { path: 'setting', component: AccountSettingComponent , data: { titulo: 'Setting'}},
					      { path: 'promesas', component: PromesaComponent , data: { titulo: 'Promesas'}},
					      { path: 'rxjs', component: RxjsComponent , data: { titulo: 'Rxjs'}},
					      { path: '', redirectTo: 'dashboard', pathMatch:'full' }
					    ]
					  },


					  //{ path: 'path/:routeParam', component: MyComponent },
					  //{ path: 'staticPath', component: ... },
					  //{ path: '**', component: ... },
					  //{ path: 'oldPath', redirectTo: '/staticPath' },
					  //{ path: ..., component: ..., data: { message: 'Custom' }
					];
			2.- Se modifica breadcrumbs.component.ts, se obtiene la data de titulo del router
				2.1.- Se crea variable para almacenar el titulo
					  titulo: string='';  
				2.2.- Se modifica metodo constructor() para hacer la injeccion de dependencia del router y luego recuperar la data de titulo del 
							router
							  constructor( private router: Router ) {
							  	//Se recupera evento
							    this.router.events
							    .pipe(
							    	//Se filtra por evento de tipo ActivationEnd
							      filter((event): event is ActivationEnd => event instanceof ActivationEnd),
							      // Se filtra donde el firstChild sea nulo
							      filter((event:ActivationEnd) => event.snapshot.firstChild === null ),
							      // Se retorna la data obtenida 
							      map((event:ActivationEnd) => event.snapshot.data),
							      // La data titulo se le asigna a la variable y al titulo del documento
							    ).subscribe(({titulo}) =>{
							      this.titulo = titulo;
							      document.title = `AdminPro - ${titulo}`
							    });
							  } 
				2.3.- Se deja un poco mas limpio el constructor como buena practica 
						2.3.1.- Se crea metodo getTitle() y se copia todo lo que esta dentro del constructor
							getTitle() {
							  	//Se recupera evento
							    this.router.events
							    .pipe(
							    	//Se filtra por evento de tipo ActivationEnd
							      filter((event): event is ActivationEnd => event instanceof ActivationEnd),
							      // Se filtra donde el firstChild sea nulo
							      filter((event:ActivationEnd) => event.snapshot.firstChild === null ),
							      // Se retorna la data obtenida 
							      map((event:ActivationEnd) => event.snapshot.data),
							      // La data titulo se le asigna a la variable y al titulo del documento
							    ).subscribe(({titulo}) =>{
							      this.titulo = titulo;
							      document.title = `AdminPro - ${titulo}`
							    });
							  } 
						2.3.2.- En el constructor se llama al metodo creado 
							constructor( private router: Router ) { 
								this.getTitle(); 
							}
			3.- Se modifica breadcrumbs.component.html, se renderiza el titulo obtenido 
				<!-- ============================================================== -->
				<!-- Bread crumb and right sidebar toggle -->
				<!-- ============================================================== -->
				<div class="row page-titles">
				  <div class="col-md-5 align-self-center">
				      <h3 class="text-themecolor">{{titulo}}</h3>
				  </div>
				  <div class="col-md-7 align-self-center">
				      <ol class="breadcrumb">
				          <li class="breadcrumb-item">
				              <a href="javascript:void(0)">Home</a>
				          </li>
				          <li class="breadcrumb-item">pages</li>
				          <li class="breadcrumb-item active">{{titulo}}</li>
				      </ol>
				  </div>
				  <div>
				      <button class="right-side-toggle waves-effect waves-light btn-inverse btn btn-circle btn-sm pull-right m-l-10"><i class="ti-settings text-white"></i></button>
				  </div>
				</div>
				<!-- ============================================================== -->
				<!-- End Bread crumb and right sidebar toggle -->
				<!-- ============================================================== -->
		**************************************************************************************************************************************
		85. ****************************************************************************************************Optimizaciones del Breadcrumbs
			1.- Se modifica breadcrumbs.component.ts, se realiza unsubscribe() del observable 
				1.1.- Se implementa clase OnDestroy en la clase
					export class BreadcrumbsComponent implements OnDestroy{...}  
				1.2.- Se crea metodo ngOnDestroy() y se realiza el unsupscribe() del observable  
					  ngOnDestroy(): void {
						    this.tituloSub$.unsubscribe();
						  }
		**************************************************************************************************************************************
		86. *********************************************************************************************Guardar cambios en GitHub - Sección 8
			1.- Se preparan los cambios --> git add .
		 	2.- Se guardan los cambios en el local --> git commit -m FinSeccion8
		 	3.- Se suben los cambios al remoto --> git push
		 	4.- Se visualizan los tags --> git tag
		 	5.- Se agrega tag --> git tag -a v1.4.0 -m"Fin Seccion 8"
		 	6.- Se sube el tag al remoto --> git push --tags	
		**************************************************************************************************************************************
	******************************************************* Fin Seccion ******************************************************************
	************************ Sección 9: Backend - Node - Express- Mongo - Instalaciones y Configuraciones ********************************
		90. *********************************************************************************************************Inicio del backend server
			1.- Se crea folder para el proyecto del backend --> adminProBackend 
			2.- Se crea package.json --> npm init -y  (Crea el packege.json)
			3.- Se instala express --> npm install express --save
				Referencia --> https://expressjs.com/es/
		**************************************************************************************************************************************
		91. *********************************************************************************************Iniciando nuestro servidor de Express
			1.- Se crea un archivo en la raiz --> index.js 
			2.- Se modifica el archivo index.js 
				console.log('Hello word!!!');
			3.- Se prueba correr el archivo creado --> node index.js 
			4.- Se modifica el archivo index.js 
				// Se importa express 
					const express = require('express');
				// Se crea el servidor de express
				const app = express();
				// Se levanta el aplicativo en un puerto determinado 
				app.listen( 3000, () => {
				    console.log('Servidor corriendo en el puerto'+ 3000);
				})
			5.- Se ejecuta --> node index.js 
			6.- Se prueba en el navegador --> localhost:3000/ 
				Nota: El servidor debe estar recibiendo peticiones 
			7.- Se instala nodemon, se abre consola con permisos de administrador y se ejecuta --> npm install -g nodemon
				Nota: Se installa para que detecte los cambios en caliente 
			8.- Se ejecuta aplicacion en caliente --> nodemon index.js
			9.- Se modifica package.json, se modifica script para correr aplicacion 
				{
				  "name": "AdminProBackend",
				  "version": "1.0.0",
				  "description": "",
				  "main": "index.js",
				  "scripts": {
				    "start:dev": "nodemon index.js"
				  },
				  "keywords": [],
				  "author": "",
				  "license": "ISC",
				  "dependencies": {
				    "express": "^4.17.1"
				  }
				}
			10.- Se ejecuta script --> 	npm run start:dev
		**************************************************************************************************************************************
		93. **********************************************************************************************************************Primera ruta
			1.- Se modifica index.js, se crea primera ruta de acceso 
				app.get( '/', (req, res) => {
				    res.json({
				        ok: true,
				        msg: 'Hello word'
				    });
				});
			2.- Se prueba en el navegador --> localhost:3000/
			3.- Se prueba en postman --> localhost:3000/
		**************************************************************************************************************************************
		94. *****************************************************************************************************Nota: Enlaces a MongoDB Altas
			* Referencia 
				https://www.mongodb.com/es/cloud/atlas/efficiency
		**************************************************************************************************************************************
		96. *************************************************************************************************Mongo Altas - Configuración de BD
				Referencia --> https://cloud.mongodb.com
				https://www.udemy.com/course/angular-fernando-herrera/learn/lecture/24468434#overview
			1.- Se crea Bd en la opcion de Database Access 
				user: eareizac
				pass: UnCBCqbB4ClLqMtf
			2.- Se descarga el aplicativo MongoDB Compass 
				Se ingresa en el aplicativo y se conecta con la base de datos con la cadena de conexion 
					mongodb+srv://eareiza:UnCBCqbB4ClLqMtf@clustertutoangular.ljd5e.mongodb.net/hospitaldb
			3.- Se instala mongoose --> npm i mongoose
				3.1.- Se prueba la aplicacion --> npm run start:dev
			4.- Conectar desde compass con el string de conexion
				1.1.- Se añade ip de conexion en networw access desde el aplicativo de mongoDB en el explorador 
					0.0.0.0/0
			5.- Se crea archivo de configuracion de la DB. 
				raiz --> db --> config.js 
			6.- Se modifica config.js 
				const mongoose = require('mongoose');

				const dbConnection = async() => {
				    try { 
				        await mongoose.connect( 'mongodb+srv://eareiza:UnCBCqbB4ClLqMtf@clustertutoangular.ljd5e.mongodb.net/hospitaldb',{
				            useNewUrlParser: true
				        });
				        console.log('DB Online');
				    } catch (error) {
				        console.log(error);
				        throw new Error('Error a la hora de inicializad DB');
				    }
				}

				module.exports = {
				    dbConnection
				}
			7.- Se modifica index.js, se ejecuta conexion 
				const {} = require('.db/config');

				//Base de Datos
				dbConection();
			8.- Se instala dotenv para las variables de entorno, este paquete permite leer archivos con extension .env  
				npm i dotenv
				8.1.- Se crea archivo en la raiz .env 
				8.2.- Se modifica index.js, se importa el archivo de enviroment  
					require('dotenv').config(); 
			9.- Se modifica archivo .env, se agrega la cadena de conexion y el puerto 
				PORT=4000
				BD_CNN=mongodb+srv://eareiza:UnCBCqbB4ClLqMtf@clustertutoangular.ljd5e.mongodb.net/myAngular

			10.- Se modifica la cadena de conexion de mongoose 
				const dbConnection = async() => {
			    try { 
			        await mongoose.connect( process.env.BD_CNN ,{
			            useNewUrlParser: true
			        });
			        console.log('DB Online');
			    } catch (error) {
			        console.log(error);
			        throw new Error('Error a la hora de inicializad DB');
			    }
			}
		**************************************************************************************************************************************
		98. *******************************************************************************************************************Configurar CORS
			1.- Se instala cors --> npm i cors 
			2.- Se modifica index.js
				2.1.- Se importa la libreria --> const cors = require('cors'); 
				2.2.- Se configura -->  
						//Configuracion de cors
						app.use(cors());
		**************************************************************************************************************************************
		99. *********************************************************************************************Guardar cambios en GitHub - Sección 8
			0.- Se crea repositorio en Github 
			1.- Se agrega al proyecto archivo .gitignore, para que ignore esta carpeta  
				node_modules/
			2.- Se crea archivo de README.md 
				# AdminProBackend

				Recuerden ejecutar 
				    npm install 
			3.- Se inicializa git --> git init	
				Nota: debemos asegurarnos que todos los archivos del proyecto esten de color verde menos el node_modules 
			4.- Se preparan los cambios --> git add .
		 	5.- Se guardan los cambios en el local --> git commit -m CORS_SPRESS
		 	6.- Se configura repositorio remoto creado en github 
		 		git remote add origin https://github.com/dirielfran/adminProBack.git 
		 	7.- Se crea branch main    
		 		git branch -M main
		 	8.- Se suben los cambios al repositorio remoto 
		 		git push -u origin main
		 	5.- Se agrega tag --> git tag -a v0.1.0 -m "inicio de backend"
		 	6.- Se sube el tag al remoto --> git push --tags	
		**************************************************************************************************************************************
	******************************************************* Fin Seccion ******************************************************************
	***************************** Sección 10: HospitalAPP - Backend Server - Funciones de Usuarios ***************************************
		105. ****************************************************************************************************Creando el modelo de usuarios
			1.- Se crea folder --> models 
			2.- Se crea modelo --> usuario.ts 
			3.- Se modifica usuario.ts 
				3.1.- Se importa Schema y model de mongoose 
					const { Schema, model }= require('mongoose');
				3.2.- Se crea el schema del usuario 
					  const UsuarioSchema = Schema({
					    nombre: {
					        type: String,
					        required: true
					    },
					    email: {
					        type: String,
					        required: true,
					        unique: true
					    },
					    password: {
					        type: String,
					        required: true
					    },
					    img: {  
					        type: String
					    },
					    role: {
					        type: String,
					        required: true,
					        default: 'ROLE_USER'
					    },
					    google: {
					        type: Boolean,
					        default: false
					    }
					});   
				3.3.- Se exporta el modulo  
					module.exports = model( 'Usuario', UsuarioSchema);
		**************************************************************************************************************************************
		106. ***********************************************************************************Creando las rutas de los servicios del usuario
			1.- Se crea folder en raiz --> routes 
			2.- Se crea archivo de rutas para usuarios en el folder creado  --> usuario.route.ts 
			3.- Se crea folder controller y archivo usuario.controller.ts 
			4.- Se modifica el archivo usuario.controller.ts 
				4.1.- Se crea constante con funcion que maneja el request y el response
					const getUsuarios = (req, res) => {
					    res.json({
					        ok: true,
					        usuarios: []
					    });
					};
				4.2.- Se exporta la la funcionalidad  
					module.exports = { getUsuarios, };
			5.- Se modifica archivo index.j
					//rutas 
					app.use( '/api/usuarios', require('./routes/usuarios.ts'));
			6.- Se modifica archivo usuario.route.ts   
				6.0.- Se crean rutas a usuario y se importa del controlador la funcion que maneja el request y el response 
					const { getUsuarios } = require('../controllers/usuario.controller.ts');
				6.1.- Se importa Router de la libreria de express 
					const { Router } = require('express');
				6.2.- Se llama a la funcion y se alamacena en variable
					const router = Router();  
				6.3.- Se exporta el router
					module.exports = router;
				6.4.- Se crea la ruta a usuario 
					router.get( '/', getUsuarios );	
		**************************************************************************************************************************************
		107. *************************************************************************************************************POST - Crear usuario
			1.- Se creo nueva base de datos en mongo --> hospitaldb 
			2.- Se modifica el archivo de enviroment --> .env, se modifica la conexion 
				BD_CNN=mongodb+srv://eareiza:UnCBCqbB4ClLqMtf@clustertutoangular.ljd5e.mongodb.net/hospitaldb
			3.- Se modifica usuario.controller.ts
				3.1.- Se crea metodo crearsuario 
					const crearUsuario = async (req, res) => {
					    const { nombre, password, email } = req.body; 

					    const usuario = new Usuario( req.body )

					    await usuario.save();

					    res.json({
					        ok: true,
					        usuario: usuario
					    });
					};
				3.2.- Se exporta metodo 
					module.exports = { getUsuarios, crearUsuario, };
			4.- Se modifica archivo usuario.route.ts 
				1.- Se importa el metodo de crearUsuario
					const { getUsuarios, crearUsuario } = require('../controllers/usuario.controller.ts');
				2.- Se crea nueva ruta   
					router.post( '/', crearUsuario );
			5.- Se modifica archivo index.js, se añade al middleware el manejo de json 
				//Lectura y parseo del body 
				app.use( express.json() )
		**************************************************************************************************************************************	
		107. **************************************************************************************************Terminar el GET de los usuarios
			1.- Se modifica usuario.controller.ts 
				const crearUsuario = async (req, res) => {
						//Se desestructuran las variables del body del request
				    const { nombre, password, email } = req.body; 

				    //Se crea un usuario con el contenido del request
				    const usuario = new Usuario( req.body )

				    //Se guarda el usuario en la base de datos 
				    await usuario.save();

				    res.json({
				        ok: true,
				        usuario: usuario
				    });
				};
			3.- Se modifica usuario.ts, se utiliza metodo del schema para modificar el objeto a retornar
				UsuarioSchema.method('toJSON', function(){
				    const { __v, _id, ...object } = this.toObject();
				    object.uid = _id;
				    return object;
				})
		**************************************************************************************************************************************
		108. **************************************************************************************Validar que el correo electrónico sea único
			1.- Se modifica usuario.controller.ts, se modifica metodo para crear usuario se crea validacion para usuario registrado con 
			anterioridad
				const crearUsuario = async (req, res = response) => {
				    const { nombre, password, email } = req.body; 

				    try{
				        console.log('email', email);
				        const existeUsuario = await Usuario.findOne({email});
				        if(existeUsuario){
				            return res.status(400).json({
				                ok: false,
				                msg: 'Usuario ya se encuentra registrado'
				            });
				        }
				        const usuario = new Usuario( req.body );    
				        await usuario.save();
				        res.json({
				            ok: true,
				            usuario: usuario
				        });
				    }catch(error){
				        console.log(error);
				        res.status(500).json({
				            ok: 'false',
				            msg: 'Eroor inesperado.'
				        })

				    }
				};	
				1.- Se importa response de express 
					const { response }= require('express');
		**************************************************************************************************************************************
		109. ******************************************************************************************************Validar campos obligatorios
		*********************************************************************************************************************express-validator
			1.- Se instala --> express-validator
				npm i express-validator   
			2.- Validacion de errores en el metodo crearUsuario()
				2.1.- Se modifica usuario.controller.ts 
					2.1.1.- Se importa validationResult de la libreria express-validator 
						const { validationResult }= require('express-validator');
					2.1.2.- Se modifica metodo crearUsuario(), para validar cada campo del request y mostrar mensaje correspondiente 
						const crearUsuario = async (req, res = response) => {
					    const { nombre, password, email } = req.body;
					    //Se recuperan errores del request 
					    const errores = validationResult(req);
					    //Validacion de errores
					    if(!errores.isEmpty()){
					        return res.status(400).json({
					            ok: false,
					            errors: errores.mapped()
					        })
					    }
					    try{
					        const existeUsuario = await Usuario.findOne({email});
					        if(existeUsuario){
					            return res.status(400).json({
					                ok: false,
					                msg: 'Usuario ya se encuentra registrado'
					            });
					        }
					        const usuario = new Usuario( req.body );    
					        await usuario.save();
					        res.json({
					            ok: true,
					            usuario: usuario
					        });
					    }catch(error){
					        console.log(error);
					        res.status(500).json({
					            ok: 'false',
					            msg: 'Eroor inesperado.'
					        })
					    }
						};
				2.2.- Se modifica usuario.route.ts
					2.2.1.- Se importa check de la libreria express-validator  
						const { check } = require('express-validator') 
					2.2.2.- Se modifica la ruta post de creacion de usuario, añadiendo los middleware check a cada campo
						router.post( '/', [
						    check('nombre', 'El nombre es obligatorio').not().isEmpty(),
						    check('password', 'El password es obligatorio').not().isEmpty(),
						    check('email', 'El email es obligatorio').isEmail()
						], crearUsuario );
		**************************************************************************************************************************************
		110. *****************************************************************************************Middleware personalizado - ValidarCampos
			1.- Se crea archivo --> middlewares --> validar-campos.js 
			2.- Se modifica usuario.controller.ts, se modifica metodo de crearUsuario() y se corta la validacion de campos y se elimina 
			la importacion de validationResult que ya	no se utiliza
				const crearUsuario = async (req, res = response) => {
				    const { nombre, password, email } = req.body; 
				    
				    try{
				        const existeUsuario = await Usuario.findOne({email});
				        if(existeUsuario){
				            return res.status(400).json({
				                ok: false,
				                msg: 'Usuario ya se encuentra registrado'
				            });
				        }
				        const usuario = new Usuario( req.body );    
				        await usuario.save();
				        res.json({
				            ok: true,
				            usuario: usuario
				        });
				    }catch(error){
				        console.log(error);
				        res.status(500).json({
				            ok: 'false',
				            msg: 'Eroor inesperado.'
				        })
				    }
				};
			3.- Se modifica validar-campos.js, se crea la validacion de campos
				3.1.- Se importa response y validationResult 
					const { response } = require('express');
					const { validationResult } = require('express-validator');
				3.2.- Se crea constante de validacion de campos 
					const validarCampos = (req, res = response , next) => {
						//Se capturan errores del request
				    const errores = validationResult(req);

				    //Validacion de errores
				    if(!errores.isEmpty()){
				        return res.status(400).json({
				            ok: false,
				            errors: errores.mapped()
				        })
				    }

				    return next();
					}
				3.3.- Se exporta el metodo validarCampos() 
					module.exports = {
					    validarCampos,
					}
			4.- Se modifica usuario.route.ts, se llama al metodo de validacion luego de los check 
				4.1.- se importa el metodo validarCmpos 
					const { validarCampos } = require('../middlewares/validar-campos');
				4.2.- Se modifica el metodo post de creacion de usuario, se le añade validarCampos 
					router.post( '/', [
					    check('nombre', 'El nombre es obligatorio').not().isEmpty(),
					    check('password', 'El password es obligatorio').not().isEmpty(),
					    check('email', 'El email es obligatorio').isEmail(),
					    validarCampos
					], crearUsuario );
		**************************************************************************************************************************************
		111. ****************************************************************************Encriptar la contraseña usando método de una sola vía
		******************************************************************************************************************************bcryptjs
			1.- Se intala bcryptjs
				npm i bcryptjs
			2.- Se modifica usuario.controller.ts
				2.1.- Se importa bcryptjs 
					const bcryptjs = require('bcryptjs');
				2.2.- Se modifica metodo crearUsuario(), se le agrega encriptacion de la contraseña 
					  //Encriptacion de contraseña 
		        const salt = bcryptjs.genSaltSync();
		        usuario.password = bcryptjs.hashSync( password, salt );
		  Nota: se eliminan los usuario creados en la base de datos mongo y se prueba el endpoint de creacion de usuario 
		  	post --> http://localhost:4000/api/usuarios

		  	body --> {
									    "nombre": "Elvis",
									    "password": "123456",
									    "email": "dirielfran@gmail.com"
									}
		**************************************************************************************************************************************
	******************************************************* Fin Seccion ******************************************************************
	**************************************** Sección 12: Backend -Autenticacion con Google Sign-in ***************************************
		Referencia --> https://developers.google.com/identity/gsi/web/guides/overview
		144. Crear ID de la aplicación y un ID secreto - Google Developer*********************************************************************
			1.- Se crea app en Google --> https://console.cloud.google.com/apis/dashboard?project=kioscoapp
			2.- seleccionar Proyectos --> Proyecto Nuevo
			3.- Pantalle de consentimiento
				Nombre de la aplicacion --> KioscoApp
				Correo electrónico de asistencia del usuario  --> dirielfran@gmail.com
				Logotipo -->   kiosco.png
				Contacto del desarrollador --> dirielfran@gmail.com 
			4.- Permisos  --> No se asocia ninguno  --> Guardar y continuear
			5.- Usuarios de Prueba  --> Guardar y continuar  --> Volver al panel  
			6.- Seleccionar Credenciales --> Crear ID de Cliente OAuth
				Tipo de aplicacion --> aplicacion Web  
				Nombre --> KioscoApp1
				Orígenes autorizados de JavaScript --> 
					http://localhost
					http://localhost:4200
					http://localhost:8080
				--> Crear  
				Se crea el cliente de OAuth
					ID de cliente 
						87337512484-5ot6pus6ha76hs4co1n5ejsef1srsc11.apps.googleusercontent.com
					SECRET 
						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
		************************************************************************************************************************************
		173. Obtener el Token de una autenticación, Google Sign-In**************************************************************************
			Nota: se tomo como ejemplo KioscoApp 
			1.- Se modifica --> index.html, se agrega script de google -->
				<script src="https://accounts.google.com/gsi/client" async defer></script>
			2.- Se modifica login.component.html, se crea div que haaga de boton google SingIn 
				<div #googleBtn id="buttonDiv"></div>
			3.- Se modifica login.component.ts 
				3.1.- Se agrega referencia al boton  
					  @ViewChild('googleBtn') googleBtn: ElementRef;
				3.2.- Se utiliza el metodo ngAfterViewInit, para que despues que este cargada la pagina se renderice el botone
					 ngAfterViewInit(): void {
				    google.accounts.id.initialize({
				      client_id: "87337512484-5ot6pus6ha76hs4co1n5ejsef1srsc11.apps.googleusercontent.com",
				      callback: this.handleCredentialResponse
				    });
				    google.accounts.id.renderButton(
				        this.googleBtn.nativeElement,
				        { theme: "outline", size: "large" }  // customization attributes
				    );
				  }

				 	handleCredentialResponse(response: any){
				    console.log("Encoded JWT ID token: "+ response.credential)
				  }
			**********************************************************************************************************************************
			174. Usar el Token de Google para autenticarnos***********************************************************************************
			**********************************************************************************************************************************
		************************************************************************************************************************************
	**************************************************************************************************************************************
	**************************************************************************************************************************************
	******************************************************* Fin Seccion ******************************************************************



	Seccion 22: Pruebas Unitarias*********************************************************************************************************
		272.- Probando String***************************************************************************************************************
		*****************************************************************************************************************************ng test
		***************************************************************************************************************************decribe()
		********************************************************************************************************************************it()
		*******************************************************************************************************************************spect
		******************************************************************************************************************************toBe()
			La función describe() es para agrupar especificaciones relacionadas, normalmente cada archivo de prueba tiene uno en el nivel 
			superior. El parámetro de cadena es para nombrar la colección de especificaciones y se concatenará con las especificaciones para 
			formar el nombre completo de una especificación. Esto ayuda a encontrar especificaciones en una suite grande. 

			Una Spect contiene una o más expectativas que prueban el estado del código. Una expectativa en Jasmine es una afirmación que es 
			verdadera o falsa. Una especificación con todas las expectativas verdaderas es una especificación aprobatoria.

			Dado que los bloques describe y it son funciones, pueden contener cualquier código ejecutable necesario para implementar la prueba. 
			Se aplican las reglas de alcance de JavaScript, por lo que las variables declaradas en a describe están disponibles para cualquier 
			it bloque dentro de la suite.

			1.- Se crea folder para pruebas --> testBasic --> string 	--> string.ts
																																--> string.spec.ts
			2.- Se modifica string.ts, se crea funcion 
				export function mensaje(nombre: string){
				    return `Saludos ${nombre}`;
				}
			3.- Se modifica string.spec.ts, se crea prueba 
				import { mensaje } from "./string"


				describe('Pruebas de string', () => {
				    it('Debe regresar un string', () =>{
				        const res = mensaje('Elvis');
				        expect( typeof res ).toBe('string');
				    })
				})
		***********************************************************************************************************************************
		274.- Probando numeros***************************************************************************************************************
			1.- Se crea folder para pruebas --> testBasic --> number	--> number.ts
																																--> number.spec.ts
			2.- Se modifica number.ts, se crea funcion 
				export function incrementar(num: number){
				    if( num > 100){
				        return 100;
				    }else{
				        return num + 1;
				    }
				}
			3.- Se modifica number.spec.ts, se crea prueba 
				import { incrementar } from "./number";

				describe('Prueba de numeros', () =>{
				    it('Debe de retornar 100 si el numero es mayor a 100', () =>{
				        const inc = incrementar(300);
				        expect( inc ).toBe(100);
				    });
				    it('Debe de retornar el numero mas 1 si es menor a 100', () =>{
				        const inc = incrementar(55);
				        expect( inc ).toBe(56);
				    });
				})
		***********************************************************************************************************************************
		275.- Probando boolean***************************************************************************************************************
		*****************************************************************************************************************************toBeTrue
		************************************************************************************************************************.not.toBeTrue
			1.- Se crea folder para pruebas --> testBasic --> boolean	--> boolean.ts
																																--> boolean.spec.ts
			2.- Se modifica boolean.ts, se crea funcion 
				export function testBoolean(){
				    return true;
				}

				export function testBooleanFalse(){
				    return false;
				}
			3.- Se modifica boolean.spec.ts, se crea prueba 
				import { testBoolean, testBooleanFalse } from "./boolean";

				describe('Prueba de Boolean', () => {
				    it('Probando verdaderos', () => {
				        const verd = testBoolean();
				        expect(verd).toBeTrue();
				    });
				    it('Probando con negacion', () => {
				        const verd = testBooleanFalse();
				        expect(verd).not.toBeTrue();        
				    })
				});
		***********************************************************************************************************************************
		276.- Probando array***************************************************************************************************************
		***************************************************************************************************************************toContain
			1.- Se crea folder para pruebas --> testBasic --> arreglos	--> arreglo.ts
																																	--> arreglo.spec.ts
			2.- Se modifica arreglo.ts, se crea funcion 
				export function getArray(){
				    return ['Elvis', 'Diego', 'Antonio', 'Ricardo', 'Camila']
				}
			3.- Se modifica arreglo.spec.ts, se crea prueba 
				import { getArray } from "./arreglos";

				describe('Prueba de arreglos', ()=>{
				    it('Debe retorna el size correcto del arreglo', () => {
				        const arreglo = getArray();
				        expect(arreglo.length).toBeGreaterThanOrEqual(5); 
				    });
				    it('Debe existir con contain', () => {
				        const arreglo = getArray();
				        expect(arreglo).toContain('Elvis'); 
				        expect(arreglo).toContain('Antonio'); 
				    });
				});
		***********************************************************************************************************************************
		277.- Probando clases***************************************************************************************************************
			1.- Se crea folder para pruebas --> testBasic --> clases	--> clase.ts
																																--> clase.spec.ts
			2.- Se modifica clase.ts, se crea funcion 
				export class Jugador{

				    hp: number;

				    constructor(){
				        this.hp = 100;
				    }

				    recibeDanio(danio: number): number{
				        if( danio >= this.hp ){
				            this.hp = 0;
				        }else{
				            this.hp = this.hp - danio;
				        }
				        return this.hp;
				    }
				}
			3.- Se modifica clase.spec.ts, se crea prueba 
				import * as exp from "constants";
				import { Jugador } from "./clase";

				describe('Pruebas de arrego', () => {
				    const jugador = new Jugador();
				    it('Debe retornar el hp correcto', () => {
				        const danio = jugador.recibeDanio(50);
				        expect(danio).toBe(50);
				    });
				    it('Debe retornar el hp 0', () => {
				        const danio = jugador.recibeDanio(150);
				        expect(danio).toBe(0);
				    });
				});
		***********************************************************************************************************************************
		278.- Ciclos de vida***************************************************************************************************************
		*************************************************************************************************************************beforeAll()
		**************************************************************************************************************************afterAll()
		************************************************************************************************************************beforeEach()
		*************************************************************************************************************************afterEach()
			1.- Se modifica clase.spec.ts
			import { Jugador } from "./clase";

			describe('Pruebas de arrego', () => {
			    const jugador = new Jugador();

			    afterAll(() => { console.log('afterAll')});
			    beforeAll(() => { console.log('beforeAll')});
			    beforeEach(() => { 
			        const jugador = new Jugador(); 
			        console.log('beforeEach');
			    });
			    afterEach(() => { console.log('afterEach')});
			    it('Debe retornar el hp correcto', () => {
			        const danio = jugador.recibeDanio(50);
			        expect(danio).toBe(50);
			    });
			    it('Debe retornar el hp 0', () => {
			        const danio = jugador.recibeDanio(150);
			        expect(danio).toBe(0);
			    });
			});
		***********************************************************************************************************************************
		279. Observar el porcentaje de cobertura de nuestra aplicación - code-coverage*****************************************************
		************************************************************************************************************ng test --code-coverage
		***********************************************************************************************************************************
		284. Event Emitter*****************************************************************************************************************
			1.- Se crean folder --> testBasico --> intermedio --> eventEmmiter --> jugador2.ts 
																																					--> jugador.spec.ts  
			2.- Modificacion en jugador2.ts  
				import { EventEmitter } from "@angular/core";

				export class Jugador2{

				    hp: number;
				    hpCambia = new EventEmitter<number>();

				    constructor(){
				        this.hp = 100;
				    }

				    recibeDanio(danio: number){
				        if( danio >= this.hp ){
				            this.hp = 0;
				        }else{
				            this.hp = this.hp - danio;
				        }
				        this.hpCambia.emit(this.hp);
				    }
				}
			3.- Modificacion en jugador2.spec.ts 
				import { Jugador2 } from "./jugador2";

				describe('Pruebas de EventEmmiter', () => {
				    let jugador: Jugador2;
				    beforeEach( () => jugador = new Jugador2() );
				    it('Debe emitir evento cuando recibe daño',()=>{
				        let nuevoHp= 0;
				        jugador.hpCambia.subscribe( hp => {
				            nuevoHp = hp;
				        });
				        jugador.recibeDanio(1000);
				        expect(nuevoHp).toBe(0);
				    });

				    it('Debe emitir evento cuando recibe daño, sobrevive si es menor a 100',()=>{
				        let nuevoHp= 0;
				        jugador.hpCambia.subscribe( hp => {
				            nuevoHp = hp;
				        });
				        jugador.recibeDanio(50);
				        expect(nuevoHp).toBe(50);
				    });
				});
		***********************************************************************************************************************************
		284.- Formularios******************************************************************************************************************
			1.- Se crea formulario.ts 
				import { FormBuilder, FormGroup, Validators } from "@angular/forms";


				export class FormularioRegister{
				    myForm: FormGroup;

				    constructor( fb: FormBuilder){
				        this.myForm = fb.group({
				            email: ['', [ Validators.required, Validators.email]],
				            password: ['', Validators.required]
				        })
				    }
				}
			2.- Se modifica formulario.spec.ts 
				import { FormBuilder } from "@angular/forms";
				import { FormularioRegister } from "./formulario"

				describe('Formularios', () =>{
				    let componente: FormularioRegister;

				    beforeEach(() => componente = new FormularioRegister(new FormBuilder()));

				    it('Debe crear un formulario con dos campos, email y password', ()=> {
				        expect( componente.myForm.contains('email')).toBeTrue();
				        expect( componente.myForm.contains('password')).toBeTrue();

				    })
				})
		***********************************************************************************************************************************
		286.- Validaciones de formularios**************************************************************************************************
			1.- Se modifica formulario.spec.ts 
				import { FormBuilder } from "@angular/forms";
				import { FormularioRegister } from "./formulario"

				describe('Formularios', () =>{
				    let componente: FormularioRegister;

				    beforeEach(() => componente = new FormularioRegister(new FormBuilder()));

				    it('Debe crear un formulario con dos campos, email y password', ()=> {
				        expect( componente.myForm.contains('email')).toBeTrue();
				        expect( componente.myForm.contains('password')).toBeTrue();
				    });
				    it('El mail es obligatorio', () =>{
				        let control = componente.myForm.get('email');
				        control.setValue('')
				        expect( control.valid ).toBeFalse();
				    });
				    it('Debe poseer un correo valido', () =>{
				        let control = componente.myForm.get('email');
				        control.setValue('dirielfran@gmail.com');
				        expect( control.valid ).toBeTrue();
				    });

				})
		***********************************************************************************************************************************
		289. Espías************************************************************************************************************************
		****************************************************************************************************************************spyOn()
		*************************************************************************************************************************callFake()
			1.- Se crea archivo de pruebas en kioscoApp --> src/app/configuraciones/proveedores/list/list-proveedores.component.spec.ts
				import { ProovedoresService } from "src/app/services/proovedores.service";
				import { ListProovedoresComponent } from "./list-proovedores.component";
				import 'rxjs/add/observable/from';
				import { Observable } from "rxjs";
				import * as exp from "constants";

				describe('Test list-proveedores.component', () => {
				    let component: ListProovedoresComponent;
				    const servicio = new ProovedoresService(null, null);

				    beforeEach(() => {
				        component = new ListProovedoresComponent(servicio);
				    });

				    it('Init: Debe cargar el listado',()=>{
				        const promotions = [
				            {
				                "id": 28,
				                "user": null,
				                "createAt": "2023-04-17T13:22:54",
				                "updateAt": null,
				                "deleted": true,
				                "name": "PromoComboPancho",
				                "description": "PromoComboPancho",
				                "codePromotion": "C",
				                "price": 450.0,
				                "products": [],
				                "productos": [],
				                "percent": null
				            },
				            {
				                "id": 29,
				                "user": null,
				                "createAt": "2023-04-17T15:25:20",
				                "updateAt": null,
				                "deleted": true,
				                "name": "PromoCarameloAlka",
				                "description": "PromoCarameloAlka",
				                "codePromotion": "C",
				                "price": 40.0,
				                "products": [],
				                "productos": [],
				                "percent": null
				            }				            
				        ];
				        spyOn( servicio, 'getProovedores').and.callFake( () => {
				            return Observable.from([ promotions ]);
				        })

				        component.ngOnInit();
				        expect( component.proovedor.length ).toBeGreaterThanOrEqual(9)
				    })

				});
		***********************************************************************************************************************************
		290. Confirmar que un método sea llamado*******************************************************************************************
		*****************************************************************************************************************Observable.empty()
		*****************************************************************************************************************toHaveBeenCalled()
			1.- Se modifica list-proveedores.component.spec.ts, se agrega metodo para probar que un metodo del servicio se llamo 
		    it('Se debe llamar al metodo del servicio',()=>{

		        const spy = spyOn( servicio, 'getProovedores').and.callFake( () => {
		            return Observable.empty();
		        })

		        component.ngOnInit();
		        expect( spy ).toHaveBeenCalled();
		    })
		***********************************************************************************************************************************
		291. Espías: returnValue***********************************************************************************************************
	    it('Debe agregar un medico',()=>{

	        const proveedor = {
	            "id": 28,
	            "user": null,
	            "createAt": "2023-04-17T13:22:54",
	            "updateAt": null,
	            "deleted": true,
	            "name": "PromoComboPancho",
	            "description": "PromoComboPancho",
	            "codePromotion": "C",
	            "price": 450.0,
	            "products": [],
	            "productos": [],
	            "percent": null,
	            "email": "",
	            "descripcion": ""
	        };
	        spyOn( servicio, 'getProovedores').and.returnValue( 
	            Observable.from([ proveedores ])
	        )

	        component.ngOnInit();
	        expect( component.proovedor.findIndex( i => i.id = proveedor.id)).toBeGreaterThanOrEqual(0)
	    })
	  ***********************************************************************************************************************************
	  292.- Probar errores en el observable**********************************************************************************************
	  ********************************************************************************************************************test switalert2
	    it('Si falla la eliminacion se debe mostrar mensaje',()=>{
	        const msgError = {
	            "error": {
	                "mensaje": "prueba"
	            }
	        }

	        const spy = spyOn( servicio, 'deleteProovedor').and.returnValue( 
	            throwError(msgError)
	        )

	        component.eliminarProovedor(1);
	        expect( spy ).toHaveBeenCalledWith(1)
	    })

	    2.- Probando switalert2
	    	Referencia --> https://github-wiki-see.page/m/prmkishor/sweetAletUnitTesting/wiki/sweetAlert2-Angular-Unit-Testing
		    it('should show basic message', (done) => {
		        const msgError = {
		            "error": {
		                "mensaje": "Mensaje de error"
		            }
		        }
		        let title = "Error al Eliminar tipo de Producto."
		        const spy = spyOn( servicio, 'deleteProovedor').and.returnValue( 
		            throwError(msgError)
		        )
		        component.eliminarProovedor(1);
		        setTimeout(() => {
		          expect(Swal.isVisible()).toBeTruthy();
		          expect(Swal.getTitle().textContent).toEqual(title);
		          expect(Swal.getContent().textContent).toEqual(msgError.error.mensaje);
		          Swal.clickConfirm();
		          done();
		        });
		    });
	  *********************************************************************************************************************************** 
	  293.- Simular confirmaciones de usurario*******************************************************************************************
	  	it('Debe llamar al servidor para borrar item', () => {
	  		spyOn( window , 'confirm').and.returnValue(true);

	  		const spy = spyOn( servicio, 'borrarItem' ).and.returnValue( Observable.empty );

	  		componente.borrarItem(1);
	  		expect( spy ).toHaveBeenCalledWith(1);
	  	})

	  	it('NO Debe llamar al servidor para borrar item', () => {
	  		spyOn( window , 'confirm').and.returnValue(true);

	  		const spy = spyOn( servicio, 'borrarItem' ).and.returnValue( Observable.empty );

	  		componente.borrarItem(1);
	  		expect( spy ).not.toHaveBeenCalledWith(1);
	  	})
	  ***********************************************************************************************************************************
	*************************************************************************************************************************************
******************************************************* Fin Seccion ******************************************************************




*********************************************************Redux Ngrx******************************************************************
	Referencia:
		https://es.redux.js.org/


	Qué es Redux  --> https://www.arsys.es/blog/programacion/redux-datos-aplicaciones/
	Redux es un patrón de arquitectura de datos que concede manipular el estado de la aplicación de una forma predecible. Está creado 
	para disminuir el número de relaciones entre los componentes de la aplicación y conservar un flujo de datos asequible.

	Redux ofrece las siguientes ventajas:

		Arquitectura escalable de datos.
		Máximo control sobre el flujo de datos y el estado de la aplicación.
		Estado global e inmutable.
		
	Estas ventajas son favorables para las aplicaciones que tienen cambios constantes (aplicaciones medianas y grandes), puesto que 
	Redux ayuda a tener su complejidad bajo control.

	Desmenuzamos las claves de Redux
		Los principios fundamentales de Redux son los siguientes: el store como pilar de todo, el state es solamente de lectura y las 
		acciones (actions) y funciones puras (reducers) son para realizar los cambios, como detallamos a continuación :
			* Store como pilar. Todos escuchan el store. El único estado válido de la aplicación está dentro del mismo 
			y cuando los componentes necesiten conocer el estado, asistirán al store para rescatarlo. Los datos van de 
			manera unidireccional desde el store a los componentes.
			* El estado (state) es de solo lectura. El state en Redux es el único objeto Javascript, estructurado en 
			forma de árbol y que contiene los datos que se van a manipular en la aplicación, siendo estos solo de lectura.
			* Si se desea alterar el estado de la aplicación, se deben utilizar las acciones (actions), pues son el único 
			modo para modificar su estado. Se representan mediante un objeto JavaScript,  contienen (como poco) el tipo 
			de acción que se desea realizar y además, algunas necesitan describirse con parámetros.
			* Los cambios se realizan a través de funciones puras. Los reducers especifican cómo cambia el estado de 
			la aplicación en respuesta a las acciones enviadas al store. Recuerda que las acciones solo describen el 
			hecho de que sucedió algo, pero no describen de qué manera cambia el estado de la aplicación. Estos 
			reciben dos parámetros: la acción y el estado que tenía anteriormente. Con ellos, se podrá modificar 
			el estado y lo devolverá con la nueva situación.
	***************************************Plugins	y	snippets	de	Visual	Studio	Code*************************************************
		• Angular	2	TypeScript	Emmet
		• Angular	5	Snippets	– TypeScript,	Html,	Angular	Material…
		• Angular	Language	Service
		• Angular	v5	Snippets
		• Angular2-inline
		• Bootstrap	4	&	Font	Awesome	snippets
		• HTML	CSS	Support
		• JavaScript	(ES6)	code	snippets
		• JS-CSS-HTML	Formatter
		• JSHint
		• Prettier	– Code	Formatter
		• Terminal
		• TSLint
		• TypeScript	Hero
		• TypeScript	Importer
	*************************************************************************************************************************************
	*************************************Sección 3: Implementar REDUX desde absoluto cero************************************************
		14. ***********************************************************************************************Inicio del proyecto - Redux básico
			1.- Creacion del proyecto 
				npm init 
				npm install -g ts-node
			2.- Se crea archivo app_1.ts y se modifica  
				console.log('hello word');
			3.- Se ejecuta el archivo creado 
				ts-node app_1
			4.- instalacion de typescript --> npm install typescript --save
		*************************************************************************************************************************************
		15. ********************************************************************************************************Acciones y Reducer básico
			1.- Se modifica el archivo app_1.ts 
				//Se crea interface 
			 	interface Action{
				    type: string;
				    payload?: any;
				}

				//Funcion reducer
				function reducer( state = 10, action: Action){
			    if( action.type === 'INCREMENTAR'){
			        return state+1;
			    }
			    return state;
				} 

				//Constante de tipo Action 
				const incrementodorAction: Action = {
				    type: 'INCREMENTAR'
				};

				// Se imprime lo retornado por la funcion reducer 
				console.log(reducer(10, incrementodorAction));
		*************************************************************************************************************************************
		16. ***************************************************************************************Configurar más acciones en nuestro reducer
			1.- Se modifica el archivo app_1.ts 
				//Interfaz
				interface Action{
				    type: string;
				    payload?: any;
				}

				//Funcion reducer
				function reducer( state = 10, action: Action){
				    switch( action.type ){
				        case 'INCREMENTAR':
				            return state +=1;
				        case 'DECREMENTAR':
				            return state -=1;
				        case 'MULTIPLICAR': 
				            return state * action.payload;
				        case 'DIVIDIR': 
				            return state / action.payload;
				        default: 
				            return state;
				    }
				} 

				// Ations
				const incrementodorAction: Action = {
				    type: 'INCREMENTAR'
				};
				const decrementadorAction: Action = {
				    type: 'DECREMENTAR'
				};
				const multiplicadorAction: Action = {
				    type: 'MULTIPLICAR',
				    payload: 2
				};
				const dividirAction: Action = {
				    type: 'DIVIDIR',
				    payload: 2
				};

				// Impresion de los reducer
				console.log(reducer(10, incrementodorAction));
				console.log(reducer(10, decrementadorAction));
				console.log(reducer(10, multiplicadorAction));
				console.log(reducer(10, dividirAction));
		*************************************************************************************************************************************
		17. **********************************************************************************************************Organizando el proyecto
			1.- Se crean folders para organizar el proyecto 
			 	contador 
			 	ngrx-fake 
			2.- Se crean los archivos necesarios 
				contador/contador.action.ts 
				ngrx-fake/ngrx.ts 
				app_2.ts 
			3.- Se modifica ngrx.ts 
					export interface Action{
			    type: string;
			    payload?: any;
				}
			4.- Se modifica contador.action.ts, se agregan las acciones   
				import { Action } from '../ngrx-fake/ngrx'

				export const incrementodorAction: Action = {
				    type: 'INCREMENTAR'
				};
				export const decrementadorAction: Action = {
				    type: 'DECREMENTAR'
				};
				export const multiplicadorAction: Action = {
				    type: 'MULTIPLICAR',
				    payload: 2
				};
				export const dividirAction: Action = {
				    type: 'DIVIDIR',
				    payload: 2
				};
				export const resetAction: Action = {
				    type: 'RESET'
				};
			5.- Se modifica app_2.ts
				import { Action } from '../recux_basico/ngrx-fake/ngrx'
				import { incrementodorAction, dividirAction, multiplicadorAction, decrementadorAction , resetAction} from '../recux_basico/contador/contador.action'

				function reducer( state = 10, action: Action){
				    switch( action.type ){
				        case 'INCREMENTAR':
				            return state +=1;
				        case 'DECREMENTAR':
				            return state -=1;
				        case 'MULTIPLICAR': 
				            return state * action.payload;
				        case 'DIVIDIR': 
				            return state / action.payload;
				        case 'RESET': 
				            return state = 0;
				        default: 
				            return state;
				    }
				} 

				console.log(reducer(10, incrementodorAction));
				console.log(reducer(10, decrementadorAction));
				console.log(reducer(10, multiplicadorAction));
				console.log(reducer(10, dividirAction));
				console.log(reducer(10, resetAction));
		*************************************************************************************************************************************
		18. ****************************************************************************************************************************Store
			1.- Se crea archivo contador/contador.reducer.ts, para separar el reducer del archivo ejecutable app_2.ts 
				import { Action } from '../ngrx-fake/ngrx'

				export function reducer( state = 10, action: Action){
				    switch( action.type ){
				        case 'INCREMENTAR':
				            return state +=1;
				        case 'DECREMENTAR':
				            return state -=1;
				        case 'MULTIPLICAR': 
				            return state * action.payload;
				        case 'DIVIDIR': 
				            return state / action.payload;
				        case 'RESET': 
				            return state = 0;
				        default: 
				            return state;
				    }
				} 
			2.- Se modifica app_2.ts, se elimina la funcion reducer y se importa del archivo creado en el paso anterior  
				import { reducer } from '../recux_basico/contador/contador.reducer' 
			3.- Se crea app_3.ts 
				//Imports 
				import { contadorReducer } from './contador/contador.reducer';
				import { Action, Reducer } from './ngrx-fake/ngrx';
				import { incrementodorAction } from './contador/contador.action'

				//Clase del store
				class Store<T> {
				    constructor( private reducer: Reducer<T>,
				                private state: T){ }
				    //Obtener el estado
				    getState(){
				        return this.state;
				    }
				    //Dispatcher del action
				    dispatch( action: Action ){
				        this.state = this.reducer( this.state, action)
				    }
				}

				//Se crea un obj del store
				const store = new Store( contadorReducer, 10 );

				//Se imprime el estado 
				console.log( store.getState() );

				//se despacha la accion
				store.dispatch( incrementodorAction );

				//Se imprime nuevamente el estado
				console.log( store.getState() );
			4.- Se modifica ngrx.ts, se agrega interface reducer  
				export interface Reducer<T> {
		    	( state: T, action: Action): T
				}
		*************************************************************************************************************************************
		19. **********************************************************************************************Suscribirse a los cambios del State
			1.- Se crea archivo app_4.ts   
			2.- Se instala la dependencia redux para javascript bainilla --> npm i redux 
				Nota: Se valia que este la dependencia en el package.json 
			3.- Se modifica el archivo app_4.ts, se hace subscribe del store de redux 
				 import { Store, createStore} from 'redux';
					import { contadorReducer } from './contador/contador.reducer';
					import { incrementodorAction } from './contador/contador.action';

					//Se crea  Store
					const store: Store = createStore( contadorReducer );

					//Se realiza subscribe
					store.subscribe( () => {
					    console.log('Subs: ', store.getState());
					})

					//Se ejecuta dispatch 
					store.dispatch( incrementodorAction );
					store.dispatch( incrementodorAction );
					store.dispatch( incrementodorAction );
					store.dispatch( incrementodorAction );
					store.dispatch( incrementodorAction );
					store.dispatch( incrementodorAction );  
		*************************************************************************************************************************************
		*************************************************************************************************************************************
		************************************************Sección 4: NGRX - Redux con Angular**************************************************
		23. **********************************************************************************Inicio de proyecto - Aplicación pura de Angular	
			1.- Creacion de la aplicacion 
				ng new redux-app
			2.- Se instala bootstrap 
				npm install bootstrap --save
			3.- se instala el audit, va a quitar errores de bulnerabilidad    
				npm audit fix
			4.- Se modifica el archivo angular.json, los styles, se agrega bootstrap.min.css   
		    "styles": [
		      "src/styles.css",
		      "node_modules/bootstrap/dist/css/bootstrap.min.css"
		    ]
		  5.- Se corre el aplicativo 
		  	ng serve -o 
		*************************************************************************************************************************************
		25. **********************************************************************************************Creación de componentes adicionales
			1.- Se crean componentes 
				ng g c contador/hijo --skip-tests
				ng g c contador/nieto --skip-tests
			2.- Se modifica app.component.html

				<div class="row text-center">
				  <div class="col">
				    <h1>Contador</h1>
				    <h2>{{ contador }}</h2>
				  </div>
				</div>

				<div class="row text-center">
				  <div class="col">
				    <button class="btn btn-primary" (click)="incrementar()">Incrementar</button>
				    <button class="btn btn-info" (click)="decrementar()">Decrementar</button>
				  </div>
				</div>
				<hr>
				<div class="row text-center">
				  <div class="col">
				    <app-hijo [contador]=contador (cambioContador)="contador= $event"></app-hijo>
				  </div>
				</div>
			3.- Se modifica app.compoent.ts 
				import { Component } from '@angular/core';

				@Component({
				  selector: 'app-root',
				  templateUrl: './app.component.html',
				  styleUrls: ['./app.component.css']
				})
				export class AppComponent {
				  contador: number;

				  constructor(){
				    this.contador = 10;
				  }

				  incrementar(){
				    this.contador ++;
				  }

				  decrementar(){
				    this.contador --;
				  }
				}
			4.- Se crea estilo para margen de los botones  
				button{
				  margin-right: 10px !important;
				}
			5.- Se modifica archivo style.css 
				button{
				  margin-right: 10px !important;
				}
			6.- Se modifica hijo.component.ts 
				import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

				@Component({
				  selector: 'app-hijo',
				  templateUrl: './hijo.component.html',
				  styleUrls: ['./hijo.component.css']
				})
				export class HijoComponent implements OnInit {

				  @Input() contador: number = 0;
				  @Output() cambioContador = new EventEmitter<number>();

				  constructor() { }

				  ngOnInit(): void {
				  }

				  multiplicador(){
				    this.contador  *= 2;
				    this.cambioContador.emit(this.contador);
				  }

				  divisor(){
				    this.contador  /= 2;
				    this.cambioContador.emit(this.contador);
				  }

				}
			7.- Se modifica hijo.component.html 
				<h3>Contador</h3>

				<h4>{{contador}}</h4>


				<div class="row text-center">
				  <div class="col">
				    <button class="btn btn-primary" (click)="multiplicador()">multiplicador</button>
				    <button class="btn btn-info" (click)="divisor()">divisor</button>
				  </div>
				</div>
		*************************************************************************************************************************************
		26. *******************************************************************************Integrar el componente nieto en nuestra aplicación
			1.- Se modifica nieto.component.html, se le agrega contador 
				<h3>Contador</h3>

				<h4>{{contador}}</h4>

				<div class="row text-center">
				  <div class="col">
				    <button class="btn btn-danger" (click)="reset()">reset</button>
				  </div>
				</div>
			2.- Se modifica nieto.component.ts, se le agrega input, output y metodo de reset del contador 
				@Input() contador: number = 0;
			  @Output() cambioContador = new EventEmitter<number>();

			  constructor() { }

			  ngOnInit(): void {
			  }

			  reset(){
			    this.contador = 0;
			    this.cambioContador.emit(this.contador);
			  }
			3.- Se modifica hijo.component.html, se le agrega el componente nieto   
				<hr>
				<div class="row text-center">
				  <div class="col">
				    <app-nieto [contador]=contador (cambioContador)="resetContador($event)"></app-nieto>
				  </div>
				</div>

			4.- Se modifica hijo.component.ts, se crea metodo para el reset al padre 
				  resetContador(numeroContador: number){
				    this.contador = numeroContador;
				    this.cambioContador.emit(this.contador)
				  }
		*************************************************************************************************************************************
		27. *********************************************************************************************Implementar NGRX en nuestro proyecto
			Referencia --> https://ngrx.io/docs
			1.- Se instala ngrx  --> npm install @ngrx/store --save
			2.- Se crea archivo contador/contador.actions.ts 
				import { createAction } from '@ngrx/store';

				//Se describen las acciones del contador para incrementar, disminuir
				export const incrementar = createAction('[Contador] Incrementar');
				export const decrementar = createAction('[Contador] decrementar');
			3.- Se crea archivo contador/contador.reducer.ts 
				import { Action } from "@ngrx/store";
				import { incrementar, decrementar } from './contador.actions'

				//Se crea funcion reductora para manejar cambios en el valor del 
				//contador según las acciones proporcionadas
				export function contadorReducer( state: number = 10, action: Action){
				  switch( action.type){
				    case incrementar.type:
				        return state + 1;
				    case decrementar.type:
				        return state + 1;
				    default:
				      return state;
				  }
				}
			4.- Se modifica app.module.ts, se agrega el store module
				//NgRx
				import { StoreModule } from '@ngrx/store';
				import { contadorReducer } from './contador/contador.reducer';

				Nota: El StoreModule.forRoot()método registra los proveedores 
				globales necesarios para acceder a Store en toda su aplicación.

			  imports: [
			    BrowserModule,
			    AppRoutingModule,
			    StoreModule.forRoot({contador: contadorReducer})
			  ],

			5.- Se modifica app.component.html 

				<div class="row text-center">
				  <div class="col">
				    <button class="btn btn-primary" (click)="incrementar()">Incrementar</button>
				    <button class="btn btn-info" (click)="decrementar()">Decrementar</button>
				  </div>
				</div>
				//Se comenta componente hijo
				<!--
				<hr>
				<div class="row text-center">
				  <div class="col">
				    <app-hijo [contador]=contador (cambioContador)="contador= $event"></app-hijo>
				  </div>
				</div>-->
			6.- Se modifica app.component.ts 
				//Se crea contrato
				interface AppStore{
				  contador: number;
				}

				@Component({
				  selector: 'app-root',
				  templateUrl: './app.component.html',
				  styleUrls: ['./app.component.css']
				})
				export class AppComponent {
					//Variable contador
				  contador!: number;

				  //Se subscribe al store y el resultado lo setea en la variable
				  constructor( private store: Store<AppStore>){
				    //this.contador = 10;
				    this.store.subscribe( state => {
				      console.log(state);
				      this.contador = state.contador;
				    })
				  }


				  incrementar(){
				    //this.contador ++;
				    //Se envia accion al store
				    this.store.dispatch( actions.incrementar() );
				  }

				  decrementar(){
				    //this.contador --;
				     //Se envia accion al store
				    this.store.dispatch( actions.decrementar() );
				  }
				}
		*************************************************************************************************************************************
		29. *******************************************************************************************************************Store DevTools 
			1.- Se instala store-devtools 
				npm install @ngrx/store-devtools --save
			2.- Se instala plugin de chrome y se recarga el navegador 
				--> https://chrome.google.com/webstore/detail/redux-devtools/lmhkpmbekcpmknklioeibfkpmmfibljd
			3.- Se modifica app.module.ts 
				import { StoreDevtoolsModule } from '@ngrx/store-devtools';

				  imports: [
				    BrowserModule,
				    AppRoutingModule,
				    StoreModule.forRoot({contador: contadorReducer}),
				    StoreDevtoolsModule.instrument({
				      maxAge: 25, // Retains last 25 states
				      logOnly: environment.production, // Restrict extension to log-only mode
				      autoPause: true, // Pauses recording actions and state changes when the extension window is not open
				    }),
				  ],
			4.- Se prueba en chrome inspeccionar --> redux 
		*************************************************************************************************************************************
		30. ****************************************************************************Escuchar cambios específicos de un elemento del State
		***********************************************************************************************************************Store.select()
			1.- Se modifica app.component.ts, se modifica constructos para utilizar el select del store, para permitir monitorear una 
			variable en particular  
				constructor( private store: Store<AppStore>){
			    //this.contador = 10;
			    //this.store.subscribe( state => {
			    //  console.log(state);
			    //  this.contador = state.contador;
			    //})
			    this.store.select('contador').subscribe( contador => {
			      console.log(contador);
			      this.contador = contador;
			    })
			  }
			2.- Probar y ver consola  
		*************************************************************************************************************************************
		31. *******************************************************************************************************************Create Reducer
			1.- Se modifica contador.reducer.ts, 

				//Se comenta en metodo convencional
				/*export function contadorReducer( state: number = 10, action: Action){
				  switch( action.type){
				    case incrementar.type:
				        return state + 1;
				    case decrementar.type:
				        return state - 1;
				    default:
				      return state;
				  }
				}*/
				//Variable del state inicial
				export const initialState = 200;

				//Funcion reducer con el cambio de estado
				const _contadorReducer = createReducer(
				  initialState,
				  on(incrementar, (state) => state + 1),
				  on(decrementar, (state) => state - 1),
				);


				export function contadorReducer(state: number | undefined, action: Action) {
				  return _contadorReducer(state, action);
				}
		*************************************************************************************************************************************
		32. ******************************************************************************************************Store en el componente hijo
			1.- Se modifica app.component.html, se le quitan atributos al componente hijo   
				<hr>
				<div class="row text-center">
				  <div class="col">
				    <!--<app-hijo [contador]=contador (cambioContador)="contador= $event"></app-hijo>-->
				    <app-hijo ></app-hijo>
				  </div>
				</div>
			2.- Se modifica omponente nieto para enfocarnos unicamente en el componente hijo  
				<!--<div class="row text-center">
				  <div class="col">
				    <app-nieto [contador]=contador (cambioContador)="resetContador($event)"></app-nieto>
				  </div>
				</div>-->
			3.- Se modifica hijo.component.ts
				export class HijoComponent implements OnInit {

					// Se comenta los input y los output 
				  /*@Input()*/ contador: number = 0;
				  //@Output() cambioContador = new EventEmitter<number>();

				  //Se realiza injeccion de dependencia del store
				  constructor( private store: Store<AppStore>) { }

				  //Consulto en el store la variable contador y realizo subscribe, cuando hay cambios 
				  //en la variable actualiza variable local
				  ngOnInit(): void {
				    this.store.select('contador').subscribe( contador => this.contador = contador);
				  }

				  //Se comentan los metodos
				  multiplicador(){
				    /* this.contador  *= 2;
				    this.cambioContador.emit(this.contador); */
				  }

				  divisor(){
				    /* this.contador  /= 2;
				    this.cambioContador.emit(this.contador); */
				  }

				  resetContador(numeroContador: number){
				    /* this.contador = numeroContador;
				    this.cambioContador.emit(this.contador) */
				  }

				}
			4.- Se crea en app --> app.reducer.ts  
				export interface AppStore{
				  contador: number;
				}
			5.- Se modifica app.component.ts, se elimina interface AppStore y se importa del archivo creado en el paso anterior 
				import { AppStore } from './app.reducers';
		*************************************************************************************************************************************
		33. ******************************************************************************************Creando acción de multiplicar y dividir
		********************************************************************************************************************************props
			1.- Se modifica contador.actions.ts, se agregan las acciones de multiplicar y dividir 
				export const multiplicar = createAction('[Contador] multiplicar', props<{numero: number}>());
				export const dividir = createAction('[Contador] dividir', props<{numero: number}>());
			2.- Se modifica contador.reducer.ts, se modifica el metodo contadorReducer(), se agregan dos casos al reducer 
				const _contadorReducer = createReducer(
				  initialState,
				  on(incrementar, (state) => state + 1),
				  on(decrementar, (state) => state - 1),
				  on(multiplicar, ( state, { numero }) => state * numero ),
				  on(dividir, ( state, { numero }) => state / numero ),
				);
			3.- Se modifica hijo.component.ts, se modifican los metodos de dividir y multiplicar, se llaman del dispach del store  
				  multiplicador(){
				    /* this.contador  *= 2;
				    this.cambioContador.emit(this.contador); */
				    this.store.dispatch( actions.multiplicar({numero: 2}) );
				  }

				  divisor(){
				    /* this.contador  /= 2;
				    this.cambioContador.emit(this.contador); */
				    this.store.dispatch( actions.dividir({numero: 2}));
				  } 
		*************************************************************************************************************************************
		34. ***********************************************************************************************************Acción Reset del nieto
		*************************************************************************************************************************initialState
		1.- Se modifica hijo.component.html, se descomenta el componente nieto y se le eliminan los atributos   
			<hr>
			<div class="row text-center">
			  <div class="col">
			    <!--<app-nieto [contador]=contador (cambioContador)="resetContador($event)"></app-nieto>-->
			    <app-nieto></app-nieto>
			  </div>
			</div>
		2.- Se modifica contador.actions.ts, se agrega accion reset   
			export const reset = createAction('[Contador] reset');   
		3.- Se modifica contador.reducer.ts, se le agrega case al contador reducer  
			const _contadorReducer = createReducer(
			  initialState,
			  on(incrementar, (state) => state + 1),
			  on(decrementar, (state) => state - 1),
			  on(multiplicar, ( state, { numero }) => state * numero ),
			  on(dividir, ( state, { numero }) => state / numero ),
			  on(reset, ( state ) => initialState ),
			);  

		4.- Se modifica hijo.component.ts, se elimina metodo resetContador(), ya que se trabaja desde el store   
		5.- Se modifica nieto.component.ts 
			@Component({
			  selector: 'app-nieto',
			  templateUrl: './nieto.component.html',
			  styleUrls: ['./nieto.component.css']
			})
			export class NietoComponent implements OnInit {

				//Se eliminan input y output 
			  /*@Input()*/ contador: number = 0;
			  /*@Output() cambioContador = new EventEmitter<number>();*/

			  //Se realiza injeccion del Store
			  constructor( private store: Store<AppStore>) { }

			  //Se realiza select de la variable contador en el store y se subcribe a su cambio
			  ngOnInit(): void {
			    this.store.select('contador').subscribe( contadore => this.contador = contadore );
			  }

			  //Se realiza el dispach del store indicando la accion a ejecutar 
			  reset(){
			    /*this.contador = 0;
			    this.cambioContador.emit(this.contador);*/
			    this.store.dispatch( actions.reset() );
			  }
			}
	*************************************************************************************************************************************
	*************************************************************************************************************************************
	********************************************Sección 5: NGRX - App - Añade Complejidad************************************************
		39. ****************************************************************************************************Inicio del proyecto - TodoApp
			1.- Se crea plaicacion --> ng new todoapp
			2.- Se instala --> npm install todomvc-app-css --save
			3.- Referencia repositorio para las plantillas --> https://github.com/Klerith/TODO-CSS-Template
			4.- Se copia el body del index del proyecto anterior en el app.component.html 
				<section class="todoapp">
				  <header class="header">
				    <h1>todos</h1>
				    <input class="new-todo" placeholder="What needs to be done?" autofocus>
				  </header>
				  <!-- This section should be hidden by default and shown when there are todos -->
				  <section class="main">
				    <input id="toggle-all" class="toggle-all" type="checkbox">
				    <label for="toggle-all">Mark all as complete</label>
				    <ul class="todo-list">
				      <!-- These are here just to show the structure of the list items -->
				      <!-- List items should get the class `editing` when editing and `completed` when marked as completed -->
				      <li class="completed">
				        <div class="view">
				          <input class="toggle" type="checkbox" checked>
				          <label>Taste JavaScript</label>
				          <button class="destroy"></button>
				        </div>
				        <input class="edit" value="Create a TodoMVC template">
				      </li>
				      <li>
				        <div class="view">
				          <input class="toggle" type="checkbox">
				          <label>Buy a unicorn</label>
				          <button class="destroy"></button>
				        </div>
				        <input class="edit" value="Rule the web">
				      </li>
				    </ul>
				  </section>
				  <!-- This footer should hidden by default and shown when there are todos -->
				  <footer class="footer">
				    <!-- This should be `0 items left` by default -->
				    <span class="todo-count"><strong>0</strong> item left</span>
				    <!-- Remove this if you don't implement routing -->
				    <ul class="filters">
				      <li>
				        <a class="selected" href="#/">All</a>
				      </li>
				      <li>
				        <a href="#/active">Active</a>
				      </li>
				      <li>
				        <a href="#/completed">Completed</a>
				      </li>
				    </ul>
				    <!-- Hidden if no completed items are left ↓ -->
				    <button class="clear-completed">Clear completed</button>
				  </footer>
				</section>
				<footer class="info">
				  <p>Double-click to edit a todo</p>
				  <!-- Remove the below line ↓ -->
				  <p>Template by <a href="http://sindresorhus.com">Sindre Sorhus</a></p>
				  <!-- Change this out with your name and url ↓ -->
				  <p>Created by <a href="http://todomvc.com">you</a></p>
				  <p>Part of <a href="http://todomvc.com">TodoMVC</a></p>
				</footer>
			5.- Se modifica angular.json, se agrega style   
		    "styles": [
		      "src/styles.css",
		      "node_modules/todomvc-app-css/index.css"
		    ],
		  6.- Se prueba la aplicacion --> ng serve -o   
		*************************************************************************************************************************************
		40. ************************************************************************Creando los componentes para nuestra aplicación de tareas
			1.- Se crea un modulo --> ng g m todos/todo --flat  
			2.- Se modifica app.module.ts, Se importa el modulo creado en el modulo principal 
				  imports: [
				    BrowserModule,
				    AppRoutingModule,
				    TodoModule
				  ],
			3.- Se crean los componentes necesarios 
				ng g c todos/todo-page  --skip-tests 
				ng g c todos/todo-list  --skip-tests 
				ng g c todos/todo-item  --skip-tests 
				ng g c todos/todo-footer --skip-tests   
				ng g c todos/todo-add --skip-tests
		*************************************************************************************************************************************
		41. ***********************************************************************************Segmentar el template en múltiples componentes
			Referencia Plantilla --> https://github.com/Klerith/TODO-CSS-Template/blob/master/index.html
			1.- Se modifica todo-page.component.html 
				<section class="todoapp">
				  <app-todo-add></app-todo-add>
				  <!-- This section should be hidden by default and shown when there are todos -->
				  <section class="main">
				    <input id="toggle-all" class="toggle-all" type="checkbox">
				    <label for="toggle-all">Mark all as complete</label>
				    <app-todo-list></app-todo-list>
				  </section>
				  <app-todo-footer></app-todo-footer>
				</section>
			2.- Se modifica app.component.html 
				<app-todo-page></app-todo-page>
				<app-footer></app-footer>
			3.- Se modifica todo-add.component.html 
				<header class="header">
				  <h1>todos</h1>
				  <input class="new-todo" placeholder="Que necesitas hacer?" autofocus>
				</header>
			4.- Se modifica todo-list.component.html 
				<ul class="todo-list">
				  <!-- List items should get the class `editing` when editing and `completed` when marked as completed -->
				  <app-todo-item></app-todo-item>
				</ul>
			5.- Se modifica todo-item.component.html 
				<li class="completed">
				  <div class="view">
				    <input class="toggle" type="checkbox" checked>
				    <label>Taste JavaScript</label>
				    <button class="destroy"></button>
				  </div>
				  <input class="edit" value="Create a TodoMVC template">
				</li>
			6.- Se modifica todo-footer.component.html 
				<!-- This footer should hidden by default and shown when there are todos -->
				<footer class="footer">
				  <!-- This should be `0 items left` by default -->
				  <span class="todo-count"><strong>0</strong> tareas pendientes</span>
				  <!-- Remove this if you dont implement routing -->
				  <ul class="filters">
				    <li>
				      <a class="selected" href="#/">Todos</a>
				    </li>
				    <li>
				      <a href="#/active">Activos</a>
				    </li>
				    <li>
				      <a href="#/completed">Completados</a>
				    </li>
				  </ul>
				  <!-- Hidden if no completed items are left ↓ -->
				  <button class="clear-completed">Borrar Completados</button>
				</footer>
			7.- Se modifica todo.module.ts, se exporta el componente TodoPageComponent   
				  exports:[
				    TodoPageComponent
				  ]
		*************************************************************************************************************************************	
		42. *******************************************************************************Instalar NgRx, modelo, acciones y reducer de Todos
			0.- Se instala ngrx --> npm install @ngrx/store --save
			1.- Se crea folder --> todos/models 
			2.- Se crea dentr de model todo.model.ts 
				export class Todo {
				  public id!: number;
				  public texto!: string;
				  public completado!: boolean;

				  constructor(texto: string){
				    this.texto = texto;
				    this.id = new Date().getTime();
				    this.completado = false;
				  }
				}
			3.- Se crea reducer todos/todo.reducer.ts   
				import { Action, createReducer, on } from '@ngrx/store';
				import { Todo } from './models/todo.model';
				import { crear } from './todo.actions';

				export const estadoInicial : Todo[] = [];

				const _todoReducer = createReducer(
				  estadoInicial,
				  on(crear, (state, {texto}) => [...state, new Todo(texto)]),
				);

				export function todoReducer(estado: Todo[] | undefined, action: Action) {
				  return _todoReducer(estado, action);
				};
			4.- Se crea los actions  --> todos/todo.actions.ts 
				import { Action, createReducer, on } from '@ngrx/store';
				import { Todo } from './models/todo.model';
				import { crear } from './todo.actions';

				export const estadoInicial : Todo[] = [];

				const _todoReducer = createReducer(
				  estadoInicial,
				  on(crear, (state, {texto}) => [...state, new Todo(texto)]),
				);

				export function todoReducer(estado: Todo[] | undefined, action: Action) {
				  return _todoReducer(estado, action);
				};
			5.- Se modifica app.module.ts, se importa ngrx 
				//ngrx
				import { StoreModule } from '@ngrx/store';
				import { todoReducer } from './todos/todo.reducer';

				1.- Se  añade en los imports de la aplicacion StoreModule.forRoot function con un objeto que contiene un contador y el 
				counterReducer que administra los estados. The StoreModule.forRoot() method registers the global providers needed to 
				access the Store throughout your application.
					StoreModule.forRoot({ todos: todoReducer })
		*************************************************************************************************************************************
		43. *************************************************************************************Definiendo el AppState de nuestra aplicación
			1.- Se instala devtools --> npm install @ngrx/store-devtools --save
			2.- Se modifica app.module.ts, se agrega instrumentacion de devtools a los imports  
				import { StoreDevtoolsModule } from '@ngrx/store-devtools';

				StoreDevtoolsModule.instrument({
		      maxAge: 25, // Retains last 25 states
		      logOnly: environment.production, // Restrict extension to log-only mode
		    }),
		  3.- Se modifica todo.reducer.ts, se agrega item al estado inicial y se valida en el explorador con el plugin Redux  
		  	export const estadoInicial : Todo[] = [ new Todo('Salvado por la campana')];
		  4.- Se crea app.reducer.ts
		  	import { Todo } from "./todos/models/todo.model";

				export interface AppState{
				  todos: Todo[]
				}
		*************************************************************************************************************************************
		44. ******************************************************************************************************************Agregar un TODO
			1.- Se importan los formularios reactivos en el app.module.ts y en todo.module.ts  
				import { ReactiveFormsModule} from '@angular/forms'

				  imports: [
				    BrowserModule,
				    AppRoutingModule,
				    TodoModule,
				    StoreModule.forRoot({ todos: todoReducer }),
				    StoreDevtoolsModule.instrument({
				      maxAge: 25, // Retains last 25 states
				      logOnly: environment.production, // Restrict extension to log-only mode
				    }),
				    ReactiveFormsModule
				  ],

			2.- Se modifica todo-add.component.ts  

				// Se crea tributo de tipo FormControl
				txtInput!: FormControl;

				//Se realiza injeccion de dependencia del Store
				constructor( private store: Store<AppState>) {
					// Se crea el Form Control
			    this.txtInput = new  FormControl( 'Hola', Validators.required);
			  }

			  agregar(){
			  	// Se valida si el input es valido 
			    if(this.txtInput.invalid) return ;
			    // Se dispara accion del store 
			    this.store.dispatch(actions.crear({ texto: this.txtInput.value }));
			    //Se hace reset del input 
			    this.txtInput.reset();
			  }
			 3.- Se modifica todo-add.component.html, se agrega atributo al input txtInput y se agrega evento enter  

			 	<header class="header">
				  <h1>todos</h1>
				  <input class="new-todo"
				    [formControl]="txtInput"
				    placeholder="Que necesitas hacer?"
				    (keyup.enter)="agregar()"
				    autofocus>
				</header>
		*************************************************************************************************************************************
		45. *************************************************************************************************Desplegar nuestra lista de TODOS
			1.- Se modifia todo.reducer.ts, se le agregan objetos al estado inicial   
					export const estadoInicial : Todo[] = [
		          new Todo('Salvar al mundo'),
		          new Todo('Vencer a thanos'),
		          new Todo('Comprar traje de Iroman'),
		          new Todo('Robar escudo del capitan America'),
		        ];
		  2.- Se modifica todo-list.component.ts
		  		//Se crea atribbuto 
		  	  todos: Todo[] = [];

		  	  //Se crea ID del store
				  constructor(private store: Store<AppState>) { }

				  ngOnInit(): void {
				  	// Se recupera del store la variable todos y se realiza sibscribe 
				    this.store.select('todos').subscribe( todos => this.todos = todos);
				  }

			3.- Se modifica todo-list.component.html, se le agrega *ngFor para recorrr la lista   
				<ul class="todo-list">
				  <!-- List items should get the class `editing` when editing and `completed` when marked as completed -->
				  <app-todo-item *ngFor="let todo of todos"></app-todo-item>
				</ul>  
		*************************************************************************************************************************************
		46. *********************************************************************************************************Controlar los TODO-Items
		*****************************************************************************************************************************@Input()
		***************************************************************************************************************************@ViewChild
		***************************************************************************************************************************ElementRef
		**************************************************************************************************************************FormControl
		************************************************************************************************************************nativeElement
		***************************************************************************************************************************(dblclick)
		*******************************************************************************************************************************(blur)
			1.- Se modifica todo-list.component.html, se agrega atributo todo
				<app-todo-item *ngFor="let todo of todos" [todo]="todo"></app-todo-item>
			2.- Se modifica todo-item.component.ts
				//Se crea input
				@Input() todo!: Todo;
				// Se creat atributo para el check reactivo
			  chkCompletado!: FormControl;
			  // Se crea atributo para el input reactivo 
			  txtInput!: FormControl;
			 	// Se crea atributo para el cambio de la clase css del elemento li
			  editando: boolean = false;
			  // Se crea atributo para manejar referencia al elemento del input 
			  @ViewChild('elemFisico') txtInputFisico!: ElementRef;
			  constructor() { }


			  ngOnInit(): void {
			  	// Se crean los elementos 
			    this.chkCompletado = new FormControl(this.todo.completado);
			    this.txtInput = new FormControl( this.todo.completado, Validators.required);
			  }

			  // metodo que agregao/quita la clase css editing y seleccion el txto del input 
			  editar(){
			    this.editando = true;
			    setTimeout(()=>{
			      this.txtInputFisico.nativeElement.select();
			    },1)

			  }

			  // Metodo que se dispara con evento blur y cambia la clase css del elemento  li
			  terminarEdicion(){
			    this.editando = false;
			  }
			3.- Se modifica todo-item.component.html  
				<li [class.completed]="todo.completado"
		    		[class.editing]="editando">
				  <div class="view">
				    <input class="toggle"
				    type="checkbox"
				    [formControl]="chkCompletado">
				    <label (dblclick)="editar()">{{todo.texto}}</label>
				    <button class="destroy"></button>
				  </div>
				  <input class="edit"
				    type="text"
				    [formControl]="txtInput"
				    (blur)="terminarEdicion()"
				    #elemFisico>
				</li>
		*************************************************************************************************************************************
		47. ************************************************************************************Toggle Action - Completar o desmarcar un TODO
		*************************************************************************************************************************valueChanges
		*****************************************************************************************************************************dispatch
			1.- Se modifico todo.model.ts, se cambio la manera de generar el id   
				  constructor(texto: string){
				    this.texto = texto;
				    this.id = Math.random();
				    this.completado = false;
				  }
			2.- Se modifica todo.actions.ts, se crea una nueva accion   
				export const togleCompletado = createAction(
				    '[TODO] Togle completado',
				    // Se pasa el id del obj todo 
				    props<{id: number}>()
				);
			3.- Se modifica todo.reducer.ts, se crea funcionalidad de la accion 
				const _todoReducer = createReducer(
				  estadoInicial,
				  on(crear, (state, {texto}) => [...state, new Todo(texto)]),
				  // Se crea nueva funcionalidad
				  on(togleCompletado, (state, {id}) => {
				  	// Se retorna un nuevo estado
				    return state.map( todo => {
				      if( todo.id === id ){
				      	// Se retorna un nuevo todo
				        return {
				          ...todo,
				          completado: !todo.completado
				        }
				      }else{
				      	// Se retorna el mismo obj todo 
				        return todo;
				      }
				    });
				  }),
				);
			4.- Se modifica todo-item.component.ts
				// Se importan todas las acciones y se le da un alias
				import * as actions from '../todo.actions'

				//Se realiza la ID del store
				constructor( private store: Store<AppState>) { }

				ngOnInit(): void {
			    this.chkCompletado = new FormControl(this.todo.completado);
			    this.txtInput = new FormControl( this.todo.completado, Validators.required);

			    // Se subscribe al cambio del atributo 
			    this.chkCompletado.valueChanges.subscribe( valor => {
			    	// Se realiza el dispach de la accion
			      this.store.dispatch( actions.togleCompletado({id: this.todo.id}))
			    })
			  }
		*************************************************************************************************************************************
		48. *******************************************************************************************************Editar una tarea por hacer
			1.- Se modifica todo.actions.ts, se crea una nueva accion  
				export const editar = createAction(
				    '[TODO] editar todo',
				    props<{id: number, texto: string}>()
				);
			2.- Se modifica todo.reducer.ts, se crea funcion de la accion creada 
				const _todoReducer = createReducer(
				  estadoInicial,
				  on(crear, (state, {texto}) => [...state, new Todo(texto)]),
				  on(togleCompletado, (state, {id}) => {
				    return state.map( todo => {
				      if( todo.id === id ){
				        return {
				          ...todo,
				          completado: !todo.completado
				        }
				      }else{
				          return todo;
				      }
				    });
				  }),
				  //Se crea nueva funcionalidad
				  on(editar, (state, {id, texto}) => {
				  	// retorna el nuevo estado
				    return state.map( todo => {
				      if( todo.id === id ){
				      	// Retorna un nuevo obj todo
				        return {
				          ...todo,
				          texto: texto
				        }
				      }else{
				      	// retorna el mismo obj todo 
				          return todo;
				      }
				    });
				  }),
				);
			3.- Se modifica todo-item.component.ts 
				  terminarEdicion(){
				    this.editando = false;
				    // Se valida el input si es igual y si es valido 
				    if( this.txtInput.invalid ) return;
				    if( this.txtInput.value === this.todo.texto ) return;
				    // Se realiza el dispatch e la accion
				    this.store.dispatch(
				      actions.editar({
				        id: this.todo.id,
				        texto: this.txtInput.value
				      })
				    );
				  }

				  editar(){
				    this.editando = true;
				    // Se setea valor de todoo en el input 
				    this.txtInput.setValue( this.todo.texto);
				    setTimeout(()=>{
				      this.txtInputFisico.nativeElement.select();
				    },1)
				  }
		*************************************************************************************************************************************
		49. *****************************************************************************************************Borrar una tarea de la lista
			1.- Se modifca todo.action.ts, se crea action eliminar   
				export const eliminar = createAction(
				    '[TODO] eliminar todo',
				    props<{id: number}>()
				);

			2.- Se modifica todo.reducer.ts, se agrega funcion al eliminar 
				const _todoReducer = createReducer(
				  estadoInicial,
				  on(crear, (state, {texto}) => [...state, new Todo(texto)]),
				  // Se agrega funcionalidad a la accion eliminar 
				  on(eliminar, (state, {id}) => state.filter( todo => todo.id !== id )),
				  on(togleCompletado, (state, {id}) => {
				    return state.map( todo => {
				      if( todo.id === id ){
				        return {
				          ...todo,
				          completado: !todo.completado
				        }
				      }else{
				          return todo;
				      }
				    });
				  }),
				  on(editar, (state, {id, texto}) => {
				    return state.map( todo => {
				      if( todo.id === id ){
				        return {
				          ...todo,
				          texto: texto
				        }
				      }else{
				          return todo;
				      }
				    });
				  }),
				);
			3.- Se modifica todo-item.component.ts, se agrega metodo eliminar   
				  eliminar(){
				    this.store.dispatch( actions.eliminar({ id: this.todo.id }))
				  }
			4.- Se modifica todo-item.component.html, se le agrega evento click al boton de eliminar   
				<button class="destroy" (click)="eliminar()"></button>
		*************************************************************************************************************************************
		50. ********************************************************************************************Toggle All - Marcar o Desmarcar Todos
			1.- Se modifica todo.action.ts, se agrega action   
				export const todoAll = createAction(
				    '[TODO] Togle all',
				    props<{completado: boolean}>()
				);
			2.- Se modifca todo.reducer.ts, se agrega funcionalidad   
				  on(todoAll, (state, {completado}) => state.map( todo => {
				  	// Se retorna un nuevo obj 
				    return {
				      ...todo,
				      completado: completado
				    }
				  })),
			3.- Se modifica todo-page.component.ts 
				//Se crea atributo 
			  completado: boolean = false;
			  // Se realiza ID del servicio
			  constructor( private store: Store<AppState> ) { }

			  // se crea metodo para el evento
			  togleAll(){
			    this.completado = !this.completado;
			    this.store.dispatch( actions.todoAll({ completado : this.completado}));
			  }
			4.- Se modifica todo-page.component.html, se crea evento para el checkbox  
				  <input id="toggle-all"
		        class="toggle-all"
		        type="checkbox"
		        (click)="togleAll()">
		*************************************************************************************************************************************
		51. **********************************************************************************************Creando un segundo reducer - Filtro
			1.- Se creo un folder en la raiz --> filtros 
			2.- Se crea un archivo de actions --> filtro.action.ts 
				//Importacion 
				import { createAction, props } from '@ngrx/store';

				// Se crea tipo pata el filtro
				export type filtrosValidos = 'todos' | 'completados' | 'pendientes'

				// Se crea la acion
				export const setFiltro = createAction('[Filtro] set Filtro', props<{ filtro: filtrosValidos }>());

			3.- Se creo un reducer --> filtro.reducer.ts 
				//Importacion
				import { Action, createReducer, on } from '@ngrx/store';
				import { filtrosValidos, setFiltro } from './filtro.actions';

				// Se añade estado inicial
				export const initialState: filtrosValidos  = 'todos';

				// Se crea la funcionalidad del action
				const _filtrosReducer = createReducer<filtrosValidos, Action>(
				  initialState,
				  on(setFiltro, (state, { filtro }) => filtro ),
				);


				export function filtrosReducer(state: any, action: Action) {
				  return _filtrosReducer(state, action);
				}
		*************************************************************************************************************************************
		52. ***************************************************************************************Combinando 2 o más reducers en el AppState
			1.- Se modifica app.reducer.ts, 
				//se agrega atributo de filtros a los state
				export interface AppState{
				  todos: Todo[],
				  filtros: filtrosValidos
				}

				// Se crea constante de tipo ActionReducerMap con los estados 
				export const appReducers: ActionReducerMap<AppState> = {
				  todos: todoReducer,
				  filtros: filtrosReducer
				}
			2.- Se modifica el app.module.ts, se modifica el metodo forRoot del StoreModule, se le agrega la constante creado con los reducers     
				    StoreModule.forRoot( appReducers ),
			Nota: Se prueba en el explorador con redux deben aparecer dos children de state 
		*************************************************************************************************************************************
		53. *****************************************************************************************Cambiar el filtro - únicamente el string
			1.- Se modifica todo-footer.component.ts 
				// Se crean atributos de la clase
			  filtroActual: actions.filtrosValidos= 'todos';
			  filtros: actions.filtrosValidos[] = ['todos','completados','pendientes']

			  // Se realiza ID del store
			  constructor( private store: Store<AppState>) { }


			  ngOnInit(): void {
			  	// Se busca en el store y se subscribe al dato filtros
			    this.store.select('filtros').subscribe( filtro => this.filtroActual = filtro);
			  }

			  // crealiza metodo para que dispare el action del reducer 
			  cambiarFiltro(filtro: actions.filtrosValidos){
			    this.store.dispatch(actions.setFiltro({ filtro: filtro }))
			  }
			2.- Se modifica todo-footer.component.html
				<!-- Remove this if you dont implement routing -->
				  <ul class="filters">
				    <li *ngFor="let filtro of filtros" (click)="cambiarFiltro(filtro)">
				      <a [class.selected]="filtro === filtroActual">{{filtro}}</a>
				    </li>
				    <!--<li>
				      <a href="#/active">Activos</a>
				    </li>
				    <li>
				      <a href="#/completed">Completados</a>
				    </li>-->
				  </ul>
				  <!-- Hidden if no completed items are left ↓ -->
				  <button class="clear-completed">Borrar Completados</button>
				</footer>
		*************************************************************************************************************************************
		54. ******************************************************************************************************Conteo de tareas pendientes
			1.- Se modifica todo-footer.component.ts 
				1.1- Se modifica metodo onInit para que se subscriba al store 
					 ngOnInit(): void {
				    //this.store.select('filtros').subscribe( filtro => this.filtroActual = filtro);
				    this.store.subscribe( state => {
				    	// Se setea al atributo el filtro actual 
				      this.filtroActual = state.filtros;
				      this.pendientes = state.todos.filter( todo => !todo.completado ).length;
				    })
				  }
				1.2.- Se crea atributo para el conteo de tareas pendientes 
					pendientes: number = 0;
			2.- Se modifica todo-footer.component.html, se le agrega atributo de conteo   
				  <span class="todo-count"><strong>{{pendientes}}</strong> tareas pendientes</span>
		*************************************************************************************************************************************
		55. ****************************************************************************************************Aplicar el filtro visualmente
			1.- Se crea pipe --> ng g pipe todos/filtro --skip-tests
			2.- Se modifica filtro.pipe.ts 
				@Pipe({
				  name: 'filtroTodo'
				})
				export class FiltroPipe implements PipeTransform {

				  transform(todos: Todo[], filtro: filtrosValidos): Todo[] {
				    switch(filtro){
				      case 'completados':
				        return todos.filter( todo => todo.completado );
				      case 'pendientes':
				        return todos.filter( todo => !todo.completado );
				      default:
				      return todos;
				    }
				  }
				}
			3.- Se modifica todo-list.component.ts 
				  todos: Todo[] = [];
				  //Se agrega atributo 
				  filtroActual!: filtrosValidos;

				  constructor(private store: Store<AppState>) { }

				  ngOnInit(): void {
				    //this.store.select('todos').subscribe( todos => this.todos = todos);
				    // Se subscribe al store 
				    this.store.subscribe( ({filtros, todos }) => {
				      this.filtroActual = filtros;
				      this.todos = todos;
				    })
				  }
			4.- Se modifica todo-list.component.html, se agrega pipe 
				<ul class="todo-list">
			    <app-todo-item *ngFor="let todo of todos | filtroTodo:filtroActual" [todo]="todo"></app-todo-item>
			  </ul>
		*************************************************************************************************************************************
		56. ************************************************************************************************Borrar todos los TODO completados
			1.- Se modifica todo.actions.ts, se crea accion  
				export const cleanTodos = createAction(
				    '[TODO] clean all'
				);
			2.- Se modifica todo.reducer.ts, se modifica createReducer() se agrega funcionalidad del action creado 
				  on(cleanTodos, (state) => state.filter( todo => !todo.completado )), 
			3.- Se modifica todo-footer.component.ts, se crea metodo clean 
				  clean(){
				    this.store.dispatch( cleanTodos() )
				  }
			4.- Se modifica todo-footer.component.html, se le agrega evento click al boton    
				<button class="clear-completed" (click)="clean()">Borrar Completados</button>
		*************************************************************************************************************************************
		57. *************************************************************************************Desplegar nuestra aplicación en GitHub Pages
			1.- Se crea repositorio en github 
			2.- Se ejecuta --> git init 
			3.- Se ejecuta --> git add .   
			4.- Se ejecuta commit --> git commit -m "Primer commit"
			5.- Se agrega remote --> git remote add pb https://github.com/paulboone/ticgit
			6.- Se crea branch main --> git branch main 
			7.- Se realiza el push --> git push 
			8.- Se construye el app de prod --> ng build --prod --base-href='/<nombre aplicativo en git>/'
				Nota: el nombre del aplicativo en it va a ser el nombre de tu repositorio
							se crea una carpeta dist, se entra en dist y se le modifica el nombre a la aplicacion por 
							docs, luego se pasa esta carpeta al raiz del proyecto y se suben los cambios 
			9.- Se despliega, se ingresa en el repositorio a la opcion setting, luego al apartados de pages, en source se indica la rama main   
				y luego la carpeta docs , se salva y se espera unos minutos al despliegue de la aplicacion, se colocara en color verde cuando 
				este ready, y se ejecuta el link indicado.   
		*************************************************************************************************************************************
	*************************************************************************************************************************************
	********************************************Sección 6: NGRX - App - Ingresos y egresos***********************************************
		62. *******************************************************************************************Inicio del proyecto - IngresoEgresoApp	
			1.- Se crea la aplicacion --> ng new ingresosEgresosApp
			2.- Se copian los assets del material del curso en los assets del app 
			3.- Se crea un componente --> ng g c auth/login --skip-tests
																--> ng g c auth/register --skip-tests
																--> ng g c dashboard --skip-tests 
																--> ng g c ingreso-egreso --skip-tests
																--> ng g c ingreso-egreso/estadistica --skip-tests
																--> ng g c ingreso-egreso/detalle --skip-tests
																--> ng g c shared/footer --skip-tests
																--> ng g c shared/navbar --skip-tests
																--> ng g c shared/sidebar --skip-tests
			4.- Se prueba la aplicacion --> ng serve -o 
		*************************************************************************************************************************************
		64. *****************************************************************************************************Diseño de nuestra aplicación
			1.- Se modifica index.html, se le agregan estilos a la aplicacion 
				 <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
		 
				 <link rel="stylesheet" href="assets/css/vendor.bundle.base.css">
				 <link rel="stylesheet" href="assets/css/vendor.bundle.addons.css">
				 <link rel="stylesheet" href="assets/css/style.css">
			2.- Se instala bootstrap --> npm i bootstrap --save
			3.- Se modifica angular.json, se le agrega estilos de bootstrap   
		    "styles": [
		      "src/styles.css",
		      "node_modules/bootstrap/dist/css/bootstrap.min.css"
		    ],
		  4.- Se modifica login.component.html, se copia del material facilitado 
				<div class="page-body-wrapper full-page-wrapper auth-page">
				  <div class="content-wrapper d-flex align-items-center auth auth-bg-1 theme-one">
				    <div class="row w-100">
				      <div class="col-lg-4 mx-auto">
				        <h2 class="text-center mb-4" style="color: white;">Ingresar</h2>
				        <div class="auto-form-wrapper">
				          <form action="#">
				            <div class="form-group">
				              <label class="label">Usuario</label>
				              <div class="input-group">
				                <input type="email" class="form-control" placeholder="Email">
				                <div class="input-group-append">
				                  <span class="input-group-text">
				                    <i class="fa fa-check-circle"></i>
				                  </span>
				                </div>
				              </div>
				            </div>
				            <div class="form-group">
				              <label class="label">Contraseña</label>
				              <div class="input-group">
				                <input type="password" class="form-control" placeholder="*********">
				                <div class="input-group-append">
				                  <span class="input-group-text">
				                    <i class="fa fa-check-circle"></i>
				                  </span>
				                </div>
				              </div>
				            </div>
				            <div class="form-group">
				              <button class="btn btn-primary submit-btn btn-block">Login</button>
				            </div>


				            <div class="text-block text-center my-3">
				              <span class="text-small font-weight-semibold">No estas registrado? </span>
				              <br>
				              <a href="" class="text-black text-small">Crear una cuenta</a>
				            </div>
				          </form>
				        </div>


				      </div>
				    </div>
				  </div>
				  <!-- content-wrapper ends -->
				</div>
			5.- Se modifica register.component.html, se copia del material facilitado
			  <div class="page-body-wrapper full-page-wrapper auth-page">
			    <div class="content-wrapper d-flex align-items-center auth register-bg-1 theme-one">
			      <div class="row w-100">
			        <div class="col-lg-4 mx-auto">
			          <h2 class="text-center mb-4">Registro</h2>
			          <div class="auto-form-wrapper">
			            <form action="#">
			              <div class="form-group">
			                <div class="input-group">
			                  <input type="email" class="form-control" placeholder="Correo">
			                  <div class="input-group-append">
			                    <span class="input-group-text">
			                      <i class="fa fa-check-circle"></i>
			                    </span>
			                  </div>
			                </div>
			              </div>
			              <div class="form-group">
			                <div class="input-group">
			                  <input type="password" class="form-control" placeholder="Password">
			                  <div class="input-group-append">
			                    <span class="input-group-text">
			                      <i class="fa fa-check-circle"></i>
			                    </span>
			                  </div>
			                </div>
			              </div>

			              <div class="form-group">
			                <button class="btn btn-primary submit-btn btn-block">Crear cuenta</button>
			              </div>
			              <div class="text-block text-center my-3">
			                <span class="text-small font-weight-semibold">ya tienes una cuenta?</span>
			                <br>
			                <a href="" class="text-black text-small">Login</a>
			              </div>
			            </form>
			          </div>
			        </div>
			      </div>
			    </div>
			    <!-- content-wrapper ends -->
			  </div>
			  <!-- page-body-wrapper ends -->
			6.- Se modifica footer.component.html, se copia del material facilitado
				<footer class="footer">
				  <div class="container-fluid clearfix">
				    <span class="text-muted d-block text-center text-sm-left d-sm-inline-block">Derechos reservados © 20XX
				      <a href="http://fernando-herrera.com" target="_blank">Fernando Herrera</a>.</span>
				    <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Creado a mano con
				      <i class="fa fa-heart text-danger"></i>
				    </span>
				  </div>
				</footer>
			7.- Se modifica navBar.component.html, se copia del material facilitado 
				<!-- partial:../../partials/_navbar.html -->
				<nav class="navbar default-layout col-sm-12 col-12 p-0 fixed-top d-flex flex-row">
				  <div class="text-center navbar-brand-wrapper d-flex align-items-top justify-content-center">
				    <a class="navbar-brand brand-logo">
				      <img src="assets/images/logo.svg" alt="logo" />
				    </a>
				    <a class="navbar-brand brand-logo-mini">
				      <img src="assets/images/logo-mini.svg" alt="logo" />
				    </a>
				  </div>

				  <div class="navbar-menu-wrapper d-flex align-items-center">

				    <ul class="navbar-nav navbar-nav-right">

				      <li class="nav-item d-none d-sm-inline-block">
				        <a class="nav-link" id="UserDropdown" href="#" data-toggle="dropdown" aria-expanded="false">
				          <span class="profile-text">Hello, Richard V.Welsh !</span>
				          <img class="img-xs rounded-circle" src="assets/images/faces/face1.jpg" alt="Profile image">
				        </a>
				      </li>
				    </ul>
				    <button class="navbar-toggler navbar-toggler-right d-sm-none align-self-center" type="button" data-toggle="offcanvas">
				      <span class="icon-menu"></span>
				    </button>
				  </div>
				</nav>
				<!-- partial -->
			8.- Se modifica sidebar.component.html, se copia del material facilitado
					<nav class="sidebar sidebar-offcanvas">
					  <ul class="nav">
					    <li class="nav-item nav-profile">
					      <div class="nav-link">

					        <div class="user-wrapper">
					          <div class="profile-image">
					            <img src="assets/images/faces/face1.jpg" alt="profile image">
					          </div>

					          <div class="text-wrapper">
					            <p class="profile-name">Cristobal Colón</p>
					            <div>
					              <small class="designation text-muted">Gerente</small>
					            </div>
					          </div>
					        </div>

					      </div>
					    </li>

					    <li class="nav-item">
					      <a class="nav-link">
					        <i class="menu-icon fa fa-tachometer-alt"></i>
					        <span class="menu-title">Dashboard</span>
					      </a>
					    </li>

					    <li class="nav-item">
					      <a class="nav-link">
					        <i class="menu-icon fa fa-clipboard-list"></i>
					        <span class="menu-title">Ingresos y Egresos</span>
					      </a>
					    </li>

					    <li class="nav-item">
					      <a class="nav-link">
					        <i class="menu-icon fa fa-table"></i>
					        <span class="menu-title">Detalle</span>
					      </a>
					    </li>

					    <li class="nav-item">
					      <a class="nav-link cursor">
					        <i class="menu-icon fa fa-sign-out-alt"></i>
					        <span class="menu-title">Cerrar sesión</span>
					      </a>
					    </li>

					  </ul>
					</nav>
			9.- Se modifica app.component.html, se agregan componentes
				<div class="container-scroller">
				  <!--<app-login></app-login>-->
				  <!--<app-register></app-register>-->
				  <app-navbar></app-navbar>
				  <div class="page-body-wrapper">
				    <app-sidebar></app-sidebar>
				    <div class="main-panel">
				      <div class="content-wrapper">
				        //paginas
				      </div>
				      <app-footer></app-footer>
				    </div>
				  </div>
				</div>
		*************************************************************************************************************************************
		65. ******************************************************************************************Creando las rutas de nuestra aplicación
			1.- Se modifica app-routing.module.ts, se crean las rutas principales  
				const routes: Routes = [
				  { path: 'login', component: LoginComponent },
				  { path: 'register', component: RegisterComponent },
				  { path: '', component: DashboardComponent },
				  { path: '**', redirectTo: '' }
				];
			2.- Se modifica app.component.ts, se le agrega router-outlet
				<div class="container-scroller">
				  <router-outlet></router-outlet>
				</div>
			3.- Se modifica login.component.html, se le agrega atributo routerLink al link de crear cuenta para que nos dirija al 
			componente register
				<a routerLink="/register" class="text-black text-small">Crear una cuenta</a>
			4.- Se modifica register.component.html, se le agrega atributo routerLink al link de crear cuenta para que nos dirija al 
			componente login  
				<a routerLink="/login" class="text-black text-small">Login</a>
		*************************************************************************************************************************************
		66. ***********************************************************************************Configurando rutas hijas de nuestra aplicación
			1.- Se crea archivo dentro de dashboard --> dashboard.routes.ts 
				export const dashboarRoutes: Routes = [
				  { path: '', component: EstadisticaComponent },
				  { path: 'ingreso-egreso', component: IngresoEgresoComponent },
				  { path: 'detalle', component: DetalleComponent }
				];
			2.- Se modifica dashboard.component.html  
				<app-navbar></app-navbar>
				<div class="page-body-wrapper">
				  <app-sidebar></app-sidebar>
				  <div class="main-panel">
				    <div class="content-wrapper">
				      <router-outlet></router-outlet>
				    </div>
				    <app-footer></app-footer>
				  </div>
				</div>
			3.- Se modifica app-routing.module.ts, se le agrega children a la ruta de dashboard 
				const routes: Routes = [
				  { path: 'login', component: LoginComponent },
				  { path: 'register', component: RegisterComponent },
				  { path: '', component: DashboardComponent, children: dashboarRoutes },
				  { path: '**', redirectTo: '' }
				];
			4.- Se prueba, se debe mostrar en el dashboard los componentes hijos 
				http://localhost:51452/detalle
				http://localhost:51452/estadistica
		*************************************************************************************************************************************
		67. ********************************************************************************************************Diseño de las rutas hijas
			1.- Se modifica ingreso-egreso.component.html, se copia del material facilitado
				<div class="row">
				  <div class="col-12">
				      <div class="card">
				        <div class="card-body">
				          <h4 class="card-title">Agregar Ingreso / Egreso</h4>
				          <p class="card-description">
				            Especifique el monto y el tipo
				          </p>

				          <form>

				            <div class="form-group">
				              <label>Descripción</label>
				              <input type="text"
				                     class="form-control"
				                     placeholder="Descripción"
				                     name="descripcion">
				            </div>

				            <div class="form-group">
				                <label>Monto</label>
				                <input type="number"
				                       class="form-control"
				                       placeholder="Monto"
				                       name="monto">
				                  <p>
				                    Debe de ser un monto positivo
				                  </p>
				              </div>

				            <div class="form-group">
				                <label>Tipo</label>
				                <br>
				                <button type="button" class="btn btn-primary btn-block">Ingreso</button>
				                <!-- <button type="button" class="btn btn-warning btn-block">Egreso</button> -->
				            </div>

				            <hr>
				            <button type="button" class="btn btn-success mr-2">
				              <i class="fa fa-save"></i>
				              Agregar
				            </button>

				            <!--
				              <button disabled class="btn btn-success mr-2">
				                <i class="fa fa-spin fa-sync"></i>
				                Espere por favor...
				              </button>
				            -->

				            <button type="reset" class="btn btn-light">Cancelar</button>

				          </form>
				        </div>
				      </div>
				    </div>
				</div>
			2.- Se modifica estadistica.component.html, se copia del material facilitado
				<div class="row">
				  <div class="col-6">
				      <div class="card card-statistics">
				        <div class="card-body">

				          <div class="clearfix">
				            <div class="float-left">
				              <i class="fa fa-money-bill-alt text-success icon-lg"></i>
				            </div>
				            <div class="float-right">
				              <p class="mb-0 text-right">Ingresos</p>
				              <div class="fluid-container">
				                <h3 class="font-weight-medium text-right mb-0">$65,650</h3>
				              </div>
				            </div>
				          </div>
				          <p class="text-muted mt-3 mb-0">
				            <i class="mdi mdi-alert-octagon mr-1" aria-hidden="true"></i> 65% lower growth
				          </p>
				        </div>
				      </div>
				    </div>

				    <div class="col-6">
				        <div class="card card-statistics">
				          <div class="card-body">

				            <div class="clearfix">
				              <div class="float-left">
				                <i class="fa fa-shipping-fast text-danger icon-lg"></i>
				              </div>
				              <div class="float-right">
				                <p class="mb-0 text-right">Ingresos</p>
				                <div class="fluid-container">
				                  <h3 class="font-weight-medium text-right mb-0">$65,650</h3>
				                </div>
				              </div>
				            </div>
				            <p class="text-muted mt-3 mb-0">
				              <i class="mdi mdi-alert-octagon mr-1" aria-hidden="true"></i> 65% lower growth
				            </p>
				          </div>
				        </div>
				      </div>
				</div>
			3.- Se modifica detalle.component.html, se copia del material facilitado
				<div class="row">
				  <div class="col-12">
				      <div class="card">
				        <div class="card-body">
				          <h4 class="card-title">Detalle de ingreso egreso</h4>

				          <table class="table">
				            <thead>
				              <tr>
				                <th>Descripción</th>
				                <th>Monto</th>
				                <th>Tipo</th>
				                <th></th>
				              </tr>
				            </thead>

				            <tbody>
				              <tr>
				                <td>Descripción</td>
				                <td>Monto</td>
				                <td>Tipo</td>
				                <td>acciones</td>
				              </tr>
				            </tbody>

				          </table>


				        </div>
				      </div>
				  </div>
				</div>
			4.- Se modifica sidebar.component.html, se le agregan routerling a las opciones del sidebar  
		    <li class="nav-item">
		      <a class="nav-link" routerLink="/">
		        <i class="menu-icon fa fa-tachometer-alt"></i>
		        <span class="menu-title">Dashboard</span>
		      </a>
		    </li>

		    <li class="nav-item">
		      <a class="nav-link" routerLink="/ingreso-egreso">
		        <i class="menu-icon fa fa-clipboard-list"></i>
		        <span class="menu-title">Ingresos y Egresos</span>
		      </a>
		    </li>

		    <li class="nav-item">
		      <a class="nav-link" routerLink="/detalle">
		        <i class="menu-icon fa fa-table"></i>
		        <span class="menu-title">Detalle</span>
		      </a>
		    </li>

		    <li class="nav-item">
		      <a class="nav-link cursor" routerLink="/login">
		        <i class="menu-icon fa fa-sign-out-alt"></i>
		        <span class="menu-title">Cerrar sesión</span>
		      </a>
		    </li>
		*************************************************************************************************************************************
		68. ***************************************************************************************Realizando un backup de nuestra aplicación
			1.- Se crea repositorio en github 
			2.- Dentro del proyecto se ejecuta 
				git init 
				git add .  
				git commit -m "Primer commit"
				git remote add origin https://github.com/dirielfran/ingresos-egresosApp.git
				git branch -M main
				git push -u origin main
				git tag -a v1.0.0 -m "Primera version"
				git push --tags
			3.- Revizar repositorio, deben estar los cambios y el tag creado 
			4.- Despliegue de aplicacion en github pages 
				4.1.- Se modifica index.html 
					<base href="/ingresos-egresosApp/">
				4.2.- Se construye la aplicacion --> ng build --prod 
					Nota: se genero error al crar el index la siguiente correccion utilice, en el angular.json cambie la configuracion para 
					produccion 	
						"configurations": {
		            "production": {
		              "optimization": {
		                "scripts": true,
		                "styles": {
		                  "minify": true,
		                  "inlineCritical": false
		                },
		                "fonts": true
		              },
		    4.3.- Se busca la app en dist y se le cambia el nombre a docs, luego se lleva  a la raiz 
		    4.4.- Se va a github  --> repositorio --> setting --> pages 
		    	4.4.1.- Se escoge la rama, luego el folder docs, se salva, se espera unos minutos a que este en verde y desplegado
		*************************************************************************************************************************************
	*************************************************************************************************************************************
	************************************Sección 7: NGRX - App - Authenticacion, Login y Registro*****************************************
		72. *************************************************************************************************************Pantalla de registro
		**************************************************************************************************************************FormControl
		******************************************************************************************************************ReactiveFormsModule 
		**************************************************************************************************************************FormBuilder 
		****************************************************************************************************************************FormGroup 
		*****************************************************************************************************************************ngSubmit 
		***************************************************************************************************************************Validators
		*****************************************************************************************Validacion por campo del formulario reactivo
			1.- Se modifica el app.module.ts, se importa formularioreactivoModule 
				  imports: [
				    BrowserModule,
				    AppRoutingModule,
				    ReactiveFormsModule
				  ],
			2.- Se modifica register.component.ts
				export class RegisterComponent implements OnInit {
					//Atributo para el formulario reactivo
				  registerForm!: FormGroup;

				  // ID del formBuilder
				  constructor( private fb: FormBuilder) { }

				  ngOnInit(): void {
				  	// Se inicializa formulario reactivo
				    this.registerForm = this.fb.group({
				      nombre: ['', Validators.required],
				      correo: ['', [Validators.required, Validators.email] ],
				      password: ['', [Validators.required]]
				    })
				  }

				  //Se crea metodo para crear usuario
				  crearUsuario(){
				    console.log(this.registerForm);
				    console.log(this.registerForm.valid);
				    console.log(this.registerForm.value);
				  }
				}
			3.- Se modifica register.component.ts
				3.1.- Se modifica el elemto form, se indica el formGroup y se le agrega evento submit  
					<form [formGroup]="registerForm" (ngSubmit)="crearUsuario()">
				3.2.- Se crea input para numbre de usuario, se le agrega formControlName, se valida check si el campo es valido 
					<div class="form-group">
		        <div class="input-group">
		          <input type="text"
		                  class="form-control"
		                  // Se agrega al formulario reactivo
		                  formControlName="nombre"
		                  placeholder="Nombre">
		          <div class="input-group-append">
		            <span class="input-group-text">
		            	//Se valida si el campo es valido 
		              <i *ngIf="registerForm.get('nombre')?.valid" class="fa fa-check-circle"></i>
		            </span>
		          </div>
		        </div>
		      </div>
		    3.3.- Se modifican los input de correo y password, para que validen si son validos y se agregan al formulario reactivo 
		      <div class="form-group">
		        <div class="input-group">
		          <input type="email"
		                  class="form-control"
		                  formControlName="correo"
		                  placeholder="Correo">
		          <div class="input-group-append">
		            <span class="input-group-text">
		              <i *ngIf="registerForm.get('correo')?.valid" class="fa fa-check-circle"></i>
		            </span>
		          </div>
		        </div>
		      </div>

		      <div class="form-group">
		        <div class="input-group">
		          <input type="password"
		                  class="form-control"
		                  formControlName="password"
		                  placeholder="Password">
		          <div class="input-group-append">
		            <span class="input-group-text">
		              <i *ngIf="registerForm.get('password')?.valid" class="fa fa-check-circle"></i>
		            </span>
		          </div>
		        </div>
		      </div>
		    3.4.- Se modifica boton para que sea de tipo submit y que se active con el formulario valido 
		              <div class="form-group">
		                <button type="submit"
		                          [disabled]="registerForm.invalid"
		                          class="btn btn-primary submit-btn btn-block">Crear cuenta</button>
		              </div>    	
		*************************************************************************************************************************************
		73. ***************************************************************************************************Preparar nuestra base de datos
			1.- Se registra aplicacion en firebace.google.com 
			2.- Una vez registrada la aplicacion --> Authentication --> Metodos de acceso --> correo electronico y contraseña y se habilita
			3.- Se crea base de datos --> Base de datos -->comenzar en modo bloqueo -->  crear
				3.1.- Se le cambian las reglas -->  allow read, write  --> publicar 
		*************************************************************************************************************************************
		74. ***************************************************************AngularFire - Comunicar nuestra aplicación de Angular con Firebase
			Referencia --> https://github.com/angular/angularfire 
									--> https://console.firebase.google.com/u/0/
			1.- Agregamos AngularFire para la authenticacion de usuarios 
				ng add @angular/fire
			2.- Se agrega una aplicacion web al firebase 
			3.- Se accde a la documentacion de angularFire --> https://github.com/angular/angularfire  
			4.- Se modifica enviroment.ts, con los datos que otorga firebase al registrar la aplicacion web   
				export const environment = {
				  production: false,
				  firebase: {
				    apiKey: "AIzaSyDbzn2-4Q8-Ecct0ueOGc7xtDMHA32Fi50",
				    authDomain: "ingresos-egresosapp.firebaseapp.com",
				    projectId: "ingresos-egresosapp",
				    storageBucket: "ingresos-egresosapp.appspot.com",
				    messagingSenderId: "781786713066",
				    appId: "1:781786713066:web:f8cc3fc290ff55ca39c093",
				    measurementId: "G-59B8G0TMN9"
				  }
				};
			5.- Se modifica enviroment.prod.ts, con los datos que otorga firebase al registrar la aplicacion web   
				export const environment = {
				  production: true,
				  firebase: {
				    apiKey: "AIzaSyDbzn2-4Q8-Ecct0ueOGc7xtDMHA32Fi50",
				    authDomain: "ingresos-egresosapp.firebaseapp.com",
				    projectId: "ingresos-egresosapp",
				    storageBucket: "ingresos-egresosapp.appspot.com",
				    messagingSenderId: "781786713066",
				    appId: "1:781786713066:web:f8cc3fc290ff55ca39c093",
				    measurementId: "G-59B8G0TMN9"
				  }
				};
			6.- Se modifica app.module.ts, se agregan importaciones de firebase   
				import { AngularFireModule } from '@angular/fire/compat';
				import { environment } from 'src/environments/environment';
				import { AngularFirestoreModule } from '@angular/fire/compat/firestore';
				import { AngularFireAuthModule } from '@angular/fire/compat/auth';

			  imports: [
			    BrowserModule,
			    AppRoutingModule,
			    ReactiveFormsModule,
			    AngularFireModule.initializeApp(environment.firebase),
			    AngularFirestoreModule,
			    AngularFireAuthModule
			  ],
			7.- Se prueba la aplicacion que no genere ningun error  
		*************************************************************************************************************************************
		75. *****************************************************************************************************Crear nuestro primer usuario
			1.- Se crea un servicio --> ng g s services/auth --skip-tests
				1.1.- Se realiza injeccion de dependencia de AngularFireAuth
					constructor( public auth: AngularFireAuth ) { }
				1.2.- Se crea metodo crearUsuario
				  crearUsuario(nombre: string, email: string, password: string){
				    //console.log( {nombre, email, password})
				    return this.auth.createUserWithEmailAndPassword(email,password);
				  }
			2.- Se modifica register.component.ts 
				2.1.- Se modifica el constructor, se agrega la ID del obj Router 
					constructor( private fb: FormBuilder,
		                private authService: AuthService,
		                private router: Router ) { }
				2.2.- Se modifica metodo crearUsuario() 
				  crearUsuario(){
				  	//Se valida si el formulario es valido
				    if(this.registerForm.invalid) return;
				    // Se crean constantes con los valores del formulario
				    const { nombre, correo, password } = this.registerForm.value;
				    //Se llama al servicio para crear el usuario
				    this.authService.crearUsuario( nombre, correo, password)
				    	// succes
				      .then( credenciales => {
				        console.log( credenciales );
				        //navegamos al dashboard
				        this.router.navigateByUrl('/')
				        //error
				      }).catch( error => {
				        console.error(error);
				      });

				  }			
			3.- Se prueba el aplicativo, componente register, se debe validar que los usuarios se esten creado en firebase --> authenticacion 
		*************************************************************************************************************************************
		76. ******************************************************************************************************Login de nuestra aplicación
		**************************************************************************************************************************FormControl
		******************************************************************************************************************ReactiveFormsModule 
		**************************************************************************************************************************FormBuilder 
		****************************************************************************************************************************FormGroup 
		*****************************************************************************************************************************ngSubmit 
		***************************************************************************************************************************Validators
			1.- Se modifica auth.service.ts, se crea metodo de loginUsuario()  
				  loginUsuario(email: string, password: string ){
				    return this.auth.signInWithEmailAndPassword(email, password);
				  }
			2.- Se moifica login.component.ts 
				2.1.- Se realiza ID de router y clase de servicio authService  
					constructor( private fb: FormBuilder,
		                private authService: AuthService,
		                private route: Router ) { }
				2.2.- Se crea atributo para el loginForm 
					loginForm!: FormGroup;
				2.3.- Se modifica el metodo onInit(), se inicializa el formulario 
					  ngOnInit(): void {
					    this.loginForm = this.fb.group({
					      email: ['', [Validators.required, Validators.email]],
					      password: ['', [Validators.required]],
					    })
					  }
				2.4.- Se crea metodo loginUsuario()  
					  loginUsuario(){
					  	//Se valida el formulario
					    if( this.loginForm.invalid ) return;
					    // Se obtienen los parametros del formulario
					    const { email, password } = this.loginForm.value;
					    //Se llamam al servicio para la validacion del login
					    this.authService.loginUsuario(email, password)
					    	// Succes
					      .then( auth => {
					      	//Se navega al dashboard
					        console.log( auth);
					        this.route.navigateByUrl('/');
					        // error
					      }).catch( error => {
					        console.error( error );
					      })
					  }
			3.- Se modifica login.component.html 
				3.1.- Se modifica el form, se agrega formulario reactivo y el submit 
					<form [formGroup]="loginForm" (submit)="loginUsuario()">
				3.2.- Se agrega input al formulario reactivo y se valida si es valido 
					<div class="input-group">
		        <input type="email"
		                class="form-control"
		                placeholder="Email"
		                formControlName="email">
		        <div class="input-group-append">
		          <span class="input-group-text">
		            <i *ngIf="loginForm.get('email')?.valid" class="fa fa-check-circle"></i>
		          </span>
		        </div>
		      </div>

		      <div class="input-group">
		        <input type="password"
		                class="form-control"
		                formControlName="password"
		                placeholder="*********">
		        <div class="input-group-append">
		          <span class="input-group-text">
		            <i *ngIf="loginForm.get('password')?.valid" class="fa fa-check-circle"></i>
		          </span>
		        </div>
		      </div>
		     3.3.- Se modifica el boton, se le agrega tipo y se valida el formulario para deshabilitarlo 0
		        <button class="btn btn-primary submit-btn btn-block"
				      type="submit"
				      [disabled]="loginForm.invalid"
				      >Login</button>

			4.- Se valida login, entrando con un usuario creado y con los datos erroneos
		*************************************************************************************************************************************
		77. ****************************************************************************************************SweetAlert 2 para los errores
			Referencia --> https://sweetalert2.github.io/#download
			1.- Se instala switalert2 --> npm install sweetalert2
			2.- Se modifica register.component.ts, se le agrega swit alert en caso de error en la creacion de usuario, se lea agrega louding 
				crearUsuario(){
			    if(this.registerForm.invalid) return;
			    //Se agrega louding
			    Swal.fire({
			      title: 'Espere por favor!',
			      didOpen: () => {
			        Swal.showLoading()
			      }
			    })

			    const { nombre, correo, password } = this.registerForm.value;
			    this.authService.crearUsuario( nombre, correo, password)
			      .then( credenciales => {
			        console.log( credenciales );
			        //Se cierra loudin en caso de success
			        Swal.close();
			        this.router.navigateByUrl('/')
			      }).catch( error => {
			      	//Se abre switAlert de error
			        Swal.fire({
			          icon: 'error',
			          title: 'Oops...',
			          text: error.message
			        });
			      });
		  	}
		 	3.- Se modifica login.component.ts, se le agrega swit alert en caso de error en la creacion de usuario, se lea agrega louding 
		 		loginUsuario(){
			    if( this.loginForm.invalid ) return;

			    Swal.fire({
			      title: 'Espere por favor!',
			      didOpen: () => {
			        Swal.showLoading()
			      }
			    })

			    const { email, password } = this.loginForm.value;
			    this.authService.loginUsuario(email, password)
			      .then( auth => {
			        console.log( auth);
			        Swal.close();
			        this.route.navigateByUrl('/');
			      }).catch( error => {
			        Swal.fire({
			          icon: 'error',
			          title: 'Oops...',
			          text: error.message
			        });
			      })
			  }
		*************************************************************************************************************************************
		78. ***********************************************************************************************Logout - Cerrar sesión del usuario
			1.- Se modifica auth.service.ts, se crea metodo logout() 
				  logout(){
				    return this.auth.signOut();
				  }
			2.- Se modifica sidebar.component.ts 
				2.1.- Se realiza ID de router y authService 
					  constructor( private authService: AuthService, private router: Router) { }
				2.2.- Se crea metodo logout() 
					  logout(){
					    this.authService.logout().then( () => {
					      this.router.navigateByUrl('/login');
					    });
					  }
			3.- Se modifica sidebar.component.html, se crea evento de logout() 
				<li class="nav-item">
		      <a (click)="logout()"   class="nav-link cursor" routerLink="/login">
		        <i class="menu-icon fa fa-sign-out-alt"></i>
		        <span class="menu-title">Cerrar sesión</span>
		      </a>
		    </li>
		  4.- Se modifica style.css, se modifica estilo de puntero 
		  	.cursor{
				  cursor: pointer;
				}
		*************************************************************************************************************************************
		79. *******************************************************************************Obtener información del usuario activo en Firebase
			1.- Se modifica auth.service.ts, se crea metodo para saber el state del usuario 
			  initAuthLister(){
			    this.auth.authState.subscribe( (user) =>{
			      console.log(user);
			      console.log(user?.uid);
			      console.log(user?.email);
			    })
			  }
			2.- Se modifica app.component.ts, se crea constructor, se le realiza ID del servicio, se llama al servicio
				  constructor( private authServices: AuthService ){
				    this.authServices.initAuthLister();
				  }
		*************************************************************************************************************************************
		80. **********************************************************************************Guard - Proteger el dashboard y sus rutas hijas
			1.- Se crea guard --> ng g g services/auth --skip-tests
				//Se realiza ID de router y el servicio
			  constructor(private authService: AuthService,
		            private router: Router){  }

			  canActivate(): Observable<boolean> {
			  	//Se valida si esta loguado el usuario
			    return this.authService.isAuth().pipe(
			    	// Si es false lo ririge al login
			      tap( estado => {
			        if(!estado) this.router.navigateByUrl('/login');
			      })
			    );
			  }
			2.- Se modifica auth.service.ts, se crea metodo para validar si usuario esta autenticado 
				  isAuth(){
				    return this.auth.authState.pipe(
				      map( user => user != null )
				    );
				  }
			3.- Se modifica app-routing.module.ts, se agrega guar a ruta dashboard 
				const routes: Routes = [
				  { path: 'login', component: LoginComponent },
				  { path: 'register', component: RegisterComponent },
				  {
				    path: '',
				    component: DashboardComponent,
				    children: dashboarRoutes,
				    canActivate: [ AuthGuard ]
				  },
				  { path: '**', redirectTo: '' }
				];
			4.- Se prueba la aplicacion intentando ingresar al dash sin estar logueado
		*************************************************************************************************************************************
		81. ******************************************************************************* Crear documento de base de datos por cada usuario
			1.- Se crea clase modelo models/Usuario.ts
				 export  class Usuario{
				  constructor(
				    public uid: string,
				    public nombre: string,
				    public email: string,
				  ){}
				}
			2.- Se modifica auth.service.ts
				//Se crea ID de obj AngularFirestore
				  constructor( public auth: AngularFireAuth,
		                private firestore: AngularFirestore ) { }
			3.- Se modifica el metodo crearUsuario(), para que al crear el usuario me cree un documento de BD 
				  crearUsuario(nombre: string, email: string, password: string){
				    //console.log( {nombre, email, password})
				    return this.auth.createUserWithEmailAndPassword(email,password)
				        .then( ({ user }) => {
				          const newUser = new Usuario( user!.uid, nombre, user?.email! );
				          return this.firestore.doc(`${user?.uid}/usuario`).set({...newUser});
				        });
				  }
		*************************************************************************************************************************************
		82. *********************************************************************************************Realizar backup a GitHub - Sección 7
			1.- git add .  
			2.- git commit -m "Seccion 7 - Auth"
			3.- git push 
			4.- git tag -a v1.1.0 -m "Sccion 7 terminada" 
			5.- git push --tags 
		*************************************************************************************************************************************
	*************************************************************************************************************************************
	************************Sección 8: Redux en nuestra aplicación de ingreso y egreso - Fin de la autenticación*************************
		87. **********************************************************************************************Interacciones de usuario - UI State
		******************************************************************************************************************Creacion de snipets
			Nota; los snipets nos sirven para autocompletar 
			1.- Snippets para acelerar mi trabajo de creación de acciones, reducers y action reducer maps
				https://gist.github.com/Klerith/e6cb4f984cf1bc9c1f2e99b5f5509b7f
				1.1.- Se copian los snipets del repositorio de git 
				1.2.- En el framework e visualcode  
					View --> CommandPalette --> Preferences User Configure snipets(enter) --> typescript.json  
				1.3.- Se pega el contenido en el typescript.json 
					{
						// Place your snippets for typescript here. Each snippet is defined under a snippet name and has a prefix, body and 
						// description. The prefix is what is used to trigger the snippet and the body will be expanded and inserted. Possible variables are:
						// $1, $2 for tab stops, $0 for the final cursor position, and ${1:label}, ${2:another} for placeholders. Placeholders with the 
						// same ids are connected.
						// Example:
						// "Print to console": {
						// 	"prefix": "log",
						// 	"body": [
						// 		"console.log('$1');",
						// 		"$2"
						// 	],
						// 	"description": "Log output to console"
						// }
						"Print to console": {
							"prefix": "clog",
							"body": [
								"console.log $1",
							],
							"description": "Log output to console"
						},

						"NgRx Actions": {
							"prefix": "ngrx-actions",
							"body": [
								"import { createAction } from '@ngrx/store';",
								"",
								"export const ${1:increment} = createAction('[${2:Counter Component}] ${1:Increment}');",
							],
							"description": "Crea las acciones de NgRx"
						},

						"NgRx Reducer": {
							"prefix": "ngrx-reducer",
							"body": [
								"import { createReducer, on } from '@ngrx/store';",
								"import { $2 } from './${1:counter}.actions';",
								"",
								"export interface State {",
								"    ${3:key}: ${4:String}; ",
								"}",
								"",
								"export const initialState: State = {",
								"   ${3:key}: ${5:'hola'},",
								"}",
								"",
								"const _${6:counter}Reducer = createReducer(initialState,",
								"",
								"    on(${2:increment}, state => ({ ...state, ${3:key}: ${5:'hola'}})),",
								"",
								");",
								"",
								"export function ${6:counter}Reducer(state, action) {",
								"    return _${6:counter}Reducer(state, action);",
								"}"
							],
							"description": "Crea el reducer básico"
						},


						"NgRx App Reducer": {
							"prefix": "ngrx-app-reducers",
							"body": [
								"import { ActionReducerMap } from '@ngrx/store';",
								"import * as $1 from './$2.reducer';",
								"",
								"",
								"export interface AppState {",
								"   ${3:todos}: $4",
								"}",
								"",
								"",
								"",
								"export const appReducers: ActionReducerMap<AppState> = {",
								"   ${3:todos}: $5,",
								"}",
							],
							"description": "Crea el AppReducer principal"
						},

					} 
				1.4.- Se instala  ngrx --> npm install @ngrx/store --save
			2.- Se crea actions, reducer en shared 	--> ui.actions.ts 
																							--> ui.reducer.ts 
					2.1.- Se modifica ui.actions.ts, se crean las acciones 
						import { createAction } from '@ngrx/store';

						export const isLoading = createAction('[UI Component] is Loading');
						export const stopLoading = createAction('[UI Component] Stop Loading');
					2.2.- Se modifica ui.reducers.ts 
						import { createReducer, on } from '@ngrx/store';
						import { isLoading, stopLoading } from './ui.actions';

						export interface State {
						    isLoading: boolean;
						}

						export const initialState: State = {
						   isLoading: false,
						}

						const _counterReducer = createReducer(initialState,

						    on(isLoading, state => ({ ...state, isLoading: true})),
						    on(stopLoading, state => ({ ...state, isLoading: false})),

						);

						export function counterReducer(state: any, action: any) {
						    return _counterReducer(state, action);
						}
		*************************************************************************************************************************************
		88. ********************************************************************************Estado global de la aplicación - ActionReducerMap
			1.- Se crea reducer global de la aplicacion en app --> app.reducer.ts 
				Nota: Si se utiliza el snipets --> ngrx-app-reducer
				
				import { ActionReducerMap } from '@ngrx/store';
				import * as ui from './shared/ui.reducer';

				export interface AppState {
				   ui: ui.State
				}
				export const appReducers: ActionReducerMap<AppState> = {
				   ui: ui.uiReducer ,
				}
			2.- Se modifica el app.module.ts 
				2.1.- Se Agregue la StoreModule.forRoot función en la imports matriz de su AppModule 
					Nota: con el objetivo que contenga el count y el counterReducer que administra el estado del contador. 
					El StoreModule.forRoot() método registra los proveedores globales necesarios para acceder a Store en toda su aplicación.
					2.1.1.- Se importa 
						// ngrx
						import { StoreModule } from '@ngrx/store';
						import { appReducers } from './app.reducer';
					2.1.2.- Se agrega a la importacion
						StoreModule.forRoot(appReducers),
				2.2.- Se agrega store-devtools 
					2.2.1.- Se instala --> npm install @ngrx/store-devtools --save  
					2.2.2.- Se realiza el import 
						import { StoreDevtoolsModule } from '@ngrx/store-devtools';
					2.2.3.- Se agrega a los imports 
						StoreDevtoolsModule.instrument({
				      maxAge: 25, // Retains last 25 states
				      logOnly: environment.production, // Restrict extension to log-only mode
				      autoPause: true, // Pauses recording actions and state changes when the extension window is not open
				    }),
			3.- Se prueba la aplicacion 
				3.1.- Se corre --> ng serve -o 
				3.2.- Se inspecciona en el navegador el redux 
		*************************************************************************************************************************************
		89. ******************************************************************************************Dispatch - Activar y Desactivar loading
			1.- Se modifica login.comnent.ts
				1.1.- Se crea ID del reducer global
					constructor( private fb: FormBuilder,
		                private authService: AuthService,
		                private store: Store<AppState>,
		                private route: Router ) { }
				1.2.- Se crean variables para que almacene la suscripcion y uno que almacene el estado del loading 
					cargando: boolean = false;
		  		uiSubscription!: Subscription;
		  	1.3.- Se modifica metodo onInit(), se realiza subscribe del reducer y se modifica la variable del loading con la accion del 
		  	reducer
		  		  ngOnInit(): void {
					    this.loginForm = this.fb.group({
					      email: ['', [Validators.required, Validators.email]],
					      password: ['', [Validators.required]],
					    });

					    this.uiSubscription =this.store.select('ui').subscribe( ui => {
					                            this.cargando = ui.isLoading;
					                            console.log('Pasando por el store');
					                          })
					  }
				1.4.- Se modifica el metodo de login(), se agregan los dispatch del store 
					  loginUsuario(){
					    if( this.loginForm.invalid ) return;

					    //Se agrega dispatch de la accion isLoading()
					    this.store.dispatch( ui.isLoading() )

					    /*Swal.fire({
					      title: 'Espere por favor!',
					      didOpen: () => {
					        Swal.showLoading()
					      }
					    })*/

					    const { email, password } = this.loginForm.value;
					    this.authService.loginUsuario(email, password)
					      .then( auth => {
					        console.log( auth);
					        //Swal.close();
					        //Se agrega dispatch de la accion stopLoading()
					        this.store.dispatch( ui.stopLoading() )
					        this.route.navigateByUrl('/');
					      }).catch( error => {
					      	//Se agrega dispatch de la accion stopLoading()
					        this.store.dispatch( ui.stopLoading() );
					        Swal.fire({
					          icon: 'error',
					          title: 'Oops...',
					          text: error.message
					        });
					      })
					  }
				1.5.- Se implementa el metodo onDestroy(), se realiza la limpieza del subscribe
					1.5.1.- Se realiza la implementacion de la clase 
						export class LoginComponent implements OnInit, OnDestroy {
					4.5.2.- Se implementa el metodo onDestroy(), se realiza limpieza delsubscribe 
						  ngOnDestroy(): void {
						    this.uiSubscription.unsubscribe();
						  }
			2.- Se modifica register.comnent.ts
				1.1.- Se crea ID del reducer global
					  constructor( private fb: FormBuilder,
					                private authService: AuthService,
					                private store: Store<AppState>,
					                private router: Router ) { }
				1.2.- Se crean variables para que almacene la suscripcion y uno que almacene el estado del loading 
					cargando: boolean = false;
		  		uiSubscription!: Subscription;
		  	1.3.- Se modifica metodo onInit(), se realiza subscribe del reducer y se modifica la variable del loading con la accion del 
		  	reducer
					  ngOnInit(): void {
					    this.registerForm = this.fb.group({
					      nombre: ['', Validators.required],
					      correo: ['', [Validators.required, Validators.email] ],
					      password: ['', [Validators.required]]
					    })

					    this.uiSubscription = this.store.select('ui').subscribe( ui => {
					      this.cargando = ui.isLoading;
					    });
					  }
				1.4.- Se modifica el metodo de crearUsuario(), se agregan los dispatch del store 
					  crearUsuario(){
					    if(this.registerForm.invalid) return;

					    /*Swal.fire({
					      title: 'Espere por favor!',
					      didOpen: () => {
					        Swal.showLoading()
					      }
					    })*/
					    // dispatch que acciona el el metodo isLoading()
					    this.store.dispatch( ui.isLoading() );

					    const { nombre, correo, password } = this.registerForm.value;
					    this.authService.crearUsuario( nombre, correo, password)
					      .then( credenciales => {
					        console.log( credenciales );
					        //Swal.close();
					        // dispatch que acciona el el metodo stopLoading()
					        this.store.dispatch( ui.stopLoading() );
					        this.router.navigateByUrl('/')
					      }).catch( error => {
					      	// dispatch que acciona el el metodo stopLoading()
					        this.store.dispatch( ui.stopLoading() );
					        Swal.fire({
					          icon: 'error',
					          title: 'Oops...',
					          text: error.message
					        });
					      });
					  }
				1.5.- Se implementa el metodo onDestroy(), se realiza la limpieza del subscribe
					1.5.1.- Se realiza la implementacion de la clase 
						export class RegisterComponent implements OnInit, OnDestroy {
					4.5.2.- Se implementa el metodo onDestroy(), se realiza limpieza delsubscribe 
						  ngOnDestroy(): void {
						    this.uiSubscription.unsubscribe();
						  }
		*************************************************************************************************************************************
		90. ****************************************************************************************************Utilizar el store - isLoading
			1.- Se modifica login.component.html, se agrega boton con icono de sync y se valida con la variable cargando
				1.1.- Se modifica boton submit con validacion de la variable cargando 
		        <button class="btn btn-primary submit-btn btn-block"
		          type="submit"
		          [disabled]="loginForm.invalid"
		          *ngIf="!cargando">Login</button>
		    1.2.- Se crea boton de tipo button para mostrar cuando se este cargando 
			    <button class="btn btn-primary submit-btn btn-block"
			            type="button"
			            [disabled]="true"
			            *ngIf="cargando">
			        <i class="fa fa-spin fa-sync"></i>
			        Espere...
			    </button>
			2.- Se modifica register.component.html, se agrega boton con icono de sync y se valida con la variable cargando
				1.1.- Se modifica boton submit con validacion de la variable cargando 
		        <button type="submit"
		                  [disabled]="registerForm.invalid"
		                  class="btn btn-primary submit-btn btn-block"
		                  *ngIf="!cargando">
		            Crear cuenta
		        </button>
		    1.2.- Se crea boton de tipo button para mostrar cuando se este cargando 
		        <button type="button"
		                  [disabled]="true"
		                  class="btn btn-primary submit-btn btn-block"
		                  *ngIf="cargando">
		            <i class="fa fa-spin fa-sync"></i>
		            Crear cuenta
		        </button>
		*************************************************************************************************************************************
		91. ***********************************************************************************************************Auth Actions y Reducer
			1.- Se crea auth.actions.ts dentro de auth  
				import { createAction, props } from '@ngrx/store';
				import { Usuario } from '../models/usuario.model';

				export const setUser = createAction(
				  '[Auth] setUser',
				  props< { user: Usuario }>()
				);

				export const unSetUser = createAction('[Auth] unSetUser');
			2.- Se crea auth.reducer.ts dentro de auth  
				import { createReducer, on } from '@ngrx/store';
				import { Usuario } from '../models/usuario.model';
				import { setUser, unSetUser } from './auth.actions';

				export interface State {
				    user: Usuario | null ;
				}

				export const initialState: State = {
				   user: null,
				}

				const _authReducer = createReducer(initialState,
				    // Se utiliza desestructuracion para romper la referencia en memoria
				    on(setUser, (state, { user }) => ({ ...state, user: {...user}})),
				    on(unSetUser, (state) => ({ ...state, user: null})),
				    );

				export function authReducer(state: any, action: any) {
				    return _authReducer(state, action);
				}
			3.- Se modifica app.reducer.ts, se agrega reducer auth al reducer global 
				import { ActionReducerMap } from '@ngrx/store';
				import * as ui from './shared/ui.reducer';
				import * as auth from './auth/auth.reducer';

				export interface AppState {
				   ui: ui.State,
				   user: auth.State
				}
				export const appReducers: ActionReducerMap<AppState> = {
				   ui: ui.uiReducer ,
				   user: auth.authReducer
				}
			4.- Se debe probar la aplicacion y debe aparecer en redux de chrome otro estado asociado 
			5.- Se modifica auth.service.ts, se agrega el dispatch de la accion  
				5.1.- Se crea ID del store 
					  constructor( public auth: AngularFireAuth,
		                private firestore: AngularFirestore,
		                private store: Store<AppState> ) { }
				5.2.- Se modifica el metodo initAuthServece(), se valida si viene el user y si viene se ejecuta por el distpatch el la 
				accion setUser
						  initAuthLister(){
						    this.auth.authState.subscribe( (user) =>{
						        console.log( user );
						        if(user){
						          //Existe
						          this.firestore.doc(`${user.uid}/usuario`).valueChanges().subscribe( firestoreUser => {
						            console.log( firestoreUser );
						            const tempUser = new Usuario('abc', 'borrame', 'abc@getMaxListeners.com');
						            this.store.dispatch( authActions.setUser({ user: tempUser }))
						          })
						        }else{
						          console.log('llamar unset del user')
						        }
						    })
						  }
		*************************************************************************************************************************************
		92. *************************************************************************************Almacenar en el Store el usuario de Firebase
			1.- Se modifica usuario.model.ts, se crea metodo para construccion de Usuaro con las caracteristicas que vienen de firebase 
				export  class Usuario{
				  static fronFirebase({email, uid, nombre}:{ email:any, uid: any, nombre: any}){
				    return new Usuario( uid, nombre, email );
				  }
				  constructor(
				    public uid: string,
				    public nombre: string,
				    public email: string,
				  ){}
				}
			2.- Se modifica auth.service.ts, se modifica el metodo initAuthLister, se recupera el usuario de firebse y se le pasa al store, 
			se realiza unsubribe y se nullea el user al cerrar session 
				  initAuthLister(){
				    this.auth.authState.subscribe( (user) =>{
				        console.log( user );
				        if(user){
				          //Existe
				          console.log('si tengo usuario')
				          this.userSubscripcion = this.firestore.doc(`${user.uid}/usuario`).valueChanges().subscribe( (firestoreUser:any) => {
				            const user = Usuario.fronFirebase( firestoreUser )
				            this.store.dispatch( authActions.setUser({ user: user }))
				          })
				        }else{
				          this.userSubscripcion.unsubscribe();
				          this.store.dispatch( authActions.unSetUser() );
				        }
				    })
				  }
		*************************************************************************************************************************************
		93. **********************************************************************************************Backup de la aplicación - Sección 8
			1.- git add .
			2.- git commit -m FinSeccion8
			3.- git push 
			4.- git tag -a v1.2.0 -m FinSeccion8
			5.- git push tags
		*************************************************************************************************************************************
	*************************************************************************************************************************************
	*********************************************Sección 9: Modulos de ingresos y egresos************************************************
		97. ****************************************************************************************************Modelo de un ingreso y egreso
			1.- Se crea clase modelo --> models/ingreso-egreso.model.ts  
				export class IngresoEgreso{
				  constructor(
				    public descripcion: string,
				    public monto: number,
				    public tipo: string,
				    public uid: string
				  ){}
				}
		*************************************************************************************************************************************
		98. ****************************************************************************************Pantalla para capturar ingresos y egresos
		**************************************************************************************************************************FormBuilder
		****************************************************************************************************************************FormGroup
		***************************************************************************************************************************Validators
		*****************************************************************************************************************************Disabled
		**********************************************************************************************************************FormControlName
		**********************************************************************************************************************FormControlName
			1.- Se modifica ingreso-egreso.component.ts, se agrega formulario reactivo  
				1.1.- Se crean ID de obj FormBuilder
					constructor( private fb: FormBuilder) { }
				1.2.- Se crea variables, se crea variable de tipo FormGroup y otra de tipo string para el tipo 
					  ingresoForm!: FormGroup;
		  			tipo: string = 'ingreso';
		  	1.3.- Se modifica el metodo ngOnInit(), se inicializa el formulario reactivo 
		  		  ngOnInit(): void {
					    this.ingresoForm = this.fb.group({
					      descripcion: ['', Validators.required],
					      monto: ['', [Validators.required, Validators.min(0)]]
					    })
					  }
				1.4.- Se creametodo guardar() 
					  guardar(){
					    if(this.ingresoForm.invalid){ return;}
					    console.log( this.ingresoForm.value);
					    console.log(this.tipo)
					  }
			2.- Se modifica ingreso-egreso.component.html, se vincula al formulario reactivo 
				2.1.- Se modifica el form 
					<form [formGroup]="ingresoForm" (submit)="guardar()">
				2.2.- Se vinculan los inputs con FormControlName
					      <div class="form-group">
		              <label>Descripción</label>
		              <input type="text"
		                     class="form-control"
		                     placeholder="Descripción"
		                     formControlName="descripcion"
		                     name="descripcion">
		            </div>

		            <div class="form-group">
		                <label>Monto</label>
		                <input type="number"
		                       class="form-control"
		                       placeholder="Monto"
		                       formControlName="monto"
		                       name="monto">
		                  <p>
		                    Debe de ser un monto positivo
		                  </p>
		             </div>
		    2.3.- Se modifica boton de agregar, se deshabilita si el formulario es invalido, se le modifica el tipo a submit 
		    	      <button type="submit" class="btn btn-success mr-2" [disabled]="ingresoForm.invalid">
		              <i class="fa fa-save"></i>
		              Agregar
		            </button>
		    2.4.- Se modifica boton de ingreso y egreso, se le agrega evento click y ngIf para que se muestre segun el tipo   
		    	     	<div class="form-group">
		                <label>Tipo</label>
		                <br>
		                <button *ngIf="tipo === 'ingreso'" (click)="tipo = 'egreso'" type="button" class="btn btn-primary btn-block">Ingreso</button>
		                <button *ngIf="tipo === 'egreso'" (click)="tipo = 'ingreso'" type="button" class="btn btn-warning btn-block">Egreso</button>
		            </div>
		*************************************************************************************************************************************
		99. **********************************************************************************************************Servicio Ingreso-Egreso
			1.- Se crea servicio --> services/ingreso-egreso.service.ts 
					// Se realiza ID 
					 constructor( private firestore: AngularFirestore, private authService: AuthService ) { }

					 //Se crea metodo para el ingreso y egreso
				  crearIngresoEgreso( ingresoEgreso: IngresoEgreso){
				  	//Se recupera la uid del user por medio del authServices
				    const uid = this.authService.user.uid;
				    //Se agrega a la colleccion items el ingresoEgreso
				    return this.firestore.doc(`${uid}/ingreso-egreso`)
				      .collection('items')
				      .add({ ...ingresoEgreso });
				  }
			2.- Se modifica auth.service.ts, se agrega variable privada user con su get para poder recuperarla en otra instancia 
					//Se crea variable
				  private _user!: Usuario | null;

				  //Se crea metodo de acceso
				  get user(){
				    return {... this._user};
				  }

				  2.1.- Se modifica metodo initAuthLister(), se setea variable user 
				  	  initAuthLister(){
						    this.auth.authState.subscribe( (user) =>{
						        if(user){
						          //Existe
						          this.userSubscripcion = this.firestore.doc(`${user.uid}/usuario`).valueChanges().subscribe( (firestoreUser:any) => {
						            const user = Usuario.fronFirebase( firestoreUser );
						            //Se setea el usuario
						            this._user = user;
						            this.store.dispatch( authActions.setUser({ user: user }));
						          })
						        }else{
						        	//Se setea null el usuario
						          this._user = null;
						          this.userSubscripcion.unsubscribe();
						          this.store.dispatch( authActions.unSetUser() );
						        }
						    })
						  }
			3.- Se modifica ingreso-egreso.model.ts, se comenta uid 
					export class IngresoEgreso{
					  constructor(
					    public descripcion: string,
					    public monto: number,
					    public tipo: string,
					    //public uid: string
					  ){}
					}
			4.- Se modifica ingreso-egreso.component.ts
				4.1.- Se cre ID del servicio de ingresoEgreso 
						constructor( private fb: FormBuilder,
		                private ingresoEgresoService: IngresoEgresoService) { }
				4.2.- Se modifica metodo guardar(), se llama al metodo de creacion de ingresoegreso en el servicio
					  guardar(){
					    if(this.ingresoForm.invalid){ return;}
					    // Se recuperan variables por desestructuracion de los valores del formulario
					    const { descripcion, monto } = this.ingresoForm.value;
					    // Se crea obj de tipo IngresoEgreso
					    const ingresoEgreso = new IngresoEgreso( descripcion, monto, this.tipo);
					    //Se invoca al metodo de creacion de ingresoEgreso
					    this.ingresoEgresoService.crearIngresoEgreso(ingresoEgreso)
					    .then( () => {
					      this.ingresoForm.reset();
					      Swal.fire('Resgistro creado', descripcion, 'success');
					    })
					    .catch( err => Swal.fire('Error', err.message, 'error'));
					  }
		*************************************************************************************************************************************
		100. *******************************************************************************************************Optimizaciones de loading
		**********************************************************************************************************************Store<AppState>
		*************************************************************************************************************************Subscription
			1.- Se modifica ingreso-egreso.component.html, Se agrega a boton de agregar icono de loading  
				        <button type="submit" class="btn btn-success mr-2" [disabled]="ingresoForm.invalid">
		              <i class="fa fa-save"></i>
		              <i class="fa fa-spin fa-sync" *ngIf="cargando"></i>
		              Agregar
		            </button>
		  2.- Se modifica ingreso-egreso.component.ts, Se agrega dispatch de loding 
		  	2.1.- Se crean variables
		  			// Variable tipo bandera 
		  		  cargando: boolean = false;
		  		  // Susbscripcion
		  			loadingDubs !: Subscription;
		  	2.2.- Se realiza ID del store 
		  		constructor( private fb: FormBuilder,
		                private ingresoEgresoService: IngresoEgresoService,
		                private store: Store<AppState>) { }
		  	2.3.- Se modifica metodo onInit(), se realiza subcribe al loading del store 
					  ngOnInit(): void {

					    this.loadingDubs = this.store.select('ui').subscribe( ui => { this.cargando = ui.isLoading });

					    this.ingresoForm = this.fb.group({
					      descripcion: ['', Validators.required],
					      monto: ['', [Validators.required, Validators.min(0)]]
					    })

					  }
				2.4.- Se modifica metodo guardar(), se realiza el dispatch del loading 
					 guardar(){

					    if(this.ingresoForm.invalid){ return;}
					    // Se realiza dispatch del isLoading
					    this.store.dispatch( ui.isLoading() )

					    const { descripcion, monto } = this.ingresoForm.value;
					    const ingresoEgreso = new IngresoEgreso( descripcion, monto, this.tipo);

					    this.ingresoEgresoService.crearIngresoEgreso(ingresoEgreso)
					    .then( () => {
					      this.ingresoForm.reset();
					      // Se realiza dispatch del stopLoading
					      this.store.dispatch( ui.stopLoading() );
					      Swal.fire('Resgistro creado', descripcion, 'success');
					    })
					    .catch( err => {
					    	// Se realiza dispatch del stopLoading
					      this.store.dispatch( ui.stopLoading() );
					      Swal.fire('Error', err.message, 'error')
					    });
					  }
				2.5.- Se implementa metodo onDestroy(), se realiza la desusbscripcion del store  
					  ngOnDestroy(): void {
						    this.loadingDubs.unsubscribe();
						  }
		*************************************************************************************************************************************
		101. *******************************************************************************************Ingreso y Egreso - Acciones y Reducer
			1.- Se crea actions --> ingreso-egreso/ingreso-egreso.actions.ts 
				import { createAction, props } from '@ngrx/store';
				import { IngresoEgreso } from '../models/ingreso-egreso.model';

				export const setItems = createAction('[IngresoEgreso] Set Items', props<{ items: IngresoEgreso[] }>());
				export const unSetItems = createAction('[IngresoEgreso] UnSet Items');
			2.- Se crea reducer --> ingreso-egreso/ingreso-egreso.reducer.ts
				import { createReducer, on } from '@ngrx/store';
				import { IngresoEgreso } from '../models/ingreso-egreso.model';
				import { setItems, unSetItems } from './ingreso-egreso.actions';

				export interface State {
				    items: IngresoEgreso[];
				}

				export const initialState: State = {
				  items: [],
				}

				const _ingresoEgresoReducer = createReducer(initialState,

				    on( setItems, (state, { items }) => ({ ...state, items: [...items]})),
				    on( unSetItems, state => ({ ...state, items: []})),

				);

				export function ingresoEgresoReducer(state: any, action: any) {
				    return _ingresoEgresoReducer(state, action);
				}
			3.- Se modifica app.reducer.ts, se agrega nuevo reducer ingresoEgresoReducer
					export interface AppState {
					   ui: ui.State,
					   user: auth.State,
					   // Se agrega el estado
					   ingresosEgresos: ingresoEgreso.State
					}
					export const appReducers: ActionReducerMap<AppState> = {
					   ui: ui.uiReducer ,
					   user: auth.authReducer,
					   // Se agrega el reducer
					   ingresosEgresos: ingresoEgreso.ingresoEgresoReducer
					}
		*************************************************************************************************************************************
		102. *****************************************************************************************************Cargar items desde Firebase
			1.- Se modifica ingreso-egreso.service.ts, se crea metodo que trae de firestore los items
					  initIngreosEgresosListener (uid: string ){
					    this.firestore.collection(`${uid}/ingreso-egreso/items`).valueChanges()
					      .subscribe( algo => {
					        console.log(algo);
					      })
					  }
			2.- Se modifica dashboard.component.ts, se traen los items del servicio  
				2.1.- Se crea variable para la subscripcion 
					userSubscription!: Subscription;
				2.2.- Se modifica metodo ngOnInit(), obtenemos el usuario y los items del usuario  
					ngOnInit(): void {
						// Se almacena en el subcribe la subscripcion del store
				    this.userSubscription = this.store.select('user')
				    .pipe(
				    	//Se filtra para que no venga null
				      filter(auth => auth.user != null)
				      )
				    .subscribe( ({user}) => {
				      console.log(user);
				      //Se llama al servicio para obtener los items
				      this.ingresoEgresoService.initIngreosEgresosListener(user?.uid!);
				    })
		  		}
		  	2.3.- Se implementa el metodo ngOnDestroy(), para eliminr la subscripcion 	
		  		  ngOnDestroy(): void {
					      this.userSubscription.unsubscribe();
					  }
		*************************************************************************************************************************************
		103. ********************************************************************************Obtener el UID de nuestros elementos de Firebase
		***********************************************************************************************************************Manejo de json *****
			1.- Se modifica ingreso-egreso.service.ts, se utiliza el metodo snapshotChanges() y se convierte la data con el operador map
				     this.firestore.collection(`${uid}/ingreso-egreso/items`).snapshotChanges()
					    .pipe(
					    	//Se obtiene el item
					      map( snapshot => {
					      	// Se obtiene los datos uid y data
					        return snapshot.map( doc => {
					          return {
					            uid: doc.payload.doc.id,
					            ...doc.payload.doc.data() as any
					          }
					        })
					      })
					    )
					    .subscribe( algo => {
					        console.log(algo);
					      })
					  }
					}
		*************************************************************************************************************************************
		104. **************************************************************************************Dispatch de las acciones para cargar items
			1.- Se modifica ingreso-egreso.service.ts, se modifica metodo initIngreosEgresosListener(), se elimina la subscripcion   
				  initIngreosEgresosListener (uid: string ){
					    this.firestore.collection(`${uid}/ingreso-egreso/items`).snapshotChanges()
					    .pipe(
					      map( snapshot => {
					        //console.log(snapshot);
					        return snapshot.map( doc => {
					          return {
					            uid: doc.payload.doc.id,
					            ...doc.payload.doc.data() as any
					          }
					        })
					      })
					    )
					    //.subscribe( algo => {
					    //    console.log(algo);
					    //  })
					}
			2.- Se modifica dashboard.component.ts
				2.1.- Se crea variable de tipo subscription  
					ingresosEgresoSuscription!: Subscription;
				2.2.- Se modifica metodo ngoninit(), se realiza la subscricion al servicio ingresoEgresoService y se realiza dispatch para 
					setear items

					  ngOnInit(): void {
					    this.userSubscription = this.store.select('user')
					    .pipe(
					      filter(auth => auth.user != null)
					      )
					    .subscribe( ({user}) => {
					    	// Se almacena en variable la subcripcion
					      this.ingresosEgresoSuscription = this.ingresoEgresoService.initIngreosEgresosListener(user?.uid!)
					      	//Se realiza la subcripcion al servicio
					        .subscribe( ingresosEgresos => {
					        	//Se realiza dispatch de la accion
					          this.store.dispatch( ingresoEgresoActions.setItems({ items : ingresosEgresos }));
					        });
					    })
					  }
				2.3.- Se modifica el metodo ngOnDestroy(), para realizar el unsubscribe. 
					  ngOnDestroy(): void {
					    this.userSubscription.unsubscribe();
					    this.ingresosEgresoSuscription.unsubscribe();
					  }
			3.- Se modifica auth.service.ts, se modifica el metodo initAuthLister(), se realiza el unSetItems cuando no existe usuarios
					import * as ingresoEgresoActions from '../ingreso-egreso/ingreso-egreso.actions';

				  initAuthLister(){
				    this.auth.authState.subscribe( (user) =>{
				        if(user){
				          //Existe
				          this.userSubscripcion = this.firestore.doc(`${user.uid}/usuario`).valueChanges().subscribe( (firestoreUser:any) => {
				            const user = Usuario.fronFirebase( firestoreUser );
				            this._user = user;
				            this.store.dispatch( authActions.setUser({ user: user }));
				          })
				        }else{
				          this._user = null;
				          this.userSubscripcion.unsubscribe();
				          this.store.dispatch( authActions.unSetUser() );
				          //Se realiza unset de items
				          this.store.dispatch( ingresoEgresoActions.unSetItems() );
				        }
				    })
				  }
		*************************************************************************************************************************************
		105. *********************************************************************************Mostrar todos los elementos de ingreso y egreso
		*******************************************************************************************************************class.text-success
		********************************************************************************************************************class.text-danger
			1.- se modifica ingreso-egreso.model.ts, se descomenta atributo uid 
				export class IngresoEgreso{
				  constructor(
				    public descripcion: string,
				    public monto: number,
				    public tipo: string,
				    public uid?: string
				  ){}
				}
			2.- Se modifica detalle.component.ts
				2.1.- Se crean variables
					  ingresosEgresos: IngresoEgreso[] = [];
		  			ingresosEgresosSubscription!: Subscription; 
		  	2.2.- Se realiza ID de store 
		  	  constructor( private store: Store<AppState> ) { }
		  	2.3.- Se modifica metodo ngOnInit(), se realiza subscription al store para recuperar los ingresosEgresos
		  		  ngOnInit(): void {
					    this.ingresosEgresosSubscription = this.store.select('ingresosEgresos').subscribe( ({items}) =>{
					      this.ingresosEgresos = items;
					    })
					  }
				2.4.- Se implementa el metodo ngOnDestroy()
					  ngOnDestroy(){
					    this.ingresosEgresosSubscription.unsubscribe();
					  }
				2.5.- Se crea metodo eliminar()
						  eliminar(uid: string){
						    console.log(uid);
						  }
			3.- Se modifica detalle.component.html
				<tr *ngFor="let item of ingresosEgresos">
		      <td>{{ item.descripcion }}</td>
		      <td>{{ item.monto }}</td>
		      <td [class.text-success]="item.tipo === 'ingreso'"
		          [class.text-danger]="item.tipo === 'egreso'">{{ item.tipo }}</td>
		      <td>
		        <button type="button" class="btn btn-danger" (click)="eliminar( item.uid! )">
		          <i class="fa fa-trash"></i>
		          Eliminar
		        </button>
		      </td>
		    </tr>
		  4.- Se modifica ingreso-egreso.service.ts, se modifica metodo crearIngresoEgreso(), se elimina el atributo uid de ingresoEgreso  
		  	Nota: esta modificacion tiene que ver con el punto 1 donde se le descomenta el uid, y cuando crea un ingreso egreso genera error 
		  	por atributo undefined 
		  		  crearIngresoEgreso( ingresoEgreso: IngresoEgreso){
					    const uid = this.authService.user.uid;
					    // Se borra atributo uid 
					    delete ingresoEgreso.uid;
					    return this.firestore.doc(`${uid}/ingreso-egreso`)
					      .collection('items')
					      .add({ ...ingresoEgreso });
					  }
		*************************************************************************************************************************************
		106. ******************************************************************************************************Eliminar items de firebase
			1.- Se modifica ingreso-egreso.service.ts, se crea metodo para borrarIngresoEgreso()
				  borrarIngresoEgreso( uidItem: string){
				    const uid = this.authService.user.uid;
				    return this.firestore.doc(`${ uid }/ingreso-egreso/items/${ uidItem }`).delete();
				  }
			2.- Se modifica metodo eliminar(), se llama al servicio para eliminar el items  
				  eliminar(uid: string){
				    this.ingresoEgresoService.borrarIngresoEgreso(uid)
				      .then( () => { Swal.fire(
				        'Borrado',
				        'Item Borrado.',
				        'success'
				      )}
				      ).catch((err) => { Swal.fire(
				        'Error',
				        err.message,
				        'error'
				        )
				      });
				  }
		*************************************************************************************************************************************
		107.- ********************************************************************************************************** Ordenar items - Pipe
		*********************************************************************************************************************creacion de pipe
			1.- Se crea pipe --> ng g pipe pipes/ordenIngreso --skip-tests
					import { Pipe, PipeTransform } from '@angular/core';
					import { IngresoEgreso } from '../models/ingreso-egreso.model';

					@Pipe({
					  name: 'ordenIngreso'
					})
					export class OrdenIngresoPipe implements PipeTransform {

					  transform(items: IngresoEgreso[]):IngresoEgreso[] {
					  	//Se agrega slice por error generado 
					    return items.slice().sort( (a,b) =>{
					      if (a.tipo === 'ingreso' ){
					        return -1;
					      }else{
					        return 1;
					      }
					    });
					  }
					}
			2.- Se modifica detalle.component.html, se ultiliza el pipe 
				<tr *ngFor="let item of ingresosEgresos | ordenIngreso">
		*************************************************************************************************************************************
		108. **************************************************************************************************Dashboard - Estadística básica
			1.- Se modifica estadistica.component.ts
				1.1.- Se rean variables  
					  ingresos: number = 0;
					  egresos: number = 0;
					  totalIngresos: number = 0;
					  totalEgresos: number = 0;

				1.2.- Se crea ID del store 
					  constructor( private store: Store<AppState>) {  }
				1.3.- Se modifica el metodo ngOnInit(), se recuperan los datos para las estadisticas  
					ngOnInit(): void {
				    this.store.select('ingresosEgresos').subscribe( ({items}) => {
				      this.generarEstadisticas( items );
				    })
				  }
				1.4.- Se crea metodo que procesa la data y genera estadisticas
					generarEstadisticas( items: IngresoEgreso[]){
				    items.forEach((item: IngresoEgreso) => {
				      if( item.tipo === 'ingreso' ){
				        this.totalIngresos += item.monto;
				        this.ingresos ++;
				      }else{
				        this.totalEgresos += item.monto;
				        this.egresos ++;
				      }
				    });
				  }
			2.- Se modifica estadistica.component.html 
				2.1.- Se agregan los ingresos
					<div class="card card-statistics">
		        <div class="card-body">

		          <div class="clearfix">
		            <div class="float-left">
		              <i class="fa fa-money-bill-alt text-success icon-lg"></i>
		            </div>
		            <div class="float-right">
		              <p class="mb-0 text-right">Ingresos</p>
		              <div class="fluid-container">
		                <h3 class="font-weight-medium text-right mb-0">{{totalIngresos | currency}}</h3>
		              </div>
		            </div>
		          </div>
		          <p class="text-muted mt-3 mb-0">
		            <i class="mdi mdi-alert-octagon mr-1" aria-hidden="true"></i> {{ ingresos }} ingresos
		          </p>
		        </div>
		      </div>
		    2.2.- Se agregan los egresos 
			    <div class="card-body">

		        <div class="clearfix">
		          <div class="float-left">
		            <i class="fa fa-shipping-fast text-danger icon-lg"></i>
		          </div>
		          <div class="float-right">
		            <p class="mb-0 text-right">Egresos</p>
		            <div class="fluid-container">
		              <h3 class="font-weight-medium text-right mb-0">{{ totalEgresos | currency}}</h3>
		            </div>
		          </div>
		        </div>
		        <p class="text-muted mt-3 mb-0">
		          <i class="mdi mdi-alert-octagon mr-1" aria-hidden="true"></i> {{ egresos }} egresos
		        </p>
		      </div>
		    2.3.- Se crea apartado de diferencias y grafica
		    	<!-- Diferencias -->
					<div class="row m-5">

					  <div class="col-12">
					      <div class="card card-statistics">
					        <div class="card-body">
					            <div class="clearfix">
					                <div class="float-left">
					                  <i class="fa fa-building text-info icon-lg"></i>
					                </div>
					                <div class="float-right">
					                  <p class="mb-0 text-right">Diferencia</p>
					                  <div class="fluid-container">
					                    <h3 [class.text-success]=" totalIngresos - totalEgresos > 0"
					                        [class.text-danger]=" totalIngresos - totalEgresos < 0">
					                      {{ totalIngresos - totalEgresos | currency }}
					                    </h3>
					                  </div>
					                </div>
					              </div>
					        </div>
					    </div>
					  </div>

					  <div class="col-12 p-5" *ngIf="ingresos > 0 || egresos > 0">
					      <div class="card card-statistics">
					        <div class="card-body">
					          <h1 class="card-title">GrÃ¡fica</h1>

					            <!-- grÃ¡fica ira aquÃ­ -->

					        </div>
					      </div>
					  </div>
					</div>
		*************************************************************************************************************************************
		110. ******************************************************************************************Chart.js - Gráficas para Angular - URL
			Refrencia --> https://valor-software.com/ng2-charts/

			1.- Se modifica estadistica.component.ts,  se agrega chart de dona al componente 
				1.1.- Se crea variable para los labels y la data 
					  // Doughnut
					  public doughnutChartLabels: string[] = [ 'Ingresos', 'Egresos'];
					  public doughnutChartData: ChartData<'doughnut'> = {
					    labels: this.doughnutChartLabels,
					    datasets: [
					      { data: [  ] },
					    ]
					  };

			  	  public doughnutChartOptions: ChartConfiguration['options'] = {
					    responsive: true,
					    maintainAspectRatio: false,
					  };

						public doughnutChartType: ChartType = 'doughnut';
				1.2.- Se modifica metodo generarEstadistica()
					generarEstadisticas( items: IngresoEgreso[]){
				    console.log(items);
				    //Se realiza clean de las variables
				    this.totalIngresos = 0;
				    this.totalEgresos = 0;
				    this.ingresos = 0;
				    this.egresos = 0;
				    items.forEach((item: IngresoEgreso) => {
				      if( item.tipo === 'ingreso' ){
				        this.totalIngresos += item.monto;
				        this.ingresos ++;
				      }else{
				        this.totalEgresos += item.monto;
				        this.egresos ++;
				      }
				    });
				    // Se setea la data de estadisticas 
				    console.log(this.doughnutChartData.datasets[0]);
				    this.doughnutChartData.datasets[0].data[0] = this.totalIngresos;
				    this.doughnutChartData.datasets[0].data[1] = this.totalEgresos;
				  }
			2.- Se modifica estadistica.component.html, se agrega chart dona  
				  <div class="col-12 p-5" *ngIf="ingresos > 0 || egresos > 0">
			      <div class="card card-statistics" style="width: 400px; height: 400px">
			        <div class="card-body" >
			          <h1 class="card-title">Grafica</h1>

			          <div style="display: block">
			            <canvas baseChart [data]="doughnutChartData"
			                              [type]="doughnutChartType">
			                              [options]="doughnutChartOptions"
			            </canvas>
			          </div>
			        </div>
			      </div>
			  </div>
		*************************************************************************************************************************************
		111. ***************************************************************************************************Colocar el nombre del usuario
			1.- Se modifica sidebar.component.ts  
				1.1.- Se crean variable para almacenar nombre del usuario y la subscripcion  
					  nameUsuario!: String | undefined;
		  			nameSubscription!: Subscription;
		  	1.2.- Se realiza ID del store 
		  		  constructor( private authService: AuthService,
		                private router: Router,
		                private store: Store<AppState>) { }
		  	1.3.- Se modifica metodo ngOnInit(), se recupera usuario del store  
		  		  ngOnInit(): void {
					    this.nameSubscription = this.store.select('user')
					    //Se pasa por pipe para que no lo traiga en caso de ser null
					    .pipe(
					      filter( ({ user }) => user != null )
					    )
					    .subscribe( ({user}) => {
					      this.nameUsuario = user?.nombre;
					    });
					  }
				1.4.- Se implementa el metodo ngOnDestroy() 
				  ngOnDestroy(): void {
				    this.nameSubscription.unsubscribe();
				  }	
			2.- Se modica sidebar.component.html, se renderia la variable creada
				    	<div class="text-wrapper">
		            <p class="profile-name">{{ nameUsuario}}</p>
		            <div>
		              <small class="designation text-muted">Gerente</small>
		            </div>
			        </div>
		*************************************************************************************************************************************
		112. *****************************************************************************************Realizar un backup a GitHub - Sección 9
			1.- git add .
			2.- git commit -m FinSeccion9
			3.- git push 
			4.- git tag -a v3.0.0 -m FinSeccion9
			5.- git push tags	
		*************************************************************************************************************************************
	*************************************************************************************************************************************
	***************************************************Sección 10: NGRX con LazyLoad*****************************************************
		116. *********************************************************************************************************************Módulo Auth
			1.- Se crea modulo --> ng g m auth/auth --flat
				1.1.- Se declaran los componentes, y se importan los modulos que se necesitan  
						@NgModule({
						  declarations: [ LoginComponent, RegisterComponent ],
						  imports: [
						    CommonModule,
						    ReactiveFormsModule,
						    RouterModule
						  ]
						})
						export class AuthModule { }
			2.- Se modifica app.module.ts, se eliminan los componentes de login y register, se importa el nuevo modulo 
				@NgModule({
				  declarations: [
				    AppComponent,
				    DashboardComponent,
				    IngresoEgresoComponent,
				    EstadisticaComponent,
				    DetalleComponent,
				    FooterComponent,
				    NavbarComponent,
				    SidebarComponent,
				    OrdenIngresoPipe
				  ],
				  imports: [
				    BrowserModule,
				    AppRoutingModule,
				    AuthModule,
				    ReactiveFormsModule,
				    AngularFireModule.initializeApp(environment.firebase),
				    AngularFirestoreModule,
				    AngularFireAuthModule,
				    NgChartsModule,
				    StoreModule.forRoot(appReducers),
				    StoreDevtoolsModule.instrument({
				      maxAge: 25, // Retains last 25 states
				      logOnly: environment.production, // Restrict extension to log-only mode
				      autoPause: true, // Pauses recording actions and state changes when the extension window is not open
				    }),
				  ],
				  providers: [],
				  bootstrap: [AppComponent]
				})
				export class AppModule { }
		*************************************************************************************************************************************
		117. ********************************************************************************************************************Módulo Share
			1.- se crea modulo --> ng g m shared/shared --flat  
				@NgModule({
				  declarations: [
				    FooterComponent,
				    NavbarComponent,
				    SidebarComponent,
				  ],
				  imports: [
				    CommonModule,
				    RouterModule
				  ],
				  exports: [
				    FooterComponent,
				    NavbarComponent,
				    SidebarComponent,
				  ],
				})
				export class SharedModule { }
			2.- Se eliminan los componentes de footer, navbar y sidebar, se importa el nuevo modulo 
					@NgModule({
					  declarations: [
					    AppComponent,
					    DashboardComponent,
					    IngresoEgresoComponent,
					    EstadisticaComponent,
					    DetalleComponent,
					    OrdenIngresoPipe
					  ],
					  imports: [
					    BrowserModule,
					    AppRoutingModule,
					    AuthModule,
					    SharedModule,
					    ReactiveFormsModule,
					    AngularFireModule.initializeApp(environment.firebase),
					    AngularFirestoreModule,
					    AngularFireAuthModule,
					    NgChartsModule,
					    StoreModule.forRoot(appReducers),
					    StoreDevtoolsModule.instrument({
					      maxAge: 25, // Retains last 25 states
					      logOnly: environment.production, // Restrict extension to log-only mode
					      autoPause: true, // Pauses recording actions and state changes when the extension window is not open
					    }),
					  ],
					  providers: [],
					  bootstrap: [AppComponent]
					})
					export class AppModule { }
		*************************************************************************************************************************************
		118. ***********************************************************************************************************Módulo Ingreso-Egreso
			1.- Se crea modulo --> ng g m ingreso-egreso/ingresoEgreso --flat
				@NgModule({
				  declarations: [
				    DashboardComponent,
				    IngresoEgresoComponent,
				    EstadisticaComponent,
				    DetalleComponent,
				    OrdenIngresoPipe
				  ],
				  imports: [
				    CommonModule,
				    ReactiveFormsModule,
				    NgChartsModule,
				    SharedModule,
				    RouterModule
				  ]
				})
				export class IngresoEgresoModule { }
			2.- Se modifica app.module.ts, se eliminan los componentes Dashboard, IngresoEgreso, Estadistica, Detalle, OrdenIngresoPipe y se 
			importa el modulo creado, se elimina el modulo SharedModule y NgChartsModule 	
				@NgModule({
				  declarations: [
				    AppComponent
				  ],
				  imports: [
				    BrowserModule,
				    AppRoutingModule,
				    AuthModule,
				    IngresoEgresoModule,
				    AngularFireModule.initializeApp(environment.firebase),
				    AngularFirestoreModule,
				    AngularFireAuthModule,
				    StoreModule.forRoot(appReducers),
				    StoreDevtoolsModule.instrument({
				      maxAge: 25, // Retains last 25 states
				      logOnly: environment.production, // Restrict extension to log-only mode
				      autoPause: true, // Pauses recording actions and state changes when the extension window is not open
				    }),
				  ],
				  providers: [],
				  bootstrap: [AppComponent]
				})
				export class AppModule { }
		*************************************************************************************************************************************
		119. ********************************************************************************************************Dashboard Routing Module
			1.- Se crea modulo -->  ng g m dashboard/dashboardRoutes --flat 
					//Se crean rutas hijas
					const rutasHijas: Routes = [
					  {
					    path: '',
					    component: DashboardComponent,
					    children: dashboarRoutes,
					    //canActivate: [ AuthGuard ]
					  },
					]

					@NgModule({
					  declarations: [],
					  imports: [
					  	// Se cargan las rutas hijas en el routerModule
					    RouterModule.forChild( rutasHijas )
					  ],
					  exports:[
					    RouterModule
					  ]
					})
					export class DashboardRoutesModule { }

			2.- Se modifica app-routing.module.ts, se elimina rutas al dashboard
				const routes: Routes = [
				  { path: 'login', component: LoginComponent },
				  { path: 'register', component: RegisterComponent },
				  { path: '**', redirectTo: '' }
				];
			3.- Se modifica elingreso.-egreso.module.ts, se importa el DashboardRoutesModule 
				  imports: [
				    CommonModule,
				    ReactiveFormsModule,
				    NgChartsModule,
				    SharedModule,
				    RouterModule,
				    DashboardRoutesModule
				  ]
		*************************************************************************************************************************************
		120. *****************************************************************************************LazyLoad del módulo de ingreso y egreso
			Referencia --> https://angular.io/guide/lazy-loading-ngmodules
			1.-Se modifica app-routing.module.ts, se carga modulo de ingresoegreso de forma lazy 
				const routes: Routes = [
					  { path: 'login', component: LoginComponent },
					  { path: 'register', component: RegisterComponent },
					  {
					    path:'',
					    loadChildren: () => import('./ingreso-egreso/ingreso-egreso.module').then( m => m.IngresoEgresoModule )
					  },
					  { path: '**', redirectTo: '' }
					];
			2.- Se modifica app.module.ts, se elimina import de modulo IngresoEgresoModule
				@NgModule({
				  declarations: [
				    AppComponent
				  ],
				  imports: [
				    BrowserModule,
				    AppRoutingModule,
				    AuthModule,
				    AngularFireModule.initializeApp(environment.firebase),
				    AngularFirestoreModule,
				    AngularFireAuthModule,
				    StoreModule.forRoot(appReducers),
				    StoreDevtoolsModule.instrument({
				      maxAge: 25, // Retains last 25 states
				      logOnly: environment.production, // Restrict extension to log-only mode
				      autoPause: true, // Pauses recording actions and state changes when the extension window is not open
				    }),
				  ],
				  providers: [],
				  bootstrap: [AppComponent]
				})
				export class AppModule { }
		*************************************************************************************************************************************
		121. *************************************************************************************************************************CanLoad
		*********************************************************************************************************Operador take rxjs/operators
			1.- Se modifica dashboard.component.ts, se corrige error con subscribe al deslogear 
				  ngOnDestroy(): void {
				    this.userSubscription?.unsubscribe();
				    this.store?.dispatch( authActions.unSetUser() );
				    this.ingresosEgresoSuscription?.unsubscribe();
				  }
			2.- Se modifica auth.guard.ts, se implementa el metodo canLoad()  
				  canLoad(): Observable<boolean> {
				    return this.authService.isAuth().pipe(
				      tap( estado => {
				        if(!estado) this.router.navigateByUrl('/login');
				      }),
				      take(1)
				    );
				  }
			3.- Se modifica app-routing.module.ts,se modifican las rutas, se agrega guard canLoad antes de la carga del modulo de ingresos 
				const routes: Routes = [
				  { path: 'login', component: LoginComponent },
				  { path: 'register', component: RegisterComponent },
				  {
				    path:'',
				    // Se agrega guard canLoad
				    canLoad: [ AuthGuard ],
				    loadChildren: () => import('./ingreso-egreso/ingreso-egreso.module').then( m => m.IngresoEgresoModule )
				  },
				  { path: '**', redirectTo: '' }
				];
		*************************************************************************************************************************************
		122. ******************************************************************************************************************LazyLoad Store
			1.- Se modifica app.reducer.ts, se elimina la entrada al state del ingresosEgresos
				export interface AppState {
				   ui: ui.State,
				   user: auth.State,
				   //ingresosEgresos: ingresoEgreso.State
				}
				export const appReducers: ActionReducerMap<AppState> = {
				   ui: ui.uiReducer ,
				   user: auth.authReducer,
				   //ingresosEgresos: ingresoEgreso.ingresoEgresoReducer
				}
			2.- Se modifica ingreso-egreso.module.ts, se realiza forFeature a ingresoEgresoReducer
				@NgModule({
				  declarations: [
				    DashboardComponent,
				    IngresoEgresoComponent,
				    EstadisticaComponent,
				    DetalleComponent,
				    OrdenIngresoPipe
				  ],
				  imports: [
				    CommonModule,
				    StoreModule.forFeature('ingresosEgresos', ingresoEgresoReducer),
				    ReactiveFormsModule,
				    NgChartsModule,
				    SharedModule,
				    RouterModule,
				    DashboardRoutesModule
				  ]
				})
				export class IngresoEgresoModule { }
			3.- Se modifica ingreso-egreso.reducer.ts, se realiza extends de AppState 
				export interface AppStateWithIngreso extends AppState{
				  ingresosEgresos: State
				}
			4.- Se modifica estadistica.component.ts, se cambia el state del store 
				constructor( private store: Store<AppStateWithIngreso>) {  }
			5.- Se modifica detalle.component.ts, se cambia el state del store 
				  constructor( private store: Store<AppStateWithIngreso>,
		                private ingresoEgresoService: IngresoEgresoService) { }
		*************************************************************************************************************************************
		123. ********************************************************************************************Backup de la aplicación - Sección 10
			1.- git add .
			2.- git commit -m FinSeccion10
			3.- git push 
			4.- git tag -a v2.0.0 -m FinSeccion10
			5.- git push tags	
		*************************************************************************************************************************************
	*************************************************************************************************************************************
	***********************************************Sección 11: Desplegar App en Firebase*************************************************
		1.- Buildeamos la aplicacion --> ng build --prod 
		2.- abrimos la consola de firebase --> https://console.firebase.google.com/u/0/project/ingresos-egresosapp/overview
																				--> hosting 
																				--> comenzar
				2.1.- Se ejecuta --> npm install -g firebase-tools
																	
												--> firebase login dirielfran@gmail.com
												--> firebase init
															--> proceed? yes 
															-->  set up GitHub Action deploys
															--> dist
												--> firebase deploy


												https://ingresos-egresosapp.web.app/login
		*************************************************************************************************************************************
		*******************************************************Sección 12: EffectsApp********************************************************
		132. **********************************************************************************************Inicio del proyecto - http-effects
			1.- Se crea la aplicacion --> ng new http-effects
			2.- Se agrega style de bootstrap 4 --> 
				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
					integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
			3.- Se agrega script de fontawesome   
				  <script src="https://kit.fontawesome.com/6cd398d6ed.js" crossorigin="anonymous"></script>
			4.- Se crea estructura del proyecto se crean los siguientes folder dentro de app  
				services, models, shared, store, usuarios 
		*************************************************************************************************************************************
		133. *************************************************************************************Módulos y componentes de nuestra aplicación
			1.- Se crean modulos de la aplicacion 
					--> ng g m shared/shared --flat 
					--> ng g m usuarios/usuarios --flat 
			2.- Se crean componetes 
				--> ng g c shared/navbar --skip-tests
				--> ng g c usuarios/usuario --skip-tests
			3.- Se modifica sared.module.ts, se le agrega el componente navBar a los exports 
				@NgModule({
				  declarations: [
				    NavbarComponent
				  ],
				  imports: [
				    CommonModule
				  ],
				  exports: [
				    NavbarComponent
				  ]
				})
				export class SharedModule { }
			4.- Se modifica usuarios.module.ts, se agregan los componentes al export  
				@NgModule({
				  declarations: [
				    ListaComponent,
				    UsuarioComponent
				  ],
				  imports: [
				    CommonModule
				  ],
				  exports: [
				    ListaComponent,
				    UsuarioComponent
				  ]
				})
				export class UsuariosModule { }
			5.- Se modifica app.module.ts, se agregan los modulos a los imports 
				@NgModule({
				  declarations: [
				    AppComponent
				  ],
				  imports: [
				    BrowserModule,
				    AppRoutingModule,
				    UsuariosModule,
				    SharedModule
				  ],
				  providers: [],
				  bootstrap: [AppComponent]
				})
				export class AppModule { }
			6.- Se modifica navbar.components.html, se copia estructura de navbar de bootstrap y se pega --> https://getbootstrap.com/docs/5.1/components/navbar/
				<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
				  <div class="container-fluid">
				    <a class="navbar-brand" href="#">Effects</a>
				    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				      <span class="navbar-toggler-icon"></span>
				    </button>
				    <div class="collapse navbar-collapse" id="navbarSupportedContent">
				      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
				        <li class="nav-item">
				          <a class="nav-link active" aria-current="page" href="#">Home</a>
				        </li>
				        <li class="nav-item">
				          <a class="nav-link" href="#">Usuario</a>
				        </li>
				      </ul>
				      <form class="d-flex">
				        <input class="form-control me-2" type="search" placeholder="Id Usuario" >
				        <button class="btn btn-outline-success" type="button">Buscar</button>
				      </form>
				    </div>
				  </div>
				</nav>
			7.- Se modifica app.component.ts, se borra todo el contenido y se agrega componentes 
				<app-navbar></app-navbar>
				<div class="container p-3">
				  <app-lista></app-lista>
				</div>
		*************************************************************************************************************************************
		134. *****************************************************************************************************Rutas de nuestra aplicación
			1.- Se modifica app-routing.module.ts, se agregan routes 
				const routes: Routes = [
				  { path: 'home', component: ListaComponent},
				  { path: 'usuario/:id', component: UsuarioComponent},
				  { path: '**', redirectTo:'home'}
				];
			2.- Se modifica shared.module.ts, se agrega a los import RouterModule 
					@NgModule({
							  declarations: [
							    NavbarComponent
							  ],
							  imports: [
							    CommonModule,
							    // Nos permite utilizar la propiedad routerLink y routerLinkActive 
							    RouterModule
							  ],
							  exports: [
							    NavbarComponent
							  ]
							})
				export class SharedModule { }
			3.- Se modifica app.component.html, se agrega router-oulet 
				<div class="container p-3">
				  <router-outlet></router-outlet>
				</div>
			4.- Se modifica navbar.component.ts 
				4.1.- Se crea ID de router para poder navegar entre links 
					  constructor( private router: Router) { }
				4.2.- Se crea metodo irUsuario(), para que navegue al componente de usuario 
				  irUsuario( id: string ){
				    if (!id){
				      return;
				    }
				    this.router.navigate([ '/usuario', id ])
				  }
			5.- Se modifica navbar.component.html, se agregan routerLink y se agrega evento click a buscador 
				5.1.- Se agrega a home, routerLink y routerLinkActive, se elimina la clase active 
					  <li class="nav-item">
		          <a class="nav-link" aria-current="page" routerLink="home" routerLinkActive="active">Home</a>
		        </li>
		    5.2.- Se agrega a usuario, routerLink y routerLinkActive, se elimina la clase active 
		    	  <li class="nav-item">
		          <a class="nav-link" routerLink="usuario/1" routerLinkActive="active">Usuario</a>
		        </li>
		    5.3.- Se agregan eventos click y keyup al input y button 
		    	<div class="d-flex">
		        <input #txtInput (keyup.enter)="irUsuario( txtInput.value )" class="form-control me-2" type="search" placeholder="Id Usuario" >
		        <button (click)="irUsuario( txtInput.value )" class="btn btn-outline-success" type="button">Buscar</button>
		      </div>
		*************************************************************************************************************************************
		136. ****************************************************************************************************************Reqres y Postman
			1.- Ingresar al siguiente link para pruebas de api en el front --> https://reqres.in/
			2.- Se utilizara postman para las pruebas, en el link sepueden revizar todos los endpoint que tenemos disponibles 
				--> https://reqres.in/api/users/1
				--> https://reqres.in/api/users
		*************************************************************************************************************************************
		137. ***************************************************************************************************************Modelo de usuario
			1.- Se crea dentro de models file --> usuario.model.ts 
				export class Usuario{

				  constructor(
				    public id: string,
				    public email: string,
				    public first_name: string,
				    public last_name: string,
				    public avatar: string,
				  ){}
				}
		*************************************************************************************************************************************
		138. *****************************************************************************************************************Usuario Service
			1.- Se crea servici para usuario --> ng g s services/usuario --skip-tests --flat
				@Injectable({
				  providedIn: 'root'
				})
				export class UsuarioService {

					// Variable para la url raiz de la api
				  private url = 'https://reqres.in/api';

				  // Se agrega ID de HttpClient para llegar a los endpoint 
				  constructor( private http:HttpClient ) { }

				  getUsers(){
				  	// Se consume servicio
				    return this.http.get(`${this.url}/users`)
				      .pipe(
				      	// Se tranforma respuesta en onj de tipo usuario
				        map( (resp: any) => {
				          return resp['data'];
				        })
				      );
				  }
				}
			2.- Se modifica app.module.ts, se agrega import del modulo HttpClientModule 
				@NgModule({
				  declarations: [
				    AppComponent
				  ],
				  imports: [
				    BrowserModule,
				    HttpClientModule,
				    AppRoutingModule,
				    UsuariosModule,
				    SharedModule
				  ],
				  providers: [],
				  bootstrap: [AppComponent]
				})
				export class AppModule { }
			3.- Se modifica lista.component.ts
				@Component({
				  selector: 'app-lista',
				  templateUrl: './lista.component.html',
				  styleUrls: ['./lista.component.css']
				})
				export class ListaComponent implements OnInit {

					// Se realiza ID del servicio
				  constructor( private userService: UsuarioService) { }

				  ngOnInit(): void {
				  	// Se realiza subscribe del servicio
				    this.userService.getUsers().subscribe( data => {
				      console.log( data );
				    })
				  }

				}
		*************************************************************************************************************************************
		139. ********************************************************************************************************HTML del lista.component
			1.- Se modifica lista.component.ts, se crea atributo para guardar la lista de usuario 
				@Component({
				  selector: 'app-lista',
				  templateUrl: './lista.component.html',
				  styleUrls: ['./lista.component.css']
				})
				export class ListaComponent implements OnInit {

				 //Lista de usuarios
				  usuarios: Usuario[] = [];

				  constructor( private userService: UsuarioService) { }

				  ngOnInit(): void {
				    this.userService.getUsers().subscribe( data => {
				      console.log( data );
				      // Se asigna a la variable la data 
				      this.usuarios = data;
				    })
				  }

				}
			2.- Se modifica lista.component.html 
				<div class="card-group">
				  <div class="card" *ngFor="let user of usuarios">
				    <img [src]="user.avatar" class="card-img-top" alt="...">
				    <div class="card-body">
				      <h5 class="card-title">{{user.first_name}} {{user.last_name}}</h5>
				      <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
				      <p class="card-text"><small class="text-muted">{{user.id}}</small></p>
				    </div>
				  </div>
				</div>
		*************************************************************************************************************************************
		140. ********************************************************************Realizar un backup a GitHub de nuestro proyecto - sección 12
			1.- Se crea repositorio en git EffectApp 
			2.- git init
			3.- git add .
			4.- git commit -m PrimerCoomit
			5.- git remote add origin https://github.com/dirielfran/EffectsApp.git
			6.- git branch -M main
			7.- git push -u origin main
			8.- git tag -a v1.0.0 -m appSinEffects
			9.- git push --tags
		*************************************************************************************************************************************
	*************************************************************************************************************************************
	*******************************************************Sección 12: ngrx/effects******************************************************
		144. ************************************************************************************************************Acciones de Usuarios
		*********************************************************************************************************************Instalacion ngrx
			1.- Install de ngrx --> npm install @ngrx/store --save
			2.- Se crea estructura de folder store 
					--> actions  
					--> effects  
					--> reducers  
			3.- Se crea dentro de actions --> usuarios.actions.ts , se crean acciones 
				import { createAction, props } from '@ngrx/store';
				import { Usuario } from 'src/app/models/usuario.model';

				export const cargarUsuarios = createAction('[Usuarios] Cargar Usuarios');

				export const cargarUsuariosSuccess = createAction(
				      '[Usuarios] Cargar Usuarios Success',
				      props<{usuarios: Usuario[]}>()
				);

				export const cargarUsuariosError = createAction(
				    '[Usuarios] Cargar Usuarios Error',
				    props<{ payload: any}>()
				);
			4.- Se crea dentro de actions --> index.ts  
				export * from './usuarios.actions';
		*************************************************************************************************************************************
		145. *************************************************************************************************************Reducer de Usuarios
			1.- Se crea usuarios.reducers.ts dentro de reducers 
				import { createReducer, on } from '@ngrx/store';
				import { Usuario } from 'src/app/models/usuario.model';
				import { cargarUsuarios, cargarUsuariosSuccess, cargarUsuariosError } from '../actions';

				//Tipado
				export interface UsuariosState {
				    users: Usuario[],
				    loaded: boolean,
				    loading: boolean,
				    error: any
				}

				//State inicial
				export const UsuariosInitialState: UsuariosState = {
				  users: [],
				  loaded: false,
				  loading: false,
				  error: null
				}

				//Se crean las funciones por cada accion
				const _usuariosReducer = createReducer(UsuariosInitialState,

				    on(cargarUsuarios , state => ({ ...state, loading: true})),
				    on(cargarUsuariosSuccess , (state , { usuarios }) => ({
				      ...state,
				      loading: false,
				      loaded: true,
				      users: [ ...usuarios ]
				    })),
				    on(cargarUsuariosError , (state , { payload }) => ({
				      ...state,
				      loading: false,
				      loaded: false,
				      users: payload
				    })),

				);

				//Se expone el reducer
				export function usuariosReducer(state: any, action: any) {
				    return _usuariosReducer(state, action);
				}
			2.- Se crea dentro de reducers index.ts 
				export * from './usuarios.reducers';
		*************************************************************************************************************************************
		146. **********************************************************************************************AppReducer, StoreModule y DevTools
		************************************************************************************************************Se instala Store-devtools
			Se instala --> npm install @ngrx/store-devtools --save
			1.- Se crea dentro de folder reducers --> app.reducers.ts 
				snippets --> ngrxapp

				import { ActionReducerMap } from '@ngrx/store';
				import * as reducers from './reducers';


				export interface AppState {
				   usuarios: reducers.UsuariosState
				}

				export const appReducers: ActionReducerMap<AppState> = {
				   usuarios: reducers.usuariosReducer,
				}
			2.- Se modifica app.module.ts, se importan el StoreModule integrando appReducers y se importa StoreDevtoolsModule
					@NgModule({
				  declarations: [
				    AppComponent
				  ],
				  imports: [
				    BrowserAnimationsModule,
				    BrowserModule,
				    HttpClientModule,
				    AppRoutingModule,
				    UsuariosModule,
				    SharedModule,
				    ButtonModule,
				    SidebarModule,
				    StoreModule.forRoot( appReducers ),
				    StoreDevtoolsModule.instrument({
				      maxAge: 25, // Retains last 25 states
				      logOnly: environment.production, // Restrict extension to log-only mode
				      autoPause: true, // Pauses recording actions and state changes when the extension window is not open
				    })
				  ],
				  providers: [],
				  bootstrap: [AppComponent]
				})
				export class AppModule { }
			3.- Subir la aplicacion y proBar la consola de redux en chrome
		*************************************************************************************************************************************
		148. *************************************************************************************************************Effects de Usuarios
			1.- Instalacion de effects  --> npm install @ngrx/effects --save
			2.- Se crea archivo dentro de folder effects --> usuarios.effects.ts 
				import { Injectable } from "@angular/core";
				import { Actions, createEffect, ofType } from "@ngrx/effects";
				import { tap, mergeMap } from "rxjs/operators";
				import { UsuarioService } from "src/app/services/usuario.service";
				import * as usuariosActions from '../actions/usuarios.actions'

				//Servicio
				@Injectable()
				export class UsuariosEffects{
				  constructor(
				  	//ID de actions de ngrx/efffects
				    private actions$: Actions,
				    //ID del servicio
				    private usuarioService: UsuarioService
				  ){  }

				  // metodo que carga mis usuarios
				  cargarUsuarios$ = createEffect(
				    () => this.actions$.pipe(
				    	// La accion que quiero cargar
				      ofType( usuariosActions.cargarUsuarios ),
				      // Imprimo la tipo de action
				      tap( data => console.log( 'effect tap', data ) ),
				      // transformo el observable
				      mergeMap(
				      	//obtengo la data del servicio
				        () => this.usuarioService.getUsers()
				          .pipe(
				            tap( data => console.log('getUsers effect', data))
				          )
				      )
				    )
				  );
				}
			2.- Se modifica lista.component.ts, se realiza dispatch de la action cargarUsuarios  
				@Component({
				  selector: 'app-lista',
				  templateUrl: './lista.component.html',
				  styleUrls: ['./lista.component.css']
				})
				export class ListaComponent implements OnInit {

				 //Lista de usuarios
				  usuarios: Usuario[] = [];

				  //Se crea ID del store
				  constructor( /*private userService: UsuarioService*/
				  							private store: Store<AppState>) { }

				  ngOnInit(): void {
				    /*this.userService.getUsers().subscribe( data => {
				      console.log( data );
				      // Se asigna a la variable la data 
				      this.usuarios = data;
				    })*/
				    //Se realiza el dispatch de la accion
				    this.store.dispatch(cargarUsuarios());
				  }

				}
			3.- Se crea dentro effects --> index.ts  
				import { UsuariosEffects } from "./usuarios.effects";

				export const EffectsArray: any[] = [UsuariosEffects];
			4.- Se modifica app.module.ts, se configura el effectsModule 
				4.1.- Se realizan las importaciones  
					import { EffectsModule } from '@ngrx/effects';
					import { EffectsArray } from './store/effects';
				4.2.- Se realiza el import del EffectsModule
					  imports: [
					    BrowserAnimationsModule,
					    BrowserModule,
					    HttpClientModule,
					    AppRoutingModule,
					    UsuariosModule,
					    SharedModule,
					    ButtonModule,
					    SidebarModule,
					    StoreModule.forRoot( appReducers ),
					    EffectsModule.forRoot(EffectsArray),
					    StoreDevtoolsModule.instrument({
					      maxAge: 25, // Retains last 25 states
					      logOnly: environment.production, // Restrict extension to log-only mode
					      autoPause: true, // Pauses recording actions and state changes when the extension window is not open
					    }),
					  ],
		*************************************************************************************************************************************
		149. ***************************************************************************************************Effects de Usuarios - Parte 2
			1.- Se modifica usuarios.effects.ts,se modifica atributo cargarUsuarios$, se agrega accion al efecto 
				  cargarUsuarios$ = createEffect(
				    () => this.actions$.pipe(
				      ofType( usuariosActions.cargarUsuarios ),
				      //tap( data => console.log( 'effect tap', data ) ),
				      mergeMap(
				        () => this.usuarioService.getUsers()
				          .pipe(
				            //tap( data => console.log('getUsers effect', data))
				            //Se agrega accion al efecto
				            map( users => usuariosActions.cargarUsuariosSuccess({usuarios: users}))
				          )
				      )
				    )
				  );
			2.- Se modifica lista.component.ts, se modifica ngOnInit(), se recuperan los datos del store   	
			  ngOnInit(): void {
			    this.store.select('usuarios').subscribe( ({users}) => {
			      this.usuarios = users;
			    })
			    this.store.dispatch(cargarUsuarios());

			  }
		*************************************************************************************************************************************
		150. ************************************************************************************************Manejo de errores en los effects
			1.- Se modifica usuario.service.ts, se insita al error cambiando la urla auna no existente 
					private url = 'https://reqres.in/api444444444444';
			2.- Se modifica usuarios.effects.ts, se le agrega catchError() al effect cargaUsuario$  
				  cargarUsuarios$ = createEffect(
				    () => this.actions$.pipe(
				      ofType( usuariosActions.cargarUsuarios ),
				      //tap( data => console.log( 'effect tap', data ) ),
				      mergeMap(
				        () => this.usuarioService.getUsers()
				          .pipe(
				            //tap( data => console.log('getUsers effect', data))
				            map( users => usuariosActions.cargarUsuariosSuccess({usuarios: users})),
				            catchError( err => of(usuariosActions.cargarUsuariosError({ payload: err })))
				          )
				      )
				    )
				  );
			3.- Se modifica usuario.reducers.ts, se modifica reducer de cargarUsuariosError, para que solo me cargue la data que 
			necesito del error
				const _usuariosReducer = createReducer(UsuariosInitialState,

				    on(cargarUsuarios , state => ({ ...state, loading: true})),
				    on(cargarUsuariosSuccess , (state , { usuarios }) => ({
				      ...state,
				      loading: false,
				      loaded: true,
				      users: [ ...usuarios ]
				    })),
				    on(cargarUsuariosError , (state , { payload }) => ({
				      ...state,
				      loading: false,
				      loaded: false,
				      error: {
				        url: payload.url,
				        name: payload.name,
				        message: payload.message
				      }
				    })),

				);
		*************************************************************************************************************************************
		151. **********************************************************************************************Mostrar la información en pantalla
			1.- Se modifica usuario.service, se modifica url, se modifica getUsers(), se le modifica endpoint
				  private url = 'https://reqres.in/api';

				  getUsers(){
				    return this.http.get(`${this.url}/users?per_page=6&delay=3`)
				      .pipe(
				        map( (resp: any) => {
				          return resp['data'];
				        })
				      );
				  }
			2.- Se modifca lista.component.ts
				2.1.- Se agregan atributos para el loading y error 
					  loading: boolean = false;
		  			error: any;
		  	2.2.- Se modifica el metodo ngOnInit() para cargar loading y error
		  		 ngOnInit(): void {
				    /*this.userService.getUsers().subscribe( data => {
				      console.log( data );
				      this.usuarios = data;
				    });*/
				    this.store.select('usuarios').subscribe( ({users, loading, error}) => {
				      this.usuarios = users;
				      this.loading = loading;
				      this.error = error;
				    })
				    this.store.dispatch(cargarUsuarios());

				  }
			3.- Se modifica lista.component.html, se agregan divs para error y loading 
					<!--div para loading -->
					<div class="alert alert-info text-center" role="alert" *ngIf="loading">
					  <h4 class="alert-heading">Cargando...</h4>
					  <p><i class="fa fa-sync fa-spin fa-2x"></i></p>
					  <p class="mb-0"></p>
					</div>
					<!--div para error -->
					<div class="alert alert-danger text-center" role="alert" *ngIf="error">
					  <h4 class="alert-heading">Cargando...</h4>
					  <pre>{{ error | json }}</pre>
					  <p class="mb-0"></p>
					</div>
		*************************************************************************************************************************************
		152. ************************************************************************************************Usuario Actions, Reducer y State
			1.- Se crea usuario.actions.ts  
					import { createAction, props } from '@ngrx/store';
					import { Usuario } from 'src/app/models/usuario.model';

					export const cargarUsuario = createAction(
					  '[Usuario] Cargar Usuarios',
					  // Se le pasa id por parametro
					  props<{ id: string }>()
					  );

					export const cargarUsuarioSuccess = createAction(
					      '[Usuario] Cargar Usuarios Success',
					      props<{usuario: Usuario}>()
					);

					export const cargarUsuarioError = createAction(
					    '[Usuario] Cargar Usuario Error',
					    props<{ payload: any}>()
					);
			2.- Se modifica actions/index.ts, se agrega usuario.actions  
				export * from './usuarios.actions';
				export * from './usuario.actions';
			3.- Se crea usuario.reducers.ts 
				// Se crea tipado
				export interface UsuarioState {
				    id      : String
				    user    : Usuario,
				    loaded  : boolean,
				    loading : boolean,
				    error   : any
				}

				// Se crea state inicial
				export const UsuarioInitialState: UsuarioState = {
				  id        : null,
				  user      : null,
				  loaded    : false,
				  loading   : false,
				  error     : null
				}

				// Se crea reducer
				const _usuarioReducer = createReducer(UsuarioInitialState,
						// Se ejecuta accion de carga de usuario pasando id
				    on(cargarUsuario , (state, {id}) => ({
				      ...state,
				      loading: true,
				      id: id
				    })),
				    // Se ejecuta accion de carga usuario pasando un usuario
				    on(cargarUsuarioSuccess , (state , { usuario }) => ({
				      ...state,
				      loading: false,
				      loaded: true,
				      user:  { ...usuario}
				    })),
				    // Se ejecuta accion de error pasando payload
				    on(cargarUsuarioError , (state , { payload }) => ({
				      ...state,
				      loading: false,
				      loaded: false,
				      error: {
				        url: payload.url,
				        name: payload.name,
				        message: payload.message
				      }
				    })),

				);

				// Se expone reducer
				export function usuarioReducer(state: any, action: any) {
				    return _usuarioReducer(state, action);
				}
			4. Se modifia reducers/index.ts, se agrega uuario.reducers
				export * from './usuarios.reducers';
				export * from './usuario.reducers';
			5.- Se modifica app.reducers.ts  
				export interface AppState {
				   usuarios: reducers.UsuariosState
				   // Se agrega state de usuario al AppState
				   usuario: reducers.UsuarioState
				}

				export const appReducers: ActionReducerMap<AppState> = {
				   usuarios: reducers.usuariosReducer,
				   // Se agrega usuarioReducer
				   usuario: reducers.usuarioReducer,
				}
				6.- Se debe probar la aplicacion y debe aparecer el nodo de usuario, incluido en el app.reducers.ts 
		*************************************************************************************************************************************
		153. *****************************************************************************************************************Usuario Effects
			1.- Se modifica usuario.component.ts, se captura id del parametro y se realiza el dispatch 
					// ID del store y del ActivatedRoute 
				  constructor( private router: ActivatedRoute,
		                private store: Store<AppState> ) { }

				  ngOnInit(): void {
				  	// se captura el parametro
				    this.router.params.subscribe( ({id}) => {
				    	//Se realiza el dispatch de la accion
				      this.store.dispatch( cargarUsuario({id}) );
				    });
				  }
			2.- Se modifica usuario.service.ts, se agrega metodo para recuperar un usuario por id 
				  getUserById( id: string ){
				    return this.http.get(`${this.url}/users/${id}`)
				      .pipe(
				        map( (resp: any) => {
				          return resp['data'];
				        })
				      );
				  }
			3.- Se crea dentro de effects --> usuario.effects.ts  
					import { Injectable } from "@angular/core";
					import { Actions, createEffect, ofType } from "@ngrx/effects";
					import { of } from "rxjs";
					import { tap, mergeMap, map, catchError } from "rxjs/operators";
					import { UsuarioService } from "src/app/services/usuario.service";
					import * as usuariosActions from '../actions'

					// Se indica que es un servicio
					@Injectable()
					export class UsuarioEffects{
						// S realiza ID del Actions y del servicio de usuarios
					  constructor(
					    private actions$: Actions,
					    private usuarioService: UsuarioService
					  ){  }

					  // Se crea effect (observable)
					  cargarUsuario$ = createEffect(
					    () => this.actions$.pipe(
					    	// Se e indica la acccion
					      ofType( usuariosActions.cargarUsuario ),
					      // Se realiza merge del observable con el que viene del servicio
					      mergeMap(
					      	// Por medio del action recupero el id y se lo paso al servicio
					        ( action ) => this.usuarioService.getUserById( action.id )
					          .pipe(
					          	// Se ejecuta accion de cargarUsuarioSuccess
					            map( user => usuariosActions.cargarUsuarioSuccess({usuario: user})),
					            catchError( err => of(usuariosActions.cargarUsuarioError({ payload: err })))
					          )
					      ), take(1)
					    )
					  );
					}
			4.- Se modifica effects/index.ts, se agrega effect UsuarioEffects
				export const EffectsArray: any[] = [UsuariosEffects, UsuarioEffects];
		*************************************************************************************************************************************
		154. **********************************************************************************************Mostrar la información del usuario
			1.- Se modifica usuario.component.ts 
				1.1.- Se crean variables 
					//Se importan variables 
				  loading: boolean = false;
		  		error: any;
		  	1.2.- Se modifica metodo ngOnInit(), se capturan estados del loadin y error
				  ngOnInit(): void {
				    this.store.select('usuario').subscribe( ({user, loading, error}) =>{
				      this.usuario = user;
				      this.loading = loading;
				      this.error = error;
				  })
			2.- Se modifica usuario.component.html 
					<!-- div para loading -->
				<div class="alert alert-info text-center" role="alert" *ngIf="loading">
				  <h4 class="alert-heading">Cargando...</h4>
				  <p><i class="fa fa-sync fa-spin fa-2x"></i></p>
				  <p class="mb-0"></p>
				</div>

				<!-- div para error -->
				<div class="alert alert-danger text-center" role="alert" *ngIf="error">
				  <h4 class="alert-heading">Cargando...</h4>
				  <pre>{{ error | json }}</pre>
				  <p class="mb-0"></p>
				</div>

				<!-- div para contenido -->
				<div class="container" style="margin-top: 5rem;">
				  <div class="row justify-content-md-center">
				    <div class="card" style="width: 18rem;" *ngIf="!loading">
				      <img [src]="usuario.avatar" alt="avatar" class="img-thumbnail">
				      <ul class="list-group list-group-flush">
				        <li class="list-group-item"><b>Nombre:</b>{{ usuario.first_name }}</li>
				        <li class="list-group-item"><b>Apellido:</b>{{ usuario.last_name }}</li>
				      </ul>
				    </div>
				  </div>
				</div>
		*************************************************************************************************************************************
		155. ********************************************************************************************Backup de la aplicación - Sección 13
			1.- Se crea repositorio en git EffectApp 
			2.- git init
			3.- git add .
			4.- git commit -m PrimerCoomit
			5.- git remote add origin https://github.com/dirielfran/EffectsApp.git
			6.- git branch -M main
			7.- git push -u origin main
			8.- git tag -a v1.0.0 -m appSinEffects
			9.- git push --tags
		*************************************************************************************************************************************
	*************************************************************************************************************************************
	*************************************************************************************************************************************
	*************************************Sección 12: Despliegue de la aplicacion en git hub**********************************************
		1.- ng build --prod
		2.- Se traslada la carpeta creada en dist a la raiz de proyecto y se le cambia el nombre por docs
		3.- git add .
		4.- git commit -m ProduccionListo
		5.- git push  (debe etar la carpeta docs en el repositorio de github remoto)
		6.- accedemos al index.html dede github y le borramos href 
		7.- Se despliega, se ingresa en el repositorio a la opcion setting, luego al apartados de pages, en source se indica la rama main   
		y luego la carpeta docs , se salva y se espera unos minutos al despliegue de la aplicacion, se colocara en color verde cuando 
		este ready, y se ejecuta el link indicado. 
	*************************************************************************************************************************************
***********************************************************Fin del Curso*************************************************************
*************************************************************************************************************************************



Complementarios*********************************************************************************************************************
	CanLoad Guard***********************************************************************************************************
		The CanLoadGuard evita la carga del módulo de carga diferida . Generalmente usamos esta protección cuando no 
		queremos que un usuario no autorizado navegue a alguna de las rutas del módulo, también se detenga y luego 
		incluso vea el código fuente del módulo.

		El Angular proporciona canActivateGuard, que evita que usuarios no autorizados accedan a la ruta. Pero no impide 
		que se descargue el módulo. El usuario puede utilizar la consola de desarrollo de Chrome para ver el código 
		fuente. The CanLoadGuard evita que se descargue el módulo.


		@Injectable({
			  providedIn: 'root'
			})
			export class RoleGuard implements CanActivate, CanLoad{

			  constructor(private authService: AuthService,
			    private router: Router){ }

			  canLoad(route: Route, segments: UrlSegment[]): boolean | Observable<boolean> | Promise<boolean> {
			    //Se valida si el usuario no esta autenticado
			    if(!this.authService.isAuthenticated()){
			      this.router.navigate(['/login']);
			      return false;
			    }
			    
			    //Se obtiene el role de la ruta
			    if(this.authService.hasRole(route.data['role'])){
			      return true;
			    }
			    swal.fire('Acceso denegado.',`Usuario ${this.authService.usuario.username} no tiene acceso a este recurso`,'warning');
			    this.router.navigate(['/clientes']);
			    return false;
			  }

			  canActivate(
			    next: ActivatedRouteSnapshot,
			    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
			      //Se valida si el usuario no esta autenticado
			      if(!this.authService.isAuthenticated()){
			        this.router.navigate(['/login']);
			        return false;
			      }

			      //Se obtiene el role de la ruta
			      let role = next.data['role'] as string;
			      if(this.authService.hasRole(role)){
			        return true;
			      }

			      swal.fire('Acceso denegado.',`Usuario ${this.authService.usuario.username} no tiene acceso a este recurso`,'warning');
			      this.router.navigate(['/clientes']);
			      return false;
			  }
			}
	************************************************************************************************************************
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
	ElementRef y Render2****************************************************************************************************
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
		/*Margen de 15px para todo el contenido*/
		    *{
			      margin: 15 px;
			    }
	************************************************************************************************************************
	Evitar propagacion de eventos*******************************************************************************************
		<button pButton pRipple type="button" (click)="$event.stopPropagation()" icon="pi pi-save" iconPos="right" class="p-button-rounded p-button-success p-button-sm"></button>
	************************************************************************************************************************
	Temas de AngularMaterial************************************************************************************************
		deeppurple-amber.css
		indigo-pink.css
		pink-bluegrey.css
		purple-green.css
	************************************************************************************************************************
	Operadores 	--> rxjs****************************************************************************************************
		Los operadores de RxJs son funciones que pueden ser encadenadas en lo que llamamos la cadena o pipeline de 
		operadores y que se sitúan entre medias del Observable (productor de la información) y el Observer (consumidor 
		de la misma) con el objetivo de filtrar, transformar o combinar los valores del Observable/Observables.

			Referencia --> http://reactivex.io/documentation/operators.html

		switchMap***********************************************************************************************************
			Proyecta cada valor de fuente en un Observable que se fusiona en el Observable de salida, emitiendo valores solo 
			del Observable proyectado más recientemente.

			el switchMap operador creará un observable derivado (llamado observable interno) a partir de una fuente observable 
			y emitirá esos valores.

			Cuando la fuente emite un nuevo valor, creará un nuevo observable interno y switcha esos valores en su lugar. De lo que 
			se cancela la suscripción son los observables internos que se crean sobre la marcha, y no la fuente observable.

			Ejemplo:____________________________________________________________________________________________________
				import { of } from 'rxjs';
				import { switchMap } from 'rxjs/operators';
				 
				const switched = of(1, 2, 3).pipe(switchMap((x: number) => of(x, x ** 2, x ** 3)));
				switched.subscribe(x => console.log(x));
				// outputs
				// 1
				// 1
				// 1
				// 2
				// 4
				// 8
				// ... and so on
		********************************************************************************************************************
		retry***************************************************************************************************************
			Donde el operador catchError ayuda a crear un camino simple para recuperarnos, el operador retry te permite 
			reintentar una petición fallida.

			Usa el operador retry antes del operador catchError. Dicho operador te re-subscribe a la fuente original del 
			observable, la cual puede re-ejecutar una secuencia llena de acciones que resultaron en el error en primer lugar. 
			Si esto incluye una petición HTTP, entonces el operador reintentará hacer la petición HTTP.

			Ejemplo_________________________________________________________________________________________________________
				import { ajax } from 'rxjs/ajax';
				import { map, retry, catchError } from 'rxjs/operators';

				const apiData = ajax('/api/data').pipe(
				  retry(3), // Retry up to 3 times before failing
				  map(res => {
				    if (!res.response) {
				      throw new Error('Value expected!');
				    }
				    return res.response;
				  }),
				  catchError(err => of([]))
				);

				apiData.subscribe({
				  next(x) { console.log('data: ', x); },
				  error(err) { console.log('errors already caught... will not run'); }
				});
		********************************************************************************************************************
		map()***************************************************************************************************************
			pasa cada valor de origen a través de una función de transformación para obtener los valores de salida 
			correspondientes.

			Ejemplo_________________________________________________________________________________________________________
				import { fromEvent } from 'rxjs';
				import { map } from 'rxjs/operators';

				const clicks = fromEvent(document, 'click');
				const positions = clicks.pipe(map(ev => ev.clientX));
				positions.subscribe(x => console.log(x));
		********************************************************************************************************************
		take(n)*************************************************************************************************************
			take devuelve un Observable que emite solo los primeros countvalores emitidos por la fuente Observable. Si la 
			fuente emite menos de countvalores, se emiten todos sus valores. Después de eso, se completa, independientemente 
			de si la fuente se completa.

			Ejemplo_________________________________________________________________________________________________________
				import { interval } from 'rxjs';
				import { take } from 'rxjs/operators';
				 
				const intervalCount = interval(1000);
				const takeFive = intervalCount.pipe(take(5));
				takeFive.subscribe(x => console.log(x));
				 
				// Logs:
				// 0
				// 1
				// 2
				// 3
				// 4
		********************************************************************************************************************
		mergeMap()**********************************************************************************************************
			El operador RxJS mergeMap() es un operador de transformación que aplica una función de proyecto en cada valor de 
			origen de un Observable, que luego se fusiona en el Observable de salida.

			En otras palabras, podemos decir que el operador RxJS mergeMap() asigna cada valor a un Observable, luego aplana 
			todos estos Observables internos usando mergeAll.

			Ejemplo: 
				const letters = of('a', 'b', 'c', 'd', 'e', 'f');  
				const result = letters.pipe(  
				  mergeMap(x => interval(2000).pipe(map(i => x+i))),  
				);  
				result.subscribe(x => console.log(x));  
		********************************************************************************************************************
	************************************************************************************************************************
	funciones 	--> rxjs ***************************************************************************************************
		interval()*********************************************************************************************************
			interval() devuelve un Observable que emite una secuencia infinita de números enteros ascendentes, con un 
			intervalo de tiempo constante de su elección entre esas emisiones. La primera emisión no se envía inmediatamente, 
			sino solo después de que ha pasado el primer período. De forma predeterminada, este operador utiliza async 
			SchedulerLikepara proporcionar una noción de tiempo, pero puede pasarle alguna SchedulerLike
			Ejemplo_______________________________________________________________________________________________________
				import { interval } from 'rxjs';
				import { take } from 'rxjs/operators';
				 
				const numbers = interval(1000);
				 
				const takeFourNumbers = numbers.pipe(take(4));
				 
				takeFourNumbers.subscribe(x => console.log('Next: ', x));
				 
				// Logs:
				// Next: 0
				// Next: 1
				// Next: 2
				// Next: 3
		*******************************************************************************************************************
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
	cicleLife Hooks Angular*************************************************************************************************
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
	Providers***************************************************************************************************************
		Refrencia --> https://medium.com/@cristianflores.ee/providers-en-angular-1832e8093e2a
		Un provider es una instrucción para el sistema de Inyección de Dependencias de cómo obtener un valor para 
		una dependencia. Dependencia: al consumo de los servicios inyectables se le conoce como dependencia. Cada componente 
		o servicio puede declarar en su constructor sus dependencias hacia servicios inyectables.

		providedIn y NgModules********************************************************************************************
			Podemos especificar que un servicio se proporcione en un determinado @NgModule. Por ejemplo, si queremos que 
			UserService no este disponible en la app a menos que se importe un UserModule, podemos especificar que el 
			servicio se proporcione solo en ese módulo.
			
			Ejemplo_____________________________________________________________________________________________________
				@Injectable({
				  providedIn: UserModule, // modulo específico
				})
				export class UserService {
				}

			Nota: Si no es posible especificar en el servicio qué módulo debe proporcionarlo, también podemos declarar 
			un provider para el servicio dentro del módulo.

			Ejemplo_____________________________________________________________________________________________________
				@NgModule({
				  providers: [UserService],
				})
				export class UserModule {
				}
		******************************************************************************************************************
		Limitar el scope del provider con componentes*********************************************************************
			Otra forma de limitar el scope del provider es agregar el servicio al array providers de la metadata del 
			componente. Los providers de componentes y de modulos son independientes entre sí. Este método es útil cuando 
			deseamos cargar con entusiasmo un módulo que necesita un servicio para sí mismo. La prestación de un servicio 
			en el componente limita el servicio solo a ese componente (otros componentes en el mismo módulo no pueden 
			acceder a él).

			Ejemplo____________________________________________________________________________________________________
				@Component({
				/* . . . */
				  providers: [UserService]
				})
	************************************************************************************************************************
	ForRoot*****************************************************************************************************************
		Referencia --> https://www.freelancermap.com/blog/es/forroot-forchild-modulos-angular/
		Se usa Cuando un módulo es “eager”, esto es, no es lazy-loaded (lo cargamos al iniciar la aplicación). Angular crea 
		un factory para todos los módulos, excepto los módulos lazy, que al cargarse bajo demanda, tienen su propio factory. 
		Cuando nosotros usamos forRoot(), lo que estamos haciendo es cargar un provider que va a ser inyectado hacia la “raíz” 
		de los módulos gracias a que usa el mismo factory que nuestro módulo principal.
	************************************************************************************************************************
	ForChild****************************************************************************************************************
		Referencia --> https://www.freelancermap.com/blog/es/forroot-forchild-modulos-angular/
		Se usa forChild al reves: cuando queremos entregar un provider que es visible solamente para los módulos “hijos” de 
		nuestro modulo, en el caso de que sean lazy loaded. Como cada modulo lazy se carga bajo demanda, tiene su propio 
		inyector.
	************************************************************************************************************************
	ForFeature**************************************************************************************************************
		Para manejar fácilmente los módulos Angular, debe conocer una terminología básica que lo ayudará con problemas 
		similares a este en el futuro.

		Una aplicación Angular está (básicamente) hecha de un módulo, llamado "AppModule" que se conoce convencionalmente 
		como Módulo raíz : este es el módulo principal de la aplicación, el que se inicia en el archivo main.ts que es, en 
		realidad, la entrada de la aplicación. Cuando importa algunos módulos externos en la matriz de importaciones de AppModule, 
		generalmente invoca el método estático forRoot para dar una configuración inicial a ese módulo: es el caso del método 
		forRoot de StoreModule, que establece la configuración inicial de la tienda.

		Puede desarrollar su aplicación encontrando alguna "zona de preocupación" para resolver una parte específica de su 
		problema. Cuando combina componentes, directivas, servicios y otros elementos de Angular en un módulo, ese módulo se 
		convierte en un módulo de características porque, como sugiere la convención de nomenclatura, proporciona una 
		característica a su aplicación. No importa cómo cargue un módulo (Eagerly o Lazily), siempre será un módulo de 
		características.


		El método forRoot se invoca en AppModule y, generalmente, una vez en la aplicación para inicializar Store y proporcionar 
		la configuración inicial de reductores/acciones/estado. Si usa EffectsModule, también invocará el método forRoot en 
		este módulo:

			@ngModule({ importaciones: [ StoreModule.forRoot(), EffectsModule.forRoot() ] }) class AppModule {}

		El método forFeature se invoca en cualquier módulo de características que requiera su propia parte de la gestión del 
		estado: como ejemplo, un UserModule definirá su propia parte del estado, describiendo las acciones requeridas, los 
		reductores, etc. Si usa EffectsModule, recuerde invocar el método forFeature contra él también. Como usted mismo habrá 
		entendido, forFeature (como el método forChild de Angular más genérico ) se puede invocar varias veces para el mismo 
		módulo importado en la aplicación:

			AppModule -> StoreModule.forRoot(...) UsersModule -> StoreModule.forFeature(...) OtherModule -> StoreModule.forFeature(...)

		Finalmente, recuerde que las firmas forRoot y forFeature son ligeramente diferentes entre sí: esta última tiene, de hecho, 
		el primer parámetro que describe el nombre de la característica . Los otros parámetros, si no recuerdo mal, son los mismos 
		que los del método forRoot. Si intenta generar un modelo para un módulo de funciones con el paquete @ngrx/schematics con el 
		siguiente comando:

			ng generate feature user/User -m modules/users/users.module.ts --group
		
		Encontrará que el nombre de la característica será simplemente la versión en minúsculas de la palabra Usuario en el 
		comando dado: usuario .
	************************************************************************************************************************
	@Input ****************************************************************************************************************
		Se usa para recibir datos en un componente mientras que @Output se usa para enviar datos fuera de un componente.
		
		Para indicarle a un componente que puede recibir un valor desde el componente padre debemos agregarle el decorador 
		@input a la propiedad que deseamos controlar.
		
		Ejemplo_________________________________________________________________________________________________________
			* incrementador.component.ts
				@Input() progress: number = 40;
			* progress.component.html
				<app-incrementador [progress]="15"></app-incrementador>
	***********************************************************************************************************************
	Config de tsconfig.json************************************************************************************************
		Deshabilitar cheque de null 
			Se agrega a "CompilerOptions" -->     "strictNullChecks": false,
			Se debe reiniciar el visual code
	***********************************************************************************************************************
	/*https://quicktype.io/*/ --> nos convierte un json en un tipado o viceversa***********************************************
************************************************************************************************************************************



******************************************************Primer curso de Angular**********************************************************
	Seccion 1 Intro************************************************************************************************************************
	Complementarios************************************************************************************************************************
		Versionado de herramientas
			instalar node.js
			instalacion de npm --> npm install npm@latest -g
			node -v 
			npm -v 
			tsc --version 
			ng -v --> angular cli 
			npm install -g typescript
			tsc --version 		
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
***************************************************************************************************************************************


sidebar*******************************************************************************************************************************
	* {
	    margin: 0;
	   }
	   nav{
	    height:70px;
	    background-color: green;
	   }
	   .container{
	    display: flex;
	    height: calc(100vh - 70px);
	   }
	   .container .main-body{
	    width: 100%;
	   }
	   .container aside{
	     width: 0;
	     overflow-y: auto;
	     transition: all 0.5s;
	     background-color: yellow;
	   }
	   /*Im using hover event to expand sidebar for example, you can use JS to handle click event*/
	   .container:hover aside{
	     width: 200px;
	   }
**************************************************************************************************************************************


***********************************************Angular test***************************************************************************
	ng test
	ng test --code-coverage
	esto carga jazmin

		* describe
		* it  
		* beforeEach
			beforeEach( () => jugador = new Jugador2() );
		* expect 
		* toBe
			expect( nuevoHP ).toBe(0);
		* toBeTruthy
			expect( control.valid ).toBeTruthy();
		* toBeFalsy
			expect( control.valid ).toBeFalsy();
		* toBeGreaterThan
			expect( componente.medicos.length ).toBeGreaterThan(0);  
		* spyOn
			spyOn( servicio, 'getMedicos').and.callFake(() =>{ return Observable.from([medicos]); } );
			spyOn( servicio, 'getMedicos').and.returnValue(() =>{ return Observable.from([medico]); } );
			spyOn( servicio, 'getMedicos').and.returnValue(() =>{ return Observable.throw(myError); } );
			spyOn( window, 'confirm').and.returnValue(true );
		* toHaveBeenCalled
			expect( espia ).toHaveBeenCalled();
		* toHaveBeenCalledWith
			expect( espia ).toHaveBeenCalledWith('1');
		* toBeGreaterThanOrEqueal
			expect( componente.medicos.indexOf( medico ) ).toBeGreaterThanOrEqual(0);
		* toBeNull
			expect( debugElement ).not.toBeNull();

		* Integracion 
			* TestBed 
			* ComponentFixture

				  let component: IncrementadorComponent;
			    let fixture: ComponentFixture<IncrementadorComponent>;

			    beforeEach( () => {
			        TestBed.configureTestingModule({
			            declarations: [ IncrementadorComponent ],
			            imports: [ FormsModule ]
			        });

			        fixture = TestBed.createComponent(IncrementadorComponent);
			        component = fixture.componentInstance;

			    });
			* Obtiene elemento html, aplica deteccion de cambios, fixture.debugElement.query
			    it('Debe de mostrar la leyenda', () => {

			        component.leyenda = 'Progreso de carga';

			        fixture.detectChanges(); // disparar la detección de cambios

			        const elem: HTMLElement = fixture.debugElement.query( By.css('h3') ).nativeElement;

			        expect( elem.innerHTML ).toContain('Progreso de carga');

			    });

			* fixture para controlar la deteccion de cambios, fixture.whenStable, revisar valor de input , fixture.debugElement.query
					  it( 'Debe de mostrar en el input el valor del progreso', () => {

				        component.cambiarValor(5);
				        fixture.detectChanges();

				        fixture.whenStable().then( () => {

				            const input = fixture.debugElement.query( By.css('input') );
				            const elem = input.nativeElement;

				            console.log( elem );

				            expect( elem.value ).toBe( '55' );


				        });

				    });

			* Validar eventos de componentes html 
					    it( 'Debe de incrementar/decrementar en 5, con un click en el botón', () => {

					        const botones = fixture.debugElement.queryAll( By.css('.btn-primary') );

					        botones[0].triggerEventHandler('click', null);
					        expect( component.progreso ).toBe(45);

					        botones[1].triggerEventHandler('click', null);
					        expect( component.progreso ).toBe(50);


					    });


			* Validar valor de un input luego de un evento  
					    it( 'En el titulo del componente, debe de mostrar el progreso', () => {

				        const botones = fixture.debugElement.queryAll( By.css('.btn-primary') );
				        botones[0].triggerEventHandler('click', null);

				        fixture.detectChanges();

				        const elem: HTMLElement = fixture.debugElement.query( By.css('h3') ).nativeElement;

				        expect( elem.innerHTML ).toContain('45');
				    });



		* Integraciones 2 
			* Probando la existencia de una ruta 
				import { RUTAS } from './app.routes';
				import { MedicoComponent } from '../../intermedio2/medico/medico.component';

				describe( 'Rutas principales', () => {

				    it( 'Debe de existir la ruta /medico/:id', () => {

				        expect( RUTAS ).toContain({
				            path: 'medico/:id',
				            component: MedicoComponent
				        });


				    });

				});

			* Pruebas de un router-oulet, confirmar que exista  
				
						import { TestBed, async } from '@angular/core/testing';
						import { AppComponent } from './app.component';

						import { RouterTestingModule } from '@angular/router/testing';
						import { By } from '@angular/platform-browser';
						import { RouterOutlet, RouterLinkWithHref } from '@angular/router';
						import { NO_ERRORS_SCHEMA } from '@angular/core';

						describe('AppComponent', () => {

						  beforeEach(async(() => {
						    TestBed.configureTestingModule({
						      declarations: [
						        AppComponent
						      ],
						      //Se modifica el beforeEach, se le agrega en los imports el RouterTestingModule
						      imports: [
						        RouterTestingModule.withRoutes([])
						      ],
						      schemas: [ NO_ERRORS_SCHEMA ]
						    }).compileComponents();
						  }));

						  it('should create the app', async(() => {
						    const fixture = TestBed.createComponent(AppComponent);
						    const app = fixture.debugElement.componentInstance;
						    expect(app).toBeTruthy();
						  }));

						  it(`should have as title 'app'`, async(() => {
						    const fixture = TestBed.createComponent(AppComponent);
						    const app = fixture.debugElement.componentInstance;
						    expect(app.title).toEqual('app');
						  }));


						  it( 'Debe de tener un router-outlet', () => {
						  	// Se obtiene el fixture
						    const fixture = TestBed.createComponent(AppComponent);
						    // Se obtiene el RouterOulet
						    const debugElement = fixture.debugElement.query( By.directive( RouterOutlet ) );
						    // Se valida que no sea null
						    expect( debugElement ).not.toBeNull();

						  });


						});

			* Probar un routerLink   
					  it( 'Debe de tener un router-outlet', () => {

					    const fixture = TestBed.createComponent(AppComponent);

					    const elementos = fixture.debugElement.queryAll( By.directive( RouterLinkWithHref ) );
					    let existe = false;

					    for( const elem of elementos){
					    	if(elem.attributes['RouterLink'] === '/medicos'){
					    		existe = true;
					    		break;
					    	}
					    }
					    expect( existe ).toBeTruthy();

					  });
**************************************************************************************************************************************