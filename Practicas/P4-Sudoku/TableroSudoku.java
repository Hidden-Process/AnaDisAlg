import java.util.*;

public class TableroSudoku implements Cloneable {
	
	// constantes relativas al numero filas y columnas del tablero
	protected static final int MAXVALOR=9; 
	protected static final int FILAS=9; 
	protected static final int COLUMNAS=9; 
							 
	protected static Random r = new Random();
	
	protected int celdas[][]; // una celda vale cero si está libre.
	
	public TableroSudoku() {
		celdas = new int[FILAS][COLUMNAS]; //todas a cero.
	}

	// crea una copia de su parámetro
	public TableroSudoku(TableroSudoku uno) {
		TableroSudoku otro = (TableroSudoku) uno.clone();
		this.celdas = otro.celdas;
	}

	// crear un tablero a parir de una configuración inicial (las celdas vacías
	// se representan con el caracter ".").
    public TableroSudoku(String s) {
    	this();
    	if(s.length() != FILAS*COLUMNAS) {
    		throw new RuntimeException("Construcción de sudoku no válida.");
    	} else {
    		for(int f=0;f<FILAS;f++) 
				for(int c=0;c<COLUMNAS;c++) {
					Character ch = s.charAt(f*FILAS+c);
					celdas[f][c] = (Character.isDigit(ch) ? Integer.parseInt(ch.toString()) : 0 ); 
				}		
		}		
    }

	/* Realizar una copia en profundidad del objeto
	 * @see java.lang.Object#clone()
	 */
	public Object clone()  {
		TableroSudoku clon;
		try {
			clon = (TableroSudoku) super.clone();
			clon.celdas = new int[FILAS][COLUMNAS]; 
			for(int i=0; i<celdas.length; i++)
				System.arraycopy(celdas[i], 0, clon.celdas[i], 0, celdas[i].length);
		} catch (CloneNotSupportedException e) {
			clon = null;
		}	
		return clon;
	}
	
	/* Igualdad para la clase
	 * @see java.lang.Object#equals()
	 */
	public boolean equals(Object obj) {
		if (obj instanceof TableroSudoku) {
			TableroSudoku otro = (TableroSudoku) obj;
			for(int f=0; f<FILAS; f++)
				if(!Arrays.equals(this.celdas[f],otro.celdas[f]))
					return false;
			return true;		
		} else
			return false;
	}
	
	public String toString() {
		String s = "";

		for(int f=0;f<FILAS;f++) {
			for(int c=0;c<COLUMNAS;c++) 
				s += (celdas[f][c]==0 ? "." : String.format("%d",celdas[f][c])); 
		}
		return s;	
	}

	// devuelva true si la celda del tablero dada por fila y columna está vacía.
	protected boolean estaLibre(int fila, int columna) {
		return celdas[fila][columna] == 0;
	}
	
	// devuelve el n�mero de casillas libres en un sudoku.
	protected int numeroDeLibres() {
		int n=0;
	    for (int f = 0; f < FILAS; f++) 
	        for (int c = 0; c < COLUMNAS; c++)
	        	if(estaLibre(f,c))
	        		n++;
	    return n;
	}
	
	protected int numeroDeFijos() {
		return FILAS*COLUMNAS - numeroDeLibres();
	}

	// Devuelve true si @valor ya esta en la fila @fila.
	protected boolean estaEnFila(int fila, int valor) {
		int columna=0;
		boolean encontrado = false;

		while(columna < COLUMNAS && !encontrado){
			if(celdas[fila][columna] == valor) encontrado = true;
			columna++;
		}
		return encontrado;
	}    

	// Devuelve true si @valor ya esta en la columna @columna.
	protected boolean estaEnColumna(int columna, int valor) {
		int fila=0;
		boolean encontrado = false;

		while(fila < FILAS && !encontrado){
			if(celdas[fila][columna] == valor) encontrado = true;
			fila++;
		}
		return encontrado;
	}    
	
	// Devuelve true si @valor ya esta en subtablero al que pertence @fila y @columna.
	protected boolean estaEnSubtablero(int fila, int columna, int valor) {
		// Sacar subtablero a partir de fila y columna
		// f y c represetan la primera celda del subtablero 

		int f = fila - (fila % 3);
		int c = columna - (columna % 3);
		int reset = c;

		int maxFila = f + 3;
		int maxCol = c + 3;

		boolean encontrado = false;

		while(f < maxFila && !encontrado){
			c = reset;
			while(c < maxCol && ! encontrado){
				if(celdas[f][c] == valor) encontrado = true;
				c++;
			}
			f++;
		}
		return encontrado;		
	}    

	// Devuelve true si se puede colocar el @valor en la @fila y @columna dadas.
	protected boolean sePuedePonerEn(int fila, int columna, int valor) {
		return ((!estaEnColumna(columna, valor)) && (!estaEnFila(fila, valor))
		&& (!estaEnSubtablero(fila, columna, valor)));
	}
	
	protected void resolverTodos(List<TableroSudoku> soluciones, int fila, int columna) {
		if(numeroDeLibres() == 0) soluciones.add(new TableroSudoku(this));
		else {
			if(estaLibre(fila, columna)) { // Si la celda correspondiente esta libre colocamos ahí.
				for(int num=1; num<=MAXVALOR; num++){ // num representa el nº a colocar.
					if(sePuedePonerEn(fila, columna, num)){
						celdas[fila][columna] = num;
						int f,c;
						if(columna < COLUMNAS-1){
							c = columna+1;
							f = fila;
						} else {
							f = fila+1;
							c = 0;
						}
						resolverTodos(soluciones, f, c);
						celdas[fila][columna]=0;
					}
				}
			} else { // Si no está, actualizamos las posiciones para probar en la siguiente
				if(columna < COLUMNAS-1){
					columna++;
				} else {
					fila++;
					columna=0;
				}
				resolverTodos(soluciones, fila, columna);
			}
		}
	}
	
	public List<TableroSudoku> resolverTodos() {
        List<TableroSudoku> sols  = new LinkedList<TableroSudoku>();
        resolverTodos(sols, 0, 0);
		return sols;
	}
	
	public static void main(String arg[]) {
		TableroSudoku t = new TableroSudoku( 
			    ".4....36263.941...5.7.3.....9.3751..3.48.....17..62...716.9..2...96.......312..9.");
		List<TableroSudoku> lt = t.resolverTodos();
		System.out.println(t);
		System.out.println(lt.size());
		for(Iterator<TableroSudoku> i= lt.iterator(); i.hasNext();) {
			TableroSudoku ts = i.next(); 
			System.out.println(ts);	
		}
	}
}
