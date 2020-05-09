package estructuras;

public class ArbolGeneral<T> {

	private NodoGeneral<T> raiz;

	public ArbolGeneral() {

		this.raiz = null;
	}

	public ArbolGeneral(T dato) {

		this.raiz = new NodoGeneral<T>(dato);

	}

	public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> lista) {

		this(dato);
		ListaGenerica<NodoGeneral<T>> hijos = new ListaEnlazadaGenerica<NodoGeneral<T>>();

		lista.comenzar();
		while (!lista.fin()) {
			ArbolGeneral<T> arbolTemp = lista.proximo();
			hijos.agregarFinal(arbolTemp.getRaiz());

		}

		raiz.setListaHijos(hijos);
	}

	private ArbolGeneral(NodoGeneral<T> nodo) {

		this.raiz = nodo;
	}

	private NodoGeneral<T> getRaiz() {

		return this.raiz;
	}

	public T getDatoRaiz() {
		if (this.getRaiz() != null) {
			return this.getRaiz().getDato();
		} else {
			return null;
		}
	}

	public ListaGenerica<ArbolGeneral<T>> getHijos() {

		ListaGenerica<ArbolGeneral<T>> lista = new ListaEnlazadaGenerica<ArbolGeneral<T>>();

		if (this.esVacio()) // hay que chekar porque this.getRaiz().getHijos() puede romper
			return lista;
		ListaGenerica<NodoGeneral<T>> hijos = (ListaGenerica<NodoGeneral<T>>) this.getRaiz().getHijos();
		hijos.comenzar();

		while (!hijos.fin()) {
			lista.agregarFinal(new ArbolGeneral<T>(hijos.proximo()));

		}
		return lista;
	}

	public void agregarHijo(ArbolGeneral<T> unHijo) {

		NodoGeneral<T> hijo = unHijo.getRaiz();
		this.raiz.getHijos().agregarFinal(hijo);
	}

	public void eliminarHijo(ArbolGeneral<T> unHijo) {

		NodoGeneral<T> hijo = unHijo.getRaiz();

		ListaGenerica<NodoGeneral<T>> listaHijos = (ListaGenerica<NodoGeneral<T>>) this.getRaiz().getHijos();
		listaHijos.comenzar();

		while (!listaHijos.fin()) {

			NodoGeneral<T> hijoTemp = listaHijos.proximo();
			if (hijoTemp.getDato().equals(hijo.getDato())) {
				listaHijos.eliminar(hijoTemp);
				break;

			}
		}
	}

	public Integer altura() {

		ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
		int max = 0, alt = 0;
		hijos.comenzar();
		while (!hijos.fin()) {
			alt = hijos.proximo().altura() + 1;
			if (alt > max)
				max = alt;
		}
		return max;
	}

	private Integer nivel(T dato, int nivel) {

		if (this.esVacio())
			return -1;
		if (this.getDatoRaiz() == dato) {
			return nivel;
		}
		ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
		hijos.comenzar();
		int max = -1;
		while (max == -1 && !hijos.fin()) {
			ArbolGeneral<T> arbol = hijos.proximo();
			max = arbol.nivel(dato, nivel + 1);
		}
		return max;
	}

	public Integer nivel(T dato) {
		// devuelve la profundidad o nivel del dato en el árbol. El nivel de
		// un nodo es la longitud del único camino de la raíz al nodo.
		return nivel(dato, 0);
	}

	public Integer ancho() {

		ArbolGeneral<T> arbol = new ArbolGeneral<T>();
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		int max = 0, nodos = 0;
		cola.encolar(this);
		cola.encolar(null);
		while (!cola.esVacia()) {
			arbol = cola.desencolar();
			if (arbol != null) {
				ListaGenerica<ArbolGeneral<T>> hijos = arbol.getHijos();
				hijos.comenzar();
				while (!hijos.fin()) {
					cola.encolar(hijos.proximo());
					nodos++;
				}
			} else {
				if (nodos > max)
					max = nodos;
				nodos = 0;
				cola.encolar(null);
			}
		}
		return max;
	}

	public Boolean esHoja() {
		return !this.esVacio() && this.getRaiz().getHijos().esVacia();
	}

	public Boolean esVacio() {
		return this.getDatoRaiz() == null;
	}

	public ListaEnlazadaGenerica<T> recorridoPorNiveles() {

		// Método que retorna una lista con los elementos del Árbol receptor,
		// en orden de recorrido por niveles de arriba hacia abajo y de
		// izquierda a derecha, incluyendo algún elemento que indique
		// el fin de cada nivel

		ArbolGeneral<T> arbol = new ArbolGeneral<T>();
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		ListaGenerica<T> lista = new ListaEnlazadaGenerica<T>();

		cola.encolar(this);
		cola.encolar(null);
		while (!cola.esVacia()) {
			arbol = cola.desencolar();
			if (arbol != null) {
				ListaGenerica<ArbolGeneral<T>> hijos = arbol.getHijos();
				hijos.comenzar();
				while (!hijos.fin()) {
					ArbolGeneral<T> aux = hijos.proximo();
					cola.encolar(aux);
					lista.agregarFinal(aux.getDatoRaiz());
				}
			} else {
				cola.encolar(null);
				lista.agregarFinal(null);
			}
		}
		return (ListaEnlazadaGenerica<T>) lista;
	}

}
