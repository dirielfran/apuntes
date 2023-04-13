anotaciones*****************************************************************************************
	@Test 
	@DisplayName
	@Nested
	@Tag 
	@ExtendWith
	@BeforeEach 
	@AfterEach
	@BeforeAll
	@afterAll
	@Disabled
	@Order We can use the @Order annotation to enforce tests to run in a specific order.

****************************************************************************************************
Inicio del Proyecto*********************************************************************************
******************************************************************************Seccion 1:Introduccion
	1.- Instalacion de Intellj DEA
		Referencia --> https://www.jetbrains.com/idea/download/#section=windows
****************************************************************************************************
***********************************************************************************Seccion 2:JUnit 5
**********************************************************************************************Atajos
	ctrl+F12 						--> estructura
	ctrl+d 							--> compara dos archivos  ***
									--> duplica select block en actual linea
	ctrl + mayus + arriba/abajo 	--> mover lineas de codigo ***
	alt + insert 					--> agregar ***
	alt + intro 					--> sugerencias ***
	ctrl + shift + F10				--> run test
	ctrl + /						--> comenta linea
	ctrl + tab 						--> seleccion entre pestañas
	alt + 1 						--> Amplio Editor ***
	ctrl + alt + v 					--> Se completa entidad con su tipo ***
	shift + alt						--> para poner varios tab ***
	ctrl + shift + v 				--> Despliega opciones con contenido copiado ctrl + c
	ctrl + w 						--> expande la seleccio 
	ctrl + alt + L 					--> Ordena formatea ***
	ctrl + shift + N 				--> Busqueda por archivo, clase  ***
	ctrl + q 						--> ver documentacion ***
	ctrl + p 						--> ver parametros ***
	ctrl + b 						--> ver declaraciones ***
	Ctrl+Alt+Mayús+T 				--> list of refactorings
	ctrl + x 						--> elimina linea ***
	shift + f6 						--> Se renombra
	sout + m 						--> agrega el print con la clase y metodo actual
	sout + p 						--> print de los argumentos del metodo
	ctrl + click					--> parado en metodo se va a la implementacion
	ctr + alt + B 					--> pasar a la implementacion del metodo  desde una interfaz 
	ctrl + alt + F12 				--> ver estructura de la clase
	ctrl + alt + L 					--> Ordena el codigo
	ctr + alt + s 					--> setting
****************************************************************************************************
************************************* Seccion 2: Junit5 ********************************************
6.- Configuracion del Proyecto**********************************************************************
	0.-  Se crea el proyecto springTest
		    <groupId>com.areiza</groupId>
		    <artifactId>junitApp</artifactId>
		    <version>1.0-SNAPSHOT</version>
	1.- Se agregan dependencias
		<dependencies>
	        <dependency>
	            <groupId>org.junit.jupiter</groupId>
	            <artifactId>junit-jupiter</artifactId>
	            <version>5.8.0-M1</version>
	        </dependency>
	    </dependencies>
	2.- Creamos paquete models --> com.eareiza.junit.models
	3.- Se crea clase java dentro de models Cuenta
		package com.eareiza.junit5.ejemplos.models;

		import java.math.BigDecimal;

		public class Cuenta {
		    private String persona;
		    private BigDecimal saldo;

		    public String getPersona() {
		        return persona;
		    }

		    public void setPersona(String persona) {
		        this.persona = persona;
		    }

		    public BigDecimal getSaldo() {
		        return saldo;
		    }

		    public void setSaldo(BigDecimal saldo) {
		        this.saldo = saldo;
		    }
		}
****************************************************************************************************
7.- Primeras Pruebas Unitarias**********************************************************************
****************************************************************************************assertEquals
******************************************************************************************assertTrue
	1.- Parado en la clase Cuenta, se crea clase spring 
		1.1 Se ejcuta --> alt + Insert
		Nota: Se creane en test el paqete de la clase y la clase CuentaTest
	2.- Se modifica CuentaTest
		2.1.- Se crea prueba --> alt + insert --> test Method
			@Test
		    void testNombreCuenta() {
		        Cuenta cuenta = new Cuenta();
		        cuenta.setPersona("Elvis");
		        Assertions.assertEquals("Elvis", cuenta.getPersona());
		    }
		2.2.- Se ejecuta con shft + alt + F10
			Nota: Debe dar success
		2.3.- Se fuerza error comentando linea
			    @Test
			    void testNombreCuenta() {
			        Cuenta cuenta = new Cuenta();
			        //cuenta.setPersona("Elvis");
			        Assertions.assertEquals("Elvis", cuenta.getPersona());
			    }
			2.3.1.- Se prueba ctr+alt+F10
				Nota: Debe dar error
		2.4.- Se fuerza otro error 
			2.4.1 Se modifica Cuenta.java, se crea constructor
				    public Cuenta(String persona, BigDecimal saldo) {
				        this.saldo = saldo;
				    }
			2.4.1 Se modifica metodo CuentaTest, se modifica metodo testNombreCuenta y se prueba
				@Test
			    void testNombreCuenta() {
			        Cuenta cuenta = new Cuenta("Elvis",new BigDecimal("100.2345"));
			        //cuenta.setPersona("Elvis");
			        Assertions.assertEquals("Elvis", cuenta.getPersona());
			    }
			   	 Nota: Debe dar error.
			2.4.2 Se modifica Cuenta el constructor
				public Cuenta(String persona, BigDecimal saldo) {
			        this.saldo = saldo;
			        this.persona = persona;
			    }
			    Nota: Debe dar success
		2.5.- Se prueba con assertEquals
			@Test
		    void testNombreCuenta() {
		        Cuenta cuenta = new Cuenta("Elvis",new BigDecimal("100.2345"));
		        //cuenta.setPersona("Elvis");
		        assertEquals("Elvis", cuenta.getPersona());
		        assertTrue(cuenta.getPersona().equals("Elvis"));
		    }
		   	Nota: debe ser success
****************************************************************************************************
8.- Test para saldo*********************************************************************************
*****************************************************************************************assertFalse
	1.- Se crea metodo  
		@Test
	    void testSaldoCuenta() {
	        Cuenta cuenta = new Cuenta("Ricardo", new BigDecimal("100.1234"));
	        assertEquals(100.1234, cuenta.getSaldo().doubleValue());
	    }
	    Nota: Debe generar success
	2.- Se prueba con assertFalse, que el saldo sea mayor a 0
	    @Test
	    void testSaldoCuenta() {
	        Cuenta cuenta = new Cuenta("Ricardo", new BigDecimal("100.1234"));
	        assertEquals(100.1234, cuenta.getSaldo().doubleValue());
	        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
	    }
****************************************************************************************************
9.- Test Driven Development TDD con JUnit***********************************************************
*************************************************************************************assertNotEquals
	1.- Se modifica CuentaTest, Se compara la referencia en memoria de dos obj con assertNoyEquals 
		 @Test
	    void testReferenciaCuenta() {
	        Cuenta antonio = new Cuenta("Antonio", new BigDecimal("567.876"));
	        Cuenta diego = new Cuenta("diego", new BigDecimal("349.876"));

	        assertNotEquals(antonio,diego);
	    }
	    Nota: Debe dar Success 
	2.-  Comparacion por valor y no por referencia
		2.1.- Se valida por valor con metodo assertEquals()
			@Override
		    public boolean equals(Object obj) {	
		        if(!(obj instanceof Cuenta)){
		            return false;
		        }
		        Cuenta cuenta = (Cuenta) obj;
		        if(this.persona == null || this.saldo == null){
		            return false;
		        }
		        return this.persona.equals(cuenta.getPersona()) && this.saldo.equals(cuenta.getSaldo());
		    }
		2.2.- Se modifica CuentaTest, el metodo testReferenciaCuenta()
			@Test
		    void nameReferenciaCuenta() {
		        Cuenta antonio = new Cuenta("Antonio", new BigDecimal("567.876"));
		        Cuenta diego = new Cuenta("Antonio", new BigDecimal("567.876"));
		        //assertNotEquals(antonio,diego);
		        assertEquals(antonio,diego);
		    }
		    Nota: Debe dar Success 
****************************************************************************************************
10.- TDD para debito y credito**********************************************************************
***************************************************************************************assertNotNull
	1.- Se modifica CuentaTest.java, se crean los metodos para probar los futuros metodos de 
		debito y credito
		@Test
	    void testDebitoCuenta() {
	        Cuenta cuenta = new Cuenta("Elvis", new BigDecimal("1000.23"));
	        cuenta.debito(new BigDecimal(100));
	        assertNotNull(cuenta.getSaldo());
	        assertEquals(900, cuenta.getSaldo().intValue());
	        assertEquals("900.23",cuenta.getSaldo().toPlainString());
	    }

	    @Test
	    void testCreditoCuenta() {
	        Cuenta cuenta = new Cuenta("Elvis", new BigDecimal("1000.23"));
	        cuenta.credito(new BigDecimal(100));
	        assertNotNull(cuenta.getSaldo());
	        assertEquals(1100, cuenta.getSaldo().intValue());
	        assertEquals("1100.23",cuenta.getSaldo().toPlainString());
	    }
	2.- Se modifica Cuenta.java, partiendo de los metodos de prueba se crean metodo de debito() y 
		credito() para saldo
		public void debito(BigDecimal monto){
	        this.saldo = this.saldo.subtract(monto);
	    }
	    public void credito(BigDecimal monto){
	        this.saldo = this.saldo.add(monto);
	    }
****************************************************************************************************
11.- Probando excepciones con assertThrows**********************************************************
****************************************************************************************assertThrows
	1.- Se crea excepcion DineroinsuficienteException.java
		1.1.- Se crea paquete
			package com.eareiza.junit5.ejemplos.models.exceptions;
		1.2.- Se crea la clase DineroinsuficienteException
			public class DineroinsuficienteException extends RuntimeException{
			    public DineroinsuficienteException(String message) {
			        super(message);
			    }
			}
	2.- Se modifica CuentaTest.java, se crea metodo para validar la exceptions
	    @Test
	    void testDinerinsuficienteException() {
	        Cuenta cuenta = new Cuenta("Elvis", new BigDecimal("100.23"));
	        Exception exception = assertThrows(DineroinsuficienteException.class, () -> {
	            cuenta.debito(new BigDecimal(1500));
	        });
	        String msj = exception.getMessage();
	        assertEquals("Dinero insuficiente", msj);
	    }
	3.- Se modifica Cuenta.java, se modifica el metodo debito() para lanzar la exception en 
		caso de que el saldo sea insuficiente
		public void debito(BigDecimal monto){
	        BigDecimal nuevoSaldo = this.saldo.subtract(monto);
	        if(nuevoSaldo.compareTo(BigDecimal.ZERO) < 0){
	            throw new DineroinsuficienteException("Dinero insuficiente");
	        }
	        this.saldo = nuevoSaldo;
	    }
****************************************************************************************************
12.- Add Banco.java y relacion con Cuenta.java******************************************************
***************************************************************************************stream java 8
**********************************************************************************************filter
*******************************************************************************************findFirst
	1.- Se modifica CuentaTest.java
		1.1.- Se crea metodo para probar la transferencia entre cuentas
			 @Test
		    void testTransferir() {
		        Cuenta cuenta1 = new Cuenta("Diego", new BigDecimal("1500"));
		        Cuenta cuenta2 = new Cuenta("Diego", new BigDecimal("3500.2345"));
		        Banco banco = new Banco();
		        banco.transferir(cuenta1, cuenta2, new BigDecimal(500));
		        assertEquals("1000", cuenta1.getSaldo().toPlainString());
		        assertEquals("4000.2345", cuenta2.getSaldo().toPlainString());
		        assertEquals(1000, cuenta1.getSaldo().doubleValue());
		        assertEquals(4000.2345, cuenta2.getSaldo().doubleValue());
		    }
		1.2.- Se crea metodo para probar las relaciones creadas
		    @Test
		    void testRelacionBancoCuenta() {
		        Cuenta cuenta1 = new Cuenta("Ricardo", new BigDecimal("1500"));
		        Cuenta cuenta2 = new Cuenta("Diego", new BigDecimal("3500.2345"));
		        Banco banco = new Banco("Banco Nacion");
		        banco.addCuenta(cuenta1);
		        banco.addCuenta(cuenta2);
		        banco.transferir(cuenta1, cuenta2, new BigDecimal(500));

		        assertEquals(2, banco.getCuentas().size());
		        assertEquals("Banco Nacion", cuenta1.getBanco().getNombre());
		        assertEquals("Diego", banco.getCuentas()
		                                        .stream()
		                                        .filter( cuenta -> cuenta.getPersona().equals("Diego")
		                                        ).findFirst().get().getPersona());
		        assertTrue(banco.getCuentas().stream().anyMatch( c -> c.getPersona().equals("Ricardo") ));
		    }
	1.- Se modifica Banco.java
		1.1.- Se crea atributo tipo list de cuentas con su get y setPersona
			private List<Cuenta> cuentas;
		1.2.- Se crea metodo para añadir una cuenta a la lista
			    public void addCuenta(Cuenta cuenta){
			        cuentas.add(cuenta);
			        cuenta.setBanco(this);
			    }
		1.3.- Se crea metodo transferir()
			public void transferir(Cuenta origen, Cuenta destino, BigDecimal monto){
		        origen.debito(monto);
		        destino.credito(monto);
		    }
		1.4.- Se crean constructores
			public Banco() {
		        this.cuentas = new ArrayList<>();
		    }

		    public Banco(String nombre) {
		        this.cuentas = new ArrayList<>();
		        this.nombre = nombre;
		    }
	2.- Se modifica Cuenta, se agrega atributo Banco con su get y set 
		private Banco banco;
****************************************************************************************************
14.- Add AssertAll*******************************************************************Metodo usertAll
*******************************************************************************************assertAll  --> Agrupa varios assertions, beneficiando quepor cada falla se van a mostrar en el reporte
	Nota: AssertAll te permite hacer varias pruevas individuales agrupadas y se fallan te da el 
	detalle de cada una que fallaron
	1.- Se modific CuentaTest.java, se modifica metodo testRelacionBancoCuenta() para incluir 
		asertAll
		@Test
	    void testRelacionBancoCuenta() {
	        Cuenta cuenta1 = new Cuenta("Ricardo", new BigDecimal("1500"));
	        Cuenta cuenta2 = new Cuenta("Diego", new BigDecimal("3500.2345"));
	        Banco banco = new Banco("Banco Nacion");
	        banco.addCuenta(cuenta1);
	        banco.addCuenta(cuenta2);
	        banco.transferir(cuenta1, cuenta2, new BigDecimal(500));
	        assertAll(
	                () -> {
	                    assertEquals(2, banco.getCuentas().size());
	                },
	                () -> {
	                    assertEquals("Banco Nacion", cuenta1.getBanco().getNombre());
	                },
	                () -> {
	                    assertEquals("Diego", banco.getCuentas()
	                            .stream()
	                            .filter(cuenta -> cuenta.getPersona().equals("Diego")
	                            ).findFirst().get().getPersona());
	                },
	                () -> {
	                    assertTrue(banco.getCuentas().stream().anyMatch(c -> c.getPersona().equals("Ricardo")));
	                }
	        );
	    }
****************************************************************************************************
15.- Add msjs de falla en los assertions************************************************************
	1.- Se modific CuentaTest.java, se modifica metodo testRelacionBancoCuenta() para incluir 
		mensajes personalizados de error
		  @Test
		    void testRelacionBancoCuenta() {
		        Cuenta cuenta1 = new Cuenta("Ricardo", new BigDecimal("1500"));
		        Cuenta cuenta2 = new Cuenta("Diego", new BigDecimal("3500.2345"));
		        Banco banco = new Banco("Banco Nacion");
		        banco.addCuenta(cuenta1);
		        banco.addCuenta(cuenta2);
		        banco.transferir(cuenta1, cuenta2, new BigDecimal(500));
		        assertAll(
		                () -> {
		                    assertEquals(2, banco.getCuentas().size(),
		                            () -> "Debe indicar la cantidad de cuentas que posee el banco.");
		                },
		                () -> assertEquals("Banco Nacion", cuenta1.getBanco().getNombre(),
		                        "Debe indicar el nombre del banco."),
		                () -> {
		                    assertEquals("Diego", banco.getCuentas()
		                            .stream()
		                            .filter(cuenta -> cuenta.getPersona().equals("Diego")
		                            ).findFirst().get().getPersona());
		                },
		                () -> {
		                    assertTrue(banco.getCuentas().stream().anyMatch(c -> c.getPersona().equals("Ricardo")));
		                }
		        );
		    }	
