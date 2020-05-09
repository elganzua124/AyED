package tp04.ej05;

public class AreaEmpresa {

	private String identificacion;
	private Integer tardanzaDeTransmision;

	public String getIdentificacion() {
		return identificacion;
	}

	public Integer getTardanzaDeTransmision() {
		return tardanzaDeTransmision;
	}

	public AreaEmpresa(String identificacion, Integer tardanzaDeTransmision) {
		this.identificacion = identificacion;
		this.tardanzaDeTransmision = tardanzaDeTransmision;
	}

}
