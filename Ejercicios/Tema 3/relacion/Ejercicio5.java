package relacion;

import java.util.Arrays;

/* ENUNCIADO

Dado un vector de enteros todos distintos y un número s.
Diseñar un algoritmo V de orden de complejidad O(nlogn) que determine si existen dos numeros en V que al sumarlos den s.

*/

public class Ejercicio5 {

    public static void main(String [] args){
        
        int[] v = {2,5,6,9,12,34};
        int s = 43;

        if(sumaFB(v, s)) System.out.println("La implemetación por fuerza bruta determina que si existe ese par de elementos en V \r\n");

        if(sumaDV(v, s)) System.out.println("La implementación por Divide y Venceras determina que si existen ese par de elementos en V \r\n");
    }

    /* 
    Implementación por Fuerza Bruta:
    Tenemos dos bucles anidados que en el peor caso ambos se ejecutan n veces.
    Por tanto T(n) € O(n^2)
    */
    public static boolean sumaFB(int[] v, int s){

        boolean encontrado = false;
        int i=0;
        while(i<v.length && !encontrado){
            int j=i+1;
            while(j<v.length){
                if(v[i] + v[j] == s) encontrado = true;
                j++;
            }

            i++;
        }

        return encontrado;
    }

    /*
    Implementación por Divide y Vencerás.
    Quicksort = Arrays.sort con complejidad O(nlogn)
    BinarySearch con complejidad (log n) pero dentro de un bucle que en el peor de los casos se ejecuta n veces. Por tanto
    complejidad O(nlogn) también. Por tanto la complejidad del algoritmo final es:
    T(n) = Tqs + n * Tbs + k ==> T(n) = nlogn + nlogn ==> max(nlogn, nlogn) = nlogn ==> T(n) € O(nlogn)
    La implementación por Divide y vencerás nos reduce la complejidad a O(nlogn) respecto a la cuadrática de fuerza bruta.
    */
  
    public static boolean sumaDV(int[]v, int s){
        
        boolean suma = false;

        // We use Quicksort to sort the array
        Arrays.sort(v);

        // We use Binary Search once its sorted
        int i = 0;
        while(i<v.length && !suma){
            int index = Arrays.binarySearch(v, 0, v.length-1, s-v[i]);
            if(index != -1) suma = true;
            i++;
        }

        return suma;
    }
    
}
