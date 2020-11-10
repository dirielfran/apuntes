package com.eareiza.singleton;

public class Singleton {
	
	private String nombre;
	private static Singleton soyUnico;
	
	private Singleton(String nombre) {
		this.nombre = nombre;
		System.out.println("Mi nombre es: "+this.nombre);
	}
	
	public static Singleton getInstance(String nombre){
		if(soyUnico == null) {
			soyUnico = new Singleton(nombre);
		}else {
			System.out.println("No se puede crea el obj de tipo Singleton, porque ya existe un obj de esta clase.");
		}
		return soyUnico;		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static Singleton getSoyUnico() {
		return soyUnico;
	}

	public static void setSoyUnico(Singleton soyUnico) {
		Singleton.soyUnico = soyUnico;
	}
	
	//Sobreescribimos el método clone, para que no se pueda clonar un objeto 
	//de esta clase
    @Override
    public Singleton clone(){
        try {
            throw new CloneNotSupportedException();
        } catch (CloneNotSupportedException ex) {
            System.out.println("No se puede clonar un objeto de la clase SoyUnico");
        }
        return null; 
    }

	public static void main(String[] args) {
		Singleton ricardo = Singleton.getInstance("Ricardo Moya");
        Singleton ramon = Singleton.getInstance("Ramón Invarato");
        
        // ricardo y ramon son referencias a un único objeto de la clase SoyUnico
        System.out.println(ramon.getNombre());
        System.out.println(ricardo.getNombre()); 

	}

}
