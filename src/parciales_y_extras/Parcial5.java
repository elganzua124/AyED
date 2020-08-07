package parciales_y_extras;

import estructuras.ColaGenerica;
import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.GrafoImplListAdy;
import estructuras.grafo.Vertice;
import estructuras.grafo.VerticeImplListAdy;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

/*
 * Implemente la clase parcial, y el método:
 * 
 * ??? resolver(Grafo<???> ciudades, String origen, Integer radio, Lista<???> evitarCiudadesContiguas)
 *
 * Se quiere retornar todas las ciudades que se encuentran a una cierta
 * distancia "radio" de una ciudad "origen" excluyendo las que son adyacentes
 * a alguna de las indicadas en "evitarCiudadesContiguas". Para cada ciudad
 * se conoce el nombre.
 *
 *
 * En este ejemplo, considerando "origen" a Moreno, y "evitarCiudadesContiguas"
 * a La Plata y Lobos, considerando además un radio 3.
 * 
 * - Se debe retornar: Moreno (es el origen), Carlos Keen, Suipacha, Cañuelas.
 * - No se deben retornar: Abasto (porque es adyacente a La Plata), Quilmes
 *   (porque es adyacente a La Plata), La Plata (porque se debe evitar), Navarro
 *   (porque es adyacente a Lobos), Pinamar (porque es adyacente a La Plata),
 *   Lobos (porque se debe evitar), Saladillo (porque está fuera del radio).
 *
 *
 *
 *
 * NOTA: Implementé los recorridos DFS y BFS.
 *
 */

public class Parcial5 {

	ListaGenerica<String> resolverDFS(Grafo<String> ciudades, String origen, Integer radio,
			ListaGenerica<String> evitarCiudadesContiguas) {

		ListaGenerica<String> camino = new ListaEnlazadaGenerica<String>();

		Vertice<String> v = buscarCiudad(ciudades, origen);

		if (v != null) {
			boolean[] marcas = new boolean[ciudades.listaDeVertices().tamanio() + 1];

			dfs(ciudades, v, marcas, camino, evitarCiudadesContiguas, radio, 0);
		}
		return camino;

	}

	private void dfs(Grafo<String> ciudades, Vertice<String> v, boolean[] marcas, ListaGenerica<String> camino,
			ListaGenerica<String> evitarCiudadesContiguas, int radio, int nivelActual) {

		if (ciudadValida(ciudades, v, evitarCiudadesContiguas))
			camino.agregarFinal(v.dato());

		
		ListaGenerica<Arista<String>> ady = ciudades.listaDeAdyacentes(v);
		ady.comenzar();

		if (++nivelActual <= radio) {
			marcas[v.getPosicion()] = true;
			while (!ady.fin()) {
				Vertice<String> vertice = ady.proximo().verticeDestino();

				if (!marcas[vertice.getPosicion()])
					dfs(ciudades, vertice, marcas, camino, evitarCiudadesContiguas, radio, nivelActual);
			}
		}

	}

	ListaGenerica<String> resolverBFS(Grafo<String> ciudades, String origen, Integer radio,
			ListaGenerica<String> evitarCiudadesContiguas) {

		ListaGenerica<String> camino = new ListaEnlazadaGenerica<String>();

		Vertice<String> v = buscarCiudad(ciudades, origen);

		if (v != null) {
			boolean[] marcas = new boolean[ciudades.listaDeVertices().tamanio() + 1];

			bfs(ciudades, v, marcas, camino, evitarCiudadesContiguas, radio);
		}
		return camino;

	}

	private void bfs(Grafo<String> ciudades, Vertice<String> vInicial, boolean[] marcas, ListaGenerica<String> camino,
			ListaGenerica<String> evitarCiudadesContiguas, int radio) {

		ColaGenerica<Vertice<String>> cola = new ColaGenerica<Vertice<String>>();
		cola.encolar(vInicial);
		cola.encolar(null);
		marcas[vInicial.getPosicion()] = true;

		int nivelActual = 0;

		while (!cola.esVacia()) {
			Vertice<String> vActual = cola.desencolar();

			if (vActual != null) {
				if (ciudadValida(ciudades, vActual, evitarCiudadesContiguas))
					camino.agregarFinal(vActual.dato());
				if (nivelActual < radio) {
					ListaGenerica<Arista<String>> listaDeAdyacentes = ciudades.listaDeAdyacentes(vActual);
					listaDeAdyacentes.comenzar();
					while (!listaDeAdyacentes.fin()) {
						Vertice<String> vSiguiente = listaDeAdyacentes.proximo().verticeDestino();
						int j = vSiguiente.getPosicion();
						if (!marcas[j]) {
							marcas[j] = true;
							cola.encolar(vSiguiente);
						}
					}
				}
			} else if (++nivelActual <= radio)
				cola.encolar(null);

		}

	}

