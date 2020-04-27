package pila_y_cola;

import listas_genericas.ListaEnlazadaGenerica;

public class ColaGenerica<T> {

	private ListaEnlazadaGenerica<T> datos = new ListaEnlazadaGenerica<T>();

	// Agrega elem a la cola.
	public void encolar(T elem) {
		datos.agregarFinal(elem);
	}

	// Elimina y devuelve el primer elemento de la cola.
	public T desencolar() {
		T aux = this.frente();
		boolean ok = datos.eliminarEn(1);
		return (ok ? aux : null);
	}

	// Devuelve el elemento en el frente de la cola sin eliminarlo.
	public T frente() {
		return datos.elemento(1);
	}

	// Retorna true si la cola está vacía, false en caso contrario.
	public boolean esVacia() {
		return datos.esVacia();
	}
}
