package com.eareiza.java8.referedMethod;

import java.util.Arrays;
import java.util.Comparator;

public class MedRefApp {
	public static void refMetodoStatico() {
		System.out.println("Metodo statico referido");
	}
	
	public void referenciaMetodoInstanciaObjArbitrario() {
		String[] nombres = {"Elvis", "Diego", "Antonio"};
		//Primera forma para ordenar un array
//		Arrays.sort(nombres, new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				return o1.compareToIgnoreCase(o2);
//			}
//		});
		//Segunda forma ordenar un array con expresiones lambda
//		Arrays.sort(nombres, (s1,s2) -> s1.compareToIgnoreCase(s2) );
//		System.out.println(nombres);
		//Tercera forma de ordenar un array con metodo referenciado
		Arrays.sort(nombres, String::compareTo);
		System.out.println(Arrays.toString(nombres));
	}
	
	public void referenciaMetodoInstanciaObjParticular() {
		System.out.println("metodo referido instancia de clase");
	}
	
	public void referenciaConstructor() {
		//Implementacion anonima
//		IPersona iPer = new IPersona() {			
//			@Override
//			public Persona crear(int id, String nombre) {
//				return new Persona(id, nombre);
//			}
//		};
//		iPer.crear(1, "Elvis");
		//Utilizando expresiones lambda
//		IPersona Iper2 = (id,nombre) -> new Persona(id, nombre);
//		Persona per = Iper2.crear(1, "Camila");
//		System.out.println(per.getId() + " - " + per.getNombre());
		//Utilizando metodos referenciados al constructor
		IPersona Iper3 = Persona::new;
		Persona per = Iper3.crear(1, "Camila");
		System.out.println(per.getId() + " - " + per.getNombre());
	}
	
	public void operar() {
		
		//IOperacion op = () -> MedRefApp.refMetodoStatico();
		//op.saludar();
		//Referencia a un metodo estatico
		IOperacion op1 = MedRefApp::refMetodoStatico;
		op1.saludar();
		
		
	}
	
	public static void main(String[] args) {
		MedRefApp app = new MedRefApp();
		//app.operar();
		//app.referenciaMetodoInstanciaObjArbitrario();
		//Se implementa el metodo saludar de la interface Funcional
//		IOperacion op = app::referenciaMetodoInstanciaObjParticular;
//		op.saludar();
		app.referenciaConstructor();
	}
}