****************************************************************************************************
16.- Add anotaciones @DisplayName y @Disabled*******************************************************
*******************************************************************************************@Disabled
****************************************************************************************@DisplayName
**********************************************************************************************fail()
	Nota: @Disables deshabilita el metodo pero indica en los resultados que esta deshabilitado
	Nota: fail() es un metodo que fuerza el fallo del test
	1.- Se modific CuentaTest.java, se modifica metodo testRelacionBancoCuenta() para incluir 
		@Disabled, @DisplayName y fail()
		@Test
	    @Disabled
	    @DisplayName("RelacionBancoCuenta")
	    void testRelacionBancoCuenta() {
	        fail();
	        Cuenta cuenta1 = new Cuenta("Ricardo", new BigDecimal("1500"));
	        Cuenta cuenta2 = new Cuenta("Diego", new BigDecimal("3500.2345"));
	        Banco banco = new Banco("Banco Nacion");
	        banco.addCuenta(cuenta1);
	        banco.addCuenta(cuenta2);
	        banco.transferir(cuenta1, cuenta2, new BigDecimal(500));
	        assertAll(
	                () -> {
	                    assertEquals(2, banco.getCuentas().size(),
	                            () -> "Debe indicar la cantidad de cuentas que posee el banco.");
	                },
	                () -> assertEquals("Banco Nacion", cuenta1.getBanco().getNombre(),
	                        "Debe indicar el nombre del banco."),
	                () -> {
	                    assertEquals("Diego", banco.getCuentas()
	                            .stream()
	                            .filter(cuenta -> cuenta.getPersona().equals("Diego")
	                            ).findFirst().get().getPersona());
	                },
	                () -> {
	                    assertTrue(banco.getCuentas().stream().anyMatch(c -> c.getPersona().equals("Ricardo")));
	                }
	        );
	    }
****************************************************************************************************
17.- Ciclo de vida, anotaciones @BeforeEach y @AfterEach********************************************
*****************************************************************************************@BeforeEach
******************************************************************************************@AfterEach
	1.- Se modifica CuentaTest.java
		1.1.- Se intancia clase de Cuenta de manera global
			Cuenta cuenta;
		1.2.- Se crea metodo con anotacion @BeforeEach inicializando la variable cuenta
			@BeforeEach
		    void setUp() {
		        this.cuenta = new Cuenta("Elvis", new BigDecimal("1000.23"));
		        System.out.println("Iniciando el metodo.");
		    }
		1.3.- Se crea metodo con la anotacion @AfterEach
			@AfterEach
		    void tearDown() {
		        System.out.println("finalizando el metodo de prueba");
		    }
		1.4.- Se modifica metodos se comenta la creacion del objeto cuenta para que trabaje con 
			la variable global
			   @Test
			    void testNombreCuenta() {
			        //Cuenta cuenta = new Cuenta("Elvis", new BigDecimal("100.2345"));
			        assertEquals("Elvis", cuenta.getPersona());
			        assertTrue(cuenta.getPersona().equals("Elvis"));
			    }

			    @Test
			    void testDebitoCuenta() {
			        //Cuenta cuenta = new Cuenta("Elvis", new BigDecimal("1000.23"));
			        cuenta.debito(new BigDecimal(100));
			        assertNotNull(cuenta.getSaldo());
			        assertEquals(900, cuenta.getSaldo().intValue());
			        assertEquals("900.23", cuenta.getSaldo().toPlainString());
			    }

			    @Test
			    void testCreditoCuenta() {
			        //Cuenta cuenta = new Cuenta("Elvis", new BigDecimal("1000.23"));
			        cuenta.credito(new BigDecimal(100));
			        assertNotNull(cuenta.getSaldo());
			        assertEquals(1100, cuenta.getSaldo().intValue());
			        assertEquals("1100.23", cuenta.getSaldo().toPlainString());
			    }
		Nota: por consola podemos observar los print
****************************************************************************************************
18.- Ciclo de vida anotaciones @AfterAll y @BeforeAll***********************************************
*******************************************************************************************@AfterAll
******************************************************************************************@BeforeAll
***************************************************************************************@TestInstance
	1.- Se modifica CuentaTest.java
		1.1.- Se crean metodos con la anotacion BeforeAll y AfterAll, un constructor vacio
			@BeforeAll
		    static void beforeAll() {
		        System.out.println("Iniciando Test");
		    }

		    @AfterAll
		    static void afterAll() {
		        System.out.println("Terminando Test");
		    }

		    public CuentaTest() {
		        System.out.println("constructor");
		    }
		    Nota: Los metodos al ser estatico no estan atados a una instancia, nota por consola que
		    se ejecuta antes de ser creada la clase. Estos metodos se ejecutan una sola vez.
		2.- Se utiliza la anotacion @TestInstance para que sea creada la instancia una sola vez por 
			clase y o por metodo que es el default

			@TestInstance tiene dos modos. Uno es LifeCycle.PER_METHOD (el predeterminado). El otro 
			es Lifecycle.PER_CLASS . Este último nos permite pedirle a JUnit que cree solo una 
			instancia de la clase de prueba y la reutilice entre pruebas.
			
			@TestInstance(TestInstance.Lifecycle.PER_CLASS)
			class CuentaTest {...}
****************************************************************************************************
19.-  Test condicionales @EnabledOnOs,@EnabledOnJre, @EnabledIfSystemPro…***************************
****************************************************************************************@EnabledOnOs
***************************************************************************************@EnabledOnJre
*********************************************************************************@EnabledIfSystemPro
	Nota: Los condicionales van a depender del contexto en que se realice una prueba
	1.- Se  modifica CuentaTest.java, se crean test para variables de entorno

		    /** Test para SO **/
		    @Test
		    @EnabledOnOs(OS.WINDOWS)
		    void testSoloWindows() {
		    }

		    @Test
		    @EnabledOnOs({OS.LINUX,OS.MAC})
		    void testSoloLinuxMac() {
		    }

		    @Test
		    @DisabledOnOs({OS.WINDOWS})
		    void testDisabledWindows() {
		    }

		    /** Test para Version Java **/
		    @Test
		    @EnabledOnJre(JRE.JAVA_8)
		    void soloJava8() {
		        System.out.println("Habilitado para java 8");
		    }

		    @Test
		    @EnabledOnJre(JRE.JAVA_15)
		    void soloJava15() {
		        System.out.println("Habilitado para java 15");
		    }

		    @Test
		    @DisabledOnJre(JRE.JAVA_8)
		    void deshabilitadoJava8() {
		        System.out.println("Habilitado para java 15");
		    }

		    /* Impresion de propiedades del sistema */
		    @Test
		    void inprimirSystemProperties() {
		        Properties properties = System.getProperties();
		        properties.forEach((k,v) -> System.out.println(k+":"+v));
		    }

		    @Test
		    @EnabledIfSystemProperty(named = "java.version", matches = "1.8.0_281")
		    void testJavaVersion() {
		    }

		    @Test
		    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
		    void testHabilitador64() {
		    }
****************************************************************************************************
20.- Ejecuciones de test condicionales con @EnabledIfEnvironmentVariable****************************
	1.- Se modiifica CuentasTest.java, se crea pruebas para validar EnabledIfEnvironmentVariable
		1.1.- Se imprimen las propiedades del sistema
		    @Test
		    void inprimirEnvProperties() {
		        Map<String, String> getenv = System.getenv();
		        getenv.forEach((k,v) -> System.out.println(k+":"+v));
		    }
		1.2.- Se crean las pruebas
			@Test
		    @EnabledIfEnvironmentVariable(named = "NUMBER_OF_PROCESSORS", matches = "12")
		    void testProcesadorsEnv() {
		    }

		    @Test
		    @EnabledIfEnvironmentVariable(named = "ENVIRONMENT", matches = "DEV")
		    void testEnv() {
		    }
****************************************************************************************************
	1.- Se modifica CuentaTest.java, se crea pruebas para el ENVIRONMENT
		    @Test
		    void inprimirEnvProperties() {
		        Map<String, String> getenv = System.getenv();
		        getenv.forEach((k,v) -> System.out.println(k+":"+v));
		    }

		    @Test
		    @EnabledIfEnvironmentVariable(named = "NUMBER_OF_PROCESSORS", matches = "12")
		    void testProcesadorsEnv() {
		    }

		    @Test
		    @EnabledIfEnvironmentVariable(named = "ENVIRONMENT", matches = "DEV")
		    void testEnv() {
		    }
21.- Ejecución de test condicional con Assumptions programáticamente*********************************
******************************************************************************************assumeTrue
****************************************************************************************assumingThat
****************************************************************************************@DisplayName
	0.- Se configura arranque de ambiente si en prod o dev
		CuentaTest --> Edit Configuratios -->  VM option -> add --> -DENV=dev  --> aply
	1.- Se modifica CuentaTest.java
		1.1.- Se importa la libreria Assumptions
			import static org.junit.jupiter.api.Assumptions.*;
		1.2.- Se crean metodo de prueba
			@Test
		    @DisplayName("test saldo Cuenta")
		    void testSaldoCuentaDev() {
		        boolean esDev = "dev".equals(System.getProperty("ENV"));
		        assumeTrue(esDev);
		        Cuenta cuenta = new Cuenta("Ricardo", new BigDecimal("100.1234"));
		        assertEquals(100.1234, cuenta.getSaldo().doubleValue());
		        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
		    }

		    @Test
		    @DisplayName("test saldo Cuenta 2 ")
		    void testSaldoCuentaDev2() {
		        Cuenta cuenta = new Cuenta("Ricardo", new BigDecimal("100.1234"));
		        boolean esDev = "dev".equals(System.getProperty("ENV"));
		        assumingThat(esDev, () -> {
		            assertEquals(100.1234, cuenta.getSaldo().doubleValue());
		        });
		        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
		    }
****************************************************************************************************
22.- Clases de test anidadas usando @Nested**********************************************************
*********************************************************************************************@Nested
	1.- Se modifica CuentaTest.java, se organiza en clases anidadas utilizando @Nested
		class CuentaTest {

		    Cuenta cuenta;

		    @BeforeAll
		    static void beforeAll() {
		        System.out.println("Iniciando Test");
		    }

		    @AfterAll
		    static void afterAll() {
		        System.out.println("Terminando Test");
		    }

		    public CuentaTest() {
		        System.out.println("constructor");
		    }

		    @BeforeEach
		    void setUp() {
		        this.cuenta = new Cuenta("Elvis", new BigDecimal("1000.23"));
		        System.out.println("Iniciando el metodo.");
		    }

		    @AfterEach
		    void tearDown() {
		        System.out.println("finalizando el metodo de prueba");
		    }

		    @Nested
		    @DisplayName("PruebaCuentaNombreSaldo")
		    class CuentaSaldoNombre{
		        @Test
		        void testNombreCuenta() {
		            //Cuenta cuenta = new Cuenta("Elvis", new BigDecimal("100.2345"));
		            assertEquals("Elvis", cuenta.getPersona());
		            assertTrue(cuenta.getPersona().equals("Elvis"));
		        }

		        @Test
		        void testSaldoCuenta() {
		            Cuenta cuenta = new Cuenta("Ricardo", new BigDecimal("100.1234"));
		            assertEquals(100.1234, cuenta.getSaldo().doubleValue());
		            assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
		        }

		        @Test
		        void nameReferenciaCuenta() {
		            Cuenta antonio = new Cuenta("Antonio", new BigDecimal("567.876"));
		            Cuenta diego = new Cuenta("Antonio", new BigDecimal("567.876"));
		            //assertNotEquals(antonio,diego);
		            assertEquals(antonio, diego);
		        }

		        @Test
		        @Disabled
		        @DisplayName("RelacionBancoCuenta")
		        void testRelacionBancoCuenta() {
		            fail();
		            Cuenta cuenta1 = new Cuenta("Ricardo", new BigDecimal("1500"));
		            Cuenta cuenta2 = new Cuenta("Diego", new BigDecimal("3500.2345"));
		            Banco banco = new Banco("Banco Nacion");
		            banco.addCuenta(cuenta1);
		            banco.addCuenta(cuenta2);
		            banco.transferir(cuenta1, cuenta2, new BigDecimal(500));
		            assertAll(() -> assertEquals(2, banco.getCuentas().size(),
		                    () -> "Debe indicar la cantidad de cuentas que posee el banco."),
		                    () -> assertEquals("Banco Nacion", cuenta1.getBanco().getNombre(),
		                            "Debe indicar el nombre del banco."),
		                    () -> {
		                        assertEquals("Diego", banco.getCuentas()
		                                .stream()
		                                .filter(cuenta -> cuenta.getPersona().equals("Diego")
		                                ).findFirst().get().getPersona());
		                    },
		                    () -> {
		                        assertTrue(banco.getCuentas().stream().anyMatch(c -> c.getPersona().equals("Ricardo")));
		                    });
		        }
		    }

		    @Nested
		    @DisplayName("PruebaSaldo")
		    class CuentaSaldo{
		        @Test
		        void testDebitoCuenta() {
		            //Cuenta cuenta = new Cuenta("Elvis", new BigDecimal("1000.23"));
		            cuenta.debito(new BigDecimal(100));
		            assertNotNull(cuenta.getSaldo());
		            assertEquals(900, cuenta.getSaldo().intValue());
		            assertEquals("900.23", cuenta.getSaldo().toPlainString());
		        }

		        @Test
		        void testTransferir() {
		            Cuenta cuenta1 = new Cuenta("Diego", new BigDecimal("1500"));
		            Cuenta cuenta2 = new Cuenta("Diego", new BigDecimal("3500.2345"));
		            Banco banco = new Banco();
		            banco.transferir(cuenta1, cuenta2, new BigDecimal(500));
		            assertEquals("1000", cuenta1.getSaldo().toPlainString());
		            assertEquals("4000.2345", cuenta2.getSaldo().toPlainString());
		            assertEquals(1000, cuenta1.getSaldo().doubleValue());
		            assertEquals(4000.2345, cuenta2.getSaldo().doubleValue());
		        }

		        @Test
		        void testCreditoCuenta() {
		            //Cuenta cuenta = new Cuenta("Elvis", new BigDecimal("1000.23"));
		            cuenta.credito(new BigDecimal(100));
		            assertNotNull(cuenta.getSaldo());
		            assertEquals(1100, cuenta.getSaldo().intValue());
		            assertEquals("1100.23", cuenta.getSaldo().toPlainString());
		        }
		    }


		    @Nested
		    @DisplayName("Prueba Excepciones")
		    class CuentaExcepciones{
		        @Test
		        void testDinerinsuficienteException() {
		            Cuenta cuenta = new Cuenta("Elvis", new BigDecimal("100.23"));
		            Exception exception = assertThrows(DineroinsuficienteException.class,
		                    () -> cuenta.debito(new BigDecimal(1500)));
		            String msj = exception.getMessage();
		            assertEquals("Dinero insuficiente", msj);
		        }
		    }


		    @Nested
		    @DisplayName("Prueba S.O.")
		    class SistemaOperativo{
		        /** Test para SO **/
		        @Test
		        @EnabledOnOs(OS.WINDOWS)
		        void testSoloWindows() {
		        }

		        @Test
		        @EnabledOnOs({OS.LINUX,OS.MAC})
		        void testSoloLinuxMac() {
		        }

		        @Test
		        @DisabledOnOs({OS.WINDOWS})
		        void testDisabledWindows() {
		        }

		    }

		    @Nested
		    @DisplayName("Prueba Variables del Sistema.")
		    class VariablesSistema{
		        /** Test para Version Java **/
		        @Test
		        @EnabledOnJre(JRE.JAVA_8)
		        void soloJava8() {
		            System.out.println("Habilitado para java 8");
		        }

		        @Test
		        @EnabledOnJre(JRE.JAVA_15)
		        void soloJava15() {
		            System.out.println("Habilitado para java 15");
		        }

		        @Test
		        @DisabledOnJre(JRE.JAVA_8)
		        void deshabilitadoJava8() {
		            System.out.println("Habilitado para java 15");
		        }

		        /* Impresion de propiedades del sistema */
		        //@Test
		        void inprimirSystemProperties() {
		            Properties properties = System.getProperties();
		            properties.forEach((k,v) -> System.out.println(k+":"+v));
		        }

		        @Test
		        @EnabledIfSystemProperty(named = "java.version", matches = "1.8.0_281")
		        void testJavaVersion() {
		        }

		        @Test
		        @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
		        void testHabilitador64() {
		        }


		        /* Impresion de propiedades del sistema */
		        @Test
		        void inprimirEnvProperties() {
		            Map<String, String> getenv = System.getenv();
		            getenv.forEach((k,v) -> System.out.println(k+":"+v));
		        }

		        @Test
		        @EnabledIfEnvironmentVariable(named = "NUMBER_OF_PROCESSORS", matches = "12")
		        void testProcesadorsEnv() {
		        }

		        @Test
		        @EnabledIfEnvironmentVariable(named = "ENVIRONMENT", matches = "DEV")
		        void testEnv() {
		        }

		        @Test
		        @DisplayName("test saldo Cuenta")
		        void testSaldoCuentaDev() {
		            boolean esDev = "dev".equals(System.getProperty("ENV"));
		            assumeTrue(esDev);
		            Cuenta cuenta = new Cuenta("Ricardo", new BigDecimal("100.1234"));
		            assertEquals(100.1234, cuenta.getSaldo().doubleValue());
		            assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
		        }

		        @Test
		        @DisplayName("test saldo Cuenta 2 ")
		        void testSaldoCuentaDev2() {
		            Cuenta cuenta = new Cuenta("Ricardo", new BigDecimal("100.1234"));
		            boolean esDev = "dev".equals(System.getProperty("ENV"));
		            assumingThat(esDev, () -> {
		                assertEquals(100.1234, cuenta.getSaldo().doubleValue());
		            });
		            assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
		        }
		    }
		}
