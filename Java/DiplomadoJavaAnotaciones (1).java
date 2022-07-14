Arquitectura y Modelos

******************************************************************************Patrones de diseño
    https://www.ecodeup.com/patrones-de-diseno-en-java-mvc-dao-y-dto/
    Patrones de diseño
    https://experto.dev/patron-de-diseno-java-singleton/
    ------------------------------------------------------------------------------------------------
    MVC --> Separacion por capasModelo Vista Controlador
    ------------------------------------------------------------------------------------------------
    DAO --> Objeto de Acceso a Datos
        La capa DAO contiene todos los métodos CRUD (create, read, update, delete), por lo general 
        se tiene un DAO para cada tabla en la base de datos

        El problema que viene a resolver este patrón es netamente el acceso a los datos, que básicamente 
        tiene que ver con la gestión de diversas fuentes de datos y además abstrae la forma de acceder a ellos.

        1.- En primer lugar, debemos hacernos las clases que representan 
        nuestros datos. Por ejemplo, podemos hacer una clase Persona con los datos de la persona y 
        los métodos set() y get() correspondientes.

        2.- Luego hacemos una interface. Esta interface tiene que tener los métodos necesarios para obtener y 
        almacenar Personas. Esta interface no debe tener nada que la relaciones con una base de datos 
        ni cualquier otra cosa específica del medio de almacenamiento que vayamos a usar, es decir, 
        ningún parámetro debería ser una Connection, ni un nombre de fichero, etc.

        3.- hacemos la implementación de la InterfaceDAO, ya contra una base de datos concreta o usando 
        una herramienta -iBATIS, Hibernate, etc- determinada. Al usar nuestra aplicación la InterfaceDAO, 
        podremos pasarle cualquier implementación que queramos o incluso cambiar una por otra en un momento dado.
    ------------------------------------------------------------------------------------------------
    DTO
        Va de la mano con el patrón de diseño DAO.

        Se utiliza para transferir varios atributos entre el cliente y el servidor o viceversa, 
        básicamente consta de 2 clases:

        La primera es una clase java conocida como Value Object que únicamente contiene sus atributos, 
        constructor, getters y setters, esta clase no tiene comportamiento.
        La segunda es una clase del lado del servidor conocida como clase de negocio (en la implementación 
        también se conoce como Business Object) es la que se encarga de obtener datos desde la base de datos 
        y llenar la clase Value Object y enviarla al cliente, o a su vez recibir la clase desde el cliente 
        y enviar los datos al servidor, por lo general tiene todos los métodos CRUD (create, read, update y delete).

            1.-Se crea la clase ClienteVO.java que será la clase también conocida como Value Object:
            2.-Se crea la clase ClienteBO.java conocida también como la clase de negocio, que es la 
            que contiene todos los métodos CRUD:
    ------------------------------------------------------------------------------------------------
    SOLID  referencia --> https://enmilocalfunciona.io/principios-solid/
    ------------------------------------------------------------------------------------------------
        Estos principios se llamaron S.O.L.I.D. por sus siglas en inglés:

            S: Single responsibility principle o Principio de responsabilidad única
                Como su propio nombre indica, establece que una clase, componente o microservicio 
                debe ser responsable de una sola cosa (el tan aclamado término “decoupled” en inglés). 
                Si por el contrario, una clase tiene varias responsabilidades, esto implica que el cambio 
                en una responsabilidad provocará la modificación en otra responsabilidad.
            O: Open/closed principle o Principio de abierto/cerrado
                Establece que las entidades software (clases, módulos y funciones) deberían estar abiertos 
                para su extensión, pero cerrados para su modificación.
                ----------------------------------------------------------------------------
                Ejemplo incorrecto
                    class Coche {  
                        String marca;

                        Coche(String marca){ this.marca = marca; }

                        String getMarcaCoche(){ return marca; }
                    }

                    public static void main(String[] args) {  
                        Coche[] arrayCoches = {
                                new Coche("Renault"),
                                new Coche("Audi")
                        };
                        imprimirPrecioMedioCoche(arrayCoches);
                    }

                    public static void imprimirPrecioMedioCoche(Coche[] arrayCoches){  
                        for (Coche coche : arrayCoches) {
                            if(coche.marca.equals("Renault")) System.out.println(18000);
                            if(coche.marca.equals("Audi")) System.out.println(25000);
                        }
                    }
                ----------------------------------------------------------------------------
                Ejemplo correcto
                    abstract class Coche {  
                        // ...
                        abstract int precioMedioCoche();
                    }

                    class Renault extends Coche {  
                        @Override
                        int precioMedioCoche() { return 18000; }
                    }

                    class Audi extends Coche {  
                        @Override
                        int precioMedioCoche() { return 25000; }
                    }

                    class Mercedes extends Coche {  
                        @Override
                        int precioMedioCoche() { return 27000; }
                    }

                    public static void main(String[] args) {

                        Coche[] arrayCoches = {
                                new Renault(),
                                new Audi(),
                                new Mercedes()
                        };

                        imprimirPrecioMedioCoche(arrayCoches);
                    }

                    public static void imprimirPrecioMedioCoche(Coche[] arrayCoches){  
                        for (Coche coche : arrayCoches) {
                            System.out.println(coche.precioMedioCoche());
                        }
                    }
                ----------------------------------------------------------------------------
            L: Liskov substitution principle o Principio de sustitución de Liskov
                Declara que una subclase debe ser sustituible por su superclase, y si al hacer esto, el 
                programa falla, estaremos violando este principio.
                Cumpliendo con este principio se confirmará que nuestro programa tiene una jerarquía de 
                clases fácil de entender y un código reutilizable.
                ---------------------------------------------------------------------------
                Ejemplo incorrecto
                    // ...
                    Coche[] arrayCoches = {  
                            new Renault(),
                            new Audi(),
                            new Mercedes(),
                            new Ford()
                    };

                    public static void imprimirNumAsientos(Coche[] arrayCoches){  
                        for (Coche coche : arrayCoches) {
                            if(coche instanceof Renault)
                                System.out.println(numAsientosRenault(coche));
                            if(coche instanceof Audi)
                                System.out.println(numAsientosAudi(coche));
                            if(coche instanceof Mercedes)
                                System.out.println(numAsientosMercedes(coche));
                            if(coche instanceof Ford)
                                System.out.println(numAsientosFord(coche));
                        }
                    }
                    imprimirNumAsientos(arrayCoches);  
                ---------------------------------------------------------------------------
                Ejemplo correcto
                    abstract class Coche {

                        // ...
                        abstract int numAsientos();
                    }

                    class Renault extends Coche {

                        // ...
                        @Override
                        int numAsientos() {
                            return 5;
                        }
                    }
                    // ...

                    class Renault extends Coche {

                        // ...
                        @Override
                        int numAsientos() {
                            return 5;
                        }
                    }
                    // ...

                    public static void imprimirNumAsientos(Coche[] arrayCoches){  
                        for (Coche coche : arrayCoches) {
                            System.out.println(coche.numAsientos());
                        }
                    }

                    imprimirNumAsientos(arrayCoches); 
                ---------------------------------------------------------------------------
            I: Interface segregation principle o Principio de segregación de la interfaz
                Este principio establece que los clientes no deberían verse forzados a depender 
                de interfaces que no usan.

                Dicho de otra manera, cuando un cliente depende de una clase que implementa una 
                interfaz cuya funcionalidad este cliente no usa, pero que otros clientes sí usan, 
                este cliente estará siendo afectado por los cambios que fuercen otros clientes en 
                dicha interfaz.

                Lo más correcto sería segregar más las interfaces, tanto como sea necesario.
                ---------------------------------------------------------------------------
                Ejemplo correcto
                    interface IAve {  
                        void comer();
                    }
                    interface IAveVoladora {  
                        void volar();
                    }

                    interface IAveNadadora {  
                        void nadar();
                    }

                    class Loro implements IAve, IAveVoladora{

                        @Override
                        public void volar() {
                            //...
                        }

                        @Override
                        public void comer() {
                            //...
                        }
                    }

                    class Pinguino implements IAve, IAveNadadora{

                        @Override
                        public void nadar() {
                            //...
                        }

                        @Override
                        public void comer() {
                            //...
                        }
                    }
                ---------------------------------------------------------------------------
            D: Dependency inversion principle o Principio de inversión de dependencia
                Establece que las dependencias deben estar en las abstracciones, no en las concreciones. 
                Es decir:

                    Los módulos de alto nivel no deberían depender de módulos de bajo nivel. Ambos 
                    deberían depender de abstracciones.
                    Las abstracciones no deberían depender de detalles. Los detalles deberían depender 
                    de abstracciones.
                
                En algún momento nuestro programa o aplicación llegará a estar formado por muchos módulos. 
                Cuando esto pase, es cuando debemos usar inyección de dependencias, lo que nos permitirá 
                controlar las funcionalidades desde un sitio concreto en vez de tenerlas esparcidas por 
                todo el programa. Además, este aislamiento nos permitirá realizar testing mucho más 
                fácilmente.
    ------------------------------------------------------------------------------------------------
    Patrones de diseño creacionales
    ------------------------------------------------------------------------------------------------
        Patron de Inyeccion de Dependencias
            La inyección de dependencias es un Patrón de Diseño que nos permite Construir software con 
            Poco Acoplamiento. El patrón Funciona con un Objeto que se Encarga de Construir las Dependencias 
            que Una Clase Necesita y se las Suministra, de ahí el término “inyección”. Esto implica que la 
            Clase ya No Crea Directamente los Objetos que necesita, sino que los Recibe de Otra clase. 
            Esta es la aplicación del principio DIP. Imaginemos que tenemos una clase A que necesita usar 
            otra clase B. Si en vez de hacer una dependencia directa de la clase B, creamos una interfaz 
            B, esto significa que podemos cambiar la implementación de la clase B muchas veces sin afectar 
            la clase A ya que no dependerán directamente. 
        -----------------------------------------------------------------------------------
        Patron Singlenton
            Referencia --> https://experto.dev/patron-de-diseno-java-singleton/

            El objetivo es que una clase sólo tenga una instancia viva y garantizar que esto suceda.
            EL patrón de diseño singleton nos asegura que solo un objeto sea creado y que solo exista una 
            sola instancia del objeto para la clase.

            Cada vez que necesitemos el objeto obtendremos la misma instancia.

            El patrón de diseño Singleton tiene muchas formas de implementarse pero veremos aqui la base de 
            sus implementación que consiste en:

                Se crea una variables privada y estatica
                el constructor es privado para que no se pueda instancear con este
                Se crea un metodo de acceso que valida si hay o no una instancia, si la no la hay la crea y si la hay 
                devuelve la instancia creada

            En este tutorial vamos a hablar del patrón de diseño "Singleton", que en ingeniería 
            del software es un patrón diseñado para limitar la creación de objetos pertenecientes 
            a una clase. El objetivo de este patrón es el de garantizar que una clase solo tenga una instancia 
            (o ejemplar) y proporcionar un punto de acceso global a ella. Esta patrón; por ejemplo, 
            suele ser utilizado para las conexiones a bases de datos.

            referencia 
            https://jarroba.com/patron-singleton-en-java-con-ejemplos/

            Este patrón se implementa haciendo privado el constructor de la clase y creando 
            (en la propia clase) un método que crea una instancia del objeto si este no existe.

                public class SoyUnico {

                    private String nombre;
                    private static SoyUnico soyUnico;

                    // El constructor es privado, no permite que se genere un constructor por defecto.
                    private SoyUnico(String nombre) {
                        this.nombre = nombre;
                        System.out.println("Mi nombre es: " + this.nombre);
                    }

                    public static SoyUnico getSingletonInstance(String nombre) {
                        if (soyUnico == null){
                            soyUnico = new SoyUnico(nombre);
                        }
                        else{
                            System.out.println("No se puede crear el objeto "+ nombre + " porque ya existe un objeto de la clase SoyUnico");
                        }
                        
                        return soyUnico;
                    }
                    
                    // metodos getter y setter

                }

            cuando no hay un objeto de la clase "SoyUnico", lo crea y si lo quiere volver a crear, 
            el método nos dice que ya hay otro creado pero le asigna la referencia al objeto, 
            es decir que las variable 'ricardo' y 'ramon' referencian al mismo y único objeto 
            de la clase "SoyUnico"

            Por último este patrón no quedaria perfectamente implementado si no controlamos 
            la copia (o clone) de los objetos de esta clase; por ello es importante sobrescribir 
            el método 'clone()', para que cuando se quiera clonar este objeto se lance una excepción. 
            Este método lo sobrescribimos de la siguiente forma:

                //Sobreescribimos el método clone, para que no se pueda clonar un objeto de esta clase
                @Override
                public SoyUnico clone(){
                    try {
                        throw new CloneNotSupportedException();
                    } catch (CloneNotSupportedException ex) {
                        System.out.println("No se puede clonar un objeto de la clase SoyUnico");
                    }
                    return null; 
                }


                public static void main(String[] args) {
                
                    SoyUnico ricardo = SoyUnico.getSingletonInstance("Ricardo Moya");
                    SoyUnico ramon = SoyUnico.getSingletonInstance("Ramón Invarato");
                    
                    // ricardo y ramon son referencias a un único objeto de la clase SoyUnico
                    System.out.println(ramon.getNombre());
                    System.out.println(ricardo.getNombre());   
                }
        -----------------------------------------------------------------------------------
        Patron Factory 
            El patron Factory que es un patrón de diseño creacional y que sirve para construir una 
            jerarquía de clases, es utilizado cuando tenemos una clase o interfaz con muchas subclases 
            o implementaciones

            Ejemplo_______________________________________________________________________
                /*Como vemos la clase Factura es una clase abstracta de la cual 
                 * heredan nuestras dos clases concretas que implementan el 
                 * cálculo del IVA. */
                public abstract class Factura {
                    private int id;
                    private double importe;
                    
                    public int getId() {
                        return id;
                    }
                    public void setId(int id) {
                        this.id = id;
                    }
                    public double getImporte() {
                        return importe;
                    }
                    public void setImporte(double importe) {
                        this.importe = importe;
                    }
                    
                    public abstract double getImporteIva();
                    
                }
                ------------------------------------------------------------------------
                //Implementacion
                public class FacturaIva extends Factura{

                    @Override
                    public double getImporteIva() {
                        // TODO Auto-generated method stub
                        return getImporte()*1.21;
                    }
                }
                ------------------------------------------------------------------------
                //Implementacion
                public class FacturaIvaReducido extends Factura{

                    @Override
                    public double getImporteIva() {
                        // TODO Auto-generated method stub
                        return getImporte()*1.07;
                    }
                    
                }
                -----------------------------------------------------------------------
                /*Factoría para que se encargue de construir ambos objetos de la jerarquía.*/
                public class FactoryFacturas {
                    //La clase lo único que hace es instanciar un objeto u otro dependiendo 
                    //del tipo que le solicitemos
                    public static Factura getFactura(String tipo) {
                        if(tipo.equals("iva")) {
                            return new FacturaIva();
                        }else {
                            return new FacturaIvaReducido();
                        }
                    }
                }
                -----------------------------------------------------------------------
                //Principal
                public class PatronFactory {

                    public static void main(String[] args) {
                         Factura f= FactoryFacturas.getFactura("iva");
                         f.setId(1);
                         f.setImporte(100);
                         System.out.println(f.getImporteIva());
                    }

                }
        -----------------------------------------------------------------------------------
        Patron Builder 
            El patrón de diseño Builder permite crear objetos que habitualmente son complejos 
            utilizando otro objeto más simple que los construye paso por paso.

            Este patrón Builder se utiliza en situaciones en las que debe construirse un objeto 
            repetidas veces o cuando este objeto tiene gran cantidad de atributos y objetos asociados, 
            y en donde usar constructores para crear el objeto no es una solución cómoda.

            Se trata de un patrón de diseño bastante útil también en la ejecución de test 
            (unit test por ejemplo) en donde debemos crear el objeto con atribu

            tos válidos o 
            por defecto.

            Necesitaremos entonces un objeto Builder que nos creará el objeto que concreto en base a 
            parámetros que le vayamos pasando paso por paso.

            Ejemplo_______________________________________________________________________

                public class CuentaBancaria {
                    private long cuentaBancaria;
                    private String nombre;
                    private double balance;
                    private double interes;
                    
                    CuentaBancaria(){
                        
                    }

                    public long getCuentaBancaria() {
                        return cuentaBancaria;
                    }

                    public void setCuentaBancaria(long cuentaBancaria) {
                        this.cuentaBancaria = cuentaBancaria;
                    }

                    public String getNombre() {
                        return nombre;
                    }

                    public void setNombre(String nombre) {
                        this.nombre = nombre;
                    }

                    public double getBalance() {
                        return balance;
                    }

                    public void setBalance(double balance) {
                        this.balance = balance;
                    }

                    public double getInteres() {
                        return interes;
                    }

                    public void setInteres(double interes) {
                        this.interes = interes;
                    }
                }

                ---------------------------------------------------------------------
                package patterns.builder;

                public interface IBuilder {
                    BankAccount build();
                }
                ---------------------------------------------------------------------
                /*
                 * Ahora implementas la interfaz y agregas los métodos que irán recibiendo 
                 * los parámetros. En este ejemplo todos estos métodos que reciben los 
                 * parámetros para crear el objeto empiezan con “with”. Cada método 
                 * devuelve el builder.

                    El método builder crear el objeto destino usando todos los parámetros.
                    
                    Es común agregar cualquier campo obligatorio como argumento en el 
                    constructor del builder dejando el resto de campos usando los métodos 
                    del builder.
                 * */
                public class CuentaBancariaBuilder implements IBuilder {
                    private long cuentaBancaria; //este es importante, se debe pasar al constructor.
                    private String nombre;
                    private double balance;
                    private double interes;
                    
                    public CuentaBancariaBuilder(long cuentaBancaria) {
                        this.cuentaBancaria = cuentaBancaria;
                    }
                    
                    public CuentaBancariaBuilder withNombre(String nombre) {
                        this.nombre = nombre;
                        return this;
                    }
                    
                    public CuentaBancariaBuilder withBalance(double balance) {
                        this.balance = balance;
                        return this;
                    }
                    
                    public CuentaBancariaBuilder withInteres(double interes) {
                        this.interes = interes;
                        return this;
                    }

                    @Override
                    public CuentaBancaria build(){
                        CuentaBancaria cuenta = new CuentaBancaria();
                        cuenta.setCuentaBancaria(this.cuentaBancaria);
                        cuenta.setNombre(this.nombre);
                        cuenta.setBalance(this.balance);
                        cuenta.setInteres(this.interes);
                        return cuenta;
                    }   
                }

                ---------------------------------------------------------------------

                /*Primero creas el builder new BankAccountBuilder(accountNumber) 
                 * que por defecto necesita el número de cuenta porque hemos 
                 * considerado que es un valor indispensable. Luego usamos el 
                 * builder y vamos enviando los parametros uno por uno.*/
                public class Contruir {

                    public static void main(String[] args) {
                        CuentaBancariaBuilder construccion = new CuentaBancariaBuilder(123456789);
                        
                        CuentaBancaria cuenta = construccion.withNombre("Ahorro")
                                                            .withBalance(900)
                                                            .withInteres(9).build();
                        
                        System.out.println(cuenta.getNombre()+" "+cuenta.getBalance());
                    }
                }
        -----------------------------------------------------------------------------------
        Patron Proxy
            El patrón de diseño Proxy es simplemente un intermediario que se diseña sobre 
            una clase para agregar alguna funcionalidad adicional, sin modificar la clase original.

            El patrón Proxy nos dice que construyamos una nueva clase proxy con la misma interfaz 
            que tiene la clase original. Luego en vez de utilizar la clase original, utilizamos 
            la clase proxy en todos los clientes que antes usaban la clase original. La clase 
            proxy agrega la funcionalidad deseada y luego delega todo el trabajo a la clase original.

            Para crear este patrón necesitamos definir una interfaz de la cual extenderá la clase 
            principal. Luego crearemos nuestra clase proxy que también extenderá de la interfaz y 
            tendrá como atributo la clase principal.

            Este patrón se conforma principalmente de tres partes.

                Interfaz: de la que se creará la clase principal.
                Clase principal: que implementa la interfaz.
                Clase proxy: la clase que también extenderá de la interfaz y que contendrá como 
                atributo la clase principal.

            Es uno de los patrones que más se usa en frameworks como Spring o standards como EJB.  
            La clave es entender que se trata de un intermediario que añade funcionalidad y modifica 
            el comportamiento por defecto de nuestras clases.

            Algunas ventajas y desventajas de este patrón:
                Podemos modificar funcionalidad sin afectar a los clientes.
                El código se puede volver complicado si es necesario introducir gran cantidad de funcionalidad nueva.
                La respuesta real del servicio puede ser desconocida hasta que realmente se la invoca. No sabemos si
            Conclusión
                Hemos visto el patrón Proxy y lo útil que puede resulta para agregar funcionalidad a una clase sin modificarla.

            Ejemplo_______________________________________________________________________
                public interface IServicioMensajes {
                    public String mensaje(String persona);
                }
                ---------------------------------------------------------------------
                public class ServicioMensajesImp implements IServicioMensajes {
                    @Override
                    public String mensaje(String persona) {
                        return "Hola "+persona;
                    }
                }
                ---------------------------------------------------------------------
                //Extrae la funcionalidad de Log a otra clase ServicioMensajeProxy
                public class ServicioMensajesProxy implements IServicioMensajes {
                    //Se instancia la interfaz
                    private IServicioMensajes sm;
                    
                    //En el constructor se crea implementacion
                    public ServicioMensajesProxy() {
                        super();
                        this.sm = new ServicioMensajesImp();
                    }
                    //En el metodo sobreescrito, se realiza el proceso nuevo y se ejecuta el mensaje
                    @Override
                    public String mensaje(String persona) {
                        System.out.println("Mensaje de log.");
                        return sm.mensaje(persona);
                    }

                }
    ------------------------------------------------------------------------------------------------
    Patrones de diseño estructurales
    ------------------------------------------------------------------------------------------------
        Patron de diseño decorator
            La definición oficial dice que “permite el ajuste dinámico de objetos para 
            modificar sus responsabilidades y comportamientos existentes.”

            La ventaja es que de este modo no afectamos a todas las clases. Cada clase define una 
            funcionalidad específica que se agrega y decora a la clase principal sin necesidad de 
            crear subclases.

            A veces se desea adicionar responsabilidades a un objeto pero no a toda la clase. 
            Las responsabilidades se pueden adicionar por medio de los mecanismos de Herencia, 
            pero este mecanismo no es flexible porque la responsabilidad es adicionada estáticamente. 
            La solución flexible es la de rodear el objeto con otro objeto que es el que adiciona 
            la nueva responsabilidad. Este nuevo objeto es el Decorator.

            Este patrón se debe utilizar cuando:
                *Hay una necesidad de extender la funcionalidad de una clase, pero no hay 
                razones para extenderlo a través de la herencia.
                *Se quiere agregar o quitar dinámicamente la funcionalidad de un objeto.

            Partes del patrón de diseño Decorator
                +Component Interface: es la interface (puede ser una clase abstracta también) 
                que define la funcionalidad y de la cuál se hereda la clase concreta y los 
                decoradores.
                +Concrete Component: es la implementación principal y cuya clase recibirá 
                los decoradores para agregar funcionalidad extra dinámicamente.
                +Decorator: puede ser una clase abstracta o no que define el Decorador que 
                hereda de la interfaz Component y de la cual luego se crearán todos los 
                demás decoradores. El decorador debe mantener la referencia al objeto 
                original a fin de invocarlo y luego agregarle otras funcionalidades propias 
                del decorador. Cada decorador tiene una relación con el componente de tipo 
                HAS-A (tiene un).
                +Concrete Decorator: son las clases que extienden o implementan el Decorator 
                con la funcionalidad acotada.
            Ejemplo_______________________________________________________________________

                +Component Interface
                public interface IAuto  {
                    String getDescripcion();
                    Double getPrecio();
                }
                -----------------------------------------------------------------------
                public class FordFiesta implements IAuto{

                    @Override
                    public String getDescripcion() {
                        return "Auto modelo FordFiesta";
                    }

                    @Override
                    public Double getPrecio() {
                        return 20000.0;
                    }

                }
                -----------------------------------------------------------------------
                public class FiatUno implements IAuto{

                    @Override
                    public String getDescripcion() {
                        return "Auto modelo FiatUno";
                    }

                    @Override
                    public Double getPrecio() {
                        return 15000.0;
                    }
                }
                ---------------------------------------------------------------------
                +Decorator
                public abstract class AutoDecorator implements IAuto {

                    private IAuto vendible;

                    public AutoDecorator(IAuto vendible) {
                        super();
                        this.vendible = vendible;
                    }

                    public IAuto getVendible() {
                        return vendible;
                    }

                    public void setVendible(IAuto vendible) {
                        this.vendible = vendible;
                    }

                }
                ---------------------------------------------------------------------
                +Concrete Decorator
                public class CDPlayer extends AutoDecorator{

                    public CDPlayer(IAuto vendible) {
                        super(vendible);
                    }

                    @Override
                    public String getDescripcion() {
                        return getVendible().getDescripcion() + " + CDPLAYER";
                    }

                    @Override
                    public Double getPrecio() {
                        return getVendible().getPrecio() + 8000.0;
                    }

                }

                --------------------------------------------------------------------
                +Concrete Decorator
                public class AireAcondicionado extends AutoDecorator{

                    public AireAcondicionado(IAuto vendible) {
                        super(vendible);
                    }
                 
                    @Override
                    public String getDescripcion() {
                        return getVendible().getDescripcion() +" + Aire Acondicionado";
                    }

                    @Override
                    public Double getPrecio() {
                        return getVendible().getPrecio() + 15000;
                    }

                }
        -----------------------------------------------------------------------------------
        Patron de diseño facade 
            El patrón de diseño Facade simplifica la complejidad de un sistema mediante 
            una interfaz mas sencilla. Mejora el acceso a nuestro sistema logrando que 
            otros sistemas o subsistemas usen un punto de acceso en común que reduce la 
            complejidad, minimizando las interacciones y dependencias.

            Se trata de un patrón de diseño bastante útil en vista de que también desacopla 
            los sistemas.

            Dijimos que el patrón de diseño Facade dispone una interfaz simple y accesible 
            a otros sistemas o subsistemas. De este modo simplifica la complejidad a los 
            clientes externos exponiendo una interfaz más clara y un acceso unificado a 
            estas funcionalidades haciendo más fácil su uso.

            Ejemplo_________________________________________________________________
                public class Bateria {
                    public void on() {
                        System.out.println("Bateria prendida");
                    }
                    

                    public void of() {
                        System.out.println("Bateria apagada.");
                    }
                }

                ----------------------------------------------------------------

                public class CPU {
                    public void inicia() {
                        System.out.println("CPU comienza.");
                    }
                    
                    public void termina() {
                        System.out.println("CPU termina.");
                    }
                }
                ----------------------------------------------------------------
                public interface ITelefonoServices {
                    void inicio();
                    void fin();
                }
                ----------------------------------------------------------------
                public class GPSService implements ITelefonoServices{
                    @Override
                    public void inicio() {
                        System.out.println("GPS prende.");
                        
                    }
                    @Override
                    public void fin() {
                        System.out.println("GPS se apaga.");
                        
                    }
                }
                ---------------------------------------------------------------
                public class WifiService implements ITelefonoServices{
                    @Override
                    public void inicio() {
                        System.out.println("Wifi prende.");
                        
                    }
                    @Override
                    public void fin() {
                        System.out.println("Wifi se apaga.");
                        
                    }
                }
                ---------------------------------------------------------------
                public class Telefono {
                    private final Bateria bateria;
                    private final CPU cpu;
                    private final List<ITelefonoServices> lista;
                    
                    public Telefono(Bateria bateria, CPU cpu, List<ITelefonoServices> listaServ) {
                        this.bateria = bateria;
                        this.cpu = cpu;
                        this.lista = listaServ;
                    }

                    public Bateria getBateria() {
                        return bateria;
                    }

                    public CPU getCpu() {
                        return cpu;
                    }

                    public List<ITelefonoServices> getLista() {
                        return lista;
                    }
                }
                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                public class Fachada {
                    public Telefono on() {
                        
                        Bateria bateria = new Bateria();
                        bateria.on();
                        
                        CPU cpu = new CPU();
                        cpu.inicia();
                        
                        ITelefonoServices wifi = new WifiService();
                        wifi.inicio();
                        
                        ITelefonoServices gps = new GPSService();
                        gps.inicio();
                        
                        List<ITelefonoServices> lista = Arrays.asList(wifi,gps);
                        
                        Telefono telefono = new Telefono(bateria, cpu, lista);
                        
                        return telefono;
                    }
                    
                    public void of(Telefono telefono) {
                        for (ITelefonoServices item : telefono.getLista()) {
                            item.fin();
                        }
                        
                        telefono.getBateria().of();
                        telefono.getCpu().termina();
                    }
                }
                ----------------------------------------------------------------
                public class PrincipalFacade {
                    public static void main(String[] args) {
                        Fachada facade = new Fachada();
                        Telefono telefono = facade.on();
                        System.out.println("****************************************************");
                        facade.of(telefono);
                    }
                }
    ------------------------------------------------------------------------------------------------
    Patrones de diseño arquitectura
    ------------------------------------------------------------------------------------------------
        Ppatrón Circuit Breaker 
            El patrón Circuit Breaker evita que una aplicación intente de manera reiterada una 
            operación que con probabilidad vaya a fallar, permitiendo que esta continúe con su 
            ejecución sin malgastar recursos mientras el problema no se resuelva. Además este 
            patrón puede detectar cuando se ha resuelto el problema permitiendo de esta manera 
            volver a ejecutar la operación comprometida. Podemos entender este patrón como un 
            proxy entre nuestra aplicación y el servicio remoto que se implementa como si fuera 
            una máquina de estados que imita el comportamiento de un interruptor de un circuito 
            eléctrico.

            Los estados:

                * Closed:   El circuito está cerrado y el flujo fluye ininterrumpidamente. 
                            Este es el estado inicial, todo funciona bien, la aplicación 
                            funciona de la manera esperada y la llamada al recurso/servicio 
                            se realiza de manera normal.
                * Open:     El circuito está abierto y el flujo interrumpido. En este estado 
                            todas las llamadas al recurso/servicio fallan inmediatamente, es 
                            decir no se realizan, devolviendo la última excepción conocida a 
                            la aplicación.
                * Half-Open: El circuito está medio abierto (o medio cerrado) dando una 
                            oportunidad al flujo para su restauración. En este estado la 
                            aplicación volverá a intentar realizar la petición al 
                            servicio/recurso que fallaba.

            Los cambios de estado:

                Como ya hemos comentado, el estado inicial es Closed. El proxy mantiene un contador 
                con el número de errores que se producen al realizar la llamada, si el número de 
                errores excede el límite especificado por configuración el proxy establece el 
                estado a Open. Además, este punto es muy importante, al mismo tiempo se inicia 
                un temporizador.

                Mientras el estado sea Open las llamadas al servicio no se realizarán, devolviendo 
                de manera automática el último error conocido. El tiempo en que el proxy permanece 
                en este estado lo marca la configuración del temporizador.

                Cuando el temporizador concluye su ciclo el estado pasa a ser Half-Open. En 
                este estado la llamada al servicio vuelve a estar disponible al menos una vez de 
                manera que:

                    Si la petición funciona correctamente se asume que el error se ha corregido, 
                    se restablece a cero el contador de errores y se establece el estado del 
                    proxy a Closed de nuevo. Todo vuelve a funcionar correctamente.

                    Si por lo contrario se produce algún error en la petición se asume que el 
                    error continua, se establece de nuevo el estado a Open y se reinicia el 
                    temporizador. El servicio/recurso continua siendo inaccesible.
************************************************************************************************




****************************************************************************************Facelets

    1.- Se crea proyecto y se escoje el framewrok JSF, luego se escoje el lenguaje a utilizar entre facelets 
    	*Se te crean pagina xhtml
    	*Se carga la libreria html
    	<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://xmlns.jcp.org/jsf/html">
    2.- Se crea una nueva pagina jsf Page
    	2.1.- new --> otro --> JavaServer Faces --> JSF Page
    	2.2.- Se crea un div --> Page
    	2.3.- Se crean tres div id -->Header, content, Footer
    	<div id="Page">
                <div id="Header">

                </div>

                <div id="Content">

                </div>

                <div id="Footer">

                </div>
            </div>
    	2.4.- Se colocan caracteristicas de estilo
    	<style>
            	#Page{height: 680px; width: 1000px; margin: 0 auto}
            	#Header{background: blue; width: 1000px; height: 30%; margin: 0}
            	#Content{float: left; background: red; width: 700px; height: 30%; margin: 0}
            	#Footer{background: blue; width: 1000px; height: 30%; margin: 0 auto; display: 	inline-block}
        	</style>
    3.- Se realiza la maquetacion con los componentes ui
    	3.1.- Se agrega la anotacion ui:insert dentro del div
    		*Toto lo que coloque dentro de la insercion hara el reemplazo por el cliente que 		este consumiento la plantilla
    	 <ui:insert name="header">
                        <p>Estoy dentro del insert - header</p>
                         
             </ui:insert>
    4.- Se crea cliente que consumira nuestra plantilla jsf
    	4.1.- Se agrega el componente ui: composition indicando la plantilla a utilizar
    	<ui:composition template="CommonLayaut.xhtml">
                
            </ui:composition>
    	*Si un cliente esta utilizando una plantilla, todo lo que esta fuera del composition 		sera ignorada
    5.- Se crea componente ui:include
    	*Nos permite incluir pagina estaticas dentro de un bloque
    	5.1.- Se crea un nuevo jsf Page --> CammonHeader
    	5.2.- Se crea contenido en la pagina
    	5.3.- Por medio del componente ui:include podemos incluir el contenido de la pagina 	cliente
    	<ui:include src="CammonHeader.xhtml">
    	</ui:include>
    	*Si se quiere incluir una parte de la pagina se debe colocar o crear dentro del tag 	composition
    6.- Se incluye en el cliente en un formulario y lo mostrara junto a la plantilla
    	6.1.- La pagina Dafault.xhtml
    	 <ui:composition template="CommonLayaut.xhtml">
                <ui:define name="content">
                    <h:form>
                        <h:outputLabel value="Introduzca un mensaje."></h:outputLabel><br></br>
                        <h:inputText value="Nombre"></h:inputText><br></br>
                        <h:commandButton action="submit" value="Enviar"></h:commandButton>
                    </h:form>
                </ui:define>
            </ui:composition>
    	6.2.- La pagina plantilla 
    	<div id="Content">
                    <p>Estoy fuera del insert - content</p>
                    <ui:insert name="content">
                        <p>Estoy dentro del insert - content</p>
                         
                    </ui:insert>
             </div>
************************************************************************************************




