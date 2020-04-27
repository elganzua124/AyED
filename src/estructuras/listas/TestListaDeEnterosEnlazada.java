package estructuras.listas;

/* Ej. 1.3.
 * Escriba una clase llamada TestListaDeEnterosEnlazada que reciba
 * en su m�todo main una secuencia de n�meros, los agregue a un
 * objeto de tipo ListaDeEnterosEnlazada y luego imprima los
 * elementos de dicha lista.
*/

import java.util.Scanner;

public class TestListaDeEnterosEnlazada {
	private static Scanner consola;


	public static void main(String[] args) {
		ListaDeEnterosEnlazada lista = new ListaDeEnterosEnlazada();
		consola = new Scanner(System.in);
		System.out.print("Por favor, ingrese un n�mero: ");
		int num = consola.nextInt();
		lista.comenzar();
		while (num != 0) {
			lista.agregarFinal(num);
			System.out.print("Por favor, ingrese un n�mero: ");
			num = consola.nextInt();
		}
		System.out.println("Contenido de lista: ");
		imprimir(lista);
		System.out.println("Ahora en sentido inverso: ");
		lista.comenzar();
		imprimirInverso(lista);

	}

	public static void imprimir(ListaDeEnterosEnlazada lista) {
		lista.comenzar();
		while (!lista.fin()) {
			System.out.println(lista.proximo());
		}
	}

	
	/* Ej. 1.5.
	 * Escriba un m�todo recursivo que imprima los elementos de una lista en
	 * sentido inverso. La lista la recibe por par�metro.
	 */
	
	
	public static void imprimirInverso(ListaDeEnterosEnlazada lista) {
		if (!lista.fin()) {
			int valor = lista.proximo();
			imprimirInverso(lista);
			System.out.println(valor);
		}
	}
}
