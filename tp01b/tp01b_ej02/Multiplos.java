package tp01b_ej02;

import java.util.Scanner;
import java.util.Arrays;

public class Multiplos {
	public static int[] multiplosDeNumero(int n) {
		int[] intArray = new int[n];
		for (int i = 0; i < n; i++)
			intArray[i] = n * (i + 1);
		return intArray;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Por favor, ingrese un número: ");
		int num = s.nextInt();
		while (num != 0) {
			System.out.println(Arrays.toString(multiplosDeNumero(num)));
			System.out.print("Por favor, ingrese un número: ");
			num = s.nextInt();
		}

	}

}
