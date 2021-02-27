package com.eareiza.colecciones;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Hello world!
 *
 */
public class setApp 
{
    public static void main( String[] args )
    {
    	//Se crea y se inicializa HashSet
    	final Set hashSet = new HashSet(1_000_000);
    	//Almacenamos el tiempo actual en milisegundos cuando comienza
    	final Long startHashSetTime = System.currentTimeMillis();
    	//Se  le añaden los elementos al hashSet
    	for (int i = 0; i < 1_000_000; i++) {
    	    hashSet.add(i);
    	}    	
    	//Almacenamos el tiempo actual en milisegundos cuando termina
    	final Long endHashSetTime = System.currentTimeMillis();
    	//Se imprime cuanto tarda
    	System.out.println("Time spent by HashSet: " + (endHashSetTime - startHashSetTime));
    	 
    	
    	//Se crea TreeSet
    	final Set treeSet = new TreeSet();
    	//Almacenamos el tiempo actual en milisegundos cuando comienza
    	final Long startTreeSetTime = System.currentTimeMillis();
    	//Se  le añaden los elementos 
    	for (int i = 0; i < 1000000; i++) {
    	    treeSet.add(i);
    	}
    	//Almacenamos el tiempo actual en milisegundos cuando termina
    	final Long endTreeSetTime = System.currentTimeMillis();
    	//Se imprime cuanto tarda
    	System.out.println("Time spent by TreeSet: " + (endTreeSetTime - startTreeSetTime));
    	
    	//Se crea LinkedHashSet
    	final Set linkedHashSet = new LinkedHashSet(1_000_000);
    	//Almacenamos el tiempo actual en milisegundos cuando comienza
    	final Long startLinkedHashSetTime = System.currentTimeMillis();
    	//Se  le añaden los elementos 
    	for (int i = 0; i < 1000000; i++) {
    	    linkedHashSet.add(i);
    	}
    	//Almacenamos el tiempo actual en milisegundos cuando termina
    	final Long endLinkedHashSetTime = System.currentTimeMillis();
    	//Se imprime cuanto tarda
    	System.out.println("Time spent by LinkedHashSet: " + (endLinkedHashSetTime - startLinkedHashSetTime));
    }
}
