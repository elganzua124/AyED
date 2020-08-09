package parciales_y_extras.parcial_08_08_2020;

import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

public class ListaDeLugares {

	private ListaGenerica<Lugar> listaDeLugares;

	private int cantCuadras;

	public ListaDeLugares(int cantCuadras) {
		listaDeLugares = new ListaEnlazadaGenerica<Lugar>();
		this.cantCuadras = cantCuadras;
	}

	public ListaGenerica<Lugar> listaDeLugares() {
		return listaDeLugares;
	}

	public int tiempoTotal() {
		if (listaDeLugares.esVacia())
			return 0;
		int t = 0;
		listaDeLugares.comenzar();
		while (!listaDeLugares.fin())
			t += listaDeLugares.proximo().getTiempoRecorrido();
		return t;
	}

	public int cantCuadras() {
		return cantCuadras;
	}

	public void agregarFinal(Lugar lugar) {
		listaDeLugares.agregarFinal(lugar);
	}

	@Override
	public String toString() {

		String str = "Lista de lugares:\n\n";

		listaDeLugares.comenzar();
		while (!listaDeLugares.fin())
			str += listaDeLugares.proximo().getNombre() + ", ";

		str += "tiempo total de recorrido: " + tiempoTotal();

		return str;
	}

}
