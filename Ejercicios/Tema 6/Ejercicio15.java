import java.util.List;
import java.util.LinkedList;

public class Ejercicio15 {

    // Matriz Adyacencia           a b c d e f g 
    private static  int [][] v = {{0,0,1,0,1,1,0},  // a
                                  {0,0,1,0,1,1,1},  // b
                                  {1,1,0,0,1,0,1},  // c
                                  {0,0,0,0,0,1,1},  // d
                                  {1,1,1,0,0,0,1},  // e
                                  {1,1,0,1,0,0,0},  // f
                                  {0,1,1,1,1,0,0}}; // g

    private static boolean ok = false;
    private static int n = v.length;
    private static List<Integer> mejor = new LinkedList<Integer>();

    public static void main (String[] args){
        int k = 3;
        List<Integer> sol = new LinkedList<Integer>();

        primeraSolKSupervisores(sol,k);

        if(ok){
            System.out.println("Primera solución con k <= " + k + " supervisores: ");
            verSolucion(sol);
        } else  System.out.println("No existe una solución con k <= " + k + "supervisores como máximo");
        
        sol.clear();

        mejorSolSupervisores(sol);
        System.out.println("Solución con el mínimo número de supervidores: ");
        verSolucion(mejor);
    }

    private static void primeraSolKSupervisores(List<Integer> sol, int k){
        if(esSolucion(sol)) ok = true;
        else {
            int j=0;
            while(!ok && j<n){
                if(esFactible(sol,j,k)){
                    sol.add(j);
                    primeraSolKSupervisores(sol, k);
                    if(!ok) sol.remove(sol.size()-1);
                }
                j++;
            }
        }
    }

    private static void mejorSolSupervisores(List<Integer> sol){
        if(esSolucion(sol)) {
            if(mejor.isEmpty() || mejor.size() > sol.size()) mejor = new LinkedList<Integer>(sol);
        } else {

            int j = 0;
            while(j<n){
                if(!sol.contains(j)){
                    sol.add(j);
                    mejorSolSupervisores(sol);
                    sol.remove(sol.remove(sol.size()-1));
                }
                j++;
            }
        }
    }

    private static boolean esFactible(List<Integer> sol, int j, int k){
        return !sol.contains(j) && sol.size() < k;
    }

    private static boolean esSolucion(List<Integer> sol){
        int i = 0;
        int j;
        boolean res = true;

        while(i<n && res){
            j=0;
            while(j<n && !(sol.contains(j) && v[i][j] == 1 )) j++;
            res = sol.contains(i) || j<n;
            i++;
        }
        return res;
    }

    private static void verSolucion(List<Integer> sol){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<sol.size(); i++){
            switch(sol.get(i)){
                case 0 : sb.append("a,");
                         break;
                case 1 : sb.append("b,");
                         break;
                case 2 : sb.append("c,");
                         break;
                case 3 : sb.append("d,");
                         break;
                case 4 : sb.append("e,");
                         break;
                case 5 : sb.append("f,");
                         break;
                case 6 : sb.append("g,");
                         break;
            }
        }
        sb.setLength(sb.length()-1);
        sb.append("]");
        System.out.println(sb.toString());
        System.out.println(" ");
    }
    
}
