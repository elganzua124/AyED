package tp04.recorrido;

import estructuras.ArbolGeneral;
import estructuras.ColaGenerica;
import estructuras.ListaEnlazadaGenerica;
import estructuras.ListaGenerica;

public class Recorrido {

	public ListaGenerica<String> recorrido(ArbolGeneral<Character> arbol) {
		ListaEnlazadaGenerica<String> resultado = new ListaEnlazadaGenerica<String>();
		ColaGenerica<ArbolGeneral<Character>> cola = new ColaGenerica<ArbolGeneral<Character>>();
		String aux = "";

		cola.encolar(arbol);
		cola.encolar(null);
		while (!cola.esVacia()) {
			ArbolGeneral<Character> a = cola.desencolar();
			if (a != null) {
				aux = aux + a.getDatoRaiz();
				ListaGenerica<ArbolGeneral<Character>> hijos = a.getHijos();
				hijos.comenzar();
				while (!hijos.fin()) {
					cola.encolar(hijos.proximo());
				}
			} else {
				resultado.agregarFinal(aux);
				if (!cola.esVacia()) {
					aux = "";
					cola.encolar(null);
				}
			}
		}
		return resultado;
	}

	public static void main(String[] args) {
		// Casos de ejemplo para probar su implementación.
		// Se instancia el siguiente ejemplo:
		//
		// A
		// / \
		// B C
		// / | \ \
		// D E F G
		// \
		// H

		ArbolGeneral<Character> arbolA = new ArbolGeneral<>('A');
		ArbolGeneral<Character> arbolB = new ArbolGeneral<>('B');
		ArbolGeneral<Character> arbolC = new ArbolGeneral<>('C');
		ArbolGeneral<Character> arbolD = new ArbolGeneral<>('D');
		ArbolGeneral<Character> arbolE = new ArbolGeneral<>('E');
		ArbolGeneral<Character> arbolF = new ArbolGeneral<>('F');
		ArbolGeneral<Character> arbolG = new ArbolGeneral<>('G');
		ArbolGeneral<Character> arbolH = new ArbolGeneral<>('H');

		arbolA.agregarHijo(arbolB);
		arbolA.agregarHijo(arbolC);

		arbolB.agregarHijo(arbolD);
		arbolB.agregarHijo(arbolE);
		arbolB.agregarHijo(arbolF);

		arbolC.agregarHijo(arbolG);

		arbolF.agregarHijo(arbolH);

		ListaGenerica<String> it = new Recorrido().recorrido(arbolA);
		it.comenzar();
		while (!it.fin()) {
			System.out.println(it.proximo());
		}

	}
}
