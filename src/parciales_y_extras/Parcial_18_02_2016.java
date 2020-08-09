package parciales_y_extras;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.GrafoImplListAdy;
import estructuras.grafo.Vertice;
import estructuras.grafo.VerticeImplListAdy;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

/*
 * Sea un grafo en donde cada nodo tiene un color (blanco o gris),
 * usted debe devolver el camino que comienza en un origen, llega
 * a un destino, y pasa alternadamente por nodos de distinto color
 * (y no pude repetir nodos). Para el siguiente grafo, considerando
 * que el origen es A y el destino es D, el camino a retornar es AGCD.
 * 
 * Implemente el siguiente método:
 * 
 * ListaGenerica devolverRecorrido(Grafo grafo)
 * 
 * */

public class Parcial_18_02_2016 {

	private class Nodo {

		public Character getLetra() {
			return letra;
		}

		public String getColor() {
			return color;
		}

		public Nodo setLetra(Character letra) {
			this.letra = letra;
			return this;
		}

		public Nodo setColorBlanco() {
			this.color = "blanco";
			return this;
		}

		public Nodo setColorGris() {
			this.color = "gris";
			return this;
		}

		private Character letra;
		private String color;

		@Override
		public String toString() {

			return letra + " (" + color + ")";
		}

	}

	public ListaGenerica<Nodo> devolverRecorrido(Grafo<Nodo> grafo, Nodo origen, Nodo destino) {

		ListaGenerica<Nodo> camino = new ListaEnlazadaGenerica<Nodo>();

		Vertice<Nodo> v = buscarNodo(grafo, origen);

		if (v != null && buscarNodo(grafo, destino) != null) {

			boolean[] marcas = new boolean[grafo.listaDeVertices().tamanio() + 1];

			dfs(grafo, v, destino, marcas, camino);

		}

		return camino;

	}

	private boolean dfs(Grafo<Nodo> grafo, Vertice<Nodo> v, Nodo destino, boolean[] marcas,
			ListaGenerica<Nodo> camino) {

		camino.agregarFinal(v.dato());

		if (v.dato().equals(destino))
			return true;

		marcas[v.getPosicion()] = true;

		ListaGenerica<Arista<Nodo>> ady = grafo.listaDeAdyacentes(v);
		ady.comenzar();
		boolean encontre = false;
		while (!ady.fin() && !encontre) {
			Vertice<Nodo> a = ady.proximo().verticeDestino();

			if (!marcas[a.getPosicion()])
				if (v.dato().getColor() != a.dato().getColor())
					encontre = dfs(grafo, a, destino, marcas, camino);
		}

		marcas[v.getPosicion()] = false;

		if (!encontre)
			camino.eliminarEn(camino.tamanio());
		return encontre;

	}

	private Vertice<Nodo> buscarNodo(Grafo<Nodo> grafo, Nodo nodo) {
		ListaGenerica<Vertice<Nodo>> vertices = grafo.listaDeVertices();
		Vertice<Nodo> v = null;
		vertices.comenzar();
		while (!vertices.fin()) {
			v = vertices.proximo();
			if (v.dato().getColor().equals(nodo.getColor()))
				return v;
		}

		if (vertices.fin())
			return null;

		return v;
	}

	public static void main(String[] args) {

		Grafo<Nodo> grafo = new GrafoImplListAdy<Nodo>();
		Parcial_18_02_2016 parcial = new Parcial_18_02_2016();

		Vertice<Nodo> a = new VerticeImplListAdy<Nodo>(parcial.new Nodo());
		Vertice<Nodo> b = new VerticeImplListAdy<Nodo>(parcial.new Nodo());
		Vertice<Nodo> c = new VerticeImplListAdy<Nodo>(parcial.new Nodo());
		Vertice<Nodo> d = new VerticeImplListAdy<Nodo>(parcial.new Nodo());
		Vertice<Nodo> e = new VerticeImplListAdy<Nodo>(parcial.new Nodo());
		Vertice<Nodo> f = new VerticeImplListAdy<Nodo>(parcial.new Nodo());
		Vertice<Nodo> g = new VerticeImplListAdy<Nodo>(parcial.new Nodo());

		a.dato().setLetra('A').setColorBlanco();
		b.dato().setLetra('B').setColorBlanco();
		c.dato().setLetra('C').setColorBlanco();
		d.dato().setLetra('D').setColorGris();
		e.dato().setLetra('E').setColorGris();
		f.dato().setLetra('F').setColorBlanco();
		g.dato().setLetra('G').setColorGris();

		grafo.agregarVertice(a);
		grafo.agregarVertice(b);
		grafo.agregarVertice(c);
		grafo.agregarVertice(d);
		grafo.agregarVertice(e);
		grafo.agregarVertice(f);
		grafo.agregarVertice(g);

		grafo.conectar(a, b);
		grafo.conectar(a, g);
		grafo.conectar(a, f);
		grafo.conectar(b, a);
		grafo.conectar(b, c);
		grafo.conectar(b, g);
		grafo.conectar(c, b);
		grafo.conectar(c, d);
		grafo.conectar(c, g);
		grafo.conectar(d, c);
		grafo.conectar(d, e);
		grafo.conectar(d, g);
		grafo.conectar(e, d);
		grafo.conectar(e, f);
		grafo.conectar(e, g);
		grafo.conectar(f, a);
		grafo.conectar(f, e);
		grafo.conectar(f, g);
		grafo.conectar(g, a);
		grafo.conectar(g, b);
		grafo.conectar(g, c);
		grafo.conectar(g, d);
		grafo.conectar(g, e);
		grafo.conectar(g, f);

		ListaGenerica<Nodo> camino = parcial.devolverRecorrido(grafo, a.dato(), d.dato());
		System.out.println(camino);

	}

}
