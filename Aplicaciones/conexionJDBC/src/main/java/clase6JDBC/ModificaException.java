	package clase6JDBC;

public class ModificaException extends Exception  {

	public String getMessage() {
		return "El producto no existe.";
	}
}
