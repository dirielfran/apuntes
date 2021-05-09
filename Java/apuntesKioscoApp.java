*****************************************************************************************************Requerimiento Retiro de caja
	Modificaciones Back***************************************************************************************************
		***Cliente.java
			*Se crea tributo con relacion OneToMany
				@JsonIgnoreProperties(value= {"cliente"}, allowSetters = true)
				@OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true )
				private List<RetiroCaja> retirosCaja;

			*Se crea objeto ArrayList
				public Cliente() {
					this.facturas = new ArrayList<>();
					this.retirosCaja = new ArrayList<>();
				}
			*Se crea get y set 
					public List<RetiroCaja> getRetirosCaja() {
						return retirosCaja;
					}
					
					//Se utiliza el metodo addPregunta para añadir la lsita de preguntas y seteo de examen
					public void setRetirosCaja(List<RetiroCaja> retiros) {
						this.retirosCaja.clear();
						retiros.forEach(this::addRetiro);
					}
			*Se crea metodos para añadir y eliminar retiros a un cliente
					public void addRetiro(RetiroCaja retiro ) {
						this.retirosCaja.add(retiro);
						//relacion inversa
						retiro.setCliente(this);
					}
					
					public void deleteRetiro(RetiroCaja retiro) {
						this.retirosCaja.remove(retiro);
						//Se estable relacion inversa al setearle null se elimana por la 
						//propiedad config. orphanRemoval = true
						retiro.setCliente(null);
					}
		***Caja.java
			* Se crea atributo
				@JsonIgnoreProperties(value= {"caja"}, allowSetters = true)
				@OneToMany(mappedBy = "caja",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true )
				private List<RetiroCaja> retirosCaja;
			* Se crea onj ArrayList en el constructor
				public Caja() {
					this.facturas = new ArrayList<>();
					this.retirosCaja = new ArrayList<>();
				}	
			* Se crea get y set
				public List<RetiroCaja> getRetiroscaja() {
					return retirosCaja;
				}
				
				//Se utiliza el metodo addretiro para añadir la lista de retiros y seteo de Caja
				public void setRetiroscaja(List<RetiroCaja> retiros) {
					this.retirosCaja.clear();
					retiros.forEach(this::addRetiro);
				}
			* Se crea metodos para añadir y eliminar retiros en caja
				public void addRetiro(RetiroCaja retiro ) {
					this.retirosCaja.add(retiro);
					//relacion inversa
					retiro.setCaja(this);
				}
				
				public void deleteRetiro(RetiroCaja retiro) {
					this.retirosCaja.remove(retiro);
					//Se estable relacion inversa al setearle null se elimana por la 
					//propiedad config. orphanRemoval = true
					retiro.setCaja(null);
				}
		***RetiroCaja.java
			***Se crean atributos cliente y caja
				@JsonIgnoreProperties(value = {"retirosCaja"})
				@ManyToOne(fetch = FetchType.LAZY)
				@JsonBackReference(value = "cliente")
				@JoinColumn(name = "cliente_id")
				private Cliente cliente;

				@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
				@ManyToOne(fetch = FetchType.LAZY)
				@JsonBackReference(value = "caja")
				@JoinColumn(name = "caja_id")
				private Caja caja;

			*** Get y set
				public Cliente getCliente() {
					return cliente;
				}

				public void setCliente(Cliente cliente) {
					this.cliente = cliente;
				}

				public Caja getCaja() {
					return caja;
				}

				public void setCaja(Caja caja) {
					this.caja = caja;
				}
		
		
			**Se sobreescribe el metodo equals, el cual se utiliza para remover el obj
				@Override
				public boolean equals(Object obj) {
					if(obj == this) {
						return true;
					}
					
					if (!(obj instanceof RetiroCaja)) {
						return false;
					}
					RetiroCaja retiro = (RetiroCaja) obj;
					return this.id != null && this.id.equals(retiro.id);
				}
		***CajaController.java
			//Metodo de cierre de una caja
			@Secured({"ROLE_ADMIN", "ROLE_USER"})
			@GetMapping("/caja/cerrarcaja")
			public Integer cerrarCaja(){
				//Se busca la caja en base de datos con estatus Abierto
				Caja caja = cajaService.buscarXEstado("Abierto");
				Double venta = 0D;
				Double mercadopago = 0D;
				//Se obtiene el cliente de la caja
				Cliente cliente = caja.getCliente();
				//Se recorre las facturas del cliente
				for (Factura item : cliente.getFacturas()) {
					item.setCaja(caja);
					//El cliente deja de pertenecer a la caja
					item.setCliente(null);
					venta += item.getTotal();
					mercadopago += item.getMercadopago() != null ? item.getMercadopago(): 0D;
					facturaService.modificoFactura(item);
				}
				//Proceso para el registro de retiros en caja
				Double totalRetiros = 0D;
				//Se recorren los retiros del cliente
				for (RetiroCaja retiro : cliente.getRetirosCaja()) {
					//Se desvincula el retiro del cliente y se le setea la caja
					retiro.setCliente(null);
					retiro.setCaja(caja);
					retiroService.saveRetiroCaja(retiro);
					totalRetiros += retiro.getMonto();
				}		
				caja.setRetiros(totalRetiros);
				caja.setMercadopago(mercadopago);
				caja.setVenta(venta);
				caja.setEstado("Cerrado");
				caja.setFechaclose(new Date());
				cajaService.saveCaja(caja);
				return caja.getCliente().getId();
			}
	Modificaciones Frond**************************************************************************************************
		***retiro.component.html, se agrega select para tipo de retiro
			<div class="form-group row">
                <label for="region" class="col-form-label col-sm-2">Tipo:</label>
                <div class="col-sm-6">
                   <select class="form-control" [(ngModel)]="retiro.tipo" name="tipo">
                    <option >Recarga Sube</option>
                    <option >Retiro</option>
                  </select>
                </div>
            </div>

        *** Se modifica retiro.component.ts, se le agrega validacion para el monto de retiro
              create():void{
				    this.retiro.cliente = this.cliente;
				    //monto de efectivo
				    let efectivo: number = 0.0;
				    let retiros: number = 0.0;
				    this.cliente.facturas.forEach( factura => {
				      efectivo += (factura.total - factura.mercadopago);
				    });
				    this.cliente.retirosCaja.forEach( retiro => {
				      retiros += retiro.monto;
				    });
				    if (this.retiro.monto > (efectivo - retiros)){
				      swal.fire('Creacion de Retiro',
				                  `El monto de retiro no debe ser mayor al disponible `, 'error');
				    }else{
				      /* console.log(this.retiro); */
				      //Se llama al metodo de la clase de servicio
				      this.retiromodalService.createRetiro(this.retiro).subscribe(
				        json => {
				          this.router.navigate(['/clientes']);
				          this.cerrarRetiro();
				          swal.fire('Creacion de Retiro',
				                    `Retiro registrado con exito`, 'success');
				      });
				    }
				  }
		SQL*************************************************************************************************************
		alter table cajas add column retiros double default null;
*********************************************************************************************************************************En Produccion


*****************************************************************************************************Aumento de tiempo de session
	Backen****************************************************************************************************************
		***Se modifica el AuthorizationServerConfig.java, 
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
					.accessTokenValiditySeconds(43200) //12 horas
					//Tiempo de expiracion del refreshToken
					.refreshTokenValiditySeconds(2592000); //30 dias
				}
*********************************************************************************************************************************En Produccion


