package tp02.ej04;

import java.util.Scanner;

import estructuras.pila_y_cola.PilaGenerica;

public class TestBalanceo {

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		System.out.format("Escriba una cadena %n(escriba 'salir' para salir): ");
		String cadena = sc.nextLine();
		while (!cadena.equals("salir")) {
			System.out.println((testBalanceo(cadena) ? "Es" : "No es") + " balanceado.");
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

	private static boolean testBalanceo(String cadena) {
		PilaGenerica<Character> pila = new PilaGenerica<Character>();
		Character ch;
		for (int i = 0; i < cadena.length(); i++) {
			ch = cadena.charAt(i);
			if (ch == '(' || ch == '[' || ch == '{')
				pila.apilar(ch);
			else {
				if (pila.esVacia() || (pila.tope() != aperturaDeCierre(ch)))
					return false;
				pila.desapilar();
			}
		}
		return ( pila.esVacia() && (cadena.length() > 0) );
	}
}
