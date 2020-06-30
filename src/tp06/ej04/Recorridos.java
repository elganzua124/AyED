package tp06.ej04;

import estructuras.ColaGenerica;
import estructuras.grafo.*;
import estructuras.listas.*;

public class Recorridos<T> {

	public ListaGenerica<Vertice<T>> dfs(Grafo<T> grafo) {
		int tam = grafo.listaDeVertices().tamanio();
		boolean[] marcas = new boolean[tam];
		ListaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<Vertice<T>>();
		lista.comenzar();
		for (int i = 0; i < tam; i++) {
			if (!marcas[i]) {
				dfs(grafo, marcas, i, lista);
			}
		}
		return lista;
	}

	private void dfs(Grafo<T> grafo, boolean[] marcas, int pos, ListaGenerica<Vertice<T>> lista) {
		marcas[pos] = true;
		Vertice<T> vertice = grafo.listaDeVertices().elemento(pos);
		lista.agregarFinal(vertice);
		ListaGenerica<Arista<T>> listaDeAdyacentes = grafo.listaDeAdyacentes(vertice);
		listaDeAdyacentes.comenzar();
		while (!listaDeAdyacentes.fin()) {
			int j = listaDeAdyacentes.proximo().verticeDestino().getPosicion();
			if (!marcas[j]) {
				dfs(grafo, marcas, j, lista);
			}
		}
	}

	public ListaGenerica<Vertice<T>> bfs(Grafo<T> grafo) {
		ListaGenerica<Vertice<T>> listaDeVertices = grafo.listaDeVertices();
		ListaGenerica<Arista<T>> listaDeAdyacentes;
		ListaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<Vertice<T>>();
		ColaGenerica<Vertice<T>> cola = new ColaGenerica<Vertice<T>>();
		Vertice<T> vertice;
		listaDeVertices.comenzar();
		cola.encolar(listaDeVertices.proximo());
		cola.encolar(null);
		while (!cola.esVacia()) {
			vertice = cola.desencolar();
			if (vertice != null) {
				if (!lista.incluye(vertice)) {
					lista.agregarFinal(vertice);
					listaDeAdyacentes = ((VerticeImplListAdy<T>) vertice).obtenerAdyacentes();
					while (!listaDeAdyacentes.fin()) {
						cola.encolar(listaDeAdyacentes.proximo().verticeDestino());
					}
				}
			} else {
				if (!cola.esVacia()) {
					cola.encolar(null);
				}
			}
		}
		return lista;
	}
}