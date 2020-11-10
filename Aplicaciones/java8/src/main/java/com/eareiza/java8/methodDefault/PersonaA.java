package com.eareiza.java8.methodDefault;

public interface PersonaA {
	public void caminar();
	
	default public void hablar() {
		System.out.println("hello metodo hablar(), de la interface PersonaA.");
	}
}
