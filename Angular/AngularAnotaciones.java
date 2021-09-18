********************************************************Primera clase 18/07/2020
https://danielsanchez.com.ar/CursoAngular/CursoSabado/


Angular 8
Nos permite ejecutar javascript fuera del navagador
lenguaje typescript
MVC
***Se necesita:
*Descarga de nodejs.org
*instalar npm
	Dependecias
		ej moments --> fechas y horas
*angular cli
	npm install -g @angular/cli
	ng --version
	ng new nombreProyecto
	cd nombreProyecto
	ng serve  --> desplega servidor interno
*Intalar consola cmdern
*Visual Studio Code

***En consola
	ng new nombre del proyecto
	-->n
	-->ccs
	accedo a la carpeta del proyecto
	+Nota: node_modules > donde se incorporan las dependencias
	code .  --> para abrir visual code
	*desde terminal de vc --> ng serve -o  (Lanza una server de desarrollo)
	*si lo quieres abrir en otro navegadorcode .
	localhost:4200

*packege.json --> configuracion del proyecto y sus dependencias
*package-lock.json  --> las despendencias de cuales dependen
*node-modules --> contiene todas las dependencias
*tsconfig --> configuraciones distintas de typescript
*kasma.config.js  --> para hacer test
*angular.json --> config del proyecto


*Crear nuevos componentes
ng g c componentes/binding
					estructura
					atributos
					formulario


*Componentes, modulos, servicio
Los módulos, componentes y servicios son clases que usan decoradores . 
Estos decoradores marcan su tipo y proporcionan metadatos que le indican a 
Angular cómo usarlos.

Los metadatos para una clase de componente lo asocian con una plantilla que 
define una vista. Una plantilla combina HTML ordinario con directivas angulares 
y marcas de enlace que permiten a Angular modificar el HTML antes de mostrarlo.

Los metadatos para una clase de servicio proporcionan la información que Angular 
necesita para que esté disponible para los componentes a través de la inyección 
de dependencia (DI) .

***Modulos
 Un NgModule declara un contexto de compilación para un conjunto de componentes 
 que está dedicado a un dominio de aplicación, un flujo de trabajo o un conjunto 
 de capacidades estrechamente relacionadas. Un NgModule puede asociar sus componentes 
 con código relacionado, como servicios, para formar unidades funcionales.

Cada aplicación Angular tiene un módulo raíz , denominado convencionalmente AppModule, 
que proporciona el mecanismo de arranque que inicia la aplicación. Una aplicación 
generalmente contiene muchos módulos funcionales.

***Componentes
	Cada aplicación angular tiene al menos un componente, el componente raíz que 
	conecta una jerarquía de componentes con el modelo de objeto de documento de 
	página (DOM). Cada componente define una clase que contiene datos y lógica de 
	la aplicación, y está asociada con una plantilla HTML que define una vista que 
	se mostrará en un entorno de destino.

	El decorador identifica la clase inmediatamente debajo de ella como un componente 
	y proporciona la plantilla y los metadatos específicos de los componentes 
	relacionados.@Component()

	Componentes
	Los componentes definen áreas de responsabilidad en la interfaz de usuario, o UI, 
	que le permiten reutilizar conjuntos de funciones de UI. Ya ha creado uno con el 
	componente de la lista de productos.

	Un componente consta de tres cosas:

		+Una clase de componente que maneja datos y funcionalidad. 
		+Una plantilla HTML que determina la interfaz de usuario. 
		+Estilos específicos de componentes que definen la apariencia. 

	Una aplicación angular comprende un árbol de componentes, en el que cada componente 
	angular tiene un propósito y una responsabilidad específicos.

***Los decoradores 
son funciones que modifican las clases de JavaScript. Angular define una serie 
de decoradores que adjuntan tipos específicos de metadatos a las clases, para 
que el sistema sepa qué significan esas clases y cómo deberían funcionar.

***Enlace de datos
	La {{hero.name}} interpolación muestra el hero.namevalor de propiedad del 
	componente dentro del <li>elemento.
		<li>{{hero.name}}</li>
	El [hero] enlace de propiedad pasa el valor de selectedHerodel padre 
	HeroListComponenta la heropropiedad del niño HeroDetailComponent.
		<app-hero-detail [hero]="selectedHero"></app-hero-detail>
	El (click) enlace de evento llama al selectHerométodo del componente cuando 
	el usuario hace clic en el nombre de un héroe.
		<li (click)="selectHero(hero)"></li>

El enlace de datos bidireccional (utilizado principalmente en formularios basados 
en plantillas ) combina el enlace de propiedad y evento en una sola notación. 
Aquí hay un ejemplo de la HeroDetailComponentplantilla que usa enlace de datos 
bidireccional con la ngModeldirectiva.
		<input [(ngModel)]="hero.name">

En el enlace bidireccional, un valor de propiedad de datos fluye al cuadro de 
entrada desde el componente como con el enlace de propiedad. Los cambios del 
usuario también vuelven al componente, restableciendo la propiedad al último 
valor, como con el enlace de eventos.

