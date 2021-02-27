CREATE OR REPLACE PACKAGE      "REF_CURSOR_PACKAGE" 
AS

    TYPE t_ref_cursor IS REF CURSOR;
    FUNCTION get_dept_ref_cursor(p_dept_id INTEGER) RETURN t_ref_cursor;

END;
/


CREATE OR REPLACE PACKAGE BODY ref_cursor_package
AS
    FUNCTION get_dept_ref_cursor (p_dept_id INTEGER)
        RETURN t_ref_cursor IS

        dept_ref_cursor t_ref_cursor;

    BEGIN      
        OPEN dept_ref_cursor FOR
            SELECT department_id, department_name, location_id
            FROM   departments
            WHERE  department_id > p_dept_id
            ORDER BY department_id;

        RETURN dept_ref_cursor;

    END get_dept_ref_cursor;

END ref_cursor_package;
/
---------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------------------

package datos;

import java.sql.*;
import java.util.*;

/**
 * Clase Conexion JDBC
 */
public class Conexion {

    //Variables de conexion

    private static String JDBC_DRIVER;
    //El puerto es opcional
    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASS;
    private static Driver driver = null;
    //Vamos a crear un archivo .properties de nombre ConexionJDBC
    private static String JDBC_FILE_NAME = "jdbc";

    /**
     * Metodo que lee el archivo de propiedades con los valores a utilizar para
     * conectarnos a la BD
     *
     * @param file
     * @return
     */
    public static Properties loadProperties(String file) {
        Properties prop = new Properties();
        ResourceBundle bundle = ResourceBundle.getBundle(file);
        Enumeration e = bundle.getKeys();
        String key = null;
        while (e.hasMoreElements()) {
            key = (String) e.nextElement();
            prop.put(key, bundle.getObject(key));
        }

    //Asignamos los valores del archivo de propiedades 
        //a las variables de la clase
        JDBC_DRIVER = prop.getProperty("driver");
        JDBC_URL = prop.getProperty("url");
        JDBC_USER = prop.getProperty("user");
        JDBC_PASS = prop.getProperty("pass");

    //Regresamos el objeto properties con los valores
        //de la conexion a la BD
        return prop;
    }

  //Para que no haya problemas al obtener la conexion de
    //manera concurrente, se usa la palabra synchronized
    public static synchronized Connection getConnection()
            throws SQLException {
        if (driver == null) {
            try {
                //Cargamos las propiedades de conexion a la BD
                loadProperties(JDBC_FILE_NAME);
                //Se registra el driver
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("Fallo en cargar el driver JDBC");
                e.printStackTrace();
            }
        }
        //regresa un objeto de tipo Connection
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


------------------------------------------------------------------------------------------------
#Archivo jdbc.properties
#Archivo de propiedades que contiene los valores
#de la cadena de conexion

#Conexion a Oracle
driver = oracle.jdbc.driver.OracleDriver
url    = jdbc:oracle:thin:@localhost:1521:XE
user   = hr
pass   = hr

------------------------------------------------------------------------------------------------

package cs;

import datos.Conexion;
import java.sql.*;
import oracle.jdbc.*;

public class TestCursores {

    public static void main(String[] args) {
        //Utilizamos una clase de oracle para poder procesar el
        //cursor que regresa la funcion de Oracle
        OracleCallableStatement oraCallStmt = null;
        OracleResultSet deptResultSet = null;
        try {
            Connection con = Conexion.getConnection();
            //Tiene dos parametros que posteriormente se definiran
            oraCallStmt = (OracleCallableStatement) con.prepareCall("{? = call ref_cursor_package.get_dept_ref_cursor(?)}");
            //Indicamos el tipo de regreso, el cual es un cursor
            oraCallStmt.registerOutParameter(1, OracleTypes.CURSOR);//Parametro 1
            oraCallStmt.setInt(2, 200);//establecemos departamento_id, parametro 2
            oraCallStmt.execute();
            //Recuperamos el resultSet y lo convertimos a un tipo Oracle
            deptResultSet = (OracleResultSet) oraCallStmt.getCursor(1);
            while (deptResultSet.next()) {
                System.out.print(" Id_departamento: " + deptResultSet.getInt(1));
                System.out.print(", Nombre_departamento: " + deptResultSet.getString(2));
                System.out.print(", Ubicación_id: " + deptResultSet.getString(3));
                System.out.println();
            }
            oraCallStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


