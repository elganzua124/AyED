package parciales_y_extras;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.GrafoImplListAdy;
import estructuras.grafo.Vertice;
import estructuras.grafo.VerticeImplListAdy;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

public class Parcial5 {

	ListaGenerica<String> resolverGrafo(Grafo<String> ciudades, String origen, Integer radio,
			ListaGenerica<String> evitarCiudadesContiguas) {

		ListaGenerica<String> camino = new ListaEnlazadaGenerica<String>();
		boolean[] marcas = new boolean[ciudades.listaDeVertices().tamanio() + 1];

		Vertice<String> v = buscarCiudad(ciudades, origen);

		if (v != null)
			dfs(ciudades, v, marcas, camino, evitarCiudadesContiguas, radio, 0);
		return camino;

	}

	private void dfs(Grafo<String> ciudades, Vertice<String> v, boolean[] marcas, ListaGenerica<String> camino,
			ListaGenerica<String> evitarCiudadesContiguas, int radio, int nivelActual) {

		camino.agregarFinal(v.dato());

		marcas[v.getPosicion()] = true;
		ListaGenerica<Arista<String>> ady = ciudades.listaDeAdyacentes(v);
		ady.comenzar();

		while (!ady.fin()) {
			Vertice<String> vertice = ady.proximo().verticeDestino();

			boolean cumple = nivelActual + 1 < radio && ciudadValida(ciudades, v, evitarCiudadesContiguas);

			if (!marcas[vertice.getPosicion()] && cumple)
				dfs(ciudades, vertice, marcas, camino, evitarCiudadesContiguas, radio, nivelActual++);
		}
		marcas[v.getPosicion()] = false;

		camino.eliminarEn(camino.tamanio());

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
				cumple = !v.dato().equals(v2.dato()) && !ciudades.esAdyacente(v, v2);
		}
		return cumple;
	}

	public static void main(String[] args) {

		Grafo<String> grafo = new GrafoImplListAdy<String>();

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

		grafo.agregarVertice(a);
		grafo.agregarVertice(b);
		grafo.agregarVertice(c);
		grafo.agregarVertice(d);
		grafo.agregarVertice(e);
		grafo.agregarVertice(f);
		grafo.agregarVertice(g);
		grafo.agregarVertice(h);
		grafo.agregarVertice(i);
		grafo.agregarVertice(j);
		grafo.agregarVertice(k);

		grafo.conectar(a, b);
		grafo.conectar(a, d);
		grafo.conectar(b, a);
		grafo.conectar(b, c);
		grafo.conectar(b, e);
		grafo.conectar(d, e);
		grafo.conectar(d, f);
		grafo.conectar(d, a);
		grafo.conectar(e, d);
		grafo.conectar(e, b);
		grafo.conectar(e, h);
		grafo.conectar(c, b);
		grafo.conectar(h, e);
		grafo.conectar(h, i);
		grafo.conectar(f, d);
		grafo.conectar(f, g);
		grafo.conectar(g, f);
		grafo.conectar(g, i);
		grafo.conectar(i, g);
		grafo.conectar(i, h);
		grafo.conectar(i, j);
		grafo.conectar(i, k);
		grafo.conectar(j, i);
		grafo.conectar(k, i);

		Parcial5 ciudades = new Parcial5();

		ListaGenerica<String> evitarCiudadesContiguas = new ListaEnlazadaGenerica<String>();
		evitarCiudadesContiguas.agregarFinal("La Plata");
		evitarCiudadesContiguas.agregarFinal("Lobos");
		ListaGenerica<String> res = ciudades.resolverGrafo(grafo, "Moreno", 3, evitarCiudadesContiguas);

		res.comenzar();
		System.out.println(res);
	}

}
