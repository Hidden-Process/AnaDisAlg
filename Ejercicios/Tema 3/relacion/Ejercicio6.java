package relacion;

/* ENUNCIADO

Sea un Array A que contiene valores numéricos naturales todos distintos.
Decimos que un dos posiciones (i < j) están invertidas si (A[i] > A[j]).
Implemetar un algoritmo que nos devuelve el número de posiciones invertidas en el array dado.

*/

public class Ejercicio6 {

    public static void main (String [] args){
        
        int [] A = {2,4,1,3,5};

        int numInvFB = InvFB(A);
        int numInvDV = InvFB(A);

        System.out.println("Según la implementación por FB el número de inversiones en el Array es de: " + numInvFB + "\r\n");
        System.out.println("Según la implementación por DV el número de inversiones en el Array es de: " + numInvDV + "\r\n");

    }

    /* Implementación por Fuerza Bruta

    Tenemos dos bucles anidados que en le peor de los casis se ejecutan n veces ambos seguidos de una constante que no afecta a la
    complejidad final. Por tanto esta implementación por FB nos ofrece una complejidad cuadratica.
    T(n) € O(n^2)

    */
    public static int InvFB(int[]v){
        int cont = 0;

        for(int i=0;i<v.length-1;i++){
            for(int j=i+1;j<v.length;j++){
                if(v[i] > v[j]) cont++;
            }
        }

        return cont;
    }

    /* Implementación por Divide y Vencerás

    Dividimos el array de entrada en 2 submitades del mismo tamaño. Creamos una función que nos cuente el numero de inversiones
    mientras va ordenando por mezcla (MergeSort). Utulizamos divide y venceras para contar la inversiones en ambas mitades
    por separado y en la mezcla.

    Este algoritmo presenta una complejodad de T(n) = 2T(n/2) + Tmerge + k , como el merge mas explicado posteriormente de 
    complejidad lineal. Esto quedaría de la siguiente manera T(n) = 2T(n/2) + n + k
    Que aplicando el teorema maestro reducido vemos como a=2, b=2, d=1 y como a = b^d ==> T(n) € O(n^d logn).
    Si resolvemos la expresión vemos como obtenemos que T(n) € O(nlogn).

    */

    public static int invDV(int[]v, int izq, int der){
        int cont = 0;

        if(izq < der){
            int m = (izq+der)/2;

            cont += invDV(v, izq, m-1);        // Inversiones en el subarray izq
            cont += invDV(v, m+1, der);       //  Inversiones en el subarray der
            cont += mergeSort(v,izq,m,der);  //   Inversiones en el proceso de merge
        }

        return cont;
    }

    /*
    Podemos comprobar como este método auxliar tiene complejidad lineal ya que recorre 2 veces el array completo en el peor de los 
    casos y añade alguna constante.
    T(n) = 2n + k ==> T(n) € O(n)

    */
    private static int mergeSort(int[] v, int izq, int m, int der){

        // Cremao 2 subarrays del mismo tamaño
        int [] a = new int [m];
        int [] b = new int[m];

        // Rellenamos los 2 subarrays con los valores del original
        for(int i=0;i<m;i++)    a[i] = v[i];
        for(int j=m;j<=der;j++) b[j] = v[j];

        // Creamos las variables que necesitaremos
        int i = 0;
        int j = 0;
        int inv = 0;
        int k = izq;

        // Vamos ordenado reescribiendo el vector de entrada contando el número de inversiones en el proceso.
        while(i<a.length && j<b.length){
            if(a[i] <= b[j]){
                v[k] = a[i];
                k++;
                i++;
            } else {
                v[k] = b[j];
                k++;
                j++;
                inv = m - (izq+i);
            }
        }

        // Terminamos de actualizar los indices y completar el vector.
        while(i < a.length){
            v[k] = a[i];
            k++;
            i++;
        }

        while (j < b.length){
            v[k] = b[j];
            k++;
            j++;
        }

        return inv;

    }
    
}
