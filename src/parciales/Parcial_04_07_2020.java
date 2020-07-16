package parciales;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.GrafoImplListAdy;
import estructuras.grafo.Vertice;
import estructuras.grafo.VerticeImplListAdy;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

/*
 * Implemente la clase Parcial,y el método:
 * 
 * ??? resolver(Grafo<???> ciudades, String origen, String destino, Lista<???> evitarPasarPor)
 * 
 * Se quiere encontrar todos los caminos desde una ciudad origen hasta una ciudad destino,
 * teniendo en cuenta que debido a la pandemia queremos evitar pasar por ciertas ciudades
 * específicas pasadas en una lista como parámetro. Para cada ciudad se conoce el nombre.
 * 
 * En este ejemplo, para llegar desde La Plata a Suipacha, evitando pasar por Quilmes y Lobos,
 * existen los siguientes caminos:
 * 
 *      La Plata, Abasto, Moreno, Carlos Keen, Suipacha
 *      
 *      La Plata, Abasto, Cañuelas, Navarro, Suipacha
 * 
 * 
 * Nota:
 * 
 * Complete en la firma del método los tipos de datos indicados con signo de interrogación.
 * 
 * Debe verificar la existencia de la ciudad origen y la ciudad destino.
 * 
 * No se puede pasar 2 veces por el mismo lugar.
 * 
 * En caso de no existir un camino posible, debe devolver el valor más adecuado que se ajusta
 * a lo solicitado.
 * 
 * Use los métodos de Grafo y Listas vistos en clase.*/

public class Parcial_04_07_2020 {

	private Grafo<String> mapaCiudades;

	ListaGenerica<ListaGenerica<String>> resolver(Grafo<String> ciudades, String origen, String destino,
			ListaGenerica<String> evitarPasarPor) {

		this.mapaCiudades = ciudades;

		ListaGenerica<ListaGenerica<String>> caminos = new ListaEnlazadaGenerica<ListaGenerica<String>>();

		ListaGenerica<String> camino = new ListaEnlazadaGenerica<String>();

		boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio() + 1];

		Vertice<String> v1 = buscarCiudad(origen);

		if (v1 != null && buscarCiudad(destino) != null)
			dfs(v1, destino, marcas, caminos, camino, evitarPasarPor);
		return caminos;

	}

	private void dfs(Vertice<String> v, String destino, boolean[] marcas, ListaGenerica<ListaGenerica<String>> caminos,
			ListaGenerica<String> camino, ListaGenerica<String> evitarPasarPor) {

		camino.agregarFinal(v.dato());
		if (v.dato().equals(destino)) {
			ListaGenerica<String> cam = new ListaEnlazadaGenerica<String>();
			clonar(camino, cam);
			caminos.agregarFinal(cam);

		} else {
			marcas[v.getPosicion()] = true;

			ListaGenerica<Arista<String>> adyacentes = mapaCiudades.listaDeAdyacentes(v);
			adyacentes.comenzar();
			while (!adyacentes.fin()) {

				Vertice<String> vertice = adyacentes.proximo().verticeDestino();

				if (!marcas[vertice.getPosicion()] && !evitarPasarPor.incluye(vertice.dato()))

					dfs(vertice, destino, marcas, caminos, camino, evitarPasarPor);
			}
		}
		camino.eliminarEn(camino.tamanio());

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

	private <T> void clonar(ListaGenerica<T> caminoOrigen, ListaGenerica<T> caminoDestino) {
		while (!caminoDestino.esVacia())
			caminoDestino.eliminarEn(1);

		caminoOrigen.comenzar();
		while (!caminoOrigen.fin()) {
			caminoDestino.agregarFinal(caminoOrigen.proximo());
		}

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

		Parcial_04_07_2020 ciudades = new Parcial_04_07_2020();

		ListaGenerica<String> evitarPasarPor = new ListaEnlazadaGenerica<String>();
		evitarPasarPor.agregarFinal("Quilmes");
		evitarPasarPor.agregarFinal("Lobos");
		ListaGenerica<ListaGenerica<String>> res = ciudades.resolver(grafo, "La Plata", "Suipacha", evitarPasarPor);

		res.comenzar();

		while (!res.fin())
			System.out.println(res.proximo());
	}
}
