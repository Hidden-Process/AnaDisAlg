public class OrdenacionRapida extends Ordenacion {
  
	public static <T extends Comparable<? super T>> void ordenar(T v[]) {
	    ordRapidaRec(v, 0, v.length-1);
	}

	// Debe ordenar ascendentemente los primeros @n elementos del vector @v con 
	// una implementación recursiva del método de ordenación rápida.	
	public static <T extends Comparable<? super T>> void ordRapidaRec(T v[], int izq, int der) {
	    if(izq < der){
			T pivote = v[der];
			int s = partir(v, pivote, izq, der);
			ordRapidaRec(v, izq, s-1);
			ordRapidaRec(v, s+1, der);
		}
	}
	   
   public static <T extends Comparable<? super T>> int partir(T v[], T pivote, int izq, int der) {

	int posPiv = der;	
	int i = izq;
	
		for(int j=izq;j<der;j++){
			if(v[j].compareTo(pivote)<=0){
				Ordenacion.intercambiar(v, i, j);
				i++;
			}
		}

		Ordenacion.intercambiar(v, i, posPiv);
		
	    return i;    	
   }

	// Pequeños ejemplos para pruebas iniciales.
	public static void main (String args[]) {
	
		// Un vector de enteros
		Integer vEnt[] = {3,8,6,5,2,9,1,1,4};
		ordenar(vEnt);
		System.out.println(vectorAString(vEnt));

		// Un vector de caracteres
		Character vCar[] = {'d','c','v','b'};
		ordenar(vCar);
		System.out.println(vectorAString(vCar));

	}	
    
}
