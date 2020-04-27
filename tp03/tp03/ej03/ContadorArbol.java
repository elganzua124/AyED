package tp03.ej03;

/*
  Ej.3
  Defina una clase Java denominada ContadorArbol cuya función principal
  es proveer métodos de validación sobre árboles binarios de enteros.
  Para ello la clase tiene como variable de instancia un
  ArbolBinario<Integer>. Implemente en dicha clase un método denominado
  numerosPares() que devuelve en una estructura adecuada (sin ningún
  criterio de orden) todos los elementos pares del árbol (divisibles
  por 2). Defina la clase dentro del paquete tp03.ejercicio3
  a) Implemente el método realizando un recorrido InOrden.
  b) Implemente el método realizando un recorrido PostOrden.
*/

import arbol_binario.ArbolBinario;
import lista_enteros.ListaDeEnterosEnlazada;

public class ContadorArbol {

	public ContadorArbol(ArbolBinario<Integer> arbol) {
		this.arbol = arbol;
	}

	private ArbolBinario<Integer> arbol;

	public ListaDeEnterosEnlazada numerosPares() {
		ListaDeEnterosEnlazada lista = new ListaDeEnterosEnlazada();
		numerosParesInOrden(arbol, lista);
		return lista;

	}

	// a) InOrden
	private void numerosParesInOrden(ArbolBinario<Integer> arbol, ListaDeEnterosEnlazada lista) {

		if (arbol.esVacio())
			return;

		int dato = arbol.getDatoRaiz();
		if (dato % 2 == 0)
			lista.agregarInicio(dato);

		if (!arbol.getHijoIzquierdo().esVacio())
			numerosParesInOrden(arbol.getHijoIzquierdo(), lista);
		if (!arbol.getHijoDerecho().esVacio())
			numerosParesInOrden(arbol.getHijoDerecho(), lista);

	}

	// b) PostOrden
	@SuppressWarnings("unused")
	private void numerosParesPostOrden(ArbolBinario<Integer> arbol, ListaDeEnterosEnlazada lista) {

		if (arbol.esVacio())
			return;

		if (!arbol.getHijoIzquierdo().esVacio())
			numerosParesPostOrden(arbol.getHijoIzquierdo(), lista);
		if (!arbol.getHijoDerecho().esVacio())
			numerosParesPostOrden(arbol.getHijoDerecho(), lista);

		int dato = arbol.getDatoRaiz();
		if (dato % 2 == 0)
			lista.agregarInicio(dato);

	}

}
