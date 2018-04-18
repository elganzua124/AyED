package tp02_ej01;

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

	}

	public static void imprimir(ListaDeEnterosEnlazada lista) {
		lista.comenzar();
		while (!lista.fin()) {
			System.out.println(lista.proximo());
		}
	}

	public static void imprimirInverso(ListaDeEnterosEnlazada lista) {
		if (!lista.fin()) {
			int valor = lista.proximo();
			imprimirInverso(lista);
			System.out.println(valor);
		}
	}
}
