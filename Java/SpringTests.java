Inicio del Proyecto*********************************************************************************
******************************************************************************Seccion 1:Introduccion
	1.- Instalacion de Intellj DEA
		Referencia --> https://www.jetbrains.com/idea/download/#section=windows
****************************************************************************************************
***********************************************************************************Seccion 2:JUnit 5
**********************************************************************************************Atajos
	ctrl+F12 						--> estructura
	ctrl+d 							--> compara dos archivos  
									--> duplica select block en actual linea
	ctrl + mayus + arriba/abajo 	--> mover lineas de codigo
	alt + insert 					--> agregar
	alt + intro 					--> sugerencias
	ctrl + shift + F10				--> run test
	ctrl + /						--> comenta linea
	ctrl + tab 						--> seleccion entre pestañas
	alt + 1 						--> Amplio Editor
	ctrl + alt + v 					--> Se completa entidad con su tipo
	shift + alt						--> para poner varios tab
	ctrl + shift + v 				--> Despliega opciones con contenido copiado ctrl + c
****************************************************************************************************
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
****************************************************************************************************













***************************************************************************************Complemenatos
****************************************************************************************************
*****************************************************************************************Anotaciones
	@Test************************************************************************************
		Indica que el metodo es una prueba
	*****************************************************************************************
****************************************************************************************************
****************************************************************************************************