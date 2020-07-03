package tp06.ej05;

import estructuras.grafo.*;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

public class Mapa {
	private Grafo<String> mapaCiudades;

	public Mapa(Grafo<String> mapaCiudades) {
		super();
		this.mapaCiudades = mapaCiudades;
	}

	public ListaGenerica<Vertice<String>> devolverCamino(String ciudad1, String ciudad2) {

		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();

		boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio() + 1];

		Vertice<String> v1 = buscarCiudad(ciudad1);

		if (v1 != null && buscarCiudad(ciudad2) != null)
			dfs(v1, ciudad2, marcas, camino);
		return camino;
	}

	private boolean dfs(Vertice<String> v1, String c2, boolean[] marcas, ListaGenerica<Vertice<String>> camino) {
		marcas[v1.getPosicion()] = true;

		camino.agregarFinal(v1);
		if (v1.dato().equals(c2)) {
			return true;
		}
		ListaGenerica<Arista<String>> ady = mapaCiudades.listaDeAdyacentes(v1);
		boolean encontre = false;
		while (!ady.fin() && !encontre) {
			Vertice<String> a = ady.proximo().verticeDestino();

			if (!marcas[a.getPosicion()])
				encontre = dfs(a, c2, marcas, camino);
		}
		if (!encontre)
			camino.eliminarEn(camino.tamanio());
		return encontre;

	}

	public ListaGenerica<Vertice<String>> devolverCaminoExceptuando(String ciudad1, String ciudad2,
			ListaGenerica<String> ciudades) {

		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
		boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio()];

		Vertice<String> v1 = buscarCiudad(ciudad1);

		if (v1 != null && buscarCiudad(ciudad2) != null)
			dfsExceptuando(v1, ciudad2, marcas, camino, ciudades);
		return camino;

	}

	private boolean dfsExceptuando(Vertice<String> v, String c2, boolean[] marcas,
			ListaGenerica<Vertice<String>> camino, ListaGenerica<String> ciudades) {
		marcas[v.getPosicion()] = true;
		camino.agregarFinal(v);
		if (v.dato().equals(c2))
			return true;
		boolean encontre = false;
		ListaGenerica<Arista<String>> adyacentes = mapaCiudades.listaDeAdyacentes(v);
		adyacentes.comenzar();
		while (!adyacentes.fin() && !encontre) {
			Vertice<String> ady = adyacentes.proximo().verticeDestino();

			if (!marcas[ady.getPosicion()] && !ciudades.incluye(ady.dato()))
				encontre = dfsExceptuando(ady, c2, marcas, camino, ciudades);
		}

		if (!encontre) {
			marcas[v.getPosicion()] = false; // es necesario?
			camino.eliminarEn(camino.tamanio());
		}

		return encontre;
	}

	public ListaGenerica<Vertice<String>> caminoMasCorto(String ciudad1, String ciudad2) {

		ListaGenerica<Vertice<String>> lista = new ListaEnlazadaGenerica<Vertice<String>>();
		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
		boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio()];

		int distancia = 0;
		int[] min = { Integer.MAX_VALUE };

		Vertice<String> v1 = buscarCiudad(ciudad1);

		if (v1 != null && buscarCiudad(ciudad2) != null)
			dfsMasCorto(v1, ciudad2, marcas, lista, camino, distancia, min);
		return lista;
	}

	private void dfsMasCorto(Vertice<String> v, String c2, boolean[] marcas, ListaGenerica<Vertice<String>> lista,
			ListaGenerica<Vertice<String>> camino, int distancia, int[] min) {
		marcas[v.getPosicion()] = true;
		camino.agregarFinal(v);
		if (v.dato().equals(c2)) {
			if (min[0] > distancia) {
				clonar(lista, camino);
				min[0] = distancia;
			}
		} else {
			ListaGenerica<Arista<String>> adyacentes = mapaCiudades.listaDeAdyacentes(v);
			adyacentes.comenzar();
			while (!adyacentes.fin()) {
				Arista<String> arista = adyacentes.proximo();
				if (!marcas[arista.verticeDestino().getPosicion()]) {
					distancia += arista.peso();
					dfsMasCorto(arista.verticeDestino(), c2, marcas, lista, camino, distancia, min);

				}

			}
		}
		marcas[v.getPosicion()] = false;
		camino.eliminarEn(lista.tamanio());
	}

	public ListaGenerica<Vertice<String>> caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto) {
		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
		boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio()];

		Vertice<String> v1 = buscarCiudad(ciudad1);

		if (v1 != null && buscarCiudad(ciudad2) != null)
			dfsSinCargarCombustible(v1, ciudad2, marcas, camino, tanqueAuto);

		return camino;

	}

	private boolean dfsSinCargarCombustible(Vertice<String> v1, String c2, boolean[] marcas,
			ListaGenerica<Vertice<String>> camino, int tanqueAuto) {
		marcas[v1.getPosicion()] = true;

		camino.agregarFinal(v1);
		if (v1.dato().equals(c2)) {
			return true;
		}
		ListaGenerica<Arista<String>> ady = mapaCiudades.listaDeAdyacentes(v1);
		boolean llego = false;
		while (!ady.fin() && !llego) {
			Arista<String> a = ady.proximo();

			if (a.peso() <= tanqueAuto && !marcas[a.verticeDestino().getPosicion()])
				llego = dfsSinCargarCombustible(a.verticeDestino(), c2, marcas, camino, tanqueAuto - a.peso());
		}
		if (!llego)
			camino.eliminarEn(camino.tamanio());
		return llego;

	}

	private <T> void clonar(ListaGenerica<T> camino, ListaGenerica<T> caminoMin) {
		while (!caminoMin.esVacia())
			caminoMin.eliminarEn(1);

		camino.comenzar();
		while (!camino.fin()) {
			caminoMin.agregarFinal(camino.proximo());
		}

	}

	private Vertice<String> buscarCiudad(String ciudad) {
		ListaGenerica<Vertice<String>> vertices = mapaCiudades.listaDeVertices();
		Vertice<String> v = null;
		vertices.comenzar();
		while (!vertices.fin()) {
			v = vertices.proximo();
			if (v.dato().equals(ciudad))
				return v;
		}
		return v;
	}

}