https://www.adictosaltrabajo.com/2010/12/22/procedimientos-almacenados-hibernate/

*****************************************************************************************Definiciones
  Session Factory : cualquier aplicación de usuario solicita Session Factory para un objeto de sesión. 
  Session Factory utiliza la información de configuración de los archivos enumerados anteriormente 
  para crear instancias del objeto de sesión de manera adecuada.
  ----------------------------------------------------------------------------------------------
  Sesión : Esto representa la interacción entre la aplicación y la base de datos en cualquier momento. 
  Esto está representado por la org.hibernate.Sessionclase. La instancia de una sesión se puede 
  recuperar del SessionFactorybean.
  ----------------------------------------------------------------------------------------------
  Consulta : permite a las aplicaciones consultar en la base de datos uno o más objetos almacenados. 
  Hibernate proporciona diferentes técnicas para consultar la base de datos, incluyendo NamedQueryy 
  Criteria API.
  ----------------------------------------------------------------------------------------------
  Caché de primer nivel : representa el caché predeterminado utilizado por el objeto Hibernate Session 
  mientras interactúa con la base de datos. También se llama como caché de sesión y almacena en caché 
  objetos dentro de la sesión actual. Todas las solicitudes del objeto Session a la base de datos deben 
  pasar por el caché de primer nivel o el caché de sesión. Hay que tener en cuenta que el caché de primer 
  nivel está disponible con el objeto de sesión hasta que el objeto de sesión esté activo.
  ----------------------------------------------------------------------------------------------
  Transacción : le permite lograr la consistencia de los datos y deshacer en caso de que algo salga inesperado.
  ----------------------------------------------------------------------------------------------
  Objetos persistentes : estos son objetos Java simples (POJO), que se mantienen como una de las filas 
  en la tabla relacionada en la base de datos por hibernación. Se pueden configurar en archivos de 
  configuración ( hibernate.cfg.xmlo hibernate.properties) o anotarse con @Entityanotaciones.
  ----------------------------------------------------------------------------------------------
  Caché de segundo nivel : se utiliza para almacenar objetos en sesiones. Esto debe habilitarse 
  explícitamente y se requeriría uno para proporcionar el proveedor de caché para un caché de segundo 
  nivel. Uno de los proveedores comunes de caché de segundo nivel es EhCache .
****************************************************************************************************




******************************************Entidad Hibernate / Persistencia Estados del ciclo de vida
  Dada una instancia de un objeto que está asignado a Hibernate, puede estar en cualquiera de los 
  cuatro estados diferentes: transitorio, persistente, separado o eliminado .
    *Objeto transitorio
      Los objetos transitorios existen en la memoria heap. Hibernate no gestiona objetos transitorios 
      o cambios persistentes en objetos transitorios.

      Los objetos transitorios son independientes de Hibernate
      Para persistir los cambios en un objeto transitorio, deberá solicitar a la sesión que guarde 
      el objeto transitorio en la base de datos, momento en el que Hibernate asigna al objeto un 
      identificador y marca el objeto como en estado persistente.
    *Objeto persistente
      Existen objetos persistentes en la base de datos e Hibernate gestiona la persistencia de los objetos persistentes.

      Hibernate mantiene los objetos persistentes
      Si los campos o propiedades cambian en un objeto persistente, Hibernate mantendrá actualizada 
      la representación de la base de datos cuando la aplicación marque los cambios como confirmados.
    *Objeto separado
      Los objetos separados tienen una representación en la base de datos, pero los cambios en el objeto 
      no se reflejarán en la base de datos, y viceversa. 

      Los objetos separados existen en la base de datos pero Hibernate no los mantiene
      Se puede crear un objeto separado cerrando la sesión con la que estaba asociado, o expulsándolo 
      de la sesión con una llamada al método evict () de la sesión.

      Una razón por la que podría considerar hacer esto sería leer un objeto fuera de la base de datos, 
      modificar las propiedades del objeto en la memoria y luego almacenar los resultados en otro lugar 
      que no sea su base de datos. Esta sería una alternativa a hacer una copia profunda del objeto.
      Para conservar los cambios realizados en un objeto separado, la aplicación debe volver a conectarlo 
      a una sesión de Hibernate válida. Una instancia separada se puede asociar con una nueva sesión de 
      Hibernate cuando su aplicación llama a uno de los métodos cargar, actualizar, combinar, 
      actualizar () o guardar () en la nueva sesión con una referencia al objeto separado. Después de 
      la llamada, el objeto separado sería un objeto persistente administrado por la nueva sesión de Hibernate.
    *Objeto eliminado
      Los objetos eliminados son objetos gestionados por Hibernate (objetos persistentes, en otras 
      palabras) que se han pasado al método remove () de la sesión. Cuando la aplicación marca los 
      cambios realizados en la sesión como confirmados, se eliminan las entradas en la base de datos
      que corresponden a los objetos eliminados.
****************************************************************************************************




**************************************************************************************SessionFactory
  Si ha estado viendo versiones anteriores de hibernate, entonces debe haber notado que han desaprobado 
  muchas clases en rápida sucesión. Clases desaprobadas son AnnotationConfiguration, ServiceRegistryBuilder 
  y así sucesivamente . Estas clases se utilizan para construir una fábrica de sesiones a través de su código 
  java y si las usa en su aplicación; pueden funcionar bien, pero siempre verá algunas advertencias molestas sobre su depreciación.

  En este tutorial, estoy dando un ejemplo de construcción de SessionFactory hibernate sin usar las 
  clases obsoletas mencionadas anteriormente. Estoy usando la última versión de hibernate, es decir, 
  Hibernate 4.3.6.Final , por lo que puede asegurarse de que está utilizando el último enfoque para 
  construir la fábrica de sesiones.

  Clases utilizadas en la construcción de SessionFactory
  He usado las siguientes clases para construir SessionFactory en hibernate 4.

  Configuración : en lugar de AnnotationConfiguration en desuso
  StandardServiceRegistryBuilder : en lugar de ServiceRegistryBuilder en desuso

  -----------------------------------------------------------------------------------------------

  import org.hibernate.SessionFactory;
  import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
  import org.hibernate.cfg.Configuration;
  import org.hibernate.service.ServiceRegistry;
   
  public class HibernateUtil
  {
     private static SessionFactory sessionFactory = buildSessionFactory();
   
     private static SessionFactory buildSessionFactory()
     {
        try
        {
           if (sessionFactory == null)
           {
              Configuration configuration = new Configuration().configure(HibernateUtil.class.getResource("/hibernate.cfg.xml"));
              StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
              serviceRegistryBuilder.applySettings(configuration.getProperties());
              ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
              sessionFactory = configuration.buildSessionFactory(serviceRegistry);
           }
           return sessionFactory;
        } catch (Throwable ex)
        {
           System.err.println("Initial SessionFactory creation failed." + ex);
           throw new ExceptionInInitializerError(ex);
        }
     }
   
     public static SessionFactory getSessionFactory()
     {
        return sessionFactory;
     }
   
     public static void shutdown()
     {
        getSessionFactory().close();
     }
  }
****************************************************************************************************




****************************************************************************************@Anotaciones

****************************************************************************************************



*****************************************************Configurar el registro de consultas de Hibernate
  *********************************Configuracion de comentarios y sentencias sql hibernate.cfg.xml
    *** show_sql habilita el registro de consultas
    *** format_sql bonito imprime el SQL
    *** use_sql_comments agrega un comentario explicativo
  <bean id = "entityManagerFactory" class = "org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
  ...
    <property name = "jpaProperties">
    <props>
        <prop key = "hibernate.show_sql"> verdadero </ prop>
        <prop key = "hibernate.format_sql"> verdadero </ prop>
        <prop key = "hibernate.use_sql_comments"> verdadero </prop>
    </props>
  </property>
  Ejemplo__________________________________________________________________________________________
    <hibernate-configuration>
      <session-factory>
         <property name="show_sql">true</property>
         <property name="format_sql">true</property>
         <property name="max_fetch_depth">1</property>
         <property name="use_sql_comments">true</property>

  *****Para registrar los parámetros de consulta, log4j se necesita la siguiente  configuración o equivalente:
    <logger name = "org.hibernate.type">
        <level value = "trace" />
    </ logger> 
    Ejemplo_____________________________________________________________________________________
      <logger name="org.hibernate.SQL">
        <level value="DEBUG"/>
      </logger>
      
      <logger name="org.hibernate.type">
          <level value="trace"/>
      </logger>
*****************************************************************************************************




*******************************************************************************************@Transient

	La anotación @Transient se utiliza para indicarle a JPA que un atributo de una Entidad no debe de 
	ser persistente, de esta manera, JPA pasa por alto el atributo y no es tomado en cuenta a la hora 
	de persistir el Objeto.
*****************************************************************************************************



*************************************************Configuración del registro de consultas de Hibernate
	Para obtener un registro de consultas en consola, se deben establecer los siguientes indicadores en la 
	configuración de la fábrica de sesiones:

	<bean id = "entityManagerFactory" class = "org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
	  ...
	  <nombre de propiedad = "jpaProperties">
	  <props>
	      <prop key = "hibernate.show_sql"> true </ prop>
	      <prop key = "hibernate.format_sql"> true </ prop>
	      <prop key = "hibernate.use_sql_comments"> true </prop>
	  </props>
	</property>
	El ejemplo anterior es para la configuración Spring de una fábrica de administradores de entidades. 
	Este es el significado de las banderas:

	show_sql 				    -->habilita el registro de consultas
	format_sql 				  -->bonita imprime el SQL
	use_sql_comments 		-->añade un comentario explicativo
	----------------------------------------------------------------------------------------------
	<?xml version="1.0" encoding="UTF-8"?>
	<Configuration>
  	<Appenders>

    <!-- Console Appender -->
    <Console name="Console" target="SYSTEM_OUT">
      <PatternLayout pattern="%d{yyyy-MMM-dd HH:mm:ss a} [%t] %-5level %logger{36} - %msg%n" />
    </Console>

    <!-- File Appender -->
    <File name="File" fileName="c:/log/app.log">
      <PatternLayout pattern="%d{yyyy-MMM-dd HH:mm:ss a} [%t] %-5level %logger{36} - %msg%n" />
    </File>

  </Appenders>
  <Loggers>
    <!-- Log everything in hibernate -->
    <Logger name="org.hibernate" level="info" additivity="false">
      <AppenderRef ref="Console" />
    </Logger>

    <!-- Log SQL statements -->
    <Logger name="org.hibernate.SQL" level="debug" additivity="false">
      <AppenderRef ref="Console" />
      <AppenderRef ref="File" />
    </Logger>

    <!-- Log JDBC bind parameters -->
    <Logger name="org.hibernate.type.descriptor.sql" level="trace" additivity="false">
      <AppenderRef ref="Console" />
      <AppenderRef ref="File" />
    </Logger>

    <!-- Log custom packages -->
    <Logger name="com.boraji.tutorial.hibernate" level="debug" additivity="false">
      <AppenderRef ref="Console" />
      <AppenderRef ref="File" />
    </Logger>

    <Root level="error">
      <AppenderRef ref="Console" />
      <AppenderRef ref="File" />
    </Root>
  </Loggers>
	</Configuration>
***************************************************************************************************


***********************************************************************Cargar una Clase con FindById
  Referencia facturacionMatAccionAction.java
  Cliente cliente = ((CuentaCliente) facturacionMatAccionManager
              .findById(CuentaCliente.class, facturaArmada.getCuentaCliente().getId())).getCliente();
***************************************************************************************************



********************************************************************************Realizar NamedQuery
  @NamedQuery(name = "entregasCubiertasFechaBaja", query = "SELECT p FROM EntregaPartida p"
        + " WHERE p.esCubiertaPor.id= :entregaPartidaId"
        + " AND p.state.workflowDefinition.estadoActual != 'N' AND p.fechaBaja is null ")
  ---------------------------------------------------------------------------------------------
      @SuppressWarnings("unchecked")
      private List<Contrato> treeDataEntregaDatosAdicionales(List<EntregaPartida> entregaPartida){
        List<Contrato> tree = new ArrayList<Contrato>();
        
        for(EntregaPartida enPartida : entregaPartida){
          Map<String,Object> queryParams = new HashMap<String, Object>();
          queryParams.put("entregaPartidaId", enPartida.getId());   
          List<EntregaPartida> entregasCubiertas = aplicacionManager.findByNamedQuery("entregasCubiertasFechaBaja", queryParams);
          if(entregasCubiertas.size() == 0){
              tree.add(enPartida.getContrato());
          }else{
            tree.add(enPartida.getContrato());
            tree.addAll(treeDataEntregaDatosAdicionales(entregasCubiertas));
          }     
        } 
        return tree;
      }
***************************************************************************************************



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




