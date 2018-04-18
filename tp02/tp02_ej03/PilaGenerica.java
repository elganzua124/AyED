package tp02_ej03;

import tp02_ejercicio2.ListaEnlazadaGenerica;

public class PilaGenerica<T> {

	private ListaEnlazadaGenerica<T> datos= new ListaEnlazadaGenerica<T>();

	// Agrega elem a la pila.
	public boolean apilar(T elem) {
		return datos.agregarEn(elem, 1);
	}

	// Elimina y devuelve el elemento en el tope de la pila.
	public T desapilar() {
		T aux = this.tope();
		boolean ok = datos.eliminar(this.tope());
		return (ok ? aux : null);
	}

	// Devuelve el elemento en el tope de la pila sin eliminarlo.
	public T tope() {
		return datos.elemento(1);
	}

	// Retorna true si la pila está vacía, false en caso contrario.
	public boolean esVacia() {
		return datos.esVacia();
	}
}
