package tp02_ej04;

import tp02_ej03.PilaGenerica;

public class TestBalanceo {

	private static PilaGenerica<Character> pila = new PilaGenerica<Character>();

	public static void main(String[] args) {
		String prueba = new String();
		prueba = "([{}({})])";
		System.out.format("Test balanceo de string: \"%s\"%n", prueba);
		if (testBalanceo(prueba))
			System.out.print("Es balanceado.");
		else
			System.out.print("No es balanceado.");
	}

	private static char aperturaDeCierre(char caracter) {
		if (caracter == ')')
			caracter = '(';
		else if (caracter == ']')
			caracter = '[';
		else if (caracter == '}')
			caracter = '{';
		return caracter;
	}

	private static boolean esApertura(char caracter) {
		return (caracter == '(' || caracter == '[' || caracter == '{');
	}

	public static boolean testBalanceo(String cadena) {
		boolean balanceado = true;
		int pos = 0;
		Character ch = cadena.charAt(pos);

		while (balanceado && pos != cadena.length()) {

			if (esApertura(ch)) {
				pila.apilar(ch);
			} else {
				if (pila.tope() == aperturaDeCierre(ch)) {
					pila.desapilar();
				} else
					balanceado = false;
			}

		}
		return balanceado;
	}
}