23.- Repitiendo pruebas con @RepeatedTest************************************************************
***************************************************************************************@RepeatedTest
	Nota: Junit permite repetr un test 
	@Test
    @DisplayName("Probando Repeticiones")
    @RepeatedTest(value = 5,name = "{displayName} - Repeticion numero {currentRepetition} de {totalRepetitions}.")
    void testDebitoCuentaRepetir() {
        //if(info.getCurrentRepetition() == 3) System.out.println("Estoy en la repetecion "+ info.getTotalRepetitions());
        //Cuenta cuenta = new Cuenta("Elvis", new BigDecimal("1000.23"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.23", cuenta.getSaldo().toPlainString());
    }
****************************************************************************************************
24.- Escribiendo pruebas parametrizadas con @ParameterizedTest***************************************
**********************************************************************************@ParameterizedTest
****************************************************************************************@ValueSource
	1.- Se modifica CuentaTest.java se crea metodo de prueba con varios argumentos
		@ParameterizedTest(name = "numer {index} ejecutando con valor {0} - {argumentsWithNames}")
	    @ValueSource(strings = {"100", "200","500","800", "1000"})
	    void testDebitoCuentaVarios( String value) {
	        cuenta.debito(new BigDecimal(value));
	        assertNotNull(cuenta.getSaldo());
	        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO)>0);
	    }
****************************************************************************************************
25.- Pruebas parametrizadas con @ParameterizedTest parte 2******************************************
**********************************************************************************@ParameterizedTest
******************************************************************************************@CsvSource
**************************************************************************************@CsvFileSource
***************************************************************************************@MethodSource
	1.- Se modifica CuentaTest.java
		1.1.- Se crea metodo de prueba donde se le pasa por parametro los datos en formato csv
			@ParameterizedTest(name = "numer {index} ejecutando con valor {0} - {argumentsWithNames}")
		    @CsvSource({"1,200","2,500","3,800","4,1000","5,1200"})
		     void testDebitoCuentaCsvSource( String index, String monto) {
		        cuenta.debito(new BigDecimal(monto));
		        assertNotNull(cuenta.getSaldo());
		        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO)>0);
		    }

		     @ParameterizedTest(name = "numer {index} ejecutando con valor {0} - {argumentsWithNames}")
		    @CsvSource({"1000,200,Ricardo,Ricardo","500,500,Diego,Diego","1200,800, Antonio, Antonio","30000,1000,Yala,Yalaury"})
		     void testDebitoCuentaCsvSource2(String saldo,String monto,String esperado,String actual) {
		        cuenta.setSaldo(new BigDecimal(saldo));
		        cuenta.debito(new BigDecimal(monto));
		        cuenta.setPersona(actual);
		        assertNotNull(cuenta.getSaldo());
		        assertNotNull(cuenta.getPersona());
		        assertEquals(esperado,actual);
		        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO)>0);
		    }
		1.2.- Se crea metodo de prueba donde se le pasa por parametro los datos archivo creado 
			en main --> resources --> new File --> data.csv
		    @ParameterizedTest(name = "numer {index} ejecutando con valor {0} - {argumentsWithNames}")
		    @CsvFileSource(resources = "/data.csv")
		     void testDebitoCuentaCsvFileSource(String monto) {
		        cuenta.debito(new BigDecimal(monto));
		        assertNotNull(cuenta.getSaldo());
		        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO)>0);
		    }

		    @ParameterizedTest(name = "numer {index} ejecutando con valor {0} - {argumentsWithNames}")
	        @CsvFileSource(resources = "/data2.csv")
	        void testDebitoCuentaCsvFileSource2(String saldo,String monto,String esperado,String actual) {
	            cuenta.setSaldo(new BigDecimal(saldo));
	            cuenta.debito(new BigDecimal(monto));
	            cuenta.setPersona(actual);
	            assertNotNull(cuenta.getSaldo());
	            assertNotNull(cuenta.getPersona());
	            assertEquals(esperado,actual);
	            assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO)>0);
	        }

	        1.2.1.- Se crea data en resources --> data2.csv
	        	1000,200,Ricardo,Ricardo
				500,500,Diego,Diego
				1200,800, Antonio, Antonio
				30000,1000,Yala,Yalaury
		1.2.- Se crea metodo de prueba donde se le pasa por parametro los datos en una lista 
		    @ParameterizedTest(name = "numer {index} ejecutando con valor {0} - {argumentsWithNames}")
		    @MethodSource("montoLista")
		     void testDebitoCuentaMethodSource(String monto) {
		        cuenta.debito(new BigDecimal(monto));
		        assertNotNull(cuenta.getSaldo());
		        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO)>0);
		    }

		    static List<String> montoLista(){
		        return Arrays.asList("100", "200","500","800", "1000", "1200");
		    }
**************************************************************************************************** 
27.- Tagging tests con anotación @Tag****************************************************************
************************************************************************************************@Tag
	Nota: La anotacion @Tag nos permite ejecutar pruebas unicamente de una etiqueta determinada,
	se pueden anotar varios tag a un determinado metodo o clase

	1.- Se modifica CuentaTest.java 
		Se utilizan varios tag a nivel de clase y a nivel de metodo

		@Nested
	    @Tag("cuenta")
	    @DisplayName("PruebaCuentaNombreSaldo")
	    class CuentaSaldoNombre{...
    	}

    	@Tag("parametrizada")
	    @Nested
	    class PruebasParametrizadas{    }

	    @Tag("parametrizada")
	    @ParameterizedTest(name = "numer {index} ejecutando con valor {0} - {argumentsWithNames}")
	    @MethodSource("montoLista")
	    void testDebitoCuentaMethodSource(String monto) {
	        cuenta.debito(new BigDecimal(monto));
	        assertNotNull(cuenta.getSaldo());
	        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO)>0);
	    }
	2.- Se ejecutan las pruebas segun tag
		run configuration --> edit configuration --> tipo de recurso:tags --> parametrizada
****************************************************************************************************
28.- Inyección de Dependencia & componentes testInfo y TestReporter*********************************
********************************************************************************************TestInfo
****************************************************************************************TestReporter
	Nota: TestInfo te genera la facilidad de poder manejar informacion importante de cada uno de 
		los metodos
	Nota: TestReporter nos permite manejar los metodos con un log donde marca con hora la impresion
	1.- Se modifica CuentaTest.java
		1.1.- Se instancian de manera glbal los objs TestInfo y TestReporter
			    private TestInfo testInfo;
    			private TestReporter testReporter;
		1.2.- Se modifica metodo anotado con @BeforeEach, se les pasa como argumento los objs 
		TestInfo y TestReporter, lo que permite que se ejecute en cada uno de los metodos
			@BeforeEach
		    void setUp(TestInfo testInfo, TestReporter testReporter) {
		        this.testInfo = testInfo;
		        this.testReporter = testReporter;
		        testReporter.publishEntry("Ejecutando la prueba "+testInfo.getDisplayName()+" "+testInfo.getTestMethod().orElse(null).getName()
		                +" Con las etiquetas "+testInfo.getTags());
		        this.cuenta = new Cuenta("Elvis", new BigDecimal("1000.23"));
		        System.out.println("Iniciando el metodo.");
		    }
		1.3.- Se modifica metodo testRelacionBancoCuenta(), para que utlice la testInfo
			@Test
	        //@Disabled
	        @DisplayName("RelacionBancoCuenta")
	        void testRelacionBancoCuenta( ) {
	            //fail();
	            System.out.println(testInfo.getTags());
	            if(testInfo.getTags().contains("cuenta")){
	                System.out.println("Hacer algo con la etiqueta cuenta");
	            }
	            Cuenta cuenta1 = new Cuenta("Ricardo", new BigDecimal("1500"));
	            Cuenta cuenta2 = new Cuenta("Diego", new BigDecimal("3500.2345"));
	            Banco banco = new Banco("Banco Nacion");
	            banco.addCuenta(cuenta1);
	            banco.addCuenta(cuenta2);
	            banco.transferir(cuenta1, cuenta2, new BigDecimal(500));
	            assertAll(() -> assertEquals(2, banco.getCuentas().size(),
	                    () -> "Debe indicar la cantidad de cuentas que posee el banco."),
	                    () -> assertEquals("Banco Nacion", cuenta1.getBanco().getNombre(),
	                            "Debe indicar el nombre del banco."),
	                    () -> {
	                        assertEquals("Diego", banco.getCuentas()
	                                .stream()
	                                .filter(cuenta -> cuenta.getPersona().equals("Diego")
	                                ).findFirst().get().getPersona());
	                    },
	                    () -> {
	                        assertTrue(banco.getCuentas().stream().anyMatch(c -> c.getPersona().equals("Ricardo")));
	                    });
	        }
****************************************************************************************************
29.- Timeout en JUnit 5*****************************************************************************
	1.- se modifica CuentaTest.java
		@Tag("timeout")
	    @Nested
	    @DisplayName("EjemploTimeOut")
	    class TimeOut{
	        @Test
	        @Timeout(5)
	        void pruebaTimeOut() throws InterruptedException{
	            TimeUnit.SECONDS.sleep(6);
	        }
	        @Test
	        @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	        void pruebaTimeOut2() throws InterruptedException{
	            TimeUnit.SECONDS.sleep(6);
	        }
	        @Test
	        void pruebaTimeOut3() {
	            assertTimeout(Duration.ofSeconds(5), () -> {
	                TimeUnit.SECONDS.sleep(6);
	            });

	        }
	    }
****************************************************************************************************
30. Maven surefire plugin***************************************************************************
	1.- Se modifica pom.xml, se agrega maven-sunfire y se le añade configuracion para que unicamente 
		ejecute las pruebas con tag cuenta

			<build>
		        <plugins>
		            <plugin>
		                <groupId>org.apache.maven.plugins</groupId>
		                <artifactId>maven-surefire-plugin</artifactId>
		                <version>2.22.2</version>
		                <configuration>
		                    <groups>
		                        cuenta
		                    </groups>
		                </configuration>
		            </plugin>
		        </plugins>
		    </build>
		2.- Desde maven --> proyecto --> lifecicle --> test -> click der --> Run test 
			Nota deberian pasar todas las pruebas y ser seccess
		3.- Ver reporte de maven maven-sunfire
			proyecto --> target --> sunfire-report --> reporte
		4.- Ejecutar por consola con maven instalado
			4.1.- Se abre consola desde donde tenemos el pom del proyecto 
			4.2.- Se ejecuta -> mvn test
		5.- Ejecutar todas las pruebas 
			5.1.- Se modifica el pom.xml, se comenta la configuracion
				<build>
			        <plugins>
			            <plugin>
			                <groupId>org.apache.maven.plugins</groupId>
			                <artifactId>maven-surefire-plugin</artifactId>
			                <version>2.22.2</version>
			                <!--<configuration>
			                    <groups>
			                        cuenta
			                    </groups>
			                </configuration>-->
			            </plugin>
			        </plugins>
			    </build>
			5.2.- Se ejecuta por consola -> mvn test
****************************************************************************************************
**************************************** Fin Seccion ***********************************************
************************************* Sección 3: Mockito *******************************************
33.- Creando y configurando el proyecto con JUnit 5 y Mockito***************************************
	1.- Se crea proyecto appMockito
		    <groupId>org.eareiza.appMockito</groupId>
		    <artifactId>appMockito</artifactId>
		    <version>1.0-SNAPSHOT</version>

		    <properties>
		        <maven.compiler.source>8</maven.compiler.source>
		        <maven.compiler.target>8</maven.compiler.target>
		    </properties>
	2.- Se agregan dependencias y se actualiza maven
		<dependencies>
	        <dependency>
	            <groupId>org.junit.jupiter</groupId>
	            <artifactId>junit-jupiter-api</artifactId>
	            <version>5.7.1</version>
	        </dependency>
	        <dependency>
	            <groupId>org.mockito</groupId>
	            <artifactId>mockito-core</artifactId>
	            <version>3.6.28</version>
	        </dependency>
	        <dependency>
	            <groupId>org.mockito</groupId>
	            <artifactId>mockito-junit-jupiter</artifactId>
	            <version>3.6.28</version>
	        </dependency>
	    </dependencies>
	3.- Se crean paqetes del proyecto en src  --> main  --> java 
		com.eareiza.sppMockito.interfaces
		com.eareiza.sppMockito.models
		com.eareiza.sppMockito.repositories
		com.eareiza.sppMockito.services
	4.- Se crea entidad Examen, con sus metodos  get, set y toString() en el paquete de models
		public class Examen {
		    private Long id;
		    private String nombre;
		    private List<String> preguntas;

		    public Examen(Long id, String nombre) {
		        this.id = id;
		        this.nombre = nombre;
		        this.preguntas = new ArrayList<>();
		    } 
			...
		}
	5.- Se crea ExamenService en paquete services
		public class ExamenService {
		}
	6.- Se crea interface IExamenService en paquete interfaces
		public interface IExamenService {
		    Examen findExamenPorNombre(String nombre);
		}
	7.- Se crea repositorio en el pauqete repositories
		public interface ExamenRepository {
		    List<Examen> findAll();
		}
****************************************************************************************************
34.- Implementando la clase Service*****************************************************************
	1.- Se crea clase implementacion de servicio ExamenServiceImpl.java
		1.1.- Se implementa la interface IExamenService y se implementan sus metodos
			public class ExamenServiceImpl implements IExamenService { ... }
		1.2.- Se crea inyeccion de dependencias del repositorio
			private ExamenRepository examenRepo;

		    public ExamenServiceImpl(ExamenRepository examenRepo) {
		        this.examenRepo = examenRepo;
		    }
		1.3.- Se modifica el metodo findExamenPorNombre(), para que busque el examen por el nombre
			pasado como argumento.
			@Override
		    public Examen findExamenPorNombre(String nombre) {
		        Optional<Examen> opt = examenRepo.findAll()
		                .stream()
		                .filter( examen -> examen.getNombre().contains(nombre))
		                .findFirst();
		        Examen examen = null;
		        if(opt.isPresent()){
		            examen = opt.orElse(null);
		        }
		        return examen;
		    }
	2.- Se crea clase de implementacion del repositorio ExamenRepositoryImpl.java, se implementa de 
		ExamenRepository y se implementa su metodo, elmismo se modifica para que genere una lista 
		de examenes
		public class ExamenRepositoryImpl implements ExamenRepository{
		    @Override
		    public List<Examen> findAll() {
		        return Arrays.asList( new Examen(1L,"Matematicas"),
		                new Examen(2L,"Lenguaje"),
		                new Examen(3L,"Biologia"),
		                new Examen(4L,"Fisica"),
		                new Examen(5L,"Quimica"),
		                new Examen(6L,"Ingles"));
		    }
		}
	3.- Se crea clase de test del servicio de implementacion ExamenServiceImpl.java, parado en el 
	nombre de la clase --> alt + insert --> test 
		class ExamenServiceImplTest {

		    @Test
		    void findExamenPorNombre() {
		    }
		}
****************************************************************************************************
35.- Realizando primeras pruebas con mockito********************************************************
************************************************************************************************mock
************************************************************************************************when

	Mockito nos permite crear objetos simulados y probar el comportamiento de nuestros casos de 
	prueba. Por lo general, nos burlamos del comportamiento usando when()y thenReturn()en el 
	objeto simulado.
	
	1.- Se modifica ExamenServiceImplTest.java
		1.1.- Se modifica findExamenPorNombre(), implementando Mockito para simular la 
		implementacion de la clase ExamenRepositoryImpl y con when simulamos los datos
		cuando es invocado el metodo de esa clase
			@Test
		    void findExamenPorNombre() {
		        IExamenRepository examenRepository = mock(ExamenRepositoryImpl.class);
		        IExamenService examenService = new ExamenServiceImpl(examenRepository);

		        List<Examen> examenes = Arrays.asList( new Examen(1L,"Matematicas"),
		                new Examen(2L,"Lenguaje"),
		                new Examen(3L,"Biologia"),
		                new Examen(4L,"Fisica"),
		                new Examen(5L,"Quimica"),
		                new Examen(6L,"Ingles"));

		        when(examenRepository.findAll()).thenReturn(examenes);
		        Optional<Examen> examen = examenService.findExamenPorNombre("Matematicas");

		        assertNotNull(examen);
		        assertEquals("Matematicas", examen.get().getNombre());
		        assertEquals(1L,examen.get().getId());
		    }

	 	    @Test
		    void findExamenPorNombreVacio() {
		        IExamenRepository examenRepository = mock(ExamenRepositoryImpl.class);
		        IExamenService examenService = new ExamenServiceImpl(examenRepository);

		        List<Examen> examenes = Collections.emptyList();

		        when(examenRepository.findAll()).thenReturn(examenes);
		        Optional<Examen> examen = examenService.findExamenPorNombre("Matematicas");

		        assertEquals(null, examen.orElse(null));
		    }
