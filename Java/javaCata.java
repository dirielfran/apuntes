
*********************************************************************************************************
El concepto de Java 8 IntStream hace referencia a Streams compuestos por números enteros.****************
	IntStream.of(args).min().getAsInt();
*********************************************************************************************************


*********************************************************************************************************
Devuelve digitos ordenados de una cantidad***************************************************************
	Su tarea es crear una función que pueda tomar cualquier número entero no negativo como argumento y 
	devolverlo con sus dígitos en orden descendente. Esencialmente, reorganice los dígitos para crear 
	el número más alto posible.

	Ejemplos:
	Entrada: 21445 Salida:54421

	Entrada: 145263 Salida:654321

	Entrada: 123456789 Salida:987654321

	import java.util.Comparator;
	import java.util.stream.Collectors;


	Resuelto Java 8-------------------------------------------------------------------------------------
		public class DescendingOrder {
		    public static int sortDesc(final int num) {
		        return Integer.parseInt(String.valueOf(num)
		                                      .chars()
		                                      .mapToObj(i -> String.valueOf(Character.getNumericValue(i)))
		                                      .sorted(Comparator.reverseOrder())
		                                      .collect(Collectors.joining()));
		    }
		}

	Mi resolucion_______________________________________________________________________________________

		import java.util.ArrayList;
		import java.util.Collections;

		public class DescendingOrder {
		      public static int sortDesc(final int num) {
		      //Se añade en variable la longitud del numero
		      int longitud = Integer.toString(num).length();
		      //Se crea Array de enteros
		      ArrayList<Integer> numeros = new ArrayList<Integer>(); 
		      int div = num;
		      //Se crea bucle la variable longitud
		      for(int i = 0 ; i<longitud ; i++){
		      	//Se saca el resto de la divicion de num / 10
		        int resto = div % 10;
		        //Se alamsena el numero de dividir num / 10
		        div = div / 10;
		        //Se alamacena el resto en el array de enteros
		        numeros.add(resto);
		      }
		      	//Se utiliza la clase colletions para ordenar los numeros
		       Collections.sort(numeros);
		       //Se crea numero auxiliar
		       int aux = 1;
		       int resultado = 0;
		       //Se crea for para que multiplique cada umero del array por su multiplo
		       for(Integer n : numeros){
		       		//Se almacena en resultado el numero del array por el aux
		         	resultado  += n*aux;
		         	//se multiplica por diez el auxiliar en cada iteracion del for
		         	aux *= 10;       
		       }
		       return resultado;
		    }
		}
*********************************************************************************************************


*********************************************************************************************************
Remueve vocales de una cadena****************************************************************************
	Referencias____________________________________________________________________________________
		public class Troll {
	    	private static final Pattern DISEMVOWEL_PATTERN = Pattern.compile("[AaEeIiOoUu]");
		    
		    public static String disemvowel(String str) {
		        return DISEMVOWEL_PATTERN.matcher(str).replaceAll("");
		    }
		}
	Referencias____________________________________________________________________________________
		Nota: (?i) regex coincide mayuscula con minusculas
		public class Troll {
		  public static String disemvowel(String str) {
		      return str.replaceAll("(?i)[aeiou]","");
		  } 
		}
	MiSolucion____________________________________________________________________________________
		public class Troll {
		    public static String disemvowel(String str) {
		        return str.replaceAll("[aeiouAEIOU]","");
		    }
		}
*********************************************************************************************************


*********************************************************************************************************
Devuelve una cadena con todas sus palabras al reves******************************************************
	/*
	 * Complete the function that accepts a string parameter, and reverses each word in the string. All 
	 spaces in the string should be retained.

			Examples
				"This is an example!" ==> "sihT si na !elpmaxe"
				"double  spaces"      ==> "elbuod  secaps"
	 * 
	 * 
	 * */
	MiSolucion____________________________________________________________________________________
		public  static String reverseWords(final String original) {
			//Se alamacena en un array las palabras contenidas en la cadena
			String[] cadena = original.split(" ");
			String cadenaInv  = "";
			int cont = 1;
			//Se recorre el array de palabras
			for (String string : cadena) {	
				//Se recorre cada palabras de manera inversa		
				for (int i = string.length()-1; i >= 0; i--) {
					//Se almacena en variable la palabra al reves
					cadenaInv += string.charAt(i);
				}
				//Coloca espacio entre palabras
				if ( cont++ < cadena.length)
					cadenaInv += " ";
			}
		    return cadenaInv;
		 }
	ResueltoJava8_________________________________________________________________________________
		public static String reverseWords(final String original){
		    return original.trim().isEmpty()?original:Arrays.stream(original.split(" ")).map(s->new StringBuilder(s).reverse().toString()).collect(Collectors.joining(" "));
		  }
	Test__________________________________________________________________________________________
		@Test
	    public void exampleCases() {
	        assertEquals("ehT kciuq nworb xof spmuj revo eht yzal .god", EcjemplosCod.reverseWords("The quick brown fox jumps over the lazy dog."));
	        assertEquals("elppa", EcjemplosCod.reverseWords("apple"));
	        assertEquals("a b c d", EcjemplosCod.reverseWords("a b c d"));
	        assertEquals("elbuod  decaps  sdrow", EcjemplosCod.reverseWords("double  spaced  words"));
	    }
*********************************************************************************************************


*********************************************************************************************************
String Ej 12/07/2020*************************************************************************************
	Complete the solution so that it returns true if the first argument(string) passed in ends with the 
	2nd argument (also a string).

		Examples:

		solution('abc', 'bc') // returns true
		solution('abc', 'd') // returns false
	MiSolucion____________________________________________________________________________________
		public class Kata {
		  public static boolean solution(String str, String ending) {
		    return str.endsWith(ending);
		  }
		}

	OtraSolucion__________________________________________________________________________________
		public class Kata {
		  public static boolean solution(String str, String ending) {
		    return str.length() >= ending.length() ? 
		    str.substring(str.length()-ending.length()).equals(ending) : false;

		  }
		}
*********************************************************************************************************

*********************************************************************************************************
String Ej 12/07/2020*************************************************************************************
	Write a function to convert a name into initials. This kata strictly takes two words with one space in between them.

	The output should be two capital letters with a dot separating them.

	It should look like this:

	Sam Harris => S.H

	Patrick Feeney => P.F
	MiSolucion____________________________________________________________________________________
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
	ResueltoJava8_________________________________________________________________________________
		public static String abbrevNameJ8(String name) {
	    return Arrays.stream(name.split(" "))
	                 .map(String::toUpperCase)
	                 .map(word -> word.substring(0, 1))
	                 .collect(Collectors.joining("."));
	               
	  }
*********************************************************************************************************