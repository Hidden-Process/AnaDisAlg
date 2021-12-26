import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class MochilaAV extends Mochila {

	public SolucionMochila resolver(ProblemaMochila pm) {

		// Vector Solución, nos indica la cantidad de objetos de cada tipo.

		int [] solucion = new int [pm.size()];
		for(int i=0; i < solucion.length; i++) solucion[i] = 0;

		// Inicializamos la solucion.

		SolucionMochila sm = new SolucionMochila(solucion,0,0);

		// Obtenemos la información del problema
		int [] pesos = pm.getPesos();
		int [] valor = pm.getValores();
		int [] unidades = pm.getUnidades();
		int pesoMax = pm.getPesoMaximo();

		// Creamos un array con los items del problema que ordenaremos según su ratio
		Item[] items = new Item[pm.size()];
		for(int i=0; i<pm.size();i++) items[i] = new Item(i, pesos[i],valor[i],unidades[i]);

		Arrays.sort(items, new OrdenarRatio());

		// Lista de posibles candidatos que en principio son todos los items
		LinkedList<Item> candidatos = new LinkedList<>();
		for(Item it : items){
			candidatos.add(it);
		}

		// Total de peso y valor acumulado, aun nada porque no hemos añadido nada a nuestro vector solución.
		int pesoTotal  = 0;
		int valorTotal = 0;

		// Escogemos el mejor candidato y lo añadimos a nuestra solución.
		// Mientras queden candidatos que probar y no hayamos llegado al maximo que puede guardar
		while( !candidatos.isEmpty() && sm.getSumaPesos() < pm.pesoMaximo){

			Item mejorCandidato = candidatos.get(0);
			int i = mejorCandidato.indice; // Para saber que item es y guardarlo en el lugar correcto del vector solucion.

			/* Metemos el item candidato en la mochila si al sumar su peso mas lo que habia en la mochila
			no superamos el peso maximmo, si ese candidato no cabe lo borramos y probamos con el siguiente mejor. */
			if(sm.getSumaPesos() + mejorCandidato.peso <= pesoMax){
				solucion[i] = solucion[i]+1;
				pesoTotal += mejorCandidato.peso;
				valorTotal += mejorCandidato.valor;
				sm = new SolucionMochila(solucion,pesoTotal,valorTotal);

				if(unidades[i] == 1) candidatos.remove(0);
				else unidades[i]--;

			} else {
				candidatos.remove(0);
			}
		}
		return sm;
	}

	/*
	* Implementamos nuestra propia clase Item y establecemos su propio metodo compare para comparar items
	* a nuestro criterio, en este caso, por su ratio, usando la interfaz Comparator.
	* Implementación adaptada del ejemplo 5 de geeks for geeks:
	* https://www.geeksforgeeks.org/arrays-sort-in-java-with-examples/
    */

	class Item{

		int indice;
		int peso;
		int valor;
		int unidades;
		private double ratio;

		// Añado el ratio a esta implementación, que se calcula dividiendo el valor entre el peso
		public Item(int indice, int peso, int valor,int unidades){
			this.indice = indice;
			this.peso = peso;
			this.valor = valor;
			this.unidades = unidades;
			this.ratio = (double) valor / (double) peso;
		}
	}

	class OrdenarRatio implements  Comparator<Item> {

		// Lo quiero ordenar por mayor ratio
		public int compare (Item i1, Item i2){
			int res = 0;
			if (i1.ratio - i2.ratio > 0) res = -1;
			if (i1.ratio - i2.ratio < 0) res = 1;
			return res;
		}
	}
}
