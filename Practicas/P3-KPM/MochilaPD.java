import java.util.ArrayList;

public class MochilaPD extends Mochila {

	public SolucionMochila resolver(ProblemaMochila pm) {

		// Obtenemos el numero total de objetos (número de unidades de cada tipo de item)

		int numElem = 0;
		for(int i=0; i<pm.size(); i++){
			numElem += pm.getUnidad(i);
		}

		// Obtengo la informacion de cada item del problema
		// Guardo la informacion de valores y pesos consecutivos en un array, controlando el nº de unidades de cada item

		ArrayList<Item> items = pm.getItems();
		int [] valores = new int[numElem];
		int [] pesos   = new int [numElem];
		int indice = 0;

		for(Item it : items){
			int unidades = it.unidades;
			for(int i=0; i<unidades; i++){
				valores[indice] = it.valor;
				pesos [indice]  = it.peso;
				indice++;
			}
		}


		int [] solucion = reconstruirSolucion(numElem,pm.getPesoMaximo(),pesos,valores);
		ArrayList<Integer> vector = transformarSolucion(solucion,pm);

		solucion = new int [vector.size()];
		for(int i=0; i<vector.size(); i++) solucion[i] = vector.get(i);

		int solucionPeso = obtenerResultado(solucion,pm.getPesos());
		int solucionValor = obtenerResultado(solucion,pm.getValores());

		SolucionMochila sm = new SolucionMochila(solucion,solucionPeso,solucionValor);

		return sm;
	}

	private int [][] generarTabla(int n, int W, int[] pesos, int[] valores){

		// Generamos la tabla a partir de la ecuacion de Bellman

		int a [][] = new int[n+1][W+1];

		for(int i=0; i<=n; i++){
			for(int j=0;j<=W; j++){
				if(i==0 || j==0) a[i][j] = 0;
				else if (pesos[i-1] > j) a[i][j] = a[i-1][j];
				else a[i][j] = Math.max(valores[i-1] + a[i-1][j-pesos[i-1]], a[i-1][j]);
			}
		}
		return a;
	}

	private int [] reconstruirSolucion(int n, int W, int[] pesos, int[] valores){

		// A partir de la solucion del valor que esta en la esquina inferior derecha reconstruimos la solución.
		// Guardamos en un array 0 o 1 según tengamos que guardar ese elemento o no.

		int [] sol = new int[n];
		int [][] a = generarTabla(n,W,pesos,valores);

		int res = a[n][W];
		int p = W;

		for(int i=n; i>0 && res>0; i--){
			if(res == a[i-1][p]) sol[i-1] = 0;
			else {
				sol[i-1] = 1;
				res = res-valores[i-1];
				p   = p-pesos[i-1];
			}
		}
		return sol;
	}

	private ArrayList<Integer> transformarSolucion(int [] sol, ProblemaMochila pm){

		// Transformamos el array binario de solucion en una lista con la cantidad de cada item.

		ArrayList<Item> items = pm.getItems();
		ArrayList<Integer> solucion = new ArrayList<>(pm.size());

		int indice = 0;
		for(Item it : items){
			int unidades = it.unidades;
			int valor = 0;
			for(int i=0; i<unidades; i++){
				valor += sol[indice];
				indice++;
			}
			solucion.add(valor);
		}
		return solucion;
	}

	private int obtenerResultado(int[] sol, int [] array){
		int res = 0;
		for(int i=0; i<sol.length;i++){
			res+= sol[i]*array[i];
		}
		return res;
	}
}
