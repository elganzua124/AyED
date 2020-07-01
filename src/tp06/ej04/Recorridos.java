package tp06.ej04;

import estructuras.ColaGenerica;
import estructuras.grafo.*;
import estructuras.listas.*;

public class Recorridos<T> {

	public ListaGenerica<Vertice<T>> dfs(Grafo<T> grafo) {
		int tam = grafo.listaDeVertices().tamanio() + 1;
		boolean[] marcas = new boolean[tam];
		ListaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<Vertice<T>>();
		lista.comenzar();
		for (int i = 1; i <= tam; i++) {
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
		ListaGenerica<Vertice<T>> resultado = new ListaEnlazadaGenerica<Vertice<T>>();
		if (!grafo.esVacio()) {
			boolean[] visitados = new boolean[grafo.listaDeVertices().tamanio() + 1];
			ListaGenerica<Vertice<T>> listaDeVertices = grafo.listaDeVertices();
			listaDeVertices.comenzar();
			while (!listaDeVertices.fin()) {
				Vertice<T> vInicial = grafo.listaDeVertices().proximo();
				if (!visitados[vInicial.getPosicion()]) {
					bfs(vInicial, visitados, resultado, grafo);
				}
			}
		}
		return resultado;
	}

	private void bfs(Vertice<T> vInicial, boolean[] visitados, ListaGenerica<Vertice<T>> resultado, Grafo<T> grafo) {
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
				if (!visitados[vSiguiente.getPosicion()]) {
					visitados[vSiguiente.getPosicion()] = true;
					cola.encolar(vSiguiente);
				}
			}
		}
	}

}