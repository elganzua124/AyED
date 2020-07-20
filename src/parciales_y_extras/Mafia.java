package parciales_y_extras;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.Vertice;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

/**
 * 
 * @author franh
 *
 *
 *         "El Paso City", años 20. Las mafias controlan varios sitios y calles
 *         de la ciudad. El intendente que debe desplazarse diariamente en su
 *         auto desde su residencia a la municipalidad, está seriamente
 *         amenazado. Ud. debe ayudar al intendente encontrando la ruta más
 *         segura para realizar su traslado diario implementando en Java un
 *         método que recibe como parámetro la ciudad, y retorne la ruta que
 *         pase por el menor número de calles y sitios controlados por la mafia.
 *         (En caso de existir más de una ruta, con retornar alguna de ellas
 *         alcanzará). La ciudad se describe como un conjunto de n sitios y
 *         varias calles bidireccionaes que unen esos sitios. Cada sitio tiene
 *         la información si está controlado por la mafia o no. Lo mismo sucede
 *         con cada una de las calles de la ciudad.
 */

public class Mafia {

	private class Sitio {

		private boolean esMafia = false;

		private String nombre;

		public void setMafioso() {
			esMafia = true;
		}

		public boolean esMafioso() {
			return esMafia;
		}

		public String nombre() {
			return nombre;
		}

	}

	private Grafo<Sitio> mapaCiudad;

	public ListaGenerica<Sitio> rutaMasSegura(Grafo<Sitio> grafo) {

		mapaCiudad = grafo;

		boolean[] marcas = new boolean[grafo.listaDeVertices().tamanio() + 1];
		ListaGenerica<Sitio> caminoActual = new ListaEnlazadaGenerica<Sitio>();
		ListaGenerica<Sitio> mejorCamino = new ListaEnlazadaGenerica<Sitio>();
		int[] menorMafias = { Integer.MAX_VALUE };
		int mafias = 0;

		Vertice<Sitio> v = buscarSitio("Casa de Intendente");

		if (v != null && buscarSitio("Municipalidad") != null)
			dfs(v, "Municipalidad", marcas, caminoActual, mejorCamino, menorMafias, mafias);

		return mejorCamino;

	}

	private void dfs(Vertice<Sitio> v, String destino, boolean[] marcas, ListaGenerica<Sitio> caminoActual,
			ListaGenerica<Sitio> mejorCamino, int[] menorMafias, int mafias) {

		caminoActual.agregarFinal(v.dato());

		if (v.dato().nombre().equals(destino)) {
			if (mafias < menorMafias[0]) {
				menorMafias[0] = mafias;
				clonar(caminoActual, mejorCamino);
			}
		} else {
			marcas[v.getPosicion()] = true;

			ListaGenerica<Arista<Sitio>> ady = mapaCiudad.listaDeAdyacentes(v);
			ady.comenzar();

			while (!ady.fin()) {
				Arista<Sitio> arista = ady.proximo();
				Vertice<Sitio> vertice = arista.verticeDestino();
				int m = mafias + arista.peso();
				if (vertice.dato().esMafioso())
					m++;
				//if (!marcas[vertice.getPosicion()]) //falta no?

				dfs(vertice, destino, marcas, caminoActual, mejorCamino, menorMafias, m);
			}

			marcas[v.getPosicion()] = false;
		}

		caminoActual.eliminarEn(v.getPosicion());

	}

	private Vertice<Sitio> buscarSitio(String ciudad) {
		ListaGenerica<Vertice<Sitio>> vertices = mapaCiudad.listaDeVertices();
		Vertice<Sitio> v = null;
		vertices.comenzar();
		while (!vertices.fin()) {
			v = vertices.proximo();
			if (v.dato().nombre().equals(ciudad))
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

}
