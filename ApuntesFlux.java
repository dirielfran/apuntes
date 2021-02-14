Contactos*****************************************************************************************************************
	Valor Humano
		Daniel Sitzer
		Guadalupe Sylveira

	Desarrolladores
		Damian Recobelli (ingreso) dricobelli
		hmujicadev

	Puertos  -- Proyecto asiganado
	
	autoplaza
	blueexpress
		Francisco lider proyecto
		alejandra hamilis lider tecnica
		matias ford desarrollador rect 


Micelaneos****************************************************************************************************************
	Puertos
		Maria Jose Padin Lider proyecto
		dario 
		nico


	Puertos Link
		https://www.fluxitports.com/
		https://medium.com/flux-it-thoughts

	Tareas
		matermost  --> Descargar

	*** Correo 
		correo: elvis.areiza@fluxit.com.ar 
		pass: Adventure262-

	***Intranet Flux
		User: elvis.areiza
		Pass: Adventure120-

	***Forticlient
		***PCE
			GW: 190.151.127.74 
			PUERTO: 2443 
			user: fluxit_pce 
			Pass: 6tshMe!F5.
		***Lirquen
			GW: GW: 164.77.210.178 
			PUERTO: 10443 
			user: fluxit
			Pass: flux1t0256

	*** Jboss
		user: root
		pass: danger120-

	***Autos 
			usuario 1111 pass pce123

			o 3333 pce123

		localhost:8080/autos

1072237

	***compara textos
		https://www.diffchecker.com/diff
	***Genera rut
		https://generarut.cl/
	***Digito verificador


	wsdltojava

	billingcontroller portal

Requerimientos***********************************************************************************************************



