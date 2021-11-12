
public class Algoritmo {
	
	public static void f(int n) {
	
	}
	
	public static void O1(int n) {
		for(int i=0; i<100;i++) {
			
		}
	}
	
	public static void OLOGN(int n) {
		while (n > 0) {
            n /= 2;
        }
	}
	
	public static void ON(int n) {
		for(int i=0; i<n;i++) {

		}
	}
	
	public static void ONLOGN(int n) {
	    for (int i = 1; i < n; i++) {
	    	for (int j = 1; j < n; j *= 2) {

	            }
	        }
	    }
	
	public static void ON2(int n) {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {

            }
        }
    }

    public static void ON3(int n) {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                for (int k = 1; k < n; k++) {
                	
                }
            }
        }
    }
    
    public static void O2N(int n) {

        for (int i = 1; i <= Math.pow(2, n); i++) {

        }
    }
    
    private static int factorial(int n) {
        if (n <= 1)
            return 1;
        else
            return n * factorial(n - 1);
    }

    public static void ONF(int n) {

        for (int i = 1; i <= factorial(n); i++) {

        }
    }
    
}