****************************************************************************************************
36.- Agregando nuevas dependencias mock*************************************************************
	1.- Se crea interface IPreguntasRepository.java
		public interface IPreguntasRepository {
		    List<String> findPreguntaPorExamenId(Long id);
		}
	2.- Se modifica IExamenService, se crea firma de metodo que busca  examen con sus preguntas
		Examen findExamenPorNombreConPregunta(String nombre);
	3.- Se modifica ExamenServiceImpl.java
		3.1.- Se crealiza inyeccion de dependencia de IPreguntasRepository 
			private IExamenRepository examenRepo;
		    private IPreguntasRepository preguntaRepo;

		    public ExamenServiceImpl(IExamenRepository examenRepo, IPreguntasRepository preguntaRepo) {
		        this.examenRepo = examenRepo;
		        this.preguntaRepo = preguntaRepo;
		    }
	4.- Se modifica ExamenServiceImplTest.java
		4.1.- Se crea instancia de IPreguntasRepository.java 
			IPreguntasRepository preguntasRepository;
		4.2.- Se modifica metodo   setUp(), se simula la instancia de IPreguntasRepository con mock y
			se le pasa al constructor del servicio ExamenServiceImpl 
			@BeforeEach
		    void setUp() {
		        examenRepository = mock(ExamenRepositoryImpl.class);
		        preguntasRepository = mock(IPreguntasRepository.class);
		        examenService = new ExamenServiceImpl(examenRepository, preguntasRepository);
		    }
****************************************************************************************************
37.- Probando nuevas dependencias mock**************************************************************
*******************************************************************************************anyLong()
	A veces queremos simular el comportamiento de cualquier argumento del tipo dado, en ese caso, 
	podemos usar comparadores de argumentos de Mockito. Los métodos de argumento de Mockito se 
	definen en org.mockito.ArgumentMatchersclase como métodos estáticos.

	1.- Se crea una clase Datos 
		1.1.- Se modifica ExamenServiceImplTest.java, se modifica metodo findExamenPorNombre() se 
		corta la lista generada y se pasa a la clase Datos, se llama a la lista de la clase 
		Datos.java
			@Test
		    void findExamenPorNombre() {
		        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
		        Optional<Examen> examen = examenService.findExamenPorNombre("Matematicas");

		        assertNotNull(examen);
		        assertEquals("Matematicas", examen.get().getNombre());
		        assertEquals(1L,examen.get().getId());
		    }
		1.2.- Se crean los datos en la clase Datos.java 
			    public final static List<Examen> EXAMENES = Arrays.asList( new Examen(1L,"Matematicas"),
			            new Examen(2L,"Lenguaje"),
			            new Examen(3L,"Biologia"),
			            new Examen(4L,"Fisica"),
			            new Examen(5L,"Quimica"),
			            new Examen(6L,"Ingles"));

			    public final static List<String> PREGUNTAS = Arrays.asList("Aritmetica",
			            "Integrales",
			            "Trigonomtricas",
			            "Derivadas",
			            "Geometria");
	2.- Se modifica ExamenServiceImplTest.java, se crea metodo de prueba para ExamenService.java
		    @Test
		    void testPreguntasExamen() {
		        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
		        when(preguntasRepository.findPreguntaPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		        Examen examen = examenService.findExamenPorNombreConPregunta("Matematica");
		        assertEquals(5,examen.getPreguntas().size());
		        assertTrue(examen.getPreguntas().contains("Aritmetica"));
		    }
		Nota: se utiliza anyLong(), para que simule cualquier numero de tipo Long
****************************************************************************************************
38.- Probando con verify****************************************************************************
**********************************************************************************************verify
	Nota: verify nos permite verificar si se llamo un metodo en especifico

	1.- Se modifica ExamenServiceImplTest.java
		1.1.- Se crea metodo para probar que se esten invocando los metodos de los respositorios
			@Test
		    void testPreguntasExamenVerify() {
		        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
		        when(preguntasRepository.findPreguntaPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		        Examen examen = examenService.findExamenPorNombreConPregunta("Matematica");
		        assertEquals(5,examen.getPreguntas().size());
		        assertTrue(examen.getPreguntas().contains("Aritmetica"));
		        verify(examenRepository).findAll();
		        verify(preguntasRepository).findPreguntaPorExamenId(1L);
		    }
		1.2.- Se crea metodo que valida si examen es null, lo cual se forza pasado una lista vacia 
		con Collections.emptyList(), y se prueba con verify si son invocados los metodos
			Nota: La prueba debe fallar al no invocarse el metodo findPreguntaPorExamenId() de 
			ExamenService

		    @Test
		    void testNoExisteExamenVerify() {
		        when(examenRepository.findAll()).thenReturn(Collections.emptyList());
		        when(preguntasRepository.findPreguntaPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		        Examen examen = examenService.findExamenPorNombreConPregunta("Matematica");
		        assertNull(examen);
		        verify(examenRepository).findAll();
		        verify(preguntasRepository).findPreguntaPorExamenId(1L);
		    }
****************************************************************************************************
39.- Inyección de dependencia y anotaciones @Mock, @InjectMocks y @ExtendWith***********************
***********************************************************************************************@Mock
****************************************************************************************@InjectMocks
*****************************************************************************************@ExtendWith
	1.- Se modifica ExamenServiceImplTest.java 
		1.1.- Se anota la clase con @ExtendWith(MockitoExtension.class) para que acepte anotaciones
		de mockito
		1.2.- Se comenta el contenido del metodo  setUp()
			@BeforeEach
		    void setUp() {
		        //examenRepository = mock(ExamenRepositoryImpl.class);
		        //preguntasRepository = mock(IPreguntasRepository.class);
		        //examenService = new ExamenServiceImpl(examenRepository, preguntasRepository);
		    }
		1.3.- Se modifica las instancias de los repositorios para crea inyeccion de dependencia en 
		el contenedor
			@Mock
		    IExamenRepository examenRepository;
		    @Mock
		    IPreguntasRepository preguntasRepository;
		1.4.- Se modifica la instancia de la clase de servicio para que se realice la inyeccion de 
		los repositorios 
		    @InjectMocks
    		ExamenServiceImpl examenService;
****************************************************************************************************
40.- Realizando más pruebas del repositorio con el metodo guardar***********************************
	1.- Se modifica IExamenRepository.java, se le agrega firma del metodo 
		Examen guardar(Examen examen);
	2.- Se modifica IExamenService, se agrega firma del metodo guardar
		Examen guardar(Examen examen); 
	3.- Se modifica ExamenServiceImpl.java, se implementa metodo guardar()
		@Override
	    public Examen guardar(Examen examen) {
	        if(!examen.getPreguntas().isEmpty()){
	            preguntaRepo.guardarVarias(examen.getPreguntas());
	        }
	        return examenRepo.guardar(examen);
	    }
	4.- Se modifica IPreguntasRepository.java, se agrega firma de metodo guardar
		void guardarVarias(List<String> preguntas);
	5.- Se modifica Datos.java, se crea onstante ExamenService
		public final static Examen EXAMEN= new Examen(7L, "Frances");
	6.- Se modifica ExamenServiceImplTest.java, se crea metodo donde se verifica id del examen y 
		que se esten invocando los metodos guaradar() y guardarVarias()
		@Test
	    void testGuardarExamen() {
	        Examen newExamen = Datos.EXAMEN;
	        newExamen.setPreguntas(Datos.PREGUNTAS);
	        when(examenRepository.guardar(newExamen)).thenReturn(Datos.EXAMEN);
	        Examen examen = examenService.guardar(Datos.EXAMEN);
	        assertNotNull(examen.getId());
	        assertEquals(7L, examen.getId());
	        assertEquals("Frances", examen.getNombre());
	        verify(examenRepository).guardar(any(Examen.class));
	        verify(preguntasRepository).guardarVarias(anyList());
	    }
****************************************************************************************************
41.- Test del id incremental en el método guardar usando Invocation Argument*************************
************************************************************************************InvocationOnMock
******************************************************************************************new Answer
*************************************************************************Behavior Driven Development
	Behavior Driven Development:
		Desarrollo dirigido por comportamiento, En TDD se enfoca a la prueba unitaria, en cambio 
		en BDD se enfoca en la prueba de más alto nivel, la prueba funcional, la de aceptación, el 
		foco está en cumplir con el negocio y no solo con el código.
		Ambos enfoques podrían convivir, ya que se podría especificar una prueba de aceptación, 
		y luego refinar en pruebas unitarias al momento de codificar esa funcionalidad en las 
		distintas capas. Tal vez una ventaja clara de BDD es que los desarrolladores se enfocan 
		en ver qué es lo que tiene que funcionar y de qué forma a nivel de negocio.

	1.- Se modifica Datos.java, se le pasa null como id
		public final static Examen EXAMEN= new Examen(null, "Frances");
	2.- Se modifica ExamenServiceImplTest.java, se utiliza new Answer al llamar al metodo
		guardar para crear id de forma incremental

		    @Test
		    void testGuardarExamen() {
		        //Given
		        Examen newExamen = Datos.EXAMEN;
		        newExamen.setPreguntas(Datos.PREGUNTAS);
		        when(examenRepository.guardar(newExamen)).then(new Answer<Examen>() {
		            Long secuencia = 8L;

		            @Override
		            public Examen answer(InvocationOnMock invocation) throws Throwable {
		                Examen examen = invocation.getArgument(0);
		                examen.setId(secuencia++);
		                return examen;
		            }

		        });
		        //when
		        Examen examen = examenService.guardar(newExamen);
		        //then
		        assertNotNull(examen.getId());
		        assertEquals(8L, examen.getId());
		        assertEquals("Frances", examen.getNombre());
		        verify(examenRepository).guardar(any(Examen.class));
		        verify(preguntasRepository).guardarVarias(anyList());
		    }
****************************************************************************************************
42.- Comprobaciones de excepciones usando when y thenThrow*******************************************
*******************************************************************************************thenTrrow
	1.- Se modifica datos.java, se crea lista con los ids null
	    public final static List<Examen> EXAMENES_ID_NULL = Arrays.asList( new Examen(null,"Matematicas"),
        new Examen(null,"Lenguaje"),
        new Examen(null,"Biologia"),
        new Examen(4L,"Fisica"),
        new Examen(5L,"Quimica"),
        new Examen(6L,"Ingles"));
    2.- Se crea test para el manejo de excepciones, se maneja excepcion cuando el id del examen es null
    	lanza un excepcion de tipo IllegalArgumentException
    	@Test
	    void testManejoExcepcion() {
	        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES_ID_NULL);
	        when(preguntasRepository
	        	.findPreguntaPorExamenId(isNull()))
	        	.thenThrow(IllegalArgumentException.class);
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	            examenService.findExamenPorNombreConPregunta("Matematicas");
	        });

	        assertEquals(IllegalArgumentException.class, exception.getClass());
	        verify(examenRepository).findAll();
	        verify(preguntasRepository).findPreguntaPorExamenId(isNull());
	    }
