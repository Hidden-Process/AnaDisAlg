package examen;

/* ENUNCIADO

Vector de n elementos ordenados estrictamente creciente todos distintos excepto uno que está repetido
Encontrar el elemento repetido
*/

public class Parcial1 {

    public static void main(String[] args) {
        int[] A = {12,13,14,15,16,17,18,18,19,20};

        int repeFB = repetidoFB(A);
        System.out.println(repeFB);

        int repeDV = repetidoDV(A, 0, A.length-1);
        System.out.println(repeDV);
    }

    public static int repetidoFB(int[]v){

        int i = 0;
        boolean encontrado = false;

        while(i<v.length-2 && !encontrado){
            if(v[i] == v[i+1]){
                encontrado = true;
            }

            i++;
        }

        return v[i];
    }

    public static int repetidoDV(int[] v, int inf, int sup){
        int valor = -1;

        if(inf<sup){

            int pm = (inf+sup)/2;

            if(v[pm]==v[pm-1] || v[pm] == v[pm+1]) return v[pm];
            
            else if(v[pm]==v[inf]+pm) return repetidoDV(v, pm+1, sup);

            else return repetidoDV(v, inf, pm-1);
        }

        return valor;
    }
    
}
