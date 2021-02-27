CREATE OR REPLACE FUNCTION "HR"."GET_EMPLOYEE_SALARY" ( p_emp_id IN employees.employee_id%TYPE )
    RETURN employees.salary%TYPE
  AS
    v_monthly_salary employees.salary%TYPE;
  BEGIN
    --Ejecuta un select para obtener el salario actual para 
    --el id_empleado proporcionado
    SELECT NVL(salary, -999)
    INTO v_monthly_salary
    FROM employees
    WHERE
      employee_id = p_emp_id;
      
    RETURN v_monthly_salary;
  END;
/

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

public class TestFunciones {

    public static void main(String[] args) {
        int empleadoId = 100; // indentificador a recuperar salario
        try {
            Connection con = Conexion.getConnection();
            // Objeto que permite ejecutar una funcion o store procedure
            CallableStatement cstmt = null;
            double salarioMensual;

            // Llamamos a una funcion de Oracle
            cstmt = con.prepareCall("{ ? = call get_employee_salary(?) }");
      // Una funcion regresa un valor
            // por lo que lo registramos como el parametro 1
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            // registrmos el segundo parametro
            cstmt.setInt(2, empleadoId);
            cstmt.execute();
            salarioMensual = cstmt.getDouble(1);
            cstmt.close();
            System.out.println("Empleado con id:" + empleadoId);
            System.out.println("Salario $" + salarioMensual);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
