package examen;

import java.util.Arrays;

// Ejercicio correspondiente al primer parcial del curso 20-21.
// Este ejercicio nos pide determinar si el vector s es subvector de v

/* ENUNCIADO:

Sea v un vector de n enteros distintos ordenados ascendentemente. Queremos comprobar si s, un segundo vector de 
tamaño constante k, con k <= n, es subvector de v.

*/

public class Ejercicio2 {

    public static void main(String [] args){

        int [] v = {-10,-3,0,4,7,19,33};
        int [] s = {4,7,19};

        if(fuerzaBruta(v, s)) System.out.println("El algoritmo por Fuerza Bruta determina que s es un subarray de v \r\n");
        
        if(subArray(v, s, 0, v.length-1)) System.out.println("La primera implementación del algoritmo por DyV determina que s es un subarray de v \r\n");

        if(subArrayBinary(v, s)) System.out.println("La segunda implementación del algoritmo por DyV determina que s es un subarray de v \r\n");

    }

    // Implementación por Fuerza Bruta --> T(n) € O(n)
    public static boolean fuerzaBruta(int[]v, int[]s){
        int i=0;
        int j=0;

        while(i<v.length && v[i] != s[0]) i++;
        while(j<s.length && v[i+j]==s[j]) j++;

        return j == s.length;
    } 

    // Implementación 1 por Divide y Vencerás --> T(n) € O(log n)
    // En esta implementación nos fabricamos nuestra propia versión de la busqueda binaria.
    public static boolean subArray(int[]v, int[]s, int inf, int sup){
        if(inf >= sup) return false;

        if(inf<sup){
            int pm = (inf+sup) / 2;

            if(v[pm] == s[0]) return compararArrays(v,s,pm);
            if(v[pm] > s[0]) return subArray(v, s, inf, pm-1);
            else return subArray(v, s, pm+1, sup);

        } return false;
    }

    // Implementación 2 por Divide y Vencerás T(n) € O(log n)
    // En esta implementación utilizamos la clase BinarySearch predefinida en Java
    public static boolean subArrayBinary(int[]v, int[]s){
        int pos = Arrays.binarySearch(v, 0, v.length-1, s[0]);

        if(pos==-1) return false;
        else return compararArrays(v, s, pos);
    }


    // El enunciado nos dice que el vector s es de longitud constante k por tanto este método es constante T(n) --> O(1)
    private static boolean compararArrays(int[]v, int[]s, int pm){
        int i=0;
        while(i<s.length && v[pm+i] == s[i]) i++;
        return i == s.length;
    }
    
}
