package clase4Interfaz;
/*
 * 2) Clase Telefono
	id:int
	Tipo y numero: String
 */
public class Telefono {
	private int id;
	private String tipo, numero;
	
	public Telefono() {
		super();
	}

	public Telefono(int id, String tipo, String numero) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.numero = numero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Telefono [id=" + id + ", tipo=" + tipo + ", numero=" + numero + "]";
	}
	
	
	
	
	
	
	
}