********************************************************************************************JSF
    1.- Se crea proyecto y se escoje el framewrok JSF, luego se escoje el lenguaje a utilizar entre facelets y JSP
    	*EL jsf TRABAJA EN LAS CAPAS DE VISTA Y CONTROLADOR
    	*trabajo con un ojeto face-servlet, que es el que se encarga de interpretar todas las 			anotaciones
    	*Permite hacer una mejor abstraccion de las capaz con respecto a JSP 
    	*Al crearse un proyecto JSF, en el jsp se importan las librerias de jsf con las que se 			van a trabajar core y html

    	<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
    	<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

    2.- Se crea el archivo JSF Faces Configuration
    	*Controla la navegacion de las paginas
    	*Controla Los beans que van a ser asociados a cada pagina JSF
    	2.1.- Propiedades del proyecto --> new -->Otros-->Java Server Faces-->JSF Faces 		Configuration
    	2.2.- No se le cambia el nombre faces-config
    	2.3.- Al crearla se crea en la carpeta WEB-INF

    2.a.- Se crea un formulario en la pagina jsp, utilizando notaciones de JSF, las de la libreria html en este caso
    	2.a.1.- Se crea un formulario con el prefijo h
    	<h:form >
                    <h:panelGrid columns="2">
                        <h:outputLabel value="Nombre:"></h:outputLabel>
                        <h:inputText value="Nombre"></h:inputText>

                        <h:outputLabel value="Contraseña:"></h:outputLabel>
                        <h:inputText value="Contraseña"></h:inputText>
                        <h:commandButton action="submit" value="Entrar"></h:commandButton>
                    </h:panelGrid>
                </h:form> 

    3.- Se crea Managed Beans y se tilda añadir a la configuracion xml
    	*Cada formulario debe tener atado un Mange Bean
    	3.1.- Propiedades del proyecto --> new--> Otro-->Java Server Faces--> JSF Managed Bean
    	3.2.- Se pone nombre, paquete, se añade al archivo faces-config.xml y el alcance o scope
    	3.3.- Se crean las propiedades
    	3.4.- Se encapsulan
    	3.5.- Se valida que se halla creado en el archivo de configuraciones

    4.- Luego ya se pueden enlazar la pagina JSF con el Managed Beans por medio de las etiquetas y el bean configurado -->usuario 	<h:form >
                    <h:panelGrid columns="2">
                        <h:outputLabel value="Nombre:"></h:outputLabel>
                        <h:inputText value="#{usuario.nombre}"></h:inputText>

                        <h:outputLabel value="Contraseña:"></h:outputLabel>
                        <h:inputText value="#{usuario.contrasena}"></h:inputText>
                        <h:commandButton action="submit" value="Entrar"></h:commandButton>
                    </h:panelGrid>
                </h:form>
    Ej: <h:inputText value="#{usuario.password}"></h:inputText>

    ----------------------------------Navegacion JSF-----------------------------------------------
    4.a.- Se crea una pagina JSF
    	4.a.1.- Se coloca nombre --> consultar
    	4.a.2.- Se marca la opcion JSP File Standard Syntax)

    4.b.- Se crea una pagina JSF  
    	4.b.1.- Se coloca nombre --> accesoDenagado
    	4.b.2.- Se marca la opcion JSP File Standard Syntax)

    4.c.- Se agrega boton a la pagina principal para consultar y navegar por paginas  
    	
    5.- Puedes crear y configurar jsps para la navegacion, por medio del archivo de configuracion
    	5.1.- Esta configuracion se hace por medio del archivo de configuracion face-config.xml
    	5.2.- Tiene una parte grafica que es el PageFlow
    	5.3.- Se indica hacia donde ir con cada parametro
    Ej:
       <navigation-rule>
            <from-view-id>/welcomeJSF.jsp</from-view-id>
            <navigation-case>
                <from-outcome>consultar </from-outcome>
                <to-view-id>/consultar.jsp</to-view-id>
            </navigation-case>
            <navigation-case>
                <from-outcome>negado</from-outcome>
                <to-view-id>/accesoDenegado.jsp</to-view-id>
            </navigation-case>
        </navigation-rule>

    6.-Configurar un commadButton en el action para que vaya a la pagina deseada (Opcional), segun el resultado de algun metodo
    	6.1.- Se crea metodo en la clase usuario que retorne una cadena "consular" en caso que 		el nombre sea admin
    	public String acceso(){
            	if(this.nombre.equals("Admin")){
                		return "consultar";
            	}else{
                		return "negado";
            	}
        	}
    	6.2.- Se añade al action del boton el llamado al metodo creado, por medio del bean 		creado en la pagina
    	<h:commandButton action="#{usuario.acceso()}" value="Ver Datos"></h:commandButton>
    	6.3.- Se configura el faces-config.xml para que realice la navegacion
    	<navigation-rule>
            	<from-view-id>/welcomeJSF.jsp</from-view-id>
            	<navigation-case>
                		<from-outcome>consultar</from-outcome>
                		<to-view-id>/consultar.jsp</to-view-id>
            	</navigation-case>
            	<navigation-case>
                		<from-outcome>negado</from-outcome>
                		<to-view-id>/accesoDenegado.jsp</to-view-id>
           	 	</navigation-case>
        	</navigation-rule>


    -----------------------------------------------------------------------------------------------
    En el comienzo de la página tenemos las directivas que indican que las marcas con prefijo f, tales como f:view 
    corresponden a la librería java.sun.com/jsf/core, mientras que las marcas con prefijo h, se encuentran en la 
    librería java.sun.com/jsf/html.


    ------------------------------------Validaciones-----------------------------------------------
    1.-Se raliza validacion para que el campo de nombre sea obligatorio y tenga informacion antes de enviar al modelo
    <h:inputText id="nombre" value="#{candidato.nombre}" required="true"  />

    2.- Validacion de rango para un campo
    <td><h:inputText id="sueldoDeseado" required="true" value="#{candidato.sueldoDeseado}">
            <f:validateLongRange minimum="1000" maximum="50000" />
    </h:inputText></td>

    3.- Se crea mensage de validacion 

    String msg = "Gracias, pero Elvis Areiza ya trabaja con nosotros.";
    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
    FacesContext facesContext = FacesContext.getCurrentInstance();
    String clientId = null; //Este es un mensaje global
    facesContext.addMessage(clientId, facesMessage);

    4.- Se crea componente en la pagina para que despliegue los mensajes de error que no han sido asociados a ningun campo
    <h:messages globalOnly="true" ></h:messages>

    ------------------------------------Convertidores-----------------------------------------------
    --------------------------JSF tiene convertidores estándar--------------------------------------	
    1.- Convertidor de Números: Permite convertir el valor de un componente en alguna
    subclase de java.lang.Number.
    Ej. <f:convertNumber type="currency" /> 
    <td><h:outputLabel for="fechaNacimiento" value="Fecha de Nacimiento:"></h:outputLabel></td>
    <td><h:inputText id="fechaNacimiento" required="true" value="#{candidato.fechaNacimiento}">
           <f:convertDateTime pattern="dd/MM/yyyy" /></h:inputText></td>
    <td><h:message for="fechaNacimiento"/></td>

    2.- Convertidor de Fechas: Permite convertir un valor de un componente a la clase
    java.util.Date.
    Ej. <f:converterDateTime pattern="dd/MM/yyyy" />
    Para consultar un lista de de formatos para cadena, consultar la clase
    java.text.SimpleDateFormat del API de Java.


    --------------------JSF permite crear convertidores personalizados------------------------------
    1.- El tag JSF, por ejemplo:
    <h:inputText id="fechaId" value="#{empleadoBean.fechaNacimiento}"
    convert="util.ConvertidorFecha" />

    2.- Clase Java: Contiene el código que implementa el convertidor, debe
    implementar la interfaz javax.faces.convert.Converter, además debemos
    sobreescribir el método getAsObject(…) o getAsSring(…) dependiendo del tipo de
    conversión que se requiera.

    3.- El convertidor debe registrarse en el archivo faces-config.xml o se puede utilizar
    la anotación @javax.faces.convert.FacesConverter

    4.- Podemos arrojar una excepción con el siguiente código:
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de
    Conversión", “Fecha inválida");
    throw new ConverterException(message);


    ------------------------------Internalizacion de aplicacion------------------------------------
    ***El idioma por default de la aplicación se puede especificar ya sea en el
    archivo faces-config.xml o vía programática en algún método action de
    un Managed Bean. El código necesario es:
    FacesContext.getCurrentInstance().getViewRoot().setLocale("es_MX");

    ***Se puede configurar un archivo de propiedades (Resource Bundle), este se configura en:
    *Este se configura en el archivo faces-config.xml
    *Pagina JSF
    	<f:loadBundle basename="mensajes" var="msg"/>
    ***Una vez configurado el archivo de propiedades se utilizar en las paginas jsf
    	<h:outputText value="#{msg[‘form.usuario’]}" />
    	<h:commandButton value="#{msg.enviar}" type="submit" action="login" />

    ***Archivo de mensajes
    *Se crea un archivo de propiedades
    *Se crea archivo de propiedades para valore requeridos
    	javax.faces.component.UIInput.REQUIRED=Valor Requerido
    *Se crea archivo faces-config.xml
    *Se configura el archivo de propiedades resource-bundle y el archivo de mensajes por defecto jsf  
        <application>
            <locale-config>
                <default-locale>es</default-locale>
            </locale-config>
            <!--etiquetas del formulario-->
            <resource-bundle>
                <!--No se necesita agregar la extension del archivo-->
                <base-name>mensajes</base-name>
                <var>msgs</var>
            </resource-bundle>
            <!--cambio de textos de validadores-->
            <message-bundle>jsf</message-bundle>
        </application>
***********************************************************************************************


Properties del sistema*************************************************************************
    /* Impresion de propiedades del sistema */
    @Test
    void inprimirSystemProperties() {
        Properties properties = System.getProperties();
        properties.forEach((k,v) -> System.out.println(k+":"+v));
    }
***********************************************************************************************

ManejoJava

Formato

****************************************Manejo de cantidades Grandes Decimales Clase BigDecimal
    ****Referencia--> https://www.concretepage.com/java/java-bigdecimal-tutorial-with-example
    BigDecimal montoCausionLista = BigDecimal.valueOf(lista.get(i).getMatOperacion().getMontoCausion()).setScale(2, RoundingMode.HALF_UP);
    BigDecimal montoCausionListaAgrup = BigDecimal.valueOf(listaAgrupada.get(j).getMatOperacion().getMontoCausion()).setScale(2, RoundingMode.HALF_UP);
    BigDecimal montoCaucion = montoCausionListaAgrup.add(montoCausionLista);
    listaAgrupada.get(j).getMatOperacion().setMontoCausion(montoCaucion.doubleValue());
***********************************************************************************************



*******************************************************************Manejo de formato decimales
    Usando DecimalFormat

    DecimalFormat df = new DecimalFormat("#.00");
    System.out.println(df.format(number));
     /* Salida : 1.42*/
    Usando String.Format

    System.out.println(String.format("%.2f", number));
    /* Salida : 1.42*/
    Si solo desea a que la salida tenga ese formato aplicaría numberformat

    System.out.printf("Valor: %.2f", number ); 
    /* Salida : 1.42*/
    Mediante Math.Round() donde la cantidad de ceros es la cantidad de decimales a limitar

    System.out.println((double)Math.round(number * 100d) / 100d);
     /* Salida : 1.42*/
    Usando la clase BigDecimal , usando el método setScale que recibe dos parámetros la 
    cantidad de decimales a limitar y el modo de redondeo

    BigDecimal bd = new BigDecimal(number);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    System.out.println(bd.doubleValue());
    /* Salida 1.42*/
**********************************************************************************************


****************************************Dar formato con dos decimales un double con BigDecimal
    double montoOper=(bd.setScale(2, RoundingMode.HALF_UP)).doubleValue();
    -------------------------------------------------------------------------------------
    BigDecimal bd = new BigDecimal(limiteOperativoCliente);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    limiteOperativoCliente = bd.doubleValue();
**********************************************************************************************


***************************************************************************************JAVA SQL

    *******************CLASES DE Envío de instrucciones a la Base de Datos
            ? Connection: métodos para crear instrucciones y para gestionar sus propiedades.
            ? Statement: permite enviar instrucciones a la Base de Datos.
            ? PreparedStatement: permite usar instrucciones preparadas (parametrizadas) o sql básicas.
            ? CallableStatement: llamada a procedimientos almacenados en la Base de Datos.

    *******************Recuperación de los resultados
            ? ResultSet: conjunto de resultados que retorna la ejecución de una consulta (query).
            ? ResultSetMetaData:  información sobre columnas del objeto ResultSet.

    Las instrucciones SQL puede ser instrucciones DML (Insert, Update, Delete), DDL (Create, Drop)

    *******************Statement
            ? Como se crea: 
                ? Statement stmt= conn.createStatement();

            ? Métodos de ejecución: 
                ?  Ejecución de instrucciones SELECT 
                    •  ResultSet resultadoSelect = stmt.executeQuery(sql);
                ? Ejecución de instrucciones DML/DDL
                    • int resultadoDML = stmt.executeUpdate(sql);

            ? Como se crea: 
                ? PreparedStatement stmt= conn.prepareStatement(sql);

            ? Métodos 
                ? executeQuery(String sql) 
                    •  Ejecución de consultas: SELECT
                    • Devuelve un objeto ResultSet
                ? executeUpdate(String sql) 
                    •  Modificaciones en la BD: INSERT, UPDATE, DELETE
                    • Devuelve el número de columnas afectadas
                ? execute(String sql)
                    • Ejecución de instrucciones que pueden devolver varios conjuntos de 
    		resultados
                    • Requiere usar luego getResultSet() o getUpdateCount() para recuperar 
    		los resultados, y getMoreResults() para ver los siguientes resultados

    *******************PreparedStatement (hereda de Statement)
            ? Como se crea: 
                ? PreparedStatement stmt= conn.prepareStatement(sql);

                ? Pasando parámetros: 
                    • stmt.setInt(1,“10”);  //Si quisiéramos el producto 10

    ******************ResultSet
    	1.-Tiene el mismo comportamiento de un cursor
    	2.-Para moverse entre filas se emplea ResultSet.next()
    	Para obtener una columna especifica de la fila, se puede hacer invocando el 
    	3.-método ResultSet.getXXX (xxx indica el tipo de datos)
    Ej:
    ResultSet resultado_ = stmt_.executeQuery(sql);

    ******************Secuencia normal de flujo
        1. Establecer la conexión con la Base de Datos
            a. Cargar controladores
            b. Establecer la conexión 
        2. Crear un objeto Statement para hacer petición a la Base de Datos
            a. Asociar una sentencia SQL al objeto Statement
            b. Proporcionar valores de los parámetros 
            c. Ejecutar el objeto Statement
        3. Procesar los resultados 
        4. Liberar recursos (cerrar la conexión)

    -----------------------------------------------------------------------------------------------
    ****************Ejemplo Insertar

    public boolean insertarPersona(Persona per){
            boolean rpta= false;
            String sql= "insert into persona values(?,?,?,?,?,?);";
            Connection cn= null;
            cn=Conexion.getConexion();
            try {
                PreparedStatement psmt = cn.prepareStatement(sql);
                psmt.setInt(1, per.getCedula());
                psmt.setString(2, per.getNombre());
                psmt.setString(3, per.getApellido());
                psmt.setString(4, per.getDireccion());
                psmt.setInt(5, per.getTelefono());
                psmt.setInt(6, per.getEstatus());
                System.out.println(sql);
                System.out.println(psmt);
                int i=psmt.executeUpdate();
                rpta = true;
            } catch (Exception e) {
                System.out.println("Error SQL."+e);
            }
            return rpta;
        }

    -----------------------------------------------------------------------------------------------
    Ejemplo modificar

    public boolean modificarPersona(int ced, Persona per){
            String sql= "UPDATE persona SET  nombre = ?, apellido = ?, "
                    + "direccion = ?, telefono = ?, estatus = ? "
                    + "WHERE cedula = 16225262";
            Connection cn= null;
            cn=Conexion.getConexion();
            try {
                PreparedStatement psmt = cn.prepareStatement(sql);
                psmt.setString(1, per.getNombre());
                psmt.setString(2, per.getApellido());
                psmt.setString(3, per.getDireccion());
                psmt.setInt(4, per.getTelefono());
                psmt.setInt(5, per.getEstatus());
                System.out.println(sql);
                System.out.println(psmt);
                int i=psmt.executeUpdate();
                rpta = true;
            } catch (Exception e) {
                System.out.println("Error SQL."+e);
            }
            return rpta;
        }

    -----------------------------------------------------------------------------------------------
    Ejemplo borrar

    public boolean eliminarPersona(int ced){
            String sql = "DELETE FROM persona WHERE cedula = ? ";
            Connection cn= Conexion.getConexion();
            try {
                PreparedStatement psmt = cn.prepareStatement(sql);
                psmt.setInt(1, ced);
                System.out.println(sql);
                System.out.println(psmt);
                int i= psmt.executeUpdate();
                rpta= true;
            } catch (Exception e) {
                System.out.println("Error Sql. "+e);
            }
            
            return rpta;
        }


    -----------------------------------------------------------------------------------------------
    ***************Ejemplo:
    try { 
    Statement instruccion = conexion.createStatement(); 
    String query = "SELECT * FROM clientes WHERE nombre LIKE \"Empresa%\"";
     ResultSet resultados = instruccion.executeQuery(query);
     System.out.println("Listado de clientes: ");
     while (resultados.next()) {
                    System.out.println("Cliente”+resultados.getString("nif") +", Nombre:”     
           +resultados.getString("nombre") +", Teléfono: " 
           +resultados.getString("telefono") );
    }
     } catch (Exception ex) {
     e.printStackTrace(); 
    }
***********************************************************************************************


*****************************************Rellena a la izquierda una cadena con espacios o ceros
    //Espacios
    System.out.println( StringUtils.leftPad("howtodoinjava", 20, " ") );

    //Java dejó una almohadilla de cadena con ceros
    
    System.out.println( StringUtils.leftPad("0123456789", 10, "0") );
    System.out.println( StringUtils.leftPad("789", 10, "0") );
    System.out.println( StringUtils.leftPad("56789", 10, "0") );
***********************************************************************************************

HttpServlet

***********************************************************************************HttpServlet
    Referencia --> https://programacion.net/articulo/servlets_basico_108/6

    Los métodos de la clase HttpServlet que manejan peticiones de cliente toman dos argumentos.

    Un objeto HttpServletRequest, que encapsula los datos desde el cliente.
    Un objeto HttpServletResponse, que encapsula la respuesta hacia el cliente.

    HttpServletRequest*******************************************************************
        Un objeto HttpServletRequest proporciona acceso a los datos de cabecera HTTP, 
        como cualquier cookie encontrada en la petición, y el método HTTP con el que 
        se ha realizado la petición. El objeto HttpServletRequest también permite 
        obtener los argumentos que el cliente envía como parte de la petición.

        Para acceder a los datos del cliente

            *El método getParameter devuelve el valor de un parámetro nombrado. 
            Si nuestro parámetro pudiera tener más de un valor, deberíamos utilizar 
            getParameterValues en su lugar. El método getParameterValues devuelve 
            un array de valores del parámetro nombrado. (El método getParameterNames 
            proporciona los nombres de los parámetros.
            *Para peticiones GET de HTTP, el método getQueryString devuelve en un 
            String una línea de datos desde el cliente. Debemos analizar estos datos 
            nosotros mismos para obtener los parámetros y los valores.
            *Para peticones POST, PUT, y DELETE de HTTP.
                Si esperamos los datos en formato texto, el método getReader devuelve 
                un BufferedReader utilizado para leer la línea de datos.
                Si esperamos datos binarios, el método getInputStream devuelve un 
                ServletInputStream utilizado para leer la línea de datos.
        Nota:
        Se debe utilizar el método getParameter[Values] o uno de los métodos que permitan 
        analizar los datos. No pueden utilizarse juntos en una única petición.
    *************************************************************************************
    HttpServletResponse******************************************************************
        Un objeto HttpServletResponse proporciona dos formas de devolver datos al usuario.

            *El método getWriter devuelve un Writer
            *El método getOutputStream devuelve un ServletOutputStream

        Se utiliza el método getWriter para devolver datos en formato texto al usuario 
        y el método getOutputStream para devolver datos binarios.

        Nota:
        Si cerramos el Writer o el ServletOutputStream después de haber enviado la 
        respuesta, permitimos al servidor saber cuando la respuesta se ha completado.
    *************************************************************************************
    Ejemplo___________________________________________________________________________
        Enumeration<String> enumeration = request.getHeaderNames();
        for (Enumeration<?> e = request.getHeaderNames(); e.hasMoreElements();) {
            String nextHeaderName = (String) e.nextElement();
            String headerValue = request.getHeader(nextHeaderName);
            System.out.println(nextHeaderName);
            System.out.println(headerValue);
        }
**********************************************************************************************

Sql

***********************************************************Trasa se sql y parametros con log4j
    <logger name="org.hibernate.SQL">
        <level value="DEBUG"/>
    </logger>
    
    <logger name="org.hibernate.type"> 
        <level value="TRACE"/>
    </logger>

    * Por el properties 
        hibernate.show_sql=false
        hibernate.format_sql=true
        hiberante.show_sql=true
        para ver localmente las consultas sql

        fijate en el archivo log4j2.xml
        ahi tenes que tener:
            <logger name="org.hibernate.type" level="TRACE"
                additivity="false">
                <AppenderRef ref="Console" />
            </logger>
        y
            <logger name="org.hibernate.sql" level="DEBUG"
                additivity="false">
                <AppenderRef ref="Console" />
            </logger>


**********************************************************************************************

Colecciones

*********************************************************************Manejo de Objetos For each
    //Recorre articulos
    for (Articulo a : articulos) {
        //Valida si encuentra el mismo articulo
        if (idproducto == a.getIdProducto()) {
            if (a.getCantidad()>0){
                //añade cantidad al id encontrado
                a.setCantidad(a.getCantidad() - 1);
                //cambia bandera
                flag = true;
                break;
            }
        }
    }
***********************************************************************************************


*********************************************************************************API COLLECTION
    Referencia --> https://www.adictosaltrabajo.com/2015/09/25/introduccion-a-colecciones-en-java/
    Una colección representa un grupo de objetos. Esto objetos son conocidos como elementos. 
    Cuando queremos trabajar con un conjunto de elementos, necesitamos un almacén donde poder 
    guardarlos. En Java, se emplea la interfaz genérica Collection para este propósito. 
    Gracias a esta interfaz, podemos almacenar cualquier tipo de objeto y podemos usar una 
    serie de métodos comunes, como pueden ser: añadir, eliminar, obtener el tamaño de la colección… 
    Partiendo de la interfaz genérica Collection extienden otra serie de interfaces genéricas. 
    Estas subinterfaces aportan distintas funcionalidades sobre la interfaz anterior.

    Tipos de colecciones:
        * Set
            +HashSet
            +TreeSet
            +LinkedHashSet
        *List
            +ArrayList
            +LinkedList
        *Map
            +HashMap
            +TreeMap
            +LinkedHashMap

    Set*********************************************************************************
        La interfaz Set define una colección que no puede contener elementos duplicados. 
        Esta interfaz contiene, únicamente, los métodos heredados de Collection añadiendo 
        la restricción de que los elementos duplicados están prohibidos. Es importante 
        destacar que, para comprobar si los elementos son elementos duplicados o no lo 
        son, es necesario que dichos elementos tengan implementada, de forma correcta, 
        los métodos equals y hashCode. 
        Para comprobar si dos Set son iguales, se comprobarán si todos los elementos 
        que los componen son iguales sin importar en el orden que ocupen dichos elementos.

        Ninguna de estas implementaciones son sincronizadas; es decir, no se garantiza 
        el estado del Set si dos o más hilos acceden de forma concurrente al mismo. 
        Esto se puede solucionar empleando una serie de métodos que actúan de wrapper 
        para dotar a estas colecciones de esta falta de sincronización:

            Java
            Set set = Collections.synchronizedSet(new HashSet());
            SortedSet sortedSet = Collections.synchronizedSortedSet(new TreeSet());
            Set set = Collections.synchronizedSet(new LinkedHashSet());


        HashSet:+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            Esta implementación almacena los elementos en una tabla hash. Es la implementación 
            con mejor rendimiento de todas pero no garantiza ningún orden a la hora de realizar 
            iteraciones. 
            Es la implementación más empleada debido a su rendimiento y a que, generalmente, 
            no nos importa el orden que ocupen los elementos. 
            Es importante definir el tamaño inicial de la tabla ya que este tamaño marcará 
            el rendimiento de esta implementación.
                //Se crea y se inicializa HashSet
                final Set hashSet = new HashSet(1_000_000);
                //Almacenamos el tiempo actual en milisegundos cuando comienza
                final Long startHashSetTime = System.currentTimeMillis();
                //Se  le añaden los elementos al hashSet
                for (int i = 0; i < 1_000_000; i++) {
                    hashSet.add(i);
                }       
                //Almacenamos el tiempo actual en milisegundos cuando termina
                final Long endHashSetTime = System.currentTimeMillis();
                //Se imprime cuanto tarda
                System.out.println("Time spent by HashSet: " + (endHashSetTime - startHashSetTime));
        TreeSet:+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            Esta implementación almacena los elementos ordenándolos en función de sus 
            valores. Es bastante más lento que HashSet. Los elementos almacenados deben 
            implementar la interfaz Comparable. 
                //Se crea TreeSet
                final Set treeSet = new TreeSet();
                //Almacenamos el tiempo actual en milisegundos cuando comienza
                final Long startTreeSetTime = System.currentTimeMillis();
                //Se  le añaden los elementos 
                for (int i = 0; i < 1000000; i++) {
                    treeSet.add(i);
                }
                //Almacenamos el tiempo actual en milisegundos cuando termina
                final Long endTreeSetTime = System.currentTimeMillis();
                //Se imprime cuanto tarda
                System.out.println("Time spent by TreeSet: " + (endTreeSetTime - startTreeSetTime));
        LinkedHashSet: ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            Esta implementación almacena los elementos en función del orden de inserción. 
            Es, simplemente, un poco más costosa que HashSet.
                //Se crea LinkedHashSet
                final Set linkedHashSet = new LinkedHashSet(1_000_000);
                //Almacenamos el tiempo actual en milisegundos cuando comienza
                final Long startLinkedHashSetTime = System.currentTimeMillis();
                //Se  le añaden los elementos 
                for (int i = 0; i < 1000000; i++) {
                    linkedHashSet.add(i);
                }
                //Almacenamos el tiempo actual en milisegundos cuando termina
                final Long endLinkedHashSetTime = System.currentTimeMillis();
                //Se imprime cuanto tarda
                System.out.println("Time spent by LinkedHashSet: " + (endLinkedHashSetTime - startLinkedHashSetTime));
    List********************************************************************************
        Referencia --> https://es.fondoperlaterra.org/comdifference-between-arraylist-and-linkedlist-in-java-20#:~:text=ArrayList%20permite%20el%20acceso%20aleatorio,los%20elementos%20de%20la%20lista.
        La interfaz List define una sucesión de elementos. A diferencia de la interfaz 
        Set, la interfaz List sí admite elementos duplicados. A parte de los métodos 
        heredados de Collection, añade métodos que permiten mejorar los siguientes puntos:

            *Acceso posicional a elementos: manipula elementos en función de su posición 
            en la lista.
            *Búsqueda de elementos: busca un elemento concreto de la lista y devuelve su 
            posición.
            *Iteración sobre elementos: mejora el Iterator por defecto.
            *Rango de operación: permite realizar ciertas operaciones sobre rangos de 
            elementos dentro de la propia lista.

        Dentro de la interfaz List existen varios tipos de implementaciones realizadas 
        dentro de la plataforma Java. Vamos a analizar cada una de ellas:

            ArrayList: +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                Esta es la implementación típica. Se basa en un array redimensionable 
                que aumenta su tamaño según crece la colección de elementos. Es la que 
                mejor rendimiento tiene sobre la mayoría de situaciones.

                Hay tres constructores de ArrayList:

                    ArrayList () 
                    ArrayList (Colección <? Extiende E> c) 
                    ArrayList (int capacidad)

                    El primero El constructor implementa una lista de matriz vacía. 
                    El segundo el constructor implementa una lista de matriz inicializada 
                    usando el Colección c elementos. 
                    El tercero el constructor implementa la lista de matrices con el 
                    capacidad proporcionado en el argumento. 

                Al trabajar con ArrayList, a veces será necesario convertir la Collection 
                ArrayList en una matriz. Se puede hacer llamando toArray ().
                ---------------------------------------------------------------
                    //Add
                    ArrayList<String> cars = new ArrayList<String>();
                    cars.add("Volvo");
                    cars.add("BMW");
                    cars.add("Ford");
                    cars.add("Mazda");
                    System.out.println(cars);
                    //Cambio de Item
                    cars.set(0, "Opel");
                    //Remueve un item
                    cars.remove(0);
                    //remueve todos los items
                    cars.clear();
                    //devuelve el tamaño
                    ArrayList Size
            LinkedList: ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                Esta implementación permite que mejore el rendimiento en ciertas ocasiones. 
                Esta implementación se basa en una lista doblemente enlazada de los 
                elementos, teniendo cada uno de los elementos un puntero al anterior y 
                al siguiente elemento.

                No se puede acceder al azar a la lista vinculada implementada usando LinkedList. 
                Si desea recuperar cualquier elemento de la lista, debe iterar la lista 
                para buscar ese elemento.

                Hay dos constructores en la clase LinkedList:

                    LinkedList () 
                    LinkedList (Colección <? Extiende E> c)

                    El primero El constructor crea una lista enlazada vacía. 
                    El segundo El constructor crea una lista vinculada, 
                    inicializada con los elementos de Colección C.
                    ------------------------------------------------------------
                    //Add
                    LinkedList<String> cars = new LinkedList<String>();
                    cars.add("Volvo");
                    cars.add("BMW");
                    cars.add("Ford");
                    cars.add("Mazda");
                    System.out.println(cars);

                    addFirst()  Adds an item to the beginning of the list.  
                    addLast()   Add an item to the end of the list  
                    removeFirst()   Remove an item from the beginning of the list.  
                    removeLast()    Remove an item from the end of the list 
                    getFirst()  Get the item at the beginning of the list   
                    getLast()   Get the item at the end of the list

        Ninguna de estas implementaciones son sincronizadas; es decir, no se garantiza 
        el estado del List si dos o más hilos acceden de forma concurrente al mismo. Esto 
        se puede solucionar empleando una serie de métodos que actúan de wrapper para dotar 
        a estas colecciones de esta falta de sincronización:

            Java
            List list = Collections.synchronizedList(new ArrayList());
            List list = Collections.synchronizedList(new LinkedList());
    Map*********************************************************************************
        La interfaz Map asocia claves a valores. Esta interfaz no puede contener claves 
        duplicadas y; cada una de dichas claves, sólo puede tener asociado un valor como 
        máximo.

        Dentro de la interfaz Map existen varios tipos de implementaciones realizadas 
        dentro de la plataforma Java. Vamos a analizar cada una de ellas:

            HashMap+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                Un HashMap es la implementación de la interface Map, esta interface es un tipo de 
                Collection que almacena datos asociando una llave a un valor, esta interface sirve 
                para muchas cosas y tiene ciertas caracteristicas que la definen, por ejemplo, no 
                permite key duplicados, cada key tiene que estar asociado a un valor como máximo, 
                si agregas un key que ya existe sobrescribe el valor del key anterior, solo permite 
                Object types lo que quiere decir que no puedes poner un valor primitivo...

                El HashMap funciona con el principio del hashing, como ya explique trabaja asignando 
                una ubicación a una key con el método hashCode() de Java.

                    Map miMapa=new HashMap();
                    //Lave, valor
                    miMapa.put("1", "Juan");
                    miMapa.put("2", "Carlos");
                    miMapa.put("3", "Rosario");
                    miMapa.put("4", "Esperanza");
                    //Se imprimen todas las llaves
                    imprimir(miMapa.keySet());
                    //Se imprimen todos los valores
                    imprimir(miMapa.values());
                    }

                    private static void imprimir(Collection coleccion) {
                        for(Object elemento :coleccion) {
                            System.out.print(elemento +" ");
                        }
                        System.out.println("");
                    }
                ------------------------------------------------------------------
                // Create a HashMap object called capitalCities
                HashMap<String, String> capitalCities = new HashMap<String, String>();

                // Add keys and values (Country, City)
                capitalCities.put("England", "London");
                capitalCities.put("Germany", "Berlin");
                capitalCities.put("Norway", "Oslo");
                capitalCities.put("USA", "Washington DC");
                System.out.println(capitalCities);
                //Accede a un Item
                capitalCities.get("England");
                //Remueve un Item
                capitalCities.remove("England");
                //Devuelve el tamaña de Item
                capitalCities.size();

                // Print keys
                for (String i : capitalCities.keySet()) {
                  System.out.println(i);
                }
                // Print values
                for (String i : capitalCities.values()) {
                  System.out.println(i);
                }
                // Print keys and values
                for (String i : capitalCities.keySet()) {
                  System.out.println("key: " + i + " value: " + capitalCities.get(i));
                }
            TreeMap:++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                Esta implementación almacena las claves ordenándolas en función de sus 
                valores. Es bastante más lento que HashMap. Las claves almacenadas deben 
                implementar la interfaz Comparable. Esta implementación garantiza, siempre, 
                un rendimiento de log(N) en las operaciones básicas, debido a la estructura 
                de árbol empleada para almacenar los elementos.

                // Declaración de un Map (un HashMap) con clave "Integer" y Valor "String". 
                //Las claves pueden ser de cualquier tipo de objetos, aunque los más utilizados 
                //como clave son los objetos predefinidos de Java como String, Integer, Double ... 
                //!!!!CUIDADO los Map no permiten datos atómicos
                Map<Integer, String> nombreMap = new HashMap<Integer, String>();
                nombreMap.size(); // Devuelve el numero de elementos del Map
                nombreMap.isEmpty(); // Devuelve true si no hay elementos en el Map y false si si los hay
                nombreMap.put(K clave, V valor); // Añade un elemento al Map
                nombreMap.get(K clave); // Devuelve el valor de la clave que se le pasa como parámetro o 'null' si la clave no existe
                nombreMap.clear(); // Borra todos los componentes del Map
                nombreMap.remove(K clave); // Borra el par clave/valor de la clave que se le pasa como parámetro
                nombreMap.containsKey(K clave); // Devuelve true si en el map hay una clave que coincide con K
                nombreMap.containsValue(V valor); // Devuelve true si en el map hay un Valor que coincide con V
                nombreMap.values(); // Devuelve una "Collection" con los valores del Map
            LinkedHashMap: +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                Esta implementación almacena las claves en función del orden de inserción. 
                Es, simplemente, un poco más costosa que HashMap.

        Ninguna de estas implementaciones son sincronizadas; es decir, no se garantiza 
        el estado del Map si dos o más hilos acceden de forma concurrente al mismo. Esto 
        se puede solucionar empleando una serie de métodos que actúan de wrapper para 
        dotar a estas colecciones de esta falta de sincronización:

            Java
            Map map = Collections.synchronizedMap(new HashMap());
            SortedMap mortedMap = Collections.synchronizedSortedMap(new TreeMap());
            Map map = Collections.synchronizedMap(new LinkedHashMap());

        Otro elemento importante a la hora de trabajar con los Maps (aunque no lo es 
        tanto como a la hora de trabajar con los ArrayList) son los "Iteradores" (Iterator). 
        Los Iteradores sirven para recorrer los Map y poder trabajar con ellos. Los Iteradores 
        solo tienen tres métodos que son el “hasNext()” para comprobar que siguen quedando 
        elementos en el iterador, el“next()”  para que nos de el siguiente elemento del 
        iterador; y el “remove()” que sirve para eliminar el elemento del Iterador. En 
        realidad se puede prescindir de los iteradores para trabajar con los Map ya que 
        la gran ventaja de los Map frente a los ArrayList, es que estos tienen una clave 
        asociada al objeto y se les puede buscar por la clave, aunque nunca esta de más 
        saber utilizar los iteradores para manejar los Map.

            // Imprimimos el Map con un Iterador
            Iterator it = map.keySet().iterator();
            while(it.hasNext()){
              Integer key = it.next();
              System.out.println("Clave: " + key + " -> Valor: " + map.get(key));
            }
    Ejemplo___________________________________________________________________________
        Se agrega registro a un array
        lista = new ArrayList<String>(Arrays.asList(arrayLinExp_1));
        lista.add(25,alicuota.toString());                  
        String[] arrayNew = new String[lista.size()];
        arrayNew = lista.toArray(arrayNew);
        lineaActual = StringUtils.join(arrayNew, ";");
***********************************************************************************************


**********************************************************Ordenal una lista con API Collections
    Collections.sort(lista, new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }           
    });
***********************************************************************************************


********************************************************Manejo de objetos con arrayLis y sesion

    ArrayList<Conversor> lista = session.getAttribute("listado") == null
            ? new ArrayList<Conversor>() : (ArrayList) session.getAttribute("listado");
