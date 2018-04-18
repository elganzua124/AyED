package tp01b_ej03;

public class Test {

	public static void main(String[] args) {
		Estudiante[] estudiantes = new Estudiante[2];
		Profesor[] profesores = new Profesor[3];
		for (int i = 0; i < 2; i++) {
			estudiantes[i] = new Estudiante();
			setearEstudiante(estudiantes[i], "nombre", "apellido", "email", "comision", "direccion");
		}
		for (int i = 0; i < 3; i++) {
			profesores[i] = new Profesor();
			setearProfesor(profesores[i], "nombre", "apellido", "catedra", "facultad", "email");
		}
		
		for (int i = 0; i < 2; i++) {
			System.out.println("Datos de estudiante:");
			System.out.println(estudiantes[i].tusDatos());
		}
		//falta para profesores
	}

	public static void setearEstudiante(Estudiante estudiante, String nombre, String apellido, String email,
			String comision, String direccion) {
		estudiante.setApellido(apellido);
		estudiante.setNombre(nombre);
		estudiante.setEmail(email);
		estudiante.setComision(comision);
		estudiante.setDireccion(direccion);
	}

	public static void setearProfesor(Profesor profesor, String nombre, String apellido, String catedra,
			String facultad, String email) {
		profesor.setApellido(apellido);
		profesor.setCatedra(catedra);
		profesor.setEmail(email);
		profesor.setFacultad(facultad);
		profesor.setNombre(nombre);
	}
}