******************************************************************************************@Anotaciones JPA
    -----------------------------------------------------------------------------------------@entity
    Crea una clase como una entidad de la btn-danger
    -------------------------------------------------------------------------@Table(name="noticias")
    Configura el nombre de la tabla de la base de datos que se va a persistir
    ---------------------------------------------------------------------------------------------@Id
    Configurar la llave primaria, se declara un atributo private int id, y se le agrega la anotacion @Id,
    el atributo debe ser unico en la entidad
    ---------------------------------------------------------------------------------@GeneratedValue
    Se agrega la anotacion @GeneratedValue, para que el atributo se genere de forma automatica autoincrementable,
    se pasa como parametro la forma como se va a generar la llave primaria, por ser mysql es Identity, para oracle 
    es sequence
      @Id
      @GeneratedValue(strategy=GenerationType.IDENTITY)
      private int id;

      referencias-->https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/jpa-primary-key.html
    -----------------------------------------------------------------------------------------@column
    JPA identifique que columna mapea contra cada atributo de la clase y es aquí donde entra @Column.
    Primero que nada algo importante: si el nombre de nuestro atributo en nuestra entidad, mapea 
    con un campo de una tabla y tienen el mismo nombre, en este caso se puede omitir la anotación 
    @Column.
    Se usa la anotación @Column para mapear el atributo fecha de nuestra entidad, a la columna 
    fechaRegistro de nuestra tabla Noticias. Tambien podríamos especificar la longitud para el 
    atributo titulo. Entonces nuestra clase de entidad quedaría de la siguiente forma:
      @Column(name="titulo",length=250,nullable=false)
        private String titulo;
        
        @Column(name="fechaRegistro")
        private Date fecha;

    Algunas propiedades que podemos definirle a @Column son las siguientes:

      name: Permite establecer el nombre del campo de la tabla de la base de datos que 
        mapeará el atributo donde se aplique la anotación @Column. A pesar de que ningún 
        atributo de @Column es obligatorio, este atributo siempre recomiendo establecerlo.
      length: Permite definir la longitud de la columna en caracteres, solo aplica para Strings.
      nullable: Crea una restricción en la tabla (Not Null) para impedir que se inserten 
      valores nulos.
**********************************************************************************************************



***************************************************************************Utilizando metodo createQuery()
**********************************************************************************Almacenando en una lista
  SessionFactory sf = manager.getDao().getSes().getSessionFactory();
  Session session = sf.openSession();
  Query query = session.createQuery("from CuentaCliente ");
  List<CuentaCliente> list = query.list();
  CuentaCliente cuenta = (CuentaCliente)list.get(0);
  System.out.println(cuenta);
  ---------------------------------------------------------------------------------------Con parametros
    SessionFactory sf = manager.getDao().getSes().getSessionFactory();
    Session session = sf.openSession();
    Query query = session.createQuery("from CuentaCliente where id = :id");
    query.setParameter("id", new Long(25801169));
    List<CuentaCliente> list = query.list();
    CuentaCliente cuenta = (CuentaCliente)list.get(0);
    System.out.println(cuenta);
  **********************************************************************************************Update
    SessionFactory sf = manager.getDao().getSes().getSessionFactory();
    Session session = sf.openSession();
    session.getTransaction().begin();
    Query query = session.createQuery("update CuentaCliente set debitoautomatico = 0  where id = :id");
    query.setParameter("id", new Long(25801169));
    int result = query.executeUpdate();
    session.getTransaction().commit();
    System.out.println(result); 
  **********************************************************************************************Delete

    SessionFactory sf = manager.getDao().getSes().getSessionFactory();
    Session session = sf.openSession();
    session.getTransaction().begin();
    Query query = session.createQuery("delete ZonaProducto where id = :id");
    query.setParameter("id", new Long(34019633));
    int result = query.executeUpdate();
    session.getTransaction().commit();
    System.out.println(result);

  ***********************************************************************************************Insert
    ****De forma masiva desde una consultas
    SessionFactory sf = manager.getDao().getSes().getSessionFactory();
    Session session = sf.openSession();
    session.getTransaction().begin();
    Query query = session.createQuery("insert into zona_producto (id,fechaalta,nombre, codigo, aplicaen) " +
        "select 185966948, fechaalta, nombre, codigo, aplicaen from zona_producto where id = :id");
    query.setParameter("id", new Long(34019635));
    int result = query.executeUpdate();
    session.getTransaction().commit();
    System.out.println(result);
    ***Si se quiere insertar un solo registro
  ****************************************************************Insertar un registro por metodo save
    SessionFactory sf = manager.getDao().getSes().getSessionFactory();
    Session session = sf.openSession();
    session.getTransaction().begin();
    WorkflowDefinition wfd = new WorkflowDefinition();
    wfd.setId(new Long(34564903));
    wfd.setUserAlta("eareiza");
    wfd.setEstadoActual("PR");
    wfd.setEntidad("ReciboRetencion");
    wfd.setEstadoOrigen("AI");
    wfd.setEstadoDestino("Para Prueba");
    wfd.setDuracionMaxima((long) 0);
    session.save(wfd);
    session.getTransaction().commit();
  
  Nota: Enlace de parámetros de hibernación
  Ya hemos visto esta característica en nuestros ejemplos anteriores. Puede enlazar parámetros como 
  declaraciones preparadas en cualquier lenguaje SQL normal. El enlace de parámetros de Hibernate 
  tiene más o menos las mismas ventajas que las declaraciones preparadas (por ejemplo, prevenir ataques 
   de inyección SQL). 
**********************************************************************************************************




*********************************************************************************************Clausula From
    SessionFactory sf = manager.getDao().getSes().getSessionFactory();
    Session session = sf.openSession();
    Query query = session.createQuery("from CuentaCliente ");
    List<CuentaCliente> list = query.list();
    CuentaCliente cuenta = (CuentaCliente)list.get(0);
    System.out.println(cuenta);
  **********************************************Clasificando completamente un nombre de claase de HQL
    SessionFactory sf = manager.getDao().getSes().getSessionFactory();
    Session session = sf.openSession();
    Query query = session.createQuery("from com.zeni.app.model.CuentaCliente ");
    List<CuentaCliente> list = query.list();
    CuentaCliente cuenta = (CuentaCliente)list.get(0);
    System.out.println(cuenta);
**********************************************************************************************************




***************************************************************************************Clausula Select HQL
  La cláusula SELECT proporciona más control sobre el conjunto de resultados que la cláusula from. Si 
  desea obtener algunas propiedades de los objetos en lugar del objeto completo, use la cláusula SELECT. 
  La siguiente es la sintaxis simple de usar la cláusula SELECT para obtener solo el campo first_name del objeto Employee:

    String hql = "SELECT E.firstName FROM Employee E";
    Query query = session.createQuery(hql);
    List results = query.list();

    NOTA: Es notable aquí que Employee.firstName es una propiedad del objeto Employee en lugar de un campo 
    de la tabla EMPLOYEE.
  ***************************************************************************************Clausula WHERE
    String hql = "FROM Employee E WHERE E.id = 10";
    Query query = session.createQuery(hql);
    List results = query.list();

  Ejemplo____________________________________________________________________________________________
    SessionFactory sf = manager.getDao().getSes().getSessionFactory();
    Session session = sf.openSession();
    Query query = session.createQuery("select cc.nroCuenta,cc.denominacionCuenta from CuentaCliente cc where cc.nroCuenta = '14251'");
    List<CuentaCliente> list = query.list();
  ************************************************************************************Clausula ORDER BY
    Para ordenar los resultados de su consulta HQL, deberá usar la cláusula ORDER BY . 
    Puede ordenar los resultados por cualquier propiedad de los objetos en el conjunto de resultados, 
    ya sea ascendente (ASC) o descendente (DESC). La siguiente es la sintaxis simple de usar la cláusula ORDER BY:

      String hql = "FROM Employee E WHERE E.id > 10 ORDER BY E.salary DESC";
      Query query = session.createQuery(hql);
      List results = query.list();
      
    Si desea ordenar por más de una propiedad, simplemente agregaría las propiedades adicionales al final 
    del pedido mediante una cláusula, separadas por comas de la siguiente manera:

      String hql = "FROM Employee E WHERE E.id > 10 " +
                   "ORDER BY E.firstName DESC, E.salary DESC ";
      Query query = session.createQuery(hql);
      List results = query.list();
    Ejemplo____________________________________________________________________________________________
      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
      Session session = sf.openSession();
      Query query = session.createQuery("from CuentaCliente cc where cc.sucursal.id = 3724 order by cc.id desc");
      List<CuentaCliente> list = query.list();
      CuentaCliente cuenta = (CuentaCliente)list.get(0);
      System.out.println(cuenta);
  ***********************************************************************************Clausula GROUP BY
    Esta cláusula permite a Hibernate extraer información de la base de datos y agruparla en función 
    de un valor de un atributo y, normalmente, utilizar el resultado para incluir un valor agregado. 
    La siguiente es la sintaxis simple de usar la cláusula GROUP BY:

    String hql = "SELECT SUM(E.salary), E.firtName FROM Employee E " +
                 "GROUP BY E.firstName";
    Query query = session.createQuery(hql);
    List results = query.list();

    Ejemplo________________________________________________________________________________________
      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
      Session session = sf.openSession();
      Query query = session.createQuery("select count(*), cc.sucursal.id from CuentaCliente cc group by sucursal.id");
      List<CuentaCliente> list = query.list();
**********************************************************************************************************




*****************************************************************************************Métodos agregados
  HQL admite una variedad de métodos agregados, similares a SQL. Funcionan de la misma manera en HQL que en SQL 
  y la siguiente es la lista de funciones disponibles:

    avg (nombre de propiedad)                 --> El promedio del valor de una propiedad.
 
    count (nombre de propiedad o *)           --> El número de veces que aparece una propiedad en los resultados.

    max (nombre de propiedad)                 --> El valor máximo de los valores de la propiedad.

    min (nombre de propiedad)                 --> El valor mínimo de los valores de la propiedad.
 
    sum (nombre de la propiedad)              -->La suma total de los valores de la propiedad.

  La palabra clave distinct solo cuenta los valores únicos en el conjunto de filas. La siguiente consulta 
  solo devolverá un recuento único:

    String hql = "SELECT count(distinct E.firstName) FROM Employee E";
    Query query = session.createQuery(hql);
    List results = query.list();
************************************************************************************************************




**********************************************************************************Paginación usando Consulta
  Hay dos métodos de la interfaz de consulta para paginación.

    Query setFirstResult(int startPosition)

      Este método toma un número entero que representa la primera fila de su conjunto de resultados, 
      comenzando con la fila 0.

    Query setMaxResults(int maxResult)

      Este método le dice a Hibernate que recupere un número fijo maxResults de objetos.

    Usando los dos métodos anteriores juntos, podemos construir un componente de paginación en nuestra 
    aplicación web o Swing. El siguiente es el ejemplo, que puede extender para obtener 10 filas a la vez:

      String hql = "FROM Employee";
      Query query = session.createQuery(hql);
      query.setFirstResult(1);
      query.setMaxResults(10);
      List results = query.list();

    Ejemplo________________________________________________________________________________________
      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
      Session session = sf.openSession();
      Query query = session.createQuery("from CuentaCliente");
      query.setFirstResult(1);
      query.setMaxResults(10);
      //select count(*), sucursal_id from cuenta_cliente group by sucursal_id
      List<CuentaCliente> list = query.list();
      //CuentaCliente cuenta = (CuentaCliente)list.get(0);
      System.out.println(list.toString());
*************************************************************************************************************


