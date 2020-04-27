package arbol_binario;

@SuppressWarnings("rawtypes")
public class ArbolBinarioPrintTest {

	static ArbolBinario objs[] = new ArbolBinario[6];

	private static void testArbol(ArbolBinario arbol) {
		System.out.print("Altura: " + arbol.altura());
		System.out.println("");
		System.out.println("Representacion:");
		System.out.println(arbol.toString());
		System.out.println("Su espejo:");
		System.out.println(arbol.espejo().toString());
		System.out.println("Recorrido por niveles:");
		arbol.recorridoPorNiveles();
		System.out.println("");
		System.out.println("Recorrido entre niveles 2 y 3:");
		arbol.entreNiveles(2,3);
		System.out.println("");
		System.out.println("-----------------------");
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		objs[0] = new ArbolBinario<Character>('a'); // upcasting
		ArbolBinario<Integer> hijoIzquierdo = new ArbolBinario<Integer>(2);
		hijoIzquierdo.agregarHijoIzquierdo(new ArbolBinario<Integer>(3));
		hijoIzquierdo.agregarHijoDerecho(new ArbolBinario<Integer>(4));
		ArbolBinario<Integer> hijoDerecho = new ArbolBinario<Integer>(5);
		hijoDerecho.agregarHijoIzquierdo(new ArbolBinario<Integer>(6));
		ArbolBinario f = new ArbolBinario<Integer>(8); // upcasting
		f.agregarHijoIzquierdo(new ArbolBinario<String>("holaa"));
		hijoDerecho.agregarHijoDerecho(f);
		objs[0].agregarHijoIzquierdo(hijoIzquierdo);
		objs[0].agregarHijoDerecho(hijoDerecho);

		objs[1] = new ArbolBinario<Integer>(1);
		ArbolBinario<Integer> hijoIzquierdoB = new ArbolBinario<Integer>(2);
		hijoIzquierdoB.agregarHijoIzquierdo(new ArbolBinario<Integer>(3));
		hijoIzquierdoB.agregarHijoDerecho(new ArbolBinario<Integer>(4));
		ArbolBinario<Integer> hijoDerechoB = new ArbolBinario<Integer>(6);
		hijoDerechoB.agregarHijoIzquierdo(new ArbolBinario<Integer>(7));
		hijoDerechoB.agregarHijoDerecho(new ArbolBinario<Integer>(8));
		objs[1].agregarHijoIzquierdo(hijoIzquierdoB);
		objs[1].agregarHijoDerecho(hijoDerechoB);

		objs[2] = new ArbolBinario<Integer>(11);
		ArbolBinario<Integer> hijoIzquierdoC = new ArbolBinario<Integer>(12);
		hijoIzquierdoC.agregarHijoIzquierdo(new ArbolBinario<Integer>(13));
		hijoIzquierdoC.agregarHijoDerecho(new ArbolBinario<Integer>(14));
		ArbolBinario<Integer> hijoDerechoC = new ArbolBinario<Integer>(15);
		hijoDerechoC.agregarHijoDerecho(new ArbolBinario<Integer>(8));
		objs[2].agregarHijoIzquierdo(hijoIzquierdoC);
		objs[2].agregarHijoDerecho(hijoDerechoC);

		objs[3] = new ArbolBinario<Integer>(11);
		ArbolBinario<Integer> hijoIzquierdoD = new ArbolBinario<Integer>(12);
		hijoIzquierdoD.agregarHijoIzquierdo(new ArbolBinario<Integer>(13));
		hijoIzquierdoD.agregarHijoDerecho(new ArbolBinario<Integer>(14));
		ArbolBinario<Integer> hijoDerechoD = new ArbolBinario<Integer>(15);
		hijoDerechoD.agregarHijoIzquierdo(new ArbolBinario<Integer>(8));
		objs[3].agregarHijoIzquierdo(hijoIzquierdoD);
		objs[3].agregarHijoDerecho(hijoDerechoD);

		objs[4] = new ArbolBinario<Integer>(1);
		ArbolBinario<Integer> hijoIzquierdoE = new ArbolBinario<Integer>(2);
		hijoIzquierdoE.agregarHijoIzquierdo(new ArbolBinario<Integer>(4));
		hijoIzquierdoE.agregarHijoDerecho(new ArbolBinario<Integer>(5));
		ArbolBinario<Integer> hijoDerechoE = new ArbolBinario<Integer>(3);
		hijoDerechoE.agregarHijoIzquierdo(new ArbolBinario<Integer>(6));
		objs[4].agregarHijoIzquierdo(hijoIzquierdoE);
		objs[4].agregarHijoDerecho(hijoDerechoE);

		objs[5] = new ArbolBinario<Integer>(1);
		ArbolBinario<Integer> hijoIzquierdoF = new ArbolBinario<Integer>(3);
		hijoIzquierdoF.agregarHijoDerecho(new ArbolBinario<Integer>(6));
		ArbolBinario hijoDerechoF = new ArbolBinario<String>("jeje"); // upcasting
		hijoDerechoF.agregarHijoIzquierdo(new ArbolBinario<Integer>(5));
		hijoDerechoF.agregarHijoDerecho(new ArbolBinario<Integer>(4));
		objs[5].agregarHijoIzquierdo(hijoIzquierdoF);
		objs[5].agregarHijoDerecho(hijoDerechoF);

		System.out.println("###########################");
		System.out.println("#     Inicio de tests     #");
		System.out.println("###########################");
		System.out.println("");

		for (ArbolBinario arbol : objs) {
			testArbol(arbol);
		}

	}

}
