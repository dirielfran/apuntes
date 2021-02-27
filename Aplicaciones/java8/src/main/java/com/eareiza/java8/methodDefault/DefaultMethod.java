package com.eareiza.java8.methodDefault;

public class DefaultMethod implements PersonaA, PersonaB {

	@Override
	public void caminar() {
		System.out.println("Hello methodDefault");
		
	}

	@Override
	public void hablar() {
		//Se indica el metodo de que interface se utilizara
		PersonaA.super.hablar();
	}

	public static void main(String[] args) {
		DefaultMethod app = new DefaultMethod();
		app.caminar();
		app.hablar();
	}



}