*****************************************************************************************************Criteria
  Hibernate proporciona formas alternativas de manipular objetos y, a su vez, datos disponibles en tablas 
  RDBMS. Uno de los métodos es Criteria API, que le permite crear un objeto de consulta de criterios mediante 
  programación donde puede aplicar reglas de filtración y condiciones lógicas.

  La interfaz Hibernate Session proporciona el método createCriteria () , que se puede usar para crear un objeto 
  Criteria que devuelve instancias de la clase del objeto de persistencia cuando la aplicación ejecuta una consulta 
  de criterios.

  El siguiente es el ejemplo más simple de una consulta de criterios que es una, que simplemente devolverá cada 
  objeto que corresponda a la clase Employee.

    Criteria cr = session.createCriteria(Employee.class);
    List results = cr.list();
    Ejemplo_____________________________________________________________________________________________

      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
      Session session = sf.openSession();
      Criteria cr = session.createCriteria(CuentaCliente.class);
      cr.setFirstResult(1);
      cr.setMaxResults(10);
      List<CuentaCliente> list = cr.list();
      System.out.println(list.toString());

  *****************************************************************************Restricciones con criterios
    Puede usar el método add () disponible para el objeto Criteria para agregar restricción para una 
    consulta de criterios. El siguiente es el ejemplo para agregar una restricción para devolver los 
    registros con un salario igual a 2000:

      Criteria cr = session.createCriteria(Employee.class);
      cr.add(Restrictions.eq("salary", 2000));
      List results = cr.list();

    Los siguientes son algunos ejemplos más que cubren diferentes escenarios y se pueden usar 
    según el requisito:

      Criteria cr = session.createCriteria(Employee.class);

      // To get records having salary more than 2000
      cr.add(Restrictions.gt("salary", 2000));
      Ejemplo___________________________________________________________________________________
        SessionFactory sf = manager.getDao().getSes().getSessionFactory();
            Session session = sf.openSession();
            Criteria cr = session.createCriteria(CuentaCliente.class);
            cr.add(Restrictions.gt("nroCuenta", 50000));
            cr.setFirstResult(1);
            cr.setMaxResults(10);
            List<CuentaCliente> list = cr.list();
            System.out.println(list.toString());

      // To get records having salary less than 2000
      cr.add(Restrictions.lt("salary", 2000));
      Ejemplo___________________________________________________________________________________
        SessionFactory sf = manager.getDao().getSes().getSessionFactory();
            Session session = sf.openSession();
            Criteria cr = session.createCriteria(CuentaCliente.class);
            cr.add(Restrictions.lt("nroCuenta", 15000));
            cr.setFirstResult(1);
            cr.setMaxResults(10);
            List<CuentaCliente> list = cr.list();

      // To get records having fistName starting with zara
      cr.add(Restrictions.like("firstName", "zara%"));
      Ejemplo___________________________________________________________________________________
          SessionFactory sf = manager.getDao().getSes().getSessionFactory();
            Session session = sf.openSession();
            
            //select count(*), sucursal_id from cuenta_cliente group by sucursal_id
            
            //CuentaCliente cuenta = (CuentaCliente)list.get(0);
            Criteria cr = session.createCriteria(CuentaCliente.class);
            // To get records having salary less than 2000
            cr.add(Restrictions.like("denominacionCuenta", "Aceitera%"));
            cr.setFirstResult(1);
            cr.setMaxResults(10);
            List<CuentaCliente> list = cr.list();
            System.out.println(list.toString());

      // Case sensitive form of the above restriction.
      cr.add(Restrictions.ilike("firstName", "zara%"));

      // To get records having salary in between 1000 and 2000
      cr.add(Restrictions.between("salary", 1000, 2000));
      Ejemplo___________________________________________________________________________________
        SessionFactory sf = manager.getDao().getSes().getSessionFactory();
            Session session = sf.openSession();
            
            //select count(*), sucursal_id from cuenta_cliente group by sucursal_id
            
            //CuentaCliente cuenta = (CuentaCliente)list.get(0);
            Criteria cr = session.createCriteria(CuentaCliente.class);
            // To get records having salary less than 2000
            cr.add(Restrictions.between("nroCuenta", 50000, 69999 ));
            cr.setFirstResult(1);
            cr.setMaxResults(10);
            List<CuentaCliente> list = cr.list();
            System.out.println(list.toString());

      // To check if the given property is null
      cr.add(Restrictions.isNull("salary"));

      // To check if the given property is not null
      cr.add(Restrictions.isNotNull("salary"));

      // To check if the given property is empty
      cr.add(Restrictions.isEmpty("salary"));

  *************************************************Condiciones AND y OR tilizando expresiones logicas
      Puede crear condiciones AND u OR utilizando las restricciones de LogicalExpression de la siguiente manera:

      Criteria cr = session.createCriteria(Employee.class);

      Criterion salary = Restrictions.gt("salary", 2000);
      Criterion name = Restrictions.ilike("firstNname","zara%");

      // To get records matching with OR conditions
      LogicalExpression orExp = Restrictions.or(salary, name);
      cr.add( orExp );

      // To get records matching with AND conditions
      LogicalExpression andExp = Restrictions.and(salary, name);
      cr.add( andExp );

      List results = cr.list();
      Ejemplo___________________________________________________________________________________
        SessionFactory sf = manager.getDao().getSes().getSessionFactory();
            Session session = sf.openSession();
            
            //select count(*), sucursal_id from cuenta_cliente group by sucursal_id
            
            //CuentaCliente cuenta = (CuentaCliente)list.get(0);
            Criteria cr = session.createCriteria(CuentaCliente.class);
            Criterion nroCuenta = Restrictions.gt("nroCuenta", 50000) ;
            Criterion nombre = Restrictions.like("denominacionCuenta", "Aceitera%");
            LogicalExpression andExp = Restrictions.and(nroCuenta , nombre);
            cr.add(andExp);
            List<CuentaCliente> list = (List<CuentaCliente>) cr.list();
            System.out.println(list.toString());
  **************************************************************************************Sentencia ORDER
      Ordenar los resultados
      Criteria API proporciona la clase org.hibernate.criterion.Order para ordenar su conjunto de 
      resultados en orden ascendente o descendente, de acuerdo con una de las propiedades de su objeto. 
      Este ejemplo demuestra cómo usaría la clase Order para ordenar el conjunto de resultados:

      Criteria cr = session.createCriteria(Employee.class);

      // To get records having salary more than 2000
      cr.add(Restrictions.gt("salary", 2000));

      // To sort records in descening order
      cr.addOrder(Order.desc("salary"));

      // To sort records in ascending order
      cr.addOrder(Order.asc("salary"));

      List results = cr.list();
*************************************************************************************************************



*************************************************************************************Metodo load() de Session
    *********************************************************************************************
    1. Entidad de carga de hibernación - session.load ()*****************************************
    La Sessioninterfaz de Hibernate proporciona varios load()métodos sobrecargados para cargar entidades 
    desde la base de datos.

    Ejemplo___________________________________________________________________________________
      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
                Session session = sf.openSession();
                session.beginTransaction();
                CuentaCliente cc = (CuentaCliente)session.load(CuentaCliente.class, new Long(32230707));
                System.out.println(cc.getDenominacionCuenta());
                session.getTransaction().commit();

      ---------------------------------------------------------------------------------------
      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
              Session sessionOne = sf.openSession();
              sessionOne.beginTransaction();
              
              //Create new Employee object
              EmpleadoEntity emp = ( EmpleadoEntity ) sessionOne.load(EmpleadoEntity.class, 1);
              
              System.out.println(emp.getLastName()+" "+emp.getFirstName());
              emp.setFirstName("Elvis");
              sessionOne.saveOrUpdate(emp);
              
              sessionOne.getTransaction().commit();
    *****************************************************************************************************
      3. Diferencia entre los métodos load () y get ()
      Por qué tenemos dos métodos para hacer el mismo trabajo. En realidad, esta es una pregunta frecuente en la entrevista también.

      La diferencia entre los métodos get y load radica en el valor de retorno cuando el identificador no existe en la base de datos.

      En el caso del get()método, obtendremos el valor de retorno como NULLsi el identificador estuviera ausente.
      Pero en caso de load()método, obtendremos una excepción de tiempo de ejecución.
    *****************************************************************************Metodo get() de Session
      El get()método es muy similar al load()método. Los get()métodos toman un identificador y un nombre de entidad 
      o una clase.

      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
                  Session session = sf.openSession();
                  session.beginTransaction();
                  CuentaCliente cc = (CuentaCliente)session.get(CuentaCliente.class, new Long(32230707));
                  System.out.println(cc.getDenominacionCuenta());
                  session.getTransaction().commit();
*************************************************************************************************************



**************************************************Actualizar entidades de Hibernate usando el método refresh ()
  A veces enfrentamos una situación en la que la base de datos de la aplicación se modifica con alguna 
  aplicación / agente externo y, por lo tanto, la entidad de hibernación correspondiente en su aplicación 
  en realidad no está sincronizada con su representación de la base de datos, es decir, tener datos 
  antiguos. En este caso, puede usar el session.refresh() método para volver a llenar la entidad con los 
  últimos datos disponibles en la base de datos .

  Puede usar uno de los métodos refresh () en la interfaz de sesión para actualizar una instancia de un objeto 
  persistente, de la siguiente manera:

    public void refresh(Object object) throws HibernateException
    public void refresh(Object object, LockMode lockMode) throws HibernateException
***************************************************************************************************************




******************************************************Fusionar entidades de Hibernate usando el método merge ()
  El método merge()hace exactamente lo contrario de lo que refresh() hace, es decir, actualiza la base de datos
   con valores de una entidad separada. El método de actualización estaba actualizando la entidad con la última 
   información de la base de datos. Básicamente, ambos son exactamente opuestos.

  La fusión se realiza cuando desea que una entidad separada cambie nuevamente al estado persistente , con los 
  cambios de la entidad separada migrados a (o anulando) la base de datos. Las firmas de método para las 
  operaciones de fusión son:

      Object merge(Object object)
      Object merge(String entityName, Object object)
**************************************************************************************************************



***********************************************Hibernate 4 - Obtenga referencia de entidad para carga diferida


  La carga diferida es un patrón de diseño comúnmente utilizado en la programación de computadoras para diferir 
  la inicialización de un objeto hasta el punto en el que se necesita. Sabemos que en hibernación, la carga diferida 
  se puede realizar especificando "fetch = FetchType.LAZY" en las anotaciones de mapeo de hibernación. p.ej

      @ManyToOne ( fetch = FetchType.LAZY )
      @JoinColumns( {
              @JoinColumn(name="fname", referencedColumnName = "firstname"),
              @JoinColumn(name="lname", referencedColumnName = "lastname")
              } )
      public EmployeeEntity getEmployee() {
          return employee;
      }

    El punto es que se aplica solo cuando se define la asignación entre dos entidades . Si la entidad anterior se ha 
    definido en DepartmentEntity, si obtiene DepartmentEntity, EmployeeEntity se cargará de forma diferida.

    session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
             
            //Get only the reference of EmployeeEntity for now
            EmployeeEntity empGet = (EmployeeEntity) session.byId( EmployeeEntity.class ).getReference( 1 );
             
            System.out.println("No data initialized till now; Lets fetch some data..");
             
             
            //Now EmployeeEntity will be loaded from database when we need it
            System.out.println(empGet.getFirstName());
            System.out.println(empGet.getLastName());
             
            session.getTransaction().commit();
            HibernateUtil.shutdown();
*****************************************************************************************************************




**********************************************************************************1. Método save () de Hibernate
  En hibernación, generalmente utilizamos una de las dos versiones siguientes del método save () :

    public Serializable save(Object object) throws HibernateException
    public Serializable save(String entityName,Object object) throws HibernateException

  Ambos métodos save () toman una referencia de objeto transitoria (que no debe ser nula) como argumento. 
  El segundo método toma un parámetro adicional ' entityName ' que es útil en caso de que haya asignado múltiples 
  entidades a una clase Java. Aquí puede especificar qué entidad está guardando utilizando el método save () .

  Nota: Recuerde que no debe llamar al save()método en una entidad persistente (entidad asociada con cualquier 
  sesión de hibernación). Cualquier cambio realizado en la entidad persistente se guarda automáticamente.

  Ejemplo_________________________________________________________________________________________________
    @Entity
    @Table(name = "Empleado")
    public class EmpleadoEntity implements Serializable{


      
         public EmpleadoEntity() {
        super();
      }

      @Id
         @Column(name = "ID", unique = true, nullable = false)
         private Integer  employeeId;
          
         @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
         private String   firstName;
          
         @Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
         private String   lastName;
         //Get y Set
    }

    --------------------------------------------------------------------------------------------------
              SessionFactory sf = manager.getDao().getSes().getSessionFactory();
              Session sessionOne = sf.openSession();
              sessionOne.beginTransaction();
              
             //Create new Employee object
              EmployeeEntity emp = new EmployeeEntity();
              emp.setEmployeeId(1);
              emp.setFirstName("Lokesh");
              emp.setLastName("Gupta");
               
              //Save employee
              sessionOne.save(emp);
              
              sessionTwo.getTransaction().commit();
****************************************************************************************************************




****************************************************************************2. Hibernate saveOrUpdate () método
  En la discusión del save()método, nos olvidamos del caso en el que tuvimos que guardar la entidad persistente 
  en otra sesión y eso resultó en un error de clave duplicada. Ese también es un escenario válido.

  Para manejar tales casos, debe usar el saveOrUpdate()método. Estrictamente hablando, debe usar saveOrUpdate () 
  incluso con entidades no persistentes. Personalmente, no veo ningún daño al hacerlo. Sin embargo, puede hacerte 
  un poco descuidado. Así que ten cuidado.

  SessionFactory sf = manager.getDao().getSes().getSessionFactory();
              Session sessionOne = sf.openSession();
              sessionOne.beginTransaction();
              
              //Create new Employee object
              EmpleadoEntity emp = new EmpleadoEntity();
              emp.setEmployeeId(1);
              emp.setFirstName("Lokesh");
              emp.setLastName("Gupta");
               
              //Save employee
              sessionOne.save(emp); 
       
              sessionOne.getTransaction().commit();
              
              //Let's see what got updated in DB
              Session sessionTwo = sf.openSession();
              sessionTwo.beginTransaction();
               
              emp.setLastName("temp");
              //Save employee again second time
              sessionTwo.saveOrUpdate(emp);
              
              sessionTwo.getTransaction().commit();
