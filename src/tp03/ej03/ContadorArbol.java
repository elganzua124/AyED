package tp03.ej03;

import estructuras.arbol.ArbolBinario;
import estructuras.listas.ListaDeEnterosEnlazada;

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
