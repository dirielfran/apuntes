package com.eareiza.java8.java8;

public class Scope {
	
	
	public void probarLocalVar() {
		double n3 = 3;
		IOperacion oper = new IOperacion() {
			
			@Override
			public double calcularProm(double n1, double n2) {
				return n1 + n2 + n3;// da error la variable local debe ser final	
			}
		};
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
