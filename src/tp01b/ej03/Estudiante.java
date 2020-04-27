package tp01b.ej03;

public class Estudiante extends Persona {
	private String comision;
	private String direccion;

	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String tusDatos() {
		return super.tusDatos() + ", comision: " + getComision() + ", direccion: " + getDireccion() + ".";
	}
}
