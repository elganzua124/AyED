package lista_enteros;

/* Ej. 1.2.
 * Escriba una clase llamada TestListaDeEnterosConArreglos que reciba en su
 * m�todo main una secuencia de n�meros, los agregue a un objeto de tipo
 * ListaDeEnterosConArreglos y luego imprima los elementos de dicha lista.
 * */


import java.util.Scanner;

public class TestListaDeEnterosConArreglos {
	public static void main(String[] args) {
		ListaDeEnterosConArreglos lista = new ListaDeEnterosConArreglos();
		lista.comenzar();
		Scanner consola = new Scanner(System.in);
		System.out.print("Por favor, ingrese un n�mero: ");
		int num = consola.nextInt();
		while (num != 0) {
			lista.agregarFinal(num);
			System.out.print("Por favor, ingrese un n�mero: ");
			num = consola.nextInt();
		}
		System.out.println("Contenido de lista: ");
		lista.comenzar();
		while (!lista.fin()) {
			System.out.println(lista.proximo());
		}
	}
}