	private Vertice<String> buscarCiudad(Grafo<String> ciudades, String ciudad) {
		ListaGenerica<Vertice<String>> vertices = ciudades.listaDeVertices();
		Vertice<String> v = null;
		vertices.comenzar();
		while (!vertices.fin()) {
			v = vertices.proximo();
			if (v.dato().equals(ciudad))
				return v;
		}
		return v;
	}

	private boolean ciudadValida(Grafo<String> ciudades, Vertice<String> v, ListaGenerica<String> lista) {
		boolean cumple = true;
		lista.comenzar();
		while (!lista.fin() && cumple) {
			Vertice<String> v2 = buscarCiudad(ciudades, lista.proximo());
			if (v2 != null)
				cumple = !v.equals(v2) && !ciudades.esAdyacente(v, v2);
		}

		return cumple;
	}

	public static void main(String[] args) {

		Grafo<String> ciudades = new GrafoImplListAdy<String>();

		Vertice<String> a = new VerticeImplListAdy<String>("Quilmes");
		Vertice<String> b = new VerticeImplListAdy<String>("La Plata");
		Vertice<String> c = new VerticeImplListAdy<String>("Pinamar");
		Vertice<String> d = new VerticeImplListAdy<String>("Moreno");
		Vertice<String> e = new VerticeImplListAdy<String>("Abasto");
		Vertice<String> f = new VerticeImplListAdy<String>("Carlos Keen");
		Vertice<String> g = new VerticeImplListAdy<String>("Suipacha");
		Vertice<String> h = new VerticeImplListAdy<String>("Cañuelas");
		Vertice<String> i = new VerticeImplListAdy<String>("Navarro");
		Vertice<String> j = new VerticeImplListAdy<String>("Saladillo");
		Vertice<String> k = new VerticeImplListAdy<String>("Lobos");

		ciudades.agregarVertice(a);
		ciudades.agregarVertice(b);
		ciudades.agregarVertice(c);
		ciudades.agregarVertice(d);
		ciudades.agregarVertice(e);
		ciudades.agregarVertice(f);
		ciudades.agregarVertice(g);
		ciudades.agregarVertice(h);
		ciudades.agregarVertice(i);
		ciudades.agregarVertice(j);
		ciudades.agregarVertice(k);

		ciudades.conectar(a, b);
		ciudades.conectar(a, d);
		ciudades.conectar(b, a);
		ciudades.conectar(b, c);
		ciudades.conectar(b, e);
		ciudades.conectar(d, e);
		ciudades.conectar(d, f);
		ciudades.conectar(d, a);
		ciudades.conectar(e, d);
		ciudades.conectar(e, b);
		ciudades.conectar(e, h);
		ciudades.conectar(c, b);
		ciudades.conectar(h, e);
		ciudades.conectar(h, i);
		ciudades.conectar(f, d);
		ciudades.conectar(f, g);
		ciudades.conectar(g, f);
		ciudades.conectar(g, i);
		ciudades.conectar(i, g);
		ciudades.conectar(i, h);
		ciudades.conectar(i, j);
		ciudades.conectar(i, k);
		ciudades.conectar(j, i);
		ciudades.conectar(k, i);

		Parcial5 parcial = new Parcial5();

		ListaGenerica<String> evitarCiudadesContiguas = new ListaEnlazadaGenerica<String>();
		evitarCiudadesContiguas.agregarFinal("La Plata");
		evitarCiudadesContiguas.agregarFinal("Lobos");
		ListaGenerica<String> resDFS = parcial.resolverDFS(ciudades, "Moreno", 3, evitarCiudadesContiguas);
		ListaGenerica<String> resBFS = parcial.resolverBFS(ciudades, "Moreno", 3, evitarCiudadesContiguas);
		System.out.println("Recorrido DFS: " + resDFS);
		System.out.println("Recorrido BFS: " + resBFS);
	}

}
