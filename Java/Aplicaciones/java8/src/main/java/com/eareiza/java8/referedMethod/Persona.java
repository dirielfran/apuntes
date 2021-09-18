package com.eareiza.java8.referedMethod;

public class Persona {
	private String nombre;
	private Integer id;
	
	public Persona(Integer id, String nombre ) {
		super();
		this.nombre = nombre;
		this.id = id;
	}
	public Persona() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
