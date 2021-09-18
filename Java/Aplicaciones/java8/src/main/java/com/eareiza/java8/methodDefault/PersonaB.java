package com.eareiza.java8.methodDefault;

public interface PersonaB {
	default public void hablar() {
		System.out.println("hello metodo hablar(), de la interface Persona B.");
	}
}
