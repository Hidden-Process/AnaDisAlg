public class Ejercicio1 {

    private static int [][] tablero;
    private static int n;
    private static int [] ejeX = {2, 1,-1,-2,-1,-2, 2, 1};
    private static int [] ejeY = {1, 2, 2, 1,-2,-1,-1,-2};

    public static void main (String [] args){
        n=5;
        tablero = new int [n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                tablero[i][j]=0;
            }
        }

        tablero = backtracking(2,2);

        String res;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(tablero[i][j] > 9) res = String.valueOf(tablero[i][j]);
                else res = " " + tablero[i][j];
                System.out.print(" " + res);
            }
            System.out.println("");
        }
    }

    public static int [][] backtracking(int x, int y){
        tablero[x][y] = 1;
        VA(2,x,y);
        return tablero;
    }

    public static boolean VA (int k, int x, int y){
        int orden = 0;
        boolean exito = false;
        int u,v;

        while( (!exito) && (orden < ejeX.length)){
            u = x + ejeX[orden];
            v = y + ejeY[orden];

            if((u>=0 && u<n) && (v>=0 && v<n) && (tablero[u][v] == 0)){
                tablero[u][v] = k;
                if(k < n*n){
                    exito = VA(k+1,u,v);
                    if(!exito) tablero[u][v]=0;
                } else exito = true;
            }
            orden++;
        }
        return exito;
    }
}