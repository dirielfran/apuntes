package com.eareiza.factory;
/*Factor�a para que se encargue de construir ambos objetos de la jerarqu�a.*/
public class FactoryFacturas {
	
	public static Factura getFactura(String tipo) {
		if(tipo.equals("iva")) {
			return new FacturaIva();
		}else {
			return new FacturaIvaReducido();
		}
	}

}
