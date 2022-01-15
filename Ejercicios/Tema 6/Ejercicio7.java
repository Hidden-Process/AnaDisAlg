import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Ejercicio7 {
    
    // Num Edificios y Solares
    private static int K = 3;

    // Matriz de costes
    private static int [][] costes = {{1,2,8},{4,5,3},{1,7,9}};
    private static String [] edificios = {"Banco", "Hotel", "Colegio"};

    public static void main(String [] args){
       int [] sol =  algoritmo(costes);
       System.out.println("Edificio -> Solar");
       System.out.println("-----------------");
       for(int i=0; i<sol.length;i++){
           System.out.println( edificios[i] + " -> " + (sol[i]+1));
       } 

       int total = 0;
       for(int i=0; i<sol.length;i++){
           total += costes[i][sol[i]];
       }
       System.out.println("-----------------");
       System.out.println("El coste total es: " + total );
    }

    private static int [] algoritmo(int [][] tabla){
        int [] sol = new int[K];
        int [] msol = new int[K];
        int mcal = Integer.MAX_VALUE;

        msol = backtracking(tabla,0,sol,msol,mcal);
        return msol;
    }

    private static int [] backtracking(int [][] tabla, int ind,  int [] sol, int [] msol, int mcal){
        if(ind == K) {
            int cal = suma(sol,tabla);
            if(cal < mcal) return sol;
            else return msol;
        } else {
            List<Integer> opciones = posibilidades(sol,ind);

            for(int i=0; i<opciones.size(); i++){
                int [] cp = sol.clone();
                cp[ind] = opciones.get(i);

                int[] otra = backtracking(tabla, ind+1, cp, msol, mcal);

                if(suma(otra,tabla) < mcal){
                    msol = otra;
                    mcal = suma(otra,tabla);
                }
            }
        }
        return msol;
    }

    private static int suma (int [] sol, int [][] tabla){
        int res = 0;
        for(int i=0; i<sol.length; i++) res += tabla[i][sol[i]];
        return res;
        }

    private static List<Integer> posibilidades(int [] sol, int ind){
        List<Integer> list = new LinkedList<>(Arrays.asList(0,1,2)) ;
        for(int i=1; i<ind; i++) list.remove(sol[i-1]);
        return list;
    }
    
}
