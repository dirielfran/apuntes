package com.eareiza.java8.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;


public class Colecciones {
	
	List<String> lista;
	
	public List<String> crearLista(){
		lista = new ArrayList<String>();
		lista.add("Elvis");
		lista.add("Yala");
		lista.add("Antonio");
		lista.add("Camila");
		lista.add("Ricardo");
		lista.add("Diego");
		return lista;
	}

	public void usarForEach() {
		//Foreach java 7
//		for (String item : crearLista()) {
//			System.out.println(item);
//		}
		//Foreach con metodos lambda
		//crearLista().forEach(x -> System.out.println(x));
		//foreach con metodos referenciados
		lista.forEach(System.out::println);
		
	}
	
	public void usarRemoveIf() {
		//Erro al remover elemento de la lista
//		for (String item : lista) {
//			if(item.compareToIgnoreCase("elvis")==0){
//				lista.remove(item);
//			}
//		}
		// Utilizando Iterato
//		Iterator<String> iter = lista.iterator();
//		while (iter.hasNext()) {
//			if(iter.next().equalsIgnoreCase("Elvis")) {
//				iter.remove();
//			}
//		}
		//Utilizando expresiones Lambda y removeIf en una ista
		lista.removeIf(x -> x.equalsIgnoreCase("yala"));
	}

	
	public void usarSort() {
		//Utilizando lambada
//		lista.sort((x,y) -> x.compareTo(y));
		//Utilizando metodos de referencia
		lista.sort(String::compareTo);
	}
	
	public static void main(String[] args) {
		Colecciones collec = new Colecciones();
		collec.crearLista();
		collec.usarSort();
		collec.usarForEach();
		collec.usarRemoveIf();
		System.out.println("************");
		collec.usarForEach();
	}

}