***********************************************************************************************


*******************************************************Borrar objetos repetidos de un arrayList
    Set<String> hs = new HashSet<>();
    hs.addAll(miArrayList);
    miArrayList.clear();
    miArrayList.addAll(hs);
***********************************************************************************************


***********************************************************************Ordenar array de objetos

    for(int i=0; i<=hs.size()-1;i++){
        for (int j = i+1; j <= hs.size()-1 ; j++) {
            if (clausulasAll.get(j).getId() < clausulasAll.get(i).getId()){
                aux = clausulasAll.get(i);
                clausulasAll.set(i, clausulasAll.get(j));
                clausulasAll.set(j, aux);
            }
        } 
    }
***********************************************************************************************

****************************************************************************Copiar un arreglo[]
    función predefinida en la biblioteca de estándar de Java:
        System.arraycopy(from, fromIndex, to, toIndex, n);
        int []datos= new int[pares.length];
        System.arraycopy(pares, 0, datos, 0, pares.length);

***********************************************************************************************


*******************************************************************************Java.util.Arrays
    La biblioteca de clases de Java incluye una clase auxiliar llamada “java.util.Arrays”, que
    incluye como métodos algunas de las tareas que se realizan más a menudo con vectores:
        Nota: es necesario importar la biblioteca,
        import java.util.Arrays;
        ● Arrays.sort(v) ordena los elementos del
        vector.
        ● Arrays.equals(v1,v2) comprueba si dos
        vectores son iguales.
        ● Arrays.toString(v) devuelve una cadena
        que representa el contenido del vector.
***********************************************************************************************
Genericos

******************************************************************************Genericos en Java
    //Se crea clase de tipo generica
    public class Gen<T> {
        //Se declara objeto de tipo Gen
        T obj;
        
        //Constructor que pasa una variable de tipo T a obj
        Gen(T o){
            obj = o;
        }
        
        //El parametro G tambien se utiliza para especificar el tipo de retorno de un objeto
        T getOb(){
            return obj;
        }
        
        void mostrarTipo(){
            System.out.println("El tipo de va riable es: "+obj.getClass().getName());
        }
    }

    -----------------------------------------------------------------------------------------------
    Una clase genérica con dos parámetros de tipo
    Puede declarar más de un parámetro de tipo en un tipo genérico. Para especificar dos o más 
    parámetros de tipo, simplemente use una lista separada por comas. 

    -----------------------------------------------------------------------------------------------
    //Se crea variable generica de entero
            Gen<Integer> iObj;

    ----------------------------------------------------------------------------------------------

    Tipos Genericos(Convensiones)

    E			Elemento(utilizado generalmente por el framework de Collecciones de java
    K			Key (Utilizados en mapas)
    N			Number(utilizado para numeros)
    T			Type(representa un tipo, es decir, una clase)
    V			Value(representa un valor, tambien se usa en mapas)
    ----------------------------------------------------------------------------------------------
***********************************************************************************************


*******************************************************************************Lectura de bytes
    //Se creaarray de tipo de dato byte de solo 20 de longitud
        byte data[]= new byte[20];
        
        //Se solicitan datos 
        System.out.println("Ingrese cadena de caracteres: ");
        //Se almacena datos en el arreglo de bytes
        System.in.read(data);


    //Se recorre arrglo de bytes y se imprimen en consola
        for (int i = 0; i < data.length; i++) {
            System.out.print((char)data[i]);
        }
***********************************************************************************************


Fechas

*******************************************************************************Manejo de fechas
    Date ahora = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
            return formateador.format(ahora);

    ----------------------------------------------------------------------Manejo de Hora

        Date ahora = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
            return formateador.format(ahora);

    ------------------------------------------------------------sumar dias al calendario

        Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(fch.getTime());
            cal.add(Calendar.DATE, dias);
            return new java.sql.Date(cal.getTimeInMillis());

    ------------------------------------------------------------------------restar dias

    Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(fch.getTime());
            cal.add(Calendar.DATE, -dias);
            return new java.sql.Date(cal.getTimeInMillis());

    ------------------------------------------------------Diferencias entre dos fechas
        //@param fechaInicial La fecha de inicio
        //@param fechaFinal  La fecha de fin
        //@return Retorna el numero de dias entre dos fechas
        public static synchronized int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) {

            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            String fechaInicioString = df.format(fechaInicial);
            try {
                fechaInicial = df.parse(fechaInicioString);
            } catch (ParseException ex) {
            }

            String fechaFinalString = df.format(fechaFinal);
            try {
                fechaFinal = df.parse(fechaFinalString);
            } catch (ParseException ex) {
            }

            long fechaInicialMs = fechaInicial.getTime();
            long fechaFinalMs = fechaFinal.getTime();
            long diferencia = fechaFinalMs - fechaInicialMs;
            double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            return ((int) dias);
        }

    --------------------------   //Devuele un java.util.Date desde un String en formato dd-MM-yyyy
        //@param La fecha a convertir a formato date
        //@return Retorna la fecha en formato Date
        public static synchronized java.util.Date deStringToDate(String fecha) {
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
                Date fechaEnviar = null;
                try {
                    fechaEnviar = formatoDelTexto.parse(fecha);
                    return fechaEnviar;
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
        }

    ----------------------------------Devuelve un String desde un objeto tipo Date 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaComoCadena = sdf.format(new Date());
        System.out.println(fechaComoCadena);
***********************************************************************************************


********************************************************************Manejo de fechas para query
   Referencia --> JexlModelINS.java --> cuposOtorgadosPorContrato()
    Calendar diaDeHoy= Calendar.getInstance();
        GregorianCalendar gregorianHoy = new GregorianCalendar(diaDeHoy.get(Calendar.YEAR),diaDeHoy.get(Calendar.MONTH),diaDeHoy.get(Calendar.DATE),0,0,0);
        Date fechaHoy = gregorianHoy.getTime();
        
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("idContrato",idContrato);
        params.put("estado", 'O');
        params.put("fecha",fechaHoy);

        List<Long> listTotalCuposOtorgados = (List<Long>)BaseModel.getManager().executeNamedQuery(false, "cuposOtorgadosTotal", params);
***********************************************************************************************


**************************************************************************Comparacion de Fechas
    ***compareTo. La clase Date implementa Comparable<Date>; por lo tanto, puedes comparar dos 
    fechas directamente con el método compareTo. Si las fechas representan al mismo punto en 
    el tiempo, el método devolverá un cero. Si la fecha que vas a comparar es anterior al 
    argumento date, el método devolverá un valor menor a cero. Si la fecha que vas a comparar 
    es posterior al argumento date, el método devolverá un valor mayor a cero. 
        Ejemplo_______________________________________________________________________
            if((ctaLista == ctaListaAgrup) && (fecVenLista.compareTo(fecVenListaAgrup) == 0)){
                ...
            }

    ***Utiliza equals, after y before. Las fechas pueden compararse con los métodos equals (igual), 
    after (después) y before (antes). Si dos fechas representan al mismo punto en el tiempo, el 
    método equals devolverá true (verdadero)
        Ejemplo_______________________________________________________________________
            System.out.print(date1.before(date2)); //muestra true (verdadero)
            System.out.print(date2.before(date2)); //muestra false (falso)

    ***Utiliza la clase Calendar. 
        La clase Calendar también tiene los métodos compareTo, equals, after y before que 
    trabajan de la misma forma que se describió anteriormente para la clase date. Entonces, 
    si la información está guardada en un objeto Calendar, no hay necesidad de extraer la fecha 
    solo para hacer una comparación.
        Ejemplo_______________________________________________________________________
            Calendar cal1 = Calendar.getInstance(); //declara cal1
            Calendar cal2 = Calendar.getInstance(); //declara cal2
            Calendar cal3 = Calendar.getInstance(); //declara cal3
            cal1.setTime(date1); //applies date to cal1
            cal2.setTime(date2);
            cal3.setTime(date3);
            System.out.print(cal1.before(cal2)); //mostrará true (verdadero)
***********************************************************************************************


*************************************************************************Manejo de Fechas Habil
    /**
     * Dada una fecha, devuelve la fecha habil
     * 
     */
    public static Date getDiaHabil(Date date,Boolean previo) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
        while( diaSemana == Calendar.SATURDAY || diaSemana == Calendar.SUNDAY ) {
            cal.add(Calendar.DAY_OF_MONTH, previo ? -1 : 1);
            diaSemana = cal.get(Calendar.DAY_OF_WEEK);
        }
        return cal.getTime();
    }
    --------------------------------------------------------------------------------------
        /**
     * Se agrego este metodo proque el getDiaHabil con previo = true, no tiene en cuenta si no es Sabado o Domingo.
     * En este se quiere siempre el dia habil anterior
     * @param date: Fecha actual
     * @param previo : Para definir que sea anterior
     * @return devuelve el dia habil anterior al date
     */
    public static Date getDiaHabilPrevio(Date date,Boolean previo) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
        if(diaSemana == Calendar.SATURDAY || diaSemana == Calendar.SUNDAY){
            while( diaSemana == Calendar.SATURDAY || diaSemana == Calendar.SUNDAY ) {
                cal.add(Calendar.DAY_OF_MONTH, previo ? -1 : 1);
                diaSemana = cal.get(Calendar.DAY_OF_WEEK);
            }
        }else{
            if(diaSemana == Calendar.MONDAY)
                cal.add(Calendar.DAY_OF_MONTH, -3);
            else
                cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        return cal.getTime();
    }
***********************************************************************************************


*****************************************************************************Formateo de fechas
    GregorianCalendar c = new GregorianCalendar();
    String fechaGeneracion = String.format("%02d%02d%4d", c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH)+1, c.get(Calendar.YEAR));
***********************************************************************************************


Excepciones

************************************************************************************Excepciones

    public class AccesoDatosEx extends Exception{
        public AccesoDatosEx(String mensaje){
            super(mensaje);
        }
    }
    ----------------------------------------------------------------------------------------------
    public class EscrituraDatosEx extends AccesoDatosEx{
        public EscrituraDatosEx(String mensaje){
            super(mensaje);
        }
    }

    ----------------------------------------------------------------------------------------------
    package manejoExcepciones2;
    import datos.*;
    import excepciones.*;
    /**
     *
     * @author Banesco
     */
    public class ManejoExcepciones2 {
        public static void main(String[] args) {
            AccesoDatos datos = new ImplementacionMySql();
            // Se cambia el estado del simulador
            datos.simularError(true);
            ejecutar(datos,"listar");
            
            //Se cambia el estado del simulador a false
            datos.simularError(false);
            System.out.println("");
            ejecutar(datos,"insertar");
        }
        
        private static void ejecutar(AccesoDatos datos, String accion){
            if(accion.equals("listar")){
                try{
                    datos.listar();
                }catch (LecturaDatosEx ex){
                    System.out.println("Error de lectura.");
                }catch (AccesoDatosEx ex){
                    System.out.println("Procesa la excepcion mas generica.");
                }finally{
                    System.out.println("Es opcional, se utiliza generalmente para el cierre de conexiones");                        
                }              
            }else if(accion.equals("insertar")){
                try{
                    datos.insertar();
                }catch (AccesoDatosEx ex){
                    System.out.println("Procesa excepcion mas generica.");
                }finally{
                    System.out.println("Es opcional, se utiliza generalmente para el cierre de conexiones");                        
                } 
            }else{
                System.out.println("No se proporciono ninguna accion conocida.");
            }
        }
    }
***********************************************************************************************

Manejo de NullPointerException
**********************************************************************ObjectUtils.defaultIfNull
    Devuelve el dto si no es null
        dto = (ConsultaDocumentoDTO) ObjectUtils.defaultIfNull(dto, new ConsultaDocumentoDTO());
***********************************************************************************************

Vista

**********************************************************************redireccionar pagina html
    <meta http-equiv="refresh" content="0; url=http://example.com/" />
    ***********************************************************************************************
    *************************************************************************//Redireciona a Pagina 
            response.sendRedirect("cart.jsp");
    -----------------------------------------------------------------------------------------------
    response.sendRedirect(request.getContextPath()+"/RegisProd.jsp?mensaje="+mensaje);
    ---------------------------------------------------------------------------request.getDispacher
    request.getRequestDispatcher("destinoBean.jsp").forward(request, response);
    -----------------------------------------------------------------------------------------------
    //4. Redireccionamos 
    RequestDispatcher rd=request.getRequestDispatcher("vista/desplegarVariables.jsp");
    //Se propagan los objetos requesty response
    //para que puedan ser utilizados por el JSP seleccionado
    rd.forward(request, response);
    //Ya no se necesita hacer nada mas despues del
    //redireccionamiento, ya que el flujo continua
    //con el JSP}
***********************************************************************************************


Java Util

*************************************************************************************Multihilos
    Tabla de métodos de la clase Thread.
    Método                      Significado
    final String getName()              Obtiene el nombre de un hilo.
    final int getPriority               Obtiene la prioridad de un hilo.
    final boolean isAlive()             Determina si un hilo todavía se está ejecutando.
    final void join()               Espera a que termine un hilo.
    void run()                  Punto de entrada para el hilo.
    static void sleep(long milisegundos)        Suspende un hilo durante un período específico de milisegundos.
    void start()                    Inicia un hilo llamando a su método run().


        //Se instancia clase que implementa runnable
            MiHilo mh = new MiHilo("#1");
            
        //Se crea hilo con la clase que implementa runnable
            Thread nuevoh = new Thread(mh);
            
        //Se comienza el hilo
            nuevoh.start();

        //Pausa el hilo
        Thread.sleep(100);


    ***En el caso de utilizar la clase Thread, se crea la clase extendiendo de la clase Thread y 
    en la clase que invoca al hilo 

        //Se instancia clase que implementa runnable
            MiHilo mh = new MiHilo("#1");
        
        //Se comienza el hilo
        mh.start();
***********************************************************************************************


*********************************************************Eliminar comillas dobles de una cadena
        
    theText.replaceAll("\"", ""); 
***********************************************************************************************


**********************Validar sin la secuencia de una cadena comienza con un determinado patron
    if(!padron.startsWith("__") && !padron.startsWith(".") && !padron.startsWith("RUCA")){

    }
***********************************************************************************************


******************************************************************Clase Propiedades del Sistema
    public class PropiedasSistema {
        
        public static void main(String[] args) {
            //Definimos variable de propiedades
            Properties propiedades = System.getProperties();
            //Obtener cada una de las propiedades y las ponemos en Enumeration
            Enumeration nombrePropiedades = propiedades.propertyNames();
            //por medio de la enumeracion y de un while verificamos si tiene mas elementos
            while(nombrePropiedades.hasMoreElements()){
                //Obtenemos el nombre de la propiedad
                String nombreProperty = (String) nombrePropiedades.nextElement();
                //Obtenemos el valos de la propiedad
                String valorProperty = propiedades.getProperty(nombreProperty);
                //Imprimir
                System.out.println("Propiedad: "+nombreProperty+" = "+valorProperty);
            }
            
        }
        
    }
***********************************************************************************************


*************************************************metodo para la carga de archivo de propiedades
    archivo de propiedades para la conexion a Base de datos

    public static Properties loadProperties(String file){
        Properties prop=new Properties();
        ResourceBundle bundle=ResourceBundle.getBundle(file);
        Enumeration e =bundle.getKeys();
        String key=null;
        while(e.hasMoreElements()){
          key=(String) e.nextElement();
          prop.put(key, bundle.getObject(key));
        }
        JDBC_DRIVER=prop.getProperty("driver");
        JDBC_URL=prop.getProperty("url");
        JDBC_USER=prop.getProperty("user");
        JDBC_PASS=prop.getProperty("pass");
        return prop;
    }
***********************************************************************************************


Anotaciones

************************************************************************************Definicion
    Una anotación en Java es aquella característica que le permite incrustar información 
    suplementaria en un archivo fuente. Esta información no cambia las acciones de un programa, 
    pero puede ser utilizada por varias herramientas, tanto durante el desarrollo como durante 
    el despliegue.

    Una anotación en Java puede ser procesada por un generador de código fuente, por el compilador 
    o por una herramienta de despliegue. En ocasiones, las anotaciones en Java también son llamadas 
    metadata, pero el término anotación es el más descriptivo y más utilizado.

    CARACTERÍSTICAS GENERALES DE LAS ANOTACIONES EN JAVA
        *Las anotaciones de Java comienzan con ‘@’.
        *Las anotaciones Java no cambian la actividad de un programa ordenado.
        *Ayudan a relacionar metadatos (datos) con los componentes del programa, es decir, 
        constructores, estrategias, clases, etc.
        *Las anotaciones en Java no son comentarios sin adulteraciones, ya que pueden cambiar 
        la forma en que el compilador trata el programa.
***********************************************************************************************


