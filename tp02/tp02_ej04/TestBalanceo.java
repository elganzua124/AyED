package tp02_ej04;

import tp02_ej03.PilaGenerica;


public class TestBalanceo {

	private static PilaGenerica<Character> pila = new PilaGenerica<Character>();

	public static void main(String[] args) {
		String prueba = new String();
		prueba = "([{}({})])";
		System.out.format("Test balanceo de string: \"%s\"%n",prueba);
		if (testBalanceo(prueba, 0))
			System.out.print("Es balanceado.");
		else
			System.out.print("No es balanceado.");
	}

	private static char aperturaDeCierre(char cadena) {
		if (cadena == ')')
			cadena = '(';
		else if (cadena == ']')
			cadena = '[';
		else if (cadena == '}')
			cadena = '{';
		return cadena;
	}

	private static boolean esApertura(char caracter) {
		return (caracter == '(' || caracter == '[' || caracter == '{');
	}

	public static boolean testBalanceo(String cadena, int pos) {
		if (pos == cadena.length()) {
			return true;
		}
		Character ch = cadena.charAt(pos);
		if (esApertura(ch)) {
			pila.apilar(ch);
		} else {
			if (pila.tope() == aperturaDeCierre(ch)) {
				pila.desapilar();
			} else
				return false;
		}
		return testBalanceo(cadena, ++pos);
	}
}
