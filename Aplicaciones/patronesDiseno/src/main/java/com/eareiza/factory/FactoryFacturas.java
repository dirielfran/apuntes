package com.eareiza.factory;
/*Factoría para que se encargue de construir ambos objetos de la jerarquía.*/
public class FactoryFacturas {
	
	public static Factura getFactura(String tipo) {
		if(tipo.equals("iva")) {
			return new FacturaIva();
		}else {
			return new FacturaIvaReducido();
		}
	}

}
