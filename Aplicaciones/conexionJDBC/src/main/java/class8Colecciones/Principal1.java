package class8Colecciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Principal1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Banco banco= new Banco();
		Persona persona1=new Persona();

		  persona1= new Persona(95970551,"Yala", "Areiza");
		  Persona persona2= new Persona(29512026,"Ricardo", "Areiza");
		  Persona persona3= new Persona(4484825,"Elvis", "Areiza");
		  Persona persona4= new Persona(44841825,"Elvis", "Areiza");
		  Persona persona5= new Persona(27752929,"Antonio", "Piñango");
		  banco.agregaPersona(persona1);
		  banco.agregaPersona(persona2);
		  banco.agregaPersona(persona3);
		  banco.agregaPersona(persona4);
		  banco.agregaPersona(persona5);
		  
		  
		  Set personas = new TreeSet<Persona>();
		  personas.add(persona1);
		  personas.add(persona2);
		  personas.add(persona3);
		  personas.add(persona4);
		  personas.add(persona5);
		  
		  System.out.println(personas);
//		 
//		  System.out.println(banco.cuantasPersonasHay());
//		  
//		  System.out.println(banco.getFila());
//		  
//		  Set<Persona> duplicado =banco.getFila();
//		  
//		 for (Persona p : duplicado) {
//			System.out.println(p.getNombre());
//		}
		//mapas
//		Map<String, Integer> mapa = new HashMap<String, Integer>();
//		mapa.put("primero", 0);
//		mapa.put("segundo", 1);
//		mapa.put("tercero", 2);
//		System.out.println(mapa);
//		mapa.remove("primero");
//		System.out.println(mapa);
//		System.out.println(mapa.get("primero"));
		//Ordenamiento de lista con obj Collections.sot
//		List<String> lista = Arrays.asList("Ricardo","Elvis","Yalaury","Antonio");
//		System.out.println(lista);
//		
//		Collections.sort(lista);
//		System.out.println(lista);
	}

}
