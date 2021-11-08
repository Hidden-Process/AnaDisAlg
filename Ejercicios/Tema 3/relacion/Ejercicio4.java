package relacion;

/* ENUNCIADO:

Dado un vector V de n enteros, Se pide escribir un algoritmo de complejidad O(nlogn) que encuentre el subvector 
de suma máxima.

*/

public class Ejercicio4 {

    public static void main(String [] args){

        int [] vector = {700,-500,400,500,-1500,880};

        Rango r = subVector(vector,0,vector.length-1);

        System.out.println(r.toString());

    }

    public static Rango subVector(int[]v, int inf, int sup){

        if(inf==sup) return new Rango(inf,sup,v[inf]);

        int m = (inf+sup)/2;

        Rango r1 = subVector(v, inf, m);
        Rango r2 = subVector(v, m+1, sup);

        int sol1 = v[m];
        int aux1 = v[m];
        int ind1 = m;

        for(int i=m-1;i>=inf;i--){
            aux1 = aux1 + v[i];
            if(aux1 > sol1){
                sol1 = aux1;
                ind1 = i;
            }
        }

        int sol2 = v[m+1];
        int aux2 = v[m+1];
        int ind2 = m+1;

        for(int j=m+2;j<=sup;j++){
            aux2 = aux2+v[j];
            if(aux2 > sol2){
                sol2 = aux2;
                ind2 = j;
            }
        }

        int sol = sol1+sol2;

        if(r1.getSuma() >= r2.getSuma() && r1.getSuma() >= sol) return r1;
        if(r2.getSuma() >= r1.getSuma() && r2.getSuma() >= sol) return r2;
        else return new Rango(ind1, ind2, sol);
    }

    
}
