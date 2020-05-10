package tp04.ej07;

import estructuras.arboles.ArbolGeneral;
import estructuras.listas.ListaEnlazadaGenerica;
import estructuras.listas.ListaGenerica;

public class Test {

	public static void main(String[] args) {
		ListaGenerica<ArbolGeneral<Integer>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<Integer>>();
		hijos.agregarFinal(new ArbolGeneral<Integer>(2));
		ArbolGeneral<Integer> a1 = new ArbolGeneral<Integer>(2,hijos);
	}
	
}