****************************************************************************************************
43.- Argument matchers*******************************************************************************
*********************************************************************************************argThat
************************************************************************************************eq()
	Nota: los matchers se utilizan para asegurar que ciertos argumentos son pasados a los mock 

	1.- Se crea metodo para implementar los matchers
		@Test
	    void testArgumentMatchers() {
	        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
	        when(preguntasRepository.findPreguntaPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
	        examenService.findExamenPorNombreConPregunta("Quimica");

	        verify(examenRepository).findAll();
	        verify(preguntasRepository).findPreguntaPorExamenId( argThat( arg ->  arg != null && arg.equals(5L) ) );
	        verify(preguntasRepository).findPreguntaPorExamenId( argThat( arg ->  arg != null && arg > 3 ) );
	        verify(preguntasRepository).findPreguntaPorExamenId( eq(5L) );
	    }
****************************************************************************************************
44.- Argument matchers parte 2**********************************************************************
********************************************************************implements ArgumentMatcher<Long>
*********************************************************************************************argThat
	1.- Se modifica ExamenServiceImplTest.java 
		1.1.- Se crea clase que implementa ArgumentMatcher, donde se coloca  validacion y mensaje personalizados
		personalizado

			public static class MyArgMatchers implements ArgumentMatcher<Long>{

		        private Long argument;
		        @Override
		        public boolean matches(Long argument) {
		            this.argument = argument;
		            return argument != null && argument > 3;
		        }

		        @Override
		        public String toString() {
		            return "Debe ser entero positivo y not null mayor a tres";
		        }
		    }
	    1.2.- Se crea prueba donde se utiliza la clase MyArgMatchers
			@Test
		    void testArgumentMatchers2() {
		        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
		        when(preguntasRepository.findPreguntaPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		        examenService.findExamenPorNombreConPregunta("Ingles");

		        verify(examenRepository).findAll();
		        verify(preguntasRepository).findPreguntaPorExamenId( argThat(new MyArgMatchers()) );
		    }
		1.3.- Se crea prueba donde no se utiliza la clase, si no, se utilizan expresiones lamda
		    @Test
		    void testArgumentMatchers3() {
		        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
		        when(preguntasRepository.findPreguntaPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		        examenService.findExamenPorNombreConPregunta("Ingles");

		        verify(examenRepository).findAll();
		        verify(preguntasRepository).findPreguntaPorExamenId(argThat( (arg) -> arg != null && arg > 0 ));
		    }
****************************************************************************************************
45.- Capturando argumentos de método con Argument capture*******************************************
*********************************************************************************************@Captor
**************************************************************************************ArgumentCaptor
	1.- Se modifica ExamenServiceImplTest.java
		1.1.- Se cre metodo que captura un argumento de un mock
		    @Test
		    void testArgumentCapture() {
		        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
		        when(preguntasRepository.findPreguntaPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		        examenService.findExamenPorNombreConPregunta("Matematica");

		        ArgumentCaptor<Long> capture = ArgumentCaptor.forClass(Long.class);
		        verify(preguntasRepository).findPreguntaPorExamenId(capture.capture());
		        assertEquals(1L, capture.getValue());

		    }
		1.2.- Se utiliza anotacion @Captor 
		    @Captor
			ArgumentCaptor<Long> capture;

			12.2.1.- Se modifica metodo testArgumentCapture(), se comenta instancia de ArgumentCaptor 
				//ArgumentCaptor<Long> capture = ArgumentCaptor.forClass(Long.class);
****************************************************************************************************
46.- Usando doThrow para comprobar excepciones en métodos void**************************************
	1.- Se modifica ExamenServiceImplTest.java, se crea metodo que ejecuta doThrow, valida exception
		devolver void el metodo
		@Test
	    void testDoThrow() {
	        Examen examen = Datos.EXAMEN;
	        examen.setPreguntas(Datos.PREGUNTAS);
	        doThrow(IllegalArgumentException.class).when(preguntasRepository).guardarVarias(anyList());

	        assertThrows(IllegalArgumentException.class, () -> {
	            examenService.guardar(examen);
	        });
	    }
****************************************************************************************************
47.- Usando doAnswer********************************************************************************
********************************************************************************************doAnswer
	1.- Se crea metodo de prueba el cual devielve como respuesta valor segun validacion
		@Test
	    void testDoAnswer() {
	        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
	        //when(preguntasRepository.findPreguntaPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
	        doAnswer( invocacion -> {
	            Long id = invocacion.getArgument(0);
	            return id == 1L ? Datos.PREGUNTAS : Collections.emptyList();
	        }).when(preguntasRepository).findPreguntaPorExamenId(anyLong());

	        Examen examen = examenService.findExamenPorNombreConPregunta("Matematica");
	        assertEquals( 5, examen.getPreguntas().size() );
	        assertEquals( 1L, examen.getId() );
	        assertTrue( examen.getPreguntas().contains("Integrales") );
	        assertEquals( "Matematica", examen.getNombre() );
	        verify(preguntasRepository).findPreguntaPorExamenId(anyLong());
	    }
****************************************************************************************************
48.- Usando doCallRealMethod para la llamada real a un método mock**********************************
**********************************************************************************doCallRealMethod()
	1.- Se mueve de paquete la clase Datos.java 
		package com.eareiza.sppMockito;
	2.- Se modifica ExamenRepositoryImpl.java, se implementan los metodos 
		@Override
	    public Examen guardar(Examen examen) {
	        System.out.println("ExamenRepositoryImpl.guardar");
	        return Datos.EXAMEN;
	    }

	    @Override
	    public List<Examen> findAll() {
	        System.out.println("ExamenRepositoryImpl.findAll");
	        try {
	            System.out.println("ExamenRepositoryImpl");
	            TimeUnit.SECONDS.sleep(5);
	        }catch ( InterruptedException e ){
	            e.printStackTrace();
	        }
	        return Datos.EXAMENES;
	    }
	3.- Se modifica ExamenServiceImplTest.java
		3.1.- Se modifican las instancias a los repo examen y preguntas para las implementaciones
			@Mock
		    ExamenRepositoryImpl examenRepository;
		    @Mock
		    PreguntasRepositoryImpl preguntasRepository;
		3.2.- Se crea metodo para que se realice de los metodo reales y no de los mock
			@Test
		    void testDoRealMethod() {
		        //when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
		        //when(preguntasRepository.findPreguntaPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		        doCallRealMethod().when(examenRepository).findAll();
		        doCallRealMethod().when(preguntasRepository).findPreguntaPorExamenId(anyLong());
		        Examen examen = examenService.findExamenPorNombreConPregunta("Matematica");
		        assertEquals( "Matematica", examen.getNombre() );
		        assertEquals( 1L, examen.getId() );
		    }
****************************************************************************************************
49.- Implementando espías con Spy y doReturn********************************************************
************************************************************************************************@Spy
********************************************************************************************doReturn
*************************************************************************************************spy
	Nota: ¿Cuándo debe usar simulacro o espía? Si desea estar seguro y evitar llamar a servicios 
	externos y solo desea probar la lógica dentro de la unidad, use mock. Si desea llamar a un 
	servicio externo y realizar una llamada de dependencia real, o simplemente decir, desea 
	ejecutar el programa tal como está y simplemente aplicar métodos específicos, utilice espía. 
	Entonces esa es la diferencia entre espiar y simular en mockito.
	
	1.- Se modifica ExamenServiceImplTest, se crea metodo testSpy()
		@Test
	    void testSpy() {
	        IExamenRepository examenRepository = spy(ExamenRepositoryImpl.class);
	        IPreguntasRepository preguntasRepository = spy(PreguntasRepositoryImpl.class);

	        IExamenService examenService = new ExamenServiceImpl(examenRepository,preguntasRepository);
	        List<String> preguntas = Arrays.asList("Geometria");
	        //when(preguntasRepository.findPreguntaPorExamenId(anyLong())).thenReturn(preguntas);
	        doReturn(preguntas).when(preguntasRepository).findPreguntaPorExamenId(anyLong());

	        Examen examen = examenService.findExamenPorNombreConPregunta("Matematica");
	        assertEquals("Matematica", examen.getNombre());
	        assertEquals( 1L, examen.getPreguntas().size());
	        assertEquals( 1L, examen.getId());
	        assertTrue( examen.getPreguntas().contains("Geometria"));

	        verify(examenRepository).findAll();
	        verify(preguntasRepository).findPreguntaPorExamenId(anyLong());
	    }
	2.- Se crea clase ExamenServiceImplTest2.java, se prueba la clase con anotaciones @Spy 
		@ExtendWith(MockitoExtension.class)
		class ExamenServiceImplTest2 {

		    @Spy
		    ExamenRepositoryImpl examenRepository;
		    @Spy
		    PreguntasRepositoryImpl preguntasRepository;

		    @InjectMocks
		    ExamenServiceImpl examenService;

		    @Test
		    void testSpy() {
		        IExamenService examenService = new ExamenServiceImpl(examenRepository,preguntasRepository);
		        List<String> preguntas = Arrays.asList("Geometria");
		        //when(preguntasRepository.findPreguntaPorExamenId(anyLong())).thenReturn(preguntas);
		        doReturn(preguntas).when(preguntasRepository).findPreguntaPorExamenId(anyLong());

		        Examen examen = examenService.findExamenPorNombreConPregunta("Matematica");
		        assertEquals("Matematica", examen.getNombre());
		        assertEquals( 1L, examen.getPreguntas().size());
		        assertEquals( 1L, examen.getId());
		        assertTrue( examen.getPreguntas().contains("Geometria"));

		        verify(examenRepository).findAll();
		        verify(preguntasRepository).findPreguntaPorExamenId(anyLong());
		    }
		}
****************************************************************************************************
51.- Verificando el orden de las invocaciones de los mock*******************************************
********************************************************************************************times(2)
******************************************************************************************atLeast(2)
***************************************************************************************atLeastOnce()
*******************************************************************************************atMost(2)
****************************************************************************************atMostOnce()
*********************************************************************************************never()
******************************************************************************verifyNoInteractions()
	NOTA: 
			times 					--> Se valida que se invoque n cantidad de veces 
			atLeast 				-->	Se valida que se ejecute al menos n cantidad de veces
			atLeastOnce				--> Se valida que se ejecute al menos una vez
			atMostOnce				--> Se valida que se a lo sumo n cantidad de veces  
			atMostOnce				--> Se valida que se a lo sumo 1 cantidad de veces  
			never           		--> Se valida que no se ejecute  
			verifyNoInteractions	--> 

	1.- Se modifica ExamenServiceImpl.java, se modifica metodo se invoca dos veces a preguntaRepo, 
	para las pruebas de invocaciones
		 @Override
	    public Examen findExamenPorNombreConPregunta(String nombre) {
	        Optional<Examen> optExamen = findExamenPorNombre(nombre);
	        Examen examen = null;
	        if(optExamen.isPresent()){
	            examen = optExamen.orElse(null);
	            List<String> preguntas = preguntaRepo.findPreguntaPorExamenId(examen.getId());
	            preguntaRepo.findPreguntaPorExamenId(examen.getId());
	            examen.setPreguntas(preguntas);
	        }
	        return examen;
	    }
	2.- Se modifica ExamenServiceImplTest.java
		2.1.- Se crean metodo para la verificacion de invocaciones de preguntasRepository
			@Test
		    void testNumeroInvocaciones() {
		        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
		        examenService.findExamenPorNombreConPregunta("Matematica");

		        //verify(preguntasRepository).findPreguntaPorExamenId(5L);
		        verify(preguntasRepository, times(2)).findPreguntaPorExamenId(1L);
		        verify(preguntasRepository, atLeast(2)).findPreguntaPorExamenId(1L);
		        verify(preguntasRepository, atLeastOnce()).findPreguntaPorExamenId(1L);
		        verify(preguntasRepository, atMost(2)).findPreguntaPorExamenId(1L);
		        //verify(preguntasRepository, atMostOnce()).findPreguntaPorExamenId(5L);
		    }

		    @Test
		    void testNumeroInvocaciones2() {
		        when(examenRepository.findAll()).thenReturn(Collections.emptyList());
		        examenService.findExamenPorNombreConPregunta("Metematica");

		        verify(preguntasRepository, never()).findPreguntaPorExamenId(1L);
		        verifyNoInteractions(preguntasRepository);
		    }
****************************************************************************************************
**************************************** Fin Seccion ***********************************************
******************** Sección 4: Spring Boot: Test de Servicios (Mockito)****************************
53.- Introducción y creando el proyecto Spring******************************************************
	1.- Se crea proyecto con spring Initilizr 
		Project 			--> Maven
		Lenguaje 			--> Java
		Version 			--> 2.4.5
		Group 				--> com.eareiza.test
		Artifact 			--> springboot_test
		Name 				--> springboot_test
		Packege Name 		--> com.eareiza.test
		Packing 			--> Jar 
		Java 				--> 8
		Dependencia 		--> Spring Web

		1.1.- Se empaqueta y se abre con Intellj
****************************************************************************************************
54.- Creando las clases del modelo******************************************************************
	1.- Se crean paquetes
		services
		interfaces
		models
		repositories
		exceptions
	2.- Se add dependencia al pom.xml, correspondiente a lombok
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
	3.- Se crea Entidad Cuenta.java, se le agregan anotacines de lombok, se le add metodo de debito 
		y credito
		@NoArgsConstructor
		@AllArgsConstructor
		@Data
		public class Cuenta {
		    private Long id;
		    private String persona;
		    private BigDecimal saldoCuenta;

		    public void debito(BigDecimal monto){
		        BigDecimal newSaldo = this.saldoCuenta.subtract(monto);
		        if(newSaldo.compareTo(BigDecimal.ZERO)<0){
		            throw new DineroInsuficienteExceptions("Dinero insuficiente en la cuenta");
		        }
		        this.saldoCuenta = newSaldo;
		    }

		    public void credito(BigDecimal monto){
		        this.saldoCuenta = this.saldoCuenta.add(monto);
		    }
		}
	4.- Se crea Entidad Banco.java, se le agregan anotacines de lombok, se le add metodo de debito 
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		public class Banco {
		    private Long id;
		    private String nombre;
		    private int totalTransferencias;
		}
	5.- se crea lase de excepcion DineroInsuficienteExceptions.java
		public class DineroInsuficienteExceptions extends RuntimeException{
		    public DineroInsuficienteExceptions(String message) {
		        super(message);
		    }
		}
****************************************************************************************************
55.- Creando los repositorios***********************************************************************
	1.- Se crean Reposotories
		1.1.- Se crea repositorio de Cuenta
			public interface CuentaRepository {
			    List<Cuenta> finAll();
			    Cuenta findById(Long id);
			    void update(Cuenta cuenta);
			}
		1.2.- Se crea repositorio de Banco
			public interface BancoRepository {
			    List<Banco> findAll();
			    Banco findById(Long id);
			    void update(Banco banco);
			}
	2.- Se crea interface de clase de servicio para Cuenta 
		public interface CuentaService {
		    Cuenta findById(Long id);
		    BigDecimal revisarSaldo(Long cuentaId);
		    int revisarTotalTransferencias(Long bancoId);
		    void transferencia(Long cuentaOrigen, Long cuentaDestino, BigDecimal monto, Long bancoId);
		}
****************************************************************************************************
56.- Implementando la clase de servicio(Service)****************************************************
	1.- Se modifica CuentaServiceImpl, se realizan las implementaciones de la interface 
		CuentaService

		public class CuentaServiceImpl implements  CuentaService{

		    private BancoRepository bancoRepository;
		    private CuentaRepository cuentaRepository;

		    public CuentaServiceImpl(BancoRepository bancoRepository, CuentaRepository cuentaRepository) {
		        this.bancoRepository = bancoRepository;
		        this.cuentaRepository = cuentaRepository;
		    }

		    @Override
		    public Cuenta findById(Long id) {
		        return cuentaRepository.findById(id);
		    }

		    @Override
		    public BigDecimal revisarSaldo(Long cuentaId) {
		        Cuenta cuenta = cuentaRepository.findById(cuentaId);
		        return cuenta.getSaldoCuenta();
		    }

		    @Override
		    public int revisarTotalTransferencias(Long bancoId) {
		        Banco banco = bancoRepository.findById(bancoId);
		        return banco.getTotalTransferencias();
		    }

		    @Override
		    public void transferencia(Long cuentaOrigen, Long cuentaDestino, BigDecimal monto, Long bancoId) {
		        Banco banco = bancoRepository.findById(1L);
		        int totalTransferencias = banco.getTotalTransferencias();
		        banco.setTotalTransferencias(++totalTransferencias);
		        bancoRepository.update(banco);

		        Cuenta origen = cuentaRepository.findById(cuentaOrigen);
		        origen.debito(monto);
		        cuentaRepository.update(origen);

		        Cuenta destino = cuentaRepository.findById(cuentaDestino);
		        destino.credito(monto);
		        cuentaRepository.update(destino);
		    }
		}	
****************************************************************************************************
57.- Escribiendo nuestros tests con JUnit y mockito*************************************************
	1.- Se crea clase para simular datos, Datos.java
		public class Datos {
		    public static final Cuenta cuenta01 = new Cuenta(1L,"Ricardo",new BigDecimal(1000));
		    public static final Cuenta cuenta02 = new Cuenta(2L,"Diego",new BigDecimal(2000));
		    public static final Cuenta cuenta03 = new Cuenta(3L,"Antonio",new BigDecimal(3000));

		    public static final Banco banco01 = new Banco(1L,"Banco Galicia",0);
		    public static final Banco banco02 = new Banco(2L,"Banco BBVA",0);
		}
	2.- Se modifica SpringbootTestApplicationTests.java 
		2.1.- Se crean instncias de los repositorios y la clase de servicio
			CuentaRepository cuentaRepository;
			BancoRepository bancoRepository;

			CuentaService cuentaService;
		2.2.- Se crea metodo setUp(), se realizan los mock de los repositorios y la injeccion de 
			los repositorios en el servicio

			@BeforeEach
			void setUp() {
				cuentaRepository = mock(CuentaRepository.class);
				bancoRepository = mock(BancoRepository.class);

				cuentaService = new CuentaServiceImpl(bancoRepository,cuentaRepository);
			}
		2.3.- Se modifica contextLoads(), se realizan las pruebas

			@Test
			void contextLoads() {
				when(cuentaRepository.findById(1L)).thenReturn(Datos.cuenta01);
				when(cuentaRepository.findById(2L)).thenReturn(Datos.cuenta02);
				when(bancoRepository.findById(1L)).thenReturn(Datos.banco01);

				BigDecimal saldoOrigen = cuentaService.revisarSaldo(1L);
				BigDecimal saldoDestino = cuentaService.revisarSaldo(2L);
				assertEquals("1000", saldoOrigen.toPlainString());
				assertEquals("2000", saldoDestino.toPlainString());

				cuentaService.transferencia(1L,2L,new BigDecimal(100), 1L);

				saldoOrigen = cuentaService.revisarSaldo(1L);
				saldoDestino = cuentaService.revisarSaldo(2L);
				assertEquals("900", saldoOrigen.toPlainString());
				assertEquals("2100", saldoDestino.toPlainString());
			}
****************************************************************************************************
58.- Test verify************************************************************************************
	1.- Se modifica SpringbootTestApplicationTests.java, se modifica metodo contextLoads(), para 
		add verify

		@Test
		void contextLoads() {
			when(cuentaRepository.findById(1L)).thenReturn(Datos.cuenta01);
			when(cuentaRepository.findById(2L)).thenReturn(Datos.cuenta02);
			when(bancoRepository.findById(1L)).thenReturn(Datos.banco01);

			BigDecimal saldoOrigen = cuentaService.revisarSaldo(1L);
			BigDecimal saldoDestino = cuentaService.revisarSaldo(2L);
			assertEquals("1000", saldoOrigen.toPlainString());
			assertEquals("2000", saldoDestino.toPlainString());

			cuentaService.transferencia(1L,2L,new BigDecimal(100), 1L);

			saldoOrigen = cuentaService.revisarSaldo(1L);
			saldoDestino = cuentaService.revisarSaldo(2L);
			assertEquals("900", saldoOrigen.toPlainString());
			assertEquals("2100", saldoDestino.toPlainString());

			verify(cuentaRepository, times(3)).findById(1L);
			verify(cuentaRepository, times(3)).findById(2L);
			verify(cuentaRepository, times(2)).update(any(Cuenta.class));

			verify(bancoRepository, times(1)).findById(1L);
			verify(bancoRepository).update(any(Banco.class));
		}
****************************************************************************************************
59.- Escribiendo tests assertThrow******************************************************************
	1.- Se modifica clase SpringbootTestApplicationTests.java
		1.1.- Se realizan  modificaciones en Datos.java, se crean metodos estaticos para rear nuevas 
			instancias de las entidades
			public static Cuenta getCuenta001(){
		        return new Cuenta(1L,"Ricardo",new BigDecimal(1000));
		    }
		    public static Cuenta getCuenta002(){
		        return new Cuenta(2L,"Diego",new BigDecimal(2000));
		    }
		    public static Cuenta getCuenta003(){
		        return new Cuenta(3L,"Antonio",new BigDecimal(3000));
		    }
		    public static Banco getBanco01(){
		        return new Banco(1L,"Banco Galicia",0);
		    }
		    public static Banco getBanco02(){
		        return new Banco(2L,"Banco BBVA",0);
		    } 
		1.2.- Se crea metodo de prueba para manejo de excepcion
			@Test
			void contextLoads2() {
				when(cuentaRepository.findById(1L)).thenReturn(Datos.getCuenta001());
				when(cuentaRepository.findById(2L)).thenReturn(Datos.getCuenta002());
				when(bancoRepository.findById(1L)).thenReturn(Datos.getBanco01());

				BigDecimal saldoOrigen = cuentaService.revisarSaldo(1L);
				BigDecimal saldoDestino = cuentaService.revisarSaldo(2L);
				assertEquals("1000", saldoOrigen.toPlainString());
				assertEquals("2000", saldoDestino.toPlainString());

				assertThrows(DineroInsuficienteExceptions.class, () -> {
					cuentaService.transferencia(1L,2L,new BigDecimal(1200), 1L);
				});

				saldoOrigen = cuentaService.revisarSaldo(1L);
				saldoDestino = cuentaService.revisarSaldo(2L);
				assertEquals("1000", saldoOrigen.toPlainString());
				assertEquals("2000", saldoDestino.toPlainString());

				verify(cuentaRepository, times(3)).findById(1L);
				verify(cuentaRepository, times(2)).findById(2L);
				verify(cuentaRepository, never()).update(any(Cuenta.class));

				verify(bancoRepository, never()).findById(1L);
				verify(bancoRepository, never()).update(any(Banco.class));
			}
****************************************************************************************************
60.- Escribiendo tests con assertSame***************************************************************
******************************************************************************************assertSame
	1.- Se modifica SpringbootTestApplicationTests.java, se crea metodo de prueba utilizando 
		assertSame 
		@Test
		void contextLoads3() {
			when(cuentaRepository.findById(1L)).thenReturn(Datos.getCuenta001());

			Cuenta cuenta1 = cuentaRepository.findById(1L);
			Cuenta cuenta2 = cuentaRepository.findById(1L);

			assertSame(cuenta1, cuenta2);
			assertTrue(cuenta1 == cuenta2);
			assertEquals("Ricardo", cuenta1.getPersona());
			assertEquals("Ricardo", cuenta2.getPersona());

			verify(cuentaRepository, times(2)).findById(anyLong());
		}
****************************************************************************************************
61.- Uso de anotaciones de spring @MockBean y @Autowired********************************************
	1.- Se modifica SpringbootTestApplicationTests, se utilizan las anotaciones de springtest para
		los mock y la injeccion de dependencias

		1.1.- A agrega anotacion @Service a la clase de servicio

		1.2.- Se modifica metodo setUp(), se comenta los mock y el inject, se add anotaciones para 
		la creacion de mock y lainjeccion de dependencias
			@MockBean
			CuentaRepository cuentaRepository;
			@MockBean
			BancoRepository bancoRepository;

			@Autowired
			CuentaService cuentaService;

			@BeforeEach
			void setUp() {
				//cuentaRepository = mock(CuentaRepository.class);
				//bancoRepository = mock(BancoRepository.class);

				//cuentaService = new CuentaServiceImpl(bancoRepository,cuentaRepository);
			}
****************************************************************************************************
63.- Deshabilitando la traza del logs de Spring en el contexto test*********************************
	1.- Se crea folder resources en test
		logback-test.xml
		application.properties
	2.- Se modifica application.properties
		logging.level.org.springframework=OFF
		logging.level.root=OFF
		spring.main.banner-mode=OFF
	3.- Se modifica logback-test.xml
		<?xml version="1.0" encoding="UTF-8"?>
		<configuration>
		    <include resource="org/springframework/boot/logging/logback/base.xml" />
		    <logger name="org.springframework" level="OFF"/>
		</configuration>
	4.- Se marca directorio resources como root de test 
		resources --> rigth click --> Marck Directory as --> Test Sources Root
****************************************************************************************************
**************************************** Fin Seccion ***********************************************
****************** Sección 5: Spring Boot: Test de Repositorios (DataJpaTest) **********************
65.- Configurando el contexto de persistencia JPA y clases entities para test***********************
	1.- Se modifica pom.xml, se agregan dependencias
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
	2.- Se modifica en test el application.properties, para configurar el log de las consultas de 
	sql
		logging.level.org.hibernate.SQL=debug
	3.- Se crea archivo import.sql en test --> resources 
		INSERT INTO cuentas (persona, saldo) VALUES('Antonio',1000)
		INSERT INTO cuentas (persona, saldo) VALUES('Ricardo',2000)
		INSERT INTO cuentas (persona, saldo) VALUES('Diego',3000)
		INSERT INTO bancos (nombre, total_transferencias) VALUES('Banco Galicia',0)
	4.- Se modifica la entidad  Cuenta.java, se le agregan anotaciones de persistencia
		@NoArgsConstructor
		@AllArgsConstructor
		@Data
		@Entity
		@Table(name="cuentas")
		public class Cuenta {
		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long id;
		    private String persona;
		    private BigDecimal saldoCuenta;

		    public void debito(BigDecimal monto){
		        BigDecimal newSaldo = this.saldoCuenta.subtract(monto);
		        if(newSaldo.compareTo(BigDecimal.ZERO)<0){
		            throw new DineroInsuficienteExceptions("Dinero insuficiente en la cuenta");
		        }
		        this.saldoCuenta = newSaldo;
		    }

		    public void credito(BigDecimal monto){
		        this.saldoCuenta = this.saldoCuenta.add(monto);
		    }
		}
	5.- Se modifica la entidad  Banco.java, se le agregan anotaciones de persistencia
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		@Entity
		@Table(name="bancos")
		public class Banco {
		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long id;
		    private String nombre;
		    @Column(name = "total_transferencias")
		    private int totalTransferencias;
		}
****************************************************************************************************
67.- Modificando nuestros repositorioscon Spring Data JPA ******************************************
	1. Se modifica CuentaRepository.java y BancoRepository.java, se extiende de JPARepository, se 
		comentan los metodos
		public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
			Optional<Cuenta> findByPersona(String persona);
		    //List<Cuenta> finAll();
		    //Cuenta findById(Long id);
		    //void update(Cuenta cuenta);
		}

		public interface BancoRepository extends JpaRepository<Banco,Long> {
		    //List<Banco> findAll();
		    //Banco findById(Long id);
		    //void update(Banco banco);
		}
	2.- Se modifica CuentaServiceImpl, se le agregan los metododos orelse a los findById()
			@Service
			public class CuentaServiceImpl implements  CuentaService{

			    private BancoRepository bancoRepository;
			    private CuentaRepository cuentaRepository;

			    public CuentaServiceImpl(BancoRepository bancoRepository, CuentaRepository cuentaRepository) {
			        this.bancoRepository = bancoRepository;
			        this.cuentaRepository = cuentaRepository;
			    }

			    @Override
			    public Cuenta findById(Long id){
			        return cuentaRepository.findById(id).orElse(null);
			    }

			    @Override
			    public BigDecimal revisarSaldo(Long cuentaId) {
			        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElse(null);
			        return cuenta.getSaldoCuenta();
			    }

			    @Override
			    public int revisarTotalTransferencias(Long bancoId) {
			        Banco banco = bancoRepository.findById(bancoId).orElse(null);
			        return banco.getTotalTransferencias();
			    }

			    @Override
			    public void transferencia(Long cuentaOrigen, Long cuentaDestino, BigDecimal monto, Long bancoId) {

			        Cuenta origen = cuentaRepository.findById(cuentaOrigen).orElse(null);
			        origen.debito(monto);
			        cuentaRepository.save(origen);

			        Cuenta destino = cuentaRepository.findById(cuentaDestino).orElse(null);
			        destino.credito(monto);
			        cuentaRepository.save(destino);

			        Banco banco = bancoRepository.findById(bancoId).orElse(null);
			        int totalTransferencias = banco.getTotalTransferencias();
			        banco.setTotalTransferencias(++totalTransferencias);
			        bancoRepository.save(banco);
			    }
			}
	3.- Se modifica Dalos.java, se modifica los metodos para que manejen los optional
		    public static Optional<Cuenta> getCuenta001(){
		        return Optional.of(new Cuenta(1L,"Ricardo",new BigDecimal(1000)));
		    }
		    public static Optional<Cuenta> getCuenta002(){
		        return Optional.of(new Cuenta(2L,"Diego",new BigDecimal(2000)));
		    }
		    public static Optional<Cuenta> getCuenta003(){
		        return Optional.of(new Cuenta(3L,"Antonio",new BigDecimal(3000)));
		    }
		    public static Optional<Banco> getBanco01(){
		        return Optional.of(new Banco(1L,"Banco Galicia",0));
		    }
		    public static Optional<Banco> getBanco02(){
		        return Optional.of(new Banco(2L,"Banco BBVA",0));
		    }
	4.- Se modifica SpringbootTestApplicationTests.java, se reemplazan los llamados al metodo update 
		por el metodo save.
****************************************************************************************************
69.- Escribiendo pruebas de integración con @DataJpaTest********************************************
	1.- Se crea archivo import.sql en test -- resources
		INSERT INTO cuentas (persona, saldo) VALUES ('Andrés', 1000);
		INSERT INTO cuentas (persona, saldo) VALUES ('John', 2000);
		INSERT INTO bancos (nombre, total_transferencias) VALUES ('El banco financiero', 0);
	2.- Se crea clase de prueba, se le añade la anotacion @DataJpaTest
		@DataJpaTest
		public class IntegracionJpaTest {...}
		2.1.- Se crea injeccion de dependencia del repositorio
			@Autowired
    		CuentaRepository cuentaRepository;
    	2.2.- Se cre metodo para prueba del metodo findById()
    		 @Test
		    void testFindById() {
		        Optional<Cuenta> cuenta = cuentaRepository.findById(1L);
		        assertTrue(cuenta.isPresent());
		        assertEquals("Andrés", cuenta.orElseThrow().getPersona());
		    }
		2.3.- Se creametodo para prueba del metodo findByPersona() 
			@Test
		    void testFindByPersona() {
		        Optional<Cuenta> cuenta = cuentaRepository.findByPersona("Andrés");
		        assertTrue(cuenta.isPresent());
		        assertEquals("Andrés", cuenta.orElseThrow().getPersona());
		        assertEquals("1000.00", cuenta.orElseThrow().getSaldo().toPlainString());
		    }
		2.4.- Se crea metodo para probar metodo findAll()
			@Test
		    void testFindAll() {
		        List<Cuenta> cuentas = cuentaRepository.findAll();
		        assertFalse(cuentas.isEmpty());
		        assertEquals(2, cuentas.size());
		    }
		2.5.- Se crea metodo para probar las excepciones por valores vacios
			@Test
		    void testFindByPersonaThrowException() {
		        Optional<Cuenta> cuenta = cuentaRepository.findByPersona("Rod");
		        assertThrows(NoSuchElementException.class, cuenta::orElseThrow);
		        assertFalse(cuenta.isPresent());
		    }
****************************************************************************************************
70.- Escribiendo pruebas para el save **************************************************************
	1.- Se modifica IntegracionJpaTest, se agrega metodo de prueba para el metodo save 
		    @Test
		    void testSave() {
		        // Given
		        Cuenta cuentaPepe = new Cuenta(null, "Pepe", new BigDecimal("3000"));

		        // When
		        Cuenta cuenta = cuentaRepository.save(cuentaPepe);
		//        Cuenta cuenta = cuentaRepository.findByPersona("Pepe").orElseThrow();
		//        Cuenta cuenta = cuentaRepository.findById(save.getId()).orElseThrow();

		        // Then
		        assertEquals("Pepe", cuenta.getPersona());
		        assertEquals("3000", cuenta.getSaldo().toPlainString());
		//        assertEquals(3, cuenta.getId());
		    }
****************************************************************************************************
71.- Escribiendo pruebas para el update y el delete*************************************************
	1.- Se modifica IntegracionJpaTest.java
		1.1.- Se crea metodo para probar update()
			@Test
		    void testUpdate() {
		        // Given
		        Cuenta cuentaPepe = new Cuenta(null, "Pepe", new BigDecimal("3000"));

		        // When
		        Cuenta cuenta = cuentaRepository.save(cuentaPepe);
		        //Cuenta cuenta = cuentaRepository.findByPersona("Pepe").orElseThrow();
		//        Cuenta cuenta = cuentaRepository.findById(save.getId()).orElseThrow();

		        // Then
		        assertEquals("Pepe", cuenta.getPersona());
		        assertEquals("3000", cuenta.getSaldo().toPlainString());
		//        assertEquals(3, cuenta.getId());

		        // When
		        cuenta.setSaldo(new BigDecimal("3800"));
		        Cuenta cuentaActualizada = cuentaRepository.save(cuenta);

		        // Then
		        assertEquals("Pepe", cuentaActualizada.getPersona());
		        assertEquals("3800", cuentaActualizada.getSaldo().toPlainString());

		    }
		1.2.- Se crea metodo para probar deleta()
		    @Test
		    void testDelete() {
		        Cuenta cuenta = cuentaRepository.findById(2L).orElseThrow();
		        assertEquals("John", cuenta.getPersona());

		        cuentaRepository.delete(cuenta);

		        assertThrows(NoSuchElementException.class, () -> {
		//            cuentaRepository.findByPersona("John").orElseThrow();
		            cuentaRepository.findById(2L).orElseThrow();
		        });
		        assertEquals(1, cuentaRepository.findAll().size());
		    } 
****************************************************************************************************
**************************************** Fin Seccion ***********************************************
*********** Sección 6: Spring Boot: Test de Controladores con MockMvc (WebMvcTest) *****************
73.- Creando controller*****************************************************************************
	1.- Se crea controllador
		1.1.- Se crea paquete
			package com.eareiza.test.springboot.app.controllers;
		1.2.- Se crea controller
			@RestController
			@RequestMapping(value = "/api/cuentas")
			public class CuentaController {
			    
			    @Autowired
			    private CuentaService cuentaService;
			    
			    @GetMapping("/{id}")
			    @ResponseStatus(HttpStatus.OK)
			    public Cuenta detalle(@PathVariable Long id){
			        return cuentaService.findById(id);
			    }
			}
****************************************************************************************************
74.- Creando controller parte 2*********************************************************************
	1.- Se modifica pom.xml, se le modifica el scope a h2
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<!--<scope>test</scope>-->
		</dependency>
	2.- Se crea Transaccion DTO en paquete creado dto 
		@Getter @Setter @Builder
		public class TransaccionDTO {
		    private Long cuentaOrigenId;
		    private Long cuentaDestinoId;
		    private BigDecimal monto;
		    private Long bancoId;
		}
	3.- Se modfica CuentaServiceImpl.java, se le agrega anotaciones de transaccionalidad de spring 
		@Service
		public class CuentaServiceImpl implements CuentaService{
		    private CuentaRepository cuentaRepository;
		    private BancoRepository bancoRepository;

		    public CuentaServiceImpl(CuentaRepository cuentaRepository, BancoRepository bancoRepository) {
		        this.cuentaRepository = cuentaRepository;
		        this.bancoRepository = bancoRepository;
		    }

		    @Override
		    @Transactional(readOnly = true)
		    public Cuenta findById(Long id) {
		        return cuentaRepository.findById(id).orElseThrow();
		    }

		    @Override
		    @Transactional(readOnly = true)
		    public int revisarTotalTransferencias(Long bancoId) {
		        Banco banco = bancoRepository.findById(bancoId).orElseThrow();
		        return banco.getTotalTransferencias();
		    }

		    @Override
		    @Transactional(readOnly = true)
		    public BigDecimal revisarSaldo(Long cuentaId) {
		        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow();
		        return cuenta.getSaldo();
		    }

		    @Override
		    @Transactional
		    public void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto,
		                           Long bancoId) {

		        Cuenta cuentaOrigen = cuentaRepository.findById(numCuentaOrigen).orElseThrow();
		        cuentaOrigen.debito(monto);
		        cuentaRepository.save(cuentaOrigen);

		        Cuenta cuentaDestino = cuentaRepository.findById(numCuentaDestino).orElseThrow();
		        cuentaDestino.credito(monto);
		        cuentaRepository.save(cuentaDestino);

		        Banco banco = bancoRepository.findById(bancoId).orElseThrow();
		        int totalTransferencias = banco.getTotalTransferencias();
		        banco.setTotalTransferencias(++totalTransferencias);
		        bancoRepository.save(banco);
		    }
		}
	4.- Se mdifica CuentaController.java, se agrega metodo transferencia() 
		@PostMapping("/transferencia")	
		public ResponseEntity<?> transferir(@RequestBody TransaccionDTO dto){
	        cuentaService.transferir(dto.getCuentaOrigenId(),dto.getCuentaDestinoId(), dto.getMonto(), dto.getBancoId());
	        Map<String, Object> response = new HashMap<>();
	        response.put("fecha", LocalDate.now().toString());
	        response.put("transaccion", dto);
	        response.put("status","OK");
	        response.put("mensaje", "Transferencia realizada con exito.");
	        return ResponseEntity.ok(response);
	    }
****************************************************************************************************
75.- Configurando Swagger***************************************************************************
	1.- Se modifica pom.xml, se agrega dependencia de Swagger
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-boot-starter</artifactId>
			<version>3.0.0</version>
		</dependency>
	2.- Se crea clase de configuracion para swagger en la raiz de la aplicacion
		package com.eareiza.test.springboot.app;

		@Configuration
		public class SpringFoxConfig {

		    @Bean
		    public Docket api(){
		        return new Docket(DocumentationType.SWAGGER_2)
		                .select()
		                .apis(RequestHandlerSelectors.basePackage("com.eareiza.test.springboot.app.controllers"))
		                .paths(PathSelectors.ant("/api/cuentas/*")).build();
		    }
		}
	3.- Se modifica application.properties, se le agrega log para sql
		logging.level.org.hibernate.SQL=debug
	4.- Se copia el import.sql del contexto de test y se pega en resources del aplicativo
****************************************************************************************************
76.- Probando los endpoints con Swagger UI**********************************************************
	1.- Se levanta la aplicacion
	2.- Se prueba en el navegador
		http://localhost:8080/v2/api-docs
		http://localhost:8080/swagger-ui/
	3.- Se prueba los metodos con swagger2
****************************************************************************************************
77.- Escribiendo pruebas unitarias para el controlador con @WebMvcTest y MockMvc********************
*****************************************************************************************@WebMvcTest
******************************************************************************MockMvcRequestBuilders
*******************************************************************************************@MockBean
	SpringBoot proporciona la anotación @WebMvcTest para probar los controladores Spring MVC. 
	Además, la prueba basada en @WebMvcTest se ejecuta más rápido porque solo cargará el controlador 
	especificado y sus dependencias sin cargar toda la aplicación. 

	Spring Boot instancia solo la capa web en lugar de todo el contexto de la aplicación. En una aplicación 
	con varios controladores, incluso puede solicitar que solo se cree una instancia de uno utilizando, 
	por ejemplo, @WebMvcTest(HomeController.class).

	1.- Se crea clase de prueba para el controlador
		1.1.- Se crea en el paquete 
			package com.eareiza.test.springboot.app.controllers;
		1.2.- Se le agrega anotacion @WebMvcTest
			@WebMvcTest(CuentaController.class)
			class CuentaControllerTest {...}
		1.3.- Se añade injeccion de depedencia de  MockMvc   
			@Autowired
    		private MockMvc mvc;
    	1.4.- Se crea Mock de la clase de servicio
    		@MockBean
    		CuentaService cuentaService;
    	1.5.- Se crea metodo para probar detalle()
    		@Test
		    void testDetalle() throws Exception {
		        //Given
		        when(cuentaService.findById(1L)).thenReturn(Datos.crearCuenta001().orElseThrow());
		        //When
		        mvc.perform(MockMvcRequestBuilders.get("/api/cuentas/1").contentType(MediaType.APPLICATION_JSON))
		        //then
		                .andExpect(status().isOk())
		                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		                .andExpect(jsonPath("$.persona").value("Andrés"))
		                .andExpect(jsonPath("$.saldo").value("1000"));
		    }
****************************************************************************************************
78.- Escribiendo pruebas para el controlador parte 2************************************************
*******************************************************************************************andExpect
****************************************************************************************objectMapper
	1.- Se instancia un ObjectMapper y se crea dentro de metodo setUp()
		ObjectMapper objectMapper;

	    @BeforeEach
	    void setUp() {
	        objectMapper = new ObjectMapper();
	    }
	2.- Se crea metodo de prueba para transferencia
		@Test
	    void testTransferir() throws Exception {
	        //Given
	        TransaccionDTO dto = new TransaccionDTO();
	        dto.setCuentaDestinoId(2L);
	        dto.setCuentaOrigenId(1L);
	        dto.setBancoId(1L);
	        dto.setMonto(new BigDecimal(100));

	        //when
	        mvc.perform(post("/api/cuentas/transferencia")
	                    .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(dto)))
	        //then
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.fecha").value(LocalDate.now().toString()))
	                .andExpect(jsonPath("$.mensaje").value("Transferencia realizada con exito."))
	                .andExpect(jsonPath("$.transaccion.bancoId").value(1L));
	    }
****************************************************************************************************
79. Ejecutando tests con Cobertura de código (Code Coverage)****************************************
	1.- Se modifica CuentaControllerTest.java, se modifica metodo testTransferir(), se agrega 
	impresion de map y sto en json con el objectMapper y se verifica el map response
		 @Test
	    void testTransferir() throws Exception {
	        //Given
	        TransaccionDTO dto = new TransaccionDTO();
	        dto.setCuentaDestinoId(2L);
	        dto.setCuentaOrigenId(1L);
	        dto.setBancoId(1L);
	        dto.setMonto(new BigDecimal(100));

	        System.out.println(objectMapper.writeValueAsString(dto));

	        Map<String, Object> response = new HashMap<>();
	        response.put("fecha", LocalDate.now().toString());
	        response.put("transaccion", dto);
	        response.put("status","OK");
	        response.put("mensaje", "Transferencia realizada con exito.");

	        System.out.println(objectMapper.writeValueAsString(response));

	        //when
	        mvc.perform(post("/api/cuentas/transferencia")
	                    .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(dto)))
	        //then
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.fecha").value(LocalDate.now().toString()))
	                .andExpect(jsonPath("$.mensaje").value("Transferencia realizada con exito."))
	                .andExpect(jsonPath("$.transaccion.bancoId").value(1L))
	        .andExpect(content().json(objectMapper.writeValueAsString(response)));
	    }
