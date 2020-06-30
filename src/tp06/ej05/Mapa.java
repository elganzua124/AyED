package tp06.ej05;

import estructuras.grafo.*;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

public class Mapa {
	private Grafo<String> mapaCiudad;

	public ListaGenerica<Vertice<String>> devolverCamino(String ciudad1, String ciudad2) {
		ListaGenerica<Vertice<String>> listaDeVertices = mapaCiudad.listaDeVertices();
		ListaGenerica<Vertice<String>> lista = new ListaEnlazadaGenerica<Vertice<String>>();
		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
		boolean[] marcas = new boolean[listaDeVertices.tamanio()];
		boolean[] query = new boolean[2];
		int[] pos = new int[2];
		for (int i = 0; i < listaDeVertices.tamanio() && !(query[0] && query[1]); i++) {
			if (listaDeVertices.elemento(i).dato().equals(ciudad1)) {
				query[0] = true;
				pos[0] = i;
			}
			if (listaDeVertices.elemento(i).dato().equals(ciudad2)) {
				query[1] = true;
				pos[1] = i;
			}
		}
		dfs(pos, marcas, lista, camino);
		return lista;
	}

	private void dfs(int[] pos, boolean[] marcas, ListaGenerica<Vertice<String>> lista,
			ListaGenerica<Vertice<String>> camino) {
		marcas[pos[0]] = true;
		Vertice<String> v = mapaCiudad.listaDeVertices().elemento(pos[0]);
		camino.agregarFinal(v);
		if (pos[0] == pos[1]) {
			lista = camino.clonar();
		} else {
			ListaGenerica<Arista<String>> ady = mapaCiudad.listaDeAdyacentes(v);
			Arista<String> arista;
			while (!ady.fin()) {
				arista = ady.proximo();
				int j = arista.verticeDestino().getPosicion();
				pos[0] = j;
				if (!marcas[pos[0]]) {
					dfs(pos, marcas, lista, camino);
					marcas[j] = false;
					camino.eliminarEn(j);
				}
			}
		}
	}

	public ListaGenerica<Vertice<String>> devolverCaminoExceptuando(String ciudad1, String ciudad2,
			ListaGenerica<String> ciudades) {
		ListaGenerica<Vertice<String>> lista = new ListaEnlazadaGenerica<Vertice<String>>();
		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
		boolean[] marcas = new boolean[mapaCiudad.listaDeVertices().tamanio()];
		boolean[] query = new boolean[2];
		int[] pos = new int[2];
		for (int i = 0; i < mapaCiudad.listaDeVertices().tamanio() && !(query[0] && query[1]); i++) {
			if (mapaCiudad.listaDeVertices().elemento(i).dato().equals(ciudad1)) {
				query[0] = true;
				pos[0] = i;
			}
			if (mapaCiudad.listaDeVertices().elemento(i).dato().equals(ciudad2)) {
				query[1] = true;
				pos[1] = i;
			}
		}
		dfs(pos, marcas, lista, camino, ciudades);
		return lista;
	}

	private void dfs(int[] pos, boolean[] marcas, ListaGenerica<Vertice<String>> lista,
			ListaGenerica<Vertice<String>> camino, ListaGenerica<String> listaCiudades) {
		marcas[pos[0]] = true;
		Vertice<String> v = mapaCiudad.listaDeVertices().elemento(pos[0]);
		camino.agregarFinal(v);
		if (pos[0] == pos[1]) {
			lista = camino.clonar();
		} else {
			ListaGenerica<Arista<String>> listaDeAdyacentes = mapaCiudad.listaDeAdyacentes(v);
			Arista<String> arista;
			while (!listaDeAdyacentes.fin()) {
				arista = listaDeAdyacentes.proximo();
				int j = arista.verticeDestino().getPosicion();
				pos[0] = j;
				if (!marcas[pos[0]] && !listaCiudades.incluye(mapaCiudad.listaDeVertices().elemento(pos[0]).dato())) {
					dfs(pos, marcas, lista, camino);
					marcas[j] = false;
					camino.eliminarEn(j);
				}
			}
		}
	}

	public ListaGenerica<Vertice<String>> caminoMasCorto(String ciudad1, String ciudad2) {

		ListaGenerica<Vertice<String>> lista = new ListaEnlazadaGenerica<Vertice<String>>();
		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
		boolean[] marcas = new boolean[mapaCiudad.listaDeVertices().tamanio()];
		boolean[] query = new boolean[2];
		int[] pos = new int[2];
		int distancia = 0;
		Integer min = 99999;
		for (int i = 0; i < mapaCiudad.listaDeVertices().tamanio() && !(query[0] && query[1]); i++) {
			if (mapaCiudad.listaDeVertices().elemento(i).dato().equals(ciudad1)) {
				query[0] = true;
				pos[0] = i;
			}
			if (mapaCiudad.listaDeVertices().elemento(i).dato().equals(ciudad2)) {
				query[1] = true;
				pos[1] = i;
			}
		}
		dfsMasCorto(pos, marcas, lista, camino, distancia, min);
		return lista;
	}

	private void dfsMasCorto(int[] pos, boolean[] marcas, ListaGenerica<Vertice<String>> lista,
			ListaGenerica<Vertice<String>> camino, int distancia, Integer min) {
		marcas[pos[0]] = true;
		if (pos[0] == pos[1]) {
			if (min > distancia) {
				lista = camino.clonar();
				min = distancia;
			}
		} else {
			ListaGenerica<Arista<String>> listaDeAdyacentes = ((VerticeImplListAdy<String>) mapaCiudad.listaDeVertices()
					.elemento(pos[0])).obtenerAdyacentes();
			Arista<String> arista;
			while (!listaDeAdyacentes.fin()) {
				arista = listaDeAdyacentes.proximo();
				int j = arista.verticeDestino().getPosicion();
				pos[0] = j;
				if (!marcas[pos[0]]) {
					distancia += arista.peso();
					dfs(pos, marcas, lista, camino);
					marcas[j] = false;
					camino.eliminarEn(j);
				}
			}
		}
	}

}