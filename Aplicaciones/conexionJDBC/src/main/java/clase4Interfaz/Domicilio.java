package clase4Interfaz;
/*
 * 3) Clase Domicilio
	id;int
	Calle, localidad: string
	numero: int
 */
public class Domicilio {
	private int id;
	private String calle, localidad;
	private int numero;
	
	
	public Domicilio() {
		super();
	}


	public Domicilio(int id, String calle, String localidad, int numero) {
		super();
		this.id = id;
		this.calle = calle;
		this.localidad = localidad;
		this.numero = numero;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	@Override
	public String toString() {
		return "Domicilio [id=" + id + ", calle=" + calle + ", localidad=" + localidad + ", numero=" + numero + "]";
	}
	
	
	
	
	
	
	
	
	
}