Ejemplo----------------------------------------------------------------------
https://stackblitz.com/edit/angular-qribvc?file=src%2Fapp%2Fproduct-list%2Fproduct-list.component.html

*ngFores una "directiva estructural". Las directivas estructurales dan forma 
o remodelan la estructura del DOM, generalmente agregando, eliminando y 
manipulando los elementos a los que están unidos. Las directivas con un 
asterisco *son directivas estructurales.
	<h2>Products</h2>

	<div *ngFor="let product of products">

	  <h3>
	    <a [title]="product.name + ' details'">
	      {{ product.name }}
	    </a>
	  </h3>

	</div>

*ngIf<p>------------------------------------------------------------------
	En el <p>elemento, use una directiva para que Angular solo cree el 
	elemento si el producto actual tiene una descripción.*ngIf<p>
		<div *ngFor="let product of products">
		  <h3><a [title]="product.name + ' details'">{{product.name}}</a></h3>
		  <p *ngIf="product.description">
		    Descripcion: {{product.description}}
		  </p>
		</div>

--------------------------------------------------------------------------
Agregue un botón para que los usuarios puedan compartir un producto con amigos. 
Vincula el clickevento del botón al share()método (in product-list.component.ts). 
El enlace de eventos utiliza un conjunto de paréntesis, ( )alrededor del evento, 
como en el siguiente <button>elemento:

	<h2>Products</h2>

	<div *ngFor="let product of products">

	  <h3>
	    <a [title]="product.name + ' details'">
	      {{ product.name }}
	    </a>
	  </h3>

	  <p *ngIf="product.description">
	    Description: {{ product.description }}
	  </p>

	  <button (click)="share()">
	    Share
	  </button>

	</div>

--------------------------------------------------------------------------------
La aplicación ahora tiene una lista de productos y una función para compartir. 
En el proceso, aprendió a usar cinco características comunes de la sintaxis de 
plantilla de Angular:

*ngFor
*ngIf
Interpolación {{ }}
Enlace de propiedad [ ]
Enlace de eventos ( )
--------------------------------------------------------------------------------
El siguiente paso es crear una nueva función de alerta que tome un producto como 
entrada. La alerta verifica el precio del producto y, si el precio es superior a 
$ 700, muestra un botón "Notificarme" que permite a los usuarios registrarse para 
recibir notificaciones cuando el producto sale a la venta.
1.- Cree un nuevo componente de alertas de productos.

	1.1.- Haga clic derecho en la appcarpeta y use Angular Generatorpara generar un nuevo 
	componente llamado product-alerts.
2.- Abierto product-alerts.component.ts.
3.- Configure el nuevo componente de alertas de productos para recibir un producto como entrada:
	3.1.-Importar Inputdesde @angular/core.
	3.2.- En la ProductAlertsComponentdefinición de clase, defina una propiedad nombrada 
	productcon un decorador. El decorador indica que el valor de la propiedad pasa del 
	componente principal del componente, el componente de la lista de productos.@Input()@Input()

		import { Component, OnInit } from '@angular/core';
		import { Input } from '@angular/core';

		@Component({
		  selector: 'app-product-alerts',
		  templateUrl: './product-alerts.component.html',
		  styleUrls: ['./product-alerts.component.css']
		})
		export class ProductAlertsComponent implements OnInit {
		  @Input()  product;
		  constructor() { }

		  ngOnInit() {
		  }

		}
4.-Defina la vista para el nuevo componente de alerta de producto.

	4.1.-Abra la product-alerts.component.htmlplantilla y reemplace 
	el párrafo del marcador de posición con un botón "Notificarme" que 
	aparece si el precio del producto es superior a $ 700.
		<p *ngIf="product.price > 700">
		    <button>Notificame</button>
		</p>

5.- Visualice el nuevo componente de alerta de producto como elemento 
secundario de la lista de productos.

	5.1.- Abierto product-list.component.html.
	5.2.- Para incluir el nuevo componente, use su selector app-product-alerts, 
	como lo haría con un elemento HTML.
	5.3.- Pase el producto actual como entrada al componente utilizando 
	el enlace de propiedad.

	<app-product-alerts	  [product]="product">
	</app-product-alerts>

