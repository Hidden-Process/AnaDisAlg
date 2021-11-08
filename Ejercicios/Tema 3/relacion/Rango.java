package relacion;

// Clase Auxiliar que la realización del ejercicio 4.

public class Rango {

    private int inferior;
    private int superior;
    private int suma;

    public Rango(int inf, int sup, int sum){
        inferior = inf;
        superior = sup;
        suma = sum;
    }

    public int getSuma(){
        return suma;
    }

    public int getInferior(){
        return inferior;
    }

    public int getSuperior(){
        return superior;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Rango [ ");
        sb.append(inferior);
        sb.append(" , ");
        sb.append(superior);
        sb.append(" , ");
        sb.append(suma);
        sb.append(" ]");
        return sb.toString();
    }
    
}
