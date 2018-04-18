package tp02_ej03;

import tp02_ejercicio2.ListaEnlazadaGenerica;

public class ColaGenerica<T> {

	private ListaEnlazadaGenerica<T> datos = new ListaEnlazadaGenerica<T>();

	// Agrega elem a la cola.
	public void encolar(T elem) {
		datos.agregarFinal(elem);
	}

	// Elimina y devuelve el primer elemento de la cola.
	public T desencolar() {
		T aux = datos.elemento(1);
		boolean ok = datos.eliminarEn(1);
		return (ok ? aux : null);
	}

	// Devuelve el elemento en el tope de la cola sin eliminarlo.
	public T tope() {
		return datos.elemento(datos.tamanio());
	}

	// Retorna true si la cola está vacía, false en caso contrario.
	public boolean esVacia() {
		return datos.esVacia();
	}
}
