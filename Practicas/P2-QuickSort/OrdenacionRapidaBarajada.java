public class OrdenacionRapidaBarajada extends OrdenacionRapida {
	
	// Implementación de QuickSort con reordenación aleatoria inicial (para comparar tiempos experimentalmente)
	public static <T extends Comparable<? super T>> void ordenar(T v[]) {
		barajar(v);
		OrdenacionRapida.ordenar(v);
    }

	// reordena aleatoriamente los datos de un vector
    private static <T> void barajar(T v[]) {
		for(int i=0;i<v.length;i++){
			int rnd = Ordenacion.aleat.nextInt(v.length);
			Ordenacion.intercambiar(v, i, rnd);
		}
    }	
	
}