****************************************************************************************************
80. Escribiendo más pruebas con MockMvc para el listar**********************************************
	Mota: se implementa Patron de diseño TDD, primero se realizan las pruebas y partiendo de ellas 
		se realizan las distintas implementaciones
	1.- Se modifica CuentaService, se le agregan firmas de metodo findAll() y save()
		List<Cuenta> findAll();
    	Cuenta save(Cuenta cuenta);
    2.- Se agrega la implementacion a los metodo 
    	@Override
	    public List<Cuenta> findAll() {
	        return null;
	    }


	    @Override
	    public Cuenta save(Cuenta cuenta) {
	        return null;
	    }
	3.- Se modifica CuentaController.java 
		    @GetMapping
		    @ResponseStatus(HttpStatus.OK)
		    public List<Cuenta> listar(){
		        return null;
		    }

		    @PostMapping
		    @ResponseStatus(HttpStatus.CREATED)
		    public Cuenta guardar(@RequestBody Cuenta cuenta){
		        return null;
		    }
	4.- Se modifica CuentaControllerTest.java 
	    @Test
	    void testListar() throws Exception {
	        //Given
	        List<Cuenta> cuentas = Arrays.asList(Datos.crearCuenta001().orElseThrow(),Datos.crearCuenta002().orElseThrow());
	        when(cuentaService.findAll()).thenReturn(cuentas);
	        //When
	        mvc.perform(post("/api/cuentas").contentType(MediaType.APPLICATION_JSON))
	        //Then
	        .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$[0].persona").value("Andres"))
	                .andExpect(jsonPath("$[1].persona").value("John"))
	                .andExpect(jsonPath("$[0].saldo").value("1000"))
	                .andExpect(jsonPath("$[1].saldo").value("2000"))
	                .andExpect(jsonPath("$", hasSize(2)))
	                .andExpect(content().json(objectMapper.writeValueAsString(cuentas)));
	    }