6.- Para que el botón "Notificarme" funcione, debe configurar dos cosas:

	el componente de alerta del producto para emitir un evento cuando el 
	usuario hace clic en "Notificarme"
	el componente de la lista de productos para actuar en ese evento

	6.1.- Abierto product-alerts.component.ts.
	6.2.- Importar Outputy EventEmitter desde @angular/core:
		import { Output, EventEmitter} from '@angular/core';
	6.3.- En la clase de componente, defina una propiedad nombrada notify
	con un decorador y una instancia de . Esto permite que el componente de 
	alerta del producto emita un evento cuando cambia el valor de la propiedad 
	de notificación.@Output()EventEmitter()
		export class ProductAlertsComponent {
		  @Input() product;
		  @Output() notify = new EventEmitter();
		}
	6.4.- En la plantilla de alerta del producto product-alerts.component.html, 
	actualice el botón "Notificarme" con un enlace de evento para llamar al 
	notify.emit()método.

		<p *ngIf="product.price > 700">
		    <button (click) = "notify.emit()">Notificame</button>
		</p>
	6.5.- A continuación, defina el comportamiento que debería ocurrir cuando 
	el usuario hace clic en el botón. Recuerde que es el componente primario 
	de la lista de productos, no el componente de alertas del producto, el 
	que actúa cuando el elemento secundario genera el evento. 
	En product-list.component.ts, defina un onNotify()método, similar 
	al share()método:
		export class ProductListComponent {
		  products = products;

		  share() {
		    window.alert('The product has been shared!');
		  }

		  onNotify() {
		    window.alert('You will be notified when the product goes on sale');
		  }
		}
	6.6.- Finalmente, actualice el componente de la lista de productos para 
	recibir resultados del componente de alertas del producto.

	En product-list.component.html, enlace el app-product-alertscomponente 
	(que es lo que muestra el botón "Notificarme") al onNotify()método del 
	componente de la lista de productos.

		<h2>Products</h2>

		<div *ngFor="let product of products">
		  <h3><a [title]="product.name + ' details'">{{product.name}}</a></h3>
		  <p *ngIf="product.description">
		    Descripcion: {{product.description}}
		  </p>
		  <app-product-alerts  [product]="product"   (notify)="onNotify()">
		  </app-product-alerts>
		  <button (click)="share()">Share</button>
		</div>

------------------------------------------------------------------------------
Registrar una ruta
La aplicación ya está configurada para usar Angular Routery usar enrutamiento 
para navegar al componente de la lista de productos que modificó anteriormente. 
Esta sección le muestra cómo definir una ruta para mostrar detalles de productos 
individuales.
	1.-Genere un nuevo componente para los detalles del producto. Dale al 
	componente el nombre product-details.

		Recordatorio: en la lista de archivos, haga clic con el botón derecho 
		en la appcarpeta, elija Angular Generatory Component.

	2.-En app.module.ts, agregue una ruta para los detalles del producto, con 
	un pathde products/:productIdy ProductDetailsComponentpara el component.

		@NgModule({
		  imports: [
		    BrowserModule,
		    ReactiveFormsModule,
		    RouterModule.forRoot([
		      { path: '', component: ProductListComponent },
		      { path: 'products/:productId', component: ProductDetailsComponent },
		    ])
		  ],

		Nota: Una ruta asocia una o más rutas URL con un componente.

	3.- La directiva configura la plantilla del componente para definir cómo navega 
	el usuario a la ruta o URL. Cuando el usuario hace clic en el nombre de un producto, 
	la aplicación muestra los detalles de ese producto.

		3.1.- Abierto product-list.component.html.

		3.2.- Actualice la directiva para asignar cada índice de la matriz a la 
		variable al iterar sobre la lista.*ngForproductsproductId

		3.3.- Modifique el ancla del nombre del producto para incluir a routerLink.

		<h2>Products</h2>

		<div *ngFor="let product of products; index as productId">
		  <h3><a [title]="product.name + ' details'" [routerLink]="['/products', productId]">{{product.name}}</a></h3>
		  <p *ngIf="product.description">
		    Descripcion: {{product.description}}
		  </p>
		  <app-product-alerts  [product]="product"   (notify)="onNotify()">
		  </app-product-alerts>
		  <button (click)="share()">Share</button>
		</div>

	4.- Pruebe el enrutador haciendo clic en el nombre de un producto. La aplicación 
	muestra el componente de detalles del producto, que actualmente siempre dice 
	"¡los detalles del producto funcionan!"

		Observe que la URL en la ventana de vista previa cambia. El segmento final 
		es products/# dónde #está el número de la ruta en la que hizo clic.
		La directiva RouterLink le da al enrutador control sobre el elemento de 
		anclaje. En este caso, la ruta, o URL, contiene un segmento fijo /products, 
		mientras que el segmento final es variable, insertando la propiedad id del 
		producto actual. Por ejemplo, la URL de un producto con un id1 será similar 
		a https://getting-started-myfork.stackblitz.io/products/1.

-----------------------------------------------------------------------------------------
Usar información de ruta
El componente de detalles del producto maneja la visualización de cada producto. El 
enrutador angular muestra componentes basados ​​en la URL del navegador y sus rutas definidas. 
Esta sección le muestra cómo usar el enrutador angular para combinar los productsdatos y 
la información de ruta para mostrar los detalles específicos de cada producto.

	1.-Abierto product-details.component.ts

	2.- Organizar el uso de datos del producto desde un archivo externo.

		2.1.- Importar ActivatedRoutedesde el @angular/router paquete y la products matriz desde ../products.
			import { Component, OnInit } from '@angular/core';
			import { ActivatedRoute } from '@angular/core';
			import { products } from '../products';

		2.2.-Defina la product propiedad e inyecte el ActivatedRouteen el constructor 
		agregándolo como argumento dentro de los paréntesis del constructor.


		El ActivatedRoutees específico para cada componente enrutado que carga el 
		enrutador angular. Contiene información sobre la ruta, sus parámetros y 
		datos adicionales asociados con la ruta.

Al inyectar ActivatedRoute, está configurando el componente para usar un servicio . La página Gestión de datos cubre los servicios con más detalle.