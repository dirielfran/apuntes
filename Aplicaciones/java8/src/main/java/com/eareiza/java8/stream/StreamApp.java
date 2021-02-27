package com.eareiza.java8.stream;

import java.util.ArrayList;
import java.util.List;

public class StreamApp {

	List<String> lista;
	List<String> numeros;
	
	
	
	public StreamApp() {
		lista = new ArrayList<String>();
		lista.add("Elvis");
		lista.add("Camila");
		lista.add("Ricardo");
		lista.add("Antonio");
		lista.add("Diego");
		
		numeros = new ArrayList<String>();
		numeros.add("1");
		numeros.add("2");
		numeros.add("3");
		numeros.add("4");
		numeros.add("5");
	}
	
	//Metodo que filtra las palabras que empiecen por variable indicada
	public void filtrar() {
		lista.stream().filter(x -> x.startsWith("E")).forEach(System.out::println);
	}

	//Metodo que ordena una lista y la imprime
	public void ordenar() {
		//Ordena de forma ascendente
//		lista.stream().sorted().forEach(System.out::println);
		//ordena de forma descendente
		lista.stream().sorted((x,y)->y.compareTo(x)).forEach(System.out::println);
	}

	public void transformar() {
		//Transforma una cadena a mayusculas
		lista.stream().map(String::toUpperCase).forEach(System.out::println);
		//forma iperativa de parsear a numeros
//		for (String string : numeros) {
//			int num = Integer.parseInt(string) + 1;
//			System.out.println(num);
//		}
		//parsea a numeros 
		numeros.stream().map(x -> Integer.parseInt(x)+1).forEach(System.out::println);
	}
	
	public void limitar() {
		lista.stream().limit(3).forEach(System.out::println);
	}
	
	public void contar() {
		System.out.println(lista.stream().count());
	}

	public static void main(String[] args) {
		StreamApp stApp = new StreamApp();
//		stApp.filtrar();
//		stApp.ordenar();
		stApp.transformar();
		stApp.limitar();
		stApp.contar();
	}

}
