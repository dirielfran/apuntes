
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



public class HelloWorld {
  private static final String HELLO_WORLD
    = "`````````````````````````````````````````````````````````````````````````````````````````"
    + "``[-]@>````````````````````````````[-]@@@`````````@@@@@@@````````````````````````````````"
    + "``@@@@<````````````````````````````@@@@@@``````````@@@@@@````````````````````````````````"
    + "````@@>````````````````````````````````++`````````````+++````````````````````````````````"
    + "````++/````````````````````````````````++`````````````+[@````````````````````````````````"
    + "````@@<+++++/````````;+++++````````````@@`````````````@@@```````````@@@@@@```````````````"
    + "````@@@@@@@@@@``````@@@@@@@@@``````````@@`````````````@@@`````````>-]@@@@@@<`````````````"
    + "````++++@.;---`````@@@@.>+++[@`````````@@`````````````@@@````````<+++@>;-]@@`````````````"
    + "````@@@`````@@<```--@.`````@@@`````````@@`````````````@@@````````@@@``````@@@````````````"
    + "````@@.`````++;```+@@```````@@@````````@@`````````````@@@```````.@@```````>++````````````"
    + "````++/`````++;```+++[@@@@@@@@@````````@@`````````````@@@```````@@@````````@@<```````````"
    + "````--/`````--;```-----@@@@@@@@````````@@`````````````@@@```````@@@````````@@@```````````"
    + "````@@>`````-]<```++@``````````````````@@`````````````@@@```````.@@````````@@>```````````"
    + "````++/`````++;```+++/`````````````````++`````````````[@@````````@@@``````@@@````````````"
    + "``@@@@@@``<+++++```++++@>;-]@@@```@@@@@@@@@@@@````@@@@@@@@@@@````<++++;;++@@.````````````"
    + "``@@@@@@``@@@@@@````@@@@@@@@@@@```@@@@@@@@@@@@```@@@@@@@@@@@@>````;+++[@@@@<`````````````"
    + "```---@>```-]@@<`````;+@@@@@.``````+++@@@@@@@`````.@@@@@@@@@>```````++[@@<```````````````"
    + "`````````````````````````````````````````````````````````````````````````````````````````"
    + "`````````````````````````````````````````````````````````````````````````````````````````"
    + "``````````````````````````````````````````````````--@@@@@```````````````@@@@@```````@@@@`"
    + "```````````````````````````````````````````````````@@@@@@```````````````>-]@@```````@@@@`"
    + "``````````````````````````````````````````````````````@@@`````````````````@@@```````@@@@`"
    + "``````````````````````````````````````````````````````@@@`````````````````@@@```````<--@`"
    + "``@@@@.````>+++[``````@@@@@<```````---@>`/-]@@````````@@@``````````<+@@@@`@@@```````.@@@`"
    + "`@@@@@@````@@@@@@```@@@@@@@@@`````@@@@@>+++++++```````+[@`````````@@@@@@@@@@@````````@@@`"
    + "``@@@@@````<----/``----@>;-]@@`````@@@@@@@@<---```````@@@````````@@@@./@@@@@@````````@@@`"
    + "```@@;``@@;``@@```/@@@`````@@@@``````@@@@@````````````@@@```````/@@@`````@@@@````````@@@`"
    + "```@@@`@@@@`;@@```@@@```````@@@``````@@@``````````````@@@```````@@@```````@@@````````@@@`"
    + "```@@@`@@@@`@@@```@@;````````@@``````@@/``````````````@@@```````@@@```````@@@````````@@@`"
    + "````@@/@@@@@@@@```@@;````````@@``````@@/``````````````@@@```````@@;```````@@@````````@@``"
    + "````@@@@@@@@@@````@@@```````@@@``````@@/``````````````@@@```````@@@```````@@@````````````"
    + "````@@@@;`@@@@````;@@@`````;@@@``````@@/``````````````@@@```````;@@;`````@@@@````````````"
    + "````@@@@``@@@@`````@@@@@;@@@@@````@@@@@@@@@@;`````@@@@@@@@@@@````@@@@@;@@@@@@@@``````/@``"
    + "`````@@@``/@@@``````@@@@@@@@@`````@@@@@@@@@@@````@@@@@@@@@@@@;````@@@@@@@@@@@@@``````@@@`"
    + "`````@@````@@/````````@@@@@/``````;@@@@@@@@@``````;@@@@@@@@@;``````/@@@@@`@@@@```````@@;`"
    + "`````````````````````````````````````````````````````````````````````````````````````````";

