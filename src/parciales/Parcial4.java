package parciales;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.Vertice;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

/**
 * 
 * @author franh
 *
 *         Implemente la clase Parcial y el método:
 * 
 *         ??? resolver(Grafo<???> ciudades, String origen, String destino,
 *         Lista<???> pasandoPor)
 * 
 *         Se quiere encontrar un camino desde una ciudad origen hasta una
 *         ciudad destino, teniendo en cuenta que debido a la pandemia, queremos
 *         pasar sí o sí por ciertas ciudades específicas pasadas en una lista
 *         como parámetro. Para cada ciudad se conoce el nombre.
 * 
 *         En este ejemplo, para llegar desde La Plata a Suipacha, pasando por
 *         Quilmes y Carlos Keen, el camino a devolver es el resaltado, ya que
 *         pasa por ambas ciudades.
 * 
 *         Nota:
 * 
 *         - Complete en la firma del método los tipos de datos indicados con
 *         signo de interrogación.
 * 
 *         - Debe verificar la existencia de la ciudad origen y la ciudad
 *         destino.
 *         
 *         - No se puede pasar 2 veces por el mismo lugar. - En caso de
 *         no existir un camino posible, deve devolver el valor más adecuado que
 *         se ajuste a lo solicitado. - Use los métodos de Grafo y Listas visto
 *         en clase.
 * 
 */

public class Parcial4 {

	private Grafo<String> mapaCiudades;

	ListaGenerica<String> resolver(Grafo<String> ciudades, String origen, String destino,
			ListaGenerica<String> pasandoPor) {

		mapaCiudades = ciudades;

		ListaGenerica<String> camino = new ListaEnlazadaGenerica<String>();

		Vertice<String> v = buscarCiudad(origen);

		if (v != null && buscarCiudad(destino) != null) {

			boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio() + 1];

			dfs(v, destino, marcas, camino, pasandoPor);

		}

		return camino;
	}

	private boolean dfs(Vertice<String> v, String destino, boolean[] marcas, ListaGenerica<String> camino,
			ListaGenerica<String> pasandoPor) {

		camino.agregarFinal(destino);

		boolean incluye = false;

		if (v.dato().equals(destino)) {

			pasandoPor.comenzar();

			incluye = true;
			while (!pasandoPor.fin() && incluye)
				incluye = camino.incluye(pasandoPor.proximo());

			return incluye;

		}

		marcas[v.getPosicion()] = true;

		ListaGenerica<Arista<String>> adys = mapaCiudades.listaDeAdyacentes(v);
		adys.comenzar();
		while (!adys.fin() && !incluye) {
			Vertice<String> ady = adys.proximo().verticeDestino();
			if (!marcas[ady.getPosicion()]) {
				incluye = dfs(ady, destino, marcas, camino, pasandoPor);
			}
		}

		if (!incluye) {
			camino.eliminarEn(camino.tamanio());
			marcas[v.getPosicion()] = false;
		}

		return incluye;

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

}
