package com.eareiza.java8.java8;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {
	
	public static boolean solution(String str, String ending) {
	    StringBuilder strB = new StringBuilder();
	    strB.append(str);	    
	    return str.endsWith(ending);
	 }
	
	public static String abbrevName(String name) {
		String[] nombres = name.toUpperCase().split(" ");
		String inicial ="";
		for (int i = 0; i < nombres.length; i++) {
			inicial += nombres[i].substring(0, 1);
			if ((nombres.length-1) != i) {
				inicial += ".";
			}
		}
	    return inicial;
	}
	
	public static String abbrevNameJ8(String name) {
	    return Arrays.stream(name.split(" "))
	                 .map(String::toUpperCase)
	                 .map(word -> word.substring(0, 1))
	                 .collect(Collectors.joining("."));
	               
	  }
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        App app = new App();
        System.out.println(app.solution("elvis", "is"));
        System.out.println(app.abbrevNameJ8("Elvis areiza"));
    }
}