****************************************************************************************************
81. Escribiendo más pruebas con MockMvc para el guardar*********************************************
	1.- Se modifica CuentaController.java, se implementan metodos listar() y guardar()
		    @GetMapping
		    @ResponseStatus(HttpStatus.OK)
		    public List<Cuenta> listar(){
		        return cuentaService.findAll();
		    }

		    @PostMapping
		    @ResponseStatus(HttpStatus.CREATED)
		    public Cuenta guardar(@RequestBody Cuenta cuenta){
		        return cuentaService.save(cuenta);
		    }
	2.- Se modifica CuentaControllerTest.java, se crea metodos de rueba para metodo guardar()
		    @Test
		    void testGuardar() throws Exception {
		        //Given
		        Cuenta cuenta = new Cuenta(null,"Pepe", new BigDecimal("1000"));
		        when(cuentaService.save(any())).then( invocation -> {
		            Cuenta c = invocation.getArgument(0);
		            c.setId(3L);
		            return c;
		        });
		        //When
		        mvc.perform(post("/api/cuentas").contentType(MediaType.APPLICATION_JSON)
		                .content(objectMapper.writeValueAsString(cuenta)))
		        //Then
		        .andExpect(status().isCreated())
		                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		                .andExpect(jsonPath("$.persona", is("Pepe")))
		                .andExpect(jsonPath("$.id", is(3)))
		                .andExpect(jsonPath("$.saldo", is(1000)));
		        verify(cuentaService).save(any());
		    }
****************************************************************************************************
82. Escribiendo más pruebas para el Service en el método findAll()**********************************
	1.- Se modifica CuentaServiceImpl.java, se implementa el metodo finAll()
		    @Override
		    public List<Cuenta> findAll() {
		        return cuentaRepository.findAll();
		    }
	2.- Se modifica SpringbootTestApplicationTests.java, se crea metodo para el metodo del servicio 
		findAll()
			@Test
			void testFindAll() {
				//Given
				List<Cuenta> datos = Arrays.asList(Datos.crearCuenta001().orElseThrow(), Datos.crearCuenta001().orElseThrow());
				when(cuentaRepository.findAll()).thenReturn(datos);
				//When
				List<Cuenta> cuentas = cuentaService.findAll();
				//Then
				assertEquals(2, cuentas.size());
				assertFalse(cuentas.isEmpty());
				assertTrue(cuentas.contains(Datos.crearCuenta001().orElseThrow()));
			}
****************************************************************************************************
83. Escribiendo más pruebas para el Service en el método save()*************************************
	1.- Se modifica CuentaServiceImpl.java, se implementa el metodo save()
	    @Override
	    public Cuenta save(Cuenta cuenta) {
	        return cuentaRepository.save(cuenta);
	    }
	2.- Se modifica SpringbootTestApplicationTests.java, se crea metodo para el metodo del servicio 
		save()
		@Test
		void testSave() {
			//Given
			Cuenta newCuenta = new Cuenta(null, "Ricardo", new BigDecimal("2000"));
			when(cuentaRepository.save(any())).then( invocation -> {
				Cuenta c = invocation.getArgument(0);
				c.setId(3L);
				return c;
			});
			//When
			Cuenta cuenta = cuentaService.save(newCuenta);
			//Then
			assertEquals("Ricardo", cuenta.getPersona());
			assertNotEquals(cuenta.getPersona(), Datos.crearCuenta001().orElseThrow().getPersona());
			assertEquals("2000", cuenta.getSaldo().toPlainString());
			assertEquals(3, cuenta.getId());
			verify(cuentaRepository).save(any());

		}
****************************************************************************************************
**************************************** Fin Seccion ***********************************************
******************** Sección 7: Spring Boot: Test de Servicios *************************************
*************************************************************************************@SpringBootTest
	Spring Boot proporciona  la anotación @SpringBootTest  para las pruebas de integración. 
	Esta anotación crea un contexto de aplicación y carga el contexto de aplicación completo.

	@SpringBootTest  arrancará el contexto completo de la aplicación, lo que significa que podemos  
	@Autowire  cualquier bean que haya sido recogido por el escaneo de componentes en nuestra prueba.

	Inicia el servidor incorporado, crea un entorno web y luego habilita los métodos @Test para realizar 
	pruebas de integración.

	De forma predeterminada, @SpringBootTest no inicia un servidor. Necesitamos agregar el atributo  
	webEnvironment  para refinar aún más cómo se ejecutan sus pruebas. Tiene varias opciones: 
		MOCK (predeterminado):  carga un contexto de aplicación web y proporciona un entorno web simulado.
		RANDOM_PORT:  carga un WebServerApplicationContext y proporciona un entorno web real. El servidor 
		incorporado se inicia y escucha en un puerto aleatorio. Este es el que se debe utilizar para la 
		prueba de integración.
		DEFINED_PORT:  carga un WebServerApplicationContext y proporciona un entorno web real. 
		NINGUNO: Carga un ApplicationContext usando SpringApplication pero no proporciona ningún entorno web.
85. Configuración y escribiendo las primeras pruebas de integración*********************************
	1.- Se modifica el pom.xml, se añade dependencia
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
		</dependency>
	2.- Se crea prueba para CuentaController
		2.1.- Se crea clase
			@SpringBootTest(webEnvironment = RANDOM_PORT) //puerto aleatorio para servidor de pruebas
			class CuentaControllerWebClientTest {...}
		2.2.- Se crea injeccion d dependencia del obj WebTestClient y se instancia ObjectMapper
			@Autowired
		    private WebTestClient client;

		    private ObjectMapper mapper;

		    @BeforeEach
		    void setUp() {
		        mapper = new ObjectMapper();
		    }
		2.3.- Se crea prueba para transferencia
			@Test
		    void testTransferencia() throws JsonProcessingException {
		        //Given
		        TransaccionDTO dto = new TransaccionDTO();
		        dto.setCuentaOrigenId(1L);
		        dto.setCuentaDestinoId(2L);
		        dto.setBancoId(1L);
		        dto.setMonto(new BigDecimal("100"));

		        Map<String, Object> response = new HashMap<>();
		        response.put("fecha", LocalDate.now().toString());
		        response.put("transaccion", dto);
		        response.put("status","OK");
		        response.put("mensaje", "Transferencia realizada con exito.");

		        //When
		        client.post().uri("http://localhost:8080/api/cuentas/transferencia")
		                .contentType(MediaType.APPLICATION_JSON)
		                .bodyValue(dto)
		                .exchange() //intercambio entre request y response
		                .expectStatus().isOk()
		                .expectBody()
		                .jsonPath("$.mensaje").isNotEmpty()
		                .jsonPath("$.mensaje").value(is("Transferencia realizada con exito."))
		                .jsonPath("$.mensaje").value( valor -> {
		                    assertEquals("Transferencia realizada con exito.",valor);
		                 })
		                .jsonPath("$.transaccion.cuentaOrigenId").isEqualTo(dto.getCuentaOrigenId())
		                .jsonPath("$.fecha").isEqualTo(LocalDate.now().toString())
		                .json(mapper.writeValueAsString(response));
		    }
****************************************************************************************************
86. Escribiendo las primeras pruebas de integración parte 2*****************************************
*****************************************************************************************consumeWith
	1.- Se modifica CuentaControllerWebClientTest.java
		1.1.- Se modifica la Uri del metodo testTransferencia(), para que corra en el mismo servidor
			 client.post().uri("/api/cuentas/transferencia")
		1.2.- Se modifica metodo testTransferencia(), se le agrega consumeWith
			@Test
		    @Order(1)
		    void testTransferencia() throws JsonProcessingException {
		        //Given
		        TransaccionDTO dto = new TransaccionDTO();
		        dto.setCuentaOrigenId(1L);
		        dto.setCuentaDestinoId(2L);
		        dto.setBancoId(1L);
		        dto.setMonto(new BigDecimal("100"));

		        Map<String, Object> response = new HashMap<>();
		        response.put("fecha", LocalDate.now().toString());
		        response.put("transaccion", dto);
		        response.put("status", "OK");
		        response.put("mensaje", "Transferencia realizada con exito.");

		        //When
		        client.post().uri("/api/cuentas/transferencia")
		                .contentType(MediaType.APPLICATION_JSON)
		                .bodyValue(dto)
		                .exchange() //intercambio entre request y response
		                //then
		                .expectStatus().isOk()
		                .expectBody()
		                .consumeWith(respuesta -> {
		                    try {
		                        JsonNode json = mapper.readTree(respuesta.getResponseBody());
		                        assertEquals("Transferencia realizada con exito.", json.path("mensaje").asText());
		                        assertEquals(1L, json.path("transaccion").path("cuentaOrigenId").asLong());
		                        assertEquals(LocalDate.now().toString(), json.path("fecha").asText());
		                        assertEquals("100", json.path("transaccion").path("monto").asText());
		                    } catch (IOException e) {
		                        e.printStackTrace();
		                    }
		                })
		                .jsonPath("$.mensaje").isNotEmpty()
		                .jsonPath("$.mensaje").value(is("Transferencia realizada con exito."))
		                .jsonPath("$.mensaje").value(valor -> {
		                    assertEquals("Transferencia realizada con exito.", valor);
		                })
		                .jsonPath("$.transaccion.cuentaOrigenId").isEqualTo(dto.getCuentaOrigenId())
		                .jsonPath("$.fecha").isEqualTo(LocalDate.now().toString())
		                .json(mapper.writeValueAsString(response));
		    }