**********************************************************************************************EncryptacionPasswordLirquen
	Descripcion: Puerto Lirquen se encuentra en proceso de mejorar la seguridad de las aplicaciones en la terminal, 
	bajo este escenario manifiesta la necesidad de Encriptar todas las Contraseñas expuestas en los archivos de 
	configuración de Portal, Documental, Middleware y Gate. Sumados a estos archivos se debe agregar también la 
	Encriptación de las contraseñas de acceso a las Bases de Datos definidas en el archivo standalone.xml.

	Reunion con Nicolas Romeu
		Revizar las clases en ambiente pce autos feature/encriptacion_password
			PasswordUtils
			PasswordUtilsTest

	Documentacion:
		java -cp "C:\jboss-eap-6.4\modules\system\layers\base\org\picketbox\main\picketbox-4.1.1.Final-redhat-1.jar" org.picketbox.datasource.security.SecureIdentityLoginModule [PASSWORD]

	Solucion------------------------------------------------------------------------------------------------------
		Solución:

		Se procederá a encriptar las contraseñas expuestas de configuración de Portal, Documental, Middleware y 
		Gate, similar a lo que se desarrolló para el mismo escenario en PCE.										-->
		El proceso de encriptación consta de 2 partes. Por un lado se deben encriptar las contraseñas de acceso 
		a los datasources de los proyectos, las cuales se encuentran definidas en el archivo 
		[JBOSS_HOME]\standalone\configuration\standalone.xml. Para este paso se van a utilizar las librerías de 
		encriptación propias de Jboss con las cuales mediante la ejecución de un comando se puede obtener la 
		contraseña encriptada que luego deberá ser agregada al archivo de configuración.
		Para la encriptación de las contraseñas de los archivos de configuración ubicados en 
		[JBOSS_HOME]/modules/system/layers/base/com/lirquen/configuration/main se deberá crear un endpoint en 
		alguno de los proyectos el cual recibe como parámetro una contraseña y retorna la contraseña encriptada 
		que luego deberá ser copiada al archivo de configuración correspondiente. Una vez realizado este proceso 
		para todas las contraseñas se deberá identificar en todos los proyectos las porciones de código que hacen 
		uso de esas contraseñas y ejecutar la función de desencriptación para realizar la comparación.
		A modo de ejemplo el endpoint podría estar definido de la siguiente manera
			[SERVER_IP]:[PORT]/portal/api/password/encrypt?pass=[PASSWORD]

	Errores:
		No accedia al portal, por consola emite error 200, se validaron las vm en launch configuratio del server, se 
		le añadio serven red Hat Enterprice Application 6.1
			***se le agrego a la vm arguments
				-Dspring.profiles.active=development -Dorg.jboss.as.logging.per-deployment=false 




	Modificaciones:
		***lirquen-portal-profiles.xml
			   
			<beans profile="production" >
			<!-- 		<bean id="simpleCors" class="com.lirquen.portal.security.DefaultFilter" /> -->
				<bean id="simpleCors" class="com.lirquen.portal.security.SimpleCORSFilter" />
		    </beans>

		Proyecto documental*****************************************************************************************
			*AuthorizationHttpRequestInterceptor.java, se modifca metodo getAuthorization() el cual solicita mdw.password
				public String getAuthorization() {
					if (this.authorization == null) {
						String decryptedPass = PasswordUtils.decrypt(password);
						String auth = this.username + ":" + decryptedPass;
				        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
				        this.authorization = "Basic " + new String(encodedAuth);
					}
					return authorization;
				}
			*PasswordController.java, se crea la clase para el servicio de encriptacion
				@Controller
				@RequestMapping("/api/password")
				public class PasswordController {

					@RequestMapping(value = "encrypt", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON )
					public @ResponseBody String encryptPassword(@RequestParam(value = "pass") String password) throws ServiceException {
						if(StringUtils.isBlank(password)) {
							throw new ServiceException("La contraseña a encriptar no debe ser vacío");
						}
						return PasswordUtils.encrypt(password);
					}
				}	
			*PasswordUtils.java, se crea la clase.
			*BasicAuthAuthenticationInterceptor.java, se modifica metodo handleMessage(), el cual solicita security.password

				@Override
				public void handleMessage(Message message) throws Fault {
					AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);

					// Verificar policy
					if (policy == null) {
						if (LOG.isDebugEnabled()) {
							LOG.debug("User attempted to log in with no credentials");
						}
						sendErrorResponse(message, HttpURLConnection.HTTP_UNAUTHORIZED);
						return;
					}

					if (LOG.isDebugEnabled()) {
						LOG.debug("Logging in use: " + policy.getUserName());
					}

					// Verificar password
					String decryptedPassword = PasswordUtils.decrypt(password);
					if (!username.equals(policy.getUserName())
							|| !decryptedPassword.equals(policy.getPassword())) {
						LOG.warn("Invalid username or password for user: "
								+ policy.getUserName());
						sendErrorResponse(message, HttpURLConnection.HTTP_FORBIDDEN);
					}
				}
			*BasicAuthenticationProvider, se modifica metodo authenticate el cual obtiene el password encriptado 
			de userDetailsService security.password
				@Override
				public Authentication authenticate(Authentication authentication) throws AuthenticationException {
					UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
					UserDetails user = userDetailsService.loadUserByUsername(token.getName());
					String decryptedPass = PasswordUtils.decrypt(user.getPassword());
					if (user == null || !decryptedPass.equals(token.getCredentials())) {
						throw new BadCredentialsException("invalid user or passsword");
					}
					return new UsernamePasswordAuthenticationToken(token.getName(),
							token.getCredentials(), user.getAuthorities());
				}
			*XAuthTokenFilter.java, se modifica metodo doFilter(), solicita login.password
				String decryptedPass = PasswordUtils.decrypt(passwordMock);
            			details = new User(username, decryptedPass, authorities);
            *AuthenticationManagerMock.java se modifica metodo authenticate(), el cual solicita login.password
            	
				if((usernameMock.equals(authentication.getName()) || plannerUsernameMock.equals(authentication.getName())) 
						&& decryptedPass.equals(authentication.getCredentials())){
					Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
					authorities.add(new SimpleGrantedAuthority(rolesMock));
					result = new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), authorities);
				}
			*AduanaBOImpl.java, se modifica getResponse(), el cual solicita manifest.service.pass
				String decryptedPass = PasswordUtils.decrypt(manifestServicePass);
				String result = wsdlConsultasManifiestoSoap.getDocumentosxRol(
						manifestServiceRut, manifestServiceUser,
						decryptedPass, sw.toString());
			*lirquen-portal-mail.xml, Se comenta creacion de bean --> javaMailSender
			*MailConfiguration.java, se crea lase de configuracion para la creacion de bena --> javaMailSender
		Proyecto portal*********************************************************************************************
			*PasswordUtils.java, se crea la clase.
			*AuthorizationHttpRequestInterceptor.java, se modifca metodo getAuthorization() el cual solicita lirquen-mdw.password
				public String getAuthorization() {
					if (this.authorization == null) {
							
						String auth = this.username + ":" + decryptedPass;
				        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
				        this.authorization = "Basic " + new String(encodedAuth);
					}
					return authorization;
				}
			*AduanaDusGuiaBOImpl.java, se modifican los metodos consultaDus() y consultaGuia(), el cual solicita aduana.consulta.ws.password
				@Override
				public RespuestaType consultaDus(Long numeroDus) throws BOException {
					ConsultaDusType parameter = new ConsultaDusType();
					String decryptedPassword = PasswordUtils.decrypt(password);
					parameter.setLogin(username);
					parameter.setPassword(decryptedPassword);
					parameter.setNumeroDus(numeroDus);
					return consultaDus.consultaDusOp(parameter);
				}

				@Override
				public RespuestaType consultaGuia(Long numeroGuia, String rutExportador) throws BOException {
					ConsultaGuiaType parameters = new ConsultaGuiaType();
					String decryptedPassword = PasswordUtils.decrypt(password);
					parameters.setLogin(username);
					parameters.setPassword(decryptedPassword);
					parameters.setNumeroGuia(numeroGuia);
					parameters.setRutExportador(rutExportador);
					// no enviar fecha
					// parameters.setFechaEmision();
					return consultaGuia.consultaGuiaOp(parameters);
				}	
			*lirquen-portal-security.xml, se crea bean authenticationProvider y se le agrega al AuthenticationManager, 
			se comenta usuario de servicio
				<bean id="authenticationUserService" class="com.lirquen.portal.security.AuthenticationProviderN4Api">		
			        <property name="userServices" ref="userServices" />
			    </bean> 

			    <!-- 	<sec:user-service id="basicUserDetailsService"> -->
				<!-- 		<sec:user name="${services-rest.username}" password="${services-rest.password}"	authorities="ROLE_USER" /> -->
				<!-- 		<sec:user name="${config.username}" password="${config.password}" authorities="ROLE_CONFIG" /> -->
				<!-- 	</sec:user-service> -->

			      		<sec:authentication-manager alias="authenticationManager">
					<!--      	<sec:authentication-provider user-service-ref="basicUserDetailsService"/> -->
					        <sec:authentication-provider ref="authenticationProviderDB"/>       		
					<!--         <sec:authentication-provider ref="authenticationProviderLDAP"/> -->
					        <sec:authentication-provider ref="authenticationProviderN4"/>
					        <sec:authentication-provider ref="authenticationUserService"/>
					    </sec:authentication-manager>
			* MailConfiguration.java
				@Configuration
				public class MailConfiguration {
					
				 	@Value("${mail.smtp}")
				 	private String host;
				 	
				 	@Value("${mail.port}")
				 	private Integer port;
				 	
				 	@Value("${mail.username}")
				 	private String username;
				 	
				 	@Value("${mail.password}")
				 	private String password;
				 	
				 	@Value("${mail.auth}")
				 	private String auth;
				 	
				 	@Value("${mail.tls}")
				 	private String tls;
					
					@Bean(name="javaMailSender")
					public JavaMailSender getJavaMailSender() {
					    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
					    mailSender.setHost(host);
					    mailSender.setPort(port);
					    
					    mailSender.setUsername(username);
					    mailSender.setPassword(password);
					    
					    Properties props = mailSender.getJavaMailProperties();
					    props.put("mail.smtp.auth", auth);
					    props.put("mail.smtp.starttls.enable", "true");
					    props.put("mail.debug", "true");
					    
					    return mailSender;
					}
				}, se refactoriza la creacion de bean javaMailSender
			*lirquen-portal-mail.xml, se comenta creacion de javaMailSender
			*AuthenticationProviderN4Api.java, se crea provider
		Proyecto mdw************************************************************************************************
			*beans.xml, se incluye scan de paquetes se comentan beans
				<context:component-scan base-package="com.lirquen.mdw.config" />

				<!-- 	<bean id="uQueryInvoker" class="com.fluxit.n4api.invoker.UQueryInvoker"> -->
				<!-- 		<constructor-arg value="${n4api.url}/apex/api/query" /> -->
				<!-- 		<constructor-arg value="${n4api.user}" /> -->
				<!-- 		<constructor-arg value="${n4api.password}" /> -->
				<!-- 	</bean> -->
					
				<!-- 	<bean id="genericServiceInvoker" class="com.fluxit.n4api.invoker.GenericServiceInvoker"> -->
				<!-- 		<constructor-arg value="${n4api.url}/apex/services/argoservice" /> -->
				<!-- 		<constructor-arg value="${n4api.user}" /> -->
				<!-- 		<constructor-arg value="${n4api.password}" /> -->
				<!-- 	</bean> -->
			*N4Configuration.java, se crea clase de configuracion que crea los beans necesarios para N4Api
				/**
				 * Clase de configuracion de Spring que crea los beans necesarios para la API de
				 * N4.
				 * 
				 * @author elvis.areiza@fluxit.com.ar
				 *
				 */

				@Configuration
				public class N4Configuration {

				 	@Value("${n4api.url}/apex/api/query")
				   	private String url;

				    @Value("${n4api.user}")
				   	private String user;

				    @Value("${n4api.password}")
				   	private String password;
				    
				 	@Value("${n4api.url}/apex/services/argoservice")
				   	private String urlArgoService;


					@Bean
					public UQueryInvoker uQueryInvoker() {
						String decryptedPass = PasswordUtils.decrypt(this.password);
						return new UQueryInvoker(url,user,decryptedPass);
					}
					
					@Bean
					public GenericServiceInvoker genericServiceInvoker() {
						String decryptedPass = PasswordUtils.decrypt(this.password);
						return new GenericServiceInvoker(urlArgoService,user,decryptedPass);
					}
				}
			*AuthorizationHttpRequestInterceptor.java, se modifca metodo getAuthorization() el cual solicita lirquen-mdw.password
				public String getAuthorization() {
					if (this.authorization == null) {
						String decryptedPass = PasswordUtils.decrypt(password);
						String auth = this.username + ":" + decryptedPass;
				        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
				        this.authorization = "Basic " + new String(encodedAuth);
					}
					return authorization;
				}
			*BasicAuthAuthenticationInterceptor.java,  se modifica metodo handleMessage(), el cual solicita mdw.password
				// Verificar password
				String decryptedPassword = PasswordUtils.decrypt(password);
				if (!username.equals(policy.getUserName())
						|| !decryptedPassword.equals(policy.getPassword())) {
					log.warn("Invalid username or password for user: "
							+ policy.getUserName());
					sendErrorResponse(message, HttpURLConnection.HTTP_FORBIDDEN);
				}
			*SOAPAuthenticationInterceptor.java,  se modifica metodo handleMessage(), el cual solicita mdw.password
				// Verificar password
				String decryptedPassword = PasswordUtils.decrypt(password);
				if (!username.equals(user) || !decryptedPassword.equals(pass)) {
					log.warn("Invalid username or password for user: " + user);
					throw new Fault(new AuthenticationException("Invalid username or password for user: " + user));
				}			
			*beans.xml, se agrega bean para LDAP que busca ldap.password
				<!-- LDAP integration -->
				<ldap:context-source
			          url="${ldap.server}"
			          base="${ldap.base}"
			          authentication-source-ref="ldap-auth-source"/>
			          
			    <bean id="ldap-auth-source" class="com.pce.middleware.ldap.LDAPCustomAuthenticationSource">
			   	     <property name="username" value="${ldap.username}"/>
				     <property name="password" value="${ldap.password}"/>
			    </bean>	

			    * Se comenta la creacion de los bean, se le realiza refactoring
			    	<!-- Factories de N4 -->
					<!-- 	<bean id="uQueryInvoker" class="com.fluxit.n4api.invoker.UQueryInvoker"> -->
					<!-- 		<constructor-arg value="${n4api.url}/apex/api/query" /> -->
					<!-- 		<constructor-arg value="${n4api.user}" /> -->
					<!-- 		<constructor-arg value="${n4api.password}" /> -->
					<!-- 	</bean> -->
						
					<!-- 	<bean id="genericServiceInvoker" class="com.fluxit.n4api.invoker.GenericServiceInvoker"> -->
					<!-- 		<constructor-arg value="${n4api.url}/apex/services/argoservice" /> -->
					<!-- 		<constructor-arg value="${n4api.user}" /> -->
					<!-- 		<constructor-arg value="${n4api.password}" /> -->
					<!-- 	</bean> -->
			*AduanaBOImpl.java, se modifca metodos al solicitar service.aduana.password y aduana.almacen.password
				@Override
				public DinsManifiesto solicitaDinsNumeroBlRut(SolicitudDinsNumeroBlRut solicitud) throws BOException {
					String xml = procesarSolicitud(solicitud, SolicitudDinsNumeroBlRut.class);
					String decryptedPassword = PasswordUtils.decrypt(pass);
					String respuesta = consultaDinSoap.solicitaDinsNumeroBlRut(user, decryptedPassword, xml);
					DinsManifiesto dinsManifiesto = null;
					try {
						dinsManifiesto = XmlUtils.xmlToObject(respuesta, DinsManifiesto.class);
					} catch (JAXBException e) {
						procesarMsgError(respuesta);
					}
					return dinsManifiesto;
				}

				@Override
				public DinsManifiesto solicitaDinsManifiesto(SolicitudDinsManifiesto solicitudDinsManifiesto) throws BOException {
					String xml = procesarSolicitud(solicitudDinsManifiesto, SolicitudDinsManifiesto.class);
					String decryptedPassword = PasswordUtils.decrypt(pass);
					String respuesta = consultaDinSoap.solicitaDinsManifiesto(user, decryptedPassword, xml);
					DinsManifiesto dinsManifiesto = null;
					try {
						dinsManifiesto = XmlUtils.xmlToObject(respuesta, DinsManifiesto.class);
					} catch (JAXBException e) {
						procesarMsgError(respuesta);
					}
					return dinsManifiesto;
				}

				@Override
				public Din solicitaDin(SolicitudDin solicitudDin) throws BOException {
					String xml = procesarSolicitud(solicitudDin, SolicitudDin.class);
					String decryptedPassword = PasswordUtils.decrypt(pass);
					String respuesta = consultaDinSoap.solicitaDin(user, decryptedPassword, xml);
					Din din = null;
					try {
						din = XmlUtils.xmlToObject(respuesta, Din.class);
					} catch (JAXBException e) {
						procesarMsgError(respuesta);
					}
					return din;
				}

					@Override
					public AlmacenResponse entregaAlmacen(RequestEntregaAlmacen request)	throws BOException {
						try {
							AlmacenResponse message = null;
							/*LIRQUEN-44
							 * Siempre la fecha del evento debe ser mayor a la de la carga de TATC, esto debe aplicarse tanto para BLs FULL Y EMPTY. 
							 * MTY: fecha-operacion -> FECHA ACTUAL EJECICIÃ“N
							 *      fecAceptacionDocRetiro  -> FECHA CARGA DE TATC 
							 *      
							 * FULL: fecha-operacion -> FECHA ACTUAL EJECICIÃ“N
							 *      fecAceptacionDocRetiro  -> FECHA DeclaraciÃ³n de ingreso (DI) (manifiesto)
							 */
							Informe inf = new Informe();
							inf.setTipoOperacion("E");
							inf.setTipoDocumento("BL");

							inf.setNumeroDocTransporteMadre(request.getBlMaster());
							inf.setNumeroDocTransporte(request.getBl());
							inf.setTipoDocRetiro(request.getDocRetiro());
							inf.setNumDocRetiro(StringUtils.isBlank(request.getNumDocRetiro()) ? "1": request.getNumDocRetiro());
							inf.setNumeroManifiesto(request.getManifiesto());
							inf.setFechaOperacion(request.getFechaOperacion());
							inf.setFecAceptacionDocRetiro(request.getFechaAceptacionDocRetiro());

							InformeRetiroAlmacen informeRA = new InformeRetiroAlmacen();
							informeRA.addInforme(inf);

							InformarEntregaAlmacen parameters = new InformarEntregaAlmacen();
							parameters.setLogin(userAlmacen);
							String decryptedPassword = PasswordUtils.decrypt(passAlmacen);
							parameters.setClave(decryptedPassword);
							parameters.setInformeRetiroAlmacen(informeRA);

							LOG.info("ADUANA. Entrega Almancen. Envio de mensaje: " + getLogMessage(inf));
							InformarEntregaAlmacenResponse response = almacenSoap.informarEntregaAlmacen(parameters);
							String codigo = response.getRespuestaAduana().getCodigo();
							String mensaje = response.getRespuestaAduana().getTexto();

							if (CollectionUtils.isNotEmpty(response.getRespuestaAduana().getRespuesta())){
								RespuestaAduana.Respuesta respuesta = response.getRespuestaAduana().getRespuesta().get(0);
								message = new AlmacenResponse(codigo, mensaje, respuesta.getCodigo(), respuesta.getTexto());
							}
							else {
								message = new AlmacenResponse(codigo, mensaje);
							}
							LOG.info("ADUANA. Entrega Almancen. Respuesta: " + getLogMessage(request.getManifiesto(), request.getBl(), message));
							return message;

						} catch (Exception e) {
							throw new BOException(e);
						}
					}

						@Override
						public AlmacenResponse recepcionAlmacen(RequestRecepcionAlmacen request) throws BOException {
							try {
								AlmacenResponse message = null;
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(new Date());
								calendar.add(Calendar.HOUR_OF_DAY, -1);
								String today = DateUtils.format(calendar.getTime(), DateUtils.dateFormat1);

								InformeRecepcionAlmacen.Informe inf = new InformeRecepcionAlmacen.Informe();
								inf.setTipoOperacion("R");
								inf.setTipoDocumento("BL");
								inf.setNumeroDocTransporteMadre(request.getBl());
								inf.setNumeroDocTransporte("");
								inf.setNumeroManifiesto(request.getManifiesto());
								inf.setFechaOperacion(today);

								InformeRecepcionAlmacen informeRA = new InformeRecepcionAlmacen();
								informeRA.addInforme(inf);

								InformarRecepcionAlmacen parameters = new InformarRecepcionAlmacen();
								parameters.setLogin(userAlmacen);
								String decryptedPassword = PasswordUtils.decrypt(passAlmacen);
								parameters.setClave(decryptedPassword);
								parameters.setInformeRecepcionAlmacen(informeRA);

								LOG.info("ADUANA. Recepcion Almacen. Envio de mensaje: " + getLogMessage(inf));
								InformarRecepcionAlmacenResponse response =  almacenSoap.informarRecepcionAlmacen(parameters);
								String codigo = response.getRespuestaAduana().getCodigo();
								String mensaje = response.getRespuestaAduana().getTexto();

								if (CollectionUtils.isNotEmpty(response.getRespuestaAduana().getRespuesta())){
									RespuestaAduana.Respuesta respuesta = response.getRespuestaAduana().getRespuesta().get(0);
									message = new AlmacenResponse(codigo, mensaje, respuesta.getCodigo(), respuesta.getTexto());
								}
								else {
									message = new AlmacenResponse(codigo, mensaje);
								}
								LOG.info("ADUANA. Recepcion Almacen. Respuesta: " + getLogMessage(request.getManifiesto(), request.getBl(), message));
								return message;

							} catch (Exception e) {
								throw new BOException(e);
							}
						}
			*LDAPCustomAuthenticationSource.java , se crea clase ue implementa el bean ldap-auth-source
			*PasswordUtils.java, se crea la clase que realiza la encriptacion y desencriptacion
		Proyecto Gate***********************************************************************************************
			*AuthorizationHttpRequestInterceptor.java, se modifica metodo getAuthorization(), se encripta el password
					public String getAuthorization() {
						if (this.authorization == null) {
							String decryptedPass = PasswordUtils.decrypt(password);
							String auth = this.username + ":" + decryptedPass;
					        byte[] encodedAuth = Base64.encode(auth.getBytes(StandardCharsets.UTF_8));
					        this.authorization = "Basic " + new String(encodedAuth);
						}
						return authorization;
					}		
			*PasswordUtils.java, se crea clase para la encriptacion --> 
			*XAuthTokenFilter.java, se desencriptan los password --> login.password   --> login.password.cfsvgm
				String decryptedPass = PasswordUtils.decrypt(passwordMock);
                String decryptedPassCfsVgm = PasswordUtils.decrypt(passwordMockCfsVgm);
            *AuthenticationManagerMock.java, se desencriptan los password --> login.password   --> login.password.cfsvgm
                String decryptedPass = PasswordUtils.decrypt(passwordMock);
                String decryptedPassCfsVgm = PasswordUtils.decrypt(passwordMockCfsVgm);
