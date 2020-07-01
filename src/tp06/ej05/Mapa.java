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

		ListaGenerica<Vertice<String>> lista = new ListaEnlazadaGenerica<Vertice<String>>();

		boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio() + 1];

		Vertice<String> v1 = buscarCiudad(ciudad1);

		if (v1 != null && buscarCiudad(ciudad2) != null)
			dfs(v1, ciudad2, marcas, lista);
		return lista;
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

	private void dfs(Vertice<String> v1, String c2, boolean[] marcas, ListaGenerica<Vertice<String>> lista) {
		marcas[v1.getPosicion()] = true;

		lista.agregarFinal(v1);
		if (v1.dato().equals(c2)) {
			return;
		}
		ListaGenerica<Arista<String>> ady = mapaCiudades.listaDeAdyacentes(v1);
		Arista<String> a;
		while (!ady.fin()) {
			a = ady.proximo();
			int j = a.verticeDestino().getPosicion();

			if (!marcas[j])
				dfs(a.verticeDestino(), c2, marcas, lista);
		}

	}

	public ListaGenerica<Vertice<String>> devolverCaminoExceptuando(String ciudad1, String ciudad2,
			ListaGenerica<String> ciudades) {
		ListaGenerica<Vertice<String>> lista = new ListaEnlazadaGenerica<Vertice<String>>();
		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
		boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio()];

		Vertice<String> v1 = buscarCiudad(ciudad1);

		if (v1 != null && buscarCiudad(ciudad2) != null)
			dfs(v1, ciudad2, marcas, lista, camino, ciudades);
		return lista;

	}

	private void dfs(Vertice<String> v, String c2, boolean[] marcas, ListaGenerica<Vertice<String>> lista,
			ListaGenerica<Vertice<String>> camino, ListaGenerica<String> ciudades) {
		marcas[v.getPosicion()] = true;

		camino.agregarFinal(v);
		if (v.dato().equals(c2)) {
			lista = camino.clonar();
		} else {
			ListaGenerica<Arista<String>> listaDeAdyacentes = mapaCiudades.listaDeAdyacentes(v);
			Arista<String> arista;
			while (!listaDeAdyacentes.fin()) {
				arista = listaDeAdyacentes.proximo();
				int j = arista.verticeDestino().getPosicion();

				if (!marcas[j] && !ciudades.incluye(arista.verticeDestino().dato())) {
					dfs(arista.verticeDestino(), c2, marcas, lista, camino, ciudades);
					marcas[j] = false;
					camino.eliminarEn(j);
				}
			}
		}
	}

	public ListaGenerica<Vertice<String>> caminoMasCorto(String ciudad1, String ciudad2) {

		ListaGenerica<Vertice<String>> lista = new ListaEnlazadaGenerica<Vertice<String>>();
		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
		boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio()];

		int distancia = 0;
		Integer min = Integer.MAX_VALUE;

		Vertice<String> v1 = buscarCiudad(ciudad1);

		if (v1 != null && buscarCiudad(ciudad2) != null)
			dfsMasCorto(v1, ciudad2, marcas, lista, camino, distancia, min);
		return lista;
	}

	private void dfsMasCorto(Vertice<String> v, String c2, boolean[] marcas, ListaGenerica<Vertice<String>> lista,
			ListaGenerica<Vertice<String>> camino, int distancia, Integer min) {
		marcas[v.getPosicion()] = true;
		if (v.dato().equals(c2)) {
			if (min > distancia) {
				lista = camino.clonar();
				min = distancia;
			}
		} else {
			ListaGenerica<Arista<String>> ady = mapaCiudades.listaDeAdyacentes(v);
			Arista<String> arista;
			while (!ady.fin()) {
				arista = ady.proximo();
				int j = arista.verticeDestino().getPosicion();
				if (!marcas[j]) {
					distancia += arista.peso();
					dfsMasCorto(arista.verticeDestino(), c2, marcas, lista, camino, distancia, min);
					marcas[j] = false;
					camino.eliminarEn(j);
				}
			}
		}
	}

}