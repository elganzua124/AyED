package tp04.ej05;

import estructuras.ColaGenerica;
import estructuras.arboles.ArbolGeneral;
import estructuras.listas.ListaGenerica;

public class EmpresaComunicacion {

	public static Integer devolverMaximoPromedio(ArbolGeneral<AreaEmpresa> arbol) {

		ColaGenerica<ArbolGeneral<AreaEmpresa>> cola = new ColaGenerica<ArbolGeneral<AreaEmpresa>>();

		cola.encolar(arbol);
		cola.encolar(null);

		ArbolGeneral<AreaEmpresa> arbolActual;

		int promNivel = 0, count = 0, max = 0;
		ListaGenerica<ArbolGeneral<AreaEmpresa>> hijos;

		while (!cola.esVacia()) {
			arbolActual = cola.desencolar();
			if (arbolActual != null && !arbolActual.esVacio()) {
				promNivel += arbolActual.getDatoRaiz().getTardanzaDeTransmision();
				count++;
				hijos = arbolActual.getHijos();
				hijos.comenzar();
				while (!hijos.fin())
					cola.encolar(hijos.proximo());
			} else if (arbolActual == null) {
				cola.encolar(null);
				if (count > 0)
					promNivel /= count;
				max = Math.max(max, promNivel);
				promNivel = 0;
				count = 0;
			}
		}
		return max;
	}

}
