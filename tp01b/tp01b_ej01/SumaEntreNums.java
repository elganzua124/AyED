package tp01b_ej01;

import java.util.Arrays;

public class SumaEntreNums {

	public static void sumaEntreNums1(int num1, int num2) {
		int suma = 0;
		int[] intArray = { num1, num2 };
		Arrays.sort(intArray);
		for (int actual = intArray[0]; actual <= intArray[1]; actual++) {
			suma = suma + actual;
		}
		System.out.print(suma + "\n");
	}

	public static void sumaEntreNums2(int num1, int num2) {

		int[] intArray = { num1, num2 };
		Arrays.sort(intArray);
		int suma = 0;
		int actual = intArray[0];
		while (actual <= intArray[1]) {
			suma = suma + actual++;
		}

		System.out.print(suma + "\n");
	}

	public static void sumaEntreNums3(int num1, int num2) {
		int[] intArray = { num1, num2 };
		Arrays.sort(intArray);
		int suma = (intArray[0] + intArray[1]) * (intArray[1] - intArray[0] + 1) / 2;
		System.out.print(suma + "\n");
	}

	public static void main(String[] args) {
		int num1 = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);

		sumaEntreNums1(num1, num2);
		sumaEntreNums2(num1, num2);
		sumaEntreNums3(num1, num2);
	}
}
