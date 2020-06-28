package tp03.ej04;

import estructuras.arboles.ArbolBinario;

public class RedBinariaLlena {
	/*
	 * Una red binaria es una red que posee una topología de árbol binario lleno.
	 * Los nodos que conforman una red binaria llena tiene la particularidad de que
	 * todos ellos conocen cuál es su retardo de reenvío. El retardo de reenvío se
	 * define como el período comprendido entre que un nodo recibe un mensaje y lo
	 * reenvía a sus dos hijos. Su tarea es calcular el mayor retardo posible, en el
	 * camino que realiza un mensaje desde la raíz hasta llegar a las hojas en una
	 * red binaria llena. Indique qué estrategia (recorrido en profundidad o por
	 * niveles) utilizará para resolver el problema. Cree una clase Java llamada
	 * RedBinariaLlena donde implementará lo solicitado en el método
	 * retardoReenvio():int
	 */
	private static ArbolBinario<Integer> arbol;

	public RedBinariaLlena(ArbolBinario<Integer> arbol) {
		RedBinariaLlena.arbol = arbol;
	}

	private static int retardoReenvio(ArbolBinario<Integer> arbol) {
		if (arbol.esVacio()) {
			return 0;
		}
		if (arbol.esHoja()) {
			return arbol.getDatoRaiz();
		}
		return arbol.getDatoRaiz()
				+ Math.max(retardoReenvio(arbol.getHijoIzquierdo()), retardoReenvio(arbol.getHijoDerecho()));
	}

	public static int retardoReenvio() {
		return RedBinariaLlena.retardoReenvio(arbol);
	}

	public static void main(String[] args) {
		ArbolBinario<Integer> raiz = new ArbolBinario<Integer>(2);
		ArbolBinario<Integer> hijo1 = new ArbolBinario<Integer>(3);
		ArbolBinario<Integer> hijo2 = new ArbolBinario<Integer>(6);
		ArbolBinario<Integer> hijo11 = new ArbolBinario<Integer>(1);
		ArbolBinario<Integer> hijo12 = new ArbolBinario<Integer>(4);
		ArbolBinario<Integer> hijo21 = new ArbolBinario<Integer>(5);
		ArbolBinario<Integer> hijo22 = new ArbolBinario<Integer>(7);

		// armar arbol

		hijo1.agregarHijoIzquierdo(hijo11);
		hijo1.agregarHijoDerecho(hijo12);
		hijo2.agregarHijoIzquierdo(hijo21);
		hijo2.agregarHijoDerecho(hijo22);
		raiz.agregarHijoIzquierdo(hijo1);
		raiz.agregarHijoDerecho(hijo2);

		ArbolBinario<Integer> ab = new ArbolBinario<Integer>(Integer.valueOf(40));
		ArbolBinario<Integer> hijoIzquierdo = new ArbolBinario<Integer>(25);
		hijoIzquierdo.agregarHijoIzquierdo(new ArbolBinario<Integer>(10));
		hijoIzquierdo.agregarHijoDerecho(new ArbolBinario<Integer>(32));
		ArbolBinario<Integer> hijoDerecho = new ArbolBinario<Integer>(78);
		ab.agregarHijoIzquierdo(hijoIzquierdo);
		ab.agregarHijoDerecho(hijoDerecho);

		arbol = ab;

		System.out.println(retardoReenvio());

	}
}
