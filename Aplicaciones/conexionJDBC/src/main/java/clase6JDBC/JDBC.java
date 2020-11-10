package clase6JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
	public static Connection administradoDeConexion() throws Exception {
		
		// Establece el nombre del driver a utilizar
		 String dbDriver = "com.mysql.jdbc.Driver";
		 
		// Establece la conexion a utilizar
		 String dbConnString = "jdbc:mysql://localhost:3306/empleosdb?useSSL=false&serverTimezone=America/Argentina/Buenos_Aires&allowPublicKeyRetrieval=true";
		 
		// Establece el usuario de la base de datos
		 String dbUser = "root";
		 
		 // Establece la contraseña de la base de datos
		 String dbPassword = "Danger120-";
		 
		 // Establece el driver de conexion
		 Class.forName(dbDriver).newInstance();
		 
		 // Retorna la conexion
		 return DriverManager.getConnection(
				 dbConnString, dbUser, dbPassword);	
	}
	
		
	
}
