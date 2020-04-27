package tp02.ej01_6;

import estructuras.listas.ListaDeEnterosEnlazada;

/* Ej. 1.6 a)
 * Escriba un programa recursivo que, a partir de un número n,
 * devuelva una lista con cada miembro de la sucesión.
*/


public class Ejercicio1_6 {
	private ListaDeEnterosEnlazada lista = new ListaDeEnterosEnlazada();

	public ListaDeEnterosEnlazada calcularSucesion(int n) {
		lista.agregarFinal(n);
		if (n > 1) {
			if ((n % 2) == 0) {
				n = n / 2;
			} else {
				n = (3 * n) + 1;
			}
			this.calcularSucesion(n);
		}
		return lista;
	}
}
