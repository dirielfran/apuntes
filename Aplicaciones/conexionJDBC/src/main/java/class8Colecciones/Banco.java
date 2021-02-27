package class8Colecciones;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/*En un software del Banco Galicia existe la clase Banco con los siguientes atributos:
private ArrayList<Persona> fila = new ArrayList();
		Además tiene los siguientes métodos:
		1) public int cuantasPersonasHay() {
		return fila.size();
		}
		Que devuelve el total de las personas que están en la fila de la ventanilla.
		2) public void agregaPersona (Persona persona){
		 fila.add(persona);
Este software funciona bien y actualmente se encuentra productivo. Hay muchas clases que
consumen la clase Banco ya que es la más importante de todas.
		La clase Persona tiene como atributos: dni, nombre y apellido.
Nos pidieron realizar el siguiente cambio: el software no debe permitir agregar personas
duplicadas a la fila del banco (actualmente si lo permite). Esto quiere decir que no se debe
dejar agregar a una persona si ya existe otra con su mismo dni en la fila.*/
public class Banco {
	private Set<Persona> fila = new LinkedHashSet<Persona>();
	public int cuantasPersonasHay() {
		
		return fila.size();
	}
	public void agregaPersona (Persona persona){
		
		 fila.add(persona);
	}
	
	
	public Set<Persona> getFila() {
		return fila;
	}
	public void setFila(Set<Persona> fila) {
		this.fila = fila;
	}
	

	

}