***************************************************************************************************************
  



****************************************************************************Recuperar un dato tipo objeto de BD
 
  Object obj = (Object) BaseModel.getManager().executeSQL(sql);
  Double precioMAV = ((obj != null) ? Double.valueOf(obj.toString().replace("[","").replace("]", "")) : 0);    
***************************************************************************************************************




**********************************************************************Anotación de mapeo  @OneToOne de Hibernate
  Ejemplo:
    Un empleado puede tener una cuenta. Del mismo modo, una cuenta se asociará con un solo empleado. Es una relación uno a uno para este ejemplo.

  Varias técnicas compatibles
  En hibernar hay 3 formas de crear relaciones uno a uno entre dos entidades. De cualquier manera, tenemos que usar la anotación @OneToOne .

    La primera técnica es la más utilizada y utiliza una columna de clave externa en una de las tablas .
    La segunda técnica utiliza una solución bastante conocida de tener una tercera tabla para almacenar el mapeo entre las dos primeras tablas.
    La tercera técnica es algo nuevo que utiliza un valor de clave primaria común en ambas tablas .

  *****1. Hibernate mapeo uno a uno con asociación de clave foránea
      n este tipo de asociación, se crea una columna de clave externa en la entidad propietaria . Por ejemplo, si 
      hacemos que EmployeeEntity sea el propietario, se "ACCOUNT_ID"creará una columna adicional en la Employeetabla. 
      Esta columna almacenará la clave foránea para la Accounttabla.

      Ejemplo___________________________________________________________________________________
        ***EmpleadosEntity.java
            @Entity
            @Table(name = "empleados")
            public class EmpleadoEntity implements Serializable{

                 /**
               * 
               */
              private static final long serialVersionUID = 2840742003224736001L;

              public EmpleadoEntity() {
                super();
              }

              @Id
               @Column(name = "ID", unique = true, nullable = false)
               private Integer  id;
                
               @Column(name = "NOMBRE", unique = false, nullable = false, length = 100)
               private String   nombre;
                
               @Column(name = "APELLIDO", unique = false, nullable = false, length = 100)
               private String   apellido;    
               
               @Column(name = "EMAIL", unique = false, nullable = false, length = 100)
               private String   correo;

               @OneToOne
               @JoinColumn(name='dept_ID')
               private Departamentos depatamento;
               /*Get y set*/
            }
        ***Departamentos.java
            @Entity
            @Table(name = "departamentos")
            public class Departamentos {
              @Id
              @Column(name = "DEPTO_ID", unique = true, nullable = false)
              private int id;
              
              @Column(name = "acc_number", unique = false, nullable = false, length = 100)
              private String acc_number;

              public int getId() {
                return id;
              }

              public void setId(int id) {
                this.id = id;
              }

              public String getAcc_number() {
                return acc_number;
              }

              public void setAcc_number(String acc_number) {
                this.acc_number = acc_number;
              }

              public Departamentos() {
                super();
              }
            }

        ***Implementacion
            SessionFactory sf = manager.getDao().getSes().getSessionFactory();
              Session sessionOne = sf.openSession();
              sessionOne.beginTransaction();
              
              Departamentos dept = new Departamentos();
              dept.setId(2);
              dept.setAcc_number("222-222-22222");
              
              EmpleadoEntity emp = new EmpleadoEntity();
              emp.setId(1);
              emp.setCorreo("dirielfran@gmail.com");
              emp.setNombre("Elvis");
              emp.setApellido("Areiza");
              
              sessionOne.saveOrUpdate(dept);
              emp.setDepatamento(dept);
              sessionOne.saveOrUpdate(emp);
              
              sessionOne.getTransaction().commit()

  *****2. Hibernate el mapeo uno a uno con la tabla de unión común
      @Entity
      @Table(name = "empleados")
      public class EmpleadoEntity implements Serializable{

           /**
         * 
         */
        private static final long serialVersionUID = 2840742003224736001L;

        
        @OneToOne(cascade = CascadeType.ALL)
        @JoinTable(name="empleado_cuenta", joinColumns=@JoinColumn(name="empleado_id"), 
        inverseJoinColumns = @JoinColumn(name="dept_id"))
        private Departamentos departamento; 
        
        public EmpleadoEntity() {
          super();
        }

        @Id
        @Column(name = "ID", unique = true, nullable = false)
        private Integer  id;
          
        @Column(name = "NOMBRE", unique = false, nullable = false, length = 100)
        private String   nombre;
          
        @Column(name = "APELLIDO", unique = false, nullable = false, length = 100)
        private String   apellido;    
         
        @Column(name = "EMAIL", unique = false, nullable = false, length = 100)
        private String   correo;
          //Get y Set
      }
    -------------------------------------------------------------------------------------------------------
      @Entity
      @Table(name = "departamentos")
      public class Departamentos {
        @Id
        @Column(name = "DEPTO_ID", unique = true, nullable = false)
        private int dept_id;
        
        @Column(name = "acc_number", unique = false, nullable = false, length = 100)
        private String acc_number;
         //Get y Set
      }
    -------------------------------------------------------------------------------------------------------
      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
                Session sessionOne = sf.openSession();
                sessionOne.beginTransaction();
                
                Departamentos dept = new Departamentos();
                dept.setDept_id(5);
                dept.setAcc_number("555-555-555555");
                
                EmpleadoEntity emp = new EmpleadoEntity();
                emp.setId(5);
                emp.setCorreo("dirielfran@gmail.com");
                emp.setNombre("Elvis");
                emp.setApellido("Areiza");
                
                sessionOne.saveOrUpdate(dept);
                emp.setDepatamento(dept);
                sessionOne.saveOrUpdate(emp);
                
                sessionOne.getTransaction().commit();
  *****3. Hibernate mapeo uno a uno con clave primaria compartida
****************************************************************************************************************



*************************************************************************************Hibernate JPA Cascade Types
    ***Tipos de cascada JPA
        Los tipos de cascada compatibles con la Arquitectura de persistencia de Java son los siguientes:

        CascadeType.PERSIST : el tipo de cascada presist significa que las operaciones save () o persist () se 
        conectan en cascada a entidades relacionadas.
        CascadeType.MERGE : el tipo en cascada merge significa que las entidades relacionadas se fusionan cuando 
        se fusiona la entidad propietaria.
        CascadeType.REFRESH : el tipo en cascada refresh hace lo mismo para la operación refresh ().
        CascadeType.REMOVE : el tipo en cascada remove elimina todas las asociaciones de entidades relacionadas con 
        esta configuración cuando se elimina la entidad propietaria.
        CascadeType.DETACH : el tipo en cascada desconecta detach todas las entidades relacionadas si se produce 
        una " desconexión manual".
        CascadeType.ALL : el tipo de cascada all es una forma abreviada de todas las operaciones en cascada anteriores.
        No hay un tipo de cascada predeterminado en JPA . Por defecto no hay operaciones en cascada.
****************************************************************************************************************



