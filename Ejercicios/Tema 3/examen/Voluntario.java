package examen;

public class Voluntario {

   public static void main(String[] args) {
        
    int [] A = {100,120,134,142,150,154,149,141,130,120,119,102,96,91,98,104,108,111};

    int fb = desnivelFB(A);
    System.out.println(fb);

    int dv = desnivelDV(A, 0,A.length-1 );
    System.out.println(dv);

    }

    public static int desnivelFB(int[] v){

        int i = 0;
        int max = 0;
        int min = 0;

        while(v[i]<v[i+1]) i++;
        max = v[i];

        while(v[i]>v[i+1]) i++;
        min = v[i];

        return (max-min);
    }

    public static int desnivelDV(int[]v, int inf, int sup){
        int max = desnivelDVmax(v, inf, sup);
        int min = desnivelDVmin(v, inf, sup);

        return max-min;
    }

    private static int desnivelDVmax(int[] v, int inf, int sup){
        int max = 0;

        if(inf < sup){
            int m = (inf+sup)/2;

            if(v[m]>v[m-1] && v[m]>v[m+1]) max = v[m];
            else if(v[m]>v[m-1] && v[m]<v[m+1]) max = desnivelDV(v, m+1, sup);
            else max = desnivelDV(v, inf, m-1);
        }

        return max;
    }

    private static int desnivelDVmin(int[]v, int inf, int sup){
        int min = 0;

        if(inf < sup){
            int m = (inf + sup) / 2;

            if(v[m]<v[m-1] && v[m]<v[m+1]) min = v[m];
            else if(v[m]<v[m-1] && v[m]>v[m+1]) min = desnivelDVmin(v, m+1, sup);
            else min = desnivelDV(v, inf, m-1);
        }

        return min;
    }
}