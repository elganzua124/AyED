package estructuras.tests;

/* Ej. 1.2.
 * Escriba una clase llamada TestListaDeEnterosConArreglos que reciba en su
 * m�todo main una secuencia de n�meros, los agregue a un objeto de tipo
 * ListaDeEnterosConArreglos y luego imprima los elementos de dicha lista.
 * */


import java.util.Scanner;

import estructuras.ListaDeEnterosConArreglos;

public class TestListaDeEnterosConArreglos {
	private static Scanner consola;

	public static void main(String[] args) {
		ListaDeEnterosConArreglos lista = new ListaDeEnterosConArreglos();
		lista.comenzar();
		consola = new Scanner(System.in);
		System.out.print("Por favor, ingrese un número: ");
		int num = consola.nextInt();
		while (num != 0) {
			lista.agregarFinal(num);
			System.out.print("Por favor, ingrese un número: ");
			num = consola.nextInt();
		}
		System.out.println("Contenido de lista: ");
		lista.comenzar();
		while (!lista.fin()) {
			System.out.println(lista.proximo());
		}
	}
}