**************************************************************************************Hibernate Criteria queries
    Referencias --> https://docs.jboss.org/hibernate/orm/3.6/reference/es-ES/html/querycriteria.html
                --> https://howtodoinjava.com/hibernate/hibernate-criteria-queries-tutorial/

    La API de criterios le permite construir un objeto de consulta de criterios mediante programación

    Ejemplo_______________________________________________________________________________________________
      Criteria crit = session.createCriteria(Product.class);
      List<Product> results = crit.list();

    2. Criterios de hibernación: uso de restricciones+++++++++++++++++++++++++++++++++++++++++++++++++++++
      La API de criterios facilita el uso de restricciones en sus consultas para recuperar objetos de forma selectiva; 
      por ejemplo, su aplicación podría recuperar solo productos con un precio superior a $ 30. Puede agregar estas 
      restricciones a un Criteriaobjeto con el add()método. El add()método toma un org.hibernate.criterion.Criterionobjeto 
      que representa una restricción individual. Puede tener más de una restricción para una consulta de criterios.

      2.1. Restricciones.eq () Ejemplo-------------------------------------------------------------------
        Para recuperar objetos que tienen un valor de propiedad que " iguala " su restricción, use el eq()método 
        en Restrictions, como sigue:

          Ejemplo___________________________________________________________________________________
            SessionFactory sf = manager.getDao().getSes().getSessionFactory();
                Session session = sf.openSession();
                session.beginTransaction();
                
                //2.1. Restricciones.eq () Ejemplo
                Criteria criterio = session.createCriteria(CuentaCliente.class);
                criterio.add(Restrictions.eq("nroCuenta", 14044));
                List<CuentaCliente> resultados = criterio.list(); 
                for (CuentaCliente cuenta : resultados) {
                  System.out.println("Cuenta: "+cuenta.getNroCuenta());
                }
                session.getTransaction().commit();
      

      2.2. Restricciones.ne () Ejemplo
        Para recuperar objetos que tienen un valor de propiedad " not equal to" su restricción, utilice el ne()método 
        de la Restrictionssiguiente manera:

        Ejemplo___________________________________________________________________________________
              //2.1. Restricciones.ne () Ejemplo
              Criteria criterio = session.createCriteria(CuentaCliente.class);
              criterio.add(Restrictions.ne("debitoAutomatico", false));
              criterio.setMaxResults(10);
              List<CuentaCliente> resultados = criterio.list();

      
      2.3. Ejemplo de Restrictions.like () y Restrictions.ilike ()
          En lugar de buscar coincidencias exactas, podemos recuperar todos los objetos que tienen una propiedad que 
          coincide con parte de un patrón dado. Para hacer esto, necesitamos crear una cláusula SQL LIKE, con like()
          el ilike()método o el método. El ilike()método no distingue entre mayúsculas y minúsculas.

              //2.3. Ejemplo de Restrictions.like () y Restrictions.ilike ()
              Criteria criterio = session.createCriteria(CuentaCliente.class);
              criterio.add(Restrictions.like("denominacionCuenta", "Nutrien%", MatchMode.ANYWHERE));
              criterio.setMaxResults(10);
              List<CuentaCliente> resultados = criterio.list(); 
              

          El ejemplo anterior utiliza un org.hibernate.criterion.MatchModeobjeto para especificar cómo hacer 
          coincidir el valor especificado con los datos almacenados. El MatchModeobjeto (una enumeración de tipo 
          seguro) tiene cuatro coincidencias diferentes

            ANYWHERE: Anyplace in the string
            END: The end of the string
            EXACT: An exact match
            START: The beginning of the string

     
      2.4. Ejemplo de Restrictions.isNull () y Restrictions.isNotNull ()
          Las restricciones isNull() y le isNotNull() permiten realizar una búsqueda de objetos que tienen 
          (o no tienen) valores de propiedad nulos.

          //2.4. Ejemplo de Restrictions.isNull () y Restrictions.isNotNull ()
          Criteria criterio = session.createCriteria(CuentaCliente.class);
          criterio.add(Restrictions.isNotNull("fechaBaja"));
          criterio.setMaxResults(10);
          List<CuentaCliente> resultados = criterio.list();

      
      2.5. Restrictions.gt (), Restrictions.ge (), Restrictions.lt () y Restrictions.le () ejemplos
          Varias de las restricciones son útiles para hacer comparaciones matemáticas. La comparación 
          mayor que es gt(), la comparación mayor que o igual es ge(), la comparación menor que es lt()
          y la comparación menor que o igual es le(). Podemos hacer una recuperación rápida de todos 
          los productos con precios superiores a $ 25 como este, confiando en las promociones tipo 
          Java para manejar la conversión a Double:

          Criteria crit = session.createCriteria(Product.class);
          crit.add(Restrictions.gt("price", 25.0));
          List<Product> results = crit.list(); 

     
      2.6. Combinando dos o más ejemplos de criterios
          Continuando, podemos comenzar a hacer consultas más complicadas con la API Criteria. Por ejemplo, 
          podemos combinar restricciones AND y OR en expresiones lógicas. Cuando agregamos más de una restricción 
          a una consulta de criterios, se interpreta como un AND, así:

              //2.6. Combinando dos o más ejemplos de criterios
              Criteria criterio = session.createCriteria(CuentaCliente.class);
              criterio.add(Restrictions.gt("nroCuenta", 50000));
              criterio.add(Restrictions.like("denominacionCuenta", "Establecimiento%", MatchMode.ANYWHERE));
              List<CuentaCliente> resultados = criterio.list(); 
          
          Si queremos tener dos restricciones que devuelvan objetos que satisfagan una o ambas restricciones, 
          debemos usar el or()método en la clase Restricciones, de la siguiente manera:

              //2.6. Combinando dos o más ejemplos de criterios
              Criteria criterio = session.createCriteria(CuentaCliente.class);
              Criterion cr1 = Restrictions.gt("nroCuenta", 50000);
              Criterion cr2 = Restrictions.like("denominacionCuenta", "Establecimiento%", MatchMode.ANYWHERE);
              LogicalExpression orExp = Restrictions.or(cr1, cr2);
              criterio.setMaxResults(10);
              criterio.add(orExp);
              List<CuentaCliente> resultados = criterio.list();

          La expresión lógica orExp que hemos creado aquí se tratará como cualquier otro criterio. Por lo tanto, 
          podemos agregar otra restricción a los criterios:

      
      2.7. Using Disjunction and Conjunction Objects with Criteria
          If we wanted to create an OR expression with more than two different criteria (for example, “price > 25.0 
          OR name like Mou% OR description not like blocks%”), we would use an org.hibernate.criterion.Disjunction 
          object to represent a disjunction.

          You can obtain this object from the disjunction() factory method on the Restrictions class. The disjunction 
          is more convenient than building a tree of OR expressions in code. To represent an AND expression with more 
          than two criteria, you can use the conjunction() method, although you can easily just add those to the Criteria 
          object. The conjunction can be more convenient than building a tree of AND expressions in code. Here is an 
          example that uses the disjunction:

              //2.7. Using Disjunction Objects with Criteria
              Criteria criterio = session.createCriteria(CuentaCliente.class);
              Criterion cr1 = Restrictions.ge("nroCuenta", 50000);
              Criterion cr2 = Restrictions.like("denominacionCuenta", "Establecimiento Los%", MatchMode.ANYWHERE);
              Criterion cr3 = Restrictions.le("nroCuenta", 50010);
              Disjunction disjunction = Restrictions.disjunction();
              disjunction.add(cr1);
              disjunction.add(cr2);
              criterio.add(disjunction);
              List<CuentaCliente> resultados = criterio.list();


              //2.7. Using Conjunction Objects with Criteria
              Criteria criterio = session.createCriteria(CuentaCliente.class);
              Criterion cr1 = Restrictions.ge("nroCuenta", 50000);
              Criterion cr2 = Restrictions.like("denominacionCuenta", "Establecimiento Los%", MatchMode.ANYWHERE);
              Criterion cr3 = Restrictions.le("nroCuenta", 50010);
              Conjunction conjunction = Restrictions.conjunction();
              Disjunction disjunction = Restrictions.disjunction();
              conjunction.add(cr1);
              conjunction.add(cr3);
              disjunction.add(conjunction);
              disjunction.add(cr2);
              criterio.add(disjunction);
              List<CuentaCliente> resultados = criterio.list();

              //Otra forma, mas compacta de hacerlo
               //2.7. Using Disjunction and conjunction Objects with Criteria
              List<CuentaCliente> resultados = session.createCriteria(CuentaCliente.class)
                  .add(Restrictions.disjunction()
                      .add(Restrictions.conjunction()
                          .add(Restrictions.ge("nroCuenta", 50000))
                          .add(Restrictions.le("nroCuenta", 50010)))
                      .add(Restrictions.like("denominacionCuenta", "Establecimiento Los%", MatchMode.ANYWHERE))).list();
        
        2.8.- Agrupacion logica
          List<CuentaCliente> resultados = session.createCriteria(CuentaCliente.class)
                  .add(Restrictions.like("denominacionCuenta", "Establecimiento Los%", MatchMode.ANYWHERE))
                  .add(Restrictions.or(Restrictions.and(Restrictions.ge("nroCuenta",50000),
                                      Restrictions.le("nroCuenta", 50010)), 
                              Restrictions.isNotNull("fechaBaja"))).list();

          ***Utilizando DisJunction y Conjunction
              @SuppressWarnings("unchecked")
              List<CuentaCliente> resultados = session.createCriteria(CuentaCliente.class)
                      .add(Restrictions.like("denominacionCuenta", "Establecimiento Los%", MatchMode.ANYWHERE))
                      .add(Restrictions.disjunction().add(Restrictions.conjunction().add(Restrictions.ge("nroCuenta", 50000))
                                                      .add(Restrictions.le("nroCuenta", 50010)))
                                      .add(Restrictions.isNotNull("fechaBaja"))).list(); 

        2.9.- Obtener un criterio de una instancia Property. Puede crear una Property llamando a Property.forName().
            //2.9.- Obtener un criterio de una instancia Property. Puede crear una Property llamando a Property.forName().
            Property propiedad = Property.forName("userAlta");
            
            List<CuentaCliente> resultados = session.createCriteria(CuentaCliente.class)
                .add(propiedad.in(new String[] {"sistemas", "jfigue"})).list();

        2.10.- Orden de los resultados
            Puede ordenar los resultados usando org.hibernate.criterion.Order.

            List<CuentaCliente> resultados = session.createCriteria(CuentaCliente.class)
                .add(propiedad.in(new String[] {"sistemas", "jfigue"}))
                .addOrder(propiedad.desc()).list();

        2.11.- La clase org.hibernate.criterion.Example le permite construir un criterio de consulta a partir de una instancia dada.

            La clase org.hibernate.criterion.Example le permite construir un criterio de consulta a partir de una instancia dada.

                Banco banco = new Banco();
                banco.setVersion(1);
                banco.setState(null);
                banco.setUserModif("jnavarro");
                
                List<Banco>  resultados = session.createCriteria(Banco.class)
                    .add(Example.create(banco)).list();

            Las propiedades de versión, los identificadores y las asociaciones se ignoran. Por defecto, las propiedades 
            valuadas como nulas se excluyen.

                Puede modificar la aplicación del Example.

                Example example = Example.create(cat)
                    .excludeZeroes()           //exclude zero valued properties
                    .excludeProperty("color")  //exclude the property named "color"
                    .ignoreCase()              //perform case insensitive string comparisons
                    .enableLike();             //use like for string comparisons
                List results = session.createCriteria(Cat.class)
                    .add(example)
                    .list();

        2.12.- Proyecciones, agregación y agrupamiento
            La clase org.hibernate.criterion.Projections es una fábrica de instancias de Projection. Puede aplicar una 
            proyección a una consulta llamando a setProjection().

                 @SuppressWarnings("unchecked")
                  List resultados = session.createCriteria(CuentaCliente.class)
                      .setProjection( Projections.projectionList()
                          .add(Projections.rowCount() )
                          .add( Projections.max("nroCuenta"))
                          .add( Projections.avg("nroCuenta"))
                          .add( Projections.groupProperty("userAlta")))
                      .add( Restrictions.ge("nroCuenta", 50000))
                      .add( Restrictions.le("nroCuenta", 70000))
                      .list();

            No es necesario ningún "agrupamiento por" explícito en una consulta por criterios. Ciertos tipos de proyecciones 
            son definidos para ser proyecciones agrupadas, que además aparecen en la cláusula SQL group by.

            Puede asignar un alias a una proyección de modo que el valor proyectado pueda ser referido en restricciones 
            u ordenamientos. Aquí hay dos formas diferentes de hacer esto:

                List results = session.createCriteria(Cat.class)
                    .setProjection( Projections.alias( Projections.groupProperty("color"), "colr" ) )
                    .addOrder( Order.asc("colr") )
                    .list();
                List results = session.createCriteria(Cat.class)
                    .setProjection( Projections.groupProperty("color").as("colr") )
                    .addOrder( Order.asc("colr") )
                    .list();

            ***Los métodos alias() y as() simplemente envuelven una instancia de proyección en otra instancia de Projection 
            con alias. Como atajo, puede asignar un alias cuando agregue la proyección a una lista de proyecciones:

                  @SuppressWarnings("unchecked")
                  List resultados = session.createCriteria(CuentaCliente.class)
                            .setProjection( Projections.projectionList()
                                .add(Projections.rowCount(), "Contador" )
                                .add( Projections.max("nroCuenta"), "Maximo" )
                                .add( Projections.avg("nroCuenta"), "Promedio" )
                                .add( Projections.groupProperty("userAlta")))
                            .add( Restrictions.ge("nroCuenta", 50000))
                            .add( Restrictions.le("nroCuenta", 70000))
                            .addOrder( Order.desc("Contador"))
                            .list();
                  -----------------------------------------------------------------          
                  List results = session.createCriteria(Domestic.class, "cat")
                      .createAlias("kittens", "kit")
                      .setProjection( Projections.projectionList()
                          .add( Projections.property("cat.name"), "catName" )
                          .add( Projections.property("kit.name"), "kitName" )
                      )
                      .addOrder( Order.asc("catName") )
                      .addOrder( Order.asc("kitName") )
                      .list();

            ***También puede usar Property.forName() para expresar proyecciones:
                @SuppressWarnings("unchecked")
                List<CuentaCliente> cuentas = session.createCriteria(CuentaCliente.class)
                    .setProjection(Projections.projectionList()
                            .add(Projections.rowCount(), "Contador")
                            .add(Property.forName("nroCuenta").avg().as("Promedio"))
                            .add(Property.forName("nroCuenta").max().as("Maximo"))
                            .add(Property.forName("nroCuenta").min().as("Minimo")))
                    .addOrder(Order.desc("Contador"))
                    .list();

        2.13.- Consultas y subconsultas separadas
            La clase DetachedCriteria le permite crear una consulta fuera del ámbito de una sesión y luego 
            ejecutarla usando una Session arbitraria.

            DetachedCriteria query = DetachedCriteria.forClass(Banco.class)
                  .add(Property.forName("userAlta").eq("jnavarro"));
              
            List<CuentaCliente> cuentas = query.getExecutableCriteria(session).setMaxResults(10).list();

            -------------------------------------------------------------------

            También puede utilizar una DetachedCriteria para expresar una subconsulta. Las instancias 
            de Criterion involucrando subconsultas se pueden obtener por medio de Subqueries o Property.

                DetachedCriteria avgCuenta = DetachedCriteria.forClass(CuentaCliente.class)
                    .setProjection(Projections.avg("nroCuenta"));
              
                List<CuentaCliente> cuentas = session.createCriteria(CuentaCliente.class)
                    .add(Property.forName("nroCuenta").ge(avgCuenta))
                    .setMaxResults(20).list();
              
                
                DetachedCriteria weights = DetachedCriteria.forClass(Cat.class)
                    .setProjection( Property.forName("weight") );
                session.createCriteria(Cat.class)
                    .add( Subqueries.geAll("weight", weights) )
                    .list();

            Las subconsultas correlacionadas tambień son posibles:

                DetachedCriteria avgWeightForSex = DetachedCriteria.forClass(Cat.class, "cat2")
                    .setProjection( Property.forName("weight").avg() )
                    .add( Property.forName("cat2.sex").eqProperty("cat.sex") );
                session.createCriteria(Cat.class, "cat")
                    .add( Property.forName("weight").gt(avgWeightForSex) )
                    .list();


        2.14.- Paginación a través del conjunto de resultados
            Un patrón de aplicación común que los criterios pueden abordar es la paginación a través del 
            conjunto de resultados de una consulta de base de datos. Hay dos métodos en la Criteria interfaz 
            para paginación, al igual que para Consulta: setFirstResult() y setMaxResults(). El setFirstResult() 
            método toma un número entero que representa la primera fila de su conjunto de resultados, comenzando 
            con la fila 0. Puede decirle a Hibernate que recupere un número fijo de objetos con el setMaxResults()
            método. Al usar ambos juntos, podemos construir un componente de paginación en nuestra aplicación 
            web o Swing.

                //2.14.- Paginación a través del conjunto de resultados
                  Criteria criterio = sessionOne.createCriteria(CuentaCliente.class);
                  criterio.setFirstResult(1);
                  criterio.setMaxResults(20);
                  List<CuentaCliente> cuentas = criterio.list();

        2.15.- Criterios de hibernación: obtenga un resultado único
            En ocasiones, sabe que solo devolverá cero o un objeto de una consulta determinada. Esto podría ser 
            porque está calculando un agregado o porque sus restricciones conducen naturalmente a un resultado 
            único. Si desea obtener una única referencia de Objeto en lugar de una Lista, el uniqueResult() método
            en el Criteria objeto devuelve un objeto o nulo. Si hay más de un resultado, el uniqueResult() método 
            arroja a HibernateException.

            El siguiente breve ejemplo demuestra que tiene un conjunto de resultados que habría incluido más 
            de un resultado, excepto que estaba limitado con el setMaxResults()método:

                  //2.15.- Criterios de hibernación: obtenga un resultado único
                  Criteria criterio = sessionOne.createCriteria(CuentaCliente.class)
                      .add(Restrictions.ge("nroCuenta", 50000))
                      .add(Restrictions.le("nroCuenta", 70000));                  
                  criterio.setMaxResults(1);
                  CuentaCliente cuenta = (CuentaCliente) criterio.uniqueResult();  

            Nota: tenga en cuenta que debe asegurarse de que su consulta devuelva solo uno o cero resultados 
            si utiliza el uniqueResult() método. De lo contrario, Hibernate lanzará una NonUniqueResultExceptionexcepción.

        2.16.- Criterios de hibernación: obtenga resultados distintos
            Si desea trabajar con resultados distintos de una consulta de criterios, Hibernate proporciona un 
            transformador de resultados para entidades distintas org.hibernate.transform.DistinctRootEntityResultTransformer, 
            lo que garantiza que no habrá duplicados en el conjunto de resultados de su consulta. En lugar de utilizar 
            SELECT DISTINCT con SQL, el transformador de resultados distinto compara cada uno de sus resultados utilizando 
            sus hashCode()métodos predeterminados y solo agrega esos resultados con códigos hash únicos a su conjunto de 
            resultados . Este puede o no ser el resultado que esperaría de una consulta DISTINCT SQL equivalente, por lo tanto, 
            tenga cuidado con esto .

                Criteria crit = session.createCriteria(Product.class);
                Criterion price = Restrictions.gt("price",new Double(25.0));
                crit.setResultTransformer( DistinctRootEntityResultTransformer.INSTANCE )
                List<Product> results = crit.list();
            
            Una nota de rendimiento adicional: la comparación se realiza en el código Java de Hibernate, no en la 
            base de datos, por lo que los resultados no únicos se seguirán transportando a través de la red.


        2.17.- Obtención de columnas seleccionadas
            Otro uso de las proyecciones es recuperar propiedades individuales, en lugar de entidades. 
            Por ejemplo, podemos recuperar solo el nombre y la descripción de nuestra tabla de productos, 
            en lugar de cargar toda la representación del objeto en la memoria.

                //2.17.- Obtención de columnas seleccionadas
                  Criteria criterio = sessionOne.createCriteria(CuentaCliente.class);
                  ProjectionList proj = Projections.projectionList();
                  proj.add(Projections.property("nroCuenta"));
                  proj.add(Projections.property("denominacionCuenta"));
                  criterio.setProjection(proj);
                  criterio.add(Restrictions.ge("nroCuenta", 50000));
                  criterio.add(Restrictions.le("nroCuenta", 60000));
                  criterio.setMaxResults(10);
                  List<Object[]> cuentas = criterio.list();
                  
                  
                  ////////////////Otra forma de hacerlo
                @SuppressWarnings("unchecked")
                List<Object[]> cuentas = sessionOne.createCriteria(CuentaCliente.class)
                      .setProjection(Projections.projectionList()
                          .add(Projections.property("nroCuenta"))
                          .add(Projections.property("denominacionCuenta")))
                      .add(Restrictions.ge("nroCuenta", 50000))
                      .add(Restrictions.le("nroCuenta", 70000))
                      .setMaxResults(10).list();
