package tp02_ej03;

//import tp02_ejercicio2.TestListaEnlazadaGenerica;
//prueba

public class Test {

	private static PilaGenerica<Integer> pila = new PilaGenerica<Integer>();
	private static ColaGenerica<Integer> cola = new ColaGenerica<Integer>();

	public static void main(String[] args) {
		System.out.println("Pila test");
		pila.apilar(3);
		pila.apilar(2);
		System.out.format("Tope de pila: %d%n" , pila.tope());
		System.out.println("Pila test");
		pila.apilar(3);
		pila.apilar(2);
		System.out.format("Tope de pila: %d%n" , cola.tope()); // revisar clase cola
		
	}

}
