public class Ejercicio4{

    // Num Vertices
    private static int V = 7;

    // Num Max Colores
    private static int K = 4;

    // Matriz Adyacencia               a b c d e f g 
    private static  int [][] graph = {{0,0,1,0,1,1,0},  // a
                                      {0,0,1,0,1,1,1},  // b
                                      {1,1,0,0,1,0,1},  // c
                                      {0,0,0,0,0,1,1},  // d
                                      {1,1,1,0,0,0,1},  // e
                                      {1,1,0,1,0,0,0},  // f
                                      {0,1,1,1,1,0,0}}; // g
    public static void main(String[] args){
        colorearGrafo(graph,K);
    }

    private static void colorearGrafo(int[][] g, int k){
        int [] sol = new int [V];
        for(int i=0; i<V; i++) sol[i] = 0;
        if(!coloreado(graph,K,sol,0)) System.out.println("No existe solución");
        else System.out.println(printSolucion(sol));
    }

    private static boolean coloreado(int[][] g, int k, int[]s, int ind){
        if(ind == V) return true;
        for(int c=1; c<=k; c++){
            if(esSeguro(g,c,s,ind)){
                s[ind] = c;
                if(coloreado(g, k, s, ind+1)) return true;
                s[ind] = 0;
            }  
        }
        return false;
    }

    private static boolean esSeguro(int [][] g, int c, int [] s, int ind){
        for(int i=0; i<V ; i++) {
            if(g[ind][i] == 1 && c == s[i]) return false;
        }
        return true;
    }

    private static String printSolucion(int [] sol){
        StringBuilder sb = new StringBuilder();
        sb.append("Graph [ ");
        for(int i=0; i<sol.length; i++) sb.append(sol[i] + ", ");
        sb.setLength(sb.length()-2);
        sb.append(" ]");
        return sb.toString();
    }
}