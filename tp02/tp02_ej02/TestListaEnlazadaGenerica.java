package tp02_ej02;

import tp01b_ej03.Estudiante;

public class TestListaEnlazadaGenerica {

	public static void main(String[] args) {
		ListaEnlazadaGenerica<Estudiante> lista = new ListaEnlazadaGenerica<>();

		lista.agregarFinal(crearEstudiante("pablo", "fonfi", "ff@few.com", "comision 2", "23 y 67 num 563"));
		lista.agregarEn((crearEstudiante("bernardo", "alcides", "werew@few.com", "comision 3", "123 y 67 num 2223")),1);
		lista.agregarFinal(crearEstudiante("ricarda", "remnuy", "ffwww@few.com", "comision 5", "23 y 45 num 1126"));
		lista.agregarFinal(crearEstudiante("alejandra", "quonti", "z23sds@few.com", "comision 7", "11 y 74 num 5554"));

		System.out.println("Datos de alumnos:");
		lista.comenzar();
		while (!lista.fin())
			System.out.println(lista.proximo().tusDatos());
	}

	public static Estudiante crearEstudiante(String nombre, String apellido, String email, String comision,
			String direccion) {
		Estudiante estudiante = new Estudiante();
		estudiante.setApellido(apellido);
		estudiante.setNombre(nombre);
		estudiante.setEmail(email);
		estudiante.setComision(comision);
		estudiante.setDireccion(direccion);
		return estudiante;
	}

}