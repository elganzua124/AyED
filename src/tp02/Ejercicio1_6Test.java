package tp02;

import estructuras.listas.ListaDeEnterosEnlazada;

/* Ej. 1.6 b)
 * Escriba un m�todo main que pruebe el m�todo implementado en a)
 * y recorra la lista resultado e imprima cada uno de los elementos.
*/

public class Ejercicio1_6Test {

	public static void main(String[] args) {

		Ejercicio1_6 f = new Ejercicio1_6();
		ListaDeEnterosEnlazada l = f.calcularSucesion(6);
		System.out.println("Contenido de lista sucesion: ");
		imprimir(l);
	}

	public static void imprimir(ListaDeEnterosEnlazada lista) {
		lista.comenzar();
		while (!lista.fin()) {
			System.out.println(lista.proximo());
		}
	}

}
