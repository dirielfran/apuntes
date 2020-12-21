*****************************************************************************************************Requerimiento Retiro de caja
	Modificaciones*********************************************************************************************************
	***Cliente.java
		*Se crea tributo con relacion OneToMany
			@JsonIgnoreProperties(value= {"cliente"}, allowSetters = true)
			@OneToMany(mappedBy = "examen",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true )
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
		*Se crea atributo
			@JsonIgnoreProperties(value = {"retirosCaja"})
			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "cliente_id")
			private Cliente cliente;

			@JsonIgnoreProperties(value = {"retirosCaja"})
			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "caja_id")
			private Caja caja;

		** Get y set
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
	 

*********************************************************************************************************************************