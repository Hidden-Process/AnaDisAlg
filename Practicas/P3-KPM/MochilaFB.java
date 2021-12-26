public class MochilaFB extends Mochila {
	
	public SolucionMochila resolver(ProblemaMochila pm) {

		SolucionMochila sm= null;

		// Obtenemos el numero de items distintos
		int numItems = pm.size();

		// Obtenemos todas las posibles combinaciones
		int combinaciones = 1;

		// La cantidad de unidades de cada item disponible mas la posibilidad de no usar ninguno de ese tipo.
		for(int i=0;i<numItems;i++){
			combinaciones *= (pm.getUnidad(i)+1);
		}

		// Obtenemos la información del problema

		int [] pesos = pm.getPesos();
		int [] valores = pm.getValores();
		int [] unidades = pm.getUnidades();

		// Generación de todas las posibles combinaciones usando el módulo.
		// Se ha adaptado lo que se comenta en este post de StackOverflow https://stackoverflow.com/a/30707153
		for(int p=0; p<combinaciones;p++){

			int aux = p;
			int [] res = new int[numItems];
			int miPeso = 0;
			int miBeneficio = 0;

			for(int i=0;i<numItems;i++){
				res[i] = (aux%(unidades[i]+1));
				miPeso+= pesos[i]*res[i];
				miBeneficio += valores[i]*res[i];
				aux /= (unidades[i]+1);
			}

			// Si es el primero o mejoro el mejor resultado anterior sin pasarme del peso, guardo el resultado.
			if(miPeso <= pm.getPesoMaximo()){
				if(sm == null || miBeneficio > sm.getSumaValores()){
					sm = new SolucionMochila(res,miPeso,miBeneficio);
				}
			}
		}
		return sm;
	}
}
