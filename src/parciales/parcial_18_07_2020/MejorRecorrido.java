package parciales.parcial_18_07_2020;

import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

public class MejorRecorrido<T> {

	private ListaGenerica<T> mejorRecorrido;

	private int gastado;

	private int maxMonto;

	public MejorRecorrido(int maxMonto) {
		mejorRecorrido = new ListaEnlazadaGenerica<T>();
		gastado = 0;
		this.maxMonto = maxMonto;
	}

	public ListaGenerica<T> getRecorrido() {
		return mejorRecorrido;
	}

	public void cambiarRecorrido(ListaGenerica<T> mejorRecorrido, int gastado) {
		clonar(mejorRecorrido, this.mejorRecorrido);
		this.gastado = gastado;
	}

	public int sinGastar() {
		return maxMonto - gastado;
	}

	public int gastado() {
		return gastado;
	}

	public int maxMonto() {
		return maxMonto;
	}

	private void clonar(ListaGenerica<T> caminoOrigen, ListaGenerica<T> caminoDestino) {
		while (!caminoDestino.esVacia())
			caminoDestino.eliminarEn(1);

		caminoOrigen.comenzar();
		while (!caminoOrigen.fin()) {
			caminoDestino.agregarFinal(caminoOrigen.proximo());
		}

	}

}