****************************************************************************************************
87. Escribiendo test de integración para el detalle*************************************************
*****************************************************************************************consumeWith
************************************************************************************@TestMethodOrder
	1.- Se modifica CuentaControllerWebClientTest.java, se crean metodo de prueba para detalle
		1.1.- Se le agrega orden de ejecucion a la clase
			@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//La clase se ordena mediante anotaciones
			@SpringBootTest(webEnvironment = RANDOM_PORT) //puerto aleatorio para servidor de pruebas
			class CuentaControllerWebClientTest {...}
		1.2.- Se crea metodos de prueba
			@Test
		    @Order(2)
		    void testDetalle() throws JsonProcessingException {
		        Cuenta cuenta = new Cuenta(1L, "Andrés", new BigDecimal(900));
		        client.get().uri("http://localhost:8080/api/cuentas/1")
		                .exchange()
		                .expectStatus().isOk()
		                .expectHeader().contentType(MediaType.APPLICATION_JSON)
		                .expectBody()
		                .jsonPath("$.persona").value(is("Andrés"))
		                .jsonPath("$.saldo").isEqualTo(900)
		                //Se compra el json que se obtiene con cuenta
		                .json(mapper.writeValueAsString(cuenta)); 
		    }

		   	@Test
		    @Order(3)
		    void testDetalle2() {
		        client.get().uri("http://localhost:8080/api/cuentas/2")
		                .exchange()
		                .expectStatus().isOk()
		                .expectHeader().contentType(MediaType.APPLICATION_JSON)
		                .expectBody(Cuenta.class) //Convirtiendo el obj json a obj Cuenta
		                .consumeWith(response -> {
		                    Cuenta c = response.getResponseBody();
		                    assertEquals("John", c.getPersona());
		                    assertEquals("2100.00", c.getSaldo().toPlainString());
		                });
		    }
		1.3.- Se le agrega el orden en que se ejecutara cada clase
			    @Test
			    @Order(1)
			    void testTransferencia() throws JsonProcessingException {...}

			 	@Test
			    @Order(2)
			    void testDetalle() throws JsonProcessingException {...}

		        @Test
			    @Order(3)
			    void testDetalle2() {...}
****************************************************************************************************
88. Escribiendo test de integración para el listar**************************************************
************************************************************************************expectBodyList()
	1.- Se modifica clase CuentaControllerWebClientTest.java, se crean pruebas para metodo listar()
		@Test
	    @Order(4)
	    void testListar() {
	    client.get().uri("/api/cuentas")
	            .exchange()
	            .expectStatus().isOk()
	            .expectHeader().contentType(MediaType.APPLICATION_JSON)
	            .expectBody()
	            .jsonPath("$[0].persona").isEqualTo("Andrés")
	            .jsonPath("$[0].id").isEqualTo(1)
	            .jsonPath("$[0].saldo").isEqualTo(900)
	            .jsonPath("$[1].persona").isEqualTo("John")
	            .jsonPath("$[1].id").isEqualTo(2)
	            .jsonPath("$[1].saldo").isEqualTo(2100)
	            .jsonPath("$").isArray()
	            .jsonPath("$").value(hasSize(2));
	    }

	    @Order(5)
	    void testListar2() {
	    client.get().uri("/api/cuentas")
	            .exchange()
	            .expectStatus().isOk()
	            .expectHeader().contentType(MediaType.APPLICATION_JSON)
	            .expectBodyList(Cuenta.class)
	            .consumeWith( response -> {
	                List<Cuenta> cuentas = response.getResponseBody();
	                assertNotNull(cuentas);
	                assertEquals(2, cuentas.size());
	                assertEquals(1L, cuentas.get(0).getId());
	                assertEquals("Andrés", cuentas.get(0).getPersona());
	                assertEquals(900, cuentas.get(0).getSaldo().intValue());
	                assertEquals(2L, cuentas.get(1).getId());
	                assertEquals("John", cuentas.get(1).getPersona());
	                assertEquals("2100.00", cuentas.get(1).getSaldo().toPlainString());
	            })
	    .hasSize(2);
	    }
****************************************************************************************************
89. Escribiendo test de integración para el guardar*************************************************
	1.- Se modifica clase CuentaControllerWebClientTest.java, se crean pruebas para metodo guardar()
	
		@Test
	    @Order(6)
	    void testGuardar() {
	        Cuenta cuenta = new Cuenta(null, "Ricardo", new BigDecimal("3000"));
	        client.post().uri("api/cuentas")
	                .contentType(MediaType.APPLICATION_JSON)
	                .bodyValue(cuenta)
	                .exchange()
	                .expectStatus().isCreated()
	                .expectHeader().contentType(MediaType.APPLICATION_JSON)
	                .expectBody()
	                .jsonPath("$.id").isEqualTo(3)
	                .jsonPath("$.persona").isEqualTo("Ricardo")
	                .jsonPath("$.persona").value(is("Ricardo"))
	                .jsonPath("$.saldo").isEqualTo(3000);
	    }

	    @Test
	    @Order(7)
	    void testGuardar2() {
	        Cuenta cuenta = new Cuenta(null, "Ricardo", new BigDecimal("3000"));
	        client.post().uri("api/cuentas")
	                .contentType(MediaType.APPLICATION_JSON)
	                .bodyValue(cuenta)
	                .exchange()
	                .expectStatus().isCreated()
	                .expectHeader().contentType(MediaType.APPLICATION_JSON)
	                .expectBody(Cuenta.class)
	                .consumeWith( response -> {
	                    Cuenta c = response.getResponseBody();
	                    assertNotNull(c);
	                    assertEquals(4L, c.getId());
	                    assertEquals("Ricardo", c.getPersona());
	                    assertEquals(3000, c.getSaldo().intValue());
	                });
	    }
****************************************************************************************************
90. Escribiendo test de integración para el eliminar************************************************
	1.- Se modifica clase CuentaControllerWebClientTest.java, se crean pruebas para metodo eliminar()
		1.1.- Se modifica CuentaService.java, se agrega fima del metodo deleteById()
			void deleteById(Long id);
		1.2.- Se modifica CuentaServiceImpl.java,  se implementa el metodo creado
			@Override
		    public void deleteById(Long id) {
		        cuentaRepository.deleteById(id);
		    }	
		1.3.- Se modifica CuentaController.java, se agrega metodo eliminar()
			@DeleteMapping("/{id}")
		    @ResponseStatus(value = HttpStatus.NO_CONTENT)
		    public void eliminar(@PathVariable Long id){
		        cuentaService.deleteById(id);
		    }
		1.4.- Se crea metodo para probar metodo eliminar()

			@Test
		    void testEliminar() {
		        client.get().uri("/api/cuentas").exchange().expectStatus().isOk()
		                .expectHeader().contentType(MediaType.APPLICATION_JSON)
		                .expectBodyList(Cuenta.class).hasSize(4);

		        client.delete().uri("/api/cuentas/3")
		                .exchange()
		                .expectStatus().isNoContent()
		                .expectBody().isEmpty();

		        client.get().uri("/api/cuentas").exchange().expectStatus().isOk()
		                .expectHeader().contentType(MediaType.APPLICATION_JSON)
		                .expectBodyList(Cuenta.class).hasSize(3);

		        client.get().uri("/api/cuentas/3")
		                .exchange()
		                .expectStatus().is5xxServerError();
		    }
****************************************************************************************************
91. Escribiendo test de integración para el eliminar parte 2****************************************
	1.- Se modifica clase CuentaControllerWebClientTest.java, se crean pruebas para metodo eliminar()
		1.1.- Se modifia CuentaController.java, se modifica metodo detalle() para el manejo de errores
			@GetMapping("/{id}")
		    public ResponseEntity<?> detalle(@PathVariable Long id){
		        Cuenta cuenta = null;
		        try {
		            cuenta = cuentaService.findById(id);
		        }catch ( NoSuchElementException e){
		            return ResponseEntity.notFound().build();
		        }
		        return ResponseEntity.ok(cuenta);
		    }
	2.-  Se modifica metodo testEliminar(), para validar excepcion 
		@Test
	    @Order(8)
	    void testEliminar() {
	        client.get().uri("/api/cuentas").exchange().expectStatus().isOk()
	                .expectHeader().contentType(MediaType.APPLICATION_JSON)
	                .expectBodyList(Cuenta.class).hasSize(4);

	        client.delete().uri("/api/cuentas/3")
	                .exchange()
	                .expectStatus().isNoContent()
	                .expectBody().isEmpty();

	        client.get().uri("/api/cuentas").exchange().expectStatus().isOk()
	                .expectHeader().contentType(MediaType.APPLICATION_JSON)
	                .expectBodyList(Cuenta.class).hasSize(3);

	        client.get().uri("/api/cuentas/3")
	                .exchange()
	                //.expectStatus().is5xxServerError();
	        .expectStatus().isNotFound().expectBody().isEmpty();
	    }
****************************************************************************************************
**************************************** Fin Seccion ***********************************************
******* Sección 8: Spring Boot: Test de integración de Servicios Rest con Template *****************
93. Configuración y escribiendo primeras pruebas de integración con TestRestTe…*********************
************************************************************************************@LocalServerPort
************************************************************************************TestRestTemplate
***************************************************************************************postForEntity
	1.- Se crea clase test para probar CuentaController.java con RestTemplate
		@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
		@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
		class CuentaControllerTestRestTemplateTest {...}
	2.- Se crean los atributos de la clase necesarion
		    @Autowired
		    private TestRestTemplate client;

		    private ObjectMapper mapper;

		    @LocalServerPort
		    private int puerto;

		    @BeforeEach
		    void setUp() {
		        mapper = new ObjectMapper();
		    }
	3.- Se crea la prueba para metodo transferir()
		 @Test
		 @Order(1)
	    void listar() {
	        TransaccionDTO dto = new TransaccionDTO();
	        dto.setCuentaDestinoId(2L);
	        dto.setCuentaOrigenId(1L);
	        dto.setBancoId(1L);
	        dto.setMonto(new BigDecimal(100));

	        ResponseEntity<String> response = client.postForEntity(crearUri("/api/cuentas/transferencia"),dto,String.class);
	        System.out.println(puerto);
	        String json = response.getBody();
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
	        assertNotNull(json);
	        assertTrue(json.contains("Transferencia realizada con exito."));
	    }
	4.- Se crea metodo que arma uri con un puerto de manera aleatoria
		    private String crearUri(String url){
		        return "http://localhost:"+puerto+url;
		    }
****************************************************************************************************
94. Escribiendo primeras pruebas de integración parte 2 ********************************************
********************************************************************************************JsonNode
	1.- Se modifica metodo testTransferir(), se le agrega JsonNode
		@Test
	    @Order(1)
	    void testTransferir() throws JsonProcessingException {
	        TransaccionDTO dto = new TransaccionDTO();
	        dto.setCuentaDestinoId(2L);
	        dto.setCuentaOrigenId(1L);
	        dto.setBancoId(1L);
	        dto.setMonto(new BigDecimal(100));

	        ResponseEntity<String> response = client.postForEntity(crearUri("/api/cuentas/transferencia"),dto,String.class);
	        System.out.println(puerto);
	        String json = response.getBody();
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
	        assertNotNull(json);
	        assertTrue(json.contains("Transferencia realizada con exito."));

	        JsonNode jsonNode = mapper.readTree(json);
	        assertEquals("Transferencia realizada con exito.", jsonNode.path("mensaje").asText());
	        assertEquals("100", jsonNode.path("transaccion").path("monto").asText());
	        assertEquals(1L, jsonNode.path("transaccion").path("cuentaOrigenId").asLong());

	        Map<String, Object> response2 = new HashMap<>();
	        response2.put("fecha", LocalDate.now().toString());
	        response2.put("transaccion", dto);
	        response2.put("status","OK");
	        response2.put("mensaje", "Transferencia realizada con exito.");

	        assertEquals(mapper.writeValueAsString(response2), json);

	    }
****************************************************************************************************
95. Escribiendo test de integración para el detalle ************************************************
	1.- Se crea metodo testDetalle
		    @Test
	    @Order(2)
	    void testDetalle() {
	        ResponseEntity<Cuenta> forEntity = client.getForEntity(crearUri("/api/cuentas/1"), Cuenta.class);
	        Cuenta cuenta = forEntity.getBody();

	        assertNotNull(cuenta);
	        assertEquals(1L, cuenta.getId());
	        assertEquals("900", cuenta.getSaldo().toPlainString());
	        assertEquals(new Cuenta(1L,"Andrés", new BigDecimal("1000")), cuenta);

	    }
****************************************************************************************************
96. Escribiendo test de integración para el listar *************************************************
	1.- Se crea metodo de prueba para metodo listar()
	    @Test
	    @Order(3)
	    void testListar() throws JsonProcessingException {
	        ResponseEntity<Cuenta[]> forEntity = client.getForEntity(crearUri("/api/cuentas"), Cuenta[].class);
	        assertNotNull(forEntity.getBody());
	        List<Cuenta> cuentas = Arrays.asList(forEntity.getBody());

	        assertEquals(HttpStatus.OK, forEntity.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON, forEntity.getHeaders().getContentType());

	        assertNotNull(cuentas);
	        assertFalse(cuentas.isEmpty());
	        assertEquals(2, cuentas.size());
	        assertEquals(1L, cuentas.get(0).getId());
	        assertEquals("Andrés", cuentas.get(0).getPersona());
	        assertEquals("900.00", cuentas.get(0).getSaldo().toPlainString());
	        assertEquals(2L, cuentas.get(1).getId());
	        assertEquals("John", cuentas.get(1).getPersona());
	        assertEquals("2100.00", cuentas.get(1).getSaldo().toPlainString());

	        JsonNode json = mapper.readTree(mapper.writeValueAsString(cuentas));
	        assertEquals(1L, json.get(0).path("id").asLong());
	        assertEquals("Andrés", json.get(0).path("persona").asText());
	        assertEquals("900.0", json.get(0).path("saldo").asText());
	        assertEquals(2L, json.get(1).path("id").asLong());
	        assertEquals("John", json.get(1).path("persona").asText());
	        assertEquals("2100.0", json.get(1).path("saldo").asText());

	    }
****************************************************************************************************
97. Escribiendo test de integración para el guardar ************************************************
	1.- Se crea prueba para el metodo Guardar()
		@Test
	    @Order(4)
	    void testGuardar() {
	        Cuenta cuenta = new Cuenta(null,"Diego", new BigDecimal("2200"));
	        ResponseEntity<Cuenta> forEntity = client.postForEntity(crearUri("/api/cuentas"), cuenta, Cuenta.class);
	        assertEquals(HttpStatus.CREATED, forEntity.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON, forEntity.getHeaders().getContentType());
	        Cuenta newCuenta = forEntity.getBody();
	        assertNotNull(forEntity);
	        assertEquals(3L, newCuenta.getId());
	        assertEquals("Diego", newCuenta.getPersona());
	        assertEquals("2200", newCuenta.getSaldo().toPlainString());
	    }
****************************************************************************************************
98. Escribiendo test de integración para el eliminar ***********************************************
******************************************************************************************exchange()
	1.- Se crea metodo de prueba para eliminar
		@Test
	    @Order(5)
	    void testDelete() {
	        ResponseEntity<Cuenta[]> forEntity = client.getForEntity(crearUri("/api/cuentas"), Cuenta[].class);
	        assertNotNull(forEntity.getBody());
	        List<Cuenta> cuentas = Arrays.asList(forEntity.getBody());
	        assertEquals(3, cuentas.size());

	        //client.delete(crearUri("/api/cuentas/3"));
	        //ResponseEntity<Void> exchange = client.exchange(crearUri("/api/cuentas/3"), HttpMethod.DELETE, null, Void.class);
	        Map<String, Long> variable = new HashMap<>();
	        variable.put("id",3L);
	        ResponseEntity<Void> exchange = client.exchange(crearUri("/api/cuentas/{id}"), HttpMethod.DELETE,
	                null, Void.class, variable);
	        assertEquals(HttpStatus.NO_CONTENT, exchange.getStatusCode());
	        assertFalse(exchange.hasBody());

	        forEntity = client.getForEntity(crearUri("/api/cuentas"), Cuenta[].class);
	        assertNotNull(forEntity.getBody());
	        List<Cuenta> cuentas2 = Arrays.asList(forEntity.getBody());
	        assertEquals(2, cuentas2.size());

	        ResponseEntity<Cuenta> forEntity3 = client.getForEntity(crearUri("/api/cuentas/3"), Cuenta.class);
	        Cuenta cuenta = forEntity3.getBody();
	        assertEquals(HttpStatus.NOT_FOUND, forEntity3.getStatusCode());
	    }
****************************************************************************************************
99. Corriendo todos los tests  *********************************************************************
	1.- Como excluir pruebas 
		1.1.- Se le agrega Tag a la clase CuentaControllerTestRestTemplateTest.java 
			@Tag("Test_RestTemplate")
			@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
			@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
			class CuentaControllerTestRestTemplateTest {...}
		1.2.- Se le agrega configuracion para que no ejecute la clase de prueba
			edit configuration --> Buid and Run --> Tag --> !Test_RestTemplate
	2.- Pruebas unitarias desde la consola
		2.1.- Desde powershell nos paramos en la aiz del proyecto, donde esta ubicado el pom.xml
			y ejeculamos --> .\mvnw test or 
****************************************************************************************************
**************************************** Fin Seccion ***********************************************


