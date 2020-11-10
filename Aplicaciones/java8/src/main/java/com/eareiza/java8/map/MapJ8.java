package com.eareiza.java8.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MapJ8 {
	
	Map<Integer, String> mapa;
	
	public void poblar() {
		mapa = new HashMap<>();
		mapa.put(0, "Elvis");
		mapa.put(1, "Antonio");
		mapa.put(2, "Diego");
		mapa.put(3, "Camila");
		mapa.put(4, "Ricardo");	
	}
	
	public void imprimirV7() {
		System.out.println("Java 7");
		for (Entry<Integer, String> obj : mapa.entrySet()) {
			System.out.println("Clave: "+obj.getKey()+" Valor: "+obj.getValue());
		}
	}
	
	public void imprimirV8() {
		System.out.println("Java 8");
		//Forma 1
//		mapa.forEach((k,v) -> System.out.println("Clave: "+k+" Valor: "+v) );
		//Forma 2
		mapa.entrySet().stream().forEach(System.out::println);
	}

	public void insertarAusente() {
		mapa.putIfAbsent(5, "Yalaury");
	}

	public void operarSiPresente() {
		mapa.computeIfPresent(2, (k,v) -> "Elvis" );
	}
	
	public void obtenerOrPredeterminado() {
		System.out.println(mapa.getOrDefault(5, "Valor por defecto"));
	}
	
	
	public void filtrarMap() {
		System.out.println("Mapa filtrado.");
		Map<Integer, String> mapaNew= mapa.entrySet().stream()
				.filter(e -> e.getValue().contains("o"))
				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		mapaNew.forEach((k,v) -> System.out.println("Clave: "+k+" Valor: "+v));
	}
	public static void main(String[] args) {
		MapJ8 map = new MapJ8();
		map.poblar();
//		map.imprimirV7();
//		map.insertarAusente();
//		map.operarSiPresente();
//		map.obtenerOrPredeterminado();
		map.imprimirV8();
		map.filtrarMap();
		
	}
}
