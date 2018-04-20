package tp02_ej04;

import tp02_ej03.PilaGenerica;

import java.util.Scanner;

public class TestBalanceo {

	private static PilaGenerica<Character> pila = new PilaGenerica<Character>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.format("Escriba una cadena %n(escriba 'salir' para salir): ");
		String cadena = sc.nextLine();
		while (!cadena.equals("salir")) {
			if (testBalanceo(cadena))
				System.out.println("Es balanceado.");
			else
				System.out.println("No es balanceado.");
			System.out.format("Escriba una cadena %n(escriba 'salir' para salir): ");
			cadena = sc.nextLine();
		}
	}

	private static char aperturaDeCierre(char caracter) {
		if (caracter == ')')
			return '(';
		if (caracter == ']')
			return '[';
		if (caracter == '}')
			return '{';
		return ' ';
	}

	private static boolean esApertura(char caracter) {
		return (caracter == '(' || caracter == '[' || caracter == '{');
	}

	private static boolean testBalanceo(String cadena) {
		Character ch;
		pila.apilar('\b'); /* para que pila.tope() no tire error
		porque primer caracter no es de apertura. Evaluar siempre
		que no es vacia sólo para ese caso es un despropósito.*/
		for (int i=0;i < cadena.length();i++) {
			ch = cadena.charAt(i);
			if (esApertura(ch))
				pila.apilar(ch);
			else {
				if (pila.tope() == aperturaDeCierre(ch)) {
					pila.desapilar();
				} else
					return false;
			}
		}
		return cadena.length()>0;
	}
}
