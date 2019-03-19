package corp.cencosud.test.models;

/**
 * Clase que representa la consulta de un hospital.
 * @author caracena
 *
 */
public class Consulta {

	private int id;
	private int cantPacientes;
	private String nombreEspecialista;
	private String tipoConsulta;
	private String estado;
	private Paciente pacienteAtendido;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantPacientes() {
		return cantPacientes;
	}
	public void setCantPacientes(int cantPacientes) {
		this.cantPacientes = cantPacientes;
	}
	public String getNombreEspecialista() {
		return nombreEspecialista;
	}
	public void setNombreEspecialista(String nombreEspecialista) {
		this.nombreEspecialista = nombreEspecialista;
	}
	public String getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Paciente getPacienteAtendido() {
		return pacienteAtendido;
	}
	public void setPacienteAtendido(Paciente pacienteAtendido) {
		this.pacienteAtendido = pacienteAtendido;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Consulta [id=").append(id).append(", cantPacientes=").append(cantPacientes)
				.append(", nombreEspecialista=").append(nombreEspecialista).append(", tipoConsulta=")
				.append(tipoConsulta).append(", estado=").append(estado).append(", pacienteAtendido=")
				.append(pacienteAtendido).append("]");
		return builder.toString();
	}
	
	
	
}
