package corp.cencosud.test.models;

/**
 * Paciente sobre los 41 años.
 * @author caracena
 *
 */
public class PacienteAnciano extends Paciente {

	private boolean tieneDieta;

	public boolean isTieneDieta() {
		return tieneDieta;
	}

	public void setTieneDieta(boolean tieneDieta) {
		this.tieneDieta = tieneDieta;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("PacienteAnciano [tieneDieta=").append(tieneDieta).append("]");
		return builder.toString();
	}
	
	
}
