package com.eareiza.factory;
/*Como vemos la clase Factura es una clase abstracta de la cual 
 * heredan nuestras dos clases concretas que implementan el 
 * cálculo del IVA. */
public abstract class Factura {
	private int id;
	private double importe;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	public abstract double getImporteIva();
	
}
