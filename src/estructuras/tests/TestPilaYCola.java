package estructuras.tests;

import src.estructuras.ColaGenerica;
import src.estructuras.PilaGenerica;

public class TestPilaYCola {

	private static PilaGenerica<Integer> pila = new PilaGenerica<Integer>();
	private static ColaGenerica<Integer> cola = new ColaGenerica<Integer>();

	public static void main(String[] args) {
		System.out.println("Pila test");
		pila.apilar(3);
		pila.apilar(2);
		System.out.format("Tope de pila: %d%n", pila.tope());
		System.out.println("Cola test");
		cola.encolar(3);
		cola.encolar(2);
		System.out.format("Frente de cola: %d%n", cola.frente());
	}

}
