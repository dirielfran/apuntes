package com.eareiza.factory;

public class FacturaIva extends Factura{

	@Override
	public double getImporteIva() {
		// TODO Auto-generated method stub
		return getImporte()*1.21;
	}

}
