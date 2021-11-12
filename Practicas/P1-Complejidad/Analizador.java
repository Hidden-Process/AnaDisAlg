import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Analizador {
	
	private static ArrayList <Integer> entrada    = new ArrayList<>();
	private static ArrayList <Double>  ratios     = new ArrayList<>(); 
	private static ArrayList <Double>  resultados = new ArrayList<>();
	
	
	public static void main(String arg[]) {
		

        TimerTask timerTask = new Descartar();
        Timer timer = new Timer();

        timer.schedule(timerTask, 9000);

        rellenarEntrada(entrada);
		calcularRatios(entrada, ratios);
		//System.out.println(min(resultados));
		String res = masCercano(min(resultados));
		System.out.println(res);
		
        timer.cancel();
		
	}
	
	public static String masCercano(double ratio) {
		
		if (ratio < 1.1) {                          	// aprox 1.0
			return "1";							
		} else if(1.1 <= ratio && ratio < 1.334) {
			return "LOGN";
		}else if (1.334 <= ratio && ratio < 2.2) {     // aprox 2.0
			return "N";
		} else if(2.2 <= ratio && ratio < 3) {
			return "NLOGN";
		} else if (3 <= ratio && ratio < 6) {   	  // aprox 4.0
			return "N2";
		} else if (6 <= ratio && ratio < 10) {        // aprox 8.0
			return "N3";
		} else if(10 <= ratio && ratio < 1500){
			return "2N";
		} else { 	
			return "NF";						       
		}	
}
	
	private static double max(ArrayList <Double> lista) {
		
		double max = 0;
		
		for(double valor : lista) {
			if(valor > max) max = valor;
		}
		
		return max;
	}
	
	private static double min(ArrayList <Double> lista) {
		
		double min = Double.MAX_VALUE;
		
		for(double val : lista) {
			if(val < min) min = val;
		}
		
		return min;
	}
	
	private static void rellenarEntrada(ArrayList<Integer> entrada) {
		
		// Valores bajos
		
		for(int i=1; i<=5;i++) {
			entrada.add(i*1);
		}
		
		// Valores medianos
		
		for(int i=1; i<=5; i++) {
			entrada.add(i*100);
		}
		
		// Valores grandes
		
		for (int i=1; i<=5; i++) {
			entrada.add(i*10000000);
		}
		
	}
	
	private static void calcularRatios(ArrayList <Integer> entrada, ArrayList <Double> ratios) {
		
		Temporizador t = new Temporizador();
		
		ArrayList <Double> tmp = new ArrayList<>();
		
		for(int i=0; i<5 ; i++) {
			
			for(int valor : entrada) {
		
				t.reiniciar();
				
				t.iniciar();
				Algoritmo.f(valor);
				t.parar();
				long t1 = t.tiempoPasado();
				
				t.reiniciar();
				
				t.iniciar();
				Algoritmo.f(valor*2);
				t.parar();
				long t2 = t.tiempoPasado();
				
				double ratio = (double)t2/t1;
				
				ratios.add(ratio);
				tmp.add(ratio);
			}
			
			
			double maxval = max(tmp);
			//System.out.println(maxval);
			resultados.add(maxval);
			tmp.clear();
		}
		
	}
	
		public static double descartes() {
			
			double media = 0;
			int cont = 0;
			
			for (double d : ratios) {
				if(d > 1) {
					media += d;
					cont++;
				}
			}
			
			return media / cont;
		}
	
}
