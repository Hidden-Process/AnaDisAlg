import java.util.TimerTask;

class Descartar extends TimerTask {

	@Override
	public void run() {
		
		double ratio = Analizador.descartes();
		System.out.println(Analizador.masCercano(ratio));
		System.exit(0);
	}

}