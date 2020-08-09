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
		// Retorna la lista de ciudades que se deben atravesar para ir de ciudad1 a
		// ciudad2 en caso que se pueda llegar, si no retorna la lista vacía. (Sin tener
		// en cuenta el combustible).

		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();

		boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio() + 1];

		Vertice<String> v1 = buscarCiudad(ciudad1);

		if (v1 != null && buscarCiudad(ciudad2) != null)
			dfs(v1, ciudad2, marcas, camino);
		return camino;
	}

	private boolean dfs(Vertice<String> v1, String c2, boolean[] marcas, ListaGenerica<Vertice<String>> camino) {

		camino.agregarFinal(v1);
		if (v1.dato().equals(c2)) {
			return true;
		}

		marcas[v1.getPosicion()] = true;

		ListaGenerica<Arista<String>> ady = mapaCiudades.listaDeAdyacentes(v1);
		ady.comenzar();

		boolean encontre = false;
		while (!ady.fin() && !encontre) {
			Vertice<String> a = ady.proximo().verticeDestino();

			if (!marcas[a.getPosicion()])
				encontre = dfs(a, c2, marcas, camino);
		}

		marcas[v1.getPosicion()] = false;

		if (!encontre)
			camino.eliminarEn(camino.tamanio());
		return encontre;

	}

	public ListaGenerica<Vertice<String>> devolverCaminoExceptuando(String ciudad1, String ciudad2,
			ListaGenerica<String> ciudades) {
		// Retorna la lista de ciudades que forman un camino desde ciudad1 a ciudad2,
		// sin pasar por las ciudades que están contenidas en la lista ciudades pasada
		// por parámetro, si no existe camino retorna la lista vacía. (Sin tener en
		// cuenta el combustible).

		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
		boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio() + 1];

		Vertice<String> v1 = buscarCiudad(ciudad1);

		if (v1 != null && buscarCiudad(ciudad2) != null)
			dfsExceptuando(v1, ciudad2, marcas, camino, ciudades);
		return camino;

	}

	private boolean dfsExceptuando(Vertice<String> vertice, String c2, boolean[] marcas,
			ListaGenerica<Vertice<String>> camino, ListaGenerica<String> ciudades) {

		camino.agregarFinal(vertice);
		if (vertice.dato().equals(c2))
			return true;
		boolean encontre = false;
		marcas[vertice.getPosicion()] = true;
		ListaGenerica<Arista<String>> ady = mapaCiudades.listaDeAdyacentes(vertice);
		ady.comenzar();
		while (!ady.fin() && !encontre) {
			Vertice<String> v = ady.proximo().verticeDestino();

			if (!marcas[v.getPosicion()] && !ciudades.incluye(v.dato()))
				encontre = dfsExceptuando(v, c2, marcas, camino, ciudades);
		}
		marcas[vertice.getPosicion()] = false;
		if (!encontre)
			camino.eliminarEn(camino.tamanio());

		return encontre;
	}

	public ListaGenerica<Vertice<String>> caminoMasCorto(String ciudad1, String ciudad2) {

		// Retorna la lista de ciudades que forman el camino más corto para llegar de
		// ciudad1 a ciudad2, si no existe camino retorna la lista vacía. (Las rutas
		// poseen la distancia). (Sin tener en cuenta el combustible).

		ListaGenerica<Vertice<String>> lista = new ListaEnlazadaGenerica<Vertice<String>>();
		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
		boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio() + 1];

		int distancia = 0;
		int[] min = { Integer.MAX_VALUE };

		Vertice<String> v1 = buscarCiudad(ciudad1);

		if (v1 != null && buscarCiudad(ciudad2) != null)
			dfsMasCorto(v1, ciudad2, marcas, lista, camino, distancia, min);
		return lista;
	}

	private void dfsMasCorto(Vertice<String> v, String c2, boolean[] marcas, ListaGenerica<Vertice<String>> lista,
			ListaGenerica<Vertice<String>> camino, int distancia, int[] min) {

		camino.agregarFinal(v);
		if (v.dato().equals(c2)) {
			if (min[0] > distancia) {
				clonar(lista, camino);
				min[0] = distancia;
			}
		} else {
			marcas[v.getPosicion()] = true;
			ListaGenerica<Arista<String>> adyacentes = mapaCiudades.listaDeAdyacentes(v);
			adyacentes.comenzar();
			while (!adyacentes.fin()) {
				Arista<String> arista = adyacentes.proximo();
				if (!marcas[arista.verticeDestino().getPosicion()]) {
					int d = distancia + arista.peso();
					dfsMasCorto(arista.verticeDestino(), c2, marcas, lista, camino, d, min);
				}
			}
			marcas[v.getPosicion()] = false;
		}
		camino.eliminarEn(camino.tamanio());
	}

	public ListaGenerica<Vertice<String>> caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto) {

		// Retorna la lista de ciudades que forman un camino para llegar de ciudad1 a
		// ciudad2. El auto no debe quedarse sin combustible y no puede cargar. Si no
		// existe camino retorna la lista vacía.

		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
		boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio() + 1];

		Vertice<String> v1 = buscarCiudad(ciudad1);

		if (v1 != null && buscarCiudad(ciudad2) != null)
			dfsSinCargarCombustible(v1, ciudad2, marcas, camino, tanqueAuto);

		return camino;

	}

	private boolean dfsSinCargarCombustible(Vertice<String> vertice, String c2, boolean[] marcas,
			ListaGenerica<Vertice<String>> camino, int tanqueAuto) {

		camino.agregarFinal(vertice);
		if (vertice.dato().equals(c2)) {
			return true;
		}
		marcas[vertice.getPosicion()] = true;
		ListaGenerica<Arista<String>> ady = mapaCiudades.listaDeAdyacentes(vertice);
		ady.comenzar();
		boolean llego = false;
		while (!ady.fin() && !llego) {
			Arista<String> a = ady.proximo();
			Vertice<String> v = a.verticeDestino();
			if (!marcas[v.getPosicion()] && a.peso() <= tanqueAuto)
				llego = dfsSinCargarCombustible(v, c2, marcas, camino, tanqueAuto - a.peso());
		}
		marcas[vertice.getPosicion()] = false;
		if (!llego)
			camino.eliminarEn(camino.tamanio());
		return llego;

	}

	public ListaGenerica<Vertice<String>> caminoConMenorCargaDeCombustible(String ciudad1, String ciudad2,
			int tanqueAuto) {
		// Retorna la lista de ciudades que forman un camino para llegar de ciudad1 a
		// ciudad2 teniendo en cuenta que el auto debe cargar la menor cantidad de
		// veces. El auto no se debe quedar sin combustible en medio de una ruta, además
		// puede completar su tanque al llegar a cualquier ciudad. Si no existe camino
		// retorna la lista vacía.
		ListaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
		ListaGenerica<Vertice<String>> mejorCamino = new ListaEnlazadaGenerica<Vertice<String>>();
		boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio() + 1];

		int[] menorCarga = { 2 }; // con 2 alcanza
		Vertice<String> v1 = buscarCiudad(ciudad1);

		if (v1 != null && buscarCiudad(ciudad2) != null)
			dfsConMenorCargaDeCombustible(v1, ciudad2, marcas, camino, mejorCamino, tanqueAuto, tanqueAuto, 1,
					menorCarga);

		return mejorCamino;

	}

	private void dfsConMenorCargaDeCombustible(Vertice<String> vertice, String c2, boolean[] marcas,
			ListaGenerica<Vertice<String>> caminoActual, ListaGenerica<Vertice<String>> mejorCamino, int tanqueLleno,
			int tanqueActual, int cargas, int[] menorCarga) {

		caminoActual.agregarFinal(vertice);

		if (vertice.dato().equals(c2)) {
			if (cargas < menorCarga[0]) {
				menorCarga[0] = cargas;
				clonar(caminoActual, mejorCamino);
			}
		} else {
			marcas[vertice.getPosicion()] = true;
			ListaGenerica<Arista<String>> ady = mapaCiudades.listaDeAdyacentes(vertice);
			ady.comenzar();
			while (!ady.fin()) {
				Arista<String> a = ady.proximo();
				Vertice<String> v = a.verticeDestino();
				if (!marcas[v.getPosicion()] && (a.peso() <= tanqueLleno)) {

					if (tanqueActual < a.peso()) {

						dfsConMenorCargaDeCombustible(v, c2, marcas, caminoActual, mejorCamino, tanqueLleno,
								tanqueLleno - a.peso(), cargas + 1, menorCarga);
					} else

						dfsConMenorCargaDeCombustible(v, c2, marcas, caminoActual, mejorCamino, tanqueLleno,
								tanqueActual - a.peso(), cargas, menorCarga);
				}
			}
			marcas[vertice.getPosicion()] = false;
		}

		caminoActual.eliminarEn(caminoActual.tamanio());
	}

	private <T> void clonar(ListaGenerica<T> caminoOrigen, ListaGenerica<T> caminoDestino) {
		while (!caminoDestino.esVacia())
			caminoDestino.eliminarEn(1);

		caminoOrigen.comenzar();
		while (!caminoOrigen.fin()) {
			caminoDestino.agregarFinal(caminoOrigen.proximo());
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
		
		if (vertices.fin())
			return null;
		
		return v;
	}

}