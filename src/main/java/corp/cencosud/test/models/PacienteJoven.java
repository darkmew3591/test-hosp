package corp.cencosud.test.models;

/**
 * Clase representativa de paciente joven, entre 16 y 40 anyos.
 * @author caracena
 *
 */
public class PacienteJoven extends Paciente {
	
	private boolean fumador;
	
	private int aniosFumador;
	
	public boolean isFumador() {
		return fumador;
	}

	public void setFumador(boolean fumador) {
		this.fumador = fumador;
	}

	public int getAniosFumador() {
		return aniosFumador;
	}

	public void setAniosFumador(int aniosFumador) {
		this.aniosFumador = aniosFumador;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("PacienteJoven [fumador=").append(fumador);
		builder.append(", aniosFumador=").append(aniosFumador);
		builder.append("]");
		return builder.toString();
	}
	
}
