package parciales_y_extras.parcial_08_08_2020;

public class Lugar {

	private String nombre;

	private int tiempoRecorrido;

	public Lugar(String nombre, int tiempoRecorrido) {
		this.nombre = nombre;
		this.tiempoRecorrido = tiempoRecorrido;
	}

	public String getNombre() {
		return nombre;
	}

	public int getTiempoRecorrido() {
		return tiempoRecorrido;
	}

}
