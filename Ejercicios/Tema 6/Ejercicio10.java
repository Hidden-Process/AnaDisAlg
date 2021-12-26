import java.util.List;
import java.util.ArrayList;

public class Ejercicio10 {

    private static boolean [][] t = {{false, true, false, true},
                                     {false, true, true, false},
                                     {true, true, false, true}};

    private static int n = t.length;
    private static int m = t[0].length;

    public static void main(String[] args){
        int[] sol = new int[n];
        List<int[]> todas = new ArrayList<int[]>();
        int i = 0;
        asignar(sol,todas,i);
        System.out.println("Soluciones Encontradas: ");
        System.out.println("----------------------- ");
        System.out.println(" ");
        for(int[] s : todas) verSolucion(s);
    }

    private static void asignar(int[] sol, List<int[]> todas, int i){
        if(i == n) todas.add(sol.clone());
        else{
            int j=0;
            while(j<m){
                if(esFactible(sol,i,j)){
                    sol[i] = j;
                    asignar(sol, todas, i+1);
                }
                j++;
            }
        }
    }

    private static boolean esFactible(int[] sol, int i, int j){
        return t[i][j] && !contiene(sol,i,j);
    }

    private static boolean contiene(int[] sol, int i, int j){
        int k=0;
        while(k<i && sol[k] != j) k++;
        return k<i;
    }

    private static void verSolucion(int[] s){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<n; i++) sb.append((s[i]+1) + ",");
        sb.setLength(sb.length()-1);
        sb.append("]");
        System.out.println(sb.toString());
        System.out.println(" ");
    }
}