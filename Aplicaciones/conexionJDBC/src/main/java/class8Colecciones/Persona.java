package class8Colecciones;
/*Este software funciona bien y actualmente se encuentra productivo. Hay muchas clases que
consumen la clase Banco ya que es la más importante de todas.
		La clase Persona tiene como atributos: dni, nombre y apellido.
Nos pidieron realizar el siguiente cambio: el software no debe permitir agregar personas
duplicadas a la fila del banco (actualmente si lo permite). Esto quiere decir que no se debe
dejar agregar a una persona si ya existe otra con su mismo dni en la fila.*/
public class Persona implements Comparable<Persona>{
	private int dni;
	private String nombre, apellido;
	private int edad;
	
	
	public Persona() {
		super();
	}
	
	public Persona(int dNI, String nombre, String apellido) {
		super();
		dni = dNI;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	
	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getDNI() {
		return dni;
	}
	public void setDNI(int dNI) {
		dni = dNI;
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
		return "Persona [DNI=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

	public int compareTo(Persona o) {
		return this.nombre.compareTo(o.nombre);
	}

}
