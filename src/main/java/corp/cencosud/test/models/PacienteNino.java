package corp.cencosud.test.models;


/**
 * Clase que representa paciente nino desde 1 a 15 años
 * @author caracena
 *
 */
public class PacienteNino extends Paciente {
	
	private int relacionPesoEstatura;

	public int getRelacionPesoEstatura() {
		return relacionPesoEstatura;
	}

	public void setRelacionPesoEstatura(int relacionPesoEstatura) {
		this.relacionPesoEstatura = relacionPesoEstatura;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("PacienteNino [relacionPesoEstatura=").append(relacionPesoEstatura).append("]");
		return builder.toString();
	}
	
	
}