*****************************************************Crear una anotación y procesarla con Java
    1.- Definir la anotación con las propiedades que dispone.
        //@Target nos indica en que lugares se puede aplicar esta anotación. 
        //En nuestro caso tanto en clases como en propiedades (Type,Field)
        @Target({ElementType.FIELD, ElementType.TYPE})
        //La anotacion Retention  valora si la anotación se chequea en tiempo de ejecucion
        @Retention(RetentionPolicy.RUNTIME)
        //usamos @interface  y le asignamos un nombre
        public @interface Imprimible {
            //Se añade propiedades a la anotación por si permite parametrizaciones.
            //En nuestro caso la anotación permite un parámetro de mayusculas true/false
            boolean mayusculas() default false;
         
        }
    2.- Aplica la anotación a una clase normal como es la clase Libro.
        //Nuestra anotación nos informa si tenemos que procesar los objetos e 
        //imprimir la información en mayusculas o minúsculas por pantalla
        public class Libro {
            @Imprimible(mayusculas=false)
             String titulo;
            @Imprimible(mayusculas=true)
             String autor;
             
            public Libro(String titulo, String autor) {
                super();
                this.titulo = titulo;
                this.autor = autor;
            }
            public String getTitulo() {
                return titulo;
            }
            public void setTitulo(String titulo) {
                this.titulo = titulo;
            }
            public String getAutor() {
                return autor;
            }
            public void setAutor(String autor) {
                this.autor = autor;
            }
             
        }
    3.- Construir un programa Java que sea capaz de procesar las anotaciones. 
    Para ello haremos uso del API de Reflection
        public class Principal {
 
            public static void main(String[] args) {
         
                List<Object> lista = new ArrayList<Object>();
                lista.add(new Libro("titulo1", "pedro"));
                procesar(lista);
         
            }
         
            public static void procesar(List<Object> lista) {
         
                try {
                    //Recorremos la lista de Objetos
                    for (Object o : lista) {
                        //Por cada Objeto leemos las propiedades (campos) que tiene “getDeclaredFields()”
                        Field[] campos = o.getClass().getDeclaredFields();
                         
                        for (Field campo : campos) {
                            //Se comprueba si el campo dispone de la anotaccion Imprimible getAnnotation(Imprimible.class)
                            Imprimible imprimir = campo.getAnnotation(Imprimible.class);
                            // System.out.println(imprimir);
                            if (imprimir != null) {
                                //Si esa anotacion existe , comprobamos si la propiedad mayuscula esta a true o false
                                if (imprimir.mayusculas()) {
                                    System.out.println(campo.get(o).toString().toUpperCase());
                                } else {
                                    System.out.println(campo.get(o).toString());
         
                                }
                            }
                        }
                    }
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
**********************************************************************************************


ManejoArchivos

***************************************************************Ver si un archivo esta bloqueado
    ***Referencia  -->ClienteSituacionGananciasAction.java  --> Zeni
    private Boolean isLocked(String path){
        String lockFileName = path + "/Padrones/__lock_access__";
        File file = new File(lockFileName);
        if(file.exists()){
            return true;
        }
        return false;
    }
***********************************************************************************************


************************************************************Filtrar un archivo por la extension
    referencia --> https://stackoverflow.com/questions/5603966/how-to-make-filefilter-in-java
    String yourPath = "insert here your path..";
    File directory = new File(yourPath);
    String[] myFiles = directory.list(new FilenameFilter() {
        public boolean accept(File directory, String fileName) {
            return fileName.endsWith(".txt");
        }
    });
************************************************************************************************


*****************************Obtener archivos desde un path con o sin estension y por un patron
    Referencia --> FileUtils.java --> Zeni

    List<String> files = FileUtils.getFilesAbsolute(path, "*.txt", false);

    List<String> padrones = FileUtils.getFilesAbsolute(path, "__*", false);

    public static List<String> getFilesAbsolute(String path, String wildcard, boolean omitExtension) {
            List<String> res = new ArrayList<String>();
            File dir = new File(path);
            FileFilter fileFilter = new WildcardFileFilter(wildcard);
            File[] files = dir.listFiles(fileFilter);
            if(files != null){
                for (int i = 0; i < files.length; i++) {
                    String fileName = files[i].getName();     
************************************************************************************************


***********************************************************Metodo filtra archicvos por estension
    Referencia -->FileUtils.java --> Zeni
    public static List<String> getFilesAbsolute(String path, String wildcard, boolean omitExtension) {
        List<String> res = new ArrayList<String>();
        File dir = new File(path);
        FileFilter fileFilter = new WildcardFileFilter(wildcard);
        File[] files = dir.listFiles(fileFilter);
        if(files != null){
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if(omitExtension)
                    fileName = fileName.substring(0, fileName.lastIndexOf('.'));
                res.add(fileName);
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("Se encontraron los siguientes archivos disponibles: " + path + "/" + wildcard + "==>" + res.toString());
        }
        return res;
    } 
************************************************************************************************


***************************************Trabajar con archivo txt separados por coma con csvReader
    Referencia --> ImportarPadronesImpuestos.java  -->Zeni
    private void procesaUnNOMINA4212(String fileName){
        UniversalManager manager = BaseModel.getManager();
        ArrayList<BaseModel> buffer = new ArrayList<BaseModel>(PadronInfo.SAVE_BUFFER_SIZE);
        logMsg("IMPORTA: NOMINA 42-12 '" + fileName + "'");
        manager.bulkUpdate("UPDATE Cliente c SET c.alicuotaInformada=0.0075");
        manager.flush(false);
        try{
            long importados = 0;
            CsvReader csvR = new CsvReader(fileName);
            csvR.setDelimiter(';');
            csvR.setUseTextQualifier(true);
            csvR.skipRecord();
            csvR.readRecord();  // Saltea la fila de headers
            csvR.setSafetySwitch(false);
            do{
                String[] registro = csvR.getValues();
                String cuit = Util.cuitFormater(registro[0]);
                Object[] cliente = CliInfoBuffer_Get(cuit);
                if(cliente != null){    // Solo interesan los registros que hablan de clientes registrados en el sistema
                    System.out.println("cuit: " + cuit + ", importados: " + importados);
                    Integer codigo = Integer.parseInt(registro[1]);
                    Double alicuotaInformada = 0.04D;
                    switch (codigo) {
                        case 1:
                            alicuotaInformada = 0D;
                        break;
                        case 2:
                            alicuotaInformada =  0D;
                        break;
                        default:
                            alicuotaInformada = 0.0075;
                        break;
                    }
            manager.bulkUpdate("UPDATE Cliente c " +
                           "   SET c.alicuotaInformada="+alicuotaInformada +
                           " WHERE c.id="+cliente[0]);
                manager.flush(false);
                
                importados++;
                }
            } while(csvR.readRecord());
            bufferingSave(manager, buffer, null);   // Flush
            
            Long count = csvR.getCurrentRecord() + 1;
            logMsg("\tLeyo " + count + " registros, importo " + importados + " registros clientes");
            csvR.close();
        } catch(IOException e){
            error("Fallo la importación");
            e.printStackTrace();
        }
    }
************************************************************************************************


*********************************************************************Manejo de archivos en Java
    //Se crea objeto de tipo archivo
            File arch = new File("D:/EstadoCta.txt");
            FileInputStream fis = null;

    //Se abre archivo
                fis = new FileInputStream(arch);

     //Se lee caracter en el archivo y se almacena en variable
                    caracter = fis.read();

    //Se imprime si es diferente a -1
                    if(caracter!=-1){
                        System.out.print((char)caracter);
                    }

     //Se cierra archivo
                    fis.close();

    ----------------------------------------------------------------------------Creacion de archivo                
    public static void crearArchivo(String nombreArchivo) {
            File archivo = new File(nombreArchivo);
            try {
                PrintWriter salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("El archivo se ha creado correctamente\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        **************************************************************************************
                try {
                    String ruta = "/ruta/filename.txt";
                    String contenido = "Contenido de ejemplo";
                    File file = new File(ruta);
                    // Si el archivo no existe es creado
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(contenido);
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            

    ----------------------------------------------------------------------------Escribir en archivo

        public static void escribirArchivo(String nombreArchivo) {
            File archivo = new File(nombreArchivo);
            try {
                PrintWriter salida = new PrintWriter(new FileWriter(archivo));
                String contenido = "Contenido a escribir en el archivo";
                salida.println(contenido);
                salida.println();
                salida.println("Fin de escritura");
                salida.close();
                System.out.println("Se ha escrito correctamente al archivo\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    -------------------------------------------------------------------------------Leer en archivo
        public static void leerArchivo(String nombreArchivo) {
            File archivo = new File(nombreArchivo);
            try {
                BufferedReader entrada = new BufferedReader(new FileReader(archivo));
                String lectura;
                lectura = entrada.readLine();
                while (lectura != null) {
                    System.out.println("Lectura: " + lectura);
                    lectura = entrada.readLine();
                }
                entrada.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    ------------------------------------------------------------------agregar ecritura a un archivo
        public static void anexarArchivo(String nombreArchivo) {
            File archivo = new File(nombreArchivo);
            try {
                PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
                String contenido = "Anexando informacion al archivo";
                salida.println(contenido);
                salida.println();
                salida.println("Fin de anexar");
                salida.close();
                System.out.println("Se ha anexado la informacion correctamente\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    ------------------------------------------------------------------Manejo de Archivo en zeni
        @Action("validarUsuario")
        @Override
        public String execute() throws Exception    {
            mensaje = "Bienvenido " + nombre + " al munddo de Struts 2. Tu número de la suerte de hoy es " + numero;
            LoginAction la = new LoginAction();
            //vector que va a contener las lineas del texto
            List<String> lista = new ArrayList<String>();
            String fichero="F:\\OPERSEC-3.dat"; //ruta del fichero
            int l=la.leer(fichero,lista); //metodo que lee el archivo y guarda las lineas de texto en el vector
            la.imprimir(l,lista); //metodo que imprime el vector por lineas
            return SUCCESS;
        }   
        
      //metodo para leer texto
        public int leer(String ruta, List lista){
            int i=0;
            try {
                FileReader fr = new FileReader(ruta);
                BufferedReader bf = new BufferedReader(fr);
                
                String cadena;
                
                while ((cadena = bf.readLine())!=null) {
                    lista.add(cadena); //aqui le asigno cada linea al vector
                    i++;
                }
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            } catch (IOException ioe){
                ioe.printStackTrace();
            }
            return i;//retorno la variable "i" para saber cuantas lineas se guardaron en el vector
        }
        
        public void imprimir(int pk, List lista){
            for(int g=0; g<pk; g++){
                System.out.println(lista.get(g));
                String p = (String)lista.get(g);
                String[] pa = p.split("\"");
                for(String pp : pa){
                    System.out.println("Cadena "+g+ " elemento "+pp);
                }
            }
        }
************************************************************************************************


******************************************************************************Listar Directorio
    Mediante la clase File podremos listar de forma sencilla el contenido de un directorio. 
    Lo primero que tendremos que hacer es crear un objeto de tipo File con el nombre del
    directorio a a listar.

 
        String sDirectorio = "c:\\datos";
        File f = new File(sDirectorio);
         

    Utilizaremos el método .exists() para comprobar que el directorio existe.

         
        if (f.exists()){ // Directorio existe }
        else { //Directorio no existe }
         

    Una vez pasada esta validación utilizamos el método .listFiles. Este método devuelve 
    todos los ficheros asociados al directorio en un array de Files. Es por ello que solo 
    nos quedará recorrer el array e ir mostrando el nombre de lo ficheros y directorios que 
    componen nuestro directorio.

         
        File[] ficheros = f.listFiles();
        for (int x=0;x<ficheros.length;x++){
          System.out.println(ficheros[x].getName());
        }

    Para obtener el nombre del fichero o directorio utilizamos el método .getName().
***********************************************************************************************


Excel

****************************************Envio Archivo Excel desde un jsp indicando en Directiva
    <%@taglib prefix="s" uri="/struts-tags"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@page contentType="application/vnd.ms-excel; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" %><!--Se indica el tipo de contenido a enviar-->
    <%@page import="java.util.Date" %>
    <%
        String nombreArchivo = "prueba.xls";
        //Se indica el nombre del archivo
        response.setHeader("Content-Disposition", "inline; filename="+nombreArchivo);
    %>
    <html>
    <head>
        <title>Reporte Excel</title>
    </head>
        <table border="1">
            <tr>
                <th>Curso</th>
                <th>Descripción</th>
                <th>Fecha Inicio</th>
                </tr>
            <tr>
                <td>1. Fudamentosde Java</td>
                <td>Aprenderemos la sintaxis básica de Java</td>
                <td><%=  new Date()  %></td>
            </tr>
            <tr>
                <td>2. Programación con Java</td>
                <td>Pondremos en práctica conceptos dela Programación Orientada a Objetos</td>
                <td><%= new Date() %></td>
            </tr>
        </table>
    </body>
***********************************************************************************************


*******************************************************************************Clase Leer excel
    import java.io.File;    
    import java.io.FileInputStream; 
    import java.io.IOException; 
    import java.util.Iterator;
    import java.io.InputStream;
    import org.apache.poi.ss.usermodel.Cell;    
    import org.apache.poi.ss.usermodel.Row; 
    import org.apache.poi.xssf.usermodel.XSSFSheet; 
    import org.apache.poi.xssf.usermodel.XSSFWorkbook;
    import org.apache.poi.ss.usermodel.DateUtil;
    /**
     *
     * @author eaareiza
     */
    public class LeerExcel {

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) throws IOException{
            FileInputStream file = new FileInputStream(new File("D:\\Prueba_excel.xlsx"));
            // Crear el objeto que tendra el libro de Excel
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            
            /*
         * Obtenemos la primera pestaña a la que se quiera procesar indicando el indice.
         * Una vez obtenida la hoja excel con las filas que se quieren leer obtenemos el iterator   
         * que nos permite recorrer cada una de las filas que contiene.
         */
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();   
        
        Row row;
            
        // Recorremos todas las filas para mostrar el contenido de cada celda
        while (rowIterator.hasNext()){  
            row = rowIterator.next();
                
            // Obtenemos el iterator que permite recorres todas las celdas de una fila
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell celda;
            while (cellIterator.hasNext()){
                celda = cellIterator.next();
                    
                // Dependiendo del formato de la celda el valor se debe mostrar como String, Fecha, boolean, entero...
                switch(celda.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        if( DateUtil.isCellDateFormatted(celda) ){
                            System.out.println(celda.getDateCellValue());
                        }else{
                            System.out.println(celda.getNumericCellValue());
                        }
                    break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.println(celda.getStringCellValue());
                    break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.println(celda.getBooleanCellValue());
                    break;
                }
            }
        }
        // cerramos el libro excel
        //workbook.close();
        }
        
    }

    -----------------------------------------------------------------------------------------------
    public class readExcel {
        static XSSFRow fila;
        public static void main(String[] args) throws IOException{
            
            FileInputStream fIS = new FileInputStream(new File("D:/prueba_excel.xlsx"));
            //Creacion de libro de trabajo
            XSSFWorkbook libro = new XSSFWorkbook(fIS);
            //Tomo la hoja de trabajo
            XSSFSheet hoja = libro.getSheetAt(0);
            //Creo un Iterator para trabajar con las filas de las hojas
            Iterator<Row> filaIterator = hoja.iterator();
            //Creo ciclo hasta que no haya elementos en filaIterator
            while(filaIterator.hasNext()){
                //Tomo la fila
                fila = (XSSFRow) filaIterator.next();
                //Creo un Iterator para trabajar con las celdas
                Iterator<Cell> celdaIterator = fila.cellIterator();
                //Creo ciclo hasta que no haya celdas en fila
                while(celdaIterator.hasNext()){
                    //Tomo la celda
                    Cell celda = (XSSFCell) celdaIterator.next();
                    //Realizo un case para identificar el tipo de dato
                    switch (celda.getCellType()){
                        //Caso tipo de dato String
                        case Cell.CELL_TYPE_STRING:
                            System.out.println(celda.getStringCellValue()+"\t\t");
                            break;
                        //Caso de tipo de dato numerico
                        case Cell.CELL_TYPE_NUMERIC:
                            //Evaluo si es fecha o numero con objeto DateUtil
                            if( DateUtil.isCellDateFormatted(celda) ){
                                System.out.println(celda.getDateCellValue());
                            }else{
                                System.out.println(celda.getNumericCellValue());
                            }
                            break;
                    }
                }
                System.out.println();
            }
            fIS.close();
        }
    }
***********************************************************************************************


******************************************************************************Escribir en excel
    import java.io.File;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.util.Map;
    import java.util.Set;
    import java.util.TreeMap;
    import org.apache.poi.ss.usermodel.Cell;
    import org.apache.poi.xssf.usermodel.XSSFChartSheet;
    import org.apache.poi.xssf.usermodel.XSSFRow;
    import org.apache.poi.xssf.usermodel.XSSFSheet;
    import org.apache.poi.xssf.usermodel.XSSFWorkbook;

    /**
     *
     * @author WIN10
     */
    public class WriteHojaCalculo {

        public static void main(String[] args) throws IOException {
            //Creo libro de trabajo
            XSSFWorkbook libro = new XSSFWorkbook();

            //Creo la hoja de trabajo
            XSSFSheet hoja = libro.createSheet("Pagina1");

            //Creacion de fila
            XSSFRow fila;

            //Se crea la data necesaria para escribir
            Map<String, Object[]> inf = new TreeMap<String, Object[]>();
            inf.put("1", new Object[]{"EMP ID", "EMP NAME", "Descripcion"});
            inf.put("2", new Object[]{"01", "Elvis", "Gerente"});
            inf.put("3", new Object[]{"02", "Diego", "Gerente"});
            inf.put("4", new Object[]{"03", "Ricardo", "Gerente"});
            inf.put("5", new Object[]{"04", "Antonio", "Gerente"});
            inf.put("6", new Object[]{"05", "Carolina", "Gerente"});

            //Iterar sobre los datos
            Set<String> keyId = inf.keySet();
            int idFila = 0;

            for (String key : keyId) {
                fila = hoja.createRow(idFila++);
                Object[] objeto = inf.get(key);
                int idCell = 0;

                for (Object obj : objeto) {
                    Cell celda = fila.createCell(idCell++);
                    celda.setCellValue((String) obj);
                }
            }

            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("c:/Users/WIN10/Desktop/Elvis/CreateWorkBooK1.xlsx"));
            libro.write(out);
            out.close();
            System.out.println("Writesheet.xlsx written successfully");

        }
    }
***********************************************************************************************


*******************************************************************************Lectura de excel
    File archivo = new File("c:/Users/WIN10/Desktop/Elvis/CreateWorkBooK.xlsx");
    FileInputStream entrada = new FileInputStream(archivo);

    ---------------------------//Obtenemos el libro de trabajo
    XSSFWorkbook libro = new XSSFWorkbook(entrada);

    ---------------------------//Se obtiene la hoja
    XSSFSheet hoja = libro.createSheet("HojaEstCell");

    ---------------------------//Se obtiene la fila
    XSSFRow fila = hoja.createRow(1);

    ---------------------------////Establezca la altura en "twips" o 1/20 de un punto.
            fila.setHeight((short)800);

    --------------------------//creo la celda 
            XSSFCell celda = (XSSFCell)fila.createCell((short)1);

    --------------------------//Valor de la celda
    celda.setCellValue("Prueba de margen");


    -------------------------//añade margen a la celda
            hoja.addMergedRegion(new CellRangeAddress(
                    1, //primera fila
                    1, //Fila siguiente
                    1, //Primera columna
                    2)); //Columna siguiente
            
    ------------------------//CELL Alignment
            fila = hoja.createRow(5);
            celda = (XSSFCell) fila.createCell(0);
            fila.setHeight((short)800);
            
    ------------------------- // Top Left alignment
            //Crear objeto de estilo para celda en el libro
            XSSFCellStyle style1 = libro.createCellStyle();
    --------------------------//Indica ancho de la celda
            hoja.setColumnWidth(0, 8000);
    --------------------------//Indica alineacion horizontal de la celda
            style1.setAlignment(XSSFCellStyle.ALIGN_LEFT);
    --------------------------//Indica alineacion vertical de la celda de la celda
            style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
            celda.setCellValue("Top Left");
    -------------------------//Se le setea estilo a la celda
            celda.setCellStyle(style1);
    -------------------------//Se toma una nueva celda
            fila = hoja.createRow(6); 
            celda = (XSSFCell) fila.createCell(1);
            fila.setHeight((short) 800);
          
    -------------------------// Center Align Cell Contents 
            XSSFCellStyle style2 = libro.createCellStyle();
            style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            celda.setCellValue("Center Aligned"); 
            celda.setCellStyle(style2);
            fila = hoja.createRow(7); 
            celda = (XSSFCell) fila.createCell(2);
            fila.setHeight((short) 800);
          
    --------------------------// Bottom Right alignment 
            XSSFCellStyle style3 = libro.createCellStyle();
            style3.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
            style3.setVerticalAlignment(XSSFCellStyle.VERTICAL_BOTTOM);
            celda.setCellValue("Bottom Right");
            celda.setCellStyle(style3);
            fila = hoja.createRow(8);
            celda = (XSSFCell) fila.createCell(3);
          
    -------------------------// Alineacion Justa
          XSSFCellStyle style4 = libro.createCellStyle();
          style4.setAlignment(XSSFCellStyle.ALIGN_JUSTIFY);
          style4.setVerticalAlignment(XSSFCellStyle.VERTICAL_JUSTIFY);
          celda.setCellValue("Contents are Justified in Alignment"); 
          celda.setCellStyle(style4);
          
    ------------------------//CELL BORDER
          fila = hoja.createRow((short) 10);
          fila.setHeight((short) 800);
          celda = (XSSFCell) fila.createCell((short) 1);
          celda.setCellValue("BORDER");
          
          XSSFCellStyle style5 = libro.createCellStyle();
    ------------------------//Creo bordes Abajo
          style5.setBorderBottom(XSSFCellStyle.BORDER_THICK);
    ------------------------//Creo color del borde de abajo
          style5.setBottomBorderColor(IndexedColors.BLUE.getIndex());
    ------------------------//Creo borde Izquierdo
          style5.setBorderLeft(XSSFCellStyle.BORDER_DOUBLE);
    -----------------------//Creo color de borde izquierdo
          style5.setLeftBorderColor(IndexedColors.GREEN.getIndex());
    -----------------------//Creo borde derecho
          style5.setBorderRight(XSSFCellStyle.BORDER_HAIR);
    -----------------------//Creo color del borde derecho
          style5.setRightBorderColor(IndexedColors.RED.getIndex());
    -----------------------//Cre el borde de arriba
          style5.setBorderTop(XSSFCellStyle.BIG_SPOTS);
    -----------------------//Creo el color del borde de arriva
          style5.setTopBorderColor(IndexedColors.CORAL.getIndex());
    -----------------------//Aplico estilo en celda
          celda.setCellStyle(style5);
          
    ----------------------//Color de relleno
          //background color
          fila = hoja.createRow((short) 11 );
          celda = (XSSFCell) fila.createCell((short) 8);
          
          XSSFCellStyle style6 = libro.createCellStyle();
          //Se añade objeto color
          XSSFColor color = new XSSFColor(Color.BLUE);
          //Setea color de relleno
          style6.setFillBackgroundColor(color);
          style6.setFillPattern(FillPatternType.LESS_DOTS);
          style6.setAlignment(XSSFCellStyle.ALIGN_FILL);
          hoja.setColumnWidth(1,8000);
          celda.setCellValue("FILL BACKGROUNG/FILL PATTERN");
          celda.setCellStyle(style6);
          
          //Foreground color
          fila = hoja.createRow((short) 12);
          celda = (XSSFCell) fila.createCell((short) 1);
          
          XSSFCellStyle style7 = libro.createCellStyle();
          style7.setFillForegroundColor(HSSFColor.BLUE.index);
          style7.setFillPattern( XSSFCellStyle.LESS_DOTS);
          style7.setAlignment(XSSFCellStyle.ALIGN_FILL);
          celda.setCellValue("FILL FOREGROUND/FILL PATTERN");
          celda.setCellStyle(style7);

          FileOutputStream out = new FileOutputStream(new File("celdastyle.xlsx"));
          libro.write(out);
          out.close();
          System.out.println("celdastyle.xlsx written successfully");
***********************************************************************************************


*********************************************************Modificar formato de una celda a texto
    DataFormat fmt = wb.createDataFormat();
    CellStyle cellStyle = wb.createCellStyle();
    cellStyle.setDataFormat(
    fmt.getFormat("@"));
    cell.setCellStyle(cellStyle);
***********************************************************************************************


*************************************Metodos de Creacion y Descarga de Archivo Excel en Cliente
    //Metodo que realiza a descarga del archivo en el cliente
    private void descargaArchivo() throws IOException, Exception {
        HttpServletResponse response = getResponse();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=CartaPorteYPF.xls");
        //Creo libro de trabajo
        HSSFWorkbook libro = new HSSFWorkbook();
        List<String[]> errores= validaDatosb(genDatosYPF());
        //Validacion, se genera hoja de error unicamente si lista de eroores no esta vacia
        if(!errores.isEmpty()){
            generarArcIPF(libro, genHeadersErroresYPF(), errores, "ErrorYPF");
        }        
        generarArcIPF(libro, genTitulosYPF(), arregloDatos(listaArregloObjeto), "CCPP");
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
***********************************************************************************************


*********************************************Buscar una plantilla o archivo dentro del proyecto
    String fichero = "src/main/resources/Calidad.xls";
    FileInputStream file = new FileInputStream(new File(fichero));
    // Crear el objeto que tendra el libro de Excel
    HSSFWorkbook libro = new HSSFWorkbook(file);
    generarArcIPF(libro, genTitulosCalidad(), genAnalisisCalidad(arregloDatosCalidad(genDatosYPFCalidad())), "Calidad");
    libro.write(response.getOutputStream());
***********************************************************************************************



Java 8

**************************************************************************@FunctionalInterface
    @FunctionalInterface La anotación @FunctionalInterface se utiliza para garantizar que la 
    interfaz funcional no pueda tener más de un método abstracto. En caso de que haya más de 
    un método abstracto, el compilador marca el mensaje 'Anotación inesperada de @FunctionalInterface'. 
    Sin embargo, no es obligatorio usar esta anotación.
**********************************************************************************************


*****************************************************************************Expresiones Lambda
    Una expresión lambda se caracteriza por la siguiente sintaxis.

        parameter -> expression body
    
    Las siguientes son las características importantes de una expresión lambda.

        Declaración de tipo opcional : no es necesario declarar el tipo de un parámetro. 
        El compilador puede deducir lo mismo del valor del parámetro.

        Paréntesis opcional alrededor del parámetro : no es necesario declarar un solo parámetro 
        entre paréntesis. Para múltiples parámetros, se requieren paréntesis.

        Opcional llaves : no es necesario usar llaves en el cuerpo de la expresión si el cuerpo 
        contiene una sola declaración.

        Palabra clave opcional return : el compilador devuelve automáticamente el valor si el 
        cuerpo tiene una sola expresión para devolver el valor. Se requieren llaves para indicar 
        que la expresión devuelve un valor.

    Ejemplo____________________________________________________________________________

        public void ordenar() {
        List<String> lista = new ArrayList<>();
        lista.add("Elvis");
        lista.add("Ricardo");
        lista.add("Diego");
        lista.add("Camila");
        lista.add("Antonio");
        
        
                //Java 7
        //      Collections.sort(lista, new Comparator<String>() {
        //          @Override
        //          public int compare(String o1, String o2) {
        //              return o1.compareTo(o2);
        //          }           
        //      });
                //Java 8
                
            Collections.sort(lista,  (String o1, String o2) -> o1.compareTo(o2));
            
            for (String string : lista) {
                System.out.println(string);
            }
        }
        -----------------------------------------------------------------------------
                public void calcular() {
                //Java 7
        //      IOperacion oper = new IOperacion() {
                    

        //      @Override
        //      public double calcularProm(double n1, double n2) {
        //              return n1 * n2 / 2;
        //          }
        //      };
                    
                //java 8 lambda     
                IOperacion oper = (double n1, double n2) -> (n1+n2)/2;
                System.out.println(oper.calcularProm(2, 6));
            }
    ***Otro tipo de sintaxis
            public void calcular() {
        //Java 7
        //      IOperacion oper = new IOperacion() {
                    

        //      @Override
        //      public double calcularProm(double n1, double n2) {
        //              return n1 * n2 / 2;
        //          }
        //      };
                    
                //java 8 lambda     
                //IOperacion oper = (double n1, double n2) -> (n1+n2)/2;
                
                //Otro tipo de sintaxis
                IOperacion oper = (double n1, double n2) -> {
                    double a = n1;
                    double b = n2;
                    return (a+b)/2;
                };
                System.out.println(oper.calcularProm(2, 6));
            }

        ----------------------------------------------------------------------------
        //Otro tipo de sintaxis
        IOperacion oper = (n1, n2) -> (6+6)/2;
        System.out.println(oper.calcularProm(2, 6));
    
    *** Alcance en Lambda
        **Para variables locales tiene el mismo comportamiento, pero no es obligatorio 
        convertirla en final para llamar una variable local        
***********************************************************************************************


****************************************************************************Metodos por defecto
    Con la palabra default me permite crear un metodo en la interface sin tener que realzar una 
    implementacion

        public interface PersonaA {
            public void caminar();
            
            default public void hablar() {
                System.out.println("hello metodo hablar(), de la interface PersonaA.");
            }
        }

        public class DefaultMethod implements PersonaA, PersonaB {

            @Override
            public void caminar() {
                System.out.println("Hello methodDefault");
                
            }

            public static void main(String[] args) {
                DefaultMethod app = new DefaultMethod();
                app.caminar();
                app.hablar();
            }
        }

    ***Puedo implementar dos interfaces con el mismo metodo por defecto y cuando implemente 
    sobreescribir e indicar cual es el metodo y de que interface es el que se ejecutara

        public interface PersonaA {
            public void caminar();
            
            default public void hablar() {
                System.out.println("hello metodo hablar(), de la interface PersonaA.");
            }
        }

        public interface PersonaB {
            default public void hablar() {
                System.out.println("hello metodo hablar(), de la interface Persona B.");
            }
        }

        public class DefaultMethod implements PersonaA, PersonaB {

            @Override
            public void caminar() {
                System.out.println("Hello methodDefault");
                
            }

            @Override
            public void hablar() {
                //Se indica el metodo de que interface se utilizara
                PersonaA.super.hablar();
            }

            public static void main(String[] args) {
                DefaultMethod app = new DefaultMethod();
                app.caminar();
                app.hablar();
            }
        }
***********************************************************************************************


***************************************************************************Interface Functional
    Java8 incluye muchas novedades y entra ellas destacan las expresiones lambda. Recordemos 
    que una expresión lambda define el comportamiento de una función.
        (x, y) -> x + y;
    Sin embargo ella sola no compila ya que necesitamos igualarla a una variable lo que no 
    tenemos claro es a qué exactamente.

    Es aquí donde aparece el concepto de Java Functional Interface. Un interface funcional es
    aquel interface que solo dispone de un método abstracto. Así pues vamos a igualar nuestra 
    expresión lambda a un interface funcional

        public class Principal {
     
            interface Matematicas {         
                public double operacion(double x, double y);         
            }
             
            public static void main(String[] args) {
             
                Matematicas o = (x, y) -> x + y;
                System.out.println(o.operacion(2, 3));
             
            }
             
        }

    Java Functional Interface , recordemos que estos únicamente disponen de un método abstracto.

    Para evitar este tipo de problemas se usa la anotación @FunctionalInterface que obliga 
    al desarrollador a solo incluir un método abstracto.

        public class Principal {
            @FunctionalInterface
            interface Matematicas {     
                public double operacion(double x, double y);     
            }
             
            public static void main(String[] args) {
                Matematicas o = (x, y) -> x + y;
                System.out.println(o.operacion(2, 3));
            }
        }
***********************************************************************************************


******************************************************************Java Lambda reduce y wrappers
    Java Lambda reduce es una de las operaciones más utilizadas cuanto trabajamos con colecciones 
    de objetos y expresiones lambda. Reduce sirve para convertir un Array de elementos en un 
    único elemento y se usa por ejemplo para calcular la suma de n términos.
        public static void main(String[] args) {
            List<Integer> gastos= new ArrayList<Integer>();
            gastos.add(100);
            gastos.add(200);
            gastos.add(300);
          
            gastos.stream().reduce((acumulador,numero)-> {
              return acumulador+numero;
            }).ifPresent(System.out::println);
            
        }

    Gracias a la programación funcional podemos realizar estas operaciones de una forma muy 
    diferente nos podemos apoyar en el método reduce que recibe 2 parámetros un acumulador 
    como primero y un elemento como segundo .

    De esta forma realiza una funcionalidad de “reducción” convirtiendo una lista de elementos 
    en un único resultado.
    --------------------------------------------------------------------------------------
    De igual manera que usamos el método de reducción para sumar números podemos usarlo 
    también por ejemplo para combinar cadenas.

    public class Principal4 {
      public static void main(String[] args) {
        List<String> nombres= new ArrayList<String>();
        nombres.add("juan");
        nombres.add("gema");
        nombres.add("maria");
        nombres.stream().reduce(String::concat).ifPresent(System.out::println);
      }
    }
***********************************************************************************************


*************************************************************************Referencias de metodos
    Cuando referenciamos un método, lo que pasa es que la funcionalidad del método original es 
    implementada con una referencia al método referenciado, esto quieres decir que cuando ejecutemos 
    el método de la interface funcional, en realidad lo que pasara es que se ejecutara el método 
    de la otra clase.   

    La referencia del método Java 8 se usa para referirse a un método y para hacer que el programa 
    o código sea simple o claro, puede usar la referencia al método en lugar de una expresión lambda.

    En resumen esta notación abrevia la expresión lambda para llamar a cualquier método.

    El operador '::' se usa como referencia de método. 

    Veamos algunos tipos de referencias de método:
        Referencia a un método de una instancia: object :: instanceMethod
        Referencia a un método estático: Class :: staticMethod
        Referencia a un método de instancia de un objeto arbitrario de un tipo particular:  Class :: instanceMethod
        Referencia a un constructor: Class :: new


    Ejemplos_____________________________________________________________________________
    public class MedRefApp {
        public static void refMetodoStatico() {
            System.out.println("Metodo statico referido");
        }
    
        public void referenciaMetodoInstanciaObjArbitrario() {
            String[] nombres = {"Elvis", "Diego", "Antonio"};
            //Primera forma para ordenar un array
    //      Arrays.sort(nombres, new Comparator<String>() {
    //          @Override
    //          public int compare(String o1, String o2) {
    //              return o1.compareToIgnoreCase(o2);
    //          }
    //      });
            //Segunda forma ordenar un array con expresiones lambda
    //      Arrays.sort(nombres, (s1,s2) -> s1.compareToIgnoreCase(s2) );
    //      System.out.println(nombres);
            //Tercera forma de ordenar un array con metodo referenciado
            Arrays.sort(nombres, String::compareTo);
            System.out.println(Arrays.toString(nombres));
        }
    
        public void referenciaMetodoInstanciaObjParticular() {
            System.out.println("metodo referido instancia de clase");
        }
        
        public void referenciaConstructor() {
            //Implementacion anonima
    //      IPersona iPer = new IPersona() {            
    //          @Override
    //          public Persona crear(int id, String nombre) {
    //              return new Persona(id, nombre);
    //          }
    //      };
    //      iPer.crear(1, "Elvis");
            //Utilizando expresiones lambda
    //      IPersona Iper2 = (id,nombre) -> new Persona(id, nombre);
    //      Persona per = Iper2.crear(1, "Camila");
    //      System.out.println(per.getId() + " - " + per.getNombre());
            //Utilizando metodos referenciados al constructor
            IPersona Iper3 = Persona::new;
            Persona per = Iper3.crear(1, "Camila");
            System.out.println(per.getId() + " - " + per.getNombre());
        }
        
        public void operar() {
            
            //IOperacion op = () -> MedRefApp.refMetodoStatico();
            //op.saludar();
            //Referencia a un metodo estatico
            IOperacion op1 = MedRefApp::refMetodoStatico;
            op1.saludar();
            
            
        }
        
        public static void main(String[] args) {
            MedRefApp app = new MedRefApp();
            //app.operar();
            //app.referenciaMetodoInstanciaObjArbitrario();
            //Se implementa el metodo saludar de la interface Funcional
    //      IOperacion op = app::referenciaMetodoInstanciaObjParticular;
    //      op.saludar();
            app.referenciaConstructor();
        }
    }        
    --------------------------------------------------------------------------------------
    @FunctionalInterface
    public interface IOperacion {
        void saludar();
    }                       
    --------------------------------------------------------------------------------------
      @FunctionalInterface
        public interface IPersona {
            Persona crear(int id, String nombre);
        }  
    --------------------------------------------------------------------------------------
        public class Persona {
            private String nombre;
            private Integer id;
            
            public Persona(Integer id, String nombre ) {
                super();
                this.nombre = nombre;
                this.id = id;
            }
            public Persona() {
                super();
            }
            public String getNombre() {
                return nombre;
            }
            public void setNombre(String nombre) {
                this.nombre = nombre;
            }
            public Integer getId() {
                return id;
            }
            public void setId(Integer id) {
                this.id = id;
            }
            
        }                                                                                   
***********************************************************************************************


************************************************************************forEach, removeIf, sort
    foreach-----------------------------------------------------------------------------
            public void usarForEach() {
                //Foreach java 7
        //      for (String item : crearLista()) {
        //          System.out.println(item);
        //      }
                //Foreach con metodos lambda
                //crearLista().forEach(x -> System.out.println(x));
                //foreach con metodos referenciados
                lista.forEach(System.out::println);
            }
    removeIf-----------------------------------------------------------------------------
        public void usarRemoveIf() {
            //Erro al remover elemento de la lista
    //      for (String item : lista) {
    //          if(item.compareToIgnoreCase("elvis")==0){
    //              lista.remove(item);
    //          }
    //      }
            // Utilizando Iterato
    //      Iterator<String> iter = lista.iterator();
    //      while (iter.hasNext()) {
    //          if(iter.next().equalsIgnoreCase("Elvis")) {
    //              iter.remove();
    //          }
    //      }
            //Utilizando expresiones Lambda y removeIf en una ista
            lista.removeIf(x -> x.equalsIgnoreCase("yala"));
        }

    sort---------------------------------------------------------------------------------
            public void usarSort() {
                //Utilizando lambada
        //      lista.sort((x,y) -> x.compareTo(y));
                //Utilizando metodos de referencia
                lista.sort(String::compareTo);
            }
***********************************************************************************************


*****************************************************************************************Stream
    metodos mas Utilizados
    ----------------------------------------------------------------------------------
        //Metodo que filtra las palabras que empiecen por variable indicada
        public void filtrar() {
            lista.stream().filter(x -> x.startsWith("E")).forEach(System.out::println);
        }
    ----------------------------------------------------------------------------------
        //Metodo que ordena una lista y la imprime
        public void ordenar() {
            //Ordena de forma ascendente
    //      lista.stream().sorted().forEach(System.out::println);
            //ordena de forma descendente
            lista.stream().sorted((x,y)->y.compareTo(x)).forEach(System.out::println);
        }
    ----------------------------------------------------------------------------------
        public void transformar() {
            //Transforma una cadena a mayusculas
            lista.stream().map(String::toUpperCase).forEach(System.out::println);
            //forma iperativa de parsear a numeros
    //      for (String string : numeros) {
    //          int num = Integer.parseInt(string) + 1;
    //          System.out.println(num);
    //      }
            //parsea a numeros 
            numeros.stream().map(x -> Integer.parseInt(x)+1).forEach(System.out::println);
        }
    ----------------------------------------------------------------------------------
         public void limitar() {
            lista.stream().limit(3).forEach(System.out::println);
        }
    ----------------------------------------------------------------------------------
        public void contar() {
            System.out.println(lista.stream().count());
        }
    ----------------------------------------------------------------------------------
    Metodo peek
        Devuelve una secuencia que consta de los elementos de esta secuencia, además 
        de realizar la acción proporcionada en cada elemento a medida que se consumen 
        elementos de la secuencia resultante.
        Ejemplo_______________________________________________________________________
            List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.info("Role: "+authority.getAuthority()))
                .collect(Collectors.toList());
    **********************************************************************************
        * Manejo de stream() java 8
        * Convertir una lista de objetos en una lista de ids 
            List<String> nombresInstalaciones = instalacionesDto.stream().map(InstalacionUsuarioDTO::getNombreInstalacion).collect(Collectors.toList());
        * Convertir una lista de string a una cadena separados por comas
            nombresInstalaciones.stream().collect(Collectors.joining(","));
        * Partiendo de una lista de long busca objetos y devuelve un atributo de ese objeto, y los muta a una lista de String 
            List<String> instalacionesDto = idsInstalaciones.stream().map(idIns -> {
                try {
                    return iInstalacionBusiness.buscarPorIdPorProjection(idIns).getNombre();
                } catch (PersistenceException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
        * Partiendo de una cadena separada por comas lo muta a una lista de String 
            List<String> list = Stream.of(idsConfActuacion.split(",")).map(String::trim).collect(Collectors.toList());
        * Muta una lista de String a una lista de Long 
            list.stream().map(Long::valueOf).collect(Collectors.toList())
        * Filtrado de objeto 
            List<InstalacionUsuarioDTO> instSinTodElementos = instalacionesUsusDto.stream().filter(x -> !x.getTodosElementosAsignados()).collect(Collectors.toList());
***********************************************************************************************


***************************************************************************************Optional
    Poder gestionar de una manera adecuada una variable para que no se genere NullPointerException

        
    El siguiente punto que vamos a ver, es cómo crear la clase, Optional tiene un constructor privado,
     y nos proporciona tres métodos factoría estáticos para instanciar la clase. 
        public static<T> Optional<T> empty()
        public static <T> Optional<T> ofNullable(T value)
        public static <T> Optional<T> of(T value)
    
    Siendo el método  el que nos permite recubrir cualquier objeto en un optional. -->.of

    Los otros dos métodos nos permiten recubrir un valor nulo o devolver un objeto
    Optional vacío en caso de que queramos avisar de la ausencia de valor. La opción de 
    recubrir un nulo viene dada principalmente para permitirnos trabajar con APIs que hacen 
    uso de nulos para avisar de estas ausencias de valor.

    El método isPresent(), método es el equivalente a variable == null y cómo el propio nombre 
    indica nos dice si el objeto Optional contiene un valor o está vacío. Este método se usa 
    principalmente si trabajamos de manera imperativa con Optional
    
    El método get() es el encargado de devolvernos el valor, devolviendo una excepción si no 
    estuviera presente.

    get () solo puede devolver un valor si el objeto envuelto no es nulo , de lo contrario, 
    arroja una excepción

    //metodo que permite detectar si una variable esta null o no inicializada y colocarle un por defecto
        public void orElse(String valor) {
            //Creo obj Optional por valor pasado
    //      Optional<String> opt = Optional.of(valor);
    //      System.out.println(opt.get());
            //el metodo ofNullable acepta valores null
            Optional<String> opt = Optional.ofNullable(valor);
            System.out.println(opt.orElse("predeterminado"));
        }
        
        //metodo que si es null permite arrojar una exception
        public void orElseThrow(String valor) {
            Optional<String> opt = Optional.ofNullable(valor);
            opt.orElseThrow(NumberFormatException::new);
        }
        
        //metodo que indica nos dice si el objeto Optional contiene un valor o está vacío
        public void isPresent(String valor) {
            Optional<String> opt= Optional.ofNullable(valor);
            System.out.println(opt.isPresent());
        }


        public void ifPresent(String valor) {
            Optional<String> opt = Optional.of(valor);
            opt.ifPresent(name -> System.out.println(name.length()));
        }

        /*
            El  método orElseGet () es similar a orElse () . Sin embargo, en lugar de
            tomar un valor para devolver si el valor Opcional no está presente, toma 
            una interfaz funcional del proveedor que se invoca y devuelve el valor de 
            la invocación
        */
            public void orElseGet(String valor) {
                System.out.println(Optional.ofNullable(valor).orElseGet(() -> "Elvis"));
            }
    Retorno condicional con filtro------------------------------------------------------
        El método de filtro se usa normalmente de esta manera para rechazar valores 
        ajustados basados ​​en una regla predefinida. Podríamos usarlo para rechazar un 
        formato de correo electrónico incorrecto o una contraseña que no sea lo 
        suficientemente segura.

        Ejemplo____________________________________________________________________
            public class Modem {
                private Double price;
             
                public Modem(Double price) {
                    this.price = price;
                }
                // standard getters and setters
            }
            //Metodo aplicado en java 7
            public boolean priceIsInRange1(Modem modem) {
                boolean isInRange = false;
             
                if (modem != null && modem.getPrice() != null
                  && (modem.getPrice() >= 10
                    && modem.getPrice() <= 15)) {
             
                    isInRange = true;
                }
                return isInRange;
            }

            //Metodo aplicado en java 8
            public boolean priceIsInRange2(Modem modem2) {
                 return Optional.ofNullable(modem2)
                   .map(Modem::getPrice)
                   .filter(p -> p >= 10)
                   .filter(p -> p <= 15)
                   .isPresent();
             }

            La llamada del mapa se usa simplemente para transformar un valor 
            en otro valor. Tenga en cuenta que esta operación no modifica 
            el valor original.

            En nuestro caso, estamos obteniendo un objeto de precio de la clase Modelo. 
            Veremos el método map () en detalle en la siguiente sección.

            En primer lugar, si se pasa un objeto nulo a este método, no esperamos 
            ningún problema.

            En segundo lugar, la única lógica que escribimos dentro de su cuerpo 
            es exactamente lo que describe el nombre del método, la verificación del 
            rango de precios. 

    Transformando valor con map()-------------------------------------------------------
        El método de mapa devuelve el resultado del cálculo envuelto dentro de Opcional. 
        Luego tenemos que llamar a un método apropiado en el Opcional devuelto para recuperar 
        su valor.

        Observe que el método de filtro simplemente realiza una comprobación del valor y 
        devuelve un valor booleano. Por otro lado, el método de mapa toma el valor existente, 
        realiza un cálculo utilizando este valor y devuelve el resultado del cálculo envuelto 
        en un objeto Opcional

        Ejemplos____________________________________________________________________
            //Metodo que devuelve longitud de una lista por parametro
            public int map(List<String> lista) {
                Optional<List<String>> listaCad = Optional.ofNullable(lista);
                int size = listaCad.map(List::size).orElse(0);
                return size;
            }

        En este ejemplo, ajustamos una lista de cadenas dentro de un objeto Opcional y 
        usamos su método de mapa para realizar una acción en la lista contenida. La 
        acción que realizamos es recuperar el tamaño de la lista.

        El método de mapa devuelve el resultado del cálculo envuelto dentro de Opcional. 
        Luego tenemos que llamar a un método apropiado en el Opcional devuelto para recuperar 
        su valor.
        --------------------------------------------------------------------------
        Podemos encadenar el mapa y filtrar juntos para hacer algo más poderoso.
        //Metodo para verificar la exactitud de una contraseña ingresada por un usuario
            public boolean verifPass(String pass) {
                return Optional.of(pass).map(String::trim)
                        .filter(x -> x.equalsIgnoreCase("password"))
                        .isPresent();       
            }
        Como podemos ver, sin limpiar primero la entrada, se filtrará; sin embargo, los 
        usuarios pueden dar por sentado que todos los espacios iniciales y finales 
        constituyen entradas. Así que transformamos la contraseña sucia en una contraseña 
        limpia con un mapa antes de filtrar las incorrectas.

    Transformando valor con flatMap ()--------------------------------------------------
        Al igual que el método map () , también tenemos el método flatMap () como alternativa 
        para transformar valores. La diferencia es que el mapa transforma los valores solo 
        cuando se desenvuelven, mientras que flatMap toma un valor envuelto y lo desenvuelve 
        antes de transformarlo.

        Anteriormente, hemos creado simples objetos String e Integer para envolverlos en una 
        instancia Opcional . Sin embargo, con frecuencia, recibiremos estos objetos de un 
        descriptor de acceso de un objeto complejo.

        Ejemplo____________________________________________________________________
        public class Person {
            private String name;
            private int age;
            private String password;
         
            public Optional<String> getName() {
                return Optional.ofNullable(name);
            }
         
            public Optional<Integer> getAge() {
                return Optional.ofNullable(age);
            }
         
            public Optional<String> getPassword() {
                return Optional.ofNullable(password);
            }
         
            // normal constructors and setters
        }
    Crear Obj Optional------------------------------------------------------------------
        ***Optional vacio Optional.empty()
            Optional<String> empty = Optional.empty();

            Tenga en cuenta que utilizamos el método isPresent () para verificar si 
            hay un valor dentro del objeto Opcional . Un valor está presente solo si 
            hemos creado Opcional con un valor no nulo .

        ***Optional.of(valor)
            El argumento pasado al método of () no puede ser nulo. De lo contrario, 
            obtendremos una NullPointerException
                Optional<String> opt = Optional.of(name);

        ***En caso de que esperemos valores null método Optional.ofNullable ()
            String name = null;
            Optional<String> opt = Optional.ofNullable(name);
            assertFalse(opt.isPresent());

            Nota: si pasamos una referencia nula , no arroja una excepción, sino 
            que devuelve un objeto opcional vacío
***********************************************************************************************


****************************************************************************************Map API
    //Se le añaden items al mapa
    public void poblar() {
        mapa = new HashMap<>();
        mapa.put(0, "Elvis");
        mapa.put(1, "Antonio");
        mapa.put(2, "Diego");
        mapa.put(3, "Camila");
        mapa.put(4, "Ricardo"); 
    }
    
    //Se maneja mapa con Version 7 de java
    public void imprimirV7() {
        System.out.println("Java 7");
        for (Entry<Integer, String> obj : mapa.entrySet()) {
            System.out.println("Clave: "+obj.getKey()+" Valor: "+obj.getValue());
        }
    }
    
    //Maneras de recorrer u mapa con version 8 de Java
    public void imprimirV8() {
        System.out.println("Java 8");
        //Forma 1
    //      mapa.forEach((k,v) -> System.out.println("Clave: "+k+" Valor: "+v) );
        //Forma 2
        mapa.entrySet().stream().forEach(System.out::println);
    }

    //Inserta un Item si no esta presente la clave
    public void insertarAusente() {
        mapa.putIfAbsent(5, "Yalaury");
    }

    //Modifica item si esta presente
    public void operarSiPresente() {
        mapa.computeIfPresent(2, (k,v) -> "Elvis" );
    }
    
    //se recupera item o un valor predeterminado
    public void obtenerOrPredeterminado() {
        System.out.println(mapa.getOrDefault(5, "Valor por defecto"));
    }
    
    //Se crea mapa de items filtrados por su contenido
    public void filtrarMap() {
        System.out.println("Mapa filtrado.");
        Map<Integer, String> mapaNew= mapa.entrySet().stream()
                .filter(e -> e.getValue().contains("o"))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        mapaNew.forEach((k,v) -> System.out.println("Clave: "+k+" Valor: "+v));
    }
***********************************************************************************************


*******************************************************************************************Date
    Java 8 nos trae al fin una nueva api para el manejo de fechas. Nos encontramos dentro del 
    paquete java.time con nuevas clases para resolver los problemas con fechas como LocalDate, 
    horas con LocalTime o la combinación de fecha y hora con LocalDateTime. También incluye como 
    es debido dentro de esta api el uso de zonas horarios con ZonedDateTime.

    Además los conceptos de Period para determinar el periodo entre dos fechas y Duration para 
    determinar la duración entre dos horas.

    ------------------------------------------------------------------------------------
    //LocalDate  -- LocalDateTime -- LocalTime
    public void verificar(int version) {
        if(version == 7) {
            Calendar fecha1 = Calendar.getInstance();
            Calendar fecha2 = Calendar.getInstance();
            fecha1.set(1982, 9, 14);
            System.out.println(fecha1.after(fecha2));
        }else if(version == 8) {
            //Trabajando con fechas
            System.out.println("Fechas:");
            LocalDate fecha1 = LocalDate.of(1982, 10, 14);
            LocalDate fecha2 = LocalDate.now();
            System.out.println(fecha1.isAfter(fecha2));
            System.out.println(fecha1.isBefore(fecha2));
            
            //Trabajando con tiempo
            System.out.println("Tiempo:");
            LocalTime tiempo1 = LocalTime.of(7, 50, 30);
            LocalTime tiempo2 = LocalTime.now();
            
            System.out.println(tiempo1.isAfter(tiempo2));
            System.out.println(tiempo1.isBefore(tiempo2));
            
            //Trabajando con fechas y hora
            System.out.println("Fecha y Hora:");
            LocalDateTime fecCom1 = LocalDateTime.of(1982, 10, 14, 23, 12, 35);
            LocalDateTime fecCom2 = LocalDateTime.of(1982, 10, 14, 23, 12, 15);
            System.out.println(fecCom1.isAfter(fecCom2));
            System.out.println(fecCom1.isBefore(fecCom2));
        }
    }
    -----------------------------------------------------------------------------------
    Puedes además sumar o restar días facilmente,

        LocalDate datePlus = localDateOf.plusDays(7);
        System.out.println(datePlus.toString());  // 2017-10-17

        LocalDate dateMinus = localDateOf.minusDays(7);
        System.out.println(dateMinus.toString()); // 2017-10-03
    -----------------------------------------------------------------------------------
        LocalDateTime localDateTimePlus = localDateTimeOf.plusDays(5);
        System.out.println(localDateTimePlus); // 2017-08-25T08:30
        LocalDateTime localDateTimeMinus = localDateTimePlus.minusMinutes(10);
        System.out.println(localDateTimeMinus); // 2017-08-25T08:20
    ------------------------------------------------------------------------------------
    //Medir tiempo  Instant  -- Duration
    public void medirTiempo(int version) throws InterruptedException{
        if (version == 7) {
            System.out.println(System.currentTimeMillis());
            long inicio = System.currentTimeMillis();
            Thread.sleep(1000);
            long fin = System.currentTimeMillis();
            System.out.println(fin - inicio);
        }else if (version == 8) {
            Instant inicio = Instant.now();
            Thread.sleep(120000);
            Instant fin = Instant.now();
            System.out.println(Duration.between(inicio, fin).toMinutes());
        }
    }
    
    ------------------------------------------------------------------------------------
    //Convertir fechas DateTimeFormatter 
    public void convertir(int version) throws  java.text.ParseException{
        if (version == 7) {
            //convertir de cadena a fecha
            String fechaCad = "1982/10/14";
            DateFormat fmd =  new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = fmd.parse(fechaCad);
            System.out.println(fecha);
            //convertir de fecha a cadena
            Date fechaActual = Calendar.getInstance().getTime();
            fmd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
            String fechaCadena = fmd.format(fechaActual);
            System.out.println(fechaCadena);
        }else if(version == 8) {
            //convertir de cadena a fecha
            String fechaCad = "1982/10/14";
            DateTimeFormatter fmd = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate fechaNac = LocalDate.parse(fechaCad,fmd);
            System.out.println(fechaNac);
            System.out.println(fmd.format(fechaNac));
            fmd = DateTimeFormatter.ofPattern("yyyyMMdd");
            System.out.println(fmd.format(fechaNac));
        }
    }
    --------------------------------------------------------------------------------------
    ZonedDateTime
        Si necesitas trabajar con zonas horarias puedes utilizar esta clase que te provee 
        el manejo de fechas con hora para la zona que determines. 

            ZoneId.getAvailableZoneIds().forEach(z -> System.out.println(z)); // list of all zones
            ZoneId zoneId = ZoneId.of("America/Panama");
        
        Para ‘moverse’ a otra zona horaria, por ejemplo Tokyo, haces uso de de LocalDateTime 
        en conjunto con ZoneDateTime pasando la zona “Asia/Tokyo”

            LocalDateTime localDateTimeOf = LocalDateTime.of(2017, Month.AUGUST, 20, 8, 30);
            ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTimeOf, zoneId);
            System.out.println(zonedDateTime); // 2017-08-20T08:30-05:00[America/Panama]
            ZonedDateTime tokyoDateTime = localDateTimeOf.atZone(ZoneId.of("Asia/Tokyo"));
            System.out.println(tokyoDateTime); // 2017-08-20T08:30+09:00[Asia/Tokyo]

        //ZonedDateTime -- ZoneId
        public void zonaHoraria() {
            System.out.println("Zona Horaria");
            //ZoneId.getAvailableZoneIds().stream().sorted((x,y)->y.compareTo(x)).forEach(System.out::println);
            ZoneId zona = ZoneId.of("America/Buenos_Aires");
            
            LocalDateTime localDateTimeOf = LocalDateTime.of(2017, Month.AUGUST, 20, 8, 30);
            ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTimeOf, zona);
            System.out.println(zonedDateTime); // 2017-08-20T08:30-05:00[America/Buenos_Aires]
            ZonedDateTime tokyoDateTime = localDateTimeOf.atZone(ZoneId.of("Asia/Tokyo"));
            System.out.println(tokyoDateTime); // 2017-08-20T08:30+09:00[Asia/Tokyo]
        }
    ---------------------------------------------------------------------------------------
    Period
        Con la clase Period puedes obtener la diferencia entre dos fechas o utilizarlo para 
        modificar valores de alguna fecha.

        LocalDate startLocalDate = LocalDate.of(2017, 10, 10);
        LocalDate endLocalDate = startLocalDate.plus(Period.ofDays(10));  // 2017-10-20
        int diffDays = Period.between(startLocalDate, endLocalDate).getDays();
        System.out.println(diffDays); // 10


        //Periodo entre fechas Period
        public void periodoFechas(int version) {
            if(version == 7) {
                Calendar nacimiento = Calendar.getInstance();
                Calendar actual = Calendar.getInstance();           
                nacimiento.set(1982, 9, 14);            
                int anios = 0;
                
                while(nacimiento.before(actual)) {
                    nacimiento.add(Calendar.YEAR, 1);
                    if(nacimiento.before(actual)) {
                        anios++; 
                    }
                }
                System.out.println(anios);
            }else if (version == 8) {
                LocalDate nacimiento = LocalDate.of(1982, 10, 14);
                LocalDate actual = LocalDate.now();
                
                Period periodo = Period.between(nacimiento, actual);
                System.out.println("Han transcurrido "+periodo.getYears()+" , "+periodo.getMonths()
                +" meses y "+periodo.getDays()+" dias desde mi nacimiento");
            }
        }
    ------------------------------------------------------------------------------------
    Duration
        Duration es el equivalente a Period pero para las horas.

        //Periodo de tiempo, clase Duration 
        public void periodoTiempo() {
            System.out.println("Periodo de tiempo");
            LocalTime startLocalTime = LocalTime.of(8, 30);
            LocalTime endLocalTime = startLocalTime.plus(Duration.ofHours(3));  // 11:30

            long diffSeconds = Duration.between(startLocalTime, endLocalTime).getSeconds();
            System.out.println(diffSeconds); // 10800 seconds
         }
***********************************************************************************************


************************************************************************Funciones de Alto Orden
    Paso y devolucion de funcones en la programacion
        public class FuncAltOrder {
            //Se crea funciones
            private Function<String, String> converMay = x -> x.toUpperCase();
            private Function<String, String> converMin = x -> x.toLowerCase();
            
            //paso de una funcion
            public void imprimir(Function<String, String> funcion, String valor) {
                System.out.println(funcion.apply(valor));
            }
            
            //Devolver una funcion
            public Function<String, String> mostrar(String mensaje) {
                return (String x) -> mensaje + x;
            }
            
            //Se crea metodo que filtra por la longitud de una palabra y las imprime
            public void filtrar(List<String> lista, Consumer<String> consumidor, int longitud) {
                //convierte la lista en stream, con el metodo filter se le pasa metodo que establece 
                //si tiene la longitud correcta, recorre la lista pasando una funcion para que realice una tarea
                lista.stream().filter(this.estableceLongitudFiltro(longitud)).forEach(consumidor);;
            }
            
            //Se crea metodo que filtra si la palabra esta contenida en la lista
            public void filtrar(List<String> lista, Consumer<String> consumidor, String cadena) {
                //convierte la lista en stream, con el metodo filter se le pasa metodo que establece 
                //si la palabra esta contenida en la lista, recorre la lista pasando una funcion para que realice una tarea
                lista.stream().filter(this.estableceLongitudFiltro(cadena)).forEach(consumidor);;
            }
            
            public Predicate<String> estableceLongitudFiltro(int longitud){
                return texto -> texto.length() < longitud;
            }
            
            public Predicate<String> estableceLongitudFiltro(String cadena){
                return texto -> texto.contains(cadena);
            }

            public static void main(String[] args) {
                // TODO Auto-generated method stub
                FuncAltOrder fao = new FuncAltOrder();
        //      fao.imprimir(fao.converMay, "elvis Areiza");
        //      fao.imprimir(fao.converMin, "Elvis Areiza");
        //      System.out.println(fao.mostrar("Hola ").apply("Elvis"));
        //      System.out.println();
                List<String> lista = new ArrayList<String>();
                lista.add("pru");
                lista.add("prueba");
                lista.add("Yalaury");
                
        //      fao.filtrar(lista, System.out::println, 7);
                fao.filtrar(lista,System.out::println, "lau");
            }

        }
***********************************************************************************************


*****************************************************************************************RxJava
    Referencias --> https://www.youtube.com/watch?v=dD0vE3GGzDM&list=PLvimn1Ins-419yVe5iPfiXrg4mZJl5kLS&index=16
                --> https://riptutorial.com/es/rx-java
    Permite hacer el procesamiento asyncrono de manera legible, basandose en Observables, 
    Los observables están orientados a gestionar flujos asíncronos.

    RxJava es una implementación Java VM de Extensiones reactivas : una biblioteca para componer 
    programas asíncronos y basados ​​en eventos mediante el uso de secuencias observables.

    Los conceptos centrales de RxJava son sus Observables y Subscribers . Un Observable emite 
    objetos, mientras que un Subscriber consume.

    Observable

        Observable es una clase que implementa el patrón de diseño reactivo. Estos Observables 
        proporcionan métodos que permiten a los consumidores suscribirse a cambios de eventos. 
        Los cambios de evento son activados por lo observable. No hay restricción en el número 
        de suscriptores que un Observable puede tener, o el número de objetos que un Observable 
        puede emitir.

            Tomar como ejemplo:

            Observable<Integer> integerObservable = Observable.just(1, 2, 3); // Integer observable
            Observable<String> stringObservable = Observable.just("Hello, ", "World", "!"); // String observable

            Aquí, un objeto observable llamado integerObservable y stringObservable se crean 
            a partir del método de fábrica que just proporcionar la biblioteca Rx. Observe 
            que Observable es genérico y, por lo tanto, puede emitir cualquier objeto.
    
    ***Subscriber

        Un Subscriber es el consumidor. Un Subscriber puede suscribirse a un solo observable. 
        El Observable llama a los onNext() , onCompleted() y onError() del Subscriber .

            Subscriber<Integer> mSubscriber = new Subscriber<Integer>() {
                // NOTE THAT ALL THESE ARE CALLED BY THE OBSERVABLE
                @Override
                public void onCompleted() {
                    // called when all objects are emitted
                    System.out.println("onCompleted called!");
                }

                @Override
                public void onError(Throwable throwable) {
                    // called when an error occurs during emitting objects
                    System.out.println("onError called!");
                }

                @Override
                public void onNext(Integer integer) {
                    // called for each object that is emitted
                    System.out.println("onNext called with: " + integer);
                }
            };
 
        Tenga en cuenta que el Subscriber también es genérico y puede admitir cualquier objeto. 
        Un Subscriber debe suscribirse al observable llamando al método de subscribe en el observable.

            integerObservable.subscribe(mSubscriber);
 
        
                //notacion lambda
                public void hello() {
                      Observable.just("Hello, World!") // create new observable
                        .subscribe(onNext -> { // subscribe and perform action
                             System.out.println(onNext);   
                        });
                }
    --------------------------------------------------------------------------------------
    ***Se descarga Dependencias
        <dependency>
            <groupId>io.reactivex</groupId>
            <artifactId>rxjava</artifactId>
            <version>1.2.6</version>
        </dependency>

    --------------------------------------------------------------------------------------
        public class RxJava {

            private List<Integer> lista1;
            private List<Integer> lista2;
            
            public RxJava() {
                super();
                lista1 = new ArrayList<Integer>();
                lista2 = new ArrayList<Integer>();
                this.llenarListas();
            }
            
            public void llenarListas() {
                for (int i = 0; i < 10; i++) {
                    lista1.add(i);
                    lista2.add(i);
                }
            }

            //busca los numeros indicados
            public void buscar() {
                //Se crean los observables para ambas listas
                Observable<Integer> obs1= Observable.from(lista1);
                Observable<Integer> obs2= Observable.from(lista2);
                //Utilizamos el metodo merge, permite unir dos observables para un unico resultado
            //    Observable.merge(obs1, obs2).subscribe(new Action1<Integer>() {
            //        public void call(Integer numero) {
            //            if(numero == 3) {
            //                System.out.println(numero);
            //            }
            //        }
            //    });

                //con notacion lambda       
                Observable.merge(obs1, obs2).filter(x->x==5).subscribe(System.out::println);
            }


            public static void main(String[] args) {
        //      List<String> listaCad= new ArrayList<String>();
        //      listaCad.add("mito");
        //      listaCad.add("code");
        //      listaCad.add("Mito Code");
        //      
        //      //Principio basico de rxJava Observable
        //      //Se apoya en el patron observer
        //      Observable<String> obs = Observable.from(listaCad); 
        //      obs.subscribe(new Action1<String>() {
        //          @Override 
        //          public void call(String elemento) {
        //              System.out.println(elemento);
        //          }
        //      });
                
                RxJava rx= new RxJava();
                rx.buscar();

            }

        }
***********************************************************************************************


Recursividad
******************************************************************************Recursion de Java
    La recursión es la técnica de hacer que una función se llame a sí misma. Esta técnica 
    proporciona una manera de dividir problemas complicados en problemas simples que son más 
    fáciles de resolver.

    La recursión puede ser un poco difícil de entender cuando se encuentra por primera vez, 
    la mejor manera de descubrir cómo funciona es experimentar con ella.

    Condición de detención
        Así como los bucles pueden encontrarse con el problema del bucle infinito, las funciones 
        recursivas pueden encontrarse con el problema de la recursión infinita. La recursión 
        infinita es cuando la función nunca deja de llamarse a sí misma. Cada función recursiva 
        debe tener una condición de detención, que es la condición donde la función deja de llamarse 
        a sí misma. En el ejemplo anterior, la condición de detención es cuando el parámetro se 
        kconvierte en 0.
    Ejemplo____________________________________________________________________
         //Use la recursividad para sumar todos los números hasta el k.
        public static int sumar(int k){
            if(k>0) {
                return k+sumar(k-1);
            }else {
                return 0;
            }
        }

        //Use la recursividad para sumar todos los números entre 5 y 10.
        public static int sumarEntre(int ini, int fin) {
            if(fin > ini) {
                return fin + sumarEntre(ini, fin-1);
            }else {
                return 0;
            }
        }
************************************************************************************************


Clases Utiles

******************************************************Clase de Hilo con implementacion runnable

    //La clase hilo debe extender de runnable
    public class MiHilo implements Runnable{
        String nombreHilo;

        public MiHilo(String nHilo) {
            this.nombreHilo = nHilo;
        }
        
        //El metodo run establece el punto de entrada para otro hilo de ejecución 
        //concurrente dentro de su programa.
        public void run(){
            System.out.println("Comenzando Hilo "+nombreHilo);
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(400);
                    System.out.println("En hilo "+nombreHilo+", el recuento "+i);
                }
            } catch (Exception e) {
                System.out.println(nombreHilo +" interrumpido.");
            }
            System.out.println("Terminado "+ nombreHilo);
        }
    }

    -----------------------------------------------------------------------------------------------

    public class Hilos {

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            System.out.println("Hilo Principal iniciado");
            //Se instancia clase que implementa runnable
            MiHilo mh = new MiHilo("#1");
            //Se crea hilo con la clase que implementa runnable
            Thread nuevoh = new Thread(mh);
            //Se comienza el hilo
            nuevoh.start();

            for (int i = 0; i < 50; i++) {
                System.out.print(" .");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println("Hilo principal interrumpido.");
                }
            }
            System.out.println("Hilo principal Terminado");
        }
    }
***********************************************************************************************


************************************************Clase de MultiHilo con implementacion runnable
    public class MiHilo3 implements Runnable{
        Thread hilo;

        public MiHilo3(String nombre) {
            this.hilo = new Thread(this, nombre);
        }
        
        public static MiHilo3 hiloCyC (String nombre){
            MiHilo3 miHilo = new MiHilo3(nombre);
            miHilo.hilo.start();
            return miHilo;
        }
        
        public void run(){
            System.out.println(hilo.getName()+" iniciando");
            try{
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(400);
                    System.out.println("El hilo "+hilo.getName()+", tanda "+i);
                }
            }catch(Exception e){
                System.out.println("interrupcion del hilo "+hilo.getName());
            }
            System.out.println("Hilo terminando "+hilo.getName());
        }    
    }
    -----------------------------------------------------------------------------------------------
    public class Multihilos {
        
        public static void main(String[] args) {
            System.out.println("Hilo principal comenzando.");
            MiHilo3 miHilo1 = MiHilo3.hiloCyC("#1");
            MiHilo3 miHilo2 = MiHilo3.hiloCyC("#2");
            MiHilo3 miHilo3 = MiHilo3.hiloCyC("#3");
            
            for (int i = 0; i < 50; i++) {
                System.out.print(" .");
                try{
                    Thread.sleep(100);
                }catch (Exception e){
                    System.out.println("Hilo principal interrumpido.");
                }
            }
            System.out.println("Hilo principal terminado.");
        }
    }
***********************************************************************************************


*****************************************************************************Clase Conexion SQL
    import java.sql.*;
    /**
     *
     * @author Elvis Areiza
     */
    public class Conexion {
        public static Connection getConexion(){
    	//Contexto de conexion
            Connection cn= null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cn=DriverManager.getConnection("jdbc:mysql://localhost/carritojsp", "root","");
                //System.out.println("Conexion satisfactoria.");
            } catch (Exception e) {
                System.out.println("Error de conexion."+e);
            }
            return cn;
        }
        public static void main(String[] args) {
            getConexion();
        }
        
    }

    ------------------------------------------------------------------------------------------------
    public class Conexion{
    	private static String JDBC_DRIVER="com.mysql.jdbc.Driver";
    	private static String JDBC_URL="jdbc:mysql://localhost/sga?useSSL=false";
    	private static String JDBC_USER ="root";
    	private static String JDBC_PASS="admin";
    	private static Driver driver=null;
    	public static synchronizedConnection getConnection() throwsSQLException{
    		if(driver==null) {
    			try{
    				ClassjdbcDriverClass=Class.forName(JDBC_DRIVER);
    				driver=(Driver) jdbcDriverClass.newInstance();
    				DriverManager.registerDriver(driver);
    			} catch(Exceptione) {
    				System.out.println("Fallo en cargar el driver JDBC");
    				e.printStackTrace();
    			}
    		}
    		returnDriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    	}


    	public static void close(ResultSetrs) {
    		try{
    			if(rs!=null) {
    				rs.close();
    			}
    		} catch(SQLExceptionsqle) {
    			sqle.printStackTrace();
    		}
    	}

    	public static void close(PreparedStatementstmt) {
    		try{
    			if(stmt!=null) {
    				stmt.close();
    			}
    		} catch(SQLExceptionsqle) {
    			sqle.printStackTrace();
    		}
    	}


    	//Cierre de la conexion
    	public static voidclose(Connection conn) {
    		try{
    			if(conn!=null) {
    				conn.close();
    			}
    		} catch(SQLExceptionsqle) {
    			sqle.printStackTrace();
    		}
    	}
    }
    ------------------------------------------------------------------------------------------------
    public class PersonasJDBC{
    	//Nos apoyamos de la llave primaria autoincrementablede MySql
    	//por lo que se omite el campo de persona_id
    	//Se utiliza un prepareStatement, por lo que podemos
    	//utilizar parametros(signos de ?)
    	//los cuales posteriormente será sustituidos por el parametrorespectivo
    	private final String SQL_INSERT = "INSERT INTO persona(nombre, apellido) VALUES(?,?)";
    	private final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=? WHERE id_persona=?";
    	privatefinalStringSQL_DELETE = "DELETE FROM persona WHERE id_persona= ?";
    	privatefinalStringSQL_SELECT = "SELECT id_persona, nombre, apellido FROM persona ORDER BY 	id_persona";

    	public int insert(Strin gnombre, String apellido) {
    		Connection conn=null;
    		PreparedStatement stmt=null;
    		ResultSet rs=null;//no se utiliza en este ejercicio
    		int rows=0; //registros afectados
    		try{
    			conn=Conexion.getConnection();
    			stmt=conn.prepareStatement(SQL_INSERT);
    			intindex=1;//contador de columnas
    			stmt.setString(index++, nombre);//param1 => ?
    			stmt.setString(index++, apellido);//param2 => ?
    			System.out.println("Ejecutando query:"+SQL_INSERT);
    			rows=stmt.executeUpdate();//no. registros afectados
    			System.out.println("Registros afectados:"+rows);
    		} catch(SQLExceptione) {
    			e.printStackTrace();
    		} finally{
    			Conexion.close(stmt);
    			Conexion.close(conn);
    		}
    		returnrows;
    	}

    	public int update(intid_persona, Stringnombre, Stringapellido) {
    		Connection conn=null;
    		PreparedStatement stmt=null;
    		int rows=0;
    		try{
    			conn=Conexion.getConnection();
    			System.out.println("Ejecutando query:"+SQL_UPDATE);
    			stmt=conn.prepareStatement(SQL_UPDATE);
    			int index=1;
    			stmt.setString(index++, nombre);
    			stmt.setString(index++, apellido);
    			stmt.setInt(index, id_persona);
    			rows=stmt.executeUpdate();
    			System.out.println("Registros actualizados:"+rows);
    		} catch(SQLExceptione) {
    			e.printStackTrace();
    		} finally{
    			Conexion.close(stmt);
    			Conexion.close(conn);
    		}
    		returnrows;
    	}


    	public int delete(int id_persona) {
    		Connection conn=null;
    		PreparedStatement stmt=null;
    		int rows=0;
    		try{
    			conn=Conexion.getConnection();
    			System.out.println("Ejecutando query:"+SQL_DELETE);
    			stmt=conn.prepareStatement(SQL_DELETE);
    			stmt.setInt(1, id_persona);
    			rows=stmt.executeUpdate();
    			System.out.println("Registros eliminados:"+rows);
    		} catch(SQLExceptione) {
    			e.printStackTrace();
    		} finally{
    			Conexion.close(stmt);
    			Conexion.close(conn);
    		}
    		returnrows;
    	}


    	public List<Persona> select() {
    		Connection conn=null;
    		PreparedStatement stmt=null;
    		ResultSet rs=null;
    		Persona persona=null;
    		List<Persona> personas =new ArrayList<Persona>();
    		try{
    			conn=Conexion.getConnection();
    			stmt=conn.prepareStatement(SQL_SELECT);
    			rs=stmt.executeQuery();
    			while(rs.next()) {
    				int id_persona=rs.getInt(1);
    				String nombre =rs.getString(2);
    				String apellido =rs.getString(3);
    				persona =new Persona();
    				persona.setId_persona(id_persona);
    				persona.setNombre(nombre);
    				persona.setApellido(apellido);
    				personas.add(persona);
    			}
    		} catch(SQLExceptione) {
    			e.printStackTrace();
    		} finally{
    			Conexion.close(rs);
    			Conexion.close(stmt);
    			Conexion.close(conn);
    		}
    		returnpersonas;
    	}
    }

    --------------------------------------------------------------------------------------------
    	public class ManejoPersonas{
    		public static void main(String[] args) {
    			PersonasJDBC personasJDBC=new PersonasJDBC();
    			//Prueba del metodoinsert
    			//personasJDBC.insert("Alberto", "Juarez");
    			//Prueba del metodoupdate
    			//personasJDBC.update(2, "Nombre3", "Apellido3");
    			//Prueba del metododelete
    			//personasJDBC.delete(1);
    			//Prueba del metodoselect
    			//Uso de un objeto persona para encapsular la informacion
    			//de un registro de base de datos
    			List<Persona>personas =personasJDBC.select();
    			for(Persona persona:personas) {
    				System.out.print(persona);
    				System.out.println("");
    			}
    		}
    	}
    -----------------------------------------------------------------------store procedure modificar
    delimiter //
    CREATE PROCEDURE procesarCuota(
    IN idCuo INT,
    IN idCom INT,
    IN ced INT,
    IN fechaPag DATE,
    IN refer VARCHAR(20)
    )
    BEGIN
    Update Cuotas set fechaProc=fechaPag, status=1, refe = refer  
    WHERE idCuota=idCuo 
    and idCompra=(select id_compra 
        from compras 
        where id_compra=idCom 
        and cedula=ced );
    END;
    //


    -----------------------------------------------------------Metodo para ejecutar store procedure
    public boolean setProcesarCuota (Cuotas cuo, int cedula){
            boolean rpta= false;
            Connection cn= null;
            CallableStatement csta=null;
            cn=Conexion.getConexion();
            try{
                csta=cn.prepareCall("{Call procesarCuota (?,?,?,?,?)}");
                
                csta.setInt(1, cuo.getId_cuota());
                csta.setInt(2, cuo.getId_compra());
                csta.setInt(3, cedula);
                csta.setString(4, cuo.getFechaProc());
                csta.setString(5, cuo.getRef());
                int i=csta.executeUpdate();
                if (i==1){
                    rpta=true;
                }else{
                    rpta=false;
                }
            }catch (Exception e){
                System.out.println("Error: "+e);
            }
            return rpta;
        }
***********************************************************************************************


********************************************************************Clase Conexion SQL metodo 2
    package datos;

    import java.sql.Connection;
    import java.sql.Driver;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;

    /**
     * Clase Conexion JDBC
     */
    public class Conexion {

        //Valores de conexion a MySql

        private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        //El puerto es opcional
        private static String JDBC_URL = "jdbc:mysql://localhost/sga?useSSL=false";
        private static String JDBC_USER = "root";
        private static String JDBC_PASS = "admin";
        private static Driver driver = null;

    	//Para que no haya problemas al obtener la conexion de
        //manera concurrente, se usa la palabra synchronized
        public static synchronized Connection getConnection()
                throws SQLException {
            if (driver == null) {
                try {
                    //Se registra el driver
                    Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                    driver = (Driver) jdbcDriverClass.newInstance();
                    DriverManager.registerDriver(driver);
                } catch (Exception e) {
                    System.out.println("Fallo en cargar el driver JDBC");
                    e.printStackTrace();
                }
            }
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        }

        //Cierre del resultSet
        public static void close(ResultSet rs) {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        //Cierre del PrepareStatement
        public static void close(PreparedStatement stmt) {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        //Cierre de la conexion
        public static void close(Connection conn) {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }

    -----------------------------------------------------------------------------------------------
    package datos;

    import domain.Persona;
    import java.sql.*;
    import java.util.*;

    /**
     * Clase que contiene los mÃ©todos de SELECT, INSERT, UPDATE y DELETE para la
     * tabla de PERSONAS en MYSQL
     *
     * @author Ubaldo
     *
     */
    public class PersonasJDBC {
        //Nos apoyamos de la llave primaria autoincrementable de MySql
        //por lo que se omite el campo de persona_id
        //Se utiliza un prepareStatement, por lo que podemos
        //utilizar parametros (signos de ?)
        //los cuales posteriormente serÃ¡ sustituidos por el parametro respectivo

        private final String SQL_INSERT
                = "INSERT INTO persona(nombre, apellido) VALUES(?,?)";

        private final String SQL_UPDATE
                = "UPDATE persona SET nombre=?, apellido=? WHERE id_persona=?";

        private final String SQL_DELETE
                = "DELETE FROM persona WHERE id_persona = ?";

        private final String SQL_SELECT
                = "SELECT id_persona, nombre, apellido FROM persona ORDER BY id_persona";

        /**
         * Metodo que inserta un registro en la tabla de Persona
         *
         * @param nombre
         * @param apellido
         */
        public int insert(String nombre, String apellido) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;//no se utiliza en este ejercicio		
            int rows = 0; //registros afectados
            try {
                conn = Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_INSERT);
                int index = 1;//contador de columnas
                stmt.setString(index++, nombre);//param 1 => ?
                stmt.setString(index++, apellido);//param 2 => ?
                System.out.println("Ejecutando query:" + SQL_INSERT);
                rows = stmt.executeUpdate();//no. registros afectados
                System.out.println("Registros afectados:" + rows);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.close(stmt);
                Conexion.close(conn);
            }
            return rows;
        }

        /**
         * Metodo que actualiza un registro existente
         *
         * @param id_persona Es la llave primaria
         * @param nombre Nuevo Valor
         * @param apellido Nuevo Valor
         * @return int No. de registros modificados
         */
        public int update(int id_persona, String nombre, String apellido) {
            Connection conn = null;
            PreparedStatement stmt = null;
            int rows = 0;
            try {
                conn = Conexion.getConnection();
                System.out.println("Ejecutando query:" + SQL_UPDATE);
                stmt = conn.prepareStatement(SQL_UPDATE);
                int index = 1;
                stmt.setString(index++, nombre);
                stmt.setString(index++, apellido);
                stmt.setInt(index, id_persona);
                rows = stmt.executeUpdate();
                System.out.println("Registros actualizados:" + rows);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.close(stmt);
                Conexion.close(conn);
            }
            return rows;
        }

        /**
         * Metodo que elimina un registro existente
         *
         * @param id_persona Es la llave primaria
         * @return int No. registros afectados
         */
        public int delete(int id_persona) {
            Connection conn = null;
            PreparedStatement stmt = null;
            int rows = 0;
            try {
                conn = Conexion.getConnection();
                System.out.println("Ejecutando query:" + SQL_DELETE);
                stmt = conn.prepareStatement(SQL_DELETE);
                stmt.setInt(1, id_persona);
                rows = stmt.executeUpdate();
                System.out.println("Registros eliminados:" + rows);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.close(stmt);
                Conexion.close(conn);
            }
            return rows;
        }

        /**
         * Metodo que regresa el contenido de la tabla de personas
         */
        public List<Persona> select() {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Persona persona = null;
            List<Persona> personas = new ArrayList<Persona>();
            try {
                conn = Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_SELECT);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    int id_persona = rs.getInt(1);
                    String nombre = rs.getString(2);
                    String apellido = rs.getString(3);
                    /*System.out.print(" " + id_persona);
                     System.out.print(" " + nombre);
                     System.out.print(" " + apellido);
                     System.out.println();
                     */
                    persona = new Persona();
                    persona.setId_persona(id_persona);
                    persona.setNombre(nombre);
                    persona.setApellido(apellido);
                    personas.add(persona);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            }
            return personas;
        }
    }

    ------------------------------------------manejo con transacciones commit,autocommit y rollback
    package manejopersonas;

    import datos.Conexion;
    import datos.PersonasJDBC;
    import java.sql.*;

    public class ManejoPersonas {

        public static void main(String[] args) {
            PersonasJDBC personasJDBC = new PersonasJDBC();
    	
            //Creamos un objeto conexion, se va a compartir
            //para todos los queries que ejecutemos
            Connection conn = null;

            try {
                conn = Conexion.getConnection();
    			//Revisamos si la conexion esta en modo autocommit
                //por default es autocommit == true
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
    			//Creamos el objeto PersonasJDBC
                //proporcionamos la conexion creada
                PersonasJDBC personas = new PersonasJDBC(conn);

    			//empezamos a ejecutar sentencias
                //recordar que una transaccion agrupa varias
                //sentencias SQL
                //si algo falla no se realizan los cambios en 
                //la BD
                //cambio correcto
                personas.update(2, "Regreso2", "Regreso");

                //Provocamos un error superando los 45 caracteres
                //del campo de apellido
                personas.insert("Miguel2",
                        "Ayala12341234123412341234123412341234123412341234123412341234123412341234123412341234");
                        //"Ayala2");
                //guardamos los cambios
                conn.commit();
            } catch (SQLException e) {
                //Hacemos rollback en caso de error
                try {
                    System.out.println("Entramos al rollback");
                    //Imprimimos la excepcion a la consola
                    e.printStackTrace(System.out);
                    //Hacemos rollback
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace(System.out);
                }
            }
        }
    }
***********************************************************************************************


*********************************************************************************Conexion AS400
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.Properties;

    /**
     *
     * @author eaareiza
     */
    public class conex400 {
       // Cargar lo siguiente desde un objeto de propiedades.
        private String DRIVER = "com.ibm.as400.access.AS400JDBCDriver";
        private String URL    = "jdbc:as400://10.1.8.51";  
        private String cadena = "SELECT * FROM b02trabajo.sif004hhhh WHERE CARACC = '000000000000000000000003734901' ";
        
        public conex400(){
            
        }
        
        public conex400(String dr, String url, String cad){
            this.DRIVER=dr;
            this.URL=url;
            this.cadena=cad;
        }
        
        public void conectar(){
            try {
                Class.forName(DRIVER);
            } catch (Exception e) {
                System.out.println("Imposible registrar el controlador.");
                System.out.println(e.getMessage());
                System.exit(1);
            }
            
            Connection c = null;
            
            PreparedStatement ps = null;
            
            try {
                // Crear las propiedades de conexión.
                Properties properties = new Properties ();
                properties.put ("user", "DESAN00821");
                properties.put ("password", "alfonso3");

                // Conectar con la base de datos local de iSeries.
                c = DriverManager.getConnection(URL, properties);

                
                try {
                    
                    ps = c.prepareStatement(cadena);//**
                    // Se ejecuta una consulta SQL en la tabla.
                    ResultSet rs = ps.executeQuery();//**
                    // Visualizar todos los datos de la tabla.
                    while (rs.next()) {//**
                        System.out.println("El empleado " + rs.getString("CARAEC") );//**
                    }//**
                } catch (SQLException e) {
                    System.out.println("Error en el select. "+e.getMessage());
                }
                // Se ejecuta una sentencia SQL que crea una tabla en la base de datos.
                //s.executeUpdate("CREATE TABLE b02trabajo.A07NIN040B (NAME VARCHAR(20), ID INTEGER)");
            } catch (SQLException sqle) {
                System.out.println("El proceso de base de datos ha fallado.");
                System.out.println("Razón: " + sqle.getMessage());
            } finally {
                // Se cierran los recursos de base de datos.
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException e) {
                    System.out.println("El borrado no ha podido cerrar Statement.");
                }
            }
        }
        
        
        /**
         * @return the DRIVER
         */
        public String getDRIVER() {
            return DRIVER;
        }

        /**
         * @param DRIVER the DRIVER to set
         */
        public void setDRIVER(String DRIVER) {
            this.DRIVER = DRIVER;
        }

        /**
         * @return the URL
         */
        public String getURL() {
            return URL;
        }

        /**
         * @param URL the URL to set
         */
        public void setURL(String URL) {
            this.URL = URL;
        }

        /**
         * @return the cadena
         */
        public String getCadena() {
            return cadena;
        }

        /**
         * @param cadena the cadena to set
         */
        public void setCadena(String cadena) {
            this.cadena = cadena;
        }
    }
    -----------------------------------------------------------------------------------------------
    import java.sql.*;
    import java.util.Properties;
    /**
     *
     * @author Elvis Areiza
     */
    public class Conexion {
        // Cargar lo siguiente desde un objeto de propiedades.
        String DRIVER = "com.ibm.as400.access.AS400JDBCDriver";
        String URL    = "jdbc:as400://10.1.8.51";
        
        public Connection getConexion(){
            Connection cn= null;
            try {
                Class.forName(DRIVER);
            } catch (Exception e) {
                System.out.println("Imposible registrar el controlador.");
                System.out.println(e.getMessage());
                System.exit(1);
            }
            
            try {
                // Crear las propiedades de conexión.
                Properties properties = new Properties ();
                properties.put ("user", "DESAN00821");
                properties.put ("password", "alfonso3");
                // Conectar con la base de datos local de iSeries.
                cn = DriverManager.getConnection(URL, properties);
                
                System.out.println("Conexion satisfactoria.");
            } catch (Exception e) {
                System.out.println("Error de conexion."+e);
            }
            return cn;
        }
        
        
    }
***********************************************************************************************


********************************************************************Directorio activo LDAP Auth
    import java.util.Hashtable;
    import javax.naming.Context;
    import javax.naming.NamingEnumeration;
    import javax.naming.NamingException;
    import javax.naming.directory.Attribute;
    import javax.naming.directory.Attributes;
    import javax.naming.directory.DirContext;
    import javax.naming.directory.InitialDirContext;
    import javax.naming.directory.SearchControls;
    import javax.naming.directory.SearchResult;
    import Modelo.Usuario;
    import java.io.IOException;
     
    /**
     * Contiene la Funcionalidad de Autenticacion
     * y busquede de atributos de un usuario en el LDAP
     *
     * @author  Elvis Areiza
     */
    public final class ldapAuth {
        
        //Se inicializan variables
        private String usuario;
        private String clave;
        private String servidor;
        private String dn;
        private String tipoAuth;
        private boolean autenticado;
        private String group;
        String ruta="D:/Log_TTK5.txt";
        //Controlador cont=new  Controlador();
        
        //containing methods for examining and updating attributes associated with objects, 
        //and for searching the directory.
        DirContext dc;
     
        /**
         * Constructor de la conexion con el Motor de LDAP
         *
         * @param server  Servidor en donde se encuentra el LDAP
         * @param dn      Directoria del arbol del LDAP
         * @param ta      Tipo de Autenticacion
         * @param usuario Usuario que desea realizar la conexion
         * @param clave   Clave del usuario
         *
         */
        public ldapAuth(){
            
        }
        
        public ldapAuth(String server, String dn, String ta,String usuario,String clave) throws IOException {
            this.servidor = server;
            this.dn = dn;
            this.tipoAuth = ta;
            this.usuario=usuario;
            this.clave=clave;
            inicializarConexion();
            //cont.EscrituraArchivo(cont.fecha(), ruta, "El Usuario "+usuario+" se inicializa conexion con LDAP.");
        }
        
        //Se inicializa conexion
        public void inicializarConexion() {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, servidor);
            env.put(Context.SECURITY_AUTHENTICATION, tipoAuth);
            env.put(Context.SECURITY_PRINCIPAL, dn);
            env.put(Context.SECURITY_CREDENTIALS, clave);
     
            try {
                dc = new InitialDirContext(env);
                setAutenticado(true);
            } catch (NamingException ex) {
                System.out.println("Error Autenticando mediante LDAP, Error causado por : " + ex.toString());
                setAutenticado(false);
            }
        }
     
        /**
         * Retorna el Atributo de la conexion con LDAP actual
         * 
         * @param atributo Nombre del Atributo que se desea obtener
         * @return Attribute con la informacion correspondiente
         */
     
        public Attribute cargarPropiedadConexion(String atributo) {
            Attribute propiedad = null;
            try {
                Attributes attrs = dc.getAttributes(dn);
     
                if (attrs == null) {
                    propiedad = null;
                } else {
                    propiedad = attrs.get(atributo);
                }
            } catch (Exception e) {
                propiedad = null;
            }
            return propiedad;
        }
        
        //Metodo para retornar un atributo
        public String cagarAtributos(String accName){
            // Se crea el buscador por defecto
            SearchControls ctls = new SearchControls();
            //Creo cadena con los filtros a implementar
            String filter = "(&(sAMAccountName="+accName+"))";
            String atributo="";
            try{
                // Busco los objetos usando los filtros
                //NamingEnumeration busqueda = dc.search("OU=Estandar,OU=Central,OU=Banesco Banco,OU=Usuarios,DC=intra,DC=banesco,DC=com", filter, ctls);
                NamingEnumeration busqueda = dc.search("OU=Administradores,OU=Central,OU=Banesco Banco,OU=Usuarios,DC=intra,DC=banesco,DC=com", filter, ctls);
                
                
                while (busqueda.hasMore()) {
                    SearchResult sr = (SearchResult) busqueda.next();
                    Attributes attrs = sr.getAttributes();
                    Attribute attr = attrs.get("employeeID");
                    atributo=(String)attr.get();
                }
            }catch(Exception e){
                System.out.println("error: "+e.getMessage());
            }
            //retorno atributo
            return atributo;
        }

        //Se crea funcion que retorna un objeto usuario con varios atributos de la intra
        public Usuario atribUsu(){
            Usuario usu= new Usuario();
            String ced, email, nombreComp;
            
            // Se crea el buscador por defecto
            SearchControls ctls = new SearchControls();
            //Creo cadena con los filtros a implementar
            String filter = "(&(sAMAccountName="+usuario+"))";
            String atributo="";
            try{
                // Busco los objetos usando los filtros
                //NamingEnumeration busqueda = dc.search("OU=Estandar,OU=Central,OU=Banesco Banco,OU=Usuarios,DC=intra,DC=banesco,DC=com", filter, ctls);
                NamingEnumeration busqueda = dc.search("OU=Administradores,OU=Central,OU=Banesco Banco,OU=Usuarios,DC=intra,DC=banesco,DC=com", filter, ctls);
                
                if (busqueda.hasMore()!=true){
                    busqueda = dc.search("OU=Estandar,OU=Central,OU=Banesco Banco,OU=Usuarios,DC=intra,DC=banesco,DC=com", filter, ctls);
                }
                    while (busqueda.hasMore()) {
                        SearchResult sr = (SearchResult) busqueda.next();
                        Attributes attrs = sr.getAttributes();
                        Attribute cedula = attrs.get("employeeID");
                        Attribute mail = attrs.get("mail");
                        Attribute nomComp = attrs.get("name");
                        Attribute grupo = attrs.get("memberOf");                    
                        
                        ced=(String)cedula.get();
                        email=(String)mail.get();
                        nombreComp=(String)nomComp.get();
                        group=(String)grupo.get();
                        
                        usu.setCedula(ced);
                        usu.setMail(email);
                        usu.setNomCompleto(nombreComp);
                        usu.setGroup(group);
                        usu=new Usuario(usuario, clave, nombreComp, email, ced, grupo);
                        
                    }
                    
                
            }catch(Exception e){
                
            }
            return usu;
        }
     
         
        /*Get's y Set's*/
        public boolean isAutenticado() {
            return autenticado;
        }
        public void setAutenticado(boolean autenticado) {
            this.autenticado = autenticado;
        }
        public String getUsuario() {
            return usuario;
        }
        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }
        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }
    }
***********************************************************************************************


*************************************************************Clase Zip un conjunto de archivos
    Comprimir___________________________________________________________________________
    public class Ejecutor {

        public static void main(String[] args) {
            // cadena que contiene la ruta donde están los archivos a comprimir
            String directorioZip = "C:\\ZIP\\";
            // ruta completa donde están los archivos a comprimir
            File carpetaComprimir = new File(directorioZip);
     
            // valida si existe el directorio
            if (carpetaComprimir.exists()) {
                // lista los archivos que hay dentro del directorio
                File[] ficheros = carpetaComprimir.listFiles();
                System.out.println("Número de ficheros encontrados: " + ficheros.length);
     
                // ciclo para recorrer todos los archivos a comprimir
                for (int i = 0; i < ficheros.length; i++) {
                    System.out.println("Nombre del fichero: " + ficheros[i].getName());
                    String extension="";
                    for (int j = 0; j < ficheros[i].getName().length(); j++) {
                        //obtiene la extensión del archivo
                        if (ficheros[i].getName().charAt(j)=='.') {
                            extension=ficheros[i].getName().substring(j, (int)ficheros[i].getName().length());
                            //System.out.println(extension);
                        }
                    }
                    try {
                        // crea un buffer temporal para ir poniendo los archivos a comprimir
                        ZipOutputStream zous = new ZipOutputStream(new FileOutputStream(directorioZip + ficheros[i].getName().replace(extension, ".zip")));
                        
                        //nombre con el que se va guardar el archivo dentro del zip
                        ZipEntry entrada = new ZipEntry(ficheros[i].getName());
                        zous.putNextEntry(entrada);
                        
                            //System.out.println("Nombre del Archivo: " + entrada.getName());
                            System.out.println("Comprimiendo.....");
                            //obtiene el archivo para irlo comprimiendo
                            FileInputStream fis = new FileInputStream(directorioZip+entrada.getName());
                            int leer;
                            byte[] buffer = new byte[1024];
                            while (0 < (leer = fis.read(buffer))) {
                                zous.write(buffer, 0, leer);
                            }
                            fis.close();
                            zous.closeEntry();
                        zous.close();                   
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }               
                }
                System.out.println("Directorio de salida: " + directorioZip);
            } else {
                System.out.println("No se encontró el directorio..");
            }
        }
    }

    Descomprimir__________________________________________________________________________
    public class Ejecutor {

        public static void main(String[] args) {
            //cadena que contiene la ruta donde están los archivos .zip
            String directorioZip = "C:\\UNZIP\\";
            //ruta donde están los archivos .zip
            File carpetaExtraer = new File(directorioZip);
            
            //valida si existe el directorio
            if (carpetaExtraer.exists()) {
                //lista los archivos que hay dentro  del directorio
                File[] ficheros = carpetaExtraer.listFiles();
                System.out.println("Número de ficheros encontrados: " + ficheros.length);
                
                //ciclo para recorrer todos los archivos .zip
                for (int i = 0; i < ficheros.length; i++) {
                    System.out.println("Nombre del fichero: " + ficheros[i].getName());
                    System.out.println("Descomprimiendo.....");
                    try {
                        //crea un buffer temporal para el archivo que se va descomprimir
                        ZipInputStream zis = new ZipInputStream(new FileInputStream(directorioZip + ficheros[i].getName()));
     
                        ZipEntry salida;
                        //recorre todo el buffer extrayendo uno a uno cada archivo.zip y creándolos de nuevo en su archivo original 
                        while (null != (salida = zis.getNextEntry())) {
                            System.out.println("Nombre del Archivo: "+salida.getName());    
                                FileOutputStream fos = new FileOutputStream(directorioZip + salida.getName());
                                int leer;
                                byte[] buffer = new byte[1024];
                                while (0 < (leer = zis.read(buffer))) {
                                    fos.write(buffer, 0, leer);
                                }
                                fos.close();
                                zis.closeEntry();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                System.out.println("Directorio de salida: " + directorioZip);
            } else {
                System.out.println("No se encontró el directorio..");
            }
        }
    }
***********************************************************************************************


*******************************************************************Comprimir .Zip un Directorio
    public static void addDirToZipArchive(ZipOutputStream zos, File fileToZip, String parrentDirectoryName) throws Exception {
        if (fileToZip == null || !fileToZip.exists()) {
            return;
        }

        String zipEntryName = fileToZip.getName();
        if (parrentDirectoryName!=null && !parrentDirectoryName.isEmpty()) {
            zipEntryName = parrentDirectoryName + "/" + fileToZip.getName();
        }

        if (fileToZip.isDirectory()) {
            System.out.println("+" + zipEntryName);
            for (File file : fileToZip.listFiles()) {
                addDirToZipArchive(zos, file, zipEntryName);
            }
        } else {
            System.out.println("   " + zipEntryName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(fileToZip);
            zos.putNextEntry(new ZipEntry(zipEntryName));
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
            fis.close();
        }
    }
        public static void main(String[] args) throws Exception {
            FileOutputStream fos = new FileOutputStream("F:\\Zip.zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            addDirToZipArchive(zos, new File("F:\\Zip\\"), null);
            zos.flush();
            fos.flush();
            zos.close();
            fos.close();
        }
    ********************************************************************************************
    try{
        // Reference to the file we will be adding to the zipfile
        BufferedInputStream origin = null;
        // Reference to our zip file
        FileOutputStream dest = new FileOutputStream( filename );
        // Wrap our destination zipfile with a ZipOutputStream
        ZipOutputStream out = new ZipOutputStream( new BufferedOutputStream( dest ) );
        // Create a byte[] buffer that we will read data 
        // from the source
        // files into and then transfer it to the zip file
        byte[] data = new byte[ BUFFER_SIZE ];
        // Iterate over all of the files in our list
        for( Iterator i=files.iterator(); i.hasNext(); ) {
            // Get a BufferedInputStream that we can use to read the 
            // source file
            String filename = ( String )i.next();
            System.out.println( "Adding: " + filename );
            FileInputStream fi = new FileInputStream( filename );
            origin = new BufferedInputStream( fi, BUFFER_SIZE );
            // Setup the entry in the zip file
            ZipEntry entry = new ZipEntry( filename );
            out.putNextEntry( entry );
            // Read data from the source file and write it out to the zip file
            int count;
            while( ( count = origin.read(data, 0, BUFFER_SIZE ) ) != -1 ){
                out.write(data, 0, count);
            }
            // Close the source file
            origin.close();
        }
        // Close the zip file
        out.close();
    }catch( Exception e )
    {
        e.printStackTrace();
    }
    Otro Metodo___________________________________________________________________________
    @SuppressWarnings("unused")
        public static void compressDirectory(ZipOutputStream out,String sourceDir, String rootDir  ) throws IOException, FileNotFoundException {
            for (File file : new File(sourceDir).listFiles()) {
                if (file.isDirectory()) {
                    compressDirectory(out, sourceDir + File.separator + file.getName(), rootDir );
                } else {
                    ZipEntry entry = new ZipEntry(sourceDir.replace(rootDir, "") + file.getName());
                    out.putNextEntry(entry);

                    FileInputStream in = new FileInputStream(sourceDir + file.getName());
                    IOUtils.copy(in, out);
                    IOUtils.closeQuietly(in);
                }
            }
        }
***********************************************************************************************


*************************************************************************Pasa un numero a letra
    public class NumberToLetter {
        private static String Unidad[] = { "cero", "uno", "dos", "tres", "cuatro",
                "cinco", "seis", "siete", "ocho", "nueve", "diez", "once", "doce",
                "trece", "catorce", "quince", "dieciseis", "diecisiete",
                "dieciocho", "diecinueve", "veinte", "un" };
        private static String Decena[] = { "veinti", "treinta", "cuarenta", "cincuenta",
                "sesenta", "setenta", "ochenta", "noventa" };
        private static String Centena[] = { "cien", "doscientos", "trescientos",
                "cuatrocientos", "quinientos", "seiscientos", "setecientos",
                "ochocientos", "novecientos", "mil", "un millón", "millones",
                "un billón", "billones" };

        private static String getUnidad(long Numero, long original) {
            String aux = "";
            for (int p = 0; p <= 20; p++) {
                if (Numero == p) {
                    if(original >= 21000 && Numero == 1)
                        aux = Unidad[21] + " ";
                    else
                        aux = Unidad[p] + " ";
                    return aux;
                }
            }
            return " ";
        }

        private static String getDecena(long Numero, long original) {
            String aux = "";
            long pf = Numero % 10;
            long pi = (Numero / 10);
            int p = 0;
            boolean sal = false;
            // pf: parte final del numero,pi: parte inicial del numero
            while (p <= 8 & sal == false) {
                if (pi == p + 2) {
                    aux = Decena[p];
                    sal = true;
                }
                p++;
            }
            if (pf == 0)
                return aux + " ";
            if (Numero > 20 & Numero < 30)
                return (aux + getUnidad(pf, original) + " ");
            return aux + " y " + getUnidad(pf, original) + " ";
        }

        private static String getCentena(long Numero, long original) {
            String aux = "", aux2 = "";
            long pf = Numero % 100;
            long pi = (Numero / 100);
            int p = 0;
            boolean sal = false;
            while (p <= 10 & sal == false) {
                if (pi == p + 1) {
                    aux = Centena[p];
                    sal = true;
                }
                p++;
            }
            if (pf == 0)
                return aux;
            if (pf < 21)
                aux2 = getUnidad(pf, original);
            else
                aux2 = getDecena(pf, original);
            if (Numero < 200)
                return (aux + "to " + aux2 + " ");
            else
                return (aux + " " + aux2 + " ");
        }

        private static String getMil(long Numero, long original) {
            String aux = "";
            long pf = Numero % 1000;
            long pi = (Numero / 1000);
            if (Numero == 1000)
                return "mil";
            if (Numero > 1000 & Numero < 1999)
                aux = Centena[9] + " ";
            else
                aux = resolverIntervalo(pi, original) + " " + Centena[9] + " ";
            if (pf != 0)
                return (aux + resolverIntervalo(pf, original) + " ");
            return aux;
        }

        private static String getMillon(long Numero, long original) {
            String aux = "";
            long pf = Numero % 1000000;
            long pi = (Numero / 1000000);
            if (Numero > 1000000 & Numero < 1999999)
                aux = Centena[10] + " ";
            else if(pi == 1)
                aux = Centena[10] + " ";
            else
                aux = resolverIntervalo(pi, original) + Centena[11] + " ";
            if (pf != 0)
                return (aux + resolverIntervalo(pf, original) + " ");
            return aux;
        }

        private static String getBillon(long Numero, long original) {
            String aux = "";
            long pf = Numero % 1000000000;
            long pi = (Numero / 1000000000);
            if (Numero > 1000000000 & Numero < 1999999999)
                aux = Centena[12] + " ";
            else
                aux = resolverIntervalo(pi, original) + Centena[13] + " ";
            if (pf != 0)
                return (aux + resolverIntervalo(pf, original) + " ");
            return aux;
        }

        private static String resolverIntervalo(long Numero, long original) {
            if (Numero >= 0 & Numero <= 20)
                return getUnidad(Numero, original);
            if (Numero >= 21 & Numero <= 99)
                return getDecena(Numero, original);
            if (Numero >= 100 & Numero <= 999)
                return getCentena(Numero, original);
            if (Numero >= 1000 & Numero <= 999999)
                return getMil(Numero, original);
            if (Numero >= 1000000 & Numero <= 999999999)
                return getMillon(Numero, original);
            if (Numero >= 1000000000 & Numero <= 2000000000)
                return getBillon(Numero, original);
            return "<<El numero esta fuera del rango>>";
        }

        public static String toLetras(double Numero) {
            return toLetras(Numero, null);
        }
        public static String toLetras(double Numero, String maskPattern) {
            long valorOriginal = (long) Numero;
            String ret = "";
            
            if (Numero < 0) {
                ret = "menos ";
                Numero *= -1;
                valorOriginal = (long) Numero;
            }

            ret += resolverIntervalo((long) Numero, valorOriginal);
            
            int presision = DoubleUtil.getNumberPresision(maskPattern);
            double dec = Math.pow(10, presision);
            long aux2 = Math.round(DoubleUtil.roundToDecimals(Numero - valorOriginal, presision)*dec);
            if(aux2 > 0) {
                if(maskPattern == null)
                    ret += " con " + aux2 + "/100";
                else {
                    ret += " con " + aux2;
                }
            }
            
            ret = StringUtils.trim(ret);
            while(StringUtils.contains(ret, "  "))
                ret = StringUtils.replace(ret, "  ", " ");
            ret = StringUtils.capitalize(ret);
            return ret;
        }
        public static String timePeriodoString(Long p){
            long tmp = p / 1000;
            int ms = (int)(p - tmp * 1000);
            int d = (int)(tmp / (60 * 60 * 24));
            tmp = tmp - d * 60 * 60 * 24;
            int h = (int)(tmp / (60 * 60));
            tmp = tmp - h * 60 * 60;
            int m = (int)(tmp / 60);
            int s = (int)(tmp - m * 60);
            String ret = "";
            
            if(d > 0) ret = ret + d + " dia" + ((d==1)? " ":"s ");
            if(h > 0) ret = ret + h + " hora" + ((h==1)? " ":"s ");
            if(m > 0) ret = ret + m + " minuto" + ((m==1)? " ":"s ");
            if(s > 0) ret = ret + s + " segundo" + ((s==1)? " ":"s ");
            if(ms > 0 || ret == "")
                ret = ret + ms+ " ms ";
            return ret;
        }
    }
***********************************************************************************************
 

ManejoVistas

****************************************************************************************Cookies
    ***Crear Cookie 
    Cookie Galleta = new Cookie(NomCookie, ValCookie);


    ***Añadir cookie
    response.addCookie(Galleta);


    ***Caducidad de una cookie
    Galleta.setMaxAge(1*365*24*60*60); //Expira dentro de un año
    Galleta.setMaxAge(-1); //Expira al finalizar la sesion del cliente/navegador


    ***Eliminar cookie
    Galleta.setMaxAge(0); // Elimina cookie del cliente/navegador

    Cookie ck=new Cookie("user","");//deleting value of cookie  
    ck.setMaxAge(0);//changing the maximum age to 0 seconds  
    response.addCookie(ck);//adding cookie in the response


    ***Para recuperar las cokies
    Cookie[] galleta = request.getCookies();  //Recupera todas las cookies
    Cookie[] galleta = request.getCookies("kuki"); //Recuperar una cookie especifica

    Cookie ck[]=request.getCookies();  
    for(int i=0;i<ck.length;i++){  
     out.print("<br>"+ck[i].getName()+" "+ck[i].getValue());//printing name and value of cookie  
    }  


    ***Metodos de una cookie
    Public String getName()/Public void setName(String NameCookie)  //Extrae / fija el nombre de la
     cookie

    Public String getValue()/public void setValue(String valorCookie)  //Extrae/fija el valor de la
     cookie

    Public int getMaxAge()/public void setMaxAge(int segundosVida) //Extre/fija el numero de 
    segundos que la cookie permanece guardad en el disco del cliente

    public String getDomain()/public void setDomain(String Dominio) //Extrae/fija el dominio de de 
    los servidores con acceso a la cookie

    public String getPath()/public void setPath(String camino)  //Extrae/fija el directorio 
    raiz(virtual) de las paginas con acceso a la cookie

    public boolean getSecure()/ public void setSecure(boolean flagSeguridad) //Extrae/fija el 
    parametro de seguridad, Si el flag es true, la cookie solo sera enviadasi la conexion es 
    segura
***********************************************************************************************


***************************************************************************************Sesiones 
    ***Creacion de objeto de tipo sesion
    HttpSession sesion = request.getSession(true)
    con parametro true, crea una nueva sesion si la peticion n corresponde a ninguna sesion, Si es 
    false devolvera la sesion actual


    ***Configuracion de tiempo de caducidad
    setMaxInactiveInterval(int);


    ***Almacenar los objetos en la sesion
    HttpSession.setAttribute(String name,Object value);


    ***Consultar el valor de una sesion
    HttpSession.getAttribute(String name)


    ***Metodos disponibles para objeto session
    Public void removeAttribute(String nombre);

    Public Enumeration getAttribute();
    Extrae el nombre de todas las variables registradas

    Public String getId();

    Public boolean isNew();
    Devuelve true si empieza en la pagina

    Public long getCreationTime();

    Public long getLastAccessedTime();

    Public int getMaxInactiveInterval() / public void setMaxInactiveInterval(int Segundos)
    Extrae / fija segundos que deben transcurrir desde el ultimo accesopara que la sesion sea 
    cerrada

    public void invalidate (): anula esta sesión y desenlaza todos los objetos vinculados a ella.
    //Cerrar sesion
            sesion.invalidate();
***********************************************************************************************


****************************************GetParameterValues recuperar del request varios valores 
   //El elemento tecnologia puede tener varios
   //valores, por ello lo procesamos como un arreglo 
    String[] tecnologias=request.getParameterValues("tecnologia");
***********************************************************************************************


*****************************************************************************Codigos de estado 
    //Codigo de estado del servidor hacia el cliente
    //Se utilizan normalmente las contantes SC_OK, SC_NOT_FOUND,etc. Tambien se pueden utilizar el codigo de error
    response.setStatus(int codigo)
    //Codigo de error, utilizado para mostrar el error en un documento HTML, normalmente los navegadores
    response.sendError(int codigo, String mensaje)
    //Se redirige el cliente a otra pagina, el codigo de estado es el 302
    response.sendRedirect(String url)

    ***Codigos mas comunes
    200: La respuesta fue correcta
    204: Sin contenido
    301: El documento solicitado ha cambiado de ubicacion
    302: El navegador se mueve al nuevo url de manera automatica
    401: Sin autorizacion
    404: Recurso no encontrado
    500: Error interno del servidor
************************************************************************************************


********************************************************************************************JSTL
    JSTL significa JavaServer Pages Standard Tag Library, esta es una librería de los JSPs
    que extiende la funcionalidad básica de los mismos, agregando principalmente las
    siguientes librerías: core, xml, sql y fmt.

    Para poder utilizar las librerías dentro de un JSP debemos agregar la siguiente
    directiva: <%@ taglib uri=”http://java.sun.com/jstl/core”prefix=”c”%>

    ***Tags de despliegue de informacion:
        <c:out value="${persona.edad}">

    ***Tag de creacion y manipulacion de variables
        <c:set var="nombre" value="Carlos" scope="request">

    ***Tag de elemntos condicionales
        <c:if test="${i > 0}">  ,   <c:choose> <c:when test="${i > 0}">  , <c:otherwise><option value="${fecha}">${fecha }</option></c:otherwise>

        Ejemplo_______________________________________________________________________
        <c:choose>
            <c:when test="${param.enter=='1'}">
                pizza. 
                <br />
            </c:when>    
            <c:otherwise>
                pizzas. 
                <br />
            </c:otherwise>
        </c:choose>

    ***Tag de iteracion de elementos
        <c:forEach items="${peliculas }" var="pelicula"> </c:forEach>

    ***Tag de importacion de productos
        <c:import url="recursoInternoExterno" />

    ***Tag de redireccionamiento
        <c:redirect url="{newUrl}" />

    ***Tag de manejo de parametros
        <c:import url="c-import-param.jsp">
            <c:param name=”nombre” value = ”${param.select}”/>
        </c:import>

    Etiquetas para crear y manipula variables, JSTL nos va a permitir crear ese tipo de variables con el tag de set. Podemos declarar una nueva variable y
    especificar el valor de dicha variable con <c:set var=”nombre” value… y una vez que hemos creado la variable opcionalmente podemos declarar el
    alcance de esta variable, en este caso estamos especificando el alcance de manera explícita scope=”page” pero si omitiéramos este atributo
    scope=”page”/> sucedería que esta variable nombre se agrega de manera automática al alcance de page. Entonces con este tag vamos a cubrir la
    limitante de expression Language para crear variables y también por ello es que vamos a poder omitir el uso de scriptles, utilizando código mucho
    más fácil de mantener y de entender en un JSP.

    Posteriormente también tenemos tags para manejar elementos condicionales, por ejemplo el tag de if si es que necesitamos manejar algún código
    condicional, ej. <c:if test=”${ i > 0 }> o también el tag de choose, este es un tag muy similar al switch de java, y los casos del switch se pueden
    poner con <c:when test=”a”>.

    También tenemos etiquetas de iteración <c:forEach var=”persona” ítems=”{personas}>… para poder procesar los elementos de una lista. Cada
    uno de estos elementos se van a ir iterando y almacenando en el objeto persona. Esto va a ser un nuevo complemento respecto al Expression
    Language debido a que en un Expression Language no podemos iterar los elementos pero si podemos ir accediendo a cada uno de ellos de
    manera individual, entonces combinaremos JSTL con Expression Language para poder acceder a los elementos de una colección


    Ejemplo_______________________________________________________________________
        saludos.jsp
        <body>
            <h1>Ejemplo de JSTL</h1><br>
            <!--Acceso a JSTL Core-->
            <a href="jstlCore.html?opcion=2">Ir al JSP de JSTL Core</a><br><br>
        </body>
        ---------------------------------------------------------------------
        struts.xml
         <action name="saludar">
            <result>/WEB-INF/pages/saludos.jsp</result>
        </action>
        
        <action name="jstlCore">
            <result>/WEB-INF/pages/Bienvenido.jsp</result>
        </action>
        ---------------------------------------------------------------------
        Bienvenido.jsp
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@taglib prefix="s" uri="/struts-tags"%>
        <html>
            <head>
                <title><s:text name="form.titulo" /></title>
            </head>
            <body>
                <h1>JSTL Core</h1>
                <!--ManipulacionVariables -->
                <c:set var="nombre" value="Ernesto" />
                Variable nombre: <c:out value="${nombre}" /><br><br>
                Variable con c&oacute;digo html:<c:out value="<h1>Hola</h1>" escapeXml="true" /><br><br>
                <!--Codigocondicionado, uso del if-->
                <c:set var="bandera" value="true" />
                <c:if test="${bandera}">La bandera es verdadera</c:if><br>
                <!--codigo condicionado, similar al switch-->
                <c:if test="${param.opcion!=null}">
                    <c:choose>
                        <c:when test="${param.opcion==1}"><br>Opcion1 proporcionada<br>
                        </c:when>
                        <c:when test="${param.opcion==2}"><br>Opcion2 proporcionada<br>
                        </c:when>
                        <c:otherwise><br>Opcion proporcionada desconocida: ${param.opcion}<br>
                        </c:otherwise>
                        
                    </c:choose>
                </c:if>
                <!--Iteracion de un arreglo -->
                <% //Uso de Scriptlet
                    //Creamosel arreglo con un scriplet(aunque no es buena practica)
                    String[] nombres = {"Claudia", "Pedro", "Carlos", "Ana"};
                    //Compartimos el arreglo de nombres
                    //en el alcance (scope) de request 
                    request.setAttribute("nombres", nombres);
                    %><br>
                    Lista de Nombres en el arreglo:<br>
                    <ul><c:forEach varStatus="status" var="persona" items="${nombres}">
                            <li>${status.count} -Nombre: ${persona}</li>
                        </c:forEach>
                    </ul><br>
                    <a href="saludar.html">Regresar al Inicio</a>
            </body>
        </html>
        </body>

        Ejemplo_______________________________________________________________________
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    ------------------------------------------------------------------------------------------------
    <c:choose>
        <c:when test="fechaBusqueda eq fecha">
            <option value="${fecha}" selected="selected">${fecha }</option>
        </c:when>
        <c:otherwise>
            <option value="${fecha}">${fecha }</option>
        </c:otherwise>
    </c:choose>
    ------------------------------------------------------------------------------------------------
    <c:forEach items="${peliculas }" var="pelicula">
    </c:forEach>

    -----------------------------------------------------------------------------------------Ejemplo
    <select id="fecha" name="fecha" class="form-control">
        <c:forEach items="${fechas}" var="fecha">
            <c:choose>
               <c:when test="${fechaBusqueda eq fecha}" >
                  <option value="${fecha}" selected>${fecha}</option>   
               </c:when>
               <c:otherwise>
                  <option value="${fecha}">${fecha}</option>    
               </c:otherwise>
            </c:choose> 
        </c:forEach>             
    </select>
************************************************************************************************


******************************************Manejo de datos por medio de EL (Expression Languaje)
    ***Con la sintaxis de Expression Language únicamente debemos de especificar el nombre del bean 
    que vamos a utilizar y el nombre de la propiedad a la cual queremos acceder, la forma más 
    común de hacerlo es:
        ${nombreBean.nombrePropiedad} 
    Pero también tenemos otra forma  --> ${nombreBean[“nombrePropiedad”]}.
    Internamente el lenguaje de expresión (EL) lo que hace es almacenar sus atributos o beans como si fuera un mapa,
    entonces por ello podemos especificar el nombre de la propiedad o también podemos especificarlo entre corchetes,
    ya que puede funcionar como un índice para acceder al mapa y posteriormente recuperar el valor respectivo de la
    propiedad.

    Un detalle importante para hacer uso de este lenguaje es que los objetos JavaBeans a utilizar ya deben
    de estar agregados previamente en algún alcance, por ejemplo, utilizando el método setAttribute() en
    un Servlet. Si recordamos los alcances que tenemos en una aplicación web tenemos los alcances de
    page, resquest, session y application.

    ***El Expression Language nos va a permitir acceder a las propiedades de un JavaBean de manera anidada,
    por ejemplo, supongamos que tenemos una clase Alumno y a su vez tiene una propiedad que es otra
    clase llamada Dirección y esta clase Dirección tiene como atributo “calle”. Entonces, podemos observar
    que ya no tenemos que instanciar cada uno de estos objetos para ir recuperando la información, si no
    que de manera anidada y por medio de la notación de punto, podemos acceder a nuestro JavaBean
    llamado alumno, posteriormente con este atributo se manda a llamar el método getDireccion(), el cual
    regresa un objeto de tipo Dirección y finalmente se manda llamar el método getCalle(). Vamos a revisar
    más adelante algunos ejercicios para poder entender a más detalle esta notación anidada.

    ***Continuando con las características de este lenguaje, podemos acceder a propiedades que están en una colección
    o en un arreglo. Según hemos comentado, simplemente con el nombre del bean que queremos acceder
     ${listaPersonas[índice/llave ]} y entre llaves especificamos el índice de la colección a la cual queremos acceder o
    la llave.
    Por ejemplo, si es que el atributo es un mapa, esta notación nos va a permitir acceder de manera muy simple a
    los elementos que tengamos en una colección. Sin embargo no es posible iterar los elementos. Si queremos iterar
    cada uno de los elementos que tengamos en una colección debemos que utilizar JSTL (JSP Standard Tag Library) el
    cual estudiaremos más adelante.

    Otra característica que tenemos respecto al Expression Languaje es que el despliegue de la información se hace
    una conversión al tipo especificado de manera automática. Otra característica es que se maneja de manera 
    automática los valores nulos o vacíos convirtiéndolos  simplemente en cadena vacía. 
    También algunas conversiones ocurren de manera automática, por ejemplo ${ “x”>”y” } el valor que tenga X y el valor que tengas
    Y se convierten a tipos enteros y se hace una comparación utilizando los elementos X y Y. Otro ejemplo es ${ 3>=
    10/2 } de igual manera esta expresión se evalúa y es equivalente a tener la sintaxis del método out.print.

    ***El lenguaje de Expression Language nos permite acceder a algunos objetos implícitos, por ejemplo uno de ellos es el objeto cookie ${
    cookie.nombreCookie.value }.

    Uno de los objetos más importantes en JSPs es el objeto de pageContext. Este objeto contiene toda la información necesaria que requiere
    un JSP. Este objeto pageContext contiene la mayoría de los objetos implícitos que utiliza un JSP, en solo mostramos como ejemplo
    ${pageContext.session.id} para acceder al objeto http session y cómo podemos obtener el identificador de nuestra sesión (JSession), pero
    existen muchas más variables que se pueden acceder al revisar el API de los JSPs.

    También vamos a poder acceder a los parámetros que recibamos desde un formulario o desde una petición get o post. Para poder acceder
    a los parámetros de una petición HTTP podemos utilizar la variable implícita param o utilizar la variable paramValues, por ejemplo la
    sintaxis ${param.nombre}.

    También podemos acceder a los cabeceros HTTP por medio de las variables implícitas header o headerValues. Como ejemplo podemos
    usar la sintaxis ${header[“user-agent“]}. Y según hemos comentado también tenemos el objeto cookie ${cookie.nombreCookie.value}
    con el cual vamos a poder acceder directamente a las cookies que tengamos almacenadas en nuestro navegador web y simplemente
    poniendo el nombre de la cookie podemos acceder posteriormente al valor de la cookie respectiva.

    Para poder acceder a los atributos que hemos agregado en el alcance de sesión podemos utilizar el objeto sessionScope, 
    por ejemplo si hemos agregado a la sesión un objeto llamado rectángulo   podemos utilizar dos sintaxis una es especificando 
    ${sessionScope.rectangulo.area} que estamos buscando este objeto en el alcance de   sessionScope y posteriormente 
    podemos acceder a la propiedad respectiva de este JavaBean, pero también podemos utilizar la siguiente  notación ${rectangulo.area}, 
    en esta notación especificamos únicamente el nombre del JavaBean y posteriormente accedemos a la  propiedad respectiva. 
    Si no especificamos el alcance lo que va hacer es primero revisar si es que ya existe una variable llamada rectángulo
    en el alcance de pageScope, si no la encuentra aquí se sigue con los siguientes alcances, resquestScope, sessionScope y applicationScope
    en ese orden. Si no se encuentra en ninguno de ellos el objeto sería nulo, pero según hemos comentado en Expression Language nos va a
    permitir manejar el concepto de nulos y nos va a desplegar simplemente una cadena vacía.

    Ejemplo_ExpressionLanguaje____________________________________________________
    saludos.jsp
    <%@taglib prefix="s" uri="/struts-tags"%>
    <html>
        <head>
            <title><s:text name="form.titulo" /></title>
        </head>
        <body>
            <s:form action="Login">
                <s:textfield name="nombre" label="Nombre" />
                <s:textfield name="username" label="Username" />
                <s:password name="password" label="Password" />
                <s:textfield name="edad" label="Edad" />
                <s:textfield name="fechaNacimiento" label="Fecha de Nacimiento" />
                <s:submit value="Enviar" />
            </s:form>
        </body>
    </html>
    ------------------------------------------------------------------------------
    @Results({@Result(name="success", location="/WEB-INF/pages/Bienvenido.jsp")})
    public class LoginAction extends ActionSupport{
        private String nombre;
        private String username;
        private String password;
        private int edad;
        private Date fechaNacimiento;
        private Persona usuario;
        private List<String> estado = new ArrayList<String>();
        
        @Action("Login")
        @Override
        public String execute() throws Exception{
            usuario = new Persona();
            usuario.setNombre(nombre);
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setEdad(edad);
            usuario.setFechaNacimiento(fechaNacimiento);
            estado.add("Activo");
            estado.add("Inactivo");
            return SUCCESS;
        }
        
        
        
        public List<String> getEstado() {
            return estado;
        }
        public Persona getUsuario() {
            return usuario;
        }
        public int getEdad(){
            return edad;
        }
        public void setEdad(int edad){
            this.edad = edad;
        }
        public void setFechaNacimiento(Date fechaNacimiento){
            this.fechaNacimiento = fechaNacimiento;
        }
        public void setNombre(String nombre){
            this.nombre = nombre;
        }
        public void setPassword(String password){
            this.password = password;
        }
        public void setUsername(String username){
            this.username = username;
        }
    }


    ------------------------------------------------------------------------------
    Bienvenido.jsp
    <%@taglib prefix="s" uri="/struts-tags"%>
    <html>
        <head>
            <title><s:text name="form.titulo" /></title>
        </head>
        <body>
            Nombre: <strong><s:property value="usuario.nombre" /></strong><br />
            Username: <strong><s:property value="usuario.username" /></strong><br />
            Password: <strong><s:property value="usuario.password" /></strong><br />
            Edad: <strong><s:property value="usuario.edad" /></strong><br />
            Fecha de Nacimiento: <strong><s:property value="usuario.fechaNacimiento" /></strong>
            <h1>Expression Languaje</h1>
            Nombre El:${usuario.nombre}<br />
            Nombre del atributo del action: ${edad}<br />
            Password EL: ${usuario['password']}<br />
            Elementos de una lista: ${estado[0]}
            
            <h1>EL y Variables Implicitas</h1>
            <ul>
                <li>Nombre Aplicaci&oacute;n: ${pageContext.request.contextPath}</li>
                <li>Navegador del Cliente: ${ header["User-Agent"] }</li>
                <li>Id Session: ${cookie.JSESSIONID.value}</li>
                <li>Web Server: ${pageContext.servletContext.serverInfo}</li>
                
        </body>
    </html>
***********************************************************************************************


***********************************************************Manejo del Contexto de la Aplicacion
    ***Manejo dinamico del contesto de la aplicacion
    <a href="${pageContext.request.contextPath}/ServletControlador">Link al servletcontrolador que despliega las variables</a>
***********************************************************************************************


*************************************************************Manejo de Java Beans en JSP x TAGS
    ********************************Para poder usar un JavaBean en una página JSP,
    se exige declararlo en la página, para ello se 
    utiliza la etiqueta:
    <jsp:useBean id="nombre javabean en jsp" scope="ambito" class="nombre ” type= "
    "packageName.className" beanName="packageName.className | <%= expression >" > 
    </jsp:useBean>

    Entre los tipos de scope:
    1.-Session
    2.-Request
    3.-Page (por defecto)

    ***Ejemplo:
    <jsp:useBean id="per" scope="request" class="Modelo.Persona” ></jsp:useBean> "

    -----------------------------------------------------------------------------------------------
    **********************************Uso de las etiquetas setProperty
    Para escribir el valor de una propiedad de un JavaBean se utiliza dentro del JSP la etiqueta:

    <jsp:setProperty
    name="id_JavaBean" property="nombre_propiedad" value="valor" param="nombre en el JSP">
    </jsp:setProperty>

    ***Ejemplo
    <jsp:setProperty name="per" property="CI"  param="Cedula" ></jsp:setProperty>

    ------------------------------------------------------------------------------------------------
    Caso 1: el parametro de los name de los obj de JSP se nombran igual a las propiedades Bean:
    <jsp:setProperty
    name="id_JavaBean en JSP" property="*" 
    </jsp:setProperty>
    ------------------------------------------------------------------
    Caso 2: el parametro de los name de los obj de JSP son diferentes a las propiedades Bean:
    <jsp:setProperty>
    name="id_JavaBean en JSP" property="ID del atributo Bean" param="name JSP " 
    </jsp:setProperty>

    -----------------------------------------------------------------------------------------------
    **********************************Uso de las etiquetas getProperty
    Uso de la etiqueta getProperty
    Para leer el valor de una propiedad de un JavaBean se utiliza dentro del JSP la etiqueta,
    <jsp:getProperty>
    name="id_JavaBean" property="nombre_propiedad" value="valor">
    </jsp:getProperty>

    **********************************A considerar scope o alcance

    Caso 1: Scope: Request
    *** La pagina destino debe contener el tag del bean
    <jsp:useBean id="per" scope="request" class="Modelo.Persona"></jsp:useBean>
    *** La pagina origen debe enviar el request a la pagina destino
    request.getRequestDispatcher("destinoBean.jsp").forward(request, response);

    Caso 2: Scope: session
    *** Se cambia el alcance o scope del bean a session
    <jsp:useBean id="per" scope="session" class="Modelo.Persona"></jsp:useBean>
    *** Se envia en request desde la pagina origen a la de destino
    request.getRequestDispatcher("destinoBean.jsp").forward(request, response);
    *** en la pagina destino se llama a la variable session
    Persona per =(Persona)session.getAttribute("per");
    *** No hace falta el tag del bean en la pagina destino
***********************************************************************************************


********************************************************************************************JSP
    ***Directivas
    Le dice al motor JSP que incluya ciertos paquetes y clases
    <%@ page import=##javax.naming.*"%> "
    <%@ page import="javax.rmi.PortableRemoteObject" %>


    ***Declaraciones
        En las declaraciones también es código Java, pero este código lo vamos a utilizar para 
    declarar ya sea variables o métodos que pertenezcan a la clase del servlet generado, y a 
    diferencia del código Java que está en un Scriptlets en el cual todas las variables que 
    declaremos son locales al método service(), en el caso de las declaraciones el código que 
    agreguemos, si es que declaramos una variable, se vuelve una variable de instancia debido 
    a que se está agregando como una variable de la clase del servlet generado y no únicamente 
    como una variable local a cierto método. 
        También los métodos que agreguemos se vuelven métodos que son parte de la clase del servlet 
    generado aunque no es muy utilizado este concepto de declaraciones ya que lo más común es 
    que desde un Scriptlets mandemos a llamar funciones o utilicemos variables que están 
    definidas en otras clases y no dentro de nuestro JSP, entonces en el caso de las declaraciones 
    lo vamos a utilizar siempre y cuando necesitemos agregar ya sea variables o métodos que 
    pertenezcan a nuestro servlet generado a partir del JSP.
        Permiten configurar variables para su uso posterior en expresiones o scriptlets.
        Sintaxis es: <%! codigoJava %> .
    <%! double bonus; String text; %>
    <%! String strMult, socsec; %>

        Ejemplo_______________________________________________________________________

            <%--Agregamos declaraciones --%>
            <%!
                //Declaramos un avariable con su get
                String usuario = "Elvis";
                public String getUsuario(){
                    return this.usuario;
                }
                
                //Declaramos un contador de visitas
                private int visitas = 1;
            %>
            <html>
                <head>
                    <title><s:text name="form.titulo" /></title>
                </head>
                <body>
                    <h1>Uso de declaraciones</h1>
                    Usuario por medio del atributo: <%= this.usuario %>
                    <br>
                    Usuario por medio del metodo: <%= this.getUsuario() %>
                    
                    <h1>Contador de visitas desde que se reinicio el servidor</h1>
                    <%= this.visitas++ %>
                </body>
             
            </html>

    ***Scriptlets
        Un Scriptlet contiene código Java a diferencia de una expresión que únicamente la utilizamos 
    para imprimir información a nuestro cliente, un Scriptlets puede contener código Java que 
    no necesariamente va a visualizar nuestro cliente debido a que si este código Java no manda 
    imprimir nada al cliente, entonces nuestro cliente no va a visualizar la información que este 
    procesando.
        Es importante mencionar que el código generado dentro de un Scriptlets se inserta dentro del 
    método service() del servlet generado a partir del JSP que se haya compilado.
        Puede tener declaraciones de variables, llamadas a funciones y en general puede tener cualquier 
    lógica siempre y cuando respetemos que este código está dentro de otro método, en este caso 
    del método service()
        Permiten embeber segmentos de codigo java dentro de una pagina JSP.
        Sintaxis de un Scriptlet es <% codigoJava %>
    <%
    strMult = request.getParameter("MULTIPLIER");
    socsec = request.getParameter("SOCSEC");
    %>


    ***Variables Predefinidas
        Dentro de un JSP podemos utilizar objetos que ya están instanciados para que podamos usarlos 
    de manera inmediata. Por ejemplo tenemos los objetos request, response, out, el objeto sesión, 
    application entre otros. 
        Estos objetos ya los tenemos disponibles en un JSP y no tenemos que instanciarlos, simplemente 
    ponemos el nombre de nuestra variable y por medio del operador punto (.) podemos acceder a los 
    atributos y métodos del objeto del tipo HTTPServletRequest. Lo mismo sucede con el método response, 
    simplemente con este nombre de variable podemos acceder a los atributos y métodos de este objeto 
    HTTPServletResponse. 
        La variable out es equivalente al objeto PrintWriter que hemos instanciado dentro de los 
    Servlets, en este caso existe una pequeña variante ya que esta variable out es de tipo JspWriter.
        La variable session es una variable de tipo HttpSession y con esta variable podemos obtener 
    información que ya hemos agregado a nuestra sesión, por ejemplo por medio de un Servlet.
        Tenemos también la variable llamada application, esta es equivalente al objeto ServletContext, 
    que en un servlet podemos obtener a partir del método getServletContext(). Este concepto lo vamos 
    a ver posteriormente a más detalle.


    ***Comentarios
        Estos comentarios tienen la ventaja de no aparecer al inspeccionar el codigo fuente de una pagina
    pagina, son comentarios de cara al servlet
        <%--Agregamos declaraciones --%>


    ***Expresiones
    Una expresión que puede ser cualquier expresión valida que a final de cuentas va a ser 
    equivalente a poner la sintaxis out.println y lo que tengamos en nuestra expresión lo va a 
    mandar al cliente.
        La expresión puede ser un valor como puede ser una cadena, una expresión matemática, una suma, 
    una resta etc. o también puede ser el resultado de una llamada a una función, pero si mandamos 
    a llamar una función el resultado de esta función no puede ser de tipo void, debe de regresar 
    algún valor ya que como podemos observar el resultado de evaluar esta expresión se va a mandar
    a nuestro cliente entonces por ello esta expresión debe de regresar algún resultado.
        Permiten recuperar dinamicamente o calcular valores a insertar directamente en la pagina JSP
        Sintaxis <%=expresión%>
    <%= record.getSocSec() %>

        Ejemplo_______________________________________________________________________
        <body>
            <h1>JSP de Expresiones</h1>
            Concatenaci&oacute;n: <%= "Saludos"+ "JSP"%><br>
            Operaci&oacute;n Matem&aacute;tica: <%= 2* 3/ 2%><br>
            Session id: <%= session.getId() %><br><br>
            <a href="saludar.html">Regresar al inicio </a>
        </body>

        Ejemplo_______________________________________________________________________

        <s:form action="Login">
            <s:textfield key="form.usuario" name="nombre" />
            <s:password key="form.password" name="clave" />
            <s:submit key="form.boton" name="submit" />
        </s:form>
        ------------------------------------------------------------------------------
        <body>
            <h1>Resultado de ProcesarFormulario</h1>
            Usuario: <%= request.getParameter("nombre") %><br>
            Password: <%= request.getParameter("clave") %><br><br>
            <a href="saludar.html">Regresar al inicio </a>
        </body>


    ***Directivas en JSP
        Las directivas nos permiten controlar el comportamiento de un JSP, por ejemplo las clases 
    que vamos a utilizar dentro de un JSP y hacer el import de clases Java, especificar el tipo 
    MIME con el que vamos a responder a nuestro cliente.
        También con estas directivas podemos especificar si un JSP va a participar en el concepto 
    de sesiones, o podemos especificar el tamaño del buffer de salida, especificar el JSP de error 
    en caso de que tengamos alguna excepción dentro de nuestro JSP, o indicar si nuestro JSP va a 
    manejar el concepto de multihilos, entre varias características más.

        Import: El atributo de import dentro de la directiva page nos va a permitir especificar 
        cuáles son las clases que vamos a importar dentro de nuestro JSP e indirectamente a nuestro 
        servlet generado a partir de la solicitud a nuestro JSP.
            La Sintaxis: <%@page import=”paquete.Clase1, paquete.ClaseN” %>. 

        ContentType:  Este atributo nos permite especificar el tipo de respuesta a nuestro cliente 
        web.
            Sintaxis: <%@page contentType=”MIME-Type” %> . 

        Session: <%@page session=”true” %>  indicar si el JSP va poder acceder al objeto sesión que 
        se haya creado anteriormente por ejemplo desde un JSP o desde otro servlet. Por default un JSP 
        está configurado para que podamos acceder al objeto session.

        Atributo isELIgnored: <%@page isELIgnored=”false” %> significa es que si queremos deshabilitar 
        el manejo de expression language, tendríamos que indicarlo por medio de esta directiva page 
        indicando en el atributo isELIgnored el valor de true.

        Atributo buffer: <%@ page buffer=”tamañoEnKb” %> Con este atributo buffer podemos especificar 
        el tamaño en Kb que puede contener nuestro buffer del JSP y si llegamos al tamaño especificado 
        en este JSP entonces se hace un flush o vaciado de manera automática de todo el flujo que hayamos 
        agregado a nuestro printWriter o a nuestro output servlet dependiendo el caso.

        ErrorPage:<%@page errorPage=”url_relativo_al_JSP_de_error” %> con el atributo erroPage vamos a 
        especificar cual va a ser el JSP que va a manejar las excepciones en caso de que el JSP provoque 
        un error, y para configurar el JSP que va a manejar los errores tenemos que indicar la directiva 
        isErrorPage con la siguiente sintaxis: <%@ page isErrorPage=”true” %> indicando que ese JSP si es 
        el JSP que va a manejar los errores, por default los JSPs tienen el valor de esta directiva igual 
        a false debido a que los JSPs no van a manejar las excepciones de manera automática sino hay que 
        configurarlos para que puedan acceder a la pila de errores.

        IsThreadSafe: con sintaxis <%@ page isThreadSafe=”true” %> Por default el contenedor de servlets,
        considera a un JSP como seguro para ser accedido por múltiples hilos, es decir, el valor por 
        default es true. 
        
        Extends <%@ page extends=”paquete.NombreClase” %> nos va a permitir heredar de una clase según 
        especifiquemos en ese atributo y por lo tanto nuestro JSP va a heredar las características de la 
        clase que especifiquemos. 

    ***Paginas de error JSP
    Se llevan a cabo a través del objeto exception, se siguen los siguientes pasos:

    1.- Escribir un servlet, JSP u otro componente para que lance excepciones en determinadas 
    condiciones, desde una JSP podria ser:
        
        public Object metodo() throws NullPointerException{
        
        }

    2.- Escribir una JSP que sera la pagina de error usando la directiva page con 
    isErrorPage = "true". Es decir que si ocurre un error en otras JSP que usen el 
    componente, se ejecutara esta agina:

        <%@ page isErrorPage="true" import="java.util.*" %>

    3.- En la pagina de error utilizar el objeto exception. Para obtener informacion de la 
    excepcion:

        <%= exception.toString() %> <% exception.printStackTrace(); %>

     
    4.- En la JSP que usan el componente, indicar que pagina se ejecutara si se produce algún 
    error, mediante la directiva page, estableciendo errorPage a la pagina de error

        <%@ page isThreadSafe="false" import="java.util.*" errorPage="error.jsp" %>


    ++++++++++++++++++++++++++++Tag para incluis contenido de otra pagina+++++++++++++++++++++++++++
    Se reemplaza footer de la pagina y se incluye footer.jsp con el tag --><jsp:include>
        <jsp:include page="../includes/footer.jsp"></jsp:include>
***********************************************************************************************


***************************************************************Manejos de Cabeceros con Request
    Los siguientes métodos nos permiten procesar los cabeceros y estos métodos están definidos 
    dentro del API de los Servlets en la clase HttpServletRequest. 

    El objeto HttpServletRequest tiene como métodos: 
        getHeader() 
        getHeaders()
        getHeaderName().
        getCookies() 
        getAuthType()
        getRemoteUser() 
        getContentLength()
        getContentType()
        getDateHeader() 
        getIntHeader()
        getMethod() 
        getRequestURI() 
        getQueryString() nos permite obtener los parámetros que vienen posterior al signo de 
        interrogación en una petición get y también en dado caso el método 
        getProtocol() el cual nos indica si se nos ejecutó un método HTTP, FTP etc.

        //Procesamos los headers relacionados con la
        //informacionde la peticion
        Metodo      --> metodoHttp=request.getMethod();
        URI         --> request.getRequestURI();             
        Host        --> request.getHeader("Host")
        Navegador   --> request.getHeader("User-Agent")
        //Procesamiento de los heades de la peticion
        Enumerationcabeceros = request.getHeaderNames();
        while(cabeceros.hasMoreElements()) {
            StringnombreCabecero=(String) cabeceros.nextElement();
            out.println("<b>"+nombreCabecero+"</b>: ");
            out.println(request.getHeader(nombreCabecero));out.println("<br>");
        }   
***********************************************************************************************


***************************************************************Manejos de Cabeceros con Request
    Los cabeceros de respuesta nos van a permitir indicar al navegador web como debe 
    comportarse ante la respuesta por parte de nuestro servidor web al usuario final.

    Para indicar el tipo de respuesta se utiliza lo que se conoce como los tipos MIME. MIME es 
    el acrónimo de (Multipurpose Internet Mail Extensions). Los tipos MIME son un conjunto de 
    especificaciones con un objetivo en específico, el cual es intercambiar archivos por medio 
    de Internet utilizando el protocolo HTTP y este tipo de archivos pueden ser texto, audio, 
    video o cualquier otro tipo de información, ya sea a nivel de texto plano o información 
    binaria (el archivo original). Hay una extenza variedad de tipos MIME

    1.- lo que tenemos que hacer es especificar del lado del servidor el tipo que enviaremos 
    a nuestro cliente, es decir, el tipo MIME application/msword.
    2.-Para agregar cabeceros a nuestra respuesta utilizaremos el objeto response y el método 
    setHeader. este método tienen dos parámetros el primer parámetro es el nombreCabecero y 
    el segundo parámetro es el valorCabecero. Similar a este método setHeader, existen los 
    métodos setDateHeader y setIntHeader, la diferencia entre estos métodos es que estamos 
    agregando Headers de tipo Date o de tipo Int respectivamente. Si no queremos que sustituya 
    este valor lo que tenemos que hacer es utilizar el método addHeader o también los similares 
    addDateHeader y addIntHeader estos métodos agregan un nuevo valor en lugar de reemplazar 
    los ya existentes.
    El método setContentType el cual se utiliza para especificar el tipo MIME según la tabla.

    Cabeceros Cache-Control y Pragma se utilizan para el control del chache y también se llega 
    a combinar con el cabecero de Expires y al combinar estos tres cabeceros lo que vamos hacer 
    es indicar a nuestro navegador web que no va a guardar cache de nuestros Servlets o JSPs.
    Content-Enconding el cual indica la codificación del documento que estamos desplegando y 
    también el cabecero Content-Length para especificar el tamaño del contenido a enviar.
    Last-Modified indica el tiempo en el documento fue modificado por última vez.
    El cabecero de Refresh indica los segundos en que el navegador debe recargar la página 
    mostrada, este cabecero suele ser muy útil en caso de que necesitemos hacer una 
    actualización cada cierto tiempo de las páginas que está visualizando nuestro cliente y 
    así actualizar la información de manera automática.

        Ejemplo_______________________________________________________________________
        <body>
            <h1>Ejemplo Cabeceros de Respuesta</h1>
            <a href="/CabecerosRespuesta/GeneracionExcelServlet">Link al Servletque genera un excel</a>
            <br>
            <a href="/CabecerosRespuesta/FechaServlet">Link al Servletque actualiza la fecha</a>
        </body>

        GeneracionExcelServlet_java____________________________________________________
        protected void doGet(HttpServletRequestrequest, HttpServletResponseresponse)throws ServletException, IOException{
            try(PrintWriter out=response.getWriter()) {
                //Especificamos el tipo del documento de respuesta (MIME)
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; filename=excelEjemplo.xls");
                //Para un uso más profesional de excelusar: https://poi.apache.org/
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-store");
                response.setDateHeader("Expires", -1);
                //Desplegamos el contenido en un excel
                //simplemente modificando el contentType
                out.println("\tValores");
                out.println("\t1");
                out.println("\t2");
                out.println("Total\t=SUMA(B2:B3)");
            }    
        }
        FechaServlet_java_____________________________________________________________
        protected void doGet(HttpServletRequestrequest, HttpServletResponseresponse)throws ServletException, IOException{
            try(PrintWriter out=response.getWriter()) {
                response.setContentType("text/html;charset=UTF-8");
                response.setHeader("refresh", "1");
                //dado en segundos
                //Obtenemos la fecha actual y le aplicamos un formato 
                Date fecha =newDate();
                //API para utilizar la clase SimpleDateFormat
                //https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
                //SimpleDateFormat formateador = new SimpleDateFormat("dd'de' MMMM 'de' yyyy'a las' HH:mm:ss");
                SimpleDateFormat formateador =new SimpleDateFormat("'Hora actualizada' HH:mm:ss");
                String fechaConFormato=formateador.format(fecha);
                out.println("<html>");
                out.println("<head>");
                out.println("<title>ServletFechaServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Fecha actualizada: "+fechaConFormato+"</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
***********************************************************************************************


****************************************************************recibir parametros de un hidden

        getRequest().setAttribute("__RiaPageId", "addendaAfipForm");
        <input type='hidden' name='__RiaPageId' value='addendaAfipForm' id='__RiaPageId' />

        String datoUno = request.getParameter("dato1");
***********************************************************************************************


***********************************************Almacenar Atributo de Session en un campo hidden
    
    <input type="hidden" name="validacion" value="${sessionScope.validacion}" id="validacion" />
***********************************************************************************************


*********************************************fomulario añade campo de forma dinamica con Jquery
    ***Referencia repConsComFacturasList.jsp

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script >
        //Se añade funcion de Jquery para que añada elementos de forma dinamica
        $(document).ready(function (){
            $("#btnAgregar").click(function(){
                var num = $("input[type=text]").length;
                var numeroSiguiente = num + 1;
                var elementoNuevo = $("#contrato" + num).clone().attr('id','contrato'+numeroSiguiente).attr("name", "contratos");
                var etiquetaNueva = $("label[for=contrato"+num+"]").clone().attr("for","contrato"+numeroSiguiente).text("Factura " + numeroSiguiente + ": ");
                $("#contrato" + num).after(elementoNuevo);
                elementoNuevo.before(etiquetaNueva);
                etiquetaNueva.before("<br />");
            });
        });
        
            $(":text").each(function(){ 
                $($(this)).val('');
        });
    </script>
***********************************************************************************************



JUnit


*****************************************************************************************@Test
********************************************************************************assertEquals()
    //aNOTACION QUE INDICA QUE ES UN METODO DE PROUEBA
    @Test
    public void testSuma() {
        Calculadora calc = new Calculadora();
        try {
            int resultado = calc.sumar(3, 2);
            int esperado = 6;
            //Metodo que indica si son iguales el resultado con lo esperado
            assertEquals(esperado, resultado);
        } catch (CalculadoraException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
**********************************************************************************************


***************************************************************************************@Before
    //Before, es una precondicion para realizar un test, se ejecuta antes de cada test
    @Before
    public void before() {
        System.out.println("Before()");
        calc = new Calculadora();
    }
**********************************************************************************************


****************************************************************************************@After
    //After, es una postcondicion para realizar un test, se ejecuta despues de cada test
    @After
    public void after() {
        System.out.println("After()");
        calc.clear();
    }
**********************************************************************************************


******************************************************************************Metodos assert()
    //Comprueba si dos elementos son iguales
        assertEquals(5, 5);
    //Compara los dos primeros valores y el tercer parametro es el margen de diferencia que puede tener        
        assertEquals(4.0, 3.75, 0.35);  
    //Si se quiere personalizar el error 
        assertEquals("5 es diferente de 4", 5, 4);
    //Comparacion de dos arreglos
        Integer[] listaA = new Integer[] {1,2,3};
        Integer[] listaB = new Integer[] {1,4,3};
        assertArrayEquals(listaA, listaB);
    //Para comprobar que una condicion sea falsa
        int a = 2;
        int b = 3;
        assertFalse(a == b);
    //Para comprobar si dos objetos son el mismo
        Date fecha = new Date();
        Date fecha1 = new Date();
        //assertSame(fecha, fecha1); //No es valido
        assertEquals(fecha, fecha1);
**********************************************************************************************


*****************************************************************************JUnit excepciones
    //Comprobando una excepcion
    @Test(expected = ArithmeticException.class)
    public void testDicXCero() {
        Calculadora calc = new Calculadora();
        calc.div(5,0);
    }
**********************************************************************************************


*************************************************************************@Test(timeout = 3000)
    //Comprobando el tienpo que trada en ejecutarse
    @Test(timeout = 3000)
    public void textTiempoOptimo() {
        Calculadora calc = new Calculadora();
        calc.tmiempoOptimo();
    }
**********************************************************************************************


***************************************************************************Clase beforeClass()
    //Se ejecuta una sola vez a diferencia de @Before, debe ser static
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Clase beforeClass");
        calc = new Calculadora();
    }
**********************************************************************************************


****************************************************************************Clase afterClass()
    //Se ejecuta una sola vez a diferencia de @After, debe ser static
    @AfterClass
    public static void afterClass() {
        System.out.println("Clase afterClass");
    }
**********************************************************************************************


***************************************************************@RunWith Pruebas parametrizadas
    Cuando anotamos una clase con @RunWith(Parametrized.class) se crearán tantas instancias y se 
    ejecutarán tantas veces los métodos de tests como parámetros devolvamos en el método anotado @Parameters

    @RunWith(value = Parameterized.class)
    public class CalculadoraParametrosTest {
        
        @Parameters
        public static Iterable<Object[]> getData(){
            List<Object[]> objs = new ArrayList<>();
            objs.add(new Object[] {2,5,7});
            objs.add(new Object[] {1,4,5});
            objs.add(new Object[] {2,4,8});
            return objs;
        }

        private int a, b , exp;

        public CalculadoraParametrosTest(int a, int b, int exp) {
            super();
            this.a = a;
            this.b = b;
            this.exp = exp;
        }
        
        @Test
        public void testAdd() {
            Calculadora calc = new Calculadora();
            int result = calc.add(a, b);
            assertEquals(exp, result);
        }
        
    }
    Suite.--------------------------------------------------------------------------------------
        
        Permite crear manualmente una suite que contenga tests de distintas clases. Las clases que forman parte de la suite
        se incluyen en la anotación @Suite, que no solo indica las clases que se incluirán sino también el orden de ejecución
        de los tests.

        @RunWith(Suite.class)
        @Suite.SuiteClasses({
          LoginTest.class,
          MenuTest.class,
          LogoutTest.class
        })
         
        public class MyTestSuite {
          // void
        }
**********************************************************************************************


ZeniUtilitarios

******************************************************************************************************Zeni
******************************************************************************Llamada de funcion en oracle
    ArrayList nrosNoGenerados =  (ArrayList)BaseModel.getManager().executeSQL("select NROS_FACTURAS_NO_GENERADAS from dual");


    Object listac =  (Object) contratoManager.executeSQL("select GET_FECHA_ALTA(19977978) from dual");             
    String  prueba = listac.toString();
    System.out.println("La prueba es: "+prueba);
**********************************************************************************************************


******************************************************************************************************Zeni
***********************************************************************************Dar formato a un String
    ddnReports[0] = String.format("%s-%s.jasper",
                            ddn.getTipoDocumentoNegocio().getFamilia(),"ASP");
**********************************************************************************************************


******************************************************************************************************Zeni
*****************************************************************Dar formato fecha en la vista a un String
    /*<ria:simplegridcolumn label='Fecha Emision' width="50" id="fechaEmision" style="text-align:center;paddind:10px;">
        <fmt:formatDate type="date" value="${item.fechaEmision}" pattern="dd/MM/yyyy" />
    </ria:simplegridcolumn>
    <ria:simplegridcolumn label='Fecha Vcto.' object="${item.fechaVenPago}" width="50" id="fechaVcto" style="text-align:center;paddind:10px;">
        <fmt:formatDate type="date" value="${item.fechaVenPago}" pattern="dd/MM/yyyy" />
    </ria:simplegridcolumn>*/
**********************************************************************************************************


*******************************************************************************************************Zeni
**********************************************************************Apache Jexl (Java EXpresion Language)
    ****Añade un objeto al contexto
    // addTransients(contrato);
    JexlContext jexlContext = JexlUtils.getContext(new Object[] { this, documentoDeNegocio, contrato });
    //Extrae texto de objeto del contexto
    String clausulaTxt = JexlUtils.expandText(jexlContext, cdn.getTexto());
***********************************************************************************************************


*******************************************************************************************************Zeni
**************************************************************************Utilizando query y parametros JPA
    HashMap<String, Object> queryParams = new HashMap<String, Object>();

    queryParams.put("idtipoDocumentoNegocio", documentoDeNegocio
            .getTipoDocumentoNegocio().getId());
    queryParams.put("idSucursal", documentoDeNegocio.getContrato()
            .getSucursalAdministrativa().getId());
    queryParams.put("idClienteVendedor", contrato.getVendedor().getCliente().getId());
    queryParams.put("idClienteComprador", contrato.getComprador().getCliente().getId());
    queryParams.put("idBolsa", null);

    clausulasAll = (List<ClausulaDocumentoNegocio>) dao
            .findByNamedQuery("clausulasDocumentoNegocioByCliente", queryParams);
***********************************************************************************************************


*******************************************************************************************************Zeni
**************************************************************************Guardar en BD hibernate con save
    
    this.mATProducto = (MATProducto) manager.save(mATProducto);
***********************************************************************************************************


*******************************************************************************************************Zeni
*******************************************************************************Obtener entidad en Hibernate

    this.mATProducto = (MATProducto) manager.get(MATProducto.class, id);
***********************************************************************************************************


*******************************************************************************************************Zeni
********************************************************************************salvar un mensaje en struts
    
    saveMessage(getText(key));
***********************************************************************************************************


*******************************************************************************************************Zeni
*********************************************************Creacion de DataSource de reporte y envio de email
    to = obtenerMails(ddn.getContrato(), true);
    ccopia = obtenerMails(ddn.getContrato(), false);
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
**********************************************************************************************************


*******************************************************************************************************Zeni
*****************************************************************Captura una parte de la cadena segun slash

    String reportName = reportPath.substring(reportPath.lastIndexOf("/")+1).replaceAll(".jasper", "");
***********************************************************************************************************


*******************************************************************************************************Zeni
***************************************************************************select utilizando tags de struts
    <td>
        <s:action name="mercadoes" var="mercados" ignoreContextParams="true"/>
        <s:select key="filter.mercado" list="%{#mercados.mercadosMAT}"
            listKey="id" listValue="nombre" emptyOption="true" required="false" cssClass="text size150"/>                   
    </td>
***********************************************************************************************************


*******************************************************************************************************Zeni
*******************************************************************************Se añade condicion al filtro
            detalleProductosFilter.addCondition("matProductoPadre.id", "=", "matProductoPadreId");
            detalleProductosFilter.setMatProductoPadreId(mATProducto.getId());
***********************************************************************************************************


*******************************************************************************************************Zeni
********************************************************************************generacion de reporte jasper
************************************************************************************************************
    JasperReportUtil.doReport(getRequest(), getResponse(),
     "reports/Boleto/slipContratos.jasper", JasperReportUtil.FORMAT_PDF, getReportParams(),
      null, String.format("attachment;filename=\"Slip Contrato.pdf\"", ""), null, null, 
      null, null, rptList);
************************************************************************************************************


********************************************************************************************************Zeni
Generacion de Map************************************************************GeneracionMapInternacionalizado
************************************************************************************************************
    /**
    * Devuelve un Map internacionalizado con los valores de operatorias para el Listado Estadistico
    * @return map de valores internacionalizados
    */
    public static Map <Character, String> getOperatoriasEstadistico() {
        ResourceBundle rb = getBundle();
        Map <Character, String> values = new HashMapOrderByValue <Character, String>();
        values.put('C', rb.getString("valor.granos"));
        values.put('M', rb.getString("valor.mat"));
        values.put('S', rb.getString("valor.subproductos"));
        
        return values;
    }
************************************************************************************************************


********************************************************************************************************Zeni
****************************************************************************************Lista de objetos Map
***************************************************************************Iteracion de lista de objetos Map
************************************************************************************************************
    ******creacion de una lista de mapas
        List<Map<String, Object>> turnosCupos = new ArrayList<Map<String, Object>>();


    ******Iteracion de la lista de map
        Iterator<Map<String, Object>> iter = turnosCupos.iterator();
            while(iter.hasNext()){
                Map<String, Object> str = iter.next();
                String grano = (String) str.get("codGrano");
                if(!prod.getCodigoONCCA().equals(grano)){
                    iter.remove();
                }
            }
************************************************************************************************************


********************************************************************************************************Zeni
************************************************************************Invocacion de un metodo desde un JSP
**************************************************************Llena select por medio del llamado a un metodo
************************************************************************************************************
    <TD><s:select id="contrato_operatoria" key="operatoria" 
        label="Operatoria"
        multiple="true"
        list="%{@com.zeni.app.util.PropertyValues@getOperatorias()}"
        required="false" emptyOption="true" cssClass="text size120"
        style="width:120px;" />
    </TD>
************************************************************************************************************


********************************************************************************************************Zeni
*************************************************Invocacion de un metodo desde un JSP por medio de un action
**************************************************************Llena select por medio del llamado a un metodo
************************************************************************************************************
        
    Action -->OperadorDeMesaAction---------------------------------------------------------------------
        public List<OperadorDeMesa> getTiposOperadoresDeMesaVigentes() {
            return operadorDeMesaManager.getAll('v');
        }

    ---------------------------------------------------------------------------------------------------
    <td valign="top" style="padding-left: 12px"><s:action
        name="operadorDeMesas" var="operadorDeMesas" /> <s:select
        key="filter.operadorDeMesaId" label="Op. Mesa"
        list="%{#operadorDeMesas.tiposOperadoresDeMesaVigentes}"
        listKey="id" listValue="nombre" emptyOption="true"
        required="false" cssClass="text size120" />
    </td>
************************************************************************************************************


********************************************************************************************************Zeni
**********************************************************Mostrar Dato en Grilla segun Validacion de un Item
    
    <ria:simplegridcolumn label='PLAZO' object="${item.plazo == 0 ? 'Contado Inmediato' : item.plazo}" width="130" id="PLAZO" style="text-align:center;paddind:10px;"/>"
************************************************************************************************************


********************************************************************************************************Zeni
********************************************************************************Dar formato a un numero JSTL
*************************************************************************Dar formato de decimales en la gria
************************************************************************************************************
    referencia --> https://www.tutorialspoint.com/jsp/jstl_format_formatnumber_tag
    <ria:simplegridcolumn label='PACTADO' width="150" id="cantPactada" style="text-align:center;paddind:10px;">
            <fmt:formatNumber type="number" maxFractionDigits = "2" value="${item.cantPactada}"/></ria:simplegridcolumn>
************************************************************************************************************


********************************************************************************************************Zeni
************************************************************************************Dar formato a una Cadena
 String.format("(%s) %s %s",factura.getCuentaCliente().getNroCuenta(), 
                    factura.getCuentaCliente().getCliente().getRazonSocial(),factura.getLeyendaMat())
************************************************************************************************************


********************************************************************************************************Zeni
********************************************Generacion de arreglo partiendo de una cadena dividida por comas
************************************************************************************************************
    String props;
    String[] p = props.split(",");
************************************************************************************************************


*******************************************************************************************************Zeni
**************************************************************************************Trabajar con patrones
    String pattern  = "[0-9]{7}-[0-9]{1}";

            String verdadero = "6412365-4";
            String falso = "923412-3";
            String false2 = "1231234-99";
            String falso3 = "64123653";

            ArrayList<String> lista = new ArrayList<>();
            lista.add(verdadero);
            lista.add(falso);
            lista.add(false2);
            lista.add(falso3);
            for (String reg : lista){
                if (reg.matches(pattern){
                 // Haz lo que quieras aquí
               }

            }
**********************************************************************************************************


********************************************************************************************************Zeni
************************************************************************************Dar formato a una Cadena
   
    DateUtil.getDateTime("dd/MM/yyyy", fechaHasta)
************************************************************************************************************


********************************************************************************************************Zeni
********************************************************************************Convertir una Cadena a Fecha
    
    fecOferta = DateUtil.convertStringToDate("dd/MM/yyyy", fechaOferta);
***********************************************************************************************************


********************************************************************************************************Zeni
*****************************************************************************************Metodo ajax.Request
************************************************************************************************************
    Referencia --> http://prototypejs.org/learn/introduction-to-ajax

    Las solicitudes reales se realizan creando instancias del Ajax.Requestobjeto.

        new Ajax.Request('/some_url', { method:'get' });

    El primer parámetro es la URL de la solicitud; El segundo es el hash de opciones. La opción de método se 
    refiere al método HTTP que se utilizará; El método predeterminado es POST.

    Ejemplo:
        new Ajax.Request('/some_url', {
          method:'get',
          onSuccess: function(transport) {
            var response = transport.responseText || "no response text";
            alert("Success! \n\n" + response);
          },
          onFailure: function() { alert('Something went wrong...'); }
        });

        Aquí, se pasan dos devoluciones de llamada en el hash que alerta de éxito o fracaso; onSuccessy 
        onFailurese llaman en consecuencia en función del estado de la respuesta. El primer parámetro que 
        se pasa a ambos es el objeto xmlHttpRequest nativo desde el cual puede usar sus propiedades 
        responseTexty responseXML, respectivamente.

        Puede especificar ambas devoluciones de llamada, una o ninguna, depende de usted. Otras devoluciones 
        de llamada disponibles son:

            onUninitialized,
            onLoading,
            onLoaded,
            onInteractive,
            onCompletey
            onException.
************************************************************************************************************


********************************************************************************************************Zeni
*********************************************************************************************Creacion de log
***********************************************************************************************Añadir al Log
************************************************************************************************************
    import com.zeni.app.util.LogMessage;

    LogMessage lm = LogMessageAction.getUserLogMessage();
    lm.clear();

    lm.addLogMessage(String.format("Fecha Desde String: " + fechaDesdeStr + "\n"));
    lm.addLogMessage(String.format("Fecha Hasta String: " + fechaDesdeStr + "\n"));

    lm.addLogMessage(String.format("Entre en el If de Fechas\n"));

    LogMessageWindow.setSize(600, 400);
    LogMessageWindow.start();
************************************************************************************************************


********************************************************************************************************Zeni
************************************************************************************************************
************************************************************************************************************
****************************************************************************************Carga del valueStack

    ValueStack stack = ActionContext.getContext().getValueStack();
***********************************************************************************************************



********************************************************************************************************Zeni
************************************************************************************************************
************************************************************************************************************
************************************************************************************LecturaArchivoExcel(xls)
    @Action("validarUsuario")
    @Override
    public String execute() throws Exception    {
        mensaje = "Bienvenido " + nombre + " al munddo de Struts 2. Tu número de la suerte de hoy es " + numero;
        FileInputStream file = new FileInputStream(new File("F:\\Prueba_Excel.xls"));
        // Crear el objeto que tendra el libro de Excel
        HSSFWorkbook workbook = new HSSFWorkbook(file);
          /*
         * Obtenemos la primera pestaña a la que se quiera procesar indicando el indice.
         * Una vez obtenida la hoja excel con las filas que se quieren leer obtenemos el iterator   
         * que nos permite recorrer cada una de las filas que contiene.
         */
        HSSFSheet hoja = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = hoja.iterator();
        
        Row row;
        
        
        // Recorremos todas las filas para mostrar el contenido de cada celda
        while (rowIterator.hasNext()){  
            row = rowIterator.next();
            // Obtenemos el iterator que permite recorres todas las celdas de una fila
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell celda;
            while (cellIterator.hasNext()){
                celda = cellIterator.next();
                
                // Dependiendo del formato de la celda el valor se debe mostrar como String, Fecha, boolean, entero...
                switch(celda.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        if( DateUtil.isCellDateFormatted(celda) ){
                            System.out.println(celda.getDateCellValue());
                        }else{
                            System.out.println(celda.getNumericCellValue());
                        }
                    break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.println(celda.getStringCellValue());
                    break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.println(celda.getBooleanCellValue());
                    break;
                }
            }
         // cerramos el libro excel
           // workbook.close();
        }
        return SUCCESS;
    }   
************************************************************************************************************



********************************************************************************************************Zeni
************************************************************************************************************
************************************************************************************************************
***********************************************************************************Lectura de Cabeceros HTTP
    
    @Action("cabeceros")
    public String cabeceros(){
        Enumeration cabeceros = getRequest().getHeaderNames();
        while(cabeceros.hasMoreElements()) {
            String nombreCabecero=(String) cabeceros.nextElement();
            System.out.print("Cabecero: "+nombreCabecero);
            System.out.println(getRequest().getHeader(nombreCabecero));
        }
        String tipoNavegador = getRequest().getHeader("User-Agent");
        if (tipoNavegador != null && tipoNavegador.contains("Trident")){
            mensaje ="Estas navegando en Internet Explorer";
        }else if (tipoNavegador != null && tipoNavegador.contains("Firefox")){
            mensaje = "Estas navegando con Firefox";
        }else if(tipoNavegador != null && tipoNavegador.contains("Chrome")){
            mensaje = "Estas navegando con Chrome";
        }
        
        return SUCCESS;
    }
************************************************************************************************************



********************************************************************************************************Zeni
************************************************************************************************************
************************************************************************************************************
*******************************************************************************Seteo de Atributos al Request
    Referencia --> SeguimientoDocumentosBoletoAction.java
    //Seteo de este atributo en el request poque sino no anda al treeGrid       
     getRequest().setAttribute("__RiaPageId", "seguimientoDocumentosBoletoList");
************************************************************************************************************      



********************************************************************************************************Zeni
************************************************************************************************************
************************************************************************************************************
***********************************************************************Crea archivo segun calensario y write
    public class ListadoMailEnvioCptesTimerTask extends AbstractAppTimerTask {

        @Override protected String getLogTitle(){return "Genera un listado con las direcciones de mail de los clientes";}
        @Override protected Date getActivationTime(){return makeActivationTime(21,45,0);}
        @Override protected Long getPeriod(){return makePeriodInHours(24);};
        
        @SuppressWarnings("unchecked")
        @Override
        protected void runTask() {
            try{
                Calendar calendar = Calendar.getInstance();
                //Se generaran archivos los dias habiles.
                if(calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
                    UniversalManager manager = BaseModel.getManager();
                    
                    String uploadDir = manager.getSystemConfig("docTec_list_dirMails_path") + "/";
                    //uploadDir = "C:\\Users\\nschwindt\\Downloads\\";
                    
                    File dirPath = new File(uploadDir);
                    if (!dirPath.exists()) {
                        dirPath.mkdirs();
                    }

                    CsvWriter writer = null;
                    writer = new CsvWriter(new FileWriter(uploadDir + "DireccionesMailClientes.csv"),'\n');
                    
                    List<Object[]> result = (List<Object[]>) manager.executeSQL("" +
                                                        "SELECT cl.cuit as cuit, TRIM(cl.razonSocial) as razonSocial," +
                                                        " LISTAGG(em.correo, ',') WITHIN GROUP (ORDER BY cl.RAZONSOCIAL) as correo" +
                                                        " FROM email em" +
                                                        " INNER JOIN cliente cl ON cl.id = em.cliente_id" +
                                                        " INNER JOIN destinatario_envio_email dem ON dem.email_id = em.id" +
                                                        " WHERE dem.tipoOperacion = 'R'" +
                                                        " AND em.fechaBaja IS NULL " +
                                                        " GROUP BY cl.cuit, cl.razonSocial" +
                                                        " ORDER BY TRIM(cl.razonSocial)");
                    for(Object[] reg : result){
                        String cuit = "";
                        String razonSocial = "";
                        String direcciones = "";
                        cuit = reg[0].toString().replace("-", "");
                        razonSocial = reg[1].toString().trim();
                        direcciones = reg[2].toString().trim();
                                            
                        writer.write(cuit+";"+
                                     razonSocial+";"+
                                     direcciones);
                        writer.endRecord();                 
                    }
                    writer.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }   
    }
************************************************************************************************************



********************************************************************************************************Zeni
************************************************************************************************************
************************************************************************************************************
************************************************************************LLenado de map con una lista por key
    Map<CuentaCliente, List<Object[]>> mapOthers = new HashMap<CuentaCliente, List<Object[]>>();
    for(Object[] reg : info){
        CuentaCliente cc = (CuentaCliente)reg[0];
        if(cc.getId().equals(cuentaCliente.getId())){
            infoCC.add(reg);
        } else {
            List<Object[]> infoOther = mapOthers.get(cc);
            if(infoOther==null)
                mapOthers.put(cc, infoOther = new ArrayList<Object[]>());
            infoOther.add(reg);
        }
    }
************************************************************************************************************


*************************************************************************Recuperar un dato tipo objeto de BD
   Object obj = (Object) BaseModel.getManager().executeSQL(sql);
   Double precioMAV = ((obj != null) ? Double.valueOf(obj.toString().replace("[","").replace("]", "")) : 0);  
************************************************************************************************************


********************************************************************************************************Zeni
************************************************************************************************************
************************************************************************************************************
**************************************************************************************Tag de struts Iterator
    Referencia --> gridControl.jsp

    /*<ria:simplegrid id="gcGrid" dataProvider="${dataProvider}"
        collection="${dataCollection}" rowPerPage="${rowPerPage}" var="item"
        rowCountVar="rowCount" resizable="false" showPropertyButton="false"
        selectionMode="${selectionMode}"
        onSetData="gridNotifyOnEmpty(this, __Messages__.gridNoData)">
        <ria:simplegridcolumn label="${idColumnLabel}" width="50"
            key="$item.id" id="id" visible="false" />
        <ria:simplegridcolumn label="${nameColumnLabel}"
            width="${nameColumnLabelWidth}" id="${entityAttrName}"
            key="$item.${entityAttrName}" />
        <s:if test="additionalAttrList!=null && additionalAttrList[0]!='null'">
            <s:iterator value="additionalAttrList" var="itemList" status="stat">
                <s:set var="idx" value="#stat.index" />
                <ria:simplegridcolumn label="${additionalAttrTitleList[idx]}"
                    width="${additionalAttrWidthList[idx]}" key="$item.${itemList}"
                    id="${itemList}" />
            </s:iterator>
        </s:if>
    </ria:simplegrid>*/
************************************************************************************************************


********************************************************************************************************Zeni
************************************************************************************************************
************************************************************************************************************
********************************************************************************Detalle en fila de la grilla
    <riaO:griddetail>
        <s:if test="%{#request.item.tipo==\"P\"}">
            <s:if test="%{#request.item.entregaPartida.entrega.analisisMuestra != null}">
                <s:property value="%{#request.item.entregaPartida.entrega.detallesAnalisisMuestra}" />
            </s:if>
            <s:if test="%{#request.item.entregaPartida.entrega.observaciones != null && #request.item.entregaPartida.entrega.observaciones != \"\"}">
                <s:property value="%{#request.item.entregaPartida.entrega.observaciones}" />
            </s:if>
                <s:property value="%{#request.item.entregaPartida.entrega.coberturasAsString != null ? ' Cubres:' + #request.item.entregaPartida.coberturasAsString : ''}" />
        </s:if>
    </riaO:griddetail>
************************************************************************************************************


********************************************************************************************************Zeni
************************************************************************************************************
************************************************************************************************************
*******************************************************************************Validar el metodo del request
    Referecia FacturacionMFCaucionAction.java --> metodo prepare()
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String mfCaucionId = getRequest().getParameter("mfCaucion.id");
            if (mfCaucionId != null && !mfCaucionId.equals("")) {
                mfCaucion = (MFCaucion)facturacionMFCaucionManager.get(MFCaucion.class, new Long(mfCaucionId));
            }
        }
************************************************************************************************************


********************************************************************************************************Zeni
************************************************************************************************************
************************************************************************************************************
**********************************************************************Carga y procesamiento de archivo excel
    ***Formulario actualizarNroCertificadoDeposito.jsp
        <s:form id="actualizarCertificadosDeposito" action="tomarCertificadosParaActualizar" enctype="multipart/form-data" method="post" onsubmit="return AjaxFormValidator.submit(this.id,null)">
            <fieldset><legend><fmt:message key="tomarMatArchivoContratoClienteFieldset.Archivo"/></legend>
                <table style="border:0px; cellspacing:0px; cellpadding:0px; width:100%">
                    <tr>
                        <td id=fileIn>
                            <s:file name="file" label="%{getText('parseaTxtForm.file')}" cssClass="text file"   
                                size="50" required="true"/>
                        </td>
                        <td>
                            <s:submit cssClass="button" method="save" key="button.save" theme="simple" value="Procesar Archivo" style="width:150px;" />
                        </td>
                    </tr>
                </table>
            </fieldset>
        </s:form>

    ***struts-Aplicacion.xml
        <action name="tomarCertificadosParaActualizar" class="com.zeni.app.webapp.action.ActualizarNroCertificadoDepositoAction" method="tomarCertDepositoParaActualizar">
            <interceptor-ref name="jsonValidationWorkflowStack" />
            <interceptor-ref name="fileUploadStack" />
            <result>/WEB-INF/pages/actualizarNroCertificadoDeposito.jsp</result>
            <result name="input">/WEB-INF/pages/actualizarNroCertificadoDeposito.jsp</result>
        </action>

    ***ActualizarNroCertificadoDepositoAction.java
        @SuppressWarnings("unchecked")
        public String tomarCertDepositoParaActualizar() throws IOException {
            if(file != null) {
                InputStream stream = new FileInputStream(file);
                CsvReader streamArchivo = new CsvReader(stream, ';', Charset.forName("ISO-8859-1"));
                idsContratos = "";
                List<F1116A> resultado = new ArrayList();
                while (streamArchivo.readRecord()) {
                    String[] linea = streamArchivo.getValues();
                    String nroCartaPorte = linea[0];
                    String certificadoOriginal = linea[1];
                    String certificadoNuevo = linea[2];
                    
                    String query = "FROM F1116A c WHERE c.nroFormulario = " + new Long(certificadoOriginal);
                    resultado = manager.find(query);
                    if(!resultado.isEmpty()) {
                        System.out.println("Carta Porte: " + nroCartaPorte + " - Cert. Orig.: " + certificadoOriginal + " - Cert. Nuevo: " + certificadoNuevo);
                        manager.bulkUpdate("UPDATE F1116A c SET c.nroFormulario = " + new Long(certificadoNuevo) + " WHERE c.nroFormulario = " + new Long(certificadoOriginal));
                        manager.flush(true);
                    }else{
                        System.out.println("CP Faltante: " + nroCartaPorte + " - Cert. Orig.: " + certificadoOriginal + " - Cert. Nuevo: " + certificadoNuevo);
                    }
                }
                streamArchivo.close();
            }
            return SUCCESS;
        }
************************************************************************************************************


Grilla SivRia
********************************************************************************************************Zeni
************************************************************************************************************
****************************************************************Obetener datos de la grilla desde el request
**********************************************************************************Modificacion de una grilla
    
    Referencia  --> FacturacionMatRegistroAction.java  
    //Modificacion de Grilla
    protected void updateComprobantesGrid() {
            int i = 0;
            //Obtengo la grilla del request
            GridModel model = (GridModel) PageManager.getModel(getRequest(),
                    "comprobantesRGrid");
            //Obtengo la data de la grilla
            GridDataCollectionProvider dataProvider = (GridDataCollectionProvider) model
                    .getDataProvider();
            //Creo un objeto para almacenar una lista de las filas
            List<GridRowModel> rows = new ArrayList<GridRowModel>();

            //Se recorre los comprobantes de facturacion
            for (Comprobante c : (List<FacturaProducto>) getComprobantesFacturacion()) {
                FacturaProducto fp = (FacturaProducto) c;
                //Se crea lista para almacenar las celdas
                List<GridCellModel> cells = new ArrayList<GridCellModel>();
                //Añado las celdas a la lista
                cells.add(createCell(fp.getFacturador()));
                cells.add(createCell(fp.getCuentaCliente().getNroCuenta()));
                cells.add(createCell(fp.getTipoComprobante().getNombre()));
                String editLink = "";
                editLink = String.format("<a title=\"%s\" href=\"generarFacturaPDFDesdeSession%s.html?idFact=%s\">"
                        + "<img border=\"0\" onmouseover=\"javascript:this.src='images/reporte_pdf01.gif';\" onmouseout= \"javascript:this.src='images/reporte_pdf00.gif';\" src=\"images/reporte_pdf00.gif\"></a>",
                        getText("facturacion.generarFacturaPDF"), this.getTipoFacturador(), i);
                cells.add(createCell(editLink));
                cells.add(createCell(fp.getLetra()));
                //Añado la lista de celdas a la fila
                rows.add(new GridRowModel(cells, null, "row" + i, i));
                i++;
            }
            //agrego las filas al dataProvider 
            dataProvider.setRows(rows);
        }
    //Creacion de celdas   
    protected GridCellModel createCell(Comparable val) {
        GridCellModel gcm = new GridCellModel();
        gcm.setObject(val);

        return gcm;
************************************************************************************************************


********************************************************************************************************Zeni
************************************************************************************************************
************************************************************************************************************
*********************************************************************************Generacion de una excepcion
  public class DiferenciaEnAsientoException extends RuntimeException{
        private static final long serialVersionUID = -1995369163017440851L;
        private Double diferencia;
        public DiferenciaEnAsientoException(Double diferencia){
            t
************************************************************************************************************


********************************************************************************************************Zeni
************************************************************************************************************
************************************************************************************************************
******************************************************************************Obtener contenido de la grilla
    Referencia --> IngresoAsientosAction.java
    Map<String, Object>[] rowList = VerySimpleGridTag.getGridContent(getRequest(), "cuentasGrid");

    Ejemplo_______________________________________________________________________
    private List<Object[]> recuperarImputaciones() throws Exception {
        List<Object[]>imputaciones = new ArrayList<Object[]>();
        Map<String, Object>[] rowList = VerySimpleGridTag.getGridContent(getRequest(), "cuentasGrid");
        for (Map<String, Object> row : rowList)
            imputaciones.add(getImputacion(row));
        return imputaciones;
    }
************************************************************************************************************


********************************************************************************************************Zeni
************************************************************************************************************
**********************************************************Crear (filter) filtros que extiendan de namedQuery
********************************************************************************************Manejo de Grilla
    public class MatOperacionCapitalesResumenAction extends BaseAction implements Preparable{
            private static final long serialVersionUID = 7128327402742497333L;
            /**
             * Filtro principal, los resumenes copian este filtro
             */
            private MATOperacionCapitalesFilter filtro = (MATOperacionCapitalesFilter) getSession().getAttribute(MATOperacionCapitalesFilter.class.getSimpleName());
            
            private MATResumenCapitalesFilter filtroCapitales = (MATResumenCapitalesFilter) super.newFilter(MATResumenCapitalesFilter.class);
            
            private GridDataProvider capitalesDataProvider;
            

            private UniversalManager manager;
            
            private double sumVol;
            
            public double getSumVol() {     
                return sumVolumen();
            }
            
            public void setManager(UniversalManager manager) {
                this.manager = manager;
            }
            
            public void setSumVol(double sumVol) {
                this.sumVol = sumVol;
            }

            public MATResumenCapitalesFilter getFiltroCapitales() {
                return filtroCapitales;
            }
            
            public GridDataProvider getCapitalesDataProvider() {
                if (capitalesDataProvider == null) {
                    capitalesDataProvider = new GridDataProvider(HashMap.class,getFiltroCapitales());
                }       
                return capitalesDataProvider;
            }
            
            @SuppressWarnings("unchecked")
            public List<HashMap> getDataCollection() {
                ArrayList<HashMap> list = new ArrayList<HashMap>();
                try {
                    list.add(new HashMap());
                } catch (Exception e) {
                    log.error("Error al generar instancia", e);
                }
                return list;
            }
            
            public List<HashMap> getOpcionesDataCollection() {
                return getDataCollection();
            }
            
            public List<HashMap> getCapitalesDataCollection() {
                return getDataCollection();
            }
            
            protected Class<MatOperacion> getEntityClass() { return MatOperacion.class; }
            
            @Override
            public void prepare() {
                super.prepare();
                getRequest().setAttribute("__RiaPageId", "matResumenPageCapitales");
            }

            public String list() throws Exception {
                filtroCapitales.copiarDesde(filtro);
                return SUCCESS; 
                } 
            
            public String exportarGrillas() throws IOException{
                HSSFWorkbook workbook = new HSSFWorkbook();
                Map<String, HSSFCellStyle>estilosDisponibles = exportarGrillasEstilos(workbook);
                HSSFSheet hoja = workbook.createSheet("Operaciones");
                Integer rows = 0;
                exportarGrillasAddHeaders(hoja, rows++,estilosDisponibles);
                rows = exportarGrillasAddFuturos(hoja, rows,estilosDisponibles);
                rows = exportarGrillasAddOpciones(hoja, rows,estilosDisponibles);
                for(int i = 0 ; i < 12 ; i++ )
                    hoja.autoSizeColumn(i);
                getResponse().setContentType("application/vnd.ms-excel");
                getResponse().addHeader("Content-Disposition", "attachment;filename=\"OperacionesMat.xls\"");
                workbook.write(getResponse().getOutputStream());
                getResponse().getOutputStream().flush();
                getResponse().getOutputStream().close();
                return SUCCESS;
            }
            private Map<String, HSSFCellStyle> exportarGrillasEstilos(HSSFWorkbook libro){
                Map<String, HSSFCellStyle> estilos = new HashMap<String, HSSFCellStyle>();
                //Bold font style
                HSSFCellStyle boldStyle = libro.createCellStyle();
                short bortWeight = 2;
                boldStyle.setBorderBottom(bortWeight);
                boldStyle.setBorderTop(bortWeight);
                boldStyle.setBorderLeft(bortWeight);
                boldStyle.setBorderRight(bortWeight);
                HSSFFont boldFont = libro.createFont();
                boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                boldStyle.setFont(boldFont);
                estilos.put("BOLD", boldStyle);
                //2 decimales
                HSSFCellStyle dosDecimales = libro.createCellStyle();
                dosDecimales.setDataFormat(libro.createDataFormat().getFormat("#,##0.00"));
                estilos.put("2DECIMAL", dosDecimales);
                
                HSSFCellStyle cuatroDecimales = libro.createCellStyle();
                cuatroDecimales.setDataFormat(libro.createDataFormat().getFormat("#,##0.0000"));
                estilos.put("4DECIMAL", cuatroDecimales);
                
                return estilos;
            }
            private void exportarGrillasAddHeaders(HSSFSheet hoja,Integer rowNum,Map<String, HSSFCellStyle> estilos){
                HSSFCellStyle bold = estilos.get("BOLD");
                HSSFRow header =  hoja.createRow(rowNum);
                String[] titulos = new String[]{"Nro Cta","R. Social","Mercado","Condición","Tipo","Producto","","Volumen","Pre/Pri","Pr. Ejercicio"};
                for(int i = 0;i<titulos.length;i++){
                    HSSFCell cell = header.createCell(i);
                    cell.setCellStyle(bold);
                    cell.setCellValue(titulos[i]);
                }
            }
            @SuppressWarnings("unchecked")
            private Integer exportarGrillasAddFuturos(HSSFSheet hoja,Integer rowNum, Map<String, HSSFCellStyle> estilos){
                List<GridRowModel> futurosRows = ((GridModel)PageManager.getModel(getRequest(),"matOperacionesFuturosGrid")).getAllRowModels();
                HSSFCellStyle cuatroDecimales = estilos.get("4DECIMAL");
                for(GridRowModel rowM : futurosRows){
                    HSSFRow xlsRow = hoja.createRow(rowNum++);
                    for(int i = 1; i < rowM.getCellModels().size() ; i ++){
                        HSSFCell xlsCell = xlsRow.createCell(i-1);
                        String stringValue = ((GridCellModel)rowM.getCellModels().get(i)).getObject().toString();
                        if( i == 9)
                            xlsCell.setCellValue( Integer.valueOf(stringValue) );
                        else if( i == 11 ){
                            xlsCell.setCellValue( Double.valueOf(stringValue.replace(',','.')) );
                            xlsCell.setCellStyle(cuatroDecimales);
                        } else
                            xlsCell.setCellValue( stringValue );
                    }
                }
                return rowNum;
            }
            @SuppressWarnings("unchecked")
            private Integer exportarGrillasAddOpciones(HSSFSheet hoja,Integer rowNum, Map<String, HSSFCellStyle> estilos){
                HSSFCellStyle cuatroDecimales = estilos.get("4DECIMAL");
                List<GridRowModel> opcionesRows = ((GridModel)PageManager.getModel(getRequest(),"matOperacionesOpcionesGrid")).getAllRowModels();
                for(GridRowModel rowM : opcionesRows){
                    HSSFRow xlsRow = hoja.createRow(rowNum++);
                    for(int i = 1; i < rowM.getCellModels().size() ; i ++){
                        HSSFCell xlsCell = xlsRow.createCell( i - (i == 11 ? 0 : (i == 12 ? 2 : 1 ) ) );
                        String stringValue = ((GridCellModel)rowM.getCellModels().get(i)).getObject().toString();
                        if( i == 9)
                            xlsCell.setCellValue( Integer.valueOf(stringValue) );
                        else if( i == 11 || i == 12){
                            xlsCell.setCellValue( Double.valueOf(stringValue.replace(',','.')) );
                            xlsCell.setCellStyle(cuatroDecimales);
                        } else
                            xlsCell.setCellValue( stringValue );
                    }
                }
                return rowNum;
            }
            
            @SuppressWarnings("unchecked")
            private double sumVolumen(){        
                SortItem[] si = new SortItem[1];
                si[0] = new SortItem("nroCuenta", true);
                //Object q = manager.executeNamedQuery("matCapitalesResumenSumVolumen",getFiltroCapitales().getParams());
                List<Object[]> opRows = manager.getAllRecordsPage(MatOperacion.class, -1, -1, si, getFiltroCapitales());
                double suma=0;
                for(Object[] a: opRows){
                    suma += (Math.round(Double.parseDouble(a[13].toString()) * 100000d) / 100000d);
                }
                setSumVol(suma);        
                return sumVol;
            }
            
            /**
             * Filtros que actualizan grillas
             */ 
            public abstract class MATResumenCFilter extends NamedQueryFilterCondition implements Serializable {
                private static final long serialVersionUID = 6414913492054412687L;

                public MATResumenCFilter(String nqName) { super(nqName);}
                public MATResumenCFilter(String[] nqsName) { super(nqsName);}
                
                @Override
                public Boolean getDistinct() {
                    return false;
                }

                public abstract void setFuturosOpciones(Character futurosOpciones);

                public Integer getNroRegistro() {
                    return (Integer) get("nroRegistro");
                }
                public void setNroRegistro(Integer nroRegistro) {
                    set("nroRegistro", nroRegistro);
                }

                public void setEstado(String estado) throws Exception {
                    if ( estado != null && estado.trim().equalsIgnoreCase("ES") ) {
                        set("estado", null);
                        set("estadistico",1);
                    } else {
                        set("estado", estado);
                        set("estadistico",0);
                    }
                    Calendar hoy = DateUtil.getToday();
                    set("hoy",DateUtil.finalDelDia(hoy.getTime()));
                    hoy.add(Calendar.DAY_OF_MONTH, -1);//hoy ahora es ayer =)
                    set("ayer",hoy.getTime());
                }
                public String getEstado() {
                    String val = (String) get("estado");
                    return (String) ( val == null ? "ES" : val );
                }
                
                public Date getFechaEstado() {
                    return (Date) get("fechaEstado");
                }
                public void setFechaEstado(Date fechaEstado) {
                    set("fechaEstado",fechaEstado);
                    if(fechaEstado != null)
                        set("fechaEstadoHasta", DateUtil.finalDelDia(fechaEstado));
                    else
                        set("fechaEstadoHasta", null);
                }

                public Long getOperadorDeMesa() {
                    return (Long) get("operadorDeMesa");
                }
                public void setOperadorDeMesa(Long operadorDeMesa) {
                    set("operadorDeMesa", operadorDeMesa);
                }

                public Long getMercado() {
                    return (Long) get("mercado");
                }
                public void setMercado(Long mercado) {
                    set("mercado", mercado);
                }

                public Long getCCliente() {
                    return (Long) get("cCliente");
                }
                public void setCCliente(Long cCliente) {
                    set("cCliente", cCliente);
                }

                public Character getTipoOperacion() {
                    return (Character) get("tOperacion");
                }
                public void setTipoOperacion(Character tipoOperacion) {
                    set("tOperacion", tipoOperacion);
                }

                public Long getSucursal() {
                    return (Long) get("sucursal");
                }
                public void setSucursal(Long sucursal) {
                    set("sucursal", sucursal);
                }

                public Character getCondicion() {
                    return (Character) get("cond");
                }
                public void setCondicion(Character cond) {
                    set("cond", cond);
                }

                public Character getTipoProducto() {
                    return (Character) get("tipoProducto");
                }

                public void setTipoProducto(Character tipoProducto) {
                    set("tipoProducto", tipoProducto);
                }

                public Long getMatProductoPadre() {
                    return (Long) get("matProductoPadre");
                }

                public void setMatProductoPadre(Long matProducto) {
                    set("matProductoPadre", matProducto);
                }

                public Long getMatProducto() {
                    return (Long) get("matProducto");
                }
                public void setMatProducto(Long matProducto) {
                    if(getTipoProducto() != null && esPadreHijo()) {
                        set("matProducto", null);
                        set("matProductoPadre", matProducto);
                    } else {
                        set("matProducto", matProducto);
                        set("matProductoPadre", null);
                    }
                }

                public Double getPrecio() {
                    return (Double) get("precio");
                }
                public void setPrecio(Double precio) {
                    set("precio", precio);
                }

                public Double getPrima() {
                    return (Double) get("prima");
                }
                public void setPrima(Double prima) {
                    set("prima", prima);
                }

                public Date getFechaAltaDesde() {
                    return (Date) get("fechaAltaDesde");
                }
                public void setFechaAltaDesde(Date fechaAltaDesde) {
                    set("fechaAltaDesde", fechaAltaDesde);
                }

                public Date getFechaAltaHasta() {
                    return (Date) get("fechaAltaHasta");
                }
                public void setFechaAltaHasta(Date fechaAltaHasta) {
                    set("fechaAltaHasta", DateUtil.finalDelDia(fechaAltaHasta));
                }
                
                public Boolean getRegristraZeni() {
                    return (Boolean) get("regristraZeni");
                }
                public void setRegristraZeni(Boolean regristraZeni) {
                    this.set("regristraZeni",regristraZeni);
                }
                
                public Long getCodigoInterno() {
                    return (Long) get("codigoInterno");
                }
                public void setCodigoInterno(Long codigoInterno) {
                    set("codigoInterno", codigoInterno);
                }
                
                public Date getFechaVto() {
                    return (Date) get("fechaVto");
                }
                public void setFechaVto(Date fechaVto) {
                    set("fechaVto", fechaVto);
                }
                
                public Character getTransferenciaDeCartera() {
                    Boolean transferenciaDeCartera = (Boolean) get("transferenciaDeCartera");
                    if (transferenciaDeCartera == null) {
                        return 'T';
                    } else if (transferenciaDeCartera) {
                        return 'S';
                    }
                    return 'N';
                }

                public void setTransferenciaDeCartera(Character v) {
                    if (v.equals('T')) {
                        set("transferenciaDeCartera", null);
                    } else if (v.equals('S')) {
                        set("transferenciaDeCartera", true);
                    }else if (v.equals('N')) {
                        set("transferenciaDeCartera", false);
                    }
                }
                
                public Character getInstruccionComitente() {
                    Boolean instruccionComitente = (Boolean) get("instruccionComitente");
                    if (instruccionComitente == null) {
                        return 'T';
                    } else if (instruccionComitente) {
                        return 'S';
                    }
                    return 'N';
                }

                public void setInstruccionComitente(Character v) {
                    if (v.equals('T')) {
                        set("instruccionComitente", null);
                    } else if (v.equals('S')) {
                        set("instruccionComitente", true);
                    }else if (v.equals('N')) {
                        set("instruccionComitente", false);
                    }
                }

                public void copiarDesde(MATOperacionCapitalesFilter pOtroFiltro) throws Exception {
                    this.setEstado(pOtroFiltro.getEstado());
                    this.setFechaEstado(pOtroFiltro.getFechaEstado());
                    this.setOperadorDeMesa(pOtroFiltro.getOperadorDeMesa());
                    this.setMercado(pOtroFiltro.getMercado());
                    this.setCCliente(pOtroFiltro.getCCliente());
                    this.setTipoOperacion(pOtroFiltro.getTipoOperacion());
                    this.setSucursal(pOtroFiltro.getSucursal());
                    this.setCondicion(pOtroFiltro.getCondicion());
                    this.setTipoProducto(pOtroFiltro.getTipoProducto());
                    this.setMatProducto(pOtroFiltro.getMatProducto());
                    this.setMatProductoPadre(pOtroFiltro.getMatProductoPadre());
                    this.setPrecio(pOtroFiltro.getPrecio());
                    this.setPrima(pOtroFiltro.getPrima());
                    this.setFechaAltaHasta(pOtroFiltro.getFechaAltaHasta());
                    this.setFechaAltaDesde(pOtroFiltro.getFechaAltaDesde());
                    this.setNroRegistro(pOtroFiltro.getNroRegistro());
                    this.setFuturosOpciones(pOtroFiltro.getFuturosOpciones());
                    this.setRegristraZeni(pOtroFiltro.getRegristraZeni());
                    this.setCodigoInterno(pOtroFiltro.getCodigoInterno());
                    this.setFechaVto(pOtroFiltro.getFechaVto());
                    this.setTransferenciaDeCartera(pOtroFiltro.getTransferenciaDeCartera());
                    this.setInstruccionComitente(pOtroFiltro.getInstruccionComitente());
                }

                public boolean esPadreHijo() {
                    //Tasa de Descuento, Cheque, Pagaré, Aval Zeni, Aval Bancario y Garantía SGR
                    return ("HQTZWÑ".indexOf(getTipoProducto()) != -1);
                }
            }
            
            public class MATResumenCapitalesFilter extends MATResumenCFilter {
                private static final long serialVersionUID = -6028975192805993222L;

                public MATResumenCapitalesFilter() {
                    super("matCapitalesResumen");
                }
                
                @Override
                public Double getPrecio() { return null;}
                @Override
                public void setPrecio(Double precio) {}

                public void setFuturosOpciones(Character futurosOpciones) {
                    List<Character> tipoOperacion = new ArrayList<Character>();
                    
                    if( futurosOpciones == null){
                        tipoOperacion.add('A');
                        tipoOperacion.add('L');
                        tipoOperacion.add('C');
                        tipoOperacion.add('P');
                        tipoOperacion.add('N');
                        tipoOperacion.add('D');
                    } else if(futurosOpciones.equals('T')) {
                        // Tenencia
                        tipoOperacion.add('A');
                        tipoOperacion.add('L');
                    } else if(futurosOpciones.equals('O')) {
                        // Opciones
                        tipoOperacion.add('C');
                        tipoOperacion.add('P');
                    } else  {
                        tipoOperacion.add('W');// 'W' es un tipo que no debe existir, se setea porque hibernate no puede manejar una lista vacia
                    }
                    set("tiposContemplados", tipoOperacion);
                }

                @Override
                public void setEstado(String estado) throws Exception {
                    super.setEstado(estado);
                }
            }
        }
************************************************************************************************************ 



********************************************************************************************************Zeni
************************************************************************************************************
*************************************************************Validar si un objeto es vacio con libreria Util
    if (!Util.isEmpty(bm.getEntrega().getProcedencia())) {
        ...
    }
************************************************************************************************************



********************************************************************************************************Zeni
************************************************************************************************************
******************************************************************************Creacion y despliegue de popup
        <th align = "right">
                <s:submit cssClass="button" key="Previsualizar" theme="simple" onclick="popUpOpen()"/>
            </td>

            function popUpOpen(){
                popupWin.openUrl('popupPrevisualizar','previsualizar1.html?popUp=popupPrevisualizar&decorator=popup&cuentaClienteId='+clienteId);
            }
***********************************************************************************************************



**************************************************************************************Manejo de token cors

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
**********************************************************************************************************





se usa para trabajar con un flujo de datos, y que se emiten eventos que uno luego puedo procesar, a través de un observador

Los observables son asíncronos. Ese flujo de datos que mencionas antes es asíncrono, quiere decir que no se sabe en qué momento el observable emitirá un valor.

observables pueden tener muchos observadores así cuando se emite un valor todos pueden recibirlo