****************************************************************************************************************




******************************************************************************Hibernate Lenguaje de consulta HQL
    Referencia  --> https://howtodoinjava.com/hibernate/complete-hibernate-query-language-hql-tutorial/

    1. Sintaxis HQL
    La sintaxis HQL se define como una gramática ANTLR . Los archivos de gramática se incluyen en el directorio 
    de gramática de la descarga principal de Hibernate. ( ANTLR es una herramienta para construir analizadores 
    de lenguaje ). Vamos a describir la sintaxis de las cuatro operaciones CRUD fundamentales aquí:

        1.1. Declaración de actualización de HQL
        ACTUALIZAR altera los detalles de los objetos existentes en la base de datos. Las entidades en memoria, 
        administradas o no, no se actualizarán para reflejar los cambios resultantes de la emisión de declaraciones 
        de ACTUALIZACIÓN. Aquí está la sintaxis de la instrucción UPDATE:

               
            //1.1. Declaración de actualización de HQL
            Query query = sessionOne.createQuery("update EmpleadoEntity set email=:email where id=:idEmp");
            query.setString("email", "dirielfran@gmail.com");
            query.setLong("idEmp", 212883438);
            int resultado = query.executeUpdate();

        1.2. Declaración de eliminación de HQL
        DELETE elimina los detalles de los objetos existentes de la base de datos. Las entidades en memoria no 
        se actualizarán para reflejar los cambios resultantes de las declaraciones DELETE. Esto también significa 
        que las reglas en cascada de Hibernate no se seguirán para las eliminaciones realizadas con HQL. Sin embargo, 
        si ha especificado eliminaciones en cascada en el nivel de la base de datos (ya sea directamente o mediante 
        Hibernate, utilizando la @OnDelete anotación), la base de datos seguirá eliminando las filas secundarias.

            //1.1. Declaración de actualización de HQL
            Query query = sessionOne.createQuery("delete from EmpleadoEntity where nombre=:nombre");
            query.setString("nombre", "PruebaElvis");
            int resultado = query.executeUpdate();

        1.3. Declaración de inserción de HQL
        Un INSERT HQL no se puede usar para insertar entidades arbitrarias directamente ; solo se puede usar para 
        insertar entidades construidas a partir de información obtenida de consultas SELECT (a diferencia del SQL 
        ordinario, en el que también se puede usar un comando INSERT para insertar datos arbitrarios en una tabla 
        como insertar valores seleccionados de otras tablas).

        El nombre de una entidad es ruta. Los nombres de propiedad son los nombres de propiedades de entidades 
        enumeradas en la ruta FROM de la consulta SELECT incorporada. La consulta select es una consulta HQL
        SELECT (como se describe en la siguiente sección).

        Como esta declaración HQL solo puede usar datos proporcionados por una selección HQL, su aplicación puede 
        ser limitada. Un ejemplo de cómo copiar usuarios a una tabla purgada antes de purgarlos realmente podría verse así:

            ejemplo de declaración de inserción hql
            Query query=session.createQuery("insert into purged_accounts(id, code, status) "+
                "select id, code, status from account where status=:status");
            query.setString("status", "purged");
            int rowsCopied=query.executeUpdate();

        1.4.-Operacion de seleccionados
              //1.4.-Operacion de seleccionados
              Query query = sessionOne.createQuery("from EmpleadoEntity where id=:idEmp");
              query.setLong("idEmp", 212883438);
              EmpleadoEntity empleado = (EmpleadoEntity) query.uniqueResult();


        1.5. HQL: paginación a través del conjunto de resultados
                  Query query = sessionOne.createQuery("from CuentaCliente where nroCuenta>:cuentaMax and nroCuenta<:cuentaMin");
                  query.setFirstResult(1);
                  query.setMaxResults(10);
                  query.setInteger("cuentaMax", 50000);
                  query.setInteger("cuentaMin", 50010);
                  List results = query.list();

        1.6. HQL - Obtenga un resultado único
            La interfaz de consulta de HQL proporciona un uniqueResult() método para obtener solo un objeto de una consulta HQL. 
            Aunque su consulta puede producir solo un objeto, también puede usar el uniqueResult()método con otros conjuntos de 
            resultados si limita los resultados al primer resultado. Puede usar el setMaxResults()método discutido en la sección anterior.

            El uniqueResult() método en el objeto Consulta devuelve un solo objeto, o nulo si no hay resultados. Si hay más de 
            un resultado, el uniqueResult() método arroja a NonUniqueResultException.

              String hql = "from Product where price>25.0";
              Query query = session.createQuery(hql);
              query.setMaxResults(1);
              Product product = (Product) query.uniqueResult();

        ***Otro tutorial de HQL  -->https://docs.jboss.org/hibernate/orm/3.6/reference/es-ES/html/queryhql.html

              16.2. La cláusula from
                  *** alias. Por ejemplo:

                      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
                      Session session = sf.openSession();
                      session.beginTransaction();
                      
                            Query query = session.createQuery("from CuentaCliente as cuenta where id=:idCta");
                            query.setLong("idCta", 28335839);
                            CuentaCliente cuenta = (CuentaCliente) query.uniqueResult();
                      System.out.println("Cuenta: "+cuenta.getNroCuenta());
                                    
                      session.getTransaction().commit();  
                  
                   La palabra clave as es opcional. También podría escribir:

                      from CuentaCliente cuenta

                  Pueden aparecer múltiples clases, lo que causa un producto cartesiano o una unión "cruzada" (cross join).

                      //16.2. La cláusula from con alias y multiples clases
                      Query query = session.createQuery("from CuentaCliente cc, Cliente c where cc.id=:idCta and c.id=:idCli");
                      query.setLong("idCta", 28335839);
                      query.setLong("idCli", 28335813);
                      Object[] cuentaCli = (Object[]) query.uniqueResult();


                      from Formula as form, Parameter as param

                  Se considera como una buena práctica el nombrar los alias de consulta utilizando una inicial en minúsculas, 
                  consistente con los estándares de nombrado de Java para las variables locales (por ejemplo, domesticCat).

              16.3. Asociaciones y uniones (joins)
                  También puede asignar alias a entidades asociadas o a elementos de una colección de valores utilizando 
                  una join. Por ejemplo:

                        // 16.3. Asociaciones y uniones (joins)
                        Query query = session.createQuery("from CuentaCliente cc inner join cc.cliente ccc ");
                        query.setMaxResults(10);
                        List<Object[]> cuentaCli = query.list();
                      
                        for (Object[] obj : cuentaCli) {
                          CuentaCliente cuenta = (CuentaCliente) obj[0];
                          Cliente cliente = (Cliente) obj[1];
                          System.out.println("Cuenta: "+cuenta.getNroCuenta());
                          System.out.println("Cliente: "+cliente.getRazonSocial());
                        }
                        ------------------------------------------------------------------
                      //16.2. La cláusula from con alias y multiples clases
                      Query query = session.createQuery("from CuentaCliente cc left join cc.cliente ccc " +
                                        "left join cc.cliente.crmFichaCliente crm");
                      //query.setLong("idCta", 28335839);
                      //query.setLong("idCli", 28335813);
                      query.setMaxResults(10);
                      List<Object[]> cuentaCli = query.list();
                      
                      for (Object[] obj : cuentaCli) {
                        CuentaCliente cuenta = (CuentaCliente) obj[0];
                        Cliente cliente = (Cliente) obj[1];
                        CRMFichaCliente ficha = (CRMFichaCliente) obj[2];
                        System.out.println("Cuenta: "+cuenta.getNroCuenta());
                        System.out.println("Cliente: "+cliente.getRazonSocial());
                        System.out.println("Ficha: "+ficha.getId());
                      }

                  
                  ***Los tipos de uniones soportadas se tomaron prestados de ANSI SQL

                      inner join

                      left outer join

                      right outer join

                      full join (no es útil usualmente) 

                  ***Si está utilizando una recuperación perezosa a nivel de propiedad (con instrumentación de código byte), 
                      es posible forzar a Hibernate a traer las propiedades perezosas inmediatamente utilizando fetch all properties.

                      //16.2. La cláusula from con alias y multiples clases
                      Query query = session.createQuery("from CuentaCliente fetch all properties ");
                      query.setMaxResults(10);
                      List<CuentaCliente> cuentaCli = query.list();
                      
                      for (CuentaCliente obj : cuentaCli) {
                        System.out.println("Cuenta: "+obj.getNroCuenta());
                      }
              16.4. Formas de sintaxis unida
                  HQL soporta dos formas de unión de asociación: implicit y explicit.

                  Las consultas que se mostraron en la sección anterior todas utilizan la forma explicit, en 
                  donde la palabra clave join se utiliza explícitamente en la claúsula from. Esta es la forma recomendada.

                  La forma implicit no utiliza la palabra clave join. Las asociaciones se "desreferencian" utilizando 
                  la notación punto. Uniones implicit pueden aparecer en cualquiera de las cláusulas HQL. La unión 
                  implicit causa uniones internas (inner joins) en la declaración SQL que resulta.

                      //16.2. La cláusula from con alias y multiples clases
                      Query query = session.createQuery("from CuentaCliente as cuenta where cuenta.cliente.id =:idCli ");
                      query.setLong("idCli", 28335813);
                      query.setMaxResults(10);
                      List<CuentaCliente> cuentaCli = query.list();
                      
                      for (CuentaCliente obj : cuentaCli) {
                        System.out.println("Cuenta: "+obj.getNroCuenta());
                      }  
              16.6. La cláusula select
                  ***La cláusula select escoge qué objetos y propiedades devolver en el conjunto de resultados de la 
                  consulta. Considere lo siguiente:

                      Query query = session.createQuery("select cliente from CuentaCliente as cuenta inner join cuenta.cliente as cliente ");
                      query.setMaxResults(10);
                      List<Cliente> cli = query.list();

                  ***La consulta seleccionará mates de otros Cats. Puede expresar esta consulta de una manera más 
                  compacta así:

                      select cuenta.cliente from CuentaCliente as cuenta

                  ***Las consultas pueden retornar propiedades de cualquier tipo de valor incluyendo propiedades del tipo componente:

                      select cat.name from DomesticCat cat
                      where cat.name like 'fri%'

                      select cust.name.firstName from Customer as cust

                  ***Las consultas pueden retornar múltiples objetos y/o propiedades como un array de tipo Object[],

                      
                      Query query = session.createQuery("select cliente.razonSocial, cuenta, act  from CuentaCliente as cuenta " +
                          "                               inner join cuenta.cliente as cliente" +
                          "                               inner join cuenta.actividad act");
                      query.setMaxResults(10);
                      List<Object[]> cli = query.list();


                      +++++++++++++++
                      List results = session.createQuery("from JavaClub3 ").list();
                      for (Iterator iter = results.iterator(); iter.hasNext();) {
                         JavaClub3 club3 = (JavaClub3) iter.next();
                         log.debug(club3);
                      }

                  ***Las consultas pueden retornar múltiples objetos y/o propiedades como una list
                      Query query = session.createQuery("select new list(cliente.razonSocial, cuenta, act)  from CuentaCliente as cuenta " +
                                          "                               inner join cuenta.cliente as cliente" +
                                          "                               inner join cuenta.actividad act");
                      query.setMaxResults(10);
                      List cli = query.list();

                      -------------------------------------------------------------------------------------
                          String queryString="select id, name from Permission ";
                          List<List<Object>> permission= session.createQuery(queryString)
                                .setResultTransformer(Transformers.TO_LIST).list();
                          //now you just expect two columns 
                          HashMap<Integer,String> map= new HashMap<Integer,String>();
                          for(List<Object> x: permission){ 
                               map.put((Integer)x.get(0),(String)x.get(1))
                          }

                  ***Esto es lo más útil cuando se usa junto con select new map:*********************************************************

                      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
                      Session session = sf.openSession();
                      session.beginTransaction();
                      Query query = session.createQuery("select new map(cuenta.nroCuenta as nroCuenta, cuenta.denominacionCuenta as denomunacion) from CuentaCliente cuenta");
                      query.setMaxResults(10);
                      List<Map<String, String>> lista = query.list();            
                      session.getTransaction().commit();


              16.7. Funciones de agregación
                  ***Las consultas HQL pueden incluso retornar resultados de funciones de agregación sobre propiedades:

                      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
                      Session session = sf.openSession();
                      session.beginTransaction();
                      Query query = session.createQuery("select avg(cuenta.nroCuenta) as Average, max(cuenta.nroCuenta) as maximo, " +
                          "                 min(cuenta.nroCuenta) as minimo, " +
                          "                 count(*)  from CuentaCliente as cuenta ");
          
                      Object[] obj = (Object[]) query.uniqueResult();
                  
                  ***Las funciones de agregación soportadas son:

                      avg(...), sum(...), min(...), max(...)

                      count(*)

                      count(...), count(distinct ...), count(all...)

                  ***Puede utilizar operadores aritméticos, concatenación y funciones SQL reconocidas en la cláusula select:

                      select cat.weight + sum(kitten.weight)
                      from Cat cat
                          join cat.kittens kitten
                      group by cat.id, cat.weight
                  
                      select firstName||' '||initial||' '||upper(lastName) from Person
                  
                  ***Las palabras clave distinct y all se pueden utilizar y tienen las misma semántica que en SQL.

                      select distinct cat.name from Cat cat

                      select count(distinct cat.name), count(cat) from Cat cat

              16.10. Expresiones
                  ***in y between pueden utilizarse así:

                      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
                      Session session = sf.openSession();
                      session.beginTransaction();
                      Query query = session.createQuery("from CuentaCliente cuenta where cuenta.denominacionCuenta between 'A' and 'B'");
                      query.setMaxResults(10);
                      List<CuentaCliente> cuenta = query.list();
                      //System.out.println(obj);

                      for (CuentaCliente object : cuenta) {
                        System.out.println(object);
                      }

                      session.getTransaction().commit();
                    --------------------------------------------------------------------------------------
                    
                      from DomesticCat cat where cat.name in ( 'Foo', 'Bar', 'Baz' )
                  ***Las formas negadas se pueden escribir así:

                      from DomesticCat cat where cat.name not between 'A' and 'B'
                      from DomesticCat cat where cat.name not in ( 'Foo', 'Bar', 'Baz' )

                  ***De manera similar, is null y is not null se pueden utilizar para probar valores nulos.

                      Los valores booleanos se pueden utilizar fácilmente en expresiones declarando substituciones de consulta HQL en la configuración de Hibernate:

                          <property name="hibernate.query.substitutions">true 1, false 0</property>

                      Esto remplazará las palabras clave true y false con los literales 1 y 0 en el SQL traducido de este HQL:

                          from Cat cat where cat.alive = true

              16.11. La cláusula order by
                  La lista retornada por una consulta se puede ordenar por cualquier propiedad de una clase retornada o componentes:

                      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
                      Session session = sf.openSession();
                      session.beginTransaction();
                      //from Cat cat where cat.kittens.size > 0
                      Query query = session.createQuery("from CuentaCliente cuenta order by cuenta.nroCuenta, cuenta.denominacionCuenta ");
                      query.setMaxResults(10);
                      List<CuentaCliente> cuenta = query.list();
                      //System.out.println(obj);

                      for (CuentaCliente object : cuenta) {
                        System.out.println(object.getNroCuenta());
                        System.out.println(object.getDenominacionCuenta());
                      }
                      session.getTransaction().commit(); 
                  
                  Los asc o desc opcionales indican ordenamiento ascendente o descendente respectivamente.

              16.12. La cláusula group by
                  Una consulta que retorna valores agregados se puede agrupar por cualquier propiedad de una clase retornada o componentes:

                      select cat.color, sum(cat.weight), count(cat)
                      from Cat cat
                      group by cat.color
                      
                      select foo.id, avg(name), max(name)
                      from Foo foo join foo.names name
                      group by foo.id
                  
                  ***Se permite también una cláusula having.

                      select cat.color, sum(cat.weight), count(cat)
                      from Cat cat
                      group by cat.color
                      having cat.color in (eg.Color.TABBY, eg.Color.BLACK)
                  
                  ***Las funciones SQL y las funciones de agregación SQL están permitidas en las cláusulas having y order by, 
                      si están soportadas por la base de datos subyacente (por ejemplo, no lo están en MySQL).

                      select cat
                      from Cat cat
                          join cat.kittens kitten
                      group by cat.id, cat.name, cat.other, cat.properties
                      having avg(kitten.weight) 
                      > 100
                      order by count(kitten) asc, sum(kitten.weight) desc
                  
                  ***La cláusula group by ni la cláusula order by pueden contener expresiones aritméticas. 
                      Hibernate tampocoo expande una entidad agrupada así que no puede escribir group by cat si 
                      todas las propiedades de cat son no-agregadas. Tiene que enumerar todas la propiedades 
                      no-agregadas explícitamente.

              16.13. Subconsultas
                  Para bases de datos que soportan subconsultas, Hibernate soporta subconsultas dentro de consultas. 
                  Una subconsulta se debe encerrar entre paréntesis (frecuentemente por una llamada a una función de 
                  agregación SQL). Incluso se permiten subconsultas correlacionadas (subconsultas que se refieren
                  a un alias en la consulta exterior).

                      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
                      Session session = sf.openSession();
                      session.beginTransaction();
                      //from Cat cat where cat.kittens.size > 0
                      Query query = session.createQuery("from CuentaCliente cuenta where cuenta.nroCuenta > " +
                          "(select avg(cuenta1.nroCuenta) from CuentaCliente cuenta1)");
                      query.setMaxResults(10);
                      List<CuentaCliente> cuenta = query.list();
                      //System.out.println(obj);

                      for (CuentaCliente object : cuenta) {
                        System.out.println(object.getNroCuenta());
                        System.out.println(object.getDenominacionCuenta());
                      }
                      session.getTransaction().commit();


                      from DomesticCat as cat
                      where cat.name = some (
                          select name.nickName from Name as name
                      )
                  
                      from Cat as cat
                      where not exists (
                          from Cat as mate where mate.mate = cat
                      )


                      from DomesticCat as cat
                      where cat.name not in (
                          select name.nickName from Name as name
                      )
                  

                      select cat.id, (select max(kit.weight) from cat.kitten kit)
                      from Cat as cat
                  
                  ***Note que las subconsultas HQL pueden ocurrir sólamente en las cláusulas select o where.

                  ***Note that subqueries can also utilize row value constructor syntax. See Sección 16.18, 
                      “Sintaxis del constructor de valores por fila” for more information.

              16.16. Consejos y Trucos
                  ***Puede contar el número de resultados de una consulta sin retornarlos:

                      SessionFactory sf = manager.getDao().getSes().getSessionFactory();
                      Session session = sf.openSession();
                      session.beginTransaction();
                      Long prueba = ((Long)session.createQuery("select count(*) from Actividad").iterate().next()).longValue();
                      System.out.println("El numero de registros: "+prueba);
                      session.getTransaction().commit();
                  
                  ***Para ordenar un resultado por el tamaño de una colección, utilice la siguiente consulta:

                      select usr.id, usr.name
                      from User as usr
                          left join usr.messages as msg
                      group by usr.id, usr.name
                      order by count(msg)


                  ***Si su base de datos soporta subselecciones, puede colocar una condición sobre el 
                  tamaño de selección en la cláusula where de su consulta:

                      from User usr where size(usr.messages) 
                      >= 1

                      Si su base de datos no soporta subselecciones, utilice la siguiente consulta:

                      select usr.id, usr.name
                      from User usr
                          join usr.messages msg
                      group by usr.id, usr.name
                      having count(msg) 
                      >= 1
                  
                  ****Como esta solución no puede retornar un User con cero mensajes debido a la unión interior, la siguiente forma también es útil:

                      select usr.id, usr.name
                      from User as usr
                          left join usr.messages as msg
                      group by usr.id, usr.name
                      having count(msg) = 0
                  
                  ***Las propiedades de un JavaBean pueden ser ligadas a los parámetros de consulta con nombre:

                      Query q = s.createQuery("from foo Foo as foo where foo.name=:name and foo.size=:size");
                      q.setProperties(fooBean); // fooBean has getName() and getSize()
                      List foos = q.list();
                  
                  ***Las colecciones son paginables usando la interfaz Query con un filtro:

                      Query q = s.createFilter( collection, "" ); // the trivial filter
                      q.setMaxResults(PAGE_SIZE);
                      q.setFirstResult(PAGE_SIZE * pageNumber);
                      List page = q.list();
                  
                  ***Los elementos de colección se pueden ordenar o agrupar usando un filtro de consulta:

                      Collection orderedCollection = s.filter( collection, "order by this.amount" );
                      Collection counts = s.filter( collection, "select this.type, count(this) group by this.type" );

            16.18. Sintaxis del constructor de valores por fila
                ***HQL soporta la utilización de la sintaxis row value constructor de SQL ANSI que a veces se denomina sintaxis tuple, aunque puede que la base de datos subyacentes no soporte esa noción. Aquí estamos refiriéndonos generalmente a las comparaciones multivaluadas que se asocian típicamente con los componentes. Considere una entidad Persona, la cual define un componente de nombre:

                    from Person p where p.name.first='John' and p.name.last='Jingleheimer-Schmidt'
                
                ***Esa es una sintaxis válida aunque un poco verbosa. Puede hacerlo un poco más conciso utilizando la sintaxis row value constructor:

                    from Person p where p.name=('John', 'Jingleheimer-Schmidt')
                
                ***También puede ser útil especificar esto en la cláusula select:

                    select p.name from Person p
                
                ***También puede ser beneficioso el utilizar la sintaxis row value constructor cuando se utilizan 
                    subconsultas que necesitan compararse con valores múltiples:

                    from Cat as cat
                    where not ( cat.name, cat.color ) in (
                        select cat.name, cat.color from DomesticCat cat
                    )
                
                nOTA: Algo que se debe tomar en consideración al decidir si quiere usar esta sintaxis es que la 
                consulta dependerá del orden de las sub-propiedades componentes en los metadatos.
    ------------------------------------------------------------------------------------------------------
      10. Consultas con nombre HQL
          Las consultas con nombre se crean mediante anotaciones a nivel de clase en entidades; normalmente, 
          las consultas se aplican a la entidad en cuyo archivo de origen ocurren, pero no existe un requisito 
          absoluto para que esto sea cierto.

          Las consultas con nombre se crean con la @NamedQueriesanotación, que contiene una serie de 
          @NamedQueryconjuntos; cada uno tiene una consulta y un nombre.

          Un ejemplo de consultas con nombre puede verse así:

          @Entity
          @Table(name="actividad")
          @NamedQueries({
              @NamedQuery(name = "actividad.buscarTodos", query = "from Actividad a"),
              @NamedQuery(name = "actividad.buscarPorNombre", query = "from Actividad a where a.nombre=:nombre"),
          })
          public class Actividad extends BaseModel implements Serializable {...}


          Ejecutar la consulta mencionada anteriormente es aún más simple.

              SessionFactory sf = manager.getDao().getSes().getSessionFactory();
              Session session = sf.openSession();
              session.beginTransaction();
              Query query = session.getNamedQuery("actividad.buscarTodos");
              List<Actividad> actividades = query.list();
              for (Actividad actividad : actividades) {
                System.out.println(actividad.getNombre());
              }
              session.getTransaction().commit();

          -------------------------------------------------------------------------------------------
              SessionFactory sf = manager.getDao().getSes().getSessionFactory();
              Map<String, Object> queryParams = new HashMap<String, Object>();
              queryParams.put("nombre", "Corredor");
              List<Actividad> actividades = manager.findByNamedQuery("actividad.buscarPorNombre", queryParams);
              for (Actividad actividad : actividades) {
                System.out.println(actividad.getNombre());
              }
                  
      12. HQL - Habilitar registros y comentarios
          Hibernate puede generar el SQL subyacente detrás de sus consultas HQL en el archivo de registro de su aplicación. 
          Esto es especialmente útil si la consulta HQL no proporciona los resultados que espera, o si la consulta lleva más 
          tiempo del deseado. Esta no es una característica que tendrá que usar con frecuencia, pero es útil si tiene que 
          recurrir a los administradores de su base de datos para que le ayuden a ajustar su aplicación Hibernate.

          12.1 Registros HQL
              La forma más fácil de ver el SQL para una consulta Hibernate HQL es habilitar la salida de SQL en los registros 
              con la propiedad " show_sql ". Establezca esta propiedad en verdadero en su archivo de configuración 
              hibernate.cfg.xml e Hibernate mostrará el SQL en los registros. Cuando busque en la salida de su aplicación 
              las instrucciones SQL de Hibernate, tendrán el prefijo "Hibernate:".

              Si activa su registro log4j para depurar las clases de Hibernate, verá instrucciones SQL en sus archivos de 
              registro, junto con mucha información sobre cómo Hibernate analizó su consulta HQL y la tradujo a SQL.

          12.2 Comentarios HQL
              El seguimiento de sus declaraciones HQL a través del SQL generado puede ser difícil, por lo que Hibernate 
              proporciona una función de comentarios sobre el objeto Query que le permite aplicar un comentario a una consulta 
              específica. La Queryinterfaz tiene un setComment()método que toma un objeto String como argumento, como sigue:

                  public Query setComment(String comment)
          
              Hibernate no agregará comentarios a sus declaraciones SQL sin alguna configuración adicional, incluso si utiliza 
              el setComment()método. También deberá establecer una propiedad de Hibernate, hibernate.use_sql_comments , en 
              true en su configuración de Hibernate.

              Si configura esta propiedad pero no establece un comentario en la consulta mediante programación, Hibernate 
              incluirá el HQL utilizado para generar la llamada SQL en el comentario. Considero que esto es muy útil para depurar HQL.

              Utilice los comentarios para identificar la salida de SQL en los registros de su aplicación si el registro 
              de SQL está habilitado.
