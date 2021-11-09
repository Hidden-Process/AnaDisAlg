package relacion;

/* ENUNCIADO:

Sea A un array unimodal consistente en una secuencia estrictamente creciente seguida de otra estrictamente decreciente.
Se pretende construir una función pico que dado el array nos devuelva su pico, es decir el valor del array donde pasa de ser 
creciente a decreciente.

*/

public class Ejercicio7 {

    public static void main(String [] args){

        int[] A = {1,5,7,9,6};

        int picoFB = picoFB(A);

        System.out.println("El pico según la implementación por FB es: " + picoFB + "\r\n");

        int picoDV = picoDV(A, 0, A.length-1);

        System.out.println("El pico según la implementación por DV es: " + picoDV + "\r\n");
    }

    /*  Implementación por fuerza bruta:

    En el peor de los casos tenemos un bucle que se va a ejecutar n veces acompañado de una constante.
    Por tanto Tn = n + k ==> T(n) € O(n)

    */
    public static int picoFB(int[] v){

        int i=0;
        boolean salir = false;

        while(i<v.length-1 && !salir){
            if(v[i]>v[i+1]) salir = true;
            i++;
        }

        if(salir)return v[i-1];
        
        return -1;
    }

    /*  Implementación por DyV

    En el peor de los casos tenemos una llamada recursiva al mismo problema con la entrada reducida a la mitad más una constante
    Por tanto T(n) = T(n/2) + k ==> a = 1, b = 2, d = 0 => Según el teorema maestro sabemos que a = b ^ d
    Por tanto T(n) € O(n^d logn) ==> T(n) € O(log n)
    La implementación por divide y venceras nos reduce el problema de una complejidad lineal a una logaritmica.
    
    */

    public static int picoDV(int[]v, int inf, int sup){
        int pico = -1;

        if(inf<sup){
            int m = (inf+sup)/2;

            if(v[m]>v[m-1] && v[m]>v[m+1]) pico = v[m];

            else if(v[m]>v[m-1] && v[m]<v[m+1]) return picoDV(v, m+1, sup);

            else return picoDV(v, inf, m-1);
        }

        return pico;
    }
    
}
