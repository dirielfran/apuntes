package clase4Interfaz;

/*
 * 1) Crear la clase Persona con los siguientes atributos:
	Es una clase abstracta
	id: int
	nombre, apellido: String
	telefono: Telefono
	domicilio: Domicilio
	
	Crear un método en Persona llamado “public String getNombreCompleto()”. La misión del método es que
	devuelva el nombre y apellido de la persona con la primer letra en mayúscula y el resto en minúscula. Por
	ejemplo, si la persona posee en sus atributos nombre = “juan” y apellido = “PEREZ”, el método
	getNombreCompleto debe devolver: “Juan, Perez”.
 */
public abstract class Persona {
	
	private int id;
	private String apellido, nombre;
	private Telefono telefono;
	private Domicilio domicilio;

	
	public static String getNombreCompleto(String nombre, String apellido) {

		char[] mayusculaNombre = nombre.toLowerCase().toCharArray();
		char[] mayusculaApellido = apellido.toLowerCase().toCharArray();
		 
		mayusculaNombre[0]= nombre.toUpperCase().charAt(0);
		nombre = String.valueOf(mayusculaNombre);
		mayusculaApellido[0]= apellido.toUpperCase().charAt(0);
		apellido = String.valueOf(mayusculaApellido);
		return nombre + ", "+apellido;
	}
		
}		 
	
	
	

