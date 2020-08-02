package parciales_y_extras.parcial_16_12_2013;

import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

public class Ciclo<T> {

	private ListaGenerica<T> ciclo;

	private int peso;

	public Ciclo() {
		ciclo = new ListaEnlazadaGenerica<T>();
		peso = -1;
	}

	public ListaGenerica<T> getCiclo() {
		return ciclo;
	}

	public int getPeso() {
		return peso;
	}

	public void cambiarCiclo(ListaGenerica<T> ciclo, int peso) {
		clonar(ciclo, this.ciclo);
		this.peso = peso;
	}

	private void clonar(ListaGenerica<T> caminoOrigen, ListaGenerica<T> caminoDestino) {
		while (!caminoDestino.esVacia())
			caminoDestino.eliminarEn(1);

		caminoOrigen.comenzar();
		while (!caminoOrigen.fin()) {
			caminoDestino.agregarFinal(caminoOrigen.proximo());
		}

	}

	@Override
	public String toString() {
		String str = "Mejor ciclo:\n\n";

		str += ciclo.toString() + "\n\n";

		str += "peso: $" + getPeso();

		return str;
	}

}
