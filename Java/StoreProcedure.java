--stored procedure que actualiza el salario de un id_empleado
--segun el factor de incremento proporcionado
  CREATE OR REPLACE PROCEDURE "HR"."SET_EMPLOYEE_SALARY" (
      p_emp_id  IN employees.employee_id%TYPE
    , p_factor  IN NUMBER
) AS

    v_monthly_salary  employees.salary%TYPE;

BEGIN

    -- Obtiene el salario actual del id_empleado proporcionado
    SELECT NVL(salary, -999)
        INTO   v_monthly_salary
        FROM   employees
        WHERE  employee_id = p_emp_id;

    -- Si existe el registro, se actualiza su salario con el factor proporcionado
    IF (v_monthly_salary != -999) THEN
        UPDATE employees SET salary = salary * p_factor WHERE employee_id = p_emp_id;
        COMMIT;
    DBMS_OUTPUT.PUT_LINE('Termino del programa, salary:' || v_monthly_salary);  
    END IF;
END;
/


------------------------------------------------------------------------------------------------------------------

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs;

import datos.Conexion;
import java.sql.*;

public class TestProcedimientos {

    public static void main(String[] args) {
        int empleadoId= 100;
        //la formula que aplica el Store Procedure es
        //salario=salario*incremento
        double incrementoSalario = 1.1;//incremento del 10%
        Connection con;
        try {
            con = Conexion.getConnection();
            Statement stmt = null;
            ResultSet rset = null;
            CallableStatement cstmt = null;
            
            stmt = con.createStatement();
            
            //Llamamos al SP para incrementar el salario
            System.out.println("Aumento del 10% al empleado:" + empleadoId);
            cstmt = con.prepareCall("{call set_employee_salary(?,?)}");
            cstmt.setInt(1, empleadoId);
            cstmt.setDouble(2, incrementoSalario);
            cstmt.execute();
            cstmt.close();
            
            //Obtenemos el nuevo valor del salario para el empleado seleccionado
            
            String query= "SELECT first_name, salary FROM employees " +
             " WHERE  employee_id = " + empleadoId;
            
            rset = stmt.executeQuery(query);
            rset.next();
            System.out.println("Nombre: " + rset.getString(1));
            System.out.println("Salario nuevo:" + rset.getFloat(2));
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}