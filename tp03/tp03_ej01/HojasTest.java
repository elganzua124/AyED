package tp03_ej01;

public class HojasTest {

	public static void main(String[] args) {
		ArbolBinario<Integer> arbolBinarioA = new ArbolBinario<Integer>(1);		
		ArbolBinario<Integer> hijoIzquierdo=new ArbolBinario<Integer>(2);
		hijoIzquierdo.agregarHijoIzquierdo(new ArbolBinario<Integer>(4));
		hijoIzquierdo.agregarHijoDerecho(new ArbolBinario<Integer>(5));		
		ArbolBinario<Integer> hijoDerecho=new ArbolBinario<Integer>(3);
		hijoDerecho.agregarHijoIzquierdo(new ArbolBinario<Integer>(6));
		hijoDerecho.agregarHijoDerecho(new ArbolBinario<Integer>(7));
		arbolBinarioA.agregarHijoIzquierdo(hijoIzquierdo);
		arbolBinarioA.agregarHijoDerecho(hijoDerecho);

		
		//System.out.println(arbolBinarioA.contarHojas());
		System.out.println(arbolBinarioA.toString());
		arbolBinarioA.espejo();
		System.out.println(arbolBinarioA.toString());
		
	}

}
