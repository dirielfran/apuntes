1.**********************************************************************************************************************
************************************************************************************************************************
********************************************************************************************1013444	Listado Fijaciones
	Usuario		--> Antonella Spezia
	Ext 		--> 5136
	Area 		--> Mercaderia
	Descripcion:
	Quería que vieran de este listado que se genera desde Facturacion – Consultar Fijaciones, 
	aparentemente no esta filtrando por contratos de canje, por ejemplo, vemos el que marque en 
	amarillo Cto 17/13355/7 de Noelma con Maiocco, que no corresponde a laboratorio.
	 
	En el archivo exportado, tampoco hay una columna, que indique, si es canje o no.*/
	***<Item name="ConsultaFijaciones" title="consultaFijaciones.menu.title" page="/consultaFijaciones.html" width="250"/>

	****struts-Facturacion.xml
	<!-- ConsultaFijaciones-START -->
			<action name="consultaFijaciones" class="com.zeni.app.webapp.action.ConsultaFijacionesAction" method="list">
				<interceptor-ref name="jsonValidationWorkflowStack" />
				<result name="success">/WEB-INF/pages/consultaFijacionesList.jsp</result>
			</action>



	***consultaFijacionesFiltro

	<s:select key="filter.situacion" label="%{getText('consultaFijacionesFiltro.situacion')}" 
						list="%{@com.zeni.app.util.PropertyValues@getSituacionesFijacion()}" 
						required="false" cssClass="text medium" />

	<s:select key="filter.tipoCanje" label="%{getText('consultaFijacionesFiltro.tipoCanje')}" 
						list="%{@com.zeni.app.util.PropertyValues@getTipoCanje()}" 
						required="false" cssClass="text medium" />


	-----------------------------------------------------------------------------------------------------------------Cambios
	****ConsultaFijacionesAction

	****Se añade al filter
	addCondition("grupoFijaciones.contrato.esCanje", "=", "tipoCanje"); 

	***Se modifica el setter de tipoCanje
	public void setTipoCanje(Character v) {			
				if(v.equals('T')){
					set("tipoCanje", null);
				}else if(v.equals('C')){
					set("tipoCanje", true);
				}else if(v.equals('N')){
					set("tipoCanje", false);
				}				
			}
			
	*****consultaFijacionesList
	***Se modifica la grilla de consultaFijacionesList
	<ria:simplegridcolumn label="contrato.canje" width="45" style="text-align:center;" id="grupoFijaciones.contrato.esCanje"
		    		key="($item.grupoFijaciones.contrato.esCanje)?'si':'no'"	/>

	*****ApplicationResources.properties
	contrato.canje=Canje
************************************************************************************************************************Implementado



***Reporte
2.**********************************************************************************************************************
************************************************************************************************************************
******************************************************1012620  	Reportes Syngenta - modalidad de nuevos contratos "Nieto"
	Usuario 	--> Juan Ignacio Dimitroff
	Ext		--> 5061
	Area		--> Mercaderías

	-------------------------------------------------------------------------------------------------------------Modificaciones
	*****menu-config.xml 
		<Item name="ReporteContratosNieto" title="Reporte Contratos Nieto" p
		age="/reporteContratosNieto.html" 
		roles="ROLE_USER,ROLE_CLIENTE_VIEW,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION,ROLE_BOLETOS,ROLE_SUBPRODUCTOS,ROLE_IMPUESTOS" width="250"/>


	*****struts.xml
		<!--Reporte de Contratos Nietos-->
		<action name="reporteContratosNieto" class="com.zeni.app.webapp.action.ConsultaAnaliticaDeContratosAction" method="repConsultaNietos">
			<result name="success">/WEB-INF/pages/reporteConsultaNietosList.jsp</result>
		</action>  


	*****ConsultaAnaliticaDeContratosAction
	**Se crea variable 
	private List<ContratoNietos> listaContratos = new ArrayList<ContratoNietos>();	
	
	public List<ContratoNietos> getListaContratos() {
		return listaContratos;
	}

	public void setListaContratos(List<ContratoNietos> listaContratos) {
		this.listaContratos = listaContratos;
	}

	**Se crea metodo
	public String repConsultaNietos() {
		List<Object[]> lista = null;
		
		String cumplido = (filtro.getCumplido() == "S") ? "AND con.cumplido = 1 " : (filtro.getCumplido() != null) ? "AND con.cumplido = 0 " : " " ;
		Date fechaDesde = filtro.getFechaEntregaDesde();
		Date fechaHasta= filtro.getFechaEntregaHasta();
		String contrato = filtro.getNumeroContrato() != null ? "AND con.NUMEROCONTRATO = '"+filtro.getNumeroContrato()+"'" : " "  ;
		int id = 0;
		

		if ((!(filtro.getFechaEntregaDesde() == null) || !(filtro.getFechaEntregaDesde() == null)) && 
				(!(filtro.getFechaEntregaHasta() == null) || !(filtro.getFechaEntregaHasta() == null))) {			
			try {
				String consulta = "SELECT (SELECT TO_CHAR(fechaalta, 'dd/mm/yyyy')  FROM contrato c WHERE numerocontrato = "+
											"(SELECT con1.NUMEROCONTRATO FROM contrato con1 WHERE con1.id  = hijo.CONTRATO_ID)) , "+
										"(SELECT nrocuenta FROM cuenta_cliente WHERE id = "+
											"(select con1.COMPRADOR_ID from contrato con1 where con1.id  = madre.CONTRATO_ID)), "+ 
										"(select con1.NUMEROCONTRATO from contrato con1 where con1.id  = madre.CONTRATO_ID), "+
										"(SELECT razonSocial FROM Cliente c INNER JOIN CUENTA_CLIENTE cc ON c.Id = cc.CLIENTE_ID where cc.id = "+
											"(select con1.COMPRADOR_ID from contrato con1 where con1.id  = madre.CONTRATO_ID)), " +
										"(SELECT con1.NUMEROCONTRATO FROM contrato con1 WHERE con1.id  = hijo.CONTRATO_ID) , "+
										"(SELECT cuit FROM cliente C INNER JOIN CUENTA_CLIENTE CC ON C.ID = CC.CLIENTE_ID INNER JOIN contrato CON "+
											"ON CC.ID = CON.VENDEDOR_ID WHERE CON.ID = hijo.CONTRATO_ID) , "+
										"(SELECT RAZONSOCIAL FROM CLIENTE C INNER JOIN CUENTA_CLIENTE CC ON C.ID = CC.CLIENTE_ID INNER JOIN CONTRATO CON "+ 
											"ON CC.ID = CON.VENDEDOR_ID WHERE CON.ID = hijo.CONTRATO_ID) , "+
										"(SELECT cobroDeMercaderia FROM contrato WHERE id = hijo.CONTRATO_ID), "+
										"(select Descripcion from Producto where id = con.Producto_ID) , "+
										"(SELECT preciomercaderia FROM contrato WHERE id = hijo.contrato_id) ,"+
										"(SELECT TO_CHAR(fechaentregadesde, 'dd/mm/yyyy') FROM contrato WHERE id = hijo.contrato_id) , "+
										"(SELECT TO_CHAR(fechaentregahasta, 'dd/mm/yyyy') FROM contrato WHERE id = hijo.contrato_id) , "+
										"(SELECT TO_CHAR(min(Fechafijaciondesde), 'dd/mm/yyyy') FROM GRUPO_FIJACIONES where CONTRATO_ID = hijo.contrato_id), "+
										"(SELECT TO_CHAR(max(Fechafijacionhasta), 'dd/mm/yyyy') FROM GRUPO_FIJACIONES where CONTRATO_ID = hijo.contrato_id), "+
										"(SELECT cantidadPactada FROM  contrato WHERE id = hijo.CONTRATO_ID) , "+
										"(SELECT ROUND(COALESCE(SUM(ep.cantidad),0),2) "+
											"FROM entrega_partida ep "+
											"INNER JOIN workflow_state ws ON ws.id = ep.state_id "+
											"INNER JOIN workflow_definition w ON w.id = ws.workflowDefinition_id "+
											"WHERE ep.fechaBaja is NULL AND w.estadoActual != 'N' "+
											"AND ep.contrato_id = hijo.CONTRATO_ID), "+
										"(SELECT ROUND(COALESCE(SUM( fp.cantidadFacturada ),0),2) "+
											"FROM factura_producto fp "+ 
											"left join tipo_comprobante tc on tc.id=fp.tipoComprobante_id "+
											"WHERE fp.contrato_id =  hijo.CONTRATO_ID and upper(fp.facturador) not like '%FINAL%' "+
											"and tc.abreviatura not in ('FA','ND','NC','LF','BF') and (tc.abreviatura <> 'FC' or fp.descontarCantidadFacturada = 1) "+
											"and fp.fechaBaja is NULL and fp.anulador_id is null and fp.anulada_id is null), "+	
										"(SELECT con2.NUMEROCONTRATO FROM contrato con2 WHERE con2.id  = nieto.CONTRATO_ID) , "+
										"(SELECT TO_CHAR(fechaalta, 'dd/mm/yyyy')  FROM contrato c WHERE NUMEROCONTRATO = "+
											"(SELECT con1.NUMEROCONTRATO FROM contrato con1 WHERE con1.id  = nieto.CONTRATO_ID)), "+
										"(SELECT RAZONSOCIAL FROM CLIENTE C INNER JOIN CUENTA_CLIENTE CC ON C.ID = CC.CLIENTE_ID INNER JOIN CONTRATO CON "+
											"ON CC.ID = CON.VENDEDOR_ID WHERE CON.ID = nieto.CONTRATO_ID) , "+
										"(SELECT cuit FROM cliente C INNER JOIN CUENTA_CLIENTE CC ON C.ID = CC.CLIENTE_ID INNER JOIN contrato CON "+
											"ON CC.ID = CON.VENDEDOR_ID WHERE CON.ID = nieto.CONTRATO_ID), "+
										"(SELECT Descripcion from Producto WHERE id = (SELECT con1.Producto_ID FROM contrato con1 WHERE con1.id  = nieto.CONTRATO_ID)) ,"+
										"(SELECT preciomercaderia FROM contrato WHERE id = nieto.contrato_id), "+
										"(SELECT TO_CHAR(fechaentregahasta, 'dd/mm/yyyy') FROM contrato WHERE id = nieto.CONTRATO_ID) , "+
										"(SELECT TO_CHAR(max(Fechafijacionhasta), 'dd/mm/yyyy') FROM GRUPO_FIJACIONES where CONTRATO_ID = nieto.contrato_id), "+
										"(SELECT cantidadPactada FROM  contrato WHERE id = nieto.CONTRATO_ID),  "+
										"(SELECT ROUND(COALESCE(SUM(ep.cantidad),0),2) "+ 
											"FROM entrega_partida ep "+
											"INNER JOIN workflow_state ws ON ws.id = ep.state_id "+ 
											"INNER JOIN workflow_definition w ON w.id = ws.workflowDefinition_id "+
											"WHERE ep.fechaBaja is NULL AND w.estadoActual != 'N' "+
											"AND ep.contrato_id = nieto.CONTRATO_ID), "+
										"(SELECT ROUND(COALESCE(SUM( fp.cantidadFacturada ),0),2) "+
											"FROM factura_producto fp "+ 
											"left join tipo_comprobante tc on tc.id=fp.tipoComprobante_id "+
											"WHERE fp.contrato_id =  nieto.CONTRATO_ID and upper(fp.facturador) not like '%FINAL%' "+
											"and tc.abreviatura not in ('FA','ND','NC','LF','BF') and (tc.abreviatura <> 'FC' or fp.descontarCantidadFacturada = 1) "+
											"and fp.fechaBaja is NULL and fp.anulador_id is null and fp.anulada_id is null) "+
									"FROM CANJE_CONTRATO_COMP_CEREAL hijo "+ 
									"LEFT join CONTRATO con "+
										"ON con.CANJE_ID = hijo.CANJE_ID "+
									"LEFT JOIN CANJE_CONTRATO_VTA_CEREAL madre "+
										"ON  hijo.CANJE_ID = madre.CANJE_ID "+
									"LEFT JOIN CANJE_CONTRATO_NIETO nieto "+
										"ON con.CANJE_ID = nieto.CANJE_ID "+
									"WHERE con.FECHAOPERACION >= to_date('"+ DateUtil.getDate(fechaDesde) + "','dd/MM/yyyy') "+
									"AND con.FECHAOPERACION <= to_date('"+ DateUtil.getDate(fechaHasta) + "','dd/MM/yyyy') "+
									"AND con.ESCANJE = 1 "+
									"AND con.CANJE_ID is not null "+
									"AND (con.ESNIETO is null or  con.ESNIETO = 0 ) "+
									"AND (con.COMPRADOR_ID in (170029069, 170029809)) "+
									"AND ((SELECT con1.comprador_id FROM contrato con1 WHERE con1.id  = hijo.CONTRATO_ID) IN (170029069, 170029809)) ";
									
				
				lista = (List<Object[]>) contratoManager.executeSQL(consulta+cumplido+contrato);
				if (lista != null && lista.size() > 0) {
					for (Object item : lista) {
						ContratoNietos cn;
						Object[] objectItem = (Object[]) item; 19/131177
						id = id + 1;
						String fechaHijo       = ((objectItem[0]     !=       null)   ?    objectItem[0].toString()     :       "");
						if(objectItem[0]     !=       null) {
							String fechaHijo  =objectItem[0].toString();
						}else{
							String fechaHijo = "";
						}
						String prueba = expresion  ?    verdadero :     falso;
						int nCtaComprador = ((objectItem[1] != null) ? Integer.parseInt(objectItem[1].toString()) : 0);
						String nContrato = ((objectItem[2] != null) ? objectItem[2].toString() : null);
						String razonSocialMadre = (objectItem[3] != null ? objectItem[3].toString() : null);
						String nContratoHijo = ((objectItem[4] != null) ? objectItem[4].toString() : null);
						String cuit_hijo = ((objectItem[5] != null) ? objectItem[5].toString() : null);
						String razonSocialHijo =((objectItem[6] != null) ? objectItem[6].toString() : null);
						String cobroMercaderia = ((objectItem[7]) != null ? objectItem[7].toString() : "");
						String producto =((objectItem[8] != null) ? objectItem[8].toString() : null);
						double precio = ((objectItem[9] != null) ? Double.valueOf(objectItem[9].toString()) : 0); 
						String fechaEntDesdeCliente = ((objectItem[10] != null) ? objectItem[10].toString() : null);
						String vto_Entrega = ((objectItem[11] != null) ? objectItem[11].toString() : null);
						String fechaFijacDesde = ((objectItem[12] != null ) ? objectItem[12].toString() : null);
						String fechaFijacHasta = ((objectItem[13] != null ) ? objectItem[13].toString() : null);
						double cantPactHijo = ((objectItem[14] != null ) ? Double.valueOf(objectItem[14].toString()) : 0);
						double ttEntregadaHijo = ((objectItem[15] != null) ? Double.valueOf(objectItem[15].toString()) : 0);
						double ttLiquidadaHijo = ((objectItem[16] != null) ? Double.valueOf(objectItem[16].toString()) : 0);
						String nContratoNieto = ((objectItem[17] != null ) ? objectItem[17].toString() : null);
						String fechaCtoNieto = ((objectItem[18] != null ) ? objectItem[18].toString() : null);
						String productor = ((objectItem[19] != null) ? objectItem[19].toString() : null);
						String cuitNieto = ((objectItem[20] != null) ? objectItem[20].toString() : null);
						String granoNieto = ((objectItem[21] != null) ? objectItem[21].toString() : null);
						double precioNieto = ((objectItem[22] != null) ? Double.valueOf(objectItem[22].toString()) : 0); 
						String fechaHastaNieto = ((objectItem[23] != null) ? objectItem[23].toString() : null);
						String fechaFijHastNieto = ((objectItem[24] != null ) ? objectItem[24].toString() : null);
						double cantPactNieto = ((objectItem[25] != null) ? Double.valueOf(objectItem[25].toString()) : 0);
						double ttEntregadaNieto = ((objectItem[26] != null) ? Double.valueOf(objectItem[26].toString()) : 0);
						double ttLiquidadaNieto = ((objectItem[27] != null) ? Double.valueOf(objectItem[27].toString()) : 0);
						double valorHijo = precio * cantPactHijo *1.105;
						double valorNieto = precioNieto * cantPactNieto *1.105;
						cobroMercaderia = getCobroMercaderia(cobroMercaderia);
						
						
						cn = new ContratoNietos(id, fechaHijo, nCtaComprador, nContrato, razonSocialMadre, 
								nContratoHijo, cuit_hijo, razonSocialHijo,cobroMercaderia, producto, precio ,valorHijo, fechaEntDesdeCliente, 
								vto_Entrega ,fechaFijacDesde ,fechaFijacHasta, cantPactHijo ,ttEntregadaHijo ,ttLiquidadaHijo, nContratoNieto, 
								fechaCtoNieto, productor, cuitNieto, granoNieto ,precioNieto ,valorNieto ,fechaHastaNieto ,fechaFijHastNieto , 
								cantPactNieto, ttEntregadaNieto, ttLiquidadaNieto);						
						listaContratos.add(cn);
					}
				}
			} catch (Exception e) {				
				e.printStackTrace();				
			}
		}
		return SUCCESS;
	}

	***Se crea bean
	public class ContratoNietos {
		private int id;
		private String fechaHijo;
		private int nCtaComprador;
		private String nContrato;
		private String razonSocialMadre;
		private String nContratoHijo;
		private String cuit_hijo;
		private String razonSocialHijo;	
		private String cobroMercaderia;
		private String producto;
		private double precio;
		private double valorHijo;
		private String fechaEntDesdeCliente;
		private String vto_Entrega;
		private String fechaFijacDesde;
		private String fechaFijacHasta;
		private double cantPactHijo;
		private double ttEntregadaHijo;
		private double ttLiquidadaHijo;
		private String nContratoNieto;
		private String fechaCtoNieto;
		private String productor;
		private String cuitNieto;
		private String granoNieto;
		private double precioNieto;
		private double valorNieto;
		private String fechaHastaNieto;
		private String fechaFijHastNieto;
		private double cantPactNieto;
		private double ttEntregadaNieto;
		private double ttLiquidadaNieto;


		public ContratoNietos(int id,String fechaHijo, int nCtaComprador, String nContrato, String razonSocialMadre, 
				String nContratoHijo, String cuit_hijo, String razonSocialHijo ,String cobroMercaderia , String producto, 
				double precio,double valorHijo , String fechaEntDesdeCliente ,String vto_Entrega,String fechaFijacDesde ,
				String fechaFijacHasta, double cantPactHijo,double ttEntregadaHijo ,double ttLiquidadaHijo, String nContratoNieto, 
				String fechaCtoNieto, String productor, String cuitNieto, String granoNieto,double precioNieto ,double valorNieto, 
				String fechaHastaNieto ,String fechaFijHastNieto , double cantPactNieto, double ttEntregadaNieto, double ttLiquidadaNieto) {
			super();
			this.id = id;
			this.fechaHijo = fechaHijo;
			this.nCtaComprador = nCtaComprador;
			this.nContrato = nContrato;
			this.razonSocialMadre = razonSocialMadre;
			this.nContratoHijo = nContratoHijo;
			this.cuit_hijo = cuit_hijo;
			this.razonSocialHijo = razonSocialHijo;
			this.precio = precio;
			this.valorHijo = valorHijo;
			this.cobroMercaderia = cobroMercaderia;
			this.producto = producto;
			this.fechaEntDesdeCliente = fechaEntDesdeCliente;
			this.vto_Entrega = vto_Entrega;
			this.fechaFijacDesde = fechaFijacDesde;
			this.fechaFijacHasta = fechaFijacHasta;
			this.cantPactHijo = cantPactHijo;
			this.ttEntregadaHijo = ttEntregadaHijo;
			this.ttLiquidadaHijo =ttLiquidadaHijo;
			this.nContratoNieto = nContratoNieto;
			this.fechaCtoNieto = fechaCtoNieto;
			this.productor = productor;
			this.cuitNieto = cuitNieto;
			this.granoNieto = granoNieto;
			this.precioNieto = precioNieto;
			this.valorNieto = valorNieto;
			this.fechaHastaNieto = fechaHastaNieto;
			this.fechaFijHastNieto = fechaFijHastNieto;
			this.cantPactNieto = cantPactNieto;
			this.ttEntregadaNieto = ttEntregadaNieto;
			this.ttLiquidadaNieto = ttLiquidadaNieto;
		}
		
		
		public double getTtLiquidadaNieto() {
			return ttLiquidadaNieto;
		}


		public void setTtLiquidadaNieto(double ttLiquidadaNieto) {
			this.ttLiquidadaNieto = ttLiquidadaNieto;
		}


		public double getTtLiquidadaHijo() {
			return ttLiquidadaHijo;
		}


		public void setTtLiquidadaHijo(double ttLiquidadaHijo) {
			this.ttLiquidadaHijo = ttLiquidadaHijo;
		}


		public double getTtEntregadaHijo() {
			return ttEntregadaHijo;
		}

		public void setTtEntregadaHijo(double ttEntregadaHijo) {
			this.ttEntregadaHijo = ttEntregadaHijo;
		}

		public double getTtEntregadaNieto() {
			return ttEntregadaNieto;
		}

		public void setTtEntregadaNieto(double ttEntregadaNieto) {
			this.ttEntregadaNieto = ttEntregadaNieto;
		}

		public String getCobroMercaderia() {
			return cobroMercaderia;
		}

		public void setCobroMercaderia(String cobroMercaderia) {
			this.cobroMercaderia = cobroMercaderia;
			
		}

		public double getValorNieto() {
			return valorNieto;
		}

		public void setValorNieto(double valorNieto) {
			this.valorNieto = valorNieto;
		}

		public double getValorHijo() {
			return valorHijo;
		}
		
		public void setValorHijo(double valorHijo) {
			this.valorHijo = valorHijo;
		}

		public String getFechaFijHastNieto() {
			return fechaFijHastNieto;
		}

		public void setFechaFijHastNieto(String fechaFijHastNieto) {
			this.fechaFijHastNieto = fechaFijHastNieto;
		}

		public String getFechaFijacHasta() {
			return fechaFijacHasta;
		}

		public void setFechaFijacHasta(String fechaFijacHasta) {
			this.fechaFijacHasta = fechaFijacHasta;
		}

		public String getFechaFijacDesde() {
			return fechaFijacDesde;
		}

		public void setFechaFijacDesde(String fechaFijacDesde) {
			this.fechaFijacDesde = fechaFijacDesde;
		}

		public double getPrecioNieto() {
			return precioNieto;
		}

		public void setPrecioNieto(double precioNieto) {
			this.precioNieto = precioNieto;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public double getCantPactNieto() {
			return cantPactNieto;
		}

		public void setCantPactNieto(double cantPactNieto) {
			this.cantPactNieto = cantPactNieto;
		}

		public String getFechaHastaNieto() {
			return fechaHastaNieto;
		}

		public void setFechaHastaNieto(String fechaHastaNieto) {
			this.fechaHastaNieto = fechaHastaNieto;
		}

		public String getGranoNieto() {
			return granoNieto;
		}

		public void setGranoNieto(String granoNieto) {
			this.granoNieto = granoNieto;
		}

		public String getCuitNieto() {
			return cuitNieto;
		}

		public void setCuitNieto(String cuitNieto) {
			this.cuitNieto = cuitNieto;
		}

		public String getProductor() {
			return productor;
		}

		public void setProductor(String productor) {
			this.productor = productor;
		}

		public String getFechaCtoNieto() {
			return fechaCtoNieto;
		}

		public void setFechaCtoNieto(String fechaCtoNieto) {
			this.fechaCtoNieto = fechaCtoNieto;
		}

		public String getnContratoNieto() {
			return nContratoNieto;
		}

		public void setnContratoNieto(String nContratoNieto) {
			this.nContratoNieto = nContratoNieto;
		}

		public double getCantPactHijo() {
			return cantPactHijo;
		}

		public void setCantPactHijo(double cantPactHijo) {
			this.cantPactHijo = cantPactHijo;
		}

		public String getVto_Entrega() {
			return vto_Entrega;
		}

		public void setVto_Entrega(String vto_Entrega) {
			this.vto_Entrega = vto_Entrega;
		}

		public String getFechaHijo() {
			return fechaHijo;
		}

		public void setFechaHijo(String fechaHijo) {
			this.fechaHijo = fechaHijo;
		}

		public int getnCtaComprador() {
			return nCtaComprador;
		}

		public void setnCtaComprador(int nCtaComprador) {
			this.nCtaComprador = nCtaComprador;
		}

		public String getnContrato() {
			return nContrato;
		}

		public void setnContrato(String nContrato) {
			this.nContrato = nContrato;
		}

		public String getRazonSocialMadre() {
			return razonSocialMadre;
		}

		public void setRazonSocialMadre(String razonSocialMadre) {
			this.razonSocialMadre = razonSocialMadre;
		}

		public String getnContratoHijo() {
			return nContratoHijo;
		}

		public void setnContratoHijo(String nContratoHijo) {
			this.nContratoHijo = nContratoHijo;
		}

		public String getCuit_hijo() {
			return cuit_hijo;
		}

		public void setCuit_hijo(String cuit_hijo) {
			this.cuit_hijo = cuit_hijo;
		}

		public String getRazonSocialHijo() {
			return razonSocialHijo;
		}

		public void setRazonSocialHijo(String razonSocialHijo) {
			this.razonSocialHijo = razonSocialHijo;
		}

		public String getProducto() {
			return producto;
		}

		public void setProducto(String producto) {
			this.producto = producto;
		}

		public String getFechaEntDesdeCliente() {
			return fechaEntDesdeCliente;
		}

		public void setFechaEntDesdeCliente(String fechaEntDesdeCliente) {
			this.fechaEntDesdeCliente = fechaEntDesdeCliente;
		}
	}
	
	
	
	***Se crea metodo de utilitario
	// F-A fijar, C-Contra entrega, H-Fecha
		// cierta, U-Contra última entrega
	public String getCobroMercaderia(String cb){
		if(cb.equalsIgnoreCase("F")){
			return "A fijar";
		}else if(cb.equalsIgnoreCase("C")){
				return "Contra entrega";
			}else if (cb.equalsIgnoreCase("H")){
				return "Fecha cierta";
			}else if (cb.equalsIgnoreCase("U")){
				return "Contra última entrega";
			}
		return null;
	}

	***Se crea reporteConsultaNietosList.jsp

	***Se crea repConsultaNietoFiltro.jsp  

	*** Se modifica ApplicationResources.properties
		# -- Reporte Consulta Nieto
		rcnList.title=Reporte Contratos Nieto
		rcnFilter.filtro.title = Filtro - Reportes Contrato Nieto
		rcnList.heading = Reporte Contratos Nieto
************************************************************************************************************************Implementado




3.**********************************************************************************************************************
************************************************************************************************************************
*******************************************************************Ticket#1013509 — Validación exportación rete syngenta
	Usuario 	--> Juan Ignacio Dimitroff
	Ext			--> 5061
	Area		--> Mercaderías
	Descripcion:
	se podrá cargar una validación mas al momento de exportar las retenciones de Syngenta?
	El sistema tendría que avisar si el numero de CERTIFICADO tiene más de 8 dígitos (debido a 
	que es motivo de rechazo o error para Syngenta, y después se genera un reclamo)

	-------------------------------------------------------------------------------------------------------------Modificacion
	****En retenciones de ganancias ---->no hay nada
	numero_certificado --> no es 

	select nroretencionnumero 
	from retencion_ganancias


	*****retenciones de ganancias a terceros
	numero_certificado =  (reg_GA3ro[4]!=null)?reg_GA3ro[4].toString():"";
	nro_certificado_str = nrocertificado  --> recibo_retencio
	nroCertificadoImp = StringUtils.leftPad(nro_certificado_str,16);

	select nrocertificado 
	From recibo_retencio

	*modificacion

	if((reg_GA3ro[4] != null) && (!reg_GA3ro[4].toString().isEmpty())) {
		nro_certificado_str= reg_GA3ro[4].toString(); 	
		if(nro_certificado_str.length() > 8){
			addActionError("El numero de certidicado tiene una longitud mayor a 8 caracteres, para el Cuit: " + reg_GA3ro[2].toString());
		}
	}


	*****RETENCIONES DE INGRESOS BRUTOS
	numero_certificado = (reg_IIBB[4]!=null)?reg_IIBB[4].toString():"";
	nro_certificado_str= reg_IIBB[4].toString(); 

	*modificacion
	if((reg_IIBB[4] != null) && (!reg_IIBB[4].toString().isEmpty())) {
		nro_certificado_str= reg_IIBB[4].toString(); 
		if(nro_certificado_str.length() > 8){
			addActionError("El numero de certidicado tiene una longitud mayor a 8 caracteres, para el Cuit: " +  reg_IIBB[2].toString());
		}
	} 


	***** RETENCIONES DE IVA
	numero_certificado = "";
	nro_certificado_str= reg_IVA[4].toString(); 

	*modificacion
	if(nro_certificado_str.length() > 8){
		addActionError("El numero de certidicado tiene una longitud mayor a 8 caracteres, para el Cuit: " +  reg_IVA[2].toString());
	}
************************************************************************************************************************Implementado




4.**********************************************************************************************************************
************************************************************************************************************************
******************************************************************************Ticket#1011252 — Pedido de mejora reportes

	Usuario 	--> Juan Ignacio Dimitroff
	Ext			--> 5061
	Area		--> Mercaderías
	Descripcion:
	Por favor agregar un aviso/warning en las pantallas de generación de los reportes: “comercial - negocios pendientes de 
	garantía”  y “aplicaciones - contratos vencidos por operador” Esta aviso debería salir con la fecha y hora de la ultima 
	generación y envio de los mismos.


	reporte  comercial-negocios pendiente de garantia-----------------------------------------------------------------------
	**struts.xml
	<action name="reporteNegociosPtesGtia" class="com.zeni.app.webapp.action.NegociosCnGarantiaPteEntregaAction" method="list">
		<result>/WEB-INF/pages/avisoNegociosPendientes.jsp</result>
	</action>

	<action name="negociosPtesGtiaDoReport" class="com.zeni.app.webapp.action.NegociosCnGarantiaPteEntregaAction" method="negociosPtesGtiaDoReport">
		<interceptor-ref name="jsonValidationWorkflowStack" />
		<result name="success">/WEB-INF/pages/avisoNegociosPendientes.jsp</result>
		<result name="error">/WEB-INF/pages/avisoNegociosPendientes.jsp</result>
	</action>

	Action 		--> NegociosCnGarantiaPteEntregaAction
	jsp    		--> avisoNegociosPendientes.jsp
				--> avisoNegociosPendientesFiltro.jsp




	Se crea atributo en action con su get y set
	<div style="margin-left: 10px">Nombre proporcionado:<s:property value="nombre" /></div>

	***Ejemplo  -->fechaUltimoArchivoRetSyngenta
	***registros insertados en tabla sys_config
	fechaEnvEmailGarantiPendiente	10/07/2017 16:39
	fechaGenArcGarantiPendiente	10/07/2017 16:39

	ExportarArchivoRetencionAction.java

	String fechaArchivoRetencion = "";
	fechaArchivoRetencion = manager.getSystemConfig("fechaUltimoArchivoRetSyngenta");
	manager.setSystemConfig("fechaUltimoArchivoRetSyngenta", DateUtil.getDateTime("dd/MM/yyyy", fechaHasta));

	*****Modificaciones:

	**Se crean registros en la BD en la tabla sys_config
		INSERT INTO sys_config VALUES ('fechaEnvEmailGarantiPendiente', '' );
		INSERT INTO sys_config VALUES ('fechaGenArcGarantiPendiente', '' );

	**Se crean atributos en el action con su get y set-->NegociosCnGarantiaPteEntregaAction.java
		private String fechaGenArchivo;
		private String fechaEnvioEmail;	
		
		public String getFechaEnvioEmail() {
			return manager.getSystemConfig("fechaEnvEmailGarantiPendiente");
		}

		public void setFechaEnvioEmail(String fechaEnvioEmail) {
			this.fechaEnvioEmail = fechaEnvioEmail;
		}

		public String getFechaGenArchivo() {
			return manager.getSystemConfig("fechaGenArcGarantiPendiente");
		}

		public void setFechaGenArchivo(String fechaGenArchivo) {
			this.fechaGenArchivo = fechaGenArchivo;
		}

	**Se generan las fechas con la accion
		**Generacion de Archivo
		manager.setSystemConfig("fechaGenArcGarantiPendiente", DateUtil.getDateTime("dd/MM/yyyy hh:mm:ss", new Date()));
		**Envio de envio de email
		manager.setSystemConfig("fechaEnvEmailGarantiPendiente", DateUtil.getDateTime("dd/MM/yyyy hh:mm:ss", new Date()));

	**Se muestran los atributos en la vista  -->avisoNegociosPendientesFiltro.jsp
		<div style="margin-left: 10px">Generacion Reporte:<s:property value="fechaGenArchivo" /></div>
		<div style="margin-left: 10px">Envio Email:<s:property value="fechaEnvioEmail" /></div>

	reporte  aplicaciones - contratos vencidos por operador-----------------------------------------------------------------------

	Action 		--> ContratoVencidoPendienteListAction
	jsp    		--> contratoVencidoPendienteList.jsp
				--> contratoVencidoPendienteFiltro.jsp

	*****Modificaciones:

	**Se crean registros en la BD en la tabla sys_config
		INSERT INTO sys_config VALUES ('fechaContVencOperacion', '' );

	**Se crean atributos en el action con su get y set-->ContratoVencidoPendienteListAction.java
		private String fechaEnvioReporte;	
		

		public String getFechaEnvioReporte() {
			return contratoManager.getSystemConfig("fechaContVencOperacion");
		}

		public void setFechaEnvioReporte(String fechaEnvioReporte) {
			this.fechaEnvioReporte = fechaEnvioReporte;
		}

	**Se generan las fechas con la accion
		contratoManager.setSystemConfig("fechaContVencOperacion", DateUtil.getDateTime("dd/MM/yyyy hh:mm:ss", new Date()));

	**Se muestran los atributos en la vista  -->contratoVencidoPendienteList.jsp
		**Se añade display: inline-block al style
		<input type="button" style="margin-right: 5px; display: inline-block" class="veryLargeButton" 
	        onclick="if(confirm('¿Enviar informe?'))location.href='<c:url value="informeContratoVencido.html"/>';" 
	        value="Enviar Informe"/>
	    **Se añade la vista del atributo de fecha creado
		<div style="margin-left: 10px; display: inline-block">Generacion y envio de Reporte:<s:property value="fechaEnvioReporte" /></div>

	--------------------------------------------------------------------------------------------------------------------
	Nuevo requerimiento, se debe indicar la fecha de la ultima vez que se genero el reporte

	**Se crean registros en la BD en la tabla sys_config
		INSERT INTO sys_config VALUES ('fechaGenContVencOperacion', '' );

	**Se crean atributos en el action con su get y set-->ContratoVencidoPendienteListAction.java
	private String fechaGenReporte;	
	

	public String getFechaEnvioReporte() {
		return contratoManager.getSystemConfig("fechaGenContVencOperacion");
	}

	public void setFechaEnvioReporte(String fechaEnvioReporte) {
		this.fechaEnvioReporte = fechaEnvioReporte;
	}

	**Se modifica metodo sobreescrito prepare()
		@Override
		public void prepare() {
			String dato1= getRequest().getParameter("generacionArch");
			if (dato1 != null){
				contratoManager.setSystemConfig("fechaGenContVencOperacion", DateUtil.getDateTime("dd/MM/yyyy hh:mm:ss", new Date()));
			}
			getRequest().setAttribute("__RiaPageId", "contratoVencidoPendienteFilter");
			super.prepare();
		}

	**Se generan las fechas con la accion
		contratoManager.setSystemConfig("fechaGenContVencOperacion", DateUtil.getDateTime("dd/MM/yyyy hh:mm:ss", new Date()));

	**Se muestran los atributos en la vista  -->contratoVencidoPendienteList.jsp
		**Se añade display: inline-block al style
		<input type="button" style="margin-right: 5px; display: inline-block" class="veryLargeButton" 
	        onclick="if(confirm('¿Enviar informe?'))location.href='<c:url value="informeContratoVencido.html"/>';" 
	        value="Enviar Informe"/>
	    **Se añade la vista del atributo de fecha creado
		<div style="margin-left: 10px; display: inline-block">Generacion y envio de Reporte:<s:property value="fechaGenReporte" /></div>


	**Se modifica vista contratoVencidoPendienteFiltro.jsp, se añade campo hidden
		<input type='hidden' name='generacionArch' value='true' id='generacionArch' />		
************************************************************************************************************************Implementado




5.**********************************************************************************************************************
************************************************************************************************************************
****************************************************************************************Ticket#1012939 — Interfaz NUFARM
	Usuario 	--> Juan Ignacio Dimitroff
	Ext			--> 5061
	Area		--> Mercaderías
	Descripcion:
	Nufarm está solicitando que incorporemos la alícuota de retención de IIBB dentro del archivo que enviamos de forma diaria.
	Solicita que ese valor lo incorporemos entre el campo de jurisdicción y monto del impuesto.

	**El dato de retecion de IIBB se encentra en:
	cliente --> clientes
		razonSocial --> Nufarm --> filtro
			Impuestos del cliente
				Se muestra por provincia --> alicuota Retencion Padron***

	ClienteAction
	clienteList.jsp
		<action name="clienteImpuestos" class="com.zeni.app.webapp.action.ClienteAction" method="editImpuestos">
			<result>/WEB-INF/pages/clienteImpuestosForm.jsp</result>
				javascript:mostrarPopupEdicionClienteProvinciaIIBB($item.id, ${cliente.id})
					<action name="editClienteProvinciaIIBB" class="com.zeni.app.webapp.action.ClienteProvinciaIIBBAction" method="edit">
            			<result>/WEB-INF/pages/clienteProvinciaIIBBForm.jsp</result>
            			<result name="error">/WEB-INF/pages/clienteImpuestosForm.jsp</result>
        			</action>

	ClienteProvinciaIIBBAction
	clienteProvinciaIIBBForm.jsp

	<fmt:message key='percepcionRetencion.alicuotaRetencionPadron'/>: <b><s:property value="padronImpuesto.alicuotaRetencion"/></b>

	ClienteProvinciaIIBBAction
	private PadronImpuesto getPadron(String tipo, Cliente c, Provincia p, Date fecha){
		if(p != null){
			@SuppressWarnings("unchecked")
			List<PadronImpuesto> piList = 
				(List<PadronImpuesto>)BaseModel.find("FROM PadronImpuesto " + 
													 "WHERE tipo = ? and cuit = ? and provincia = ? " + 
													 "ORDER BY fechaPublicacion desc", 
													 new Object[]{tipo, c.getCuit(), p});
			for(PadronImpuesto pi : piList){
				if(fechaIn(fecha, pi.getFechaVigenciaDesde(), pi.getFechaVigenciaHasta()))
					return pi;
			}
		}
		return null;
	}


	(select alicuotaRetencion from padron_Impuesto p
	where cuit = '30-70052897-5'
	and tipo = 'PBA'
	and Provincia_id = (select distinct(pro.id) 
		from detalle_factura df
			join factura_producto fp
				on factura_id = fp.id
			join contrato c
	 		on fp.contrato_id = c.id
		join Ciudad ciu
			on c.PROCEDENCIA_ID = ciu.id
		join Partido p
			on p.id = ciu.PARTIDO_ID
		join provincia pro
			on p.PROVINCIA_ID = pro.id
			where 
			factura_id = 200255756)
	and fechavigenciadesde <= CURRENT_DATE
	and fechavigenciahasta >= current_date
	order by fechaPublicacion desc)


	@{$DetalleFactura.getFactura().getContrato().getProcedencia().getPartido().getProvincia()+";"}


	DetalleFactura.get

	***query del programa	
	SELECT df    
	FROM DetalleFactura df    
	JOIN df.factura fp   
	WHERE 1=1 	 
	AND ( (fp.conceptoFactura not in ('C','V') 
		AND (fp.contrato.comprador.cliente.id = 30374 
			OR fp.contrato.vendedor.cliente.id = 30374))	 
	OR (fp.conceptoFactura = 'C' 
		AND (fp.contrato.comprador.cliente.id = 30374))	 
	OR (fp.conceptoFactura = 'V' 
		AND (fp.contrato.vendedor.cliente.id = 30374))) 
	AND fp.fechaFactura >= :fechaDesde 
	AND fp.fechaFactura <= :fechaHasta
	AND fp.tipoComprobante.abreviatura in ('LP', 'BP', 'CP', 'LF', 'BF','DV', 'CV', 'FA', 'NC', 'ND')
	order by fp.nroFactura, df.orden

	clienteFiltro.jsp


	***menu-config.xml
		<Item name="ExportacionArchivoMenu" title="exportacionArchivoList.title" 
		page="/editExportacionArchivos.html" width="250" roles="ROLE_USER,ROLE_FACTURACION"/>
		
	***struts-Facturacion.xml
		<!--ExportacionArchivosAction-START -->
		<action name="editExportacionArchivos"
			class="com.zeni.app.webapp.action.ExportacionArchivosAction" method="edit">
			<interceptor-ref name="jsonValidationWorkflowStack" />
			<result>WEB-INF/pages/exportacionArchivosForm.jsp</result>
			<result name="error">WEB-INF/pages/exportacionArchivosForm.jsp</result>
		</action>
		<action name="exportacionArchivos"
			class="com.zeni.app.webapp.action.ExportacionArchivosAction" method="save">
			<interceptor-ref name="jsonValidationWorkflowStack" />
			<result name="success">WEB-INF/pages/exportacionArchivosForm.jsp</result>
			<result name="cancel">WEB-INF/pages/exportacionArchivosForm.jsp</result>
		</action>
		<action name="generarExcelCartaPorte" class="com.zeni.app.webapp.action.ExportacionArchivosAction" method="generarExcelCartaPorte">
			<result name="success">WEB-INF/pages/exportacionArchivosForm.jsp</result>
		</action>
		<action name="mandoCartasPorteXls" class="com.zeni.app.webapp.action.ExportacionArchivosAction" method="mandoCartasPorteXls">
			<result name="success">WEB-INF/pages/exportacionArchivosForm.jsp</result>
		</action>
		<action name="datosExportacionPorDefecto" class="com.zeni.app.webapp.action.ExportacionArchivosAction"
				method="getDatosExportacionPorDefecto">
			<result name="success" type="noOp" />
		</action>


	Modificaciones---------------------------------------------------------------------------------------------------------------------

	***ExportacionManagerImpl.java

	****Se crea funcion para traer la alicuota por id de la factura

		private double alicuota(String cuit, Date fechaDesde, Date fechaHasta, Long factura) {
			double alicuota = 0 ;
			List<Object[]> lista = null;
			Map<String, Object>params =new HashMap<String, Object>();
			if(fechaDesde != null)
				params.put("fechaDesde", fechaDesde);
			if(fechaHasta != null)
				params.put("fechaHasta", fechaHasta);
			String consulta ="select alicuotaRetencion from padron_Impuesto p "+
								"where cuit = '"+cuit+"' "+
								"and tipo = 'PBA' "+
								"and Provincia_id = (select distinct(pro.id) "+ 
														"from detalle_factura df "+
														"join factura_producto fp "+
															"on factura_id = fp.id "+
														"join contrato c "+
															"on fp.contrato_id = c.id "+
														"join Ciudad ciu "+
															"on c.PROCEDENCIA_ID = ciu.id "+
														"join Partido p "+
															"on p.id = ciu.PARTIDO_ID "+
														"join provincia pro "+
															"on p.PROVINCIA_ID = pro.id "+
														"where df.id = "+factura+") "+
								"and fechavigenciadesde <=  to_date('"+ DateUtil.getDate(fechaDesde) + "','dd/MM/yyyy') "+
								"and fechavigenciahasta >= to_date('"+ DateUtil.getDate(fechaHasta) + "','dd/MM/yyyy') "+
								"order by fechaPublicacion desc ";
			
			List<Object[]> listaResultados  = (List<Object[]>) dao.executeSQL(consulta);
			if(listaResultados!= null && listaResultados.size()>0){
				for(Object item: listaResultados){
					BigDecimal bigDecimalValue= (BigDecimal) item;
					alicuota = ((bigDecimalValue != null)?Double.parseDouble(bigDecimalValue.toString()):0);	
					return alicuota;
				}
			}
			return alicuota;
		}




	****Se modifica metodo getExportacion() para que inserte alicuota en array de impresion 
		lista = new ArrayList<String>(Arrays.asList(arrayLinExp_1));
		lista.add(25,alicuota.toString());					
		String[] arrayNew = new String[lista.size()];
		arrayNew = lista.toArray(arrayNew);
		lineaActual = StringUtils.join(arrayNew, ";");


	Nota: estos cambios se les realizo roolback, ya que existe una interface que realiza este trabajo
	Facturacion -->  Exportacion, se busca el cliente y se modifica los condicionales que tiene por base de datos
	En este caso se le agrega el porcentaje de percepcion luego de jurisdiccion
	 @{$DetalleFactura.getFactura().getPorcPercepcion1()+";"}
************************************************************************************************************************Implementado

private Double getPorcPercepcionIIBB(String leyenda) {


6.**********************************************************************************************************************
************************************************************************************************************************
*********************************************************************Ticket#1012282 — Envío automático de cartas ofertas
	Usuario 	--> JUAN EMILIANO
	Ext			--> 6235
	Area		--> Administración - Rosario
	Descripcion:
	Buen día.
 	Necesitamos poder enviar por mail mediante el sistema las cartas ofertas tal cual se envían los boletos en formato 
 	papel. 	Hoy en día hay que descargar el archivo en Word para poder agregar las cláusulas correspondientes y luego 
 	lo tenemos que convertirlo a PDF para poder recién enviarlo por mail manualmente.


 	********Documentacion
 	menu-config.xml------------------------------------------------------------------------------------------------
 	<Item name="GeneracionDocumentosMenu" title="generacionDocumentosList.title" page="/generacionDocumentos.html"
 	 width="250" roles="ROLE_USER,ROLE_BOLETOS,ROLE_FACTURACION"/>

 	struts.xml----------------------------------------------------------------------------------------------------
 	<action name="generacionDocumentos" class="com.zeni.app.webapp.action.GeneracionDocumentosListAction" method="list">
        <result>/WEB-INF/pages/generacionDocumentosList.jsp</result>
    </action>

    Action 	--> GeneracionDocumentosListAction.java
    views  	--> generacionDocumentosList.jsp
    		--> clausulasPreview.jsp
    Filtro  --> generacionDocumentosFiltro.jsp

    clausulasPreview.jsp -->
    <action name="imprimirDocumento" class="com.zeni.app.webapp.action.DocumentoDeNegocioReportAction" method="printDocumento">
	    <result name="success" type="noOp"/>
	    <result name="successMail">/WEB-INF/pages/clausulasPreview.jsp</result>
	    <result name="error" type="redirectAction">generacionDocumentos</result>
	</action>

	private String getImprimirDdn(DocumentoDeNegocio ddn) { -->Contrato.java


	
	***Se revisa metodo previewClausulas() de DocumentoDeNegocioReportAction.java
	<action name="clausulasCartaOferta" class="com.zeni.app.webapp.action.DocumentoDeNegocioReportAction" method="previewClausulas">
    	<result name="success">/WEB-INF/pages/clausulasCartaOferta.jsp</result>
    </action>

    ****Archivos
    select * from TIPO_DOCUMENTO_NEGOCIO;
	select * from boleto where CONTRATO_ID = (select id from contrato where numerocontrato = '19/09846/4');
	select * from CARTA_OFERTA order by id desc;--200544080   (19/11359/1)
	select * from contrato where id =  200481221; 


	SELECT clausula 
	FROM ClausulaDocumentoNegocio clausula
	JOIN clausula.tipoDocumentoNegocios as tdn
	LEFT JOIN clausula.bolsas as bolsa
	WHERE clausula.vigente=true
	AND tdn.id= :idtipoDocumentoNegocio
	AND ((clausula.cuentaCliente is null 
		OR (clausula.cuentaCliente.id= :idCuentaClienteVendedor 
			AND clausula.aplicaCuandoEsVendedor=true) 
		OR (clausula.cuentaCliente.id= :idCuentaClienteComprador 
			AND clausula.aplicaCuandoEsComprador=true))
	AND (clausula.cliente is null 
		OR (clausula.cliente.id= :idClienteVendedor 
			AND clausula.aplicaCuandoEsVendedor=true) 
		OR (clausula.cliente.id= :idClienteComprador 
			AND clausula.aplicaCuandoEsComprador=true)))
	AND (clausula.sucursal is null OR clausula.sucursal.id= :idSucursal)
	AND (bolsa.id= :idBolsa OR :idBolsa is null)
	ORDER BY clausula.orden	


	{idClienteComprador=169144709, idCuentaClienteVendedor=94847569, idClienteVendedor=28846, idSucursal=3722, idtipoDocumentoNegocio=3964, idCuentaClienteComprador=169144725, idBolsa=null}

	/*SELECT count(*)
	FROM Clausula_Documento_Negocio clausula
	inner join rel_clausula_docn_tipo_docn rcdtd
	  on clausula.id = rcdtd.clausuladocumentonegocio_id
	inner join tipo_documento_negocio tdn
	 on rcdtd.tipodocumentonegocio_id = tdn.id
	left outer join rel_clausula_bolsa rcb
	  on clausula.id = rcb.clausuladocumentonegocio_id 
	left outer join bolsa b
	  on rcb.bolsa_id = b.id
	where clausula.vigente = 1
	and tdn.id= 3964
	and (clausula.cuentacliente_id is null 
	      or (clausula.cuentaCliente_id = 94847569
	          and clausula.aplicaCuandoEsVendedor=1)
	      or (clausula.cuentaCliente_id = 169144725
	          and clausula.aplicaCuandoEsComprador=1))
	  and (
	            clausula.cliente_id is null 
	            or clausula.cliente_id= 28846
	            and clausula.aplicaCuandoEsVendedor=1 
	            or clausula.cliente_id= 169144709
	            and clausula.aplicaCuandoEsComprador=1
	        ) 
	  and (
	        clausula.sucursal_id is null 
	        or clausula.sucursal_id=3722
	    ) 
	 and (
	            b.id=null 
	            or null is null
	        ) 
	    order by
	        clausula.orden*/


	*** El metodo  getReportPath() devuelve el path del reporte, para luego 
    	String reportPath = getReportPath(getReports(ddn), ddn.getTipoDocumentoNegocio().getFamilia());
		DataSource atachmentArr = null;
        atachmentArr = JasperReportUtil.doReport_toMemory(getRequest(),
        		   reportPath, 
        		   format, 
        		   getReportParams(), null,
        		   String.format("%s-%s.%s", getReportName(reportPath), ddn.getContrato().getNumeroContrato().replaceAll("/", ""), format.toLowerCase()), 
        		   null, rptList, "JRBeanCollectionDataSource", "application/pdf");
        SendEMailUtil.SendEmail(mailEngine, to, ccopia, null, ((cartaOferta == null)?"BOLETO ":cartaOferta) + ddn.getContrato().getNumeroContrato() + " " + ddn.getContrato().getVendedor().getCliente().getRazonSocial(), 
        		new Date(),(cartaOferta==null)?bodyMailBoleto:bodyMailCartaOferta,atachmentArr);
        mailEnviado = "MAIL ENVIADO";


    ***Reporte ENVIADO
        /reports/CartaOferta/CartaOferta.jasper


	******Modificaciones
	***Contrato.java

		if("CartaOfertaComprador".equals(ddn.getTipoDocumentoNegocio().getCodigo())){
    		sb.append(String.format("<td class='docPrint' style='padding-right: 15px;'><a href=\"javascript:popupWin.openUrl('popupClausulasPreview', 'clausulasCartaOferta.html?decorator=popup&id=%s&familia=%s')\">Imprimir</a></td>",
					ddn.getId().toString(), ddn.getTipoDocumentoNegocio().getFamilia()));
    	}


    ***struts.xml
    	<action name="clausulasCartaOferta" class="com.zeni.app.webapp.action.DocumentoDeNegocioReportAction" method="previewClausulas">
        	<result name="success">/WEB-INF/pages/clausulasCartaOferta.jsp</result>
        </action>

        <action name="imprimirDocumentoCF" class="com.zeni.app.webapp.action.DocumentoDeNegocioReportAction" method="printDocumento">
            <result name="success" type="noOp"/>
            <result name="successMail">/WEB-INF/pages/clausulasCartaOferta.jsp</result>
            <result name="error" type="redirectAction">generacionDocumentos</result>
        </action>

    ***Se creo clausulasCartaOferta.jsp

    ***ClausulaDocumentoNegocioManagerImpl.java
        Se modifica el metodo --> public List<ClausulaDocumentoNegocio> getClausulas()
        	if (StringUtils.equalsIgnoreCase("CartaOferta", documentoDeNegocio.getTipoDocumentoNegocio().getFamilia())){
					 Set<ClausulaDocumentoNegocio> hs = new HashSet<ClausulaDocumentoNegocio>();
					 hs.addAll(clausulasAll);
					 clausulasAll.clear();
					 clausulasAll.addAll(hs);
					 ClausulaDocumentoNegocio aux ;
					 for(int i=0; i<=hs.size()-1;i++){
						for (int j = i+1; j <= hs.size()-1 ; j++) {
							if (clausulasAll.get(j).getId() < clausulasAll.get(i).getId()){
								aux = clausulasAll.get(i);
								clausulasAll.set(i, clausulasAll.get(j));
								clausulasAll.set(j, aux);
							}
						} 
					}

	***DocumentoDeNegocioReportAction.java
		Se añade atributo para el body del email
		private static final String bodyMailCartaOferta = 
    		"<table style='width:530px;border-collapse:collapse;font-family:Helvetica;font-size:13px;color:#4D4D4D;'>" +
    		"<tr>" +
    		"<td>" +
    		"Sres. Vendedores:<br/><br/>" +
    		"Les adjuntamos la carta oferta de ref. para la firma.<br/><br/>" +
    		"Estimaremos,<br/><br/>" +
    		"1) Imprimir 5 juegos.<br/><br/>" +
    		"2) Firmar cada página con sello social por la/s persona/s habilitada/s en las bolsas. (Aclarar nombre/s y DNI)<br/><br/>" +
    		"3) Esta carta oferta debe estar en poder del comprador 48 horas antes del pago de la factura.<br/><br/>" +
    		"4) Agradeceremos su rápido envío, a la calle Arias 1639  piso 11  C.P. 1429DWA C.A.B.A..<br/><br/>" +
    		"</br>" +
    		"Por consultas, por favor, referirse al sector Boletos.<br/><br/>" +
    		"</td>" +
    		"</tr>" +
    		"</table>";

		Se modifica el metodo  --> public String enviarMail() { 

			String cartaOferta = null;
				
				if (StringUtils.equalsIgnoreCase("CartaOferta", ddn.getTipoDocumentoNegocio().getFamilia())){
					cartaOferta = "Carta Oferta" ;
				}

			SendEMailUtil.SendEmail(mailEngine, to, ccopia, null, (cartaOferta == null)?"BOLETO ":cartaOferta + ddn.getContrato().getNumeroContrato() + " " + ddn.getContrato().getVendedor().getCliente().getRazonSocial(), 
	            		new Date(),(cartaOferta==null)?bodyMailBoleto:bodyMailCartaOferta,atachmentArr);
************************************************************************************************************************Implementado




7.**********************************************************************************************************************
************************************************************************************************************************
**************************************************************Ticket#1013342 — RV: Slip de boletos - slipContratos.jrxml
	Usuario 	--> JUAN EMILIANO
	Ext			--> 6235
	Area		--> Administración - Rosario
	Descripcion:Adjunto como salieron hoy en Rosario…3 negocios en una hoja… obviamente que no salen, por cada hoja tengo dos 
	mitades de dos negocios diferentes y un solo negocio entero, imposible de seguir…

	*****Documentacion

	<Item name="GeneracionDocumentosMenu" title="generacionDocumentosList.title" page="/generacionDocumentos.html" width="250" roles="ROLE_USER,ROLE_BOLETOS,ROLE_FACTURACION"/>

	<action name="generacionDocumentos" class="com.zeni.app.webapp.action.GeneracionDocumentosListAction" method="list">
        <result>/WEB-INF/pages/generacionDocumentosList.jsp</result>
    </action>
		    
    ***generacionDocumentosList.jsp
    <s:submit value="Generar Slip" cssClass="button" method="printSlip" key="button.generarSlip" theme="simple" cssStyle="width: 210px"/>

    <action name="slipContrato" class="com.zeni.app.webapp.action.GeneracionDocumentosListAction" method="printSlip">
        <result>/WEB-INF/pages/generacionDocumentosList.jsp</result>
    </action>

    ***GeneracionDocumentosListAction.java
    	


    ***Contrato 
    public String printSlip()  {
    		@NamedQuery(name = "contratos.SinSlip", query = "SELECT c FROM Contrato c"
				+ " WHERE c.slip=false and c.fechaBaja is NULL and c.state.workflowDefinition.estadoActual in('15','20') and c.operatoria not in ('I', 'S', 'M') order by c.comprador.id asc"),
		
		Select w2.estadoactual,c.* 
		From Contrato c,WORKFLOW_STATE w1,
		workflow_Definition w2
		where c.state_id = w1.id
   		and w1.WORKFLOWDEFINITION_ID = w2.ID
		and c.slip = 0
		and c.fechaBaja is null
		and w2.estadoactual in('15','20')
		and c.operatoria not in ('I', 'S', 'M') 
		order by c.comprador_id asc
		

  
   select from contrato c where slip = 0 and Fechabaja is null;
  
  update contrato set slip = 0 where NUMEROCONTRATO = '19/09181/0' and FECHABAJA is null;  
  update contrato set slip = 0 where NUMEROCONTRATO = '19/09204/6' and FECHABAJA is null;
  
  --sc
  update contrato set slip = 0 where NUMEROCONTRATO = '19/09143/6' and FECHABAJA is null;  
  update contrato set slip = 0 where NUMEROCONTRATO = '19/09182/2' and FECHABAJA is null;
  
  update contrato set slip = 0 where NUMEROCONTRATO = '19/13269/2' and FECHABAJA is null;  
  update contrato set slip = 0 where NUMEROCONTRATO = '19/13252/1' and FECHABAJA is null;
  

  update contrato set slip = 0 where NUMEROCONTRATO = '19/13218/5' and FECHABAJA is null;  
  update contrato set slip = 0 where NUMEROCONTRATO = '19/09182/2' and FECHABAJA is null;
  
  update contrato set slip = 0 where NUMEROCONTRATO = '19/13269/2' and FECHABAJA is null;  
  update contrato set slip = 0 where NUMEROCONTRATO = '19/13230/3' and FECHABAJA is null;

   update contrato set slip = 0 where NUMEROCONTRATO = '19/13310/5' and FECHABAJA is null;  
  update contrato set slip = 0 where NUMEROCONTRATO = '19/13244/8' and FECHABAJA is null;
  
  update contrato set slip = 0 where NUMEROCONTRATO = '19/13238/9' and FECHABAJA is null;  
  update contrato set slip = 0 where NUMEROCONTRATO = '19/13272/5' and FECHABAJA is null;



  ****Modificaciones en el reporte jasper
 	($V{PAGE_COUNT}.intValue()==2) || $F{AFijar} || ($F{comentarios}.toString().length() > 50)
************************************************************************************************************************Implementado





8.**********************************************************************************************************************
************************************************************************************************************************
*********************************************************Ticket#1013328 — Sistema extremadamente lento y precios ajustes
	Usuario 	--> FACUNDO IBAÑEZ
	Ext			--> 5017
	Area		--> IM - Nave de Producción
	Descripcion: Los filtros no funcionan, por ejemplo lo ordenas por código (especie) y los lista mal. 
	Si en el mercado solo se busca listar “Byma” no trae a todos los productos que incluye dicho mercado.
	

	menu-config.xml 	--> Futuros y Opciones 	--> Configuracion 	--> MAT Precio Ajuste 
	struts              --> struts-Mat.xml
	Action 				--> MatPrecioAjusteAction.java
	views				-->	matPrecioAjusteList.jsp
						--> matPrecioAjusteFiltro.jsp


	***Revisar --> vista de -->Garantías y Tenencia Histórica

	***El filtro se dirije al siguiente accion
	<action name="filtrarMATPrecioAjuste" class="com.zeni.app.webapp.action.MatPrecioAjusteAction"
		method="list">
		<interceptor-ref name="jsonValidationWorkflowStack"/>
		<result>/WEB-INF/pages/matPrecioAjusteList.jsp
		</result>
	</action>

	/*select
        * 
    from
        ( select
            this_.id as y0_,
            matproduct3_.codigo as y1_,
            matproduct3_.nombre as y2_,
            matproduct1_.codigo as y3_,
            matproduct1_.codigoInterno as y4_,
            matproduct1_.nombre as y5_,
            matproduct4_.nombre as y6_,
            this_.posicion as y7_,
            this_.fecha as y8_,
            this_.precioAjuste as y9_ 
        from
            mat_precio_ajuste this_ 
        left outer join
            mat_producto matproduct1_ 
                on this_.matProducto_id=matproduct1_.id 
        left outer join
            mat_base matproduct4_ 
                on matproduct1_.destino_id=matproduct4_.id 
        left outer join
            mercado matproduct3_ 
                on matproduct1_.mercado_id=matproduct3_.id 
        left outer join
            rel_mat_producto_mercado mercados9_ 
                on matproduct1_.id=mercados9_.matProducto_id 
        left outer join
            mercado matproduct2_ 
                on mercados9_.mercado_id=matproduct2_.id 
        where
            matproduct2_.id=? 
            and this_.fechaBaja is null 
            and this_.fecha=? 
        order by
            matproduct2_.nombre asc ) 
    where
        rownum <= ?*/

    select MATPRODUCTOPADRE_ID, NOMBRE, CODIGO, CODIGOINTERNO from MAT_PRODUCTO where id = 201629955 order by id desc;
	select * from REL_MAT_PRODUCTO_MERCADO where MATPRODUCTO_ID = 201629955;

	select MATPRODUCTOPADRE_ID, NOMBRE, CODIGO, CODIGOINTERNO from MAT_PRODUCTO where id = 160000000 order by id desc;
	select * from REL_MAT_PRODUCTO_MERCADO where MATPRODUCTO_ID = 160000000;

	INSERT INTO REL_MAT_PRODUCTO_MERCADO (MATPRODUCTO_ID,MERCADO_ID)  
	VALUES (201629955, 83095463);


	SELECT DISTINCT(MATPRODUCTOPADRE_ID) FROM MAT_PRODUCTO WHERE FECHABAJA IS NULL;


	1)	Corregir la carga de un hijo para cree la/las relaciones en REL_MAT_PRODUCTO_MERCADO con el/los mercados que 
		tenga asociados el padre. (Futuros y opciones>> configuración >> productos mat) “Detalle de productos”
	2)	Insertar  las relaciones para los productos que no tengan asociación. (Spre que corresponda)

	******Modificaciones:

	INSERT INTO REL_MAT_PRODUCTO_MERCADO (MATPRODUCTO_ID,MERCADO_ID)
	select mp.id, mp.mercado_id 
	FROM MAT_PRODUCTO mp
	where mp.id not in (
	                    SELECT mp.id  
	                    FROM MAT_PRODUCTO mp , rel_mat_producto_mercado rmpm
	                    WHERE FECHABAJA IS NULL
	                    and mp.MATPRODUCTOPADRE_ID is not null
	                    and mp.MERCADO_ID = rmpm.MERCADO_ID
	                    and mp.id = rmpm.MATPRODUCTO_ID)
	and mp.FECHABAJA is null
	and mp.MATPRODUCTOPADRE_ID is not null
	***********************************************************************************************************Problema 2 
		insercion en la tabla de relacion REL_MAT_PRODUCTO_MERCADO

		matProductoForm.jsp--> 
			<action name="editMatProductoDetalleProducto" class="com.zeni.app.webapp.action.MATProductoAction"
				method="editDetalleProducto">
				<interceptor-ref name="jsonValidationWorkflowStack" />
				<result>/WEB-INF/pages/matProductoDetalleProductoForm.jsp</result>
			</action>

			--> El action genera un objeto y va a -->matProductoDetalleProductoForm.jsp
				this.mATProducto = (MATProducto) manager.get(MATProducto.class, id);

		matProductoDetalleProductoForm.jsp
			<s:form id="saveMatProductoDetalleProducto" action="saveMatProductoDetalleProducto" method="post" onsubmit="return ajaxValidateForm(this.id,null)">

			/*<action name="saveMatProductoDetalleProducto" class="com.zeni.app.webapp.action.MATProductoAction"
				method="saveMatProductoDetalleProducto">
				<interceptor-ref name="jsonValidationWorkflowStack" />
				<result name="input">/WEB-INF/pages/matProductoDetalleProductoForm.jsp</result>
				<result name="cancel" type="redirectAction">mATProductoes</result>
				<result name="delete" type="redirectAction">mATProductoes</result>
				<result name="success" type="redirectAction">mATProductoes</result>
				<result name="editMatProductoDetalleProducto" type="redirectAction">
					<param name="actionName">editMatProductoDetalleProducto</param>
					<param name="popUp">${popUp}</param>
					<param name="decorator">${decorator}</param>
					<param name="id">${mATProducto.id}</param>
				</result>
			</action>*/

			saveMatProductoDetalleProducto()
				if(isNew) {
					//Copiar los datos desde el Padre
					copiarDatosDelPadre();
				}

			*****modificacion:
			Se añade para que registre el nuevo objeto en la tabla de relacion
			if(isNew) {
					manager.executeSQL("INSERT INTO REL_MAT_PRODUCTO_MERCADO VALUES ("+mATProducto.getId()+","+mATProducto.getMercado().getId()+")");
				}
	***********************************************************************************************************Problema 3
		Descripcion: Se relaizan modificaciones para la edicion del precio del producto por Id

		private FilterCondition matProductoFilter = new FilterCondition();
		
		public String editPrecio() {
			
			if (id != null) {
				this.matProductoId = id;	
				this.matProducto= new MATProducto();
				this.matProducto= (MATProducto) manager.findById(MATProducto.class, matProductoId);
				String query="select mpa.id from Mat_Precio_Ajuste mpa where mpa.matProducto_id = "+id+" " +
								"and mpa.fechaAlta = (select max(mpa2.fechaAlta) from Mat_Precio_Ajuste mpa2 " +
														"where mpa2.matProducto_id ="+id+" and mpa2.fechaBaja is null) " +
								"and mpa.fechaBaja is null";
				List<Object> lista  = (List<Object>)manager.executeSQL(query);
				if(lista.size()>0){
					long mpa = Long.parseLong(lista.get(0).toString());
					this.matPrecioAjuste = new MatPrecioAjuste();
					this.matPrecioAjuste = (MatPrecioAjuste) manager.get(MatPrecioAjuste.class, mpa);
					//////////matP.collectionResult = this.matPrecioAjuste;
					//Filtro por el cliente para traer las provincias de IIBB que tenga el cliente
					//matProductoFilter.addCondition("matProducto.id", "=", "matProductoId");
					//matProductoFilter.set("matProductoId", this.matPrecioAjuste.getId());
		        					
				}
			} else {
				this.matPrecioAjuste = new MatPrecioAjuste();
				//recuperarUltimoIngresado();
			}
			validateGrants(matPrecioAjuste);
			return SUCCESS;
		}

		select mpa.* 
		from Mat_Precio_Ajuste mpa 
		where mpa.matProducto_id =176082573 
		and mpa.fechaAlta = (select max(mpa2.fechaAlta) 
		                    from Mat_Precio_Ajuste mpa2 
		                    where mpa2.matProducto_id =176082573 
		                    and mpa2.fechaBaja is null) 
		and mpa.fechaBaja is null


		******Modificaciones:
		***MatPrecioAjusteAction.java
			public String edit() {
				if (id != null) {
					this.matPrecioAjuste = (MatPrecioAjuste) manager.get(MatPrecioAjuste.class, id);
				} else {
					this.matPrecioAjuste = new MatPrecioAjuste();
					if(matProductoId != null){
						String query="select mpa.id from Mat_Precio_Ajuste mpa where mpa.matProducto_id = "+matProductoId+" " +
								"and mpa.fechaAlta = (select max(mpa2.fechaAlta) from Mat_Precio_Ajuste mpa2 " +
														"where mpa2.matProducto_id ="+matProductoId+" and mpa2.fechaBaja is null) " +
								"and mpa.fechaBaja is null";
						List<Object> lista  = (List<Object>)manager.executeSQL(query);
						if(lista.size()>0){
							this.matPrecioAjuste = (MatPrecioAjuste) manager.get(MatPrecioAjuste.class, Long.parseLong(lista.get(0).toString()));
						}				
					}
				}
				validateGrants(matPrecioAjuste);
				return SUCCESS;
			}

		***matAvalList.jsp
		/*<a href="editMatPrecioAjuste.html?matProductoId=${item.matProducto.id}" target="_blank"><img border="0" onmouseover="javascript:this.src='images/EditarF00.gif';" onmouseout="javascript:this.src='images/Editar00.gif';" src="images/Editar00.gif"></a>
		*/		
************************************************************************************************************************Implementado




9.**********************************************************************************************************************
************************************************************************************************************************
**************************************Ticket#1013735 — Agregar botones PDF y RTF en el envío automático de Cartas Oferta
	Usuario 	--> JUAN EMILIANO
	Ext			--> 6235
	Area		--> Administración - Rosario
	Descripcion: Necesitamos que en el sub módulo de envío agreguen la los "botones"  PDF y  RTF para poder visualizar 
	y descargar el boleto antes de enviarlo por sistema. 

	struts.xml----------------------------------------------------------------------------------------------------

	***clausulasCartaOferta.jsp

	****Modificacion:
	***clausulasCartaOferta.jsp
	Se agregaron botones para el pdf y Word

	/*<td><div style="margin-left: 10px"><s:submit cssClass="button" method="printDocumento" key="clausulasPreview.popup.print" theme="simple"/></div></td>
	<td><div style="margin-left: 10px"><s:submit cssClass="button" method="printDocumentoRTF" key="clausulasPreview.popup.print.rtf" theme="simple"/></div></td>*/		   		
************************************************************************************************************************Implementado





10.*********************************************************************************************************************
************************************************************************************************************************
***********************************************************************************Ticket#1013756 — RV: contratos nietos
	Usuario 	--> FUX JAVIER
	Ext			--> 5012
	Area		--> Mercaderías	Casa Central
	Descripcion:Hoy para los nietos (no importa el comprador) sale una profoma de boleto especial. Necesitamos modificar 
	esto. Todo cto. nieto donde NO figure en el árbol de relación, Syngenta, salga un boleto tradicional o carta oferta.
	Paso algunos ejemplos de ctos.
 
		19/097046 comprador Sigma agro  --> Unico ejemplo a tomar en cuenta*********
	19/080928 comprador agrotecnos  --> Consultar --> no es nieto
		19/082487 comprador agrotecnos  --> Consultar --> aparece Syngenta como comprador en una de las patas
	19/078765 comprador Agrotecnos  --> Consultar --> no es nieto
	19/078741 comprador Agrotecnos  --> Consultar --> aparece Syngenta como comprador en una de las patas
	19/107564 comprador Agrotecnos  --> Consultar --> aparece Syngenta como comprador en una de las patas

	Nota: Los casos de prueba ha consultar, ninguno fueron positivos, no tomar en cuenta. ( ya esta validado con el 
		usuario)

	Comentarios:  Actualmente a los contratos nietos se imprime un documento especial, se requiere que en caso de que 
	sea contrato nieto valide si en ninguana de sus patas sea el comprador Syngenta y dde ser true impprimir cartaOferta
	normal.

	******Documentacion:
	menu-config.xml------------------------------------------------------------------------------------------------
 	<Item name="GeneracionDocumentosMenu" title="generacionDocumentosList.title" page="/generacionDocumentos.html"
 	 width="250" roles="ROLE_USER,ROLE_BOLETOS,ROLE_FACTURACION"/>

 	/*<action name="generacionDocumentos" class="com.zeni.app.webapp.action.GeneracionDocumentosListAction" method="list">
        <result>/WEB-INF/pages/generacionDocumentosList.jsp</result>
    </action>

    	<meta name="filtro" content="/WEB-INF/pages/generacionDocumentosFiltro.jsp"/>*/

	generarBoletoContratoNieto

	reporte que genera  --> _JASPER_NIETO_AFIJAR

	*****Modificaciones:

	***Se crea un nuevo metodo para validar los nietos en la clase Contrato.java, se valida si Syngenta es comprador en alguno de sus nodos.

		//Metodo que valida si documento tiene en alguno de sus nodos como comprador a Syngenta
		public boolean validarNieto(DocumentoDeNegocio ddn){
			List<Object> lista = null;
			try {
				String consulta = "SELECT (SELECT c.id FROM Cliente c INNER JOIN CUENTA_CLIENTE cc	ON c.Id = cc.CLIENTE_ID	WHERE CC.ID = con.COMPRADOR_ID) ," +
						"(SELECT c.id FROM Cliente c INNER JOIN CUENTA_CLIENTE cc ON c.Id = cc.CLIENTE_ID WHERE CC.ID = (select con2.COMPRADOR_ID from contrato con2 where con2.id = hijo.contrato_id)) ," +
						"(SELECT c.id FROM Cliente c INNER JOIN CUENTA_CLIENTE cc ON c.Id = cc.CLIENTE_ID WHERE CC.ID = (select con3.COMPRADOR_ID from contrato con3 where con3.id = madre.contrato_id)) " +
						"FROM CANJE_CONTRATO_COMP_CEREAL hijo " +
						"LEFT join CONTRATO con " +
						"ON con.CANJE_ID = hijo.CANJE_ID " +
						"LEFT JOIN CANJE_CONTRATO_VTA_CEREAL madre " +
						"ON  hijo.CANJE_ID = madre.CANJE_ID " +
						"WHERE con.NUMEROCONTRATO = '"+ddn.getContrato().getNumeroContrato()+"'";
				lista = (List<Object>) manager.executeSQL(consulta);
				if (lista != null ){
					for (Object item : lista) {					
						Object[] objectItem = (Object[]) item;					
						long contrato = ((objectItem[0] != null) ? Long.parseLong(objectItem[0].toString()) : null);
						long hijo = ((objectItem[1] != null) ? Long.parseLong(objectItem[1].toString()) : null);
						long madre = ((objectItem[2] != null) ? Long.parseLong(objectItem[2].toString()) : null);
						
						if ((contrato == 29419 || contrato == 30474) || (hijo == 29419 || hijo == 30474) || (madre == 29419 || madre == 30474)){
							return false;
						}					
					}				
				};
			} catch (Exception e) {
				e.getMessage();
			}
			
			return true;
		}

	***Se agrega validacion validarNieto(ddn) al metodo --> getImprimirDdn(DocumentoDeNegocio ddn) para que entre en 
		Carta Oferta, en caso de no ser nieto y no tener a Syngenta como comprador en ninguno de sus nodos.
		
		if((ddn.getContrato().getEsNieto()==null || !ddn.getContrato().getEsNieto()) || validarNieto(ddn)){


	***Se agrega validacion validarNieto(ddn) al metodo --> getImprimirDdn(DocumentoDeNegocio ddn) para que entre en 
		Boleto, en caso de no ser nieto y no tener a Syngenta como comprador en ninguno de sus nodos.

		else if (StringUtils.equals("Boleto", ddn.getTipoDocumentoNegocio().getFamilia()) && 
			((ddn.getContrato().getEsNieto()==null || !ddn.getContrato().getEsNieto()) || validarNieto(ddn))) {
************************************************************************************************************************Implementado





11.*********************************************************************************************************************
************************************************************************************************************************
***********************************************************************************Ticket#1013805 — RV: Contratos nietos
	Usuario 	--> FUX JAVIER
	Ext			--> 5012
	Area		--> Mercaderías	Casa Central
	Descripcion:
		Cuando un negocio es nieto y es a fijar, da este error:
			Ejemplo 19/10756/4

	*****Documentacion:
		****Nota:
		El error se presenta cuando el contrato no posee precio de mercaderia 

	*****Modificacion:
		***ContratoNietoSyngentaAction 	--> generarBoletoContrato()
		Descripcion: Se añade validacion para que en caso de que sea null inserte cadena vacia en los parametros del reporte
		getReportParams().put("precio", (contrato.getPrecioMercaderia() != null)?contrato.getPrecioMercaderia().toString():"");
************************************************************************************************************************Implementado





12.*********************************************************************************************************************
************************************************************************************************************************
******************************************************************************************Ticket#1013832 — boletos nieto
	Usuario 	--> FUX JAVIER
	Ext			--> 5012
	Area		--> Mercaderías	Casa Central
	Descripcion:
		Les paso esta incidencia para ver y que salgan bien los boletos, ya que duplica una línea y no muestra lo que correpsonde.

	*****Documentacion:
		Se analizo el metodo generarBoletoContrato() de la clase ContratoNietoSyngentaAction.java, se esta añadiendo un obj fcn
		con la misma referencia a la lista --> facturaContratoNietoList.add(fcn); al setearle los atributos al objeto fcn sobreescriben 
		y en la lista se graban con la misma referencia en memoria por lo que en la lista quedan los mismos datos en cada registro, 
		solucion, se debe crear el objeto dentro del ciclo for para que por cada iteracion se cree una referencia en memoria distinta. 


	*****Modificacion:
		***ContratoNietoSyngentaAction 	--> generarBoletoContrato()
		for (Object[] facturaContratoNieto : facturasContratoNieto) {
			FacturasContratoNieto fcn = new FacturasContratoNieto();
************************************************************************************************************************Implementado




***Reporte
13.*********************************************************************************************************************
************************************************************************************************************************
*********************************************************************************Ticket#1012974 — SIC - Consulta pactado
	Usuario 	--> ANTOGNOLI NICOLAS (D)		
	Ext			--> 5004	
	Area		--> Pago a Proveedores	Casa Central
	Descripcion:Buenos días,
	   Quisiera saber si se podría crear una nueva consulta para listar lo pactado:
	 
		Así me exporto en Excel los siguientes datos:
		·         Fecha de oper.
		·         Op. Vend.
		·         Producto
		·         Pactado
		·         Sucursal
		·         Operatoria 
		Desde la Consulta Analitica de Contratos se demora mucho, importandome datos que no me hacen falta.	

	*****Documentacion:	


	*****Modificacion:
		***menu-config.xml
			/*<Item name="ReporteConsultaPactado" title="Reporte Consulta Pactado" page="/consultaPactado.html" roles="ROLE_USER,ROLE_CLIENTE_VIEW,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION,ROLE_BOLETOS,ROLE_SUBPRODUCTOS,ROLE_IMPUESTOS" width="250"/> 
        	<Item name="Saludar" title="login" page="/saludar.html" roles="ROLE_USER,ROLE_CLIENTE_VIEW,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION,ROLE_BOLETOS,ROLE_SUBPRODUCTOS,ROLE_IMPUESTOS" width="250"/> 
        	*/
		
	    *****Se crea Action --> ConsultaPactadoAction.java
        	import groovy.lang.PropertyValue;
        	import java.math.BigDecimal;
			import java.util.ArrayList;
			import java.util.Date;
			import java.util.HashMap;
			import java.util.List;
			import java.util.Map;
			import java.util.ResourceBundle;

			import org.apache.struts2.convention.annotation.Action;
			import org.apache.struts2.convention.annotation.Result;
			import org.apache.struts2.convention.annotation.Results;

			import com.opensymphony.xwork2.Preparable;
			import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
			import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
			import com.opensymphony.xwork2.validator.annotations.ValidatorType;
			import com.zeni.app.service.ContratoManager;
			import com.zeni.app.util.DateUtil;
			import com.zeni.app.util.HashMapOrderByValue;
			import com.zeni.app.util.PropertyValues;

			@Results({
					@Result(name = "success", location = "/WEB-INF/pages/reportesConsultaPactadosList.jsp"),
					@Result(name = "input", location = "/WEB-INF/pages/reportesConsultaPactadosList.jsp") })
			public class ConsultaPactadoAction extends BaseAction implements Preparable {

				private static final long serialVersionUID = -8312974938496627302L;
				private static ContratoManager contratoManager;
				private List<ConsultaPactados> listaConsultaP = new ArrayList<ConsultaPactados>();
				private Date fechaDesde;
				private Date fechaHasta;
				private String numeroContrato;
				private List<String> operatoria;
				private String operadorDeMesaId;

				public String getOperadorDeMesaId() {
					return operadorDeMesaId;
				}

				public void setOperadorDeMesaId(String operadorDeMesaId) {
					this.operadorDeMesaId = operadorDeMesaId;
				}

				public List<String> getOperatoria() {
					return operatoria;
				}

				public void setOperatoria(List<String> operatoria) {
					this.operatoria = operatoria;
				}

				public String getNumeroContrato() {
					return numeroContrato;
				}


				public void setNumeroContrato(String numeroContrato) {
					this.numeroContrato = numeroContrato;
				}

				public Date getFechaHasta() {
					return fechaHasta;
				}

				public void setFechaHasta(Date fechaHasta) {
					this.fechaHasta = fechaHasta;
				}

				public Date getFechaDesde() {
					return fechaDesde;
				}

				public void setFechaDesde(Date fechaDesde) {
					this.fechaDesde = fechaDesde;
				}

				public List<ConsultaPactados> getListaConsultaP() {
					return listaConsultaP;
				}

				public void setListaConsultaP(List<ConsultaPactados> listaConsultaP) {
					this.listaConsultaP = listaConsultaP;
				}

				public ContratoManager getContratoManager() {
					return contratoManager;
				}

				public void setContratoManager(ContratoManager contratoManager) {
					this.contratoManager = contratoManager;
				}

				@Action("consultaPactado")
				public String execute() {
					return SUCCESS;
				}

				@Override
				public void validate() {
					if (getNumeroContrato() == null) {
						setFieldErrors(null);
					}
				}	

				@Action("reporteConsultaPactado")
				public String repConsultaPactados() {
					List<Object[]> lista = null;
					List<String> listaOperatoria = null;
					String stringOperatoria = "";

					listaOperatoria = getOperatoria();
					if (listaOperatoria != null) {
						stringOperatoria = " AND con.OPERATORIA IN (";
						boolean bandera = false;
						for (String string : listaOperatoria) {
							if (bandera) {
								stringOperatoria += ",'" + string + "'";
							} else {
								stringOperatoria += "'" + string + "'";
								bandera = true;
							}
						}
						stringOperatoria += ")";
					}
					Date fechaDesde = getFechaDesde();
					Date fechaHasta = getFechaHasta();
					String contrato = !(getNumeroContrato() == null) ? (!(getNumeroContrato()
							.equals("")) ? " AND con.NUMEROCONTRATO = '"
							+ getNumeroContrato() + "'" : " ") : " ";
					String opMesaId = (getOperadorDeMesaId() != null) ? (!(getOperadorDeMesaId()
						.equals("")) ? " AND (SELECT operadordemesa_id FROM cuenta_cliente WHERE id = con.VENDEDOR_ID) = "
							+ getOperadorDeMesaId() : " ") : " ";
					int id = 0;
					if (!(fechaDesde == null) && !(fechaHasta == null)) {
						try {

							String consulta = "SELECT TO_CHAR(con.FECHAOPERACION, 'dd/mm/yyyy'), "
									+ "(SELECT razonSocial FROM Cliente C "
									+ "INNER JOIN CUENTA_CLIENTE  CC ON C.Id = CC.CLIENTE_ID "
									+ "INNER JOIN CONTRATO CON1 ON CC.ID = CON.VENDEDOR_ID  "
									+ "WHERE con1.NUMEROCONTRATO = con.NUMEROCONTRATO), "
									+ "con.cantidadPactada, "
									+ "(SELECT OM.nombre FROM operador_de_mesa OM WHERE OM.id = con.OPERADORDEMESA_ID), "
									+ "(select Descripcion from Producto where id = con.Producto_ID), "
									+ "(SELECT NOMBRE FROM SUCURSAL WHERE ID = SUCURSALADMINISTRATIVA_ID), "
									+ " con.operatoria "
									+ "FROM CONTRATO con "
									+ "WHERE con.FECHAOPERACION >= to_date('"
									+ DateUtil.getDate(fechaDesde)
									+ "','dd/MM/yyyy') "
									+ "AND con.FECHAOPERACION <= to_date('"
									+ DateUtil.getDate(fechaHasta)
									+ "','dd/MM/yyyy') "
									+ contrato + stringOperatoria + opMesaId;

							lista = (List<Object[]>) contratoManager.executeSQL(consulta);
							if (lista != null && lista.size() > 0) {
								for (Object item : lista) {
									Map<Character, String> mapa = PropertyValues
											.getOperatorias();
									ConsultaPactados cp;
									Object[] objectItem = (Object[]) item;
									id = id + 1;
									String fecha = ((objectItem[0] != null) ? objectItem[0]
											.toString() : null);
									String vendedor = ((objectItem[1] != null) ? objectItem[1]
											.toString() : null);
									double cantPactada = ((objectItem[2] != null) ? Double
											.parseDouble(objectItem[2].toString()) : 0);
									String opMesa = ((objectItem[3] != null) ? objectItem[3]
											.toString() : null);
									String producto = ((objectItem[4] != null) ? objectItem[4]
											.toString() : null);
									String sucursal = ((objectItem[5] != null) ? objectItem[5]
											.toString() : null);
									String codOperatoria = ((objectItem[6] != null) ? mapa
											.get(objectItem[6]) : null);

									cp = new ConsultaPactados(id, fecha, vendedor,
											cantPactada, opMesa, producto, sucursal,
											codOperatoria);

									listaConsultaP.add(cp);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						addActionError("La fecha de inicio y de fin son necesarias para la busqueda");
					}
					return SUCCESS;
				}

				public class ConsultaPactados {
					private int id;
					private String fechaOperac;
					private String vendedor;
					private double cantPactada;
					private String opMesa;
					private String producto;
					private String sucursal;
					private String codOperatoria;

					public ConsultaPactados(int id, String fechaOperac, String vendedor,
							double cantPactada, String opMesa, String producto,
							String sucursal, String codOperatoria) {
						super();
						this.id = id;
						this.fechaOperac = fechaOperac;
						this.vendedor = vendedor;
						this.cantPactada = cantPactada;
						this.opMesa = opMesa;
						this.producto = producto;
						this.sucursal = sucursal;
						this.codOperatoria = codOperatoria;
					}

					public double getCantPactada() {
						return cantPactada;
					}

					public void setCantPactada(double cantPactada) {
						this.cantPactada = cantPactada;
					}

					public String getOpMesa() {
						return opMesa;
					}

					public void setOpMesa(String opMesa) {
						this.opMesa = opMesa;
					}

					public int getId() {
						return id;
					}

					public void setId(int id) {
						this.id = id;
					}

					public String getFechaOperac() {
						return fechaOperac;
					}

					public void setFechaOperac(String fechaOperac) {
						this.fechaOperac = fechaOperac;
					}

					public String getVendedor() {
						return vendedor;
					}

					public void setVendedor(String vendedor) {
						this.vendedor = vendedor;
					}

					public String getProducto() {
						return producto;
					}

					public void setProducto(String producto) {
						this.producto = producto;
					}

					public String getSucursal() {
						return sucursal;
					}

					public void setSucursal(String sucursal) {
						this.sucursal = sucursal;
					}

					public String getCodOperatoria() {
						return codOperatoria;
					}

					public void setCodOperacion(String codOperacion) {
						this.codOperatoria = codOperacion;
					}

					@Override
					public String toString() {
						return "ConsultaPactados [id=" + id + ", fechaOperac="
								+ fechaOperac + ", vendedor=" + vendedor + ", cantPactada="
								+ cantPactada + ", opMesa=" + opMesa + ", producto="
								+ producto + ", sucursal=" + sucursal + ", codOperacion="
								+ codOperatoria + "]";
					}
				}
			}

		*****Se crea reportesConsultaPactadosList.jsp
			<%@ include file="/common/taglibs.jsp"%>
			<head>
				<title><fmt:message key="rcpList.title"/></title>
				<meta name="heading" content="<fmt:message key='rcpList.heading'/>"/>
				<meta name="filtroTitle" content="<fmt:message key='rcpFilter.filtro.title'/>"/>
				<meta name="filtro" content="/WEB-INF/pages/repConsultaPactadoFiltro.jsp" />
			</head>

			<%@ include file="auditEntity.jspf" %>				


			<ria:simplegrid id="repoGrid"
							collection="${listaConsultaP}"
							rowPerPage="${rowPerPage}"
							width="860px" height="400px"
							var="item"
							rowCountVar="rowCount"
							resizable="true"
							showPropertyButton="true"
							selectionMode="single"
							onSetData="gridNotifyOnEmpty(this, __Messages__.gridNoData)" 
							oldQueryMethod="false">
			    <ria:simplegridcolumn label='FECHA OPERACION' object="${item.fechaOperac}" width="150" id="fecha_CTO" style="text-align:center; paddind:10px"/>
			    <ria:simplegridcolumn label='VENDEDOR' object="${item.vendedor}" width="150" id="vendedor" style="text-align:center;paddind:10px;"/>
			    <ria:simplegridcolumn label='PACTADO' width="150" id="cantPactada" style="text-align:center;paddind:10px;">
    				<fmt:formatNumber type="number" maxFractionDigits = "2" value="${item.cantPactada}"/></ria:simplegridcolumn>
			    <ria:simplegridcolumn label='OP. MESA' object="${item.opMesa}" width="200" id="opMesa" style="text-align:center;paddind:10px;"/>
			   	<ria:simplegridcolumn label='PRODUCTO' object="${item.producto}" width="200" id="nContrato" style="text-align:center;paddind:10px;"/>
			   	<ria:simplegridcolumn label='SUCURSAL' object="${item.sucursal}" width="200" id="sucursal" style="text-align:center;paddind:10px;"/>
			   	<ria:simplegridcolumn label='OPERATORIA' object="${item.codOperatoria}" width="150" id="Operatoria" style="text-align:center;paddind:10px;"/>   	
				<ria:gridinnerexport id="innerexport" filename="Contratos Pactados"/>
			</ria:simplegrid>

		*****Se crea repConsultaNietoFiltro.jsp 
			<%@ include file="/common/taglibs.jsp"%>

			/*<s:form id="repConsultaPactadoFiltro" action="reporteConsultaPactado"
				method="post" validate="true">
				<table width="100%">
					<tr>
						<td align="left" valign="top">
							<table>
								<tr>
									<td valign="top" style="padding-left: 12px"><sx:datetimepicker
											key="fechaDesde" label="Desde: " required="false"
											cssClass="text" displayFormat="%{datePattern}"
											templateCssPath="%{#datetimepickerCSS}" cssStyle="width:100px" />
									</td>
									<td valign="top" style="padding-left: 12px"><sx:datetimepicker
											key="fechaHasta" label="Hasta: " required="false"
											cssClass="text" displayFormat="%{datePattern}"
											templateCssPath="%{#datetimepickerCSS}" cssStyle="width:100px" />
									</td>
									<td valign="top" style="padding-left: 12px"><s:textfield
											name="numeroContrato" label="Contrato:" required="false"
											cssClass="text size100" maxlength="255" /></td>
									<td valign="top" style="padding-left: 12px"><s:action
											name="operadorDeMesas" var="operadorDeMesas" /> <s:select
											key="operadorDeMesaId" label="Op. Mesa"
											list="%{#operadorDeMesas.tiposOperadoresDeMesaVigentes}"
											listKey="id" listValue="nombre" emptyOption="true"
											required="false" cssClass="text size120" /></td>
									<td style="padding-left: 10px"><s:select
											id="contrato_operatoria" key="operatoria" label="Operatoria"
											size="2" multiple="true"
											list="%{@com.zeni.app.util.PropertyValues@getOperatorias()}"
											required="false" emptyOption="true" cssClass="text size120"
											style="width:120px;" /></td>
									<td style="padding-left: 25px;"><input type='button'
										style="width: 60px; margin-right: 5px; margin-left: auto; margin-right: auto; width: 80px; height: 25px;"
										class="button" value="<fmt:message key="button.clearfilter"/>"
										onclick="clearForm(this.form);" /> <s:submit cssClass="button"
											style="width: 60px; margin-right: 5px; margin-left: auto;  margin-right: auto; width: 80px; height: 25px;"
											action="reporteConsultaPactado" key="button.filter" /></td>
								</tr>
							</table>
						</td>

					</tr>
				</table>
			</s:form> */

		***Se modifica ApplicationResources.properties
			# -- Reporte Consulta Pactado
			rcpList.title=Reporte Consulta Pactados
			rcpFilter.filtro.title = Filtro - Reportes Consulta Pactados
			rcpList.heading = Reportes Consulta Pactados     


		----------------------------------------------------------------------------------------------------executeSQL
			SELECT TO_CHAR(con.FECHAOPERACION, 'dd/mm/yyyy'), 
			(SELECT razonSocial FROM Cliente C 
			  INNER JOIN CUENTA_CLIENTE  CC ON C.Id = CC.CLIENTE_ID
			  INNER JOIN CONTRATO CON1 ON CC.ID = CON.VENDEDOR_ID 
			  WHERE con1.NUMEROCONTRATO = con.NUMEROCONTRATO),
			con.cantidadPactada,
			(SELECT OM.nombre FROM operador_de_mesa OM WHERE OM.id = con.OPERADORDEMESA_ID),
			(select Descripcion from Producto where id = con.Producto_ID), 
			(SELECT NOMBRE FROM SUCURSAL WHERE ID = SUCURSALADMINISTRATIVA_ID),  
			con.operatoria 
			FROM CONTRATO con 
			WHERE con.FECHAOPERACION >= to_date('01/06/2019','dd/MM/yyyy') 
			AND con.FECHAOPERACION <= to_date('31/07/2019','dd/MM/yyyy')  
			AND con.NUMEROCONTRATO = '19/14174/8'
			AND con.OPERATORIA IN ('C')
			AND con.OPERADORDEMESA_ID = 3776


			SELECT razonSocial FROM Cliente c INNER JOIN CUENTA_CLIENTE cc ON c.Id = cc.CLIENTE_ID where cc.id = 3776 


			/*(SELECT razonSocial FROM Cliente C inner join CUENTA_CLIENTE  CC c.Id = cc.CLIENTE_ID
			  INNER JOIN CONTRATO CON1 ON CC.ID = CON.VENDEDOR_ID 
			  WHERE con1.NUMEROCONTRATO = con.NUMEROCONTRATO)		  
			  
			  SELECT nombre FROM operador_de_mesa WHERE id = 3776*/		
************************************************************************************************************************Implementado





14.*********************************************************************************************************************
************************************************************************************************************************
*********************************************************************Ticket#1013270 — liquidacion 331002644283 con error
	Usuario 	--> Juan Ignacio Dimitroff
	Ext			--> 5061
	Area		--> Mercaderías
	Descripcion:
		Por favor ver porque al hacer una liquidacion y tildar “mostrar detalle de cartas de porte”, en las observaciones 
		de la liqudiacion aparecen los Kilos BRUTOS y no los netos.
		Esto genera rechazos de las liquidaciones.
		Por favor modificar la leyenda para que aparezcan los kilos netos aplicados al contrato. 
	

	*****Documentacion:
		struts-Finales.xml
			facturacionFinalForm.jsp
				<jsp:include page='facturacionFinalEntregasDetallado.jsp' flush="true"/>
			FacturacionFinalAction.java


	*****Modificacion: no se realizaron modificaciones ya que el usuario indica que no se viene presentando el incidente		
************************************************************************************************************************Implementado




***Store Procedure
15.*********************************************************************************************************************
************************************************************************************************************************
*************************************************Ticket#1013558 — RV: consulta analitica sin cto canje asociado en excel
	Usuario 	--> Juan Ignacio Dimitroff
	Ext			--> 5061
	Area		--> Mercaderías
	Descripcion:
	El listar los cttos de la consulta analítica los contratos tienen la relación de canje cargada o su respectivo 
	“arbolito”	El problema, es que al bajar la lista en Excel, no aparecen relacionados los contratos de canje… lo que 
	nos obliga a completar los listados de forma manual


	SQL:********************************************************************************************************
		select * from contrato where NUMEROCONTRATO = '18/11382/5'  --contrato.id 187286325

		select * from canje where id = 187286325

		select * from contrato where NUMEROCONTRATO = '19/08461/9'  --contrato.id 198424694

		select * from canje where id = 198424694

		SELECT CONTRATOCANJEASOCIADO_ID FROM contrato


		SELECT C.CONTRATOCANJEASOCIADO_ID,C.ESCANJE, C.NUMEROCONTRATO, C.* FROM CONTRATO WHERE C.NUMEROCONTRATO LIKE (

		'18/11554/0',
		'18/11552/6',
		'18/11382/5',
		'18/13233/6',
		'18/13734/8',
		'18/14391/5',
		'19/01023/6',
		'19/00868/7',
		'18/13676/4',
		'19/06983/7',
		'19/07348/8',
		'19/08461/9',
		'18/12722/2')


		Caso de prueba #1:   --> contrato1 = compra
		null	201225664	1	19/12816/2	201223942	201225664	201223939	null

		Caso de prueba #2:  -->(contrato1 = venta and compra is not null)
		null	201316542	1	19/12935/0	201312449	201316542	201312446	null

		update CANJE_CONTRATO_COMP_CEREAL set contrato_id = 201312446 where CANJE_ID = 201312449;
		update CANJE_CONTRATO_VTA_CEREAL set contrato_id = 201316542 where CANJE_ID = 201312449;

		Caso de prueba #3:  -->(contrato1 = venta and nieto is not null)
		null	201173990	1	19/12700/7	201173431	201173990	201173427	null

		update CANJE_CONTRATO_VTA_CEREAL set contrato_id = 201173990 where CANJE_ID = 201173431;
		insert into CANJE_CONTRATO_NIETO values (201173431,201173427)
		delete CANJE_CONTRATO_COMP_CEREAL where CANJE_ID = 201173431;


		Caso de prueba #4:  --> (contrato1 = nieto and venta is not null)
		null	201173427	1	19/12694/2	null 		null 		null 	null			
		null	201223939	1	19/12812/4	null 		null 		null 	null	

		null 	201173427	1	19/12694/2	19532145	14992108	null 	null	

		    select c.canje_id,c.* from contrato c where numerocontrato = '19/12694/2'
    		update contrato set canje_id = 19532145 where numerocontrato = '19/12694/2'
    
    		select * from canje
    		insert into CANJE_CONTRATO_NIETO values (19532145,201173427)
    		insert into CANJE_CONTRATO_VTA_CEREAL values (19532145,14992108)
    
	Procedimiento Almacenado:***********************************************************************************

		DECLARE 
		     contratoCanjeAsociado CONTRATO.CONTRATOCANJEASOCIADO_ID%type;
		     contrato1 CONTRATO.ID%type;
		     esCanje CONTRATO.ESCANJE%type; 
		     numeroContrato CONTRATO.NUMEROCONTRATO%type; 
		     idCanje CONTRATO.CANJE_ID%type;
		     compra CANJE_CONTRATO_COMP_CEREAL.CONTRATO_ID%TYPE;
		     venta CANJE_CONTRATO_VTA_CEREAL.CONTRATO_ID%type;
		     nieto CANJE_CONTRATO_NIETO.CONTRATO_ID%TYPE;     
		     CURSOR cursor1 is 
		        SELECT C.CONTRATOCANJEASOCIADO_ID , C.ID , C.ESCANJE, C.NUMEROCONTRATO, C.CANJE_ID, H.CONTRATO_ID , V.CONTRATO_ID, N.CONTRATO_ID 
		        FROM CONTRATO C
		        LEFT JOIN
		        CANJE_CONTRATO_COMP_CEREAL H
		        ON C.CANJE_ID = H.CANJE_ID
		        LEFT JOIN
		        CANJE_CONTRATO_VTA_CEREAL V
		        ON C.CANJE_ID = V.CANJE_ID
		        LEFT JOIN
		        CANJE_CONTRATO_NIETO N
		        ON C.CANJE_ID = N.CANJE_ID
		        WHERE C.CONTRATOCANJEASOCIADO_ID  is NULL 
		        AND C.CANJE_ID IS NOT NULL
		        AND C.ESCANJE = 1
		        AND (C.ID = H.CONTRATO_ID OR C.ID = V.CONTRATO_ID OR C.ID = N.CONTRATO_ID)
		        AND C.FECHAALTA > '01/01/2017';
		  BEGIN 
		     OPEN cursor1; 
		     LOOP 
		     FETCH cursor1 into contratoCanjeAsociado, contrato1, esCanje, numeroContrato, idCanje, compra, venta, nieto; 
		        EXIT WHEN cursor1%notfound; 
		        IF contrato1 = compra 
		          THEN
		            INSERT INTO CONTRATOPRE VALUES(contratoCanjeAsociado,contrato1, 'MODIFICACION VENTA: ' || venta);
		            UPDATE CONTRATO 
		            SET CONTRATOCANJEASOCIADO_ID = venta
		            WHERE  CONTRATO.id = contrato1;            
		        ELSIF (contrato1 = venta and compra is not null)  
		          THEN
		            INSERT INTO CONTRATOPRE VALUES(contratoCanjeAsociado,contrato1, 'MODIFICACION COMPRA: ' || compra); 
		            UPDATE CONTRATO 
		              SET CONTRATOCANJEASOCIADO_ID = compra
		              WHERE  CONTRATO.id = contrato1;               
		        ELSIF (contrato1 = venta and nieto is not null)
		          THEN 
		            INSERT INTO CONTRATOPRE VALUES(contratoCanjeAsociado,contrato1, 'MODIFICACION NIETO: ' || nieto);
		            UPDATE CONTRATO 
		              SET CONTRATOCANJEASOCIADO_ID = nieto
		              WHERE  CONTRATO.id = contrato1;
		        ELSIF (contrato1 = nieto and venta is not null)
		          THEN 
		            INSERT INTO CONTRATOPRE VALUES(contratoCanjeAsociado,contrato1, 'MODIFICACION VENTA CON NIETO: ' || venta);
		            UPDATE CONTRATO 
		              SET CONTRATOCANJEASOCIADO_ID = venta
		              WHERE  CONTRATO.id = contrato1;
		        END IF;
		     END LOOP; 
		     CLOSE cursor1; 
		  END; 
		/
************************************************************************************************************************Implemetado




***Se agrega Columna
16.*********************************************************************************************************************
************************************************************************************************************************
*******************************************************************Ticket#Sin numero de Incidencia Peticion de Juan Arpi
	Usuario 	--> Juan Ignacio Dimitroff
	Ext			--> 5061
	Area		--> Mercaderías
	Descripcion:
	Se solicito crear campo adicional de observacion al crear y editar un cliente


	****Modificacion del ClienteForm.jsp

		<div style="width:100%; padding:3px;">
			<div style="width:25%; float:left;">
				<fieldset style="width: 100%;">			
					<legend><fmt:message key="cliente.inscriptoEnBolsaTech"/></legend>
					<table style="width:100%;border: 1px solid black;border-collapse: collapse">
						<tr>
							<th style="border: 1px solid black;border-collapse: collapse; text-align: center;padding: 5px">SÍ</th>
							<th style="border: 1px solid black;border-collapse: collapse; text-align: center;padding: 5px">NO</th>
						</tr>
						<tr>
							<td style="border: 1px solid black;border-collapse: collapse; text-align: left;padding: 5px">
								<div align="center">
									<s:checkbox id="inBolsaTechSi" cssStyle="align:center;" key="cliente.inBolsaTech" cssClass="checkbox" theme="simple" value="(cliente.inBolsaTech)?true:false" onchange="onChangeBolsaTech('si')"/>
								</div>	
							</td>
							<td style="border: 1px solid black;border-collapse: collapse; text-align: left;padding: 5px">
								<div align="center">
									<s:checkbox id="inBolsaTechNo" cssStyle="align:center;" key="cliente.inBolsaTech" cssClass="checkbox" value="(cliente.inBolsaTech)?false:true" theme="simple" onchange="onChangeBolsaTech('no')" />
								</div>	
							</td>
						</tr>
						<tr>
							<td style="border: 1px solid black;border-collapse: collapse" colspan = "4">
								<div id="divVtoBolsaTech">
									<sx:datetimepicker label="Vencimiento" key="cliente.vtoBolsaTech" id="vtoBolsaTech"
									required="false" cssClass="text" displayFormat="%{datePattern}" 
									templateCssPath="%{#datetimepickerCSS}" value="%{fechaDesde}"/>
								</div>
								<div id="divObsBolsaTech">
									<s:textarea id="obsBolsaTech" label="Observaciones" key="cliente.obsBolsaTech" style="width:95%;" rows="2" cols="50"/>
								</div>
							</td>
						</tr>
					 </table>
				</fieldset>
			</div >
			<div style="width:70%; float:right;">
				<s:textarea id="observacion" label="Observaciones del Cliente:" key="cliente.observacion" style="width:75%; height:100px" />
			</div>
		</div>


	*******se agrago atributo al bean Cliente

		private String observacion;

		@Column
	    public String getObservacion() {
	    	return observacion;
	    }
	    
	    public void setObservacion(String observacion) {
	    	this.observacion = observacion;
	    }



	***** Se modifico el ApplicationResources.properties
	    cliente.observacion=Observacion

	*****Se modifico el clienteList.jsp
	    <ria:simplegridcolumn label="cliente.observacion" width="400" key="$item.observacion" id="observacion"/>
************************************************************************************************************************Implementado



        
***Reporte
17.*********************************************************************************************************************
************************************************************************************************************************
***************************************************Ticket#1010069 — Archivo resumen con operaciones mercado de capitales
	Usuario 	--> FACUNDO IBAÑEZ
	Ext			--> 5017
	Area		--> IM - Nave de Producción
	Descripcion: 

	Necesitamos obtener un archivo en Excel “diario” que resuma bajo estos títulos: 
 
		Denominación Cliente
			CUIT
			Especie
			Plazo
			Moneda
			C/V
			Cantidad
			Precio
			Monto
 
 
		Las operaciones de los clientes que operaron en mercado de capitales (no entran fondos / futuros)
 
	Hay que tener en cuenta que un mismo cliente puede comprar  / vender a distintos precios y/o cantidades. En estos 
	casos solo necesitamos una línea resumida de la especie operada.


	*****Documentacion
		***menu-config.XML
			<Menu name="CapitalesMenu"   title="menu.Capitales" description="MercadoFinanciero" roles="ROLE_USER,ROLE_MAT,ROLE_COMERCIAL" page="" width="140">
            <Item name="MatOperacionCapitalesMenu" title="matOperacionList.title" page="/matOperacionCapitales.html" width="220" roles="ROLE_USER,ROLE_MAT,ROLE_COMERCIAL"/>
        ***struts.xml
        	<action name="matOperacionCapitales" class="com.zeni.app.webapp.action.MatOperacionCapitalesAction" method="list">
        ***vista
        	<result>/WEB-INF/pages/matOperacionCapitalesList.jsp</result>
        ***filtro
        	<meta name="filtro" content="/WEB-INF/pages/matOperacionCapitalesFiltro.jsp"/>

		***struts.xml
			<result name="detalleOperacionesCapitales" type="redirectAction">
				<param name="actionName">matRegistrosCapitales</param>    
				
				<action name="matRegistrosCapitales" class="com.zeni.app.webapp.action.MatRegistroListCapitalesAction" method="list">
					<interceptor-ref name="jsonValidationWorkflowStack" />
					<result>/WEB-INF/pages/matRegistroCapitalesList.jsp</result>
				</action>   

	******Sentencias SQL
		select C.razonsocial, 
		      C.cuit,
		      matO.id,
		      matO.cuentacliente_id, 
		      matO.horasaliquidar,
		      (select nombre from moneda where id = (select moneda_id from mat_Operacion where id = matO.id)) as moneda
		from mat_operacion matO
		inner join cuenta_cliente CC
		 on matO.cuentacliente_id = CC.id
		inner join cliente C
		 on C.Id = CC.CLIENTE_ID
		where matO.fecha ='22/07/2019'
		and matO.id = 203781646
		and matO.condicion not in ('R','S')


	*****Modificaciones:
		***menu-config.xml
			<Menu name="CapitalesMenu"   title="menu.Capitales" description="MercadoFinanciero" roles="ROLE_USER,ROLE_MAT,ROLE_COMERCIAL" page="" width="140">
				<Item name="ReporteOperClientes" title="Reporte Operaciones Clientes" page="/repOpClientes.html" width="220" roles="ROLE_USER,ROLE_MAT"/>

		***Se modifica ApplicationResources.properties
			# -- Reporte Consulta Operaciones Cliente
			rcopList.title=Reporte Consulta de Operaciones del Cliente
			rcopFilter.filtro.title = Filtro - Reportes de Operaciones Clientes
			rcopList.heading = Reportes de Operaciones Clientes
		

		***Se crea repConsOperClientesList.jsp
			<%@ include file="/common/taglibs.jsp"%>
			<head>
				<title><fmt:message key="rcopList.title"/></title>
				<meta name="heading" content="<fmt:message key='rcopList.heading'/>"/>
				<meta name="filtroTitle" content="<fmt:message key='rcopFilter.filtro.title'/>"/>
				<meta name="filtro" content="/WEB-INF/pages/repConsOperClientesFiltro.jsp" />
			</head>

			<%@ include file="auditEntity.jspf" %>				


			<ria:simplegrid id="repoGrid"
							collection="${listaConsultaOC}"
							rowPerPage="${rowPerPage}"
							width="860px" height="400px"
							var="item"
							rowCountVar="rowCount"
							resizable="true"
							showPropertyButton="true"
							selectionMode="single"
							onSetData="gridNotifyOnEmpty(this, __Messages__.gridNoData)" 
							oldQueryMethod="false">
			    <ria:simplegridcolumn label='CLIENTE' object="${item.cliente}" width="250" id="CLIENTE" style="text-align:center; paddind:10px"/>
			    <ria:simplegridcolumn label='CUIL/CUIT' object="${item.cuit}" width="150" id="CUIL/CUIT" style="text-align:center;paddind:10px;"/>
			    <ria:simplegridcolumn label='ESPECIES' object="${item.especies}" width="200" id="ESPECIES" style="text-align:center;paddind:10px;" />
			    <ria:simplegridcolumn label='PLAZO' object="${item.plazo}" width="100" id="PLAZO" style="text-align:center;paddind:10px;"/>
			   	<ria:simplegridcolumn label='MONEDA' object="${item.moneda}" width="150" id="MONEDA" style="text-align:center;paddind:10px;"/>
			   	<ria:simplegridcolumn label='C/V' object="${item.condicion}" width="150" id="CONDICION" style="text-align:center;paddind:10px;"/>
			   	<ria:simplegridcolumn label='CANTIDAD' object="${item.cantidad}" width="100" id="CANTIDAD" style="text-align:center;paddind:10px;"/>  
			   	/*<ria:simplegridcolumn label='PRECIO' object="${item.precio}" width="100" id="PRECIO" style="text-align:center;paddind:10px;" formatter="formatoImporteJS"/> 	
				<ria:simplegridcolumn label='MONTO' object="${item.monto}" width="100" id="MONTO" style="text-align:center;paddind:10px;" formatter="formatoImporteJS"/> 
				<ria:gridinnerexport id="innerexport" filename="Contratos Pactados"/>
			</ria:simplegrid>*/



			<script type="text/javascript">	

				function formatoImporteJS(pImporte){
					if(!pImporte || isNaN(pImporte.toString().toFloat()))
						pImporte='0';
					return pImporte.toString().toFloat().toFixed(2).toString();
				}
				
			</script>


		***Se crea repConsOperClientesFiltro.jsp
			<%@ include file="/common/taglibs.jsp"%>
			<s:form id="repConsOperClientesFiltro" action="consOperClientes"
				method="post" validate="true">
				
				<table style="margin: auto">		
						<tr>
						<td valign="top" style="padding-left: 12px"><sx:datetimepicker
								key="fechaDesde" label="Desde: " required="false" cssClass="text"
								displayFormat="%{datePattern}"
								templateCssPath="%{#datetimepickerCSS}" cssStyle="width:100px" />
						</td>
						<td valign="top" style="padding-left: 12px"><sx:datetimepicker
								key="fechaHasta" label="Hasta: " required="false" cssClass="text"
								displayFormat="%{datePattern}"
								templateCssPath="%{#datetimepickerCSS}" cssStyle="width:100px" />
						</td>
						<td valign="top" style="padding-left: 12px"><s:textfield
								name="cuitCliente" label="CUIT:" required="false"
								cssClass="text size100" maxlength="255" /></td>
						<td style="padding-left: 25px; "><input type='button'
										style="width: 60px; margin-right: 5px; margin-left: auto; margin-right: auto; width: 80px; height: 25px; margin-top: 10px;"
										class="button" value="<fmt:message key="button.clearfilter"/>"
										onclick="clearForm(this.form);" />
						</td>
						<td style="padding-left: 25px;"><s:submit cssClass="button"
								style="width: 60px; margin-right: 5px; margin-left: auto;  margin-right: auto; width: 80px; height: 25px; margin-top: 10px;"
								action="consOperClientes" key="button.filter" /></td>
					</tr>
				</table>

			</s:form>

		***Se crea Action ConsultaOperClientesAction.java

			@Results({
				@Result(name = "success", location = "/WEB-INF/pages/repConsOperClientesList.jsp")	 
				})
			public class ConsultaOperClientesAction extends BaseAction implements Preparable{
				
				private static ContratoManager contratoManager;
				private List<OperacionesClientes> listaConsultaOC = new ArrayList<OperacionesClientes>();
				private Date fechaDesde;
				private Date fechaHasta;
				private String cuitCliente;
				public Date getFechaDesde() {
					return fechaDesde;
				}
				public void setFechaDesde(Date fechaDesde) {
					this.fechaDesde = fechaDesde;
				}
				public Date getFechaHasta() {
					return fechaHasta;
				}
				public void setFechaHasta(Date fechaHasta) {
					this.fechaHasta = fechaHasta;
				}
				public String getCuitCliente() {
					return cuitCliente;
				}
				public void setCuitCliente(String cuitCliente) {
					this.cuitCliente = cuitCliente;
				}	
				
				public ContratoManager getContratoManager() {
					return contratoManager;
				}
				
				public void setContratoManager(ContratoManager contratoManager) {
					this.contratoManager = contratoManager;
				}
				
				@Action("repOpClientes")
				public String execute() {
					return SUCCESS;
				}	
				
				@Action("consOperClientes")
				public String repConsultaPactados() {
					List<Object[]> lista = null;
					
					Date fechaDesde = getFechaDesde();
					Date fechaHasta = getFechaHasta();
					String stringCuit = !(getCuitCliente() == null) ? (!(getCuitCliente()
							.equals("")) ? " AND C.CUIT = '"
							+ getCuitCliente() + "'" : " ") : " ";
					int id = 0;
					
					if (!(fechaDesde == null) && !(fechaHasta == null)) {
						try {
							String consulta ="select C.razonsocial, " +
									"C.cuit, " +
									"(SELECT mp.nombre FROM mat_producto mp  WHERE mp.id = matO.matproducto_id) , " +
									"matO.horasaliquidar as Plazo,"+
									"matO.id, " +
									"matO.cuentacliente_id, " +
									"(select nombre from moneda where id = (select moneda_id from mat_Operacion where id = matO.id)), " +
									"case when matO.condicion = 'C' then 'Compra' " +
									"      when matO.condicion = 'V' then 'Venta' " +
									"end, " +
									"matO.volumen, " +
									"matO.preciooprima " +
									"from mat_operacion matO " +
									"inner join cuenta_cliente CC " +
									"on matO.cuentacliente_id = CC.id " +
									"inner join cliente C " +
									"on C.Id = CC.CLIENTE_ID "+
									"WHERE matO.fecha >= to_date('"
									+ DateUtil.getDate(fechaDesde)
									+ "','dd/MM/yyyy') "
									+ "AND matO.fecha <= to_date('"
									+ DateUtil.getDate(fechaHasta)
									+ "','dd/MM/yyyy') "+
									"AND matO.condicion NOT IN ('R','S') "+ 
									"and matO.tipo = 'L'"+stringCuit;
									
							lista = (List<Object[]>) contratoManager.executeSQL(consulta);
							if (lista != null && lista.size() > 0) {
								for (Object item : lista) {
									Object[] objectItem = (Object[]) item;
									OperacionesClientes opeClie;
									id = id + 1;
									String cliente = ((objectItem[0] != null) ? objectItem[0].toString() : null);
									String cuit = ((objectItem[1] != null) ? objectItem[1].toString() : null);
									String producto = ((objectItem[2] != null) ? objectItem[2].toString() : null);
									int plazo = ((objectItem[3] != null) ? Integer.parseInt(objectItem[3].toString()) : 0);
									long operacion = ((objectItem[4] != null) ? Long.parseLong(objectItem[4].toString()) : 0);
									long cuentaCliente = ((objectItem[5] != null) ? Long.parseLong(objectItem[5].toString()) : 0);
									String moneda = ((objectItem[6] != null) ? objectItem[6].toString() : null);
									String condicion = ((objectItem[7] != null) ? objectItem[7].toString() : null);
									int cantidad = ((objectItem[8] != null) ? Integer.parseInt(objectItem[8].toString()) : 0);
									double precio = ((objectItem[9] != null) ? Double.parseDouble(objectItem[9].toString()) : 0);
									
									opeClie = new OperacionesClientes(id, cliente, cuit, producto, plazo, operacion, cuentaCliente, moneda, condicion, cantidad, precio);
									
									listaConsultaOC.add(opeClie);
								}
							}				
									
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else {
						addActionError("La fecha de inicio y de fin son necesarias para la busqueda");
					}
					return SUCCESS;
				}
				
				public class OperacionesClientes {
					private int id;
					private String cliente;
					private String cuit;
					private String especies;
					private int plazo;
					private long idOperacion;
					private long ctaCliente;
					private String moneda;
					private String condicion;
					private int cantidad;
					private double precio;
					private double monto;	
					

					public OperacionesClientes(int id, String cliente, String cuit,
							String especies, int plazo, long idOperacion, long ctaCliente,
							String moneda, String condicion, int cantidad, double precio) {
						super();
						this.id = id;
						this.cliente = cliente;
						this.cuit = cuit;
						this.especies = especies;
						this.plazo = plazo;
						this.idOperacion = idOperacion;
						this.ctaCliente = ctaCliente;
						this.moneda = moneda;
						this.condicion = condicion;
						this.cantidad = cantidad;
						this.precio = precio;
						this.monto = cantidad * precio;
					}

					public int getPlazo() {
						return plazo;
					}

					public void setPlazo(int plazo) {
						this.plazo = plazo;
					}

					public int getCantidad() {
						return cantidad;
					}

					public void setCantidad(int cantidad) {
						this.cantidad = cantidad;
					}

					public double getMonto() {
						return monto;
					}

					public void setMonto(double monto) {
						this.monto = getCantidad() * getPrecio();
					}

					public int getId() {
						return id;
					}

					public void setId(int id) {
						this.id = id;
					}

					public String getCliente() {
						return cliente;
					}

					public void setCliente(String cliente) {
						this.cliente = cliente;
					}

					public String getCuit() {
						return cuit;
					}

					public void setCuit(String cuit) {
						this.cuit = cuit;
					}

					public String getEspecies() {
						return especies;
					}

					public void setEspecies(String especies) {
						this.especies = especies;
					}

					public long getIdOperacion() {
						return idOperacion;
					}

					public void setIdOperacion(long idOperacion) {
						this.idOperacion = idOperacion;
					}

					public long getCtaCliente() {
						return ctaCliente;
					}

					public void setCtaCliente(long ctaCliente) {
						this.ctaCliente = ctaCliente;
					}

					public String getMoneda() {
						return moneda;
					}

					public void setMoneda(String moneda) {
						this.moneda = moneda;
					}

					public String getCondicion() {
						return condicion;
					}

					public void setCondicion(String condicion) {
						this.condicion = condicion;
					}

					public double getPrecio() {
						return precio;
					}

					public void setPrecio(double precio) {
						this.precio = precio;
					}

					@Override
					public String toString() {
						return "ConsultaPactados [id=" + id + ", cliente=" + cliente
								+ ", cuit=" + cuit + ", especies=" + especies + ", plazo="
								+ plazo + ", idOperacion=" + idOperacion + ", ctaCliente="
								+ ctaCliente + ", moneda=" + moneda + ", condicion="
								+ condicion + ", cantidad=" + cantidad + ", precio="
								+ precio + ", monto=" + monto + "]";
					}	
				
				}
			}
	Incidencias_________________________________________________________________________________________________
		Solicitud del usuario en la columna de la grilla correspondiente a plazo, cuando tenga 0 debe contener Contado Inmediato

		***Se modifica repConsOperClientesList.jsp, se le añade valñidacion al object
			<ria:simplegridcolumn label='PLAZO' object="${item.plazo == 0 ? 'Contado Inmediato' : item.plazo}" width="130" id="PLAZO" style="text-align:center;paddind:10px;"/>
	Incidencias_________________________________________________________________________________________________
		Modificacion segun solicitud del usuario para que la columna precio levante seis decimales

		***Se modifica repConsOperClientesList.jsp, se modifica metodo javascript formatoImporteJS para que formatee a seis decimales
			return pImporte.toString().toFloat().toFixed(6).toString(); 	
***********************************************************************************************************************Implementado




18.*********************************************************************************************************************
************************************************************************************************************************
***************************************************************************Ticket#1014114 — Reporte Estadistico Clientes
	Usuario 	--> ANTOGNOLI NICOLAS (D)		
	Ext			--> 5004	
	Area		--> Pago a Proveedores	Casa Central
	Descripcion:
	Se necesitaría saber los id de los productos por los cuales está conformado el informe de Estadístico Clientes.
	Es decir Producto Id 225449 Nombre Soja
	Solo los números, no me hacen falta los nombres.

	Al tener productos como Gas Oil y demás se que no los tiene en cuenta, pero quiero saber cuales son.


	*****Documentacion:
		menu-config.xml
		<Item name="ReporteEstadisticoClientes" title="Estadístico de Clientes" page="/editReporteEstadisticoCliente.html" roles="ROLE_USER,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P" width="250"/>
        	
        struts.xml
        <!-- ReporteEstadisticoClienteAction -START -->
        <action name="editReporteEstadisticoCliente" class="com.zeni.app.webapp.action.ReporteEstadisticoClienteAction"	method="edit">
			<result>/WEB-INF/pages/reporteEstadisticoCliente.jsp</result>
		</action>

		Vistas
		reporteEstadisticoCliente.jsp
		reporteEstadisticoClienteFiltro.jsp

			<s:form id="reporteEstadisticoClienteFilter" action="generarReporteEstadisticoCliente.html" method="post" onsubmit="return ajaxValidateForm(this.id,null)">
				<action name="generarReporteEstadisticoCliente"
					class="com.zeni.app.webapp.action.ReporteEstadisticoClienteAction"
					method="list">
					<interceptor-ref name="jsonValidationWorkflowStack" />
					<result name="input">/WEB-INF/pages/reporteEstadisticoCliente.jsp
					</result>
					<result name="success" type="noOp" />
				</action>
***********************************************************************************************************************Solucionado 




19.*********************************************************************************************************************
************************************************************************************************************************
*******************************************************************************Ticket#1013480 — RV: Anomalía del sistema
	Usuario 	--> Juan Ignacio Dimitroff
	Ext			--> 5061
	Area		--> Mercaderías
	Descripcion:

	Por favor ver porque cuando exportamos desde la consulta analítica el detalle completo de contratos 
	(Syngenta en este caso), sale vacio el contrato de canje asociado y el vendedor, a pesar de estar cargado y 
	asociado en el sistema
***********************************************************************************************************************Implementado





20.*********************************************************************************************************************
************************************************************************************************************************
*******************************************************************************Ticket#1013914 — RV: Anomalía del sistema
	Usuario 	--> ANTONELLI ALEJANDRO (D)		/ JUAN EMILIANO	 
	Ext			--> 5021  /  6235	
	Area		--> Mercaderías	Casa Central  / Administración	Rosario
	Descripcion:
 	Para el negocio 19/151646 se hizo una carta oferta.
 	Pero según el modulo donde observes el negocio, esta cargada o no. Ni desde la “Consulta Analítica” ni desde 
 	“Administración Documentación” se ve reflejada la carta oferta, pero si en “Seguimiento Documentación” y 
 	“Autorizador de Contratos”.	

 	*****Documentacion:

 		menu-config.xml
 		<Menu name="ComercialMenu" title="menu.comercial" description="Comercial" roles="ROLE_USER,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION" page=""	width="50">
        	<Item name="ConsultaAnaliticaDeContratosMenu" title="consultaAnaliticaDeContratos.heading" page="/consultaAnaliticaDeContratos.html" roles="ROLE_USER,ROLE_CLIENTE_VIEW,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION,ROLE_BOLETOS,ROLE_SUBPRODUCTOS,ROLE_IMPUESTOS" width="250"/>

        struts.xml
        	<action name="consultaAnaliticaDeContratos" class="com.zeni.app.webapp.action.ConsultaAnaliticaDeContratosAction" method="list">
	        	<interceptor-ref name="jsonValidationWorkflowStack" />
	            <result>/WEB-INF/pages/consultaAnaliticaDeContratosList.jsp</result>
	        </action>

	    *****vista
	        consultaAnaliticaDeContratosList.jsp
	        consultaAnaliticaDeContratosFiltro.jsp

	****Opcion Seguimiento Documentacion
		<Item name="SeguimientoDocumentacionBoletosMenu" title="seguimientoDocumentosBoletoList.title" page="/seguimientoDocumentosBoleto.html" width="250"/>
		    

	Modificaciones_________________________________________________________________________________________________
		Nota: Las modificaciones nunca se implementaron por no presentarse la incidencia
		ConsultaAnaliticaDeContratosAction.java
			private String tipoNegocio;

			public String getTipoNegocio() {
				return tipoNegocio();
			}

			public void setTipoNegocio(String tipoNegocio) {
				this.tipoNegocio = tipoNegocio;
			}


			public String tipoNegocio(){
				List<Object[]> lista = null;
				String consulta = "select tdn.familia from contrato c " +
						"inner join contrato_t_documento_negocio ctdn " +
						"on c.id = ctdn.contrato_id " +
						"inner join tipo_documento_negocio tdn " +
						"on ctdn.TIPO_DOCUMENTO_NEGOCIO_ID = tdn.id " +
						"where numerocontrato = "+filtro.getContratoId();
				String tn = "";
				lista = (List<Object[]>) contratoManager.executeSQL(consulta);
				if (lista != null && lista.size() > 0) {
					for (Object item : lista) {
						Object[] objectItem = (Object[]) item;
						tn = ((objectItem[0] != null) ? objectItem[0].toString() : null);
					}
				}		
				return tn;
			}

	        <s:property value="tipoNegocio" />

	         list="%{@com.zeni.app.util.PropertyValues@getOperatorias()}"

	         '<c:url value="/generarFacturaPDFDesdeBD.html"/>?idFact='

	         referencia -->FacturacionAction.java --> generarFacturaPDFDesdeBD()
	         FileUtils.downloadFile(path, ServletActionContext.getResponse(), "application/pdf");
***********************************************************************************************************************Cerrado por no presentarse la incidencia





21.*********************************************************************************************************************
************************************************************************************************************************
******************************************************************Ticket#1014115 — Agregar boton Legajo en Ficha cliente 
	Se modifica edicion de cliente para que abra archivo de Legajo del cliente en servidor esterno 10.2.0.243
	Usuario 	--> CENCIONE LUCRECIA	
	Ext			--> 5164		
	Area		--> Contaduría	Casa Central
	Descripcion:
 	necesitaríamos que nos agreguen el botón “Legajo” en SIC >> CRM >> Ficha cliente, el cual me debería linquear la 
 	carpeta del CUIT correspondiente que se encuentra en la siguiente ruta: 10.2.0.243


 	*****Documentacion:
 		menu-config.xml
 		<Menu name="CRMMenu"   title="menu.CRM" description="CRM" roles="ROLE_CRM,ROLE_APLICACION,ROLE_SUCURSAL" page="" width="40">
            <Item name="AMFichaCompletaCliente" title="CRM.AMFichaCompletaCliente" page="/clientesCRM.html" width="200"/>
        struts-CRM.xml
        <action name="clientesCRM" class="com.zeni.app.webapp.action.ClienteCRMAction"
			method="list">
			<result>/WEB-INF/pages/clienteCRMList.jsp</result>
		</action>

	*****vista
		clienteCRMList.jsp

			Va a editCRMFichaCliente.html?id=25696&idCliente=25696
			struts-CRM.xml

			<action name="editCRMFichaCliente" class="com.zeni.app.webapp.action.CRMFichaClienteAction"
				method="edit">
				<result>/WEB-INF/pages/crmFichaClienteForm.jsp</result>
				<result name="error">/WEB-INF/pages/clienteCRMList.jsp</result>
			</action>

			***vista 
				clienteCRMList.jsp


				<fmt:message key='cliente.cuit'/>: <b><s:property value="crmFichaCliente.cliente.cuit"/></b>
	Modificaciones__________________________________________________________________________________________
	CRMFichaClienteAction.java
		*****Se agrega atributo con su getter y setter;
			private String cuit;
			private String rutaGlobal;
			private String[] docClientes;
			private Map documentos = new HashMap();

		*****Se modifica metodo edit()
			if(cuit != null){
	        	abrirarchivo();
	        }

	    *****Se crea metodo abrirarchivo()
	    	public Map abrirarchivo(){
			    String fichero = ""; 
		    	try {	    	
			    	String cuitString = cuit.replace("-", "");
			    	//fichero="N:\\DOC. CLIENTES";
			    	fichero = manager.getSystemConfig("DocumentacionLegajo");
			    	File f = new File (fichero);  
			    	if (f.exists()){ 
			    		// Directorio existe 
			    		File[] ficheros = f.listFiles();
			            boolean bandera = false;
			            List<String> nombre;
			            for (int x=0;x<ficheros.length;x++){
			            	String nombreArchivo = (ficheros[x].getName().length()>=11)?ficheros[x].getName().substring(0, 11):"";	            	
			              if(cuitString.equalsIgnoreCase(nombreArchivo)){
			            	  rutaGlobal = fichero+"/"+ficheros[x].getName();
			            	  f = new File (rutaGlobal);	            	  
			            	  docClientes = f.list();	            	  
			            	  for (int i= 0;i<docClientes.length;i++) {
			            		Integer iPrueba = i;	            		
			            		String prueba = docClientes[i];
			            		documentos.put(iPrueba.toString(), docClientes[i]);
			            	  }
			            	  bandera = true;
			            	  break;
			              }
			            }  
			            if (!bandera){
			            	addActionError("No se ha encuentrado el archivo "+fichero);
			            }
			    	} else { 
			    		//Directorio no existe 
			    		addActionError("No se ha encuentrado el archivo "+fichero);
			    	} 
			    	
			     }catch (IllegalArgumentException e) {
			    	 addActionError("No se ha encuentrado el archivo "+fichero);
			     }    	 
			     return documentos;
			}

		*****Se crea metodo para descargar el archivo en el explorador
			 public void changeEstado(){
		    	String doc = getRequest().getParameter("documento");
		    	String cuit = getRequest().getParameter("cuit");
		    	String fichero = manager.getSystemConfig("DocumentacionLegajo") + "/";
		    	cuit = cuit.replace("-", "");
		    	String ruta = "";
		    	abrirarchivo();    	
				doc = documentos.get(doc).toString();
				ruta = rutaGlobal+"\\"+doc;	
				ruta = fichero+cuit+"/"+doc;
		    	try {
		    		if (ruta.contains(".pdf")){
		    			FileUtils.downloadFile( ruta, ServletActionContext.getResponse(), "application/pdf");
		    		}else{
		    			FileUtils.downloadZIP(ruta, ServletActionContext.getResponse());
		    		}  		
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		*****Se crea metodo onChange para abrir folder del usuario
			function onChange() {
				var cuit = document.getElementById("cuit").value;
				cuit = (cuit.replace("-","")).replace("-","");		
				var url ='file:///N:/DOC. CLIENTES/'+cuit+'/Mercado de Capitales/';
				if(url != null) {
					window.open(url)	
				}
			}


	crmFichaClienteDatos1.jsp
		*****Se modifica para que por medio de la url invoque al metodo changeEstado()
			<TD>
	    		<a href='editCRMFichaCliente.html?cuit=${crmFichaCliente.cliente.cuit}&id=${crmFichaCliente.id}&idCliente=${crmFichaCliente.cliente.id}' onclick="javascript: onChange();" 
	    		title='<s:text name="crmFichaCliente.button.consultar"/>' ><fmt:message key='cliente.cuit'/>: <b><s:property value="crmFichaCliente.cliente.cuit" /></a></b>		    	
	    	</TD>
	crmFichaClienteForm.jsp
		******Se crea validacion para la creacion de un select con los documentos del cliente
	    	<s:if test="cuit != null">
    			<TD>
    				<s:select name="docCliente" id="docCliente" list="documentos" label = "Documentos del Cliente:" style="width:175px;" onchange="javascript: onChangeEstado(%{crmFichaCliente.cliente.cuit});"/>
    			</TD>
    			<input type='hidden' name='cuit' value='${crmFichaCliente.cliente.cuit}' id='cuit' />
    		</s:if>	

    	*****Se crea funcion onChangeEstado(cuit) --> JavaScript para abrir ventana de descarga e invocar el metodo que lo ejecuta
    			function onChangeEstado(cuit) {
				var doc = document.getElementById("docCliente").value;
				var cuit = document.getElementById("cuit").value;
				var url ='<c:url value="/changeEstadoDocCli.html"/>?documento='+doc+'&cuit='+cuit;
				if(url != null) {
					window.open(url);
				}
			}

	***************************************************************************************************************
	Nota: Segunda modificacion, se agrega la compresion del directorio Mercado de Capitales a un .Zip
		public Map abrirarchivo(){
	    	String[] docClientesB;
		    String fichero = ""; 
	    	try {	    	
		    	String cuitString = cuit.replace("-", "");
		    	//fichero="N://DOC. CLIENTES";
		    	fichero = manager.getSystemConfig("DocumentacionLegajo");
		    	File f = new File (fichero);  
		    	if (f.exists()){ 
		    		// Directorio existe 
		    		File[] ficheros = f.listFiles();
		            boolean bandera = false;
		            List<String> nombre;
		            for (int x=0;x<ficheros.length;x++){
		            	String nombreArchivo = (ficheros[x].getName().length()>=11)?ficheros[x].getName().substring(0, 11):"";	            	
		              if(cuitString.equalsIgnoreCase(nombreArchivo)){
		            	  rutaGlobal = fichero+"/"+ficheros[x].getName();
		            	  f = new File (rutaGlobal);
		            	  File carpetaComprimir = new File (rutaGlobal); 
		            	  File[] ficherosb = carpetaComprimir.listFiles();
		            	  docClientes = f.list();	            	  
		            	  Integer iPrueba = 0;
		            	  for (int i= 0;i<ficherosb.length;i++) {
		            		  if (ficherosb[i].getName().contains("Mercado de capitales") && (!ficherosb[i].getName().contains(".zip") || ficherosb[i].getName().contains(".rar") )){	            			  
		            			  	FileOutputStream fos = new FileOutputStream(rutaGlobal+"/Mercado de capitales.zip");
		            		        ZipOutputStream zos = new ZipOutputStream(fos);
		            		        File arcZip = new File(rutaGlobal+"/Mercado de capitales/");
		            		        FileUtils.addDirAZip(zos, arcZip, null);
		            		        zos.flush();
		            		        fos.flush();
		            		        zos.close();
		            		        fos.close();
		            		  }
		            	  }
		            	  docClientes = f.list();
		            	  for (int i= 0;i<docClientes.length;i++) {	            			       
		            		if (docClientes[i].contains(".zip") || docClientes[i].contains(".rar")){
		            			documentos.put(iPrueba.toString(), docClientes[i]);
		            			iPrueba++;
		            		}
		            	  }
		            	  f = new File (rutaGlobal+"/Mercado de capitales");
		            	  docClientesB = f.list();
		            	  for (int i= 0;i<docClientesB.length;i++) { 
		            		if (!docClientesB[i].contains("Thumbs")){
		            			documentos.put(iPrueba.toString(), docClientesB[i]);
		            			iPrueba++;
		            		}	            		
		            	  }
		            	  bandera = true;
		            	  break;
		              }
		            }  
		            if (!bandera){
		            	addActionError("No se ha encuentrado el archivo "+rutaGlobal);
		            }
		    	} else { 
		    		//Directorio no existe 
		    		addActionError("No se ha encuentrado el directorio "+fichero);
		    	} 
		     }catch (FileNotFoundException e) {
		    	 addActionError("Archivo no encontrado en "+rutaGlobal);
			} catch (IOException e) {
				addActionError("Error en la escritura del archivo ubicado en "+rutaGlobal);
			} catch (Exception e) {
				addActionError("Error: Por favor comunicarse con el area de sistemas. "+e.getMessage());
			}    	 
		     return documentos;
		}


		public void changeEstado(){
	    	String doc = getRequest().getParameter("documento");
	    	String cuit = getRequest().getParameter("cuit");
	    	cuit = cuit.replace("-", "");
	    	String ruta = "";
	    	File folder1 = new File("N:\\DOC. CLIENTES\\"+cuit);
	    	File folder2 = new File("N:\\DOC. CLIENTES\\"+cuit+"\\Mercado de capitales");
	    	String[] documentos1 = folder1.list();
	    	String[] documentos2 = folder2.list();
	    	int cont = 0;
	    	for (int i = 0; i < documentos1.length; i++) {
	    		if (documentos1[i].contains(".zip") || documentos1[i].contains(".rar")){
	    			documentos.put(Integer.valueOf(cont).toString(), documentos1[i]);
	    			cont++;
	    		}
			}
	    	for (int i= 0;i<documentos2.length;i++) {            		
	    		documentos.put(Integer.valueOf(cont).toString(), documentos2[i]);	
	    		cont++;
	    	 } 	
	    	//abrirarchivo();    	
			doc = documentos.get(doc).toString();		
	    	try {    		
	    		if(doc.contains(".rar") || doc.contains(".zip")){
	    			ruta = folder1.getPath()+"/"+doc;	
	    			//ruta = rutaGlobal+"/"+doc;
	    			FileUtils.downloadZIP(ruta, ServletActionContext.getResponse());
	    		}else{
	    			//ruta = rutaGlobal+"/Mercado de capitales"+"\\"+doc;	
	    			ruta =folder2.getPath()+"/"+doc;	
	    			FileUtils.downloadFile( ruta, ServletActionContext.getResponse(), "application/pdf");
	    		}		
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }

	    Compresion____________________________________________________________________________________________
	    //Metodo que comprime un Directorio a .zip
		public static void addDirAZip(ZipOutputStream zos, File archivoZip, String directorioPadre) throws Exception {
		    if (archivoZip == null || !archivoZip.exists()) {
		        return;
		    }
		    String zipEntryName = archivoZip.getName();
		    if (directorioPadre!=null && !directorioPadre.isEmpty()) {
		        zipEntryName = directorioPadre + "/" + archivoZip.getName();
		    }
		    if (archivoZip.isDirectory()) {
		        for (File file : archivoZip.listFiles()) {
		        	if (!file.getName().contains("Thumbs"))
		        		addDirAZip(zos, file, zipEntryName);
		        }
		    } else {
		        byte[] buffer = new byte[1024];
		        FileInputStream fis = new FileInputStream(archivoZip);
		        zos.putNextEntry(new ZipEntry(zipEntryName));
		        int length;
		        while ((length = fis.read(buffer)) > 0) {
		            zos.write(buffer, 0, length);
		        }
		        zos.closeEntry();
		        fis.close();
		    }
		}
***********************************************************************************************************************Implementado




22.*********************************************************************************************************************
************************************************************************************************************************
*****************************************************************************Ticket#1012633 — TASAS INMEDIATO EN DOLARES
	Usuario 	--> BONATO MATIAS		
	Ext			--> 5150	
	Area		--> Mercaderías	Casa Central
	Descripcion:
 	Necesitamos que cuando se liquide tasa para  Inmediato en Dolares la tasa sea Cero. 
	Ahora está tomando la tasa del Futuro en Dolares “ normal”, pero no podemos separarlo.

	Documentacion:
		<Item name="MATProductoMenu" title="mATProductoList.title" page="/mATProductoes.html" width="210"/>

		<action name="mATProductoes" class="com.zeni.app.webapp.action.MATProductoAction"
			method="list">
			<result name="success">/WEB-INF/pages/mATProductoList.jsp</result>
		</action>
************************************************************************************************************************Susp. Req Proveedor





23.*********************************************************************************************************************
************************************************************************************************************************
***********************************************************************Ticket#1013616 — Actualización archivos .txt CABA
	Usuario 	--> Debora Iezzi - ZENI	
	Ext			--> 
	Area		--> 
	Descripcion:
	AGIP realizó modificaciones en el diseño de registro de los archivos .txt para las presentaciones de agentes de 
	recaudación.
	Necesitamos la modificación en el sistema comercial para percepciones, y en SAP para retenciones; aplica para la 
	presentación de 05/2019 que vence el día 07/06, por lo tanto lo precisamos con bastante urgencia.
	Adjunto la notificación y el nuevo registro de importación que detalla los datos que se modifican y los que se 
	deberían agregar. 

	De este modo ponemos en vuestro conocimiento que a partir del Período 2019 - 05, cuyo vencimiento opera el 07/06/2019, se observaran las
	siguientes adecuaciones en el Aplicativo e-Arciba:
	* En Tipo de comprobante de orígen de la Retención/Percepción, se incorporaron los comprobantes del Régimen de "Factura de Crédito Electrónica MiPyMEs".
	
	* En Letra de Comprobantes para Factura de Crédito Electrónica MiPyMEs, permitirá la carga de las letras A, B y C.
	
	*En los casos de "Retenciones" del Régimen de "Factura de Crédito Electrónica MiPyMEs", se incorporaron dos campos uno con tipo de
	Aceptación Expresa o Tácita, y otro para informar la fecha de Aceptación Expresa.
	
	*En la carga por Importación, en el caso de seleccionar "Situación IB del Retenido/Percibido", si selecciona la opción 2: Convenio
	Multilateral, el número a cargar es la CUIT.

	IMPORTANTE: Para los Agentes de Recaudación ISIB, que opten por realizar la carga de retenciones/percepciones por 
	"importación de datos",	deberán generar los archivos teniendo en cuenta el Nuevo 
	"Diseño de Registro de Importación de Retenciones/Percepciones" y "Diseño de Registro de Importación de Notas de Crédito".

	Para mayor información podrá consultar el "Manual de Ayuda", y los nuevos "Diseño de Registro de Importación de Retenciones/Percepciones" y
	"Diseño de Registro de Importación de Notas de Crédito" en nuestra página Web www.agip.gob.ar - Solapa: Agentes de Recaudación - Ingresos
	Brutos-Definiciones Generales-Aplicativo e-ARCIBA-Aplicativo.

	Documentacion__________________________________________________________________________________________________
		<Menu name="ImpuestosMenu" title="menu.impuestos" description="Impuestos" roles="ROLE_IMPUESTOS,ROLE_USER" page="" width="80">
			<Item name="LiquidacionIIBBMenu" title="liquidacionIIBB.title" page="" width="200" roles="ROLE_IMPUESTOS,ROLE_USER">
				<Item name="ArchivosIIBBMenu" title="Archivos" page="/archivosIIBBList.html" width="140"/>

		<action name="archivosIIBBList" class="com.zeni.app.webapp.action.ArchivosIIBBAction"
			method="listArchivos">
			<result name="success">/WEB-INF/pages/archivosIIBBList.jsp</result>
		</action>

		***Action --> ArchivosIIBBAction.java

		***Vistas --> archivosIIBBList.jsp
			<a href="downloadArchivoIIBBAFIP.html?txt=${item}" title="Descargar">Descargar</a>
			<action name="downloadArchivoIIBBAFIP" class="com.zeni.app.webapp.action.LiquidacionIIBBAction" method="downloadAFIP">
				<result name="success" type="noOp" />
				<result name="error">/WEB-INF/pages/liquidacionIIBBList.jsp</result>
			</action>

		Modificaciones_____________________________________________________________________________________________
			Modificaciones en la clase GananciaManagerImpl.java  --> generarArchivosPercepcionesIIBB_CABA()
						String renglon = String.format(Locale.GERMANY, "2029%s%s%s%08d%08d%s%016.2f                3%s%s%s%s%s%016.2f%016.2f%016.2f%05.2f%016.2f%016.2f           \r\n", 
												fecha, 
												dp.tipoComprobante=='F'?"01":(dp.tipoComprobante=='D'?"02":"09"),
												dp.letraComprobante,
												dp.pvta,
												dp.nroComprobante, 
												fecha,
												dp.importe, 
												dp.cuitSinFormato,
												dp.tipoInscripcionIIBB=='D'?"1":(dp.tipoInscripcionIIBB=='C'?"2":(dp.tipoInscripcionIIBB=='S'?"5":"4")),
												dp.tipoInscripcionIIBB=='N'?"00000000000":dp.cuitSinFormato,
												dp.situacionIVA=='R'?"1":(dp.situacionIVA=='E'?"3":(dp.situacionIVA=='M'?"4":" ")),
												dp.razonSocial,
												dp.totalTodasPercepciones,
												dp.importeIva,
												dp.baseImponible, 
												dp.alicuota(), 
												dp.importePercepcion, 
												dp.importePercepcion);
			if( renglon.length() != 228 ){


		Metodo Original_______________________________________________________________________________________________
		private String generarArchivosPercepcionesIIBB_CABA(Integer mes, Integer anio, Provincia provincia, List<AsientoContable> percepciones){
			String fileName = RetencionIIBB.getArchivoPAFIP(mes, anio, provincia.getNombre(), provincia.getCodigoInterno());
			try{
				if(!mkDirs(fileName))// Se asegura de que el path este creado en el servidor
					return "ERROR generarArchivoPercepcionesIIBB("+mes+", "+anio+", "+provincia.getNombre()+"): NO PUDO CREAR LAS CARPETAS PARA - " + fileName;
				PrintWriter outputStream = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), Charset.forName("ISO-8859-1")));
				Calendar calendar =Calendar.getInstance();// Crea una instancia de calendario para simplificar el formateo de la fecha
				Boolean exportarErrores = Boolean.TRUE;
				for(AsientoContable ac : percepciones){
					try{
						DatosPercepcion dp = null;
						if( FacturaProducto.class.getSimpleName().equals(ac.getRef_entity()) )
							dp = getDatosPercepcionFactura(ac, 'C');
						calendar.setTime(dp.fecha);
						String fecha = String.format("%02d/%02d/%4d", calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR));
						//Se utiliza locale Germany para que utilize como sepador decimal la coma
						String renglon = String.format(Locale.GERMANY, "2029%s%s%s%08d%08d%s%016.2f                3%s%s%s%s%s%016.2f%016.2f%016.2f%05.2f%016.2f%016.2f\r\n", 
															fecha, 
															dp.tipoComprobante=='F'?"01":(dp.tipoComprobante=='D'?"02":"09"),
															dp.letraComprobante,
															dp.pvta,
															dp.nroComprobante, 
															fecha,
															dp.importe, 
															dp.cuitSinFormato,
															dp.tipoInscripcionIIBB=='D'?"1":(dp.tipoInscripcionIIBB=='C'?"2":(dp.tipoInscripcionIIBB=='S'?"5":"4")),
															dp.tipoInscripcionIIBB=='N'?"00000000000":StringUtils.leftPad(dp.nroIIBBSinFormato, 11, '0'),
															dp.situacionIVA=='R'?"1":(dp.situacionIVA=='E'?"3":(dp.situacionIVA=='M'?"4":" ")),
															dp.razonSocial,
															dp.totalTodasPercepciones,
															dp.importeIva,
															dp.baseImponible, 
															dp.alicuota(), 
															dp.importePercepcion, 
															dp.importePercepcion);
						if( renglon.length() != 217 ){
							log.error(String.format("Error en asiento.id %s: %s", ac.getId(),renglon));
							if(exportarErrores)
								outputStream.print(renglon);
						}else
							outputStream.print(renglon);
					}catch (Exception e) {
						String mensaje = e.getMessage()!=null?e.getMessage():e.getClass().getSimpleName();
						log.error(String.format("Error en asiento.id %s: %s\n",ac.getId(),mensaje));
						if(exportarErrores)
							outputStream.printf("Error en asiento.id %s: %s\r\n", ac.getId(), mensaje);
					}
				}
				outputStream.flush();
				outputStream.close();
				return "OK";
			} catch (Exception e) {
				return "ERROR generarArchivoIIBB_AFIP("+mes+", "+anio+", "+provincia.getNombre()+"): " +
									e.getClass().getSimpleName()+": "+e.getMessage() + " - " + fileName;
			}
		}
************************************************************************************************************************Implementado




	

24.*********************************************************************************************************************
************************************************************************************************************************
*************************************************************************Ticket#1011815 — OLEAGINOSA MORENO HNOS A FIJAR
	Usuario 	--> FUX JAVIER
	Ext			--> 5012
	Area		--> Mercaderías	Casa Central
	Descripcion: 
	Solo para la bolsa de Rosario, boletos confirma, negocios a fijar, debe exporta en la: Fecha y condición de pago: 
	Contra Entrega.
 
	ver ejemplo cto. 18/15975/0

	Documentacion___________________________________________________________________________________________________
		<Menu name="BoletosMenu" title="menu.boletos" description="Boletos" roles="ROLE_USER,ROLE_BOLETOS" page="" width="40">
			<Item name="GeneracionDocumentosMenu" title="generacionDocumentosList.title" page="/generacionDocumentos.html" width="250" roles="ROLE_USER,ROLE_BOLETOS,ROLE_FACTURACION"/>
		
		<action name="generacionDocumentos" class="com.zeni.app.webapp.action.GeneracionDocumentosListAction" method="list">
            <result>/WEB-INF/pages/generacionDocumentosList.jsp</result>
        </action>	    

        *****Vistas:
        	generacionDocumentosList.jsp
        	<action name="imprimirDocumento" class="com.zeni.app.webapp.action.DocumentoDeNegocioReportAction" method="printDocumento">
	            <result name="success" type="noOp"/>
	            <result name="successMail">/WEB-INF/pages/clausulasPreview.jsp</result>
	            <result name="error" type="redirectAction">generacionDocumentos</result>
	        </action>
        	generacionDocumentosFiltro.jsp

     Modificaciones_________________________________________________________________________________________________
     	DocumentoDeNegocioReportAction.java --> metodo makePagos(TransformerHandler hd, AttributesImpl atts)
			if (ddn.getContrato().getAFijar() && "BoletoConfirma".equals(ddn.getTipoDocumentoNegocio().getCodigo()) && 
					"RosBolCom".equals(ddn.getContrato().getBolsa().getCodigo())){
				openCloseElement(hd, atts, "FechaCondicionPago", null, null, "Contra Entrega");
			}else{
				openCloseElement(hd, atts, "FechaCondicionPago", null, null, getFechaCondicionPago());
			}
************************************************************************************************************************Implementado





25.*********************************************************************************************************************
************************************************************************************************************************
**************************************Ticket#1013080 — Oleaginosa Moreno = pago contra carta de garantia y certific[...]
	Usuario 	--> FUX JAVIER
	Ext			--> 5012
	Area		--> Mercaderías	Casa Central
	Descripcion: 
	Para los boletos confirma y papel, cuyo pago sea contra cd y cg, deberá salir en la condición de pago:
 
		Contra Carta de Garantía y Certificado de Deposito
 
		No debe salir mas: contra documentación a satisfacción del comprador

		Nota: Para cualquier boleto fisico o confirma donde de Oleaginosa Moreno 


	Documentacion___________________________________________________________________________________________________

	Modificaciones__________________________________________________________________________________________________
		DocumentoDeNegocioReportAction.java --> metodo makePagos(TransformerHandler hd, AttributesImpl atts)

		
		if (ddn.getContrato().getAFijar() && "BoletoConfirma".equals(ddn.getTipoDocumentoNegocio().getCodigo()) && 
				"RosBolCom".equals(ddn.getContrato().getBolsa().getCodigo()) && ddn.getContrato().getComprador().getNroCuenta() == 2024){
			openCloseElement(hd, atts, "FechaCondicionPago", null, null, "Contra Entrega");
		}else{
			if(("BoletoConfirma".equals(ddn.getTipoDocumentoNegocio().getCodigo()) || "BoletoFisico".equals(ddn.getTipoDocumentoNegocio().getCodigo())) 
					&& (!ddn.getContrato().getCertificadosDeDeposito().isEmpty() ) && (!ddn.getContrato().getCartasGarantia().isEmpty()) && (ddn.getContrato().getComprador().getNroCuenta() == 2024)) {
				openCloseElement(hd, atts, "FechaCondicionPago", null, null, "Contra Carta de Garantía y Certificado de Deposito");
			}else{
				openCloseElement(hd, atts, "FechaCondicionPago", null, null, getFechaCondicionPago());
			}				
		}
************************************************************************************************************************Implementado





26.*********************************************************************************************************************
************************************************************************************************************************
***************************************************************************Ticket#1013162 — NEGOCIOS CON SURSEM VENDEDOR
	Usuario 	--> FUX JAVIER
	Ext			--> 5012
	Area		--> Mercaderías	Casa Central
	Descripcion: 
	Por favor les solicitamos que  para todos los negocios donde venda la firma Sursem, salga cargado en forma 
	automática, el negocio con pago directo al vendedor, al igual que en el boleto.

	Este ticket se debe cerrar, sin embargo se solicitaron dos modificaciones:

	Para contratos donde el vendedor es :
		Ruralco Soluciones S.A

		Nidera Seeds Argentina S.A.U

	Para todos los boletos confirma y físicos, la impresion del xml debe tener en "A la orden: " vendedor, actualmente 
	indica comprdor.


	Modificacion___________________________________________________________________________________________________
	DocumentoDeNegocioReportAction.java --> metodo makePagos()
		if(ddn.getContrato().getVendedorEn("DOW") || isLaboratorio || (ddn.getContrato().getVendedor().getNroCuenta() == 14579 || ddn.getContrato().getVendedor().getNroCuenta() == 15386)){
				openCloseElement(hd, atts, "PagoAOrdenDe", "CodLista", "1", null);
			}else{
				openCloseElement(hd, atts, "PagoAOrdenDe", "CodLista", getCodLista("PagoAOrdenDe", null), null);				
			}
************************************************************************************************************************Implementado





27.*********************************************************************************************************************
************************************************************************************************************************
************************************************************************************************************Debora Iezzi 
  	Buenos días, el viernes 16/08 y lunes 19/08 no se pudieron procesar los padrones de SISA por un problema de AFIP.

	Necesitamos que en el sistema se copien los datos del 20/08 para cada cliente ya que al momento de liquidar las retenciones 
	de ganancias nos está tomando a todos los clientes como “no inscriptos” (para los movimientos entre las fechas anteriores 
	mencionadas).

	Documentacion___________________________________________________________________________________________________
		<action name="getClientePadronRG4310" class="com.zeni.app.webapp.action.ClienteSituacionGananciasAction" method="getClientePadronRG4310">
            <result>/WEB-INF/pages/clientePadronRG4310Form.jsp</result>
            <result name="error">/WEB-INF/pages/clienteImpuestosForm.jsp</result>
        </action>


        <Menu name="ImpuestosMenu" title="menu.impuestos" description="Impuestos" roles="ROLE_IMPUESTOS,ROLE_USER" page="" width="80">
        	<Item name="CAIMenu" title="cAIList.heading" page="/cAIs.html" width="200"/>
    	    <Item name="TipoImpuestoMenu" title="tipoImpuestoList.menu" page="/tipoImpuestoes.html" width="200"/>
        	<Item name="TipoDocumentacionMenu" title="tipoDocumentacionList.title" page="/tipoDocumentaciones.html" width="200"/>
        	<Item name="LiquidacionGananciasMenu" title="liquidacionGanancias.title" page="" width="200" roles="ROLE_IMPUESTOS,ROLE_USER">
        		<Item name="ImportarExclusionesMenu" title="importarExclusiones.title" page="/rg830_2681.html" width="150"/>
        		<Item name="LiquidarGananciasMenu" title="liquidarGanancias.title" page="/liquidarGanancias.html" width="150"/>

        <action name="liquidarGanancias" class="com.zeni.app.webapp.action.LiquidacionGananciasAction" method="edit">
			<result name="success">/WEB-INF/pages/liquidacionGanancias.jsp</result>
		</action>

	Modificacion___________________________________________________________________________________________________

		CREATE or replace PROCEDURE PADRONES (fechaAltaPadron IN padron_rg2300.FECHAALTA%type, fechaCopy IN padron_rg2300.FECHAALTA%type) is  
			  cuenta PADRON_RG2300.ID%type;
				fechaAlta padron_rg2300.FECHAALTA%type;
				userAlta padron_rg2300.USERALTA%type;
				fechaModif padron_rg2300.FECHAMODIF%type;
				usuarioModif padron_rg2300.USERMODIF%type;
				fechaBaja padron_rg2300.FECHABAJA%type;
				userBaja padron_rg2300.USERBAJA%type;
				fechaGeneracion padron_rg2300.FECHAGENERACION%type;
				observacion padron_rg2300.OBSERVACIONES%type;
				situacion padron_rg2300.SITUACION%type;
				cuit padron_rg2300.CUIT%type;
				cbu padron_rg2300.CBU%type;
				categoria padron_rg2300.CATEGORIA%type;
				fechaActCBU padron_rg2300.FECHAACTUALIZACIONCBU%type;
				fechaPublicacion padron_rg2300.FECHAPUBINCLUSION%type;
				fechaPubSusp padron_rg2300.FECHAPUBSUSPENSION%type;
				fechaLevSusp padron_rg2300.FECHALEVANTASUSPENSION%type;
				fechaNotExc padron_rg2300.FECHANOTIFEXCLUSION%type;
				fechaActRegis padron_rg2300.FECHAACTUALIZACIONREGISTRO%type;
				estado padron_rg2300.STATE_ID%type;
				estadoCuit padron_rg2300.ESTADOCUIT%type;
				fechaVigEst padron_rg2300.FECHAVIGENCIAESTADO%type;
				fechaNotEst padron_rg2300.FECHANOTIFICACIONDFEESTADO%type;
				fechaNotCat padron_rg2300.FECHANOTIFICACIONDFECATEGORIA%type;
				codCategoria padron_rg2300.CODIGOCATEGORIA%type;
				CURSOR cursor1 is
			  select p.FECHAALTA
							,p.USERALTA
							,p.FECHAMODIF
							,p.USERMODIF
							,p.FECHABAJA
							,p.USERBAJA
							,p.FECHAGENERACION
							,p.OBSERVACIONES
							,p.SITUACION
							,p.CUIT
							,p.CBU
							,p.CATEGORIA
							,p.FECHAACTUALIZACIONCBU
							,p.FECHAPUBINCLUSION
							,p.FECHAPUBSUSPENSION
							,p.FECHALEVANTASUSPENSION
							,p.FECHANOTIFEXCLUSION
							,p.FECHAACTUALIZACIONREGISTRO
							,p.STATE_ID
							,p.ESTADOCUIT
							,p.FECHAVIGENCIAESTADO
							,p.FECHANOTIFICACIONDFEESTADO
							,p.FECHANOTIFICACIONDFECATEGORIA
							,p.CODIGOCATEGORIA
					from cliente c
					inner join workflow_state ws
					on c.state_id = ws.id
					inner join workflow_definition wd
					on ws.workflowdefinition_id = wd.id
					inner join padron_rg2300 p
					on c.cuit = p.cuit
					where wd.estadoactual = 'H'
					and trunc(p.fechaAlta) = trunc(fechaCopy); 
			BEGIN 
			  OPEN cursor1; 
			  SELECT MAX("A1"."ID") "MAX(ID)" into cuenta FROM "APPTEST"."PADRON_RG2300" "A1";
				LOOP
			    cuenta := cuenta + 1;
					FETCH cursor1 into  fechaAlta , userAlta , fechaModif , 
										usuarioModif , fechaBaja, userBaja , 
										fechaGeneracion , observacion , situacion, 
										cuit , cbu , categoria , 
										fechaActCBU, fechaPublicacion, fechaPubSusp, 
										fechaLevSusp, fechaNotExc, fechaActRegis, 
										estado, estadoCuit, fechaVigEst, 
										fechaNotEst, fechaNotCat, codCategoria;
					EXIT WHEN cursor1%notfound;
					insert into padron_rg2300 values ( cuenta, fechaAltaPadron , userAlta , fechaModif , 
										usuarioModif , fechaBaja, userBaja , 
										fechaGeneracion , observacion , situacion, 
										cuit , cbu , categoria , 
										fechaActCBU, fechaPublicacion, fechaPubSusp, 
										fechaLevSusp, fechaNotExc, fechaActRegis, 
										estado, estadoCuit, fechaVigEst, 
										fechaNotEst, fechaNotCat, codCategoria);  
				END LOOP;
				CLOSE cursor1; 	        
			END; 


			EXECUTE PADRONES('17/08/2019 01:00:00,000000000 AM', '15/08/2019 01:00:00,000000000 AM');

			select count(*) from padron_rg2300 where TRUNC(fechaalta) = '17/08/2019';

			delete from padron_rg2300 where TRUNC(fechaalta) = '17/08/2019';

			select * from padron_rg2300 where TRUNC(fechaalta) = '17/08/2019';

			update padron_rg2300_1 set fechaalta = '16/08/2019 00:00:00,000000000 AM'

			create table padron_rg2300_1 as 
			  select * from padron_rg2300 where rownum < 10



			 Produccion: 

			 select  max(id) from padron_rg2300 
			  -->205451748

			  select count(*)
			from cliente c
			inner join workflow_state ws
			on c.state_id = ws.id
			inner join workflow_definition wd
			on ws.workflowdefinition_id = wd.id
			inner join padron_rg2300 p
			on c.cuit = p.cuit
			where wd.estadoactual = 'H'
			and trunc(p.fechaAlta) = '15/08/2019' 
			-->2619

			EXECUTE PADRONES('16/08/2019 01:00:00,000000000 AM', '15/08/2019 01:00:00,000000000 AM');

			select  max(id) from padron_rg2300 
			205454367

			EXECUTE PADRONES('19/08/2019 01:00:00,000000000 AM', '15/08/2019 01:00:00,000000000 AM');

			select  max(id) from padron_rg2300 
			205456984
************************************************************************************************************************Solucionado





28.*********************************************************************************************************************
************************************************************************************************************************
***********************************************************************Ticket#1013025 — Modificar boleto en consignacion
	Usuario 	--> JUAN EMILIANO
	Ext			--> 6235
	Area		--> Administración - Rosario
	Descripcion: 
	Cambiar encabezad a “BOELTO EN CONSIGNACION”	 
	El la cl 2, agregar: ''Las disminuciones o incrementos del precio efectivo de venta con relación al precio ordenado 
	estarán a cargo del consignatario, el que en ningún caso podrá liquidar un precio menor al estipulado''.
	 
	Agregar la cl 7: ''Si por cualquier causa la operación de venta no se realizare, el consignatario solo está obligado 
	a devolver, a su elección, mercadería de la misma especie y calidad, o su equivalente en dinero a precios de mercado''.
	 
	La cl 6 dice: del presente contrato compra-venta, debería decir ''del presente contrato en consignación''.
	 
	Por ultimo, en la cl 1, no deberían aparecer las palabras ''compran'' y ''venden'' ya que del sector de sellado de 
	la bolsa dicen que se presta la confusión con un modelo de compraventa. Quedaría así: ENRIQUE ZENI... por cuenta y 
	orden de XXXXX.... con domicilio en ....., entrega en consignación a DIAZ & FORTI S.A. con domicilio en AV. MADRES 
	DE PLAZA 25 DE 3020 13 4 ….

	***Modificaciones a las Clausulas
	Clausula 301:
	"El/Los Señor/es ENRIQUE R. ZENI &amp; CIA. S.A.C.I.A.F.E I. por cuenta y orden de <b>@{$Contrato.vendedor.cliente.razonSocial.toUpperCase()} - @{$Contrato.actividadVendedor.nombre.toUpperCase()} </b>con domicilio en&nbsp;<b>@{$Contrato.vendedor.domicilioNegocio.domicilioCompleto.toUpperCase()} - </b><b>@{$Contrato.vendedor.domicilioNegocio.ciudad.codigoPostal.toUpperCase()} - @{$Contrato.vendedor.domicilioNegocio.ciudad.nombreCompleto.toUpperCase()}&nbsp;</b>y el/los Señor/es&nbsp; <b>@{$Contrato.comprador.cliente.razonSocial.toUpperCase()}&nbsp; </b>con domicilio en <b>@{$Contrato.comprador.domicilioNegocio.domicilioCompleto.toUpperCase()} - </b><b>@{$Contrato.comprador.domicilioNegocio.ciudad.codigoPostal.toUpperCase()} - @{$Contrato.comprador.domicilioNegocio.ciudad.nombreCompleto.toUpperCase()}&nbsp;</b>(compra/n la cantidad de <b>@{$Contrato.cantidadPactada} @{$Contrato.unidadDeMedida.abreviatura} (@{$Contrato.cantidadPactadaEnLetras.toUpperCase()})</b> de <b>@{$Contrato.producto.nombre.toUpperCase()}</b><b>&nbsp;@{$Contrato.cantidadTransporteDesc.toUpperCase()}</b>&nbsp;<b>- CALIDAD&nbsp;</b><b>@{$Contrato.calidad.nombre.toUpperCase()}</b><b>&nbsp;</b><b>DE LA COSECHA </b><b>@{$Contrato.anioCosecha.toString()}</b><b>- </b>al precio de <b>@{$Contrato.monedaFacturacionMercaderia} @{$Contrato.precioMercaderia}</b>&nbsp;<b>(@{$Contrato.monedaFacturacionMercaderia.nombre} @{$Contrato.PrecioMercaderiaEnLetras.toUpperCase()})&nbsp;</b>MAS IVA el/la <b>TN</b> - PESO NETO A GRANEL PUESTO SOBRE<b> CAMIÓN</b> PROCEDENTES DE @{$Contrato.procedencia.NombreCompleto.toUpperCase()}<b>.&nbsp;</b>@{if($Contrato.getCompradorEn("Bunge Ar") and $Contrato.cantidadPactada le 99.999) "<b>LAS PARTES ACUERDAN UNA TOLERANCIA DEL 5% DE LOS KILOS NEGOCIADOS DENTRO DE LOS CUALES EL FALTANTE O SOBRANTE DE MERCADERIA SE TOMARA SIN DIFERENCIA DE PRECIO.</b>" else if($Contrato.getCompradorEn("Bunge Ar")) "<b>LAS PARTES ACUERDAN UNA TOLERANCIA DE MAS 2.000 KILOS / MENOS 3.000 KILOS SOBRE LA MERCADERIA NEGOCIADA DENTRO DE LOS CUALES EL FALTANTE O SOBRANTE DE KILOS SE TOMARA SIN DIFERENCIA DE PRECIO.</b>" else if($Contrato.getCompradorEn("CARGILLE") and $Contrato.cantidadPactada le 99.999 and ($Contrato.cantidadTransporte==null || $Contrato.cantidadTransporte==0)) "<b>LAS PARTES ACUERDAN UNA TOLERANCIA DEL 5% DE LOS KILOS NEGOCIADOS DENTRO DE LOS CUALES EL FALTANTE O SOBRANTE DE MERCADERIA SE TOMARA SIN DIFERENCIA DE PRECIO.</b>" &nbsp;else if($Contrato.getCompradorEn("CARGILLE") and ($Contrato.cantidadTransporte==null || $Contrato.cantidadTransporte==0))"<b>LAS PARTES ACUERDAN UNA TOLERANCIA DE MAS 2.000 KILOS / MENOS 3.000 KILOS SOBRE LA MERCADERIA NEGOCIADA DENTRO DE LOS CUALES EL FALTANTE O SOBRANTE DE KILOS SE TOMARA SIN DIFERENCIA DE PRECIO.</b>"}<br />No rige la cláusula -habiendo vagones- ni los artículos 105 y 112 del Reglamento de la Cámara Arbitral de Cereales de la Bolsa de Comercio de Rosario.-"

	Clausula 302
	Las entregas y recibos se efectuarán del <span style="font-weight: bold;">@{$Contrato.fechaEntregaDesde} </span>AL <span style="font-weight: bold;"> @{$Contrato.fechaEntregaHasta} </span>EN DESTINO <b>@{$Contrato.destino.nombreCompleto.toUpperCase()}</b> La mercadería entregada se liquidará según análisis. Las disminuciones o incrementos del precio efectivo de venta con relación al precio ordenado estarán a cargo del consignatario, el que en ningún caso podrá liquidar un precio menor al estipulado.

	Clausula 305
	@{if($Contrato.comprador.cliente.laboratorio==false) "A los efectos impositivos, los vendedores declaran que la mercadería del presente contrato en consignacion es de su propia producción. "} El vendedor autoriza al corredor a firmar en su nombre toda la documentación necesaria para el fiel y definitivo cumplimiento del presente y liquidación electrónica primaria de venta. Quedando establecido que toda tasa, contribución o impuesto municipal o provincial que grave la presente operación estará a cargo del vendedor, si es que se impone por el origen de la mercadería vendida o por el lugar de descarga. 

	Clausula 306
	Todos los firmantes acuerdan que todas las divergencias, cuestiones o reclamaciones que surjan de o que se relacionen con cualquiera de las relaciones jurídicas que se deriven de este contrato y entre cualesquiera de ellos, serán resueltas en forma definitiva por la Cámara Arbitral de Cereales de la Bolsa de Comercio de Rosario. El tribunal actuará como amigable componedor, con aplicación de las Reglas y Usos del Comercio de Granos y del Reglamento de Procedimientos aprobado por Decreto 931/98 y/o sus futuras modificaciones, ampliaciones o normas complementarias. Si por cualquier causa la operación de venta no se realizare, el consignatario solo está obligado a devolver, a su elección, mercadería de la misma especie y calidad, o su equivalente en dinero a precios de mercado.



	Documentacion___________________________________________________________________________________________________
		<Menu name="ComercialMenu" title="menu.comercial" description="Comercial" roles="ROLE_USER,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION" page=""	width="50">
        	<Item name="ContratoMenu"      title="contratoList.heading.menu" page="/contratoes.html" roles="ROLE_USER,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION,ROLE_BOLETOS,ROLE_SUBPRODUCTOS,ROLE_IMPUESTOS" width="250"/>
    	
    	<action name="contratoes" class="com.zeni.app.webapp.action.ContratoListAction"
			method="list">
			<result>/WEB-INF/pages/contratoList.jsp</result>
		</action> 

	Modificaciones_________________________________________________________________________________________________

		***Se modifica Contrato.java --> Metodo getImprimirDdn(DocumentoDeNegocio ddn)
			return String
			.format(
					"<td class='docPrint'><a href=\"javascript:popupWin.openUrl('popupClausulasPreview', 'clausulasPreview.html?decorator=popup&id=%s&familia=%s')\">Imprimir</a></td>" +
					"<td class='docPrint'><a href=\"javascript:popupWin.openUrl('popupClausulasPreview', 'clausulasPreview.html?decorator=popup&id=%s&familia=%s&Consignacion=%s')\">Consignacion</a></td>",
					ddn.getId().toString(), ddn.getTipoDocumentoNegocio().getFamilia(), ddn.getId().toString(), ddn.getTipoDocumentoNegocio().getFamilia(), "true");

		***Se crea reporte Boleto-Consignacion.jrxml

		***Se modifica DocumentoDeNegocioReportAction.java
			*Se crea atributo de clase con su get y set
				private String consignacion; 
			*Se modifica el metodo prepare(), para obtener el parametro consignacion del request y setearlo al atributo de clase consignacion
				this.setConsignacion(getRequest().getParameter("consignacion") != null ? getRequest().getParameter("consignacion") : "");
			*Se modifica el metodo previewClausulas(), para cambiarlas clausulas del contrato.
				if (consignacion.equalsIgnoreCase("true")) ddn.getContrato().setClausulasDocumentoNegocio(ddnConsignacion(ddn.getContrato().getClausulasDocumentoNegocio())); 
		
			*Se crea metodo ddnConsignacion(List<ClausulaDocumentoNegocio> lista)
				public List<ClausulaDocumentoNegocio> ddnConsignacion(List<ClausulaDocumentoNegocio> lista){		
					getRequest().getSession().setAttribute("consignacion", this.consignacion);
					int cont = 0;
					for (ClausulaDocumentoNegocio clausula : lista) {
						 switch(cont) {
						 case 0: 
							 lista.get(0).setTexto(lista.get(0).getTexto().replace("vende/n", ""));
							 lista.get(0).setTexto(lista.get(0).getTexto().replace("y el/los ", "entrega en consignación a el/los "));
							 lista.get(0).setTexto(lista.get(0).getTexto().replace("compra/n", ""));
							 break;
						 case 1:
							 lista.get(1).setTexto(lista.get(1).getTexto()+" Las disminuciones o incrementos del precio efectivo " +
							 		"de venta con relación al precio ordenado estarán a cargo del consignatario, el que en ningún " +
							 		"caso podrá liquidar un precio menor al estipulado.");
							 break;
						 case 2: 
							 lista.get(2).setTexto(lista.get(2).getTexto().replace("RENUNCIANDO EL VENDEDOR", "RENUNCIANDO EL COMITENTE "));
							 break;
						 case 3: 
							 lista.get(3).setTexto(lista.get(3).getTexto().replaceAll("vendedores", "comitentes" ));
							 break;
						 case 4:
							 lista.get(4).setTexto(lista.get(4).getTexto().replaceAll("vendedores", "comitentes" ));
							 lista.get(4).setTexto(lista.get(4).getTexto().replaceAll("vendedor", "comitente" ));
							 break;
						 case 5:
							 lista.get(5).setTexto(lista.get(5).getTexto().replaceAll("vendedores", "comitentes" ));
							 lista.get(5).setTexto(lista.get(5).getTexto().replaceAll("de compra-venta", "en consignación"));
							 break;
						 case 6:
							 lista.get(6).setTexto(lista.get(6).getTexto()+"Si por cualquier causa la operación de venta no se realizare, el consignatario " +
							 		"solo está obligado a devolver, a su elección, mercadería de la misma especie y calidad, o su equivalente en dinero a precios de mercado.");
							 break;
						 case 9:
							 lista.get(9).setTexto(lista.get(9).getTexto().replaceAll("VENDEDOR CUIT", "COMITENTE CUIT" ));
							 lista.get(9).setTexto(lista.get(9).getTexto().replaceAll("COMPRADOR CUIT", "CONSIGNATARIO CUIT" ));
							 break;
						 }				 
						cont++;
					}
					
					return lista;
				}
			*Se modifica el metodo printDocumento(), se agrega validacion, si es consignacion redirige al reporte creado
				String reportPath = "";
				if (consignacion.equals("true")){
					reportPath = "/reports/Boleto/Boleto-Consignacion.jasper";
				}else{
					reportPath = getReportPath(getReports(ddn), ddn.getTipoDocumentoNegocio().getFamilia());
				}	
		***Se modifica ClausulasPreview.jsp, para que contenga un hiden con el valor del atributo consignacion
			<s:hidden key="consignacion"/>
************************************************************************************************************************Implementado




29.*********************************************************************************************************************
************************************************************************************************************************
**********************************************************************************Ticket#1013439 — precios de fijaciones
	Usuario 	--> Juan Ignacio Dimitroff
	Ext			--> 5061
	Area		--> Mercaderías
	Descripcion:
	Actualmente al cargar un precio en el contrato precio hecho de canje, la contrapartida carga el precio automáticamente 
	con el dcto en el precio.
 	(maestro de clientes / editar boletos)
 	Por favor hacer que:

		- las fijaciones se generen automáticamente en los contratos de canje e incluyan las rebajas en el precio 
		detalladas gracias
************************************************************************************************************************Por Documentacion





30.*********************************************************************************************************************
************************************************************************************************************************
***************************************************************Ticket#1013084 — Modificaciones en Cartas Oferta para BCR
	Usuario 	--> JUAN EMILIANO
	Ext			--> 6235
	Area		--> Administración - Rosario
	Descripcion: 
	Reemplazar la clausula n° 9 por la que detallo a continuación:
 
		“Todas las cuestiones, divergencias o reclamaciones que surjan de cualquiera de las relaciones jurídicas derivadas
		 de la presente oferta y/o de su aceptación, sea ésta tácita o expresa, entre oferente y/o aceptante y/o corredor 
		 interviniente, serán resueltas en forma definitiva por la Cámara Arbitral de Cereales de la Bolsa de Comercio de 
		 Rosario. El tribunal actuará como amigable componedor, con aplicación de las Reglas y Usos del Comercio de Granos 
		 y del Reglamento de Procedimientos aprobado por Decreto 931/98 y/o sus futuras modificaciones, ampliaciones o 
		 normas complementarias.”
 
	Otras correcciones:

	- Los vocablos que deben utilizar son “OFERENTE”/ “DESTINATARIO”, y en algunos ejemplares presentados por ustedes 
	utilizan las palabras “VENDEDOR”/ “COMPRADOR”.

	- En la cabecera donde figura la leyenda “Bolsa de Comercio de Rosario: Fecha: ” vemos que es diferente a la de 
	concertación (vocablo correcto que deben usar “Fecha de Inicio de Trámite”) ya que dicha fecha la constatamos con 
	la declaración que hacen en AFIP cuando adjuntan el comprobante de registración en ese organismo. Por lo tanto, dado 
	que ese campo no responde a la fecha de concertación = Inicio de Tramite, necesitamos que en el cuerpo de la carta 
	oferta figure declarado la fecha de inicio de tramite (concertación). 
************************************************************************************************************************Implementado





31.*********************************************************************************************************************
************************************************************************************************************************
***********************************************Ticket#1013223 — Clausula Soja Molienda fábrica / comprador Vicentin SAIC
	Usuario 	--> FANTIN RODRIGO	
	Ext			--> 6227	
	Area		--> Administración	Rosario
	Descripcion:
	Favor reemplazar en todos los contratos de Soja: Clausula Soja molienda fábrica, comprador Vicentin SAIC ( todas las bolsas ).
 
	Clausula vieja, que debe der ser reemplazada:
	“TODAS LAS OPERACIONES DE COMPRAVENTA DE SOJA COSECHA 16/17 EN ADELANTE ESTARAN SUJETAS A LAS BASES DE 
	COMERCIALIZACION PARA "SOJA MOLIENDA FABRICA" DEJANDO SIN EFECTO Y VALIDEZ TODO LO QUE CONTRADIGA LA PRESENTE. 
	Condiciones Molienda Fábrica cosecha 2015/2016: Para entregas y negocios pactados a partir del 01/04/2017.
	Base de comercialización: Las entregas de soja quedan sujetas a la siguiente base de comercialización: 
	Materias Extrañas: UNO POR CIENTO (1%) incluido CERO COMA CINCO POR CIENTO (0.5%) DE TIERRA. Tolerancias de 
	recibo: Las entregas de soja quedan sujetas a las tolerancias de recibo que se establecen a continuacion: 
	Materias extrañas: CUATRO POR CIENTO (4%) incluido CERO COMA CINCO POR CIENTO (0.5%) de tierra. Granos negros: 
	UNO POR CIENTO (1%) Granos quebrados y/o partidos: CINCUENTA POR CIENTO (50%) - Granos dañados: CINCO POR CIENTO 
	(5 %) - se computarán dentro de este rubro y con una tolerancia máxima libre de descuento del UNO POR CIENTO (1%) 
	a los granos quemados por secadora o "de avería". Granos Verdes: OCHO POR CIENTO (8%) Humedad: TRECE COMA CINCO 
	POR CIENTO (13.5%) Chamico (Datura ferox): TREINTA (30) semillas por c/kilo. Insectos y/o arácnidos vivos: Libre.”


	Clausula nueva, que reemplaza la anterior:
	“Condiciones Molienda Fábrica cosecha 2016/2017: Base de comercialización: Las entregas de soja quedan sujetas 
	a la siguiente base de comercialización: Materias Extrañas: UNO POR CIENTO (1%) incluido CERO COMA CINCO POR 
	CIENTO (0.5%) DE TIERRA. Tolerancias de recibo: Las entregas de soja quedan sujetas a las tolerancias de recibo 
	que se establecen a continuación: Materias extrañas: TRES POR CIENTO (3%) incluido CERO COMA CINCO POR CIENTO (0.5%) 
	de tierra. Granos negros: UNO POR CIENTO (1%) Granos quebrados y/o partidos: CINCUENTA POR CIENTO (50%) Cuerpos 
	dañados: CINCO POR CIENTO (5%) - se computarán dentro de este rubro y con una tolerancia máxima libre de descuento 
	del UNO POR CIENTO (1%)  a los granos quemados por secadora o "de avería". Granos Verdes: CINCO  POR CIENTO (5%) 
	Humedad: TRECE COMA CINCO POR CIENTO (13.5%) Chamico (Datura ferox): CINCO (5) semillas por c/kilo. Insectos y/o 
	arácnidos vivos: Libre.”


	Documentacion___________________________________________________________________________________________________

		menu-config.xml------------------------------------------------------------------------------------------------
	 	<Item name="GeneracionDocumentosMenu" title="generacionDocumentosList.title" page="/generacionDocumentos.html"
	 	 width="250" roles="ROLE_USER,ROLE_BOLETOS,ROLE_FACTURACION"/>

	 	struts.xml----------------------------------------------------------------------------------------------------
	 	<action name="generacionDocumentos" class="com.zeni.app.webapp.action.GeneracionDocumentosListAction" method="list">
	        <result>/WEB-INF/pages/generacionDocumentosList.jsp</result>
	    </action>

	    Action 	--> GeneracionDocumentosListAction.java
	    views  	--> generacionDocumentosList.jsp
	    		--> clausulasPreview.jsp
	    Filtro  --> generacionDocumentosFiltro.jsp

	    clausulasPreview.jsp -->
	    <action name="imprimirDocumento" class="com.zeni.app.webapp.action.DocumentoDeNegocioReportAction" method="printDocumento">
		    <result name="success" type="noOp"/>
		    <result name="successMail">/WEB-INF/pages/clausulasPreview.jsp</result>
		    <result name="error" type="redirectAction">generacionDocumentos</result>
		</action>

		private String getImprimirDdn(DocumentoDeNegocio ddn) { -->Contrato.java

		Primera:
		TODAS LAS OPERACIONES DE COMPRAVENTA DE SOJA COSECHA 16/17 EN ADELANTE ESTARAN SUJETAS A LAS BASES DE
		COMERCIALIZACION PARA "SOJA MOLIENDA FABRICA" DEJANDO SIN EFECTO Y VALIDEZ TODO LO QUE CONTRADIGA LA PRESENTE.
		Condiciones Molienda Fábrica cosecha 2015/2016: Para entregas y negocios pactados a partir del 01/04/2017.

		***Modificacion:
		Condiciones Molienda Fábrica cosecha 2016/2017:

		Segunda
		Base de comercialización: Las entregas de soja quedan sujetas a la siguiente base de comercialización: Materias
		Extrañas: UNO POR CIENTO (1%) incluido CERO COMA CINCO POR CIENTO (0.5%) DE TIERRA. Tolerancias de recibo: Las
		entregas de soja quedan sujetas a las tolerancias de recibo que se establecen a continuacion: Materias extrañas:
		CUATRO POR CIENTO (4%) incluido CERO COMA CINCO POR CIENTO (0.5%) de tierra.

		***Modificacion
		Base de comercialización: Las entregas de soja quedan sujetas a la siguiente base de comercialización: Materias 
		Extrañas: UNO POR CIENTO (1%) incluido CERO COMA CINCO POR CIENTO (0.5%) DE TIERRA. Tolerancias de recibo: Las 
		entregas de soja quedan sujetas a las tolerancias de recibo que se establecen a continuación: Materias extrañas: 
		TRES POR CIENTO (3%) incluido CERO COMA CINCO POR CIENTO (0.5%) de tierra.

		Tercera
		Granos negros: UNO POR CIENTO (1%) Granos quebrados y/o partidos: CINCUENTA POR CIENTO (50%) - Granos dañados: 
		CINCO POR CIENTO (5 %) - se computarán dentro de este rubro y con una tolerancia máxima libre de descuento del 
		UNO POR CIENTO (1%) a los granos quemados por secadora o "de avería". Granos Verdes: OCHO POR CIENTO (8%) 
		Humedad: TRECE COMA CINCO POR CIENTO (13.5%) Chamico (Datura ferox): TREINTA (30) semillas por c/kilo. Insectos 
		y/o arácnidos vivos: Libre.

		***Modificacion
		Granos negros: UNO POR CIENTO (1%) Granos quebrados y/o partidos: CINCUENTA POR CIENTO (50%) Cuerpos 
		dañados: CINCO POR CIENTO (5%) - se computarán dentro de este rubro y con una tolerancia máxima libre de descuento 
		del UNO POR CIENTO (1%)  a los granos quemados por secadora o "de avería". Granos Verdes: CINCO  POR CIENTO (5%) 
		Humedad: TRECE COMA CINCO POR CIENTO (13.5%) Chamico (Datura ferox): CINCO (5) semillas por c/kilo. Insectos y/o 
		arácnidos vivos: Libre.”




	Modificaciones_________________________________________________________________________________________________
		Se modifico por la opcion del aplicativo para las editar las clausulas Boletos--> clausulas
		***Texto Original:
		Observaciones: VENDEDOR CUIT: @{$Contrato.vendedor.cliente.cuit}; CORREDOR CUIT: 30-51282309-9; COMPRADOR CUIT: @{$Contrato.comprador.cliente.cuit}.-@{if($Contrato.numeroSioGranos!=null)" Número de Operación Sio-Granos: "+$Contrato.numeroSioGranos+"."} El comprador otorgará el cupo con un código alfanumérico que obligatoriamente debe consignarse en el campo observaciones de cada carta de porte. En caso de que el vendedor remita camiones sin poseer cupo para la descarga, el comprador podrá, a su exclusiva opción, proceder a la descarga de los mismos, debiendo en tal caso el vendedor abonar al comprador U$S 10 (diez dólares) por tonelada en concepto de cargos extras por descargas no otorgadas.@{if($Contrato.monedaFacturacionMercaderia.simbolo!='$' )" El pago se hará en pesos convertidos al tipo de cambio comprador para la liquidación de divisas de exportación publicado por el "+$Contrato.banco.nombre+" correspondiente al cierre del tercer día hábil anterior al vencimiento de la factura."}
		@{if($Contrato.producto.codigo=='O10'|| $Contrato.producto.codigo=='O22')'TODAS LAS OPERACIONES DE COMPRAVENTA DE SOJA COSECHA 16/17 EN ADELANTE ESTARAN SUJETAS A LAS BASES DE COMERCIALIZACION PARA "SOJA MOLIENDA FABRICA" DEJANDO SIN EFECTO Y VALIDEZ TODO LO QUE CONTRADIGA LA PRESENTE. Condiciones Molienda Fábrica cosecha 2015/2016: Para entregas y negocios pactados a partir del 01/04/2017.'}
		@{if($Contrato.producto.codigo=='O10'|| $Contrato.producto.codigo=='O22') 'Base de comercialización: Las entregas de soja quedan sujetas a la siguiente base de comercialización: Materias Extrañas: UNO POR CIENTO (1%) incluido CERO COMA CINCO POR CIENTO (0.5%) DE TIERRA. Tolerancias de recibo: Las entregas de soja quedan sujetas a las tolerancias de recibo que se establecen a continuacion: Materias extrañas: CUATRO POR CIENTO (4%) incluido CERO COMA CINCO POR CIENTO (0.5%) de tierra.'} @{if($Contrato.producto.codigo=='O10'|| $Contrato.producto.codigo=='O22')'Granos negros: UNO POR CIENTO (1%) Granos quebrados y/o partidos: CINCUENTA POR CIENTO (50%) - Granos dañados: CINCO POR CIENTO (5 %) - se computarán dentro de este rubro y con una tolerancia máxima libre de descuento del UNO POR CIENTO (1%) a los granos quemados por secadora o "de avería". Granos Verdes: OCHO POR CIENTO (8%) Humedad: TRECE COMA CINCO POR CIENTO (13.5%) Chamico (Datura ferox): TREINTA (30) semillas por c/kilo. Insectos y/o arácnidos vivos: Libre.'}
		***Texto Modificado
		"Observaciones: <b>VENDEDOR CUIT: @{$Contrato.vendedor.cliente.cuit}; CORREDOR CUIT: 30-51282309-9; COMPRADOR CUIT: @{$Contrato.comprador.cliente.cuit}.-@{if($Contrato.numeroSioGranos!=null)" Número de Operación Sio-Granos: "+$Contrato.numeroSioGranos+"."}</b> El comprador otorgará el cupo con un código alfanumérico que obligatoriamente debe consignarse en el campo observaciones de cada carta de porte. En caso de que el vendedor remita camiones sin poseer cupo para la descarga, el comprador podrá, a su exclusiva opción, proceder a la descarga de los mismos, debiendo en tal caso el vendedor abonar al comprador U$S 10 (diez dólares) por tonelada en concepto de cargos extras por descargas no otorgadas.@{if($Contrato.monedaFacturacionMercaderia.simbolo!='$'' )" El pago se hará en pesos convertidos al tipo de cambio comprador para la liquidación de divisas de exportación publicado por el "+$Contrato.banco.nombre+" correspondiente al cierre del tercer día hábil anterior al vencimiento de la factura."}<br />
		@{if($Contrato.producto.codigo=='O10'|| $Contrato.producto.codigo=='O22' || $Contrato.comprador.cliente.cuit == '30-50095962-9')'Condiciones Molienda Fábrica cosecha 2016/2017:' else if($Contrato.producto.codigo=='O10'|| $Contrato.producto.codigo=='O22')'TODAS LAS OPERACIONES DE COMPRAVENTA DE SOJA COSECHA 16/17 EN ADELANTE ESTARAN SUJETAS A LAS BASES DE COMERCIALIZACION PARA "SOJA MOLIENDA FABRICA" DEJANDO SIN EFECTO Y VALIDEZ TODO LO QUE CONTRADIGA LA PRESENTE. Condiciones Molienda Fábrica cosecha 2015/2016: Para entregas y negocios pactados a partir del 01/04/2017.'}<br />
		@{if($Contrato.producto.codigo=='O10'|| $Contrato.producto.codigo=='O22' || $Contrato.comprador.cliente.cuit == '30-50095962-9')'Base de comercialización: Las entregas de soja quedan sujetas a la siguiente base de comercialización: Materias Extrañas: UNO POR CIENTO (1%) incluido CERO COMA CINCO POR CIENTO (0.5%) DE TIERRA. Tolerancias de recibo: Las entregas de soja quedan sujetas a las tolerancias de recibo que se establecen a continuación: Materias extrañas: TRES POR CIENTO (3%) incluido CERO COMA CINCO POR CIENTO (0.5%) de tierra.' else if($Contrato.producto.codigo=='O10'|| $Contrato.producto.codigo=='O22') 'Base de comercialización: Las entregas de soja quedan sujetas a la siguiente base de comercialización: Materias Extrañas: UNO POR CIENTO (1%) incluido CERO COMA CINCO POR CIENTO (0.5%) DE TIERRA. Tolerancias de recibo: Las entregas de soja quedan sujetas a las tolerancias de recibo que se establecen a continuacion: Materias extrañas: CUATRO POR CIENTO (4%) incluido CERO COMA CINCO POR CIENTO (0.5%) de tierra.'} @{if($Contrato.producto.codigo=='O10'|| $Contrato.producto.codigo=='O22' || $Contrato.comprador.cliente.cuit == '30-50095962-9') 'Granos negros: UNO POR CIENTO (1%) Granos quebrados y/o partidos: CINCUENTA POR CIENTO (50%) Cuerpos dañados: CINCO POR CIENTO (5%) - se computarán dentro de este rubro y con una tolerancia máxima libre de descuento del UNO POR CIENTO (1%)  a los granos quemados por secadora o "de avería". Granos Verdes: CINCO  POR CIENTO (5%) Humedad: TRECE COMA CINCO POR CIENTO (13.5%) Chamico (Datura ferox): CINCO (5) semillas por c/kilo. Insectos y/o arácnidos vivos: Libre.' else if($Contrato.producto.codigo=='O10'|| $Contrato.producto.codigo=='O22')'Granos negros: UNO POR CIENTO (1%) Granos quebrados y/o partidos: CINCUENTA POR CIENTO (50%) - Granos dañados: CINCO POR CIENTO (5 %) - se computarán dentro de este rubro y con una tolerancia máxima libre de descuento del UNO POR CIENTO (1%) a los granos quemados por secadora o "de avería". Granos Verdes: OCHO POR CIENTO (8%) Humedad: TRECE COMA CINCO POR CIENTO (13.5%) Chamico (Datura ferox): TREINTA (30) semillas por c/kilo. Insectos y/o arácnidos vivos: Libre.'}"
************************************************************************************************************************Implementado





**************************************************************************************************************Pendiente
	public Map abrirarchivo(){
	    	String[] docClientesB;
		    String fichero = ""; 
	    	try {	    	
		    	String cuitString = cuit.replace("-", "");
		    	fichero = manager.getSystemConfig("DocumentacionLegajo");
		    	File f = new File (fichero);
		    	String ruta = manager.getSystemConfig("DocumentacionLegajoLog");
		    	File file = new File(ruta);
	            // Si el archivo no existe es creado
	            if (!file.exists()) {
	                file.createNewFile();
	            }
	            FileWriter fw = new FileWriter(file,true);
	            BufferedWriter bw = new BufferedWriter(fw);
		    	if (f.exists()){ 
		    		// Directorio existe 
		    		File[] ficheros = f.listFiles();
		            boolean bandera = false;
		            List<String> nombre;
		            for (int x=0;x<ficheros.length;x++){
		            	String nombreArchivo = (ficheros[x].getName().length()>=11)?ficheros[x].getName().substring(0, 11):"";	            	
		              if(cuitString.equalsIgnoreCase(nombreArchivo)){
		            	  rutaGlobal = fichero+"/"+ficheros[x].getName();
		            	  f = new File (rutaGlobal);
		            	  File carpetaComprimir = new File (rutaGlobal); 
		            	  File[] ficherosb = carpetaComprimir.listFiles();
		            	  docClientes = f.list();	            	  
		            	  Integer iPrueba = 0;
		            	  for (int i= 0;i<ficherosb.length;i++) {
		            		  if (ficherosb[i].getName().contains("Mercado de capitales") && (!ficherosb[i].getName().contains(".zip") || ficherosb[i].getName().contains(".rar") )){	            			  
		            			                      
		                          bw.write(file.getAbsolutePath()+" "+(cont++)+"\n");
		                          bw.write("A.-"+file.getPath()+" "+(cont++)+"\n"); 
	                              FileOutputStream fos = new FileOutputStream(rutaGlobal+"/Mercado de capitales.zip");
		            		        ZipOutputStream zos = new ZipOutputStream(fos);
		            		        String arcZip = rutaGlobal+"/Mercado de capitales/";
		            		        bw.write("A.-"+rutaGlobal+"/Mercado de capitales.zip"+" "+(cont++)+"\n");
		            		        bw.write("A.-"+rutaGlobal+"/Mercado de capitales"+" "+(cont++)+"\n"); 
		            		        addDirAZip(zos, new File(arcZip), null);
		            		        zos.flush();
		            		        fos.flush();
		            		        zos.close();
		            		        fos.close();
		            		        bw.write("A.-"+"cerro output"+" "+(cont++)+"\n");
		            		  }
		            	  }
		            	  docClientes = f.list();
		            	  for (int i= 0;i<docClientes.length;i++) {	            			       
		            		if (docClientes[i].contains(".zip") || docClientes[i].contains(".rar")){
		            			documentos.put(iPrueba.toString(), docClientes[i]);
		            			iPrueba++;
		            			bw.write("A.-"+"Inserto en mapa a "+ docClientes[i].toString()+"\n"+cont++);
		            		}
		            	  }
		            	  f = new File (rutaGlobal+"/Mercado de capitales");
		            	  docClientesB = f.list();
		            	  for (int i= 0;i<docClientesB.length;i++) { 
		            		if (!docClientesB[i].contains("Thumbs")){
		            			documentos.put(iPrueba.toString(), docClientesB[i]);
		            			iPrueba++;
		            			bw.write("A.-"+"Inserto en mapa a "+ docClientesB[i].toString()+"\n"+cont++);
		            		}	            		
		            	  }
		            	  bandera = true;
		            	  break;
		              }
		            }  
		            if (!bandera){
		            	addActionError("No se ha encuentrado el archivo "+rutaGlobal);
		            }
		            bw.close(); 
		    	} else { 
		    		//Directorio no existe 
		    		addActionError("No se ha encuentrado el directorio "+fichero);
		    	} 
		     }catch (FileNotFoundException e) {
		    	 e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				addActionError("Error: Por favor comunicarse con el area de sistemas. "+e.getMessage());
			}    	 
		     return documentos;
		}
	    
	    public void changeEstado(){
	    	String doc = getRequest().getParameter("documento");
	    	String cuit = getRequest().getParameter("cuit");
	    	cuit = cuit.replace("-", "");
	    	String ruta = "";
	    	//String fichero="N://DOC. CLIENTES";
	    	String fichero = manager.getSystemConfig("DocumentacionLegajo");
	    	File f = new File (fichero); 
	    	if (f.exists()){ 
	    		File[] ficheros = f.listFiles();
	    		for (int x=0;x<ficheros.length;x++){
	    			String nombreArchivo = (ficheros[x].getName().length()>=11)?ficheros[x].getName().substring(0, 11):"";
	    			if(cuit.equalsIgnoreCase(nombreArchivo)){
	    				rutaGlobal = fichero+"/"+ficheros[x].getName();
	    				break;
	    			}
	    		}
	    	}
	    	File folder1 = new File(rutaGlobal);
	    	File folder2 = new File(rutaGlobal+"/Mercado de capitales");
	    	String[] documentos1 = folder1.list();
	    	String[] documentos2 = folder2.list();
	    	int cont = 0;
	    	for (int i = 0; i < documentos1.length; i++) {
	    		if (documentos1[i].contains(".zip") || documentos1[i].contains(".rar")){
	    			documentos.put(Integer.valueOf(cont).toString(), documentos1[i]);
	    			cont++;
	    		}
			}
	    	for (int i= 0;i<documentos2.length;i++) { 
	    		if (!documentos2[i].contains("Thumbs")){
	    			documentos.put(Integer.valueOf(cont).toString(), documentos2[i]);	
	        		cont++;
	    		}    		
	    	 } 	   	
			doc = documentos.get(doc).toString();		
	    	try {    		
	    		if(doc.contains(".rar") || doc.contains(".zip")){
	    			ruta = folder1.getPath()+"/"+doc;	
	    			FileUtils.downloadZIP(ruta, ServletActionContext.getResponse());
	    		}else{	
	    			ruta =folder2.getPath()+"/"+doc;	
	    			FileUtils.downloadFile( ruta, ServletActionContext.getResponse(), "application/pdf");
	    		}		
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    public String consulta(){
	    	this.setHabilitarEdicionContacto(false);
	    	if (id != null){
	    		crmFichaCliente = crmFichaClienteManager.get(id);
	    		
	    		setearFiltros(crmFichaCliente.getCliente().getId());
	    	}
	    	return SUCCESS;
	    }
	    
	    
	  //Metodo que comprime un Directorio a .zip
	  	public void addDirAZip(ZipOutputStream zos, File fileToZip, String parrentDirectoryName) throws Exception {
	  		 //File file = new File("/Documentacion/DOC. CLIENTES/Prueba.txt");
	  		 File file = new File(manager.getSystemConfig("DocumentacionLegajoLog"));
	         try {
	             //PrintWriter salida = new PrintWriter(new FileWriter(archivo), true);
	             FileWriter fw = new FileWriter(file,true);
	             if (!file.exists()) {
	                 file.createNewFile();
	             }
	             BufferedWriter bw = new BufferedWriter(fw);
	             bw.write("B.-"+"Paso 1 archivo "+ fileToZip.getPath()+" "+(cont++)+"\n");             
	             if (fileToZip == null || !fileToZip.exists()) {
	            	 bw.write("B.-"+"No existe el archivo "+ fileToZip.getPath()+" "+(cont++)+"\n");
	                 return;
	             }
	             
	             String zipEntryName = fileToZip.getName();
	             bw.write("B.-"+"Archivo de entrada  "+ zipEntryName+" "+(cont++)+"\n");
	             if (parrentDirectoryName!=null && !parrentDirectoryName.isEmpty()) {
	                 zipEntryName = parrentDirectoryName + "/" + fileToZip.getName();
	                 bw.write("C.-"+"Archivo de entrada Hijo "+ zipEntryName+" "+(cont++)+"\n");
	             }
	             
	             if (fileToZip.isDirectory()) {
	            	 bw.write("Es Directorio "+ fileToZip.getPath()+" "+(cont++)+"\n");
	                 for (File file1 : fileToZip.listFiles()) {
	                	 bw.write("C.-"+"Es Hijo "+ file1.getPath()+" "+(cont++)+"\n");
	                 	addDirAZip(zos, file1, zipEntryName);
	                 }
	             } else {
	            	 bw.write("D.-"+"Compresion:  "+ fileToZip.getPath()+" "+(cont++)+"\n");
	            	 bw.write("D.-"+"Compresion:  "+ zipEntryName+" "+(cont++)+"\n");
	                 byte[] buffer = new byte[1024];
	                 FileInputStream fis = new FileInputStream(fileToZip);
	                 zos.putNextEntry(new ZipEntry(zipEntryName));
	                 int length;
	                 while ((length = fis.read(buffer)) > 0) {
	                     zos.write(buffer, 0, length);
	                 }
	                 zos.closeEntry();
	                 fis.close();
	                 bw.write("D.-"+"Finalizo la compresion del :  "+ zipEntryName+" "+(cont++)+"\n");
	             }
	             bw.close();
	         } catch (IOException ex) {
	             ex.printStackTrace();
	         }
	  	
	  	}
************************************************************************************************************************




32.*********************************************************************************************************************
************************************************************************************************************************
***************************************************************************Requerimiento de Adolfo Shaw Pedido de fondos
	Descripcion: Se solicita agregar una columna en el reporte para la posicion global, esta debe depender de la moneda, 
	el valor de posicion global lo encontramos al editar el registro en el mismo reporte.


	Requerimiento de Claudio Pedido de Fondos, se añade calculo de la posicion Global en dolares y pesos segun sea el 
	caso a Pedido de Fondos.
	Documentacion___________________________________________________________________________________________________
		***Opcion del reporte:
		Comercial --> Pedido de fondos  
		***menu-config.xml
		<Menu name="ComercialMenu" title="menu.comercial" description="Comercial" roles="ROLE_USER,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_FACTURACION" page=""	width="50">
        	<Item name="PedidoDeFondoMenu" title="pedidoDeFondo.title" page=""	width="250" roles="ROLE_USER,ROLE_COMERCIAL,ROLE_COMERCIAL_REQ_P,ROLE_IMPUESTOS,ROLE_COBRANZAS,ROLE_TESORERIA">
        		<Item name="PedidoDeFondoConsulta" title="pedidoDeFondo.consulta" page="/pedidoDeFondoes.html" width="250"/>
        ***struts.xml
        	<action name="pedidoDeFondoes" class="com.zeni.app.webapp.action.PedidoDeFondoListAction" method="list">
				<interceptor-ref name="jsonValidationWorkflowStack" />
				<result>/WEB-INF/pages/pedidoDeFondoList.jsp</result>
			</action>

	Modificaciones_________________________________________________________________________________________________
	***Se crea clase SaldosCuentaCliente.java

	***jexlModelINS.java, se crea metodo saldoGlobal(CuentaCliente cc, String moneda) para que busque el saldo y 
	pueda ser invocado por la grilla de la vista
		public Double saldoGlobal(CuentaCliente cc, String moneda){
			SaldosCuentaCliente saldos = new SaldosCuentaCliente(cc, true);
			if ("$".equals(moneda)){
				return saldos.getSaldoCtaCteGEPesos();
			}else{
				return saldos.getSaldoCtaCteGEDolares();
			}
		}

	***pedidoDeFondoList.jsp, se añade columna a la grilla con el valor del saldo Global
		<ria:simplegridcolumn label="" width="20" id="moneda" key="-XLS-$item.moneda.simbolo" style="text-align:center;">
               ~{if($item.moneda.simbolo == '$')
                         "<label style='';>$</label>";
                        else "<label style='color: black;font-weight: bold'>US</label>";}~        
        </ria:simplegridcolumn>
		<ria:simplegridcolumn label="Grupo CtaCte." width="100" key="model:saldoGlobal($item.cuentaCliente,$item.moneda.simbolo)" id="grupoCtaCte" style="text-align:right; " />
        
	Nota: Por pedido de Adolfo Shaw se reemplaza la columna saldo ctacte por la columna creada
		<ria:simplegridcolumn id="saldoPesos" visible="true" label="Saldo CtaCte $" width="100" key="$item.cuentaCliente.saldoCtaCtePesos" style="text-align:right;" />	
************************************************************************************************************************IMplementado





33.*********************************************************************************************************************
************************************************************************************************************************
******************************************************Ticket#1014234 — Descripción en cuenta corriente de mercados y FCI 
	Usuario 	--> MEZA PABLO (D)	
	Ext			--> 5069	
	Area		--> Mercaderías	Casa Central
	Descripcion:
	Necesitamos que cuando se liquide un comprobante que se graba en las cuentas corrientes de los clientes y en una 
	cuenta de mercados como Byma – MAV – Matba – Rofex – Delta – FIMA en la descripción de la cuenta de mercado grabe el 
	número de cuenta y la razón social además del concepto que se liquida.

	Debería describir para el primer comprobante: Caución Tomador – (50305) Tomas Hnos Y Cia S.A.
	 
	Se puede tomar como modelo alguna de las cuentas de diferencias y garantías.

	Documentacion___________________________________________________________________________________________________

		<Menu name="ClienteMenu"   title="menu.cliente" description="Cliente" roles="ROLE_ADMIN,ROLE_USER,ROLE_CLIENTE_FULL,ROLE_CLIENTE_VIEW" page="" width="40">
            <Item name="CtaCteMenu" title="menu.ctacte"  page="/movimientoes.html" width="240"/>

        <action name="movimientoes" class="com.zeni.app.webapp.action.MovimientoAction"
			method="list">
			<result>/WEB-INF/pages/movimientoList.jsp</result>
		</action>

		***Cauciones
			<action name="mfCauciones" class="com.zeni.app.webapp.action.FacturacionMFCaucionAction"
				method="list">
				<result>/WEB-INF/pages/mfCaucionList.jsp</result>
			</action>

			<action name="editMFCaucion" class="com.zeni.app.webapp.action.FacturacionMFCaucionAction"
				method="edit">
				<result>/WEB-INF/pages/mfCaucionForm.jsp</result>
				<result name="error">/WEB-INF/pages/mfCaucionList.jsp</result>
			</action>

			<input type="button" class="button" id="simularButton" value="Simular" onclick="javascript:onSimularVencimiento();" style="width:160px;"/>

			function onSimular() {
				new Ajax.Request('<c:url value="/onSimularMFCaucion.html"/>',

			/*<action name="onSimularMFCaucion" class="com.zeni.app.webapp.action.FacturacionMFCaucionAction" method="simular">
				<interceptor-ref name="jsonValidationWorkflowStack" />
				<result name="success" type="noOp"/>
			</action>*/

		***Diferencias y Garantias
			/*<action name="diferenciasGarantias" class="com.zeni.app.webapp.action.DiferenciasGarantiasListAction" method="list">
				<result name="success">/WEB-INF/pages/diferenciasGarantiasList.jsp</result>
			</action>*/

			/*<input id="facturarBtn" type="button" class="button" value="Facturar" onclick="javascript:onFacturar()" />
				function onFacturar() {
					new Ajax.Request('<c:url value="/facturarDiferenciasGarantias.html"/>'*/

						<action name="facturarDiferenciasGarantias" class="com.zeni.app.webapp.action.DiferenciasGarantiasListAction" method="facturar">
							<result>/WEB-INF/pages/empty.jsp</result>
						</action>

		***Movimientos  de Acciones
			/*<action name="facturacionMatAcciones" class="com.zeni.app.webapp.action.FacturacionMatAccionAction" method="list">
				<result name="success">/WEB-INF/pages/facturacionMatAccion.jsp</result>
			</action>
				facturacionMatAccion.jsp*/
					<input type="button" class="button" id="facturarButton" value="Facturar" onclick="javascript:onFacturar();" style="width:160px; display: none;"/>
						new Ajax.Request('<c:url value="/onFacturarMatAccion.html"/>',
									/*<action name="onFacturarMatAccion" class="com.zeni.app.webapp.action.FacturacionMatAccionAction" method="facturar">
										<interceptor-ref name="jsonValidationWorkflowStack" />
										<result name="success" type="noOp"/>
									</action>*/

		***Movimiento de Acreencias
			<action name="mfAcreencias" class="com.zeni.app.webapp.action.MFAcreenciasAction" method="list">
				<result>/WEB-INF/pages/mfAcreenciasList.jsp</result>
			</action>

			/*<input type="button" class="button" id="simularButton" value="Simular" onclick="javascript:onSimular();" style="width:160px; margin-top:5px" />*/
				new Ajax.Request('<c:url value="/onSimularMatAcreencia.html"/>',

						/*<action name="onSimularMatAcreencia" class="com.zeni.app.webapp.action.MFAcreenciasAction" method="simular">
							<interceptor-ref name="jsonValidationWorkflowStack" />
							<result name="success" type="noOp"/>
						</action>*/

		***facturacionMatManagerImp
		protected void generarMovimientos(FacturaProducto factura, Double importe, String descripcionMov) {
				ctacteManager.generarMovimientosRegDifGtia(factura, importe, descripcionMov);
			}
			protected void generarMovimientosAt(FacturaProducto factura, Double importe, String descripcionMov) {
				ctacteManager.generarMovimientosAcciones(factura, importe, descripcionMov);
			}
			protected void generarMovimientosAt(FacturaProducto factura, Double importe, String descripcionMov, CuentaCliente contraparte) {
				ctacteManager.generarMovimientosAcreencia(factura, importe, descripcionMov, contraparte);
			}
			protected void generarMovimientosMFCaucionAt(FacturaProducto factura, CuentaCliente ccMercado, Double importe, Double importeMercado, String descripcionMov) {
				ctacteManager.generarMovimientosMFCaucion(factura, ccMercado, importe, importeMercado, descripcionMov);
			}


			ctacteManager.generarMovimientosRegDifGtia(cuentaCliente, 
										(Double)factura.getDatosTransient().get("IMPORTE_ORIGINAL"),
										(Moneda)factura.getDatosTransient().get("MONEDA"),
										factura, 
										String.format("(%s) %s", factura.getCuentaCliente().getNroCuenta(), factura.getCuentaCliente().getCliente().getRazonSocial()));

	Modificaciones_________________________________________________________________________________________________
	***** Movimientos  de cauciones
	***FacturacionMFCaucionManagerImpl.java, el metodo facturarVencimientoGuardarFactura(), se
	agrega el numero de cuenta y la razon social a la descripcion del movimiento.
		generarMovimientosMFCaucionAt(facturaPersistida, mfCaucion.getMercado().getCuentaClienteMAT(), 
				(AppConstants.ABREVIATURA_LIQUIDACION_NO_FISCAL.equals(factura.getTipoComprobante().getAbreviatura())?factura.getImporteACuenta():-factura.getImporteACuenta()), 
				(mfCaucion.getCondicion()=='T'?-mfCaucion.getMontoFinal():mfCaucion.getMontoFinal()), String.format("(%s) %s Caución %s - Mercado: %s - Nro Boleto: %s", 
				factura.getCuentaCliente().getNroCuenta(), factura.getCuentaCliente().getDenominacionCuenta(), PropertyValues.getCondicionCaucion().get(mfCaucion.getCondicion()), 
				mfCaucion.getMercado().getNombre(), (mfCaucion.getNroBoleto()==null?"":mfCaucion.getNroBoleto().toString())));

	***** Movimientos de Acciones
	***FacturacionMatAccionManagerImpl.java, facturarMatAccionesGuardarFactura(), se agrega el numero de cuenta y la 
	razon social a la descripcion del movimiento.
		generarMovimientosAt(facturaPersistida, -factura.getImporteACuenta(), 
				String.format("(%s) %s %s",factura.getCuentaCliente().getNroCuenta(), factura.getCuentaCliente().getCliente().getRazonSocial(), 
					factura.getLeyendaMat()));

	****Movimientos de Acreencias
	***FacturacionMatAcreenciaManagerImpl.java, facturarMatAcreenciaGuardarFactura(), se agrega el numero de cuenta y la 
	razon social a la descripcion del movimiento.
		generarMovimientosAt(facturaPersistida, -factura.getImporteACuenta(), String.format("(%s) %s %s",factura.getCuentaCliente().getNroCuenta(), 
				factura.getCuentaCliente().getCliente().getRazonSocial(),factura.getLeyendaMat()));

		//mov en cta mercado/contraparte		
		if(contraparte!=null)
			generarMovimientosAt(facturaPersistida, factura.getImporteACuenta(), String.format("(%s) %s %s",factura.getCuentaCliente().getNroCuenta(), 
					factura.getCuentaCliente().getCliente().getRazonSocial(),factura.getLeyendaMat()), contraparte);
************************************************************************************************************************Implementado





34.*********************************************************************************************************************
************************************************************************************************************************
*****************************************************Ticket#1014251 — Pendiente de Futuros y MC en los Pedidos de Fondos 
	Usuario 	--> MEZA PABLO (D)	
	Ext			--> 5069	
	Area		--> Mercaderías	Casa Central
	Descripcion:
	El programa de PEDIDO DE FONDOS, para el pendiente de liquidar Mercados de Futuro y Capitales no está teniendo en 
	cuenta para el saldo a liquidar de cancelaciones y primas.
	Necesitamos corregir esta omisión en forma urgente.
	Se adjunta el ejemplo de Adeco para el día de hoy.

	Nota: en la consulta de pedido de fondo, el campo Pendiente Mercado de Futuros y Capitales, no esta tomando en cuante 
	Agrupa Caratula y Cancelacion, estos datos los trae el reporte de diferencia y garantias --> opcion Futuro y Opciones-->Archivo de Dif. y Gatia cliente.
	Esta incidencia se da tanto para pesos como para dolares.



	Documentacion___________________________________________________________________________________________________
	*****Archvo de diferencias y garantia
		/*<action name="editDiferenciasGarantiasTotalesCliente"
			class="com.zeni.app.webapp.action.DiferenciasGarantiasTotalesClienteAction" method="edit">
			<interceptor-ref name="jsonValidationWorkflowStack" />
			<result>WEB-INF/pages/diferenciasGarantiasTotalesClienteForm.jsp</result>
			<result name="error">WEB-INF/pages/diferenciasGarantiasTotalesClienteForm.jsp</result>
		</action>*/

		***generarReporte()
			//Matba U$S
			datosCliente.setCancelaMatbaDolar(saldoCanceladas(cuentaCliente.getId(),fecha,matbaId,monedaDolarId));
			datosCliente.setAgrupaCarMatbaDolar(saldoOfertaEntregaAgrupada(cuentaCliente.getId(),fecha,matbaId,monedaDolarId));
			//Rofex U$S
			datosCliente.setCancelaRofexDolar(saldoCanceladas(cuentaCliente.getId(),fecha,rofexId,monedaDolarId));
			datosCliente.setAgrupaCarRofexDolar(saldoCanceladaParaEntrega(cuentaCliente.getId(),fecha,rofexId,monedaDolarId));
			//Rofex $
			datosCliente.setCancelaRofexPesos(saldoCanceladasPesos(cuentaCliente.getId(),fecha,rofexId,monedaPesosId));
	Modificaciones__________________________________________________________________________________________________
		***PedidoDeFondoManager.java, se crean metodo en la interface

			public Double saldoCanceladas(Long cuentaClienteId, Date fecha, Long mercadoId, Long monedaId);	
			public Double saldoCanceladasPesos(Long cuentaClienteId, Date fecha, Long mercadoId, Long monedaId);			
			public Double saldoOfertaEntregaAgrupada(Long cuentaClienteId, Date fecha, Long mercadoId, Long monedaId);			
			public Double saldoCanceladaParaEntrega(Long cuentaClienteId, Date fecha, Long mercadoId, Long monedaId);
			public Double saldoOpciones(Long cuentaClienteId, Date fecha, Long mercadoId, Long monedaId);

		***PedidoDeFondoManagerImpl.java, se implementan los metodos de busqueda de saldos para pedidos de fondo

			@SuppressWarnings("unchecked")
			public Double saldoCanceladas(Long cuentaClienteId, Date fecha,
					Long mercadoId, Long monedaId) {
					Double saldo = 0D;
					String query;
					String condicion = "";
					Double precio = 0D;
					Double precioEntrada = 0D;
					Integer volumen = 0;
					Long idLiquidado;
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaHasta = new Date();
					
					query = "SELECT mt.condicion, mt.precioOprima, mp.volumenContrato, mr.liquida_a_id" +
							" FROM mat_registro mr" +
							" LEFT OUTER JOIN mat_operacion mt ON mt.id = mr.matOperacion_id" +
							" LEFT OUTER JOIN mat_producto mp ON mp.id = mt.matProducto_id" + 
							" LEFT OUTER JOIN cuenta_cliente cc ON cc.id = mt.cuentaCliente_id" +
							" LEFT OUTER JOIN workflow_state ws on mr.state_id = ws.id" +
							" LEFT OUTER JOIN workflow_definition wd on ws.workflowdefinition_id = wd.id" +
							" WHERE mt.cuentaCliente_id = " + cuentaClienteId +
							" AND mt.mercado_id = " + mercadoId +
							" AND mp.monedaOperacion_id = " + monedaId +
							" AND mr.accion = 'C'" +
							" AND wd.estadoActual IN('C')" +
							" AND ws.fecha >= to_date('"+ dateFormat.format(fecha) + "','dd/MM/yyyy')" +
							" AND ws.fecha < to_date('"+ dateFormat.format(fechaHasta) + "','dd/MM/yyyy')" +
							" AND mt.fechaBaja IS NULL" +
							" AND (SELECT COUNT(fp.id)" +
						    "      FROM factura_producto fp" +
						    "      JOIN rel_matOperacion_factura rmof ON rmof.factura_id = fp.id" +
						    "      WHERE rmof.matoperacion_id = mt.id" +
						    "      AND fp.anulador_id IS NULL" +
						    "      AND UPPER(fp.facturador) = 'FACTURACIONREGISTROS-C') = 0";
					
					List<Object[]> result_query = (List<Object[]>) manager.executeSQL(query);
					for(Object[] reg_cancelaciones : result_query) {
						condicion = ((Character) reg_cancelaciones[0]).toString();
						precio = ((BigDecimal) reg_cancelaciones[1]).doubleValue();
						volumen = ((BigDecimal) reg_cancelaciones[2]).intValue();
						idLiquidado = ((BigDecimal) reg_cancelaciones[3]).longValue();
						
						query = "";
						query = "SELECT mo.condicion, mo.precioOprima" +
								" FROM mat_registro mr" +
								" INNER JOIN mat_operacion mo ON mo.id = mr.matOperacion_id" +
								" WHERE mr.id = " + idLiquidado;
						List<Object[]> result_reg = (List<Object[]>) manager.executeSQL(query);
						for(Object[] reg_entrada : result_reg) {
							precioEntrada = ((BigDecimal) reg_entrada[1]).doubleValue();
							
							if("V".equals(condicion)){
								saldo = saldo + ((precio - precioEntrada) * volumen);
							}else{
								saldo = saldo + ((precioEntrada - precio) * volumen);
							}
						}
					}
					return saldo;
				}

				public Double saldoCanceladasPesos(Long cuentaClienteId, Date fecha,
						Long mercadoId, Long monedaId) {
					Double saldo = 0D;
					String query;
					String condicion = "";
					Double precio = 0D;
					Double precioEntrada = 0D;
					Integer volumen = 0;
					Long idLiquidado;
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaHasta = DateUtil.addToDate(fecha, Calendar.DAY_OF_MONTH, 1);
					
					query = "SELECT mt.condicion, mt.precioOprima, mt.volumen, mr.liquida_a_id" +
							" FROM mat_registro mr" +
							" LEFT OUTER JOIN mat_operacion mt ON mt.id = mr.matOperacion_id" +
							" LEFT OUTER JOIN mat_producto mp ON mp.id = mt.matProducto_id" + 
							" LEFT OUTER JOIN cuenta_cliente cc ON cc.id = mt.cuentaCliente_id" +
							" LEFT OUTER JOIN workflow_state ws on mr.state_id = ws.id" +
							" LEFT OUTER JOIN workflow_definition wd on ws.workflowdefinition_id = wd.id" +
							" WHERE mt.cuentaCliente_id = " + cuentaClienteId +
							" AND mt.mercado_id = " + mercadoId +
							" AND mp.monedaOperacion_id = " + monedaId +
							" AND mr.accion = 'C'" +
							" AND wd.estadoActual IN('C')" +
							" AND mt.fecha >= to_date('"+ dateFormat.format(fecha) + "','dd/MM/yyyy')" +
							" AND mt.fecha < to_date('"+ dateFormat.format(fechaHasta) + "','dd/MM/yyyy')" +
							" AND mt.fechaBaja IS NULL";
					
					List<Object[]> result_query = (List<Object[]>) manager.executeSQL(query);
					
					for(Object[] reg_cancelaciones : result_query) {
						condicion = ((Character) reg_cancelaciones[0]).toString();
						precio = ((BigDecimal) reg_cancelaciones[1]).doubleValue();
						volumen = ((BigDecimal) reg_cancelaciones[2]).intValue();
						idLiquidado = ((BigDecimal) reg_cancelaciones[3]).longValue();
						
						query = "";
						query = "SELECT mo.condicion, mo.precioOprima" +
								" FROM mat_registro mr" +
								" INNER JOIN mat_operacion mo ON mo.id = mr.matOperacion_id" +
								" WHERE mr.id = " + idLiquidado;
						List<Object[]> result_reg = (List<Object[]>) manager.executeSQL(query);
						for(Object[] reg_entrada : result_reg) {
							precioEntrada = ((BigDecimal) reg_entrada[1]).doubleValue();
						
							if("V".equals(condicion)){
								saldo = saldo + ((precio - precioEntrada) * volumen);
							}else{
								saldo = saldo + ((precioEntrada - precio) * volumen);
							}
						}
					}
					return saldo;
				}
				
				@SuppressWarnings("unchecked")
				public Double saldoOfertaEntregaAgrupada(Long cuentaClienteId, Date fecha, Long mercadoId, Long monedaId){
					Double saldo = 0D;
					String query;
					String condicion = "";
					Double precio = 0D;
					Double precioEntrada = 0D;
					Integer volumen = 0;
					Long idProducto;
					String tipo = "";
					Long idDestino;
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaHasta = DateUtil.addToDate(fecha, Calendar.DAY_OF_MONTH, 1);
					
					query = "SELECT mt.condicion, mt.precioOprima, mp.volumenContrato, " +
							" mt.matProducto_id, mt.tipo, mt.destino_id" +
							" FROM mat_registro mr" +
							" LEFT OUTER JOIN mat_operacion mt ON mt.id = mr.matOperacion_id" +
							" LEFT OUTER JOIN mat_producto mp ON mp.id = mt.matProducto_id" + 
							" LEFT OUTER JOIN cuenta_cliente cc ON cc.id = mt.cuentaCliente_id" +
							" LEFT OUTER JOIN workflow_state ws on mr.state_id = ws.id" +
							" LEFT OUTER JOIN workflow_definition wd on ws.workflowdefinition_id = wd.id" +
							" WHERE mt.cuentaCliente_id = " + cuentaClienteId +
							" AND mt.mercado_id = " + mercadoId +
							" AND mp.monedaOperacion_id = " + monedaId +
							" AND mt.tipo IN('F','T')" +
							" AND wd.estadoActual IN('OA')" +
							" AND ws.fecha >= to_date('"+ dateFormat.format(fecha) + "','dd/MM/yyyy')" +
							" AND ws.fecha < to_date('"+ dateFormat.format(fechaHasta) + "','dd/MM/yyyy')" +
							" AND mt.fechaBaja IS NULL";
				
					List<Object[]> result_query = (List<Object[]>) manager.executeSQL(query);
					for(Object[] reg_ofertaEntrAgrup : result_query) {
						condicion = ((Character) reg_ofertaEntrAgrup[0]).toString();
						precio = ((BigDecimal) reg_ofertaEntrAgrup[1]).doubleValue();
						volumen = ((BigDecimal) reg_ofertaEntrAgrup[2]).intValue();
						idProducto = ((BigDecimal) reg_ofertaEntrAgrup[3]).longValue();
						tipo = ((Character) reg_ofertaEntrAgrup[4]).toString();
						idDestino = ((BigDecimal) reg_ofertaEntrAgrup[5]).longValue();

						query = "";			
						query = "SELECT MAX(mt.precioOPrima) " +
								" FROM mat_registro mr" +
								" LEFT OUTER JOIN mat_operacion mt ON mt.id = mr.matOperacion_id" +
								" LEFT OUTER JOIN mat_producto mp ON mp.id = mt.matProducto_id" +
								" LEFT OUTER JOIN cuenta_cliente cc ON cc.id = mt.cuentaCliente_id" +
								" LEFT OUTER JOIN workflow_state ws on mr.state_id = ws.id" +
								" LEFT OUTER JOIN workflow_definition wd on ws.workflowdefinition_id = wd.id" +
								" WHERE mt.cuentaCliente_id = " + cuentaClienteId +
								" AND mt.mercado_id = " + mercadoId +
								" AND mp.monedaOperacion_id = " + monedaId +
								" AND mt.matProducto_id = " + idProducto +
								" AND mt.destino_id = " + idDestino +
								" AND mt.tipo = '" + tipo + "'" +
								" AND wd.estadoActual IN('T')" +
								" AND ws.fecha >= to_date('"+ dateFormat.format(fecha) + "','dd/MM/yyyy')" +
								" AND ws.fecha < to_date('"+ dateFormat.format(fechaHasta) + "','dd/MM/yyyy')" +
								" AND mt.fechaBaja IS NULL";
					
						List result_query1 = (List) manager.executeSQL(query);
						if(!result_query1.isEmpty() && !Util.isEmpty(result_query1.get(0))){
							precioEntrada = ((BigDecimal) result_query1.get(0)).doubleValue();
							
							if("V".equals(condicion)){
								saldo = saldo + ((precio - precioEntrada) * volumen);
							}else{
								saldo = saldo + ((precioEntrada - precio) * volumen);
							}
						}
					}
					
					return saldo;
				}
				
				@SuppressWarnings("unchecked")
				public Double saldoCanceladaParaEntrega(Long cuentaClienteId, Date fecha, Long mercadoId, Long monedaId){
					Double saldo = 0D;
					String query;
					String condicion = "";
					Double precio = 0D;
					Double precioEntrada = 0D;
					Integer volumen = 0;
					Long idLiquidado;
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaHasta = DateUtil.addToDate(fecha, Calendar.DAY_OF_MONTH, 1);
					
					query = "SELECT mt.condicion, mt.precioOprima, mp.volumenContrato, mr.liquida_a_id" +
							" FROM mat_registro mr" +
							" LEFT OUTER JOIN mat_operacion mt ON mt.id = mr.matOperacion_id" +
							" LEFT OUTER JOIN mat_producto mp ON mp.id = mt.matProducto_id" + 
							" LEFT OUTER JOIN cuenta_cliente cc ON cc.id = mt.cuentaCliente_id" +
							" LEFT OUTER JOIN workflow_state ws on mr.state_id = ws.id" +
							" LEFT OUTER JOIN workflow_definition wd on ws.workflowdefinition_id = wd.id" +
							" WHERE mt.cuentaCliente_id = " + cuentaClienteId +
							" AND mt.mercado_id = " + mercadoId +
							" AND mp.monedaOperacion_id = " + monedaId +
							" AND mt.tipo = 'F'" +
							" AND wd.estadoActual IN('CE')" +
							" AND ws.fecha >= to_date('"+ dateFormat.format(fecha) + "','dd/MM/yyyy')" +
							" AND ws.fecha < to_date('"+ dateFormat.format(fechaHasta) + "','dd/MM/yyyy')" +
							" AND mt.fechaBaja IS NULL" +
						    " AND (SELECT COUNT(fp.id)" +
							"      FROM factura_producto fp" +
							"      JOIN rel_matOperacion_factura rmof ON rmof.factura_id = fp.id" +
							"      WHERE rmof.matoperacion_id = mt.id" +
							"      AND fp.anulador_id IS NULL" +
							"      AND UPPER(fp.facturador) = 'FACTURACIONREGISTROS-CE') = 0";
					
					List<Object[]> result_query = (List<Object[]>) manager.executeSQL(query);
					for(Object[] reg_cancelaParaEntrega : result_query) {
						condicion = ((Character) reg_cancelaParaEntrega[0]).toString();
						precio = ((BigDecimal) reg_cancelaParaEntrega[1]).doubleValue();
						volumen = ((BigDecimal) reg_cancelaParaEntrega[2]).intValue();
						idLiquidado = ((BigDecimal) reg_cancelaParaEntrega[3]).longValue();
						
						query = "";
						query = "SELECT mo.condicion, mo.precioOprima" +
								" FROM mat_registro mr" +
								" INNER JOIN mat_operacion mo ON mo.id = mr.matOperacion_id" +
								" WHERE mr.id = " + idLiquidado;
						List<Object[]> result_reg = (List<Object[]>) manager.executeSQL(query);
						for(Object[] reg_entrada : result_reg) {
							precioEntrada = ((BigDecimal) reg_entrada[1]).doubleValue();
						
							if("V".equals(condicion)){
								saldo = saldo + ((precio - precioEntrada) * volumen);
							}else{
								saldo = saldo + ((precioEntrada - precio) * volumen);
							}
						}
					}
				
					return saldo;
				}

				@SuppressWarnings("unchecked")
				public Double saldoOpciones(Long cuentaClienteId, Date fecha, Long mercadoId, Long monedaId){
					Double saldo = 0D;
					Double importe = 0D;
					String query;
					String condicion;
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					
					query = "SELECT (mt.prima * mp.volumenContrato) AS importe, mt.condicion," +
							" mp.monedaOperacion_id" +
							" FROM mat_registro mr" +
							" INNER JOIN mat_operacion mt ON mt.id = mr.matOperacion_id" +
							" INNER JOIN mat_producto mp ON mp.id = mt.matProducto_id" +
							" INNER JOIN workflow_state ws on mr.state_id = ws.id" +
							" INNER JOIN workflow_definition wd on ws.workflowdefinition_id = wd.id" +
							" WHERE mt.cuentaCliente_id = " + cuentaClienteId +
							" AND ws.fecha >= to_date('"+ dateFormat.format(fecha) + "','dd/MM/yyyy')" +
							" AND ws.fecha <= to_date('"+ dateFormat.format(fecha) + "','dd/MM/yyyy')" +
							" AND mt.mercado_id = " + mercadoId +
							" AND mp.monedaOperacion_id = " + monedaId +
							" AND mt.tipo IN('C','P')" +
							" AND wd.estadoActual IN('F','C')" +
							" AND mt.fechaBaja IS NULL";
					
					List<Object[]> result_query = (List<Object[]>) manager.executeSQL(query);
					for(Object[] reg_opciones : result_query) {
						importe = ((BigDecimal) reg_opciones[0]).doubleValue();
						condicion = ((Character) reg_opciones[1]).toString();
						
						if("V".equals(condicion)){
							saldo = saldo + importe;
						}else{
							saldo = saldo - importe;
						}
					}
					
					return saldo;
				}

		***PedidoDeFondoAction.java, se modifica el metodo setSaldos(), para que sume los saldos de cancelacion y agrupados
			Date diaPrevio = DateUtil.getDiaHabilPrevio(new Date(), Boolean.TRUE);
			Long rofexId = new Long(manager.getSystemConfig("id_mercado_rofex"));
			Long matbaId = new Long(manager.getSystemConfig("id_mercado_matba"));
			Long monedaDolarId = new Long(manager.getSystemConfig("id_moneda_US"));
			Long monedaPesosId = new Long(manager.getSystemConfig("id_moneda_ARG")); 

			Double CancelaRofexDolar = pedidoDeFondoManager.saldoCanceladas(this.cuentaCliente.getId(), diaPrevio, rofexId, monedaDolarId);
			Double CancelaMatbaDolar = pedidoDeFondoManager.saldoCanceladas(this.cuentaCliente.getId(), diaPrevio, matbaId, monedaDolarId);
			Double CancelaRofexPesos = pedidoDeFondoManager.saldoCanceladasPesos(this.cuentaCliente.getId(), diaPrevio, rofexId, monedaPesosId);
			Double AgrupaCarMatbaDolar = pedidoDeFondoManager.saldoOfertaEntregaAgrupada(this.cuentaCliente.getId(), diaPrevio, matbaId, monedaDolarId);
			Double AgrupaCarRofexDolar = pedidoDeFondoManager.saldoCanceladaParaEntrega(this.cuentaCliente.getId(), diaPrevio, rofexId, monedaDolarId);
			double opcionesMatbaDolar = pedidoDeFondoManager.saldoOpciones(this.cuentaCliente.getId(), diaPrevio, matbaId, monedaDolarId);
			double opcionesRofexDolar = pedidoDeFondoManager.saldoCanceladaParaEntrega(this.cuentaCliente.getId(), diaPrevio, rofexId, monedaDolarId);
			double opcionesRofexPesos = pedidoDeFondoManager.saldoOpciones(this.cuentaCliente.getId(), diaPrevio, rofexId, monedaPesosId);
				
			difFondosYOpsPesos = difFondosYOpsPesos + pendMercFutYCapitalPesos + cancelaRofexPesos + opcionesRofexPesos;
			difFondosYOpsDolares = difFondosYOpsDolares + pendMercFutYCapitalDolares + cancelaMatbaDolar + cancelaRofexDolar + 
						agrupaCarMatbaDolar + agrupaCarRofexDolar + opcionesMatbaDolar + opcionesRofexDolar;
************************************************************************************************************************Implementado





35.*********************************************************************************************************************
************************************************************************************************************************
*******************************************************************Ticket#1014375 — RV: Precios Delta - Carga automática   
	Usuario 	--> MEZA PABLO (D) / FRUMBOLI ALEJO	
	Ext			--> 5069 / 5141	
	Area		--> Mercaderías	Casa Central
	Descripcion:
	Precios se agregaron y no los levanta el sistema.


	Documentacion___________________________________________________________________________________________________
		<action name="preciosDelta" class="com.zeni.app.webapp.action.PreciosDeltaAction" method="list">
            <result>/WEB-INF/pages/preciosDelta.jsp</result>
        </action>

	begin 
	  DELTA AHORRO DECRETO N° 596/2019
	  insert into equivalencia_prod_fci values ('DELTA AHORRO PLUS DECRETO N° 596/2019 Art. 2', 'N', '$', 14980,'DELTA AHORRO PLUS DECRETO N° 596/2019 Art. 2','DELTA');
	  insert into equivalencia_prod_fci values ('DELTA RENTA DÓLARES PLUS DECRETO N° 596/2019', 'T', 'USD', 115197,'DELTA RENTA DÓLARES PLUS DECRETO N° 596/2019','DELTA');
	  insert into equivalencia_prod_fci values ('DELTA RENTA DÓLARES PLUS DECRETO N° 596/2019 Art. 2', 'N', 'USD', 115196,'DELTA RENTA DÓLARES PLUS DECRETO N° 596/2019 Art. 2','DELTA');
	end;
************************************************************************************************************************Implementado





36.*********************************************************************************************************************
************************************************************************************************************************
*********************************************************Ticket#1014323 — SYNGENTA - CTO. 19/00621/3 - ERROR AL LIQUIDAR 
	Usuario 	--> BONATO MATIAS		
	Ext			--> 5150	
	Area		--> Mercaderías	Casa Central
	Descripcion: Liquide la venta normalmente pero no me deja liquidar la final de la compra. 

	Nota: el usuario modifico los Kg. facturados.
************************************************************************************************************************Cerrado con Exito




***Facturacion y Liquidacion
37.*********************************************************************************************************************
************************************************************************************************************************
**************************************************************************Ticket#1013600 — Carga automática de cauciones 
	Usuario 	--> BECHARA SANTIAGO		Mercaderías	Casa Central
	Ext			--> 5192
	Area		--> Mercaderías	Casa Central
	Descripcion: 
	El mercado BYMA envía un archivo con todos los datos de las cauciones operadas en el día. Existirá la posibilidad  
	de la carga automática de cauciones colocadoras en pesos, colocadora en dólares y tomadoras en pesos a partir del 
	archivo que envía BYMA con las operaciones.

	Directrices:
	1.- Se deben cargar las cauciones de manera automatica desde un archivo .doc y aplicar formulas indicadas por el 
	usuario.
	2.- Se debe realizar un proceso simulado de facturación.
	3.- Mostrar en pantalla cada factura resultado del proceso simulado de facturacion y generacion del recibo en PDF 
	para su inspeccion por sistema.
	4.- Proceso que genere la facturacion segun dia indicado y se cargue en cuenta del cliente.

	Mercado Financiero --> Cauciones --> Nuevo

	Documentacion___________________________________________________________________________________________________

		<action name="mfCauciones" class="com.zeni.app.webapp.action.FacturacionMFCaucionAction"
			method="list">
			<result>/WEB-INF/pages/mfCaucionList.jsp</result>
		</action>

		/*<input type="button" style="margin-right: 5px" class="button" name="New"
        onclick="location.href='<c:url value="/editMFCaucion.html"/>'"
        value="<fmt:message key="button.add"/>"/>*/

        /*<action name="editMFCaucion" class="com.zeni.app.webapp.action.FacturacionMFCaucionAction"
			method="edit">
			<result>/WEB-INF/pages/mfCaucionForm.jsp</result>
			<result name="error">/WEB-INF/pages/mfCaucionList.jsp</result>
		</action>*/

		/*<input type="button" class="button" id="simularButton" value="Simular" onclick="javascript:onSimular();" style="width:160px;"/>*/
			new Ajax.Request('<c:url value="/onSimularMFCaucion.html"/>',

			/*<action name="onSimularMFCaucion" class="com.zeni.app.webapp.action.FacturacionMFCaucionAction" method="simular">
				<interceptor-ref name="jsonValidationWorkflowStack" />
				<result name="success" type="noOp"/>
			</action>*/

		Nota: Actualmente hay un proceso que va a un servidor y levanta las operaciones,  santiago indico la opcion.
			Mercado Financiero-opeaciones-operaciones byma-procesar.

			/*<action name="matOperacionCapitales" class="com.zeni.app.webapp.action.MatOperacionCapitalesAction" method="list">*/ 
			<result>/WEB-INF/pages/matOperacionCapitalesList.jsp</result>

			/*<meta name="filtro" content="/WEB-INF/pages/matOperacionCapitalesFiltro.jsp"/>
				<input type="button" style="margin-right: 5px;width:100px" class="button" name="operacionesBYMA"
	        	   onclick="location.href='<c:url value="/editMatIngresaOperaciones.html"/>'"
	        	   value="Operac. BYMA"/>*/

	        	/*<action name="editMatIngresaOperaciones" class="com.zeni.app.webapp.action.MatOperacionCapitalesAction" method="editOperaciones">
					<interceptor-ref name="jsonValidationWorkflowStack" />
					<interceptor-ref name="fileUploadStack" />
					<result name="input">/WEB-INF/pages/matIngresaOperacionesBymaForm.jsp</result>
					<result name="success">/WEB-INF/pages/matIngresaOperacionesBymaForm.jsp</result>
					<result name="cancel" type="redirectAction">mainMenu</result>
					<result name="error">/WEB-INF/pages/matIngresaOperacionesBymaForm.jsp</result>
				</action>*/

				/*<s:form id="matIngresaOperacionesBymaForm" action="saveMatOperacionArchivoByma" enctype="multipart/form-data" 
				method="post" onsubmit="return AjaxFormValidator.submit(this.id,'Operaciones')">
					<s:submit cssClass="button" id="uploadFile" method="importarOperaciones" value="Procesar" cssStyle="width:160px;" theme="simple"/>*/

						/*<action name="saveMatOperacionArchivoByma" class="com.zeni.app.webapp.action.MatOperacionCapitalesAction" method="saveByma">
							<interceptor-ref name="jsonValidationWorkflowStack" />
							<interceptor-ref name="fileUploadStack" />
							<result name="input">/WEB-INF/pages/matIngresaOperacionesBymaForm.jsp</result>
							<result name="cancel" type="redirectAction">matOperacionCapitales</result>
							<result name="delete" type="redirectAction">matOperacionCapitales</result>
							<result name="success" type="redirectAction">matOperacionCapitales</result>
							<result name="procesado">/WEB-INF/pages/matIngresaOperacionesBymaForm.jsp</result>
						</action>*/


	****Proceso de carga de operaciones
		BymaRequest.java
		MatOperacionCapitalesAction.java
		matIngresaOperacionesBymaForm.jsp

	****Facturacion de Cauciones
		FacturacionMFCaucionAction
		FacturacionMFCaucionManagerImpl
		mfCaucionList.jsp
		mfCaucionForm.jsp

		private void getRecuperarOperacionesPendientes(String pGrillaNombre) {

		Modificaciones_________________________________________________________________________________________________
		***struts-Mat.xml
			/*<action name="procesoAutByma" class="com.zeni.app.webapp.action.FacturacionMFCaucionAction"
					method="importarOperaciones">
					<result>/WEB-INF/pages/mfCaucionForm.jsp</result>
				</action>
				<action name="simularComprobante" class="com.zeni.app.webapp.action.FacturacionMFCaucionAction" method="simularComprobante">
					<result>/WEB-INF/pages/empty.jsp</result>
				</action>		
						<action name="facturarComprobante" class="com.zeni.app.webapp.action.FacturacionMFCaucionAction" method="facturarComprobante">
					<result>/WEB-INF/pages/empty.jsp</result>
			</action>	*/

		***mfCaucionForm.jsp
			++++Se Agrego
			/*</br>
			<div style="padding-left: 4px">
			<ria:collapse id="a" style="width:860px" header="Procesamiento Automático" collapse="true" >
				<table style="margin-left: 20px; padding-left: 5px">
					<td>
						<fieldset style="height: 100px;">
							<legend><fmt:message key="documentoRespaldatorio.procesamientoDirectoOPByma"/></legend>
							<li id="liButtons" class="buttonBar bottom">
						 	    <input type="button" style="margin-right: 5px; width: 160px" Class="button"
					        	onclick="location.href='<c:url value="/procesoAutByma.html"/>'"
					        	value="Proceso Automatico"/>	 	        
						    </li>
						</fieldset>
					</td>
					<td>
						<fieldset id="nameFile">
					    	<legend><fmt:message key="documentoRespaldatorio.procesamientoManualOPByma"/></legend>
						    <table>
						    	<tr>
							  	 	<td id=fileIn>
								    	<s:file name="file" label="%{getText('parseaTxtForm.file')}" cssClass="text file"   
								   			cssStyle="widht:420;" required="false"  onchange="ObtieneRuta(this);"/>
									</td>
						    	</tr>
						    	<tr>
							    	<td>
										<s:submit cssClass="button" id="uploadFile" method="importarOperaciones" value="Procesar Archivo" onclick="return fileIsValid();" cssStyle="width:160px;" theme="simple"/>

									</td>
						        </tr>	
					    	</table>
					    </fieldset>
					</td>				
				</table>
				
			</ria:collapse>*/
			++++Se Agrego
				/*<s:if test="%{operaciones != null}">			
					<ria:simplegrid id="procesoMFCaucionpGrid"
								collection="${operacionesAgrup}"  var="item"
								rowCountVar="rowCount" stateful="false" rowPerPage="${rowPerPage}"
								heightRow="25" height="250" width="850" resizable="true" 
								showPropertyButton="false" selectionMode="multiple" rowSelectable="true">
								<ria:simplegridcolumn id="fecha" label="Fecha Oper." width="100" object="${item.matOperacion.fecha}" style="text-align:center;" formatter="formatFecha"/>
								<ria:simplegridcolumn id="producto" label="Producto." width="120" object="${item.matOperacion.matProducto.nombre}" style="text-align:center;" />
								<ria:simplegridcolumn id="Condicion" label="Condicion." width="50" style="text-align:center; " >
									<c:choose>
										<c:when test="${item.matOperacion.condicion == 'C'}">
											 <label style='color: red;font-weight: bold';>${item.matOperacion.condicion}</label>
										 </c:when>
										 <c:otherwise>
										 	 <label style=''>${item.matOperacion.condicion}</label>
										  </c:otherwise>
				       				 </c:choose>
								</ria:simplegridcolumn>
								<ria:simplegridcolumn id="volumneOp" label="Vol Oper" width="80" object="${item.matOperacion.importeBruto}" style="text-align:right;" formatter="formatoImporteJS"/>
								<ria:simplegridcolumn id="Tasa" label="Tasa" width="80" object="${item.matOperacion.precio}" style="text-align:right;" formatter="formatoImporteJS"/>
								<ria:simplegridcolumn id="cuenta" label="Cuenta Cliente" width="300px" sortable="false"
						    	object="${(item.matOperacion.cuentaCliente!=null && item.liquida_a!=null && (item.matOperacion.mercado.id==-6 || item.matOperacion.mercado.id==-9))?
						    			  item.liquida_a.matOperacion.cuentaCliente.cuentaCliDescr:
						    	          item.matOperacion.cuentaCliente!=null?item.matOperacion.comitente.cuentaClienteDescr:item.matOperacion.comitente.clienteDescr}"/>		    
						    	<ria:simplegridcolumn id="comitente" label="Comitente" width="70px" style="text-align:left;" exportedString="@S"
						    		object="${item.matOperacion.rechazoComitente!=null? item.matOperacion.rechazoComitente : item.matOperacion.comitente.numero}" />
						    	<ria:simplegridcolumn label='DIVISION' object="${item.matOperacion.cuentaCliente.id}" width="60px" id="Cta. Comprador" style="text-align:center;" visible="false"/>
								<ria:gridinnerexport id="innerexport" filename="Comprobantes por periodo"/>
					</ria:simplegrid></br>
					<li class="buttonBar bottom">
							<input type="button" class="button" id="simularAutButton" value="Simular" onclick="javascript:onSimularAut();" style="width:160px;"/>
							<input type="button" class="button" id="facturarAutButton" value="Facturar" onclick="javascript:onFacturarAut();" style="width:160px; display: none;"/>
					</li>
				</s:if>
				</div>
				</br>	*/

		++++Se Agrego
			function onFacturarAut(){
				var grilla = SweetDevRia.$("procesoMFCaucionpGrid");
				var comprobantes = grilla.getCheckedRows();
				
				if(!confirm("Está seguro de los datos ingresados para facturar?"))
			 		return;
				
				if (comprobantes.length > 0) {
					new Ajax.Request('<c:url value="/simularComprobante.html"/>',
							  { parameters: {					  
									comprobantesSeleccionados: comprobantes.toString(),
									facturacion: 'true'
								  },
							    onComplete: function(transport, json) {
							    	var msg = getValidValue(json.msg);
									if(msg != null)	alert(msg);
							    	// Limpiamos la seleccion de la grilla
							    	grilla.checkedRows = [];
							    	// refrescamos la grilla para que recargue
							    	//SweetDevRia_asyncRefreshGrid('comprobantesMFCaucionGrid');
							    	displayElement("facturarAutButton", json.habilitarFacturacion);
							    }
							  });
				} else {
					alert("No hay comprobantes seleccionados");
				}
			}

			function onSimularAut() {
				var grilla = SweetDevRia.$("procesoMFCaucionpGrid");
				var comprobantes = grilla.getCheckedRows();
				
				if(!confirm("Está seguro de los datos ingresados para Simular?"))
			 		return;
				
				if (comprobantes.length > 0) {
					new Ajax.Request('<c:url value="/simularComprobante.html"/>',
							  { parameters: {					  
									comprobantesSeleccionados: comprobantes.toString(),
									facturacion: 'false'
								  },
							    onComplete: function(transport, json) {
							    	var msg = getValidValue(json.msg);
									if(msg != null)
										alert(msg);
							    	// Limpiamos la seleccion de la grilla
							    	grilla.checkedRows = [];
							    	// refrescamos la grilla para que recargue
							    	SweetDevRia_asyncRefreshGrid('comprobantesMFCaucionGrid');
							    	//SweetDevRia_asyncRefreshGrid('procesoMFCaucionpGrid');
							    	displayElement("facturarAutButton", json.habilitarFacturacion);				    	
							    }
							  });
				} else {
					alert("No hay comprobantes seleccionados");
				}
			}

		++++Se Agrego
			function ObtieneRuta(f){
			    if(f.files.length==0){
					AjaxFormValidator.addError($("matIngresaOperacionesBymaForm"), "file", 
							"<fmt:message key='documentoRespaldatorio.upload.error.nofile'/>");
					AjaxFormValidator.showErrors($("matIngresaOperacionesBymaForm"));
					fileValid = false;
			    } else {
					AjaxFormValidator.removeAllMessages();
				    fileValid = true;
			    }
			}

			function fileIsValid(){
				if(!fileValid)
					alert("Debe seleccionar un archivo para importar !!");
				return fileValid;
			}

			function formatFecha(sFecha){
			    if(sFecha.indexOf('/')==-1){
			    	var str = sFecha.split(' ');
			       	var mes = str[1];
			       	var mesStr;
			       	var fecha;
			       	switch (mes) {
				       case "Jan":
				       		mesStr = "01"; break;
				       case "Feb":
				       		mesStr = "02"; break;
				       case "Mar":
				       		mesStr = "03"; break;    
				       case "Apr":
				       		mesStr = "04"; break;    
				       case "May":
				       		mesStr = "05"; break;    
				       case "Jun":
				       		mesStr = "06"; break;
				       case "Jul":
				       		mesStr = "07"; break;
				       case "Aug":
				       		mesStr = "08"; break;    
				       case "Sep":
				       		mesStr = "09"; break;    
				       case "Oct":
				       		mesStr = "10"; break;
				       case "Nov":
				       		mesStr = "11"; break;    
				       case "Dec":
				       		mesStr = "12"; break;
				       default: System.out.println ("Mes Inexistente"); break;
					}
			       	fecha = str[2] + "/" + mesStr + "/" + str[5];
			    	return fecha;
			    }else{
			      	return sFecha;
			    }
			}

			function formatoImporteJS(pImporte){
				if(!pImporte || isNaN(pImporte.toString().toFloat()))
					pImporte='0';
				return pImporte.toString().toFloat().toFixed(2).toString();
			}

		****FacturacionMFCaucionAction.java
			Se realizaron varias modificaciones ver version

		***FacturacionMFCaucionManager.java  
				ResultadoSimulacion simularConcertacionAut(LogMessage lm, FacturaProducto datosFactura, List<MFCaucion> mfCaucionList, Map<String, Object>[] rowListGarantias)
				throws Exception;

		***FacturacionMFCaucionManagerImpl.java
			+++Se agrega metodo simularConcertacionAut()
			public ResultadoSimulacion simularConcertacionAut(LogMessage lm, FacturaProducto datosFactura, List<MFCaucion> mfCaucionList, Map<String, Object>[] rowListGarantias)
					throws Exception {
				List<FacturaProducto> comprobantes = new ArrayList<FacturaProducto>();
				lm.addLogMessage(String.format("Simulando Factura de Concertación de la Caución\n"));
				StringBuilder errores = new StringBuilder("");
				for (MFCaucion mfCaucion : mfCaucionList) {
					addFactura(lm, comprobantes, generarLiquidacion(lm, errores, datosFactura, mfCaucion, rowListGarantias));
				}		
				
				return new ResultadoSimulacion(comprobantes, errores.toString());
			}
			***Se modifico metodo facturarConcertacionGuardarFactura(), con validacion para cuando las garantias sean null
				if (rowListGarantias!=null){
					...
				}

		***MatOperacion.java
			++++Se agrego atributo 	private Double montoCausion; con su get y set.

		Incidencias_________________________________________________________________________________________________________
		***Se necesita que al facturar se listen los comprobantes de LX
		***Se agregan registros en caso de que un comitente tenga varias cuentas

		****mfCaucionForm.jsp  --> se modifica el metodo javascript onFacturarAut()
			SweetDevRia_asyncRefreshGrid('comprobantesMFCaucionGrid');
	    	displayElement("facturarAutButton", json.habilitarFacturacion);
	    	var url = '<c:url value="/listarComprobantesLote.html?nroLote='+ json.lote + '"/>';
			location.href = url;

		****FacturacionMFCaucionAction.java
			**Se crea nueva lista 
			private List<FacturaProducto> factP = new ArrayList<FacturaProducto>();

			**Se crea variable global numeroLote, con su get y set, para el envio de numero de lote en el json. 
				private Long numeroLote;	
				public Long getNumeroLote() {
					return numeroLote;
				}
				public void setNumeroLote(Long numeroLote) {
					this.numeroLote = numeroLote;
				}

			**Se crea metodo metodo getComitentep(), con parametro de cuentacliente para capturar comitente
				private Comitente getComitentep(Long nroComitente, Long mercadoId, Long cuenta){
					return (Comitente) manager.findFirst(Comitente.class, 
														   new String[]{"numero","mercado.id","fechaBaja", "cuentaCliente.id"}, 
														   new Object[]{nroComitente, mercadoId,  null, cuenta});
				}

			**Se modifica metodo facturar(), para que añada las facturas a la lista creada y se añade nroLote
				factP.add(factura);
				numeroLote = nroLote;

			**Se modifica el metodo commonOperacionesByma(), para que añada un registro si un comitente tiene maS DE UNA CuentaCliente
				List<Object> listaCuentas = getCuentaCliente(locOp.getComitente().getNumero(), mercado.getId());
				int totCuentas = listaCuentas.size();
				if (totCuentas > 1){
					for (Object object : listaCuentas) {
						MatOperacion locOpC = locOp.clone();
						CuentaCliente cc = (CuentaCliente)manager.findById(CuentaCliente.class, Long.parseLong(object.toString()));
						locOpC.setCuentaCliente(cc);
						Comitente comit = getComitentep(comitente, mercado.getId(), cc.getId());
						locOpC.setComitente(comit);
						String descComitente = cc.toString() + " - " + locOpC.getComitente().getClienteDescr();
						//locOpC.getComitente().setCuentaCliente(cc);
						locOpC.getComitente().setCuentaClienteDescr(descComitente);
						MatRegistro reg = new MatRegistro(accion, null, liquida_a, locOpC, nroBoleto, null);
						reg.setFilaArchivo(filaArchivo);
						reg.setNroBoletoCancela(boletoCancela);
						reg.setFechaAlta(locOp.getFechaAlta());
						reg.setUserAlta(locOp.getUserAlta());
						//reg.getMatOperacion().getComitente().setCuentaCliente(cc);
						//reg.getMatOperacion().getComitente().setCuentaClienteDescr(cc.getNroCuenta()+" - "+locOpC.getComitente().getClienteDescr());
						//operaciones.get(2).getMatOperacion().getComitente().getCuentaClienteDescr();
						operaciones.add(reg);
					}
				}else{
					MatRegistro reg = new MatRegistro(accion, null, liquida_a, locOp, nroBoleto, null);
					reg.setFilaArchivo(filaArchivo);
					reg.setNroBoletoCancela(boletoCancela);
					reg.setFechaAlta(locOp.getFechaAlta());
					reg.setUserAlta(locOp.getUserAlta());

					operaciones.add(reg);
				}

			**Se agrega metodo para la captura de CuentaCliente
				private List<Object> getCuentaCliente(Long nroComitente, Long mercadoId){
					String query= "select distinct(cuentacliente_id) " +
							"from comitente where  nro_comitente =" + nroComitente+
							" and mercado_id = "+mercadoId;
					List<Object> registros = (List<Object>)manager.executeSQL(query);
					return registros;
				}

			**Se modifica metodo simularComprobante(), para que al facturar made numero de lote, añada a comprobantes la 
			lista de facturas, se setea los comprobantes en el atributo de session
				hm.put("lote", numeroLote);
				this.comprobantes = factP;
				setComprobantesFacturacion(factP);


		***Incidencia------------------------------------------------------------------------------------
		private Comitente getComitentep(Long nroComitente, Long mercadoId){
				return (Comitente) manager.findFirst(Comitente.class, 
													   new String[]{"numero","mercado.id","fechaBaja"}, 
													   new Object[]{nroComitente, mercadoId,  null});
			}

		locOp.setCuentaCliente(locOp.getComitente().getCuentaCliente());


				Map<String, Object> params = new HashMap<String, Object>();
				params.put("mercadoId", mercadoId);
				String qry = "SELECT r.nroRegistro, r.matOperacion.comitente.numero, r.matOperacion.condicion, r.matOperacion.posicion" +
							 "  FROM MatRegistro r " +
							 " WHERE r.matOperacion.mercado.id = :mercadoId " +
							 "   AND r.state.workflowDefinition.estadoActual in ('F','T','P','O') ";	// Abierta (F)-Caratulada (T)-Pedido Ejercicio (P)-Oferta Entrega (O)

				List<Object[]> registros = (List<Object[]>)manager.executeQuery(false, qry, params);
				totRegistroMerc = registros.size();
***********************************************************************************************************************Por Implementacion incidencias




38.*********************************************************************************************************************
************************************************************************************************************************
***************************************************************************Ticket#1014439 — Boletos Moreno con garantia. 
	Usuario 	--> FUX JAVIER
	Ext			--> 5012
	Area		--> Mercaderías	Casa Central
	Descripcion:Buenas tardes
 
	Cuando el negocio es pago contra documentación ( cd y cg ) debe salir la fecha de pago
	 
	Hoy sale Contra Carta de Garantía y Certificado de Depósito. 
	 
	Debe salir: Contra Carta de Garantía y Certificado de Depósito el día 25/9/19
	 
	 
	Cto. 19/19547/2  --> 19/19689/0

	Documentacion___________________________________________________________________________________________________
	Condiciones:
	1.- Boleto fisico o confirma 
	2.- carta garantia comprador(cg) certificado de deposito(cd)
		"CartaGarantia", ddn.getTipoDocumentoNegocio().getFamilia() 
		"CertificadoDeDeposito", ddn.getTipoDocumentoNegocio().getFamilia()

	3.- debe ser de Heliginosa Moreno

	la fecha se debe sacr de fecha cierta

	Modificaciones_________________________________________________________________________________________________
		Se modifica DocumentoDeNegocioReportAction.java, el metodo makePagos()

		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		openCloseElement(hd, atts, "FechaCondicionPago", null, null, String.format("%s %s","Contra Carta de Garantía y Certificado de Deposito", ddn.getContrato().getVencParcFechaCierta() != null? " el dia "+formateador.format(ddn.getContrato().getVencParcFechaCierta()): "."));
***********************************************************************************************************************Implementado





39.*********************************************************************************************************************
************************************************************************************************************************
****************************************************************************************Ticket#1014511 — CUPOS ANULACION
	Usuario 	--> SALGADO ABIGAIL (D)	
	Ext			--> 5044	
	Area		--> Mesa de Operaciones	Casa Central
	Descripcion: 
 
	Cuando voy a cupos anulación, en la cuenta vendedor, me trae NULL… en la búsqueda de vendedores.
***********************************************************************************************************************cerrar con Exito





40.*********************************************************************************************************************
************************************************************************************************************************
************************************************************************Ticket#1014562 — Boletos de nuevos laboratorios. 
	Usuario 	--> FUX JAVIER
	Ext			--> 5012
	Area		--> Mercaderías	Casa Central
	Descripcion:
	Necesitamos incluir a estos nuevos laboratorios, para que genere los boletos/cartas ofertas como pago con especies:
	 
	Rotam de Argentina Agroquimica S.R.L
	Bioceres Semillas S.A
	Gentos S.A
	Nuseed S.A
	Sigma Agro SA
	Tecnomyl S.A.
	Timac Agro Argentina SA
	UPL Argentina
	Vetifarma S.A.
	 
	Ejemplo:
	 
	Tipo de operación = compra-venta con pago en especie.
	Fecha y condición de pago = con pago en especie.

	Documentacion___________________________________________________________________________________________________

	Nota: Para cambiar de carta Oferta a Boleto Confirma, en Administracion de Documentacion se debe anular, luego ir 
	a editar contrato con el lapiz en la misma pantalle, luego ir al arbol y destildar  Sin Boleto y Carta Oferta Comprador
	Comprador, luego se tilda Boleto Confirma.

	***Casos de pruebas:
		20/00108/8 --> Comprador Helm Argentina
			Sin Boleto, Carta Oferta Comprador
		19/19011/9 --> Comprador Yara Argentina SA
			Boleto Confirma

	****Items de cambio
		FechaCondicionPago
			<FechaCondicionPago>Con pago en especies</FechaCondicionPago>
				if (ddn.getContrato().getAFijar() && "BoletoConfirma".equals(ddn.getTipoDocumentoNegocio().getCodigo()) && 
						"RosBolCom".equals(ddn.getContrato().getBolsa().getCodigo()) && ddn.getContrato().getComprador().getNroCuenta() == 2024){
					openCloseElement(hd, atts, "FechaCondicionPago", null, null, "Contra Entrega");
				}else{
					if(("BoletoConfirma".equals(ddn.getTipoDocumentoNegocio().getCodigo()) || "BoletoFisico".equals(ddn.getTipoDocumentoNegocio().getCodigo())) 
							&& (!ddn.getContrato().getCertificadosDeDeposito().isEmpty() ) && (!ddn.getContrato().getCartasGarantia().isEmpty()) && (ddn.getContrato().getComprador().getNroCuenta() == 2024)) {
						SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
						openCloseElement(hd, atts, "FechaCondicionPago", null, null, String.format("%s %s","Contra Carta de Garantía y Certificado de Deposito", ddn.getContrato().getVencParcFechaCierta() != null? " el dia "+formateador.format(ddn.getContrato().getVencParcFechaCierta()): "."));
					}else{
						openCloseElement(hd, atts, "FechaCondicionPago", null, null, getFechaCondicionPago());
					}				
				}
				Condiciones:
				 	*
		TipoOperacion
			<TipoOperacion CodLista="2"/>
				if(ddn.getContrato().getEsCanje() !=null && ddn.getContrato().getComprador().getCliente().getLaboratorio() != null && ddn.getContrato().getEsCanje() == true && ddn.getContrato().getComprador().getCliente().getLaboratorio() == true){
					tipoOperacion = "2";
				}
	***Hallar Laboratorio
		ddn.getContrato().getComprador().getCliente().getLaboratorio()
	***Hallar Cuenta
		ddn.getContrato().getComprador()..getNroCuenta()

	***CuentasClientes de Casos de prueba
		48913	 	Vetifarma S.A.							1
		20203212	Tecnomyl S.A.							1
		180233290	Bioceres Semillas S.A					1
		156610189	Rotam de Argentina Agroquimica S.R.L	0
		183790037	Gentos S.A								0
		208126630	Gentos S.A								0
		197088438	Nuseed S.A								0
************************************************************************************************************************Implementado





41.*********************************************************************************************************************
************************************************************************************************************************
****************************************************************************Requerimiento CartaOferta Aljandro Antonelli
	N° de Carta de Porte
	Procedencia de la CCPP
	Provincia
	Fecha de Carta de porte
	N° de cto. Comprador Sursem
	Cuit vendedor
	Razon social vendedor.
	Domicilio fiscal vendedor
	Actividad Vendedor
	Comprador SURSEM
	Producto
	Cantidad aplicada
	N° de COE factura_producto con contrato
	Fecha del COE
	*Kilos del COE
	Precio de la TN
	Importe total del COE
	Cto. Vendedor Sursem
	Vendedor SURSEM
	CUIT Comprador
	Razon Social Comprador
	Producto 
	Actividad comprador
	N° de COE
	Fecha del COE
	Kilos del COE
	Precio de la TN
	Importe total del COE

	Scripts sql ejecutados se guardan en carpeta de trabajo
************************************************************************************************************************Implementado





42.*********************************************************************************************************************
************************************************************************************************************************
****************************************************************************Ticket#1014426 — RE: Consulta fraccion de cp
	Nota: yA REALICE LAS MODIFICACIONES EN EL JSP Y SE PASO A PRODUCCION
	Tampoco sales las observaciones y calidades Favor de ver urgente 
************************************************************************************************************************Implementado





43.*********************************************************************************************************************
************************************************************************************************************************
**********************************************************************Ticket#1013333 — COMISIÓN Y SELLADO- SYNGENTA S.A. 
	Usuario 	--> Juan Ignacio Dimitroff
	Ext			--> 5061
	Area		--> Mercaderías
	Descripcion:Por favor podremos generar un reporte que contenga la sig información de las Facturas de comisiones y sellados
	Esta información es necesaria para poder cobrar en tiempo y forma las facturas de comisiones.

	Documentacion___________________________________________________________________________________________________
		
		Caso de prueba:
		0101-00140212

		/*select tipocomprobante_id, fp.* from factura_producto fp where nrofactura = '0101-00140212'  --id -->194055830

		select * from comision where facturacomision_id = 194055830

		select * from contrato where id = 187689858 --187526285 --187689858

		select * from tipo_comprobante where abreviatura = 'FA'

		select * from tipo_comprobante where id = 148013

		select fp.totalhaber,tc.abreviatura,c.importe, c.facturacomision_id, c.contrato_id, con.numerocontrato, con.* 
		from factura_producto fp 
		inner join tipo_comprobante tc
		on fp.tipocomprobante_id = tc.id
		inner join comision c
		on fp.id = c.facturacomision_id
		inner join contrato con
		on con.id =  c.contrato_id
		where tc.id = 148013
		and fp.nrofactura = '0101-00140212'*/

	Modificaciones_________________________________________________________________________________________________
	***menu-config.xml
		/*<Menu name="FacturacionMenu" title="menu.facturacion" description="Facturación" roles="ROLE_USER,ROLE_FACTURACION" page="" width="65">            
			<Item name="ReporteComisiones" title="Reporte Comisiones Facturadas" page="/RepComisionesFacturadas.html" width="250" roles="ROLE_USER,ROLE_CONTADURIA,ROLE_IMPUESTOS"/>*/
	***ApplicationResources.properties
		# -- Reporte de Comisiones Facturadas
		rComFactList.title=Reporte Consulta de Comisiones Facturadas
		rComFactFilter.filtro.title = Filtro - Reportes de Comisiones Facturadas
		/*rComFact.heading = Reportes de Comisiones Facturadas*/
	***Se crea Action ConsultaComisionesFacturadasAction.java
	***Se crea Vista repConsComFacturadasList.java
	***Se crea Filtro repConsComFacturadasFiltro.java
************************************************************************************************************************Implementado





44.*********************************************************************************************************************
**************************************************************************************************************25/02/2019
*********************************************************************************Ticket#1013009 — Carga de Precios Delta 
	Usuario 	--> MEZA PABLO (D)	
	Ext			--> 5069	
	Area		--> Mercaderías	Casa Central
	Descripcion:
	Necesitamos implementar un control de variación de precios para los FCI.
	Cuando si ingrese un precio tanto en forma manual como por archivo con una variación en mas o en menos del 5 % respecto 
	del día anterior el precio no debe guardarse y el sistema debe mostrar un cartel de alerta.

	******Proceso Manual de edicion del precio de los productos.
		Documentacion___________________________________________________________________________________________________

		Se necesita eque en la carga manual (Facturas y Ociones --> Configuracion -->Mat Precio Ajuste --> new) y la forma 
		automatica (MercadoFinanciero --> Actualiacion de precios delta), se muestre una alerta y una confirmacion de la 
		carga, en caso de que el precio anterior del productos varie en mas de un 5%

		Modificaciones_________________________________________________________________________________________________
		*****MatPrecioAjusteAction.java, 

			***Se modifica metodo prepare para que si viene por metodo get, obtenga las variables 
			de ajuste y las setee al objeto matPrecioAjuste
					/**
				 * Grab the entity from the database before populating with request
				 * parameters
				 */
				public void prepare() {
					super.prepare();
					if (getRequest().getMethod().equalsIgnoreCase("post") || getRequest().getParameter("validado")!=null) {
						// prevent failures on new
						String mATPrecioAjusteId = getRequest().getParameter("matPrecioAjuste.id");
						if (mATPrecioAjusteId != null && !mATPrecioAjusteId.equals("")) {
							matPrecioAjuste = (MatPrecioAjuste) manager.get(MatPrecioAjuste.class,
									new Long(mATPrecioAjusteId));
							validateGrants(matPrecioAjuste);
						} else {
							matPrecioAjuste = new MatPrecioAjuste();
							//recuperarUltimoIngresado();
						}
						if (getRequest().getParameter("validado")!=null){
							String matProducto = getRequest().getParameter("matProducto");
							String fecha = getRequest().getParameter("fechaAjuste");
							String precioAjuste = getRequest().getParameter("precioAjuste");
							if (matProducto != null && !matProducto.equals("")){
								this.matProductoId = Long.parseLong(matProducto);
								
									try {
										matPrecioAjuste.setFecha(DateUtil.convertStringToDate(fecha));
									} catch (ParseException e) {
										e.printStackTrace();
										addActionError("Error en la fecha.");
									}
									matPrecioAjuste.setPrecioAjuste(Double.parseDouble( precioAjuste.replace(",",".")) );
							}
						}			
					}
				}

			***Se crea metodo obtenerPrecioAjusteAnt(), valida si el precio de ajuste es mayor al 5% respecto al precio 
			anterior para cada producto y envia el resultado a la vista
				@SkipValidation
				public String obtenerPrecioAjusteAnt() {
					Map<String, Object> hm = new HashMap<String, Object>();
					String producto = getRequest().getParameter("idProd");
					double precioActual = Double.parseDouble( getRequest().getParameter("precioAjuste").replace(",","."));
					matProductoId = Long.parseLong(producto);
					double  ajusteAnt = 0D;
					String sql = "select precioajuste " +
									"from mat_precio_ajuste " +
									"where matproducto_id ="+ producto +
									" and rownum < 2 order by fecha desc";
					Object precioAjuste = (Object) manager.executeSQL(sql);
					ajusteAnt = Double.parseDouble( precioAjuste.toString().replace("[","").replace("]", ""));		
					double variacion = ajusteAnt * 0.05;
					Double ajuste = Math.abs(( precioActual - ajusteAnt));
					if (ajuste > variacion){		
						hm.put("resultado", true);
						hm.put("variacion", ajusteAnt);	
					}else{
						hm.put("resultado", false);
					}
					
					JSONObject json = JSONObject.fromObject(hm);
					getResponse().setHeader("X-JSON", json.toString());
					
					return SUCCESS;
				}
		*****Se modifica matPrecioAjusteForm.jsp
			***Se blanquea action del formulario para que no se vaya por el post
				/*<s:form id="matPrecioAjusteForm" action="" method="post" validate="true">*/

			***Se modifica boton para que ejecute metodo javascript 
				/*<!--<s:submit cssClass="button"  key="button.save" theme="simple" onclick="return validar();"/>-->
		       <input type="button" class="button" id="simularButton" value="Simular" onclick="javascript:validar();" style="width:160px;"/> */
	        
	        ***Se modifica metodo javascript validar(), se agrega metodo request para validar ajuste
		       new Ajax.Request('<c:url value="/ajusteAnterior.html"/>',
				{
					parameters : {idProd : idMatProducto, precioAjuste: precio},
					onComplete : function(transport, json) {
						var result = getValidValue(json.resultado);
						var varia = getValidValue(json.variacion);
						if(result){
							if(confirm("El precio tiene una variacion mayor al 5% respecto al precio anterior ("+varia+").")){
								location.href='<c:url value="/saveMatPrecioAjuste.html"/>?validado=true&matProducto='+idMatProducto+'&fechaAjuste='+fecha+'&precioAjuste='+precio;	
								//return false;
							}
						}else{
							location.href='<c:url value="/saveMatPrecioAjuste.html"/>?validado=true&matProducto='+idMatProducto+'&fechaAjuste='+fecha+'&precioAjuste='+precio;	
						}					
					}
				});

		*****Se modifica struts-Mat.xml
			/*<action name="ajusteAnterior" class="com.zeni.app.webapp.action.MatPrecioAjusteAction"
				method="obtenerPrecioAjusteAnt">
				<result type="noOp"/>
			</action>*/

		Incidencias_________________________________________________________________________________________________________
			Nota:En el proceso manual se estan tomando en cuenta todos los productos para la validacion y se requiere unicamente 
			los fondos(FCI).


			***Se modifica MatPrecioAjusteAction, el metodo obtenerPrecioAjusteAnt(), para que filte por fondos unicamente para la validacion
			String sql1 = "select subfamilia from mat_producto where id = "+producto;
			Object validacion = (Object) manager.executeSQL(sql1);
			String subFamilia = String.valueOf(validacion.toString().replace("[","").replace("]", ""));
			if (subFamilia.equals("I")){
				String sql = "select precioajuste " +
						"from mat_precio_ajuste " +
						"where matproducto_id = "+ producto +
						" and fecha = (select max(fecha) " +
										"from mat_precio_ajuste " +
										"where matproducto_id = "+ producto +" and fechaBaja is null) " +
						" and fechaBaja is null";
		
		
				Object precioAjuste = (Object) manager.executeSQL(sql);
				ajusteAnt = Double.parseDouble( precioAjuste.toString().replace("[","").replace("]", ""));		
				double variacion = ajusteAnt * 0.05;
				Double ajuste = Math.abs(( precioActual - ajusteAnt));
				if (ajuste > variacion){		
					hm.put("resultado", true);
					hm.put("variacion", ajusteAnt);	
				}else{
					hm.put("resultado", false);
				}
			}else{
				hm.put("resultado", false);
			}


	******Proceso Automatico de edicion del precio de los productos.
	Documentacion___________________________________________________________________________________________________

		/*<action name="preciosDelta" class="com.zeni.app.webapp.action.PreciosDeltaAction" method="list">
            <result>/WEB-INF/pages/preciosDelta.jsp</result>
        </action>
		<action name="savePreciosDelta" class="com.zeni.app.webapp.action.PreciosDeltaAction" method="save">
		    <result name="success">/WEB-INF/pages/preciosDelta.jsp</result> 
            <result name="cancel">/WEB-INF/pages/preciosDelta.jsp</result>
        </action>*/

        	/*	<action name="savePreciosDelta" class="com.zeni.app.webapp.action.PreciosDeltaAction" method="save">
				    <result name="success">/WEB-INF/pages/preciosDelta.jsp</result> 
		            <result name="cancel">/WEB-INF/pages/preciosDelta.jsp</result>
		        </action>*/
    Modificaciones_________________________________________________________________________________________________
    *****PreciosDeltaAction.java
    	**Se añade atributo validacion con su get y set, que almacena el valor del campo hidden validacion del formulario.
    		private String validacion;
		
			public String getValidacion() {
				return validacion;
			}

			public void setValidacion(String validacion) {
				this.validacion = validacion;
			}

		**Se modifica metodo procesarArchivoDelta()
			*Se añade atributo que almacena atributo de la session para saber si valida o no.
				boolean valid = getRequest().getSession().getAttribute("validacion") != null ? 
				(Boolean) getRequest().getSession().getAttribute("validacion") : Boolean.parseBoolean(this.validacion);

			*Se añade atributo que almacena valor retornado por metodo ajusteCons()
				boolean ajusteConsulta = ajusteCons(pAj);

			*se añade validacion, para saber si es una validacion o no
				if (valid == true){
					if(!ajusteConsulta){
						preciosConEquiv.get(i).setMotivo("Variacion mayor al 5%.");
						preciosSinEquiv.add(preciosConEquiv.get(i));
					}
				}else{
					mpa.add(pAj);
				}

			*Se añade validacion, creacion y borrado de atributo de la session
				if (valid == false){
					if(mpa!=null && mpa.size()>0){
			    		muestraManager.saveList(mpa);
					}
			    	totalConEquiv = mpa.size(); 
			    	saveMessage(String.format("Se Actualizaron %s Precios correctamente.",totalConEquiv.toString()));
			    	getRequest().getSession().removeAttribute("validacion");
			   }else{
				   getRequest().getSession().setAttribute("validacion", false);
			   }

			*Se agrega metodo que valida si el precio tiene una variacion de mas del 5%
				public boolean ajusteCons(MatPrecioAjuste precio){
					double  ajusteAnt = 0D;
					String query = "select precioajuste " +
										"from mat_precio_ajuste " +
										"where matproducto_id = (select id from mat_producto where codigo = '"+precio.getMatProducto().getCodigo()+"') " +
										"and fecha = (select max(fecha) " +
														"from mat_precio_ajuste " +
														"where matproducto_id = (select id " +
																					"from mat_producto " +
																					"where codigo = '"+precio.getMatProducto().getCodigo()+"'))";          
					Object precioAjuste = (Object) muestraManager.executeSQL(query);
					ajusteAnt = Double.parseDouble( precioAjuste.toString().replace("[","").replace("]", ""));
					double variacion = ajusteAnt * 0.05;
					Double ajuste = Math.abs(( precio.getPrecioAjuste() - ajusteAnt));
					if (ajuste > variacion){
						return false;
					}else{
						return true;
					}
				}
	*****preciosDelta.jsp
		***Se añade hidden que almacena atributo session
			/*<input type="hidden" name="validacion" value="${sessionScope.validacion}" id="validacion" />*/

		***Se agrega boton para la validacion
			/*<input type="button" class="button" id="simularButton" value="Validar Precios" onclick="javascript:validar();" style="width:160px;"/>*/

		***Se crea metodo javascripts para la validacion de los precios del archivo
			function validar(){	 
				if(!fileValid){
					alert("Debe seleccionar un archivo para importar !!");
					return fileValid;
				}else{
					var tipoAlerta = document.getElementById("validacion");	
				    tipoAlerta.value = 'true';
				    displayElement("actualizar", true);	
				    displayElement("simularButton", false);	
				    var obj = document.getElementById("actualizar");    
				    if (obj){
				       obj.click();   
				    }
				}	
			}

	Incidencias_________________________________________________________________________________________________________
		Se modifica PreciosDeltaAction.java, el metodo ajusteCons(MatPrecioAjuste precio), se genera error al encontrar 
		mas de un registro en el query, se modifica para el query para no traer registros que esten de baja
			String query = "select precioajuste " +
							"from mat_precio_ajuste " +
							"where matproducto_id = (select id from mat_producto where codigo = '"+precio.getMatProducto().getCodigo()+"' and fechabaja is null) " +
							"and fecha = (select max(fecha) " +
											"from mat_precio_ajuste " +
											"where matproducto_id = (select id " +
																		"from mat_producto " +
																		"where codigo = '"+precio.getMatProducto().getCodigo()+"' and fechabaja is null) and fechabaja is null)";   
************************************************************************************************************************Por Implementacion





45.*********************************************************************************************************************
**************************************************************************************************************04/01/2019
*****************************************************************************Ticket#1012593 — Cuentas de clientes en U$S
	Usuario 	--> RODRIGUEZ AUGUSTO		
	Ext			--> 5146	
	Area		--> Contaduría	Casa Central	
	Descripcion:
	Necesitamos que los movimientos en U$S de todos los clientes excepto la las cuentas 91 (que tiene que ir a la cuenta contable 
	115005) y las 397 (que tiene que ir a la cuenta contable 115009) el resto tienen que ir a la cuenta contable 115008.

	Documentacion___________________________________________________________________________________________________

		Nota: Los asientos que se estan generando en la cuenta incorrecta(115005) son los asientos manuales, cuenta correcta
		115008

		/*<action name="ingresoAsientos" class="com.zeni.app.webapp.action.IngresoAsientosAction" method="list">
            <result>/WEB-INF/pages/ingresoManualAsientosList.jsp</result>
        </action>*/

        /*<action name="editAsientos" class="com.zeni.app.webapp.action.IngresoAsientosAction" method="edit">
            <result>/WEB-INF/pages/ingresoManualAsientosForm.jsp</result>
            <result name="error" type="redirectAction">ingresoAsientos</result>
        </action>*/

        ***Action --> IngresoAsientosAction.java  --> metodo save()
        	Integer nuevoAsiento = asientoContableManager.asientoAD(
					locAD, getCuentaCliente(), 
					locAD.getImporte() * (tipoMovCta.equals('C')? -1d : 1d),
					recuperarImputaciones(),
					asientoContable.getDescripcion(), fechaValor);
	        	***asientoContableManagerImpl.java  --> asientoAD()
	        		pushAsiento(nuevoAsiento, auxAsiento.clone(), 
					locCuenta != null ? locCuenta : getBuscadorDeCuentas().findCuentaContable(AppConstants.C_CONTABLE_DEUD_POR_SERV, true),
					pCtaCliente.getNroCuenta() + " - " + pCtaCliente.getDenominacionCuenta() , pImporte);
        ***Vista  --> ingresoManualAsientosForm.jsp

	    ***Cuentas contables mapeadas o asociadas
	        select * from sys_config where id like 'nroCtaCliente:nroCtaContable'
        ***AppConstants.java
        public static final String C_CONTABLE_DEUD_POR_SERV = "DEUD_POR_SERV";
		public static final String C_CONTABLE_DEUD_POR_SERV_US = "DEUD_POR_SERV_US";

	Modificaciones_________________________________________________________________________________________________
	******asientoContableManagerImpl.java  --> asientoAD()
		if(auxAsiento.getMoneda().getId() == 1718){
			pushAsiento(nuevoAsiento, auxAsiento.clone(), 
					locCuenta != null ? locCuenta : getBuscadorDeCuentas().findCuentaContable(AppConstants.C_CONTABLE_DEUD_POR_SERV_US, true),
					pCtaCliente.getNroCuenta() + " - " + pCtaCliente.getDenominacionCuenta() , pImporte);
		}else{
			pushAsiento(nuevoAsiento, auxAsiento.clone(), 
					locCuenta != null ? locCuenta : getBuscadorDeCuentas().findCuentaContable(AppConstants.C_CONTABLE_DEUD_POR_SERV, true),
					pCtaCliente.getNroCuenta() + " - " + pCtaCliente.getDenominacionCuenta() , pImporte);
		}
************************************************************************************************************************Implementado





46.*********************************************************************************************************************
**************************************************************************************************************24/10/2019
******************************************************************Ticket#1014664 — Incidencias para mercado de capitales 
	Usuario 	--> FACUNDO IBAÑEZ
	Ext			--> 5017
	Area		--> IM - Nave de Producción
	Descripcion:

	1.-Archivo diario a CNV, se emite diariamente. El archivo actualmente no está levantando el precio correcto de las operaciones, además no levanta las cauciones.

	2.-Precios Delta: Es un proceso automatico y diario. Hay tres productos que no carga

	Documentacion___________________________________________________________________________________________________

	2.- El problema se presenta cuando el producto no esta en la tabla de equivalencias o tiene mal cargado el producto en esta tabla

		select * from equivalencia_prod_fci p where upper(p.descripcion) like upper('DELTA RENTA DÓLARES PLUS DECRETO N° 596/2019')
		and socgerente = 'DELTA' and upper(p.clase)= 'T';

		select * from mat_producto where codigo = '15695';

		select * from mat_producto where nombre like ('DELTA%');

		update equivalencia_prod_fci p set p.codigodelta = '15695' 
		where upper(p.descripcion) like upper('DELTA RENTA DÓLARES PLUS DECRETO N° 596/2019')
		and socgerente = 'DELTA' and upper(p.clase)= 'T';

		select * from mat_precio_ajuste  order by fechaalta desc

		delete mat_precio_ajuste where useralta ='eareiza'

	1.- Problema A: La tabla no esta levantando el precio correcto de las operaciones.
		Nota: el precio esta bien, pero esta tomando solo dos decimales, el usuario desea cuatro decimales.

		Problema B: No esta levantando las operaciones de cauciones

	Modificaciones_________________________________________________________________________________________________
		ConsultaOperClientesAction.java
			Se modifica el metodo repConsultaPactados(), se modifica la consulta sql para que traiga las cauciones
				String consulta ="select C.razonsocial, " +
						"C.cuit, " +
						"(SELECT mp.nombre FROM mat_producto mp  WHERE mp.id = matO.matproducto_id) , " +
						"matO.horasaliquidar as Plazo,"+
						"matO.id, " +
						"matO.cuentacliente_id, " +
						"(select nombre from moneda where id = (select moneda_id from mat_Operacion where id = matO.id)), " +
						"case when matO.condicion = 'C' then 'Compra' " +
						"      when matO.condicion = 'V' then 'Venta' " +
						"end, " +
						"matO.volumen, " +
						"matO.preciooprima " +
						"from mat_operacion matO " +
						"inner join cuenta_cliente CC " +
						"on matO.cuentacliente_id = CC.id " +
						"inner join cliente C " +
						"on C.Id = CC.CLIENTE_ID "+
						"WHERE matO.fecha >= to_date('"
						+ DateUtil.getDate(fechaDesde)
						+ "','dd/MM/yyyy') "
						+ "AND matO.fecha <= to_date('"
						+ DateUtil.getDate(fechaHasta)
						+ "','dd/MM/yyyy') "+
						"AND matO.condicion IN ('C','V') " +
						"AND matO.tipo in ('L','A')"+stringCuit;
		repConsOperClientesList.jsp
			***Se modifica columna para que formatee los decimales a solo dos decimales
				/*<ria:simplegridcolumn label='MONTO' object="${item.monto}" width="100" id="MONTO" style="text-align:center;paddind:10px;" formatter="formatoImporteJS1"/> */
			***Se crea metodo javaScript para formatear los decimales
				function formatoImporteJS1(pImporte){
					if(!pImporte || isNaN(pImporte.toString().toFloat()))
						pImporte='0';
					return pImporte.toString().toFloat().toFixed(2).toString();
				}	

		****Punto B________________________________________________________________________________________________
			Modificaciones_____________________________________________________________________________________
			***Se crea metodo opeCauciones() en la clase ConsultaOperClientesAction.java
				@SuppressWarnings("unchecked")
				private void opeCauciones(int id, Date fechaDesde, Date fechaHasta, String cuit){
					List<Object[]> lista = null;
					BigDecimal bd ;
				    //bd = bd.setScale(2, RoundingMode.HALF_UP);
					String stringCuit = !(cuit == null) ? (!(cuit.equals("")) ? " AND cuentaclie2_.CUIT = '"
							+ cuit + "'" : " ") : " ";
					if (!(fechaDesde == null) && !(fechaHasta == null)) {
						try {
							String consulta ="select cuentaclie2_.razonSocial as y1_, " +
													"cuentaclie2_.cuit as y2_, " +
													"trunc(this_.fechaVencimiento, 'dd') - trunc(this_.fechaAlta, 'dd') as y3_, " +
													"moneda3_.nombre as y4_, " +
													//"this_.condicion as y5_, " +
													"case when this_.condicion = 'T' then 'Tomadora' " +
													"      when this_.condicion = 'C' then 'Colocadora' " +
													"end as y5_, " +
													"this_.monto as y6_, " +
													"this_.tasa as y7_, " +
													"this_.monto + (this_.monto * (trunc(this_.fechaVencimiento, 'dd') - trunc(this_.fechaAlta,'dd')) * this_.tasa / 36500) as y8_  " +
											"from mf_caucion this_ " +
											"left outer join cuenta_cliente cuentaclie1_ " +
											"	on this_.cuentaCliente_id=cuentaclie1_.id " +
											"left outer join cliente cuentaclie2_ " +
											"   on cuentaclie1_.cliente_id=cuentaclie2_.id " +
											"left outer join moneda moneda3_ " +
											"	on this_.moneda_id=moneda3_.id " +
											"where this_.fechaBaja is null "+
											"and this_.fechaVencimiento >= to_date('"+ DateUtil.getDate(fechaDesde) +"','dd/MM/yyyy') "+
											"and this_.fechaVencimiento <= to_date('"+ DateUtil.getDate(fechaHasta) +"','dd/MM/yyyy') "+stringCuit+"" +
											"order by y1_ asc";
											
							lista = (List<Object[]>) contratoManager.executeSQL(consulta);
							if (lista != null && lista.size() > 0) {
								for (Object item : lista) {
									Object[] objectItem = (Object[]) item;
									OperacionesClientes opeClie;
									id++;
									String cliente = ((objectItem[0] != null) ? objectItem[0].toString() : null);
									String cuitString = ((objectItem[1] != null) ? objectItem[1].toString() : null);
									String producto = "Caucion";
									int plazo = ((objectItem[2] != null) ? Integer.parseInt(objectItem[2].toString()) : 0);
									String moneda = ((objectItem[3] != null) ? objectItem[3].toString() : null);
									String condicion = ((objectItem[4] != null) ? objectItem[4].toString() : null);
									long operacion = 0;
									long cuentaCliente = 0;
									BigDecimal montoOper = new BigDecimal((objectItem[5] != null) ? objectItem[5].toString() : "0");
									double precio = ((objectItem[6] != null) ? Double.parseDouble(objectItem[6].toString()) : 0);
									double montoTotal = ((objectItem[7] != null) ? Double.parseDouble(objectItem[7].toString()) : 0);
									montoOper=montoOper.setScale(2, RoundingMode.HALF_UP);
									opeClie = new OperacionesClientes(id, cliente, cuitString, producto, plazo, operacion, cuentaCliente, moneda, condicion, montoOper, precio, montoTotal);
									
									listaConsultaOC.add(opeClie);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				***Se modifica la  claseOBJ OperacionesClientes
					***Se crea nuevo atributo con su get y set
						private BigDecimal montoOper;
					***Se crea nuevo constructor 
						public OperacionesClientes(int id, String cliente, String cuit,
								String especies, int plazo, long idOperacion, long ctaCliente,
								String moneda, String condicion, BigDecimal montoOper, double precio, double monto) {
							super();
							this.id = id;
							this.cliente = cliente;
							this.cuit = cuit;
							this.especies = especies;
							this.plazo = plazo;
							this.idOperacion = idOperacion;
							this.ctaCliente = ctaCliente;
							this.moneda = moneda;
							this.condicion = condicion;
							this.montoOper = montoOper;
							this.precio = precio;
							this.monto = monto;
						}	
			***Se modifica la vista para que muestre de forma correcta las cauciones
				/*<ria:simplegrid id="repoGrid"
							collection="${listaConsultaOC}"
							rowPerPage="${rowPerPage}"
							width="860px" height="400px"
							var="item"
							rowCountVar="rowCount"
							resizable="true"
							showPropertyButton="true"
							selectionMode="single"
							onSetData="gridNotifyOnEmpty(this, __Messages__.gridNoData)" 
							oldQueryMethod="false">
			    <ria:simplegridcolumn label='CLIENTE' object="${item.cliente}" width="250" id="CLIENTE" style="text-align:center; paddind:10px"/>
			    <ria:simplegridcolumn label='CUIL/CUIT' object="${item.cuit}" width="150" id="CUIL/CUIT" style="text-align:center;paddind:10px;"/>
			    <ria:simplegridcolumn label='ESPECIES' width="200" id="ESPECIES" style="text-align:center;paddind:10px;" >
			 		<c:choose>
						<c:when test="${item.condicion == 'Compra' || item.condicion == 'Venta'}">
							<label>${item.especies}</label>
						</c:when>
						<c:otherwise>
						 	<label style='color: blue;font-weight: bold'>${item.especies}</label>
						</c:otherwise>
					</c:choose>
				</ria:simplegridcolumn>  
			    <ria:simplegridcolumn label='PLAZO' object="${item.plazo == 0 ? 'Contado Inmediato' : item.plazo}" width="130" id="PLAZO" style="text-align:center;paddind:10px;"/>
			   	<ria:simplegridcolumn label='MONEDA' object="${item.moneda}" width="150" id="MONEDA" style="text-align:center;paddind:10px;"/>
			   	<ria:simplegridcolumn label='C/V' object="${item.condicion}" width="150" id="CONDICION" style="text-align:center;paddind:10px;"/>
			   	<ria:simplegridcolumn label='CANTIDAD' width="100" id="CANTIDAD" style="text-align:center;paddind:10px;">
			   		<c:choose>
			   			<c:when test="${item.condicion == 'Compra' || item.condicion == 'Venta'}">
			   				<label style='font-weight: bold';>${item.cantidad}</label>
						</c:when>
						<c:otherwise>
						 	<label style='color: blue;font-weight: bold'>${item.montoOper}</label>
						</c:otherwise>
			   		</c:choose>
			   	</ria:simplegridcolumn>  
			   	<ria:simplegridcolumn label='PRECIO' object="${item.precio}" width="100" id="PRECIO" style="text-align:center;paddind:10px;" formatter="formatoImporteJS"/> 	
				<ria:simplegridcolumn label='MONTO' object="${item.monto}" width="100" id="MONTO" style="text-align:center;paddind:10px;" formatter="formatoImporteJS1"/> 
				<ria:gridinnerexport id="innerexport" filename="Contratos Pactados"/>
			</ria:simplegrid>*/
************************************************************************************************************************En incidencias



47.*********************************************************************************************************************
**************************************************************************************************************19/09/2019
*******************************************************************************Ticket#1014429 — cto. nietos ( urgentes ) 
	Usuario 	--> FUX JAVIER
	Ext			--> 5012
	Area		--> Mercaderías	Casa Central
	Descripcion: 
	Necesitamos que se vuelva atrás el cambio de cto. nietos en el sistema cuyo comprador sea Syngenta, y que genere para 
	todos la proforma estándar de boleto o carta oferta.
	Que a su vez incorporen un ítem para marcar si es crescere, y ahí si genere el boleto especial.
	Tenemos varios ctos. cargados como nieto que no son crescere que genera el boleto especial….(porque el comprador es Syngenta)  
	Y nos complica la generación.

	Nota: Se valido con el usuario funcional Javier Fux, solicito que no se genere mas el boleto especial para contrato nieto.

	Documentacion___________________________________________________________________________________________________
		***Contrato.java
			**getImprimirDdn(DocumentoDeNegocio ddn)

		Contratos de ejemplo
			19203756/20033035

	Modificaciones_________________________________________________________________________________________________
		***Se modifica el metodo getImprimirDdn(DocumentoDeNegocio ddn),se comenta codigo  para que no genere boleto espcial de contrato nieto

			/*Por solicitud del usuario, ya no se geeran los boletos especiales de contrato nieto Req 1014429*/
			else if (StringUtils.equals("Boleto", ddn.getTipoDocumentoNegocio().getFamilia()) /*&& ((ddn.getContrato().getEsNieto()==null || !ddn.getContrato().getEsNieto()) || validarNieto(ddn))*/) {
			
			//if((ddn.getContrato().getEsNieto()==null || !ddn.getContrato().getEsNieto()) || validarNieto(ddn)){

			/*}else{//es nieto
            	sb.append(String.format("<td class='docPrint'><a href='generarBoletoContratoNieto.html?id=%s&format=PDF'>PDF</a></td>", this.getId()));
            	sb.append(String.format("<td class='docPrint'><a href='generarBoletoContratoNieto.html?id=%s&format=RTF'>RTF</a></td>", this.getId()));
            }*/
************************************************************************************************************************Implementado




48.*********************************************************************************************************************
************************************************************************************************************************
**********************************************************************************Requerimiento Consulta Factura Credito
	Se requiere hacer una consulta para el usuario, de las opciones de comprobantes y cta ctes, ubicadas en la opcion 
	Facturacion --> Consulta Factura Credito en AFIP 

	Documentacion__________________________________________________________________________________________________
		***ConsultaFacturaCreditoAction.jsp
		***AfipWSFECredClient.java

		/*<Item name="ConsultaFacturaCreditoMenu" title="Consulta Factura de Credito en AFIP" page="/consultaFacturaCreditoEnAfip.html" width="250"/>

			<action name="consultaFacturaCreditoEnAfip" class="com.zeni.app.webapp.action.ConsultaFacturaCreditoAction" method="list">
				<interceptor-ref name="jsonValidationWorkflowStack" />
				<result>/WEB-INF/pages/consultaFacturaCreditoForm.jsp</result>
			</action>

				<td>
		    		<input type="button" value="Comprobantes" class="button" style="width:160px;" onclick="javascript:consultaComprobantes()">
		    	</td>
		    	<td>
		    		<input type="button" value="Ctas. Ctes." class="button" style="width:180px;" onclick="javascript:consultaCtasCtes()">
		    	</td>
		    	<td>
		    		<input type="button" value="Cta. Cte." class="button" style="width:160px;" onclick="javascript:consultaCtaCte()">
		    	</td>
		    	<td>*/

		    	popupWin.define("popupConsultarFacturaCredito","Consulta","consultaComprobantes.html?popUp=popupConsultarFacturaCredito&decorator=popup",-1,-1,800,620);
			    function consultaComprobantes() {
					var url = "consultaComprobantes.html?popUp=popupConsultarFacturaCredito&decorator=popup";
			        popupWin.openUrl('popupConsultarFacturaCredito',url);
				}

					<action name="consultaComprobantes" class="com.zeni.app.webapp.action.ConsultaFacturaCreditoAction" method="consultaFECredComprobantes">
						<result>/WEB-INF/pages/consultaFacturaCreditoDatos.jsp</result>
					</action>
			    
			    popupWin.define("popupConsultarFacturaCredito","Consulta","consultaCtasCtes.html?popUp=popupConsultarFacturaCredito&decorator=popup",-1,-1,1000,620);
			    function consultaCtasCtes() {
					var url = "consultaCtasCtes.html?popUp=popupConsultarFacturaCredito&decorator=popup";
			        popupWin.openUrl('popupConsultarFacturaCredito',url);
				}
					<action name="consultaCtaCte" class="com.zeni.app.webapp.action.ConsultaFacturaCreditoAction" method="consultaFECredCtaCte">
						<result>/WEB-INF/pages/consultaFacturaCreditoDatos.jsp</result>
					</action>
			    
			    popupWin.define("popupConsultarFacturaCredito","Consulta","consultaCtaCte.html?popUp=popupConsultarFacturaCredito&decorator=popup",-1,-1,800,620);
			    function consultaCtaCte() {
					var url = "consultaCtaCte.html?popUp=popupConsultarFacturaCredito&decorator=popup";
			        popupWin.openUrl('popupConsultarFacturaCredito',url);
				}

	Modificaciones_________________________________________________________________________________________________
	***struts-Facturacion.xml
		/*<action name="consultaComprobantes" class="com.zeni.app.webapp.action.ConsultaFacturaCreditoAction" method="consultaFECredComprobantes">
			<result>/WEB-INF/pages/consultaComprobantesAfip.jsp</result>
		</action>
		<action name="consultaCtasCtes" class="com.zeni.app.webapp.action.ConsultaFacturaCreditoAction" method="consultaFECredCtasCtes">
			<result>/WEB-INF/pages/consultaCtaCtesAfip.jsp</result>
		</action>*/

	***ConsultaFacturaCreditoAction.java
		**Se crean atributos de forma global para ser compartidos con la vista
			private List<ComprobanteType> listaFacturas = new ArrayList<ComprobanteType>();	
			private List<InfoCtaCteType> listaCtaCtes = new ArrayList<InfoCtaCteType>();	
			
			private String nroComprobante;

		**Se modofica metodo consultaFECredComprobantes(), para que añada a la lista los objetos rescatados del ws
			listaFacturas.add(r[i]);

		**se modifica el metodo consultaFECredCtasCtes(), para que añada a la lista los objetos rescatados del ws y 
		segun el caso pueda resolver la consulta de un ctacte especifica
			if( nroComprobante == null || nroComprobante.equals(""))	{
					listaCtaCtes.add(r[i]);
				}else{
					if(r[i].getCodCtaCte() == Long.parseLong(nroComprobante)){
						listaCtaCtes.add(r[i]);
					}
				}
			}
	***Se crea consultaCtaCtesAfip.jsp
	***Se crea consultaCtaCtesFiltro.jsp
	***Se crea consultaComprobantesAfip.jsp
************************************************************************************************************************Implementado



49.*********************************************************************************************************************
************************************************************************************************************************
**************************************************************************************Requerimiento Impresion de cheques
	Se necesita cuadrar la impresion de cheques y cheques diferidos

	Documentacion___________________________________________________________________________________________________
		/*<!-- Cheques para Orden de Pago START -->
		<action name="ingresarChequesOP" class="com.zeni.app.webapp.action.OrdenDePagoChequesAction" method="list">
			<result>/WEB-INF/pages/ordenDePagoChequesForm.jsp</result>
		</action>
		<action name="saveChequesOP" class="com.zeni.app.webapp.action.OrdenDePagoChequesAction" method="save">
		<interceptor-ref name="jsonValidationWorkflowStack" />
			<result>/WEB-INF/pages/ordenDePagoChequesForm.jsp</result>
			<result name="input">/WEB-INF/pages/ordenDePagoChequesForm.jsp</result>
		</action>
		*/

		public static void sendChequeHSBCToPrinter(List<Map<String, RelCtaContOP>> chequesAImprimir, String impresora, Map<String, Object> reportParams) throws Exception {
			byte[] output = JasperReportUtil.getReport(ServletActionContext.getRequest(),
					"/reports/PedidoDeFondo/chequeHSBC.jasper",
					JasperReportUtil.FORMAT_PDF, reportParams, null,
					null, null, null,
					chequesAImprimir, "JRMapCollectionDataSource");
			print(new ByteArrayInputStream(output), impresora, 1);
		}

		public static void sendChequeDiferidoHSBCToPrinter(List<Map<String, RelCtaContCheque>> chequesAImprimir, String impresora, Map<String, Object> reportParams) throws Exception {
			byte[] output = JasperReportUtil.getReport(ServletActionContext.getRequest(),
					"/reports/PedidoDeFondo/chequeHSBC_Diferido.jasper",
					JasperReportUtil.FORMAT_PDF, reportParams, null,
					null, null, null,
					chequesAImprimir, "JRMapCollectionDataSource");
			print(new ByteArrayInputStream(output), impresora, 1);
		}

	Modificaciones_________________________________________________________________________________________________
************************************************************************************************************************Implementado




50.*********************************************************************************************************************
************************************************************************************************************************
***********************************************************************************************SOLICITUD DE CAMBIO Nº 45
	Se requieren modificaciones en la factura electronica, se agrega una leyenda segun validacion de codigo afip, se añade 
	monto en leta a la factura

	Documentacion___________________________________________________________________________________________________
		***facturaProducto2.jrxml
			Se crea leyenda en un objeto label del reporte, se valida que la leyenda aparezca segun codigo afip
				La presente se considerará aceptada si en el plazo de 30 días de
				recibida la misma en el DFE no se hubiera registrado su rechazo o
				aceptación, y que si, al vencimiento del mencionado plazo no se
				hubiera registrado su cancelación, se considerará que esta
				constituye título ejecutivo en los términos del Art. 523 del Código
				Procesal Civil y Comercial de la Nación y concordantes.
				Su aceptación implica la plena conformidad para la transferencia de la
				información contenida en el documento a terceros en caso de optarse
				por su cesión, transmisión o negociación. <BR><BR>
************************************************************************************************************************Implementado




51.*********************************************************************************************************************
************************************************************************************************************************
*********************************************************************************************************************YPF
	referecia matRegistroStateAction.java
	***matRegistroList.jsp
		/*<td valign="bottom">
	  		<input type="button" class="button"	onclick="exportarArchivoLiqFinal();" value="Archivo Liquidación Final" style="width: 200px;" />
	  	</td>*/

	  	function exportarArchivoLiqFinal() {
			var grilla = SweetDevRia.$('matRegistrosGrid');
			var registros = grilla.getCheckedRows();
			if (registros.length < 1){
				alert("Debe seleccionar al menos un Registro");
			} else {
				var registros = grilla.getCheckedRows();
				location.href='<c:url value="/matRegistroExportarArchivoLiqFinal.html?ids='+registros+'"/>';
			}
		}

	***struts-Mat.xml
		/*<action name="matRegistroExportarArchivoLiqFinal" class="com.zeni.app.webapp.action.MatRegistroStateAction"
			method="generarArchivoLiqFinal">
			<result name="success" type="noOp"/>
			<result name="error" type="noOp"/>
		</action>*/


	***MatRegistroStateAction.java
	private void setArchivosConcertacionInicial(ZipOutputStream zip, List<MatRegistro> matRegistros) throws IOException, Exception {
	
	CartaPorte___________________________________________________________________________________________________
		•	Las columnas Destino (Centro),  Fecha Descarga SIEMPRE deben tener formato TEXTO. El resto de las columnas, formato GENERAL
		•	Columna CUIT del Remitente Comercial: cuando el CUIT sea distinto a YPF, entonces este CUIT debe ser igual al CUIT informado en la columna Cuit del Proveedor (Proveedor Granos)
		•	Columna CUIT del Intermediario: cuando el CUIT sea distinto a YPF, entonces este CUIT debe ser igual al CUIT informado en la columna Cuit del Proveedor (Proveedor Granos)
		•	Columna CUIT del Destinatario: cuando el grano sea SOJA, siempre deberá ser el CUIT de YPF
		•	Columnas Patente Camión, Patente Acoplado: no deben existir espacios en blanco entre las letras y números

		***fijar campos en adicionales de el modulo de entregas, entregas adicionales.
			PATENTECHASIS      --> Patente Camión	
			PATENTE_CHASIS     --> Patente Acoplado



		Documentacion___________________________________________________________________________________________

			<action name="editEntregaContrato" class="com.zeni.app.webapp.action.EntregaAction" method="edit">
	            <result>/WEB-INF/pages/editEntrega.jsp</result>
	        </action>

	        var url = "editEntregaDatAdicionales.html?popUp=popupDatosAdicionales&decorator=popup";

	       /*<action name="editEntregaDatAdicionales" class="com.zeni.app.webapp.action.EntDatAdicionalesAction" method="edit">
				<result name="input">/WEB-INF/pages/entregaDatosAdicionalesForm.jsp</result>
	            <result>/WEB-INF/pages/entregaDatosAdicionalesForm.jsp</result>
	        </action>*/

	    Modificaciones__________________________________________________________________________________________
		    *** Se modifica entregaDatosAdicionalesForm.jsp
			    	/*<td>
						<s:textfield label ="Patente Camión" key="entregaDatosAdicionales.patenteChasis" required="false" maxlength="50" cssClass="text medium"/>
					</td>
					<td>
						<s:textfield label ="Patente Acoplado" key="entregaDatosAdicionales.patente_Acoplado" required="false" maxlength="50" cssClass="text medium"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:textfield label ="Nombre Transportista" key="entregaDatosAdicionales.nombreTransportista" required="true" maxlength="50" cssClass="text medium"/>
					</td>
					<td>
						<s:textfield label ="CUIT Transportista" key="entregaDatosAdicionales.cuitTransportista" required="true" maxlength="50" cssClass="text medium"/>
					</td>
					<td>
						<s:textfield label ="Nombre Chofer" key="entregaDatosAdicionales.nombreChofer" required="true" maxlength="50" cssClass="text medium"/>
					</td>
					<td>
						<s:textfield label ="CUIT Chofer" key="entregaDatosAdicionales.cuitChofer" required="true" maxlength="50" cssClass="text medium"/>
					</td>*/

			*** Se modifica EntregaDatosAdicioneles.java
				Se crean nuevos atributos con su get y set
				private String patente_Acoplado;	//Añadido para cliente YPF
				private String nombreTransportista;	//Añadido para cliente YPF
				private String cuitTransportista;	//Añadido para cliente YPF
				private String nombreChofer;		//Añadido para cliente YPF
				private String cuitChofer;			//Añadido para cliente YPF

				@Column
				public String getPatente_Acoplado() {
					return patente_Acoplado;
				}

				public void setPatente_Acoplado(String patente_Acoplado) {
					this.patente_Acoplado = patente_Acoplado;
				}
				
				@Column
				public String getNombreTransportista() {
					return nombreTransportista;
				}

				
				public void setNombreTransportista(String nombreTransportista) {
					this.nombreTransportista = nombreTransportista;
				}

				@Column
				public String getCuitTransportista() {
					return cuitTransportista;
				}


				public void setCuitTransportista(String cuitTransportista) {
					this.cuitTransportista = cuitTransportista;
				}

				@Column
				public String getNombreChofer() {
					return nombreChofer;
				}


				public void setNombreChofer(String nombreChofer) {
					this.nombreChofer = nombreChofer;
				}

				@Column
				public String getCuitChofer() {
					return cuitChofer;
				}


				public void setCuitChofer(String cuitChofer) {
					this.cuitChofer = cuitChofer;
				}

			*** Se modifica Base de Datos (Se debe realizar la modificacion en Base de Datos de Produccion)
				**Se ñaden campos a la tabala entrega_datos_adicionales
					alter table entrega_datos_adicionales
		    			add PATENTE_ACOPLADO varchar(255)

					alter table entrega_datos_adicionales
					add NOMBRETRANSPORTISTA varchar(255)
					    
					alter table entrega_datos_adicionales
					Add CUITTRANSPORTISTA varchar2(16)

					alter table entrega_datos_adicionales
					add NOMBRECHOFER varchar(255)
					    
					alter table entrega_datos_adicionales
					Add CUITCHOFER varchar2(16)
				**Se agrega registro a la tabla sys_config
					insert into sys_config values ('id_YPF', 28254);

			*** Modificacion de ApplicationResources.properties
	    		# -- Reporte Entregas Carta Porte YPF
				/*entregaYPF.title = Reporte Entregas Carta Porte YPF
				entregaYPF.filtro.title = Filtro - Reporte Entregas Carta Porte YPF
				entregaYPF.heading = Reporte Entregas Carta Porte YPF*/

			*** Se Modifica menu-config.xml
				/*<Menu name="AplicacionMenu" title="menu.aplicacion" description="Aplicaciones" roles="ROLE_USER,ROLE_APLICACION" page="" width="60">
			        <Item name="EntregaYPY" title="entregaYPF.heading" page="/repEntregasYPF.html" width="240"/>*/	        

			*** Se crea jsp entregasYPF.jsp
				<%@ include file="/common/taglibs.jsp"%>
				<head>
					<title><fmt:message key="entregaYPF.title"/></title>
					<meta name="heading" content="<fmt:message key='entregaYPF.heading'/>"/>
					<meta name="filtroTitle" content="<fmt:message key='entregaYPF.filtro.title'/>"/>
					<meta name="filtro" content="/WEB-INF/pages/entregasYPFFiltro.jsp" />
				</head>

				<%@ include file="auditEntity.jspf" %>	

			*** Se crea entregasYPFFiltro.jsp
				<%@ include file="/common/taglibs.jsp"%>

				<s:form id="repConsultaYPF" action="reporteConsultaYPF" method="post" onsubmit="return AjaxFormValidator.submit(this.id, 'repoGrid')">
					<table width="100%">
						<tr>
							<td align="left" valign="top">
								<table>
									<tr >
										<TD valign="top">
											<sx:datetimepicker key="fechaDesde" label="Desde: "
												required="false" cssClass="text" displayFormat="%{datePattern}"
												templateCssPath="%{#datetimepickerCSS}" cssStyle="width:100px"/>								
										 </TD>
										 <TD valign="top">
											<sx:datetimepicker key="FechaHasta"  label="Hasta: "
												required="false" cssClass="text" displayFormat="%{datePattern}"
												templateCssPath="%{#datetimepickerCSS}" cssStyle="width:100px"/>								
										 </TD>
										 <td>
										 	<s:textfield name="numeroCartaPorte" label="Carta Porte:" required="false" cssClass="text size100" maxlength="255" />
										 </td>	
									</tr>
								</table>
							</td>
							<td align="right" valign="bottom">
							   <table>
							     <tr>
							        <td>
										<input type='button' style="width:60px; margin-right: 5px" class="button"
											value="<fmt:message key="button.clearfilter"/>" onclick="clearForm(this.form);" />
									</td>
							        <td>
								      <s:submit cssClass="button" action="reporteConsultaYPF" key="button.generar"/>
								   </td>   
								 </tr>  
								</table>
							</td>
						</tr>
					</table>
				</s:form>

	    	***Crecion de ConsultaCartaPorteYPFAction.java
	    		package com.zeni.app.webapp.action;

				import java.io.IOException;
				import java.util.ArrayList;
				import java.util.Date;
				import java.util.List;
				import java.util.Map;
				import java.util.Set;
				import java.util.TreeMap;

				import javax.servlet.http.HttpServletResponse;


				import org.apache.poi.hssf.usermodel.HSSFCell;
				import org.apache.poi.hssf.usermodel.HSSFCellStyle;
				import org.apache.poi.hssf.usermodel.HSSFRow;
				import org.apache.poi.hssf.usermodel.HSSFSheet;
				import org.apache.poi.hssf.usermodel.HSSFWorkbook;
				import org.apache.poi.ss.usermodel.CellStyle;
				import org.apache.poi.ss.util.CellRangeAddress;
				import org.apache.struts2.convention.annotation.Action;
				import org.apache.struts2.convention.annotation.Result;
				import org.apache.struts2.convention.annotation.Results;


				import com.opensymphony.xwork2.validator.annotations.DateRangeFieldValidator;
				import com.zeni.app.model.Cliente;
				import com.zeni.app.model.F1116A;
				import com.zeni.app.service.ContratoManager;
				import com.zeni.app.util.DateUtil;

				@Results({
					@Result(name = "success", location = "/WEB-INF/pages/entregasYPF.jsp"),
					@Result(name = "input", location = "/WEB-INF/pages/entregasYPF.jsp") })
				public class ConsultaCartaPorteYPFAction extends BaseAction{

					private static final long serialVersionUID = 4736169221538576443L;
						
					private static ContratoManager contratoManager;
					private Date fechaDesde;
					private Date fechaHasta;
					private String numeroCartaPorte;
					
					//Lista de Cartas porte ya validadas 
					private List<Object[]> listaArregloObjeto;	
					
					public ContratoManager getContratoManager() {
						return contratoManager;
					}
					
					public void setContratoManager(ContratoManager contratoManager) {
						ConsultaCartaPorteYPFAction.contratoManager = contratoManager;
					}
					
					public Date getFechaDesde() {
						return fechaDesde;
					}
					
					@DateRangeFieldValidator(message = "Fecha invalida", key = "i18n.key", shortCircuit = true, min = "01/01/2012")
					public void setFechaDesde(Date fechaDesde) {
						this.fechaDesde = fechaDesde;
					}
					
					public Date getFechaHasta() {
						return fechaHasta;
					}

					@DateRangeFieldValidator(message = "Fecha invalida", key = "i18n.key", shortCircuit = true, min = "01/01/2012")	
					public void setFechaHasta(Date fechaHasta) {
						this.fechaHasta = fechaHasta;
					}
					
					public String getNumeroCartaPorte() {
						return numeroCartaPorte;
					}
					
					public void setNumeroCartaPorte(String numeroCartaPorte) {
						this.numeroCartaPorte = numeroCartaPorte;
					}
					
					@Action("repEntregasYPF")
					@Override
					public String execute() {
						return SUCCESS;
					}

					@Action("reporteConsultaYPF")
					public String repConsultaYPF() {
						try {
							if (!(fechaDesde == null) && !(fechaHasta == null)) {
								descargaArchivo();
								clearActionErrors();
							}else {
								addActionError("La fecha de inicio y de fin son necesarias para la busqueda");				
							}			
						} catch (IOException e) {
							e.printStackTrace();
							addActionError("Error en la escritura del archivo");
						} catch (Exception e) {
							e.printStackTrace();
							addActionError("Error en la generacion del Archivo");
						}
						return SUCCESS;
					}
					
					//Recupera cliente segin id inscrito en la tabla sys_config
					private Cliente cuitCliente(String id){
						Cliente cliente = (Cliente) contratoManager.findById(Cliente.class, new Long(contratoManager.getSystemConfig(id)));
						return cliente;
					};
					
					//Metodo que realiza a descarga del archivo en el cliente
					private void descargaArchivo() throws IOException, Exception {
						HttpServletResponse response = getResponse();
						response.setContentType("application/vnd.ms-excel");
				        response.setHeader("Content-Disposition", "attachment; filename=CartaPorteYPF.xls");
				        //Creo libro de trabajo
				        HSSFWorkbook libro = new HSSFWorkbook();
				        List<String[]> errores= validaDatosb(genDatosYPF());
				        //Validacion, se genera hoja de error unicamente si lista de errores no esta vacia
				        if(!errores.isEmpty()){
				        	//Generacion de hoja de errores CCPP
				        	generarArcIPF(libro, genHeadersErroresYPF(), errores, "ErrorYPF");
				        }        
				        //Generacion de archivo CCPP
				        generarArcIPF(libro, genTitulosYPF(), arregloDatos(listaArregloObjeto), "CCPP");
				        //Generacion de archivo CalidadCCPP
				        generarArcIPF(libro, genTitulosCalidad(), genAnalisisCalidad(arregloDatosCalidad(genDatosYPFCalidad())), "CalidadCCPP");
				        libro.write(response.getOutputStream());
					}
					
					//Metodo que crea con los datos, las hojas del archivo excel
					private void generarArcIPF(HSSFWorkbook libro, String[] titulos, List<String[]>
					arregloFilas, String tipo) throws IOException, Exception {		

				        //Creo la hoja de trabajo
				        HSSFSheet hoja = libro.createSheet(tipo);
				        //Ancho de la columna por defecto
				        hoja.setDefaultColumnWidth(15);
				        
				        //Creacion de fila
				        HSSFRow fila;
				        Integer rows = 0;
				        
				      //Creacion de celda
				        HSSFCell cell; 
				        fila = hoja.createRow(rows++);
				        
				        for(int i = 0;i<titulos.length;i++){
							cell = fila.createCell(i);
							cell.setCellValue(titulos[i]);
						}
						try {			
							for (String[] arregloFila : arregloFilas) {
								fila = hoja.createRow(rows++);
								for(int i = 0;i<arregloFila.length;i++){
									cell = fila.createCell(i); 						
									cell.setCellValue(arregloFila[i]);
									//Valida los campos de peso, los cuales son tipo double
									if (i > 20 && !tipo.equals("ErrorYPF")){
										cell.setCellValue(Double.parseDouble(arregloFila[i]));
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}        
					}
					
					//Metodo que crea con los datos, las hojas del archivo excel
					private void generarArcIPF(HSSFWorkbook libro, List<Map<Integer, Object[]>> listaT, List<List<String>>
					arregloFilas, String tipo) throws IOException, Exception {		
						
				        //Creo la hoja de trabajo
				        HSSFSheet hoja = libro.createSheet(tipo);
				        //Ancho de la columna por defecto
				        hoja.setDefaultColumnWidth(15);

				        //Creacion de fila
				        HSSFRow fila;
				        Integer rows = 0;
				       
				        // Center Align Cell Contents 
				        HSSFCellStyle style2 = libro.createCellStyle();
				        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				        
				        //Creacion de celda
				        HSSFCell cell; 
				        
				        for (Map<Integer, Object[]> titulos : listaT) {
				        	fila = hoja.createRow(rows);
				        	//Iterar sobre los datos de la fila1 de tiulos
				            Set<Integer> keyId = titulos.keySet();
				            int idCell = 0;
				            int columna = 0;
				            for (Integer key : keyId) {
				                Object[] objeto = titulos.get(key);            
				                String titu = (String) objeto[0];
				                int col = (Integer) objeto[1];
				                
				                cell = (HSSFCell) fila.createCell(idCell);
				                cell.setCellValue(titu);
				                columna = columna + col;
				                setRegionBorder(hoja, rows, idCell, (columna-1));
				                cell.setCellStyle(style2);
				                idCell = idCell + col;
				            }
				            rows++;
						}     
						try {	
							//Lista de codigos de calidad que no tienen camara 
							List<Integer> noCamara = new ArrayList<Integer>();
							noCamara.add(85);
							noCamara.add(97);
							noCamara.add(96);
							//noCamara.add(89);
							
							//Se recorre primera lista
							for (List<String> arregloFila : arregloFilas) {
								//Se desglosa primera lista
								List<String> datos = arregloFila;
								//Se toma la fila de la hoja excel
								fila = hoja.createRow(rows++);
								//Contador donde comienza Grado
								int cont = 0;
								int cont1 = 13;
								//Se recorre la segunda lista indexada
								for(int i = 0;i<datos.size();i++){
									//Se valida si tiene numero de certificdo, si es false no tiene numero de certificado
									int camara = Integer.parseInt(datos.get(9));
									
									
									//cell.setCellValue(datos.get(i));
									//Hasta el iteracion 4, las campos estan en la misma poscicion de la consulta
									if((i <= 5) && (i != 4)){
										cell = fila.createCell(i); 
										cell.setCellValue(datos.get(i));
										cont++;
									//Se valida si el codigo de calidad lleva camara o no
									}else if (i == 4 ){							
										if(!noCamara.contains(Integer.parseInt(datos.get(i)))){
											cell = fila.createCell(i); 
											cell.setCellValue("Si");							
										}else{
											cell = fila.createCell(i); 
											cell.setCellValue("No");
											break;
										}
										cont++;
									}else if(i>5){
										if(i <= 8){
											if(Double.parseDouble(datos.get(i)) > 0){
												cell = fila.createCell(i); 
												cell.setCellValue(1);
												cell = fila.createCell(i + cont1); 
												cell.setCellValue(datos.get(i));
											}else{
												cell = fila.createCell(i); 
												cell.setCellValue(2);
												cell = fila.createCell(i + cont1); 
												cell.setCellValue(datos.get(i));
											}
											cont1 = cont1 - 2;
											cont++;
										}else if(i <= 9){
											double factor = Double.parseDouble(datos.get(10));	
											//Se graba el factor en la columna 11 y 12
											if (camara == 0){
												cell = fila.createCell(11); 
												cell.setCellValue(factor);
												cont++;
											}else{
												cell = fila.createCell(12); 						
												cell.setCellValue(factor);
												cont++;
											}
										}else if( i >= 10 && datos.size() > (cont+1)){
											//Se captura posicion en el excel segun el rubro
											int  posRubro = posicionRubro(Integer.parseInt(datos.get(++cont)));
											double resultado = Double.parseDouble(datos.get(++cont));
											double porcentaje = Double.parseDouble(datos.get(++cont));
											double merma = Double.parseDouble(datos.get(cont + 1));
											if (posRubro  == 9){
												if (camara == 0){
													cell = fila.createCell(posRubro); 
													cell.setCellValue(resultado);
												}else{
													cell = fila.createCell(++posRubro); 						
													cell.setCellValue(resultado);
												}
											}else if(posRubro  == 13){
												if (camara==0){
													cell = fila.createCell(posRubro); 
													cell.setCellValue(resultado);
													cell = fila.createCell(++posRubro); 
													cell.setCellValue(merma);
												}else{
													cell = fila.createCell(posRubro+2); 
													cell.setCellValue(resultado);
													cell = fila.createCell(posRubro+3); 
													cell.setCellValue(porcentaje);
												}
											}else if(posRubro  > 21){
												if (camara==0){
													cell = fila.createCell(posRubro); 
													cell.setCellValue(resultado);
													cell = fila.createCell(++posRubro); 
													cell.setCellValue(porcentaje);
													cell = fila.createCell(++posRubro); 
													cell.setCellValue(merma);
												}else{
													cell = fila.createCell(posRubro+3); 
													cell.setCellValue(resultado);
													cell = fila.createCell(posRubro+4); 
													cell.setCellValue(porcentaje);
												}
											}
											cont++;
										}
									}					
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}        
					}
						
					//Metodo que genera la lista de registros resultado de la consulta genDatosYPFCalidad, formatea los 
					//campos y los combierte en una lista
					private List<List<String>> arregloDatosCalidad(List<Object[]> lista){
						//Lista que alcenara todos los registros resultado de la consulta
						List<List<String>> listaArreglos = new ArrayList<List<String>>();
						int id = 0;
						if (lista != null && lista.size() > 0) {
							for (Object item : lista) {
								Object[] objectItem = (Object[]) item;
								id = id + 1;
								//Lista que almacena los campos de cada registro
								List<String> arregloFila= new ArrayList<String>();
								//Se les da formato a cada campo
								String nroCARTADEPORTE = ((objectItem[0] != null) ? objectItem[0].toString() : null);
								String codigoOncca = ((objectItem[1] != null) ? objectItem[1].toString() : null);
								String fechaOperacion = ((objectItem[2] != null) ? objectItem[2].toString() : null);
								double  cantidadentrega= ((objectItem[3] != null) ? (Double.valueOf(objectItem[3].toString()))*1000 : 0);
								int condicionCamara = ((objectItem[4] != null) ? Integer.parseInt(objectItem[4].toString()) : 0);
								String locCodOncca = ((objectItem[5] != null) ? objectItem[5].toString() : null);
								double mermaZarandeo = ((objectItem[6] != null) ? (Double.valueOf(objectItem[6].toString()))*1000 : 0);
								double mermaHumedad = ((objectItem[7] != null) ? (Double.valueOf(objectItem[7].toString()))*1000 : 0);
								double mermaFumigado = ((objectItem[8] != null) ? (Double.valueOf(objectItem[8].toString()))*1000 : 0);
								int nroCertificado = ((objectItem[9].toString().equals("1")) ? Integer.parseInt(objectItem[9].toString()) : 0);
								double netoStock = cantidadentrega - mermaZarandeo - mermaHumedad - mermaFumigado;
								//Se añaden los campos a la lista 			
								arregloFila.add(nroCARTADEPORTE);
								arregloFila.add(codigoOncca);
								arregloFila.add(fechaOperacion);
								arregloFila.add(String.valueOf(netoStock));
								arregloFila.add(String.valueOf(condicionCamara));
								arregloFila.add(locCodOncca);
								arregloFila.add(String.valueOf(mermaZarandeo));
								arregloFila.add(String.valueOf(mermaHumedad));
								arregloFila.add(String.valueOf(mermaFumigado));
								arregloFila.add(String.valueOf(nroCertificado));
								listaArreglos.add(arregloFila);
							}
						}
						return listaArreglos;
					}

					//Crea regiones en la hoja excel
					private static void setRegionBorder(HSSFSheet sheet, int row, int columnFrom, int columnTo)  {
						CellRangeAddress region = new CellRangeAddress(row, row, columnFrom, columnTo);	
						sheet.addMergedRegion(region);		
						final short border = CellStyle.BORDER_THIN;
						HSSFWorkbook wb = sheet.getWorkbook();
					}
						
					//Metodo que devuelve los titulos del archivo excel CCPP
					private String[] genTitulosYPF(){
						//Genero Header del Excel
				        String[] titulos = new String[]{"Número de Carta de Porte",	
														"Cuit Proveedor ",	
														"CUIT Titular",	
														"CUIT Intermediario",	
														"CUIT Remitente Comercial",	
														"CUIT Corredor",	
														"CUIT Representante / Entregador",	
														"CUIT Destinatario",	
														"Destino",	
														"Transportista",	
														"CUIT Transportista", 
														"Chofer",	
														"CUIT/CUIL Chofer",	
														"Número Material Codigo ONCCA",	
														"Cosecha",	
														"Establecimiento de Procedencia /Dirección",	
														"Partido de Procedencia  Código ONCCA",	
														"Localidad de Procedencia  Codigo ONCCA",	
														"Patente Camión",	
														"Patente Acoplado",	
														"Fecha Descarga",	
														"Peso Bruto (Camion + Carga)",	
														"Peso Tara (Camion Vacío)",	
														"Peso Neto (Bruto - Tara)",	
														"Peso Final (Neto - Merma)"
										};
						return titulos;
					}
					
					//Metodo que devuelve los titulos del archivo excel de Calidad
					private List<Map<Integer, Object[]>> genTitulosCalidad(){
						List<Map<Integer, Object[]>> listaMap= new ArrayList<Map<Integer,Object[]>>();
						Map<Integer, Object[]> mapTitulos = new TreeMap<Integer, Object[]>();
						Map<Integer, Object[]> mapTitulos1 = new TreeMap<Integer, Object[]>();
						Map<Integer, Object[]> mapTitulos2 = new TreeMap<Integer, Object[]>();
						
						//Genero Header1 del Excel
						mapTitulos.put(0,new Object[]{"", 5});
						mapTitulos.put(1,new Object[]{"Camara", 1});
						mapTitulos.put(2,new Object[]{"Acondicionamientos", 3});
						mapTitulos.put(3,new Object[]{"Grado", 2});
						mapTitulos.put(4,new Object[]{"Factor", 2});
						mapTitulos.put(5,new Object[]{"Humedad", 4});
						mapTitulos.put(6,new Object[]{"", 5});
						mapTitulos.put(7,new Object[]{"Incluido Tierra", 5});
						mapTitulos.put(8,new Object[]{"Granos Dañados", 5});
						mapTitulos.put(9,new Object[]{"Granos Verdes", 5});
						mapTitulos.put(10,new Object[]{"Granos Partidos", 5});
						mapTitulos.put(11,new Object[]{"Granos Quebrados", 5});
						mapTitulos.put(12,new Object[]{"Granos Quemados o Averiados", 5});
						mapTitulos.put(13,new Object[]{"Granos negros", 5});
						mapTitulos.put(14,new Object[]{"Olores Comerciales Objetables", 5});
						mapTitulos.put(15,new Object[]{"Chamicos", 5});
						mapTitulos.put(16,new Object[]{"MATERIAS EXTRAÑAS", 5});
						mapTitulos.put(17,new Object[]{"GRANO PICADO", 5});
						mapTitulos.put(18,new Object[]{"GRANOS AMOHOSADOS", 5});
						mapTitulos.put(19,new Object[]{"PESO HECTOLITRICO", 5});
						mapTitulos.put(20,new Object[]{"COLOR", 5});
						mapTitulos.put(21,new Object[]{"TIPO", 5});
						mapTitulos.put(22,new Object[]{"MATERIA GRASA", 5});
						mapTitulos.put(23,new Object[]{"ACIDEZ DE LA MATERIA GRASA", 5});
						mapTitulos.put(24,new Object[]{"TANINOS", 5});
						mapTitulos.put(25,new Object[]{"ENTEROS + QUEBRADOS", 5});
						mapTitulos.put(26,new Object[]{"ENTEROS", 5});
						mapTitulos.put(27,new Object[]{"GRANOS PANZA BLANCA", 5});
						mapTitulos.put(28,new Object[]{"MANCHADOS Y/O COLOREADOS", 5});
						mapTitulos.put(29,new Object[]{"ENYESADOS O MUERTOS", 5});
						mapTitulos.put(30,new Object[]{"COLORADOS Y ESTRIAS ROJAS", 5});
						mapTitulos.put(31,new Object[]{"SEMILLAS BEJUCO Y/O POROTILLO", 5});
						mapTitulos.put(32,new Object[]{"GRANOS CON CARBON", 5});
						mapTitulos.put(33,new Object[]{"GRANOS ARDIDOS", 5});
						mapTitulos.put(34,new Object[]{"REVOLCADOS EN TIERRA", 5});
						mapTitulos.put(35,new Object[]{"TREBOL DE OLOR", 5});
						mapTitulos.put(36,new Object[]{"PUNTA SOMBREADA", 5});
						mapTitulos.put(37,new Object[]{"PUNTA NEGRA", 5});
						mapTitulos.put(38,new Object[]{"CONTENIDO PROTEICO", 5});
						mapTitulos.put(39,new Object[]{"CAPACIDAD GERMINATIVA", 5});
						mapTitulos.put(40,new Object[]{"GRANOS PELADOS", 5});
						mapTitulos.put(41,new Object[]{"CARBON", 5});
						mapTitulos.put(42,new Object[]{"CALIBRE ZARANDA 2.2", 5});
						mapTitulos.put(43,new Object[]{"CALIBRE ZARANDA 2.5", 5});
						mapTitulos.put(44,new Object[]{"PROTEINA MÍNIMA", 5});
						mapTitulos.put(45,new Object[]{"PROTEINA MÁXIMA", 5});
						listaMap.add(mapTitulos);
						
						//Genero Header2 del Excel
						mapTitulos1.put(0,new Object[]{"",1});
						mapTitulos1.put(1,new Object[]{"Producto",1});
						mapTitulos1.put(2,new Object[]{"",2});
						mapTitulos1.put(3,new Object[]{"Camara",1});	
						mapTitulos1.put(4,new Object[]{"Localidad",1});	
						mapTitulos1.put(5,new Object[]{"Sarandeo",1});	
						mapTitulos1.put(6,new Object[]{"Secado",1});	
						mapTitulos1.put(7,new Object[]{"Fumigado",1});	
						mapTitulos1.put(8,new Object[]{"En Puerto",1});	
						mapTitulos1.put(9,new Object[]{"En Cámara",1});	
						mapTitulos1.put(10,new Object[]{"En Puerto",1});	
						mapTitulos1.put(11,new Object[]{"En Cámara",1});	
						mapTitulos1.put(12,new Object[]{"En Puerto",2});		
						mapTitulos1.put(13,new Object[]{"En Cámara",2});		
						mapTitulos1.put(14,new Object[]{"Acondicionamientos",3});			
						mapTitulos1.put(15,new Object[]{"Volátil",2});	
						int cont = 16;
						for (int i = 0; i < 78; i++) {
							if(i%2==0){
								mapTitulos1.put(cont,new Object[]{"En Puerto",3});	
							}else{
								mapTitulos1.put(cont,new Object[]{"En Cámara",2});
							}
							cont++;
						}	
						
						listaMap.add(mapTitulos1);

						//Genero Header3 del Excel
						mapTitulos2.put(0,new Object[]{"CP",1});
						mapTitulos2.put(1,new Object[]{"Cod.Onca",1});
						mapTitulos2.put(2,new Object[]{"Fecha Operación",1});
						mapTitulos2.put(3,new Object[]{"Neto Stock",1});	
						mapTitulos2.put(4,new Object[]{"SI/NO",1});	
						mapTitulos2.put(5,new Object[]{"Cod.Onca",1});	
						mapTitulos2.put(6,new Object[]{"SI/NO",1});	
						mapTitulos2.put(7,new Object[]{"SI/NO",1});	
						mapTitulos2.put(8,new Object[]{"SI/NO",1});	
						mapTitulos2.put(9,new Object[]{"Medicion",1});	
						mapTitulos2.put(10,new Object[]{"Medicion",1});	
						mapTitulos2.put(11,new Object[]{"Medicion",1});	
						mapTitulos2.put(12,new Object[]{"Medicion",1});		
						mapTitulos2.put(13,new Object[]{"Medicion",1});		
						mapTitulos2.put(14,new Object[]{"KG Merma",1});			
						mapTitulos2.put(15,new Object[]{"Medición",1});	
						mapTitulos2.put(16,new Object[]{"% Desc.",1});
						mapTitulos2.put(17,new Object[]{"KG Fumigado",1});		
						mapTitulos2.put(18,new Object[]{"KG Secado",1});		
						mapTitulos2.put(19,new Object[]{"KG Zarandeo",1});			
						mapTitulos2.put(20,new Object[]{"Medición",1});	
						mapTitulos2.put(21,new Object[]{"KG Merma",1});

						cont = 22;
						for (int i = 0; i < 39; i++) {			
							mapTitulos2.put(cont++,new Object[]{"Medición",1});	
							mapTitulos2.put(cont++,new Object[]{"% Desc/Bonif",1});
							mapTitulos2.put(cont++,new Object[]{"KG Merma",1});	
							mapTitulos2.put(cont++,new Object[]{"Medición",1});
							mapTitulos2.put(cont++,new Object[]{"% Desc/Bonif",1});				
						}	


						listaMap.add(mapTitulos2);
						return listaMap;
					}
					
					//Mapa con los rubros y su posicion en el excel
					private int posicionRubro(int idRubro){
						Map<Integer, Integer> mapCampos = new TreeMap<Integer, Integer>();		
						mapCampos.put(429520,9);
						mapCampos.put(429522,9);
						mapCampos.put(429545,13);
						mapCampos.put(429605,22);
						mapCampos.put(429507,27);
						mapCampos.put(429613,27);
						mapCampos.put(429542,32);
						mapCampos.put(429518,42);						
						mapCampos.put(429526,47);
						mapCampos.put(429535,52);
						mapCampos.put(429559,57);
						mapCampos.put(429601,62);
						mapCampos.put(429471,67);
						mapCampos.put(429538,72);
						mapCampos.put(429527,77);
						mapCampos.put(429563,82);
						mapCampos.put(429496,87);
						mapCampos.put(429606,92);
						mapCampos.put(429498,97);
						mapCampos.put(429536,122);
						mapCampos.put(429548,127);
						mapCampos.put(2006,132);
						mapCampos.put(1648,137);
						mapCampos.put(1647,142);
						mapCampos.put(429617,147);
						mapCampos.put(429525,152);
						mapCampos.put(429567,157);
						mapCampos.put(429600,162);
						mapCampos.put(429539,172);
						mapCampos.put(429494,182);
						mapCampos.put(429517,187);
						mapCampos.put(429532,192);
						mapCampos.put(429479,197);
						mapCampos.put(429603,202);
						mapCampos.put(429561,0);
						mapCampos.put(429473,0);
						return mapCampos.get(idRubro);
					}
					
					//Metodo que devuelve los titulos del archivo excel de errores
					private String[] genHeadersErroresYPF(){
						//Genero Header del Excel
				        String[] titulos = new String[]{"Número de Carta de Porte",	
														"Datos Faltantes"
										};
						return titulos;
					}
					
					//Metodo que genera los datos de entregas segun fecha y para cliente YPF
					@SuppressWarnings("unchecked")
					private List<Object[]> genDatosYPF(){
						List<Object[]> lista = null;
						Date fechaDesde = getFechaDesde();
						Date fechaHasta = getFechaHasta();
						String cartaPorte = !(getNumeroCartaPorte() == null) ? (!(getNumeroCartaPorte()
								.equals("")) ? " AND e.nrocartadeporte = '"
								+ getNumeroCartaPorte() + "'" : " ") : " ";
						
						try {
							String consulta ="SELECT distinct(e.NROCARTADEPORTE), " +
													"(SELECT CUIT FROM CLIENTE WHERE ID = e.CLIENTEVENDEDOR_ID), " +
													"(SELECT CUIT FROM CLIENTE WHERE ID = e.CLIENTECOMPRADOR_ID), " +
													"eda.CUITINTERMEDIARIO, " +
													"EDA.CUITREFCOMERCIAL, " +
													"(SELECT CUIT FROM ENTREGADOR WHERE ID = E.ENTREGADOR_ID), " +
													" EDA.CUITRECIBIDOR, " +
													"eda.puertoYPF, " +
													"eda.nombreTransportista, " +
													"eda.cuitTransportista, " +
													"eda.nombreChofer, " +
													"eda.cuitChofer, " +
													"(SELECT CODIGOONCCA FROM Producto WHERE id = e.PRODUCTO_ID), " +
													"(select nombre from Producto where id = e.PRODUCTO_ID), " +
													"(select aniocosecha from contrato where id = (select ep.CONTRATO_ID " +
													"												from entrega_partida ep " +
													"												where ep.entrega_id = e.id " +
													"												and rownum <= 1)), " +
													"(SELECT codigooncca FROM partido WHERE id = (SELECT ciu.partido_id FROM ciudad ciu WHERE ciu.id = e.procedencia_id)), " +
													"(SELECT ciu.CODIGO FROM ciudad ciu WHERE ciu.id = e.procedencia_id), " +
													"EDA.PATENTECHASIS, " +
													"EDA.PATENTE_ACOPLADO, " +
													"TO_CHAR(E.FECHADESCARGAMERCADERIA, 'dd/MM/yyyy'), " +
													"EDA.PESOBRUTO, " +
													"EDA.TARA, " +
													"EDA.TOTALMERMAS " +
											"from ENTREGA e " +
											"left join ENTREGA_DATOS_ADICIONALES eda " +
											"	on eda.entrega_id = e.id " +
											"left join entrega_partida ep " +
											"	on ep.entrega_id = e.id " +
											"inner join contrato c " +
											"	on ep.contrato_id = c.id " +
											"WHERE " +
											"e.FECHAALTA >= to_date('"+ DateUtil.getDate(fechaDesde)+ "','dd/MM/yyyy') " +
											"AND e.FECHAALTA <= to_date('"+ DateUtil.getDate(fechaHasta) + "','dd/MM/yyyy') " +
											"and ((select cli.id from cliente cli " +
											"					inner join cuenta_cliente cc " +
											" 						on cli.id = cc.cliente_id " +
											"					where cc.id = c.vendedor_id) = " +contratoManager.getSystemConfig("id_YPF")+" or " +
													"(select cli.id from cliente cli " +
													"			inner join cuenta_cliente cc " +
													"				 on cli.id = cc.cliente_id" +
													"			where cc.id = c.vendedor_id) = " +contratoManager.getSystemConfig("id_YPF")+") "+cartaPorte;
						
							lista = (List<Object[]>) contratoManager.executeSQL(consulta);
						} catch (Exception e) {
							addActionError("Error en la consulta a los datos: "+e.getMessage());
						}			
							
						return lista;
					}
					
					//Metodo que genera los datos de calidad entregas segun fecha y para cliente YPF
					@SuppressWarnings("unchecked")
					private List<Object[]> genDatosYPFCalidad(){
						List<Object[]> lista = null;
						Date fechaDesde = getFechaDesde();
						Date fechaHasta = getFechaHasta();
						String cartaPorte = !(getNumeroCartaPorte() == null) ? (!(getNumeroCartaPorte()
								.equals("")) ? " AND e.nrocartadeporte = '"
								+ getNumeroCartaPorte() + "'" : " ") : " ";
						
						try {
							String consulta ="SELECT distinct(e.NROCARTADEPORTE), " +
									"(SELECT CODIGOONCCA FROM Producto WHERE id = e.PRODUCTO_ID) as codigoOncca, " +
									"TO_CHAR(E.FECHADESCARGAMERCADERIA, 'dd/MM/yyyy'), " +
									"e.cantidadentrega, " +
									"(select c.codigo from calidad c  " +
										"inner join contrato con " +
										"  on c.id = con.calidad_id " +
										"inner join entrega_partida ep " +
										"  on con.id = ep.contrato_id " +
										"where ep.entrega_id = e.id " +
										"and rownum <= 1), " +
										"(select codigooncca from bolsa b " +
										"inner join analisis_muestra am " +
										"  on b.id = am.camara_id " +
										"inner join entrega ent " +
										"  on am.id = e.analisismuestra_id " +
										"where e.id = ent.id), " +
										"e.CANTIDADMERMAPORZARANDEO, " +
										"e.CANTIDADMERMAPORSECADO, " +
										"e.CANTIDADMERMAPORFUMIGADO, " +
										"CASE WHEN (select NROCERTIFICADOANALISIS " +
										"			from analisis_muestra am " +
										"           inner join entrega ent " +
										"  				on am.id = ent.analisismuestra_id" +
										" 			where ent.id = e.id) IS NULL " +
										"THEN 0 ELSE 1 " +
										"END " +
										"from ENTREGA e " +
										"left join ENTREGA_DATOS_ADICIONALES eda " +
										"	on eda.entrega_id = e.id " +
										"left join entrega_partida ep " +
										"	on ep.entrega_id = e.id " +
										"inner join contrato c " +
										"	on ep.contrato_id = c.id " +
										"WHERE " +
										"e.FECHAALTA >= to_date('"+ DateUtil.getDate(fechaDesde)+ "','dd/MM/yyyy') " +
										"AND e.FECHAALTA <= to_date('"+ DateUtil.getDate(fechaHasta) + "','dd/MM/yyyy') " +
										"and ((select cli.id from cliente cli " +
										"					inner join cuenta_cliente cc " +
										" 						on cli.id = cc.cliente_id " +
										"					where cc.id = c.vendedor_id) = " +contratoManager.getSystemConfig("id_YPF")+" or " +
												"(select cli.id from cliente cli " +
												"			inner join cuenta_cliente cc " +
												"				 on cli.id = cc.cliente_id" +
												"			where cc.id = c.vendedor_id) = " +contratoManager.getSystemConfig("id_YPF")+") "+cartaPorte;
					
							lista = (List<Object[]>) contratoManager.executeSQL(consulta);
						} catch (Exception e) {
							addActionError("Error en la consulta a los datos: "+e.getMessage());
						}			
							
						return lista;
					}

					@SuppressWarnings("unchecked")
					private List<List<String>> genAnalisisCalidad(List<List<String>> arregloDatos){
						List<Object[]> lista = null;
						for (List<String> datos : arregloDatos) {
							String nroCartaPorte= datos.get(0).toString(); 
							
							try {
								String consulta ="select CONCEPTORUBROCALIDAD_ID, resultado, PORCENTAJEBONIFICACIONREBAJA, MERMAENCANTIDAD  " +
													"from detalle_analisis_muestra dam " +
													"inner join entrega e " +
													"  on dam.analisismuestra_id = e.analisismuestra_id " +
													"where e.nrocartadeporte = '"+nroCartaPorte+"'";
								lista = (List<Object[]>) contratoManager.executeSQL(consulta);
								//Formato de numero de Carta Porte
								nroCartaPorte = String.format("000%s-%s",nroCartaPorte.substring(0, 1), nroCartaPorte.substring(1));	
								datos.set(0, nroCartaPorte);
								Double analisisFactor=100D;
								if (lista != null && lista.size() > 0) {
									for (Object item : lista) {
										Object[] objectItem = (Object[]) item;
										int idRubro = ((objectItem[0] != null) ? Integer.parseInt(objectItem[0].toString()) : 0);
										double resultado = ((objectItem[1] != null) ? (Double.valueOf(objectItem[1].toString())) : 0);
										double porcRebaja = ((objectItem[2] != null) ? (Double.valueOf(objectItem[2].toString())) : 0);
										double mermaCant = ((objectItem[3] != null) ? (Double.valueOf(objectItem[3].toString())) : 0);	
										//Suma al factor resultado del analisis exceptuando al rubro grado
										analisisFactor += (idRubro == 429520 || idRubro == 429522) ? 0: porcRebaja;
										datos.add(String.valueOf(idRubro));
										datos.add(String.valueOf(resultado));
										datos.add(String.valueOf(porcRebaja));
										datos.add(String.valueOf(mermaCant));						
									}					
								}
								datos.add(10, String.valueOf(analisisFactor));
							} catch (Exception e) {
								addActionError("Error en la consulta a los datos: "+e.getMessage());
							}	
						}
						return arregloDatos;
					}
						
					//Metodo que genera la lista de registros CCPP
					private List<String[]> arregloDatos(List<Object[]> lista){
						List<String[]> listaArreglos = new ArrayList<String[]>();
						int id = 0;
						if (lista != null && lista.size() > 0) {
							for (Object item : lista) {
								Object[] objectItem = (Object[]) item;
								id = id + 1; 
								String nroCARTADEPORTE = ((objectItem[0] != null) ? objectItem[0].toString() : "");
								String cuitVendedor = ((objectItem[1] != null) ? objectItem[1].toString() : "");
								String cuitComprador = ((objectItem[2] != null) ? objectItem[2].toString() : "");
								String cuitIntermediario = ((objectItem[3] != null) ? objectItem[3].toString() : "");
								String cuitRefComercial = ((objectItem[4] != null) ? objectItem[4].toString() : "");
								String cuitEntregador = ((objectItem[5] != null) ? objectItem[5].toString() : "");
								String cuitRecibidor = ((objectItem[6] != null) ? objectItem[6].toString() : "");
								String destino = ((objectItem[7] != null) ? objectItem[7].toString() : "");
								String transportista = ((objectItem[8] != null) ? objectItem[8].toString() : "");
								String cuitTransportista = ((objectItem[9] != null) ? objectItem[9].toString() : "");
								String chofer = ((objectItem[10] != null) ? objectItem[10].toString() : "");
								String cuitChofer = ((objectItem[11] != null) ? objectItem[11].toString() : "");
								String oncaaProducto = ((objectItem[12] != null) ? objectItem[12].toString() : "");
								String producto = ((objectItem[13] != null) ? objectItem[13].toString() : "");
								String cosecha = ((objectItem[14] != null) ? objectItem[14].toString() : "");
								String onccaPartido = ((objectItem[15] != null) ? objectItem[15].toString() : "");
								String onccaLocalidad = ((objectItem[16] != null) ? objectItem[16].toString() : "");
								String patenteChasis = ((objectItem[17] != null) ? objectItem[17].toString() : "");
								String patenteAcoplado = ((objectItem[18] != null) ? objectItem[18].toString() : "");
								String fechaDescarga = ((objectItem[19] != null) ? objectItem[19].toString() : "");
								double pesoBruto = ((objectItem[20] != null) ? (Double.valueOf(objectItem[20].toString()))*1000 : 0);
								double pesoTara = ((objectItem[21] != null) ? (Double.valueOf(objectItem[21].toString()))*1000 : 0);
								double pesoMerma = ((objectItem[22] != null) ? (Double.valueOf(objectItem[22].toString()))*1000 : 0);		
								double pesoNeto = pesoBruto - pesoTara;
								double pesoFinal = (pesoBruto -pesoTara)-pesoMerma;
								//Formato de numero de Carta Porte
								nroCARTADEPORTE = String.format("000%s-%s",nroCARTADEPORTE.substring(0, 1), nroCARTADEPORTE.substring(1));	
								cosecha = cosecha.replace("/", "");
								cosecha = String.format("20%s-20%s",cosecha.substring(0, 2), cosecha.substring(2, 4));
								if(producto.contains("Soja")){
									cuitRecibidor = cuitCliente("id_YPF").getCuit();
								}
								if (!cuitIntermediario.replace("-", "").equalsIgnoreCase(cuitCliente("id_YPF").getCuit().replace("-", ""))){
									cuitVendedor = cuitIntermediario;
								}
								if (!cuitRefComercial.replace("-", "").equalsIgnoreCase(cuitCliente("id_YPF").getCuit().replace("-", "") )){
									cuitVendedor = cuitRefComercial;
								}					
								String[] arregloFila = new String[]{nroCARTADEPORTE, cuitVendedor.replace("-", ""), cuitComprador.replace("-", ""), cuitIntermediario.replace("-", ""), cuitRefComercial.replace("-", ""),
										cuitCliente("zeni_cliente_id").getCuit().replace("-", ""), cuitEntregador.replace("-", ""), cuitRecibidor.replace("-", ""), destino, transportista, cuitTransportista.replace("-", ""), 
										chofer, cuitChofer.replace("-", ""), oncaaProducto,cosecha, "",onccaPartido,onccaLocalidad , patenteChasis, patenteAcoplado, 
										fechaDescarga, String.valueOf(pesoBruto), String.valueOf(pesoTara), String.valueOf(pesoNeto), String.valueOf(pesoFinal)};
								listaArreglos.add(arregloFila);
							}
						}
						return listaArreglos;
					}

					//Metodo para la validacion de datos segun reglas de negocio
					private List<String[]> validaDatosb(List<Object[]> lista){
						List<String[]> listaArreglos = new ArrayList<String[]>();
						listaArregloObjeto = new ArrayList<Object[]>();
						String[] titulos = genTitulosYPF();
						boolean bandera = false;
						String cadena="";
						//Valida si la lista esta vacia
						if (lista != null && lista.size() > 0) {
							//Recorre la lista de objetos
							for (Object item : lista) {
								//Desglosa la lista
								Object[] objectItem = (Object[]) item;
								String nroCARTADEPORTE = ((objectItem[0] != null) ? objectItem[0].toString() : null);
								bandera = false;
								cadena = "";
								int cont = 0;
								for (int i = 0; i < objectItem.length; i++) {
									//Se validan campos faltantes y se prende bandera de error
									if(objectItem[i] == null && (i != 5) && (i != 8) && (i != 9) && (i != 10) && (i != 11) && (i != 17) && (i != 18) && (i != 22)){
										cadena = cadena+titulos[cont]+", ";
										bandera = true;
									}
									if(i==4){
										cont++;
									}
									if(i != 13)
									cont++;
								}
								if (bandera){
									String[] arregloFila = new String[]{nroCARTADEPORTE, cadena};
									listaArreglos.add(arregloFila);
								}else{
									listaArregloObjeto.add((Object[]) item);
								}
							}			
						}
						return listaArreglos;
					}
				}

			Se agrega campo Fumigado------------------------------------------------------------------------------
				****Se agregan campos para Fumigado
					<tr>
						<td><b><span class="text"><fmt:message
										key="entrega.mermafumigado" />
							</span> </b>
						</td>
						<td><s:textfield id="porcentajeFumigado" label=""
								key="entrega.porcentajeFumigado" required="false"
								cssClass="currency size50"
								onchange="javascript:formatXDecimales(this,2); calcularMermaFumigado();" />
						</td>
						<td><s:textfield id="mermaPorFumigado" label=""
								key="entrega.cantidadMermaPorFumigado" required="false"
								cssClass="currency size50"
								onchange="javascript:formatTFToUnidad(this);calcularNetoFumigado();" />
						</td>
						<td align="right"><s:textfield id="netoFumigado"
								label="" key="entrega.cantidadNetaPorFumigado"
								required="false" cssClass="currency size50"
								onchange="javascript:formatTFToUnidad(this); calcularCantidadNeta();" />
						</td>
					</tr>


				***Se agregan funcion JavaScript para Fumigado
					function calcularMermaFumigado(){
						//Capturo valor del campo porcentajeZarandeo
						var porcFumigado = document.getElementById('porcentajeFumigado').value.replace(",",".");
						//Inicializo variable result
						var result="";
						//Se valida si tiene porcentaje de Zarandeo
						if( porcFumigado != "" ){
							//Captura la cantidadEntrega
							var cantEntrega = document.getElementById('cantidadEntrega').value == "" ? "0" : document.getElementById('cantidadEntrega').value.replace(",",".");
							//Calcula la merma por zarandeo
							result = formatToUnidad(parseFloat(cantEntrega) * parseFloat(porcFumigado) / 100);	
						}
						//Se le asigna a la merma por Zarandeo el valor de result
						document.getElementById('mermaPorFumigado').value = result.replace(".",",");
						//ejecuta metodo NetoZarandeo
						calcularNetoFumigado();
					}


					function calcularNetoFumigado(){
						//Capturo valor del campo mermaZarandeo
						var mermaFumigado = document.getElementById('mermaPorFumigado').value.replace(",",".");
						//Inicializo variable result
						var result="";
						//Se valida si tiene mermaZarandeo
						if( mermaFumigado != ""){
							//Captura la cantidadEntrega
							var cantEntrega = document.getElementById('cantidadEntrega').value == "" ? "0" : document.getElementById('cantidadEntrega').value.replace(",",".") ;
							//Calcula el neto por zarandeo
							result = formatToUnidad(parseFloat(cantEntrega) - parseFloat(mermaFumigado));
						}
						//Se le asigna al neto por Zarandeo el valor de result
						document.getElementById("netoFumigado").value = result.replace(".",",");
						calcularCantidadNeta();
					}
				***Se modifica el metodo calcularCantidadNeta(), oara que tome en cuenta el campo fumigado
					if(cantEntrega != ""){
						var mermaPorHumedad = document.getElementById('mermaPorHumedad').value== ""? "0" : document.getElementById('mermaPorHumedad').value.replace(",",".");
						var otrasMermas = document.getElementById('otrasMermas').value == "" ? "0" : document.getElementById('otrasMermas').value.replace(",",".");
						var cantZarandeo = document.getElementById('mermaPorZarandeo').value == "" ? "0" : document.getElementById('mermaPorZarandeo').value.replace(",",".");  
						var cantFumigado = document.getElementById('mermaPorFumigado').value == "" ? "0" : document.getElementById('mermaPorFumigado').value.replace(",","."); 
						result = formatToUnidad((parseFloat(cantEntrega) - parseFloat(cantZarandeo) - parseFloat(cantFumigado) - parseFloat(mermaPorHumedad)) - parseFloat(otrasMermas));
					}
				***Se modifica el metodo calcularCantOtrasMermas(), para que tome en cuenta el campo de fumigado
					function calcularCantOtrasMermas() {
						var cantBruta = $("cantidadEntrega").value.toFloat0();
						var cantNeta = $("cantidadNeta").value.toFloat0();
						var mermaZarandeo = $("mermaPorZarandeo").value.toFloat0();
						var mermaHumedad = $("mermaPorHumedad").value.toFloat0();
						var mermaFumigado = $("mermaPorFumigado").value.toFloat0();
						var result = parseFloat(cantBruta) - parseFloat(cantNeta) - parseFloat(mermaZarandeo) - parseFloat(mermaFumigado) - parseFloat(mermaHumedad);
						if (result < 0.0000001) {
							result = 0;
						}
						$("otrasMermas").value = formatToUnidad(result);
					}
				***Se agregan campos en la clase entrega.java, con su get y set

					private Double porcentajeFumigado;
					private Double cantidadMermaPorFumigado;
					private Double cantidadNetaPorFumigado;
					@Column
					public Double getPorcentajeFumigado() {
						return porcentajeFumigado;
					}	
					public void setPorcentajeFumigado(Double porcentajeFumigado) {
						this.porcentajeFumigado = porcentajeFumigado;
					}
					@Column
					public Double getCantidadMermaPorFumigado() {
						return cantidadMermaPorFumigado;
					}
					public void setCantidadMermaPorFumigado(Double cantidadMermaPorFumigado) {
						this.cantidadMermaPorFumigado = cantidadMermaPorFumigado;
					}
					@Column
					public Double getCantidadNetaPorFumigado() {
						return cantidadNetaPorFumigado;
					}
					public void setCantidadNetaPorFumigado(Double cantidadNetaPorFumigado) {
						this.cantidadNetaPorFumigado = cantidadNetaPorFumigado;
					} 
				***Se agregan campos en la base de datos entrega
					alter table entrega
					add PORCENTAJEFUMIGADO float

					ALTER TABLE ENTREGA
					ADD CANTIDADMERMAPORFUMIGADO FLOAT

					ALTER TABLE ENTREGA
					ADD CANTIDADNETAPORFUMIGADO FLOAT
************************************************************************************************************************Implementado



52.*********************************************************************************************************************
************************************************************************************************************************
********************************************************************Requerimiento validacion de impuesto percepcion CABA
	Usuario 	--> TETI MATIAS
	Ext			--> 5134
	Area		--> Impuestos	Casa Central
	Descripcion: 
	Modificacion de Regla de percepcion de CABA, eliminandose de la misma la exclusion para los casos de comisiones que 
	invierten en la operacion. 
	
	Documentacion___________________________________________________________________________________________________
		<action name="editReglaImpuesto" class="com.zeni.app.webapp.action.ReglaImpuestoAction" method="edit">
            <result>/WEB-INF/pages/reglaImpuestoForm.jsp</result>
            <result name="error">/WEB-INF/pages/reglaImpuestoList.jsp</result>
        </action>

    	Tabla 
    		if( model:AgentePercepcion($Vendedor, $Regla) 
			or model:EsVendZeniLiqIntereses($Vendedor, $Factura) ){
				if((model:ComisConsigQuePercibe($Vendedor, $ActividadVendedor) and not model:ComisionistaConsignatario($ActividadComprador)) or (not model:ComisionistaConsignatario($ActividadVendedor) and not model:ComisionistaConsignatario($ActividadComprador) and not model:Exento($Comprador, $Regla.provincia, $FechaAcreditacionFactura))){ 
				  if( model:InscriptoEn($Comprador, $Regla.provincia) ){    
					ALICUOTA1 = model:getPadronDeJurisdiccionAlicuota('ARF',$Comprador,$Regla,$Factura.fechaFactura);
					ALICUOTA2 = model:getPadronDeJurisdiccionAlicuota('PCA',$Comprador,$Regla,$Factura.fechaFactura);
					if( ALICUOTA1 != null ) {
			                    ALICUOTA = ALICUOTA1 / 100;
					} else if( ALICUOTA2 != null ) {
					    ALICUOTA = ALICUOTA2 / 100;
					} else {
					    ALICUOTA = 0.06;
					}
				  } else {
					if( model:IntervieneJurisdiccion($Factura, $Regla.provincia) ){
					  ALICUOTA1 = model:getPadronDeJurisdiccionAlicuota('ARF',$Comprador,$Regla,$Factura.fechaFactura);
					  ALICUOTA2 = model:getPadronDeJurisdiccionAlicuota('PCA',$Comprador,$Regla,$Factura.fechaFactura);
					  if( ALICUOTA1 != null ) {
			                     ALICUOTA = ALICUOTA1 / 100;
					  } else if( ALICUOTA2 != null ) {
					     ALICUOTA = ALICUOTA2 / 100;
					  } else {
					     ALICUOTA = 0.06;
					  }
					} else {
					  ALICUOTA = null;
					}
				  }
				  RESULT = (ALICUOTA == null? 0: $Importe * ALICUOTA);
				} else {
					0;
				}
			} else
			    0;

		Modificaciones_________________________________________________________________________________________________
		if(not model:Exento($Comprador, $Regla.provincia, $FechaAcreditacionFactura)){}

	***Requerimiento 2: se modifican los clientes con check de agentes de retenciones y percepcion
		create or replace PROCEDURE IIBB(cuitCliente IN cliente.cuit%type) is
		    idCliente cliente.id%type;
		    CURSOR cursor1 is
		      select c.id from cliente c where c.cuit = cuitCliente;    
		  BEGIN    
		      OPEN cursor1;     
		      FETCH cursor1 into idCliente ;
		      update cliente_provincia_iibb set esagenteretencion = 1 where cliente_id = idCliente and provincia_id = 233;
		      dbms_output.put_line(idCliente ||'-'|| cuitCliente);
		  END;

		  EXECUTE IIBB('30-62197317-3');
************************************************************************************************************************Implementado




53.*********************************************************************************************************************
************************************************************************************************************Req.98 SIMAX
*********************************************************************Modificacion de el ancho del contenido de la pagina
***************************************************************************************Modificar vista en Ret del Recibo 
	Usuario 	--> TETI MATIAS
	Ext			--> 5134
	Area		--> Impuestos	Casa Central
	Descripcion: 
	Necesito que la ventana que muestra las retenciones se pueda expandir para tener una vista mas completa de todas las columnas.
	Se encuentra en el módulo Cobranzas/Retenciones del Recibo.

	Documentacion___________________________________________________________________________________________________

		***Vista reciboRetencionList.jsp y reciboRetencionFiltro.jsp

	Modificaciones_________________________________________________________________________________________________
		*****reciboRetencionList.jsp
			***Se modifica ancho de la grilla
				<ria:simplegrid id="recibosRetencionGrid" dataProvider="${dataProvider}" collection="${dataCollection}" rowPerPage="${rowPerPage}"
				var="item" rowCountVar="rowCount" width="1200px" height="400px" resizable="false" showPropertyButton="false" selectionMode="multiple" 
				rowSelectable="true" style="text-align:left;"
				onSetData="gridNotifyOnEmpty(this, __Messages__.gridNoData)">
			***Se ajusta ancho de las columnas
			***Se modifica por javascripts el ancho de los div correspondientes
				var contenido = document.getElementById("contenido")
				var principal = document.getElementById("main")
				var pie = document.getElementById("footer")
				var pagina = document.getElementById("page")

				contenido.style.width = '1200px'
				principal.style.width = '1200px'
				pagina.style.width = '1200px'

			*****reciboRetencionFiltro.jsp
				***Se modifica ancho de obj table
				 	<table border="0" cellspacing="0" cellpadding="0" width="1160px">
************************************************************************************************************************Implementado




54.*********************************************************************************************************************
*************************************************************************************************************Diego Plaza
**********************************************************************************************Consultas sql de hibernate
******************************************************************************************************mat_Operacion.html
	select
                cuentaclie1_.id||'_'||mercado5_.id||'_'||matoperaci0_.condicion||'_'||matoperaci0_.tipo||'_'||matoperaci0_.posicion||'_'||matproduct3_.id||'_'||matbase6_.id||'_'||matoperaci0_.precio as col_0_0_,
                cuentaclie1_.nroCuenta as col_1_0_,
                cliente2_.razonSocial as col_2_0_,
                mercado5_.nombre as col_3_0_,
                matoperaci0_.condicion as col_4_0_,
                matoperaci0_.tipo as col_5_0_,
                comitente11_.nro_comitente as col_6_0_,
                matproduct3_.nombre as col_7_0_,
                matoperaci0_.posicion as col_8_0_,
                matbase6_.nombre as col_9_0_,
                unidaddeme4_.abreviatura as col_10_0_,
                case 
                    when 1=1
                    and (matproduct3_.operaVolumenPorBoleta is null 
                    or matproduct3_.operaVolumenPorBoleta=0 
                    or matoperaci0_.state_id is not null) then count(matregistr7_.id)*matproduct3_.volumenContrato 
                    else SUM(matoperaci0_.volumen) 
                end as col_11_0_,
                matoperaci0_.precio as col_12_0_,
                case 
                    when mercado5_.id=-6 then AVG(nvl(matoperaci0_.precioOPrima,
                    0)) 
                    else sum(matoperaci0_.precioOPrima*matoperaci0_.volumen)/sum(matoperaci0_.volumen) 
                end as col_13_0_ 
            from
                mat_operacion matoperaci0_ 
            inner join
                cuenta_cliente cuentaclie1_ 
                    on matoperaci0_.cuentaCliente_id=cuentaclie1_.id 
            inner join
                cliente cliente2_ 
                    on cuentaclie1_.cliente_id=cliente2_.id 
            inner join
                mat_producto matproduct3_ 
                    on matoperaci0_.matProducto_id=matproduct3_.id 
            inner join
                unidad_de_medida unidaddeme4_ 
                    on matproduct3_.unidadDeMedida_id=unidaddeme4_.id 
            inner join
                mercado mercado5_ 
                    on matoperaci0_.mercado_id=mercado5_.id 
            inner join
                mat_base matbase6_ 
                    on matoperaci0_.destino_id=matbase6_.id 
            inner join
                mat_registro matregistr7_ 
                    on matoperaci0_.id=matregistr7_.matOperacion_id 
            inner join
                workflow_state workflowst8_ 
                    on matregistr7_.state_id=workflowst8_.id 
            inner join
                workflow_definition workflowde9_ 
                    on workflowst8_.workflowDefinition_id=workflowde9_.id,
                comitente comitente11_,
                sys_config systemconf10_ 
            where
                matoperaci0_.comitente_id=comitente11_.id 
                and matoperaci0_.regristraZeni=1
                and (
                   null is null 
                 or cuentaclie1_.operadorDeMesa_id=null
                ) 
                and (
                    null is null 
                    or mercado5_.id=null
                ) 
                and mercado5_.id<>83095463 
                and (
                    null is null 
                    or cuentaclie1_.id=null
                ) 
                and (
                    null is null 
                    or cliente2_.id=null
                )
                and (
                    null is null 
                    or matoperaci0_.tipo=null
                ) 
                and (
                    null is null 
                    or cuentaclie1_.sucursal_id=null
                ) 
                and (
                    null is null 
                    or matoperaci0_.condicion=null
                ) 
                and (
                    null is null 
                    or matproduct3_.tipoProducto=null
                ) 
                and (
                    null is null 
                    or matproduct3_.id=null
                ) 
                and (
                    4 is null 
                    or matbase6_.id=4
                ) 
                and (
                    null is null 
                    or matoperaci0_.precio=null
                ) 
                and (
                    null is null 
                    or matoperaci0_.precioOPrima=null
                ) 
                and (
                    '' is null 
                    or matoperaci0_.posicion=''
                ) 
                and (
                    null is null 
                    or matoperaci0_.fecha>=null
                ) 
                and (
                    null is null 
                    or matoperaci0_.fecha<=null
                ) 
                and (
                    null is null 
                    or workflowde9_.estadoActual=null
                ) 
                and (
                    null is null 
                    or workflowst8_.fecha>=null
                ) 
                and (
                    null is null 
                    or workflowst8_.fecha<=null
                ) 
                and (
                    0=0 
                    or workflowde9_.estadoActual in (
                        'F' , 'C' , 'J' , 'E' , 'O' , 'OA' , 'AJ'
                    ) 
                    or workflowde9_.estadoActual='I' 
                    and matoperaci0_.fecha>=null 
                    and matoperaci0_.fecha<=null
                ) 
                and (
                    null is null 
                    or matregistr7_.nroRegistro=null
                ) 
                and (
                    null is null 
                    or matregistr7_.nroSubCtaReg=null
                ) 
                and systemconf10_.id='id_mercado_rofex' 
                and (
                    null is null 
                    or matoperaci0_.transfCartera=null
                ) 
            group by
                cuentaclie1_.id ,
                mercado5_.id ,
                matproduct3_.id ,
                matbase6_.id ,
                cuentaclie1_.nroCuenta ,
                cliente2_.razonSocial ,
                mercado5_.nombre ,
                matoperaci0_.condicion ,
                matoperaci0_.tipo ,
                comitente11_.nro_comitente ,
                matproduct3_.nombre ,
                matproduct3_.volumenContrato ,
                matoperaci0_.posicion ,
                matbase6_.nombre ,
                unidaddeme4_.abreviatura ,
                matoperaci0_.precio ,
                systemconf10_.value ,
                matproduct3_.operaVolumenPorBoleta ,
                matoperaci0_.state_id 
            order by
                cuentaclie1_.nroCuenta asc,
                mercado5_.nombre asc,
                matoperaci0_.condicion asc,
                matoperaci0_.tipo asc,
                matproduct3_.nombre asc,
                matoperaci0_.posicion asc,
                matbase6_.nombre asc
**************************************************************************************contratosPendienteFacturacion.html
	select
            distinct this_.id as y0_,
            this_.id as y1_,
            this_.cobroDeMercaderia as y2_,
            this_.operatoria as y3_,
            matcontrat3_.id as y4_,
            (select
                CANT_PDTE_FACTURAR(this_.id,
                this_.cobroDeMercaderia,
                this_.cantidadPactada,
                0) 
            from
                dual) as y5_,
            (SELECT
                count(*) 
            FROM
                entrega_partida ep1 
            INNER JOIN
                entrega_partida ep2 
                    on ep2.esCubiertaPor_id=ep1.id 
            WHERE
                ep1.contrato_id = this_.id) as y6_,
            ( (SELECT
                COUNT(af.id) 
            FROM
                anticipo_financiero af  
            WHERE
                af.contrato_id = this_.id  
                AND af.fechaBaja IS NULL 
                AND af.facturaProducto_id IS NULL) +  (SELECT
                COUNT(afc.id) 
            FROM
                anticipo_financiero_cancel afc  
            INNER JOIN
                anticipo_financiero af 
                    ON (
                        afc.anticipoFinanciero_id=af.id
                    )  
            WHERE
                af.contrato_id = this_.id  
                AND af.fechaBaja IS NULL 
                AND afc.fechaBaja IS NULL 
                AND afc.facturaProducto_id IS NULL )  ) as y7_,
            (select
                ep.userAlta 
            from
                entrega_partida ep 
            where
                ep.contrato_id = this_.id 
                and rownum=1) as y8_,
            this_.numeroContrato as y9_,
            vendedor4_.nroCuenta as y10_,
            vendedor_c5_.razonSocial as y11_,
            comprador6_.nroCuenta as y12_,
            comprador_7_.razonSocial as y13_,
            this_.cond_NegRealizaFacturacion as y14_,
            producto8_.nombre as y15_,
            monedafact9_.simbolo as y16_,
            this_.precioMercaderia as y17_,
            this_.cantidadPactada as y18_,
            unidaddeme10_.formato as y19_,
            (SELECT
                ROUND(COALESCE(SUM(f.cantidadAFijar),
                0),
                3) 
            FROM
                grupo_fijaciones gf,
                fijacion f,
                workflow_state ws,
                workflow_definition w 
            WHERE
                gf.contrato_id = this_.id 
                AND gf.fechaBaja is NULL 
                AND f.grupoFijaciones_id = gf.id 
                AND f.fechaBaja is NULL 
                AND f.state_id = ws.id 
                AND ws.workflowDefinition_id = w.id 
                AND w.estadoActual != 'A') as y20_,
            (SELECT
                ROUND(COALESCE(SUM(ep.cantidad),
                0),
                3)   
            FROM
                entrega_partida ep  
            INNER JOIN
                workflow_state ws 
                    ON ws.id = ep.state_id  
            INNER JOIN
                workflow_definition w 
                    ON w.id = ws.workflowDefinition_id  
            WHERE
                ep.fechaBaja is NULL 
                AND w.estadoActual != 'N' 
                AND ep.contrato_id = this_.id) as y21_,
            (CASE  
                WHEN 'A' = (SELECT
                    mc.representacion 
                FROM
                    mat_contrato mc 
                WHERE
                    mc.contrato_id = this_.id ) THEN   (ROUND(     (    (COALESCE(   (SELECT
                    SUM( fp.cantidadFacturada )         
                FROM
                    factura_producto fp          
                left join
                    tipo_comprobante tc 
                        on tc.id=fp.tipoComprobante_id         
                WHERE
                    fp.contrato_id = this_.id 
                    and upper(fp.facturador) not like '%FINAL%'          
                    and tc.abreviatura not in ('FA','ND','NC','LF','BF') 
                    and (tc.abreviatura <> 'FC' 
                    or fp.descontarCantidadFacturada = 1)            
                    and fp.fechaBaja is NULL 
                    and fp.anulador_id is null 
                    and fp.anulada_id is null )   ,
                0 ) )    -      (COALESCE(  (SELECT
                    MAX( SUM(fp.cantidadFacturada))         
                FROM
                    factura_producto fp          
                left join
                    tipo_comprobante tc 
                        on tc.id=fp.tipoComprobante_id         
                WHERE
                    fp.contrato_id = this_.id 
                    and upper(fp.facturador) not like '%FINAL%'          
                    and tc.abreviatura not in ('FA','ND','NC','LF','BF') 
                    and (tc.abreviatura <> 'FC' 
                    or fp.descontarCantidadFacturada = 1)          
                    and fp.fechaBaja is NULL 
                    and fp.anulador_id is null 
                    and fp.anulada_id is null 
                GROUP BY
                    fp.emisorMAT )  ,
                0 ) ) )  ,
                3 ) )  
                ELSE     (SELECT
                    ROUND(coalesce(sum(fp.cantidadFacturada),
                    0),
                    3)       
                FROM
                    factura_producto fp          
                left join
                    tipo_comprobante tc 
                        on tc.id=fp.tipoComprobante_id      
                WHERE
                    fp.contrato_id = this_.id          
                    and upper(fp.facturador) not like '%FINAL%'          
                    and tc.abreviatura not in (
                        'FA','ND','NC','LF','BF'
                    ) 
                    and (
                        tc.abreviatura <> 'FC' 
                        or fp.descontarCantidadFacturada = 1
                    )          
                    and fp.fechaBaja is NULL 
                    and fp.anulador_id is null          
                    and fp.anulada_id is null) 
            END ) as y22_,
            (select
                CANT_PDTE_FACTURAR(this_.id,
                this_.cobroDeMercaderia,
                this_.cantidadPactada,
                1) 
            from
                dual) as y23_,
            this_.cond_NegAnticipoFinanciero as y24_,
            this_.vencParcFechaCierta as y25_,
            this_.realizaCobranza as y26_,
            (select
                DOC_RESP_PARA_FACTURAR(this_.id) 
            from
                dual) as y27_,
            (SELECT
                wd.estadoDestino 
            FROM
                workflow_definition wd   
            WHERE
                wd.id = (
                    SELECT
                        ws.workflowDefinition_id 
                    FROM
                        workflow_state ws  
                    WHERE
                        ws.id = (
                            SELECT
                                b.state_id 
                            FROM
                                boleto b  
                            WHERE
                                b.fechaBaja is null    
                                AND b.contrato_id = this_.id
                        )
                    )
            ) as y28_, sucursalad11_.nombre as y29_, (
                SELECT
                    cc.comentario 
                FROM
                    comentario_contrato cc 
                WHERE
                    cc.subtipocomentario_id = 1746 
                    AND cc.id = (
                        SELECT
                            MAX(cc1.id) 
                        FROM
                            comentario_contrato cc1                     
                        WHERE
                            cc1.subtipocomentario_id = 1746 
                            AND cc1.contrato_id = this_.id
                    ) 
                    AND cc.contrato_id = this_.id 
                ) as y30_, vendedor_o12_.nombre as y31_, operadorde13_.nombre as y32_, state_work2_.estadoDestino as y33_, this_.crescere as y34_, this_.esCanje as y35_, this_.fechaOperacion as y36_ 
            from
                contrato this_ 
            left outer join
                workflow_state state1_ 
                    on this_.state_id=state1_.id 
            left outer join
                workflow_definition state_work2_ 
                    on state1_.workflowDefinition_id=state_work2_.id 
            left outer join
                cuenta_cliente comprador6_ 
                    on this_.comprador_id=comprador6_.id 
            left outer join
                cliente comprador_7_ 
                    on comprador6_.cliente_id=comprador_7_.id 
            left outer join
                moneda monedafact9_ 
                    on this_.monedaFacturacionMercaderia_id=monedafact9_.id 
            left outer join
                operador_de_mesa operadorde13_ 
                    on this_.operadorDeMesa_id=operadorde13_.id 
            left outer join
                producto producto8_ 
                    on this_.producto_id=producto8_.id 
            left outer join
                sucursal sucursalad11_ 
                    on this_.sucursalAdministrativa_id=sucursalad11_.id 
            left outer join
                unidad_de_medida unidaddeme10_ 
                    on this_.unidadDeMedida_id=unidaddeme10_.id 
            left outer join
                cuenta_cliente vendedor4_ 
                    on this_.vendedor_id=vendedor4_.id 
            left outer join
                cliente vendedor_c5_ 
                    on vendedor4_.cliente_id=vendedor_c5_.id 
            left outer join
                operador_de_mesa vendedor_o12_ 
                    on vendedor4_.operadorDeMesa_id=vendedor_o12_.id 
            left outer join
                mat_contrato matcontrat3_ 
                    on this_.id=matcontrat3_.contrato_id 
            where
                not state_work2_.estadoActual in (
                    '10', '60', '45'
                ) 
                and this_.cumplido=0 
                and this_.deDeposito=0 
                and this_.fechaBaja is null 
                and this_.padreHijo=0 
                and (
                    select
                        CANT_PDTE_FACTURAR(this_.id,
                        this_.cobroDeMercaderia,
                        this_.cantidadPactada,
                        1) 
                    from
                        dual
                )<>0.0 
            order by
                y8_ asc,
                y9_ asc 

	-------------------------------------------------------------------------------
	select
	        count(distinct this_.id) as y0_ 
	    from
	        contrato this_ 
	    left outer join
	        workflow_state state1_ 
	            on this_.state_id=state1_.id 
	    left outer join
	        workflow_definition state_work2_ 
	            on state1_.workflowDefinition_id=state_work2_.id 
	    where
	        not state_work2_.estadoActual in (
	            '10', '60', '45'
	        ) 
	        and this_.cumplido=0 
	        and this_.deDeposito=0 
	        and this_.fechaBaja is null 
	        and this_.padreHijo=0
	        and (
	            select
	                CANT_PDTE_FACTURAR(this_.id,
	                this_.cobroDeMercaderia,
	                this_.cantidadPactada,
	                1) 
	            from
	                dual
	        )<>0.0


	TRACE [http-8080-3] StringType.nullSafeSet(151) | binding '10' to parameter: 1
	TRACE [http-8080-3] StringType.nullSafeSet(151) | binding '60' to parameter: 2
	TRACE [http-8080-3] StringType.nullSafeSet(151) | binding '45' to parameter: 3
	TRACE [http-8080-3] BooleanType.nullSafeSet(151) | binding 'false' to parameter: 4
	TRACE [http-8080-3] BooleanType.nullSafeSet(151) | binding 'false' to parameter: 5
	TRACE [http-8080-3] BooleanType.nullSafeSet(151) | binding 'false' to parameter: 6
	TRACE [http-8080-3] DoubleType.nullSafeSet(151) | binding '0.0' to parameter: 7
************************************************************************************************************************Implementado




55.*********************************************************************************************************************
************************************************************************************Modificacion de BD Impresora Rosario
	Descripcion: Modificacion de impresora de rosario por base de datos
		Documentacion___________________________________________________________________________________________________
			http://10.2.0.201:631/printers/ROSARIO1116
			
			***struts-Facturacion.xml
			<action name="imprimirComprobantesFacturacion" class="com.zeni.app.webapp.action.ComprobanteReportAction" method="imprimirComprobantesFacturacion">
				<result name="impresionDesdeFacturadores" type="redirectAction">contratosPendienteFacturacion</result>
				<result name="contratosPendienteFacturacion" type="redirectAction">contratosPendienteFacturacion</result>
				<result name="impresionDesdeComprobantes">/WEB-INF/pages/comprobanteList.jsp</result>
				<result name="error">/WEB-INF/pages/login.jsp</result>
				<result name="ctoAnalisis" type="redirectAction">contratoAnalisis</result>
				<result name="debCred" type="redirectAction">editFacturacionDebitoCredito</result>
				<result name="anticipoFinanciero" type="redirectAction">
					<param name="actionName">anticiposFinancieros</param>
					<param name="from">cto</param>
				    <param name="idContrato">${idContrato}</param>
				</result>
				<result name="anulacion" type="redirectAction">
					<param name="actionName">editAnularComprobante</param>
					<param name="id">${factura.anulada.id}</param>
				</result>
				<result name="gastosEntrega" type="redirectAction">gastosEntregas</result>
			</action>

			***ComprobanteReportAction
				public String imprimirComprobantesFacturacion() throws PrintException {


			***JasperReportUtil.java
				@SuppressWarnings("unchecked")
				public static Impresora getImpresoraObj(String tipoComprobante) throws PrintException {
					HashMap<String, Object> queryParams = new HashMap<String, Object>();
					Sucursal suc = UserSecurityAdvice.getCurrentUser().getSucursal();
					queryParams.put("tipoComprobante", "%"+tipoComprobante+"%");
					queryParams.put("idSucursal", suc.getId());
					List<Impresora> impresoras = (List<Impresora>)getFacturacionManager().findByNamedQuery("impresora.Xprioridad", queryParams);
					if (impresoras == null || impresoras.size() == 0 || impresoras.get(0) == null)
						throw new PrintException("No hay impresora definida. Tipo comprobante: " + tipoComprobante + " -- Sucursal: " + suc.getNombre());

					return impresoras.get(0);
				}
			***Tabla impresora
				@Entity
				@Table(name="impresora")
				@NamedQueries( {
					@NamedQuery(name = "impresora.Xprioridad",
							    query = "SELECT i FROM Impresora i"
							    	 + " WHERE i.tipoComprobante like :tipoComprobante"
							    	 + " AND i.sucursal.id = :idSucursal"
							    	 + " AND i.activa = true"
							    	 + " ORDER BY i.prioridad")	
		Modificaciones_________________________________________________________________________________________________
			http://10.2.0.201:631/printers/ROS_FACTURACION

			  select * from sys_config where value like('%printer%')

			  update sys_config set value = 'http://10.2.0.201:631/printers/ROS_FACTURACION'
			  where id = 'impresoraRorario'
			  
			  select * from  sys_config where id = 'impresoraRorario'

			Incidencias_______________________________________________________________________________________
			No hubo cambios sigue imprimiendo por la misma impresora

				select * from sys_config where value like('%printer%')

				select * from impresora where id = -12

				SELECT i FROM Impresora i
				WHERE i.tipoComprobante like 
				AND i.sucursal.id = 
				AND i.activa = true
							    	 + " ORDER BY i.prioridad
				             
				update sys_config set value = 'http://10.2.0.201:631/printers/ROSARIO1116'
				where id = 'impresoraRorario'

				update impresora set URI = 'http://10.2.0.201:631/printers/ROS_FACTURACION'
				where id = -12

				select * from sucursal where id = 3733
************************************************************************************************************************Implementado



56.*********************************************************************************************************************
*************************************(Matias)Modificacion de las reglas de percepcion en caso de ser la pampa el destino
	Documentacion___________________________________________________________________________________________________
		http://10.2.1.60:9090/zeni/editReglaImpuesto.html?id=19

		***JexlModelNS.java

		Regla en la base de datos:


		if( model:AgentePercepcion($Vendedor, $Regla) ) {
		    ALICUOTA = 0;
		    if( model:IntervieneJurisdiccion($Factura, $Regla.provincia)
		    and not model:ConstanciaDeNoPercepcion($Comprador, $Regla.provincia, $FechaAcreditacionFactura) )
		        if( model:EsVendedorCargill($Factura) and model:DestinoMercaderia($Factura, $Regla.provincia))
		            ALICUOTA = 0.025;
		        else if(not model:EsVendedorCargill($Factura))
			    ALICUOTA = 0.025;
		        else
		            ALICUOTA = 0;

		    RESULT = $Importe * ALICUOTA;
		} else
		    0;


	Modificaciones_________________________________________________________________________________________________

		if( model:AgentePercepcion($Vendedor, $Regla) ) {
		    ALICUOTA = 0;
		    if( model:IntervieneJurisdiccion($Factura, $Regla.provincia)
		    and not model:ConstanciaDeNoPercepcion($Comprador, $Regla.provincia, $FechaAcreditacionFactura) )
		        if( model:EsVendedorCargill($Factura) and model:DestinoMercaderia($Factura, $Regla.provincia))
		            ALICUOTA = 0.025;
		        else if(not model:EsVendedorCargill($Factura) and model:DestinoMercaderia($Factura, $Regla.provincia))
			    	ALICUOTA = 0.025;
		        else
		            ALICUOTA = 0;

		    RESULT = $Importe * ALICUOTA;
		} else
		    0;
************************************************************************************************************************Implementado




57.*********************************************************************************************************************
********************************************************************************************************SIMAX 24(Debora)
 	El error se produce en el modulo
	Detalle del error: Buenas tardes, estamos mirando que la LPG del contrato 20/02573/1 trae como actividad “Comis./Consign.”, pero debería ser “Productor”.
	Se podrá ver de dónde surge el error?? El mismo ocasiona que el liquidador de retenciones de Ganancias traiga una alícuota incorrecta. 

	Documentacion___________________________________________________________________________________________________
		<!--FacturaContratoListAction-START -->
		<action name="facturasContrato"
			class="com.zeni.app.webapp.action.FacturaContratoListAction" method="list">
			<result>/WEB-INF/pages/facturaContratoList.jsp</result>
			<result name="cancel" type="redirectAction">${from}</result>
		</action>


		<action name="generarF1116BPDFDesdeBD"
			class="com.zeni.app.webapp.action.FacturacionAplicacionEntregasAction"
			method="generarF1116BPDFDesdeBD">
			<result name="success" type="noOp" />
		</action>

		***En el reporte me trae 
			"Actividad: " + $F{facturaProducto}.getContrato().getActividadComprador().getNombre().toUpperCase()

	***Nota: Se detecta que no es en el reporte el error, se cierra el incidente por no porder replicar el caso
************************************************************************************************************************Implementado



58.*********************************************************************************************************************
***********************************************************************************Ticket#1014704 — RV: Retenciones IIBB 
	Descripcion:(Matias)
	Tenemos una inconsistencia entre la Cuenta corriente y el comprobante generado por el liquidador de IIBB.
 
	El liquidador genero una retención de Santa Fe a una alícuota de 1,13% por $2065,06 que es incorrecta.
	Pero en la cuenta corriente del cliente lo calculo bien a una alícuota de 1,125% por $2055,92.
	 
	Necesitamos saber cuál fue el motivo para evitar que se sigan generando mal los comprobantes.

	Documentacion___________________________________________________________________________________________________
		***liquidacionIIBBList.jsp
			function retencionManual(fechaLiq){
		    	popupWin.openUrl('popupRetencionManual','retencionIIBBManualEdit.html?popUp=popupRetencionManual&decorator=popup&fechaLiquidacion='+fechaLiq);
		    }
		***struts-Facturacion.xml
			<action name="retencionIIBBManualEdit" class="com.zeni.app.webapp.action.RetencionIIBBManualAction" method="edit">
				<result>/WEB-INF/pages/retencionIIBBManualForm.jsp</result>
			</action>

	Modificaciones_________________________________________________________________________________________________
		***retencionIIBBManualForm.jsp
			Se modifico el metodo javascript onChangeField()
			function onChangeField(field){
				var d = field.value.toFloat();
				field.value = d.format(3);
				calculaDependientes();
			}
***********************************************************************************************************************Implementado




59.*********************************************************************************************************************
************************************************************************Ticket#1014872 — Tipo de Cambio facturación BYMA 
	Usuario 	--> BECHARA SANTIAGO		Mercaderías	Casa Central
	Ext			--> 5192
	Area		--> Mercaderías	Casa Central
	Descripcion:
	Al seleccionar el TC Banco Nación vendedor del día anterior para facturar las operaciones de BYMA lo toma pero al 
	realizar la liquidación lo remplaza por el TC Banco Nación comprador (como si estuviese parametrizado para que 
	facture con ese tipo de cambio).

	Documentacion___________________________________________________________________________________________________
	    <!--FacturacionMatAccion-START-->
		<action name="facturacionMatAcciones" class="com.zeni.app.webapp.action.FacturacionMatAccionAction" method="list">
			<result name="success">/WEB-INF/pages/facturacionMatAccion.jsp</result>
		</action>

		Nota: tiene un metodo de simular que le pega al 
			new Ajax.Request('<c:url value="/onSimularMatAccion.html"/>',

			<action name="onSimularMatAccion" class="com.zeni.app.webapp.action.FacturacionMatAccionAction" method="simular">
				<interceptor-ref name="jsonValidationWorkflowStack" />
				<result name="success" type="noOp"/>
			</action>

	Modificaciones_________________________________________________________________________________________________
		***asientoContableManagerImpl.java
		Se modifica el metodo genAsientoAcciones(), para que Se setea la cotizacion a la que contiene la factura
			acComun.setCotizacion(facturaProducto.getCotizacionImporte());
************************************************************************************************************************Implementado





60.*********************************************************************************************************************
************************************************************************************************Incidencia Nº 162(SIMAX)
	Usuario 	--> CENCIONE LUCRECIA	
	Ext			--> 5164		
	Area		--> Contaduría	Casa Central
	Descripcion:

	El error se produce en el modulo CRM
	Detalle del error: No podemos bajar los archivos que contienen las altas de clientes del mes de Diciembre para informar 
	a la UIF. Se adjunta pantalla con el error que figura en sistema. La severidad es alta porque debemos cumplir con el 
	regimen informativo mensual obligatorio dispuesto por la UIF.
	Muchas gracias! 

	Documentacion___________________________________________________________________________________________________
	***struts-CRM.xml
    <action name="generacionComitentesUif" class="com.zeni.app.webapp.action.GeneracionComitentesUifAction" method="edit">
        <result name="success">WEB-INF/pages/generacionComitentesUifForm.jsp</result>
        <result name="cancel">WEB-INF/pages/generacionComitentesUifForm.jsp</result>
     </action>
    ***generacionComitentesUifForm.jsp
     	<input type="button" class="button" onclick="javascript:imprimirXML()" value="<fmt:message key="button.generar"/>"/>

     	....
     	new Ajax.Request('<c:url value="/imprimirXMLUif.html"/>',...)
     	...

************************************************************************************************************************Implementado





61.*********************************************************************************************************************
************************************************************************************************Incidencia Nº 89 (SIMAX)
	Usuario 	--> TETI MATIAS	
	Ext			--> 5134		
	Area		--> Impuestos	Casa Central
	Descripcion___________________________________________________________________________________________________
		El error se produce en el modulo Impuestos/IIBB/Consultar
		Detalle del error: Me trajo para liquidar una retención de IIBB BsAs en el que los únicos campos que se pueden 
		modificar son el % de la base y la alícuota.
		Me trae un monto que toma como base Cero, por lo que si modifico la base o alícuota, va a seguir siendo cero.
		En la cuenta corriente del cliente, la liquidación tiene un monto de $53627,62, que es lo que me debería traer como base.
		Necesitamos saber porque trajo cero y si se puede hacer algo para poder generar la retención.

	Documentacion_________________________________________________________________________________________________
		/*<action name="liquidacionIIBBList" class="com.zeni.app.webapp.action.LiquidacionIIBBAction"
			method="list">
			<interceptor-ref name="jsonValidationWorkflowStack" />
			<result name="success">/WEB-INF/pages/liquidacionIIBBList.jsp</result>
		</action>*/

	    function retencionManual(fechaLiq){
	    	popupWin.openUrl('popupRetencionManual','retencionIIBBManualEdit.html?popUp=popupRetencionManual&decorator=popup&fechaLiquidacion='+fechaLiq);
	    }

	    <action name="retencionIIBBManualEdit" class="com.zeni.app.webapp.action.RetencionIIBBManualAction" method="edit">
			<result>/WEB-INF/pages/retencionIIBBManualForm.jsp</result>
		</action>

		Nota cuando se hace el checkrow por javascript se ejecuta el proceso procesaOpFactura('CRm');

		function procesaOpFactura(op){
       		new Ajax.Request('<c:url value="/retencionIIBBManualProcesaOperacion.html"/>',

       	<action name="procesaOperacionLiquidacionIIBB" class="com.zeni.app.webapp.action.LiquidacionIIBBAction"
				method="procesaOperacion">
			<result name="success" type="noOp" />
		</action>



			<action name="generarF1116BPDFDesdeBD"
				class="com.zeni.app.webapp.action.FacturacionAplicacionEntregasAction"
				method="generarF1116BPDFDesdeBD">
				<result name="success" type="noOp" />
			</action>
	Modificaciones_________________________________________________________________________________________________
		***Se modifico el metodo  getFacturadoACuentaRetIIBB, de la clase modelo FacturaProducto.java
			@SuppressWarnings("unchecked")
			@Transient
			public Double getFacturadoACuentaRetIIBB() {
				//Si es una Final calculo lo facturado de l BF y le resta lo facturado a cuenta de las BP (el facturado a cuenta es negativo, por eso se suma)
				if(getTipoComprobante().getAbreviatura().equals("BF")) {
					Double cantFact = cantidadFacturada==null?0:cantidadFacturada;
					Double precOper = precioOperacion==null?0:precioOperacion;
					WSLpgAjuste ajustes = f1116B.getWsLpgAjuste();
					long debito = ajustes.getwSLpgAjusteDebito().getId();
					long  credito = ajustes.getwSLpgAjusteCredito().getId();
					//List<WSLpgImporte> importesDeb = null;
					List<Object[]> importesDeb = null;
					String sql ="select importe, concepto, wslpgajustecd_id from ws_lpg_importe where WSLPGAJUSTECD_ID in ("+credito+","+debito+")";
					importesDeb = (List<Object[]>)getManager().executeSQL(sql);
					Double impDebito= 0.0;
					Double impCredito = 0.0;
					System.out.println(importesDeb.toString());
					if (importesDeb != null && importesDeb.size() > 0) {
						for (Object importe : importesDeb) {
							Object[] objectItem = (Object[]) importe;
							double importeAjuste = ((objectItem[0] != null) ? Double.valueOf(objectItem[0].toString()) : 0);
							String concepto = ((objectItem[1] != null)? objectItem[1].toString():null);
							long idAjuste = ((objectItem[2] != null) ? Long.valueOf(objectItem[2].toString()) : 0);
							//WSLpgImporte importe = (WSLpgImporte)importe1;
							if (idAjuste == credito && concepto.equals("Calidad/Varios")) {
								impCredito = importeAjuste ;
							}else if(idAjuste == debito && concepto.equals("Calidad/Varios")) {
								impDebito = importeAjuste;
							}
						}
					}			
					return (cantFact * precOper) + f1116B.getTotalFacturadoACuenta() - impCredito + impDebito;
				} else {
					return getFacturadoACuenta();
				}
			}
************************************************************************************************************************Implementado





62.*********************************************************************************************************************
************************************************************************************************Incidencia Nº122 (SIMAX)
	Usuario 	--> TETI MATIAS	
	Ext			--> 5134		
	Area		--> Impuestos	Casa Central
	Descripcion___________________________________________________________________________________________________
		El error se produce en el modulo Clientes/Impuestos Clientes
		Detalle del error:
		A cada cliente se le carga la alícuota según la categoría que tiene en la nómina 42-12. Para aquellos que 
		tienen 3-4-5-6 corresponde 0,75% y para los que tienen 1-2 o no aparecen en la misma corresponde 0%.
		Adjunto el caso de un cliente que tiene 0,75% pero tiene categoría 2 (debería ser 0%) 
	Documentacion_________________________________________________________________________________________________
		**Directorio de donde se toman los padrones de impuesto
			Z:\Padrones\__NOMINA_42_12
		**ruta
			clienteImpuestos.html?clienteId=28526

			<!--ClienteAction-START-->
	        <action name="clienteImpuestos" class="com.zeni.app.webapp.action.ClienteAction" method="editImpuestos">
				<result>/WEB-INF/pages/clienteImpuestosForm.jsp</result>
				<result name="error">/WEB-INF/pages/clienteList.jsp</result>
			</action>
		Nota: esta trayendo de forma correcta la alicuota grabada en la base de datos, por lo que se revisara la carga 
		de padrones
		**ruta:
			rg830_2681.html

			<action name="rg830_2681"
				class="com.zeni.app.webapp.action.ClienteSituacionGananciasAction" method="importarExclusionesStart">
	            <result>/WEB-INF/pages/clienteSituacionGananciasExclusiones.jsp</result>
			</action>
			***clienteSituacionGananciasExclusiones.jsp
				<TD align="right">
					<input type="button" style="width:150px; margin-right: 5px" class="button"
							onclick="importarPadrones()" value="Importar Padrones" title="Importa los Padrones RG4310, ARBA, CABA, TUCUMAN" /> 
				</TD>

				new Ajax.Request('<c:url value="/importarPadrones.html"/>',		{})

				<action name="importarPadrones"
					class="com.zeni.app.webapp.action.ClienteSituacionGananciasAction" method="importarPadrones">
					<result name="success" type="noOp"/>
				</action>

			***se configura una carpeta local como path en la base de datos
				select * from sys_config where id = 'upload_base_path'
					upload_base_path	/Zeni-DOC-Uploas-Prod-Zenitest

				***Prueba en produccion
					select * from sys_config where id = 'upload_base_path'
						upload_base_path	/Zeni-DOC-Uploas-Prod

				30580942373;2;DONHACHE S A

				"/__lock_access__"

				cuit: 30-58004382-4, importados: 629
				cuit: 30-58012912-5, importados: 630
				cuit: 30-58804951-1, importados: 631
	Nota_____________________________________________________________________________________________________
		Se encontro unas comillas que hacian que se tomaran varios registros como una cadena
************************************************************************************************************************Implementado




63.*********************************************************************************************************************
****************************************************************Ticket#1014881 — Ruralco Soluciones S.A boletos confirma
	Usuario 	--> FUX JAVIER
	Ext			--> 5012
	Area		--> Mercaderías	Casa Central
	Descripcion___________________________________________________________________________________________________
	Cuando vendedor este cliente para los boletos confirma, debe salir pago a zeni ( corredor ) hoy sale al vendedor 
	y están rechazando los boletos.

	Modificacion___________________________________________________________________________________________________
		***Se modifico el metodo makePagos() de la clase DocumentoDeNegocioReportAction.java, se quito laviladion para 
		cuando ruralco sea vendedor realice flujo normal
		if(ddn.getContrato().getVendedorEn("DOW") || isLaboratorio || ( ddn.getContrato().getVendedor().getNroCuenta() == 15386)){
			openCloseElement(hd, atts, "PagoAOrdenDe", "CodLista", "1", null);
		}else{
			openCloseElement(hd, atts, "PagoAOrdenDe", "CodLista", getCodLista("PagoAOrdenDe", null), null);				
		}
************************************************************************************************************************Implementado




64.*********************************************************************************************************************
*********************************************************************************Ticket#1014700 — Retenciones del Recibo
	Usuario 	--> TETI MATIAS	
	Ext			--> 5134		
	Area		--> Impuestos	Casa Central
	Descripcion___________________________________________________________________________________________________
		Necesitaría que en el módulo Cobranzas/Retenciones del recibo se agregue un estado más que sea Para Reclamar(Origen AI)(PR)) y Rechazada(Origen PR)(RR). 
		Además que el cuadro del listado pueda expandirse.
		Este vendría de la mano de la incidencia de Antonela Spezia para que cada retención esté vinculada con su comprobante.

	Documentacion___________________________________________________________________________________________________
		<!--ReciboRetencionAction-START-->
        <action name="reciboRetenciones" class="com.zeni.app.webapp.action.ReciboRetencionAction" method="list">
            <result>/WEB-INF/pages/reciboRetencionList.jsp</result>
        </action>

        ***El listado lo obtiene del metodo public List<WorkflowDefinition> getEstados(String entity) { --> workflowManagerImp,java

    Modificaciones_________________________________________________________________________________________________
    	SELECT * FROM Workflow_Definition w 
		WHERE w.entidad = 'ReciboRetencion' AND w.estadoActual <> '-'
		ORDER BY w.estadoActual

		select max(id) from workflow_definition

		insert into Workflow_Definition (id, useralta, ESTADOACTUAL,ENTIDAD,ESTADOORIGEN,ESTADODESTINO,DURACIONMAXIMA) 
		VALUES (34564901,'user' 'PR', 'ReciboRetencion', 'AI', 'Para Reclamar', 0);

		update Workflow_Definition set fechaalta = CURRENT_TIMESTAMP where id  = 34564901;

		update Workflow_Definition set estadodestino = 'Para Reclamar' where id  = 34564901;

		update Workflow_Definition set estadoactual = 'PR' where id  = 34564901;

		update Workflow_Definition set estadoorigen = 'AI' where id  = 34564901;

		update Workflow_Definition set sendemail = 0 where id  = 34564901;

		-----------------------------------------------------------------------------------------------------------
			insert into Workflow_Definition (id, useralta, ESTADOACTUAL,ENTIDAD,ESTADOORIGEN,ESTADODESTINO,DURACIONMAXIMA) 
			VALUES (34564902,'user' 'RR', 'ReciboRetencion', 'R', 'Rechazada', 0);

			update Workflow_Definition set fechaalta = CURRENT_TIMESTAMP where id  = 34564902;

			update Workflow_Definition set sendemail = 0 where id  = 34564902;


			--------------------------------------------------------------------------------

			update Workflow_Definition set estadoorigen = 'PR' where id  = -1402;
************************************************************************************************************************Implementado




65.*********************************************************************************************************************
***************************************************Ticket#1014159 — Valorizar CH y Pagarés en la consulta de operaciones
	Usuario 	--> BECHARA SANTIAGO		Mercaderías	Casa Central
	Ext			--> 5192
	Area		--> Mercaderías	Casa Central
	Descripcion___________________________________________________________________________________________________
		Necesitamos valorizar los pagarés y cheques en la consulta de operaciones del mercado de capitales.
		Para los documentos en pesos debe valorizar con el importe nominal a cobrar o pagar.
		Para los documentos en dólares debe valorizar con el último TC. BCRA 	
	Nota: Se necesita que se agregue una columna con la tasa del dia a la grilla, en precio actual debe ir el resultado 
	de la formula (volumen*tasa*tipo de cambio BancoCentral), esto debe aplicar solamente para dolares.

	Documentacion___________________________________________________________________________________________________
		<action name="matOperacionCapitalesResumen" class="com.zeni.app.webapp.action.MatOperacionCapitalesResumenAction" method="list">
			<interceptor-ref name="jsonValidationWorkflowStack" />
			<result>/WEB-INF/pages/matOperacionCapitalesResumen.jsp</result>
		</action> 

	Modificaciones_________________________________________________________________________________________________
		******Modificacion matOperacionCapitalesResumen.jsp
			***Se modifica columna id-->col_13_0_ de la grilla, la propiedad key
				<ria:simplegridcolumn label="Precio" width="100" key="($estadoActual == 'F' || $estadoActual == 'CC')?format:formatearDecimales(model:calcularPrecioPrima($idCtaCte,$idProducto,$precioOPrima),'#.#######'): (!$tipo.toString().equals('N') || ($tipo.toString().equals('N') && $volumen!=1)) ? format:formatearDecimales($precioOPrima,'#.#######') : ''" id="col_13_0_" style="text-align:right" />		
			
				***Implementacion:
					<ria:simplegridcolumn label="Precio" width="100" key="(($idMercado == -11) && ($monedaId == 1718)) ? format:formatearDecimales(model:calcularPrecioMAV($idOper),'#.#####') : ($estadoActual == 'F' || $estadoActual == 'CC') ? format:formatearDecimales(model:calcularPrecioPrima($idCtaCte,$idProducto,$precioOPrima),'#.#######') : (!$tipo.toString().equals('N') || ($tipo.toString().equals('N') && $volumen!=1)) ? format:formatearDecimales($precioOPrima,'#.#######') : '' " id="col_13_0_" style="text-align:right" />		
			***Se agrega columna para la tasa
				/*<ria:simplegridcolumn label="TasaBC" width="100" key="format:formatearDecimales(model:precioPrimaOper($idOper),'#.#####')" id="tasaBC" style="text-align:right" />*/
		

		*****Modificacion en JexlModelNS.java, se agregan metodos js para recuperar la tasa del banco centrar--> tasaBC($idOper) y metodo para calcula el precio  -->calcularPrecioMAV
				public Double calcularPrecioMAV(long idOper){
					String sql = "  select * from (" +
												"select c.promedio " +
												"from  moneda_cotizacion c " +
												"where c.moneda_id = 1718 " +
												"and c.cotizada_id = 1717 " +
												"and c.banco_id = 19558201 " +
												"and c.banco_id = 19558201 " +
												"and c.fechaBaja is null " +
												"order by fecha desc) " +
									"WHERE rownum <=1";
					Object obj = (Object) BaseModel.getManager().executeSQL(sql);
					Double precioMAV = ((obj != null) ? Double.valueOf(obj.toString().replace("[","").replace("]", "")) : 0);
					return tasaBC(idOper) * precioMAV;
				}
				
				public Double tasaBC(long idOper){
					MatOperacion obj1 = (MatOperacion) BaseModel.getManager().findById(MatOperacion.class, new Long(idOper));
					return obj1.getPrecioOPrima();
				}
		*****Modificacion en MatOperacion.java, Se agregan campos a la consulta @NamedQuery(name="matCapitalesResumen"(utlizada por el filter) para traer los datos de la grilla
			***Se modifican los datos del select
						   		 "		 mop.id as idOper, " +
			   		 "		 mop.moneda.id as monedaId" +
			***se modifica el GroupBy
			   			 " Group By cc.id, merc.id, prod.id, mop.matPartProvId, cc.nroCuenta, cli.razonSocial, merc.nombre, mop.tipo, mop.comitente.numero, " +
				   		 "		   prod.nombre, mop.posicion, um.abreviatura, prod.codigoInterno, prod.fechaVto, mop.fechaVto, mop.precio, wdf.estadoActual, mop.id, mop.moneda.id " +
		*****Modificacion matOperacionCapitalesResumen.jsp, Se solicita traer en la columna TotalValorizado $, traer el resultado de la formula (volumen * Precio)
			***Documentado antes del cambio:
				<ria:simplegridcolumn label="Total Valorizado $" width="120" key="(((($estadoActual == 'F' || $estadoActual == 'EG' || $estadoActual == 'CC') && !$tipo.toString().equals('N')) || (($estadoActual == 'F' || $estadoActual == 'EG' || $estadoActual == 'CC') && ($tipo.toString().equals('N') && $volumen!=1))) ? format:formatearDecimales(model:ultPrecioActivo($idProducto) * $volumen,'#.##'): '')" id="totalActual" style="text-align:right"/>
			***Modificacion:
				<ria:simplegridcolumn label="Total Valorizado $" width="120" key="(($idMercado == -11) && ($monedaId == 1718)) ? format:formatearDecimales(model:calcularPrecioMAV($idOper) * $volumen,'#.##') : (((($estadoActual == 'F' || $estadoActual == 'EG' || $estadoActual == 'CC') && !$tipo.toString().equals('N')) || (($estadoActual == 'F' || $estadoActual == 'EG' || $estadoActual == 'CC') && ($tipo.toString().equals('N') && $volumen!=1))) ? format:formatearDecimales(model:ultPrecioActivo($idProducto) * $volumen,'#.##'): '')" id="totalActual" style="text-align:right"/>		


		*****Modificacion matOperacionCapitalesResumen.jsp, Modificacion para mercado mav y es en pesos		
			***Documentado antes del cambio
				<ria:simplegridcolumn label="Total Valorizado $" width="120" key="(($idMercado == -11) && ($monedaId == 1718)) ? format:formatearDecimales(model:calcularPrecioMAV($idOper) * $volumen,'#.##') : (((($estadoActual == 'F' || $estadoActual == 'EG' || $estadoActual == 'CC') && !$tipo.toString().equals('N')) || (($estadoActual == 'F' || $estadoActual == 'EG' || $estadoActual == 'CC') && ($tipo.toString().equals('N') && $volumen!=1))) ? format:formatearDecimales(model:ultPrecioActivo($idProducto) * $volumen,'#.##'): '')" id="totalActual" style="text-align:right"/>		
			***Modificacio:
				<ria:simplegridcolumn label="Total Valorizado $" width="120" key="(($idMercado == -11) ? format:formatearDecimales(model:calcularPrecioMAV($idOper, $mopCond, $monedaId) * $volumen,'#.##') : (((($estadoActual == 'F' || $estadoActual == 'EG' || $estadoActual == 'CC') && !$tipo.toString().equals('N')) || (($estadoActual == 'F' || $estadoActual == 'EG' || $estadoActual == 'CC') && ($tipo.toString().equals('N') && $volumen!=1))) ? format:formatearDecimales(model:ultPrecioActivo($idProducto) * $volumen,'#.##'): '')" id="totalActual" style="text-align:right"/>		
			
			(($idMercado == -11) ? ($monedaId == 1718) ? 'Dolares' : ($mopCond == 'C') ? 'Compra' : 'Venta'  : 'Prueba' )

	Incidencia*****************************************************************************************************
		***Se modifica MatOperacion.java, 
			****Se modifica @NamedQuery, para que traiga totalValorizadoB, totalValorizado, tasaMov, los cuales van a ser utilizados en la vista
				 "		 SUM(CASE WHEN (mop.mercado = -11 and  mop.moneda = 1718) " +
				   		 "				  	THEN (mop.precioOPrima * mop.cotizacionDia ) " +
				   		 "				  	WHEN (mop.mercado = -11 and  mop.moneda = 1717)  " +
				   		 "					THEN (CASE  WHEN (mop.condicion = 'C') " +
				   		 "								THEN (mop.precioOPrima) " +
				   		 "								WHEN (mop.condicion = 'V') " +
				   		 "								THEN (mop.precioOPrima) " +
				   		 "							END)  " +
				   		 "				END) as totalValorizadoB, " +
				   		 "		 SUM(CASE WHEN (mop.mercado = -11 and  mop.moneda = 1718) " +
				   		 "				  	THEN (mop.precioOPrima * mop.cotizacionDia * mop.volumen) " +
				   		 "				  	WHEN (mop.mercado = -11 and  mop.moneda = 1717)  " +
				   		 "					THEN (CASE  WHEN (mop.condicion = 'C') " +
				   		 "								THEN (mop.volumen*mop.precioOPrima) " +
				   		 "								WHEN (mop.condicion = 'V') " +
				   		 "								THEN (mop.volumen) " +
				   		 "							END)  " +
				   		 "				END) as totalValorizado, " +
				   		 "		 SUM(CASE WHEN (mop.mercado = -11 and  mop.moneda = 1718) " +
				   		 "					THEN (mop.precioOPrima) " +
				   		 "					WHEN (mop.mercado = -11 and mop.moneda = 1717)" +
				   		 "					THEN (0) " +
				   		 "					END ) AS tasaMov, " +
			****Se crea atributo con formula para capturar la tasa del dia
					private Double cotizacionDia;
					@Formula("(CASE WHEN moneda_id=1718 THEN (select * from (select c.promedio from moneda_cotizacion c where " +
				    		" c.moneda_id = 1718 and c.cotizada_id = 1717 and c.banco_id = 19558201 and c.fechaBaja is null  order by c.promedio desc) where rownum = 1) ELSE 1 END)")
					@Basic(fetch=FetchType.LAZY)
					public Double getCotizacionDia() {
						if(fieldHandler != null)
							return (Double) fieldHandler.readObject(this, "cotizacionDia", cotizacionDia);
						return cotizacionDia;
					}
					public void setCotizacionDia(Double cotizacionDia) {
						if(fieldHandler != null)
							this.cotizacionDia = (Double)fieldHandler.writeObject(this, "cotizacionDia", this.cotizacionDia, cotizacionDia);
						else
							this.cotizacionDia = cotizacionDia;
					}
		****Se modifica la vista para que muestre matOperacionCapitalesResumen.jsp
			<ria:simplegridcolumn label="Precio Prima" width="100" key="(($idMercado == -11) && ($tasaMov != 0) ) ? format:formatearDecimales($tasaMov,'#.#####')  : ''" id="tasaBC" style="text-align:right" />
			<ria:simplegridcolumn label="Precio" width="100" key="(($idMercado == -11) && ($tasaMov != 0))  ? format:formatearDecimales($totalValorizadoB,'#.#####') : ($estadoActual == 'F' || $estadoActual == 'CC') ? format:formatearDecimales(model:calcularPrecioPrima($idCtaCte,$idProducto,$precioOPrima),'#.#######') : (!$tipo.toString().equals('N') || ($tipo.toString().equals('N') && $volumen!=1)) ? format:formatearDecimales($precioOPrima,'#.#######') : '' " id="col_13_0_" style="text-align:right" />		
			
			//<ria:simplegridcolumn label="Total Valorizado $" width="120" key="($idMercado == -11)  ? format:formatearDecimales($totalValorizado,'#.##') : (((($estadoActual == 'F' || $estadoActual == 'EG' || $estadoActual == 'CC') && !$tipo.toString().equals('N')) || (($estadoActual == 'F' || $estadoActual == 'EG' || $estadoActual == 'CC') && ($tipo.toString().equals('N') && $volumen!=1))) ? format:formatearDecimales(model:ultPrecioActivo($idProducto) * $volumen,'#.##'): '')" id="totalActual" style="text-align:right"/>		
************************************************************************************************************************Implementado





Reporte
66.*********************************************Incidencia Nº 183    Solicitante: Cencione Lucrecia ( ZENI > CONTADURIA)
	Usuario 	--> CENCIONE LUCRECIA	
	Ext			--> 5164		
	Area		--> Contaduría	Casa Central

	Descripcion___________________________________________________________________________________________________
		Necesitamos un reporte que nos informe si las cuentas 50.000 a 69999 tienen cargados cotitulares- Estos se 
		cargan en "clientes">> "Cuentas clientes">> "Cotitulares". Esto lo necesitamos para corroborar que todas 
		las cuentas los tengan cargados para luego tirar el proceso de cruce con listas de terroristas y peps.

	NOTA: Se requiere un reporte con las cuentas y los cotitulares por cada cuenta donde presente los siguientes campo:
		Cuenta
		Titular
		Cotitulares
		cuit
		tipo vinculacion
	Modificaciones_________________________________________________________________________________________________
	***menu-config.xml
		/*
		<Item name="PEPMenu" title="PEP" page="" width="200" roles="ROLE_ADMIN,ROLE_COMERCIAL">
        	<Item name="PEPProfilesMenu" title="Profiles" page="/pepProfile.html" width="150"/>
        	<Item name="PEPRevisionesMenu" title="Revisiones" page="/pepRevision.html" width="150"/>
        	<Item name="repCotitulares" title="Reporte Cotitulares" page="/repCotitulares.html" width="150"/>
        </Item>*/

    ***ApplicationResources.properties
        # -- Reporte de Cuentas Clientes Cotitulares
		rCCList.title=Reporte Consulta de Cuentas Clientes Cotitulares
		rCCFilter.filtro.title = Filtro - Reportes de Cuentas Clientes Cotitulares
		rCCList.heading = Reportes de Cuentas Clientes Cotitulares

    ***Creacion de clase Action repConsultaAction.java
		@Results({
			@Result(name = "success", location = "/WEB-INF/pages/repCotitularList.jsp")	 
			})
		public class RepCotitularesAction extends BaseAction implements Preparable{
			/**
			 * 
			 */
			private static final long serialVersionUID = -8984506146511307141L;

			private List<CuentaClienteCotitular> listaConsultaCO = null;
			private List<CuentaCliente> listaCliente = null;
			private UniversalManager manager;	
			private Date fechaDesde;
			private Date fechaHasta;
			private String tipoArchivo;
			

			public String getTipoArchivo() {
				return tipoArchivo;
			}

			public void setTipoArchivo(String tipoArchivo) {
				this.tipoArchivo = tipoArchivo;
			}

			public Date getFechaDesde() {
				return fechaDesde;
			}
			
			public void setFechaDesde(Date fechaDesde) {
				this.fechaDesde = fechaDesde;
			}


			public Date getFechaHasta() {
				return fechaHasta;
			}


			public void setFechaHasta(Date fechaHasta) {
				this.fechaHasta = fechaHasta;
			}


			public UniversalManager getManager() {
				return manager;
			}

			public void setManager(UniversalManager manager) {
				this.manager = manager;
			}

			public List<CuentaClienteCotitular> getListaConsultaCO() {
				return listaConsultaCO;
			}


			public void setListaConsultaCO(List<CuentaClienteCotitular> listaConsultaCO) {
				this.listaConsultaCO = listaConsultaCO;
			}
			
			public List<CuentaCliente> getListaCliente() {
				return listaCliente;
			}

			public void setListaCliente(List<CuentaCliente> listaCliente) {
				this.listaCliente = listaCliente;
			}
			


			@Action("repCotitular")
			public String execute() {
				return SUCCESS;
			}	
			
			@SuppressWarnings("unchecked")
			@Action("repCotitularesList")
			public String repConsultaCotitulares() {
				String query="";
				String query1="";
				String desde = (fechaDesde != null)? "AND cc.fechaAlta >= to_date('"+ DateUtil.getDate(fechaDesde) + "01:00:00','DD/MM/YYYY HH24:MI:SS') ":"";
				String hasta = (fechaHasta != null)? "AND cc.fechaAlta <= to_date('"+ DateUtil.getDate(fechaHasta) + "23:59:59','DD/MM/YYYY HH24:MI:SS') ":"";
				
				if(tipoArchivo.equals("2")){
					query = "SELECT ccc FROM CuentaClienteCotitular ccc " +
							"JOIN  ccc.cuentaCliente cc " +
							"WHERE cc.nroCuenta > 50000 AND cc.nroCuenta < 69999 "+desde+hasta;			
					query1 ="SELECT cc FROM CuentaClienteCotitular ccc " +
							"right join  ccc.cuentaCliente cc " +
							"WHERE cc.nroCuenta > 50000 AND cc.nroCuenta < 69999 " +
							"AND ccc.id is null "+desde+hasta;	 
					listaCliente = manager.find(query1);
					listaConsultaCO = manager.find(query);
					int i = 0;
					for (CuentaClienteCotitular item : listaConsultaCO) {
						
						if(item.getTipoVinculacion() != null){
							item.setTipoVinculacion(metodoDeInstancia(item.getTipoVinculacion()));				
						}
						if(item.getCuit() !=null){
							item.setCuit(item.getCuit().replace("-", ""));
						}
					}
				}else{
					query = "select cc from CuentaCliente cc   " +
							"where cc.nroCuenta > 0  "+desde+hasta;
					setListaCliente(manager.find(query));		
				}
				return SUCCESS;
			}
			
			public String metodoDeInstancia(String tipo){
				Map <String, String> values = PropertyValues.getTiposVinculos();
				return values.get(tipo);
			}


		}


	***Creacion de repCotitularList.jsp
		<%@ include file="/common/taglibs.jsp"%>
		/*
		<head>
			<title><fmt:message key="rCCList.title"/></title>
			<meta name="heading" content="<fmt:message key='rCCList.heading'/>"/>
			<meta name="filtroTitle" content="<fmt:message key='rCCFilter.filtro.title'/>"/>
			<meta name="filtro" content="/WEB-INF/pages/repCotitularesFiltro.jsp" />
		</head>

		<%@ include file="auditEntity.jspf" %>

		<s:if test="%{listaConsultaCO != null}">
		    <ria:simplegrid id="repoGrid"
							collection="${listaConsultaCO}"
							rowPerPage="${rowPerPage}"
							width="860px" height="400px"
							var="item"
							rowCountVar="rowCount"
							resizable="true"
							showPropertyButton="true"
							selectionMode="single"
							onSetData="gridNotifyOnEmpty(this, __Messages__.gridNoData)" 
							oldQueryMethod="false">
				<ria:simplegridcolumn label='Cuenta' key="${item.cuentaCliente.nroCuenta}" width="100" id="nroCuenta" style="text-align:center;paddind:10px;"/>
				<ria:simplegridcolumn label='Fecha Alta' key="${item.cuentaCliente.fechaAlta}" width="100" id="fechaAlta" style="text-align:center;paddind:10px;" formatter="formatFecha"/>
				<ria:simplegridcolumn label='Fecha Baja' key="${item.cuentaCliente.fechaBaja}" width="100" id="fechaBaja" style="text-align:center;paddind:10px;" formatter="formatFecha"/>
				<ria:simplegridcolumn label='Cliente' key="${item.cuentaCliente.denominacionCuenta}" width="250" id="Titular" style="text-align:center;paddind:10px;"/>
			    <ria:simplegridcolumn label='Cuit Cliente' key="${item.cliente.cuit}" width="100" id="cuit" style="text-align:center;paddind:10px;"/>
			    <ria:simplegridcolumn label='Nombre Co-Titular' key="${item.nombre}" width="150" id="nombreCoTitular" style="text-align:center; paddind:10px"/>
			    <ria:simplegridcolumn label='Apellido Co-Titular' key="${item.apellidoPaterno}" width="150" id="apellidoCoTitular" style="text-align:center; paddind:10px"/>
			    <ria:simplegridcolumn label='Cuit' key="${item.cuit}" width="100" id="cuit" style="text-align:center;paddind:10px;"/>
			    <ria:simplegridcolumn label='Documento' key="${item.nroDocumentoIdentidad}" width="100" id="documento" style="text-align:center;paddind:10px;"/>
			    <ria:simplegridcolumn label="Vinculacion"  key="${item.tipoVinculacion}" width="100" id="vinculacion" style="text-align:center;paddind:10px;" />
				<ria:gridinnerexport id="innerexport" filename="Contratos Pactados"/>
			</ria:simplegrid> 
		</s:if>
		<s:elseif test="%{listaCliente != null}">
				<ria:simplegrid id="repoGrid"
							collection="${listaCliente}"
							rowPerPage="${rowPerPage}"
							width="860px" height="400px"
							var="item"
							rowCountVar="rowCount"
							resizable="true"
							showPropertyButton="true"
							selectionMode="single"
							onSetData="gridNotifyOnEmpty(this, __Messages__.gridNoData)" 
							oldQueryMethod="false">
				<ria:simplegridcolumn label='Cuenta' key="${item.nroCuenta}" width="100" id="nroCuenta" style="text-align:center;paddind:10px;"/>
				<ria:simplegridcolumn label='Fecha Alta' key="${item.fechaAlta}" width="100" id="fechaAlta" style="text-align:center;paddind:10px;" formatter="formatFecha"/>
				<ria:simplegridcolumn label='Fecha Baja' key="${item.fechaBaja}" width="100" id="fechabaja" style="text-align:center;paddind:10px;" formatter="formatFecha"/>
				<ria:simplegridcolumn label='Cliente' key="${item.denominacionCuenta}" width="250" id="Titular" style="text-align:center;paddind:10px;"/>
				<ria:simplegridcolumn label='Cuit' key="${item.cliente.cuit}" width="100" id="cuit" style="text-align:center;paddind:10px;"/>
				<ria:gridinnerexport id="innerexport" filename="Contratos Pactados"/>
			</ria:simplegrid> 
		</s:elseif>
		<script type="text/javascript">
			function formatFecha(sFecha){
			        if(sFecha.indexOf('/')==-1){
					   var str =sFecha.split(' ')[0];
				       var str1 = str.split('-');	
				       return new Date(str1[0],str1[1]-1,str1[2]).format();
			        }else{
			          return sFecha;
			        }
				}
		</script>*/

	***Creacion de repCotitularesFiltro.jsp
		<%@ include file="/common/taglibs.jsp"%>
		<s:form id="repCotitularesFiltro" action="repCotitularesList"
			method="post" validate="true">
			
			<table style="margin: auto">		
					<tr>
					<td valign="top" style="padding-left: 12px"><sx:datetimepicker
							key="fechaDesde" label="Desde: " required="false" cssClass="text"
							displayFormat="%{datePattern}"
							templateCssPath="%{#datetimepickerCSS}" cssStyle="width:100px" />
					</td>
					<td valign="top" style="padding-left: 12px"><sx:datetimepicker
							key="fechaHasta" label="Hasta: " required="false" cssClass="text"
							displayFormat="%{datePattern}"
							templateCssPath="%{#datetimepickerCSS}" cssStyle="width:100px" />
					</td>
					 <td>
						 <s:select label="Tipo de Archivo" 
							headerKey="-1" 
							list="#{'1':'Clientes', '2':'Cotitulares'}" 
							name="tipoArchivo" 
							value="2" 
							cssStyle="width:100px"/>
					</td>
					<td style="padding-left: 25px; "><input type='button'
									style="width: 60px; margin-right: 5px; margin-left: auto; margin-right: auto; width: 80px; height: 25px; margin-top: 10px;"
									class="button" value="<fmt:message key="button.clearfilter"/>"
									onclick="clearForm(this.form);" />
					</td>
					<td style="padding-left: 25px;"><s:submit cssClass="button"
							style="width: 60px; margin-right: 5px; margin-left: auto;  margin-right: auto; width: 80px; height: 25px; margin-top: 10px;"
							action="repCotitularesList" key="button.filter" /></td>
				</tr>
			</table>

		</s:form>
************************************************************************************************************************Implementado  





67.**************************************************Incidencia Nº 182    Solicitante: Iezzi Debora ( ZENI > IMPUESTOS ) 
	Buenos días, en la cuenta corriente del cliente 4040 - Teodoro Torre figura duplicada la anulación de una retención 
	de ganancias liquidada por nosotros, por lo tanto el saldo que posee en la cuenta es incorrecto. Necesitamos que se 
	vea el caso y se corrija de forma urgente.
	Modificaciones_________________________________________________________________________________________________
		Se elimino el asiento duplicado
************************************************************************************************************************Implementado





68.**********************************************Incidencia Nº 203    Solicitante: De Fontes Nerea ( ZENI > CONTADURIA ) 
	Esta incidencia va de la mano con las modificaciones realizadas 
	para cerrar el requerimiento 183
************************************************************************************************************************Implementado




69.-**************************************************Incidencia Nº 196    Solicitante: Unno Juan ( ZENI > MERCADERIAS ) 
	Usuario 	--> UNNO JUAN	
	Ext			--> 6221		
	Area		--> Administración	Rosario	
	
	Descripcion: El error se produce en el modulo Cobranzas - Ingreso Manual Detalle del error: al querer guardar un recibo queda la pantalla en blanco.

	Incidencia solucionada
************************************************************************************************************************Implementado



Asientos Contables
70.-*******************************************Incidencia Nº 224    Solicitante: Rodriguez Augusto ( ZENI > CONTADURIA ) 
	Usuario 	--> Rodriguez Augusto	
	Ext			--> 5146		
	Area		--> Contaduria	
	
	Descripcion:Por favor asociar todos los movimientos que se realicen en el cliente 16596- con la cuenta contable 115009-
	DEUDORES POR SERVICIOS ROFEX CTA 397. Y todo lo que se facture en el cliente 16596- contra la cuenta contable DEUDORES 
	P/SERV INSCRIPTO ROFEX CTA 397 

	Documentacion___________________________________________________________________________________________________

		<action name="editAsientos" class="com.zeni.app.webapp.action.IngresoAsientosAction" method="edit">
            <result>/WEB-INF/pages/ingresoManualAsientosForm.jsp</result>
            <result name="error" type="redirectAction">ingresoAsientos</result>
        </action>

    Modificaciones_________________________________________________________________________________________________
    	Se modifica por base de datos la cuenta asociada al cliente
    		update sys_config set value ='43:201034;47:201036;80:201042;81:201040;84:201038;85:201044;90:201030;98:201032;397:115009;91:115031;15426:115033;16596:115009'
				where id = 'nroCtaCliente:nroCtaContable'
************************************************************************************************************************Por Implementacion*** BD




71.-*****************************************************Incidencia Nº 220    Solicitante: Meza Pablo ( ZENI > MERCADO ) 
	Usuario 	--> BECHARA SANTIAGO		Mercaderías	Casa Central
	Ext			--> 5192
	Area		--> Mercaderías	Casa Central
	Descripcion___________________________________________________________________________________________________	
		El error se produce en el modulo Facturador de registros CME
		Detalle del error: Para el producto ACEITE DE SOJA el resultado de cancelaciones y las primas sobre opciones se debe 
		dividir por 100 para obtener el valor correcto en dólares.
		Se adjunta ejemplo del 05-02-2020

	Documentacion_________________________________________________________________________________________________ 
		<!--FacturacionMatRegistro-START-->
		<action name="facturacionMatRegistros" class="com.zeni.app.webapp.action.FacturacionMatRegistroAction" method="list">
			<result name="success">/WEB-INF/pages/facturacionMatRegistro.jsp</result>
		</action>

		****Evento simular
			<action name="onSimularMatRegistro" class="com.zeni.app.webapp.action.FacturacionMatRegistroAction" method="simular">
				<interceptor-ref name="jsonValidationWorkflowStack" />
				<result name="success" type="noOp"/>
			</action>
	Modificaciones_________________________________________________________________________________________________

		Se modifica metodo esOperacionCME()  --> FacturacionMatRegistroManagerImp.java, para que tome en cuenta la unidad de medida LongBinaryOperator
			private boolean esOperacionCME(MatOperacion operacion) {
				//Indica que el precio esta en centavos y debe dividirse por 100
				String unidMed =  operacion.getMatProducto().getUnidadDeMedida().getAbreviatura();
				return ("BU".equals(unidMed) || "LB".equals(unidMed) );
			}
************************************************************************************************************************Implementado




72.-********************************************************************Ticket#1012758 — Ret. Ganancias Monotributistas
	Usuario 	--> Debora Iezzi - ZENI	
	Ext			--> 
	Area		--> 
	Descripcion_____________________________________________________________________________________________________
		Necesitamos que se realice una modificación en el liquidador de retenciones de ganancias para que no se generen 
		las mismas a clientes monotributistas.

	Documentacion___________________________________________________________________________________________________
		<action name="editCliente" class="com.zeni.app.webapp.action.ClienteAction"
			method="edit">
			<result>/WEB-INF/pages/clienteForm.jsp</result>
			<result name="error">/WEB-INF/pages/clienteList.jsp</result>
		</action>

		Nota: Para identificar si el cliente es monotributista tiene id -6 en la tabla de tipo_inscripto_impuesto

		***Proceso de generacion de provisoria

		<action name="liquidarGanancias" class="com.zeni.app.webapp.action.LiquidacionGananciasAction" method="edit">
			<result name="success">/WEB-INF/pages/liquidacionGanancias.jsp</result>
		</action>


		 function procesaOperacion(op, id){
	    	var popupFeedback = false;
			if(op.match(/P/)){
				popupFeedback = true;
	    		notificacionManager.stop();
	    		LogMessageWindow.startPopUp("logMessage");
	    	}
	       	new Ajax.Request('<c:url value="/procesaOperacionLiquidacionGanancias.html"/>',
	 				{ 
					  feedback : !popupFeedback,
	       			  parameters: {'fechaDesde': $N('fechaDesde').value, 'fechaHasta': $N('fechaHasta').value, 
	 							   'OP': op, 'id': id, 'autoretencion':$N('autoretencion').value},
	       			  onComplete: function(transport, json) {
							procesaJSONResult(json);
					    	if(popupFeedback){
								LogMessageWindow.end();
								notificacionManager.restart();
					    	}
	 			  	  }
	 			});
	    }


	    <action name="procesaOperacionLiquidacionGanancias" class="com.zeni.app.webapp.action.LiquidacionGananciasAction"
				method="procesaOperacion">
			<result name="success" type="noOp" />
		</action>

	Modificaciones_________________________________________________________________________________________________

	***Se modifica metodo en GananciaManagerImpl.java, para que no liquide en la provisoria a los clientes identificados 
	como monotributistas
		public LiquidacionGanancias liquidarGanancia(LogMessage lm, Date desde, Date hasta, Integer nroRetencionPuntoVta, Boolean autoretencion,CuentaCliente cuentaCliente) throws Exception {
			...
				if(cuenta.getCliente().getSituacionIVA().getId() != -6){
					...
				}	
			...
		}
***********************************************************************************************************************IMplementado




73.-*******************************************************Requerimiento de anulacion de Formulario Cheque diferido 699
	Documentacion___________________________________________________________________________________________________
		<action name="pedidoDeFondoDiferido" class="com.zeni.app.webapp.action.PedidoDeFondoDiferidoListAction" method="list">
			<interceptor-ref name="jsonValidationWorkflowStack" />
			<result>/WEB-INF/pages/pedidoDeFondoDiferidoList.jsp</result>
		</action>

	Modificaciones_________________________________________________________________________________________________

		Se modifico por base de datos la tabla pedidio de fondo
			select * from rel_cta_cont_cheque where nroformulario = '699'

			select * from pedido_de_fondo where id = 214392592

			update  pedido_de_fondo set nroformulariocheque = null where id = 214392592
***********************************************************************************************************************Implementado




74.-********************************************Incidencia Nº 232    Solicitante: De Fontes Nerea ( ZENI > CONTADURIA )	
	Descripcion___________________________________________________________________________________________________
		Enumeración correlativa para las alertas generadas por el sistema y se quiere que el modulo de crmConsultaAlertas 
		pueda ser accedido unicamente por el departamento de lucrecia


	Documentacion___________________________________________________________________________________________________
		select * from role_grant

		select r.name  from user_role ur
		join app_user appu on ur.user_id = appu.id 
		join role r on ur.role_id = r.id
		where ur.user_id = 160482078

		select * from app_user where username = 'lcencione'


		***Añadir columna de id a la grilla
			<action name="consultaAlertasCRM" class="com.zeni.app.webapp.action.CRMConsultaAlertasAction"
				method="list">
				<result>/WEB-INF/pages/crmConsultaAlertasList.jsp</result>
			</action>
	Modificaciones_________________________________________________________________________________________________
		*** Se realiza triger para que genere id consegutivo

			create trigger CRM_Alertas_trigger 
			before insert on crm_alerta 
			for each row 
			begin 
			select max(id)+1 into :new.id from crm_alerta; 
			end;
		***Se modifica grilla de la vista crmConsultaAlertasList.jsp, se añade columna para id
		 	<ria:simplegridcolumn label="Id Alerta" width="80" key="$item.id" id="id" sortOrder="1" sortAscendant="true"/>	
***********************************************************************************************************************IMplementado





75.-********************************************************************************Incidencia en reporte de CartaPorte
	Descripcion: 
	Se deben hacer las modificaciones para que el grado contenga 2 en caso de que no tenga grado

	Modificaciones_________________________________________________________________________________________________
		***ConsultaCartaPorteYPFAction.java --> metodo private void generarArcIPF(HSSFWorkbook libro, List<Map<Integer, Object[]>> listaT, List<List<String>>
																					arregloFilas, String tipo) throws IOException, Exception {	}
		//En caso de que grado este en blanco se setea 2, Interface de YPF acepta (1,2,3)
			if(j==9 && camara == 0){
				cell = fila.createCell(j);
				cell.setCellValue(2);
			}else if(j==10 && camara != 0){
				cell = fila.createCell(j);
				cell.setCellValue(2);
			}else{
				cell = fila.createCell(j);
			    cell.setCellValue(0);
			} 
***********************************************************************************************************************Implementado




76.-********************************************Incidencia Nº 229    Solicitante: De Fontes Nerea ( ZENI > CONTADURIA )
	Usuario 	--> NEREA DE FONTES	
	Ext			--> 5073	
	Area		--> Contaduría	Casa Central

	Descripcion:
		Por favor necesitamos un reporte con las operaciones mensuales de los clientes. Y que el mismo, se envíe 
		automaticamente a los clientes (también mensualmente).

	Documentacion________________________________________________________________________________________________

	SQL__________________________________________________________________________________________________________
			select TO_CHAR(mo.fecha,'DD/MM/YYYY'), mo.fechaliq, mo.condicion, mp.nombre, mo.preciooprima,mo.volumen, m.nombre, (mo.preciooprima*mo.volumen) as montoNeto 
			--select mo.id,rmf.*,fp.id 
			--select count(*)
			from mat_operacion mo
			inner join rel_matoperacion_factura rmf
			on mo.id = rmf.MATOPERACION_ID
			inner join factura_producto fp
			on rmf.FACTURA_ID = fp.id
			inner join tipo_comprobante tc
			on tc.id = fp.tipocomprobante_id
			left join mat_producto mp
			on mo.matproducto_id = mp.id
			left join moneda m
			on mo.moneda_id = m.id
			where mo.cuentacliente_id = -1--170408596
			and tc.abreviatura = 'LX'
			and mo.fecha >= to_date('01/03/2020 01:00:00','DD/MM/YYYY HH24:MI:SS')
			and mo.fecha <= to_date('31/03/2020 23:59:59','DD/MM/YYYY HH24:MI:SS')

			select * from factura_producto where id = 212457042
			select * from factura_producto where nrofactura = '1111-00123249'

	Modificaciones_________________________________________________________________________________________________
		*** Se modifica CuentaClienteManager.java,	Se agrega la firma del metodo getCtasCincuentaMil()
			List<CuentaCliente> getCtasCincuentaMil(Integer minCuenta, Integer maxCuenta);
		*** Se modifica CuentaClienteManagerImp.java, se crea metodo que crea una lista con las cuentas 50000
			public List<CuentaCliente> getCtasCincuentaMil(Integer minCuenta, Integer maxCuenta ) {
				Map<String, Object> queryParams = new HashMap<String, Object>();
				queryParams.put("minCuenta", minCuenta);
				queryParams.put("maxCuenta", maxCuenta);
				List<CuentaCliente> list = (List<CuentaCliente>) dao.executeNamedQuery(false,"ctasCincuentaMil", queryParams);
				return list;
			}
		*** Se modifica MatOperacionManager.java, se crea firma que recupera la lista de clientes y otra que recupera las operaciones
			public List<CuentaCliente> getCuentas(int min, int max);
			public List getOperaciones(long idCuenta, String fechaIni, String fechaFin);
		*** Se modifica MatOperacionManagerImpl.java
			** Se implementa el metodo getCuentas()
				public List<CuentaCliente> getCuentas(int min, int max){
					Map<String, Object> queryParams = new HashMap<String, Object>();
					queryParams.put("minCuenta", min);
					queryParams.put("maxCuenta", max);
					List<CuentaCliente> list = (List<CuentaCliente>) dao.executeNamedQuery(false,"ctasCincuentaMil", queryParams);
					return list;
				}
			** Se implementa el metodo getOperaciones()
				public List getOperaciones(long idCuenta, String fechaIni, String fechaFin)  {
					String query;
					List operaciones = null;
						query = "select TO_CHAR(mo.fecha, 'dd/mm/yyyy'), " +
								"				TO_CHAR(mo.fechaLiq, 'dd/mm/yyyy'), " +
								//"				mo.condicion, " +
								"				case when mo.condicion = 'C' then 'Compra' " +
								"      			when mo.condicion = 'V' then 'Venta' " +
								"				when mo.condicion = 'R' then 'Rescate' " +
								"				when mo.condicion = 'L' then 'Licitacion' " +
								"				when mo.condicion = 'S' then 'Suscripcion' " +
								"				end, " +
								"				mp.nombre, " +
								"				mo.precioOPrima," +
								"				mo.volumen, " +
								"				m.nombre, " +
								"				(mo.precioOPrima*mo.volumen), " +
								" 				fp.nroFactura " +
								"		from MatOperacion mo " +
								"		inner join mo.facturas fp " +
								"		inner join fp.tipoComprobante tc" +
								"		left join mo.matProducto mp " +
								"		left join mo.moneda m " +
								"where mo.cuentaCliente.id = "+idCuenta+
								" and tc.abreviatura = 'LX' " +
								" and mo.fecha >= to_date('"+fechaIni + "01:00:00','DD/MM/YYYY HH24:MI:SS') " +
								"and mo.fecha <= to_date('"+fechaFin+ "01:00:00','DD/MM/YYYY HH24:MI:SS') " +
								"and mo.fechaBaja is null ";
						operaciones = dao.find(query);
					return operaciones;
				}
		*** Se modifica CuentaCliente.java, se crea NamedQuery que devuelve las cuentas ctasCincuentaMil
			@NamedQuery(name="ctasCincuentaMil", 
		   	  query="SELECT cc FROM CuentaCliente cc join fetch cc.cliente where cc.nroCuenta >= :minCuenta and cc.nroCuenta <= :maxCuenta "),
		*** Se modifica CRMFichaClienteAction.java
			** Se crea metodo principal repCliOper()
				@SuppressWarnings("unused")
				public void repCliOper(){
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Calendar calendar = Calendar.getInstance();
					String fechaActual = sdf.format(calendar.getTime());
					calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
					calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
					String fechaIni = sdf.format(calendar.getTime());
					calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
					String fechaFin = sdf.format(calendar.getTime());
					getReportParams().put("reportParams.pFecha", fechaActual);
					getReportParams().put("reportParams.fechaIni", fechaIni);
					getReportParams().put("reportParams.fechaFin", fechaFin);
					try {
						List<CuentaCliente> cli = matOperacionManager.getCuentas(50000, 50003);
						String query = "select cfc.correoConvApertura from CRMFichaCliente cfc where cfc.cliente.id = :idCliente";
						String correoCli = "";
						for (CuentaCliente cc : cli) {
							correoCli = correoConvContrato(cc.getCliente().getId());
							List<Operaciones> operaciones = getOperaciones(cc.getId(),fechaIni, fechaFin);	
							if(operaciones.size()>0){
								getReportParams().put("reportParams.cliente", cc.getDenominacionCuenta());
								enviarInforme(operaciones, correoCli);
							}
						}	
					} catch (Exception e) {
						e.printStackTrace();
						addActionError("Error en la generacion del Archivo");
					}
				}
			** Se crea metodo que genera una lista de operaciones por cada cliente getOperaciones
				@SuppressWarnings("unchecked")
				public List<Operaciones> getOperaciones(Long idCuenta, String fechaIni, String fechaFin){
					List<Object[]> operaciones = matOperacionManager.getOperaciones(idCuenta, fechaIni, fechaFin);
					DecimalFormat df = new DecimalFormat("#.000");
					List<Operaciones> listOper = new LinkedList<CRMFichaClienteAction.Operaciones>();		
					for (Object[] matOperacion : operaciones) {	
						Operaciones operacion = new Operaciones();
						operacion.setFechaOper ((matOperacion[0] != null)? matOperacion[0].toString():null);
						operacion.setFechaLiq ((matOperacion[1] != null)? matOperacion[1].toString():null);
						operacion.setCondicion((matOperacion[2] != null)? matOperacion[2].toString():null);
						operacion.setProducto((matOperacion[3] != null)? matOperacion[3].toString():null);
						Double precio =(matOperacion[4] != null)? Double.valueOf(matOperacion[4].toString()):0;
						operacion.setPrecio(Double.valueOf(df.format(precio)));
						//operacion.setPrecio((matOperacion[4] != null)? Double.valueOf(matOperacion[4].toString()):0);
						operacion.setVolumen((matOperacion[5] != null)? Integer.valueOf(matOperacion[5].toString()):0);			
						operacion.setMoneda((matOperacion[6] != null)? matOperacion[6].toString():null);
						operacion.setMontoNeto((matOperacion[7] != null)? Double.valueOf(matOperacion[7].toString()):0);
						listOper.add(operacion);
					}
					return listOper;
				}
			** Se crea metodo que retorna el correo por cliente
				public String correoConvContrato(long idCliente){
					String query = "select cfc.correoConvApertura from CRMFichaCliente cfc where cfc.cliente.id = :idCliente";
					Map<String, Object> queryParams = new HashMap<String, Object>();
					queryParams.put("idCliente", idCliente);
					String correo = (String)matOperacionManager.findFirst(query, queryParams);		
					return correo;
				}
			** Se crea metodo que envia el mail
				public void enviarInforme(List  listOfMap, String correoCli){
					 String body = "Conforme las reglamentaciones de los mercados, la documentación " +
					 		"de respaldo de cada operación se encuententra a disposición del cliente. ";
					try {
						SendEMailUtil.SendEmail(mailEngine, correoCli, null, null, "Prueba", new Date(), 
								body,
								generarReporte(listOfMap), null, null, null, false);
						addActionMessage("los informes han sido enviados.");
					 } catch (Exception e) {
						 e.printStackTrace();
					 }
					
				}
			** Se crea metodo que genera el reporte
				public DataSource generarReporte(List  list) throws Exception {		
					DataSource dataSource = null;
					dataSource = JasperReportUtil.doReport_toMemory_timerTask(
							reportPath + "/reports/CRM/ReporteOperClientesMes.jasper",
							JasperReportUtil.FORMAT_PDF,
							getReportParams(),
							null, "clientes.pdf\"", 
							null, list, "JRBeanCollectionDataSource", "application/pdf");
					return  dataSource;
				}
			** Se crea clase para las operaciones
				public class Operaciones{
					private String fechaOper;
					private String fechaLiq;
					private String condicion;
					private Double precio;
					private Integer volumen;
					private String producto;
					private String moneda;
					private Double montoNeto;
					public Operaciones() {
						super();
					}
					
					
					public String getProducto() {
						return producto;
					}


					public void setProducto(String producto) {
						this.producto = producto;
					}


					public String getFechaOper() {
						return fechaOper;
					}
					public void setFechaOper(String fechaOper) {
						this.fechaOper = fechaOper;
					}
					public String getFechaLiq() {
						return fechaLiq;
					}
					public void setFechaLiq(String fechaLiq) {
						this.fechaLiq = fechaLiq;
					}
					public String getCondicion() {
						return condicion;
					}
					public void setCondicion(String condicion) {
						this.condicion = condicion;
					}
					public Double getPrecio() {
						return precio;
					}
					public void setPrecio(Double precio) {
						this.precio = precio;
					}
					public Integer getVolumen() {
						return volumen;
					}
					public void setVolumen(Integer volumen) {
						this.volumen = volumen;
					}
					public String getMoneda() {
						return moneda;
					}
					public void setMoneda(String moneda) {
						this.moneda = moneda;
					}
					public Double getMontoNeto() {
						return montoNeto;
					}
					public void setMontoNeto(Double montoNeto) {
						this.montoNeto = montoNeto;
					}
					@Override
					public String toString() {
						return "Operaciones [fechaOper=" + fechaOper + ", fechaLiq="
								+ fechaLiq + ", condicion=" + condicion + ", precio="
								+ precio + ", volumen=" + volumen + ", producto="
								+ producto + ", moneda=" + moneda + ", montoNeto="
								+ montoNeto + "]";
					}			
				}
		*** Se creo clase timerTask  RepRegInfTimerTask.java
				public class RepRegInfTimerTask extends AbstractAppTimerTask{

				@Override protected String getLogTitle(){return "timerTask de prueba";}
				@Override protected Date getActivationTime(){return makeActivationTime(15,04,0);}
				//@Override protected Long getPeriod(){return makePeriodInDays(24);};
				@Override protected Long getPeriod(){return makePeriodInMinutes(10);};
				UniversalManager manager = BaseModel.getManager();
				
				@Transactional
				@Override protected void runTask() {
					
					try {
						lm.DEBUG_LOG_TIMERTASK_START("ReporteOperacioneslientesMesTimerTask", "runTask");
						log.error("ReporteOperacioneslientesMesTimerTask - Inicio del timer");
						// Initialize ActionContext
				        ConfigurationManager configurationManager = new ConfigurationManager();
				        configurationManager.addContainerProvider(new XWorkConfigurationProvider());
				        Configuration config = configurationManager.getConfiguration();
				        Container container = config.getContainer();
				
				        ValueStack stack = container.getInstance(ValueStackFactory.class).createValueStack();
				        stack.getContext().put(ActionContext.CONTAINER, container);
				        ActionContext.setContext(new ActionContext(stack.getContext()));
				
				        ActionContext.getContext().setSession(new HashMap<String, Object>());
				        // populate the request so getRequest().getSession() doesn't fail in BaseAction.java
				        ServletActionContext.setRequest(new MockHttpServletRequest());
						lm.DEBUG_LOG_TIMERTASK("ActionContext Iniciado");
						log.error("ActionContext Iniciado");
						//ConsultaCartaPorteYPFAction action = new ConsultaCartaPorteYPFAction();
						CRMFichaClienteAction action = new CRMFichaClienteAction();
						UniversalManager makeManager = makeManager();
						ContratoManager contratoManager = (ContratoManager)makeBean("contratoManager");
						MatOperacionManager matOperacionManager = (MatOperacionManager)makeBean("matOperacionManager");
						Calendar calendar = Calendar.getInstance();
						calendar.set(calendar.MONTH, calendar.get(calendar.MONTH)-1);
						System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
						//Se valida que sea el tercer dia del mes para ejecutar
						if(calendar.get(5)==07/*ejecutarTimer(calendar, makeManager, this.getClass().getName()) && validacionMes(calendar)*/){
							action.setManager(makeManager);
							action.setMatOperacionManager(matOperacionManager);
							action.setReportPath(this.getReportPath());
							MailEngine me = (MailEngine)makeBean("mailEngine");
							action.setMailEngine(me);
							action.repCliOper();
							lm.DEBUG_LOG_TIMERTASK("Operaciones del mes");
							log.error("Se manda el correo con las operaciones del Cliente: " + calendar.getTime());									
						}	
					} catch (Exception e) {
						e.printStackTrace();
						lm.DEBUG_LOG_TIMERTASK(e);
					}
				}
				
				private Boolean validacionMes(Calendar fechaFin){
					if(fechaFin.DAY_OF_MONTH == 1){
						
					}
					boolean validacion = false;
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					SystemConfig fechaString = (SystemConfig)manager.findFirst(SystemConfig.class, 
																				new String[]{"id"}, 
																				new Object[]{"repClientesOper"});
					Calendar fechaInic = Calendar.getInstance();		
					try {
						fechaInic.setTime(sdf.parse(fechaString.getValue()));
						long diferencia = fechaFin.getTimeInMillis() - fechaInic.getTimeInMillis();
						double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
						if(dias > 30){				
							fechaString.setValue(sdf.format(fechaFin.getTime()));				
							manager.save(fechaString);
							validacion = true;
						}else{
							validacion = false;
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return validacion;		
				}
			}
***********************************************************************************************************************En Documentacion
	

	C:\workspace\Zeni\web\src\main\jasperreports\CRM

	C:\workspace\Zeni\web\src\main\webapp\reports\CRM\ReporteOperClientesMes.jasper
					
		SessionFactory sf = manager.getDao().getSes().getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
							
        Query query = session.createQuery("from CuentaCliente as cuenta ");
        query.setMaxResults(10);
        List<CuentaCliente> cli = query.list();
		session.getTransaction().commit();	
		contratosReportePDF(cli);
        enviarInforme(cli);

        // Reporte PDF de los contratos filtrados
		public String contratosReportePDF(List  listOfMap) {
			DataSource atachmentArr = null;
			try {
				getReportParams().put("reportParams.pFecha", DateUtil.getDateTime("d 'de' MMMM 'de' yyyy", new Date()));
				getReportParams().put("DATOS_ZENI",listOfMap);
				JasperReportUtil.doReport(getRequest(), 
						  getResponse(),
						  "/reports/CRM/ReporteOperClientesMes.jasper", 
						  JasperReportUtil.FORMAT_PDF,
						  getReportParams(), 
						  null, 
						  "attachment;filename=\"clientes.pdf\"",
						  null, 
						  null, 
						  null, 
						  null, 
						  listOfMap);		
			} catch (Exception e) {
				log.error(e);
			}
			return SUCCESS;
		}


		public DataSource generarReporte(List  list) throws Exception {
			return JasperReportUtil.doReport_toMemory(getRequest(),
				"/reports/CRM/ReporteOperClientesMes.jasper",
				JasperReportUtil.FORMAT_PDF, 
				getReportParams(), 
				null,
				"attachment;filename=\"clientes.pdf\"", 
				null, 
				list, 
				"JRBeanCollectionDataSource", 
				"application/pdf");
		}


		public void enviarInforme(List  listOfMap){
			 String body = "Prueba Email";
			try {
				SendEMailUtil.SendEmail(mailEngine, "eareiza@zeni.com.ar" , null, null ,"Prueba", new
						Date(), body, generarReporte(listOfMap));
				addActionMessage("los informes han sido enviados.");
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
			
		}
		10.1.10.102
									



77.-****************************************************Incidencia Nº 240    Solicitante: Meza Pablo ( ZENI > MERCADO )
	Se realizo rollback de los cambios del requerimiento Ticket#1014159 — Valorizar CH y Pagarés en la consulta de 
	operaciones, para solventar
***********************************************************************************************************************Implementado

	public DataSource generarReporte(List  list) throws Exception {
			return JasperReportUtil.doReport_toMemory(getRequest(),
				"/reports/CRM/ReporteOperClientesMes.jasper",
				JasperReportUtil.FORMAT_PDF, 
				getReportParams(), 
				null,
				"attachment;filename=\"clientes.pdf\"", 
				null, 
				list, 
				"JRBeanCollectionDataSource", 
				"application/pdf");
	}
		
		public void enviarInforme(List  listOfMap){
			 
			 //List lista= contratoManager.getAllRecordsPage(Contrato.class, -1, -1,
			 //null, getFilter());
			 Set<String> set = new HashSet<String>();



78.-*********************************************Incidencia Nº 252    Solicitante: Deacon Silvio ( ZENI > MERCADERIAS )
	Usuario 	--> DEACON SILVIO	
	Ext			--> 6225	
	Area		--> Administración	Rosario	

	Descripcion:	
	Urgente - Cambios en Excel para subir a pagina YPF, en el mismo hay que agregar algunos datos necesarios que no 
	incluye y el cliente pide con urgencia por pedido de sus productores. en tema Fernando Cavenaghi. Gracias

	Modificaciones_________________________________________________________________________________________________
		***Se modifica ConsultaCartaPorteYPFAction.java, para mapear n el excel la humedad, fumigado y zarandeo

			***** Se agregan managers 
				private MatOperacionManager matOperacionManager;
				private CuentaClienteManager cuentaClienteManager;
				public CuentaClienteManager getCuentaClienteManager() {
					return cuentaClienteManager;
				}

				public void setCuentaClienteManager(CuentaClienteManager cuentaClienteManager) {
					this.cuentaClienteManager = cuentaClienteManager;
				}

				public MatOperacionManager getMatOperacionManager() {
					return matOperacionManager;
				}

				public void setMatOperacionManager(MatOperacionManager matOperacionManager) {
					this.matOperacionManager = matOperacionManager;
				}
			***** Se modifica metodo generarArcIPF(), para que mapee en el excel la humedad, fumigado y zarandeo
				//se agrega validacion para que mapee en el excel humedad, zarandeo, fumigado
				{...}}else if(i <= 12) { 
					if(/*camara == 0 &&*/ i == 11){
						cell = fila.createCell(13);
						cell.setCellValue(Double.parseDouble(datos.get(12)));
						//cell = fila.createCell(14);
						//cell.setCellValue(Integer.parseInt(datos.get(7)));
					}
					cont++;
				}else if( i >= 13 && datos.size() > (cont+1)){...}
			***** Se modifica metodo arregloDatosCalidad(), para que acomode en el arrego el porcentaje de humedad, fumigado y zarandeo
				double porcZarandeo = ((objectItem[10] != null) ? (Double.valueOf(objectItem[10].toString())) : 0);
				double porcHumedad = ((objectItem[11] != null) ? (Double.valueOf(objectItem[11].toString())) : 0);
				double porcFumigado = ((objectItem[12] != null) ? (Double.valueOf(objectItem[12].toString())) : 0);
				
				arregloFila.add(String.valueOf(porcZarandeo));
				arregloFila.add(String.valueOf(porcHumedad));
				arregloFila.add(String.valueOf(porcFumigado));

			**** Se modifica metodo posicionRubro(), se agregan nuevos rubros
				mapCampos.put(429512,9);
				mapCampos.put(429474,102);
				mapCampos.put(429475,102);
				mapCampos.put(429567,177);
				mapCampos.put(429568,177);
				*** Se modifica metodo para que coloque la posicion 212 por default
					mapCampos.put(429603,202);	
					if(mapCampos.containsKey(idRubro)){
						return mapCampos.get(idRubro);	
					}else{
						return 212;
					}	
			*** se modifica metodo genDatosYPFCalidad(), se añade a la consulta la recuperacion del porcentaje de humedad, zarandeo, fumigado
				", e.PORCENTAJEZARANDEO, " +
				"e.PORCENTAJEHUMEDAD, " +
				"e.PORCENTAJEFUMIGADO " +

			*** Se modifica genAnalisisCalidad()
				//Si no tiene certificado busca analisis de Calidad en Calada
				if (datos.get(9).equals("0")) agregaCalidadCalada(datos);
			*** Se añade metodo agregaCalidadCalada()
				@SuppressWarnings({ "unchecked", "unused" })
				private List<String> agregaCalidadCalada(List<String> lista){
					String nroCartaPorte= lista.get(0).toString();
					try {			
						Map<String, Object> queryParams = new HashMap<String, Object>();
				        queryParams.put("nroCartaPorte", nroCartaPorte);
				        //NamedQuery implementado en MuetraCaladaYPF
				        List<Object[]> objetos = manager.findByNamedQuery("muestra.calidad", queryParams);
				        //Double analisisFactor=100D;
				        if (objetos.size()> 0){
				        	for (Object[] obj : objetos) {
				        		int idRubro = ((obj[0] != null) ? Integer.parseInt(obj[0].toString()) : 0);
								double resultado = ((obj[1] != null) ? (Double.valueOf(obj[1].toString())) : 0);
								double porcRebaja = ((obj[2] != null) ? (Double.valueOf(obj[2].toString())) : 0);
								Double mermaCant = ((obj[3] != null) ? (Double.valueOf(obj[3].toString())*1000) : 0);
								int merma = mermaCant.intValue();
								//analisisFactor += (idRubro == 429520 || idRubro == 429522) ? 0: porcRebaja;
								lista.add(String.valueOf(idRubro));
								lista.add(String.valueOf(resultado));
								lista.add(String.valueOf(porcRebaja));
								lista.add(String.valueOf(merma));
							}
				        	//lista.add(10, String.valueOf(analisisFactor));
				        }
					} catch (Exception e) {
						e.printStackTrace();
					}		
					return lista;
				}

		**** Se modifica EntregaAction.java
			*** Se agrega objeto Map que renderiza la lista de rubros en la vista 
				private Map <String, String> lista;
				 
			    public Map <String, String> getLista() {
			        return lista;
			    } 

			*** Se modifica metodo edit(), para que llene la lista creada
				lista = getRubrosYPF();
			*** Se crea metodo que genera la lista
				public Map <String, String> getRubrosYPF() {
			    	Map <String, String> values = new LinkedHashMap <String, String>();

			    	String sql = "select rubro.id, rubro.nombre from Concepto_Rubro_Calidad rubro" +
				     		"															where rubro.fechaBaja is null" +
				     		"															and rubro.codFinal is not null ";
			    	List <Object[]> lista =  (List<Object[]>) manager.executeSQL(sql);

				    for (Object[] item : lista) {
				    	if(PropertyValues.posicionRubro(Integer.valueOf(item[0].toString()))!=0)
				    		values.put(item[0].toString(), item[1].toString());
					}
			    	return values;
			    }
			*** Se crea metodo que responde al request de la vista y guarda la muestra
				@Action("envioDatosYPF")
				public void datosYPF(){
					String codRubro = getRequest().getParameter("codRubro");
					Double porcBon = Double.valueOf(getRequest().getParameter("porcBon"));
					Double resultado = Double.valueOf(getRequest().getParameter("resultado"));
					Double cantidad = Double.valueOf(getRequest().getParameter("cantidad"));
					String idEntrega = getRequest().getParameter("entregaId");
			        ConceptoRubroCalidad rubro = (ConceptoRubroCalidad) manager.findById(ConceptoRubroCalidad.class, new Long(codRubro));
			    	Entrega entrega = (Entrega) manager.findById(Entrega.class, new Long(idEntrega));
					MuestraCaladaYPF muestra = new MuestraCaladaYPF();
					muestra.setEntrega(entrega);
					muestra.setConceptoRubroCalidad(rubro);
					muestra.setResultado(resultado);
					muestra.setMuestraCantidad(cantidad);
					muestra.setPorcentajeBonificacion(porcBon);	
					manager.save(muestra);
				}
		-----------------------------------------------------------------------------------------------------------
		**** Se realizan ajustes para incorporar al excel el analisis de calidad en -Calada
			*** Se crea entidad MuestraCaladaYPF.java
				@Entity
				@Table(name = "MUESTRA_CALADA_YPF")
				@NamedQueries({
				    @NamedQuery(name = "muestra.calidad", query = "SELECT m.conceptoRubroCalidad.id, " +
				    		"												m.resultado, " +
				    		"												m.porcentajeBonificacion, " +
				    		"												m.muestraCantidad " +
				    		"										FROM MuestraCaladaYPF m " +
				    		"										INNER JOIN m.entrega e " +
				    		"										where e.nroCartaDePorte =:nroCartaPorte " )
				})
				public class MuestraCaladaYPF extends BaseModel implements Serializable{

					private static final long serialVersionUID = 2574365697280452011L;

					@Column(name = "RESULTADO", unique = false, nullable = false)
					private Double resultado;
					@Column(name = "PORCENTAJEBONIFICACION", unique = false, nullable = false)
					private Double porcentajeBonificacion = 0D; 
					@Column(name = "MUESTRACANTIDAD", unique = false, nullable = false)
					private Double muestraCantidad;
					
					private Entrega entrega;
					private ConceptoRubroCalidad conceptoRubroCalidad;	
					
					
					@ManyToOne(optional=false, fetch = FetchType.LAZY)
					public Entrega getEntrega() {
						return entrega;
					}

					public void setEntrega(Entrega entrega) {
						this.entrega = entrega;
					}

					public Double getResultado() {
						return resultado;
					}

					public void setResultado(Double resultado) {
						this.resultado = resultado;
					}

					
					public Double getPorcentajeBonificacion() {
						return porcentajeBonificacion;
					}

					public void setPorcentajeBonificacion(Double porcentajeBonificacion) {
						this.porcentajeBonificacion = porcentajeBonificacion;
					}

					@Column
					public Double getMuestraCantidad() {
						return muestraCantidad;
					}

					public void setMuestraCantidad(Double muestraCantidad) {
						this.muestraCantidad = muestraCantidad;
					}

					@ManyToOne(optional=false, fetch = FetchType.LAZY)
					public ConceptoRubroCalidad getConceptoRubroCalidad() {
						return conceptoRubroCalidad;
					}

					public void setConceptoRubroCalidad(ConceptoRubroCalidad conceptoRubroCalidad) {
						this.conceptoRubroCalidad = conceptoRubroCalidad;
					}

					public MuestraCaladaYPF() {
						super();
					}

					@Override
					public boolean bussinessEquals(BaseModel other) {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public int bussinessHashCode() {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public String toString() {
						// TODO Auto-generated method stub
						return null;
					}

				}
		*** Se modifica el archivo hibernate.cfg.xml, se mapea la entidad creada
			<mapping class="com.zeni.app.model.MuestraCaladaYPF"/>
		*** Se modifica editEntrega.jsp, Se crean los campor para ingresar los datos en MuestraCaladaYPF
				<tr>
					<td colspan="2"><s:checkbox id="cbYPF" key=""
							cssClass="checkbox" theme="simple" /> <s:label
							for="cbYPF"
							value="YPF" 
							cssClass="choice desc" theme="simple" />
					</td>
					<td valign="middle" ><input type='button'
							style="width: 50px; margin-right: 5px; margin-left: auto; margin-right: auto; height: 25px; display: none; "
							class="button" id="btnAgregar" onclick="javascript:onYPF();" value="<fmt:message key="button.ca.add"/>"	 />
					</td>
				</tr>
				<tr id="sectorYPF" style="visibility: hidden">
						<td align="left" valign="top"><s:select key="dependencia" id="rubro1" name="rubro1"
								list="lista" 
								required="false" emptyOption="true" label="Rubro" cssClass="text medium"  /> 
						</td>
						<td><s:textfield id="porcRub1" label="% Rebaja"
								key="" required="false"
								cssClass="currency size50" 
								onchange="javascript:formatXDecimales(this,2);calcularKgBonif()" />
						</td>
						<td><s:textfield id="medRub1"  label="Medicion"
								key="" required="false"
								cssClass="currency size50" />
						</td>
						<td><s:textfield id="kgRub1" label="Kg. Rebaja."
								key="" required="false" 
								cssClass="currency size50" readonly="false"/>
						</td>
				</tr>
		
		
		
		*** Se crea metodo javaScript en editEntrega.jsp, para entrar la insercion de datos, mediante el checkbox

				//Se valida si se registran datos de calidad en calada YPF
				var checkbox = document.getElementById('cbYPF');
				checkbox.addEventListener("change", validaCheckbox, false);
				
				function validaCheckbox(){
				  var checked = checkbox.checked;
				  if(checked){
				    alert('checkbox esta seleccionado');
				    let boton = document.getElementById("btnAgregar");
					boton.style.display = 'block';
				 	var sector = document.getElementById("sectorYPF");
				 	sector.style.visibility = 'visible'	;
				  }else{
					alert('checkbox esta deseleccionada');
				    let boton = document.getElementById("btnAgregar");
					boton.style.display = 'none';
				 	var sector = document.getElementById("sectorYPF");
				 	sector.style.visibility = 'hidden'	;
				  }
				}

		*** Se crea metodo ajax en editEntrega.jsp, para enviar los datos a matRegistroExportarArchivoLiqFinal
					function onYPF(){
						if(!confirm("¿Está seguro de ingresar el rubro?"))
					 		return;
						
						var rubro =  document.getElementById('rubro1').value;
						alert(rubro)
						var porcentaje = document.getElementById('porcRub1').value.replace(",",".");
						var medida = document.getElementById('medRub1').value;
						var kilos = document.getElementById('kgRub1').value.replace(",",".");
						if(rubro == ""){
							alert('Debe ingresar el rubro.')
						}else{
							if(porcentaje == ""){
								alert('Debe ingresar el porcentaje.')
							}else if(medida == ""){
								alert('Debe ingresar valor en medicion.')
							}else{
								new Ajax.Request('<c:url value="/envioDatosYPF.html"/>',
										  { parameters: {					  
												codRubro: rubro,
												porcBon: porcentaje,
												resultado: medida,
												cantidad:kilos,
												entregaId: $N("entrega.id").value
											  },
										    onComplete: function(transport, json) {
													alert("El Rubro fue registrado.");
										    }
										  });
							}
						}
					}

		*** Se crea metodo en editEntrega.jsp, para el formateo y calculo de datos
				function calcularKgBonif(){
					//Capturo valor del campo porcentaje
					var porcentaje = document.getElementById('porcRub1').value.replace(",",".");
					//Inicializo variable result
					var result="";
					//Se valida si tiene porcentaje 
					if( porcentaje != "" ){
						//Captura la cantidadEntrega
						var cantEntrega = document.getElementById('cantidadEntrega').value == "" ? "0" : document.getElementById('cantidadEntrega').value.replace(",",".");
						//Calcula la merma 
						result = formatToUnidad(parseFloat(cantEntrega) * parseFloat(porcentaje) / 100);	
					}
					//Se le asigna a la merma 
					document.getElementById('kgRub1').value = result.replace(".",",");
				}	

		***Insercion en la base de datos
			CREATE TABLE "ZENIAPP"."MUESTRA_CALADA_YPF" 
				   (	"ID" NUMBER(19,0) NOT NULL ENABLE, 
					"FECHAALTA" TIMESTAMP (6), 
					"FECHABAJA" TIMESTAMP (6), 
					"FECHAMODIF" TIMESTAMP (6), 
					"STATE_ID" NUMBER(19,0), 
					"USERALTA" VARCHAR2(50 BYTE), 
					"USERBAJA" VARCHAR2(50 BYTE), 
					"USERMODIF" VARCHAR2(50 BYTE), 
					"RESULTADO" FLOAT(126), 
					"PORCENTAJEBONIFICACION" FLOAT(126), 
					"MUESTRACANTIDAD" FLOAT(126), 
					"ENTREGA_ID" NUMBER(19,0), 
					"CONCEPTORUBROCALIDAD_ID" NUMBER(19,0)
				   ) SEGMENT CREATION IMMEDIATE 
				  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
				 NOCOMPRESS LOGGING
				  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
				  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
				  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
				  TABLESPACE "ZENI" ;
***********************************************************************************************************************Implementado




78.-*****************************************************Ticket#1013719 — Solicitud por Retenciones Ganancias Generadas 
	Usuario 	--> SOLA FLORENCIA	
	Ext			--> 6219		
	Area		--> Administración	Rosario

	Descripcion:
	El sistema está calculando retenciones de ganancias sobre comprobantes que no generan acreditaciones en la Cta Cte.
 
	En el caso de Noelma por ejemplo, el valor de mercadería se aplica como pago directo en algunos casos. Sin embargo 
	tiempo después, cuando envían tanto retenciones como pago de CBU se aplican como recibo de IVA (No pago Directo). 
	Tanto el primer recibo como el segundo no generan saldos a favor en la Cta de los clientes y el sistema está generando 
	la retencion de ganancias que no corresponde.
 	
 	Nota: E usuario indico que no se esta presentando incidencia, se procedio a cerrar la incidencia
***********************************************************************************************************************Implementado



79.-**********************************************Nerea modificar combo en clientes vista, para que muestre fideicomiso
	Se añade item de fideicomiso por una interface --> Configuracion --> Tipo de setConceptoRubroCalidad
	afecta la tabla tipo sociedad	
***********************************************************************************************************************Implementado
 

80.-**********************************************************************filtro operador en cuporIndividualesList.html
	Usuario 	--> Diego Liñan 	
	Ext			--> 6219		
	Area		--> Administración	Rosario

	Descripcion:
		Se necesita incluir el operador como filtro en la consulta cuporIndividualesList.html

	Documentacion___________________________________________________________________________________________________
	***Struts.xml
		<action name="cuposIndividualesList" class="com.zeni.app.webapp.action.CupoListAction" method="list">
	        <result>/WEB-INF/pages/cuposIndividualesList.jsp</result>
	    </action>
	***menu-config.xml
		<Item name="CupoAAnulacionMenu" title="cupoAAnulacion.heading" page="/cuposIndividualesList.html" width="250"/>
	***CupoListAction.java
	***CupoAgrupador.java
	***cupoFiltro.jsp

	Nota: en primera instancia debo añadir el operador de mesa OPERADORDEMESA_ID del contrato
	***Filtro expresion
		{fechaDesde=(fechaAsignacion >= Mon May 04 10:37:07 ART 2020), 
		cantidadAnuladosMayorCero=(cupoAgrupador.cantidadAnulados > null), 
		ctaVendedorTxt=(cupoAgrupador.contrato.vendedor.cliente.razonSocial ilike null), 
		fechaHasta=(fechaAsignacion <= null), 
		compradorId=(cupoAgrupador.contrato.comprador.id = null), 
		vendedorId=(cupoAgrupador.contrato.vendedor.id = null), 
		contratoId=(cupoAgrupador.contrato.id = null), 
		OR={AND0={numeroContrato=(cupoAgrupador.contrato.numeroContrato = null)}, AND1={numeroContrato=(cupoAgrupador.contratosCubiertos.numeroContrato = null)}}, 
		cantidadOtorgadosMayorCero=(cupoAgrupador.cantidadOtorgados > null), 
		estado=(estado != null), 
		lugarTxt=(cupoAgrupador.lugar.nombre ilike null), 
		cumplido=(cumplido = null), 
		productoId=(cupoAgrupador.producto.id = null), 
		lugarId=(cupoAgrupador.lugar.id = null), 
		nroCuentaVendedor=(cupoAgrupador.contrato.vendedor.nroCuenta = null)}

	Modificaciones_________________________________________________________________________________________________
		***cupoFiltro.jsp,Agregar combo de operador
			<td align="left" valign="top"><appfuse:valuelist id="operador"
				modelEntity="OperadorDeMesa" entityAttrName="nombre" idAttr="filter.operadorId"
				nameAttr="filter.operadorTxt" 
				nameLabel="filter.operadorDeMesaId" width="300" height="350"
				title="filter.operadorDeMesaId" idColumnLabel="lugar.id"
				nameColumnLabel="lugar.nombre" nameColumnLabelWidth="200"
				readonly="false" required="false"/></td>
			<td>

			Nota se pidio una modificacion para cambiar el obj appfuse por un select 

				<td align="left" valign="top">
					<s:action	name="operadorDeMesas" var="operadorDeMesas" /> 
					<s:select
					key="filter.operadorId" label="Operador Vendedor"
					list="%{#operadorDeMesas.tiposOperadoresDeMesaVigentes}"
					listKey="id" listValue="nombre" emptyOption="true"
					required="false" cssClass="text medium" />
				</td>

		***CupoListAction.java, se agregan atributos al filter, con su get y set
			addCondition("cupoAgrupador.contrato.vendedor.operadorDeMesa.id", "=", "operadorId");
			addCondition("cupoAgrupador.contrato.vendedor.operadorDeMesa.nombre", "ilike", "operadorTxt");

			Se elimina una condicion del filtro
				
			//addCondition("cupoAgrupador.contrato.vendedor.operadorDeMesa.nombre", "ilike", "operadorTxt");

			public Long getOperadorId(){
				return (Long)get("operadorId");
			}
			public void setOperadorId(Long v) {
				set("operadorId", v);
			}
			public String getOperadorTxt(){
				return (String)get("operadorTxt");
			}	    
			public void setOperadorTxt(String v) {
				set("operadorTxt", v);
			}
***********************************************************************************************************************Implementado


81.-*************************************************************************Cupos otorgados pendientes | Modificacion.
	Usuario 	--> Diego Liñan 	
	Ext			--> 	
	Area		--> 

	Descripcion:
		
		Actualmente en el campo “otorgados pendientes” de la consulta analítica, toma los cupos otorgados desde mañana, 
		y no considera los de hoy.
		Este es el dato q toma Leo para la columna “otorgados”.
		Hablando con Atilio, creemos q deberia considerar los del dia de la consulta tambien.

		SUR AGROP
		Para este cto tiene 12 cupos otorgados.
		Pero en el campo “otorgados pendientes” aparecen solo 9, ya q hoy tiene 3 cupos.
		 
		A la hora de decidir cupear, el comercial debe saber q hoy tiene 3 cupos tambien.

		Caso de prueba: 20/11982/9
			consultaAnaliticaDeContratos.html
	
	Documentacion__________________________________________________________________________________________________
		***struts.xml
			<!--ConsultaAnaliticaDeContratos-START-->
	        <action name="consultaAnaliticaDeContratos" class="com.zeni.app.webapp.action.ConsultaAnaliticaDeContratosAction" method="list">
	        	<interceptor-ref name="jsonValidationWorkflowStack" />
	            <result>/WEB-INF/pages/consultaAnaliticaDeContratosList.jsp</result>
	        </action>
        *** consultaAnaliticaDeContratosList.jsp
        	<meta name="filtro" 	 content="/WEB-INF/pages/consultaAnaliticaDeContratosFiltro.jsp" />

        	<ria:simplegridcolumn label="Otorgados Pendientes" width="150" style="font-size:12px;text-align:center;" 
							key="(model:cuposOtorgadosPorContrato($item.id)>0)?model:cuposOtorgadosPorContrato($item.id):''"/>
		*** jexlModelINS.java
			public Long cuposOtorgadosPorContrato(Long idContrato){
				...
				List<Long> listTotalCuposOtorgados = (List<Long>)BaseModel.getManager().executeNamedQuery(false, "cuposOtorgadosTotal", params);
			}
		*** Cupo.java
			@NamedQueries({
					@NamedQuery(name = "cuposOtorgadosTotal", query = "SELECT COUNT(c.id) FROM Cupo c "
							+ "WHERE c.cupoAgrupador.contrato.id = :idContrato "
							+ "AND c.estado = :estado " + "AND c.fechaAsignacion > :fecha"),
					@NamedQuery(name = "cuposOtorgadosTodos", query = "SELECT COUNT(c.id) FROM Cupo c "
							+ "WHERE c.cupoAgrupador.contrato.id = :idContrato "
							+ "AND c.estado = :estado ") })
	Modificaciones_________________________________________________________________________________________________
		*** Cupo.java, se modifica la consulta para que busque fechaAsignacion >= al dia
			@NamedQuery(name = "cuposOtorgadosTotal", query = "SELECT COUNT(c.id) FROM Cupo c "
							+ "WHERE c.cupoAgrupador.contrato.id = :idContrato "
							+ "AND c.estado = :estado " + "AND c.fechaAsignacion >= :fecha"),
***********************************************************************************************************************Implementado



82.-*************************************************************************Cupos otorgados pendientes | Modificacion.
	Usuario 	--> Devora 	
	Ext			--> 	
	Area		--> 

	Descripcion:
	Detalle del problema: Buenos días, el sistema está calculando retenciones de ganancias sobre comprobantes que no 
	generan acreditaciones en la Cta Cte. En el caso de Noelma, por ejemplo, el valor de mercadería se aplica como pago 
	directo en algunos casos. Sin embargo, tiempo después, cuando envían tanto retenciones como pago de CBU se aplican 
	como recibo de IVA (No pago Directo). Tanto el primer recibo como el segundo no generan saldos a favor en la Cta de 
	los clientes y el sistema está generando la retención de ganancias que no corresponde. Adjunto un mail con un caso, 
	con la incidencia original enviada por Sebastián Senno, y paso un ejemplo actual. 

	Documentacion__________________________________________________________________________________________________

	Nota: Se cierra la incidencia, La Sra Florencia indica que no se esta presentando la incidencia.
***********************************************************************************************************************Cerrada



83.-******************************************************************Simax N° 146 fecha cobranza FacturaGastosSellados.
	Usuario 	--> Nicolas Agtonelli
	Ext			--> 	
	Area		--> 

	Descripcion:
	Agregar fecha de cobranza de factura como una última columna en reporte "Consulta Detalle Factura Gastos Sellado" 
	Recibirá futuras notificaciones a medida que avance su solicitud.


	Documentacion__________________________________________________________________________________________________

		<action name="consultaFacturaGastosSelladoes"
			class="com.zeni.app.webapp.action.DetalleFacturaGastosSelladoConsultaAction"
			method="list">
			<result>/WEB-INF/pages/detalleFacturaGastosSelladoConsulta.jsp</result>
		</action>
	Modificaciones_________________________________________________________________________________________________
		***jexlModelINS.java, se crea metodo para buscar fecha de cobro en la consulta de gastos de sellado
				public String fechaCobro(Long idDetalleFacturaGastosSellado){
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String fechaCobro = "";
					if(idDetalleFacturaGastosSellado == null){
						return fechaCobro;
					}
					@SuppressWarnings("unchecked")
					List<GastoRecuperar> fpList = 
						(List<GastoRecuperar>)BaseModel.find(GastoRecuperar.class, 
												new String[]{"detalleFacturaGastosSellado.id","fechaBaja"}, 
												new Object[]{idDetalleFacturaGastosSellado, null});
					for(GastoRecuperar fp : fpList){

						if(fp.getFactura() != null){
							Integer nroFactSuc = fp.getFactura().getNroFacturaSucursal();
							Long nroFacturaNum = fp.getFactura().getNroFacturaNumero();
							Long idTipoComp = fp.getFactura().getTipoComprobante().getId();
							Character letraComp = fp.getFactura().getLetra();
							
							@SuppressWarnings("unchecked")
							List<Movimiento> movimientos = 
									(List<Movimiento>)BaseModel.find(Movimiento.class, 
															new String[]{"pvta","nro","tipoComprobante.id","letra"}, 
															new Object[]{nroFactSuc, nroFacturaNum, idTipoComp, letraComp});
							for(Movimiento mov : movimientos){
								if(mov.getFecha() != null){
									if(mov.getFecha().compareTo(fp.getFactura().getFechaFactura())==0 || mov.getFecha().after(fp.getFactura().getFechaFactura())){
										fechaCobro = sdf.format(mov.getFecha());
									}else{
										fechaCobro = "";
									}
									break;
								}
							}
						}
					}
					return fechaCobro;
				}
				
		*** Se modifica detalleFacturaGastosSelladoConsulta.jsp, se agrega a la grilla columna de fecha de cobro, se recupera de metodo del jexlModelINS.java
			<ria:simplegridcolumn label="Fecha Cobranza" width="120" key="model:fechaCobro($item.id)" id="fechaCobranza" style="text-align:center;" sortable="false"/>
***********************************************************************************************************************Implementado



84.-*****************************************************************************Simax N°350 Error en modulo Carta Porte
	Usuario 	--> Fernando Rossi 	
	Ext			--> 	
	Area		--> 

	Descripcion:
	El error se produce en el modulo aplicaciones cartas de porte. impacta en los informes diarios de interfaces a laboratorios
	Detalle del error:
		Te comento lo que está sucediendo con la generación de los archivos de fmc para ver si se puede solucionar estos problemas.

		Pongo como ejemplo las cp. 84843873 / 84995475, el sistema no levanta ningún dato.

		En todas las demás cartas de porte, ejemplo 84443479, el error es que toma como Intermediario al laboratorio 
		( fmc) y como ref. comercial al intermediario ( insumos agroquímicos).


	
	Documentacion___________________________________________________________________________________________________

	 Notas:
	 	Hay errores en las cargas de la merma de las entregas
	 	errores al cargar el excel carta Porte   --> Se esta presentando porque no tiene entreg adicional cargada
	 	nombre del cargador debe ser el productor o quien Inicializo
	 	nombre del recibidor debe ser el exportador o donde termina la mercaderia
	 	nombre del ref comercial debe ser el intermediario o nieto

	**** struts-Facturacion.xml
		<action name="editExportacionArchivos"
			class="com.zeni.app.webapp.action.ExportacionArchivosAction" method="edit">
			<interceptor-ref name="jsonValidationWorkflowStack" />
			<result>WEB-INF/pages/exportacionArchivosForm.jsp</result>
			<result name="error">WEB-INF/pages/exportacionArchivosForm.jsp</result>
		</action>

		<action name="exportacionArchivos"
			class="com.zeni.app.webapp.action.ExportacionArchivosAction" method="save">
			<interceptor-ref name="jsonValidationWorkflowStack" />
			<result name="success">WEB-INF/pages/exportacionArchivosForm.jsp</result>
			<result name="cancel">WEB-INF/pages/exportacionArchivosForm.jsp</result>
		</action>

		<action name="mandoCartasPorteXls" class="com.zeni.app.webapp.action.ExportacionArchivosAction" method="mandoCartasPorteXls">
			<result name="success">WEB-INF/pages/exportacionArchivosForm.jsp</result>
		</action>

	*** exportacionArchivosForm.jsp

	****Punto dos: modificacion de la entrega
		editEntregaContrato.html

		<action name="editEntregaDatAdicionales" class="com.zeni.app.webapp.action.EntDatAdicionalesAction" method="edit">
			<result name="input">/WEB-INF/pages/entregaDatosAdicionalesForm.jsp</result>
            <result>/WEB-INF/pages/entregaDatosAdicionalesForm.jsp</result>
        </action>

        var url = "editEntregaDatAdicionales.html?popUp=popupDatosAdicionales&decorator=popup";

    Modificaciones_________________________________________________________________________________________________

    ***EntDatAdicionalesAction.java

    	public EntregaDatosAdicionales completaDatosAdic(Entrega entrega){
		 	    EntregaDatosAdicionales entregaDatosAdicionales = new EntregaDatosAdicionales();
		 	    entregaDatosAdicionales.setEmbalajeGrano("G".charAt(0));
		        entregaDatosAdicionales.setMovimiento("I".charAt(0));
		        //Entregador por default es Amato Y Catalano id: -9
		        entregaDatosAdicionales.setEntregador(getEntregadorById(-9L));
		        entregaDatosAdicionales.setEntrega(entrega);
		 	   	//Por default el total neto es igual a la cantidad de la entrega
		 	   	entregaDatosAdicionales.setTotalNeto(entrega.getCantidadEntrega());
		 	    setEntregaDatosAdicionales(entregaDatosAdicionales);
		 	   	//completa datos adicionales de los contratos
		 	   	fillDatosadicionales(entrega.getEntregasPartida());
		    	//Seteo de campos de auditoria
		        signalSave(getEntregaDatosAdicionales());
		        entregaDatosAdicionales = (EntregaDatosAdicionales) manager.save(entregaDatosAdicionales);
		        //entrega.setEntregaDatosAdicionales(entregaDatosAdicionales);
		    	return entregaDatosAdicionales;
		    }
		    
		    @Action("replicaDatAdic")
			public void replicaDatAdic(){
				String idEntrega = getRequest().getParameter("entregaId");
				entregaDatosAdicionales = (EntregaDatosAdicionales)manager.findFirst(EntregaDatosAdicionales.class, new String[]{"entrega.id"}, new Object[]{new Long(idEntrega)});
		//		if(entregaDatosAdicionales == null){
		//			Entrega entrega = (Entrega) manager.findById(Entrega.class, new Long(idEntrega));
		//			completaDatosAdic(entrega);
		//		}
				if(entregaDatosAdicionales != null){
					Double otrasMermas = Double.valueOf(getRequest().getParameter("otrasMermas"));
					Double mermaPorZarandeo = Double.valueOf(getRequest().getParameter("mermaPorZarandeo"));
					Double mermaPorHumedad = Double.valueOf(getRequest().getParameter("mermaPorHumedad"));
					Double mermaPorFumigado = Double.valueOf(getRequest().getParameter("mermaPorFumigado"));
					Double cantidadEntregada = Double.valueOf(getRequest().getParameter("cantidadEntregada"));
					Double cantidadNeta = Double.valueOf(getRequest().getParameter("cantidadNeta"));
					String nroCartaPorte = getRequest().getParameter("nroCartaPorte");
					String fechaDescarga = getRequest().getParameter("fechaDescarga");	
					Double totalMermas = mermaPorZarandeo + mermaPorHumedad + mermaPorFumigado + otrasMermas;
					entregaDatosAdicionales.setTotalMermas(totalMermas);
					entregaDatosAdicionales.setTotalNeto(cantidadNeta);
					signalSave(getEntregaDatosAdicionales());
			        manager.save(entregaDatosAdicionales);
				}		
			}
    

    	***Se le realizaron modificaciones al metodo fillDatosadicionales(), para que modifique de manera correcta el intermediario y ref comercial en caso de contratos nieto
    		if(contratos.size()<2){
				entregaDatosAdicionales.setNombreRefComercial(entregaPadre.getContrato().getComprador().getCliente().getRazonSocial());
				entregaDatosAdicionales.setCuitRefComercial(entregaPadre.getContrato().getComprador().getCliente().getCuit());
				if(contratos.size()==1){
					entregaDatosAdicionales.setNombreIntermediario(contratos.get(contratos.size()-1).getVendedor().getCliente().getRazonSocial());
					entregaDatosAdicionales.setCuitIntermediario(contratos.get(contratos.size()-1).getVendedor().getCliente().getCuit());
				}
			}else{
				...
			}

    ***ExportacionArchivosAction.java
		if (Util.isEmpty(bm.getEntrega().getEntregaDatosAdicionales())) {
    	   EntDatAdicionalesAction entAdic= new EntDatAdicionalesAction();
    	   //EntregaDatosAdicionales entregaDatosAdicionales = new EntregaDatosAdicionales();
    	   //entregaDatosAdicionales.setEmbalajeGrano("G".charAt(0));
           //entregaDatosAdicionales.setMovimiento("I".charAt(0));
           //Entregador por default es Amato Y Catalano id: -9
           entAdic.setManager(manager);
           entAdic.setAplicacionManager(aplicacionManager);
           //entregaDatosAdicionales.setEntregador(entAdic.getEntregadorById(-9L));
           //entregaDatosAdicionales.setEntrega(bm.getEntrega());
    	   //Por default el total neto es igual a la cantidad de la entrega
    	   //entregaDatosAdicionales.setTotalNeto(bm.getEntrega().getCantidadEntrega());
    	   //entAdic.setEntregaDatosAdicionales(entregaDatosAdicionales);
    	   //completa datos adicionales de los contratos
    	   //entAdic.fillDatosadicionales(bm.getEntrega().getEntregasPartida());
       	   //Seteo de campos de auditoria
           //signalSave(entAdic.getEntregaDatosAdicionales());
           //entregaDatosAdicionales = (EntregaDatosAdicionales) entAdic.getManager().save(entregaDatosAdicionales);
           //bm.getEntrega().setEntregaDatosAdicionales(entregaDatosAdicionales);
           bm.getEntrega().setEntregaDatosAdicionales(entAdic.completaDatosAdic(bm.getEntrega()));
       }

    *** editEntrega.jsp
       ***Se agrega id al boton
       			key="entrega.edicion.finalizarButton" theme="simple" id ="btnSave"/>


       	***Se añade evento de boton, manda datos de modificacion de mermas y las modifica en la entidad EntregaDatosAdicionales
       	var btnGuardar = document.getElementById('btnSave');
	
		btnGuardar.addEventListener('click', saludar);
		
		function saludar(){
			var mermaPorHumedad = document.getElementById('mermaPorHumedad').value== ""? "0" : document.getElementById('mermaPorHumedad').value.replace(",",".");
			var otrasMermas = document.getElementById('otrasMermas').value == "" ? "0" : document.getElementById('otrasMermas').value.replace(",",".");
			var cantZarandeo = document.getElementById('mermaPorZarandeo').value == "" ? "0" : document.getElementById('mermaPorZarandeo').value.replace(",",".");  
			var cantFumigado = document.getElementById('mermaPorFumigado').value == "" ? "0" : document.getElementById('mermaPorFumigado').value.replace(",","."); 
			var cantEntrega = document.getElementById('cantidadEntrega').value == "" ? "0" : document.getElementById('cantidadEntrega').value.replace(",",".");
			var cartaPorte = document.getElementById('tfNroCartaDePorte').value;
			var cantidadNeta = document.getElementById('cantidadNeta').value == "" ? "0" : document.getElementById('cantidadNeta').value.replace(",",".");
			var fechaDescarga = dojo.widget.byId("fechaDescarga").getValue();
			new Ajax.Request('<c:url value="/replicaDatAdic.html"/>',
					  { parameters: {			
						    mermaPorZarandeo : cantZarandeo,
						    mermaPorHumedad : mermaPorHumedad,
						    mermaPorFumigado : cantFumigado,
						    cantidadEntregada : cantEntrega,
						    cantidadNeta : cantidadNeta,
						    otrasMermas: otrasMermas,
						    nroCartaPorte : cartaPorte,
						    fechaDescarga : fechaDescarga,
							entregaId: $N("entrega.id").value
						  },
					    onComplete: function(transport, json) {
					    }
					  });
		}
	****Incidente entrega adicional se crea en blanco------------------------------------------------------------------
	Documentacion___________________________________________________________________________________________________
		editEntregasMasivas.html

		<action name="editEntregasMasivas" class="com.zeni.app.webapp.action.EntregasMasivasAction" method="edit">
        	<interceptor-ref name="jsonValidationWorkflowStack" />
            <result>/WEB-INF/pages/editEntregasMasivas.jsp</result>
        </action>	
************************************************************************************************************************En Testing



85.-**********************************************************************************Simax N°369 Exportación Boleto Afip
	Usuario 	-->Javier Fux	
	Ext			--> 	
	Area		--> 

	Descripcion:
	Detalle del problema: El error se produce en el modulo Detalle del error: Exportación Boleto Afip. cuando exportamos 
	un boleto con garantía a favor del vendedor. Debe completar la fecha inicial del plazo de pago. Debe completar la 
	fecha final de pago. **aca se debe tomar como un forward o disponible según corresponda. Es decir no debe tener en 
	cuenta si tiene una garantía cargada AL VEDNEDOR. 

	Documentacion___________________________________________________________________________________________________

	Modulo de Exportacion Boleto Afip
	Datos para generar el archivo
	CUIT: 20249136329
	Agencia - Sede N 2 Rosario

		<action name="generarArchivoAfip" class="com.zeni.app.webapp.action.BoletoAfipAction" method="generarArchivoAfip">
            <result name="error">/WEB-INF/pages/boletoAfipForm.jsp</result>
            <result name="success">/WEB-INF/pages/boletoAfipForm.jsp</result>
            <result name="cancel">/WEB-INF/pages/boletoAfipForm.jsp</result>
         	<result name="input" type="noOp"/>
        </action>

    Modificaciones_________________________________________________________________________________________________
    ***Se modifica BoletoAfipAction.java, se modifica metodo generarArchivoAfip() para que valide si tiene garantia vendedor
        //Se valida si el contrato tiene asociado Garantia del vendedor
        CartaGarantia carGar =(CartaGarantia) contratoManager.findFirst(CartaGarantia.class, new String[]{"contrato.id", "fechaBaja", "tipoDocumentoNegocio.codigo"}, new Object[]{contrato.getId(), null, "CartaGarantiaVendedor"});
        boolean valGarVen = !Util.isEmpty(carGar);
        if (contrato.getTieneTipoDocumentoAval() && (contrato.getFechaEntregaDesde().compareTo(xFechaOp60) == 0 || contrato.getFechaEntregaDesde().before(xFechaOp60)) && !valGarVen) {
            xModalidad = "6";
        }
************************************************************************************************************************En Testing



86.-***************************************************Incidencia Nº 348  error en el modulo Ingreso de operaciones Matba
	Usuario 	-->Alejo Frumboli
	Ext			--> 	
	Area		--> 

	Descripcion:	Cuando levantamos el archivo csv con las operaciones delegadas, carga mal el producto. Adjunto 
	ejemplo, el producto el soja en US, y en SIC levanta SOJA Disp $. 

	Documentacion__________________________________________________________________________________________________
*************************************************************************************************************************Cerrado



87.-************************************************************Incidencia con el Nº 379  archivo CSR de Advanta semillas
	Usuario 	--> Manuel Convella
	Ext			--> 	
	Area		--> 

	Descripcion:	necesito generen el archivo CSR de Advanta semillas. esto se necesita para renovar el permiso de certificacion. 
	session de linux 

	si este no se puede liquidar finales de este client 

	Documentacion__________________________________________________________________________________________________
		1.- Acceder VMWare Player
		2.- contraseña usuario
		3.- Acceder K --> aplicaciones --> desarrllo --> svnClient
		4.- Abrir --> seleccionar carpeta
		5.- Acceder a Home --> usuario --> documentos --> documentos tecnicos --> wsafip --> wsaa -->aceptar
		6.- abrir en panel derecho cert-reg-howto-PASOS REALES.texto
		7. ctrl + f --> buscas el Cliente
		8.-copias la primera linea  --> crea el certificado
		9.- k --> buscar --> konsole --> pego
		10.-k --> buscar --> krauser --> file manager
*************************************************************************************************************************Cerrado



88.-********************************************************************************************Reporte Comision ADM Agro
	Usuario 	--> Martin Lombardi / Alejandro 
	Ext			--> 	
	Area		--> 

	Descripcion:	generar y enviar adjunto  al mail  de comisiones,   un archivo TXT conteniendo los datos de las 
	Facturas de comisiones  que nos han emitido.

	Les adjuntamos  la estructura y el formato del Archivo solicitado,  con la descripción de los campos y su longitud.
	Descripción Campos	Longitud	Ejemplo - Formato
	Nro Contrato comprador								10	Ejemplo:  0143012341
	Fecha Emisión Factura								8	Formato AAAAMMDD
	TIPO CBTE 											1	1-FACTURA / 2-DEBITO /3-CREDITO
	CBTE AFIP											3	01/02/03/201/202/203
	PUNTO DE VENTA										4	
	NROCBTE												8	
	FECVTO												8	Formato AAAAMMDD
	KILOS												9	
	PRECIO en TNS.										9	Ultimas 2 posiciones son decimales
	MONEDA												1	1-pesos / 2 dolares
	CAE													14	
	VTO AUTORIZACION CAE								8	Formato AAAAMMDD
	CUITEMISOR											11	
	TCAMBIO												10	Ultimas 5 posiciones son decimales
	GRAVADO												14	Ultimas 2 posiciones son decimales
	IVA													14	Ultimas 2 posiciones son decimales
	PERCEPCION IB Arba									14	Ultimas 2 posiciones son decimales
	PERCEPCION IB CABA									14	Ultimas 2 posiciones son decimales
	PERCEPCION IB S Fe									14	Ultimas 2 posiciones son decimales
	PERCEPCION IB La Pampa								14	Ultimas 2 posiciones son decimales
	TOTAL del Cbte										14	Ultimas 2 posiciones son decimales
	Consultas SQL__________________________________________________________________________________________________

		select fp.id, (select c.NROCONTRATOCOMPRADOR from contrato c where c.id = fp.contrato_id) ,TO_CHAR(FECHAFACTURA, 'yyyyMMdd') , CASE WHEN (TIPOCOMPROBANTE_ID=148013) THEN 1 WHEN (TIPOCOMPROBANTE_ID=148001) THEN 2 WHEN (TIPOCOMPROBANTE_ID=148002) THEN 3 END,  codigoafip,
		      (select pv.numero from punto_de_venta pv where id = fp.PUNTODEVENTA_ID), NROFACTURANUMERO, TO_CHAR(FECHAVTO, 'yyyyMMdd'), ROUND (CANTIDADFACTURADA*1000,1), ROUND((c.precio*(CANTIDADFACTURADA)*1/100), 2),
		      monedafactura_id, CAE,  TO_CHAR(FECHAVTOCAE, 'yyyyMMdd'), 
		     (SELECT cuit FROM cliente cl
		        INNER JOIN workflow_state ws ON cl.state_id = ws.id 
		        INNER JOIN workflow_definition wd ON wd.id = ws.workflowdefinition_id 
		        AND wd.estadoActual = 'H'
		      WHERE cl.cuit like '%'||CUITCLIENTEEMISOR||'%'), 
		      ROUND(COTIZACIONIMPORTE, 5), 
		      ROUND(BASEIMPONIBLE,2), ROUND(IMPORTEIVA,2), ROUND(TotalHaber,2), fp.id
		from factura_producto fp
		inner join comision c on 
		c.facturacomision_id = fp.id
		where fp.cuentacliente_id = 47642
		and (fp.fechaalta between '04/06/2020' and '05/06/2020')


	Modificaciones_________________________________________________________________________________________________
		***menu-config.xml
			<Item name="RepComCliente" title="Reporte Comisiones por Cliente" page="/repComFacturadasCliente.html" width="250" roles="ROLE_USER,ROLE_CONTADURIA,ROLE_IMPUESTOS"/>

		*** ApplicationResources.properties
			# -- Reporte de Comisiones
			rCList.title=Reporte Consulta de Comisiones por Cliente
			rCFilter.filtro.title = Filtro - Reportes de Comisiones
			rCList.heading = Reportes de Comisiones 

		*** ConsultaComisionesFacturadasAction.java
			***** Se crean atributos, con sus get y set

					List<FacturaProducto> facturasComis;
					List<FacturasDTO> listFacturas;

					private UniversalManager manager;	
					private Date fechaDesde;
					private Date fechaHasta;
					private Long ctaCliente;	
					private String ctaClienteTxt;
					private String nroCuentaComprador;
					
					public List<FacturasDTO> getListFacturas() {
						return listFacturas;
					}

					public void setListFacturas(List<FacturasDTO> listFacturas) {
						this.listFacturas = listFacturas;
					}

					public List<FacturaProducto> getFacturasComis() {
						return facturasComis;
					}

					public void setFacturasComis(List<FacturaProducto> facturasComis) {
						this.facturasComis = facturasComis;
					}

					public String getNroCuentaComprador() {
						return nroCuentaComprador;
					}

					public void setNroCuentaComprador(String nroCuentaComprador) {
						this.nroCuentaComprador = nroCuentaComprador;
					}

					public String getCtaClienteTxt() {
						return ctaClienteTxt;
					}

					public void setCtaClienteTxt(String ctaClienteTxt) {
						this.ctaClienteTxt = ctaClienteTxt;
					}

					public Long getCtaCliente() {
						return ctaCliente;
					}

					public void setCtaCliente(Long ctaCliente) {
						this.ctaCliente = ctaCliente;
					}

					public UniversalManager getManager() {
						return manager;
					}

					public void setManager(UniversalManager manager) {
						this.manager = manager;
					}

					public Date getFechaDesde() {
						return fechaDesde;
					}

					public void setFechaDesde(Date fechaDesde) {
						this.fechaDesde = fechaDesde;
					}

					public Date getFechaHasta() {
						return fechaHasta;
					}

					public void setFechaHasta(Date fechaHasta) {
						this.fechaHasta = fechaHasta;
					}


			***** Se crea action
				@Action("repComFacturadasCliente")
				public String reporteComCliente(){
					return "repConComi";
				}

			***** Se agrega result
				@Results({
					@Result(name = "success", location = "/WEB-INF/pages/repConsComFacturasList.jsp"),
					@Result(name = "repConComi", location = "/WEB-INF/pages/repComXClienteList.jsp")	
					})

			***** Se crea action que responde a la vista
				@Action("repComisionesList")
				public String repComisionesList(){
					try {
						if (!(fechaDesde == null) && !(fechaHasta == null)) {
							getComisionesXCliente();
							descargaArchivo();
							clearActionErrors();
						}else {
							addActionError("La fecha de inicio y de fin son necesarias para la busqueda");				
						}			
					} catch (IOException e) {
						e.printStackTrace();
						addActionError("Error en la escritura del archivo");
					} catch (Exception e) {
						e.printStackTrace();
						addActionError("Error en la generacion del Archivo");
					}
					return "repConComi";
				}

			***** Se crea metodo getComisionesXCliente(), realiza la consulta de las comisiones por cliente y devuelve una lista 
				public List<String[]> getComisionesXCliente(){
				List<String[]> listaArreglos = new ArrayList<String[]>();
				listFacturas = new ArrayList<FacturasDTO>();
				String sql = "select (select c.NROCONTRATOCOMPRADOR from contrato c where c.id = fp.contrato_id) ,TO_CHAR(FECHAFACTURA, 'dd/MM/yyyy') , " +
						"				CASE WHEN (TIPOCOMPROBANTE_ID=148013) THEN 1 " +
						"					 WHEN (TIPOCOMPROBANTE_ID=148001) THEN 2 " +
						"					 WHEN (TIPOCOMPROBANTE_ID=148002) THEN 3 END," +
						"  				codigoafip, (select pv.numero from punto_de_venta pv where id = fp.PUNTODEVENTA_ID), NROFACTURANUMERO, " +
						"				 TO_CHAR(FECHAVTO, 'dd/MM/yyyy'), ROUND(CANTIDADFACTURADA*1000,1) ,ROUND(c.precio,2), " +
						"				monedafactura_id, CAE, TO_CHAR(FECHAVTOCAE, 'dd/MM/yyyy'), " +
						"				(SELECT cuit FROM cliente cl " +
						"						INNER JOIN workflow_state ws ON cl.state_id = ws.id " +
						"						INNER JOIN workflow_definition wd ON wd.id = ws.workflowdefinition_id " +
						"				AND wd.estadoActual = 'H' " +
						"				WHERE cl.cuit like '%'||CUITCLIENTEEMISOR||'%'), " +
						"				ROUND(COTIZACIONIMPORTE, 5), "+
						"				ROUND(BASEIMPONIBLE,2), ROUND(IMPORTEIVA,2), ROUND(TotalHaber,2), fp.id " +
						"		from factura_producto fp " +
						"		inner join comision c on " +
						"		c.facturacomision_id = fp.id " +
						"		where fp.cuentacliente_id = ? " +
						"		and (fp.fechaalta between ? and ?) "; 		
				List<Object[]> listaResultados  = (List<Object[]>)manager.executeSQL(sql, new Object[]{ctaCliente, fechaDesde, fechaHasta});
				
				if(listaResultados!= null && listaResultados.size()>0){
					for(Object item: listaResultados){
						Object[] objectItem = (Object[]) item;	
						Double percepcionBsAs = 0D;
						Double percepcionCABA = 0D;
						Double percepcionSFE = 0D;
						Double percepcionLaPampa = 0D;
						Long idFactura = ((objectItem[17] != null) ? Long.valueOf(objectItem[17].toString()) : null);
						String qry ="from DetalleFactura df  where df.factura.id = ?  " ;
						List<DetalleFactura> detalles= (List<DetalleFactura>) manager.find(qry, new Object[]{idFactura});
						
						FacturaProducto fp = new FacturaProducto();
						fp.setDetallesFactura(detalles);
						fp.calculaPercepcionesBsAs();
						percepcionBsAs = fp.getPercProvBsAs();
						percepcionCABA = fp.getPercProvCABA();
						percepcionSFE = fp.getPercProvSantaFe();
						percepcionLaPampa = fp.getPercProvLaPampa();
						
						String contratoComprador = ((objectItem[0] != null) ? objectItem[0].toString().replace("-", ""): null); 	
						String fechaEmision = ((objectItem[1] != null) ? objectItem[1].toString() : null);	
						String tCbte = ((objectItem[2] != null) ? objectItem[2].toString() : null);
						String cteAfip = ((objectItem[3] != null) ? objectItem[3].toString() : null);	
						String ptoVta = ((objectItem[4] != null) ? objectItem[4].toString() : null);
						String nroCbte = ((objectItem[5] != null) ? objectItem[5].toString() : null);
						String fVto = ((objectItem[6] != null) ? objectItem[6].toString() : null);
						String canKilos = ((objectItem[7] != null) ? objectItem[7].toString() : null);
						String precio = ((objectItem[8] != null) ? objectItem[8].toString() : null);
						String moneda = ((objectItem[9] != null) ? objectItem[9].toString().equals("1717")?"1":"2" : null);
						String cae = ((objectItem[10] != null) ? objectItem[10].toString() : null);	
						String vtoCae = ((objectItem[11] != null) ? objectItem[11].toString() : null);	
						String cuitEmisor = ((objectItem[12] != null) ? objectItem[12].toString().replace("-", "") : null);
						Double tCambio = ((objectItem[13] != null) ? Double.valueOf(objectItem[13].toString()) : 0); 
						Double gravado = ((objectItem[14] != null) ? Double.valueOf(objectItem[14].toString()) : 0);
						Double iva = ((objectItem[15] != null) ? Double.valueOf(objectItem[15].toString()) : 0);
						Double totalCb = ((objectItem[16] != null) ? Double.valueOf(objectItem[16].toString()) : 0);
						FacturasDTO factura = new FacturasDTO(idFactura.toString(), contratoComprador, fechaEmision, tCbte, cteAfip, ptoVta, 
															nroCbte, fVto, canKilos, precio, moneda, cae, vtoCae, cuitEmisor, 
															tCambio, gravado, iva, percepcionBsAs, percepcionCABA, percepcionSFE, 
															percepcionLaPampa, totalCb );
						String[] arregloFila = new String[]{
								contratoComprador, fechaEmision, tCbte, cteAfip, ptoVta, 
								nroCbte, fVto, canKilos, precio, moneda, cae, vtoCae, cuitEmisor, 
								String.valueOf(tCambio), String.valueOf(gravado), String.valueOf(iva), 
								String.valueOf(percepcionBsAs), String.valueOf(percepcionCABA), 
								String.valueOf(percepcionSFE),String.valueOf(percepcionLaPampa), 
								String.valueOf(totalCb)
						};
						listFacturas.add(factura);
						listaArreglos.add(arregloFila);
					}
				}		
				return listaArreglos;
			}

			***** Se crea metodo que realiza a descarga del archivo en el cliente
				//Metodo que realiza a descarga del archivo en el cliente
				private void descargaArchivo() throws IOException, Exception {
					HttpServletResponse response = getResponse();
					response.setContentType("application/vnd.ms-excel");
			        response.setHeader("Content-Disposition", "attachment; filename=comisiones.xls");
			        //Creo libro de trabajo
			        HSSFWorkbook libro = new HSSFWorkbook();
			        generarArcIPF(libro, genTitulosYPF(), getComisionesXCliente(), "comisiones");
			        libro.write(response.getOutputStream());
				}

			***** Se generan las hojas del archivo excel
				//Metodo que crea con los datos, las hojas del archivo excel
				private void generarArcIPF(HSSFWorkbook libro, String[] titulos, List<String[]>
				arregloFilas, String tipo) throws IOException, Exception {		

			        //Creo la hoja de trabajo
			        HSSFSheet hoja = libro.createSheet(tipo);
			        //Ancho de la columna por defecto
			        hoja.setDefaultColumnWidth(15);
			        
			        //Creacion de fila
			        HSSFRow fila;
			        Integer rows = 0;
			        
			      //Creacion de celda
			        HSSFCell cell; 
			        fila = hoja.createRow(rows++);
			        HSSFCellStyle style = libro.createCellStyle();
			        HSSFCellStyle style1 = libro.createCellStyle();
			        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
			        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
			        
			        HSSFCellStyle cs = libro.createCellStyle();
			        HSSFDataFormat df = libro.createDataFormat();
			        cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			        cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			        cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
			        cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
			        
			        HSSFCellStyle cs1 = libro.createCellStyle();
			        HSSFDataFormat df1 = libro.createDataFormat();
			        cs1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			        cs1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			        cs1.setBorderRight(HSSFCellStyle.BORDER_THIN);
			        cs1.setBorderTop(HSSFCellStyle.BORDER_THIN);
			        
			        
			        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			        
			        for(int i = 0;i<titulos.length;i++){
						cell = fila.createCell(i);
						cell.setCellStyle(style);
						cell.setCellValue(titulos[i]);
					}
			        try {			
						for (String[] arregloFila : arregloFilas) {
							fila = hoja.createRow(rows++);
							
							for(int i = 0;i<arregloFila.length;i++){
								cell = fila.createCell(i); 	
								cell.setCellStyle(style1);
								if (arregloFila[i] != null){
									if(!(arregloFila[i].equals(""))){
										//Valida los campos que  son tipo double
										if ((i == 8) || (i > 12)){
											if (i == 13){
										        cs.setDataFormat(df.getFormat("#,##0.00000"));
										        cell.setCellValue(Double.parseDouble(arregloFila[i]));
										        cell.setCellStyle(cs);	
											}else{
												cs.setDataFormat(df.getFormat("#,##0.00"));
												cell.setCellValue(Double.parseDouble(arregloFila[i]));
												cell.setCellStyle(cs);	
											}								
										}else if ((i == 1) || (i == 6) || ( i == 11)){
											cell.setCellValue(arregloFila[i]);
										}else{
											if (i == 10){
										        cs1.setDataFormat(df1.getFormat("###############0"));
										        cell.setCellValue(Long.parseLong(arregloFila[i]));
										        cell.setCellStyle(cs1);
											}else{
												cell.setCellValue(Long.parseLong(arregloFila[i]));
											}								
										}
									}	
								}									
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}        
				}

			***** Se crea una clase DTO FacturasDTO
				public class FacturasDTO{
					private String idFactura;
					private String nroContrato;
					private String fechaEmiFactura;
					private String tipoComprobante;
					private String cteAFIP;
					private String puntoVenta;
					private String nroCbte;
					private String fecVTO;
					private String cantKilos;
					private String precio;
					private String moneda;
					private String cae;
					private String vtoCAE;
					private String cuitEmisor;
					private Double tCambio;
					private Double gravado;
					private Double iva;
					private Double percArba;
					private Double percCABA;
					private Double percSFE;
					private Double percLaPampa;
					private Double totalCbte;
					
					
					public FacturasDTO(String idFactura, String nroContrato, String fechaEmiFactura,
							String tipoComprobante, String cteAFIP, String puntoVenta,
							String nroCbte, String fecVTO, String cantKilos, String precio,
							String moneda, String cae, String vtoCAE, String cuitEmisor,
							Double tCambio, Double gravado, Double iva, Double percArba,
							Double percCABA, Double percSFE, Double percLaPampa,
							Double totalCbte) {
						super();
						this.idFactura = idFactura;
						this.nroContrato = nroContrato;
						this.fechaEmiFactura = fechaEmiFactura;
						this.tipoComprobante = tipoComprobante;
						this.cteAFIP = cteAFIP;
						this.puntoVenta = puntoVenta;
						this.nroCbte = nroCbte;
						this.fecVTO = fecVTO;
						this.cantKilos = cantKilos;
						this.precio = precio;
						this.moneda = moneda;
						this.cae = cae;
						this.vtoCAE = vtoCAE;
						this.cuitEmisor = cuitEmisor;
						this.tCambio = tCambio;
						this.gravado = gravado;
						this.iva = iva;
						this.percArba = percArba;
						this.percCABA = percCABA;
						this.percSFE = percSFE;
						this.percLaPampa = percLaPampa;
						this.totalCbte = totalCbte;
					}
					
					
					public String getIdFactura() {
						return idFactura;
					}


					public void setIdFactura(String idFactura) {
						this.idFactura = idFactura;
					}


					public String getNroContrato() {
						return nroContrato;
					}
					public void setNroContrato(String nroContrato) {
						this.nroContrato = nroContrato;
					}
					public String getFechaEmiFactura() {
						return fechaEmiFactura;
					}
					public void setFechaEmiFactura(String fechaEmiFactura) {
						this.fechaEmiFactura = fechaEmiFactura;
					}
					public String getTipoComprobante() {
						return tipoComprobante;
					}
					public void setTipoComprobante(String tipoComprobante) {
						this.tipoComprobante = tipoComprobante;
					}
					public String getCteAFIP() {
						return cteAFIP;
					}
					public void setCteAFIP(String cteAFIP) {
						this.cteAFIP = cteAFIP;
					}
					public String getPuntoVenta() {
						return puntoVenta;
					}
					public void setPuntoVenta(String puntoVenta) {
						this.puntoVenta = puntoVenta;
					}
					public String getNroCbte() {
						return nroCbte;
					}
					public void setNroCbte(String nroCbte) {
						this.nroCbte = nroCbte;
					}
					public String getFecVTO() {
						return fecVTO;
					}
					public void setFecVTO(String fecVTO) {
						this.fecVTO = fecVTO;
					}
					public String getCantKilos() {
						return cantKilos;
					}
					public void setCantKilos(String cantKilos) {
						this.cantKilos = cantKilos;
					}
					public String getPrecio() {
						return precio;
					}
					public void setPrecio(String precio) {
						this.precio = precio;
					}
					public String getMoneda() {
						return moneda;
					}
					public void setMoneda(String moneda) {
						this.moneda = moneda;
					}
					public String getCae() {
						return cae;
					}
					public void setCae(String cae) {
						this.cae = cae;
					}
					public String getVtoCAE() {
						return vtoCAE;
					}
					public void setVtoCAE(String vtoCAE) {
						this.vtoCAE = vtoCAE;
					}
					public String getCuitEmisor() {
						return cuitEmisor;
					}
					public void setCuitEmisor(String cuitEmisor) {
						this.cuitEmisor = cuitEmisor;
					}
					public Double gettCambio() {
						return tCambio;
					}
					public void settCambio(Double tCambio) {
						this.tCambio = tCambio;
					}
					public Double getGravado() {
						return gravado;
					}
					public void setGravado(Double gravado) {
						this.gravado = gravado;
					}
					public Double getIva() {
						return iva;
					}
					public void setIva(Double iva) {
						this.iva = iva;
					}
					public Double getPercArba() {
						return percArba;
					}
					public void setPercArba(Double percArba) {
						this.percArba = percArba;
					}
					public Double getPercCABA() {
						return percCABA;
					}
					public void setPercCABA(Double percCABA) {
						this.percCABA = percCABA;
					}
					public Double getPercSFE() {
						return percSFE;
					}
					public void setPercSFE(Double percSFE) {
						this.percSFE = percSFE;
					}
					public Double getPercLaPampa() {
						return percLaPampa;
					}
					public void setPercLaPampa(Double percLaPampa) {
						this.percLaPampa = percLaPampa;
					}
					public Double getTotalCbte() {
						return totalCbte;
					}
					public void setTotalCbte(Double totalCbte) {
						this.totalCbte = totalCbte;
					}
				}

			***** Se crea metodo que  crea con los datos, las hojas del archivo excel
					//Metodo que crea con los datos, las hojas del archivo excel
					private void generarArcIPF(HSSFWorkbook libro, String[] titulos, List<String[]>
					arregloFilas, String tipo) throws IOException, Exception {		

				        //Creo la hoja de trabajo
				        HSSFSheet hoja = libro.createSheet(tipo);
				        //Ancho de la columna por defecto
				        hoja.setDefaultColumnWidth(15);
				        
				        //Creacion de fila
				        HSSFRow fila;
				        Integer rows = 0;
				        
				      //Creacion de celda
				        HSSFCell cell; 
				        fila = hoja.createRow(rows++);
				        HSSFCellStyle style = libro.createCellStyle();
				        HSSFCellStyle style1 = libro.createCellStyle();
				        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
				        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
				        
				        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
				        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
				        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
				        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				        
				        for(int i = 0;i<titulos.length;i++){
							cell = fila.createCell(i);
							cell.setCellStyle(style);
							cell.setCellValue(titulos[i]);
						}
						try {			
							for (String[] arregloFila : arregloFilas) {
								fila = hoja.createRow(rows++);
								for(int i = 0;i<arregloFila.length;i++){
									cell = fila.createCell(i); 	
									cell.setCellStyle(style1);
									cell.setCellValue(arregloFila[i]);
									//Valida los campos que  son tipo double
									if ((i == 8) || (i > 16)){
										cell.setCellValue(Double.parseDouble(arregloFila[i]));
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}        
					}

			***** Se crea metodo que devuelve los titulos del archivo excel
				//Metodo que devuelve los titulos del archivo excel
				private String[] genTitulosYPF(){
					//Genero Header del Excel
			        String[] titulos = new String[]{"Contrato Comprador",
			        								"Emision Factura",
			        								"Tipo Cte",
			        								"Cte AFIP",
			        								"Punto Venta",
			        								"Nro. CBTE",
			        								"Fecha VTO",
			        								"Kilos",
			        								"Precio TNS.",
			        								"Moneda",
			        								"CAE",
			        								"VTO. CAE",
			        								"CUIT Emisor",
			        								"Tipo Cambio",
			        								"Gravado",
			        								"IVA",
			        								"Percep. BsAs",
			        								"Percep. CABA",
			        								"Percep. SFe",
			        								"Percep. La Pampa",
			        								"Total Cbte."
			        			};
					return titulos;
				}

		*** Se crea vista, repComXClienteList.jsp
			<%@ include file="/common/taglibs.jsp"%>
			<head>
				<title><fmt:message key="rCList.title"/></title>
				<meta name="heading" content="<fmt:message key='rCList.heading'/>"/>
				<meta name="filtroTitle" content="<fmt:message key='rCFilter.filtro.title'/>"/>
				<meta name="filtro" content="/WEB-INF/pages/repComXClienteFiltro.jsp" />
			</head>
			<style>
			       #posts { display: flex; flex-wrap: wrap }
			       .card { padding: 0.5rem; box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.3); transition: all 0.3s ease; }
			       .cardB:hover{transform: scale(1.1); margin-bottom: 40px;}
			       .cardT:hover{transform: scale(1.1); margin-top: 40px }
			</style>
			<%@ include file="auditEntity.jspf" %>
			<s:if test="%{listFacturas != null}">
				<div class="card cardB">
				    <ria:simplegrid id="repoGridFactComis"
									collection="${listFacturas}"
									rowPerPage="${rowPerPage}"
									width="830px" height="250px"
									var="item1"
									rowCountVar="rowCount"
									resizable="true"
									showPropertyButton="true"
									selectionMode="single"
									onSetData="gridNotifyOnEmpty(this, __Messages__.gridNoData)" 
									oldQueryMethod="false">
						<ria:simplegridcolumn label='Factura' key="${item1.idFactura}" width="100" id="idFacturar" style="text-align:center;paddind:10px;"/>
						<ria:simplegridcolumn label='Contrato Comprador' key="${item1.nroContrato}" width="100" id="contrato_Comprador" style="text-align:center;paddind:10px;"/>
						<ria:simplegridcolumn label='Emision Factura' key="${item1.fechaEmiFactura}" width="100" id="emision_Factura" style="text-align:center;paddind:10px;" />
						<ria:simplegridcolumn label='Tipo Cte' key="${item1.tipoComprobante}" width="250" id="tipo_Cte" style="text-align:center;paddind:10px;"/>
						<ria:simplegridcolumn label='Cte AFIP' key="${item1.cteAFIP}" width="100" id="cteAFIP" style="text-align:center;paddind:10px;"/>
						<ria:simplegridcolumn label='Punto Venta' key="${item1.puntoVenta}" width="100" id="puntoVenta" style="text-align:center;paddind:10px;" />
						<ria:simplegridcolumn label='Nro. CBTE' key="${item1.nroCbte}" width="250" id="nroCbte" style="text-align:center;paddind:10px;"/>
					    <ria:simplegridcolumn label='Fecha VTO' key="${item1.fecVTO}" width="100" id="fechaVTO" style="text-align:center;paddind:10px;"/>
						<ria:simplegridcolumn label='Kilos' key="${item1.cantKilos}" width="100" id="kilos" style="text-align:center;paddind:10px;" />
						<ria:simplegridcolumn label='Precio TNS.' key="${item1.precio}" width="250" id="precioTNS" style="text-align:center;paddind:10px;"/>
					    <ria:simplegridcolumn label='Moneda' key="${item1.moneda}" width="100" id="moneda" style="text-align:center;paddind:10px;"/>
						<ria:simplegridcolumn label='CAE' key="${item1.cae}" width="100" id="cae" style="text-align:center;paddind:10px;" />
						<ria:simplegridcolumn label='VTO. CAE' key="${item1.vtoCAE}" width="250" id="vtoCAE" style="text-align:center;paddind:10px;"/>
					    <ria:simplegridcolumn label='CUIT Emisor' key="${item1.cuitEmisor}" width="100" id="cuitEmisor" style="text-align:center;paddind:10px;"/>
						<ria:simplegridcolumn label='Tipo Cambio' key="${item1.tCambio}" width="100" id="tCambio" style="text-align:center;paddind:10px;" />
						<ria:simplegridcolumn label='Gravado' key="${item1.gravado}" width="250" id="gravado" style="text-align:center;paddind:10px;"/>
					    <ria:simplegridcolumn label='IVA' key="${item1.iva}" width="100" id="iva" style="text-align:center;paddind:10px;"/>
						<ria:simplegridcolumn label='Percep. BsAs' key="${item1.percArba}" width="100" id="percArba" style="text-align:center;paddind:10px;"/>
						<ria:simplegridcolumn label='Percep. CABA' key="${item1.percCABA}" width="100" id="percCABA" style="text-align:center;paddind:10px;"/>
						<ria:simplegridcolumn label='Percep. SFe' key="${item1.percSFE}" width="100" id="percSFE" style="text-align:center;paddind:10px;"/>
						<ria:simplegridcolumn label='Percep. La Pampa' key="${item1.percLaPampa}" width="100" id="percLaPampa" style="text-align:center;paddind:10px;"/>
						<ria:simplegridcolumn label='Total Cbte.' key="${item1.totalCbte}" width="100" id="totalCbte" style="text-align:center;paddind:10px;" />
						<ria:gridinnerexport id="innerexport" filename="Contratos Pactados"/>
					</ria:simplegrid> 
				</div>
			</s:if>


			<script type="text/javascript">
			    document.getElementById("cliente_ctaClienteTxt").style.width ="220px";
				document.getElementById("repoGridFactComis").style.height= "300px";
				document.getElementById("repoGridFactComis_frame_border").style.height= "300px";
				function formatFecha(sFecha){
				        if(sFecha.indexOf('/')==-1){
						   var str =sFecha.split(' ')[0];
					       var str1 = str.split('-');	
					       return new Date(str1[0],str1[1]-1,str1[2]).format();
				        }else{
				          return sFecha;
				        }
					}
			</script>

		*** Se crea filtro, repComXClienteFiltro.jsp
			<%@ include file="/common/taglibs.jsp"%>
			<s:form id="repComisionesFiltro" action="repComisionesList" method="post" validate="true">	
				<table style="margin: auto">
						<tr>
							<TD align="left" colspan=3 style="margin-top: 15px">
								<appfuse:valuelist id="cliente" modelEntity="CuentaCliente" entityAttrName="cliente.razonSocial"
									additionalAttr="denominacionCuenta, nroCuenta"
									additionalAttrTitle="cuentaCliente.denominacionCuenta,cuentaCliente.nroCuenta"
									additionalAttrWidth="200, 100" idAttr="ctaCliente"
									entityCodeAttrName="nroCuenta"
									entityCodeName="nroCuentaComprador"
									entityCodeCss="text size50"
									nameAttr="ctaClienteTxt" nameAttrReadonly="false" 
									nameLabel="Cuenta Cliente" nameCss="text mediumlarge"  
									width="500" height="350" title="cuentaClienteList.heading" 
									idColumnLabel="cuentaCliente.id" nameColumnLabel="cliente.razonSocial" nameColumnLabelWidth="200" readonly="false" required="false" />
							</TD>
						<td valign="top" style="padding-left: 12px"><sx:datetimepicker
								key="fechaDesde" label="Desde: " required="false" cssClass="text"
								displayFormat="%{datePattern}"
								templateCssPath="%{#datetimepickerCSS}" cssStyle="width:100px" />
						</td>
						<td valign="top" style="padding-left: 12px"><sx:datetimepicker
								key="fechaHasta" label="Hasta: " required="false" cssClass="text"
								displayFormat="%{datePattern}"
								templateCssPath="%{#datetimepickerCSS}" cssStyle="width:100px" />
						</td>
						<td style="padding-left: 25px;">
			//						<input type='button'
			//										style="width: 60px; margin-right: 5px; margin-left: auto; margin-right: auto; width: 80px; height: 25px; margin-top: 10px;"
			//										class="button" value="<fmt:message key="button.clearfilter"/>"
			//										onclick="clearForm(this.form);" />
			//						</td>
			//						<td style="padding-left: 25px;"><s:submit cssClass="button"
			//								style="width: 60px; margin-right: 5px; margin-left: auto;  margin-right: auto; width: 80px; height: 25px; margin-top: 10px;"
			//								action="repComisionesList" key="button.filter" /></td>
			//					</tr>
				</table>
			</s:form>

	Modificacion2__________________________________________________________________________________________________
		<action name="comisionesFacturadas" class="com.zeni.app.webapp.action.ComisionFacturadaListAction"
			method="list">
			<result>/WEB-INF/pages/comisionFacturadaList.jsp</result>
		</action>

		/*<action name="filtrarComisionFacturada"
			class="com.zeni.app.webapp.action.ComisionFacturadaListAction"
			method="list">
			<interceptor-ref name="jsonValidationWorkflowStack" />
			<result>/WEB-INF/pages/comisionFacturadaList.jsp</result>
		</action>*/
*************************************************************************************************************************Implementado









89.-**********************************************************************Simax 417 Incdencia con titular y proveedor YPF
	Usuario 	--> Silcio Deacon
	Ext			--> 	
	Area		--> 

	Descripcion:
	Archivo YPF - en el archivo que saca el sistema al generarse modifica los datos del titular y proveedor de YPF

	Modificaciones_________________________________________________________________________________________________
		***ConsultaCartaPorteYPFAction.java,
			***** Se modifica metodo genDatosY(), se modifica consulta para que traiga el cargador en las entregas adicionales
			String consulta ="SELECT distinct(e.NROCARTADEPORTE), " +
									"(SELECT CUIT FROM CLIENTE WHERE ID = e.CLIENTEVENDEDOR_ID), " +
									"(SELECT CUIT FROM CLIENTE WHERE ID = e.CLIENTECOMPRADOR_ID), " +
									"eda.CUITINTERMEDIARIO, " +
									"EDA.CUITREFCOMERCIAL, " +
									"(SELECT CUIT FROM ENTREGADOR WHERE ID = E.ENTREGADOR_ID), " +
									" EDA.CUITRECIBIDOR, " +
									"eda.puertoYPF, " +
									"eda.nombreTransportista, " +
									"eda.cuitTransportista, " +
									"eda.nombreChofer, " +
									"eda.cuitChofer, " +
									"(SELECT CODIGOONCCA FROM Producto WHERE id = e.PRODUCTO_ID), " +
									"(select nombre from Producto where id = e.PRODUCTO_ID), " +
									"(select aniocosecha from contrato where id = (select ep.CONTRATO_ID " +
									"												from entrega_partida ep " +
									"												where ep.entrega_id = e.id " +
									"												and rownum <= 1)), " +
									"(SELECT codigooncca FROM partido WHERE id = (SELECT ciu.partido_id FROM ciudad ciu WHERE ciu.id = e.procedencia_id)), " +
									"(SELECT ciu.CODIGO FROM ciudad ciu WHERE ciu.id = e.procedencia_id), " +
									"EDA.PATENTECHASIS, " +
									"EDA.PATENTE_ACOPLADO, " +
									"TO_CHAR(E.FECHADESCARGAMERCADERIA, 'dd/MM/yyyy'), " +
									"EDA.PESOBRUTO, " +
									"EDA.TARA, " +
									"EDA.TOTALMERMAS, " +
									"eda. CUITCARGADOR " + //Se añade por requerimiento Simax 417
							"from ENTREGA e " +
							"left join ENTREGA_DATOS_ADICIONALES eda " +
							"	on eda.entrega_id = e.id " +
							"left join entrega_partida ep " +
							"	on ep.entrega_id = e.id " +
							"inner join contrato c " +
							"	on ep.contrato_id = c.id " +
							"WHERE e.fechabaja IS NULL " +
							"AND e.FECHAALTA >= to_date('"+ DateUtil.getDate(fechaDesde)+ "01:00:00','DD/MM/YYYY HH24:MI:SS') " +
							"AND e.FECHAALTA <= to_date('"+ DateUtil.getDate(fechaHasta) + "23:59:59','DD/MM/YYYY HH24:MI:SS') " +
							"and ((select cli.id from cliente cli " +
							"					inner join cuenta_cliente cc " +
							" 						on cli.id = cc.cliente_id " +
							"					where cc.id = c.vendedor_id) = " +contratoManager.getSystemConfig("id_YPF")+" or " +
									"(select cli.id from cliente cli " +
									"			inner join cuenta_cliente cc " +
									"				 on cli.id = cc.cliente_id" +
									"			where cc.id = c.vendedor_id) = " +contratoManager.getSystemConfig("id_YPF")+") "+cartaPorte;
			***** Se modifica metodo arregloDatos(), en la validacion si YPF es intermediario, se le setea cargador al proveedor y al titular
				if (!cuitRefComercial.replace("-", "").equalsIgnoreCase(cuitCliente("id_YPF").getCuit().replace("-", "") )){
					//Se realizan modificaciones, en caso de ser YPF el intermediario el titular y el proveedor deben ser = cargador
					cuitVendedor = cuitCargador;
					cuitComprador = cuitCargador;
				}	
*************************************************************************************************************************Implementado


90.-**************************************************Simax 418 Incidencia en peso bruto y peso neto entregas adicionales
	Usuario 	--> Silcio Deacon
	Ext			--> 	
	Area		--> 

	Descripcion:
	Archivo YPF - al generar el archivo cuando la CP tiene una rebaja los kg. brutos - netos y netos con mermas los 
	modifica y se reportan con error a YPF.
*************************************************************************************************************************Implementado


91.-*******************************************************************************Simax 412 Archivo Retenciones Syngenta
	Usuario 	--> Fernando Rossi
	Ext			--> 	
	Area		--> 

	Descripcion:
	modificación archivo Txt de retenciones Syngenta Agro. Cuando contabilizamos con un mismo numero de retención varios 
	coe necesitan que el txt refleje la retención en solo una linea con el importe total del comprobante.
	modulo: Syngenta exportar retenciones 

	Documentacion_________________________________________________________________________________________________
		exportarArchivoRetencion.html

		<action name="exportarArchivoRetencion" class="com.zeni.app.webapp.action.ExportarArchivoRetencionAction" method="edit">
		    <result name="error">/WEB-INF/pages/exportarArchivoRetencionForm.jsp</result>
		    <result name="success">/WEB-INF/pages/exportarArchivoRetencionForm.jsp</result>
		    <result name="cancel">/WEB-INF/pages/exportarArchivoRetencionForm.jsp</result>
		    <result name="input" type="noOp"/>
		</action>

		public String generarArchivo() {...}

	SQL___________________________________________________________________________________________________________
 		// RETENCIONES DE INGRESOS BRUTOS
	    // Armado de Linea de Cabecera
	    //System.out.println("RETENCIONES DE INGRESOS BRUTOS");
		query = "select " +
	    		"	sum(r.importeRetencion), " +
	    		"	nvl(SUM(r.importebaseimponible), 0), " +
	    		"	cl.cuit," +
	    		"	nvl(r.alicuota, 0), " +
	    		"	r.nrocertificado, " +
	    		"	r.tipoImpuesto_id " +
				"FROM recibo_retencion r "+
				"left outer JOIN cuenta_cliente cc "+ 
				"ON cc.ID = r.cuentacliente_id "+
				"	left outer JOIN cliente cl "+
				"ON cl.ID = cc.cliente_id "+
				"	left outer JOIN factura_producto fp "+ 
				"ON fp.ID = r.factura_id "+
				"left outer JOIN contrato con "+ 
				"ON fp.CONTRATO_ID = con.id "+
				"left outer JOIN CUENTA_CLIENTE ccvend "+ 
				"ON con.VENDEDOR_ID = ccvend.id "+
				"	left outer JOIN recibo re "+
				"ON re.ID = r.recibo_id "+
				"WHERE r.provincia_id IS NOT NULL "+
	    	 	"AND r.fechaBaja IS NULL "+
	    	 	"AND CCVEND.NROCUENTA in (" + cuentas + ") " +
	    	 	"AND r.TIPOIMPUESTO_ID = -1 " + //RET_IIBB
	    	 	"AND fp.TIPOCOMPROBANTE_ID <> 148013 "+ //No incluye FA
	    	 	(fechaDesde!=null?" AND r.fechaAlta >= :fechaDesde":"") +
	    	 	(fechaHasta!=null?" AND r.fechaAlta <= :fechaHasta":"") + 
	    	 	" group by cl.cuit, r.NROCERTIFICADO, r.tipoImpuesto_id, r.alicuota ";


				query_detalle = "SELECT " +
					"r.nroRetencion AS numero, " +
		    		"r.fechaEnvioCarta, " +
		    		"r.provincia_id, " +
		    		"r.factura_id, " +
		    		"fp.nroFacturaSucursal, " +
		    		"fp.letra, " +
		    		"fp.nroFacturaNumero, " +
		    		"fp.fechaFactura " +
					"FROM recibo_retencion r "+
					"left outer JOIN cuenta_cliente cc "+ 
					"ON cc.ID = r.cuentacliente_id "+
					"	left outer JOIN cliente cl "+
					"ON cl.ID = cc.cliente_id "+
					"	left outer JOIN factura_producto fp "+ 
					"ON fp.ID = r.factura_id "+
					"left outer JOIN contrato con "+ 
					"ON fp.CONTRATO_ID = con.id "+
					"left outer JOIN CUENTA_CLIENTE ccvend "+ 
					"ON con.VENDEDOR_ID = ccvend.id "+
					"	left outer JOIN recibo re "+
					"ON re.ID = r.recibo_id "+
					"WHERE r.provincia_id IS NOT NULL "+
		    	 	"AND r.fechaBaja IS NULL "+
		    	 	"AND r.NROCERTIFICADO='" + nro_certificado_str + "' " +
		    	 	"AND CCVEND.NROCUENTA in (" + cuentas + ") " +
		    	 	"AND r.TIPOIMPUESTO_ID = -1 " + //RET_IIBB
		    	 	"AND fp.TIPOCOMPROBANTE_ID <> 148013 "+ //No incluye FA
		    	 	(fechaDesde!=null?" AND r.fechaAlta >= :fechaDesde":"") +
		    	 	(fechaHasta!=null?" AND r.fechaAlta <= :fechaHasta":"");
	Modificaciones_________________________________________________________________________________________________
		No se realizan modificaciones, se detecta error manual en la carga de la alicuota
*************************************************************************************************************************Cerrada



92.-*****************************************************************Simax 400 Se modifica correo de boleto y carta porte
	Usuario 	--> Emiliano Juan
	Ext			--> 	
	Area		--> 

	Descripcion:

	Documentacion___________________________________________________________________________________________________

		<action name="generacionDocumentos" class="com.zeni.app.webapp.action.GeneracionDocumentosListAction" method="list">
            <result>/WEB-INF/pages/generacionDocumentosList.jsp</result>
        </action>

        <action name="clausulasCartaOferta" class="com.zeni.app.webapp.action.DocumentoDeNegocioReportAction" method="previewClausulas">
        	<result name="success">/WEB-INF/pages/clausulasCartaOferta.jsp</result>
        </action>

    Modificaciones_________________________________________________________________________________________________
    	***DocumentoDeNegocioReportAction.java
    		*Se modifica metodo enviarMail(), para que tome las nuevas descriciones del corre

    		*Se agrego descripcion para cartaPorte y para boleto
	    			 private static final String bodyMailBoleto1 = 
	    		"<table style='width:530px;border-collapse:collapse;font-family:Helvetica;font-size:13px;color:#4D4D4D;'>" +
	    		"<tr>" +
	    		"<td>" +
	    		"Estimados,<br/><br/>" +
	    		"Debido a la situación actual y, mientras duren las medidas dispuestas por el Gobierno Nacional de “aislamiento social, preventivo y obligatorio” en respuesta a la Pandemia de COVID-19, se ha modificado el circuito del Boleto, por lo tanto:<br/><br/>" +
	    		"Estimaremos,<br/><br/>" +
	    		"1) Imprimir 1 hoja del boleto.<br/><br/>" +
	    		"2) Firmar con sello social por la/s persona/s habilitada/s en las bolsas. (Aclarar nombre/s y DNI)<br/><br/>" +
	    		"3) Escanear la copia del boleto firmado.<br/><br/>" +
	    		"4) Enviar por mail a: 3boletos@zeni.com.ar <br/><br/>" +
	    		"5) Cuando la situación se normalice, por favor enviar el físico a nuestras oficinas.<br/><br/>" +
	    		"Agradeceremos su rápido envío.<br/><br/>" +
	    		"Por consultas, por favor, referirse al sector Boletos.<br/><br/>" +
	    		"Desde ya gracias, <br/><br/>" +
	    		"Saludos." +
	    		"</td>" +
	    		"</tr>" +
	    		"</table>";

	    		    private static final String bodyMailCartaOferta1 = 
	    		"<table style='width:530px;border-collapse:collapse;font-family:Helvetica;font-size:13px;color:#4D4D4D;'>" +
	    		"<tr>" +
	    		"<td>" +
	    		"Estimados,<br/><br/>" +
	    		"Debido a la situación actual y, mientras duren las medidas dispuestas por el Gobierno Nacional de “aislamiento social, preventivo y obligatorio” en respuesta a la Pandemia de COVID-19, se ha modificado el circuito de las cartas de oferta, por lo tanto:<br/><br/>" +
	    		"Estimaremos,<br/><br/>" +
	    		"1) Imprimir 1 hoja de la carta de oferta.<br/><br/>" +
	    		"2) Firmar con sello social por la/s persona/s habilitada/s en las bolsas. (Aclarar nombre/s y DNI)<br/><br/>" +
	    		"3) Escanear la copia de la carta de oferta firmada.<br/><br/>" +
	    		"4) Enviar por mail a: 3boletos@zeni.com.ar <br/><br/>" +
	    		"5) Cuando la situación se normalice, por favor enviar el físico a nuestras oficinas.<br/><br/>" +
	    		"Agradeceremos su rápido envío.<br/><br/>" +
	    		"Por consultas, por favor, referirse al sector Boletos.<br/><br/>" +
	    		"Desde ya gracias, <br/><br/>" +
	    		"Saludos." +
	    		"</td>" +
	    		"</tr>" +
    			"</table>";
*************************************************************************************************************************IMplementado Git



93.-**********************************************************************************Modificacion Reporte de comisiones
	Usuario 	--> Jose Moreno
	Ext			--> 	
	Area		--> 

	Descripcion:  Se solicita modificacion en el reporte de comisiones Facturadas por cliente, para que se obtenga el 
	nro de punto de venta codigo AFIP

	Modificaciones_________________________________________________________________________________________________
		***ConsultaComisionesFacturadasAction.java
			*Se modifica el metodo getComisionesXCliente()
			String sql = "select (select c.NROCONTRATOCOMPRADOR from contrato c where c.id = fp.contrato_id) ,TO_CHAR(FECHAFACTURA, 'dd/MM/yyyy') , " +
				"				CASE WHEN (TIPOCOMPROBANTE_ID=148013) THEN 1 " +
				"					 WHEN (TIPOCOMPROBANTE_ID=148001) THEN 2 " +
				"					 WHEN (TIPOCOMPROBANTE_ID=148002) THEN 3 END," +
				"  				codigoafip, (select pv.nroafip from punto_de_venta pv where id = fp.PUNTODEVENTA_ID), NROFACTURANUMERO, " +
				"				 TO_CHAR(FECHAVTO, 'dd/MM/yyyy'), ROUND(CANTIDADFACTURADA*1000,1) , ROUND(c.precio,2), " +
				"				monedafactura_id, CAE, TO_CHAR(FECHAVTOCAE, 'dd/MM/yyyy'), " +
				"				(SELECT cuit FROM cliente cl " +
				"						INNER JOIN workflow_state ws ON cl.state_id = ws.id " +
				"						INNER JOIN workflow_definition wd ON wd.id = ws.workflowdefinition_id " +
				"				AND wd.estadoActual = 'H' " +
				"				WHERE cl.cuit like '%'||CUITCLIENTEEMISOR||'%'), " +
				"				ROUND(COTIZACIONIMPORTE, 5), "+
				"				ROUND(BASEIMPONIBLE,2), ROUND(IMPORTEIVA,2), ROUND(TotalHaber,2), fp.id " +
				"		from factura_producto fp " +
				"		inner join comision c on " +
				"		c.facturacomision_id = fp.id " +
				"		where fp.cuentacliente_id = ? " +
				"		and (fp.fechaalta between ? and ?) "; 		
		List<Object[]> listaResultados  = (List<Object[]>)manager.executeSQL(sql, new Object[]{ctaCliente, fechaDesde, fechaHasta});
************************************************************************************************************************Por pase a produccion



94.-**********************************************************************Simax 425 Error en alertas de limite Operativo
	Usuario 	--> Nerea De Fontes
	Ext			--> 	
	Area		--> 

	Descripción:

	El error se produce en el modulo CRM>Consulta de Alertas
	Detalle del error: El sistema está informando alerta por superación de limite de un cliente cuyo monto superado es 
	$0. No debería haber saltado dicha alerta.

	Documentacion__________________________________________________________________________________________________

		<action name="consultaAlertasCRM"
			class="com.zeni.app.webapp.action.CRMConsultaAlertasAction" method="list">
			<result>/WEB-INF/pages/crmConsultaAlertasList.jsp</result>
		</action>

		CrmPerfilTransaccionalLimitesTimerTask.java
			runTask()
				//obtiene todas las CRMFichaCliente que no están dadas de baja
				//List<CRMFichaCliente> fichaList = manager.find("SELECT mc FROM CRMFichaCliente mc WHERE mc.id = "+new Long(171014978)+" and mc.fechaBaja is null");
				List<CRMFichaCliente> fichaList = manager.find("SELECT mc FROM CRMFichaCliente mc WHERE mc.fechaBaja is null");

				//recorre la lista obtenida de CRMFichaCliente
				for(CRMFichaCliente ficha : fichaList){
					String[] properties = new String[] { "cliente.id", "incluyeCalculoUIF", "fechaBaja" };
					Object[] values = new Object[] { ficha.getCliente().getId(), true, null };
					
					//busca las cuentas clientes del cliente que figura en la ficha, que incluya calculo UIF y no esté dada de baja
					List marcaList = manager.find(CuentaCliente.class, properties, values);

					//obtiene ultimo balance
					Integer anioUltBalance = getUltimoBalance(ficha.getId());    -->2019

					//chequea si es el ultimo balance
					String balanceActualizado = isBalanceActualizado(ficha.getId());

					//calcula total debitos
	                saldoCliente.setTotalDebitos(calculaTotalDebitos(fecha, ficha.getId()));					
				}
	Modificaciones_________________________________________________________________________________________________
		***CrmPerfilTransaccionalLimitesTimerTask.java
			*Se modifica metodo runTask(), para que limite operativo quede con dos decimales
				limiteOperativoCliente = limiteOperativoCliente - reduccionLO + ampliacion + ampliacionInflacionLO;
				BigDecimal bdLimiteOperativoCliente = new BigDecimal(limiteOperativoCliente);
				bdLimiteOperativoCliente = bdLimiteOperativoCliente.setScale(2, RoundingMode.HALF_UP);
				limiteOperativoCliente = bdLimiteOperativoCliente.doubleValue();
************************************************************************************************************************Por pase a produccion




95.-*************************************************************************************Cambio en reporte de comisiones
	Descripcion:
	A solicitud de claudio se quita del menu-config el repote de comisiones Facturadas y reporte de comisiones facturadas 
	por cliente, este ultimo reporte se muestra en modulo de comisiones facturadas

	Modificaciones_________________________________________________________________________________________________
		***menu-config.xml, se eliminan las siguientes opciones
			<Item name="ReporteComisiones" title="Reporte Comisiones Facturadas" page="/RepComisionesFacturadas.html" width="250" roles="ROLE_USER,ROLE_CONTADURIA,ROLE_IMPUESTOS"/> 
			<Item name="RepComCliente" title="Reporte Comisiones por Cliente" page="/repComFacturadasCliente.html" width="250" roles="ROLE_USER,ROLE_CONTADURIA,ROLE_IMPUESTOS,ROLE_FACTURACION"/> 
       	
		***Se modifica, se agrega boton que redirige al modulo de reporte comisiones por cliente
			<input type="button" class="button" onclick="location.href='<c:url value="/repComFacturadasCliente.html"/>'"
        	value="Excel"/>
*************************************************************************************************************************Por pase a produccion



96.-********************************************************Simax 447 envio de correo en timer task documentos de riesgo
	Usuario 	--> Nerea De Fontes
	Ext			--> 	
	Area		--> 

	Descripción:
	Actualizar pedido de docuentación a los clientes conforme el riesgo asignado. En caso de riesgo Alto, pedir documentacion 
	anualmente. Riesgo Medio cada 2 años. Riego Bajo cada 3 años. 
	Documentacion___________________________________________________________________________________________________

		<action name="editCRMFichaCliente" class="com.zeni.app.webapp.action.CRMFichaClienteAction"
			method="edit">
			<result>/WEB-INF/pages/crmFichaClienteForm.jsp</result>
			<result name="error">/WEB-INF/pages/clienteCRMList.jsp</result>
		</action>

		***Pendiente de legajo completo
			private String legajoCompleto;
			@Formula(value="(SELECT CASE WHEN (" +
		        //Tilde en Contrato Certificado
		        "CFC.CONTRATO_CERTIFICA = 1 AND " +
		        //Tilde en Aporto DNI Firmante
		        "CFC.APORTO_DNI_FIRMANTE = 1 AND " +
		        //Diferencia de dias entre hoy y la fecha peps sea menor a un año mas un día
		        "(trunc((select sysdate from dual), 'dd')-trunc(cfc.fechaPeps, 'dd')) < 366 AND " + 
		        //Si son Unipersonal o Soc. de Hecho
		        "(TS.NOMBRE IN ('Unipersonal','Soc.de Hecho') OR " +
		            //Diferencia en meses entre hoy y el cierre del balance sea menor o igual 17 
		            "(MONTHS_BETWEEN(to_date(concat((select to_char(sysdate, 'MM') from dual),(select to_char(sysdate, 'YYYY') from dual)),'MMYYYY'),to_date(concat(to_char(coalesce(cfc.mes_cierre_balance,1),'00'),(select to_char(coalesce(max(b.anio),1900),'0000') from crm_balance b where b.crmfichacliente_id=cfc.id and b.fechaBaja IS NULL)),'MMYYYY')) <= 17)) AND " +
		        //Si son Unipersonal o Soc. de Hecho
		        "(TS.NOMBRE IN('Unipersonal','Soc.de Hecho') OR " +
		          //Balance certificado del ultimo balance.
		          "(select bal.balance_certificado from crm_balance bal where bal.crmfichacliente_id = cfc.id and bal.fechabaja is null and bal.anio = (SELECT MAX(e.anio) FROM crm_balance e WHERE e.crmfichacliente_id = cfc.id AND e.fechaBaja IS NULL ) ) = 1 ) AND " +  
		        //Si son Unipersonal o Soc. de Hecho
		        "(TS.NOMBRE IN ('Unipersonal','Soc.de Hecho') OR " +
		        //Si aportó estatuto y Fecha Vencimiento de Actas y Fecha Vencimiento Social son mayores o iguales al hoy
		          "(CFC.FECHAVTOACTAS >= (SELECT SYSTIMESTAMP FROM DUAL) AND CFC.APORTO_ESTATUTO = 1 AND CFC.FECHAVTOCTOSOCIAL >= (SELECT SYSTIMESTAMP FROM DUAL) ) ) AND " + 
		        //Si NO son Unipersonal o Soc. de Hecho
		        "(TS.NOMBRE NOT IN ('Unipersonal','Soc.de Hecho') OR " +
		        //Si Diferencia de la fecha de hoy con Año de DDJJ + 2 años y en el mes 7 es menor a 0 y DDJJ de Gcias Personales aprobada
		        "(CFC.DDJJ_GCIAS_BS_PERSONALES = 1 and (MONTHS_BETWEEN (to_date (concat ( (select to_char(sysdate, 'MM') from dual), (select to_char(sysdate, 'YYYY') from dual) ), 'MMYYYY' ), to_date (concat (to_char (07,'00'), to_char (coalesce (CFC.ANIO_DDJJ,1900)+2,'0000' ) ), 'MMYYYY' ) ) < 0 ) ) ) ) AND " +
		      //Si son Unipersonal o Soc. de Hecho
		        "(TS.NOMBRE IN ('Unipersonal','Soc.de Hecho') OR " + 
		          //Tiene Titularidad Social
		          "(CFC.titularidadsocial=1)) AND " +
		        //Si tiene el CACC
		        "CFC.CACCVIGENTE = 1 THEN 'SI' ELSE 'NO' END "+
		        "FROM CRM_FICHA_CLIENTE CFC INNER JOIN CLIENTE CL ON CL.ID = CFC.CLIENTE_ID INNER JOIN TIPO_SOCIEDAD TS ON TS.ID = CL.TIPOSOCIEDAD_ID WHERE CFC.ID = id)")
			@Basic(fetch=FetchType.LAZY)
			public String getLegajoCompleto() {
				if(fieldHandler != null)
					return (String) fieldHandler.readObject(this, "legajoCompleto", legajoCompleto);
				return legajoCompleto;
			}

		*** Categoria de Riesgo en CRMFichaCliente.java
			private String riesgoCliente; //(B)=Bajo  (M)=Medio  (A)=Alto

		***Previsualidar Documentacion
			<action name="editCRMFichaCliente" class="com.zeni.app.webapp.action.CRMFichaClienteAction"
				method="edit">
				<result>/WEB-INF/pages/crmFichaClienteForm.jsp</result>
				<result name="error">/WEB-INF/pages/clienteCRMList.jsp</result>
			</action>

			<th align = "left">
	 			<s:submit cssClass="button" key="Reclamar" theme="simple" onclick="reclamar()"/>
	 		</td>
		 	 
	 		<th align = "right">
	 			<s:submit cssClass="button" key="Previsualizar" theme="simple" onclick="popUpOpen()"/>
	 		</td>

	 		function popUpOpen(){
				popupWin.openUrl('popupPrevisualizar','previsualizar1.html?popUp=popupPrevisualizar&decorator=popup&cuentaClienteId='+clienteId);
			}

		***Boton reclamar general en Cliente-CRM
			clientesCRM.html

			<action name="clientesCRM" class="com.zeni.app.webapp.action.ClienteCRMAction"
				method="list">
				<result>/WEB-INF/pages/clienteCRMList.jsp</result>
			</action>

			<head>
			    <title><fmt:message key="clienteCRMList.title"/></title>
			    <meta name="heading" content="<fmt:message key='clienteCRMList.heading'/>"/>
				<meta name="filtro" content="/WEB-INF/pages/crmFichaClienteFiltro.jsp"/>
				<meta name="filtroTitle" content="<fmt:message key='clienteList.filtro.title'/>"/>
			    <meta name="menu" content="CRMMenu"/>
			</head>

				<td>
					<s:submit cssClass="UIFButton" onclick="jqvascript:reclamarGeneral()" method="list" key="button.recGen" theme="simple"/>
				</td>

				function reclamarGeneral(){
					$("filter_legajoCompleto").value = "NO";
					$("filter_cuentaCondicion").value = 50000;
					var url = '<c:url value="/reclamarGeneral.html"/>'; 
					window.open(url,"_self")
				}

			***reclamarGeneral
				<action name="reclamarGeneral" class="com.zeni.app.webapp.action.ClienteCRMAction"
					method="reclamarGeneral">
					<interceptor-ref name="jsonValidationWorkflowStack" />
					<result name="RECGEN">/WEB-INF/pages/reclamarList.jsp</result>
				</action>

				<input type="button" class="UIFButton" onclick="javascript:popUpReclamo($N('selectedCupo').value)" value="<fmt:message key="button.recGenFin"/>"/>

				<action name="reclamoGeneralMandar" class="com.zeni.app.webapp.action.CRMFichaClienteAction"
					method="reclamarGeneralMail">
					<result name="SUCCESS" type="noOp"></result>
				</action>

					public String reclamarGeneralMail(){
							reclamarGeneralSend(aux_cliente);
					..}

		***Analisis Crediticio fecha
			analisisCrediticioCRM.html
			<action name="analisisCrediticioCRM"
				class="com.zeni.app.webapp.action.CRMAnalisisCrediticioAction"
				method="list">
				<result>/WEB-INF/pages/crmAnalisisCrediticioList.jsp</result>
			</action>

			select anio from crm_balance  --> SELECT MAX(e.anio) FROM crm_balance e WHERE e.crmfichacliente_id = id AND e.fechaBaja IS NULL
			select mes_cierre_balance from CRM_FICHA_CLIENTE
			mes_DDJJ  --> CRM_FICHA_CLIENTE
			anio_DDJJ --> CRM_FICHA_CLIENTE
	Modificaciones__________________________________________________________________________________________________
		***Se crea timerTask CrmDocRiesgoClienteTimerTask 
			import java.util.Calendar;
			import java.util.Date;
			import java.util.HashMap;

			import org.apache.struts2.ServletActionContext;
			import org.springframework.mock.web.MockHttpServletRequest;
			import org.springframework.transaction.annotation.Transactional;

			import com.opensymphony.xwork2.ActionContext;
			import com.opensymphony.xwork2.config.Configuration;
			import com.opensymphony.xwork2.config.ConfigurationManager;
			import com.opensymphony.xwork2.config.providers.XWorkConfigurationProvider;
			import com.opensymphony.xwork2.inject.Container;
			import com.opensymphony.xwork2.util.ValueStack;
			import com.opensymphony.xwork2.util.ValueStackFactory;
			import com.zeni.app.model.core.BaseModel;
			import com.zeni.app.service.MailEngine;
			import com.zeni.app.service.UniversalManager;
			import com.zeni.app.webapp.action.CRMFichaClienteAction;

			public class CrmDocRiesgoClienteTimerTask extends AbstractAppTimerTask{

				@Override protected String getLogTitle(){return "timerTask de prueba";}
				@Override protected Date getActivationTime(){return makeActivationTime(13,41,0);}
				//@Override protected Long getPeriod(){return makePeriodInDays(24);};
				@Override protected Long getPeriod(){return makePeriodInMinutes(10);};
				UniversalManager manager = BaseModel.getManager();
				
				
				@Transactional
				@Override protected void runTask() {
					
					try {
						lm.DEBUG_LOG_TIMERTASK_START("Email de CRM Documentacion de Riesgo", "runTask");
						log.error("Envio de email de RiesgoMesTimerTask - Inicio del timer");
						// Initialize ActionContext
				        ConfigurationManager configurationManager = new ConfigurationManager();
				        configurationManager.addContainerProvider(new XWorkConfigurationProvider());
				        Configuration config = configurationManager.getConfiguration();
				        Container container = config.getContainer();
				
				        ValueStack stack = container.getInstance(ValueStackFactory.class).createValueStack();
				        stack.getContext().put(ActionContext.CONTAINER, container);
				        ActionContext.setContext(new ActionContext(stack.getContext()));
				
				        ActionContext.getContext().setSession(new HashMap<String, Object>());
				        // populate the request so getRequest().getSession() doesn't fail in BaseAction.java
				        ServletActionContext.setRequest(new MockHttpServletRequest());
						lm.DEBUG_LOG_TIMERTASK("ActionContext Iniciado");
						log.error("ActionContext Iniciado");
						CRMFichaClienteAction action = new CRMFichaClienteAction();
						UniversalManager makeManager = makeManager();

						Calendar calendar = Calendar.getInstance();
						calendar.set(calendar.MONTH, calendar.get(calendar.MONTH)-2);
						//Se valida que sea el primer dia del mes
						if(calendar.get(5)==20){
							action.setManager(makeManager);
							action.setReportPath(this.getReportPath());
							MailEngine me = (MailEngine)makeBean("mailEngine");
							action.setMailEngine(me);
							action.docRiesgoMail();
							lm.DEBUG_LOG_TIMERTASK("Email enviados");
							log.error("Se manda el correo con la documentacion pendiente: " + calendar.getTime());									
						}	
					} catch (Exception e) {
						e.printStackTrace();
						lm.DEBUG_LOG_TIMERTASK(e);
					}
				}	
			}
		***Modificacion en CRMFichaClienteAction.java
			** Se crea metodo Metodo que ejecuta timerTask CrmDocRiesgoCienteTask.java
				//Metodo que ejecuta timerTask CrmDocRiesgoCienteTask.java
				@SuppressWarnings("unchecked")
				public void docRiesgoMail(){
					//Query con la data necesaria de los clientes 
					String qry ="SELECT c.id,cfc.id,cfc.riesgoCliente, cfc.mesDDJJ, cfc.anioDDJJ, cfc.mesCierreBalance, " +
							" (SELECT MAX(e.anio) FROM CRMBalance e WHERE e.crmFichaCliente.id = cfc.id AND e.fechaBaja IS NULL), " +
							" (SELECT COALESCE(COUNT(CL.id),0) FROM Cliente CL WHERE (CL.tipoSociedad.id=3944 OR CL.tipoSociedad.id=3946) AND CL.id = c.id) "+
						"FROM CRMFichaCliente cfc " +
						"JOIN cfc.cliente c " +
						"JOIN c.cuentasCliente cc " +
						"WHERE cc.nroCuenta >=50000 AND cc.nroCuenta < 70000 " +
						"AND cfc.fechaBaja IS NULL " +
						"AND c.fechaBaja IS NULL " +
						"AND cc.fechaBaja IS NULL ";
					
					List<Object[]> lista = manager.find(qry);

					for (Object[] obj : lista) {
						long idCliente = (obj[0] != null) ? Long.parseLong(obj[0].toString()): 0;
						long idCRM = (obj[1] != null) ? Long.parseLong(obj[1].toString()): 0;
						String riesgo = (obj[2] != null) ? obj[2].toString():"";
						int mesDoc = (obj[3] != null) ? Integer.parseInt(obj[3].toString()): 12;
						int anioDoc = (obj[4] != null) ? Integer.parseInt(obj[4].toString()): 0;
						int mesBalance = (obj[5] != null) ? Integer.parseInt(obj[5].toString()): 12;
						int anioBalance = (obj[6] != null) ? Integer.parseInt(obj[6].toString()): 0;
						int esFisica = (obj[7] != null) ? Integer.parseInt(obj[7].toString()): 0;
						int anio = 0;
						//Riesgo
						//(B)=Bajo  (M)=Medio  (A)=Alto
						anio = ("B".equals(riesgo))? 3 :("M".equals(riesgo))?2:1;
						//Fecha de vencimeinto del documento
						Calendar cal = Calendar.getInstance();
						Calendar fechaActual = Calendar.getInstance();
						//Se setean el tiempo de las fechas a 0
						fechaActual.set(Calendar.HOUR_OF_DAY, 0);
						fechaActual.set(Calendar.MINUTE, 0);
						fechaActual.set(Calendar.SECOND, 0);
						fechaActual.set(Calendar.MILLISECOND, 0);
						cal.set(Calendar.HOUR_OF_DAY, 0);
						cal.set(Calendar.MINUTE, 0);
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);
						//Valida si es persona fisica
						if (esFisica == 1){
							cal.set(anioDoc+anio, mesDoc-1, 1,0,0,0);
							System.out.println(cal.getTime());
							System.out.println(fechaActual.getTime());
						}else{
							//cal.set(anioBalance+anio, mesBalance-1, 1,0,0,0);
							cal.set(2020,6,20);
							System.out.println(cal.getTime());
							System.out.println(fechaActual.getTime());
						}
						//Compara la fecha de vencimiento del documento con la fecha actual, si es igual llama al metodo de envio de email
						if (fechaActual.compareTo(cal)==0){
							try {
								reclamarGeneralSend(idCliente); 
							} catch (Exception e) {
								e.printStackTrace();
							}
						}			
					}
				}
			** Se modifica metodo  reclamarGeneralSend(), se modifica el fetch que ejecuta el query
				//Forzar a Hibernate a traer todas las propiedades del pojo. por mas que tenga Lizy en Fetch Type.
				ArrayList<CuentaCliente> CcList = (ArrayList<CuentaCliente>) manager.find("from CuentaCliente cc join fetch cc.cliente c join fetch c.crmFichaCliente where cc.id=? " ,new Object[]{Long.valueOf(idCtaCte)});
			** Se modifica metodo  reclamarGeneralSend(), se ejecuta query para recuperar crmFichaCliente con fetch al cliente
				cl = (Cliente) manager.findById(Cliente.class, aux_cliente);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("clienteId", cl.getId());
				String qry ="SELECT crm FROM CRMFichaCliente crm FETCH ALL PROPERTIES join fetch crm.cliente WHERE crm.cliente.id = :clienteId";
				List<CRMFichaCliente> LcrmFcte = new ArrayList<CRMFichaCliente>();
				LcrmFcte=(List<CRMFichaCliente>)manager.executeQuery(false, qry, params);
				crmFcte = LcrmFcte.get(0);
				//crmFcte = cl.getCrmFichaCliente();
				//Object executeQuery(String query, String[] param, Object[] value);

	Pruebas_________________________________________________________________________________________________________
		***Datos
			Persona Fisica:
				•	Riesgo Bajo. Gallaratto, Damian Bernabe --> idCliente 27141
				•	Riesgo Medio. Curmona, Sebastian  -->idcrm 179805633   --idCliente 179805624
				•	Riesgo Alto. Fortuny, Fernando Luis --> 177958447  --> 177958440

			Persona jurídica:
				•	Riesgo Bajo. Agrotecnologia y Servicios S.A. --> 30419
				•	Riesgo Medio. Mondelez Argentina S.A.  --> 27628
				•	Riesgo Alto. Friar S.A  --> 

		** filtros por id para persona fisica
			idCliente == 197805624 || idCliente == 27141 || idCliente == 177958440
		**filtros por id para juridicas
			idCliente==30419 || idCliente == 27628

		***Pruebas 2

			Puerto Laharrague S.A. 218666732
			Credicuotas Consumo S.A. 196104258
			Inbor S.A 204613057  //arreglar año de balance cuando es null
			Banco Comafi S.A 188611798
			Salentein Argentina B.V. 30047
			Agrosem S.R.L.   ?id=29221&idCliente=29221
			Roberto Bauche Y Cia S.A.   id=30735&idCliente=30735
			Cereales Dec S.A.   id=44268002&idCliente=44267998
			Maiten Agropecuaria S.A. id=30832&idCliente=30832
			Codi S.A   id=104154364&idCliente=104154360
			MGP Logistics S.R.L id=211610573&idCliente=211610564
			INCREASECARD S.A.  id=218363167&idCliente=218363158
			Lartirigoyen Y Cia. S.A. id=28853&idCliente=28853
************************************************************************************************************************En Documentacion



96.-*****************************Simax 446 archivo en Excel, conteniendo los datos de las Liquidaciones Finales emitidas
	Usuario 	--> Aejandro 
	Ext			--> 	
	Area		--> 

	Descripción:
	En esta oportunidad, les solicitamos tengan a bien, nos puedan comenzar a generar y enviar adjunto al mail de finales, 
	un archivo en Excel, conteniendo los datos de las Liquidaciones Finales emitidas. Este archivo, nos permitirá identificar 
	rápidamente las que se encuentran conformes y detectar aquellas con diferencias y poder solicitarles rápidamente los 
	ajustes que permitan conformar la misma.

	Les adjuntamos un archivo en Excel como ejemplo, con la descripción y el formato de cada columna. Todos los datos a 
	contener en el archivo son en formato numérico. Dependiendo del dato de cada columna, podrán ser valores en positivo o 
	negativo. Describimos a continuación los datos solicitados en cada columna:

	a- CUIT EMISOR: Vuestro número de CUIT, sin guiones. Por Ejemplo 30621973173.
	b- Nro COE: El número de COE sin guiones. Por ejemplo: 331001234567
	c- Monto Pendiente de Liquidación (2.5%): Monto en pesos pendiente de Pago de cada parcial liquidada en el contrato.  
	d- Kilos Liquidados: total de kilos que se liquidan en esta final. En caso de ser un contrato a fijar, indicar los kilos 
		por el cual liquidan en esta final. Por ejemplo un contrato a fijar de 1.000 tns, tiene 5 fijaciones de 200 tns. y 
		emiten una liquidación final por 200 tns. los kilos a indicar serán 200.000 kilos.                                                      --> kilos
	e- Kilos Excedentes/Faltantes: en esta columna, se indicará solo en el caso que en la liquidación final se facturen o 
		se acrediten kilos. Excedente o faltantes liquidados dentro de la final. Por ejemplo, un contrato por 1.000 tns., 
		entregaron 1.002.360 kilos, ya habían cobrado parciales por 1.000 tns y cuando emiten la final facturan los 2.360 
		kilos de excedente. Estos 2.360 kilos se deben indicar en esta columna.
	f- Monto del Excedente o Faltante: en esta columna, se indicarán los pesos de los kilos indicados en la columna anterior.
	g- Diferencia de precio: monto en pesos, que resultará por la anulación de mercadería.
	h- Bonificación/Rebaja de Calidad: monto en pesos, resultante de las liquidación de calidad de la mercadería entregada, 
		incluyendo todos los rubros, excepto Proteínas.
	i- Bonificación/Rebaja por Proteínas: monto en pesos, resultante de la liquidación de proteínas.   											--> bonificacion/rebaja  ? misma columna
	j- Honorarios Cámara: monto en pesos, resultantes de los Honorarios Cámara.
	k- Sellados: monto en pesos, resultantes del impuesto de sellos. (De corresponder)
	l- Gastos de Acondicionamiento: monto en pesos, resultante por un servicio de Secada o Zarandeo. ( de corresponder)
	m- Almacenaje: monto en pesos, resultante de un almacenaje convenido, solo si es liquidado a la misma alícuota de IVA 
		del Principal. (de corresponder)
	n- IVA: monto en pesos, correspondiente a la sumatoria del IVA de todos los conceptos Gravados.
	o- Percepciones ARBA: montos en pesos. (de corresponder)
	p- Percepciones CABA: monto en pesos. (de corresponder)
	q- Percepciones S.Fe: monto en pesos. (de corresponder)
	r- Percepciones La Pampa: monto en pesos. (de corresponder)
	s- Total Comprobante: monto en pesos, correspondiente a la sumatoria de todos los rubros liquidados.


	Modificaciones_________________________________________________________________________________________________
		* Se modifica ExportacionArchivosAction.java
			** Se crea atributo con su set
				private ExportacionManagerSimple exportacionManagerADM;

				public void setExportacionManagerADM(ExportacionManagerSimple exportacionManagerADM) {
					this.exportacionManagerADM = exportacionManagerADM;
				}
			
			** Se crea metodo exportacionADM()
				public Boolean exportacionADM(CuentaCliente cc) {
					String vals =exportacionManager.getSystemConfig("exportarArchivosADM");
					if(vals.indexOf(cc.getCliente().getCuit())!=-1 ){return true;} else {return false;}
				}
			
			** Se modifica el metodo esportar(), se incluye el nuevo metodo en las validaciones y se llama al 
			metodo getExportacion de la clase ExportacionManagerImplADM
				if(!exportacionALG(cuentaCliente) 	   && !exportacionTARNET(cuentaCliente) && 
				   !exportacionTARNET_B(cuentaCliente) && !exportacionBIT(cuentaCliente)    && 
				   !exportacionEmerger(cuentaCliente)  && !exportacionAsp(cuentaCliente) && 
				   !exportacionAtanor(cuentaCliente) && !exportacionSynNide(cuentaCliente) && 
				   !exportacionFMC(cuentaCliente)      && !exportacionPhysys(cuentaCliente) && 
				   !exportacionRizobacter(cuentaCliente) && !exportacionADM(cuentaCliente)){...}

				}else if(exportacionADM(cuentaCliente)){
				    salida = exportacionManagerADM.getExportacion(exportacion, cuentaCliente, fechaDesde, DateUtil.finalDelDia(fechaHasta));							    
			  	}

		* Se modifica applicationContext.xml
			<!--ExportacionManagerADM-START-->
		    <bean id="exportacionManagerADM" class="com.zeni.app.service.impl.ExportacionManagerImplADM">
		        <property name="dao" ref="universalDao"/>
		        <property name="workflowManager" ref="workflowManager"/>
		    </bean>
		    <!--ExportacionManagerADM-END-->

		* Se modifica Base de Datos, la tabla sys_config+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			insert into sys_config values('exportarArchivosADM','30-62197317-3'

		* Se debe dar de alta al reporte por el SIC Facturacion-->exportar++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		* Se crea la clase ExportacionManagerImplADM.java
    
************************************************************************************************************************Por pase a testing



----------------------



	<Item name="ExportacionArchivoMenu" title="exportacionArchivoList.title" page="/editExportacionArchivos.html" width="250" roles="ROLE_USER,ROLE_FACTURACION"/>
	<Item name="ExportacionMenu" title="exportacionList.title" page="/exportacions.html" width="250" roles="ROLE_USER"/>


	<action name="editExportacionArchivos"
		class="com.zeni.app.webapp.action.ExportacionArchivosAction" method="edit">
		<interceptor-ref name="jsonValidationWorkflowStack" />
		<result>WEB-INF/pages/exportacionArchivosForm.jsp</result>
		<result name="error">WEB-INF/pages/exportacionArchivosForm.jsp</result>
	</action>

	        <s:submit id="btnSubmit" method="exportar" key="button.generar" value="Generar Archivos" cssClass="button" cssStyle="width: 210px"/>
        

        <action name="exportacionArchivos"
			class="com.zeni.app.webapp.action.ExportacionArchivosAction" method="save">
			<interceptor-ref name="jsonValidationWorkflowStack" />
			<result name="success">WEB-INF/pages/exportacionArchivosForm.jsp</result>
			<result name="cancel">WEB-INF/pages/exportacionArchivosForm.jsp</result>
		</action>