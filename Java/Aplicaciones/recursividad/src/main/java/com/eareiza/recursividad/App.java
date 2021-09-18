package com.eareiza.recursividad;

/**
 * Hello world!
 *
 */
public class App{
    public static void main( String[] args ) {
        System.out.println( sumar(10) );
        System.out.println( sumarEntre(5, 10));
    }
    
    //Use la recursividad para sumar todos los números hasta el k.
    public static int sumar(int k){
    	if(k>0) {
    		return k+sumar(k-1);
    	}else {
    		return 0;
    	}
    }
    
    //Use la recursividad para sumar todos los números entre 5 y 10.
    public static int sumarEntre(int ini, int fin) {
    	if(fin > ini) {
    		return fin + sumarEntre(ini, fin-1);
    	}else {
    		return 0;
    	}
    }
}