***************************************************************************************************************Ganancias por caja
	Backend***************************************************************************************************************
		*** Caja.java, se agrega atributo
			private Double ganancia;
		*** CajaServiceImpl.java, se modifica metodo saveCaja() para que setee la ganancia por caja al cerrar.
			@Override
			@Transactional()
			public Caja saveCaja(Caja caja) {
				Double ganancias = 0D;
				for (Factura factura : caja.getFacturas()) {
					for (ItemFactura item : factura.getItems()) {
						Double cantidad = item.getCantidad();
						Double precio = item.getPrecio();
						for (ItemInventario inventario : item.getItems_inventario()) {
							if(item.getItem_inventario() != null && item.getItem_inventario().getId() == inventario.getId()) {
								cantidad -= item.getCantinv();
								ganancias += item.getCantinv() * (precio - inventario.getPreciocompra());
							}else {
								ganancias += cantidad * (precio - inventario.getPreciocompra());
							}					
						}				
						ganancias += item.getComision();
					}
				}
				caja.setGanancia(ganancias);
				return cajaRepo.save(caja);
			}
	FrontEnd**************************************************************************************************************
		***Caja.ts
			ganancia: number;
		***caja.component.html
			<th>Ganancia</th>
			<td style="color: darkorchid">{{caja.ganancia | number}}</td>
	sql*******************************************************************************************************************
		 alter table cajas change `ganancias`^Cganancia` double default null;

*********************************************************************************************************************************En Produccion


*****************************************************************************************************************Modulo de Gastos
	Backend***************************************************************************************************************	
		***Se creo tabla en base de datos
			CREATE TABLE `gastos` (
			  `id` int(11) NOT NULL AUTO_INCREMENT,
			  `montoPesos` double NOT NULL,
			  `descripcion` varchar(250) NOT NULL,
			  `fechaFact` date default NULL,
			  `fechaCarga` date default NULL,
			  `Usuario` varchar(45) NOT NULL,
			  `proveedor` varchar(45) default NULL,
			  `imagen` varchar(45) default NULL,
			  `clasificacion` varchar(45) NOT NULL,
			  `montodolar` double default NULL,
			  `tasadolar` double default NULL,
			  `inventario_id` bigint(20) default NULL,
			  PRIMARY KEY (`id`),
			  UNIQUE KEY `id_UNIQUE` (`id`),
			  KEY `FKgastoinventario` (`inventario_id`),
			  CONSTRAINT `FKgastosinventario` FOREIGN KEY (`inventario_id`) REFERENCES `inventarios` (`id`)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;
		*** Se modifica Gastos.java, se agrega atributo con su get y set
			@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
			@OneToOne
			@JoinColumn(name="inventario_id")
			private Inventario inventario;
		*** Se modifica InventarioController.java, en la creacion de inventario genera gasto
			*** Se modifica metodo saveInventario()
				//Metodo para guardar inventario
				@Secured({"ROLE_ADMIN", "ROLE_USER"})
				@PostMapping("/inventarios")
				public ResponseEntity<?> saveInventario(@Valid @RequestBody Inventario inventario, BindingResult result,
														@RequestParam(value = "user") String user){
					Inventario inventarioNew = null;
					Map<String, Object> response = new HashMap<>();
					//Se validan errores del requestbody
					if(result.hasErrors()) {
						List<String> errores = result.getFieldErrors()
							//Se convierte en stream
							.stream()
							//Cada error se convierte en un string
							.map(error -> "El campo '"+error.getField()+"' "+error.getDefaultMessage())
							//Se convierte en una lista
							.collect(Collectors.toList());
						response.put("errores", errores);
						return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
					}
					
					try {
						//Se recupera el usuario que recupero del get
						inventario.setUsuario(usuarioService.findByUsername(user));
						//Se recorren los items del inventario
						for (ItemInventario item : inventario.getItems()) {
							//A la existencia actual se le suma la existencia del producto mas lo que se añade del inventario
							Double existenciaActual = item.getProducto().getExistencia()+item.getStockadd();
							//Se setea al stockinicial del inventario la existencia del producto
							item.setStockinicial(item.getProducto().getExistencia());
							item.setExistencia(item.getStockadd());
							//Se modifica existencia y precio del producto segun el inventario cargado
							Producto producto = item.getProducto();
							producto.setExistencia(existenciaActual);
							producto.setPrecio(item.getPrecioventa());
							productoService.saveProducto(producto);
						}
						//Se añade gasto
						Gastos gasto = new Gastos();
						gasto.setMontoPesos(inventario.getTotal());
						gasto.setDescripcion(inventario.getDescripcion());
						gasto.setFechaFact(inventario.getFechacreate());
						gasto.setUsuario(inventario.getUsuario().getUsername());
						gasto.setProveedor(inventario.getProveedor());
						gasto.setClasificacion("Inventario");
						gasto.setInventario(inventario);
						//Se guarda el inventario
						inventarioNew = inventarioService.save(inventario);
						//Se crea el gasto
						gastosService.guardar(gasto);
					} catch (DataAccessException e) {
						response.put("mensaje", "Error al insertar el inventario.");
						response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
						System.out.println(e.getMostSpecificCause().getMessage());
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					response.put("mensaje","El inventario se ha registrado.");
					response.put("inventario", inventarioNew);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}
			*** Se modifica metodo 
				Gastos gasto = gastosService.buscarPorInventario(idInventario);
				gastosService.borrarGasto(gasto.getId());
		*** Se modifica GastosRepository.java
			@Query("select g from Gastos g where inventario.id = ?1")
			public Gastos findByInventario(Long inventario);
		*** IGastosService.java
			Gastos buscarPorInventario(Long inventario);
		*** GastosServiceImpl.java
			@Override
			public Gastos buscarPorInventario(Long inventario) {
				return repoGastos.findByInventario(inventario);
			}
		*** Se modifica GastosController.java
			*** Se agrega metodo para recuperar el pageable de gastos
				@GetMapping("/gastos/page/{page}")
				public Page<Gastos> indicePaginado(@PathVariable("page") Integer pagina) {
					Pageable page = PageRequest.of(pagina, 5);
					// se llama al metodo de la implementacion
					return serviceGastos.getGastos(page);
				}
			*** Se crea metodo para guardar gasto
				//Se crea Cliente
				//Se le añade seguridad a los endpoint por url
				@Secured("ROLE_ADMIN")
				@PostMapping("/gastos")
				public ResponseEntity<?> saveGastos(@Valid @RequestBody Gastos gasto, BindingResult result) {
					Gastos gastoNew = null;
					//Se agrega map para el envio de mensaje y obj en el response
					Map<String, Object> response = new HashMap<>();
					
					//Validacion de errores del binding result
					if(result.hasErrors()) {
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
					//Manejo de errores utilizando el obj de spring DataAccessException
					try {
						//Se crea Gasto por medio de la clase de servicio
						gastoNew = serviceGastos.guardar(gasto);
					} catch (DataAccessException e) {
						//Se crean mensajes de error y se añaden all map
						response.put("mensaje", "Error al insertar Gasto en la Base de Datos");
						response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
						//Se crea respuesta enviando los mensajes del error y el estatus
						return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
					}
					//Se agrega mensaje success al mapa
					response.put("mensaje", "El Gasto se ha registrado");
					//Se agrega obj cliente creado al mapa
					response.put("gasto", gastoNew);
					envioMail(gastoNew, "registrado");
					//Se retorna respuesta añadiendo mapa y estatus
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
				}
			*** Se crea metodo para eliminar gastos
				//delete de Gasto
				//Se le añade seguridad a los endpoint por url
				@Secured("ROLE_ADMIN")
				@DeleteMapping("/gastos/{id}")
				public ResponseEntity<?> delete(@PathVariable Integer id) {
					Map<String, Object> response = new HashMap<>();
					try {
						Gastos gasto = serviceGastos.buscarPorId(id);
						serviceGastos.borrarGasto(id);
					} catch (DataAccessException e) {
						//Se crean mensajes de error y se añaden all map
						response.put("mensaje", "Error al eliminar el gato con ID ".concat(id.toString()).concat(" en la Base de Datos"));
						response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
						//Se crea respuesta enviando los mensajes del error y el estatus
						return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
					}
					
					response.put("mensaje", "El Gasto ha sido eliminado con exito");
					
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}
			*** Se crea metodo para el envio de mail
				private void envioMail(Gastos gasto, String tipo) {
					//Se envia email por modificacion del producto
					EnvioMail enviomail = new EnvioMail();
					String body ="Se ha "+tipo+" un gasto con los siguientes detalles: <br> \n"
							+ "Id: "+gasto.getId()+"\n"
							+ "Fecha: "+gasto.getFechaFact()+"\n"
							+ "Precio: "+gasto.getUsuario()+"\n"
							+ "Proveedor: "+gasto.getClasificacion()+"\n";
					String subject="El gasto "+gasto.getId()+" se ha "+tipo+" con exito.";
					//enviomail.sendEmail("dirielfran@gmail.com", subject, body);
					
					SimpleMailMessage email = new SimpleMailMessage();
			        
			        //recorremos la lista y enviamos a cada cliente el mismo correo
			        email.setTo("dirielfran@gmail.com");
			        email.setSubject(subject);
			        email.setText(body);
			        
			        mailSender.send(email);
				}
			*** Se crea metodo para recuperar gasto por id
				//Se recupera gasto por id
				@Secured({"ROLE_ADMIN", "ROLE_USER"})
				@GetMapping("/gastos/{id}")
				public ResponseEntity<?> showGasto(@PathVariable Long id) {
					Gastos gasto = null;
					//Se crea Map para el envio de mensaje de error en el ResponseEntity
					Map<String, Object> response = new HashMap<>();
					//Se maneja el error de base datos con el obj de spring DataAccessExceptions
					try {
						//Se recupera el inventario por el id
						gasto = serviceGastos.buscarPorId(id);
					} catch (DataAccessException e) {
						response.put("mensaje", "Error a realizar la consulta en la base de Datos.");
						response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					//Se valida si el inventario es null y se maneja el error
					if(gasto == null) {
						response.put("mensaje", "El gasto ID: ".concat(id.toString().concat(" no existe en la Base de Datos")));
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
					}
					//En caso de existir se retorna el obj y el estatus del mensaje
					return new ResponseEntity<Gastos>(gasto, HttpStatus.OK);
				}
	FrontEnd*************************************************************************************************************
		*** Se crea componente gastos 
			ng g c components/gastos
		*** Se crea clase de servicio
			ng g s components/gastos/gastos
		*** Se crea clase 
			ng g class components/gastos/gastos
		*** Se modifca clase gastos.ts
			import { Inventario } from '../../inventarios/inventario';
			export class Gastos {
			  id : number;
			  montoPesos: number;
			  descripcion: string;
			  fechaFact: string;
				fechaCarga: string;
				usuario: string;
				proveedor: string;
				imagen: string= "+58kiosco.png";
				clasificacion: string;
				montoDolar: number;
				tasaDolar: number;
				inventario: Inventario;
			}		
		*** Se modifica gastos.service.ts
			import { Injectable } from '@angular/core';
			import { HttpClient } from '@angular/common/http';
			import { Router } from '@angular/router';
			import { map, catchError } from 'rxjs/operators';
			import { Observable, throwError } from 'rxjs';
			import { Gastos } from './gastos';

			@Injectable({
			  providedIn: 'root'
			})
			export class GastosService {

			    // Se alamacena en variable el endPoint

			    private urlEndPoint: string ='http://localhost:8080/api/gastos';
			    // private urlEndPoint: string ='http://66.228.61.76/springAngular/api/gastos';

			    constructor( private httpClient:HttpClient,
			      private router:Router) {  }


			    getGastos(page: number): Observable<any>{
			      return this.httpClient.get(this.urlEndPoint+'/page/'+page).pipe(
			        map( (response: any) => {
			          response.content as Gastos[];
			          return response;
			        } )
			      );
			    }


			  //Metodo delete de Gasto
			  delete(id:number): Observable<Gastos>{
			    console.log(id);
			    return this.httpClient.delete<Gastos>(`${this.urlEndPoint}/${id}`).pipe(
			      catchError( e => {
			        if(e.error.mensaje){
			          console.error(e.error.mensaje);
			        }
			        return throwError(e);
			      })
			    );
			  }

			   creategasto(gasto:Gastos, user:string): Observable<any>{
			    return this.httpClient.post<any>(this.urlEndPoint+'?user='+user, gasto).pipe(
			      // Se agrega el operador catchError, intercepta en caso de existir error
			      catchError(e => {
			        // Manejo de validacion que viene en el response del backend
			        if ( e.status === 400){
			          return throwError(e);
			        }
			        // Se muestra por consola el error
			        if (e.error.mensaje){
			          console.error(e.error.mensaje);
			        }
			        return throwError(e);
			      })
			    );
			  }
			}		
		*** Se modifica gastos.components.ts
			import { Component, OnInit } from '@angular/core';
			import { Gastos } from './gastos';
			import { GastosService } from './gastos.service';
			import { ActivatedRoute, Router } from '@angular/router';
			import { AuthService } from '../../usuarios/auth.service';
			import Swal from 'sweetalert2';

			@Component({
			  selector: 'app-gastos',
			  templateUrl: './gastos.component.html',
			  styleUrls: ['./gastos.component.css']
			})
			export class GastosComponent implements OnInit {

			  gastos: Gastos[];
			  //Atributo para la paginacion
			  paginador: any;

			  constructor(  private ActivatedRoute: ActivatedRoute,
			    private gastoService: GastosService,
			    private router:Router,
			    public authService:AuthService
			  ) { }

			   ngOnInit(): void {
			    this.ActivatedRoute.paramMap.subscribe( params => {
			      //Se agrega atributo que recupera la pagina dinamicamente
			      let pagina = +params.get('page');
			      if(!pagina){
			        pagina = 0;
			      }
			      this.gastoService.getGastos(pagina).subscribe(
			        response => {
			          this.gastos = (response.content as Gastos[]);
			          /* console.log(this.inventarios); */
			          //Se da valor al paginador
			          this.paginador = response;
			        }
			      )
			    })
			  }

			  delete(gasto: Gastos): void{
			    const swalWithBootstrapButtons = Swal.mixin({
			      customClass: {
			        confirmButton: 'btn btn-success',
			        cancelButton: 'btn btn-danger'
			      },
			      buttonsStyling: false
			    })

			    swalWithBootstrapButtons.fire({
			      title: 'Esta usted seguro?',
			      text: `Desea eliminar el gasto ${gasto.id}?`,
			      icon: 'warning',
			      showCancelButton: true,
			      confirmButtonText: 'Si, eliminar!',
			      cancelButtonText: 'No, cancelar!',
			      reverseButtons: true
			    }).then((result) => {
			      if (result.value) {
			        this.gastoService.delete(gasto.id).subscribe(
			          response => {
			            this.gastos = this.gastos.filter(item => item !== gasto);
			            swalWithBootstrapButtons.fire(
			              'Gasto Eliminado!',
			              `Gasto ${gasto.id} eliminado con exito.`,
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

			}
		*** Se modifica gastos.components.html
			<div class="card border-primary mb-3">
			    <div class="card-header">Gastos</div>
			    <div class="card-body text-primary">
			        <h3 class="card-title">Listado de Gastos</h3>
			        <div class="my-2 text-left">
			            <!--[routerLink] Nos permite crear rutas nternas del proyecto-->
			            <button *ngIf="authService.hasRole('ROLE_ADMIN')" class="btn btn-rounded btn-primary" type="buttom" [routerLink]="['/gastos/form']">Crear Gasto</button>
			        </div>
			        <!-- Se crea div informativo en caso de no haber clientes en la lista -->
			        <div class="alert alert-info" *ngIf="gastos?.length == 0">
			            No hay registros en la Base de Datos.
			        </div>
			        <div class="table-responsive">
			            <!-- Se agrega directiva ngIf para validar si la lista de clientes esta vacia -->
			            <table class="table table-striped" *ngIf="gastos?.length>0">
			                <thead>
			                    <tr>
			                        <th>Id</th>
			                        <th>Descripcion</th>
			                        <th>fecha</th>
			                        <th>Proveedor</th>
			                        <th>Usuario</th>
			                        <th>Clasificacion</th>
			                        <th>Total</th>
			                        <th *ngIf="authService.hasRole('ROLE_ADMIN')">Detalle</th>
			                        <th *ngIf="authService.hasRole('ROLE_ADMIN')">eliminar</th>
			                    </tr>
			                </thead>
			                <tbody>
			                    <!--Se añade condicional *ngFor-->
			                    <tr *ngFor="let gasto of gastos">
			                        <td>{{gasto.id}}</td>
			                        <td>{{gasto.descripcion}}</td>
			                        <td>{{gasto.fechaFact | date:'EEEE dd, MMMM yyyy'}}</td>
			                        <td>{{gasto.proveedor}}</td>
			                        <td>{{gasto.usuario}}</td>
			                        <td>{{gasto.clasificacion}}</td>
			                        <td>{{gasto.montoPesos | number:'.1-2'}}</td>
			                        <td *ngIf="authService.hasRole('ROLE_ADMIN')">
			                            <button class="btn btn-primary btn-sm" type="button" [routerLink]="['/gastos', gasto.id]">Ver</button>
			                        </td>
			                        <td *ngIf="authService.hasRole('ROLE_ADMIN')">
			                            <button class="btn btn-danger btn-sm" type="button" name="eliminar" (click)="delete(gasto)">Eliminar</button>
			                        </td>
			                    </tr>
			                </tbody>
			            </table>
			            <!-- Paginacion -->
			            <paginadorinv-nav *ngIf="paginador" [paginador]="paginador"></paginadorinv-nav>
			        </div>

			    </div>
		*** Se modfica app.routes.ts
			{path: 'gastos', component: GastosComponent},
			{path: 'gastos/page/:page', component: GastosComponent},
			{path: 'gastos/form', component: GastosformComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ROLE_ADMIN'}},
		*** Se modifica header.component.ts
			<li class="nav-item" routerLinkActive="active">
                <a class="nav-link" [routerLink]="['/gastos']">Gastos</a>
            </li>
        *** Se creo componente para el formulario
            ng g c components/gastos/form/gastosform 
        *** Se modifica gastosform.component.ts
            import { Component, OnInit } from '@angular/core';
			

		*** Se modifica gastosform.component.html
			<div class="card bg-light">
			    <div class="card-header">Nuevo Inventario</div>
			    <div class="card-body">
			        <div class="card-title">
			            <form #gastoForm="ngForm">
			                <div class="form-group row" *ngIf="usuario">
			                    <label for="cliente" class="col-sm-2 col-form-label">Usuario</label>
			                    <div class="col-sm-6">
			                        <input type="text" class="form-control" name="usuario" value="{{usuario}}" disabled>
			                    </div>
			                </div>

			                <div class="form-group row">
			                    <label for="descripcion" class="col-sm-2 col-form-label">Proveedor</label>
			                    <div class="col-sm-6">
			                        <input type="text" class="form-control" name="proveedor" [(ngModel)]="gasto.proveedor" #proveedor="ngModel">
			                        <!-- Se valida con ngIf si es invalido el contenido del input y si tipeas o dejas el foco -->
			                        <div class="alert alert-danger" *ngIf="proveedor.invalid && (proveedor.dirty || proveedor.touched)">
			                            <!-- Se agrega mensaje validando si en los errores esta el reuired -->
			                            <div *ngIf="proveedor.errors.required">
			                                Nombre es requerido.
			                            </div>
			                            <!-- Se agrega mensaje validando la longitud en los errores -->
			                            <div *ngIf="proveedor.errors.minlength">
			                                Nombre debe tener al menos 4 caracteres.
			                            </div>
			                        </div>
			                    </div>
			                </div>

			                <div class="form-group row">
			                    <label for="descripcion" class="col-sm-2 col-form-label">Descripcion</label>
			                    <div class="col-sm-6">
			                        <input type="text" class="form-control" name="descripcion" [(ngModel)]="gasto.descripcion" #descripcion="ngModel">
			                        <!-- Se valida con ngIf si es invalido el contenido del input y si tipeas o dejas el foco -->
			                        <div class="alert alert-danger" *ngIf="descripcion.invalid && (descripcion.dirty || descripcion.touched)">
			                            <!-- Se agrega mensaje validando si en los errores esta el reuired -->
			                            <div *ngIf="descripcion.errors.required">
			                                Nombre es requerido.
			                            </div>
			                            <!-- Se agrega mensaje validando la longitud en los errores -->
			                            <div *ngIf="descripcion.errors.minlength">
			                                Nombre debe tener al menos 4 caracteres.
			                            </div>
			                        </div>
			                    </div>
			                </div>

			                <div class="form-group row">
			                    <label for="createAt" class="col-form-label col-sm-2">Fecha:</label>
			                    <div class="col-sm-6">
			                        <input type="date" class="form-control" [(ngModel)]="gasto.fechaFact" name="fechafact" required style="width:250px">
			                    </div>
			                </div>

			               /* <div class="form-group row">
			                    <label for="monto" class="col-sm-2 col-form-label">Monto:</label>
			                    <div class="col-sm-6">
			                        <input type="number" class="form-control" name="monto" [(ngModel)]="gasto.montoPesos" #monto="ngModel" required>
			                    </div>
			                    <!-- el atributo touched valida si se hace click fuera del elemento -->
			                    <div class="alert alert-danger" *ngIf="monto.invalid && monto.touched || monto.invalid && gastoForm.submitted">
			                        El monto es requerido
			                    </div>
			                </div>
			                <div class="form-group row">
			                    <div class="col-sm-6">
			                        <input type="submit" value="Crear Gasto" (click)="crearGasto(gastoForm)" class="btn btn-secondary" [disabled]="!gastoForm.form.valid">
			                    </div>
			                </div>
			            </form>
			        </div>
			    </div>
			</div>*/
		*** Se crea componente para mostrar detalles del gasto
			ng g c components/gastos/detalle/gastosDetalle
		*** Se modifica gastos-detalle.component.ts
			import { Component, OnInit } from '@angular/core';
			import { Gastos } from '../../gastos';
			import { GastosService } from '../../gastos.service';
			import { ActivatedRoute } from '@angular/router';

			@Component({
			  selector: 'app-gastos-detalle',
			  templateUrl: './gastos-detalle.component.html',
			  styleUrls: ['./gastos-detalle.component.css']
			})
			export class GastosDetalleComponent implements OnInit {

			  gasto: Gastos;
			  titulo: string = 'Gasto';

			  constructor(private activatedRoute: ActivatedRoute,
			    private gastoService: GastosService) { }

			  ngOnInit(): void {
			    this.activatedRoute.paramMap.subscribe(params => {
			      let id = +params.get('id');
			      this.gastoService.getGasto(id).subscribe(gasto => this.gasto = gasto);
			    })
			  }
			}
		*** Se modifica gastos-detalle.component.html
			<div class="card bg-light" *ngIf="gasto">
			    <div class="card-header">{{titulo}}: {{gasto.descripcion}}</div>
			    <div class="card-body">
			        <h4 class="card-title">
			            <a [routerLink]="['/gastos']" class="btn btn-light btn-xs">&laquo; volver</a>
			        </h4>

			        <ul class="list-group my-2">
			            <li class="list-group-item list-group-item-primary">Detalle del gasto</li>
			            <li class="list-group-item ">Proveedor: {{gasto.proveedor}}</li>
			            <li class="list-group-item ">Fecha de Factura: {{gasto.fechaFact | date:'EEEE dd, MMMM yyyy'}}</li>
			            <li class="list-group-item ">Fecha de Modificacion: {{gasto.fechaCarga}}</li>
			            <li class="list-group-item ">Usuario: {{gasto.usuario}}</li>
			            <li class="list-group-item ">Monto del Gasto: {{gasto.montoPesos}}</li>
			        </ul>
			    </div>
			</div>
********************************************************************************************************************************* En Produccion



********************************************************************************************************************Envio de mail
	***ProductoController.java, se le añade envio de correo al editar producto.
			//Metodo para la modificacion de un producto
	@Secured("ROLE_ADMIN")
	@PutMapping("/producto/{id}")
	public ResponseEntity<?> updateProducto(@Valid @RequestBody Producto producto,
			BindingResult result,
			@PathVariable Long id){
		Producto productoUpd= null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errores = result.getFieldErrors()
					.stream().map(err->"El campo "+err.getField()+" "+err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		Producto productoAct = productoServ.getbyId(id);
		//Se valida si el producto existe
		if(productoAct== null) {
			response.put("mensaje", "El producto ID: ".concat(id.toString()).concat(" no existe en la Base de datos."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
		}
		try {
			//Se envia email por modificacion del producto
			EnvioMail enviomail = new EnvioMail();
			String body ="Se ha registrado un gasto con los siguientes detalles: <br> \n"
					+ "Nombre: "+producto.getNombre()+"\n"
					+ "Id: "+producto.getId()+"\n"
					+ "Fecha: "+producto.getCreateAt()+"\n"
					+ "Precio: "+producto.getPrecio()+"\n"
					+ "Proveedor: "+producto.getProveedor()+"\n"
					+ "Minimo: "+producto.getMinimo()+"\n";
			String subject="El producto "+producto.getNombre()+" se ha modificado con exito.";
			//enviomail.sendEmail("dirielfran@gmail.com", subject, body);
			
			SimpleMailMessage email = new SimpleMailMessage();
	        
	        //recorremos la lista y enviamos a cada cliente el mismo correo
	        email.setTo("dirielfran@gmail.com");
	        email.setSubject(subject);
	        email.setText(body);
	        
	        mailSender.send(email);	
			
			//Se modifican los atributos modificados y se guarda
			productoAct.setNombre(producto.getNombre());
			productoAct.setPrecio(producto.getPrecio());
			productoAct.setProveedor(producto.getProveedor());
			productoAct.setExistencia(producto.getExistencia());
			productoAct.setMinimo(producto.getMinimo());
			productoUpd = productoServ.saveProducto(productoAct);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al modificar el cliente con Id: ".concat(id.toString()).concat(" en la base de datos."));
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto se modifico con exito.");
		response.put("producto", productoUpd);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
********************************************************************************************************************************* Detalle en produccion



***************************************************************************************************Creacion de proceso Cajachica
	*** Se crea base de datos Cajachica
		CREATE TABLE `cajachica` (
		  `id` bigint(20) NOT NULL AUTO_INCREMENT,
		  `fecha` date not NULL,
		  `saldomp` double default NULL,
		  `saldoefectivo` double default NULL,
		  `monto` double default NULL,
		  `factura_id` bigint(20) default NULL,
		  `gasto_id` bigint(20) default NULL,
		  `caja_id` bigint(20) default NULL,
		  PRIMARY KEY (`id`),
		  UNIQUE KEY `id_UNIQUE` (`id`),
		  KEY `FKfactura` (`factura_id`),
		  KEY `FKgasto` (`gasto_id`),
		  KEY `FKgasto` (`caja_id`),
		  CONSTRAINT `FKgastocaja` FOREIGN KEY (`gasto_id`) REFERENCES `gastos` (`id`),
		  CONSTRAINT `FKdiferenciacaja` FOREIGN KEY (`caja_id`) REFERENCES `caja` (`id`),
		  CONSTRAINT `FKfacturacaja` FOREIGN KEY (`factura_id`) REFERENCES `facturas` (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	*** Se crea pojo Cajachica.java

		@Entity
		@Table(name = "cajachica")
		public class Cajachica implements Serializable{

			/**
			 * 
			 */
			private static final long serialVersionUID = -8134548246055327266L;

			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id;
			
			@Temporal(TemporalType.TIMESTAMP)
			private Date fecha;
			
			private Double saldomp;
			private Double saldoefectivo;
			private Double monto;
			
			@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
			@OneToOne
			@JoinColumn(name="factura_id")
			private Factura factura;
			
			
			@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
			@OneToOne
			@JoinColumn(name="gasto_id")
			private Gastos gasto;

			
			public Cajachica() {
				this.fecha = new Date();
			}
			
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public Date getFecha() {
				return fecha;
			}
			public void setFecha(Date fecha) {
				this.fecha = fecha;
			}
			public Double getSaldomp() {
				return saldomp;
			}
			public void setSaldomp(Double saldomp) {
				this.saldomp = saldomp;
			}
			public Double getSaldoefectivo() {
				return saldoefectivo;
			}
			public void setSaldoefectivo(Double saldoefectivo) {
				this.saldoefectivo = saldoefectivo;
			}
			public Double getMonto() {
				return monto;
			}
			public void setMonto(Double monto) {
				this.monto = monto;
			}
			public Factura getFactura() {
				return factura;
			}
			public void setFactura(Factura factura) {
				this.factura = factura;
			}
			public Gastos getGasto() {
				return gasto;
			}
			public void setGasto(Gastos gasto) {
				this.gasto = gasto;
			}

		}
	*** se crea repositorio 
		public interface ICajaChicaRepository extends CrudRepository<Cajachica, Long> {
	
			Cajachica findTopByOrderByIdDesc();
			
			@Query("select c from Cajachica c where factura.id = ?1")
			Cajachica findByfacturaId(Long idFactura);
			
			@Query("select c from Cajachica c where gasto.id = ?1")
			Cajachica findByGastoId(Long idGasto);

		}
	*** Se crea interface
		public interface ICajachicaService {
	
			public Cajachica findTopByOrderByIdDesc();
			public void registroCaja(Factura factura);
			public void deleteRegistroCaja(Factura factura);
			public Cajachica saveCaja(Cajachica caja);
			public Cajachica findById(Long id);
			public Cajachica findByFactura(Long id);
			public Cajachica findByGasto(Long id);
			public void deleteCaja(Long id);
			
		}

	*** Se modifica Gasto.java, se agrega atributo metodopago
		private String metodopago;

	*** Se modifica gastos.ts (FrontEnd)
	*** Se modifica gastosform.component.html (FrontEnd)
		 <div class="form-group row">
            <label for="metodopago" class="col-form-label col-sm-2">Tipo de Pago:</label>
            <div class="col-sm-6">
                <select class="form-control" [(ngModel)]="gasto.metodopago" name="metodopago">
              <option >MercadoPago</option>
              <option >Efectivo</option>
            </select>
            </div>
        </div>
    *** Se modifica Base de datos
        alter table gastos add column metodopago varchar(50) default null;********************************************************
        insert into cajachica(fecha,saldomp,saldoefectivo,monto) values('2020-12-27',1000,1000,0);********************************
        alter table inventarios add column metodopago varchar(50) default null;
********************************************************************************************************************************En Produccion


********************************************************Se intregra el Registro de la Factura en la Cajachica al cierre de cajas
	Modificacion__________________________________________________________________________________________________________
		*Se modifica facturaServiceImpl.java, se modifica el metodo saveFactura(), se comenta proceso que registra factura 
		en caja chica 
			//Se registra factura en la cajachica
			//cajaService.registroCaja(factura);
			***Se modifica metodo deleteFactura(), se comenta proceso de registro en cajachica
				//Se elimina registro de factura en caja
				//cajaService.deleteRegistroCaja(factura);
		*Se modifica CajaServiceImpl.java, se agrega registro de cajachica al cerrar caja
			*Se agrega inyeccion de dependencia al servicio de caja
				@Autowired
				private ICajachicaService cajaService;
			*Se modifica metodo saveCaja(), se agrega registro de facturas en cajachica
				@Override
				@Transactional()
				public Caja saveCaja(Caja caja) {
					Double ganancias = 0D;
					for (Factura factura : caja.getFacturas()) {
						for (ItemFactura item : factura.getItems()) {
							Double cantidad = item.getCantidad();
							Double precio = item.getPrecio();
							for (ItemInventario inventario : item.getItems_inventario()) {
								if(item.getItem_inventario() != null && item.getItem_inventario().getId() == inventario.getId()) {
									cantidad -= item.getCantinv();
									ganancias += item.getCantinv() * (precio - inventario.getPreciocompra());
								}else {
									ganancias += cantidad * (precio - inventario.getPreciocompra());
								}					
							}				
							ganancias += item.getComision();
						}
						//Se registra factura en la cajachica
						cajaService.registroCaja(factura);
					}
					caja.setGanancia(ganancias);
					return cajaRepo.save(caja);
				}
********************************************************************************************************************************En Produccion



*************************************************************************************************************Diferencias en Caja
********************************************************************************************************************************En Produccion




********************************************************************************************************Productos a consignacion
	Descripcion: Se requiere funcionalidad para registrar inventario en consignacion, marcar el inventario_item, al facturar 
	validar producto a consignacion si es a consicnacion marcar factura_item y crea formulario donde se rendericen y se puedan 
	pagar, solo las que esten marcadas como vendidas.

	colocar marca a inventario_item
	colocar marca a facturas_items
	alter table de facturas_items
	alter table de inventarios items
	alterar proceso de inventario para que cuando este marcado no cree un gasto y marque el inventario item
	alterar proceso de facturacion para que al facturar consignacion marque facturas_items
	alterar proceso de borrar factura 

	crear formulario en el front par que recupere consiganciones por pagar y realizar el proceso de pago
	crear en el backend proceso para que se paguen las consignaciones y se desmarquen las facturas items

	Modificacion__________________________________________________________________________________________________________
		* ItemInventario.java, se agrega atributo de tipo booleano para consignacion con su get y set
			private Boolean consignacion;
		* ItemFactura.java, se agrega atributo de tipo booleano para consignacion con su get y set
			
			private Boolean consignacion;
		* Modificacion de base de datos
			use db_springboot_backend;

			alter table facturas_items add column consignacion boolean default null;

			alter table inventarios_items add column consignacion boolean default null;
		* InventarioController.java, se modifica metodo saveInventario(), se alterar proceso de inventario para que cuando 
			este marcado no cree un gasto y marque el inventario item
			*Se añade parametro --> @RequestParam(value = "consignacion") Boolean consignacion
			*Se setea el atributo consignacion  --> item.setConsignacion(consignacion);
			*Se añade validacion para que no cree el gasto cuando sea consignacion
				if (consignacion) {....}
		* app.module.ts, se importa el modulo para MatSlideToggleModule
			*Se agrega el import --> import {MatSlideToggleModule} from '@angular/material/slide-toggle';
			*Se añade a los imports --> MatSlideToggleModule,
		* inventarioform.component.ts (Backend)
			*Se crea importacion al themecolor		
				import {ThemePalette} from '@angular/material/core';
			*Se crea variables
			  	consignacion = false;

				disabled = false;
				checked = false;
				color: ThemePalette = 'primary';
			* Se crea metodo que cambia la variable consignacion
				  mostrar(): void{
				    this.consignacion = !this.consignacion;
				  }
			* Se modifica el metodo de creacion de inventario

				  // Creacion de Inventario
				  crearInventario( inventarioForm ): void{
				    this.habilitado = false;
				    const usuario: string = this.authService.usuario.username;
				    console.log(this.consignacion);
				    // Se valida si la factura es igual a 0
				    if (this.inventario.items.length === 0){
				      this.autoCompleteControl.setErrors({ 'invalid': true});
				    }
				    if(inventarioForm.form.valid && this.inventario.items.length > 0){
				      this.inventarioService.createInventario(this.inventario, usuario, this.consignacion).subscribe(inventario => {
				        Swal.fire(this.titulo, `El inventario fue creado con exito.`, 'success');
				        this.router.navigate(['/inventarios']);
				      });
				    }
				  }
		* inventario.service.ts, se modifica metodo createInventario()    (Backend)
			createInventario(inventario: Inventario, user: string, consignacion: boolean): Observable<any>{
			    return this.httpClient.post<any>(this.urlEndPoint + '?user=' + user + '&consignacion=' + consignacion, inventario).pipe(
			      // Se agrega el operador catchError, intercepta en caso de existir error
			      catchError(e => {
			        // Manejo de validacion que viene en el response del backend
			        if ( e.status === 400){
			          return throwError(e);
			        }
			        // Se muestra por consola el error
			        if (e.error.mensaje){
			          console.error(e.error.mensaje);
			        }
			        return throwError(e);
			      })
			    );
			  }
		* inventarioform.component.html, se le agrega slide angularMaterial
			    <mat-slide-toggle class="tp-margin" [checked]="checked" [disabled]="disabled" [color]="color" (change)="mostrar()">
                    Consignacion
                </mat-slide-toggle>
        * facturaServiceImpl.java, se modifica metodo, se valida si el inventario es de consignacion y se le agrega seteo consignacion 
          al item de factura	
        	//Metodo para la creacion de una factura
			@Override
			@Transactional
			public Factura saveFactura(Factura factura) {
				//Se recorren los items de la factura
				for (ItemFactura item : factura.getItems()) {
					boolean consignacion = false;
					Comision comision = comisionRepo.findbyProducto(item.getProducto().getId());
					if (comision != null) item.setComision(comision.getComision());
					//Se recuperan los inventarios activos
					List<ItemInventario> items = itemInvServ.getInventarios(item.getProducto().getId(), "Activo");
					Double cantidad = BigDecimal.valueOf(item.getCantidad()).setScale(3, RoundingMode.HALF_UP).doubleValue();
					List<ItemInventario> inventAfect = new ArrayList<>();
					//Se recorren los inventarios activos
					for (ItemInventario itemInv : items) {
						if(itemInv.getConsignacion()) consignacion = true;
						if (cantidad > 0) {
							Double existencia =  BigDecimal.valueOf(itemInv.getExistencia()).setScale(3, RoundingMode.HALF_UP).doubleValue();
							//Se valida si la existencia es menor o igual
							if(cantidad <= existencia) {
								//se disminuye la existencia
								itemInv.setExistencia(BigDecimal.valueOf(existencia-cantidad).setScale(3, RoundingMode.HALF_UP).doubleValue());
								cantidad = 0D;
							}else {
								//Se setea a cero la existencia del inventario
								itemInv.setExistencia(0D);
								//Se disminuye la cantidad para que pueda ser descontada en el siguiente inventario
								cantidad -= existencia;
							}
							if(itemInv.getExistencia() == 0) {
								itemInv.setEstado("Inactivo");	
								item.setItem_inventario(itemInv);
								item.setCantinv(existencia);
							}
							itemInvServ.saveItemInventario(itemInv);	
							inventAfect.add(itemInv);
						}
					}
					item.setConsignacion(consignacion);
					item.setItems_inventario(inventAfect);
				}
				//Se registra factura en la cajachica
				//cajaService.registroCaja(factura);
				return facturasRepo.save(factura);
			}	
********************************************************************************************************************************En Produccion



******************************************************************************************************Incidente de retiro de caja
	Descripcion: Error 400, problemas con la de nullpointexception en el json --> cliente  --> factura
	Correccion: Se le agrego @JsonIgnoreProperties (ignoreUnknown = true) a los pojos Factura, cliente y facturas_items
*********************************************************************************************************************************En Produccion



*****************************************************************************Incidencia muestra mal el listado de consignaciones
	Descripcion: no muestra de manera correcta la cantidad de productos en consignacion
	Cambio: 
		***IFacturaRepository.java, se modifica query findConsignaciones()
					@Query(value="select p.nombre as producto, "
						+ "sum(fi.cantidad) as cantiad, "
						+ "inv.preciocompra as precio, "
						+ "inv.inventario_id as inventario, "
						+ "fi.id as factura "
					+ "from facturas_items fi "
					+ "inner join productos p "
					+ "	on fi.producto_id = p.id "
					+ "inner join inventarios_items inv "
					+ "	on inv.id = (select iteminventario_id "
					+ "					from itemsfactura_itemsinventario "
					+ "					where itemfactura_id = fi.id limit 1) "
					+ "where fi.consignacion = 1 "
					+ "group by fi.producto_id", nativeQuery = true)
			public List<Object[]> findConsignaciones();
********************************************************************************************************************************En Produccion



****************************************************************************************************************Modulo de Perdidas
	Descripcion: se necesita un modulo para el regstro de todas las perdidas del sistema
	Campos de la entidad:
		Id
		Fecha de creacion
		usuario
		producto
		tipo
		precio
		cantidad
		monto
		descripcion

	Modificacion(Back)_______________________________________________________________________________________________________
		* Se crea entidad Perdida en el backend
			@Table(name="perdidas")
			@Entity
			public class Perdida implements Serializable{

				
				@Id
				@GeneratedValue(strategy = GenerationType.IDENTITY)
				private Long id;
				
				@Column(name = "create_at")
				@Temporal(TemporalType.TIMESTAMP)
				private Date createAt;
				
				@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
				@JoinColumn(name = "user")
				private String user;
				
				@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
				@OneToOne
				@JoinColumn(name="producto_id")
				private Producto producto;
				
				private String tipo;
				
				private Double cantidad;
				
				private Double precio;
				
				private Double monto;
				
				private String Descripcion;
				
				// Se le añade el cascade para que se elimine en cascada cuando sea eliminada la
				// entidad
				@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
				@ManyToMany(fetch = FetchType.LAZY)
				@JoinTable(name = "itemsperdida_itemsinventario", joinColumns = @JoinColumn(name = "itemperdida_id"), inverseJoinColumns = @JoinColumn(name = "iteminventario_id"),
						// Se configura para que exista una key usuario rol igual
						uniqueConstraints = { @UniqueConstraint(columnNames = { "itemperdida_id", "iteminventario_id" }) })
				private List<ItemInventario> items_inventario;
				
				@PrePersist
				public void prePersistFecha() {
					this.createAt = new Date();
				}	
				public List<ItemInventario> getItems_inventario() {
					return items_inventario;
				}
				public void setItems_inventario(List<ItemInventario> items_inventario) {
					this.items_inventario = items_inventario;
				}
				public Long getId() {
					return id;
				}
				public void setId(Long id) {
					this.id = id;
				}
				public Date getCreateAt() {
					return createAt;
				}
				public void setCreateAt(Date createAt) {
					this.createAt = createAt;
				}
				public String getUser() {
					return user;
				}
				public void setUser(String user) {
					this.user = user;
				}
				public Producto getProducto() {
					return producto;
				}
				public void setProducto(Producto producto) {
					this.producto = producto;
				}
				public String getTipo() {
					return tipo;
				}
				public void setTipo(String tipo) {
					this.tipo = tipo;
				}
				public Double getCantidad() {
					return cantidad;
				}
				public void setCantidad(Double cantidad) {
					this.cantidad = cantidad;
				}
				public Double getPrecio() {
					return precio;
				}
				public void setPrecio(Double precio) {
					this.precio = precio;
				}
				public Double getMonto() {
					return monto;
				}
				public void setMonto(Double monto) {
					this.monto = monto;
				}
				public String getDescripcion() {
					return Descripcion;
				}
				public void setDescripcion(String descripcion) {
					Descripcion = descripcion;
				}



				@Override
				public String toString() {
					return "Perdida [id=" + id + ", createAt=" + createAt + ", user=" + user + ", producto=" + producto + ", tipo="
							+ tipo + ", cantidad=" + cantidad + ", precio=" + precio + ", monto=" + monto + ", Descripcion="
							+ Descripcion + ", items_inventario=" + items_inventario + "]";
				}



				/**
				 * 
				 */
				private static final long serialVersionUID = 1371542177788588449L;

			}
		* Se crea tabla de base de datos perdidas
			CREATE TABLE `perdidas` (
			  `id` bigint(20) NOT NULL AUTO_INCREMENT,
			  `create_at` date NOT NULL,
			  `user` varchar(50)  NOT NULL,
			  `producto_id` bigint(20) DEFAULT NULL,
			  `tipo` enum('Vencido','Extraviado','Deteriorado') NOT NULL,
			  `cantidad` double DEFAULT NULL,
			  `precio` double DEFAULT NULL,
			  `monto` double DEFAULT NULL,
			  `descripcion` varchar(255) DEFAULT NULL,
			  `iteminventario_id` bigint(20) DEFAULT NULL,
			  `cantinv` double DEFAULT NULL,
			  PRIMARY KEY (`id`),
			  UNIQUE KEY `id_UNIQUE` (`id`),
			  KEY `FKproducto` (`producto_id`),
			  KEY `FKiteminventarioperdida` (`iteminventario_id`),
			  CONSTRAINT `FKiteminventariosperd` FOREIGN KEY (`iteminventario_id`) REFERENCES `inventarios_items` (`id`),
			  CONSTRAINT `FKproductosperd` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
			) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;


			CREATE TABLE `itemsperdida_itemsinventario` (
			  `itemperdida_id` bigint(20) NOT NULL,
			  `iteminventario_id` bigint(20) NOT NULL,
			  UNIQUE KEY `UKitems` (`itemperdida_id`,`iteminventario_id`),
			  KEY `FKiteminventario` (`iteminventario_id`),
			  CONSTRAINT `FKitemsperdidas` FOREIGN KEY (`itemperdida_id`) REFERENCES `perdidas` (`id`),
			  CONSTRAINT `FKitemsinventariosperdida` FOREIGN KEY (`iteminventario_id`) REFERENCES `inventarios_items` (`id`)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;
		* Se crea repositorio
			@Repository
			public interface IPerdidaRepository extends JpaRepository<Perdida, Long> {

			}
		* Se crea Interface IPersidaService
			public interface IPerdidaService {

				List<Perdida> findAll();
				Page<Perdida> finAll(Pageable pagina);
				Perdida findById(Long idPerdida);
				Perdida savePerdida(Perdida perdida);
				void deletePerdida(Long idPerdda);
				
			}
		* Se crea la clase de servicio
			@Service
			public class PerdidaServiceImpl implements IPerdidaService {
				
				@Autowired
				private IPerdidaRepository perdidasRepo;
				
				@Autowired
				private IItemInventarioService itemInvServ;
				
				@Autowired
				private IProductoRepository productoRepo;

				@Override
				@Transactional(readOnly = true)
				public List<Perdida> findAll() {
					return perdidasRepo.findAll();
				}

				@Override
				@Transactional(readOnly = true)
				public Page<Perdida> finAll(Pageable pagina) {
					return perdidasRepo.findByOrderByIdDesc(pagina);
				}

				@Override
				@Transactional(readOnly = true)
				public Perdida findById(Long idPerdida) {
					return perdidasRepo.findById(idPerdida).orElse(null);
				}

				@Override
				@Transactional
				public Perdida savePerdida(Perdida perdida) {
					//Se recuperan los inventarios activos
					List<ItemInventario> items = itemInvServ.getInventarios(perdida.getProducto().getId(), "Activo");
					Double cantidad = BigDecimal.valueOf(perdida.getCantidad()).setScale(3, RoundingMode.HALF_UP).doubleValue();
					List<ItemInventario> inventAfect = new ArrayList<>();
					Double existenciaProd = perdida.getProducto().getExistencia();
					perdida.getProducto().setExistencia(existenciaProd-cantidad);
					//Se recorren los inventarios activos
					for (ItemInventario itemInv : items) {
						if (cantidad > 0) {
							Double existencia =  BigDecimal.valueOf(itemInv.getExistencia()).setScale(3, RoundingMode.HALF_UP).doubleValue();
							//Se valida si la existencia es menor o igual
							if(cantidad <= existencia) {
								//se disminuye la existencia
								itemInv.setExistencia(BigDecimal.valueOf(existencia-cantidad).setScale(3, RoundingMode.HALF_UP).doubleValue());
								cantidad = 0D;
							}else {
								//Se setea a cero la existencia del inventario
								itemInv.setExistencia(0D);
								//Se disminuye la cantidad para que pueda ser descontada en el siguiente inventario
								cantidad -= existencia;
							}
							if(itemInv.getExistencia() == 0) {
								itemInv.setEstado("Inactivo");	
								perdida.setItem_inventario(itemInv);
								perdida.setCantinv(existencia);
							}
							itemInvServ.saveItemInventario(itemInv);	
							inventAfect.add(itemInv);
						}
					}
					productoRepo.save(perdida.getProducto());
					perdida.setItems_inventario(inventAfect);
					return perdidasRepo.save(perdida);
				}

				@Override
				@Transactional
				public void deletePerdida(Long idPerdida) {
					Optional<Perdida> opt = perdidasRepo.findById(idPerdida);
					Perdida perdida = null;
					if (opt.isPresent()) {
						perdida = opt.get();
						//Se obtiene la existencia del producto y se repone
						Double cantProd = perdida.getProducto().getExistencia();
						perdida.getProducto().setExistencia(cantProd+perdida.getCantidad());
						productoRepo.save(perdida.getProducto());
						//Se obtiene la cantidad a reponer
						Double cantidad = perdida.getCantidad();
						Long idInvInactivo = 0L;
						//Se valida si el item inactivo un inventario
						if(perdida.getItem_inventario() != null) {	
							//Activo el inventario y repongo la existencia
							ItemInventario invInactivo = itemInvServ.getItemInventario(perdida.getItem_inventario().getId());
							idInvInactivo = invInactivo.getId();
							invInactivo.setEstado("Activo");
							invInactivo.setExistencia(perdida.getCantinv());
							cantidad -= perdida.getCantinv();
							itemInvServ.saveItemInventario(invInactivo);
						}
						//Recorro los inventarios activos
						for (ItemInventario itemInv : perdida.getItems_inventario()) {		
							if (idInvInactivo != itemInv.getId()) {
								//se aumenta la existencia
								itemInv.setExistencia(itemInv.getExistencia()+cantidad);
								itemInv.setEstado("Activo");
								cantidad -= cantidad;
								itemInvServ.saveItemInventario(itemInv);
							}										
						}	
					}	
					
					perdidasRepo.deleteById(idPerdida);
				}
			}
		* Se crea controlador
			@RestController
			@RequestMapping("/api")
			@CrossOrigin(origins = {"http://localhost:4200","*"})
			public class PerdidaRestController {
				
				@Autowired
				private IPerdidaService perdidaService;
				


				@GetMapping("/perdida")
				public List<Perdida> getPerdidas(){
					return perdidaService.findAll();
				}
				
				@Secured({"ROLE_ADMIN","ROLE_USER"})
				@GetMapping("/perdida/page/{page}")
				public Page<Perdida> cajaPage(@PathVariable Integer page){
					Pageable pagina = PageRequest.of(page, 5);
					return perdidaService.finAll(pagina);
				}
				
				//Se recupera perdida por id
				@Secured({"ROLE_ADMIN","ROLE_USER"})
				@GetMapping("/perdida/{id}")
				public ResponseEntity<?> showPerdida(@PathVariable("id") Long idPerdida){
					Perdida perdida = null;
					Map<String, Object> response = new HashMap<>();
					try {
						//Se recupera la perdida de la clase de servicio
						perdida = perdidaService.findById(idPerdida);
					} catch (DataAccessException e) {
						response.put("mensaje", "Error al realizar la consulta en la base de datos.");
						response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
						//Se envia error a la vista
						return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					//Se valida si la caja esta en null
					if(perdida == null) {
						response.put("mensaje", "La perdida Id: ".concat(idPerdida.toString())
								.concat(" no existe en la base de datos."));
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
					}
					return new ResponseEntity<Perdida>(perdida, HttpStatus.OK);
				}
				
				//Metodo para la creacion de caja
				@Secured({"ROLE_ADMIN"})
				@PostMapping("/perdida")
				public ResponseEntity<?> savePerdida(@Valid @RequestBody Perdida perdida, BindingResult result){
					Perdida perdidaNew = null;
					Map<String, Object> response = new HashMap<>();
					if(result.hasErrors()) {
						List<String> errores = result.getFieldErrors()
							//Se convierte en stream
							.stream()
							//Cada error se convierte en un string
							.map(error -> "El campo '"+error.getField()+"' "+error.getDefaultMessage())
							//Se convierte en una lista
							.collect(Collectors.toList());
						response.put("errores", errores);
						return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
					}
					
					try {
						perdidaNew = perdidaService.savePerdida(perdida);
					} catch (DataAccessException e) {
						response.put("mensaje", "Error al insertar la Perdida.");
						response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					response.put("mensaje","La perdida se ha registrado.");
					response.put("perdida", perdidaNew);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}
				
				
				
				//Metodo para modificar Perdida
				@Secured("ROLE_ADMIN")
				@PutMapping("/perdida/{id}")
				public ResponseEntity<?> updatePerdida(@Valid @RequestBody Perdida perdida,
						BindingResult result,
						@PathVariable("id") Long idPerdida){
					Perdida perdidaUpd= null;
					Map<String, Object> response = new HashMap<>();
					//Se validan errorres que se reciben del request
					if(result.hasErrors()) {
						List<String> errores = result.getFieldErrors()
								.stream().map(err->"El campo "+err.getField()+" "+err.getDefaultMessage())
								.collect(Collectors.toList());
						response.put("errores", errores);
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
					}
					Perdida perdidaAct = perdidaService.findById(idPerdida);
					//Se valida si existe
					if(perdidaAct == null) {
						response.put("mensaje", "La perdida ID: ".concat(idPerdida.toString()).concat(" no existe en la Base de datos."));
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
					}
					try {
						//Se stean modificaciones
						
						//Pendiente
						
						//Se guarda en base de datos
						perdidaUpd = perdidaService.savePerdida(perdidaAct);
					} catch (DataAccessException e) {
						response.put("mensaje", "Error al modificar La perdida con Id: ".concat(idPerdida.toString()).concat(" en la base de datos."));
						response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
						return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					response.put("mensaje", "La perdida se modifico con exito.");
					response.put("perdida", perdidaUpd);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}
				
				//Metodo para eliminar perdida
				@Secured({"ROLE_ADMIN"})
				@DeleteMapping("/perdida/{id}")
				public ResponseEntity<?> deletePerdida(@PathVariable("id") Long idPerdida){
					Map<String, Object> response = new HashMap<>();
					try {
						perdidaService.deletePerdida(idPerdida);
					} catch (DataAccessException e) {
						response.put("mensaje", "Error al eliminar la perdida ID: "
								.concat(idPerdida.toString())
								.concat(" No existe en la base de datos."));
						response.put("error", e.getMessage().concat(": ")
								.concat(e.getMostSpecificCause().getMessage()));
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					response.put("mensaje", "La perdida ha sido eliminada con exito.");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}

			}
	Modificacion(Front)_______________________________________________________________________________________________________
		* Se crea foder --> Perdidas 
		* Se crea componente -- ng g  c Perdidas/perdidas --skipTests -is
			*Se modifica perdidas.component.ts	
				@Component({
				  selector: 'app-perdidas',
				  templateUrl: './perdidas.component.html',
				  styles: [
				  ]
				})
				export class PerdidasComponent implements OnInit {

				  perdidas: Perdida[];
				  //Atributo para la paginacion
				  paginador: any;

				  constructor( private ActivatedRoute: ActivatedRoute,
				                private router: Router,
				                public authService: AuthService,
				                private perdidasService: PerdidasService) { }

				  ngOnInit(): void {
				    this.ActivatedRoute.paramMap.subscribe( params => {
				      //Se agrega atributo que recupera la pagina dinamicamente
				      let pagina = +params.get('page');
				      if(!pagina){
				        pagina = 0;
				      }
				      this.perdidasService.getPerdidas(pagina).subscribe(
				        response => {
				          this.perdidas = (response.content as Perdida[]);
				          console.log(this.perdidas);
				          //Se da valor al paginador
				          this.paginador = response;
				        }
				      )
				    })
				  }

				  delete(perdida: Perdida): void{
				    const swalWithBootstrapButtons = Swal.mixin({
				      customClass: {
				        confirmButton: 'btn btn-success',
				        cancelButton: 'btn btn-danger'
				      },
				      buttonsStyling: false
				    })

				    swalWithBootstrapButtons.fire({
				      title: 'Esta usted seguro?',
				      text: `Desea eliminar la perdida ${perdida.id}?`,
				      icon: 'warning',
				      showCancelButton: true,
				      confirmButtonText: 'Si, eliminar!',
				      cancelButtonText: 'No, cancelar!',
				      reverseButtons: true
				    }).then((result) => {
				      if (result.value) {
				        this.perdidasService.delete(perdida.id).subscribe(
				          response => {
				            this.perdidas = this.perdidas.filter(item => item !== perdida);
				            swalWithBootstrapButtons.fire(
				              'Perdida Eliminada!',
				              `Perdida ${perdida.id} eliminada con exito.`,
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

				}
			*Se modifica perdidas.component.html
				<div class="main-content">
				    <div class="container-fluid">
				        <div class="card-body text-primary">
				            <h3 class="card-title">Listado de Perdidas</h3>
				            <div class="my-2 text-left">
				                <!--[routerLink] Nos permite crear rutas nternas del proyecto-->
				                <button *ngIf="authService.hasRole('ROLE_ADMIN')" class="btn btn-rounded btn-success" type="buttom" [routerLink]="['/perdidas/form']">Crear Perdida</button>
				            </div>
				            <!-- Se crea div informativo en caso de no haber clientes en la lista -->
				            <div class="alert alert-info" *ngIf="perdidas?.length == 0">
				                No hay registros en la Base de Datos.
				            </div>
				            <div class="table-responsive">
				                <!-- Se agrega directiva ngIf para validar si la lista de clientes esta vacia -->
				                <table class="table table-striped" *ngIf="perdidas?.length>0">
				                    <thead>
				                        <tr>
				                            <th>Id</th>
				                            <th>fecha</th>
				                            <th>Usuario</th>
				                            <th>Producto</th>
				                            <th>tipo</th>
				                            <th>Monto</th>
				                            <th *ngIf="authService.hasRole('ROLE_ADMIN')">Detalle</th>
				                            <th *ngIf="authService.hasRole('ROLE_ADMIN')">eliminar</th>
				                        </tr>
				                    </thead>
				                    <tbody>
				                        <!--Se añade condicional *ngFor-->
				                        <tr *ngFor="let perdida of perdidas">
				                            <td>{{perdida.id}}</td>
				                            <td>{{perdida.createAt | date:'EEEE dd, MMMM yyyy'}}</td>
				                            <td>{{perdida.user.username}}</td>
				                            <td>{{perdida.producto.nombre | titlecase}}</td>
				                            <td>{{perdida.tipo}}</td>
				                            <td>{{perdida.monto | number:'.1-2'}}</td>
				                            <td *ngIf="authService.hasRole('ROLE_ADMIN')">
				                                <button class="btn btn-success btn-sm" type="button" [routerLink]="['/perdida', perdida.id]">Ver</button>
				                            </td>
				                            <td *ngIf="authService.hasRole('ROLE_ADMIN')">
				                                <button class="btn btn-danger btn-sm" type="button" name="eliminar" (click)="delete(perdida)">Eliminar</button>
				                            </td>
				                        </tr>
				                    </tbody>
				                </table>
				                <!-- Paginacion -->
				                <paginadorinv-nav *ngIf="paginador" [paginador]="paginador"></paginadorinv-nav>
				            </div>

				        </div>
		* Se crea clase de servicio --> ng g s Perdidas/perdidas --skipTests
			@Injectable({
			  providedIn: 'root'
			})
			export class PerdidasService {

			  private urlEndPoint = 'http://localhost:8080/api/perdida';
			  // private urlEndPoint: string ='http://66.228.61.76/springAngular/api/perdida';
			  
			  constructor( private httpClient: HttpClient,
			                private router: Router) {  }

			  getPerdidas(page: number): Observable<any>{
			    return this.httpClient.get(this.urlEndPoint + '/page/' +page).pipe(
			      map( (response: any) =>{
			        response.content as Perdida[];
			        return response;
			      })
			    );
			  }  


			  createPerdida(perdida: Perdida, user: string): Observable<any>{
			    return this.httpClient.post<any>(this.urlEndPoint + '?user=' + user, perdida).pipe(
			      // Se agrega el operador catchError, intercepta en caso de existir error
			      catchError(e => {
			        // Manejo de validacion que viene en el response del backend
			        if ( e.status === 400){
			          return throwError(e);
			        }
			        // Se muestra por consola el error
			        if (e.error.mensaje){
			          console.error(e.error.mensaje);
			        }
			        return throwError(e);
			      })
			    );
			  }

			  getPerdida(id: number): Observable<Perdida>{
			    return this.httpClient.get<Perdida>(`${this.urlEndPoint}/${id}`).pipe(
			      catchError(e => {
			        if (e.status === 401 && e.error.mensaje){
			          this.router.navigate(['/perdidas']);
			          console.error(e.error.mensaje);
			        }
			        return throwError(e);
			      })
			    );
			  }


			  // Metodo de modificacion del inventario
			  update(perdida: Perdida): Observable<Perdida>{
			    return this.httpClient.put(`${this.urlEndPoint}/${perdida.id}`, perdida).pipe(
			      map((response: any) => response.perdida as Perdida),
			      catchError( e => {
			        if (e.status === 400){
			          return throwError(e);
			        }
			        if (e.error.mensaje){
			          console.error(e.error.mensaje);
			        }
			        return throwError(e);
			      })
			    );
			  }

			  // Metodo delete de Inventario
			  delete(id: number): Observable<Perdida>{
			    return this.httpClient.delete<Perdida>(`${this.urlEndPoint}/${id}`).pipe(
			      catchError( e => {
			        if (e.error.mensaje){
			          console.error(e.error.mensaje);
			        }
			        return throwError(e);
			      })
			    );
			  }
			}
		* Se crea clase Perdida --> ng g class Perdidas/perdida --skipTests
			export class Perdida {
			    id: number;
			    createAt:string;
			    user: Usuario;
			    producto: Producto;
			    tipo: string;
			    cantidad: number;
			    precio: number;
			    monto: number;
			    descripcion: number;
			}
		* Se modifica sidebar.component.ts
			export const ROUTES: RouteInfo[] = [
			    { path: '/clientes', title: 'Usuarios',  icon: 'flutter_dash', class: '' },
			    { path: '/productos', title: 'Productos',  icon: 'bubble_chart', class: '' },
			    { path: '/gastos', title: 'Gastos',  icon: 'paid', class: '' },
			    { path: '/cajas', title: 'Cajas',  icon: 'savings', class: '' },
			    { path: '/inventarios', title: 'Inventarios',  icon: 'list_alt', class: '' },
			    { path: '/consignacion', title: 'Consignacion',  icon: 'mediation', class: '' },
			    { path: '/perdidas', title: 'Perdidas',  icon: 'mediation', class: '' },
			 /*   { path: '/dashboard', title: 'Dashboard',  icon: 'dashboard', class: '' },
			    { path: '/user-profile', title: 'User Profile',  icon:'person', class: '' },
			    { path: '/table-list', title: 'Table List',  icon:'content_paste', class: '' },
			    { path: '/typography', title: 'Typography',  icon:'library_books', class: '' },
			    { path: '/icons', title: 'Icons',  icon:'bubble_chart', class: '' },
			    { path: '/maps', title: 'Maps',  icon:'location_on', class: '' },
			    { path: '/notifications', title: 'Notifications',  icon:'notifications', class: '' },
			    { path: '/upgrade', title: 'Upgrade to PRO',  icon:'unarchive', class: 'active-pro' }*/
			];

			export const ROUTESUSER: RouteInfo[] = [
			  { path: '/clientes', title: 'Usuarios',  icon: 'flutter_dash', class: '' },
			  { path: '/productos', title: 'Productos',  icon: 'bubble_chart', class: '' },
			  { path: '/cajas', title: 'Cajas',  icon: 'savings', class: '' },
			  { path: '/inventarios', title: 'Inventarios',  icon: 'list_alt', class: '' },
			  { path: '/consignacion', title: 'Consignacion',  icon: 'mediation', class: '' },
			  { path: '/perdidas', title: 'Perdidas',  icon: 'mediation', class: '' },
			/*   { path: '/dashboard', title: 'Dashboard',  icon: 'dashboard', class: '' },
			  { path: '/user-profile', title: 'User Profile',  icon:'person', class: '' },
			  { path: '/table-list', title: 'Table List',  icon:'content_paste', class: '' },
			  { path: '/typography', title: 'Typography',  icon:'library_books', class: '' },
			  { path: '/icons', title: 'Icons',  icon:'bubble_chart', class: '' },
			  { path: '/maps', title: 'Maps',  icon:'location_on', class: '' },
			  { path: '/notifications', title: 'Notifications',  icon:'notifications', class: '' },
			  { path: '/upgrade', title: 'Upgrade to PRO',  icon:'unarchive', class: 'active-pro' }*/
			];
		* Se modifica admin-layout.routing.ts, se agregan rutas
			{ path: 'perdidas',               component: PerdidasComponent, },
			{ path: 'perdidas/page/:page',    component: PerdidasComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ROLE_USER'}},
		* Se crea componente --> ng g c Perdidas/formPerdidas --skipTests -is
********************************************************************************************************************************** En produccion doc imcompleta


Modulo de comisiones**************************************************************************************************************
	SQL____________________________________________________________________________________________________________________
	*Modificacion tabla facturas
		ALTER TABLE `db_springboot_backend`.`facturas` 
		ADD COLUMN `Comision_mp` DOUBLE NULL AFTER `cliente_id`;
	Backend________________________________________________________________________________________________________________
	*Facturas.java, se crea atributo comision_mp con get y set
		private Double comision_mp;
	*facturaServiceImpl.java
		*Se crea metodo que hace el calculo de la comision de MercadoPago
			//Comision mercadopago
			private Factura comisionMP(Factura factura) {
				Double mercadoPago = factura.getMercadopago();
				Double comision_mp = (mercadoPago * 5)/100;
				factura.setComision_mp(comision_mp);
				factura.setTotal(factura.getTotal()+comision_mp);
				return factura;
			}
		*Se modifica saveFactura(), se valida si hay mercadoPago y si es asi se calcula y se setea a la factura
			public Factura saveFactura(Factura factura) {
				if( factura.getMercadopago() != null ) comisionMP(factura);
	FrondEnd______________________________________________________________________________________________________________
		*Se modifica detalle-factura.component.html, se agrega visualizacion del impuesto
			<h2>Comision MP:<span class="badge badge-secondary">{{factura.comision_mp}}</span></h2>
		* Se modifica facturas.component.html, Se agrega visualizacion de la comision y se agrega en los calculos de efectivo y total
			<h2>Total Efectivo:<span class="badge badge-secondary">{{(factura.getTotalFactura() + factura.comision_mp - factura.mercadopago) | number}}</span></h2>

			<h2 *ngIf="factura.mercadopago > 0">Comision MP:<span class="badge badge-info">{{getComisMp() | number}}</span></h2>
			<h2>Total Factura:<span class="badge badge-secondary">{{factura.total + factura.comision_mp | number}}</span></h2>
		* Se mdifica facturas.component.ts
			* Se agrega deteccion de cambios por medio de ChangeDetectorRef
				 constructor(private clienteService: ClienteService,
				               private facturasService: FacturasService,
				               private ActivatedRoute: ActivatedRoute,
								private router:Router
								private router:Router,
					 			private cdref: ChangeDetectorRef
				             ) { }

				ngAfterContentChecked() {
					 this.cdref.detectChanges();
				}
			* Se crea metodo para calculo de comision
				+  getComisMp():number{
				+    this.factura.comision_mp = (this.factura.mercadopago*5)/100;
				+    return this.factura.comision_mp;
				+  }
		* Se modifica factura.ts, se agrega atributo comision_mp
			 comision_mp: number = 0;
********************************************************************************************************************************** Por Pase a produccion



Excepciones***********************************************************************************************************************
	Backend________________________________________________________________________________________________________________
	* ProductoController.java, se agrega endpoint para pruebas
		+       @Secured({"ROLE_ADMIN", "ROLE_USER"})
		+       @PostMapping("/productoPrueba")
		+       public ResponseEntity<Producto> saveProducto(@Valid @RequestBody Producto producto){
		+               return new ResponseEntity<Producto>(productoServ.saveProducto(producto), HttpStatus.OK);
		+       }
	* Producto.java, se le agrega anotacion NotBlank al nombre
		+       @NotBlank
        private String nombre;
    * Se crea paquete package com.eareiza.springAngular.excepciones;
    * Se crea clase ErrorInfo.java
        import com.fasterxml.jackson.annotation.JsonProperty;

		public class ErrorInfo {

		    @JsonProperty("mensaje")
		    private String mensaje;
		    @JsonProperty("status_code")
		    private int codigo;
		    @JsonProperty("uri")
		    private String uriRequested;

		    public ErrorInfo(int statusCode, String message, String uriRequested) {
		        this.mensaje = message;
		        this.codigo = statusCode;
		        this.uriRequested = uriRequested;
		    }

			public String getMensaje() {
				return mensaje;
			}

			public int getCodigo() {
				return codigo;
			}

			public String getUriRequested() {
				return uriRequested;
			}

		}
	* Se crea clase RestExceptionHandler.java
		import org.springframework.http.HttpStatus;
		import org.springframework.http.ResponseEntity;
		import org.springframework.validation.BindingResult;
		import org.springframework.validation.FieldError;
		import org.springframework.web.bind.MethodArgumentNotValidException;
		import org.springframework.web.bind.annotation.ControllerAdvice;
		import org.springframework.web.bind.annotation.ExceptionHandler;

		import javax.servlet.http.HttpServletRequest;
		import java.util.List;

		@ControllerAdvice
		public class RestExceptionHandler {

		    @ExceptionHandler(MethodArgumentNotValidException.class)
		    public ResponseEntity<ErrorInfo> methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {

		        // get spring errors
		        BindingResult result = e.getBindingResult();
		        List<FieldError> fieldErrors = result.getFieldErrors();

		        // convert errors to standard string
		        StringBuilder errorMessage = new StringBuilder();
		        fieldErrors.forEach(f -> errorMessage.append(f.getField() + " " + f.getDefaultMessage() +  " "));

		        // return error info object with standard json
		        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), errorMessage.toString(), request.getRequestURI());
		        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);

		    }

		}
	FrontEnd_______________________________________________________________________________________________________________
	* Se implementa PrimeNg
		*Instalacion primeNg y primeIcons
			npm install primeng primeicons
		* Se modifica angular.json, se le agrega a styles estilos de primeNg
			+                            "node_modules/@fortawesome/fontawesome-free/css/all.css",
			+                            "node_modules/primeicons/primeicons.css",
			+                            "node_modules/primeng/resources/themes/vela-blue/theme.css",
			+                            "node_modules/primeng/resources/primeng.min.css"
********************************************************************************************************************************** En Desarrollo





Iconos****************************************************************************************************************************
	https://material.io/resources/icons/?style=baseline
	add_chart
	trending_up
	settings_ethernet
	speed
								repeat
								price_change
	sentiment_satisfied_alt
	insights
	price_check
	auto_graph
	insert_emoticon
	add_reaction
	mood
	mood_bad
	sentiment_very_dissatisfied
	sentiment_neutral
	sentiment_very_satisfied
**********************************************************************************************************************************



Querys**************************************************************************************************************************
	select p.nombre as nombre,sum(fi.cantidad) as cantidad,	sum(((fi.precio-ii.preciocompra)*fi.cantidad)+fi.comision) as ganancia, sum(((fi.precio)*fi.cantidad)) as venta  
	from facturas_items fi inner join productos p on p.id = fi.producto_id inner join itemsfactura_itemsinventario piv on fi.id = piv.itemfactura_id 
		inner join inventarios_items ii on ii.id = piv.iteminventario_id group by p.id 	order by 3  desc;

	***Importa en un csv
		select p.nombre as nombre,	sum(fi.cantidad) as cantidad, sum(((fi.precio-ii.preciocompra)*fi.cantidad)+fi.comision) as ganancia,  
				sum(((fi.precio)*fi.cantidad)) as venta  
		from facturas_items fi  
			inner join productos p 
				on p.id = fi.producto_id 
			inner join itemsfactura_itemsinventario piv 
				on fi.id = piv.itemfactura_id 
			inner join inventarios_items ii  
				on ii.id = piv.iteminventario_id 
		group by p.id 
		order by 3  desc
		INTO OUTFILE '/var/lib/mysql-files/GananciaXProducto010221_310221.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';



	use db_springboot_backend;

	select * from facturas_items where consignacion = 1;

	update facturas_items set consignacion = 0 where id = 56;

	select * from inventarios_items where consignacion = 1;

	select * from facturas_items;

	select * from inventarios_items where producto_id = 96;

	****************************************************************************************************************Gastos
		 select f.fecha_fact, f.descripcion, f.monto_pesos, f.clasificacion, f.metodopago from gastos f where fecha_fact >= '2021-02-01' and fecha_fact <= '2021-02-15' and f.clasificacion = 'Gasto' INTO OUTFILE '/var/lib/mysql-files/Gastos010221_150221.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';
		 select f.fecha_fact, f.descripcion, f.monto_pesos, f.clasificacion, f.metodopago from gastos f where fecha_fact >= '2021-01-01' and fecha_fact <= '2021-01-15' and f.clasificacion = 'Gasto' INTO OUTFILE '/var/lib/mysql-files/Gastos010121_150121.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';
		 select f.fecha_fact, f.descripcion, f.monto_pesos, f.clasificacion, f.metodopago from gastos f where fecha_fact >= '2021-01-01' and fecha_fact <= '2021-01-31' and f.clasificacion = 'Gasto' INTO OUTFILE '/var/lib/mysql-files/Gastos010121_310221.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';


	****************************************************************************************************GanaciasXProducto
		select  p.nombre as nombre,sum(fi.cantidad) as cantidad, sum(((fi.precio-ii.preciocompra)*fi.cantidad)+fi.comision) as ganancia,
		 sum(((fi.precio)*fi.cantidad)) as venta
		from facturas_items fi
		inner join facturas f on f.id = fi.factura_id
		inner join productos p on p.id = fi.producto_id
		inner join itemsfactura_itemsinventario piv on fi.id = piv.itemfactura_id
		inner join inventarios_items ii on ii.id = piv.iteminventario_id where f.create_at between 20210101 and 20210115
		group by p.id
		order by 3  desc
		INTO OUTFILE '/var/lib/mysql-files/GananciaXProducto010121_150121.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';

		select  p.nombre as nombre,sum(fi.cantidad) as cantidad, sum(((fi.precio-ii.preciocompra)*fi.cantidad)+fi.comision) as ganancia,
		 sum(((fi.precio)*fi.cantidad)) as venta
		from facturas_items fi
		inner join facturas f on f.id = fi.factura_id
		inner join productos p on p.id = fi.producto_id
		inner join itemsfactura_itemsinventario piv on fi.id = piv.itemfactura_id
		inner join inventarios_items ii on ii.id = piv.iteminventario_id where f.create_at between 20210101 and 20210131
		group by p.id
		order by 3  desc
		INTO OUTFILE '/var/lib/mysql-files/GananciaXProducto010121_310121.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';

		select  p.nombre as nombre,sum(fi.cantidad) as cantidad, sum(((fi.precio-ii.preciocompra)*fi.cantidad)+fi.comision) as ganancia,
		 sum(((fi.precio)*fi.cantidad)) as venta
		from facturas_items fi
		inner join facturas f on f.id = fi.factura_id
		inner join productos p on p.id = fi.producto_id
		inner join itemsfactura_itemsinventario piv on fi.id = piv.itemfactura_id
		inner join inventarios_items ii on ii.id = piv.iteminventario_id where f.create_at between 20210201 and 20210215
		group by p.id
		order by 3  desc
		INTO OUTFILE '/var/lib/mysql-files/GananciaXProducto01021_150221.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';


	*********************************************************************************************************GanaciaGeneral

		select  sum(((fi.precio-ii.preciocompra)*fi.cantidad)+fi.comision) as ganancia, 
		 sum(((fi.precio)*fi.cantidad)) as venta
		from facturas_items fi
		inner join facturas f on f.id = fi.factura_id
		inner join productos p on p.id = fi.producto_id
		inner join itemsfactura_itemsinventario piv on fi.id = piv.itemfactura_id
		inner join inventarios_items ii on ii.id = piv.iteminventario_id where f.create_at between 20210201 and 20210228;
		order by 3  desc
		INTO OUTFILE '/var/lib/mysql-files/GananciaXProducto01021_150221.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';


	*************************************************************************************************Ganancia por Producto
		select  sum(((fi.precio-ii.preciocompra)*fi.cantidad)+fi.comision) as ganancia, sum(fi.cantidad) as cantidad,
		 sum(((fi.precio)*fi.cantidad)) as venta
		from facturas_items fi
		inner join facturas f on f.id = fi.factura_id
		inner join productos p on p.id = fi.producto_id
		inner join itemsfactura_itemsinventario piv on fi.id = piv.itemfactura_id
		inner join inventarios_items ii on ii.id = piv.iteminventario_id 
	    where f.create_at between 20210301 and 20210312
	    and p.id = 130;
	*****************************************************************************************************Ganancia y Gastos
    select  sum(((fi.precio-ii.preciocompra)*fi.cantidad)+fi.comision) as ganancia, (select sum(f.monto_pesos) from gastos f where fecha_carga >= '2021-02-01' and fecha_carga <= '2021-02-28' and f.clasificacion = 'Gasto') as gastos,
	((sum(((fi.precio-ii.preciocompra)*fi.cantidad)+fi.comision))-(select sum(f.monto_pesos) from gastos f where fecha_carga >= '2021-02-01' and fecha_carga <= '2021-02-28' and f.clasificacion = 'Gasto')) as GananciaReal,
	sum(((fi.precio)*fi.cantidad)) as venta
	from facturas_items fi
	inner join facturas f on f.id = fi.factura_id
	inner join productos p on p.id = fi.producto_id
	inner join itemsfactura_itemsinventario piv on fi.id = piv.itemfactura_id
	inner join inventarios_items ii on ii.id = piv.iteminventario_id where f.create_at between 20210201 and 20210228;


	*****************************************************************************************Usuario para coneccion linode
		usuario: eareiza
		pass: danger120
********************************************************************************************************************************



Implementaciones que faltan
	* Implementar exceptions 
	* Implementar en el Front la organizacion por modulos
	* Implementar test 
	* Crear parametrizacion de comision
	* Crear parametrizacion de cuenta
		* Crear un entidad Cuenta
		* Efectivo
		* Banco
		* Pedidos Ya
		* Mercado Pago
