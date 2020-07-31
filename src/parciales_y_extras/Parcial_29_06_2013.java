package parciales_y_extras;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.Vertice;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

/**
 *
 * ¿Es sólo una moda o vino para quedarse? No se sabe, pero como parte de un
 * plan fuerte de expansión en Argentina el número de locales abiertos de una
 * muy conocida cadena internacional de café ha aumentado 30% en la primera
 * mitad de 2013 con respecto al mismo período del año 2012.
 * 
 * Al parecer las personas se han vuelto tan adictas a estas tiendas de café
 * gourmet que los inmuebles que están cerca de estas cafeterías obtienen
 * mejores rentas. Esto ha sido notado por una compañía de bienes raíces, que
 * está interesada en identificar si una determinada esquina es un lugar valioso
 * en términos de su proximidad al mayor número de cafés de esta cadena.
 * 
 * Para ello cuentan con un mapa de la ciudad representada en un grafo, donde
 * cada arista indica la cantidad de cafés en esa cuadra. Supongamos que una
 * persona promedio está dispuesta a caminar un número de cuadras fijo X para
 * obtener su café matinal. Usted tiene que determinar si la esquina en cuestión
 * es valiosa. Es considerada valiosa si el número de cafés en los que una
 * persona puede alcanzar es mayor a un número fijo arbitrario Y.
 * 
 */

public class Parcial_29_06_2013 {
	/*
	 * Una arista es una cuadra, su peso es la cantidad de cafes en la cuadra Los
	 * vertices son las esquinas x: cantidad de cuadras que una persona esta
	 * dispuesta a caminar y: cantidad de cafes que hay que superar para que la
	 * esquina sea valiosa.
	 * 
	 */

	public <T> boolean es_valiosa(Grafo<T> mapa, T origen, int x, int y) {

		boolean es_valiosa = false;

		Vertice<T> v = buscarEsquina(mapa, origen);

		if (v != null) {

			boolean[] marcas = new boolean[mapa.listaDeVertices().tamanio() + 1];

			es_valiosa = dfs(mapa, v, marcas, x, y, 0, 0);

		}

		return es_valiosa;
	}

	private <T> boolean dfs(Grafo<T> mapa, Vertice<T> v, boolean[] marcas, int x, int y, int acum_cafes, int caminado) {

		if (acum_cafes > y)
			return true;

		if (caminado == x) // no tiene sentido visitar sus adyacentes
			return false;

		marcas[v.getPosicion()] = true;
		boolean cumple = false;

		ListaGenerica<Arista<T>> ady = mapa.listaDeAdyacentes(v);
		ady.comenzar();

		while (!ady.fin() && !cumple) {

			Arista<T> arista = ady.proximo();
			Vertice<T> vertice = arista.verticeDestino();

			if (!marcas[vertice.getPosicion()]) {
				int cafes = acum_cafes + arista.peso();
				cumple = dfs(mapa, vertice, marcas, x, y, cafes, caminado + 1);
			}

		}

		marcas[v.getPosicion()] = false;

		return cumple;
	}

	private <T> Vertice<T> buscarEsquina(Grafo<T> grafo, T ciudad) {
		ListaGenerica<Vertice<T>> vertices = grafo.listaDeVertices();
		Vertice<T> v = null;
		vertices.comenzar();
		while (!vertices.fin()) {
			v = vertices.proximo();
			if (v.dato().equals(ciudad))
				return v;
		}

		return v;
	}

}
