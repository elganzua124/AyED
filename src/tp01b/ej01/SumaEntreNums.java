package tp01b.ej01;

import java.util.Arrays;

public class SumaEntreNums {

	public static void sumaEntreNumsA(int num1, int num2) {
		int suma = 0;
		int[] intArray = { num1, num2 };
		Arrays.sort(intArray);
		for (int actual = intArray[0]; actual <= intArray[1]; actual++) {
			suma = suma + actual;
		}
		System.out.print(suma + "\n");
	}

	public static void sumaEntreNumsB(int num1, int num2) {

		int[] intArray = { num1, num2 };
		Arrays.sort(intArray);
		int suma = 0;
		int actual = intArray[0];
		while (actual <= intArray[1]) {
			suma = suma + actual++;
		}

		System.out.print(suma + "\n");
	}

	public static void sumaEntreNumsC(int num1, int num2) { // formula de suma
		int[] intArray = { num1, num2 };
		Arrays.sort(intArray);
		int suma = (intArray[0] + intArray[1]) * (intArray[1] - intArray[0] + 1) / 2;
		System.out.print(suma + "\n");
	}

	public static void sumaEntreNumsC2(int num1, int num2) { // recursivo
		System.out.println(num1);
		if (num1 < num2) {
			sumaEntreNumsC2(num1 + 1, num2);
		}
	}

	public static void main(String[] args) {
		int num1 = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);

		sumaEntreNumsA(num1, num2);
		sumaEntreNumsB(num1, num2);
		sumaEntreNumsC(num1, num2);
	}
}
