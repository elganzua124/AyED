package parciales;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.Vertice;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

/*
 * Se tiene un grafo que representa el mapa de un juego de búsqueda del tesoro.
 * Cada vértice representa un escondite, estos poseen una o varias pistas que
 * llevan a los escondites siguientes, las aristas representan las pistas. Hay
 * escondites terminales, que no tienen pistas y que contienen tesoros (no
 * tienen adyacentes).
 * De cada escondite se tiene un nombre que lo identifica, la complejidad de
 * poder abrirlo (Alta, Media, Baja) y la profundidad que está enterrado en
 * centímetros. El mapa parte de un vértice distinguido que es el único que
 * está a profundidad cero.
 * Implemente la clase Parcial y el método:
 * 
 *  ListaGenerica<???> resolver(Grafo<???> mapa)
 *  
 *  El método recibe como parámetro un mapa y retorna el camino cuyo promedio
 *  de profundidad de los escondites sea mayor.
 *  
 */

public class Parcial3 {

	private Grafo<Escondite> mapa;

	private class Escondite {
		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getComplejidad() {
			return complejidad;
		}

		public void setComplejidad(String complejidad) {
			this.complejidad = complejidad;
		}

		public int getProfundidad() {
			return profundidad;
		}

		public void setProfundidad(int profundidad) {
			this.profundidad = profundidad;
		}

		private String nombre;
		private String complejidad;
		private int profundidad;
	}

	public ListaGenerica<Escondite> resolver(Grafo<Escondite> mapa) {

		this.mapa = mapa;
		ListaGenerica<Escondite> camino = new ListaEnlazadaGenerica<Escondite>();

		Vertice<Escondite> v = buscarPartidaCero();
		if (v != null) {
			ListaGenerica<Escondite> caminoActual = new ListaEnlazadaGenerica<Escondite>();

			boolean[] marcas = new boolean[mapa.listaDeVertices().tamanio() + 1];

			float[] promedio = { 0 };

			dfs(v, marcas, caminoActual, camino, 0, promedio);
		}
		return camino;

	}

	private void dfs(Vertice<Escondite> v, boolean[] marcas, ListaGenerica<Escondite> caminoActual,
			ListaGenerica<Escondite> camino, int prof, float[] prom) {

		caminoActual.agregarFinal(v.dato());

		prof += v.dato().getProfundidad();

		if (mapa.listaDeAdyacentes(v).esVacia()) {
			float p = prof / caminoActual.tamanio();
			if (p > prom[0]) {
				prom[0] = p;
				clonar(caminoActual, camino);
			}

		} else {
			marcas[v.getPosicion()] = true;

			ListaGenerica<Arista<Escondite>> adyacentes = mapa.listaDeAdyacentes(v);
			adyacentes.comenzar();
			while (!adyacentes.fin()) {

				Vertice<Escondite> vertice = adyacentes.proximo().verticeDestino();

				if (!marcas[vertice.getPosicion()])
					dfs(vertice, marcas, caminoActual, camino, prof, prom);

			}
			marcas[v.getPosicion()] = false;
		}

		caminoActual.eliminarEn(v.getPosicion());

	}

	private Vertice<Escondite> buscarPartidaCero() {
		ListaGenerica<Vertice<Escondite>> vertices = mapa.listaDeVertices();
		Vertice<Escondite> v = null;
		vertices.comenzar();
		while (!vertices.fin()) {
			v = vertices.proximo();
			if (v.dato().getProfundidad() == 0)
				return v;
		}
		return v;

	}

	private <T> void clonar(ListaGenerica<T> caminoOrigen, ListaGenerica<T> caminoDestino) {
		while (!caminoDestino.esVacia())
			caminoDestino.eliminarEn(1);

		caminoOrigen.comenzar();
		while (!caminoOrigen.fin()) {
			caminoDestino.agregarFinal(caminoOrigen.proximo());
		}

	}

}
