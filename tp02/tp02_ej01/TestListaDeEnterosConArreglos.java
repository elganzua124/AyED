package tp02_ej01;

import java.util.Scanner;

public class TestListaDeEnterosConArreglos {
	public static void main(String[] args) {
		ListaDeEnterosConArreglos lista = new ListaDeEnterosConArreglos();
		lista.comenzar();
		Scanner consola = new Scanner(System.in);
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