*************************************************************************************************************************En Desarrollo




****************************************************************************************IncidenciaEncryptacionPCEGroovys
	Descripcion: Se enciuentra incidencia al consumir los servicios groovys, error No Se puede establecer conexionn 
	con N4

	Modificacion:
		***pcemdw
			*Se modifica N4Configuration.java
				*Se modifica n4ArgoDaoFactory(), se agrega desemcriptacion en metodo GenericServerInvoker()
					GenericServiceInvoker argoServiceInvoker = new GenericServiceInvoker(
						urlService, env.getProperty(ARGO_BILLING_USER),
						decryptedPassword);
				*Se modifica n4ArgoDaoFactory() y n4ArgoBillingDaoFactory(), se agrega desemcriptacion en metodo 
				GenericServerInvoker()
					GenericServiceInvoker argoServiceInvoker = new GenericServiceInvoker(
						urlService, env.getProperty(ARGO_BILLING_USER),
						decryptedPassword);
************************************************************************************************************************En Produccion


*********************************************************************************Reactivacion de patentes por sus medios
	*Proyecto PCE-autos, se modifica GateTXDAO, se modifica query
			@Query("select g from GateTx as g where (g.gateOut IS NULL OR g.reactivation IS true) AND g.type = 'POR_SUS_MEDIOS'")
			public List<GateTx> findGatePsm();
