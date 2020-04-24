package tp02.ej01;

/* Ej. 1.3.
 * Escriba una clase llamada TestListaDeEnterosEnlazada que reciba
 * en su método main una secuencia de números, los agregue a un
 * objeto de tipo ListaDeEnterosEnlazada y luego imprima los
 * elementos de dicha lista.
*/

import java.util.Scanner;

public class TestListaDeEnterosEnlazada {
	public static void main(String[] args) {
		ListaDeEnterosEnlazada lista = new ListaDeEnterosEnlazada();
		Scanner consola = new Scanner(System.in);
		System.out.print("Por favor, ingrese un número: ");
		int num = consola.nextInt();
		lista.comenzar();
		while (num != 0) {
			lista.agregarFinal(num);
			System.out.print("Por favor, ingrese un número: ");
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
	 * Escriba un método recursivo que imprima los elementos de una lista en
	 * sentido inverso. La lista la recibe por parámetro.
	 */
	
	
	public static void imprimirInverso(ListaDeEnterosEnlazada lista) {
		if (!lista.fin()) {
			int valor = lista.proximo();
			imprimirInverso(lista);
			System.out.println(valor);
		}
	}
}