****************************************************************************************************************




**********************************************************************************Hibernate @NamedQuery Tutorial
    Las consultas con nombre en hibernación son una técnica para agrupar las declaraciones HQL en una ubicación 
    única y, en última instancia , referirlas con algún nombre cuando sea necesario usarlas. Que ayuda en gran 
    medida en la limpieza del código debido a que estas declaraciones HQL ya no se encuentran dispersos en todo 
    el código.

    La definición de consulta con nombre tiene dos atributos importantes:

        nombre : el nombre de la consulta de nombre por el cual se ubicará utilizando la sesión de hibernación.
        consulta : Aquí da la declaración HQL para que se ejecute en la base de datos.

    @Entity
    @Table(name = "DEPARTMENT", uniqueConstraints = {
                        @UniqueConstraint(columnNames = "ID"),
                        @UniqueConstraint(columnNames = "NAME") })
    @NamedQueries
    (
        {
            @NamedQuery(name=DepartmentEntity.GET_DEPARTMENT_BY_ID, query=DepartmentEntity.GET_DEPARTMENT_BY_ID_QUERY),
            @NamedQuery(name=DepartmentEntity.UPDATE_DEPARTMENT_BY_ID, query=DepartmentEntity.UPDATE_DEPARTMENT_BY_ID_QUERY)
        }
    )
    public class DepartmentEntity implements Serializable {
         
        static final String GET_DEPARTMENT_BY_ID_QUERY = "from DepartmentEntity d where d.id = :id"; 
        public static final String GET_DEPARTMENT_BY_ID = "GET_DEPARTMENT_BY_ID"; 
         
        static final String UPDATE_DEPARTMENT_BY_ID_QUERY = "UPDATE DepartmentEntity d SET d.name=:name where d.id = :id"; 
        public static final String UPDATE_DEPARTMENT_BY_ID = "UPDATE_DEPARTMENT_BY_ID"; 
        ...
    }
    ---------------------------------------------------------------------------------------------------
        //Open the hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try
        {
            //Update record using named query
            Query query = session.getNamedQuery(DepartmentEntity.UPDATE_DEPARTMENT_BY_ID)
                                        .setInteger("id", 1)
                                        .setString("name", "Finance");
            query.executeUpdate();
             
            //Get named query instance
            query = session.getNamedQuery(DepartmentEntity.GET_DEPARTMENT_BY_ID)
                                        .setInteger("id", 1);
            //Get all departments (instances of DepartmentEntity)
            DepartmentEntity department = (DepartmentEntity) query.uniqueResult();
            System.out.println(department.getName());
        }
        finally{
            session.getTransaction().commit();
            HibernateUtil.shutdown();
        }
