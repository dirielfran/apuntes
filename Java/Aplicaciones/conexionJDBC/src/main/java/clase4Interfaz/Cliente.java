package clase4Interfaz;
/*4) Cliente (Hereda de Persona)
numeroCliente: int
*/


public class Cliente extends Persona {
	private int numeroCliente;

	public Cliente() {
		super();
	}

	public Cliente(int numeroCliente) {
		super();
		this.numeroCliente = numeroCliente;
	}

	public int getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(int numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	@Override
	public String toString() {
		return "Cliente [numeroCliente=" + numeroCliente + "]";
	}
	
	
	
	

}