************************************************************************************************************************




************************************************************************************************************************
	clientes:
		puertos central --> chile
			soporte evolutivo
		terminal linker 


	redmine herramienta de tickets

	daria ted plata --> verizo
		soporte portal de negocios
		desarrollo evolutivo

	termminal sarate  --> sarate  
		desarrollo evolutivo
		incidencias
		N4

	

Comandos*****************************************************************************************************************
	//Empaquetar el proyecto desde la consola
	mvn clean install -Dmaven.test.skip=true

	mvn install -Dmaven.test.skip=true

	jdbc:sqlserver://10.11.17.138:1433
mvn clean install -Dmaven.test.skip=true

	
//Creacion de bean con el constructor
<bean id="mybean" class="carmelo.spring.xml.MyBean">
    <constructor-arg value="150" />
    <constructor-arg value="Este es un mensaje..." />
</bean>

<bean id="mybean" class="carmelo.spring.xml.MyBean">
    <property name="valor" value="150"/>
    <property name="mensaje" value="Este es un mensaje..."/>
</bean>

//Hacer referencia a otro bean

<bean id="otherbean"
      class="carmelo.spring.xml.OtherBean"
p:myBean-ref="mybean" />


<bean id="otherbean" class="carmelo.spring.xml.OtherBean">
    <!-- inyectar por constructor -->
    <!--<constructor-arg ref="mybean" />-->
    
    <!-- inyectar por setter -->
    <property name="myBean" ref="mybean"/>
