
*****************************************************************************************7ptos
  091122**************************************************************************************
    Create a function with two arguments that will return an array of the 
    first n multiples of x.

    Assume both the given number and the number of times to count will be positive 
    numbers greater than 0.

    Return the results as an array or list ( depending on language ).

    Examples
    countBy(1,10)  // should return  {1,2,3,4,5,6,7,8,9,10}
    countBy(2,5)  // should return {2,4,6,8,10}
    ****************************************************************************
      import java.util.stream.IntStream;

      public class Kata{
        public static int[] countBy(int x, int n){
          
          return IntStream.rangeClosed(1, n)
            .map(i -> i * x)
            .toArray();
        }
      }
    ****************************************************************************
**********************************************************************************************
*****************************************************************************************7ptos
  101122**************************************************************************************
    Hubo un examen en tu clase y lo pasaste. ¡Felicidades!
    Pero eres una persona ambiciosa. Quiere saber si es mejor que el estudiante 
    promedio de su clase.

    Recibe una matriz con los puntajes de las pruebas de sus compañeros. ¡Ahora 
    calcule el promedio y compare su puntuación!

    ¡ Regresa Truesi estás mejor, de lo contrario False!

    Nota:
    Sus puntos no están incluidos en la matriz de puntos de su clase. ¡Para 
    calcular el punto promedio, puede agregar su punto a la matriz dada!
  ****************************************************************************
    import java.util.Arrays;
    class Kata {
        static boolean betterThanAverage(final int[] classPoints, final int yourPoints) {
            return Arrays.stream(classPoints).average().orElse(0) < yourPoints;
        }
    }
  ****************************************************************************
    import java.util.stream.*;

    public class Kata {
      public static boolean betterThanAverage(int[] classPoints, int yourPoints) {
        return IntStream.of(classPoints).sum()/classPoints.length < yourPoints;
      }
    }
**********************************************************************************************
