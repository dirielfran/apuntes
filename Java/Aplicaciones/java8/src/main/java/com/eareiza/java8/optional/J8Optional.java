package com.eareiza.java8.optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class J8Optional {
	
	public void probar(String valor) {
		//System.out.println(valor.contains("Hello"));
		try {
			Optional opt = Optional.empty();
			opt.get();
		} catch (Exception e) {
			System.out.println("no tiene elementos");
		}
		
	}
	
	//metodo que permite detectar si una variable esta null o no inicializada y colocarle un por defecto
	public void orElse(String valor) {
		//Creo obj Optional por valor pasado
//		Optional<String> opt = Optional.of(valor);
//		System.out.println(opt.get());
		//el metodo ofNullable acepta valores null
		Optional<String> opt = Optional.ofNullable(valor);
		System.out.println(opt.orElse("predeterminado"));
	}
	
	//metodo que si es null permite arrojar una exception
	public void orElseThrow(String valor) {
		Optional<String> opt = Optional.ofNullable(valor);
		opt.orElseThrow(NumberFormatException::new);
	}
	
	//metodo que indica nos dice si el objeto Optional contiene un valor o está vacío
	public void isPresent(String valor) {
		Optional<String> opt= Optional.ofNullable(valor);
		System.out.println(opt.isPresent());
	}
	
	//Metodo que permite validar si tiene valor, el optional, si es asi ejecuta funcion
	public void ifPresent(String valor) {
	    Optional<String> opt = Optional.of(valor);
	    opt.ifPresent(name -> System.out.println(name.length()));
	}
	
	//Metodo que ejecuta funciion en caso de qe este vacio el optional
	public void orElseGet(String valor) {
		System.out.println(Optional.ofNullable(valor).orElseGet(() -> "Elvis"));
	}
	
	//metodo que devuelve un booleano segun un filtro
	public boolean filter(Moden moden) {
		return Optional.ofNullable(moden).map(Moden::getPrecio)
				.filter(x -> x >= 10).filter(x -> x <= 15).isPresent();
	}
	
	//Metodo que devuelve longitud de una lista por parametro
	public int map(List<String> lista) {
		Optional<List<String>> listaCad = Optional.ofNullable(lista);
		int size = listaCad.map(List::size).orElse(0);
		return size;
	}
	
	//Metodo para verificar la exactitud de una contraseña ingresada por un usuario
	public boolean verifPass(String pass) {
		return Optional.of(pass).map(String::trim)
				.filter(x -> x.equalsIgnoreCase("password"))
				.isPresent();		
	}
	
	public static void main(String[] args) {
		J8Optional opt = new J8Optional();
		//opt.probar(null);
//		opt.orElse(null);
//		opt.orElseThrow(null);
//		opt.isPresent(null);
//		opt.ifPresent(null);
//		opt.orElseGet(null);
//		Moden moden = new Moden(18.0);
//		System.out.println(opt.filter(moden));
//		List<String> lista = Arrays.asList("Enero","Febrero", "Marzo");
//		System.out.println(opt.map(null));
		System.out.println(opt.verifPass("pass"));
	}
	
	public static class Moden{
		private Double precio;

		public Moden(Double precio) {
			super();
			this.precio = precio;
		}

		public Double getPrecio() {
			return precio;
		}

		public void setPrecio(Double precio) {
			this.precio = precio;
		}
	}
	
	public class Person {
	    private String name;
	    private int age;
	    private String password;
	 
	    public Optional<String> getName() {
	        return Optional.ofNullable(name);
	    }
	 
	    public Optional<Integer> getAge() {
	        return Optional.ofNullable(age);
	    }
	 
	    public Optional<String> getPassword() {
	        return Optional.ofNullable(password);
	    }
	 
	    // normal constructors and setters
	}

}
