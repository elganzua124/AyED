package tp03_ej01;

import tp02_ej02.*;

public class ArbolBinario<T> {

	private NodoBinario<T> raiz;

	public ArbolBinario(T dato) {
		this.raiz = new NodoBinario<T>(dato);
	}

	private ArbolBinario(NodoBinario<T> nodo) {
		this.raiz = nodo;
	}

	private NodoBinario<T> getRaiz() {
		return raiz;
	}

	public T getDatoRaiz() {
		if (this.getRaiz() != null) {
			return this.getRaiz().getDato();
		} else {
			return null;
		}
	}

	public ArbolBinario<T> getHijoIzquierdo() {
		return new ArbolBinario<T>(this.raiz.getHijoIzquierdo());
	}

	public ArbolBinario<T> getHijoDerecho() {
		return new ArbolBinario<T>(this.raiz.getHijoDerecho());
	}

	public void agregarHijoIzquierdo(ArbolBinario<T> hijo) {
		this.raiz.setHijoIzquierdo(hijo.getRaiz());
	}

	public void agregarHijoDerecho(ArbolBinario<T> hijo) {
		this.raiz.setHijoDerecho(hijo.getRaiz());
	}

	public void eliminarHijoIzquierdo() {
		this.raiz.setHijoIzquierdo(null);
	}

	public void eliminarHijoDerecho() {
		this.raiz.setHijoDerecho(null);
	}

	public boolean esVacio() {
		return this.getDatoRaiz() == null;
	}

	public boolean esHoja() {
		return this.getDatoRaiz() != null && this.getHijoIzquierdo().esVacio() && this.getHijoDerecho().esVacio();
	}

	public void imprimirPostOrden() {
		imprimirPostOrden(this);
	}

	private void imprimirPostOrden(ArbolBinario<T> arbol) {
		if (!arbol.getHijoIzquierdo().esVacio()) {
			imprimirPostOrden(arbol.getHijoIzquierdo());
		}
		if (!arbol.getHijoDerecho().esVacio()) {
			imprimirPostOrden(arbol.getHijoDerecho());
		}
		if (arbol.getHijoIzquierdo().esVacio() || arbol.getHijoDerecho().esVacio())
			System.out.println("soy hoja");
		if (arbol.getDatoRaiz() != null) {
			System.out.println(arbol.getDatoRaiz());
		}
	}

	public int contarHojas() {
		return contarHojas(this);
	}

	// Devuelve la cantidad de hojas del árbol receptor.
	private int contarHojas(ArbolBinario<T> arbol) {
		if (this.esHoja())
			return 1;
		int hojas = 0;
		if (!arbol.getHijoIzquierdo().esVacio())
			hojas = arbol.getHijoIzquierdo().contarHojas();
		if (!arbol.getHijoDerecho().esVacio())
			hojas = hojas + arbol.getHijoDerecho().contarHojas();
		return hojas;
	}

	public ArbolBinario<T> espejo() {
		return espejo(this);
	}

	private ArbolBinario<T> espejo(ArbolBinario<T> arbol) {

		ArbolBinario<T> oldIzq = arbol.getHijoIzquierdo();

		if (!arbol.getHijoIzquierdo().esVacio()) {
			if (!arbol.getHijoDerecho().esVacio()) {
				arbol.agregarHijoIzquierdo(arbol.getHijoDerecho().espejo());
			} else
				arbol.eliminarHijoIzquierdo();
		}
		if (!arbol.getHijoDerecho().esVacio()) {
			if (oldIzq.getRaiz() != null)
				arbol.agregarHijoDerecho(oldIzq.espejo());
			else {
				arbol.agregarHijoIzquierdo(arbol.getHijoDerecho().espejo());
				arbol.eliminarHijoDerecho();
			}
		} else if (oldIzq.getRaiz() != null)
			arbol.agregarHijoDerecho(oldIzq.espejo());

		return arbol;
	}

	private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
		if (!this.getHijoDerecho().esVacio()) {
			this.getHijoDerecho().toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false,
					sb);
		}
		sb.append(prefix).append(isTail ? "└── " : "┌── ").append(this.getRaiz().getDato().toString()).append("\n");
		if (!this.getHijoIzquierdo().esVacio()) {
			this.getHijoIzquierdo().toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true,
					sb);
		}
		return sb;
	}

	@Override
	public String toString() {
		return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
	}

}