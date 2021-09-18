package com.eareiza.java8.FuncAltoOrden;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FuncAltOrder {
	//Se crea funciones
	private Function<String, String> converMay = x -> x.toUpperCase();
	private Function<String, String> converMin = x -> x.toLowerCase();
	
	//paso de una funcion
	public void imprimir(Function<String, String> funcion, String valor) {
		System.out.println(funcion.apply(valor));
	}
	
	//Devolver una funcion
	public Function<String, String> mostrar(String mensaje) {
		return (String x) -> mensaje + x;
	}
	
	//Se crea metodo que filtra por la longitud de una palabra y las imprime
	public void filtrar(List<String> lista, Consumer<String> consumidor, int longitud) {
		//convierte la lista en stream, con el metodo filter se le pasa metodo que establece 
		//si tiene la longitud correcta, recorre la lista pasando una funcion para que realice una tarea
		lista.stream().filter(this.estableceLongitudFiltro(longitud)).forEach(consumidor);;
	}
	
	//Se crea metodo que filtra si la palabra esta contenida en la lista
	public void filtrar(List<String> lista, Consumer<String> consumidor, String cadena) {
		//convierte la lista en stream, con el metodo filter se le pasa metodo que establece 
		//si la palabra esta contenida en la lista, recorre la lista pasando una funcion para que realice una tarea
		lista.stream().filter(this.estableceLongitudFiltro(cadena)).forEach(consumidor);;
	}
	
	public Predicate<String> estableceLongitudFiltro(int longitud){
		return texto -> texto.length() < longitud;
	}
	
	public Predicate<String> estableceLongitudFiltro(String cadena){
		return texto -> texto.contains(cadena);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FuncAltOrder fao = new FuncAltOrder();
//		fao.imprimir(fao.converMay, "elvis Areiza");
//		fao.imprimir(fao.converMin, "Elvis Areiza");
//		System.out.println(fao.mostrar("Hola ").apply("Elvis"));
//		System.out.println();
		List<String> lista = new ArrayList<String>();
		lista.add("pru");
		lista.add("prueba");
		lista.add("Yalaury");
		
//		fao.filtrar(lista, System.out::println, 7);
		fao.filtrar(lista,System.out::println, "lau");
	}

}
