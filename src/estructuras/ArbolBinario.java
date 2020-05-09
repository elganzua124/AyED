package estructuras;

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

	// Ej.2 a) Devuelve la cantidad de árbol/subárbol hojas del árbol receptor.
	public int contarHojas() {
		return contarHojas(this);
	}

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

	// version InOrden
	public int contarHojasInOrden() {
		return contarHojasInOrden(this);
	}

	public int contarHojasInOrden(ArbolBinario<T> arbol) {

		return (arbol.getHijoIzquierdo().esVacio() ? 0 : arbol.getHijoIzquierdo().contarHojasInOrden())
				+ (arbol.esHoja() ? 1 : 0)
				+ (arbol.getHijoDerecho().esVacio() ? 0 : arbol.getHijoDerecho().contarHojasInOrden());
	}

	// Ej.2 b) Devuelve el árbol binario espejo del árbol receptor.
	public ArbolBinario<T> espejo() {
		return espejo(this);
	}

	private ArbolBinario<T> espejo(ArbolBinario<T> arbol) {
		ArbolBinario<T> espejo = new ArbolBinario<T>(arbol.getDatoRaiz());
		if (!arbol.getHijoIzquierdo().esVacio()) {
			if (!arbol.getHijoDerecho().esVacio()) {
				espejo.agregarHijoIzquierdo(arbol.getHijoDerecho().espejo());
				espejo.agregarHijoDerecho(arbol.getHijoIzquierdo().espejo());
			} else
				espejo.agregarHijoDerecho(arbol.getHijoIzquierdo().espejo());

		} else if (!arbol.getHijoDerecho().esVacio())
			espejo.agregarHijoIzquierdo(arbol.getHijoDerecho().espejo());

		return espejo;
	}

	/*
	 * Ej.2 c) Imprime el recorrido por niveles de los elementos del árbol receptor
	 * entre los niveles n y m (ambos inclusive). (0 <= n < m <= altura del árbol)
	 */

	public void entreNiveles(int n, int m) {
		if (this.altura() < m || m <= n || n < 0) {
			System.out.print("\"Valores de n y/o m inválidos.\"");
			return;
		}
		for (int i = n; i <= m; i++)
			imprimirNivel(i);
	}

	// https://stackoverflow.com/a/13350429
	private void arbolesEnNivel(int nivelActual, int nivel, ColaGenerica<ArbolBinario<T>> cola) {
		if (nivelActual == nivel)
			cola.encolar(this);
		else {
			if (!this.getHijoIzquierdo().esVacio())
				getHijoIzquierdo().arbolesEnNivel(nivelActual + 1, nivel, cola);
			if (!this.getHijoDerecho().esVacio())
				getHijoDerecho().arbolesEnNivel(nivelActual + 1, nivel, cola);
		}
	}

	public int altura() {
		int alt = 0;
		if (!this.getHijoIzquierdo().esVacio())
			alt = this.getHijoIzquierdo().altura() + 1;
		if (!this.getHijoDerecho().esVacio()) {
			int altDer = this.getHijoDerecho().altura() + 1;
			if (altDer > alt)
				alt = altDer;
		}
		return alt;
	}

	public void recorridoPorNiveles() {
		ArbolBinario<T> arbol = null;
		ColaGenerica<ArbolBinario<T>> cola = new ColaGenerica<ArbolBinario<T>>();
		cola.encolar(this);
		cola.encolar(null); // marca de fin nivel
		short i = 1;
		System.out.print("Nivel 0: ");
		while (!cola.esVacia()) {
			arbol = cola.desencolar();
			if (arbol != null) {
				System.out.print(arbol.getDatoRaiz() + " ");
				if (!arbol.getHijoIzquierdo().esVacio())
					cola.encolar(arbol.getHijoIzquierdo());
				if (!arbol.getHijoDerecho().esVacio())
					cola.encolar(arbol.getHijoDerecho());
			} else if (!cola.esVacia()) { // el null que recibimos es por cambio de nivel
				System.out.println("");
				System.out.print("Nivel " + i++ + ": ");
				cola.encolar(null); // agregamos la marca de fin del nivel proximo a evaluar
			}
		}
	}

	private void imprimirNivel(int n) {
		ColaGenerica<ArbolBinario<T>> cola = new ColaGenerica<ArbolBinario<T>>();

		arbolesEnNivel(0, n, cola);

		System.out.print("Nivel " + n + ": ");
		while (!cola.esVacia())
			System.out.print(cola.desencolar().getDatoRaiz() + " ");
		System.out.println("");
	}

	// ##### https://stackoverflow.com/a/27153988 ####

	/*
	 * Si en la consola de Eclipse aparecen ??? hay que: To change the text encoding
	 * globally, we can open Window -> Preferences -> General -> Workspace. Change
	 * the Text file encoding from default Cp1252 to UTF-8.
	 */

	@Override
	public String toString() {
		return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
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
}