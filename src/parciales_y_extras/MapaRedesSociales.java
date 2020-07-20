package parciales_y_extras;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.GrafoImplListAdy;
import estructuras.grafo.Vertice;
import estructuras.grafo.VerticeImplListAdy;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

// parcial viejo

/*
 * Facebook, Friendster, etc, son en la actualidad populares sitios web de redes sociales.
 * En estos sitios, una persona puede crear un perfil virtual y construir una relación de
 * amistad con cualquier otra persona que esté registrada en la red, siempre y cuando ésta
 * acepte su invitación. Esta relación de amistad es muy bien modelada usando un grafo,
 * por ejemplo como el que se muestra en la figura siguiente. (Por simplicidad los nombres
 * de las personas fueron abreviados a un carácter).
 * 
 * En el ejemplo hay 3 grupos de amigos: { {BCDFGHI}, {AE}, {J} }. Usted debe determinar
 * cuáles son los grupos de amigos que hay en la red social Facebook.
 */

public class MapaRedesSociales {

	private Grafo<Character> grafo;

	public ListaGenerica<ListaGenerica<Character>> gruposDeAmigos(Grafo<Character> grafo) {

		this.grafo = grafo;

		int tam = grafo.listaDeVertices().tamanio();

		boolean[] marcas = new boolean[grafo.listaDeVertices().tamanio() + 1];

		ListaGenerica<ListaGenerica<Character>> grupos = new ListaEnlazadaGenerica<ListaGenerica<Character>>();

		for (int i = 1; i <= tam; i++) {
			if (!marcas[i]) {
				ListaGenerica<Character> camino = new ListaEnlazadaGenerica<Character>();
				dfs(marcas, i, camino);
				grupos.agregarFinal(camino);
			}
		}

		return grupos;

	}

	private void dfs(boolean[] marcas, int pos, ListaGenerica<Character> camino) {
		marcas[pos] = true;
		Vertice<Character> vertice = grafo.listaDeVertices().elemento(pos);
		camino.agregarFinal(vertice.dato());
		ListaGenerica<Arista<Character>> listaDeAdyacentes = grafo.listaDeAdyacentes(vertice);
		listaDeAdyacentes.comenzar();
		while (!listaDeAdyacentes.fin()) {
			int j = listaDeAdyacentes.proximo().verticeDestino().getPosicion();
			if (!marcas[j]) {
				dfs(marcas, j, camino);
			}
		}
	}

	public static void main(String[] args) {

		Grafo<Character> grafo = new GrafoImplListAdy<Character>();

		Vertice<Character> a = new VerticeImplListAdy<Character>('a');
		Vertice<Character> b = new VerticeImplListAdy<Character>('b');
		Vertice<Character> c = new VerticeImplListAdy<Character>('c');
		Vertice<Character> d = new VerticeImplListAdy<Character>('d');
		Vertice<Character> e = new VerticeImplListAdy<Character>('e');
		Vertice<Character> f = new VerticeImplListAdy<Character>('f');
		Vertice<Character> g = new VerticeImplListAdy<Character>('g');
		Vertice<Character> h = new VerticeImplListAdy<Character>('h');
		Vertice<Character> i = new VerticeImplListAdy<Character>('i');
		Vertice<Character> j = new VerticeImplListAdy<Character>('j');

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

		grafo.conectar(a, e);
		grafo.conectar(b, c);
		grafo.conectar(b, h);
		grafo.conectar(c, b);
		grafo.conectar(c, h);
		grafo.conectar(d, f);
		grafo.conectar(d, g);
		grafo.conectar(e, a);
		grafo.conectar(f, d);
		grafo.conectar(f, g);
		grafo.conectar(f, h);
		grafo.conectar(g, d);
		grafo.conectar(g, f);
		grafo.conectar(g, i);
		grafo.conectar(h, b);
		grafo.conectar(h, c);
		grafo.conectar(h, f);
		grafo.conectar(h, i);
		grafo.conectar(i, g);
		grafo.conectar(i, h);

		MapaRedesSociales mapa = new MapaRedesSociales();

		ListaGenerica<ListaGenerica<Character>> res = mapa.gruposDeAmigos(grafo);

		res.comenzar();

		while (!res.fin())
			System.out.println(res.proximo());

	}
}
