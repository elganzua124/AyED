package tp01b.ej04;

/*
Dado un arreglo de valores tipo int se desea calcular el valor m�ximo, m�nimo, y
promedio en un �nico m�todo. Escriba tres m�todos de clase, donde respectivamente:
a. Devuelva lo pedido por el mecanismo de retorno de un m�todo en Java
("return").
b. Devuelva lo pedido interactuando con alg�n par�metro (el par�metro no puede
ser de tipo arreglo).
c. Devuelva lo pedido sin usar par�metros ni la sentencia "return".
*/

import java.util.Scanner;

public class Calculadora {
	public static Datos maxminpromA(int[] nums) {

		Datos d = new Datos();
		int min = 999;
		int max = 1;
		float tot = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > max)
				max = nums[i];
			if (nums[i] < min)
				min = nums[i];
			tot = tot + nums[i];
		}

		d.setMin(min);
		d.setMax(max);
		d.setProm(tot / nums.length);

		return d;
	}

	public static int[] maxminpromB(int[] nums) {// terminar
		int t[] = new int[10];
		return t;

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
