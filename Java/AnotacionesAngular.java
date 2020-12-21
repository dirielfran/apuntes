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
	5.- Se modifica footer.component.html, se le agrega clase bootstrap para centrar TypeNotPresentException
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
	5.- Se crean animaciones, se modifica el archivo style.css
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
146.- 



kioscoApp******************************************************************************************************************************
	creacion de log
	mensaje de error al eliminar inventario
	manejo de excepcion en caso de intentar facturar un producto sin existencia
	icluir iconos
	colocar iconos a todos los botones
***************************************************************************************************************************************







Complementarios*****************************************************************************************************************
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
	ElementRef**************************************************************************************************************
		Es simplemente una clase que envuelve elementos DOM nativos en el navegador y le permite trabajar con DOM 
		proporcionando el nativeElementobjeto que expone todos los métodos y propiedades de los elementos nativos.
		Ejemplo------------------------------------------------------------------------------------------------------
			import { Directive, ElementRef, OnInit } from '@angular/core';

			@Directive({
			  selector: '[appMakered]',
			})
			export class MakeredDirective {
			  constructor(
			    private elementRef: ElementRef
			  ) { }

			  ngOnInit() {
			    this.elementRef.nativeElement.style.backgroundColor = 'red';
			  }
			}
			----------------------------------------------------------------------------------------------------
			import { Component, ViewChild, ElementRef, AfterViewInit } from '@angular/core';

			@Co
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
********************************************************************************************************************************













