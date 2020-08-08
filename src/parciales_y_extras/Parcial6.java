package parciales_y_extras;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.GrafoImplListAdy;
import estructuras.grafo.Vertice;
import estructuras.grafo.VerticeImplListAdy;
import estructuras.listas.ListaGenerica;

/*
 * 
 * Devuelve la cantidad de ciclos desde un origen dado,
 * recorriendo las aristas válidas, sin repetir vertices
 * excepto el primero.
 * 
 * peso_arista = 1 -> vertice_destino alcanzable
 * peso_arista = 0 -> vertice_destino no alcanzable
 * 
 */
public class Parcial6 {

	public <T> int cantidadCiclosValidos(Grafo<T> grafo, T origen) {

		int ciclos = 0;
		Vertice<T> v = buscarVertice(grafo, origen);

		if (v != null) {
			boolean[] marcas = new boolean[grafo.listaDeVertices().tamanio() + 1];
			ciclos = dfs(grafo, marcas, v, v, 0);
		}
		return ciclos;
	}

	private <T> int dfs(Grafo<T> grafo, boolean[] marcas, Vertice<T> vOrigen, Vertice<T> vActual, int ciclos) {

		marcas[vActual.getPosicion()] = true;
		ListaGenerica<Arista<T>> ady = grafo.listaDeAdyacentes(vActual);
		ady.comenzar();
		while (!ady.fin()) {
			Arista<T> arista = ady.proximo();

			if (arista.peso() != 0) {
				vActual = arista.verticeDestino();

				if (vOrigen.equals(vActual))
					ciclos++;
				else if (!marcas[vActual.getPosicion()])
					ciclos = dfs(grafo, marcas, vOrigen, vActual, ciclos);
			}
		}

		marcas[vActual.getPosicion()] = false;
		return ciclos;
	}

	private <T> Vertice<T> buscarVertice(Grafo<T> grafo, T dato) {
		ListaGenerica<Vertice<T>> vertices = grafo.listaDeVertices();
		Vertice<T> v = null;
		vertices.comenzar();
		while (!vertices.fin()) {
			v = vertices.proximo();
			if (v.dato().equals(dato))
				return v;
		}
		return v;
	}

	public static void main(String[] args) {

		Grafo<Character> grafo = new GrafoImplListAdy<Character>();
		Parcial6 parcial = new Parcial6();

		Vertice<Character> a = new VerticeImplListAdy<Character>('A');
		Vertice<Character> b = new VerticeImplListAdy<Character>('B');
		Vertice<Character> c = new VerticeImplListAdy<Character>('C');
		Vertice<Character> d = new VerticeImplListAdy<Character>('D');
		Vertice<Character> e = new VerticeImplListAdy<Character>('E');
		Vertice<Character> f = new VerticeImplListAdy<Character>('F');
		Vertice<Character> g = new VerticeImplListAdy<Character>('G');

		grafo.agregarVertice(a);
		grafo.agregarVertice(b);
		grafo.agregarVertice(c);
		grafo.agregarVertice(d);
		grafo.agregarVertice(e);
		grafo.agregarVertice(f);
		grafo.agregarVertice(g);

		grafo.conectar(a, b, 1);
		grafo.conectar(b, d, 1);
		grafo.conectar(d, e, 1);
		grafo.conectar(e, a, 1);
		grafo.conectar(e, f, 1);
		grafo.conectar(f, a, 1);
		grafo.conectar(d, g, 1);
		grafo.conectar(g, c, 1);
		grafo.conectar(c, d, 1);
		grafo.conectar(d, a, 1);
		grafo.conectar(c, a, 0);

		int ciclos = parcial.cantidadCiclosValidos(grafo, 'A');

		System.out.println(ciclos); // imprime 3
	}
}
