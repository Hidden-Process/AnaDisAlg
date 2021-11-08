package relacion;

/* ENUNCIADO:

Dado un vector ordenado v, de n elementos distintos, escribir un algoritmo de complejidad logaritmica O(log n) que 
encuentre un número i tal que 1 <= i <= n y v[i] == i.

*/

public class Ejercicio1 {

    public static void main(String [] args){

        int [] v1 = {1,3,5,7,10};

        int [] v2 = {0,2,5,9};

        int [] v3 = {0,1,2,3,4};

        int index = indice(v2, 0, v2.length);
        System.out.println(index);

    }

    /* En el peor de los casos haremos una sola llamada recursiva ya que son casos excluyentes de tamaño mitad del problema,
    más alguna cosntante. Por tanto T(n) = T(n/2) + k ==> T(n) € O(log n)
    */ 
    public static int indice(int[]v, int inf, int sup){
        int index = -1;

        if(inf < sup){
            int pm = (inf+sup)/2;

            if(v[pm] == pm) return pm;
            if(v[pm] > pm) return indice(v,inf,pm-1);
            else return indice(v,pm+1,sup);
        }

        return index;
    }
    
}
