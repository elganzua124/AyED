package tp06.ej04;

import estructuras.ColaGenerica;
import estructuras.grafo.*;
import estructuras.listas.*;

public class Recorridos<T> {

	public ListaGenerica<Vertice<T>> dfs(Grafo<T> grafo) {
		int tam = grafo.listaDeVertices().tamanio();
		boolean[] marcas = new boolean[tam + 1];
		ListaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<Vertice<T>>();
		lista.comenzar();
		for (int i = 1; i <= tam; i++) {
			if (!marcas[i]) {
				dfs(grafo, marcas, lista.proximo(), lista);
			}
		}
		return lista;
	}

	private void dfs(Grafo<T> grafo, boolean[] marcas, Vertice<T> vertice, ListaGenerica<Vertice<T>> lista) {
		marcas[vertice.getPosicion()] = true;
		lista.agregarFinal(vertice);
		ListaGenerica<Arista<T>> listaDeAdyacentes = grafo.listaDeAdyacentes(vertice);
		listaDeAdyacentes.comenzar();
		while (!listaDeAdyacentes.fin()) {
			Vertice<T> v = listaDeAdyacentes.proximo().verticeDestino();
			if (!marcas[v.getPosicion()]) {
				dfs(grafo, marcas, v, lista);
			}
		}
	}

	public ListaGenerica<Vertice<T>> bfs(Grafo<T> grafo) {

		ListaGenerica<Vertice<T>> resultado = new ListaEnlazadaGenerica<Vertice<T>>();
		if (!grafo.esVacio()) {

			ListaGenerica<Vertice<T>> listaDeVertices = grafo.listaDeVertices();
			int tam = listaDeVertices.tamanio();
			boolean[] marcas = new boolean[tam + 1];
			listaDeVertices.comenzar();
			for (int i = 1; i <= tam; i++) {
				Vertice<T> v = listaDeVertices.proximo();
				if (!marcas[i]) {
					bfs(grafo, v, marcas, resultado);
				}
			}
		}
		return resultado;
	}

	private void bfs(Grafo<T> grafo, Vertice<T> vInicial, boolean[] visitados, ListaGenerica<Vertice<T>> resultado) {
		ColaGenerica<Vertice<T>> cola = new ColaGenerica<Vertice<T>>();
		cola.encolar(vInicial);
		visitados[vInicial.getPosicion()] = true;
		while (!cola.esVacia()) {
			Vertice<T> vActual = cola.desencolar();
			resultado.agregarFinal(vActual);
			ListaGenerica<Arista<T>> listaDeAdyacentes = grafo.listaDeAdyacentes(vActual);
			listaDeAdyacentes.comenzar();
			while (!listaDeAdyacentes.fin()) {
				Vertice<T> vSiguiente = listaDeAdyacentes.proximo().verticeDestino();
				int j = vSiguiente.getPosicion();
				if (!visitados[j]) {
					visitados[j] = true;
					cola.encolar(vSiguiente);
				}
			}
		}
	}

}