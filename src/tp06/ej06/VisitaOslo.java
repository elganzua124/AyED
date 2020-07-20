package tp06.ej06;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.GrafoImplListAdy;
import estructuras.grafo.Vertice;
import estructuras.grafo.VerticeImplListAdy;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

public class VisitaOslo {

	private Grafo<String> lugaresDeOslo;

	public ListaGenerica<String> paseoEnBici(Grafo<String> lugares, String destino, int maxTiempo,
			ListaGenerica<String> lugaresRestringidos) {
		lugaresDeOslo = lugares;

		ListaGenerica<String> camino = new ListaEnlazadaGenerica<String>();

		boolean[] marcas = new boolean[lugaresDeOslo.listaDeVertices().tamanio() + 1];

		Vertice<String> v = buscarLugar("Ayuntamiento");
		if (v != null && buscarLugar(destino) != null)
			dfs(v, destino, marcas, camino, maxTiempo, 0, lugaresRestringidos);
		return camino;

	}

	private boolean dfs(Vertice<String> v, String destino, boolean[] marcas, ListaGenerica<String> camino,
			int maxTiempo, int tiempoActual, ListaGenerica<String> lugaresRestringidos) {

		marcas[v.getPosicion()] = true;
		camino.agregarFinal(v.dato());
		if (v.dato().equals(destino)) {
			return true;
		}
		boolean encontre = false;

		ListaGenerica<Arista<String>> adyacentes = lugaresDeOslo.listaDeAdyacentes(v);
		adyacentes.comenzar();
		while (!adyacentes.fin()) {

			Arista<String> arista = adyacentes.proximo();
			Vertice<String> ady = arista.verticeDestino();
			int tiempo = tiempoActual + arista.peso();

			if (!marcas[ady.getPosicion()] && !lugaresRestringidos.incluye(ady.dato())) {
				
				if(camino.incluye(ady.dato()))
					System.out.print("maal");
				if (tiempo <= maxTiempo)
					encontre = dfs(ady, destino, marcas, camino, maxTiempo, tiempo, lugaresRestringidos);
			}
		}
		if (!encontre) {
			marcas[v.getPosicion()] = false; // es necesario?
			camino.eliminarEn(camino.tamanio());
		}

		return encontre;

	}

	private Vertice<String> buscarLugar(String lugar) {
		ListaGenerica<Vertice<String>> vertices = lugaresDeOslo.listaDeVertices();
		Vertice<String> v = null;
		vertices.comenzar();
		while (!vertices.fin()) {
			v = vertices.proximo();
			if (v.dato().equals(lugar))
				return v;
		}
		return v;

	}

	public static void main(String[] args) {

		Grafo<String> grafo = new GrafoImplListAdy<String>();

		Vertice<String> a = new VerticeImplListAdy<String>("Palacio Real");
		Vertice<String> b = new VerticeImplListAdy<String>("Akker Brigge");
		Vertice<String> c = new VerticeImplListAdy<String>("La Opera");
		Vertice<String> d = new VerticeImplListAdy<String>("Museo Vikingo");
		Vertice<String> e = new VerticeImplListAdy<String>("Parque Vigeland");
		Vertice<String> f = new VerticeImplListAdy<String>("Holmenkollen");
		Vertice<String> g = new VerticeImplListAdy<String>("Fortaleza Akershus");
		Vertice<String> h = new VerticeImplListAdy<String>("Museo del Barco Polar");
		Vertice<String> i = new VerticeImplListAdy<String>("Museo Fram");
		Vertice<String> j = new VerticeImplListAdy<String>("El Tigre");
		Vertice<String> k = new VerticeImplListAdy<String>("Ayuntamiento");
		Vertice<String> l = new VerticeImplListAdy<String>("FolkMuseum");
		Vertice<String> m = new VerticeImplListAdy<String>("Galeria Nacional");
		Vertice<String> n = new VerticeImplListAdy<String>("Parque Botanico");
		Vertice<String> o = new VerticeImplListAdy<String>("Museo Munch");

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
		grafo.agregarVertice(l);
		grafo.agregarVertice(m);
		grafo.agregarVertice(n);
		grafo.agregarVertice(o);

		// grafo.conectar(a , , );
		// grafo.conectar(a , , );
		// grafo.conectar(b , , );
		// grafo.conectar( b, , );
		// grafo.conectar( b, , );
		// grafo.conectar( c, , );
		// grafo.conectar( c, , );
		// grafo.conectar( d, , );
		// grafo.conectar( d, , );
		grafo.conectar(e, l, 20);
		// grafo.conectar( e, , );
		// grafo.conectar( f, , );
		// grafo.conectar( g, , );
		grafo.conectar(h, d, 5);
		// grafo.conectar( h, , );
		grafo.conectar(i, h, 5);
		// grafo.conectar( i, , );
		grafo.conectar(j, o, 15);
		// grafo.conectar( j, , );
		// grafo.conectar( j, , );
		grafo.conectar(k, j, 15);
		grafo.conectar(j, n, 10);
		// grafo.conectar( k, , );
		// grafo.conectar( k, , );
		grafo.conectar(l, i, 5);
		// grafo.conectar(l , , );
		// grafo.conectar( l, , );
		// grafo.conectar( l, , );
		grafo.conectar(m, e, 10);
		// grafo.conectar( m, , );
		grafo.conectar(n, m, 15);
		// grafo.conectar( n, , );
		// grafo.conectar( n, , );
		grafo.conectar(o, n, 1);
		// grafo.conectar( o, , );

		VisitaOslo lugares = new VisitaOslo();

		ListaGenerica<String> lugaresRestringidos = new ListaEnlazadaGenerica<String>();
		lugaresRestringidos.agregarFinal("Akker Brigge");
		lugaresRestringidos.agregarFinal("Palacio Real");
		lugaresRestringidos.agregarFinal("Museo Munch"); // cancela el camino por El Tigre, y sigue por el Parque
															// Botanico
		ListaGenerica<String> res = lugares.paseoEnBici(grafo, "Museo Vikingo", 120, lugaresRestringidos);

		System.out.print(res);

	}
}