****************************************************************************************************************



*******************************************************************************************Cache de Primer nivel
    El almacenamiento en caché es una instalación proporcionada por los marcos ORM que ayudan a los usuarios 
    a obtener una aplicación web que se ejecuta rápidamente, mientras que ayudan a reducir el número de consultas 
    realizadas a la base de datos en una sola transacción. Hibernate logra el segundo objetivo mediante la 
    implementación de caché de primer nivel.

    La caché de primer nivel en hibernación está habilitada de forma predeterminada y no necesita hacer nada 
    para que esta funcionalidad funcione. De hecho, no puede deshabilitarlo ni siquiera con fuerza.

    Es fácil entender el caché de primer nivel si entendemos el hecho de que está asociado con el objeto 
    Session . Como sabemos, el objeto de sesión se crea a pedido desde la fábrica de sesiones y se pierde una vez 
    que se cierra la sesión . Del mismo modo, el caché de primer nivel asociado con el objeto de sesión está 
    disponible solo hasta que el objeto de sesión esté activo. Está disponible solo para objetos de sesión y no 
    está accesible para ningún otro objeto de sesión en ninguna otra parte de la aplicación.

    Hechos importantes
        * El caché de primer nivel está asociado con el objeto "sesión" y otros objetos de sesión en la aplicación 
        no pueden verlo.
        * El alcance de los objetos de caché es de sesión. Una vez que se cierra la sesión, los objetos en caché 
        desaparecen para siempre.
        * El caché de primer nivel está habilitado de manera predeterminada y no puede deshabilitarlo.
        * Cuando consultamos una entidad por primera vez, se recupera de la base de datos y se almacena en 
        el caché de primer nivel asociado con la sesión de hibernación.
        + Si consultamos el mismo objeto nuevamente con el mismo objeto de sesión, se cargará desde la caché 
        y no se ejecutará ninguna consulta SQL.
        + La entidad cargada se puede eliminar de la sesión utilizando el método evict (). La próxima carga de 
        esta entidad volverá a hacer una llamada a la base de datos si se ha eliminado utilizando el método evict ().
        + Todo el caché de la sesión se puede eliminar con el método clear (). Eliminará todas las entidades 
        almacenadas en caché.

    Ejemplo_______________________________________________________________________________________________


          //Open the hibernate session
          Session session = manager.getDao().getSes().getSessionFactory().openSession();
          session.beginTransaction();
           
          //recupero CuentaCliente la primera vez
          CuentaCliente cuenta = (CuentaCliente) session.load(CuentaCliente.class, new Long(34219999));
          System.out.println(cuenta.getDenominacionCuenta());
           
          //recupero CuentaCliente nuevamenta
          cuenta = (CuentaCliente) session.load(CuentaCliente.class, new Long(34219999));
          System.out.println(cuenta.getDenominacionCuenta());
           
          session.getTransaction().commit();

      Nota: La segunda vez no realiza la consulta a la base de datos 

    ****Recuperación de caché de primer nivel con nueva sesión
      Con una nueva sesión, la entidad se recupera de la base de datos nuevamente, independientemente de que 
      ya esté presente en cualquier otra sesión de la aplicación.
        

        //Open the hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
         
        Session sessionTemp = HibernateUtil.getSessionFactory().openSession();
        sessionTemp.beginTransaction();
        try
        {
            //fetch the department entity from database first time
            DepartmentEntity department = (DepartmentEntity) session.load(DepartmentEntity.class, new Integer(1));
            System.out.println(department.getName());
             
            //fetch the department entity again
            department = (DepartmentEntity) session.load(DepartmentEntity.class, new Integer(1));
            System.out.println(department.getName());
             
            department = (DepartmentEntity) sessionTemp.load(DepartmentEntity.class, new Integer(1));
            System.out.println(department.getName());
        }
        finally
        {
            session.getTransaction().commit();
            HibernateUtil.shutdown();
             
            sessionTemp.getTransaction().commit();
            HibernateUtil.shutdown();
        }

    *****Eliminar objetos de caché del ejemplo de caché de primer nivel
        Aunque no podemos deshabilitar el caché de primer nivel en hibernación, podemos eliminar algunos 
        objetos cuando sea necesario. Esto se hace usando dos métodos:

            evict()
            clear()

        Aquí evict () se usa para eliminar un objeto particular de la memoria caché asociada con la 
        sesión , y el método clear () se usa para eliminar todos los objetos en caché asociados con la 
        sesión . Entonces son esencialmente como eliminar uno y eliminar todo.

        Ejemplo_______________________________________________________________________________________________
          //Open the hibernate session
          Session session = manager.getDao().getSes().getSessionFactory().openSession();
          session.beginTransaction();
          
           
          try{
            //fetch the department entity from database first time
            CuentaCliente cuenta = (CuentaCliente) session.load(CuentaCliente.class, new Long(34219999));
            System.out.println(cuenta.getDenominacionCuenta());
            
            //Se recupera el obj cuenta nuevamente
            cuenta = (CuentaCliente) session.load(CuentaCliente.class, new Long(34219999));
            System.out.println(cuenta.getDenominacionCuenta());
            
            //Se elimina el obj cuenta de la session
            session.evict(cuenta);
            
            //Se recupera nuevamente el obj cuenta
            cuenta = (CuentaCliente) session.load(CuentaCliente.class, new Long(34219999));
            System.out.println(cuenta.getDenominacionCuenta());
          }finally{
            session.getTransaction().commit();
          }
****************************************************************************************************************


******************************************************Como recuperar datos que estan configurados Lazy(peresosa)
	**Para recuperar atributos mapeados de una bd con join fetch
		//Forzar a Hibernate a traer todas las propiedades del pojo. por mas que tenga Lizy en Fetch Type.
		ArrayList<CuentaCliente> CcList = (ArrayList<CuentaCliente>) manager.find("from CuentaCliente cc join fetch cc.cliente c join fetch c.crmFichaCliente where cc.id=? " ,new Object[]{Long.valueOf(idCtaCte)});
	**Forzar propiedades de una Clases con FETCH ALL PROPERTIES
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clienteId", cl.getId());
		String qry ="SELECT crm FROM CRMFichaCliente crm FETCH ALL PROPERTIES join fetch crm.cliente WHERE crm.cliente.id = :clienteId";
		List<CRMFichaCliente> LcrmFcte = new ArrayList<CRMFichaCliente>();
		LcrmFcte=(List<CRMFichaCliente>)manager.executeQuery(false, qry, params);
		crmFcte = LcrmFcte.get(0);

****************************************************************************************************************