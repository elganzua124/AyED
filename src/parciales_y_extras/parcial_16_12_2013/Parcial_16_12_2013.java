package parciales_y_extras.parcial_16_12_2013;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.GrafoImplListAdy;
import estructuras.grafo.Vertice;
import estructuras.grafo.VerticeImplListAdy;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

/*
 * Dado un grafo dirigido y pesado, se pide implementar un método en Java
 * que retorne el ciclo simple con mayor peso del mismo que comience y
 * termine en un vértice dado v, este vértice se pasa como parámetro. Se
 * debe retornar el ciclo y el costo del mismo.
 * 
 * Ciclo simple: ciclo en el que todos sus vértices, excepto el primero y
 * el último son distintos.
 * 
 * Ejemplo: Digrafo pesado. Vértice v = C
 * 
 * hay 6 ciclos simples que comienzan en C:
 * 
 * (C,A,F,E,B,C) con costo 14
 * (C,A,F,E,B,G,C) con costo 19
 * (C,A,F,E,B,G,D,C) con costo 33
 * (C,A,D,C) con costo 37
 * (C,A,D,B,C) con costo 32
 * (C,A,D,B,G,C) con costo 36 
 * 
 * Se debe retornar (C,A,D,C) con costo 37
 * 
 */

public class Parcial_16_12_2013 {

	public <T> Ciclo<T> cicloMayorPeso(Grafo<T> grafo, Vertice<T> vertice) {
		Ciclo<T> mejorCiclo = new Ciclo<T>();

		if (incluyeVertice(grafo, vertice)) {

			boolean[] marcas = new boolean[grafo.listaDeVertices().tamanio() + 1];
			ListaGenerica<T> camino = new ListaEnlazadaGenerica<T>();

			dfs(grafo, vertice, camino, marcas, mejorCiclo, 0);

		}

		return mejorCiclo;
	}

	private <T> void dfs(Grafo<T> grafo, Vertice<T> vertice, ListaGenerica<T> camino, boolean[] marcas,
			Ciclo<T> mejorCiclo, int peso) {

		camino.agregarFinal(vertice.dato());

		boolean es_primer_vertice = (vertice.dato().equals(camino.elemento(1)));

		if (camino.tamanio() == 1)
			mejorCiclo.cambiarCiclo(camino, peso);
		else if (es_primer_vertice) {
			if (peso > mejorCiclo.getPeso())
				mejorCiclo.cambiarCiclo(camino, peso);
			camino.eliminarEn(camino.tamanio());
			return;

		}

		marcas[vertice.getPosicion()] = !es_primer_vertice;
		ListaGenerica<Arista<T>> ady = grafo.listaDeAdyacentes(vertice);

		ady.comenzar();
		while (!ady.fin()) {
			Arista<T> arista = ady.proximo();
			Vertice<T> v = arista.verticeDestino();

			if (!marcas[v.getPosicion()]) {
				int p = peso + arista.peso();
				dfs(grafo, v, camino, marcas, mejorCiclo, p);
			}
		}

		marcas[vertice.getPosicion()] = false;
		camino.eliminarEn(camino.tamanio());
	}

	private <T> boolean incluyeVertice(Grafo<T> grafo, Vertice<T> vertice) {
		ListaGenerica<Vertice<T>> vertices = grafo.listaDeVertices();
		Vertice<T> v = null;
		vertices.comenzar();
		while (!vertices.fin()) {
			v = vertices.proximo();
			if (v == vertice)
				return true;
		}
		return (v == vertice);// return false?

	}

	public static void main(String[] args) {

		Grafo<Character> grafo = new GrafoImplListAdy<Character>();
		Parcial_16_12_2013 parcial = new Parcial_16_12_2013();

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

		grafo.conectar(a, f, 1);
		grafo.conectar(a, d, 20);
		grafo.conectar(b, c, 1);
		grafo.conectar(b, g, 5);
		grafo.conectar(c, a, 9);
		grafo.conectar(d, c, 8);
		grafo.conectar(d, b, 1);
		grafo.conectar(e, b, 1);
		grafo.conectar(f, e, 2);
		grafo.conectar(g, c, 1);
		grafo.conectar(g, d, 7);

		Ciclo<Character> ciclo = parcial.cicloMayorPeso(grafo, c);

		System.out.println(ciclo);

	}

}
