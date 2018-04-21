package tp03_ej01;

public class ArbolBinarioPrintTest {

	static ArbolBinario<Integer> arbolBinarioA;
	static ArbolBinario<Integer> arbolBinarioB;
	static ArbolBinario<Integer> arbolBinarioC;
	static ArbolBinario<Integer> arbolBinarioD;
	static ArbolBinario<Integer> arbolBinarioE;
	static ArbolBinario<Integer> arbolBinarioF;
	static ArbolBinario<Integer> arbolBinarioG;
	static ArbolBinario<Integer> arbolBinarioV;

	public static void main(String[] args) {
		// ----- arbolBinarioA -----
		arbolBinarioA = new ArbolBinario<Integer>(1);
		ArbolBinario<Integer> hijoIzquierdo = new ArbolBinario<Integer>(2);
		hijoIzquierdo.agregarHijoIzquierdo(new ArbolBinario<Integer>(3));
		hijoIzquierdo.agregarHijoDerecho(new ArbolBinario<Integer>(4));
		ArbolBinario<Integer> hijoDerecho = new ArbolBinario<Integer>(5);
		hijoDerecho.agregarHijoIzquierdo(new ArbolBinario<Integer>(6));
		hijoDerecho.agregarHijoDerecho(new ArbolBinario<Integer>(7));
		arbolBinarioA.agregarHijoIzquierdo(hijoIzquierdo);
		arbolBinarioA.agregarHijoDerecho(hijoDerecho);

		// ----- arbolBinarioB -----
		arbolBinarioB = new ArbolBinario<Integer>(1);
		ArbolBinario<Integer> hijoIzquierdoB = new ArbolBinario<Integer>(2);
		hijoIzquierdoB.agregarHijoIzquierdo(new ArbolBinario<Integer>(3));
		hijoIzquierdoB.agregarHijoDerecho(new ArbolBinario<Integer>(4));
		ArbolBinario<Integer> hijoDerechoB = new ArbolBinario<Integer>(6);
		hijoDerechoB.agregarHijoIzquierdo(new ArbolBinario<Integer>(7));
		hijoDerechoB.agregarHijoDerecho(new ArbolBinario<Integer>(8));
		arbolBinarioB.agregarHijoIzquierdo(hijoIzquierdoB);
		arbolBinarioB.agregarHijoDerecho(hijoDerechoB);

		// ----- arbolBinarioC -----
		arbolBinarioC = new ArbolBinario<Integer>(11);
		ArbolBinario<Integer> hijoIzquierdoC = new ArbolBinario<Integer>(12);
		hijoIzquierdoC.agregarHijoIzquierdo(new ArbolBinario<Integer>(13));
		hijoIzquierdoC.agregarHijoDerecho(new ArbolBinario<Integer>(14));
		ArbolBinario<Integer> hijoDerechoC = new ArbolBinario<Integer>(15);
		hijoDerechoC.agregarHijoDerecho(new ArbolBinario<Integer>(8));
		arbolBinarioC.agregarHijoIzquierdo(hijoIzquierdoC);
		arbolBinarioC.agregarHijoDerecho(hijoDerechoC);

		// ----- arbolBinarioD -----
		arbolBinarioD = new ArbolBinario<Integer>(11);
		ArbolBinario<Integer> hijoIzquierdoD = new ArbolBinario<Integer>(12);
		hijoIzquierdoD.agregarHijoIzquierdo(new ArbolBinario<Integer>(13));
		hijoIzquierdoD.agregarHijoDerecho(new ArbolBinario<Integer>(14));
		ArbolBinario<Integer> hijoDerechoD = new ArbolBinario<Integer>(15);
		hijoDerechoD.agregarHijoIzquierdo(new ArbolBinario<Integer>(8));
		arbolBinarioD.agregarHijoIzquierdo(hijoIzquierdoD);
		arbolBinarioD.agregarHijoDerecho(hijoDerechoD);

		// ----- arbolBinarioE -----
		arbolBinarioE = new ArbolBinario<Integer>(1);
		ArbolBinario<Integer> hijoIzquierdoE = new ArbolBinario<Integer>(2);
		hijoIzquierdoE.agregarHijoIzquierdo(new ArbolBinario<Integer>(4));
		hijoIzquierdoE.agregarHijoDerecho(new ArbolBinario<Integer>(5));
		ArbolBinario<Integer> hijoDerechoE = new ArbolBinario<Integer>(3);
		hijoDerechoE.agregarHijoIzquierdo(new ArbolBinario<Integer>(6));
		arbolBinarioE.agregarHijoIzquierdo(hijoIzquierdoE);
		arbolBinarioE.agregarHijoDerecho(hijoDerechoE);

		// ----- arbolBinarioF -----
		arbolBinarioF = new ArbolBinario<Integer>(1);
		ArbolBinario<Integer> hijoIzquierdoF = new ArbolBinario<Integer>(3);
		hijoIzquierdoF.agregarHijoDerecho(new ArbolBinario<Integer>(6));
		ArbolBinario<Integer> hijoDerechoF = new ArbolBinario<Integer>(2);
		hijoDerechoF.agregarHijoIzquierdo(new ArbolBinario<Integer>(5));
		hijoDerechoF.agregarHijoDerecho(new ArbolBinario<Integer>(4));
		arbolBinarioF.agregarHijoIzquierdo(hijoIzquierdoF);
		arbolBinarioF.agregarHijoDerecho(hijoDerechoF);

		
		
		// ---- Comienzo de tests de espejo de arboles ----
		
		System.out.println(arbolBinarioA.toString());
		arbolBinarioA.espejo();
		System.out.println(arbolBinarioA.toString());

		System.out.println("-----------------------");

		System.out.println(arbolBinarioB.toString());
		arbolBinarioB.espejo();
		System.out.println(arbolBinarioB.toString());

		System.out.println("-----------------------");

		System.out.println(arbolBinarioC.toString());
		arbolBinarioC.espejo();
		System.out.println(arbolBinarioC.toString());

		System.out.println("-----------------------");

		System.out.println(arbolBinarioD.toString());
		arbolBinarioD.espejo();
		System.out.println(arbolBinarioD.toString());

		System.out.println("-----------------------");

		System.out.println(arbolBinarioE.toString());
		arbolBinarioE.espejo();
		System.out.println(arbolBinarioE.toString());

		System.out.println("-----------------------");

		System.out.println(arbolBinarioF.toString());
		arbolBinarioF.espejo();
		System.out.println(arbolBinarioF.toString());

		// ----- Fin de tests de espejo de arboles -----
		
	}

}
