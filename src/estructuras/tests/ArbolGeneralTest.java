package estructuras.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import estructuras.arboles.ArbolGeneral;

public class ArbolGeneralTest {

	ArbolGeneral<Integer> ag;
	ArbolGeneral<Integer> a2;
	ArbolGeneral<Integer> a3;
	ArbolGeneral<Integer> a4;
	ArbolGeneral<Integer> a5;
	ArbolGeneral<Integer> a6;
	ArbolGeneral<Integer> a7;
	ArbolGeneral<Integer> a8;
	ArbolGeneral<Integer> a9;
	ArbolGeneral<Integer> a10;

	@Before
	public void setUp() throws Exception {

		ag = new ArbolGeneral<Integer>(1);

		a2 = new ArbolGeneral<Integer>(2);
		a3 = new ArbolGeneral<Integer>(3);
		a4 = new ArbolGeneral<Integer>(4);
		a5 = new ArbolGeneral<Integer>(5);
		a6 = new ArbolGeneral<Integer>(6);
		a7 = new ArbolGeneral<Integer>(7);
		a8 = new ArbolGeneral<Integer>(8);
		a9 = new ArbolGeneral<Integer>(9);
		a10 = new ArbolGeneral<Integer>(10);
		ag.agregarHijo(a2);
		ag.agregarHijo(a3);
		ag.agregarHijo(a4);
		a2.agregarHijo(a5);
		a3.agregarHijo(a6);
		a3.agregarHijo(a7);
		a3.agregarHijo(a8);
		a4.agregarHijo(a9);

		/*
		 * Arbol usado en las pruebas 1 2 3 4 5 6 7 8 9
		 * 
		 */

	}

	@Test
	public void testAltura() {
		assertEquals(Integer.valueOf(2), ag.altura());
		assertEquals(Integer.valueOf(1), a2.altura());
		assertEquals(Integer.valueOf(0), a5.altura());
	}

	@Test
	public void testNivel() {
		// Prueba para un arbol que es solo una hoja
		assertEquals(Integer.valueOf(0), a5.nivel(5));

		// Prueba para distintos elementos del arbol ag
		assertEquals(Integer.valueOf(0), ag.nivel(1));
		assertEquals(Integer.valueOf(1), ag.nivel(2));
		assertEquals(Integer.valueOf(2), ag.nivel(7));
		assertEquals(Integer.valueOf(2), ag.nivel(9));
		assertEquals(Integer.valueOf(-1), ag.nivel(10));
	}

	@Test
	public void testAncho() {
		assertEquals(Integer.valueOf(5), ag.ancho());
		assertEquals(Integer.valueOf(3), a3.ancho());
		assertEquals(Integer.valueOf(1), a4.ancho());

	}
	/*
	 * @Test(expected = NullPointerException.class) // ya lo arregle public void
	 * testEsVacio() { ArbolGeneral<Integer> arbolBinarioA = new
	 * ArbolGeneral<Integer>(); arbolBinarioA.getHijos(); }
	 */
}
