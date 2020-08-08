package parciales_y_extras.parcial_18_07_2020;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.GrafoImplListAdy;
import estructuras.grafo.Vertice;
import estructuras.grafo.VerticeImplListAdy;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

/*
 * 
 * Implemente la clase Parcial, y el método:
 * 
 * ???? resolver(Grafo<????> ciudades, String origen, int maxMonto)
 * 
 * Se quiere realizar un viaje desde una ciudad origen, recorriendo la mayor cantidad de
 * localidades de la provincia. Sin embargo se tiene un presupuesto limitado para gastar
 * en los peajes de las distintas rutas (“maxMonto”). Su algoritmo debería devolver el
 * camino que cumple con la condición mencionada y el monto sobrante del gasto en peajes.
 * Si hubieran 2 caminos que permiten recorrer la misma cantidad de ciudades, debe devolver
 * el que permita el mayor ahorro.
 * 
 * En el siguiente ejemplo, partiendo de La Plata con un presupuesto máximo de $500, el
 * mejor resultado sería el camino que pasa por: La Plata, Quilmes, Moreno, Carlos Keen,
 * Suipacha, Navarro, Saladillo, y sobran $75 (diferencia de los $500 originales y los $425
 * del camino).
 * Si bien hay otro camino con el mismo número de ciudades (La Plata, Abasto, Moreno, Carlos
 * Keen, Suipacha, Navarro, Saladillo), el ahorro obtenido es menor ($25 resultante de la
 * diferencia de los $500 originales y los $475 del camino).
 * 
*/

public class Parcial_18_07_2020 {

	public MejorRecorrido<String> resolver(Grafo<String> ciudades, String origen, int maxMonto) {

		MejorRecorrido<String> mejor = new MejorRecorrido<String>(maxMonto);

		Vertice<String> v = buscarSitio(ciudades, origen);

		if (v != null) {
			ListaGenerica<String> caminoActual = new ListaEnlazadaGenerica<String>();
			boolean[] marcas = new boolean[ciudades.listaDeVertices().tamanio() + 1];

			dfs(ciudades, v, marcas, caminoActual, 0, mejor);

		}

		return mejor;
	}

	/*
	 * Para que sea el último nodo del camino, todos sus adyacentes no deben ser
	 * alcanzables, ya que no hay dinero suficiente para pagar el peaje de alguna de
	 * las ciudades adyacentes. Es decir que el recorrido hay que hacerlo postorden.
	 * Podría hacerse preorden, pero estaría actualizando el mejor camino muchas veces.
	 */
	private void dfs(Grafo<String> ciudades, Vertice<String> v, boolean[] marcas, ListaGenerica<String> caminoActual,
			int gastado, MejorRecorrido<String> mejor) {

		marcas[v.getPosicion()] = true;
		caminoActual.agregarFinal(v.dato());

		ListaGenerica<Arista<String>> ady = ciudades.listaDeAdyacentes(v);
		ady.comenzar();

		while (!ady.fin()) {

			Arista<String> arista = ady.proximo();
			Vertice<String> vertice = arista.verticeDestino();
			int aGastar = gastado + arista.peso();

			if (!marcas[vertice.getPosicion()] && aGastar <= mejor.maxMonto()) {

				dfs(ciudades, vertice, marcas, caminoActual, aGastar, mejor);
				// ya evalué todos mis adyacentes y caminoActual
				// tiene el mejor candidato a evaluar
				if (caminoActual.tamanio() > mejor.getRecorrido().tamanio())
					mejor.cambiarRecorrido(caminoActual, aGastar);
				else if (caminoActual.tamanio() == mejor.getRecorrido().tamanio())
					if (aGastar < mejor.gastado())
						mejor.cambiarRecorrido(caminoActual, aGastar);

				caminoActual.eliminarEn(caminoActual.tamanio());
			}
		}

		marcas[v.getPosicion()] = false;
	}

	private <T> Vertice<T> buscarSitio(Grafo<T> grafo, String ciudad) {
		ListaGenerica<Vertice<T>> vertices = grafo.listaDeVertices();
		Vertice<T> v = null;
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

	public static void main(String[] args) {

		Grafo<String> grafo = new GrafoImplListAdy<String>();
		Parcial_18_07_2020 parcial = new Parcial_18_07_2020();

		Vertice<String> a = new VerticeImplListAdy<String>("Quilmes");
		Vertice<String> b = new VerticeImplListAdy<String>("La Plata");
		Vertice<String> c = new VerticeImplListAdy<String>("Pinamar");
		Vertice<String> d = new VerticeImplListAdy<String>("Moreno");
		Vertice<String> e = new VerticeImplListAdy<String>("Abasto");
		Vertice<String> f = new VerticeImplListAdy<String>("Carlos Keen");
		Vertice<String> g = new VerticeImplListAdy<String>("Cañuelas");
		Vertice<String> h = new VerticeImplListAdy<String>("Suipacha");
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

		grafo.conectar(a, b, 50);
		grafo.conectar(a, d, 100);
		grafo.conectar(b, a, 50);
		grafo.conectar(b, c, 300);
		grafo.conectar(b, e, 100);
		grafo.conectar(c, b, 300);
		grafo.conectar(d, a, 100);
		grafo.conectar(d, f, 100);
		grafo.conectar(d, e, 100);
		grafo.conectar(e, b, 100);
		grafo.conectar(e, d, 100);
		grafo.conectar(e, g, 100);
		grafo.conectar(f, d, 100);
		grafo.conectar(f, h, 100);
		grafo.conectar(g, e, 100);
		grafo.conectar(g, i, 200);
		grafo.conectar(h, f, 100);
		grafo.conectar(h, i, 50);
		grafo.conectar(i, g, 200);
		grafo.conectar(i, k, 110);
		grafo.conectar(i, j, 25);
		grafo.conectar(i, h, 50);
		grafo.conectar(j, i, 25);
		grafo.conectar(k, i, 110);

		MejorRecorrido<String> mejorCamino = parcial.resolver(grafo, "La Plata", 500);
		System.out.println(mejorCamino);

	}

}
