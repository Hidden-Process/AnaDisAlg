package examen;

// Ejercicio correspondiente al primer parcial del curso 19-20
// Mismo ejercicio preguntado en el curso 18-19 aunque con la diferencia de A[0] si puede ser 0

/* ENUNCIADO:

Sea un array ordenado estrictamente decreciente de numeros naturales, con A[0] = k> 0.
Se pide obtener el menor elemento superior a k que no este en el array.
Si están todos los elementos, el número que falta es el siguiente.

*/

public class Ejercicio1 {
	
	public static void main(String [] args) {
		
		// Ejemplos 19-20
		int [] v1 = {21,22,24,30,36,37};
		int [] v2 = {21,22,23,24,25,26};
		
		int sol1 = menor(v1,0,v1.length-1);
		int sol2 = menor(v2,0,v2.length-1);
		
		System.out.println(sol1 + " - " + sol2 + "\r\n");
		
		// Ejemplos 18-19
		int [] a1 = {0,1,2,6,9,11,15};
		int [] a2 = {1,2,3,4,6,9,11,15};
		int [] a3 = {0,1,2,3,4,5,6};
		
		int res1 = menor(a1, 0, a1.length-1);
		int res2 = menor(a2,0,a2.length-1);
		int res3 = menor(a3, 0, a3.length-1);
		
		System.out.println(res1 + " - " + res2 + " - " + res3);
 		
	}
	
	public static int menor(int[] A, int inf, int sup) {
		
		int menor = 0;
		
		if(A[sup] - A[inf] == sup - inf) return A[sup]+1;

		while(inf<sup) {
			
			int m = (inf+sup)/2;

			if((A[m] - m) == A[0]) return menor(A, m+1, sup);
			
			else return menor(A, inf, m-1);
			
		}
		
		return menor;
	}


}

