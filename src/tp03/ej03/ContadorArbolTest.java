package tp03.ej03;

import estructuras.arboles.ArbolBinario;
import estructuras.listas.ListaDeEnterosEnlazada;

public class ContadorArbolTest {

	public static void main(String[] args) {
		ArbolBinario<Integer> arbol = new ArbolBinario<Integer>(40);

		ArbolBinario<Integer> hijoizquierdo = new ArbolBinario<Integer>(25);
		hijoizquierdo.agregarHijoIzquierdo(new ArbolBinario<Integer>(10));
		hijoizquierdo.agregarHijoDerecho(new ArbolBinario<Integer>(33));
		ArbolBinario<Integer> hijoderecho = new ArbolBinario<Integer>(78);
		arbol.agregarHijoIzquierdo(hijoizquierdo);
		arbol.agregarHijoDerecho(hijoderecho);

		ContadorArbol c = new ContadorArbol(arbol);

		ListaDeEnterosEnlazada lista = c.numerosPares();

		System.out.println(arbol.toString());

		lista.comenzar();
		System.out.println("Numeros pares del arbol:");
		while (!lista.fin()) {
			System.out.print(lista.proximo() + " | ");
		}

	}

}
