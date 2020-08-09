package parciales_y_extras.parcial_08_08_2020;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.GrafoImplListAdy;
import estructuras.grafo.Vertice;
import estructuras.grafo.VerticeImplListAdy;
import estructuras.listas.ListaGenerica;

/*
 * Implemente la clase Parcial y el método:
 * 
 *??? resolver(Grafo<???> sitios, String origen, int cantCuadras)
 * 
 * Un grupo de turistas está visitando la ciudad de La Plata. Quieren salir
 * de la Iglesia San Ponciano sin alejarse mucho y visitar todo lo posible.
 * Cada vértice contiene el nombre del sitio y el tiempo en minutos requerido
 * para recorrerlo, y en las aristas se cuenta con la cantidad de cuadras que
 * separan un sitio de otro. Además, quiere saber el tiempo que le demanda
 * visitar todos los lugares.
 * 
 * Por ejemplo para este grafo, partiendo de la Iglesia San Ponciano, con una
 * distancia máxima de 9 cuadras, debe retornar:
 * 
 * Iglesia San Ponciano, Legislatura, Coliseo Podestá, MACLA, Estadio de la
 * ciudad de LP, Palacio Campodonico y Tiempo total = 290 minutos.
 * 
 * Nota:
 * - Complete en la firma del método los tipos de datos indicados con signo de
 *   interrogación.
 * - Debe verificar la existencia del origen y destino. (Error en el enunciado,
 *   no hay destino que se deba verificar.
 * - Use los métodos de Grafo y Listas vistos en clase.
 * 
 */

public class Parcial_08_08_2020 {

	public ListaDeLugares resolver(Grafo<Lugar> sitios, String origen, int cantCuadras) {

		ListaDeLugares listaDelugares = new ListaDeLugares(cantCuadras);

		Vertice<Lugar> v = buscarSitio(sitios, origen);

		if (v != null) {

			boolean[] marcas = new boolean[sitios.listaDeVertices().tamanio() + 1];

			dfs(sitios, v, marcas, 0, listaDelugares);
		}

		return listaDelugares;
	}

	private void dfs(Grafo<Lugar> lugares, Vertice<Lugar> v, boolean[] marcas, int peso,
			ListaDeLugares listaDelugares) {

		/*
		 * Piden el conjunto de lugares, asi que no es necesario eliminar ni desmarcar
		 * vertices en la lista, sino agregar a la lista de lugares tantos vertices no
		 * marcados pueda (no me alejo mucho del origen).
		 * 
		 */

		marcas[v.getPosicion()] = true;
		listaDelugares.agregarFinal(v.dato());

		ListaGenerica<Arista<Lugar>> ady = lugares.listaDeAdyacentes(v);
		ady.comenzar();

		while (!ady.fin()) {

			Arista<Lugar> arista = ady.proximo();
			Vertice<Lugar> vertice = arista.verticeDestino();
			int pesoActual = peso + arista.peso();
			if (!marcas[vertice.getPosicion()] && pesoActual <= listaDelugares.cantCuadras())

				dfs(lugares, vertice, marcas, pesoActual, listaDelugares);
		}

	}

	private Vertice<Lugar> buscarSitio(Grafo<Lugar> grafo, String lugar) {
		ListaGenerica<Vertice<Lugar>> vertices = grafo.listaDeVertices();
		Vertice<Lugar> v = null;
		vertices.comenzar();
		while (!vertices.fin()) {
			v = vertices.proximo();
			if (v.dato().getNombre().equals(lugar))
				return v;
		}

		if (vertices.fin())
			return null;

		return v;
	}

	public static void main(String[] args) {

		Grafo<Lugar> grafo = new GrafoImplListAdy<Lugar>();
		Parcial_08_08_2020 parcial = new Parcial_08_08_2020();

		Vertice<Lugar> a = new VerticeImplListAdy<Lugar>(new Lugar("Iglesia San Ponciano", 30));
		Vertice<Lugar> b = new VerticeImplListAdy<Lugar>(new Lugar("Legislatura", 20));
		Vertice<Lugar> c = new VerticeImplListAdy<Lugar>(new Lugar("Coliseo Podestá", 50));
		Vertice<Lugar> d = new VerticeImplListAdy<Lugar>(new Lugar("MACLA", 120));
		Vertice<Lugar> e = new VerticeImplListAdy<Lugar>(new Lugar("Estadio Ciudad de LP", 30));
		Vertice<Lugar> f = new VerticeImplListAdy<Lugar>(new Lugar("Rectorado UNLP", 45));
		Vertice<Lugar> g = new VerticeImplListAdy<Lugar>(new Lugar("Palacio Campodónico", 40));
		Vertice<Lugar> h = new VerticeImplListAdy<Lugar>(new Lugar("Museo UNLP", 200));

		grafo.agregarVertice(a);
		grafo.agregarVertice(b);
		grafo.agregarVertice(c);
		grafo.agregarVertice(d);
		grafo.agregarVertice(e);
		grafo.agregarVertice(f);
		grafo.agregarVertice(g);
		grafo.agregarVertice(h);

		grafo.conectar(a, b, 5);
		grafo.conectar(a, c, 2);
		grafo.conectar(a, d, 9);
		grafo.conectar(a, e, 4);
		grafo.conectar(b, a, 5);
		grafo.conectar(b, c, 3);
		grafo.conectar(c, b, 3);
		grafo.conectar(c, a, 2);
		grafo.conectar(c, g, 1);
		grafo.conectar(d, a, 9);
		grafo.conectar(d, e, 8);
		grafo.conectar(e, a, 4);
		grafo.conectar(e, d, 8);
		grafo.conectar(e, f, 6);
		grafo.conectar(f, e, 6);
		grafo.conectar(f, g, 7);
		grafo.conectar(f, h, 1);
		grafo.conectar(g, f, 7);
		grafo.conectar(g, c, 1);
		grafo.conectar(h, f, 1);

		ListaDeLugares listaDeLugares = parcial.resolver(grafo, "Iglesia San Ponciano", 9);
		System.out.println(listaDeLugares);

	}

}
