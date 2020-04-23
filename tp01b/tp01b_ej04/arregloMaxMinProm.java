package tp01b_ej04;

import java.util.Scanner;

public class arregloMaxMinProm {
	public static int[] maxminpromA(int[] nums) {

	}

	public static int[] maxminpromB(int[] nums) {

	}

	public static void maxminpromC(int[] nums) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Ingrese un numero para definir " + "la dimension fisica del arreglo: ");

		int num = scan.nextInt();

		int[] arreglo = new int[num];

		for (int i = 0; i < arreglo.length; i++) {
			System.out.println("Ingrese un numero n " + "para posicion: " + i + " del arreglo: ");
			arreglo[i] = scan.nextInt();
		}

		scan.close();

		System.out.print("arreglo = [");

		for (int i = 0; i < arreglo.length; i++) {
			System.out.print(arreglo[i]);
			if (i < arreglo.length - 1)
				System.out.print(",");
		}

		System.out.print("]" + "\n");

		int max = 0;
		int min = 999;
		float sum = 0;

		for (int i = 0; i < arreglo.length; i++) {
			if (arreglo[i] > max)
				max = arreglo[i];
			if (arreglo[i] < min)
				min = arreglo[i];
			sum += arreglo[i];
		}

		float prom = sum / arreglo.length;

		System.out.print("El Maximo es: " + max + "\n");
		System.out.print("El Minimo es: " + min + "\n");
		System.out.print("El Promedio es: " + prom + "\n");

	}
}