  public static String greet() {
    return new BrainFuck(HELLO_WORLD).process("");
  }
}

class BrainFuck {
  public static final char OP_NEXT = '>';
  public static final char OP_PREVIOUS = '<';
  public static final char OP_INCREMENT = '+';
  public static final char OP_DECREMENT = '-';
  public static final char OP_WRITE = '.';
  public static final char OP_READ = ',';
  public static final char OP_JUMP_ZERO = '[';
  public static final char OP_JUMP_NONZERO = ']';

  private final String code;

  public BrainFuck(final String code) {
    this.code = code;
  }

  public String process(final String input) {
    final Data data = new Data(2);
    int inputPointer = 0;
    final StringBuilder output = new StringBuilder();
    for (int instructionPointer = 0; instructionPointer < code.length(); instructionPointer++) {
      final char instruction = code.charAt(instructionPointer);
      switch (instruction) {
        case OP_NEXT:
          data.next();
          break;
        case OP_PREVIOUS:
          data.previous();
          break;
        case OP_INCREMENT:
          data.increment();
          break;
        case OP_DECREMENT:
          data.decrement();
          break;
        case OP_WRITE:
          output.append((char) Byte.toUnsignedInt(data.get()));
          break;
        case OP_READ:
          data.set((byte) input.charAt(inputPointer++));
          break;
        case OP_JUMP_ZERO:
          if (data.get() == 0) {
            instructionPointer = matchNext(instructionPointer);
          }
          break;
        case OP_JUMP_NONZERO:
          if (data.get() != 0) {
            instructionPointer = matchPrevious(instructionPointer);
          }
          break;
      }
    }
    return output.toString();
  }

  private int matchNext(final int instructionPointer) {
    return match(instructionPointer, true);
  }

  private int matchPrevious(final int instructionPointer) {
    return match(instructionPointer, false);
  }

  private int match(final int start, final boolean next) {
    final int step;
    final char same;
    final char match;
    if (next) {
      step = 1;
      same = OP_JUMP_ZERO;
      match = OP_JUMP_NONZERO;
    } else {
      step = -1;
      same = OP_JUMP_NONZERO;
      match = OP_JUMP_ZERO;
    }

    for (int instructionPointer = start + step, level = 1; instructionPointer < code.length(); instructionPointer += step) {
      final char nextInstruction = code.charAt(instructionPointer);
      if (nextInstruction == same) {
        level++;
      } else if (nextInstruction == match && --level == 0) {
        return instructionPointer;
      }
    }
    throw new IllegalStateException(String.format("no match for start=%s and next=%b", start, next));
  }
}

class Data {
  private final int growth;
  private byte[] data;
  private int pointer = 0;

  Data(final int growth) {
    this.growth = growth;
    data = new byte[growth];
  }

  void next() {
    pointer++;
  }

  void previous() {
    pointer--;
  }

  void increment() {
    ensureCapacity();
    data[pointer]++;
  }

  void decrement() {
    ensureCapacity();
    data[pointer]--;
  }

  byte get() {
    ensureCapacity();
    return data[pointer];
  }

  void set(final byte b) {
    ensureCapacity();
    data[pointer] = b;
  }

  private void ensureCapacity() {
    final int difference = data.length - pointer;
    final int rate;
    final int destPos;

    if (difference <= 0) {
      rate = 1 - difference / growth;
      destPos = 0;
    } else if (difference > data.length) {
      rate = 1 + difference / growth;
      destPos = rate * growth;
    } else {
      return;
    }

    final byte[] data = new byte[this.data.length + rate * growth];
    System.arraycopy(this.data, 0, data, destPos, this.data.length);
    this.data = data;
    pointer += destPos;
  }
}








https://us02web.zoom.us/j/84882711549?pwd=U3ErNmg1aDBDdjVST2ZydDdZTDJDdz09