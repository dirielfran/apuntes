package com.eareiza.java8.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaApp {

	public void ordenar() {
		List<String> lista = new ArrayList<>();
		lista.add("Elvis");
		lista.add("Ricardo");
		lista.add("Diego");
		lista.add("Camila");
		lista.add("Antonio");
		
		
		//Java 7
//		Collections.sort(lista, new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				return o1.compareTo(o2);
//			}			
//		});
		//Java 8
		
		Collections.sort(lista,  (String o1, String o2) -> o1.compareTo(o2));
		
		for (String string : lista) {
			System.out.println(string);
		}
	}

	public void calcular() {
		//Java 7
//		IOperacion oper = new IOperacion() {
			

//		@Override
//		public double calcularProm(double n1, double n2) {
//				return n1 * n2 / 2;
//			}
//		};
			
		//java 8 lambda		
		//IOperacion oper = (double n1, double n2) -> (n1+n2)/2;
		
		//Otro tipo de sintaxis
//		IOperacion oper = (double n1, double n2) -> {
//			double a = n1;
//			double b = n2;
//			return (a+b)/2;
//		};
		
		//Otro tipo de sintaxis
		IOperacion oper = (n1, n2) -> (6+6)/2;
		System.out.println(oper.calcularProm(2, 6));
	}
	
	public static void main(String[] args) {
		LambdaApp app = new LambdaApp();
		app.ordenar();
		app.calcular();
	}

}