</bean>


//Crear beans internos
En código Java tenemos clases internas y en Spring beans internos, 
estos beans son creados dentro de un bean padre y solo están 
accesibles para este, normalmente no indicamos su ID ya que no 
requerimos, además, el scope que se les aplica por defecto es 
prototype.

<bean id="innerbean" class="carmelo.spring.xml.OtherBean">
    <property name="myBean">
        <bean class="carmelo.spring.xml.MyBean"
              p:valor="10101" p:mensaje="InnerBean..." />
    </property>
</bean>
Previamente establecimos la propiedad myBean como una referencia a 
otro bean, en este caso en lugar de referenciar a otro bean lo que 
hicimos fue crear un bean interno para uso exclusivo de esta 
propiedad.



public class MyBean {

    public MyBean() {
    }

    public MyBean(Integer valor, String mensaje) {
        this.valor = valor;
        this.mensaje = mensaje;
    }

    private Integer valor;
    private String mensaje;

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}


<bean id="mybean"
      class="carmelo.spring.xml.MyBean"/>

<bean id="mybean" class="carmelo.spring.xml.MyBean">
    <constructor-arg value="150" />
    <constructor-arg value="Este es un mensaje..." />
</bean>


public class OtherBean {
    
    private MyBean myBean;

    public OtherBean() {
    }

    public OtherBean(MyBean myBean) {
        this.myBean = myBean;
    }

    public MyBean getMyBean() {
        return myBean;
    }

    public void setMyBean(MyBean myBean) {
        this.myBean = myBean;
    }

}

<bean id="otherbean" class="carmelo.spring.xml.OtherBean">
    <!-- inyectar por constructor -->
    <!--<constructor-arg ref="mybean" />-->
    
    <!-- inyectar por setter -->
    <property name="myBean" ref="mybean"/>
</bean>



https://www.logicbig.com/tutorials/spring-framework/spring-security/user-details-service.html





***********************************************

0.930 *190 platanos