
--------------------------------------------------------------------------------------------------------------------------
package datos;

import java.sql.*;
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




--------------------------------------------------------------------------------------------------------------------------



package datos;

import domain.Persona;
import java.sql.*;
import java.util.*;

/**
 * Clase que contiene los métodos de SELECT, INSERT, UPDATE y DELETE para la
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
    //los cuales posteriormente será sustituidos por el parametro respectivo

    private java.sql.Connection userConn;
    
    private final String SQL_INSERT
            = "INSERT INTO persona(nombre, apellido) VALUES(?,?)";

    private final String SQL_UPDATE
            = "UPDATE persona SET nombre=?, apellido=? WHERE id_persona=?";

    private final String SQL_DELETE
            = "DELETE FROM persona WHERE id_persona = ?";

    private final String SQL_SELECT
            = "SELECT id_persona, nombre, apellido FROM persona ORDER BY id_persona";
    
    public PersonasJDBC(){};
    
    public PersonasJDBC(Connection conn){
        this.userConn = conn;
    }
    
    
    /**
     * Metodo que inserta un registro en la tabla de Persona
     *
     * @param nombre
     * @param apellido
     */
    public int insert(String nombre, String apellido) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;//no se utiliza en este ejercicio		
        int rows = 0; //registros afectados
        try {
            //Valido si la variable userConn fue inicializada
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            //conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;//contador de columnas
            stmt.setString(index++, nombre);//param 1 => ?
            stmt.setString(index++, apellido);//param 2 => ?
            System.out.println("Ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();//no. registros afectados
            System.out.println("Registros afectados:" + rows);

        } finally {
            Conexion.close(stmt);
            //Si es diferente a null, mantenemos viva la conexion
            if (this.userConn == null){ 
                Conexion.close(conn);
            }
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
    public int update(int id_persona, String nombre, String apellido) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            //valido si se inicializo una conexion
            conn = (this.userConn != null ) ? this.userConn : Conexion.getConnection();
            //conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, nombre);
            stmt.setString(index++, apellido);
            stmt.setInt(index, id_persona);
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
        } finally {
            Conexion.close(stmt);
            //Si es diferente a null la dejamos viva
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
        return rows;
    }


    /**
     * Metodo que elimina un registro existente
     *
     * @param id_persona Es la llave primaria
     * @return int No. registros afectados
     */
    public int delete(int id_persona) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            //conn = Conexion.getConnection();
            //Se valida si esta inicializada la conexion
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id_persona);
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        }finally {
            Conexion.close(stmt);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
        return rows;
    }

    /**
     * Metodo que regresa el contenido de la tabla de personas
     */
    public List<Persona> select() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<Persona>();
        try {
            //conn = Conexion.getConnection();
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
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

        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
        return personas;
    }
}
----------------------------------------------------------------------------------------------------------------------------
package domain;

/**
 * Clase que representa un registro de la tabla de Persona
 *
 * @author Ubaldo
 *
 */
public class Persona {

    private int id_persona;

    private String nombre;

    private String apellido;

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int idPersona) {
        id_persona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

     @Override
    public String toString() {
        return "Persona{" + "id_persona=" + id_persona + ", nombre=" + nombre + ", apellido=" + apellido + '}';
    }
}


-----------------------------------------------------------------------------------------------------------------------------

package manejopersonas;

import datos.PersonasJDBC;
import datos.Conexion;
import domain.Persona;
import java.sql.*;

public class ManejoPersonas {

    public static void main(String[] args) {
        PersonasJDBC personasJDBC = new PersonasJDBC();
    //Prueba del metodo insert
        //personasJDBC.insert("Alberto", "Juarez");
        
        //Prueba del metodo update
        //personasJDBC.update(2, "Nombre3", "Apellido3");
        
        //Prueba del metodo delete
        //personasJDBC.delete(1);
      
        //Prueba del metodo select
        //Uso de un objeto persona para encapsular la informacion
        //de un registro de base de datos
        
        Connection conn = null;
        
        try {
            conn = Conexion.getConnection();
            //Revisamos si la conexion esta en modo AutoCommit
            //Por default es autocommit true
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            //Creamos el Objeto Persona
            PersonasJDBC personas = new PersonasJDBC(conn);
            
            personas.update(2, "Elvis", "Areiza");
            
            //Provocamos un error
            personas.insert("Miguel", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                    + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            
            //guardamos los cambios haciendo commit
            conn.commit();
        } catch (SQLException e) {
            try {
                 //Hacemos rollback
                System.out.println("Entramos al rollback");
                //Imprimimos la excepcion en consola
                e.printStackTrace(System.out);
                //Hacemos rollback
                conn.rollback();
            } catch (SQLException el) {
                el.printStackTrace(System.out);
            }           
        }
        //List<Persona> personas = personasJDBC.select();
        //for (Persona persona : personas) {
        //    System.out.print(persona);
        //    System.out.println("");
        //}
    }

}
